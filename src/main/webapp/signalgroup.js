let localhost = document.location.host;
let room;
let protocol = window.location.protocol == "http:" ? "ws:" : "wss:";
let AudioContext = window.AudioContext || window.webkitAudioContext;
let recorded = false;
let gainNode = null;
/*
let ws = new WebSocket(protocol+"//"+localhost+"/DoAnIMessage/call/"+room);
ws.binaryType = "arraybuffer";
ws.onmessage = function (event) {
	convertToAudio(event);
}
ws.addEventListener('error', function (event) {
	  console.log('WebSocket error: ', event);
});
ws.onclose = function (event) {
	  console.log('The connection has been closed successfully.', close);
};
*/
let audioCtx = new AudioContext();
let userID;
let ws = null;
function connectAudio(roomID){
	room = roomID;
	if (ws == null || ws.readyState != 1){
		ws = new WebSocket(protocol+"//"+localhost+"/DoAnIMessage/call/"+room);
		ws.binaryType = "arraybuffer";
		ws.onmessage = function (event) {
			convertToAudio(event);
		}
		ws.addEventListener('error', function (event) {
			  console.log('WebSocket error: ', event);
		});
		ws.onclose = onerrorconnect;
	}
	audioCtx.resume();
}
function onerrorconnect(event){
	console.log('The connection has been closed successfully.');
	console.log(event);
	ws = new WebSocket(protocol+"//"+localhost+"/DoAnIMessage/call/"+room);
	ws.binaryType = "arraybuffer";
	ws.onmessage = function (event) {
		convertToAudio(event);
	}
	ws.addEventListener('error', function (event) {
		  console.log('WebSocket error: ', event);
	});
	ws.onclose = onerrorconnect;
}
function record(id) {
	if (!recorded){
	recorded = true;
	constraints = { audio: true };
	navigator.mediaDevices.getUserMedia(constraints).then((stream) => {
	    let source = audioCtx.createMediaStreamSource(stream);
	    audioCtx.audioWorklet.addModule("audio-processor.js").then(() => {
	        const customAudioProcessor = new AudioWorkletNode(audioCtx, "audio-processor");
	        //customAudioProcessor.socket = ws;
	        userID = id;
	        //userID = Id;
	        customAudioProcessor.port.onmessage = sendToServer;
	        //customAudioProcessor.recording = true;
			//var dest = audioCtx.createMediaStreamDestination();
			gainNode = audioCtx.createGain();
			source.connect(gainNode);
	        gainNode.connect(customAudioProcessor);//.connect(dest);
			//let video = document.getElementById("localStream");
			//video.srcObject = dest.stream;
	    }); 
	    //audioCtx.destination.play();
	});
	}
}
function voiceMute(mute) {
  if(mute.id == "") {
    gainNode.gain.setValueAtTime(0, audioCtx.currentTime);
    mute.id = "activated";
    mute.textContent = "";
  } else {
    gainNode.gain.setValueAtTime(1, audioCtx.currentTime);
    mute.id = "";
    mute.textContent = "Mute";
  }
}

let index = 1;
let Channel = [];
let count = [];
let asource;
let time = [];
function sendToServer(event){
	let array = event.data;
	let dataToSend = new Float32Array(129);
	dataToSend.set(array[0]);
	dataToSend[128] = userID;
	if (ws instanceof WebSocket){
	if (ws.readyState == 1)
		ws.send(dataToSend.buffer);
	}
}
function convertToAudio(event) {
	//console.log(event);
	let array = event.data;
	let floatArray = new Float32Array(array);
	let id = floatArray[128];
	floatArray = floatArray.slice(0,128);
	if (!time[id]){
		time[id] = audioCtx.currentTime;
		count[id] = 0;
		Channel[id] = new Float32Array(3840);
	}
	if (count[id] <= 29){
		Channel[id].set(floatArray,count[id]*128);
		count[id]++;
	}
	if (count[id] == 30){
		count[id] = 0;
		let myArrayBuffer = audioCtx.createBuffer(1, 3840, audioCtx.sampleRate);
		myArrayBuffer.copyToChannel(Channel[id],0,0);
		time[id] += 0.03;
		playSound(myArrayBuffer,time[id]);
		Channel[id] = new Float32Array(3840);
	}
	/*
	let source = aCtx.createBufferSource();
	aCtx.decodeAudioData(buffer).then((audio) => {
		source.buffer = audio;
		source.connect(aCtx.destination);
		source.start();
	});
	*/
}
function playSound(buffer, playTime) {
	var source = audioCtx.createBufferSource();
	source.buffer = buffer;
	source.start(playTime);
	source.connect(audioCtx.destination);
	}
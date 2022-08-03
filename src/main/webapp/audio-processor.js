registerProcessor("audio-processor",class AudioProcessor extends AudioWorkletProcessor {
    process (inputs, outputs, parameters) {
    	const input = inputs[0];
    	this.port.postMessage(input);
        return true;
    }
    
    constructor(){
    	super();
        //console.log(this.port);
        this.port.start();
    }
    
    set socket(ws){
    	console.log(ws);
    	this._socket = ws;
    	console.log(this._socket);
    }
    get socket(){
    	return this._socket;
    }
})
let ws = null;
let userID;
function getUserById(id){	
    		return $.post("C_getUserById",{ID : id},function(data,status){
    			document.getElementById("text-call").innerHTML = data.Fullname + " calling to you......";
    		});
}
function setOnline(id){
	let localhost = document.location.host;
	let protocol = window.location.protocol == "http:" ? "ws:" : "wss:";
	ws = new WebSocket(protocol+"//"+localhost+"/DoAnIMessage/online/"+id);
	ws.onmessage = function (event) {
		if (event.data){
			let caller = document.getElementById("call-to");
			caller.classList.add("open");
			console.log(event.data);
			getUserById(event.data);
			document.getElementById("batmay").onclick = function(){
				popitup('Call.jsp?id='+event.data, 'calling');
				closeCall();
			};
		};
	};
	userID = id;
}
function closeCall(){
	let caller = document.getElementById("call-to");
	caller.classList.remove("open");
}
function callOther(id){
	if (ws == null || ws.readyState != 1){
		setOnline(userID);
	}
	ws.send(id);
}
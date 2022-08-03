
<!DOCTYPE html>
<%@page import="Model.BEAN.User"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./font/themify-icons/themify-icons.css">
<link rel="stylesheet" href="./font/fontawesome-free-5.15.3-web/css/all.css">
<script src='signalgroup.js'></script>
<title>Call</title>
</head>
<body>
	<% User user = (User)request.getSession().getAttribute("User"); 
	  String IdRoom = request.getParameter("id");
	%>
    <div class="person">
    </div>
    <div class="call__user">
        <div class="call__user-img">

		    <img class="img_person" src="./img/person.png" alt="">
        </div>

    </div>
    <div class="call__function">
        <div class="call__receive call__voice icon-calls">
            <i class="fas fa-phone-square-alt" onclick="connectAudio('<%=IdRoom%>'); record('<%=user.getUser_ID()%>');"></i>
        </div>



        <div class="call__mic">
            <i class="fas fa-microphone icon-calls" onclick="voiceMute(this);"></i>
        </div>



        <div class="call__refuse call__voice icon-calls">
            <i class="fas fa-phone-square-alt" onclick="javascript:window.close('','_parent','');"></i>
        </div>
    </div>
</body>
<script>
function count_time(){
    var hour= console.log(document.getElementsByClassName("js_hour").innerHTML);
    var minute= console.log(document.getElementsByClassName("js_minute").innerHTML);
    var second= console.log(document.getElementsByClassName("js_second").innerHTML);
    second+=1;
    if(second==60){
        minute+=1;
        second=0;
    }
    if(minute=60){
        hour+=1;
        minute=0;   
    }
    if(hour<10){
        hour="0"+hour;
    }
    if(minute<10){
        minute="0"+minute;
    }
    if(second<10){
        second="0"+second;
    }
document.getElementById("clock").innerHTML = hour+" :"+minute+" : "+second;
}



</script>
</html>
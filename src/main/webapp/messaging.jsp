<%@page import="java.util.List"%>
<%@page import="Model.BEAN.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%
User user = (User) request.getAttribute(("User"));
if (user == null)
	user = (User) session.getAttribute("User");
else {
	session.setAttribute("User", user);
}
String targetId = request.getParameter("targetID");
if (targetId == null)
	targetId = "-1";
%>
<title>Document</title>
<link rel="stylesheet" href="./css/base.css">
<link rel="stylesheet" href="./css/normalize.css">
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet"
	href="./font/fontawesome-free-5.15.3-web/css/all.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="online.js"></script>
<script>
		setOnline('<%=user.getUser_ID()%>');
    	loadListFriend();//da sua
    	getInfoFriend("<%out.print(targetId);%>");
    	setInterval(loadMessage,1000);
		function loadMessage(){
    		$.post("C_loadmessage",{sendID : "<%out.print(user.getUser_ID());%>", receiveID : "<%out.print(targetId);%>"},function(data,status){
    			document.getElementsByClassName("message__body")[0].innerHTML = data;
    		});	
    	};
       	function sendMessage(){
    		let content = document.getElementById("message-content").value;
    		if(content != ""){
    			$.post("C_sendmessage",{sendID : "<%out.print(user.getUser_ID());%>", receiveID : "<%out.print(targetId);%>", messContent : content}); 
        		loadMessage();
        		loadListFriend();//da sua
        		document.getElementById("message-content").value = "";
    		}
    	};
		function loadListFriend(){
    		$.post("C_loadListFriend",{UserID : "<%out.print(user.getUser_ID());%>"},function(data,status){
    			document.getElementsByClassName("list__friend")[0].innerHTML = data;
    		});	
    	};
    	function searchFriend(){
    		let name = document.getElementById("searchFriend").value;
    		$.post("C_search",{Name : name},function(data,status){
    			document.getElementsByClassName("list__friend")[0].innerHTML = data;
    			document.getElementById("searchFriend").value ="";
    		});	
    	};
    	function searchGroup(){
    		let name = document.getElementById("searchGroup").value;
    		$.post("C_searchGroup",{Name : name},function(data,status){
    			document.getElementsByClassName("list__friend-group")[0].innerHTML = data;
    			document.getElementById("searchGroup").value ="";
    		});	
    	};
    	function insertID(ID){
    		$.post("C_insertFriend",{ID_Friend : ID},function(data,status){});
    	};
    	function createGroup(){
    		let name = document.getElementById("nameGroup").value;
    		$.post("C_createGroup",{NameGroup : name},function(data,status){
    			document.getElementById("nameGroup").value ="";
    		});	
    		loadListFriend();
    	};
    	function popitup(url,windowName) {
    	       newwindow=window.open(url,windowName,'height=600,width=1200');
    	       if (window.focus) {newwindow.focus()}
    	       return false;
    	};
    	function deleteMessage(recieveid){//themmoi
    		$.post("C_deleteMessage",{sendID : "<%out.print(user.getUser_ID());%>", receiveID : recieveid}); 
    		loadMessage();
    		loadListFriend();//da sua
    	};
    	function getInfoFriend(id){
    		$.post("C_getUserById",{ID : id},function(data,status){
    			document.getElementById("headerNameFriend").innerHTML = data.Fullname+"";
    			document.getElementById("nameFriend").value = data.Fullname+"";
    			document.getElementById("ageFriend").value = data.Age+"";
    			if(data.Gender) document.getElementById("genderFriend").value = "Nam";
    			else document.getElementById("genderFriend").value = "Nữ";
    		});	
    	};
    </script>
</head>
<body>
	<div class="grid">
		<div class="form_message">
			<div class="account">
				<div class="account__header">
					<div class="account__header-bar">
						<div class="account__header-user">
							<i class="account__header-img fas fa-user-circle icon-user"></i>
							<div class="user-info"><%=user.getFullname()%></div>
							<!-- account__user-info--appear -->
							<div class="account__user-info"></div>
						</div>
						<div class="account__header-function">
							<div class="account__header-mess font__img-account"
								onclick="loadListFriend()">
								<i class="fab fa-facebook-messenger icon-header"></i>
							</div>
							<div class="account__header-friend font__img-account">
								<i class="fas fa-users icon-header"></i>
							</div>
							<a href="C_logout">
								<div class=".account__header-friend font__img-account">
									<i class="fas fa-outdent icon-header"></i>
								</div>
							</a>
						</div>
					</div>
					<div class="account__header-search">
						<input type="text" name="Search" placeholder="Tìm kiếm"
							id="searchFriend"> <input type="button" value="Search"
							class="btn-search" onclick="searchFriend()">
					</div>
				</div>
				<div class="account__body">
					<ul class="list__friend">
					</ul>
				</div>
			</div>
			<div class="message">
				<div class="message__header">
					<div class="message__header-info">
						<i class="message__header-img far fa-user-circle icon-user"></i> 
						<span class="message__header-name" id="headerNameFriend"></span>
					</div>
					<div class="message__header-func">
						<a href="" class="message__header-voiced" onclick="popitup('Call.jsp?id=<%=user.getUser_ID()%>', 'calling')"> 
						<i class="font__img-account fas fa-phone icon-mess" onclick="callOther('<%=targetId%>');"></i>
						</a> 
						<a> 
						<i	class="font__img-account fas fa-info-circle icon-mess icon-info"></i>
						</a>
					</div>
				</div>
				<div class="message__body"></div>
				<div class="message__submit">
					<input class="message__content-submit" id="message-content">
					<input class="btn__submit-mess" type="button" value="Send"
						onclick="sendMessage()" id="btn-send">
				</div>
			</div>
		</div>
	</div>
	<div class="modal">
		<div class="GroupMess-main">
			<div class="GroupMess-create">
				<input type="text" placeholder="Tên group" name="NameGroup"
					id="nameGroup">
				<div class="btn__Group-create">
					<input type="button" value="Đăng ký" class="btn-addgroup"
						onclick="createGroup()"> <input type="reset" value="Reset"
						class="btn-addgroup">
				</div>
			</div>
			<div class="GroupMess-member">
				<div class="GroupMess-close">
					<i class="icon-close fas fa-window-close"></i>
				</div>
				<div class="Groupmember-content">
					<span class="GroupMess__header">Thêm thành viên</span>
					<div class="GroupMess-search">
						<input type="text" name="searchMember" id="searchGroup"> <input
							type="button" name="btn__search-member" value="Tìm kiếm"
							class="btn-timkiem" onclick="searchGroup()">
					</div>
				</div>
				<div class="Groupmember-List">
					<ul class="list__friend-group">



					</ul>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
    $("#message-content").keyup(function(event) {
        if (event.keyCode === 13) {
            sendMessage();
        }
    });

    	//show account user

        // show modal group
    const groupOpen = document.querySelector('.account__header-friend')
    const groupInfo = document.querySelector('.modal')
    const groupClose = document.querySelector('.icon-close')
    function ShowModalGroup(){
        groupInfo.classList.add('GroupMess');
    }
    function HideModalGroup(){
        groupInfo.classList.remove('GroupMess');
    }
    groupOpen.addEventListener('click',ShowModalGroup)
    groupClose.addEventListener('click',HideModalGroup)
        //show delete
    function ShowDelete(element,name){   
        item = document.getElementsByClassName("item__friend-delete");    
        for(let i of item)
            if(!$(element).children('.item__friend-delete')[0].isSameNode(i))
                i.classList.remove(name);
        $(element).children('.item__friend-delete')[0].classList.toggle(name)
    }
</script>
</body>
<!-- INFO-MYSELF -->
<div class="info">
	<div class="info-container">
		<div class="info-header">
			<div class="close-icon">
				<i class="info-close-icon fas fa-times "></i>
			</div>
			<div class="person">
				<img src="./img/person.png" alt="" class="icon-person">
			</div>
			<div class="text-info">Thông tin cá nhân</div>
		</div>
		<div class="info-body">
			<form action="C_updateUser" method="post" accept-charset="utf-8">
				<input type="hidden" name="idUpdate" value="<%=user.getUser_ID()%>"> 
				<div class="info-row">
					<label for="" class="info-label"> Họ và tên </label> <input
						type="text" name="nameUpdate" class="info-input" value="<%=user.getFullname()%>">
				</div>
				<div class="info-row">
					<label for="" class="info-label"> Tuổi </label> 
					<input type="text" name="ageUpdate" class="info-input" value="<%=user.getAge()%>">
				</div>
				<div class="info-row">
						<label for="Gender" class="register-label"> Giới tính </label> <br>
						<input type="radio" name="genderUpdate" value="nam" <%if(user.isGender()) out.print("checked"); %>>Nam <br>
		                <input type="radio" name="genderUpdate" value="nu" <%if(!user.isGender()) out.print("checked"); %>>Nữ 
				</div>
				<div class="info-row">
					<label for="" class="info-label"> Mật khẩu </label> 
					<input onchange="checkPass();" id="passUpdate" type="password" class="info-input" name="passwordUpdate">
				</div>
				<div class="info-row">
					<label for="" class="info-label"> Nhập lại mật khẩu </label> 
					<input onchange="checkPass();" id="passUpdateConfirm" type="password" class="info-input">
				</div>
				<div class="btn-capnhat">
					<input type="submit" class="btn-update" id="update-btn" value="Cập nhật thông tin">
				</div>
			</form>
		</div>
	</div>
</div>
<!-- CALLING TO -->
<div class="call-to" id="call-to">
	<div class="call-container">
		<div class="img-call">
			<img alt="" src="./img/iconcall.png" class="person-img">
		</div>
		<div class="text-call" id="text-call"></div>
		<div class="icon-call">
			<i class="fas fa-phone-square icon-phone phone" id="batmay"></i> <i
				class="fas fa-phone-square icon-phone unphone" onclick="closeCall();"></i>
		</div>
	</div>
</div>



<!-- INFO-FRIENDS -->
<div class="info-friend">
	<div class="info-container">
		<div class="info-header">
			<div class="close-icon">
				<i class="info-close-icons fas fa-times "></i>
			</div>
			<div class="person">
				<img src="./img/person.png" alt="" class="icon-person">
			</div>
			<div class="text-info">Thông tin cá nhân</div>
		</div>
		<div class="info-body">
			<div class="info-row">
				<label for="" class="info-label"> Họ và tên </label> <input
					type="text" class="info-input" id="nameFriend"
					readonly>
			</div>
			<div class="info-row">
				<label for="" class="info-label"> Tuổi </label> <input type="text"
					class="info-input" id=ageFriend readonly>
			</div>
			<div class="info-row">
				<label for="" class="info-label"> Giới tính </label> <input
					type="text" class="info-input" id="genderFriend" readonly>
			</div>
		</div>
	</div>
	<div></div>
	<script type="text/javascript">
	function checkPass(){
		if(document.getElementById("passUpdate").value.length >=6 && document.getElementById("passUpdate").value == document.getElementById("passUpdateConfirm").value){
			document.getElementById("update-btn").disabled = false;
		}else{
			document.getElementById("update-btn").disabled = true;
		}
	};
const infoopen=document.querySelector('.account__header-img');
const infoclose=document.querySelector('.info-close-icon');
const info=document.querySelector('.info');
function ShowInfo(){
info.classList.add('open');
}
function HideInfo(){
info.classList.remove('open');
}
infoopen.addEventListener('click',ShowInfo);
infoclose.addEventListener('click',HideInfo);
</script>
	<script type="text/javascript">
const infofopen=document.querySelector('.icon-info');
const infofclose=document.querySelector('.info-close-icons');
const infof=document.querySelector('.info-friend');
function ShowInfoFriend(){
infof.classList.add('open');
}
function HideInfoFriend(){
infof.classList.remove('open');
}
infofopen.addEventListener('click',ShowInfoFriend);
infofclose.addEventListener('click',HideInfoFriend);
</script>
</html>
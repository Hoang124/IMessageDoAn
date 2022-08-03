<%@page import="Model.BEAN.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
User user = (User) request.getSession().getAttribute("User");
if (user != null)
	response.sendRedirect("messaging.jsp");
%>
<!DOCTYPE html>
<html lang="en">
<script type="text/javascript">

</script>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/base.css">
<link rel="stylesheet"
	href="./font/fontawesome-free-5.15.3-web/css/all.css">
<link rel="stylesheet" href="./font/themify-icons/themify-icons.css">
<title>Document</title>
</head>
<body>
	<div class="IMessApp">.</div>
	<div class="formLogin">
		<div class="grid">
			<header class="formLogin__header"> </header>
			<div class="formLogin__body">
				<div class="formLogin__body-content">
					<h1 class="formLogin__content-sologan">
						Tụ họp <br> mọi lúc, mọi nơi
					</h1>
					<span class="formLogin__content-description"> Với Messenger,
						việc kết nối với những người mình yêu mến thật đơn giản và thú vị.
					</span>
					<div class="formLogin__content-form">
						<form class="formLogin__form-group" action="C_checklogin"
							method="post">
							<input class="formLogin__group-input" type="text"
								placeholder="Email hoặc số điện thoại" name="username">
							<input class="formLogin__group-input" type="password"
								placeholder="Password" name="password">
							<div class="formLogin__group-btn">
								<button class="formLogin__group-btnLogin">Đăng nhập</button>



								<a class="formLogin__group-register js-register-open" href="#">Đăng
									ký nếu chưa có tài khoản</a>
							</div>
						</form>

					</div>
				</div>
				<div class="formLogin__body-image">
					<img class="formLogin__body-image-main"
						src="./img/fb-messenger.jpg" alt="">
				</div>
			</div>
			<footer class="formLogin__footer"> </footer>
		</div>
	</div>
	<!-- REGISTER -->
	<div class="register js-register">
		<div class="register-container ">
			<div class="register-header">Đăng kí</div>
			<div class="register-close js-register-close">
				<i class="register-close-icon ti-close js-register-close"></i>
			</div>
			<div class="register-body">
				<form action="C_register" method="post" accept-charset="utf-8">
					<div class="register-row">
						<label for="" class="register-label"> Họ và tên </label> 
						<input type="text" name="Fullname" placeholder="Họ và tên" class="register-input">
					</div>
					<div class="register-row">
						<label for="" class="register-label"> Tuổi </label> 
						<input type="text" name="Age" placeholder="Tuổi" class="register-input">
					</div>
					<div class="register-row">
						<label for="Gender" class="register-label"> Giới tính </label> <br>
						<input type="radio" name="Gender" value="nam">Nam <br>
	                    <input type="radio" name="Gender" value="nu" >Nữ 
					</div>
					<div class="register-row">
						<label for="" class="register-label"> Username </label> 
						<input type="text" name="username" placeholder="Username" class="register-input">
					</div>
					<div class="register-row">
						<label for="" class="register-label"> Mật khẩu </label> 
						<input onchange="checkPass();" type="password" id="passInput" name="password" placeholder="Mật khẩu" class="register-input">
					</div>
					<div class="register-row">
						<label for="" class="register-label"> Nhập lại mật khẩu </label> 
						<input onchange="checkPass();" type="password" id="passConfirm" placeholder="Nhập lại mật khẩu" class="register-input">
					</div>
					<input type="submit" id="register-btn" value="Đăng ký">
					
				</form>
			</div>
		</div>
	</div>
	<!-- Đăng kí -->
	<script>
	function checkPass(){
		if(document.getElementById("passInput").value.length >=6 && document.getElementById("passInput").value == document.getElementById("passConfirm").value){
			document.getElementById("register-btn").disabled = false;
		}else{
			document.getElementById("register-btn").disabled = true;
		}
	};
		const regsopen = document.querySelector('.js-register-open')
		const regsclose = document.querySelector('.js-register-close')
		const register = document.querySelector('.js-register')
		function Showregister() {
			register.classList.add('open')
		}
		function Hideregister() {
			register.classList.remove('open')
		}
		regsopen.addEventListener('click', Showregister)
		regsclose.addEventListener('click', Hideregister)
	</script>
</body>
</html>
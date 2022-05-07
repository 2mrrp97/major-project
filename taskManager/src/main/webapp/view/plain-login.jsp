<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>StudentManagement | Login </title>
<style>
        @import url('https://fonts.googleapis.com/css?family=Raleway:400,700');

		* {
			box-sizing: border-box;
			margin: 0;
			padding: 0;	
			font-family: Raleway, sans-serif;
		}
		
		body {
			background: linear-gradient(90deg, #dae6fa, #dae6fa);		
			background: linear-gradient(to bottom, #05245a, #0446bb, #1a4999);
		}
		
		.container {
			display: flex;
			align-items: center;
			justify-content: center;
			min-height: 100vh;
			overflow: hidden;
		}
		
		.screen {		
			background: linear-gradient(90deg, #e9edf3, #749ee7);	
			position: relative;	
			height: 600px;
			width: 360px;	
			box-shadow: 5px 20px 50px #000000d4;
		}
		
		.screen_content {
			z-index: 1;
			position: relative;	
			height: 100%;
		}
		
		.screen_background {		
			position: absolute;
			top: 0;
			left: 0;
			right: 0;
			bottom: 0;
			z-index: 0;
			-webkit-clip-path: inset(0 0 0 0);
			clip-path: inset(0 0 0 0);	
		}
		
		.screen_background_shape {
			transform: rotate(45deg);
			position: absolute;
		}
		
		.screen_background_shape1 {
			height: 520px;
			width: 520px;
			background: #FFF;	
			top: -50px;
			right: 120px;	
			border-radius: 0 72px 0 0;
		}
		
		.screen_background_shape2 {
			height: 220px;
			width: 220px;
			background: #0446bb;	
			top: -172px;
			right: 0;	
			border-radius: 32px;
		}
		
		.screen_background_shape3 {
			height: 540px;
			width: 190px;
			background: linear-gradient(270deg, #6c98e5, #0446bb);
			top: -24px;
			right: 0;	
			border-radius: 32px;
		}
		
		.screen_background_shape4 {
			height: 400px;
			width: 200px;
			background: #ffffff;	
			top: 420px;
			right: 50px;	
			border-radius: 60px;
		}
		
		.login {
			width: 320px;
			padding: 30px;
			padding-top: 156px;
		}
		
		.login_field {
			padding: 20px 0px;	
			position: relative;	
		}
		
		.login_input {
			border: none;
			border-bottom: 2px solid #D1D1D4;
			background: none;
			padding: 10px;
			padding-left: 24px;
			font-weight: 700;
			width: 75%;
			transition: .2s;
		}
		
		.login_input:active,
		.login_input:focus,
		.login_input:hover {
			outline: none;
			border-bottom-color: #0446bb;
		}
		
		.login_submit {
			background: #fff;
			font-size: 14px;
			margin-top: 30px;
			padding: 16px 20px;
			border-radius: 26px;
			border: 1px solid #5b8bde;
			text-transform: uppercase;
			font-weight: 700;
			display: flex;
			align-items: center;
			width: 100%;
			color: #0446bb;
			box-shadow: 0px 2px 2px #0446bb;
			cursor: pointer;
			transition: .2s;
		}
		
		.login_submit:active,
		.login_submit:focus,
		.login_submit:hover {
			border-color: #0446bb;
			outline: none;
		}
		

</style>
</head>
    <body>    
		<div class="container">
			<div class="screen">
				<div class="screen_content">
					<form:form action = "login/auth" method = "post" class="login">
						<c:if test = "${param.logout != null}">
							<div class="login_field">
								<span style = "color:green; font-weight : bolder" >You have been Logged Out</span>
							</div>
						</c:if>
						<div class="login_field">
							<input type="text" name = "username" class="login_input" placeholder="User name / Email">
						</div>
						<div class="login_field">
							<i class="login_icon fas fa-lock"></i>
							<input type="password" name = "password" class="login_input" placeholder="Password">
						</div>
						<c:if test = "${param.error != null}">
							<div class="login_field">
								<span style = "color:red ; font-weight : bolder" >Invalid Login Credentials</span>
							</div>
						</c:if>
						<button class="button login_submit">
							<span class="button_text">Log In Now</span>
						</button>				
					</form:form>
				</div>
				<div class="screen_background">
					<span class="screen_background_shape screen_background_shape4"></span>
					<span class="screen_background_shape screen_background_shape3"></span>		
					<span class="screen_background_shape screen_background_shape2"></span>
					<span class="screen_background_shape screen_background_shape1"></span>
				</div>		
			</div>
		</div>
</body>
</html>
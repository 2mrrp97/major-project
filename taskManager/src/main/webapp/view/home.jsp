<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
	<title>Portal</title>
</head>
<style>
    body{
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
	font-family: 'Jost', sans-serif;
	background: linear-gradient(to bottom, #05245a, #0446bb, #1a4999);
}
.main{
	width: 350px;
	height: 500px;
	overflow: hidden;
	border-radius: 10px;
	box-shadow: 5px 20px 50px #000000d4;
    background-color: #d9e7ff;
}
#chk{
	display: none;
}
.signup{
	position: relative;
	width:100%;
	height: 100%;
}
label{
	color: #0446bb;
	font-size: 2.3em;
	justify-content: center;
	display: flex;
	margin: 60px;
	font-weight: bold;
	cursor: pointer;
	transition: .5s ease-in-out;
}
input{
	width: 60%;
	height: 20px;
	background: #e0dede;
	justify-content: center;
	display: flex;
	margin: 20px auto;
	padding: 10px;
	border: none;
	outline: none;
	border-radius: 5px;
}
a{
	text-decoration:none
}
button{
	width: 60%;
	height: 40px;
	margin: 10px auto;
	justify-content: center;
	display: block;
	color: #fff;
	background: #0446bb;
	font-size: 1em;
	font-weight: bold;
	margin-top: 20px;
	outline: none;
	border: none;
	border-radius: 5px;
	transition: .2s ease-in;
	cursor: pointer;
	
}
button:hover{
	background: white;
    border: 1px solid #0446bb;
    color: #0446bb;
}
.login{
	height: 460px;
	background: #eee;
	border-radius: 60% / 10%;
	transform: translateY(-180px);
	transition: .8s ease-in-out;
}
.login label{
	color: #0446bb;
	transform: scale(.6);
}


</style>
<body>
	<div class="main">  	
		<input type="checkbox" id="chk" aria-hidden="true">

			<div class="signup">
				<div>
					<label for="chk" aria-hidden="true">Login As</label>
                    <a href = "login"><button>Admin</button></a>
					<a href = "login"><button>Teacher</button></a>
                    <a href = "login"><button>Student</button></a>
				</div>
			</div>

			<div class="login">
				<form>
					<label for="chk" aria-hidden="true">Portal</label>
				</form>
			</div>
	</div>
</body>
</html>
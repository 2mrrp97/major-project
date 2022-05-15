<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>add tchr</title>
<link rel="stylesheet" href="css/styles.css">
<style>
	 body{
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
    flex-direction: column;
	align-items: center;
	min-height: 100vh;
	font-family: 'Jost', sans-serif;
	background: linear-gradient(to bottom, #05245a, #0446bb, #1a4999);
	}
	h1 {
	    text-shadow: 0 1px 0 rgba(255, 255, 255, .7), 0px 2px 0 rgba(0, 0, 0, .5);
	    text-transform: uppercase;
	    text-align: center;
	    color: #fff;
	    margin: 0 0 30px 0;
	    letter-spacing: 4px;
	    font: normal 26px/1 Verdana, Helvetica;
	    position: relative;
	}
	
	h1:after, 
	h1:before {
	    background-color: #777;
	    content: "";
	    height: 1px;
	    position: absolute;
	    top: 15px;
	    width: 120px;   
	}
	
	h1:after {      
	    right: 0;
	}
	
	h1:before {
	    background-image: linear-gradient(right, #777, #fff);
	    left: 0;
	}
	
	.login {
	  width: 400px;
	  margin: 16px auto;
	  font-size: 16px;
	}
	.login input , .login textarea {
	  box-sizing: border-box;
	  display: block;
	  width: 100%;
	  border-width: 1px;
	  border-style: solid;
	  padding: 16px;
	  outline: 0;
	  font-family: inherit;
	  font-size: 0.95em;
	  margin-bottom: 20px;
	}
	
	.login button[type="submit"] {
	  background: #28d;
	  border-color: transparent;
	  color: #fff;
	  cursor: pointer;
	  width: 100%;
	  padding: 9px;
	  font-size: 20px;
	  margin-top: 15px;
	}
	
	.login button[type="submit"]:hover {
	  background: #17c;
	}
	
	.login button[type="submit"]:focus {
	  border-color: #05a;
	}
</style>
</head>
<body>
<h1>add teacher form</h1>
<form:form action = "logout" method  = "post">
	<button type = "submit">logout</button>
</form:form>

<div class = "login">
	<form:form method = "post" action = "addteacher" modelAttribute = "teacher">
		<form:input path = "name" type = "text" placeholder = "name"/>
		<form:input path = "emailId" type = "email"  placeholder = "email id"/>
		<form:input path = "dob" type = "date"/>
		<button type = "submit">Add</button>
	</form:form>
</div>

</body>
</html>
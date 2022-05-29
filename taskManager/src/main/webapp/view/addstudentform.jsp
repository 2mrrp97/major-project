<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>

    </head>
<style>

body {
    background: rgb(234, 238, 238);
    color: #888da8;
    letter-spacing: 0.2px;
    font-family: 'Roboto', sans-serif;
    padding: 0;
    margin: 0;
}
        #left-menu {
    position: fixed;
    top: 0;
    left: 0;
    width: 280px;
    background: linear-gradient(to bottom, #05245a, #0446bb, #1a4999);
    overflow-y: auto;
    height: 100vh;
    border-right: 1px solid #e6ecf5;
    margin-top: 60px;
    overflow-x: hidden;
    z-index: 2;
}

#logo {
    position: fixed;
    top: 0;
    z-index: 2;
    left: 0;
    background-color: #464e62;
    border-color: #464e62;
    height: 60px;
    width: 265px;
    font-size: 30px;
    line-height: 2em;
    border-right: 1px solid #e6ecf5;
    z-index: 4;
    color: #fff;
    padding-left: 15px;
    overflow: hidden;
    text-align: center;
}
ul{
    padding: 0;
}

li{
    list-style: none;
    background-color: white;
    margin-top: 15px;
    padding: 10px;
    text-align: center;
    font-weight: 600 !important;
    margin-right: 10px;
    margin-left: 10px;
    cursor: pointer;
    
}
a{
    text-decoration: none;
    color: #05245a;
}

#main-content {
    min-height: calc(100vh - 60px);
    clear: both;
}

#page-container {
    padding-left: 300px;
    padding-top: 80px;
    padding-right: 25px;
}

#page-container.small-left-menu,
#header .header-left.small-left-menu {
    padding-left: 80px;
}

h1{
    color: #052661;
    text-align: center;
    padding-bottom: 20px;
}

.login {
  width: 400px;
  margin: 16px auto;
  font-size: 16px;
  box-shadow: 2px 2px 9px 3px #464e62;
  padding: 20px;
}
.login input {
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
  margin-top: 8px;
}

.login button[type="submit"] {
  background: #043b9c;
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

.login label{
    font-size: 20px;
    color: #052661;
    font-weight: 600;
}
</style>
    <body>
        <%@ include file="partials/menu.jsp" %>  


        <div id="main-content">
            <div id="page-container">
                <h1>Add New Student</h1>
                <div class="login">
                    <form:form method = "post" action = "addstudent?cid=${courseId}" modelAttribute = "student" id = "addstudent">
                    	<label>Name</label>
						<form:input path = "name" type = "text" placeholder = "Name"/>
						<label>Email</label>
						<form:input path = "emailId" type = "email"  placeholder = "Email id"/>
						<label>Date of Birth</label>
						<form:input path = "dob" type = "date"/>
						<button type = "submit">Add</button>
					</form:form>
                </div>
            </div>
        </div>
    </body>
</html>
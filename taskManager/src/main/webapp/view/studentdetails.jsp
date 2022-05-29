<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib  uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>STUDENT DETAILS </title>
<link rel="stylesheet" href="css/styles2.css" type ="stylesheet/css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
    * {
    box-sizing: border-box;
}

body {
    background: rgb(234, 238, 238);
    color: #888da8;
    letter-spacing: 0.2px;
    font-family: 'Roboto', sans-serif;
    padding: 0;
    margin: 0;
}
h1{
    color: #052661;
    text-align: center;
    padding-bottom: 20px;
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
.dashboardBtn {
    text-decoration: none;
    color: #05245a;
}
input {
    border-color: #d8e0e5;
    border-radius: 2px !important;
    box-shadow: none !important;
    font-weight: 300 !important;
}

.form-control:disabled,
.form-control[readonly] {
    background-color: #f6f7fb;
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
    width: 280px;
    font-size: 30px;
    line-height: 2em;
    border-right: 1px solid #e6ecf5;
    z-index: 4;
    color: #fff;
    padding-left: 15px;
    overflow: hidden;
    text-align: center;
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

.card {
    border: 1px solid #e6ecf5;
    margin-bottom: 1em;
    padding-bottom: 20px;
}

.card .title {
    padding: 15px 20px;
    border-bottom: 1px solid #e6ecf5;
    margin-bottom: 10px;
    color: #fff;
    font-size: 18px;
    background: #1a4999;
    text-align: center;
}

.courseDetailsCard {
	border: 1px solid #e6ecf5;
	height : 250px;
    width : 200px;
    display : inline-block;
    position : relative;
    padding : 20px;
}

.courseDetailsCard:hover {
	box-shadow: 1px 1px 10px black;
}
.courseDetailsCard span {
display : block ;
color : black;
}

.courseDetailsCard a {
	position : absolute ;
	bottom : 0px;
	left : 0px;
	display : block;
	width : 100%;
	background : #1a4999;
	text-align : center;
	padding : 10px;
	color : white;
}
</style>
</head>
<body>

<div id="logo">
        <span class="big-logo"><a href = "${pageContext.request.contextPath}/dashboard">DASHBOARD</a></span>
    </div>
    <div id="left-menu">

        <ul>
			<security:authentication property = "principal.authorities"></security:authentication>
            <security:authorize access = "hasRole('ADMIN')">
            	<li><a href = "${pageContext.request.contextPath}/addteacher" class = "dashboardBtn">Add new teacher</a></li>
            	<li><a href = "${pageContext.request.contextPath}/addcourse" class = "dashboardBtn">Add new course</a></li>
            </security:authorize>
            
            
            <li>
            <form:form action = "${pageContext.request.contextPath}/logout" method  = "post">
				<button type = "submit" style = "border : none ; outline : none; background : none">logout</button>
			</form:form>
            </li>
        </ul>
    </div>
    
    
    <div id="main-content">
        	
       <div id = "page-container">
       	
	${s}
			course : ${s.course}
  		</div>
    </div>
    
        
        
    
</body>

</html>
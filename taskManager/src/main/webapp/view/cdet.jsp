<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib  uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>c det </title>
<link rel="stylesheet" href="css/styles.css">
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
            
            <security:authorize access = "hasRole('TEACHER')">
            	<li><a href = "${pageContext.request.contextPath}/addstudent" class = "dashboardBtn">Add new Student</a></li>
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
       	
		<h1>COURSE NAME</h1>
		<h3 class = "text-center"> Start : ${c.startDate}</h3>
		<h3 class = "text-center"> End : ${c.endDate}</h3>
		
 
		<div class = "col-md-5 d-inline-block">
		 	<h5>Assigned teachers : </h5>
			<c:forEach var = "t" items = "${c.teachers}">
				<div>${t.name}(${t.emailId})<a href = "/rmvt/tid/${t.id}/cid/${c.courseId}">Remove</a></div> 
			</c:forEach>

			<h5> uploaded class materials : </h5>
	 		${c.assignments} <br>
		</div>
		
		
		<div class = "col-md-5 d-inline-block">
		
		 	<h5>Available Teachers</h5>
			<form:form method = "post" action = "/coursedetails/${c.courseId}">
				<input type = "text" value = "${c.courseId}" name = "courseId" hidden>
				 <c:forEach var = "t" items = "${avlt}">
					${t.name}(${t.emailId})<input type = "checkbox" value = "${t.id}" name = "addTeachersIds[]"><br>
				</c:forEach>
				<button type = "submit">Add</button>
			</form:form>
       	
		</div>
			
			<div>
				<h5> enrolled ppl : </h5>
	 				${c.enrolledStudents} <br>
			</div>
  		</div>
    </div>
    
        
        
    
</body>

</html>
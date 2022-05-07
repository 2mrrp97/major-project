<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>dashbiard</title>
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
<h1>Admin dashboard</h1>
<form:form action = "logout" method  = "post">
	<button type = "submit">logout</button>
</form:form>
<a href = "addteacher">Add new teacher</a>
<a href = "addcourse">Add new course</a>

<c:forEach var="c" items="${courses}">
    	<div>
    	 start : ${c.startDate}
    	 end : ${c.endDate} 
    	 
    	 <a href = "coursedetails/${c.courseId}">Manage</a>
    	</div>
	</c:forEach>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>c det </title>
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
<h1>c det </h1>
<form:form action = "${pageContext.request.contextPath}/logout" method  = "post">
	<button type = "submit">logout</button>
</form:form>
nmame : c.name <br>
 start : ${c.startDate} <br>
 end : ${c.endDate}<br>
 
 
 assigned teachers : <br>
<c:forEach var = "t" items = "${c.teachers}">
<div>${t}<a href = "/rmvt/tid/${t.id}/cid/${c.courseId}">remv</a></div> 
</c:forEach>

 uploaded class materials : <br>
 ${c.assignments} <br>
 
 
 <h1>Avlt</h1>
<form:form method = "post" action = "/coursedetails/${c.courseId}">
<input type = "text" value = "${c.courseId}" name = "courseId" hidden>
 <c:forEach var = "t" items = "${avlt}">
	${t} <input type = "checkbox" value = "${t.id}" name = "addTeachersIds[]"><br>
	
</c:forEach>
<button type = "submit">Add</button>
</form:form>


</body>

</html>
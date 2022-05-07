<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>add tchr</title>
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
<h1>add teacher form</h1>
<form:form action = "logout" method  = "post">
	<button type = "submit">logout</button>
</form:form>

<form:form method = "post" action = "addteacher" modelAttribute = "teacher">
	<form:input path = "name" type = "text" placeholder = "name"/>
	<form:input path = "emailId" type = "email"  placeholder = "email id"/>
	<form:input path = "dob" type = "date"/>
	<button type = "submit">Add</button>
</form:form>

</body>
</html>
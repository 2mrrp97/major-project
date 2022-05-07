<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>add tchr</title>

</head>
<body>
<h1>add course form</h1>


<form:form action = "${pageContext.request.contextPath}/logout" method  = "post">
	<button type = "submit">logout</button>
</form:form>

<form:form method = "post" action = "addcourse" modelAttribute = "course" id = "addCourse">
	<form:input path = "startDate" type = "date"/>
	<form:input path = "endDate" type = "date"  />
	<form:input path = "description" type = "text"/> <br>
	<c:forEach var="i" items="${teachers}">
    	${i.name}(${i.emailId}) <input type = "checkbox" name = "teachers[]" value = "${i.id}"/> <br>
	</c:forEach>
	<button type = "submit" id = "submit">Add</button>
</form:form>

</body>
<script>
	document.getElementById("submit").addEventListener('click'  , () => {
		const form = document.getElementById('addCourse')
		//form.addEventListener('submit' , (e) => { e.preventDefault() })
		console.log(form)
	})
</script>
</html>
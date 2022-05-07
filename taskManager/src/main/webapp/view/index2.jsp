<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
<h1>Index.jsp</h1>
<form action = "upload" method = "post" enctype = "multipart/form-data">
file: 
	<input type = "file" name = "file">
	<button type = "submit">Upload</button>
</form>

Download : 
<a href = "downloadFile/${fileId}">${fileDownloadUri}</a>
<form:form action = "logout" method  = "post">
	<button type = "submit">logout</button>
</form:form>
</body>

</html>
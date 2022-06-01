<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib  uri="http://www.springframework.org/security/tags" prefix="security" %>

<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
<style>
@import url(https://fonts.googleapis.com/css?family=Roboto+Condensed:300);



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

    .card{
    position: relative;
    padding: 15px;
    background: white;
    box-shadow: 2px 2px 7px 2px;
    margin-top: 20px;
    }
</style>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2">
                    <div id="logo">
                        <span class="big-logo">DASHBOARD</span>
                    </div>
                    <div id="left-menu">
                        <ul>
                
                            <li><form:form method = "post" action = "${pageContext.request.contextPath}/logout">
                            <button type = "submit" >logout</button>
                            </form:form></li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-10 mb-5">
                    <div class="ms-4 container-fluid">
                        <div class="row">
                            <div class="col-md-10">
                                <div class="card">
                                    <h5 class="card-header">Student Information</h5>
                                    <div class="card-body">
                                      <h5 class="card-title"><b> Name : </b>${s.name}</h5>
                                      <p class="card-text">
                                      	<h5>Description of student : </h5>
                                      	<h6><b>Date of Birth : </b> ${s.dob} </h6>
                                      	<h6><b> Enrolled Course : </b> ${s.course.courseName} </h6>
                                      </p>
                           
                                    </div>
                                  </div>
                            </div>
                        </div>
                    </div>
                    <div class="ms-4 container-fluid">
                        <div class="row">
                         
                           	<c:forEach var="c" items="${assmap}">
						    	<div class="card col-md-3" style="width: 18rem; margin-left: 11px;">
                                <div class="card-body">
                                  <h5 class="card-title">Class Material</h5>
                   
                                  <p class="card-text"><b>Description : </b>${c.key.description} </p>
                                  <p class ='card-text'>
                                  	<b>Study material : </b>
                                  <a href="${pageContext.request.contextPath}/view/${c.key.id}" class="card-link" target = "_blank">${c.key.fileName}</a>
                                  </p>
                                  <p class ='card-text'>
                                  	<b>Uploaded Answer : </b>
                                  <c:if test = "${c.value != null}">
                                  	<a href="${pageContext.request.contextPath}/answer/view/${c.value.id}" class="card-link" target = "_blank">${c.value.fileName}</a>
                                  </c:if>
                                  <c:if test = "${c.value == null}">
                                  	<form:form action = "${pageContext.request.contextPath}/answer/upload" method = "post" enctype = "multipart/form-data">
										<input type = "file" name = "file" />
										<input type = "text" name = "assignmentId"  value = "${c.key.id}" hidden/>
										<input type = "text" name = "username"value = "${s.emailId}" hidden/>
										
										<button type = "submit">Upload</button>
									</form:form>
                                  </c:if>
                                  
                                  </p>
                                  
                                  
                                </div>
                            </div>
							</c:forEach>
                        </div>
                    </div>
                </div>
                </div>
            </div>
            
            

        
        
        
    </body>
</html>


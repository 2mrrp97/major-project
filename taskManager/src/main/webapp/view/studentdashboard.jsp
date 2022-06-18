<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib  uri="http://www.springframework.org/security/tags" prefix="security" %>

<html>
    <head>
       
      <link rel="stylesheet" href="css/styles2.css" type ="stylesheet/css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- JavaScript Bundle with Popper -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
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
                        <a href = "${pageContext.request.contextPath}/dashboard" class="big-logo">DASHBOARD</a>
                    </div>
                    <div id="left-menu">
                        <ul>
                
                            <li><form:form method = "post" action = "${pageContext.request.contextPath}/logout">
                            <button type = "submit" class = "btn">Logout</button>
                            </form:form></li>
                            <li><a href = "${pageContext.request.contextPath}/resetpwd">Reset Password</a></li>
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
                   
                                 
                                  <p class ='card-text'>
                                  	<b>Study material : </b>
                                  <a href="${pageContext.request.contextPath}/view/${c.key.id}" class="card-link" target = "_blank">${c.key.fileName}</a>
                                  </p>
                                  <p class ='card-text'>
                                  	<b>Uploaded Answer : </b>
                                  <c:if test = "${c.value != null}">
                                  	<a href="${pageContext.request.contextPath}/answer/view/${c.value.id}" class="card-link" target = "_blank">${c.value.fileName}</a>
                                  	<!-- Modal -->
								<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
								  <div class="modal-dialog" role="document">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h5 class="modal-title" id="exampleModalLabel">Edit Answer</h5>
								        <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close">
								          <span aria-hidden="true"></span>
								        </button>
								      </div>
								      <div class="modal-body">
								       <form:form action = "${pageContext.request.contextPath}/answer/upload?ansId=${c.value.id}" method = "post" enctype = "multipart/form-data"
								       id = "ansEditForm">
											<input type = "file" name = "file" id = "editfile"/>
											<input type = "text" name = "assignmentId"  value = "${c.key.id}" hidden/>
											<input type = "text" name = "username"value = "${s.emailId}" hidden/>
											
											<button type = "submit" class = "btn btn-dark">Submit</button>
										</form:form>
								      </div>
								      <div class="modal-footer">
								        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
								      </div>
								    </div>
								  </div>
								</div>
                                  	
                                  	
                                  	
                                  	
                                  	
                                  	<c:if test = "${c.value.remark != null}">
                                  		<div>
                                  			Remarks : ${c.value.remark.content}
                                  		</div>
                                  	</c:if>
                                  	<c:if test = "${c.value.remark == null}">
	                                  	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
										  Edit
										</button>
                                  		<div>
                                  			<b>Remarks : </b> No remarks added for this answer yet.
                                  		</div>
                                  	</c:if>
                                  </c:if>
                                  <c:if test = "${c.value == null}">
                                  	<form:form action = "${pageContext.request.contextPath}/answer/upload" method = "post" enctype = "multipart/form-data"
                                  	id = "ansUploadForm">
										<input type = "file" name = "file" id = "uploadfile"/>
										<input type = "text" name = "assignmentId"  value = "${c.key.id}" hidden/>
										<input type = "text" name = "username" value = "${s.emailId}" hidden/>
										
										<button type = "submit" class = "btn btn-dark" id = "uploadButton">Upload</button>
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
            
            

        <script>
			var form = document.getElementById('ansUploadForm');
			document.querySelector('#ansUploadForm button').addEventListener('click' , () => {
				form.addEventListener("submit" , (e) => { e.preventDefault() })
				var file = document.getElementById('uploadfile');
				console.log(file.value);
				
		        var allowedExtensions = /(\.pdf|\.docx)$/i;
		        if (!allowedExtensions.exec(file.value)) {
		            alert('Invalid file type. Please upload only .pdf format documents.');
		            file.value = '';
		        }
		        else 
		        	form.submit();
			})
			
			var editForm = document.getElementById('ansEditForm');
			document.querySelector('#ansEditForm button').addEventListener('click' , () => {
				editForm.addEventListener("submit" , (e) => { e.preventDefault() })
				var file = document.getElementById('editfile');
				console.log(file.value);
				
		        var allowedExtensions = /(\.pdf|\.docx)$/i;
		        if (!allowedExtensions.exec(file.value)) {
		            alert('Invalid file type. Please upload only .pdf format documents.');
		            file.value = '';
		        }
		        else 
		        	editForm.submit();
			})
		</script>
    </body>
</html>


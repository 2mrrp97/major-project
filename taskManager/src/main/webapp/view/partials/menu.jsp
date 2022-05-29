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
 </div>

    
    
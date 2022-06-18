<div id="logo">
             <span class="big-logo"><a href = "${pageContext.request.contextPath}/dashboard">DASHBOARD</a></span>
        </div>
        <div id="left-menu">
        <ul>
			
            <security:authorize access = "hasRole('ADMIN')">
            	<li><a href = "${pageContext.request.contextPath}/addteacher" class = "dashboardBtn">Add new teacher</a></li>
            	<li><a href = "${pageContext.request.contextPath}/addcourse" class = "dashboardBtn">Add new course</a></li>
            </security:authorize>

            <li>
            <form:form action = "${pageContext.request.contextPath}/logout" method  = "post">
				<button type = "submit" style = "border : none ; outline : none; background : none; padding : 0; margin: 0">
				<span style = "font-size : 20px">Logout</span></button>
			</form:form>
            </li>
            <li><a href = "${pageContext.request.contextPath}/resetpwd" class = "dashboardBtn">Reset Password</a></li>
        </ul>
    </div>
 </div>

    
    
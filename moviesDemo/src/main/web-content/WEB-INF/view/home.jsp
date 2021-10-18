<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Company home page</title>
</head>
<body>
    <h2>Company Home Page! ohayoo, arigatto gozaimass </h2>
    <hr>
    <p>
    Welcome to the company home page.
    </p>

    <hr>
    <!-- Display username and role -->

    User: <security:authentication property="principal.username" />
    <br><br>
    Role(s): User: <security:authentication property="principal.authorities" />
    <hr>

    <security:authorize access="hasRole('MANAGER')">
    <!-- Add a link to point to /leaders ... this is for the managers -->

    	<p>
    		<a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
    		(Only for Manager peeps)
    	</p>
    </security:authorize>

    <security:authorize access="hasRole('ADMIN')">
    	<!-- Add a link to point to /systems ... this is for the admins -->

    	<p>
            		<a href="${pageContext.request.contextPath}/systems">IT Systems Meeting</a>
            		(Only for Admin)
        </p>
    </security:authorize>

    <hr>


	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout"
			   method="POST">

		<input type="submit" value="Logout" />

	</form:form>

</body>
</html>
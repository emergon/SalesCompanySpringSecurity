<%-- 
    Document   : home
    Created on : Dec 15, 2019, 8:56:41 PM
    Author     : anastasios
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello Spring MVC!</h1>
        <form:form action="${pageContext.request.contextPath}/logout" method="POST">
            <input type="submit" value="Logout">
        </form:form>
        <hr>
        <p>
            User: <security:authentication property="principal.username"/>
            <br/>
            Role(s): <security:authentication property="principal.authorities"/>
        </p>
        <hr>
        <a href="${pageContext.request.contextPath}/customer/list">List of Customers</a>
        <br/>
        <a href="${pageContext.request.contextPath}/admin/register/showForm">Register New User</a>
    </body>
</html>

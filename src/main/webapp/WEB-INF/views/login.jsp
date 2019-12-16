<%-- 
    Document   : login
    Created on : Dec 15, 2019, 9:12:52 PM
    Author     : anastasios
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${param.error != null}">
            <i>Sorry you entered wrong username/password</i>
        </c:if>
        <form:form 
            action="${pageContext.request.contextPath}/authenticate"
            method="POST"><%--must be POST--%>
            <%--name fields username and password--%>
            <p>User name: <input type="text" name="username"/></p>
            <p>Password: <input type="password" name="password"/></p>
            <input type="submit" value="Login"/>
        </form:form>
    </body>
</html>

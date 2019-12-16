<%-- 
    Document   : registration-form
    Created on : Dec 14, 2019, 4:05:45 PM
    Author     : anastasios
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Form</title>
        <style>
            .error{
                color: red;
            }
        </style>
    </head>
    <body>
        <h3>Register User</h3>
        <c:if test="${userExistsError != null}">
            <div class="error">
                ${userExistsError}
            </div>
        </c:if>
        <form:form action="${pageContext.request.contextPath}/admin/register/processRegistration" 
                   method="POST" 
                   modelAttribute="user">
            <p>
                <form:input path="username" placeholder="username"/>
                <form:errors path="username" cssClass="error"/>
            </p>
            <p>
                <form:password path="password" placeholder="password"/>
                <form:errors path="password" cssClass="error"/>
            </p>
            <p>
                <form:input path="fname" placeholder="first name"/>
                <form:errors path="fname" cssClass="error"/>
            </p>
            <p>
                <form:input path="lname" placeholder="last name"/>
                <form:errors path="lname" cssClass="error"/>
            </p>
            <p>
                <form:input path="email" placeholder="email"/>
                <form:errors path="email" cssClass="error"/>
            </p>
            <p>
                <form:select path="roles" items="${roles}" multiple="true" itemValue="rid" itemLabel="rname"/>
                <form:errors path="roles"/>
            </p>
            <input type="submit" value="Register">
        </form:form>
    </body>
</html>

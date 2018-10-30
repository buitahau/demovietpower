<%--
  Created by IntelliJ IDEA.
  User: HauKute
  Date: 8/4/2018
  Time: 10:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Users List</title>
    <base href="/">
    <link href="<c:url value='/static/css/bootstrap.css'/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet" type="text/html"/>
    <script type="text/javascript" src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>
</head>

<body>
<div class="generic-container">
    <div class="panel panel-default">
        <%@include file="/WEB-INF/views/authheader.jsp"%>
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Users </span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Email</th>
                <th>SSO ID</th>
                <th width="100"></th>
                <th width="100"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>${user.ssoId}</td>
                    <td><a href="<c:url value='/edit-user-${user.ssoId}' />" class="btn btn-success

custom-width">edit</a></td>
                    <td><a href="<c:url value='/delete-user-${user.ssoId}' />" class="btn btn-danger

custom-width">delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <sec:authorize access="hasRole('ADMIN')">
    <div class="well">
        <a href="<c:url value='/newuser' />">Add New User</a>
    </div>
    </sec:authorize>
</div>
</body>
</html>
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
    <%--<link href="<c:url value='/static/css/bootstrap.css'/>" rel="stylesheet" type="text/css"/>--%>
    <%--<link href="<c:url value='/static/css/app.css' />" rel="stylesheet" type="text/html"/>--%>
    <%--<script type="text/javascript" src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>--%>
</head>

<body>

<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="<c:url value="/"/>">Home</a>
    </li>
    <li class="breadcrumb-item active">List User</li>
</ol>

<c:if test="${not empty messsage}">
    <div class="alert alert-danger">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>

        ${messsage}
    </div>
</c:if>

<div class="row">
    <div class="col-12">
        <div class="card mb-3">
            <div class="card-header">
                <i class="fas fa-chart-area"></i> List User

                <sec:authorize access="hasRole('SUPER_ADMIN')">
                    <a class="btn btn-info btn-sm float-right" href="<c:url value="/admin/user/add"/>">Add New User</a>
                </sec:authorize>

                <sec:authorize access="hasRole('ADMIN')">
                    <a class="btn btn-info btn-sm float-right" href="<c:url value="/admin/user/add"/>">Add New User</a>
                </sec:authorize>
            </div>

            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>UserName</th>
                            <th>Role</th>
                            <th>Company</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td>${user.userName}</td>
                                <td>${user.role.roleName}</td>
                                <td>
                                    <c:if test="${user.company != null && user.company.companyId != null && user.company.companyId > 0}">
                                        ${user.company.name}
                                    </c:if>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${isSuperAdmin}">
                                            <a href="<c:url value='/admin/user/edit/${user.userId}'/>" class="btn btn-success">Edit</a>
                                        </c:when>

                                        <c:otherwise>
                                            <c:if test="${user.role.roleName eq 'SHOP' || user.role.roleName eq 'OPERATOR' || user.role.roleName eq 'MAINTENANCE'}">
                                                <a href="<c:url value='/admin/user/edit/${user.userId}'/>" class="btn btn-success">Edit</a>
                                            </c:if>
                                        </c:otherwise>
                                    </c:choose>
                                </td>

                                <td>
                                    <c:choose>
                                        <c:when test="${isSuperAdmin}">
                                            <a href="<c:url value='/admin/user/delete/${user.userId}'/>" class="btn btn-danger">Delete</a>
                                        </c:when>
                                        <c:otherwise>
                                            <c:if test="${user.role.roleName eq 'SHOP' || user.role.roleName eq 'OPERATOR' || user.role.roleName eq 'MAINTENANCE'}">
                                                <a href="<c:url value='/admin/user/delete/${user.userId}'/>" class="btn btn-danger">Delete</a>
                                            </c:if>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
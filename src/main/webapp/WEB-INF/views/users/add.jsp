<%--
  Created by IntelliJ IDEA.
  User: HauKute
  Date: 8/4/2018
  Time: 10:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Edit User</title>
</head>

<body>
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="<c:url value="/"/>">Home</a>
    </li>

    <li class="breadcrumb-item">
        <a href="<c:url value="/admin/user/list.html"/>">List User</a>
    </li>

    <li class="breadcrumb-item active">
        <c:choose>
            <c:when test="${user.userId != null && user.userId  > 0}">Edit</c:when>
            <c:otherwise>Add</c:otherwise>
        </c:choose>
        User
    </li>
</ol>

<c:url value="/admin/user/insert-or-update" var="insertOrUpdateURL" />
<form:form method="POST" modelAttribute="user" class="form-horizontal" id="editForm" action="${insertOrUpdateURL}">
    <div class="row">
        <div class="col-lg-8">
            <div class="card mb-3">
                <div class="card-header">
                    <i class="fas fa-chart-area"></i>
                    <c:choose>
                        <c:when test="${formulaModel.formula.formulaId != null && formulaModel.formula.formulaId  > 0}">Edit</c:when>
                        <c:otherwise>Add</c:otherwise>
                    </c:choose>
                    User
                </div>

                <div class="card-body">
                    <div class="row">
                        <div class="col-12">
                            <div class="form-group">
                                <label for="userName">userName</label>
                                <form:input type="text" path="userName" id="userName" class="form-control" placeholder="Enter username"/>
                            </div>
                        </div>

                        <div class="col-12">
                            <div class="form-group">
                                <label for="password">password</label>
                                <form:input type="text" path="password" id="password" class="form-control" placeholder="Enter password"/>
                            </div>
                        </div>

                        <div class="col-12">
                            <div class="form-group">
                                <label for="machineId">Role</label>
                                <form:select path="role.roleId" cssClass="form-control" id="roleId">
                                    <form:option value="-1" label="Select Role"/>
                                    <c:forEach items="${roles}" var="role">
                                        <form:option value="${role.roleId}" label="${role.roleId} - ${role.roleName}"/>
                                    </c:forEach>
                                </form:select>
                            </div>
                        </div>

                        <div class="col-12">
                            <div class="form-group">
                                <label for="machineId">machine</label>
                                <form:select path="machine.machineId" cssClass="form-control" id="machineId">
                                    <form:option value="-1" label="Select Machine"/>
                                    <c:forEach items="${listMachine}" var="machine">
                                        <form:option value="${machine.machineId}" label="${machine.code} - ${machine.name}"/>
                                    </c:forEach>
                                </form:select>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card-footer small text-muted">
                    <div class="row">
                        <button class="btn btn-success mr-2" onclick="savingUser();">
                            <c:choose>
                                <c:when test="${user.userId != null && user.userId  > 0}">Update</c:when>
                                <c:otherwise>Save</c:otherwise>
                            </c:choose>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <form:input type="hidden" path="userId" id="userId" class="form-control"/>
</form:form>
</div>
<script>
    function savingUser(){
        $('#editForm').submit();
    }
</script>
</body>
</html>

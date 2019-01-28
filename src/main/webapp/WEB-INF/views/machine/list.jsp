<%--
  Created by IntelliJ IDEA.
  User: HauKute
  Date: 12/20/2018
  Time: 9:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Machine</title>
    <meta charset="utf-8">
</head>

<body>
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="<c:url value="/"/>">Home</a>
    </li>
    <li class="breadcrumb-item active">List Machine</li>
</ol>

<div class="row">
    <div class="col-12">
        <div class="card mb-3">
            <div class="card-header">
                <i class="fas fa-chart-area"></i> List Machine
                <a class="btn btn-info btn-sm float-right" href="<c:url value="/admin/machine/add"/>">Add Machine</a>
            </div>

            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Code</th>
                            <th>Name</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${listMachines}" var="machine">
                            <tr>
                                <td>${machine.code}</td>
                                <td>${machine.name}</td>
                                <td>
                                    <a href="<c:url value='/admin/machine/${machine.machineId}/colour' />" class="btn btn-success custom-width">Colour</a>
                                    <a href="<c:url value='/admin/machine/${machine.machineId}/formula' />" class="btn btn-primary custom-width">Formula</a>
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

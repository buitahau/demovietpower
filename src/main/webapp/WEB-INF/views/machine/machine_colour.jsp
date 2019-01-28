<%--
  Created by IntelliJ IDEA.
  User: HauKute
  Date: 12/20/2018
  Time: 9:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Machine colour</title>
    <meta charset="utf-8">
</head>
<body>
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="<c:url value="/"/>">Home</a>
    </li>
    <li class="breadcrumb-item">
        <a href="<c:url value="/admin/machine/list"/>">List Machine</a>
    </li>
    <li class="breadcrumb-item active">Machine Colour</li>
</ol>
<div class="row">
    <div class="col-12">
        <div class="card mb-3">
            <div class="card-header">
                <i class="fas fa-chart-area"></i>
                Machine Colour
            </div>

            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Colour Code</th>
                            <th>Colour Name</th>
                            <th>Quantity</th>
                            <th width="100"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${machineColourants}" var="c">
                            <tr>
                                <td>${c.colourant.colourantCode}</td>
                                <td>${c.colourant.colourantName}</td>
                                <td>${c.quantity}</td>
                                <td><a href="<c:url value='/admin/machine/colour/detail/${c.machineColourantId}' />" class="btn btn-success custom-width">Detail</a></td>
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

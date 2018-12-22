<%--
  Created by IntelliJ IDEA.
  User: HauKute
  Date: 12/21/2018
  Time: 6:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/static/css/bootstrap.min.css" rel="stylesheet" type="text/css" media="all">
    <script type="text/javascript" src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row content mt-5">
        <div class="col-sm-2">
            <a href="<c:url value='/machine/${machineId}/colour'/>">Back</a>
        </div>
        <div class="col-sm-8">
            <h4><small>Machine Colour Detail</small></h4>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Colour Code</th>
                    <th>Colour Name</th>
                    <th>Action</th>
                    <th>Date</th>
                    <th>Quantity</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${logs}" var="l">
                    <tr>
                        <td>${l.machineColourant.colourant.colourantCode}</td>
                        <td>${l.machineColourant.colourant.colourantName}</td>
                        <td>${l.action}</td>
                        <td>${l.createdDate}</td>
                        <td>${l.quantity}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>

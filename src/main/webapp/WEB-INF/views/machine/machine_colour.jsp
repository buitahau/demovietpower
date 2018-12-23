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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/static/css/bootstrap.min.css" rel="stylesheet" type="text/css" media="all">
    <script type="text/javascript" src="<c:url value="/demoson/static/js/jquery-3.3.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/demoson/static/js/bootstrap.min.js"/>"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row content mt-5">
        <div class="col-sm-2">
            <a href="<c:url value='/'/>">Back</a>
        </div>
        <div class="col-sm-8">
            <h4><small>Machine Colour</small></h4>
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
                        <td><a href="<c:url value='/machine/colour/detail/${c.machineColourantId}' />" class="btn btn-success custom-width">Detail</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>

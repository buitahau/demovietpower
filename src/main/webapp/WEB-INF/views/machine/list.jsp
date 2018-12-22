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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/static/css/bootstrap.min.css" rel="stylesheet" type="text/css" media="all">
    <script type="text/javascript" src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row content mt-5">
        <div class="col-sm-2">
        </div>
        <div class="col-sm-8">
            <h4><small>List Machine</small></h4>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Code</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listMachines}" var="machine">
                        <tr>
                            <td>${machine.name}</td>
                            <td>${machine.code}</td>
                            <td><a href="<c:url value='/machine/${machine.machineId}/colour' />" class="btn btn-success custom-width">colour</a>
                            <a href="<c:url value='/machine/${machine.machineId}/formula' />" class="btn btn-primary custom-width">formula</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>

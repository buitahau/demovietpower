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
</head>
<body>
<div class="container">
     <table class="table table-hover">
        <thead>
        <tr>
            <th>Name</th>
            <th>Code</th>
            <th width="100"></th>
            <th width="100"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listMachines}" var="machine">
            <tr>
                <td>${machine.name}</td>
                <td>${machine.code}</td>
                <td><a href="<c:url value='/machine/${machine.machineId}/colour' />" class="btn btn-success custom-width">colour</a></td>
                <td><a href="<c:url value='/machine/${machine.machineId}/formula' />" class="btn btn-danger custom-width">formula</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

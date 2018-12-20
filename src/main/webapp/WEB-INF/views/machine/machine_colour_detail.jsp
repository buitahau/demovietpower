<%--
  Created by IntelliJ IDEA.
  User: HauKute
  Date: 12/21/2018
  Time: 6:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Colour Name</th>
            <th>Action</th>
            <th>Created Date</th>
            <th>Quantity</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${logs}" var="l">
            <tr>
                <td>${l.machineColourant.colourant.colourantCode}</td>
                <td>${l.action}</td>
                <td>${l.createdDate}</td>
                <td>${l.quantity}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

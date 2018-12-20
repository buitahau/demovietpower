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
</head>
<body>
<div class="container">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Colour Name</th>
            <th>Quantity</th>
            <th width="100"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${machineColourants}" var="c">
            <tr>
                <td>${c.colourant.colourantName}</td>
                <td>${c.quantity}</td>
                <td><a href="<c:url value='/machine/colour/detail/${c.machineColourantLogId}' />" class="btn btn-success custom-width">Detail</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

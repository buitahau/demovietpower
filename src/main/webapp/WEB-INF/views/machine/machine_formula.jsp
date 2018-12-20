<%--
  Created by IntelliJ IDEA.
  User: HauKute
  Date: 12/20/2018
  Time: 9:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Detail Formula</title>
</head>
<body>
<div class="container">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Formula</th>
            <th>Product</th>
            <th>Base</th>
            <th>Created Date</th>
            <th>Quantity</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${machinefpbs}" var="f">
            <tr>
                <td>${f.formulaProductBase.formula.formulaCode}</td>
                <td>${f.formulaProductBase.productBase.product.productCode}</td>
                <td>${f.formulaProductBase.productBase.base.baseCode}</td>
                <td>${f.createdDate}</td>
                <td>${f.quantity}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

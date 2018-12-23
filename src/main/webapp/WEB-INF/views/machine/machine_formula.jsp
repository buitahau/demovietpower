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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/static/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" media="all">
    <script type="text/javascript" src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row content mt-5">
        <div class="col-sm-2">
            <a href="<c:url value='/'/>">Back</a>
        </div>
        <div class="col-sm-8">
            <h4><small>Detail Formula</small></h4>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Formula</th>
                        <th>Product</th>
                        <th>Base</th>
                        <th>Date</th>
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
    </div>
</div>
</body>
</html>

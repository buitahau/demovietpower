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
    <%--<a href="<c:url value='/'/>">Back</a>--%>
</head>
<body>
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="<c:url value="/"/>">Home</a>
    </li>
    <li class="breadcrumb-item">
        <a href="<c:url value="/admin/machine/list"/>">List Machine</a>
    </li>

    <li class="breadcrumb-item active">Detail Formula</li>
</ol>
<div class="row">
    <div class="col-12">
        <div class="card mb-3">
            <div class="card-header">
                <i class="fas fa-chart-area"></i>
                Detail Formula
            </div>

            <div class="card-body">
                <div class="table-responsive">
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
    </div>
</div>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: HauKute
  Date: 1/4/2019
  Time: 8:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>List Collections</title>
    <meta charset="utf-8">
</head>

<body>
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="<c:url value="/"/>">Home</a>
    </li>

    <li class="breadcrumb-item active">List Formula</li>
</ol>

<div class="row">
    <div class="col-12">
        <div class="card mb-3">
            <div class="card-header">
                <i class="fas fa-chart-area"></i>
                List Formula

                <a class="btn btn-info btn-sm float-right" href="<c:url value="/admin/formula/add"/>">Add Formula</a>
            </div>

            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Formula Code</th>
                            <th>Formula Name</th>
                            <th>Collection</th>
                            <th>BaseOnCan</th>
                            <th>ApproximateColor</th>
                            <th>Substrate</th>
                            <th>Action</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${listFormula}" var="formula">
                            <tr>
                                <td>${formula.formulaCode}</td>
                                <td>${formula.formulaName}</td>
                                <td>${formula.collection.collectionName}</td>
                                <td>${formula.baseOnCan}</td>
                                <td>${formula.approximateColor}</td>
                                <td>${formula.substrate}</td>
                                <td>
                                    <a href="<c:url value='/admin/formula/edit'/>?formula.formulaId=${formula.formulaId}" class="btn btn-default custom-width">Edit</a>
                                </td>
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

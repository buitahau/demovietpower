<%--
  Created by IntelliJ IDEA.
  User: HauKute
  Date: 1/5/2019
  Time: 9:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Formula Collection</title>
</head>
<body>
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="<c:url value="/"/>">Home</a>
    </li>

    <li class="breadcrumb-item">
        <a href="<c:url value="/admin/collection/list"/>">List Collection</a>
    </li>

    <li class="breadcrumb-item active">
        List Formula of Collection ${collection.collectionName}
    </li>
</ol>

<div class="row">
    <div class="col-12">
        <div class="card mb-3">
            <div class="card-header">
                <i class="fas fa-chart-area"></i>
                List Formula of Collection ${collection.collectionName}
            </div>

            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Formula Code</th>
                            <th>Formula Name</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${listFormulas}" var="f">
                            <tr>
                                <td>${f.formulaCode}</td>
                                <td>${f.formulaName}</td>
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

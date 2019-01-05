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
<div class="row">
    <div class="col-md-12 d-flex align-items-stretch grid-margin">
        <div class="row flex-grow">
            <div class="col-12">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title">Search</h4>
                        <form:form method="POST" modelAttribute="collection" class="form-forms-sample">
                            <div class="row">
                                <div class="col-6">
                                    <div class="form-group">
                                        <label for="collectionName">Name</label>
                                        <form:input type="text" path="collectionName" id="collectionName" class="form-control" placeholder="Enter collection name"/>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-group">
                                        <label for="machine">Machine</label>
                                        <form:select path="machine.machineId" cssClass="form-control" id="machine">
                                            <form:option value="" label="All"/>
                                            <form:option value="-1" label="Default"/>
                                            <c:forEach items="${listMachines}" var="m">
                                                <form:option value="${m.machineId}" label="${m.name}"/>
                                            </c:forEach>
                                        </form:select>
                                    </div>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-success mr-2">Search</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-12 grid-margin">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">List Collection</h4>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Machine</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${listCollections}" var="collection">
                            <tr>
                                <td>${collection.collectionName}</td>
                                <td>${collection.description}</td>
                                <td>${collection.machine.name}</td>
                                <td><a href="<c:url value='/admin/collection/${collection.collectionId}/formula' />" class="btn btn-success custom-width">Detail</a></td>
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

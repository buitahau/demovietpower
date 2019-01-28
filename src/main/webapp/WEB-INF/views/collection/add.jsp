<%--
  Created by IntelliJ IDEA.
  User: HauKute
  Date: 1/5/2019
  Time: 9:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>List Collection</title>
</head>
<c:set var="pageLabel" value="Add Collection"></c:set>
<c:set var="buttonLabel" value="Save Collection"></c:set>
<c:if test="${collection.collectionId != null && collection.collectionId  > 0}">
    <c:set var="pageLabel" value="Edit Collection"></c:set>
    <c:set var="buttonLabel" value="Update Collection"></c:set>
</c:if>
<body>
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="<c:url value="/"/>">Home</a>
    </li>

    <li class="breadcrumb-item">
        <a href="<c:url value="/admin/collection/list"/>">List Collection</a>
    </li>

    <li class="breadcrumb-item active">
        ${pageLabel}
    </li>
</ol>

<div class="row">
    <div class="col-12">
        <div class="card mb-3">
        <c:url value="/admin/collection/insert-or-update" var="insertOrUpdateURL" />
        <form:form method="POST" modelAttribute="collection" class="form-horizontal" id="editForm" action="${insertOrUpdateURL}">
            <div class="card-header">
                <i class="fas fa-chart-area"></i> ${pageLabel}
            </div>

            <div class="card-body">
                <div class="row">
                    <div class="col-12">
                        <div class="form-group">
                            <label for="collectionName">Name</label>
                            <form:input type="text" path="collectionName" id="collectionName" class="form-control" placeholder="Enter collection name"/>
                        </div>
                    </div>

                    <div class="col-12">
                        <div class="form-group">
                            <label for="collectionName">Description</label>
                            <form:input type="text" path="description" id="description" class="form-control" placeholder="Enter collection description"/>
                        </div>
                    </div>
                </div>
                <form:input type="hidden" path="collectionId" id="collectionId" class="form-control"/>
            </div>

            <div class="card-footer small text-muted">
                <div class="row">
                    <button class="btn btn-success mr-2" onclick="addCollection();">
                        ${buttonLabel}
                    </button>
                </div>
            </div>
        </form:form>
        </div>
    </div>
</div>
<script>
    function addCollection(){
        $('#editForm').submit();
    }
</script>
</body>
</html>

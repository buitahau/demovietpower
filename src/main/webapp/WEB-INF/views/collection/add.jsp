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
    <title>Add Collection</title>
</head>
<body>
<div class="row">
    <div class="col-lg-12 grid-margin">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">
                    <c:choose>
                        <c:when test="${collection.collectionId != null && collection.collectionId  > 0}">Edit</c:when>
                        <c:otherwise>Add</c:otherwise>
                    </c:choose>

                    Collection
                </h4>

                <c:url value="/admin/collection/insert-or-update" var="insertOrUpdateURL" />

                <form:form method="POST" modelAttribute="collection" class="form-horizontal" id="editForm" action="${insertOrUpdateURL}">
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
                    <button class="btn btn-success mr-2" onclick="addCollection();">
                        <c:choose>
                            <c:when test="${collection.collectionId != null && collection.collectionId  > 0}">Update</c:when>
                            <c:otherwise>Save</c:otherwise>
                        </c:choose>
                    </button>
                </form:form>
            </div>
        </div>
    </div>
</div>
<script>
    function addCollection(){
        // $('#action').val("insert-or-update");
        $('#editForm').submit();
    }
</script>
</body>
</html>

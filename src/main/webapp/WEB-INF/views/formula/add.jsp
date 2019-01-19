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
                <h4 class="card-title">Add Collection</h4>

                <c:url value="/admin/formula/insert-or-update" var="insertOrUpdateURL" />

                <form:form method="POST" modelAttribute="formula" class="form-horizontal" id="editForm" action="${insertOrUpdateURL}">
                    <div class="row">
                        <div class="col-12">
                            <div class="form-group">
                                <label for="formulaCode">formulaCode</label>
                                <form:input type="text" path="formulaCode" id="formulaCode" class="form-control" placeholder="Enter formula code"/>
                            </div>
                        </div>

                        <div class="col-12">
                            <div class="form-group">
                                <label for="formulaName">formulaName</label>
                                <form:input type="text" path="formulaName" id="formulaName" class="form-control" placeholder="Enter formula name"/>
                            </div>
                        </div>

                        <div class="col-12">
                            <div class="form-group">
                                <label for="collectionId">formulaCode</label>
                                <form:select path="collection.collectionId" cssClass="form-control" id="collectionId">
                                    <form:option value="-1" label="Select Collection"/>
                                    <c:forEach items="${listCollection}" var="collection">
                                        <form:option value="${collection.collectionId}" label="${collection.collectionName}"/>
                                    </c:forEach>
                                </form:select>
                            </div>
                        </div>

                        <div class="col-12">
                            <div class="form-group">
                                <label for="baseOnCan">baseOnCan</label>
                                <form:input type="number" path="baseOnCan" id="baseOnCan" class="form-control" placeholder="Enter formula code"/>
                            </div>
                        </div>

                        <div class="col-12">
                            <div class="form-group">
                                <label for="approximateColor">approximateColor</label>
                                <form:input type="text" path="approximateColor" id="approximateColor" class="form-control" placeholder="Enter formula code"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-12">
                            <div class="form-group">
                                <label for="substrate">substrate</label>
                                <form:input type="text" path="substrate" id="substrate" class="form-control" placeholder="Enter collection description"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-12">
                            <div class="form-group">
                                <label for="substrate">comment</label>
                                <form:textarea path="comment" id="comment" class="form-control" placeholder="Enter collection description" rows="5"/>
                            </div>
                        </div>
                    </div>

                    <form:input type="hidden" path="formulaId" id="formulaId" class="form-control"/>

                    <button class="btn btn-success mr-2" onclick="addFormula();">
                        Save
                    </button>
                </form:form>
            </div>
        </div>
    </div>
</div>

<script>
    function addFormula(){
        $('#editForm').submit();
    }
</script>
</body>
</html>

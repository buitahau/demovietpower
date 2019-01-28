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
    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="<c:url value="/"/>">Home</a>
        </li>

        <li class="breadcrumb-item">
            <a href="<c:url value="/admin/formula/list.html"/>">List Formula</a>
        </li>

        <li class="breadcrumb-item active">
            <c:choose>
                <c:when test="${formulaModel.formula.formulaId != null && formulaModel.formula.formulaId  > 0}">Edit</c:when>
                <c:otherwise>Add</c:otherwise>
            </c:choose>
            Formula
        </li>
    </ol>

    <c:url value="/admin/formula/insert-or-update" var="insertOrUpdateURL" />
    <form:form method="POST" modelAttribute="formulaModel" class="form-horizontal" id="editForm" action="${insertOrUpdateURL}">
        <div class="row">
            <div class="col-lg-8">
                <div class="card mb-3">
                    <div class="card-header">
                        <i class="fas fa-chart-area"></i>
                        <c:choose>
                            <c:when test="${formulaModel.formula.formulaId != null && formulaModel.formula.formulaId  > 0}">Edit</c:when>
                            <c:otherwise>Add</c:otherwise>
                        </c:choose>
                        Formula
                    </div>

                    <div class="card-body">
                        <div class="row">
                            <div class="col-12">
                                <div class="form-group">
                                    <label for="formulaCode">Formula Code</label>
                                    <form:input type="text" path="formula.formulaCode" id="formulaCode" class="form-control" placeholder="Enter formula code"/>
                                </div>
                            </div>

                            <div class="col-12">
                                <div class="form-group">
                                    <label for="formulaName">Formula Name</label>
                                    <form:input type="text" path="formula.formulaName" id="formulaName" class="form-control" placeholder="Enter formula name"/>
                                </div>
                            </div>

                            <div class="col-12">
                                <div class="form-group">
                                    <label for="collectionId">Collection</label>
                                    <form:select path="formula.collection.collectionId" cssClass="form-control" id="collectionId">
                                        <form:option value="-1" label="Select Collection"/>
                                        <c:forEach items="${listCollection}" var="collection">
                                            <form:option value="${collection.collectionId}" label="${collection.collectionName}"/>
                                        </c:forEach>
                                    </form:select>
                                </div>
                            </div>

                            <div class="col-12">
                                <div class="form-group">
                                    <label for="baseOnCan">Base On Can</label>
                                    <form:input type="number" path="formula.baseOnCan" id="baseOnCan" class="form-control" placeholder="Enter formula code"/>
                                </div>
                            </div>

                            <div class="col-12">
                                <div class="form-group">
                                    <label for="approximateColor">Approximate Color</label>
                                    <form:input type="text" path="formula.approximateColor" id="approximateColor" class="form-control" placeholder="Enter formula code"/>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-12">
                                <div class="form-group">
                                    <label for="substrate">Substrate</label>
                                    <form:input type="text" path="formula.substrate" id="substrate" class="form-control" placeholder="Enter collection description"/>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-12">
                                <div class="form-group">
                                    <label for="substrate">Comment</label>
                                    <form:textarea path="formula.comment" id="comment" class="form-control" placeholder="Enter collection description" rows="5"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="card-footer small text-muted">
                        <div class="row">
                            <button class="btn btn-success mr-2" onclick="addFormula();">
                                <c:choose>
                                    <c:when test="${formulaModel.formula.formulaId != null && formulaModel.formula.formulaId  > 0}">Update</c:when>
                                    <c:otherwise>Save</c:otherwise>
                                </c:choose>
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-4">
                <div class="card mb-3">
                    <div class="card-header">
                        <i class="fas fa-chart-area"></i>
                        List Formula Collection
                    </div>

                    <div class="card-body">
                        <div class="row">
                            <c:forEach items="${formulaModel.listFormulaColourant}" var="formulaColourant" varStatus="status">
                                <div class="col-4">
                                    <div class="form-group">
                                        <label>${formulaColourant.colourant.colourantCode}</label>
                                        <form:input type="number" path="listFormulaColourant[${status.index}].quantity" class="form-control" value="${formulaModel.listFormulaColourant[status.index].quantity}"/>
                                        <form:input type="hidden" path="listFormulaColourant[${status.index}].colourant.colourantId" class="form-control" value="${formulaModel.listFormulaColourant[status.index].colourant.colourantId}"/>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <form:input type="hidden" path="formula.formulaId" id="formulaId" class="form-control"/>
    </form:form>

<script>
    function addFormula(){
        $('#editForm').submit();
    }
</script>
</body>
</html>

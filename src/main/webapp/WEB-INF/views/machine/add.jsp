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
<c:set var="pageLabel" value="Add Machine"></c:set>
<c:set var="buttonLabel" value="Save Machine"></c:set>
<c:if test="${machine.machineId != null && machine.machineId  > 0}">
    <c:set var="pageLabel" value="Edit Machine"></c:set>
    <c:set var="buttonLabel" value="Update Machine"></c:set>
</c:if>
<body>
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="<c:url value="/"/>">Home</a>
    </li>

    <li class="breadcrumb-item">
        <a href="<c:url value="/admin/machine/list"/>">List Machine</a>
    </li>

    <li class="breadcrumb-item active">
        ${pageLabel}
    </li>
</ol>

<div class="row">
    <div class="col-12">
        <div class="card mb-3">
        <c:url value="/admin/machine/insert-or-update" var="insertOrUpdateURL" />
        <form:form method="POST" modelAttribute="machine" class="form-horizontal" id="editForm" action="${insertOrUpdateURL}">
            <div class="card-header">
                <i class="fas fa-chart-area"></i> ${pageLabel}
            </div>

            <div class="card-body">
                <div class="row">
                    <div class="col-12">
                        <div class="form-group">
                            <label for="code">Name</label>
                            <form:input type="text" path="code" id="code" class="form-control" placeholder="Enter machine code"/>
                        </div>
                    </div>


                    <div class="col-12">
                        <div class="form-group">
                            <label for="name">Name</label>
                            <form:input type="text" path="name" id="name" class="form-control" placeholder="Enter machine name"/>
                        </div>
                    </div>

                    <div class="col-12">
                        <div class="form-group">
                            <label for="description">Description</label>
                            <form:textarea type="text" path="description" id="description" class="form-control" placeholder="Enter machine description" rows="3"/>
                        </div>
                    </div>

                    <div class="col-4">
                        <div class="form-group">
                            <label for="maxQuantity">maxQuantity</label>
                            <form:input type="number" path="maxQuantity" id="maxQuantity" class="form-control"/>
                        </div>
                    </div>

                    <div class="col-4">
                        <div class="form-group">
                            <label for="warningQuantity">warningQuantity</label>
                            <form:input type="number" path="warningQuantity" id="warningQuantity" class="form-control"/>
                        </div>
                    </div>

                    <div class="col-4">
                        <div class="form-group">
                            <label for="minQuantity">minQuantity</label>
                            <form:input type="number" path="minQuantity" id="minQuantity" class="form-control"/>
                        </div>
                    </div>
                </div>
                <form:input type="hidden" path="machineId" id="machineId" class="form-control"/>
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

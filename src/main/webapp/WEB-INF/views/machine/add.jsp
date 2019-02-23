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
                            <label for="code">Code</label>
                            <form:input type="text" path="code" id="code" class="form-control" placeholder="Enter machine code"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <div class="form-group">
                            <label for="name">Name</label>
                            <form:input type="text" path="name" id="name" class="form-control" placeholder="Enter machine name"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <div class="form-group">
                            <label for="description">Description</label>
                            <form:textarea type="text" path="description" id="description" class="form-control" placeholder="Enter machine description" rows="3"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-4">
                        <div class="form-group">
                            <label for="maxQuantity">Max Quantity</label>
                            <form:input type="number" path="maxQuantity" id="maxQuantity" class="form-control"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-4">
                        <div class="form-group">
                            <label for="minQuantity">Min Quantity</label>
                            <form:input type="number" path="minQuantity" id="minQuantity" class="form-control"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-4">
                        <div class="form-group">
                            <label for="warningQuantity">Warning Quantity</label>
                            <form:input type="number" path="warningQuantity" id="warningQuantity" class="form-control"/>
                        </div>
                    </div>
                </div>

                <form:input type="hidden" path="machineId" id="machineId" class="form-control"/>
            </div>

            <div class="card-footer small text-muted">
                <div class="row">
                    <button class="btn btn-success mr-2">
                        ${buttonLabel}
                    </button>
                </div>
            </div>
        </form:form>
        </div>
    </div>
</div>

<script>
    $(document).ready(function(){
        $('.btn-success').click(function(e){
            addCollection(e);
        })
    });

    function addCollection(e){
        e.preventDefault();
        e.stopPropagation();

        $('.error').remove();

        var valid = true;

        var maxQuantity = parseFloat($('#maxQuantity').val());
        var minQuantity = parseFloat($('#minQuantity').val());
        var warningQuantity = parseFloat($('#warningQuantity').val());

        if($('#code').val().trim() == ""){
            valid = false;

            var errorMessage = $('<span class="error">The code  is not correct, please update!!!</span>');
            errorMessage.insertAfter($('#code'));
        }

        if($('#name').val().trim() == ""){
            valid = false;

            var errorMessage = $('<span class="error">The name  is not correct, please update!!!</span>');
            errorMessage.insertAfter($('#name'));
        }

        if($('#maxQuantity').val().trim() == "" || minQuantity > maxQuantity || minQuantity > warningQuantity){
            valid = false;

            var errorMessage = $('<span class="error">The Min quantity is not correct, please update!!!</span>');
            errorMessage.insertAfter($('#minQuantity'));
        }

        if($('#warningQuantity').val().trim() == "" ||  warningQuantity >= maxQuantity){
            valid = false;

            var errorMessage = $('<span class="error">The warning quantity is not correct, please update!!!</span>');
            errorMessage.insertAfter($('#warningQuantity'));
        }

        if($('#maxQuantity').val().trim() == ""){
            valid = false;

            var errorMessage = $('<span class="error">The max quantity is not correct, please update!!!</span>');
            errorMessage.insertAfter($('#maxQuantity'));
        }

        if(valid){
            $('#editForm').submit();
        } else {
            return false;
        }
    }
</script>
</body>
</html>

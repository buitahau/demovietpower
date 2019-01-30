<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<c:set var="pageLabel" value="Add Company"></c:set>
<c:set var="buttonLabel" value="Save Company"></c:set>
<c:if test="${company.companyId != null && company.companyId  > 0}">
    <c:set var="pageLabel" value="Edit Company"></c:set>
    <c:set var="buttonLabel" value="Update Company"></c:set>
</c:if>

<head>
    <title>${pageLabel}</title>
</head>
<body>
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="<c:url value="/"/>">Home</a>
    </li>

    <li class="breadcrumb-item">
        <a href="<c:url value="/admin/company/list"/>">List Company</a>
    </li>

    <li class="breadcrumb-item active">
        ${pageLabel}
    </li>
</ol>

<div class="row">
    <div class="col-12">
        <div class="card mb-3">
        <c:url value="/admin/company/insert-or-update" var="insertOrUpdateURL" />
        <form:form method="POST" modelAttribute="company" class="form-horizontal" id="editForm" action="${insertOrUpdateURL}">
            <div class="card-header">
                <i class="fas fa-chart-area"></i> ${pageLabel}
            </div>

            <div class="card-body">
                <div class="row">
                    <div class="col-12">
                        <div class="form-group">
                            <label for="code">code</label>
                            <form:input type="text" path="code" id="code" class="form-control" placeholder="Enter Company code"/>
                        </div>
                    </div>

                    <div class="col-12">
                        <div class="form-group">
                            <label for="name">Name</label>
                            <form:input type="text" path="name" id="name" class="form-control" placeholder="Enter Company name"/>
                        </div>
                    </div>

                    <div class="col-12">
                        <div class="form-group">
                            <label for="phone">phone</label>
                            <form:input type="text" path="phone" id="phone" class="form-control" placeholder="Enter Company phone"/>
                        </div>
                    </div>

                    <div class="col-12">
                        <div class="form-group">
                            <label for="email">email</label>
                            <form:input type="text" path="email" id="email" class="form-control" placeholder="Enter Company phone"/>
                        </div>
                    </div>

                    <div class="col-12">
                        <div class="form-group">
                            <label for="website">website</label>
                            <form:input type="text" path="website" id="website" class="form-control" placeholder="Enter Company website"/>
                        </div>
                    </div>

                    <div class="col-12">
                        <div class="form-group">
                            <label for="address">address</label>
                            <form:input type="text" path="address" id="address" class="form-control" placeholder="Enter Company phone"/>
                        </div>
                    </div>

                    <div class="col-12">
                        <div class="form-group">
                            <label for="city">city</label>
                            <form:input type="text" path="city" id="city" class="form-control" placeholder="Enter Company phone"/>
                        </div>
                    </div>
                </div>
                <form:input type="hidden" path="companyId" id="companyId" class="form-control"/>
            </div>

            <div class="card-footer small text-muted">
                <div class="row">
                    <button class="btn btn-success mr-2" onclick="saveCompany();">
                        ${buttonLabel}
                    </button>
                </div>
            </div>
        </form:form>
        </div>
    </div>
</div>
<script>
    function saveCompany(){
        $('#editForm').submit();
    }
</script>
</body>
</html>

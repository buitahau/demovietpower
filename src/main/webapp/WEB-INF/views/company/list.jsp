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
    <title>List Company</title>
    <meta charset="utf-8">
</head>
<body>

<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="<c:url value="/"/>">Home</a>
    </li>

    <li class="breadcrumb-item active">
       List Company
    </li>
</ol>

<div class="row">
    <div class="col-12">
        <div class="card mb-3">
            <form:form method="POST" modelAttribute="company" class="form-forms-sample">
                <div class="card-header">
                    <i class="fas fa-chart-area"></i>
                    Search
                </div>

                <div class="card-body">
                    <div class="row">
                        <div class="col-6">
                            <div class="form-group">
                                <label for="code">code</label>
                                <form:input type="text" path="code" id="code" class="form-control" placeholder="Enter Company code"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-6">
                            <div class="form-group">
                                <label for="name">Name</label>
                                <form:input type="text" path="name" id="name" class="form-control" placeholder="Enter Company name"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-6">
                            <div class="form-group">
                                <label for="phone">phone</label>
                                <form:input type="text" path="phone" id="name" class="form-control" placeholder="Enter Company phone"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-6">
                            <div class="form-group">
                                <label for="website">website</label>
                                <form:input type="text" path="website" id="website" class="form-control" placeholder="Enter Company website"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card-footer small text-muted">
                    <div class="row">
                        <button type="submit" class="btn btn-success mr-2">Search</button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-12">
        <div class="card mb-3">
            <div class="card-header">
                <i class="fas fa-chart-area"></i>
                List Company
                <a class="btn btn-info btn-sm float-right"  href="<c:url value="/admin/company/add"/>">Add Company</a>
            </div>

            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>code</th>
                            <th>name</th>
                            <th>phone</th>
                            <th>email</th>
                            <th>website</th>
                            <th>address</th>
                            <th>city</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${listCompany}" var="company">
                            <tr>
                                <td>${company.code}</td>
                                <td>${company.name}</td>
                                <td>${company.phone}</td>
                                <td>${company.email}</td>
                                <td>${company.website}</td>
                                <td>${company.address}</td>
                                <td>${company.city}</td>
                                <td>
                                    <a href="<c:url value='/admin/company/edit'/>?companyId=${company.companyId}" class="btn btn-default custom-width">Edit</a>
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

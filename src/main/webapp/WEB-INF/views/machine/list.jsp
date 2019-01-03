<%--
  Created by IntelliJ IDEA.
  User: HauKute
  Date: 12/20/2018
  Time: 9:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Machine</title>
    <meta charset="utf-8">
</head>
<body>
<div class="row">
    <div class="col-lg-12 grid-margin">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">List Machine</h4>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Code</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listMachines}" var="machine">
                                <tr>
                                    <td>${machine.name}</td>
                                    <td>${machine.code}</td>
                                    <td><a href="<c:url value='/admin/machine/${machine.machineId}/colour' />" class="btn btn-success custom-width">colour</a>
                                    <a href="<c:url value='/admin/machine/${machine.machineId}/formula' />" class="btn btn-primary custom-width">formula</a></td>
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

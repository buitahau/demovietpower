<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin - Login</title>

    <!-- Bootstrap core CSS-->
    <link href="<c:url value="/static/vendor/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">

    <!-- Custom fonts for this template-->
    <link href="<c:url value="/static/vendor/fontawesome-free/css/all.min.css"/>" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template-->
    <link href="<c:url value="/static/css/sb-admin.css"/>" rel="stylesheet">

</head>

<body class="bg-dark">

<div class="container">
    <div class="card card-login mx-auto mt-5">
        <div class="card-header">Login</div>
        <div class="card-body">
            <c:url var="loginUrl" value="/login" />
            <h5>Login</h5>

            <form action="${loginUrl}" method="post">
                <c:if test="${param.error != null}">
                    <div class="alert alert-danger">
                        <p>Invalid username and password.</p>
                    </div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div class="alert alert-success">
                        <p>You have been logged out successfully.</p>
                    </div>
                </c:if>

                <spring:message code="label.userName" var="labelUserName"></spring:message>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="text" id="username" class="form-control" name="ssoId" placeholder="${labelUserName}" required="required" autofocus="autofocus">
                        <label for="username">User Name</label>
                    </div>
                </div>

                <spring:message code="label.password" var="labelPassword"></spring:message>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="password" class="form-control" id="password" name="password" placeholder="${labelPassword}" required="required">
                        <label for="password">Password</label>
                    </div>
                </div>

                <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />

                <spring:message code="label.submit" var="labelSubmit"></spring:message>
                <button type="submit" class="btn btn-primary btn-block" href="index.html">${labelSubmit}</button>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="<c:url value="/static/vendor/jquery/jquery.min.js"/>"></script>
<script src="<c:url value="/static/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>

<!-- Core plugin JavaScript-->
<script src="<c:url value="/static/vendor/jquery-easing/jquery.easing.min.js"/>"></script>

</body>

</html>

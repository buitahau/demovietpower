<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login page</title>
    <link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
    <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
</head>

<body>
<div id="mainWrapper">
    <div class="login-container">
        <div class="login-card">
            <div class="login-form">
                <c:url var="loginUrl" value="/login" />
                <form action="${loginUrl}" method="post" class="form-horizontal">
                    <div>
                        <div style="text-align: right;padding:5px;margin:5px 0px;background:#ccc;">
                            <a href="${pageContext.request.contextPath}/login?lang=en">Login (English)</a>
                            &nbsp;&nbsp;
                            <a href="${pageContext.request.contextPath}/login?lang=fr">Login (French)</a>
                            &nbsp;&nbsp;
                            <a href="${pageContext.request.contextPath}/login?lang=vi">Login (Vietnamese)</a>
                        </div>
                    </div>
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
                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
                        <input type="text" class="form-control" id="username" name="ssoId" placeholder="${labelUserName}" required>
                    </div>
                    <spring:message code="label.password" var="labelPassword"></spring:message>
                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="${labelPassword}" required>
                    </div>
                    <div class="input-group input-sm">
                        <div class="checkbox">
                            <label><input type="checkbox" id="rememberme" name="remember-me"> Remember Me</label>
                        </div>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                    <spring:message code="label.submit" var="labelSubmit"></spring:message>
                    <div class="form-actions">
                        <input type="submit"
                               class="btn btn-block btn-primary btn-default" value="${labelSubmit}">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: HauKute
  Date: 10/7/2018
  Time: 11:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>--%>
<!DOCTYPE html>
<html lang="">
<head>
    <sitemesh:write property="head"/>
    <%@ include file="/WEB-INF/views/decorator/header.jsp" %>
</head>
<body>
    <%@ include file="/WEB-INF/views/decorator/navigation.jsp" %>
    <main id="page-wrapper5">
        <div class="container-fluid">
            <sitemesh:write property="body" />
        </div>
    </main>
</body>
</html>

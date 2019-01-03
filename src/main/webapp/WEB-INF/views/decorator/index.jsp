<%--
  Created by IntelliJ IDEA.
  User: HauKute
  Date: 10/7/2018
  Time: 11:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="">
<head>
    <sitemesh:write property="head"/>
    <%@ include file="/WEB-INF/views/decorator/header.jsp" %>
</head>
<body>
    <div class="container-scroller">
        <%@ include file="/WEB-INF/views/decorator/top_title.jsp" %>
        <div class="container-fluid page-body-wrapper">
            <%@ include file="/WEB-INF/views/decorator/navigation.jsp" %>
            <div class="main-panel">
                <div class="content-wrapper">
                    <sitemesh:write property="body" />
                </div>
            </div>
        </div>
    </div>
</body>
</html>

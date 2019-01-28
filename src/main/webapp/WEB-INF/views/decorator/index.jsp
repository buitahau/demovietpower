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

<%--<body id="page-top">--%>
    <%--<div id="loader_container" class="row hide_loader">--%>
        <%--<div id="loader">--%>
            <%--<div class="dot"></div>--%>
            <%--<div class="dot"></div>--%>
            <%--<div class="dot"></div>--%>
            <%--<div class="dot"></div>--%>
            <%--<div class="dot"></div>--%>
            <%--<div class="dot"></div>--%>
            <%--<div class="dot"></div>--%>
            <%--<div class="dot"></div>--%>
            <%--<div class="lading"></div>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="container-scroller">--%>
        <%--<%@ include file="/WEB-INF/views/decorator/top_title.jsp" %>--%>
        <%--<div class="container-fluid page-body-wrapper">--%>
            <%--<%@ include file="/WEB-INF/views/decorator/navigation.jsp" %>--%>
            <%--<div class="main-panel">--%>
                <%--<div class="content-wrapper">--%>
                    <%--<sitemesh:write property="body" />--%>
                <%--</div>--%>
                <%--<footer class="footer">--%>
                    <%--<div class="container-fluid clearfix">--%>
                        <%--<span class="text-muted d-block text-center text-sm-left d-sm-inline-block">Copyright © 2018</span>--%>
                    <%--</div>--%>
                <%--</footer>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</body>--%>

<body id="page-top">
<%@ include file="/WEB-INF/views/decorator/top_title.jsp" %>

<div id="wrapper">
    <%@ include file="/WEB-INF/views/decorator/navigation.jsp" %>
    <div id="content-wrapper">
        <div class="container-fluid">
            <sitemesh:write property="body" />
        </div>

        <footer class="sticky-footer">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright © Your Website 2018</span>
                </div>
            </div>
        </footer>
    </div>
</div>
<%@ include file="/WEB-INF/views/decorator/footer.jsp" %>
</body>
</html>

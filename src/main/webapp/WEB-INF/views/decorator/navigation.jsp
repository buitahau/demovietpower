<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Sidebar -->
<ul class="sidebar navbar-nav">
    <li class="nav-item">
        <a class="nav-link" href="<c:url value="/admin/user/list"/>">
            <i class="menu-icon mdi mdi-table"></i>
            <span class="menu-title">User</span>
        </a>
    </li>

    <li class="nav-item">
        <a class="nav-link" href="<c:url value="/admin/machine/list"/>">
            <i class="menu-icon mdi mdi-television"></i>
            <span class="menu-title">List Machine</span>
        </a>
    </li>

    <li class="nav-item">
        <a class="nav-link" href="<c:url value="/admin/collection/list"/>">
            <i class="menu-icon mdi mdi-table"></i>
            <span class="menu-title">Collections</span>
        </a>
    </li>

    <li class="nav-item">
        <a class="nav-link" href="<c:url value="/admin/formula/list"/>">
            <i class="menu-icon mdi mdi-table"></i>
            <span class="menu-title">Formula</span>
        </a>
    </li>

    <li class="nav-item">
        <a class="nav-link" href="<c:url value="/admin/import/upload"/>">
            <i class="menu-icon mdi mdi-sticker"></i>
            <span class="menu-title">Import</span>
        </a>
    </li>
</ul>
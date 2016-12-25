<%@ page import="com.karlchu.mo.web.security.util.SecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>
<header class="main-header">
<!-- Logo -->
<a href="/home.html" class="logo">
    <!-- mini logo for sidebar mini 50x50 pixels -->
    <span class="logo-mini"></span>
    <!-- logo for regular state and mobile devices -->
    <span class="logo-lg">MobiFone</span>
</a>
<!-- Header Navbar: style can be found in header.less -->
<nav class="navbar navbar-static-top" role="navigation">
<!-- Sidebar toggle button-->
<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
    <span class="sr-only">Toggle navigation</span>
</a>

<c:set var="avatar" value="/themes/vmsreport/dist/img/avatar.png" />
<security:authentication var="principal" property="principal" />

<c:if test="${not empty principal.avatar}">
    <c:set var="avatar" value="/repository${principal.avatar}" />
</c:if>

<div class="navbar-custom-menu">
<ul class="nav navbar-nav">
<li class="dropdown user user-menu">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
        <img src="<c:url value="${avatar}" />" class="user-image" alt="User Image">
        <%--<span class="hidden-xs"><%=SecurityUtils.getPrincipal().getDisplayName()%></span>--%>
    </a>
    <ul class="dropdown-menu">
        <!-- User image -->
        <li class="user-header">
            <img src="<c:url value="${avatar}" />" class="img-circle" alt="Avatar">

            <%--<p>--%>
                <%--<fmt:message key="user.manager.last_login" />:&nbsp;<%=SecurityUtils.getPrincipal().getLastLoginDateTime()%>--%>
            <%--</p>--%>
        </li>
        <!-- Menu Footer-->
        <li class="user-footer">
            <c:set var="prefix" value="/normal" />
            <security:authorize ifAnyGranted="ADMIN">
                <c:set var="prefix" value="/admin"></c:set>
            </security:authorize>
            <div class="pull-left">
                <a href="<c:url value="${prefix}/user/profileedit.html?pojo.userId=${principal.userId}"/>" class="btn btn-default btn-flat"><fmt:message key="editprofile" /></a>
            </div>
            <div class="pull-right">
                <a href="<c:url value="/logout.jsp"/>" class="btn btn-default btn-flat"><fmt:message key="logout" /></a>
            </div>
        </li>
    </ul>
</li>
</ul>
</div>
</nav>
</header>


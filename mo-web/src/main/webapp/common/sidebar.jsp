<%@ page import="com.karlchu.mo.web.security.util.SecurityUtils" %>
<%@page trimDirectiveWhitespaces="true"%>
<%@ include file="/common/taglibs.jsp" %>
<c:set var="prefix" value="/normal" />
<security:authorize ifAnyGranted="ADMIN">
    <c:set var="prefix" value="/admin" />
</security:authorize>

<c:set var="avatar" value="/themes/vmsreport/dist/img/avatar.png" />
<security:authentication var="principal" property="principal" />

<c:if test="${not empty principal.avatar}">
    <c:set var="avatar" value="/repository${principal.avatar}" />
</c:if>

<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="<c:url value="${avatar}" />" class="img-circle" alt="Avatar" />
            </div>
            <div class="pull-left info">
                <%--<p><%=SecurityUtils.getPrincipal().getDisplayName()%></p>--%>
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">MENU</li>
            <li id="administration_menu" class="treeview">
                <a href="#">
                    <i class="fa fa-navicon"></i> <span><fmt:message key="menu.administration"/></span> <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <security:authorize ifAnyGranted = "ADMIN,MANAGE_USER_FULL,MANAGE_USER_ADD,MANAGE_USER_EDIT,MANAGE_USER_VIEW,MANAGE_USER_DELETE">
                        <li id="user_menu"><a href="<c:url value="${prefix}/user/list.html"/>"><i class="fa fa-circle-o"></i> <fmt:message key="menu.user"/></a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted = "ADMIN,MANAGE_USERGROUP_FULL,MANAGE_USERGROUP_ADD,MANAGE_USERGROUP_EDIT,MANAGE_USERGROUP_VIEW">
                        <li id="usergroup_menu"><a href="<c:url value="${prefix}/usergroup/list.html"/>"><i class="fa fa-circle-o"></i> <fmt:message key="menu.usergroup"/></a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted = "ADMIN,MANAGE_ROLE_ADD,MANAGE_ROLE_EDIT,MANAGE_ROLE_VIEW,MANAGE_ROLE_DELETE,MANAGE_ROLE_FULL,MANAGE_ROLE_PERMISSION">
                        <li id="role_menu"><a href="<c:url value="${prefix}/role/list.html"/>"><i class="fa fa-circle-o"></i> <fmt:message key="menu.role"/></a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted = "ADMIN,MANAGE_TEAM_FULL,MANAGE_TEAM_ADD,MANAGE_TEAM_EDIT,MANAGE_TEAM_VIEW,MANAGE_TEAM_DELETE,MANAGE_TEAM_DISTRICT">
                        <li id="team_menu"><a href="<c:url value="${prefix}/team/list.html"/>"><i class="fa fa-circle-o"></i> <fmt:message key="menu.team"/></a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted = "ADMIN,MANAGE_PACKAGE_FULL,MANAGE_PACKAGE_ADD,MANAGE_PACKAGE_EDIT,MANAGE_PACKAGE_VIEW,MANAGE_PACKAGE_DELETE,MANAGE_PACKAGE_PACKAGEGROUP">
                        <li id="package_level_menu"><a href="<c:url value="${prefix}/packagelevel/list.html"/>"><i class="fa fa-circle-o"></i> <fmt:message key="menu.packagelevel"/></a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted = "ADMIN,MANAGE_PACKAGE_FULL,MANAGE_PACKAGE_ADD,MANAGE_PACKAGE_EDIT,MANAGE_PACKAGE_VIEW,MANAGE_PACKAGE_DELETE,MANAGE_PACKAGE_PACKAGEGROUP">
                        <li id="package_group_menu"><a href="<c:url value="${prefix}/packagegroup/list.html"/>"><i class="fa fa-circle-o"></i> <fmt:message key="menu.packagegroup"/></a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted = "ADMIN,MANAGE_DEPARTMENT_FULL,MANAGE_DEPARTMENT_ADD,MANAGE_DEPARTMENT_EDIT,MANAGE_DEPARTMENT_VIEW,MANAGE_DEPARTMENT_DELETE">
                        <li id="department_menu"><a href="<c:url value="${prefix}/department/list.html"/>"><i class="fa fa-circle-o"></i> <fmt:message key="menu.department"/></a></li>
                    </security:authorize>
                </ul>
            </li>
            <li  id="report_menu" class="treeview">
                <a href="#">
                    <i class="fa fa-list-alt"></i> <span><fmt:message key="menu.report"/></span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <security:authorize ifAnyGranted="ADMIN,HAS_GRANTED_DISTRICTS">
                    <ul class="treeview-menu">
                        <security:authorize ifAnyGranted = "ADMIN,MANAGE_REPORT_101_FULL,MANAGE_REPORT_101_VIEW,MANAGE_REPORT_101_EXPORT">
                            <li id="report101_menu"><a href="<c:url value="${prefix}/report/report101A.html"/>"><i class="fa fa-circle-o"></i>&nbsp;<span class="right_submenu"><fmt:message key="menu.report101"/></span></a></li>
                        </security:authorize>
                        <security:authorize ifAnyGranted = "ADMIN,MANAGE_REPORT_102_FULL,MANAGE_REPORT_102_VIEW,MANAGE_REPORT_102_EXPORT">
                            <li id="report102_menu"><a href="<c:url value="${prefix}/report/report102A.html"/>"><i class="fa fa-circle-o"></i>&nbsp;<span class="right_submenu"><fmt:message key="menu.report102"/></span></a></li>
                        </security:authorize>
                        <security:authorize ifAnyGranted = "ADMIN,MANAGE_REPORT_103_FULL,MANAGE_REPORT_103_VIEW,MANAGE_REPORT_103_EXPORT">
                            <li id="report103_menu"><a href="<c:url value="${prefix}/report/report103A.html"/>"><i class="fa fa-circle-o"></i>&nbsp;<span class="right_submenu"><fmt:message key="menu.report103"/></span></a></li>
                        </security:authorize>
                        <security:authorize ifAnyGranted = "ADMIN,MANAGE_REPORT_104_FULL,MANAGE_REPORT_104_VIEW,MANAGE_REPORT_104_EXPORT">
                            <li id="report104_menu"><a href="<c:url value="${prefix}/report/report104.html"/>"><i class="fa fa-circle-o"></i>&nbsp;<span class="right_submenu"><fmt:message key="menu.report104"/></span></a></li>
                        </security:authorize>
                        <security:authorize ifAnyGranted = "ADMIN,MANAGE_REPORT_105_FULL,MANAGE_REPORT_105_VIEW,MANAGE_REPORT_105_EXPORT">
                            <li id="report105_menu"><a href="<c:url value="${prefix}/report/report105.html"/>"><i class="fa fa-circle-o"></i>&nbsp;<span class="right_submenu"><fmt:message key="menu.report105"/></span></a></li>
                        </security:authorize>
                        <security:authorize ifAnyGranted = "ADMIN,MANAGE_REPORT_106_FULL,MANAGE_REPORT_106_VIEW,MANAGE_REPORT_106_EXPORT">
                            <li id="report106_menu"><a href="<c:url value="${prefix}/report/report106.html"/>"><i class="fa fa-circle-o"></i>&nbsp;<span class="right_submenu"><fmt:message key="menu.report106"/></span></a></li>
                        </security:authorize>
                        <security:authorize ifAnyGranted = "ADMIN,MANAGE_REPORT_107_FULL,MANAGE_REPORT_107_VIEW,MANAGE_REPORT_107_EXPORT">
                            <li id="report107_menu"><a href="<c:url value="${prefix}/report/report107.html"/>"><i class="fa fa-circle-o"></i>&nbsp;<span class="right_submenu"><fmt:message key="menu.report107"/></span></a></li>
                        </security:authorize>
                        <security:authorize ifAnyGranted = "ADMIN,MANAGE_REPORT_108_FULL,MANAGE_REPORT_108_VIEW,MANAGE_REPORT_108_EXPORT">
                            <li id="report108_menu"><a href="<c:url value="${prefix}/report/report108.html"/>"><i class="fa fa-circle-o"></i>&nbsp;<span class="right_submenu"><fmt:message key="menu.report108"/></span></a></li>
                        </security:authorize>
                        <security:authorize ifAnyGranted = "ADMIN,MANAGE_REPORT_109_FULL,MANAGE_REPORT_109_VIEW,MANAGE_REPORT_109_EXPORT">
                            <li id="report109_menu"><a href="<c:url value="${prefix}/report/report109.html"/>"><i class="fa fa-circle-o"></i>&nbsp;<span class="right_submenu"><fmt:message key="menu.report109"/></span></a></li>
                        </security:authorize>
                    </ul>
                </security:authorize>
            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
<%@ include file="/common/taglibs.jsp" %>
<div id="sidebar" class="sidebar responsive ace-save-state sidebar-fixed">
<script type="text/javascript">
    try {
        ace.settings.loadState('sidebar')
    } catch (e) {
    }
</script>

<div class="sidebar-shortcuts" id="sidebar-shortcuts">
    <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
        <button class="btn btn-success">
            <i class="ace-icon fa fa-signal"></i>
        </button>

        <button class="btn btn-info">
            <i class="ace-icon fa fa-pencil"></i>
        </button>

        <button class="btn btn-warning">
            <i class="ace-icon fa fa-users"></i>
        </button>

        <button class="btn btn-danger">
            <i class="ace-icon fa fa-cogs"></i>
        </button>
    </div>

    <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
        <span class="btn btn-success"></span>

        <span class="btn btn-info"></span>

        <span class="btn btn-warning"></span>

        <span class="btn btn-danger"></span>
    </div>
</div>
<!-- /.sidebar-shortcuts -->

<ul class="nav nav-list">
<li>
    <a href="<c:url value="/home.html"/>">
        <i class="menu-icon fa fa-tachometer"></i>
        <span class="menu-text"> Dashboard </span>
    </a>

    <b class="arrow"></b>
</li>
<security:authorize ifAllGranted="USER_MANAGEMENT">
<li id="users-manage">
    <a href="#" class="dropdown-toggle"><i class="menu-icon fa fa-desktop"></i>
                <span class="menu-text">
                    <fmt:message key="users.management"/>
                </span>
        <b class="arrow fa fa-angle-down"></b>
    </a>
    <b class="arrow"></b>
    <ul class="submenu">
        <li id="permission">
            <a href="<c:url value="/admin/permission/list.html"/>"><i
                    class="menu-icon fa fa-caret-right"></i><fmt:message key="permission.title"/></a>
            <b class="arrow"></b>
        </li>
        <li id="role">
            <a href="<c:url value="/admin/role/list.html"/>">
                <i class="menu-icon fa fa-caret-right"></i><fmt:message key="role.title"/>
            </a>
            <b class="arrow"></b>
        </li>
        <li id="user">
            <a href="<c:url value="/admin/user/list.html"/>">
                <i class="menu-icon fa fa-caret-right"></i><fmt:message key="users.title"/>
            </a>
            <b class="arrow"></b>
        </li>
        <li id="userGroup">
            <a href="<c:url value="/admin/usergroup/list.html"/>">
                <i class="menu-icon fa fa-caret-right"></i><fmt:message key="userGroup.title"/>
            </a>
            <b class="arrow"></b>
        </li>
    </ul>
</li>
</security:authorize>
    <li>
        <a href="#" class="dropdown-toggle">
            <i class="menu-icon fa fa-list"></i>
            <span class="menu-text">
                    <fmt:message key="label.tip.title"/>
                </span>
            <b class="arrow fa fa-angle-down"></b>
        </a>
        <b class="arrow"></b>
        <ul class="submenu">
            <li>
                <a href="<c:url value="/admin/tip/list.html"/>">
                    <i class="menu-icon fa fa-caret-right"></i><fmt:message key="list.tip.title"/>
                </a>
                <b class="arrow"></b>
            </li>
        </ul>
    </li>
</ul>
<!-- /.nav-list -->

<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
    <i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state"
       data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
</div>
</div>

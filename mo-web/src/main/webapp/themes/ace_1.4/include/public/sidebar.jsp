<%@ include file="/common/taglibs.jsp" %>
<div id="sidebar" class="sidebar                  responsive                    ace-save-state">
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
<security:authorize ifAllGranted="META_DATA">
<li id="meta-data">
    <a href="#" class="dropdown-toggle">
        <i class="menu-icon fa fa-list"></i>
                <span class="menu-text">
                    <fmt:message key="metadata"/>
                </span>
        <b class="arrow fa fa-angle-down"></b>
    </a>
    <b class="arrow"></b>
    <ul class="submenu">
        <li id="region">
            <a href="<c:url value="/admin/region/list.html"/>">
                <i class="menu-icon fa fa-caret-right"></i><fmt:message key="region.management"/>
            </a>
            <b class="arrow"></b>
        </li>
        <li id="city">
            <a href="<c:url value="/admin/city/list.html"/>">
                <i class="menu-icon fa fa-caret-right"></i><fmt:message key="city.management"/>
            </a>
            <b class="arrow"></b>
        </li>
        <li id="distributor">
            <a href="<c:url value="/admin/distributor/list.html"/>">
                <i class="menu-icon fa fa-caret-right"></i><fmt:message key="distributor.management"/>
            </a>
            <b class="arrow"></b>
        </li>
        <li id="hotzone">
            <a href="<c:url value="/admin/hotzone/list.html"/>">
                <i class="menu-icon fa fa-caret-right"></i><fmt:message key="hotzone.management"/>
            </a>
            <b class="arrow"></b>
        </li>
        <li id="outlet-manage" class="">
            <a href="#" class="dropdown-toggle">
                <i class="menu-icon fa fa-caret-right"></i>
                <fmt:message key="outlet.management"/>
                <b class="arrow fa fa-angle-down"></b>
            </a>

            <b class="arrow"></b>

            <ul class="submenu">
                <li id="outlet" class="">
                    <a href="<c:url value="/admin/outlet/list.html"/>">
                        <i class="menu-icon fa fa-caret-right"></i><fmt:message key="outlet.title"/>
                    </a>

                    <b class="arrow"></b>
                </li>

                <li id="outlettype" class="">
                    <a href="<c:url value="/admin/outlettype/list.html"/>">
                        <i class="menu-icon fa fa-caret-right"></i><fmt:message key="outlettype.title"/>
                    </a>
                    <b class="arrow"></b>
                </li>
            </ul>
        </li>


        <li id="product-manage">
            <a href="#" class="dropdown-toggle">
                <i class="menu-icon fa fa-caret-right"></i>
                <fmt:message key="product.management"/>
                <b class="arrow fa fa-angle-down"></b>
            </a>

            <b class="arrow"></b>

            <ul class="submenu">
                <li id="catgroup" class="">
                    <a href="<c:url value="/admin/catgroup/list.html"/>">
                        <i class="menu-icon fa fa-caret-right"></i><fmt:message key="catgroup.title"/>
                    </a>
                    <b class="arrow"></b>
                </li>

                <li id="productgroup" class="">
                    <a href="<c:url value="/admin/productgroup/list.html"/>">
                        <i class="menu-icon fa fa-caret-right"></i><fmt:message key="productgroup.title"/>
                    </a>
                    <b class="arrow"></b>
                </li>

                <li id="product" class="">
                    <a href="<c:url value="/admin/product/list.html"/>">
                        <i class="menu-icon fa fa-caret-right"></i><fmt:message key="product.title"/>
                    </a>
                    <b class="arrow"></b>
                </li>
            </ul>
        </li>

        <li id="retailer">
            <a href="<c:url value="/admin/retailer/list.html"/>">
                <i class="menu-icon fa fa-caret-right"></i><fmt:message key="retailer.title"/>
            </a>
            <b class="arrow"></b>
        </li>

        <li id="changeHistory">
            <a href="<c:url value="/admin/retailer/changeHistory.html"/>">
                <i class="menu-icon fa fa-caret-right"></i><fmt:message key="retailer.change.history"/>
            </a>
            <b class="arrow"></b>
        </li>
        <li id="complaintype">
            <a href="<c:url value="/admin/complaintype/list.html"/>">
                <i class="menu-icon fa fa-caret-right"></i><fmt:message key="complaintype.management"/>
            </a>
            <b class="arrow"></b>
        </li>
        <li id="declinemetadata">
            <a href="<c:url value="/admin/declinemetadata/list.html"/>">
                <i class="menu-icon fa fa-caret-right"></i><fmt:message key="declinemetadata.management"/>
            </a>
            <b class="arrow"></b>
        </li>
        <li id="survey-manage" class="">
            <a href="#" class="dropdown-toggle">
                <i class="menu-icon fa fa-caret-right"></i>
                <fmt:message key="survey.management"/>
                <b class="arrow fa fa-angle-down"></b>
            </a>

            <b class="arrow"></b>

            <ul  class="submenu">

                <li id="surveytype" class="">
                    <a href="<c:url value="/admin/surveytype/list.html"/>">
                        <i class="menu-icon fa fa-caret-right"></i><fmt:message key="surveytype.title"/>
                    </a>
                    <b class="arrow"></b>
                </li>
                <li id="questiontype" class="">
                    <a href="<c:url value="/admin/questiontype/list.html"/>">
                        <i class="menu-icon fa fa-caret-right"></i><fmt:message key="questiontype.title"/>
                    </a>
                    <b class="arrow"></b>
                </li>

                <li id="survey" class="">
                    <a href="<c:url value="/admin/survey/list.html"/>">
                        <i class="menu-icon fa fa-caret-right"></i><fmt:message key="survey.title"/>
                    </a>
                    <b class="arrow"></b>
                </li>
                <li id="outletsegment" class="">
                    <a href="<c:url value="/admin/outletsegment/list.html"/>">
                        <i class="menu-icon fa fa-caret-right"></i><fmt:message key="outletsegment.title"/>
                    </a>
                    <b class="arrow"></b>
                </li>

            </ul>
        </li>
    </ul>
</li>
</security:authorize>
<security:authorize ifAllGranted="PERFECT_STORE">
<li id="perfect-store">
    <a href="#" class="dropdown-toggle">
        <i class="menu-icon fa fa-tag"></i>
                <span class="menu-text">
                    <fmt:message key="perfect.store"/>
                </span>
        <b class="arrow fa fa-angle-down"></b>
    </a>
    <b class="arrow"></b>
    <ul class="submenu">
        <li id="outletkpi">
            <a href="<c:url value="/admin/outletkpi/list.html"/>">
                <i class="menu-icon fa fa-caret-right"></i><fmt:message key="outletkpi.management"/>
            </a>
            <b class="arrow"></b>
        </li>
        <li id="exhibitregister">
            <a href="<c:url value="/admin/exhibitregister/list.html"/>">
                <i class="menu-icon fa fa-caret-right"></i><fmt:message key="exhibitregister.management"/>
            </a>
            <b class="arrow"></b>
        </li>
        <li id="salesinfo">
            <a href="<c:url value="/admin/salesinfo/list.html"/>">
                <i class="menu-icon fa fa-caret-right"></i><fmt:message key="salesinfo.management"/>
            </a>
            <b class="arrow"></b>
        </li>
        <li id="outlettransfer">
            <a href="<c:url value="/admin/outlettransfer/list.html"/>">
                <i class="menu-icon fa fa-caret-right"></i><fmt:message key="transferoutlet.management"/>
            </a>
            <b class="arrow"></b>
        </li>
    </ul>
</li>
</security:authorize>
<security:authorize ifAllGranted="EMER_MANAGEMENT">
<li id="emer">
    <a href="#" class="dropdown-toggle">
        <i class="menu-icon fa fa-list-alt"></i>
                <span class="menu-text">
                    <fmt:message key="emer"/>
                </span>
        <b class="arrow fa fa-angle-down"></b>
    </a>
    <b class="arrow"></b>
    <ul class="submenu">
        <li id="outletmodelgroup"><a href="<c:url value="/admin/outletmodelgroup/list.html"/>"><i
                class="menu-icon fa fa-caret-right"></i><fmt:message key="outletmodelgroup.management"/></a><b
                class="arrow"></b></li>
        <li id="outletmodel"><a href="<c:url value="/admin/outletmodel/list.html"/>"><i
                class="menu-icon fa fa-caret-right"></i><fmt:message key="outletmodel.management"/></a><b
                class="arrow"></b></li>
        <li id="routeschedule"><a href="<c:url value="/admin/routeschedule/list.html"/>"><i
                class="menu-icon fa fa-caret-right"></i><fmt:message key="routeschedule.management"/></a><b
                class="arrow"></b></li>
        <li id="outletuser-import"><a href="<c:url value="/admin/outletuser/import.html"/>"><i
                class="menu-icon fa fa-caret-right"></i><fmt:message key="label.outleruser.import"/></a><b
                class="arrow"></b></li>

        <li id="eie-manage" class="">
            <a href="#" class="dropdown-toggle"><i class="menu-icon fa fa-caret-right"></i><fmt:message
                    key="eie.management"/><b class="arrow fa fa-angle-down"></b></a>
            <b class="arrow"></b>
            <ul class="submenu">
                <li id="eie"><a href="<c:url value='/admin/eie/list.html'/>"><i
                        class="menu-icon fa fa-caret-right"></i><fmt:message key="eie.title"/></a><b class="arrow"></b>
                </li>
                <li id="zone"><a href="<c:url value='/admin/zone/list.html'/>"><i
                        class="menu-icon fa fa-caret-right"></i><fmt:message key="hotzone.title"/></a><b
                        class="arrow"></b></li>
                <li id="calculation"><a href="<c:url value='/admin/eie/calculation.html'/>"><i
                        class="menu-icon fa fa-caret-right"></i><fmt:message key="eie.calculation"/></a><b
                        class="arrow"></b></li>
            </ul>
        </li>

        <li id="outletregister-import"><a href="<c:url value="/admin/outletregister/import.html"/>"><i
                class="menu-icon fa fa-caret-right"></i><fmt:message key="outletregister.management"/></a><b
                class="arrow"></b></li>
        <li id="outletregister-list"><a href="<c:url value="/admin/outletregister/list.html"/>"><i
                class="menu-icon fa fa-caret-right"></i><fmt:message key="label.registered.outlets"/></a><b
                class="arrow"></b></li>

        <li id="employee-performance" class="">
            <a href="#" class="dropdown-toggle"><i class="menu-icon fa fa-caret-right"></i><fmt:message
                    key="employee.performance"/><b class="arrow fa fa-angle-down"></b></a>
            <b class="arrow"></b>
            <ul class="submenu">
                <li id="auditedoutlets"><a href="<c:url value='/admin/employeeperformance/auditedoutlets.html'/>"><i
                        class="menu-icon fa fa-caret-right"></i><fmt:message key="label.auditedoutlets"/></a><b
                        class="arrow"></b></li>
                <li id="auditedoutletmodelgroup"><a href="<c:url value='/admin/employeeperformance/auditedoutletmodel.html'/>"><i
                        class="menu-icon fa fa-caret-right"></i><fmt:message key="label.auditedoutletmodel"/></a><b
                        class="arrow"></b></li>
                <li id="n2pexhibition"><a href="<c:url value='/admin/employeeperformance/n2pexhibition.html'/>"><i
                        class="menu-icon fa fa-caret-right"></i><fmt:message key="label.n2pexhibition"/></a><b
                        class="arrow"></b></li>
                <li id="exhibitionimprovement"><a href="<c:url value='/admin/employeeperformance/exhibitionimprovement.html'/>"><i
                        class="menu-icon fa fa-caret-right"></i><fmt:message key="label.exhibitionimprovement"/></a><b
                        class="arrow"></b></li>
                <li id="auditedoutletmodelpassed"><a href="<c:url value='/admin/employeeperformance/auditedoutletmodelpassed.html'/>"><i
                        class="menu-icon fa fa-caret-right"></i><fmt:message key="label.auditedoutletmodelpassed"/></a><b
                        class="arrow"></b></li>
                <li id="auditedmhs"><a href="<c:url value='/admin/employeeperformance/auditedmhs.html'/>"><i
                        class="menu-icon fa fa-caret-right"></i><fmt:message key="label.auditedmhs"/></a><b
                        class="arrow"></b></li>
                <li id="auditedhotzone"><a href="<c:url value='/admin/employeeperformance/auditedhotzone.html'/>"><i
                        class="menu-icon fa fa-caret-right"></i><fmt:message key="label.auditedhotzone"/></a><b
                        class="arrow"></b></li>
                <li id="auditedfacing"><a href="<c:url value='/admin/employeeperformance/auditedfacing.html'/>"><i
                        class="menu-icon fa fa-caret-right"></i><fmt:message key="label.auditedfacing"/></a><b
                        class="arrow"></b></li>

            </ul>
        </li>
    </ul>
</li>
</security:authorize>
<security:authorize ifAllGranted="LOYALTY_MANAGEMENT">
<li id="retailer-loyalty">
    <a href="#" class="dropdown-toggle">
        <i class="menu-icon fa fa-file-o"></i>
                <span class="menu-text">
                    <fmt:message key="retailer.loyalty"/>
                </span>
        <b class="arrow fa fa-angle-down"></b>
    </a>
    <b class="arrow"></b>
    <ul class="submenu">
        <li id="campaign"><a href="<c:url value="/admin/loyalty/campaign/list.html"/> "><i
                class="menu-icon fa fa-caret-right"></i><fmt:message key="loyalty.campaign"/></a><b class="arrow"></b>
        </li>
        <li id="loyalty-gift"><a href="<c:url value="/admin/loyalty/gift/list.html"/>"><i
                class="menu-icon fa fa-caret-right"></i><fmt:message key="loyalty.gift"/></a><b class="arrow"></b></li>
        <li id="loyalty-rule"><a href="<c:url value="/admin/loyalty/rule/list.html"/>"><i
                class="menu-icon fa fa-caret-right"></i><fmt:message key="loyalty.rule"/></a><b class="arrow"></b></li>
        <li id="rlsupplier"><a href="<c:url value="/admin/rlsupplier/list.html"/>"><i
                class="menu-icon fa fa-caret-right"></i><fmt:message key="loyalty.supplier"/></a><b class="arrow"></b>
        </li>
        <li id="giftransfer"><a href="<c:url value="/admin/loyalty/giftransfer.html"/>"><i
                class="menu-icon fa fa-caret-right"></i><fmt:message key="rl.gift.transfer"/></a><b class="arrow"></b>
        </li>
        <li id="accumulate-history"><a href="<c:url value="/admin/accumulate/history.html"/>"><i
                class="menu-icon fa fa-caret-right"></i><fmt:message key="rl.point.history"/></a><b class="arrow"></b>
        </li>
        <li id="transfer-approved"><a href="<c:url value="/admin/gift/transfer/approved.html"/>"><i
                class="menu-icon fa fa-caret-right"></i><fmt:message key="rl.transfer.gift.approved"/></a><b
                class="arrow"></b></li>
        <li id="feedback-management" class="">
            <a href="#" class="dropdown-toggle"><i class="menu-icon fa fa-caret-right"></i><fmt:message
                    key="feedback.management"/><b class="arrow fa fa-angle-down"></b></a>
            <b class="arrow"></b>
            <ul class="submenu">
                <li id="feedback-retailer"><a href="<c:url value="/admin/loyalty/feedback/retailer.html"/>"><i
                        class="menu-icon fa fa-caret-right"></i><fmt:message key="rl.retailerloyalty"/></a><b
                        class="arrow"></b></li>
                <li id="feedback-supplier"><a href="<c:url value="/admin/loyalty/feedback/supplier.html"/>"><i
                        class="menu-icon fa fa-caret-right"></i><fmt:message key="rl.supplier"/></a><b
                        class="arrow"></b></li>
                <li id="feedback-promotion"><a href="<c:url value="/admin/loyalty/feedback/promotion.html"/>"><i
                        class="menu-icon fa fa-caret-right"></i><fmt:message key="rl.campaign"/></a><b
                        class="arrow"></b></li>
            </ul>
        </li>
        <li id="report-management" class="">
            <a href="#" class="dropdown-toggle"><i class="menu-icon fa fa-caret-right"></i><fmt:message
                    key="rl.report.management"/><b class="arrow fa fa-angle-down"></b></a>
            <b class="arrow"></b>
            <ul class="submenu">
                <li id="report-gift"><a href="<c:url value="/admin/loyalty/report/gift.html"/>"><i
                        class="menu-icon fa fa-caret-right"></i><fmt:message key="rl.gifts"/></a><b class="arrow"></b>
                </li>
            </ul>
        </li>

    </ul>
</li>
</security:authorize>
</ul>
<!-- /.nav-list -->

<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
    <i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state"
       data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
</div>
</div>

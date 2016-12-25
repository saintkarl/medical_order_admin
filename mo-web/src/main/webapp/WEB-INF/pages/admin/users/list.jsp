<%@ include file="/common/taglibs.jsp" %>
<html>

<head>
    <title><fmt:message key="user.list"/></title>
    <meta name="menu" content="users-manage"/>
    <meta name="gmenu" content="user"/>
    <link href="/themes/themeadmin/vendor/bootstrap-datepicker-master/dist/css/bootstrap-datepicker3.min.css"
          rel="stylesheet">
    <script type="text/javascript">

        $(document).ready(function () {

            $(".sbrole").click(function () {
                var check = $(this).is(':checked');
                console.log($(this).is(':checked'));
                console.log($(this).val());

                if (check = 'true') {
                    setCookie($(this).val(), 0);
                }
                if (check = 'false') {
                    setCookie($(this).val(), 1);
                }
                console.log(check);
            });
        });
    </script>
</head>
<body>

<section class="breadcrumbs ace-save-state" id="breadcrumbs">

    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> <fmt:message key="user.management"/> </a></li>
        <li class="active"><fmt:message key="user.list"/></li>
    </ol>
</section>

<c:url var="formUrl" value="/admin/user/list.html"/>

<section class="page-content">
    <div class="page-header">
        <h1>
            <fmt:message key="user.management"/>
            <small><i class="ace-icon fa fa-angle-double-right"></i> <fmt:message key="label.list"/></small>
        </h1>
    </div>
    <c:if test="${!empty errorMessage}">
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">x</span></button>
            <p>${errorMessage}</p>
        </div>
    </c:if>

    <c:if test="${!empty messageResponse}">
        <div class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">x</span></button>
            <p>${messageResponse}</p>
        </div>
    </c:if>


    <form:form action="${formUrl}" commandName="item" role="form" id="listForm" cssClass="form-horizontal">
        <div class="row">
            <div class="col-xs-12">
                <div class="widget-box table-filter">
                    <div class="widget-header">
                        <h4 class="widget-title"><fmt:message key="label.searchfilter"/></h4>

                        <div class="widget-toolbar">
                            <a href="#" data-action="collapse">
                                <i class="ace-icon fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <div class="widget-body">
                        <div class="widget-main">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><fmt:message key="label.name"/></label>
                                    <div class="col-sm-8">
                                        <div class="fg-line">
                                            <form:input path="pojo.userName" cssClass="form-control input-sm"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><fmt:message key="label.code"/></label>
                                    <div class="col-sm-8">
                                        <div class="fg-line">
                                            <form:input path="pojo.code" cssClass="form-control input-sm"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><fmt:message key="portal.user.role"/><span
                                            class="require-label"></span></label>
                                    <div class="col-sm-8">
                                        <div class="fg-line">
                                            <form:select path="roleCode" cssClass="form-control selectpicker"
                                                         data-live-search="true">
                                                <form:option value=""><fmt:message key="label.select"/></form:option>
                                                <form:options items="${roles}" itemLabel="name" itemValue="code"/>
                                            </form:select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><fmt:message key="user.management.userGroup"/><span
                                            class="require-label"></span></label>
                                    <div class="col-sm-8">
                                        <div class="fg-line">
                                            <form:select path="pojo.userGroup.userGroupId"
                                                         cssClass="form-control selectpicker" data-live-search="true">
                                                <form:option value=""><fmt:message key="label.select"/></form:option>
                                                <form:options items="${userGroups}" itemLabel="name"
                                                              itemValue="userGroupId"/>
                                            </form:select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="widget-bottom form-actions center">
                            <button id="btnApplySearch" type="button" class="btn btn-sm btn-success">
                                <fmt:message key="label.apply.search"/>
                                <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                            </button>
                        </div>
                    </div>
                </div>

                <div class="table-btn-controls">
                    <div class="pull-right tableTools-container">
                        <div class="dt-buttons btn-overlap btn-group">
                            <a userId="" flag="info"
                               class="dt-button buttons-colvis btn btn-white btn-primary btn-bold addEditUser">
                                <span>
                                    <i class="fa fa-plus-circle bigger-110 purple"></i>
                                    <span class="hidden"><fmt:message key="label.addnew"/></span>
                                </span>
                            </a>
                            <a href="<c:url value="/admin/user/import.html"/>"
                               class="dt-button buttons-colvis btn btn-white btn-primary btn-bold ">
                                <span>
                                    <i class="fa fa-file bigger-110 purple"></i>
                                    <span class="hidden"><fmt:message key="label.importuser"/></span>
                                </span>
                            </a>
                            <a id="confirmDelete" class="dt-button buttons-html5 btn btn-white btn-primary btn-bold">
                                <span>
                                    <i class="fa fa-trash-o bigger-110 pink"></i>
                                    <span class="hidden"><fmt:message key="label.delete"/></span>
                                </span>
                            </a>
                        </div>
                    </div>

                </div>

                <c:if test="${items.totalItems > 0}">
                    <div class="dataTables_wrapper form-inline no-footer">
                        <display:table name="items.listResult" cellspacing="0" cellpadding="0" requestURI="${formUrl}"
                                       partialList="true" sort="external" size="${items.totalItems}" defaultsort="3"
                                       id="tableList" excludedParams="checkList"
                                       pagesize="${items.maxPageItems}" export="false"
                                       class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                       style="margin: 3em 0 1.5em;">
                            <display:column headerClass="center select-cell" class="center select-cell" sortable="false"
                                            title="<label class='pos-rel'><input type='checkbox' class='ace'><span class='lbl'></span></label>">
                                <label class="pos-rel">
                                    <input name="checkList" type="checkbox" class="ace"
                                           value="${tableList.userId}"/>
                                    <span class="lbl"></span>
                                </label>
                            </display:column>
                            <display:column headerClass="text-left" property="userName" sortName="name" sortable="true"
                                            titleKey="user.management.username"/>

                            <display:column headerClass="text-left" property="code" sortName="name" sortable="true"
                                            titleKey="user.management.code"/>

                            <display:column headerClass="text-left" property="fullName" sortName="name" sortable="true"
                                            titleKey="user.management.fullname"/>

                            <display:column headerClass="col-actions" titleKey="label.action">
                                <a class="btn btn-xs btn-info addEditUser" flag="info" userId="${tableList.userId}">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                    <span class="hidden"><fmt:message key="label.edit"/></span>
                                </a>
                                <a class="btn btn-xs btn-info addEditUser" flag="role" userId="${tableList.userId}">
                                    <i class="ace-icon fa fa-users bigger-120"></i>
                                    <span class="hidden"><fmt:message key="role.title"/></span>
                                </a>
                                <a class="btn btn-xs btn-info addEditUser" flag="user-line"
                                   userId="${tableList.userId}">
                                    <i class="ace-icon fa fa-user bigger-120"></i>
                                    <span class="hidden"><fmt:message key="user.management.userLine"/></span>
                                </a>
                                <a type="button" class="btn btn-xs btn-info"
                                   href="<c:url value="/admin/user/userOutlet.html"/>?pojo.user.userId=${tableList.userId}">
                                    <i class="ace-icon fa fa-plus bigger-120"></i>
                                    <span class="hidden"><fmt:message key="user.management.userLine"/></span>
                                </a>
                                <button onclick="warningBeforeDelete('${tableList.userId}');"
                                        type="button" class="btn btn-xs btn-danger">
                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                </button>

                            </display:column>
                            <display:setProperty name="paging.banner.placement" value="bottom"/>
                            <display:setProperty name="paging.banner.item_name" value="user"/>
                            <display:setProperty name="paging.banner.items_name" value="users"/>
                        </display:table>
                    </div>
                </c:if>

                <input type="hidden" name="crudaction" id="crudaction"/>
                <input type="hidden" id="hiddenItem"/>
            </div>
        </div>
    </form:form>
</section>

<div class="modal fade" role="dialog" id="addModal"></div>

<script type="text/javascript">
    $(document).ready(function () {
        activeMenu();
        $("#btnApplySearch").click(function () {
            $("#itemForm").submit();
        });

        $('.addEditUser').click(function () {
            var $modal = $('#addModal'),
                    userId = $(this).attr('userId'),
                    flag = $(this).attr('flag');
            $modal.load('<c:url value="/ajax/admin/user/edit.html"/>', {'pojo.userId': userId, 'flag': flag, '_': new Date().getTime()}, function () {
                $modal.modal();
                registerSubmitPopup();
            });
        });

        function registerSubmitPopup() {
            $('select').selectpicker('refresh');
            initDate();
            $('#submit').click(function () {
                $("#addUserFormItem").submit();
            });

            $('#checkAllBoxRole, #checkAllBoxOutlet').click(function () {
                if ($(this).prop('checked')) {
                    $(this).closest('table').find('input[type="checkbox"]').prop('checked', true);
                } else {
                    $(this).closest('table').find('input[type="checkbox"]').prop('checked', false);
                }
            });

            $('#addUserFormItem').submit(function (e) {
                e.preventDefault();
                $("#crudation").val("insert-update");

                var formData = new FormData($(this)[0]);

                $.ajax({
                    cache: false,
                    type: "POST",
                    dataType: "html",
                    contentType: false,
                    processData: false,
                    data: formData,
                    url: "<c:url value='/ajax/admin/user/edit.html' />",
                    success: function (res) {
                        if (res.trim() == "success") {
                            document.location = "<c:url value="/admin/user/list.html"/>";
                        } else {
                            $("#addModal").html(res);
                            registerSubmitPopup();
                        }
                    }
                });
                return false;
            });
        }


        $("#confirmDelete").click(function () {
            deleteBySelectedIds({
                title: "<fmt:message key="delete.confirm.message.title"/>",
                text: "<fmt:message key="delete.confirm.message.content"/>",
                cancelButtonText: "<fmt:message key="label.cancel"/>",
                confirmButtonText: "<fmt:message key="label.delete"/>"
            }, function () {
                $('#crudaction').val('delete');
                $("#itemForm").submit();
            });
        });
    });
</script>

</body>
</html>
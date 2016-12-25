<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <title><fmt:message key="permission.management"/></title>
    <meta name="menu" content="users-manage"/>
    <meta name="gmenu" content="permission"/>
</head>
<body>

<section class="breadcrumbs ace-save-state" id="breadcrumbs">
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> <fmt:message key="permission.management"/></a></li>
        <li class="active"><fmt:message key="permission.title"/></li>
    </ol>
</section>

<c:url var="formUrl" value="/admin/permission/list.html"/>
<section class="page-content">
    <div class="page-header">
        <h1>
            <fmt:message key="permission.management"/>
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


    <form:form action="${formUrl}" commandName="items" role="form" id="listForm" cssClass="form-horizontal">
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
                                    <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.name"/></label>
                                    <div class="col-sm-9">
                                        <form:input path="pojo.name" placeholder="Name" cssClass="col-xs-10 col-sm-5"/>
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
                            <a permissionId="" class="dt-button buttons-colvis btn btn-white btn-primary btn-bold addEditPermission">
                                <span>
                                    <i class="fa fa-plus-circle bigger-110 purple"></i>
                                    <span class="hidden"><fmt:message key="label.addnew"/></span>
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
                                   partialList="true" sort="external" size="${items.totalItems }" defaultsort="3"
                                   id="tableList" excludedParams="checkList"
                                   pagesize="${items.maxPageItems}" export="false"
                                   class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer" style="margin: 3em 0 1.5em;">
                        <display:column headerClass="center select-cell" class="center select-cell" sortable="false"
                                        title="<label class='pos-rel'><input type='checkbox' class='ace'><span class='lbl'></span></label>">
                            <label class="pos-rel">
                                    <input name="checkList" type="checkbox" class="ace"
                                           value="${tableList.permissionId}"/>
                                    <span class="lbl"></span>
                            </label>
                        </display:column>
                        <display:column headerClass="text-left" property="name" sortName="name" sortable="true"
                                        titleKey="permission.name"/>
                        <display:column headerClass="text-left" property="code" sortName="code" sortable="true"
                                        titleKey="permission.code"/>
                        <display:column headerClass="col-actions" titleKey="label.action">
                            <a class="btn btn-xs btn-info addEditPermission"
                              permissionId="${tableList.permissionId}">
                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                            </a>

                            <button onclick="warningBeforeDelete('${tableList.permissionId}');"
                                    type="button" class="btn btn-xs btn-danger">
                                <i class="ace-icon fa fa-trash-o bigger-120"></i>
                            </button>

                        </display:column>
                        <display:setProperty name="paging.banner.placement" value="bottom"/>
                        <display:setProperty name="paging.banner.item_name" value="permission"/>
                        <display:setProperty name="paging.banner.items_name" value="permissions"/>
                    </display:table>
                </div>
            </c:if>

            <input type="hidden" name="crudaction" id="crudaction"/>
            <input type="hidden" id="hiddenItem"/>
            </div>
        </div>
    </form:form>
</section>

<div class="modal fade" role="dialog" id="addModel"></div>

<script type="text/javascript">
    $(document).ready(function () {
        $("#btnApplySearch").click(function () {
            $("#itemForm").submit();
        });

        $(".addEditPermission").click(function(){
            var $modal = $('#addModel');
            var permissionId = $(this).attr("permissionId");
            $modal.load('<c:url value="/ajax/admin/permission/edit.html"/>', {'pojo.permissionId' : permissionId, '_': new Date().getTime()}, function(){
                $modal.modal();
                registerSubmitPopup();
            });
        });

        function registerSubmitPopup(){

            $("#submit").click(function(){
                $("#addPermissionFormItem").submit();
            });

            $("#addPermissionFormItem").submit(function(e){
                e.preventDefault();

                $("#crudaction").val("insert-update");
                var formData = new FormData($(this)[0]);
                $.ajax({
                    cache: false,
                    type: "POST",
                    dataType: 'html',
                    contentType: false,
                    processData:false,
                    data: formData,
                    url:  "<c:url value="/ajax/admin/permission/edit.html"/>",
                    success: function(res){
                        if(res.trim() == "success"){
                            document.location = "<c:url value="/admin/permission/list.html"/>";
                        }else{
                            $("#addModel").html(res);
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
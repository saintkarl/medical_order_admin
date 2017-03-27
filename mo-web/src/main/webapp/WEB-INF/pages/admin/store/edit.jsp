<%@ include file="/common/taglibs.jsp" %>
<c:url var="url" value="/admin/store/edit.html"/>
<c:url var="backUrl" value="/admin/store/list.html"/>
<head>
    <title><fmt:message key="edit.store.title"/></title>
</head>
<body>

<div class="breadcrumbs ace-save-state" id="breadcrumbs">
    <ul class="breadcrumb">
        <li>
            <i class="ace-icon fa fa-home home-icon"></i>
            <a href="#"><fmt:message key="label.home"/></a>
        </li>
        <li>
            <a href="#"><fmt:message key="label.store.title"/></a>
        </li>
        <li class="active"><fmt:message key="edit.store.title"/></li>
    </ul>
</div>

<div class="page-content">
    <c:if test="${not empty messageResponse}">
        <div class="row">
            <div class="col-xs-12">

                <div class="alert alert-${alert} alert-dismissible fade in" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                        ${messageResponse}
                </div>
            </div>
        </div>
    </c:if>

    <div class="page-header">
        <div class="row">
            <h1 class="col-sm-8"><fmt:message key="edit.store.title"/> </h1>
            <div class="col-sm-3">
                <a href="${backUrl}" class="btn btn-grey">
                    <i class="ace-icon fa fa-arrow-left"></i>
                    <fmt:message key="label.back"/>
                </a>
                <button class="btn btn-success btnSave">
                    <i class="ace-icon fa fa-save"></i>
                    <fmt:message key="label.save"/>
                </button>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <form:form commandName="item" action="${formUrl}" method="post" id="itemForm" class="form-horizontal" novalidate="novalidate">

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.code"/></label>

                    <div class="col-sm-9">
                        <form:input path="pojo.code" cssClass="col-xs-10 col-sm-5"/>
                        <form:errors path="pojo.code" cssClass="red-text"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.name"/></label>

                    <div class="col-sm-9">
                        <form:input path="pojo.name" cssClass="col-xs-10 col-sm-5"/>
                        <form:errors path="pojo.name" cssClass="red-text"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.address"/></label>

                    <div class="col-sm-9">
                        <form:input path="pojo.address" cssClass="col-xs-10 col-sm-10"/>
                        <form:errors path="pojo.address" cssClass="red-text"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.tel"/></label>

                    <div class="col-sm-9">
                        <form:input path="pojo.tel" cssClass="col-xs-10 col-sm-5"/>
                        <form:errors path="pojo.tel" cssClass="red-text"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.email"/></label>

                    <div class="col-sm-9">
                        <form:input path="pojo.email" cssClass="col-xs-10 col-sm-5"/>
                        <form:errors path="pojo.email" cssClass="red-text"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.owner"/></label>

                    <div class="col-sm-9">
                        <form:input path="pojo.owner" cssClass="col-xs-10 col-sm-5"/>
                        <form:errors path="pojo.owner" cssClass="red-text"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.status"/></label>
                    <div class="col-sm-9 radio">
                        <label>
                            <input name="pojo.status" type="radio" class="ace" value="${Constants.ACTIVE}" <c:if test="${item.pojo.status eq Constants.ACTIVE || empty item.pojo.status}">checked</c:if>>
                            <span class="lbl"><fmt:message key="label.active"/></span>
                        </label>
                        <label>
                            <input name="pojo.status" type="radio" class="ace" value="${Constants.INACTIVE}" <c:if test="${item.pojo.status eq Constants.INACTIVE}">checked</c:if>>
                            <span class="lbl"><fmt:message key="label.inactive"/></span>
                        </label>
                        <form:errors path="pojo.status" cssClass="red-text"/>
                    </div>
                </div>

                <form:hidden path="pojo.storeId" />
                <form:hidden path="crudaction" value="insert-update"/>
            </form:form>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $('.btnSave').click(function(){
            $('#itemForm').submit();
        });
    });
</script>
</body>




<%@ include file="/common/taglibs.jsp" %>
<c:url var="url" value="/admin/tip/edit.html"/>
<c:url var="backUrl" value="/admin/tip/list.html"/>
<head>
    <title><fmt:message key="edit.tip.title"/></title>
</head>
<body>

<div class="breadcrumbs ace-save-state" id="breadcrumbs">
    <ul class="breadcrumb">
        <li>
            <i class="ace-icon fa fa-home home-icon"></i>
            <a href="#"><fmt:message key="label.home"/></a>
        </li>
        <li>
            <a href="#"><fmt:message key="label.tip.title"/></a>
        </li>
        <li class="active"><fmt:message key="edit.tip.title"/></li>
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
            <h1 class="col-sm-9"><fmt:message key="edit.tip.title"/> </h1>
            <div class="col-sm-2">
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
            <form:form commandName="item" action="${formUrl}" method="post" id="itemForm" class="form-horizontal" enctype="multipart/form-data" novalidate="novalidate">

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.title"/></label>

                    <div class="col-sm-9">
                        <form:input path="pojo.title" cssClass="col-xs-10 col-sm-5"/>
                        <form:errors path="pojo.title" cssClass="red-text"/>
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.description"/></label>

                    <div class="col-sm-9">
                        <form:textarea path="pojo.description" cssClass="col-xs-10 col-sm-5"/>
                        <form:errors path="pojo.title" cssClass="red-text"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.source"/></label>

                    <div class="col-sm-9">
                        <form:input path="pojo.source" cssClass="col-xs-10 col-sm-5"/>
                        <form:errors path="pojo.source" cssClass="red-text"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.tip.category"/></label>

                    <div class="col-sm-3">
                        <select name="pojo.tipCategory.tipCategoryId" class="chosen-select">
                            <c:forEach items="${tipCategories}" var="tipCategory">
                                <option value="${tipCategory.tipCategoryId}" <c:if test="${tipCategory.tipCategoryId eq item.pojo.tipCategory.tipCategoryId}">selected</c:if>>${tipCategory.name}</option>
                            </c:forEach>
                        </select>
                        <form:errors path="pojo.tipCategory.tipCategoryId" cssClass="red-text"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.thumnail"/></label>
                    <div class="col-sm-5">
                        <c:if test="${!empty item.pojo.thumbnail}">
                            <p>
                                <img class="file-preview-image" src="<c:url value="/repository${item.pojo.thumbnail}"/>"/>
                            </p>
                        </c:if>
                        <input name="img_file" multiple="" type="file" class="ace_file_input" title="<fmt:message key="drop.file.or.click.choose"/>"/>
                        <form:errors path="pojo.thumbnail" cssClass="red-text"/>
                    </div>
                    <div class="col-sm-4">

                    </div>
                </div>

                <h4 class="header green clearfix"><fmt:message key="label.content"/></h4>
                <textarea name="pojo.content" class="wysiwyg-editor" id="tip-content-editor">${item.pojo.content}</textarea>
                <div class="hr hr-double dotted"></div>

                <form:hidden path="pojo.tipId" />
                <form:hidden path="crudaction" value="insert-update"/>
            </form:form>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        CKEDITOR.replace('tip-content-editor');
        $('.btnSave').click(function(){
            $('#itemForm').submit();
        });
        <c:if test="${!empty item.pojo.thumbnail}">
        $('.ace_file_input').ace_file_input('show_file_list', ['<c:url value="/repository${item.pojo.thumbnail}"/>']);
        </c:if>
    });
</script>
</body>




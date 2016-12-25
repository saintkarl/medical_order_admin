<%@ include file="/common/taglibs.jsp"%>
<c:url var="formUrl" value="/admin/tip-category/list.html"></c:url>
<c:url var="ajaxAddEdit" value="/ajax/admin/tip-category/edit.html"></c:url>
<html>
<head>
    <title><fmt:message key="title.tip.category.management"/></title>
</head>
<body>
<div class="row m-b-2">
    <div class="col-sm-12">
        <ol class="breadcrumb">
            <li><a href="#"><fmt:message key="label.home"/> </a></li>
            <li class="active"><fmt:message key="title.tip.category.management"/></li>
        </ol>
    </div>
</div>
<div class="row col-sm-12">
    <c:if test="${not empty messageResponse}">
        <div class="alert alert-${alert} alert-dismissible fade in" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
                ${messageResponse}
        </div>
    </c:if>
</div>

<form:form commandName="items" action="${formUrl}" id="listForm" method="post" autocomplete="off" cssClass="form-horizontal">
    <div class="row">
        <div class="pull-xs-right">
            <button type="button" class="btn btn-sm btn-danger m-r-1 waves-effect waves-light" disabled="" id="deleteAll" onclick="warningBeforeDelete()">
                <i class="fa fa-trash-o"></i> <fmt:message key="label.delete"/>
            </button>
        </div>
    </div>
    <div class="table-responsive m-t-1">
        <table id="tableList" class="table table-striped table-bordered dataTable no-footer add-thead-inverse" style="margin: 1em 0 1.5em;">
            <thead>
            <tr>
                <th class="white_text column-check-box">
                    <fieldset class='form-group'><input type='checkbox' id='checkAll' class='check-box-element'><label for='checkAll'></label></fieldset>
                </th>
                <th class="white_text"><fmt:message key="label.code"/></th>
                <th class="white_text"><fmt:message key="label.name"/></th>
                <th class="white_text"><fmt:message key="label.description"/></th>
                <th class="white_text"><fmt:message key="title.tip.category.parent"/></th>
                <th class="white_text"><fmt:message key="label.status"/></th>
                <th class="white_text"><fmt:message key="label.action"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${items.listResult}" var="tableList" varStatus="status">
                <tr>
                    <td class="column-check-box">
                        <fieldset class="form-group">
                            <input type="checkbox" name="checkList" id="checkbox${status.index}" value="${tableList.catGroupId}" class="check-box-element">
                            <label for="checkbox${status.index}"></label>
                        </fieldset>
                    </td>
                    <td>
                        <span title="${tableList.code}"><str:truncateNicely upper="10">${tableList.code}</str:truncateNicely></span>
                    </td>
                    <td>
                        <span title="${tableList.name}"><str:truncateNicely upper="30">${tableList.name}</str:truncateNicely></span>
                    </td>
                    <td>
                        <span title="${tableList.longDescription}"><str:truncateNicely upper="65">${tableList.longDescription}</str:truncateNicely></span>
                    </td>
                    <td>
                        <span title="${tableList.parent.name}"><str:truncateNicely upper="30">${tableList.parent.name}</str:truncateNicely></span>
                    </td>
                    <td>
                        <c:choose>
                        <c:when test="${tableList.active}">
                        <span class="label label-success"><fmt:message key="label.active"/>
                            </c:when>
                            <c:otherwise>
                                <span class="label label-danger"><fmt:message key="label.inactive"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:url var="editURL" value="/ajax/admin/tip-category/edit.html" eventParams="pojo.catGroupId">
                            <sc:param name="pojo.catGroupId" value="${tableList.catGroupId}"/>
                        </c:url>
                        <a sc-url="${editURL}" class="btn btn-primary btn-sm waves-effect waves-light" onclick="update(this);"><i class="fa fa-pencil" aria-hidden="true"></i></a>
                        <a class="btn btn-danger btn-sm waves-effect btnDelete" data-toggle="tooltip" title="Delete" onclick="warningBeforeDelete(${tableList.catGroupId})">
                            <i class="fa fa-trash-o" aria-hidden="true"></i>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <form:hidden path="crudaction" id="crudaction"/>
</form:form>

<div class="row">
    <div class="col-sm-12 text-xs-right">
        <div class="fixed-action-btn active">
            <a class="btn-floating btn-large btn-primary waves-effect waves-light" onclick="update(this);"><i class="fa fa-plus"></i></a>
        </div>
    </div>
</div>

<div class="modal fade" id="addModal" tabindex="-1" role="dialog"></div>

<script type="text/javascript">
    var $modal = $('#addModal');
    $(document).ready(function() {
        $("#tableList").dataTable({
            "order": [[ 5, "asc" ],[ 1, "asc" ]],
            "columnDefs": [
                { "orderable": false, "targets": 0 }
            ]
        });
    });

    function update(btn){
        var url = $(btn).attr('sc-url');

        if(typeof url == "undefined"){
            url = '${ajaxAddEdit}'
        }

        $modal.load(url,{},function () {
            $modal.modal('toggle');
            registerSubmitPopup(url);
        });
    }

    function registerSubmitPopup(url) {
        $('#btnSave').click(function () {
            if(typeof url != 'undefined'){
                $('#formEdit').attr('action',url);
            }
            $('#formEdit').submit();
        });
        $('#formEdit').submit(function(e) {
            e.preventDefault();
            $("#crudactionEdit").val("insert-update");
            $.ajax({
                cache: false,
                type: "POST",
                dataType: "html",
                data: $(this).serialize(),
                url: $(this).attr('action'),
                success: function(res) {
                    if(res.trim() == '${WebConstants.REDIRECT_UPDATE}'){
                        $('#crudaction').val('${WebConstants.REDIRECT_UPDATE}');
                        $('#listForm').submit();
                    }else if(res.trim() == '${WebConstants.REDIRECT_INSERT}'){
                        $('#crudaction').val('${WebConstants.REDIRECT_INSERT}');
                        $('#listForm').submit();
                    }else if(res.trim() == '${WebConstants.REDIRECT_ERROR}'){
                        $('#crudaction').val('${WebConstants.REDIRECT_ERROR}');
                        $('#listForm').submit();
                    }else{
                        $("#addModal").html(res);
                        registerSubmitPopup();
                    }
                }
            });
            return false;
        });
    }
</script>
</body>
</html>

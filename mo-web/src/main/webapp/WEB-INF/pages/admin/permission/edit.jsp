<%@ include file="/common/taglibs.jsp" %>

<c:choose>
    <c:when test="${!empty messageResponse}">${messageResponse}</c:when>
    <c:otherwise>
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header bg-blue">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title"><fmt:message key="permission.management"/> </h4>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <c:if test="${!empty errorMessage}">
                            <div class="callout callout-danger">
                                <h4>Error!</h4>
                                <p>${errorMessage}</p>
                            </div>
                        </c:if>

                        <c:url value="/admin/permission/list.html" var="formUrl"/>
                        <form:form commandName="item" id="addPermissionFormItem" action="${formUrl}" novalidate="novalidate" enctype="multipart/form-data">
                            <div class="box-body">
                                <div class="form-group">
                                    <label><fmt:message key="permission.name"/><span class="require-label"></span> </label>
                                    <div class="fg-line">
                                        <form:input path="pojo.name" cssClass="form-control required"/>
                                        <form:errors path="pojo.name" cssClass="error-inline-validate"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label><fmt:message key="permission.code"/><span class="require-label"></span> </label>
                                    <div class="fg-line">
                                        <form:input path="pojo.code" cssClass="form-control required"/>
                                        <form:errors path="pojo.code" cssClass="error-inline-validate"/>
                                    </div>
                                </div>
                            </div>
                            <form:hidden path="pojo.permissionId" />
                            <form:hidden path="crudaction" value="insert-update"/>
                        </form:form>
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="box-footer p-r-25">
                        <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="label.cancel"/> </button>
                        <button type="button" id="submit" class="btn btn-primary"><fmt:message key="label.save"/></button>
                    </div>

                </div>
            </div>
        </div>
    </c:otherwise>
</c:choose>



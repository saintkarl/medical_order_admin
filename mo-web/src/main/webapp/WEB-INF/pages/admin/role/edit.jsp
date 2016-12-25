<%@ include file="/common/taglibs.jsp" %>

<c:choose>
    <c:when test="${!empty messageResponse}">${messageResponse}</c:when>
    <c:otherwise>
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header bg-blue">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title"><fmt:message key="role.management"/> </h4>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <c:if test="${!empty errorMessage}">
                            <div class="callout callout-danger">
                                <h4>Error!</h4>
                                <p>${errorMessage}</p>
                            </div>
                        </c:if>

                        <c:url value="/admin/role/list.html" var="formUrl"/>
                        <form:form commandName="item" id="addRoleFormItem" action="${formUrl}" novalidate="novalidate" enctype="multipart/form-data">
                            <div class="tabbable">
                                <ul class="nav nav-tabs" id="myTab">
                                    <li class="active">
                                        <a data-toggle="tab" href="#home">
                                            <i class="green ace-icon fa fa-home bigger-120"></i>
                                            <fmt:message key="role.info"/>
                                        </a>
                                    </li>

                                    <li>
                                        <a data-toggle="tab" href="#messages">
                                            <i class="green ace-icon fa fa-home bigger-120"></i>
                                            <fmt:message key="role.permission"/>
                                        </a>
                                    </li>
                                </ul>

                                <div class="tab-content">
                                    <div id="home" class="tab-pane fade in active">
                                        <div class="form-group">
                                            <label><fmt:message key="label.name"/><span class="require-label"></span></label>
                                            <div class="fg-line">
                                                <form:input path="pojo.name" cssClass="form-control input-sm"/>
                                                <form:errors path="pojo.name" cssClass="error-inline-validate"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label><fmt:message key="label.code"/><span class="require-label"></span></label>
                                            <div class="fg-line">
                                                <form:input path="pojo.code" cssClass="form-control input-sm"/>
                                                <form:errors path="pojo.code" cssClass="error-inline-validate"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div id="messages" class="tab-pane fade">
                                        <display:table name="permissions" cellspacing="0" cellpadding="0" requestURI="#"
                                                       partialList="true" sort="external" size="${fn:length(permissions)}" defaultsort="3"
                                                       id="tableList" excludedParams="checkList"
                                                       pagesize="${fn:length(permissions)}" export="false"
                                                       class="table  table-bordered table-hover" style="margin: 3em 0 1.5em;">
                                            <display:column headerClass="select-cell" class="select-cell" sortable="false"
                                                            title="<div class='checkbox'><label><input type='checkbox' id='checkAllBox' class='select-box'/><i class='input-helper'></i></label></div>">
                                                <div class="checkbox">
                                                    <c:set var="checkbox" value="" />
                                                    <label>
                                                        <input name="mapPermissions[${tableList.permissionId}]" <c:if test="${mapPermissions[tableList.permissionId] != null}">checked="true"</c:if>
                                                               type="checkbox" class="select-box" value="${tableList.permissionId}"/>
                                                        <i class="input-helper"></i>
                                                    </label>
                                                </div>
                                            </display:column>

                                            <display:column headerClass="text-left" property="name" sortName="name" sortable="true"
                                                            titleKey="permission.name"/>

                                            <display:setProperty name="paging.banner.onepage" value=""/>
                                            <display:setProperty name="paging.banner.all_items_found" value=""/>
                                            <display:setProperty name="paging.banner.item_name" value=""/>
                                            <display:setProperty name="paging.banner.items_name" value=""/>
                                        </display:table>
                                    </div>
                                </div>
                            </div>
                            <form:hidden path="pojo.roleId" />
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



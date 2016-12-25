<%@ include file="/common/taglibs.jsp" %>

<c:choose>
    <c:when test="${!empty messageResponse}">${messageResponse}</c:when>
    <c:otherwise>
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header bg-blue">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">
                        <fmt:message var="title" key="user.management"/>
                        <c:choose>
                            <c:when test="${item.flag eq Constants.ROLE_EDIT}">
                                <fmt:message var="title" key="role.management"/>
                            </c:when>
                            <c:when test="${item.flag eq Constants.USER_LINE_EDIT}">
                                <fmt:message var="title" key="user.management.userLine"/>
                            </c:when>
                        </c:choose>
                        ${title}
                    </h4>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <c:if test="${!empty errorMessage}">
                            <div class="callout callout-danger">
                                <h4>Error!</h4>
                                <p>${errorMessage}</p>
                            </div>
                        </c:if>
                        <c:url var="formUrl" value="/admin/user/edit.html"/>
                        <form:form commandName="item" id="addUserFormItem" action="${formUrl}" novalidate="novalidate" enctype="multipart/form-data">
                            <c:if test="${item.flag eq Constants.INFO_EDIT}">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label><fmt:message key="user.management.username"/><span class="require-label"></span></label>
                                            <div>
                                                <form:input path="pojo.userName" cssClass="form-control input-sm"/>
                                                <form:errors path="pojo.userName" cssClass="error-inline-validate"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label><fmt:message key="user.management.code"/><span class="require-label"></span></label>
                                            <div class="fg-line">
                                                <form:input path="pojo.code" cssClass="form-control input-sm"/>
                                                <form:errors path="pojo.code" cssClass="error-inline-validate"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label><fmt:message key="user.management.firstname"/><span class="require-label"></span></label>
                                            <div class="fg-line">
                                                <form:input path="pojo.firstName" cssClass="form-control input-sm"/>
                                                <form:errors path="pojo.firstName" cssClass="error-inline-validate"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label><fmt:message key="user.management.fullname"/><span class="require-label"></span></label>
                                            <div class="fg-line">
                                                <form:input path="pojo.fullName" cssClass="form-control input-sm"/>
                                                <form:errors path="pojo.fullName" cssClass="error-inline-validate"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label><fmt:message key="user.management.birthday"/><span class="require-label"></span></label>
                                            <div class="fg-line">
                                                <fmt:formatDate value="${item.pojo.userDemographicDTO.birthday}" var="dateString" pattern="dd/MM/yyyy" />
                                                <form:input id="birthday" path="pojo.userDemographicDTO.birthday" value="${dateString}" type="text" name="birthday" cssClass="form-control date-picker"/>
                                                <form:errors path="pojo.userDemographicDTO.birthday" cssClass="error-inline-validate"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label><fmt:message key="user.management.phonenumber"/><span class="require-label"></span></label>
                                            <div class="fg-line">
                                                <form:input path="pojo.phoneNumber" cssClass="form-control input-sm"/>
                                                <form:errors path="pojo.phoneNumber" cssClass="error-inline-validate"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label><fmt:message key="user.management.userGroup"/><span class="require-label"></span></label>
                                            <div class="fg-line">
                                                <form:select path="pojo.userGroup.userGroupId" cssClass="form-control selectpicker" data-live-search="true">
                                                    <form:options items="${userGroups}" itemLabel="name" itemValue="userGroupId"/>
                                                </form:select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label><fmt:message key="user.management.password"/><span class="require-label"></span></label>
                                            <div class="fg-line">
                                                <form:password path="pojo.password" cssClass="form-control input-sm"/>
                                                <form:errors path="pojo.password" cssClass="error-inline-validate"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label><fmt:message key="user.management.email"/><span class="require-label"></span></label>
                                            <div class="fg-line">
                                                <form:input path="pojo.email" cssClass="form-control input-sm"/>
                                                <form:errors path="pojo.email" cssClass="error-inline-validate"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label><fmt:message key="user.management.lastname"/><span class="require-label"></span></label>
                                            <div class="fg-line">
                                                <form:input path="pojo.lastName" cssClass="form-control input-sm"/>
                                                <form:errors path="pojo.lastName" cssClass="error-inline-validate"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label><fmt:message key="user.management.placeofbirth"/><span class="require-label"></span></label>
                                            <div class="fg-line">
                                                <form:input path="pojo.userDemographicDTO.placeOfBirth" cssClass="form-control input-sm"/>
                                                <form:errors path="pojo.userDemographicDTO.placeOfBirth" cssClass="error-inline-validate"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label><fmt:message key="city.title"/><span class="require-label"></span></label>
                                            <div class="fg-line">
                                                <form:select path="pojo.userDemographicDTO.cityOfBirth.cityId" cssClass="form-control selectpicker" data-live-search="true">
                                                    <form:options items="${cities}" itemLabel="name" itemValue="cityId"/>
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label><fmt:message key="user.management.sex"/></label>
                                            <div>
                                                <label  class="col-sm-4 radio radio-inline" style="margin-top: 0 !important;">
                                                    <input name="pojo.userDemographicDTO.sex" <c:if test="${item.pojo.userDemographicDTO.sex eq 'M'}">checked</c:if> type="radio" class="select-box" value="M" checked />
                                                    <i class="input-helper"></i>
                                                    <fmt:message key="label.male" />
                                                </label>
                                                <label class="col-sm-4 radio radio-inline" style="padding-top: 0;">
                                                    <input name="pojo.userDemographicDTO.sex" <c:if test="${item.pojo.userDemographicDTO.sex eq 'F'}">checked</c:if>  type="radio" class="select-box" value="F" />
                                                    <i class="input-helper"></i>
                                                    <fmt:message key="label.female" />
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${item.flag eq Constants.USER_LINE_EDIT}">
                                <div class="form-group">
                                    <%--<label><fmt:message key="user.management.userLine"/><span class="require-label"></span></label>--%>
                                    <div class="fg-line">
                                        <form:select path="pojo.userLineManagerDTO.lineManager.userId" cssClass="form-control selectpicker" data-live-search="true">
                                            <c:forEach items="${userLines}" var="userLine">
                                                <option value="${userLine.userId}" <c:if test="${userLine.userId == userLineManager.lineManager.userId}">selected</c:if> > ${userLine.userName}</option>
                                            </c:forEach>
                                            <%--<form:options items="${userLines}" itemLabel="userName" itemValue="userId"/>--%>
                                        </form:select>
                                    </div>
                                </div>
                                <input type="hidden" name="pojo.userLineManagerDTO.userLineManagerId" value="${userLineManager.userLineManagerId}"/>
                            </c:if>
                            <c:if test="${item.flag eq Constants.ROLE_EDIT}">
                                <div class="form-group">
                                    <%--<label><fmt:message key="user.management.role"/></label>--%>
                                    <div class="fg-line">
                                        <display:table name="roles" cellspacing="0" cellpadding="0" requestURI="#"
                                                       partialList="true" sort="external" size="${fn:length(roles)}" defaultsort="3"
                                                       id="tableList" excludedParams="checkList"
                                                       pagesize="${fn:length(roles)}" export="false"
                                                       class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer" style="margin: 3em 0 1.5em;">
                                            <display:column headerClass="select-cell" class="select-cell" sortable="false"
                                                            title="<div class='checkbox'><label><input type='checkbox' id='checkAllBoxRole' class='select-box'/><i class='input-helper'></i></label></div>">
                                                <div class="checkbox">
                                                    <c:set var="checkbox" value="" />

                                                    <label>
                                                        <input name="mapRoles[${tableList.roleId}]" <c:if test="${mapRoles[tableList.roleId] != null}">checked="true"</c:if>
                                                               type="checkbox" class="select-box" value="${tableList.roleId}"/>

                                                        <i class="input-helper"></i>
                                                    </label>
                                                </div>
                                            </display:column>
                                            <display:column headerClass="text-left" property="name" sortName="name" sortable="true"
                                                            titleKey="role.name" />

                                            <display:setProperty name="paging.banner.onepage" value=""/>
                                            <display:setProperty name="paging.banner.all_items_found" value=""/>
                                            <display:setProperty name="paging.banner.item_name" value=""/>
                                            <display:setProperty name="paging.banner.items_name" value=""/>
                                        </display:table>
                                    </div>
                                </div>
                            </c:if>

                        <input type="hidden" name="crudaction" id="crudation"/>
                        <form:hidden path="pojo.userId"/>
                        <form:hidden path="flag"/>
                        <form:hidden path="pojo.userDemographicDTO.userDemographicId" />
                        <form:hidden path="pojo.userLineManagerDTO.userLineManagerId" />
                    </form:form>
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="box-footer p-r-25">
                        <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="label.cancel"/> </button>
                        <button type="button" id="submit" form="addUserFormItem" class="btn btn-primary"><fmt:message key="label.save"/></button>
                    </div>

                </div>
            </div>
        </div>
    </c:otherwise>
</c:choose>

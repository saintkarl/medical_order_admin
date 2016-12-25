<%--
  Created by IntelliJ IDEA.
  User: hiepvv
  Date: 10/18/2016
  Time: 3:51 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ include file="/common/taglibs.jsp"%>
<sc:url var="formEditUrl" value="/ajax/admin/business/product-category/edit.html" eventParams="cc">
    <sc:param name="cc" value="<%=String.valueOf(System.currentTimeMillis())%>"/>
</sc:url>
<c:choose>
    <c:when test="${not empty messageResponse}">
        ${messageResponse}
    </c:when>
    <c:otherwise>
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        <fmt:message var="titleFormEdit" key="title.category.add"/>
                        <c:if test="${not empty item.pojo.catGroupId}">
                            <fmt:message var="titleFormEdit" key="title.category.edit"/>
                        </c:if>
                            ${titleFormEdit}
                    </h4>
                </div>
                <div class="modal-body">
                    <form:form commandName="item" action="${formEditUrl}" id="formEdit" method="post" autocomplete="off" cssClass="form-horizontal">
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="md-form">
                                    <form:input path="pojo.code" id="code" cssClass="form-control"/>
                                    <label for="code" class="active"><fmt:message key="title.category.code.label"/> </label>
                                    <form:errors path="pojo.code" cssClass="red-text"/>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="md-form">
                                    <form:input path="pojo.name" id="name" cssClass="form-control"/>
                                    <label for="name" class="active"><fmt:message key="title.category.name.label"/> </label>
                                    <form:errors path="pojo.name" cssClass="red-text"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="md-form">
                                    <form:textarea path="pojo.shortDescription" id="shortDescription" cssClass="form-control md-textarea" rows="5" cols="30" />
                                    <label for="shortDescription" class="active"><fmt:message key="title.category.shortDescription.label"/> </label>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <form:select path="pojo.parent.catGroupId" cssClass="mdb-select">
                                    <form:option value="" selected=""><fmt:message key="title.category.catgroup.label"/></form:option>
                                    <form:options items="${listCatGroups}" itemLabel="name" itemValue="catGroupId"/>
                                </form:select>
                                <label><fmt:message key="title.category.parent"/></label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="md-form">
                                    <form:textarea path="pojo.longDescription" id="longDescription" cssClass="form-control md-textarea" rows="5" cols="30"/>
                                    <label for="longDescription" class="active"><fmt:message key="title.category.longDescription.label"/></label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="md-form">
                                    <form:input path="pojo.displayOrder" id="displayOrder" cssClass="form-control" type="number"/>
                                    <label for="displayOrder" class="active"><fmt:message key="title.category.displayOrder"/></label>
                                    <form:errors path="pojo.displayOrder" cssClass="red-text"/>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <fmt:message key="label.status"/>
                                <div class="switch p-x-1 p-t-1">
                                    <label>
                                        <fmt:message key="label.inactive"/>
                                        <input type="checkbox" name="pojo.active" value="true" <c:if test="${item.pojo.active || (empty item.pojo.catGroupId && empty item.pojo.active)}">checked</c:if>>
                                        <span class="lever"></span> <fmt:message key="label.active"/>
                                    </label>
                                </div>
                            </div>
                        </div>

                        <form:hidden path="crudaction" id="crudactionEdit"/>
                    </form:form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="title.category.cancel"/></button>
                    <a type="button" class="btn btn-primary" id="btnSave"><fmt:message key="title.category.btnSave"/></a>
                </div>
            </div>
        </div>
    </c:otherwise>
</c:choose>



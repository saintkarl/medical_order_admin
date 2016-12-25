<%@ page language="java" isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
    <title><fmt:message key="errorPage.title"/></title>
</head>

<body id="error">
<div id="content-header">
    <h1><fmt:message key="errorPage.heading"/></h1>
</div>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="widget-box">
                <div class="widget-content">
                    <fmt:message key="errorPage.unexpectedMessage"></fmt:message>
                    <%=exception.toString()%>   <%-- bieu thuc cac kieu --%>
                    <%=exception.getMessage()%>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

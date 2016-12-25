<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/taglibs.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <%@ include file="/common/meta.jsp" %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title><fmt:message key="webapp.name"/>&trade;&nbsp;-&nbsp;<decorator:title/></title>
    <script type="text/javascript" language="javascript">
        var contextPath = '${ers:getAppURL(pageContext.request)}';
    </script>
    <link rel="stylesheet" href="<c:url value="/themes/styles/bootstrap.min.css"/> ">
    <link rel="stylesheet" href="<c:url value="/themes/styles/font-awesome.min.css"/> ">
    <link rel="stylesheet" href="<c:url value="/themes/styles/ionicons/2.0.1/css/ionicons.min.css"/> ">
    <link rel="stylesheet" href="<c:url value="/themes/styles/AdminLTE.min.css"/> ">
    <link rel="stylesheet" href="<c:url value="/themes/plugins/iCheck/square/blue.css"/> ">
    <decorator:head/>
</head>
<body <decorator:getProperty property="body.id" writeEntireProperty="true"/> class='signin hold-transition login-page <decorator:getProperty property="body.class"
                                                                                                                  writeEntireProperty="true"/>'>
<div class="page_wrapper">
    <c:set var="currPage" scope="request"><decorator:getProperty property="body.id"/></c:set>
    <jsp:include page="/common/login_header.jsp"/>
    <div class="body_wrapper">
        <decorator:body/>
    </div>
    <div id="footer">
        <jsp:include page="/common/login_footer.jsp"/>
    </div>
</div>

<script src="<c:url value="/themes/plugins/jQuery/jQuery-2.1.4.min.js"/>"></script>
<script src="<c:url value="/themes/script/bootstrap.min.js"/>"></script>
<script src="<c:url value="/themes/plugins/iCheck/icheck.min.js"/>"></script>
<script>
    $(function () {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
    });
</script>
</body>
</html>

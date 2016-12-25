<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <meta name="description" content="<fmt:message key="login.page"/>"/>

    <!-- Page title -->
    <title><fmt:message key="login.page"/></title>

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="<c:url value='/themes/ace_1.4/dist/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/themes/font-awesome-4.6.3/css/font-awesome.css'/>">

    <!-- text fonts -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300"/>

    <!-- ace styles -->
    <link rel="stylesheet" href="<c:url value='/themes/ace_1.4/dist/css/ace.min.css'/>"/>


    <link rel="stylesheet" href="<c:url value='/themes/ace_1.4/dist/css/ace-rtl.min.css'/>"/>

    <!--[if lte IE 8]>
    <script src="<c:url value="/themes/html5shiv/html5shiv.min.js"/>"></script>
    <script src="<c:url value="/themes/respond/respond.min.js"/>"></script>
    <![endif]-->

    <script src="<c:url value="/themes/ace_1.4/jquery/2.1.4/jquery.min.js"/>"></script>
    <!--[if IE]>
    <script src="<c:url value="/themes/ace_1.4/jquery/1.11.3/jquery.min.js"/>"></script>
    <![endif]-->

</head>

<body class="login-layout" <decorator:getProperty property="body.id" writeEntireProperty="true"/><decorator:getProperty
        property="body.class"
        writeEntireProperty="true"/>>
<!--[if lt IE 7]>
<p class="alert alert-danger">You are using an <strong>outdated</strong> browser. Please upgrade your browser to improve
    your experience.</p>
<![endif]-->

<div class="color-line"></div>

<decorator:body/>
</body>
</html>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/taglibs.jsp" %>
<html>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title><fmt:message key="login.title"/></title>

    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="<c:url value="/themes/bootstrap/css/bootstrap.min.css"/>">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="<c:url value="/themes/font-awesome-4.6.3/css/font-awesome.min.css"/>">
    <!-- Ionicons -->
    <link rel="stylesheet" href="<c:url value="/themes/ionicons-2.0.1/css/ionicons.min.css"/>">
    <!-- Theme style -->
    <link rel="stylesheet" href="<c:url value="/themes/admin_lte/css/AdminLTE.min.css"/>">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
     folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="<c:url value="/themes/admin_lte/css/skins/_all-skins.min.css"/>">
    <!-- iCheck -->
    <link rel="stylesheet" href="<c:url value="/themes/plugins/iCheck/square/blue.css"/>">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="<c:url value="/themes/html5shiv/html5shiv.min.js"/>"></script>
    <script src="<c:url value="/themes/respond/respond.min.js"/>"></script>
    <![endif]-->

<body class="hold-transition login-page" <decorator:getProperty property="body.id" writeEntireProperty="true"/><decorator:getProperty property="body.class"
                                                                                                   writeEntireProperty="true"/>>
<!--[if lt IE 7]>
<p class="alert alert-danger">You are using an <strong>outdated</strong> browser. Please upgrade your browser to improve your experience.</p>
<![endif]-->
<div class="color-line"></div>
<decorator:body/>
<!-- jQuery 2.2.3 -->
<script src="<c:url value="/themes/plugins/jQuery/jquery-2.2.3.min.js"/>"></script>

<!-- Bootstrap 3.3.6 -->
<script src="<c:url value="/themes/bootstrap/js/bootstrap.min.js"/>"></script>
<!-- iCheck -->
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
</body></html>

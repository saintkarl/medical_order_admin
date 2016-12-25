<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/taglibs.jsp" %>
<html>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>E-Retailer Login Page</title>

    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <!--<link rel="shortcut icon" type="image/ico" href="favicon.ico" />-->

    <!-- Vendor styles -->
    <link rel="stylesheet" href="<c:url value='/themes/themeadmin/vendor/fontawesome/css/font-awesome.css'/>">
    <link rel="stylesheet" href="<c:url value='/themes/themeadmin/vendor/metisMenu/dist/metisMenu.css'/>">
    <link rel="stylesheet" href="<c:url value='/themes/themeadmin/vendor/animate.css/animate.css'/>">
    <link rel="stylesheet" href="<c:url value='/themes/themeadmin/vendor/bootstrap/dist/css/bootstrap.css'/>">

    <!-- App styles -->
    <link rel="stylesheet" href="<c:url value='/themes/themeadmin/fonts/pe-icon-7-stroke/css/pe-icon-7-stroke.css'/>">
    <link rel="stylesheet" href="<c:url value='/themes/themeadmin/fonts/pe-icon-7-stroke/css/helper.css'/>">
    <link rel="stylesheet" href="<c:url value='/themes/themeadmin/styles/style.css'/>">
    <script src="<c:url value="/themes/themeadmin/vendor/jquery/dist/jquery.min.js"/>"></script>

    <style type="text/css">.jqstooltip { position: absolute;left: 0px;top: 0px;visibility: hidden;background: rgb(0, 0, 0) transparent;background-color: rgba(0,0,0,0.6);filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000);-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000)";color: white;font: 10px arial, san serif;text-align: left;white-space: nowrap;padding: 5px;border: 1px solid white;z-index: 10000;}.jqsfield { color: white;font: 10px arial, san serif;text-align: left;}</style></head>
<body class="blank" <decorator:getProperty property="body.id" writeEntireProperty="true"/><decorator:getProperty property="body.class"
                                                                                                   writeEntireProperty="true"/>>

<!-- Simple splash screen-->
<div class="splash" style="display: none;"> <div class="color-line"></div><div class="splash-title"><h1>Homer - Responsive Admin Theme</h1><p>Special AngularJS Admin Theme for small and medium webapp with very clean and aesthetic style and feel. </p><img src="images/loading-bars.svg" width="64" height="64"> </div> </div>
<!--[if lt IE 7]>
<p class="alert alert-danger">You are using an <strong>outdated</strong> browser. Please upgrade your browser to improve your experience.</p>
<![endif]-->

<div class="color-line"></div>

<decorator:body/>




<!-- Vendor scripts -->

<script src="<c:url value="/themes/themeadmin/vendor/jquery-ui/jquery-ui.min.js"/>"></script>
<script src="<c:url value="/themes/themeadmin/vendor/slimScroll/jquery.slimscroll.min.js"/>"></script>
<script src="<c:url value="/themes/themeadmin/vendor/bootstrap/dist/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/themes/themeadmin/vendor/metisMenu/dist/metisMenu.min.js"/>"></script>
<script src="<c:url value="/themes/themeadmin/vendor/iCheck/icheck.min.js"/>"></script>
<script src="<c:url value="/themes/themeadmin/vendor/sparkline/index.js"/>"></script>

<!-- App scripts -->
<script src="<c:url value="/themes/themeadmin/scripts/homer.js"/>"></script>


</body></html>

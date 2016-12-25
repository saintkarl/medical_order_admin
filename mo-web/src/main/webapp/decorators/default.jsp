<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>FCV&trade;&nbsp;-&nbsp;<decorator:title/></title>

    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="<c:url value="/themes/styles/bootstrap.min.css"/>">
    <%--<link rel="stylesheet"shref="<c:url value="/themes/plugins/bootstrap-table-master/src/bootstrap-table.css"/>">--%>

    <link rel="stylesheet" href="<c:url value="/themes/styles/jquery-ui-1.10.4.custom.min.css"/>">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="<c:url value="/themes/styles/ionicons/2.0.1/css/ionicons.min.css"/>">

    <%--<!-- iCheck -->--%>
    <%--<link rel="stylesheet" href="<c:url value="/themes/plugins/iCheck/flat/blue.css"/>">--%>
    <%--<!-- Morris chart -->--%>
    <%--<link rel="stylesheet" href="<c:url value="/themes/plugins/morris/morris.css"/>">--%>
    <%--<!-- jvectormap -->--%>
    <%--<link rel="stylesheet" href="<c:url value="/themes/plugins/jvectormap/jquery-jvectormap-1.2.2.css"/>">--%>
    <!-- Date Picker -->

    <link rel="stylesheet" href="<c:url value="/themes/plugins/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.css"/>">

    <link rel="stylesheet" href="<c:url value="/themes/plugins/daterangepicker/daterangepicker.css"/>">
    <!-- Daterange picker -->
    <link rel="stylesheet" href="<c:url value="/themes/plugins/daterangepicker/daterangepicker-bs3.css"/>">
    <%--<link rel="stylesheet" href="<c:url value="/themes/plugins/datepicker/datepicker3.css"/>">--%>
<%--<!-- bootstrap wysihtml5 - text editor -->--%>
    <%--<link rel="stylesheet" href="<c:url value="/themes/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" />">--%>
    <!-- Theme style -->
    <link rel="stylesheet" href="<c:url value="/themes/dist/css/AdminLTE_v1.0.css"/>">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="<c:url value="/themes/dist/css/skins/_all-skins.min.css"/>">

    <link rel="stylesheet" href="<c:url value="/themes/css/style_v1.13.css"/>">
    <link rel="stylesheet" href="<c:url value="/themes/css/fixedHeaderTableStyle_v1.0.css"/>">
    <!-- jQuery 2.1.4 -->
    <script src="<c:url value="/themes/plugins/jQuery/jQuery-2.1.4.min.js" />"></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="<c:url value='/themes/javascript/html5shiv/3.7.3/html5shiv.min.js'/>"></script>
    <script src="<c:url value='/themes/javascript/respond/1.4.2/respond.min.js'/>"></script>
    <![endif]-->

    <link href="<c:url value="/themes/plugins/bootstrap-switch/bootstrap-switch.css"/>" rel="stylesheet">
    <decorator:head/>
</head>

<body <decorator:getProperty property="body.id" writeEntireProperty="true"/> class='hold-transition skin-blue sidebar-mini <decorator:getProperty property="body.class"
                                                                                                                                            writeEntireProperty="false"/>'>
<section>

    <div class="main">

        <div class="headerbar">

            <div class="header-top">
                <jsp:include page="/common/header.jsp"/>
            </div>
            <!-- header-right -->


        </div>
        <!-- headerbar -->
        <div class="wrapper">
            <jsp:include page="/common/sidebar.jsp"/>
            <decorator:body/>
        </div>
        <div id="footer">
            <jsp:include page="/common/footer.jsp"></jsp:include>
        </div>
    </div>
    <!-- mainpanel -->


</section>

<!-- jQuery UI 1.11.4 -->
<%--<script src="<c:url value="/themes/plugins/jQueryUI/jquery-ui.min.js" />"></script>--%>
<script src="<c:url value="/themes/script/jquery-ui.js" />"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.5 -->
<script src="<c:url value="/themes/script/bootstrap.min.js" />"></script>
<!-- Morris.js charts -->
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="<c:url value="/themes/plugins/morris/morris.min.js" />"></script>--%>
<!-- Sparkline -->
<%--<script src="<c:url value="/themes/plugins/sparkline/jquery.sparkline.min.js" />"></script>--%>
<!-- jvectormap -->
<%--<script src="<c:url value="/themes/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js" />"></script>--%>
<%--<script src="<c:url value="/themes/plugins/jvectormap/jquery-jvectormap-world-mill-en.js" />"></script>--%>
<!-- jQuery Knob Chart -->
<%--<script src="<c:url value="/themes/plugins/knob/jquery.knob.js" />"></script>--%>
<!-- Bootbox -->
<script src="<c:url value="/scripts/bootbox.min.js" />"></script>
<!-- Bootstrap WYSIHTML5 -->
<script src="<c:url value="/themes/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" />"></script>
<!-- date-range-picker -->

<%--Use this version for vietnamese language--%>
<script src="<c:url value="/themes/plugins/daterangepicker/moment_vi.js" />"></script>

<%--Do not use below minimized version for moment JS. B/c above unminimized version has modified for the Vietnamese language.--%>
<%--<script src="<c:url value="/themes/plugins/datepicker/bootstrap-datepicker.js" />"></script>--%>
<script src="<c:url value="/themes/plugins/daterangepicker/daterangepicker_v1.0.js"/>"></script>
<script src="<c:url value="/themes/plugins/daterangepicker/jquery.daterangepicker_v1.0.js"/>"></script>
<script src="<c:url value="/themes/plugins/fixedTable/jquery.fixedheadertable.js"/>"></script>

<script type="text/javascript" src="<c:url value="/themes/plugins/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"/>" charset="UTF-8"></script>
<!-- Slimscroll -->
<script src="<c:url value="/themes/plugins/slimScroll/jquery.slimscroll.min.js" />"></script>
<!-- FastClick -->
<script src="<c:url value="/themes/plugins/fastclick/fastclick.min.js" />"></script>
<!-- AdminLTE App -->
<script src="<c:url value="/themes/dist/js/app.min.js" />"></script>
<%--<!-- AdminLTE for demo purposes -->
<script src="<c:url value="/themes/dist/js/demo.js" />"></script>--%>
<%--<!-- fullCalendar 2.2.5 -->--%>
<%--<script src="<c:url value="/themes/plugins/daterangepicker/moment.min.js" />"></script>--%>
<%--<script src="<c:url value="/themes/plugins/fullcalendar/fullcalendar.min.js"/>"></script>--%>
<script src="<c:url value="/themes/plugins/bootstrap-switch/bootstrap-switch.js"/>"></script>
<%--<script src="<c:url value="/themes/plugins/bootstrap-table-master/src"/>"></script>--%>
<script src="<c:url value="/themes/script/jquery.validate.js"/>"></script>
<script src="<c:url value="/themes/script/jquery-validate.bootstrap-tooltip.js"/>"></script>
<script src="<c:url value="/scripts/global.source.v_1.8.js"/>"></script>
</body>
</html>
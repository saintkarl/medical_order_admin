<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="/common/meta.jsp" %>
    <title><fmt:message key="webapp.name"/>&trade;&nbsp;-&nbsp;<decorator:title/></title>
        <!-- bootstrap & fontawesome -->
        <link rel="stylesheet" href="<c:url value="/themes/ace_1.4/dist/css/bootstrap.min.css"/>">
        <link rel="stylesheet" href="<c:url value="/themes/font-awesome-4.6.3/css/font-awesome.min.css"/>">

        <!-- page specific plugin styles -->
        <%--<link rel="stylesheet" href="<c:url value="/themes/ace_1.4/components/select2-4.0.3/dist/css/select2.min.css"/>">--%>
        <!-- text fonts -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300"/>

        <!-- page specific plugin styles -->
        <link rel="stylesheet" href="<c:url value="/themes/ace_1.4/components/_mod/jquery-ui.custom/jquery-ui.custom.min.css"/>">
        <link rel="stylesheet" href="<c:url value="/themes/ace_1.4/components/fullcalendar/dist/fullcalendar.min.css"/>">
        <link rel="stylesheet" href="<c:url value="/themes/ace_1.4/components/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css"/>">
        <%--<link rel="stylesheet" href="<c:url value="/themes/ace_1.4/components/bootstrap-select/dist/css/bootstrap-select.css"/>">--%>
        <link rel="stylesheet" href="<c:url value="/themes/ace_1.4/components/chosen/chosen.min.css"/>">

        <%--vendor theme--%>
        <link href="<c:url value="/themes/plugins/kartik-bootstrap-fileinput/css/fileinput.min.css"/>" rel="stylesheet" >
        <%--<link href="<c:url value="/themes/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css"/>" rel="stylesheet" >--%>

        <!-- ace styles -->
        <link rel="stylesheet" href="<c:url value="/themes/ace_1.4/dist/css/ace.min.css"/>">
        <link rel="stylesheet" href="<c:url value="/themes/ace_1.4/dist/css/ace-skins.min.css"/>">
        <link rel="stylesheet" href="<c:url value="/themes/ace_1.4/dist/css/ace-rtl.min.css"/>">

        <%--popup detele--%>
        <link href="<c:url value="/themes/plugins/sweetalert/lib/sweet-alert.css"/>" rel="stylesheet">

        <link rel="stylesheet" href="<c:url value="/themes/mo/css/global.css"/>">



        <!-- inline styles related to this page -->

        <!-- ace settings handler -->
        <script src="<c:url value="/themes/ace_1.4/dist/js/ace-extra.min.js"/>"></script>
        <!--[if !IE]> -->
        <script src="<c:url value="/themes/ace_1.4/jquery/2.1.4/jquery.min.js"/>"></script>
        <!-- <![endif]-->
        <!--[if IE]>
        <script src="<c:url value="/themes/ace_1.4/jquery/1.11.3/jquery.min.js"/>"></script>
        <![endif]-->
        <script src="<c:url value='/themes/mo/js/global.js'/>"></script>
    <decorator:head/>

<body class="no-skin">
<!-- Header -->
<jsp:include page="/themes/ace_1.4/include/header.jsp"/>
<div class="main-container ace-save-state" id="main-container">
    <jsp:include page="/themes/ace_1.4/include/sidebar.jsp"/>
        <div class="main-content">
            <div class="main-content-inner">
                <decorator:body/>
            </div>
        </div>

        <div class="footer">
            <div class="footer-inner">
                <div class="footer-content">
                            <span class="bigger-120">
                                <span class="blue bolder"><fmt:message key="copyright.text"/></span>
                            </span>
                </div>
            </div>
        </div>

        <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
            <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
        </a>
</div>
<!-- Footer-->
<jsp:include page="/themes/ace_1.4/include/footer.jsp"/>

<script>
    function warningBeforeDelete(id){
        bootbox.confirm('<fmt:message key="confirm.delete"/>', '<fmt:message key="confirm.delete.msg"/>', function(r) {
            if(r){
                if(id != null && id != undefined){
                    $("<input type='hidden' name='checkList' value='"+id+"'>").appendTo($("#listForm"));
                    $("#crudaction").val("delete");
                    $("#listForm").submit();
                }
            }
        });
    }
</script>

</body>
</html>
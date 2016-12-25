<%@page trimDirectiveWhitespaces="true"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="login.title"/></title>
</head>

<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="center">
                    <h1>
                        <i class="ace-icon fa fa-leaf green"></i>
                        <span class="red"><fmt:message key="tips.and.tricks"/></span>
                        <span class="white" id="id-text2"><fmt:message key="application"/></span>
                    </h1>
                    <h4 class="blue" id="id-company-text">&copy; <fmt:message key="company.name"/></h4>
                </div>

                <div class="space-6"></div>

                <div class="login-container">
                    <div class="position-relative">
                        <div id="login-box" class="login-box visible widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header blue lighter bigger">
                                        <i class="ace-icon fa fa-coffee green"></i>
                                        <fmt:message key="please.enter.your.information"/>
                                    </h4>

                                    <div class="space-6"></div>

                                    <form name="loginForm" action="<c:url value="/j_security_check"/>" method="post">
                                        <fieldset>
                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input name="j_username" id="username" type="text" class="form-control" placeholder="<fmt:message key="username"/>" />
															<i class="ace-icon fa fa-user"></i>
														</span>
                                            </label>

                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input name="j_password" id="password" type="password" class="form-control" placeholder="<fmt:message key="password"/>" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
                                            </label>


                                            <span style="color:red;">
                                                <c:choose>
                                                    <c:when test="${param.error == 1}">
                                                        <fmt:message key="login.error.passwordmissedmatch"/>
                                                    </c:when>
                                                    <c:when test="${param.error == 2}">
                                                        <fmt:message key="login.error.sessionexpired"/>
                                                    </c:when>
                                                </c:choose>
                                            </span>
                                            <div class="space"></div>
                                            <div class="clearfix">
                                                <label class="inline">
                                                    <input type="checkbox" class="ace" />
                                                    <span class="lbl"> <fmt:message key="remember.me"/></span>
                                                </label>

                                                <button id="submit" type="submit" class="width-35 pull-right btn btn-sm btn-primary">
                                                    <i class="ace-icon fa fa-key"></i>
                                                    <span class="bigger-110"><fmt:message key="login"/></span>
                                                </button>
                                            </div>

                                            <div class="space-4"></div>
                                        </fieldset>
                                    </form>

                                    <%--<div class="social-or-login center">--%>
                                    <%--<span class="bigger-110">Or Login Using</span>--%>
                                    <%--</div>--%>

                                    <%--<div class="space-6"></div>--%>

                                    <%--<div class="social-login center">--%>
                                    <%--<a class="btn btn-primary">--%>
                                    <%--<i class="ace-icon fa fa-facebook"></i>--%>
                                    <%--</a>--%>

                                    <%--<a class="btn btn-info">--%>
                                    <%--<i class="ace-icon fa fa-twitter"></i>--%>
                                    <%--</a>--%>

                                    <%--<a class="btn btn-danger">--%>
                                    <%--<i class="ace-icon fa fa-google-plus"></i>--%>
                                    <%--</a>--%>
                                    <%--</div>--%>
                                </div><!-- /.widget-main -->

                                <div class="toolbar clearfix">
                                    <div>
                                        <%--<a href="#" data-target="#forgot-box" class="forgot-password-link">--%>
                                        <%--<i class="ace-icon fa fa-arrow-left"></i>--%>
                                        <%--I forgot my password--%>
                                        <%--</a>--%>
                                    </div>

                                    <div>
                                        <%--<a href="#" data-target="#signup-box" class="user-signup-link">--%>
                                        <%--I want to register--%>
                                        <%--<i class="ace-icon fa fa-arrow-right"></i>--%>
                                        <%--</a>--%>
                                    </div>
                                </div>
                            </div><!-- /.widget-body -->
                        </div><!-- /.login-box -->

                        <div id="forgot-box" class="forgot-box widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header red lighter bigger">
                                        <i class="ace-icon fa fa-key"></i>
                                        Retrieve Password
                                    </h4>

                                    <div class="space-6"></div>
                                    <p>
                                        Enter your email and to receive instructions
                                    </p>

                                    <form>
                                        <fieldset>
                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email" class="form-control" placeholder="Email" />
															<i class="ace-icon fa fa-envelope"></i>
														</span>
                                            </label>

                                            <div class="clearfix">
                                                <button type="button" class="width-35 pull-right btn btn-sm btn-danger">
                                                    <i class="ace-icon fa fa-lightbulb-o"></i>
                                                    <span class="bigger-110">Send Me!</span>
                                                </button>
                                            </div>
                                        </fieldset>
                                    </form>
                                </div><!-- /.widget-main -->

                                <div class="toolbar center">
                                    <a href="#" data-target="#login-box" class="back-to-login-link">
                                        Back to login
                                        <i class="ace-icon fa fa-arrow-right"></i>
                                    </a>
                                </div>
                            </div><!-- /.widget-body -->
                        </div><!-- /.forgot-box -->

                        <div id="signup-box" class="signup-box widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header green lighter bigger">
                                        <i class="ace-icon fa fa-users blue"></i>
                                        New User Registration
                                    </h4>

                                    <div class="space-6"></div>
                                    <p> Enter your details to begin: </p>

                                    <form>
                                        <fieldset>
                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email" class="form-control" placeholder="Email" />
															<i class="ace-icon fa fa-envelope"></i>
														</span>
                                            </label>

                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="Username" />
															<i class="ace-icon fa fa-user"></i>
														</span>
                                            </label>

                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="Password" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
                                            </label>

                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="Repeat password" />
															<i class="ace-icon fa fa-retweet"></i>
														</span>
                                            </label>

                                            <label class="block">
                                                <input type="checkbox" class="ace" />
                                                <span class="lbl">
															I accept the
															<a href="#">User Agreement</a>
														</span>
                                            </label>

                                            <div class="space-24"></div>

                                            <div class="clearfix">
                                                <button type="reset" class="width-30 pull-left btn btn-sm">
                                                    <i class="ace-icon fa fa-refresh"></i>
                                                    <span class="bigger-110">Reset</span>
                                                </button>

                                                <button type="button" class="width-65 pull-right btn btn-sm btn-success">
                                                    <span class="bigger-110">Register</span>

                                                    <i class="ace-icon fa fa-arrow-right icon-on-right"></i>
                                                </button>
                                            </div>
                                        </fieldset>
                                    </form>
                                </div>

                                <div class="toolbar center">
                                    <a href="#" data-target="#login-box" class="back-to-login-link">
                                        <i class="ace-icon fa fa-arrow-left"></i>
                                        Back to login
                                    </a>
                                </div>
                            </div><!-- /.widget-body -->
                        </div><!-- /.signup-box -->
                    </div><!-- /.position-relative -->

                    <%--<div class="navbar-fixed-top align-right">--%>
                    <%--<br />--%>
                    <%--&nbsp;--%>
                    <%--<a id="btn-login-dark" href="#">Dark</a>--%>
                    <%--&nbsp;--%>
                    <%--<span class="blue">/</span>--%>
                    <%--&nbsp;--%>
                    <%--<a id="btn-login-blur" href="#">Blur</a>--%>
                    <%--&nbsp;--%>
                    <%--<span class="blue">/</span>--%>
                    <%--&nbsp;--%>
                    <%--<a id="btn-login-light" href="#">Light</a>--%>
                    <%--&nbsp; &nbsp; &nbsp;--%>
                    <%--</div>--%>
                </div>
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.main-content -->
</div><!-- /.main-container -->


<%--<div class="login-container">--%>
<%--<div class="row">--%>
<%--<div class="col-md-12">--%>
<%--<div class="text-center m-b-md">--%>
<%--<h3><fmt:message key="please.login"/></h3>--%>
<%--<small><fmt:message key="application.welcome"/></small>--%>

<%--</div>--%>
<%--<div class="hpanel forum-box">--%>
<%--<div class="panel-body">--%>
<%--<form name="loginForm" action="<c:url value="/j_security_check"/>" method="post">--%>
<%--<div class="form-group">--%>
<%--<label class="control-label" for="username">Username</label>--%>
<%--<input type="text" placeholder="" title="Please enter you username" required="" value="" name="j_username" id="username" class="form-control">--%>
<%--<span class="help-block small">Your unique username to app</span>--%>

<%--</div>--%>
<%--<div class="form-group">--%>
<%--<label class="control-label" for="password">Password</label>--%>
<%--<input type="password" title="Please enter your password" placeholder="" required="" value="" name="j_password" id="password" class="form-control">--%>
<%--<span class="help-block small">Your strong password</span>--%>

<%--</div>--%>

<%--<fmt:message var="login" key="login"/>--%>
<%--<input type="submit" name="buttonLogin" value="${login}" class="btn btn-success btn-block" id="submit"/>--%>
<%--<span style="color:red;">--%>
<%--<c:choose>--%>
<%--<c:when test="${param.error == 1}">--%>
<%--<fmt:message key="login.error.passwordmissedmatch"/>--%>
<%--</c:when>--%>
<%--<c:when test="${param.error == 2}">--%>
<%--<fmt:message key="login.error.sessionexpired"/>--%>
<%--</c:when>--%>
<%--</c:choose>--%>
<%--</span>--%>

<%--</form>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="row">--%>
<%--<div class="col-md-12 text-center">--%>
<%--<fmt:message key="copyright.text"/>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<script type="text/javascript">
    if('ontouchstart' in document.documentElement) document.write("<script src='<c:url value="/themes/ace_1.4/components/_mod/jquery.mobile.custom/jquery.mobile.custom.min.js"/>'>"+"<"+"/script>");
</script>

<script type="text/javascript">
    jQuery(function($) {
        $(document).on('click', '.toolbar a[data-target]', function(e) {
            e.preventDefault();
            var target = $(this).data('target');
            $('.widget-box.visible').removeClass('visible');//hide others
            $(target).addClass('visible');//show target
        });
    });



    //you don't need this, just used for changing background
    jQuery(function($) {
        $('#btn-login-dark').on('click', function(e) {
            $('body').attr('class', 'login-layout');
            $('#id-text2').attr('class', 'white');
            $('#id-company-text').attr('class', 'blue');

            e.preventDefault();
        });
        $('#btn-login-light').on('click', function(e) {
            $('body').attr('class', 'login-layout light-login');
            $('#id-text2').attr('class', 'grey');
            $('#id-company-text').attr('class', 'blue');

            e.preventDefault();
        });
        $('#btn-login-blur').on('click', function(e) {
            $('body').attr('class', 'login-layout blur-login');
            $('#id-text2').attr('class', 'white');
            $('#id-company-text').attr('class', 'light-blue');

            e.preventDefault();
        });

    });
</script>


<script type="text/javascript">
    $(document).ready(function(){
        $('#submit').click(function(){
            if($('#username').val() == '' || $('#password').val() == '') {
                alert('<fmt:message key="login.error.empty.username.or.password"/>');
                return false;
            }
            return true;
        });
    });
</script>
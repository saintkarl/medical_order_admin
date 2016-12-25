<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Login to ${ACEGI_SECURITY_PORTAL_KEY.name} system.</title>
</head>
<body class="hold-transition login-page">
<div class="login-box-body">
    <p class="login-box-msg">Sign in to start your session</p>
    <form action="<c:url value="/j_security_check"/>" method="post">
        <div class="form-group has-feedback">
            <input type="text" name="j_username" class="form-control" placeholder="Email">
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
            <input type="password" class="form-control" name="j_password" placeholder="Password">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            <input id="inputPortal" name="j_portal" type="hidden" class="form-control" value="${ACEGI_SECURITY_PORTAL_KEY.portalId}"/>

        </div>
        <div class="row">
            <div class="col-xs-8">
                <div class="checkbox icheck">
                    <label>
                        <input type="checkbox"> Remember Me
                    </label>
                </div>
            </div>
            <div class="col-xs-4">
                <button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
            </div>
        </div>
    </form>

    <div class="social-auth-links text-center">
        <p>- OR -</p>
        <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign in using Facebook</a>
        <a href="#" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-google-plus"></i> Sign in using Google+</a>
    </div>

    <a href="#">I forgot my password</a><br>
    <a href="register.html" class="text-center">Register a new membership</a>

</div>
</body>
</html>

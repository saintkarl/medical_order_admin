<%@ include file="/common/taglibs.jsp"%>
<page:applyDecorator name="error">
    <head>
        <title><fmt:message key="404.title"/></title>
        <meta name="heading" content="<fmt:message key='404.title'/>"/>
    </head>
    <c:url var="homeUrl" value="/admin/index.html"/>

    <body>
    <!-- Content Header (Page header) -->
    <section class="breadcrumbs ace-save-state" id="breadcrumbs">
        <h1>
            404 Error Page
        </h1>
        <ol class="breadcrumb">
            <li><a href="${homeUrl}"><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active">404 error</li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="page-content">
        <div class="error-page">
            <h2 class="headline text-yellow"> 404</h2>
            <div class="error-content">
                <h3><i class="fa fa-warning text-yellow"></i> Oops! Page not found.</h3>
                <p>
                    We could not find the page you were looking for.
                    Meanwhile, you may <a href="${homeUrl}">return to dashboard</a> or try using the search form.
                </p>
            </div><!-- /.error-content -->
        </div><!-- /.error-page -->
    </section><!-- /.content -->
    </body>


</page:applyDecorator>
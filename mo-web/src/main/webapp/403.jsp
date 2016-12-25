<%@ include file="/common/taglibs.jsp"%>

<page:applyDecorator name="error">

    <head>
        <title><fmt:message key="403.title"/></title>
        <meta name="heading" content="<fmt:message key='403.title'/>"/>
    </head>
    <c:url value="/" var="homeUrl"/>
    <!-- Content Header (Page header) -->
    <section class="breadcrumbs ace-save-state" id="breadcrumbs">
        <h1>
            403 Error Page
        </h1>
        <ol class="breadcrumb">
            <li><a href="${homeUrl}"><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active">403 error</li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="page-content">

        <div class="error-page">
            <h2 class="headline text-red">403</h2>
            <div class="error-content">
                <h3><i class="fa fa-warning text-red"></i> Oops! Something went wrong.</h3>
                <p>
                    We will work on fixing that right away.
                    Meanwhile, you may <a href="${homeUrl}">return to dashboard</a> or try using the search form.
                </p>
            </div>
        </div><!-- /.error-page -->

    </section><!-- /.content -->
</page:applyDecorator>
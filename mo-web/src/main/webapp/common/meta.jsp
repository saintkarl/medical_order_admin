<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<c:choose>
    <c:when test="${tat:isAndroidTablet(pageContext.request)}">
        <meta name="viewport" content="width=800, user-scalable=yes"/>
    </c:when>
    <c:otherwise>
        <meta name="viewport" content="width=device-width, user-scalable=yes"/>
    </c:otherwise>
</c:choose>

<!-- HTTP 1.1 -->
<meta http-equiv="Cache-Control" content="no-store"/>
<!-- HTTP 1.0 -->
<meta http-equiv="Pragma" content="no-cache"/>
<!-- Prevents caching at the Proxy Server -->
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="generator" content="Retirement Co., Ltd" />
<link rel="icon" href="<c:url value="/favicon.ico"/>"/>

<meta name="viewport" content="width=device-width, user-scalable=no" />

<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
	response.setContentType("text/html; charset=iso-8859-1");
	response.setHeader("Cache-Control", "no-cache, private, must-revalidate, max-stale=0, post-check=0, pre-check=0 no-store"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setDateHeader("Expires", 0); // Proxies.
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <title></title>
  <link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css" />
</head>
<body>
<jsp:useBean id="date" class="java.util.Date" />
<fmt:formatDate value="${date}" pattern="yyyy" var="currentYear" />
<!-- Home -->
<div data-role="page" id="wd_main.m">
    <div data-theme="b" data-role="header">
        <h3>WebDaptive Mobile</h3>
		<a href="<c:url value='/j_spring_security_logout'/>" rel="external" class="ui-btn-right">Logout</a>
    </div>
	<div data-role="content" class="ui-content" role="main">
		<ul data-role="listview" data-inset="true" class="ui-listview ui-listview-inset ui-corner-all ui-shadow">
 		<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMINS')">
		  <li data-corners="false" data-shadow="false" data-iconshadow="true" data-wrapperels="div" data-icon="arrow-r" data-iconpos="right" data-theme="c" class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-first-child ui-btn-up-c"><div class="ui-btn-inner ui-li"><div class="ui-btn-text"><a href="#samples" class="ui-link-inherit"><i class="icon-doc"></i>Mobile Sample Applications</a></div><span class="ui-icon ui-icon-arrow-r ui-icon-shadow">&nbsp;</span></div></li>
 		</sec:authorize>
		  <li data-corners="false" data-shadow="false" data-iconshadow="true" data-wrapperels="div" data-icon="arrow-r" data-iconpos="right" data-theme="c" class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-btn-up-c"><div class="ui-btn-inner ui-li"><div class="ui-btn-text"><a href="#maps" class="ui-link-inherit"><i class="icon-location"></i> Maps &amp; Directions to QAT Global Facilties</a></div><span class="ui-icon ui-icon-arrow-r ui-icon-shadow">&nbsp;</span></div></li>
 		<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMINS')">
		  <li data-corners="false" data-shadow="false" data-iconshadow="true" data-wrapperels="div" data-icon="arrow-r" data-iconpos="right" data-theme="c" class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-last-child ui-btn-up-c"><div class="ui-btn-inner ui-li"><div class="ui-btn-text"><a href="#links" class="ui-link-inherit"><i class="icon-link"></i>Training</a></div><span class="ui-icon ui-icon-arrow-r ui-icon-shadow">&nbsp;</span></div></li>
 		</sec:authorize>
		</ul>
	</div>

    <div data-theme="b" data-role="footer">
        <h4>Copyright &copy; QAT Global 1995-<c:out value="${currentYear}" />. All rights reserved.</h4>
    </div>
</div>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMINS')">
<!-- Samples -->
<div data-role="page" id="samples" data-url="samples">
  <div data-theme="b" data-role="header">
    <h1>Mobile Sample Applications</h1>
    <a href="./wd_main.m.jsp" rel="external" data-icon="back" data-iconpos="left" data-direction="reverse" class="ui-btn-right">Back</a> </div>
  <div data-role="content">
    <ul data-role="listview" data-inset="true">
      <li><a href="../../county/fetchCounties?view=mobile" rel="external">Counties MVC</a></li>
    </ul>
  </div>
</div>
</sec:authorize>

<!-- Maps & Directions -->
<div data-role="page" id="maps" data-url="maps">
  <div data-theme="b" data-role="header">
    <h1>Maps &amp; Directions to QAT Global Facilties</h1>
    <a href="./wd_main.m.jsp" data-icon="back" data-iconpos="left" data-direction="reverse" class="ui-btn-right">Back</a> </div>
  <div data-role="content">
    <ul data-role="listview" data-inset="true">
      <li><a href="http://goo.gl/maps/afLzb" rel="external">QAT Global Headquarters</a></li>
      <li><a href="http://goo.gl/maps/WfXD8" rel="external">QAT Global Brazil Office</a></li>
    </ul>
  </div>
</div>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMINS')">
 <!-- Training -->
<div data-role="page" id="links" data-url="links">
  <div data-theme="b" data-role="header">
    <h1>Training</h1>
    <a href="./wd_main.m.jsp" data-icon="back" data-iconpos="left" data-direction="reverse" class="ui-btn-right">Back</a> </div>
  <div data-role="content">
    <ul data-role="listview" data-inset="true">
      <li><a rel="external" href="http://jquerymobile.com/">jQuery Mobile</a></li>
      <li><a rel="external" href="http://jquery.com/">jQuery Core</a></li>
    </ul>
  </div>
</div>
</sec:authorize>
  <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
  <script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
</body>
</html>

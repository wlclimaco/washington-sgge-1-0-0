<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>


<%-- Default Title --%>
<title><decorator:title></decorator:title></title>

<%-- Stylesheets imported via javascript-components.xml --%>
<s:iterator value="cssImports">
	<s:if test="%{absolutePath}">
		<link rel="stylesheet" href="<s:property value='path'/>"></link>
	</s:if>
	<s:else>
		<link rel="stylesheet"
			href="<%=request.getContextPath()%><s:property value='path'/>"></link>
	</s:else>
</s:iterator>


<%-- JavaScript settings imported via javascript-components.xml --%>
<s:iterator value="jsSettings">
	<s:if test="%{absolutePath}">
		<script src="<s:property value="path"/>"></script>
	</s:if>
	<s:else>
		<script src="<%=request.getContextPath()%><s:property value="path"/>"></script>
	</s:else>
</s:iterator>

<%-- Conditional IE CSS Adjustments --%>
<!--[if IE]>
<link rel="stylesheet" type="text/css" href="<s:url includeParams="none" value="/styles/ie.css"/>"></link>
<![endif]-->

<%-- Include Page Head (primarily for page title) --%>
<decorator:head />


</head>
<body>
<jsp:include page="user_info.jsp" flush="true"/>
<%-- START Main Document --%>
<div class="fullscreen"></div>
<div id="doc1" class="application lighting"><%-- START Header 1 --%>
<div id="hd-user" class="yui-g"><%-- Breadcrumbs (currently hard-coded) --%>
<div class="logo yui-u first" style="width: 49%">
<h1></h1>
<h2>&raquo;&nbsp;<s:text name="breadcrumbs.label.mlc" /></h2>
</div>
<%-- Login Menu --%>
<div class="yui-g user-settings">
<ul class="link-list">
	<li><strong><s:text name="header.label.welcome" /> <a id="user-name" class="alist" href="<s:url includeParams="none" value="profile/ajax.list.action"/>" ></a></strong></li>
	<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
		<li><a id="systemsettings" class="alist" href="<s:url includeParams="none" value="systemsettings/ajax.list.action"/>"
			title="<s:text name="header.label.settings"/>"><s:text

			name="header.label.settings" /></a></li>
	</sec:authorize>

	<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
		<li><a id="users-list" class="alist" href="<s:url includeParams="none" value="user/ajax.list.action"/>"
			title="<s:text name="header.label.users"/>"><s:text
			name="header.label.users" /></a></li>
	</sec:authorize>

	<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support')">
		<li><a id="eventhistory" class="alist" href="<s:url includeParams="none" value="process/ajax.processPageAction.action"/>"
			title="<s:text name="header.label.eventhistory"/>"><s:text
			name="header.label.eventhistory" /></a></li>
	</sec:authorize>

	<li class="last">
	<!--  <a id="logout-link"
		href="<s:url value="/pages/login.jsp" includeParams="none"/>"
		title="<s:text name="header.label.logout"/>"><s:text
		name="header.label.logout" /></a> -->
		<a id="header-logout" href="<c:url value='/j_spring_security_logout'/>"><s:text name="header.label.logout" /></a>
	</li>
</ul>
</div>

</div>
<%-- END Header 1 --%>

<%-- START Header 2--%>
<div id="hd" class="yui-gc">
<%-- START Main application menu --%>

<div class="yui-u first">
<div id="sensus-menu">
<div class="bd">
<ul class="first-of-type">
	<li><a id="menu-dashboard" class="alist"
		href="<s:url includeParams="none" value="dashboard/ajax.list.action"/>"
		title="<s:text name="menu.main.dashboard"/>"><s:text
		name="menu.main.dashboard" /></a></li>
	<li><a id="menu-smartpoints" class="alist"
		href="<s:url includeParams="none" value="smartpoint/ajax.list.action"/>"
		title="<s:text name="menu.main.smartpoint"/>"><s:text
		name="menu.main.smartpoint" /></a></li>

	<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support')">
		<li><a id="menu-groups" class="alist"
			href="<s:url includeParams="none" value="group/ajax.list.action"/>"
			title="<s:text name="menu.main.group"/>"><s:text
			name="menu.main.group" /></a></li>
		<li><a id="menu-schedules" class="alist"
			href="<s:url includeParams="none" value="schedule/ajax.list.action"/>"
			title="<s:text name="menu.main.schedule"/>"><s:text
			name="menu.main.schedule"/></a></li>
	</sec:authorize>

	<li><a id="menu-analytics" class="alist"
		href="<s:url includeParams="none" value="analytics/ajax.list.action?tb=&ty=1&g=&di=&de=&dt=&dv=1"/>"
		title="<s:text name="menu.main.analytics"/>"><s:text
		name="menu.main.analytics" /></a></li>
</ul>
</div>
</div>
</div>
<%-- END Main application menu --%>


<%-- START Search Box--%>
<div class="yui-gc">
     <form id="search-form-container" class="rounded">
           <div class="search-input-container yui-u first">
	           <div id="search-label-container">
		           <a tabindex="1" href="#label-options" class="fg-button fg-button-icon-right ui-widget ui-state-default" id="search-label">
		           <span class="ui-icon ui-icon-triangle-1-s"></span><input id="search-type" value="12" type="hidden"><s:text name="search.label.poleid"/></a>
				   <div id="label-options" class="hidden">
		               <ul>
		                   <li class="hide"><a href="#" class="action-option" id="search-pole-id"><input value="12" type="hidden"><s:text name="search.label.poleid"/></a></li>
		                   <li><a href="#" class="action-option" id="flexNetID"><input value="36" type="hidden"><s:text name="search.label.flexnetid"/></a></li>
		               </ul>
		          </div>
			  </div>
	          <input type="text" id="search-text" value="" class="validate[required, custom[specialCharsCustomSearch]]"/>
          </div>
          <div id="search-submit-container" class="yui-u">
             <a href="#" id="search-submit" class="button"><s:text name="search.label.submit"/></a>
			 <!-- <a href="search/ajax.advanced.action" id="advanced" class="white nodeco alist"><s:text name="search.label.advanced"/></a>&nbsp;| -->
             <a href="search/ajax.list.action" id="saved" class="white nodeco alist"><s:text name="search.label.saved"/></a>
		 </div>
	</form>
</div>
<%-- END Search Box--%>
</div>
<%-- END Header 2--%>

<%-- START Page Content --%>
<div id="bd" class="content-container">
<jsp:include page="../scripts/pages/dashboard_main.js.jsp" flush="true"/>
<div id="load"><%-- Page Content --%> <decorator:body /></div>

</div>

<%-- Detail Map and Action Dialog Placeholders --%>

 <%-- Progress Bar --%>
<div id="loading" style="display: none;">
<h5><s:text name="widgets.progress.updatingresults" /></h5>
<div id="progress-bar"></div>
</div>
<div id="action-dialog" style="display: none"></div>
<div id="control-light-dialog" style="display: none"></div>
<div id="monitor-dialog" style="display: none"></div>
<div id="action-dialog-lrp" style="display: none"></div>
<div id="remove-lrp" style="display: none"></div>
<div id="abort-lrp" style="display: none"></div>

<%-- System Messaging Bar --%>
<div id="system-messaging"></div>
<%-- END Page Content --%>

<%-- START Footer --%>
<div id="ft" class="yui-g">
<div class="yui-u first"><%-- Footer address section --%>
<div class="vcard">
<div class="fn org">&copy;<span id="current-year"></span>
<s:text name="company.org" /></div>
<div class="adr">
<p class="version">SLC v<span id="version-number"></span> Build <span id="build-number"></span></p>
</div>
</div>
</div>
<%-- Footer Links --%>
<div class="yui-u footer-links">

</div>
<%-- END Footer --%>
</div>



<!-- Scripts imported via javascript-components.xml -->
<s:iterator value="jsImports">
	<s:if test="%{absolutePath}">
		<script src="<s:property value="path"/>"></script>
	</s:if>
	<s:else>
		<script src="<%=request.getContextPath()%><s:property value="path"/>"></script>
	</s:else>
</s:iterator>

<%-- END Main Document --%>
</body>
</html>
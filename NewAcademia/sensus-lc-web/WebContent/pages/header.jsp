<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
		<div id="hd-user" class="yui-g"><%-- Breadcrumbs (currently hard-coded) --%>
			<div class="logo yui-u first" style="width: 49%">
				<h1></h1>
				<h2>&raquo;&nbsp;<s:message code="breadcrumbs.label.mlc" /></h2>
			</div>
			<%-- Login Menu --%>
			<div class="yui-g user-settings">
				<ul class="link-list">
					<li><strong><s:message code="header.label.welcome" /> <a id="user-name" class="alist" href="profile"></a></strong></li>
					<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
					<li><a id="systemsettings" class="alist" href="settings/"	title="<s:message code="header.label.settings"/>"><s:message code="header.label.settings" /></a></li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
					<li><a id="users-list" class="alist" href="user" title="<s:message code="header.label.users"/>"><s:message code="header.label.users" /></a></li>
					</sec:authorize>
					<li class="last">
						<a id="header-logout" href="j_spring_security_logout"><s:message code="header.label.logout" /></a>
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
							<li><a id="menu-dashboard" class="alist" href="dashboard" title="<s:message code="menu.main.dashboard"/>"><s:message code="menu.main.dashboard" /></a></li>
							<li><a id="menu-smartpoints" class="alist" href="light" title="<s:message code="menu.main.smartpoint"/>"><s:message code="menu.main.smartpoint" /></a></li>
							<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support')">
							<li><a id="menu-groups" class="alist" href="group"	title="<s:message code="menu.main.group"/>"><s:message code="menu.main.group" /></a></li>
							</sec:authorize>
							<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator','ROLE_Role.Customer Support')">
							<li><a id="menu-systemintelligence" class="alist" href="systemintelligence/" title="<s:message code="menu.main.systemIntelligence"/>"><s:message code="menu.main.systemIntelligence"/></a></li>
							</sec:authorize>
							<li><a id="menu-analytics" class="alist" href="analytics/?tb=&ty=1&g=&di=&de=&dt=&dv=1"	title="<s:message code="menu.main.analytics"/>"><s:message code="menu.main.analytics" /></a></li>
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
								<span class="ui-icon ui-icon-triangle-1-s"></span><input id="search-type" value="12" type="hidden"><s:message code="search.label.poleid"/>
							</a>
							<div id="label-options" class="hidden">
								<ul>
									<li class="hide">
										<a href="#" class="action-option" id="search-pole-id"><input value="12" type="hidden"><s:message code="search.label.poleid"/></a>
									</li>
									<li>
										<a href="#" class="action-option" id="flexNetID"><input value="36" type="hidden"><s:message code="search.label.flexnetid"/></a>
									</li>
								</ul>
							</div>
						</div>
						<input type="text" id="search-text" value="" class="validate[required, custom[specialCharsCustomSearch]]"/>
					</div>
					<div id="search-submit-container" class="yui-u">
						<a href="#" id="search-submit" class="button"><s:message code="search.label.submit"/></a>
						<!-- <a href="search/ajax.advanced.action" id="advanced" class="white nodeco alist"><s:message code="search.label.advanced"/></a>&nbsp;| -->
						<a href="search" id="saved" class="white nodeco alist"><s:message code="search.label.saved"/></a>
					</div>
				</form>
			</div>
			<%-- END Search Box--%>
		</div>
		<%-- END Header 2--%>
		<%-- START Page Content --%>
		<div id="bd" class="content-container">

			<div id="load"><%-- Page Content --%></div>
		</div>
		<%-- Detail Map and Action Dialog Placeholders --%>
		<%-- Progress Bar --%>

		<%-- System Messaging Bar --%>
		<div id="system-messaging"></div>
		<%-- END Page Content --%>

		<%-- START Footer --%>
		<div id="ft" class="yui-g">
			<div class="yui-u first"><%-- Footer address section --%>
				<div class="vcard">
					<div class="fn org">&copy;<span id="current-year"></span>
					<s:message code="company.org" /></div>
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
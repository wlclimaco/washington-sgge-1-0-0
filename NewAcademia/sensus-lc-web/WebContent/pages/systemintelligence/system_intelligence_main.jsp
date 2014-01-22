<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<div id="settings-container" class="yui-ge point-type-navigation header rounded-top-corners">
		<div class="yui-u first">
			<ul class="tabs">
				<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator','ROLE_Role.Customer Support')">
					<li><a id="process" class="tag-page-action" href="process"><s:message code="systemsettings.page.tab.eventhistory" /></a></li>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
					<li class="active"><a id="schedule" href="schedule"><s:message code="systemsettings.page.tab.schedules" /></a></li>
				</sec:authorize>

			</ul>
		</div>
</div>
<div class="content-inner">
		<!-- Messaging -->
		<div id="messaging-main" class="messaging">
			<span class="message"></span><a href="" class="remove"><s:message code="message.action.close" /></a>
		</div>
		<div id="tabs-content"></div>
</div>

<%@ include file="../../scripts/pages/systemintelligence/system_intelligence_main.js.jsp" %>
<%@ include file="../../scripts/pages/systemintelligence/system_intelligence_init.js.jsp" %>
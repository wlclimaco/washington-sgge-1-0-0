<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and !hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="settings-container" class="content-inner">
		<div id="tabs" class="yui-t2 ui-tabs ui-widget ui-corner-all">
			<div class="yui-b settings-nav-container">
				<ul class="settings-nav">
					<li class="ui-tabs-selected ui-state-active">
						<a class="linkTab" href="settings/system"> <spring:message code="profile.page.general"/></a>
					</li>
					<li>
						<a class="linkTab" href="tag"> <spring:message code="systemsettings.page.manageTags"/></a>
					</li>
				</ul>
			</div>
			<div id="yui-main">
				<div class="yui-b settings-content" id="tabs-content"></div>
			</div>
		</div>
	</div>

	<jsp:include page="../../scripts/pages/system_settings/system_settings_main.js.jsp" flush="true" />
	<jsp:include page="../../scripts/pages/system_settings/system_settings_init.js.jsp" flush="true" />

</sec:authorize>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<div id="settings-container" class="content-inner">
    <!-- Messaging -->
	<div id="messaging-main" class="messaging hide" >
		<span class="message"></span><a href="" class="remove"><s:message code="message.action.close" /></a>
	</div>
	<div id="yui-main" >
      	<div class="content-header">
          	<h1><s:message code="systemsettings.page.title" /></h1>
		</div>
		<div id="tabs">
			<ul>
				<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
					<li><a href="generalsettings"><s:message code="systemsettings.page.tab.general" /></a></li>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
					<li><a href="tag" class="tag-page-action"><s:message code="systemsettings.page.tab.tag" /></a></li>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
					<%-- <c:if test="${userContext.getTenant().getEcoModeDisable() == false}"> --%>
						<li><a href="settingsEcomode" class="tag-page-action"><s:message code="systemsettings.page.tab.eco-mode" /></a></li>
					<%--</c:if> --%>
				</sec:authorize>
			</ul>
		</div>
	</div>
</div>

<%@ include file="../../scripts/pages/system_settings_main.js.jsp" %>
<%@ include file="../../scripts/pages/system_settings_init.js.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<div id="settingsContainer" class="content-inner">
    <!-- Messaging -->
		<div id="messaging-main" class="messaging hide" >
			<span class="message"></span><a href="" class="remove"><s:message code="message.action.close" /></a>
		</div>
		<div id="tabs" class="yui-t2">
			<div class="yui-b settings-nav-container" id="getDiv">
				<ul  class="settings-nav">
					<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
						<li>
							<a href="generalsettings"><s:message code="systemsettings.page.tab.general" /></a>
						</li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
						<li>
							<a href="tag" ><s:message code="systemsettings.page.tab.tag" /></a>
					   </li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
						<li>
							<a href="settingsEcomode" ><s:message code="systemsettings.page.tab.eco-mode" /></a>
						</li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
						<li>
							<a href="academia/academiacreate" >Fotos</a>
						</li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
						<li>
							<a href="settingsEcomode" >Exercicios</a>
						</li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
						<li>
							<a href="academia" >Academia</a>
						</li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
						<li>
							<a href="settingsEcomode" >Grupo Muscular</a>
						</li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
						<li>
							<a href="settingsEcomode" >Serie de treino</a>
						</li>
					</sec:authorize>
				</ul>

			</div>
			<div id="yui-main" >
				<div class="yui-b settings-content" id="insertDiv"></div>
			</div>
		</div>


</div>

<%@ include file="../../scripts/pages/systemsettings/system_settings_main.js.jsp" %>
<%@ include file="../../scripts/pages/systemsettings/system_settings_init.js.jsp" %>
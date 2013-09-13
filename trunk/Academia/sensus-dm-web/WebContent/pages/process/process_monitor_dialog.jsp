<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<%-- Long Running Process dialog --%>
	<div id="action-dialog-panel" class="action-dialog">
		<h2>
			<spring:message code="action.longRunningProcessMonitor.title" />
		</h2>
		<div class="descriptive-button-row">
			<p></p>
			<a href="#" class="button monitor"><spring:message code="action.longRunningProcessMonitor.submit" /></a>
			<a href="#" class="button dismiss"><spring:message code="action.longRunningProcessMonitor.cancel" /></a>
		</div>
		<div class="highlight">
			<small>
				<input type="checkbox" /> <spring:message code="action.longRunningProcessMonitor.highlight" />
			</small>
		</div>
	</div>

</sec:authorize>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="summary-tabs" class="hide">
		<ul>
			<li id="communication">
				<a href="summary/communication"><spring:message code="summary.tab.communication.title"/></a>
			</li>
			<li id="demandResponse">
				<a href="summary/demandResponse"><spring:message code="summary.tab.demandresponse.title"/></a>
			</li>
			<li id="importHanDevices">
				<a href="summary/importHanDevices"><spring:message code="sensus.dm.action.import.han.device"/></a>
			</li>
		</ul>

		<button class="ui-button refresh" title="<spring:message code="summary.text.refreshData"/>">
			<span class="ui-icon ui-icon-refresh"><spring:message code="summary.text.refreshData"/></span>
		</button>

	</div>

	<div id="summary-container" class="hide white"></div>

</sec:authorize>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<sec:authorize access="hasRole('ROLE_EPM_SERVICE_ELECTRIC') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="device-lcm-relay" class="point-detail-container">
		<h3><spring:message code="smartpointdetail.tabs.about.lcmRelay" /></h3>
		<div id="list">
			<div class="blankslate hide" id="blankslate-lcm-relay">
				<p><spring:message code="smartpointdetail.tabs.about.noLcmRelay" /></p>
			</div>
			<table id="lcm-relay" class="small-table">
				<thead>
					<tr>
						<th><spring:message code="smartpointdetail.tabs.about.relay"/></th>
						<th><spring:message code="smartpointdetail.tabs.about.amp"/></th>
						<th><spring:message code="systemintelligence.scheduledCreateEvent.deviceClass"/></th>
						<th><spring:message code="smartpointdetail.tabs.about.lcmRelay.enrollment"/></th>
						<th><spring:message code="smartpointdetail.tabs.about.lcmRelay.randomize"/></th>
						<th><spring:message code="smartpointdetail.tabs.about.lcmRelay.tamper.detect"/></th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>

</sec:authorize>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<sec:authorize access="hasRole('ROLE_EPM_SERVICE_ARQIVA') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<!-- START - Device Type Details -->
	<div id="device-endpoint-container" class="point-detail-container">
		<h3><spring:message code="smartpointdetail.tabs.about.endpoints"/>
		</h3>
		<!-- Blankslate -->
		<div class="blankslate hide" id="blankslate-endpoints-devices">
			<p><spring:message code="smartpointdetail.tabs.about.noEndpoint" /></p>
		</div>
		<table id="endpoint-devices" class="small-table">
		<thead>
			<tr>
				<th><spring:message code="smartpointdetail.tabs.about.type"/></th>
				<th><spring:message code="smartpointdetail.tabs.about.deviceId"/></th>
				<th></th>
			</tr>
		</thead>
		<tbody>

		</tbody>
	   </table>
	</div>

</sec:authorize>
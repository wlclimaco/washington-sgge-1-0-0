<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<sec:authorize access="hasRole('ROLE_EPM_SERVICE_ELECTRIC') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<!-- START - Device Type Details -->
	<div id="device-han-container" class="point-detail-container">
		<h3><spring:message code="smartpointdetail.tabs.about.hanDevices"/>

		<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR')">
			<a href="" class="edit right han-join editConnect"><spring:message code="smartpointdetail.tabs.about.connectHanDevice"/></a>
		</sec:authorize>

		</h3>
		<!-- Blankslate -->
		<div class="blankslate hide" id="blankslate-han-devices">
			<p><spring:message code="smartpointdetail.tabs.about.noHanDevice" /></p>
		</div>
		<table id="han-devices" class="small-table">
		<thead>
			<tr>
				<th><spring:message code="smartpointdetail.tabs.about.type"/></th>
				<th><spring:message code="smartpointdetail.tabs.about.deviceId"/></th>
				<th><spring:message code="smartpointdetail.tabs.about.status"/></th>
				<th><spring:message code="smartpointdetail.tabs.about.lastStatus"/></th>
				<th></th>
			</tr>
		</thead>
		<tbody>

		</tbody>
	   </table>
	</div>

</sec:authorize>
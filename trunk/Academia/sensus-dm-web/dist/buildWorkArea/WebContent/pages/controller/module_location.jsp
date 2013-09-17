<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<!-- Start Address -->
	<div id="address" class="point-detail-container">

		<div class="current-monitored-event">
			<h3>
				<spring:message code="smartpointdetail.tabs.about.location"/>
				<%-- <sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR')"> --%>
					<%-- <spring:message code="smartpointdetail.tabs.about.editLocation"/> --%>
				<%-- </sec:authorize> --%>
			</h3>
		</div>

		<div id="detail-map-container"></div>
		<table class="small-table">
			<tr>
				<td>
					<spring:message code="commons.pages.address"/>:
				</td>
				<td>
					<span id="address-val"></span>
				</td>
			</tr>
			<tr>
				<td>
					<spring:message code="commons.pages.latitude"/>:
				</td>
				<td>
					<span id="lat-val"></span>&deg;
				</td>
			</tr>
			<tr>
				<td>
					<spring:message code="commons.pages.longitude"/>:
				</td>
				<td>
					<span id="long-val"></span>&deg;
				</td>
			</tr>
			<tr>
				<td>
					<spring:message code="smartpointdetail.tabs.about.timeZone"/>:
				</td>
				<td>
					<span id="timeZone"></span>
				</td>
			</tr>
		</table>
	</div>
	<!-- End Address -->

</sec:authorize>
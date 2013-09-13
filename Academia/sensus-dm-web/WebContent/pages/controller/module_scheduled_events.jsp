<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 	
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and 
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<!-- START - Scheduled Events -->
	<div id="scheduled" class="point-detail-container">

		<h3><spring:message code="commons.pages.scheduledEvents"/></h3>

		<div id="list">

			<!-- Blankslate -->
			<div class="blankslate hide" id="blankslate-scheduled">
				<p><spring:message code="smartpointdetail.tabs.about.noSchedulesWithThisSmartPoint" /></p>
			</div>

			<table id="scheduled-events" class="small-table">

				<thead>
					<tr>
						<th><spring:message code="commons.pages.actionType"/></th>
						<th><spring:message code="commons.pages.actionName"/></th>
						<th><spring:message code="systemintelligence.page.today.event"/></th>
						<th><spring:message code="commons.pages.date"/></th>
						<th><spring:message code="commons.pages.Time"/></th>
						<th><spring:message code="smartpointdetail.tabs.about.repeats"/></th>
						<th><spring:message code="commons.pages.status"/></th>
						<th></th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>
		</div>
	</div>
	<!-- END - Scheduled Events -->

</sec:authorize>
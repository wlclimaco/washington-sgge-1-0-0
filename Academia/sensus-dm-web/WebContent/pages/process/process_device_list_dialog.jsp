<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and 
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="processing-list-container">
		<ul class="ui-state-highlight summary">
			<li class="selected-points">
				<div class="export-select tools">
					<ul class="link-list right">
						<li class="export-type last"><small><strong><spring:message code="commons.pages.Export" /></strong>: <a href="" class="csv" id="csvDialog"><spring:message code="commons.pages.csv" /></a></small></li>
					</ul>
				</div>
				<table class="small-table">
					<thead>
						<tr id='header'>
							<th><spring:message code="smartpointdetail.tabs.about.deviceId" /></th>
							<th><spring:message code="commons.pages.networkAddress" /></th>
							<th><spring:message code="commons.pages.address" /></th>
							<th class="columError"><spring:message code="commons.pages.errorTwo" /></th>
						</tr>
					</thead>
					<tbody id="list-table">
					</tbody>
				</table>
			</li>
		</ul>
	</div>

</sec:authorize>
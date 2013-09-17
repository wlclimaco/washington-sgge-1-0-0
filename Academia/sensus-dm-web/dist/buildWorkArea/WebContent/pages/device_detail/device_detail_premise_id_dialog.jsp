<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and 
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div>
		<h2 id="premiseIdText"></h2>
		<table id="premiseIdTable" class="small-table">
			<thead>
				<tr>
					<th colspan="2"><spring:message code="commons.pages.type"/></th>
					<th><spring:message code="commons.pages.utility"/></th>
					<th><spring:message code="commons.pages.device_description"/></th>
					<th><spring:message code="commons.pages.device_id"/></th>
					<th><spring:message code="commons.pages.networkAddress"/></th>
					<th><spring:message code="commons.pages.added"/></th>
					<th><spring:message code="commons.pages.status"/></th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</div>

</sec:authorize>
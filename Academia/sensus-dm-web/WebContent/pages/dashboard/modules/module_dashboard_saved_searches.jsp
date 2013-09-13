<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div class="yui-u ss-widget-container box-saved-searches">
		<h3>
			<span><spring:message code="search.page.title" /></span>
		</h3>
		<!-- Blankslate -->
		<div class="blankslate hide dashboard-saved">
			<h5>
				<spring:message code="dashboard.page.createSavedSearch" />
			</h5>
			<p><spring:message code="dashboard.page.createSavedSearchMessage" /></p>
		</div>
		<table id="search-table" class="list">
			<thead>
				<tr>
					<th class="hide"><span> <spring:message code="commons.pages.id" /></span></th>
					<th class="tall"><span id="NAME"> <spring:message code="search.page.table.name" /></span></th>
					<th class="tall"><span> <spring:message code="commons.pages.description" /></span></th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>

</sec:authorize>
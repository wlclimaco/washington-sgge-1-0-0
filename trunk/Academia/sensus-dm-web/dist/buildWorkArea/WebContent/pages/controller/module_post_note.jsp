<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<!-- START - Notes -->
	<div id="notes" class="point-detail-container">

		<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
			<h3><strong><spring:message code="smartpointdetail.tabs.about.postNote"/></strong></h3>
			<textarea id="new-note"></textarea><a href="" id="note-submit" class="button">
			<spring:message code="smartpointdetail.tabs.about.postNote"/></a>
		</sec:authorize>

		<div class="blankslate" id="blankslate-note">
			<p><spring:message code="smartpointdetail.tabs.about.noNotesWithThisSmartPoint"/></p>
		</div>

		<div class="annotation point-detail-container">
			<dl></dl>
		</div>
	</div>
	<!-- END - Notes -->

</sec:authorize>
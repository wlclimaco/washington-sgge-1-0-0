<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<sec:authorize access="hasRole('ROLE_EPM_SERVICE_ELECTRIC') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="device-demand-response-setup" class="point-detail-container">
		<h3><spring:message code="smartpointdetail.tabs.about.demandResponseSetup"/></h3>
		  <div id="list">
				<!-- Blankslate -->
				<div class="blankslate hide" id="blankslate-demand-response-setup">
					<p><spring:message code="smartpointdetail.tabs.about.noDemandResponse" /></p>
				</div>
				<table id="demand-response-setup" class="small-table">
				<thead>
					<tr>
						<th><spring:message code="smartpointdetail.tabs.about.enrollmentCode"/></th>
						<th><spring:message code="smartpointdetail.tabs.about.randomizeStart"/></th>
						<th><spring:message code="smartpointdetail.tabs.about.randomizeEnd"/></th>
						<th><spring:message code="smartpointdetail.tabs.about.last"/></th>
					</tr>
				</thead>
					<tbody>
					</tbody>
			   </table>
		   </div>
	</div>

</sec:authorize>
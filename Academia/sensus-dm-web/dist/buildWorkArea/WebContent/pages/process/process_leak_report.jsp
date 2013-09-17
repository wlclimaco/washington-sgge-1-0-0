<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and 
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div class="content-inner">
		<div id="yui-main">
			<div class="tools">
				<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
					<div class="export-select-right">
						<ul class="link-list">
							<li class="last export-type">
								<small>
									<strong><spring:message code="commons.pages.Export" /></strong>:
									<a href="" id="csv" class="csv"><spring:message code="commons.pages.csv" /></a>
								</small>
							</li>
						</ul>
					</div>
				</sec:authorize>
			</div>
		</div>

		<div id="list">
			<!-- Blankslate -->
			<div class="blankslate hide">
				<h5><spring:message code="commons.pages.blankResult" /></h5>
			</div>

			<table id="report-table" class="list">
				<thead>
					<tr>
						<th><span><spring:message code="commons.pages.deviceId" /></span></th>
						<th><span><spring:message code="commons.pages.networkAddress" /></span></th>
						<th><span><spring:message code="systemintelligence.leakReport.leakTime" /></span></th>
						<th><span><spring:message code="systemintelligence.leakReport.recentConsumption" /></span></th>
						<th><span><spring:message code="systemintelligence.leakReport.recentConsumptionPercentage" /></span></th>
						<th><span><spring:message code="systemintelligence.leakReport.priorConsumption" /></span></th>
						<th><span><spring:message code="systemintelligence.leakReport.priorConsumptionPercentage" /></span></th>
						<th><span><spring:message code="systemintelligence.leakReport.dailyConsumptionAverage" /></span></th>
						<th class="hide"></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
	<jsp:include page="../../scripts/pages/process/process_leak_report_main.js.jsp" flush="true" />
	<jsp:include page="../../scripts/pages/process/process_leak_report_init.js.jsp" flush="true" />

</sec:authorize>
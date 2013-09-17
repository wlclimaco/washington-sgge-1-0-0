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
				<h5><spring:message code="commons.pages.blankResultToday" /></h5>
			</div>

			<table id="today-table" class="list">
				<thead>
					<tr>
						<th id="id" class="hide" rowspan="2"><span><spring:message code="commons.pages.id" /></span></th>
						<th id="device_type" rowspan="2" class="hide"><span><spring:message code="commons.pages.device_type" /></span></th>
						<th rowspan="2"><span id="action_name"><spring:message code="commons.pages.actionType" /></span></th>
						<th rowspan="2"><span id="action_type"><spring:message code="commons.pages.actionName" /></span></th>
						<th rowspan="2"><span id="action_name"><spring:message code="commons.pages.event" /></span></th>
						<th colspan="2"><span><spring:message code="commons.pages.smartPoints" /></span></th>
						<th rowspan="2"><span id="modified_user"><spring:message code="systemintelligence.page.today.requestedBy" /></span></th>
						<th rowspan="2"><span id="start_time"><spring:message code="commons.pages.startTime" /></span></th>
						<th rowspan="2"><span id="status"><spring:message code="commons.pages.status" /></span></th>
						<th rowspan="2" class="hide"></th>
					</tr>
					<tr class="sub-head">
						<th><span id="smartpoint_count"><spring:message code="commons.pages.total" /></span></th>
						<th><span id="failed"><spring:message code="commons.pages.failed" /></span></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>


	<jsp:include page="../../scripts/pages/process/process_today_main.js.jsp" flush="true" />
	<jsp:include page="../../scripts/pages/process/process_today_init.js.jsp" flush="true" />

</sec:authorize>
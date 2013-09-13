<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div class="yui-u first ss-widget-container">
		<h3>
			<span><spring:message code="dashboard.page.eventsToday" /></span>
		</h3>
		<!-- Blankslate -->
		<div class="blankslate hide">
			<h5>
				<spring:message code="dashboard.page.blankResult" />
			</h5>
			<p>
				<a class="alist event-hisotry-process" href="process/history"><spring:message code="dashboard.page.removefilters" /></a>
			</p>
		</div>
		<table id="event-table" class="list">
			<thead>
				<tr>
					<th class="hide" rowspan="2"><span> <spring:message code="commons.pages.id" /></span></th>
					<th id="device_type" rowspan="2" class="hide"><span><spring:message code="commons.pages.device_type" /></span></th>
					<th rowspan="2"><span> <spring:message code="systemintelligence.page.today.event" /></span></th>
					<th rowspan="2"><span> <spring:message code="commons.pages.action" /></span></th>
					<th colspan="2"><span> <spring:message code="commons.pages.smartPoints" /></span></th>
					<th class="hide" rowspan="2"><span id="START_TIME"><spring:message code="commons.pages.startTime" /></span></th>
					<th rowspan="2"><span> <spring:message code="commons.pages.status" /></span></th>
					<th class="hide" rowspan="2">&nbsp;</th>
					<th rowspan="2" class="hide"></th>
				</tr>
				<tr>
					<th class="sub-head"><span> <spring:message code="commons.pages.total" /></span></th>
					<th class="sub-head"><span> <spring:message code="commons.pages.failed" /></span></th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>

</sec:authorize>
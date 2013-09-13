<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div>
		<div class="highlight clear hide">
			<p>
				<em>
					<spring:message code="commons.pages.your" />
					<strong class="action-type"></strong>
					(<spring:message code="commons.pages.id" />: <span class="process-id"></span>) <spring:message code="commons.pages.request" />,
						<strong class="request-by"></strong>
							<span id="completed-in" class="hide">
								<spring:message code="longRunning.table.header.completedIn" />
								<span class="time"></span>
							</span>
					<span id="command-sent" class="hide">
						<spring:message code="longRunning.table.header.wasSent" />
					</span>.
				</em>
			</p>
			<p>
				<spring:message code="summary.text.actionInitiatedBy" /> &ldquo;<span class="initiate-by"></span>&rdquo;
			</p>
		</div>
		<br>
		<div id="detail-header-container" class="ss-widget-table-summary-kpi">
			<table id="informationTable" class="summary-kpi">
				<tbody>
					<tr>
						<td class="success" style="">
							<span class="title"></span>
							<strong class="value"></strong>
							<small class="sub-head"></small>
						</td>
						<td class="failed">
							<span class="title"></span>
							<strong class="value"></strong>
							<small class="sub-head"></small>
						</td>
						<td class="last fail hide">
							<ul>
								<li class="fail"></li>
							</ul>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<h4 id="list-fail" class="fail hide">
			<strong></strong> <spring:message code="summary.text.failedDevicesListedBellow"/>
		</h4>
		<div class="selected-points selected-point hide">
			<div class="export-select tools">
				<ul class="link-list">
					<li class="export-type last">
						<small><spring:message code="commons.pages.displaying"/> <span class="size"></span> <spring:message code="commons.pages.of"/> <span class="size"></span>
						<strong><spring:message code="commons.pages.exportAll"/></strong>: <a href="#" class="csv">CSV</a></small>
					</li>
				</ul>
			</div>
			<table id="tableCommunicationSummary" class="small-table">
				<thead>
					<tr>
						<th><spring:message code="summary.text.headerTable.device_id"/></th>
						<th><spring:message code="summary.text.headerTable.Network_Address"/></th>
						<th><spring:message code="summary.text.headerTable.Premise_ID"/></th>
						<th><spring:message code="summary.text.headerTable.Address"/></th>
						<th><spring:message code="summary.text.headerTable.State"/></th>
						<th><spring:message code="summary.text.headerTable.Error"/></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>

</sec:authorize>
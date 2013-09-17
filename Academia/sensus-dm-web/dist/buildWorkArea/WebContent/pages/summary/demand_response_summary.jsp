<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasRole('ROLE_EPM_SERVICE_ELECTRIC') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div class="ui-state-error ui-corner-all hide">
		<small><strong></strong> <spring:message code="summary.tab.importHan.notReturnedEventStatus"/>
		<a href="" class="button"><spring:message code="sensus.dm.action.get.demand.response.event.status"/></a></small>
	</div>

	<fieldset>
		<legend>
			<a href="" class="text-button spindown">
				<span class="ui-icon-triangle-1-e ui-icon"></span>
				<span class="ui-icon-triangle-1-s ui-icon" style="display:none"></span>
				<small><spring:message code="systemintelligence.page.event.hanMessageText"/></small>
			</a>
		</legend>
		<div class="spindown-child point-detail-container" style="display:none">
			<dl id="demand-response-details" class="sui-pad1">
				<dt><spring:message code="smartpointdetail.tabs.about.enrollmentCode"/></dt>
				<dd></dd>
				<dt><spring:message code="smartpointdetail.tabs.about.offset"/></dt>
				<dd></dd>
				<dt><spring:message code="smartpointdetail.tabs.about.dutyCycleRate"/></dt>
				<dd></dd>
				<dt><spring:message code="smartpointdetail.tabs.about.loadAdjustment"/></dt>
				<dd></dd>
				<dt><spring:message code="smartpointdetail.tabs.about.criticalityLevel"/></dt>
				<dd></dd>
				<dt><spring:message code="smartpointdetail.tabs.about.randomize"/></dt>
				<dd></dd>
			</dl>
		</div>
	</fieldset>

	<div id="detail-header-container" class="ss-widget-table-summary-kpi">
		<table class="summary-kpi" id="summaryDemandResponse">
			<tbody>
				<tr>
					<td>
						<span class="title"><spring:message code="commons.pages.start"/></span>
						<strong class="value"></strong>
						<small class="sub-head"></small>
					</td>
					<td>
						<span class="title"><spring:message code="summary.text.headerTable.duration"/></span>
						<strong class="value"></strong>
						<small class="sub-head"></small>
					</td>
					<td>
						<span class="title"><spring:message code="summary.text.headerTable.participation"/></span>
						<strong class="value"></strong>
						<small class="sub-head"></small>
					</td>
					<td>
						<span class="title"><spring:message code="summary.text.headerTable.receivedStart"/></span>
						<strong class="value"></strong>
						<small class="sub-head"></small>
					</td>
					<td>
						<span class="title"><spring:message code="summary.text.headerTable.FullParticipation"/></span>
						<strong class="value"></strong>
						<small class="sub-head"></small>
					</td>
					<td>
						<span class="title"><spring:message code="summary.text.headerTable.PartialParticipation"/></span>
						<strong class="value"></strong>
						<small class="sub-head"></small>
					</td>
					<td>
						<span class="title"><spring:message code="summary.text.headerTable.OptOut"/></span>
						<strong class="value"></strong>
						<small class="sub-head"></small>
					</td>
					<td class="last fail hide">
						<ul id="failed-devices-group">
						</ul>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

	<div class="summary demand-response-sumary">
		<h4 id="list-fail" class="fail hide">
			<strong></strong> <spring:message code="summary.text.listedBellow.participatingDevices"/>
		</h4>
		<div class="selected-points selected-point hide">
			<div class="export-select tools">
				<ul class="link-list">
					<li class="export-type last">
						<small><spring:message code="commons.pages.displaying"/> <span class="size"></span> <spring:message code="commons.pages.of"/> <span class="size"></span>
						<strong><spring:message code="commons.pages.exportAll"/></strong>: <a href="#" class="csv"> <spring:message code="commons.pages.csv"/> </a></small>
					</li>
				</ul>
			</div>

			<table class="small-table" id="tableDemandResponse">
				<thead>
					<tr>
						<th><spring:message code="summary.text.headerTable.device_id"/></th>
						<th><spring:message code="summary.text.headerTable.Network_Address"/></th>
						<th><spring:message code="summary.text.headerTable.ParentDeviceID"/></th>
						<th><spring:message code="summary.text.headerTable.Premise_ID"/></th>
						<th><spring:message code="summary.text.headerTable.NetworkStatus"/></th>
						<th><spring:message code="summary.text.headerTable.FullParticipation"/></th>
						<th><spring:message code="summary.text.headerTable.PartialParticipation"/></th>
						<th><spring:message code="summary.text.headerTable.OptOut"/></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>

</sec:authorize>
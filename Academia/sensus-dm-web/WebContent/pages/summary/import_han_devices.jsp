<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasRole('ROLE_EPM_SERVICE_ELECTRIC') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div class="summary import-han-device-summary">
		<div id="detail-header-container" class="ss-widget-table-summary-kpi">
			<table id="informationTableImportHan" class="summary-kpi">
				<tbody>
					<tr>
						<td>
							<span class="title"><spring:message code="commons.pages.CONNECTED"/></span>
							<strong class="value"></strong>
							<small class="sub-head"></small>
						</td>
						<td>
							<span class="title"><spring:message code="commons.pages.DISCONNECTED"/></span>
							<strong class="value"></strong>
							<small class="sub-head"></small>
						</td>
						<td class="last fail hide">
							<ul id="failed-devices-group-importHan">
							</ul>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<h4 id="list-fail" class="fail hide">
			<strong></strong> <spring:message code="summary.text.listedBellow.failedDevices"/>
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
			<table id="tableImportHanDevice" class="small-table">
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
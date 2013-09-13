<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div class="summary tamper-detect-sumary">
		<h4 id="list-fail" class="fail hide">
			<strong></strong> <spring:message code="summary.text.listedBellow.devices"/>
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

			<table class="small-table" id="tableTamperDetect">
				<thead>
					<tr>
						<th><spring:message code="summary.text.headerTable.device_id"/></th>
						<th><spring:message code="summary.text.headerTable.Network_Address"/></th>
						<th><spring:message code="summary.text.headerTable.Premise_ID"/></th>
						<th><spring:message code="smartpointdetail.tabs.about.relay"/></th>
						<th><spring:message code="summary.tab.tamperDetect.tamperTimer"/></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>

</sec:authorize>
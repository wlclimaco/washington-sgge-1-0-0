<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<ul class="ui-state-highlight summary">
		<li class="hide"><span id="id-process"> </span></li>

	   <li id="header-message">

				<spring:message code="commons.pages.your"/> <strong> <span id="action-type-enum"></span> </strong> &nbsp;<spring:message code="commons.pages.request"/>,
				<strong> <span id="action-name"></span> </strong>&nbsp;<span id="time"></span>.<br/>

			<p id="initied" class="hide"><spring:message code="summary.text.actionInitiated"/>&nbsp;<span id="action-type"></span>"<span id="action-name-group"></span>"</p>
	   </li>


		<li class="success">
			<span class="icon-small"></span>
			<span id="count-success"></span> (<span id="percent-success"></span>%) <spring:message code="commons.pages.succeeded" /> <small></small>
		</li>

		<li class="fail"><span class="icon-small"></span><span id="count-failure"></span> (<span id="percent-failure"></span>%) <spring:message code="commons.pages.failed" />
			<ul class="summary-container summary-container-ul"> </ul>
		</li>

		<li>
			 <div class="selected-points selected-point">
				<div class="export-select tools">
					<ul class="link-list export-ul left">
						<li class="export-type last"><small><strong><spring:message code="commons.pages.displaying" /> <span id="sum-page"></span> <spring:message code="commons.pages.of" /> <span id="sum-failure"></span> <spring:message code="commons.pages.Export" /></strong>: <a href="#" id="csv" class="csv"><spring:message code="commons.pages.csv" /></a></small></li>
					</ul>
				</div>
				<table id="summary-meters-table" class="small-table">
					<thead>
						<tr>
						</tr>
						<tr>
							<th><spring:message code="commons.pages.deviceId" /></th>
							<th><spring:message code="commons.pages.networkAddress" /></th>
							<th><spring:message code="commons.pages.address" /></th>
							<th><spring:message code="commons.pages.errorTwo" /></th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
		   </div>
		</li>
	</ul>

</sec:authorize>
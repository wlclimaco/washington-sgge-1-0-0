<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="detail-header-container" class="ss-widget-table-summary-kpi">
		<table class="summary-kpi">
			<tr>
				<!-- Device Information -->
				<c:if test="${summaryDatas.deviceInformation}">
					<td class="first">
						<div id="detail-header-pane-1" class="detail-header">
							<h1>
								<strong></strong>
							</h1>
							<p class="description-address"></p>
						</div>
					</td>
				</c:if>

				<!-- Premise Id -->
				<c:if test="${summaryDatas.premiseId}">
					<td id="summaryPremiseId">
						<p><spring:message code="commons.pages.premise_id"/></p>
						<strong class="value"><a href="#" class="request-premise-detail"></a></strong>
						<small id="premise-id-count" class="sub-head"></small>
					</td>
				</c:if>

				<!-- Network Address -->
				<c:if test="${summaryDatas.networkAddress}">
					<td id="summaryNetworkAddress">
						<p><spring:message code="commons.pages.networkAddress"/></p>
						<strong class="value"></strong>
						<small class="sub-head hide"><spring:message code="commons.pages.upload.label.flexnet"/></small>
					</td>
				</c:if>

				<!-- Network Status -->
				<c:if test="${summaryDatas.networkStatus}">
					<td>
						<p><spring:message code="commons.pages.networkStatus" /></p>
						<strong class="value" id="network-status-value"></strong>
					</td>
				</c:if>

				<!-- Installed Date -->
				<c:if test="${summaryDatas.installedDate}">
					<td id="summaryInstalledDate">
						<p><spring:message code="smartpointdetail.tabs.about.installedDate"/></p>
						<strong class="value"></strong>
					</td>
				</c:if>

				<!-- Parent -->
				<c:if test="${summaryDatas.parent}">
					<td>
						<p><spring:message code="commons.pages.parent"/></p>
						<strong class="value" id="parent-value">--</strong>
					</td>
				</c:if>

				<!-- Connected Date -->
				<c:if test="${summaryDatas.connectedDate}">
					<td>
						<p><spring:message code="commons.pages.connectedDate" /></p>
						<strong id="connected-date-value" class="value"></strong>
					</td>
				</c:if>

				<!-- Life Cycle -->
				<c:if test="${summaryDatas.lifecycle}">
					<td id="lifecycle">
						<p><span><spring:message code="smartpointdetail.tabs.about.lifecycle" /></span></p>
						<strong class="value"></strong>
					</td>
				</c:if>

				<!-- Transmit -->
				<c:if test="${summaryDatas.transmit}">
					<td id="transmit">
						<span><spring:message code="smartpointdetail.tabs.about.transmit" /></span>
						<strong class="value"></strong>
						<small class="sub-head"></small>
					</td>
				</c:if>

				<!-- Last Read -->
				<c:if test="${summaryDatas.lastRead}">
					<td id="lastRead">
						<span><spring:message code="smartpointdetail.tabs.about.lastRead" /></span>
						<strong class="value"></strong>
						<small class="sub-head"></small>
					</td>
				</c:if>

				<!-- SmartPoint -->
				<c:if test="${summaryDatas.smartPoint}">
					<td id="smartPoint">
						<span><spring:message code="commons.pages.networkAddress" /></span>
						<strong class="value"><span></span></strong>
					</td>
				</c:if>

				<!--  Actions -->
				<c:if test="${summaryDatas.actions}">
					<td class="last">
						<!-- The Admin and Operator users see its actions -->
						<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR')">
							<span id="actions-button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-icon-secondary" role="button" aria-disabled="false">
								<span class="ui-button-text"><spring:message code="commons.pages.actions" /></span>
								<span class="ui-button-icon-secondary ui-icon ui-icon-triangle-1-s"></span>
							</span>
							<!-- SmartPoint Actions-->
							<div id="actions-options" class="hidden">
								<ul class="detail-actions-height"></ul>
							</div>
						</sec:authorize>
						<!-- The Customer and Billing users see its actions, just if service is not Electric -->
						<sec:authorize access="hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
							<c:if test="${serviceType != 'ELECTRIC'}">
								<span id="actions-button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-icon-secondary" role="button" aria-disabled="false">
									<span class="ui-button-text"><spring:message code="commons.pages.actions" /></span>
									<span class="ui-button-icon-secondary ui-icon ui-icon-triangle-1-s"></span>
								</span>
								<!-- SmartPoint Actions-->
								<div id="actions-options" class="hidden">
									<ul class="detail-actions-height"></ul>
								</div>
							</c:if>
						</sec:authorize>
					</td>
				</c:if>
			</tr>
		</table>
	</div>

</sec:authorize>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<sec:authorize access="hasRole('ROLE_EPM_SERVICE_WATER') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="device-information" class="point-detail-container">

		<!-- Whether device has Details Module -->
		<c:if test="${contentModules.deviceDetails}">
			<div class="yui-gd">
				<div class="yui-u first">
					<h3><spring:message code="smartpointdetail.tabs.about.smartpointInformation"/></h3>
					<dl id="device-information" class="read-only font-page">
						<dt><spring:message code="commons.pages.device_id"/>:</dt>
						<dd></dd>
						<dt><spring:message code="commons.pages.network_address"/>:</dt>
						<dd></dd>
						<dt><spring:message code="commons.pages.premise_id"/>:</dt>
						<dd><a href="" class="request-premise-detail text-button"></a></dd>
						<dt><spring:message code="commons.pages.ipAddress"/>:</dt>
						<dd></dd>
						<dt><spring:message code="commons.pages.last_heard"/>:</dt>
						<dd></dd>
						<dt><spring:message code="commons.pages.status"/>:</dt>
						<dd></dd>
						<dt><spring:message code="smartpointdetail.tabs.about.installedDate"/>:</dt>
						<dd></dd>
						<dt><spring:message code="smartpointdetail.tabs.about.encryption"/>:</dt>
						<dd></dd>
						<dt><spring:message code="commons.pages.quarantine"/>:</dt>
						<dd></dd>
					</dl>
				</div>

				<div class="yui-u device-details">
					<h3><spring:message code="smartpointdetail.tabs.about.meter_information"/></h3>
					<dl class="read-only">
						<dt><spring:message code="smartpointdetail.brokenPipe"/>:</dt>
						<dd></dd>
						<dt><spring:message code="smartpointdetail.customerMeterNumber"/>:</dt>
						<dd></dd>
						<dt><spring:message code="smartpointdetail.historyScale"/>:</dt>
						<dd></dd>
						<dt><spring:message code="smartpointdetail.leakConsecutiveReads"/>:</dt>
						<dd></dd>
						<dt><spring:message code="smartpointdetail.leakDetectionThreshold"/>:</dt>
						<dd></dd>
						<dt><spring:message code="smartpointdetail.meterUnits"/>:</dt>
						<dd></dd>
						<dt><spring:message code="smartpointdetail.readingResolution"/>:</dt>
						<dd></dd>
						<dt><spring:message code="smartpointdetail.reverseFlowThreshold"/>:</dt>
						<dd></dd>
					</dl>
				</div>
			</div>
		</c:if>

		<!--  Whether device has not Details Module -->
		<c:if test="${!contentModules.deviceDetails}">
			<h3><spring:message code="smartpointdetail.tabs.about.smartpointInformation"/></h3>
			<dl id="device-information" class="read-only font-page">
				<dt><spring:message code="commons.pages.device_id"/>:</dt>
				<dd></dd>
				<dt><spring:message code="commons.pages.network_address"/>:</dt>
				<dd></dd>
				<dt><spring:message code="commons.pages.premise_id"/>:</dt>
				<dd><a href="" class="request-premise-detail text-button"></a></dd>
				<dt><spring:message code="commons.pages.ipAddress"/>:</dt>
				<dd></dd>
				<dt><spring:message code="commons.pages.last_heard"/>:</dt>
				<dd></dd>
				<dt><spring:message code="commons.pages.status"/>:</dt>
				<dd></dd>
				<dt><spring:message code="smartpointdetail.tabs.about.installedDate"/>:</dt>
				<dd></dd>
				<dt><spring:message code="smartpointdetail.tabs.about.encryption"/>:</dt>
				<dd></dd>
			</dl>
		</c:if>

	</div>

	<div id="firmware-information" class="point-detail-container">
		<h3><spring:message code="smartpointdetail.tabs.about.firmwareInformation"/></h3>
		<dl class="read-only">
			<dt><spring:message code="smartpointdetail.tabs.about.flexNetFirmWare"/>:</dt>
			<dd></dd>
			<dt><spring:message code="smartpointdetail.tabs.about.meterFirmWare"/>:</dt>
			<dd></dd>
		</dl>
	</div>

</sec:authorize>
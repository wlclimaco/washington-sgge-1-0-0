<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="device-information" class="point-detail-container">

		<!-- Whether device has not LCM Relay Module -->
		<c:if test="${!contentModules.lcmRelay && !contentModules.demandResponseSetupLcm}">
			<h3><spring:message code="smartpointdetail.tabs.about.smartpointInformation"/></h3>
			<table class="small-table">
				<tbody>
				</tbody>
			</table>
		</c:if>

		<!-- Whether device has LCM Relay Module -->
		<c:if test="${contentModules.lcmRelay}">
			<div class="yui-gd">
				<div class="yui-u first">
					<h3><spring:message code="smartpointdetail.tabs.about.smartpointInformation"/></h3>
					<table class="small-table">
						<tbody>
						</tbody>
					</table>
				</div>

				<div class="yui-u lcm-relay">
					<c:import url = "../controller/module_lcm_relay.jsp"/>
				</div>
			</div>
		</c:if>

		<!-- Whether device has Demand Response Setup LCM Module -->
		<c:if test="${contentModules.demandResponseSetupLcm}">
			<div class="yui-gd">
				<div class="yui-u first">
					<h3><spring:message code="smartpointdetail.tabs.about.smartpointInformation"/></h3>
					<table class="small-table">
						<tbody>
						</tbody>
					</table>
				</div>

				<div class="yui-u lcm-relay">
					<c:import url = "../controller/module_demand_response_setup.jsp"/>
				</div>
			</div>
		</c:if>
	</div>

	<div id="firmware-information" class="point-detail-container">
		<h3><spring:message code="smartpointdetail.tabs.about.firmwareInformation"/></h3>
		<table class="small-table">
			<tbody>
			</tbody>
		</table>
	</div>

</sec:authorize>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div class="yui-gc">

		<div class="yui-u first">
			<c:if test="${contentModules.alarmsGasMeter}">
				<c:import url = "../controller/module_device_alarm.jsp"/>
			</c:if>

			<c:if test="${contentModules.alarmsWaterMeter}">
				<c:import url = "../controller/module_device_alarm.jsp"/>
			</c:if>

			<c:if test="${contentModules.alarmsflexnetLcm}">
				<c:import url = "../controller/module_device_alarm.jsp"/>
			</c:if>

			<c:if test="${contentModules.deviceInformation}">
				<c:import url = "../controller/module_device_information.jsp"/>
			</c:if>

			<c:if test="${contentModules.waterMeterDeviceInformation}">
				<c:import url = "../controller/module_waterdevice_information.jsp"/>
			</c:if>

			<c:if test="${contentModules.gasMeterDeviceInformation}">
				<c:import url = "../controller/module_gasdevice_information.jsp"/>
			</c:if>

			<c:if test="${contentModules.hanDevices}">
				<c:import url = "../controller/module_han_devices.jsp"/>
			</c:if>

			<c:if test="${contentModules.demandResponseSetup}">
				<c:import url = "../controller/module_demand_response_setup.jsp"/>
			</c:if>

			<c:if test="${contentModules.demandResponseProgramParticipation}">
				<c:import url = "../controller/module_demand_response_program_participation.jsp"/>
			</c:if>

			<c:if test="${contentModules.scheduledEvents}">
				<c:import url = "../controller/module_scheduled_events.jsp"/>
			</c:if>

			<c:if test="${contentModules.endpoints}">
				<c:import url = "../controller/module_endpoints.jsp"/>
			</c:if>
		</div>

		<div class="yui-u">
			<c:if test="${contentModules.location}">
				<c:import url = "../controller/module_location.jsp"/>
			</c:if>

			<c:if test="${contentModules.group}">
				<c:import url = "../controller/module_groups.jsp"/>
			</c:if>

			<c:if test="${contentModules.tag}">
				<c:import url = "../controller/module_tags.jsp"/>
			</c:if>

			<c:if test="${contentModules.postNote}">
				<c:import url = "../controller/module_post_note.jsp"/>
			</c:if>
		</div>

	</div>

	<jsp:include page="../../scripts/pages/modules_detail/module_detail_content.js.jsp" flush="true"/>
	<jsp:include page="../../scripts/pages/device_detail/device_detail_about_init.js.jsp" flush="true"/>

</sec:authorize>
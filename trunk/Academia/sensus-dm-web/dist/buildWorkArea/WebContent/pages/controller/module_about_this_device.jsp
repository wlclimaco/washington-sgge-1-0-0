<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div class="yui-gc">

		<div class="yui-u first">

			<c:if test="${contentModules.alarmsGasMeter}">
				<c:import url = "module_device_alarm.jsp"/>
			</c:if>

			<c:if test="${contentModules.alarmsWaterMeter}">
				<c:import url = "module_device_alarm.jsp"/>
			</c:if>

			<c:if test="${contentModules.alarmsflexnetLcm}">
				<c:import url = "module_device_alarm.jsp"/>
			</c:if>

			<c:if test="${contentModules.deviceInformation}">
				<c:import url = "module_device_information.jsp"/>
			</c:if>

			<c:if test="${contentModules.hanDevices}">
				<c:import url = "module_han_devices.jsp"/>
			</c:if>

			<c:if test="${contentModules.lcmRelay}">
				<c:import url = "module_lcm_relay.jsp"/>
			</c:if>

			<c:if test="${contentModules.demandResponseSetup}">
				<c:import url = "module_demand_response_setup.jsp"/>
			</c:if>

			<c:if test="${contentModules.demandResponseProgramParticipation}">
				<c:import url = "module_demand_response_program_participation.jsp"/>
			</c:if>

			<c:if test="${contentModules.scheduledEvents}">
				<c:import url = "module_scheduled_events.jsp"/>
			</c:if>
		</div>

		<div class="yui-u">
			<c:if test="${contentModules.location}">
				<c:import url = "module_location.jsp"/>
			</c:if>

			<c:if test="${contentModules.group}">
				<c:import url = "module_groups.jsp"/>
			</c:if>

			<c:if test="${contentModules.tag}">
				<c:import url = "module_tags.jsp"/>
			</c:if>

			<c:if test="${contentModules.postNote}">
				<c:import url = "module_post_note.jsp"/>
			</c:if>
		</div>

	</div>

</sec:authorize>
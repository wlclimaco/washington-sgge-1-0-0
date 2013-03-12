<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
	<!-- Start About this SmartPoint -->
	<div class="yui-gf">
	
		<div class="yui-u first">


			<c:if test="${param.location == 'content'}">
  				<c:import url = "module_location.jsp"/>
			</c:if>
			

			<c:if test="${param.group == 'content'}">
  				<c:import url = "module_groups.jsp"/>
			</c:if>
			
			

			<c:if test="${param.tag == 'content'}">
				<c:import url = "module_tags.jsp"/>
			</c:if>
			
			
			<c:if test="${param.light_alerts == 'content'}">
				<c:import url = "module_light_alerts.jsp"/>
			</c:if>


		</div>
		
		<div class="yui-g">
		
			<div class="yui-u first-float-left">

				<c:if test="${param.postNote == 'content'}">
	  				<c:import url = "module_post_note.jsp"/>
				</c:if>
			
			</div>

			<div class="yui-u">

				<c:if test="${param.deviceInformation == 'content'}">
	  				<c:import url = "module_device_information.jsp"/>
				</c:if>

				<c:if test="${param.lcmRelay == 'content'}">
	  				<c:import url = "module_lcm_relay.jsp"/>
				</c:if>

				<c:if test="${param.demandResponseProgramParticipation == 'content'}">
	  				<c:import url = "module_demand_response_program_participation.jsp"/>
				</c:if>
				
				<c:if test="${param.demandResponseSetup == 'content'}">
	  				<c:import url = "module_demand_response_setup.jsp"/>
				</c:if>
				
				<c:if test="${param.hanDevices == 'content'}">
	  				<c:import url = "module_han_devices.jsp"/>
				</c:if>

				<c:if test="${param.scheduledEvents == 'content'}">
	  				<c:import url = "module_scheduled_events.jsp"/>
				</c:if>
				
			</div>

		</div>
	
	</div>
	<!-- End About this SmartPoint -->
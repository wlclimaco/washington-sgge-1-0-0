<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
	<!-- Start About this SmartPoint -->
	<div class="yui-gc">
	
		<div class="yui-u first">
			
			<c:if test="${param.lightAlerts == 'content'}">
				<c:import url = "module_light_alerts.jsp"/>
			</c:if>
			
			<c:if test="${param.lightSchedule == 'content'}">
				<c:import url = "module_light_schedule.jsp"/>
			</c:if>

			<c:if test="${param.lightReadings == 'content'}">
				<c:import url = "module_light_readings.jsp"/>
			</c:if>

			<c:if test="${param.lightConfigurations == 'content'}">
				<c:import url = "module_light_configurations.jsp"/>
			</c:if>

		</div>
		
		<div class="yui-u">

			<c:if test="${param.lightLocation == 'content'}">
  				<c:import url = "module_light_location.jsp"/>
			</c:if>
			

			<c:if test="${param.lightGroups == 'content'}">
  				<c:import url = "module_light_groups.jsp"/>
			</c:if>
			
			

			<c:if test="${param.lighttags == 'content'}">
				<c:import url = "module_light_tags.jsp"/>
			</c:if>
			
		</div>

	
	</div>
	<!-- End About this SmartPoint -->
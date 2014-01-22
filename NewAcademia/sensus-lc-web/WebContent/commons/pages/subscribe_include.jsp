<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<table class="small-table">
	<thead>
		<tr>
			<td><strong><s:message code="advanced.page.alarms"/></strong></td>
			<td>Email</td>
		</tr>
	</thead>
	<tbody id="subscribeTable">
		<tr>
			<td><s:message code="smartpoint.status.BOARD_FAILURE"/></td>
			<td class="small-label"><input type="checkbox" class="send-email"  title="Board Failure"  id="SUBSCRIBE_ALARM_BOARD_FAILURE"> <label  for="SUBSCRIBE_ALARM_BOARD_FAILURE"><s:message code="profile.page.subscribe"/></label></td>
		</tr>
		<tr>
			<td><s:message code="smartpoint.status.LAMP_FAILURE"/></td>
			<td class="small-label"><input type="checkbox" class="send-email"  title="Lamp Failure" id="SUBSCRIBE_ALARM_LAMP_FAILURE" > <label for="SUBSCRIBE_ALARM_LAMP_FAILURE"><s:message code="profile.page.subscribe"/></label></td>
		</tr>
		<tr>
			<td><s:message code="smartpoint.status.POWER_FAILURE"/></td>
			<td class="small-label"><input type="checkbox" class="send-email"  title="Power Failure"  id="SUBSCRIBE_ALARM_POWER_FAILURE"> <label for="SUBSCRIBE_ALARM_POWER_FAILURE"><s:message code="profile.page.subscribe"/></a></td>
		</tr>
		<tr>
			<td><s:message code="smartpoint.status.METROLOGY_ERROR"/></td>
			<td class="small-label"><input type="checkbox" class="send-email"  title="Metrology Error"  id="SUBSCRIBE_ALARM_METROLOGY_ERROR"> <label for="SUBSCRIBE_ALARM_METROLOGY_ERROR"><s:message code="profile.page.subscribe"/></a></td>
		</tr>
		<tr>
			<td><s:message code="smartpoint.status.METROLOGY_COM_FAILURE"/></td>
			<td class="small-label"><input type="checkbox" class="send-email"  title="Metrology COM Failure"  id="SUBSCRIBE_ALARM_METROLOGY_COM_FAILURE"> <label for="SUBSCRIBE_ALARM_METROLOGY_COM_FAILURE"><s:message code="profile.page.subscribe"/></a></td>
		</tr>
		<tr>
			<td colspan="2"><br/><h4 class="yui-pad"><s:message code="smartpoint.status.WARNING"/></h4></td>
		</tr>
		<tr>
			<td><s:message code="smartpoint.status.BROWN_OUT_DETECTED"/></td>
			<td class="small-label"><input type="checkbox" class="send-email"  title="Brownout Detected" id="SUBSCRIBE_WARN_BROWNOUT_DETECTED"> <label for="SUBSCRIBE_WARN_BROWNOUT_DETECTED"><s:message code="profile.page.subscribe"/></label></td>
		</tr>
		<tr>
			<td><s:message code="smartpoint.status.COMMUNICATION_FAIL"/></td>
			<td class="small-label"><input type="checkbox" class="send-email"  title="Communication Fail" id="SUBSCRIBE_WARN_COMMN_FAIL"> <label for="SUBSCRIBE_WARN_COMMN_FAIL"><s:message code="profile.page.subscribe"/></label></td>
		</tr>
		<tr>
			<td><s:message code="smartpoint.status.METROLOGY_RESET"/></td>
			<td class="small-label"><input type="checkbox" class="send-email"  title="Metrology Reset" id="SUBSCRIBE_WARN_METROLOGY_RESET"> <label for="SUBSCRIBE_WARN_METROLOGY_RESET"><s:message code="profile.page.subscribe"/></label></td>
		</tr>
		<tr>
			<td><s:message code="smartpoint.status.REVERSE_ENERGY"/></td>
			<td class="small-label"><input type="checkbox" class="send-email"  title="Reverse Energy" id="SUBSCRIBE_WARN_REVERSE_ENERGY"> <label for="SUBSCRIBE_WARN_REVERSE_ENERGY"><s:message code="profile.page.subscribe"/></label></td>
		</tr>
		<tr>
			<td><s:message code="smartpoint.status.POWER_SURGE_DETECTED"/></td>
			<td class="small-label"><input type="checkbox" class="send-email"  title="Power Surge"  id="SUBSCRIBE_WARN_POWER_SURGE" > <label for="SUBSCRIBE_WARN_POWER_SURGE"><s:message code="profile.page.subscribe"/></label></td>
		</tr>
		<tr>
			<td><s:message code="smartpoint.status.HIGH_CURRENT"/></td>
			<td class="small-label"><input type="checkbox" class="send-email"  title="High Current" id="SUBSCRIBE_WARN_HIGH_CURRENT"> <label for="SUBSCRIBE_WARN_HIGH_CURRENT"><s:message code="profile.page.subscribe"/></label></td>
		</tr>
		<tr>
			<td><s:message code="smartpoint.status.LOW_CURRENT"/></td>
			<td class="small-label"><input type="checkbox" class="send-email"  title="low Current" id="SUBSCRIBE_WARN_LOW_CURRENT"> <label for="SUBSCRIBE_WARN_LOW_CURRENT"><s:message code="profile.page.subscribe"/></label></td>
		</tr>
		<tr>
			<td><s:message code="smartpoint.status.LIGHT_QUALITY"/></td>
			<td class="small-label"><input type="checkbox" class="send-email"  title="Light Quality"  id="SUBSCRIBE_WARN_LIGHT_QUALITY"> <label for="SUBSCRIBE_WARN_LIGHT_QUALITY"><s:message code="profile.page.subscribe"/></label></td>
		</tr>
	</tbody>
</table>

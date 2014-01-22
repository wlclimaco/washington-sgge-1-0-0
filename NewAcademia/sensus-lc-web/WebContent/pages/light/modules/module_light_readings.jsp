<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<div id="readings" class="point-detail-container">
	<h3><s:message code="smartpointdetail.page.readings" />
		<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
			<a id="get-readings" href="#" class="button small right"><s:message code="smartpointdetail.page.getreadings"/></a>
			<a id="reset-min-max" href="#" class="button small right"><s:message code="smartpoint.actions.reset" /></a>
		</sec:authorize></h3>
		<div class="highlight ui-helper-clearfix">
			<table class="small-table">
				<tr>
					<td  width="15%"><s:message code="smartpointdetail.page.meterReading" />:</td>
					<td class="reading_values"><span id="total-consumption"></span><sup><s:message code="smartpointdetail.status.kilowattsSymbol" /></sup></td>
					<td width="45%"></td>
				</tr>
			 </table>
		</div>
	<div class="sui-pad">
		<table class="small-table">
			<tr>
				<th></th>
				<th><s:message code="smartpointdetail.page.last" /></th>
				<th><s:message code="smartpointdetail.page.min" /></th>
				<th><s:message code="smartpointdetail.page.max" /></th>
			</tr>
			<tr>
				<td width="15%"><s:message code="smartpointdetail.status.voltage" />:</td>
				<td class="reading_values"><span id="voltage-last"></span><sup><small><s:message code="smartpointdetail.status.voltageSymbol" /></small></sup></td>
				<td class="reading_values"><span id="voltage-min"></span><small><sup><s:message code="smartpointdetail.status.voltageSymbol" /></sup></small></td>
				<td class="reading_values"><span id="voltage-max"></span><small><sup><s:message code="smartpointdetail.status.voltageSymbol" /></sup></small></td>
			</tr>
			<tr>
				<td><s:message code="smartpointdetail.status.current" />:</td>
				<td class="reading_values"><span id="current-last"></span><sup><small><s:message code="smartpointdetail.status.currentSymbol" /></small></sup></td>
			</tr>
			<tr>
				<td><s:message code="smartpointdetail.status.power" />:</td>
				<td class="reading_values"><span id="power-last"></span><sup><small><s:message code="smartpointdetail.status.watageSymbol" /></small></sup></td>
			</tr>
		 </table>
	</div>
 </div>
 <script src="scripts/pages/light/modules/module_light_readings.js"></script>
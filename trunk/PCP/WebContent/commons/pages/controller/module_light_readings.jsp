<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<div id="readings" class="point-detail-container">
	<h3><s:message code="smartpointdetail.page.readings" />
		<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
			<a id="reset-min-max" href="#" class="button small right">Reset Min/Max</a>
		</sec:authorize></h3>
	<div class="sui-pad">
		<table class="small-table">
			<tr>
				<th></th>
				<th class="num"><s:message code="smartpointdetail.page.last" /></th>
				<th class="num"><s:message code="smartpointdetail.page.min" /></th>
				<th class="num"><s:message code="smartpointdetail.page.max" /></th>
			</tr><tr>
				<td width="15%"><s:message code="smartpointdetail.status.voltage" />:</td>
				<td class="num"><span id="voltage-last"></span><sup><small><s:message code="smartpointdetail.status.voltageSymbol" /></small></sup></td>
				<td class="num"><span id="voltage-min"></span><small><sup><s:message code="smartpointdetail.status.voltageSymbol" /></sup></small></td>
				<td class="num"><span id="voltage-max"></span><small><sup><s:message code="smartpointdetail.status.voltageSymbol" /></sup></small></td>
			</tr><tr>
				<td><s:message code="smartpointdetail.status.current" />:</td>
				<td class="num"><span id="current-last"></span><sup><small><s:message code="smartpointdetail.status.currentSymbol" /></small></sup></td>
			</tr><tr>
				<td><s:message code="smartpointdetail.status.power" />:</td>
				<td class="num"><span id="power-last"></span><sup><small><s:message code="smartpointdetail.status.watageSymbol" /></small></sup></td>
			</tr>

		 </table>
	</div>
 </div>
 <script src="commons/scripts/controller/module_light_readings.js"></script>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasRole('ROLE_EPM_SERVICE_ELECTRIC') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="action_configure_alarms" class="action-dialog">
		<form id="actionCofigureAlarmsForm" name="actionCofigureAlarmsForm" action="" method="post">
			<fieldset>
				<legend id="set-tamper-dialog-legend"></legend>
				<table class="small-table">
					<thead>
						<tr>
							<td id="lcmAlarms"><strong><spring:message code="devicedetail.dialog.setTamper.lcmAlarms"/></strong></td>
							<td class="cell-center"></td>
							<td class="cell-center"></td>
						</tr>
					</thead>
					<tbody>
						<tr id="relay1">
							<td><spring:message code="devicedetail.dialog.setTamper.relay1"/></td>
							<td class="cell-center"><spring:message code="smartpointdetail.tabs.about.tamperDetectTimeout"/></td>
							<td class="cell-center">
								<input id="minutes-relay1" name="1" class="date-short validate[custom[integer], min[0]]" type="text"/> <spring:message code="commons.pages.minutes"/>
							</td>
						</tr>
						<tr id="relay2">
							<td><spring:message code="devicedetail.dialog.setTamper.relay2"/></td>
							<td class="cell-center"><spring:message code="smartpointdetail.tabs.about.tamperDetectTimeout"/></td>
							<td class="cell-center">
								<input id="minutes-relay2" name="2" class="date-short validate[custom[integer], min[0]]" type="text"/> <spring:message code="commons.pages.minutes"/>
							</td>
						</tr>
						<tr id="relay3">
							<td><spring:message code="devicedetail.dialog.setTamper.relay3"/></td>
							<td class="cell-center"><spring:message code="smartpointdetail.tabs.about.tamperDetectTimeout"/></td>
							<td class="cell-center">
								<input id="minutes-relay3" name="3" class="date-short validate[custom[integer], min[0]]" type="text"/> <spring:message code="commons.pages.minutes"/>
							</td>
						</tr>
					</tbody>
				</table>
			</fieldset>
		</form>
	</div>

</sec:authorize>
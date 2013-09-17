<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and 
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<%-- Add SmartPoints to Group Action Dialog --%>
	<div id="action-dialog-panel" class="action-dialog">
	<form id="add_schedule_form" name="add_event_schedule_form" method="post"><!-- Messaging -->
	<h2><span class="smartpoint-count"></span>&nbsp;<s:text
		name="action.addtoeventschedule.label.smartpoints"></s:text></h2>

	<%-- Messaging --%>
	<div id="action-messaging" class="messaging"><span
		class="message"></span></div>

	<%-- Additional Fields --%>
	<fieldset class="two-line">
	<ul>
		<li class="ui-widget">
			<label for="event_schedule_add_select">
				<spring:message code="action.schedule.label.schedulehint"/>&nbsp;
				<span class="required">*</span>
			</label>
			<br />
			<div id="select_event_schedule">
				<s:select name="add_schedule_select" list="scheduledEventsByActionType"
				listKey="id" id="add_schedule_select" cssClass="combobox" listValue="value"
				 multiple="false" size="1" required="false" />
			</div>
		</li>
	</ul>
	</fieldset>
	</form>
	</div>

</sec:authorize>
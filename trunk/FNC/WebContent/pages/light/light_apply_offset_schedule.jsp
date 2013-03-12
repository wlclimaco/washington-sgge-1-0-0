<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
<%-- Add SmartPoints to Group Action Dialog --%>
<div id="action-dialog-panel" class="action-dialog">
<form id="add-schedule-form" name="add_event_schedule_form" method="post"><!-- Messaging -->
<h2><span class="smartpoint-count"></span>&nbsp;<s:message code="action.addtoeventschedule.label.smartpoints" /></h2>

<%-- Messaging --%>
<div id="action-messaging" class="messaging"><span
	class="message"></span></div>

<%-- Additional Fields --%>
<fieldset class="two-line">
<ul>
	<li class="ui-widget">
		<label for="offset_schedule_add_select">
			<s:message code="action.addtoschedule.label.offsetschedulehint" />&nbsp;
			<span class="required">*</span>
		</label>
		<br />
		<div id="select_offset_schedule">
			<select name="add_schedule_select" list="offsetScheduleList" listKey="id" id="add-schedule-select" cssClass="combobox" listValue="value" multiple="false" size="1" required="false">

			</select>
		</div>
	</li>

</ul>
<div class="highlight"><small><!--<input type="checkbox">--><s:message code="action.addtoschedule.label.offsetschedulehintReplace" /></small></div>
</fieldset>
</form>
</div>


</sec:authorize>
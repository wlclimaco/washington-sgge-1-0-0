<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%-- Add SmartPoints to Group Action Dialog --%>
<div id="action-dialog-panel" class="action-dialog">
<form id="add-schedule-form" name="add_event_schedule_form" method="post"><!-- Messaging -->
<h2><span class="smartpoint-count"></span>&nbsp;<s:text
	name="action.addtoeventschedule.label.smartpoints"></s:text></h2>

<%-- Messaging --%>
<div id="action-messaging" class="messaging"><span
	class="message"></span></div>
	
<%-- Additional Fields --%>
<fieldset class="two-line">
<ul>
	<li class="ui-widget">
		<label for="offset_schedule_add_select">
			<s:text name="action.addtoschedule.label.offsetschedulehint"></s:text>&nbsp;
			<span class="required">*</span>
		</label> 
		<br />
		<div id="select_offset_schedule">
			<s:select name="add_schedule_select" list="offsetScheduleList" listKey="id" id="add-schedule-select" cssClass="combobox" listValue="value" multiple="false" size="1" required="false" />
		</div>
	</li>
	
</ul>
<div class="highlight"><small><!--<input type="checkbox">--><s:text name="action.addtoschedule.label.offsetschedulehintReplace"></s:text></small></div>
</fieldset>
</form>
</div>

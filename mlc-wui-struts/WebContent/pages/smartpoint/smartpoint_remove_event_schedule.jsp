<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%-- Reset SmartPoint Schedule Dialog --%>
<div id="action-dialog-panel" class="action-dialog">
<form id="add-schedule-form" name="create_schedule_form" method="post">

<%-- Messaging --%>
<h2>
	<s:text name="action.removefromschedule.label.smartpoints1"></s:text>&nbsp;<span class="smartpoint-count"></span>&nbsp;<s:text name="action.removefromschedule.label.smartpoints2"></s:text>
</h2>
<div id="action-messaging" class="messaging"><span class="message"></span></div>

<%-- Additional Description --%>
<div class="highlight"><small><s:text
	name="action.removefromschedule.label.hint"></s:text></small></div>
	
</form>
</div>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<sec:authorize access="hasAnyRole('ROLE_Role.Admin','ROLE_Role.System Operator')">

<%-- Reset SmartPoint Schedule Dialog --%>
<div id="action-dialog-panel" class="action-dialog">
<form id="add-schedule-form" name="create_schedule_form" method="post">

<%-- Messaging --%>
<h2>
	<s:message code="action.removefromschedule.label.smartpoints1"/>&nbsp;<span class="smartpoint-count"></span>&nbsp;<s:message code="action.removefromschedule.label.smartpoints2"/>
</h2>
<div id="action-messaging" class="messaging"><span class="message"></span></div>

<%-- Additional Description --%>
<div class="highlight"><small><s:message code="action.removefromschedule.label.hint"/></small></div>

</form>
</div>

</sec:authorize>
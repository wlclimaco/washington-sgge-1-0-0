<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<%-- Remove Smartpoints from Group Action Dialog --%>
<div id="action-dialog-panel" class="action-dialog">
<form id="add-group-form" name="add_group_form" method="post">

<%-- Messaging --%>
<h2><span class="smartpoint-count"></span>&nbsp;<s:message code="action.removefromgroup.label.smartpoints"/></h2>
<div id="action-messaging" class="messaging"><span
	class="message"></span></div>

<%-- Additional Fields --%>
<fieldset class="two-line">
<ul>
	<li class="ui-widget"><label for="group_add_select">
	<s:message code="action.removefromgroup.label.grouphint"/>&nbsp;<span class="required">*</span></label> <br />
	<select name="group_add_select" id="group-add-select" class="combobox"></select>
	</li>

</ul>
</fieldset>
</form>
</div>

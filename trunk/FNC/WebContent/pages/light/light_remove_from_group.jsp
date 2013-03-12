<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%-- Remove Smartpoints from Group Action Dialog --%>
<div id="action-dialog-panel" class="action-dialog">
<form id="add-group-form" name="add_group_form" method="post">

<%-- Messaging --%>
<h2><span class="smartpoint-count"></span>&nbsp;<s:text
	name="action.removefromgroup.label.smartpoints"></s:text></h2>
<div id="action-messaging" class="messaging"><span
	class="message"></span></div>

<%-- Additional Fields --%>
<fieldset class="two-line">
<ul>
	<li class="ui-widget"><label for="group_add_select"><s:text
		name="action.removefromgroup.label.grouphint"></s:text>&nbsp;<span
		class="required">*</span></label> <br />
	<s:select name="group_add_select" list="groupList" listKey="id"
		id="group-add-select" cssClass="combobox" listValue="value"
		multiple="false" size="1" required="false" /></li>
		
</ul>
</fieldset>
</form>
</div>

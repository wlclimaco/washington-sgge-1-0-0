<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<%-- Create Group Dialog --%>
<div id="action-dialog-create" class="action-dialog">
<form id="create-group-form" name="create_group_form" method="post">
<fieldset class="two-line">

<%-- Messaging --%>
<div id="create-group-messaging" class="messaging"><span
	class="message"></span></div>

<%-- Form Fields --%>
<ul>
	<li class="add-group-control"><label for="control_group_name_create"><s:text
		name="action.addgroup.label.grouplabel"></s:text>&nbsp;<span
		class="required">*</span></label><br />
	<input type="text" id="control-group-name-create" tabindex="1"
		class="required" /> <span class="note"><s:text
		name="action.addgroup.label.grouphint"></s:text></span></li>
	<li class="add-group-control"><label for="control_group_description"><s:text
		name="action.addgroup.label.descrlabel"></s:text></label><br />
	<textarea id="control_group_description" tabindex="2"></textarea></li>
</ul>
</fieldset>
</form>
</div>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<div id="edit-status-container" class="action-dialog">
	<fieldset>
        <ul class="radio-list">
            <li class="radio"><input type="radio" checked="checked" id="status-ative" name="change-status" value="ACTIVE"/><label for="status-ative"><s:message code="smartpoint.actions.editLightStatus.active" /></label></li>
            <li class="radio"><input type="radio" id="status-deative" name="change-status" value="DEACTIVATED"/><label for="status-deative"><s:message code="smartpoint.actions.editLightStatus.deactive" /></label></li>
            <li class="radio"><input type="radio" id="status-maintain" name="change-status" value="MAINTENANCE"/><label for="status-maintain"><s:message code="smartpoint.actions.editLightStatus.maintenance" /></label></li>
        </ul>
    </fieldset>
	<div class="highlight"><small><s:message code="smartpoint.actions.editLightStatus.note" /></small></div>
</div>

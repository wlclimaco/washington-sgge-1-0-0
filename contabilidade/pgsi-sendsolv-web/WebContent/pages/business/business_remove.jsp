<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<%-- Delete Group Dialog --%>
<div id="action-dialog-delete" class="small-dialog-container">

	<div class="delete-dialog-container">
		<span class="icon-nav icon-exclamation-triangle icon-medium-button error"></span>
		<span id="errorText" class="text"></span><br>
		<span class="text"><s:message code="commons.pages.errorBusiness"/></span>
	</div>

</div>
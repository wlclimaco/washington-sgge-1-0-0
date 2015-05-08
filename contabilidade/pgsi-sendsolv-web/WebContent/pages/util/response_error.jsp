<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<div id="response-error">
	<div class="container">
		<div class="title">
			<span class="icon-nav icon-exclamation-triangle icon-medium-button error"></span>
			<h5 id="error"></h5>
		</div>
		<p id="error-time"><s:message code="commons.dialog.error.message.time" text="" /></p>
		<p id="error-message"><s:message code="commons.dialog.error.message.tryagain" text="" /></p>
		<div class="suport">
			<span><s:message code="commons.dialog.error.suport" text="" /></span>
			<div class="info-container">
				<span class="email">support@pgsi.com</span>
				<br>
				<span class="phone">1-800-999-9999</span>
			</div>
		</div>
	</div>
</div>
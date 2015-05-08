<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<%-- Delete Group Dialog --%>
<div id="dialog-status-batch">
	<form id="dialogStatusBatchForm">
		<hr class="top">
		<div class="alert hide">
			<span class="icon-nav icon-exclamation-triangle"></span>
			<div class="label">
			</div>
		</div>
		<div class="label first"><s:message code="commons.pages.comment" text="default text" /></div>
		<textarea class="required" style="width: 450px" placeholder="*" name="comment"></textarea>
		<hr class="last">
	</form>
</div>

<jsp:include page="../../scripts/pages/payments/money_transfer_batch_status_init.js.jsp" flush="true" />
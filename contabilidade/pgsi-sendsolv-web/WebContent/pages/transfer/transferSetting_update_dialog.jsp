<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>

<form id="transfer-input" method="post" action="#">
	<hr class="top">
	<div id="recipientListRadio">

	</div>
	<!--  -->

	<div class="newline">
	<input type="radio" class="first spacer search" name="recipient" value="2">
		<label class="spacer"><s:message code="pages.recipient.view.recipientId" text="default text" /></label>
		<input type="text" class="width-short spacer recipientId" id="recipientParticipantId" name="recipientParticipantId" data-inputmask="'mask': '***-*****'">
		<label class="parent spacer"></label>&nbsp;
		<label class="spacer active"></label>
	</div>
	<!--  -->
	<div class="newline">
	<input type="radio" class="first " name="recipient" value="0" checked="checked" >
	<label class=""><s:message code="pages.recipient.view.enter.recipient" text="default text" /></label>
	<hr class="last">
	</div>
</form>
<jsp:include page="../../scripts/pages/transfers/transferSetting_create_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/transfers/transferSetting_init.js.jsp" flush="true" />

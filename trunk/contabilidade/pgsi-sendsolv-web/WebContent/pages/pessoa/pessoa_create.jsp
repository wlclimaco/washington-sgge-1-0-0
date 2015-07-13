<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>

<div class="content business-create">
	<form id="business-form" method="post" action="#">

		<jsp:include page="business_fields.jsp" flush="true" />

		<div class="buttons-form">
			<input id="add-button" type="submit" value="<s:message code='commons.pages.save' text='default text' />" class="btn ui-button ui-widget ui-state-default ui-corner-all" role="button">

			<input type="reset" onClick="window.history.back();" value="<s:message code='commons.pages.cancel' text='default text' />" class="btn ui-button ui-widget ui-state-default ui-corner-all" role="button">

		</div>

	</form>
</div>

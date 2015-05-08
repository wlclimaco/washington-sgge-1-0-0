<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>

<form id="batch-date-transaction" method="post" action="#">
	<hr class="top">
		<div class="alert hide">
			<span class="icon-nav icon-exclamation-triangle"></span>
			<div class="label">
			</div>
		</div>
		<div class="row-form">
			<div class="updateDate"><div class="label first col-form"><s:message code="pages.batches.dialog.transfer.date" text="default text" /></div><input name="date" id="date" class="width-short date required" placeholder="*" type="text"></div>
			<div class="reactive hide"><div class="label first col-form"><s:message code="pages.batches.dialog.expiration.date" text="default text" /></div><input name="date" id="expiration" class="width-short date required" placeholder="*" type="text"></div>
			<div class="label first col-form "><s:message code="commons.pages.comment" text="default text" /></div><textarea name="comment" id="comment"class="note required" minlength="5" maxlength="255" placeholder="*" style="width: 250px"></textarea>
		</div>
	<hr class="last">
</form>
<jsp:include page="../../scripts/pages/payments/batches_view_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/payments/money_transfer_batch_status_init.js.jsp" flush="true" />

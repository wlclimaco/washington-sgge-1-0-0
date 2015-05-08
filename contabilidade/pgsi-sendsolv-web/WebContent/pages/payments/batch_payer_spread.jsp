<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<div id="spreadReviewDialog">
	<hr class="top">
	<div class="label first section">
		<b></b>
	</div>
	<div class="label payer first"><s:message code="pages.batches.dialog.reviewspread.label.custom" text="default text" /></div>
	<input type="text" class="spread" id="customSpread" style="width: 80px; text-align: right" value="">
	<div class="label" style="margin-right: 24px">%</div>
	<a href="#" id="applyToSelection" class="btn spread"><s:message code="pages.batches.buttons.apply" text="default text" /></a>

	<a href="#" id="resetSelection" class="btn spread"><s:message code="pages.batches.buttons.reset" text="default text" /></a>
	<hr>

	<div id="data_list_container">
		<table id="dialog_data_list"></table>
	</div>

</div>

<jsp:include page="../../scripts/pages/payments/batch_payer_spread_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/payments/batch_payer_spread.init.js.jsp" flush="true" />
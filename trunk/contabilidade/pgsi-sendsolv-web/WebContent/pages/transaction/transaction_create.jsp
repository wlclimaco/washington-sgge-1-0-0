<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<div id="dialog-one-off" style="width: auto; min-height: 50px; max-height: none; height: auto;" class="ui-dialog-content ui-widget-content">
	<form id="dialog-one-off-form">
		<hr class="top">
		<input type="radio" class="first radio newline" name="batch" value="N" id="new-batch">
		<label><s:message code="pages.batches.dialog.createNewBatchFromSelection.createNewBatch" text="default text" /></label>

		<label class="first indent new-batch hide newline" style="display: block;" id=""><s:message code="commons.pages.description" text="default text" /></label>
		<input type="text" id="description" name="description" class="width-medium new-batch hide" placeholder="*" style="display: block;">

		<label class="new-batch hide" style="display: block;"><s:message code="pages.batches.view.transfer.date" text="default text" /></label>
		<input type="text" id="transfer-date" name="transferDate" class="width-shortest date new-batch hide" placeholder="*" style="display: block;">

		<hr>

		<label class="first"><s:message code='pages.transfer.form.label.total.deduction' text='default text' /></label>
		<input id="amount" class="required width-short" placeholder="*" type="text" data-inputmask="'alias': 'numeric', 'groupSeparator': ',', 'autoGroup': true, 'digits': 2, 'digitsOptional': false, 'prefix': 'USD ', 'placeholder': '0'" name="amount">

		<label class=""><s:message code='pages.transfer.form.label.pricing.plan' text='default text' /></label>
		<select id="pricing" class="width-short" style="display: none;">
		</select>

		<label class=""><s:message code='pages.transfer.form.label.promotional.fee' text='default text' /></label>
		<input id="fee" class=" width-shortest" type="text" data-inputmask="'alias': 'numeric', 'groupSeparator': ',', 'autoGroup': true, 'digits': 2, 'digitsOptional': false, 'prefix': 'USD ', 'placeholder': '0'">

		<label class="new-batch first" style="display: block;"><s:message code="commons.pages.expires" text="default text" /></label>
		<input type="text" id="expires" class="width-shortest date new-batch" style="display: block;">

		<label class="first newline" for="note"><s:message code='commons.pages.note' text='default text' /></label>
		<textarea id="note" style="width: 70%"></textarea>

		<hr class="last">
	</form>
</div>

<jsp:include page="../../scripts/pages/transaction/transaction_create_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/transaction/transaction_create_init.js.jsp" flush="true" />

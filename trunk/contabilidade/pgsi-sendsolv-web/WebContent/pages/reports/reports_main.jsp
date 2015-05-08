<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<nav class="secondary">
	<span><s:message code="pages.ardm.label.compliance" text="default text" /></span>
	<span class="icon-nav icon-angle-right"></span>
	<span><s:message code="pages.ardm.label.abnormalBehaviour" text="default text" /></span>
</nav>

<h2>
	<s:message code="pages.adrm.label.title" text="default text" />
</h2>

<div id="adrm-report-wrapper" class="content">
	<form id="adrm-report">
		<label class="" for="date-range-start"><s:message code="pages.ardm.label.dateRange" text="default text" /></label>
		<input id="date-range-start" type="text" class="date required" placeholder="*" name="fromDate">
		<label><s:message code="pages.ardm.label.to" text="default text" /></label>
		<input id="date-range-end" type="text" class="date required" placeholder="*" name="toDate">

		<hr style="margin-bottom: 20px;">

		<div style="float: left; position: relative;">
			<fieldset>
				<legend><s:message code="pages.ardm.label.transactions" text="default text" /></legend>
				<label class="first" for="number-of-days">
				<s:message code="pages.ardm.label.numberOfDays" text="default text" />
				</label>
				<input id="number-of-days" type="text" class="small" number="true" name="numberOfDays">
				<label class="first" for="number-of-transactions">
					<s:message code="pages.ardm.label.numberOfTransactions" text="default text" />
				</label>
				<input class="small" type="text" id="number-of-transactions" number="true" name="numberOfTransaction">

				<label class="first" for="transfer-value">
					<s:message code="pages.ardm.label.amount" text="Amount in US dollars of a single transaction:" />
				</label>
				<input class="medium" type="text" id="transfer-value" name="transferValue" data-inputmask="'alias': 'numeric', 'groupSeparator': ',', 'autoGroup': true, 'digits': 2, 'digitsOptional': false, 'prefix': 'USD ', 'placeholder': '0'">
			</fieldset>
		</div>

		<div style="float: left; position: relative; clear: left; margin-top: 20px;">
			<fieldset>
				<legend><s:message code="pages.ardm.label.sendersRecipients" text="default text" /></legend>

				<label class="first large" for="number-of-recipients">
				<s:message code="pages.ardm.label.numberOfRecipients" text="Number of recipients receiving transactions from one sender:" />
				</label>
				<input id="number-of-recipients" type="text" class="small" number="true" name="numberOfRecipients">

				<label class="first large" for="number-of-members">
					<s:message code="pages.ardm.label.numberOfMembers" text="Number of members sending transactions to one recipient:" />
				</label>
				<input type="text" id="number-of-members" class="small" number="true" name="numberOfMembers">
			</fieldset>
		</div>

		<div class="buttons-form">
			<div>
				<input type="button" id="cancel" value='<s:message code="commons.pages.cancel" text="default text" />' onclick="window.history.back();">
				<input id="submit-button" value='<s:message code="" text="Generate Report" />' type="submit">
			</div>
		</div>

	</form>
</div>

<jsp:include page="../../scripts/pages/reports/reports_init.js.jsp" flush="true" />
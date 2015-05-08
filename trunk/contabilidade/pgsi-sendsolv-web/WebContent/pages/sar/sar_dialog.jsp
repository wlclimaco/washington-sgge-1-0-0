
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec'
    uri='http://www.springframework.org/security/tags'%>

<input type="hidden" name="sar-id" id="sar-id" />
<input type="hidden" name="participant-id" id="participant-id" />
<input type="hidden" name="sar-type" id="sar-type" />
<input type="hidden" name="participant-name" id="participant-name" />
<input type="hidden" name="sar-business-id" id="sar-business-id" />


<div class="person-content sar">
	<form id="SAR-form" method="post" action="#">
    <div class="section-1">
	<hr style="margin-top: 0;">
		<div>
            <label class="first"><s:message code="pages.sar.dialog.activity.summary" text="default text" /></label>
            <input type="text" id="summary" name="summary" class="required width-long" placeholder="*">
        </div>
		<div class="update">
			<hr class="top">
				<label class="recipientStatus"></label>
				<label class="first"><s:message code="pages.sar.dialog.report.target.id" text="default text" /></label>
				<div><input type="text" name="recipientId" id="recipientParticipantId" class="width-short recipientId" placeholder="*" data-inputmask="'mask': '***-*****'"><label class="parent" id="nameParticipant"></label></div>
			<div id="reportTarget">
				<label class="first"><s:message code="pages.sar.dialog.payroll.source" text="default text" /></label>
					<div id="locationName"></div>
				<hr>
			</div>
		</div>

        <div>
            <label class="first"><s:message code="pages.sar.dialog.activity.period" text="default text" /></label>
			<div><label>From </label><input id="activityStartDateTimeUTC" class=" width-short date datepicker" type="text"></div>
		</div>
		<div>
			<div><label>To </label><input id="activityStopDateTimeUTC" class=" width-short date datepicker" type="text"></div>
        </div>
        <div>
            <label class="first"><s:message code="pages.sar.dialog.total.amount" text="default text" /></label>
            <input type="text" id="amount" name="amount" class="required width-short" placeholder="*"  data-inputmask="'alias': 'numeric', 'groupSeparator': ',', 'autoGroup': true, 'digits': 2, 'digitsOptional': false, 'prefix': 'USD ', 'placeholder': '0'">
        </div>
		<hr>
		<div>
            <label class="first" for="note"><s:message code="pages.sar.dialog.activity.detail" text="default text" /></label>
            <textarea id="note" class="required" name="note" style="width: 70%; max-height: 100px;" placeholder="*"></textarea>
		</div>
</div>


</form>
</div>

<jsp:include page="../../scripts/pages/sar/sar_dialog_init.js.jsp" flush="true" />

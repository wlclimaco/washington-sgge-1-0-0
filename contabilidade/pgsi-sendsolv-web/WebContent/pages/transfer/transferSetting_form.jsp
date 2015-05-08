

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec'
    uri='http://www.springframework.org/security/tags'%>

<div class="person-content">
	<form id="transfer-form" method="post" action="#">
	<input type="hidden" name="locationId" id="location-id" value="0" />
	<input type="hidden" name="busdinessPlanId" id="busdinessPlanId" value="0" />
	<input type="hidden" name="productPlanApplicability" id="productPlanApplicability" value="0" />
	<input type="hidden" name="currency" id="currencyId" value="" />
	<input type="hidden" name="countryId" id="countryId" value="" />
	<input type="hidden" name="oldbusdinessPlanId" id="oldbusdinessPlanId" value="" />
	<input type="hidden" name="payer" id="payerId" value="" />
	<input type="hidden" name="transferMe" id="transferMeId" value="" />
	<input type="hidden" name="locationName" id="location-name" value="0" />
	<input type="hidden" name="memberId" id="member-id" value="0" />
	<input type="hidden" id="recipientId" value="" />
	<input type="hidden" name="employmentInfoId" id="employmentInfo-id" value="" />
	<input type="hidden" name="transferId" id="transfer-id" value="" />
	<input type="hidden" name="effectiveStartDate" id="effectiveStartDate" value="" />
	<input type="hidden" name="noteId" id="noteId" value="" />
	<input type="hidden" name="customFee" id="customFeeId" value="0" />
	<input type="hidden" name="customVersion" id="customVersion" value="0" />

    <div class="section-1">
		<div class="update hide">
			<hr class="top">
			<div id="dialog-edit">
				<label class="first"><s:message code="commons.pages.recipient" text="default text" /></label>
				<label class="parent recipientName"></label><a class="form-link lower" title="" href="#"><span class="icon-nav icon-search-find"></span><s:message code="commons.pages.view" text="default text" /></a>
				<label class="recipientStatus"></label>
                <sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
                    <label class="first"><s:message code="pages.recipient.view.recipientId" text="default text" /></label>
                    <input type="text" name="recipientId" id="recipientParticipantId" class="width-short recipientId" data-inputmask="'mask': '***-*****'">
                </sec:authorize>
				<hr style="margin-top: 0;">
			</div>
			<div id="dialog-insert" class="hide">
				<label class="first"><s:message code="commons.pages.recipient" text="default text" /></label>
				<label class="parent"></label>
				<a class="form-link lower" title="" href="#"><span class="icon-nav icon-search-find"></span><s:message code="commons.pages.view" text="default text" /></a>
				<br>
				<br>
				<hr style="margin-top: 0;">
			</div>
			<label class="first"><s:message code="pages.transfer.form.label.payroll.source" text="default text" /></label>
				<div id="locationName"></div>
			<hr>
		</div>
        <div class="insert">
            <label class="first"><s:message code="commons.pages.member" text="default text" /></label><label class="parent memberName"></label>
            <a href="" title="" class="form-link memberTitle lower alist"><span class="icon-nav icon-search-find"></span><s:message code="commons.pages.view" text="default text" /></a>
            <label class="first"><s:message code="commons.pages.recipient" text="default text" /></label><label class="parent recipientName"></label>
            <a href="" title="" class="form-link recipientTitle lower alist"><span class="icon-nav icon-search-find"></span><s:message code="commons.pages.view" text="default text" /></a>
            <label class="first"><s:message code="pages.transfer.form.label.payroll.source" text="default text" /></label>
            <label class="parent locationName"></label>
            <a href="" class="form-link lower locationTitle a list" title=""><span class="icon-nav icon-search-find alist"></span><s:message code="commons.pages.view" text="default text" /></a>

			<hr>
		</div>
        <div>
            <label class="first"><s:message code="pages.transfer.form.label.total.deduction" text="default text" /></label>
            <input type="text" id="amount" name="amount" class="required width-short" placeholder="*"  data-inputmask="'alias': 'numeric', 'groupSeparator': ',', 'autoGroup': true, 'digits': 2, 'digitsOptional': false, 'prefix': 'USD ', 'placeholder': '0'">
        </div>
        <div>
            <label class="first"><s:message code="pages.transfer.form.label.pricing.plan" text="default text" /></label>
            <select id="pricing" name="pricing" class="width-short required" placeholder="*">
                <option></option>
            </select>
			<label class=""><s:message code="pages.transfer.form.label.plan.category" text="default text" /></label>
            <select id="planCategoryId" name="planCategoryId" class="width-short required" placeholder="*">
                <option></option>
            </select>
        </div>
        <!--  -->
        <div>
            <label class="first"><s:message code="pages.transfer.form.label.promotional.fee" text="default text" /></label>
			<input type="text" id="fee" name="fee" class="width-shortest" data-inputmask="'alias': 'numeric', 'groupSeparator': ',', 'autoGroup': true, 'digits': 2, 'digitsOptional': false, 'prefix': 'USD ', 'placeholder': '0'">
		</div>
		<div>
			<label> <s:message code="pages.transfer.form.label.expires" text="default text" /></label>
			<input id="fee-exp" class=" width-short date datepicker" type="text">
        </div>
        <div>
            <label class="first"><s:message code="pages.transfer.form.label.country" text="default text" /></label>
            <select id="destcountry" name="destcountry" class="width-medium required" placeholder="*">
                <option></option>
            </select>
        </div>
        <div>
            <label class="first"><s:message code="pages.transfer.form.label.destination.currency" text="default text" /></label>
            <select
                id="currency" name="currency" class="width-short required" placeholder="*">
                <option></option>
            </select>
        </div>
        <div>
            <label class="first"><s:message code="pages.transfer.form.label.transfer.method" text="default text" /></label>
            <select id="methodTransfer" name="methodTransfer" class="width-medium required" placeholder="*">
                <option></option>
            </select>
        <div>
            <label class=""><s:message code="pages.transfer.form.label.payer" text="default text" /></label>
            <select id="payer" name="payer" class="width-medium required" placeholder="*">
                <option></option>
            </select>
        </div>
		<div class="address hide">
			<hr>
			 <jsp:include page="../address/address_main.jsp" flush="true" />
        </div>
        <div class="deposit hide">
			<hr>
            <label class="first account"><s:message code="pages.transfer.form.label.account.number" text="default text" /></label>
			<input type="text" class="width-medium account" id="account" data-inputmask-regex="[0-9]{9}"></input>
			<label class="account"><s:message code="pages.transfer.form.label.account.type" text="default text" /></label>
			<select id="account-type" class="width-medium">
				<option></option>
			</select>
		</div>
		<div>
            <hr class="contact-info account">
            <label class="first"><s:message code="pages.transfer.form.label.schedule.type" text="default text" /></label>
            <select id="schedule" class="width-short required" name="schedule" placeholder="*">
                <option></option>
            </select>
		</div>
		<div>
            <label class="skips-label hide"><s:message code="pages.transfer.form.label.pay.cycle.skips" text="default text" /></label>
            <input type="text" name="skips" class="width-shortest skips hide required" placeholder="*" id="skips" data-inputmask-regex="[0-9]{2}" min="0" max="51"></input>
		</div>
		<div>
            <label class="first" style="height: 50px;"><s:message code="pages.transfer.form.label.pay.date" text="default text"  /></label>
			<div class="frequencyBasedEventCalendarList"><input type="hidden" class="datePayDay" name="datePayDay" id="datePayDay"/></div>

		</div>
            <hr>
		<div>
            <label class="first" for="note"><s:message code="pages.transfer.form.label.note" text="default text" /></label>
            <textarea id="note" style="width: 70%; max-height: 19px;"></textarea>
		</div>

        <hr style="margin-bottom: 0;">

            <div class="buttons-form">
                <div>
                    <input id="prev-button" value="<< Back" type="submit">
                    <input type="button" id="cancel" value="Cancel">
                    <input id="save-button" value='<s:message code="commons.pages.save" text="default text" />' type="submit">
                </div>
            </div>
        </div>
</form>
</div>
<jsp:include page="../../scripts/pages/member/member_create_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/address/address_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/address/address_init.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/transfers/transferSetting_create_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/transfers/transferSetting_actions.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/transfers/transferSetting_create_init.js.jsp" flush="true" />

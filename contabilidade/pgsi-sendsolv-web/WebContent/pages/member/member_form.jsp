<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>

<div class="person-content">
	<form id="member-form" method="post" action="#">
	<input type="hidden" name="locationId" id="location-id" value="0" />
	<input type="hidden" name="memberId" id="member-id" value="0" />
	<input type="hidden" name="documentId" id="document-id" value="" />
	<input type="hidden" name="ssnId" id="ssn-id" value="" />
	<input type="hidden" name="parentOrganizationId" id="parentOrganizationId" value="" />
	<input type="hidden" name="employmentInfoId" id="employmentInfoId" value="" />
	<input type="hidden" name="employmentInfoVersion" id="employmentInfoVersion" value="" />
	<input type="hidden" name="note-id" id="noteId" value="" />
	<div class="section-1">
		<div class="location-member">
			<div>
				<label class="first" for="location"><s:message code="pages.member.form.label.location" text="default text" /></label>
				<input id="location" name="memberLocation" class="width-longest location" placeholder="*" type="text">
			</div>
			<div class="remember">
				<input type="checkbox" id="remember" class="newline"><label for="remember"><s:message code="pages.member.form.label.rememberLocation" text="default text" /></label>
			</div>
		</div>
		<hr style="margin-top: 0;">
		<div>
			<label class="first" for="prefix"><s:message code="pages.contacts.form.label.prefix" text="default text" /></label>
			<select name="prefix" id="prefix" style="display: none;">
				<option></option>
			</select>
		</div>
		<div>
			<label class="first" for="firstname"><s:message code="pages.member.form.label.firstName" text="default text" /></label>
			<input id="firstname" name="firstname" class="required width-long" placeholder="*" type="text" minlength="1" maxlength="40" >
		</div>
		<div>
			<label for="middlename"><s:message code="pages.member.form.label.middleName" text="default text" /></label>
			<input id="middlename" class="width-medium" type="text" maxlength="40">
		</div>

		<div>
			<label class="first" for="lastname"><s:message code="pages.member.form.label.lastName" text="default text" /></label>
			<input id="lastname" name="lastname" class="required width-long" placeholder="*" type="text" minlength="1" maxlength="40">
		</div>

		<div>
			<label for="motherMaidenName"><s:message code="pages.member.form.label.mothername" text="Mother's Maiden Name" /></label>
			<input id="motherMaidenName" class="width-long valueOthernames" minlength="1" maxlength="40"  type="text">
		</div>

		<div>
			<label class="first" for="suffix"><s:message code="pages.member.form.label.suffix" text="default text" /></label>
			<select id="suffix" class="width-shortest">
			</select>
		</div>

		<jsp:include page="../person/other_names_main.jsp" flush="true" />

		<hr>

		<div>
			<label class="first"> <s:message code="pages.member.form.label.dateofBirth" text="default text" /></label>
			<input type="text" id="dob" class="required width-short datepicker" placeholder="*" name="dob">
		</div>
		<!--  -->
		<div>
			<label><s:message code="pages.member.form.label.snn" text="default text" /></label>
			<input type="text" id="ssn"  class="required width-short" minlength="11" maxlength="11">
		</div>
		<hr>
		<!-- Document -->
		<div id="section-document">
			<a href="#" id="toggle-id" title="Add ID Document" class="form-link first"><span class="icon-nav icon-plus"></span><s:message code="pages.member.form.label.addIDDocument" text="default text" /></a>
			<div id="id-section">
					<!--  -->
				<div>
					<label class="first id"><s:message code="pages.member.form.label.issuingCountry" text="default text" /></label>
					<select id="identification-country" name="identificationCountry" class="width-medium id document" >
						<option></option>
					</select>
				</div>
				<!--  -->
				<div>
					<label class="id"><s:message code="pages.member.form.label.issuingState" text="default text" /></label> <select
						id="identification-region" name="idstate" class="width-medium id">
						<option></option>
					</select>
				</div>
				<div>
					<label class="first id"><s:message code="pages.member.form.label.idType" text="default text" /></label>
					<select id="document-type" name="document-type" class="width-medium id document" >
						<option></option>
					</select>
				</div>
				<!--  -->
				<div>
					<label class="first id"><s:message code="pages.member.form.label.idNumber" text="default text" /></label>
					<input type="text" id="idnumber"  name="idnumber" class="width-short id document"  minlength="1" maxlength="80">
				</div>
				<!--  -->
				<div>
					<label class="first"><s:message code="pages.member.form.label.expirationDate" text="default text" /></label>
					<input type="text" id="idexpirationdate" name="idexpirationdate" class=" datepicker width-short document" >
				</div>
			</div>
			<hr>
		</div>

			<jsp:include page="../address/address_main.jsp" flush="true" />
		<hr>
			<jsp:include page="../phone/phone_main.jsp" flush="true" />
		<hr>
		<!--  -->
		<label class="first"><s:message code="pages.member.form.label.preferredLanguage" text="default text" /></label> <select
			id="preferredLanguage" class="width-short">
			<option></option>
		</select>
		<!--  -->
		<label><s:message code="pages.member.form.label.bestTime" text="default text" /></label>
		<input class="width-short" id="timetocall" name="timetocall" type="text"  maxlength="50">
		<!--  -->
		<label><s:message code="pages.member.form.label.gender" text="default text" /></label>
		<select id="gender" class="width-short"></select>
		<hr>
		<div>
				<label class="first" style="width: 150px"><s:message code="pages.member.form.label.countryOfBirth" text="default text" /></label>
			<input type="hidden" name="birthId" id="birthId" class="required" value="0" placeholder="*" />

			<select class="required" name="birth" id="birth">
				<option></option>
			</select>
		</div>
		<div>
			<input type="hidden" name="residenceId" id="residenceId" value="0" placeholder="*" />
			<label class=""><s:message code="pages.member.form.label.countryOfResidence" text="default text" /></label>
			<select class="required" placeholder="*" id="residence" name="residence">
				<option></option>
			</select>
		</div>
		<!--  -->
		<input type="hidden" name="citizenshipId" id="citizenshipId" value="0" placeholder="*" />
		<label class="first" style="width: 150px;"><s:message code="pages.member.form.label.countryOfCitizenship" text="default text" /></label>
			<select id="citizenship" class="required" placeholder="*" name="citizenship">
			<option></option>
		</select>
		<hr>
			<jsp:include page="../security_question/security_question_main.jsp" flush="true" />
		<hr>
			<jsp:include page="../email/email_main.jsp" flush="true" />
		<hr>
		<!-- Employment  -->
		<div id="section-employment">
			<a href="#" title="Add Employment Details" id="toggle-employment" class="form-link first">
				<span class="icon-nav icon-plus"></span><s:message code="pages.member.form.label.addEmployment" text="default text" />
			</a>

			<div id="employment-section">
				<label class="first"><s:message code="pages.member.view.employeeid" text="default text" /></label>
				<input id="employeeId" class="width-short" type="text">
				<label class="first"><s:message code="pages.member.form.label.jobTitle" text="default text" /></label>
				<input id="jobtitle" class=" width-long" type="text">
				<!--  -->
				<label class="first" for="street-address-line-2"><s:message code="pages.member.form.label.hireDate" text="default text" /></label>
				<input id="hiredate" type="text" class="width-short datepicker">
				<!--  -->
				<label><s:message code="pages.member.form.label.payPerPayPeriod" text="default text" /></label>
				<input class="width-short" id="currentpay" data-inputmask="'alias': 'numeric', 'groupSeparator': ',', 'autoGroup': true, 'digits': 2, 'digitsOptional': false, 'prefix': 'USD ', 'placeholder': '0'" type="text">
			</div>
			<hr>
		</div>

		<!-- Risk  -->
		<div id="section-risk">
			<label class="first"><s:message code="pages.member.form.label.risk" text="default text" /></label>
			<select id="risk" class="width-short">
				<option></option>
			</select>

			<label for="risknote"><s:message code="pages.member.form.label.riskNote" text="default text" /></label>
			<textarea id="risknote" class="width-longest" name="risknote" maxlength="255"></textarea>
			<hr>
		</div>

		<!-- Note  -->
		<div id="section-note">
			<label class="first" for="note"><s:message code="pages.member.form.label.otherNote" text="default text" /></label>
			<textarea id="note" style="width: 70%"></textarea>
			<hr>
		</div>

		<div class="buttons-form">
			<div>
				<input type="button" id="cancel" value='<s:message code="commons.pages.cancel" text="default text" />' onclick="window.history.back();">
				<input id="save-button" value='<s:message code="commons.pages.save" text="default text" />' type="submit">
				<input id="next-button" value='<s:message code="commons.pages.recipientadd" text="default text" /> >>' type="submit">
			</div>
		</div>

	</div>
</form>
</div>
<jsp:include page="../../scripts/pages/identification/identification_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/identification/identification_init.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/address/address_init.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/member/member_create_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/member/member_create_init.js.jsp" flush="true" />
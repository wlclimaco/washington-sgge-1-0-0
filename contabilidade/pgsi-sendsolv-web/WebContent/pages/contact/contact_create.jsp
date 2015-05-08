<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

    	<input type="hidden" name="contactId" id="contact-id" value="0" />
		<input type="hidden" name="nameListId" id="nameListId" value="0" />
		<input type="hidden" name="ssnID" id="ssnID" value="0" />
		<input type="hidden" name="noteId" id="noteId" value="0" />


		<div class="group-inputs">
			<div class="row-form">
				<div class="col-form">
					<label for="contactType">
						<s:message code="pages.contacts.form.label.contacttype" text="default text" />
					</label>
					<select name="contactType" id="contact-type" style="display: none;" class="required" >
						<option></option>
					</select>
				</div>
				<div class="col-form">
					<label for="contactTitle">
						<s:message code="pages.contacts.form.label.contacttitle" text="Contact Title" />
					</label>

					<input type="text" id="contact-title" name="contactTitle">

				</div>
			</div>
		</div>

		<div class="group-inputs">
			<div class="row-form">
				<div class="col-form">
					<label for="contact-prefix"><s:message code="pages.contacts.form.label.prefix" text="default text" />
					</label>
					<select name="contactPrefix" id="contact-prefix" style="display: none;">
						<option></option>
					</select>
				</div>

				<div class="col-form">
					<label for="contact-firstname">
						<s:message code="pages.contacts.form.label.firstname" text="default text" /></label>
					<input type="text" id="contact-firstname" name="contactFirstName" minlength="1" maxlength="40" class="required" placeholder="*">
				</div>

				<div class="col-form">
					<label for="contact-middlename" style="margin-right: 3px;"><s:message
							code="pages.contacts.form.label.middlename" text="default text" /></label>
					<input type="text" id="contact-middlename" name="contactMiddleName" minlength="1" maxlength="40">
				</div>
			</div>

			<div class="row-form">
				<div class="col-form">
					<label for="contact-lastname" style="margin-left: 185px;">
						<s:message code="pages.contacts.form.label.lastname" text="default text" /></label>
					<input type="text" id="contact-lastname" name="contactLastName"  minlength="1" maxlength="40" class="required" placeholder="*">
				</div>

				<div class="col-form">
					<label for="contact-maidenname">
						<s:message code="pages.contacts.form.label.otherNames" text="default text" />
					</label>
					<input type="text" id="contact-maidenname" name="contactMaidenName" minlength="1" maxlength="40">
				</div>

				<div class="col-form">
					<label for="contact-suffix" style="min-width: inherit;">
						<s:message code="pages.contacts.form.label.suffix" text="default text" />
					</label>
					<select name="contactSuffix" id="contact-suffix" style="display: none;">
						<option></option>
					</select>
				</div>
			</div>
		</div>

		<jsp:include page="../phone/phone_main.jsp" flush="true" />

		<jsp:include page="../email/email_main.jsp" flush="true" />

		<jsp:include page="../address/address_main.jsp" flush="true" />

		<div class="group-inputs">
			<div class="row-form">
				<div class="col-form">
					<label for="contact-social-security-number">
						<s:message code="pages.contacts.form.label.socialsecuritynumber" text="default text" /></label>
					<input type="text" id="contact-social-security-number" name="contactSocialSecurityNumber" class="phone">
				</div>

				<div class="col-form">
					<label for="contact-date-of-birth">
						<s:message code="pages.contacts.form.label.dateofbirth" text="default text" /></label>
					<input type="text" id="contact-date-of-birth" name="contactDateOfBirth" class="phone datepicker">
				</div>

				<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
					<div class="col-form">
						<label for="contact-pep" style="min-width: inherit">
							<s:message code="pages.contacts.form.label.pep" text="PEP" />
						</label>
						<select name="contactPep" id="contact-pep" class="phone" style="display: none;">
						</select>
					</div>


					<div class="col-form">
						<label for="contact-risk"><s:message code="commons.pages.risklevel" text="default text" /></label>
						<select name="contactRisk" id="contact-risk" style="display: none;" Class="phone">
						</select>
					</div>

				</sec:authorize>
			</div>

			<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">

				<div class="row-form">
					<div class="col-note">
						<label for="riskNote" style="margin-right: 25px;">
							<s:message code="commons.pages.riskNote" text="default text" />
						</label>
						<textarea id="riskNote" minlength="10" maxlength="255" name="riskNote" ></textarea>
					</div>
				</div>

			</sec:authorize>

		</div>

		<div class="group-inputs">
			<div class="row-form">
					<div class="col-note">
						<label for="contact-pep" style="margin-right: 25px;">
							<s:message code="commons.pages.note" text="default text" />
						</label>
						<textarea id="note"></textarea>
					</div>
				</div>
		</div>
		
<jsp:include page="../../scripts/pages/address/address_init.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/contact/contact_create_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/contact/contact_create_init.js.jsp" flush="true" />
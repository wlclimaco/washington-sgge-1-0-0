<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>

<div class="group-inputs" id="email-template" style="position:absolute;top:0px z-index:142">
	<div class="container">
		<div class="row-form">
			<input type="hidden" name="emailId" class="email-id">
			<input type="hidden" name="modelAction" class="model-action">
			<input type="hidden" name="emailVersion" class="email-version">

			<div class="col-form">
				<label for="email-type" style="margin-right: 10px;">
					<s:message code="pages.contacts.form.label.emailtype" text="default text" />
				</label>
				<select name="emailType" style="display: none;" class="email-type" maxlength="254">
					<option></option>
				</select>
			</div>

			<div class="col-form">
				<label for="emailAddress">
					<s:message code="pages.contacts.form.label.emailaddress" text="default text" /></label>
				<input type="text" class="email-address email" name="emailAddress">
			</div>

			<div class="col-form">
				<div class="primary-checkbox">
					<input type="radio" name="primaryEmail" checked>
					<s:message code="pages.form.label.primary" text="default text" />
				</div>
			</div>

			<div class="close-button-form hide">
				<span class="icon-small-button icon-nav icon-remove" title='<s:message code="commons.pages.delete" text="default text" />'></span>
			</div>
		</div>
	</div>

	<a href="javascript:;" id="add-email" title='<s:message code="pages.form.label.addemail" text="default text" />'>
		<span class="icon-small-button icon-nav icon-plus"></span>
		<s:message code="pages.form.label.addemail" text="default text" />
	</a>

</div>


<jsp:include page="../../scripts/pages/email/email_init.js.jsp" flush="true" />


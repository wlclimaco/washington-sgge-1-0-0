<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>

<div class="group-inputs" id="phone-template">
	<div class="container">
		<div class="row-form">
			<input type="hidden" name="phoneId" class="phone-id">
			<input type="hidden" name="modelAction" class="model-action">
			<input type="hidden" name="phoneVersion" class="phone-version">
			<div class="col-form">
				<label for="phoneType"><s:message code="pages.form.label.phonetype" text="default text" /></label>
				<select name="phoneType" class="phone-type required phone">

				</select>
			</div>
			<div class="col-form">
				<label for="phoneCountry"><s:message code="pages.form.label.countryCode" text="default text" /></label>
				<select name="phoneCountry" class="phone-country required">

				</select>
			</div>

			<div class="col-form">
				<input type="text" name="phoneNumber" class="phone-number required phone" placeholder="*" number="true" minlength="7" maxlength="15">
			</div>

			<div class="col-form">
				<label for="phoneExtension"><s:message code="pages.form.label.phoneextension" text="default text" /></label>
				<input type="text" class="phone-extension" name="phoneExtension" minlength="1" maxlength="10">
			</div>

			<div class="col-form">
				<div class="primary-checkbox">
					<input type="radio" name="primary" checked>
					<s:message code="pages.form.label.primary" text="default text" />
				</div>
			</div>

			<div class="close-button-form hide">
				<span class="icon-small-button icon-nav icon-remove" title='<s:message code="commons.pages.delete" text="default text" />'></span>
			</div>
		</div>
	</div>

	<a href="javascript:;" id="add-phone" title='<s:message code="pages.form.label.addphone" text="default text" />'>
		<span class="icon-small-button icon-nav icon-plus"></span>
		<s:message code="pages.form.label.addphone" text="default text" />
	</a>
</div>

<jsp:include page="../../scripts/pages/phone/phone_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/phone/phone_init.js.jsp" flush="true" />


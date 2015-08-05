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

			<div class="email" style="position:absolute;left:0px;top:0px;width:500px;height:27px;z-index:40;">
				<label for="email-type" style="margin-right: 10px;">
					<s:message code="pages.contacts.form.label.emailtype" text="default text" />
				</label>
				<select name="emailType" class="email-type" maxlength="254" style="position:absolute;width:94px;height:27px;line-height:27px;z-index:4;" name="cnae" value="">
					<option value="1">Comercial</option>
					<option value="2">Compras</option>
					<option value="3">SAC</option>
					<option value="4">NFE</option>
				</select>
				<div style="position:absolute;left:300px;top:0px;width:500px;height:27px;z-index:40;">
					<label for="emailAddress">
						<s:message code="pages.contacts.form.label.emailaddress" text="default text" /></label>
					<input type="text" class="email-address email" name="emailAddress" style="position:absolute;width:300px;height:27px;line-height:27px;z-index:4;" value="">
					<div class="close-button-form hide">
						<span class="icon-small-button icon-nav icon-remove" title='<s:message code="commons.pages.delete" text="default text" />'></span>
					</div>
				</div>
			</div>
		</div>

	<a href="javascript:;" id="add-email" title='<s:message code="pages.form.label.addemail" text="default text" />' style="position:absolute;left:195px;top:30px;width:500px;height:27px;z-index:40;">
		<span class="icon-small-button icon-nav icon-plus"></span>
		<s:message code="pages.form.label.addemail" text="default text" />
	</a>

</div>
</div>

<jsp:include page="../../scripts/pages/email/email_init.js.jsp" flush="true" />


<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<div class="" id="create-document">
	<form id="create-document-form" name="createDocumentForm" method="post" action="#" >
    	<input type="hidden" name="contactId" id="contact-id" tabindex="0" value="" />
		<div class="row-form">
				<div class="col-form">
					<label for="document-type"><s:message code="pages.document.form.label.documentType" text="default text" /></label>
					<select name="documentType" id="document-type" style="display: none;" class="required" placeholder="*">
						<option></option>
					</select>
				</div>

				<div class="col-form">
					<label for="description"><s:message code="pages.document.form.label.description" text="First Name" /></label>
					<input type="text" id="description" name="description" minlength="5" maxlength="255"  style="width: 277px;">
				</div>
		</div>

		<div class="row-form">
				<div class="col-form">
					<label for="filing-status"><s:message code="pages.document.form.label.filingStatus" text="default text" /></label>
					<select name="filingStatus" id="filing-status" style="display: none;" class="required">
						<option></option>
					</select>
				</div>
				<div class="col-form action-required">
					<input type="checkbox" name="vehicle" id="isActionRequired">
					<span><s:message code="pages.document.form.label.actionRequired" text="default text" /></span>
				</div>
		</div>

		<div class="row-form">
				<div class="col-note">
					<label for="contact-pep"><s:message code="pages.document.form.label.note" text="PEP" /></label>
					<textarea id="note" class="note" name="note" minlength="5" maxlength="255"></textarea>
				</div>
		</div>
	</form>
</div>

<jsp:include page="../../scripts/pages/document/document_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/document/document_init.js.jsp" flush="true" />
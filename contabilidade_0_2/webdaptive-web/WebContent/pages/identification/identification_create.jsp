<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<div>
	<form  id="identification-create">
		<input type="hidden" id="identification-parentkeyvalue">
		<input type="hidden" id="identification-parentkey">
		<hr class="top">
		<div class="row-form">
			<label class="first id"><s:message code="pages.form.label.issuingcountry" text="default text" /></label>
			<select id="identification-country" name="identification-country" class="width-medium id required" style="display: none;">
				<option></option>
			</select>
		</div>
		<div class="row-form">
			<label class="id first"><s:message code="pages.form.label.issuingstate" text="default text" /></label>
			<select id="identification-region" name="identification-region" class="width-medium id" style="display: none;">
				<option></option>
			</select>
		</div>
		<div class="row-form">
			<label class="first id"><s:message code="pages.form.label.idtype" text="default text" /></label>
			<select name="document-type" id="document-type" class="required" style="display: none;">
				<option></option>
			</select>
		</div>
		<div class="row-form">
			<label class="first id"><s:message code="pages.form.label.idnumber" text="default text" /></label>
			<input name="identification-number" type="text" id="identification-number" class="required" placeholder="*" minlength="1" maxlength="80" style="width: 200px;">
		</div>
		<div class="row-form">
			<label class="first"><s:message code="pages.form.label.expirationdate" text="default text" /></label>
			<input name="identification-date" type="text" id="identification-date" class="datepicker required" placeholder="*" style="width: 200px;">
		</div>
		<hr>
	</form>
</div>

<jsp:include page="../../scripts/pages/identification/identification_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/identification/identification_init.js.jsp" flush="true" />
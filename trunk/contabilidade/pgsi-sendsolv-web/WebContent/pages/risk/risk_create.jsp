<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<div class="small-dialog-container" id="create-risk">
	<form name="createContactForm" method="post" action="#" id ="risk-form">
    	<input type="hidden" id="risk-dialog-parentkey" name="risk-dialog-parentkey" value="" />
    	<input type="hidden" id="risk-dialog-parentkeytype" name="risk-dialog-parentkeytype" value="" />
		<input type="hidden" id="risk-dialog-level" name="risk-dialog-level-value" value="" />

		<div class="row-form">
			<div class="col-form">
				<label for="risk-dialog-level-value">
					<s:message code="commons.pages.risk" text="default text" />
				</label>
				<select id="risk-dialog-level-value" class="required" name="riskType"  style="display: none;" tabindex="1">
					<option></option>
				</select>
			</div>
		</div>

		<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
			<div class="row-form pep hide">
				<div class="col-form">
					<label for="pep-dialog-level-value">
						<s:message code="pages.contacts.form.label.pep" text="default text" />
					</label>
					<select id="pep-dialog-level-value" class="required" name="pepType"  style="display: none;" tabindex="1">
						<option></option>
					</select>
				</div>
			</div>
		</sec:authorize>

		<div class="row-form">
			<div class="col-note">
				<label for="contact-pep">
					<s:message code="pages.document.form.label.note" text="default text" />
				</label>
				<textarea id="risk-dialog-note" class="risk-note"></textarea>
			</div>
		</div>
	</form>
</div>
<jsp:include page="../../scripts/pages/risk/risk_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/risk/risk_init.js.jsp" flush="true" />
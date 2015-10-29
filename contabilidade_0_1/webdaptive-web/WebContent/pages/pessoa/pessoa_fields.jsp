<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>

<div class="group-inputs location hide border-top">
	<div class="row-form">
		<div class="col-form" style="margin-right: 0;">
			<label for="organizationkey">
				<s:message code="pages.form.label.organizationkey" text="default text" />
			</label>
			<input type="text" id="organization-key" name="organizationkey" class="required" placeholder="*">
			<input type="text" id="parentKey" name="parentKey" class="hide" placeholder="*">
			<input type="text" id="status" name="status" class="hide">
		</div>
	</div>
</div>

<div class="group-inputs border-top" style="margin-top: -1px;">
	<div class="row-form">
		<div class="col-form">
			<label for="name">
				<s:message code="pages.form.label.name" text="default text" />
			</label>
			<input type="text" id="name" name="name" class="required" placeholder="*" minlength="1" maxlength="40">
		</div>

		<div class="col-form organization hide" style="margin-right: 0;">
			<label for="dbaname"><s:message code="pages.form.label.dbaName" text="default text" /></label>
			<input type="text" id="dbaname" name="dbaName" minlength="1" maxlength="255">
		</div>

	</div>

	<div class="row-form">
		<div class="col-form">
			<label for="ein"><s:message code="pages.form.label.ein" text="default text" /></label>
			<input type="text" id="ein" name="ein" minlength="9" maxlength="10">
		</div>

		<div class="col-form">
			<label for="sic"><s:message code="pages.form.label.sic" text="default tex" /></label>
			<input type="text" name="sic" id="sic" class="required" placeholder="*" minlength="4" maxlength="4" data-inputmask-regex="[0-9]{4}">
		</div>

		<div class="col-form">
			<label for="naics"><s:message code="pages.form.label.naics" text="default text" /></label>
			<input type="text" name="organizationNaics" id="naics" class="required" placeholder="*" minlength="2" maxlength="10" data-inputmask-regex="[0-9]{2,6}">
		</div>


	</div>
</div>

<div class="group-inputs">
	<div class="row-form">
		<div class="col-form">
			<label for="numberMembers">
				<s:message code="pages.form.label.numberofmembers" text="default text" />
			</label>
			<select	name="numberMembers" id="number-members" style="display: none;" class="required">
				<option></option>
			</select>
		</div>

		<div class="col-form">
			<label for="numberMigrantMembers">
				<s:message code="pages.form.label.numberofmigrantmembers" text="default text" />
			</label>
			<select name="numberMigrantMembers" id="number-migrant-members" style="display: none;">
				<option></option>
			</select>
		</div>

		<div class="col-form organization hide">
			<label for="totalLocations">
				<s:message code="pages.form.label.totalLocations" text="default text" /></label>
				<input type="text" name="totalLocations" id="total-locations" data-inputmask-regex="[0-9]" class="total-locations required" placeholder="*">
		</div>

		<div class="col-form organization hide">
			<label for="organizationPayrollcentralized">
			<s:message code="pages.form.label.payrollcentralized" text="default text" /></label>
			<select name="organizationPayrollcentralized" id="payroll-centralized">
			</select>
		</div>

	</div>
</div>

<jsp:include page="../phone/phone_main.jsp" flush="true" />
<jsp:include page="../address/address_main.jsp" flush="true" />

<jsp:include page="../../scripts/pages/business/business_view_main.js.jsp" flush="true" />
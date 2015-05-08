<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>

<input type="hidden" id="address-id" name="addressId" class="">

<div class="group-inputs" id="address-template">
	<div class="col first">
		<div class="row-form">
			<div class="col-form">
				<label for="streetAddressLine1">
					<s:message code="pages.form.label.streetaddress" text="default text" />
				</label>
				<input type="text" id="street-address-line-1" name="streetAddressLine1" class="required" placeholder="*" minlength="1" maxlength="80">
			</div>
		</div>

		<div class="row-form">
			<div class="col-form">
				<label for="streetAddressLine2">&nbsp</label>
				<input type="text" id="street-address-line-2" name="streetAddressLine2" minlength="1" maxlength="80">
			</div>
		</div>

		<div class="row-form">
			<div class="col-form">
				<label for="streetAddressLine3">&nbsp</label>
				<input type="text" id="street-address-line-3" name="streetAddressLine3" minlength="1" maxlength="80">
			</div>
		</div>

		<div class="row-form">
			<div class="col-form">
				<label for="streetAddressLine4">&nbsp</label>
				<input type="text" id="street-address-line-4" name="streetAddressLine4" minlength="1" maxlength="80">
			</div>
		</div>
	</div>

	<div class="col second">
		<div class="row-form">
			<div class="col-form">
				<label for="country">
					<s:message code="pages.form.label.country" text="default text" />
				</label>
				<select class="required" name="country" id="country">
					<option></option>
				</select>
			</div>
		</div>

		<div class="row-form">
			<div class="col-form">
				<label for="city">
					<s:message code="pages.form.label.city" text="default text" />
				</label>
				<input type="text" id="city" name="city" class="required" placeholder="*" minlength="1" maxlength="40">
			</div>
		</div>

		<div class="row-form">
			<div class="col-form hide" id="state-column">
				<label for="state">
					<s:message code="pages.form.label.state" text="default text" />
				</label>
				<select id="state" name="state"	class="required">
				</select>
			</div>
		</div>

		<div class="row-form">
			<div class="col-form">
				<label for="zipPostalCode">
					<s:message code="pages.form.label.zippostalcode" text="default text" />
				</label>
				<input type="text" id="zip-postal-code" class="required" placeholder="*" data-inputmask-regex="[a-zA-Z0-9 -]*" name="zipPostalCode" minlength="2" maxlength="12">
			</div>
		</div>

	</div>
</div>

<jsp:include page="../../scripts/pages/address/address_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/address/address_init.js.jsp" flush="true" />

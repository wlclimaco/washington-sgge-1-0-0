<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

	<form name="createEmploymentForm" method="post" action="#" id="createEmploymentForm">
		<input type="hidden" id="locationId" name="locationId">
		<input type="hidden" id="id" name="id">

		<div class="group-inputs" style="padding-top: 0;">
			<hr style="margin-top: 0;">
			<div class="row-form">
				<div class="col-form">
					<label class="first"><s:message code='commons.pages.locations' text='default text' /></label>
					<input id="locationName" class="required width-longest" placeholder="*" type="text" value="" >
				</div>
			</div>
		</div>

		<div id="employment-section">

			<div class="group-inputs">
				<div class="row-form">
					<div class="col-form">
						<label class="first" for="employeeId">
							<s:message code='pages.member.view.employeeid' text='default text' />
						</label>
						<input id="employeeId" class="width-short" type="text" value="" name="employeeId">
					</div>
				</div>

				<hr>
					<div class="row-form">
						<div class="col-form">
							<label class="first"><s:message code='pages.member.view.employment.enrollment.type' text='default text' /></label>
							<select id="enrolltype" class="width-medium">
								<option></option>
							</select>
						</div>
					</div>
				<hr>

				<div class="row-form">
					<div class="col-form">
						<label class="first" for="jobTitle">
						<s:message code='pages.member.form.label.jobTitle' text='default text' />
						</label>
						<input id="jobTitle" class=" width-long" type="text" value="" name="jobTitle">
					</div>
				</div>

				<div class="row-form">
					<div class="col-form">
						<label class="first" for="hireDate">
							<s:message code='pages.member.form.label.hireDate' text='default text' />
						</label>
						<input id="hireDate" name="hireDate" type="text" class="width-short date" value="">
					</div>
				</div>

				<div class="row-form">
					<div class="col-form">
						<label class="first" for="payPerPeriod">
							<s:message code='pages.member.view.payatenrollment' text='default text' />
						</label>

						<input type="text" id="payPerPeriod" name="payPerPeriod" class="width-short"
						data-inputmask="'alias': 'numeric', 'groupSeparator': ',', 'autoGroup': true, 'digits': 2, 'digitsOptional': false, 'prefix': 'USD ', 'placeholder': '0'">
					</div>
				</div>
			</div>

		</div>
	</form>
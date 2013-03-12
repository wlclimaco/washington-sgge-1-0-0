<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">

<%-- Schedule Event Template --%>
<div id="event{iCount}" class="event-container">
<%-- Remove Button and Header --%>
<h3 class="remove"><s:message code="schedulecreate.form.event.legend2" />{iCount}</h3>

<%-- Form Fields --%>
<ul class="fields">
	<%-- On/Off Toggle --%>
	<li class="highlight">
		<label><s:message code="schedulecreate.form.event.what.label" /><span class="required">*</span></label>
		<div class=" dimmer" >
			<fieldset>
				<div id="radio_light{iCount}">
					<div class="control">
						<input type="radio" id="radio1{iCount}" name="radio{iCount}" class="validate[required] dimmer-options" /><label for="radio1{iCount}"><s:message code="schedulecreate.page.off" /></label>
						<input type="radio" id="radio2{iCount}" name="radio{iCount}" class="validate[required] dimmer-options" /><label for="radio2{iCount}" class="button-action-expand option-value"><s:message code="schedulecreate.page.dim" /><span class="label-desc"></span>
							<div id="dim_options{iCount}" class="control_schedule ui-dialog button-options hide">
								<ul>
									<li><a href="" class="ui-button ui-widget ui-state-default ui-button-text-only">25%</a></li>
									<li><a href="" class="ui-button ui-widget ui-state-default ui-button-text-only">50%</a></li>
									<li><a href="" class="ui-button ui-widget ui-state-default ui-button-text-only">75%</a></li>
									<li><a href="" class="ui-button ui-widget ui-state-default ui-button-text-only">90%</a></li>
								</ul>
							</div>
						</label>
						<input type="radio" id="radio3{iCount}" name="radio{iCount}" class="validate[required] dimmer-options"/><label for="radio3{iCount}"><s:message code="schedulecreate.page.blink" /></label>
						<input type="radio" id="radio4{iCount}" name="radio{iCount}" /><label for="radio4{iCount}"><s:message code="schedulecreate.page.on" /></label>
					</div>
				</div>
	        </fieldset>
	    </div>
	</li>
	<%-- Time of Day Field --%>
	<li><label><s:message
		code="schedulecreate.form.event.when.label" /><span class="required">*</span></label>
	<div class="radio time">
		<input type="text" id="time{iCount}" class="validate[required,custom[timeFormat]]" name="time{iCount}" value="" />
		<span class="note"><s:message code="schedulecreate.form.event.when.descr" /></span>
	</div>
	</li>
	<%-- Weekday Selector --%>
	<li><label><s:message
		code="schedulecreate.form.event.repeat.label" /><span class="required">*</span></label>
	<div id="event-repeat{iCount}" class="event-day-custom checkbox">
		<input type="checkbox" class="validate[minCheckbox[1]]" id="day{iCount}Mon" name="day{iCount}" value="MONDAY">
		<label for="day{iCount}Mon"><s:message code="schedulecreate.form.event.mon.label" /></label>
		<input type="checkbox" id="day{iCount}Tue" name="day{iCount}" value="TUESDAY">
		<label for="day{iCount}Tue"><s:message code="schedulecreate.form.event.tue.label" /></label>
		<input type="checkbox" id="day{iCount}Wed" name="day{iCount}" value="WEDNESDAY">
		<label for="day{iCount}Wed"><s:message code="schedulecreate.form.event.wed.label" /></label>
		<input type="checkbox" id="day{iCount}Thu" name="day{iCount}" value="THURSDAY">
		<label for="day{iCount}Thu"><s:message code="schedulecreate.form.event.thur.label" /></label>
		<input type="checkbox" id="day{iCount}Fri" name="day{iCount}" value="FRIDAY">
		<label for="day{iCount}Fri"><s:message code="schedulecreate.form.event.fri.label" /></label>
		<input type="checkbox" id="day{iCount}Sat" name="day{iCount}" value="SATURDAY">
		<label for="day{iCount}Sat"><s:message code="schedulecreate.form.event.sat.label" /></label>
		<input type="checkbox" id="day{iCount}Sun" name="day{iCount}" value="SUNDAY">
		<label for="day{iCount}Sun"><s:message code="schedulecreate.form.event.sun.label" /></label>
	</div>
	</li>
</ul>
</div>
</sec:authorize>
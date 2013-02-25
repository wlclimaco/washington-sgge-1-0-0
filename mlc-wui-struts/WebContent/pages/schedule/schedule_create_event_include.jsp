<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
<%-- Schedule Event Template --%>
<div id="event<s:property value="count"/>" class="event-container">
<%-- Remove Button and Header --%>
<h3 class="remove"><s:text name="schedulecreate.form.event.legend2" /><s:property
	value="count" /></h3>

<%-- Form Fields --%>
<ul class="fields">
	<%-- On/Off Toggle --%>
	<li class="highlight">
		<label><s:text name="schedulecreate.form.event.what.label" /><span class="required">*</span></label> 
		<div class="lighting-controls dimmer" >	
			<fieldset>
				<div id="radio_light<s:property value="count"/>">
					<div class="control">
						<input type="radio" id="radio1<s:property value="count"/>" name="radio<s:property value="count"/>" class="validate[required] dimmer-options" /><label for="radio1<s:property value="count"/>"><s:text name="schedulecreate.page.off" /></label>
						<input type="radio" id="radio2<s:property value="count"/>" name="radio<s:property value="count"/>" class="validate[required] dimmer-options" /><label for="radio2<s:property value="count"/>" class="button-action-expand option-value"><s:text name="schedulecreate.page.dim" /><span class="label-desc"></span>
							<div id="dim_options<s:property value="count"/>" class="control_schedule ui-dialog button-options hide">
								<ul>
									<li><a href="" class="ui-button ui-widget ui-state-default ui-button-text-only">25%</a></li>
									<li><a href="" class="ui-button ui-widget ui-state-default ui-button-text-only">50%</a></li>
									<li><a href="" class="ui-button ui-widget ui-state-default ui-button-text-only">75%</a></li>
									<li><a href="" class="ui-button ui-widget ui-state-default ui-button-text-only">90%</a></li>
								</ul>
							</div>
						</label>
						<input type="radio" id="radio3<s:property value="count"/>" name="radio<s:property value="count"/>" class="validate[required] dimmer-options"/><label for="radio3<s:property value="count"/>"><s:text name="schedulecreate.page.blink" /></label>
						<input type="radio" id="radio4<s:property value="count"/>" name="radio<s:property value="count"/>" /><label for="radio4<s:property value="count"/>"><s:text name="schedulecreate.page.on" /></label>
					</div>
				</div>
	        </fieldset>
	    </div>    
	</li>
	<%-- Time of Day Field --%>
	<li><label><s:text
		name="schedulecreate.form.event.when.label" /><span class="required">*</span></label>
	<div class="radio time">
		<input type="text" id="time<s:property value='count'/>" class="validate[required,custom[timeFormat]]" name="time<s:property value='count'/>" value="" />
		<span class="note"><s:text name="schedulecreate.form.event.when.descr" /></span>	
	</div>
	</li>
	<%-- Weekday Selector --%>
	<li><label><s:text
		name="schedulecreate.form.event.repeat.label" /><span class="required">*</span></label>
	<div id="event-repeat<s:property value='count'/>" class="event-day-custom checkbox">
		<input type="checkbox" class="validate[minCheckbox[1]]" id="day<s:property value="count"/>Mon" name="day<s:property value="count"/>" value="MONDAY">
		<label for="day<s:property value="count"/>Mon"><s:text name="schedulecreate.form.event.mon.label" /></label> 
		<input type="checkbox" id="day<s:property value="count"/>Tue" name="day<s:property value="count"/>" value="TUESDAY">
		<label for="day<s:property value="count"/>Tue"><s:text name="schedulecreate.form.event.tue.label" /></label> 
		<input type="checkbox" id="day<s:property value="count"/>Wed" name="day<s:property value="count"/>" value="WEDNESDAY">
		<label for="day<s:property value="count"/>Wed"><s:text name="schedulecreate.form.event.wed.label" /></label> 
		<input type="checkbox" id="day<s:property value="count"/>Thu" name="day<s:property value="count"/>" value="THURSDAY">
		<label for="day<s:property value="count"/>Thu"><s:text name="schedulecreate.form.event.thur.label" /></label> 
		<input type="checkbox" id="day<s:property value="count"/>Fri" name="day<s:property value="count"/>" value="FRIDAY">
		<label for="day<s:property value="count"/>Fri"><s:text name="schedulecreate.form.event.fri.label" /></label> 
		<input type="checkbox" id="day<s:property value="count"/>Sat" name="day<s:property value="count"/>" value="SATURDAY">
		<label for="day<s:property value="count"/>Sat"><s:text name="schedulecreate.form.event.sat.label" /></label> 
		<input type="checkbox" id="day<s:property value="count"/>Sun" name="day<s:property value="count"/>" value="SUNDAY">
		<label for="day<s:property value="count"/>Sun"><s:text name="schedulecreate.form.event.sun.label" /></label>
	</div>
	</li>
</ul>
</div>
</sec:authorize>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<div id="sunrise-sunset" class="point-detail-container sunrise-sunset">
	<h3>
		<div class="left"><s:message code="smartpointdetail.lightschedule" /></div>
			<span class="missing_data rounded ui-state-highlight"></span>
	</h3>
	<div id="sunrise-sunset-details">
		<dl class="read-only">
			<dt><s:message code="smartpointdetail.sunrisesunset.sunset" />:</dt>
			<dd>
				<div class="left"><span class="sunset"></span><span id="sunset-time"></span></div>
					<div class="offset">
						<span class="sunsetoffset"></span>
					</div>
			</dd>
			<dt><s:message code="smartpointdetail.sunrisesunset.sunrise" />:</dt>
			<dd>
				<div class="left"><span class="sunrise"></span><span id="sunrise-time"></span></div>
					<div class="offset">
						<span class="sunriseoffset"></span>
					</div>
			</dd>
			<dt><s:message code="smartpointdetail.sunrisesunset.offsetschedule" />:</dt>
			<dd id="sunrise-sunset-offset"></dd>
			<dt><s:message code="smartpointdetail.sunrisesunset.eventschedule" />:</dt>
			<dd id="sunrise-sunset-event"></dd>
		</dl>
	</div>
	<div id="apply-offset-schedule-smartpoint" class="hide">
		<fieldset class="edit-only-context two-line">
			<ul>
				<li>
					<label for="offsetScheduleAddSelect"><s:message code="smartpointdetail.sunrisesunset.selectoffsetschedule" /> <span class="required">*</span></label>
					<br/>
					<select name="offset_schedule_add_select" list="offsetScheduleList" listKey="id" id="offset-schedule-add-select" cssClass="combobox" listValue="value" multiple="false" size="1" required="false" />
				</li>
				<li class="submit-row">
					<input type="submit" id="apply-offset-schedule" class="submit-short-form-context apply-offset-schedule-action" value="Apply Offset Schedule" /> <s:message code="smartpointdetail.sunrisesunset.or"/> <a href="" class="cancel-edit"><s:message code="smartpointdetail.sunrisesunset.cancel"/></a>
				</li>
			</ul>
		</fieldset>
	</div>
	<div id="apply-event-schedule-smartpoint" class="hide">
		<fieldset class="edit-only-context two-line">
			<ul>
				<li>
					<label for="eventScheduleAddSelect"><s:message code="smartpointdetail.sunrisesunset.selecteventschedule" /> <span class="required">*</span></label>
					<br/>

					<select name="event_schedule_add_select" list="eventScheduleList" listKey="id" id="event-schedule-add-select" cssClass="combobox" listValue="value" multiple="false" size="1" required="false" />
				</li>
				<li class="submit-row">
					<input type="submit" id="apply-event-schedule" class="submit-short-form-context apply-event-schedule-action" value="Apply Event Schedule" /> <s:message code="smartpointdetail.sunrisesunset.or"/> <a href="" class="cancel-edit"><s:message code="smartpointdetail.sunrisesunset.cancel"/></a>
				</li>
			</ul>
		</fieldset>
	</div>
</div>

<script src="scripts/pages/light/modules/module_light_schedule.js"></script>

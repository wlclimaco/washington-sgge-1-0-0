<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
<%-- START Main Page Content --%>
<div class="content-inner">
<%-- START Main Content Container --%>
<div id="yui-main" class=""><%-- Header and Description --%>
<div class="content-header">
	<h1>

	</h1>
	<p class="description"><s:message code="schedulecreate.page.intro.offset" /></p>
</div>
<%-- START Form Container --%>
<div id="schedule-list">
	<%-- Top Actions and Buttons --%>
	<div class="create-action-container">
		<a id="ajax-button" class="alist" href="schedule"><s:message code="schedulecreate.actions.listschedules" /></a>
	</div>

	<div class="yui-gc">
		<div class="yui-u first">
			<%-- START Form --%>

			<form id="create-schedule-form" name="create_schedule_form" method="post" action="#">
				<fieldset>
					<%-- Messaging --%>
					<div class="ui-state-error ui-corner-all" style="display: none;">
						<p><span class="ui-icon ui-icon-alert"></span></p>
					</div>
					<ul>
						<li>
							<label for="schedule_name"><s:message code="schedulecreate.form.name.label" /><span class="required">*</span></label>
							<input type="hidden" name="scheduleId" id="schedule-id" tabindex="1">
							<input type="text" id="schedule-name" class="validate[required,maxSize[100]]" name="schedule_name" tabindex="1" />
						</li>
						<li>
							<label for="schedule_description"><s:message code="schedulecreate.form.description.label" /></label>
							<textarea class="validate[maxSize[150]]" id="schedule-description" tabindex="2"></textarea>
						</li>
					</ul>
				</fieldset>

			<%-- START Additional Form Fields --%>
			<fieldset id="offset" class="sunrise-sunset"><legend><s:message code="schedulecreate.form.sunrise.legend" /></legend>
				<p class="description"><s:message code="schedulecreate.page.offset" /></p>
				<ul class="fields">
					<li class="underline">
						<label>
							<span class="sunset"></span><s:message code="schedulecreate.page.turnlights" /> <strong><s:message code="schedulecreate.page.on" /></strong>
						</label>
						<input type="text" id="offsetSunsetTime" class="date-short validate[required, custom[multiple5],min[0],max[635]]" name="offsetSunsetTime"/> <s:message code="schedulecreate.page.min" />
		        		<span id="switch-sunset" class="switch radio">
								<input checked type="radio" id="offsetSunsetBefore" name="offsetSunset" value="true"/><label for="offsetSunsetBefore"><s:message code="schedulecreate.page.before" /></label>

		            			<input type="radio" id="offsetSunsetAfter" name="offsetSunset" value="false"/><label for="offsetSunsetAfter"><s:message code="schedulecreate.page.after" /></label>
		        		</span>
		        		<s:message code="schedulecreate.page.sunset" /> <s:message code="schedulecreate.page.setto" />
		        		<select id="sunset-intensity" class="short">
		        				<option value="50"><s:message code="schedulecreate.page.fiftyhundred" /></option>
		        				<option value="100"><s:message code="schedulecreate.page.onehundred" /></option>
		        		</select> <s:message code="schedulecreate.page.intensity" />
		    		</li>

					<li class="">
						<label><span class="sunrise"> </span><s:message code="schedulecreate.page.turnlights" /> <strong><s:message code="schedulecreate.page.off" /></strong></label>
							<input type="text" id="offsetSunriseTime" class="date-short validate[required, min[0],max[635]]" name="offsetSunriseTime"/> <s:message code="schedulecreate.page.min" />
		        		<span id="switch-sunrise" class="switch radio">
		            			<input type="radio" id="offsetSunriseBefore" name="offsetSunrise" value="true"/><label for="offsetSunriseBefore"><s:message code="schedulecreate.page.before" /></label>
		            			<input checked type="radio" id="offsetSunriseAfter" name="offsetSunrise" value="false"/><label for="offsetSunriseAfter"><s:message code="schedulecreate.page.after" /></label>
		        		</span>
		        		<s:message code="schedulecreate.page.sunrise" />
		    		</li>
				</ul>
			</fieldset>
			<fieldset id="override">
				<ul>
					<li class="submit">
							<label>&nbsp;</label><input type="submit" id="create-schedule" class="button" /> &nbsp;<s:message code="schedulecreate.actions.or" />&nbsp;
						<a href="schedule" class="cancel alist" id="ajax-button"><s:message code="schedulecreate.actions.cancel" /></a>
					</li>
				</ul>
				<div id="event1"></div>
			</fieldset>
			<input type="hidden" name="scheduleType" id="schedule-type" value="1">
			<input type="hidden" name="smartpointCount" id="smartpoint-count">
	<%-- END Additional Form Fields --%></form>
	<%-- END Form --%></div>
	</div>

</div>
<%-- END Form Container --%></div>

<%@ include file="../../scripts/pages/schedule_create_offset_main.js.jsp" %>
<%@ include file="../../scripts/pages/schedule_create_offset_init.js.jsp" %>

</sec:authorize>


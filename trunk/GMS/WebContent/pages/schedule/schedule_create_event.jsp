<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
<%-- START Main Page Content --%>
<div class="content-inner"><%-- Messaging --%>

	<%-- START Main Content Container --%>
	<div id="yui-main" class="">
		<%-- Header and Description --%>
		<div class="content-header">
			<h1>

			</h1>
			<p class="description"><s:message code="schedulecreate.page.intro.event" /></p>
		</div>

		<%-- START Form Container --%>
		<div id="schedule-list">
		<%-- Top Actions and Buttons --%>
			<div class="create-action-container">
				<a id="ajax-button" class="alist" href="schedule"><s:message code="schedulecreate.actions.listschedules" /></a>
			</div>

			<div class="yui-gc">
				<div class="yui-u first"><%-- START Form --%>
					<%-- START Form --%>
							<form id="create-schedule-event-form" name="create_schedule_form" method="post" action="#">
							<%--<form id="update-schedule-form" name="create_schedule_form" method="post" action="#">--%>

						<fieldset>
							<%-- Messaging --%>
								<div class="ui-state-error ui-corner-all" style="display: none;">
									<p><span class="ui-icon ui-icon-alert"></span></p>
								</div>

								<ul>
									<li>
										<label for="schedule_name"><s:message code="schedulecreate.form.name.label" /><span class="required">*</span></label>
										<input type="hidden" name="scheduleId" id="schedule-id" tabindex="1" value="${id}">
										<input type="text" id="schedule-name" class="validate[required, maxSize[100]]" name="schedule_name" tabindex="1" value="${name}" />
									</li>
									<li>
										<label for="schedule_description"><s:message code="schedulecreate.form.description.label" /></label>
										<textarea id="schedule-description" class="validate[maxSize[150]]" tabindex="2">${description}</textarea>
									</li>
								</ul>
						</fieldset>

						<div id="event-schedule"eventSchedule">
							<fieldset id="override"><legend><s:message code="schedulecreate.form.event.legend1" /> </legend>
								<%-- Placeholder for event includes --%>
								<div id="event-add"></div>
								<%--<s:iterator status="eventsList" value="%{eventsList}">
									<div class="list-events hide events-number">
										<div class="event-time"><s:property value="%{top.date}" /></div>
										<s:iterator status="events-days" value="%{days}">
											<div class="event-day"><s:property value="%{top}" /></div>
										</s:iterator>
										<div class="event-status"><s:property value="%{top.state}" /></div>
									</div>
								</s:iterator>--%>
								<ul>
									<li class="underline"><label>&nbsp;</label>
										<a href="" id="event-add-link" class="plus"><s:message code="schedulecreate.actions.addevent" /></a>
									</li>
									<li class="submit"><label>&nbsp;</label>
											<input type="submit" id="create-schedule-event" class="button" value="" />&nbsp;<s:message code="schedulecreate.actions.or" />&nbsp;

										<a href="schedule" id="ajax-button" class="cancel alist"><s:message code="schedulecreate.actions.cancel" /></a>
									</li>
								</ul>
							</fieldset>
						</div>

						<input type="hidden" name="scheduleType" id="schedule-type" value="2">
						<input type="hidden" name="smartpointCount" id="smartpoint-count"  value="${smartpointCount}">

					<%-- END Additional Form Fields --%>
					</form>
				<%-- END Form --%>
				</div>
			</div>
		</div>
<%-- END Form Container --%></div>
<%-- END Main Content Container --%></div>
<%-- END Main Page Content --%>

<%@ include file="../../scripts/pages/schedule_create_event_main.js.jsp" %>
<%@ include file="../../scripts/pages/schedule_create_event_init.js.jsp" %>

</sec:authorize>
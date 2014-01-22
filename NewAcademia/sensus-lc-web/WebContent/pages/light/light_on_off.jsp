<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<%-- Switch SmartPoint On/Off Dialog --%>
<div id="action-dialog-panel" class="action-dialog">

	<form id="switch-lights-form" name="switch_lights_form" method="post">

	<%-- Description --%>
	<h2 class="description">
		<s:message code="action.switchlight.label.smartpoints1"></s:message>&nbsp;
		<span class="smartpoint-count"></span>&nbsp;
		<s:message code="action.switchlight.label.smartpoints2"></s:message>
	</h2>

	<%-- Messaging --%>
	<div id="action-messaging" class="messaging">

		<span class="message"></span></div>

		<!--START: Dimmer -->
		<div class="lighting-controls dimmer">

			<fieldset class="two-line">
				<label class="title"><s:message code="action.switchlight.subtitle" /></label>
				<div class="control">
					<select name="dimmer" id="dimmer"></select>
				</div>
			 	<p class="note lights-dim"><s:message code="smartpoint.actions.note"></s:message></p>
			</fieldset>

			<!-- Wainting for another sprint -->
			<!--
				<fieldset>
					<legend><s:message code="action.switchlight.legend.manegeOverride" /></legend>
					<ul class="radio">
						<li class="radio">
							<input type="radio" id="cpExpireManual" name="cpExpire" value="Manual" checked />
							<label for="cpExpireManual"><s:message code="action.switchlight.manege.expire" /></label>
						</li>
						<li class="radio">
							<input type="radio" id="cpExpireNextEvent" name="cpExpire" value="Manual" />
							<label for="cpExpireManual"><s:message code="action.switchlight.manege.expireOnNextScheduled" /></label>
						</li>
						<li class="radio">
							<input type="radio" id="cpExpireSchedule" name="cpExpire" value="Manual" />
							<label for="cpExpireManual"><s:message code="action.switchlight.manege.expireIn" /></label>
							<input type="text" class="date-short" />
							<small><s:message code="action.switchlight.hours" /></small>
						</li>
					</ul>
				</fieldset>
			-->

		</div>
		<!--END: Dimmer -->

	</form>
</div>
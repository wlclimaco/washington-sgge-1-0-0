<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%-- Switch SmartPoint On/Off Dialog --%>
<div id="action-dialog-panel" class="action-dialog">

	<form id="switch-lights-form" name="switch_lights_form" method="post">

	<%-- Description --%>
	<h2 class="description">
		<s:text name="action.switchlight.label.smartpoints1"></s:text>&nbsp;
		<span class="smartpoint-count"></span>&nbsp; 
		<s:text name="action.switchlight.label.smartpoints2"></s:text>
	</h2>

	<%-- Messaging --%>
	<div id="action-messaging" class="messaging">

		<span class="message"></span></div>

		<!--START: Dimmer -->
		<div class="lighting-controls dimmer">
		
			<fieldset class="two-line">
				<label class="title"><s:text name="action.switchlight.subtitle" /></label>
				<div class="control">
					<select name="dimmer" id="dimmer"></select>
				</div>
			 	<p class="note lights-dim"><s:text name="smartpoint.actions.note"></s:text></p>
			</fieldset>
			
			<!-- Wainting for another sprint -->
			<!-- 
				<fieldset>
					<legend><s:text name="action.switchlight.legend.manegeOverride" /></legend>
					<ul class="radio">
						<li class="radio">
							<input type="radio" id="cpExpireManual" name="cpExpire" value="Manual" checked />
							<label for="cpExpireManual"><s:text name="action.switchlight.manege.expire" /></label>
						</li>
						<li class="radio">
							<input type="radio" id="cpExpireNextEvent" name="cpExpire" value="Manual" />
							<label for="cpExpireManual"><s:text name="action.switchlight.manege.expireOnNextScheduled" /></label>
						</li>
						<li class="radio">
							<input type="radio" id="cpExpireSchedule" name="cpExpire" value="Manual" />
							<label for="cpExpireManual"><s:text name="action.switchlight.manege.expireIn" /></label> 
							<input type="text" class="date-short" /> 
							<small><s:text name="action.switchlight.hours" /></small>
						</li>
					</ul>
				</fieldset>   
			-->

		</div>
		<!--END: Dimmer -->

	</form>
</div>
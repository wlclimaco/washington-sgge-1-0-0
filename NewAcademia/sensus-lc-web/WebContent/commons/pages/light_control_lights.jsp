<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<%-- Switch SmartPoint On/Off Dialog --%>
<div id="action-dialog-panel" class="action-dialog">
	<form id="turnOnOffLightsForm" name="turnOnOffLightsForm" method="post" >
	<h2>
		<s:message code="smartpoint.actions.control.head" />
	</h2>
    <!-- Messaging -->
    <div id="turnOnOffLightsFormMessaging" class="messaging"><span class="message"></span></div>
		<fieldset class="two-line">
		     <div class="lighting-controls dimmer clearfix">
			<fieldset>
			    <label class="title"><s:message code="smartpoint.actions.control" /></label>
			        <div class="control">
			            <div id="radio_light">
			                <input type="radio" id="radio1" class="dim" value="0" name="radio" checked="checked" /><label for="radio1"><s:message code="smartpoint.actions.controlLights.off" /></label>
			                <input type="radio" id="radio2" name="radio" /><label for="radio2" class="button-action-expand option-value"><s:message code="smartpoint.actions.controlLights.dim" /><span class="label-desc"></span>
			                    <div id="dim_options" class="ui-dialog button-options hide">
			                        <ul>
	                                    <li><a href="" class="ui-button ui-widget ui-state-default ui-button-text-only">25%</a><input type="radio" class="hide dim" value="25"></li>
	                                    <li><a href="" class="ui-button ui-widget ui-state-default ui-button-text-only">50%</a><input type="radio" class="hide dim" value="50"></li>
	                                    <li><a href="" class="ui-button ui-widget ui-state-default ui-button-text-only">75%</a><input type="radio" class="hide dim" value="75"></li>
	                                    <li><a href="" class="ui-button ui-widget ui-state-default ui-button-text-only">90%</a><input type="radio" class="hide dim" value="90"></li>
									</ul>
			                    </div>
			                </label>
			                <input type="radio" id="radio3" name="radio" /><label for="radio3" class="button-action-expand option-value"><s:message code="smartpoint.actions.controlLights.blink" /><span class="label-desc"></span>
			                    <div id="blink_options" class="ui-dialog button-options hide">
			                        <ul>
			                            <li><a href="" class="ui-button ui-widget ui-state-default ui-button-text-only"><s:message code="smartpoint.actions.controlLights.slow" /></a><input type="radio" class="hide blink" value="SLOW"></li>
			                            <li><a href="" class="ui-button ui-widget ui-state-default ui-button-text-only"><s:message code="smartpoint.actions.controlLights.fast" /></a><input type="radio" class="hide blink" value="FAST"></li>
			                        </ul>
			                    </div>
			                </label>
			                <input type="radio" id="radio4" class="dim" value="100" name="radio" /><label for="radio4"><s:message code="smartpoint.actions.controlLights.on" /></label>
			                 </div>
			             </div>
			  </fieldset>

		      </div>
		</fieldset>
		 <p class="note lights-dim"><s:message code="smartpoint.actions.note" /></p>
   		<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
			<fieldset >
				<legend><s:message code="action.switchlight.legend.manegeOverride" /></legend>
				<ul class="radio">
					<li class="radio">
						<input checked type="radio" id="cpExpireNextEvent" name="cpExpire" value="SCHEDULED" />
						<label for="cpExpireManual"><s:message code="action.switchlight.manege.expireOnNextScheduled" /></label>
					</li>
					<li class="radio">
						<input type="radio" id="cpExpireManual" name="cpExpire" value="PERMANENT" />
						<label for="cpExpireManual"><s:message code="action.switchlight.manege.expire" /></label>
					</li>
					<li class="radio">
						<input type="radio" id="cpExpireSchedule" name="cpExpire" value="PER_DATE" />
						<label for="cpExpireManual"><s:message code="action.switchlight.manege.expireIn" /></label>
						<input id="override-date" type="text" class="date-short" />
						<label for="cpExpireManual"><s:message code="smartpoint.actions.controlLights.at" /></label>
						<input id="override-time" type="text" class="date-short validate[required, custom[timeFormat]]" />
					</li>
				</ul>
			</fieldset>
		</sec:authorize>
      </form>
      <div class="highlight"><small><s:message code="smartpoint.actions.controlLights.note" /></small></div>
</div>
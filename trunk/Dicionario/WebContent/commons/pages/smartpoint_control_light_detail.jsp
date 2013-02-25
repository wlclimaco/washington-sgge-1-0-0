<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<%-- Switch SmartPoint On/Off Dialog --%>
<div id="action-dialog-panel" class="action-dialog">
	<form id="turnOnOffLightsForm" name="turnOnOffLightsForm" method="post" >
    <!-- Messaging -->
    <div id="turnOnOffLightsFormMessaging" class="messaging"><span class="message"></span></div>        
        <fieldset class="two-line">
             <div class="lighting-controls dimmer">
                    <fieldset>
                        <label class="title"><s:text name="smartpoint.actions.control" /></label>
                            <div class="control">
                                <div id="radio_light">
                                    <input type="radio" id="radio1" name="radio" checked="checked" /><label for="radio1"><s:text name="smartpoint.actions.controlLights.off" /></label>
                                    <input type="radio" id="radio2" name="radio" /><label for="radio2" class="button-action-expand option-value"><s:text name="smartpoint.actions.controlLights.dim" /><span class="label-desc"></span>
                                        <div id="dim_options" class="ui-dialog button-options hide">
                                            <ul></ul>
                                        </div>
                                    </label>
                                    <input type="radio" id="radio3" name="radio" /><label for="radio3" class="button-action-expand option-value"><s:text name="smartpoint.actions.controlLights.blink" /><span class="label-desc"></span>
                                        <div id="blink_options" class="ui-dialog button-options hide">
                                            <ul>
                                                <li><a href="" class="ui-button ui-widget ui-state-default ui-button-text-only"><input type="radio" class="hide" value="SLOW"><s:text name="smartpoint.actions.controlLights.slow" /></input></a></li>
                                                <li><a href="" class="ui-button ui-widget ui-state-default ui-button-text-only"><input type="radio" class="hide" value="FAST"><s:text name="smartpoint.actions.controlLights.fast" /></input></a></li>
                                            </ul>
                                        </div>
                                    </label>
                                    <input type="radio" id="radio4" name="radio" /><label for="radio4"><s:text name="smartpoint.actions.controlLights.on" /></label>
                                </div>                      
                            </div>
                    </fieldset>                                
            </div>
   		 </fieldset>
   		<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
			<fieldset >
				<legend><s:text name="action.switchlight.legend.manegeOverride" /></legend>
				<ul class="radio">
					<li class="radio">
						<input checked type="radio" id="cpExpireNextEvent" name="cpExpire" value="SCHEDULED" />
						<label for="cpExpireManual"><s:text name="action.switchlight.manege.expireOnNextScheduled" /></label>
					</li>
					<li class="radio">
						<input type="radio" id="cpExpireManual" name="cpExpire" value="PERMANENT" />
						<label for="cpExpireManual"><s:text name="action.switchlight.manege.expire" /></label>
					</li>
					<li class="radio">
						<input type="radio" id="cpExpireSchedule" name="cpExpire" value="PER_DATE" />
						<label for="cpExpireManual"><s:text name="action.switchlight.manege.expireIn" /></label> 
						<input id="override-date" type="text" class="date-short" /> 
						<label for="cpExpireManual"><s:text name="smartpoint.actions.controlLights.at" /></label> 
						<input id="override-time" type="text" class="date-short validate[required, custom[timeFormat]]" /> 
						<small><s:text name="action.switchlight.hours" /></small>
					</li>
				</ul>
			</fieldset> 
		</sec:authorize>
			<div class="highlight"><small><s:text name="smartpoint.actions.controlLights.note" /></small></div>
      </form>
</div>
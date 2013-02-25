<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
<%-- START Main Page Content --%>
<div class="content-inner">
<%-- START Main Content Container --%>
<div id="yui-main" class=""><%-- Header and Description --%>
<div class="content-header">
	<h1>
		<s:if test="%{id==null}">
			<s:text name="schedulecreate.page.header.offset" />
		</s:if>
		<s:else>
			<s:text name="schedulecreate.page.header.updateOffset" />${name}"
		</s:else>	
	</h1>
	<p class="description"><s:text name="schedulecreate.page.intro.offset" /></p>
</div>
<%-- START Form Container --%>
<div id="schedule-list">
	<%-- Top Actions and Buttons --%>
	<div class="create-action-container">
		<a id="ajax-button" class="alist" href="schedule/ajax.list.action"><s:text name="schedulecreate.actions.listschedules" /></a> 
	</div>
	
	<div class="yui-gc">
		<div class="yui-u first">
			<%-- START Form --%>
			<s:if test="%{id==null}">
				<form id="create-schedule-form" name="create_schedule_form" method="post" action="#">
			</s:if>
			<s:else>
				<form id="update-schedule-form" name="create_schedule_form" method="post" action="#">
			</s:else>
				<fieldset>
					<%-- Messaging --%>
					<div class="ui-state-error ui-corner-all" style="display: none;">
						<p><span class="ui-icon ui-icon-alert"></span></p>
					</div>
					<ul>
						<li>
							<label for="schedule_name"><s:text name="schedulecreate.form.name.label" /><span class="required">*</span></label>
							<input type="hidden" name="scheduleId" id="schedule-id" tabindex="1">
							<input type="text" id="schedule-name" class="validate[required,maxSize[100]]" name="schedule_name" tabindex="1" />
						</li>
						<li>
							<label for="schedule_description"><s:text name="schedulecreate.form.description.label" /></label> 
							<textarea class="validate[maxSize[150]]" id="schedule-description" tabindex="2"></textarea>
						</li>
					</ul>
				</fieldset>
	
			<%-- START Additional Form Fields --%>
			<fieldset id="offset" class="sunrise-sunset"><legend><s:text name="schedulecreate.form.sunrise.legend" /></legend>
				<p class="description"><s:text name="schedulecreate.page.offset" /></p>
				<ul class="fields">
					<li class="underline">
						<label><span class="sunset"></span><s:text name="schedulecreate.page.turnlights" /> <strong><s:text name="schedulecreate.page.on" /></strong></label>
						<s:if test="%{turnLightOn==null}">
							<input type="text" id="offsetSunsetTime" class="date-short validate[required, custom[multiple5],min[0],max[635]]" name="offsetSunsetTime" value="0"/> <s:text name="schedulecreate.page.min" />                                                      
		        		</s:if>
		        		<s:else>
		        			<input type="text" id="offsetSunsetTime" class="date-short validate[required, custom[multiple5],min[0],max[635]]" name="offsetSunsetTime"/> <s:text name="schedulecreate.page.min" />
		        		</s:else>
		        		<span id="switch-sunset" class="switch radio">
		            		<s:if test="%{sunsetBefore==true}">
		            			<input checked type="radio" id="offsetSunsetBefore" name="offsetSunset" value="true" CHECKED/><label for="offsetSunsetBefore"><s:text name="schedulecreate.page.before" /></label>
		            		</s:if>	  
							<s:else>
								<input checked type="radio" id="offsetSunsetBefore" name="offsetSunset" value="true"/><label for="offsetSunsetBefore"><s:text name="schedulecreate.page.before" /></label>
							</s:else>
		            		<s:if test="%{sunsetBefore==false}">	
		            			<input type="radio" id="offsetSunsetAfter" name="offsetSunset" value="false" CHECKED/><label for="offsetSunsetAfter"><s:text name="schedulecreate.page.after" /></label>
		            		</s:if>	
		            		<s:else>
		            			<input type="radio" id="offsetSunsetAfter" name="offsetSunset" value="false"/><label for="offsetSunsetAfter"><s:text name="schedulecreate.page.after" /></label>
		            		</s:else> 
		        		</span>
		        		<s:text name="schedulecreate.page.sunset" /> <s:text name="schedulecreate.page.setto" /> 
		        		<select id="sunset-intensity" class="short">
		        			<s:if test="%{offsetIntensity == 50">
		        				<option value="50" SELECTED><s:text name="schedulecreate.page.fiftyhundred" /></option>
		        			</s:if>
		        			<s:else>
		        				<option value="50"><s:text name="schedulecreate.page.fiftyhundred" /></option>
		        			</s:else>
		        			<s:if test="%{offsetIntensity == 100 || offsetIntensity == null}">
		        				<option value="100" SELECTED><s:text name="schedulecreate.page.onehundred" /></option>
		        			</s:if>
		        			<s:else>
		        				<option value="100"><s:text name="schedulecreate.page.onehundred" /></option>
		        			</s:else>
		        			
		        		</select> <s:text name="schedulecreate.page.intensity" />
		    		</li>

					<li class="">
						<label><span class="sunrise"> </span><s:text name="schedulecreate.page.turnlights" /> <strong><s:text name="schedulecreate.page.off" /></strong></label>
						<s:if test="%{turnLightOff==null}">
							<input type="text" id="offsetSunriseTime" class="date-short validate[required, min[0],max[635]]" name="offsetSunriseTime" value="0"/> <s:text name="schedulecreate.page.min" />
						</s:if>
						<s:else>
							<input type="text" id="offsetSunriseTime" class="date-short validate[required, min[0],max[635]]" name="offsetSunriseTime"/> <s:text name="schedulecreate.page.min" />                                                     
		        		</s:else>
		        		<span id="switch-sunrise" class="switch radio">
		            		<s:if test="%{sunriseBefore==true}">
		            			<input type="radio" id="offsetSunriseBefore" name="offsetSunrise" value="true" CHECKED/><label for="offsetSunriseBefore"><s:text name="schedulecreate.page.before" /></label>
		            		</s:if>
		            		<s:else>
		            			<input type="radio" id="offsetSunriseBefore" name="offsetSunrise" value="true"/><label for="offsetSunriseBefore"><s:text name="schedulecreate.page.before" /></label>
		            		</s:else>
		            		<s:if test="%{sunriseBefore==false}">  
		            			<input checked type="radio" id="offsetSunriseAfter" name="offsetSunrise" value="false" CHECKED/><label for="offsetSunriseAfter"><s:text name="schedulecreate.page.after" /></label>
		            		</s:if>	
		            		<s:else>
		            			<input checked type="radio" id="offsetSunriseAfter" name="offsetSunrise" value="false"/><label for="offsetSunriseAfter"><s:text name="schedulecreate.page.after" /></label>
		            		</s:else>
		        		</span> 
		        		<s:text name="schedulecreate.page.sunrise" />
		    		</li>
				</ul>
			</fieldset>
			<fieldset id="override">
				<ul>
					<li class="submit">
						<s:if test="%{id==null}">
							<label>&nbsp;</label><input type="submit" id="create-schedule" class="button" value="<s:text name="schedulecreate.actions.createschedule" />" />&nbsp;<s:text name="schedulecreate.actions.or" />&nbsp;
						</s:if>
						<s:else>
							<label>&nbsp;</label><input type="submit" id="create-schedule" class="button editOffsetSchedule" value="<s:text name="schedulecreate.actions.editOffsetSchedule" />" />&nbsp;<s:text name="schedulecreate.actions.or" />&nbsp;
						</s:else>	
						<a href="schedule/ajax.list.action" class="cancel alist" id="ajax-button"><s:text name="schedulecreate.actions.cancel" /></a>
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

</sec:authorize>


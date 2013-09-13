<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<sec:authorize access="hasRole('ROLE_EPM_SERVICE_ELECTRIC') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<tr class="cells-align-top">
					<td class="relayLegend"><strong></strong></td>
					<td class="">
			<ul>
				<li>
					<label for="enrollment-code"><spring:message code="systemintelligence.page.event.hanEnrollment"/></label>
						<span></span>
					</label>
					<input type="text" id="enrollmentCode" class="validate[custom[integer],min[0],max[255]]"/>
				</li>
			</ul>
			<fieldset class="demand-response-fields">
				<legend>
					<a href="#" class="text-button spindown spanToggle">
						<span class="ui-icon-triangle-1-e ui-icon"></span>
						<span id="legend-demand-response"><spring:message code="systemintelligence.dialogDemandResponse.legendClass"/></span>
					</a>
				</legend>
				<div class="advanced-search-container yui-gf toggleClass" style="display: none">
					<div class="yui-u first">
						<h5>
							<small><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassMessage"/></small>
						</h5>
					</div>
					<div class="yui-g">
						<div class="yui-u">
							<ul>
								<li class="checkbox"><input type="checkbox" id="device_class_hvac" name="hancheck" value="HVAC_COMPRESSOR" /><label for="device_class_hvac"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassHVAC"/></label></li>
								<li class="checkbox"><input type="checkbox" id="device_class_heater"name="hancheck" value="STRIP_HEATER" /><label for="device_class_heater"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassStrip"/></label></li>
								<li class="checkbox"><input type="checkbox" id="device_class_water_heater"name="hancheck" value="WATER_HEATER" /><label for="device_class_water_heater"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassWater"/></label></li>
								<li class="checkbox"><input type="checkbox" id="device_class_pool_pump"name="hancheck" value="POOL_PUMP" /><label for="device_class_pool_pump"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassPool"/></label></li>
								<li class="checkbox"><input type="checkbox" id="device_class_smart_appliances"name="hancheck" value="SMART_APPLIANCES" /><label for="device_class_smart_appliances"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassSmart"/></label></li>
								<li class="checkbox"><input type="checkbox" id="device_class_irrigation"name="hancheck" value="IRRIGATION_PUMP" /><label for="device_class_irrigation"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassIrrigation"/></label></li>
							</ul>
						</div>
						<div class="yui-u">
							<ul>
								<li class="checkbox"><input type="checkbox" id="device_class_cni"name="hancheck" value="MANAGED_COMMERCIAL" /><label for="device_class_cni"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassManaged"/></label></li>
								<li class="checkbox"><input type="checkbox" id="device_class_simple" name="hancheck" value="SIMPLE_MISC" /><label for="device_class_simple"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassSimple"/></label></li>
								<li class="checkbox"><input type="checkbox" id="device_class_ext_lighting" name="hancheck" value="EXTERIOR_LIGHTING" /><label for="device_class_ext_lighting"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassExterior"/></label></li>
								<li class="checkbox"><input type="checkbox" id="device_class_int_lighting" name="hancheck" value="INTERIOR_LIGHTING" /><label for="device_class_int_lighting"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassInterior"/></label></li>
								<li class="checkbox"><input type="checkbox" id="device_class_vehicle" name="hancheck" value="ELECTRIC_VEHICLE" /><label for="device_class_vehicle"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassElectric"/></label></li>
								<li class="checkbox"><input type="checkbox" id="device_class_gen" name="hancheck" value="GENERATION_SYSTEMS" /><label for="device_class_gen"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassGeneration"/></label></li>
							</ul>
						</div>
					</div>
				</div>
			</fieldset>
			<fieldset>
				<legend>
					<a href="#" class="text-button spindown spanToggle">
						<span class="ui-icon-triangle-1-e ui-icon"></span>
						<span id="legend-demand-response"><spring:message code="systemintelligence.dialogDemandResponse.legendRandomize"/></span>
					</a>
				</legend>
				<div class="advanced-search-container yui-gf toggleClass" style="display: none">
					<div class="yui-u first">
						<h5>
							<small><spring:message code="systemintelligence.dialogDemandResponse.randomizeMessage"/></small>
						</h5>
					</div>
					<div class="yui-u">
						<ul>
							<li>
								<label for="randomize-start"><spring:message code="smartpointdetail.tabs.about.randomizeStart"/> :</label>
								<input type="text" id="randomizeStart" class="validate[custom[integer], min[0],max[60]] date-short" /> <spring:message code="commons.pages.minutes"/>
							</li>
							<li>
								<label for="randomize-end"><spring:message code="smartpointdetail.tabs.about.randomizeEnd"/> :</label>
								<input type="text" id="randomizeEnd" class="validate[custom[integer], min[0],max[60]] date-short" /> <spring:message code="commons.pages.minutes"/>
							</li>
						</ul>
					</div>
				</div>
			</fieldset>
		</td>
	</tr>
		
</sec:authorize>
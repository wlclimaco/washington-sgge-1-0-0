<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<sec:authorize access="hasRole('ROLE_EPM_SERVICE_ELECTRIC') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="demand-response-han" class="action-dialog">
		<form id="createDemandResponseForm" name="createDemandResponseForm" action="" method="post">
			<fieldset class="demand-response-fields label-right">
				<legend><spring:message code="systemintelligence.page.event.hanMessageText" /></legend>
				<ul>
					<li>
						<label><spring:message code="systemintelligence.scheduledCreateEvent.when" /><span class="required">*</span></label>
						<div class="radio">
							<input type="text" id="demandResponseWhen" name="demandResponseWhen" class="short validate[required]" value="" />
							<spring:message code="commons.pages.at" />
							<input type="text" id="demandResponseTime" class="time short validate[required, custom[timeFormat]]" name="demandResponseTime" />
							<span class="note timezone">
								<spring:message code="systemintelligence.scheduledCreateEvent.timeZone" />
							</span>
						</div>
					</li>
					<li>
						<label for=""><spring:message code="systemintelligence.page.event.hanDuration"/><span class="required">*</span></label>
						<input type="text" id="duration" name="duration" class="validate[required, custom[integer], min[1],max[1440]] short" />
						<span class="note duration">
							<spring:message code="systemintelligence.dialogDemandResponse.DurationNote" />
						</span>
					</li>
					<li>
						<label for="enrollment-code"><spring:message code="systemintelligence.page.event.hanEnrollment"/><span class="required">*</span></label>
						<input type="text" id="enrollment-code" name="enrollmentCode" class="short validate[required, custom[integer], min[0],max[255]]" />
						<span class="note"><spring:message code="systemintelligence.page.event.msgEnrollmentCode"/></span>
					</li>
					<li>
						<label for="setback"><spring:message code="systemintelligence.page.event.OffsetHeating"/></label>
						<input type="text" id="pctHeating" class="validate[custom[number]] date-short" /> °
						<span class="note temperature-type"></span>
					</li>
					<li>
						<label for="setback"><spring:message code="systemintelligence.page.event.OffsetCooling"/></label>
						<input type="text" id="pctCooling" class="validate[custom[number]] date-short" /> °
						<span class="note temperature-type"></span>
					</li>
					<li>
						<label for="duty-cycle"><spring:message code="systemintelligence.dialogDemandResponse.dutyCycleRate"/></label>
						<input type="text" id="duty-cycle" name="dutyCycle" class="validate[custom[integer], min[0], max[100]] date-short" /> %
					</li>
					<li>
						<label for="load-adjustment"><spring:message code="systemintelligence.dialogDemandResponse.loadAdjustment"/></label>
						<input type="text" id="load-adjustment" name="loadAdjustment" class="validate[custom[integer], min[-100], max[100]] date-short" /> % <spring:message code="systemintelligence.page.event.average"/>
					</li>
					<li>
						<label for="hvac-cycle"><spring:message code="systemintelligence.page.event.hanCriticality"/></label>
					<select id="hanCriticality" >
						<optgroup label="Voluntary">
							<option value="1"><spring:message code="systemintelligence.page.event.Level1" /></option>
							<option value="2"><spring:message code="systemintelligence.page.event.Level2" /></option>
							<option value="3"><spring:message code="systemintelligence.page.event.Level3" /></option>
							<option value="4"><spring:message code="systemintelligence.page.event.Level4" /></option>
							<option value="5"><spring:message code="systemintelligence.page.event.Level5" /></option>
							<option value="6"><spring:message code="systemintelligence.page.event.Level6" /></option>
						</optgroup>
						<optgroup label="Mandatory">
							<option value="7"><spring:message code="systemintelligence.page.event.Level7" /></option>
							<option value="8"><spring:message code="systemintelligence.page.event.Level8" /></option>
							<option value="9"><spring:message code="systemintelligence.page.event.Level9" /></option>
						</optgroup>
						<optgroup label="Utility Defined">
							<option value="10"><spring:message code="systemintelligence.page.event.Level10" /></option>
							<option value="11"><spring:message code="systemintelligence.page.event.Level11" /></option>
							<option value="12"><spring:message code="systemintelligence.page.event.Level12" /></option>
							<option value="13"><spring:message code="systemintelligence.page.event.Level13" /></option>
							<option value="14"><spring:message code="systemintelligence.page.event.Level14" /></option>
							<option value="15"><spring:message code="systemintelligence.page.event.Level15" /></option>
						</optgroup>
					</select>
					</li>
					<li>
						<label for="randomize"><spring:message code="systemintelligence.page.event.hanRandomize"/></label>
						<ul class="radio-list">
							<li class="radio">
								<input id="randomizeStart" name="hanStart" type="checkbox" id="randomize" /> <spring:message code="systemintelligence.page.event.hanStart"/>
							</li>
							<li class="radio">
								<input id="randomizeEnd" name="hanEnd" type="checkbox" id="randomize" /> <spring:message code="systemintelligence.page.event.hanEnd"/>
							</li>
						</ul>
					</li>
				</ul>
			</fieldset>

			<fieldset class="demand-response-fields">
				<legend>
					<a id="click" href="#" class="text-button spindown">
						<span class="ui-icon-triangle-1-e ui-icon"></span>
						<span id="legend-demand-response"><spring:message code="systemintelligence.dialogDemandResponse.legendClass" /></span>
					</a>
				</legend>
				<div id="toggleClass" class="advanced-search-container yui-gf">
					<div class="yui-u first">
						<h5>
							<small><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassMessage"/></small>
						</h5>
					</div>
					<div class="yui-g">
						<div class="yui-u">
							<ul>
								<li class="checkbox"><input type="checkbox" id="device_class_hvac" name="hancheck" value="HVACCompressor" /><label for="device_class_hvac"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassHVAC"/></label></li>
								<li class="checkbox"><input type="checkbox" id="device_class_heater"name="hancheck" value="StripHeaters" /><label for="device_class_heater"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassStrip"/></label></li>
								<li class="checkbox"><input type="checkbox" id="device_class_water_heater"name="hancheck" value="WaterHeater" /><label for="device_class_water_heater"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassWater"/></label></li>
								<li class="checkbox"><input type="checkbox" id="device_class_pool_pump"name="hancheck" value="PoolPump" /><label for="device_class_pool_pump"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassPool"/></label></li>
								<li class="checkbox"><input type="checkbox" id="device_class_smart_appliances"name="hancheck" value="SmartAppliances" /><label for="device_class_smart_appliances"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassSmart"/></label></li>
								<li class="checkbox"><input type="checkbox" id="device_class_irrigation"name="hancheck" value="IrrigationPump" /><label for="device_class_irrigation"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassIrrigation"/></label></li>
							</ul>
						</div>
						<div class="yui-u">
							<ul>
								<li class="checkbox"><input type="checkbox" id="device_class_cni"name="hancheck" value="IndustrialLoads" /><label for="device_class_cni"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassManaged"/></label></li>
								<li class="checkbox"><input type="checkbox" id="device_class_simple" name="hancheck" value="SimpleMiscLoads" /><label for="device_class_simple"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassSimple"/></label></li>
								<li class="checkbox"><input type="checkbox" id="device_class_ext_lighting" name="hancheck" value="ExteriorLighting" /><label for="device_class_ext_lighting"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassExterior"/></label></li>
								<li class="checkbox"><input type="checkbox" id="device_class_int_lighting" name="hancheck" value="InteriorLighting" /><label for="device_class_int_lighting"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassInterior"/></label></li>
								<li class="checkbox"><input type="checkbox" id="device_class_vehicle" name="hancheck" value="ElectricVehicle" /><label for="device_class_vehicle"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassElectric"/></label></li>
								<li class="checkbox"><input type="checkbox" id="device_class_gen" name="hancheck" value="GenerationSystems" /><label for="device_class_gen"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassGeneration"/></label></li>
							</ul>
						</div>
					</div>
				</div>
			</fieldset>
		</form>
		<input type="text" id="date-today-DR" name="date-today" class="hide" value=""/>
	</div>

</sec:authorize>
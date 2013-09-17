<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div class="content-inner">

		<div id="yui-main">

			<div class="content-header">
				<h1 id="title-scheduled"><spring:message code="systemintelligence.scheduledCreateEvent" /></h1>
				<p class="description">
					<spring:message code="systemintelligence.scheduledCreateEvent.bodyDescription" />
					<span class="required"><spring:message code="commons.pages.required"/></span>
					<spring:message code="commons.pages.information" />
				</p>
			</div>

			<!-- Content of Schedule -->
			<div id="scheduleList">

				<!-- Button Back -->
				<div class="create-action-container">
					<a class="cancel back url ajax-button" id="back-list" href="schedule"> <spring:message code="systemintelligence.scheduledCreateEvent.backEvent" /></a>&nbsp;&nbsp;
				</div>

				<form id="createEventForm" name="createEventForm" method="post" action="schedule/upload" enctype="multipart/form-data">

					<!-- Schedule Definition -->
					<fieldset>
						<!-- Messaging -->
						<div class="ui-state-error ui-corner-all hide">
							<p>
								<span class="ui-icon ui-icon-alert"></span>
								<strong><spring:message code="commons.pages.messageError" /></strong>
								<spring:message code="systemintelligence.scheduledCreateEvent.messaging" />
							</p>
						</div>

						<ul>
							<!-- Event Name -->
							<li>
								<label for="scheduleNameCreate"><spring:message code="systemintelligence.scheduledCreateEvent.name" /><span class="required">*</span></label>
								<input type="text" id="scheduledEventName" name="scheduledEventName" tabindex="1" class="validate[required, custom[noSpecialCaracters], maxSize[200]] long"/>
								<input type="hidden" id="scheduledEventName-old" name="scheduledEventName-old"/>
								<input type="hidden" name="bMonitored" id="b-monitored" />
									<input type="hidden" id="actionId" name="actionId"/>
							</li>
							<!-- Description -->
							<li>
								<label for="scheduleDescription"><spring:message code="commons.pages.descriptionTwo"  /></label>
								<textarea id="scheduledEventDescription" name="scheduledEventDescription" tabindex="2" class="validate[maxSize[200]] long"></textarea>
							</li>
						</ul>
						<input type="hidden" name="scheduledEventId" id="scheduledEventId"/>
					</fieldset>

					<!-- Define Event -->
					<fieldset id="override">
						<legend><spring:message code="systemintelligence.scheduledCreateEvent.define" /></legend>

						<div id="event1" class="event-container">
							<ul class="fields">

								<!-- Button Action -->
								<li class="highlight">
									<label>
										<spring:message code="systemintelligence.scheduledCreateEvent.action" />
										<span class="required">*</span>
									</label>
									<div class="yui-pad2">

										<a tabindex="1" href="#actions-options" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all" id="actions-button">
											<span class="ui-icon ui-icon-triangle-1-s"></span>
											<spring:message code="commons.pages.actions" />
										</a>
										<div id="actions-options" class="hidden">
											<ul id="action-type-content">
											</ul>
										</div>

									</div>
								</li>

								<!-- Button When  -->
								<li>
									<label class="double-height">
										<spring:message code="systemintelligence.scheduledCreateEvent.when" />
										<span class="required">*</span>
									</label>

									<div class="radio">
										<input type="text" id="scheduledEventWhen" tabindex="4" name="scheduledEventWhen" />
										<spring:message code="commons.pages.at" />
										<input type="text" id="scheduleEventTime" class="validate[required, custom[timeFormat]] time" tabindex="5" name="scheduleEventTime" />
										<span class="note timezone">
											<spring:message code="systemintelligence.scheduledCreateEvent.timeZone" />
										</span>
									</div>
									<input type="hidden" name="actionName" id="actionName" />

									<div class="checkbox sub-input submit-row-pad">
										<spring:message code="systemintelligence.page.event.repeat" />
										<input id="repeatCheckBox" name="repeatCheckBox" type="checkbox"/>

										<strong id="description-repeat"></strong>

										<a class="text-button hide" style="margin-left:10px;" href="edit" id="edit-repeats"><spring:message code="commons.pages.edit" /></a>
									</div>
								</li>
							</ul>
						</div>
					</fieldset>
					<fieldset class="han-text-message hide">
						<legend><spring:message code="systemintelligence.page.event.hanTextMessage"/></legend>
							<ul>
								<li>
									<p class="desc"><spring:message code="systemintelligence.page.event.hanDescription"/></p>
									<div id="notes" class="sui-pad">
									  <textarea id="textMessageHan"  name="textMessageHan" class="validate[required, maxSize[120]] long"></textarea>
									</div>
								</li>
								<li class="highlight sui-pad1h">
									<spring:message code="systemintelligence.page.event.hanMessage"/>
									<input type="text" id="text_message_duration" name="textMessageDuration" class="validate[custom[integer],required, min[1],max[60]] date-short">&nbsp;<spring:message code="commons.pages.minutes"/>.
								</li>
							 </ul>
							  <fieldset class="pre-program-fields">
									<legend><spring:message code="systemintelligence.scheduledCreateEvent.deliverOnDate"/></legend>
										<div class="advanced-search-container group yui-gf">
											<div class="yui-u first">
												<h5><spring:message code="systemintelligence.scheduledCreateEvent.preProgram"/>
													<small><spring:message code="systemintelligence.scheduledCreateEvent.preProgramMessage"/></small>
												</h5>
											</div>
											<div class="yui-u">
												<ul class="sui-pad1v">
													<li class="radio">
														<input type="radio" id="sendNow" value="sendNow" name="preProgram" checked="checked"/><label for="send_now"><spring:message code="systemintelligence.scheduledCreateEvent.sendNow"/></label>
													</li>
													<li class="radio">
														<input type="radio" id="sendLater" name="preProgram" value="sendLater"/>
														<spring:message code="systemintelligence.scheduledCreateEvent.sendMessages"/> <input type="text" id="preProgramDate" class="short datepicker" name="sendMessagesDate"/> <spring:message code="commons.pages.at"/> <input type="text" id="preProgramTime" class="time short" name="sendMessagesTime" />
													</li>
												</ul>
											</div>
										</div>
								</fieldset>
					</fieldset>
					<fieldset class="demand-response-fields_hide hide">
						<fieldset class="demand-response-fields">
							<legend><spring:message code="systemintelligence.page.event.hanMessageText"/></legend>
								<div class="advanced-search-container group yui-gf">
									<div class="yui-u first">
										<h5>
											<small><spring:message code="systemintelligence.page.event.hanDemandResponseFields"/></small>
										</h5>
									</div>
									<div class="yui-u">
										<ul>
											<li>
												<label for=""><spring:message code="systemintelligence.page.event.hanDuration"/><span class="required">*</span></label>
												<input type="text" id="duration" name="duration" class="validate[required, custom[integer], min[1],max[1440]] short"/>
												<span class="note duration">
													<spring:message code="systemintelligence.dialogDemandResponse.DurationNote" />
												</span>
											</li>
											<li>
												<label for="enrollment-code"><spring:message code="systemintelligence.page.event.hanEnrollment"/><span class="required">*</span></label>
												<input type="text" id="enrollment-code" name="enrollmentCode" class="validate[required, custom[integer],min[0],max[255]] short"/>
												<span class="note"><spring:message code="systemintelligence.page.event.msgEnrollmentCode"/></span>
											</li>
											 <li>
												<label for="setback"><spring:message code="systemintelligence.page.event.OffsetHeating"/></label>
												<input type="text" id="pctHeating" name="pctHeating" class="date-short validate[custom[number]]"/> °
												<span class="note temperature-type"></span>
											</li>
											<li>
												<label for="setback"><spring:message code="systemintelligence.page.event.OffsetCooling"/></label>
												<input type="text" id="pctCooling" name="pctCooling" class="date-short  validate[custom[number]]"/> °
												<span class="note temperature-type"></span>
											</li>
											<li>
												<label for="duty-cycle"><spring:message code="systemintelligence.dialogDemandResponse.dutyCycleRate"/></label>
												<input type="text" id="duty-cycle" name="dutyCycle" class="date-short validate[custom[number]]"/> %
											</li>
											<li>
												<label for="load-adjustment"><spring:message code="systemintelligence.dialogDemandResponse.loadAdjustment"/></label>
												<input type="text" id="load-adjustment" name="loadAdjustment" class="validate[custom[integer], min[-100], max[100]] date-short" /> % <spring:message code="systemintelligence.page.event.average"/>
											</li>
											<li>
												<label for="hvac-cycle"><spring:message code="systemintelligence.page.event.hanCriticality" /></label>
												<select id="hanCriticality" name="hanCriticality">
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
														<input id="randomizeStart" name="hanStart" type="checkbox" /> <spring:message code="systemintelligence.page.event.hanStart"/>
													</li>
													<li class="radio">
														<input id="randomizeEnd" name="hanEnd" type="checkbox" /> <spring:message code="systemintelligence.page.event.hanEnd"/>
													</li>
												</ul>
											</li>
										</ul>
									</div>
								</div>
						</fieldset>
						<fieldset class="demand-response-fields">
							<legend><spring:message code="systemintelligence.scheduledCreateEvent.deviceClass"/></legend>
							<div class="advanced-search-container group yui-gf">
								<div class="yui-u first">
									<h5>
										<small><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassMessage"/></small>
									</h5>
								</div>
								<div class="yui-g">
									<div class="yui-u first">
										<ul>
											<li class="checkbox"><input type="checkbox" id="device_class_hvac" name="hancheck" value="HVACCompressor" /><label for="device_class_hvac"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassHVAC"/></label></li>
											<li class="checkbox"><input type="checkbox" id="device_class_heater"name="hancheck" value="StripHeaters" /><label for="device_class_heater"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassStrip"/></label></li>
											<li class="checkbox"><input type="checkbox" id="device_class_water_heater"name="hancheck" value="WaterHeater" /><label for="device_class_water_heater"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassWater"/></label></li>
											<li class="checkbox"><input type="checkbox" id="device_class_pool_pump"name="hancheck" value="PoolPump" /><label for="device_class_pool_pump"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassPool"/></label></li>
											<li class="checkbox"><input type="checkbox" id="device_class_smart_appliances"name="hancheck" value="SmartAppliances" /><label for="device_class_smart_appliances"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassSmart"/></label></li>
											<li class="checkbox"><input type="checkbox" id="device_class_irrigation"name="hancheck" value="IrrigationPump" /><label for="device_class_irrigation"><spring:message code="systemintelligence.scheduledCreateEvent.deviceClassIrrigation"/></label></li>
										</ul>
									</div>
									<div class="yui-u first">
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
						<fieldset class="pre-program-fields">
							<legend><spring:message code="systemintelligence.scheduledCreateEvent.programDevice"/></legend>
							<div class="advanced-search-container group yui-gf">
								<div class="yui-u first">
									<h5><spring:message code="systemintelligence.scheduledCreateEvent.preProgram"/>
										<small><spring:message code="systemintelligence.scheduledCreateEvent.preProgramMessageToDR"/></small>
									</h5>
								</div>
								<div class="yui-u">
									<ul class="sui-pad1v">
										<li class="radio">
											<input type="radio" id="send_now" name="demandResponsePreProgram" value="sendNow" checked="checked"/><label for="send_now"><spring:message code="systemintelligence.scheduledCreateEvent.sendNow"/></label>
										</li>
										<li class="radio">
											<input type="radio" id="send_later" name="demandResponsePreProgram" value="sendLater" />
											<spring:message code="systemintelligence.scheduledCreateEvent.sendMessages"/> <input type="text" id="pre_program_date" name="demandResponseDate" class="short datepicker" value="" /> <spring:message code="commons.pages.at"/> <input type="text" name="demandResponseTime" id="timeHan" class="validate[required, custom[timeFormat]] time short"/>
										</li>
									</ul>
								</div>
							</div>
						</fieldset>
					</fieldset>
					<!-- Stripe - Select a Group or import a delimited list of SmartPoints -->
					<fieldset>
						<legend><spring:message code="systemintelligence.scheduledCreateEvent.selectDevices"/></legend>
					</fieldset>

					<!-- START: Tabs -->
					<div id="tabs" class="tabs-bottom">
						<ul>
							<li><a id="select-groups" href="#tab-groups"><spring:message code="commons.pages.groups" /></a></li>
							<li><a id="select-tags" href="#tab-tags"><spring:message code="commons.pages.tags" /></a></li>
							<li><a id="import-devices" href="#tab-upload-list"><spring:message code="systemintelligence.scheduledCreateEvent.upload" /></a></li>
						</ul>
						<!-- Groups -->
						<div id="tab-groups">
							<fieldset>
								<div class="spindown_child">
									<!-- START: Group Search -->
									<input type="hidden" id="listGroups" name="listGroups"/>

									<div class="advanced-search-container group yui-gf" id="GroupsDiv">
										<div class="yui-u first">
											<h5>
												<spring:message code="commons.pages.Groups"/>
												<small><spring:message code="systemintelligence.scheduledCreateEvent.groupInformation"/></small>
											</h5>
										</div>
										<div class="yui-u">
											<div id="group-list">
												<ul class="ui-listcombobox"></ul>
											</div>
											<br/>
											<!--<s:select name="combo_group" list="ListGroup" listKey="id" cssClass="combobox" listValue="value" multiple="false" size="1" required="false" />  -->
											<select class="combobox" id="combo_group" size="1" name="combo_group" style="display: none;"></select>
										</div>
									</div>
									<!-- END: Group Search -->
								</div>
							</fieldset>
						</div>
						<!-- TAGS -->
						<div id="tab-tags">
							<fieldset>
								<div class="spindown_child">
									<!-- START: Tag Search -->

									<input type="hidden" id="listTags" name="listTags"/>

									<div class="advanced-search-container group yui-gf" id="TagsDiv">
										<div class="yui-u first">
											<h5>
												<spring:message code="commons.pages.tags"/>
												<small><spring:message code="systemintelligence.scheduledCreateEvent.tagInformation"/></small>
											</h5>
										</div>
										<div class="yui-u">
											<div id="tag-list">
												<ul class="ui-listcombobox"></ul>
											</div>
											<br/>
											<!-- TODO <s:select name="combo_tag" list="ListTag" listKey="id" cssClass="combobox" listValue="value" multiple="false" size="1" required="false" /> -->
											<select class="combobox" id="combo_tag" size="1" name="combo_tag" style="display: none;"></select>
										</div>
									</div>
									<!-- END: Tag Search -->
								</div>
							</fieldset>
						</div>
						<!-- Upload List -->
						<div id="tab-upload-list">
							<fieldset>
								<div class="spindown_child">
									<!-- START: Configuration Search -->
									<div class="advanced-search-container configuration yui-gf" id="MetersDiv">
										<div class="yui-u first">
											<h5><spring:message code="systemintelligence.scheduledCreateEvent.uploadDelimited"/>
												<small><spring:message code="systemintelligence.scheduledCreateEvent.uploadinformation"/></small>
											</h5>
										</div>

										<div class="yui-u">
											<ul class="collapse">
												<li class="highlight">
													<label for="upload_ids"><spring:message code="systemintelligence.scheduledCreateEvent.uploadImport"/></label>

													<div class="radio sui-padv">

														<input type="radio" name="importBy" id="upload-type-meter" value="DEVICE_ID" checked="checked">
														<label for="upload_type_meter"><spring:message code="commons.pages.device_id"/></label>

														<input type="radio" name="importBy" id="upload-type-flexnet" value="NETWORK_ADDRESS">
														<label for="upload_type_flexnet"><spring:message code="commons.pages.network_address"/></label>
													</div>

												</li>
												<li class="row-pad">
													<div class="sui-pad"><spring:message code="systemintelligence.scheduledCreateEvent.uploadFile"/></div>

													<input type="file" name="upload" id="upload"/>

													<span class="note"><spring:message code="commons.pages.upload.span.note"/></span>
												</li>
												<li class="row-pad">
													<div class="sui-pad"><spring:message code="systemintelligence.scheduledCreateEvent.uploadCopy"/></div>
													<textarea name="meterList" id="listSmartPoint" rows="8" cols="20" class="long"></textarea>
												</li>
											</ul>
										</div>
									</div>
									<!-- END: Address Search -->
								</div>
							</fieldset>
						</div>
					</div>
					<!-- END: Tabs -->

					<!-- Monitor Event -->
					<fieldset>
						<legend><spring:message code="systemintelligence.scheduledCreateEvent.monitorEvent"/></legend>

						<!-- START: monitor event -->
						<div class="advanced-search-container configuration yui-gf">
							<div class="yui-u first">
								<h5>
									<small><spring:message code="systemintelligence.scheduledCreateEvent.monitorInformation"/></small>
								</h5>
							</div>
							<input type="hidden" id="action-view-id" name="actionViewId"/>
							<div class="yui-u">
								<ul class="collapse">
									<li class="checkbox">
										<input type="checkbox" name="recentRequestMonitor" id="recentRequestMonitor" value="recentRequestMonitor"/>
										<label for="recentRequestMonitor"><spring:message code="commons.pages.recentRequests" /></label>
									</li>
								</ul>
							</div>
						</div>
					</fieldset>

					<!-- Apply To -->
					<fieldset>
						<legend>
							<strong>
								<spring:message code="systemintelligence.scheduledCreateEvent.applyTo"/>
								<span id="total-devices" class="size"></span>
							</strong>
							<spring:message code="commons.pages.smartPoints"/>
							<span class="note"><em><spring:message code="systemintelligence.scheduledCreateEvent.SmartPointsInformation"/></em></span>
						</legend>
						<ul>
							<li class="row-pad">
								<input type="submit" id="createAction" class="button" value="<spring:message code="systemintelligence.scheduledCreateEvent.createEvent"/>" /> or
								<a id="cancel-schedule" href="schedule" class="cancel ajax-button back url">
									<spring:message code="commons.pages.cancel"/>
								</a>
							</li>
						</ul>
					</fieldset>



					<!-- Dialog Reats -->
					<div id="actionDialogRepeatDefine" class="action-dialog hide">

						<div id="addRepeatDefinitionForm" name="addRepeatDefinitionForm">
							<div id="saveFormMessaging" class="messaging">
								<span class="message"></span>
							</div>
							<fieldset>
								<ul>
									<li class="highlight">
										<label for="repeats" class="title short">
											<spring:message code="systemintelligence.dialogCreateEvent.repeat" />
										</label>
										<select id="repeats" name="repeatType">
											<option id="day" value="DAILY" selected="selected"><spring:message code="commons.pages.daily" /></option>
											<option id="weekday" value="EVERY_WEEKDAY"><spring:message code="systemintelligence.dialogCreateEvent.weekday" /></option>
											<option id="every-other" value="EVERY_MON_WED_FRI"><spring:message code="systemintelligence.dialogCreateEvent.mon" /></option>
											<option id="every-t" value="EVERY_TUE_THURS"><spring:message code="systemintelligence.dialogCreateEvent.tues" /></option>
											<option id="weekly" value="WEEKLY"><spring:message code="commons.pages.weekly" /></option>
											<option id="monthly" value="MONTHLY"><spring:message code="commons.pages.monthly" /></option>
											<option id="yearly" value="YEARLY"><spring:message code="commons.pages.yearly" /></option>
											<!-- <option id="custom" value="CUSTOM"><spring:message code="commons.pages.custom" /></option> -->
										</select>
									</li>

									<fieldset id="repeats-form">
										<ul id="day-form" class="repeat-form">
											<li id="starts-on">
												<label for="starts-on" class="title short"><spring:message code="systemintelligence.dialogCreateEvent.starts_on" /></label>
												<input type="text" class="short" disabled="disabled" id="repeatStartDateDaily" name="repeatStartDateDaily"/>
											</li>

											<li id="repeats-every">
												<label for="saved-search-description" class="title short"><spring:message code="systemintelligence.dialogCreateEvent.repeats_every" /></label>
												<input type="text" maxlength="5" class="date-short validate[required,custom[integer],min[1],max[255]]" id="repeats-every-value" name="repeatsEveryValueDaily"/>
												<span id="repeats-text"> <spring:message code="systemintelligence.dialogCreateEvent.days" /> </span>
											</li>

											<li id="ends-on">
												<label for="startime-combobox" class="title short"><spring:message code="systemintelligence.dialogCreateEvent.ends" />:</label>
												<ul class="radio-list">
													<li class="radio">
														<input type="radio" id="never" name="endsDaily" value="never" checked='checked'/>
														<label for="never"><spring:message code="systemintelligence.dialogCreateEvent.never" /></label>
													</li>
													<li class="radio">
														<input type="radio" id="after" name="endsDaily" value="after">
														<label for="after"><spring:message code="systemintelligence.dialogCreateEvent.after" /></label>
														<input type="text" maxlength="5" class="date-short validate[required,custom[integer],min[1],max[255]]" id="occurrences" name="occurrencesDaily"/>
														<spring:message code="systemintelligence.dialogCreateEvent.occurrences" />
													</li>
													<li class="radio">
														<input type="radio" id="on" class="on" name="endsDaily" value="on">
														<label for="on"> <spring:message code="systemintelligence.dialogCreateEvent.on" /></label>
														<input type="text" class="short datepicker" id="date-after-daily" name="dateAfterDaily"/>
													</li>
												</ul>
											</li>
										</ul>
										<ul id="weekday-form" class="repeat-form">
											<li id="starts-on">
												<label for="starts-on" class="title short"><spring:message code="systemintelligence.dialogCreateEvent.starts_on" /></label>
												<input type="text" class="short" disabled="disabled" id="repeat-start-date-weekday" name="repeatStartDateWeekday"/>
											</li>

											<li id="ends-on">
												<label for="startime-combobox" class="title short"><spring:message code="systemintelligence.dialogCreateEvent.ends" />:</label>
												<ul class="radio-list">
													<li class="radio">
														<input type="radio" id="never" name="endsWeekday" value="never" checked="checked" />
														<label for="never"><spring:message code="systemintelligence.dialogCreateEvent.never" /></label>
													</li>
													<li class="radio">
														<input type="radio" id="after-weekday" name="endsWeekday" value="after">
														<label for="after"><spring:message code="systemintelligence.dialogCreateEvent.after" /></label>
														<input type="text" maxlength="5" class="date-short validate[required,custom[integer],min[1],max[255]]" id="occurrences-weekday" name="occurrencesWeekday"/>
														<spring:message code="systemintelligence.dialogCreateEvent.occurrences" />
													</li>
													<li class="radio">
														<input type="radio" id="on-weekday" class="on" name="endsWeekday" value="on">
														<label for="on"> <spring:message code="systemintelligence.dialogCreateEvent.on" /></label>
														<input type="text" class="short datepicker" id="date-after-dateWeekDay" name="dateAfterWeekday"/>
													</li>
												</ul>
											</li>

										</ul>
										<ul id="every-other-form"  class="repeat-form">
											<li id="starts-on">
												<label for="starts-on" class="title short"><spring:message code="systemintelligence.dialogCreateEvent.starts_on" /></label>
												<input type="text" class="short" disabled="disabled" id="repeat-start-date-every-other" name="repeatStartDateEveryOther"/>
											</li>

											<li id="ends-on">
												<label for="startime-combobox" class="title short"><spring:message code="systemintelligence.dialogCreateEvent.ends" />:</label>
												<ul class="radio-list">
													<li class="radio">
														<input type="radio" id="never" name="endsEveryOther" value="never" checked="checked" />
														<label for="never"><spring:message code="systemintelligence.dialogCreateEvent.never" /></label>
													</li>
													<li class="radio">
														<input type="radio" id="after-every-other" name="endsEveryOther" value="after">
														<label for="after"><spring:message code="systemintelligence.dialogCreateEvent.after" /></label>
														<input type="text" maxlength="5" class="date-short validate[required,custom[integer],min[1],max[255]]" id="occurrences-every-other" name="occurrencesEveryOther"/>
														<spring:message code="systemintelligence.dialogCreateEvent.occurrences" />
													</li>
													<li class="radio">
														<input type="radio" id="on-every-other" class="on" name="endsEveryOther" value="on">
														<label for="on"> <spring:message code="systemintelligence.dialogCreateEvent.on" /></label>
														<input type="text" class="short datepicker" id="date-after-dateEveryOther" name="dateAfterEveryOther"/>
													</li>
												</ul>
											</li>

										</ul>
										<ul id="every-t-form"  class="repeat-form">
											<li id="starts-on">
												<label for="starts-on" class="title short"><spring:message code="systemintelligence.dialogCreateEvent.starts_on" /></label>
												<input type="text" class="short"  disabled="disabled" id="repeat-start-date-every-t" name="repeatStartDateEveryT"/>
											</li>

											<li id="ends-on">
												<label for="startime-combobox" class="title short"><spring:message code="systemintelligence.dialogCreateEvent.ends" />:</label>
												<ul class="radio-list">
													<li class="radio">
														<input type="radio" id="never" name="endsEveryT" value="never" checked="checked" />
														<label for="never"><spring:message code="systemintelligence.dialogCreateEvent.never" /></label>
													</li>
													<li class="radio">
														<input type="radio" id="after-every-t" name="endsEveryT" value="after">
														<label for="after"><spring:message code="systemintelligence.dialogCreateEvent.after" /></label>
														<input type="text" maxlength="5" class="date-short validate[required,custom[integer],min[1],max[255]]" id="occurrences-every-t" name="occurrencesEveryT"/>
														<spring:message code="systemintelligence.dialogCreateEvent.occurrences" />
													</li>
													<li class="radio">
														<input type="radio" id="on-every-t" class="on" name="endsEveryT" value="on">
														<label for="on-every-t"> <spring:message code="systemintelligence.dialogCreateEvent.on" /></label>
														<input type="text" class="short datepicker" id="date-after-dateEvery" name="dateAfterEveryT"/>
													</li>
												</ul>
											</li>

										</ul>
										<ul id="weekly-form" class="repeat-form">
											<li id="starts-on">
												<label for="starts-on" class="title short"><spring:message code="systemintelligence.dialogCreateEvent.starts_on" /></label>
												<input type="text" class="short" disabled="disabled" id="repeat-start-date-weekly" name="repeatStartDateWeekly"/>
											</li>

											<li id="repeats-every">
												<label for="saved-search-description" class="title short"><spring:message code="systemintelligence.dialogCreateEvent.repeats_every" /></label>
												<input type="text" maxlength="5" class="date-short validate[required,custom[integer],min[1],max[255]]" id="repeats-every-value-weekly" name="repeatsEveryValueWeekly"/>
												<span id="repeats-text"> <spring:message code="systemintelligence.dialogCreateEvent.weeks" /> </span>
											</li>

											<li id="day-custom-container">
												<label class="title short"><spring:message code="systemintelligence.dialogCreateEvent.repeatOn" /></label>

												<input type="hidden" id="list-of-days-selected" name="list-of-days-selected"/>

												<div id="validate_day_week" class="event-day-custom checkbox" >
													<input type="checkbox" class="validate[minCheckbox[1]]" id="MONDAY" value="2" name="dayWeekly">
													<label for="MONDAY"><spring:message code="systemintelligence.dialogCreateEvent.Mon" /></label>

													<input type="checkbox" class="validate[minCheckbox[1]]" id="TUESDAY" value="3" name="dayWeekly">
													<label for="TUESDAY"><spring:message code="systemintelligence.dialogCreateEvent.tue" /></label>

													<input type="checkbox" class="validate[minCheckbox[1]]" id="WEDNESDAY" value="4" name="dayWeekly">
													<label for="WEDNESDAY"><spring:message code="systemintelligence.dialogCreateEvent.wed" /></label>

													<input type="checkbox" class="validate[minCheckbox[1]]" id="THURSDAY" value="5" name="dayWeekly">
													<label for="THURSDAY"><spring:message code="systemintelligence.dialogCreateEvent.thu" /></label>

													<input type="checkbox" class="validate[minCheckbox[1]]" id="FRIDAY" value="6" name="dayWeekly">
													<label for="FRIDAY"><spring:message code="systemintelligence.dialogCreateEvent.fri" /></label>

													<input type="checkbox" class="validate[minCheckbox[1]]" id="SATURDAY" value="7" name="dayWeekly">
													<label for="SATURDAY"><spring:message code="systemintelligence.dialogCreateEvent.sat" /></label>

													<input type="checkbox" class="validate[minCheckbox[1]]" id="SUNDAY" value="1" name="dayWeekly">
													<label for="SUNDAY"><spring:message code="systemintelligence.dialogCreateEvent.sun" /></label>
													<div><label for="day_validation" class="hide" style="clear: both"></label></div>
												</div>
											</li>

											<li id="ends-on">
												<label for="startime-combobox" class="title short"><spring:message code="systemintelligence.dialogCreateEvent.ends" />:</label>
												<ul class="radio-list">
													<li class="radio">
														<input type="radio" id="never" name="endsWeekly" value="never" checked="checked" />
														<label for="never"><spring:message code="systemintelligence.dialogCreateEvent.never" /></label>
													</li>
													<li class="radio">
														<input type="radio" id="after-weekly" name="endsWeekly" value="after">
														<label for="after"><spring:message code="systemintelligence.dialogCreateEvent.after" /></label>
														<input type="text" maxlength="5" class="date-short validate[required,custom[integer],min[1],max[255]]" id="occurrences-weekly" name="occurrencesWeekly"/>
														<spring:message code="systemintelligence.dialogCreateEvent.occurrences" />
													</li>
													<li class="radio">
														<input type="radio" id="on-weekly" class="on" name="endsWeekly" value="on">
														<label for="on"> <spring:message code="systemintelligence.dialogCreateEvent.on" /></label>
														<input type="text" class="short datepicker" id="date-after-EveryValueWeekly" name="dateAfterWeekly"/>
													</li>
												</ul>
											</li>
										</ul>

										<ul id="monthly-form" class="repeat-form">
											<li id="starts-on">
												<label for="starts-on" class="title short"><spring:message code="systemintelligence.dialogCreateEvent.starts_on" /></label>
												<input type="text" class="short" disabled="disabled" id="repeat-start-date-monthly" name="repeatStartDateMonthly"/>
											</li>

											<li id="repeats-every">
												<label for="saved-search-description" class="title short"><spring:message code="systemintelligence.dialogCreateEvent.repeats_every" /></label>
												<input type="text" maxlength="5" class="date-short validate[required,custom[integer],min[1],max[255]]" id="repeats-every-value-monthly" name="repeatsEveryValueMonthly"/>
												<span id="repeats-text"> <spring:message code="systemintelligence.dialogCreateEvent.months" /> </span>
											</li>

											<li id="repeats-monthly-custom">
												<label for="startime-combobox" class="title short"><spring:message code="commons.pages.repeat" /></label>
												<ul class="radio-list">
													<li class="radio">
														<input type="radio" id="date" name="repeatByMonthly" value="date" checked="checked"/>
														<label id="neverLabel" for="never"><spring:message code="commons.pages.date"/></label>
														<input type="radio" id="day-week" name="repeatByMonthly" value="day-week" />
														<label id="neverLabel" for="never"><spring:message code="commons.pages.DayWeek"/></label>
													</li>
												</ul>
											</li>

											<li id="ends-on">
												<label for="startime-combobox" class="title short"><spring:message code="systemintelligence.dialogCreateEvent.ends" />:</label>
												<ul class="radio-list">
													<li class="radio">
														<input type="radio" id="never" name="endsMonthly" value="never" checked="checked" />
														<label for="never"><spring:message code="systemintelligence.dialogCreateEvent.never" /></label>
													</li>
													<li class="radio">
														<input type="radio" id="after-repeats-monthly" name="endsMonthly" value="after">
														<label for="after"><spring:message code="systemintelligence.dialogCreateEvent.after" /></label>
														<input type="text" maxlength="5" class="date-short validate[required,custom[integer],min[1],max[255]]" id="occurrences-repeats-monthly" name="occurrencesMonthly"/>
														<spring:message code="systemintelligence.dialogCreateEvent.occurrences" />
													</li>
													<li class="radio">
														<input type="radio" id="on-repeats-monthly" class="on" name="endsMonthly" value="on">
														<label for="on"> <spring:message code="systemintelligence.dialogCreateEvent.on" /></label>
														<input type="text" class="short datepicker" id="date-after-EveryValueMonthly" name="dateAfterMonthly"/>
													</li>
												</ul>
											</li>

										</ul>
										<ul id="yearly-form" class="repeat-form">
											<li id="starts-on">
												<label for="starts-on" class="title short"><spring:message code="systemintelligence.dialogCreateEvent.starts_on" /></label>
												<input type="text" class="short" disabled="disabled" id="repeat-start-date-yearly" name="repeatStartDateYearly"/>
											</li>

											<li id="repeats-every">
												<label for="saved-search-description" class="title short"><spring:message code="systemintelligence.dialogCreateEvent.repeats_every" /></label>
												<input type="text" maxlength="5" class="date-short validate[required,custom[integer],min[1],max[255]]" id="repeats-every-value-yearly" name="repeatsEveryValueYearly"/>
												<span id="repeats-text"> <spring:message code="systemintelligence.dialogCreateEvent.years" /> </span>
											</li>

											<li id="ends-on">
												<label for="startime-combobox" class="title short"><spring:message code="systemintelligence.dialogCreateEvent.ends" />:</label>
												<ul class="radio-list">
													<li class="radio">
														<input type="radio" id="never" name="endsYearly" value="never" checked="checked" />
														<label for="never"><spring:message code="systemintelligence.dialogCreateEvent.never" /></label>
													</li>
													<li class="radio">
														<input type="radio" id="after-yearly" name="endsYearly" value="after">
														<label for="after"><spring:message code="systemintelligence.dialogCreateEvent.after" /></label>
														<input type="text" maxlength="5" class="date-short validate[required,custom[integer],min[1],max[255]]" id="occurrences-yearly" name="occurrencesYearly"/>
														<spring:message code="systemintelligence.dialogCreateEvent.occurrences" />
													</li>
													<li class="radio">
														<input type="radio" id="on-yearly" class="on" name="endsYearly" value="on">
														<label for="on"> <spring:message code="systemintelligence.dialogCreateEvent.on" /></label>
														<input type="text" class="short datepicker" id="date-after-EveryValueYearly" name="dateAfterYearly"/>
													</li>
												</ul>
											</li>
										</ul>

										<div id="form">
											<input type="hidden" id="action-name" 					name="actionTest"/>
											<input type="hidden" id="has-repeat" 					name="has-repeat"/>
											<input type="hidden" id="repeat-type-select" 			name="repeat-type"/>
											<input type="hidden" id="day-weekly" 					name="day-weekly"/>
											<input type="hidden" id="repeat-by-monthly" 			name="repeat-by-monthly"/>
											<input type="hidden" id="ends-daily-radio" 				name="ends-daily"/>
											<input type="hidden" id="ends-weekday-radio" 			name="ends-weekday"/>
											<input type="hidden" id="ends-every-other-radio" 		name="ends-every-other"/>
											<input type="hidden" id="ends-every-t-radio" 			name="ends-every-t"/>
											<input type="hidden" id="ends-weekly-radio" 			name="ends-weekly"/>
											<input type="hidden" id="ends-monthly-radio" 			name="ends-monthly"/>
											<input type="hidden" id="ends-yearly-radio" 			name="ends-yearly"/>
											<input type="hidden" id="demand-response-duration"		name="demandResponseDuration"/>
											<input type="hidden" id="demand-response-when"			name="demandResponseWhen"/>
											<input type="hidden" id="demand-response-time"			name="demand_response_time"/>
										</div>
									</fieldset>

									<li class="ui-state-highlight">
										<label for="starts-on" class="title short"><strong><spring:message code="systemintelligence.dialogCreateEvent.summary" /></strong></label>
										<p class="read-only" >
											<strong id="repeats-summary"><spring:message code="commons.pages.weekly" /></strong>
										</p>
									</li>

								</ul>
							</fieldset>
						</div>
					</div>
				</form>

			</div>
			<input type="hidden" id="date-today" name="date-today"/>

			<div id="actionDialogform" class="action-dialog hide">
				<form id="dialogForm"></form>
			</div>
		</div>
	</div>
	<jsp:include page="../../scripts/pages/schedule/schedule_create_main.js.jsp" flush="true"/>
	<jsp:include page="../../scripts/pages/schedule/schedule_create_init.js.jsp" flush="true"/>
	<jsp:include page="../../scripts/pages/schedule/schedule_create_dialog_actions.js.jsp" flush="true"/>
	<jsp:include page="../../scripts/pages/schedule/schedule_create_dialog_init.js.jsp" flush="true"/>

</sec:authorize>
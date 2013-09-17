<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and !hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">

	sensus.pages.scheduleEvent = {

		isSubmit : false,

		bSessionFilter : false,

		sRepeatStartDate: null,

		oFrequency : {
			DAILY 				: null,
			EVERY_WEEKDAY 		: null,
			EVERY_MON_WED_FRI	: null,
			EVERY_TUE_THURS 	: null,
			WEEKLY 				: null,
			MONTHLY 			: null,
			YEARLY 				: null,
			FREQUENCY_TYPE 		: null
		},

		group : {

			//Receives preloaded data
			<c:choose>
				<c:when test="${empty schedule}">
					oPreLoadResponse : null,
				</c:when>
				<c:otherwise>
					oPreLoadResponse : ${schedule},
				</c:otherwise>
			</c:choose>

			/**
			 * Get list groups from the Response Object
			 * @param  - [Object] oResponse object response
			 * @return - [Array] List of groups
			 */
			fnGetListAllGroups: function(oResponse) {
				if (oResponse && oResponse.groups && oResponse.groups.length) {
					return oResponse.groups;
				}

				return null;
			},

			/**
			 * Load Groups and displays on screen
			 * @param  - [String] sId Id of Device
			 * @return - [void]
			 */
			fnLoadGroups : function() {

				var i = 0,
					sTableGroupHtml = '',
					sOptionGroupHtml = '',
					oGroups = $('#groups'),
					oTbodyGroup = $(' .small-table tbody', oGroups);
					oOptionGroup = $('#combo_group'),

					//Receives preloaded data
					<c:choose>
						<c:when test="${empty group}">
							oResponseAllGroup = null,
						</c:when>
						<c:otherwise>
						oResponseAllGroup = ${group},
						</c:otherwise>
					</c:choose>

					aAllGroups = sensus.pages.scheduleEvent.group.fnGetListAllGroups(oResponseAllGroup),
					iAllGroupsSize = 0,
					oBlankslateGroup = $('#blankslate-group'),
					oTableGroup = $(' .small-table',oGroups);

					if (aAllGroups) {
						iAllGroupsSize = aAllGroups.length;
					}

					for ( ; i < iAllGroupsSize; i = i + 1) {

						var sGroupName = aAllGroups[i].name,
							sGroupId = aAllGroups[i].id;

						sOptionGroupHtml +='<option value="'+sGroupId+'">'+sGroupName+'</option>';
					}

					i = 0;
					oOptionGroup.empty().append(sOptionGroupHtml);
					$('#group-list').find('.ui-listcombobox').append(sTableGroupHtml);

			},

			init : function () {

				sensus.pages.scheduleEvent.group.fnLoadGroups();

			}

		},

		tag : {

			/**
			 * Get list tags from the Response Object
			 * @param  - [Object] oResponse object response
			 * @return - [Array] List of all tags
			 */
			fnGetListAllTags: function(oResponse) {

				if (oResponse.tags != undefined || oResponse.tags != null || oResponse.tags != "" ) {
					return oResponse.tags;
				}

				return null;
			},

			/**
			 * Load Tags and displays on screen
			 * @param  - [String] sId Id of Device
			 * @return - [void]
			 */
			fnLoadTags : function() {

				var i = 0,
					sLiTagHtml = '',
					sOptionTagHtml = '',
					oTags = $('#tags'),
					oUlTag = $('#tag-list').find('.ui-listcombobox');
					oOptionTag = $('#combo_tag'),

					//Receives preloaded data
					<c:choose>
						<c:when test="${empty tag}">
						oResponseAllTags = null,
						</c:when>
						<c:otherwise>
						oResponseAllTags = ${tag},
						</c:otherwise>
					</c:choose>


					aAllTags = sensus.pages.scheduleEvent.tag.fnGetListAllTags(oResponseAllTags),
					iTagByMeterSize = 0,
					iAllTagsSize = 0,
					oBlankslateTag = $('#blankslate-tags'),
					oTableTag = $(' .small-table',oTags);

					if (aAllTags) {
						iAllTagsSize = aAllTags.length;
					}

					for ( ; i < iAllTagsSize; i = i + 1) {

						var sTagName = aAllTags[i].name,
							sTagId = aAllTags[i].id;

						sLiTagHtml +='<option value="'+sTagId+'">'+sTagName+'</option>';
					}
					i = 0;
					$('#combo_tag').empty().append(sLiTagHtml);
					oUlTag.empty().append(sOptionTagHtml);

			},

			init : function () {

				sensus.pages.scheduleEvent.tag.fnLoadTags();
			},
		},

		scheduleEdit :{

			//Receives preloaded data
			<c:choose>
				<c:when test="${empty schedule}">
					oPreLoadResponse : null,
				</c:when>
				<c:otherwise>
					oPreLoadResponse : ${schedule},
				</c:otherwise>
			</c:choose>

			fnFetchScheduleById : function() {

				return sensus.pages.scheduleEvent.scheduleEdit.oPreLoadResponse;

			},

			/**
			 * Get list schedules from the Response Object
			 * @param  - [Object] oResponse object response
			 * @return - [Array] List of all schedules
			 */
			fnGetListSchedule: function(oResponse) {

				if (oResponse.schedules != undefined || oResponse.schedules != null || oResponse.schedules != "" ) {
					return oResponse.schedules[0];
				}

				return null;
			},

			fnFillSchedule : function() {

				var oResponse 	= sensus.pages.scheduleEvent.scheduleEdit.fnFetchScheduleById();
				var oSchedule 	= sensus.pages.scheduleEvent.scheduleEdit.fnGetListSchedule(oResponse);
				var oDMAction 	= oSchedule.dmAction;
				var oActionType = oDMAction.actionType;
				var sChecked 	= "checked";

				// Fill the inputs on the jsp page by fetchScheduleById
				if ($.address.parameter("type") === "edit") {
					$("#scheduledEventName, #scheduledEventName-old").val(oSchedule.name);
					$("#scheduledEventId").val(oSchedule.id);
				}

				if (!$.ajaxValidator.fnIsNullOrUndefined(oSchedule.description)) {
					$("#scheduledEventDescription").text(oSchedule.description);
				}

				$("#actionId").val(oActionType.actionTypeEnumValue);
				$("#action-name").val(oActionType.actionTypeEnumNameValue);
				$('#actions-button').data("actionKey", oActionType.actionTypeEnumNameValue);
				$("#action-view-id").val(oDMAction.id);
				$("#actionName").val(sensus.locale.get(oActionType.actionCategoryEnumNameValue));
				$("#scheduledEventWhen").val(oDMAction.actionTime);
				$("#total-devices").text(oDMAction.totalDevices);

				/************
				 * FREQUENCY
				 ************/
				var oScheduleFrequency 		= oSchedule.frequency;
				var sFrequencyTypeEnum 		= oScheduleFrequency.frequencyTypeEnum;
				var scheduleEventNamespace 	= sensus.pages.scheduleEvent;

				// If frequency type is different of Never fill the oFrequency object
				if (sFrequencyTypeEnum != "NEVER") {

					var oFrequency = scheduleEventNamespace.oFrequency;

					oFrequency[sFrequencyTypeEnum] = null;

					oFrequency[sFrequencyTypeEnum] = {
							ends : {}
					};

					// Common function to fill the some fields of the frequency object
					var fnFillFrequencyObject = function(sType) {

						if (oScheduleFrequency.startOnDate) {
							oFrequency[sType].starts = oScheduleFrequency.nextExecution;
						}

						if (oScheduleFrequency.timeToRepeat) {
							oFrequency[sType].everyDay = oScheduleFrequency.timeToRepeat;
						}

						if (oScheduleFrequency.neverEnds) {
							oFrequency[sType].ends.never = oScheduleFrequency.neverEnds;
						} else {

							if (oScheduleFrequency.endsAfterOccurrences) {
								oFrequency[sType].ends.afterOccurrences = oScheduleFrequency.endsAfterOccurrences;
							} else if (oScheduleFrequency.endDate) {
								oFrequency[sType].ends.onDate = oScheduleFrequency.endDate;
							}

						}

					};

					// Fill the some fields of the frequency object (commons fields)
					fnFillFrequencyObject(sFrequencyTypeEnum);

					if (sFrequencyTypeEnum == "WEEKLY") {
						oFrequency[sFrequencyTypeEnum].daysOfWeeks = oScheduleFrequency.daysOfWeeks;
					} else if (sFrequencyTypeEnum == "MONTHLY") {
						oFrequency[sFrequencyTypeEnum].dayOfWeek = oScheduleFrequency.dayOfWeek;
					}

					oFrequency.FREQUENCY_TYPE = sFrequencyTypeEnum;
					sensus.pages.scheduleEvent.oFrequency = oFrequency;

					// Fill the frequency Dialog with frequency object
					scheduleEventNamespace.fnLoadFrequency(oFrequency);
				}

				/************
				 * HAN TEXT MESSAGE
				 ************/
				if (oActionType.actionTypeEnumNameValue === "sensus.dm.action.send.han.text.message") {

					var oTextMessageHan = $("#textMessageHan");

					oTextMessageHan.val(oDMAction.textMessage);
					oTextMessageHan.text(oDMAction.textMessage);
					$("#text_message_duration").val(oDMAction.durationHANTextMessage);

					$("#preProgramDate").val(oSchedule.startTime);

					if (oDMAction.actionTime == new Date().getTime())
					{
						$("#sendNow").prop(sChecked, true);
					} else {
						$("#sendLater").prop(sChecked, true);
					}
				}

				/************
				 * DEMAND RESPONSE
				 ************/
				if (oActionType.actionTypeEnumNameValue === "sensus.dm.action.demand.response") {

					var iProperties 	 = oSchedule.properties.length;
					var oProperties 	 = oSchedule.properties;
					var oProperty 		 = [];
					var sTemperatureType = sensus.settings.temperatureType;
					var oPctHeating 	 = $("#pctHeating");
					var oPctCooling 	 = $("#pctCooling");
					var sPropertyName 	 = "";
					var sTrue 			 = "true";

					var fnCheckTrue = function(value, $element) {

						if (value === sTrue) {
							$element.prop(sChecked, true);
						}

					};

					for (var i = 0; i < iProperties; i++) {

						oProperty = oProperties[i];
						sPropertyName = oProperty.propertyName;

						if (sPropertyName === "DEMAND_RESPONSE_DURATION") {

							$("#duration").val(oProperty.propertyValue);

						} else if (sPropertyName === "DEMAND_RESPONSE_ENROLLMENT_CODE") {

							$("#enrollment-code").val(oProperty.propertyValue);

						} else if (sPropertyName === "DEMAND_RESPONSE_HEATING") {

							if (sTemperatureType.toLowerCase() == sensus.locale.get("systemsettings.page.temperature.fahrenheit").toLowerCase()) {

								oPctHeating.val($.temperature.convertTemperature(oProperty.propertyValue, "sf"));

							} else {
								oPctHeating.val(oProperty.propertyValue);
							}

						} else if (sPropertyName === "DEMAND_RESPONSE_COOLING") {

							// If system temperature is fahrenheit convert the scale in celsius to scale in fahrenheit
							if (sTemperatureType.toLowerCase() == sensus.locale.get("systemsettings.page.temperature.fahrenheit").toLowerCase()) {

								oPctCooling.val($.temperature.convertTemperature(oProperty.propertyValue, "sf"));

							} else {
								oPctCooling.val(oProperty.propertyValue);
							}

						} else if (sPropertyName === "DEMAND_RESPONSE_DUTY_CYCLE_RATE") {

							$("#duty-cycle").val(oProperty.propertyValue);

						} else if (sPropertyName === "DEMAND_RESPONSE_LOAD_ADJUSTMENT") {

							$("#load-adjustment").val(oProperty.propertyValue);

						} else if (sPropertyName === "DEMAND_RESPONSE_CRITICALITY_LEVEL") {

							$('#hanCriticality option[value="'+ oProperty.propertyValue +'"]').attr("selected","selected");

						} else if (sPropertyName === "DEMAND_RESPONSE_RANDOMIZE_START") {

							fnCheckTrue(oProperty.propertyValue, $("#randomizeStart"));

						} else if (sPropertyName === "DEMAND_RESPONSE_RANDOMIZE_END") {

							fnCheckTrue(oProperty.propertyValue, $("#randomizeEnd"));

						} else if (sPropertyName === "HVAC_COMPRESSOR") {

							fnCheckTrue(oProperty.propertyValue, $("#device_class_hvac"));

						} else if (sPropertyName === "STRIP_HEATER") {

							fnCheckTrue(oProperty.propertyValue, $("#device_class_heater"));

						} else if (sPropertyName === "WATER_HEATER") {

							fnCheckTrue(oProperty.propertyValue, $("#device_class_water_heater"));

						} else if (sPropertyName === "POOL_PUMP") {

							fnCheckTrue(oProperty.propertyValue, $("#device_class_pool_pump"));

						} else if (sPropertyName === "SMART_APPLIANCES") {

							fnCheckTrue(oProperty.propertyValue, $("#device_class_smart_appliances"));

						} else if (sPropertyName === "IRRIGATION_PUMP") {

							fnCheckTrue(oProperty.propertyValue, $("#device_class_irrigation"));

						} else if (sPropertyName === "MANAGED_COMMERCIAL") {

							fnCheckTrue(oProperty.propertyValue, $("#device_class_cni"));

						} else if (sPropertyName === "SIMPLE_MISC") {

							fnCheckTrue(oProperty.propertyValue, $("#device_class_simple"));

						} else if (sPropertyName === "EXTERIOR_LIGHTING") {

							fnCheckTrue(oProperty.propertyValue, $("#device_class_ext_lighting"));

						} else if (sPropertyName === "INTERIOR_LIGHTING") {

							fnCheckTrue(oProperty.propertyValue, $("#device_class_int_lighting"));

						} else if (sPropertyName === "ELECTRIC_VEHICLE") {

							fnCheckTrue(oProperty.propertyValue, $("#device_class_vehicle"));

						} else if (sPropertyName === "GENERATION_SYSTEMS") {

							fnCheckTrue(oProperty.propertyValue, $("#device_class_gen"));

						}
					}

					$("#pre_program_date").val(oSchedule.startTime);

					if (oDMAction.actionTime == new Date().getTime()) {
						$("#send_now").prop(sChecked, true);
					} else {
						$("#send_later").prop(sChecked, true);
					}
				}

				/************
				 * GROUPS
				 ************/
				if (oDMAction.groups.length > 0) {

					var sListGroups = "";

					for (var group in oDMAction.groups) {

						var iGroupId = oDMAction.groups[group].id;

						sListGroups = sListGroups + iGroupId + "|";

					}
					$("#listGroups").val(sListGroups);
				}

				/************
				 * TAGS
				 ************/
				if (oDMAction.tags.length > 0) {

					var sListTags = "";

					for (var tag in oDMAction.tags) {

						var iTagId = oDMAction.tags[tag].id;

						sListTags = sListTags + iTagId + "|";

					}
					$("#listTags").val(sListTags);
				}

				/************
				 * UPLOAD LIST
				 ************/
				if (oDMAction.devices.length > 0) {

					$("#upload-type-flexnet").prop(sChecked, true);

					var iTotalDevices 	= oDMAction.devices.length;
					var sListDevices 	= "";

					for (var schedule in oDMAction.devices) {

						if (schedule < iTotalDevices - 1) {
							sListDevices = sListDevices + oDMAction.devices[schedule].radio.flexNetId + ",";
						} else {
							sListDevices = sListDevices + oDMAction.devices[schedule].radio.flexNetId
						}
					}

					$("#listSmartPoint").text(sListDevices);
				}

				/************
				 * MONITOR EVENT
				 ************/
				if (oDMAction.isMonitored == true) {

					var oRecentRequestMonitor = $("#recentRequestMonitor");

					oRecentRequestMonitor.prop(sChecked, true);
					oRecentRequestMonitor.val(true);

				}
			},

			init : function() {
				sensus.pages.scheduleEvent.scheduleEdit.fnFillSchedule();
			}
		},

		fnChangeSummary : function(oSelect) {

			var oRepeatSummary = $("#repeats-summary");
			var oRepeatsSelect = $("#repeats");
			var sType = "";

			if (oSelect.attr("id") == "frequencyEnum") {

				sType = oSelect.val();

			} else {

				sType = $("option:selected", oSelect).text();

			}

			switch(sType) {
				case sensus.locale.get("commons.pages.daily"):
					{
					oSelect.val("DAILY")
					oRepeatSummary.text(sensus.locale.get('systemintelligence.scheduledEvent.summary.daily'));
					}
				break;
				case sensus.locale.get("systemintelligence.dialogCreateEvent.weekday"):
					{
					oRepeatsSelect.val("EVERY_WEEKDAY")
					oRepeatSummary.text(sensus.locale.get('systemintelligence.scheduledEvent.summary.everyWeekday'));
					}
				break;
				case sensus.locale.get("systemintelligence.dialogCreateEvent.mon"):
					{
					oRepeatsSelect.val("EVERY_MON_WED_FRI")
					oRepeatSummary.text(sensus.locale.get('systemintelligence.scheduledEvent.summary.everyMonWedFri'));
				}
				break;
				case sensus.locale.get("systemintelligence.dialogCreateEvent.tues"):
					{
					oRepeatsSelect.val("EVERY_TUE_THURS")
					oRepeatSummary.text(sensus.locale.get('systemintelligence.scheduledEvent.summary.everyTueThurs'));
				}
				break;
				case sensus.locale.get("commons.pages.weekly"):
					{
					oRepeatsSelect.val("WEEKLY")
					oRepeatSummary.text(sensus.locale.get('systemintelligence.scheduledEvent.summary.weekly'));
				}
				break;
				case sensus.locale.get("commons.pages.monthly"):
					{
					oRepeatsSelect.val("MONTHLY")
					oRepeatSummary.text(sensus.locale.get('systemintelligence.shceduledEvent.summary.monthly'));
					}
				break;
				case sensus.locale.get("commons.pages.yearly"):
					{
					oRepeatsSelect.val("YEARLY")
					oRepeatSummary.text(sensus.locale.get('systemintelligence.scheduledEvent.summary.yearly'));
				}
				break;
				case sensus.locale.get("commons.pages.custom"):
					{
					oRepeatsSelect.val("CUSTOM")
					oRepeatSummary.text(sensus.locale.get('systemintelligence.scheduledEvent.summary.custom'));
					}
				break;
			}
		},

		fnChangeRepeatType : function(oSelect) {

			var sIdOption 	= $("option:selected", oSelect).attr("id");
			var $repeatForm = $(".repeat-form");

			$repeatForm.hide();

			$repeatForm.each(function() {

				$(this).removeClass("enable");

			});

			$("#repeats-form #" + sIdOption.toLowerCase() +"-form").show().addClass("enable");

			$('#repeats-form input[type=text]:visible').click(function() {
				$(this).prev().prev().attr("checked", true);
			});

			$('#repeats-form :radio:visible').click(function() {
				$('.formError').remove();
			});

			var dateFormat 		 = sensus.settings.dateFormatMask.replace('yyyy','yy');
			var sClassTypeFormat = 'datePt';
			var sFormatDate 	 = 'yy-mm-dd';
			var $date 			 = $('#starts-on input','.enable');
			var $end 			 = $('#ends-on .hasDatepicker','.enable');
			var $repeatsEvery 	 = $('#repeats-every input','.enable');

			if (dateFormat == 'mm/dd/yy') {
				sClassTypeFormat = 'dateEn';
			}

			$date.removeClass();

			var dEnd = $.datepicker.formatDate(sFormatDate, new Date($date.val()));

			if ($date.val().length == 0) {
				dEnd = $.datepicker.formatDate(sFormatDate, new Date($("#scheduledEventWhen").val()));
			}

			$date.addClass("short datepicker validate[required, custom["+sClassTypeFormat+"], future["+dEnd+"]]");

			$end.removeClass();

			$end.datepicker("destroy");

			if ($('#starts-on .hasDatepicker','.enable').length) {

				var dStart = $.datepicker.formatDate(sFormatDate, $.datepicker.parseDate(dateFormat, $('#starts-on .datepicker','.enable').val()));
				$end.addClass("short datepicker validate[required, custom["+sClassTypeFormat+"], future["+dStart+"]]");

			}

			$end.datepicker({
				dateFormat: dateFormat,
				minDate: "-0D"
			});

			if ($repeatsEvery.length) {

				if ($repeatsEvery.val().length == 0) {
					$repeatsEvery.val('1');
				}

			}
		},

		fnLoadRepeatDialog : function() {

			var oRepeatsSelect 	= $("#repeats");
			var oRepeatSummary 	= $("#repeats-summary");
			var dateFormat 		= sensus.settings.dateFormatMask;
			var formatter 		= dateFormat.replace('yyyy','yy');

			$(".datepicker").datepicker({minDate: '-0d', dateFormat: formatter, onSelect: function() { $(".ui-datepicker a").removeAttr("href"); }});
			$('.datepicker-day').datepicker({ dateFormat: 'dd'});
			$('.event-day-custom').buttonset();

			sensus.pages.scheduleEvent.fnChangeRepeatType(oRepeatsSelect);
			sensus.pages.scheduleEvent.fnChangeSummary(oRepeatsSelect);

			oRepeatsSelect.change(function() {

				sensus.pages.scheduleEvent.fnChangeRepeatType(oRepeatsSelect);
				sensus.pages.scheduleEvent.fnChangeSummary(oRepeatsSelect);

			});
		},

		/*
		 * Function: getDeviceCountByGroup
		 * Purpose:  Get Count of Devices in a Group
		 * Returns:  integer
		 * Inputs:   -
		 */
		getDeviceCountByGroup: function(iId) {

			var iSize = 0;

			var fnCallBack = function(response) {
				iSize = response.devices.length;
			};

			if (iId.length > 0) {

				$.ajaxValidator.fnDoCall("api/electricdevice/fetch", {groupIds : iId}, false, fnCallBack);

			}

			return iSize;
		},

		/*
		 * Function: isDayBefore
		 * Purpose:  Check if is day before today
		 * Returns:  boolean
		 * Inputs:   string - dateText
		 */
		isDayBefore : function(dateText) {

			var isToday 	= false;
			var yesterday 	= new Date();
			var mask      	= sensus.settings.dateFormatMask.replace('yyyy','yy');
			var oLabel		= $('<label class="error" for="" id="scheduledEventWhen-label">' + sensus.locale.get('tag.page.error.invalidDate') + '</label>');

			var showMessageError = function() {

				if (!$("#scheduledEventWhen-label").length) {

					$('#scheduledEventWhen').validationEngine('showPrompt', sensus.locale.get('tag.page.error.invalidDate'), 'red', '', true);

				}
			};

			var hideMessageError = function() {

				$("#scheduledEventWhen").css({'border' : '1px solid #AAAAAA'});

				if ($("#scheduledEventWhen-label").length) {

					$("#scheduledEventWhen-label").remove();

				}

			};

			isToday =  $.datepicker.formatDate(mask, new Date()) == $("#scheduledEventWhen").val();

			if (isToday && $("#scheduleEventTime").val().length) {

				return sensus.pages.scheduleEvent.isHourAndMinuteBefore();

			}

			yesterday.setDate( yesterday.getDate()-1 );

			if (new Date(dateText) < yesterday) {

				showMessageError();
				return true;

			} else {

				hideMessageError();
				return false;
			}
		},

		/**
		 * Function to load Frequency Dialog
		 *
		 * @Param Object
		 * 			oFrequency - {DAILY : { starts : "10/10/2010", everyDay : 10, ends : {never : true, afterOcurrences : null, onDate : null}}}
		 */
		fnLoadFrequency : function(oFrequency) {

			var sFrequencyTypeEnum  = oFrequency.FREQUENCY_TYPE;
			var object			    = oFrequency[sFrequencyTypeEnum];
			var $hasRepeat 			= $("#has-repeat");
			var $repeatTypeSelect 	= $("#repeat-type-select");
			var $endRepeat			= $('#ends-on');
			var $never 				= $("#never");

			if (sFrequencyTypeEnum !== "NEVER") {

				var fnFillCommonsFields = function($starOn, $repetsEvery, $endsNever, $endsAfter, $endsOn, $endsRadio) {

					var sAfter 			  = "after";
					var sChecked 		  = "checked";
					var sNever 			  = "never";
					var sOn 			  = "on";
					var iEveryDay 		  = object.everyDay;
					var sAfterOccurrences = object.ends.afterOccurrences;
					var sOnDate 		  = object.ends.onDate;
					var sStarts 		  = object.starts;

					if (sStarts) {

						var sStartsFormated = $.date.dateFormat(sStarts);

						if (sStartsFormated == "") {
							sStartsFormated = sStarts;
						}

						$starOn.val(sStartsFormated);
					}

					if (iEveryDay) {
						$repetsEvery.val(iEveryDay);
					}

					if (object.ends.never) {
						$endsNever.val(sNever);
						$endsRadio.val(sNever);
						$endRepeat.find('#' + sNever).prop(sChecked, sChecked);
					}

					if (sAfterOccurrences) {
						$endsAfter.val(sAfterOccurrences);
						$endsRadio.val(sAfter);
						$endRepeat.find('#' + sAfter).prop(sChecked, sChecked);
					}

					if (sOnDate) {

						var sDateFormated = $.date.dateFormat(sOnDate);

						if (sDateFormated == "") {
							sDateFormated = sOnDate;
						}

						$endsOn.val(sDateFormated);
						$endsRadio.val(sOn);
						$endRepeat.find('#' + sOn).prop(sChecked, sChecked);
					}
				};

				$hasRepeat.val(true);
				$repeatTypeSelect.val(sFrequencyTypeEnum);

			} else {

				$hasRepeat.val(false);
				$repeatTypeSelect.val("");

			}

			// DAILY
			if (sFrequencyTypeEnum === "DAILY") {
				fnFillCommonsFields($("#repeatStartDateDaily"), $("#repeats-every-value"), $never, $("#occurrences"), $("#date-after-daily"), $("#ends-daily-radio"));
			}

			// EVERY_WEEKDAY
			else if (sFrequencyTypeEnum === "EVERY_WEEKDAY") {
				fnFillCommonsFields($("#repeat-start-date-weekday"), "", $never, $("#occurrences-weekday"), $("#date-after-dateWeekDay"), $("#ends-weekday-radio"));
			}

			// EVERY_MON_WED_FRI
			else if (sFrequencyTypeEnum === "EVERY_MON_WED_FRI") {
				fnFillCommonsFields($("#repeat-start-date-every-other"), "", $never, $("#occurrences-every-other"), $("#date-after-dateEveryOther"), $("#ends-every-other-radio"));
			}

			// EVERY_TUE_THURS
			else if (sFrequencyTypeEnum === "EVERY_TUE_THURS") {
				fnFillCommonsFields($("#repeat-start-date-every-t"), "", $never, $("#occurrences-every-t"), $("#date-after-dateEvery"), $("#ends-every-t-radio"));
			}

			// "WEEKLY"
			else if (sFrequencyTypeEnum === "WEEKLY") {

				fnFillCommonsFields($("#repeat-start-date-weekly"), $("#repeats-every-value-weekly"), $never, $("#occurrences-weekly"), $("#date-after-EveryValueWeekly"), $("#ends-weekly-radio"));

				if (object.daysOfWeeks) {

					var sListDays 	= "";
					var sDay 		= "";

					for (var day in object.daysOfWeeks) {

						if (object.daysOfWeeks[day] === "MONDAY") {
							sDay = "2";
						} else if (object.daysOfWeeks[day] === "TUESDAY") {
							sDay = "3";
						} else if (object.daysOfWeeks[day] === "WEDNESDAY") {
							sDay = "4";
						} else if (object.daysOfWeeks[day] === "THURSDAY") {
							sDay = "5";
						} else if (object.daysOfWeeks[day] === "FRIDAY") {
							sDay = "6";
						} else if (object.daysOfWeeks[day] === "SATURDAY") {
							sDay = "7";
						} else if (object.daysOfWeeks[day] === "SUNDAY") {
							sDay = "1";
						}

						sListDays += sDay + ",";
					}

					$("#list-of-days-selected").val(sListDays);
				}
			}

			// "MONTHLY"
			else if (sFrequencyTypeEnum === "MONTHLY") {

				fnFillCommonsFields($("#repeat-start-date-monthly"), $("#repeats-every-value-monthly"), $never, $("#occurrences-repeats-monthly"), $("#date-after-EveryValueMonthly"), $("#ends-monthly-radio"));

				var oRepeatByMonthly = $("#repeat-by-monthly");

				// Repeats By
				if (object.dayOfWeek === true) {
					oRepeatByMonthly.val("day-week");
				} else {
					oRepeatByMonthly.val("date");
				}
			}

			// "YEARLY"
			else if (sFrequencyTypeEnum === "YEARLY") {
				fnFillCommonsFields($("#repeat-start-date-yearly"), $("#repeats-every-value-yearly"), $never, $("#occurrences-yearly"), $("#date-after-EveryValueYearly"), $("#ends-yearly-radio"));
			}
		}
	};
	</script>

</sec:authorize>
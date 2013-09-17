<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
	$(document).ready(function() {

		if ($("div.ui-dialog #actionDialogRepeatDefine").length) {

			$("div.ui-dialog #actionDialogRepeatDefine").dialog('destroy').parent().remove();
		}

		if ($(".tabs").length) {
			sensus.util.page.initMessagingTabs();
		} else {
			sensus.util.page.initMessaging();
		}

		$("#tabs").tabs();

		// fix the classes
		$(".tabs-bottom .ui-tabs-nav, .tabs-bottom .ui-tabs-nav > *").removeClass( "ui-corner-all ui-corner-top" ).addClass( "ui-corner-bottom" );
		$( ".tabs-bottom .ui-tabs-nav" ).removeClass( "ui-corner-bottom" )

		// move the nav to the bottom
		$(".tabs-bottom .ui-tabs-nav").appendTo(".tabs-bottom");

		$('.combobox').combobox();

		$('#actions-button').text(sensus.locale.get("systemintelligence.create.action.selectAction")).attr('tabindex','3');

		$('.button').button();

		$("#createEventForm").validationEngine('attach', {validationEventTrigger: 'submit', scroll: false});

		$('.han-text-message').hide();

		$('.demand-response-fields_hide').hide();

		$('.timezone').text(sensus.locale.get('smartpointdetail.tabs.about.timeZone') + ' (' + sensus.settings.timezone + ') ');

		$('.ui-icon-list-ipt-check').addClass('ui-button-custom');

		sensus.pages.scheduleEvent.group.init();

		sensus.pages.scheduleEvent.tag.init();

		if (($.address.parameter('type') === 'edit') || ($.address.parameter('type') === 'clone')) {

			if ($.address.parameter('type') === 'edit') {
				$('#title-scheduled').text(sensus.locale.get('systemintelligence.scheduledCreateEventEdit'));
			}

			if ($.address.parameter('id')) {
				sensus.pages.scheduleEvent.scheduleEdit.init();
			}

		}

		// Get DATE of the JSP and convert to default format
		var fnDateTime = function(dateToday, oDate, oTime) {

			if (oDate.val() == "") {

				oDate.val($.date.dateFormat(dateToday));
				oTime.val($.date.dateFormat(dateToday, "h:ia"));

			} else {

				var sDateWhen = oDate.val();

				oDate.val($.date.dateFormat(sDateWhen));
				oTime.val($.date.dateFormat(sDateWhen, "h:ia"));

			}

		};

		var dateToday			= $.date.setDateServer(true);
		var oScheduledEventWhen = $("#scheduledEventWhen");
		var oScheduledEventTime = $("#scheduleEventTime");
		var oDemandResponseWhen = $("#pre_program_date");
		var oDemandResponseTime = $('#timeHan');
		var oHanTextWhen 		= $("#preProgramDate");
		var oHanTextTime 		= $("#preProgramTime");

		$("#date-today").val(dateToday.getTime());

		//Fill datetime field of Scheduled Event When
		fnDateTime(dateToday, oScheduledEventWhen, oScheduledEventTime);

		//Fill datetime field of Demand Response when
		fnDateTime(dateToday, oDemandResponseWhen, oDemandResponseTime);

		//Fill datetime field of Han Text when
		fnDateTime(dateToday, oHanTextWhen, oHanTextTime);

		// field with id scheduleEventTime
		$('.time').calendricalTime();

		var fnEnableDisableGroup = function(oObject) {

			if (oObject.val().length) {

				$('#combo_group_input, #GroupsDiv .yui-u button').hide();
				$('input[name=groupList]').attr("disabled", true);
				$("#upload").attr('disabled', true);

			} else {

				if ($('#combo_group option').length) {

					$('#combo_group_input, #GroupsDiv .yui-u button').show();

				}

				$('input[name=groupList]').attr("disabled", false);
				$("#upload").attr('disabled', false);
			}
		}

		var fnEnableDisableTag = function(oObject) {

			if (oObject.val().length) {

				$('#combo_tag_input, #TagsDiv .yui-u button').hide();
				$('input[name=tagList]').attr("disabled", true);
				$("#upload").attr('disabled', true);

			} else {

				if ($('#combo_tag option').length) {
					$('#combo_tag_input, #TagsDiv .yui-u button').show();
				}

				$('input[name=tagList]').attr("disabled", false);
				$("#upload").attr('disabled', false);
			}
		}

		$('#listSmartPoint').bind({

			  click: function() {
				fnEnableDisableGroup($(this));
				fnEnableDisableTag($(this));
			  },

			  blur: function() {
				fnEnableDisableGroup($(this));
				fnEnableDisableTag($(this));
			  }
		});

		// Check if request was well
		if ($.address.parameter('check')) {

			if ($.address.parameter('check') === 'error') {

				sensus.util.page.stopProgressBar();
				sensus.util.page.showMessage("messaging-main", sensus.locale.get("systemintelligence.ScheduledEvent.error"), "error");

			}

			$.fn.pageLoader.parameter("check", "");

		}

		// List Check Groups
		$("#combo_group_input").focus(function(event, ui) {

			$(this).val('');
			var that = $(this);

			$(this).autocomplete({

				close : function(event, ui) {

					var sMatcher 	= new RegExp( "^" + $.ui.autocomplete.escapeRegex( $(this).val() ) + "$", "i" );
					var bValid 		= false;

				  	sSelect = $(that).siblings('.combobox');
					iSelectSize = ((sSelect.children("option").size())-1);

					sSelect.children( "option" ).each(function() {

						if ($(this).text().match(sMatcher)) {

							this.selected = bValid = true;

				          	$(this).parent()
				          			.siblings('#group-list')
				          			.find('ul')
				          			.append('<li class="checkbox"><input type="checkbox" name="groupList" checked="checked" value="' + $(this).val() + '"> ' + $(this ).text() + '</li>');

				          	$(this).parent().next().val(iSelectSize + " " + sensus.locale.get("systemintelligence.scheduledCreateEvent.more"));

							if (iSelectSize == 0) {
								$(this).parent().next().next().remove();
								$(this).parent().next().remove();
							}

							fnCountSmartpoint($(this), true, 'group');

							$("#upload, #listSmartPoint").attr('disabled', true);

							$(this).remove();
				          	return false;
						}
					});
				}
			});
		});

		var listGroups 	= $('#listGroups');
		var aGroup 		= [];
		var	aGroups 	= [];

		if (listGroups.length && listGroups.val().trim() != "") {

			aGroup = listGroups.val().trim().split("|");

			for (i in aGroup) {

				if (aGroup.hasOwnProperty(i)) {
					aGroups.push(aGroup[i]);
				}
			}
		}

		// Show or Hide 'Select' with groups
		if ($('#combo_group option').size() < 1) {

			$('#combo_group, #combo_group_input, #GroupsDiv .ui-icon-list-ipt-check').remove();
			$('#GroupsDiv').empty();

			$('<div class="blankslate hide schedule-event-blankslate"><h5>'+sensus.locale.get("commons.pages.blankGroups.eventCreate")+'</h5><p>'+sensus.locale.get("commons.pages.blankGroups.eventCreate2")+'</p><u><a class="alist" href="/group/create">'+sensus.locale.get("commons.pages.blankGroups.eventCreateLink")+'</a></u></div>').appendTo('#GroupsDiv');
			$('.blankslate').show();

		} else {

			// add checkbox groups list groups selected
			$('#combo_group option').each(function(i) {

				if (aGroups.length > 0) {

					for (var iGroup in aGroups) {

						if (aGroups.hasOwnProperty(iGroup)) {

							if (parseInt(aGroups[iGroup], 10) === parseInt($(this).val(),10) ) {

								$('<li class="checkbox"><input type="checkbox" checked="checked" name="groupList" value="' + $(this).val() + '"> ' + $(this).text() + '</li>').appendTo('#group-list ul');
								$(this).remove();

							}
						}
					}

				} else if (i < 6) {

					$('<li class="checkbox"><input type="checkbox" name="groupList" value="' + $(this).val() + '"> ' + $(this).text() + '</li>').appendTo('#group-list ul');
					$(this).remove();

				}
			});

			var lengthOptions = $("#combo_group option").length;

			if (lengthOptions) {

				$('#combo_group_input').val(lengthOptions + " " + sensus.locale.get("systemintelligence.scheduledCreateEvent.more"))

			} else {

				//hide select
				$('#combo_group_input, #GroupsDiv .yui-u button').hide();

			}
		}

		// List Check Tags
		$("#combo_tag_input").focus(function(event, ui) {

			$(this).val('');

			var that = $(this);

			$(this).autocomplete({

				close : function(event, ui) {

					var sMatcher = new RegExp( "^" + $.ui.autocomplete.escapeRegex( $(this).val() ) + "$", "i" );
					var bValid = false;

				  	sSelect = $(that).siblings('.combobox');
					iSelectSize = ((sSelect.children("option").size())-1);

					sSelect.children( "option" ).each(function() {

						if ( $( this ).text().match( sMatcher ) ) {

							this.selected = bValid = true;

				          	$(this).parent()
				          			.siblings('#tag-list')
				          			.find('ul')
				          			.append('<li class="checkbox"><input type="checkbox" name="tagList" checked="checked" value="' + $(this).val() + '"> ' + $(this ).text() + '</li>');

				          	$(this).parent().next().val(iSelectSize + " " + sensus.locale.get("systemintelligence.scheduledCreateEvent.more"));

							if (iSelectSize == 0) {
								$(this).parent().next().next().remove();
								$(this).parent().next().remove();
							}

							fnCountSmartpoint($(this), true, 'tag');

							$("#upload, #listSmartPoint").attr('disabled', true);

							$(this).remove();
				          	return false;
						}
					});
				}
			});
		});

		var listTags = $('#listTags');
		var aTag 	 = [];
		var	aTags 	 = [];

		if (listTags.length && listTags.val().trim() != "") {

			aTag = listTags.val().trim().split("|");

			for (i in aTag) {

				if (aTag.hasOwnProperty(i)) {
					aTags.push(aTag[i]);
				}
			}
		}

		// Show or Hide 'Select' with tags
		if ($('#combo_tag option').size() < 1) {

			$('#combo_tag, #combo_tag_input, #TagsDiv .ui-icon-list-ipt-check').remove();
			$('#TagsDiv').empty();

			$('<div class="blankslate hide schedule-event-blankslate"><h5>'+sensus.locale.get("commons.pages.blankTags.eventCreate")+'</h5><p>'+sensus.locale.get("commons.pages.blankTags.eventCreate2")+'</p><u><a class="alist" href="/systemsettings">'+sensus.locale.get("commons.pages.blankTags.eventCreateLink")+'</a></u></div>').appendTo('#TagsDiv');
			$('.blankslate').show();

		} else {

			// add checkbox tags list tags selected
			$('#combo_tag option').each(function(i) {

				if (aTags.length > 0) {

					for (var iTag in aTags) {

						if (aTags.hasOwnProperty(iTag)) {

							if (parseInt(aTags[iTag], 10) === parseInt($(this).val(),10) ) {

								$('<li class="checkbox"><input type="checkbox" checked="checked" name="tagList" value="' + $(this).val() + '"> ' + $(this).text() + '</li>').appendTo('#tag-list ul');
								$(this).remove();

							}
						}
					}

				} else if (i < 6) {

					$('<li class="checkbox"><input type="checkbox" name="tagList" value="' + $(this).val() + '"> ' + $(this).text() + '</li>').appendTo('#tag-list ul');
					$(this).remove();

				}
			});

			var lengthOptions = $("#combo_tag option").length;

			if (lengthOptions) {

				$('#combo_tag_input').val(lengthOptions + " " + sensus.locale.get("systemintelligence.scheduledCreateEvent.more"))

			} else {

				//hide select
				$('#combo_tag_input, #TagsDiv .yui-u button').hide();

			}
		}

		var dateFormat 			= sensus.settings.dateFormatMask.replace('yyyy','yy');
		var sClassTypeFormat 	= 'datePt';

		if (dateFormat == 'mm/dd/yy') {
			sClassTypeFormat = 'dateEn';
		}

		oScheduledEventWhen.addClass("validate[required, custom["+sClassTypeFormat+"]]");

		var oFormError = $('.formError');

		oScheduledEventWhen.datepicker({

			dateFormat: dateFormat,
			minDate: "-0D",

			onSelect: function() {
				oFormError.remove();
			},

			// When is changed the date, change the date into the frequency dialog too.
			onClose: function() {
				$('#starts-on input','.enable').val($(this).val());
			}
		});

		$('#hanMessageDate').datepicker({

			dateFormat: dateFormat,
			minDate: "-0D",
			onSelect: function() {
				oFormError.remove();
			}

		});

		$('#preProgramDate').datepicker({

			dateFormat: dateFormat,
			minDate: "-0D",
			onSelect: function() {
				oFormError.remove();
			}

		});

		$('#scheduleEventTime, #scheduledEventWhen, #actions-button, #select-groups, #Meters, #select-tags, #import-devices').click(function() {
			$(".formError").remove();
		});

		var oFieldActionId = $("#actionId");

		$(".row-pad").delegate('#createAction', 'click', function(e) {

			e.preventDefault();

			sensus.util.page.startProgressBar();

			var bDoIt 				= false;
			var dateFormat 			= sensus.settings.dateFormatMask.replace('yyyy','yy');
			var sClassTypeFormat 	= 'datePt';
			var sCurrentDate		= $('#date-today').val();

			if (dateFormat == 'mm/dd/yy') {
				sClassTypeFormat = 'dateEn';
			}

			var fnCallback = function(bMonitored) {

				$('#scheduleEventTime').removeClass('time');

				$('.calendricalTimePopup').hide();

				$("#b-monitored").val(bMonitored);

				var sScheduleName = $('#scheduledEventName').val().trim();

				if ($.address.parameter('type') == 'edit'
						&& sScheduleName == $('#scheduledEventName-old').val().trim()) {

					if (!$('#repeatCheckBox').prop("checked")) {

						$('#createEventForm').submit();

					} else {

						var dEventWhen 	= new Date($('#scheduledEventWhen').val());
						var dStartsOn 	= new Date($('#starts-on input','.enable').val());

						dEventWhen = new Date($.datepicker.formatDate(sensus.settings.dateFormatMask.replace('yyyy', 'yy'), dEventWhen));
						dStartsOn = new Date($.datepicker.formatDate(sensus.settings.dateFormatMask.replace('yyyy', 'yy'), dStartsOn));

						if (dEventWhen.getTime() <= dStartsOn.getTime()) {
							$('#createEventForm').submit();
						} else {
							sensus.util.page.showMessage("messaging-main",sensus.locale.get('systemintelligence.scheduledCreateEvent.RepeatStartTime'), "error");
							sensus.util.page.stopProgressBar();
						}
					}

				} else {

					sensus.util.page.startProgressBar();

					var fnCallbackFetchSchedule = function (oResponse) {

						if (oResponse.operationSuccess) {

							if (oResponse.schedules.length) {

								sensus.util.page.showMessage("messaging-main", sensus.locale.get('systemintelligence.scheduledCreateEvent.alreadyExists'), "error");
								sensus.util.page.stopProgressBar();

							} else if (!$('#repeatCheckBox').prop("checked")) {

								sensus.util.session.remove(["SelectedFilters"]);
								$('#createEventForm').submit();

							} else {

								var dEventWhen = new Date($('#scheduledEventWhen').val());
								dEventWhen = new Date($.datepicker.formatDate(sensus.settings.dateFormatMask.replace('yyyy', 'yy'), dEventWhen));

								var dStartsOn = new Date($('#starts-on input','.enable').val());
								dStartsOn = new Date($.datepicker.formatDate(sensus.settings.dateFormatMask.replace('yyyy', 'yy'), dStartsOn));

								if (dEventWhen.getTime() <= dStartsOn.getTime()) {
									$('#createEventForm').submit();
								} else {
									sensus.util.page.showMessage("messaging-main",sensus.locale.get('systemintelligence.scheduledCreateEvent.RepeatStartTime'), "error");
									sensus.util.page.stopProgressBar();
								}

								return;
							}

						} else {
							sensus.util.page.showMessage("messaging-main", oResponse.messages[0], "error");
						}
					}

					$.ajaxValidator.fnDoCall("api/schedule/fetch", {scheduleName : sScheduleName, type: 'checkEvent'}, false, fnCallbackFetchSchedule);
				}
			};

			var fnValidSchedule = function() {

				var oScheduleEventWhen 	= $("#scheduledEventWhen");
				var oCreateEventForm	= $("#createEventForm");

				$('html, body').animate({ scrollTop: 0 }, 'slow');

				if (oScheduleEventWhen.val() === '') {

					oScheduleEventWhen.removeClass();
					oScheduleEventWhen.addClass('validate[required]');
					oCreateEventForm.validationEngine('validateField', '#scheduledEventWhen');
					oScheduleEventWhen.addClass('hasDatepicker, validate[required, custom[' + sClassTypeFormat + ']]');

				} else {

					oScheduleEventWhen.removeClass('hasDatepicker, validate[required, custom[' + sClassTypeFormat + ']]');
					oScheduleEventWhen.addClass('validate[required ,custom[' + sClassTypeFormat + ']]');
					oCreateEventForm.validationEngine('validateField', '#scheduledEventWhen');

				}

				if ((oCreateEventForm.validationEngine('validateField', '#scheduledEventName'))
						|| (oCreateEventForm.validationEngine('validateField', '#scheduledEventWhen'))
						|| (oCreateEventForm.validationEngine('validateField', '#scheduleEventTime'))) {

					bDoIt = false;
					sensus.util.page.stopProgressBar();

				} else {

					sensus.util.page.stopProgressBar();

					var oActionButtons 		= $('#actions-button');
					var sActionKey 			= oActionButtons.data("actionKey");
					var sDemandResponseText = sensus.locale.get("sensus.dm.action.demand.response");
					var oScheduleEventTime 	= $("#scheduleEventTime");
					var sActionText 		= oActionButtons.text().trim();
					var sDate 				= oScheduleEventWhen.val();
					var sHour 				= oScheduleEventTime.val();
					var dDateClient 		= $.date.parseDate(sDate + ' ' + sHour, sensus.settings.dateFormatMask.replace("yyyy", "yy"));
					var dDateServer 		= $.date.setDateServer();

					if ((dDateServer > dDateClient) && (sActionKey != "sensus.dm.action.demand.response")) {

						oScheduleEventTime.validationEngine('showPrompt', sensus.locale.get('systemintelligence.scheduledCreateEvent.dateValition'), 'red', '', true);
						sensus.util.page.stopProgressBar();
						bDoIt = false;

					} else if (sActionText === "Send Text Message") {

						sensus.util.page.stopProgressBar();

						if ((oCreateEventForm.validationEngine('validateField', '#textMessageHan'))|| (oCreateEventForm.validationEngine('validateField', '#text_message_duration'))) {

							bDoIt = false;
							sensus.util.page.stopProgressBar();

						} else {

							if ($('#sendNow').is(":checked") == false) {

								var oDate = $('#preProgramDate');
								var oTime = $("#preProgramTime");

								oDate.addClass("validate[required, custom["+sClassTypeFormat+", future["+$.date.dateFormat(sCurrentDate,"yy-mm-dd")+"]");
								oTime.addClass("validate[required, custom[timeFormat]]");

								var fnCheckHours = function() {

									var dateFormat	= sensus.settings.dateFormatMask.replace('yyyy','yy');
									var sDate 		= oDate.val();
									var sHour 		= oTime.val().replace('am', ' am').replace('pm', ' pm');
									var dDateClient = new Date(sDate + ' ' + sHour);
									var dDateServer = $.date.setDateServer();

									if (dDateServer > dDateClient) {

										oTime.validationEngine('showPrompt', sensus.locale.get('commons.pages.futureDate'), 'red', '', true);
										return true;

									}
								}

								var fnCheckMessage = function() {

									var oTextMessageDuration = $('#text_message_duration')
									var sTextMessageDuration = $(oTextMessageDuration).val();

									if ((sTextMessageDuration == '0') || (sTextMessageDuration.search(/^\d+$/) == -1 )) {

										oTextMessageDuration.validationEngine('showPrompt', sensus.locale.get('commons.pages.invalidValue'), 'red', '', true);
										sensus.util.page.stopProgressBar();
										return true;

									}
								},

								fnCheckDateSendMessages = function() {

										var sScheduleDate 		= oScheduleEventWhen.val();
										var sScheduleTime 		= oScheduleEventTime.val().replace('am', ' am').replace('pm', ' pm');
										var oScheduleDate 		= new Date(sScheduleDate + ' ' + sScheduleTime);
										var sSendMessagesDate 	= $('#preProgramDate').val();
										var sSendMessagesTime 	= $('#preProgramTime').val().replace('am', ' am').replace('pm', ' pm');
										var oSendMessagesDate 	= new Date(sSendMessagesDate + ' ' + sSendMessagesTime);

										if (oSendMessagesDate > oScheduleDate) {

											sensus.util.page.showMessage("messaging-main", sensus.locale.get('systemintelligence.ScheduledEvent.validationDate'), "error");
											sensus.util.page.stopProgressBar();
											return true;

										}
								}

								if (oCreateEventForm.validationEngine('validateField', '#preProgramDate')
										|| oCreateEventForm.validationEngine('validateField', '#preProgramTime')
										|| fnCheckHours() || fnCheckMessage() || fnCheckDateSendMessages()) {

									bDoIt = false;
									sensus.util.page.stopProgressBar();

								} else {
									bDoIt = true;
								}

							} else {

								bDoIt = true;
								$('#preProgramDate').removeClass().addClass('short datepicker hasDatepicker');
								$('#preProgramTime').removeClass().addClass('short datepicker hasDatepicker');

							}
						}

					} else if (sActionKey === "sensus.dm.action.demand.response") {

						var oDuration 		 = $("#duration");
						var oDateAndDuration = sensus.util.actionrequestutil.demandResponse["fnValidatorDateAndDuration"]($('#scheduledEventWhen'), $('#scheduleEventTime'), oDuration);
						var bValidDate  	 = true;

						if (oDateAndDuration != null) {

							var sDateWhen 	 	= oDateAndDuration.date.split(" ")[0];
							var sDateTime 	 	= oDateAndDuration.date.split(" ")[1];
							var sDateFormat		= sensus.settings.dateFormatMask.replace('yyyy','yy');
							var bCompareDate 	= true;
							var dDateServer 	= $.date.setDateServer();

							/**
							 * If Demand Response action, was programmed to Send Now
							 * Is filled the hidden fields at JSP, for send to Controller the date of server
							 */
							if ($("#send_now:checked").length) {

								var dDateServer = $.date.dateFormat(dDateServer, sDateFormat + " h:ia","", {bUserTZ : false});

								$("#pre_program_date").val(dDateServer.split(" ")[0]);
								$("#timeHan").val(dDateServer.split(" ")[1]);

							} else {

								// Validate send time only if the duration field is filled
								if (oDuration.val() != "") {

									/**
									 * Validation of dates when the user put the datetime
									 * The datetime never can be less than datetime current.
									 * And datetime informed never can be greather than end time.
									 * (END TIME = Action date + Duration informed)
									 */
									var oTimeHan 		= $("#timeHan");
									var sPreProgramWhen = $("#pre_program_date").val();
									var sPreProgramTime = oTimeHan.val();
									var dDateAction 	= $.date.parseDate(sDateWhen + ' ' + sDateTime, sDateFormat);
									var dDatePreProgram = $.date.parseDate(sPreProgramWhen + ' ' + sPreProgramTime, sDateFormat);
									var dDateNow 		= dDateServer;
									var iTimeZoneValue 	= parseInt(sensus.settings.timezoneMinutes, 10);

									// Add system timezone to dDateAction variable for makes validation (because the function returns date in UTC)
									dDateAction.setMinutes(dDateAction.getMinutes() + iTimeZoneValue);

									// End Time is the sum between the action date and duration informed in minutes
									var dEndTime = dDateAction;
									dEndTime.setMinutes(dEndTime.getMinutes() + oDateAndDuration.duration);

									// Parse the date format like 00/00/00 00:00am/pm
									var sEndTimeFormated = $.date.dateFormat(dEndTime, sDateFormat + " h:ia","", {bUserTZ : false});

									// Verify that the pre program date informed is greather than date current.
									bCompareDate = $.date.setCompareToDate(dDateNow, dDatePreProgram);

									/**
									 * If the dates still corrects validate the other situation
									 * Verify if preprogram date is less than end time.
									 */
									if (bCompareDate) {

										// Remove one minute for the action have at least one minute
										dEndTime = new Date(dEndTime - 1);

										// Verify that the pre program date informed is less than end time.
										bCompareDate = $.date.setCompareToDate(dDatePreProgram, dEndTime);
									}

									$("#pre_program_date").addClass("validate[required, custom["+sClassTypeFormat+"]");
									$("#timeHan").addClass("validate[required, custom[timeFormat]]");
								}
							}

							// If bCompareDate variable is different of true the pre program date is incorrect.
							if (!bCompareDate) {

								sensus.util.page.showMessage("messaging-main", sensus.locale.get("systemintelligence.scheduledCreateEvent.demandResponseDatesValidation", sEndTimeFormated), "error");

							} else {

								var oHeating = $('#pctHeating');
								var oCooling = $('#pctCooling');

								if (oHeating.val()) {
									// Function to Set the field of temperature according with system temperature
									sensus.util.actionrequestutil.demandResponse["fnSetTemperatureValue"](oHeating);
								}

								if (oCooling.val()) {
									// Function to Set the field of temperature according with system temperature
									sensus.util.actionrequestutil.demandResponse["fnSetTemperatureValue"](oCooling);
								}

								$("#demand-response-when").val(sDateWhen);
								$("#demand-response-time").val(sDateTime);
								$("#demand-response-duration").val(oDateAndDuration.duration);

								bValidDate = false;
							}
						}

						if ((oCreateEventForm.validationEngine('validateField', '#enrollment-code')) || (oCreateEventForm.validationEngine('validateField', '#pctHeating'))
								|| (oCreateEventForm.validationEngine('validateField', '#pctCooling')) || (oCreateEventForm.validationEngine('validateField', '#duty-cycle'))
								|| (oCreateEventForm.validationEngine('validateField', '#load-adjustment')) || (oCreateEventForm.validationEngine('validateField', '#pre_program_date'))
								|| (oCreateEventForm.validationEngine('validateField', '#timeHan')) || bValidDate) {

							bDoIt = false;
							sensus.util.page.stopProgressBar();

						} else {
							bDoIt = true;
						}

					} else {
						bDoIt = true;
					}
				}

				if (!oFieldActionId.val().length) {

					$('#actions-button').validationEngine('showPrompt', sensus.locale.get('commons.pages.fieldRequired'), 'red', '', true);
					sensus.util.page.stopProgressBar();

				} else {

					if (!$('.ui-listcombobox [name=groupList]:checked').length && !$('#upload').val().length && !$('#listSmartPoint').val().length && !$('.ui-listcombobox [name=tagList]:checked').length) {

						$('#select-groups').validationEngine('showPrompt', sensus.locale.get("systemintelligence.scheduledCreateEvent.selectSmartPoints"), 'red', '', true);
						sensus.util.page.stopProgressBar();

					} else {

						if (bDoIt) {

							sensus.pages.longrunningprocess.monitorUpload(fnCallback);
						}
					}
				}
			};

			fnValidSchedule();
		});

		/***********************
		 *
		 *  If selecting a group, the upload field and the text field(To type a meterId or flexNetId) is disabled.
		 *
		 *  Or
		 *
		 *  If typing meterId or flexNetId in the text field or selecting a file to upload the groups checkboxs and comboBox with is disabled.
		 *
		 **************************************/
		$(".ui-listcombobox").delegate('input[name=groupList]', 'click', function(e) {

			if ($('input[name=groupList]:checked').length) {

				$("#upload, #listSmartPoint").attr('disabled', true);

				$('#tag-list input').attr('disabled', true);
				$('#combo_tag_input, #TagsDiv .yui-u button').hide();

			} else {

				$("#upload, #listSmartPoint").attr('disabled', false);

				$('#tag-list input').attr('disabled', false);
				$('#combo_tag_input, #TagsDiv .yui-u button').show();

			}

			fnCountSmartpoint($(this), false, 'group');

		});

		$(".ui-listcombobox").delegate('input[name=tagList]', 'click', function(e) {

			if ($('input[name=tagList]:checked').length) {

				$("#upload, #listSmartPoint").attr('disabled', true);
				$('#group-list input').attr('disabled', true);
				$('#combo_group_input, #GroupsDiv .yui-u button').hide();

			} else {

				$("#upload, #listSmartPoint").attr('disabled', false);
				$('#group-list input').attr('disabled', false);
				$('#combo_group_input, #GroupsDiv .yui-u button').show();

			}

			fnCountSmartpoint($(this), false,'tag');
		});

		/*
		 * Function: fnCountSmartpoint
		 * Purpose:  To increment or decrement the quantity of SmartPoints
		 * Returns:  -
		 * Inputs:   object jQuery
		 */
		var fnCountSmartpoint = function(oGroup, wasAppended, sType) {

			var sIds = [];

			if (sType == 'group') {

				$("#GroupsDiv #group-list ul li").each(function() {

					if ($(this).find("input").prop("checked") == true) {
						sIds.push(parseInt($(this).find("input").val(), 10));
					}
				});

			} else if (sType == 'tag') {

				$("#TagsDiv #tag-list ul li").each(function() {

					if ($(this).find("input").prop("checked") == true) {
						sIds.push(parseInt($(this).find("input").val(), 10));
					}

				});
			}

			var iDevices	 	= sensus.pages.scheduleEvent.getDeviceCountByGroup(sIds);
			var oLabelDevices 	= $('.size');

			oLabelDevices.text(iDevices);
		}

		// When change upload the
		$('#upload').change(function() {

			if ($(this).val().length) {

				sensus.pages.scheduleEvent.isSubmit = true;

				$("#listSmartPoint").attr("disabled","disabled");
				$('#combo_group_input, #GroupsDiv .yui-u button').hide();
				$('input[name=groupList]').attr('disabled', true);
				$('#tag-list input').attr('disabled', true);
				$('#combo_tag_input, #TagsDiv .yui-u button').hide();

			} else {

				$("#listSmartPoint").attr('disabled',false);
				$('#combo_group_input, #GroupsDiv .yui-u button').show();
				$('input[name=groupList]').attr('disabled', false);
				$('#tag-list input').attr('disabled', false);
				$('#combo_tag_input, #TagsDiv .yui-u button').show();

			}
		});

		/** get id to set value default */
		var sActionId = $('[id^=types-]', '#action-type-content').attr('name');

		// set Action default
		//	actions-button
	    if ($('#action-name').val() != undefined) {

			if ($('#action-name').val().length > 2) {

				$("#actions-button").text(sensus.locale.get($('#action-name').val()));
				$('.han-text-message').hide();

				if ($("#actionId").val() == "") {
					$("#actionId").val(sActionId);
				}

				$('.demand-response-fields_hide').hide();

				if ($('#action-name').val() === "sensus.dm.action.send.han.text.message") {
					$('.han-text-message').show();
				}

				if ($('#action-name').val() === "sensus.dm.action.demand.response") {

					$("#scheduledEventWhen").removeClass().addClass("validate[required] hasDatepicker");

					var sTemperatureType 	= sensus.settings.temperatureType;
					var oPctHeating 		= $("#pctHeating");
					var oPctCooling 		= $("#pctCooling");

					$('.temperature-type').text(sensus.locale.get("systemintelligence.dialogDemandResponse.validInputs."+ sTemperatureType));

					if (sTemperatureType.toLowerCase() === sensus.locale.get("systemsettings.page.temperature.celsius").toLowerCase()) {

						oPctHeating.addClass("validate[number, min[0],max[25.4]]");
						oPctCooling.addClass("validate[number, min[0],max[25.4]]");

					} else {

						oPctHeating.addClass("validate[number, min[0],max[45.72]]");
						oPctCooling.addClass("validate[number, min[0],max[45.72]]");

					}

					$('.demand-response-fields_hide').show();
				}

			} else {
				$('#actions-button').text(sensus.locale.get("systemintelligence.create.action.selectAction")).attr('tabindex','3');
			}

		} else {
			$('#actions-button').text(sensus.locale.get("systemintelligence.create.action.selectAction")).attr('tabindex','3');
		}

		// Open Dialog Repeat
		$("#repeatCheckBox").click(function(e) {

			if ($('#repeatCheckBox').is(":checked")) {

				$('.formError').remove();

				if (!$("#createEventForm").validationEngine('validateField', '#scheduledEventWhen')) {

					sensus.util.actiondialog.launchActionDialog("repeatSchedule", sensus.pages.schedule.dialogSettings["repeatSchedule"]());

				} else {

					$(this).attr('checked', false);
				}

			} else {

				var oFrequency = {
						DAILY 				: null,
						EVERY_WEEKDAY 		: null,
						EVERY_MON_WED_FRI	: null,
						EVERY_TUE_THURS 	: null,
						WEEKLY 				: null,
						MONTHLY 			: null,
						YEARLY 				: null,
					    FREQUENCY_TYPE 		: null
				};

				sensus.pages.scheduleEvent.oFrequency = oFrequency;

				/** START  Clean the form*/
				$("#addRepeatDefinitionForm input:text").val("");
				$("#addRepeatDefinitionForm input:checkbox").prop("checked", false);

				$(".radio-list").each(function() {
					$(this).find("input:radio:first").prop("checked", true);
				});

				// Set default state for the repeat dialog
				$("#repeat-type-select, #repeats").val("DAILY");

				$('#description-repeat').empty();
				$('#edit-repeats').hide();
			}
		});

		// Open Dialog Repeat after set information in it
		$(".submit-row-pad").delegate("#edit-repeats", "click", function(e) {

			e.preventDefault();

			if (!$("#createEventForm").validationEngine('validateField', '#scheduledEventWhen')) {

				sensus.util.actiondialog.launchActionDialog("repeatSchedule",
						sensus.pages.schedule.dialogSettings["repeatSchedule"]());
			}
		});

		if ($("#descriptionRepeat").text().toUpperCase() === "NEVER") {
			$("#repeatCheckBox").attr("checked", false);
		};

		// Back to Schedule List
		$("#back-list, #cancel-schedule").click(function(e) {

			e.preventDefault();

			sensus.util.page.startGlobalProgressBar();

			var sUrl 		= $(this).attr("href");
			var sSession 	= sensus.util.session.read("SelectedFilters");
			var $tabContent = $("#tabs-content");

			var callback = function() {
				$.fn.pageLoader.parameter("initialLoad", null);
			};

			// Remove validations alerts
			$('.formError').remove();

			sensus.util.session.remove(["SelectedFilters"]);

			if (sSession) {
				sUrl = sUrl + "?initialLoad=false&" + sSession;
			}

			if ($tabContent.length) {

				$.fn.pageLoader.loadTab(sUrl, $tabContent, null, callback, null, false);

			} else {
				$.fn.pageLoader.load(sUrl, $("#load"), null, callback, null, false);
			}
		});

		$("#recentRequestMonitor").click(function() {

			var $this = $(this);

			$this.val($this.is(":checked"));
		});

		/**************************************************************************************
		 *
		 * EDIT
		 *
		 * Purpose: Enable when will create a schedule and disable when will edit a schedule
		 *
		 **************************************************************************************/
		if ($.address.parameter('type') === 'edit' || $.address.parameter('type') === 'clone') {

			var fnMenuPlugCallback = function (item,sActionId) {
				var option = item.split("-")[1];
				$("input#actionName").val(option);
			};

			$("input#actionName").val(sActionId);

			if ($("#actionId").val() == "") {
				$("input#actionId").val(sActionId);
			}

			if ($.address.parameter('type') === 'edit') {
				$('#createAction').val(sensus.locale.get("systemintelligence.page.action.buttonDialogSchedule"));
			}

			var aSelDays = $('#list-of-days-selected').val().split(',');

			aSelDays.pop();

			 $.each(aSelDays,function(index, item) {

				var oSel = $('#validate_day_week input[value="'+item.toLowerCase()+'"]');

				oSel.attr('checked', true);
				oSel.next().addClass('ui-state-active');

			});

			// if has group selected disable upload
			if ( $('input[name=groupList]:checked').length ) {

				$("#upload, #listSmartPoint").attr('disabled', true);

				$('#tag-list input').attr('disabled', true);
				$('#combo_tag_input, #TagsDiv .yui-u button').hide();

			} else if ($("#listSmartPoint").val().length) {

				$('input[name=groupList], #upload').attr('disabled', true);
				$('#combo_group_input, #GroupsDiv .yui-u button').hide();

				$('#tag-list input').attr('disabled', true);
				$('#combo_tag_input, #TagsDiv .yui-u button').hide();

			} else if ($('input[name=tagList]:checked').length) {

				$("#upload, #listSmartPoint").attr('disabled', true);

				$('#group-list input').attr('disabled', true);
				$('#combo_group_input, #GroupsDiv .yui-u button').hide();

			}

			$('#actions-button').click(function() {
				return false;
			});

			if ($("#has-repeat").val() == "true") {

				$("#repeatCheckBox").prop('checked', true);

				$("#addRepeatDefinitionForm #repeats option").each(function() {

					if ($(this).val() == $("#repeat-type-select").val()) {
						$(this).prop("selected", true);
					}

				});

				var sId 	= $("#repeats").find("option:selected").attr("id");
				var idEnds 	= $("#ends-" + sId + "-radio").val();

				$("#" + sId + "-form #ends-on #" + idEnds).prop("selected", true);

				sensus.pages.scheduleEvent.fnChangeSummary($("#repeats"));

				$("#description-repeat").html("<a>" + $("#repeats-summary").text() + "</a>");

				if ($("#description-repeat").length && $("#description-repeat").text().trim().length) {
					$("#edit-repeats").removeClass("hide");
				}
			}

		} else {

			var fnMenuPlugCallback = function (item,sActionId) {

				var option 			= item.split("-")[0];
				var oActionButton 	= $("#actions-button");

				oActionButton.data("actionKey", option);
				oActionButton.text(sensus.locale.get(option)).append('<span class="ui-icon ui-icon-triangle-1-s"></span>');

		    	$("input#actionName").val(option);
		    	$("input#actionId").val(sActionId);
		    	$('.han-text-message').hide();
		    	$('.demand-response-fields_hide').hide();

				if (option === "sensus.dm.action.send.han.text.message") {
					$('.han-text-message').show();
				} else if (option === "sensus.dm.action.demand.response") {

					$("#scheduledEventWhen").removeClass().addClass("validate[required] hasDatepicker");

					var sTemperatureType 	= sensus.settings.temperatureType;
					var oPctHeating 		= $("#pctHeating");
					var oPctCooling 		= $("#pctCooling");

					$('.temperature-type').text(sensus.locale.get("systemintelligence.dialogDemandResponse.validInputs."+ sTemperatureType));

					if (sTemperatureType.toLowerCase() === sensus.locale.get("systemsettings.page.temperature.celsius").toLowerCase()) {
						oPctHeating.addClass("validate[number, min[0],max[25.4]]");
						oPctCooling.addClass("validate[number, min[0],max[25.4]]");
					} else {
						oPctHeating.addClass("validate[number, min[0],max[45.72]]");
						oPctCooling.addClass("validate[number, min[0],max[45.72]]");
					}

					$('.demand-response-fields_hide').show();
				}
			}

			// Add Actions default at device list
			var oDefaultActions = [];

			if (sensus.settings.oDeviceTypeParameters.scheduleActions) {
				oDefaultActions = sensus.settings.oDeviceTypeParameters.scheduleActions;
			}

			var oActions = sensus.util.actionrequestutil.fnLoadDefaultActions(oDefaultActions);

			/* Initialize action buttons */
			sensus.util.page.menuPlug(sensus.pages.systemintelligence, fnMenuPlugCallback, oActions, null, false);
			$('#actions-button').append('<span class="ui-icon ui-icon-triangle-1-s"></span>');
		}

		$('#description-repeat').delegate('a', 'click', function(e) {
			e.preventDefault();
			sensus.util.actiondialog.launchActionDialog("repeatSchedule", sensus.pages.schedule.dialogSettings["repeatSchedule"]());
		});

		sensus.util.page.stopGlobalProgressBar();
	});
	</script>
</sec:authorize>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and 
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">

	sensus.pages.schedule.dialogSettings = {

		repeatSchedule : function() {

			var oFrequencyDescription = {
					DAILY 				: {name : "DAILY"},
					EVERY_WEEKDAY 		: {name : "EVERY_WEEKDAY"},
					EVERY_MON_WED_FRI	: {name : "EVERY_MON_WED_FRI"},
					EVERY_TUE_THURS 	: {name : "EVERY_TUE_THURS"},
					WEEKLY 				: {name : "WEEKLY"},
					MONTHLY 			: {name : "MONTHLY"},
					YEARLY 				: {name : "YEARLY"}
			};

			/**
			 * Function to validate the frequency form of according the frequency selected
			 * @Param String - sFrequency (frequency selected)
			 * @Param Jquery Object - $form (frequency form)
			 *
			 * @Return True or false (return false when exists error in the some input)
			 */
			var fnValidateFrequencyForm = function(sFrequency, $form) {

				var sValidateField 	= "validateField";
				var sChecked 		= ":checked";

				// Validation when frequency type is Daily
				if (sFrequency == oFrequencyDescription.DAILY.name) {

					if ($form.validationEngine(sValidateField, "#repeatStartDateDaily")
							|| $form.validationEngine(sValidateField, "#repeats-every-value")) {
						return false;
					}

					if ($("#after").is(sChecked) && $form.validationEngine(sValidateField, "#occurrences")) {
						return false;
					}

					if ($("#on").is(sChecked) && $form.validationEngine(sValidateField, "#date-after-daily")) {
						return false;
					}

					return true;
				}

				// Validation when frequency type is Every Weekday
				if (sFrequency == oFrequencyDescription.EVERY_WEEKDAY.name) {

					if ($form.validationEngine(sValidateField, "#repeat-start-date-weekday")) {
						return false;
					}

					if ($("#after-weekday").is(sChecked) && $form.validationEngine(sValidateField, "#occurrences-weekday")) {
						return false;
					}

					if ($("#on-weekday").is(sChecked) && $form.validationEngine(sValidateField, "#date-after-dateWeekDay")) {
						return false;
					}

					return true;
				}

				// Validation when frequency type is Every Monday, Wednesday and Friday
				if (sFrequency == oFrequencyDescription.EVERY_MON_WED_FRI.name) {

					if ($form.validationEngine(sValidateField, "#repeat-start-date-every-other")) {
						return false;
					}

					if ($("#after-every-other").is(sChecked) && $form.validationEngine(sValidateField, "#occurrences-every-other")) {
						return false;
					}

					if ($("#on-every-other").is(sChecked) && $form.validationEngine(sValidateField, "#date-after-dateEveryOther")) {
						return false;
					}

					return true;
				}

				// Validation when frequency type is Every Tuesday and Thursday
				if (sFrequency == oFrequencyDescription.EVERY_TUE_THURS.name) {

					if ($form.validationEngine(sValidateField, "#repeat-start-date-every-t")) {
						return false;
					}

					if ($("#after-every-t").is(sChecked) && $form.validationEngine(sValidateField, "#occurrences-every-t")) {
						return false;
					}

					if ($("#on-every-t").is(sChecked) && $form.validationEngine(sValidateField, "#date-after-dateEvery")) {
						return false;
					}

					return true;
				}

				// Validation when frequency type is Weekly
				if (sFrequency == oFrequencyDescription.WEEKLY.name) {

					if ($form.validationEngine(sValidateField, "input[name=dayWeekly]")
							|| $form.validationEngine(sValidateField, "#repeat-start-date-weekly")
							|| $form.validationEngine(sValidateField, "#repeats-every-value-weekly")) {
						return false;
					}

					if ($("#after-weekly").is(sChecked) && $form.validationEngine(sValidateField, "#occurrences-weekly")) {
						return false;
					}

					if ($("#on-weekly").is(sChecked) && $form.validationEngine(sValidateField, "#date-after-EveryValueWeekly")) {
						return false;
					}

					return true;
				}

				// Validation when frequency type is Monthly
				if (sFrequency == oFrequencyDescription.MONTHLY.name) {

					if ($form.validationEngine(sValidateField, "#repeat-start-date-monthly")
							|| $form.validationEngine(sValidateField, "#repeats-every-value-monthly")) {
						return false;
					}

					if ($("#after-repeats-monthly").is(sChecked) && $form.validationEngine(sValidateField, "#occurrences-repeats-monthly")) {
						return false;
					}

					if ($("#on-repeats-monthly").is(sChecked) && $form.validationEngine(sValidateField, "#date-after-EveryValueMonthly")) {
						return false;
					}

					return true;
				}

				// Validation when frequency type is Yearly
				if (sFrequency == oFrequencyDescription.YEARLY.name) {

					if ($form.validationEngine(sValidateField, "#repeat-start-date-yearly")
							|| $form.validationEngine(sValidateField, "#repeats-every-value-yearly")) {
						return false;
					}

					if ($("#after-yearly").is(sChecked) && $form.validationEngine(sValidateField, "#occurrences-yearly")) {
						return false;
					}

					if ($("#on-yearly").is(sChecked) && $form.validationEngine(sValidateField, "#date-after-EveryValueYearly")) {
						return false;
					}

					return true;
				}

			};

			return {

				title : sensus.locale.get("action.repeatschedule.title"),

				width : 600,

				minheight: 150,

				/** The dialog buttons (submit and cancel)*/
				buttons : (function() {

					var buttons = {};

					buttons[sensus.locale.get("commons.pages.save")] = function() {

						var sSelected 	= $("#repeats").val();
						var bDoIt 		= fnValidateFrequencyForm(sSelected, $("#validateRepeatForm"));

						if (bDoIt) {

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

							// START - Fill frequency object
							var sIdForm = $(".repeat-form.enable").attr("id");

							// Fill the ends object into the frequency type object
							var fnFillFrequencyObject = function(sType, sStartOn, sTimeToRepeat, sAfterOccurrences, sOnDate) {

								oFrequency[sType] = {
										ends : {}
								};

								if (!$.sc.isNullOrUndefined(sStartOn) && sStartOn != "") {
									oFrequency[sType].starts = sStartOn;
								}

								if (!$.sc.isNullOrUndefined(sTimeToRepeat) && sTimeToRepeat != "") {
									oFrequency[sType].everyDay = sTimeToRepeat;
								}

								if (!$.sc.isNullOrUndefined(sAfterOccurrences) && sAfterOccurrences != "") {
									oFrequency[sType].ends.afterOccurrences = sAfterOccurrences;
								} else if (!$.sc.isNullOrUndefined(sOnDate) && sOnDate != "") {
									oFrequency[sType].ends.onDate = sOnDate;
								} else {
									oFrequency[sType].ends.never = true;
								}

							};

							if (sIdForm == "day-form") {

								oFrequency.FREQUENCY_TYPE = oFrequencyDescription.DAILY.name;
								fnFillFrequencyObject(oFrequencyDescription.DAILY.name, $("#repeatStartDateDaily").val(), $("#repeats-every-value").val(), $("#occurrences").val(), $("#date-after-daily").val());

							} else if (sIdForm == "weekday-form") {

								oFrequency.FREQUENCY_TYPE = oFrequencyDescription.EVERY_WEEKDAY.name;
								fnFillFrequencyObject(oFrequencyDescription.EVERY_WEEKDAY.name, $("#repeat-start-date-weekday").val(), null, $("#occurrences-weekday").val(), $("#date-after-dateWeekDay").val());

							} else if (sIdForm == "every-other-form") {

								oFrequency.FREQUENCY_TYPE = oFrequencyDescription.EVERY_MON_WED_FRI.name;
								fnFillFrequencyObject(oFrequencyDescription.EVERY_MON_WED_FRI.name, $("#repeat-start-date-every-other").val(), null, $("#occurrences-every-other").val(), $("#date-after-dateEveryOther").val());

							} else if (sIdForm == "every-t-form") {

								oFrequency.FREQUENCY_TYPE = oFrequencyDescription.EVERY_TUE_THURS.name;
								fnFillFrequencyObject(oFrequencyDescription.EVERY_TUE_THURS.name, $("#repeat-start-date-every-t").val(), null, $("#occurrences-every-t").val(), $("#date-after-dateEvery").val());

							} else if (sIdForm == "weekly-form") {

								oFrequency.FREQUENCY_TYPE = oFrequencyDescription.WEEKLY.name;
								fnFillFrequencyObject(oFrequencyDescription.WEEKLY.name, $("#repeat-start-date-weekly").val(), $("#repeats-every-value-weekly").val(), $("#occurrences-weekly").val(), $("#date-after-EveryValueWeekly").val());

								var aDays = [];

								$("#validate_day_week label.ui-state-active").each(function() {
									aDays.push($(this).attr("for"));
								});

								oFrequency[oFrequencyDescription.WEEKLY.name].daysOfWeeks = aDays;

							} else if (sIdForm == "monthly-form") {

								oFrequency.FREQUENCY_TYPE = oFrequencyDescription.MONTHLY.name;
								fnFillFrequencyObject(oFrequencyDescription.MONTHLY.name, $("#repeat-start-date-monthly").val(), $("#repeats-every-value-monthly").val(), $("#occurrences-repeats-monthly").val(), $("#date-after-EveryValueMonthlys").val());

								if ($("#date").attr("checked") != "checked") {
									oFrequency[oFrequencyDescription.MONTHLY.name].dayOfWeek = true;
								}

							} else if (sIdForm == "yearly-form") {

								oFrequency.FREQUENCY_TYPE = oFrequencyDescription.YEARLY.name;
								fnFillFrequencyObject(oFrequencyDescription.YEARLY.name, $("#repeat-start-date-yearly").val(), $("#repeats-every-value-yearly").val(), $("#occurrences-yearly").val(), $("#date-after-EveryValueYearly").val());

							}

							sensus.pages.scheduleEvent.oFrequency = oFrequency;
							sensus.pages.scheduleEvent.fnLoadFrequency(oFrequency);

							// Append in form
							$("#addRepeatDefinitionForm").appendTo("#createEventForm").hide();

							$("#actionDialogRepeatDefine").dialog("close");
							$("#description-repeat").html("<a>" + $("#repeats-summary").text() + "</a>");
							$("#edit-repeats").show();
							$(".ui-dialog").find(".ui-dialog-titlebar-close").show();
						}
					};

					buttons[sensus.locale.get("commons.pages.cancel")] = function() {

						var oFrequency 		= sensus.pages.scheduleEvent.oFrequency;
						var sFrequencyType 	= oFrequency.FREQUENCY_TYPE;
						var $addRepeatForm 	=  $("#addRepeatDefinitionForm");

						/** START  Clean the form*/
						$("#addRepeatDefinitionForm input:text").val("");
						$("#addRepeatDefinitionForm input:checkbox").prop("checked", false);

						$(".radio-list").each(function() {
							$(this).find("input:radio:first").prop("checked", true);
						});
						/** END */

						// If haven't value in sFrequencyType variable reset the dialog with default values
						if ($.sc.isNullOrUndefined(sFrequencyType)) {

							// Set default state for the repeat dialog
							$("#repeat-type-select, #repeats").val(oFrequencyDescription.DAILY.name);

							$addRepeatForm.appendTo("#createEventForm").hide();
							$("#description-repeat").empty();
							$("#actionDialogRepeatDefine").dialog("close");
							$("#repeatCheckBox").attr("checked", false);
							$(".formError").remove();
							$("#edit-repeats").hide();
							$(".ui-dialog").find(".ui-dialog-titlebar-close").show();

						// If have value in sFrequencyType variable fill the dialog of according with the frequency object
						} else {

							// Function to load the inputs of the option selected previously
							var fnFillInputs = function($startOn, $timeToRepeat, $endsAfter, $ednsAfterRadio, $endsOn, $endsOnRadio, $form) {

								var sStarts = oFrequency[sFrequencyType].starts;
								var sOnDate = oFrequency[sFrequencyType].ends.onDate;

								if (sStarts) {

									var sStartsFormated = $.date.dateFormat(sStarts);

									if (sStartsFormated == "") {
										sStartsFormated = sStarts;
									}

									$startOn.val(sStartsFormated);
								}

								if (!$.sc.isNullOrUndefined($timeToRepeat)) {
									$timeToRepeat.val(oFrequency[sFrequencyType].everyDay);
								}

								if (!$.sc.isNullOrUndefined(oFrequency[sFrequencyType].ends.afterOccurrences)) {

									$endsAfter.val(oFrequency[sFrequencyType].ends.afterOccurrences);
									$ednsAfterRadio.prop("checked", true);

								} else if (!$.sc.isNullOrUndefined(sOnDate)) {

									var sOnDateFormated = $.date.dateFormat(sOnDate);

									if (sOnDateFormated == "") {
										sOnDateFormated = sOnDate
									}

									$endsOn.val(sOnDateFormated);
									$endsOnRadio.prop("checked", true);

								} else {
									$("#never").prop("checked", true);
								}

								$form.addClass("enable");
							};

							$(".repeat-form.enable").removeClass("enable");

							if (sFrequencyType == oFrequencyDescription.DAILY.name) {

								fnFillInputs($("#repeatStartDateDaily"), $("#repeats-every-value"), $("#occurrences"), $("#after"), $("#date-after-daily"), $("#on"), $("#day-form"));

							} else if (sFrequencyType == oFrequencyDescription.EVERY_WEEKDAY.name) {

								fnFillInputs($("#repeat-start-date-weekday"), null, $("#occurrences-weekday"), $("#after-weekday"), $("#date-after-dateWeekDay"), $("#on-weekday"), $("#weekday-form"));

							} else if (sFrequencyType == oFrequencyDescription.EVERY_MON_WED_FRI.name) {

								fnFillInputs($("#repeat-start-date-every-other"), null, $("#occurrences-every-other"), $("#after-every-other"), $("#date-after-dateEveryOther"), $("#on-every-other"), $("#every-other-form"));

							} else if (sFrequencyType == oFrequencyDescription.EVERY_TUE_THURS.name) {

								fnFillInputs($("#repeat-start-date-every-t"), null, $("#occurrences-every-t"), $("#after-every-t"), $("#date-after-dateEvery"), $("#on-every-t"), $("#every-t-form"));

							} else if (sFrequencyType == oFrequencyDescription.WEEKLY.name) {

								fnFillInputs($("#repeat-start-date-weekly"), $("#repeats-every-value-weekly"), $("#occurrences-weekly"), $("#after-weekly"), $("#date-after-EveryValueWeekly"), $("#on-weekly"), $("#weekly-form"));

								var arrayDays = oFrequency[sFrequencyType].daysOfWeeks;

								for (var i = 0; i < arrayDays.length; i++) {
									$("#validate_day_week").find("input#" + arrayDays[i].trim()).prop("checked", true);
								}

							} else if (sFrequencyType == oFrequencyDescription.MONTHLY.name) {

								fnFillInputs($("#repeat-start-date-monthly"), $("#repeats-every-value-monthly"), $("#occurrences-repeats-monthly"), $("#after-repeats-monthly"), $("#date-after-EveryValueMonthly"), $("#on-repeats-monthly"), $("#monthly-form"));

							} else if (sFrequencyType == oFrequencyDescription.YEARLY.name) {

								fnFillInputs($("#repeat-start-date-yearly"), $("#repeats-every-value-yearly"), $("#occurrences-yearly"), $("#after-yearly"), $("#date-after-EveryValueYearly"), $("#on-yearly"), $("#yearly-form"));

							}

							// Append in form
							$("#addRepeatDefinitionForm").appendTo("#createEventForm").hide();
							$("#repeat-type-select, #repeats").val(sFrequencyType);
							$("#actionDialogRepeatDefine").dialog("close");
							$(".formError").remove();
							$(".ui-dialog").find(".ui-dialog-titlebar-close").show();
						}
					};

					return buttons;
				})(),

				action : function(actionDialog) {

					//Change align of the buttons
					$("div .ui-dialog-buttonset").addClass("align-buttonset");

					var sDateFormat 		= sensus.settings.dateFormatMask.replace("yyyy", "yy");
					var sFormatDate 		= "yy-mm-dd";
					var sClassTypeFormat 	= "datePt";
					var $date 				= $("#starts-on input", ".enable");
					var $end 		 		= $("#ends-on .datepicker",".enable");
					var $form 				= $("#addRepeatDefinitionForm");
					var $repeatEvery 		= $("#repeats-every input",".enable");
					var $dateWhen 	 		= $("#scheduledEventWhen");
					var dEnd 		 		= $.datepicker.formatDate(sFormatDate, $.datepicker.parseDate(sDateFormat, $dateWhen.val()));

					if (sDateFormat == "mm/dd/yy") {
						sClassTypeFormat = "dateEn";
					}

					$(".ui-dialog").find(".ui-dialog-titlebar-close").hide();
					$("#custom-text-area").attr("readonly", "readonly");

					actionDialog.empty();

					$form.appendTo(actionDialog).show();
					$form.wrap("<form id='validateRepeatForm' name='validateRepeatForm' action='#'></form>");

					actionDialog.dialog("open");
					actionDialog.dialog("option", "closeOnEscape", false);

					// Add class of validation for all date inputs
					$("#repeatStartDateDaily, #repeat-start-date-weekday, #repeat-start-date-every-other, #repeat-start-date-every-t, #repeat-start-date-weekly, #repeat-start-date-yearly").addClass("validate[required, custom["+sClassTypeFormat+"]]");

					$("#date-after-daily", ".radio-list").datepicker({
						dateFormat: sDateFormat,
						minDate: "-0D"
					});

					$("#repeat-start-date-weekday, #repeat-start-date-every-t, #repeat-start-date-every-other, #repeat-start-date-weekly, #repeat-start-date-monthly, #repeat-start-date-yearly").datepicker({
						dateFormat	: sDateFormat,
						minDate		: "-0D"
					});

					sensus.pages.scheduleEvent.fnLoadRepeatDialog();

					$date.datepicker({
						dateFormat: sDateFormat,
						minDate: "-0D"
					});

					$end.datepicker().change(function() {

						var dStart = $.datepicker.formatDate(sFormatDate, $.datepicker.parseDate(sDateFormat, $("#starts-on .datepicker",".enable").val()));

						if (dStart.length == 0) {
							dStart = $.datepicker.formatDate(sFormatDate, $.datepicker.parseDate(sDateFormat, $dateWhen.val()));
						}

						$end.addClass("short datepicker validate[required, custom["+sClassTypeFormat+"], future["+ dStart +"]]");
					});

					$end.datepicker({
						dateFormat: sDateFormat,
						minDate: "-0D"
					});

					if ($date.val().length == 0) {
						$date.val($dateWhen.val());
					}

					if ($repeatEvery.val() && $repeatEvery.val().length == 0) {
						$repeatEvery.val("1");
					}

					$("input[type=radio]", ".radio-list:visible").live("click", function() {

						$(".date-short, .short, .datepicker, .hasDatepicker", ".radio-list:visible").val("");
						$(".formError").remove();

					});

					// Commom function to select de elment and fill the others dialog inputs
					var fnChangeOption = function($element) {

						$element.prev().prev().attr("checked", true);
						$(".date-short, .short, .datepicker, .hasDatepicker", ".radio-list:visible").val("");

					};

					// Event Click for the inputs of ends on X date
					$("#date-after-daily, #date-after-dateWeekDay, #date-after-dateEveryOther, #date-after-dateEvery, #date-after-EveryValueWeekly, #date-after-EveryValueMonthly, #date-after-EveryValueYearly").live("click", function() {
						// Select this option and clean the others inputs
						fnChangeOption($(this));
					});

					// Event Click for the inputs of ends after X occurrences
					$("#occurrences, #occurrences-weekday, #occurrences-every-other, #occurrences-every-t, #occurrences-weekly, #occurrences-repeats-monthly, #occurrences-yearly").live("click", function() {
						// Select this option and clean the others inputs
						fnChangeOption($(this));
					});

					var aSelDays = $("#list-of-days-selected").val().split(",").pop();

					$.each(aSelDays, function(index, item) {

						var $sel = $("#validate_day_week input[value='"+item.toLowerCase()+"']");
						$sel.attr("checked", true).next().addClass("ui-state-active");

					});

					dEnd = $.datepicker.formatDate(sFormatDate, new Date($date.val()));

					if ($date.val().length == 0) {
						dEnd = $.datepicker.formatDate(sFormatDate, new Date($dateWhen.val()));
					}

					$date.removeClass().addClass("short datepicker validate[required, custom["+sClassTypeFormat+"], future["+dEnd+"]]");

					actionDialog.delegate(".onDatepicker", "focus", function() {
						$(".on").click();
					});

					actionDialog.delegate(".on", "click", function() {

						var dStart = $.datepicker.formatDate(sFormatDate, $.datepicker.parseDate(sDateFormat, $("#starts-on .datepicker", ".enable").val()));

						$(this).next().next().removeClass().addClass("short onDatepicker datepicker validate[required, custom["+sClassTypeFormat+"], future["+dStart+"]]");

					});

					var dStart = $.datepicker.formatDate(sFormatDate, $.datepicker.parseDate(sDateFormat, $("#starts-on .datepicker", ".enable").val()));

					$("#repeats").change(function() {

						var $date 			 = $("#starts-on input", ".enable");
						var $dateWhen 		 = $("#scheduledEventWhen");
						var dateFormat 		 = sensus.settings.dateFormatMask.replace("yyyy", "yy");
						var sClassTypeFormat = "datePt";
						var sFormatDate 	 = "yy-mm-dd";
						var dEnd 			 = $.datepicker.formatDate(sFormatDate, $.datepicker.parseDate(dateFormat, $dateWhen.val()));
						var dStart 			 = $.datepicker.formatDate(sFormatDate, $.datepicker.parseDate(dateFormat, $('#starts-on .datepicker','.enable').val()));

						if (dateFormat == "mm/dd/yy") {
							sClassTypeFormat = "dateEn";
						}

						if ($date.val().length == 0) {
							$date.val($dateWhen.val());
						}

						$date.removeClass().addClass("short onDatepicker datepicker validate[required, custom["+sClassTypeFormat+"], future["+dEnd+"]]");
						$('.on').next().next().removeClass().addClass("short onDatepicker datepicker validate[required, custom["+sClassTypeFormat+"], future["+dStart+"]]");

						$date.datepicker({
							dateFormat: dateFormat,
							minDate: "-0D"
						});
					});

					dEnd = $.datepicker.formatDate(sFormatDate, $.datepicker.parseDate(sDateFormat, $dateWhen.val()));

					var $on = $('.on').next().next();

					$date.removeClass().addClass("short datepicker validate[required, custom["+sClassTypeFormat+"], future["+dEnd+"]]");

					$on.removeClass().addClass("short onDatepicker datepicker validate[required, custom["+sClassTypeFormat+"], future["+dStart+"]] ");

					$on.datepicker({
						dateFormat: sDateFormat,
						minDate: "-0D"
					});

					/** Updates the field Repeat Start Date	*/
					var fnUpdateStartDate = function(oDateSchedule, oDateRepeat) {

						if (oDateRepeat.val() != oDateSchedule.val()) {
							oDateRepeat.val(oDateSchedule.val());
						}

					};

					//Update the field Repeat Start Date Daily
					fnUpdateStartDate($dateWhen, $('#repeatStartDateDaily'));

					//Update the field Repeat Start Date Every Weekday
					fnUpdateStartDate($dateWhen, $('#repeat-start-date-weekday'));

					//Update the field Repeat Start Date Every Mon, Wed and Fri
					fnUpdateStartDate($dateWhen, $('#repeat-start-date-every-other'));

					//Update the field Repeat Start Date Every Tuesday and Thursday
					fnUpdateStartDate($dateWhen, $('#repeat-start-date-every-t'));

					//Update the field Repeat Start Date Every Weekly
					fnUpdateStartDate($dateWhen, $('#repeat-start-date-weekly'));

					//Update the field Repeat Start Date Every Monthly
					fnUpdateStartDate($dateWhen, $('#repeat-start-date-monthly'));

					//Update the field Repeat Start Date Every Yearly
					fnUpdateStartDate($dateWhen, $('#repeat-start-date-yearly'));
				}
			}
		}
	};
	</script>

</sec:authorize>
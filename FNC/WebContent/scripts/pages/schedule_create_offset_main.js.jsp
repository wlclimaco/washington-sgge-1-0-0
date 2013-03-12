<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager')">
<script type="text/javascript">
/**
 * @namespace sensus.pages.schedule
 * @description The creat-main namespace for the Schedule Page.
 */

/**
 * @fileoverview Defines the core functionality of the Create Schedule page.
 * @author Anke Doerfel-Parker
 */

/**
 * The main schedule namespace containing schedule-related functions.
 */
sensus.pages.schedule = {

	/** The changed */
	changed: false,

	validateSunriseSunset : function(element) {

		if ($(element).length) {

			var value = $(element).val();

			if (value % 5 == 0){

				return true;

			} else {

				$(element).validationEngine('showPrompt', $.sc.locale("schedulecreate.form.timeSunsetSunrise"), 'red', '', true);

				return false;

			}

		} else {

			return true;

		}
	},

	validateSunriseSunsetSwitch : function(element) {

		var name = $(element).attr('name');
		var timeField = $(element).closest('li').find('input[name='+ name +'Time]').val();

		if (timeField == "") {

			return true;

		} else {

			if ($(element).parent().find("[aria-pressed='true']").length > 0) {

				return true;

			} else {

				return false;

			}

		}

		$(element).validationEngine('showPrompt', $.sc.locale("schedulecreate.form.lightsunset.required"), 'red', '', true);

		return false;
	},

	getScheduleRequest : function() {

		var scheduleName = $("#schedule-name").val();
		var scheduleType = $("#schedule-type").val();
		var sDescription = $('#schedule-description').val();
		var scheduleId = $("#schedule-id").val() == "" ? scheduleId = null : $("#schedule-id").val();
		var sunsetIntensity = parseInt($("#sunset-intensity").find(":selected").val());
		var sunriseMinutes = $('#offsetSunriseTime').val();
		var sunsetMinutes = $('#offsetSunsetTime').val();
		var sunriseBefore = $('#switch-sunrise input:checked').val();
		var sunsetBefore = $('#switch-sunset input:checked').val();
		var message = '';

		var oOffsetSchedule = new offsetSchedule(scheduleId, sDescription, scheduleName, 'OFFSET', sunriseMinutes, sunsetMinutes, sunriseBefore, sunsetBefore, sunsetIntensity);

		var oScheduleRequest = new scheduleRequest(null, null, null, null, null, null, oOffsetSchedule, 'offsetSchedule');

		return oScheduleRequest;

	},

	/**
	* Ajax call tha will submit the request ajax
	*/
	ajaxCall : function (urlAdress) {

		$.sc.startProgressBar(null,true);

		$.ajax( {
			url : urlAdress,
			type : 'POST',
			contentType : 'application/json',
			data : $.toJSON({'scheduleRequest' : sensus.pages.schedule.getScheduleRequest()}),

			/**
			 * The success handler. This will be invoked if the HTTP request
			 * returned correctly. The status of the reuested operation
			 * needs to be determined from the response object.
			 */
			success : function(data) {

				/* If successful, load the main schedule page */
				if (data.operationSuccess == true) {

					if ($("#schedule-id").val() == '') {

						message = $.sc.locale("action.addschedule.success", [ $("#schedule-name").val(), sensus.settings.smartpointUrl ]);

					} else {

						message = $.sc.locale("action.updateschedule.success", [ $("#schedule-name").val(), sensus.settings.smartpointUrl ]);

					}

					sensus.commons.lib.ajax.do_load($.extend({}, sensus.commons.lib.ajax.param, {
						sUrl : 'schedule/ajax.list.action',
						message : {
							bMessage : true,
							sMessage : message
						}
					}));

				} else {

					/* Show error message */
					if (!data.messages) {

						if ($("#schedule-id").val() == '') {

							message = $.sc.locale("action.addschedule.error");

						} else {

							message = $.sc.locale("action.updateschedule.error");

						}

						$.sc.showMessage("messaging-main", data.messageList[0].text, "error");

					} else {

						$.sc.showMessage("messaging-main", ' ' + data.messages, "error");

					}

					$.sc.stopProgressBar(null,true);

				}
			}
		});
	},

	processSchedule : function (oJson) {

		var message = '';
		/* If successful, load the main schedule page */
		if (oJson.operationSuccess == true) {

			if ($("#schedule-id").val() == '') {

				message = $.sc.locale("action.addschedule.success", [ $("#schedule-name").val(), sensus.settings.smartpointUrl ]);

			} else {

				message = $.sc.locale("action.updateschedule.success", [ $("#schedule-name").val(), sensus.settings.smartpointUrl ]);

			}

			$.sc.getPage($.extend({}, sensus.commons.lib.ajax.param, {
				sUrl : 'schedule',
				message : {
					bMessage : true,
					sMessage : message,
					status : 'success'
				}
			}));

		} else {

			/* Show error message */
			if (!oJson.messages) {

				if ($("#schedule-id").val() == '') {

					message = $.sc.locale("action.addschedule.error");

				} else {

					message = $.sc.locale("action.updateschedule.error");

				}

				$.sc.showMessage("messaging-main", oJson.messageList[0].text, "error");

			} else {

				$.sc.showMessage("messaging-main", ' ' + oJson.messages, "error");

			}

		}

	},

	createSchedule : function() {

		var oRequest = sensus.pages.schedule.getScheduleRequest();
		$.sc.getJson("api/schedule/insert", oRequest, false, sensus.pages.schedule.processSchedule, $.sc.locale("action.longRunningProcessDialog.confirm"));

	},

	updateSchedule : function() {

		var oRequest = sensus.pages.schedule.getScheduleRequest();

		if (sensus.pages.schedule.changed == true) {

			$.sc.getJson("api/schedule/update", oRequest, false, sensus.pages.schedule.processSchedule, $.sc.locale("action.longRunningProcessDialog.confirm"), false);

		} else {

			$.sc.getJson("api/schedule/update", oRequest, false, sensus.pages.schedule.processSchedule, $.sc.locale("action.longRunningProcessDialog.confirm"));

		}
	}
};
</script>
</sec:authorize>
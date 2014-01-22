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

	/** Validate Sunrise and Sunset time */
	validateSunriseSunset : function(element) {

		if ($(element).length)
		{
			var value = $(element).val();

			if (value % 5 == 0)
			{
				return true;
			}
			else
			{
				$(element).validationEngine('showPrompt', $.sc.locale("schedulecreate.form.timeSunsetSunrise"), 'red', '', true);

				return false;
			}
		}
		else
		{
			return true;
		}
	},

	/**
	* Generate Offset Schedule
	*/
	getOffsetScheduleRequest : function() {

		var scheduleName    = $("#schedule-name").val();
		var scheduleType    = $("#schedule-type").val();
		var sDescription    = $('#schedule-description').val();
		var scheduleId      = $("#schedule-id").val() == "" ? scheduleId = null : $("#schedule-id").val();
		var sunsetIntensity = parseInt($("#sunset-intensity").find(":selected").val());
		var sunriseMinutes  = $('#offsetSunriseTime').val();
		var sunsetMinutes   = $('#offsetSunsetTime').val();
		var sunriseBefore   = $('#switch-sunrise input:checked').val();
		var sunsetBefore    = $('#switch-sunset input:checked').val();
		var message = '';

		var oOffsetSchedule = new offsetSchedule(scheduleId, sDescription, scheduleName, 'OFFSET', sunriseMinutes, sunsetMinutes, sunriseBefore, sunsetBefore, sunsetIntensity);

		var oScheduleRequest = new scheduleRequest(null, null, null, null, null, null, oOffsetSchedule, 'offsetSchedule');

		return oScheduleRequest;

	},

	/**
	* Schedule Callback function
	*/
	fnCallBack : function (oResponse) {

		var message = '';

		/* If successful, load the main schedule page */
		if (oResponse.operationSuccess) {

			if ($("#schedule-id").val() == '')
			{
				message = $.sc.locale("action.addschedule.success", [ $("#schedule-name").val(), "light" ]);
			}
			else
			{
				message = $.sc.locale("action.updateschedule.success", [ $("#schedule-name").val(), "light" ]);
			}

			$.sc.getPage($.extend({}, sensus.commons.lib.ajax.param, {
				sUrl : 'systemintelligence/schedule',
				message : {
					bMessage : true,
					sMessage : message,
					status : 'success'
				}
			}));
		}
		else
		{
			/* Show error message */
			if (!oResponse.messages)
			{
				if ($("#schedule-id").val() == '')
				{
					message = $.sc.locale("action.addschedule.error");
				}
				else
				{
					message = $.sc.locale("action.updateschedule.error");
				}

				$.sc.showMessage("messaging-main", oResponse.messageList[0].text, "error");
			}
			else
			{
				$.sc.showMessage("messaging-main", ' ' + oResponse.messages, "error");
			}
		}
	},

	/**
	* Create Schedule
	*/
	createSchedule : function()
	{
		$.sc.getJson("api/schedule/insert", sensus.pages.schedule.getOffsetScheduleRequest(), false, sensus.pages.schedule.fnCallBack, $.sc.locale("action.longRunningProcessDialog.confirm"));
	},

	/**
	* Update Schedule
	*/
	updateSchedule : function()
	{
		var oRequest = sensus.pages.schedule.getOffsetScheduleRequest();

		if (sensus.pages.schedule.changed)
		{
			$.sc.getJson("api/schedule/update", oRequest, false, sensus.pages.schedule.fnCallBack, $.sc.locale("action.longRunningProcessDialog.confirm"), false);
		}
		else
		{
			$.sc.getJson("api/schedule/update", oRequest, false, sensus.pages.schedule.fnCallBack, $.sc.locale("action.longRunningProcessDialog.confirm"));
		}
	}
};
</script>
</sec:authorize>
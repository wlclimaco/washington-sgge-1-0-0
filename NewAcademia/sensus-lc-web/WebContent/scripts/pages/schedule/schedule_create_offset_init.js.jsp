<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager')">
<script type="text/javascript">
/**
 * @namespace sensus.pages.schedule

 * @description The creat-init namespace for the Schedule Page.
 */


/**
 * @fileoverview Initializes the Create schedule Page.
 * @author Anke Doerfel-Parker
 */

/*
 * Initialize the page.
 */

$(document).ready(function($) {

	/*
	 * Add one empty schedule event. The event HTML template is loaded via an
	 * Ajax call to a Struts action.
	 */
	if ($.address.parameter('id'))
	{

		//	Add id to form
		$("#schedule-list form").attr("id", "update-schedule-offset-form");
		//	Add id to button
		$("#create-schedule").val($.sc.locale("schedulecreate.actions.editOffsetSchedule"));
		//	Add class editOffsetSchedule to button submit
		$("#create-schedule").addClass("editOffsetSchedule");

		var request = new scheduleRequest($.address.parameter('id'));

		var fnCallback = function(data)
		{
			//	Add headder title
			$(".content-header h1").html($.sc.locale("schedulecreate.page.header.updateOffset") + " \"" +data.schedules[0].name+ "\"");

			$("#schedule-id").val(data.schedules[0].id);
			$("#smartpoint-count").val(data.schedules[0].smartPointCount);
			$('#schedule-name').val(data.schedules[0].name);
			$('#schedule-description').val(data.schedules[0].description);
			$('#offsetSunsetTime').val(data.schedules[0].sunsetOffsetMinutes);
			$('#offsetSunriseTime').val(data.schedules[0].sunriseOffsetMinutes);
			$('#switch-sunset input, #switch-sunrise input').prop('checked', false);
			$('#sunset-intensity option[value="' + data.schedules[0].intensity + '"]').prop('selected', true);
			$('#switch-sunset label, #switch-sunrise label').removeClass('ui-state-active');

			if ($('#offsetSunsetTime').val() == 0 && $('#offsetSunriseTime').val() == 0)
			{
				$('#offsetSunsetAfter').prop('checked', true);
				$('#offsetSunsetAfter').next().addClass('ui-state-active');
				$('#offsetSunriseBefore').prop('checked', true);
				$('#offsetSunriseBefore').next().addClass('ui-state-active');
			}
			else
			{
				if (data.schedules[0].sunsetBefore)
				{
					$('#offsetSunsetBefore').prop('checked', true);
					$('#offsetSunsetBefore').next().addClass('ui-state-active');
				}
				else
				{
					$('#offsetSunsetAfter').prop('checked', true);
					$('#offsetSunsetAfter').next().addClass('ui-state-active');
				}

				if (data.schedules[0].sunriseBefore)
				{
					$('#offsetSunriseBefore').prop('checked', true);
					$('#offsetSunriseBefore').next().addClass('ui-state-active');
				}
				else
				{
					$('#offsetSunriseAfter').prop('checked', true);
					$('#offsetSunriseAfter').next().addClass('ui-state-active');
				}
			}
		};

		<c:choose>
			<c:when test="${empty response}">
				var oPreLoadResponse = null;
			</c:when>
			<c:otherwise>
				var oPreLoadResponse = ${response};
			</c:otherwise>
		</c:choose>
		if(!$.sc.isValidPreLoad(oPreLoadResponse, true))
		{

			$.sc.getJson('api/schedule/fetch', request, false, fnCallback, null, null, null);
		}
		else
		{
			fnCallback(oPreLoadResponse);
		}


	}
	else
	{
		//	Add id to form
		$("#schedule-list form").attr("id", "create-schedule-offset-form");
		//	Add id to button
		$("#create-schedule").val($.sc.locale("schedulecreate.actions.createschedule"));
		//	Add headder title
		$(".content-header h1").html($.sc.locale("schedulecreate.page.header.offset"));

	}

	/*
	 * Set up create schedule button.
	 */
	$("a.button").button();
	$('#switch-sunset').buttonset();
	$('#switch-sunrise').buttonset();

	/*
	 * Set up radio schedule button when none is checked.
	 */
	if (!$('#offsetSunsetBefore').is(":checked") && !$('#offsetSunsetAfter').is(":checked"))
	{
		$('#offsetSunsetAfter').prop('checked', true);
    }

	if (!$('#offsetSunriseBefore').is(":checked") && !$('#offsetSunriseAfter').is(":checked"))
	{
		$('#offsetSunriseBefore').prop("checked", true);
    }

	/*
	 * Create the new schedule form elements
	 */
	$("#create-schedule-offset-form").validationEngine('attach', {
		validationEventTrigger: 'submit',
		onValidationComplete: function(form, status){

			var validSunset  = sensus.pages.schedule.validateSunriseSunset("#offset #offsetSunsetTime"),
				validSunrise = sensus.pages.schedule.validateSunriseSunset("#offset #offsetSunriseTime");

			if (validSunset && validSunrise && status)
			{
				sensus.pages.schedule.createSchedule();
			}
		}
	});

	$("#update-schedule-offset-form").validationEngine('attach', {
		validationEventTrigger: 'submit',
		onValidationComplete: function(form, status){

			var validSunset = sensus.pages.schedule.validateSunriseSunset("#offset #offsetSunsetTime"),
				validSunrise = sensus.pages.schedule.validateSunriseSunset("#offset #offsetSunriseTime");

			if (validSunset && validSunrise && status)
			{
				sensus.pages.schedule.updateSchedule();
			}
		}
	});

	var sunriseTime     = $("[name=offsetSunriseTime]").val();
	var sunsetTime      = $("[name=offsetSunsetTime]").val();
	var switchSunrise   = $("#switch-sunrise").find(":checked").val();
	var switchSunset    = $("#switch-sunset").find(":checked").val();
	var offsetIntensity = $("#sunset-intensity").val();

	$(".editOffsetSchedule").click(function()
	{
		if ($("#sunset-intensity").val() != offsetIntensity || $("[name=offsetSunriseTime]").val() != sunriseTime || $("[name=offsetSunsetTime]").val() != sunsetTime ||
			$("#switch-sunrise").find(":checked").val() != switchSunrise || $("#switch-sunset").find(":checked").val() != switchSunset)
		{
			sensus.pages.schedule.changed = true;
		}
		else
		{
			sensus.pages.schedule.changed = false;
		}
	});

	$.sc.stopGlobalProgressBar();

});
</script>
</sec:authorize>
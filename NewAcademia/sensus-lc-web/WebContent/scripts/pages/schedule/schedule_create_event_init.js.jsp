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
	 * Set up most page components.
	 */

	 /*
	 * Set up create schedule button.
	 */
	$("a.button").button();

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

	var buttonsets = $(".switch").buttonset();

	$(".range-finder").change(function()
	{
		sensus.pages.schedule.customRepeatDisplay(this);
	});

	/*
	 * Create the events
	 */
	$('#event-add-link').click(function()
	{
		sensus.pages.schedule.event.addEvent();
		return false;
	});

	/*
	 * Add one empty schedule event. The event HTML template is loaded via an
	 * Ajax call to a Struts action.
	 */
	if($.address.parameter('id'))
	{
		//	Add id to form
		$("#schedule-list form").attr("id", "update-schedule-event-form");
		//	Add id to button
		$("#create-schedule-event").val($.sc.locale("schedulecreate.actions.editEventSchedule"));

		$("#create-schedule-event").addClass("edit-event-schedule");

		var request = new scheduleRequest($.address.parameter('id'));

		var fnCallback = function(data)
		{
			var aEvents = data.eventSchedules[0].events;
			var sTitle = $.sc.locale("schedulecreate.page.header.updateEvent") + " \"" +data.eventSchedules[0].name+ "\"";

			$('#schedule-name').val(data.eventSchedules[0].name);
			$('#schedule-description').val(data.eventSchedules[0].description);
			$('.content-header h1').text(sTitle);

			if(aEvents)
			{
				for (var i = 0; i < aEvents.length; i++)
				{
					$.when(sensus.pages.schedule.event.addEvent()).done(function()
					{
						var index = (i+1);

						$('#time'+index).val(($.sc.dateFormat(aEvents[i].time, "h:i A")).trim());

						if (aEvents[i].lightBlinkEnum != 'NONE')
						{
							sBlink = aEvents[i].lightBlinkEnum;

							$('#radio_light'+index+' :input').eq(2).prop('checked',true).next().addClass('ui-state-active').find('.label-desc').html('<strong> '+sBlink+'</strong>');
						}
						else
						{
							if (aEvents[i].intensity == 0)
							{
								$('#radio_light'+index+' :input').eq(0).prop('checked',true).next().addClass('ui-state-active');
							}
							else if (aEvents[i].intensity > 0 && aEvents[i].intensity < 100)
							{
								$('#radio_light'+index+' :input').eq(1).prop('checked',true).next().addClass('ui-state-active').find('.label-desc').html('<strong> '+aEvents[i].intensity+'%</strong>');
							}
							else if(aEvents[i].intensity == 100)
							{
								$('#radio_light'+index+' :input').eq(5).prop('checked',true).next().addClass('ui-state-active');
							}
						}

						var aDays = aEvents[i].days;
						for (var k = 0; k < aDays.length; k++)
						{
							$('#event-repeat'+index+' :input[value="'+aDays[k]+'"]').prop('checked',true).next().addClass('ui-state-active');
						}
					});
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
			$.sc.getJson('api/schedule/fetch', request, false, fnCallback, null, null, null, null);

		}
		else
		{
			fnCallback(oPreLoadResponse);
		}

	}
	else
	{
		//	Add Title
		var sTitle = $.sc.locale("schedulecreate.page.header.event");
		$('.content-header h1').text(sTitle);

		//	Add id to form
		$("#schedule-list form").attr("id", "create-schedule-event-form");
		//	Add id to button
		$("#create-schedule-event").val($.sc.locale("schedulecreate.actions.createschedule"));

		sensus.pages.schedule.event.addEvent();
	}

	/*
	 * Create the new schedule form elements
	 */
	$("#create-schedule-event-form").validationEngine('attach', {
		validationEventTrigger : 'submit',
		onValidationComplete   : function(form, status) {
			var validSunset = sensus.pages.schedule.validateSunriseSunset("#offset #offsetSunsetTime");
			var validSunrise = sensus.pages.schedule.validateSunriseSunset("#offset #offsetSunriseTime");

			if (validSunset && validSunrise && status) {
				sensus.pages.schedule.event.createSchedule();
			}
		}
	});

	$("#update-schedule-event-form").validationEngine('attach', {
		validationEventTrigger: 'submit',
		onValidationComplete: function(form, status){
			var validWeekday = sensus.pages.schedule.validateWeekday();
			var validSunset = sensus.pages.schedule.validateSunriseSunset("#offset #offsetSunsetTime");
			var validSunrise = sensus.pages.schedule.validateSunriseSunset("#offset #offsetSunriseTime");

			if (validSunset && validSunrise && status)
			{
				sensus.pages.schedule.updateSchedule();
			}
		}
	});

	$(".event-day-custom input").live("click", function() {
		if ($(this).attr("checked"))
		{
			$(this).parent().find(".error").hide();
		}
	});

	var sunriseTime     = $("[name=offsetSunriseTime]").val();
	var sunsetTime      = $("[name=offsetSunsetTime]").val();
	var switchSunrise   = $("#switch-sunrise").find(":checked").val();
	var switchSunset    = $("#switch-sunset").find(":checked").val();
	var offsetIntensity = $("#sunset-intensity").val();

	var numberEvents = $(".events-number").size();

	$(".edit-event-schedule").click(function()
	{
		if ($("#event-add > div").size() != numberEvents)
		{
			sensus.pages.schedule.event.changed = true;
		}
		else
		{
			var countEvents = 1;
			var isChanged = false;

			$(".events-number").each(function()
			{
				var eventNumber = $("#event-add > div").eq(countEvents - 1).attr("id").substring(5);

				if ($(".list-events" + countEvents).find(".event-time").text() != $("#time" + eventNumber).val())
				{
					isChanged = true;
				}

				if ($(".list-events" + countEvents).find(".event-status").text() !== $("#dimmer" + countEvents, "#event" + countEvents).find(":selected").val())
				{
					isChanged = true;
				}

				var days = [];
				var countDays = 0;
				var currentDays = [];
				var currentCountDays = 0;

				$(".list-events" + countEvents).find(".event-day").each(function() {

					days[countDays] = $(this).text();
					countDays ++;

				});

				$("#event-repeat" + countEvents).find(":checked").each(function() {

					currentDays[currentCountDays] = $(this).val();
					currentCountDays ++;

				});

				for (var c in currentDays)
				{
					if (currentDays.hasOwnProperty(c))
					{
						if ($.inArray(currentDays[c], days) < 0)
						{
							isChanged = true;
						}
					}

				};

				for (var d in days)
				{
					if (days.hasOwnProperty(d))
					{
						if ($.inArray(days[d], currentDays) < 0)
						{
							isChanged = true;
						}
					}
				};

				countEvents ++;
			});

			if (isChanged)
			{
				sensus.pages.schedule.event.changed = true;
			}
			else
			{
				sensus.pages.schedule.event.changed = false;
			}
		}
	});

	$.sc.stopGlobalProgressBar();
});
</script>
</sec:authorize>
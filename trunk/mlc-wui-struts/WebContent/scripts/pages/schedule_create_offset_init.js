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
	if ($.address.parameter('id')) {

		var request = {'scheduleRequest' : new scheduleRequest($.address.parameter('id'))};
		
		$.ajax( {
			url          : 'schedule/ajax.fillOffsetSchedule.action',
			type         : "POST",
			data         : $.toJSON(request),				
			dataType     : 'json',
			contentType  : "application/json; charset=utf-8",
			success      : function(data) {

				var sName = $('.content-header h1');
				sName.html(sName.text().trim().replace('""','"' + data.schedules[0].name + '"'));
				
				$("#schedule-id").val(data.schedules[0].id);
				$("#smartpoint-count").val(data.schedules[0].smartPointCount);
				$('#schedule-name').val(data.schedules[0].name);
				$('#schedule-description').val(data.schedules[0].description);
				$('#offsetSunsetTime').val(data.schedules[0].sunsetOffsetMinutes);
				$('#offsetSunriseTime').val(data.schedules[0].sunriseOffsetMinutes);
				$('#switch-sunset input, #switch-sunrise input').prop('checked', false);
				$('#sunset-intensity option[value="' + data.schedules[0].intensity + '"]').prop('selected', true);
				$('#switch-sunset label, #switch-sunrise label').removeClass('ui-state-active');

				if ($('#offsetSunsetTime').val() == 0 && $('#offsetSunriseTime').val() == 0) {
				
					$('#offsetSunsetAfter').prop('checked', true);
					$('#offsetSunsetAfter').next().addClass('ui-state-active');
					$('#offsetSunriseBefore').prop('checked', true);
					$('#offsetSunriseBefore').next().addClass('ui-state-active');
				
				} else {
				
					if (data.schedules[0].sunsetBefore) {
						$('#offsetSunsetBefore').prop('checked', true);
						$('#offsetSunsetBefore').next().addClass('ui-state-active');
					} else {
						$('#offsetSunsetAfter').prop('checked', true);
						$('#offsetSunsetAfter').next().addClass('ui-state-active');
					}
					
					if (data.schedules[0].sunriseBefore) {
						$('#offsetSunriseBefore').prop('checked', true);
						$('#offsetSunriseBefore').next().addClass('ui-state-active');
					} else {
						$('#offsetSunriseAfter').prop('checked', true);
						$('#offsetSunriseAfter').next().addClass('ui-state-active');
					}
				
				}
				
			}
		});
	}
	
	/*
	 * Set up most page components.
	 */
	sensus.util.page.stopProgressBar(null,true);
	sensus.util.page.initMessaging();
	
	/*
	 * Set up create schedule button.
	 */
	$("a.button").button();
	$('#switch-sunset').buttonset();
	$('#switch-sunrise').buttonset();
	
	/*
	 * Set up radio schedule button when none is checked.
	 */
	if (!$('#offsetSunsetBefore').is(":checked") && !$('#offsetSunsetAfter').is(":checked")) {

		$('#offsetSunsetAfter').prop('checked', true);
		
    }

	if (!$('#offsetSunriseBefore').is(":checked") && !$('#offsetSunriseAfter').is(":checked")) {
		
		$('#offsetSunriseBefore').prop("checked", true);
		
    }

	/*
	 * Create the new schedule form elements
	 */
	$("#create-schedule-form").validationEngine('attach', {
		validationEventTrigger: 'submit',
		onValidationComplete: function(form, status){
			
			var validSunset = sensus.pages.schedule.validateSunriseSunset("#offset #offsetSunsetTime"),
				validSunrise = sensus.pages.schedule.validateSunriseSunset("#offset #offsetSunriseTime");
			
			if (validSunset && validSunrise && status) {
				sensus.pages.schedule.createSchedule();
			}
		}
	});
	
	$("#update-schedule-form").validationEngine('attach', {
		validationEventTrigger: 'submit',
		onValidationComplete: function(form, status){
			
			var validSunset = sensus.pages.schedule.validateSunriseSunset("#offset #offsetSunsetTime"),
				validSunrise = sensus.pages.schedule.validateSunriseSunset("#offset #offsetSunriseTime");
			
			if (validSunset && validSunrise && status) {
				sensus.pages.schedule.updateSchedule();
			}
		}
	});
	
	var sunriseTime = $("[name=offsetSunriseTime]").val();
	var sunsetTime = $("[name=offsetSunsetTime]").val();
	var switchSunrise = $("#switch-sunrise").find(":checked").val();
	var switchSunset = $("#switch-sunset").find(":checked").val();
	var offsetIntensity =  $("#sunset-intensity").val();
	
	$(".editOffsetSchedule").click(function() {	
		
		if ($("#smartpoint-count").val() > 0) {

			if ($("#sunset-intensity").val() != offsetIntensity || $("[name=offsetSunriseTime]").val() != sunriseTime || $("[name=offsetSunsetTime]").val() != sunsetTime || 
				$("#switch-sunrise").find(":checked").val() != switchSunrise || $("#switch-sunset").find(":checked").val() != switchSunset) {

				sensus.pages.schedule.changed = true;
				
			} else {

				sensus.pages.schedule.changed = false;
				
			}
		}	
	});	

	sensus.util.page.stopProgressBar(0,true);	
	
});


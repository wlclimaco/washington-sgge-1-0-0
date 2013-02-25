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
			
				$(element).validationEngine('showPrompt', sensus.locale.get("schedulecreate.form.timeSunsetSunrise"), 'red', '', true);
				
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

		$(element).validationEngine('showPrompt', sensus.locale.get("schedulecreate.form.lightsunset.required"), 'red', '', true);

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

		sensus.util.page.startProgressBar(null,true);
				
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
					
						message = sensus.locale.get("action.addschedule.success", [ $("#schedule-name").val(), sensus.settings.smartpointUrl ]);
						
					} else {
					
						message = sensus.locale.get("action.updateschedule.success", [ $("#schedule-name").val(), sensus.settings.smartpointUrl ]);
						
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
						
							message = sensus.locale.get("action.addschedule.error");
							
						} else {
						
							message = sensus.locale.get("action.updateschedule.error");
							
						}
						
						sensus.util.page.showMessage("messaging-main", data.messageList[0].text, "error");
					
					} else {
					
						sensus.util.page.showMessage("messaging-main", ' ' + data.messages, "error");
						
					}
					
					sensus.util.page.stopProgressBar(null,true);
					
				}
			}
		});
	},	
	
	processSchedule : function (oRequest) {

		var message = '';
		/* If successful, load the main schedule page */
		if (oRequest.operationSuccess == true) {

			if ($("#schedule-id").val() == '') {
			
				message = sensus.locale.get("action.addschedule.success", [ $("#schedule-name").val(), sensus.settings.smartpointUrl ]);
				
			} else {
			
				message = sensus.locale.get("action.updateschedule.success", [ $("#schedule-name").val(), sensus.settings.smartpointUrl ]);
				
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
			if (!oRequest.messages) {
			
				if ($("#schedule-id").val() == '') {
				
					message = sensus.locale.get("action.addschedule.error");
					
				} else {
				
					message = sensus.locale.get("action.updateschedule.error");
					
				}
				
				sensus.util.page.showMessage("messaging-main", oRequest.messageList[0].text, "error");
			
			} else {
			
				sensus.util.page.showMessage("messaging-main", ' ' + oRequest.messages, "error");
				
			}
						
		}

	},	

	createSchedule : function() {
	
		var oRequest = {'scheduleRequest' : sensus.pages.schedule.getScheduleRequest()};
		$.ajaxValidator.fnDoCall(sensus.settings.createUrl, oRequest, false, sensus.pages.schedule.processSchedule, sensus.locale.get("action.longRunningProcessDialog.confirm"));	
		
	},
	
	updateSchedule : function() {
	
		var oRequest = {'scheduleRequest' : sensus.pages.schedule.getScheduleRequest()};
		
		if (sensus.pages.schedule.changed == true) {		
			
			$.ajaxValidator.fnMonitor(sensus.settings.updateScheduleUrl, oRequest, false, sensus.pages.schedule.processSchedule, sensus.locale.get("action.longRunningProcessDialog.confirm"), false);
						
		} else {
		
			$.ajaxValidator.fnDoCall(sensus.settings.updateScheduleUrl, oRequest, false, sensus.pages.schedule.processSchedule, sensus.locale.get("action.longRunningProcessDialog.confirm"));
			
		}
	}
};
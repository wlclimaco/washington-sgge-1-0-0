sensus.commons.modules.content.lightSchedule = {

	fnReloadSchedule: function(aData) {

		$('.missing_data').hide();

		if(sensus.settings.timezone != aData.timeZone){
			
			sensus.commons.modules.util.fnFillData($('.missing_data'), [sensus.locale.get('smartpointdetail.differenttimezone')+ ' '+sensus.settings.timezone]);
			$('.missing_data').show();

		} else {

			$('span.missing_data').remove();

		}

		var sSunSetValue = '2012-01-10T'+aData.sunsetTime+'.000';
		var sSunRiseValue = '2012-01-10T'+aData.sunriseTime+'.000';

		sensus.commons.modules.util.fnFillData($('#sunrise-time'), [ $.date.dateFormat( sSunRiseValue, 'h:i A')]);
		sensus.commons.modules.util.fnFillData($('#sunset-time'), [ $.date.dateFormat( sSunSetValue, 'h:i A')]);

		var sHtmlOffSet  = '';
		var sTypeSunset  = sensus.locale.get('smartpointdetail.sunrisesunset.aftersunset');
		var sTypeSunsire = sensus.locale.get('smartpointdetail.sunrisesunset.aftersunrise');
		
		if(aData.offSetSchedule){

			if(aData.offSetSchedule.name != null){

				if (aData.offSetSchedule.sunsetOffsetMinutes) {

					if (aData.offSetSchedule.sunsetBefore) {
						sTypeSunset = sensus.locale.get('smartpointdetail.sunrisesunset.beforesunset');
					} 
					
					sensus.commons.modules.util.fnFillData($('.sunsetoffset'), [sensus.locale.get('smartpointdetail.sunrisesunset.turnlightson')+ ' '+ aData.offSetSchedule.sunsetOffsetMinutes + sTypeSunset]);

				}

				if (aData.offSetSchedule.sunriseOffsetMinutes) {

					if (aData.offSetSchedule.sunriseBefore) {
						sTypeSunsire = sensus.locale.get('smartpointdetail.sunrisesunset.beforesunrise');
					}

					sensus.commons.modules.util.fnFillData($('.sunriseoffset'), [sensus.locale.get('smartpointdetail.sunrisesunset.turnlightsoff')+ ' '+ aData.offSetSchedule.sunriseOffsetMinutes + sTypeSunsire]);

				}

				if(aData.offSetSchedule.name == 'Unknown Offset Schedule'){

					sHtmlOffSet = '<div class="left">'+aData.offSetSchedule.name+'</div>';

					if (sensus.settings.userContext.userRole == "ROLE_Role.Admin" || sensus.settings.userContext.userRole == "ROLE_Role.System Operator" && aData.currentStatusMessage.lightStatusEnumValue != 4){

						sHtmlOffSet += '<a href="#" class="button small right edit-context apply-offset-schedule">'+sensus.locale.get('smartpointdetail.sunrisesunset.applyoffsetschedule')+'</a>';

					}

				} else {
					
					if (sensus.settings.userContext.userRole == "ROLE_Role.Admin") {
						sHtmlOffSet = '<div class="left"><a class="alist" id="smartpointdetail-update" href="schedule/ajax.createOffsetSchedule.action?id='+aData.offSetSchedule.id+'">'+aData.offSetSchedule.name+'</a></div>';
					} else {
						sHtmlOffSet = '<div class="left">' + aData.offSetSchedule.name + '</div>';
					}
					
					if (sensus.settings.userContext.userRole == "ROLE_Role.Admin" || sensus.settings.userContext.userRole == "ROLE_Role.System Operator"){

						sHtmlOffSet += '<a id="offset-schedule-'+aData.offSetSchedule.id+'" href="#" class="button small right remove-offset-schedule">'+sensus.locale.get("smartpointdetail.sunrisesunset.removeoffsetschedule")+'</a>';

					}

				}

			}

		} else {

			sHtmlOffSet = '<div class="left">'+sensus.locale.get('smartpointdetail.status.noneapplied')+'</div>';

			if (sensus.settings.userContext.userRole == "ROLE_Role.Admin" || sensus.settings.userContext.userRole == "ROLE_Role.System Operator"){

				if(aData.currentStatusMessage.lightStatusEnumValue != 4){

					sHtmlOffSet += '<a href="#" class="button small right edit-context apply-offset-schedule">'+sensus.locale.get('smartpointdetail.sunrisesunset.applyoffsetschedule')+'</a>';

				}

			}

		}

		sensus.commons.modules.util.fnFillData($('#sunrise-sunset-offset'), [sHtmlOffSet]);

		var sHtmlEvent = '';
		if(aData.eventSchedule){

			if(aData.eventSchedule.name != null){

				if(aData.eventSchedule.name == 'Unknown Event Schedule'){

					sHtmlEvent = '<div class="left">'+aData.eventSchedule.name+'</div>';

					if (sensus.settings.userContext.userRole == "ROLE_Role.Admin" || sensus.settings.userContext.userRole == "ROLE_Role.System Operator"){

						sHtmlEvent += '<a href="#" class="button small right edit-context apply-event-schedule">'+sensus.locale.get('smartpointdetail.sunrisesunset.applyeventschedule')+'</a>';

					}

				} else {

					if (sensus.settings.userContext.userRole == "ROLE_Role.Admin") {

						sHtmlEvent = '<div class="left"><a class="alist" id="smartpointdetail-update" href="schedule/ajax.createEventSchedule.action?id='+aData.eventSchedule.id+'">'+aData.eventSchedule.name+'</a></div>';

					} else {

						sHtmlEvent = '<div class="left">' + aData.eventSchedule.name + '</div>';

					}
					
					if (sensus.settings.userContext.userRole == "ROLE_Role.Admin" || sensus.settings.userContext.userRole == "ROLE_Role.System Operator"){

						sHtmlEvent += '<a id="event-schedule-'+aData.eventSchedule.id+'" href="#" class="button small right remove-event-schedule">'+sensus.locale.get("smartpointdetail.sunrisesunset.removeeventschedule")+'</a>';

					}

				}

			}

		} else {

			sHtmlEvent = '<div class="left">'+sensus.locale.get('smartpointdetail.status.noneapplied')+'</div>';

			if (sensus.settings.userContext.userRole == "ROLE_Role.Admin" || sensus.settings.userContext.userRole == "ROLE_Role.System Operator"){

				if(aData.currentStatusMessage.lightStatusEnumValue != 4){

					sHtmlEvent += '<a href="#" class="button small right edit-context apply-event-schedule">'+sensus.locale.get('smartpointdetail.sunrisesunset.applyeventschedule')+'</a>';

				}

			}

		}

		sensus.commons.modules.util.fnFillData($('#sunrise-sunset-event'), [sHtmlEvent]);
		
		$(".button").button();

		if (aData.currentStatusMessage.lightStatusEnum == "DEACTIVATED") {
		
			$('a.button', '#sunrise-sunset').remove();
			
		}

		},
		loadSchedules : function(){
			var oRequest 	= {'inquiryScheduleRequest': new inquiryScheduleRequest(null)};
			var fnCallback = function(response){
				
				if($.ajaxValidator.fnIsNullOrUndefined(response)) {
					var sSelectOffset = '';
					var aOffset = response.offsetSchedules;
					for(var i=0; i<aOffset.length; i++){

						sSelectOffset += '<option value="'+aOffset[i].id+'">'+aOffset[i].name+'</option>';

					}
					$('#offset-schedule-add-select').empty().append(sSelectOffset);

					var sSelectEvent = '';
					var aEvent = response.offsetSchedules;
					for(i=0; i<aEvent.length; i++){

						sSelectEvent += '<option value="'+aEvent[i].id+'">'+aEvent[i].name+'</option>';

					}
					$('#event-schedule-add-select_input').empty().append(sSelectEvent);
				}
				
			};
			$.ajaxValidator.fnDoCall("schedule/search.action?", oRequest, false, fnCallback);
		},
		
		fnUpdateSchedule: function() {
			
			sensus.commons.modules.bForceReload = true;
			var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
			var oFirstMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
			sensus.commons.modules.content.lightSchedule.fnReloadSchedule(oFirstMeter);
			sensus.commons.modules.bForceReload = false;

		
		},
		
		init : function(){

			var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
			var oFirstMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
			sensus.commons.modules.content.lightSchedule.fnReloadSchedule(oFirstMeter);
			sensus.commons.modules.content.lightSchedule.loadSchedules();

		}

};

sensus.commons.modules.content.lightSchedule.init();

$('#offset-schedule-add-select').combobox( {
	zIndex : 3999
});

$("#offset-schedule-add-select_input").attr("value", sensus.locale.get("widgets.combobox.prompt.typetosearch"));

$('#offset-schedule-add-select_input').focus(function() {
	
	if ($(this).attr('value') == sensus.locale.get("widgets.combobox.prompt.typetosearch")) {
		
		$(this).attr('value', '');
		
	}
	
});

$('#event-schedule-add-select').combobox( {
	zIndex : 3999
});

$("#event-schedule-add-select_input").attr("value", sensus.locale.get("widgets.combobox.prompt.typetosearch"));

$('#event-schedule-add-select_input').focus(function() {
	if ($(this).attr('value') == sensus.locale.get("widgets.combobox.prompt.typetosearch")) {
		$(this).attr('value', '');
	}
});


//load apply offset schedule
$('#tabs-content').on('click', '.apply-offset-schedule', function() {

	$('#apply-offset-schedule-smartpoint').show();
	$('#sunrise-sunset-details').hide();
	return false;
});

//load apply event schedule
$('#tabs-content').on('click', '.apply-event-schedule', function() {
	$('#apply-event-schedule-smartpoint').show();
	$('#sunrise-sunset-details').hide();
	return false;
});

//remove smartpoint offset schedule
$('#tabs-content').on('click', '.remove-offset-schedule', function() {
	var nId 					= $(this).attr('id').split('-')[2];
	var aLights 				= [ $.address.parameter('id') ];
	var oEventSchedule 			= new eventSchedule(nId, null, null, 'offsetSchedule', null);
	var oSearchLight 			= { searchParameters :  []};
	var eventScheduleRequest 	= new scheduleRequest(null,null,null,null,null,null,oEventSchedule,'offsetSchedule', null, aLights, oSearchLight, null, null);
	var fnCallBack = sensus.commons.modules.content.lightSchedule.fnUpdateSchedule;
	
	$.ajaxValidator.fnMonitor(sensus.settings.removeSchedule, {'scheduleRequest' : eventScheduleRequest}, false, fnCallBack, sensus.locale.get("action.longRunningProcessDialog.confirm"), true);
	return false;
});

//remove smartpoint event schedule
$('#tabs-content').on('click', '.remove-event-schedule', function() {
	var nId 					= $(this).attr('id').split('-')[2];
	var aLights 				= [ $.address.parameter('id') ];
	var oEventSchedule 			= new eventSchedule(nId, null, null, 'eventSchedule', null);
	var oSearchLight 			= { searchParameters :  []};
	var eventScheduleRequest 	= new scheduleRequest(null,null,null,null,null,null,oEventSchedule,'eventSchedule', null, aLights, oSearchLight, null, null);
	var fnCallBack = sensus.commons.modules.content.lightSchedule.fnUpdateSchedule;

	$.ajaxValidator.fnMonitor(sensus.settings.removeSchedule, {'scheduleRequest' : eventScheduleRequest}, false, fnCallBack, sensus.locale.get("action.longRunningProcessDialog.confirm"), true);

	return false;
});

//Apply offset schedule
$('#tabs-content').on('click', '.apply-offset-schedule-action', function() {

	sensus.pages.longrunningprocess.bIsDetail = true;
	if (sensus.util.combobox.getOption("#offset-schedule-add-select") != undefined) {

		$('#apply-offset-schedule-smartpoint').hide();
		$('#sunrise-sunset-details').show();
		$('.formError').remove();

		var nId = sensus.util.combobox.getOption("#offset-schedule-add-select");
		var sScheduleName = $("#offset-schedule-add-select option[value='"+nId+"']").text();
		var aLights = [ $.address.parameter('id') ];
		var oEventSchedule = new eventSchedule(nId, null, null, 'offsetSchedule', null);
		var oSearchLight = { searchParameters :  []};
		var eventScheduleRequest = new scheduleRequest(nId,null,null,null,null,null,oEventSchedule,'offsetSchedule', null, aLights, oSearchLight, sScheduleName, null);

		$.ajaxValidator.fnMonitor(sensus.settings.applySchedule, {'scheduleRequest' : eventScheduleRequest}, false, sensus.commons.modules.content.lightSchedule.fnUpdateSchedule, sensus.locale.get("action.longRunningProcessDialog.confirm"), true);

	} else if (($("#offset-schedule-add-select_input").val() == sensus.locale.get("widgets.combobox.prompt.typetosearch"))
					|| ($("#offset-schedule-add-select_input").val() == "")) {

		$('#offset-schedule-add-select_input').validationEngine('showPrompt', sensus.locale.get("smartpointdetail.validation.validValue"), 'red', '', true);

	} else {

		$('#offset-schedule-add-select_input').validationEngine('showPrompt', sensus.locale.get("smartpoint.actions.applySchedule.not.exists", $("#offset-schedule-add-select_input").val()), 'red', '', true);

	}

	return false;
});

//Apply event schedule
$('#tabs-content').on('click', '.apply-event-schedule-action', function() {

	sensus.pages.longrunningprocess.bIsDetail = true;
	if (sensus.util.combobox.getOption("#event-schedule-add-select") != undefined) {
		$('#apply-event-schedule-smartpoint').hide();
		$('#sunrise-sunset-details').show();
		$('.formError').remove();
		var nId	= sensus.util.combobox.getOption("#event-schedule-add-select");
		var sScheduleName = $("#event-schedule-add-select option[value='"+nId+"']").text();
		var aLights = [ $.address.parameter('id') ];
		var oEventSchedule = new eventSchedule(nId, null, null, 'EVENT', null);
		var oSearchLight = { searchParameters :  []};
		var offsetScheduleRequest = new scheduleRequest(nId,null,null,null,null,null,oEventSchedule,'EVENT', null, aLights, oSearchLight, sScheduleName, null);

		$.ajaxValidator.fnMonitor(sensus.settings.applySchedule, {'scheduleRequest' : offsetScheduleRequest}, false, sensus.commons.modules.content.lightSchedule.fnUpdateSchedule, sensus.locale.get("action.longRunningProcessDialog.confirm"), true);

	} else if (($("#event-schedule-add-select_input").val() == sensus.locale.get("widgets.combobox.prompt.typetosearch"))
					|| ($("#event-schedule-add-select_input").val() == "")) {
		$('#event-schedule-add-select_input').validationEngine('showPrompt', sensus.locale.get("smartpointdetail.validation.validValue"), 'red', '', true);
	} else {
		$('#event-schedule-add-select_input').validationEngine('showPrompt', sensus.locale.get("smartpoint.actions.applySchedule.not.exists", $("#event-schedule-add-select_input").val()), 'red', '', true);
	}
	
	return false;
});

//hide load apply offset schedule div
$('#apply-offset-schedule-smartpoint .cancel-edit').click(function() {
	$('#apply-offset-schedule-smartpoint').hide();
	$("#offset-schedule-add-select_input").attr("value", sensus.locale.get("widgets.combobox.prompt.typetosearch"));
	$('#sunrise-sunset-details').show();
	return false;
});

//hide load apply event schedule div
$('#apply-event-schedule-smartpoint .cancel-edit').click(function() {
	$('#apply-event-schedule-smartpoint').hide();
	$("#event_schedule_add_select_input").attr("value", sensus.locale.get("widgets.combobox.prompt.typetosearch"));
	$('#sunrise-sunset-details').show();
	return false;
});

$(".button").button();
sensus.commons.modules.content.lightSchedule = {

	fnReloadSchedule: function(aData) {

			$('.missing_data').hide();

			var iLightTimeZone = aData.radio.location.timeZoneInfo.offsetInHours;

			if(aData.radio.location.timeZoneInfo && sensus.settings.TIME_ZONE_SHORT != aData.radio.location.timeZoneInfo.displayNameShort)
			{
				sensus.commons.modules.util.fnFillData($('.missing_data'), [$.sc.locale('smartpointdetail.differenttimezone') + aData.radio.location.timeZoneInfo.timeZone + '.']);
				$('.missing_data').show();
			}
			else
			{
				$('span.missing_data').remove();
			}

			if (aData.lightSchedule.sunsetTimeDate == null || aData.lightSchedule.sunriseTimeDate == null)
			{
				if(aData.lightSchedule.sunsetTime == null || aData.lightSchedule.sunriseTime == null)
				{
					var sSunSetValue = $.sc.locale('smartpointdetail.sunrisesunset.notset');
					var sSunRiseValue = $.sc.locale('smartpointdetail.sunrisesunset.notset');
				}
				else
				{
					var sSunSetValue = $.sc.dateFormat('2012-01-10T'+aData.lightSchedule.sunsetTime.split(" ")[1]+'.000', 'h:i A', null, false, iLightTimeZone);
					var sSunRiseValue = $.sc.dateFormat('2012-01-10T'+aData.lightSchedule.sunriseTime.split(" ")[1]+'.000', 'h:i A', null, false, iLightTimeZone);
				}
			}
			else
			{
				var sSunSetValue = $.sc.dateFormat(aData.lightSchedule.sunsetTimeDate, 'h:i A', null, false, iLightTimeZone);
				var sSunRiseValue = $.sc.dateFormat(aData.lightSchedule.sunriseTimeDate, 'h:i A', null, false, iLightTimeZone);
			}

			sensus.commons.modules.util.fnFillData($('#sunrise-time'), [ sSunRiseValue ]);
			sensus.commons.modules.util.fnFillData($('#sunset-time'), [ sSunSetValue ]);


			var sHtmlOffSet  = '';
			var sTypeSunset  = $.sc.locale('smartpointdetail.sunrisesunset.aftersunset');
			var sTypeSunsire = $.sc.locale('smartpointdetail.sunrisesunset.aftersunrise');

			if(aData.offsetSchedule)
			{
				if(aData.offsetSchedule.name != null)
				{

					if (aData.offsetSchedule.sunsetOffsetMinutes)
					{
						if (aData.offsetSchedule.sunsetBefore)
						{
							sTypeSunset = $.sc.locale('smartpointdetail.sunrisesunset.beforesunset');
						}

						sensus.commons.modules.util.fnFillData($('.sunsetoffset'), [$.sc.locale('smartpointdetail.sunrisesunset.turnlightson')+ ' '+ aData.offsetSchedule.sunsetOffsetMinutes + sTypeSunset]);
					}

					if (aData.offsetSchedule.sunriseOffsetMinutes)
					{
						if (aData.offsetSchedule.sunriseBefore)
						{
							sTypeSunsire = $.sc.locale('smartpointdetail.sunrisesunset.beforesunrise');
						}

						sensus.commons.modules.util.fnFillData($('.sunriseoffset'), [$.sc.locale('smartpointdetail.sunrisesunset.turnlightsoff')+ ' '+ aData.offsetSchedule.sunriseOffsetMinutes + sTypeSunsire]);
					}

					if(aData.offsetSchedule.name == 'Unknown Offset Schedule')
					{
						sHtmlOffSet = '<div class="left">'+aData.offsetSchedule.name+'</div>';

						if (sensus.settings.userContext.userRole == "ROLE_Role.Admin" || sensus.settings.userContext.userRole == "ROLE_Role.System Operator" && aData.lastNotificationHistory.lifeCycleStateValue != 4)
						{
							sHtmlOffSet += '<a href="#" class="button small right edit-context apply-offset-schedule">'+$.sc.locale('smartpointdetail.sunrisesunset.applyoffsetschedule')+'</a>';
						}
					}
					else
					{
						if (sensus.settings.userContext.userRole == "ROLE_Role.Admin")
						{
							sHtmlOffSet = '<div class="left"><a class="alist" id="smartpointdetail-update" href="systemintelligence/schedule/insertOffset?id='+aData.offsetSchedule.id+'">'+aData.offsetSchedule.name+'</a></div>';
						}
						else
						{
							sHtmlOffSet = '<div class="left">' + aData.offsetSchedule.name + '</div>';
						}

						if (sensus.settings.userContext.userRole == "ROLE_Role.Admin" || sensus.settings.userContext.userRole == "ROLE_Role.System Operator")
						{
							sHtmlOffSet += '<a id="offset-schedule-'+aData.offsetSchedule.id+'" href="#" class="button small right remove-offset-schedule">'+$.sc.locale("smartpointdetail.sunrisesunset.removeoffsetschedule")+'</a>';
						}
					}
				}
			}
			else
			{
				sHtmlOffSet = '<div class="left">'+$.sc.locale('smartpointdetail.status.noneapplied')+'</div>';

				if (sensus.settings.userContext.userRole == "ROLE_Role.Admin" || sensus.settings.userContext.userRole == "ROLE_Role.System Operator")
				{
					if(aData.lastNotificationHistory.lifeCycleStateValue != 4)
					{
						sHtmlOffSet += '<a href="#" class="button small right edit-context apply-offset-schedule">'+$.sc.locale('smartpointdetail.sunrisesunset.applyoffsetschedule')+'</a>';
					}
				}
			}

			sensus.commons.modules.util.fnFillData($('#sunrise-sunset-offset'), [sHtmlOffSet]);

			var sHtmlEvent = '';
			if(aData.eventSchedule)
			{
				if(aData.eventSchedule.name != null)
				{
					if(aData.eventSchedule.name == 'Unknown Event Schedule')
					{
						sHtmlEvent = '<div class="left">'+aData.eventSchedule.name+'</div>';

						if (sensus.settings.userContext.userRole == "ROLE_Role.Admin" || sensus.settings.userContext.userRole == "ROLE_Role.System Operator")
						{
							sHtmlEvent += '<a href="#" class="button small right edit-context apply-event-schedule">'+$.sc.locale('smartpointdetail.sunrisesunset.applyeventschedule')+'</a>';
						}
					}
					else
					{
						if (sensus.settings.userContext.userRole == "ROLE_Role.Admin")
						{
							sHtmlEvent = '<div class="left"><a class="alist" id="smartpointdetail-update" href="systemintelligence/schedule/insertEvent?id='+aData.eventSchedule.id+'">'+aData.eventSchedule.name+'</a></div>';
						}
						else
						{
							sHtmlEvent = '<div class="left">' + aData.eventSchedule.name + '</div>';
						}

						if (sensus.settings.userContext.userRole == "ROLE_Role.Admin" || sensus.settings.userContext.userRole == "ROLE_Role.System Operator")
						{
							sHtmlEvent += '<a id="event-schedule-'+aData.eventSchedule.id+'" href="#" class="button small right remove-event-schedule">'+$.sc.locale("smartpointdetail.sunrisesunset.removeeventschedule")+'</a>';
						}
					}
				}
			}
			else
			{
				sHtmlEvent = '<div class="left">'+$.sc.locale('smartpointdetail.status.noneapplied')+'</div>';

				if (sensus.settings.userContext.userRole == "ROLE_Role.Admin" || sensus.settings.userContext.userRole == "ROLE_Role.System Operator")
				{
					if(aData.lastNotificationHistory.lifeCycleStateValue != 4)
					{
						sHtmlEvent += '<a href="#" class="button small right edit-context apply-event-schedule">'+$.sc.locale('smartpointdetail.sunrisesunset.applyeventschedule')+'</a>';
					}
				}
			}

			sensus.commons.modules.util.fnFillData($('#sunrise-sunset-event'), [sHtmlEvent]);

			$(".button").button();

			if (aData.lastNotificationHistory.lifeCycleState == "DEACTIVATED")
			{
				$('a.button', '#sunrise-sunset').remove();
			}

		},
		loadSchedules : function(){

			var fnScheduleCallback = function(response)
			{
				if(!$.sc.isNullOrUndefined(response))
				{
					var sSelectOffset = '';
					var aOffset = response.offsetSchedules;
					for(var i=0; i<aOffset.length; i++)
					{
						sSelectOffset += '<option value="'+aOffset[i].id+'">'+aOffset[i].name+'</option>';
					}

					$('#offset-schedule-add-select').empty().append(sSelectOffset);

					var sSelectEvent = '';
					var aEvent = response.eventSchedules;

					for(i=0; i<aEvent.length; i++)
					{
						sSelectEvent += '<option value="'+aEvent[i].id+'">'+aEvent[i].name+'</option>';
					}

					$('#event-schedule-add-select').empty().append(sSelectEvent);
				}
			};

			var obj = function() {
				return {
						url : "api/schedule/fetchAll",
						data : new inquiryScheduleRequest()
					};
			};

			$.when(sensus.commons.modules.util.getData(obj)).done(function(oResponse) {
				fnScheduleCallback(oResponse);
			});
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

$("#offset-schedule-add-select_input").attr("value", $.sc.locale("widgets.combobox.prompt.typetosearch"));

$('#offset-schedule-add-select_input').focus(function() {

	if ($(this).attr('value') == $.sc.locale("widgets.combobox.prompt.typetosearch"))
	{
		$(this).attr('value', '');
	}

});

$('#event-schedule-add-select').combobox( {
	zIndex : 3999
});

$("#event-schedule-add-select_input").attr("value", $.sc.locale("widgets.combobox.prompt.typetosearch"));

$('#event-schedule-add-select_input').focus(function() {
	if ($(this).attr('value') == $.sc.locale("widgets.combobox.prompt.typetosearch")) {
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
	var oEventSchedule 			= new offsetSchedule(nId, null, null, 'offsetSchedule', null, null, null, null, null, aLights);
	var oSearchLight 			= { searchParameters :  []};
	var eventScheduleRequest 	= new scheduleRequest(null,null,null,null,null,null,oEventSchedule,'offsetSchedule', null, aLights, oSearchLight, null, aLights);
	var fnCallBack = sensus.commons.modules.content.lightSchedule.fnUpdateSchedule;

	eventScheduleRequest.lightRequest = new lightTableRequest(sensus.util.page.getSearchCriteria());
	eventScheduleRequest.lightRequest = sensus.pages.smartpoint.setLightIdsOnLightCriteria(oRequest.lightRequest, aLights);
	$.sc.monitor("api/schedule/deletelights", eventScheduleRequest, false, fnCallBack, $.sc.locale("action.longRunningProcessDialog.confirm"), true);

	return false;
});

//remove smartpoint event schedule
$('#tabs-content').on('click', '.remove-event-schedule', function() {
	var nId 					= $(this).attr('id').split('-')[2];
	var aLights 				= [ $.address.parameter('id') ];
	var oEventSchedule 			= new eventSchedule(nId, null, null, 'eventSchedule', null, aLights);
	var oSearchLight 			= { searchParameters :  []};
	var eventScheduleRequest 	= new scheduleRequest(null,null,null,null,null,null,oEventSchedule,'eventSchedule', null, aLights, oSearchLight, null, aLights);
	var fnCallBack = sensus.commons.modules.content.lightSchedule.fnUpdateSchedule;

	eventScheduleRequest.lightRequest = new lightTableRequest(sensus.util.page.getSearchCriteria());
	eventScheduleRequest.lightRequest = sensus.pages.smartpoint.setLightIdsOnLightCriteria(oRequest.lightRequest, aLights);
	$.sc.monitor("api/schedule/deletelights", eventScheduleRequest, false, fnCallBack, $.sc.locale("action.longRunningProcessDialog.confirm"), true);

	return false;
});

//Apply offset schedule
$('#tabs-content').on('click', '.apply-offset-schedule-action', function() {

	sensus.pages.longrunningprocess.bIsDetail = true;
	if (sensus.util.combobox.getOption("#offset-schedule-add-select") != undefined)
	{
		$('#apply-offset-schedule-smartpoint').hide();
		$('#sunrise-sunset-details').show();
		$('.formError').remove();

		var nId = sensus.util.combobox.getOption("#offset-schedule-add-select");
		var sScheduleName = $("#offset-schedule-add-select option[value='"+nId+"']").text();
		var aLights = [ $.address.parameter('id') ];
		var oEventSchedule = new eventSchedule(nId, null, null, 'offsetSchedule', null, null, null, null, null, aLights);
		var oSearchLight = { searchParameters :  []};
		var eventScheduleRequest = new scheduleRequest(nId,null,null,null,null,null,oEventSchedule,'offsetSchedule', null, aLights, oSearchLight, sScheduleName, aLights);

		eventScheduleRequest.lightRequest = new lightTableRequest(sensus.util.page.getSearchCriteria());
		eventScheduleRequest.lightRequest = sensus.pages.smartpoint.setLightIdsOnLightCriteria(oRequest.lightRequest, aLights);
		$.sc.monitor("api/schedule/insertlights", eventScheduleRequest, false, sensus.commons.modules.content.lightSchedule.fnUpdateSchedule, $.sc.locale("action.longRunningProcessDialog.confirm"), true);
	}
	else if (($("#offset-schedule-add-select_input").val() == $.sc.locale("widgets.combobox.prompt.typetosearch"))
					|| ($("#offset-schedule-add-select_input").val() == ""))
	{
		$('#offset-schedule-add-select_input').validationEngine('showPrompt', $.sc.locale("smartpointdetail.validation.validValue"), 'red', '', true);
	}
	else
	{
		$('#offset-schedule-add-select_input').validationEngine('showPrompt', $.sc.locale("smartpoint.actions.applySchedule.not.exists", $("#offset-schedule-add-select_input").val()), 'red', '', true);
	}

	return false;
});

//Apply event schedule
$('#tabs-content').on('click', '.apply-event-schedule-action', function() {

	sensus.pages.longrunningprocess.bIsDetail = true;
	if (sensus.util.combobox.getOption("#event-schedule-add-select") != undefined)
	{
		$('#apply-event-schedule-smartpoint').hide();
		$('#sunrise-sunset-details').show();
		$('.formError').remove();
		var nId	= sensus.util.combobox.getOption("#event-schedule-add-select");
		var sScheduleName = $("#event-schedule-add-select option[value='"+nId+"']").text();
		var aLights = [ $.address.parameter('id') ];
		var oEventSchedule = new eventSchedule(nId, null, null, 'EVENT', null, aLights);
		var oSearchLight = { searchParameters :  []};
		var offsetScheduleRequest = new scheduleRequest(nId,null,null,null,null,null,oEventSchedule,'EVENT', null, aLights, oSearchLight, sScheduleName);

		offsetScheduleRequest.lightRequest = new lightTableRequest(sensus.util.page.getSearchCriteria());
		offsetScheduleRequest.lightRequest = sensus.pages.smartpoint.setLightIdsOnLightCriteria(oRequest.lightRequest, aLights);
		$.sc.monitor("api/schedule/insertlights", offsetScheduleRequest, false, sensus.commons.modules.content.lightSchedule.fnUpdateSchedule, $.sc.locale("action.longRunningProcessDialog.confirm"), true);
	}
	else if (($("#event-schedule-add-select_input").val() == $.sc.locale("widgets.combobox.prompt.typetosearch"))
					|| ($("#event-schedule-add-select_input").val() == ""))
	{
		$('#event-schedule-add-select_input').validationEngine('showPrompt', $.sc.locale("smartpointdetail.validation.validValue"), 'red', '', true);
	}
	else
	{
		$('#event-schedule-add-select_input').validationEngine('showPrompt', $.sc.locale("smartpoint.actions.applySchedule.not.exists", $("#event-schedule-add-select_input").val()), 'red', '', true);
	}

	return false;
});

//hide load apply offset schedule div
$('#apply-offset-schedule-smartpoint .cancel-edit').click(function() {
	$('#apply-offset-schedule-smartpoint').hide();
	$("#offset-schedule-add-select_input").attr("value", $.sc.locale("widgets.combobox.prompt.typetosearch"));
	$('#sunrise-sunset-details').show();
	return false;
});

//hide load apply event schedule div
$('#apply-event-schedule-smartpoint .cancel-edit').click(function() {
	$('#apply-event-schedule-smartpoint').hide();
	$("#event_schedule_add_select_input").attr("value", $.sc.locale("widgets.combobox.prompt.typetosearch"));
	$('#sunrise-sunset-details').show();
	return false;
});

$(".button").button();
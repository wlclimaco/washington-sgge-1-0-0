sensus.commons.modules.content.lightAlerts = {

	fnLoadLightAlerts : function(aData) {

		var aAlertsClassification = aData.lastNotificationHistory.alertClassifications;
		var sHtml = '';
		var oStatusMsnContainer = $('#status-messages');
		var iCountAlarmWarning = aAlertsClassification.length;

		oStatusMsnContainer.empty();

		if (iCountAlarmWarning < 1)
		{
			$('#light-clear-all').remove();
		}

		if (aAlertsClassification[0] && aAlertsClassification[0].alertSubType != null)
		{
			for (var i = 0; i < aAlertsClassification.length; i++)
			{
				sHtml = '';

				if(aAlertsClassification[i].alertTypeValue == '1')
				{
					sType = 'alarm';
				}
				else if(aAlertsClassification[i].alertTypeValue == '2')
				{
					sType = 'warning';
				}

				sHtml += '<dt class="'+sType+'">'+$.sc.locale("smartpointdetail.status."+sType)+'</dt>';
				sHtml += '<dd><div class="first-column"><span id="alert-type-' + sType + '" class="message"><a id="alert' + i +'" class="alert-detail-action text_button" href="#">' + $.sc.locale('sensus.mlc.alert_subtype.' + aAlertsClassification[i].alertSubType) + '</a></span></div>';

				if (sensus.settings.userContext.userRole == "ROLE_Role.Admin" || sensus.settings.userContext.userRole == "ROLE_Role.System Operator")
				{
					var sIdAnchor = "alert" + i;
					sHtml += '<a href="#" id="' + sIdAnchor +'" class="button small right clear-status">'+$.sc.locale("smartpointdetail.status.clear")+'</a>';
				}

				// Format message hour
				var iLightTimeZone = aData.radio.location.timeZoneInfo.offsetInHours;
				var oDate 	= $.sc.dateFormat(  aAlertsClassification[i].messageDate, 'dateObj',null,false,iLightTimeZone);
				var oLastMessage = Globalize.format(new Date(oDate.getFullYear(), oDate.getMonth(), oDate.getDate()),'D');
				var sHour       = $.sc.dateFormat( oDate, 'h:i:s:fff A',null,false);


				sHtml += '<span id="last-message" class="right time-stamp">' + oLastMessage + ' ' + $.sc.locale("smartpointdetail.status.at") + " " +  sHour + '</span></dd>';

				oStatusMsnContainer.append(sHtml);

				$("#alert" + i).data('alertClassification', aAlertsClassification[i]);
				$("#alert" + i).data('lastOperationalData', aData.lastOperationalData);

			}

		}
		else
		{
			oStatusMsnContainer.parents('.status-viewport').hide()
		}


		$(".button").button();

	},

	fnUpdateLightAlerts : function(aData) {

		sensus.commons.modules.bForceReload = true;
		var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
		var oFirstMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);

		sensus.commons.modules.content.lightAlerts.fnLoadLightAlerts(oFirstMeter);
		sensus.commons.modules.bForceReload = false;

	},

	init : function(){

		var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();

		var	oFirstMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);

		sensus.commons.modules.content.lightAlerts.fnLoadLightAlerts(oFirstMeter);

	}

};

sensus.commons.modules.content.lightAlerts.init();

$(".alert-detail-action").click(function() {

	$.sc.launchActionDialog($(this).attr("id"), sensus.commons.modules.dialogSettings.alertDetails);

	return false;

});

$(".button").button();

// Clear Status Alarm
$('#status-messages').on('click', '.clear-status', function() {

	var aId = [$.address.parameter('id')];
	var iNotificationHistoryId = $(this).attr("id");
	var oAlertClassification = $('#' + iNotificationHistoryId).data('alertClassification');

	if(iNotificationHistoryId == 'null')
	{
		iNotificationHistoryId = null;
	}

	var oAlertCriteria = new AlertCriteria(null, [oAlertClassification.alertSubType]);

	var oNotificationHistoryCriteria = new NotificationHistoryCriteria(oAlertClassification.notificationHistoryId);
	var oLightCriteria = new LightCriteria([parseInt($.address.parameter('id'))]);
	var oRequest = new lightRequest(oLightCriteria, null, null, oAlertCriteria, null, null, null, null, null, null, oNotificationHistoryCriteria);

	if (oAlertClassification.alertSubTypeValue != 8)
	{
		$.sc.monitor("api/lighttop/clearstatus", oRequest, false, sensus.commons.modules.content.lightAlerts.fnUpdateLightAlerts, $.sc.locale("action.longRunningProcessDialog.confirm"), true);
	}
	else
	{
		$.sc.getJson("api/lighttop/clearstatus", oRequest, false, sensus.commons.modules.content.lightAlerts.fnUpdateLightAlerts, $.sc.locale("action.longRunningProcessDialog.confirm"), false);
	}

	return false;
});

// Clear All Status Alarm
$('.clear-all').click(function() {

	var oRequest = new lightRequest(new LightCriteria([parseInt($.address.parameter('id'))]));

	$.sc.monitor("api/lighttop/clearstatus", oRequest, false, sensus.commons.modules.reloadData, $.sc.locale("action.longRunningProcessDialog.confirm"), true);
	return false;

});
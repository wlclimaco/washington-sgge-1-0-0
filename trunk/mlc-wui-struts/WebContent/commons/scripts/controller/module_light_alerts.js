sensus.commons.modules.content.lightAlerts = {

	fnLoadLightAlerts : function(aData) {
		
		var aAlarmWarningList = aData.currentAlarmWarningMessageList;
		var sDateTimeMessage  = aData.currentStatusMessage.date;
		var sHtml = '';
		var oStatusMsnContainer = $('#status-messages');
		var iCountAlarmWarning = 0;
		
		
		oStatusMsnContainer.empty();

		
		if (aAlarmWarningList.length > 0) {
		
			for (i in aAlarmWarningList) {
		
				if (aAlarmWarningList.hasOwnProperty(i) && aAlarmWarningList[i].statusMessage != "ACTIVE" && aAlarmWarningList[i].statusMessage != "MAINTENANCE") {
		
					iCountAlarmWarning++;
				
				} 

			}

		}
		
		if (iCountAlarmWarning < 1) {
		
			$('#light-clear-all').remove();

		}

		if (aAlarmWarningList[0].statusMessage != "ACTIVE" && aAlarmWarningList[0].statusMessageSubtype != null) {

			for (var i = 0; i < aAlarmWarningList.length; i++) {

				if(aAlarmWarningList[i].statusMessageValue == '1') {

					sType = 'alarm';

				} else if(aAlarmWarningList[i].statusMessageValue == '2') {

					sType = 'warning';

				}

				sHtml += '<dt class="'+sType+'">'+sensus.locale.get("smartpointdetail.status."+sType)+'</dt>';
				sHtml += '<dd><div class="first-column"><span id="alert-type-' + sType + '" class="message"><a id="' + aAlarmWarningList[i].statusMessageId +'-'+ aAlarmWarningList[i].statusMessageSubtypeValue +'" class="alert-detail-action text_button" href="#">' + sensus.locale.get('sensus.mlc.status_subtype.'+aAlarmWarningList[i].statusMessageSubtype) + '</a></span></div>';

				if (sensus.settings.userContext.userRole == "ROLE_Role.Admin" || sensus.settings.userContext.userRole == "ROLE_Role.System Operator") {

					var sIdAnchor = aAlarmWarningList[i].statusMessageId + '-' + aAlarmWarningList[i].statusMessageValue + '-' + aAlarmWarningList[i].statusMessageSubtypeValue;
					sHtml += '<a href="#" id="' + sIdAnchor +'" class="button small right clear-status">'+sensus.locale.get("smartpointdetail.status.clear")+'</a>';

				}

				// Format message hour
				var oLastMessage = $.date.dateFormat( sDateTimeMessage, 'MM d, yy',null,true);
				var sHour = $.date.dateFormat( sDateTimeMessage, 'h:i:s.fff A',null,true);

				sHtml += '<span id="last-message" class="right time-stamp">' + oLastMessage + ' ' + sensus.locale.get("smartpointdetail.status.at") + " " +  sHour + '</span></dd>';

			}
		}

		sensus.commons.modules.util.fnFillData(oStatusMsnContainer, [sHtml]);
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

	var sIdAndSubType = $(this).attr("id");
	
	sensus.util.actiondialog.launchActionDialog(sIdAndSubType, sensus.commons.modules.dialogSettings.alertDetails);
	return false;

});

$(".button").button();

// Clear Status Alarm
$('#status-messages').on('click', '.clear-status', function() {

	var parameters = $(this).attr("id").split("-");
	var iStatusMessageId = parameters[0];
	if(iStatusMessageId == 'null'){
	
		iStatusMessageId = null;
	
	}
	var iStatusTypeId = parameters[1];
	var iStatusMessageSubtype = parameters[2];
	var oStatusMessages = [ {

			id                    : iStatusMessageId,
			lightStatusEnumValue  : iStatusTypeId,
			statusExceptions      : [ { statusExceptionTypeEnumValue : iStatusMessageSubtype } ]

	} ];

	
	var oRequest = null;
	
	if (iStatusMessageSubtype != '8') {
		
		oRequest = { 'lightRequest': new lightRequest($.address.parameter('id'), null, false, null, null, true, null, null, null, null, null, null, oStatusMessages) };
		$.ajaxValidator.fnMonitor(sensus.settings.clearStatus, oRequest, false, sensus.commons.modules.content.lightAlerts.fnUpdateLightAlerts, sensus.locale.get("action.longRunningProcessDialog.confirm"), true);
	
	} else {
	
		oRequest = { 'lightRequest': new lightRequest($.address.parameter('id'), null, false, null, null, false, null, null, null, null, null, null, oStatusMessages) };
		$.ajaxValidator.fnDoCall(sensus.settings.clearStatus, oRequest, false, sensus.commons.modules.content.lightAlerts.fnUpdateLightAlerts, sensus.locale.get("action.longRunningProcessDialog.confirm"));
	
	}

	return false;
});

// Clear All Status Alarm
$('.clear-all').click(function() {

	var oRequest = { 'lightRequest': new lightRequest($.address.parameter('id')) };
	$.ajaxValidator.fnMonitor(sensus.settings.clearStatus, oRequest, false, sensus.commons.modules.reloadData, sensus.locale.get("action.longRunningProcessDialog.confirm"), true);
	return false;

});
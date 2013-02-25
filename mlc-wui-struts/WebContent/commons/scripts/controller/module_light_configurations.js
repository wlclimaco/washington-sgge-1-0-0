sensus.commons.modules.content.lightConfigurations = {

	setProtect : function(aData){

		var sClass, sText, bProtected;
		if(aData.firstLight.protect){

			sClass="locked";
			sText="unlocked";
			bProtected = false;

		} else {

			sClass="unlocked";
			sText="locked";
			bProtected = true;

		}
		var sHtmlProtect = '<div class="left"><span id="light-protect" class="'+sClass+'"></span><span id="light-text">'+ sensus.locale.get('smartpointdetail.configuration.' + sClass ) + '</span></div>';

		if (sensus.settings.userContext.userRole == "ROLE_Role.Admin" || sensus.settings.userContext.userRole == "ROLE_Role.System Operator"){

			sHtmlProtect += '<a href="#" id="'+bProtected+'" class="button small right update-light-protected">'+sensus.locale.get('smartpoint.actions.'+sText)+'</a>';

		}
		$('#update-protect').empty();
		sensus.commons.modules.util.fnFillData($('#update-protect'), [sHtmlProtect]);
		$(".button").button();

	},
	
	fnLoadLightConfigurations : function(aData) {
		
		sensus.commons.modules.content.lightConfigurations.setProtect(aData);
		
		var aData = sensus.commons.modules.util.fnGetFirstDeviceResponse(aData);
		var fnGetParameterValue = sensus.commons.modules.util.fnGetParameterValue;
		
		sensus.commons.modules.util.fnFillData($('#pole-id'),  [fnGetParameterValue(aData, "POLE_ID", 'value')]);
		sensus.commons.modules.util.fnFillData($('#pole-id', '#update-poleid'),  [fnGetParameterValue(aData, "POLE_ID", 'value')]);
		sensus.commons.modules.util.fnFillData($('#update-light-type'),          [fnGetParameterValue(aData, "LAMP_TYPE_WATTAGE_DIMMABLE", 'value')]);
		sensus.commons.modules.util.fnFillData($('#update-voltage-range'),       [fnGetParameterValue(aData, "INPUT_VOLTAGE_RANGE", 'value')]);
		sensus.commons.modules.util.fnFillData($('#update-temperature-color'),   [fnGetParameterValue(aData, "COLOR_TEMPERATURE", 'value')]);
		sensus.commons.modules.util.fnFillData($('#update-housing'),             [fnGetParameterValue(aData, "HOUSING_COLOR", 'value')]);
		sensus.commons.modules.util.fnFillData($('#update-manufacturer'),        [fnGetParameterValue(aData, "MANUFACTURER", 'value')]);
		sensus.commons.modules.util.fnFillData($('#update-sensus-part-number'),  [fnGetParameterValue(aData, "MODEL_NUMBER", 'value')]);
		sensus.commons.modules.util.fnFillData($('#update-bulb-serial-number'),  [fnGetParameterValue(aData, "BULB_SERIAL_NUMBER", 'value')]);
		sensus.commons.modules.util.fnFillData($('#update-light-driver-serial'), [fnGetParameterValue(aData, "LIGHT_DRIVER", 'value')]);
		sensus.commons.modules.util.fnFillData($('#update-lower-serial-number'), [fnGetParameterValue(aData, "LOWER_ASSEMBLY_SERIAL_NUMBER", 'value')]);
		sensus.commons.modules.util.fnFillData($('#update-upper-serial-number'), [fnGetParameterValue(aData, "UPPER_ASSEMBLY_SERIAL_NUMBER", 'value')]);

		var sDateAddedToFormat = aData.createDate;
		var sHour = ' ' + $.date.dateFormat( sDateAddedToFormat, 'h:i:s.fff A');

		sensus.commons.modules.util.fnFillData($('#update-date-added'), [ $.date.dateFormat( sDateAddedToFormat, sensus.settings.dateFormatMask), sHour ]);
		sensus.commons.modules.util.fnFillData($('#update-firmware'), [aData.firmwareVersion]);

		
	},
	
	fnUpdateConfigurations : function(aData){
		
		sensus.commons.modules.bForceReload = true;
		sensus.commons.modules.content.lightConfigurations.init();
		sensus.commons.modules.bForceReload = false;
		
	},
	
	init : function(){

		var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
		sensus.commons.modules.content.lightConfigurations.fnLoadLightConfigurations(oResponse);	
	
	}

};

sensus.commons.modules.content.lightConfigurations.init();	
// Lock and Unlock Light and refresh div
$('#configuration').on('click', '.update-light-protected', function() {

	$.ajaxValidator.fnDoCall(sensus.settings.updateLightProtected, {'lightRequest': new lightRequest($.address.parameter('id'),null,$(this).attr('id'))}, false, sensus.commons.modules.content.lightConfigurations.setProtect, sensus.locale.get("action.longRunningProcessDialog.confirm"));
	return false;
});

//Edit Pole Id
var oUpdatePoleId = $('#update-poleid'); 
$('.edit', oUpdatePoleId).click(function() {

	$("#pole-id-input").val($(".read-only div span", oUpdatePoleId).text());
	$('.read-only', oUpdatePoleId).hide();
	$('.edit-only', oUpdatePoleId).show();
	$('.formError').remove();

	return false;
});

//Cancel Edit Pole Id
$('.cancel-edit', oUpdatePoleId).click(function() {

	$('.edit-only', oUpdatePoleId).hide();
	$('.read-only', oUpdatePoleId).show();
	$('.formError').remove();

	return false;
});

//Save Pole Id
$('.save-pole-id', oUpdatePoleId).click(function(e) {
	e.preventDefault();

	var poleId = $("#pole-id-input").val().trim();

	if (poleId.length === 0) {

		$('#pole-id-input').validationEngine('showPrompt', sensus.locale.get("smartpointdetail.configuration.poleid.error"), 'red', '', true);

	} else if (poleId.length > 12) {

		$('#pole-id-input').validationEngine('showPrompt', sensus.locale.get("smartpointdetail.configuration.poleid.errorformat"), 'red', '', true);

	} else {

		$('.formError').remove();
		var aParameters = [];
		aParameters.push({'propertyEnum':'POLE_ID', 'value': poleId});
		var oRequest = { 'lightRequest': new lightRequest($.address.parameter('id'), null, false, null, null, true, null, null, null, null, null, null, null, aParameters) };
		$.ajaxValidator.fnMonitor(sensus.settings.updateLightProperty, oRequest, false, sensus.commons.modules.content.lightConfigurations.fnUpdateConfigurations, sensus.locale.get("action.longRunningProcessDialog.confirm"), true);
		
		$('.edit-only', oUpdatePoleId).hide();
		$('.read-only', oUpdatePoleId).show();

	}

});
sensus.commons.modules.content.lightConfigurations = {

	setProtect : function(aData){

		var sClass, sText, bProtected;

		if ($.sc.isNullOrUndefined(aData.light) && aData.operationSuccess)
		{
			if ($("#update-protect a").attr("id") == "true")
			{
				sClass="locked";
				sText="unlocked";
				bProtected = false;
			}
			else
			{
				sClass="unlocked";
				sText="locked";
				bProtected = true;
			}
		}
		else
		{
			if (aData.firstLight)
			{
				aData = aData.firstLight;
			}
			else
			{
				aData = aData.light;
			}

			if(aData.protect)
			{
				sClass="locked";
				sText="unlocked";
				bProtected = false;
			}
			else
			{
				sClass="unlocked";
				sText="locked";
				bProtected = true;
			}
		}

		var sHtmlProtect = '<div class="left"><span id="light-protect" class="' + sClass + '"></span><span id="light-text">'+ $.sc.locale('smartpointdetail.configuration.' + sClass ) + '</span></div>';

		if (sensus.settings.userContext.userRole == "ROLE_Role.Admin" || sensus.settings.userContext.userRole == "ROLE_Role.System Operator")
		{
			sHtmlProtect += '<a href="#" id="'+bProtected+'" class="button small right update-light-protected">'+$.sc.locale('smartpoint.actions.'+sText)+'</a>';
		}

		$('#update-protect').empty();
		sensus.commons.modules.util.fnFillData($('#update-protect'), [sHtmlProtect]);
		$(".button").button();

	},

	fnLoadLightConfigurations : function(aData) {

		sensus.commons.modules.content.lightConfigurations.setProtect(aData);

		var aData = sensus.commons.modules.util.fnGetFirstDeviceResponse(aData);
		var fnGetParameterValue = sensus.commons.modules.util.fnGetParameterValue;
		sensus.commons.modules.util.fnFillData($('#pole-id'),  				     [aData.poleId]);
		sensus.commons.modules.util.fnFillData($('#pole-id', '#update-poleid'),  [aData.poleId]);
		sensus.commons.modules.util.fnFillData($('#update-light-type'),          [aData.configuration.lampTypeWattageDimmable]);
		sensus.commons.modules.util.fnFillData($('#update-voltage-range'),       [aData.configuration.inputVoltageRange]);
		sensus.commons.modules.util.fnFillData($('#update-temperature-color'),   [aData.configuration.colorTemperature]);
		sensus.commons.modules.util.fnFillData($('#update-housing'),             [aData.configuration.housingColor]);
		sensus.commons.modules.util.fnFillData($('#update-manufacturer'),        [aData.configuration.manufacturer]);
		sensus.commons.modules.util.fnFillData($('#update-sensus-part-number'),  [aData.configuration.modelNumber]);
		sensus.commons.modules.util.fnFillData($('#update-bulb-serial-number'),  [aData.configuration.bulbSerialNumber]);
		sensus.commons.modules.util.fnFillData($('#update-light-driver-serial'), [aData.configuration.lightDriverSerialNumber]);
		sensus.commons.modules.util.fnFillData($('#update-lower-serial-number'), [aData.configuration.lowerAssemblySerial]);
		sensus.commons.modules.util.fnFillData($('#update-upper-serial-number'), [aData.configuration.upperAssemblySerial]);

		var sDateAddedToFormat = aData.configuration.dateAdded;

		sensus.commons.modules.util.fnFillData($('#update-date-added'), [$.sc.dateFormat( sDateAddedToFormat, sensus.settings.DATE_FORMAT,null,false,aData.radio.location.timeZoneInfo.offsetInHours)]);
		sensus.commons.modules.util.fnFillData($('#update-firmware'), [aData.configuration.firmwareVersion]);

	},

	fnUpdateConfigurations : function(aData)
	{
		sensus.commons.modules.bForceReload = true;
		sensus.commons.modules.content.lightConfigurations.init();
		sensus.commons.modules.bForceReload = false;
	},

	init : function()
	{
		var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
		sensus.commons.modules.content.lightConfigurations.fnLoadLightConfigurations(oResponse);
	}

};

sensus.commons.modules.content.lightConfigurations.init();
// Lock and Unlock Light and refresh div
$('#configuration').on('click', '.update-light-protected', function() {

	var id = parseInt($.address.parameter('id'));

	var oLightCriteria = new LightCriteria([id]);

    if ($(this).attr("id") == "true")
    {
    	var light = new Light(true);
    }
    else
    {
    	var light = new Light(false);
    }

	$.sc.getJson("api/lighttop/updateprotected", new lightMassUpdateRequest(light, oLightCriteria), false, sensus.commons.modules.content.lightConfigurations.setProtect, $.sc.locale("action.longRunningProcessDialog.confirm"));
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

	var sPoleId = $("#pole-id-input").val().trim();

	if (sPoleId.length === 0)
	{
		$('#pole-id-input').validationEngine('showPrompt', $.sc.locale("smartpointdetail.configuration.poleid.error"), 'red', '', true);
	}
	else if (sPoleId.length > 12)
	{
		$('#pole-id-input').validationEngine('showPrompt', $.sc.locale("smartpointdetail.configuration.poleid.errorformat"), 'red', '', true);
	}
	else
	{
		$('.formError').remove();

		var lightId = $.address.parameter('id');
		var oLight = {
			id : lightId,
			poleId : sPoleId
		};

		oRequest = new lightMaintenanceRequest(oLight);

		$.sc.monitor("api/lighttop/upsertproperty/poleid", oRequest, false, sensus.commons.modules.content.lightConfigurations.fnUpdateConfigurations, $.sc.locale("action.longRunningProcessDialog.confirm"), true);

		$('.edit-only', oUpdatePoleId).hide();
		$('.read-only', oUpdatePoleId).show();
	}
});
sensus.commons.modules.content.lightReadings = {

	loadReadings : function(aData) {

		var olastOperationalData = aData.light.lastOperationalData;
		var fnFillData = sensus.commons.modules.util.fnFillData;
		var fnGetParameterValue = sensus.commons.modules.util.fnGetParameterValue;
		var fnFillLastDate = sensus.commons.modules.util.fnFillLastDate;

		if (!$.sc.isNullOrUndefined(olastOperationalData))
		{
			var sDateVoltVal = olastOperationalData.acVoltage;
			var sDateVoltMinVal = olastOperationalData.acVoltageMin;
			var sDateVoltMaxVal = olastOperationalData.acVoltageMax;
			var sDateCurrentVal = olastOperationalData.acCurrent;
			var sDateVoltCreate = olastOperationalData.acVoltageDate;
			var sDateVoltMinCreate = olastOperationalData.acVoltageMinDate;
			var sDateVoltMaxCreate = olastOperationalData.acVoltageMaxDate;
			var sDateCurrentCreate = olastOperationalData.acCurrentDate;
			var sTotalConsumption = olastOperationalData.consumption;
			var sDateConsumption = olastOperationalData.consumptionDate;
		}

		var sPowerLast = parseInt(sDateVoltVal)*parseInt(sDateCurrentVal)/1000;
		var sMaskToFormat = sensus.settings.dateFormatMask + " | h:i:s.fff A";

		if(isNaN(sPowerLast))
		{
			sPowerLast = '--';
		}

		var iLightTimeZone = aData.light.radio.location.timeZoneInfo.offsetInHours;
		var fnDateFormat = function(sDateVal)
		{
			return sDateVal == '--' || !sDateVal ? "" : "<span class='time-stamp'>" + $.sc.dateFormat( sDateVal, sMaskToFormat, null, false, iLightTimeZone).replace("|", $.sc.locale("smartpointdetail.page.at")) + "</span>";
		};


		fnFillData($('#voltage-last'), [sDateVoltVal, fnDateFormat( sDateConsumption )]);
		fnFillData($('#voltage-min'),  [sDateVoltMinVal, fnDateFormat( sDateVoltMinCreate )]);
		fnFillData($('#voltage-max'),  [sDateVoltMaxVal, fnDateFormat( sDateVoltMaxCreate )]);
		fnFillData($('#current-last'), [sDateCurrentVal, fnDateFormat( sDateConsumption )]);
		fnFillData($('#power-last'),   [sPowerLast, fnDateFormat( sDateConsumption )]);
		fnFillData($('#total-consumption'), [sTotalConsumption, fnDateFormat( sDateConsumption )]);

	},

	fnUpdateReadings : function(aData)
	{
		sensus.commons.modules.bForceReload = true;
		sensus.commons.modules.content.lightReadings.init();
		sensus.commons.modules.bForceReload = false;
	},

	init : function()
	{

		var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
		sensus.commons.modules.content.lightReadings.loadReadings(oResponse);
	}
};

sensus.commons.modules.content.lightReadings.init();

$("#reset-min-max").click(function(e) {
	e.preventDefault();
	sensus.commons.modules.summaryData.lightActions.resetValues();
	return false;
});

$("#get-readings").click(function(e) {
	e.preventDefault();
	sensus.commons.modules.summaryData.lightActions.getReadings();
	return false;
});
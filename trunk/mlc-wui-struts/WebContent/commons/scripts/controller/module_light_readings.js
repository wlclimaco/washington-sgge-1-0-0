sensus.commons.modules.content.lightReadings = {

	loadReadings : function(aData) {

		aData = aData.firstLight;

		var fnFillData = sensus.commons.modules.util.fnFillData;
		var fnGetParameterValue = sensus.commons.modules.util.fnGetParameterValue;
		var fnFillLastDate = sensus.commons.modules.util.fnFillLastDate;
		var sDateVoltVal = fnGetParameterValue(aData, "AC_VOLTAGE", 'value');
		var sDateVoltMinVal = fnGetParameterValue(aData, "AC_VOLTAGE_MIN", 'value');
		var sDateVoltMaxVal = fnGetParameterValue(aData, "AC_VOLTAGE_MAX", 'value');
		var sDateCurrentVal = fnGetParameterValue(aData, "AC_CURRENT", 'value');
		var sDateVoltCreate = fnFillLastDate(aData, "AC_VOLTAGE");
		var sDateVoltMinCreate = fnFillLastDate(aData, "AC_VOLTAGE_MIN");
		var sDateVoltMaxCreate = fnFillLastDate(aData, "AC_VOLTAGE_MAX");
		var sDateCurrentCreate = fnFillLastDate(aData, "AC_CURRENT");
		var sPowerLast = parseInt(sDateVoltVal)*parseInt(sDateCurrentVal)/1000;
		var sMaskToFormat = sensus.settings.dateFormatMask + " " + sensus.locale.get("smartpointdetail.page.at") + " h:i:s.fff A";

		if(isNaN(sPowerLast)){

			sPowerLast = '--';

		}

		var fnDateFormat = function(sDateVal) {
			return sDateVal == '--' ? "" : "<span class='right time-stamp'>" + $.date.dateFormat( sDateVal, sMaskToFormat) + "</span>";
		};

		fnFillData($('#voltage-last'), [sDateVoltVal, fnDateFormat( sDateVoltCreate )]);
		fnFillData($('#voltage-min'),  [sDateVoltMinVal, fnDateFormat( sDateVoltMinCreate )]);
		fnFillData($('#voltage-max'),  [sDateVoltMaxVal, fnDateFormat( sDateVoltMaxCreate )]);
		fnFillData($('#current-last'), [sDateCurrentVal, fnDateFormat( sDateCurrentCreate )]);
		fnFillData($('#power-last'),  [sPowerLast, fnDateFormat( sDateVoltCreate )]);

	},

	fnUpdateReadings : function(aData){

		sensus.commons.modules.bForceReload = true;
		sensus.commons.modules.content.lightReadings.init();
		sensus.commons.modules.bForceReload = false;

	},
		
	init : function(){

		var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
		sensus.commons.modules.content.lightReadings.loadReadings(oResponse);

	}

};

sensus.commons.modules.content.lightReadings.init();

$("#reset-min-max").click(function() {

	sensus.commons.modules.summaryData.lightActions.resetValues();
	return false;

});

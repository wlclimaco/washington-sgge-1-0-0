<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<script type="text/javascript">
/**
 *
 * @namespace sensus.pages.smartpoint
 * @description The main namespace for the SmartPoint Page.
 * @fileoverview Defines the core functionality of the SmartPoint page.
 * @author Anke Doerfel-Parker
 *
 * The main namespace for the Group Page.
 */
sensus.pages.smartpoint = {


	name              : "",    // The name
	isMap             : false, // The is Map
	count             : 0,     // The is options length
	orderBy           : 11,    // The order column
	sorting           : "asc", // The sorting asc or desc
	isFilter          : false, // The is Filter
	pagination        : 0,     // The table pagination
	initialized       : false, // Whether this is page has been fully initialized.
	numberOfRows      : 15,    // The table number of rows
	totalRecords      : 0,     // Total Records
	optionslength     : 0,     // The is options length
	inputFilterValue  : "",    // The is input filter value of checkboxes filters
	isAdvancedSearch  : false, // Is advanced search
	loadLayer         : {
		loadLayerTable  : null,
		loadLayerPage   : null
	},
	setLoadLayer      : function(pageLayer, tableLayer) {
		this.loadLayer.loadLayerTable = tableLayer;
		this.loadLayer.loadLayerPage = pageLayer;
	},
	fnReloadTable     : function() {

		//Reload table function
		sensus.widgets.datatable.reloadTable(sensus.pages.smartpoint.smartpointTable, null, false);

	},
	processLightState : function(oLight){

		var sStatus = '';
		var iLightTimeZone = oLight.radio.location.timeZoneInfo.offsetInHours;

		if (oLight)
		{

			sStatus = $.sc.locale("smartpoint.status."+oLight.lifeCycleState);

			if(oLight.lifeCycleState != "ACTIVE")
			{
				sStatus += "<br><small>" + $.sc.dateFormat(oLight.lastNotificationHistory.updateDate, sensus.settings.DATE_FORMAT+' h:i:s.fff A', null, false, iLightTimeZone) +"</small>";
			}

			return sStatus;
		}

	},
	processLightAlerts : function(oLight){

		var sAlerts = '--';
		var sOverrride = '';
		var iLightTimeZone = oLight.radio.location.timeZoneInfo.offsetInHours;

		if (oLight) {

			if(oLight.overrideType && oLight.overrideType != 'NONE')
			{
				sOverrride = "<span title='Override' class='icon-small icon-override right'></span>";
			}

			var oNotificationHistory = oLight.lastNotificationHistory;
			if (oNotificationHistory.alertClassifications.length)
			{
				var oAlertClassification = oNotificationHistory.alertClassifications[0];
				sAlerts = $.sc.locale("smartpoint.status."+oAlertClassification.alertSubType)+"<br><small>" + $.sc.dateFormat(oAlertClassification.messageDate, sensus.settings.DATE_FORMAT+' h:i:s.fff A', null, false, iLightTimeZone) +sOverrride+"</small>";
			}
			else if(sOverrride != '')
			{
				sAlerts = $.sc.locale("smartpoint.status.OVERRIDE") + "<br><small>" + $.sc.dateFormat(oLight.overrideCreateDate, sensus.settings.DATE_FORMAT + ' h:i:s.fff A', null, false, iLightTimeZone) + sOverrride + "</small>";
			}

			return sAlerts;

		}

	},

	processEcoMode : function(ecoMode){

		var sEcoMode = "";
		if(ecoMode){

			sEcoMode = Math.round(ecoMode) + '%';

		} else {

			sEcoMode = '--';

		}

		return sEcoMode;

	},

	processProtect : function(bProtect){

		var sProtect = "";
		// Change to Unlock Ico
		if (bProtect == null || bProtect == false) {

			sProtect = '<span title="Unlocked" class="unlocked"></span>';

		} else if (bProtect == true) {

			// Add Locked ico by default
			sProtect = '<span title="Locked" class="locked"></span>';

		}

		return sProtect;
	},

	formatDateAdded : function(oLight) {

		return $.sc.dateFormat( oLight.configuration.dateAdded, sensus.settings.DATE_FORMAT, null, false, oLight.radio.location.timeZoneInfo.offsetInHours);

	},

	setLightIdsOnLightCriteria : function(oLightRequest, aLightIds) {

		if (!$.sc.isNullOrUndefined(aLightIds) && aLightIds.length)
		{
			oLightRequest.lightCriteria.lightIdList = aLightIds;
		}
		else
		{
			if (sensus.widgets.datatable.isAllRows)
			{
				oLightRequest.lightCriteria.notInlightIdList = sensus.widgets.datatable.deselectedRows;
			}
			else
			{
				oLightRequest.lightCriteria.lightIdList = sensus.widgets.datatable.selectedRows;
			}
		}

		return oLightRequest;
	},

	// Apply Protected
	applyProtected : function() {

		sensus.pages.smartpoint.applyProtectedUtil(true);
		return false;
	},

	// Apply Unprotected
	applyUnprotected : function() {

		sensus.pages.smartpoint.applyProtectedUtil(false);
		return false;
	},

	applyProtectedUtil : function(bProtect) {

		var objSelection = $.sc.getRowsSelection();
		var oSearchCriteria = sensus.util.page.getSearchCriteria();

		var oRequest = new lightTableRequest(oSearchCriteria);
		oRequest.light = new Light(bProtect);
		oRequest.lightCriteria.lightIdList = objSelection.aIds;
		oRequest.lightCriteria.notInlightIdList = objSelection.aNIds;

		var iCountLights = $(".checked-count", "#actions").text();

		var fnCallback = function()
		{
			sensus.widgets.datatable.reloadTable(sensus.pages.smartpoint.smartpointTable);
		}
		var sMessage;
		if(bProtect)
		{
			sMessage = $.sc.locale("smartpoint.actions.locked.success", iCountLights);
		}
		else
		{
			sMessage = $.sc.locale("smartpoint.actions.unlocked.success", iCountLights);
		}

		$.sc.getJson("api/lighttop/updateprotected", oRequest, false, fnCallback, sMessage);

	},

	// Clear Alerts
	clearAlerts : function() {

		var objSelection = $.sc.getRowsSelection();
		var oSearchCriteria = sensus.util.page.getSearchCriteria();
		oSearchCriteria.lightCriteria.lightIdList = objSelection.aIds;
		oSearchCriteria.lightCriteria.notInlightIdList = objSelection.aNIds;

		var oRequest = new lightTableRequest(oSearchCriteria);

		var fnCallback = function() {

			sensus.widgets.datatable.reloadTable(sensus.pages.smartpoint.smartpointTable);

		}

		$.sc.monitor("api/lighttop/clearstatus", oRequest, false, fnCallback, $.sc.locale("action.longRunningProcessDialog.confirm"), false);

		return false;
	},

	// Update Status
	getDataFromLight : function() {

		var objSelection = $.sc.getRowsSelection();

		var oSearchCriteria = sensus.util.page.getSearchCriteria();

		if (objSelection.aIds.length == 0 && $.address.parameter('id'))
		{
			objSelection.aIds = [$.address.parameter('id')];
		}

		oSearchCriteria.lightCriteria.lightIdList = objSelection.aIds;
		oSearchCriteria.lightCriteria.notInlightIdList = objSelection.aNIds;

		oSearchCriteria.actionCriteria = {
			getDataFromLightEnum : "ALL"
		};

		var oRequest = new lightTableRequest(oSearchCriteria);

		$.sc.monitor("api/lighttop/fetchstatus", oRequest, false, null, $.sc.locale("action.longRunningProcessDialog.confirm"), false);

		return false;
	},

	/**
	 * Reset min/max values
	 */
	getReadings : function() {

		var objSelection = $.sc.getRowsSelection();
		var oSearchCriteria = sensus.util.page.getSearchCriteria();
		if (objSelection.aIds.length == 0 && $.address.parameter('id'))
		{
			objSelection.aIds = [$.address.parameter('id')];
		}
		oSearchCriteria.lightCriteria.lightIdList = objSelection.aIds;
		oSearchCriteria.lightCriteria.notInlightIdList = objSelection.aNIds;
		oSearchCriteria.actionCriteria = {
				getDataFromLightEnum : "READING"
		};

		var oRequest = new lightTableRequest(oSearchCriteria);

		$.sc.monitor("api/lighttop/fetchstatus", oRequest, false, null, $.sc.locale("action.longRunningProcessDialog.confirm"), false);

		return false;
	},

	/**
	 * Reset min/max values
	 */
	resetValues :  function() {

		var objSelection = $.sc.getRowsSelection();
		var oSearchCriteria = sensus.util.page.getSearchCriteria();
		oSearchCriteria.lightCriteria.lightIdList = objSelection.aIds;
		oSearchCriteria.lightCriteria.notInlightIdList = objSelection.aNIds;

		var oRequest = new lightTableRequest(oSearchCriteria);

		$.sc.getJson("api/lighttop/updatereset", oRequest, false, null, $.sc.locale("action.longRunningProcessDialog.confirm"));
	},

	/**
	 * Clear Override
	 */
	clearManualOverride : function() {

		var objSelection = $.sc.getRowsSelection();

		var oSearchCriteria = sensus.util.page.getSearchCriteria();
		if (objSelection.aIds.length == 0 && $.address.parameter('id'))
		{
			objSelection.aIds = [$.address.parameter('id')];
		}
		oSearchCriteria.lightCriteria.lightIdList = objSelection.aIds;
		oSearchCriteria.lightCriteria.notInlightIdList = objSelection.aNIds;

		oSearchCriteria.actionCriteria = {
				blinkStatus : "NONE",
		 		override : "NONE",
		 		isClearOverride : true
		};

		if ($('#content-controller').length) {
			bIsDetail = true;
			fnCallback = sensus.commons.modules.summaryData.lightInformation.fnUpdateLightInformation;
		}

		var oRequest = new lightTableRequest(oSearchCriteria);

		$.sc.monitor("api/lighttop/controlLights", oRequest, false, null, $.sc.locale("action.longRunningProcessDialog.confirm"), false);
	},

	fnRequestAction : function(){

		return sensus.util.page.getSearchCriteria();

	},

	/**
	 * Clear all selected checkbox from Smartpoint Table.
	 * Usually used for callbacks.
	 */
	fnClearTableSelects : function () {
		sensus.widgets.datatable.clearSelectsFunction.call(sensus.pages.smartpoint.smartpointTable);
	}

};
</script>
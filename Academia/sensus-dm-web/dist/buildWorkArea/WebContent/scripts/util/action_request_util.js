/**
 * @fileoverview Defines common functionality to loading action dialogs using the jQuery Dialog plugin.
 * @author Anke Doerfel-Parker
 */
/**
 * The main namespace for common action dialog related functionality.
 */
sensus.util.actionrequestutil = {

	oSingleActionsAttr : {
		"sensus.dm.action.initiate.demand.reset" 			  : { sSuccessMessage : "action.longRunningProcessDialog.demand.reset.confirm"},
		"sensus.dm.action.demand.read" 						  : { sSuccessMessage : "action.longRunningProcessDialog.demand.read.confirm"},
		"sensus.dm.action.remote.connect" 		  			  : { sSuccessMessage : "action.longRunningProcessDialog.remote.connect.confirm"},
		"sensus.dm.action.remote.disconnect" 	  			  : { sSuccessMessage : "action.longRunningProcessDialog.remote.Disconnect.confirm"},
		"sensus.dm.action.remote.arm.connect"	  			  : { sSuccessMessage : "action.longRunningProcessDialog.remote.Arm.confirm"},
		"sensus.dm.action.get.connection.status" 			  : { sSuccessMessage : "smartpointdetail.tabs.about.getStatus"},
		"sensus.dm.action.get.remote.connect.status" 		  : { sSuccessMessage : "smartpointdetail.tabs.about.getRemoteConectStatus"},
		"sensus.dm.action.get.demand.response.event.status"   : { sSuccessMessage : "commons.pages.getDemandResponseEventStatusSucess"}

	},

	oDialogActionAttr : {
		"sensus.dm.action.setup.demand.response" 		      : { dialogId : 'SetupDemandResponse'},
		"sensus.dm.action.connect.han.device" 				  : { dialogId : 'connectHanDevice'},
		"sensus.dm.action.connect.to.han" 					  : { dialogId : 'connectToHan'},
		"sensus.dm.action.disconnect.han.device"			  : { dialogId : 'disconnectFromChildPage'},
		"sensus.dm.action.demand.response" 					  : { dialogId : 'DemandResponse'},
		"sensus.dm.action.delete.han.device" 				  : { dialogId : 'deleteHanDevices'},
		"addTag" 											  : { dialogId : 'addTag'},
		"removeTag" 										  : { dialogId : 'removeTag'},
		"addToGroup" 										  : { dialogId : 'addToGroup'},
		"removeFromGroup" 									  : { dialogId : 'removeFromGroup'},
		"sensus.dm.action.set.tamper.detect.timer" 		      : { dialogId : 'setTamperTimeout'},
		"sensus.dm.action.get.tamper.detect.timer" 		      : { dialogId : 'getTamperTimeout'},
		"sensus.dm.action.get.demand.response.setup.status"   : { dialogId : 'getParticipation'}
	},

	/**
	 * The Ajax call to the Controller.
	 * This is the last function before the actions reach the controller.
	 */
	fnActionCall : function (url, bDetail, item, typeId, sSuccessMessage, filterSearch, oParameters, fnCallback, isMonitorable) {

		if (sensus.util.actionrequestutil.verify(bDetail)) {

			if ($.ajaxValidator.fnIsNullOrUndefined(isMonitorable)) {

				isMonitorable = true;
			}

			var sMessagindId = null;
			var parameters = oParameters;

			// Set Message div id
			if (bDetail && sSuccessMessage) {
				sMessagindId = "messaging-smartpoint-detail";
			}

			// Set Default parameter if 'oParameters' is null
			if ($.ajaxValidator.fnIsNullOrUndefined(oParameters)) {

				oParameters = new Action({
							actionType : {id : parseInt(typeId, 10)},
							actionTypeName : item,
							actionTime :new Date(),
							onDemand : true,
							isMonitored : true,
						});
			}

			var oData = { parameters : oParameters};
			var deviceSearch = null;

			if ($.isFunction(filterSearch)) {
				deviceSearch = filterSearch().deviceSearch;
			}

			// Add default properties to Request
			sensus.util.actionrequestutil.fnAddDefaultParamToActionRequest(oData, deviceSearch, bDetail);

			if (isMonitorable) {

				$.ajaxValidator.fnMonitor(url, oData, false, fnCallback, (sSuccessMessage ? sensus.locale.get(sSuccessMessage) : sSuccessMessage), null, sMessagindId);

			} else {

				$.ajaxValidator.fnDoCall(url, oData, false, fnCallback, (sSuccessMessage ? sensus.locale.get(sSuccessMessage) : sSuccessMessage), null, null, null, null, sMessagindId);
			}
		}
	},

	/**
	 * Add default Properties to Action Request
	 * @param {Object} - oRequest
	 * 						The Request Object.
	 * @param {Object} - deviceSearch
	 * 						The Device Search Object.
	 * @param {Boolean} - bDetail
	 * 						Whether is detail or not.
	 */
	fnAddDefaultParamToActionRequest : function (oRequest, deviceSearch, bDetail, bReturnDeviceAsArray) {
		var bAllSelected =  sensus.widgets.datatable.isAllRows;
		var aUnselected = null;

		// Set the unselected ids
		if ($.ajaxValidator.fnValidArray(sensus.widgets.datatable.fnGetSelectedData(true))) {
			aUnselected = sensus.widgets.datatable.fnGetSelectedData(true);
		}

		// Add default Sort Expression
		oRequest.sortExpressions = [{field : "flexnet_id", direction : "Ascending"}];

		/* Detail or checked */
		if (!bAllSelected) {
			// Add Devices
			if (!$.ajaxValidator.fnIsNullOrUndefined(oRequest.parameters)) {
				// TODO - Remove device from parameters, always send the correct device object.
				oRequest.parameters.devices = sensus.util.actionrequestutil.fnPrepareDevice(bDetail, bReturnDeviceAsArray);
			} else {
				oRequest.devices = sensus.util.actionrequestutil.fnPrepareDevice(bDetail, bReturnDeviceAsArray);
			}

			return;
		}

		/* All Selected */
		if (bAllSelected) {
			// Add Pagination All Selected
			oRequest.paginationAllSelected = sensus.widgets.datatable.isAllRows;
			// Add deviceSearch
			oRequest.deviceSearch = deviceSearch;
			// Add Unselected ids.
			oRequest.selectionPaginationIds = aUnselected;

			return;
		}
	},

	/** Prepare the device Object */
	fnPrepareDevice : function (bDetail, bReturnAsArray) {

		// Device Detail. When it is device detail, fetch by the id to fill the device object
		if (bDetail) {
			return sensus.util.globalActions.fnPrepareParametersDevice();
		}

		// Get selected Devices
		var selectedDevices = sensus.widgets.datatable.fnGetSelectedData(false, bReturnAsArray);

		// Create the device Object
		if (bReturnAsArray == false) {
			return  sensus.util.page.fnCreateDeviceObject(selectedDevices);
		}

		// Create the Device Object as Array.
		// TODO - Remove this kind of option, always sent as a device object
		return sensus.util.page.createDevice(selectedDevices);

	},

	verify : function (bDetail) {

		var oYuiGe = $(".yui-ge");

		// Return when is detail
		if (bDetail) {
			return true;
		}

		// Return when checkbox is selected
		if ($("#checked-count").text() != 0) {
			$('.message').removeClass("ui-state-error");
			return true;
		}

		/* Show Error Message*/

		$('.message', oYuiGe).addClass("ui-state-error");
		$('.messaging', oYuiGe).hide();

		// SystemIntelligence Event Create
		$("input#actionId").val(sensus.util.page.scheduledEventId);
		return false
	},

	/**
	 * Actions that opens dialog.
	 */
	fnDialogActionCall : function (bDetail, item, typeId, dialogId, filterSearch) {
		if (sensus.util.actionrequestutil.verify(bDetail)) {
			sensus.util.actiondialog.launchActionDialog(dialogId, sensus.commons.modules.dialogSettings[dialogId](bDetail, item, typeId, filterSearch));
		}
	},

	menuPlugCallBack : function (item, typeId, bDetail, filterSearch) {

		var url =  "api/deviceop/apply";


		// Delete groups Action
		if (item == "deleteGroups") {

			sensus.util.actionrequestutil.fnDeleteGroups(item, typeId, bDetail, filterSearch);
			return;
		}

		// Send Han Text Message Action
		if ((item == "sensus.dm.action.send.han.text.message") && (sensus.util.actionrequestutil.verify(bDetail))) {

			sensus.util.actionrequestutil.fnSendHan(item, typeId, bDetail, filterSearch);
			return;
		}

		// Single actions that does not open dialog
		if (sensus.util.actionrequestutil.oSingleActionsAttr[item]) {

			sensus.util.actionrequestutil.fnActionCall(url, bDetail, item, typeId,
					sensus.util.actionrequestutil.oSingleActionsAttr[item].sSuccessMessage,
					filterSearch, null, null, sensus.util.actionrequestutil.oSingleActionsAttr[item].isMonitorable);
			return;

		}

		// Actions that opens dialogs
		if (sensus.util.actionrequestutil.oDialogActionAttr[item]) {

			sensus.util.actionrequestutil.fnDialogActionCall(bDetail, item, typeId, sensus.util.actionrequestutil.oDialogActionAttr[item].dialogId, filterSearch);
			return;

		}
	},

	fnSendHan : function (item, typeId, bDetail, filterSearch) {

		if (bDetail) {

			var bConnected = true;
			var oDevice = sensus.pages.device.module.request.get("device");

			// Get Device Status
			if (oDevice && (oDevice.deviceType != "ELECTRIC_METER" && oDevice.lifecycleStateEnum != "JOINED")) {

				bConnected = false;
			}

			if (bConnected) {

				sensus.commons.modules.dialogSettings.bDetail = true;
				sensus.util.actiondialog.launchActionDialog('HANTextMessage',
						sensus.commons.modules.dialogSettings.HANTextMessage(item,typeId,bDetail, filterSearch));

			} else {

				sensus.util.actiondialog.launchActionDialog('SendHANTextMessageDisconnected',
						sensus.commons.modules.dialogSettings['SendHANTextMessageDisconnected']);
			}

		} else {

			sensus.util.actiondialog.launchActionDialog('HANTextMessage',
					sensus.commons.modules.dialogSettings.HANTextMessage(item,typeId,bDetail, filterSearch));
		}
	},

	fnDeleteGroups : function (item, typeId, bDetail, filterSearch) {
		if (sensus.util.actionrequestutil.verify(bDetail)) {

			var aSelectedRows = sensus.widgets.datatable.isAllRows ? sensus.widgets.datatable.fnGetSelectedData(true) : sensus.widgets.datatable.fnGetSelectedData(false),
				iRows = aSelectedRows.length,
				aValue = [],
				aIds = [],
				aNames = [];

			for (var i = 0; i < iRows; i++) {
				aValue = aSelectedRows[i].split("|");
				aIds.push(aValue[0]);
				aNames.push(aValue[1]);
			}

			if (sensus.pages.group.checksSchedule(aIds)) {
				sensus.util.actiondialog.launchActionDialog('deleteInstructions', sensus.pages.group.dialogSettings.deleteInstructions(aIds, aNames));
			} else {
				sensus.util.actiondialog.launchActionDialog('deleteGroups', sensus.pages.group.dialogSettings.deleteGroups(aIds, aNames));
			}

		}
		$("#actionName").val(sensus.locale.get(item));
	},


	/**
	 * Load default actions
	 *
	 * @param aActions
	 * 			Array, the actions
	 * @return the actions group by categories
	 */
	fnLoadDefaultActions : function (aActions) {

		var sCategory;
		var oAction;
		var sort = sensus.util.page.sort;
		var get = sensus.locale.get;
		var oReturn = {};
		var length = aActions.length;
		var iReturnLength;
		var x = 0;
		var i = 0;

		// Create the action category object
		for (; x < length; x = x + 1) {

			oAction = aActions[x];
			sCategory = oAction.actionCategoryEnumNameValue;

			if (oReturn[sCategory] == undefined) {

				oReturn[sCategory] = {
						name 		: sCategory,
						description : get(sCategory),
						actionType 	: []
				};
			}

			oReturn[sCategory].actionType.push({
					id 			: oAction.actionTypeEnumValue,
					name 		: oAction.actionTypeEnumNameValue,
					description : get(oAction.actionTypeEnumNameValue)
			});
		}

		/* Convert the Object to array,in order to sort the categories.
		 * The object creation is used, to avoid using 'grep' on every iteration.
		 */
		var aReturn = $.map(oReturn, function(k, v) {
		    return [k];
		});

		// Sort cathegories ascending
		sort(aReturn, "description");
		// aReturn.reverse();

		iReturnLength = aReturn.length;

		// Sort the actions types
		for (; i < iReturnLength; i += 1) {

			sort(aReturn[i].actionType, "description");
			// aReturn[i].actionType.reverse();
		}

		return aReturn;
	},

	/**
	 * Actions commons for the Demand Response Action
	 */
	demandResponse : {

		/**
		 * Validate date of Demand Response action
		 * @Param oWhen
		 * 			Selector - Input
		 * @Param oTime
		 * 			Selector - Input
		 * @Param oDuration
		 * 			String - Duration in minutes
		 * @Param oTimeZone
		 * 			Object - Contain the time zone of device if is in detail page, otherwise the object is null.
		 * @Returns oDateAndDuration
		 * 			Array - If date is valid return a Array with duration and date, otherwise returns null.
		 */
		fnValidatorDateAndDuration : function(oWhen, oTime, oDuration, oTimeZone) {

			var oDateAndDuration = [];
			var sDateFormat 	 = sensus.settings.dateFormatMask.replace('yyyy','yy');
			var iTimeZoneValue 	 = null;

			// Parse to date and time inputs, to date format.
			var dateDemandResponse 	= $.date.parseDate(oWhen.val() +' '+ oTime.val(), sDateFormat);
			var dateServer;

			if (oTimeZone) {

				// Get the time zone of device
				if (oTimeZone.iTZMinutes) {
					iTimeZoneValue = parseInt(oTimeZone.iTZMinutes, 10) * (-1);
				}

				// Date Server in UTC
				dateServer = $.date.setDateServer(true);

				// Apply device time zone in date server
				dateServer = $.date.dateFormat(dateServer, sDateFormat + " h:ia", oTimeZone);
				dateServer = $.date.parseDate(dateServer, sDateFormat);

			} else {

				// Date Server with system time zone
				dateServer = $.date.setDateServer();

				// Get the time zone of system
				iTimeZoneValue = parseInt(sensus.settings.timezoneMinutes, 10) * (-1);
			}

			var dDateInUTC = $.date.fnGetDate(dateDemandResponse, {iTZMinutes : iTimeZoneValue});

			iDuration = parseInt(oDuration.val(), 10);

			oDateAndDuration.duration = iDuration;
			oDateAndDuration.date = $.date.dateFormat(dDateInUTC, sDateFormat + " h:ia", {bUserTZ : false});

			var bCompareDate = $.date.setCompareToDate(dateDemandResponse, dateServer);

			// If bCompareDate is true, the date and duration need more validation for confirm is valid or no.
			if (bCompareDate) {

				var datesDifference = dateServer.getTime() - dateDemandResponse.getTime();
				datesDifference = Math.round(datesDifference / 60000);

				if (iDuration > 1440) {

					oDuration.validationEngine("showPrompt", sensus.locale.get("systemintelligence.dialogDemandResponse.maxDuration"), "red", "", true);
					sensus.util.page.stopProgressBar();
					return null;

				} else {

					if (iDuration < datesDifference + 1) {
						oDuration.validationEngine("showPrompt", sensus.locale.get("systemintelligence.dialogDemandResponse.validateDate"), "red", "", true);
						sensus.util.page.stopProgressBar();
						return null;
					}

					oDateAndDuration = [];
					oDateAndDuration.duration = iDuration;
					oDateAndDuration.date = $.date.dateFormat(dDateInUTC, sDateFormat + " h:ia", {bUserTZ : false});
				}

			}

			return oDateAndDuration;
		},

		/**
		 * Set Temperature Value at Inputs received
		 * @param oTemperature
		 * 			Object - Temperature input
		 * @returns
		 * 			Inputs filled with correct value.
		 * 			If temperature of system is Fahrenheit the value is converted to scale in Celsius.
		 */
		fnSetTemperatureValue : function(oTemperature) {

			var sTemperatureType 	= sensus.settings.temperatureType;
			var temperatureValue	= parseInt(oTemperature.val(), 10);

			if (temperatureValue) {

				if (sTemperatureType.toLowerCase() != sensus.locale.get("systemsettings.page.temperature.celsius").toLowerCase()) {

					oTemperature.val($.temperature.convertTemperature(temperatureValue, "sc"));

				}

			}

		}
	},

	/**
	 * Get Action Types by Category
	 *
	 * @param aActions
	 * 			{Array}, actions to search by category
	 * @param aActionCategories
	 * 			{Array}, the categories to search on array
	 * @return {Array} of action types found or {null}
	 */
	fnGetActionTypesByCategories : function (aActions, aActionCategories) {

		var iActionsLength;
		var iCategoriesLength;
		var i;
		var x;
		var aActionTypes = [];
		var oAction;
		var sCategory;

		if (aActions && aActions.length && aActionCategories && aActionCategories.length) {

			for (iActionsLength = aActions.length, i = 0; i < iActionsLength; i = i + 1) {

				oAction = aActions[i];

				for (iCategoriesLength = aActionCategories.length, x = 0; x < iCategoriesLength; x = x + 1) {

					sCategory = aActionCategories[x];

					if (oAction.actionCategoryEnumNameValue == sCategory) {

						aActionTypes.push(oAction.actionTypeEnum);
					}
				}
			}
		}

		return aActionTypes.length > 0 ? aActionTypes : null;
	}
};

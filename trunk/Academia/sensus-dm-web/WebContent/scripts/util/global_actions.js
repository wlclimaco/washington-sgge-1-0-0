sensus.util.globalActions = {

	/**
	 * Performs search if don't have in cash
	 *
	 * @returns - [String] String containing parameters the device
	 */
	fnPrepareParametersDevice : function(oFetchObj) {

		var aParameters = [];
		var	aTemp = [];

		if (!oFetchObj) {

			oFetchObj = sensus.pages.device.module.util.ajaxData.fetchDeviceById();
		}

		$.ajaxValidator.fnDoCall(oFetchObj.url, oFetchObj.data, false, function(oResponse) {

			var oDevice = oResponse.firstDevice;

			if (oDevice.radio.flexNetId != null && oDevice.radio.flexNetId != undefined && oDevice.radio.flexNetId != '') {
				aTemp.push(oDevice.radio.flexNetId);
			} else {
				aTemp.push('');
			}

			if (oDevice.deviceType != null && oDevice.deviceType != undefined && oDevice.deviceType != '') {
				aTemp.push(oDevice.deviceType);
			} else {
				aTemp.push('');
			}

			if (oDevice.radio.customerId != null && oDevice.radio.customerId!= undefined && oDevice.radio.customerId != '') {
				aTemp.push(oDevice.radio.customerId);
			} else {
				aTemp.push('');
			}

			if (oDevice.electricMeterFlexNetId != null && oDevice.electricMeterFlexNetId != undefined && oDevice.electricMeterFlexNetId != '') {
				aTemp.push(oDevice.electricMeterFlexNetId);
			} else {
				aTemp.push(null);
			}

			if (oDevice.deviceId != null && oDevice.deviceId != undefined && oDevice.deviceId != '') {
				aTemp.push(oDevice.deviceId);
			} else {
				aTemp.push('');
			}

			if (oDevice.deviceId != null && oDevice.deviceId != undefined && oDevice.deviceId != '') {
				aTemp.push(oDevice.deviceId);
			} else {
				aTemp.push('');
			}

			if (oDevice.deviceTypeValue != null && oDevice.deviceTypeValue != undefined && oDevice.deviceTypeValue != '') {
				aTemp.push(oDevice.deviceTypeValue);
			} else {
				aTemp.push('');
			}

			// Specific Device Type Enum
			// Enum
			aTemp.push(
					[
					 oDevice.electricMeterTypeEnum,
					 oDevice.hanDeviceTypeEnum,
					 oDevice.lcmTypeEnum,
					 oDevice.waterMeterTypeEnum,
					 oDevice.gasMeterTypeEnum
					 ].join('')
			);

			// Value
			aTemp.push(
					[
					 oDevice.electricMeterTypeEnumValue,
					 oDevice.hanDeviceTypeEnumValue,
					 oDevice.lcmTypeEnumValue,
					 oDevice.waterMeterTypeEnumValue,
					 oDevice.gasMeterTypeEnumValue
					 ].join('')
			);
			if (oDevice.lifecycleStateEnum != null && oDevice.lifecycleStateEnum != undefined && oDevice.lifecycleStateEnum != '') {
				aTemp.push('');
				aTemp.push(oDevice.lifecycleStateEnum);
			} else {
				aTemp.push('');
			}
		});

		aParameters.push(aTemp.join("|"));

		return sensus.util.page.createDevice(aParameters);
	},

	summary : {
		/**
		 * The dialog title.
		 */
		title : sensus.locale.get("action.processingsummary.title"),

		width : 1678,

		minheight: 700,
		/**
		 * Whether this dialog requires a smartpoint list.
		 */
		requiresSmartpoints : false,

		/**
		 * The function that will be called when the action dialog is launched.
		 *
		 * @param actionDialog
		 *            a reference to the actionDialog objext
		 */
		open : function() {
			sensus.util.page.stopProgressBar();
		},

		action : function(actionDialog) {

			var $actionDialog = $("#action-dialog");

			$actionDialog.summary(sensus.util.globalActions.sId, sensus.util.globalActions.sType);

			$actionDialog.dialog('open');
		}
	}
}
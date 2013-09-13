sensus.pages.deviceactions = {

	/*
	 * Premise Id Dialog
	 * @param premiseId
	 * @param [Object] Device object
	 */
	premiseIdDialog : function (premiseId, oDeviceObj) {

		var fnCallback = function (oResponse) {

			var aDevices;
			var	aTableRows;
			var	max;
			var	i;
			var	sWaterMeterEnum;
			var	sDeviceTypeEnum;
			var	sDeviceSubType;
			var	sType;
			var	sMeterType;
			var	sStatus;
			var	oDevice;
			var	oRadio;
			var	oConfig;
			var	sDeviceId;
			var	iFlexNetId;
			var	decorators;
			var oTimeZone;

			if (oResponse && oResponse.devices && oResponse.devices.length) {

				aDevices		= oResponse.devices;
				aTableRows		= [];
				max				= aDevices.length;
				i 				= 0;
				sWaterMeterEnum = sensus.constants.services.water.meter.name;
				sGasMeterEnum   = sensus.constants.services.gas.meter.name;
				sType 			= "";
				sMeterType 		= "";
				decorators		= sensus.pages.device.module.decorators;

				oTimeZone = sensus.pages.device.module.util.getTimeZoneMinutes(oDeviceObj);

				for (; i < max; i = i + 1) {

					oDevice 		= aDevices[i];
					sDeviceTypeEnum = oDevice.deviceType || "";
					sDeviceId 		= oDevice.deviceId;
					oRadio 			= oDevice.radio || {};
					iFlexNetId		= oRadio.flexNetId || "";
					oConfig			= oDevice.configuration || {};
					sMeterType 		= sensus.locale.get("main.page.service." + sDeviceTypeEnum.split("_")[0]);

					aTableRows.push("<tr>");

					// Type
					if (sDeviceTypeEnum.contains("METER")) {

						// Whether device is METER (WATER_METER, ELECTRIC_METER, GAS_METER) get flexNet Id
						sNetworkAddress = iFlexNetId;
						sType 			= sensus.locale.get("commons.pages." + sDeviceTypeEnum);
						sDeviceSubType	= oDevice.electricMeterTypeEnum || oDevice.waterMeterTypeEnum || oDevice.gasMeterTypeEnum;

						aTableRows.push("<td colspan='2'>");

					} else {

						// Whether device is HAN_DEVICE or LCM
						sNetworkAddress = oDevice.macAddress;
						sDeviceSubType	= oDevice.hanDeviceTypeEnum || oDevice.lcmTypeEnum;
						sMeterType 		= sensus.locale.get("main.page.service.ELECTRIC");
						sType 			= sensus.locale.get("commons.pages." + sDeviceSubType);

						aTableRows.push("<td class='checkbox'><span class='icn-node icon-small' /></td><td>");
					}

					// Whether device on array is equals to device on current detail page
					if (sDeviceId == oDeviceObj.deviceId) {

						aTableRows.push("<strong>" + sType + "</strong>");

					} else {

						aTableRows.push("<a class='alist' href='device/detail?id="
								+ iFlexNetId +
								"&deviceType=" + sDeviceTypeEnum +
								"&typeEnum=" + sDeviceSubType + "'>" +
								sType + "</a>");
					}

					aTableRows.push("</td>");

					// meter type
					aTableRows.push("<td>");
					aTableRows.push(sMeterType);
					aTableRows.push("</td>");

					// description
					aTableRows.push("<td>");
					aTableRows.push(oDevice.deviceModel ? oDevice.deviceModel.description : "");
					aTableRows.push("</td>");

					// device id
					aTableRows.push("<td>");
					aTableRows.push(sDeviceId);
					aTableRows.push("</td>");

					// network address
					aTableRows.push("<td>");
					aTableRows.push(sNetworkAddress);
					aTableRows.push("</td>");

					// install date, added
					aTableRows.push("<td>");
					aTableRows.push(oConfig.installDate ? $.date.dateFormat(oConfig.installDate, sensus.settings.dateFormatMask.replace("yyyy", "yy"), oTimeZone) : "");
					aTableRows.push("</td>");

					// status
					if (sDeviceTypeEnum == sWaterMeterEnum || sDeviceTypeEnum == sGasMeterEnum) {

						sStatus = decorators.getStatus(oDevice.status);

					} else {

						sStatus = oDevice.lifecycleStateEnum;

						if (sStatus) {

							sStatus = sensus.locale.get("enum.lifecycle.state." + sStatus);

						} else {

							sStatus = "";
						}
					}

					aTableRows.push("<td>");
					aTableRows.push(sStatus);
					aTableRows.push("</td>");

					aTableRows.push("</tr>");
				}

				if (max > 0) {

					$("#premiseIdTable tbody").html(aTableRows.join(''));
				}
			}
		},

		oRequest = {premiseId : premiseId},
		sApiUrl = "api/electricdevice/fetch";

		return {

			title : sensus.locale.get("commons.pages.premiseId"),

			width : 900,

			height : 500,

			action : function (actionDialog) {

				actionDialog.dialog("option", "title", this.title + " #" + premiseId);

				actionDialog.load("device/premiseIdDialog", function () {

					$("#premiseIdText").text(sensus.locale.get("smartpointdetail.dialog.premiseId.text") + premiseId);

					$.ajaxValidator.fnDoCall(sApiUrl, oRequest, false, fnCallback);
				});

				actionDialog.dialog('open');
			}
		};
	}
}
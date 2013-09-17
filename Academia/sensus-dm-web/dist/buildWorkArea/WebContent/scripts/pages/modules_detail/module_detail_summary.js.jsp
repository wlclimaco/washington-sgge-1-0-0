<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
	sensus.pages.device.module.summaryDatas = {

		lifecycle : {

			init : function (oResponse) {

				var module = sensus.pages.device.module;
				var oDevice = module.request.get("device", oResponse);

				if (oDevice && oDevice.status) {

					$("#lifecycle strong").text(module.decorators.getStatus(oDevice.status));
				}
			}
		},

		transmit : {

			init : function (oResponse) {

				var module = sensus.pages.device.module;
				var oDevice = module.request.get("device", oResponse);
				var sMode;

				if (oDevice) {

					sMode = oDevice.configuration.transmitterOperationalModeEnum || "";

					$("#transmit strong").html(oDevice.transmitRate ? (oDevice.transmitRate + "<sup>MIN</sup>") : "");

					if (sMode != "") {

						$("#transmit").append("<small class='sub-head'>"
								+ module.summaryDatas.transmitMode.getTransmitMode(sMode) + " "
								+ sensus.locale.get("communication.page.mode") + "</small>");
					}
				}
			}
		},

		transmitMode : {

			BOOST : sensus.locale.get("communication.page.transmitMode.BOOST") + " / ",
			NORMAL_MIX : sensus.locale.get("communication.page.transmitMode.NORMAL_MIX"),
			MPASS_NORMAL : sensus.locale.get("communication.page.transmitMode.MPASS_NORMA"),

			getTransmitMode : function (sTransmitMode) {

				if (!sTransmitMode) {

					return null;
				}

				if (sTransmitMode.contains("__")) {

					return this.replaceNormalMix(this.replaceBoost(this.replaceBy(sTransmitMode)));
				}

				if (sTransmitMode.contains("BY")) {

					return this.replaceMpassNormal(this.replaceBy(sTransmitMode));
				}

				return sensus.locale.get("communication.page.transmitMode." + sTransmitMode);
			},

			replaceBy : function (sTransmitMode) {

				var by = sTransmitMode.substring(sTransmitMode.lastIndexOf("_") + 1, sTransmitMode.length);
				by = "(" + by.split("BY").join(":") + ")";

				return sTransmitMode.substring(0, sTransmitMode.lastIndexOf("_")) + " " + by;
			},

			replaceBoost : function (sTransmitMode, sUnders) {

				return sTransmitMode.replace("BOOST" + (sUnders || "__"), this.BOOST);
			},

			replaceNormalMix : function (sTransmitMode) {

				return sTransmitMode.replace("NORMAL_MIX", this.NORMAL_MIX);
			},

			replaceMpassNormal : function (sTransmitMode) {

				return sTransmitMode.replace("MPASS_NORMAL", this.MPASS_NORMAL);
			}
		},

		lastRead : {

			init : function (oResponse) {

				var module = sensus.pages.device.module;
				var aLoadProfiles = module.request.get("loadProfiles",  oResponse);
				var oLoadProfile = aLoadProfiles[0] || {};
				var dLastReadTime = oLoadProfile.lastReadTime;
				var $lastRead;
				var aLastReasValue;
				var oTimeZone;

				if (oLoadProfile.lastReadValue) {

					oTimeZone = module.util.getTimeZoneMinutes(module.request.get("device", oResponse));

					$lastRead = $("#lastRead strong");

					aLastReadValue = oLoadProfile.lastReadValue.split(" ");

					if (aLastReadValue.length > 1) {

						$lastRead.html(aLastReadValue[0] + "<sup>" + aLastReadValue[1] + "</sup>");

					} else {

						$lastRead.html(aLastReadValue[0]);
					}

					if (dLastReadTime) {

						$lastRead.next().text($.date.dateFormat(dLastReadTime, "MM d, yy at h:ia", oTimeZone));
					}
				}
			}
		},

		smartPoint : {

			init : function (oResponse) {

				var module 		= sensus.pages.device.module;
				var oDevice 	= module.request.get("device", oResponse) || {};
				var oRadio 		= oDevice.radio || {flexNetId : ""};
				var iFlexNetId 	= oRadio.flexNetId || "";
				var sDeviceType = oDevice.deviceType || "";

				$("#smartPoint strong > span").text(iFlexNetId);
			}
		},

		messageReceived : {

			init : function (oResponse) {

				var module 		= sensus.pages.device.module;
				var oDevice 	= module.request.get("device", oResponse) || {};
				var oTimeZone 	= module.util.getTimeZoneMinutes(oDevice);

				if (oDevice && oDevice.lastHeard) {

					$(".stamp-smartpoint").removeClass("hide");
					$("#receivedDate").text($.date.dateFormat(oDevice.lastHeard, "MM d, yy at h:ia", oTimeZone));

				}
			}
		},

		deviceInformation : {

			fillDeviceInformation : function (oDevice) {

				var	oConfig 		= oDevice.configuration || {};
				var	oRadio			= oDevice.radio || {};
				var	oLocation		= oRadio.location || {};
				var	sDeviceId		= oDevice.deviceId;
				var	$summary		= $("#detail-header-container table");
				var	oTouRead 		= $("#touRead");
				var	oLoadProfile 	= $('#loadProfile');
				var	sLoadProfile 	= sensus.locale.get("smartpointdetail.page.loadprofiletab");
				var decorators		= sensus.pages.device.module.decorators;
				var	oDeviceConfiguration = oDevice.configuration;

				// Device ID
				$summary.find("h1").html(decorators.getDeviceInfo(oDevice));

				// Address
				$summary.find(".description-address").text(decorators.getAddress(oLocation));

				// Tabs TOU and Load Profile or Interval Reads
				if (oDevice.deviceType == sensus.constants.services.electric.meter.name) {

					if (oDeviceConfiguration.touEnable != undefined) {

						if (oDeviceConfiguration.touEnable != true) {
							oTouRead.hide();
						}

					} else {
						oTouRead.hide();
					}

					if (oDeviceConfiguration.esmEnable != undefined) {

						if (oDeviceConfiguration.esmEnable == true) {

							oLoadProfile.text(sLoadProfile).attr("href", "device/loadProfiles");

						} else {

							oLoadProfile.text(sensus.locale.get("smartpointdetail.page.intervalReadstab"));
						}

					} else {

						oLoadProfile.text(sLoadProfile).attr("href", "device/loadProfiles");
					}
				}
			},

			init : function (oResponse) {

				var module = sensus.pages.device.module;
				var oDevice = module.request.get("device", oResponse);

				if (oDevice) {

					module.summaryDatas.deviceInformation.fillDeviceInformation(oDevice);
				}
			}
		},

		networkStatus : {

			fillNetworkStatus : function (oDevice) {

				var _electric;
				var $networkStatus = $('#network-status-value');

				if (oDevice && oDevice.lifecycleStateEnum) {

					_electric = sensus.constants.services.electric;

					if (oDevice.deviceType == _electric.meter.name
							|| oDevice.lcmTypeEnum == _electric.lcm.flexNetLCM) {

						$networkStatus.siblings("p").text(sensus.locale.get("commons.pages.lifeCycleState"));
					}

					$networkStatus.text(sensus.locale.get("commons.pages." + oDevice.lifecycleStateEnum));
				}
			},

			init : function (oResponse) {

				var module = sensus.pages.device.module;

				module.summaryDatas.networkStatus.fillNetworkStatus(
						module.request.get("device", oResponse));
			}
		},

		parent : {

			fillParent : function (oDevice) {

				var oLcmEnum = sensus.constants.services.electric.lcm;
				var $parent = $('#parent-value');

				// Whether FLEXNET_LCM Type hide parent because it is owner father
				if (oDevice.deviceType == oLcmEnum.name && oDevice.lcmTypeEnum == oLcmEnum.flexNetLCM) {

					$parent.parent().hide();

				} else {

					// Whether any HAN or LCM Type has parent id
					if (oDevice && oDevice.electricMeterFlexNetId) {

						$parent.html("<a href='device/detail?id=" + oDevice.electricMeterFlexNetId
								+ "&deviceType=" + sensus.constants.services.electric.meter.name + "' class='alist'>"
								+ oDevice.electricMeterFlexNetId + "</a>");
					}
				}
			},

			init : function (oResponse) {

				var module = sensus.pages.device.module;
				var oDevice = module.request.get("device", oResponse);

				if (oDevice) {

					module.summaryDatas.parent.fillParent(oDevice);
				}
			}
		},

		premiseId : {

			setPremiseId : function ($summaryPremiseId, iPremiseId, iPremiseIdDeviceCount, oDevice) {

				if (iPremiseId) {

					$summaryPremiseId.find("a.request-premise-detail").text("#" + iPremiseId).click(function(e) {

						e.preventDefault();

						sensus.util.actiondialog.launchActionDialog("premiseIdDialog",
								sensus.pages.deviceactions.premiseIdDialog(iPremiseId, oDevice));
					});

					if (iPremiseIdDeviceCount) {

						$summaryPremiseId.find("small.sub-head").text(iPremiseIdDeviceCount + " "
								+ sensus.locale.get("commons.pages.smartPoints"));
					}

				} else {

					$summaryPremiseId.find(".request-premise-detail").remove();
					$summaryPremiseId.find("strong.value").text("--");
				}
			},

			getParentDevice : function (oDevice) {

				var oResponse;

				// Whether device has parent
				if (oDevice.electricMeterFlexNetId) {

					oResponse = $.ajaxValidator.fnDoCall("api/electricdevice/fetch", {
						id : oDevice.electricMeterFlexNetId,
						deviceType : sensus.constants.services.electric.meter.name
					}, false, null, null, true);

					if (oResponse && oResponse.firstDevice) {

						return oResponse.firstDevice;
					}
				}

				return oDevice;
			},

			fillPremiseId : function (oDevice) {

				var $summaryPremiseId = $("#summaryPremiseId");
				var iPremiseId;
				var iPremiseIdDeviceCount;

				if (oDevice.configuration) {

					// Whether device has not premise id try get from parent
					if (!oDevice.configuration.premiseId) {

						oDevice = this.getParentDevice(oDevice);
					}

					iPremiseId = oDevice.configuration.premiseId;
					iPremiseIdDeviceCount = oDevice.configuration.premiseIdDeviceCount;
				}

				this.setPremiseId($("#summaryPremiseId"), iPremiseId, iPremiseIdDeviceCount, oDevice);
			},

			init : function (oResponse) {

				var module = sensus.pages.device.module;
				var oDevice = module.request.get("device", oResponse);

				if (oDevice) {

					module.summaryDatas.premiseId.fillPremiseId(oDevice);
				}
			}
		},

		networkAddress : {

			fillNetworkAddress : function (oDevice) {

				var sNetworkAddress = oDevice.macAddress || oDevice.radio.flexNetId || "--";

				if (!$.sc.isNullOrUndefined(oDevice) && !$.sc.isNullOrUndefined(sNetworkAddress)) {
					$("#summaryNetworkAddress .value").text(sNetworkAddress);
				}
			},

			init : function (oResponse) {

				var module  = sensus.pages.device.module;
				var oDevice = module.request.get("device", oResponse);

				if (oDevice) {
					module.summaryDatas.networkAddress.fillNetworkAddress(oDevice);
				}
			}
		},

		installedDate : {

			fillInstalledDate : function (oDevice) {

				var $installedDate 	= $("#summaryInstalledDate .value");
				var sInstallDate 	= oDevice.configuration.installDate;

				if (!$.sc.isNullOrUndefined(oDevice) && !$.sc.isNullOrUndefined(sInstallDate)) {

					var oTimeZone 	= sensus.pages.device.module.util.getTimeZoneMinutes(oDevice);

					$installedDate.text($.date.dateFormat(sInstallDate,
						   sensus.settings.dateFormatMask.replace("yyyy", "yy"), oTimeZone));

				} else {
					$installedDate.text("--");
				}
			},

			init : function (oResponse) {

				var module = sensus.pages.device.module;
				var oDevice = module.request.get("device", oResponse);

				if (oDevice) {
					module.summaryDatas.installedDate.fillInstalledDate(oDevice);
				}
			}
		},

		connectedDate : {

			fillConnectedDate : function (oDevice) {

				var sDateFormat;
				var $connectedDate;
				var oLcmEnum;
				var sInstallDate;

				if (oDevice) {

					sDateFormat = sensus.settings.dateFormatMask.replace("yyyy", "yy");
					oLcmEnum = sensus.constants.services.electric.lcm;
					sInstallDate = "--";
					$connectedDate = $("#connected-date-value");

					if (oDevice.deviceType == oLcmEnum.name
							&& oDevice.lcmTypeEnum == oLcmEnum.flexNetLCM) {

						if (oDevice.lifecycleStateEnum == "INSTALLED" && oDevice.configuration.installDate) {

							sInstallDate = ' '+ $.date.dateFormat(oDevice.configuration.installDate, sDateFormat);
						}

						$connectedDate.siblings("p").text(sensus.locale.get("smartpointdetail.tabs.about.installedDate"));

					} else {

						if (oDevice.lifecycleStateEnum == "JOINED" && oDevice.configuration.installDate) {

							sInstallDate = $.date.dateFormat(oDevice.configuration.installDate, sDateFormat);
						}
					}

					$connectedDate.text(sInstallDate);
				}
			},

			init : function (oResponse) {

				var module = sensus.pages.device.module;

				module.summaryDatas.connectedDate.fillConnectedDate(
						module.request.get("device", oResponse));
			}
		},

		actions : {

			connectFunctions : {

				fnApplyHanDevice : function (oData) {

					var hanDeviceType = oData.sDeviceType || "";

					var connectHanDevice = new ConnectHanDevice({
							devices			: (oData.devices || null),
							installCode		: (oData.sInstallCode || null),
							macAddress		: (oData.sMacAddress  || null),
							manufacture		: (oData.sManufacture || null),
							model			: (oData.sModelNumber || null),
							actionType		: {id: parseInt(oData.iActionId,10)},
							actionTypeName	: oData.sActionName,
							actionTime		: $.date.setDateServer(),
							onDemand		: true,
							isMonitored		: true,
							customerId		: (oData.sCustomerId || null),
							hanDeviceType	: hanDeviceType,
							deviceId		: (oData.sDeviceId || null),
							clientEndPointId: (oData.sClientEndPointId || null),
							flexNetId		: (oData.sFlexNetId || null)
					});

					var fnCallback = function () {

						sensus.pages.device.module.reloadModules();
					}

					var sUrl = 'api/importFile/upload/action/importFile';

					$.ajaxValidator.fnMonitor(sUrl, {parameters : connectHanDevice}, false, fnCallback,
							sensus.locale.get("action.connectHanDevice.confirm"), null, "messaging-smartpoint-detail");
				},

				/**
				 * Validate the connect Dialog
				 * @param {Object} oData - The data with the field values.
				 * @returns Return true if there is some field that does not match with validation
				 */
				fnValidateInsertHanDevice : function (oData) {

					var sSelect 		= sensus.locale.get('commons.pages.select');
					var	oDeviceType 	= $('#select-device-types');
					var	oDeviceId 		= $("#device_name_input");
					var	oManufacture 	= $("#select-manufacture");
					var	oModelNumber 	= $("#select-model-number");
					var	oInstallCode 	= $("#install_code");
					var sFieldRequired	= sensus.locale.get('commons.pages.fieldRequired');
					var sMaxLength 		= sensus.locale.get('smartpointdetail.form.name.maxlength');

					// Validations selects and input
					if (oData.sDeviceType == sSelect || oData.sDeviceId == ""
						|| oData.sManufacture == sSelect || oData.sModelNumber == sSelect) {

						if (oData.sDeviceType == sSelect) {

							oDeviceType.validationEngine('showPrompt', sFieldRequired, 'red', '', true);
						}

						if (oData.sDeviceId == "") {

							oDeviceId.validationEngine('showPrompt', sFieldRequired, 'red', '', true);
							oDeviceId.val("");
						}

						if (oData.sManufacture == sSelect) {

							oManufacture.validationEngine('showPrompt', sFieldRequired, 'red', '', true);
						}

						if (oData.sModelNumber == sSelect) {

							oModelNumber.validationEngine('showPrompt', sFieldRequired, 'red', '', true);
						}

						return true;
					}

					if (oData.sDeviceId.length > 25 || oData.oInstallCode.length > 25) {

						if (oData.sDeviceId.length > 25) {

							oDeviceId.validationEngine('showPrompt', sMaxLength, 'red', '', true);
							oDeviceId.val("");
						}

						if (oData.oInstallCode.length > 25) {

							oInstallCode.validationEngine('showPrompt', sMaxLength, 'red', '', true);
							oInstallCode.val("");
						}

						return true;
					}

					return false;
				},

				/**
				 * Validate the connect Dialog
				 * @param {Object} oData - The data with the field values.
				 * @returns Return true if there is some field that does not match with validation
				 */

				fnValidateConnectToHanFields : function (oData, bExists) {

					var regEx = /^\s+$/;

					// Validation inputs
					if (oData.sInstallCode == "" || oData.sInstallCode.search(regEx) == 0
							|| oData.sDeviceId == "" || oData.sDeviceId.search(regEx) == 0
							|| oData.sInstallCode.contains(' ') || bExists == false) {

						var oInstallCode 	= $("#install_code");
						var oDeviceId 		= $("#device_id");

						if ((oData.sInstallCode == "") || (oData.sInstallCode.search(regEx) == 0)) {

							oInstallCode.validationEngine('showPrompt', sensus.locale.get('commons.pages.fieldRequired'), 'red', '', true);
							oInstallCode.val("");
						}

						if ((oData.sClientEndPointId == "") || (oData.sClientEndPointId.search(regEx) == 0)) {

							oDeviceId.validationEngine('showPrompt', sensus.locale.get('commons.pages.fieldRequired'), 'red', '', true);
							oDeviceId.val("");

						}

						 if ((oData.sInstallCode.contains(' '))) {

							 oInstallCode.validationEngine('showPrompt', sensus.locale.get('commons.pages.removeSpace'), 'red', '', true);
						 }

						 if (bExists == false) {

							 oDeviceId.validationEngine('showPrompt', sensus.locale.get('commons.pages.parentIdNotFound'), 'red', '', true);
						 }

						return true;
					}

					return false;
				},

				/**
				 * Validate the connect Dialog
				 * @param {Object} oData - The data with the field values.
				 * @returns Return true if there is some field that does not match with validation
				 */
				fnValidateConnectHanFields : function (oData) {

					// Regular Expression for validate the fields
					var regExMac 		= /^([a-fA-F0-9][a-fA-F0-9]:){7}[a-fA-F0-9][a-fA-F0-9]$/;
					var	regExInstall 	= /^\s+$/;

					if (oData.sInstallCode == "" || oData.sInstallCode.contains(' ')
						|| oData.sInstallCode.search(regExInstall) == 0 || oData.sMacAddress.search(regExMac) == -1) {

						var oInstallCode = $("#install_code");

						// Validation field Install Code
						if (oData.sInstallCode == "" || oData.sInstallCode.search(regExInstall) == 0) {

							oInstallCode.validationEngine('showPrompt', sensus.locale.get('commons.pages.fieldRequired'), 'red', '', true);
							oInstallCode.val("");
						}

						if (oData.sInstallCode.contains(' ')) {
							oInstallCode.validationEngine('showPrompt', sensus.locale.get('commons.pages.removeSpace'), 'red', '', true);
						}

						// Validation field Mac Address
						if (oData.sMacAddress.search(regExMac) == -1) {

							var oMacAddress = $("#mac_address");
							oMacAddress.validationEngine('showPrompt', sensus.locale.get('sensus.epm.enter.invalidMacAddress'), 'red', '', true);
						}

						return true;
					}

					return false;
				},

				/**
				 * Events to clear validation error message.
				 * @param {Object} actionDialog - The Dialog DOM Object.
				 */
				fnClearValidationEvents : function (actionDialog) {

					// Remove div of validation error
					$("#install_code", actionDialog).focus(function() {

						$(".install_codeformError", actionDialog).remove();

					});

					// Remove div of validation error
					$("#mac_address", actionDialog).focus(function() {

						$(".mac_addressformError", actionDialog).remove();

					});

					// Remove div of validation error
					$("#select-device-types", actionDialog).click(function() {

						$(".select-device-typesformError", actionDialog).remove();

					});

					// Remove div of validation error
					$("#select-manufacture", actionDialog).click(function() {

						$(".select-manufactureformError", actionDialog).remove();

					});

					// Remove div of validation error
					$("#select-model-number", actionDialog).click(function() {

						$(".select-model-numberformError", actionDialog).remove();

					});

					// Remove div of validation error
					$("#device_name_input", actionDialog).focus(function() {

						$(".device_name_inputformError", actionDialog).remove();

					});
				}
			},

			connectHANDevice : {

				fnFillExistingDevice : function (device, oDom) {

					// Show Device Information Container.
					oDom.oFormInfo.removeClass("hide");
					oDom.oAddFields.addClass("hide");

					// Change Icon
					oDom.oMacAddressValidate.removeClass("icn-sending").addClass("icn-valid-form");

					// Set Device Type Enum
					if (sensus.constants.services.electric.han.name == device.deviceType) {
						oDom.oDeviceType.text(device.hanDeviceTypeEnum);
					}

					if (sensus.constants.services.electric.lcm.name == device.deviceType) {
						oDom.oDeviceType.text(device.lcmTypeEnum);
					}

					// Set Values
					oDom.oDeviceId.text(device.deviceId);
					oDom.oManufacture.text(device.deviceModel.manufacture);
					oDom.oModelNumber.text(device.deviceModel.description);
					oDom.oCustomerId.text(device.radio.customerId);
					oDom.oDeviceTypeId.text(device.deviceType);
					oDom.oDeviceTypeId.text(device.deviceType);
					oDom.oFlexNetId.text(device.radio.flexNetId);
				},

				fnFillManufactureByType : function (typeId, oDom) {

					oDom.oSelectManufacture.empty();
					oDom.oSelectModelNumber.empty();
					oDom.oSelectModelNumber.attr("disabled", "true");

					if (oDom.oSelectDeviceTypes.val() != sensus.locale.get("commons.pages.select")) {

						oDom.oSelectManufacture.removeAttr("disabled");

						function fnCallback (response) {

							if (response && response.devices && response.devices.length) {

								var aOptions 			= ["<option name='0'>Select</option>"];
								var	iHanDevicesLength 	= response.devices.length;
								var	sManufacture;

								for (var i = 0; i < iHanDevicesLength; i++) {

									sManufacture = response.devices[i].deviceModel.manufacture;
									aOptions.push("<option name="+ sManufacture +">" + sManufacture + "</option>");

								}

								oDom.oSelectManufacture.append(aOptions.join(""));
							}
						}

						return $.ajaxValidator.fnDoCall("api/electricdevice/fetch", {type : "manufacture", typeId : typeId}, false, fnCallback);

					} else {
						oDom.oSelectManufacture.attr("disabled", "true");
					}
				},

				fnFillModelNumberByManufacture : function (typeId, sManufacture, oDom ) {

					oDom.oSelectModelNumber.empty();

					if (oDom.oSelectManufacture.val() != sensus.locale.get("commons.pages.select")) {

						oDom.oSelectModelNumber.removeAttr("disabled");

						function fnCallback (response) {

							if (response && response.devices && response.devices.length) {

								var aOptions = ["<option name='0'>Select</option>"],
									iHanDevicesLength = response.devices.length,
									sModel;

								for (var i = 0; i < iHanDevicesLength; i++) {

									sModel = response.devices[i].deviceModel.description;
									aOptions.push("<option name="+ sModel +">" + sModel + "</option>");

								}

								oDom.oSelectModelNumber.append(aOptions.join(''));
							}
						}

						return $.ajaxValidator.fnDoCall("api/electricdevice/fetch",
								{type : "model", typeId : typeId, manufacture : sManufacture}, false, fnCallback);

					} else {
						oDom.oSelectModelNumber.attr("disabled", "true");
					}
				},

				fnFillNonExistingDevice : function (oDom) {

					var oSelectDeviceTypes = $("#select-device-types");
					var data 	 = [];
					var sOptions = "";

					oSelectDeviceTypes.empty();

					data.push({id : 0, value : 'Select'})
					data.push({id : 0, value : 'THERMOSTAT'})
					data.push({id : 1, value : 'LCM'})
					data.push({id : 2, value : 'IHD'})

					for (var i = 0; i <= 3; i++) {
						sOptions += "<option name='"+ data[i].id +"'>" + data[i].value + "</option>";
					}

					oSelectDeviceTypes.append(sOptions);
				},

				fnGetHanDeviceByDeviceResponse : function (id) {

					return $.ajaxValidator.fnDoCall("api/electricdevice/fetch",
						{macAddress : id, deviceType: "HAN_DEVICE", connectHanDevice : true}, false);
				}
			},

			/**
			 * Get "Remove Action Object"
			 * Create the "Remove Action Object" to exclude some actions base on device
			 *
			 * @param sDeviceType
			 * 			{String}, the device type (ELECTRIC_METER, HAN_DEVICE, LCM..)
			 * @param sDeviceSubType
			 * 			{String}, the device sub type (IHD, FLEXNET_LCM, THERMOSTAT..)
			 * @param oDevice
			 * 			{Object}, the device
			 */
			getRemoveActionObject : function (sDeviceType, sDeviceSubType, oDevice) {

				var actions 		= sensus.pages.device.module.summaryDatas.actions;
				var oRemoveAction 	= {};

				// Wheter device is LCM or HAN_DEVICE
				if (sDeviceSubType) {

					// Whether device is connected:
					if (actions.validators.isConnect(oDevice)) {

						// remove connect to han action
						oRemoveAction["sensus.dm.action.connect.to.han"] = true;

					} else {

						// remove get demand response event status and disconnect han device actions
						oRemoveAction["sensus.dm.action.get.demand.response.event.status"] = true;
						oRemoveAction["sensus.dm.action.disconnect.han.device"] = true;
					}

				// Wheter device is ELECTRIC_METER
				} else if (sDeviceType == sensus.constants.services.electric.meter.name) {

					if (actions.validators.isRemoteConnectAvailable(oDevice)) {

						oRemoveAction["sensus.dm.action.remote.connect"] = true;
						oRemoveAction["sensus.dm.action.remote.disconnect"] = true;
						oRemoveAction["sensus.dm.action.remote.arm.connect"] = true;
					}
				}

				return oRemoveAction;
			},

			validators : {

				/**
				 * Valid if device is connect
				 *
				 * @param oDevice
				 * 			{Object}, the device
				 * @return {Boolean}, wheter device life cycle state is JOINED, return true (Connect)
				 */
				isConnect : function (oDevice) {

					return oDevice.lifecycleStateEnum == "JOINED";
				},

				/**
				 * Valid if device is Remote Connect Available
				 *
				 * @param oDevice
				 * 			{Object}, the device
				 * @return {Boolean}, wheter device configuration of remote connect available is REMOTE_DISCONNECT
				 */
				isRemoteConnectAvailable : function (oDevice) {

					if (oDevice.configuration) {

						return oDevice.configuration.remoteConnectAvailableEnum != "REMOTE_DISCONNECT";
					}

					return true;
				}
			},

			/**
			 * Get Device Type Models
			 *
			 * @param aDeviceTypePermissions
			 * 			{Array}, all device type permissions allowed on a service (electric, water, gas..)
			 * @param sDeviceTypeEnum
			 * 			{String}, device type (ELECTRIC_METER, HAN_DEVICE, LCM, WATER_METER..)
			 * @return {Object} Device Type Model reference of device type enum
			 */
			getDeviceTypeModels : function (aDeviceTypePermissions, sDeviceTypeEnum) {

				return $.grep(aDeviceTypePermissions, function (o, i) {

					return sDeviceTypeEnum == o.deviceType;

				})[0].deviceTypeModels;
			},

			/**
			 * Get Actions
			 *
			 * @param aDeviceTypeModels
			 * 			{Array}, all device type models of specified device
			 *
			 * @param oTypeModels
			 * 			{Object}, this object define what models get from device type models array
			 * 			example: {
			 * 					COMMOM : true,
			 * 					FLEXNET_LCM : true
			 * 			}
			 *
			 * @param oDevice
			 * 			{Object}, the device object from fetch by id
			 *
			 * @param oRemoveAction
			 * 			{Object}, an object with actions to remove
			 * 			example : {
			 * 					"sensus.dm.action.get.demand.response.event.status" : true
			 * 			}
			 *
			 * @return {Array}, based on object examples, the return will be all actions
			 * 					allowed for FLEXNET_LCM device
			 */
			getActions : function (aDeviceTypeModels, oTypeModels, oDevice, oRemoveAction) {

				var i = 0;
				var length;
				var actions = [];

				var arr = $.grep(aDeviceTypeModels, function (o, i) {
					return oTypeModels[o.name];
				});

				length = arr.length;

				if (length == 1) {
					actions = arr[0].actions;
				} else {

					for (; i < length; i = i + 1) {
						actions = actions.concat(arr[i].actions);
					}
				}

				return $.grep(actions, function(o, i) {
					return !oRemoveAction[o.actionTypeEnumNameValue];
				});
			},

			/**
			 * Fill Actions based on device and start the action buttons
			 *
			 * @param oDevice
			 * 			{Object}, the device from fetch by id
			 * 			some device atributes is need for fill actions, like type and sub device type
			 */
			fillActions : function (oDevice) {

				var module 				= sensus.pages.device.module;
				var actions 			= module.summaryDatas.actions;
				var actionrequestutil	= sensus.util.actionrequestutil;
				var sDeviceSubType		= oDevice.hanDeviceTypeEnum || oDevice.lcmTypeEnum || null;
				var sDeviceType			= oDevice.deviceType;
				var aDeviceTypeModels 	= actions.getDeviceTypeModels(sensus.settings.oDeviceTypeParameters.deviceTypePermissions, sDeviceType);
				var oTypeModels 		= {COMMON : true};
				var oRemoveAction;
				var actions;

				// Create the "Type Models Object" to exclude some actions base on device type
				if (sDeviceSubType) {
					oTypeModels[sDeviceSubType] = true;
				}

				// Create the "Remove Action Object" to exclude some actions base on device
				oRemoveAction = actions.getRemoveActionObject(sDeviceType, sDeviceSubType, oDevice);

				actions = actions.getActions(aDeviceTypeModels, oTypeModels, oDevice, oRemoveAction);

				actions = actionrequestutil.fnLoadDefaultActions(actions);

				// Initialize action buttons
				sensus.util.page.menuPlug(module,
						actionrequestutil.menuPlugCallBack,
						actions, null, true);
			},

			init : function (oResponse) {

				var module = sensus.pages.device.module;
				var oDevice = module.request.get("device", oResponse);

				if (oDevice) {

					module.summaryDatas.actions.fillActions(oDevice);
				}
			}
		}
	}
	</script>

</sec:authorize>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
	sensus.pages.device.module = {

			loadModules : function (sModuleType, aModules, oResponse) {

				var aModuleConfigs = sensus.pages.device.module[sModuleType];
				var i = 0;
				var length = aModules ? aModules.length : 0;
				var sModule;

				for (; i < length; i = i + 1) {

					sModule = aModules[i];

					if (aModuleConfigs[sModule]) {

						aModuleConfigs[sModule].init(oResponse);
					}
				}
			},

			reloadModules : function () {

				sensus.util.page.startProgressBar();

				$.fn.pageLoader.load($.fn.pageLoader.currentPage() + "?" + $.fn.pageLoader.queryString(), $("#load"));

				sensus.util.page.stopProgressBar();
			},

			decorators : {

				status : {
					FIXED_BASE_MOM	 : sensus.locale.get("filter.status_meter.fixed_base_mom"),
					FIXED_BASE_LAT 	 : sensus.locale.get("filter.status_meter.fixed_base_lat"),
					WALK_BY_DRIVE_BY : sensus.locale.get("filter.status_meter.walk_by_drive_by"),
					IDLE 			 : sensus.locale.get("filter.status_meter.idle"),
					UNKNOWN 		 : sensus.locale.get("filter.status_meter.unknown"),
				},

				getStatus : function (value) {

					if (value) {
						return this.status[value];
					}
					return "";
				},

				getAddress : function (oLocation) {

					var sAddress 	= oLocation.address || "",
						sCity 		= oLocation.city || "",
						sState 		= oLocation.state || "",
						sZip 		= oLocation.zip || "";

					if (sAddress && sCity && sState && sZip) {

						return sAddress + " " + sCity + ", " + sState + " " + sZip;
					}
					return "";
				},

				getDeviceInfo : function (oDevice) {

					var sDeviceTypeEnum = oDevice.deviceType;

					// Whether LCM or FLEXNET_LCM Device
					if (sDeviceTypeEnum == "LCM") {

						sDeviceTypeEnum = oDevice.lcmTypeEnum;

					// Whether HAN Device
					} else if (sDeviceTypeEnum == "HAN_DEVICE") {

						sDeviceTypeEnum = oDevice.hanDeviceTypeEnum;
					}

					if (sDeviceTypeEnum) {

						return "<strong>"
							+ sensus.locale.get("commons.pages." + sDeviceTypeEnum)
							+ "</strong> " + (oDevice.deviceId || "");
					}

					return oDevice.deviceId || "";
				}
			},

			util : {

				/**
				 * Get Service Device Type
				 *
				 * @param sDeviceTypeEnum
				 * 			[String], Device Type Enum
				 * @return [String], the application service based on device type
				 */
				getServiceByDeviceType : function (sDeviceTypeEnum) {

					// Whether device is METER (WATER_METER, ELECTRIC_METER or GAS_METER)
					if (sDeviceTypeEnum.contains("METER")) {

						return sDeviceTypeEnum.split("_")[0].toLowerCase();
					}

					// Other device types
					switch (sDeviceTypeEnum) {
					case "HAN_DEVICE":
					case "LCM":

						return "electric";

					default:
						return "";
					}
				},

				/**
				 * Get the display minutes information of the device
				 * @param oDevice
				 * 			[Object] the device
				 * @returns oTimeZoneInfo
				 * 			[Object] Contains the time zone object
				 * 					 with the time zone in minutes or empty object
				 */
				getTimeZoneMinutes : function(oDevice) {

					var oTimeZoneInfo;

					if (oDevice && oDevice.radio && oDevice.radio.location && oDevice.radio.location.timeZoneInfo) {

						oTimeZoneInfo = oDevice.radio.location.timeZoneInfo;

						if (oTimeZoneInfo && oTimeZoneInfo.displayMinutes) {

							return {
								iTZMinutes 	: oTimeZoneInfo.displayMinutes,
								sTZName 	: oTimeZoneInfo.displayName
							};
						}
					}

					return {};
				},

				createLineInformation : function (aData) {

					return ["<tr><td class='left-td'>", aData[0], ":</td><td>", aData[1], "</td></tr>"].join("");
				},

				getFirstDeviceResponse : function (oResponse) {

					if (oResponse && oResponse.devices && oResponse.devices.length) {

						return oResponse.devices[0];
					}

					return null;
				},

				createDeviceRequest : function(oDevice) {

					var oDeviceRequest = null;

					if (!$.sc.isNullOrUndefined(oDevice)) {

						var oRadio = new Radio({

							flexNetId 	: oDevice.radio.flexNetId,
							customerId 	: oDevice.radio.customerId,
							location 	: oDevice.radio.location

						});

						oDeviceRequest = new Device({

							deviceId 	: oDevice.deviceId,
							radio 		: oRadio,
							deviceType 	: oDevice.deviceType

						});
					}

					return oDeviceRequest;
				},

				ajaxData : {

					fetchDeviceById : function () {

						var sServiceType = sensus.settings.serviceType,
							sId = $.address.parameter("id"),
							sDeviceType =  $.address.parameter("deviceType"),
							obj;

						switch (sServiceType) {

						case sensus.constants.services.electric.name :
							obj = {
								url : "api/electricdevice/fetch",
								data : {id : sId, deviceType : sDeviceType}
							};
							break;

						case sensus.constants.services.water.name :
							obj = {
								url : "api/watermeterdevice/fetch",
								data : {
									devices : [new Device({radio : new Radio({flexNetId : sId}), deviceType : sDeviceType})]
								}
							};
							break;

						case sensus.constants.services.gas.name :
							obj = {
								url : "api/gasmeterdevice/fetch",
								data : {
									devices : [new Device({radio : new Radio({flexNetId : sId}), deviceType : sDeviceType})]
								}
							};
							break;

						default :
							obj = {};
						}

						return obj;
					}
				}
			}
	}
	</script>

</sec:authorize>
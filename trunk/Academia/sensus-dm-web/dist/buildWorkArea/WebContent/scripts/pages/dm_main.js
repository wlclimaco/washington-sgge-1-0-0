sensus.pages.dm = {

		getSettings : function (bLocaleInit) {

			$.ajax({
				dataType    : 'json',
				type        : 'POST',
				url 		: 'fillSettings',
				contentType : 'application/json; charset=utf-8',
				async 		: false,
				success 	: function (oData) {

					var oDeviceTypeParameters = sensus.settings.oDeviceTypeParameters;
					sensus.settings = oData;
					sensus.settings.oDeviceTypeParameters = oDeviceTypeParameters;

					$("#version-number").text(oData.dmProduct + " " + oData.dmVersion);
					$("#build-number").text(oData.dmBuildVersion);
					$(".logo h1", "#hd-user").text(oData.customer);

					if (bLocaleInit) {

						sensus.locale.init();
					}
				}
			});
		},

		getDeviceTypeParameters : function() {

			$.ajaxValidator.fnDoCall("fetchServicesByDeviceType", null, false, function (oResponse) {

				var aServicesTypePermissions, sService;

				if (oResponse && oResponse.servicesPermissions && oResponse.servicesPermissions.servicesTypePermissions
						&& oResponse.servicesPermissions.servicesTypePermissions.length) {

					sService = sensus.settings.serviceType;

					aServicesTypePermissions = $.grep(oResponse.servicesPermissions.servicesTypePermissions, function (e, i) {
						return e.serviceTypeEnum == sService;
					});

					sensus.settings.oDeviceTypeParameters = aServicesTypePermissions[0];
				}
			});
		},

		currentHeadService : null,

		setHeadService : function(service) {

			if (this.currentHeadService) {

				head.feature(this.currentHeadService, false);
			}

			this.currentHeadService = service;
			head.feature(service, true);
		},

		/**
		 * Get Device Type value based on device list tabs hierarchy.
		 * @param {Array} - aDeviceType
		 * 						Array with differents types.
		 * @returns {String}
		 * 					The device type value.
		 */
		fnGetDeviceType : function (aDeviceTypes, aDeviceTypeCount) {
			// Electric redirect
			if (sensus.settings.serviceType == sensus.constants.services.electric.name) {
				// Electric Meter
				if ($.inArray(sensus.constants.services.electric.meter.name, aDeviceTypes) != -1) {
					return [sensus.constants.services.electric.meter.name];
				}

				// Electric Han
				if ($.inArray(sensus.constants.services.electric.han.name, aDeviceTypes) != -1) {

					var hanDeviceType = sensus.constants.services.electric.han.name;
					var aNewDeviceTypeCount = $.grep(aDeviceTypeCount, function(oDeviceTypeCount){

						return oDeviceTypeCount.device.deviceType == hanDeviceType;
					});

					if (aNewDeviceTypeCount && aNewDeviceTypeCount.length) {

						return [sensus.constants.services.electric.han.name, aNewDeviceTypeCount[0].device.hanDeviceTypeEnum];
					}

					return [sensus.constants.services.electric.han.name];
				}

				// Electric LCM
				if ($.inArray(sensus.constants.services.electric.lcm.name, aDeviceTypes) != -1) {
					return [sensus.constants.services.electric.lcm.name];
				}

				return [sensus.constants.services.electric.meter.name];
			}

			// Water redirect
			if (sensus.settings.serviceType == sensus.constants.services.water.name) {

				// Water Meter
				if ($.inArray(sensus.constants.services.water.meter, aDeviceTypes) != -1) {
					return [sensus.constants.services.water.meter.name];
				}

				return [sensus.constants.services.water.meter.name];
			}

			// Gas redirect
			if (sensus.settings.serviceType == sensus.constants.services.gas.name) {

				// Gas Meter
				if ($.inArray(sensus.constants.services.gas.meter, aDeviceTypes) != -1) {
					return [sensus.constants.services.gas.meter.name];
				}

				return [sensus.constants.services.gas.meter.name];
			}

			// Arqiva redirect
			if (sensus.settings.serviceType == sensus.constants.services.arqiva.name) {

				// Arqiva Meter
				if ($.inArray(sensus.constants.services.arqiva.meter, aDeviceTypes) != -1) {
					return [sensus.constants.services.arqiva.meter.name];
				}

				return [sensus.constants.services.arqiva.meter.name];
			}

			return null;
		},

		/**
		 *	Redirect Search.
		 *	Get all types came from the search and redirect to correct tab
		 *
		 *	@param {Array} - aDevices
		 *						All the devices came from the search
		 * @param {Object} - oRequest
		 * 						The Request Object for the search
		 */
		fnRedirectSearch : function (aDevices, oRequest) {

			var iDeviceLength = aDevices.length;
			var aDeviceTypes = [];

			// Get All types
			for (var i = 0; i < iDeviceLength; i++) {

				aDeviceTypes.push(aDevices[i].device.deviceType);
			}

			// Get the type based on device list hierarchy and Redirect to Device List with correct tab checked
			sensus.pages.dm.fnRedirectToDevice(sensus.pages.dm.fnGetDeviceType(aDeviceTypes, aDevices), oRequest);
		},

		/**
		 * Quick Search Callback function.
		 *
		 * @param {Object} - oResponse
		 * 						The response Object
		 * @param {Object} - oRequest
		 * 						The Request Object for the search
		 */
		fnQuickSearchCallback : function(oResponse, oRequest) {

			// Device Length
			var iDevicesLength = oResponse.deviceTypeCountList.length;

			// Clean search field.
			$('#search-text').val('');

			// When only one device found, redirect to detail page.
			if (iDevicesLength == 1 && oResponse.firstDeviceTypeCountList.deviceCount == 1) {

				var oFirstDevice = oResponse.firstDeviceTypeCountList.device;
				var sTypeEnum = oFirstDevice.electricMeterTypeEnum || oFirstDevice.hanDeviceTypeEnum || oFirstDevice.lcmTypeEnum;

				$.fn.pageLoader.load("device/detail?id=" + oFirstDevice.radio.flexNetIdAsString + "&deviceType=" + oFirstDevice.deviceType + "&typeEnum=" + sTypeEnum, $("#load"));

				// Remove Sessions
				sensus.util.session.remove(["SelectedFilters"]);

				return;
			}

			// Redirect to device list with correct tab selected.
			sensus.pages.dm.fnRedirectSearch(oResponse.deviceTypeCountList, oRequest);

		},

		/**
		 * Quick Search Error Callback function.
		 *
		 * @param {Object} - oResponse
		 * 						The response Object
		 */
		fnQuickSearchErrorCallback : function (oResponse, oRequest) {

			// Get the type based on device list hierarchy.
			sensus.pages.dm.fnRedirectToDevice(sensus.pages.dm.fnGetDeviceType([]), oRequest);
		},

		/**
		 * Redirect To Device function.
		 *
		 * @param string - sDeviceType
		 * 						The type of device
		 * @param string - sType
		 * 						The type of search
		 * @param string - sQuery
		 * 						The string of search
		 */
		fnRedirectToDevice : function (aDeviceTypes, oRequest) {

			var sType;
			var sQuery;

			// Get search type and value.
			for (var r in oRequest) {
				sType = r;
				sQuery = oRequest[r];
			}

			$.fn.pageLoader.load([sensus.util.page.fnFormatURLService(), "?saved=true&device_type=", aDeviceTypes[0], "|",
							        (aDeviceTypes[1] ? "&device_subtype=" + aDeviceTypes[1] + "|" : ""), '&query=', sQuery, '|', sType].join(""), $("#load"));
		},

		/**
		 * Validate Search Field.
		 *
		 * @param {String} - sQuery
		 * 						The field value.
		 * @param {String} - sType
		 * 						The Search Type
		 * @param {String} - sTypeLabel
		 * 						The Type Value
		 * @returns {String}
		 * 				The Error Value
		 */
		fnValidateQuickSearchField : function (sQuery, sType, oSearchLabel) {

			var sTypeLabel = oSearchLabel.find('span').text().replace(/^\s+|\s+$/g,"");

			if (sQuery.length <= 0) {

				return sensus.locale.get("commons.pages.smartpoint", sTypeLabel);

			} else if (sQuery.length > 25) {

				return sensus.locale.get('commons.pages.maxcharacter.' + sType);

			} else if (sQuery.match(['[-@!#$%¨&*+´`^~;?áÁéÉíÍóÓúÚãÃçÇ|\?,./{}"<>()]'])) {

				return sensus.locale.get('commons.pages.characterInvalid');

			}

			return "";
		},

		/**
		 * Search Field. Ajax call to get how many devices exists by type.
		 * If only one device exists redirect to detail page.
		 * In case there is more than one device redirect to device list.
		 */
		fnQuickSearch : function () {

			var oSearchLabel = $("#search-label");
			var oSearchText = $("#search-text");
			var sType = oSearchLabel.find("input").val();
			var	sQuery = oSearchText.val().replace(/^\s+|\s+$/g,"");
			var sUrl = sensus.settings.appRoot + "/api/device/fetchBySearch";
			var oRequest = {};
			var sValidationError = "";

			// Validate the Search Field.
			sValidationError = sensus.pages.dm.fnValidateQuickSearchField(sQuery, sType, oSearchLabel);

			if (sValidationError.length) {
				oSearchText.validationEngine('showPrompt', sValidationError, 'red', 'topLeft', true);
				return false;
			}

			sensus.util.page.startGlobalProgressBar();

			// Verify the type of search, and set on correct property
			if (sType === "NETWORK_ADDRESS") {
				oRequest[sType] = unescape(sQuery);
			} else {
				oRequest[sType] = sQuery;
			}

			// Ajax for search
			$.ajaxValidator.fnDoCall(sUrl, oRequest, false, sensus.pages.dm.fnQuickSearchCallback, null, true, null, null, sensus.pages.dm.fnQuickSearchErrorCallback);
		}
}
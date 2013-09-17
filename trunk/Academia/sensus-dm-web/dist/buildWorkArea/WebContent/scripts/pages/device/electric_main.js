sensus.pages.device = {

		keepFilters : ["search", "tag", "device_subtype"],

		exportCSVUrl : "api/export/generateDevicesCSV",

		tableConfig : {

			ELECTRIC_METER : {

				sortExpression : "device_id",
				url : "api/electricmeterdevice/fetchAll",
				mapColNum : 6,

				cutomizeHack : 12,

				columns : {

					typeEnum		: {propertyRequest : "electricMeterTypeEnum"},
					typeEnumValue 	: {propertyRequest : "electricMeterTypeEnumValue"},
					installDate		: {sId : "INSTALL_DATE", bVisible : true}
				}
			},

			HAN_DEVICE : {

				sortExpression : "device_id",
				url : "api/handevice/fetchAll",
				mapColNum : 7,

				cutomizeHack : 12,

				columns : {

					flexNetId		: {propertyRequest : "macAddress"},
					typeEnum		: {propertyRequest : "hanDeviceTypeEnum"},
					typeEnumValue 	: {propertyRequest : "hanDeviceTypeEnumValue"},
					electricSubType : {propertyRequest : "hanDeviceTypeEnum"},
					baseRepId		: {propertyRequest : "electricMeterFlexNetId"},
					electricSubType : {sId : "DEVICE_SUBTYPE", propertyRequest : "hanDeviceTypeEnum", bVisible : true}
				}
			},

			LCM : {

				sortExpression : "alarm|alarm_time|device_id",
				url : "api/lcmdevice/fetchAll",
				mapColNum : 7,

				columns : {

					flexNetId		: {propertyRequest : "macAddress"},
					typeEnum		: {propertyRequest : "lcmTypeEnum"},
					typeEnumValue 	: {propertyRequest : "lcmTypeEnumValue"},
					electricSubType : {sId : "DEVICE_SUBTYPE", propertyRequest : "lcmTypeEnum", bVisible : true},
					baseRepId		: {propertyRequest : "electricMeterFlexNetId"},
					alarm			: {sId : "ALARM", bVisible : true}
				}
			}
		},

		getTableConfig : function () {

			var sDeviceType = $.address.parameter("device_type").replace("|", "");
			var tableConfig = sensus.pages.device.tableConfig[sDeviceType];
			var columnsConfig = {};
			var electric;

			if (!tableConfig.columns.length) {

				$.extend(true, columnsConfig, sensus.util.device.allColumns, tableConfig.columns);
				electric = sensus.constants.services.electric;

				switch (sDeviceType) {

				case electric.meter.name:

					tableConfig.columns = [columnsConfig.deviceId, columnsConfig.flexNetId, columnsConfig.description, columnsConfig.installDate,
					                       columnsConfig.lifecycleState, columnsConfig.latitude, columnsConfig.longitude, columnsConfig.id,
					                       columnsConfig.deviceType, columnsConfig.customerId, columnsConfig.baseRepId, columnsConfig.clientEndPoint,
					                       columnsConfig.deviceSubType, columnsConfig.deviceSubTypeId, columnsConfig.typeEnum, columnsConfig.typeEnumValue,
					                       columnsConfig.timeZoneInMinutes];
					break;

				case electric.han.name:

					tableConfig.columns = [columnsConfig.deviceId, columnsConfig.description, columnsConfig.electricSubType,
										   columnsConfig.flexNetId, columnsConfig.parentId, columnsConfig.cityName, columnsConfig.lifecycleState,
					                       columnsConfig.installDate,columnsConfig.latitude, columnsConfig.longitude, columnsConfig.id,
					                       columnsConfig.customerId, columnsConfig.baseRepId, columnsConfig.clientEndPoint,
					                       columnsConfig.deviceSubType, columnsConfig.deviceSubTypeId, columnsConfig.typeEnum,
					                       columnsConfig.typeEnumValue, columnsConfig.showMap];
					break;

				case electric.lcm.name:

					tableConfig.columns = [columnsConfig.deviceId, columnsConfig.description, columnsConfig.electricSubType, columnsConfig.flexNetId,
										   columnsConfig.parentId, columnsConfig.cityName, columnsConfig.lifecycleState, columnsConfig.alarm,
										   columnsConfig.installDate, columnsConfig.alarmTime, columnsConfig.latitude,
										   columnsConfig.longitude, columnsConfig.id, columnsConfig.customerId,
					                       columnsConfig.baseRepId, columnsConfig.clientEndPoint, columnsConfig.deviceSubType,
					                       columnsConfig.deviceSubTypeId, columnsConfig.typeEnum, columnsConfig.typeEnumValue,
					                       columnsConfig.showMap];
					break;
				}
			}

			return tableConfig;
		},

		requestAction : function () {

			var _electric = sensus.constants.services.electric;
			var _paramRequest = sensus.util.filter.paramRequest;
			var _deviceUtil = sensus.util.device;
			var _fnSetRequest = _deviceUtil.setRequest;

			var aQuery = _paramRequest.getParameterArray("query");
			var aDeviceTypes = _paramRequest.getParameterArray("device_type");

			var sDeviceType = aDeviceTypes[0];

			var oSearch = _deviceUtil.getSearchRequest();
			var oDevice = {radio : {location : _deviceUtil.getLocation()}};
			var oConfiguration = {};
			var oDeviceSearch = {};

			switch (sDeviceType) {

			case _electric.meter.name:

				_fnSetRequest(_paramRequest.getParameterArray("lifecycle_state"), oDeviceSearch, "electricMeterLifecycleStateEnumList");
				_fnSetRequest(_paramRequest.getParameterArray("remote_disconnect"), oDeviceSearch, "remoteConnectStatusEnumList");
				_fnSetRequest(_paramRequest.getParameter("firmware"), oConfiguration, "firmwareMeter");

				oDevice.configuration = oConfiguration;
				oDeviceSearch.electricMeter = oDevice;
				oSearch.electricMeterSearch = oDeviceSearch;
				break;

			case _electric.lcm.name:

				_fnSetRequest(_paramRequest.getParameterArray("lifecycle_state"), oDeviceSearch, "lcmLifecycleStateEnumList");
				_fnSetRequest(_paramRequest.getParameterArray("device_subtype"), oDeviceSearch, "lcmTypeEnumList");
				_fnSetRequest(_paramRequest.getParameterArray("alarm"), oDeviceSearch, "alarmEnumList");

				oDeviceSearch.lcm = oDevice;
				oSearch.lcmSearch = oDeviceSearch;
				break;

			case _electric.han.name:

				_fnSetRequest(_paramRequest.getParameterArray("lifecycle_state"), oDeviceSearch, "hanLifecycleStateEnumList");
				_fnSetRequest(_paramRequest.getParameterArray("device_subtype"), oDeviceSearch, "hanDeviceTypeEnumList");

				oDeviceSearch.hanDevice = oDevice;
				oSearch.hanDeviceSearch = oDeviceSearch;
				break;
			}

			// Search
			if (aQuery && aQuery.length > 1) {

				sensus.util.device.setSearchRequest(aQuery[0], aQuery[1], sDeviceType, oDeviceSearch, oDevice);
			}

			return {
				deviceSearch : oSearch,
				deviceType	 : sDeviceType
			}
		},

		getFilters : function () {

			var filterUtil = sensus.util.filter;

			return filterUtil.filterArrayToObject(filterUtil.getDeviceFiltersByType());
		},

		changeViewEvent : function (e) {

			var $this = $(this);
			var $filter = $("#w-filters");
			var $viewport = $(".status-viewport");
			var $mapList = $("#map-list");
			var sDeviceType = decodeURI($.address.parameter("device_type")).replace("|", "");
			var electricService = sensus.constants.services.electric;
			var $lifecycleStates;
			var $lifecycleState;
			var sUrl = "api/electricmeterdevice/fetchAll";

		    if ($this.hasClass("list") && !$viewport.is(":visible")) {

		    	if (electricService.lcm.name == sDeviceType || electricService.han.name == sDeviceType) {

					$filter.filters("setFilterDisable", $("#lifecycle_state"), false);
				}

				// List View
		    	$mapList.addClass("hide-map");
				$viewport.show();
				sensus.widgets.datatable.reloadTable(sensus.pages.device.smartpointTable, 0, false);

		    } else if ($this.hasClass("map") && $mapList.hasClass("hide-map")) {

		    	sensus.util.page.startProgressBar();

		    	if (electricService.lcm.name == sDeviceType || electricService.han.name == sDeviceType) {

		    		$lifecycleState = $("#lifecycle_state");

		    		// Lock the click event at life cycle state filter
		    		$filter.filters("setFilterDisable", $lifecycleState, true);

		    		// Uncheck the life cycle state options
		    		$filter.filters("setFilterOptions",
		    				$lifecycleState.find("input:not([value='JOINED']):not([value='INSTALLED'])"), false);

		    		// Check the JOINED and INSTALLED options
		    		$filter.filters("setFilterOptions",
		    				$lifecycleState.find("input[value='JOINED'], input[value='INSTALLED']"), true);

		    		if (electricService.lcm.name == sDeviceType) {

						sUrl = "api/lcmdevice/fetchAll";

					} else {

						sUrl = "api/handevice/fetchAll";
					}
		    	}

		    	$viewport.hide();
		    	$mapList.removeClass("hide-map");

		    	// Map View
		    	if ($.sc.map.mapExists()) {

			    	// Whether map already exists
		    		$('#map').empty();
		    	}

	    		// Create map
	    		sensus.util.device.createMap(sUrl);

		    	sensus.util.page.stopProgressBar();
		    }
		}
}
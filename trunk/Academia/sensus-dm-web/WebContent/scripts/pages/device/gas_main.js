sensus.pages.device = {

		exportCSVUrl : "api/export/generateGasDevicesCSV",

		mapUrl :  "api/gasmeterdevice/fetchAll",

		tableConfig : {

			GAS_METER :	{

				sortExpression : "alarm|alarm_time|device_id",
				url : "api/gasmeterdevice/fetchAll",
				mapColNum : 6,

				cutomizeHack : 14,

				columns : {

					typeEnum		: {propertyRequest : "gasMeterTypeEnum"},
					typeEnumValue 	: {propertyRequest : "gasMeterTypeEnumValue"},
					installDate		: {sId : "INSTALL_DATE", bVisible : true},
					alarm			: {sId : "ALARM", bVisible : true}
				}
			}
		},

		getTableConfig : function () {

			var tableConfig = sensus.pages.device.tableConfig.GAS_METER;
			var columnsConfig = {};

			if (!tableConfig.columns.length) {

				$.extend(true, columnsConfig, sensus.util.device.allColumns, tableConfig.columns);

				tableConfig.columns = [columnsConfig.deviceId, columnsConfig.flexNetId, columnsConfig.lastHead, columnsConfig.status,
				                       columnsConfig.installDate,columnsConfig.alarm, columnsConfig.latitude, columnsConfig.longitude, columnsConfig.id,
				                       columnsConfig.deviceType, columnsConfig.customerId, columnsConfig.baseRepId, columnsConfig.clientEndPoint,
				                       columnsConfig.deviceSubType, columnsConfig.deviceSubTypeId, columnsConfig.typeEnum, columnsConfig.typeEnumValue,
				                       columnsConfig.alarmTime, columnsConfig.quarantine, columnsConfig.timeZoneInMinutes];
			}

			return tableConfig;
		},

		requestAction : function () {

			var _paramRequest = sensus.util.filter.paramRequest;
			var _deviceUtil = sensus.util.device;
			var _fnSetRequest = _deviceUtil.setRequest;

			var aQuery = _paramRequest.getParameterArray("query");

			var oSearch = _deviceUtil.getSearchRequest();
			var oConfiguration = {};
			var oDevice = {radio : {location : _deviceUtil.getLocation()}, configuration : oConfiguration};
			var oDeviceSearch = {gasMeter : oDevice};

			// Search
			if (aQuery && aQuery.length > 1) {

				sensus.util.device.setSearchRequest(aQuery[0], aQuery[1], "GAS_METER", oDeviceSearch, oDevice);
			}

			_fnSetRequest(_paramRequest.quarantine(), oDevice, "quarantine");
			_fnSetRequest(_paramRequest.getParameterArray("alarm"), oDeviceSearch, "alarmEnumList");
			_fnSetRequest(_paramRequest.getParameterArray("status_meter"), oDeviceSearch, "waterGasMeterStatusEnumList");
			_fnSetRequest(_paramRequest.getParameter("firmware"), oConfiguration, "firmwareMeter");

			oSearch.gasMeterSearch = oDeviceSearch;

			return {
				deviceSearch : oSearch,
				deviceType	 : "GAS_METER"
			}
		},

		getFilters : function () {

			var filters = sensus.util.filter.filters;

			return {
				group 				: filters.group,
				status_meter		: filters.status_meter,
				description			: filters.description,
			    alarm				: filters.alarm,
			    install_date		: filters.install_date,
			    address 			: filters.address,
			    meter_firmware		: filters.meter_firmware,
			    quarantine			: filters.quarantine,
			    tag 				: filters.tag
			};
		}
}
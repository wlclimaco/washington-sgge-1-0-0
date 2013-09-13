sensus.pages.device = {

		exportCSVUrl : "api/export/generateWaterDevicesCSV",

		mapUrl :  "api/watermeterdevice/fetchAll",

		tableConfig : {

			WATER_METER :	{

				sortExpression : "alarm|alarm_time|device_id",
				url : "api/watermeterdevice/fetchAll",
				mapColNum : 6,

				cutomizeHack : 14,

				columns : {

					typeEnum		: {propertyRequest : "waterMeterTypeEnum"},
					typeEnumValue 	: {propertyRequest : "waterMeterTypeEnumValue"},
					installDate		: {sId : "INSTALL_DATE", bVisible : true},
					alarm			: {sId : "ALARM", bVisible : true}
				}
			}
		},

		getTableConfig : function () {

			var tableConfig = sensus.pages.device.tableConfig.WATER_METER;
			var columnsConfig = {};

			if (!tableConfig.columns.length) {

				$.extend(true, columnsConfig, sensus.util.device.allColumns, tableConfig.columns);

				tableConfig.columns = [columnsConfig.deviceId, columnsConfig.flexNetId, columnsConfig.lastHead, columnsConfig.status,
				                       columnsConfig.installDate, columnsConfig.alarm, columnsConfig.latitude, columnsConfig.longitude, columnsConfig.id,
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
			var oDevice = {radio : {location : _deviceUtil.getLocation()}};
			var oConfiguration = {};
			var oDeviceSearch = {};


			_fnSetRequest(_paramRequest.quarantine(), oDevice, "quarantine");
			_fnSetRequest(_paramRequest.getParameterArray("alarm"), oDeviceSearch, "alarmEnumList");
			_fnSetRequest(_paramRequest.getParameterArray("status_meter"), oDeviceSearch, "waterGasMeterStatusEnumList");

			oDevice.configuration = oConfiguration;
			oDeviceSearch.waterMeter = oDevice;
			oSearch.waterMeterSearch = oDeviceSearch;

			// Search
			if (aQuery && aQuery.length > 1) {

				sensus.util.device.setSearchRequest(aQuery[0], aQuery[1], "WATER_METER", oDeviceSearch, oDevice);
			}

			return {
				deviceSearch : oSearch,
				deviceType	 : "WATER_METER"
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
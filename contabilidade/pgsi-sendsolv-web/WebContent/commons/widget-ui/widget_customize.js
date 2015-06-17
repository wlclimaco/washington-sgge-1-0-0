(function($)
{
	/** Set DATA OBJECT **/
	var oData = {};

	var oAjax = {};

	var fnElectricFilters = function ()
	{
		return sensus.util.filter.getDeviceFiltersByType();
	};

	var fnGetDefaultFilters = function (sDeviceCategory)
	{

		var serviceType = sensus.settings.serviceType;
		var services 	= sensus.constants.services;

		switch (serviceType)
		{
			case services.water.name:
			case services.gas.name:

				return sensus.util.filter.getDeviceFiltersByType();

			case services.electric.name:

				return fnElectricFilters();
		}
	};

	var fnGetAllElectricColumns = function (sDeviceCategoryEnum)
	{
		switch (sDeviceCategoryEnum)
		{
			case sensus.constants.services.electric.meter.name :

				return [
					"DEVICE_ID",
					"NETWORK_ADDRESS",
					"METER_TYPE",
					"LAST_HEARD",
					"INSTALL_DATE",
					"INSTALL_DATE_TIMEZONE",
					"LIFECYCLE_STATE",
					"ADDRESS",
					"LATITUDE",
					"LONGITUDE",
					"ENCRYPTION_SUPPORT",
					"CITY_NAME",
					"PREMISE_ID",
					"ZIP_CODE",
					"REMOTE_DISCONNECT",
					"FIRMWARE_FLEXNET",
					"MAP_IT",
					"ALERT_CATEGORY",
					"ALERT_SEVERITY",
					"ALARM",
					"OPERATIONAL_MODE_STATE_DESC",
					"RMA"
				];

			case sensus.constants.services.electric.light.name :

				return [
			        "DEVICE_TYPE",
			        "POLE_ID",
			        "FLEXNET_ID",
			        "INSTALL_DATE",
			        "INSTALL_DATE_TIMEZONE",
			        "LIFECYCLE_STATE",
			        "ADDRESS",
			        "CITY_NAME",
			        "ZIP_CODE",
			        "MAP_IT",
			        "LIGHT_STATE",
			        "ALERT_CATEGORY",
			        "ALERT_SEVERITY",
			        "ALARM",
			        "LATITUDE",
			        "LONGITUDE",
			        "FIRMWARE_FLEXNET",
			        "MODEL_NUMBER"
			        ];

			case sensus.constants.services.electric.lcm.name :

				return [
			        "DEVICE_ID",
			        "DEVICE_TYPE",
			        "DEVICE_CATEGORY",
			        "NETWORK_ADDRESS",
			        "PARENT_ID",
			        "CITY_NAME",
			        "LIFECYCLE_STATE",
			        "ADDRESS",
			        "LATITUDE",
			        "LONGITUDE",
			        "INSTALL_DATE",
			        "INSTALL_DATE_TIMEZONE",
			        "ZIP_CODE",
			        "MAP_IT",
			        "ALERT_CATEGORY",
			        "ALERT_SEVERITY",
			        "ALARM"
		        ];

			case sensus.constants.services.electric.ihd.name :

				return [
			        "DEVICE_ID",
			        "DEVICE_TYPE",
			        "NETWORK_ADDRESS",
			        "PARENT_ID",
			        "CITY_NAME",
			        "HAN_DEVICE_STATUS",
			        "ADDRESS",
			        "LATITUDE",
			        "LONGITUDE",
			        "INSTALL_DATE",
			        "INSTALL_DATE_TIMEZONE",
			        "ZIP_CODE",
			        "MAP_IT"
				];

			case sensus.constants.services.electric.thermostat.name :

				return [
			        "DEVICE_ID",
			        "DEVICE_TYPE",
			        "NETWORK_ADDRESS",
			        "PARENT_ID",
			        "CITY_NAME",
			        "HAN_DEVICE_STATUS",
			        "ADDRESS",
			        "LATITUDE",
			        "LONGITUDE",
			        "INSTALL_DATE",
			        "INSTALL_DATE_TIMEZONE",
			        "ZIP_CODE",
			        "MAP_IT"
		        ];

			case sensus.constants.services.electric.tower.name :

				return [
				    "DEVICE_ID",
				    "TOWER_NAME",
				    "ADDRESS",
				    "TOWER_NETWORK",
				    "CITY_NAME",
				    "ZIP_CODE",
				    "TOWER_STATE",
					"TOWER_WEATHER_STATION",
					"TOWER_TYPE",
					"TOWER_HEIGHT",
					"TOWER_OFF_LINE",
					"TOWER_ACTION"
				];
		}
	};

	var fnGetAllWaterGasColumns = function (sDeviceCategoryEnum)
	{
		var cServices = sensus.constants.services;

		switch (sDeviceCategoryEnum)
		{
			case cServices.gas.meter.name :

				return [
			        "DEVICE_ID",
			        "NETWORK_ADDRESS",
			        "LAST_HEARD",
			        "STATUS",
			        "INSTALL_DATE",
			        "INSTALL_DATE_TIMEZONE",
			        "ALERT_CATEGORY",
			        "ALERT_SEVERITY",
			        "ALARM",
			        "ADDRESS",
			        "CITY_NAME",
			        "PREMISE_ID",
			        "METER_TYPE",
			        "LATITUDE",
			        "LONGITUDE",
			        "ZIP_CODE",
			        "FIRMWARE_FLEXNET",
			        "MAP_IT",
			        "PRODUCT_TYPE_DESC",
			        "METER_MODEL_DESC"
		        ];

			case cServices.water.meter.name :

				return [
			        "DEVICE_ID",
			        "NETWORK_ADDRESS",
			        "LAST_HEARD",
			        "STATUS",
			        "INSTALL_DATE",
			        "INSTALL_DATE_TIMEZONE",
			        "ALERT_CATEGORY",
			        "ALERT_SEVERITY",
			        "ALARM",
			        "ADDRESS",
			        "CITY_NAME",
			        "PREMISE_ID",
			        "METER_TYPE",
			        "LATITUDE",
			        "LONGITUDE",
			        "ZIP_CODE",
			        "FIRMWARE_FLEXNET",
			        "MAP_IT",
			        "PRODUCT_TYPE_DESC",
			        "METER_MODEL_DESC"
		        ];

			case cServices.water.gateway.name :

				return [
			        "DEVICE_ID",
			        "NETWORK_ADDRESS",
			        "LAST_HEARD",
			        "STATUS",
			        "INSTALL_DATE",
			        "INSTALL_DATE_TIMEZONE",
			        "ALERT_CATEGORY",
			        "ALERT_SEVERITY",
			        "ALARM",
			        "ADDRESS",
			        "CITY_NAME",
			        "PREMISE_ID",
			        "DEVICE_TYPE",
			        "LATITUDE",
			        "LONGITUDE",
			        "ZIP_CODE",
			        "FIRMWARE_FLEXNET",
			        "MAP_IT"
		        ];

			case cServices.gas.gateway.name :

				return [
			        "DEVICE_ID",
			        "NETWORK_ADDRESS",
			        "LAST_HEARD",
			        "STATUS",
			        "INSTALL_DATE",
			        "INSTALL_DATE_TIMEZONE",
			        "ALERT_CATEGORY",
			        "ALERT_SEVERITY",
			        "ALARM",
			        "ADDRESS",
			        "CITY_NAME",
			        "PREMISE_ID",
			        "DEVICE_TYPE",
			        "LATITUDE",
			        "LONGITUDE",
			        "ZIP_CODE",
			        "FIRMWARE_FLEXNET",
			        "MAP_IT"
		        ];

			case cServices.water.sensor.name :

				return [
			        "DEVICE_ID",
			        "NETWORK_ADDRESS",
			        "LAST_HEARD",
			        "STATUS",
			        "INSTALL_DATE",
			        "INSTALL_DATE_TIMEZONE",
			        "ALERT_CATEGORY",
			        "ALERT_SEVERITY",
			        "ALARM",
			        "ADDRESS",
			        "CITY_NAME",
			        "PREMISE_ID",
			        "DEVICE_TYPE",
			        "LATITUDE",
			        "LONGITUDE",
			        "ZIP_CODE",
			        "FIRMWARE_FLEXNET",
			        "MAP_IT"
		        ];

			case cServices.gas.sensor.name :

				return [
			        "DEVICE_ID",
			        "NETWORK_ADDRESS",
			        "LAST_HEARD",
			        "STATUS",
			        "INSTALL_DATE",
			        "INSTALL_DATE_TIMEZONE",
			        "ALERT_CATEGORY",
			        "ALERT_SEVERITY",
			        "ALARM",
			        "ADDRESS",
			        "CITY_NAME",
			        "PREMISE_ID",
			        "DEVICE_TYPE",
			        "LATITUDE",
			        "LONGITUDE",
			        "ZIP_CODE",
			        "FIRMWARE_FLEXNET",
			        "MAP_IT"
		        ];

			case cServices.water.tower.name :

				return [
				    "DEVICE_ID",
				    "TOWER_NAME",
				    "ADDRESS",
				    "TOWER_NETWORK",
				    "CITY_NAME",
				    "TOWER_STATE",
				    "ZIP_CODE",
					"TOWER_WEATHER_STATION",
					"TOWER_TYPE",
					"TOWER_HEIGHT",
					"TOWER_OFF_LINE",
					"TOWER_ACTION"
				];

			case cServices.gas.tower.name :

				return [
					"DEVICE_ID",
					"TOWER_NAME",
					"ADDRESS",
					"TOWER_NETWORK",
					"CITY_NAME",
					"ZIP_CODE",
					"TOWER_STATE",
					"TOWER_WEATHER_STATION",
					"TOWER_TYPE",
					"TOWER_HEIGHT",
					"TOWER_OFF_LINE",
					"TOWER_ACTION"
				];
		}
	};

	var fnAllColumns = function (sDeviceCategoryEnum)
	{
		switch (sensus.settings.serviceType)
		{
			case sensus.constants.services.water.name:
			case sensus.constants.services.gas.name:

				return fnGetAllWaterGasColumns(sDeviceCategoryEnum);

			case sensus.constants.services.electric.name:

				return fnGetAllElectricColumns(sDeviceCategoryEnum);
		}
	};

	var fnConvertPropertyListToString = function (e)
	{
		if ( $.sc.isValidArray(e.customizations)
				&& $.sc.isValidArray(e.customizations[0].properties) )
		{
			var aStringList 		= [];
			var aProperties 		= e.customizations[0].properties
			var iPropertiesLength 	= aProperties.length;

			for (var i = 0; i < iPropertiesLength; i++)
			{
				aStringList.push(aProperties[i]["propertyValue"]);
			}

			return aStringList;
		}

		return null;
	};

	var getDefaultObj = function (e)
	{
		var sDeviceCategoryEnum = $.address.parameter(sensus.constants.requests.deviceCategory);

		if ( !$.sc.isNullOrUndefined(sDeviceCategoryEnum) )
		{
			sDeviceCategoryEnum = sDeviceCategoryEnum.replace("|", '');

			var allFilter 			= fnGetDefaultFilters(sDeviceCategoryEnum);
			var getDefaultFilter 	= sensus.util.device.getDefaultFilters(sDeviceCategoryEnum);
			var allColumns 			= fnAllColumns(sDeviceCategoryEnum)
			var aProperties 		= null;
		}
		else
		{
			var getDefaultFilter 	= null;
			var allFilter 			= null;
			var allColumns 			= null;
			var aProperties 		= null;

			switch ( $.sc.pageLoader.currentPage() )
			{
				case sensus.constants.requests.alert:

					allColumns 	= sensus.pages.alert.column.getAllColumns[sensus.settings.serviceType];
					break;

				case "report/tab/lifecycle_meter":

					allColumns 	= sensus.pages.lifecyclemeter.column.getAllColumns[sensus.settings.serviceType];
					break;

				case "report/tab/troubled_meter" :

					allColumns 	= sensus.pages.troubledmeter.column.getAllColumns[sensus.settings.serviceType];
					break;
			}

		}
		return {
			filters 			: fnConvertPropertyListToString(e) || getDefaultFilter,
			additionalFilters 	: allFilter,
			listColumn 			: fnConvertPropertyListToString(e) || [],
			additionalColumns 	: allColumns
		}
	};

	/**
	 * Function: removeDuplicateValues
	 * Purpose:  Test if has duplicate values
	 * Returns:  array: ["METER_ID", "METER_ID"]
	 * Inputs:   array: ["METER_ID"]
	 *
	 */
	var removeDuplicateValues =	function (arr)
	{
		var arrayOut 	= [];
		var obj 		= {};

		for (var i = 0; i < arr.length; i++)
		{
			obj[arr[i]] = 0;
		}

		for (var i in obj)
		{
			arrayOut.push(i);
		}

		return arrayOut;
	};

	//Extract areaType based on current page, service and device category(when it exists)
	var getAreaType = function (bDeviceCategory)
	{
		//Page with device category parameter
		if (bDeviceCategory)
		{
			return sensus.settings.serviceType
						+ "_" + $.sc.pageLoader.currentPage()
						.toUpperCase()
						.split("/")
						.join("_")
						.replace(sensus.settings.serviceType, "")
						+ "_" +
						$.sc.replaceAll("\\|", "", $.address.parameter(sensus.constants.requests.deviceCategory));
		}
		return sensus.settings.serviceType
									+ "_" +
									$.sc.pageLoader.currentPage().
									toUpperCase().
									split("/").
									join("_").
									replace(sensus.settings.serviceType, "");
	};

	/**
	 * Get data from database
	 *
	 * @param sPage
	 * 			string, page
	 * @param sType
	 * 			string, customization type filter/column
	 */
	var getData = function (sPage, sType, oColumnFilter)
	{
		var oResponse = {};
		var customSearchId;

		if ( sPage )
		{
			var fnCallback = function (e)
			{
				oResponseCall 	= e.responseJSON ? e.responseJSON : e;

				var defaultObj 	= getDefaultObj(oResponseCall);
				var	aColumn 	= [];

				if ( $.sc.isValidArray(oResponseCall.customizations) && $.sc.isValidArray(oResponseCall.customizations[0].properties) )
				{
					defaultObj.listColumn = fnConvertPropertyListToString(oResponseCall)
				}

				// Use extend to remove object reference.
				oResponse = $.extend(true, {}, defaultObj);

				if ( $.sc.isValidArray(oResponseCall.customizations)
						&& oResponseCall.customizations.length > 0)
				{
					oResponse.id = oResponseCall.customizations[0].id;
				}
				else
				{
					oResponse.id = null;
				}
			}

			var sDeviceCategory = sensus.util.filter.paramRequest.getParameter(sensus.constants.requests.deviceCategory);
			var sUrl 			= null;

			if (sDeviceCategory)
			{
				var sAreaType = getAreaType(true);
			}
			else
			{	//AreaTypeEnum Parameter
				var sAreaType = getAreaType(false);
			}

			var oTypeCustomize =
			{
				Columns : 'COLUMN',
				Filters : 'FILTER'
			}

			// Customization Object
			var customization = new Customization (
			{
				deviceCategory 	  	  : sDeviceCategory,
				customizationTypeEnum : oTypeCustomize[sType],
				areaType			  : sAreaType
			});


			// CustomSearchRequest Object
			var customSearhRequest = new CustomSearchRequest(
			{
				customization : customization
			});

			if ( oTypeCustomize[sType] == oTypeCustomize.Columns )
			{
				// Columns Url
				sUrl = 'api/search/fetchColumn';
			}
			else if ( oTypeCustomize[sType] == oTypeCustomize.Filters )
			{
				// Filter Url
				sUrl = 'api/search/fetchFilter';
			}

			if ( $.sc.isNullOrUndefined(oColumnFilter) )
			{
				$.sc.ajax.post(
				{

					sUrl				: sUrl,
					oRequest			: customSearhRequest,
					fnCallback			: fnCallback,
					bAsync				: false,
					bHideProgressBar 	: true
				});
			}
			else
			{
				fnCallback(oColumnFilter);
			}

			var sessionData = sensus.util.session.read(sType);

			/**
			 * Check if there are customization in the session
			 *
			 * archive: session.js
			 */

			if ( $.isArray(sessionData) )
			{
				if (sType == 'Filters')
				{
					oResponse.filters = sessionData.slice(0);
				}
				else if ( sType == 'Columns' )
				{
					oResponse['listColumn'] = sessionData.slice(0);
				}
			}
		}

		return oResponse;
	};

	var fnCreateColumnRequest = function (aActive)
	{
		var aActiveLength 	= aActive.length;
		var listColumn 		= [];

		for (var i = 0; i < aActiveLength; i ++)
		{
			listColumn.push({Column : aActive[i]});
		}

		return new ColumnFilterRequest({listColumn : listColumn});
	};

	$.wCustomize = (function()
	{
		return {
			getData : getData,
			getAreaType : getAreaType
		};
	})();

	/**
	 * @param sPage
	 * 			string, page
	 * @param sType
	 * 			string, customization type filter/column
	 * @param fnCallBack
	 * 			function, callback function
	 * @param objFilters
	 * 			object, filter´s object
	 */
	$.fn.wCustomize = function(sType, fnCallBack, objFilters, sPage, fnFilterEventsCallback)
	{
		var dData        = getData(sPage, sType);
		var oAjaxData    = {};
		var sAjaxData    = "";
		var aAll         = [];
		var aCurrFilters = [];
		var aActive      = [];

		// Our plugin implementation code goes here.

		/** Append to LOAD dialog content **/

		$('#custom-filters').remove();

		if ( !$('#custom-filters').length )
		{
			var sText;

			if ( sType === "Filters" )
			{
				sText = $.sc.locale.get("widgets.filter.filterSet");
			}
			else if ( sType === "Columns" )
			{
				sText = $.sc.locale.get("widgets.filter.view");
			}

			$('<div id="custom-filters" class="action-dialog"><div id="droppable-area"><h2>' + $.sc.locale.get('widgets.customize.currently.' +  sType) +
				'</h2><fieldset><ul id="checkbox-list-active" class="checkbox-list-active sortable connected-sortable" style="float:left;"></ul>' +
					'<ul style="float:left"><li id="last-active" class="error hide">' + $.sc.locale.get('widgets.customize.error') +
						'</li></ul></fieldset><fieldset class="clear"><legend>' + $.sc.locale.get('widgets.customize.additional.' + sType) +
							'</legend><ul id="checkbox-list" class="checkbox-list sortable connected-sortable"></ul></fieldset></div>' +
								'<div class="highlight"><small><input id="set-default" type="checkbox"> ' + $.sc.locale.get('widgets.customize.checkbox') +
									'&nbsp;' + sText + '</small></div></div>').appendTo('#load');
		}

		/** fill the dialog **/
		$actionCustomFilters = $("#custom-filters").dialog(
		{
			autoOpen	: false,
			title		: $.sc.locale.get("filter.customize." + sType),
			width		: 940,
			modal		: true,

			iconButtons: [
                {
                    text: "help",
                    icon: "ui-icon-help",
                    click: function() {
                    	sensus.pages.dm.fnHelperCall(true, "customize");
                    }
                }
            ],

			buttons		: (function()
			{
				var buttons = {};

				buttons[$.sc.locale.get("filter.customize.set." + sType)] = function()
				{
					$.sc.progressBar.startGlobal();

					var bSetDefault	 	= $('#set-default').is(":checked");
					var sMessagindId 	= "messaging-main";
					var sSuccessMessage;

					$('#checkbox-list-active li').each(function(index)
					{
						aActive.push($(this).attr('id').toUpperCase());
					});

					// Remove values duplicate in array
					aActive = removeDuplicateValues(aActive);

					if ( bSetDefault == true )
					{
						oAjaxData[sAjaxData] = aActive;

						sensus.util.session.remove(sAjaxData);

						var oTypeCustomize =
						{
							Columns : 'COLUMN',
							Filters : 'FILTER'
						}

						var oRequest = new SystemPaginationRequest(
						{
							mapObject :
							{
								page 			: sPage,
								type 			: sAjaxData.toLowerCase(),
								filters			: oAjaxData[sAjaxData],
								deviceCategory	: sensus.util.filter.paramRequest.getParameter(sensus.constants.requests.deviceCategory),
								customSearchId	: dData.id,
								areaTypeEnum    : function ()
								{
									if ( $.sc.isNullOrUndefined(sensus.util.filter.paramRequest.getParameter(sensus.constants.requests.deviceCategory)) )
									{
										return getAreaType(false);
									}
									return getAreaType(true);
								}()
							}
						});

						var fnCallBackUpdate = function(oResponse)
						{
							oResponse = oResponse.responseJSON ? oResponse.responseJSON : oResponse;

							if ( oResponse.operationSuccess == true )
							{
								if ( sAjaxData == 'Filters' )
								{
									$.sc.listener.wait(
									{
										eventName  	: "deviceList",
										arguments  	: ["filter"],
										fnCallback  : $.sc.progressBar.stopGlobal
									});

									sSuccessMessage = $.sc.locale.get("filter.message.update.filters");

									sensus.util.filter.init(null,
									sensus.util.filter.filterArrayToObject(aActive),

									function (oResponse)
									{
										$.sc.filter.rebuild(
										{
											hasCustomize	: true,
											element			: "#w-filters",
											tagsDiv			: ".active-filters-list div.first",
											title			: $.sc.locale.get("filter.device.label"),
											table 			: sensus.pages.device.deviceTable,
											filters 		: oResponse
										}, true);

										$.sc.listener.notify(
										{
										   	eventName  : "deviceList",
										   	arguments  : ["filter"]
										});
									});

								}
								else if ( sAjaxData == 'Columns' )
								{

									switch ( $.sc.pageLoader.currentPage() )
									{
										case sensus.constants.requests.alert:

											$.sc.listener.wait(
											{
												eventName  	: "alertList",
												arguments  	: ["table"],
												fnCallback  : $.address.parameter("view") ? sensus.util.device.initMap : $.sc.progressBar.stopGlobal
											});

											sensus.pages.alert.table.oPreloadResponse = "refresh";
											sensus.pages.alert.column.oResponseColumn = null;
											sensus.pages.alert.table.init();
											break;

										case "report/tab/lifecycle_meter" :

											$.sc.listener.wait(
											{
												eventName  	: "lifecycleMeterList",
												arguments  	: ["table"],
												fnCallback  : $.sc.progressBar.stopGlobal
											});

											sensus.pages.lifecyclemeter.table.oResponse = "refresh";
											sensus.pages.lifecyclemeter.column.oResponseColumn = null;
											sensus.pages.lifecyclemeter.table.init();
											break;

										case "report/tab/troubled_meter" :

											$.sc.listener.wait(
											{
												eventName  	: "troubledMeterList",
												arguments  	: ["table"],
												fnCallback  : $.sc.progressBar.stopGlobal
											});

											sensus.pages.troubledmeter.table.oResponse = "refresh";
											sensus.pages.troubledmeter.column.oResponse = null;
											sensus.pages.troubledmeter.table.init();
											break;

										default:
											$.sc.listener.wait(
											{
												eventName  	: "deviceList",
												arguments  	: ["table"],
												fnCallback  : $.sc.progressBar.stopGlobal
											});

											/** rebuild columns **/
											sensus.util.device.initTable("refresh", sensus.pages.device.getTableConfig(), null, null);
											break;
									}
								}

								$('.cp-title').slideDown();
							}
							else
							{
								$.sc.message.show(
								{
									message: $.sc.locale.get('widgets.customize.errorRequest'),
									type: "error"
								});
							}
						};

						$.sc.ajax.post(
						{
							sUrl: "api/search/updateColumnFilter",
							oRequest: oRequest,
							fnCallback: fnCallBackUpdate
						});
					}
					else
					{
						if ( sAjaxData == 'Filters' )
						{
							$.sc.listener.wait(
							{
								eventName  	: "deviceList",
								arguments  	: ["filter"],
								fnCallback  : $.sc.progressBar.stopGlobal
							});

							sSuccessMessage = $.sc.locale.get("filter.message.update.filters");

							/** * Send array of filters to back-end to create session */
							sensus.util.session.create(
							{
								key : sAjaxData,
								value : aActive
							});

							sensus.util.filter.init(
								null,
								sensus.util.filter.filterArrayToObject(aActive),
								function (oResponse)
								{
									/** Add the search filter when is the saved search page */
									if ( $.address.parameter(sensus.constants.requests.saved) )
									{
										var filterUtil 	= sensus.util.filter;
										var filter 		= {search : filterUtil.filters.search};
										var opts 		= filterUtil.options;

										filter.search.inputs.query_type.options = [opts.device_id, opts.network_address, opts.premise_id];
										oResponse = $.extend({}, filter, oResponse);
									}

									$.sc.filter.rebuild(
									{
										hasCustomize	: true,
										element			: "#w-filters",
										tagsDiv			: ".active-filters-list div.first",
										title			: $.sc.locale.get("filter.device.label"),
										table 			: sensus.pages.device.deviceTable,
										filters 		: oResponse
									}, true);

									$.sc.listener.notify(
									{
									   	eventName  : "deviceList",
									   	arguments  : ["filter"]
									});
								}
							);
						}
						else if ( sAjaxData == 'Columns' )
						{
							/** * Send array of columns to back-end to create session */
							sensus.util.session.create(
							{
								key 	: sAjaxData,
								value 	: aActive
							});

							/** rebuild columns **/
							switch ( $.sc.pageLoader.currentPage() )
							{
								case sensus.constants.requests.alert:

									$.sc.listener.wait(
									{
										eventName  	: "alertList",
										arguments  	: ["table"],
										fnCallback  : $.address.parameter("view") ? sensus.util.device.initMap : $.sc.progressBar.stopGlobal
									});

									sensus.pages.alert.table.oPreloadResponse = "refresh";
									sensus.pages.alert.table.init();
									break;

								case "report/tab/lifecycle_meter" :

									$.sc.listener.wait(
									{
										eventName  	: "lifecycleMeterList",
										arguments  	: ["table"],
										fnCallback  : $.sc.progressBar.stopGlobal
									});

									sensus.pages.lifecyclemeter.table.oResponse = "refresh";
									sensus.pages.lifecyclemeter.table.init();
									break;

								case "report/tab/troubled_meter" :

									$.sc.listener.wait(
									{
										eventName  	: "troubledMeterList",
										arguments  	: ["table"],
										fnCallback  : $.sc.progressBar.stopGlobal
									});

									sensus.pages.troubledmeter.table.oResponse = "refresh";
									sensus.pages.troubledmeter.table.init();
									break;

								default:
									$.sc.listener.wait(
									{
										eventName  	: "deviceList",
										arguments  	: ["table"],
										fnCallback  : $.sc.progressBar.stopGlobal
									});

									sensus.util.device.initTable("refresh", sensus.pages.device.getTableConfig(), {Columns: aActive}, null);
									break;
							}
						}

						$('.cp-title').slideDown();
					}

					oAjax = {};

					/** close and empty dialog **/
					$(this).dialog('close');
					$(this).empty();
				},

				buttons[$.sc.locale.get("commons.pages.cancel")] = function()
				{
					$(this).empty();
					$(this).dialog('close');
				}

				return buttons;
			})(),

			open: function()
			{
				var aCurrFiltersNames;
				var aAllNames;

				/** FILTERS **/
				if ( sType == 'Filters' )
				{
					sAjaxData 			= 'Filters';
					aCurrFilters 		= [];
					aCurrFiltersNames	= [];

					for ( o in objFilters )
					{
						if ( objFilters.hasOwnProperty(o) )
						{
							aCurrFilters.push((objFilters[o].session).toString().toLowerCase());
							aCurrFiltersNames.push(objFilters[o].name);
						}
					}

					var objCustomizeEnum = dData.filters;
					var sValue;

					aAll 		= dData.additionalFilters;
					aAllNames 	= [];

					for ( o in aAll )
					{
						if ( aAll.hasOwnProperty(o) )
						{
							sValue = $.sc.locale.get("commons.pages." + aAll[o].toLowerCase());

							if ( aAll[o] == sensus.constants.requests.flexnetFirmware )
							{
								sValue = $.sc.locale.get("firmware.firmwareFlexnet");
							}

							aAllNames.push(sValue);
						}
					}
				}
				/** COLUMNS **/
				else if ( sType == 'Columns' )
				{
					var serviceType		= sensus.settings.serviceType;
					var services 		= sensus.constants.services;
					var sDeviceCategory = sensus.util.filter.paramRequest.getParameter(sensus.constants.requests.deviceCategory);

					sAjaxData 			= 'Columns';
					aCurrFilters 		= objFilters.aColumnsIds;
					aCurrFiltersNames	= [];
					aAllNames 			= [];
					aAll 				= dData.additionalColumns;

					/** Populate aCurrFiltersNames Array **/
					for (p in aCurrFilters)
					{
						switch (serviceType)
						{
							case services.water.name:
							case services.gas.name:

								if ( sDeviceCategory == sensus.constants.services.gas.meter.name
									|| sDeviceCategory == sensus.constants.services.water.meter.name )
								{
									if ( aCurrFilters[p] == "METER_TYPE" )
									{
										aCurrFiltersNames.push("SMARTPOINT");
									}
								}
								else
								{
									if ( aCurrFilters[p] == "METER_TYPE" )
									{
										aCurrFiltersNames.push(aCurrFilters[p]);
									}
								}

								if ( aCurrFilters[p] == "STATUS" )
								{
									aCurrFiltersNames.push("COMMUNICATION");
								}
								else if ( aCurrFilters[p] == "METER_MODEL_DESC" )
								{
									aCurrFiltersNames.push("METER_TYPE");
								}
								else if ( aCurrFilters[p] != "METER_TYPE" )
								{
									aCurrFiltersNames.push(aCurrFilters[p]);
								}

							break;

							case services.electric.name:

								aCurrFiltersNames.push(aCurrFilters[p]);

							break;
						}
					}

					/** Populate aAllNames Array **/
					for (p in dData.additionalColumns)
					{
						if ( dData.additionalColumns.hasOwnProperty(p) )
						{
							switch ( serviceType )
							{
								case services.water.name:
								case services.gas.name:

									if ( sDeviceCategory == sensus.constants.services.gas.meter.name
											|| sDeviceCategory == sensus.constants.services.water.meter.name )
									{

										if ( dData.additionalColumns[p] == "METER_TYPE" )
										{
											aAllNames.push("SMARTPOINT");
										}
									}
									else
									{
										if ( dData.additionalColumns[p] == "METER_TYPE" )
										{
											aAllNames.push(dData.additionalColumns[p]);
										}
									}

									if ( dData.additionalColumns[p] == "STATUS" )
									{
										aAllNames.push("COMMUNICATION");
									}
									else if ( dData.additionalColumns[p] == "METER_MODEL_DESC" )
									{
										aAllNames.push("METER_TYPE");
									}
									else if ( dData.additionalColumns[p] != "METER_TYPE" )
									{
										aAllNames.push(dData.additionalColumns[p]);
									}

								break;

								case services.electric.name:

									aAllNames.push(dData.additionalColumns[p]);

								break;
							}
						}
					}
				}

				/** hide error div **/
				$('#last-active').hide();

				/** clear actives **/
				$('#checkbox-list-active').empty();

				/** clear additionals **/
				$('#checkbox-list').empty();

				/** fill actives **/
				for (l in aCurrFilters)
				{
					if ( aCurrFilters.hasOwnProperty(l) )
					{
						if ( sType == "Filters" )
						{
							if( aCurrFilters[l] != 'CUSTOM_FILTERS' )
							{
								var noSortable = "";

								if (aCurrFilters[l] == sensus.constants.requests.lifecycleState)
								{
									noSortable = " no-sortable";
								}

								$('<li id="' + aCurrFilters[l] + '" class="ui-state-default' + noSortable + '"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>'
										+ aCurrFiltersNames[l] + '</li>').appendTo('#checkbox-list-active');

								// Remove from array the columns already selected
								var iPos = $.inArray(aCurrFilters[l], aAll);

								if ( iPos != -1 )
								{
									aAll.splice(iPos,1);
									aAllNames.splice(iPos,1);
								}
							}
						}
						else if ( sType == "Columns" )
						{
							if ( aCurrFilters[l] != 'CUSTOM_FILTERS' )
							{
								var noSortable = "";

								if ( aCurrFilters[l] == "DEVICE_ID"
									|| aCurrFilters[l] == "METER_ID"
									|| aCurrFilters[l] == "FLEXNET_ID"
									|| aCurrFilters[l] == "NETWORK_ADDRESS"
									|| aCurrFilters[l] == "TOWER_ACTION"
									|| aCurrFilters[l] == "ALERT_ACTIONS")
								{
									noSortable = " no-sortable";
								}

								$('<li id="' + aCurrFilters[l] + '" class="ui-state-default' + noSortable + '"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>'
										+ $.sc.locale.get('widgets.customize.additional.Column.' + aCurrFiltersNames[l]) + '</li>').appendTo('#checkbox-list-active');

								// Remove from array the columns already selected
								var iPos = $.inArray(aCurrFilters[l], aAll);

								if ( iPos != -1 )
								{
									aAll.splice(iPos,1);
									aAllNames.splice(iPos,1);
								}
							}
						}
					}
				}

				/** fill additionals **/
				for (l in aAll)
				{
					if ( aAll.hasOwnProperty(l) )
					{
						if ( sType == "Filters" )
						{
							$('<li id="' + aAll[l] + '" class="ui-state-highlight"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>'
									+ aAllNames[l] + '</li>').appendTo('#checkbox-list');
						}
						else if ( sType == "Columns" )
						{
							$('<li id="' + aAll[l] + '" class="ui-state-highlight"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>'
									+ $.sc.locale.get('widgets.customize.additional.Column.' + aAllNames[l])
									+ '</li>').appendTo('#checkbox-list');
						}
					}
				}
			},

			dialogClass: 'action-dialog',
			resizable: false
		});

		// INIT sortable
		$( "#checkbox-list-active, #checkbox-list" ).sortable(
		{
			containment	: "#droppable-area",
			scroll		: false,
			connectWith	: ".connected-sortable",
			cancel	 	: ".no-sortable",
			receive		: function (event, ui)
			{
				if ( $('#checkbox-list-active li').length < 1 )
				{
					$('#checkbox-list-active').sortable('cancel');
					$('#last-active').show();
				}
				else
				{
					$('#last-active').hide();
				}
			}

		}).disableSelection();

		$actionCustomFilters.dialog('open');
	};
})(jQuery);
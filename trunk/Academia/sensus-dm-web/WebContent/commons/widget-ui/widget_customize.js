// plugin definition

//head(function() {
	(function($) {
		/** Set DATA OBJECT **/
		var oData = {};

		var oAjax = {};

		var fnWaterGasFilters = function () {

			return ["group", "status_meter", "description", "alarm",
			        "install_date", "address", "meter_firmware", "quarantine", "tag"];
		};

		var fnElectricFilters = function () {

			return sensus.util.filter.getDeviceFiltersByType();
		};

		var fnGetDefaultFilters = function () {

			var serviceType = sensus.settings.serviceType;
			var services = sensus.constants.services;

			switch (serviceType) {

			case services.water.name:
			case services.gas.name:
				return fnWaterGasFilters();

			case services.electric.name:
				return fnElectricFilters();
			
			}

		};

		var fnElectricColumns = function (sDeviceTypeEnum, allColumns) {

			// Add LifeCycleState At Position 6
			allColumns.splice('6', '0', {columnEnum : "LIFECYCLE_STATE", fieldName : sensus.locale.get("smartpointdetail.tabs.about.state"), ordered : false, propertyRequest : 'lifecycleStateEnum', sOrderColumn : 'lifecycle_state'});

			// Electric Meter
			if (sDeviceTypeEnum == sensus.constants.services.electric.meter.name) {
				allColumns.splice('6','0',{columnEnum : "ENCRYPTION_SUPPORT", fieldName : sensus.locale.get("commons.pages.encryption_support"), ordered : true, propertyRequest : "configuration.encryptionSupported", sOrderColumn : 'ENCRYPTION_SUPPORT'});
				allColumns.splice('7','0',{columnEnum : "INSTALL_DATE", fieldName : sensus.locale.get("filter.device.installdate.label"), ordered : true, propertyRequest : 'configuration.installDate', sOrderColumn : 'install_date'});
				allColumns.splice('11','0',{columnEnum : "NETWORK_ADDRESS", fieldName : sensus.locale.get("commons.pages.networkAddress"), ordered : true, propertyRequest : 'radio.flexNetId', sOrderColumn : 'flexnet_id'});
				allColumns.splice('13','0',{columnEnum : "PREMISE_ID", fieldName : sensus.locale.get("commons.pages.premise.id"), ordered : true, propertyRequest : 'configuration.premiseId', sOrderColumn : 'PREMISE_ID'});
				allColumns.splice('14','0',{columnEnum : "REMOTE_DISCONNECT", fieldName : sensus.locale.get("commons.pages.remote_disconnect"), ordered : true, propertyRequest : 'remoteConnectStatusEnum', sOrderColumn : 'REMOTE_DISCONNECT'});
			}

			//Han
			if (sDeviceTypeEnum == sensus.constants.services.electric.han.name) {
				allColumns.splice('11','0',{columnEnum : "NETWORK_ADDRESS", fieldName : sensus.locale.get("commons.pages.networkAddress"), ordered : true, propertyRequest : 'macAddress', sOrderColumn : 'flexnet_id'});
				allColumns.splice('12','0',{columnEnum : "PARENT_ID", fieldName : sensus.locale.get("commons.pages.parent_id"), ordered : true, propertyRequest : 'electricMeterFlexNetId', sOrderColumn : 'PARENT_ID'});
				allColumns.splice('7','0',{columnEnum : "INSTALL_DATE", fieldName : sensus.locale.get("filter.device.installdate.label"), ordered : true, propertyRequest : 'configuration.installDate', sOrderColumn : 'install_date'});
				allColumns.splice('4','0',{columnEnum : "DEVICE_SUBTYPE", fieldName : sensus.locale.get("commons.pages.type"), ordered : true, propertyRequest : 'hanDeviceTypeEnum', sOrderColumn : 'device_subtype'});
			}

			// LCM
			if (sDeviceTypeEnum == sensus.constants.services.electric.lcm.name) {
				allColumns.splice('4','0',{columnEnum : "DEVICE_SUBTYPE", fieldName : sensus.locale.get("commons.pages.type"), ordered : true, propertyRequest : 'lcmTypeEnum', sOrderColumn : 'device_subtype'}),
				allColumns.splice('11','0',{columnEnum : "NETWORK_ADDRESS", fieldName : sensus.locale.get("commons.pages.networkAddress"), ordered : true, propertyRequest : 'macAddress', sOrderColumn : 'flexnet_id'});
				allColumns.splice('12','0',{columnEnum : "PARENT_ID", fieldName : sensus.locale.get("commons.pages.parent_id"), ordered : true, propertyRequest : 'electricMeterFlexNetId', sOrderColumn : 'PARENT_ID'});
				allColumns.splice('7','0',{columnEnum : "ALARM", fieldName : sensus.locale.get("commons.pages.alert"), ordered : true, propertyRequest : 'firstAlarm.alarmEnum', sOrderColumn : 'alarm|alarm_time'});
				allColumns.splice('8','0',{columnEnum : "INSTALL_DATE", fieldName : sensus.locale.get("filter.device.installdate.label"), ordered : true, propertyRequest : 'configuration.installDate', sOrderColumn : 'install_date'});
			}
		};

		var	fnWaterGasColumn = function (sDeviceTypeEnum, allColumns) {
	//			allColumns.splice(2,1);
				allColumns.splice('2','0',{columnEnum : "PREMISE_ID", fieldName : sensus.locale.get("commons.pages.premise.id"), ordered : true, propertyRequest : 'configuration.premiseId', sOrderColumn : 'PREMISE_ID'});
				allColumns.splice('6','0', {columnEnum : "INSTALL_DATE", fieldName : sensus.locale.get("filter.device.installdate.label"), ordered : true, propertyRequest : 'configuration.installDate', sOrderColumn : 'install_date'});
				allColumns.push({columnEnum : "NETWORK_ADDRESS", fieldName : sensus.locale.get("commons.pages.networkAddress"), ordered : true, propertyRequest : 'radio.flexNetId', sOrderColumn : 'flexnet_id'});
				allColumns.push({columnEnum : "STATUS", fieldName : sensus.locale.get("commons.pages.status"), ordered : true, propertyRequest : 'status', sOrderColumn : 'status'});
				allColumns.push({columnEnum : "ALARM", fieldName : sensus.locale.get("commons.pages.alert"), ordered : true, propertyRequest : 'firstAlarm.alarmEnum', sOrderColumn : 'alarm|alarm_time'});
				allColumns.push({columnEnum : "LAST_HEARD", fieldName : sensus.locale.get("commons.pages.last_heard"), ordered : true, propertyRequest : 'lastHeard', sOrderColumn : 'last_heard'});
				allColumns.push({columnEnum : "QUARANTINE", fieldName : sensus.locale.get("commons.pages.quarantine"), ordered : true, propertyRequest : 'quarantine', sOrderColumn : 'quarantine'});
		};

		var fnGetDefaultColumns = function (sDeviceTypeEnum) {
			var allColumns = [
			     		     {columnEnum : "ADDRESS", fieldName : sensus.locale.get("commons.pages.address"), ordered : true, propertyRequest : "radio.location.address", sOrderColumn : 'address'},
			     		     {columnEnum : "CITY_NAME", fieldName : sensus.locale.get("commons.pages.city"), ordered : true, propertyRequest : "radio.location.city", sOrderColumn : 'CITY_NAME'},
			     		     {columnEnum : "DESCRIPTION", fieldName : sensus.locale.get("commons.pages.device_description"), ordered : true, propertyRequest : 'deviceModel.description', sOrderColumn : 'device_type_desc'},
			     		     {columnEnum : "DEVICE_ID", fieldName : sensus.locale.get("commons.pages.device_id"), ordered : true, propertyRequest : 'deviceId', sOrderColumn : 'device_id'},
			     		     {columnEnum : "LATITUDE", fieldName : sensus.locale.get("commons.pages.latitude"), ordered : false, propertyRequest : 'radio.location.latitude', sOrderColumn : 'LATITUDE'},
			     		     {columnEnum : "LONGITUDE", fieldName : sensus.locale.get("commons.pages.longitude"), ordered : false, propertyRequest : 'radio.location.longitude', sOrderColumn : 'LONGITUDE'},
			     		     {columnEnum : "MAP_IT", fieldName : sensus.locale.get("commons.pages.map_it"), ordered : false},
			     		     {columnEnum : "ZIP_CODE", fieldName : sensus.locale.get("commons.pages.zip_code"), ordered : true, propertyRequest : 'radio.location.zip', sOrderColumn : 'ZIP_CODE'}
			     		]


			// Water and Gas Default Filters
			if (sensus.settings.serviceType == sensus.constants.services.water.name || sensus.settings.serviceType == sensus.constants.services.gas.name) {
				fnWaterGasColumn(sDeviceTypeEnum, allColumns);
			}

			// Electric Default Filters
			if (sensus.settings.serviceType == sensus.constants.services.electric.name) {
				fnElectricColumns(sDeviceTypeEnum, allColumns);
			}

			return allColumns;
		};

		var getDefaultObj = function (e) {

			var sDeviceTypeEnum = $.address.parameter("device_type") || $.address.parameter("deviceType")

			if (sDeviceTypeEnum) {
				sDeviceTypeEnum = sDeviceTypeEnum.replace("|", '');
			}

			// Get All Filters
			var allFilter = fnGetDefaultFilters(sDeviceTypeEnum);

			// Get All Columns
			var allColumns = fnGetDefaultColumns(sDeviceTypeEnum);

			return {
				filters : e.filters || [],
				additionalFilters : e.additionalFilters || allFilter,
				listColumn : e.listColumn,
				additionalColumns : e.additionalColumns || allColumns
			}
		};

		/**
		 * Function: removeDuplicateValues
		 * Purpose:  Test if has duplicate values
		 * Returns:  array: ["METER_ID", "METER_ID"]
		 * Inputs:   array: ["METER_ID"]
		 *
		 */
		var removeDuplicateValues =	function (arr) {

			var arrayOut = [];
			var obj = {};

			for (var i = 0; i < arr.length; i++) {
				obj[arr[i]] = 0;
			}

			for (var i in obj) {
				arrayOut.push(i);
			}

			return arrayOut;
		};

		/**
		 * Get data from database
		 *
		 * @param sPage
		 * 			string, page
		 * @param sType
		 * 			string, customization type filter/column
		 */
		var getData = function (sPage, sType, oColumnFitler, session) {

			var oResponse = {};

			if (sPage) {

				var fnCallback = function (e) {

					var defaultObj = getDefaultObj(e),
						columnEnum,
						oColumn;

					// Use extend to remove object reference.
					oResponse = $.extend(true, {}, defaultObj);

					if (oResponse.listColumn != undefined) {

						for (var i = 0, iColumnsLength = oResponse.listColumn.length; i < iColumnsLength; i ++) {

							columnEnum = oResponse.listColumn[i].columnEnum;

							oColumn = $.grep(defaultObj.additionalColumns, function(e, i) {
								return e.columnEnum == columnEnum;
							});

							oResponse.listColumn[i] = {fieldName : oColumn[0].fieldName, columnEnum : oColumn[0].columnEnum, ordered : oColumn[0].ordered, propertyRequest : oColumn[0].propertyRequest, sOrderColumn: oColumn[0].sOrderColumn}

						}
					}
				}

				var sDeviceType = $.address.parameter("device_type");

				if (sDeviceType) {

					var oTypeCustomize = {
						Columns : 'COLUMN',
						Filters : 'FILTER'
					}

					var	sPropertyEnum;

					var sDeviceSubType = sensus.util.filter.paramRequest.getParameter("device_subtype");

					if (sDeviceSubType == "IHD" || sDeviceSubType == "THERMOSTAT")  {

					    sPropertyEnum = sDeviceSubType + "_" + oTypeCustomize[sType];

					} else {

						sPropertyEnum = sensus.util.filter.paramRequest.getParameter("device_type") + "_" + oTypeCustomize[sType];
					}

					if ($.sc.isNullOrUndefined(oColumnFitler)) {

						$.ajaxValidator.fnDoCall('api/search/fetchColumnFilter', {page : sPage, propertyEnum : sPropertyEnum}, false, fnCallback);

					} else {

						fnCallback(oColumnFitler);
					}

				}

				var sessionData;

				if ($.sc.isNullOrUndefined(session)) {
					sessionData = sensus.util.session.read(sType);
				} else {
					sessionData = session[sType];
				}

				/**
				 * Check if there are customization in the session
				 *
				 * archive: session.js
				 */

				if ($.isArray(sessionData)) {

					if (sType == 'Filters') {

						oResponse.filters = sessionData.slice(0);

					} else if (sType == 'Columns') {

						var listColumn = sessionData.slice(0);

						$.each(oResponse['additionalColumns'], function(i) {

							var iColumnIndex = $.inArray(this.columnEnum, listColumn);

							if (iColumnIndex != -1) {

								listColumn[iColumnIndex] = {fieldName : this.fieldName, ordered : this.ordered, columnEnum : this.columnEnum, propertyRequest : this.propertyRequest, labelKey: this.propertyRequest, sOrderColumn : this.sOrderColumn};

							} else if (listColumn[i] == "MAP_IT") {

								listColumn[i] = {fieldName : "Map It", ordered : false, columnEnum : "MAP_IT"};
							}
						});


						oResponse['listColumn'] = listColumn;
					}
				}
			}

			return oResponse;
		};

		var fnCreateColumnRequest = function (aActive) {

			var aActiveLength = aActive.length;
			var listColumn = [];


			for (var i = 0; i < aActiveLength; i ++) {

				listColumn.push({Column : aActive[i]});

			}

			return new ColumnFilterRequest({listColumn : listColumn});

		};

		$.wCustomize = (function() {
			return  {
				getData : getData
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
		$.fn.wCustomize = function(sType, fnCallBack, objFilters, sPage, fnFilterEventsCallback) {

			var dData        = getData(sPage, sType),
				oAjaxData    = {},
				sAjaxData    = "",
				aAll         = [],
				aCurrFilters = [],
				aActive      = [];

			// Our plugin implementation code goes here.

			/** Append to LOAD dialog content **/

			$('#custom-filters').remove();

			if (!$('#custom-filters').length) {

				var sText;

				if (sType === "Filters") {
					sText = sensus.locale.get("widgets.filter.filterSet");
				} else if (sType === "Columns") {
					sText = sensus.locale.get("widgets.filter.view");
				}

				$('<div id="custom-filters" class="action-dialog"><h2>'+ sensus.locale.get('widgets.customize.currently') + '&nbsp;' + sType + '</h2><fieldset><ul id="checkbox-list-active" class="checkbox-list-active sortable connected-sortable" style="float:left;"></ul><ul style="float:left"><li id="last-active" class="error hide">'+ sensus.locale.get('widgets.customize.error') +'</li></ul></fieldset><fieldset class="clear"><legend>'+ sensus.locale.get('widgets.customize.additional') + '&nbsp;' +sType+'</legend><ul id="checkbox-list" class="checkbox-list sortable connected-sortable"></ul></fieldset><div class="highlight"><small><input id="set-default" type="checkbox"> '+ sensus.locale.get('widgets.customize.checkbox') + '&nbsp;' + sText+'</small></div></div>').appendTo('#load');

			}

			/** fill the dialog **/
			$actionCustomFilters = $("#custom-filters").dialog({
				autoOpen: false,
				title: 'Customize '+sType,
				width: 940,
				minheight: 150,
				modal: true,
				buttons: (function() {

					var buttons = {};

					buttons['Set '+sType] = function() {

							var bSetDefault = $('#set-default').is(":checked");

							$('#checkbox-list-active li').each(function(index) {
								aActive.push($(this).attr('id').toUpperCase());
							});

							// Remove values duplicate in array
							aActive = removeDuplicateValues(aActive);

							if (bSetDefault == true && $.address.parameter("device_type")) {

								oAjaxData[sAjaxData] = aActive;

								var oTypeCustomize = {
									Columns : 'COLUMN',
									Filters : 'FILTER'
								}

								var	sPropertyEnum;

								var sDeviceSubType = sensus.util.filter.paramRequest.getParameter("device_subtype");

								if (sDeviceSubType == "IHD" || sDeviceSubType == "THERMOSTAT")  {

								    sPropertyEnum = sDeviceSubType + "_" + oTypeCustomize[sType];

								} else {

									sPropertyEnum = sensus.util.filter.paramRequest.getParameter("device_type") + "_" + oTypeCustomize[sType];
								}

								var oRequest = {
									page 			: sPage,
									type 			: sAjaxData.toLowerCase(),
									filters			: oAjaxData[sAjaxData],
									sortExpressions : sensus.util.page.getSortExpression(sensus.pages.device.smartpointTable),
									propertyEnum 	: sPropertyEnum
								};

								var fnCallBackUpdate = function(oResponse) {

									if (oResponse.operationSuccess == true) {

										if (sAjaxData == 'Filters') {

											var $filter = $("#w-filters");

											$filter.filters("cleanAllFilters");
											$filter.filters("destroy");

											sensus.util.filter.init(null,
												sensus.util.filter.filterArrayToObject(aActive),

												function (oResponse) {

													$("#w-filters").filters({
														createReload : true,
														hasCustomize : true,
														title : sensus.locale.get("filter.device.label"),
														table : sensus.pages.device.smartpointTable,
														filters : oResponse});
												}
											);

										} else if (sAjaxData == 'Columns') {

											/** rebuild columns **/

											sensus.pages.device.smartpointTable.data().config.session = null;
											sensus.pages.device.smartpointTable.data().config.oColumnFilter = null;

											sensus.widgets.datatable.rebuild(sensus.pages.device.smartpointTable)
										}

										$('.cp-title').slideDown();

									} else {
										sensus.util.page.showMessage("messaging-main", sensus.locale.get('widgets.customize.errorRequest'), "error");
									}
								};

								$.ajaxValidator.fnDoCall("api/search/updateColumnFilter", oRequest, false, fnCallBackUpdate);

							} else {

								if (sAjaxData == 'Filters') {

									/** * Send array of filters to back-end to create session */
									sensus.util.session.create({key : sAjaxData, value : aActive});

									var $filter = $("#w-filters");

									$filter.filters("cleanAllFilters");
									$filter.filters("destroy");

									sensus.util.filter.init(null,
										sensus.util.filter.filterArrayToObject(aActive),

										function (oResponse) {

											/** Add the search filter when is the saved search page */
											if ($.address.parameter("saved")) {

												var filterUtil 	= sensus.util.filter;
												var filter 		= {search : filterUtil.filters.search};
												var opts 		= filterUtil.options;

												filter.search.inputs.query_type.options = [opts.device_id, opts.network_address, opts.premise_id];
												oResponse = $.extend({}, filter, oResponse);

											}

											$("#w-filters").filters({
												createReload : true,
												hasCustomize : true,
												title : sensus.locale.get("filter.device.label"),
												table : sensus.pages.device.smartpointTable,
												filters : oResponse});
										});

								} else if (sAjaxData == 'Columns') {
									/** * Send array of columns to back-end to create session */

									sensus.util.session.create({key : sAjaxData, value : aActive});
							   		/** rebuild columns **/

									sensus.pages.device.smartpointTable.data().config.session = null;
									sensus.pages.device.smartpointTable.data().config.oColumnFilter = null;

									sensus.widgets.datatable.rebuild(sensus.pages.device.smartpointTable);
								}

								$('.cp-title').slideDown();
							}

							oAjax = {};

							/** close and empty dialog **/
							$(this).dialog('close');
							$(this).empty();
					},
					buttons['Cancel'] = function() {
						$(this).empty();
						$(this).dialog('close');
					}
					return buttons;
				})(),

				open: function() {

					var aCurrFiltersNames;
					var aAllNames;

					/** FILTERS **/
					if (sType == 'Filters') {

						sAjaxData = 'Filters';

						aCurrFilters = [];
						aCurrFiltersNames = [];

						for (o in objFilters) {
							if (objFilters.hasOwnProperty(o)) {
								aCurrFilters.push((objFilters[o].session).toString().toLowerCase());
								aCurrFiltersNames.push(objFilters[o].name);
							}
						}

						var objCustomizeEnum = dData.filters;
						aAll = dData.additionalFilters;
						aAllNames = [];

						for (o in aAll) {
							if (aAll.hasOwnProperty(o)) {
								aAllNames.push(sensus.locale.get("commons.pages." + aAll[o].toLowerCase()));
							}
						}


						/** COLUMNS **/
					} else if (sType == 'Columns') {

						sAjaxData = 'Columns';

						var objCustomizeEnum = [];
						aCurrFilters = objFilters.aColumnsIds;
						aCurrFiltersNames = objFilters.aColumnsNames;
						aAllNames = [];

						/** Populate objCustomizeEnum Array **/
						for (n in dData.listColumn) {
							if (dData.listColumn.hasOwnProperty(n)) {
								objCustomizeEnum.push({
									columnEnum : dData.listColumn[n].columnEnum,
									fieldName : dData.listColumn[n].fieldName});
							}
						}
						/** Populate aAll Array **/
						for (p in dData.additionalColumns) {
							if (dData.additionalColumns.hasOwnProperty(p)) {
								aAll.push(dData.additionalColumns[p].columnEnum.toLowerCase());
								aAllNames.push(dData.additionalColumns[p].fieldName);
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
					for (l in aCurrFilters) {

						if (aCurrFilters.hasOwnProperty(l)) {

							if(aCurrFilters[l] != 'CUSTOM_FILTERS') {

								$('<li id="' + aCurrFilters[l] + '" class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span> '
										//+ sensus.locale.get('commons.pages.'+aCurrFilters[l].toLowerCase())
										+ aCurrFiltersNames[l]
										+ '</li>').appendTo('#checkbox-list-active');

								// Remove from array the columns already selected
								var iPos = $.inArray(aCurrFilters[l].toLowerCase(), aAll);

								if(iPos != -1) {

									aAll.splice(iPos,1);
									aAllNames.splice(iPos,1);

								}
							}
						}
					}

					/** fill additionals **/
					for (l in aAll) {

						if (aAll.hasOwnProperty(l)) {

							$('<li id="' + aAll[l] + '" class="ui-state-highlight"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span> '
									//+ sensus.locale.get('commons.pages.'+aAll[l].toLowerCase())
									+ aAllNames[l]
									+ '</li>').appendTo('#checkbox-list');

						}
					}

					$('#FLEXNET_ID').addClass('no-sortable');
					$('#METER_ID').addClass('no-sortable');
					$('#DEVICE_ID').addClass('no-sortable');

			},
			dialogClass: 'action-dialog',
			resizable: false
			});

			// INIT sortable
			$( "#checkbox-list-active, #checkbox-list" ).sortable({

				connectWith: ".connected-sortable",
				cancel : ".no-sortable",
				receive: function (event, ui) {

					if ($('#checkbox-list-active li').length < 1) {

						$('#checkbox-list-active').sortable('cancel');
						$('#last-active').show();

					} else {

						$('#last-active').hide();

					}
				}

			}).disableSelection();

			$actionCustomFilters.dialog('open');
		};

	})(jQuery);
	//}); head
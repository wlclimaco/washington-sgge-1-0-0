$(document).ready(function(){
	sensus.widgets.datatable = {

		/**
		 * Track the table rows the user selected across the pages.
		 */
		selectedRows : [],

		selectedDialogRows : [],

		/**
		 * Track the all rows the user deselected across the pages.
		 * This functionality is diff from selectedRows because it keeps the deselected rows
		 */

		deselectedRows : [],

		deselectedDialogRows : [],

		/**
		 * Track if AllRows selection is enabled
		 */
		isAllRows : false,

		/**
		 * Track the total amount of records for all page selection
		 */
		allRowsCount : {},

		/**
		 * Current Pagination
		 */
		iCurrentPagination : 0,

		/**
		 * Total item on page
		 */
		iTotalPagination : 0,

		/**
		 * Contains the row data.
		 * Used on Events click of a single row
		 */
		currentRow : null,

		/**
		 * Object used on current table
		 */
		oTableSettings : {},

		objLatLon : [],

		oCustomize : {},

		/**
		 * Model Object for datatable config.
		 * Each new Table will have a new instance from this object.
		 */
		TableSettings : function () {
			// URL
			this.sAjaxSource = "";
			//aData
			this.aData = [];
			// Columns
			this.aColumns = [];
			//Custom Column Page Name
			this.customColumns = "";
			this.dateRefresh = "";
			this.oPreLoadResponse   = null;  // Pre-loaded data
			this.bPreLoad			= true;  // Pre-loaded data
			// Settings
			this.oSettings = {
				bCheckbox : false,
				bSelection : false,
				bDialogCheckbox  : false,
				edit : null,
				remove : null,
				removeRecentRequest: null,
				process : null,
				order : "",
				aLengthMenu : [],
				iDisplayLength : sensus.settings.pageSize,
				smartpointFilter : null,
				bFooterVisible: true,
				aSelectedParameters : []
			};

			// Export CSV
			this.$exportCSVElement = null;

			// Callbacks
			this.fnRowCallback     = null;
			this.fnHeaderCallback  = null;
			this.fnFooterCallback  = null;
			this.fnDrawCallback    = null;
			this.fnInfoCallback    = null;
			this.fnPreDrawCallback = null;
		},

		/**
		 * TODO - Put this mothod on global function
		 * @param parent
		 * @param child
		 * @returns
		 */
		extend : function(parent, child) {
			var i;
			child = child || {};
			for (i in parent) {
				if (parent.hasOwnProperty(i)) {
					child[i] = parent[i];
				}
			}
			return child;
		},

		/**
		 * Reload table method, getting URL data
		 * @param table
		 * 				The table object
		 */
		reloadTable : function(table, iStart, bCleanSelect) {

			var iDisplayStart = iStart;

			if (iDisplayStart == undefined) {
				iDisplayStart =  $.address.parameter("iDisplayStart");
			}

			if ($.sc.isNullOrUndefined(bCleanSelect) || bCleanSelect == true)
			{
				sensus.widgets.datatable.clearSelectsFunction.call(table);
			}

			sensus.util.page.startProgressBar();

			$(".status-viewport-loading").removeClass("hide");
			//var dataUrl = "?";

			$.fn.pageLoader.parameter("iDisplayStart", 0);

			//dataUrl += $.address.queryString();

			table.fnSettings()._iDisplayLength = parseInt(sensus.settings.pageSize, 10);
			table.fnSettings()._iDisplayStart = iDisplayStart || 0;
			//table.fnSettings().sAjaxSource = table.data("table").sAjaxSource + dataUrl;

			$('.dataTables_length').find('select option[value='+ table.fnSettings()._iDisplayLength +']').prop("selected", true);

			table.fnStandingRedraw();

			//$("#action-dialog").dialog('close');
		},

		rebuild : function(oTable) {

			sensus.util.page.startProgressBar();

			$(".status-viewport-loading").removeClass("hide");
			var oConfig = oTable.data("config");

			oConfig.bPreLoad = false;

			oTable.fnDestroy();

			oTable.empty();
			oTable.append("<thead><tr></tr></thead><tbody></tbody>")

			oTable.dataTable(sensus.widgets.datatable.setTable(oConfig));
		},

		/**
		 * Initial method to configure table settings
		 * @param config
		 * 				Settings coming from pages.
		 * @returns
		 * 			The Table
		 */
		setTable : function(config) {

			var oTableSettings = null,
				//dataUrl        = "?",
				defaultConfig  = this.getDefaultConfig(),
				iDisplayStart  = $.address.parameter("iDisplayStart"),
				oColumnSetup   = {},
				oTable         = $("#" + config.id);
				hasColumn	   = true;
				oCustomize 	   = "";
				DefaulSortEnum = config.oSettings.sDefaulSortEnum;

			if(oTable[0].id == "smartpoint-table"){
				oCustomize 	   = $.wCustomize.getData(config.customColumns, 'Columns', config.oColumnFilter, config.session);//
			}
			// Change the sort column If the current sort column is NOT visible
			if(DefaulSortEnum) {

			    DefaulSortEnum = DefaulSortEnum.split("|")[0].toUpperCase();
				if (oCustomize.listColumn) {
					hasColumn = false;
					for (var x = 0; x < oCustomize.listColumn.length; x++ ) {

						if (DefaulSortEnum.indexOf(oCustomize.listColumn[x].columnEnum) == 0 ) {

							hasColumn = true;
							break;
						}
					}
					if(!hasColumn){

						config.oSettings.sDefaulSortEnum = "device_id";
					}
				}
			}

			// Set object 'config' with configurations of the table in the DOM
			oTable.data("config", $.extend({}, config));

			config.fnServerData = function ( sSource, aoData, fnCallback ) {
				// Request AJAX
				sensus.widgets.datatable.fnCreateRequestModel.call(this, sSource, oTable, config, aoData, null, fnCallback);
			}
			config.aColumns = this.addCustomColumns(config, oTable).aColumns;

			//Add dynamically columns to aColumns array
			config.aColumns = this.addColHeader(config, hasColumn);

			//Index columns so that table configuration can be based on column id, not
			//absolute position.
			oColumnSetup = this.getColumnSetup(config.aColumns);
			config.aColumns = oColumnSetup;
			config.aoColumnDefs = oColumnSetup.aoColumnDefs;

			//Inherit tableSettings from config
			oTableSettings = this.extend(config, new this.TableSettings());
			this.oTableSettings = this.extend(config, new this.TableSettings());
			oTable.data("table", oTableSettings);

			//Set Default Pagination
			jQuery.fn.dataTableExt.oPagination.iFullNumbersShowPages = 12;

			//Set URL data

			$.fn.pageLoader.parameter("iDisplayStart", 0);

			//dataUrl += $.address.queryString();

			if (config.sAjaxSource) {
				//config.sAjaxSource = config.sAjaxSource + dataUrl;
			} else {
				defaultConfig.bServerSide = false;
			}

			//Callbacks
			config.fnRowCallback     = this.rowCallback;
			config.fnHeaderCallback  = this.fnHeaderCallback;
			config.fnFooterCallback  = this.fnFooterCallback;
			config.fnDrawCallback    = this.fnDrawCallback;
			config.fnInfoCallback    = this.fnInfoCallback;
			config.fnPreDrawCallback = this.fnPreDrawCallback;

			if (config.oSettings.iDefaultCol != null || config.oSettings.aaSorting != null) {
				if (config.oSettings.aaSorting) {
					defaultConfig.aaSorting = config.oSettings.aaSorting;
				} else if (config.oSettings.sDefaultSort != null) {
					defaultConfig.aaSorting = [[ config.oSettings.iDefaultCol, config.oSettings.sDefaultSort ]];
				} else {
					defaultConfig.aaSorting = [[ config.oSettings.iDefaultCol, "asc" ]];
				}
			}

			//Set Length
			if (config.oSettings.aLengthMenu) {
				defaultConfig.aLengthMenu = config.oSettings.aLengthMenu;
			}

			if (sensus.settings.pageSize != undefined) {
				defaultConfig.iDisplayLength = parseInt(sensus.settings.pageSize, 10);
			} else {
				defaultConfig.iDisplayLength = 25;
			}

			if (iDisplayStart != undefined) {
				defaultConfig.iDisplayStart = parseInt(iDisplayStart, 10);
			}

			return $.extend(defaultConfig, config);
		},

		pad2 : function(number) {

		     return (number < 10 ? '0' : '') + number

		},

		fnCreateRequestModel : function(sSource, oTable, config, aoData, aoCustomColumns, fnCallback) {


			var fnSuccessCallBack = function(json)
			{
				//	oTable.dateRefresh = $.date.setDateServer(true);
				var oRefresh = new Date();

				// Now as Utc
				var oRefreshUtc = new Date(oRefresh.getTime() + oRefresh.getTimezoneOffset() * 60000);
				var iTimeZoneMinutes = parseInt(sensus.settings.timezoneMinutes, 10);


				//Add Last Refresh
				if (oRefreshUtc != undefined) {

					var sDate = $.date.dateFormat( oRefreshUtc, sensus.settings.dateFormatMask.replace("yyyy", "yy") + ' h:i:s A');
					$(".stamp").text(sensus.locale.get('table.refreshed', sDate)).wrapInner("<em/>");

				}

				if (aoCustomColumns) {

					json.aaData = sensus.widgets.datatable.fnConvertAdata(json, aoCustomColumns.columns, config.oSettings.sResponseObj, true);

				} else {

					json.aaData = sensus.widgets.datatable.fnConvertAdata(json, config.oSettings.aColumns, config.oSettings.sResponseObj, false);

				}

				if (json.resultsSetInfo) {

					json.iTotalDisplayRecords = json.resultsSetInfo.totalRowsAvailable;

				}

				// Valid Response Object
				var oValidResponse = $.ajaxValidator.fnValidResponse(json);

				// Validate oResponse
				if (!oValidResponse.bIsValid) {

					// Show Error Message
					sensus.util.page.showMessage("messaging-main", oValidResponse.sErrorMessage, "error");
				}

				fnCallback(json);
			}


			if(config.bPreLoad && config.oPreLoadResponse != "refresh")
			{
				if($.sc.isValidPreLoad(config.oPreLoadResponse, true))
				{

					fnSuccessCallBack(config.oPreLoadResponse);
					config.bPreLoad = false;
				}
			} else
			{
				var oRequest      = new config.oSettings.oRequest(config.oSettings.fnRequest(oTable)),
					iStartRow     = ($.grep(aoData, function(e) { return e.name == 'iDisplayStart'; }))[0].value,
					iPageSize     = ($.grep(aoData, function(e) { return e.name == 'iDisplayLength'; }))[0].value,
					oNewRequest   = { };

				// Whether sortExpressions is undefined
				if (oRequest.sortExpressions === undefined) {

					if (oTable.dataTableSettings.length > 1) {

						var oDataSettings = $.grep(oTable.dataTableSettings, function(e) { return e.sInstance == oTable.attr('id'); });

						oRequest.sortExpressions = sensus.util.page.getSortExpression($(this), oDataSettings[0].aaSorting);

					} else {

						oRequest.sortExpressions = sensus.util.page.getSortExpression($(this), oTable.dataTableSettings[0].aaSorting);

					}

				}

				// Whether startRow is undefined
				// Don't check if startRow is null, there are cases when startRow has to be null
				if (oRequest.startRow === undefined) {
					oRequest.startRow = iStartRow;
				}

				// Whether pageSize is undefined
				// Don't check if pageSize is null, there are cases when pageSize has to be null
				if (oRequest.pageSize === undefined) {
					oRequest.pageSize = iPageSize;
				}

				// Whether endRow is undefined
				// Don't check if endRow is null, there are cases when endRow has to be null
				if (oRequest.endRow === undefined) {
					oRequest.endRow	= 0;
				}
				var json = null;

				$.ajax({
					dataType    : "json",
					type        : "POST",
					url         : sSource,
					data        : $.toJSON(oRequest),
					async       : false,
					contentType : "application/json; charset=utf-8",
					success     : fnSuccessCallBack
				});

				oTable = null;
			}
		},


		fnExecuteFunctionByName : function (functionName, context, args) {

			var namespaces  = functionName.split("."),
				func        = namespaces.pop();

			for(var i = 0; i < namespaces.length; i++) {

				context = context[namespaces[i]];

			}

			return context[func].call(this, args);

		},


		fnConvertAdata : function (data, aRowColumn, obj, bCustomize) {

			var aaData = [];

			for (var i in data[obj]) {

				if (data[obj].hasOwnProperty(i)) {

					var aData = [];

					for (var k in aRowColumn) {

						var objRequest;

						if (bCustomize) {

							objRequest = aRowColumn[k].propertyRequest;

						} else {

							objRequest = aRowColumn[k];
						}


						if ($.ajaxValidator.fnIsNullOrUndefined(objRequest)) {
							continue;
						}

						if (objRequest === '') {

							aData.push('');
							continue;

						}

						if (objRequest.match(/fn\(/)) {

							var aaChildren = objRequest.split('[');
							var sFunction;

							if (aaChildren.length > 1) {

								sFunction = aaChildren[1].replace(')]', '').replace('fn(','');
								aData.push(sensus.widgets.datatable.fnExecuteFunctionByName(sFunction, window, data[obj][i][aaChildren[0]]));

								continue;
							}

							sFunction = aaChildren[0].replace(')', '').replace('fn(','');
							aData.push(sensus.widgets.datatable.fnExecuteFunctionByName(sFunction, window, data[obj][i]));

							continue;
						}

						var aChildren = objRequest.split('.');

						if (aChildren[1]) {

							var oChildAux = data[obj][i][aChildren[0]];

							if (!oChildAux) {

								aData.push('');
								continue;
							}

							for (x = 1; x < aChildren.length; x++) {

								if (!$.sc.isNullOrUndefined(oChildAux[aChildren[x]])){
									oChildAux = oChildAux[aChildren[x]];
								}else{
									oChildAux = "";
								}
							}

							aData.push(oChildAux);
							continue;
						}

						var aaChildren = objRequest.split('[');

						if (aaChildren[1]) {

							if (aaChildren[1] == 'object]') {

								aData.push(JSON.stringify(data[obj][i][aaChildren[0]]));
								continue;
							}

							var sChildren = aaChildren[1].replace(']', '').split('=');
							var oChildren = data[obj][i][aaChildren[0]];

							if(oChildren && oChildren.length) {

								var aChildren = $.grep(oChildren, function(e) { return e[sChildren[0]] == sChildren[1]; });

								if(aChildren.length){

									aData.push(aChildren[0].value);
									continue;
								}

								aData.push('');
								continue;
							}

							aData.push('');
							continue;
						}

						// Else just put the value from array in the property
						aData.push(data[obj][i][aChildren[0]]);
					}

					// Add all rows of the table
					aaData.push(aData);
				}

			}

			return aaData;

		},

		addCustomColumns : function(config, oTable) {
			var oThis 				= this,
				oTr 				= oTable.find("thead").find("tr"),
				columns 			= config.aColumns,
				iIndexCol 			= -1,
				bAddDefaultHeader 	= true,
				oCustomDataResponse = {},
				oCustomData 		= null,
				aCustomDataColumn 	= [],
				aDefaultIds			= [],
				sColumnClass 		= "",
				sTr 				= "";

			if(!$.ajaxValidator.fnIsNullOrUndefined(oCustomize)){
				oCustomDataResponse = oCustomize;
			}else{
				oCustomDataResponse = $.wCustomize.getData(config.customColumns, 'Columns', config.oColumnFilter, config.session);
			}

			if (config.customColumns) {

				if ( oCustomDataResponse && oCustomDataResponse.listColumn) {

					oCustomData = this.fnFormatColumnResponse(oCustomDataResponse, oTable).listColumn;
					config.oSettings.iMapCol = undefined;

				} else {

					oCustomData = config.aColumns.slice();

					aDefaultIds.aColumnsIds = [];
					aDefaultIds.aColumnsNames = [];

					/* TODO - It is removing the last three items from device.
					* Make it generic for all pages
					*/
					var oCustomDataLength = oCustomData.length;
					oCustomData.splice(oCustomDataLength - (config.oSettings.cutomizeHack || 13), oCustomDataLength);

					$.each(oCustomData, function(i, e) {
						if(!$.ajaxValidator.fnIsNullOrUndefined(e)){
							aDefaultIds.aColumnsIds.push(e.sId);
							aDefaultIds.aColumnsNames.push(e.sName);
						}
					});

					aDefaultIds.aColumnsIds.splice(parseInt(config.oSettings.iMapCol, 10) - 1, 0, 'MAP_IT');
					aDefaultIds.aColumnsNames.splice(parseInt(config.oSettings.iMapCol, 10) - 1, 0, sensus.locale.get('commons.pages.map_it'));

					oTable.data("columnsResponse", aDefaultIds);
				}

				$.each(columns, function(iColumns) {
					if(!$.ajaxValidator.fnIsNullOrUndefined(columns[iColumns])){
						if (!columns[iColumns].bFixed) {
							$.each(oCustomData, function(iCustomIndex) {

								// At the first iteration, add TH at the header of the table and push
								// data to the new array. The new array is created in order to create
								// a new reference to the object
								if (bAddDefaultHeader) {

									var sColumnClass 		= "";
									if(!$.ajaxValidator.fnIsNullOrUndefined(oCustomData[iCustomIndex])){
										if (oCustomData[iCustomIndex].sId == "ENCRYPTION_STATUS") {
											sColumnClass = "no-encryption";

										} else if (oCustomData[iCustomIndex].sId == "LIFECYCLE_STATE") {
											//sColumnClass = "active";
											oCustomData[iCustomIndex].bEffectSortable = true;

										} else if (oCustomData[iCustomIndex].sId == "ALARM") {
											sColumnClass = "alarm";
											oCustomData[iCustomIndex].bEffectSortable = true;

										}
										else if (oCustomData[iCustomIndex].sId == "LATITUDE") {
											oCustomData[iCustomIndex].bEffectSortable = true;

										} else if (oCustomData[iCustomIndex].sId == "LONGITUDE") {
											oCustomData[iCustomIndex].bEffectSortable = true;
										}

										if (oCustomData[iCustomIndex].sId == 'MAP_IT') {

											// Add column Map it for fix error of SORT
											if(config.oSettings.iMapCol) {

												var oMapIt = {sId : 'MAP_IT', sWidth : "5%", sName : sensus.locale.get("filter.device.mapit.label")};
												oCustomData.splice(parseInt(config.oSettings.iMapCol, 10) - 1, 0, oMapIt);

											}

											config.oSettings.iMapCol = iCustomIndex + 1;

										} else {


											sTr += oThis.addDefaultHeader(oCustomData[iCustomIndex].sName, true, sColumnClass, oCustomData[iCustomIndex].sOrderColumn);

											var columnsCopy = columns.slice(0);
											columnsCopy.splice(iCustomIndex, 0, oCustomData[iCustomIndex]);

											// Get information from init page and merge with data from database
											for (var iKey in columnsCopy) {
												if(!$.ajaxValidator.fnIsNullOrUndefined(columnsCopy[iKey])){
													if ((columnsCopy[iKey].sId == oCustomData[iCustomIndex].sId)) {
														aCustomDataColumn.push($.extend(oCustomData[iCustomIndex], columnsCopy[iKey]));
														break;
													}
												}
											}

										}

									}
								}
								// Get the index of the column that match with object from init page
								if(!$.ajaxValidator.fnIsNullOrUndefined(oCustomData[iCustomIndex])){
									if (columns[iColumns] && (columns[iColumns].sId == oCustomData[iCustomIndex].sId)) {
										return bAddDefaultHeader;
									}
								}
								sColumnClass = "";
							});
						}

						bAddDefaultHeader = false;

						// If columns doesn't have hide columns came from init object, push
						// to the new array

						if (!columns[iColumns].bVisible && columns[iColumns].bFixed) {
							aCustomDataColumn.push(columns[iColumns]);
							sTr += oThis.addDefaultHeader(oTable, "", false, columns[iColumns].sOrderColumn);
						}
					}
				});


				oTr.append(sTr);
				columns = aCustomDataColumn;
				config.aColumns = columns;

				config.fnServerData = function ( sSource, aoData, fnCallback ) {

					// Request AJAX
					sensus.widgets.datatable.fnCreateRequestModel.call(this, sSource, oTable, config, aoData, config.aColumns, fnCallback);
				}
			}

			return config;
		},

		fnFormatColumnResponse : function(e, oTable) {
			var listColumn = [],
				oColumn = {
					sId                : "",
					sName              : "",
					bEffectSortable    : "",
					propertyRequest    : "",
					propertyOrderEnum  : ""
				};

			e.aColumnsIds = [];
			e.aColumnsNames = [];

			$.each(e.listColumn, function(iColumnIndex) {

				var oCurrentColumn = $.extend({}, oColumn);

				oCurrentColumn.sId = e.listColumn[iColumnIndex].columnEnum;
				oCurrentColumn.bEffectSortable = e.listColumn[iColumnIndex].ordered;
				oCurrentColumn.sName = e.listColumn[iColumnIndex].fieldName;
				oCurrentColumn.propertyRequest = e.listColumn[iColumnIndex].propertyRequest;
				oCurrentColumn.sOrderColumn = e.listColumn[iColumnIndex].sOrderColumn;

				e.listColumn[iColumnIndex] = oCurrentColumn;

				e.aColumnsIds.push(oCurrentColumn.sId);
				e.aColumnsNames.push(oCurrentColumn.sName);
			});

			oTable.data("columnsResponse", e);

			return e;
		},

		/**
		 * Add dynamically columns
		 * @param config
		 * 				Object came from init pages
		 */
		addColHeader : function(config, hasColumn) {
			var settings = config.oSettings,
				columns = config.aColumns;
			//Add columns to new params
			if (settings.bCheckbox || settings.bDialogCheckbox) {
				columns.splice(0,0, {sId: "Check", sWidth : "1%", bSortable : true, bCustomColumn : true});
			}
			if (settings.remove || settings.process || settings.removeRecentRequest) {
				columns.splice(columns.length,0, {sId: "Blank", sWidth : "1%", bSortable : true, bCustomColumn : true});
			}
			if (settings.iMapCol) {
				columns.splice(settings.iMapCol,0, {sId: "Map", sWidth : "1%", bSortable : true, bCustomColumn : true});
			}

			var oRequest = null;

			if (!$.sc.isNullOrUndefined(config.oSettings.fnRequest)) {
				oRequest = new config.oSettings.oRequest(config.oSettings.fnRequest());
			}

			if ((!hasColumn) && (oCustomize.listColumn)) {
				$tableHeaders = $("#device_id").parent();
				$tableHeaders.addClass("sorting_asc");
			}

			if (DefaulSortEnum) {
				if(oRequest.deviceType == "HAN_DEVICE"){
					config.oSettings.sDefaultSort = "desc";
					$tableHeaders = $("#" + DefaulSortEnum.toLowerCase()).parent();
					$tableHeaders.addClass("sorting_desc");
					$tableHeaders = $("#" + DefaulSortEnum.toLowerCase()).parent().siblings().removeClass("sorting_desc");
				}else{
					$tableHeaders = $("#" + DefaulSortEnum.toLowerCase()).parent();
					$tableHeaders.addClass("sorting_asc");
					$tableHeaders = $("#" + DefaulSortEnum.toLowerCase()).parent().siblings().removeClass("sorting_asc");
				}
			}

			return columns;
		},

		/**
		 * Create a copy of the default configuration for datatable.
		 *
		 * @return default configuration for datatable
		 */
		getDefaultConfig : function() {
			return {
				sPaginationType : "full_numbers",
				bProcessing : true,
				bServerSide: true,
				sDom : 'tr<"yui-gb table-footer"<"yui-u paging first"p><"yui-u numbers"il>><"stamp">',
				bAutoWidth : false,
				iDisplayLength : 25,
				aLengthMenu : [25, 50, 100],
				asStripClasses : [ "", "alt" ],
				oLanguage : {
					sLengthMenu : sensus.locale.get('table.records.page'),
					sZeroRecords : sensus.locale.get('table.records.none'),
					sInfo : sensus.locale.get('table.records.info'),
					sInfoEmpty : sensus.locale.get('table.records.infoempty'),
					sInfoFiltered : sensus.locale.get('table.records.filter'),
					sProcessing : sensus.locale.get('table.processing'),
					oPaginate : {
						sFirst : "",
						sLast : "",
						sNext : sensus.locale.get('table.page.next') + " &gt;&gt;",
						sPrevious : "&lt;&lt; " + sensus.locale.get('table.page.prev')
					}
				}
			};
		},

		/**
		 * Index columns so that table configuration can be based on column id, not
		 * absolute position.
		 *
		 * @parm setup object for all columns
		 * @returns object with indexes for columns by id and visible columns by id
		 */
		getColumnSetup : function(columns) {
			var columnSetup = {
				columns : [],
				colIndexAll : {},
				colIndexVisible : {},
				aVisible : [],
				aoColumnDefs : [{
					bSortable : false,
					aTargets :  []
				}]
			};

			var visibleCounter = 0;
			var	iCustomColumns = 0;

			for ( var i = 0; i < columns.length; i++) {

				var column = jQuery.extend({}, columnSetup);

				column = jQuery.extend(column, columns[i]);
				columnSetup.colIndexAll[column.sId] = i;

				if (column.bVisible !== false) {

					columnSetup.aVisible.push(true);
					columnSetup.colIndexVisible[column.sId] = visibleCounter++;

				} else {

					columnSetup.aVisible.push(false);

				}

				if (column.bCustomColumn){
					iCustomColumns += 1;
				}

				if (column.bSortable == false || column.bEffectSortable) {
					columnSetup.aoColumnDefs[0].aTargets.push(i - iCustomColumns);
				}
				columnSetup.columns.push(columns[i]);
			}
			return columnSetup;
		},

		/**
		 * Add effect box to sort and open columns dialog
		 * @param oTable
		 * 				The Table Object
		 */
		addEffectSort : function(oTable) {

			var oTh = oTable.find("th"),
				fnCleanSortableClass = null,
				oTableDataColumn = oTable.data("table").aColumns,
				sSortOptions = '<div class="ui-dialog sort-options" style="display: none"><ul><li><a href="" class="asc"><span class="icon-small icn-sort-asc"></span> '+sensus.locale.get('commons.pages.custom.sortAscending')+'</a></li><li><a href="" class="desc"><span class="icon-small icn-sort-desc"></span> '+sensus.locale.get('commons.pages.custom.sortDescending')+'</a></li><li><a href="" class="custom-column-action"><span class="icon-small icn-col-select"></span> '+sensus.locale.get('commons.pages.Columns')+'</a></li></ul></div>';

			fnCleanSortableClass = function() {
				oTable.find('.sorting_desc').removeClass("sorting_desc");
				oTable.find('.sorting_asc').removeClass("sorting_asc");
			};

			$.each(oTableDataColumn.columns, function(iColumnIndex) {

				if (oTableDataColumn.columns[iColumnIndex].bEffectSortable) {

					var oSortOptions = $(sSortOptions),
						iColIndex = oTableDataColumn.colIndexAll[oTableDataColumn.columns[iColumnIndex].sId],
						iVisibleCol = oTableDataColumn.colIndexVisible[oTableDataColumn.columns[iColumnIndex].sId],
						oCol = $(oTh).eq(iColIndex);

					oCol.hover(function () {
						$('div', this).stop(true, true).delay(200).slideDown(200);
					}, function () {
						$('div', this).stop(true, true).slideUp(200);
					});

					if (!oCol.find(".sort-options").length) {
						oCol.removeClass("sorting_disabled").append(oSortOptions);
					}

					$(oSortOptions).find('.asc').click(function(e) {
						e.preventDefault();
						fnCleanSortableClass();
						oCol.removeClass("sorting_disabled").removeClass("sorting_disabled");
						oCol.addClass("sorting_asc");
						oTable.fnSort( [ [iVisibleCol,'asc'] ] );
					});

					$(oSortOptions).find('.desc').click(function(e) {
						e.preventDefault();
						fnCleanSortableClass();
						oCol.removeClass("sorting_disabled").removeClass("sorting_asc");
						oCol.addClass("sorting_desc")
						oTable.fnSort( [ [iVisibleCol,'desc'] ] );
					});

					$(oSortOptions).find('.custom-column-action').click(function(e) {
						e.preventDefault();
						$('#customize-filter').wCustomize('Columns',function() {}, oTable.data("columnsResponse"), oTable.data("table").customColumns);
					});

				}
			});

		},

		addDefaultHeader : function(sName, bVisible, sClass, sClassId) {
			var sDisplay = bVisible ? "" : "style='display: none'";

			if (sClass === 'unlocked') {
				var oThs = '<th class="sorting" rowspan="1" colspan="1" ' + sDisplay + '><span id="' + sClassId + '"><a class="'+ sClass +'">' + sName + '</a></span></th>'
			} else if (sClass === 'active' || sClass === 'alarm') {
				var oThs = '<th class="sorting ' + sClass + ' sorting_asc" rowspan="1" colspan="1" ' + sDisplay + '><span id="' + sClassId + '"><a">' + sName + '</a></span></th>'
			} else if (sName === "State") {
				var oThs = '<th class="active sorting" rowspan="1" colspan="1" ' + sDisplay + '><span class="'+ sClass +'" id="' + sClassId + '">' + sName + '</span></th>'
			} else {
				var oThs = '<th class="sorting" rowspan="1" colspan="1" ' + sDisplay + '><span class="'+ sClass +'" id="' + sClassId + '">' + sName + '</span></th>'
			}

			return oThs;
		},

		addHeader : function(oTable) {
			var datatable = oTable.data("table");
			var $headRows = oTable.children("thead").children("tr");
			var iHeadRows = $headRows.length;
			var iSecondRowLength = $($headRows).last().find("th").length;

			//Add dynamically th. Check if the th already exists

			var sMap = sensus.locale.get("commons.pages.map");

			if(datatable.id == "group-table"){
				sMap = sensus.locale.get("commons.pages.mapIt");
			}

			if (iHeadRows > 1) {

				if (datatable.aColumns.columns.length != $(oTable).children("thead").find("th").length - (iSecondRowLength - 1)) {
					//Add checkbox
					if (datatable.oSettings.bCheckbox || datatable.oSettings.bDialogCheckbox) {
						$('<th class="checkbox" rowspan='+iSecondRowLength+'><input type="checkbox" id="select-page" name="select-page"></input></th>').insertBefore(oTable.find("th:eq(0)"));
					}
					//Add remove or process
					if(datatable.oSettings.remove || datatable.oSettings.process || datatable.removeRecentRequest) {
						$($headRows[0]).append('<th rowspan='+ iSecondRowLength +'>&nbsp;</th>');
					}


					if (datatable.oSettings.iMapCol) {
						$("<th rowspan=" + iSecondRowLength +">" + sMap + "</th>").insertBefore(oTable.find("thead tr")
																													  .find("th:eq(" + datatable.oSettings.iMapCol + ")"));
					}
				}

			} else if (datatable.aColumns.columns.length != oTable.children("thead").find("th").length) {

				//Add checkbox
				if (datatable.oSettings.bCheckbox || datatable.oSettings.bDialogCheckbox) {
					$('<th class="checkbox"><input type="checkbox" id="select-page" name="select-page"></input></th>').insertBefore(oTable.find("th:eq(0)"));
				}
				//Add remove or process
				if(datatable.oSettings.remove || datatable.oSettings.process || datatable.removeRecentRequest) {
					$($headRows[0]).append('<th>&nbsp;</th>');
				}
				//Add map
				if (datatable.oSettings.iMapCol) {
					$("<th>" + sMap + "</th>").insertBefore(oTable.find("thead tr")
																				  .find("th:eq("+ datatable.oSettings.iMapCol +")"));
				}
			}
		},

		/**
		 * Row Callback, called each row itaration
		 *
		 * @param nRow
		 * 					current row
		 * @param aData
		 * 					row data
		 * @param iDisplayIndex
		 * 					row index
		 * @returns
		 * 			DOM row
		 */
		rowCallback : function(nRow, aData, iDisplayIndex) {

			var datatable      = $(this).data("table"),
				edit           = datatable.oSettings.edit,
				editCol        = null,
				order          = datatable.oSettings.order,
				orderCol       = null,
				input          = null,
				tdCheck        = null,
				remove         = null,
				removeRecentRequest = null,
				oAction        = null,
				abort          = null,
				summary        = null,
				cancel         = null,
				deleteBt       = null,
				smartpointCols = 0,
				mapCol         = null,
				aSelected      = datatable.oSettings.bDialogCheckbox ? sensus.widgets.datatable.selectedDialogRows : sensus.widgets.datatable.selectedRows,
				aDeselected    = datatable.oSettings.bDialogCheckbox ? sensus.widgets.datatable.deselectedDialogRows : sensus.widgets.datatable.deselectedRows,
				oActionEdit    = null,
				oActionClone   = null,
				oActionButton  = null,
				oActionCancel  = null,
				oActionDelete  = null,
				oActionPause   = null,
				oActionResume  = null,
				oActionUl      = null;

			//Abort
			abort = $('<td><a class="delete abort remove_dialog" href="#">'+ sensus.locale.get("commons.pages.expire")+'</a></td>').click(function(e) {

				e.preventDefault();

				sensus.widgets.datatable.currentRow = aData;

				sensus.widgets.datatable.abortDialog(datatable);
			});


			//Summary
			summary = $('<td><a class="button" href="#">'+ sensus.locale.get("table.summary")+'</a></td>').click(function(e) {
				e.preventDefault();
				sensus.util.page.startProgressBar();

				sensus.widgets.datatable.currentRow = aData;

				sensus.widgets.datatable.summaryDialog(datatable);
			});

			//Cancel
			cancel = $('<td><a class="delete abort remove_dialog" href="#">'+ sensus.locale.get("commons.pages.cancel") +'</a></td>').click(function(e) {
				e.preventDefault();

				sensus.widgets.datatable.currentRow = aData;

				sensus.widgets.datatable.cancelDialog(datatable);
			});

			//Remove
			remove = $('<td><a id="delete_' + iDisplayIndex + '" class="delete delete_dialog delete_col" href="#">'+ sensus.locale.get("table.action.delete") +'</a></td>').click(function(e) {

				e.preventDefault();

				sensus.widgets.datatable.currentRow = aData;

				sensus.widgets.datatable.removeDialog(datatable);

			});

			//removeRecentRequest
			removeRecentRequest = $('<td><a id="delete_' + iDisplayIndex + '" class="delete delete_dialog delete_col" href="#">'+ sensus.locale.get("commons.pages.remove") +'</a></td>').click(function(e) {

				e.preventDefault();

				sensus.widgets.datatable.currentRow = aData;

				sensus.widgets.datatable.removeRecentRequestDialog(datatable);

			});

			if (datatable.oSettings.process && datatable.oSettings.process.oAction) {
				oAction = $('<td class="button-action-expand"><a class="button" role="button"><span class="left">Actions</span><span class="ui-icon ui-icon-triangle-1-s left"></span></a><div id="listMenu" class="ui-dialog view-options hide"></div></td>')
				.hover(
						function () { $('div', this).stop(true, true).delay(200).slideDown(200); },
						function () { $('div', this).stop(true, true).slideUp(200); }
				);

				oActionUl = $('<ul/>');

				var sStatusColumn,
					aStatusColumn;

				if (datatable.oSettings.process.oAction.sStatusColumn) {
					sStatusColumn = aData[datatable.aColumns.colIndexAll[datatable.oSettings.process.oAction.sStatusColumn]];
					aStatusColumn = sStatusColumn.split('_');
					if(aStatusColumn.length > 1){
						sStatusColumn = aStatusColumn[1];
					}
				}

				// button [EDIT]
				if (datatable.oSettings.process.oAction.edit) {
					var url = datatable.oSettings.process.oAction.edit.url.split('?');

					if (url.length > 1) {
						oActionEdit  = $('<li><a id="menu-bt-edit" href="'
								+ datatable.oSettings.process.oAction.edit.url + '&id='+ aData[datatable.aColumns.colIndexAll.Id] + '&type=edit&editType=schedule'
								+ '"><span class="icon-small icn-edit"></span> '+ sensus.locale.get("commons.pages.edit") +'</a></li>');
					} else {
						oActionEdit  = $('<li><a id="menu-bt-edit" href="'
								+ datatable.oSettings.process.oAction.edit.url +'?id='+ aData[datatable.aColumns.colIndexAll.Id] + '&type=edit&editType=schedule'
								+ '"><span class="icon-small icn-edit"></span> '+ sensus.locale.get("commons.pages.edit") +'</a></li>');
					}

					oActionEdit.click(function(e) {

						e.preventDefault();

						if (datatable.oSettings.process.oAction.edit.bSaveSession) {

							sensus.util.session.create({
								key : "SelectedFilters",
								value : $.fn.pageLoader.queryString()
							});
						}

						$.fn.pageLoader.load($("a", oActionEdit).attr("href"), $("#tabs-content"));

						sensus.util.page.stopProgressBar();

					});

					oActionUl.append(oActionEdit);
				}

				// button [CLONE]
				if (datatable.oSettings.process.oAction.clone) {

					oActionClone = $('<li><a id="menu-bt-clone" href="'
							+ datatable.oSettings.process.oAction.clone.url +'?id='+ aData[datatable.aColumns.colIndexAll.Id]
							+ '&type=clone&editType=schedule"><span class="icon-small icn-clone"></span> '+ sensus.locale.get("commons.pages.clone") +'</a></li>');

					oActionClone.click(function(e) {

						e.preventDefault();

						if (datatable.oSettings.process.oAction.clone.bSaveSession) {

							sensus.util.session.create({
								key : "SelectedFilters",
								value : $.fn.pageLoader.queryString()
							});
						}

						$.fn.pageLoader.load($("a", oActionClone).attr("href"), $("#tabs-content"));

						sensus.util.page.stopProgressBar();

					});

					oActionUl.append(oActionClone);
				}
				// button [PAUSE]
				if (datatable.oSettings.process.oAction.pause && (sStatusColumn == "ENABLED") || (sStatusColumn == "Scheduled")) {

					oActionPause = $('<li><a id="menu-bt-play" href=""><span class="icon-small icn-pause"></span> '+ sensus.locale.get("commons.pages.pause") +'</a></li>').click(function(e) {

						e.preventDefault();

						var sUrl =  datatable.oSettings.process.oAction.pause.url,
							oRequest = {type : "statusPaused", id : aData[datatable.aColumns.colIndexAll.Id]};

						var fnCallBack = function(oResponse) {

							if (oResponse.operationSuccess === true) {
								sensus.util.page.showMessage("messaging-main", sensus.locale.get("systemintelligence.scheduledEvent.paused.success"), "confirm");
								datatable.oSettings.process.oAction.pause.success();
							} else {
								sensus.util.page.showMessage("messaging-main", sensus.locale.get("systemintelligence.scheduledEvent.paused.error"), "error");
							}

						};

						$.ajaxValidator.fnDoCall(sUrl, oRequest, false, fnCallBack);

					});

					oActionUl.append(oActionPause);
				}
				// button [RESUME]
				if (datatable.oSettings.process.oAction.resume && sStatusColumn.toLowerCase() == "Paused".toLowerCase()) {
					oActionResume = $('<li><a id="menu-bt-play" href=""><span class="icon-small icn-play"></span> '+ sensus.locale.get("commons.pages.resume") +'</a></li>').click(function(e) {

						e.preventDefault();

						var sUrl =  datatable.oSettings.process.oAction.pause.url,
							oRequest = {type : "statusResume", id : aData[datatable.aColumns.colIndexAll.Id]};

						var fnCallBack = function(oResponse) {

							if (oResponse.operationSuccess === true) {
								sensus.util.page.showMessage("messaging-main", sensus.locale.get("systemintelligence.scheduledEvent.resumed.success"), "confirm");
								datatable.oSettings.process.oAction.pause.success();
							} else {
								sensus.util.page.showMessage("messaging-main", sensus.locale.get("systemintelligence.scheduledEvent.resumed.error"), "error");
							}

						};

						$.ajaxValidator.fnDoCall(sUrl, oRequest, false, fnCallBack);

					});

					oActionUl.append(oActionResume);
				}
				// button [Cancel]
				if (datatable.oSettings.process.oAction.cancel) {
					oActionCancel = $('<li><a id="menu-bt-delete" title="'+ datatable.oSettings.process.oAction.cancel.text(aData, iDisplayIndex) +'" class="delete-dialog last" href=""><span class="icon-small icn-delete"></span> '+ sensus.locale.get("commons.pages.cancel") +'</a></li>').click(function(e) {
						e.preventDefault();

						sensus.widgets.datatable.currentRow = aData;
						sensus.widgets.datatable.cancelDialog(datatable);
					});
					oActionUl.append(oActionCancel);
				}
				// button [FAILED]
				if (datatable.oSettings.process.oAction.failed && sStatusColumn.toLowerCase() == "Failed".toLowerCase()&&(aData[datatable.aColumns.colIndexAll.smartPoints] > 0)) {
					oActionResume  = null;
					oActionResume = $('<li><a id="menu-bt-play" href=""><span class="icon-small icn-play"></span> '+ sensus.locale.get("commons.pages.runNow") +'</a></li>').click(function(e) {

						e.preventDefault();

						var sUrl =  datatable.oSettings.process.oAction.pause.url,
							oRequest = {type : "statusFailed", id : aData[datatable.aColumns.colIndexAll.Id]};

						var fnCallBack = function(oResponse) {

							if (oResponse.operationSuccess === true) {
								sensus.util.page.showMessage("messaging-main", sensus.locale.get("systemintelligence.scheduledEvent.failure.success"), "confirm");
								datatable.oSettings.process.oAction.pause.success();
							} else {
								sensus.util.page.showMessage("messaging-main", sensus.locale.get("systemintelligence.scheduledEvent.failure.error"), "error");
							}

						};

						$.ajaxValidator.fnDoCall(sUrl, oRequest, false, fnCallBack);

					});

					oActionUl.append(oActionResume);
				}

				// button [DELETE]
				if (datatable.oSettings.process.oAction.deleteBt) {
					oActionDelete = $('<li><a id="menu-bt-delete" title="'+ datatable.oSettings.process.oAction.deleteBt.text(aData, iDisplayIndex) +'" class="delete-dialog last" href=""><span class="icon-small icn-summary"></span> '+ sensus.locale.get("commons.pages.delete") +'</a></li>').click(function(e) {
						e.preventDefault();

						sensus.widgets.datatable.currentRow = aData;
						sensus.widgets.datatable.deleteDialog(datatable);
					});

					oActionUl.append(oActionDelete);
				}

				oAction.find('#listMenu').append(oActionUl);
			}

			if (datatable.oSettings.bCheckbox || datatable.oSettings.bDialogCheckbox) {
				aData.splice(0, 0, "");
			}

			if (datatable.oSettings.iMapCol) {
				mapCol = datatable.oSettings.iMapCol;
				aData.splice(mapCol, 0, "");
			}

			//Checkbox
			if (datatable.oSettings.bCheckbox || datatable.oSettings.bDialogCheckbox) {
				input = $('<input type=checkbox name="check_'+ iDisplayIndex +'" id="check_'+ aData[datatable.aColumns.colIndexAll.Id] +'" value="'+ aData[datatable.aColumns.colIndexAll.Id] +'"/>');
				tdCheck = $('td:eq(' + datatable.aColumns.colIndexAll.Check + ')', nRow);
				$("<td class='checkbox'></td>").append(input).insertBefore(tdCheck);
			};

			//Map
			if (datatable.oSettings.iMapCol) {

				mapCol = datatable.oSettings.iMapCol;

				$("<td class='map-col'><a id='map_" + iDisplayIndex + "' href='#'>" + sensus.locale.get("table.type.map") + "</a></td>").insertBefore($('td:eq(' + mapCol + ')', nRow)).click(
						function(evt, ui) {
							sensus.util.page.startProgressBar();
							evt.preventDefault();
							sensus.widgets.datatable.mapDialog(datatable, aData);
					});
			}

			//Remove Coulumns
			if (datatable.oSettings.remove) {

				$(nRow).append(remove);

			} else if (datatable.oSettings.removeRecentRequest) {

				$(nRow).append(removeRecentRequest);

			} else if (datatable.oSettings.process) {

				if (aData[datatable.aColumns.colIndexAll.status] == sensus.locale.get("table.type.processing")) {

					if (datatable.oSettings.process.abort) {

						$(nRow).append(abort);

					} else {

						$(nRow).append("<td/>");
					}

					$('td:eq(' + datatable.aColumns.colIndexAll.status + ')', nRow).addClass('processing').html("<span>"+ aData[datatable.aColumns.colIndexAll.status] +"</span>");

				} else if ((aData[datatable.aColumns.colIndexAll.status] == sensus.locale.get("table.type.scheduled"))||(aData[datatable.aColumns.colIndexAll.status] == "Paused")) {

					if (datatable.oSettings.process.oAction) {

						$(nRow).append(oAction);

					} else {

						$(nRow).append(cancel);
					}

				} else if (aData[datatable.aColumns.colIndexAll.status] == sensus.locale.get("table.type.complete")
								|| aData[datatable.aColumns.colIndexAll.status] == sensus.locale.get("table.type.error")
								|| aData[datatable.aColumns.colIndexAll.status] == sensus.locale.get("commons.pages.aborted")
								|| aData[datatable.aColumns.colIndexAll.status] == sensus.locale.get("summary.text.processStatus.EXPIRED")
								|| aData[datatable.aColumns.colIndexAll.status] == sensus.locale.get("table.type.cancelled")) {

					if (datatable.oSettings.process.remove) {
						$(nRow).append(remove);

					} else if (datatable.oSettings.process.removeRecentRequest) {

						$(nRow).append(removeRecentRequest);

					} else {

						var sSize = datatable.oSettings.process.sSmartpointSize;

						if (aData[datatable.aColumns.colIndexAll[sSize]] > 0) {

							$(nRow).append(summary);

						} else {

							$(nRow).append("<td/>");
						}
					}

				} else if (aData[datatable.aColumns.colIndexAll.status] == "Started"
							|| aData[datatable.aColumns.colIndexAll.status] == "Command Sent") {

					// Actions
					oAction = $('<td class="button-action-expand"><a class="button" role="button"><span class="left">'
							+ sensus.locale.get("commons.pages.actions") + '</span><span class="ui-icon ui-icon-triangle-1-s left">'
							+ '</span></a><div id="listMenu" class="ui-dialog view-options hide"></div></td>').hover(
								function () {
									$('div', this).stop(true, true).delay(200).slideDown(200);
								},
								function () {
									$('div', this).stop(true, true).slideUp(200);
								});

					oActionUl = $('<ul/>');

					// Summary
					oActionUl.append($('<li><a id="menu-bt-summary" title="' + sensus.locale.get("table.summary")
							+ '" class="delete-dialog last" href=""><span class="icon-small icn-summary"></span> '
							+ sensus.locale.get("table.summary") + '</a></li>').click(function(e) {
						e.preventDefault();
						sensus.util.page.startProgressBar();
						sensus.widgets.datatable.currentRow = aData;
						sensus.widgets.datatable.summaryDialog(datatable);
					}));

					// Abort
					if (datatable.oSettings.process.oAction && datatable.oSettings.process.oAction.bAbortAction) {

						var sAbortTitle = sensus.locale.get(datatable.oSettings.process.oAction.abortTitle(aData, datatable.aColumns.colIndexAll));
						var sIconStyle 	= "icn-delete";

						// Change the icon when action title was Expire
						if (sAbortTitle == "Expire") {
							sIconStyle = "icn-notice-form";
						}

						oActionUl.append($('<li><a id="menu-bt-abort" title="'
								+ sAbortTitle
								+ '" class="delete-dialog last" href=""><span class="icon-small '+ sIconStyle +'"></span> '
								+ sAbortTitle + '</a></li>').click(function(e) {
							e.preventDefault();
							sensus.widgets.datatable.currentRow = aData;
							sensus.widgets.datatable.abortDialog(datatable);
						}));
					}

					// button [EXPIRE]
					if (datatable.oSettings.process.oAction && datatable.oSettings.process.oAction.expire) {

						var oColumn = datatable.aColumns.colIndexAll;

						// Is action expireable
						if ($.inArray(aData[oColumn.ActionName], datatable.oSettings.process.oAction.expire.expireableActions) != -1) {

							// datatable.oSettings.process.oAction.expire.dialogSettings.sDialogText = datatable.oSettings.process.oAction.expire.title;

							oActionUl.append($("<li><a href='' class='menu-bt-expire last' title='"
									+ datatable.oSettings.process.oAction.expire.title
									+ "'><span class='icon-small icn-notice-form'></span> "
									+ sensus.locale.get("expire.actionlabel") + "</a></li>").click(function(e) {
										e.preventDefault();
										sensus.util.actiondialog.launchActionDialog("expireDialog",
												sensus.util.process.actions.processDialog(aData, oColumn, datatable.id,
														datatable.oSettings.process.oAction.expire.dialogSettings,
														datatable.oSettings.process.oAction.expire.device));
									}));
						}
					}

					oAction.find('#listMenu').append(oActionUl);
					$(nRow).append(oAction);
				};

				if (datatable.oSettings.process.oAction && aData[datatable.aColumns.colIndexAll.Locked] === sensus.locale.get("table.locked.true")) {

					$('td:eq('+ datatable.aColumns.colIndexAll.Locked +')', nRow).html("<span class='locked'></span>");
					$(nRow).append("<td></td>");

				} else if (datatable.oSettings.process.oAction && aData[datatable.aColumns.colIndexAll.Locked] === sensus.locale.get("table.locked.false")) {

					$('td:eq('+ datatable.aColumns.colIndexAll.Locked +')', nRow).html("<span class='unlocked'></span>");
					$(nRow).append(oAction);
				}

				if (datatable.oSettings.process && datatable.oSettings.process.oAction && datatable.oSettings.process.oAction.allRow == true) {

					$(nRow).append(oAction);
				}
			}

			// Smartpoint Link
			if (datatable.oSettings.smartpointFilter) {
				var aSmartpointFilterCols = datatable.oSettings.smartpointFilter.aCols,
					iSmartpointColsLentgh = aSmartpointFilterCols.length,
					iSmartpointColsIStart = 0;

				for (; iSmartpointColsIStart < iSmartpointColsLentgh; iSmartpointColsIStart += 1 ) {
					$('td:eq('+ datatable.aColumns.colIndexAll[aSmartpointFilterCols[iSmartpointColsIStart]] +')', nRow).html("<a class='alist' href='"+ sensus.util.page.fnFormatURLService() +"/ajax.list.action?"+ datatable.oSettings.smartpointFilter.filter +"="+ aData[datatable.aColumns.colIndexAll.Id] +"|'>"+ aData[datatable.aColumns.colIndexVisible[aSmartpointFilterCols[iSmartpointColsIStart]]] +"</a>").click(function(e)  {
						$.fn.pageLoader.parameter(datatable.oSettings.smartpointFilter.filter, aData[datatable.aColumns.colIndexAll.Id]);
					});
				}
			}

			//Edit link
			if (edit && datatable.aColumns.colIndexAll[edit.col]) {
				editCol = edit.col;

				var sOptionalParams = "";
				if (edit.params) {
					sOptionalParams = edit.params(aData, datatable.aColumns.colIndexAll);
				}

				var oEdit = $('<a class="alist" href="'+ edit.url +'?id='+ aData[datatable.aColumns.colIndexAll.Id]  + sOptionalParams  + '"><strong>' + (aData[datatable.aColumns.colIndexAll[editCol]] || "") + '</strong></a>');
				oEdit.click(function(e) {

					e.preventDefault();

					if (edit.bSaveSession) {

						sensus.util.session.create({
							key : "SelectedFilters",
							value : $.fn.pageLoader.queryString()
						});
					}
				});
				$('td:eq(' + datatable.aColumns.colIndexAll[editCol] + ')', nRow).html(oEdit);
			}

			//Order by Columns
			if (order) {
				orderCol = datatable.aColumns.colIndexAll[order];
				$(nRow).data("order", aData[orderCol]);
			}

			//Selection - ALL, PAGE, NONE,
			if (aSelected){

				var $checkCount = $("#checked-count");
				if (datatable.oSettings.bDialogCheckbox != undefined) {
					$checkCount = $("#checked-count-dialog");
				}

				if (sensus.widgets.datatable.isAllRows && datatable.oSettings.bDialogCheckbox == undefined) {
					input.prop("checked", true);
					for (deselected in aDeselected) {
					    if (aDeselected.hasOwnProperty(deselected)) {
							if (input.attr("value") == aDeselected[deselected].id) {
					        	input.prop("checked", false);
					        	$(this).find("thead input:checkbox").prop("checked", false);
								break;
					        }
					    }
				    }
				} else {
					$checkCount.html($().numberFormat(aSelected.length));
					//check pre selected checkboxes
					for (preChecked in aSelected) {
					     if (aSelected.hasOwnProperty(preChecked)) {
							if (input.attr("value") == aSelected[preChecked].id) {
					        	input.prop("checked", true);
					        }
					     }
				    }
				}
			}

			if (datatable.fnRowCallback) {
				datatable.fnRowCallback.call($(this), nRow, aData, iDisplayIndex, datatable.aColumns.colIndexAll);
			}

			var oNumberFormat = $('.number-format a', nRow);

			if (oNumberFormat.length == 0) {
				oNumberFormat = $('.number-format', nRow);
			}

			if (oNumberFormat.length > 0) {

				oNumberFormat.each(function() {
					$(this).text($().numberFormat(parseInt($(this).text())));
				});
			}
			return nRow;
		},

		/**
		 * Info Callback. Get the table information
		 * @param oSettings
		 * @param iStart
		 * @param iEnd
		 * @param iMax
		 * @param iTotal
		 * @param sPre
		 * @returns
		 * 			Object information
		 */

		fnInfoCallback  : function( oSettings, iStart, iEnd, iMax, iTotal, sPre ) {
			var datatable = sensus.widgets.datatable.oTableSettings,
				$tableFooter = $(".table-footer"),
				$next = $(".next", $tableFooter),
				$previous = $(".previous", $tableFooter),
				tableLength = $(".dataTables_length").find("select option:selected").val(),
				totalPagination = Math.ceil(iTotal / tableLength),
				current = Math.ceil(iEnd / tableLength);

			//Set corner border to next and previous buttons
			$next.addClass("ui-corner-all");
			$previous.addClass("ui-corner-all");

			//Check if current paginations is 1 to hide previous buttons
			if (iStart === 1) {
				$previous.hide();
			} else {
				$previous.show();
			}

			//Check if is the last pagination is the current item to hide next buttons
			if (current === totalPagination) {
				$next.hide();
			} else {
				$next.show();
			}

			//Set current pagination
			sensus.widgets.datatable.iCurrentPagination = current,

			//Set Total items
			sensus.widgets.datatable.iTotalPagination = totalPagination;

			if (datatable.oSettings.bSelection) {
				sensus.widgets.datatable.allRowsCount = iTotal;
			}

			if (!datatable.oSettings.bDialogCheckbox) {
				var Oresults =$('div .results'),
				sHtml = '';

				if (!$.isNumeric(iTotal)) {
					sHtml += '<span id="results">' + 0 + '</span>&nbsp;'+sensus.locale.get("commons.pages.matches");
				} else if ((iTotal != 1) && (iTotal != 0)) {
					sHtml += '<span id="results">'+$().numberFormat(iTotal)+'</span>&nbsp;'+sensus.locale.get("commons.pages.matches");
				} else {
					sHtml += '<span id="results">'+$().numberFormat(iTotal)+'</span>&nbsp;'+sensus.locale.get("commons.pages.matche");
				}

				Oresults.empty().append(sHtml);
			}

			if (datatable.fnInfoCallback) {
				datatable.fnInfoCallback(oSettings, iStart, iEnd, iMax, iTotal, sPre);
			}

			return sPre;
		},

		/**
		 * Footer callback called when footer is done
		 * @param nRow
		 * 				Actual Row
		 * @param aaData
		 * 				Table Data
		 * @param iStart
		 * @param iEnd
		 * @param aiDisplay
		 * @param iRecordsDisplay
		 */

		fnFooterCallback : function(nRow, aaData, iStart, iEnd, aiDisplay, iRecordsDisplay) {

			var datatable = $(this).data("table"),
				iDataLength = $(this).parent().find(".table-footer option:selected").text();

			// Filter slide left border
			if ($("#w-filters").length) {

				// Add class to continue the separate filter line
				$(".table-footer").addClass("side-slide").css({borderTop : "none"});
			}

			if (!$("#action-dialog-lrp").find(this).length) {
				$.fn.pageLoader.parameter("iDisplayStart", parseInt(iStart, 10));
			}

			//Functionality to check checkbox
			if (datatable.oSettings.bCheckbox) {
				sensus.widgets.datatable.selectFunctionality.call($(this));
			}else if (datatable.oSettings.bDialogCheckbox){
				sensus.widgets.datatable.selectDialogFunctionality.call($(this));
			}

			//Functionalities to all, page, none
			if (datatable.oSettings.bSelection) {
				sensus.widgets.datatable.clearSelects.call($(this));
				sensus.widgets.datatable.selectionButtonPage.call($(this));
				sensus.widgets.datatable.selectAll.call($(this));
			}

			if (aaData.length > 0) {
				$(this).parent().siblings(".blankslate").hide();
				$(this).show();
				$(this).siblings('.table-footer').show();

			} else {
				$(this).hide();
				$('.table-footer').hide();
				$(this).parent().siblings(".blankslate").show();
			}

			if (datatable.fnFooterCallback) {
				datatable.fnFooterCallback.call($(this), nRow, aaData, iStart, iEnd, aiDisplay, iRecordsDisplay, datatable.aColumns.colIndexAll);
			}

			sensus.util.page.stopProgressBar(iDataLength, true);

		},

		fnHeaderCallback : function( nHead, aasData, iStart, iEnd, aiDisplay) {
			sensus.widgets.datatable.addHeader(this);
			sensus.widgets.datatable.addEffectSort(this);
		},

		/**
		 * Called on every 'draw' event, and allows dynamically modify any aspect you want about the created DOM.
		 * @param oSettings
		 */
		fnDrawCallback : function(oSettings) {

			var oTableSetting = this.data("table");

			if (oTableSetting && oTableSetting.$exportCSVElement) {

				if (oSettings.aoData && oSettings.aoData.length) {

					oTableSetting.$exportCSVElement.removeClass("disabled");

				} else {

					oTableSetting.$exportCSVElement.addClass("disabled");
				}
			}

			var datatable = $(this).data("table"),
				$table = $(this),
				$selectPage = $('#select-page'),
				$tableFooter = $(".table-footer"),
				$previous = $(".previous", $tableFooter),
				iCurrent = sensus.widgets.datatable.iCurrentPagination,
				iTotalPagination = sensus.widgets.datatable.iTotalPagination,
				$paginationContainer = $previous.next(),
				$paginationLast = $("span:last", $paginationContainer),
				$paginationFirst = $("span:first", $paginationContainer),
				$oPaging = $(this).siblings('.table-footer').find('.paging'),
				$actionTableLength = $("#action-table_length"),
				$actionTableInfo = $("#action-table_info");

			if (iTotalPagination === 1) {

				$oPaging.hide();
				$actionTableLength.hide();
				$actionTableInfo.text($actionTableInfo.text().replace("|",""));

			} else {

				$oPaging.show();

			}

			sensus.widgets.datatable.iCurrentPagination = 0;
			sensus.widgets.datatable.iTotalPagination = 0;

			var iPaginatePosition = 1;

			$(".dataTables_paginate span span").each(function() {

				if ($(this).hasClass('paginate_button')) {

					iPaginatePosition += 1;

				}

				if ($(this).hasClass('paginate_active')) {

					return false;

				}
			});

			if ((iPaginatePosition < 7) && (iTotalPagination > 12)) {

				//Add last pagination
				if (iCurrent < iTotalPagination - 2) {

					if (iCurrent + 7 <= iTotalPagination) {

						$paginationLast.text(iTotalPagination);
						$paginationLast.prev().text(iTotalPagination - 1);
						$("<span class='paginate_button'>...</span>").insertBefore($paginationLast.prev());

					}
				} else if (iCurrent < iTotalPagination - 1) {

					$paginationLast.text(iTotalPagination);
					$("<span class='paginate_button'>...</span>").insertBefore($paginationLast);

				}
			}

			if ((iCurrent == 7) && (iTotalPagination > 12)) {
				//Add first button
				var buttonFirst = $('span span.paginate_button:first').clone(true);
				buttonFirst.text('1');

				var tablePaginate = $('span:eq(2)','.dataTables_paginate');
				$(buttonFirst).prependTo(tablePaginate);

			} else if ((iCurrent > 7) && (iTotalPagination > 12)) {

				//Add first Pagination
				if (iCurrent > 3) {

					$paginationFirst.text("1");
					$paginationFirst.next().text("2");
					$("<span class='paginate_button'>...</span>").insertAfter($paginationFirst.next());

				} else if (iCurrent > 2) {

					$paginationFirst.text("1");
					$("<span class='paginate_button'>...</span>").insertAfter($paginationFirst);

				}
			}

			/*
			//Add Last Refresh
			if (datatable.dateRefresh != undefined) {

				var sDate = $.date.dateFormat(datatable.dateRefresh);
				$(".stamp").text(sensus.locale.get('table.refreshed', sDate)).wrapInner("<em/>");

			}
			*/

			if ($table.find(":checked").length > ($table.closest('table').find('tbody').find('tr').length - 1)) {
				$table.find($selectPage).prop('checked', true);
			} else {
				$table.find($selectPage).prop('checked', false);
			}

			$('.button').button();

			//Order by column
			if (datatable.oSettings.order) {

				var data		= null;
				var $menuDay 	= $('#menuDay');
				var sDemandRead = "demandReadModelAction";

				$(this).find("tbody tr").each(function(i, e) {

					var order 			= $(e).data("order");
					var orderDecorated 	= "";

					if (datatable.oSettings.fnOrderDecorator) {
						orderDecorated = datatable.oSettings.fnOrderDecorator(order) || order;
					} else {
						orderDecorated = order;
					}

					if (!data) {

						data = orderDecorated;

						if (datatable.oSettings.order == sDemandRead) {

							oButtonBack = "<tr class='row-header'><td colspan='" + (parseInt(datatable.aColumns.columns.length, 10) + 1) +"'><h4>" + orderDecorated +"</h4></td></tr>";
							$menuDay.html(oButtonBack);

						} else {
							$("<tr class='row-header'><td colspan='" + (parseInt(datatable.aColumns.columns.length, 10) + 1) +"'><h4>" + orderDecorated +"</h4></td></tr>").insertBefore(e);
						}

					} else if (orderDecorated != data) {

						data = orderDecorated;

						if (datatable.oSettings.order == sDemandRead) {

							oButtonBack = "<tr class='row-header'><td colspan='" + (parseInt(datatable.aColumns.columns.length, 10) + 1) +"'><h4>" + orderDecorated +"</h4></td></tr>";
							$menuDay.html(oButtonBack);

						} else {
							$("<tr class='row-header'><td colspan='" + (parseInt(datatable.aColumns.columns.length, 10) + 1) +"'><h4>" + orderDecorated +"</h4></td></tr>").insertBefore(e);
						}
					}
				});
			}

			//Hide columns that contains bVisible false
			var aVisible = datatable.aColumns.aVisible;

			for (var i in aVisible) {
				if (aVisible.hasOwnProperty(i)) {
					if (!aVisible[i]){
						$.each($(this).find("tr"), function(iTr, e) {
							$(e).find("td:eq("+ i +")").addClass("hide");
						});
					};
				}
			}

			if (datatable.fnDrawCallback) {
				datatable.fnDrawCallback.call($(this), oSettings, datatable.aColumns.colIndexAll);
			}

			if (datatable.oSettings.bFooterVisible === false) {
				$table.parent().find('.table-footer').remove();
				$table.parent().find('.stamp').remove();
			}

		},

		fnPreDrawCallback : function(oSettings) {
			var datatable = $(this).data("table");
			sensus.util.page.startProgressBar();
			var oSelect = $(this).parent().find('select');
			oSelect.bind("change", function() {
				$.fn.pageLoader.parameter("iDisplayStart", 0);
			    oSettings._iDisplayStart = 0;
			})
			var events = oSelect.data("events");

			if (events != undefined) {
				events.change.reverse();
			}

			if (datatable.fnPreDrawCallback) {
				datatable.fnPreDrawCallback.call($(this), oSettings);
			}
		},

		fnCheckExistsSelected : function (aArray, val) {
			var iArrayLength = aArray.length;
			var iPosition = -1;
			for (var iPos = 0; iPos < iArrayLength; iPos++) {
				if (aArray[iPos].id == val) {
					iPosition = iPos;
					break;
				}
			}
			return iPosition;
		},

		fnAddSelectedValues : function (oCheckbox) {
			var data	   = $(oCheckbox).closest("table").data("table"),
				aSelectedParameters = data.oSettings.aSelectedParameters,
				oVal = {};

			// Add id to oVal Object. This is the default parameter passed.
			oVal.id = $(oCheckbox).attr("value");

			// Parameters passed at request.
			if (aSelectedParameters != undefined) {
				var iParametersLength = aSelectedParameters.length;
				for (var iParameterPos = 0; iParameterPos < iParametersLength; iParameterPos ++) {
					var sParameter = aSelectedParameters[iParameterPos];
					oVal[sParameter] = $(oCheckbox).closest("tr").find("td:eq("+ data.aColumns.colIndexAll[sParameter] +")").text();
				}
			}

			return oVal;
		},

		selectDialogFunctionality : function() {
			var $actiondialog = $("#action-dialog-lrp");
			sensus.widgets.datatable.selectionCheckPage.call($(this), $actiondialog);
			sensus.widgets.datatable.selectCheckbox.call($(this), $actiondialog);
		},

		selectFunctionality : function() {
			sensus.widgets.datatable.selectionCheckPage.call($(this), $());
			sensus.widgets.datatable.selectCheckbox.call($(this), $());
		},

		addSelected : function(val, bDialog) {
			var selected = bDialog ? sensus.widgets.datatable.selectedDialogRows : sensus.widgets.datatable.selectedRows,
				deselected = bDialog ? sensus.widgets.datatable.deselectedDialogRows : sensus.widgets.datatable.deselectedRows;

			//If deselectedRows contains current checkbox, remove it
			var iDeselectedPos = sensus.widgets.datatable.fnCheckExistsSelected(deselected, val.id);
			deselected.splice(iDeselectedPos, 1);

			//Add current checkbox to selectedRows
			if (val.id.match('^(0|[1-9][0-9]*)$')) {
				var iSelectedPos = sensus.widgets.datatable.fnCheckExistsSelected(selected, val.id);
				if (iSelectedPos == -1) {
					selected.push(val);
				}
			}
		},

		addDeselected : function(val, bDialog) {
			var selected = bDialog ? sensus.widgets.datatable.selectedDialogRows : sensus.widgets.datatable.selectedRows,
				deselected = bDialog ? sensus.widgets.datatable.deselectedDialogRows : sensus.widgets.datatable.deselectedRows;


			//If selectedRows contains current checkbox, remove it
			var iSelectedPos = sensus.widgets.datatable.fnCheckExistsSelected(selected, val.id);
			selected.splice(iSelectedPos, 1);

			if (val.id.match('^(0|[1-9][0-9]*)$')) {
				var iDeselectedPos = sensus.widgets.datatable.fnCheckExistsSelected(deselected, val.id);
				if (iDeselectedPos == -1) {
					deselected.push(val);
				}
			}
		},

		setTotalResult : function(bDialog) {

			var selected = bDialog ? sensus.widgets.datatable.selectedDialogRows : sensus.widgets.datatable.selectedRows,
				deselected = bDialog ? sensus.widgets.datatable.deselectedDialogRows : sensus.widgets.datatable.deselectedRows;
				$checkCount = null;

			if (bDialog) {
				$checkCount = $("#checked-count-dialog");
			} else {
				$checkCount = $("#checked-count");
			}

			if (bDialog) {
				$checkCount.text($().numberFormat(selected.length));
			} else {
				if (sensus.widgets.datatable.isAllRows == true) {
					$checkCount.text($().numberFormat(sensus.widgets.datatable.allRowsCount - deselected.length));
				} else {
					$checkCount.text($().numberFormat(selected.length));
				}
			}
		},

		fnGetSelectedData : function (bDeselected, bReturnAsArray) {
			var aData = [];
			var aSelected = bDeselected ? sensus.widgets.datatable.deselectedRows : sensus.widgets.datatable.selectedRows;
			var iSelectedLength = aSelected.length;

			// Set bReturnAsArray 'true' when no parameters is sent
			if ($.ajaxValidator.fnIsNullOrUndefined(bReturnAsArray)) {
				bReturnAsArray = true;
			}

			if (bReturnAsArray) {

				// Return as Array separated by "|"

				for (var iSelectedPos = 0; iSelectedPos < iSelectedLength; iSelectedPos ++) {
					var sAttrs;
					if (bDeselected) {
						sAttrs = parseInt(aSelected[iSelectedPos]["id"], 10);
						aData.push(sAttrs);
					} else {
						sAttrs = "";
						for (var attr in aSelected[iSelectedPos]) {
							sAttrs += aSelected[iSelectedPos][attr] + "|";
						}
						aData.push(sAttrs);
					}
				}
			} else {

				// Return as Object

				aData = aSelected;
			}

			return aData;
		},

		/**
		 * TODO - Throw this function on a separate file
		 * Function to check all checkbox when clickin on checkbox of the head
		 */
		selectCheckbox : function($dialog) {

			var	bHasDialog = $dialog.length,
				$table     = bHasDialog ? $dialog.find(this) : $(this),
				$checkPage = bHasDialog ? $dialog.find('#select-page') : $('#select-page'),
				$actions   = $(".actions"),
				$tbody     = $table.find("tbody");

			// Clean Propagaiton from click. After reload table, avoid duplicated click.
			$tbody.undelegate("input:checkbox", "click");


			$tbody.delegate("input:checkbox", "click", function(event) {

				var oVal = sensus.widgets.datatable.fnAddSelectedValues($(this));

				if (!$(this).is(':checked')) {

					$checkPage.prop('checked', false);
					sensus.widgets.datatable.addDeselected(oVal, bHasDialog);

				} else {

					if ($('tbody', $table).find(":checked").length >= ($tbody.find('tr').length)) {
						if (!$('#process-table').is(':visible')) {
							$('#actions .message').addClass("ui-state-highlight");
						} else {
							$('.long-running').find('.message').addClass("ui-state-highlight");
						}
						$checkPage.prop('checked', true);
					} else {
						$checkPage.prop('checked', false);
					}

					sensus.widgets.datatable.addSelected(oVal, bHasDialog);

					if (!$('#process-table').is(':visible')) {
						$actions.find('.message').removeClass("ui-state-error");
					} else {
						$('.long-running').find('.message').removeClass("ui-state-error");
					}
				}

				$(this).toggleClass('row_selected');

				sensus.widgets.datatable.setTotalResult(bHasDialog);
			});

		},

		/**
		 * TODO - Throw this function on a separate file
		 *Event to Page link.
		 */
		selectionCheckPage : function($dialog) {

			var bHasDialog = $dialog.length,
				$table = bHasDialog ? $dialog.find(this) : $(this),
				$actions = $(".actions"),
				$thead = $table.find("thead");

			$thead.delegate("input:checkbox", "click", function(e) {
				//Cheking all checkbox
				if($(this).is(':checked')) {

					//Check all checkbox from page and remove it from deselectedRows
					$table.find('tbody input:checkbox').prop('checked', true).each(function(i){
						var oVal = sensus.widgets.datatable.fnAddSelectedValues($(this));
						sensus.widgets.datatable.addSelected(oVal, bHasDialog);
					});

				} else {

					$table.find('tbody input:checkbox').prop('checked', false).each(function(i){
						var oVal = sensus.widgets.datatable.fnAddSelectedValues($(this));
						sensus.widgets.datatable.addDeselected(oVal, bHasDialog);
					});
				}

				sensus.widgets.datatable.setTotalResult(bHasDialog);

				//minus the top header checkbox
				if (!$('#process-table').is(':visible')) {
					$actions.find('.message').removeClass("ui-state-error").addClass("ui-state-highlight");
				} else {
					$('.long-running').find('.message').removeClass("ui-state-error").addClass("ui-state-highlight");
				}

			});
		},

		/**
		 * TODO - Throw this function on a separate file
		 * Event to check all checkbox when clickin on checkbox of the head
		 */
		selectionButtonPage : function() {

			var $table = $(this);
			var $actions = $("#actions");

			$(".result-count").delegate(".select-page", "click", function(e) {

				e.preventDefault();

				if (sensus.widgets.datatable.isAllRows) {
					sensus.widgets.datatable.clearSelectsFunction.call($(this));
					sensus.widgets.datatable.isAllRows = false;
				}

				$table.find(':checkbox').attr('checked', true);

				//get the checked items from page
				$table.find(":checked").each(function(i) {
					var oVal = sensus.widgets.datatable.fnAddSelectedValues($(this));
					sensus.widgets.datatable.addSelected(oVal, false);
				});

				//minus the top header checkbox
				sensus.widgets.datatable.setTotalResult(false);
				$actions.find('.message').removeClass("ui-state-error").addClass("ui-state-highlight");
			});
		},

		/**
		 * TODO - Throw this function on a separate file
		 * Select All Checkbox of the table
		 * @param e
		 */
		selectAll : function() {

			var $actions = $("#actions"),
				$table = $(this);

			$(".result-count").delegate(".select-all", "click", function(e) {

				e.preventDefault();

				sensus.widgets.datatable.clearSelectsFunction.call($table);

				$table.find('input:checkbox').prop('checked', true);

				//Enable allRows selection for server command to be sent
				sensus.widgets.datatable.isAllRows = true;

				$table.find(":checked").each(function(i) {
					sensus.widgets.datatable.addSelected({id : $(this).attr("value")});
				});

				sensus.widgets.datatable.setTotalResult(false);

				$actions.find('.message').removeClass("ui-state-error").addClass("ui-state-highlight");
			});
		},

		/**
		 * TODO - Throw this function on a separate file
		 * Clear All Checkbox of the table
		 * @param e
		 */
		clearSelects : function() {
			var $table = $(this);

			$(".result-count").delegate(".select-none", "click", function(e) {
				e.preventDefault();
				sensus.widgets.datatable.clearSelectsFunction.call($table);
			});
		},

		/**
		 * TODO - Throw this function on a separate file
		 * Functionality to clear checkbox
		 */
		clearSelectsFunction : function() {
			var oDialog = $("#action-dialog-lrp:visible");
			var bDialog = oDialog.length;
			var $table = $(this);

			$table.find('input:checkbox').prop('checked', false);

			if (bDialog) {

				var $actions = $("#actions", oDialog);

				$("#checked-count-dialog").text('0');
				sensus.widgets.datatable.selectedDialogRows = [];
				sensus.widgets.datatable.deselectedDialogRows = [];
				$actions.find('.message').removeClass("ui-state-error").removeClass("ui-state-highlight");
				$actions.find(".filter_select").hide();

			} else {
				var $actions = $("#actions");

				//Enable allRows selection for server command to be sent
				sensus.widgets.datatable.isAllRows = false;

				$("#checked-count").text('0');
				sensus.widgets.datatable.selectedRows = [];
				sensus.widgets.datatable.deselectedRows = [];
				$actions.find('.message').removeClass("ui-state-error").removeClass("ui-state-highlight");
				$actions.find(".filter_select").hide();
			}
		},

		/**
		 * Functionality to call dialogs
		 * @param datatable - The table
		 */
		removeDialog : function(datatable) {
			this.oTableSettings = datatable;
			sensus.util.actiondialog.launchActionDialog("remove", this.dialogSettings["remove"]);
		},


		/**
		 *  Functionality to call dialogs
		 * @param datatable - The table
		 */
		removeRecentRequestDialog : function(datatable) {
			sensus.util.actiondialog.launchActionDialog("removeRecentRequest",
					sensus.util.process.actions.processDialog(
						this.currentRow,
						datatable.aColumns.colIndexAll,
						datatable.id,
						datatable.oSettings.process.removeRecentRequest));
		},

		/**
		 * Functionality to call dialogs
		 * @param datatable - The table
		 */
		summaryDialog : function(datatable) {
			this.oTableSettings = datatable;
			sensus.util.actiondialog.launchActionDialog("summary", this.dialogSettings["summary"]);
		},

		/**
		 * Functionality to call dialogs
		 * @param datatable - The table
		 */
		abortDialog : function(datatable) {

			sensus.util.actiondialog.launchActionDialog("abort",
					sensus.util.process.actions.processDialog(
							this.currentRow,
							datatable.aColumns.colIndexAll,
							datatable.id,
							datatable.oSettings.process.abort));
		},

		/**
		 * Functionality to call dialogs
		 * @param datatable - The table
		 */
		cancelDialog : function(datatable) {

			this.oTableSettings = datatable;

			if (datatable.oSettings.process.oAction) {

				this.oTableSettings.oSettings.process.cancel =  datatable.oSettings.process.oAction.cancel;
				sensus.util.actiondialog.launchActionDialog("cancel", this.dialogSettings["cancel"]);

			} else {
				sensus.util.actiondialog.launchActionDialog("cancel", this.dialogSettings["cancel"]);
			}
		},

		/**
		 * Functionality to call dialogs to Delete
		 * @param datatable - The table
		 */
		deleteDialog : function(datatable) {

			this.oTableSettings = datatable;

			if (datatable.oSettings.process.oAction) {
					sensus.util.actiondialog.launchActionDialog("deleteGroups", this.dialogSettings["deleteGroups"]);
			}
		},

		mapDialog : function(datatable, aData) {
			this.oTableSettings = datatable;
			this.currentRow = aData;
			sensus.util.actiondialog.launchActionDialog("mapIt", this.dialogSettings["mapIt"]);
		}
	};

	/**
	 * TODO - Throw this object on a separate file.
	 * Dialog Settings Objects. Hols all dialogs settings objects
	 *
	 */
	sensus.widgets.datatable.dialogSettings = {
			remove : {

				/**
				 * The dialog title.
				 */
				title : sensus.locale.get("commons.pages.deleteConfirm"),

				/**
				 * Whether this dialog requires a smartpoint list.
				 */
				requiresSmartpoints : false,

				/**
				 * The dialog width.
				 */
				width : 300,
				/**
				 * The dialog buttons (submit and cancel).
				 */
				buttons : (function() {
					var buttons = {};

					buttons[sensus.locale.get("commons.pages.delete")] = function() {

						var settings = sensus.widgets.datatable.oTableSettings.oSettings.remove || sensus.widgets.datatable.oTableSettings.oSettings.process.remove;
						var sValue;
						var sParamValue;
						var sParamName;

						if (settings.param) {
							sParamValue = sensus.widgets.datatable.oTableSettings.oSettings.remove.param.paramValue;
							sParamName = sensus.widgets.datatable.oTableSettings.oSettings.remove.param.paramName;;
							sValue = sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll[sParamValue]];
						}

						// Call back function for to show the delete message
						var fnCallback = function (data) {

							var success = settings.success(sensus.widgets.datatable.currentRow, sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll);

							if (data.operationSuccess == true) {
								sensus.util.page.showMessage("messaging-main", success, "confirm");
							} else {
								sensus.util.page.showMessage("messaging-main", sensus.util.page.getMessageList(data.messages), "error");
							}
						}

						var removeRequest;

						// If the table is saved search the request for to delete is the customSearchRequest object
						if (sensus.widgets.datatable.oTableSettings.id == "saved-table") {

							var oCustomSearch = new CustomSearch({
								id : sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.Id]
							});

							removeRequest = new CustomSearchRequest({
								customSearch : oCustomSearch
							});

						} else {

							removeRequest = new RemoveRequest({id : sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.Id]});

							if (sParamValue) {

								removeRequest[sParamName] = sValue;
							}
						}

						// Function for to remove one element
						$.ajaxValidator.fnDoCall (settings.url, removeRequest, false, fnCallback);

						$("#action-dialog").dialog('close');
					};

					buttons[sensus.locale.get("commons.pages.cancel")] = function() {
						$("#action-dialog").dialog('close');
					};

					return buttons;
				})(),

				/**
				 * The function that will be called when the action dialog is launched.
				 *
				 * @param actionDialog
				 *            a reference to the actionDialog objext
				 */
				action : function(actionDialog) {

					var settings = sensus.widgets.datatable.oTableSettings.oSettings.remove || sensus.widgets.datatable.oTableSettings.oSettings.process.remove;

					$('#action-dialog').load('commons/pages/datatable_remove.jsp', function() {

						$("#action-dialog").removeClass("waiting");
						$("#selected").append(settings.text(sensus.widgets.datatable.currentRow, sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll));

					});

					$("#action-dialog").dialog('open');
				}
			},

			removeRecentRequest : {

				/**
				 * The dialog title.
				 */
				title : sensus.locale.get("commons.pages.removeConfirm"),

				/**
				 * Whether this dialog requires a smartpoint list.
				 */
				requiresSmartpoints : false,

				/**
				 * The dialog width.
				 */
				width : 300,
				/**
				 * The dialog buttons (submit and cancel).
				 */
				buttons : (function() {
					var buttons = {};
					buttons[sensus.locale.get("commons.pages.remove")] = function() {
						var settings = sensus.widgets.datatable.oTableSettings.oSettings.removeRecentRequest || sensus.widgets.datatable.oTableSettings.oSettings.process.removeRecentRequest;
						var sOptionalParam = "";
						if(settings.param){
							sOptionalParam = "&";
							sParamValue    = sensus.widgets.datatable.oTableSettings.oSettings.removeRecentRequest.param.paramValue;
							sOptionalParam += sensus.widgets.datatable.oTableSettings.oSettings.removeRecentRequest.param.paramName + "=" + sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll[sParamValue]]
						}
						$.ajax({
							url : settings.url,
							async : false,
							data : "id=" + sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.Id] + sOptionalParam,
							success : function(data){
								var success = settings.success(sensus.widgets.datatable.currentRow, sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll);
								if (data.result == sensus.constants.ajax.ok) {
									sensus.util.page.showMessage("messaging-main", success + sensus.util.page.getMessageList(data.messages), "confirm");
								} else {

									sensus.util.page.showMessage("messaging-main", sensus.util.page.getMessageList(data.messages), "error");

								}
							},
							error: function(e) {
								if (e.result == "FAIL") {
									sensus.util.page.showMessage("messaging-main", sensus.locale.get("commons.pages.error"), "error");
								}
							}
						});
						$("#action-dialog").dialog('close');
					};
					buttons[sensus.locale.get("commons.pages.cancel")] = function() {
						$("#action-dialog").dialog('close');
					};
					return buttons;
				})(),

				/**
				 * The function that will be called when the action dialog is launched.
				 *
				 * @param actionDialog
				 *            a reference to the actionDialog objext
				 */
				action : function(actionDialog) {
					var settings = sensus.widgets.datatable.oTableSettings.oSettings.removeRecentRequest || sensus.widgets.datatable.oTableSettings.oSettings.process.removeRecentRequest;
					$('#action-dialog').load('commons/pages/datatable_remove.jsp', function() {
						$("#action-dialog").removeClass("waiting");
						$("#selected").append(settings.text(sensus.widgets.datatable.currentRow, sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll));
					});
					$("#action-dialog").dialog('open');
				}
			},

		abort : {

			/**
			 * The dialog title.
			 */
			title : sensus.locale.get("commons.pages.abortConfirm"),

			/**
			 * Whether this dialog requires a smartpoint list.
			 */
			requiresSmartpoints : false,

			/**
			 * The dialog width.
			 */
			width : 300,
			/**
			 * The dialog buttons (submit and cancel).
			 */
			buttons : (function() {
				var buttons = {};
				buttons[sensus.locale.get("commons.pages.abort")] = function() {
					$.ajax({
						url : sensus.widgets.datatable.oTableSettings.oSettings.process.abort.url,
						async : false,
						data : "id=" + sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.Id],
						success : function(data) {
							var success = sensus.widgets.datatable.oTableSettings.oSettings.process.abort.success(sensus.widgets.datatable.currentRow, sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll);
							if (data.result == sensus.constants.ajax.ok) {
								sensus.util.page.showMessage("messaging-main", success + sensus.util.page.getMessageList(data.messages), "confirm");
							} else {

								sensus.util.page.showMessage("messaging-main", sensus.util.page.getMessageList(data.messages), "error");

							}
						},
						error: function(e) {
							if (e.result == "FAIL") {
								sensus.util.page.showMessage("messaging-main", sensus.locale.get("commons.pages.error"), "error");
							}
						}
					});
					$("#action-dialog").dialog('close');
				};
				buttons[sensus.locale.get("commons.pages.cancel")] = function() {
					$("#action-dialog").dialog('close');
				};
				return buttons;
			})(),
			/**
			 * The function that will be called when the action dialog is launched.
			 *
			 * @param actionDialog
			 *            a reference to the actionDialog objext
			 */
			action : function(actionDialog) {
				$('#action-dialog').load('commons/pages/datatable_remove.jsp', function() {
					$("#action-dialog").removeClass("waiting");
					$("#selected").append(sensus.widgets.datatable.oTableSettings.oSettings.process.abort.text(sensus.widgets.datatable.currentRow, sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll));
				});
				$("#action-dialog").dialog('open');
			}
		},

		cancel : {

			/**
			 * The dialog title.
			 */
			title : sensus.locale.get("systemintelligence.ScheduledEvent.cancelConfirm"),

			/**
			 * Whether this dialog requires a smartpoint list.
			 */
			requiresSmartpoints : false,

			/**
			 * The dialog width.
			 */
			width : 300,
			/**
			 * The dialog buttons (submit and cancel).
			 */
			buttons : (function() {
				var buttons = {};
				buttons[sensus.locale.get("systemintelligence.ScheduledEvent.cancel")] = function() {

					var sUrl = sensus.widgets.datatable.oTableSettings.oSettings.process.cancel.url,
						oRequest = {id: sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.Id]};

					var fnCallBack = function(oResponse) {

						if (oResponse.operationSuccess === true) {

							var success = sensus.widgets.datatable.oTableSettings.oSettings.process.cancel.success(sensus.widgets.datatable.currentRow, sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll);

							sensus.util.page.showMessage("messaging-main", sensus.locale.get("commons.pages.successScheduleEvent", sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.eventName]), "confirm");

						} else {
							sensus.util.page.showMessage("messaging-main", oResponse.messages[0].messageInfo.code, "error");
						}
					};

					$.ajaxValidator.fnDoCall(sUrl, oRequest, false, fnCallBack);

					$("#action-dialog").dialog('close');
				};
				buttons[sensus.locale.get("commons.pages.close")] = function() {
					$("#action-dialog").dialog('close');
				};
				return buttons;
			})(),
			/**
			 * The function that will be called when the action dialog is launched.
			 *
			 * @param actionDialog
			 *            a reference to the actionDialog objext
			 */
			action : function(actionDialog) {
				var $actionDialog = $("#action-dialog");

				$actionDialog.load('commons/pages/datatable_remove.jsp', function() {
					$actionDialog.removeClass("waiting");
					$("#selected").append(
							sensus.widgets.datatable.oTableSettings.oSettings.process.cancel.text(
									 sensus.widgets.datatable.currentRow
									,sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll
							)
					);
				});
				$actionDialog.dialog('open');
			}

		},

		/**
		 * Delete group from smartpoint selected
		 */
		deleteGroups: {

			/** * The dialog title. */
			title : sensus.locale.get("commons.pages.deleteConfirm"),

			/**  * The dialog width. */
			width : 300,

			/** * The dialog buttons (submit and cancel).*/
			buttons : (function() {
				var buttons = {},
					SmartPointCount;

				var actionDialog = $("#action-dialog");

				buttons[sensus.locale.get("commons.pages.delete")] = function() {

					$(this).dialog('close');


					$.ajax({
						url : sensus.widgets.datatable.oTableSettings.oSettings.process.oAction.deleteBt.url,
						type : 'POST',
						data :  "id=" + sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.Id],

						success : function(data) {

							sensus.widgets.datatable.clearSelectsFunction.call(sensus.pages.systemintelligenceaction.actionTable);
							sensus.widgets.datatable.reloadTable(sensus.pages.systemintelligenceaction.actionTable);
							if (data.result === sensus.constants.ajax.ok) {
								sensus.util.page.showMessage("messaging-main", sensus.locale.get("systemintelligence.page.action.deleteSuccess"), "confirm");
							} else {
								sensus.util.page.showMessage("messaging-main", sensus.locale.get(data.messages), "error");
							}
						}
					});

					actionDialog.dialog('close');
				};
				buttons[sensus.locale.get("commons.pages.cancel")] = function() {
					var actionDialog = $("#action-dialog");
					actionDialog.dialog('close');
				};
				return buttons;
			})(),
			/**
			 * The function that will be called when the action dialog is launched.
			 *
			 * @param actionDialog
			 *            a reference to the actionDialog objext
			 */
			action : function(actionDialog) {

				var actionDialog = $("#action-dialog");

				actionDialog.load(sensus.widgets.datatable.oTableSettings.oSettings.process.oAction.deleteBt.urlPage+'?id='+sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.Id], function() {

					actionDialog.removeClass("waiting");

				});

				actionDialog.dialog('open');
			}
		},

		/**
		 * Configuration for the "Processing Summary" dialog.
		 */
		summary : {
			/**
			 * The dialog title.
			 */
			title : sensus.locale.get("action.processingsummary.title"),

			width : 1038,

			minheight: 700,
			/**
			 * Whether this dialog requires a smartpoint list.
			 */
			requiresSmartpoints : false,

			action : function(actionDialog) {

				var $actionDialog = $("#action-dialog");

				$actionDialog.summary(sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.Id],sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.ActionName],'',sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.Id]);

				$actionDialog.dialog('open');

				sensus.util.page.stopProgressBar();
			}
		},

		mapIt : {

			//title : function(event, ui){
				//if(sensus.widgets.datatable.oTableSettings.id == "group-table"){
				//	sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.groupName] + ' - ' + sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.groupSmartPointCount] + ' '+$.sc.locale('commons.pages.smartPoints');
				//} else {
					//return '';
				//}

			//},
			width : 700,

			minheight: 400,
			/**
			 * Whether this dialog requires a smartpoint list.
			 */
			requiresSmartpoints : false,

			open : function() {

				sensus.util.page.stopProgressBar();

				// sensus.widgets.datatable.dialogSettings.mapIt.clickEvent.iClick = 0;
				// $(document).bind("click", sensus.widgets.datatable.dialogSettings.mapIt.clickEvent);
			},

			/**
			 * The function that will be called when the action dialog is launched.
			 *
			 * @param actionDialog
			 *            a reference to the actionDialog objext
			 */
			action : function(actionDialog) {

				sensus.widgets.datatable.objLatLon.splice(0, sensus.widgets.datatable.objLatLon.length);
				sensus.util.page.stopProgressBar(0,false);
				actionDialog.empty();
				actionDialog.append($("<div>").attr("id", "detail-map-total"));
				actionDialog.append($("<div>").attr("id", "detail-map-container").css("minHeight", sensus.widgets.datatable.dialogSettings.mapIt.minheight + "px"));
				//$('#detail-map-container').width('760px');
				//$('#detail-map-container').height('350px');

				var dialogTitle = '';

				var sName, iGroupId, iScheduleId, iUserId, aGroupIds, oSearch, oRequest;
				var	objLatLon = [];
				var oSearch;
				var url;
				var sMapSmall = null;
				var sDeviceType = null;
				var sDeviceSubType = null;
				var services = sensus.constants.services;
				var bActionve = true;

				if (sensus.widgets.datatable.oTableSettings.id == "group-table") {
					sDeviceType = sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.deviceType];
					sDeviceSubType = sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.hanDeviceType];
					bActionve = false;

					// Electric
					if (sensus.settings.serviceType == sensus.constants.services.electric.name)
					{

						if ($.sc.isNullOrUndefined(sDeviceType)) {
							sDeviceType = services.electric.meter.name;
						}

						if (services.electric.meter.name == sDeviceType)
						{
							url = "api/electricmeterdevice/fetchAll"
						} else if (services.electric.lcm.name == sDeviceType)
						{
							url = "api/lcmdevice/fetchAll";
						} else if (services.electric.han.name == sDeviceType)
						{
							url = "api/handevice/fetchAll";
						}
					}

					// Water
					if (sensus.settings.serviceType == sensus.constants.services.water.name)
					{
						url = "api/watermeterdevice/fetchAll";
						sDeviceType = services.water.meter.name
					}

					// Gas
					if (sensus.settings.serviceType == sensus.constants.services.gas.name)
					{
						url = "api/gasmeterdevice/fetchAll";
						sDeviceType = services.gas.meter.name;
					}


					dialogTitle = sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.groupName] + ' - ' + sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.groupSmartPointCount] + ' '+$.sc.locale('commons.pages.smartPoints');
					iGroupId = sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.Id];
					oSearch = new DeviceSearch({groups : [{id : iGroupId}]});

				} else if (sensus.widgets.datatable.oTableSettings.id == "smartpoint-table") {

					url = sensus.pages.device.smartpointTable.data().config.sAjaxSource;
					oSearch = sensus.pages.device.smartpointTable.data().config.oSettings.fnRequest();
					sMapSmall = "small";

					objLatLon.push({
						latitudeAvg : sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.latitude],
						longitudeAvg : sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.longitude],
						alertEnum : sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.alarm],
						radio : {flexNetId : sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.Id]},
						devicesTotalByLatLong : 1
					});

				}

				$("#action-dialog").dialog( { title: dialogTitle });

				var request = new InquiryDeviceRequest({ deviceSearch : oSearch});

				request.deviceType = sDeviceType;

				var mapConfig = {
					ajaxCalls :  {
						fetchBounds : {
							url : "api/maps/fetch",
							request : request,
						},
						fetchAll : {
							url : url,
							request : function (nCurrentTrunc, aLatLonTruncs, aIds) {

								var oRequest;

								if (sensus.widgets.datatable.oTableSettings.id == "group-table") {
									var oSearch = new DeviceSearch({groups : [{id : iGroupId}]})
									oRequest = new InquiryDeviceRequest({deviceSearch : oSearch, geocodeLatLongTruncs : aLatLonTruncs, deviceType : sDeviceType});

									if (sDeviceSubType) {

										oSearch.hanDeviceSearch = {
												hanDeviceTypeEnumList : [sDeviceSubType]
										}
									}
								}

								if (sensus.widgets.datatable.oTableSettings.id == "smartpoint-table") {
									oSearch = sensus.pages.device.smartpointTable.data().config.oSettings.fnRequest();
									oSearch.geocodeLatLongTruncs = aLatLonTruncs;
									var radio = new Radio({flexNetId : aIds});

									if (!$.sc.isNullOrUndefined(oSearch.deviceSearch))
									{
										if (!$.sc.isNullOrUndefined(oSearch.deviceSearch.electricMeterSearch)
												&& !$.sc.isNullOrUndefined(oSearch.deviceSearch.electricMeterSearch.electricMeter))
										{
											oSearch.deviceSearch.electricMeterSearch.electricMeter.radio = radio;
										}

										if (!$.sc.isNullOrUndefined(oSearch.deviceSearch.hanDeviceSearch)
												&& !$.sc.isNullOrUndefined(oSearch.deviceSearch.hanDeviceSearch.hanDevice))
										{
											oSearch.deviceSearch.hanDeviceSearch.hanDevice.radio = radio;
										}

										if (!$.sc.isNullOrUndefined(oSearch.deviceSearch.lcmSearch)
												&& !$.sc.isNullOrUndefined(oSearch.deviceSearch.lcmSearch.lcm))
										{
											oSearch.deviceSearch.lcmSearch.lcm.radio = radio;
										}

										if (!$.sc.isNullOrUndefined(oSearch.deviceSearch.waterMeterSearch)
												&& !$.sc.isNullOrUndefined(oSearch.deviceSearch.waterMeterSearch.waterMeter))
										{
											oSearch.deviceSearch.waterMeterSearch.waterMeter.radio = radio;
										}

										if (!$.sc.isNullOrUndefined(oSearch.deviceSearch.gasMeterSearch)
												&& !$.sc.isNullOrUndefined(oSearch.deviceSearch.gasMeterSearch.gasMeter))
										{
											oSearch.deviceSearch.gasMeterSearch.gasMeter.radio = radio;
										}

									}

									oRequest =  new InquiryDeviceRequest(oSearch);
								}

								if ($.sc.isNullOrUndefined(nCurrentTrunc))
								{
									nCurrentTrunc = 4;
								}
								oRequest.geoCodeTrunc = nCurrentTrunc;
								oRequest.pageSize = 10;
								oRequest.sortExpressions = [{ field:"device_id", direction:"Ascending"}];

								return oRequest;

							}
						}
					},

					functionalities : {

						popup : {
							bActionve : bActionve
						}
					}
				}

				actionDialog.dialog('open');

				$.sc.map.build(mapConfig, 'detail-map-container', objLatLon, 1, sMapSmall);
			}
		}
	};
});
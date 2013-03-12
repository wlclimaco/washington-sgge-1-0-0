sensus.widgets.datatable = {

	sSelText              : '',
	objLatLon             : [],
	isAllRows             : false, // Track if AllRows selection is enabled
	currentRow            : null,  // Contains the row data. Used on Events click of a single row
	selectedRows          : [],    // Track the table rows the user selected across the pages.
	allRowsCount          : {},    // Track the total amount of records for all page selection
	oTableSettings        : {},    // Object used on current table
	deselectedRows        : [],    // Track the all rows the user deselected across the pages. This functionality is diff from selectedRows because it keeps the deselected rows
	isPageSelection       : false, // Is page selection
	iTotalPagination      : 0,     // Total item on page
	iCurrentPagination    : 0,	   // Current Pagination
	selectedDialogRows    : [],
	deselectedDialogRows  : [],

	/**
	 * Model Object for datatable config.
	 * Each new Table will have a new instance from this object.
	 */
	TableSettings : function () {

		this.aData              = [];
		this.aColumns           = [];  // Columns
		this.sAjaxSource        = "";  // Url
		this.dateRefresh        = "";
		this.customColumns      = "";  //Custom Column Page Name
		this.fnRowCallback      = null;
		this.fnDrawCallback     = null;
		this.fnInfoCallback     = null;
		this.fnHeaderCallback   = null;
		this.fnFooterCallback   = null;
		this.fnPreDrawCallback  = null;
		this.oSettings          = { // Settings
			order            : "",
			edit             : null,
			remove           : null,
			process          : null,
			bCheckbox        : false,
			bSelection       : false,
			aLengthMenu      : [],
			iDisplayLength   : 15,
			bFooterVisible   : true,
			bDialogCheckbox  : false,
			smartpointFilter : null
		};

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
	 * Reload table method, getting url data
	 * @param table
	 * 				The table object
	 */
	reloadTable : function(table, iStart) {

		var iLength        = parseInt($.address.parameter("length"),10),
			iDisplayStart  = iStart;

		if (iDisplayStart == undefined) {
			var allRowsCount = sensus.widgets.datatable.allRowsCount;

			if (allRowsCount/iLength === parseInt(allRowsCount/iLength)){
				iDisplayStart =  $.address.parameter("iDisplayStart");
			} else {
				iDisplayStart = 0;
			}
		}

		sensus.widgets.datatable.objLatLon = [];
		$.sc.startProgressBar(null, true);
		sensus.widgets.datatable.clearSelectsFunction.call(table);

		$(".status-viewport-loading").removeClass("hide");

		var dataUrl = "?";

		$.address.parameter("iDisplayStart", 0);
		$.address.parameter("length", 0);

		dataUrl += $.address.queryString();

		table.fnSettings()._iDisplayLength = iLength || parseInt(sensus.settings.PAGE_SIZE, 10);
		table.fnSettings()._iDisplayStart = parseInt(iDisplayStart, 10) || 0;
		table.fnSettings().sAjaxSource = table.data("table").sAjaxSource + dataUrl;

		$('.dataTables_length').find('select option[value='+ table.fnSettings()._iDisplayLength +']')
							   .prop("selected", true);

		table.fnStandingRedraw();
		$("#loading").dialog('close');

	},

	rebuild : function(oTable) {

		$.sc.startProgressBar(null, true);
		$.address.parameter("length", $('.dataTables_length').find('select').val());

		var oConfig = oTable.data("config");

		oTable.fnDestroy();

		oTable.empty();
		oTable.append("<thead><tr></tr></thead><tbody></tbody>");
		oTable.dataTable(sensus.widgets.datatable.setTable(oConfig));
	},

	/**
	 * Save page size in the user profile
	 */
	savePageSize: function() {

		$.sc.savePropertyProfile( { "PAGE_SIZE" : $(".dataTables_length").find("option:selected").val() } );

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
			dataUrl        = "?",
			defaultConfig  = this.getDefaultConfig(),
			iDisplayStart  = $.address.parameter("iDisplayStart"),
			iLength        = $.address.parameter("length"),
			oTable         = $("#" + config.id);

		// Set object 'config' with configurations of the table in the DOM
		oTable.data("config", $.extend({}, config));

		config.fnServerData = function ( sSource, aoData, fnCallback ) {

			// Request AJAX
			sensus.widgets.datatable.fnCreateRequestModel.call(this, sSource, oTable, config, aoData, null, fnCallback);

		};

		config.aColumns = this.addCustomColumns(config, oTable).aColumns;

		//Add dynamically columns to aColumns array
		config.aColumns = this.addColHeader(config);

		//Index columns so that table configuration can be based on column id, not
		//absolute position.
		var oColumnSetup    = this.getColumnSetup(config.aColumns);
		config.aColumns     = oColumnSetup;
		config.aoColumnDefs = oColumnSetup.aoColumnDefs;

		//Inherit tableSettings from config
		oTableSettings = this.extend(config, new this.TableSettings());
		this.oTableSettings = this.extend(config, new this.TableSettings());
		oTable.data("table", oTableSettings);

		//Set Default Pagination
		jQuery.fn.dataTableExt.oPagination.iFullNumbersShowPages = 12;

		$('#bd').on('click', '.sorting, .sorting_desc, .sorting_asc', function(){

			if(config.customColumns != "smartpointlist"){

				var oThat = $(this);
				var oClass = oThat.attr('class');
				var sSort = "asc";
				if(oClass == 'sorting_asc'){

					sSort = "desc";

				}
				var sId = $(this).find('span').attr('id');
				if(sId){

					$.address.parameter('sort',$(this).find('span').attr('id')+'|'+sSort+'|');

				}

			}
		});

		//Set url data

		$.address.parameter("iDisplayStart", 0);
		$.address.parameter("length", 0);
		dataUrl += $.address.queryString();

		if (config.sAjaxSource) {
			config.sAjaxSource = config.sAjaxSource + dataUrl;
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

		if (config.oSettings.iDefaultCol != null) {
			if (config.oSettings.sDefaultSort != null) {
				defaultConfig.aaSorting = [[ config.oSettings.iDefaultCol, config.oSettings.sDefaultSort ]];
			} else {
				defaultConfig.aaSorting = [[ config.oSettings.iDefaultCol, "asc" ]];
			}
		}

		//Set Length
		if (config.oSettings.aLengthMenu) {
			defaultConfig.aLengthMenu = config.oSettings.aLengthMenu;
		}
		if (config.oSettings.iDisplayLength && iLength == undefined) {
			defaultConfig.iDisplayLength = parseInt(config.oSettings.iDisplayLength, 10);
		} else if (iLength != undefined) {
			defaultConfig.iDisplayLength = parseInt(iLength,10);
		} else if (sensus.settings.PAGE_SIZE != undefined) {
			defaultConfig.iDisplayLength = parseInt(sensus.settings.PAGE_SIZE, 10);
		} else {
			defaultConfig.iDisplayLength = 15;
		}

		if (iDisplayStart != undefined) {
			defaultConfig.iDisplayStart = parseInt(iDisplayStart, 10);
		}

		return $.extend(defaultConfig, config);
	},

	pad2 : function(number) {

	     return (number < 10 ? '0' : '') + number;

	},

	fnGetEnumValue : function (sEnum, aKey) {

		return sensus.settings.enums[sEnum][aKey];

	},

	fnGetEnumKey : function (sEnum, sValue) {

		var sKey = '';

		for (var i in sensus.settings.enums[sEnum]) {

			if (sensus.settings.enums[sEnum][i] == sValue) {

				sKey = i;

			}

		}

		return sKey;

	},

	fnExecuteFunctionByName : function (functionName, context, args) {

		var namespaces  = functionName.split("."),
			func        = namespaces.pop();

		for(var i = 0; i < namespaces.length; i++) {

			context = context[namespaces[i]];

		}

		return context[func].call(this, args);

	},

	fnConvertEnum : function (sEnum, aKey) {

		for (var i in aKey) {

			if (aKey.hasOwnProperty(i)) {

				aKey[i] = sensus.settings.enums[sEnum][aKey[i]];

			}

		}

		return aKey;

	},

	fnConvertAdata : function (data, aRowColumn, obj) {

		var aaData = [];

		for (var i in data[obj]) {

			if (data[obj].hasOwnProperty(i)) {

				var aData = [];

				for (var k in aRowColumn) {

					if (!aRowColumn.hasOwnProperty(k)) {
						continue;
					}

					if (aRowColumn[k] === '') {

						aData.push('');
						continue;

					}

					if( aRowColumn[k].match(/fn\(/)){

						var aaChildren = aRowColumn[k].split('[');
						var sFunction  = aaChildren[1].replace(')]', '').replace('fn(','');

						aData.push(sensus.widgets.datatable.fnExecuteFunctionByName(sFunction, window, data[obj][i][aaChildren[0]]));

					} else {

						var aChildren = aRowColumn[k].split('.');

						if (aChildren[1]) {

							if (!data[obj][i][aChildren[0]]) {

								aData.push('');
								continue;

							}

							aData.push(data[obj][i][aChildren[0]][aChildren[1]]);
							continue;

						}

						var aaChildren = aRowColumn[k].split('[');

						if (aaChildren[1]) {

							if (aaChildren[1] == 'object]') {

								aData.push(JSON.stringify(data[obj][i][aaChildren[0]]));

							} else {

								var sChildren  = aaChildren[1].replace(']', '').split('='),
									oChildren  = data[obj][i][aaChildren[0]];

								if(oChildren && oChildren.length){

									var aChildren = $.grep(oChildren, function(e) { return e[sChildren[0]] == sChildren[1]; });
									if(aChildren.length){

										aData.push(aChildren[0].value);

									} else {

										aData.push('');

									}

								} else {

									aData.push('');

								}

							}

							continue;

						}

						// Else just put the value from array in the property
						aData.push(data[obj][i][aChildren[0]]);

					}
				}
				// Add all rows of the table
				aaData.push(aData);

			}

		}

		return aaData;

	},

	fnConvertAdataCustomColumns : function (data, aRowColumn, obj) {

		var aaData = [];

		for (var i in data[obj]) {

			if (data[obj].hasOwnProperty(i)) {

				var aData = [];

				for (var k in aRowColumn) {

					if (!aRowColumn.hasOwnProperty(k) || aRowColumn[k].propertyRequest == null) {
						continue;
					}

					if (aRowColumn[k].propertyRequest === '') {

						aData.push('');
						continue;

					}

					if( aRowColumn[k].propertyRequest.match(/fn\(/)){

						var aaChildren = aRowColumn[k].propertyRequest.split('[');
						//var sFunction  = aaChildren[1].replace(')]', '').replace('fn(','');
						aData.push(sensus.widgets.datatable.fnExecuteFunctionByName(aaChildren[1].replace(')]', '').replace('fn(',''), window, data[obj][i][aaChildren[0]]));
						continue;

					} else {

						var aChildren = aRowColumn[k].propertyRequest.split('.');

						if (aChildren[1]) {

							if (!data[obj][i][aChildren[0]]) {

								aData.push('');
								continue;

							}

							aData.push(data[obj][i][aChildren[0]][aChildren[1]]);
							continue;

						}

						var aaChildren = aRowColumn[k].propertyRequest.split('[');

						if (aaChildren[1]) {

							if (aaChildren[1] == 'object]') {

								aData.push(JSON.stringify(data[obj][i][aaChildren[0]]));
								continue;

							} else {

								var sChildren = aaChildren[1].replace(']', '').split('=');
								var oChildren = data[obj][i][aaChildren[0]];

								if(oChildren && oChildren.length){

									var aChildren = $.grep(oChildren, function(e) { return e[sChildren[0]] == sChildren[1]; });
									if(aChildren.length){

										aData.push(aChildren[0].value);
										continue;

									} else {

										aData.push('');
										continue;

									}

								} else {

									aData.push('');
									continue;

								}

							}

							//continue;

						}

						// Else just put the value from array in the property
						aData.push(data[obj][i][aChildren[0]]);

					}
				}
				// Add all rows of the table
				aaData.push(aData);

			}

		}

		return aaData;

	},


	fnCreateRequestModel : function(sSource, oTable, config, aoData, aoCustomColumns, fnCallback) {

		var oRequest      = new config.oSettings.oRequest(config.oSettings.fnRequest(oTable));
		//var oRequest      = config.oSettings.oRequest;
		//var	sRequestName  = (config.oSettings.oRequest.valueOf().toString().match(/function (.{1,})\(/)[1]).trim();
		var	iStartRow     = ($.grep(aoData, function(e) { return e.name == 'iDisplayStart'; }))[0].value;
		var	iPageSize     = ($.grep(aoData, function(e) { return e.name == 'iDisplayLength'; }))[0].value;
		//var	oNewRequest   = { };

		if (oTable.dataTableSettings[0].aaSorting.length) {

			try {

				var oDataSettings    = $.grep(oTable.dataTableSettings, function(e) { return e.sInstance == oTable.attr('id'); }), //Get Element
					//iSortPosition    = 0,
					sEnumValue       = null,
					iPosColSelected  = oDataSettings[0].aaSorting[0][0];

				// Need count each column, even hidden columns, to now what column do the sort
				// and get the id from DOM.
				$.each($(this).find("th"), function(iKey, oDom) {

					if ($(this).hasClass("hide")) {
						return;
					}

					if ( $(this).hasClass("checkbox") || (config.oSettings.iMapCol && config.oSettings.iMapCol == iKey) ) {
						iPosColSelected = iPosColSelected + 1;
					}

					if (iKey == iPosColSelected) {
						sEnumValue = $(oDom).find("span").attr('id').replace('id=\"','').replace('\"','').replace(/-/g,'_');
						return false;
					}

				});

				// var sEnumValue     = $(this).find("th:eq(" + iSortPosition + ") span").attr('id').replace('id=\"','').replace('\"','').replace(/-/g,'_'),
				var	sDirection     = oDataSettings[0].aaSorting[0][1];
				oRequest.sortExpressions = [{
					'field'      : sEnumValue,
					'direction'  : sDirection.substr(0, 1).toUpperCase() + sDirection.substr(1)+'ending'
				}];

			} catch(e) {

			}

		}

		if (!oRequest.action){
			oRequest.action = 'table';
		}
		oRequest.startRow          = iStartRow;
		oRequest.pageSize          = iPageSize;
		oRequest.endRow            = 0;
		//oNewRequest[sRequestName]  = oRequest;
		var json = null;
		$.ajax({
			dataType    : 'json',
			type        : "POST",
			url         : sSource,
			data        : $.toJSON(oRequest),
			async       : false,
			contentType : "application/json; charset=utf-8",
			success     : function(json) {

				oTable.dateRefresh = json.responseTime;

				//Add Last Refresh
				if (oTable.dateRefresh != undefined) {

					var sDate = $.sc.dateFormat(oTable.dateRefresh, sensus.settings.DATE_FORMAT + ' - h:i:s A');

					$(".stamp").text($.sc.locale('table.refreshed', [sDate + " " + " " + sensus.settings.timezoneShort ]))
							   .wrapInner("<em/>");
				}

				if (aoCustomColumns) {

					json.aaData = sensus.widgets.datatable.fnConvertAdataCustomColumns(json, aoCustomColumns.columns, config.oSettings.sResponseObj);

				} else {

					json.aaData = sensus.widgets.datatable.fnConvertAdata(json, config.oSettings.aColumns, config.oSettings.sResponseObj);

				}


				if (json.resultsSetInfo) {

					json.iTotalDisplayRecords = json.resultsSetInfo.totalRowsAvailable;

				}

				fnCallback(json);

			}
		});

		oTable = null;
	},

	addCustomColumns : function(oTableSettings, oTable) {

		// If not was set "customColumns" on init of the page
		if (!oTableSettings.customColumns) {
			return oTableSettings;
		}

		var oThis 				 = this,
			oTr 				 = oTable.find("thead").find("tr"),
			columns 			 = oTableSettings.aColumns,
			iIndexCol 			 = -1,
			bAddDefaultHeader 	 = true,
			oCustomDataResponse  = null,
			oCustomData 		 = null,
			aCustomDataColumn 	 = [],
			aColumnIds 			 = [],
			sColumnClass 		 = "",
			sTr 				 = "";

		if (oTableSettings.customColumns == "analyticslist") {

			oCustomDataResponse  = $.wCustomize && $.wCustomize.getData(oTableSettings.customColumns, "", oTableSettings.aColumns);

		} else {

			oCustomDataResponse  = $.wCustomize && $.wCustomize.getData(oTableSettings.customColumns, 'Columns');

		}

		if ( oCustomDataResponse && oCustomDataResponse.listColumn) {

			oCustomData = this.fnFormatColumnResponse(oCustomDataResponse, oTable).listColumn;
			oTableSettings.oSettings.iMapCol = undefined;

		}

		$.each(columns, function(iColumns) {

			$.each(oCustomData, function(iCustomIndex) {

				// At the first iteration, add TH at the header of the table and push
				// data to the new array. The new array is created in order to create
				// a new reference to the object
				if (bAddDefaultHeader) {

					if (oCustomData[iCustomIndex].sId == "ENCRYPTION_STATUS") {
						sColumnClass = "no-encryption";

					} else if (oCustomData[iCustomIndex].sId == "LIFECYCLE_STATE") {
						sColumnClass = "active";
						oCustomData[iCustomIndex].bEffectSortable = true;

					} else if (oCustomData[iCustomIndex].sId == "LATITUDE") {
						oCustomData[iCustomIndex].bEffectSortable = true;

					} else if (oCustomData[iCustomIndex].sId == "LONGITUDE") {
						oCustomData[iCustomIndex].bEffectSortable = true;
					}

					if (oCustomData[iCustomIndex].sId == 'MAP_IT') {

						// Add column Map it for fix error of SORT
						if (oTableSettings.oSettings.iMapCol) {

							var oMapIt = {sId : 'MAP_IT', sWidth : "5%", sName : $.sc.locale("filter.device.mapit.label")};
							oCustomData.splice(parseInt(oTableSettings.oSettings.iMapCol, 10) - 1, 0, oMapIt);

						}

						oTableSettings.oSettings.iMapCol = iCustomIndex + 1;

					} else {
						sTr += oThis.addDefaultHeader(
								oCustomData[iCustomIndex].sName,
								true,
								sColumnClass,
								oCustomData[iCustomIndex].sId.toLowerCase()
						);
						aColumnIds.push(oCustomData[iCustomIndex].sId);

						// To get information from init page and merge with data from database
						for (var iKey in columns) {
							if (columns[iKey].sId == oCustomData[iCustomIndex].sId) {

								aCustomDataColumn.push($.extend(oCustomData[iCustomIndex], columns[iKey]));
								break;
							}
						}

					}
				}

				// Get the index of the column that match with object from init page
				if (columns[iColumns] && (columns[iColumns].sId == oCustomData[iCustomIndex].sId)) {
					iIndexCol = iColumns;
					return bAddDefaultHeader;
				}

				sColumnClass = "";
			});

			bAddDefaultHeader = false;

			// If columns doesn't have hide columns came from init object, push
			// to the new array

			if (iIndexCol === -1
					&& columns[iColumns]
					&& !columns[iColumns].bVisible
					&& columns[iColumns].bFixed) {

				aCustomDataColumn.push(columns[iColumns]);
				sTr += oThis.addDefaultHeader("", false);
			}

			iIndexCol = -1;
		});

		oTr.append(sTr);
		columns = aCustomDataColumn;
		oTableSettings.aColumns = columns;

		oTableSettings.fnServerData = function ( sSource, aoData, fnCallback ) {

			// Request AJAX
			sensus.widgets.datatable.fnCreateRequestModel.call(this, sSource, oTable, oTableSettings, aoData, oTableSettings.aColumns, fnCallback);

		};

		return oTableSettings;
	},

	fnFormatColumnResponse : function(e, oTable) {

		var listColumn  = [],
			oColumn     = {
				sId                : "",
				sName              : "",
				bEffectSortable    : "",
				propertyRequest    : "",
				propertyOrderEnum  : ""
			};

		e.aColumnsIds = [];

		$.each(e.listColumn, function(iColumnIndex) {

			var oCurrentColumn = $.extend({}, oColumn);

			oCurrentColumn.sId             = e.listColumn[iColumnIndex].columnEnum;
			oCurrentColumn.bEffectSortable = e.listColumn[iColumnIndex].ordered;
			oCurrentColumn.sName           = e.listColumn[iColumnIndex].fieldName;

			e.listColumn[iColumnIndex] = oCurrentColumn;

			e.aColumnsIds.push(oCurrentColumn.sId);
		});

		oTable.data("columnsResponse", e);

		return e;
	},


	/**
	 * Add dynamically columns
	 * @param config
	 * 				Object came from init pages
	 */
	addColHeader : function(config) {
		var settings = config.oSettings,
			columns = config.aColumns;
		//Add columns to new params
		if (settings.bCheckbox || settings.bDialogCheckbox) {
			columns.splice(0,0, {sId: "Check", sWidth : "1%", bSortable : true, bCustomColumn : true});
		}
		if (settings.remove || settings.process) {
			columns.splice(columns.length,0, {sId: "Blank", sWidth : "1%", bSortable : true, bCustomColumn : true});
		}
		if (settings.iMapCol) {
			columns.splice(settings.iMapCol,0, {sId: "Map", sWidth : "1%", bSortable : true, bCustomColumn : true});
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
			sDom            : 'tr<"yui-gb table-footer"<"yui-u paging first"p><"yui-u numbers"il>><"stamp">',
			bAutoWidth      : false,
			aLengthMenu     : [15, 25, 50, 100],
			bProcessing     : true,
			bServerSide     : true,
			asStripClasses  : [ "", "alt" ],
			iDisplayLength  : 15,
			sPaginationType : "full_numbers",
			oLanguage : {
				sInfo          : $.sc.locale('table.records.info'),
				sInfoEmpty     : $.sc.locale('table.records.infoempty'),
				sProcessing    : $.sc.locale('table.processing'),
				sLengthMenu    : $.sc.locale('table.records.page'),
				sZeroRecords   : $.sc.locale('table.records.none'),
				sInfoFiltered  : $.sc.locale('table.records.filter'),
				oPaginate      : {
					sFirst    : "",
					sLast     : "",
					sNext     : $.sc.locale('table.page.next') + " &gt;&gt;",
					sPrevious : "&lt;&lt; " + $.sc.locale('table.page.prev')
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

		var visibleCounter = 0,
			iCustomColumns = 0;

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

			if (column.bCustomColumn) {
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

		var oTh                   = oTable.find("th"),
			fnCleanSortableClass  = null,
			oTableDataColumn      = oTable.data("table").aColumns,
			sSortOptions          = '<div class="ui-dialog sort-options" style="display: none"><ul><li><a href="" class="asc"><span class="icon-small icn-sort-asc"></span> Sort Ascending</a></li><li><a href="" class="desc"><span class="icon-small icn-sort-desc"></span> Sort Descending</a></li><li><a href="" class="custom-column-action"><span class="icon-small icn-col-select"></span> Columns</a></li></ul></div>';

		fnCleanSortableClass = function() {

			$('.sorting_desc', oTable).removeClass("sorting_desc");
			$('.sorting_asc', oTable).removeClass("sorting_asc");

		};

		var MakeSort = function(sDirection, iColumnIndex) {

			var LightOrderByEnum = sensus.settings.enums.LightOrderByEnum;

			var key = $.map(LightOrderByEnum, function(e, i) { if(e === oTableDataColumn.columns[iColumnIndex].sId) { return i }; })[0];

			$.address.parameter('sort', key + '|' + sDirection+'|');

		};

		$.each(oTableDataColumn.columns, function(iColumnIndex) {

			if (oTableDataColumn.columns[iColumnIndex].bEffectSortable) {

				var oSortOptions  = $(sSortOptions),
					iColIndex     = oTableDataColumn.colIndexAll[oTableDataColumn.columns[iColumnIndex].sId],
					iVisibleCol   = oTableDataColumn.colIndexVisible[oTableDataColumn.columns[iColumnIndex].sId],
					oCol          = $(oTh).eq(iColIndex);

				oCol.hover(
					function () {
						$('div', this).stop(true, true)
									  .delay(200)
									  .slideDown(200);
					},
					function () {
						$('div', this).stop(true, true)
									  .slideUp(200);
					}
				);

				if (!oCol.find(".sort-options").length) {
					oCol.removeClass("sorting_disabled").append(oSortOptions);
				}

				$('.asc', oSortOptions).click(function(e) {
					e.preventDefault();

					fnCleanSortableClass();

					MakeSort('asc', iColumnIndex);

					oTable.fnSort( [ [iVisibleCol,'asc'] ] );

					oCol.removeClass("sorting_disabled").addClass("sorting_asc");

				});

				$('.desc', oSortOptions).click(function(e) {
					e.preventDefault();

					fnCleanSortableClass();

					MakeSort('desc', iColumnIndex);

					oTable.fnSort( [ [iVisibleCol,'desc'] ] );

					oCol.removeClass("sorting_disabled, sorting_asc").addClass("sorting_desc");

				});

				$(oSortOptions).find('.custom-column-action').click(function(e) {
					e.preventDefault();

					// Model default of settings
					var oCustomerSettings = {
						sTypeOfElement     : 'Columns', //sType
						sCurrentPage       : "smartpointlist", // sPage
						oCurrentFilters    : oTable.data("columnsResponse") // objFilters
					};

					$('#customize-filter').wCustomize(oCustomerSettings);

				});

			}

		});

	},

	addDefaultHeader : function(sName, bVisible, sClass, sColumnId, sId) {

		var sDisplay = "style='display: none'",
			oThs     = "";

		if (bVisible) {

			sDisplay = "";

		}

		if (!sColumnId) {
			sColumnId = "";
		}

		if (sColumnId == 'eco_mode') {
			sColumnId = 'ecomode';
		}

		if (!sName || sName == "" || sName == "null") {

			sName = $.sc.locale("smartpoint.table.header." + sColumnId.replace(/\_/g, ""));

		}

		oThs = '<th id="' + sColumnId + '" class="sorting" rowspan="1" colspan="1" ' + sDisplay + '><span class="'+ sClass +'">' + sName + '</span></th>';

		if (sClass === 'unlocked') {
			oThs = '<th id="' + sColumnId + '" class="sorting" rowspan="1" colspan="1" ' + sDisplay + '><span><a class="'+ sClass +'">' + sName + '</a></span></th>';
		} else if (sClass === 'active') {
			oThs = '<th id="' + sColumnId + '" class="sorting ' + sClass + ' sorting_asc" rowspan="1" colspan="1" ' + sDisplay + '><span><a">' + sName + '</a></span></th>';
		} else if (sName === "State") {
			oThs = '<th id="' + sColumnId + '" class="active sorting" rowspan="1" colspan="1" ' + sDisplay + '><span class="'+ sClass +'">' + sName + '</span></th>';
		}

		return oThs;
	},

	addHeader : function(oTable) {

		var datatable         = oTable.data("table"),
			$headRows         = oTable.children("thead").children("tr"),
			iHeadRows         = $headRows.length,
			iSecondRowLength  = $($headRows).last().find("th").length;

		//Add dynamically th. Check if the th already exists

		if (iHeadRows > 1) {

			if (datatable.aColumns.columns.length != oTable.find("th").length - (iSecondRowLength - 1)) {
				//Add checkbox
				if (datatable.oSettings.bCheckbox || datatable.oSettings.bDialogCheckbox) {
					$('<th class="checkbox" rowspan='+iSecondRowLength+'><input type="checkbox" id="select-page" name="select-page"></input></th>').insertBefore(oTable.find("th:eq(0)"));
				}
				//Add remove or process
				if (datatable.oSettings.remove || datatable.oSettings.process) {
					$($headRows[0]).append('<th rowspan='+ iSecondRowLength +'>&nbsp;</th>');
				}
				//Add map
				if (datatable.oSettings.iMapCol) {
					$("<th rowspan=" + iSecondRowLength +">" + $.sc.locale("dashboard.page.mapIt") + "</th>").insertBefore(oTable.find("thead tr")
																												  .find("th:eq(" + datatable.oSettings.iMapCol + ")"));
				}
			}

		} else if (datatable.aColumns.columns.length != oTable.children("thead").find("th").length) {

			//Add checkbox
			if (datatable.oSettings.bCheckbox || datatable.oSettings.bDialogCheckbox) {
				$('<th class="checkbox"><input type="checkbox" id="select-page" name="select-page"></input></th>').insertBefore(oTable.find("th:eq(0)"));
			}
			//Add remove or process
			if (datatable.oSettings.remove || datatable.oSettings.process) {
				$($headRows[0]).append('<th>&nbsp;</th>');
			}
			//Add map
			if (datatable.oSettings.iMapCol) {
				$("<th>" + $.sc.locale("dashboard.page.mapIt") + "</th>").insertBefore(oTable.find("thead tr")
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
			iActive        = datatable.oSettings.iActiveCol,
			input          = null,
			tdCheck        = null,
			remove         = null,
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
		abort = $('<td><a class="delete abort remove_dialog" href="#">'+ $.sc.locale("commons.pages.abort")+'</a></td>').click(function(e) {
			e.preventDefault();

			sensus.widgets.datatable.currentRow = aData;

			sensus.widgets.datatable.abortDialog(datatable);
		});


		//Summary
		summary = $('<td><a class="button small" href="#">'+ $.sc.locale("table.summary")+'</a></td>').click(function(e) {
			e.preventDefault();

			$.sc.startProgressBar();

			sensus.widgets.datatable.currentRow = aData;

			// Call Widget Summary
			$("#action-dialog").summary(sensus.widgets.datatable.currentRow[datatable.aColumns.colIndexAll.Id], 3);
			sensus.widgets.datatable.summaryDialog(datatable);

			$.sc.stopProgressBar();
		});

		//Cancel
		cancel = $('<td><a class="delete abort remove_dialog" href="#">'+ $.sc.locale("commons.pages.cancel") +'</a></td>').click(function(e) {
			e.preventDefault();

			sensus.widgets.datatable.currentRow = aData;

			sensus.widgets.datatable.cancelDialog(datatable);
		});

		//Remove
		remove = $('<td><a id="delete_' + iDisplayIndex + '" class="delete delete_dialog delete_col" href="#">'+ $.sc.locale("table.action.delete") +'</a></td>').click(function(e) {

			e.preventDefault();

			sensus.widgets.datatable.currentRow = aData;

			sensus.widgets.datatable.removeDialog(datatable);

		});

		//Add Active Col
		if (iActive != null) {

			$("td:eq(" + iActive + ")", nRow).addClass("active");
			$("th:eq(" + iActive + ")", $(this)).addClass("active");

		}

		if (datatable.oSettings.process && datatable.oSettings.process.oAction) {

			oAction = $('<td class="button-action-expand"><a class="button" role="button"><span class="left">Actions</span><span class="ui-icon ui-icon-triangle-1-s left"></span></a><div id="listMenu" class="ui-dialog view-options hide"></div></td>')
			.hover(
					function () { $('div', this).stop(true, true).delay(200).slideDown(200); },
					function () { $('div', this).stop(true, true).slideUp(200); }
			);

			oActionUl = $('<ul/>');

			// button [EDIT]
			if (datatable.oSettings.process.oAction.edit) {

				var url = datatable.oSettings.process.oAction.edit.url.split('?');

				if (url.length > 1) {
					oActionEdit  = $('<li><a id="menu-bt-edit" href="'
											+ datatable.oSettings.process.oAction.edit.url
											+'&id='+ aData[datatable.aColumns.colIndexAll.Id]
											+ '"><span class="icon-small icn-edit"></span> '
											+ $.sc.locale("commons.pages.edit")
											+'</a></li>').click(function(e) {
								e.preventDefault();
								sensus.util.session.removeSessionCustomize("util/removeSessionCustomize.action", "sessionCustomizeType=SelectedFiltersSchedule");
								sensus.util.session.createSession("util/createSessionCustomize.action", "sessionCustomizeType=SelectedFiltersSchedule&sessionCustomize=" + $.address.queryString().split("&").join("&sessionCustomize="));
					});
				} else {
					oActionEdit  = $('<li><a id="menu-bt-edit" href="'
							+ datatable.oSettings.process.oAction.edit.url +'?id='+ aData[datatable.aColumns.colIndexAll.Id]
							+ '"><span class="icon-small icn-edit"></span> '+ $.sc.locale("commons.pages.edit") +'</a></li>').click(function(e) {e.preventDefault()
								sensus.util.session.removeSessionCustomize("util/removeSessionCustomize.action", "sessionCustomizeType=SelectedFiltersSchedule");
								sensus.util.session.createSession("util/createSessionCustomize.action", "sessionCustomizeType=SelectedFiltersSchedule&sessionCustomize=" + $.address.queryString().split("&").join("&sessionCustomize="));
							});
				}

				oActionEdit.click(function() {
					var urlParam = {
							sUrl : $('a', oActionEdit).attr("href"),
							$container : $('#load'),
							$container_tabs : $('#tabs-content'),
							$element : $(this),
							bTab : true
						};

					sensus.commons.lib.ajax.loadTab($.extend({}, sensus.commons.lib.ajax.param, urlParam));
				});

				oActionUl.append(oActionEdit);
			}
			// button [CLONE]
			if (datatable.oSettings.process.oAction.clone) {

				oActionClone = $('<li><a id="menu-bt-clone" href="'
						+ datatable.oSettings.process.oAction.clone.url
						+'?id='+ aData[datatable.aColumns.colIndexAll.Id]
						+ '&type=clone"><span class="icon-small icn-clone"></span> '
						+ $.sc.locale("commons.pages.clone")
						+'</a></li>').click(function(e) {e.preventDefault();});

				oActionClone.click(function() {
					var urlParam = {
							sUrl             : $('a', oActionClone).attr('href'),
							$container       : $('#load'),
							$container_tabs  : $('#tabs-content'),
							$element         : $(this),
							bTab             : true
						};

					sensus.commons.lib.ajax.loadTab($.extend({}, sensus.commons.lib.ajax.param, urlParam));
				});
				oActionUl.append(oActionClone);
			}
			// button [PAUSE]
			if (datatable.oSettings.process.oAction.pause && aData[datatable.aColumns.colIndexAll.status] == $.sc.locale("table.type.scheduled")) {

				oActionPause = $('<li><a id="menu-bt-play" href=""><span class="icon-small icn-pause"></span> '+ $.sc.locale("commons.pages.pause") +'</a></li>').click(function(e) {
					e.preventDefault();

					$.ajax({
						url : datatable.oSettings.process.oAction.pause.url,
						async : false,
						data : "id=" + aData[datatable.aColumns.colIndexAll.Id],
						success : function(data) {

							var success = settings.success(aData, sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll);

							if (data.result == sensus.constants.ajax.ok) {
								$.sc.showMessage("messaging-main", success + $.sc.messageList(data.messages), "confirm");
							} else {
								$.sc.showMessage("messaging-main", $.sc.messageList(data.messages), "error");
							}
						},
						error: function(e) {

							if (e.result == "FAIL") {
								$.sc.showMessage("messaging-main", $.sc.locale("commons.pages.error"), "error");
							}

						}
					});

				});

				oActionUl.append(oActionPause);
			}
			// button [RESUME]
			if (datatable.oSettings.process.oAction.resume && aData[datatable.aColumns.colIndexAll.status] == $.sc.locale("table.type.paused")) {

				oActionResume = $('<li><a id="menu-bt-play" href=""><span class="icon-small icn-play"></span> '+ $.sc.locale("commons.pages.resume") +'</a></li>').click(function(e) {
					e.preventDefault();

					$.ajax({
						url : datatable.oSettings.process.oAction.resume.url,
						async : false,
						data : "id=" + aData[datatable.aColumns.colIndexAll.Id],
						success : function(data) {

							var success = settings.success(aData, sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll);

							if (data.result == sensus.constants.ajax.ok) {
								$.sc.showMessage("messaging-main", success + $.sc.messageList(data.messages), "confirm");
							} else {
								$.sc.showMessage("messaging-main", $.sc.messageList(data.messages), "error");
							}
						},
						error: function(e) {

							if (e.result == "FAIL") {
								$.sc.showMessage("messaging-main", $.sc.locale("commons.pages.error"), "error");
							}

						}
					});

				});

				oActionUl.append(oActionResume);
			}
			// button [Cancel]
			if (datatable.oSettings.process.oAction.cancel) {

				oActionCancel = $('<li><a id="menu-bt-delete" title="'+ datatable.oSettings.process.oAction.cancel.text(aData, iDisplayIndex) +'" class="delete-dialog last" href=""><span class="icon-small icn-delete"></span> '+ $.sc.locale("commons.pages.cancel") +'</a></li>').click(function(e) {
					e.preventDefault();

					sensus.widgets.datatable.currentRow = aData;
					sensus.widgets.datatable.cancelDialog(datatable);
				});
				oActionUl.append(oActionCancel);
			}
			// button [DELETE]
			if (datatable.oSettings.process.oAction.deleteBt) {

				oActionDelete = $('<li><a id="menu-bt-delete" title="'+ datatable.oSettings.process.oAction.deleteBt.text(aData, iDisplayIndex) +'" class="delete-dialog last" href=""><span class="icon-small icn-delete"></span> '+ $.sc.locale("commons.pages.delete") +'</a></li>').click(function(e) {
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

			$("<td class='map-col'><a id='map_" + iDisplayIndex + "' href='#'>"+ $.sc.locale("dashboard.page.map") +"</a></td>").insertBefore($('td:eq(' + mapCol + ')', nRow)).click(
					function(evt, ui) {
						$.sc.startProgressBar();
						evt.preventDefault();
						sensus.widgets.datatable.mapDialog(datatable, aData);
				});
		}

		//Remove Coulumns
		if (datatable.oSettings.remove) {

			$(nRow).append(remove);

		} else if (datatable.oSettings.process) {
			if (aData[datatable.aColumns.colIndexAll.status] == false) {
				if (datatable.oSettings.process.abort) {
					$(nRow).append(abort);
				}else {
					$(nRow).append("<td/>");
				}
				$('td:eq(' + datatable.aColumns.colIndexAll.status + ')', nRow).addClass('processing').html("<span>"+ aData[datatable.aColumns.colIndexAll.status] +"</span>");

			} else if (aData[datatable.aColumns.colIndexAll.status] == $.sc.locale("table.type.scheduled")) {

				if (datatable.oSettings.process.oAction) {
					$(nRow).append(oAction);
				} else {
					$(nRow).append(cancel);
				}

			} else if (aData[datatable.aColumns.colIndexAll.status] == true
							|| aData[datatable.aColumns.colIndexAll.status] == $.sc.locale("table.type.error")
							|| aData[datatable.aColumns.colIndexAll.status] == $.sc.locale("commons.pages.aborted")
							|| aData[datatable.aColumns.colIndexAll.status] == $.sc.locale("table.type.cancelled")) {

				$('td:eq(' + datatable.aColumns.colIndexAll.status + ')', nRow).html("<span>"+ $.sc.locale("process.page."+aData[datatable.aColumns.colIndexAll.status]) +"</span>");
				if (datatable.oSettings.process.remove) {
					$(nRow).append(remove);
				} else {
					var sSize = datatable.oSettings.process.sSmartpointSize;
					if (aData[datatable.aColumns.colIndexAll[sSize]] > 0) {
						$(nRow).append(summary);
					} else {
						$(nRow).append("<td/>");
					}
				};
			} else if (aData[datatable.aColumns.colIndexAll.status] == "") {
				$(nRow).append("<td/>");
			};

			if (datatable.oSettings.process.oAction && aData[datatable.aColumns.colIndexAll.Locked] === $.sc.locale("table.locked.true")) {
				$('td:eq('+ datatable.aColumns.colIndexAll.Locked +')', nRow).html("<span class='locked'></span>");
				$(nRow).append("<td></td>");
			} else if (datatable.oSettings.process.oAction && aData[datatable.aColumns.colIndexAll.Locked] === $.sc.locale("table.locked.false")) {
				$('td:eq('+ datatable.aColumns.colIndexAll.Locked +')', nRow).html("<span class='unlocked'></span>");
				$(nRow).append(oAction);
			}

			if (datatable.oSettings.process && datatable.oSettings.process.oAction && datatable.oSettings.process.oAction.allRow == true) {
				$(nRow).append(oAction);
			}
		}



		//Smartpoint Link
		if (datatable.oSettings.smartpointFilter) {

			var aSmartpointFilterCols = datatable.oSettings.smartpointFilter.aCols,
				iSmartpointColsLentgh = aSmartpointFilterCols.length,
				iSmartpointColsIStart = 0;

			for (; iSmartpointColsIStart < iSmartpointColsLentgh; iSmartpointColsIStart += 1 ) {

				if (aSmartpointFilterCols[0] == 'smartpoint') {

					$('td:eq('+ datatable.aColumns.colIndexAll[aSmartpointFilterCols[iSmartpointColsIStart]] +')', nRow).html("<a class='alist' href='smartpoint/ajax.list.action?"+ datatable.oSettings.smartpointFilter.filter +"="+ aData[datatable.aColumns.colIndexAll.Id] +"|'>"+ aData[datatable.aColumns.colIndexAll.smartpoint] +"</a>").click(function(e)  {
						$.address.parameter(datatable.oSettings.smartpointFilter.filter, aData[datatable.aColumns.colIndexAll.Id]);
					});

				} else {

					$('td:eq('+ datatable.aColumns.colIndexAll[aSmartpointFilterCols[iSmartpointColsIStart]] +')', nRow)
								.html("<a class='alist' href='smartpoint/ajax.list.action?"+ datatable.oSettings.smartpointFilter.filter +"="+ aData[datatable.aColumns.colIndexAll.Id] +"|'>"+ aData[datatable.aColumns.colIndexVisible[aSmartpointFilterCols[iSmartpointColsIStart]]] +"</a>").click(function(e)  {

						$.address.parameter(datatable.oSettings.smartpointFilter.filter, aData[datatable.aColumns.colIndexAll.Id]);

					});
				}

			}

		}

		// datatable.oSettings.edit
		if (edit) {

			editCol = edit.col;

			var oEdit = $('<a class="alist" href="'+ edit.url +'?id='+ aData[datatable.aColumns.colIndexAll['Id']] +'"><strong>' + aData[datatable.aColumns.colIndexAll[editCol]] + '</strong></a>');

			oEdit.click(function(e) {
				e.preventDefault();

				/**
				 * When you go to light detail has to save filtes
				 * to put back when you click
				 * on button "back to list"
				 *
				 **/
				if (editCol == "Pole_Id") {

					// Namespace do sabe filter
					sensus.util.sFilters = $.address.queryString();

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
		if (aSelected) {

			var $checkCount = $("#checked-count");
			if (datatable.oSettings.bDialogCheckbox != undefined) {
				$checkCount = $("#checked-count-dialog");
			}

			if (sensus.widgets.datatable.isAllRows && datatable.oSettings.bDialogCheckbox == undefined) {
				input.prop("checked", true);
				for(deselected in aDeselected) {
				    if (aDeselected.hasOwnProperty(deselected)) {
						if (input.attr("value") == aDeselected[deselected]) {
				        	input.prop("checked", false);
				        	$(this).find("thead input:checkbox").prop("checked", false);
							break;
				        }
				    }
			    }
			} else {
				$checkCount.html($().numberFormat(aSelected.length));
				//check pre selected checkboxes
				for(preChecked in aSelected) {
				     if (aSelected.hasOwnProperty(preChecked)) {
						if (input.attr("value") == aSelected[preChecked]) {
				        	input.prop("checked", true);
				        }
				     }
			    }
			}
		}

		if (datatable.fnRowCallback) {
			datatable.fnRowCallback.call($(this), nRow, aData, iDisplayIndex, datatable.aColumns.colIndexAll);
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
		 }else {
			$next.show();
		}

		//Set current pagination
		sensus.widgets.datatable.iCurrentPagination = current,

		//Set Total items
		sensus.widgets.datatable.iTotalPagination = totalPagination;

		if (datatable.oSettings.bSelection) {
			sensus.widgets.datatable.allRowsCount = iTotal;
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

		var datatable    = $(this).data("table"),
			iDataLength  = parseInt($(".dataTables_length").find("option:selected").val(), 10);

		//Open dialog page size
		$(".dataTables_length").find("select").change(function() {

			if (sensus.settings.PAGE_SIZE_SHOW_DIALOG == "3") {
				sensus.widgets.datatable.pageSizeDialog(datatable);
			}

		});

		if (!$("#action-dialog-lrp").find(this).length) {
			$.address.parameter("iDisplayStart", parseInt(iStart, 10));
			$.address.parameter("length", iDataLength);
		}

		//Functionality to check checkbox
		if (datatable.oSettings.bCheckbox) {
			sensus.widgets.datatable.selectFunctionality.call($(this));
		}else if (datatable.oSettings.bDialogCheckbox) {
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
			$('.table-footer').show();
		} else {
			$(this).hide();
			$('.table-footer').hide();
			$(this).parent().siblings(".blankslate").show();
		}

		if (datatable.fnFooterCallback) {
			datatable.fnFooterCallback.call($(this), nRow, aaData, iStart, iEnd, aiDisplay, iRecordsDisplay, datatable.aColumns.colIndexAll);
		}

		$.sc.stopProgressBar(iDataLength, true);

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

		var datatable             = $(this).data("table"),
			$table                = $(this),
			$selectPage           = $('#select-page'),
			$tableFooter          = $(".table-footer"),
			$previous             = $(".previous", $tableFooter),
			iCurrent              = sensus.widgets.datatable.iCurrentPagination,
			iTotalPagination      = sensus.widgets.datatable.iTotalPagination,
			$paginationContainer  = $previous.next(),
			$paginationLast       = $("span:last", $paginationContainer),
			$paginationFirst      = $("span:first", $paginationContainer);
			$oPaging              = $('.paging', $table.siblings('.table-footer'));

		if (iTotalPagination === 1) {

			$oPaging.hide();

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

		if ($table.find(":checked").length > ($table.closest('table').find('tbody').find('tr').length - 1)) {

			$table.find($selectPage).prop('checked', true);

		} else {

			$table.find($selectPage).prop('checked', false);

		}
		$('.button').button();

		//Order by column
		if (datatable.oSettings.order) {

			var date  = null,
				$tr   = $(this).find("tbody tr");

			$.each($tr, function(i, e) {

				var order            = $(e).data("order"),
					orderComparable  = "";

				if (datatable.oSettings.orderType === 'date') {
					orderComparable = $.sc.dateFormat(order) || order;
				} else {
					orderComparable = order;
				}

				if (!date) {
					date = orderComparable;
					$("<tr class='row-header'><td colspan='"+ ((datatable.aColumns.columns.length) + 1) +"'><h4>"+ order +"</h4></td></tr>").insertBefore(e);
				} else if (orderComparable != date) {
					date = orderComparable;
					$("<tr class='row-header'><td colspan='"+ ((datatable.aColumns.columns.length) + 1) +"'><h4>"+ order +"</h4></td></tr>").insertBefore(e);
				}

			});
		}

		//Hide columns that contains bVisible false
		var aVisible = datatable.aColumns.aVisible;

		for (var i in aVisible) {
			if (aVisible.hasOwnProperty(i)) {

				if (!aVisible[i]) {
					$.each($(this).find("tr"), function(iTr, e) {

						var oHideTd = $(e).find("td:eq("+ i +")");

						if (oHideTd.parent('tr').hasClass('row-header') != true) {

							$(e).find("td:eq("+ i +")").addClass("hide");

						}

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

		$('html, body').animate({ scrollTop: 0 }, 'slow', function() {

			$('#action-dialog').dialog( "option", "position", "center" );

		});


	},

	fnPreDrawCallback : function(oSettings ) {
		var datatable             = $(this).data("table");

		$.sc.startProgressBar(null, true);

		var oSelect = $(this).parent().find('select');

		oSelect.bind("change", function() {
		    $.address.parameter("iDisplayStart", 0);
		    oSettings._iDisplayStart = 0;
		});

		var events = oSelect.data("events");

		if (events != undefined) {
			events.change.reverse();
		}

		if (datatable.fnPreDrawCallback) {
			datatable.fnPreDrawCallback.call($(this), oSettings);
		}

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

		var selected    = bDialog ? sensus.widgets.datatable.selectedDialogRows : sensus.widgets.datatable.selectedRows,
			deselected  = bDialog ? sensus.widgets.datatable.deselectedDialogRows : sensus.widgets.datatable.deselectedRows;

		//If deselectedRows contains current checkbox, remove it
		if (jQuery.inArray(val, deselected) != -1) {
			deselected.splice(jQuery.inArray(val, deselected), 1);
			sensus.widgets.datatable.sSelText = '';
		}

		//Add current checkbox to selectedRoes
		if ( val.match('^(0|[1-9][0-9]*)$') ) {
			if (jQuery.inArray(val, selected) == -1) {
				selected.push(val);
				sensus.widgets.datatable.sSelText = $('input[value="'+val+'"]','.table-selection').parent().siblings().filter(':visible').eq(0).text();
			}
		}
	},

	addDeselected : function(val, bDialog) {

		var selected    = bDialog ? sensus.widgets.datatable.selectedDialogRows : sensus.widgets.datatable.selectedRows,
			deselected  = bDialog ? sensus.widgets.datatable.deselectedDialogRows : sensus.widgets.datatable.deselectedRows;

		//If selectedRows contains current checkbox, remove it
		if (jQuery.inArray(val, selected) != -1) {
			selected.splice(jQuery.inArray(val, selected), 1);
		}

		//Add current checkbox to deselectedRows
		if ( val.match('^(0|[1-9][0-9]*)$') ) {
			if (jQuery.inArray(val, deselected) == -1) {
				deselected.push(val);
			}
		}
	},

	setTotalResult : function(bDialog) {

		var selected        = bDialog ? sensus.widgets.datatable.selectedDialogRows : sensus.widgets.datatable.selectedRows,
			deselected      = bDialog ? sensus.widgets.datatable.deselectedDialogRows : sensus.widgets.datatable.deselectedRows;
			$checkCount     = null,
			iSelectedLights = sensus.widgets.datatable.allRowsCount;

		if (bDialog) {

			$checkCount     = $("#checked-count-dialog");
			iSelectedLights = $('#process-table tbody input').length;

		} else {

			$checkCount = $("#checked-count");

		}

		if (sensus.widgets.datatable.isAllRows == true) {

			$checkCount.empty().html($().numberFormat(iSelectedLights - deselected.length));

		} else {

			$checkCount.empty().html($().numberFormat(selected.length));

		}

	},

	/**
	 * TODO - Throw this function on a separate file
	 * Function to check all checkbox when clickin on checkbox of the head
	 */
	selectCheckbox : function($dialog) {

		var	bHasDialog = $dialog.length,
			$table     = bHasDialog ? $dialog.find(this) : $(this),
			$checkPage = bHasDialog ? $dialog.find('#select-page') : $('#select-page'),
			$actions   = $("#actions"),
			$tbody     = $table.find("tbody");

		$tbody.on("click", "input:checkbox", function(event) {

			if (!$(this).is(':checked')) {

				$checkPage.prop('checked', false);
				sensus.widgets.datatable.addDeselected($(this).attr("value"), bHasDialog);

			} else {

				if ($('tbody', $table).find(":checked").length >= ($tbody.find('tr').length)) {
					$checkPage.prop('checked', true);
				} else {
					$checkPage.prop('checked', false);
				}

				sensus.widgets.datatable.addSelected($(this).attr("value"), bHasDialog);
				$actions.find('.message').removeClass("ui-state-error");
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

		var bHasDialog  = $dialog.length,
			$table      = bHasDialog ? $dialog.find(this) : $(this),
			$actions    = $("#actions"),
			$thead      = $table.find("thead");

		$thead.on("click", "input:checkbox", function(e) {
			//Cheking all checkbox
			if ($(this).is(':checked')) {

				//Check all checkbox from page and remove it from deselectedRows
				$table.find('tbody input:checkbox').prop('checked', true).each(function(i) {
					sensus.widgets.datatable.addSelected($(this).attr("value"), bHasDialog);
				});

			} else {

				$table.find('tbody input:checkbox').prop('checked', false).each(function(i) {
					sensus.widgets.datatable.addDeselected($(this).attr("value"), bHasDialog);
				});
			}

			sensus.widgets.datatable.setTotalResult(bHasDialog);

			//minus the top header checkbox
			$actions.find('.message').removeClass("ui-state-error").addClass("ui-state-highlight");

		});
	},


	/**
	 * TODO - Throw this function on a separate file
	 * Event to check all checkbox when clickin on checkbox of the head
	 */
	selectionButtonPage : function() {

		var $table = $(this);
		var $actions = $("#actions");

		$(".result-count").on("click", ".select-page", function(e) {

			e.preventDefault();

			if (sensus.widgets.datatable.isAllRows) {
				sensus.widgets.datatable.clearSelectsFunction.call($(this));
				sensus.widgets.datatable.isAllRows = false;
			}

			$table.find(':checkbox').attr('checked', true);

			//get the checked items from page
			$table.find(":checked").each(function(i) {
				sensus.widgets.datatable.addSelected($(this).attr("value"));
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

		$(".result-count").on("click", ".select-all", function(e) {
			e.preventDefault();

			sensus.widgets.datatable.clearSelectsFunction.call($table);

			$table.find('input:checkbox').prop('checked', true);

			//Enable allRows selection for server command to be sent
			sensus.widgets.datatable.isAllRows = true;

			$table.find(":checked").each(function(i) {
				sensus.widgets.datatable.addSelected($(this).attr("value"));
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

		$(".result-count").on("click", ".select-none", function(e) {
			e.preventDefault();
			sensus.widgets.datatable.clearSelectsFunction.call($table);
		});

	},


	/**
	 * TODO - Throw this function on a separate file
	 * Functionality to clear checkbox
	 */
	clearSelectsFunction : function() {

		var oDialog  = $("#action-dialog-lrp:visible"),
			bDialog  = oDialog.length,
			$table   = $(this);

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
	 * @param datatable
	 * 					The table
	 */
	removeDialog : function(datatable) {
		this.oTableSettings = datatable;
		$.sc.launchActionDialog("remove", this.dialogSettings["remove"]);
	},

	/**
	 * Functionality to call dialogs
	 * @param datatable
	 * 					The table
	 */
	summaryDialog : function(datatable) {
		this.oTableSettings = datatable;
		$.sc.launchActionDialog("summary", this.dialogSettings["summary"]);
	},

	/**
	 * Functionality to call dialogs
	 * @param datatable
	 * 					The table
	 */
	abortDialog : function(datatable) {
		this.oTableSettings = datatable;
		$.sc.launchActionDialog("abort", this.dialogSettings["abort"]);
	},

	/**
	 * Functionality to call dialogs
	 * @param datatable
	 * 					The table
	 */
	cancelDialog : function(datatable) {

		this.oTableSettings = datatable;

		if (datatable.oSettings.process.oAction) {

			this.oTableSettings.oSettings.process.cancel =  datatable.oSettings.process.oAction.cancel;
			$.sc.launchActionDialog("cancel", this.dialogSettings["cancel"]);

		} else {
			$.sc.launchActionDialog("cancel", this.dialogSettings["cancel"]);
		}
	},


	/**
	 * Functionality to call dialogs to Delete
	 * @param datatable
	 * 					The table
	 */
	deleteDialog : function(datatable) {

		this.oTableSettings = datatable;

		if (datatable.oSettings.process.oAction) {
				$.sc.launchActionDialog("deleteGroups", this.dialogSettings["deleteGroups"]);
		}
	},

	mapDialog : function(datatable, aData) {
		this.oTableSettings = datatable;
		this.currentRow = aData;
		$.sc.launchActionDialog("mapIt", this.dialogSettings["mapIt"]);
	},

	pageSizeDialog: function(datatable) {
		this.oTableSettings = datatable;
		$.sc.launchActionDialog("pageSize", this.dialogSettings["pageSize"]);
	}
};


/**
 * TODO - Throw this object on a separate file.
 * Dialog Settings Objects. Hols all dialogs settings objects
 *
 */
sensus.widgets.datatable.dialogSettings = {

		remove : {
			title                : $.sc.locale("commons.pages.deleteConfirm"),
			width                : 300,
			requiresSmartpoints  : false,
			buttons              : (function() {

				var buttons = {};
				buttons[$.sc.locale("commons.pages.delete")] = function() {

					var settings      = sensus.widgets.datatable.oTableSettings.oSettings.remove || sensus.widgets.datatable.oTableSettings.oSettings.process.remove,
						sRequestName  = settings.oRequest.trim(),
						oNewRequest   = { }; // TODO check new solution to this code.

					oNewRequest = settings.fnRequest(sensus.widgets.datatable.currentRow,  sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll);

					$.ajax({
						url          : settings.url,
						type         : "POST",
						dataType     : 'json',
						contentType  : "application/json; charset=utf-8",
						async        : false,
						data         : $.toJSON(oNewRequest),
						success      : function(data) {

							var success = settings.success(sensus.widgets.datatable.currentRow, sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll);

							// Seccess
							if (data.operationSuccess) {

								$.sc.showMessage("messaging-main", success + $.sc.messageList(data.messages), "confirm");
								return;

							}

							// Error
							if (data.messageList && data.messageList.length) {

								$.sc.showMessage("messaging-main", data.messageList[0].text , "error"); // $.sc.messageList(data.messages)
								return;

							}

							$.sc.showMessage("messaging-main", $.sc.locale("commons.pages.error"), "error");

						},
						error: function(e) {
							if (e.result == "FAIL") {
								$.sc.showMessage("messaging-main", $.sc.locale("commons.pages.error"), "error");
							}
						}
					});
					$("#action-dialog").dialog('close');
				};
				buttons[$.sc.locale("commons.pages.cancel")] = function() {
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
				$('#action-dialog').load('thirdparty/pages/datatable_remove.jsp', function() {
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
		title : $.sc.locale("action.abortprocess.title"),

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
			buttons[$.sc.locale("commons.pages.abort")] = function() {

				var settings      = sensus.widgets.datatable.oTableSettings.oSettings.abort || sensus.widgets.datatable.oTableSettings.oSettings.process.abort;
				var sRequestName  = settings.oRequest; //settings.oRequest.valueOf().toString().match(/function (.{1,})\(/)[1];
				var oNewRequest   = { }; // TODO check new solution to this code.

				oNewRequest = settings.fnRequest(sensus.widgets.datatable.currentRow,  sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll);

				$.ajax({
					url : sensus.widgets.datatable.oTableSettings.oSettings.process.abort.url,
					type         : "POST",
					dataType     : 'json',
					contentType  : "application/json; charset=utf-8",
					async        : false,
					data         : $.toJSON(oNewRequest),
					success      : function(data) {

						var success = sensus.widgets.datatable.oTableSettings.oSettings.process.abort.success(sensus.widgets.datatable.currentRow, sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll);

						if (data.operationSuccess) {
							$.sc.showMessage("messaging-main", success + $.sc.messageList(data.messages), "confirm");
						} else {

							// Error
							if (data.messageList && data.messageList.length) {

								$.sc.showMessage("messaging-main", data.messageList[0].text , "error"); // $.sc.messageList(data.messages)
								return;

							}

							$.sc.showMessage("messaging-main", $.sc.messageList(data.messages), "error");

						}
					},
					error: function(e) {
						if (e.result == "FAIL") {
							$.sc.showMessage("messaging-main", $.sc.locale("commons.pages.error"), "error");
						}
					}
				});
				$("#action-dialog").dialog('close');
			};
			buttons[$.sc.locale("commons.pages.cancel")] = function() {
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
			$('#action-dialog').load('thirdparty/pages/datatable_remove.jsp', function() {
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
		title : $.sc.locale("commons.pages.deleteConfirm"),

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
			buttons[$.sc.locale("action.cancel.submit")] = function() {

				$.ajax({
					url      : sensus.widgets.datatable.oTableSettings.oSettings.process.cancel.url,
					async    : false,
					data     : "id=" + sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.Id],
					success  : function(data) {

						var success = sensus.widgets.datatable.oTableSettings.oSettings.process.cancel.success(sensus.widgets.datatable.currentRow, sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll);

						if (data.result == sensus.constants.ajax.ok) {
							$.sc.showMessage("messaging-main", $.sc.messageList(data.messages), "confirm");
						} else {

							$.sc.showMessage("messaging-main", success, "error");

						}
					},
					error: function(e) {
						if (e.result == "FAIL") {
							$.sc.showMessage("messaging-main", $.sc.locale("commons.pages.error"), "error");
						}
					}
				});
				$("#action-dialog").dialog('close');
			};
			buttons[$.sc.locale("commons.pages.cancel")] = function() {
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

			$actionDialog.load('thirdparty/pages/datatable_remove.jsp', function() {
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
	deleteGroups:{

		/** * The dialog title. */
		title : $.sc.locale("commons.pages.deleteConfirm"),

		/**  * The dialog width. */
		width : 300,

		/** * The dialog buttons (submit and cancel).*/
		buttons : (function() {
			var buttons = {},
				SmartPointCount;

			var actionDialog = $("#action-dialog");

			buttons[$.sc.locale("commons.pages.delete")] = function() {

				$(this).dialog('close');


				$.ajax({
					url      : sensus.widgets.datatable.oTableSettings.oSettings.process.oAction.deleteBt.url,
					type     : 'POST',
					data     :  "id=" + sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.Id],
					success  : function(data) {

						sensus.widgets.datatable.clearSelectsFunction.call(sensus.pages.systemintelligenceaction.actionTable);
						sensus.widgets.datatable.reloadTable(sensus.pages.systemintelligenceaction.actionTable);

						if (data.result === sensus.constants.ajax.ok) {
							$.sc.showMessage("messaging-main", $.sc.locale("systemintelligence.page.action.deleteSuccess"), "confirm");
						} else {
							$.sc.showMessage("messaging-main", $.sc.locale(data.messages), "error");
						}
					}
				});

				actionDialog.dialog('close');
			};
			buttons[$.sc.locale("commons.pages.cancel")] = function() {
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
		title : $.sc.locale("action.processingsummary.title"),

		width : 950,

		minheight: 550,
		/**
		 * Whether this dialog requires a smartpoint list.
		 */
		requiresSmartpoints : false,

		/**
		 * The function that will be called when the action dialog is launched.
		 *
		 * @param actionDialog
		 *            a reference to the actionDialog objext
		 */
		open : function() {
			$.sc.stopProgressBar(0);
		},
		action : function(actionDialog) {

			var $actionDialog = $("#action-dialog");

			//$actionDialog.summary(sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.Id], 3);

			$actionDialog.dialog('open');
		}
	},

	mapIt : {

		width : 790,

		minheight: 400,
		/**
		 * Whether this dialog requires a smartpoint list.
		 */
		requiresSmartpoints : false,

		open : function() {

			$.sc.stopProgressBar(0,false);
			sensus.widgets.datatable.dialogSettings.mapIt.clickEvent.iClick = 0;
			$(document).bind("click", sensus.widgets.datatable.dialogSettings.mapIt.clickEvent);

		},

		close: function() {

			$(document).unbind('click', sensus.widgets.datatable.dialogSettings.mapIt.clickEvent);
			$.sc.stopProgressBar(0,false);

		},

		/**
		 * The function that will be called when the action dialog is launched.
		 *
		 * @param actionDialog
		 *            a reference to the actionDialog objext
		 */

		action : function(actionDialog) {

			sensus.widgets.datatable.objLatLon.splice(0, sensus.widgets.datatable.objLatLon.length);
			$.sc.stopProgressBar(0,false);
			$("#action-dialog").empty();
			$("#action-dialog").append($("<div>").attr("id", "detail-map-total"));
			$("#action-dialog").append($("<div>").attr("id", "detail-map-container").css("minHeight", sensus.widgets.datatable.dialogSettings.mapIt.minheight + "px"));
			$('#detail-map-container').width('760px');
			$('#detail-map-container').height('350px');

			var dialogTitle = '';

			if (sensus.widgets.datatable.oTableSettings.id == "group-table") {
				dialogTitle = sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.groupName] + ' - ' + sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.groupSmartPointCount] + ' '+$.sc.locale('commons.pages.smartPoints');
			}

			var sName, iGroupId, iScheduleId, iUserId, aGroupIds;

			if (sensus.widgets.datatable.oTableSettings.id == "group-table") {
				dialogTitle = sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.groupName] + ' - ' + sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.groupSmartPointCount] + ' '+$.sc.locale('commons.pages.smartPoints');
				iGroupId = sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.Id];
				sName = sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.groupName];
				sensus.widgets.datatable.objLatLon.push({
					latitude:sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.groupLatitude],
					longitude:sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.groupLongitude]
				});
			} else if (sensus.widgets.datatable.oTableSettings.id == "schedule-table") {
				dialogTitle = sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.scheduleName] + ' - ' + sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.scheduleSmartPointCount] + ' '+$.sc.locale('commons.pages.smartPoints');
				iScheduleId = sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.Id];
				sName = sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.scheduleName];
				sensus.widgets.datatable.objLatLon.push({
					latitude:sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.centerLatitude],
					longitude:sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.centerLongitude]
				});
			} else if (sensus.widgets.datatable.oTableSettings.id == "user-table") {
					//TODO ###############################
					//iUserId = sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.Id];
					dialogTitle = sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.id] + ' - ' + sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.userSmartPointCount] + ' '+$.sc.locale('commons.pages.smartPoints');
					var aGroups = $.parseJSON(sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.userGroups]);
					aGroupIds = [];
					for (var i=0; i<aGroups.length; i++){

						aGroupIds.push({id : aGroups[i].id});

					}

					sName = sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.userName];
					sensus.widgets.datatable.objLatLon.push({
						latitude:sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.userLatitude],
						longitude:sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.userLongitude]
					});
			} else if (sensus.widgets.datatable.oTableSettings.id == "user-create") {

				var aGroupIds = [];
				$(".chzn-choices .search-choice a").each(function(){
					aGroupIds.push({id : $(this).attr("id")});
				});

				sName = 'User';
				sensus.widgets.datatable.objLatLon.push({
					latitude:sensus.pages.user.nLat,
					longitude:sensus.pages.user.nLng
				});
			} else {

			//try{

				//var sStatus = sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.STATUS].split('|')[1];

			//} catch(e){

				var sStatus = sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.STATUS];

			//}

				var objLatLon = [];
				objLatLon.push({
					latitude:sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.LATITUDE],
					longitude:sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.LONGITUDE],
					status:sStatus,
					id:sensus.widgets.datatable.currentRow[sensus.widgets.datatable.oTableSettings.aColumns.colIndexAll.Id]
				});

			}

			$("#action-dialog").dialog( { title: dialogTitle });
			//$.sc.maps('detail-map-container', sensus.widgets.datatable.objLatLon, iGroupId, sName, iScheduleId, iUserId, aGroupIds);
			$.sc.maps('detail-map-container', objLatLon, null, null, "small", null);

			$('html, body').animate({ scrollTop: 0 }, 'fast', function(){

				actionDialog.dialog('open');

			});
		},

		clickEvent : function(event, ui) {
			if (!$(event.target).is("#action-dialog") && $(event.target).closest("#action-dialog").length == 0 && sensus.widgets.datatable.dialogSettings.mapIt.clickEvent.iClick) {
				$("#action-dialog").dialog('close');
			}
			sensus.widgets.datatable.dialogSettings.mapIt.clickEvent.iClick += 1;
		}
	} ,

	pageSize : {

		/**
		 * The dialog title.
		 */
		title : $.sc.locale("action.pagesize.title"),
		/**
		 * Whether this dialog requires a smartpoint list.
		 */
		requiresSmartpoints : true,

		/**
		 * The dialog buttons (submit and cancel).
		 */
		buttons : [
           {
				id     : "page-size-submit",
				text   : $.sc.locale("action.pagesize.submit"),
				click  : function() {

					var nPageSize = $(".dataTables_length").find("option:selected").val();
					if (($("#page-size-checkbox").attr("checked") == "checked") || (sensus.settings.PAGE_SIZE_SHOW_DIALOG == "1")) {

						var aSettingsList = [];

						sensus.settings.PAGE_SIZE_SHOW_DIALOG = "1";

						aSettingsList.push({ propertyEnum : "PAGE_SIZE",             propertyValue : nPageSize });
						aSettingsList.push({ propertyEnum : "PAGE_SIZE_SHOW_DIALOG", propertyValue : sensus.settings.PAGE_SIZE_SHOW_DIALOG });

						sensus.util.ajaxaction.actionUrlAdress = sensus.settings.saveProfileSettings;
						sensus.util.ajaxaction.data = { 'settingsRequest' : new settingsRequest(aSettingsList) };
						sensus.util.ajaxaction.sendActionDefault($.sc.locale("action.savesettings.success"), $.sc.locale("action.longRunningProcessDialog.error"));

					}

					sensus.widgets.datatable.savePageSize();
					$(this).dialog('close');

				}
			},
			{
				id     : "page-size-cancel",
				text   : $.sc.locale("action.pagesize.cancel"),
				click  : function() {

					if ($("#page-size-checkbox").attr("checked") == "checked") {

						var aSettingsList = [];

						sensus.settings.PAGE_SIZE_SHOW_DIALOG = "2";
						sensus.util.page.fnSavePropertyProfile( { "PAGE_SIZE_SHOW_DIALOG" : sensus.settings.PAGE_SIZE_SHOW_DIALOG } );

					}

					$(this).dialog('close');

				}
		}],

		/**
		 * The name of the form to clear when launching the dialog.
		 */
		form : 'page_size_form',

		/**
		 * The function that will be called when the action dialog is launched.
		 *
		 * @param actionDialog
		 *            a reference to the actionDialog objext
		 */
		action : function(actionDialog) {

			$('#action-dialog').load('pageSize', function() {
				actionDialog.removeClass('waiting');
				$("#dialogValuePageSize").text($(".dataTables_length").find("select option:selected").val());
			});
			actionDialog.addClass('waiting');
			actionDialog.dialog('open');
		}
	}

};
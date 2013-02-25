/**
 * @description Customize columns of the table
 * @version     -
 * @file        widget_customize.js
 * @author      Miqueias de Oliveira Gomes (miqueias_gomes@qat.com)
 *
 * @copyright Copyright 2012 QAT Global, all rights reserved.
 *
 */

(function($) {

	/**
	 * @returns  object: oResponse
	 * @param    sType string
	 *
	 * If has datas on session return this datas from session or null if not have
	 */
	var fnGetDataFromSession = function(sType) {

		// Check if there is session
		var oListColumn = sensus.util.session.readSession(sType);
		// if not has session finalize here and return list saved on database
		if (oListColumn == undefined || oListColumn == null) {
			return null;
		}

		return oListColumn;
	};

	var fnColumnModel = function(sColumnEnum, sFieldName, sOrdered) {
		return {
			columnEnum : sColumnEnum,
			fieldName  : sFieldName,
			ordered    : sOrdered
		};
	};

	var fnAnalyticsCustomize = function(aColumns) {

	aListColumn = [];
	
	for (var i=0; i<aColumns.length; i++) {
		
		var sColumn = aColumns[i].sId.toLowerCase();
		
		if (aColumns[i].sId.toLowerCase() == 'analytics_group_total') {
			sColumn = aColumns[i].sId.toLowerCase() + $.address.parameter('ty');
		} 
		
		aListColumn.push(fnColumnModel(aColumns[i].sId, sensus.locale.get("analytics.table."+sColumn), null) );
	
	}

	return { listColumn : aListColumn };

	};


	/**
	 * @param sPage
	 * 			string, page
	 * @param sType
	 * 			string, customization type filter/column
	 *
	 * Get data from database
	 *
	 */
	var getData = function (sPage, sType, aColumns) {

		// Put String on the upperCase standard
		sType = sType.toUpperCase();

		// Check if has page
		if (!sPage) {
			return false;
		}

		if (sPage.toLowerCase() == "analyticslist") {

			return fnAnalyticsCustomize(aColumns);

		}

		var oColumnFilter = fnGetDataFromSession(sType);
		// check if has data saved on session
		if (oColumnFilter) {
		
			//return additional column if customized
			if (oColumnFilter.additionalColumns) {
				return oColumnFilter;
			}
		
			if(oColumnFilter.listUrl){
				
				$.address.queryString(oColumnFilter.listUrl);
			
			}
		
		}

		// Object request to send
		var columnFilterRequest = {
			'columnFilterRequest' : {
				listTypeEnum : 'SMARTPOINTLIST',
				userContext : new UserContext()
			}
		};
		// Send request to get columns or filters
		$.ajax({
			url          : 'util/fetchCustomize.action',
			type         : "POST",
			dataType     : 'json',
			contentType  : "application/json; charset=utf-8",
			async        : false,
			data         : $.toJSON(columnFilterRequest),
			success      : function(oData) {

				if (oData && !oData.operationSuccess) {

					// array with messages
					oData.messageList; // TODO make functionality to show error messages

					return false;
				}

				oColumnFilter = oData;
			}
		});
				
		if (sensus.settings.userContext.tenant.ecoModeDisable) {
			for (u in oColumnFilter.filters) {
				if (oColumnFilter.filters[u].filterEnum == "ECOMODE") {
					oColumnFilter.filters.splice(u, 1);
				}
			}
		}
		
		if (sensus.settings.userContext.tenant.ecoModeDisable) {
			for (u in oColumnFilter.listColumn) {
				if (oColumnFilter.listColumn[u].columnEnum == "ECOMODE") {
					oColumnFilter.listColumn.splice(u, 1);
				}
			}
		}

		return oColumnFilter;
	};


	/**
	 * @returns  void
	 * @param    sType string
	 *
	 * Create a HTML of dialog
	 */
	var fnCreateHTML = function(sType) {

		// Append to LOAD dialog content
		$('#custom-filters').remove();

		if (!$('#custom-filters').length) {

			$('<!-- Start Dialog: Custom Columns and Filters --><div id="custom-filters" class="action-dialog"><h2>'
					+ sensus.locale.get('widgets.customize.currently') +' ' + sensus.locale.get('widgets.customize.' + sType) + '</h2><fieldset>'
					+ '<ul id="checkbox-list-active" class="checkbox-list-active sortable connected-sortable"></ul><ul style="float:left"><li id="last-active" class="error hide">'
					+ sensus.locale.get('widgets.customize.error') + '</li></ul></fieldset><fieldset class="clear"><legend>'
					+ sensus.locale.get('widgets.customize.additional') + ' '
					+ sensus.locale.get('widgets.customize.' + sType) + '</legend><ul id="checkbox-list" class="checkbox-list sortable connected-sortable"></ul></fieldset><div class="highlight"><small><input id="set-default" type="checkbox"> '
					+ sensus.locale.get('widgets.customize.checkbox.' + sType)
					+ '</small></div></div><!-- End Dialog: Custom Columns and Filters -->').appendTo('#action-dialog');

		}

	};


	/**
	 * @returns  void
	 * @param    oSettings object
	 *
	 * Get Active columns selected for user and get additional columns
	 * and put in the object oSettings.oColumnFilterModel.
	 */
	var fnGetColumnsFromScreen = function(oSettings) {

		oSettings.oColumnFilterModel.listTypeEnum = 'SMARTPOINTLIST';

		// ACTIVE COLUMN
		$('#checkbox-list-active li').each(function(i) {

			var oLiColumn = {
				columnEnum : $(this).attr('id').toUpperCase(),
				fieldName  : $(this).text().trim(),
				ordered    : null
			};

			oSettings.oColumnFilterModel.listColumn.push(oLiColumn);

		});

		// ADDITIONAL COLUMN
		$('#checkbox-list li').each(function(i) {

			var oLiAdditionalColumn = {
				columnEnum : $(this).attr('id').toUpperCase(),
				fieldName  : $(this).text().trim(),
				ordered    : null
			};

			oSettings.oColumnFilterModel.additionalColumns.push(oLiAdditionalColumn);

		});

	};

	/**
	 * @returns  void
	 * @param    oSettings object
	 *
	 * Get Active columns selected for user and get additional columns
	 * and put in the object oSettings.oColumnFilterModel.
	 */
	var fnGetFiltersFromScreen = function(oSettings) {

		oSettings.oColumnFilterModel.listTypeEnum = 'SMARTPOINTLIST';

		// ACTIVE FILTER
		$('#checkbox-list-active li').each(function(i) {

			var oLiFilter = {
				filterEnum    : $(this).attr('id').toUpperCase(),
				displayOrder  : null
			};

			oSettings.oColumnFilterModel.filters.push(oLiFilter);

		});

		// ADDITIONAL FILTER
		$('#checkbox-list li').each(function(i) {

			var oLiAdditionalFilter = {
				filterEnum    : $(this).attr('id').toUpperCase(),
				displayOrder  : null
			};

			oSettings.oColumnFilterModel.additionalFilters.push(oLiAdditionalFilter);

		});

	};


	/**
	 * @returns  void
	 * @param    oSettings object
	 *
	 * Set Active columns and Set additional columns
	 */
	var fnSetColumnsOnTheScreen = function(oSettings) {

		// Active Columns
		for ( var iKey in oSettings.aDataOfTheElement.listColumn) {

			if (oSettings.aDataOfTheElement.listColumn.hasOwnProperty(iKey)) {

				var sEnum = oSettings.aDataOfTheElement.listColumn[iKey].columnEnum.toLowerCase();
				var sLabel = sensus.locale.get("smartpoint.table.header." + sEnum.replace(/\_/g, ""));
				var sClassDisableDrag = 'ui-state-default';

				// For fixing the columns pole id and rni id
				if (sEnum == 'pole_id' || sEnum == 'rni_id' || sEnum == 'status') {

					sClassDisableDrag = 'ui-state-default ui-draggable ui-draggable-disabled ui-state-disabled';

					$('<li id="' + sEnum + '" class="' + sClassDisableDrag + '"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span> ' + sLabel + '</li>').draggable({
					   start: function(event, ui) {
							return false;
						}
					}).appendTo('#checkbox-list-active');

					continue;
				}

				$('<li id="' + sEnum + '" class="' + sClassDisableDrag + '"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span> ' + sLabel + '</li>').appendTo('#checkbox-list-active');

			}

		}

		// Additional Columns
		for ( var iKey in oSettings.aDataOfTheElement.additionalColumns) {

			if (oSettings.aDataOfTheElement.additionalColumns.hasOwnProperty(iKey)) {

				var sEnum  = oSettings.aDataOfTheElement.additionalColumns[iKey].columnEnum.toLowerCase(),
					sLabel = sensus.locale.get("smartpoint.table.header." + sEnum.replace(/\_/g, ""));

				$('<li id="' + sEnum + '" class="ui-state-highlight"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span> ' + sLabel + '</li>').appendTo('#checkbox-list');

			}

		}

	};

	/**
	 * @returns  void
	 * @param    oSettings object
	 *
	 * Set Active filters and Set additional filters
	 */
	var fnSetFiltersOnTheScreen = function(oSettings) {

		var activeFilters = oSettings.aDataOfTheElement.filters;

		// Active FILTERS
		for ( var iKey in activeFilters) {

			if (activeFilters.hasOwnProperty(iKey)) {

				var sEnum              = activeFilters[iKey].filterEnum.toLowerCase(),
					sLabel             = sensus.locale.get("widgets.customize.filter." + sEnum.replace(/\_/g, "")),
					sClassDisableDrag  = 'ui-state-default';

				$('<li id="' + sEnum + '" class="' + sClassDisableDrag + '">' + '<span class="ui-icon ui-icon-arrowthick-2-n-s"></span> '
							 + sLabel + '</li>').appendTo('#checkbox-list-active');

			}

		}

		var additionalFilters = oSettings.aDataOfTheElement.additionalFilters;

		// Additional FILTERS
		for ( var iKey in additionalFilters) {

			if (additionalFilters.hasOwnProperty(iKey)) {

				var sEnum   = additionalFilters[iKey].filterEnum.toLowerCase(),
					sLabel  = sensus.locale.get("widgets.customize.filter." + sEnum.replace(/\_/g, ""));

				$('<li id="' + sEnum + '" class="ui-state-highlight"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span> '
							 + sLabel + '</li>').appendTo('#checkbox-list');

			}

		}

	};


	/**
	 * @returns  void
	 * @param    -
	 *
	 * Rebuild the table with customization Or Rebuild the filters.
	 */
	var fnReloadTable = function(oSettings) {

		if (oSettings.sTypeOfElement.toLowerCase() == "filters") {

			// Array to send to the plugin widget filter
			var aActive   = [],
				oFilters  = oSettings.oColumnFilterModel.filters;

			for(var oFilter in oFilters) {
				if (oFilters.hasOwnProperty(oFilter)) {

					aActive.push(oFilters[oFilter].filterEnum);

				}
			}

			// Add a property that create a button "Custom Filter"
			aActive.push('CUSTOM_FILTERS');

			$('.filter-container li:not(:last)').remove();
			$("#w-filters").wFilters(aActive, sensus.pages.smartpoint.smartpointTable, oSettings.fnCallBack);
			sensus.util.page.showMessage("messaging-main", "Filters Updated Successfully" ,"confirm");

		} else if (oSettings.sTypeOfElement.toLowerCase() == "columns") {

			sensus.widgets.datatable.rebuild(sensus.pages.smartpoint.smartpointTable);
			sensus.util.page.showMessage("messaging-main", sensus.locale.get("widgets.customize.successUpdateColumns"), "confirm");

		}

	};


	/**
	 * @returns  void
	 * @param    oCustomerSettings
	 * 			     object
	 *
	 * <i>wCustomize</i> object is a global variable.
	 * <i>jQuery.fn.wCustomize</i> through which it may be  accessed.
	 *
	 * @example
	 *      // Initialization with configuration options
	 *      $(document).ready(function {
	 *         $('#customize-column').wCustomize( {
	 *        	   sTypeOfElement : 'Columns',
	 *			   sCurrentPage   : 'smartpointlist',
	 *         } );
	 *      });
	 *
	 */
	$.fn.wCustomize = function(oCustomerSettings) {

        // Reference the current instance of the plugin
		var oCustomize = this;

		// Constructor method called when the object is created
        oCustomize.init = function() {

            // Merged with settings default provided
			oCustomize.oSettings = $.extend({}, oSettings, oCustomerSettings);

			// Get all data from Database or Session
			oCustomize.oSettings.aDataOfTheElement = getData(oCustomize.oSettings.sCurrentPage, oCustomize.oSettings.sTypeOfElement);

			// Create HTML of the dialog
			fnCreateHTML(oCustomize.oSettings.sTypeOfElement.toLowerCase());

        };

		// Default Model of Settings
		var oSettings = {
			fnCallBack          : null,
			sCurrentPage        : "",
			sDialogTitle        : "",
			sTypeOfElement      : "",
			oCurrentFilters     : null,
			aDataOfTheElement   : [],
			oColumnFilterModel  :  { // Model equal 'ColumnFilterResponse' of the java
				listColumn         : [],
				additionalColumns  : [],
				filters            : [],
				additionalFilters  : [],
				listTypeEnum       : null,
				userContext        : new UserContext()
			}
		};

		// Call the Constructor
		oCustomize.init();

		// Default Model of Dialog
		var oDialogSettings = {
			modal       : true,
			width       : 730,
			title       : sensus.locale.get('widgets.customize.customize.' + oCustomize.oSettings.sTypeOfElement.toLowerCase()),
			autoOpen    : false,
			minheight   : 150,
			resizable   : false,
			dialogClass : 'action-dialog',
			buttons     : [
				{ // BUTTON CONFIRM CUSTOMIZE
					id     : "submit-set-customize",
					text   : sensus.locale.get("widgets.customize.set." + oCustomize.oSettings.sTypeOfElement.toLowerCase()),
					click  : function() {

						if (oCustomize.oSettings.sTypeOfElement.toLowerCase() == "columns") {

							// Get the COLUMNS Active and Additional and set on 'oCustomerSettings.oSettings'
							fnGetColumnsFromScreen(oCustomize.oSettings);

						} else if (oCustomize.oSettings.sTypeOfElement.toLowerCase() == "filters") {

							// Get the FILTERS Active and Additional and set on 'oCustomerSettings.oSettings'
							$('.clear','.filter-container').click();
							fnGetFiltersFromScreen(oCustomize.oSettings);

						}

						// If true is need save on the database
						var bCheckBoxSetDefault = $('#set-default').is(":checked");

						if (bCheckBoxSetDefault) {

							$.ajax({
								type         : "POST",
								url          : "smartpoint/ajax.insertColumnFilter.action",
								dataType     : 'json',
								data         : $.toJSON({'columnFilterRequest' : oCustomize.oSettings.oColumnFilterModel}),
								contentType  : "application/json; charset=utf-8",
								success      : function(oResponse) {

									if (oResponse && !oResponse.operationSuccess) {
										sensus.util.page.showMessage("messaging-main", sensus.locale.get('widgets.customize.errorRequest'), "error");
									}

								}
							
							});

						}

						// Send object to back-end to create session
						sensus.util.session.createSession(oCustomize.oSettings.sTypeOfElement, oCustomize.oSettings.oColumnFilterModel);

						// Rebuild Columns
						fnReloadTable(oCustomize.oSettings);

						// Close and Empty Dialog
						$(this).dialog('close');
					}
				},
				{ // BUTTON CANCEL
					id     : "page-size-cancel",
					text   : "Cancel",
					click  : function() {$(this).dialog('close'); }

				}
			],
			close : function() { $(this).empty(); },
			open  : function() {

				if (oCustomize.oSettings.sTypeOfElement.toLowerCase() == 'columns') {

					// Function to populate the dialog with columns saved on session or on database.
					fnSetColumnsOnTheScreen(oCustomize.oSettings);

				} else if (oCustomize.oSettings.sTypeOfElement.toLowerCase() == 'filters') {

					// This function is to populate the dialog with filters saved on session or on database.
					fnSetFiltersOnTheScreen(oCustomize.oSettings);

				}

			}
		};

		// Fill the dialog
		$actionCustomFilters = $("#custom-filters").dialog(oDialogSettings);

		// INIT sortable
		$( "#checkbox-list-active, #checkbox-list" ).sortable({

			cancel       : ".no-sortable",
			connectWith  : ".connected-sortable",
			receive      : function (event, ui) {

				if ($('#checkbox-list-active li').length < 1) {

					$('#checkbox-list-active').sortable('cancel');
					$('#last-active').show();

				} else {

					$('#last-active').hide();

				}

			}

		}).disableSelection();

		// Call API jQuery ui dialog
		$actionCustomFilters.dialog('open');

	};


	/**
	 * @returns  void
	 * @param    oCustomerSettings
	 * 			     object
	 *
	 * <i>wCustomize</i> object is a global variable.
	 * <i>jQuery.wCustomize</i> through which it may be accessed
	 * to get data without others functionalities.
	 *
	 */
	$.wCustomize = (function() {
		return  {
			getData : getData
		};
	})();


})(jQuery);
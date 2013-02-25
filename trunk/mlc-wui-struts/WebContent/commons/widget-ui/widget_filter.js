/*
 *  Widget Filter JavaScript
 *
 *  Copyright 2011, QAT Brazil.
 *
 *  ! Plugin definition
 *
 *  	The filters can be of the following types:
 *
 * 			- action_type
 *          - action_type_all
 *  		- address           			#
 * 			- alarm_type					#
 *			- BULB_SERIAL_NUMBER			+
 *			- COLOR_TEMPERATURE				+
 *          - configuration     			#
 *          - connection_status
 *          - custom_filters
 *			- DATE_ADDED					+
 * 			- device_type
 * 			- ECO_MODE						+
 *          - encryption_status
 *          - event_schedule    			#
 * 			- event_type
 *          - filters_event
 *          - FIRMWARE_VERSION				+
 *  		- groups            			#
 *  		- group_type
 *  		- HOUSING_COLOR					+
 *          - install_date
 *          - lifecycle_state
 * 			- LIGHT_DRIVER_SERIAL_NUMBER	+
 * 			- light_types     				#
 * 			- LOWER_ASSEMBLY_SERIAL_NUMBER	+
 *          - meter_firmware
 *          - MODEL_NUMBER					+
 *          - remote_disconnect
 *  		- repeats
 *          - offset_schedule   			#
 * 			- status_meter
 *          - status_process
 *  		- search
 *  		- status        				#
 *  		- tags     						#
 *  		- UPPER_ASSEMBLY_SERIAL_NUMBER	+
 *  		- users
 *  		- VOLTAGE_RANGE					+
 * 			- warning_type      			#
 */
/**
 * @param arrayObj
 * 			array, Array with filters to build
 * @param objTable
 * 			object, table to execute filters
 * @param fnCallBack
 * 			function, callback function
 * @param sPage
 * 			string, page of filter
 * @return date
 * 			date java script format
 */


 sensus.widgets.filters = {

		bDoIt : false,

		config  : {

			action_type        : { type : 'checkBox',      sJava : 'actionType'       },
			action_type_all    : { type : 'checkBox',      sJava : 'actionTypeAll'    },
			address            : {
				type        : 'text',
				sJava       : 'address',
				validation  : 'none' ,
				fields      : [ { name : 'Street', type : 'text', id : 'street' }, { name : 'City', type : 'text', id : 'city' }, { name : 'Zip', type : 'text', id : 'zip' } ]
			},
			alarm_type         : { type : 'checkBox',      sJava : 'alarmType'        },
			configuration      : { type : 'checkBox',      sJava : 'configuration'    },
			connection_status  : { type : 'checkBox',      sJava : 'connectionStatus' },
			custom_filters     : { type : 'customFilter',  sJava : 'custom_filters'   },
			device_type        : { type : 'checkBox',      sJava : 'deviceType'       },
			ecomode            : { type : 'checkBox',      sJava : 'ecoMode'          },
			color_temperature  : { type : 'checkBox',      sJava : 'colorTemperature' },
			housing_color      : { type : 'checkBox',      sJava : 'housingColor'     },
			voltage_range	   : { type : 'checkBox',      sJava : 'voltageRange'     },
			encryption_status  : { type : 'checkBox',      sJava : 'encryptionStatus' },
			event_schedule     : { type : 'checkBox',      sJava : 'eventSchedule'    },
			event_type         : { type : 'checkBox',      sJava : 'eventType'        },
			filters_event      : { type : 'dateFilter',    sJava : 'filters_event'    },
			groups             : { type : 'checkBox',      sJava : 'groups'           },
			group_type         : { type : 'checkBox',      sJava : 'groupType'        },
			install_date       : {
				type        : 'text',
				sJava       : 'installDate',
				validation  : 'none',
				fields      : [ { name : 'Start', type : 'text', id : 'start' }, { name : 'End', type : 'text', id : 'end' } ]
			},
			date_added	       : {
				type        : 'text',
				sJava       : 'dateAdded',
				validation  : 'none',
				fields      : [ { name : 'Start', type : 'text', id : 'start' }, { name : 'End', type : 'text', id : 'end' } ]
			},
			lifecycle_state    : { type : 'checkBox',      sJava : 'lifecycleState'   },
			light_types        : {
				type   : 'select',
				sJava  : 'lightTypes',
				fields : [
		           {name : 'Dimmable',  type : 'selectFilter', id : 'dimmable', options:[], validation: 'required'},
		           {name : 'Housing',   type : 'selectFilter', id : 'housing',  options:[], validation: 'required'},
		           {name : 'Wattage',   type : 'selectFilter', id : 'wattage',  options:[], validation: 'required'},
		           {name : 'Type',      type : 'selectFilter', id : 'lamptype', options:[], validation: 'required'}
	            ]
			},
			firmware_version	: {
				type   		: 'text',
				sJava  		: 'firmwareVersion',
				validation  : 'none',
				fields      : [{ name : 'firmware_version', type : 'text', id : 'firmware_version', validation: 'required' }]
			},
			model_number	: {
				type   		: 'text',
				sJava  		: 'modelNumber',
				validation  : 'required',
				fields      : [{ name : 'model_number', type : 'text', id : 'model_number', validation  : 'required' }]
			},
			bulb_serial_number	: {
				type   		: 'text',
				sJava  		: 'bulbSerialNumber',
				validation  : 'none',
				fields      : [{ name : 'bulb_serial_number', type : 'text', id : 'bulb_serial_number', validation: 'required' }]
			},
			light_driver_serial_number	: {
				type   		: 'text',
				sJava  		: 'SerialNumber',
				validation  : 'none',
				fields      : [{ name : 'light_driver_serial_number', type : 'text', id : 'light_driver_serial_number', validation: 'required' }]
			},
			lower_assembly_serial_number	: {
				type   		: 'text',
				sJava  		: 'lowerAssemblySerialNumber',
				validation  : 'none',
				fields      : [{ name : 'lower_assembly_serial_number', type : 'text', id : 'lower_assembly_serial_number', validation: 'required' }]
			},
			upper_assembly_serial_number	: {
				type   		: 'text',
				sJava  		: 'upperAssemblySerialNumber',
				validation  : 'none',
				fields      : [{ name : 'upper_assembly_serial_number', type : 'text', id : 'upper_assembly_serial_number', validation: 'required' }]
			},
			meter_firmware     : {
				type        : 'text',
				sJava       : 'meter_firmware',
				validation  : 'none',
				fields      : [
	                { name : 'Major',        type : 'text', id : 'major' },
	                { name : 'Minor',        type : 'text', id : 'minor' },
	                { name : 'Patch',        type : 'text', id : 'patch' },
	                { name : 'Engineering',  type : 'text', id : 'engineering' }
                ]
			},
			offset_schedule    : { type : 'checkBox',      sJava : 'offsetSchedule'   },
			remote_disconnect  : { type : 'checkBox',      sJava : 'remoteDisconnect' },
			repeats            : { type : 'checkBox',      sJava : 'repeats'          },
			search             : { type : 'searchFilter',  sJava : 'search',          validation : 'valid' },
			status             : { type : 'checkBox',      sJava : 'status'           },
			status_meter       : { type : 'checkBox',      sJava : 'statusMeter'      },
			status_process     : { type : 'checkBox',      sJava : 'statusProcess'    },
			tags               : { type : 'checkBox',      sJava : 'tags'             },
			users              : { type : 'checkBox',      sJava : 'users'            },
			warning_type       : { type : 'checkBox',      sJava : 'warningType'      }

		}
};

$.fn.wFilters = function (arrayObj, objTable, fnCallBack, sPage) {

	$('.filter-container li:not(:last)').remove();

	// clear filter DOM
	$('#w-filters').remove();

	$('.yui-t2').prepend('<div id="w-filters"></div>');

	// set object array
	var objFilters  = [{ }],
		objThat     = $('#w-filters');

	// filter html template
	$(objThat).append('<div class="yui-b">'
						+ '<form id="filtersForm">'
							+ '<div class="filter-vert rounded hide" id="searchFilter">'
								+ '<h3 class="rounded-top">' + sensus.locale.get('process.filter.searchactions') + '</h3>'
								+ '<div id="search" class="filter-input filter-input search ui-widget">'
									+ '<ul>'
										+ '<li><input id="query" type="text" value="" maxlength="100" /></li>'
										+ '<li><select id="query_type" class="validate[required]"></select></li>'
										+ '<li><button id="go-button">Search</button></li>'
									+ '</ul>'
								+ '</div>'
							+ '</div>'
							+ '<div class="filter-vert rounded" id="filters">'
								+ '<h3 class="rounded-top">' + sensus.locale.get('smartpoint.filter.filterlights') + '</h3>'
								+ '<div class="filter-input search ui-widget hide" id="dateFilter">'
									+ '<ul>'
										+ '<li>'
											+ '<span>View</span> <input id="view_from" type="text" class="short" />'
										+ '</li>'
										+ '<li>'
											+ '<span>Back</span> <input id="total_days" type="text" style="width: 2em" value="10" />'
											+ '<button id="go-button">days</button>'
										+ '</li>'
									+ '</ul>'
								+ '</div>'
								+ '<div class="filter-input status ui-widget hide" id="checkBox">'
									+ '<label class="toggle off">Status</label>'
									+ '<div class="collapse checkBoxUl">'
										+ '<ul class="all-checked">'
											+ '<li class="checkbox">'
												+ '<input type="checkbox" value="ALL" checked="checked"> <strong>All Status</strong>'
											+ '</li>'
										+ '</ul>'
										+ '<ul class="ui-listcombobox"></ul>'
									+ '</div>'
								+ '</div>'
								+ '<div class="filter-input emergency ui-widget hide" id="textFilter">'
									+ '<label class="toggle off">Configuration</label>'
									+ '<ul class="collapse" id="textFilterUl"></ul>'
								+ '</div>'
							+ '</div>'
						+ '</form>'
					+ '</div>');

	//  Check if there are customization of FILTERS in the database.
	if (sPage === "smartpointlist") {

		// retrieve date from session
		var oFilterCustomize = $.wCustomize.getData('smartpointlist', 'Filters');

		// if has filter customization
		if (oFilterCustomize.filters) {

			var oFilters  = oFilterCustomize.filters;

			// Clear configuration from init page
			arrayObj = [];

			for(var oFilter in oFilters) {
				if (oFilters.hasOwnProperty(oFilter)) {

					arrayObj.push(oFilters[oFilter].filterEnum);

				}
			}

			// Add a property that create a button "Custom Filter"
			arrayObj.push('CUSTOM_FILTERS');

		}

		// verify if is a text search
		if ($.address.parameter("query") || $.address.parameter("sd")) {

			arrayObj.splice(0, 0, "SEARCH");

		}

	}

	// Ajax call for populate the filters options
	var aAddressNames  = $.address.parameterNames(),
		nArrayObjSize  = '';

	for (c in aAddressNames) {
	
		if (aAddressNames.hasOwnProperty(c)
				&& (aAddressNames[c] !== "")
				&& (aAddressNames[c] !== "length")
				&& (aAddressNames[c] !== "sort")) {

			if ($.inArray(aAddressNames[c].toUpperCase(), arrayObj) == -1) {

				nArrayObjSize = parseInt(arrayObj.length)-1;
				arrayObj.splice(nArrayObjSize,0,aAddressNames[c].toUpperCase());

			}

		}

	}

	$.ajax({
		url     : "util/include.fillFilter.action",
		async   : false,
		data    : "data=" + arrayObj,
		success : function(data) {

			var sFilterName = '';

			for (l in arrayObj) {

				if (!arrayObj.hasOwnProperty(l)) {
					continue;
				}

				if (arrayObj[l].toLowerCase() == 'search') {

					var countQuery          = 0,
						$queryType          = $("#query_type"),
						queryOptions        = "",
						queryOptionsLength  = data.queryType.length;

					for (; countQuery < queryOptionsLength; countQuery++) {

						queryOptions += "<option class='"+ data.queryType[countQuery][0] +"' value='"+ data.queryType[countQuery][0] +"'>"+ data.queryType[countQuery][1] +"</option>";

					}

					$queryType.closest("#searchFilter").removeClass("hide");
					$queryType.append(queryOptions);

				}

				sFilterName = arrayObj[l].toLowerCase();

				var oConfig = sensus.widgets.filters.config[sFilterName];

				if (oConfig) {

					if (oConfig.type == 'checkBox') {

						objFilters.push({name : sensus.locale.get('commons.pages.' + sFilterName), type : 'checkBox',	session : sFilterName, boxes : data[oConfig.sJava]});

					} else if (oConfig.type == 'text') {

						objFilters.push({name : sensus.locale.get('commons.pages.' + sFilterName), type : 'textFilter', session : sFilterName, fields : oConfig.fields});

					} else if (oConfig.type == 'select') {

						for (k in oConfig.fields) {

							if (oConfig.fields.hasOwnProperty(k)) {

								oConfig.fields[k].options = data[oConfig.sJava][oConfig.fields[k].id];

							}
						}

						objFilters.push({name : sensus.locale.get('commons.pages.' + sFilterName), type : 'textFilter', session : sFilterName, fields : oConfig.fields});

					} else {

						objFilters.push({name : '', type : oConfig.type, session : sFilterName });

					}

				}
			}

			objFilters.shift(0);

		}
	});

	// Remove selected filters from table top bar
	$('.filter-container').on('click', 'li a:not(".clear")',  function(e) {
		e.preventDefault();

	    var oThis = $(this).closest('li');
	    var	sClickedId  = ($(this).closest('li').attr('id')).split('-');
		var sFilterId = sensus.pages.objFilters.convertIdName(sClickedId[0]).toUpperCase();
		var sInputType = $('#' + sFilterId).find(':input').attr('type');

		if (sFilterId.match(/^VECTOR_/)) {

			sInputType = 'drawing';

		}

		if (sInputType == undefined) {

			sInputType = $('#' + sFilterId).attr('type');

			if (sInputType == undefined) {
				sInputType = 'select';
			}

		}

		// for checkbox filtess options
	    if (sInputType == 'checkbox') {

	    	// action for checkbox different for "ALL" option
			if (sClickedId[1] != 'ALL') {

				//clear
				$('#' + sFilterId + ' :checkbox[value=' + sClickedId[1] + ']').attr('checked', false);
				sHash = sensus.pages.objFilters.buildHash(sFilterId);
				sensus.pages.objFilters.removeTag(sFilterId,sHash,oThis,true);

			}

		// for text filters options
	    } else if (sInputType == 'text') {

			// clear
			$('#' + sFilterId).val('');
			sHash = 0;
			sensus.pages.objFilters.removeTag(sFilterId, sHash, oThis,true);

			if (sFilterId === 'total_days') {

				$('.filter-container').find('#view_from').remove();

				sFilterId = 'view_from';
				$('#' + sFilterId).val('');
				sensus.pages.objFilters.removeTag(sFilterId, sHash, oThis, true);

			} else if (sFilterId === 'view_from') {

				$('.filter-container').find('#total_days').remove();

				sFilterId =	'total_days';
				$('#' + sFilterId).val('');
				sensus.pages.objFilters.removeTag(sFilterId, sHash, oThis, true);

			}

			if (sFilterId === 'end') {

				$('.filter-container').find('#end').remove();
				$('.filter-container').find('#start').remove();

				sFilterId = 'end';
				$('#' + sFilterId).val('');
				sensus.pages.objFilters.removeTag('date_added_before', sHash, oThis, true);

				sFilterId = 'start';
				$('#' + sFilterId).val('');
				sensus.pages.objFilters.removeTag('date_added_after', sHash, oThis, true);

			}

			if (sFilterId === 'start') {

				$('.filter-container').find('#end').remove();
				$('.filter-container').find('#start').remove();

				sFilterId = 'start';
				$('#' + sFilterId).val('');
				sensus.pages.objFilters.removeTag('date_added_before', sHash, oThis, true);

				sFilterId = 'end';
				$('#' + sFilterId).val('');
				sensus.pages.objFilters.removeTag('date_added_after', sHash, oThis, true);

			}

		// for select filters options
	    } else if (sInputType == 'select') {

			// clear
	    	$('#' + sFilterId).val('');
			sHash = 0;
			sensus.pages.objFilters.removeTag(sFilterId,sHash,oThis, true);

		// for other filters types

		} else if(sInputType == 'drawing') {

			sHash = 0;
			var aIds = $('#' + sFilterId.toLowerCase()).data().aIds;
			for (var i = 0; i < aIds.length; i++) {

				sensus.widgets.datatable.addDeselected(aIds[i], false);
				$('#smartpoint-table :checkbox[value='+aIds[i]+']').attr("checked", false);

			}
			sensus.widgets.datatable.setTotalResult(false);
			var sNewId = sFilterId.toLowerCase().charAt(0).toUpperCase() + sFilterId.toLowerCase().slice(1);
			var sFeatureId = "OpenLayers.Feature."+ sNewId.charAt(0).toUpperCase() + sNewId.slice(1);
			var oLayer = sensus.util.mapopen.objMap.getLayersByName('Editable')[0];
			var oFeature = oLayer.getFeatureById(sFeatureId);
			oLayer.removeFeatures( [ oFeature ] );
			sensus.pages.objFilters.removeTag(sFilterId,sHash,oThis,false);

	    } else {

			sHash = 0;
			sensus.pages.objFilters.removeTag(sFilterId,sHash,oThis, true);

		}
		
		if ($('.filter-container li').length == 1) {
			$('#tag-clear').hide();
		}

	});


	// Create a object with all options selected of a filter
	sensus.pages.objFilters = {

		filtersConf :{

			groups : {name : sensus.locale.get('commons.pages.groups'), type : 'checkBox',	session : 'groups'}

		},


		/**
		 * Remove tags
		 */
		/**
		 * @param sFilterId
		 * 			string, Filter dom
		 * @param sHash
		 * 			string, hash for address url
		 * @param oThis
		 * 			object, dom object
		 */
		removeTag : function(sFilterId,sHash,oThis,bReload){

			/** set url **/
			$.address.parameter(sFilterId.toLowerCase(), sHash);

			if($('span', oThis).hasClass('bad-tag') === false && bReload){

				/** reload table **/
				sensus.widgets.datatable.reloadTable(objTable, 0);

			}

			/** remove tag **/
			$(oThis).closest('li').remove();

		},

		existsDate : function(value){

			try {
				var dateFormat = sensus.settings.dateFormatMask;
				$.datepicker.parseDate(dateFormat, value);
				return true;
			} catch(e){
				return false;
			}
		},
		/**
		 * Fill the object
		 */
		/**
		 * @param sFilterId
		 * 			string, Filter dom
		 * @param sFilterType
		 * 			string, Filter type
		 * @param sFilterIdClicked
		 * 			string, dom object of clicked option
		 */
		fillObj : function(sFilterId,sFilterType,sFilterIdClicked) {

			var bSuccess = false;

			// for checkbox option
			if (sFilterType == 'checkbox') {

				if (($(sFilterIdClicked).is(":selected"))||$(sFilterIdClicked).is(":checked")) {

					// build tag
					sensus.pages.objFilters.buildTag(sFilterId, sFilterIdClicked, 'checkbox','');

				} else {// other options
					/** prevent for duplicate tag **/
					$("[id$='" + sFilterId + "-" + $(sFilterIdClicked).val() + "']").remove();

				}

				/** build hash for url **/
				sHash = sensus.pages.objFilters.buildHash(sFilterId);
				/** set url **/
				$.address.parameter(sFilterId.toLowerCase(), sHash);

				bSuccess = true;

			/** for other options **/
			} else {

				if(sFilterId === 'dateFilter'){

					var sStart = '';

				}

				$('#'+sFilterId+' li').each(function(){

					if($(this).find('select:visible').length){

						oInput = $(this).find('select:visible');

					} else {

						oInput = $(this).find('input:visible');

					}

					if(oInput.length) {

						if(!$("#filtersForm").validationEngine('validateField', $('#'+oInput.attr('id')))) {

							var sValue = '';

							if(oInput.attr('id') == 'query') {

								if($('#query_type').length) {

									sValue = $('#query_type :selected').val() + '|' + escape(oInput.val());

								} else {

									sValue = escape(oInput.val());

								}

								sensus.pages.objFilters.buildTag('search', oInput, 'text', oInput.attr('id'));

							} else {

								if(sFilterId === 'dateFilter') {

									if(oInput.attr('id') == 'view_from' && $("#total_days").val() != '') {

										sStart = oInput.val();
										var iBack = $("#total_days").val();
										sensus.pages.objFilters.buildTag(oInput.parent().find('label:first').text(),oInput,'text',oInput.attr('id'));
										var dStart = $.datepicker.parseDate(dateFormat, sStart);
										var dEnd = $.datepicker.formatDate(sensus.settings.dateFormatMask, new Date(dStart - (iBack*24*60*60*1000)));
										sValue = sStart+ "|"+dEnd;

									}

								} else {
								
									if (oInput.attr('id') == 'start' && $('#end').val().length == 0) {
										return ;
									}
									
									sensus.pages.objFilters.buildTag(oInput.parent().find('label:first').text(),oInput,'text',oInput.attr('id'));
									sValue = oInput.val();
									
								}

							}
							
							sensus.pages.objFilters.buildAddress(oInput.attr('id'),sValue);
							bSuccess = true;

						} else {

							bSuccess = false;
							oInput.removeClass('hide');
							return false;

						}
					}

				});
			}
			return bSuccess;
		},
		/**
		 * Build the URL
		 */
		/**
		 * @param sFilterId
		 * 			string, Filter dom
		 * @param sValue
		 * 			string, option value
		 */
		buildAddress : function(sFilterId, sValue) {

			/** set url **/
			if (sValue != '') {

				/** set parameter **/
				$.address.parameter(sFilterId.toLowerCase(), sValue.replace(/\s/g,"%20"));
				//$.address.parameter(sFilterId, encodeURIComponent(sValue));

			} else {

				/** clear parameter **/
				$.address.parameter(sFilterId.toLowerCase(), 0);

			}
		},
		/**
		 * Build Tags of table top bar
		 */
		/**
		 * @param sFilterId
		 * 			string, Filter dom
		 * @param oThis
		 * 			object, dom object
		 * @param sType
		 * 			string, filter type
		 * @param sIdFilter
		 * 			string, filter dom id
		 */
		buildTag : function(sFilterId, oThis, sType, sIdFilter) {
		
				var oTag = '';
				var sValue = $(oThis).val();

				//TODO next sprint - Lucas
				if(sType == 'drawing'){

					sValue = parseFloat(sensus.util.mapopen.vector.area).toFixed(2);

				}

				if($(oThis).get(0)){

					if(($(oThis).get(0).tagName).toLowerCase() == 'select'){

						sValue = $("option:selected", $(oThis)).text();

					}

				}

				// validate sValue
				if (sValue != undefined && sValue != '') {

					var tagName = '';
					var sFilterIdConv = sensus.pages.objFilters.convertIdName(sFilterId).toUpperCase();
					var sTagTitle = '';

						// for checkbox options
						if (sType == 'checkbox') {

							// retrieve tag name
							tagName = $('#'+sFilterId+' label').text();

							// set filter to ALL
							if(sValue == 'ALL') {

								$('#'+sFilterId+' .ui-listcombobox :checked').each(function(index) {

									$('#'+sFilterIdConv+'-'+$(this).val()).remove();

								});

								$('#'+sFilterId+' .ui-listcombobox :checkbox').attr('checked', false);


							} else { // set others filters

								$('#'+sFilterIdConv+'-ALL').remove();
								$('#'+sFilterIdConv+ ' ul.all-checked li.checkbox :input').attr('checked', false);

								if ($('.filter-container').length) {

									// get text
									objText = $(oThis).parent().text();
									sTagTitle = $(oThis).parent().attr('title');

									// tag html template
									if (objText != ' ') {

										oTag = $('<li id="'+ sFilterIdConv +'-'+sValue+'"><span class="type remove"><a href="">'+tagName+'</a></span><span class="title" title="'+sTagTitle+'">'+objText+'</span></li>');

									} else {

										//oTag = $('<li id="'+ sFilterIdConv +'-'+sValue+'"><span class="type remove bad-tag"><a href="">'+tagName+'</a></span><span class="title" title="'+sTagTitle+'">'+objText+'</span></li>');
										$('#'+sFilterIdConv+' :checkbox[value="'+sValue+'"]').remove();
									}
								}
							}
						}

						if($('.filter-container').length) {

							// for text filters
							if (sType == 'text') {

								// clear tag
								$('#'+sIdFilter , '.filter-container').remove();

								if(sFilterId == 'search') {

									if($('#query_type:visible','#'+sFilterId).length > 0){

										tagName = $('#query_type option:selected','#'+sFilterId).text();

									} else {

										tagName = $("#go-button", "#"+sFilterId).text();

									}

								} else {

									if(sIdFilter == "query_type" ) {
										return false;
									}

									if(sIdFilter == 'view_from') {

										var sBack = $('#view_from').parent().next().find('input').val();

										if (sBack && (!sBack.length || parseInt(sBack) > 365 || parseInt(sBack) < 0)) {
											return false;
										}

										var iBack = parseInt($('#view_from').parent().next().find('input').val());
										var dateFormat = sensus.settings.dateFormatMask;
										var dStart = $.datepicker.parseDate(dateFormat, sValue);
										var dEnd = $.datepicker.formatDate(dateFormat, new Date(dStart - (iBack*24*60*60*1000)));

										sValue = sValue + ' - ' + dEnd;
										tagName = sensus.locale.get('commons.pages.viewing');

									} else {

										/** set tag text **/
										tagName = sFilterId;

									}
								}

								if (tagName.length) {
									/** tag html template **/
									oTag = $('<li id="'+sIdFilter+'"><span class="type remove"><a href="">' + tagName + '</a></span> <span class="title">' + unescape(sValue) + '</span></li>');
								}

							}

							/** for select filters **/
							if (sType == 'select') {

								/** clear tag **/
								$('.filter-container #'+sIdFilter+'').remove();
								/** set tag text **/
								tagName = $('.' + sIdFilter).text();
								/** get text **/
								sValue = $('#query').val();
								/** tag html template **/
								oTag = $('<li id="'+sIdFilter+'"><span class="type remove"><a href="">'+tagName+'</a></span><span class="title">'+decodeURI(sValue)+'</span></li>');

							}

							//TODO next sprint - Lucas
							if (sType == 'drawing') {

								/** clear tag **/
								//$('.filter-container #'+sIdFilter+'').remove();
								/** set tag text **/
								tagName = 'drawing';
								/** get text **/
								//sValue = $('#query').val();
								/** tag html template **/
								oTag = $('<li id="'+sIdFilter+'"><span class="type remove"><a href="">'+tagName+'</a></span><span class="title"><span class="shape-icon" style="background:'+sensus.util.mapopen.vector.color+';opacity:0.6"></span>Shape '+ sensus.util.mapopen.vector.index +' ('+sValue+'km<sup>2</sup>)</span></li>');
								oTag.data('aIds', sensus.util.mapopen.vector.aIds);
							}

						/** insert tag on table **/
						if (oTag) {

							oTag.insertBefore('.filter-container li:last');

						}
					}

			} else if (sValue == ''){

				$('#'+sFilterId.toLowerCase(),'.filter-container').remove();

			}
			
			if ($('.filter-container li').length == 1) {
				$('#tag-clear').hide();
			} else {
				$('#tag-clear').show();
			}

		},

		/**
		 * Build the hash for URL
		 */
		/**
		 * @param sFilterId
		 * 			string, Filter dom
		 * @return sHash
		 * 			string, url
		 *
		 */
		buildHash : function(sFilterId) {
			sHash = '';
			iFilterLen = $('#'+sFilterId+ ' input:checkbox:checked').length;
			/** hash for ALL **/
			if(iFilterLen == 0){

				/** check all option **/
				var objField = '#'+sensus.pages.objFilters.convertIdName(sFilterId).toUpperCase()+ ' ul.all-checked li.checkbox :input';
				$(objField).attr('checked', true);
				sensus.pages.objFilters.buildTag(sensus.pages.objFilters.convertIdName(sFilterId), $(objField), 'checkbox', '');

				} else {

				/** check options from URL **/
				$.each($('#'+sFilterId+' input:checkbox:checked'), function(i, e) {

					if($(this).val() != 'ALL'){

						sHash+=$(this).val()+'|';

					}

				});

			}

			return sHash;

		},
		/**
		 * Convert the ID names to HTML
		 */
		/**
		 * @param sString
		 * 			string, string for convert
		 * @return newstring
		 * 			string, converted string
		 */
		convertIdName : function(sString) {

			if (sString !== undefined && sString !== null) {

				newstring = sString.toLowerCase().replace(/^\s+|\s+$/g,"").replace(" ","_");
				return newstring;

			} else {

				return "";

			}

		},

		defaultTag : function(){

			var tagId = '';
			$('ul.all-checked li').each(function(i){

				tagId = $(this).parent().parent().parent().attr('id');
				sensus.pages.objFilters.buildTag(tagId, $(this).find('input'), 'checkbox', '');

			});


		},
		
		createCheckboxFilter : function(aData, sNewDiv){
		
			iSelectSize = (aData.boxes) ? aData.boxes.length : 0;
			/** show options as autocomplete/select **/
			if (iSelectSize > 6) {

				$('<select id="combobox" class="listcombobox"></select>').appendTo(sNewDiv + ' .checkBoxUl');
				$(".listcombobox").combobox();

			}
			/** 6 options as checkbox **/
			var sLabel = '';
			var aLi = '';
			var aOptions = '';
			for (k in aData.boxes) {

				if (aData.boxes.hasOwnProperty(k)) {

					if (k < 6) {

						if(aData.boxes[k][1]){

							if(aData.boxes[k][1].length > 17) {

								sLabel = aData.boxes[k][1].substr(0,19)+ '...';

							} else {

								sLabel = aData.boxes[k][1];

							}

						aLi += '<li class="checkbox" title="'+aData.boxes[k][1]+'"><input type="checkbox" value="'+aData.boxes[k][0]+'"> '+sLabel+'</li>';

						}

					} else {

						aOptions += '<option value="'+aData.boxes[k][0]+'">'+aData.boxes[k][1]+'</option>" />';

					}

				}

			}
			$(aLi).appendTo(sNewDiv + ' .ui-listcombobox');
			$(aOptions).appendTo(sNewDiv + ' .listcombobox');
			/** set "more..." text to field **/
			$('#combobox_input', sNewDiv).val((parseInt(iSelectSize)-6)+' more...');			
		
		},
		
		createTextFilter : function(aData, sNewDiv){
		
			var sHtml = '';

			for (k in aData.fields) {

				if (aData.fields.hasOwnProperty(k)) {

					var j = 0;
					/** for text filters **/
					if (aData.fields[k].type == 'text') {

						var sClass = null;
						if(aData.fields[k].validation){
						
							sClass = 'validate[required]';
						
						}
						/** html template **/
						sHtml = $('<li id="textFilterLi'+k+'" ><label for="'+aData.fields[k].id+'">' + sensus.locale.get('commons.pages.'+aData.fields[k].id) + '</label><input id="'+aData.fields[k].id+'" class="standard '+sClass+'" type="text" /></li>');
						/** appent to filter dom **/
						$('#textFilterUl', $sNewDiv).append(sHtml);


					}
					/** for slider filters **/
					if (aData.fields[k].type == 'slider') {

						/** html template **/
						sHtml = $('<li id="sliderLi'+k+'"><p><label for="'+aData.fields[k].id+'">'+sensus.locale.get('commons.pages.'+ aData.fields[k].id)+':</label><input id="'+aData.fields[k].id+'_input" class="slider-value" type="text"></p><div id="'+aData.fields[k].id+'" class="ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all"><div class="ui-slider-range ui-widget-header" style="left: 10%; width: 80%;"></div><a class="ui-slider-handle ui-state-default ui-corner-all" href="#" style="left: 10%;"></a><a class="ui-slider-handle ui-state-default ui-corner-all" href="#" style="left: 90%;"></a></div></li>');
						/** append to filter dom **/
						$('#textFilterUl', $sNewDiv).append(sHtml);


					}

					/** for select filters **/
					else if (aData.fields[k].type == 'selectFilter') {

						/** html template **/
						if(aData.fields[k].validation){

							var validationClass = 'validate['+aData.fields[k].validation+']';

						}
						sHtml = $('<li id="textFilterLi'+k+'" ><label for="'+aData.fields[k].id+'">'+sensus.locale.get('commons.pages.'+aData.fields[k].id)+'</label><select id="'+aData.fields[k].id.toUpperCase()+'" class="standard '+validationClass+'"><option>ALL</option></select> </li>');
						/** append to filter dom **/
						$('#textFilterUl', $sNewDiv).append(sHtml);

						var aOptions = '';

						for (j in aData.fields[k].options) {

							if (aData.fields[k].options.hasOwnProperty(j)) {

								/** fill select options **/
								aOptions += ("<option value='"+encodeURIComponent(aData.fields[k].options[j][0])+"'>"+aData.fields[k].options[j][1]+"</option>");

							}

						}

						$('#'+aData.fields[k].id.toUpperCase()).append(aOptions);

					}

					if (k == (aData.fields.length)-1) {

						/** set style of last text to short **/
						$($sNewDiv + ' #textFilterLi'+k+ ' .standard').removeClass('standard').addClass('short');
						$('<button id="go-button">Go</button>').insertAfter($sNewDiv+' #textFilterLi'+k +' .short' );

					}

				}

			}
		
		}

	};


	/**
	 * Generate the HTML code of filters
	 */
	var x = 0;
	for (x in objFilters) {

		if (objFilters.hasOwnProperty(x)) {

			/** clone dom **/
			var sDivToClone = '#' + objFilters[x].type;

			if ((sDivToClone == '#searchFilter')||(sDivToClone == '#dateFilter')) {

				/** show dom div **/
				$(sDivToClone).removeClass("hide");

			} else {

				var sSession = objFilters[x].session.toUpperCase();
				/** clone and append dom div to filters **/
				$('#filters').append($(sDivToClone).clone(true).attr("id", sSession));

				var $sNewDiv = '#'+sSession;
				/** set filter text **/
				$($sNewDiv + ' .toggle').html(sensus.locale.get('commons.pages.'+objFilters[x].session));
				/** set ALL text **/
				var allText = sensus.locale.get('filter.all.'+objFilters[x].session);
				$($sNewDiv).find("strong").html(allText);

				/** for checkbox **/
				if (objFilters[x].type == 'checkBox') {
				
					sensus.pages.objFilters.createCheckboxFilter(objFilters[x], $sNewDiv);

				}
				/** for text filters **/
				if (objFilters[x].type == 'textFilter') {
				
					sensus.pages.objFilters.createTextFilter(objFilters[x], $sNewDiv);

				}
				/** set custom buttom to filter **/
				if (sDivToClone == '#customFilter') {

					/** html template **/
					$('<div id="customFilter" class="filter-input advanced ui-widget"><a class="button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" id="custom-filter-action" href="" role="button" aria-disabled="false"><span class="ui-button-text">'+sensus.locale.get('filter.customize')+'</span></a></div>').appendTo('#filters');

				}
				/** show div filter **/
				$($sNewDiv, objThat).removeClass("hide");

			}

		}

	}


	/**
	 * Slider option
	 */
	$( ".ui-slider" ).each(function() {

		/** create slider option **/
		$(this).empty().slider({
			range: true,
			min: 0,
			max: 10,
			values: [ 1, 9 ],
			slide: function( event, ui ) {
				$(this).parent().find('.slider-value').val( "v" + ui.values[ 0 ] + " - " + ui.values[ 1 ] );
			},
			stop: function(event, ui) {
			}
		});
		/** set option value **/
		$(this).parent().find('.slider-value').val( "v" + 1 + " - " + 9 );
	});

	/**
	 * Execute callback function
	 */
	fnCallBack(objThat);

	$('#checkBox').remove();

	$('.search-error').hide();

	sensus.pages.objFilters.defaultTag();

	/**
	 * Action to execute a filter
	 */

	$(objThat).on("click",'.filter-input :checkbox, #go-button',  function(e) {

		var sFilterId = $(this).closest('.filter-input').attr('id');
		var	sInputType = $(this).attr('type');

		if(!sInputType) {

			sInputType = $(this).parent().parent().children().eq(0).get(0).tagName;

		}

		// verify if the action is from "ALL" option
		if($(this).val()== 'ALL') {

			if(!$(this).is(":checked")) {

				e.preventDefault();

			} else {

				// create checkbox object
				var bReload = sensus.pages.objFilters.fillObj(sFilterId, sInputType, $(this));

				// reload table
				if (bReload === true) {

					if(sensus.pages.smartpoint && sensus.pages.smartpoint.smartpointTable && !$('#map-list').hasClass('hide-map')){

						var oSearch = sensus.pages.smartpoint.smartpointTable.data().config.oSettings.fnRequest();
						sensus.util.mapopen.oRequest = {'inquiryLightRequest' : new inquiryLightRequest(oSearch) };
						sensus.util.mapopen.fnGetData();

					} else {

					sensus.widgets.datatable.reloadTable(objTable, $.address.parameter("iDisplayStart"));

					}

				}

			}

		} else {

			if ($(this).is(":button")) {

				e.preventDefault();

			}

			// create checkbox object
			var bReload = sensus.pages.objFilters.fillObj(sFilterId, sInputType, $(this));

			// reload table
			if (bReload === true) {

				//$('#'+sFilterId+' li').find('input:visible').next('label').hide();
				$.address.parameter("iDisplayStart", 0);
				
				if(sensus.pages.smartpoint && $.address.path() == "/smartpoint/ajax.list.action" && !$('#map-list').hasClass('hide-map')){

						var oSearch = sensus.pages.smartpoint.smartpointTable.data().config.oSettings.fnRequest();
						sensus.util.mapopen.oRequest = {'inquiryLightRequest' : new inquiryLightRequest(oSearch) };
						sensus.util.mapopen.fnGetData();

				} else {

					sensus.widgets.datatable.reloadTable(objTable, $.address.parameter("iDisplayStart"));

				}

			}

		}

	});


	$(objThat).on("keypress",'#query',  function(e) {

	 if(e.which == 13) {

		var sFilterId = $(this).closest('.filter-input').attr('id'),
			sInputType = $(this).attr('type');

		if ($(this).is(":button")) {

			e.preventDefault();

		}

		/** create checkbox object **/
		var bReload = sensus.pages.objFilters.fillObj(sFilterId, sInputType, $(this));

		/** reload table **/
		if (bReload === true) {

			$('#'+sFilterId+' li').find('input:visible').next('label').hide();
			$.address.parameter("iDisplayStart", 0);
			if(sensus.pages.smartpoint && sensus.pages.smartpoint.smartpointTable && !$('#map-list').hasClass('hide-map')){

				var oSearch = sensus.pages.smartpoint.smartpointTable.data().config.oSettings.fnRequest();
				sensus.util.mapopen.oRequest = {'inquiryLightRequest' : new inquiryLightRequest(oSearch) };
				sensus.util.mapopen.fnGetData();

			} else {

				sensus.widgets.datatable.reloadTable(objTable, $.address.parameter("iDisplayStart"));

			}

		}

	 }

	});

	/**
	 * Get the filters from URL
	 */
	var sAddressNames = $.address.parameterNames();

	if (sAddressNames.length != 0) {

		for (c in sAddressNames) {

			if (sAddressNames.hasOwnProperty(c)) {

				sFilter = sAddressNames[c].toUpperCase();

				if ((sFilter !== "LENGTH") || (sFilter !== "SD") || (sFilter !== "SORT")) {
					/** get values from url **/
					var aHasUrl = ($.address.parameter(sAddressNames[c])).split('|');

					if (aHasUrl.length > 1) {
					
						/** for text search **/
						if (sFilter == 'QUERY') {

							/** Change the option **/
							$('#query_type').val(aHasUrl[0]);
							$('#query').val(decodeURI(aHasUrl[1]));
							/** build tag **/
							var oObj = $('#query_type').closest('ul').find('#query');
							sensus.pages.objFilters.buildTag($('#query_type').closest('.filter-input').attr('id'), oObj, oObj.attr('type'), oObj.attr('id'));

						} else if  ($('#'+sFilter).find('input').attr('type') == "text"){
													
							var objFilter = $('#'+sFilter);
							objFilter.find('input').val(decodeURI(aHasUrl[0]));
							
							// build tag
							sensus.pages.objFilters.buildTag($('#'+sFilter).find('label:first').text(), objFilter.find('input'), 'text', sFilter);

							objFilter.find('label').removeClass('off').addClass('on').next('ul').show();

						} else if (sFilter == 'DATE_ADDED_AFTER' || sFilter == 'DATE_ADDED_BEFORE' || sFilter == 'START' || sFilter == 'END') {

							if (sFilter == 'DATE_ADDED_AFTER' || sFilter == 'START') {
								$("#start").val($.date.dateFormat( aHasUrl[0], sensus.settings.dateFormatMask));
							} else {
								$("#end").val($.date.dateFormat( aHasUrl[0], sensus.settings.dateFormatMask));
							}

							// build tag
							sensus.pages.objFilters.buildTag('start', $('#start'), 'text', 'start');
							sensus.pages.objFilters.buildTag('end', $('#end'), 'text', 'end');

							$("#date_added").find('label').removeClass('off').addClass('on').next('ul').show();

						} else {
							
							if (sFilter == "STREET"|| sFilter == "CITY" || sFilter == "ZIP") {
							
								var objFilter = $('#'+sFilter);
								objFilter.val(decodeURI(aHasUrl[0]));
								
								// build tag
								sensus.pages.objFilters.buildTag(sFilter, objFilter, 'text', sFilter);

								$("#address").find('label').removeClass('off').addClass('on').next('ul').show();
								
							}
							
							/** remove last element from aHasUrl **/
							var objCombobox = '';
							var oFilter = $('#'+sFilter);
														
							// To Remove if exist one element in array and it is empty
							if (aHasUrl.length == 1 && aHasUrl[0].replace(/\s/, "").length < 0) {

								aHasUrl.pop();

							}

							var sLabel = '';
							for(i in aHasUrl) {

								if (aHasUrl.hasOwnProperty(i)) {

									if(aHasUrl[i]){

										if($('#'+sFilter).get(0)){
										
											if($('#'+sFilter).get(0).tagName.toLowerCase() == 'select'){
											
												$('#'+sFilter+' option[value="'+aHasUrl[i]+'"]').attr('selected', 'selected');

												sensus.pages.objFilters.buildTag(sFilter,$('#'+sFilter),'text',sFilter);

												$('#'+sFilter).parent().parent().parent().find('label').removeClass('off').addClass('on').next('ul').show();

											} else {

												aHasUrl[i] = aHasUrl[i].replace('%2C',',');
												/** check checkbox **/
												var objFilter = $('#'+sFilter+' :checkbox[value="' + aHasUrl[i].replace(/\s/, "_") +'"]');

												if(objFilter.length === 0) {

													$('#combobox option[value="'+aHasUrl[i]+'"]','#'+sFilter).text();

													sLabel = $('#combobox option[value="'+aHasUrl[i]+'"]','#'+sFilter).text();
													sLabelLong = sLabel;
													if(sLabel.length > 17) {

														sLabel = sLabel.substr(0, 15)+'...';

													}

													$('<li class="checkbox" title="'+sLabelLong+'"><input type="checkbox" value="'+aHasUrl[i]+'"> '+ sLabel + '</li>').appendTo('#'+sFilter + ' .ui-listcombobox');

													$('#combobox option[value="'+aHasUrl[i]+'"]','#'+sFilter).remove();
													objCombobox = $('#combobox option', '#'+sFilter);

													if(objCombobox.size() < 1){

														$('#combobox_input, button', '#'+sFilter).remove();

													} else {

														$('#combobox_input', '#'+sFilter).val(objCombobox.size()+' more...');

													}

													objFilter = $('#'+sFilter+' :checkbox[value='+aHasUrl[i]+']');

												}

												objFilter.attr('checked', true);
												/** build tag **/
												sensus.pages.objFilters.buildTag(sFilter,objFilter,'checkbox');

											}

										}

									}

								}

							}

							oFilter.find('label').removeClass('off').addClass('on').next('div').show();
						}

					/** for other filters **/
					} else {

						if(sFilter != 'total_days') {

							var objFilter = $('#'+sFilter);

							objFilter.val(decodeURI(aHasUrl));
							/** build tag **/
							sensus.pages.objFilters.buildTag(sFilter,objFilter,'text',sFilter);

							objFilter.parent().parent().parent().find('label').removeClass('off').addClass('on').next('ul').show();

						}

					}

				}

			}

		}

	}

	/**
	 * Append a filter from selectbox to checkbox
	 */
	$("#filters").on("focus", ".ui-autocomplete-input", function(event, ui) {

		$(this).val('');

		var that = $(this);

		$(this).autocomplete({

			close : function(event, ui) {

				var sMatcher = new RegExp( "^" + $.ui.autocomplete.escapeRegex( $(this).val() ) + "$", "i" ),
					bValid = false;

			  	sSelect = $(that).siblings('.listcombobox');
				iSelectSize = ((sSelect.children("option").size())-1);
				sSelect.children( "option" ).each(function() {

					if ( $( this ).text().match( sMatcher ) ) {

						this.selected = bValid = true;
						var sLabel = $(this ).text();

						if(sLabel.length > 17){

							sLabel = sLabel.substr(0,15)+'...';

						} else {

							sLabel = sLabel;

						}
			          	$(this).parent().siblings('.ui-listcombobox').append('<li class="checkbox" title="'+$(this ).text()+'"><input type="checkbox" checked="checked" value="'+ $(this).val() +'"> '+ sLabel +'</li>');
			          	sFilterId = $(this).closest('.filter-input').attr('id');
						var objFilter = ($('#'+sFilterId+' li:last').children());
						sensus.pages.objFilters.fillObj(sFilterId, 'checkbox', objFilter);
						sensus.widgets.datatable.reloadTable(objTable, 0);
			          	$(this).parent().next().val(iSelectSize+' more...');
						if (iSelectSize == 0) {

							$(this).parent().next().next().remove();
							$(this).parent().next().remove();

						}
						$(this).remove();
			          	return false;

					}

				});

			}

		});

	});	

	/**
	 * Reset all filters
	 */
	$('.filter-container').on('click','.clear',  function(e) {
		e.preventDefault();

		/** reset select box **/
		if ($(this).hasClass("reset-history")) {
			$('#w-filters select option:eq(0)').prop("selected", "selected");
		} else {
			$('#w-filters select').val(0);
		}

		/** reset checkbox **/
		$('#w-filters :checkbox').attr('checked', false);
		/** reset all checkbox **/
		$('#w-filters .all-checked :checkbox').attr('checked', true);
		/** reset all text box **/
		$('#w-filters input[type=text]').val('');
		/** reset tags **/
		$('.filter-container li:not(:last)').remove();
		/** reset url **/
		sPar = $.address.parameterNames();
		for (x in sPar) {

			if (sPar.hasOwnProperty(x)) {
	
				if(sPar[x] != 'id' && sPar[x] != 'sort' && sPar[x] != 'length'){

					$.address.parameter(sPar[x], 0);

					}
				}

			}
		/** reload table **/


		sensus.pages.objFilters.defaultTag();
		
		try {
			sensus.util.mapopen.objMap.getLayersByName('Editable')[0].destroyFeatures();
		} catch(e) {}

		$.address.parameter("iDisplayStart", 0);
		sensus.widgets.datatable.reloadTable(objTable,0);
	});

	/**
	 * Execute the Customize filter/columns plugin
	 */

	$('#custom-filter-action').click(function(e) {
		e.preventDefault();

		// Model default of settings
		var oCustomerSettings = {
			sTypeOfElement     : 'Filters', //sType
			sCurrentPage       : "smartpointlist", // sPage
			oCurrentFilters    : objFilters, // objFilters
			fnCallBack         : fnCallBack
		};

		$('#customize-filter').wCustomize(oCustomerSettings); // $('#customize-filter').wCustomize('Filters',fnCallBack, objFilters, 'smartpointlist');

	});

	// set datapicker to end option
	var dateFormat        = sensus.settings.dateFormatMask,
		sClassTypeFormat  = 'datePt';

	if ( dateFormat == 'mm/dd/yy') {

		sClassTypeFormat = 'dateEn';

	}

	$("#start").datepicker({
		maxDate    : "+0D",
		dateFormat : dateFormat,
		onSelect   : function(dateText, oDate) {

			var oEnd = $("#end");

			oEnd.removeClass();
			
			dEnd = $.datepicker.formatDate('yy-mm-dd', new Date(oDate.currentYear, oDate.currentMonth, oDate.currentDay));

			oEnd.addClass("short hasDatepicker validate[required, custom["+sClassTypeFormat+"], future["+dEnd+"]");

		}
	});

	$("#end").datepicker({dateFormat: dateFormat, maxDate: "+0D"});
	$("#view_from").datepicker({dateFormat: dateFormat, maxDate: "+0D"});
	$("#query").addClass("validate[required]");
	$("#start").addClass("validate[required, custom["+sClassTypeFormat+"]");
	$("#end").addClass("validate[required, custom["+sClassTypeFormat+"]");
	$('#total_days').addClass('validate[required,custom[integer],min[0],max[365]]');

	var sDateNow = $.datepicker.formatDate('yy-mm-dd', new Date(sensus.settings.datenow));

	$('#view_from').addClass('validate[required, custom['+sClassTypeFormat+'], past[' + sDateNow + ']]');

	$("#start, #end, #query, .toggle").click(function(){

		$('#filtersForm').validationEngine('hide');

	});

};



/**
 * Toggle the filter
 */
$(".toggle").live("click", function() {

	/** create toggle functionality **/
	$(this).next(".collapse").toggle('blind',null,500);

	if ($(this).hasClass('on')) {

		$(this).switchClass('on', 'off', 500);

	} else {

		$(this).switchClass('off', 'on', 500);

	}

	return false;
});
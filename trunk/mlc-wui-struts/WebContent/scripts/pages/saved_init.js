/**
 * @namespace sensus.pages.search
 * @description The init namespace for the Search Page.
 */

/**
 * @fileoverview Initializes the search page.
 *
 * @author Cristiane Cobo
 */
$(document).ready(function() {

	sensus.util.page.initMessaging();

	$(".tabs").find("li").find("a:contains('Saved')").click(function(e){
		e.preventDefault();
	});

	/* Init common page functionality */
	sensus.util.page.initProgressBar();
	sensus.util.page.startProgressBar();
	sensus.util.actiondialog.initActionDialog();
	
	function removeDuplicates(inputArray) {
		var i;
		var len = inputArray.length;
		var outputArray = [];
		var temp = {};

		for (i = 0; i < len; i++) {
			temp[inputArray[i]] = 0;
		}
		for (i in temp) {
			outputArray.push(i);
		}
		return outputArray;
	}	

	sensus.pages.saved.savedTable = $('#saved-table').dataTable(sensus.widgets.datatable.setTable({

		id : "saved-table",
		sAjaxSource : sensus.settings.searchUrl,
		aColumns : [
			{sId: "Id",           bVisible : false                    },
			{sId: "Name",         sWidth : "12%",   bSortable : true  },
			{sId: "Description",  sWidth : "30%",   bSortable : false },
			{sId: "Filter",       sWidth : "40%",   bSortable : false },
			{sId: "Added",        sWidth : "11%",   bSortable : true  },
			{sId: "Delete",       bVisible : false                    },
			{sId: "Url",          bVisible : false                    }
		],
		oSettings : {
			oRequest      : inquiryPaginationRequest,
			fnRequest     : function() {

					return new SearchMeter([]);

			},
			aColumns      : ['id', 'name', 'description', 'searchParameters', 'createDate','',''],
			sResponseObj  : 'customSearches',
			iDefaultCol   : 1,
			remove        : {
				bRemove  : true,
				url      : sensus.settings.deleteSearchUrl,
				text     : function(data, i) {
					return sensus.locale.get("searchdelete.warning.genericmessage", data[i.Name]);
				},
				oRequest : 'customSearchRequest',
				fnRequest : function(data, i) {

					var request = new customSearchRequest(data[i.Id], null, null, null, null, null);
					return request;

				},
				success  : function(data, i) {
					sensus.widgets.datatable.reloadTable(sensus.pages.saved.savedTable);
					return sensus.locale.get("action.deletesearch.success");
				}
			}
		},
		fnRowCallback : function(nRow, aData, iDisplayIndex, col) {

			//Format  date
			$("td:eq(" +col.Added+ ")", nRow).text($.date.dateFormat(aData[col.Added]));

			/* Add Description Parameters */
			$('td:eq(' + col.Description + ')', nRow).html(aData[col.Description]);

			var oParameters      = aData[col.Filter],
				sHtmlParameters  = '<ul class="filter-container">',
				sValue           = "";
				aParameters 	 = [];
			
			for (var iKey in oParameters) {
	
				if (oParameters.hasOwnProperty(iKey) 
						&& oParameters[iKey].propertyEnum.toLowerCase() != 'smartpoint_column' 
						&& oParameters[iKey].propertyEnum.toLowerCase() != 'smartpoint_filter') {
					
					sValue = decodeURIComponent(oParameters[iKey].name);

					aParameters.push(oParameters[iKey].propertyEnum);

					
					var currentValue = oParameters[iKey].propertyEnum.toLowerCase();
					var titleValue = sensus.locale.get('commons.pages.' + currentValue);

					if (titleValue == "Date End" || titleValue == "Date Start") {
					
						sValue = $.date.dateFormat( sValue, sensus.settings.dateFormatMask);
						
					}

					// Get information of the field value of the object
					if (currentValue == 'pole_id') {
						sValue = oParameters[iKey].value;
					} else if(currentValue == 'sort') {
						sValue = oParameters[iKey].value.split(' ')[1].replace('asc', ' <img src="images/icn-sort-up.gif">')
														.replace('desc', ' <img src="images/icn-sort-down.gif">');

					} else if (currentValue == 'dimmable') {

						if(oParameters[iKey].name == 'true'){
						
							sValue = sensus.locale.get('smartpoint.filter.lamptype.dimmable.yes');
						
						} else if(oParameters[iKey].name == 'false'){
						
							sValue = sensus.locale.get('smartpoint.filter.lamptype.dimmable.no');
						
						} else {
						
							sValue = sensus.locale.get(oParameters[iKey].name);
							
						}

					} else if (currentValue == 'all_warnings' || currentValue == 'all_alarms') {

						sValue = sensus.locale.get(oParameters[iKey].name);

					} else if (currentValue == 'protected') {

						sValue = 'Unlocked';

						if (oParameters[iKey].value == 'true') {

							sValue = 'Locked';

						}

					} else if (currentValue == 'all_status') {

						switch(sValue) {
							case '-1' : sValue = 'Override';    break;
							case '0'  : sValue = 'Maintenance'; break;
							case '1'  : sValue = 'Alarm';       break;
							case '2'  : sValue = 'Warning';     break;
							case '3'  : sValue = 'Active';      break;
							case '4'  : sValue = 'Deactivated'; break;
						}

					} else if (currentValue == 'eco_mode') {
					
						switch(sValue) {
							case '50_100' : sValue = sensus.locale.get('smartpoint.filter.ecomodeEconomy'); break;
							case '15_50' : sValue = sensus.locale.get('smartpoint.filter.ecomodeValue'); break;
							case '0_15' : sValue = sensus.locale.get('smartpoint.filter.ecomodeSecurity'); break;
						}
					
					}
					
					//if (sValue != undefined ){
					if (sValue != undefined && oParameters[iKey].labelKey != "sensus.mlc.property.sort") {
						
						sHtmlParameters += '<li id="filter-active" class="read_only"><span class="title">' + titleValue
											+ '</span><span class="type-status">' + sValue + '</span></li>';
					}
				}

			}

			aParameters = removeDuplicates(aParameters);
			var sUrl = '';
			for (var i in aParameters) {

				if (aParameters.hasOwnProperty(i)) {
				
				var sFilter = sensus.pages.saved.config[aParameters[i]];
				sFilter = sFilter.replace("date_added_before","start");
				sFilter = sFilter.replace("date_added_after","end");
				sUrl += '&'+sFilter;

				var aFilters = ($.grep(oParameters, function(e) { return e.propertyEnum == aParameters[i]; }));

					for (var k in aFilters) {
					
						if (aFilters.hasOwnProperty(k)) {
							
							if ((aFilters[k].propertyEnum === 'DATE_ADDED_AFTER') || (aFilters[k].propertyEnum === 'DATE_ADDED_BEFORE')){
								sUrl.replace("DATE_ADDED_BEFORE","start");
								sUrl.replace("DATE_ADDED_AFTER","end");
								
								sUrl += $.date.dateFormat(aFilters[k].value);

							} else {
							
								if(sFilter == 'sort='){
						
									var aSort = aFilters[k].value.split(' ');
									sUrl += aSort[0]+'|'+aSort[1];
									
								} else {

									sUrl += escape(aFilters[k].value);
									
								}
							}
							
							if(sFilter != 'street' && sFilter != 'city' && sFilter != 'zip' && sFilter != 'query=12|' && sFilter != 'query=36|'){
							
								sUrl += '|';
							
							}

						}

					}

				}

			}

			/* Add Saved Link */
			$('td:eq(' + col.Name + ')', nRow).wrapInner('<strong><a class="afilter" href="smartpoint/ajax.list.action?sd=true'+sUrl+'"class="edit"></a></strong>');

			$('td:eq(' + col.Filter + ')', nRow).html(sHtmlParameters + '</ul>');

		}

	}));

});
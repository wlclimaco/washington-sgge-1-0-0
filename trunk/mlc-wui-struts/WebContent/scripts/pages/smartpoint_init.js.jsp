<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<script type="text/javascript">

/**
 * @description The init namespace for the SmartPoint Page.
 * @fileoverview Initializes the SmartPoint page.
 *  
 * @author Anke Doerfel-Parker
 */
$(document).ready(function() {

	// Initialize common page elements
	sensus.util.actiondialog.initActionDialog();
	sensus.util.page.initMessaging();

	$(".tabs").find("li").find("a:contains('Search')").click(function(e){
		e.preventDefault();
	});

	// Set up List/Map toggle switch (set no value yet)
	var objViews = $("#views");

	objViews.buttonset();

	$('.map', objViews).button("option", "icons", {
		primary : 'ui-icon-map'
	});

	$('.list', objViews).button("option", "icons", {
		primary : 'ui-icon-list'
	});

	var sMsg = 'smartpoint.table.header';
	sensus.util.mapopen.iZoom = -1;
	if(sensus.util.mapopen.objMap){
	
		delete sensus.util.mapopen.objMap;
	
	}
	
	if(!$.address.parameter('sort')){
	
		$.address.parameter('sort', 'ss.label_key|desc|');
	
	}

	// jQuery dataTable setup 
	sensus.pages.smartpoint.smartpointTable = $('#smartpoint-table').dataTable(sensus.widgets.datatable.setTable({
			id             : "smartpoint-table",
			customColumns  : "smartpointlist",
			sAjaxSource    : sensus.settings.searchUrl,
			aColumns       : [
				{sId : "POLE_ID",                       bEffectSortable: true,  sName : sensus.locale.get(sMsg + '.poleid'),                     propertyRequest : "poleId",                                      sWidth : "5%"},
				{sId : "RNI_ID",                        bEffectSortable: true,  sName : sensus.locale.get(sMsg + '.flexnetid'),                  propertyRequest : "rniId",                                                                 sWidth : "5%"},
	          	{sId : "LAMP_TYPE",                     bEffectSortable: true,  sName : sensus.locale.get(sMsg + '.lamptype'),                   propertyRequest : "lampTypeWattageDimmable",                   sWidth : "5%"},
	          	{sId : "DATE_ADDED",                    bEffectSortable: true,  sName : sensus.locale.get(sMsg + '.added'),                      propertyRequest : "dateAdded[fn(sensus.pages.smartpoint.processDateAdded)]",                                   sWidth : "5%"},
	          	{sId : "CITY_NAME",	                    bEffectSortable: true,  sName : sensus.locale.get(sMsg + '.city'),                       propertyRequest : "cityName",                                    sWidth : "5%"},
	          	{sId : "PROTECTED",                     bEffectSortable: true,  sName : sensus.locale.get(sMsg + '.protected'),                  propertyRequest : "protect[fn(sensus.pages.smartpoint.processProtect)]",                   sWidth : "5%"},
	          	{sId : "ECOMODE",                      bEffectSortable: true,  sName : sensus.locale.get(sMsg + '.ecomode'),                  	 propertyRequest : "ecoMode[fn(sensus.pages.smartpoint.processEcoMode)]",                   sWidth : "5%"},
	          	{sId : "STATUS",                        bEffectSortable: true,  sName : sensus.locale.get(sMsg + '.alertstatus'),                propertyRequest : "currentStatusMessage[fn(sensus.pages.smartpoint.processLightStatus)]",  sWidth : "5%"},
				{sId : "FIRMWARE_VERSION",              bEffectSortable: true,  sName : sensus.locale.get(sMsg + '.firmwareversion'),            propertyRequest : "firmwareVersion",                             sWidth : "5%"},
				{sId : "MODEL_NUMBER",                  bEffectSortable: true,  sName : sensus.locale.get(sMsg + '.modalnumber'),                propertyRequest : "modelNumber",                                 sWidth : "5%"},
				{sId : "COLOR_TEMPERATURE",             bEffectSortable: true,  sName : sensus.locale.get(sMsg + '.colortemperature'),           propertyRequest : "colorTemperature",                            sWidth : "5%"},
				{sId : "HOUSING_COLOR",                 bEffectSortable: true,  sName : sensus.locale.get(sMsg + '.housingcolor'),               propertyRequest : "housingColor",                                sWidth : "5%"},
				{sId : "INPUT_VOLTAGE_RANGE",           bEffectSortable: true,  sName : sensus.locale.get(sMsg + '.voltagerange'),               propertyRequest : "inputVoltageRange",                          sWidth : "5%"},
				{sId : "BULB_SERIAL_NUMBER",            bEffectSortable: true,  sName : sensus.locale.get(sMsg + '.bulbserialnumber'),           propertyRequest : "bulbSerialNumber",                           sWidth : "5%"},
				{sId : "SERIAL_NUMBER",                 bEffectSortable: true,  sName : sensus.locale.get(sMsg + '.lightdriverserialnumber'),    propertyRequest : "lightDriverSerialNumber",                   sWidth : "5%"},
				{sId : "LOWER_ASSEMBLY_SERIAL_NUMBER",  bEffectSortable: true,  sName : sensus.locale.get(sMsg + '.lowerassemblyserialnumber'),  propertyRequest : "lowerAssemblySerialNumber",                 sWidth : "5%"},
				{sId : "UPPER_ASSEMBLY_SERIAL_NUMBER",  bEffectSortable: true,  sName : sensus.locale.get(sMsg + '.upperassemblyserialnumber'),  propertyRequest : "upperAssemblySerialNumber",                 sWidth : "5%"},
				{sId : "LATITUDE",	                    bVisible : false,       bFixed : true,                                                   propertyRequest : "latitude",                                     sWidth : "5%"},
	          	{sId : "LONGITUDE",                     bVisible : false,       bFixed : true,                                                   propertyRequest : "longitude",                                    sWidth : "5%"},
				{sId : "Id", 	                        bVisible : false,       bFixed : true,                                                   propertyRequest : "id",                                                                    sWidth : "5%"},
			],
			oSettings      : {
			   oRequest      : inquiryLightRequest,
			   bCheckbox     : true,
			   bSelection    : true,
			   iMapCol       : 7,
			   iDefaultCol   : 9,
			   sResponseObj  : 'lights',
			   fnRequest     : function() {

					var sStatus = $.address.parameter('status'),
						aStatus = [];

					if (sStatus) {

						aStatus = sStatus.split("|");
						aStatus.pop();

					}

					return new searchLight(sensus.widgets.datatable.fnConvertEnum('LightStatusEnum', aStatus), sensus.util.page.getSearchParameters());
			   }
			   <sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support')">
			   ,edit : {
					col : "POLE_ID",
					url : "smartpoint/ajax.smartpoint.detail.main.action"
				}
				</sec:authorize>
			},
			fnRowCallback : function(nRow, aData, iDisplayIndex, col) {

				var aStatus = aData[col.STATUS].split('|');
				$("td:eq(" + col.STATUS + ")", nRow).addClass("status active").html(aStatus[0]);
				$(nRow).addClass(aStatus[1]);
	
		   	},

		   	fnDrawCallback : function(setting, objColumn) {

				// Validation IE8. If not has status the ie8 put class on the first column

				// Column default active
				$("th:eq(" + objColumn.STATUS + ")", "#smartpoint-table").addClass('active');

				if (objColumn.PROTECTED ) {

					// Column default active
					$("th:eq(" + objColumn.PROTECTED + ") span:first", "#smartpoint-table").html('<b class="locked"></b>');

				}

			},

		   	fnInfoCallback  : function( oSettings, iStart, iEnd, iMax, iTotal, sPre ) {

			    $("#total-records").html(iTotal);

				// Change text to singular
			    if (iTotal == 1) {

			    	$("#label-total-records").html(sensus.locale.get("table.result.match"));

			    }

				$('#general-total').html(iTotal);

			}

	}));

	var objStatusViewport = $("#table-list");
	var objMap = $("#map-list");

	objMap.addClass('hide-map');

	objViews.find("label").click(function() {

		if ($(this).prev().hasClass("list")) {

			objStatusViewport.show();
			sensus.pages.smartpoint.fnReloadTable();
			objMap.addClass('hide-map');

		} else if ($(this).prev().hasClass("map")) {

			sensus.util.mapopen.iZoom = 6;
			objStatusViewport.hide();
		
			sensus.util.mapopen.sUrl = "smartpoint/ajax.fetchLightsToMap.action";
			var oSearch = sensus.pages.smartpoint.smartpointTable.data().config.oSettings.fnRequest();
			sensus.util.mapopen.oRequest = {'inquiryLightRequest' : new inquiryLightRequest(oSearch) };

			if(!$.isEmptyObject(sensus.util.mapopen.objMap)){
			
				if(sensus.util.mapopen.iZoom == 6){
	
					sensus.util.mapopen.mapIt('map', sensus.widgets.datatable.objLatLon);
	
				} 
				
				if(sensus.util.mapopen.objMap.getZoom() == 18){
	
					for (c in sensus.widgets.datatable.selectedRows) {
	
						if (sensus.widgets.datatable.selectedRows.hasOwnProperty(c))	{
	
							$('#map-smartpoint-id-'+sensus.widgets.datatable.selectedRows[c]).attr('checked',true);
	
						}
	
					}
				
					for (d in sensus.widgets.datatable.deselectedRows) {
	
						if (sensus.widgets.datatable.deselectedRows.hasOwnProperty(d))	{
	
							$('#map-smartpoint-id-'+sensus.widgets.datatable.deselectedRows[d]).attr('checked',false);
	
						}

					}

				}
				
			} else {

				sensus.util.mapopen.mapIt('map', sensus.widgets.datatable.objLatLon);
				
			}

			objMap.removeClass('hide-map');
			sensus.util.mapopen.objMap.moveTo();

		}

	});

	

	// Set up rest of filter area
	$(".collapse").hide();

	$('.list', '#views').next().addClass('ui-state-active');

	// Set up combo boxes and tabs 
	$(".combo-filter").combofilter( {
		zIndex : 3999
	});

	/// jQuery dataTable setup 
	$(".results .save, .tabs .save").click(function() {

		sensus.util.actiondialog.launchActionDialog("saveSearch", sensus.pages.smartpoint.dialogSettings["saveSearch"]);
		return false;

	});

	$("#lights-on-off").wLightControl(true);
	
	// Export Csv
	$("#csv").click(function() {

		sensus.pages.smartpoint.generateCsvFile();
		return false;

	});

	// Initialize action buttons
	
	var oMenuPlug = {

			'clearAlerts' : sensus.pages.smartpoint.clearAlerts,
			'clearManualOverride' : sensus.pages.smartpoint.clearManualOverride,
			'resetValuesLightList' : sensus.pages.smartpoint.resetValues,
			'updateStatus' : sensus.pages.smartpoint.updateStatus,
			'applyProtected' : sensus.pages.smartpoint.applyProtected,
			'applyListen' : sensus.pages.smartpoint.applyListen

	};
	
	sensus.util.page.menuPlug(sensus.pages.smartpoint, oMenuPlug);

	var aFilters = ['GROUPS', 'STATUS','ALARM_TYPE','WARNING_TYPE', 'LIGHT_TYPES', 'EVENT_SCHEDULE', 'OFFSET_SCHEDULE', 'TAGS', 'ADDRESS', 'CONFIGURATION', 'ECOMODE', 'custom_filters'];

	if ($.address.parameter("sd") || $.address.parameter("query")) {

		$('.tab-saved').removeClass('hide');

		aFilters.splice(0, 0, "SEARCH");

	}

	// FILTER settings
	$("#w-filters").wFilters(aFilters, sensus.pages.smartpoint.smartpointTable, function() {

		<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
			$("#GROUPS").append("<a href='group/ajax.createGroupPage.action' class='alist filter-create launch'>"+ sensus.locale.get("smartpoint.filter.creategroup") +"</a>");
		</sec:authorize>

		$('#search').on("click", '#go-button', function(e) {

			var sQuery = $("#query").val();

			// isEmpty
			if (sQuery && sQuery.length) {

				// isValid
				if (sQuery.match(/[a-zA-Z0-9\-]/g) != null) {

					sensus.widgets.filters.bDoIt = true;
					return true;

				} else {

					$('#query').validationEngine('showPrompt', sensus.locale.get('commons.pages.characterInvalid'), 'red', 'topLeft', true);

				}

			}

		});

		$('#searchFilter h3').html(sensus.locale.get("smartpoint.search.title"));

		var oFilters = $("#filters"); 

		$(".EVENT_ID, .RNI_id, .ACTION_ID, .ACTION_NAME", "#query_type").remove();

		$("div:not(.hide) label.off:not(:eq(0), :eq(1))", oFilters).removeClass("on").addClass("off").next().hide();

		$('#STATUS label, #GROUPS label', oFilters).removeClass("off").addClass("on");

		if (!$.address.parameterNames().length) {

			$("#configuration .all-checked", oFilters).remove();

		};

		$('#map').css('height', parseInt($('#filtersForm').height())-30 );		
		
	}, "smartpointlist");
	
	$("th#status").removeClass("sorting_disabled").addClass("sorting_desc");
	
	if ($('.point-type-navigation').is(':visible')) {
		$('.messaging:first').remove();
	}

	$('#table-list').on('click', '.alist', function(e){

		sensus.util.session.createSession('COLUMNS', { listUrl : $.address.queryString() });
	
	});

});
</script>
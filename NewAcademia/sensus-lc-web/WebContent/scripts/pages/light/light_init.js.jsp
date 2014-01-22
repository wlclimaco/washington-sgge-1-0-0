<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<script type="text/javascript">

/**
 * @description The init namespace for the SmartPoint Page.
 * @fileoverview Initializes the SmartPoint page.
 *
 * @author Anke Doerfel-Parker
 */
$(document).ready(function() {

	$(".tabs").find("li").find("a:contains('Search')").click(function(e){
		e.preventDefault();
	});

	// Set up List/Map toggle switch (set no value yet)
	var objViews = $("#views");

	objViews.buttonset();
	$( ".map", objViews).button({ icons: { primary: "ui-icon-map" }});
	$( ".list", objViews).button({ icons: { primary: "ui-icon-list" }});

	var sMsg = 'smartpoint.table.header';

	if($.address.parameterNames().length == 0)
	{
		$.address.parameter('lifecycle_state', '5|');
	}

	if(!$.address.parameter('sort'))
	{
		$.address.parameter('sort', 'label_key|asc|');
	}


	var aColumns = [
			{sId : "POLE_ID",                  	 	bEffectSortable: true,  sName : $.sc.locale(sMsg + '.poleid'),                     propertyRequest : "poleId",                                      							sWidth : "5%"},
			{sId : "FLEXNET_ID",                    bEffectSortable: true,  sName : $.sc.locale(sMsg + '.flexnetid'),                  propertyRequest : "radio.flexNetId",                                                         sWidth : "5%"},
          	{sId : "LAMP_TYPE_WATTAGE_DIMMABLE",    bEffectSortable: true,  sName : $.sc.locale(sMsg + '.lamptype'),                   propertyRequest : "configuration.lampTypeWattageDimmable",                   				sWidth : "5%"},
          	{sId : "DATE_ADDED",                    bEffectSortable: true,  sName : $.sc.locale(sMsg + '.added'),                      propertyRequest : "[fn(sensus.pages.smartpoint.formatDateAdded)]",     						sWidth : "5%"},
          	{sId : "CITY",	                    	bEffectSortable: true,  sName : $.sc.locale(sMsg + '.city'),                       propertyRequest : "radio.location.city",                                    					sWidth : "5%"},
          	{sId : "PROTECTED",                     bEffectSortable: true,  sName : $.sc.locale(sMsg + '.protected'),                  propertyRequest : "protect[fn(sensus.pages.smartpoint.processProtect)]",                 	sWidth : "5%"},
          	{sId : "ECOMODE",                       bEffectSortable: true,  sName : $.sc.locale(sMsg + '.ecomode'),                    propertyRequest : "ecoMode[fn(sensus.pages.smartpoint.processEcoMode)]",                     sWidth : "5%"},
          	{sId : "LIFECYCLE_STATE",               bEffectSortable: true,  sName : $.sc.locale(sMsg + '.lifecyclestate'),             propertyRequest : "[fn(sensus.pages.smartpoint.processLightState)]",  						sWidth : "5%"},
          	{sId : "ALERTS",                        bEffectSortable: true,  sName : $.sc.locale(sMsg + '.alerts'),                     propertyRequest : "[fn(sensus.pages.smartpoint.processLightAlerts)]",						sWidth : "5%"},
			{sId : "FIRMWARE_VERSION",              bEffectSortable: true,  sName : $.sc.locale(sMsg + '.firmwareversion'),            propertyRequest : "configuration.firmwareVersion",                             				sWidth : "5%"},
			{sId : "MODEL_NUMBER",                  bEffectSortable: true,  sName : $.sc.locale(sMsg + '.modelnumber'),                propertyRequest : "configuration.modelNumber",                                 				sWidth : "5%"},
			{sId : "COLOR_TEMPERATURE",             bEffectSortable: true,  sName : $.sc.locale(sMsg + '.colortemperature'),           propertyRequest : "configuration.colorTemperature",                            				sWidth : "5%"},
			{sId : "HOUSING_COLOR",                 bEffectSortable: true,  sName : $.sc.locale(sMsg + '.housingcolor'),               propertyRequest : "configuration.housingColor",                                				sWidth : "5%"},
			{sId : "INPUT_VOLTAGE_RANGE",           bEffectSortable: true,  sName : $.sc.locale(sMsg + '.voltagerange'),               propertyRequest : "configuration.inputVoltageRange",                          				sWidth : "5%"},
			{sId : "BULB_SERIAL_NUMBER",            bEffectSortable: true,  sName : $.sc.locale(sMsg + '.bulbserialnumber'),           propertyRequest : "configuration.bulbSerialNumber",                          				sWidth : "5%"},
			{sId : "SERIAL_NUMBER",                 bEffectSortable: true,  sName : $.sc.locale(sMsg + '.lightdriverserialnumber'),    propertyRequest : "configuration.lightDriverSerialNumber",                   				sWidth : "5%"},
			{sId : "LOWER_ASSEMBLY_SERIAL_NUMBER",  bEffectSortable: true,  sName : $.sc.locale(sMsg + '.lowerassemblyserialnumber'),  propertyRequest : "configuration.lowerAssemblySerial",                 						sWidth : "5%"},
			{sId : "UPPER_ASSEMBLY_SERIAL_NUMBER",  bEffectSortable: true,  sName : $.sc.locale(sMsg + '.upperassemblyserialnumber'),  propertyRequest : "configuration.upperAssemblySerial",                 						sWidth : "5%"},
			{sId : "ECOMODE_REPLACED_WATTAGE",  	bEffectSortable: true,  sName : $.sc.locale(sMsg + '.ecomodereplacedwattage'),	   propertyRequest : "ecoModeBaseline.replacedWattage",                 						sWidth : "5%"},
			{sId : "ECOMODE_REPLACED_TYPE",  		bEffectSortable: true,  sName : $.sc.locale(sMsg + '.ecomodereplacedtype'),  	   propertyRequest : "ecoModeBaseline.replacedType",                 							sWidth : "5%"},
			{sId : "CONSUMPTION",             		bEffectSortable: true,  sName : $.sc.locale(sMsg + '.totalconsumption') + " KWh",  propertyRequest : "lastOperationalData.consumption",                 						sWidth : "5%"},
			{sId : "LATITUDE",	                    bVisible : false,       bFixed : true,                                             propertyRequest : "radio.location.latitude",                                     			sWidth : "5%"},
          	{sId : "LONGITUDE",                     bVisible : false,       bFixed : true,                                             propertyRequest : "radio.location.longitude",                                    			sWidth : "5%"},
          	{sId : "Id", 	                        bVisible : false,       bFixed : true,                                             propertyRequest : "id",                                                                 		sWidth : "5%"},
          	{sId : "PRECEDENCE", 	                bVisible : false,       bFixed : true,                                             propertyRequest : "lastNotificationHistory.precedence",                                      sWidth : "5%"},
		];

	// Pre loaded Filters/Columns
	<c:choose>
		<c:when test="${not empty refresh}">
			var oFilterPreLoad = "refresh";
		</c:when>
		<c:when test="${empty filters}">
			var	oFilterPreLoad = null;
		</c:when>
		<c:otherwise>
			var	oFilterPreLoad = ${filters};
		</c:otherwise>
	</c:choose>

	// jQuery dataTable setup
	sensus.pages.smartpoint.smartpointTable = $('#smartpoint-table').dataTable(sensus.widgets.datatable.setTable({
			id             : "smartpoint-table",
			customColumns  : "smartpointlist",
			sAjaxSource    : "api/light/fetchall",
			bPreLoad : true,
			<c:choose>
				<c:when test="${not empty refresh}">
					oPreLoadResponse : "refresh",
				</c:when>
				<c:when test="${empty response}">
			    	oPreLoadResponse : null,
			    </c:when>
			    <c:otherwise>
			    	oPreLoadResponse : ${response},
			    </c:otherwise>
			</c:choose>
			oFilterPreLoad	: oFilterPreLoad,
			aColumns       	: aColumns,
			oSettings      	: {
			   oRequest      : lightTableRequest,
			   bCheckbox     : true,
			   bSelection    : true,
			   iMapCol       : 7,
			   iDefaultCol   : 10,
			   sResponseObj  : 'lightList',
			   fnRequest     : sensus.pages.smartpoint.fnRequestAction
			   <sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support')">
			   ,edit : {
					col : "POLE_ID",
					url : "lightDetail"
				}
				</sec:authorize>
			},
			fnRowCallback : function(nRow, aData, iDisplayIndex, col) {

				$("td:eq(" + col.LIFECYCLE_STATE + ")", nRow).addClass("strongText");

				if(col.ALERTS)
				{
					$("td:eq(" + col.ALERTS + ")", nRow).addClass("strongText status active");
				}

				if(aData[col.PRECEDENCE] != null)
				{
					$(nRow).addClass(aData[col.PRECEDENCE].toLowerCase());
				}

		   	},

		   	fnDrawCallback : function(setting, objColumn) {

		   		if(objColumn.ALERTS)
		   		{
		   			// Column default active
					$("th:eq(" + objColumn.ALERTS + ")", "#smartpoint-table").addClass('active');
		   		}

				if (objColumn.PROTECTED )
				{
					// Column default active
					$("th:eq(" + objColumn.PROTECTED + ") span:first", "#smartpoint-table").html('<b class="locked"></b>');
				}

			},

		   	fnInfoCallback  : function( oSettings, iStart, iEnd, iMax, iTotal, sPre ) {

			    $("#total-records").html(iTotal);

				// Change text to singular
			    if (iTotal == 1) {

			    	$("#label-total-records").html($.sc.locale("table.result.match"));

			    }

				$('#general-total').html(iTotal);

			}

	}));

	var objStatusViewport = $("#table-list");
	var objMap = $("#map-list");

	objMap.addClass('hide-map');

	objViews.find("label").click(function() {

		if ($(this).prev().hasClass("list")) {

			$('#lights-select-all, #lights-select-page, #lights-select-none, #comma').show();
			$('#lights-maps-all, #lights-maps-none').hide();
			$('#lights-select-page').show();
			objStatusViewport.show();
			sensus.pages.smartpoint.fnReloadTable();
			objMap.addClass('hide-map');

		} else if ($(this).prev().hasClass("map")) {

			$('#lights-select-all, #lights-select-page, #lights-select-none, #comma').hide();
			$('#lights-maps-all, #lights-maps-none').show();
			objMap.removeClass('hide-map');
			sensus.util.mapopen.iZoom = 0;
			objStatusViewport.hide();

			if($.sc.mapExists() && $('#map').hasClass('olMap'))
			{
				$.sc.mapFromFilter(true);
			}
			else
			{
				var oSearchCriteria = sensus.pages.smartpoint.smartpointTable.data().config.oSettings.fnRequest();
				var oRequest = new lightTableRequest(oSearchCriteria);
				$.sc.maps('map', sensus.widgets.datatable.objLatLon, "api/maps/fetch", oRequest, null, 1);
				$.sc.zoomTo(0);
			}

		}

	});

	// Set up rest of filter area
	$(".collapse").hide();

	$('.list', '#views').next().addClass('ui-state-active');

	/// jQuery dataTable setup
	$(".results .save, .tabs .save").click(function() {

		$.sc.launchActionDialog("saveSearch", sensus.pages.smartpoint.dialogSettings["saveSearch"]);
		return false;

	});

	$("#lights-on-off").wLightControl(true);

	// Export Csv
	$("#csv").click(function(e) {

		e.preventDefault();

		$.sc.startProgressBar();

		// Get Request populate with current filters
		var request = { lightRequest: new lightTableRequest(sensus.pages.smartpoint.fnRequestAction()) };
		request.lightRequest.pageSize = 0;
		request.lightRequest.sortExpressions = $.sc.getSortExpression();

		// Default Columns
		request.csvColumns = $("#smartpoint-table").data().columnsResponse.aColumnsIds;
		var iMapItColumn = $.inArray("MAP_IT", request.listColumn);
		if(iMapItColumn != -1)
		{
			request.listColumn.splice( iMapItColumn, 1 );
		}
		sensus.util.exportcsv.generateCSV('api/export/generateLightCSV', request);

	});

	// Initialize action buttons

	var oMenuPlug = {

			'clearAlerts' : sensus.pages.smartpoint.clearAlerts,
			'clearManualOverride' : sensus.pages.smartpoint.clearManualOverride,
			'resetValuesLightList' : sensus.pages.smartpoint.resetValues,
			'updateStatus' : sensus.pages.smartpoint.getDataFromLight,
			'updateReadings' : sensus.pages.smartpoint.getReadings,
			'applyProtected' : sensus.pages.smartpoint.applyProtected,
			'applyListen' : sensus.pages.smartpoint.applyUnprotected

	};

	sensus.util.page.menuPlug(sensus.pages.smartpoint, oMenuPlug);

	var aFilters = ['SEARCH', 'GROUPS', 'LIFECYCLE_STATE', 'ALERTS', 'ALARM_TYPE','WARNING_TYPE', 'LIGHT_TYPES', 'EVENT_SCHEDULE', 'OFFSET_SCHEDULE', 'TAGS', 'ADDRESS', 'CONFIGURATION', 'ECOMODE', 'CUSTOM_FILTERS'];

	if (($.address.parameter("sd") || $.address.parameter("query"))) {
		$('.tab-saved').removeClass('hide');
	} else {
		$('.tab-saved').addClass('hide');
		aFilters.shift();
	}

	// FILTER settings
	$.sc.filters(aFilters, sensus.pages.smartpoint.smartpointTable, function() {

		<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
			$("#GROUPS").append("<a href='groupcreate' class='alist filter-create launch'>"+ $.sc.locale("smartpoint.filter.creategroup") +"</a>");
		</sec:authorize>

		$('#search').on("click", '#go-button', function(e) {

			var sQuery = $("#query").val();
			// isEmpty
			if (sQuery && sQuery.length) {

				sType = $("#query-type").find("option:selected").val();
				// isValid
				if ((sType ==  "36" && $.isNumeric(sQuery)) || (sType == "12" && sQuery.match(/[a-zA-Z0-9\-]/g) != null))
				{
					sensus.widgets.filters.bDoIt = true;
					return true;
				}
				else
				{
					if(sType ==  "36")
					{
						$("#query").validationEngine('showPrompt', $.sc.locale("search.validation.onlyLetterFlexnetId"), 'red', '', true);
						return false;
					}
					else
					{
						$('#query').validationEngine('showPrompt', $.sc.locale('commons.pages.characterInvalid'), 'red', 'topLeft', true);
					}

				}

			}
		});

		$('#searchFilter h3').html($.sc.locale("smartpoint.search.title"));

		var oFilters = $("#filters");

		$("div:not(.hide) label.off:not(:eq(0), :eq(1), :eq(2))", oFilters).removeClass("on").addClass("off").next().hide();

		$('#GROUPS label, #LIFECYCLE_STATE label, #ALERTS label', oFilters).removeClass("off").addClass("on");

		if (!$.address.parameterNames().length) {

			$("#configuration .all-checked", oFilters).remove();

		};

		// Remove filter preLoad.
		oFilterPreLoad = null;
		sensus.pages.smartpoint.smartpointTable.data().config.oFilterPreLoad = null;

	}, "smartpointlist", oFilterPreLoad);

	$("th#alerts").removeClass("sorting_disabled").addClass("sorting_desc");

	if ($('.point-type-navigation').is(':visible')) {
		$('.messaging:first').remove();
	}

	$('#table-list').on('click', '.alist', function(e){
		var oColumns = sensus.util.session.readSession('COLUMNS');

		if(oColumns.session.columnFilters)
		{
			sensus.util.session.createSession('COLUMNS', { listUrl : $.address.queryString(), customSearchId: oColumns.session.columnFilters.customSearchId, listColumn: oColumns.session.columnFilters.listColumn, additionalColumns: oColumns.session.columnFilters.additionalColumns, filters: oColumns.session.columnFilters.filters, additionalFilters: oColumns.session.columnFilters.additionalFilters, listTypeEnum: oColumns.session.columnFilters.listTypeEnum});
		}
		else
		{
			sensus.util.session.createSession('COLUMNS', { listUrl : $.address.queryString()});
		}

	});

	$('#lights-maps-all').click(function(e){
		e.preventDefault();
		//Enable allRows selection for server command to be sent
		sensus.widgets.datatable.isAllRows = true;
		sensus.widgets.datatable.selectedRows = [];
		sensus.widgets.datatable.deselectedRows = [];
		sensus.widgets.datatable.setTotalResult(false);
		$('#popup_contentDiv :checkbox').attr('checked', true);
		$("#actions").find('.message').removeClass("ui-state-error").addClass("ui-state-highlight");
	});
	$('#lights-maps-none').click(function(e){
		e.preventDefault();
		//Enable allRows selection for server command to be sent
		sensus.widgets.datatable.isAllRows = false;
		sensus.widgets.datatable.selectedRows = [];
		sensus.widgets.datatable.deselectedRows = [];
		sensus.widgets.datatable.setTotalResult(false);
		$('#popup_contentDiv :checkbox').attr('checked', false);
		$("#actions").find('.message').removeClass("ui-state-error").addClass("ui-state-highlight");
	});

	$.sc.stopGlobalProgressBar();

});
</script>
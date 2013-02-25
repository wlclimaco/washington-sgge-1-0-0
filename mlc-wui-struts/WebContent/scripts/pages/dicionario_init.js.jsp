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
	sensus.pages.dicionario.smartpointTable = $('#smartpoint-table').dataTable(sensus.widgets.datatable.setTable({
			id             : "smartpoint-table",
			customColumns  : "smartpointlist",
			sAjaxSource    : sensus.settings.searchUrl,
			aColumns       : [
				{sId : "ID",          bEffectSortable: true,  sName : sensus.locale.get(sMsg + '.id'),              propertyRequest : "id",       		sWidth : "5%"},
				{sId : "REQUERID", bEffectSortable: true,  sName : sensus.locale.get(sMsg + '.idTela'),         	propertyRequest : "Requerid",   	sWidth : "5%"},
	          	{sId : "LABEL",       bEffectSortable: true,  sName : sensus.locale.get(sMsg + '.label'),           propertyRequest : "label",    		sWidth : "5%"},
	          	{sId : "NAME",        bEffectSortable: true,  sName : sensus.locale.get(sMsg + '.name'),            propertyRequest : "name",     		sWidth : "5%"},
	          	{sId : "POSITIONX",	  bEffectSortable: true,  sName : sensus.locale.get(sMsg + '.positionX'),       propertyRequest : "positionX",		sWidth : "5%"},
	          	{sId : "POSITIONY",   bEffectSortable: true,  sName : sensus.locale.get(sMsg + '.positionY'),       propertyRequest : "positionY",		sWidth : "5%"},
	          	{sId : "TYPE",        bEffectSortable: true,  sName : sensus.locale.get(sMsg + '.type'),            propertyRequest : "type"     ,		sWidth : "5%"},
				{sId : "BUSCA",       bEffectSortable: true,  sName : sensus.locale.get(sMsg + '.firmwareversion'), propertyRequest : "Busca",			sWidth : "5%"},
				{sId : "VALIDATION",  bEffectSortable: true,  sName : sensus.locale.get(sMsg + '.modalnumber'),     propertyRequest : "Validation",     sWidth : "5%"},
				{sId : "TAMANHO",     bEffectSortable: true,  sName : sensus.locale.get(sMsg + '.housingcolor'),    propertyRequest : "Tamanho",        sWidth : "5%"},
				{sId : "ID", 	      bVisible : false,       bFixed : true,                                        propertyRequest : "id",             sWidth : "5%"},
			],
			oSettings      : {
			   oRequest      : inquiryPaginationRequest,
			   bCheckbox     : true,
			   bSelection    : true,
			   iDefaultCol   : 9,
			   sResponseObj  : 'dicionarios',
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
				if(aData[col.STATUS]){
					var aStatus = aData[col.STATUS].split('|');
					$("td:eq(" + col.STATUS + ")", nRow).addClass("status active").html(aStatus[0]);
					$(nRow).addClass(aStatus[1]);
				}
		   	},

		   	fnDrawCallback : function(setting, objColumn) {

				// Validation IE8. If not has status the ie8 put class on the first column

				// Column default active
				$("th:eq(" + objColumn.STATUS + ")", "#smartpoint-table").addClass('active');


			},

		   	fnInfoCallback  : function( oSettings, iStart, iEnd, iMax, iTotal, sPre ) {

			    $("#total-records").html(iTotal);

				// Change text to singular
			    if (iTotal == 1) {

			    	$("#label-total-records").html(sensus.locale.get("table.result.match"));

			    }

				$("#actions .message span:not(.checked-count)").remove();
				$('<span style="font-weight:normal"> '+sensus.locale.get("table.filter.of")+' '+ iTotal +'</span>').insertAfter($('#checked-count'));

			}

	}));

	var objStatusViewport = $("#table-list");
	var objMap = $("#map-list");

	objMap.addClass('hide-map');

	objViews.find("label").click(function() {

		if ($(this).prev().hasClass("list")) {

			objStatusViewport.show();
			objMap.addClass('hide-map');

		} else if ($(this).prev().hasClass("map")) {

			sensus.util.mapopen.iZoom = 6;
			objStatusViewport.hide();
		
			sensus.util.mapopen.sUrl = "smartpoint/ajax.fetchLightsToMap.action";
			var oSearch = sensus.pages.dicionario.smartpointTable.data().config.oSettings.fnRequest();
			sensus.util.mapopen.oRequest = {'inquiryPaginationRequest' : new inquiryPaginationRequest(oSearch) };

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

		sensus.util.actiondialog.launchActionDialog("saveSearch", sensus.pages.dicionario.dialogSettings["saveSearch"]);
		return false;

	});

	$("#lights-on-off").wLightControl(true);
	
	// Export Csv
	$("#csv").click(function() {

		sensus.pages.dicionario.generateCsvFile();
		return false;

	});

	// Initialize action buttons
	
	var oMenuPlug = {

			'clearAlerts' : sensus.pages.dicionario.clearAlerts,
			'clearManualOverride' : sensus.pages.dicionario.clearManualOverride,
			'resetValuesLightList' : sensus.pages.dicionario.resetValues,
			'updateStatus' : sensus.pages.dicionario.updateStatus,
			'applyProtected' : sensus.pages.dicionario.applyProtected,
			'applyListen' : sensus.pages.dicionario.applyListen

	};
	
	sensus.util.page.menuPlug(sensus.pages.dicionario, oMenuPlug);

	var aFilters = ['GROUPS', 'STATUS','ALARM_TYPE','WARNING_TYPE', 'LIGHT_TYPES', 'EVENT_SCHEDULE', 'OFFSET_SCHEDULE', 'TAGS', 'ADDRESS', 'CONFIGURATION', 'ECO_MODE', 'custom_filters'];

	if ($.address.parameter("sd") || $.address.parameter("query")) {

		$('.tab-saved').removeClass('hide');

		aFilters.splice(0, 0, "SEARCH");

	}

	// FILTER settings
	$("#w-filters").wFilters(aFilters, sensus.pages.dicionario.smartpointTable, function() {

		<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
			$("#groups").append("<a href='group/ajax.createGroupPage.action' class='alist filter-create launch'>"+ sensus.locale.get("smartpoint.filter.creategroup") +"</a>");
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

		$('#status label', oFilters).removeClass("off").addClass("on");
		$("#groups label", oFilters).removeClass("off").addClass("on");		

		if (!$.address.parameterNames().length) {

			$("#configuration .all-checked", oFilters).remove();

		};

		$('#map').css('height', parseInt($('#filtersForm').height())-30 );		
		
	}, "smartpointlist");
	
	$("th#status").removeClass("sorting_disabled").addClass("sorting_desc");
	
	if ($('.point-type-navigation').is(':visible')) {
		$('.messaging:first').remove();
	}	

});
</script>
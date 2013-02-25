
/**
 * @namespace sensus.pages.analytics
 * @description The init namespace for the Analytics Page.
 * @fileoverview Initializes the Analytics page.
 * @author Raphael Constantino
 *
 */
$(document).ready(function() {

	sensus.util.page.initMessaging();

	// Here set a active menu acord URL
	$(".active", $("#analytics-bar")).removeClass("active").end().find("#bar" + $.address.parameter("ty")).addClass("active");

	// Export csv
	$("#csv").click(function() {

		sensus.pages.analytics.generateCsvFile();
		return false;

	});

	// Start the date filter for
	$("#actions-options").wDateFilters();

	// Initialize component Combo from jQuery UI
	$('#group_select').combobox({
		zIndex : 3999
	});

	// Initialize component DatePicker
	$('.datepicker').datepicker({
		dateFormat  : sensus.pages.analytics.datePickerFormat(),
		maxDate     : '0d'
	});

	// Set Parameter to do the validation
	sensus.pages.analytics.validation == ($.address.parameter('dv') == '1') ? 1 : 0;

	// Initialize objects and components
	sensus.pages.analytics.getHash($.address.hash());

	$('#group_select_input').autocomplete({
		close: function(event, ui) {

			sensus.pages.analytics.search();
			sensus.pages.analytics.getTableData();
			sensus.widgets.datatable.rebuild(sensus.pages.analytics.analyticsTable);

		}
	});

	var oGroupSelInput = $('#group_select_input');

	oGroupSelInput.autocomplete({
		close: function(event, ui) {

			sensus.pages.analytics.search();
			sensus.pages.analytics.getTableData();
			sensus.widgets.datatable.rebuild(sensus.pages.analytics.analyticsTable);

		}
	});

	oGroupSelInput.click(function() {
		$(this).attr('value', '');
	});

	var oDateTagBox = $("#date-tag", $("#actions-options")),
		oActive;

	oActive = ($.address.parameter("dt").length > 0) ? $("#" + $.address.parameter("dt"), oDateTagBox) : $("#1w", oDateTagBox);

	oActive.addClass("active");

	sensus.pages.analytics.setGroup();
	sensus.pages.analytics.setDateInterval(oActive, 1);

	// Initialize container
	$("a", oDateTagBox).click(function() {

		oActive.removeClass("active");
		oActive = $(this).parent();
		oActive.addClass("active");

		sensus.pages.analytics.setDateInterval(oActive);
		sensus.pages.analytics.search();
		sensus.pages.analytics.getTableData();
		sensus.widgets.datatable.rebuild(sensus.pages.analytics.analyticsTable);

		return false;

	});

	$("#analytics-filter-date").validationEngine('attach', {
		validationEventTrigger  : 'submit',
		onValidationComplete    : function(form, status) {

			var sMask         = sensus.settings.dateFormatMask,
				sValDate1     = $("#analytics-filter-date #datepicker1").val(),
				sValDate2     = $("#analytics-filter-date #datepicker2").val(),
				sExistsDate1  = sensus.pages.analytics.existsDate(sValDate1, "1", sMask),
				sExistsDate2  = sensus.pages.analytics.existsDate(sValDate2, "2", sMask);

			if (sExistsDate1 && sExistsDate2) {

				var bTodayDate1    = sensus.pages.analytics.isTodayDate(sValDate1,"1"),
					bTodayDate2    = sensus.pages.analytics.isTodayDate(sValDate2,"2"),
					bGreaterDate1  = sensus.pages.analytics.isGreaterDate(sValDate1,"1", sMask),
					bValidDate1    = sensus.pages.analytics.isValidDate(sValDate1,"1", sMask),
					bValidDate2    = sensus.pages.analytics.isValidDate(sValDate2,"2", sMask);

				if (bValidDate1 && bValidDate2 && bTodayDate1 && bTodayDate2 && bGreaterDate1 && status) {

					sensus.pages.analytics.filterAnalyticsDateCall();
					sensus.pages.analytics.getTableData();
					
				}

			}

			sensus.util.page.stopProgressBar(null, true);

		}

	});

	sensus.pages.analytics.buildHash();
	
	sensus.pages.analytics.getTableData();

	var iType = $.address.parameter('ty');

	
	sensus.pages.analytics.analyticsTable = $('#analytics-table').dataTable(sensus.widgets.datatable.setTable({
		id             	: "analytics-table",
		sAjaxSource		: sensus.settings.searchUrl,
		customColumns  	: "analyticslist",
		aColumns       	: sensus.pages.analytics.aCustomColumns,
         oSettings     : {
        	 oRequest      : inquiryAnalyticsRequest,
			 fnRequest     : function() {},
			 sDefaultSort  : 'desc',
			 sResponseObj  : 'groups',
			 iDefaultCol   : 1,
			 iActiveCol    : 1
         },
         fnRowCallback : function(nRow, aData, iDisplayIndex, col) {

			// Need know what table is it, to show the datas according the columns.
			var sAnalyticsTypeEnum = sensus.widgets.datatable.fnGetEnumValue('AnalyticsTypeEnum', $.address.parameter('ty'));

			// Parse string in a jSON object.
			oData = $.parseJSON(aData[col.ANALYTICS_GROUP_TOTAL]);

			// This case the column that have a datas to be parse is "Conservation Carbon Credits"
			if (sAnalyticsTypeEnum == "CONSERVATION_CARBON_CREDITS") {
			
				oData = $.parseJSON(aData[col.ANALYTICS_GROUP_CREDITS_CREATED]);

			}

			// If not have data, do nothing below
			if (!oData) {
				return;
			}
			// Figure out what kind of table is it and put the data on the column.

			for(var i=0; i<oData.length; i++){

				var sCol = 'ANALYTICS_GROUP_'+oData[i].description.toUpperCase().replace(/ /g, '_').trim();
				
				if(iType == '4'){
				
					oData[i].value = parseFloat(oData[i].value) / 1000;

				}
								
				if((iType == '5') && (sCol == 'ANALYTICS_GROUP_TOTAL')){
				
					if (oData[i].value) {
						oData[i].value = oData[i].value.toFixed(2) + '%';
					}

				}
				
				if((iType == '6') && (sCol == 'ANALYTICS_GROUP_ENERGY_SAVED') && (oData[i].value > 0)){
				
					var fValue = parseFloat(oData[i].value) / 1000;
					oData[i].value = fValue.toFixed(3);
  
				}
				
				$("td:eq("+ col[sCol] +")", nRow).text(oData[i].value);
			
			}
			
         }, 
		 fnDrawCallback : function(){
		 
			$('#analytics-table #id').hide();
			$('th:visible', '#analytics-table').each(function(){
			
				var oThat = $(this);
				var sEnumValue = oThat.attr('id').toUpperCase();
				var sId =$.map(sensus.settings.enums.AnalyticsGroupOrderByEnum, function(e, i) { if(e === sEnumValue) { return i }; })[0];
				oThat.children('span').attr('id', sId);

			});
		 
		 }

	}));
	
	// Load the List of Groups
	$.ajax({
		url          : sensus.settings.searchUrl,
		dataType     : 'json',
		contentType  : "application/json; charset=utf-8",
		type         : "POST",
		async        : false,
		data        : $.toJSON( { 'inquiryAnalyticsRequest': new inquiryAnalyticsRequest() }),				
		success      : function(data) {

			var sGroups = '';
		
			for (var i = 0; i < data.groups.length; i++) {

				sGroups += "<option value='" + data.groups[i].id + "'>" + data.groups[i].name + "</option>";
			
			}
			
			$('#group_select').append(sGroups);
			
		}			
	});	
	
	oGroupSelInput.val($('#group_select').find('option:first').text());

	// Initialize History
	sensus.pages.analytics.setPageState($.address.queryString());
	
	// Analytics bar
	$("a", $("#analytics-bar")).click(function(e) {
		e.preventDefault();
		
		$('#datepicker1').val($.address.parameter('di'));
		$('#datepicker2').val($.address.parameter('de'));

		// BEGIN - Change type of the table on URL
		var sType = $(this).attr("id").substring(3,4);
		$.address.parameter("ty", sType);
		// BEGIN - Change type of the table on URL

		// BEGIN - Change Active Class between buttons
		$(".active", $("#analytics-bar")).removeClass("active");
		$(this).addClass("active");
		// END - Change Active Class between buttons
		iType = $.address.parameter('ty');
		sensus.pages.analytics.search();
		$.when(sensus.pages.analytics.getTableData()).done(function() {
		
			//sensus.widgets.datatable.rebuild(sensus.pages.analytics.analyticsTable);
			
		});

	});	
});
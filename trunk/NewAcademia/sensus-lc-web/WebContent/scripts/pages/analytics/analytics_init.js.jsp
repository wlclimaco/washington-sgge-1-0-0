<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
/**
 * @namespace sensus.pages.analytics
 * @description The init namespace for the Analytics Page.
 * @fileoverview Initializes the Analytics page.
 * @author Raphael Constantino
 *
 */
$(document).ready(function() {

	<c:choose>
		<c:when test="${not empty refresh}">
			sensus.pages.analytics.bPreload = false;
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${empty response_table}">
					sensus.pages.analytics.oPreLoadResponseTable = null;
				</c:when>
				<c:otherwise>
					sensus.pages.analytics.oPreLoadResponseTable = ${response_table};

				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${empty response}">
					sensus.pages.analytics.oPreLoadResponseGroup = null;
			    </c:when>
			    <c:otherwise>
			    	sensus.pages.analytics.oPreLoadResponseGroup = ${response};
			    </c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${empty response_chart}">
					sensus.pages.analytics.oPreLoadResponseChart = null;
				</c:when>
				<c:otherwise>
					sensus.pages.analytics.oPreLoadResponseChart = ${response_chart};
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>



	//Verification the status of "Eco Mode" to display the Eco-Mode tab and Carbon Credits  tab
	if(sensus.settings.userContext.tenant.ecoModeDisable == false)
	{
		$('#analytics-bar ul.menu > li[class=hide]').show();
	}

	//Add default sort
	if(!$.address.parameter('sort'))
	{
		$.address.parameter('sort', 'total|asc|');
	}

	// Here set a active menu acord URL
	$(".active", $("#analytics-bar")).removeClass("active").end().find("#bar" + $.address.parameter("ty")).addClass("active");

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

	oActive = ($.address.parameter("dt").length > 0 && $.address.parameter("dt") != '0') ? $("#" + $.address.parameter("dt"), oDateTagBox)  : $("#1w", oDateTagBox);

	if( $.address.parameter("dt") != '0')
	{
		oActive.addClass("active");
	}
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

			var sMask         = sensus.settings.DATE_FORMAT,
				sValDate1     = $("#analytics-filter-date #datepicker1").val(),
				sValDate2     = $("#analytics-filter-date #datepicker2").val(),
				sExistsDate1  = sensus.pages.analytics.existsDate(sValDate1, "1", sMask),
				sExistsDate2  = sensus.pages.analytics.existsDate(sValDate2, "2", sMask);

			$.address.parameter('dt','0');
			if (sExistsDate1 && sExistsDate2)
			{
				var bTodayDate1    = sensus.pages.analytics.isTodayDate(sValDate1,"1"),
					bTodayDate2    = sensus.pages.analytics.isTodayDate(sValDate2,"2"),
					bGreaterDate1  = sensus.pages.analytics.isGreaterDate(sValDate1,"1", sMask),
					bValidDate1    = sensus.pages.analytics.isValidDate(sValDate1,"1", sMask),
					bValidDate2    = sensus.pages.analytics.isValidDate(sValDate2,"2", sMask);

				if (bValidDate1 && bValidDate2 && bTodayDate1 && bTodayDate2 && bGreaterDate1 && status)
				{
					sensus.pages.analytics.filterAnalyticsDateCall();
					sensus.pages.analytics.getTableData();
				}
			}

			$.sc.stopProgressBar(null, true);
		}

	});
	$.address.parameter("chart","normal");
	$("#menu-chart").find("a").click(function(event){
		event.preventDefault();
		var oChartType = $(this);
		var sId = oChartType.prop("id");
		if (oChartType.hasClass("icon-chart-"+sId))
		{
			var sSiblingId = oChartType.siblings().prop("id");
			$.address.parameter("chart",sId);
			oChartType.removeClass("icon-chart-"+sId).addClass("icon-chart-"+sId+"-on");
			oChartType.siblings().removeClass("icon-chart-"+sSiblingId+"-on").addClass("icon-chart-"+sSiblingId);
			sensus.pages.analytics.fillChartParameters();
		}

	});

	sensus.pages.analytics.buildHash();

	sensus.pages.analytics.getTableData();

	$('#chart_type_input').width('84');

	var iType = $.address.parameter('ty');

	sensus.pages.analytics.analyticsTable = $('#analytics-table').dataTable(sensus.widgets.datatable.setTable({
		id             	 : "analytics-table",
		sAjaxSource		 : 'api/analytics/fetch',
		oPreLoadResponse : sensus.pages.analytics.oPreLoadResponseTable,
		bPreLoad 		 : sensus.pages.analytics.bPreload,
		customColumns  	 : "analyticslist",
		aColumns       	 : sensus.pages.analytics.aCustomColumns,
        oSettings     	 : {
        	 oRequest     : inquiryAnalyticsRequest,
			 fnRequest    : function() {},
			 sDefaultSort : 'desc',
			 sResponseObj : 'groups',
			 iDefaultCol  : 1,
			 iActiveCol   : 1
         },
         fnRowCallback : function(nRow, aData, iDisplayIndex, col) {

			// Need know what table is it, to show the datas according the columns.
			var sAnalyticsTypeEnum = sensus.widgets.datatable.fnGetEnumValue('AnalyticsTypeEnum', $.address.parameter('ty'));

			// Parse string in a jSON object.
			oData = $.parseJSON(aData[col.ANALYTICS_GROUP_TOTAL]);

			// This case the column that have a datas to be parse is "Conservation Carbon Credits"
			if (sAnalyticsTypeEnum == "CONSERVATION_CARBON_CREDITS")
			{
				oData = $.parseJSON(aData[col.ANALYTICS_GROUP_CREDITS_CREATED]);
			}

			// If not have data, do nothing below
			if (!oData)
			{
				return;
			}

			// Figure out what kind of table is it and put the data on the column.
			for(var i=0; i<oData.length; i++)
			{
				var sCol = 'ANALYTICS_GROUP_'+oData[i].description.toUpperCase().replace(/ /g, '_').trim();

				if(iType == '4')
				{
					oData[i].value = $.convertionNumber(oData[i].value,false,0).dvalueConverter;
				}

				if((iType == '5') && oData[i].value)
				{
					if (sCol == 'ANALYTICS_GROUP_TOTAL')
					{
						oData[i].value = oData[i].value.toFixed(2) + '%';
					}

					if (sCol == 'ANALYTICS_GROUP_BASELINE_CONSUMPTION')
					{
						oData[i].value = oData[i].value.toFixed(3);
					}
				}

				if((iType == '6') && (sCol == 'ANALYTICS_GROUP_ENERGY_SAVED') && (oData[i].value > 0))
				{
					var fValue = parseFloat(oData[i].value);
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

	var request = new analyticsRequest();

	var fnCallBack = function(data)
	{
		var sGroups = '';
		for (var i = 0; i < data.analyticsGroups.length; i++)
		{
			sGroups += "<option value='" + data.analyticsGroups[i].id + "'>" + data.analyticsGroups[i].name + "</option>";
		}

		$('#group_select').append(sGroups);

	};
	if(sensus.pages.analytics.bPreload && $.sc.isValidPreLoad(sensus.pages.analytics.oPreLoadResponseGroup, true))
	{

		fnCallBack(sensus.pages.analytics.oPreLoadResponseGroup);

	}
	else
	{
		/** Performs the ajax call to search the properties */
		$.sc.getJson('api/analytics/fetchAllGroup', request, false, fnCallBack);
	}
 	oGroupSelInput.val($('#group_select').find('option:first').text());

	// Initialize History
	sensus.pages.analytics.setPageState($.address.queryString());
	$.address.parameter("og",$("#group_select").children().eq("0").val());
	// Analytics bar
	$("a", $("#analytics-bar")).click(function(e) {
		e.preventDefault();

		$('#datepicker1').val($.address.parameter('di'));
		$('#datepicker2').val($.address.parameter('de'));

		// Reset pagination
		$.address.parameter("iDisplayStart", 0);

		// BEGIN - Change type of the table on URL
		var sType = $(this).attr("id").substring(3,4);
		$.address.parameter("ty", sType);
		// END - Change type of the table on URL

		// BEGIN - Change Active Class between buttons
		$(".active", $("#analytics-bar")).removeClass("active");
		$(this).addClass("active");
		// END - Change Active Class between buttons

		iType = sType;
		sensus.pages.analytics.search();
		$.when(sensus.pages.analytics.getTableData()).done(function() {
			sensus.widgets.datatable.rebuild(sensus.pages.analytics.analyticsTable);
		});
	});

	$("#csv").click(function(e) {

		e.preventDefault();
		$.sc.startProgressBar();

		// Get Request populate with current filters
		var request = new inquiryAnalyticsRequest();

		// Default Request Parameters
		if ($.sc.getSortExpression() != false)
		{
			request.sortExpressions	= $.sc.getSortExpression();
		}

		var oAnalyticsCSVRequest = new AnalyticsCSVRequest(request);

		oAnalyticsCSVRequest.timezone = request.timezone;

		sensus.util.exportcsv.generateCSV('api/export/generateAnalytics', oAnalyticsCSVRequest);
	});

	$.sc.stopGlobalProgressBar();

});
</script>
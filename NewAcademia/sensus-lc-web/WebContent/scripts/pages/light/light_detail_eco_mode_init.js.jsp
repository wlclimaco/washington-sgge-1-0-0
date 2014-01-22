<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager')">
<script type="text/javascript">

/**
 * @fileoverview Initializes the SmartPoint Detail History page.
 * @author
 *
 */

$(document).ready(function() {


	<c:choose>
	    <c:when test="${empty response}">
	    	var oPreLoadResponse = null;
	    </c:when>
	    <c:otherwise>
	    	var oPreLoadResponse = ${response};
	    </c:otherwise>
	</c:choose>

	<c:choose>
	    <c:when test="${empty chart_response}">
			sensus.pages.smartpoint.detail.ecoMode.oPreLoadChartResponse = null;
	    </c:when>
	    <c:otherwise>
			sensus.pages.smartpoint.detail.ecoMode.oPreLoadChartResponse = ${chart_response};
	    </c:otherwise>
	</c:choose>

	sensus.util.page.initMessaging();

	if(!$.address.parameter('sort'))
	{
		$.address.parameter('sort', 'consumption_day|desc|');
	}

	sensus.pages.smartpoint.detail.ecoMode.header.init();

	$('#load').on('click', '#edit-eco-mode', function(e){

		e.preventDefault();
		$.sc.launchActionDialog("#edit-eco-mode", sensus.pages.smartpoint.detail.dialogSettings.baselineEcoMode);

	});

	$('#date-tag a').click(function(e){

		e.preventDefault();
		$('.active', '#date-tag').removeClass('active');
		$(this).parent().addClass('active');
		var sDateFilter = $(this).attr('id').split('-')[1];
		var dEnd = new Date();
		var dInitial = new Date();
		if(sDateFilter == 'ytd')
		{
			dInitial.setDate(1);
			dInitial.setMonth(0);
		}
		else
		{
			dInitial.addMonths(-parseInt(sDateFilter));
		}

		$.address.parameter('di', $.sc.dateFormat(dInitial, sensus.settings.dateFormatMask,null,false));
		$.address.parameter('de', $.sc.dateFormat(dEnd, sensus.settings.dateFormatMask,null,false));

		if(sensus.pages.smartpoint.detail.ecoModeTable)
		{
			sensus.widgets.datatable.reloadTable(sensus.pages.smartpoint.detail.ecoModeTable);
			sensus.pages.smartpoint.detail.ecoMode.chart.init();
		}
	});

	$('#datetage-1').click();



	/** * jQuery dataTable setup ** */
	sensus.pages.smartpoint.detail.ecoModeTable = $('#ecomode-table').dataTable(sensus.widgets.datatable.setTable({

		id : "ecomode-table",
		sAjaxSource : "api/ecomode/fetch",
		bPreLoad : true,
		oPreLoadResponse : oPreLoadResponse,
		aColumns : [
			{sId: "ecoDate",         sWidth : "5%",   bSortable : true  },
			{sId: "ecoMeasured",     sWidth : "5%",   bSortable : true  },
			{sId: "ecoBaseLine",	 sWidth : "5%",   bSortable : true },
			{sId: "ecoMode",      	 sWidth : "5%",   bSortable : true  }
		],

		oSettings : {
			oRequest 		: inquiryEcoModeRequest,
			fnRequest 		: function() { },
			aColumns      : ['day[fn(sensus.pages.smartpoint.detail.ecoMode.data.processEcoModeDate)]','consumption[fn(sensus.pages.smartpoint.detail.ecoMode.data.processConsumption)]','ecomodeBaseline[fn(sensus.pages.smartpoint.detail.ecoMode.data.processBaseline)]','ecoMode'],
			sResponseObj  	: 'lightConsumptions',
			bCheckbox     	: false,
			iDefaultCol   	: 0,
			sDefaultSort  	: "desc",
			bSelection    	: false

		},

		fnRowCallback : function(nRow, aData, iDisplayIndex, col) {

			$("td:eq(" +col.ecoMeasured+ ")", nRow).addClass('active');
			var nEcoMode = aData[col.ecoMode];
			if(nEcoMode)
			{
				sEcoMode = nEcoMode.toFixed(2) + '%';
			}
			else
			{
				sEcoMode = '--';
			}
			$("td:eq(" +col.ecoMode+ ")", nRow).html(sEcoMode);
		},

		fnInfoCallback  : function( oSettings, iStart, iEnd, iMax, iTotal, sPre ) {

			$("th:eq(1)").addClass('active');
			var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
			var sReplaced = '--';
			var sReplacedType = '--';
			if(oResponse.light.ecoModeBaseline && oResponse.light.ecoModeBaseline.replacedWattage && oResponse.light.ecoModeBaseline.replacedType)
			{
				var sReplaced = oResponse.light.ecoModeBaseline.replacedWattage.toString();
				var sReplacedType = oResponse.light.ecoModeBaseline.replacedType.toString();
			}
			$('#ecomode_baseline').html($.sc.locale("smartpoint.detail.ecoMode.baseline", sReplaced, sReplacedType));
		}
	}));

	sensus.pages.smartpoint.detail.ecoMode.chart.init();

	/* Export csv */
	$("#csv").click(function() {
		sensus.pages.smartpoint.detail.ecoMode.generateCsvFile();
		return false;

	});

});
</script>
</sec:authorize>
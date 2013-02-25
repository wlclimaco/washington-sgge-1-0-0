<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager')">
<script type="text/javascript">

/**
 * @fileoverview Initializes the SmartPoint Detail History page.
 * @author 
 *
 */

$(document).ready(function() {

	sensus.util.page.initMessaging();
	
	if(!$.address.parameter('sort')){

		$.address.parameter('sort', 'consumption_day|desc|');

	}
	
	sensus.pages.smartpoint.detail.ecoMode.header.init();
	
	$('#load').on('click', '#edit-eco-mode', function(e){

		e.preventDefault();
		sensus.util.actiondialog.launchActionDialog("#edit-eco-mode", sensus.pages.smartpoint.detail.dialogSettings.baselineEcoMode);

	});
	
	$('#date-tag a').click(function(e){
	
		e.preventDefault();
		$('.active', '#date-tag').removeClass('active');
		$(this).parent().addClass('active');
		var sDateFilter = $(this).attr('id').split('-')[1];
		var dDate = new Date();
		if(sDateFilter == 'ytd'){
		
			var dEnd = dDate.toDateString();
			var dInitial = dDate;
			dInitial.setDate(1);
			dInitial.setMonth(0);
			dInitial = new Date(dInitial).toDateString();
		
		} else {
		
			var dEnd = dDate.toDateString();
			var dInitial = new Date(dDate.setDate(dDate.getDate() - parseInt(sDateFilter))).toDateString();
		
		}
		
		$.address.parameter('di', $.date.dateFormat(dInitial, sensus.settings.dateFormatMask,null,false));
		$.address.parameter('de', $.date.dateFormat(dEnd, sensus.settings.dateFormatMask,null,false));
		
		if(sensus.pages.smartpoint.detail.ecoModeTable){
		
			sensus.widgets.datatable.reloadTable(sensus.pages.smartpoint.detail.ecoModeTable);
			sensus.pages.smartpoint.detail.ecoMode.chart.init();
			
		}
	
	});	
	
	$('#datetage-30').click();

	/** * jQuery dataTable setup ** */
	sensus.pages.smartpoint.detail.ecoModeTable = $('#ecomode-table').dataTable(sensus.widgets.datatable.setTable({

		id : "ecomode-table",
		sAjaxSource : "smartpoint/ajax.fetchEcoMode.action",
		aColumns : [
			{sId: "ecoDate",         sWidth : "5%",   bSortable : true  },
			{sId: "ecoMeasured",     sWidth : "5%",   bSortable : true  },
			{sId: "ecoBaseLine",	 sWidth : "5%",   bSortable : true },
			{sId: "ecoMode",      	 sWidth : "5%",   bSortable : true  }
		],

		oSettings : {
			oRequest : inquiryEcoModeRequest,
			fnRequest : function() {

			}, 
			aColumns      : ['day[fn(sensus.pages.smartpoint.detail.ecoMode.data.processEcoModeDate)]','consumption[fn(sensus.pages.smartpoint.detail.ecoMode.data.processConsumption)]','ecomodeBaseline[fn(sensus.pages.smartpoint.detail.ecoMode.data.processBaseline)]','ecoMode'],
			sResponseObj  : 'lightConsumptions',		
			bCheckbox     : false,
			iDefaultCol   : 0,
			sDefaultSort  : "desc",
			bSelection    : false

		},
		
		fnRowCallback : function(nRow, aData, iDisplayIndex, col) {
			
			$("td:eq(" +col.ecoMeasured+ ")", nRow).addClass('active');
			var nEcoMode = aData[col.ecoMode];
			if(nEcoMode){

				sEcoMode = nEcoMode.toFixed(2) + '%';

			
			} else {
			
				sEcoMode = '--';
			
			}
			$("td:eq(" +col.ecoMode+ ")", nRow).html(sEcoMode);

		},

		fnInfoCallback  : function( oSettings, iStart, iEnd, iMax, iTotal, sPre ) {
			
			$("th:eq(1)").addClass('active');
			var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
			var sReplaced = '--';
			var sReplacedType = '--';
			if(oResponse.firstLight.ecoModeBaseline && oResponse.firstLight.ecoModeBaseline.replacedWattage && oResponse.firstLight.ecoModeBaseline.replacedType){

				var sReplaced = oResponse.firstLight.ecoModeBaseline.replacedWattage.toString();
				var sReplacedType = oResponse.firstLight.ecoModeBaseline.replacedType.toString();

			} 
			$('#ecomode_baseline').html(sensus.locale.get("smartpoint.detail.ecoMode.baseline", sReplaced, sReplacedType));

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
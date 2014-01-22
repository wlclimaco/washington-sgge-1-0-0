<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">

/**
 * @fileoverview Defines the core functionality of the Smartpoint Detail History.
 * @author
 */

/**
 * The main namespace for smartpoint detail eco-mode - related functionality.
 */

sensus.pages.smartpoint.detail.ecoMode = {


	oPreLoadChartResponse : null,

	bPreload : true,

	data : {

		reload : function(){

			sensus.commons.modules.bForceReload = true;
			sensus.pages.smartpoint.detail.ecoMode.header.init();
			sensus.pages.smartpoint.detail.ecoMode.chart.init();
			sensus.commons.modules.bForceReload = false;

		},

		processBaseline : function(data){

			sData = '--';
			if(data != null)
			{
				sData = parseFloat(data).toFixed(3);
			}

			return sData;
		},

		processConsumption : function(data){

			sData = '--';
			if(data != null)
			{
				sData = parseFloat(data);
			}

			return sData;
		},

		processEcoMode : function(data){

			sData = '--';
			if(data != null)
			{
				sData = parseFloat(data).toFixed(0) + '%';
			}

			return sData;
		},

		processEcoModeDate : function(data){

			sData = '--';
			if(data)
			{
				sData = $.sc.dateFormat(data, sensus.settings.dateFormatMask,null, false)
			}

			return sData;
		}

	},

	header : {

		fillHeader : function (aData) {

			var sPoleId = aData.poleId;

			if(aData.ecoModeBaseline && aData.ecoModeBaseline.replacedWattage && aData.ecoModeBaseline.replacedType)
			{
				var sReplaced = aData.ecoModeBaseline.replacedWattage;
				var sReplacedType = aData.ecoModeBaseline.replacedType;
				$('.description', '#eco-mode').html($.sc.locale('smartpointdetail.ecoMode.headerFull',sPoleId, sReplaced, sReplacedType));
				$('#no-baseline').remove();
			}
			else
			{
				$('.description', '#eco-mode').html($.sc.locale('smartpointdetail.ecoMode.header',sPoleId));
			}
		},

		init : function(){

			var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();

			sensus.pages.smartpoint.detail.ecoMode.header.fillHeader(oResponse.light);

		}

	},

	chart : {

		fillChart : function(oResponse){

			var oLightResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
			var sReplaced = '--';
			var sReplacedType = '--';
			if(oLightResponse.light.ecoModeBaseline && oLightResponse.light.ecoModeBaseline.replacedWattage && oLightResponse.light.ecoModeBaseline.replacedType)
			{
				sReplaced = oLightResponse.light.ecoModeBaseline.replacedWattage.toString();
				sReplacedType = oLightResponse.light.ecoModeBaseline.replacedType.toString();
			}
			var aConsumptions = oResponse.lightConsumptions;
			var aCategories = [];
			var aSeries = [];
			var aType = [];
			var sFormatDate  = 'M dd';
			for(var i in aConsumptions)
			{
				aCategories.push($.sc.dateFormat(aConsumptions[i].day, sFormatDate,null,false));
				aSeries.push(aConsumptions[i].consumption);
				aType.push(aConsumptions[i].ecomodeBaseline);
			}

			chart = new Highcharts.Chart({
				chart: {
					renderTo: 'container',
					defaultSeriesType: 'column',
					marginTop: 60,
					marginBottom: 50
				},
				title: {
					text: '',
				},
				xAxis: {
					categories: aCategories//['Jul 10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22']
				},
				yAxis: {
					title: {
						text: 'kWh'
					},
					plotLines: [{
						value: 0,
						width: 1,
						color: '#808080'
					}]
				},
				tooltip: {
					formatter: function() {
			                return '<b>'+ this.series.name +'</b><br/>'+
							this.x +': '+ this.y;
					}
				},
				legend: {
					align: 'left',
					verticalAlign: 'top',
					y: 0,
					floating: true,
					borderWidth: 0
				},
				series: [{
					name: $.sc.locale("smartpoint.detail.ecoMode.measured"),
					data: aSeries//[123,123,123,123,123,123,123,123,123,123,123,123]
				},
				{
					type: 'spline',
					color: '#cccccc',
					name: $.sc.locale("smartpoint.detail.ecoMode.chartBaseline", sReplaced, sReplacedType),
					data: aType//[196,196]
				}]
			});
			$('.report-graphic-container').show();
		},

		init : function(){

			var oLightResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();

			if($('#blankslate').is(':visible'))
			{
				$('.report-graphic-container').hide();
			}
			else if(sensus.pages.smartpoint.detail.ecoMode.bPreload && $.sc.isValidPreLoad(sensus.pages.smartpoint.detail.ecoMode.oPreLoadChartResponse, true))
			{
				sensus.pages.smartpoint.detail.ecoMode.chart.fillChart(sensus.pages.smartpoint.detail.ecoMode.oPreLoadChartResponse);
				sensus.pages.smartpoint.detail.ecoMode.bPreload = false;
			}
			else
			{
				var oRequest = new inquiryEcoModeRequest(null, [$.address.parameter('id')], oLightResponse.light);
				oRequest.action = "map";
				$.sc.getJson("api/ecomode/fetch", oRequest, false, sensus.pages.smartpoint.detail.ecoMode.chart.fillChart, null, false);
			}
		}
	},

	generateCsvFile : function() {
		var oLightResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();

		sensus.pages.longrunningprocess.actionUrlAdress = "api/process/updateMonitorProcess";

		if ($.address.parameter('sort'))
		{
			var sSort = $.address.parameter('sort').split('|');
			sSort.pop();
			var sDirection = sSort[1];
			var aSort = [{
				'field'      : sSort[0].toUpperCase(),
				'direction'  : sDirection.substr(0, 1).toUpperCase() + sDirection.substr(1)+'ending'
			}];

		}

		var oRequest = new inquiryEcoModeRequest(null, [$.address.parameter('id')], oLightResponse.light);

		if(aSort)
		{
			oRequest.sortExpressions = aSort;
		}

		var oEcoModeCSVRequest = new EcoModeCSVRequest(oRequest);

		oEcoModeCSVRequest.timezone = oRequest.timezone;


		sensus.util.exportcsv.generateCSV('api/export/generateEcoModeCSV', oEcoModeCSVRequest, true);
	}

};
</script>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">

/**
 * @fileoverview Defines the core functionality of the Smartpoint Detail History.
 * @author 
 */
 
/**
 * The main namespace for smartpoint detail eco-mode - related functionality.
 */
sensus.pages.smartpoint.detail = {};
sensus.pages.smartpoint.detail.ecoMode = {

	data : {
	
		reload : function(){

			sensus.commons.modules.bForceReload = true;
			sensus.pages.smartpoint.detail.ecoMode.header.init();
			sensus.pages.smartpoint.detail.ecoMode.chart.init();
			sensus.commons.modules.bForceReload = false;
		
		},
		
		processBaseline : function(data){
		
			sData = '--';
			if(data){
			
				sData = parseFloat(data/1000).toFixed(3);
			
			} 
			
			return sData;
		
		},
		
		processConsumption : function(data){

			sData = '--';
			if(data){
			
				sData = parseFloat(data/1000).toFixed(3);
			
			} 
			
			return sData;		
		
		
		},
		
		processEcoMode : function(data){
		
			sData = '--';
			if(data){
			
				sData = parseFloat(data).toFixed(0) + '%';
			
			} 
			
			return sData;
		
		},

		processEcoModeDate : function(data){
		
			sData = '--';
			if(data){
			
				sData = $.date.dateFormat(data, sensus.settings.dateFormatMask,null, false)
			
			} 
			
			return sData;
		
		}		
	
	},

	header : {

		fillHeader : function (aData) {

			var sPoleId = $.grep(aData.firstLight.parameters, function(e) { return e.propertyEnum == "POLE_ID"; })[0].value;
			
			if(aData.firstLight.ecoModeBaseline && aData.firstLight.ecoModeBaseline.replacedWattage && aData.firstLight.ecoModeBaseline.replacedType){
			
				var sReplaced = aData.firstLight.ecoModeBaseline.replacedWattage;
				var sReplacedType = aData.firstLight.ecoModeBaseline.replacedType;
				$('.description', '#eco-mode').html(sensus.locale.get('smartpointdetail.ecoMode.headerFull',sPoleId, sReplaced, sReplacedType));
				$('#no-baseline').remove();
			
			} else {
			
				$('.description', '#eco-mode').html(sensus.locale.get('smartpointdetail.ecoMode.header',sPoleId));
			
			}


		},

		init : function(){

			var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
			sensus.pages.smartpoint.detail.ecoMode.header.fillHeader(oResponse);

		}

	},

	chart : {

		fillChart : function(oResponse){

			var oLightResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
			var sReplaced = '--';
			var sReplacedType = '--';
			if(oLightResponse.firstLight.ecoModeBaseline && oLightResponse.firstLight.ecoModeBaseline.replacedWattage && oLightResponse.firstLight.ecoModeBaseline.replacedType){
			
				sReplaced = oLightResponse.firstLight.ecoModeBaseline.replacedWattage.toString();
				sReplacedType = oLightResponse.firstLight.ecoModeBaseline.replacedType.toString();
			
			}
			var aConsumptions = oResponse.lightConsumptions;
			var aCategories = [];
			var aSeries = [];
			var aType = [];
			var sFormatDate  = 'M dd';
			for(var i in aConsumptions) {

				aCategories.push($.date.dateFormat(aConsumptions[i].day, sFormatDate,null,false));
				aSeries.push(aConsumptions[i].consumption/1000);
				aType.push(aConsumptions[i].ecomodeBaseline/1000);

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
					name: 'Measured Consumption',
					data: aSeries//[123,123,123,123,123,123,123,123,123,123,123,123]
				},
				{
					type: 'spline',
					color: '#cccccc',
					name: sensus.locale.get("smartpoint.detail.ecoMode.chartBaseline", sReplaced, sReplacedType),
					data: aType//[196,196]
				}]
			});			
		},
		
		init : function(){
		
			if($('#blankslate').is(':visible')){
			
				$('.report-graphic-container').hide();
			
			} else {
			
				var oRequest = { 'inquiryEcoModeRequest' : new inquiryEcoModeRequest(null, [$.address.parameter('id')])};
				$.ajaxValidator.fnDoCall("smartpoint/ajax.fetchEcoModeChart.action", oRequest, false, sensus.pages.smartpoint.detail.ecoMode.chart.fillChart, null, false);
			
			}
		
		}
	
	},
	
	generateCsvFile : function() {
		
		sensus.pages.longrunningprocess.actionUrlAdress = "process/updateMonitorProcess.action";
		
		if ($.address.parameter('sort')) {

			var sSort = $.address.parameter('sort').split('|');
			sSort.pop();
			var sDirection = sSort[1];
			var aSort = [{
				'field'      : sSort[0],
				'direction'  : sDirection.substr(0, 1).toUpperCase() + sDirection.substr(1)+'ending'
			}];

		}
		
		var data = { 'inquiryEcoModeRequest' : new inquiryEcoModeRequest(null, [$.address.parameter('id')]) };
		if(aSort){

			data.inquiryEcoModeRequest.sortExpressions = aSort;

		}
		//sensus.util.exportcsv.bReload = true;
		sensus.util.exportcsv.generateCSV('smartpoint/ajax.insertProcessEcoMode.action','smartpoint/generateFileEcoMode.action',data,true);

	}	

};
</script>
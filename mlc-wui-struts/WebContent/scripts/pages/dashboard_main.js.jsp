<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
/**
 * @namespace sensus.pages.dashboard
 * @description The main namespace for the DashBoard Page.
 */


/**
 * @fileoverview Defines the core functionality of the dashboard page.
 * @author Alexandre Tiveron
 */

/**
 * The main namespace for the Dashboard Page.
 */
sensus.pages.dashboard = {
	
	header : {
		
		fillHeader : function (aColumns) {
			
			for (var iId in aColumns) {

				if (aColumns.hasOwnProperty(iId)) {

					var sId = "";
					var	iValue = "";
					var oIdElement = null;
					
					// Validate Column  
					if ($.ajaxValidator.fnIsNullOrUndefined(aColumns[iId]) && $.ajaxValidator.fnIsNullOrUndefined(aColumns[iId].description)) {
						continue;
					}
					
					// Set "sId"
					sId = aColumns[iId].description.replace(/\s/g,'').toLowerCase();
					
					// Set "iValue"
					if (!$.ajaxValidator.fnIsNullOrUndefined(aColumns[iId].value)) {
						iValue  = $.convertionNumber(aColumns[iId].value,false,0).dvalueConverter;
					}
					
					oIdElement = $("#" + sId, "#summaryDataTable");

					if ($(oIdElement).attr('id') == 'totaleco-mode' && iValue != '') {
						
						parseInt(iValue).toFixed(0);
						
						var nValue = Math.round(iValue);
												
						var sEcoModeLabel = sensus.locale.get('smartpoint.detail.ecoMode.Economy');
						
						if(nValue >= 15 && nValue <= 50){
						
							sEcoModeLabel = sensus.locale.get('smartpoint.detail.ecoMode.Value');
						
						} else if(nValue < 15){
						
							sEcoModeLabel = sensus.locale.get('smartpoint.detail.ecoMode.Security');
						
						}
						
						$("#performing").html(sEcoModeLabel);
												
					}

					sensus.pages.dashboard.fnValidateValues( $('a', oIdElement), iValue, true);
					
					if(sId == 'totalconsumption' || sId == 'totalcarboncredits') {
					
						// Validate date
						if($.ajaxValidator.fnIsNullOrUndefined(aColumns[iId].date)) {
							continue;
						}
						
						var sDate = Globalize.format(new Date(aColumns[iId].date.replace('T',' ').replace(/-/g,'/').split('.')[0]),'D');
						var sText = sensus.locale.get("dashboard.page.since") + ' ' + sDate;

						oIdElement.siblings('small').text(sText);
					}
					
				}

			}
		},
		
		fnHeaderCallback : function (oResponse) {
			
			var oThis = sensus.pages.dashboard.header;
			var aColumns = oResponse.columns;
			
			// Validate aColumns
			if (!$.ajaxValidator.fnValidArray(aColumns)) {
				return;
			}
			
			oThis.fillHeader(aColumns);
		},
		
		/**
		* Header Init functionality.
		*/
		init : function() {
			$.ajaxValidator.fnDoCall("dashboard/ajax.loadHeaderData.action", {'analyticsRequest' : new analyticsRequest()}, false, this.fnHeaderCallback);
			
		}
	},
	
	lightingControl : {
		
		fillTable : function (oResponse) {

			var aColumns = oResponse.columns;
			var oLightingControlElement = $("#kpi_container");
				
			// Validate aColumns
			if (!$.ajaxValidator.fnValidArray(aColumns)) {
				return;
			}
			
			for (var iId in aColumns) {

				if (aColumns.hasOwnProperty(iId)) {

					var sAnalyticsEnum  = (aColumns[iId].analyticsTypeEnum).toLowerCase();
					var	sDashboardEnum  = (aColumns[iId].dashboardViewTypeEnum).toLowerCase();
					
					// Alarm, Warning and Installed
					$('#'+sAnalyticsEnum, oLightingControlElement).text(sensus.locale.get("dashboard.page." + sAnalyticsEnum));
					$('#' + sAnalyticsEnum + '_' + sDashboardEnum, oLightingControlElement).children().text($.convertionNumber(aColumns[iId].value,false,0).dvalueConverter);

					if(sDashboardEnum == 'week' || sDashboardEnum == 'month') {

						var sId = sAnalyticsEnum + '_' + sDashboardEnum;
						
						sensus.pages.dashboard.fnValidateValues( $('#' + sId + '_avg', oLightingControlElement), $.convertionNumber(aColumns[iId].average,false,0).dvalueConverter);
						$('#' + sId + '_trends', oLightingControlElement).text(aColumns[iId].trends);
						sensus.pages.dashboard.fnValidateValues( $('#' + sId + '_change', oLightingControlElement), $.convertionNumber(aColumns[iId].change,false,0).dvalueConverter + '%');
						
						if(parseInt(aColumns[iId].change, 0) < 0){

							$('#' + sId + '_change', oLightingControlElement).prepend('<span class="negative"></span>');

						} else {

							$('#'+ sId + '_change', oLightingControlElement).prepend('<span class="positive"></span>');

						}

					}

				}

			}
			
		},
		
		/**
		 * TODO - Add default value at the first iteration.
		 */
		fnAddDefaultValues : function () {
			//Add zero when field for null
			$('#kpi_container table tbody tr td').each(function(){
			
				if ((!$(this).text().length) && (!$(this).find('span').hasClass('spark'))) {
					
					$(this).text("0");
				
				} else if ((!$(this).text().length) && ($(this).find('span').hasClass('spark'))) {
					
					if ($(this).hasClass('week')) {
					
						$(this).text("0,0,0,0,0,0,0");        
					
					} else {
						
						$(this).text("0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
					
					}

					$(this).sparkline('html', {type: 'line', height: '1em'} );
				}
			});

		},
		
		fnLightingControlCallback : function (oResponse) {
			var oThis = sensus.pages.dashboard.lightingControl;
			
			oThis.fillTable(oResponse);
			oThis.fnAddDefaultValues();
			
			//FPO Sparkline call
			$('.inline-spark').sparkline('html', {type: 'line', height: '1em'} );
		},
		
		init : function () {
			$.ajaxValidator.fnDoCall("dashboard/ajax.loadLightControlTable.action", {'analyticsRequest' : new analyticsRequest()}, false, this.fnLightingControlCallback);
		}
	},
	
	alertsByType : {
	
		aTypes : {},
		
		fnCreateChart : function (aData) {
			//FPO PieChart
			chart = new Highcharts.Chart({
				chart: {
					renderTo: 'container'
				},
				title: {
					text: sensus.locale.get('dashboard.page.alertsByType'),
					align: 'left',
					y: 25,
					style: {
						color: '#666',
						fontSize: '14px'
					}
				},
				plotArea: {
					shadow: null,
					borderWidth: null,
					backgroundColor: null
				},
				tooltip: {
					formatter: function() {
						return '<b>'+ this.point.name +'</b>: '+ this.y +' %';
					}
				},
				plotOptions: {
					pie: {
						allowPointSelect: true,
						cursor: 'pointer',
						/*
						events: {
							click: function(event) {

								var nId = $.map(sensus.settings.enums.StatusExceptionTypeEnum, function(e, i) { if(e === sensus.pages.dashboard.alertsByType.aTypes[event.point.config[0]].toUpperCase()) { return i }; })[0];
							console.log(nId);
								sensus.commons.lib.ajax.do_load($.extend({}, sensus.commons.lib.ajax.param, {
									sUrl : "smartpoint/ajax.list.action?warning_type=" +nId+'|',
									bFilter : true
								}));
							}
						},		
*/						
						dataLabels: {
							enabled: false
						},
						showInLegend: true
					}
				},
				legend: {
					align: "right",
					enabled: true,
					layout: "vertical",
					lineHeight: 16,
					margin: 0,
					verticalAlign: "top"
				},
				series: [{
					type: 'pie',
					name: sensus.locale.get("dashboard.page.alarmsByType"),
					data: aData
				}]
			});
		},
		
		getChartData : function (oResponse) {
			
			var iTotalAlarm = 0;
			var	aData = [];
			
			for (var i in oResponse.alertsByType) {
			
				if (oResponse.alertsByType.hasOwnProperty(i)) {
				
					iTotalAlarm += oResponse.alertsByType[i];
					
				}

			}
			
			for (var i in oResponse.alertsByType) {

				if (oResponse.alertsByType.hasOwnProperty(i)) {

					var iPercent = parseFloat( ((oResponse.alertsByType[i] / iTotalAlarm) * 100).toFixed(2) ); 

					aData.push([
						sensus.locale.get("dashboard.page." + i), 
						iPercent
					]);
					
					sensus.pages.dashboard.alertsByType.aTypes[sensus.locale.get("dashboard.page." + i)]= i;

				}
			}
			
			return aData;
			
		},
		
		fnAlertsByTypeCallback : function (oResponse) {
			// Show BlankSlate if alerts is null
			if ($.isEmptyObject(oResponse.alertsByType)) {
				$("#container #blankslate").show();
				return;
			}
			
			var aData = sensus.pages.dashboard.alertsByType.getChartData(oResponse);
			sensus.pages.dashboard.alertsByType.fnCreateChart(aData);
		},
		
		init : function () {
			$.ajaxValidator.fnDoCall("dashboard/ajax.loadHighchartsData.action", {'analyticsRequest' : new analyticsRequest()}, false, this.fnAlertsByTypeCallback);
		}
	},
	
	tableFailures : {
		
		
		init : function (sId, fnRequest) {
			
			var oDataTableProperties = {
					id           : sId,
					sAjaxSource  : sensus.settings.search + "?t=" + new Date().getTime(),
					aColumns     : [
						{sId: "Pole_ID",   bSortable:false, sWidth : "25%"},
						{sId: "RNI_ID",    bSortable:false, sWidth : "25%"},
						{sId: "Id",        bVisible : false},
						{sId: "Date",      bSortable:false, sWidth : "25%"},
						{sId: "LATITUDE",  bSortable:false, bVisible : false},
						{sId: "LONGITUDE", bSortable:false, bVisible : false},
						{sId: "STATUS",    bSortable:false, bVisible : false}
					],

					oSettings : {
						oRequest  : analyticsRequest,
						fnRequest : fnRequest, 	
						aColumns : ['parameters[propertyEnum=POLE_ID]','rniId','id','currentStatusMessage.date','parameters[propertyEnum=LATITUDE]','parameters[propertyEnum=LONGITUDE]','currentLightStatus'],
						sResponseObj  : 'lights',
						bCheckbox     : false,
						bSelection    : false,
						iMapCol       : 2, 
					},

					fnRowCallback : function(nRow, aData, iDisplayIndex, col) {

						if (aData[col.Date]) {

							$("td:eq(" +col.Date+ ")", nRow).html($.date.dateFormat(aData[col.Date], sensus.settings.dateFormatMask) + ' ' + $.date.dateFormat(aData[col.Date], 'h:i:s.fff A')); 

						}
						$('td:eq(' + col.Pole_ID + ')', nRow).html('<strong class="flexnetid">' + aData[col.Pole_ID] + '</strong>');
						<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support')">
							$('td:eq(' + col.Pole_ID + ')', nRow).html('<a id="flexnetid' + aData[col.Id] + '"class="alist" href="smartpoint/ajax.smartpoint.detail.main.action?id=' + aData[col.Id] + '"><strong>' + aData[col.Pole_ID] + '</strong></a>');
						</sec:authorize>	
					},

					fnDrawCallback : function(setting, col) {

						$('thead th',$(this)).eq(2).addClass('map-col');

					}
				};

			return oDataTableProperties;
		}
	},
	
	fnValidateValues : function(oElement, iValue, isHeader) {
		
		if (isHeader == null) {
		
			isHeader = false;
			
		}
	
		if (iValue == '' || iValue == '%') {
			
			if (isHeader) {
			
				$(oElement).parent().text("--"); 
			
			} else {

				$(oElement).text("--"); 
			
			}			
		
		} else {
		
			$(oElement).text(iValue);
		
		}
		
	}
 }
</script>
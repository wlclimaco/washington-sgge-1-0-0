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

    oPreLoadResponse : null,
    oPreLoadHeaderResponse : null,
    oPreLoadResumeResponse : null,
    oPreLoadAlertsResponse : null,

	header : {

		fillHeader : function (oResponse) {

			var aColumns = oResponse.columns;

			// Validate aColumns
			if (!$.sc.isValidArray(aColumns))
			{
				return;
			}

			for (var iId in aColumns)
			{
				if (aColumns.hasOwnProperty(iId))
				{
					var sId = "";
					var	iValue = "";
					var oIdElement = null;

					// Validate Column
					if ($.sc.isNullOrUndefined(aColumns[iId]) && $.sc.isNullOrUndefined(aColumns[iId].description))
					{
						continue;
					}

					// Set "sId"
					sId = aColumns[iId].description.replace(/\s/g,'').toLowerCase();

					// Set "iValue"
					if (!$.sc.isNullOrUndefined(aColumns[iId].value))
					{
						iValue  = $.convertionNumber(aColumns[iId].value,false,0).dvalueConverter;
					}

					oIdElement = $("#" + sId, "#summaryDataTable");

					if ($(oIdElement).attr('id') == 'totaleco-mode' && iValue != '')
					{

						parseInt(iValue).toFixed(0);

						var nValue = Math.round(iValue);

						var sEcoModeLabel = $.sc.locale('smartpoint.detail.ecoMode.Economy');

						if(nValue >= 15 && nValue <= 50)
						{
							sEcoModeLabel = $.sc.locale('smartpoint.detail.ecoMode.Value');
						}
						else if (nValue < 15)
						{
							sEcoModeLabel = $.sc.locale('smartpoint.detail.ecoMode.Security');
						}

						$("#performing").html(sEcoModeLabel);
					}

					sensus.pages.dashboard.fnValidateValues( $('a', oIdElement), iValue, true);

					if(sId == 'totalconsumption' || sId == 'totalcarboncredits')
					{
						// Validate date
						if($.sc.isNullOrUndefined(aColumns[iId].date))
						{
							continue;
						}

						var sDate = aColumns[iId].date.split('-').splice(0,3);
						sDate = Globalize.format(new Date(sDate[0], sDate[1] - 1, sDate[2]),'D');

						var sText = $.sc.locale("dashboard.page.since") + ' ' + sDate;

						oIdElement.siblings('small').text(sText);

						if(oIdElement.text() != "--")
						{
							var date = new Date();
							var oLinkTag  = oIdElement.find('a');

							if(!oLinkTag.length)
							{
								continue;
							}

							if (new Date().getDate() == 1)
							{
								oLinkTag.attr('href', oLinkTag.attr('href').replace('&dt=0', "&dt=1d"));
							}
							else
							{
								date.setDate(date.getDate()-1);

								oLinkTag.attr('href', oLinkTag.attr('href').replace('&de=', "&de="+$.datepicker.formatDate("mm/dd/yy", date)));

								date.setDate(1);

								oLinkTag.attr('href', oLinkTag.attr('href').replace('&di=', "&di="+$.datepicker.formatDate("mm/dd/yy", date)));
							}

						}
					}
				}
			}
		},

		/**
		* Header Init functionality.
		*/
		init : function() {

			if($.sc.isValidPreLoad(sensus.pages.dashboard.oPreLoadHeaderResponse, true))
			{
				this.fillHeader(sensus.pages.dashboard.oPreLoadHeaderResponse);
			}

		}
	},

	lightingControl : {

		fillTable : function (oResponse) {
			var aColumns = oResponse.columns;
			var oLightingControlElement = $("#kpi_container");

			// Validate aColumns
			if (!$.sc.isValidArray(aColumns))
			{
				return;
			}

			for (var iId in aColumns)
			{
				if (aColumns.hasOwnProperty(iId))
				{
					var sAnalyticsEnum  = (aColumns[iId].analyticsTypeEnum).toLowerCase();
					var	sDashboardEnum  = (aColumns[iId].dashboardViewTypeEnum).toLowerCase();

					// Alarm, Warning and Installed
					$('#'+sAnalyticsEnum, oLightingControlElement).text($.sc.locale("dashboard.page." + sAnalyticsEnum));
					$('#' + sAnalyticsEnum + '_' + sDashboardEnum, oLightingControlElement).children().text($.convertionNumber(aColumns[iId].value,false,0).dvalueConverter);

					if(sDashboardEnum == 'week' || sDashboardEnum == 'month')
					{
						var sId = sAnalyticsEnum + '_' + sDashboardEnum;

						sensus.pages.dashboard.fnValidateValues( $('#' + sId + '_avg', oLightingControlElement), $.convertionNumber(aColumns[iId].average,false,0).dvalueConverter);
						$('#' + sId + '_trends', oLightingControlElement).text(aColumns[iId].trends);
						sensus.pages.dashboard.fnValidateValues( $('#' + sId + '_change', oLightingControlElement), $.convertionNumber(aColumns[iId].change,false,0).dvalueConverter + '%');

						if (sAnalyticsEnum == "lighting_warning"){
							if(parseInt(aColumns[iId].change, 0) < 0)
							{
								$('#'+ sId + '_change', oLightingControlElement).prepend('<span class="positive-alarm-warn"></span>');
							}
							else
							{
								$('#' + sId + '_change', oLightingControlElement).prepend('<span class="negative-alarm-warn"></span>');
							}
						}
						if (sAnalyticsEnum == "lighting_alarm"){
							if(parseInt(aColumns[iId].change, 0) <= 0)
							{
								$('#'+ sId + '_change', oLightingControlElement).prepend('<span class="positive-alarm-warn"></span>');
							}
							else
							{
								$('#' + sId + '_change', oLightingControlElement).prepend('<span class="negative-alarm-warn"></span>');
							}
						}
						if (sAnalyticsEnum == "lighting_installed"){
							if(parseInt(aColumns[iId].change, 0) < 0)
							{
								$('#' + sId + '_change', oLightingControlElement).prepend('<span class="negative"></span>');
							}
							else
							{
								$('#'+ sId + '_change', oLightingControlElement).prepend('<span class="positive"></span>');
							}
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
			$('#kpi_container table tbody tr td').each(function()
			{
				if ((!$(this).text().length) && (!$(this).find('span').hasClass('spark')))
				{
					$(this).text("0");
				}
				else if ((!$(this).text().length) && ($(this).find('span').hasClass('spark')))
				{
					if ($(this).hasClass('week'))
					{
						$(this).text("0,0,0,0,0,0,0");
					}
					else
					{
						$(this).text("0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
					}

					$(this).sparkline('html', {type: 'line', height: '1em'} );
				}
			});
		},

		fnLightingControlLoad : function (oResponse) {
			var oThis = sensus.pages.dashboard.lightingControl;

			oThis.fillTable(oResponse);
			oThis.fnAddDefaultValues();

			//FPO Sparkline call
			$('.inline-spark').sparkline('html', {type: 'line', height: '1em'} );
		},

		init : function () {
			if($.sc.isValidPreLoad(sensus.pages.dashboard.oPreLoadResumeResponse, true))
			{
				this.fnLightingControlLoad(sensus.pages.dashboard.oPreLoadResumeResponse);
			}
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
					text: $.sc.locale('dashboard.page.alertsByType'),
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
					name: $.sc.locale("dashboard.page.alarmsByType"),
					data: aData
				}]
			});
		},

		getChartData : function (oResponse) {

			var iTotalAlarm = 0;
			var	aData = [];

			for (var i in oResponse.alertsByType)
			{
				if (oResponse.alertsByType.hasOwnProperty(i))
				{
					iTotalAlarm += oResponse.alertsByType[i];
				}
			}

			for (var i in oResponse.alertsByType)
			{
				if (oResponse.alertsByType.hasOwnProperty(i))
				{
					var iPercent = parseFloat( ((oResponse.alertsByType[i] / iTotalAlarm) * 100).toFixed(2) );

					aData.push([
						$.sc.locale("dashboard.page." + i),
						iPercent
					]);

					sensus.pages.dashboard.alertsByType.aTypes[$.sc.locale("dashboard.page." + i)]= i;
				}
			}

			return aData;
		},

		fnAlertsByTypeCallback : function (oResponse) {
			// Show BlankSlate if alerts is null
			if ($.isEmptyObject(oResponse.alertsByType))
			{
				$("#container #blankslate").show();
				return;
			}

			var aData = sensus.pages.dashboard.alertsByType.getChartData(oResponse);
			sensus.pages.dashboard.alertsByType.fnCreateChart(aData);
		},

		init : function () {
			if($.sc.isValidPreLoad(sensus.pages.dashboard.oPreLoadAlertsResponse, true))
			{

				this.fnAlertsByTypeCallback(sensus.pages.dashboard.oPreLoadAlertsResponse);
			}
		}
	},

	getLightTimeZone : function(oRadio) {
		return oRadio.location.timeZoneInfo.offsetInHours;
	},

	tableFailures : {

		init : function (sId, fnRequest, responseType)
		{

			var oDataTableProperties = {
					id           : sId,
					bPreLoad : true,
					oPreLoadResponse : responseType,
					sAjaxSource  : "api/dashboard/fetch" + "?t=" + new Date().getTime(),
					aColumns     : [
						{sId: "Pole_ID",   		bSortable:false, sWidth : "25%"},
						{sId: "RNI_ID",    		bSortable:false, sWidth : "25%"},
						{sId: "Id",        		bVisible : false},
						{sId: "Date",      		bSortable:false, sWidth : "25%"},
						{sId: "LATITUDE",  		bSortable:false, bVisible : false},
						{sId: "LONGITUDE", 		bSortable:false, bVisible : false},
						{sId: "ALERTS",    		bSortable:false, bVisible : false},
						{sId: "PRECEDENCE",     bSortable:false, bVisible : false},
						{sId: "LIGHT_TIMEZONE", bSortable:false, bVisible : false}
					],

					oSettings : {
						oRequest  : analyticsRequest,
						fnRequest : fnRequest,
						aColumns : ['poleId','radio.flexNetId','id','lastNotificationHistory.messageDate','radio.location.latitude','radio.location.longitude','lastNotificationHistory.precedenceValue','lastNotificationHistory.precedence','radio[fn(sensus.pages.dashboard.getLightTimeZone)]'],
						sResponseObj  : 'lights',
						bCheckbox     : false,
						bSelection    : false,
						iMapCol       : 2,
					},

					fnRowCallback : function(nRow, aData, iDisplayIndex, col) {

						if (aData[col.Date])
						{
							$("td:eq(" +col.Date+ ")", nRow).html($.sc.dateFormat(aData[col.Date], sensus.settings.DATE_FORMAT + " h:i:s.fff A", null, false, aData[col.LIGHT_TIMEZONE]));
						}
						$('td:eq(' + col.Pole_ID + ')', nRow).html('<strong class="flexnetid">' + aData[col.Pole_ID] + '</strong>');
						<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support')">
							$('td:eq(' + col.Pole_ID + ')', nRow).html('<a id="flexnetid' + aData[col.Id] + '"class="alist" href="lightDetail?id=' + aData[col.Id] + '"><strong>' + aData[col.Pole_ID] + '</strong></a>');
						</sec:authorize>

						var aStatus = aData[8].toLowerCase();
						$(nRow).addClass(aStatus);
					},

					fnDrawCallback : function(setting, col) {

						$('thead th',$(this)).eq(2).addClass('map-col');

					}
				};

			return oDataTableProperties;
		}
	},

	fnValidateValues : function(oElement, iValue, isHeader) {

		if (isHeader == null)
		{
			isHeader = false;
		}

		if (iValue == '' || iValue == '%')
		{
			if (isHeader)
			{
				$(oElement).parent().text("--");
			}
			else
			{
				$(oElement).text("--");
			}

		}
		else
		{
			$(oElement).text(iValue);
		}
	}
 }
</script>
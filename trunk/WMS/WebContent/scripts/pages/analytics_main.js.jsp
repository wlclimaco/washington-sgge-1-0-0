<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
/**
 * @namespace sensus.pages.analytics
 * @description The main namespace for the Analytics Page.
 * @fileoverview Defines the core functionality of the Analytics page.
 * @author Raphael Constantino
 */


/**
 * The main namespace for the Analytics Page.
 */


sensus.pages.analytics = {

		count                : 0,  // Validation datePicker
		iPage                : "", // Validation page
		validation           : 0,  // Validation date
		chartTitle           : "", // Highchart Title name
		historyHash          : "", // Actual hash got from the url
		graphicData          : [], // Object data for highchart
		analyticsType        : "", // Analytics Type
		analyticsDateTag     : "", // Analytics date tag select
		graphicCategories    : [],
		defaultSeriesType    : "", // Highchart graphic type
		secondsCheckFileCsv  : 0,  // Current seconds to check export file
		monthNames           : [   // Array with name of months
              	$.sc.locale('analytics.month.short.jan')
              , $.sc.locale('analytics.month.short.feb')
              , $.sc.locale('analytics.month.short.mar')
              , $.sc.locale('analytics.month.short.apr')
              , $.sc.locale('analytics.month.short.may')
              , $.sc.locale('analytics.month.short.jun')
              , $.sc.locale('analytics.month.short.jul')
              , $.sc.locale('analytics.month.short.aug')
              , $.sc.locale('analytics.month.short.sep')
              , $.sc.locale('analytics.month.short.oct')
              , $.sc.locale('analytics.month.short.nov')
              , $.sc.locale('analytics.month.short.dec')
        ],

		/**
		 * Fill Page Fields
		 *
		 * @parm String action - target of the datas
		 * @returns void
		 */
		fillPageFields : function(action) {

			if (!sensus.pages.analytics.fillPageFields.cols) {

				var analyticsTable = $("#analytics-table");
				sensus.pages.analytics.fillPageFields.cols = $("thead th", analyticsTable);

			}

			$.ajax({
				url      : "analytics/" + action + ".action #yui-main",
				async    : false,
				success  : function(data) {

					var oThead   = $(data).find("thead > *"),
						oHeader  = $(data).find(".content-header > *"),
						sClass   = "";

					$.each(sensus.pages.analytics.fillPageFields.cols, function(i, e) {

						if (i > 0) {
							$(e).find("span").text(oThead.find("th:eq("+ i +")").text());
							sClass = oThead.find("th:eq("+ i +")").attr("class");

							if (!sClass) {

								sClass = "";
							}

							$(e).attr("class", sClass);

						}

					});

					$(".content-header").html(oHeader);

					sensus.pages.analytics.chartTitle = $(data).find("#y-title").val();
				}
			});

		},

		/**
		 * Parse the history hash query string into an object.
		 *
		 * @param the
		 *            history hash query string
		 * @return the hash as object
		 */
		parseHash : function(hash) {

			var state = {
				ty : "",
				g  : "",
				di : "",
				de : "",
				dt : "",
				db : ""
			};

			if (hash && hash != "") {

				hash = hash.split('&');

				for ( var i in hash) {

					hash[i] = jQuery.isFunction(hash[i]) ? "" : hash[i].split('=');

					if (hash[i].length == 2) {

						state[hash[i][0]] = hash[i][1];

						if (hash[i][0] == "ty") {

							sensus.pages.analytics.analyticsType = hash[i][1];

						} else if (hash[i][0] == "dt") {

							sensus.pages.analytics.analyticsDateTag = hash[i][1];

						}

					}

				}

			}

			return state;
		},

		/**
		 * Get Analytics Type
		 *
		 * @returns String sAnalyticsType - type of analytics
		 */
		getAnalyticsType : function() {

			var sAnalyticsType = $.address.parameter("ty");

			if (sAnalyticsType.length == 0) {

				sensus.pages.analytics.analyticsType = "1";

			}

			return sAnalyticsType;
		},

		/**
		 * Build the hash query string to support history.
		 *
		 * @return empty string
		 */
		buildHash : function() {

			$.address.parameter("ty", sensus.pages.analytics.getAnalyticsType());
			$.address.parameter("g" , sensus.pages.analytics.getGroupId());
			$.address.parameter("di", $("#datepicker1").val());
			$.address.parameter("de", $("#datepicker2").val());
			$.address.parameter("dt", $("#actions-options").find(".active").attr("id"));
			$.address.parameter("db", "");

		},

		/**
		 * Get Group Id
		 *
		 * @returns Int nId - Id of the element selected on combobox
		 */
		getGroupId : function() {

			var oGroupSelect  = $("#group_select"),
				sName         = $("#group_select_input").val(),
				nId           = oGroupSelect.find("option:contains('"+ sName +"')").val(),
				aSelected     = oGroupSelect.find("option:contains('All')");

			$.each(aSelected, function(i, e) {

				if (($(e).text() == "All") && (sName == "All")) {

					nId = $(e).attr("value");

				}

			});

			if (nId == undefined || sName == $.sc.locale('analytics.page.allLights')) {

				nId = "0";

			}

			return nId;
		},

		/**
		 * Set Group
		 *
		 * @returns void
		 */
		setGroup : function() {

			var hashObj = sensus.pages.analytics.parseHash(this.historyHash);

			if (hashObj.db) {

				$("#group_select_input").val($.sc.locale("analytics.page.allLights"));

			}

		},

		/**
		 * Set Date Interval
		 * @parm Object e  - element DOM
		 * @parm Int iPage - index of the page
		 * @returns void
		 */
		setDateInterval : function(e, iPage) {

			var oDatepicker1    = $('#datepicker1'),
				oDatepicker2    = $('#datepicker2'),
				iAnalyticsType  = sensus.pages.analytics.getAnalyticsType();

			if (e.length) {

				var oOption    = e.find("a").attr("class"),
					dInitDate  = new Date(),
					dEndDate   = new Date();

				if (oOption == 'ytd') {

					dInitDate.setDate(1);
					dInitDate.setMonth(0);

				} else if (oOption == '90') {

					dInitDate.setDate(1);
					dInitDate.setMonth(dInitDate.getMonth() - 2);

				} else if (oOption == '365') {

					var d = new Date;
					var nYear = d.getFullYear();

					if(nYear % 4 == 0){

						oOption = parseInt(oOption) +1;

					}

					dInitDate.setDate(dInitDate.getDate()-(parseInt(oOption)));

				} else {

					dInitDate.setDate(dInitDate.getDate()-(parseInt(oOption)));

				}

				if ((sensus.pages.analytics.validation == 0) || ($.address.parameter('dv') == null)) {

					if ((iPage==1 || iAnalyticsType == 5 || iAnalyticsType == 6) && ($.address.parameter('dt')!='1d')) {

						//Avoiding subtracting one day if interval date is of type ytd or 3m(90)
						if (oOption != 'ytd' && oOption != '90') {
							dInitDate.setDate((dInitDate.getDate()) - 1);
						}

						dEndDate.setDate((dEndDate.getDate()) - 1);

					}

				} else {

					if (sensus.pages.analytics.count>0) {

						$.address.parameter('dv', 0);
						sensus.pages.analytics.count=0;

					} else {

						sensus.pages.analytics.count++;

					}

				}

				oDatepicker1.datepicker( "setDate" , dInitDate);
				oDatepicker2.datepicker( "setDate" , dEndDate);

			} else {

				if (iAnalyticsType == 5 || iAnalyticsType == 6) {

					var dateFormat = sensus.settings.DATE_FORMAT,
						dtInit     = $.datepicker.parseDate(dateFormat, oDatepicker1.val()),
						dtEnd      = $.datepicker.parseDate(dateFormat, oDatepicker2.val()),
						dt         = new Date();

					dt.setDate((dt.getDate()) - 1);

					var dtYesterday = dt;

					if (dtInit > dtYesterday) {

						oDatepicker1.datepicker( "setDate" , dtYesterday);

					}

					if (dtEnd > dtYesterday) {

						oDatepicker2.datepicker( "setDate" , dtYesterday);

					}

				}

			}

		},

		/**
		 * Re-initialize the page from the hash. This function is currently only a
		 * placeholder until the Analytics page becomes more complex.
		 *
		 * @param hash
		 *            the hash query string
		 */
		setPageState : function(hash) {

			hashObj = sensus.pages.analytics.parseHash(hash);

			$("#analytics-table, #analytics-table-wrapper").show();

			sensus.pages.analytics.buildHash();

			if (sensus.pages.analytics.analyticsTable) {

				sensus.util.page.startProgressBar(null,true);

				if (hash == "") {

					sensus.pages.analytics.searchGraphic("");

				} else {

					sensus.pages.analytics.searchGraphic(hashObj);

				}

				sensus.pages.analytics.fillChartParameters();

			}

			$("#smartpoint-map-container").empty();

		},

		/**
		 * Fill Chart Parameters
		 * @returns void
		 */
		fillChartParameters : function() {

			if (sensus.pages.analytics.graphicData.length > 0) {

				sensus.pages.analytics.highChart.series = sensus.pages.analytics.graphicData;

			} else {

				sensus.pages.analytics.highChart.series = [{
					name : "",
					type : "column",
					data : []
				}];

			}
			sensus.pages.analytics.highChart.xAxis.categories = sensus.pages.analytics.graphicCategories;
			sensus.pages.analytics.highChart.chart.defaultSeriesType = sensus.pages.analytics.defaultSeriesType;
			sensus.pages.analytics.highChart.yAxis.title.text = sensus.pages.analytics.chartTitle;

			var chart = new Highcharts.Chart(sensus.pages.analytics.highChart);

		},

		/**
		 * Get Hash
		 *
		 * @parm String hash - from url
		 * @returns void
		 */
		getHash : function(hash) {

			hashObj = sensus.pages.analytics.parseHash(hash);
			sensus.pages.analytics.historyHash = hash;

		},

		/**
		 * Load SmartPoint records based on search filters. This function is
		 * currently only a placeholder until the Group page becomes more complex.
		 */
		search : function() {
			sensus.pages.analytics.buildHash();
			sensus.pages.analytics.setPageState($.address.queryString());

		},

		/**
		 * Get the Ajax callback url for the Group table. Appends the timestamp to
		 * the main url to avoid caching.
		 *
		 * @return the url for the Group table to use for Ajax Callbacks
		 */
		getAjaxSource : function(hash) {

			return sensus.settings.searchUrl;

		},

		/**
		 * The Search Graphic
		 *
		 * @parm String hash - from url
		 * @returns void
		 */
		searchGraphic : function(hash) {



			var request = new analyticsRequest();

			var aColumns = [];

			var fnCallBack = function(data) {


				sensus.pages.analytics.graphicData = [];

				var categories = [];
				var aSeries = [];
				var aType = [];
				var sFormatDate = 'M dd';

				if($.address.parameter('dt') == '3m' || $.address.parameter('dt') == 'ytd' || $.address.parameter('dt') == '1y'){

					sFormatDate = 'M yy';

				} else if($.address.parameter('dt') == '1d') {

					sFormatDate = 'hh';

				}

				var oControl = {};

				if(data.columns){

					for( c in data.columns) {

						if (data.columns.hasOwnProperty(c)) {

							if($.grep(aSeries, function(e,i){ aPosition = i; return e.name == $.sc.locale(data.columns[c].description); }).length){

								if($.address.parameter('ty') == 5){

									if(data.columns[c].description == "sensus.mlc.ecomode_consumption.baseline"){

										aType.push(data.columns[c].value);

									} else if  (data.columns[c].description == "sensus.mlc.ecomode_consumption.measured"){

										aSeries[oControl[$.sc.locale(data.columns[c].description)]].data.push(parseFloat(data.columns[c].value) / 1000);

									} else {

										aSeries[oControl[$.sc.locale(data.columns[c].description)]].data.push(data.columns[c].value);

									}

								} else {

									if (data.columns[c].description == "sensus.mlc.conservation.energy_saved" || $.address.parameter('ty') == 4 || $.address.parameter('ty') == 5){

										aSeries[oControl[$.sc.locale(data.columns[c].description)]].data.push(parseFloat(data.columns[c].value) / 1000);

									} else {

										aSeries[oControl[$.sc.locale(data.columns[c].description)]].data.push(data.columns[c].value);

									}

								}

							} else {

								var bVisible = true;

								if(data.columns[c].description == 'sensus.mlc.status_subtype.all') {

									bVisible = false;

								}

								if(data.columns[c].description == "sensus.mlc.ecomode_consumption.baseline"){

									aType.push(parseFloat(data.columns[c].value) / 1000);

								} else {

									var nIndex = aSeries.push({
										'name'    : $.sc.locale(data.columns[c].description),
										'data'    : [],
										'type'    : "column",
										'visible' : bVisible
									});

									oControl[$.sc.locale(data.columns[c].description)] = parseInt(nIndex -1 );

									if  (data.columns[c].description == "sensus.mlc.ecomode_consumption.measured" || data.columns[c].description == "sensus.mlc.conservation.energy_saved"){

										aSeries[oControl[$.sc.locale(data.columns[c].description)]].data.push(parseFloat(data.columns[c].value) / 1000);

									} else {

										aSeries[oControl[$.sc.locale(data.columns[c].description)]].data.push(data.columns[c].value);

									}

								}


							}

							var sDate = $.date.dateFormat(data.columns[c].date, sFormatDate, null, false, true);

							if ($.address.parameter('ty') == 5 || $.address.parameter('ty') == 6){

								sDate = $.sc.dateFormat(data.columns[c].date, sFormatDate, null, false);

							}

							if($.inArray(sDate,categories) < 0) {

								categories.push(sDate);

							}

						}

					}

				}

				sensus.pages.analytics.graphicData = aSeries;
				sensus.pages.analytics.graphicCategories = categories;
				if(aType.length){

					var oType = { type: 'spline', color: '#cccccc', name: $.sc.locale("analytics.table.analytics_group_baseline_consumption"), data: aType	};
					sensus.pages.analytics.graphicData.push(oType);

				}


			};

			$.sc.getJson('api/analytics/fetchChart', request, false, fnCallBack);


		},

		/**
		 * The Date Picker Format
		 */
		datePickerFormat : function() {

			return sensus.settings.DATE_FORMAT.toLowerCase();

		},

		/**
		 * Init Highchart
		 */
		highChart   : ({
			chart   : {
				renderTo     : 'container',
				marginTop    : 60,
				marginBottom : 50
			},
			colors  :  [
				'#4572A7',
				'#AA4643',
				'#5dab5a',
				'#80699B',
				'#3D96AE',
				'#DB843D',
				'#92A8CD',
				'#A47D7C',
				'#B5CA92'
			],
			title   : {
				text: ''
			},
			xAxis   : {},
			yAxis   : {
				title : {
					text: ''
				},
				plotLines : [{
					value : 0,
					width : 1,
					color : '#808080'
				}]
			},
			tooltip : {
				formatter : function() {

	                return '<b>'+ this.series.name +'</b><br/>'+
					this.x +': '+ this.y.toFixed(3);

				}

			},

			 series : {

					events : {
						legendItemClick : function(event) {

							var seriesIndex  = this.index,
								series       = this.chart.series,
								current      = series[seriesIndex];

							if (current.name == $.sc.locale("sensus.mlc.status_subtype.all")) {

								$.each(series, function(i, e){

									current.visible ? e.show() : e.hide();

								});

							} else {

								$.each(series, function(i, e){

									if (e.name == $.sc.locale("sensus.mlc.status_subtype.all")){

										e.hide();

									}

								});

							}

						}

					}

			 } ,

			legend : {
				y              : 0.0,
				align          : 'left',
				verticalAlign  : 'top',
				floating       : true,
				borderWidth    : 0

			}
		}),

		/**
		 * Format decimal numbers on graphics
		 *
		 * @param nStr
		 */
		addCommas: function(nStr) {

			nStr += '';

			var x  = nStr.split('.');
			var x1 = x[0];
			var x2 = '';

			if (sensus.pages.analytics.getAnalyticsType() != 1
					&& sensus.pages.analytics.getAnalyticsType() != 2
					&& sensus.pages.analytics.getAnalyticsType() != 3) {

				x2 = (x[1] == undefined) ? x2 = '.' : '.' + x[1];

				for(var i = x2.length; i < 4; i++) {

					x2 = x2 +'0';

				}

			}

			var rgx = /(\d+)(\d{3})/;

			while (rgx.test(x1)) {

				x1 = x1.replace(rgx, '$1' + ',' + '$2');

			}

			return x1 + x2;
		},

		/**
		 * Check if the date informed is valid
		 *
		 * @parm String value  - the date
		 * @parm String n      - A Class to be selection
		 * @parm String mask   - date format to be parse
		 * @returns Boolean true/false
		 */
		isValidDate : function(value, n, mask) {

			if ($.datepicker.parseDate(mask, value) <= new Date()) {

				return true;

			}

			$("#analytics-filter-date #datepicker" + n).validationEngine('showPrompt', $.sc.locale("analytics.page.date.error"), 'red', '', true);
			return false;

		},

		/**
		 * Check if the date informed is today
		 *
		 * @parm String value  - the date
		 * @parm String n      - A Class to be selection
		 * @returns Boolean true/false
		 */
		isTodayDate : function(value, n) {

			var iAnalyticsType  = sensus.pages.analytics.getAnalyticsType(),
				dateFormat      = sensus.settings.DATE_FORMAT.substring(0, 8),
				dt              = new Date(),
				dtYesterday     = dt.setDate((dt.getDate() - 1));

			if ( (iAnalyticsType == 5 || iAnalyticsType == 6)
					&& $.datepicker.parseDate(dateFormat, value) > dtYesterday) {

					$("#analytics-filter-date #datepicker" + n).validationEngine('showPrompt', $.sc.locale("analytics.page.date.yesterdayDate"), 'red', '', true);
					return false;
			}

			return true;

		},

		/**
		 * Check if the date informed is valid, begin date and end date.
		 *
		 * @parm String value  - the date
		 * @parm String n      - A Class to be selection
		 * @parm String mask   - date format to be parse
		 * @returns Boolean true/false
		 */
		isGreaterDate : function(value, n, mask) {

			if ($.datepicker.parseDate(mask, $("#datepicker1").val()) <= $.datepicker.parseDate(mask, $("#datepicker2").val())) {

				return true;
			}

			$("#analytics-filter-date #datepicker" + n).validationEngine('showPrompt', $.sc.locale("analytics.page.date.error.greater"), 'red', '', true);
			return false;

		},

		/**
		 * Check if the date is valid
		 *
		 * @parm String value  - the date
		 * @parm String n      - A Class to be selection
		 * @parm String mask   - date format to be parse
		 * @returns Boolean true/false
		 */
		existsDate : function(value, n, mask) {

			try {

				$.datepicker.parseDate(mask, value);
				return true;

			} catch(e) {

				$("#analytics-filter-date #datepicker" + n).validationEngine('showPrompt', $.sc.locale("analytics.page.date.error"), 'red', '', true);
				return false;

			}

		},

		/**
		 * Filter Analytics Date
		 */
		filterAnalyticsDateCall : function() {

			$("#actions-options").find(".active").removeClass("active");
			sensus.pages.analytics.search();

		},

		/**
 		 * Ajax Call to generate CSV File
		 */
		generateCsvFile : function() {

			sensus.pages.longrunningprocess.actionUrlAdress = "process/updateMonitorProcess.action";
			var data = { 'inquiryAnalyticsRequest' : new inquiryAnalyticsRequest() };
			sensus.util.exportcsv.generateCSV(sensus.settings.insertProcess,sensus.settings.generateFile,data);

		},

		aCustomColumns : [],

		aData : [],

		getTableData : function(){

			var request = new inquiryAnalyticsRequest();

			var aColumns = [];

			var fnCallBack = function(data) {

				if($.sc.isValidArray(data.groups)){

					if(data.groups[0].columns != null && data.groups[0].columns != undefined) {

						var aCol = data.groups[0].columns;
						aColumns.push({sId: "ANALYTICS_GROUP_NAME", sWidth : "5%", bEffectSortable: false,  sName : "0", propertyRequest : "name", sWidth : "5%"});
						for(var i=0; i<aCol.length; i++){

							var sId = aCol[i].description.toUpperCase().replace(/ /g, '_').trim();
							var sProperty = "";
							if(sId == 'TOTAL' || sId == 'CREDITS_CREATED'){

								sProperty = "columns[object]";

							}

							aColumns.push({sId: "ANALYTICS_GROUP_"+sId, sWidth : "5%", bEffectSortable: false,  sName : "0",   propertyRequest : sProperty, sWidth : "5%"});

						}


						aColumns.push({sId: "Id", sWidth : "5%", bVisible : false, bFixed : true,  propertyRequest : "id", sWidth : "5%"});
						sensus.pages.analytics.aCustomColumns = aColumns;

							if(sensus.pages.analytics.analyticsTable){

								sensus.pages.analytics.analyticsTable.data().config.aColumns = aColumns;

								sensus.widgets.datatable.rebuild(sensus.pages.analytics.analyticsTable);

							}


						$('#analytics-table #id').hide();

					}

				} else {

					sensus.pages.analytics.aCustomColumns.push({sId: "Id", sWidth : "5%", bVisible : false, bFixed : true,  propertyRequest : "id", sWidth : "5%"});
					$('#analytics-table').hide();
					$('#blankslate').show();
					sensus.util.page.stopProgressBar(null, true);
				}
			};

			$.sc.getJson('api/analytics/fetch', request, false, fnCallBack);

		}
};
</script>
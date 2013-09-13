<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">

		// @fileoverview Initializes the device detail readings page.
		$(document).ready(function() {

			var oTimeZone = sensus.pages.device.module.util.getTimeZoneMinutes(sensus.pages.device.module.request.get("device"));

			var sUrlTab 	 = "";
			var sBlankslate  = "";
			var sUrlExport 	 = "";
			var aChannelText = [];

			if ($.address.value().contains("intervalReads")) {

				sUrlTab = "/api/deviceop/fetchIntervalRead";
				sBlankslate = "commons.pages.blankslateLoadData";
				sUrlExport = "api/export/generateFileCsvIntervalRead";

			} else if ($.address.value().contains("loadProfiles")) {

				sUrlTab = "/api/deviceop/fetchMeterLoadProfile";
				sBlankslate = "commons.pages.blankslateLoadProfileData";
				sUrlExport = "api/export/generateFileCsvLoadProfile";

			} else if ($.address.value().contains("snapshot")) {

				sUrlTab = "/api/deviceop/fetchSnapshot";
				sBlankslate = "commons.pages.blankslateSnapshot";
				sUrlExport = "api/export/generateFileCsvSnapshot";

			} else if ($.address.value().contains("readData")) {

				var oPreLoadProfile;

				// Update the field last read
				<c:choose>
					<c:when test="${empty loadProfiles}">
						oPreLoadProfile = null;
					</c:when>
					<c:otherwise>
						oPreLoadProfile = ${loadProfiles};
					</c:otherwise>
				</c:choose>

				if (oPreLoadProfile) {

					var oLoadProfiles 	= oPreLoadProfile.loadProfiles[0];
					var $lastRead 		= $("#lastRead strong");
					var aLastReadValue 	= oLoadProfiles.lastReadValue.split(" ");
					var dLastReadTime 	= oLoadProfiles.lastReadTime;

					if (aLastReadValue.length > 1) {
						$lastRead.html(aLastReadValue[0] + "<sup>" + aLastReadValue[1] + "</sup>");
					} else {
						$lastRead.html(aLastReadValue[0]);
					}

					if (dLastReadTime) {
						$lastRead.next().text($.date.dateFormat(dLastReadTime, "MM d, yy at h:ia", oTimeZone));
					}
				}

				sUrlTab = "/api/deviceop/fetchDataRead";
				sBlankslate = "commons.pages.blankslateReadData";
				sUrlExport = "api/export/generateFileCsvReadData";

			} else if ($.address.value().contains("tou")) {

				sUrlTab = "/api/deviceop/fetchTouRead";
				sBlankslate = "commons.pages.blankslateTOU";
				sUrlExport = "api/export/generateFileCsvTouRead";

			}

			if (!$.address.value().contains("tou")) {
				$("#first-column").find("span").text(sensus.locale.get("smartpointdetail.tabs.intervalreads.samplepoint"));
			}

			var sLabel1,
				sLabel2,
				sLabel3,
				sLabel4,
				sLabel5,
				sLabel6,
				sLabel7,
				sLabel8,
				sLabel9,
				sLabel10,
				sLabel11,
				sLabel12,
				sLabel13,
				sLabel14,
				sLabel15,
				sLabel16;

			var oChangeValue = {
				sChannel1 : true,
				sChannel2 : true,
				sChannel3 : true,
				sChannel4 : true,
				sChannel5 : true,
				sChannel6 : true,
				sChannel7 : true,
				sChannel8 : true,
				sChannel9 : true,
				sChannel10 : true,
				sChannel11 : true,
				sChannel12 : true,
				sChannel13 : true,
				sChannel14 : true,
				sChannel15 : true,
				sChannel16 : true
			};

			var sParameterDateStart = "date_start";
			var oDatepicker 		= $("#datepicker1");
			var date = new Date();
			var currentMonth = date.getMonth();
			var currentDate = date.getDate();
			var currentYear = date.getFullYear();
			var dateFormat 			= sensus.settings.dateFormatMask.replace('yyyy','yy');

			oDatepicker.datepicker({

				dateFormat: sensus.settings.dateFormatMask.replace('yyyy', 'yy'),
				maxDate    : new Date(currentYear, currentMonth, currentDate),


				onSelect: function(dateText, inst) {

					$.fn.pageLoader.parameter(sParameterDateStart, dateText);
					sensus.util.module_operation.fnReload(sensus.pages.readings.loadReadingsTable, sensus.settings.appRoot + sUrlTab);

				}

			}).val(function() {

				var sStart = $.address.parameter(sParameterDateStart);

				// if not has date in URL set values
				if (!sStart) {

					sStart = sensus.pages.readings.oDate.getDate($.date.setDateServer(true), oTimeZone);

					$.fn.pageLoader.parameter(sParameterDateStart, sStart);

				}

				return sStart;
			});

			/*
			 * Function: fnGenerateHTML
			 * Purpose:  Generate HTML (DOM) of pagination
			 * Returns:  -
			 * Inputs:   -
			 */
			var fnGenerateHTML = function() {
				 var sDateFormatMask = sensus.settings.dateFormatMask.replace('yyyy', 'yy');
				 var sDateStarts	 = $.address.parameter(sParameterDateStart);
				 var oLi 			 = $('<li id="li-pagination"/>');
				 var oOl 			 = $('<ol id="ol-pagination">');
				 var oUl 			 = $('<ul/>');
				 var oHtml 			 = oLi.append(oOl);

				// If contains value at sDateStarts variable create the pagination
				 if (sDateStarts) {

					var oDateStart 	= $.datepicker.parseDate(sDateFormatMask, sDateStarts);
					var iDay 		= oDateStart;
					var iMonth 		= oDateStart.getMonth() + 1;
					var iYear 		= oDateStart.getFullYear();
					var dDateServer = $.date.setDateServer(true);

					// Apply device timezone
					dDateServer.setMinutes(dDateServer.getMinutes() + (parseInt(oTimeZone.iTZMinutes, 10)));

					// Mask to show date formated
					if (sDateFormatMask === "dd/mm/yy") {
						var sMaskLabelDate = 'M. dd';
					} else {
						var sMaskLabelDate = 'dd. M';
					}

					// Loop to build a buttons
					for (var i = 0; i < 7; i++) {

						var sCurrentDate = iDay;
						var oLi 		 = $('<li/>');
						var sDateStart 	 = sCurrentDate;
						var sToday 		 = new Date(dDateServer.getDate());
						var sYesterday 	 = new Date(dDateServer.getDate());

						sYesterday.setDate(sYesterday.getDate()-1);

						// Check if current date in loop is the date of today
						if ($.datepicker.formatDate(sDateFormatMask, sCurrentDate) === $.datepicker.formatDate(sDateFormatMask, sToday)) {

							oLi.addClass('active').append('<a id="'+$.datepicker.formatDate(sDateFormatMask, sCurrentDate)+'" href="' + $.datepicker.formatDate(sDateFormatMask, sCurrentDate) + '" class="button-pagination url">' + sensus.locale.get("commons.pages.today") + '</a>');

						} else {

							// Check if the date of the URL is a current date, for
							// put the class with active
							if ($.datepicker.formatDate(sDateFormatMask, sCurrentDate) === $.address.parameter(sParameterDateStart)) {

								oLi.addClass('active').append('<a id="'+$.datepicker.formatDate(sDateFormatMask, sCurrentDate)+'" href="' + $.datepicker.formatDate(sDateFormatMask, sCurrentDate) + '" class="button-pagination url">' + $.datepicker.formatDate(sMaskLabelDate, sCurrentDate) + '</a>');

							} else {

								oLi.append('<a id="'+$.datepicker.formatDate(sDateFormatMask, sCurrentDate)+'" href="' + $.datepicker.formatDate(sDateFormatMask, sCurrentDate) + '" class="button-pagination url">' + $.datepicker.formatDate(sMaskLabelDate, sCurrentDate) + '</a>');

							}
						}

						// Add new elements in HTML
						oHtml.find('#ol-pagination').prepend(oLi);
						iDay.setDate(iDay.getDate() - 1)
					}

					// Create the button 'back'
					var oBackButton = $('<a class="back button ui-button url ui-widget ui-state-default ui-corner-all ui-button-text-only" href="" role="button" aria-disabled="false" />').click(function(e) {

						e.preventDefault();

						sensus.pages.readings.fnPaginationButtonClick("back", sUrlTab);
					});

					// Create the button 'next'
					var oNextButton = $('<a class="next button ui-button url ui-widget ui-state-default ui-corner-all ui-button-text-only" href="" role="button" aria-disabled="false" />').click(function(e) {

						e.preventDefault();

						sensus.pages.readings.fnPaginationButtonClick("forward", sUrlTab);
					});

					var sDateStart = $.datepicker.parseDate(sDateFormatMask, $.address.parameter(sParameterDateStart));

					var sToday = $.date.setDateServer(true);

					// Apply device timezone
					sToday.setMinutes(sToday.getMinutes() + (parseInt(oTimeZone.iTZMinutes, 10)));

					sToday = $.datepicker.formatDate(sDateFormatMask, sToday);
					sToday = $.datepicker.parseDate(sDateFormatMask, sToday);

					var sYesterday = $.date.setDateServer(true);

					// Apply device timezone
					sYesterday.setMinutes(sYesterday.getMinutes() + (parseInt(oTimeZone.iTZMinutes, 10)));

					sYesterday.setDate(sToday.getDate()-1);

					var sAux = $.date.setDateServer(true);

					// Apply device timezone
					sAux.setMinutes(sAux.getMinutes() + (parseInt(oTimeZone.iTZMinutes, 10)));

					sAux.setDate(sToday.getDate()-2);

					var oPagination = oHtml.find('#ol-pagination');
					oPagination.prepend(oBackButton);

					if ($.datepicker.formatDate(sDateFormatMask, sDateStart) === $.datepicker.formatDate(sDateFormatMask, sToday)) {

						oBackButton.append('<span class="ui-button-text"> « ' + sensus.locale.get("commons.pages.back") + '</span>');
						$('#msg-blankslate-readings').text(sensus.locale.get(sBlankslate, sensus.locale.get('commons.pages.today')));

					} else if ($.datepicker.formatDate(sDateFormatMask,sDateStart)=== $.datepicker.formatDate(sDateFormatMask,sYesterday)) {

						oBackButton.append('<span class="ui-button-text"> « ' + sensus.locale.get("commons.pages.back") + '</span>');
						oPagination.append($('<li><a class="button-pagination url" href="#" id="'+ $.datepicker.formatDate(sDateFormatMask, sToday) +'">'+sensus.locale.get('commons.pages.today')+'</a></li>'));
						$('#msg-blankslate-readings').text(sensus.locale.get(sBlankslate, $.datepicker.formatDate(sDateFormatMask, sDateStart)));
						oPagination.append(oNextButton);
						oNextButton.append('<span class="ui-button-text">' + sensus.locale.get("table.page.next") + ' &raquo; </span>');

					} else {

						oBackButton.append('<span class="ui-button-text"> « ' + sensus.locale.get("commons.pages.back") + '</span>');

						if ($.datepicker.formatDate(sDateFormatMask,sDateStart) !== $.datepicker.formatDate(sDateFormatMask,sAux)) {
							oPagination.append($('<li><a class="button-pagination" href="#" id="">...</a></li>'));
						}

						oPagination.append($('<li><a class="button-pagination url" href="#" id="'+ $.datepicker.formatDate(sDateFormatMask, sYesterday) +'">' + $.datepicker.formatDate(sMaskLabelDate, sYesterday) + '</a></li>'));
						oPagination.append($('<li><a class="button-pagination url" href="#" id="' + $.datepicker.formatDate(sDateFormatMask, sToday) +'">Today</a></li>'));
						$('#msg-blankslate-readings').text(sensus.locale.get(sBlankslate, $.datepicker.formatDate(sDateFormatMask, sDateStart)));
						oPagination.append(oNextButton);
						oNextButton.append('<span class="ui-button-text">' + sensus.locale.get("table.page.next") + ' &raquo; </span>');
					}
				 }

				$(".table-footer").find(".paging").removeAttr("style");

				return oUl.append(oHtml);
			};

			$('#reading-table').css({"display" : "none !important"});

			<c:choose>
				<c:when test="${empty response}">
					var oPreLoadResponse = null;
				</c:when>
				<c:otherwise>
					var oPreLoadResponse = ${response};
				</c:otherwise>
			</c:choose>

			/*
			 * jQuery dataTable setup
			 *
			 * Data Table for Readings
			 */
			sensus.pages.readings.loadReadingsTable = $('#reading-table').dataTable(sensus.widgets.datatable.setTable({
				id : "reading-table",
				aaData : sensus.pages.readings.aDataTable(oPreLoadResponse, sensus.settings.appRoot + sUrlTab),

				// Scrolling horizontal
				sScrollX : "100%",
				sScrollXInner : sensus.pages.readings.sScrollInner,

				aoColumns : [{ "sWidth": "5%" },{ "sWidth": "5%" },{ "sWidth": "5%" },{ "sWidth": "5%" },{ "sWidth": "5%" },{ "sWidth": "5%" },{ "sWidth": "5%" },{ "sWidth": "5%" },
				  			 { "sWidth": "5%" },{ "sWidth": "5%" },{ "sWidth": "5%" },{ "sWidth": "5%" },{ "sWidth": "5%" },{ "sWidth": "5%" },{ "sWidth": "5%" },{ "sWidth": "5%" },
				  			 { "sWidth": "5%" },{ "sWidth": "5%" },{ "sWidth": "5%" },{ "sWidth": "5%" },{ "sWidth": "5%" },{ "sWidth": "5%" },{ "sWidth": "5%" },{ "sWidth": "5%" },
				  			 { "sWidth": "5%" },{ "sWidth": "5%" },{ "sWidth": "5%" },{ "sWidth": "5%" },{ "sWidth": "5%" },{ "sWidth": "5%" },{ "sWidth": "5%" },{ "sWidth": "5%" },
				  			 { "sWidth": "5%" },{ "sWidth": "5%" }
				],

				aColumns : [ { sId : "samplePoint", 	bSortable : false, bVisible : false},
							 { sId : "channelOne",  	bSortable : false, bVisible : false},
							 { sId : "labelOne", 		bSortable : false, bVisible : false},
							 { sId : "channelTwo", 		bSortable : false, bVisible : false},
							 { sId : "labelTwo", 		bSortable : false, bVisible : false},
							 { sId : "channelThree",  	bSortable : false, bVisible : false},
							 { sId : "labelThree", 		bSortable : false, bVisible : false},
							 { sId : "channelFour", 	bSortable : false, bVisible : false},
							 { sId : "labelFour", 		bSortable : false, bVisible : false},
							 { sId : "channelFive", 	bSortable : false, bVisible : false},
							 { sId : "labelFive", 		bSortable : false, bVisible : false},
							 { sId : "channelSix", 		bSortable : false, bVisible : false},
							 { sId : "labelSix", 		bSortable : false, bVisible : false},
							 { sId : "channelSeven", 	bSortable : false, bVisible : false},
							 { sId : "labelSeven", 		bSortable : false, bVisible : false},
							 { sId : "channelEight", 	bSortable : false, bVisible : false},
							 { sId : "labelEight", 		bSortable : false, bVisible : false},
							 { sId : "channelNine", 	bSortable : false, bVisible : false},
							 { sId : "labelNine", 		bSortable : false, bVisible : false},
							 { sId : "channelTen", 		bSortable : false, bVisible : false},
							 { sId : "labelTen", 		bSortable : false, bVisible : false},
							 { sId : "channelEleven",   bSortable : false, bVisible : false},
							 { sId : "labelEleven",		bSortable : false, bVisible : false},
							 { sId : "channelTwelve",   bSortable : false, bVisible : false},
							 { sId : "labelTwelve",		bSortable : false, bVisible : false},
							 { sId : "channelThirteen", bSortable : false, bVisible : false},
							 { sId : "labelThirteen",   bSortable : false, bVisible : false},
							 { sId : "channelFourteen", bSortable : false, bVisible : false},
							 { sId : "labelFourteen",   bSortable : false, bVisible : false},
							 { sId : "channelFifteen",  bSortable : false, bVisible : false},
							 { sId : "labelFifteen",   	bSortable : false, bVisible : false},
							 { sId : "channelSixteen", 	bSortable : false, bVisible : false},
							 { sId : "labelSixteen", 	bSortable : false, bVisible : false},
							 { sId : "orderDate", 		bVisible : false}
						],
				oSettings : {
					iDefaultCol : 0,
					order : "orderDate",
					iDisplayLength: -1
				},
				/**
				 * @param nRow
				 * 			number, row number
				 * @param aData
				 * 			array, data array
				 * @param iDisplayIndex
				 * 			integer, display index
				 * @param objColumn
				 * 			object, column object
				 */
				fnRowCallback : function(nRow, aData, iDisplayIndex, objColumn) {

					if ((aData[objColumn.labelOne] != undefined)) {
						oChangeValue.sChannel1 = false;
						sLabel1 = aData[objColumn.labelOne];
					}

					if ((aData[objColumn.labelTwo] != undefined)) {
						oChangeValue.sChannel2 = false;
						sLabel2 = aData[objColumn.labelTwo];
					}

					if ((aData[objColumn.labelThree] != undefined)) {
						oChangeValue.sChannel3 = false;
						sLabel3 = aData[objColumn.labelThree];
					}

					if ((aData[objColumn.labelFour] != undefined)) {
						oChangeValue.sChannel4 = false;
						sLabel4 = aData[objColumn.labelFour];
					}

					if ((aData[objColumn.labelFive] != undefined)) {
						oChangeValue.sChannel5 = false;
						sLabel5 = aData[objColumn.labelFive];
					}

					if ((aData[objColumn.labelSix] != undefined)) {
						oChangeValue.sChannel6 = false;
						sLabel6 = aData[objColumn.labelSix];
					}

					if ((aData[objColumn.labelSeven] != undefined)) {
						oChangeValue.sChannel7 = false;
						sLabel7 = aData[objColumn.labelSeven];
					}

					if ((aData[objColumn.labelEight] != undefined)) {
						oChangeValue.sChannel8 = false;
						sLabel8 = aData[objColumn.labelEight];
					}

					if ((aData[objColumn.labelNine] != undefined)) {
						oChangeValue.sChannel9 = false;
						sLabel9 = aData[objColumn.labelNine];
					}

					if ((aData[objColumn.labelTen] != undefined)) {
						oChangeValue.sChannel10 = false;
						sLabel10 = aData[objColumn.labelTen];
					}

					if ((aData[objColumn.labelEleven] != undefined)) {
						oChangeValue.sChannel11 = false;
						sLabel11 = aData[objColumn.labelEleven];
					}

					if ((aData[objColumn.labelTwelve] != undefined)) {
						oChangeValue.sChannel12 = false;
						sLabel12 = aData[objColumn.labelTwelve];
					}

					if ((aData[objColumn.labelThirteen] != undefined)) {
						oChangeValue.sChannel13 = false;
						sLabel13 = aData[objColumn.labelThirteen];
					}

					if ((aData[objColumn.labelFourteen] != undefined)) {
						oChangeValue.sChannel14 = false;
						sLabel14 = aData[objColumn.labelFourteen];
					}

					if ((aData[objColumn.labelFifteen] != undefined)) {
						oChangeValue.sChannel15 = false;
						sLabel15 = aData[objColumn.labelFifteen];
					}

					if ((aData[objColumn.labelSixteen] != undefined)) {
						oChangeValue.sChannel16 = false;
						sLabel16 = aData[objColumn.labelSixteen];
					}

					if (!$.address.value().contains("snapshot")) {

						sensus.pages.readings.fnLoadData(aData[objColumn.channelOne], objColumn.channelOne, nRow);

						sensus.pages.readings.fnLoadData(aData[objColumn.channelTwo], objColumn.channelTwo, nRow);

						sensus.pages.readings.fnLoadData(aData[objColumn.channelThree], objColumn.channelThree, nRow);

						sensus.pages.readings.fnLoadData(aData[objColumn.channelFour], objColumn.channelFour, nRow);

						sensus.pages.readings.fnLoadData(aData[objColumn.channelFive], objColumn.channelFive, nRow);

						sensus.pages.readings.fnLoadData(aData[objColumn.channelSix], objColumn.channelSix, nRow);

						sensus.pages.readings.fnLoadData(aData[objColumn.channelSeven], objColumn.channelSeven, nRow);

						sensus.pages.readings.fnLoadData(aData[objColumn.channelEight], objColumn.channelEight, nRow);

						sensus.pages.readings.fnLoadData(aData[objColumn.channelNine], objColumn.channelNine, nRow);

						sensus.pages.readings.fnLoadData(aData[objColumn.channelTen], objColumn.channelTen, nRow);

						sensus.pages.readings.fnLoadData(aData[objColumn.channelEleven], objColumn.channelEleven, nRow);

						sensus.pages.readings.fnLoadData(aData[objColumn.channelTwelve], objColumn.channelTwelve, nRow);

						sensus.pages.readings.fnLoadData(aData[objColumn.channelThirteen], objColumn.channelThirteen, nRow);

						sensus.pages.readings.fnLoadData(aData[objColumn.channelFourteen], objColumn.channelFourteen, nRow);

						sensus.pages.readings.fnLoadData(aData[objColumn.channelFifteen], objColumn.channelFifteen, nRow);

						sensus.pages.readings.fnLoadData(aData[objColumn.channelSixteen], objColumn.channelSixteen, nRow);

					}

					// The oTimeZone variable contains the device timezone in minutes.
					if (!$.ajaxValidator.fnIsNullOrUndefined(oTimeZone) || !$.isEmptyObject(oTimeZone)) {

						// Date format with the device timezone
						var aStartTime = $.date.dateFormat(aData[objColumn.orderDate], "h:i A", oTimeZone).split(" ");
						var sOrderDate = $.date.dateFormat(aData[objColumn.orderDate], sensus.settings.dateFormatMask.replace("yyyy", "yy"), oTimeZone);

						$("td:eq("+ objColumn.samplePoint +")", nRow).html(aStartTime[0] +' <small>'+aStartTime[1]+'</small>');

						// Format the order Date to date format
						sOrderDate = $.date.parseDate(sOrderDate, sensus.settings.dateFormatMask.replace("yyyy", "yy")).toString();

						// Set the formated date into hide OrderDate column, the column is used to create the table header
						$("td:eq("+ objColumn.orderDate +")", nRow).html(sOrderDate);

					}
				},

				fnDrawCallback : function() {

					$('.table-footer').show();

					var $rowHeader = $('.row-header h4');
					var oPrevHeadder;

					if ($rowHeader.length > 0) {

						$rowHeader.each(function(i) {

							var sOrderDate = $(this).text();

							if 	(sOrderDate.length) {

								var sTextToday  = "";
								var	sTextButton = "";

								// check if the date selected is the date of today
								if ($.address.parameter(sParameterDateStart)) {

									sTextToday = sensus.locale.get("commons.pages.today") + " : ";
									sTextButton = sensus.locale.get("commons.pages.yesterday");

								} else {
									sTextButton = sensus.locale.get("commons.pages.yesterday");
								}

								var sDateStart 	= $.datepicker.parseDate(sensus.settings.dateFormatMask.replace('yyyy', 'yy'), $.address.parameter(sParameterDateStart));
								var sToday 		= $.date.setDateServer(true);

								sToday.setMinutes(sToday.getMinutes() + (parseInt(oTimeZone.iTZMinutes, 10)));

								var sYesterday 	= new Date(sToday.getDate());

								sYesterday.setDate(sYesterday.getDate()-1);

								if (sDateStart.getDate() === sToday.getDate()) {
									var oButtonBack = '<a aria-disabled="false" role="button" id="back" href="" class="back button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">' + '<span class="ui-button-text"> « ' + sensus.locale.get("commons.pages.yesterday") + '</span></a> ' + sensus.locale.get("commons.pages.today") + " <strong>" + $.date.dateFormat(sOrderDate, 'M dd, yy', oTimeZone) + '</strong>';
								} else if (sDateStart.getDate() === sYesterday.getDate) {
									var oButtonBack = '<a aria-disabled="false" role="button" id="back" href="" class="back button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">' + '<span class="ui-button-text"> « ' + sensus.locale.get("commons.pages.back") + '</span>' + '</a>  <strong>' + $.date.dateFormat(sOrderDate, 'M dd, yy', oTimeZone) + ' </strong><a aria-disabled="false" role="button" id="forward" href="" class="back button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">' + '<span class="ui-button-text" >' + sensus.locale.get("commons.pages.today") + ' &raquo;</span>' + '</a> ';
								} else {
									var oButtonBack = '<a aria-disabled="false" role="button" id="back" href="" class="back button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">' + '<span class="ui-button-text"> « ' + sensus.locale.get("commons.pages.back") + '</span>' + '</a> <strong>' + $.date.dateFormat(sOrderDate, 'M dd, yy', oTimeZone) + ' </strong><a aria-disabled="false" role="button" href="" id="forward" class="back button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">' + '<span class="ui-button-text" >' + sensus.locale.get("table.page.next") + ' &raquo;</span>' + '</a> ';
								}
								$('#menuDay').html(oButtonBack);
							}

							$(this).parent().remove();
							$('paging first').html('');
						});


					}

					// Fill the container of pagination
					$('div.paging').empty().html(fnGenerateHTML());

					// Event click that change selected item
					$('.button-pagination').click(function(e) {

						e.preventDefault();

						var oThis = this;

						// Remove button currently active
						$('table-footer').find('.active').removeClass('active');

						// Set class active in button clicked
						$(oThis).parent('li').addClass('active');

						var sLinkDateUrl = $(oThis).attr('id');

						if (sLinkDateUrl.length > 0) {

							$.address.parameter(sParameterDateStart, sLinkDateUrl);
							oDatepicker.val($.address.parameter(sParameterDateStart));
							sensus.util.module_operation.fnReload(sensus.pages.readings.loadReadingsTable,sensus.settings.appRoot + sUrlTab);

						}
					});

					var visibleColumns = 0;

					if (!$.address.value().contains("tou")) {

						$("#first-column").removeClass("hide");

						$('#reading-table tbody tr > td:nth-child(1)').removeClass("hide");

						visibleColumns = 1;
					}

					visibleColumns += sensus.pages.readings.fnLoadColumns(oChangeValue.sChannel1, $('#channel1'), 2, 1, sLabel1, true);

					visibleColumns += sensus.pages.readings.fnLoadColumns(oChangeValue.sChannel2, $('#channel2'), 4, 3, sLabel2, true);

					visibleColumns += sensus.pages.readings.fnLoadColumns(oChangeValue.sChannel3, $('#channel3'), 6, 5, sLabel3, true);

					visibleColumns += sensus.pages.readings.fnLoadColumns(oChangeValue.sChannel4, $('#channel4'), 8, 7, sLabel4, true);

					visibleColumns += sensus.pages.readings.fnLoadColumns(oChangeValue.sChannel5, $('#channel5'), 10, 9, sLabel5, false);

					visibleColumns += sensus.pages.readings.fnLoadColumns(oChangeValue.sChannel6, $('#channel6'), 12, 11, sLabel6, false);

					visibleColumns += sensus.pages.readings.fnLoadColumns(oChangeValue.sChannel7, $('#channel7'), 14, 13, sLabel7, false);

					visibleColumns += sensus.pages.readings.fnLoadColumns(oChangeValue.sChannel8, $('#channel8'), 16, 15, sLabel8, false);

					visibleColumns += sensus.pages.readings.fnLoadColumns(oChangeValue.sChannel9, $('#channel9'), 18, 17, sLabel9, false);

					visibleColumns += sensus.pages.readings.fnLoadColumns(oChangeValue.sChannel10, $('#channel10'), 20, 19, sLabel10, false);

					visibleColumns += sensus.pages.readings.fnLoadColumns(oChangeValue.sChannel11, $('#channel11'), 22, 21, sLabel11, false);

					visibleColumns += sensus.pages.readings.fnLoadColumns(oChangeValue.sChannel12, $('#channel12'), 24, 23, sLabel12, false);

					visibleColumns += sensus.pages.readings.fnLoadColumns(oChangeValue.sChannel13, $('#channel13'), 26, 25, sLabel13, false);

					visibleColumns += sensus.pages.readings.fnLoadColumns(oChangeValue.sChannel14, $('#channel14'), 28, 27, sLabel14, false);

					visibleColumns += sensus.pages.readings.fnLoadColumns(oChangeValue.sChannel15, $('#channel15'), 30, 29, sLabel15, false);

					visibleColumns += sensus.pages.readings.fnLoadColumns(oChangeValue.sChannel16, $('#channel16'), 32, 31, sLabel16, false);

					$("table tbody tr.row-header > td:nth-child(1)").attr("colspan", visibleColumns);

					oChangeValue.sChannel1 = true;
					oChangeValue.sChannel2 = true;
					oChangeValue.sChannel3 = true;
					oChangeValue.sChannel4 = true;
					oChangeValue.sChannel5 = true;
					oChangeValue.sChannel6 = true;
					oChangeValue.sChannel7 = true;
					oChangeValue.sChannel8 = true;
					oChangeValue.sChannel9 = true;
					oChangeValue.sChannel10 = true;
					oChangeValue.sChannel11 = true;
					oChangeValue.sChannel12 = true;
					oChangeValue.sChannel13 = true;
					oChangeValue.sChannel14 = true;
					oChangeValue.sChannel15 = true;
					oChangeValue.sChannel16 = true;
				}
			}));



			$('.link-list').on('click', '#back', function(e) {

				e.preventDefault();

				sensus.pages.readings.fnPaginationButtonClick("back", sUrlTab);

				return false;
			});

			$('.link-list').on('click', '#forward', function(e) {

				e.preventDefault();

				sensus.pages.readings.fnPaginationButtonClick("forward", sUrlTab);

				return false;
			});

			// Ajust the columns size when table loading is finished
			sensus.pages.readings.loadReadingsTable.ready(function() {

				$(".dataTables_scroll").show();

				if ($("#reading-table:visible").length) {
					$(".blankslate").hide();
					sensus.pages.readings.loadReadingsTable.fnAdjustColumnSizing();
				} else {
					 $(".dataTables_scroll").hide();
					 $(".blankslate").show();
				}

			});

			// To  Generate the archive
			$("#csv").click(function(e) {

				e.preventDefault();

				sensus.util.page.startProgressBar();

				var sDateStart = $.date.parseDate($.address.parameter('date_start'), sensus.settings.dateFormatMask.replace("yyyy", "yy"))

				// Get Request
				var request = sensus.util.module_operation.createDeviceReadingRequest({
					initialDate 	: sDateStart,
					endDate 		: sDateStart
				});

				sensus.util.exportcsv.generateCSV(sUrlExport, request);
			});



			// hide pagination
			$('.numbers').html("");

			sensus.util.page.stopGlobalProgressBar();



		});
	</script>

</sec:authorize>
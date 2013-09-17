<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
	//head(function() {
		sensus.pages.demandreads = {

			fnLoadDemandReadsTable : function () {

				// Demand Read jQuery Data Table
				sensus.pages.demandreads.demandReadsTable = $('#demand-reads-table').dataTable(sensus.widgets.datatable.setTable({

					id : "demand-reads-table",
					sAjaxSource : "api/deviceop/fetchMeterDemandReads",

					aColumns : [
						{sId: "tier", 	                sWidth : "5%", bSortable : true},
						{sId: "demandReadPeakdemand", 	sWidth : "5%", bSortable : true},
						{sId: "demandReadPeaktime", 	sWidth : "5%", bSortable : true},
						{sId: "demandReadRead", 		sWidth : "2%", bSortable : true},
						{sId: "demandReadLastreset", 	sWidth : "5%", bSortable : true},
						{sId: "demandReadCounter",		sWidth : "5%", bSortable : true},
						{sId: "demandReadDemandUnits", 	sWidth : "5%", bVisible : false},
						{sId: "demandReadModelAction", 	sWidth : "5%", bVisible : false}
					],

					oSettings : {
						oRequest : InquiryDemandReadRequest,
						sortEnum : "PeakDemandOrderByEnum",
						order : "demandReadModelAction",
						fnRequest : sensus.pages.demandreads.fnRequestAction,
						sResponseObj  : 'peakDemands',
						aColumns : ['tier','peakDemand', 'peakTime', 'readingDate', 'resetDate', 'counter', 'demandUnits', 'modelAction'],
						aSelectedParameters : ["demandReadPeakdemand"],
						iDefaultCol : 5,
						sDefaultSort : 'desc'
					},

					fnRowCallback : function(nRow, aData, iDisplayIndex, oColumn) {

						if (!$.ajaxValidator.fnIsNullOrUndefined(aData[oColumn.demandReadPeaktime])) {
							$("td:eq("+ oColumn.demandReadPeaktime +")", nRow).text($.date.dateFormat(aData[oColumn.demandReadPeaktime], sensus.settings.dateFormatMask.replace("yyyy", "yy") + ' h:i:s a').toUpperCase());
						}

						if (!$.ajaxValidator.fnIsNullOrUndefined(aData[oColumn.demandReadRead])) {
							$("td:eq("+ oColumn.demandReadRead +")", nRow).text($.date.dateFormat(aData[oColumn.demandReadRead], sensus.settings.dateFormatMask.replace("yyyy", "yy") + ' h:i:s a').toUpperCase());
						}

						if (!$.ajaxValidator.fnIsNullOrUndefined(aData[oColumn.demandReadLastreset])) {
							$("td:eq("+ oColumn.demandReadLastreset +")", nRow).text($.date.dateFormat(aData[oColumn.demandReadLastreset], sensus.settings.dateFormatMask.replace("yyyy", "yy") + ' h:i:s a').toUpperCase());
						}

						if (!$.ajaxValidator.fnIsNullOrUndefined(aData[oColumn.demandReadPeakdemand]) && !$.ajaxValidator.fnIsNullOrUndefined(aData[oColumn.demandReadDemandUnits])) {
							$("td:eq("+ oColumn.demandReadPeakdemand +")", nRow).html(aData[oColumn.demandReadPeakdemand] + ' ' + aData[oColumn.demandReadDemandUnits]);
						}
					},

					fnDrawCallback : function() {

						$('.paging.first').empty().prepend(sensus.pages.demandreads.fnGenerateHTML());

						// Event click that change selected item
						$('.button-pagination').click(function(e) {

							e.preventDefault();
							sensus.pages.demandreads.demandReadsTable.fnDestroy();
							$('#smartpoint-table thead tr, #smartpoint-table tbody').empty();

							var sLinkDateUrl = $(this).attr('id');
							var sParameterDateStart = "date_start";

							if (sLinkDateUrl.length > 0) {
								$.fn.pageLoader.parameter(sParameterDateStart, sLinkDateUrl);
								$('#select_year_chzn a span').text($(this).text());
								sensus.pages.demandreads.fnLoadDemandReadsTable();
							}
						});
					}

				}));
			},

			fnRequestAction : function () {

				var inquiryDemandReadRequest = new InquiryDemandReadRequest({
					device		: sensus.util.module_operation.createNewDeviceForReadingsRequest(sensus.util.module_operation.fnPrepareDeviceData()),
					initialDate	: sensus.util.page.fnFormatDateFilter($.address.parameter('date_start'), 'date_start', 'startYear'),
					endDate		: sensus.util.page.fnFormatDateFilter($.address.parameter('date_start'), 'date_start', 'endYear')
				});

				return inquiryDemandReadRequest;

			},

			fnGenerateHTML : function () {

				var sParameterDateStart = "date_start";
				var sStart = $.address.parameter(sParameterDateStart)
				var sToday = $.date.setDateServer();
				var oLi = $('<li id="li-pagination"/>');
				var oOl = $('<ol id="ol-pagination">');
				var oUl = $('<ul/>');
				var oHtml = oLi.append(oOl);

				if (sStart) {

					var sDateStarts = $.address.parameter(sParameterDateStart);

					iDay = $.datepicker.parseDate(sensus.settings.dateFormatMask.replace('yyyy', 'yy'), sDateStarts);

					var sCurrentDate = iDay;

					for (var i = 0; i <= 4; i++) {
						oLi = $('<li/>');
						oLi.prepend('<a id="'+$.datepicker.formatDate(sensus.settings.dateFormatMask.replace('yyyy', 'yy'), sCurrentDate)+'" href="' + $.datepicker.formatDate(sensus.settings.dateFormatMask.replace('yyyy', 'yy'), sCurrentDate) + '" class="button-pagination url">' + iDay.getFullYear() + '</a>');

						// Add new elements in HTML
						oHtml.find('#ol-pagination').prepend(oLi);
						iDay.setFullYear(iDay.getFullYear() - 1);
					}

					oHtml.find('#ol-pagination li').last().addClass('active');
				}

				// Create the button 'back'
				var oBackButton = $('<a class="back button ui-button url ui-widget ui-state-default ui-corner-all ui-button-text-only" href="" role="button" aria-disabled="false" />').click(function(e) {
					e.preventDefault();
					sensus.pages.demandreads.fnPaginationButtonClick("back");
				});

				// Create the button 'next'
				var oNextButton = $('<a class="next button ui-button url ui-widget ui-state-default ui-corner-all ui-button-text-only" href="" role="button" aria-disabled="false" />').click(function(e) {
					e.preventDefault();
					sensus.pages.demandreads.fnPaginationButtonClick("forward");
				});

				var sDateStart = $.datepicker.parseDate(sensus.settings.dateFormatMask.replace('yyyy', 'yy'), $.address.parameter(sParameterDateStart));

				var sAux1 = new Date();
				sAux1.setFullYear(sToday.getFullYear()-2);

				var oPagination = oHtml.find('#ol-pagination');
				oPagination.prepend(oBackButton);

				var oDate	   = $.datepicker.formatDate(sensus.settings.dateFormatMask.replace('yyyy', 'yy'),sDateStart);
				var sYesterday = new Date(oDate);

				sYesterday.setFullYear(sYesterday.getFullYear()-1);

				var oDateServer		  = $.date.setDateServer();
				var sLinkTextToday 	  = (oDateServer).getFullYear().toString();
				var sLinkTextPast  	  = sYesterday.getFullYear();
				var sStartDate 	  	  = sDateStart.getFullYear().toString();
				var sBlankslate    	  = "commons.pages.demandReads";
				var oDemandReadHeader = $('.row-header td > h4');

				oDemandReadHeader.html('<strong class="demand-reads-header">' + sStartDate + '</strong>');
				oDemandReadHeader.prepend($('<a class="next button ui-button url ui-widget ui-state-default ui-corner-all ui-button-text-only" href="" role="button" aria-disabled="false" />'));

				oDemandReadHeader.find('a').prepend($('<span class="ui-button-text"> &#171; ' + sLinkTextPast + '</span>')).click(function(e){
					e.preventDefault();
					sensus.pages.demandreads.fnPaginationButtonClick("back");
				});

				oBackButton.append('<span class="ui-button-text"> &#171; ' + sensus.locale.get("commons.pages.back") + '</span>');

				if (oDate === $.datepicker.formatDate(sensus.settings.dateFormatMask.replace('yyyy', 'yy'),sToday)) {

					$('#msg-blankslate-readings').text(sensus.locale.get(sBlankslate, sLinkTextToday));

				} else {

					if (oDate === $.datepicker.formatDate(sensus.settings.dateFormatMask.replace('yyyy', 'yy'),sYesterday)) {

						oPagination.append($('<li><a class="button-pagination url" href="#" id="'+ $.datepicker.formatDate(sensus.settings.dateFormatMask.replace('yyyy', 'yy'), oDateServer) +'">'+sLinkTextToday+'</a></li>'));

					} else {

						if (oDate !== $.datepicker.formatDate(sensus.settings.dateFormatMask.replace('yyyy', 'yy'),sAux1)) {
								oPagination.append($('<li><a class="button-pagination url" href="#" id="' + $.address.parameter('date_start') +'">...</a></li>'));
						}

						oPagination.append($('<li><a class="button-pagination url" href="#" id="'+ $.datepicker.formatDate(sensus.settings.dateFormatMask.replace('yyyy', 'yy'), sYesterday) +'">' + sLinkTextPast + '</a></li>'));
						oPagination.append($('<li><a class="button-pagination url" href="#" id="' + $.datepicker.formatDate(sensus.settings.dateFormatMask.replace('yyyy', 'yy'), oDateServer) +'">'+ sLinkTextToday +'</a></li>'));

					}
					oPagination.append(oNextButton);
					oNextButton.append('<span class="ui-button-text">' + sensus.locale.get("table.page.next") + ' &raquo; </span>');
					$('#msg-blankslate-readings').text(sensus.locale.get(sBlankslate, sStartDate));
				}

				//hide pagination
				$('.numbers').html("");

				$(".table-footer").removeAttr("style");

				return oUl.append(oHtml);
			},

			/*
			* Object: oDate
			* Porpose: Encapsulate all the related functionalities with date
			*/
			oDate : (function() {

				/*
				* Function: _convertDate
				* Purpose: Convert the date to format MM/dd/yy
				* Returns: string: sDate - date converted into format MM/dd/yy
				* Inputs: String: sDate - date to be converted
				*/
				var _convertDate = function(sDate) {

					if (!sDate instanceof Date) {

						sDate = __removeWhiteSpace(sDate) || new Date();
						sDate = new Date(sDate);

					}

					var sMask = sensus.settings.dateFormatMask.replace('yyyy', 'yy');
					return $.datepicker.formatDate(sMask, new Date(sDate));
				};

				/*
				* Function: __removeWhiteSpace
				* Purpose: Remove all white spaces
				* Returns: string: sDate
				* Inputs: string: sDate
				*/
				var __removeWhiteSpace = function(sDate) {
					return sDate && sDate.replace(/\s/g, "");
				};

				return {

					/*
					* Function: getDate
					* Purpose: 	if not pass argument has today date into format MM/dd/yy
					* 			or the date informed in this format
					* Returns: boolean: sDate - date converted on format MM/dd/yy
					* Inputs: string: sDate - date to will be comparative
					*/
					getDate : function(sDate) {
						return _convertDate(sDate);
					},

					/*
					* Function: isToday
					* Purpose: Do comparative
					* Returns: boolean: sDate - date converted into format MM/dd/yy
					* Inputs: string: sDate - date to will be comparative
					*/
					isToday : function(sDate) {
						return (sDate == _convertDate(new Date()));
					},

					/*
					* Function: getTomorrowDate
					* Purpose: Get the date of tomorrow
					* Returns: string: sDate - date converted into format MM/dd/yy
					* Inputs: string: sDate - date to will be comparative
					*/
					getTomorrowDate : function(sDate) {

						sDate = new Date(new Date(sDate).getTime() + (1 * 24 * 60 * 60 * 1000));
						return _convertDate(sDate);

					},

					/*
					 * Function: getMask
					 * Purpose:  Get the format of the mask
					 * Returns:  string : format of MM/dd/yy
					 * Inputs:   -
					 */
					getMask : function() {
						return sensus.settings.dateFormatMask.replace('yyyy','yy');
					},

					/* *
					* Function transforms the first letters of each word typed in UPPER CASE and the rest in Tiny
					*/
					firstLetterCapitalized: function(word){

						word=word.split("");
						var tmp="";
						for(i=0;i<word.length;i++){
								if(word[i-1]){
										if(word[i-1]==" "){word[i]=word[i].replace(word[i],word[i].toUpperCase());}
										else{word[i]=word[i].replace(word[i],word[i].toLowerCase());}
								}
								else{word[i]=word[i].replace(word[i],word[i].toUpperCase());}
								tmp+=word[i];
						}

						return  tmp;
					}

				};

			})(),

			fnPaginationButtonClick : function(sType) {

				var sDateStart 	   = $.address.parameter("date_start");
				var dateEnd 	   = $.datepicker.parseDate(sensus.settings.dateFormatMask.replace('yyyy', 'yy'), sDateStart);
				var dateEndAux 	   = dateEnd;

				if (sType === "back") {
					dateEndAux.setFullYear(dateEndAux.getFullYear()-1);
				} else if (sType === "forward") {
					dateEndAux.setFullYear(dateEndAux.getFullYear()+1);
				}

				$.fn.pageLoader.parameter("date_start", $.datepicker.formatDate(sensus.settings.dateFormatMask.replace('yyyy', 'yy'), dateEndAux));

				sensus.pages.demandreads.demandReadsTable.fnDestroy();
				$('#smartpoint-table thead tr, #smartpoint-table tbody').empty();
				sensus.pages.demandreads.fnLoadDemandReadsTable();
			}
		};
		//}); head
	</script>

</sec:authorize>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
		sensus.pages.readings = {

				sScrollInner : "100%",

				/*
				* Function to fetch readings and setting in sScrollInner variable the percentage according the channels' amount.
				* @Param oPreLoadResponse - Object
				* @Param sUrl - String
				* @Return aData - Array of Array
				*/
				aDataTable : function(oPreLoadResponse, sUrl) {

					var aData = sensus.util.module_operation.fnPreDrawCallback(oPreLoadResponse, sUrl);

					if (!$.sc.isNullOrUndefined(aData) && !$.sc.isNullOrUndefined(aData[0]) && aData[0].length > 0) {

						var aDataFirst = aData[0];

						if (!$.sc.isNullOrUndefined(aDataFirst[11]) && $.sc.isNullOrUndefined(aDataFirst[15])) {
							sensus.pages.readings.sScrollInner = "110%";
						} else if (!$.sc.isNullOrUndefined(aDataFirst[15]) && $.sc.isNullOrUndefined(aDataFirst[19])) {
							sensus.pages.readings.sScrollInner = "120%";
						} else if (!$.sc.isNullOrUndefined(aDataFirst[19]) && $.sc.isNullOrUndefined(aDataFirst[23])) {
							sensus.pages.readings.sScrollInner = "150%";
						} else if (!$.sc.isNullOrUndefined(aDataFirst[23]) && $.sc.isNullOrUndefined(aDataFirst[27])) {
							sensus.pages.readings.sScrollInner = "160%";
						} else if (!$.sc.isNullOrUndefined(aDataFirst[27])) {
							sensus.pages.readings.sScrollInner = "200%";
						}

					}

					return aData;
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
					var _convertDate = function(sDate, oTimeZone) {

						if (!sDate instanceof Date) {

							sDate = __removeWhiteSpace(sDate) || new Date();
							sDate = new Date(sDate);

						}

						var sMask = sensus.settings.dateFormatMask.replace('yyyy', 'yy');

						if (!$.sc.isNullOrUndefined(oTimeZone)) {
							return $.datepicker.formatDate(sMask, new Date(sDate), oTimeZone);
						} else {
							return $.datepicker.formatDate(sMask, new Date(sDate));
						}

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
						getDate : function(sDate, oTimeZone) {
							return _convertDate(sDate, oTimeZone);
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
						firstLetterCapitalized: function(word) {

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

				/*
				* Load the Load Profile table
				*/
				fnLoadData : function(aChannel, iChannel, nRow) {

					if (aChannel != undefined) {

						if (aChannel.contains("TOTAL")) {

							var aValue = aChannel.split("|");

							if (aValue.length > 1) {
								$("td:eq("+ iChannel +")", nRow).html('<span class="read-total">'+aValue[0]+'</span> '+aValue[1]);
							} else {

								if (aValue[0].contains("TOTAL")) {
									$("td:eq("+ iChannel +")", nRow).html('<span class="read-total">'+aValue[0]+'</span> ');
								} else {
									$("td:eq("+ iChannel +")", nRow).html(aValue[0]);
								}

							}
						}
					}

				},

				/** Load the Columns */
				fnLoadColumns : function(bChangeValue, oChannel, iChild, iEq, sLabel, bVisible) {

					var visibleColumns 	= 0;
					var sLabelFormated 	= sLabel;
					var sPipe 			= "|";
					var iHiphenIndex;

					if (sLabelFormated && sLabelFormated.indexOf(sPipe) >= 0) {

						sLabelFormated = sLabel.substr(0, sLabel.indexOf(sPipe));

						iHiphenIndex = sLabelFormated.indexOf("-");

						if (iHiphenIndex != -1) {

							sLabelFormated = sLabelFormated.substr(iHiphenIndex + 1, sLabelFormated.length);
						}
					}

					if (!bVisible) {

						if (bChangeValue == true) {

							oChannel.find("span").css({"display": "none"});
							oChannel.addClass('hide');
							$(".dataTables_scrollBody").find('tr>td:nth-child('+ iChild +')').addClass('hide');

						} else {
							visibleColumns +=1;
							oChannel.removeClass("hide");
							$(".dataTables_scrollBody").find('tr>td:nth-child('+ iChild +')').show().addClass('readings num');
							$(".dataTables_scrollHead").find("th:eq("+ iEq +")").show().find("span").text(sensus.pages.readings.oDate.firstLetterCapitalized(sLabelFormated));
							oChannel.find("span").css({"display": "block"});
						}

					} else {

						oChannel.removeClass("hide");
						$(".dataTables_scrollBody").find('tr>td:nth-child('+ iChild +')').removeClass("hide");
						visibleColumns += 1;

						if (bChangeValue != true) {

							$(".dataTables_scrollBody").find('tr>td:nth-child('+ iChild +')').addClass('readings num');
							$(".dataTables_scrollHead").find("th:eq("+ iEq +")").find("span").text(sensus.pages.readings.oDate.firstLetterCapitalized(sLabelFormated));
							oChannel.find("span").css({"display": "block"});

						} else {

							oChannel.find("span").css({"display": "none"});

						}
					}

					return visibleColumns;
				},

				fnPaginationButtonClick : function(sType, sUrlTab) {

					var sDateStart 		= $.address.parameter("date_start");
					var sDateFormatMask = sensus.settings.dateFormatMask.replace('yyyy', 'yy');
					var dateEnd 		= $.datepicker.parseDate(sDateFormatMask, sDateStart);
					var dateEndAux 		= dateEnd;

					if (sType === "back") {

						dateEndAux.setDate(dateEndAux.getDate()-1);

					} else if (sType === "forward") {

						dateEndAux.setDate(dateEndAux.getDate()+1);

					}

					$.fn.pageLoader.parameter("date_start", $.datepicker.formatDate(sDateFormatMask, dateEndAux));
					$("#datepicker1").val($.datepicker.formatDate(sDateFormatMask, dateEndAux));
					sensus.util.module_operation.fnReload(sensus.pages.readings.loadReadingsTable, sensus.settings.appRoot + sUrlTab);

				},

				fnLoadColumnsText : function(aChannelsText, sFirstColumn) {

					var iChannels = aChannelsText.length;
					var x;

					for (var i = 0; i < iChannels; i++) {

						x = i + 1;
						$("#channel"+ x +"").find("span").html(sensus.locale.get(aChannelsText[i])+"<br><small></small>");

					}

					$("#first-column").find("span").text(sensus.locale.get(sFirstColumn));

				}
		};
	</script>

</sec:authorize>
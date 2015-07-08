$(document).ready(function()
{
		$.pgsi.table = (function()
		{
			/*************
			 * PRIVATE *
			*************/
			/**
			 * Default configuration for common functionality. These can be changed
			 */
			var _oConfig =
			{
				ajax 		:
				{
					bAsync : true,
				},
				common 		:
				{
					sTableFooter 			: "table-footer",
					sBlankslate 			: ".blankslate",
					sLengthContainer 		: ".dataTables_length",
					pageSizeSubmitButtonId 	: "changeDefault",
					pageSizeCheckBox 		: "page-size-checkbox",
					pageSizeKeepButtonId 	: "keepCurrent",
					sActionId 				: "action-dialog",
					sPageSizeValueId 		: "#dialogValuePageSize",
					sNext 					: "next",
					sPrev 					: "previous",
					results 				: "results",
					totalRecordsLabel 		: "label-total-records",
					totalRecordsId 			: "total-records",
					generalTotalId  		: "general-total",
					datePaginationId 		: "ol-pagination",
					tableFooterStamp 		: "stamp",
					selectButtonId 			: "select-button",
					longRunningDialogId		: "message-recent-request",
					checkboxMessage 		: ".message",
					actionsId 				: "actions",
					selectPageId 			: "select-page",
					selectNoneId 			: "select-none",
					selectAllId 			: "select-all",
					checkedCountId 			: "checked-count"

				},
				settings 	:
				{
					aPaginationTypes: ["ellipses", "daily", "monthly", "yearly"],
					aPaginationSize : [25, 50, 100],
					asStripeClasses : [ "", "alt" ]
				},
				messages  	:
				{
					recordsPerPage 		: "table.records.page",
					recordsNone 		: "table.records.none",
					recordsInfo 		: "table.records.info",
					recordsInfoEmpty	: "table.records.infoempty",
					recordsFilter 		: "table.records.filter",
					processing 			: "table.processing",
					next 				: "table.page.next",
					prev 				: "table.page.prev",
					pageSizeDialogTitle : "action.pagesize.title",
					pageSizeSubmitButton: "action.pagesize.submit",
					pageSizeCancelButton: "action.pagesize.cancel",
					resultMatches 		: "table.result.matches",
					resultMatch 		: "table.result.match",
					paginationBack 		: "table.page.back",
					paginationBackArrow : "table.page.back_arrow"
				},
				project 	:
				{
					pageSizeDialogProperty 			: "pageSizeShowDialog",
					pageSizeProperty  				: "PAGE_SIZE",
					pageSizeShowDialogProperty 		: "PAGE_SIZE_SHOW_DIALOG",
					pageSizeUrl 					: "pageSize",
					sToDateUrl 						: "toSearchValue",
					sFromDateUrl 					: "fromSearchValue",
				}
			};

			/** Private Attr */
			var _iCurrentPagination = 0;
			var _oDefaultConfig 	=
			{
				sPaginationType  : _oConfig.settings.aPaginationTypes[0],
				sDom 			 : "tr<'yui-gb " + _oConfig.common.sTableFooter + "'<'yui-u paging first'p><'yui-u numbers'il>><'" + _oConfig.common.tableFooterStamp +"'>",
				bAutoWidth 		 : false,
				bFixedHeader	 : true,
				bServerSide 	 : true,
				bIsDialog 		 : false,
				bParamsOnUrl 	 : true,
				iDisplayLength 	 : 25,
				aLengthMenu 	 : _oConfig.settings.aPaginationSize,
				asStripeClasses  : _oConfig.settings.asStripeClasses,
				bHideProgressBar : false,
				oLanguage 		 :
				{
					sLengthMenu 	: $.pgsi.locale.get(_oConfig.messages.recordsPerPage),
					sZeroRecords 	: $.pgsi.locale.get(_oConfig.messages.recordsNone),
					sInfo 			: $.pgsi.locale.get(_oConfig.messages.recordsInfo),
					sInfoEmpty 		: $.pgsi.locale.get(_oConfig.messages.recordsInfoEmpty),
					sInfoFiltered 	: $.pgsi.locale.get(_oConfig.messages.recordsFilter),
					sProcessing 	: $.pgsi.locale.get(_oConfig.messages.processing),
					oPaginate 		:
					{
						sFirst 	  : "",
						sLast  	  : "",
						sNext  	  : $.pgsi.locale.get(_oConfig.messages.next) + " &gt;&gt;",
						sPrevious : "&lt;&lt; " + $.pgsi.locale.get(_oConfig.messages.prev)
					}
				}
			};

			/** Private Functions **/
			var _fnFooterCallback = function(nRow, aaData, iStart, iEnd, aiDisplay, fnCallback)
			{
				if (aaData.length > 0)
				{
					$(this).parent().siblings(_oConfig.common.sBlankslate).addClass("hide");
					$(this).removeClass("hide");
					$(this).siblings().not(".stamp").css('visibility', 'visible');
				}
				else
				{
					$(this).addClass("hide");
					$(this).siblings().not(".stamp").css('visibility', 'hidden');
					$(this).parent().siblings(_oConfig.common.sBlankslate).removeClass("hide");
				}

				if (!$.pgsi.isNullOrUndefined(fnCallback))
				{
					fnCallback.call($(this), nRow, aaData, iStart, iEnd, aiDisplay);
				}
			};

			var _fnShowViewPortFixedHeader = function ($viewport, $table)
			{
				//Fix for fixedHeader issue
				if ( $.pgsi.isNullOrUndefined($table.data("fixedHeader")) )
				{
					$viewport.removeClass("hide");
					return;
				}

				$viewport.removeClass("hide");
				$table.data("fixedHeader").fnUpdate(true);
			};

			var _fnInitComplete = function(oSettings, aaData, config, fnCallback)
			{
				var $table = this;

				if(config.bFixedHeader && !config.bIsDialog)
				{
					// Start fixed header
					$table.data("fixedHeader", new $.fn.dataTable.FixedHeader( $table, { "zTop": "20" } ));
				}

				$table.on("remove", function()
				{
					// Destroy FixedHeader
					_destroyFixedHeader($(this));
				});

				if (!$.pgsi.isNullOrUndefined(fnCallback))
				{
					fnCallback.call($(this), oSettings, aaData, config);
				}

			};

			var _fnInfoCallback  = function( oSettings, iStart, iEnd, iMax, iTotal, sPre, fnCallback, config )
			{
				var $tableFooter 	= $("." + _oConfig.common.sTableFooter, oSettings.nTableWrapper);
				var	$next 			= $("." + _oConfig.common.sNext, $tableFooter);
				var	$previous 		= $("." + _oConfig.common.sPrev, $tableFooter);
				var tableLength 	= $(config.id).siblings().find("select option:selected").val();
				var	totalPagination = Math.ceil(iTotal / tableLength);
				var	current 		= Math.ceil(iEnd / tableLength);

				//Set corner border to next and previous buttons
				$next.addClass("ui-corner-all");
				$previous.addClass("ui-corner-all");

				//Check if current paginations is 1 to hide previous buttons
				if (iStart === 1)
				{
					$previous.hide();
				}
				else
				{
					$previous.show();
				}

				//Check if is the last pagination is the current item to hide next buttons
				if (current === totalPagination)
				{
					$next.hide();
				} else {
					$next.show();
				}

				//Set current pagination
				_iCurrentPagination = current;

				//Set Total items
				_iTotalPagination = totalPagination;

				var Checkbox = $(this).data("checkbox");

				if (!$.pgsi.isNullOrUndefined(Checkbox))
				{
					Checkbox.allRowsCount(iTotal);
				}

				var sMatches 	= config.sMatches 	? config.sMatches 	: $.pgsi.locale.get(_oConfig.messages.resultMatches);
				var sMatch 		= config.sMatch 	? config.sMatch 	: $.pgsi.locale.get(_oConfig.messages.resultMatch);
				var $Matches 	= $("#"+ (config.sTotalLabel ? config.sTotalLabel : _oConfig.common.totalRecordsLabel));
				var $Total 		= $("#"+ (config.sTotalResults ? config.sTotalResults : _oConfig.common.totalRecordsId));

				if (!$.isNumeric(iTotal))
				{
					iTotal = 0;
					$Matches.text(sMatches);
				}
				else if (iTotal != 1)
				{
					$Matches.text(sMatches);
				}
				else
				{
					$Matches.text(sMatch);
				}
				$Total.text(iTotal);

				if (!$.pgsi.isNullOrUndefined(fnCallback))
				{
					fnCallback.call($(this), oSettings, iStart, iEnd, iMax, iTotal, sPre);
				}

				return sPre;
			};

			var _fnRowCallback = function (nRow, aData, iDisplayIndex, fnCallback, config)
			{
				var Checkbox = $(this).data("checkbox");

				if (!$.pgsi.isNullOrUndefined(Checkbox))
				{
					var input = $('td:eq(0)', nRow).find("input");

					if (Checkbox.allRows())
					{
						input.prop("checked", true);
						for (unselected in Checkbox.unselected())
						{
						    if (Checkbox.unselected().hasOwnProperty(unselected))
						    {
								if (input.attr("value") == Checkbox.unselected()[unselected])
								{
						        	input.prop("checked", false);
						        	$(this).find("thead input:checkbox").prop("checked", false);
									break;
						        }
						    }
					    }
					}
					else
					{
						for (preChecked in Checkbox.selected())
						{
						     if (Checkbox.selected().hasOwnProperty(preChecked))
						     {
								if (input.attr("value") == Checkbox.selected()[preChecked])
								{
						        	input.prop("checked", true);
						        }
						     }
					    }
					}
				}

				if ($.isFunction(config.sRowGrouping))
				{
					$(nRow).data("rowGrouping", config.sRowGrouping(aData));
				}
				else if (config.sRowGrouping)
				{
					$(nRow).data("rowGrouping", config.oCheckBoxMethods.fnReduce(config.sRowGrouping, aData));
				}

				if (!$.pgsi.isNullOrUndefined(fnCallback))
				{
					fnCallback.call($(this), nRow, aData, iDisplayIndex);
				}

				return nRow;
			};

			var _fnFullNumberPagination = function(iCurrent, iTotalPagination, $paginationLast, $paginationFirst)
			{
				var iPaginatePosition 		= parseInt($(".current").text());
				var oMiddlePaginateButton	= $("<a class='paginate_button'>...</a>");

				var fnSetEndPaging			= function()
				{
					$paginationLast.text(iTotalPagination);
					$paginationLast.prev().text(iTotalPagination - 1);
					$("<a class='paginate_button'>...</a>").insertBefore($paginationLast.prev());
				};

				var fnSetStartPaging		= function()
				{
					$paginationFirst.text("1");
					$paginationFirst.next().text("2");
					oMiddlePaginateButton.insertAfter($paginationFirst.next());
				};

				if ((iPaginatePosition < 7) && (iTotalPagination > 12))
				{
					//Add last pagination
					if (iCurrent < iTotalPagination - 2)
					{
						if (iCurrent + 7 <= iTotalPagination)
						{
							fnSetEndPaging();
						}
					}
					else if (iCurrent < iTotalPagination - 1)
					{
						$paginationLast.text(iTotalPagination);
						oMiddlePaginateButton.insertBefore($paginationLast);
					}
				}

				if ((iCurrent == 7) && (iTotalPagination > 12))
				{
					//Add first button
					$paginationFirst.text("1");

					fnSetEndPaging();
				}
				else if ((iCurrent > 7) && (iTotalPagination > 12))
				{
					//Add first Pagination
					if (iCurrent > 3)
					{
						fnSetStartPaging();

						if ((iTotalPagination - iCurrent ) >= 7)
						{
							fnSetEndPaging();
						}
					}
					else if (iCurrent > 2)
					{
						$paginationFirst.text("1");
						oMiddlePaginateButton.insertAfter($paginationFirst);
					}
				}
			};

			var _fnDailyPagination = function(config, oSettings)
			{
				_fnCreateDatePagination(config, oSettings, _oConfig.settings.aPaginationTypes[1]);
			};

			var _fnMonthlyPagination = function(config, oSettings)
			{
				_fnCreateDatePagination(config, oSettings, _oConfig.settings.aPaginationTypes[2]);
			};

			var _fnYearlyPagination = function(config, oSettings)
			{
				_fnCreateDatePagination(config, oSettings, _oConfig.settings.aPaginationTypes[3]);
			};

			var _fnCreateDatePagination = function(config, oSettings, sType)
			{
				var $Table 				= $(config.id);
				var _formatMask			= pgsi.settings.user.dateFormat;
				var dToday 				= new Date();
				var sToSearchValue 		= _oConfig.project.sToDateUrl;
				var sFromSearchValue 	= _oConfig.project.sFromDateUrl;
				var $Ul 				= $("<ul><li id='li-pagination'><ol id='"+ _oConfig.common.datePaginationId +"'></ol></li></ul>");
				var ol 					= "#"+ _oConfig.common.datePaginationId;
				var sCurrentDate 		= $.address.parameter(sToSearchValue) ? $.address.parameter(sToSearchValue) : $.pgsi.date.format(new Date(), _formatMask, false);
				var dCurrentDate 		= new Date(sCurrentDate);
				var dCurrentDateStart 	= new Date(sCurrentDate);
				var dLast 				= new Date(dToday);
				var dLastStart 			= new Date(dToday);
				var dPenult				= new Date(dToday);
				var dPenultStart		= new Date(dToday);
				var dBefore 			= new Date(dCurrentDate);
				var dNextDate 			= new Date(dCurrentDate);
				var sLast;
				var sCurrentDateStart;
				var dAntePenult;
				var dAntePenultStart;
				var dNext;
				var dNextStart;
				var sPenult;
				var sActual;
				var sBefore;
				var sText;
				var $li;

				var fnCreatePaginationLi= function(title, endDate, startDate)
				{
					var $LI = $('<li></li>');
					var $a;
					var ed = new Date(endDate);
					var sd = new Date(startDate);

					if (title)
					{
						$LI.addClass("valid-pagination");
						$a 	= $('<a id="'+ $.pgsi.date.format(ed, $.pgsi.replaceAll("/", "-", _formatMask), {iTZMinutes: 0}) +'"class="button-pagination url" href="#">'+ title +'</a>');
						$a.data(sToSearchValue, ed);
						$a.data(sFromSearchValue, sd);
					}
					else
					{
						$LI.addClass("invalid");
						$a 	= $('<a href="#" class="button-pagination">...</a>');
						$a.data("oNextPrev", endDate);
					}

					$LI.append($a);
					return $LI;
				};

				//	Create Next Button
				//	Check if is actual Date. If Not Create Next Button
				switch(sType)
				{
					case _oConfig.settings.aPaginationTypes[3] :
						dBefore.setFullYear(dBefore.getFullYear()-1);

						dCurrentDateStart.setMonth(1);
						dCurrentDateStart.setDate(1);
						//	Set Next Date
						dNext		= new Date(dCurrentDate);
						dNextStart	= new Date(dCurrentDateStart);
						dNext.setFullYear(dNext.getFullYear()+1);
						dNextStart.setFullYear(dNext.getFullYear()+1);

						sCurrentDateStart = $.address.parameter(sFromSearchValue) ? $.address.parameter(sFromSearchValue) : $.pgsi.date.format(dCurrentDateStart, _formatMask, false);
						dNextDate.setFullYear(dNextDate.getFullYear() + 1);
						//	Set Penult End date
						dPenult.setFullYear(dPenult.getFullYear() - 1);
						dPenult.setMonth(11);
						dPenult.setDate(dPenult.getDaysInMonth());
						//	Set Penult Start date
						dPenultStart.setFullYear(dPenultStart.getFullYear() - 1);
						dPenultStart.setMonth(0);
						dPenultStart.setDate(1);
						//	Set Antepenult End date
						dAntePenult = new Date(dPenult);
						dAntePenult.setFullYear(dAntePenult.getFullYear() -1);
						dAntePenultStart = new Date(dAntePenult);
						dAntePenultStart.setDate(1);
						dAntePenultStart.setMonth(0);

						dLastStart.setDate(1);
						dLastStart.setMonth(0);
						sLast 	= $.datepicker.formatDate("yy", dLast);
						sPenult = $.datepicker.formatDate("yy", dPenult);
						sActual = $.datepicker.formatDate("yy", dCurrentDate);
						sBefore = $.datepicker.formatDate("yy", dBefore);
						break;

					case _oConfig.settings.aPaginationTypes[2] :
						dBefore.addMonths(-1);

						dCurrentDateStart.setDate(1);
						//	Set Next Date
						dNext		= new Date(dCurrentDate);
						dNextStart	= new Date(dCurrentDateStart);
						dNext.addMonths(1);
						dNext.setDate(dToday.getMonth() === dNext.getMonth() ? dToday.getDate() : dNext.getDaysInMonth());
						dNextStart.addMonths(1);
						dNextStart.setDate(1);

						sCurrentDateStart = $.address.parameter(sFromSearchValue) ? $.address.parameter(sFromSearchValue) : $.pgsi.date.format(dCurrentDateStart, _formatMask, false);
						dNextDate.addMonths(1);
						dPenult.addMonths(-1);
						dPenult.setDate(dPenult.getDaysInMonth());
						//	Set Penult Start date
						dPenultStart.addMonths(-1);
						dPenultStart.setDate(1);
						//
						dAntePenult = new Date(dPenult);
						dAntePenult.addMonths(-1);
						dAntePenultStart = new Date(dAntePenult);
						dAntePenultStart.setDate(1);

						dLastStart.setDate(1);
						sLast 	= dLast.getMonth() !== 11 ? $.datepicker.formatDate("M", dLast) : $.datepicker.formatDate("M yy", dLast);
						sPenult = dPenult.getMonth() !== 11 ? $.datepicker.formatDate("M", dPenult) : $.datepicker.formatDate("M yy", dPenult);

						sActual = dCurrentDate.getMonth() !== 11 ? $.datepicker.formatDate("M", dCurrentDate) : $.datepicker.formatDate("M yy", dCurrentDate);
						sBefore = dBefore.getMonth() !== 11 ? $.datepicker.formatDate("M", dBefore) : $.datepicker.formatDate("M yy", dBefore);
						break;

					case _oConfig.settings.aPaginationTypes[1] :
						dBefore.setDate(dBefore.getDate - 1);

						//	Set Next Date
						dNext		= new Date(dCurrentDate);
						dNextStart	= new Date(dCurrentDateStart);
						dNext.setDate(dNext.getDate()+1);
						dNextStart.setDate(1);

						dNextDate.setDate(dNextDate.getDate() + 1);
						dPenult.setDate(dLast.getDate() - 1);
						dPenultStart.setDate(dLast.getDate() - 1);

						dAntePenult = new Date(dPenult);
						dAntePenult.setDate(dAntePenult.getDate() -1);
						dAntePenultStart = new Date(dAntePenult);

						sLast 	= $.datepicker.formatDate("d. M", dLast);
						sPenult = $.datepicker.formatDate("d. M", dPenult);
						sActual = $.datepicker.formatDate("d. M", dCurrentDate);
						sBefore = $.datepicker.formatDate("d. M", dBefore);
						break;
				}
				if(sCurrentDate !== $.pgsi.date.format(new Date(), _formatMask))
				{
					var sNext = '<a href="#" class="next button ui-button url ui-widget ui-state-default ui-corner-all ui-button-text-only">';
					sNext += '<span class="ui-button-text">';
					sNext += '<span class="ui-button-text">'+ $.pgsi.locale.get("table.page.next") +'</span></span></a>';
					var $Next 		= $(sNext);

					$Next.data(sToSearchValue, dNextDate);
					$Ul.find(ol).append($Next);
				};

				//	Check if should to show last button before next
				if (sCurrentDate === $.pgsi.date.format(dPenult, _formatMask))
				{
					$Ul.find(ol).prepend(fnCreatePaginationLi(sLast, dLast, dLastStart));
				}
				else if ($.pgsi.date.format(dCurrentDate, _formatMask) === $.pgsi.date.format(dPenult, _formatMask))
				{
					$Ul.find(ol).prepend(fnCreatePaginationLi(sLast, dLast, dLastStart));
				}
				else if ($.pgsi.date.format(dCurrentDate, _formatMask) === $.pgsi.date.format(dAntePenult, _formatMask))
				{
					$Ul.find(ol).prepend(fnCreatePaginationLi(sLast, dLast, dLastStart));
					$Ul.find(ol).prepend(fnCreatePaginationLi(sPenult, dPenult, dPenultStart));
				}
				else if (dCurrentDate < dPenult)
				{
					$Ul.find(ol).prepend(fnCreatePaginationLi(sLast, dLast, dLastStart));
					$Ul.find(ol).prepend(fnCreatePaginationLi(sPenult, dPenult, dPenultStart));

					$Ul.find(ol).prepend(fnCreatePaginationLi(
						null,
						{
							next : {
								fromSearchValue 	: dNextStart,
								toSearchValue 		: dNext
							},
							prev : {
								fromSearchValue 	: dAntePenultStart,
								toSearchValue 	 	: dAntePenult
							}
						}
					));
				}

				//	Create Middle Pagination Buttons
				//	while i less than 7
				for (var i = 0; i <= 7; i++)
				{
					switch(sType)
					{
						case _oConfig.settings.aPaginationTypes[3] :
							sText = $.datepicker.formatDate("yy", dCurrentDate);
							$li = fnCreatePaginationLi(sText, dCurrentDate, dCurrentDateStart);
							dCurrentDateStart.setFullYear(dCurrentDateStart.getFullYear() - 1);
							dCurrentDate.setFullYear(dCurrentDate.getFullYear() - 1);
							dCurrentDate.setMonth(11);
							dCurrentDate.setDate(dCurrentDate.getDaysInMonth());
							break;

						case _oConfig.settings.aPaginationTypes[2] :
							sText = dCurrentDate.getMonth() !== 11 ? $.datepicker.formatDate("M", dCurrentDate) : $.datepicker.formatDate("M yy", dCurrentDate);
							$li = fnCreatePaginationLi(sText, dCurrentDate, dCurrentDateStart);
							dCurrentDate.addMonths(-1);
							dCurrentDate.setDate(dCurrentDate.getDaysInMonth());
							dCurrentDateStart.addMonths(-1);
							break;

						case _oConfig.settings.aPaginationTypes[1] :
							sText = $.datepicker.formatDate("d. M", dCurrentDate);
							$li = fnCreatePaginationLi(sText, dCurrentDate, dCurrentDateStart);
							dCurrentDate.setDate(dCurrentDate.getDate() - 1);
							dCurrentDateStart.setDate(dCurrentDate.getDate() - 1);
							break;

						default :
							break;
					}
					// Add Button at HTML
					$Ul.find(ol).prepend($li);
				}

				//	Create Back Button
				//	Check if is actual Date. If Not Create Next Button
				var fnCreateBackButton = function()
				{
					var sBack = '<a href="#" class="back button ui-button url ui-widget ui-state-default ui-corner-all ui-button-text-only">';
					sBack += '<span class="ui-button-text">';
					sBack += '<span class="ui-button-text">'+ $.pgsi.locale.get(_oConfig.messages.paginationBack) +'</span></span></a>';

					var $Back 			= $(sBack);
					var backdate 		= new Date(dCurrentDate);
					var backdateStart 	= new Date(dCurrentDateStart);

					$Back.data(sToSearchValue, backdate);
					$Back.data(sFromSearchValue, backdateStart);
					return $Back;
				};
				$Ul.find(ol).prepend(fnCreateBackButton());

				//	Add and remove Pagination
				$(".paging_" + sType).append($Ul);
				$("."+ _oConfig.common.sTableFooter).removeAttr("style");

				//	Add Click Event for pagination Buttons
				$(ol + ' .button-pagination').click(function(e)
				{
					e.preventDefault();

					if (!$(this).closest("li").hasClass("active"))
					{
						var $thisPagination 	= $(this);
						var dFromSearchValue 	= $thisPagination.data(sFromSearchValue);
						var dToSearchValue 		= $thisPagination.data(sToSearchValue);

						if (dFromSearchValue && dToSearchValue)
						{
							var fnCallback 			= function(e)
							{
								if (config.fnPaginationButtonClick)
								{
									config.fnPaginationButtonClick(e);
								}
							};

							//	Add Date range To URL
							$.pgsi.pageLoader.setParameter(sFromSearchValue, $.pgsi.date.format(dFromSearchValue, _formatMask, false));
							$.pgsi.pageLoader.setParameter(sToSearchValue, $.pgsi.date.format(dToSearchValue, _formatMask, false));

							//	Call BE to make a refresh
							$.pgsi.ajax.post({
								sUrl 	 	: config.sAjaxSource,
								oRequest 	: config.ajax.fnRequest(),
								bAsync		: _oConfig.ajax.bAsync,
								fnCallback 	: fnCallback
							});
						}
					}
				});

				var fnCreateSubHeaderPagination = function(sActual, sBefore)
				{
					var sHtml = '';
					sHtml += '<tr class="subHeaderPagination row-header"><td colspan="9">';
					sHtml += '<h4><a class="back button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" href="" role="button" aria-disabled="false">';
					sHtml += '<span class="ui-button-text">' + $.pgsi.locale.get(_oConfig.messages.paginationBackArrow) + sBefore +'</span></a>';
					sHtml += ' <strong>'+ sActual +'</strong></h4></td></tr>';

					var $line =$(sHtml);

					$Table.prepend($line);
				};

				//	Create Sub Header Pagination
				if(config.subHeaderPagination)
				{
					fnCreateSubHeaderPagination(sActual, sBefore);
				}

				//	Add Click Event for back Button
				$(ol + ' .back, .subHeaderPagination .back').click(function(e)
				{
					e.preventDefault();
					var oActive 	= $("#" + _oConfig.common.datePaginationId + " .active");
					var oPrev 		= oActive.prev();
					var oInvalid 	= $("#" + _oConfig.common.datePaginationId + " .invalid");

					if (oPrev.hasClass("invalid"))
					{
						var oNextPrev = oInvalid.find('a').data("oNextPrev");
						oInvalid.find('a').data(sFromSearchValue, oNextPrev.prev.fromSearchValue);
						oInvalid.find('a').data(sToSearchValue, oNextPrev.prev.toSearchValue);
						oInvalid.find('a').next().find("a").trigger('click');
					}
					else
					{
						oActive.prev().find("a").trigger('click');
					}
				});

				//	Add Click Event for next Button
				$(ol + ' .next').click(function(e)
				{
					e.preventDefault();
					var oActive 	= $("#" + _oConfig.common.datePaginationId + " .active");
					var oNext 		= oActive.next();
					var oInvalid 	= $("#" + _oConfig.common.datePaginationId + " .invalid");

					if (oNext.hasClass("invalid"))
					{
						var oNextPrev = oInvalid.find('a').data("oNextPrev");
						oInvalid.find("a").data(sFromSearchValue, oNextPrev.next.fromSearchValue);
						oInvalid.find("a").data(sToSearchValue, oNextPrev.next.toSearchValue);
						oInvalid.find("a").trigger('click');
					}
					else
					{
						oActive.next().find("a").trigger('click');
					}
				});

				$.pgsi.pageLoader.setParameter(sFromSearchValue, sCurrentDateStart);
				$.pgsi.pageLoader.setParameter(sToSearchValue, sCurrentDate);

				//	Add class active to
				$(ol+" li").removeClass("active");
				// hide pagination
				$(".numbers").html("");
				$(config.id+" ."+ _oConfig.common.sTableFooter).removeAttr("style");

				$(ol+" li a[id='"+ $.pgsi.replaceAll("/", "-", sCurrentDate) +"']").closest("li").addClass("active");
			};

			var _fnDrawCallback = function(oSettings, fnCallback, config)
			{
				// Disable export button when there's no rows
				if (config && config.$exportCSV)
				{
					if (config.aaData && config.aaData.length)
				    {
				    	config.$exportCSV.removeClass("disabled");
				    }
				    else
				    {
				    	config.$exportCSV.addClass("disabled");
				    }
				}

				var $table 				= $(config.id);
				var	$selectPage 		= $("#" + _oConfig.common.selectPageId);
				var	$tableFooter 		= $("." + _oConfig.common.sTableFooter);
				var	$previous 			= $("." + _oConfig.common.sPrev, $tableFooter);
				var	iCurrent 			= _iCurrentPagination;
				var	iTotalPagination 	= _iTotalPagination;
				var	$paginationContainer= $previous.next();
				var	$paginationLast 	= $("a:last", $paginationContainer);
				var	$paginationFirst 	= $("a:first", $paginationContainer);
				var	$oPaging 			= $(this).siblings("."+ _oConfig.common.sTableFooter).find('.paging');

				if (iTotalPagination === 1)
				{
					$oPaging.hide();
				}
				else
				{
					$oPaging.show();
				}

				_iCurrentPagination = 0;
				_iTotalPagination 	= 0;

				if (oSettings.sPaginationType === _oConfig.settings.aPaginationTypes[0])
				{
					_fnFullNumberPagination(iCurrent, iTotalPagination, $paginationLast, $paginationFirst);
				}
				else if (oSettings.sPaginationType === _oConfig.settings.aPaginationTypes[1])
				{
					_fnDailyPagination(config, oSettings);
				}
				else if (oSettings.sPaginationType === _oConfig.settings.aPaginationTypes[2])
				{
					_fnMonthlyPagination(config, oSettings);
				}
				else if (oSettings.sPaginationType === _oConfig.settings.aPaginationTypes[3])
				{
					_fnYearlyPagination(config, oSettings);
				}

				if ($table.find(":checked").length > ($table.closest('table').find('tbody').find('tr').length - 1))
				{
					$table.find($selectPage).prop('checked', true);
				}
				else
				{
					$table.find($selectPage).prop('checked', false);
				}

				$('.button').button();

				//Order by column
				if (config.sRowGrouping)
				{
					var data = null;

					$(this).find("tbody tr").each(function(i, e)
					{
						var orderDecorated = $(e).data("rowGrouping");

						if (orderDecorated != data)
						{
							data = orderDecorated;

							$("<tr class='row-header'><td colspan='" + (parseInt(config.aoColumns.length, 10) + 1) +"'><h4>" + orderDecorated +"</h4></td></tr>").insertBefore(e);
						}
					});
				}

				if (!$.pgsi.isNullOrUndefined(fnCallback))
				{
					fnCallback.call($(this), oSettings);
				}

				if (!$.pgsi.isNullOrUndefined(config.oSettings) && config.oSettings.bFooterVisible === false)
				{
					$table.parent().find("."+ _oConfig.common.sTableFooter).remove();
					$table.parent().find("." + _oConfig.common.tableFooterStamp).remove();
				}
			};

			var _fnAjaxCallback = function(config, fnCallback, oResponse)
			{
			//	if ($.pgsi.isValidPreLoad(config.aaData, true))
			//	{
//					// Refresh date
//					var oRefreshUtc = $.pgsi.date.createTimeZoneJS();
//
//					//Add Last Refresh
//					if (oRefreshUtc != undefined)
//					{
//						var sDate = $.pgsi.date.format( oRefreshUtc, pgsi.settings.user.dateFormat + ' h:i:s A', true);
//						$(config.id).siblings("." + _oConfig.common.tableFooterStamp).text($.pgsi.locale.get('table.refreshed', sDate)).wrapInner("<em/>");
//					}
//
//					if (config.aaData.resultsSetInfo)
//					{
//						config.iTotalDisplayRecords = config.aaData.resultsSetInfo.totalRowsAvailable;
//					}
//					else
//					{
//						config.iTotalDisplayRecords = 2;
//					}
//
//					// Valid Response Object
//					var oValidResponse = $.pgsi.isValidResponse(config.aaData);
//
//					// Validate oResponse
//					if (!oValidResponse.bIsValid)
//					{
//						// Show Error Message
//						$.pgsi.message.show({message: oValidResponse.sErrorMessage});
//
//					}
//					else
//					{
//						// TODO - review impl
//						if ( !$.pgsi.isNullOrUndefined(config.ajax.responseInterceptor)
//								&& $.isFunction(config.ajax.responseInterceptor) )
//						{
//							config.aaData = config.ajax.responseInterceptor(config.aaData);
//						}
//					}
//
//					// If there is response. it was made an ajax call. So its not necessary to set the response list
//					if (!oResponse)
//					{
//						config.aaData = config.aaData[config.ajax.sObj];
//					}
//
//					fnCallback(config);
//
//					config.bPreLoad = false;
//				}
			};

			var _fnAjax = function(config, aoData, fnTableCallBack)
			{
				var oRequest 	= new config.ajax.oRequest(config.ajax.fnRequest());
				var iStartRow   = ($.grep(aoData, function(e) { return e.name == 'iDisplayStart'	; }))[0].value;
				iStartRow		= isNaN(iStartRow) ? config.iDisplayStart : iStartRow;
				var iPageSize   = ($.grep(aoData, function(e) { return e.name == 'iDisplayLength'	; }))[0].value;
				iPageSize		= isNaN(iPageSize) ? config.iDisplayLength : iPageSize;


				if ( $.pgsi.isNullOrUndefined(oRequest.sortExpressions) )
				{
					oRequest.sortExpressions = pgsi.util.page.getSortExpression($(config.id).dataTable(), config);
				}

				// Whether startRow is undefined
				// Don't check if startRow is null, there are cases when startRow has to be null
				if (oRequest.startRow === undefined)
				{
					oRequest.startRow = iStartRow;
				}

				if (config.bParamsOnUrl != false)
				{
					// Whether pageSize is undefined
					// Don't check if pageSize is null, there are cases when pageSize has to be null
					oRequest.pageSize = $.address.parameter("length") ? $.address.parameter("length") : iPageSize;
				}
				else
				{
					oRequest.pageSize = iPageSize ? iPageSize : undefined;
				}

				// Whether endRow is undefined
				// Don't check if endRow is null, there are cases when endRow has to be null
				if (oRequest.endRow === undefined)
				{
					oRequest.endRow	= 0;
				}

				var fnCallback = function(json)
				{
					config.aaData = json;

					_fnAjaxCallback(config, fnTableCallBack, json);
				};

				var bHideProgressBar = config.bHideProgressBar;
				if (config.bAutoRefreshReload === true)
				{
					bHideProgressBar = true;
					config.bAutoRefreshReload = false;
				}

				$.pgsi.ajax.post({
					sUrl 		: config.sAjaxSource,
					oRequest 	: oRequest,
					bAsync	 	: _oConfig.ajax.bAsync,
					fnCallback 	: fnCallback,
					bHideProgressBar : bHideProgressBar
				});

			};

			var _fnGetCheckboxElem = function (data, type, full, config)
			{
				var sValue;
				if (!$.pgsi.isNullOrUndefined(config.sCheckbox))
				{
					sValue = config.sCheckbox;
				}
				else
				{
					sValue = config.sDialogCheckbox;
				}
				return $('<input type="checkbox" class="checkObj" id="check_'+ data + '" value="'+ data +'"/>').data("obj", config.oCheckBoxMethods.fnReduce(sValue, full));
			 };

			 var _fnReduce = function (index, aData)
			 {
				var response;
				index = index.split(".");

				if (index.length > 1)
				{
					jQuery.each(aData, function(i, value)
					{
						if (i == index[0])
						{
							response = value;
						}
					});

					for (var x = 1; x < index.length; x++)
					{
						jQuery.each(response, function(i, value)
						{
							if (i == index[x])
							{
								response = value;
							}
						});
					}
				}
				else
				{
					jQuery.each(aData, function(i, value)
					{
						if (i == index)
						{
							response = value;
						}
					});
				}

				return response;
			};

			var _Checkbox = function ($count, config, $table)
			{
				var _aSelected 		= [];
				var _aUnselected 	= [];
				var _isAllRows 		= false;
				var _iAllRowsCount 	= 0;
				var _table 			= $table;
				var _$count;
				var _config;

				// Constructor
				var _Checkbox = (function(oTableCount)
				{
					_$count = oTableCount;
					_config = config;
				})($count, config);

				var _fnCheckExists = function (aArray, val)
				{
					var iArrayLength = aArray.length;
					var iPosition = -1;

					for (var iPos = 0; iPos < iArrayLength; iPos++)
					{
						// Transform String like 'a.b' into a obj reference
						if (aArray[iPos] == val)
						{
							iPosition = iPos;
							break;
						}
					}
					return iPosition;
				};

				this.getTotalSelected = function()
				{
					var iValue = this.selected().length;

					if (this.allRows() == true)
					{
						iValue = this.allRowsCount() - this.unselected().length;

						if (config.fnAllInterceptor)
						{
							var iNewValue = config.fnAllInterceptor(iValue, config);
							iValue = !$.pgsi.isNullOrUndefined(iNewValue) ? iNewValue : iValue;
						}
					}

					return iValue;
				};

				this.setTotalResult = function()
				{
					if (_table.data() && _table.data().config && _table.data().config.sCheckedCountId)
					{
						_$count.find(_table.data().config.sCheckedCountId).text($().numberFormat(this.getTotalSelected()));
					}
				};

				this.setAllRows = function(iAllRows)
				{
					this.allRowsCount(iAllRows);
					this.setTotalResult();
					_$count.find("#" + _oConfig.common.generalTotalId).text($().numberFormat(iAllRows));
					_$count.closest(".yui-ge").next().find("#" + _oConfig.common.totalRecordsId).text($().numberFormat(iAllRows));
				};

				this.addSelected = function (id)
				{
					if (_aSelected.indexOf(id) == -1)
					{
					    _aSelected.push(id);
					}
				};

				this.removeSelected = function (id)
				{
					if (_aSelected.indexOf(id) > -1)
					{
					    _aSelected.splice(index, 1);
					}
				};

				this.selected = function (obj, bRemove)
				{
					// Get Selected when no parameter specified
					if ($.pgsi.isNullOrUndefined(obj))
					{
						return _aSelected;
					}

					// Remove Selected When bRemove Param is true
					if (bRemove)
					{
						var iSelectedIndex = _fnCheckExists(_aSelected, obj);

						if (iSelectedIndex != -1)
						{
							_aSelected.splice(iSelectedIndex, 1);
						}

						return;
					}

					// Remove From Unselected
					this.unselected(obj, true);

					// Add Selected
					if (_fnCheckExists(_aSelected, obj) == -1)
					{
						_aSelected.push(obj);
					}
				};

				this.unselected = function (obj, bRemove)
				{
					// Get Unselected when no parameter specified
					if ($.pgsi.isNullOrUndefined(obj))
					{
						return _aUnselected;
					}

					// Remove Unselected When bRemove Param is true
					if (bRemove)
					{
						var iUnselectedIndex = _fnCheckExists(_aUnselected, obj);

						if (iUnselectedIndex  != -1)
						{
							_aUnselected.splice(iUnselectedIndex, 1);
						}

						return;
					}

					// Remove From Selected
					this.selected(obj, true);

					// Add Unselected
					if (_fnCheckExists(_aUnselected, obj) == -1)
					{
						_aUnselected.push(obj);
					}
				};

				this.allRows = function (bValue)
				{
					if ($.pgsi.isNullOrUndefined(bValue))
					{
						return _isAllRows;
					}

					_isAllRows = bValue;
				};

				this.allRowsCount = function (iValue)
				{
					if ($.pgsi.isNullOrUndefined(iValue))
					{
						return _iAllRowsCount;
					}

					_iAllRowsCount = iValue;
				};

				this.addObjToArray = function(obj, array)
				{
					if ($.inArray(obj, array) === -1)
					{
					    // Element was not found, add it.
					    array.push(obj);
					}
					return array;
				};

				this.restart = function ()
				{
					var oCheckBoxMethods 	= this;
					//	Clear checkbox elements
					oCheckBoxMethods.clearSelected();

					//	Add unselected array
					$(".checkObj:checked").each(function()
					{
						$(this).prop("checked", false);
					});
				};

				this.clearSelected = function ()
				{
					_aSelected   	= [];
					_aUnselected 	= [];
					_isAllRows 		= false;
					this.setTotalResult();
				};
			};

			var _fnInitCheckboxEvents = function (config)
			{
				var sCheckboxControlId 	= "#" + _oConfig.common.selectButtonId;
				var $table 				= $(config.id);
				var $count 				= config.sDialogCheckbox ? $("." + _oConfig.common.longRunningDialogId) : $("#" + _oConfig.common.actionsId + " " + _oConfig.common.checkboxMessage);
				var Checkbox 			= new config.oCheckBoxMethods.fnCheckbox($count, config, $table);
				var $checkPage 			= config.sDialogCheckbox ? $(".ui-dialog-content").find("#" + _oConfig.common.selectPageId) : $("#doc1").find("#" + _oConfig.common.selectPageId);
				var $actions 			= $("." + _oConfig.common.actionsId);

				$table.data("checkbox", null);

				// Select Single Checkbox
				$table.on("change", "tbody .checkObj", function(e)
				{
					if (config.fnCheckboxClickInterceptor)
					{
						config.fnCheckboxClickInterceptor($(this), oVal);
					}

					var oVal = $(this).data("obj");
					var $tbody = $('tbody', $table);

					if (!$(this).is(':checked'))
					{
						Checkbox.unselected(oVal);
						$checkPage.prop('checked', false);
					}
					else
					{
						Checkbox.selected(oVal);
						$count.removeClass("ui-state-error");

						if ($tbody.find(":checked").length >= ($tbody.find('input:checkbox').length))
						{
							$count.addClass("ui-state-highlight");
							$checkPage.prop('checked', true);
						}
					}

					$(this).toggleClass('row_selected');

					Checkbox.setTotalResult();

					if ($tbody.find(":checked").length === 0)
					{
						$count.removeClass("ui-state-highlight");
					}
					else
					{
						$count.removeClass("ui-state-error").addClass("ui-state-highlight");
					}
				});

				if ($checkPage.length)
				{
					// Select Page Checkbox
					$checkPage.on("click", function(e)
					{
						if($(this).is(':checked'))
						{
							// Check All page checkbox
							$table.find('tbody input:checkbox').each(function (i, e)
							{
								$(e).prop("checked", true);
								$(e).trigger("change");
							});
						}
						else
						{
							// Uncheck All page checkbox
							$table.find('tbody input:checkbox').each(function (i, e)
							{
								$(e).prop("checked", false);
								$(e).trigger("change");
							});
						}

						Checkbox.setTotalResult();

						if ($table.find("tbody :checked").length === 0)
						{
							$count.removeClass("ui-state-highlight");
						}
						else
						{
							$count.removeClass("ui-state-error").addClass("ui-state-highlight");
						}
					});
				}

				if (!$.pgsi.isValidArray(config.checkBoxControllType))
				{
					$(sCheckboxControlId).menuPlug({
						content: $("#" + _oConfig.common.selectButtonId).next().html(), // grab content from this page
						backLink: false,
						maxHeight: 200,
						showSpeed: 400,
						actionCallback : function (actionId) {

							if (actionId == "select_all")
							{
								//	Check Checkbox Status
								$(sCheckboxControlId + " input:checkbox").prop("checked", true);
								$(sCheckboxControlId + " input:checkbox").prop("disabled", false);

								Checkbox.clearSelected();

								if (_oDefaultConfig.bFixedHeader)
								{
									$table.parent().find(".dataTable input:checkbox").prop("checked", true);
								}
								else
								{
									$table.find("input:checkbox").prop("checked", true);
								}

								//Enable allRows selection for server command to be sent
								Checkbox.allRows(true);

								Checkbox.setTotalResult();

								$actions.find(_oConfig.common.checkboxMessage).removeClass("ui-state-error").addClass("ui-state-highlight");
							}
							else if (actionId == "select_page")
							{
								//	Check Checkbox Status
								$(sCheckboxControlId + " input:checkbox").prop("checked", true);
								$(sCheckboxControlId + " input:checkbox").prop("disabled", true);

								Checkbox.clearSelected();

								// Check All page checkbox
								if (_oDefaultConfig.bFixedHeader)
								{
									$table.parent().find(".dataTable input:checkbox").each(function (i, e)
									{
										$(e).prop("checked", true);
									});
								}
								else
								{
									$table.find("tbody input:checkbox").each(function (i, e)
									{
										$(e).prop("checked", true);
									});
								}

								Checkbox.restart();
								$count.removeClass("ui-state-error").addClass("ui-state-highlight");
							}
							else if (actionId == "select_none")
							{
								//	Check Checkbox Status
								$(sCheckboxControlId + " input:checkbox").prop("checked", false);
								$(sCheckboxControlId + " input:checkbox").prop("disabled", false);

								if (_oDefaultConfig.bFixedHeader)
								{
									$table.parent().find(".dataTable input:checkbox").prop("checked", false);
								}
								else
								{
									$table.find("input:checkbox").prop("checked", false);
								}

								$count.removeClass("ui-state-highlight");
								Checkbox.clearSelected();
							}

						}
					});
				}
				else
				{
					// Select Page By Button
					$(config.checkBoxControllType[0]).on("click", function (e)
					{
						e.preventDefault();

						if (_oDefaultConfig.bFixedHeader)
						{
							$table.parent().find(".dataTable input:checkbox").prop("checked", false);
						}
						else
						{
							$table.find("input:checkbox").prop("checked", false);
						}

						Checkbox.clearSelected();

						// Check All page checkbox
						if (_oDefaultConfig.bFixedHeader)
						{
							$table.parent().find(".dataTable input:checkbox").each(function (i, e)
							{
								$(e).prop("checked", true);
								$(e).trigger("change");
							});
						}
						else
						{
							$table.find("tbody input:checkbox").each(function (i, e)
							{
								$(e).prop("checked", true);
								$(e).trigger("change");
							});
						}

						Checkbox.setTotalResult();

						if ($table.find("tbody :checked").length !== 0)
						{
							$count.removeClass("ui-state-error").addClass("ui-state-highlight");
						}
					});

					// Clear Select
					$(config.checkBoxControllType[1]).on("click", function (e)
					{
						e.preventDefault();

						if (_oDefaultConfig.bFixedHeader)
						{
							$table.parent().find(".dataTable input:checkbox").prop("checked", false);
						}
						else
						{
							$table.find("input:checkbox").prop("checked", false);
						}

						Checkbox.clearSelected();

						if ($table.find("tbody :checked").length === 0)
						{
							$count.removeClass("ui-state-highlight");
						}
					});


					// Select All
					$(config.checkBoxControllType[2]).on("click", function (e)
					{
						e.preventDefault();

						Checkbox.clearSelected();

						if (_oDefaultConfig.bFixedHeader)
						{
							$table.parent().find(".dataTable input:checkbox").prop("checked", true);
						}
						else
						{
							$table.find("input:checkbox").prop("checked", true);
						}

						//Enable allRows selection for server command to be sent
						Checkbox.allRows(true);

						Checkbox.setTotalResult();

						if ($table.find("tbody :checked").length !== 0)
						{
							$actions.find(_oConfig.common.checkboxMessage).removeClass("ui-state-error").addClass("ui-state-highlight");
						}
					});
				}

				$table.data("checkbox", Checkbox);
			};

			var _fnSort = function(options)
			{
				var fnCleanSortableClass = function(oTable)
				{
					oTable.find('.sorting_desc').removeClass("sorting_desc");
					oTable.find('.sorting_asc').removeClass("sorting_asc");
				};
				var oTh = $(options.th);

				var oTable = options.table;
				fnCleanSortableClass(oTable);
				oTh.removeClass("sorting_disabled");
				oTh.addClass("sorting_" + options.sortDir);
				oTable.dataTable().fnSort( [ [options.iVisibleCol, options.sortDir] ] );
			};

			var addEffectSort = function(oTh, config, iVisibleCol)
			{
				var	oSortOptions = $("<div class='ui-dialog sort-options' style='display: none'>"
										+ "<ul>"
											+ "<li>"
												+ "<a href='' class='asc'>"
													+ "<span class='icon-small icn-sort-asc'> </span> " + $.pgsi.locale.get("table.SortAscending")
										 		+ "</a>"
											+ "</li>"
											+ "<li>"
												+ "<a href='' class='desc'>"
													+ "<span class='icon-small icn-sort-desc'></span> " + $.pgsi.locale.get("table.SortDescending")
												+ "</a>"
											+ "</li>"
											+ "<li>"
												+ "<a href='' class='custom-column-action'>"
													+ "<span class='icon-small icn-col-select'></span> " + $.pgsi.locale.get("table.Columns")
												+ "</a>"
											+ "</li>"
										+ "</ul>"
									+"</div>");

				oTh.hover(function ()
				{
					$('div', this).stop(true, true).delay(200).slideDown(200);
				}, function ()
				{
					$('div', this).stop(true, true).slideUp(200);
				});

				if (!oTh.find(".sort-options").length)
				{
					oTh.removeClass("sorting_disabled").append(oSortOptions);
				}

				var oTable	= $(config.id);

				$(oSortOptions).find('.asc').click(function(e)
				{
					e.preventDefault();

					var oTh = oTable.find("th:eq("+ iVisibleCol +")");
					_fnSort({
						iVisibleCol : iVisibleCol,
						th 			: oTh,
						table  		: oTable,
						sortDir 	: "asc"
					});
				});

				$(oSortOptions).find('.desc').click(function(e)
				{
					e.preventDefault();

					var oTh = oTable.find("th:eq("+ iVisibleCol +")");
					_fnSort({
						iVisibleCol : iVisibleCol,
						th 			: oTh,
						table  		: oTable,
						sortDir 	: "desc"
					});
				});

				$(oSortOptions).find('.custom-column-action').click(function(e)
				{
					e.preventDefault();
					$("#customize-filter").wCustomize("Columns",function() {}, config.aColumnsIds, "smartpointlist");

					// TODO - future commons customize
					/*$.pgsi.customization.open({
						sType 		: "columns"
					});*/
				});

				return oTh;
			};

			// PageLengthChangeEvent
			var _fnPageLengthChangeEvent = function(sPageSize, oSettings, config)
			{
				// Reset display start for every pageSize change
				oSettings._iDisplayStart = 0;

				if (config.bParamsOnUrl)
				{
					$.pgsi.pageLoader.setParameter("iDisplayStart", 0);
					$.pgsi.pageLoader.setParameter("length", sPageSize);
				}

				if (!pgsi.settings || !pgsi.settings.user || !pgsi.settings.user[_oConfig.project.pageSizeDialogProperty])
				{
					return;
				}

				var oPageSizeDialog =
				{
					// Whether this dialog requires a light list.
					requiresSmartpoints : true,
					width               : 540,
					title               : $.pgsi.locale.get(_oConfig.messages.pageSizeDialogTitle),
					isMonitored			: true,
					buttons 			:
					[
						{
							id 		: _oConfig.common.pageSizeSubmitButtonId,
							text 	: $.pgsi.locale.get(_oConfig.messages.pageSizeSubmitButton),
							click 	: function()
							{
								var bPageSizeShowDialog = !$("#"+ _oConfig.common.pageSizeCheckBox).is(":checked");
								var oProperty 			= {};

								oProperty[_oConfig.project.pageSizeProperty] 			= sPageSize;
								oProperty[_oConfig.project.pageSizeShowDialogProperty] 	= bPageSizeShowDialog;

								$.pgsi.savePropertyProfile(oProperty);
							}
						},

						{
							id : _oConfig.common.pageSizeKeepButtonId,
							text : $.pgsi.locale.get(_oConfig.messages.pageSizeCancelButton),
							click : function()
							{
								$.pgsi.closeActionDialog("#"+ _oConfig.common.sActionId);
								$.pgsi.progressBar.stop();
							}
						},
					],
					action : function(actionDialog)
					{
						var oActionDialog = actionDialog;

						oActionDialog.empty().load(_oConfig.project.pageSizeUrl, function()
						{
							var $dialogValuePageSize = $(_oConfig.common.sPageSizeValueId);

							oActionDialog.removeClass("waiting");

							$dialogValuePageSize.text(sPageSize);
						});

						actionDialog.dialog( "open" );
						actionDialog.dialog( "option", "position", "center" );

						$.pgsi.progressBar.stop();
					}
				};

				$.pgsi.launchActionDialog(null, oPageSizeDialog);
			};

			/**********
			 * GLOBAL *
			 *********/

			/**
			 * Reload table method, getting URL data
			 * @param table
			 * 				The table object
			 */
			var _reloadTable = function(oParam)
			{
				if (oParam.table)
				{
					if (oParam.bHideProgressBar !== true)
					{
						$.pgsi.progressBar.start();
					}

					var iDisplayStart = oParam.iStart;

					if ( $.pgsi.isNullOrUndefined(iDisplayStart) )
					{
						if (oParam.iSelected)
						{
							var ilength 			= oParam.table.fnSettings()._iDisplayLength;
							var iCurrent 			= oParam.table.fnSettings()._iCurrentPage;
							var iNewTotalDisplay 	= oParam.table.fnSettings()._iRecordsDisplay - oParam.iSelected;

							if ( iNewTotalDisplay <= ((iCurrent - 1) * ilength) )
							{
								iDisplayStart 	= 	(iCurrent - 2) * ilength;
							}
							else
							{
								iDisplayStart 	= 	(iCurrent - 1) * ilength;
							}
						}
						else
						{
							iDisplayStart =  $.address.parameter("iDisplayStart") ? $.address.parameter("iDisplayStart") : 0;
						}
					}

					var checkbox = oParam.table.data("checkbox");

					if ( ($.pgsi.isNullOrUndefined(oParam.bCleanSelect) || oParam.bCleanSelect == true) && !$.pgsi.isNullOrUndefined(checkbox) )
					{
						oParam.table.data("checkbox").clearSelected();
						oParam.table.find('input:checkbox').prop('checked', false);

						//Fix for fixedHeader issue
						if ( !$.pgsi.isNullOrUndefined(oParam.table.data("fixedHeader")) )
						{
							$(".FixedHeader_Header").find('input:checkbox').prop('checked', false);
						}

						$("#" + _oConfig.common.selectButtonId + " input").prop('checked', false).prop('disabled', false);
					}

					$(".status-viewport-loading").removeClass("hide");

					oParam.table.fnSettings()._iDisplayStart = (iDisplayStart >= 0) ? iDisplayStart : 0;

					$.pgsi.pageLoader.setParameter("iDisplayStart", oParam.table.fnSettings()._iDisplayStart);
					$.pgsi.pageLoader.setParameter("length", oParam.table.fnSettings()._iDisplayLength);

					$(_oConfig.common.sLengthContainer).find('select option[value='+ oParam.table.fnSettings()._iDisplayLength +']').prop("selected", true);

					oParam.table.api().draw(false);
				}
			};

			var _Config =
			{
				sCheckedCountId 		: "#"+ _oConfig.common.checkedCountId,
				checkBoxControllType 	: ["."+_oConfig.common.selectPageId, "."+_oConfig.common.selectNoneId, "."+_oConfig.common.selectAllId],
				oCheckBoxMethods 		:
				{
					fnInitCheckboxEvents 	: _fnInitCheckboxEvents,
					fnCheckbox 				: _Checkbox,
					fnGetCheckboxElem 		: _fnGetCheckboxElem,
					fnReduce 				: _fnReduce
				}
			};

			var _createTableHeaders = function(options)
			{
				var aSupHeaders 	= [];
				var aCollumns 		= options.aoColumns || options.aoColumnDefs;
				var oTable 			= $(options.id);
				var $thead 			= $("<thead/>");
				var $tr 			= $("<tr/>");
				var $tbody 			= $("<tbody/>");
				var aRowSpan = ($.grep(aCollumns, function(e) {
					if(e) { return e.supHeader; }
				}));
				var iRowSpan = aRowSpan.length ? 2 : 1;

				var fnCreateTh = function(oColumn, i, bSubHeader)
				{
					var $th;

					//	Create Sup Header in case of there is
					if (oColumn.supHeader && !bSubHeader)
					{
						if ($.inArray(oColumn.supHeader, aSupHeaders) !== -1)
						{
							return;
						}

						var aColSpan = ($.grep(aRowSpan, function(e) {
							return e.supHeader === oColumn.supHeader;
						}));

						$th = $('<th colspan="'+ aColSpan.length +'">' + oColumn.supHeader + '</th>');
						aSupHeaders.push(oColumn.supHeader);

						return $th;
					}

					oColumn.headerData = oColumn.headerData ? oColumn.headerData : "";

					//	Create Header
					if ($.isFunction(oColumn.headerData))
					{
						$th = oColumn.headerData(oColumn.order, oColumn.headerData);
					}
					else if (oColumn.bSortable !== false && options.bSort !== false)
					{
						$th = $('<th class="sorting"><span id="' + oColumn.order + '"><a class="'+ oColumn.order +'">' + oColumn.headerData + '</a></span></th>');
					}
					else
					{
						$th = $('<th>' + oColumn.headerData + '</th>');
					}

					//	Add EffectSortable
					if (oColumn.bEffectSortable)
					{
						oColumn.bSortable = false;
						$th = addEffectSort($th, options, i, oTable);
					}

					//	Set as default Sorting
					if ( !$.pgsi.isNullOrUndefined(options.oSettings)
							&& i == options.oSettings.iDefaultCol
							&& oColumn.bEffectSortable )
					{
						$th.addClass("sorting_" + (options.oSettings.sDefaultSort || "asc"));
					}

					//	set as subhead os rowspan
					if (bSubHeader)
					{
						$th.addClass("sub-head");
					}
					else
					{
						$th.attr("rowspan", iRowSpan);
					}

					//create tooltip
					if (oColumn.tooltip)
					{
						$th.addClass(oColumn.tooltip.sClass);
						$th.attr("title", oColumn.tooltip.sMessage);
					}
					return $th;
				};

				var iColumn = 0;

				// Set Checkbox
				if (options.sCheckbox || options.sDialogCheckbox)
				{
					var sInput = "";
					if ($.pgsi.isValidArray(options.checkBoxControllType))
					{
						sInput = "<input type='checkbox' id='" + _oConfig.common.selectPageId + "' class='checkObj' name='" + _oConfig.common.selectPageId + "'/>";
					}

					// Add Column Set Up
					aCollumns.splice(0, 0, {mData : options.sCheckbox, sClass : "checkbox", mRender : function (data, type, full) {return options.oCheckBoxMethods.fnGetCheckboxElem(data, type, full, options);}, sDefaultContent : "", bSortable : false});
					iColumn ++;

					// Add Header Column
					$tr.prepend('<th rowspan="' + (options.sDialogCheckbox ? 2 : 1) + '" class="checkbox">'+ sInput +'</th>');
				}

				// Create Superior Headers
				for (var iColumnsLength = aCollumns.length; iColumn < iColumnsLength; iColumn ++)
				{
					$tr.append(fnCreateTh(aCollumns[iColumn], iColumn));
				}

				$thead.append($tr);

				if (iRowSpan > 1)
				{
					$tr = $("<tr class='sub-head' />");
					//	Create Superior Headers
					for (var i in aRowSpan)
					{
						$tr.append(fnCreateTh(aRowSpan[i], i, true));
					}
					$thead.append($tr);
				}

				oTable.empty();
				oTable.append($thead);
				oTable.append($tbody);

				// Init Events
				options.oCheckBoxMethods.fnInitCheckboxEvents(options);
			};

			/** Global Methods */
			var _setTable = function(config)
			{
				config = $.extend({}, _Config, config);

				// Add Features
				_createTableHeaders(config);

				if (config.bParamsOnUrl)
				{
					var iDisplayStart  = $.address.parameter("iDisplayStart");
				}

				if (!config.sPaginationType)
				{
					config.sPaginationType = _oConfig.settings.aPaginationTypes[0];
				}

				// Set Callbacks
				config.fnFooterCallback = function(nRow, aaData, iStart, iEnd, aiDisplay)
				{
					_fnFooterCallback.call($(this), nRow, aaData, iStart, iEnd, aiDisplay, config.footerCallback);
				};

				config.fnInfoCallback = function(oSettings, iStart, iEnd, iMax, iTotal, sPre)
				{
					return _fnInfoCallback.call($(this), oSettings, iStart, iEnd, iMax, iTotal, sPre, config.infoCallback, config);
				};

				config.fnInitComplete = function(oSettings, aaData)
				{
					_fnInitComplete.call($(this), oSettings, aaData, config, config.initComplete);
				};

				config.fnRowCallback = function (nRow, aData, iDisplayIndex)
				{
					_fnRowCallback.call($(this), nRow, aData, iDisplayIndex, config.rowCallback, config);
				};

				config.fnDrawCallback = function(oSettings)
				{
					_fnDrawCallback.call($(this), oSettings, config.drawCallback, config);
				};



				//Set Length
				if ( !$.pgsi.isNullOrUndefined(config.oSettings) && !$.pgsi.isNullOrUndefined(config.oSettings.aLengthMenu) )
				{
					config.aLengthMenu = config.oSettings.aLengthMenu;
				}

				// Set Displat Length
				if (!config.bIsDialog)
				{
					if (pgsi.settings.user.pageSize != undefined)
					{
						config.iDisplayLength = $.address.parameter("length") ? parseInt($.address.parameter("length"), 10) : parseInt(pgsi.settings.user.pageSize, 10);
					}
					else
					{
						config.iDisplayLength = 25;
					}
				}

				// Set Display Start
				if (iDisplayStart != undefined)
				{
					config.iDisplayStart = parseInt(iDisplayStart, 10);
				}

				// Set Initial Sort
				if (!$.pgsi.isNullOrUndefined(config.oSettings) && config.oSettings.iDefaultCol != null)
				{
					config.aaSorting = [[ config.oSettings.iDefaultCol, (config.oSettings.sDefaultSort || "asc") ]];
				}

				//Set Default Pagination
				jQuery.fn.dataTableExt.oPagination.iFullNumbersShowPages = 12;

	//***************************************************************************

				jQuery.fn.dataTableExt.oPagination[_oConfig.settings.aPaginationTypes[0]] =
				{
				    'oDefaults':
				    {
				        'iShowPages': 12
				    },
				    'fnClickHandler': function(e)
				    {
				        var $element		= $(e.target);
				        var sValue			= $element.text();

			        	//	If button disabled do nothing
				        if ($(this).is('[disabled]') || sValue === "..." || $(this).hasClass("current"))
				        {
				            return false;
				        }

				        var fnCallbackDraw 	= e.data.fnCallbackDraw;
				        var oSettings 		= e.data.oSettings;
				        var oClasses 		= oSettings.oClasses;
				        var sPage 			= parseInt((sValue -1 ));
				        var sPaginationType = $(this).attr("class").replace(/(paginate_button|ui-corner-all)/g, "").trim();
				        var iDisplayStart 	= $.address.parameter("iDisplayStart") ? parseInt($.address.parameter("iDisplayStart")) : 0;
				        var currentPage;
				        var $table			= $(oSettings.nTable);
				        var config			= $table.data("config");

						if ( sValue != 1 && !$.pgsi.isNullOrUndefined(config.$autoRefresh) )
						{
							_clearInterval(config, $table);

							// Delete Storage
							_fnStorageInterval(config, null, true);
						} else if ( sValue == 1 && !$.pgsi.isNullOrUndefined(config.$autoRefresh) && _fnStorageInterval(config) == -1 )
						{
							_createInterval(config, $table.dataTable());
						}

				        if ($(this).hasClass(oClasses.sPagePrevious))
				        {
				        	currentPage = iDisplayStart - oSettings._iDisplayLength;
				        }
				        else if ($(this).hasClass(oClasses.sPageNext))
				        {
				        	currentPage = iDisplayStart + oSettings._iDisplayLength;
				        }
				        else
				        {
				        	currentPage = sPage * oSettings._iDisplayLength;
				        }

				        sPage = sPage ? sPage : 0;

				        if ($.pgsi.isNullOrUndefined(config.bParamsOnUrl) || config.bParamsOnUrl == true)
				        {
				        	$.address.parameter("iDisplayStart", currentPage);
						}

				        //Fix for fixedHeader issue
						if ( !$.pgsi.isNullOrUndefined($table.data("fixedHeader")) )
						{
							$(".FixedHeader_Header").find('input:checkbox').prop('checked', false);
						}

						//	Check if it's a number. If not get the name printed
				 		sPage = $(this).hasClass("paginate_number") ? sPage : sPaginationType;

				        oSettings.oApi._fnPageChange(oSettings, sPage);
				        fnCallbackDraw(oSettings);

				        return true;
				    },
				    // fnInit is called once for each instance of pager
				    'fnInit': function(oSettings, nPager, fnCallbackDraw)
				    {
				        var oClasses 		= $.extend
				    	(
				    			oSettings.oClasses,
				    			{
				    				"sPageButtonStaticDisabled"	: "paginate_button paginate_button_disabled",
				    				"sPageFirst"				: "first",
				    				"sPagePrevious"				: "previous",
				    				"sPageNext"					: "next",
				    				"sPageLast"					: "last"
				    			}
				    	);

				        var oLang 			= oSettings.oLanguage.oPaginate;
				        var that 			= this;
				        var iShowPages 		= oSettings.oInit.iShowPages || this.oDefaults.iShowPages;
				        var iShowPagesHalf 	= Math.floor(iShowPages / 2);
				        var oFirst 			= $('<a class="' + oClasses.sPageButton + ' ' + oClasses.sPageFirst + '">' + oLang.sFirst + '</a>');
				        var oPrevious 		= $('<a class="' + oClasses.sPageButton + ' ' + oClasses.sPagePrevious + '">' + oLang.sPrevious + '</a>');
				        var oNumbers 		= $('<span class="paginate_number"></span>');
				        var oNext 			= $('<a class="' + oClasses.sPageButton + ' ' + oClasses.sPageNext + '">' + oLang.sNext + '</a>');
				        var oLast 			= $('<a class="' + oClasses.sPageButton + ' ' + oClasses.sPageLast + '">' + oLang.sLast + '</a>');

				        $.extend(oSettings, {
				            _iShowPages: iShowPages,
				            _iShowPagesHalf: iShowPagesHalf,
				        });

				        //	Add event click for numbers buttons
				        oNumbers.off("click", "."+oClasses.sPageButton, { 'fnCallbackDraw': fnCallbackDraw, 'oSettings': oSettings }, that.fnClickHandler);
				        oNumbers.on("click", "."+oClasses.sPageButton, { 'fnCallbackDraw': fnCallbackDraw, 'oSettings': oSettings }, that.fnClickHandler);

				        //	Add event click for Others button such as "Prev, Next, First, Last"
				        oFirst.click({ 'fnCallbackDraw': fnCallbackDraw, 'oSettings': oSettings, 'sPage': 'first' }, that.fnClickHandler);
				        oPrevious.click({ 'fnCallbackDraw': fnCallbackDraw, 'oSettings': oSettings, 'sPage': 'previous' }, that.fnClickHandler);
				        oNext.click({ 'fnCallbackDraw': fnCallbackDraw, 'oSettings': oSettings, 'sPage': 'next' }, that.fnClickHandler);
				        oLast.click({ 'fnCallbackDraw': fnCallbackDraw, 'oSettings': oSettings, 'sPage': 'last' }, that.fnClickHandler);

				        // Draw
				        $(nPager).append(oFirst, oPrevious, oNumbers, oNext, oLast);
				    },
				    // fnUpdate is only called once while table is rendered
				    'fnUpdate': function(oSettings, fnCallbackDraw)
				    {
				        var oClasses 		= oSettings.oClasses;
				        var that 			= this;
				        var tableWrapper 	= oSettings.nTableWrapper;
				        // Update stateful properties
				        this.fnUpdateState(oSettings);

				        if (oSettings._iCurrentPage === 1)
				        {
				            $('.' + oClasses.sPageButton + '.' + oClasses.sPageFirst, tableWrapper).attr('disabled', true);
				            $('.' + oClasses.sPagePrevious, tableWrapper).attr('disabled', true);
				        }
				        else
				        {
				            $('.' + oClasses.sPageFirst, tableWrapper).removeAttr('disabled');
				            $('.' + oClasses.sPagePrevious, tableWrapper).removeAttr('disabled');
				        }

				        if (oSettings._iTotalPages === 0 || oSettings._iCurrentPage === oSettings._iTotalPages)
				        {
				            $('.' + oClasses.sPageNext, tableWrapper).attr('disabled', true);
				            $('.' + oClasses.sPageLast, tableWrapper).attr('disabled', true);
				        }
				        else
				        {
				            $('.' + oClasses.sPageNext, tableWrapper).removeAttr('disabled');
				            $('.' + oClasses.sPageLast, tableWrapper).removeAttr('disabled');
				        }

				        var i, oNumber, oNumbers = $('.paginate_number', tableWrapper);

				        // Erase   HERE
				        oNumbers.html('');

				        //oSettings._iFirstPage
				        for (i = oSettings._iFirstPage; i <= oSettings._iLastPage; i++)
				        {
				        	oNumber = $('<a class="' + oClasses.sPageButton + ' paginate_number' + '">' + oSettings.fnFormatNumber(i) + '</a>');

				            // Draw
				            oNumbers.append(oNumber);
				        }

				        // Add class active for current page;
				        var oPages = $(".paginate_button", tableWrapper);

				        oPages.removeClass(oClasses.sPageButtonActive);
				        oPages.filter(function (){
						    return $(this).text() == oSettings._iCurrentPage;
						}).addClass(oClasses.sPageButtonActive);

				    },
				    // fnUpdateState used to be part of fnUpdate
				    // The reason for moving is so we can access current state info before fnUpdate is called
				    'fnUpdateState': function(oSettings)
				    {
				        var iCurrentPage 	= Math.ceil((oSettings._iDisplayStart + 1) / oSettings._iDisplayLength);
				        var iTotalPages 	= Math.ceil(oSettings.fnRecordsDisplay() / oSettings._iDisplayLength);
				        var iFirstPage 		= iCurrentPage - oSettings._iShowPagesHalf;
				        var iLastPage 		= iCurrentPage + oSettings._iShowPagesHalf;

				        if (iTotalPages < oSettings._iShowPages)
				        {
				            iFirstPage 	= 1;
				            iLastPage 	= iTotalPages;
				        }
				        else if (iFirstPage < 1)
				        {
				            iFirstPage 	= 1;
				            iLastPage 	= oSettings._iShowPages;
				        }
				        else if (iLastPage > iTotalPages)
				        {
				            iFirstPage 	= (iTotalPages - oSettings._iShowPages) + 1;
				            iLastPage 	= iTotalPages;
				        }

				        $.extend(oSettings, {
				            _iCurrentPage: iCurrentPage,
				            _iTotalPages: iTotalPages,
				            _iFirstPage: iFirstPage,
				            _iLastPage: iLastPage
				        });
				    }
				};

				var oPaginationCreator =
				{
				    'fnClickHandler': function(e)
				    {
				        var $element		= $(e.target);
				        var	sValue			= $element.text();
				        var	fnCallbackDraw 	= e.data.fnCallbackDraw;
				        var oSettings 		= e.data.oSettings;
				        var sPage 			= parseInt((sValue -1 ));

				        return true;
				    },
				    // fnInit is called once for each instance of pager
				    'fnInit': function(oSettings, nPager, fnCallbackDraw)
				    {
				        var oClasses 	= oSettings.oClasses;
				        var oLang 		= oSettings.oLanguage.oPaginate;
				        var that 		= this;

				        _fnCreateDatePagination(config, oSettings, config.sPaginationType);
				    },
				    // fnUpdate is only called once while table is rendered
				    'fnUpdate': function(oSettings, fnCallbackDraw)
				    {
				        var oClasses 	 = oSettings.oClasses;
				        var that 		 = this;
				        var tableWrapper = oSettings.nTableWrapper;
				        // Update stateful properties
				        this.fnUpdateState(oSettings);
				    },
				    // fnUpdateState used to be part of fnUpdate
				    // The reason for moving is so we can access current state info before fnUpdate is called
				    'fnUpdateState': function(oSettings)
				    {
				        var iCurrentPage = Math.ceil((oSettings._iDisplayStart + 1) / oSettings._iDisplayLength);
				        var iTotalPages  = Math.ceil(oSettings.fnRecordsDisplay() / oSettings._iDisplayLength);
				        var iFirstPage 	 = iCurrentPage - oSettings._iShowPagesHalf;
				        var iLastPage 	 = iCurrentPage + oSettings._iShowPagesHalf;

				        $.extend(oSettings, {
				            _iCurrentPage: iCurrentPage,
				            _iTotalPages: iTotalPages,
				            _iFirstPage: iFirstPage,
				            _iLastPage: iLastPage
				        });
				    }
				};

				jQuery.fn.dataTableExt.oPagination[_oConfig.settings.aPaginationTypes[1]] = oPaginationCreator;
				jQuery.fn.dataTableExt.oPagination[_oConfig.settings.aPaginationTypes[2]] = oPaginationCreator;
				jQuery.fn.dataTableExt.oPagination[_oConfig.settings.aPaginationTypes[3]] = oPaginationCreator;
	//****************************************************************************

				// Clear refresh value from aaData
				if (config.aaData == "refresh")
				{
					config.aaData 	= [];
					config.bRefresh = true;
				}

				config.fnServerData = function (sSource, aoData, fnCallback, oSettings)
				{
					// Ajax Call If is refresh or bPreload is False
					if(!config.bPreLoad || config.bRefresh)
					{
						var fnRefreshCallBack = function(oResponse)
						{
							_fnAjaxCallback(config, fnCallback);
						};
						_fnAjax(config, aoData, fnRefreshCallBack);
					}
					else
					{
						// Request AJAX
						_fnAjaxCallback(config, fnCallback);
					}
				};

				config = $.extend({}, _oDefaultConfig, config);
				$(config.id).data("config", config);

				return config;
			};

			var _fnCheckBoxMethods = function(oTable)
			{
				return(oTable.data("checkbox"));
			};

			var _rebuild = function(oTable)
			{
				$.pgsi.progressBar.start();
				$.address.parameter("length", oTable.next().find(_oConfig.common.sLengthContainer).find('select').val());

				var oConfig = oTable.data("config");

				oTable.fnDestroy();

				_createTableHeaders(oConfig);
				oTable.dataTable(_setTable(oConfig));
			};

			// Manipulate auto refresh state on local storage
			var _fnStorageInterval = function (config, value, bDelete)
			{

				var oPropertiesResponse = $.pgsi.storage.get(pgsi.pages.dm.sUserStorageKey);

				if ($.pgsi.isNullOrUndefined(oPropertiesResponse["autoRefresh"]))
				{
					oPropertiesResponse["autoRefresh"] = {};
				}

				// Getter
				if ($.pgsi.isNullOrUndefined(value) && !bDelete)
				{
					return oPropertiesResponse.autoRefresh[config.id + "-auto-refresh"];
				}

				// Set value as disabled on local storage
				if ($.pgsi.isNullOrUndefined(value))
				{
					value = -1;
				}


				// Setter
				oPropertiesResponse.autoRefresh[config.id + "-auto-refresh"] = value;
				$.pgsi.storage.set(pgsi.pages.dm.sUserStorageKey, oPropertiesResponse);
				return;

			}

			// Start Interval saving into Local Storage
			var _startInterval = function(config, $table)
			{
				var fnInterval = function ()
				{
					config.bAutoRefreshReload = true;
					_reloadTable({table : $table, iStart : 0, bHideProgressBar : true})
				}
				_fnStorageInterval(config, setInterval(fnInterval, pgsi.settings.time.autoRefreshTime));
			};

			var _restartInterval = function(config, $table)
			{
				// Check if there is already a auto refresh assigned to current page
				if (_fnStorageInterval(config) != -1)
				{
					clearInterval(_fnStorageInterval(config));
					_createInterval(config, $table);
				}
			};

			// Create Interval
			var _createInterval = function(config, $table)
			{
				_startInterval(config, $table);
				config.$autoRefresh.find("#refresh-page .ui-icon").removeClass("ui-icon").addClass("loading");
				config.$autoRefresh.find(".enable-auto-refresh").addClass("active").find("a").text($.pgsi.locale.get('table.autorefresh.enabled'));
				config.$autoRefresh.find(".disable-auto-refresh").removeClass("active").find("a").text($.pgsi.locale.get('table.autorefresh.disable'));
			};

			// Clear interval
			var _clearInterval = function(config, $table)
			{
				if (_fnStorageInterval(config) != -1)
				{
					clearInterval(_fnStorageInterval(config));
				}
				if (!$.pgsi.isNullOrUndefined($table))
				{
					config.$autoRefresh.find("#refresh-page .loading").removeClass("loading").addClass("ui-icon");
					config.$autoRefresh.find(".enable-auto-refresh").removeClass("active").find("a").text($.pgsi.locale.get('table.autorefresh.enable'));
					config.$autoRefresh.find(".disable-auto-refresh").addClass("active").find("a").text($.pgsi.locale.get('table.autorefresh.disabled'));
				}
			}

			var _buildAutoRefreshElem = function (config, $table)
			{
				var oButton = $('<div id="refresh-page" class="left"><span class="ui-icon"></span></div>');
				var oSelect = $('<div class="select-refresh left"></div>');
				var oBox = $("<ul class='hide'><li class='enable-auto-refresh'><a href='#' class='auto-refresh-event'>" + $.pgsi.locale.get('table.autorefresh.enable') + "</a></li><li class='disable-auto-refresh'><a href='#' class='auto-refresh-event'>" + $.pgsi.locale.get('table.autorefresh.disable') + "</a></li></ul>")

				oButton.click(function (e) {
					e.preventDefault();
					_reloadTable({table : $table, iStart : 0});
				});

				oSelect.click(function (e) {
					if ( oBox.is(":hidden") )
					{
						oBox.slideDown( 300 );
						return;
					}

					oBox.hide();
				});

				oBox.find(".auto-refresh-event").click(function(e) {

					e.preventDefault();

					if ($(this).parent().hasClass("active"))
					{
						return;
					}

					// Check if there is already a auto refresh assigned to current page
					if (_fnStorageInterval(config) != -1)
					{
						_clearInterval(config, $table);

						// Delete Storage
						_fnStorageInterval(config, null, true);

						return;
					}

					// Disable AutoRefresh
					_createInterval(config, $table);

				});

				config.$autoRefresh.empty();
				oSelect.append(oBox);
				config.$autoRefresh.append(oButton);
				config.$autoRefresh.append(oSelect);

				// Hide select when click outside
				$(document).mouseup(function (e)
				{
				    var container = oSelect;

				    if (!container.is(e.target) // if the target of the click isn't the container...
				        && container.has(e.target).length === 0) // ... nor a descendant of the container
				    {
				    	oBox.hide();
				    }
				});

				$table.on("remove", function(e) {
					config.$autoRefresh.empty();
				});
			}

			// Add Auto refresh feature
			var _setAutoRefresh = function(config, $table)
			{

				// Build Autro Refresh Elements
				_buildAutoRefreshElem(config, $table);

				/*
				 * TEMP - This code clean local Storage because state is no longer kept.
				 * In case state store is required again just activate this condidition.
				 */
				// Check if there is already a auto refresh assigned to current page
//				if (_fnStorageInterval(config) == -1)
//				{
//					_clearInterval(config, $table);
//				} else
//				{
//					_createInterval(config, $table);
//				}

				_createInterval(config, $table);

			};

			var _destroyFixedHeader = function(oThis)
			{
				// FIXME - Code throws exception on IE 11
				try
				{
					var fixedHeaderData = oThis.data("fixedHeader");
					if (!$.pgsi.isNullOrUndefined(fixedHeaderData))
					{
						var aoCache = fixedHeaderData.fnGetSettings().aoCache;

						if($.pgsi.isValidArray(aoCache))
						{
							for (var i = 0, l = aoCache.length; i < l; i++)
							{
								aoCache[i].nWrapper.remove();
							}
						}
						// Unbind events
						$(window).off(".FixedHeader"); // event namespace, custom fixedHeader
					}
				} catch (e)
				{

				}
			}

			var _init = function (config)
			{

				if( $.pgsi.isNullOrUndefined(config) || $.pgsi.isNullOrUndefined(config.id) )
				{
					return false
				}

				config = _setTable(config);

				var $table = $(config.id).dataTable(config).
				on( 'length.dt', function ( e, settings, len )
				{
					_fnPageLengthChangeEvent(len, settings, config);
				});

				// Auto Refresh
				if( !$.pgsi.isNullOrUndefined(config.$autoRefresh) )
				{
					_setAutoRefresh(config, $table);
					$table.on("remove", function()
					{
						// Stop Interval when leaving page
						_clearInterval(config);
					});

				}

				return $table;
			};

			return ({
				init					: _init,
				setTable 				: _setTable,
				reloadTable 			: _reloadTable,
				checkbox				: _fnCheckBoxMethods,
				rebuild 	 			: _rebuild,
				clearInterval 			: _clearInterval,
				restartInterval 		: _restartInterval,
				showViewPortFixedHeader : _fnShowViewPortFixedHeader,
				destroyFixedHeader		: _destroyFixedHeader
			});

		})(jQuery);
	});
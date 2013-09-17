<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">

	sensus.modules.dashboard = {

		loadContentModules : function (oResponse, aContents) {

			var length = aContents ? aContents.length : 0;
			var content = sensus.modules.dashboard.content;
			var i = 0;
			var sModule;
			var module;

			// Init modules
			for (; i < length; i = i + 1) {

				sModule = aContents[i];
				module = content[sModule];

				if (module) {

					module.init(oResponse);
				}
			}
		},

		util : {

			createViewAllButton : function ($insertAfter, sUrl, sLabel, iColspan) {

				$('<tr><td class="size" ' + (iColspan > 0 ? ('colspan="' + iColspan + '"') : "")
						+ '><small><a href="' + sUrl + '" class="text-button alist">'
						+ sLabel + ' &raquo;</a></small></td></tr>')
					.insertAfter($insertAfter);
			}
		},

		content : {

			header : {

				baseLink : null,

				activeAlarms : function (oResponse, oHeader) {

					var aAlarms = oResponse.alarmsTypesCount;
					var oAlarm;
					var alarm;
					var length;
					var i;
					var _services;
					var subcolumns;
					var otherSubColumn;
					var iAllOthersAmount;

					if (aAlarms) {

						length = aAlarms.length;

						iAllOthersAmount = 0;
						otherSubColumn = {
							label : "groupcreate.page.allothers",
							count : 0,
							url : null
						};

						_services = sensus.constants.services;

						// Define default alarms by service;
						switch (sensus.settings.serviceType) {

						case _services.electric.name :

							subcolumns = {
								RELAY_CURRENT_TAMPER : this.util.getActiveAlarmSubColumn("RELAY_CURRENT_TAMPER", this.baseLink),
								RELAY_VOLTAGE_TAMPER : this.util.getActiveAlarmSubColumn("RELAY_VOLTAGE_TAMPER", this.baseLink)
							};
							break;

						case _services.gas.name :

							subcolumns = {
								TILT : this.util.getActiveAlarmSubColumn("TILT", this.baseLink),
								allOthers : otherSubColumn
							};
							break;

						case _services.water.name :

							subcolumns = {
								LEAK_DETECTED : this.util.getActiveAlarmSubColumn("LEAK_DETECTED", this.baseLink),
								allOthers : otherSubColumn
							};
							break;
						}

						// Add column
						oHeader.rows.installed.columns.activeAlarms = {
							label : "dashboard.page.summary.activeAlarms",
							subcolumns : subcolumns
						};

						// Fill subcolumns
						for (i = 0; i < length; i = i + 1) {

							oAlarm = aAlarms[i];
							alarm = oAlarm.alarm;

							if (subcolumns[alarm]) {

								subcolumns[alarm].count = oAlarm.amount;

							} else {

								iAllOthersAmount = iAllOthersAmount + oAlarm.amount;
							}
						}

						if (subcolumns.allOthers) {

							otherSubColumn.count = iAllOthersAmount;
							otherSubColumn.url = this.baseLink + this.util.getAllOthersAlarmsLink(["TILT", "LEAK_DETECTED"]);
						}
					}
				},

				deviceType : function (oResponse, oHeader) {

					var aDeviceTypeCount = oResponse.deviceTypeCountList;
					var oDeviceTypeCount;
					var allDevicesAmount;
					var deviceSubType;
					var deviceType;
					var subcolumns;
					var baseLink;
					var _columns;
					var columns;
					var oDevice;
					var length;
					var i;

					if (aDeviceTypeCount && aDeviceTypeCount.length) {

						_columns = oHeader.rows.installed.columns;
						length = aDeviceTypeCount.length;
						baseLink = "electriclist?";
						allDevicesAmount = 0;

						for (i = 0; i < length; i = i + 1) {

							oDeviceTypeCount = aDeviceTypeCount[i];
							oDevice = oDeviceTypeCount.device;
							deviceType = oDevice.deviceType;

							if (!_columns[deviceType]) {

								_columns[deviceType] = {
									label : "commons.pages." + deviceType,
									subcolumns : {}
								};
							}

							deviceSubType = oDevice.hanDeviceTypeEnum || oDevice.lcmTypeEnum || "total";

							_columns[deviceType].subcolumns[deviceSubType] = {
								label : "commons.pages." + deviceSubType,
								count : oDeviceTypeCount.deviceCount,
								url : baseLink + "&device_type=" + deviceType + "|" + this.util.getDeviceListTabLink(deviceType, deviceSubType)
							};

							allDevicesAmount = allDevicesAmount + oDeviceTypeCount.deviceCount;
						}

						if (allDevicesAmount) {

							oHeader.rows.installed.columns.allDevices.subcolumns.total.count = allDevicesAmount;
						}
					}
				},

				communications : function (oResponse, oHeader) {

					var aStatusCount = oResponse.waterGasMeterStatusCount;
					var allDevicesAmount;
					var oStatusCount;
					var statusEnum;
					var subcolumns;
					var length;
					var i;

					if (aStatusCount && aStatusCount.length) {

						length = aStatusCount.length
						allDevicesAmount = 0;
						subcolumns = {};

						for (i = 0; i < length; i = i + 1) {

							oStatusCount = aStatusCount[i];

							statusEnum = oStatusCount.waterGasMeterStatusEnum;

							subcolumns[statusEnum] = {
							    label : "filter.status_meter." + statusEnum.toLowerCase(),
								count : oStatusCount.amount,
								url : this.baseLink + "&status_meter=" + statusEnum + "|"
							};

							allDevicesAmount = allDevicesAmount + oStatusCount.amount;
						}

						if (allDevicesAmount) {

							oHeader.rows.installed.columns.allDevices.subcolumns.total.count = allDevicesAmount;
						}

						oHeader.rows.installed.columns.communications = {
							label : "dashboard.page.summary.communications",
							subcolumns : subcolumns
						};
					}
				},

				quarantine : function (oResponse, oHeader) {

					oHeader.rows.installed.columns.dataQuality = {
						label : "dashboard.page.header.quality",
						subcolumns : {
							quarantine : {
								label : "filter.quarantine.name",
								count : oResponse.quarantineAmount,
								url : this.baseLink + "&quarantine=true|"
							}
						}
					};
				},

				allDevices : function (oResponse, oHeader) {

					//var deviceCount = oResponse.firstDeviceTypeCountList || {device : {deviceCount : 0}};

					oHeader.rows.installed.columns.allDevices = {
						label : "commons.pages.totalDevices",
						subcolumns : {
							total : {
								label : "commons.pages.total",
								count : 0,//deviceCount.deviceCount,
								url : this.baseLink
							}
						}
					};
				},

				createHeaderColumn : function (value, clazz, rowspan, colspan) {

					return ["<th ", (clazz ? "class='" + clazz + "' " : ""),
					        (rowspan > 1 ? ("rowspan='" + rowspan + "' ") : ""),
					        (colspan > 1 ? ("colspan='" + colspan + "' ") : ""),
					        ">", value, "</th>"].join("");
				},

				createBodyColumn : function (value, clazz, rowspan, colspan) {

					return ["<td ", (clazz ? "class='" + clazz + "' " : ""),
					        (rowspan > 1 ? ("rowspan='" + rowspan + "' ") : ""),
					        (colspan > 1 ? ("colspan='" + colspan + "' ") : ""),
					        ">", value, "</td>"].join("");
				},

				createLink : function (value, clazz, link) {

					if (value > 0) {

						return ["<a ", (clazz ? "class='" + clazz + "' " : ""), "href='", (link || "#") ,"' >", $().numberFormat(value), "</a>"].join("");
					}

					return $().numberFormat(value);
				},

				util : {

					getAllOthersAlarmsLink : function (aAlarmsToIgnore) {

						var deviceTypePermissionIndex = 0;
						var alarms;

						if (sensus.settings.serviceType == sensus.constants.services.electric.name) {

							// On Electric Service, only LCM has Alarms
							deviceTypePermissionIndex = 2;
						}

						alarms = sensus.settings.oDeviceTypeParameters.deviceTypePermissions[deviceTypePermissionIndex].alarms;

						// remove alarms
						if (aAlarmsToIgnore && aAlarmsToIgnore.length) {

							alarms = $.grep(alarms, function(alarm) {

							    return $.inArray(alarm, aAlarmsToIgnore) == -1;
							});
						}

						if (alarms) {

							return "&alarm=" + alarms.join("|") + "|";
						}

						return "";
					},

					getActiveAlarmSubColumn : function (alarm, baseLink) {

						return {
							label : "filter.alarm." + alarm.toLowerCase(),
							count : 0,
							url : baseLink + "&alarm=" + alarm + "|"
						};
					},

					getDeviceListTabLink : function (sDeviceType, sDeviceSubType) {

						var _electric;

						if (sDeviceSubType == "total") {

							return "&lifecycle_state=INSTALLED|";
						}

						_electric = sensus.constants.services.electric;

						if (sDeviceType == _electric.meter.name ||
								sDeviceSubType == _electric.lcm.flexNetLCM) {

							return "&device_subtype=" + sDeviceSubType + "|&lifecycle_state=INSTALLED|";
						}

						if (sDeviceType == _electric.han.name) {

							return "&device_subtype=" + sDeviceSubType + "|&lifecycle_state=JOINED|";
						}

						return "";
					}
				},

				buildHeader : function (oHeader) {

					var _get = _get = sensus.locale.get;

					var $header = ["<tr class='header'>", this.createHeaderColumn("<h2>" + _get(oHeader.title) + "</h2>", "title", 2)],
						$subheader = ["<tr class='subheader'>"],
						$tbody = ["<tr>"];

					var row, _rows = oHeader.rows,
						column, _columns,
						subcolumn, _subcolumns;

					var subheaderCount;

					var $headerTable = $("#header-table");

					// rows
					for (row in _rows) {

						row = _rows[row];
						_columns = row.columns;

						$tbody.push(this.createBodyColumn(_get(row.label), "row-title-sub"));

						// columns
						for (column in _columns) {

							column = _columns[column];
							_subcolumns = column.subcolumns;

							subheaderCount = 0;

							// subcolumn
							for (subcolumn in _subcolumns) {

								subcolumn = _subcolumns[subcolumn];

								// sub header
								$subheader.push(this.createHeaderColumn(_get(subcolumn.label)));

								// body
								$tbody.push(this.createBodyColumn(
										this.createLink(subcolumn.count, "alist", subcolumn.url)));

								subheaderCount += 1;
							}

							// header
							$header.push(this.createHeaderColumn(_get(column.label), null, null, subheaderCount));
						}
					}

					// Close tags
					$header.push("</tr>");
					$subheader.push("</tr>");
					$tbody.push("</tr>");

					// Set on DOM
					$headerTable.find("thead").html($header.join("") + $subheader.join(""));
					$headerTable.find("tbody").html($tbody.join(""));

					// Add style classes
					$headerTable.find(".header th:eq(1), .subheader th:eq(0), tbody td:nth-child(2)").addClass("today");
					$headerTable.find(".header th:eq(2), .subheader th:gt(0)").addClass("week");
					$headerTable.find(".header th:gt(2)").addClass("month");
				},

				init : function (oResponse) {

					var dashboardHeader;
					var _services;
					var oHeader;

					if (oResponse.dashboardHeader) {

						_services = sensus.constants.services;

						oHeader = {
							title : "commons.pages.smartPoints",
							rows : {
								installed : {
									label : "commons.pages.Installed",
									columns : {}
								}
							}
						};

						dashboardHeader = oResponse.dashboardHeader;

						this.baseLink = $("#menu-smartpoint").attr("href");

						this.allDevices(dashboardHeader, oHeader);

						switch (sensus.settings.serviceType) {

						case _services.electric.name :

							this.deviceType(dashboardHeader, oHeader);
							break;

						case _services.gas.name :
						case _services.water.name :

							this.communications(dashboardHeader, oHeader);
							this.quarantine(dashboardHeader, oHeader);
							break;
						}

						this.activeAlarms(dashboardHeader, oHeader);

						this.buildHeader(oHeader);
					}
				}
			},

			savedSearches : {

				init : function (oResponse) {

					var $searchTable;

					if (oResponse.customSearches) {

						$searchTable = $("#search-table");

						// jQuery dataTable setup for Saved Searches Table
						sensus.pages.dashboard.searchTable = $searchTable.dataTable(sensus.widgets.datatable.setTable({

							id : "search-table",
							sAjaxSource : "api/search/fetch",
							oPreLoadResponse : oResponse.customSearches,
							bPreLoad : true,
							aColumns : [{ sId : "Id",          bVisible : false},
										{ sId : "name", 	   sWidth : "5%", bSortable : false},
										{ sId : "description", sWidth : "5%", bSortable : false}],
							oSettings : {
								oRequest : InquiryPaginationRequest,
								fnRequest : function() {
									return {
										pageSize: 5,
										startRow: 0,
										endRow: 0,
										sortExpressions : [{direction : "Descending", field : "name"}]
									};
								},
								sortEnum 		: 'BaseSortEnum',
								iDefaultCol 	: 1,
								sDefaultSort 	: "asc",
								sResponseObj 	: "customSearches",
								aColumns	 	: ["id", "name", "description", "searchParameters"],
								bFooterVisible 	: false
							},

							fnRowCallback : function (nRow, aData, iDisplayIndex, oColumn) {

								$("td:eq(" + oColumn.name + ")", nRow).html("<a href='#' data-id='" + aData[oColumn.Id] + "'>"
										+ aData[oColumn.name] + "</a>");
							},

							fnDrawCallback : function (oSetting, oColumn) {

								<sec:authorize access="!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
									sensus.modules.dashboard.util.createViewAllButton($('#search-table tbody tr:last'),
										"savedSearch", sensus.locale.get("commons.pages.viewAll"), 3);
								</sec:authorize>
							}
						}));

						$searchTable.on("click", "a:not(.alist)", function (e) {

							e.preventDefault();

							sensus.util.page.startGlobalProgressBar();

							$.ajaxValidator.fnDoCall("api/search/fetch", {
								customSearch : {id :  $(this).data("id")},
								sortExpressions : [{field: "name", direction: "Ascending"}]
							}, false, function(oCustomSearchResponse) {

								var oFilledFilters;
								var oCustomSearch;

								if (oCustomSearchResponse && oCustomSearchResponse.customSearches
										&& oCustomSearchResponse.customSearches.length) {

									oCustomSearch = oCustomSearchResponse.customSearches[0];
									oFilledFilters = sensus.pages.search.fillFilters(oCustomSearch.searchParameters);

									$.fn.pageLoader.load("savedSearch?saved=true&name=" + escape(oCustomSearch.name)
											+ "&" + decodeURI(oFilledFilters.url), $("#load"), null, null, null, false);
								}

							}, null, true);
						});
					}
				}
			},

			eventsToday : {

				init : function (oResponse) {

					if (oResponse.todayProcesses) {

						// jQuery Today Events Data Table Setup
						sensus.pages.dashboard.eventTable = $('#event-table').dataTable(sensus.widgets.datatable.setTable({

							id : "event-table",
							sAjaxSource : sensus.util.process.api.fetch,
							oPreLoadResponse : oResponse.todayProcesses,
							bPreLoad : true,
							aColumns : [{sId: "Id",				sWidth: "5%",	bVisible  : false},
										{sId: "deviceType", 	bVisible : false},
										{sId: "event",			sWidth: "5%", 	bSortable : false},
										{sId: "type",			sWidth: "5%",	bSortable : false},
										{sId: "total",			sWidth: "5%", 	bSortable : false},
										{sId: "failed",			sWidth: "5%", 	bSortable : false},
										{sId: "startTime",		sWidth: "5%",	bVisible  : false},
										{sId: "status",			sWidth: "5%", 	bSortable : false},
										{sId: "processStatus",	bVisible: false},
										{sId: "deviceSubType", 	bVisible: false}
							],
							oSettings : {
								oRequest : InquiryProcessRequest,
								fnRequest : function() {

									var sDateToday = $.datepicker.formatDate(sensus.settings.dateFormatMask.replace('yyyy', 'yy'), new Date());

									var processSearch = new ProcessSearch({
										endDate		: sensus.util.page.fnFormatDateFilter(sDateToday, 'endDate'),
										startDate 	: sensus.util.page.fnFormatDateFilter(sDateToday, 'setStartDate')
									});

									return {
										processSearch : processSearch,
										pageSize: 5,
										startRow: 0,
										endRow: 4
									};
								},
								sortEnum 		: 'ProcessOrderByEnum',
								iDefaultCol 	: 6,
								sDefaultSort 	: "desc",
								sResponseObj 	: "processes",
								aColumns	 	: ["id", "fn(sensus.util.process.fnCreateProcessDeviceType)",
												   "description", "fn(sensus.util.process.fnCreateProcessCategoryType)",
												   "totalSmartpoints", "failedSmartpoints", "startTime",
												   "fn(sensus.util.process.fnCreateStatus)",
												   "processStatusEnumValue", "propertyHanDeviceType"],

								bFooterVisible 	: false
							},

							fnRowCallback : function(nRow, aData, iDisplayIndex, oColumn) {

								// Device Type
								if (aData[oColumn.deviceType])
									$("td:eq("+ oColumn.deviceType +")", nRow).html(sensus.locale.get("commons.pages."+aData[oColumn.descriptionEvent]));

								// Event Column
								$("td:eq("+ oColumn.event +")", nRow).html(
										sensus.util.process.fnCreateEventColumn(aData[oColumn.Id], aData[oColumn.event],
												aData[oColumn.type], aData[oColumn.status], "event-table",
												aData[oColumn.deviceType], aData[oColumn.deviceSubType]));

								// Action Column
								$("td:eq("+ oColumn.type +")", nRow).html(
										sensus.locale.get(aData[oColumn.type]));

								// Total Column
								$("td:eq("+ oColumn.total +")", nRow).addClass('num number-format');

								// Failed Column
								$("td:eq("+ oColumn.failed +")", nRow)
									.addClass('num number-format')
									.css({'color' : '#990000'});
							},

							fnDrawCallback : function (oSetting, oColumn) {

								sensus.modules.dashboard.util.createViewAllButton($('#event-table tbody tr:last'),
										"process/history", sensus.locale.get("commons.pages.viewAll"), 9)
							}

						}));
					}
				}
			}
		}
	}
	</script>

</sec:authorize>
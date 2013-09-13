<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
		$(document).ready(function() {

			var SCHEDULED = sensus.locale.get("commons.pages.SCHEDULED");
			var sActionTitle = sensus.locale.get("systemintelligence.page.history.actionType.title") + " ";
			var $csv = $("#csv");

			sensus.util.page.initMessagingTabs();

			// jQuery Today data table setup
			sensus.pages.processtoday.todayTable = $('#today-table').dataTable(sensus.widgets.datatable.setTable({

				id : "today-table",
				sAjaxSource : sensus.util.process.api.fetch,

				<c:choose>
					<c:when test="${not empty refresh}">
						oPreLoadResponse : "refresh",
					</c:when>
					<c:when test="${empty response}">
						oPreLoadResponse : null,
					</c:when>
					<c:otherwise>
						oPreLoadResponse : ${response},
					</c:otherwise>
				</c:choose>
				bPreLoad : true,

				aColumns : [
					{sId: "Id", bVisible : false},
					{sId: "deviceType", bVisible : false},
					{sId: "actionType", sWidth : "5%", bSortable : false},
					{sId: "ActionName", sWidth : "5%", bSortable : false},
					{sId: "descriptionEvent", sWidth : "5%", bSortable : false},
					{sId: "total", sWidth : "5%"},
					{sId: "failed", sWidth : "5%"},
					{sId: "requestedBy", sWidth : "5%"},
					{sId: "startTime", sWidth : "5%"},
					{sId: "status", sWidth : "5%"},
					{sId: "deviceSubType", bVisible : false}
				],

				oSettings : {
					oRequest : InquiryProcessRequest,
					fnRequest : sensus.pages.processtoday.fnRequestAction,

					sortEnum : 'ProcessOrderByEnum',
					order : "status",
					fnOrderDecorator : function(sStatus) {

						if (sStatus == sensus.locale.get("summary.text.processStatus.CANCELLED") || sStatus == sensus.locale.get("summary.text.processStatus.EXPIRED")) {

							return "<strong>" + sensus.locale.get("summary.text.processStatus.CANCELLED") + " / " + sensus.locale.get("summary.text.processStatus.EXPIRED") + "</strong>";
						}

						return "<strong>" + sStatus + "</strong>";
					},

					iDefaultCol  : 8,
					sDefaultSort : 'desc',
					sResponseObj : 'processes',
					aColumns     : ["id", "fn(sensus.util.process.fnCreateProcessDeviceType)", "fn(sensus.util.process.fnCreateProcessCategoryType)",
									"fn(sensus.util.process.fnCreateProcessName)", "description",
									"totalSmartpoints", "failedSmartpoints", "createUser", "startTime",
									"fn(sensus.util.process.fnCreateStatus)", "propertyHanDeviceType"],

					process : {
						sSmartpointSize : "total",
						<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR')">
							abort : sensus.util.process.actions.abortDialogSetting,
						</sec:authorize>

						oAction : {
							sStatusColumn : 'status',
							expire : {
								title : sensus.locale.get("expire.title"),
								expireableActions : sensus.util.process.expireableActions,
								dialogSettings : sensus.util.process.actions.expireDialogSettings
							},
							edit  : {
								url 	: "schedule/edit"
							},
							clone : {
								url 	: "schedule/edit"
							},
							<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR')">
								cancel : {
									url : "api/schedule/delete",
									text : function(data, i) {

										// Get the object with
										var oColumn = sensus.widgets.datatable.oTableSettings.aColumns.columns;
										var sId = 'EventDescription';

										for (var key in oColumn) {

											if(oColumn[key].sId === sId || oColumn[key].sId === "descriptionEvent") {

												var sNameSchedule = data[key];

												if ($.sc.isNullOrUndefined(sNameSchedule)) {
													return "";
												}

												if (sNameSchedule.contains("[OnDemand|")) {
													sNameSchedule = sNameSchedule.substring(0, sNameSchedule.indexOf("[OnDemand|"));
												}

												if (sNameSchedule.contains("[Schedule.")) {
													sNameSchedule = sNameSchedule.substring(sNameSchedule.indexOf("]") + 1, sNameSchedule.length);
												}


												sNameSchedule = sNameSchedule.replace(/\[/g, ' ');
												sNameSchedule = sNameSchedule.replace(/\]/g, ' ');

												return sensus.locale.get("systemintelligence.scheduledEvent.a.titleDelete", sNameSchedule);
											}

										}

									},
									success : function(data, i) {
										sensus.widgets.datatable.reloadTable(sensus.pages.processtoday.todayTable);
										return (sensus.locale.get("systemintelligence.scheduledEvent.deleteFail", data[i.eventName]));
									}
								},
							</sec:authorize>
							pause  : {
								url : "api/schedule/update",
								success : function(data, i) {
									sensus.widgets.datatable.reloadTable(sensus.pages.processtoday.todayTable);
									return "";
								}
							},
							resume : {
								url : "api/schedule/update",
								success : function(data, i) {
									sensus.widgets.datatable.reloadTable(sensus.pages.processtoday.todayTable);
									return "";
								}
							},
							<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR')">
								bAbortAction : true,
								abortTitle : function(data, i) {

									var sActionName = data[i.ActionName];

									if (sActionName == "sensus.dm.action.send.han.text.message"
											|| sActionName == "sensus.dm.action.demand.response") {
										return "commons.pages.cancel";
									}
									return "commons.pages.expire";
								}
							</sec:authorize>
						}
					}
				},

				$exportCSVElement : $csv,

				fnRowCallback : function(nRow, aData, iDisplayIndex, oColumn) {

					// Device Type
					if (aData[oColumn.deviceType])
						$("td:eq("+ oColumn.deviceType +")", nRow).html(sensus.locale.get("commons.pages."+aData[oColumn.descriptionEvent]));

					// Event Column
					$("td:eq("+ oColumn.descriptionEvent +")", nRow).html(
							sensus.util.process.fnCreateEventColumn(
									aData[oColumn.Id], aData[oColumn.descriptionEvent], aData[oColumn.actionType], aData[oColumn.status],
									"today-table", aData[oColumn.deviceType], aData[oColumn.deviceSubType]));

					// Action Type Column
					$("td:eq("+ oColumn.actionType +")", nRow).html(
							sensus.locale.get(aData[oColumn.actionType]))
							.attr("title",  sActionTitle + aData[oColumn.Id]);

					// Action Name Column
					$("td:eq("+ oColumn.ActionName +")", nRow).html(
							sensus.util.process.fnCreateActionNameColumn(aData[oColumn.ActionName], aData[oColumn.status], aData[oColumn.Id]));

					if (SCHEDULED == aData[oColumn.status]) {

						var bScheduled = true;

					} else {

						var bScheduled = false;
					}

					// Total Column
					$("td:eq("+ oColumn.total +")", nRow).addClass('num number-format').html(
							sensus.util.process.fnCreateLinkSmartpointsDialog(aData[oColumn.total], sensus.util.process.summaryType.total, aData[oColumn.Id], bScheduled));

					// Failed Column
					$("td:eq("+ oColumn.failed +")", nRow).addClass('num number-format alerts').html(
							sensus.util.process.fnCreateLinkSmartpointsDialog(aData[oColumn.failed], sensus.util.process.summaryType.failed, aData[oColumn.Id], bScheduled));

					// Start Time Column, Date format
					var aStartTime = $.date.dateFormat(aData[oColumn.startTime], "h:i A").split(" ");
					$("td:eq("+ oColumn.startTime +")", nRow).html(aStartTime[0] + " " + "<span class='timezone'>" + aStartTime[1] + "</span>");

					// Add triangle to process with parent
					if (aData[oColumn.parentId] !== "") {

						$("td:eq("+ oColumn.Id +")", nRow)
							.addClass("spindown")
							.append("<span class='ui-icon-triangle-1-e ui-icon' id='"+ aData[oColumn.parentId] +"'></span>");
					}
				},

				fnDrawCallback : function(setting, oColumn) {

					$('.row-header td').removeClass('hide');
				}
			}));

			// hide pagination and add link for Event History
			$('.paging').html(
					$('<a aria-disabled="false" role="button" href="' + "process/history" + '" id="event-history-button"/>')
						.addClass("alist button left ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only")
						.html('<span class="ui-button-text">' + sensus.locale.get("systemintelligence.page.today.viewAllEventHistory") + '</span>'));

			// hide pagination select options
			$('.numbers').html("");

			// To Generate the archive CSV
			sensus.util.exportcsv.setGenerateCSVEvent({

				url : "api/export/generateTodayCSV",

				$element : $csv,

				getGenerateRequestCSV : function() {

					// Get Request populate with current filters
					var request = new InquiryProcessRequest(sensus.pages.processtoday.fnRequestAction());

					// Default Request Parameters
					request.sortExpressions = sensus.util.page.getSortExpression(sensus.pages.processtoday.todayTable);

					return request;
				}
			});

			// Set current date
			$("#dateToday").html($.date.dateFormat($.date.setDateServer(true), "M dd, yy"));

			sensus.util.page.stopGlobalProgressBar();
		});
	</script>

</sec:authorize>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and 
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
	$(document).ready(function() {

		// jQuery Recent Requests data table setup
		sensus.pages.longrunningprocess.lrpTable = $("#process-table").dataTable(sensus.widgets.datatable.setTable({

			id : "process-table",
			sAjaxSource : sensus.util.process.api.fetch,
			aColumns : [
				{sId: "parentId", bVisible : false},
				{sId: "Id", bVisible : false},
				{sId: "deviceType", bVisible : false},
				{sId: "ActionName", sWidth : "5%", bSortable : false},
				{sId: "descriptionEvent", sWidth : "5%", bSortable : false},
				{sId: "total", sWidth : "5%", bSortable : false},
				{sId: "failed", sWidth : "5%", bSortable : false},
				{sId: "startTime", sWidth : "5%", bSortable : false},
				{sId: "completed", sWidth : "5%", bSortable : false},
				{sId: "status", sWidth : "5%", bSortable : false},
				{sId: "actionType", sWidth : "5%", bVisible : false},
				{sId: "endTime", sWidth : "5%", bVisible : false},
				{sId: "secToComplete", sWidth : "5%", bVisible : false},
				{sId: "deviceSubType", bVisible : false}
			],

			oSettings : {
				oRequest : InquiryProcessRequest,
				fnRequest : function() {
					return {
						sortExpressions : [{"field" : "status", "direction" : "Descending"},
										   {"field" : "start_time", "direction": "Descending"}],
						pageSize : null,
						processes : [new Process({"monitoredInstance" : true, "dashboardMonitored" : false})],
						startRow : 0,
						endRow : 0
					};
				},
				sResponseObj : 'processes',
				aColumns     : ["parentProcess", "id", "fn(sensus.util.process.fnCreateProcessDeviceType)", "fn(sensus.util.process.fnCreateProcessName)", "description", "totalSmartpoints",
								"failedSmartpoints", "startTime", "startTime", "fn(sensus.util.process.fnCreateStatus)",
								"fn(sensus.util.process.fnCreateProcessCategoryType)", "endTime", "estimatedSecondsToComplete", "propertyHanDeviceType"],

				bDialogCheckbox : true,

				process : {

					abort : sensus.util.process.actions.abortDialogSetting,

					removeRecentRequest : sensus.util.process.actions.removeRecentRequestDialogSetting,

					oAction : {

						expire : {
							title : sensus.locale.get("expire.title"),
							expireableActions : sensus.util.process.expireableActions,
							dialogSettings : sensus.util.process.actions.expireDialogSettings
						},

						bAbortAction : true,
						abortTitle : function(data, i) {

							var sActionName = data[i.ActionName];

							if (sActionName == "sensus.dm.action.send.han.text.message"
									|| sActionName == "sensus.dm.action.demand.response") {
								return "commons.pages.cancel";
							}
							return "commons.pages.expire";
						}
					}
				},
				bFooterVisible : false
			},

			fnRowCallback : function(nRow, aData, iDisplayIndex, oColumn) {

				// Device Type
				if (aData[oColumn.deviceType]) {
					$("td:eq("+ oColumn.deviceType +")", nRow).html(sensus.locale.get("commons.pages."+aData[oColumn.descriptionEvent]));
				}

				// Event Column
				$("td:eq("+ oColumn.descriptionEvent +")", nRow).html(
						sensus.util.process.fnCreateEventColumn(
								aData[oColumn.Id], aData[oColumn.descriptionEvent], aData[oColumn.actionType], aData[oColumn.status],
								"process-table", aData[oColumn.deviceType], aData[oColumn.deviceSubType]));

				// Action Name Column
				$("td:eq("+ oColumn.ActionName +")", nRow).html(sensus.locale.get(aData[oColumn.ActionName]))
					.attr("title",  sensus.locale.get("systemintelligence.page.history.actionType.title") + " " + aData[oColumn.Id]);

				// Total Column and Failed Column Style Class
				$("td:eq("+ oColumn.total +")", nRow).addClass('number-format');
				$("td:eq("+ oColumn.failed +")", nRow).addClass('number-format');

				// Start Time Column, Date Style Format '10:12AM Today'
				var aStartTime 		= $.date.dateFormat(aData[oColumn.startTime], "h:i A").split(" ");
				var sDateFormated 	= $.date.dateFormat(aData[oColumn.startTime], "day");

				$("td:eq("+ oColumn.startTime +")", nRow).html(aStartTime[0] + " " + "<span class='timezone'>" + aStartTime[1]
					+ "</span><br><small>" + sDateFormated + "</small>");

				// Completed In Column
				$("td:eq(" + oColumn.completed + ")", nRow).html(
						sensus.util.process.fnCreateCompletedInColumn(aData[oColumn.status], aData[oColumn.secToComplete],
								$.date.fnGetDate(aData[oColumn.startTime]), $.date.fnGetDate(aData[oColumn.endTime])));

				// Set Process ID to Summary View
				if (aData[oColumn.status] == sensus.locale.get("table.type.complete")
						&& aData[oColumn.total] > 0) {

					$(nRow).addClass("summary");
					$(nRow).data("id", aData[oColumn.Id]);
				}

				// Add triangle to process with parent
				if (!$.ajaxValidator.fnIsNullOrUndefined(aData[oColumn.parentId])) {

					$("td:eq("+ oColumn.Id +")", nRow)
						.addClass("spindown")
						.append("<span class='ui-icon-triangle-1-e ui-icon' id='"+ aData[oColumn.parentId] +"'></span>");

				}
			},

			fnDrawCallback : function(oSetting, oColumn) {

				// Summary View
				var sSummary = '<tr class="summary-container"><td colspan="13"><a class="summary-toggle" href="">'
					+ sensus.locale.get("longRunning.table.viewSummary") +
					'</a><ul class="ui-state-highlight summary hide"></td></tr>';

				$(".summary", "#process-table").each(function(i, item) {

					var $summary = $(sSummary);
					var id 		 = $(this).data("id");

					// Toggle Button
					$summary.find(".summary-toggle").toggle(

						function() {

							var $this 		= $(this);
							var $summaryBox = $this.parent().find(".summary");

							$('.summary-container td').css({'white-space' : 'normal'});

							$this.text(sensus.locale.get("longRunning.table.closeSummary"));

							if (!$summaryBox.data("loaded")) {

								$summaryBox.summary(id, "", true);
								$summaryBox.data("loaded", true);

							}

							$this.parent().find(".summary").slideDown("500");
						},

						function() {

							var $this = $(this);

							$this.text(sensus.locale.get("longRunning.table.viewSummary"));

							$this.parent().find(".summary").slideUp("500");
						}
					)

					$(item).data("summary", $summary);
					$summary.insertAfter($(item));

				});
			}
		}));

		// Remove Recent Requests Selected Function
		$('#processing-container').delegate("#remove-all-lrp", "click", function(e) {

			e.preventDefault();

			var aSelectedIds = sensus.widgets.datatable.selectedDialogRows;

			if (aSelectedIds.length != 0 ) {

				// ProcessRequest set Processes IDs and Monitored false
				var oProcessRequest = sensus.pages.longrunningprocess.fnCreateProcessRequest(aSelectedIds, {monitoredInstance: false});

				$.ajaxValidator.fnDoCall(sensus.util.process.api.updateStatus, oProcessRequest, false, function(oResponse) {

					sensus.pages.longrunningprocess.longRunningProcessSystemMessaging();

					sensus.widgets.datatable.reloadTable(sensus.pages.longrunningprocess.lrpTable);

				});

			} else {

				$('.message-recent-request').addClass("ui-state-error");
			}
		});
	});
	</script>

</sec:authorize>
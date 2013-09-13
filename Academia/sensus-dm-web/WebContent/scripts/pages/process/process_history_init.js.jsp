<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
		$(document).ready(function() {

			var sActionTitle = sensus.locale.get("systemintelligence.page.history.actionType.title") + " ";
			var $csv = $("#csv");

			sensus.util.page.initMessagingTabs();

			// Set start date filter: current date and 10 days
			if (!($.address.parameter("view_from") && $.address.parameter("total_days"))) {

				$.fn.pageLoader.parameter("view_from", $.datepicker.formatDate(sensus.settings.dateFormatMask.replace('yyyy', 'yy'), $.date.setDateServer()));
				$.fn.pageLoader.parameter("total_days", 10);
			}

			// jQuery History data table setup
			sensus.pages.processhistory.historyTable = $('#history-table').dataTable(sensus.widgets.datatable.setTable({

				id : "history-table",
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
					{sId: "parentId", bVisible : false},
					{sId: "Id", bVisible : false},
					{sId: "deviceType", bVisible : false},
					{sId: "actionType", sWidth : "5%", bSortable : false},
					{sId: "ActionName", sWidth : "5%", bSortable : false},
					{sId: "descriptionEvent", sWidth: "5%", bSortable: false},
					{sId: "total", sWidth : "5%", bSortable : true},
					{sId: "failed", sWidth : "5%", bSortable : true},
					{sId: "requestedBy", sWidth : "5%", bSortable : true},
					{sId: "startTime", sWidth : "5%", bSortable : true},
					{sId: "status", sWidth : "5%", bSortable : true},
					{sId: "deviceSubType", bVisible : false}
				],

				oSettings : {
					oRequest : InquiryProcessRequest,
					fnRequest : sensus.pages.processhistory.fnRequestAction,

					sortEnum : 'ProcessOrderByEnum',
					order : "startTime",
					fnOrderDecorator : function(date) {
						// Formate process date
						return $.date.dateFormat(date, "day");
					},

					iDefaultCol  : 9,
					sDefaultSort : "desc",
					sResponseObj : 'processes',
					aColumns     : ["parentProcess", "id", "fn(sensus.util.process.fnCreateProcessDeviceType)",
									"fn(sensus.util.process.fnCreateProcessCategoryType)",
									"fn(sensus.util.process.fnCreateProcessName)", "description",
									"totalSmartpoints", "failedSmartpoints", "createUser", "startTime",
									"fn(sensus.util.process.fnCreateStatus)",
									"propertyHanDeviceType"],

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
						$("td:eq("+ oColumn.deviceType +")", nRow).html(sensus.locale.get("commons.pages." + aData[oColumn.descriptionEvent]));

					// Event Column
					$("td:eq("+ oColumn.descriptionEvent +")", nRow).html(
							sensus.util.process.fnCreateEventColumn(aData[oColumn.Id],
									aData[oColumn.descriptionEvent], aData[oColumn.actionType], aData[oColumn.status],
									"history-table", aData[oColumn.deviceType], aData[oColumn.deviceSubType]));

					// Action Type Column
					$("td:eq("+ oColumn.actionType +")", nRow).html(
							sensus.locale.get(aData[oColumn.actionType]))
							.attr("title",  sActionTitle + aData[oColumn.Id]);

					// Action Name Column
					$("td:eq("+ oColumn.ActionName +")", nRow).html(
							sensus.util.process.fnCreateActionNameColumn(aData[oColumn.ActionName], aData[oColumn.status], aData[oColumn.Id]));

					// Total Column
					$("td:eq("+ oColumn.total +")", nRow).addClass('num number-format').html(
							sensus.util.process.fnCreateLinkSmartpointsDialog(aData[oColumn.total], sensus.util.process.summaryType.total, aData[oColumn.Id]));

					// Failed Column
					$("td:eq("+ oColumn.failed +")", nRow).addClass('num number-format alerts').html(
							sensus.util.process.fnCreateLinkSmartpointsDialog(aData[oColumn.failed], sensus.util.process.summaryType.failed, aData[oColumn.Id]));

					// Start Time Column, Date format
					var aStartTime = $.date.dateFormat(aData[oColumn.startTime], "h:i A").split(" ");
					$("td:eq("+ oColumn.startTime +")", nRow).html(aStartTime[0] + " " + "<span class='timezone'>" + aStartTime[1] + "</span>");

					// Add triangle to process with parent
					if (aData[oColumn.parentId]) {

						$("td:eq("+ oColumn.Id +")", nRow)
							.addClass("spindown")
							.append("<span class='ui-icon-triangle-1-e ui-icon' id='"+ aData[oColumn.parentId] +"'></span>");
					}
				},

				fnDrawCallback : function(setting, oColumn) {

					$('.row-header td').removeClass('hide');
				}
			}));

			<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

				var oPreLoad;
				<c:choose>
					<c:when test="${not empty refresh}">
						oPreLoad = "refresh";
					</c:when>
					<c:when test="${empty filters}">
						oPreLoad = null;
					</c:when>
					<c:otherwise>
						oPreLoad = ${filters};
					</c:otherwise>
				</c:choose>

				var filtersUtil = sensus.util.filter.filters;
				var options 	= sensus.util.filter.options;

				filtersUtil.search.inputs.query_type.options = [options.id, options.device_id, options.network_address];

				var oFilters = {
						search					: filtersUtil.search,
						date_filter				: filtersUtil.date_filter,
						all_action_categories	: filtersUtil.all_action_categories,
						users					: filtersUtil.users
					};

				sensus.util.filter.init(oPreLoad, oFilters,

					function(oResponse) {

						$("#w-filters").filters({
							tagsDiv : ".filter-results-containter div.first",
							title : sensus.locale.get("widgets.filter.filterEvents"),
							table : sensus.pages.processhistory.historyTable,
							filters : oResponse});
				});

			</sec:authorize>

			// To Generate the archive CSV
			sensus.util.exportcsv.setGenerateCSVEvent({

				url : "api/export/generateHistoryCSV",

				$element : $csv,

				getGenerateRequestCSV : function() {

					// Get Request populate with current filters
					var request = new InquiryProcessRequest(sensus.pages.processhistory.fnRequestAction());

					// Default Request Parameters
					request.sortExpressions = sensus.util.page.getSortExpression(sensus.pages.processhistory.historyTable);

					return request;
				}
			});

			sensus.util.page.stopGlobalProgressBar();
		});
	</script>

</sec:authorize>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
	$(document).ready(function() {

		var sMessage = $.address.parameter('message');
		var $csv 	 = $("#csv");

		sensus.util.page.initMessagingTabs();

		if (sMessage) {

			// Get informations from url
			sMessage = unescape(sMessage);

			var sName 		= unescape($.address.parameter('name'));
			var	sWhen 		= unescape($.address.parameter('when'));
			var	sTime 		= unescape($.address.parameter('time'));
			var	bRepeat 	= $.address.parameter('repeat');
			var sMessaging  = "messaging-main";

			if (sMessage == "INSERT") {

				var sFormatDate = "MM d, yy";
				var	sDateFormatMask = sensus.settings.dateFormatMask.replace("yyyy","yy");

				if (sDateFormatMask == "dd/mm/yy") {

					sFormatDate = "d MM, yy";
				}

				sWhen = $.datepicker.formatDate(sFormatDate, $.datepicker.parseDate(sDateFormatMask, sWhen));

				sensus.util.page.showMessage(sMessaging,
						sensus.locale.get('systemintelligence.scheduledCreateEvent.successCreate', sName, sWhen, sTime),
						"confirm");

			} else if (sMessage == "UPDATE") {

				sensus.util.page.showMessage(sMessaging,
						sensus.locale.get('systemintelligence.updateScheduledEvent.success', sName),
						"confirm");
			} else {

				sensus.util.page.showMessage(sMessaging, sMessage, "error");
			}

			// Clear parameters
			$.fn.pageLoader.parameters({
				check : null,
				message : null,
				name : null,
				when : null,
				time : null,
				repeat : null,
			});
		}

		sensus.util.page.scheduledEventId = 0;

		if (!$.address.parameter("status_scheduled")) {

			$.fn.pageLoader.parameter("status_scheduled", "ENABLED|");
		}

		// To Generate the archive CSV
		sensus.util.exportcsv.setGenerateCSVEvent({

			url : "api/export/generateScheduleCSV",

			$element : $csv,

			getGenerateRequestCSV : function() {

				// Get Request populate with current filters
				var request = new InquiryScheduleRequest(sensus.pages.systemintelligence.fnRequestAction());

				// Set date formart default
				request.dateFormat = sensus.util.page.getDateFormat(sensus.settings.dateFormatMask) + " hh:mm a";

				// Default Request Parameters
				request.sortExpressions = sensus.util.page.getSortExpression(sensus.pages.systemintelligence.systemIntelligenceScheduledEventTable);

				return request;
			}
		});

		$("#ajax-button").click(function(e) {

			e.preventDefault();

			sensus.util.page.startGlobalProgressBar();

			// Create Session to Filters
			sensus.util.session.create({
				key : "SelectedFilters",
				value : $.fn.pageLoader.queryString()
			});

			$.fn.pageLoader.load($(this).attr("href"), $("#tabs-content"), null, null, null, false);
		});

		// jQuery dataTable setup
		sensus.pages.systemintelligence.systemIntelligenceScheduledEventTable = $('#listTable').dataTable(sensus.widgets.datatable.setTable({

			id : "listTable",
			sAjaxSource : "api/schedule/fetchAll",
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
				{sId : "Id", 			bVisible: false},
				{sId : "eventName",   	sWidth : "5%", 	bSortable : true},
				{sId : "actionName",  	sWidth : "5%", 	bSortable : false},
				{sId : "actionType",  	sWidth : "5%", 	bSortable : false},
				{sId : "scheduledBy", 	sWidth : "5%", 	bSortable : true},
				{sId : "deliverOn",   	sWidth : "5%", 	bSortable : true},
				{sId : "eventDate",   	sWidth : "5%", 	bSortable : true},
				{sId : "repeats",     	sWidth : "5%", 	bSortable : true},
				{sId : "smartPoints", 	sWidth : "5%", 	bSortable : true},
				{sId : "status", 		bVisible: true, bSortable : true},
				{sId : "procId", 		bVisible: false}
			],

			oSettings : {
				oRequest : InquiryScheduleRequest,
				sortEnum : 'ScheduleOrderByEnum',
				fnRequest : sensus.pages.systemintelligence.fnRequestAction,
				sResponseObj  : 'schedules',
				aColumns      : ['id','name','dmAction.actionType.actionTypeEnumNameValue','dmAction.actionType.actionCategoryEnumNameValue',
				                 'modifyUser','frequency.nextExecution','dmAction.actionTime','frequency.frequencyTypeEnum',
				                 'dmAction.totalDevices','statusEnum','dmAction.processId'],
				iDefaultCol  : 6,
				sDefaultSort : "asc"
				<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR')">,
				process : {
					oAction : {
						allRow : true,
						sStatusColumn : 'status',
						edit   : {url : "schedule/edit", bSaveSession : true},
						clone  : {url : "schedule/edit", bSaveSession : true},
						pause  : {
							url : "api/schedule/update",
							success : function(data, i) {
								sensus.widgets.datatable.reloadTable(sensus.pages.systemintelligence.systemIntelligenceScheduledEventTable);
								return "";
							}
						},
						failed  : {
							url : "api/schedule/update",
							success : function(data, i) {
								sensus.widgets.datatable.reloadTable(sensus.pages.systemintelligence.systemIntelligenceScheduledEventTable);
								return "";
							}
						},
						resume : {
							url : "api/schedule/update",
							success : function(data, i) {
								sensus.widgets.datatable.reloadTable(sensus.pages.systemintelligence.systemIntelligenceScheduledEventTable);
								return "";
							}
						},
						cancel : {
							url : "api/schedule/delete",
							text : function(data, i) {

								// Get the object with
								var oColumn = sensus.widgets.datatable.oTableSettings.aColumns.columns;
								var sId 	= 'eventName';

								for (var key in oColumn) {

								    if ( oColumn[key].sId === sId ) {
								    	return sensus.locale.get("systemintelligence.page.today.cancelConfirm", data[key]);
								    }
								}
							},
							success : function(data, i) {

								var oSettings = sensus.pages.systemintelligence.systemIntelligenceScheduledEventTable.fnSettings();
								var	iStart = null;

								if ((oSettings._iRecordsDisplay - 1) % $('.dataTables_length').find('select').val() === 0) {
									iStart = (oSettings._iRecordsDisplay - 1) - oSettings._iDisplayLength;
								}

								sensus.widgets.datatable.reloadTable(sensus.pages.systemintelligence.systemIntelligenceScheduledEventTable,iStart);
								return (sensus.locale.get("systemintelligence.scheduledEvent.deleteFail", data[i.eventName]));
							}
						}
					}
				}
				</sec:authorize>
			},

			$exportCSVElement : $csv,

			fnRowCallback : function(nRow, aData, iDisplayIndex, objColumn) {

				$("td:eq(" + objColumn.eventName + ")", nRow)
						.html("<a class='request-readonly-action' href='#'>" + aData[objColumn.eventName] + "</a>")
						.click(function(e) {

							e.preventDefault();

							sensus.pages.systemintelligence.colId = aData[objColumn.Id];
							sensus.pages.systemintelligence.dialogSettings["scheduledEvent"].id = aData[objColumn.Id];
							sensus.pages.systemintelligence.dialogSettings["scheduledEvent"].eventName = aData[objColumn.eventName];
							sensus.pages.systemintelligence.dialogSettings["scheduledEvent"].actionName = aData[objColumn.actionName];
							sensus.pages.systemintelligence.dialogSettings["scheduledEvent"].eventDate = aData[objColumn.eventDate];
							sensus.pages.systemintelligence.dialogSettings["scheduledEvent"].repeats = aData[objColumn.repeats];

							sensus.util.actiondialog.launchActionDialog("eventView", sensus.util.process.actions.eventView(aData[objColumn.Id], "openUpdateById"));
						});

				// Internationalization of the some columns
				$("td:eq(" + objColumn.actionName + ")", nRow).html(sensus.locale.get(aData[objColumn.actionName]));
				$("td:eq(" + objColumn.actionType + ")", nRow).html(sensus.locale.get(aData[objColumn.actionType]));

				if (aData[objColumn.repeats]) {
					$("td:eq(" + objColumn.repeats + ")", nRow).html(sensus.locale.get("commons.pages." + aData[objColumn.repeats]));
				}

				// Apply widget_date_format
				var oSelector = $("td:eq(" + objColumn.eventDate + ")", nRow);

				if (oSelector.text() != '') {

					var sDate = oSelector.text();
					var	aDate = $.date.dateFormat(sDate,"h:i A").split(" ");

					oSelector.html($.date.dateFormat(sDate) + " " + aDate[0] + "<small>" + aDate[1] + "</small>");
				}

				if ((!aData[objColumn.procId]) || (aData[objColumn.procId] == null)) {

					var oDeliverOn = $("td:eq(" + objColumn.deliverOn + ")", nRow);

					if (oDeliverOn.text() != '') {

						oDeliverOn.html(oDeliverOn.text());

						// Date Format
						var sDate = oDeliverOn.text();
						var	aDate = $.date.dateFormat(sDate,"h:i A").split(" ");

						oDeliverOn.html($.date.dateFormat(sDate) + " " + aDate[0] + "<small>" + aDate[1] + "</small>");
					}

				} else {
					$("td:eq(" + objColumn.deliverOn + ")", nRow).html(
							'<a class="request-summary"href="" class="'+aData[objColumn.actionName]+'" id="'+aData[objColumn.procId]+'">' + sensus.locale.get("commons.pages.delivered") + '</a>');
				}

				oSelector.addClass("active");

				// Create Device Column Link.
				$("td:eq(" + objColumn.smartPoints + ")", nRow).html(
						sensus.util.process.fnCreateLinkSmartpointsDialog(aData[objColumn.smartPoints], sensus.util.process.summaryType.total, aData[objColumn.Id], true));

				$("td:eq(" + objColumn.status + ")", nRow).html(sensus.locale.get("systemintelligence.scheduledEvent.status." + aData[objColumn.status]));
			},

			fnDrawCallback : function(setting, objColumn) {

				$(".status-viewport-loading").hide();
				$('div.timeline-band-layer-inner img').hide();
			}
		}));

		$("#listTable").delegate(".request-summary", "click", function(e) {

			var $this = $(this);

			e.preventDefault();

			sensus.pages.systemintelligence.dialogSettings["scheduledEvent"].id = $this.attr('id');
			sensus.pages.systemintelligence.dialogSettings["scheduledEvent"].actionName = $this.attr('class');

			sensus.util.actiondialog.launchActionDialog("summary", sensus.pages.systemintelligence.dialogSettings["summary"]);
		});

		<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

			var filters = sensus.util.filter.filters;
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

			// search hint 'Search Events'
			filters.query_search.inputs.query.hint = sensus.locale.get("smartpointdetail.tabs.searchEvents");

			sensus.util.filter.init(oPreLoad, {
					query_search				: filters.query_search,
					schedule_action_categories	: filters.schedule_action_categories,
					status_scheduled			: filters.status_scheduled,
					repeats						: filters.repeats,
					users						: filters.users
				},

				function(oResponse) {

					$("#w-filters").filters({
						tagsDiv : ".filter-results-containter div.first",
						title : sensus.locale.get("widgets.filter.filterEvents"),
						table : sensus.pages.systemintelligence.systemIntelligenceScheduledEventTable,
						filters : oResponse});
				});

		</sec:authorize>

		// Close all filter except the first
		$("#filters label:gt(1)").click();

		sensus.util.page.stopGlobalProgressBar();
	});
	</script>
</sec:authorize>
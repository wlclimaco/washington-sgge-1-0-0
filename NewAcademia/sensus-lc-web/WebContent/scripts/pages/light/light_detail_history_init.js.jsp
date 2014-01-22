<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager')">
<script type="text/javascript">

/**
 * @fileoverview Initializes the SmartPoint Detail History page.
 * @author Alexandre Tiveron
 *
 */

$(document).ready(function() {

	function wFn() {

		var sToday = $.datepicker.formatDate(sensus.settings.DATE_FORMAT, new Date());
		$('#view_from').val(sToday);
		$('#filters h3').text($.sc.locale("smartpoint.filter.filterActions"));

	};

	var aColumns = [
		{sId : "Id",             sWidth : "5%",  bSortable: true, bVisible : false},
		{sId : "Action",         sWidth : "25%", bSortable: true},
		{sId : "Description",    sWidth : "25%", bSortable: false},
		{sId : "CreateUser",     sWidth : "10%", bSortable: true},
		{sId : "StartTime",      sWidth : "10%", bSortable: true},
		{sId : "status",         sWidth : "10%", bSortable: true},
		{sId : "notificationHistoryId",sWidth : "5%",  bSortable: false, bVisible : false},
		{sId : "smartpoint",	 sWidth : "5%", bSortable : false, bVisible : false},
		{sId : "alertSubType",   sWidth : "5%", bSortable: false, bVisible : false}
	];

	if(!$.address.parameter('sort'))
	{
		$.address.parameter('sort', 'start_datetime|desc|');
	}

	var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
	var	oFirstMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
	var sPoleId =  sensus.commons.modules.util.fnGetParameterValue(oFirstMeter, "poleId", 'value');
	var iLightTimeZone = oFirstMeter.radio.location.timeZoneInfo.offsetInHours;

	// Pre loaded Filters/Columns
	<c:choose>
		<c:when test="${not empty refresh}">
			var oFilterPreLoad = "refresh";
		</c:when>
		<c:when test="${empty filters}">
			var	oFilterPreLoad = null;
		</c:when>
		<c:otherwise>
			var	oFilterPreLoad = ${filters};
		</c:otherwise>
	</c:choose>

	// jQuery dataTable setup
	sensus.pages.smartpointdetailhistory.lightHistoryTable = $('#light-history-table').dataTable(sensus.widgets.datatable.setTable({
			id           : "light-history-table",
			sAjaxSource  : "api/light/fetchhistory",
			bPreLoad : true,
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
			aColumns     : aColumns,
			oSettings    : {
				oRequest      : notificationHistoryRequest,
				aColumns      : ['processId', 'name', 'description', 'createUser', 'createDate', 'statusComplete', 'notificationHistoryId', 'lightCount', 'alertSubType'],
				order         : "StartTime",
				orderType     : "date",
				iDefaultCol   : 4,
				sDefaultSort  : "desc",
				sResponseObj  : 'lightHistory',
				fnRequest     : sensus.pages.smartpointdetailhistory.fnNotificationHistoryCriteria,
				process : {
					sSmartpointSize : "smartpoint"
					<sec:authorize ifAllGranted="ROLE_Role.Admin">
					,abort : {
						url       : "api/process/abort",
						oRequest  : 'processRequest',
						fnRequest : function(data, i) {

							var aProcessList = [{
								id : data[i.Id]
							}];

							return new processRequest(aProcessList);

						},
						text : function(data, i){
							return $.sc.locale("longRunning.table.message.abort");
						},
						success : function(data, i){
							sensus.widgets.datatable.reloadTable(sensus.pages.smartpointdetailhistory.lightHistoryTable);
							return $.sc.locale("action.summary.abort.process.success", data[i.Event]);
						}
					}
					</sec:authorize>

				}
			},
			fnRowCallback : function(nRow, aData, iDisplayIndex, col) {

					try
					{
						//Format hour and minute
						$("td:eq(" +col.StartTime+ ")", nRow).text($.sc.dateFormat(aData[col.StartTime], 'h:i:s.fff A', null, false, iLightTimeZone));

						//Alt text - Process Id
						if (aData[col.Id] != null)
						{
							$("td:eq(" + col.Action + ")", nRow).text(aData[col.Action]).attr('title', 'Process ID ' + aData[col.Id]);
						}

						if ($("td:eq(" + col.notificationHistoryId + ")" , nRow).text() != '')
						{
							if(aData[col.Action] == 'Active' || aData[col.Action] == 'Deactivated')
							{
								$("td:eq(" + col.Description + ")", nRow).text("");
							}
							else
							{
								$("td:eq(" + col.Description + ")", nRow).text($.sc.locale(aData[col.Description]));
							}
						}
						else
						{
							$('td:eq(' + col.Description + ')', nRow).html(sensus.util.process.fnFormatDescription(null, aData[col.status], aData[col.Description], $.address.parameter('id'), sPoleId));
						}

						//Format Processing or Complete
						if (aData[col.status] == false)
						{
							$("td:eq(" +col.status+ ")", nRow).addClass("processing").html("<span>" + $.sc.locale("table.type.processing") + "</span>");
						}
						else
						{
							$("td:eq(" +col.status+ ")", nRow).html("<span>" + $.sc.locale("table.type.complete") + "</span>");
						}

						if ($("td:eq(" + col.notificationHistoryId + ")" , nRow).text() != '' && aData[col.Action] != 'Active')
						{

							var btnAlertDetail = $('<a id="' + aData[col.Description] + '" class="button small" href="#">'+ $.sc.locale("table.summary")+'</a>').click(function(e) {
								e.preventDefault();

								$.sc.startProgressBar();

								$.sc.launchActionDialog(aData[col.notificationHistoryId] + '-' + aData[col.alertSubType], sensus.commons.modules.dialogSettings.alertDetails);
								$.sc.stopProgressBar();

							});

							$("td:last", nRow).html(btnAlertDetail);
						}
					}
					catch(e)
					{
					}
				},
				fnDrawCallback : function(setting, col) {

					try
					{
						//Add Style Order Date
						$(this).find(".row-header h4").each(function(i) {

							var sOrderDate  = $(this).text(),
								sSimpleDay  = $.sc.dateFormat(sOrderDate,'simpleDay', null, false, iLightTimeZone);

							if (sSimpleDay === $.sc.locale("commons.pages.today")
									|| sSimpleDay === $.sc.locale("commons.pages.yesterday"))
							{
								$(this).html($.sc.dateFormat(sOrderDate, null, null, false, iLightTimeZone) + ": " + "<strong>" + sSimpleDay + "</strong>");
							}
							else
							{
								$(this).html($.sc.dateFormat(sOrderDate, null, null, false, iLightTimeZone));
							}
						});
					}
					catch(e)
					{
					}
				}
	}));

	sensus.commons.modules.summaryData.statusMessage.init();

	<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support')">
		var sFilters = ['SEARCH', 'FILTERS_EVENT', 'ACTION_TYPE'];
	</sec:authorize>

	<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
		var sFilters = ['SEARCH', 'FILTERS_EVENT', 'ACTION_TYPE', 'USERS'];
	</sec:authorize>

	$.sc.filters(sFilters, sensus.pages.smartpointdetailhistory.lightHistoryTable, wFn, null, oFilterPreLoad);

	//$('.filter-results-container li#id').remove();

	$('#USERS label, #ACTION_TYPE label', '#w-filters').removeClass("off").addClass("on");

	/* Export csv */
	$("#csv").click(function(e) {

		e.preventDefault();

		$.sc.startProgressBar();

		// Get Request populate with current filters
		var request = { notificationHistoryRequest : new notificationHistoryRequest(sensus.pages.smartpointdetailhistory.fnNotificationHistoryCriteria()) };
		request.notificationHistoryRequest.pageSize = 0;
		request.notificationHistoryRequest.sortExpressions = $.sc.getSortExpression();

		// Timezone
		request.timezone = oFirstMeter.radio.location.timeZoneInfo.timeZone;

		sensus.util.exportcsv.generateCSV('api/export/generateLightHistoryCSV', request);

	});

});
</script>
</sec:authorize>
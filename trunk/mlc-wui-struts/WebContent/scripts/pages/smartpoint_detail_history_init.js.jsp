<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager')">
<script type="text/javascript">

/**
 * @fileoverview Initializes the SmartPoint Detail History page.
 * @author Alexandre Tiveron
 *
 */

$(document).ready(function() {

	sensus.util.page.initMessaging();

	function wFn() {

		$("option:eq(0), option:eq(1), option:eq(2), option:eq(3)", "#query_type").remove();
		$("option:eq(4)", "#query_type").prop("selected","selected");
		sToday = $.datepicker.formatDate(sensus.settings.dateFormatMask, new Date());
		$('#view_from').val(sToday);
		$('#filters h3').text(sensus.locale.get("smartpoint.filter.filterActions"));
		
	};
			
	var aColumns = [
		{sId : "Id",             sWidth : "5%",  bSortable: true, bVisible : false},
		{sId : "Action",         sWidth : "25%", bSortable: true},
		{sId : "Description",    sWidth : "25%", bSortable: false},
		{sId : "CreateUser",     sWidth : "10%", bSortable: true},
		{sId : "StartTime",      sWidth : "10%", bSortable: true},
		{sId : "status",         sWidth : "10%", bSortable: true},
		{sId : "statusMessageId",sWidth : "5%",  bSortable: false, bVisible : false},
		{sId : "smartpoint",	 sWidth : "5%", bSortable : false, bVisible : false}
	];

	if(!$.address.parameter('sort')){
	
		$.address.parameter('sort', 'start_datetime|desc|');
	
	}	
	
	var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
	var	oFirstMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
	var sPoleId =  sensus.commons.modules.util.fnGetParameterValue(oFirstMeter, "POLE_ID", 'value');

	// jQuery dataTable setup
	sensus.pages.smartpointdetailhistory.lightHistoryTable = $('#light-history-table').dataTable(sensus.widgets.datatable.setTable({
			id           : "light-history-table",
			sAjaxSource  : sensus.settings.searchSmartpointHistory,
			aColumns     : aColumns,
			oSettings    : {
				oRequest      : inquiryLightRequest,
				aColumns      : ['processId', 'name', 'description', 'createUser', 'createDate', 'statusComplete', 'statusMessageId', 'smartpointCount'],
				order         : "StartTime",
				orderType     : "date",
				iDefaultCol   : 4,
				sDefaultSort  : "desc",
				sResponseObj  : 'lightHistory',			
				fnRequest     : function() {

					var dEndDate = null;
					var iBack = parseInt($.address.parameter('total_days'));
					var dStartDate = $.address.parameter('view_from') || null;
					var aUserIds = null;
					var sLightTextSearch = unescape($.address.parameter('query'));

					if (dStartDate) {

						// Convert String to object date
						var aDate = dStartDate.split('|');
						dStartDate = $.datepicker.parseDate(sensus.settings.dateFormatMask, aDate[0]); 
						dStartDate.setHours(23);
						dStartDate.setMinutes(59);
						dStartDate.setSeconds(59);
	
						dEndDate = $.datepicker.parseDate(sensus.settings.dateFormatMask, aDate[1]); 
						dEndDate.setHours(0);
						dEndDate.setMinutes(0);
						dEndDate.setSeconds(0);

						dStartDate = $.date.fnTimeToUTC(dStartDate);
						dEndDate   = $.date.fnTimeToUTC(dEndDate);

					} 

					if ($.address.parameter('action_type')) {

						var aActionCategoryList = $.address.parameter('action_type').split('|');
						aActionCategoryList.pop();

					}
					
					if ($.address.parameter('users')) {

						aUserIds = $.address.parameter('users').split('|');
						aUserIds.pop();

					}					

					var search = new processFilter(dEndDate, dStartDate, sensus.widgets.datatable.fnConvertEnum('LCActionCategoryEnum', aActionCategoryList), sLightTextSearch, aUserIds) 
				
					return search;
					
				}, 
				process : {
					sSmartpointSize : "smartpoint"
					<sec:authorize ifAllGranted="ROLE_Role.Admin">
					,abort : {
						url       : sensus.settings.abortProcessUrl,
						oRequest  : 'processRequest',
						fnRequest : function(data, i) {
							
							var aProcessList = [{
								id : data[i.Id]
							}];

							return new processRequest(aProcessList);

						},	
						text : function(data, i){
							return sensus.locale.get("longRunning.table.message.abort");
						},
						success : function(data, i){
							sensus.widgets.datatable.reloadTable(sensus.pages.smartpointdetailhistory.lightHistoryTable);
							return sensus.locale.get("action.summary.abort.process.success", data[i.Event]);	
						}
					}
					</sec:authorize>

				}
			},
			fnRowCallback : function(nRow, aData, iDisplayIndex, col) {
					
					try {

						//Format hour and minute 
						$("td:eq(" +col.StartTime+ ")", nRow).text($.date.dateFormat(aData[col.StartTime], 'h:i:s.fff A'));
						
						//Alt text - Process Id
						if (aData[col.Id] != null) {
							$("td:eq(" + col.Action + ")", nRow).text(aData[col.Action]).attr('title', 'Process ID ' + aData[col.Id]);	
						}
						
						//Format text of Description
						$('td:eq(' + col.Description + ')', nRow).html(
							sensus.util.process.fnFormatDescription(null, aData[col.status], aData[col.Description], $.address.parameter('id'), sPoleId)
						);
						
						//Format Processing or Complete
						if (aData[col.status] == false) {

								$("td:eq(" +col.status+ ")", nRow).addClass("processing").html("<span>" + sensus.locale.get("table.type.processing") + "</span>");

						} else {

							$("td:eq(" +col.status+ ")", nRow).html("<span>" + sensus.locale.get("table.type.complete") + "</span>");

						}	
						
						if ($("td:eq(" + col.statusMessageId + ")" , nRow).text() != '') {
							
							var btnAlertDetail = $('<a id="' + aData[col.Description] + '" class="button small" href="#">'+ sensus.locale.get("table.summary")+'</a>').click(function(e) {
								e.preventDefault();

								sensus.util.page.startProgressBar();
								
								var iSubtype = sensus.widgets.datatable.fnGetEnumKey('StatusExceptionTypeEnum', aData[col.Description].replace(new RegExp(" ","gm"),"_").toUpperCase());
																
								sensus.util.actiondialog.launchActionDialog(aData[col.statusMessageId] + '-' + iSubtype, sensus.commons.modules.dialogSettings.alertDetails);			
								sensus.util.page.stopProgressBar();
								
							});
						
							$("td:last", nRow).html(btnAlertDetail);
						
						}

					} catch(e) {

					}
				},
				fnDrawCallback : function(setting, col) {

					try{

						//Add Style Order Date
						$(this).find(".row-header h4").each(function(i) {

							var sOrderDate  = $(this).text(),
								sSimpleDay  = $.date.dateFormat(sOrderDate,'simpleDay');

							if (sSimpleDay === sensus.locale.get("commons.pages.today")
									|| sSimpleDay === sensus.locale.get("commons.pages.yesterday")) {

								$(this).html($.date.dateFormat(sOrderDate) + ": " + "<strong>" + sSimpleDay + "</strong>");

							} else {
								$(this).html($.date.dateFormat(sOrderDate));
							}

						});

					} catch(e) {
						
					}
				}
	}));
	
	<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support')">
		var sFilters = ['SEARCH', 'FILTERS_EVENT', 'ACTION_TYPE'];
	</sec:authorize>

	<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
		var sFilters = ['SEARCH', 'FILTERS_EVENT', 'ACTION_TYPE', 'USERS'];
	</sec:authorize>
	
	$("#w-filters").wFilters(sFilters, sensus.pages.smartpointdetailhistory.lightHistoryTable, wFn);
	
	$('.filter-results-container li#id').remove();
	
	$('#USERS label, #ACTION_TYPE label', '#w-filters').removeClass("off").addClass("on");
	
	/* Export csv */
	$("#csv").click(function() {

		sensus.pages.smartpointdetailhistory.generateCsvFile();
		return false;

	});
	
});
</script>
</sec:authorize>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager')">
<script type="text/javascript">

/**
 * @namespace sensus.pages.process
 * @description The init namespace for the Process Page.
 * @fileoverview Initializes the process page.
 *
 * @author Raphael Constantino
 */
$(document).ready(function() {

	sensus.util.page.initMessaging();

	var aColumns = [
		{sId : "Id",             sWidth : "5%", bVisible: false},
		{sId : "Action",         sWidth : "5%"},
		{sId : "ActionId",	     sWidth : "5%", bVisible: false},
		{sId : "smartpoint",	 sWidth : "5%", bSortable : true},
		{sId : "SmartPointsId",  sWidth : "5%", bVisible: false},
		{sId : "Description",    sWidth : "5%", bSortable : false},
		{sId : "CreateUser",     sWidth : "5%", bSortable : true},
		{sId : "StartTime",      sWidth : "5%"},
		{sId : "status",         sWidth : "5%"},
      	{sId : "isSubmitted",    sWidth : "5%", bVisible: false},
      	{sId : "flexNetId",      sWidth : "5%", bVisible: false},
      	{sId : "parentId",       sWidth : "5%", bVisible: false},
      	{sId : "CreateDate",     sWidth : "5%", bVisible : false}
     ];

	if(!$.address.parameter('sort')){

		$.address.parameter('sort', 'start_datetime|desc|');

	}

	/** * jQuery dataTable setup ** */
	sensus.pages.process.eventHistoryTable = $('#event-history-table').dataTable(sensus.widgets.datatable.setTable({
		id           : "event-history-table",
		sAjaxSource  : "api/process/fetchAll",
		aColumns     : aColumns,
		oSettings    : {
			oRequest  : inquiryProcessRequest,
			fnRequest : function() {

				var aUserIds = null;
				var dEndDate = null;
				var iBack = parseInt($.address.parameter('total_days'));
				var iTimeZoneHours = parseFloat(sensus.settings.TIME_ZONE_HOURS);
				var dStartDate = $.address.parameter('view_from') || null;
				var sLightTextSearch = $.address.parameter('query');

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

				if ($.address.parameter('users')) {

					aUserIds = $.address.parameter('users').split('|');
					aUserIds.pop();

				}

				if ($.address.parameter('event_type')) {

					var aActionCategoryList = $.address.parameter('event_type').split('|');
					aActionCategoryList.pop();

				}

				var search = new processFilter(dEndDate, dStartDate, sensus.widgets.datatable.fnConvertEnum('LCActionCategoryEnum', aActionCategoryList), sLightTextSearch, aUserIds);

				return search;
			},
			aColumns          : ['id','lcAction[object]','lcAction.actionTypeValue','processItems[fn(sensus.pages.process.processSize)]','','description','createUser','startTime','isProcessComplete','isSubmitted','processItems[object]','parentProcess.id','createDate'],
			sResponseObj      : 'processes',
			order             : "CreateDate",
			orderType         : "date",
			iDefaultCol       : 7,
			sDefaultSort      : "desc",
			smartpointFilter  : {
				aCols : ["smartpoint"],
				filter : "process"
			},
			process           : {
				sSmartpointSize : "smartpoint"
				<sec:authorize ifAllGranted="ROLE_Role.Admin">
					,abort : {
						url       : "api/process/abort",
						text      : function(data, i) {
							return $.sc.locale("longRunning.table.message.abort");
						},
						oRequest  : 'processRequest',
						fnRequest : function(data, i) {

							var aProcessList = [{
								id : data[i.Id]
							}];

							return new processRequest(aProcessList);
						},
						success   : function(data, i) {
							sensus.widgets.datatable.reloadTable(sensus.pages.process.eventHistoryTable);
							return $.sc.locale("action.summary.abort.process.success", data[i.Event]);
						}
					}
				</sec:authorize>

			}
		},
		fnRowCallback : function(nRow, aData, iDisplayIndex, col) {

			var oAction = $.parseJSON(aData[col.Action]);

			$("td:eq(" + col.Action + ")", nRow).text($.sc.locale('sensus.process.'+oAction.actionType.toLowerCase()))
											    .attr('title', 'Process ID ' + aData[col.Id]);

			var oFlexNet = $.parseJSON(aData[col.flexNetId]);
			var nSmartpointCount = aData[col.smartpoint];

			if (oFlexNet[0]) {
				var nFlexNetId = oFlexNet[0].light.rniId;
				var nId		   = oFlexNet[0].light.id;
				var nPoleId    = '';
			}

			$("td:eq(" +col.smartpoint+ ")", nRow).text(nSmartpointCount);

			//Format hour and minute
			if (nSmartpointCount < 1) {

				$("td:eq(" +col.smartpoint+ ")", nRow).html('<span class="launch ui-state-disabled">'+nSmartpointCount+'</span>');

			} else {

				var sActionValue = '';

				if(oAction.actionParameters.length) {

					sActionValue = oAction.actionParameters[0].actionValue+'|';

				}

				sActionValue = '';

				for (c in oFlexNet) {
					if (oFlexNet.hasOwnProperty(c)) {
						sActionValue += oFlexNet[c].light.id+'|';
					}
				}

				if (nSmartpointCount > 1) {
					$("td:eq(" +col.smartpoint+ ")", nRow).html('<a href="light/?lights='+ sActionValue +'" class="launch">' + nSmartpointCount + '</a>');
				} else {
					$("td:eq(" +col.smartpoint+ ")", nRow).html('<a href="light/?id='+ nId +'" class="launch">' + nSmartpointCount + '</a>');
					nPoleId = sensus.util.process.fnGetParameterValue(oFlexNet[0].light, "POLE_ID", 'value');
				}
			}

			if (aData[col.status] == false) {

				$("td:eq(" +col.status+ ")", nRow).addClass("processing").html("<span>" + $.sc.locale("table.type.processing") + "</span>");

			} else {

				$("td:eq(" +col.status+ ")", nRow).html("<span>" + $.sc.locale("table.type.complete") + "</span>");

			}

			$("td:eq(" +col.StartTime+ ")", nRow).text($.sc.dateFormat(aData[col.StartTime], 'h:i:s.fff A'));

			$('td:eq(' + col.Description + ')', nRow).html(sensus.util.process.fnFormatDescription(aData[col.ActionId], aData[col.status], aData[col.Description], nId, nPoleId));

			// add triangle to process with parent
			if (aData[col.parentId] && aData[col.parentId] !== "0") {

				$("td:eq("+ col.Action +")", nRow).addClass("spindown").append("<span class='ui-icon-triangle-1-e ui-icon' id='"+ aData[col.parentId] +"'></span>");

			}
	   	},
	   	fnDrawCallback : function(setting, col) {

			//Add Style Order Date
			$(this).find(".row-header h4").each(function(i) {

				try {

					sOrderDate = $(this).text();

					if ($.sc.dateFormat(sOrderDate,'simpleDay') === $.sc.locale("commons.pages.today")
						|| $.sc.dateFormat(sOrderDate,'simpleDay') === $.sc.locale("commons.pages.yesterday")) {

						$(this).html($.sc.dateFormat(sOrderDate) + ": " + "<strong>" + $.sc.dateFormat(sOrderDate,'simpleDay') + "</strong>");

					} else {

						$(this).html($.sc.dateFormat(sOrderDate));

					}

				} catch(e){

				}

			});

			$('.alist','#event-history-table').addClass('launch');

		},
		fnInfoCallback  : function( oSettings, iStart, iEnd, iMax, iTotal, sPre ) {

			$("#total-records").html(iTotal);

			// Change text to singular
			if (iTotal == 1) {

				$("#label-total-records").html($.sc.locale("table.result.match"));

			}

		}
	}));


	//Open Childrens
	$("#event-history-table").on("click", ".spindown", function(e) {

			var oChildRow  = $(this).parent(),
				iParentId  = oChildRow.find("span").attr("id");

			if (!oChildRow.hasClass('childOn')) {

				$(this).find('span').removeClass("ui-icon-triangle-1-e").addClass("ui-icon-triangle-1-s");
				var aProcessList = [{
					id : iParentId
				}];
				$.ajax({
					url     		: "process/getProcessById.action",
					dataType     	: 'json',
					contentType  	: "application/json; charset=utf-8",
					type         	: "POST",
					data         	: $.toJSON({'processRequest': new processRequest(aProcessList,null,null,null)}),
					success : function(response) {

						var oRow = "<tr class='spindown_child'>";
						var iParent = 0;
						var bSubmited = false;
						var bCompleted = false;
						var iDescriptionCol = sensus.util.page.fnGetArrayObject(aColumns, 'sId', 'Description');
						var iActionIdCol = sensus.util.page.fnGetArrayObject(aColumns, 'sId', 'ActionId');
						var iStatusCol = sensus.util.page.fnGetArrayObject(aColumns, 'sId', 'status');
						var iCreateUser = sensus.util.page.fnGetArrayObject(aColumns, 'sId', 'CreateUser');
						var iSmartpointIdCol = sensus.util.page.fnGetArrayObject(aColumns, 'sId', 'SmartPointsId');
						var iFlexnetIdCol = sensus.util.page.fnGetArrayObject(aColumns, 'sId', 'flexNetId');
						var iStartTimeCol = sensus.util.page.fnGetArrayObject(aColumns, 'sId', 'StartTime');
						var oFlexNet = response.processes[0].processItems;
						var sActionValue = '';

						for (i in aColumns) {

							if (aColumns.hasOwnProperty(i)) {

								var sClass = "";
								var oSpan = "";

								if (aColumns[i].bVisible == false) {

									sClass = "hide";

								}

								if (aColumns[i].sId == 'status' && response.firstProcess.isProcessComplete) {

									bCompleted = true;

								}

								if (aColumns[i].sId == 'isSubmitted' && response.firstProcess.isSubmitted) {

									bSubmited = true;

								}

								oRow += "<td class='"+ sClass +"'><span>" + oSpan + $.sc.locale('sensus.process.'+response.firstProcess.lcAction.actionType.toLowerCase()) + "</span></td>";

							}

						}

						oRow += "</tr>";
						oRow = $(oRow);

						for (c in oFlexNet) {
							if (oFlexNet.hasOwnProperty(c)) {
								sActionValue += oFlexNet[c].light.id+'|';
							}
						}

						$("td:eq(3)", oRow).html('<a href="smartpoint/ajax.list.action?lights='+ sActionValue +'" class="afilter launch">' + oFlexNet.length + '</a>');

						//Format hour and minute
						var oDateMessage = response.firstProcess.startTime;
						var sDate = oDateMessage.split(' ');

						sDate.splice(4,1);

						sDate = sDate.join(' ');

						var sFormatDate = $.sc.dateFormat(sDate, 'h:i:s.fff A');

						$("td:eq(" + iCreateUser + ")", oRow).text(response.firstProcess.createUser);

						$("td:eq(" + iStartTimeCol + ")", oRow).text(sFormatDate);

						$("td:eq(" + iStatusCol + ")", oRow).text($.sc.locale("table.type.complete"));

						if (bCompleted === true && bSubmited === true) {

							$("td:eq(13)", oRow).html('<a class="button summary-button" href="#">' + $.sc.locale("table.summary") + '</a>');

						}



						$(".summary-button", oRow).addClass('small');

						$(".summary-button", oRow).click(function(e) {
							e.preventDefault();

							sensus.util.page.startProgressBar();

							// Call Widget Summary
							$("#action-dialog").summary(response.firstProcess.id, 3);

							sensus.util.page.stopProgressBar();

						});

						$('td:eq(' + iDescriptionCol + ')', oRow).html(sensus.util.process.fnFormatDescription(response.firstProcess.id, response.firstProcess.isProcessComplete, response.firstProcess.description, response.firstProcess.processItems[0].light.id, sensus.util.process.fnGetParameterValue(response.firstProcess.processItems[0].light, "POLE_ID", 'value')));

						if (iParent) {

							oRow.find("td:first")
								.addClass("spindown")
								.append("<span class='ui-icon-triangle-1-e ui-icon' id='"+ iParent +"'></span>");

						}

						oChildRow.after(oRow);
						oChildRow.data("parent", oRow);
						oChildRow.addClass('childOn');
						$(".button").button();

					}

				});

			} else {

				var oParentRow = oChildRow.data("parent");
				oChildRow.removeClass('childOn');
				$(this).find('span').removeClass("ui-icon-triangle-1-s").addClass("ui-icon-triangle-1-e");

				oParentRow.detach();

				while(oParentRow.data("parent")) {

					oParentRow = oParentRow.data("parent");
					oParentRow.detach();

				}

			}

		}

	);


	/* Export csv */
	$("#csv").click(function(e) {
		e.preventDefault();
		sensus.pages.process.generateCsvFile();
		return false;

	});

	/**
	 * Export Csv File Sucess Process
	 */
	$("#event-history-table").on("click", "#export-csv-file-success-process", function() {

		var nProcessId = $(this).closest("tr").find("td:eq(0)").text();
		var sFileName = $(this).find("span").text();

		sensus.util.process.downloadFile(sFileName, true, nProcessId);

		sensus.widgets.datatable.reloadTable(sensus.pages.process.eventHistoryTable);

		return false;
	});

	function wFn() {

		$("option:eq(1), option:eq(4), option:eq(5)", "#query_type").remove();
		$('#filters h3').text($.sc.locale("smartpoint.filter.filterActions"));

	};

	<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support')">
		var sFilters = ['SEARCH', 'FILTERS_EVENT', 'EVENT_TYPE'];
	</sec:authorize>

	<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
		var sFilters = ['SEARCH', 'FILTERS_EVENT', 'EVENT_TYPE', 'USERS'];
	</sec:authorize>


	$.sc.filters(sFilters, sensus.pages.process.eventHistoryTable, wFn);

	$('#USERS label, #EVENT_TYPE label', '#w-filters').removeClass("off").addClass("on");

	sToday = $.datepicker.formatDate(sensus.settings.DATE_FORMAT, new Date());

	$('#view_from').val(sToday);

});
</script>
</sec:authorize>
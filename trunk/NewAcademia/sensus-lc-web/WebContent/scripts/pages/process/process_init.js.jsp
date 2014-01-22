<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
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

	var aColumns = [
		{sId : "Id",             sWidth : "5%", bVisible: false},
		{sId : "Action",         sWidth : "5%", bVisible: true},
		{sId : "ActionId",	     sWidth : "5%", bVisible: false},
		{sId : "Description",    sWidth : "5%", bVisible: true, bSortable : false},
		{sId : "light",	 		 sWidth : "5%", bVisible: true, bSortable : true},
		{sId : "lightFailed",	 sWidth : "5%", bVisible: true, bSortable : true},
		{sId : "CreateUser",     sWidth : "5%", bVisible: true, bSortable : true},
		{sId : "StartTime",      sWidth : "5%", bVisible: true},
		{sId : "status",         sWidth : "5%", bVisible: true},
      	{sId : "isSubmitted",    sWidth : "5%", bVisible: false},
      	{sId : "flexNetId",      sWidth : "5%", bVisible: false},
      	{sId : "parentId",       sWidth : "5%", bVisible: false},
      	{sId : "CreateDate",     sWidth : "5%", bVisible: false}
     ];

	if(!$.address.parameter('sort'))
	{
		$.address.parameter('sort', 'start_datetime|desc|');
	}

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

	/** * jQuery dataTable setup ** */
	sensus.pages.process.eventHistoryTable = $('#event-history-table').dataTable(sensus.widgets.datatable.setTable({
		id            : "event-history-table",
		sAjaxSource   : "api/process/fetchAll",
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
		aColumns      : aColumns,
		oSettings     : {
			oRequest  : inquiryProcessRequest,
			fnRequest : sensus.pages.process.fnInquiryProcessRequest,
			aColumns  : ['id',
						'lcAction[object]',
						'lcAction.actionTypeValue',
						'description',
						'processItemAmount',
						'processItemFailedAmount',
						'createUser',
						'startTime',
						'isProcessComplete',
						'isSubmitted',
						'processItems[object]',
						'parentProcess.id',
						'createDate[fn(sensus.pages.process.formatDate)]'
						],
			sResponseObj      : 'processes',
			order             : "CreateDate",
			orderType         : "date",
			iDefaultCol       : 7,
			sDefaultSort      : "desc",
			smartpointFilter  : {
				aCols : ["light"],
				filter : "process"
			},
			process           : {
				sSmartpointSize : "light"
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

			$("td:eq(" + col.Action + ")", nRow).text(oAction.description)
											    .attr('title', 'Process ID ' + aData[col.Id]);

			var oFlexNet = $.parseJSON(aData[col.flexNetId]);
			var nlightCount = aData[col.light];

			if (oFlexNet != null && oFlexNet[0])
			{
				var nFlexNetId = oFlexNet[0].light.rniId;
				var nId		   = oFlexNet[0].light.id;
				var nPoleId    = oFlexNet[0].light.poleId;
			}

			$("td:eq(" +col.light+ ")", nRow).text(nlightCount);

			//Format hour and minute
			if (nlightCount < 1)
			{
				$("td:eq(" +col.light+ ")", nRow).html('<span>' + nlightCount + '</span>');
			}
			else
			{
				var sActionValue = '';

				if(oAction.actionParameters.length)
				{
					sActionValue = oAction.actionParameters[0].actionValue+'|';
				}

				sActionValue = aData[0];

				$("td:eq(" + col.lightFailed + ")", nRow).html('<a href="light?processId=' + sActionValue +'&failed=true" class="alist" >' + aData[col.lightFailed] + '</a>');

				if (nlightCount > 1)
				{
					$("td:eq(" +col.light+ ")", nRow).html('<a href="light?processId='+ sActionValue +'" class="alist">' + nlightCount + '</a>');
				}
				else
				{
					$("td:eq(" +col.light+ ")", nRow).html('<a href="lightDetail?id='+ nId +'" class="alist">' + nlightCount + '</a>');
					nPoleId = sensus.util.process.fnGetParameterValue(oFlexNet[0].light, "poleId");
				}
			}

			if (!(aData[col.status]))
			{
				$("td:eq(" +col.status+ ")", nRow).addClass("processing").html("<span>" + $.sc.locale("table.type.processing") + "</span>");
			}
			else
			{
				$("td:eq(" +col.status+ ")", nRow).html("<span>" + $.sc.locale("table.type.complete") + "</span>");
			}

			if (aData[col.lightFailed] > 0)
			{
				$("td:eq(" + col.lightFailed + ")", nRow).addClass('alerts');
			}

			$("td:eq(" +col.StartTime+ ")", nRow).text($.sc.dateFormat(aData[col.StartTime], 'h:i:s.fff A'));

			$('td:eq(' + col.Description + ')', nRow).html(sensus.util.process.fnFormatDescription(aData[col.ActionId], aData[col.status], aData[col.Description], nId, nPoleId));

			// add triangle to process with parent
			if (aData[col.parentId] && aData[col.parentId] !== "0")
			{
				$("td:eq("+ col.Action +")", nRow).addClass("spindown").append("<span class='ui-icon-triangle-1-e ui-icon' id='"+ aData[col.parentId] +"'></span>");
			}
	   	},

	   	fnDrawCallback : function(setting, col) {

			//Add Style Order Date
			$(this).find(".row-header h4").each(function(i) {

				try
				{
					sOrderDate = $(this).text();

					if ($.sc.dateFormat(sOrderDate,'simpleDay') === $.sc.locale("commons.pages.today")
						|| $.sc.dateFormat(sOrderDate,'simpleDay') === $.sc.locale("commons.pages.yesterday"))
					{
						$(this).html($.sc.dateFormat(sOrderDate) + ": " + "<strong>" + $.sc.dateFormat(sOrderDate,'simpleDay') + "</strong>");
					}
					else
					{
						$(this).html($.sc.dateFormat(sOrderDate));
					}
				}
				catch(e)
				{
					console.log(e);
				}
			});
		},

		fnInfoCallback  : function( oSettings, iStart, iEnd, iMax, iTotal, sPre ) {

			$("#total-records").html(iTotal);

			// Change text to singular
			if (iTotal == 1)
			{
				$("#label-total-records").html($.sc.locale("table.result.match"));
			}
		}

	}));

	//Open Childrens
	$("#history").on("click", "#event-history-table .spindown", function(e) {

		var oChildRow    = $(this).parent(),
			iParentId  = $(this).find('span').attr('id');

		var aProcessList = [{
			id : iParentId
		}];

		if (!oChildRow.hasClass('childOn'))
		{
			$(this).find('span').removeClass("ui-icon-triangle-1-e").addClass("ui-icon-triangle-1-s");

			// CallBack function will create div with child process
			var fnCallBack = function(response)
			{

				var aProcesses = response.processes;
				var oRow = "<tr class='spindown_child'>";

				for (i in aProcesses)
				{
					if (aProcesses.hasOwnProperty(i))
					{
						var sClass = "";
						var oSpan = "";
						var sDate         = $.sc.dateFormat(aProcesses[i].createDate,"h:i:s.fff A"),
							sParentId      = "parent-id-" + iParentId,
							ochildProcess  ="<tr class='spindown-child "+ sParentId +"' style='display: table-row;'>";

						ochildProcess+= "<td class='hide'>"+ aProcesses[i].id +"</td>";

						// Check if has parent process
						if (aProcesses[i][11] == "0")
						{
							ochildProcess+= "<td>"+ aProcesses[i].id +"</td>";
						}
						else
						{
							ochildProcess+= "<td title='Process ID " + aProcesses[i].id + "'>"+ $.sc.locale('sensus.process.'+ aProcesses[i].lcAction.actionType.toLowerCase()) + "</td>";
						}
						ochildProcess+= "<td>"+ sensus.util.process.fnFormatDescription(aProcesses[i].lcAction.actionTypeValue, aProcesses[i].isProcessComplete, aProcesses[i].description, aProcesses[i].processItems[0].light.id, aProcesses[i].processItems[0].light.poleId) +"</td>";

						aProccessItems = aProcesses[i].processItems;

						ochildProcess+= "<td><a href='main#/light?processId="+ aProcesses[i].id +"' >" +  aProcesses[i].processItemAmount + "</a></td>";
						ochildProcess+= "<td>"+ aProcesses[i].processItemFailedAmount + "</td>";

						ochildProcess+= "<td>"+ aProcesses[i].createUser + "</td>";

						ochildProcess+= "<td>"+ sDate +"</td>";

						if (!aProcesses[i].isProcessComplete)
						{
							ochildProcess+= "<td class='processing' ><span class='processing'>" + $.sc.locale("table.type.processing") + "</span></td>"
						}
						else
						{
							ochildProcess+= "<td>" + $.sc.locale("table.type.complete") + "</td>"
						}

						oRow += "<td class='"+ sClass +"'><span>" + oSpan + $.sc.locale('sensus.process.'+aProcesses[i].lcAction.actionType.toLowerCase()) + "</span></td>";
						oChildRow.after(ochildProcess);
						oChildRow.addClass('childOn');
					}
				}
			};

			$.sc.getJson("api/process/fetch/id", {processList : [{ id : iParentId }]}, false, fnCallBack, null, false, true);
		}
		else
		{
			oChildRow.removeClass('childOn');
			oChildRow.find('span').removeClass("ui-icon-triangle-1-s");
			var orowChild     = oChildRow.next('tr'),
				orowChildNext = orowChild.next('tr');

			while(orowChild.hasClass('spindown-child'))
			{
				orowChild.remove();
				orowChild = orowChildNext;
				orowChildNext = orowChildNext.next('tr');
			}
		}
	});

	/* Export csv */
	$("#csv").click(function(e) {

		e.preventDefault();

		$.sc.startProgressBar();

		// Get Request populate with current filters
		var oRequest = new inquiryProcessRequest(sensus.pages.process.fnInquiryProcessRequest());

		// Default Columns
		var aDefaultColumns = [];
		$.each(aColumns, function(i, e) {
			if (e.bVisible)
			{
				aDefaultColumns.push(e.sId);
			}
		});

		oRequest.listColumn = aDefaultColumns;

		// Default Request Parameters
		oRequest.sortExpressions	= $.sc.getSortExpression();
		var oProcessCSVRequest = new ProcessCSVRequest(oRequest);

		oProcessCSVRequest.timezone = oRequest.timezone;
		sensus.util.exportcsv.generateCSV('api/export/generateProcessCSV', oProcessCSVRequest);
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

	};

	<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support')">
		var sFilters = ['SEARCH', 'FILTERS_EVENT', 'EVENT_TYPE'];
	</sec:authorize>

	<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
		var sFilters = ['SEARCH', 'FILTERS_EVENT', 'EVENT_TYPE', 'USERS'];
	</sec:authorize>

	$.sc.filters(sFilters, sensus.pages.process.eventHistoryTable, wFn, null, oFilterPreLoad);

	$('#USERS label, #EVENT_TYPE label', '#w-filters').removeClass("off").addClass("on");

	sToday = $.datepicker.formatDate(sensus.settings.DATE_FORMAT, new Date());

	$('#view_from').val(sToday);

	$.sc.stopGlobalProgressBar();

});
</script>
</sec:authorize>
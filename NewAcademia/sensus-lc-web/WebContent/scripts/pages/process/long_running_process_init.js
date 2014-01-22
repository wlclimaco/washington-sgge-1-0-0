/**
 * @fileoverview Initializes long running process.
 *
 * @author Raphael Constantino
 */

$(document).ready(function() {

	var oSystemMessaging = $("#system-messaging");

	oSystemMessaging.pinFooter();

	$(window).resize(function()
	{
		oSystemMessaging.pinFooter();
	});

	var sHtml = '<div id="system-messaging-list"><ul>';
	sHtml += '<li class="message-title">'+ $.sc.locale("messaging.process.title")+'</li>';
	sHtml += '<li id="rni-offline" class="system-message-label error rni-link" id="link-status"><a rel="black" class="rounded bubble" title="'+$.sc.locale("messaging.process.rniofflinemsg")+'">'+$.sc.locale("messaging.process.rnioffline")+' </a></li>';
	sHtml += '<li id="request-processing" class="system-message-label"><a href="#" class="processing rounded">'+ $.sc.locale("action.recentRequest.title")+' <span id="long-running-process-size-p" class="count rounded hide"></span></a></li>';
	sHtml += '<li id="request-complete" class="system-message-label"><a href="#" class="rounded">'+ $.sc.locale("action.recentRequest.title")+' <span id="long-running-process-size-c" class="count rounded hide"></span></a></li>';
	sHtml += '<li id="processing-size" class="hide"></li></ul></div>';

	oSystemMessaging.html(sHtml);

	sensus.pages.longrunningprocess.longRunningProcessSystemMessaging();
	sensus.pages.longrunningprocess.checkRni();

	if (sensus.pages.longrunningprocess.isRniOn)
	{
		$(".jquerybubblepopup").remove();
	}
	else
	{
		$("#rni-offline").mouseover(function() {

			$(".jquerybubblepopup").removeClass("hide-jquerybubblepopup");

		}).mouseout(function() {

			$(".jquerybubblepopup").addClass("hide-jquerybubblepopup");

		});

		$('.bubble').CreateBubblePopup({
				position       : 'top',
				align	       : 'center',
				innerHtml      : $('.bubble').attr('title'),
				width          : '200px',
				themeMargins   : {
					total       : '5px',
					difference  : '5px'
				},
				innerHtmlStyle : {
					color         : ($(this).attr('id')!='azure' ? '#000000' : '#333333'),
					'text-align'  :'center'
				},
				themeName      : 	'black',
				themePath      : 	'images/jquerybubblepopup-theme'
		});

		$('.bubble').removeAttr('title');
	}

	/** Open processing details */
	var oBody = $("body");

	oBody.on("click", "#request-processing a", function(e) {
		e.preventDefault();
		sensus.util.actiondialog.launchActionDialog("tableDialog", sensus.pages.longrunningprocess.dialogSettingsProcess["tableDialog"]);
	});

	oBody.on("click", "#request-complete a", function(e) {
		e.preventDefault();
		sensus.util.actiondialog.launchActionDialog("tableDialog", sensus.pages.longrunningprocess.dialogSettingsProcess["tableDialog"]);
	});

	/** Check each 10 seconds the list long running process */
	var fnAutoRefreshLRP = setInterval(function() {

		sensus.pages.longrunningprocess.longRunningProcessSystemMessaging();

	}, sensus.settings.longRunningProcessTime);

	/** Check each 5 minutes the rni */
	var fnAutoRefreshRNI = setInterval(function() {

		sensus.pages.longrunningprocess.checkRni();

	}, sensus.settings.checkRniTime);

	var oLrpDialog    = $("#action-dialog-lrp"),
		aSelectedIds  = [];

	oLrpDialog.on("click", "#remove-all-lrp", function(e) {
		e.preventDefault();

		$.each(oLrpDialog.find("table tbody").find("input:checked"), function(e, i){
			aSelectedIds.push({'id':$(this).attr("value")});
		});

		if (aSelectedIds.length != 0 )
		{
			$('.message-recent-request').removeClass("ui-state-error");

			var fnCallBack = function(e) {

				sensus.pages.longrunningprocess.longRunningProcessSystemMessaging();

				$("#checked-count-dialog").text("0");
				$("#select-dialog").prop("checked", false);

				sensus.widgets.datatable.reloadTable(sensus.pages.longrunningprocess.lrpTable);

			};

			var oRequest = new processRequest(aSelectedIds);
			oRequest.action =  "updateMonitoProcess";
			$.sc.getJson("api/process/update", oRequest, false, fnCallBack);
		}
		else
		{
			$('.message-recent-request').addClass("ui-state-error");
		}

		aSelectedIds = [];
	});

	var oEventHistoryProcess = $(".event-history-process");

	oEventHistoryProcess.unbind();
	oEventHistoryProcess.die();

	$("#processing-container").on("click", oEventHistoryProcess, function(e) {
		event.preventDefault();

		$.sc.closeActionDialog(oLrpDialog);

		var oParam = {
			sUrl             : $(this).attr('href'),
			$container       : $('#load'),
			$element         : $(this),
			$container_tabs  : $('#tabs-content')
		};

		$.sc.loadTab($.extend({}, sensus.commons.lib.ajax.param, oParam));
	});

	oBody.on('click', '#process-table a.afilter, a.alist', function() {
		$.sc.closeActionDialog(oLrpDialog);
	});

	/**
	 * Export Csv File Sucess Process
	 */
	oLrpDialog.on("click", "#export-csv-file-success-process", function() {

		var nProcessId  = $(this).closest("tr").find("td:eq(1)").text(),
			sFileName   = $(this).find("span").text();

		sensus.util.process.downloadFile(sFileName, true, nProcessId);

		sensus.widgets.datatable.reloadTable(sensus.pages.longrunningprocess.lrpTable);

		return false;
	});

	//Open Childrens
	oLrpDialog.on("click", "#process-table .spindown", function(e) {

		var orowNow    = $(this).parent(),
			iParentId  = $(this).find('span').attr('id');

		var aProcessList = [{
			id : iParentId
		}];

		if (!orowNow.hasClass('childOn'))
		{
			var fnCallback = function(response)
			{
				$(this).find('span').removeClass("ui-icon-triangle-1-e").addClass("ui-icon-triangle-1-s");

				var aProcesses = response.processes;
				var oRow = "<tr class='spindown_child'>";

				for (i in aProcesses)
				{
					if (aProcesses.hasOwnProperty(i))
					{
						var sDate         = $.sc.dateFormat(aProcesses[i].createDate,"h:i:s.fff A"),
							sParentId      = "parent-id-" + iParentId,
							ochildProcess  ="<tr class='spindown-child "+ sParentId +"' style='display: table-row;'>";

						ochildProcess+= "<td></td>";
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

						aProccessItems = aProcesses[i].processItems;

						ochildProcess+= "<td><a href='main#/light?processId="+ aProcesses[i].id +"' >" +  aProcesses[i].processItemAmount + "</a></td>";
						ochildProcess+= "<td>"+ aProcesses[i].processItemFailedAmount + "</td>";
						ochildProcess+= "<td>"+ sensus.util.process.fnFormatDescription(aProcesses[i].lcAction.actionTypeValue, aProcesses[i].isProcessComplete, aProcesses[i].description, aProcesses[i].processItems[0].light.id, aProcesses[i].processItems[0].light.poleId) +"</td>";
						ochildProcess+= "<td>"+ sDate +"</td>";

						if (!aProcesses[i].isProcessComplete)
						{
							ochildProcess+= "<td class='processing' ><span class='processing'>" + $.sc.locale("table.type.processing") + "</span></td>"
						}
						else
						{
							ochildProcess+= "<td>" + $.sc.locale("table.type.complete") + "</td>"
						}

						oRow += "<td><span>"+ $.sc.locale('sensus.process.'+aProcesses[i].lcAction.actionType.toLowerCase()) + "</span></td>";
						orowNow.after(ochildProcess);
						orowNow.addClass('childOn');

						$.sc.stopProgressBar(null,false);
					}
				}
			};

			$.sc.getJson("api/process/fetch/id", new processRequest(aProcessList), false, fnCallback);

		}
		else
		{
			orowNow.removeClass('childOn');
			orowNow.find('span').removeClass("ui-icon-triangle-1-s");
			var orowChild     = orowNow.next('tr'),
				orowChildNext = orowChild.next('tr');

			if(orowChild.hasClass('spindown-child'))
			{
				orowChild.remove();
				orowChild = orowChildNext;
				orowChildNext = orowChildNext.next('tr');
			}

			$.sc.stopProgressBar(null,false);
		}
	});
});
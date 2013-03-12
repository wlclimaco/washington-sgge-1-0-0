/**
 * @fileoverview Initializes long running process.
 *
 * @author Raphael Constantino
 */

$(document).ready(function() {

	sensus.util.page.initMessaging();

	var oSystemMessaging = $("#system-messaging");

	/** Initializate system messagim bar */

	oSystemMessaging.pinFooter();

	$(window).resize(function() {

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

	if (sensus.pages.longrunningprocess.isRniOn) {

		$(".jquerybubblepopup").remove();

	} else {

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

		if (aSelectedIds.length != 0 ) {

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

		} else {

			$('.message-recent-request').addClass("ui-state-error");

		}

		aSelectedIds = [];
	});

	var oEventHistoryProcess = $(".event-history-process");

	oEventHistoryProcess.unbind();
	oEventHistoryProcess.die();

	$("#processing-container").on("click", oEventHistoryProcess, function(e) {
		event.preventDefault();

		$('#action-dialog-lrp').dialog('close');

		var oParam = {
			sUrl             : $(this).attr('href'),
			$container       : $('#load'),
			$element         : $(this),
			$container_tabs  : $('#tabs-content')
		};

		sensus.commons.lib.ajax.loadTab($.extend({}, sensus.commons.lib.ajax.param, oParam));
	});

	oBody.on('click', '#process-table a.afilter, a.alist', function() {
		$('#action-dialog-lrp').dialog('close');
	});

	/**
	 * Export Csv File Sucess Process
	 */
	$("#action-dialog-lrp").on("click", "#export-csv-file-success-process", function() {

		var nProcessId  = $(this).closest("tr").find("td:eq(1)").text(),
			sFileName   = $(this).find("span").text();

		sensus.util.process.downloadFile(sFileName, true, nProcessId);

		sensus.widgets.datatable.reloadTable(sensus.pages.longrunningprocess.lrpTable);

		return false;
	});

});
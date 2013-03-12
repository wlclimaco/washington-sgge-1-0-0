// plugin definition

$summary = null;

$.fn.summary = function(sId, type) {

	// Our plugin implementation code goes here.
	var objThat = $(this),
		search = function() {

			// Empty content of the DOM
			$(objThat).empty();

			var $content = $($summary).clone();
			var bShowDialog = true;
			var sUrl = "";

			$(objThat).append($content);

			if (type == undefined) {

				sUrl = 'id=' + sId;

			} else {

				sUrl = 'id=' + sId + '&type=' + type;

			}

			var aList = [{'id': parseInt(sId)}];
			var oRequest = {
				"action"	:  "id",
				"id"		:   parseInt(sId)
			};

			$.ajax({
			  url			: 'api/process/fetch',
			  dataType     	: 'json',
			  contentType  	: "application/json; charset=utf-8",
			  type         	: "POST",
			  //data         	: new processRequest(aList,null,null,null),
			  data         	: $.toJSON(oRequest),
			  async : false,
			  success: function(data) {

				  if (data.operationSuccess) {

					  var oRetry = $('<a class="button small ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" href="process/retryUnreacheable.action" id="retry-buttom" role="button" aria-disabled="false"><span class="ui-button-text">' + $.sc.locale("action.summary.retry") + '</span></a>');
					  var oExport = $('<small> <a class="launch" href="#" >Export as a .csv file</a></small>');
					  var oSummaryContainer = $('#summary-container', objThat);

					  $("#ui-dialog-title-action-dialog").text("Request Summary");
					  $("#id-process", $content).text(sId);

					  var sMessage = $.sc.locale("process.page.text", "0", "5", "6");

					  $("li#request em").before(sMessage);

					  if (data.firstProcess.startTime) {

						  $('#hour-completed', $content).text($.sc.dateFormat(data.firstProcess.endTime, 'h') - $.sc.dateFormat(data.firstProcess.startTime, 'h'));
						  $('#minute-completed', $content).text($.sc.dateFormat(data.firstProcess.endTime, 'i') - $.sc.dateFormat(data.firstProcess.startTime, 'i'));

					  }

					  var aItems = data.firstProcess.processItems;
					  var iCountFailure = 0;
					  var iCountSuccess = 0;
					  var iCount = {};
						iCount.ABORTED = 0;
						iCount.RNI_SYNC_FAILURE = 0,
						iCount.LIGHT_DEACTIVATED = 0,
						iCount.LIGHT_PROTECTED = 0,
						iCount.LIGHT_IN_MAINTENANCE = 0,
						iCount.LIGHT_WITH_FAILURE = 0,
						iCount.RNI_ASYNC_FAILURE = 0;
						iCount.LIGHT_WITH_MAX_GROUP_ALLOWED = 0;
						iCount.LIGHT_BELONG_GROUP = 0;

					  for(k=0; k<aItems.length; k++){

						if(aItems[k].processResult == 'SUCCESS'){

							iCountSuccess = iCountSuccess + 1;

						} else {

							iCountFailure = iCountFailure + 1;

						}

						if(aItems[k].processReason){

							iCount[aItems[k].processReason] = parseInt(iCount[aItems[k].processReason]) + 1;

						}

					  }

					  var iPercentSuccess  = (iCountSuccess/((iCountSuccess)+(iCountFailure)))*100;

					  if (iCountFailure == 0) {

							$('div.selected-points').hide();
							$(".export-fail", objThat).parent('small').remove();

					  }

					  if (iCountSuccess == 0) {

							$(".export-success", objThat).parent('small').remove();

					  }

					  $('#count-failure', $content).text($.convertionNumber(iCountFailure,false,0).dvalueConverter);
					  $('#count-success', $content).text($.convertionNumber(iCountSuccess,false,0).dvalueConverter);
					  $('#percent-success', $content).text($.convertionNumber(iPercentSuccess.toFixed(2),false,0).dvalueConverter);
					  $('#percent-failure', $content).text($.convertionNumber((100-(iPercentSuccess)).toFixed(2),false,0).dvalueConverter);

					  if(iCount.ABORTED > 0) {
						  oSummaryContainer.append('<li class="aborted">' + iCount.ABORTED + ' ' + $.sc.locale("process.page.error.aborted") + '</li>');
						  oSummaryContainer.find(".aborted").append(oExport.clone().addClass("export export-aborted"));
					  }

					  if(iCount.RNI_SYNC_FAILURE > 0) {
						  oSummaryContainer.append('<li class="syncFailure">' + iCount.RNI_SYNC_FAILURE + ' ' + $.sc.locale("process.page.error.sync") + '</li>');
						  oSummaryContainer.find(".syncFailure").append(oRetry);
						  oSummaryContainer.find(".syncFailure").append(oExport.clone().addClass("export export-sync"));
					  }

					  if(iCount.LIGHT_DEACTIVATED > 0) {
						  oSummaryContainer.append('<li class="lightDeactivated">' + iCount.LIGHT_DEACTIVATED + ' ' + $.sc.locale("process.page.error.deactivated") + '</li>');
						  oSummaryContainer.find(".lightDeactivated").append(oExport.clone().addClass("export export-deactivated"));
					  }

					  if(iCount.LIGHT_PROTECTED > 0) {
						  oSummaryContainer.append('<li class="lightProtected">' + iCount.LIGHT_PROTECTED + ' ' + $.sc.locale("process.page.error.protected") + '</li>');
						  oSummaryContainer.find(".lightProtected").append(oExport.clone().addClass("export export-protected"));
					  }

					  if(iCount.LIGHT_IN_MAINTENANCE > 0) {
						  oSummaryContainer.append('<li class="lightMaintenance">' + iCount.LIGHT_IN_MAINTENANCE + ' ' + $.sc.locale("process.page.error.maintenance") + '</li>');
						  oSummaryContainer.find(".lightMaintenance").append(oExport.clone().addClass("export export-maintenance"));
					  }

					  if(iCount.LIGHT_WITH_FAILURE > 0) {
						  oSummaryContainer.append('<li class="lightWithFailure">' + iCount.LIGHT_WITH_FAILURE + ' ' + $.sc.locale("process.page.error.light_with_failure") + '</li>');
						  oSummaryContainer.find(".lightWithFailure").append(oExport.clone().addClass("export export-failure"));
					  }

					  if(iCount.RNI_ASYNC_FAILURE > 0) {
						  oSummaryContainer.append('<li class="unreachable">' + iCount.RNI_ASYNC_FAILURE + ' ' + $.sc.locale("process.page.error.async") +' </li>');
						  oSummaryContainer.find(".unreachable").append(oRetry);
						  oSummaryContainer.find(".unreachable").append(oExport.clone().addClass("export export-async"));

					  }

					  if(iCount.LIGHT_WITH_MAX_GROUP_ALLOWED > 0) {
						  oSummaryContainer.append('<li class="lightWithMaxGroupAllowed">' + iCount.LIGHT_WITH_MAX_GROUP_ALLOWED + ' ' + $.sc.locale("process.page.error.light_with_max_group_allowed") + '</li>');
						  oSummaryContainer.find(".lightWithMaxGroupAllowed").append(oExport.clone().addClass("export export-max-group"));
					  }

					  if(iCount.LIGHT_BELONG_GROUP > 0) {
						  oSummaryContainer.append('<li class="lightBelongGroup">' + iCount.LIGHT_BELONG_GROUP + ' ' + $.sc.locale("process.page.error.light_belong_group") + '</li>');
						  oSummaryContainer.find(".lightBelongGroup").append(oExport.clone().addClass("export export-light-belong-group"));
					  }

					  var x = 0;
				  	  var oSmtBody = $('#summary-meters-table > tbody:last', $content);
				  	  var sTableLines = "";

					  if (type && type > 0) {

						  	var sLightId = "";
							var sLightAddress = "";
						  	var sLightStatus = "";
							var oLight = null;
						  	var aProcessItems = data.firstProcess.processItems;
							var nCount = 0;

						  	/** fill table with devices **/
							for (x in aProcessItems) {

								if (aProcessItems.hasOwnProperty(x) && aProcessItems[x].processResult && aProcessItems[x].processResult != "SUCCESS") {

									oLight = aProcessItems[x].light;

									// Column Address
									if (oLight.parameters[0].value && oLight.parameters[0].value !== undefined) {
										sLightAddress = oLight.parameters[0].value;
									}

									// Column Id
									if (oLight.id && oLight.id !== undefined) {
										sLightId = oLight.rniId;
									}

									// Column Status
									if (aProcessItems[x].processReason && aProcessItems[x].processReason  !== undefined) {
										sLightStatus = aProcessItems[x].processReason;
									}

									sTableLines += '<tr><td>'+ sLightId + '</td><td>'+ sLightAddress + '</td><td>'+ $.sc.locale("process.page.error." + sLightStatus.toLowerCase()) + '</td></tr>';
									nCount++;
								}
							}

							oSmtBody.append(sTableLines);

							// Header with total of lights
							$("#displaying").html($.sc.locale("process.page.displaying", nCount + "", nCount + ""));

						} else {
							$('div.selected-points').hide();
						}

					  	oRetry.click(function(e) {
							e.preventDefault();
							var aList = [{'id': parseInt(sId)}];
							$.ajax({
								  url			: $(this).attr("href"),
								  dataType     	: 'json',
								  contentType  	: "application/json; charset=utf-8",
								  type         	: "POST",
								  data         	: $.toJSON(new processRequest(aList,null,null,null)),
								  async        	: false,

							});

							if ($(sensus.pages.longrunningprocess.lrpTable).is(':visible')) {

								sensus.widgets.datatable.reloadTable(sensus.pages.longrunningprocess.lrpTable);

							} else {

								$('.ui-dialog').hide();
								sensus.widgets.datatable.reloadTable(sensus.pages.process.eventHistoryTable);

							}

					  	});

				  } else {

					  bShowDialog = false;

					  if (type) {

						sensus.util.page.showMessage("messaging-main", $.sc.locale('action.summary.retry.error') , "error");

					  }

				  }

				}

			});

			if (bShowDialog && type) {

				sensus.util.actiondialog.launchActionDialog("summary", sensus.widgets.datatable.dialogSettings["summary"]);

			}

			return bShowDialog;

	};
	objThat.undelegate("click").unbind("click");

	$(objThat).on("click", ".export", function(e) {
		var sStatusEnum;
		var sReasonEnum;
		var sFailValue = "";
		var sFileName = "";

		if ($(this).hasClass("export-success")) {

			sStatusEnum = 'SUCCESS';

		} else {

			sStatusEnum = 'MLCFAILURE';

			if ($(this).hasClass("export-protected")) {
				sReasonEnum = 'LIGHT_PROTECTED';
			} else if ($(this).hasClass("export-sync")) {
				sReasonEnum = 'RNI_SYNC_FAILURE';
			} else if ($(this).hasClass("export-async")) {
				sReasonEnum = 'RNI_ASYNC_FAILURE';
			} else if ($(this).hasClass("export-aborted")) {
				sReasonEnum = 'ABORTED';
			} else if ($(this).hasClass("export-deactivated")) {
				sReasonEnum = 'LIGHT_DEACTIVATED';
			} else if ($(this).hasClass("export-maintenance")) {
				sReasonEnum = 'LIGHT_IN_MAINTENANCE';
			}
		}

		var aProcessItems = [{'processItemStatusEnum': sStatusEnum, 'processStatusReasonEnum': sReasonEnum}];
		var aProcessList = [{ 'id': sId,  'processItems' : aProcessItems}];
		var oRequest = new processRequest(aProcessList, null, null, null);
		var data = { 'processRequest' : oRequest };

		$.ajax({
			url   : sensus.settings.generateSummaryFileCSV,
			dataType     : 'json',
			contentType  : "application/json; charset=utf-8",
			type         : "POST",
			data         : $.toJSON(data),
			async        : false,
			success : function(result) {

				if (result.operationSuccess) {

					if (result.fileName.length > 0) {

						window.location = "process/downloadCSVFile.action?fileName=" + result.fileName + "&Id=" + sId;

					}

				}

			}

		});

		return false;
	});

	if (!$summary) {

		$.ajax({
			url    : "util/summary",
			async  : false,
			success: function(data) {
				$summary = data;
				if (!search()) {
					sensus.pages.longrunningprocess.hasError = true;
				}
			}
		});

	} else {

		search();

	}

	/**
	 * Export CSV
	 */
	$(".summary").on("click", "#csv", function(e) {
		e.preventDefault();
		$.address.parameter("process_id", sId);
		$.address.parameter("status_item", 10);
		sensus.util.exportcsv.generateCSV("process/ajax.insertCSVProcess.action", "process/ajax.generateFileCSV.action");
	});

	$(".summary").on("click", "#export-success", function(e) {
		e.preventDefault();
		$.address.parameter("process_id", sId);
		$.address.parameter("status_item", 5);
		sensus.util.exportcsv.generateCSV("process/ajax.insertCSVProcess.action", "process/ajax.generateFileCSV.action");
	});

	$(".summary").on("click", "#export-unreachable", function(e) {
		e.preventDefault();
		$.address.parameter("process_id", sId);
		$.address.parameter("status_item", 10);
		sensus.util.exportcsv.generateCSV("process/ajax.insertCSVProcess.action", "process/ajax.generateFileCSV.action");
	});

};
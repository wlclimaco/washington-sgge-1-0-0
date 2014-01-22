/**
 * @namespace sensus.pages.longrunningprocess.dialogSettingsProcess
 * @description
 */

/**
 * @fileoverview
 * @author Raphael Constantino
 */

sensus.pages.longrunningprocess.dialogSettingsProcess = {

		oRecentDialog : $(),

		longRunningProcessDialog : {

			// Whether this dialog requires a light list.
			requiresSmartpoints  : true,
			width                : 540,
			title                : $.sc.locale("action.longRunningProcessDialog"),
			isMonitored			 : true,

			/**
			 * The function that will be called when the action dialog is launched.
			 *
			 * @param actionDialog
			 *            a reference to the action dialog object
			 */
			action : function(actionDialog) {

				actionDialog.dialog('open');
				actionDialog.dialog( "option", "position", "center" );

				var oActionDialog = actionDialog;

				oActionDialog.empty().load("light/monitorDialog", function() {

					oActionDialog.removeClass("waiting");

					$(".descriptive-button-row a").button();

					// if action is not monitored
					$("a.dismiss", oActionDialog).click($.sc.monitorDialogEvent);

					// if action is monitored
					$("a.monitor", oActionDialog).click($.sc.monitorDialogEvent);

				});
				$.sc.stopProgressBar(null,false);
			}

		},

		/**
		 * Configuration for the Long Running Process Dialog Recent Request
		 */
		tableDialog : {

			/**
			 * Whether this dialog requires a light list.
			 */
			requiresSmartpoints : true,

			/**
			 * The dialog title.
			 */
			title: $.sc.locale("action.recentRequest.title"),

			/**
			 * The dialog width.
			 */
			width: 1024,

			/**
			 * The dialog minheight.
			 */
			minheight: 600,

			/**
			 * The dialog height.
			 */
			height: 600,

			/**
			 * The dialog resizable.
			 */
			resizable: true,

			/**
			 * The action before close.
			 */
			beforeClose : function() {
				sensus.widgets.datatable.clearSelectsFunction.call(sensus.pages.longrunningprocess.lrpTable, true);
			},

			/**
			 * The function that will be called when the action dialog is launched.
			 *
			 * @param actionDialog
			 *            a reference to the actionDialog objext
			 */
			action : function(actionDialog) {

				actionDialog.dialog('open');
				$('html, body').animate({ scrollTop: 0 }, 'fast', function() {

					actionDialog.dialog( "option", "position", "center" );

				});

				actionDialog.addClass("waiting");

				function fnGetRecentDialog() {

					var oRecentDialog = sensus.pages.longrunningprocess.dialogSettingsProcess.oRecentDialog;

					if ( oRecentDialog.length )
					{
						return oRecentDialog;
					}
					else
					{
				       return $.get('process/includeProcessRecentDialog').success(function(e) {

				    	   sensus.pages.longrunningprocess.dialogSettingsProcess.oRecentDialog = e;
				    	   return e;

				       });
					}
				}

				actionDialog.removeClass("waiting");

				$.when(fnGetRecentDialog()).then(function() {

					   actionDialog.html(sensus.pages.longrunningprocess.dialogSettingsProcess.oRecentDialog);

						/** * jQuery dataTable setup ** */
						sensus.pages.longrunningprocess.lrpTable = $('#process-table').dataTable(sensus.widgets.datatable.setTable(
							{
								id : "process-table",
								sAjaxSource : "api/process/fetch/monitored",
								aColumns : [
									     	{sId : "Id",             sWidth : "5%", bVisible: false},
											{sId : "Action",         sWidth : "5%"},
											{sId : "ActionId",	     sWidth : "5%", bVisible: false},
											{sId : "smartpoint",	 sWidth : "5%"},
											{sId : "lightFailed",	 sWidth : "5%", bVisible: true, bSortable : true},
											{sId : "SmartPointsId",  sWidth : "5%", bVisible: false},
											{sId : "Description",    sWidth : "5%"},
											{sId : "CreateUser",     sWidth : "5%", bVisible: false },
											{sId : "StartTime",      sWidth : "5%"},
											{sId : "status",         sWidth : "5%"},
											{sId : "isSubmitted",    sWidth : "5%", bVisible: false},
											{sId : "flexNetId",      sWidth : "5%", bVisible: false},
											{sId : "parentId",       sWidth : "5%", bVisible: false},
											{sId : "CreateDate",     sWidth : "5%", bVisible : false}
								         ],
								oSettings : {
									oRequest   : processRequest,
									fnRequest  : function() {},
									aColumns          : ['id','lcAction[object]','lcAction.actionTypeValue','processItems[fn(sensus.pages.longrunningprocess.processSize)]', 'processItemFailedAmount', '','description','createUser','startTime','isProcessComplete','isSubmitted','processItems[object]','parentProcess.id','createDate'],
									sResponseObj      : 'processes',
									orderType         : "date",
									iDefaultCol		  : 8,
									sDefaultSort      : "desc",
									bDialogCheckbox   : true,
									iDisplayLength    : 1,
									smartpointFilter  : {
										aCols   : ["smartpoint"],
										filter  : "process"
									},
									process           : {
										abort : {
											url      : "api/process/abort",
											text     : function(data, i) {
												return $.sc.locale("longRunning.table.message.abort");
											},
											oRequest  : 'processRequest',
											fnRequest : function(data, i) {

												var aProcessList = [{
													id : data[i.Id]
												}];

												return new processRequest(aProcessList);
											},
											success  : function(data, i) {

												sensus.widgets.datatable.reloadTable(sensus.pages.longrunningprocess.lrpTable);
												return $.sc.locale("action.longRunningProcessDialog.abortSuccess");
											}
										},
										remove : {
											url     : "api/process/update",
											text    : function(data, i) {
												return $.sc.locale("longRunning.table.message.remove");
											},
											oRequest  : 'processRequest',
											fnRequest : function(data, i) {

												var aProcessList = [{
													id : data[i.Id]
												}];
												var oRequest = new processRequest(aProcessList);

												oRequest.action = "updateMonitoProcess";

												return oRequest;
											},
											success : function(data, i) {
												sensus.widgets.datatable.reloadTable(sensus.pages.longrunningprocess.lrpTable);
												return $.sc.locale("action.longRunningProcessDialog.removeSuccess");

											}
										},
										cancel : {
											url     : "process/abortProcessUrl.action",
											text    : function(data, i) {
												return $.sc.locale("commons.pages.cancelConfirm",data[i.ActionType]);
											},
											oRequest  : 'processRequest',
											fnRequest : function(data, i) {

												var aProcessList = [{
													id : data[i.Id]
												}];

												var oRequest = new processRequest(aProcessList);

												oRequest.action = "updateMonitoProcess";

												return oRequest;
											},
											success : function(data, i) {
												sensus.widgets.datatable.reloadTable(sensus.pages.longrunningprocess.lrpTable);
												return $.sc.locale("commons.pages.cancelSuccess",data[i.ActionType]);
											}
										}
									}
								},

								fnRowCallback : function(nRow, aData, iDisplayIndex, col) {

									var oAction = $.parseJSON(aData[col.Action]);

									$("td:eq(" + col.Action + ")", nRow).text($.sc.locale('sensus.process.'+oAction.actionType.toLowerCase()))
																		.attr('title', 'Process ID ' + aData[col.Id]);

									var oFlexNet          = $.parseJSON(aData[col.flexNetId]),
										nSmartpointCount  = aData[col.smartpoint];

									if (oFlexNet[0])
									{
										nId	= oFlexNet[0].light.id;
									}

									if (aData[col.status])
									{
										if (aData[col.smartpoint] > 0)
										{
											$(nRow).addClass("summary");
											$(nRow).data("id", aData[col.Id]);
										}
									}

									$("td:eq(" +col.smartpoint+ ")", nRow).text(nSmartpointCount);

									if (aData[col.lightFailed] > 0)
									{
										$("td:eq(" + col.lightFailed + ")", nRow).addClass('alerts');
									}

									//Format hour and minute
									if (nSmartpointCount < 1 || aData[col.ActionId] == '7')
									{
										$("td:eq(" +col.smartpoint+ ")", nRow).html('<span class="ui-state-disabled">'+nSmartpointCount+'</span>');
									}
									else
									{
										var sType         = 'processId',
											sActionValue  = aData[col.Id];

										$("td:eq(" + col.lightFailed + ")", nRow).html('<a href="light?'+ sType +'=' + sActionValue +'&failed=true" class="alist" >' + aData[col.lightFailed] + '</a>');

										if (nSmartpointCount > 1)
										{
											$("td:eq(" +col.smartpoint+ ")", nRow).html('<a href="light?'+ sType +'=' + sActionValue +'" class="alist" >' + nSmartpointCount + '</a>');
										}
										else
										{
											$("td:eq(" +col.smartpoint+ ")", nRow).html('<a href="lightDetail?id='+ nId +'" class="alist">' + nSmartpointCount + '</a>');
										}
									}

									if (!aData[col.status])
									{
										$("td:eq(" +col.status+ ")", nRow).html("<span class='processing'>" + $.sc.locale("table.type.processing") + "</span>");
									}
									else
									{
										$("td:eq(" +col.status+ ")", nRow).html("<span>" + $.sc.locale("table.type.complete") + "</span>");
									}

									$("td:eq(" +col.StartTime+ ")", nRow).text($.sc.dateFormat(aData[col.StartTime], 'h:i:s.fff A'));

									if (!($.sc.isNullOrUndefined(oFlexNet[0])))
									{
										$('td:eq(' + col.Description + ')', nRow).html(sensus.util.process.fnFormatDescription(aData[col.ActionId], aData[col.status], aData[col.Description], nId, oFlexNet[0].light.poleId));
									}

									// add triangle to process with parent
									if (aData[col.parentId] && aData[col.parentId] !== "0")
									{
										$("td:eq("+ col.Action +")", nRow).addClass("spindown").append("<span class='ui-icon-triangle-1-e ui-icon' id='"+ aData[col.parentId] +"'></span>");
									}
								},

								fnDrawCallback : function(setting, col) {

									$("#process-table td:eq("  + col.lightFailed + ") a, #process-table td:eq("  + col.smartpoint + ") a").click(function (){
										$("#action-dialog-lrp").dialog("close");
									});

									var sSummary = '<tr class="summary-container"><td colspan="10"><a class="summary-toggle" href="">'
														+ $.sc.locale("action.recentRequest.viewsummary")
														+'</a><ul class="ui-state-highlight summary hide"></td></tr>';

									$(".table-footer, .stamp", $("#action-dialog-lrp")).addClass("hide");
									$("#process-table_wrapper .table-footer").addClass('process-table-footer');

									//Summary
									$.each($(this).find(".summary"), function(i, e) {

										var $summary = $(sSummary),
											nId = $(this).data("id");

										$(this).attr("id", nId);

										$summary.find(".summary-toggle").toggle(

											function() {

												var $summaryBox = $(this).parent().find(".summary");

												$(this).text($.sc.locale("longRunning.table.closeSummary"));

												if (!$summaryBox.data("loaded"))
												{
													$summaryBox.summary(nId);
													$summaryBox.data("loaded", true);
												}

												// Check if on widget summary throw exception
												if (!sensus.pages.longrunningprocess.hasError)
												{
													$(this).parent()
														   .find(".summary")
														   .slideDown("500");
												}
												else
												{
													// Show Error Message
													$(this).parent()
															.find(".summary")
															.addClass("ui-state-error")
															.text($.sc.locale('action.summary.retry.error'))
															.slideDown("500");
												}
											},
											function() {

												$(this).text($.sc.locale("longRunning.table.viewSummary"))
													   .parent()
													   .find(".summary")
													   .slideUp("500");
											}

										);

										$(e).data("summary", $summary);
										$summary.insertAfter($(e));
									});
								}

							}

						));

				   });
			}
		}

};
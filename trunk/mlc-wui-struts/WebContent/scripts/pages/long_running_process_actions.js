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

			// Whether this dialog requires a smartpoint list.
			requiresSmartpoints  : true,
			width                : 540,
			title                : sensus.locale.get("action.longRunningProcessDialog"),
			isMonitored			 : true,

			/**
			 * The function that will be called when the action dialog is launched.
			 *
			 * @param actionDialog
			 *            a reference to the actionDialog objext
			 */
			action : function(actionDialog) {

				actionDialog.dialog('open');
				actionDialog.dialog( "option", "position", "center" );

				var oActionDialog = actionDialog;

				oActionDialog.empty().load(sensus.settings.longRunningProcessDialog, function() {

					oActionDialog.removeClass("waiting");

					$(".descriptive-button-row a").button();

					// if action is not monitored
					$("a.dismiss", oActionDialog).click($.ajaxValidator.fnMonitorDialogEvent);

					// if action is monitored
					$("a.monitor", oActionDialog).click($.ajaxValidator.fnMonitorDialogEvent);


				});

				sensus.util.page.stopProgressBar(null,false);

			}

		},

		/**
		 * Configuration for the Long Running Process Dialog Recent Request
		 */

		tableDialog : {

			/**
			 * Whether this dialog requires a smartpoint list.
			 */

			requiresSmartpoints : true,

			/**
			 * The dialog title.
			 */

			title: sensus.locale.get("action.recentRequest.title"),

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

					if ( oRecentDialog.length ) {

						return oRecentDialog;

					} else {

				       return $.get('process/include.process.recent.dialog.action').success(function(e) {

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
								sAjaxSource : "process/search.action",
								aColumns : [
									     	{sId : "Id",             sWidth : "5%", bVisible: false},
											{sId : "Action",         sWidth : "5%"},
											{sId : "ActionId",	     sWidth : "5%", bVisible: false},
											{sId : "smartpoint",	 sWidth : "5%"},
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
									aColumns          : ['id','lcAction[object]','lcAction.actionTypeValue','processItems[fn(sensus.pages.longrunningprocess.processSize)]','','description','createUser','startTime','isProcessComplete','isSubmitted','processItems[object]','parentProcess.id','createDate'],
									sResponseObj      : 'processes',
									orderType         : "date",
									iDefaultCol		  : 7,
									sDefaultSort      : "desc",
									bDialogCheckbox   : true,
									iDisplayLength    : 1,
									smartpointFilter  : {
										aCols   : ["smartpoint"],
										filter  : "process"
									},
									process           : {
										abort : {
											url      : "process/abortProcessUrl.action",
											text     : function(data, i) {
												return sensus.locale.get("longRunning.table.message.abort");
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
												return sensus.locale.get("action.longRunningProcessDialog.abortSuccess");
											}
										},
										remove : {
											url     : "process/longRunningProcessRemove.action",
											text    : function(data, i) {
												return sensus.locale.get("longRunning.table.message.remove");
											},
											oRequest  : 'processRequest',
											fnRequest : function(data, i) {

												var aProcessList = [{
													id : data[i.Id]
												}];

												return new processRequest(aProcessList);
											},
											success : function(data, i) {
												sensus.widgets.datatable.reloadTable(sensus.pages.longrunningprocess.lrpTable);
												return sensus.locale.get("action.longRunningProcessDialog.removeSuccess");

											}
										},
										cancel : {
											url     : "process/abortProcessUrl.action",
											text    : function(data, i) {
												return sensus.locale.get("commons.pages.cancelConfirm",data[i.ActionType]);
											},
											oRequest  : 'processRequest',
											fnRequest : function(data, i) {

												var aProcessList = [{
													id : data[i.Id]
												}];

												return new processRequest(aProcessList);
											},
											success : function(data, i) {
												sensus.widgets.datatable.reloadTable(sensus.pages.longrunningprocess.lrpTable);
												return sensus.locale.get("commons.pages.cancelSuccess",data[i.ActionType]);
											}
										}
									}
								},
								
								fnRowCallback : function(nRow, aData, iDisplayIndex, col) {

									var oAction = $.parseJSON(aData[col.Action]);

									$("td:eq(" + col.Action + ")", nRow).text(sensus.locale.get('sensus.process.'+oAction.actionType.toLowerCase()))
																		.attr('title', 'Process ID ' + aData[col.Id]);

									var oFlexNet          = $.parseJSON(aData[col.flexNetId]),
										nSmartpointCount  = aData[col.smartpoint];

									if (oFlexNet[0]) {
										var nFlexNetId = oFlexNet[0].light.rniId,
											nId        = oFlexNet[0].light.id;

									}
									
									if (aData[col.status]){
										
										if (aData[col.smartpoint] > 0){	
											
											$(nRow).addClass("summary");
											$(nRow).data("id", aData[col.Id]);
											
										}	
										
									}

									$("td:eq(" +col.smartpoint+ ")", nRow).text(nSmartpointCount);

									//Format hour and minute
									if (nSmartpointCount < 1 || aData[col.ActionId] == '7') {

										$("td:eq(" +col.smartpoint+ ")", nRow).html('<span class="launch ui-state-disabled">'+nSmartpointCount+'</span>');

									} else {

										var sType         = 'lights',
											sActionValue  = '';
										
										for (c in oFlexNet) {

											if (oFlexNet.hasOwnProperty(c)) {

												sActionValue += oFlexNet[c].light.id+'|';

											}

										}
										
										if (nSmartpointCount > 0) {
											
											$("td:eq(" +col.smartpoint+ ")", nRow).html('<a href="smartpoint/ajax.list.action?'+ sType +'=' + sActionValue +'" class="afilter launch">' + nSmartpointCount + '</a>');	
										
										} else {
											
											$("td:eq(" +col.smartpoint+ ")", nRow).html('<a href="smartpoint/ajax.smartpoint.detail.main.action?id='+ nId +'" class="afilter launch">' + nSmartpointCount + '</a>');
											
										}

									}

									if (aData[col.status] == false) {

										$("td:eq(" +col.status+ ")", nRow).html("<span class='processing'>" + sensus.locale.get("table.type.processing") + "</span>");

									} else {

										$("td:eq(" +col.status+ ")", nRow).html("<span>" + sensus.locale.get("table.type.complete") + "</span>");

									}

									$("td:eq(" +col.StartTime+ ")", nRow).text($.date.dateFormat(aData[col.StartTime], 'h:i:s.fff A'));
									
									if (oFlexNet != null && oFlexNet.length > 0) {
										
										$('td:eq(' + col.Description + ')', nRow).html(sensus.util.process.fnFormatDescription(aData[col.ActionId], aData[col.status], aData[col.Description], nId, sensus.util.process.fnGetParameterValue(oFlexNet[0].light, "POLE_ID", 'value')));
									
									}

									// add triangle to process with parent
									if (aData[col.parentId] && aData[col.parentId] !== "0") {

										$("td:eq("+ col.Action +")", nRow).addClass("spindown").append("<span class='ui-icon-triangle-1-e ui-icon' id='"+ aData[col.parentId] +"'></span>");

									}

								},
								
								fnDrawCallback : function(setting, col) {

									var sSummary = '<tr class="summary-container"><td colspan="10"><a class="summary-toggle" href="">'
														+ sensus.locale.get("action.recentRequest.viewsummary")
														+'</a><ul class="ui-state-highlight summary hide"></td></tr>';

									$(".table-footer, .stamp", $("#action-dialog-lrp")).addClass("hide");
									$("#process-table_wrapper .table-footer").addClass('process-table-footer');


									//Summary
									$.each($(this).find(".summary"), function(i, e) {

										var $summary = $(sSummary),
											nId = $(this).data("id");

										$summary.find(".summary-toggle").toggle(

											function() {

												var $summaryBox = $(this).parent().find(".summary");

												$(this).text(sensus.locale.get("longRunning.table.closeSummary"));

												if (!$summaryBox.data("loaded")) {

													$summaryBox.summary(nId);
													$summaryBox.data("loaded", true);

												}

												// Check if on widget summary throw exception
												if (!sensus.pages.longrunningprocess.hasError) {

													$(this).parent()
														   .find(".summary")
														   .slideDown("500");
												} else {

													// Show Error Message
													$(this).parent()
															.find(".summary")
															.addClass("ui-state-error")
															.text(sensus.locale.get('action.summary.retry.error'))
															.slideDown("500");
												}

											},
											function() {

												$(this).text(sensus.locale.get("longRunning.table.viewSummary"))
													   .parent()
													   .find(".summary")
													   .slideUp("500");

											}

										);

										$(e).data("summary", $summary);
										$summary.insertAfter($(e));

									});


									//Open Childrens
									$("#process-table").on("click", ".spindown", function(e) {

											var orowNow    = $(this).parent(),
												iParentId  = $(this).find('span').attr('id');
												
											var aProcessList = [{
												id : iParentId
											}];

											if (!orowNow.hasClass('childOn')) {

												orowNow.find('span').removeClass("ui-icon-triangle-1-e");
												orowNow.find('span').addClass("ui-icon-triangle-1-s");

												$.ajax({
													url: "process/getProcessById.action",
													dataType     	: 'json',
													contentType  	: "application/json; charset=utf-8",
													type         	: "POST",
													data : $.toJSON({'processRequest': new processRequest(aProcessList,null,null,null)}),										
													success: function(response) {
													
														var aProcesses = response.processes;
														

														for (i in aProcesses) {

															if (aProcesses.hasOwnProperty(i)) {

																var sDate         = $.date.dateFormat(aProcesses[i].createDate,"h:i:s.fff A"),
																	sParentId      = "parent-id-" + iParentId,
																	ochildProcess  ="<tr class='spindown-child "+ sParentId +"' style='display: table-row;'>";

																ochildProcess+= "<td></td>";
																ochildProcess+= "<td class='hide'>"+ aProcesses[i].id +"</td>";

																// Check if has parent process
																if (aProcesses[i][11] == "0") {

																	ochildProcess+= "<td>"+ aProcesses[i].id +"</td>";

																} else {

																	ochildProcess+= "<td title='Process ID " + aProcesses[i].id + "'>"+ sensus.locale.get('sensus.process.'+ aProcesses[i].lcAction.actionType.toLowerCase()) + "</td>";

																}
																
																aProccessItems = aProcesses[i].processItems;
																var sActionValue = null;
																
																for (c in aProccessItems) {
																	if (aProccessItems.hasOwnProperty(c)) {
																		sActionValue += aProccessItems[c].light.id+'|';
																	}
																}
																
																ochildProcess+= "<td><a href='smartpoint/ajax.list.action?lights="+ sActionValue +"' class='afilter launch'>" + aProccessItems.length + "</a></td>";
																ochildProcess+= "<td>"+ sensus.util.process.fnFormatDescription(response.firstProcess.id, response.firstProcess.isProcessComplete, response.firstProcess.description, response.firstProcess.processItems[0].light.id, sensus.util.process.fnGetParameterValue(response.firstProcess.processItems[0].light, "POLE_ID", 'value')) +"</td>";
																ochildProcess+= "<td>"+ sDate                +"</td>";
																
																if (aProcesses[i].isProcessComplete == false) {
																
																	ochildProcess+= "<td class='processing'>" + sensus.locale.get("table.type.processing") + "</td>"
											
																} else {
																
																	ochildProcess+= "<td>" + sensus.locale.get("table.type.complete") + "</td>"

																}
																
																ochildProcess+= '<td><a id="delete_' + aProcesses[i].id + '" class="delete delete_dialog delete_col" href="#">' + sensus.locale.get("table.action.delete") + '</a></td></tr>';

																orowNow.after(ochildProcess);
																orowNow.addClass('childOn');

															}

														}

													}

												});

											} else {

												orowNow.removeClass('childOn');
												orowNow.find('span').removeClass("ui-icon-triangle-1-s");
												orowNow.find('span').addClass("ui-icon-triangle-1-e");

												var orowChild = orowNow.next('tr'),
													orowChildNext = orowChild.next('tr');

												while(orowChild.hasClass('spindown-child')) {

													orowChild.remove();
													orowChild = orowChildNext;
													orowChildNext = orowChildNext.next('tr');

												}

											}

									});

								}

							}

						));

				   });

			}
		}

};
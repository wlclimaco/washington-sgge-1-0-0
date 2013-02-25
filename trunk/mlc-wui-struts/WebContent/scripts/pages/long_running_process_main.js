/**
 * @namespace sensus.pages.longrunningprocess
 * @description The creat-main namespace for long running process.
 */
 
/**
 * @fileoverview Defines the core functionality of the long running process.
 * @author Raphael Constantino
 */

/**
 * The main group page namespace containing group functions.
 */

sensus.pages.longrunningprocess = {

		lrpSize      : 0,
		hasError     : false,
		isRniOn      : false,
		bIsDetail    : false,
		processSize  : function(oProcess) {

			nSize = 0;

			if(oProcess.length) {

				nSize = oProcess.length;

			}

			return nSize;

		},
		checkRni   : function() {

			$.when(this.checkRniAjax()).then(function(e) {

				sensus.pages.longrunningprocess.isRniOn = e.operationSuccess;


				if (e.operationSuccess == false) {

					$("#system-messaging").show();
					$("#rni-offline").show();
					sensus.util.page.stopProgressBar(null, true);

				} else if (e.operationSuccess == true) {

					$("#rni-offline").hide();

				}

				if (sensus.pages.longrunningprocess.lrpSize == 0 && e.operationSuccess == true) {

					$("#system-messaging").hide();

				}

			});

		},

		monitor : function(url, data) {

			sensus.pages.longrunningprocess.monitorUrl = url;
			sensus.pages.longrunningprocess.data = data;

			$.when(this.checkRniAjax()).then(function(e) {

				if (($.isArray(e.operationSuccess) && (e.operationSuccess != false)) || url == "process/updateMonitorProcess.action") {

					if (sensus.settings.monitor == 3) {

						sensus.util.actiondialog.launchActionDialog("longRunningProcessDialog", sensus.pages.longrunningprocess.dialogSettingsProcess["longRunningProcessDialog"], "monitor-dialog");

					} else {

						sensus.pages.longrunningprocess.sendAction(sensus.settings.monitor == 1 ? true : false);

					}

				} else {

					//sensus.util.page.stopProgressBar(null, true);
					sensus.util.page.showMessage("messaging-main", sensus.locale.get("commons.pages.error") + sensus.locale.get("action.longRunningProcessDialog.rnioffline"), "error");

					if (sensus.pages.longrunningprocess.isClearSelect) {

						sensus.widgets.datatable.clearSelects.call(sensus.pages.longrunningprocess.lrpTable);

					}

				}

			});

		},

		// Check rni
		checkRniAjax : function () { 
			return $.ajax({
				url   		 : sensus.settings.checkRni,
				dataType     : 'json',
				type         : "POST",
				data         : $.toJSON({'processRequest' : new processRequest()}),
				async        : false,
				contentType  : "application/json; charset=utf-8"
			});
		},

		// Check if there are long runnig process and him status
		longRunningProcessSystemMessaging : function () {

			$.ajax( {
				url      	 : sensus.settings.checkLongRunningProcess,
				dataType     : 'json',
				type         : "POST",
				data         : $.toJSON({'processRequest' : new processRequest()}),
				async        : false,
				contentType  : "application/json; charset=utf-8",
				success  	 : function(e) {

					var nLrpSize = e.countMonitoredProcess.count_monitored,
						processingSize = e.countMonitoredProcess.count_processing;

					$("#long-running-process-size-p").text(nLrpSize);
					$("#long-running-process-size-c").text(nLrpSize);

					sensus.pages.longrunningprocess.lrpSize = nLrpSize;

					if (nLrpSize > 0) {

						$("#system-messaging").show();

					} else if (nLrpSize == 0 && sensus.pages.longrunningprocess.isRniOn == "true") {

						$("#system-messaging").hide();

					}

					if (processingSize > 0) {

						$("#request-complete").hide();
						$("#request-processing").show();

					} else if (nLrpSize > 0) {

						$("#request-processing").hide();
						$("#request-complete").show();

					} else {

						$("#request-processing").hide();
						$("#request-complete").hide();

					}

				}

			});

		},

		// Send ajax action default
		sendAction : function(isMonitored) {

			var bIsError = false;
			sensus.util.page.startProgressBar(null, true);
			
			if($.isPlainObject(sensus.pages.longrunningprocess.data)){

				var obj = sensus.pages.longrunningprocess.data;
				var sRequest = '';
				for(var key in obj) {
				
					sRequest = key;

				}

				sensus.pages.longrunningprocess.data[sRequest].isMonitored = isMonitored;	

				data = $.toJSON(sensus.pages.longrunningprocess.data);
				sContentType = "application/json; charset=utf-8";
				sType = "POST";

			} else {

				data = sensus.pages.longrunningprocess.data + "&isMonitored=" + isMonitored;
				sContentType = null;
				sType = "GET";

			}

			$.ajax({
				url          : sensus.pages.longrunningprocess.actionUrlAdress,
				dataType     : 'json',
				contentType  : sContentType,
				type         : sType,
				data         : data,
				async        : false,
				success      : function(response) {

					if (response.result == sensus.constants.ajax.ok || response.result == "success" || response.operationSuccess) {

						var sMessage = response.messages || response.messageList;

						sensus.util.page.showMessage("messaging-main", sensus.locale.get("action.longRunningProcessDialog.confirm") + sensus.util.page.getMessageList(sMessage), "confirm");

						if(sensus.pages.longrunningprocess.bIsDetail) {
						
							var actionUrlAdress = sensus.pages.longrunningprocess.actionUrlAdress;

							sensus.pages.longrunningprocess.forceLightStatusLRPId = null;
							
							(function fnVerify(){
							

								var response = sensus.pages.longrunningprocess.checkUpdatedLightStatus();

								if(!response){

									setTimeout(fnVerify, 10000);

								}

							})();

							if (response.firstProcess) {

								sensus.pages.longrunningprocess.forceLightStatusLRPId = response.firstProcess.id;

							} else {

								sensus.pages.longrunningprocess.forceLightStatusLRPId = response.forceLightStatusLRPId;

							}

							
						} else {
						
							sensus.widgets.datatable.reloadTable(sensus.pages.smartpoint.smartpointTable);
							sensus.util.page.stopProgressBar(null, true);
						
						}

					} else {

						sensus.util.page.showMessage("messaging-main", sensus.locale.get("commons.pages.error") + sensus.util.page.getMessageList(response.messages), "error");
						sensus.util.page.stopProgressBar(null, true);
					}

					bIsError = true;

     			},

				error: function(e) {

					if (e.operationSuccess == false) {

						sensus.util.page.showMessage("messaging-main", sensus.locale.get("commons.pages.error"), "error");

					}

				}

			});


			var iCount = 0;
			(function fnVerify(){
				iCount += 1;

				if (iCount == 10) {

					if(!bIsError) {

						sensus.util.page.showMessage('messaging-main',sensus.locale.get('commons.pages.exportmsg'),'confirm')
						sensus.util.page.stopProgressBar(null, true);

					}

				} else if (iCount < 10) {

					setTimeout(fnVerify, 1000);

				}

			})();



		},

		/** Call Prompt Monitor Export */
	callPromptMonitorActionExport : function(hasBoxUpdating) {

		sensus.pages.longrunningprocess.promptMonitorActionUtil(hasBoxUpdating);

	},

	/** Call Monitor Action */
	callPromptMonitorAction : function(hasBoxUpdating) {

		sensus.pages.longrunningprocess.checkRni();

		if (sensus.pages.longrunningprocess.isRniOn == true) {

			sensus.pages.longrunningprocess.promptMonitorActionUtil(hasBoxUpdating);

		} else {

			sensus.util.page.stopProgressBar(null, true);
			sensus.util.page.showMessage("messaging-main", sensus.locale.get("action.longRunningProcessDialog.error") + sensus.locale.get("action.longRunningProcessDialog.rnioffline"), "error");

			if (sensus.pages.longrunningprocess.isClearSelect) {

				sensus.widgets.datatable.clearSelects.call(sensus.pages.longrunningprocess.lrpTable);

			}

			if (sensus.pages.longrunningprocess.reloadTable) {

				sensus.pages.smartpoint.fnReloadTable();

			}

		}

	},

	/** Prompt Monitor Action */
	promptMonitorActionUtil : function (hasBoxUpdating) {

		if (sensus.settings.monitor == 3) {

			sensus.util.actiondialog.launchActionDialog("longRunningProcessDialog", sensus.pages.longrunningprocess.dialogSettingsProcess["longRunningProcessDialog"]);

		} else if (sensus.settings.monitor == 2) {

			if (sensus.pages.longrunningprocess.actionUrlAdress == "updateSchedule") {

				sensus.pages.schedule.isUpdateMonitored = true;
				sensus.pages.schedule.ajaxCall(sensus.settings.updateScheduleUrl);

			} else if (sensus.pages.longrunningprocess.bIsDetail) {
				sensus.util.ajaxaction.sendActionUpdatedStatus(false);

			} else {

				sensus.pages.longrunningprocess.sendAction(false);

			}

		} else if (sensus.settings.monitor == 1) {

			if (hasBoxUpdating) {

				if (sensus.pages.longrunningprocess.actionUrlAdress == "updateSchedule") {

					sensus.pages.schedule.isUpdateMonitored = true;
					sensus.pages.schedule.ajaxCall(sensus.settings.updateScheduleUrl);

				} else {

					sensus.pages.longrunningprocess.sendAction(true)

				}

			} else {

				if (sensus.pages.longrunningprocess.actionUrlAdress == "updateSchedule") {

					sensus.pages.schedule.isUpdateMonitored = true;
					sensus.pages.schedule.ajaxCall(sensus.settings.updateScheduleUrl);

				} else {

					sensus.pages.longrunningprocess.sendAction(true);

				}

			}

		}

	},
	//Call check update status loop
	checkUpdatedLightStatus : function() {

		var response = false;
		var aSelectedIds  = [];
		aSelectedIds.push({'id':sensus.pages.longrunningprocess.forceLightStatusLRPId});
		$.ajax( {
			url      	 : sensus.settings.checkUpdatedLight,
			dataType     : 'json',
			type         : "POST",
			data         : $.toJSON({'processRequest' : new processRequest(aSelectedIds)}),
			async        : false,
			contentType  : "application/json; charset=utf-8",
			
			success  : function(e) {
				
				if(e.lights.length > 0 ) {
				
					if($.isFunction(sensus.pages.longrunningprocess.fnCallBack)){
				
						sensus.pages.longrunningprocess.fnCallBack();
						sensus.util.page.stopProgressBar(null, true);
					
					}
					
				response = true;
				}

			}

		});
		return response;
	},	

};
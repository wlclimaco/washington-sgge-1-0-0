/**
 * @namespace sensus.pages.longrunningprocess
 * @description The create-main namespace for Long Running Processes.
 * @fileoverview Defines the core functionality of the Long Running Processes.
 * @author Vinícius Silva
 */
//head(function() {
	sensus.pages.longrunningprocess = {

		lrpSize : 0,

		isRniOn : false,

		/**
		 * This field is used to know whether the action will be send by AJAX or by form submit (In case of upload file for example)
		 */
		fnFormField : undefined,

		fnCheckRniCallback : function(oResponse) {

			var $rniOffline = $("#rni-offline");

			if (oResponse.linkStatus) {

				$("#rni-offline").hide();

				$('.bubble').RemoveBubblePopup();

				sensus.pages.longrunningprocess.isRniOn = true;

			} else {

				$("#system-messaging").show();
				$("#rni-offline").show();

				sensus.pages.longrunningprocess.isRniOn = false;
			}
		},

		fnCallbackMonitorUpload : null,

		monitorUpload : function(fnCallback) {

			var longrunningprocess = sensus.pages.longrunningprocess;
			var dialog = longrunningprocess.dialogSettingsProcess;
			var bMonitored;

			switch (sensus.settings.monitor) {
			case "1" :

				bMonitored = true;
				break;

			case "2" :

				bMonitored = false;
				break;

			case "3" :

				longrunningprocess.fnCallbackMonitorUpload = function () {

					if (fnCallback) {

						fnCallback(dialog.longRunningProcessDialog.isMonitor);
					}
				}

				sensus.util.actiondialog.launchActionDialog("longRunningProcessDialog", dialog.longRunningProcessDialog);
				break;

			default :
				break;
			}

			// Whether has a callback function and monitor is '1' or '2'
			if (fnCallback && sensus.settings.monitor != "3") {
				fnCallback(bMonitored);
			}
		},

		monitor : function(url, data, sMessage, fnCallback) {

			sensus.util.page.startProgressBar();
			sensus.pages.longrunningprocess.monitorUrl = url;
			sensus.pages.longrunningprocess.monitorData = data;

			var sDivmessaging = "messaging-main",
				iSize = $('.messaging-smartpoint-detail').length;

			if(iSize){
				sDivmessaging = "messaging-smartpoint-detail";
			}


			if(!sMessage){
				sMessage = sensus.locale.get("action.longRunningProcessDialog.demand.reset.confirm");
			}

			$.when(this.checkRniAjax()).then(function(e) {

				if (($.isArray(e.stringResult) && (e.stringResult[0] != "false")) || url == "process/ajax.updateMonitorProcess.action") {

					if (sensus.settings.monitor == 3){

						sensus.pages.longrunningprocess.dialogSettingsProcess.sMessage = sMessage;
						sensus.pages.longrunningprocess.dialogSettingsProcess.fnCallback = fnCallback;
						sensus.pages.longrunningprocess.dialogSettingsProcess.sDivmessaging = sDivmessaging;
						sensus.util.actiondialog.launchActionDialog("longRunningProcessDialog", sensus.pages.longrunningprocess.dialogSettingsProcess["longRunningProcessDialog"]);

					} else {

						sensus.pages.longrunningprocess.sendAction(sensus.settings.monitor == 1 ? true : false, sMessage, sDivmessaging, fnCallback)

					}

				} else {

					sensus.util.page.stopProgressBar();
					sensus.util.page.showMessage(sDivmessaging, sensus.locale.get("commons.pages.error") + sensus.locale.get("action.longRunningProcessDialog.rnioffline"), "error");
					if (sensus.pages.longrunningprocess.isClearSelect) {

						sensus.util.selection.clearSelects();

					}

				}
			});
		},

		/**
		 * Check if there are Processes Monitored
		 */
		longRunningProcessSystemMessaging : function () {

			$.ajax({
				url : sensus.util.process.api.fetchCountMonitored,
				type : "POST",
				async : true,
				contentType : "application/json; charset=utf-8",
				success : function(oProcessResponse) {

					var $systemMessaging = $("#system-messaging");
					var $requestProcessing = $("#request-processing");
					var $requestComplete = $("#request-complete");

					if (!oProcessResponse.operationSuccess) {
						return;
					}

					// Set Current time
					if (!$.ajaxValidator.fnIsNullOrUndefined(oProcessResponse.processResponseTime)) {
						sensus.settings.currentTime = $.date.fnGetDate(oProcessResponse.processResponseTime, {bUserTZ : false});
					}

					var lrpSize = oProcessResponse.countMonitoredProcess["count_monitored"] || 0;
					var processingSize = oProcessResponse.countMonitoredProcess["count_processing"] || 0;

					sensus.pages.longrunningprocess.lrpSize = lrpSize;

					$("#long-running-process-size-p").text(lrpSize);
					$("#long-running-process-size-c").text(lrpSize);

					// show/hide system messaging bar
					if (lrpSize > 0) {

						$systemMessaging.show();

						// show or hide complete or processing box
						if (processingSize > 0) {
							// in processing
							$requestProcessing.show();
							$requestComplete.hide();

						} else {
							// all completed
							$requestComplete.show();
							$requestProcessing.hide();
						}

					} else if (sensus.pages.longrunningprocess.isRniOn) {

						// RNI ON and NO Processes Monitored case
						$systemMessaging.hide();

					} else {

						// RNI Off case
						$systemMessaging.show();
						$requestProcessing.hide();
						$requestComplete.hide();
					}
				}
			});
		},

		/**
		 * Send AJAX action default
		 */
		sendAction : function(isMonitored, sMessage, sDivmessaging, fnCallback) {

			if (sensus.pages.longrunningprocess.fnFormField != undefined) {
				sensus.pages.longrunningprocess.fnFormField(isMonitored);
			} else {
				$.ajax({
					url : sensus.pages.longrunningprocess.monitorUrl,
					type : 'POST',
					async : false,
					data : sensus.pages.longrunningprocess.monitorData + "&isMonitored=" + isMonitored,
					success: function(data) {

						/**
						 * Not is possible store in a variable because of callBack Function
						 */
						$('#'+sDivmessaging).hide();

						if ($.isFunction(fnCallback)) {
							fnCallback(data);
						}

						var oMessage = $('#'+sDivmessaging);
						var $selectNone = $('.select-none');

						// TODO - Remove first condition when all response came directly from BE side.
						if (data && (data.result == sensus.constants.ajax.ok || data.result == "success")) {

							sensus.util.page.showMessage(sDivmessaging, sMessage + sensus.util.page.getMessageList(data.messages), "confirm");
							$selectNone.click();
						} else if (data && data.operationSuccess) {
							sensus.util.page.showMessage(sDivmessaging, sMessage + sensus.util.page.getMessageList(data.messageList), "confirm");
							$selectNone.click();
						} else {

							var messages = data.messages || data.messageList;
							var responseMessage = sensus.util.page.getMessageList(messages);

							if (!responseMessage) {
								responseMessage = "";
							}

							if(!oMessage.is(':visible')){
								sensus.util.page.showMessage(sDivmessaging, sensus.locale.get("commons.pages.error") + responseMessage, "error");
							}

						}
						sensus.pages.longrunningprocess.longRunningProcessSystemMessaging();
						sensus.util.page.stopProgressBar();
					},
					error: function(e) {
						if (e.result == "FAIL") {

							sensus.util.page.showMessage(sDivmessaging, sensus.locale.get("commons.pages.error"), "error");

						}
						sensus.util.page.stopProgressBar();
					}
				});
			}

			// Reset object
			sensus.pages.longrunningprocess.fnFormField = undefined;
		},

		/**
		 * Create Process Request
		 *
		 * @param [Array], the processes id
		 * @param [Object], the processes request parameters
		 *
		 * @return [Object], the process request populated by parameters
		 */
		fnCreateProcessRequest : function(aProcessId, oProcessRequestParam) {

			var oParam = {};
			var aProcessList = [];

			for (var i = 0; i < aProcessId.length; i++) {

				oProcessRequestParam.id = aProcessId[i].id;

				aProcessList.push(new Process(oProcessRequestParam));
			}

			oParam.processList = aProcessList;

			return new ProcessRequest(oParam);
		}
	};
//});
//head(function() {
	(function($) {

		var oMonitorDeferred = $.Deferred();

		/**
		 * Get the Response Object from BE
		 * @param {String} sUrl
		 *					The url from the ajax call.
		 * @param {Object} oRequest
		 *					The Request Object
		 * @param {Boolean} bAsync
		 *					Whether the Ajax call wil be sync or async.
		 * @returns {Object}
		 *					The Response Object came from BE.
		 *
		 */
		function fnGetResponse (sUrl, oRequest, bAsync, oDeferred) {

			var oResponse = null;

			if (fnIsNullOrUndefined(bAsync)) {
				bAsync = true;
			}

			if (fnIsNullOrUndefined(oRequest)) {
				oRequest = {};
			}

			$.ajax({
				dataType     : 'json',
				type         : "POST",
				url          : sUrl,
				contentType  : "application/json; charset=utf-8",
				data         : $.toJSON(oRequest),
				async		 : bAsync,
				success      : function (oData) {
					oResponse = oData;
					if (!fnIsNullOrUndefined(oDeferred)) {
						oDeferred.resolve(oResponse);
					}
				},
				error 		 : function (error) {
					console.log(error);
				}
			});

			return oResponse;
		}

		/**
		 * Validate if a property is null or undefined.
		 * @param {Object}   - The property to be validated.
		 * @return {Boolean} - Whether the property is valid or not.
		 */
		function fnIsNullOrUndefined (oProperty) {
			if (oProperty === null || oProperty === undefined) {
				return true;
			}

			return false;
		}

		/**
		 * Validation to check if is an Array and if it has Values
		 * @ returns {Boolean}
		 */
		function fnValidArray (oProperty) {
			if (!fnIsNullOrUndefined(oProperty) && $.isArray(oProperty) && oProperty.length) {
				return true;
			}

			return false;
		}

		/**
		 * Get the message came at the response.
		 * @param {Object} oResponse - The Response Object.
		 * @param {String} sDefaultMessage - The default value to be set if response does not have Message
		 */
		function fnGetResponseMessage (oResponse, sDefaultMessage) {

			var aMessageList = oResponse.messageList;
			var oMessage;

			if (fnValidArray(aMessageList)) {

				oMessage = aMessageList[0];

				if (oMessage.text) {

					return oMessage.text;
				}

				return oMessage.messageInfo.code;
			}

			return sDefaultMessage;
		}

		/**
		 * Valid Response Object came from BE.
		 * @param {Object} oResponse
		 *  				The Object returned from BE.
		 * @returns {Object}
		 *					An Object that contains whether the response is valid or not and the error message.
		 */
		function fnValidResponse (oResponse) {
			var oResponseValidation = {
				bIsValid : true,
				sErrorMessage : ""
			};

			if (fnIsNullOrUndefined(oResponse)) {
				oResponseValidation.bIsValid = false;
				oResponseValidation.sErrorMessage = sensus.locale.get("commons.pages.error");
				return oResponseValidation;
			}

			if (oResponse.operationSuccess == false) {
				oResponseValidation.bIsValid = false;
				oResponseValidation.sErrorMessage = fnGetResponseMessage(oResponse, sensus.locale.get("commons.pages.error"));

				return oResponseValidation;
			}

			return oResponseValidation;
		}

		/**
		 * Check if RNI is On and show a error message if not.
		 * @param {Boolean} bAsyncCall - Whether is async ajax call or not.
		 */
		function fnIsRniOn (bAsyncCall, fnCallback) {

			var oDeferred = $.Deferred();
			var oRniResponse;

			// Set Async false if no parameter is specified
			if (fnIsNullOrUndefined(bAsyncCall)) {
				bAsyncCall = false;
			}

			fnGetResponse("api/process/checkRNIStatus", {}, bAsyncCall, oDeferred);

			$.when(oDeferred).then(function(oResponse) {
				if ($.isFunction(fnCallback)) {
					fnCallback(oResponse);
				}
				oRniResponse = oResponse.linkStatus;
			});

			return oRniResponse;
		}


		/**
		 * Set whether the action is monitor or not at the request.
		 * @param {Object}  oRequest - The request Object that will be changed.
		 * @param {Boolean} isMonitored - The monitored field to be added at the request..
		 * @returns {Object} - The new Request Object.
		 */
		function fnSetMonitor (oRequest, isMonitored) {

			var i;
			var length;

			// Set isMonitored field at the request
			if (oRequest) {

				if (oRequest.parameters) {

					oRequest.parameters.isMonitored = isMonitored;
				}

				if (oRequest.processList && oRequest.processList.length) {

					for (i = 0, length = oRequest.processList.length; i < length; i = i + 1) {

						oRequest.processList[i].monitoredInstance = isMonitored;
					}
				}
			}
		}

		/**
		 * Event click for Monitor/Dismiss Dialog.
		 * This function check whether is monitor or not and save the preferences if remember action is checked.
		 * Also it resolve the Deferred used at the prompt dialog.
		 *
		 * @param {Object} - The element.
		 */
		function fnMonitorDialogEvent (e) {

			var sMonitor;
			var bMonitor;
			var longrunningprocess = sensus.pages.longrunningprocess;

			e.preventDefault();

			if ($(this).hasClass("dismiss")) {

				sMonitor = "2";
				bMonitor = false;

			} else {

				sMonitor = "1";
				bMonitor = true;
			}

			longrunningprocess.dialogSettingsProcess.longRunningProcessDialog.isMonitor = bMonitor;

			// If checked, don't show Monitor Dialog again
			if ($(".highlight small input").is(":checked")) {

				// Update User Settings
				sensus.settings.monitor = sMonitor;

				// Save User Settings
				var properties = [new Property({propertyName : "MONITOR_REQUEST", propertyValue : sMonitor})];

				var oRequest = new PropertyRequest({properties : properties});

				fnDoCall("api/settings/upsert", oRequest, true);

			}

			$("#dialog-monitor-dissmiss").dialog("close");

			oMonitorDeferred.resolve();

			if (longrunningprocess.fnCallbackMonitorUpload) {

				longrunningprocess.fnCallbackMonitorUpload();
				longrunningprocess.fnCallbackMonitorUpload = null;
			}
		}

		/**
		 * Check if Response is back or not.
		 * @param {Object} - The Request Object to send at the check ajax call
		 * @param {oWaitResponseDeferred} - The deferred object that will be resolved when response is back.
		 * @param {String} - sSuccessMessage - The Success Message.
		 * @returns {Boolean} - Whether the response is back.
		 */
		function fnCheckResponseStatus (oRequest, oWaitResponseDeferred, sSuccessMessage) {

			(function fnVerify(){

				var oResponse = $.ajaxValidator.fnDoCall("smartpoint/checkUpdatedLight.action", oRequest, false, null, sSuccessMessage, true);

				if(fnValidArray(oResponse.lights)){
					// Stop Progress bar
					sensus.util.page.stopProgressBar(null, false);
					oWaitResponseDeferred.resolve();
				} else {
					setTimeout(fnVerify, 10000);
				}

			})();

		}

		/**
		 * Block the application askig for response.
		 * @param {Object} - The Response Object came from the first action
		 * @param {Function} - The Callback functon send to the action originally.
		 * @param {String} - The Success message.
		 */
		function fnWaitResponse (oResponse, fnCallback, sSuccessMessage) {
			var iProcessId = 0;
			var aSelectedIds  = [];
			var oRequest = null;
			var oWaitResponseDeferred = $.Deferred();

			if (!fnIsNullOrUndefined(oResponse.firstProcess)) {

				iProcessId = oResponse.firstProcess.id;

			} else {

				iProcessId = oResponse.forceLightStatusLRPId;

			}

			aSelectedIds.push({'id' : iProcessId});
			oRequest = {'processRequest' : new ProcessRequest(aSelectedIds)};

			$.when(oWaitResponseDeferred).then(function () {
				if($.isFunction(fnCallback)){

					if (!fnIsNullOrUndefined(sSuccessMessage)) {

						sensus.util.page.showMessage("messaging-main", sSuccessMessage, "confirm");

					}

					fnCallback(oResponse);
				}
			});

			fnCheckResponseStatus(oRequest, oWaitResponseDeferred, null);
		}

		/**
		 * Send ajax calls to get the response and validate the response showing the error message it is invalid.
		 * @param {String}    sUrl         - The URL from the ajax call
		 * @param {Object}    oRequest     - The Request Object
		 * @param {Boolean}   bAsync       - Whether the ajax call will be sync or async.
		 * @param {Function}  fnCallback   - The callback function to be executed after the ajax call
		 * @param {String}    sSuccessMessage   - The Success Message
		 * @param {Boolean}   bBlockScreen - Block Screen if true.
		 * @param {Boolean}   bResponseExpected - Ignore response to show success message.
		 * @param {Function}  fnErrorCallback - The error callback function to be executed after the ajax call.
		 * @param {String} 	  sMessagindId - The div id to show the message
		 * @returns {Object} - The Response Object.
		 */
		function fnDoCall (sUrl, oRequest, bAsync, fnCallback, sSuccessMessage, bBlockScreen, bHideProgressBar, bResponseExpected, fnErrorCallback, sMessagindId) {

			// Start Progress Bar
			if(!bHideProgressBar){

				sensus.util.page.startProgressBar();

			}

			if (fnIsNullOrUndefined(bResponseExpected)) {
				bResponseExpected = true;
			}

			// Set default message div id if no one is specified.
			if (fnIsNullOrUndefined(sMessagindId) && sSuccessMessage) {
				sMessagindId = "messaging-main";
			}

			var oResponse = fnGetResponse(sUrl, oRequest, bAsync);
			var oValidResponse = fnValidResponse(oResponse);
			var sResponseSuccessMessage = null;

			if (!bResponseExpected) {
				return oResponse;
			}

			// Validate oResponse
			if (!oValidResponse.bIsValid) {

				// Show Error Message
				sensus.util.page.showMessage(sMessagindId, oValidResponse.sErrorMessage, "error");
				// Stop Progress bar
				sensus.util.page.stopProgressBar(null, false);

				// Execute Error Callback function if it exists
				if ($.isFunction(fnErrorCallback)) {
					try {
						fnErrorCallback(oResponse, oRequest)
					} catch (e) {
						// Show Error
						console.log(e);
					}
				}

				return;
			}

			sResponseSuccessMessage = fnGetResponseMessage(oResponse, null);

			// Show Success Message if it exists
			if (!fnIsNullOrUndefined(sSuccessMessage)) {
				sensus.util.page.showMessage(sMessagindId, sSuccessMessage, "confirm");
			}

			if (!fnIsNullOrUndefined(sResponseSuccessMessage)) {
				sensus.util.page.showMessage(sMessagindId, sResponseSuccessMessage, "confirm");
			}

			// Execute Callback function if it exists
			if ($.isFunction(fnCallback)) {
				try {
					fnCallback(oResponse, oRequest);
				} catch (e) {
					// Show Error
					console.log(e);
				}
			}

			if (fnIsNullOrUndefined(bBlockScreen) || !bBlockScreen) {
				// Stop Progress bar
				sensus.util.page.stopProgressBar(null, false);
			}

			return oResponse;
		}

		/**
		 * Send ajax calls for monitored actions, checking if RNI is online and getting whether will monitor or not
		 *
		 * @param {String}		sUrl				 - The URL from the ajax call
		 * @param {Object}		oRequest			 - The Request Object
		 * @param {Boolean}		bAsync				 - Whether the ajax call will be sync or async
		 * @param {Function}	fnCallback 			 - The callback function to be executed after the ajax call
		 * @param {String}		sSuccessMessage		 - The Success Message
		 * @param {String}		sMessagindId		 - The div id to show the message
		 * @param {Boolean}		bCheckResponseReturn - Boolean if will hold application waiting for response
		 * @param {String}		sMessagindId		 - The div id to show the message
		 */
		function fnMonitor (sUrl, oRequest, bAsync, fnCallback, sSuccessMessage, bCheckResponseReturn, sMessagindId) {

			// Start Progress Bar
			sensus.util.page.startProgressBar();

			// Used to pass to fnDoCall if will block application
			var bBlockProgressBar = false;

			// Check RNI
			if (!fnIsRniOn(false)) {
				// Stop Progress bar
				sensus.util.page.stopProgressBar(null, false);
				return;
			}

			if (!fnIsNullOrUndefined(bCheckResponseReturn) && bCheckResponseReturn) {
				var fnActionCallback = fnCallback;
				bBlockProgressBar = true;
				fnCallback = function (oResponse) {
					fnWaitResponse(oResponse, fnActionCallback, sSuccessMessage);
				};
			}

			if(bBlockProgressBar){

				sSuccessMessage = null;

			}

			switch (sensus.settings.monitor) {
				case "1" :
					fnSetMonitor(oRequest, true);
					// Ajax call for the action
					fnDoCall(sUrl, oRequest, bAsync, fnCallback, sSuccessMessage, bBlockProgressBar, null, null, null, sMessagindId);
					break;
				case "2" :
					fnSetMonitor(oRequest, false);
					// Ajax call for the action
					fnDoCall(sUrl, oRequest, bAsync, fnCallback, sSuccessMessage, bBlockProgressBar, null, null, null, sMessagindId);
					break;
				case "3" :
					// Open dialog
					sensus.util.actiondialog.launchActionDialog("longRunningProcessDialog", sensus.pages.longrunningprocess.dialogSettingsProcess["longRunningProcessDialog"]);
					$.when(oMonitorDeferred).then(function () {

						fnSetMonitor(oRequest, sensus.pages.longrunningprocess.dialogSettingsProcess.longRunningProcessDialog.isMonitor);

						// Ajax call for the action
						fnDoCall(sUrl, oRequest, bAsync, fnCallback, sSuccessMessage, bBlockProgressBar, null, null, null, sMessagindId);
						sensus.pages.longrunningprocess.dialogSettingsProcess.longRunningProcessDialog.isMonitor = null;
						oMonitorDeferred = $.Deferred();
					});
					break;
				default :
					break;
			}
		}


		$.ajaxValidator = (function()  {

			return {
				fnDoCall			 : fnDoCall,
				fnMonitor			 : fnMonitor,
				fnIsNullOrUndefined  : fnIsNullOrUndefined,
				fnValidArray		 : fnValidArray,
				fnValidResponse		 : fnValidResponse,
				fnIsRniOn			 : fnIsRniOn,
				fnMonitorDialogEvent : fnMonitorDialogEvent
			};

		})();

	}(jQuery));
//}); head
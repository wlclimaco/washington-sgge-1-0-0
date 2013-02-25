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
	function fnGetResponse (sUrl, oRequest, bAsync) {

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
			data         : oRequest,
			async		 : bAsync,
			success      : function (oData) {
				oResponse = oData;
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
	 * @param {Object} oResponse - The Resonse Object.
	 * @param {String} sDefaultMessage - The default value to be set if response does not have Message
	 */
	function fnGetResponseMessage (oResponse, sDefaultMessage) {
		if (fnValidArray(oResponse.messageList)) {
			return oResponse.messageList[0].text;
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
			oResponseValidation.sErrorMessage = $.sc.locale("commons.pages.error");
			return oResponseValidation;
		}

		if (oResponse.operationSuccess == false) {
			oResponseValidation.bIsValid = false;
			oResponseValidation.sErrorMessage = fnGetResponseMessage(oResponse, $.sc.locale("commons.pages.error"));

			return oResponseValidation;
		}

		return oResponseValidation;
	}

	/**
	 * Check if Rni is On and show a error message if not.
	 * @param {Boolean} bShowErrorMessage - If it will show message when rni is off
	 */
	function fnIsRniOn (bShowErrorMessage) {
		var oRniResponse = fnGetResponse("api/process/checkRNIStatus", new processRequest(), false);
		var oValidResponse = fnValidResponse(oRniResponse);

		if (!oValidResponse.bIsValid && bShowErrorMessage) {
			sensus.util.page.showMessage("messaging-main", $.sc.locale("commons.pages.error") + $.sc.locale("action.longRunningProcessDialog.rnioffline"), "error");
			return false;
		}

		return true;
	}


	/**
	 * Set whether the action is monitor or not at the request.
	 * @param {Object}  oRequest - The request Object that will be changed.
	 * @param {Boolean} isMonitored - The monitored field to be added at the request..
	 * @returns {Object} - The new Request Object.
	 */
	function fnSetMonitor (oRequest, isMonitored) {
		// Set isMonitored field at the request
		for (var key in oRequest) {
			//oRequest[key].isMonitored = isMonitored;
			oRequest.isMonitored = isMonitored;
		}

		return oRequest;

	}

	/**
	 * Event click for Monitor/Dismiss Dialog.
	 * This function check whether is monitor or not and save the preferences if remember action is checked.
	 * Also it resolve the Deferred used at the prompt dialog.
	 *
	 * @param {Object} - The element.
	 */
	function fnMonitorDialogEvent (e) {
		e.preventDefault();

		var iMonitor;
		var bMonitor;

		if ($(this).hasClass("dismiss")) {
			iMonitor = "2";
			bMonitor = false;
		} else {
			iMonitor = "1";
			bMonitor = true;
		}

		sensus.pages.longrunningprocess.dialogSettingsProcess.longRunningProcessDialog.iMonitor = bMonitor;

		if ($('.highlight small input').is(':checked')) {
			sensus.settings.monitor = iMonitor;

			sensus.util.page.fnSavePropertyProfile( { "MONITOR_REQUEST" : iMonitor } );
		}

		$("#monitor-dialog").dialog('close');

		oMonitorDeferred.resolve();

	}

	/**
	 * Check if Response is back or not.
	 * @param {Object} - The Request Object to send at the check ajax call
	 * @param {oWaitResponseDeferred} - The deferred object tha will be resolved when response is back.
	 * @param {String} - sSuccessMessage - The Success Message.
	 * @returns {Boolean} - Whether the response is back.
	 */
	function fnCheckResponseStatus (oRequest, oWaitResponseDeferred, sSuccessMessage) {

		(function fnVerify(){

			var oResponse = $.sc.getJson("api/light/fetch", oRequest, false, null, sSuccessMessage, true);

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


		oRequest = {"id" : iProcessId, "action" : "table"};

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
	 * @param {String}   sUrl         - The url from the ajax call
	 * @param {Object}   oRequest     - The Request Object
	 * @param {Boolean}  bAsync       - Whether the Ajax call wil be sync or async.
	 * @param {Function} fnCallback   - The callback funtion to be executed after the ajax call
	 * @param {Boolean}  bBlockScreen - Block Screen if true.
	 * @returns {Object} - The Response Object.
	 */
	function fnDoCall (sUrl, oRequest, bAsync, fnCallback, sSuccessMessage, bBlockScreen, bHideProgressBar) {

		// Start Progress Bar
		if(!bHideProgressBar){

			sensus.util.page.startProgressBar(null, false);

		}

		var oResponse = fnGetResponse(sUrl, oRequest, bAsync);
		var oValidResponse = fnValidResponse(oResponse);
		var sResponseSuccessMessage = null;

		// Validate oResponse
		if (!oValidResponse.bIsValid) {

			// Show Error Message
			sensus.util.page.showMessage("messaging-main", oValidResponse.sErrorMessage, "error");
			// Stop Progress bar
			sensus.util.page.stopProgressBar(null, false);

			return;
		}

		sResponseSuccessMessage = fnGetResponseMessage(oResponse, null);

		// Show Success Message if it exists
		if (!fnIsNullOrUndefined(sSuccessMessage)) {
			sensus.util.page.showMessage("messaging-main", sSuccessMessage, "confirm");
		}

		if (!fnIsNullOrUndefined(sResponseSuccessMessage)) {
			sensus.util.page.showMessage("messaging-main", sResponseSuccessMessage, "confirm");
		}

		// Execute Callback function if it exists
		if ($.isFunction(fnCallback)) {
			try {
				fnCallback(oResponse);
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
	 * Send ajax calls for monitored actions, checking if rni is online and geting whether will monitor or not.
	 * @param {String}   sUrl       - The url from the ajax call
	 * @param {Object}   oRequest   - The Request Object
	 * @param {Boolean}  bAsync     - Whether the Ajax call wil be sync or async.
	 * @param {Function} fnCallback - The callback funtion to be executed after the ajax call
	 * @param {Boolean}   bCheckResponseReturn - Boolean if will hold application waiting for response.
	 */
	function fnMonitor (sUrl, oRequest, bAsync, fnCallback, sSuccessMessage, bCheckResponseReturn) {

		// Start Progress Bar
		$.sc.startProgressBar(null, false);

		// Used to pass to fnDoCall if will block application
		var bBlockProgressBar = false;

		// Check RNI
		if (!fnIsRniOn(true)) {
			// Stop Progress bar
			$.sc.stopProgressBar(null, false);
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

		switch (sensus.settings.MONITOR_REQUEST) {
			case "1" :
				oRequest = fnSetMonitor(oRequest, true);
				// Ajax call for the action
				$.sc.getJson(sUrl, oRequest, bAsync, fnCallback, sSuccessMessage, bBlockProgressBar);
				break;
			case "2" :
				oRequest = fnSetMonitor(oRequest, false);
				// Ajax call for the action
				$.sc.getJson(sUrl, oRequest, bAsync, fnCallback, sSuccessMessage, bBlockProgressBar);
				break;
			case "3" :
				// Open dialog
				$.sc.launchActionDialog("longRunningProcessDialog", sensus.pages.longrunningprocess.dialogSettingsProcess["longRunningProcessDialog"], "monitor-dialog");
				$.when(oMonitorDeferred).then(function () {

					oRequest = fnSetMonitor(oRequest, sensus.pages.longrunningprocess.dialogSettingsProcess.longRunningProcessDialog.iMonitor);
					// Ajax call for the action

					$.sc.getJson(sUrl, oRequest, bAsync, fnCallback, sSuccessMessage, bBlockProgressBar);
					sensus.pages.longrunningprocess.dialogSettingsProcess.longRunningProcessDialog.iMonitor = null;
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
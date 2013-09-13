/*
 * jQuery Sensus Commons v0.1
 * http://www.qatglobal.com
 *
 * Copyright (c) 2009-2010 Qat Global
 *
 * Authors : Lucas Oliveira, Raphael Constantino
 *
 * Date: 2012-11-05 14:22:12 +0300 (Wed, 04 May 2011)
 */
(function ($) {

    $.sc = (function () {

		/*
		 * Initialize language settings.
		 */
		/*jQuery.i18n.properties( {
			name : 'messages',
			path : "api/settings/fetchmessages/",
			mode : 'map',
			encoding : 'ISO-8859-1',
			language : sensus.settings.language
		});*/

		var oMonitorDeferred = $.Deferred();
		var oMainMenu = $("#sensus-menu");
		var sAjaxUrl = null;

		/**
		 * Util functions for the system
		 */
		var _util = (function () {

			/**
			 * Validate if a property is null or undefined.
			 * @param {Object}   - The property to be validated.
			 * @return {Boolean} - Whether the property is valid or not.
			 */
			var _isNullOrUndefined = function (oProperty) {

				if (oProperty === null || oProperty === undefined) {

					return true;

				}

				return false;

			};

			/**
			 * Validation to check if is an Array and if it has Values
			 * @ returns {Boolean}
			 */
			var _isValidArray = function(oProperty) {

				if (!$.sc.isNullOrUndefined(oProperty) && $.isArray(oProperty) && oProperty.length) {
					return true;
				}

				return false;
			};

			/**
			 * Wrap the retrieval of localized strings in case we want to change the
			 * underlying implementation in the future.
			 *
			 * @param args
			 *            if only one argument is provided, return the lookup result for
			 *            this argument. If more then one argument is provide, use the first
			 *            value as lookup key and the rest of the arguments as data to fill
			 *            in the returned string.
			 * @return localized messsage
			 */
			var _locale = function() {

				if (arguments.length == 0) {

					return "";
				}

				if (arguments.length == 1) {

					return jQuery.i18n.prop(arguments[0]);

				}

				if (typeof (arguments[1]) === "string") {

					var args = [];

					for ( var i = 1; i < arguments.length; i++) {
						args.push(arguments[i]);
					}

					return jQuery.i18n.prop(arguments[0], args);
				}

				return jQuery.i18n.prop(arguments[0], arguments[1]);
			}

			/**
			 * Launches the action dialog with the action id and the corresponding
			 * dialog settings. This function expects the dialog to be launched from the
			 * "#action_dialog" element. For an example of dialog settings, see
			 * pages/smartpoint_actions.js.
			 *
			 * @param sActionId
			 *            the string code for the action. This is only needed to display
			 *            a warning note for unimplemented actions.
			 * @param the
			 *            settings for the particular action dialog. These settings are
			 *            typically defined in the main namespace for the containing
			 *            page.
			 */
			var _launchActionDialog = function(sActionId, oDialogSettings, sDialogId) {

				if (!oDialogSettings) {

					alert($.sc.locale("widget.action.unimplemented", sActionId));

					return;
				}


				oDialogSettings.actionId = sActionId;

				if(oDialogSettings.width  ==  null) {

					oDialogSettings.width = 500;

				}

				$.sc.clearFormElements(oDialogSettings.form);

				var actionDialogId = "#action-dialog";

				if(sActionId == "tableDialog") {

					actionDialogId = "#action-dialog-lrp";

				}

				if(sActionId == "longRunningProcessRemove") {

					actionDialogId = "#remove-lrp";

				} else if(sActionId == "longRunningProcessAbort") {

					actionDialogId = "#abort-lrp";

				}

				if(!$.sc.isNullOrUndefined(sDialogId)){

					actionDialogId = "#"+sDialogId;

				}

				$(actionDialogId).dialog( "close" );

				var actionDialog = $(actionDialogId).dialog( {
					autoOpen     : false,
					title        : oDialogSettings.title,
					close        : oDialogSettings.close,
					beforeClose  : oDialogSettings.beforeClose,
					width        : function() {

						var width;

						if (oDialogSettings.width != undefined) {

							width = oDialogSettings.width;

						} else {

							width = 500;

						}

						return width;
					},
					minheight : function() {

						var minheight;

						if (oDialogSettings.minheight != undefined) {

							minheight = oDialogSettings.minheight;

						} else {

							minheight = 150;

						}

						return minheight;
					},
					modal        : true,
					buttons      : oDialogSettings.buttons,
					dialogClass  : 'action-dialog',
					resizable    : false
				});

				oDialogSettings.action(actionDialog);
			};

			/**
			 * Initializes the action dialog (hides the element if visible).s
			 */
			var _initActionDialog = function() {
				$('.action-dialog').hide();
			}

			return {
				_isNullOrUndefined : _isNullOrUndefined,
				_isValidArray      : _isValidArray,
				_locale			   : _locale,
				_launchActionDialog: _launchActionDialog
			}

		})();

		var _progressBar = (function () {

			var oLoading = $('#loading');
			var oProgressbar = $("#progress-bar");

			var _startProgressBar = function(sMessage,bTable){
				//$("body").append('<div class="ui-widget-overlay" style="width: 1903px; height: 825px; z-index: 1001;"></div>');
				$('.formError').remove();

				if (bTable == undefined) {
					bTable = false;
				}

				if (oLoading.data("bTable") == undefined){

					if (!oLoading.dialog('isOpen')) {

						oLoading.data("bTable", bTable);

						if (sMessage == null) {

							$('h5', oLoading).text($.sc.locale("commons.pages.updating"));

						} else {

							$('h5', oLoading).text($.sc.locale(sMessage));

						}

						oLoading.dialog({modal: true});
						oLoading.dialog({closeOnEscape: false});
						oProgressbar.progressbar( {
							value : 1
						});

						(function progress() {

							var value = oProgressbar.progressbar('value');
							oProgressbar.progressbar('value', value + 1);

							if (value < 100) {

								setTimeout(progress, 200);

								if (value == 99){

									oProgressbar.progressbar( {

										value : 1

									});

								}

							}

						}());

					}

				}

			};

			var _stopProgressBar = function(recordCount, bTable) {

				if (bTable == undefined){

					bTable = false;

				}

				if (oLoading.data("bTable") == bTable) {

					oProgressbar.progressbar('value', 100);
					setTimeout(function () {

						oLoading.dialog("close");
						$('.fullscreen').hide();

					}, 50);

					oLoading.data("bTable", null);

				}

			};

			return {
				_startProgressBar : _startProgressBar,
				_stopProgressBar : _stopProgressBar
			}
		})();

		/**
		 * Get the message came at the response.
		 * @param {Object} oResponse - The Resonse Object.
		 * @param {String} sDefaultMessage - The default value to be set if response does not have Message
		 */
		var _getResponseMessage = function(oResponse, sDefaultMessage) {

			var aMessageList = oResponse.messageList;
			var oMessage;

			if ($.sc.isValidArray(aMessageList)) {

				oMessage = aMessageList[0];

				if (oMessage.text) {

					return oMessage.text;
				}

				return oMessage.messageInfo.code;
			}

			return sDefaultMessage;


		};

		/**
		 * Show a message, either as confirmation or error. This is intended to work
		 * with the general messaging area at the top of the main content pages but
		 * will work with other targets as well.
		 *
		 * @param the
		 *            id of the target element to display the message into
		 * @param the
		 *            message
		 * @param the
		 *            message type, either "confirm" or "error".
		 */
		var _showMessage = function(target, message, type) {

			$('html, body').animate({ scrollTop: 0 }, 'slow');

			if ($("#" + target).length == 0) {

				var oSystemMessaging = '<div id="messaging-main" class="messaging" style="display: none;"><span	class="message"></span><a href="" class="remove">' + $.sc.locale("message.action.close") + '</a></div>';
				$('.content-inner').before(oSystemMessaging);

			}

			$("#" + target).fadeIn(200, function() {

				var targetEl = $("#" + target);

				$("#" + target + " .message").removeClass("ui-state-error").html(message);

				if ("confirm" == type) {

					targetEl.removeClass("ui-state-error");
					targetEl.addClass("ui-state-highlight");

				} else {

					targetEl.removeClass("ui-state-highlight");
					targetEl.addClass("ui-state-error");

				}

			});

		};

		/**
		 * Converts the message array of an ajax callback result object to an HTML
		 * UL list.
		 *
		 * @param messages
		 *            array of message strings
		 * @return HTML for the message list
		 */
		var _messageList = function(messages) {
			if (!messages || messages.length == 0) {
				return "";
			}
			for ( var i = 0; i < messages.length; i++) {
				messages[i] = "<li>{0}</li>".format(messages[i].text);
			}
			return "<ul class='messaging-details'>{0}</ul>".format(messages.join(""));
		};

		var _isValidResponse = function(oResponse) {

			var oResponseValidation = {
			    bIsValid : true,
			    sErrorMessage : ""
			};

			if ($.sc.isNullOrUndefined(oResponse)) {
			    oResponseValidation.bIsValid = false;
			    oResponseValidation.sErrorMessage = $.sc.locale("commons.pages.error");
			    return oResponseValidation;
			}

			if (oResponse.operationSuccess == false) {
			    oResponseValidation.bIsValid = false;
			    oResponseValidation.sErrorMessage = $.sc.getResponseMessage(oResponse, $.sc.locale("commons.pages.error"));

			    return oResponseValidation;
			}

			return oResponseValidation;

		};

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
		var _ajax = function(sUrl, oRequest, bAsync, bJson, bGet) {

			var oResponse = null;
			var sDataType = '';
			var sContentType = '';
			var oData = '';
			var sType = 'POST';

			if ($.sc.isNullOrUndefined(bAsync)) {
				bAsync = true;
			}

			if(bJson && oRequest){

				sDataType = 'json';
				sContentType = 'application/json; charset=utf-8';
				if ($.sc.isNullOrUndefined(oRequest)) {
					oRequest = {};
				}

				oData = $.toJSON(oRequest);

			}

			if(bGet){
				sType = "GET";
			}

			$.ajax({
				dataType     : sDataType,
				type         : sType,
				url          : sUrl,
				contentType  : sContentType,
				data         : oData,
				async		 : bAsync,
				success      : function (oData) {

					oResponse = oData;

				}
			});

			return oResponse;
		};

		/**
		 * Send ajax calls to get the response and validate the response showing the error message it is invalid.
		 * @param {String}   sUrl         - The url from the ajax call
		 * @param {Object}   oRequest     - The Request Object
		 * @param {Boolean}  bAsync       - Whether the Ajax call wil be sync or async.
		 * @param {Function} fnCallback   - The callback funtion to be executed after the ajax call
		 * @param {Boolean}  bBlockScreen - Block Screen if true.
		 * @returns {Object} - The Response Object.
		 */
		var _getJson = function(sUrl, oRequest, bAsync, fnCallback, sSuccessMessage, bBlockScreen, bHideProgressBar, bResponseExpected, fnErrorCallback, sMessagindId, bGet){

			// Start Progress Bar
			//if(!$.sc.isNullOrUndefined(bHideProgressBar)){
			if(!bHideProgressBar){
				$.sc.startProgressBar(null,false);

			}

			if ($.sc.isNullOrUndefined(bResponseExpected)) {
				bResponseExpected = true;
			}

			// Set default message div id if no one is specified.
			if ($.sc.isNullOrUndefined(sMessagindId)) {
				sMessagindId = "messaging-main";
			}

			var oResponse = $.sc.ajax(sUrl, oRequest, bAsync, true, bGet);
			var oValidResponse = _isValidResponse(oResponse);
			var sResponseSuccessMessage = null;

			if (!bResponseExpected) {
				return oResponse;
			}

			// Validate oResponse
			if (!oValidResponse.bIsValid) {

				// Show Error Message
				$.sc.showMessage(sMessagindId, oValidResponse.sErrorMessage, "error");
				// Stop Progress bar
				$.sc.stopProgressBar(null, false);

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

			sResponseSuccessMessage = $.sc.getResponseMessage(oResponse, null);

			// Show Success Message if it exists
			if (!$.sc.isNullOrUndefined(sSuccessMessage) && oResponse.operationSuccess) {
				$.sc.showMessage(sMessagindId, sSuccessMessage, "confirm");
			}

			if (!$.sc.isNullOrUndefined(sResponseSuccessMessage) && oResponse.operationSuccess) {
				$.sc.showMessage(sMessagindId, sResponseSuccessMessage, "confirm");
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

			if (($.sc.isNullOrUndefined(bBlockScreen) || !bBlockScreen) && (!bHideProgressBar)) {
				// Stop Progress bar
				$.sc.stopProgressBar(null, false);
			}

			return oResponse;

		};

		var _isValidPreLoad = function(oPreLoadResponse, bShowErrorMessage) {

			var oValidResponse = _isValidResponse(oPreLoadResponse);

			if(!oValidResponse.bIsValid)
			{
				if(bShowErrorMessage)
				{
					// Show Error Message
					$.sc.showMessage("messaging-main", oValidResponse.sErrorMessage, "error");
					// Stop Progress bar
					$.sc.stopProgressBar(null, true);
			    }

				return false;

			}

			return true;

		};

		var _isValidResponse = function(oResponse) {

			var oResponseValidation = {
					bIsValid : true,
					sErrorMessage : ""
			};

			if ($.sc.isNullOrUndefined(oResponse)) {
				oResponseValidation.bIsValid = false;
			    oResponseValidation.sErrorMessage = $.sc.locale("commons.pages.error");
			    return oResponseValidation;
			}

			if (oResponse.operationSuccess == false) {
				oResponseValidation.bIsValid = false;
			    oResponseValidation.sErrorMessage = $.sc.getResponseMessage(oResponse, $.sc.locale("commons.pages.error"));

			    return oResponseValidation;
			}

			return oResponseValidation;

		};

		/**
		 * Check if Response is back or not.
		 * @param {Object} - The Request Object to send at the check ajax call
		 * @param {oWaitResponseDeferred} - The deferred object tha will be resolved when response is back.
		 * @param {String} - sSuccessMessage - The Success Message.
		 * @returns {Boolean} - Whether the response is back.
		 */
		var _checkResponseStatus = function(oRequest, oWaitResponseDeferred, sSuccessMessage) {

			(function fnVerify(){

				var oResponse = $.sc.getJson("/slc/smartpoint/checkUpdatedLight.action", oRequest, false, null, sSuccessMessage, true);

				if($.sc.isValidArray(oResponse.lights)){
					// Stop Progress bar
					$.sc.stopProgressBar(null, false);
					oWaitResponseDeferred.resolve();
				} else {
					setTimeout(fnVerify, 10000);
				}

			})();

		};

		/**
		 * Block the application askig for response.
		 * @param {Object} - The Response Object came from the first action
		 * @param {Function} - The Callback functon send to the action originally.
		 * @param {String} - The Success message.
		 */
		var _waitResponse = function(oResponse, fnCallback, sSuccessMessage) {
			var iProcessId = 0;
			var aSelectedIds  = [];
			var oRequest = null;
			var oWaitResponseDeferred = $.Deferred();

			if (!$sc.isNullOrUndefined(oResponse.firstProcess)) {

				iProcessId = oResponse.firstProcess.id;

			} else {

				iProcessId = oResponse.forceLightStatusLRPId;

			}

			aSelectedIds.push({'id' : iProcessId});
			oRequest = {'processRequest' : new processRequest(aSelectedIds)};

			$.when(oWaitResponseDeferred).then(function () {
				if($.isFunction(fnCallback)){

					if (!$.sc.isNullOrUndefined(sSuccessMessage)) {

						$.sc.showMessage("messaging-main", sSuccessMessage, "confirm");

					}

					fnCallback(oResponse);
				}
			});

			checkResponseStatus(oRequest, oWaitResponseDeferred, null);
		};

		/**
		 * Send ajax calls for monitored actions, checking if rni is online and geting whether will monitor or not.
		 * @param {String}   sUrl       - The url from the ajax call
		 * @param {Object}   oRequest   - The Request Object
		 * @param {Boolean}  bAsync     - Whether the Ajax call wil be sync or async.
		 * @param {Function} fnCallback - The callback funtion to be executed after the ajax call
		 * @param {Boolean}   bCheckResponseReturn - Boolean if will hold application waiting for response.
		 */
		var _monitor = function(sUrl, oRequest, bAsync, fnCallback, sSuccessMessage, bCheckResponseReturn) {

			// Start Progress Bar
			$.sc.startProgressBar(null,false);

			// Used to pass to fnDoCall if will block application
			var bBlockProgressBar = false;

			// Check RNI
			if (!$.sc.isRniOn(true)) {
				// Stop Progress bar
				$.sc.stopProgressBar(null, false);
				return;
			}

			if (!$.sc.isNullOrUndefined(bCheckResponseReturn) && bCheckResponseReturn) {
				var fnActionCallback = fnCallback;
				bBlockProgressBar = true;
				fnCallback = function (oResponse) {
					$.sc.waitResponse(oResponse, fnActionCallback, sSuccessMessage);
				};
			}

			if(bBlockProgressBar){

				sSuccessMessage = null;

			}

			switch (sensus.settings.monitor) {
				case "1" :
					oRequest = $.sc.setMonitor(oRequest, true);
					// Ajax call for the action
					$.sc.getJson(sUrl, oRequest, bAsync, fnCallback, sSuccessMessage, bBlockProgressBar);
					break;
				case "2" :
					oRequest = $.sc.setMonitor(oRequest, false);
					// Ajax call for the action
					$.sc.getJson(sUrl, oRequest, bAsync, fnCallback, sSuccessMessage, bBlockProgressBar);
					break;
				case "3" :
					// Open dialog
					$.sc.launchActionDialog("longRunningProcessDialog", sensus.pages.longrunningprocess.dialogSettingsProcess["longRunningProcessDialog"], "monitor-dialog");
					$.when(oMonitorDeferred).then(function () {

						oRequest = $.sc.setMonitor(oRequest, sensus.pages.longrunningprocess.dialogSettingsProcess.longRunningProcessDialog.iMonitor);
						// Ajax call for the action

						$.sc.getJson(sUrl, oRequest, bAsync, fnCallback, sSuccessMessage, bBlockProgressBar);
						sensus.pages.longrunningprocess.dialogSettingsProcess.longRunningProcessDialog.iMonitor = null;
						oMonitorDeferred = $.Deferred();
					});
					break;
				default :
					break;
			}

		};

		/**
		 * Resets the fields in the form with the provide id.
		 *
		 * @param target
		 *            the id of the form to clear
		 */
		var _clearFormElements = function(target) {

			var target = $("#" + target);

			$(".messaging", target).hide();

			target.find(':input').each(function() {

				$(this).removeClass("error");

				switch (this.type) {
					case 'password':
					case 'select-multiple':
					case 'select-one':
					case 'text':
					case 'textarea':
						$(this).val('');
						break;
					case 'checkbox':
					case 'radio':
						this.checked = false;
				}

			});

		};

		var _formValidate = function(oDom, sUrl, fnCallBack){

			$(oDom).validationEngine('attach', {
					validationEventTrigger : 'submit',
					onValidationComplete   : function(form, status) {

						if (status) {

							if($.isFunction(fnCallback)){

								fnCallback();

							}

						}

					}

				});
		};


		return {
			ajax : _ajax,
			checkResponseStatus : _checkResponseStatus,
			getJson : _getJson,
			clearFormElements : _clearFormElements,
			formValidate : _formValidate,
			getResponseMessage : _getResponseMessage,
			isNullOrUndefined : _util._isNullOrUndefined,
			isValidArray : _util._isValidArray,
			isValidPreLoad : _isValidPreLoad,
			launchActionDialog : _util._launchActionDialog,
			locale : _util._locale,
			messageList : _messageList,
			showMessage : _showMessage,
			startProgressBar : _progressBar._startProgressBar,
			stopProgressBar : _progressBar._stopProgressBar,
			waitResponse : _waitResponse
		};
	})();

})(jQuery);

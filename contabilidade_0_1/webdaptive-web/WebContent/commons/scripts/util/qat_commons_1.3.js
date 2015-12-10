/*
 * jQuery Sensus Commons v1.2
 * http://www.qat.com
 *
 * Copyright (c) 2009-2014 Qat Global
 *
 * Authors	: Lucas Oliveira, Raphael Constantino (previous versions)
 * 			  Delcides Junior v1.2
 *
 * Date: 2014-01-16 20:50:00 UTC (Wed, 16 Jan 2014)
 */
(function ($)
{
	$.qat = (function ()
	{
		/**
		 * Default configuration for common functionality. These can be changed
		 * using setGeneralConfig.
		 */
		var _oConfig =
		{
			common:
			{
				errorLabel: "commons.pages.error",
				errorValidationLabel : "commons.pages.errorValidation",
				doubleHyphenLabel: "commons.pages.doubleHyphen"
			},
			content:
			{
				main	: "#load",
				tabs 	: "#tabs-content",
				inner	: ".content-inner"
			},
			date:
			{
				todayLabel		: "commons.pages.today",
				yesterdayLabel	: "commons.pages.yesterday",
				UTCTimeZone		: "Etc/GMT"
			},
			dialog:
			{
				actionId	: "#action-dialog",
				dialogClass	: "action-dialog",
				notImplLabel: "commons.action.notimplemented"
			},
			locale:
			{
				mode : 'map'
			},
			message:
			{
				mainContainer	: "messaging-main",
				closeLabel		: "message.action.close"
			},
			monitor:
			{
				bAsync 							: true,
				rniOfflineLabel 				: "action.longRunningProcessDialog.rnioffline",
				checkRniUrl						: "api/process/checkRNIStatus",
				requestAttribute 				: "processRequest",
				longRunninProcessDialog 		: "longRunningProcessDialog",
				monitorDialogSettingsNamespace 	: "longrunningprocess",
				sRniSystemMessaging 			: "#system-messaging",
				sRniOfflineMessaging 			: "#rni-offline"
			},
			pageLoader:
			{
				qatLabel	: "commons.pages.qat",
				serviceLabel: "main.page.manage_"
			},
			progressBar:
			{
				oLoading	: $('#loading'),
				oProgressbar: $("#progress-bar"),
				oPreload	: $("#preload"),
				oFullScreen	: $(".fullscreen"),
				initiated	: false,
				updateLabel	:"commons.pages.updating"
			},
			settings:
			{
				sUpsertSettingsUrl 		: "api/settings/upsert",
				sUpsertSettingsMessage  : "action.savesettings.success"
			}
		};

		/**
		 * Change common default configuration
		 *
		 * @param {Object} -
		 *            An object containing the properties to be changed in
		 *            defaults.
		 */
		var _setGeneralConfig = function(oConfig)
		{
			$.extend(_oConfig, oConfig);
		};

		/**
		 * Ajax methods
		 */
		var _ajax = (function()
		{
			/**
			 * Default configuration for all ajax calls
			 * This will be extended for every call.
			 */
			var _oCallDefaults =
			{
				bAsync			: true,		// True for Asynchronous calls
				bCache			: true,		// True for caching, this is the default behavior from Jquery Ajax
				bBlockScreen	: false,	// If the screen will be blocked (for monitored operations)
				bHideProgressBar: false,	// Hide the progress bar for underlying operations
				bGet			: false,	// False for POST type Ajax calls
				bJson			: true,		// If the content being sent is in JSON format
				bInitialLoad 	: true,		// False to set initialLoad parameter as false on request
				bShowMessage	: true,		// If the returned object will be validated
				fnCallback		: null,		// Callback for succeeded operations
				fnErrorCallback	: null,		// Callback for failed operations
				oDeferred		: null,
				oRequest		: null,		// Request Object
				sContentType	: 'application/json; charset=utf-8',
				sDataType		: null,
				sMessagindId	: null,		// Container id for messages
				sSuccessMessage	: null,
				msgTarget		: null,
				sUrl			: null		// If this is null the call won't happen and an error message will be shown
			};


			/**
			 * Make an http ajax call
			 *
			 * @param {Object} Object containing all settings for the call
			 * @returns {Object} The Response Object came from BE.
			 *
			 */
			var _call = function(oCallConfig)
			{
				if(!oCallConfig.sUrl)
				{
					_message.show({message: _locale.get(_oConfig.common.errorLabel), type: "error"});
					return false;
				}

				var oData = '';
				var sType = 'POST';

				if (oCallConfig.bJson)
				{
					if (_util.isNullOrUndefined(oCallConfig.oRequest))
					{
						oCallConfig.oRequest = {};
					}

					oData = $.toJSON(oCallConfig.oRequest);
				}

				if (oCallConfig.bGet)
				{
					sType = "GET";
					oCallConfig.bCache = false;

					if(oCallConfig.bInitialLoad === false)
					{
						if (oCallConfig.sUrl.indexOf("?") == -1)
						{
							oCallConfig.sUrl += "?initialLoad=false";
						}
						else
						{
							oCallConfig.sUrl += "&initialLoad=false";
						}
					}
				}

				if(WDHost)
				{
					oCallConfig.sUrl = "/" + oCallConfig.sUrl;
					oCallConfig.sUrl = _util.replaceAll("//", "/", oCallConfig.sUrl);
				}

				oCallConfig.oResponse = null;

				if (_util.fnCheckXSS(oData))
				{
					if ((_util.isNullOrUndefined(oCallConfig.bBlockScreen) || !oCallConfig.bBlockScreen) && (!oCallConfig.bHideProgressBar))
					{
						// Stop Progress bar
						_progressBar.stop();
					}

					_message.show({message: _locale.get(_oConfig.common.errorValidationLabel), type: "error"});

					return false;
				}

				$.ajax({
						dataType 	: oCallConfig.sDataType,
						type 		: sType,
						url			: oCallConfig.sUrl,
						contentType	: oCallConfig.sContentType,
						data		: oData,
						cache		: oCallConfig.bCache,
						async		: oCallConfig.bAsync,
						timeout		: oCallConfig.timeout || null,
						success		: function(oData)
						{
							oCallConfig.oResponse = oData;

							if (!_util.isNullOrUndefined(oCallConfig.oDeferred))
							{
								oCallConfig.oDeferred.resolve(oCallConfig.oResponse);
							}
						},
						error 		: function(jqXHR, textStatus, errorThrown)
						{
							console.log("Ajax call error: " + textStatus + ', ' + errorThrown);

							if (oCallConfig.bShowErrorMessage)
							{
								_message.show({message: _locale.get(_oConfig.common.errorLabel), type: "error"});
							}
						},
						complete	: function()
						{
							oCallConfig._afterCall();
						}
					});
			};

			/**
			 * Send ajax calls to get the response and validate the response
			 * showing the error message if it is invalid.
			 *
			 */
			var _doCall = function(oCallConfig)
			{

				oCallConfig = $.extend({}, _oCallDefaults, oCallConfig);

				// Start Progress Bar
				if (!oCallConfig.bHideProgressBar)
				{
					_progressBar.start(null, false);
				}

				oCallConfig._afterCall = function()
				{
					var oResponse 		= oCallConfig.oResponse;
					var oValidResponse 	= _util.isValidResponse(oResponse);

					// Validate oResponse
					if (!oValidResponse.bIsValid)
					{
						if(oCallConfig.bShowMessage)
						{
							// Show Error Message
							_message.show({message: oValidResponse.sErrorMessage, target : oCallConfig.msgTarget, type: "error"});
						}

						if ((_util.isNullOrUndefined(oCallConfig.bBlockScreen) || !oCallConfig.bBlockScreen) && (!oCallConfig.bHideProgressBar))
						{
							// Stop Progress bar
							_progressBar.stop();
						}

						// Execute Error Callback function if it exists
						if ($.isFunction(oCallConfig.fnErrorCallback))
						{
							try
							{
								oCallConfig.fnErrorCallback(oResponse, oCallConfig.oRequest);
							}
							catch (e)
							{
								// Log Error
								console.log(e);
							}
						}

						return;
					}

					if(oCallConfig.bShowMessage)
					{
						var sResponseSuccessMessage = _message.fromResponse(oResponse, null);

						// Show Success Message if it exists
						if (!_util.isNullOrUndefined(oCallConfig.sSuccessMessage) && oResponse.operationSuccess)
						{
							_message.show({message: oCallConfig.sSuccessMessage, target : oCallConfig.msgTarget, type: "confirm"});
						}

						if (!_util.isNullOrUndefined(sResponseSuccessMessage) && oResponse.operationSuccess)
						{
							_message.show({message: sResponseSuccessMessage, target : oCallConfig.msgTarget, type: "confirm"});
						}
					}

					// Execute Callback function if it exists
					if ($.isFunction(oCallConfig.fnCallback))
					{
						try
						{
							oCallConfig.fnCallback(oResponse, oCallConfig.oRequest);
						}
						catch (e)
						{
							// Show Error
							console.log(e);
						}
					}

					if ((_util.isNullOrUndefined(oCallConfig.bBlockScreen) || !oCallConfig.bBlockScreen) && (!oCallConfig.bHideProgressBar))
					{
						// Stop Progress bar
						_progressBar.stop();
					}
				};

				_call(oCallConfig);

				if(!oCallConfig.bAsync)
				{
					return oCallConfig.oResponse;
				}
			};

			/**
			 * Utility method for GET type Ajax calls
			 */
			var _get = function(oCallConfig)
			{
				if(!_util.isNullOrUndefined(oCallConfig))
				{
					oCallConfig.bGet = true;
				}

				return _doCall(oCallConfig);
			};

			return{
				get		: _get,
				post	: _doCall
			};

		})();


		/**
		 * Utility methods for the application.
		 */
		var _util = (function ()
		{

			/**
			 * Closes the action dialog with the parameter selector.
			 *
			 * @param dialogSelector -
			 *            The dialog selector or object (Jquery).
			 *
			 */
			var _closeActionDialog = function(dialogSelector)
			{
				var oDialog = (dialogSelector instanceof jQuery) ? dialogSelector : $(dialogSelector);

				if (!$.qat.isNullOrUndefined(oDialog.dialog("instance")))
				{
					oDialog.dialog('close');
				}

				return oDialog;
			};

			/**
			 * This is a modified copy of the jQuery Extend method. It modifies the need for a plain object.
			 * That said, this must be used with caution to avoid errors.
			 * The main purpose of the method is to have the ability to deep copy request objects (from request model).
			 *
			 * For detailed usage guide, refer to jQuery Extend method.
			 *
			 */
			var _extend = function()
			{
				var src, copyIsArray, copy, name, options, clone,
				target = arguments[0] || {},
				i = 1,
				length = arguments.length,
				deep = false;

				// Handle a deep copy situation
				if ( typeof target === "boolean" ) {
					deep = target;

					// skip the boolean and the target
					target = arguments[ i ] || {};
					i++;
				}

				// Handle case when target is a string or something (possible in deep copy)
				if ( typeof target !== "object" && !$.isFunction(target) ) {
					target = {};
				}

				// fail if only one argument is passed
				if ( i === length ) {
					return false;
				}

				for ( ; i < length; i++ ) {
					// Only deal with non-null/undefined values
					if ( (options = arguments[ i ]) != null ) {
						// Extend the base object
						for ( name in options ) {
							src = target[ name ];
							copy = options[ name ];

							// Prevent never-ending loop
							if ( target === copy ) {
								continue;
							}

							// Recurse if we're merging plain objects or arrays -> changed to check for object type
							if ( deep && copy && ( (copyIsArray = $.isArray(copy)) || typeof copy == "object" ) ) {
								if ( copyIsArray ) {
									copyIsArray = false;
									clone = src && $.isArray(src) ? src : [];

								} else {
									// changed to check for object type
									clone = src && typeof copy == "object" ? src : {};
								}

								// Never move original objects, clone them
								target[ name ] = _extend( deep, clone, copy );

							// Don't bring in undefined values
							} else if ( copy !== undefined ) {
								target[ name ] = copy;
							}
						}
					}
				}

				// Return the modified object
				return target;
			};

			/**
			 * Check if the current user logged has role
			 *
			 * @param Role string
			 *
			 */
			var _hasRole = function(sRole)
			{
				return qat.settings.userContext.userRole.indexOf(sRole) !== -1;
			};

			/**
			 * Validate if a property is null or undefined.
			 *
			 * @param {Object} -
			 *            The property to be validated.
			 * @return {Boolean} - Whether the property is valid or not.
			 */
			var _isNullOrUndefined = function (oProperty)
			{
				if (oProperty === null || oProperty === undefined || oProperty === "")
				{
					return true;
				}

				return false;
			};

			/**
			 * Validation to check if is an Array and if it has Values
			 *
			 * @param {Array} - The array to be validated.
			 * @return {Boolean} - Whether the array is valid or not.
			 */
			var _isValidArray = function(aObject)
			{
				if (!_isNullOrUndefined(aObject) && $.isArray(aObject) && aObject.length)
				{
					return true;
				}

				return false;
			};

			/**
			 * Check if Valid Response Object came from ViewController.
			 *
			 * @param {Object}
			 *            oPreLoadResponse The Object returned from
			 *            ViewController.
			 * @param {Boolean}
			 *            bShowErrorMessage Whether to show or not the error
			 *            message.
			 */
			var _isValidPreLoad = function(oPreLoadResponse, bShowErrorMessage)
			{
				var oValidResponse = _isValidResponse(oPreLoadResponse);

				if (!oValidResponse.bIsValid)
				{
					if ((bShowErrorMessage === undefined)
							|| (bShowErrorMessage === "")
							|| (bShowErrorMessage === " ") || bShowErrorMessage)
					{
						// Show Error Message
						_message.show({message: oValidResponse.sErrorMessage, type: "error"});
						// Stop Progress bar
						_progressBar.stopGlobal();
					}
					return false;
				}
				return true;
			};

			/**
			 * Valid Response Object came from BE.
			 *
			 * @param {Object}
			 *            oResponse The Object returned from BE.
			 * @returns {Object} An Object that contains whether the response is
			 *          valid or not and the error message.
			 */
			var _isValidResponse = function(oResponse)
			{
				var oResponseValidation =
				{
					bIsValid : true,
					sErrorMessage : ""
				};

				if (_isNullOrUndefined(oResponse))
				{
					oResponseValidation.bIsValid = false;
					oResponseValidation.sErrorMessage = _locale.get(_oConfig.common.errorLabel);
					return oResponseValidation;
				}

				if (oResponse.operationSuccess == false)
				{
					oResponseValidation.bIsValid = false;
					oResponseValidation.sErrorMessage = _message.fromResponse(oResponse, _locale.get(_oConfig.common.errorLabel));
					return oResponseValidation;
				}

				return oResponseValidation;
			};

			/**
			 * Launches the action dialog with the action id and the
			 * corresponding dialog settings. This function expects the dialog
			 * to be launched from the "#action-dialog" element.
			 *
			 * @param sActionId
			 *            the string code for the action. This is only needed to
			 *            display a warning note for unimplemented actions.
			 * @param the
			 *            settings for the particular action dialog. These
			 *            settings are typically defined in the main namespace
			 *            for the containing page.
			 */
			var _launchActionDialog = function(sActionId, oDialogSettings, sDialogId)
			{
				if (!oDialogSettings)
				{
					alert(_locale.get(_oConfig.dialog.notImplLabel, sActionId));
					return;
				}

				oDialogSettings.actionId = sActionId;

				if(_isNullOrUndefined(oDialogSettings.width))
				{
					oDialogSettings.width = 500;
				}

				if(_isNullOrUndefined(oDialogSettings.minheight))
				{
					oDialogSettings.minheight = 150;
				}

				_form.clearElements(oDialogSettings.form);

				var actionDialogId = _oConfig.dialog.actionId;

				if(!_isNullOrUndefined(sDialogId))
				{
					actionDialogId = "#"+sDialogId;
				}

				_closeActionDialog(actionDialogId);
				$(actionDialogId).empty();

				var actionDialog = $(actionDialogId).dialog( {
					autoOpen	: false,
					position	: { my: "center", at: "center", of: window },
					title		: oDialogSettings.title,
					close		: oDialogSettings.close,
					beforeClose	: oDialogSettings.beforeClose,
					width		: oDialogSettings.width,
					minHeight	: oDialogSettings.minheight,
					maxHeight	: oDialogSettings.maxheight,
					modal		: true,
					buttons		: oDialogSettings.buttons,
					dialogClass	: _oConfig.dialog.actionClass,
					open 		: oDialogSettings.open,
					resizable	: false,
					dragStop	: oDialogSettings.dragStop,
					iconButtons : oDialogSettings.iconButtons
				});

				oDialogSettings.action(actionDialog);
			};

			/**
			 * Replace all occurrences of given token, for the given
			 * replaceToken in the given String
			 *
			 * @param {String} -
			 *            What will be replaced.
			 * @param {String} -
			 *            What will replace.
			 * @param {String} -
			 *            Where the replacement is going to occur.
			 */
			var _replaceAll = function replaceAll(token, replaceToken, str)
			{
				return str.replace(new RegExp(token, 'g'), replaceToken);
			};

			/**
			 * Truncate the given number using the given precision.
			 *
			 * @param {Number} -
			 *            The number to be truncated.
			 * @param {Number} -
			 *            The precision used, decimal places.
			 * @return {String} - Truncated number as String.
			 */
			var _nTruncate = function(value, precision)
			{
				var intPlaces = String(value).split(".")[0].length;
				if (value.length != intPlaces)
				{
					precision += intPlaces + 1;
				}

				return String(value).substring(0, precision);
			};

			/**
 			 *	@param Object Mapped
 			 *		Property name is the property Enum
 			 *		Property valude is property Value
			 */
			var _savePropertyProfile = function (oParam, id)
			{
				var aSettingsList = [];

				for (var i in oParam)
				{
					aSettingsList.push(
						{
							propertyName 	: i,
							propertyValue 	: oParam[i]
						}
					);
				}

				var oRequest = new UserContextRequest({
					properties 	: aSettingsList
				});

				var fnCallback = function(oResponse)
				{
					qat.pages.dm.fnReloadUserSettings(oResponse);
					_closeActionDialog(_oConfig.dialog.actionId);
				};

				_ajax.post({
					sUrl 			: _oConfig.settings.sUpsertSettingsUrl,
					oRequest 		: oRequest,
					bAsync 			: false,
					fnCallback 		: fnCallback,
					sSuccessMessage : _locale.get(_oConfig.settings.sUpsertSettingsMessage)
				});
			};

			var _fnCheckXSS = function(value) {
				var bReturn;

				try {
					bReturn = decodeURI(value).match(/(<([^>]+)>)/ig);
				} catch (e) {
					bReturn = null;
				}

				return bReturn;
			};

			/**
			 * Set Strong
			 *
			 * @param sValue
			 *            String - the value
			 * @return the value between "strong" tag
			 */
			var _setStrong = function (sValue)
			{
				if(_isNullOrUndefined(sValue))
				{
					return "";
				}
				return ": <strong>" + sValue + "</strong>";
			};

			/**
			 * Capitalize words in a String.
			 *
			 * @param word
			 *            String - the word / text
			 * @return the capitalized word / text
			 */
			var _capitalize = function(word)
			{
				word = word.split("");
				var tmp = "";

				for (i = 0; i < word.length; i++)
				{
					if (word[i-1])
					{
						if (word[i-1] == " ")
						{
							word[i] = word[i].replace(word[i], word[i].toUpperCase());
						}
						else
						{
							word[i] = word[i].replace(word[i],word[i].toLowerCase());
						}
					}
					else
					{
						word[i] = word[i].replace(word[i],word[i].toUpperCase());
					}

					tmp += word[i];
				}

				return  tmp;
			};

			return {
				closeActionDialog	: _closeActionDialog,
				extend				: _extend,
				hasRole				: _hasRole,
				isNullOrUndefined	: _isNullOrUndefined,
				isValidArray		: _isValidArray,
				isValidPreLoad		: _isValidPreLoad,
				isValidResponse		: _isValidResponse,
				launchActionDialog	: _launchActionDialog,
				nTruncate			: _nTruncate,
				replaceAll			: _replaceAll,
				savePropertyProfile : _savePropertyProfile,
				fnCheckXSS 			: _fnCheckXSS,
				setStrong			: _setStrong,
				_capitalize			: _capitalize
			};
		})();

		/**
		 * Monitor methods
		 */
		var _monitor = (function()
		{
			/**
			 * Default configuration for monitor
			 */
			var _oMonitorDefaults =
			{
				oMonitorDeferred  		: $.Deferred(),
				url 					: "",
				oRequest 				: null,
				fnCallback 				: null,
				sSuccessMessage 		: "",
				bCheckResponseReturn 	: false, // TODO
				fnRequestInterceptor	: null,
				bCheckRni				: true
			};

			/** Check if Rni is On and show a error message if not.
			*
			* @param {Boolean}
			*            bShowErrorMessage - If it will show message when RNI is off
			*/
			var _checkIfRniIsOn = function(bShowErrorMessage, fnCallback)
			{
				var oRequest 		= {};
//					oRequest[_oConfig.monitor.requestAttribute] = new ProcessRequest();

				var fnCheckRniCallBack = function(oRniResponse)
				{
					var oValidResponse 	= _util.isValidResponse(oRniResponse);
					var $rniOffline 	= $(_oConfig.monitor.sRniOfflineMessaging);

					if (!oRniResponse.linkStatus)
					{
						$rniOffline.show();
						qat.pages.longrunningprocess.toggleSystemMessaging(true);
					}
					else if (oRniResponse.linkStatus)
					{
						$rniOffline.hide();
						qat.pages.longrunningprocess.toggleSystemMessaging(true);
					}

					if (qat.pages.longrunningprocess.lrpSize == 0 && oRniResponse.linkStatus)
					{
						qat.pages.longrunningprocess.toggleSystemMessaging(false);
					}

					if (bShowErrorMessage)
					{
						if (!oValidResponse.bIsValid)
						{
							_message.show({message: _locale.get(_oConfig.common.errorLabel), type : "error"});
						}
						else if (!oRniResponse.linkStatus)
						{
							_message.show({message: _locale.get(_oConfig.monitor.rniOfflineLabel), type : "error"});
						}

						if ($.isFunction(fnCallback))
						{
							fnCallback(oRniResponse.linkStatus);
						}
						return;
					}

					if ($.isFunction(fnCallback))
					{
						fnCallback(oRniResponse.linkStatus);
					}
				};
				_ajax.post({
					sUrl 				: _oConfig.monitor.checkRniUrl,
					oRequest 			: oRequest,
					bHideProgressBar 	: true,
					fnCallback 			: fnCheckRniCallBack
				});
			};

			/**
			 * Set whether the action is monitor or not at the request.
			 *
			 * @param {Object}
			 *            oRequest - The request Object that will be changed.
			 * @param {Boolean}
			 *            isMonitored - The monitored field to be added at the
			 *            request..
			 * @returns {Object} - The new Request Object.
			 */
			var _setMonitor = function(oRequest, isMonitored, oMonitorDefaults)
			{
				// Set isMonitored field at the request
				if (oRequest instanceof ScheduleRequest)
				{
					oRequest.monitored = isMonitored;
					return oRequest;
				}

//				if (!oRequest.processCriteria)
//				{
//					oRequest.processCriteria = new ProcessCriteria();
//				}

				if ($.isFunction(oMonitorDefaults.fnRequestInterceptor))
				{
					oMonitorDefaults.fnRequestInterceptor(oRequest, isMonitored);
				}

//				oRequest.processCriteria.monitored = isMonitored;
				return oRequest;

			};

			/**
			 * Event click for Monitor/Dismiss Dialog. This function check whether
			 * is monitor or not and save the preferences if remember action is
			 * checked. Also it resolve the Deferred used at the prompt dialog.
			 *
			 * @param {Object} -
			 *            The element.
			 */
			var _monitorDialogEvent = function(e)
			{
				e.preventDefault();

				var iMonitor;
				var bMonitor;

				if ($(e.currentTarget).hasClass("dismiss"))
				{
					iMonitor = 2;
					bMonitor = false;
				}
				else
				{
					iMonitor = 1;
					bMonitor = true;
				}

				qat.pages.longrunningprocess.dialogSettingsProcess.longRunningProcessDialog.iMonitor = bMonitor;

				if ($('.highlight small input').is(':checked')) {
					_util.savePropertyProfile(
						{
							"MONITOR_REQUEST" : iMonitor.toString()
						}
					);
				}

				$.qat.closeActionDialog("#monitor-dialog");
				_oMonitorDefaults.oMonitorDeferred.resolve();
			};

			/**
			 * Block the application askig for response.
			 *
			 * @param {Object} -
			 *            The Response Object came from the first action
			 * @param {Function} -
			 *            The Callback functon send to the action originally.
			 * @param {String} -
			 *            The Success message.
			 */
			var _waitResponse = function(oResponse, fnCallback, sSuccessMessage, oMonitorDefaults)
			{
				var iProcessId 				= 0;
				var oRequest 				= null;
				var oWaitResponseDeferred 	= $.Deferred();

				if (!_util.isNullOrUndefined(oResponse.firstProcess))
				{
					iProcessId = oResponse.firstProcess.id;
				}
				else
				{
					iProcessId = oResponse.forceLightStatusLRPId;
				}

				oRequest =
				{
					processList : [ {
						id : iProcessId
					} ]
				};

				$.when(oWaitResponseDeferred).then(
						function() {
							if ($.isFunction(fnCallback)) {

								if (!_util.isNullOrUndefined(sSuccessMessage))
								{
									_message.show({message: sSuccessMessage, type: "confirm"});

								}

								fnCallback(oResponse);
							}
						});

				_checkResponseStatus(oRequest, oWaitResponseDeferred, null, oMonitorDefaults);
			};

			/**
			 * Check if Response is back or not.
			 *
			 * @param {Object} -
			 *            The Request Object to send at the check ajax call
			 * @param {oMonitorDefaults} -
			 *            The deferred object tha will be resolved when response is
			 *            back.
			 * @param {String} -
			 *            sSuccessMessage - The Success Message.
			 * @returns {Boolean} - Whether the response is back.
			 */
			var _checkResponseStatus = function(oRequest, oWaitResponseDeferred, sSuccessMessage, oMonitorDefaults)
			{
				(function fnVerify()
				{
					var oResponse = _ajax.post({
						sUrl 				: "api/process/fetch/id" 	,
						oRequest 			: oRequest 					,
						bAsync 				: false 					,
						sSuccessMessage 	: sSuccessMessage 			,
						bBlockScreen 		: true
					});

					if (_util.isValidArray(oResponse.processes) && oResponse.processes[0].isProcessComplete)
					{
						// Stop Progress bar
						_progressBar.stop();
						oWaitResponseDeferred.resolve();
					}
					else
					{
						setTimeout(fnVerify, 10000);
					}

				})();
			};

			var _setMonitorAjax = function(oMonitorDefaults)
			{

				oMonitorDefaults = $.qat.extend(_oMonitorDefaults, oMonitorDefaults);

				switch (parseInt(qat.settings.user.monitorRequest, 10))
				{
					case 1: // Always Monitor
						oMonitorDefaults.fnAlwaysMonitor(oMonitorDefaults);
						break;

					case 2: // Always Dismiss Monitor
						oMonitorDefaults.fnAlwaysDismissMonitor(oMonitorDefaults);
						break;

					case 3: // Prompt
						$.when(oMonitorDefaults.oMonitorDeferred).then(
						function()
						{
							oMonitorDefaults.fnMonitorDeferred(oMonitorDefaults);
						});

						// Open dialog
						_util.launchActionDialog(_oConfig.monitor.longRunninProcessDialog , qat.pages[_oConfig.monitor.monitorDialogSettingsNamespace].dialogSettingsProcess[_oConfig.monitor.longRunninProcessDialog], "monitor-dialog");
						break;

					default:
						break;
				}
			};

			/**
			 * Send ajax calls for monitored actions, checking if rni is online and
			 * geting whether will monitor or not.
			 *
			 * @param {String}
			 *            sUrl - The url from the ajax call
			 * @param {Object}
			 *            oRequest - The Request Object
			 * @param {Boolean}
			 *            bAsync - Whether the Ajax call wil be sync or async.
			 * @param {Function}
			 *            fnCallback - The callback funtion to be executed after the
			 *            ajax call
			 * @param {Boolean}
			 *            bCheckResponseReturn - Boolean if will hold application
			 *            waiting for response.
			 */
			//var _start = function(.url, oRequest, bAsync, fnCallback, sSuccessMessage, bCheckResponseReturn) {
			var _start = function(oMonitorDefaults)
			{
				oMonitorDefaults = $.extend({}, _oMonitorDefaults, oMonitorDefaults);

				// Start Progress Bar
				_progressBar.start();

				// Used to pass to fnDoCall if will block application
				oMonitorDefaults.bBlockProgressBar = false;

				var fnCheckRniCallBack = function(IsRniIsOn)
				{
					// Check RNI
					if (oMonitorDefaults.bCheckRni && (!IsRniIsOn))
					{
						// Stop Progress bar
						_progressBar.stop();
						return;
					}

					if (!_util.isNullOrUndefined(oMonitorDefaults.bCheckResponseReturn) && oMonitorDefaults.bCheckResponseReturn)
					{
						var fnActionCallback 				= oMonitorDefaults.fnCallback;
						var sSuccessMessageAfter 			= oMonitorDefaults.sSuccessMessage;
						oMonitorDefaults.bBlockProgressBar 	= true;

						oMonitorDefaults.fnCallback = function(oResponse)
						{
							_waitResponse(oResponse, fnActionCallback, sSuccessMessageAfter, oMonitorDefaults);
						};
					}

					if (oMonitorDefaults.bBlockProgressBar)
					{
						oMonitorDefaults.sSuccessMessage = null;
					}

					// Ajax call for the action
					var fnMonitorAjaxPost = function ( oMonitorDefaults )
					{
						_ajax.post(
						{
							sUrl 			: oMonitorDefaults.url,
							oRequest 		: oMonitorDefaults.oRequest,
							bAsync 			: oMonitorDefaults.bAsync,
							fnCallback 		: oMonitorDefaults.fnCallback,
							bBlockScreen 	: oMonitorDefaults.bBlockProgressBar,
							sSuccessMessage : oMonitorDefaults.sSuccessMessage,
							msgTarget		: oMonitorDefaults.msgTarget
						});
					};

					var fnDefineMonitor = function(oMonitorDefaults, bMonitor)
					{
						oMonitorDefaults.oRequest = _setMonitor(oMonitorDefaults.oRequest, bMonitor, oMonitorDefaults);
						fnMonitorAjaxPost( oMonitorDefaults );
					};

					oMonitorDefaults.fnAlwaysMonitor = function(oMonitorDefaults)
					{
						fnDefineMonitor( oMonitorDefaults, true );
					};

					oMonitorDefaults.fnAlwaysDismissMonitor = function(oMonitorDefaults)
					{
						fnDefineMonitor( oMonitorDefaults, false );
					};

					oMonitorDefaults.fnMonitorDeferred = function(oMonitorDefaults)
					{
						fnDefineMonitor( oMonitorDefaults, qat.pages.longrunningprocess.dialogSettingsProcess.longRunningProcessDialog.iMonitor );
						qat.pages.longrunningprocess.dialogSettingsProcess.longRunningProcessDialog.iMonitor = null;
						_oMonitorDefaults.oMonitorDeferred = $.Deferred();
					};

					_setMonitorAjax( oMonitorDefaults );
				};

				_checkIfRniIsOn(true, fnCheckRniCallBack);
			};

			return {
				start				: _start,
				setMonitor 			: _setMonitor,
				setMonitorAjax		: _setMonitorAjax,
				monitorDialogEvent 	: _monitorDialogEvent,
				checkRni		 	: _checkIfRniIsOn
			};
		})();

		/**
		 * Date manipulation methods.
		 */
		var _date = (function ()
		{
			/**
			 * Is Less Compare two dates
			 *
			 * @param Date -
			 *            dDate1
			 * @param Date -
			 *            dDate2
			 * @return Boolean - Whether the dDate1 is less than or equals to
			 *         dDate2
			 */
			var _isLess = function (dDate1, dDate2)
			{
				return dDate1._timeProxy <= dDate2._timeProxy;
			};

			var _convertDate = function (sDateTime, sFormat)
			{
				if (!_util.isNullOrUndefined(sDateTime) && !_util.isNullOrUndefined(sFormat))
				{
					var aFormat = sFormat.split(" ")[0].split("/");
					var aDateTime = sDateTime.split(" ")[0].split("/");

					return {
						days : aDateTime[aFormat.indexOf("dd")],
						months : aDateTime[aFormat.indexOf("mm")],
						years : aDateTime[aFormat.indexOf("yy")],
					}

				}

				return null;
			}

			var _convertHours = function (sDateTime, sFormat)
			{
				var aDateTime 	= sDateTime.split(" ");
				var sTime 		= aDateTime.length > 1 ? aDateTime[1].toLowerCase() : null;
				var iHourFormat = 0;
				var bMidnight 	= false;
				var oTimeObj  = {}

				// Format time
				if (sTime)
				{
					if (sTime.indexOf("m") != -1)
					{
						var sHour = sTime.split(":")[0];

						/**
						 * If hours is PM and different 12 PM or If hours is
						 * 12AM
						 *
						 * iHourFormat variable receives the value 12 for to
						 * pass 24hrs format
						 */
						if (sTime.indexOf("pm") != -1 && sHour != "12")
						{
							iHourFormat = 12;
						}
						else if (sTime.indexOf("am") != -1 && sHour == "12")
						{
							bMidnight = true;
						}

						sTime = sTime.replace(/(am|pm)/, "").trim();
					}

					sTime = sTime.split(":");

					if (bMidnight)
					{
						sTime[0] = 0;
					}

					oTimeObj.hours = (iHourFormat == 12 ? (parseInt(sTime[0], 10) + iHourFormat) : sTime[0]);
					oTimeObj.minutes = (sTime[1]);

					// Validate if contains seconds
					if (sTime[2])
					{
						oTimeObj.seconds = (sTime[2]);
					}
				}

				return oTimeObj;
			}

			var _convertToTimeZoneJS = function (oDate, sTimezoneId)
			{
				var oTimeZoneJS = new timezoneJS.Date(oDate, sTimezoneId);
//				var iBrowserOffset = oTimeZoneJS.getTimezoneOffset();
//				var iCurrentOffset = 0;

//				oTimeZoneJS.setTimezone(sTimezoneId);

//				iCurrentOffset = oTimeZoneJS.getTimezoneOffset();

				// The purpose of this code is to create an instance of the timezoneJS with the parameter timezone
				// but withough modify the hour
//				oTimeZoneJS.setMinutes(oTimeZoneJS.getMinutes() - (iBrowserOffset - iCurrentOffset));

				return oTimeZoneJS;
			}

			var _convertToTimeZoneJSFromString = function (oDate, sTimezoneId, sFormat)
			{

				// Date on string format YEAR-MONTH-DAY-HOURS-MINUTES-SECONDS-MILISECONDS
				if ($.type(oDate) == "string" && oDate.indexOf("-") != -1)
				{
					aDateFields = oDate.split("-");

					var oTimeZoneJS = new timezoneJS.Date(aDateFields[0], (aDateFields[1] - 1), aDateFields[2],
							aDateFields[3], aDateFields[4], aDateFields[5], aDateFields[6], _oConfig.date.UTCTimeZone);
					oTimeZoneJS.setTimezone(sTimezoneId);

					return oTimeZoneJS;
				}


				if ($.type(oDate) == "string" && oDate.indexOf("/") != -1)
				{
					var oTimeObj = _convertHours(oDate, sFormat);
					var oDateObj = _convertDate(oDate, sFormat);

					var oTimeZoneJS = new timezoneJS.Date(oDateObj.years, (oDateObj.months - 1), oDateObj.days, oTimeObj.hours, oTimeObj.minutes, oTimeObj.seconds, sTimezoneId);

					return oTimeZoneJS;
				}

			}

			var _getEpochTime = function (oDate, oCurrentTimeZone, oNewTimeZone, sFormat)
			{
				if (!$.type(oDate) == "object")
				{
					return date._timeProxy;
				}

				return _createTimeZoneJS(oDate, oCurrentTimeZone, oNewTimeZone, sFormat)._timeProxy;

			};

			var _getCurrentEpochInUTC = function()
			{
				return _createTimeZoneJSInUTC()._timeProxy;
			}

			var _getCurrentEpoch = function()
			{
				return _createTimeZoneJS()._timeProxy;
			}

			var _createTimeZoneJSInUTC = function (oDate, oCurrentTimeZone, sFormat)
			{
				return _createTimeZoneJS(oDate, oCurrentTimeZone, false, sFormat);
			}

			var _createTimeZoneJS = function (oDate, oCurrentTimeZone, oNewTimeZone, sFormat, bFollowsDST)
			{

				var oTimeZoneJS;
				var sTimezoneId		= _getTimezoneId(oCurrentTimeZone);
				var bFollowsDST 	= true;

				if (!_util.isNullOrUndefined(oCurrentTimeZone))
				{
					bFollowsDST = oCurrentTimeZone.bFollowsDST;
				}

				// Current Date
				if (_util.isNullOrUndefined(oDate))
				{
					oTimeZoneJS = new timezoneJS.Date(sTimezoneId);
				}

				// Remove TimeZone Difference
				if ($.type(oDate) == "number" || $.type(oDate) == "date")
				{
					oTimeZoneJS = _convertToTimeZoneJS(oDate, sTimezoneId, sFormat)
				}

				if ($.type(oDate) == "string")
				{
					oTimeZoneJS = _convertToTimeZoneJSFromString(oDate, sTimezoneId, sFormat)
				}

				if (oDate instanceof timezoneJS.Date)
				{
					oTimeZoneJS = new timezoneJS.Date(oDate, oDate.timezone);
				}

				if (_util.isNullOrUndefined(oTimeZoneJS))
				{
					oTimeZoneJS = new timezoneJS.Date(sTimezoneId);
					console.log("Error converting date, please verify. Cause: Invalid date. Commons#Date:1345");
				}

				// Apply new Time Zone
				if (!_util.isNullOrUndefined(oNewTimeZone))
				{
					oTimeZoneJS.setTimezone(_getTimezoneId(oNewTimeZone));
				}

				if (bFollowsDST === false && oTimeZoneJS.getTimezoneInfo().isDST)
				{
					oTimeZoneJS.setMinutes(oTimeZoneJS.getMinutes() - 60);
				}

				return oTimeZoneJS;

			}

			var _convertDateInSecToMiliSec = function (oDate)
			{
				if ( !$.qat.isNullOrUndefined(oDate)
						&& oDate.toString().length === 10 )
				{
					return oDate * 1000;
				}

				return oDate;
			}

			/**
			 * TODO - CHECK
			 * Get Current Date Call server to get the current date or get from
			 * name-space (qat.settings.currentTime) date cache refresh each
			 * ten seconds
			 *
			 * @param bUtc
			 *            Boolean - the flag to apply UTC time-zone
			 * @param the
			 *            server current date
			 */
			var _getCurrentDate = function (bUtc)
			{
				var oNow 			= new Date();
			    var oUtc 			= new Date(oNow.getTime() + oNow.getTimezoneOffset() * 60000);

				if (bUtc)
				{
					// Clone date to removing references
					return _createTimeZoneJSInUTC();
				}

				// Apply application time-zone
				return _createTimeZoneJS();
			};

			/**
			 * Format day with 'Today' or 'Yesterday' labels
			 *
			 * @param sFormatedDate
			 *            String - the begin of date formated "mm/dd/yy ww"
			 * @param dDate
			 *            Date - the date
			 * @return the date with formated time "mm/dd/yy Today"
			 */
			var _formatSimpleDay = function (sFormatedDate, dDate)
			{
				var bSimpleDay = (sFormatedDate.indexOf("wt") != -1);

				if (sFormatedDate && (sFormatedDate.indexOf("ww") != -1 || bSimpleDay))
				{
					var sText = null;
					// Current Date
					var dNow = _getCurrentDate();

					// Today
					if (dNow.getDate() === dDate.getDate())
					{
						sText = _locale.get(_oConfig.date.todayLabel);
					}

					// Yesterday
					if ((dNow.getDate() - 1) === dDate.getDate())
					{
						sText = _locale.get(_oConfig.date.yesterdayLabel);
					}

					if(bSimpleDay)
					{
						return sText || sFormatedDate.replace("wt", "");
					}

					return sFormatedDate.replace("ww", _util.setStrong(sText));
				}

				return sFormatedDate;
			};

			/**
			 * Get Valid Date Format
			 *
			 * @param sFormat
			 *            String - the format
			 * @return the format validated
			 */
			var _getValidDateFormat = function (sFormat)
			{
				if (sFormat.indexOf("yyyy") != -1)
				{
					return sFormat.replace("yyyy", "yy");
				}

				return sFormat;
			};

			/**
			 * Format With Zero
			 *
			 * @param iValue
			 *            Number - the value
			 * @return return a string with zero when value lower than ten
			 */
			var _formatWithZero = function (iValue)
			{
				if (iValue < 10)
				{
					return "0" + iValue;
				}

				return iValue;
			};

			/**
			 * Get Hours
			 *
			 * @param iHours
			 *            Number - the hours
			 * @return the hours on 1-12 format
			 */
			var _getHour = function (iHours)
			{
				if (iHours == 0)
				{
					return 12;
				}

				if (iHours < 13)
				{
					return iHours;
				}

				return iHours - 12;
			};

			/**
			 * Format Time Get a format string and replace the keys with the
			 * data from date parameter
			 *
			 * @param sFormatedDate
			 *            String - the begin of date formated "mm/dd/yy h:i:sA"
			 * @param dDate
			 *            Date - the date
			 * @return the date with formated time "mm/dd/yy 8:12:54AM"
			 */
			var _formatTime = function (sFormatedDate, dDate)
			{
				var iHours 			= '';
				var iMinutes 		= '';
				var iSeconds		= '';
				var iMiliSeconds	= '';

				if (!_util.isNullOrUndefined(dDate))
				{
					iHours 			= dDate.getHours();
					iMinutes 		= dDate.getMinutes();
					iSeconds		= dDate.getSeconds();
					iMiliSeconds	= dDate.getMilliseconds();
				}

				// Hour
				if (sFormatedDate.indexOf("hh") != -1)
				{
					sFormatedDate = sFormatedDate.replace("hh", iHours);
				}
				else if (sFormatedDate.indexOf("h") != -1)
				{
					sFormatedDate = sFormatedDate.replace("h", _getHour(iHours));
				}

				// Minutes
				if (sFormatedDate.indexOf("i") != -1)
				{
					sFormatedDate = sFormatedDate.replace("i", _formatWithZero(iMinutes));
				}

				// Seconds
				if (sFormatedDate.indexOf("s") != -1)
				{
					sFormatedDate = sFormatedDate.replace("s", _formatWithZero(iSeconds));
				}

				// Milliseconds
				if (sFormatedDate.indexOf("fff") != -1)
				{
					sFormatedDate = sFormatedDate.replace("fff", iMiliSeconds == 0 ? "000" : iMiliSeconds);
				}

				// Period
				if (sFormatedDate.indexOf("a") != -1)
				{
					sFormatedDate = sFormatedDate.replace(new RegExp("a(\s|$)"), iHours >= 12 ? "'pm'" : "'am'");
				}
				else if (sFormatedDate.indexOf("A") != -1)
				{
					sFormatedDate = sFormatedDate.replace(new RegExp("A(( )|$)"), iHours >= 12 ? "'PM '" : "'AM '");
				}

				//Small Seconds and AM/PM
				if (sFormatedDate.indexOf("z") != -1)
				{
					sFormatedDate = sFormatedDate.replace(new RegExp("z(\s|$)"),"");
					var aDate 		= sFormatedDate.split(":");
					var aDateLength = aDate.length;
					var sHours = "";

					if (iHours >= 12) {
						sHours = "PM";
					} else {
						sHours = "AM";
					}
					aDate[(aDateLength - 1)] = "'<small>'" + aDate[(aDateLength - 1)] + "'" + sHours + "</small>'"
					sFormatedDate =  aDate.join(":");
				}

				return sFormatedDate;
			};

			/**
			 * Apply Time-zone Offset
			 *
			 * @param dDate
			 *            Date - the date
			 * @param iTimezoneOffset
			 *            Number - the time-zone offset (minutes)
			 */
			var _applyTimezoneOffset = function (dDate, iTimezoneOffset)
			{
				// Apply time zone offset when different of null, undefined and zero
				if (iTimezoneOffset)
				{
					dDate.setMinutes(dDate.getMinutes() + iTimezoneOffset);
				}

				return dDate;
			};

			/**
			 * Get Time-zone ID
			 *
			 * @param oTimeZone
			 *            Object - the time zone object null/undefined, true or
			 * @return the Time Zone ID
			 */
			var _getTimezoneId = function (oTimeZone)
			{
				if (_util.isNullOrUndefined(oTimeZone) || oTimeZone === true || oTimeZone.bUserTZ)
				{
					// Return user time-zone
					return qat.settings.user.timeZone;
				}

				if (oTimeZone.sTZId)
				{
					oTimeZone.sTZId = _convertGMT(oTimeZone.sTZId);

					// Return the specific time-zone
					return oTimeZone.sTZId;
				}

				return _oConfig.date.UTCTimeZone;
			};

			/**
			 * Get Time-zone Offset (Minutes)
			 *
			 * @param oTimeZone
			 *            Object - the time zone object null/undefined, true or
			 *            bUserTZ equals true: apply user time-zone iTZMinutes:
			 *            (Number) the time-zone offset (minutes) to apply on
			 *            date
			 * @return the offset
			 */
			var _getTimezoneOffset = function (oTimeZone)
			{
				if (_util.isNullOrUndefined(oTimeZone) || oTimeZone === true || oTimeZone.bUserTZ)
				{
					// Return user current time-zone
					return - (new timezoneJS.Date(qat.settings.user.timeZone).getTimezoneOffset());
				}

				if (oTimeZone.iTZMinutes && $.isNumeric(oTimeZone.iTZMinutes))
				{
					// Return the specific time-zone
					return parseInt(oTimeZone.iTZMinutes, 10);
				}

				if (oTimeZone.sTZId)
				{
					oTimeZone.sTZId = _convertGMT(oTimeZone.sTZId);

					// Return device time-zone
					if (!_util.isNullOrUndefined(timezoneJS.timezone.zones[oTimeZone.sTZId]))
					{
						return - (new timezoneJS.Date(oTimeZone.sTZId).getTimezoneOffset());
					}
				}

				return 0;
			};

			/**
			 * Date Format
			 *
			 * D - short day of week - 'Mon' to 'Sun' DD - long day of week -
			 * 'Monday' to 'Sunday' d - the day number without zero dd - the day
			 * number with zero M - short month name - 'Jan' to 'Dec' MM - long
			 * month name - 'January' to 'December' m - the month number without
			 * zero mm - the month number with zero y - the year in tow digits
			 * yy - the full year h - hour until 12 hh - hour until 23 i -
			 * minutes s - seconds a - 'am' or 'pm' marker A - 'AM' or 'PM'
			 * marker simpleDay - apply the application format date day - apply
			 * the application format with 'Today' or 'Yesterday'
			 *
			 * @param date
			 *            Date - apply time-zone String - parse date and apply
			 *            time-zone Number - create date and apply time-zone
			 * @param sFormat
			 *            String - the format - "dd/mm/yy h:i:s A"
			 * @param oTimeZone
			 *            Object - the time zone object null/undefined, true or
			 *            bUserTZ equals true: apply user time-zone iTZMinutes:
			 *            (Number) the time-zone offset (minutes) to apply on
			 *            date
			 * @return String - the date formated
			 */
			var _format = function (date, sFormat, oTimeZone)
			{
				if (_util.isNullOrUndefined(date))
				{
					return null;
				}

				var dDate = _createTimeZoneJS(date, _oConfig.date.UTCTimeZone, oTimeZone);

				// Default values for sFormat parameter
				if (!sFormat)
				{
					sFormat = qat.settings.user.dateFormat;
				}

				// Set only Today or Yesterday
				if (sFormat == "simpleDay")
				{
					sFormat = _getValidDateFormat(qat.settings.user.dateFormat) + " wt";
				}

				// Set date with Today or Yesterday Value
				if (sFormat == "day")
				{
					sFormat = _getValidDateFormat(qat.settings.user.dateFormat) + " ww";
				}
				else
				{
					sFormat = _getValidDateFormat(sFormat);
				}

				sFormat = _formatTime(sFormat, dDate); // Format hour, minutes, seconds and period
				sFormat = $.datepicker.formatDate(sFormat, dDate); // Format the date: year, month and day
				sFormat = _formatSimpleDay(sFormat, dDate); // Set today or Yesterday

				return sFormat;
			};

			/**
			 * 	Return a formated text based on the difference between two date objects or milliseconds passed,
			 * 	or an object containing days, hours, minutes and seconds if the bReturnObject param is true.
			 * 	Will return null if the difference is less than 1.
			 *
			 *	@param startDate	- Date object / Long
			 *	@param endDate		- Date object / Long
			 *	@param bReturnObject	- Boolean
			 */
			var _timeBetween = function(startDate, endDate, bReturnObject)
			{
			    if(startDate < endDate )
			    {
					var difference_ms 	= endDate - startDate; // Calculate the difference in milliseconds

					return _msToHumanReadable(difference_ms, bReturnObject);
				}
				else
				{
					return null;
				}
			};

			/**
			 *	Return a formated text based on the milliseconds passed, or an object containing days, hours, minutes
			 *	and seconds if the bReturnObject param is true.
			 *	Will return a default message if milliseconds param is less than 1000.
			 *
			 *	@param milliseconds		- Integer / Long
			 *	@param bReturnObject	- Boolean
			 */
			var _msToHumanReadable = function(milliseconds, bReturnObject)
			{
				if (milliseconds !== parseInt(milliseconds))
				{
					return null;
				}

				var oTime 		= {};
				milliseconds 	= milliseconds/1000;

				// Return default message if it's less than a second
				if (!bReturnObject && milliseconds <= 0)
				{
					return "0 " + $.qat.locale.get("commons.time.seconds");
				}

				oTime.seconds 	= Math.floor(milliseconds % 60);
				milliseconds 	= milliseconds/60;
				oTime.minutes 	= Math.floor(milliseconds % 60);
				milliseconds 	= milliseconds/60;
				oTime.hours 	= Math.floor(milliseconds % 24);
				oTime.days 		= Math.floor(milliseconds/24);

				if(bReturnObject)
				{
					return oTime;
				}

				//Build a time interval message
				var sTimeMessage = "";

				//set hours
				sTimeMessage  = oTime.hours == 1 ? oTime.hours + " " + $.qat.locale.get("commons.time.hour") + " " : "";
				sTimeMessage += oTime.hours > 1 ? oTime.hours + " " + $.qat.locale.get("commons.time.hours") + " " : "";

				//set minutes
				sTimeMessage += oTime.minutes == 1 ? oTime.minutes + " " + $.qat.locale.get("commons.time.minute") + " " : "";
				sTimeMessage += oTime.minutes >  1 || oTime.days > 0 || (oTime.hours > 0 && oTime.minutes == 0) ? oTime.minutes + " " + $.qat.locale.get("commons.time.minutes") + " " : "";

				if(oTime.days > 0)
				{
					// set days
					sTimeMessage = oTime.days + " " + (oTime.days == 1 ? $.qat.locale.get("commons.time.day") + " "  :  $.qat.locale.get("commons.time.days") + " ") + sTimeMessage;
				}
				else
				{
					// set seconds
					sTimeMessage  += oTime.seconds == 1 ? oTime.seconds + " " + $.qat.locale.get("commons.time.second") + " " : "";
					sTimeMessage  += oTime.seconds > 1 || oTime.seconds == 0 ? oTime.seconds + " " + $.qat.locale.get("commons.time.seconds") + " " : "";
				}

				return sTimeMessage
			};

			/**
			 * When GMT id is returned, this code concatenate it with prefixed Etc/.
			 * Since Etc/ considers Greenwich west as positive value, the ID sign is changed
			 * to match with international standard.
			 *  For instance : GMT-5 is switched to Etc/GMT+5 and the final value stands by the
			 *  offset -5.
			 *  Code created by: Raphael Constantino/Pedro Cardoso to fix TTP 38261.
			 */
			var _convertGMT = function (timeZoneId)
			{

				if ($.qat.isNullOrUndefined(timeZoneId))
				{
					return timeZoneId;
				}

				// Validate for wrong GMT Time Zone ID
				if($.qat.isNullOrUndefined(timezoneJS.timezone.zones[timeZoneId])
						&& timeZoneId.match(/^GMT[+-][1-9][0-4]?$/g))
				{
					// Convert GMT-13 to GMT+0
		            if (timeZoneId.match(/^GMT[-][1][3]?$/g))
		            {
		            	return "Etc/" + timeZoneId.replace("-13", "+0");
		            }
		            // Convert GMT- to GMT+
		            if (timeZoneId.match(/^GMT[-][1-9][0-4]?$/g))
		  			{
		            	return "Etc/" + timeZoneId.replace("-", "+");
		  			}
		            // Convert GMT+ to GMT-
		            if(timeZoneId.match(/^GMT[+][1-9][0-4]?$/g))
		  			{
		  				return "Etc/" + timeZoneId.replace("+", "-");
		  			}
				}

				return timeZoneId;
			}

			return (
			{
				epochTime :
				{
					currentUTC			: _getCurrentEpochInUTC,
					current				: _getCurrentEpoch,
					convertFromValue	: _getEpochTime
				},

				timeBetween					: _timeBetween,
				msToHumanReadable			: _msToHumanReadable,
				format						: _format,
				getCurrentDate				: _getCurrentDate,
				isLess						: _isLess,
				convertGMT					: _convertGMT,
				createTimeZoneJS			: _createTimeZoneJS,
				convertDateInSecToMiliSec	: _convertDateInSecToMiliSec,
				createTimeZoneJSInUTC 		: _createTimeZoneJSInUTC,
			});
		})();

		/**
		 * Defines common enum-related functionality.
		 */
		var _enums = (function()
		{
			/**
			 * Fetch the full ENUM Object.
			 *
			 * @param {String} sEnumName - The ENUM full name.
			 * @return {Array} - The ENUM object (Array format).
			 */
			var _fetchByName = function(sEnumName)
			{
				if(_util.isNullOrUndefined(qat.settings.enums)
						|| _util.isNullOrUndefined(qat.settings.enums[sEnumName]))
				{
					return false;
				}
				return qat.settings.enums[sEnumName];
			};

			/**
			 * Filter the enum by the passed value/filter and return the desired key.
			 *
			 * @param {String} sEnumName - The ENUM full name.
			 * @param {String} sFilter - The filter key to be used.
			 * @param {String} sValue - The value to filter by.
			 * @param {String} sReturnValue - The wanted key.
			 * @return {String} - The value of the wanted key.
			 */
			var _fetchFiltered = function(sEnumName, sFilter, sValue, sReturnValue)
			{
				var oEnum = _fetchByName(sEnumName);

				if(!oEnum || _util.isNullOrUndefined(sValue))
				{
					return false;
				}

				var aFiltered = [];

				$.each(oEnum, function(o, i)
				{
				    if(i[sFilter] === sValue)
				    {
				    	aFiltered.push(i);
				    }
				});

				if(!_util.isValidArray(aFiltered))
				{
					return false;
				}

				return aFiltered[0][sReturnValue];
			};

			/**
			 * Fetch the the ENUM label by it's value.
			 *
			 * @param {String} sEnumName - The ENUM full name.
			 * @param {String} sValue - The ENUM value to filter by.
			 * @return {String} - The ENUM label.
			 */
			var _fetchLabelByValue = function(sEnumName, sValue)
			{
				return _fetchFiltered(sEnumName, "value", sValue, "label");
			};

			/**
			 * Fetch the the ENUM labels by the values passed.
			 *
			 * @param {String} sEnumName - The ENUM full name.
			 * @param {Array} aValues - The ENUM values to filter by.
			 * @return {Array} - The ENUM labels.
			 */
			var _fetchLabelsByValues = function(sEnumName, aValues)
			{
				var a = new Array();
				var oEnum = _fetchByName(sEnumName);

				if(oEnum && _util.isValidArray(aValues))
				{
					var aFiltered = [];

					$.each(oEnum, function(o, i)
					{
						if($.inArray(i["value"], aValues) != -1)
					    {
					    	aFiltered.push(i);
					    }
					});

					if(_util.isValidArray(aFiltered))
					{
						for (i in aFiltered)
						{
							a.push(aFiltered[i]["label"]);
						}
					}
				}

				return a;
			};

			/**
			 * Fetch the the ENUM value by it's label.
			 *
			 * @param {String} sEnumName - The ENUM full name.
			 * @param {String} sLabel - The ENUM label to filter by.
			 * @return {String} - The ENUM value.
			 */
			var _fetchValueByLabel = function(sEnumName, sLabel)
			{
				return _fetchFiltered(sEnumName, "label", sLabel, "value");
			};

			/**
			 * Fetch the ENUM I18N label key by it's value.
			 *
			 * @param {String} sEnumName - The ENUM full name.
			 * @param {String} sValue - The ENUM label to filter by.
			 * @return {String} - The ENUM I18N label key.
			 */
			var _fetchI18NByLabel = function(sEnumName, sLabel)
			{
				return _fetchFiltered(sEnumName, "label", sLabel, "i18nLabelKey");
			};

			/**
			 * Internationalize the ENUM I18N label key by it's label.
			 *
			 * @param {String} sEnumName - The ENUM full name.
			 * @param {String} sValue - The ENUM label to filter by.
			 * @param {String} sDefaultValue - A default value to return if the label isn't found.
			 * @return {String} - The ENUM I18N label key
			 */
			var _internationalizeByLabel = function(sEnumName, sLabel, sDefaultValue)
			{
				var sKey = _fetchI18NByLabel(sEnumName, sLabel);

				if(!sKey)
				{
					return sDefaultValue || _locale.get(_oConfig.common.doubleHyphenLabel);
				}

				return _locale.get(sKey);
			};

			return {
				fetchByName				: _fetchByName,
				fetchLabelByValue		: _fetchLabelByValue,
				fetchLabelsByValues		: _fetchLabelsByValues,
				fetchValueByLabel		: _fetchValueByLabel,
				fetchI18NByLabel		: _fetchI18NByLabel,
				internationalizeByLabel	: _internationalizeByLabel
			};
		})();

		/**
		 * Form utilities
		 */
		var _form = (function()
		{
			/**
			 * Resets the fields in the form with the provide id.
			 *
			 * @param target
			 *            the id of the form to clear
			 */
			var _clearElements = function(target)
			{
				var $target = $("#" + target);
				$(".messaging", $target).hide();

				$target.find(':input').each(function()
				{
					$(this).removeClass("error");

					switch (this.type)
					{
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

			/**
			 * Attach the validation engine to the form
			 *
			 * @param {Object}
			 *            oDom - dom element
			 * @param {function}
			 *            fnCallback - callback function
			 */
			var _attachValidation = function(oDom, fnCallback)
			{
				$(oDom).validationEngine('attach',
				{
					validationEventTrigger : 'submit',
					onValidationComplete : function(form, status)
					{
						if (status)
						{
							if ($.isFunction(fnCallback))
							{
								fnCallback();
							}
						}
					}
				});
			};

			return {
				attachValidation	: _attachValidation,
				clearElements		: _clearElements
			};

		})();

		/**
		 * Locale methods
		 */
		var _locale = (function()
		{
			var _init = function (oMessageProperties)
			{
				jQuery.i18n.properties(
				{
					name				: null,
					mode				: _oConfig.locale.mode,
					language			: qat.settings.language,
					preLoadedProperties	: oMessageProperties
				});
			};

			/**
			 * Wrap the retrieval of localized strings in case we want to change
			 * the underlying implementation in the future.
			 *
			 * @param args
			 *            if only one argument is provided, return the lookup
			 *            result for this argument. If more then one argument is
			 *            provide, use the first value as lookup key and the
			 *            rest of the arguments as data to fill in the returned
			 *            string.
			 * @return localized message
			 */
			var _get = function ()
			{
				var args, i;

				if (arguments.length == 0)
				{
					return "";
				}

				if (arguments.length == 1)
				{
					return jQuery.i18n.prop(arguments[0]);
				}

				if (typeof (arguments[1]) === "string")
				{
					args = [];

					for (i = 1; i < arguments.length; i = i + 1)
					{
						args.push(arguments[i]);
					}

					return jQuery.i18n.prop(arguments[0], args);
				}

				return jQuery.i18n.prop(arguments[0], arguments[1]);
			};

			return{
				get		: _get,
				init	: _init
			};

		})();

		/**
		 * Local Storage methods
		 */
		var _storage = (function()
		{
			var _set = function(key, value, options)
			{
				return $.jStorage.set(key, value, options);
			};

			var _get = function(key, defaultValue)
			{
				return $.jStorage.get(key, defaultValue);
			};

			var _delete = function(key)
			{
				return $.jStorage.deleteKey(key);
			};

			var _flush = function()
			{
				return $.jStorage.flush();
			};

			return{
				set		: _set,
				get		: _get,
				delete	: _delete,
				flush	: _flush
			};

		})();


		/**
		 * Message manipulation methods.
		 */
		var _message = (function ()
		{
			/**
			 * Converts the message array of an ajax callback result object to
			 * an HTML UL list.
			 *
			 * @param messages -
			 *            array of message strings
			 * @return HTML for the message list
			 */
			var _asList = function(messages)
			{
				if (!messages || messages.length == 0)
				{
					return "";
				}
				for ( var i = 0; i < messages.length; i++)
				{
					messages[i] = "<li>" + messages[i].text + "</li>";
				}
				return "<ul class='messaging-details'>" + messages.join("") + "</ul>";
			};

			/**
			 * Get the message that came in the response.
			 *
			 * @param {Object}
			 *            oResponse - The Resonse Object.
			 * @param {String}
			 *            sDefaultMessage - The default value to be set if
			 *            response does not have Message
			 */
			var _fromResponse = function(oResponse, sDefaultMessage)
			{
				if (!_util.isNullOrUndefined(oResponse))
				{
					if (_util.isValidArray(oResponse.messageList))
					{
						return oResponse.messageList[0].text;
					}
				}

				return sDefaultMessage;
			};

			/**
			 * Hide the desired message container, if no parameter is passed, the default container is used.
			 *
			 * @param sTarget {String} - the target id
			 */
			var _hide = function(sTarget)
			{
				if(!sTarget)
				{
					sTarget = _oConfig.message.mainContainer;
				}

				$("#" + sTarget).fadeOut("slow");
			};

			/**
			 * Show a message, either as confirmation or error. This is intended
			 * to work with the general messaging area at the top of the main
			 * content pages but will work with other targets as well.
			 *
			 * @param the
			 *            id of the target element to display the message into
			 * @param the
			 *            message
			 * @param the
			 *            message type, either "confirm" or "error".
			 */
			var _show = function(oMessage)
			{
				var _defaults =
				{
					message: "",
					type	: "error",
					target	: _oConfig.message.mainContainer
				};

				// To avoid extend null instead the correct div's id
				if ($.qat.isNullOrUndefined(oMessage.target))
				{

					oMessage.target = undefined;
				}

				oMessage = $.extend({}, _defaults, oMessage);

				$('html, body').animate({ scrollTop : 0 }, 'slow');

				if ($("#" + oMessage.target).length == 0)
				{
					// If the target does not exists
					oMessage.target = _defaults.target;

					var oSystemMessaging = '<div id="'+ oMessage.target +'" class="messaging" style="display: none;"><span	class="message"></span><a href="" class="remove">'
							+ _locale.get(_oConfig.message.closeLabel) + '</a></div>';
					if($(_oConfig.content.tabs).length != 0)
					{
						$(_oConfig.content.tabs).before(oSystemMessaging);
					}
					else
					{
						$(_oConfig.content.inner).before(oSystemMessaging);
					}
				}

				var oMessageTarget = $("#" + oMessage.target);

				oMessageTarget.fadeIn(200, function()
				{
					var _this = $(this);
					_this.find(".message").removeClass("ui-state-error").html(oMessage.message);

					if ("confirm" == oMessage.type)
					{
						_this.removeClass("ui-state-error");
						_this.addClass("ui-state-highlight");
					}
					else
					{
						_this.removeClass("ui-state-highlight");
						_this.addClass("ui-state-error");
					}

				})
				.find(".remove").click(function(e)
				{
					e.preventDefault();
					$(this).parent().fadeOut("slow");
					return false;
				});
			};

			return {
				asList			: _asList,
				fromResponse	: _fromResponse,
				hide			: _hide,
				show			: _show
			};
		})();

		/**
		 * Page Loader methods
		 */
		var _pageLoader = (function()
		{
			var _oLoadDefaults =
			{
				url					: null,
				$content			: null,	// Jquery Object
				$link				: null, // Jquery Object
				callback			: null,
				bUpdateUrl			: true,
				bStartProgressBar	: true,
				oMessage			: null
			};

			var _getStaticURL = function ()
			{
				return "/" + qat.settings.serviceType.toLowerCase() + "/";
			};

			var _activeMenu = function ($menu, $item)
			{
				$menu.find("a.active").removeClass("active");

				if ($item.length)
				{
					$item.addClass("active");
				}
			};

			/**
			 * Flow control for ajax calls
			 */
			var _ajaxLoad = function(oLoadConfig)
			{
				var _ajaxLoadCallback = function(oResponse)
				{
					oLoadConfig.$content.html(oResponse);

					// Message to be shown after the load completes
					if(oLoadConfig.oMessage && oLoadConfig.oMessage.message)
					{
						_message.show(oLoadConfig.oMessage);
					}
					// Callback for page loading
					if ($.isFunction(oLoadConfig.callback))
					{
						oLoadConfig.callback();
					}
				}

				if(oLoadConfig.isViewController)
				{
					_ajax.post({
						bInitialLoad: oLoadConfig.bInitialLoad,
						bShowMessage: false,
						sUrl: oLoadConfig.url,
						bBlockScreen : oLoadConfig.bBlockScreen,
						fnCallback: _ajaxLoadCallback,
						oRequest: new InquiryPaginationRequest()
					});
					return;
				}

				_ajax.get({
					bCache: false,
					bJson: false,
					bInitialLoad: oLoadConfig.bInitialLoad,
					bShowMessage: false,
					sUrl: oLoadConfig.url,
					bBlockScreen : oLoadConfig.bBlockScreen,
					fnCallback: _ajaxLoadCallback
				});
			};

			/**
			 * Utility method to get/set the current page
			 */
			var _currentPage = function (sCurrentPage)
			{
				if(_util.isNullOrUndefined(sCurrentPage))
				{
					if($.address.path() === _getStaticURL())
					{
						return null;
					}
					return $.address.path().split(_getStaticURL())[1];
				}

				$.address.value(_getStaticURL() + sCurrentPage);
			};

			var _load = function (oLoadConfig)
			{
				oLoadConfig = $.extend({}, _oLoadDefaults, oLoadConfig);

				if(_util.isNullOrUndefined(oLoadConfig.url))
				{
					_message.show({message: _locale.get(_oConfig.common.errorLabel), type: "error"});
					return false;
				}

				var _loadThis = function()
				{
					if (oLoadConfig.$link && oLoadConfig.$link.length)
					{
						_activeMenu(oLoadConfig.$link.parents("ul"), oLoadConfig.$link);
					}
					else
					{
						var splitedUrl 		= oLoadConfig.url.split("?")[0];
						var defaultAppMenu 	= $("#qat-menu");
						oLoadConfig.$link 	= defaultAppMenu.find("a[href^='" + splitedUrl + "']");

						if (!oLoadConfig.$link.length)
						{
							splitedUrl 			= splitedUrl.split("/")[0];
							oLoadConfig.$link 	= defaultAppMenu.find("a[href^='" + splitedUrl + "']");
						}

						_activeMenu(defaultAppMenu, oLoadConfig.$link);
					}

					if (oLoadConfig.bUpdateUrl)
					{
						_currentPage(oLoadConfig.url);
					}

					_title(_currentPage());
					_ajaxLoad(oLoadConfig);
				};

				if (oLoadConfig.bStartProgressBar)
				{
					_progressBar.startGlobal(_loadThis);
				}
				else
				{
					_loadThis();
				}

			};

			// TODO - check usability
			var _loadTab = function (url, $tabContent)
			{
				_currentPage(url);
				_ajaxLoad({ url: url, $content: $tabContent });
			};

			var _getQueryString = function (parameterNames)
			{
				var parameterName;
				var length;
				var value;
				var arr;
				var i;

				if (!parameterNames)
				{
					parameterNames = _parameterNames();
				}

				length = parameterNames.length;
				arr = [];

				for (i = 0; i < length; i = i + 1)
				{
					parameterName = parameterNames[i];
					value = $.address.parameter(parameterName);

					if (value)
					{
						arr.push(parameterName + "=" + value);
					}
				}

				return arr.join("&");
			};

			var _parameterNames = function (invalidParameters)
			{
				var parameterNames = $.address.parameterNames();
				var length = parameterNames.length;
				var parameterName;
				var arr = [];
				var i = 0;

				for (; i < length; i = i + 1)
				{
					parameterName = parameterNames[i];

				    if ($.inArray(parameterName, invalidParameters) == -1)
				    {
				    	arr.push(parameterName);
				    }
				}

				return invalidParameters && invalidParameters.length ? arr : parameterNames;
			};

			/**
			 * Utility method to set one parameter
			 */
			var _setParameter = function (parameter, value)
			{
				var obj = {};
				obj[parameter] = value;
				_setParameters(obj);
			};

			/**
			 * Utility method to set N parameters
			 */
			var _setParameters = function (parameters)
			{
				$.address.history(false);

				for (var i in parameters)
				{
					$.address.parameter(i, parameters[i]);
				}

				$.address.history(true);
			};

			var _tabs = function ($ul, $tabContent, fnUrlInterceptor, sInitTabUrl, sProgressBarType)
			{
				if (_util.isNullOrUndefined(sProgressBarType))
				{
					sProgressBarType = "startGlobal";
				}

				$ul.on("click", "li a", function(e)
				{
					e.preventDefault();
					var _this = $(this);
					var	_href;
					var _tabUrl;

					var fnLoad = function ()
					{
						_currentPage(_this.attr("href"));
						_activeMenu($ul, _this);
						_href = fnUrlInterceptor ? fnUrlInterceptor(_this, e) : _this.attr("href");

						if (_this.hasClass("external-call"))
						{
							$tabContent = $("#load");
						}

						_ajaxLoad({ url: _href, $content: $tabContent, isViewController: true, bBlockScreen : true });
					}

					if (sProgressBarType === "startGlobal")
					{
						_progressBar.startGlobal(fnLoad);
					}
					else if (sProgressBarType === "start")
					{
						_progressBar.start();
						fnLoad();
					}

				});

				// load first tab
				var $initTab = $ul.find("a[href='" + _currentPage() + "']");

				if (!$initTab.length)
				{

					if (sInitTabUrl === false)
					{
						return;
					}

					if (!_util.isNullOrUndefined(sInitTabUrl))
					{
						// Set Default parameter based on parameter
						$initTab = $ul.find("a[href='" + sInitTabUrl + "']");
					}
					else
					{
						// Add default as first value when no parameter specified
						$initTab = $ul.find("a:first");
					}

					// Always set the correct url
					_currentPage($initTab.attr("href"));
				}

				var	_href = fnUrlInterceptor ? fnUrlInterceptor($initTab) : $initTab.attr("href");
				var _query = _getQueryString();

				_activeMenu($ul, $initTab);
				_ajaxLoad({ url: _href + (_query ? ("?" + _query) : ""), bInitialLoad: (_query) ? false : true, $content: $tabContent, isViewController: true });
			};

			var _title = function (currentPage)
			{
				if(currentPage)
				{
					if (currentPage.indexOf("/") != -1)
					{
						currentPage = currentPage.split("/");
						currentPage = currentPage[0];
					}
					currentPage = _locale.get("commons.title." + currentPage);
				}
				else
				{
					currentPage = "";
				}

				var _titlePrefix		= _locale.get(_oConfig.pageLoader.qatLabel);
				var _titleService		= _locale.get(_oConfig.pageLoader.serviceLabel + qat.settings.serviceType.toLowerCase());
				var _titleSeparator		= " - ";

				$.address.title([_titlePrefix, _titleService, currentPage].join(_titleSeparator));
			};

			return {
				currentPage		: _currentPage,
				load			: _load,
				getQueryString	: _getQueryString,
				setParameter	: _setParameter,
				setParameters	: _setParameters,
				tabs			: _tabs,
				activeMenu		: _activeMenu,
				parameterNames	: _parameterNames
			};
		})();

		/**
		 * Progress bar manipulation methods.
		 */
		var _progressBar = (function()
		{

			/**
			 * Initialize the progressbar dialog. This requires the configured element.
			 */
			var _initProgressBar = function ()
			{
				$(_oConfig.progressBar.oLoading).show().dialog(
				{
					autoOpen 	: false,
					modal 		: true,
					dialogClass : 'loading',
					resizable 	: false
				});
			};

			/**
			 * Starts the Global Progress Bar.
			 *
			 * @param {Function} -
			 *            A callBack function.
			 */
			var _startGlobalProgressBar = function(fnCallback)
			{

				$('html, body').animate({ scrollTop: 0 }, 'fast');
				_oConfig.progressBar.oPreload.fadeIn(300, function ()
				{
					if(_oConfig.progressBar.oLoading.dialog("isOpen"))
				    {
						_stopProgressBar();
				    }

				    _oConfig.progressBar.initiated = false;

				    if ($.isFunction(fnCallback))
				    {
				    	fnCallback();
				    }
				});

			};

			var _stopGlobalProgressBar = function()
			{
				_oConfig.progressBar.oPreload.fadeOut(300);
				_oConfig.progressBar.initiated = true;
			};

			/**
			 * Hide and start the progressbar. This requires the "#loading" and
			 * "#progressbar" elements to be present. The progress of the bar is
			 * currently hard-coded.
			 */
			var _startProgressBar = function(sMessage)
			{
				if (_oConfig.progressBar.initiated)
				{
					$('.formError').remove();

					if (sMessage == null)
					{
						$('h5', _oConfig.progressBar.oLoading).text(_locale.get(_oConfig.progressBar.updateLabel));
					}
					else
					{
						$('h5', _oConfig.progressBar.oLoading).text(_locale.get(sMessage));
					}

					if (!_oConfig.progressBar.oLoading.dialog('isOpen'))
					{
						_oConfig.progressBar.oLoading.dialog('open');
						_oConfig.progressBar.oProgressbar.progressbar(
						{
							value : 1
						});

						(function progress()
						{
							var value = _oConfig.progressBar.oProgressbar.progressbar('value');
							_oConfig.progressBar.oProgressbar.progressbar('value', value + 1);

							if (value < 100)
							{
								setTimeout(progress, 200);

								if (value == 99)
								{
									_oConfig.progressBar.oProgressbar.progressbar(
									{
										value : 1
									});
								}
							}
						}());
					}
				}
			};

			/**
			 * Complete, stop and hide the progressbar. This requires the "#loading" and
			 * "#progressbar" elements to be present.
			 *
			 * @param recordcount
			 *            if a value greater than 0 is provided, the progressbar will
			 *            complete and stop after a delay instead of immediately
			 */
			var _stopProgressBar = function()
			{
				if (_oConfig.progressBar.initiated)
				{
					if (_oConfig.progressBar.oProgressbar.is(":visible"))
					{
						_oConfig.progressBar.oProgressbar.progressbar('value', 100);
					}

					setTimeout(function()
					{
						_oConfig.progressBar.oLoading.dialog("close");
						_oConfig.progressBar.oFullScreen.hide();
					}, 50);

				}
			};

			return {
				init		: _initProgressBar,
				startGlobal	: _startGlobalProgressBar,
				stopGlobal	: _stopGlobalProgressBar,
				start		: _startProgressBar,
				stop		: _stopProgressBar
			};
		})();


		var _listener = (function()
		{
			/**
			 *	@Configuration
			 */
			var _oListener = {};

			/**
			 *	@Class
			 */
			var Listeners = function(oParam)
			{
				this.evt 		= oParam.evt;
				this.arguments 	= oParam.arguments;
			};

			/**
			 *	Method used to check if all dependences complete
			 *	@Param - sEventName
			 * 		String value for name of event
			 *
			 */
			var _isArgumentsDone = function(sEventName)
			{
				if (!_oListener[sEventName])
				{
					return;
				}

				for (var i in _oListener[sEventName].arguments)
				{
					if (!_oListener[sEventName].arguments[i])
					{
						return;
					}
				}

				return true;
			};

			/**
			 *	Method used get All arguments and status of each one
			 *	@Param - sEventName
			 * 		String value for name of event
			 *
			 */
			var _getEventStatus = function(sEventName)
			{
				return _oListener[sEventName];
			};

			/**
			 *	Method used to create a wait event
			 *	@Param - oParam
			 *		- @Attribute - arguments
			 * 			String array filled with arguments to wait
			 *		- @Attribute - eventName
			 * 			String value for name of event
			 *		- @Attribute - fnCallback
			 * 			Function to be executed when all arguments are done
			 *
			 */
			var _wait = function(oCOnfig)
			{
				var oParam = _util.extend({}, oCOnfig);

				// Listen for the event.
				var fnEventFunction = function (e)
				{
					if (_isArgumentsDone(oParam.eventName) && $.isFunction(oParam.fnCallback))
					{
						if($.isFunction(oParam.fnCallback))
						{
							oParam.fnCallback();
						}
						_destroy(oParam);
					}
				};

				// Create the event.
				_oListener[oParam.eventName] = new Listeners(
				{
					evt 			: document.createEvent("Event"),
					arguments 		: {},
					fnEventFunction : fnEventFunction
				});

				// Define a name for event
				_oListener[oParam.eventName].evt.initEvent(oParam.eventName, true, true);

				if (oParam.arguments && _util.isValidArray(oParam.arguments))
				{
					$.each(oParam.arguments, function(i, argument){
						_oListener[oParam.eventName].arguments[argument] = false;
					});
				}

				document.addEventListener(oParam.eventName, fnEventFunction, false);
			};

			var _destroy = function(oParam)
			{
				document.removeEventListener(oParam.eventName, _oListener[oParam.eventName].fnEventFunction, false);
				delete _oListener[oParam.eventName];
				oParam.fnCallback = null;
			};

			/**
			 *	Method used to mark a dependence such as completed
			 *	@Param - oParam
			 *		- @Attribute - arguments
			 * 			String array filled with arguments completed
			 *		- @Attribute - eventName
			 * 			String value for name of event
			 *
			 */
			var _notify = function(oParam)
			{
				if (!_oListener[oParam.eventName])
				{
					return;
				}
				// target can be any Element or other EventTarget.
				for (var i in oParam.arguments)
				{
					_oListener[oParam.eventName].arguments[oParam.arguments[i]] = true;
				}

				document.dispatchEvent(_oListener[oParam.eventName].evt);
			};

			return {
				wait 			: _wait,
				notify 			: _notify,
				getEventStatus 	: _getEventStatus
			};
		})();


		return {
			/* Utility methods */
			extend				: _util.extend,
			getEnumFromValue	: _util.getEnumFromValue,
			hasRole				: _util.hasRole,
			isNullOrUndefined	: _util.isNullOrUndefined,
			isValidArray		: _util.isValidArray,
			isValidPreLoad		: _util.isValidPreLoad,
			isValidResponse		: _util.isValidResponse,
			nTruncate			: _util.nTruncate,
			replaceAll			: _util.replaceAll,
			savePropertyProfile : _util.savePropertyProfile,
			setGeneralConfig	: _setGeneralConfig,

			listener 			: _listener,

			/* Ajax */
			ajax				: _ajax,
			pageLoader			: _pageLoader,

			/* Monitor */
			monitor				: _monitor,

			/* Formatters */
			date				: _date,

			/* ENUMs */
			enums				: _enums,

			/* Locale */
			locale				: _locale,

			/* Storage */
			storage				: _storage,

			/* UI Helpers */
			form				: _form,
			closeActionDialog	: _util.closeActionDialog,
			launchActionDialog	: _util.launchActionDialog,
			message				: _message,
			progressBar			: _progressBar
		};

	})();

})(jQuery);
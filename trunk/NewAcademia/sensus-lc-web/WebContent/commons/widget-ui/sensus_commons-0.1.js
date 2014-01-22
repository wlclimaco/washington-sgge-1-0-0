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
(function($) {

	$.sc = (function() {

		/**
		 * Load properties of system from method fetchProperties
		 */
		var fnReloadProperties = function() {

			var bHeader = false;

			if (sensus.settings) {

				bHeader = true;

			}

			$.ajax({
						dataType : 'json',
						type : "POST",
						url : 'api/settings/fetchproperties',
						async : false,
						success : function(oData) {

							sensus.settings = {};

							// Validating response from fetchProperties
							if (!(oData === null) || !(oData === undefined)) {

								if (!(oData.settings === null)
										|| !(oData.settings.length)) {

									// add properties to namespace
									for ( var iKey in oData.settings) {

										if ((oData.settings[iKey]
												.hasOwnProperty('propertyEnum'))
												&& (oData.settings[iKey]
														.hasOwnProperty('propertyValue'))) {
											sensus.settings[oData.settings[iKey]['propertyEnum']] = oData.settings[iKey]['propertyValue'];
										}
									}
								}

								// Data from controller properties
								if (oData.systemProperties != null) {

									if (!(oData.systemProperties["timeZoneHours"] === null)
											|| !(oData.systemProperties["timeZoneHours"] === undefined)) {
										sensus.settings.TIME_ZONE_HOURS = oData.systemProperties["timeZoneHours"];
									}
									if (!(oData.systemProperties["timeZoneShort"] === null)
											|| !(oData.systemProperties["timeZoneShort"] === undefined)) {
										sensus.settings.TIME_ZONE_SHORT = oData.systemProperties["timeZoneShort"];
									}
									if (!(oData.systemProperties["responseDateTime"] === null)
											|| !(oData.systemProperties["responseDateTime"] === undefined)) {
										sensus.settings.responseDateTime = oData.systemProperties["responseDateTime"];
									}
								}

								if (!(oData.enums === null)
										|| !(oData.enums === undefined)) {
									sensus.settings.enums = oData.enums;
								}
								if (!(oData.enums === null)
										|| !(oData.enums === undefined)) {
									sensus.settings.enums = oData.enums;
								}
								if (!(oData.userContext === null)
										|| !(oData.userContext === undefined)) {
									sensus.settings.userContext = oData.userContext;
								}

								sensus.settings.DATE_FORMAT = sensus.settings.DATE_FORMAT.toLowerCase().replace("yyyy", "yy");
								sensus.settings.dateFormatMask = sensus.settings.DATE_FORMAT;
								sensus.settings.monitor = sensus.settings.MONITOR_REQUEST;

								// Data from ui settings
								sensus.settings.longRunningProcessTime = oData.uiProperties["longrunningprocess.url.longRunningProcessTime"];
								sensus.settings.slcVersion = oData.uiProperties["lc.release"];
								sensus.settings.slcBuildVersion = oData.uiProperties["lc.build.number"];
								sensus.settings.checkRniTime = oData.uiProperties["longrunningprocess.url.checkRniTime"];
								sensus.settings.smartpointUrl = oData.uiProperties["slc.smartpoint.url"];

								sensus.settings.map = {
									aerial : {
										name : oData.uiProperties["slc.map.layer.aerial.name"],
										url : oData.uiProperties["slc.map.layer.aerial.url"]
												.split(","),
										attribution : oData.uiProperties["slc.map.layer.aerial.attribution"]
									},
									mqosm : {
										name : oData.uiProperties["slc.map.layer.mqosm.name"],
										url : oData.uiProperties["slc.map.layer.mqosm.url"]
												.split(","),
										attribution : oData.uiProperties["slc.map.layer.mqosm.attribution"]
									}
								};

								sensus.settings.units =
								{
										kWh	: oData.uiProperties["slc.units.kWh"],
										mWh	: oData.uiProperties["slc.units.mWh"],
										gWh	: oData.uiProperties["slc.units.gWh"],
										tWh	: oData.uiProperties["slc.units.tWh"],
										pWh	: oData.uiProperties["slc.units.pWh"]
								};

							}
							if (!bHeader) {

								$.ajax({
									type : "POST",
									url : './header?locale='
											+ sensus.settings.LANGUAGE,
									async : false,
									success : function(html) {

										$('#doc1').html(html);

									}
								});

							}

						}
					});

		}

		fnReloadProperties();

		/*
		 * Initialize language settings.
		 */
		jQuery.i18n.properties({
			name : 'messages',
			path : "api/settings/fetchmessages/",
			mode : 'map',
			encoding : 'ISO-8859-1',
			language : sensus.settings.LANGUAGE
		});

		/*
		 * Set the locale from the sensus settings.
		 */
		$.getScript("thirdparty/globalize/custom/cultures/globalize.culture."
				+ sensus.settings.LANGUAGE.replace("_", "-") + ".js",
				function() {

					Globalize.culture(sensus.settings.LANGUAGE
							.replace("_", "-"));

				});

		var oMonitorDeferred = $.Deferred();
		var oMainMenu = $("#sensus-menu");
		var sAjaxUrl = null;
		var _new = function(obj) {

			return Object.create(obj);

		};

		/**
		 * Util functions for the system
		 */
		var _util = (function() {

			/**
			 * Get selections ids.
			 *
			 * @return {Objects} - Whith ids and not ids.
			 */
			var _getRowsSelection = function() {
				var aNids = [];
				var aIds = [];

				if (sensus.widgets.datatable.isAllRows) {
					aNids = sensus.widgets.datatable.deselectedRows;
				} else {
					aIds = sensus.widgets.datatable.selectedRows;
				}

				return objSelection = {

					aIds : aIds,

					aNIds : aNids

				}
			};

			/**
			 * Validate if a property is null or undefined.
			 *
			 * @param {Object} -
			 *            The property to be validated.
			 * @return {Boolean} - Whether the property is valid or not.
			 */
			var _isNullOrUndefined = function(oProperty) {

				if (oProperty === null || oProperty === undefined) {

					return true;

				}

				return false;

			};

			/**
			 * Validation to check if is an Array and if it has Values @ returns
			 * {Boolean}
			 */
			var _isValidArray = function(oProperty) {

				if (!$.sc.isNullOrUndefined(oProperty) && $.isArray(oProperty)
						&& oProperty.length) {
					return true;
				}

				return false;
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
			};

			/**
			 * Launches the action dialog with the action id and the
			 * corresponding dialog settings. This function expects the dialog
			 * to be launched from the "#action_dialog" element. For an example
			 * of dialog settings, see pages/smartpoint_actions.js.
			 *
			 * @param sActionId
			 *            the string code for the action. This is only needed to
			 *            display a warning note for unimplemented actions.
			 * @param the
			 *            settings for the particular action dialog. These
			 *            settings are typically defined in the main namespace
			 *            for the containing page.
			 */
			var _launchActionDialog = function(sActionId, oDialogSettings, sDialogId, oPosition) {

				if (!oDialogSettings) {

					alert($.sc.locale("widget.action.unimplemented", sActionId));

					return;
				}

				oDialogSettings.actionId = sActionId;

				if (oDialogSettings.width == null) {

					oDialogSettings.width = 500;

				}

				$.sc.clearFormElements(oDialogSettings.form);

				var actionDialogId = "#action-dialog";

				if (sActionId == "tableDialog") {

					actionDialogId = "#action-dialog-lrp";

				}

				if (sActionId == "longRunningProcessRemove") {

					actionDialogId = "#remove-lrp";

				} else if (sActionId == "longRunningProcessAbort") {

					actionDialogId = "#abort-lrp";

				}

				if (!$.sc.isNullOrUndefined(sDialogId)) {

					actionDialogId = "#" + sDialogId;

				}

				_util._closeActionDialog(actionDialogId);

				var fnPosition = function(){

					if (!$.sc.isNullOrUndefined(oPosition))
					{
						return oPosition;
					}

					return;
				};

				var actionDialog = $(actionDialogId).dialog({
					autoOpen : false,
					title : oDialogSettings.title,
					close : oDialogSettings.close,
					beforeClose : oDialogSettings.beforeClose,
					position: fnPosition(),
					width : function() {

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
					modal : true,
					buttons : oDialogSettings.buttons,
					dialogClass : 'action-dialog',
					resizable : false
				});

				oDialogSettings.action(actionDialog);
			};

			/**
			 * Closes the action dialog with the parameter selector.
			 *
			 * @param dialogSelector
			 *            the dialog selector or object (Jquery).
			 *
			 */
			var _closeActionDialog = function(dialogSelector) {

				var oDialog = (dialogSelector instanceof jQuery) ? dialogSelector
						: $(dialogSelector);

				if (oDialog.is(":data(dialog)")) {
					oDialog.dialog('close');
				}

				return oDialog;

			};

			/**
			 * Initializes the action dialog (hides the element if visible).s
			 */
			var _initActionDialog = function() {
				$('.action-dialog').hide();
			};

			/**
			 * Get a list of ENUM names using values from the desired ENUM.
			 */
			var _getEnumFromValue = function(aValues, Enum) {

				var a = new Array();

				for (i in aValues) {
					if (aValues.hasOwnProperty(i)) {
						a.push(Enum[aValues[i]]);
					}
				}

				return a;

			};

			/**
			 * Trunc the given number using the give precision.
			 *
			 * @param {Number} -
			 *            The number to be truncated.
			 * @param {Number} -
			 *            The precision used, decimal places.
			 * @return {String} - Truncated number
			 */
			var _nTruncate = function(value, precision) {
				var power = Math.pow(10, 4);
				var intPlaces = String(value).split(".")[0].length;
				if (value.length != intPlaces) {
					precision += intPlaces + 1;
				}

				return String(value).substring(0, precision);
			};

			/**
			 * Replace all occurrences of given token, for the given
			 * replaceToken in the given String
			 */
			var _replaceAll = function replaceAll(token, replaceToken, str) {
				return str.replace(new RegExp(token, 'g'), replaceToken);
			};

			return {
				_isNullOrUndefined : _isNullOrUndefined,
				_getRowsSelection : _getRowsSelection,
				_isValidArray : _isValidArray,
				_locale : _locale,
				_launchActionDialog : _launchActionDialog,
				_closeActionDialog : _closeActionDialog,
				_getEnumFromValue : _getEnumFromValue,
				_replaceAll : _replaceAll,
				_nTruncate : _nTruncate
			}

		})();

		var _progressBar = (function() {

			var oLoading = $('#loading');
			var oProgressbar = $("#progress-bar");
			var _oPreload = $("#preload");
			var _initiated = false;

			var _startGlobalProgressBar = function(fnCallback) {

				$('html, body').animate({ scrollTop: 0 }, 'fast');
				_oPreload.fadeIn(300, function () {
				     if(oLoading.dialog("isOpen"))
				     {
				    	 _stopProgressBar();
				     }

				     _initiated = false;

				     if ($.isFunction(fnCallback))
				     {
				    	 fnCallback();
				     }
				 });

			};

			var _stopGlobalProgressBar = function() {
				_oPreload.fadeOut(300);
				_initiated = true;
			};

			var _startProgressBar = function(sMessage, bTable) {
				if (_initiated) {
					$('.formError').remove();

					if (bTable == undefined) {
						bTable = false;
					}

					if (oLoading.data("bTable") == undefined) {

						if (!oLoading.dialog('isOpen')) {

							oLoading.data("bTable", bTable);

							if (sMessage == null) {

								$('h5', oLoading).text(
										$.sc.locale("commons.pages.updating"));

							} else {

								$('h5', oLoading).text($.sc.locale(sMessage));

							}

							oLoading.dialog({
								autoOpen : true,
								modal : true,
								dialogClass : "loading",
								closeOnEscape : false,
								resizable : false
							});
							oProgressbar.progressbar({
								value : 1
							});

							(function progress() {

								var value = oProgressbar.progressbar('value');
								oProgressbar.progressbar('value', value + 1);

								if (value < 100) {

									setTimeout(progress, 200);

									if (value == 99) {

										oProgressbar.progressbar({

											value : 1

										});

									}

								}

							}());

						}

					}
				}
			};

			var _stopProgressBar = function(recordCount, bTable) {
				if (_initiated) {
					if (bTable == undefined) {

						bTable = false;

					}

					if (oLoading.data("bTable") == bTable) {

						oProgressbar.progressbar('value', 100);
						setTimeout(function() {

							oLoading.dialog("close");
							$('.fullscreen').hide();

						}, 50);

						oLoading.data("bTable", null);

					}
				}
			};

			return {
				_startGlobalProgressBar : _startGlobalProgressBar,
				_stopGlobalProgressBar : _stopGlobalProgressBar,
				_startProgressBar : _startProgressBar,
				_stopProgressBar : _stopProgressBar
			}
		})();

		/**
		 * Get the message came at the response.
		 *
		 * @param {Object}
		 *            oResponse - The Resonse Object.
		 * @param {String}
		 *            sDefaultMessage - The default value to be set if response
		 *            does not have Message
		 */
		var _getResponseMessage = function(oResponse, sDefaultMessage) {
			if (!$.sc.isNullOrUndefined(oResponse)) {
				if ($.sc.isValidArray(oResponse.messageList)) {

					return oResponse.messageList[0].text;

				}
			}
			return sDefaultMessage;

		};

		/**
		 * Show a message, either as confirmation or error. This is intended to
		 * work with the general messaging area at the top of the main content
		 * pages but will work with other targets as well.
		 *
		 * @param the
		 *            id of the target element to display the message into
		 * @param the
		 *            message
		 * @param the
		 *            message type, either "confirm" or "error".
		 */
		var _showMessage = function(target, message, type) {

			$('html, body').animate({
				scrollTop : 0
			}, 'slow');

			if ($("#" + target).length == 0) {

				var oSystemMessaging = '<div id="messaging-main" class="messaging" style="display: none;"><span	class="message"></span><a href="" class="remove">'
						+ $.sc.locale("message.action.close") + '</a></div>';
				if($('.content-inner').length != 0)
				{
					$('.content-inner').before(oSystemMessaging);
				}
				else
				{
					$('#load').html(oSystemMessaging);
				}
			}

			$("#" + target).fadeIn(
					200,
					function() {

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
		 * Converts the message array of an ajax callback result object to an
		 * HTML UL list.
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
			return "<ul class='messaging-details'>{0}</ul>".format(messages
					.join(""));
		};

		/**
		 * Valid Response Object came from BE.
		 *
		 * @param {Object}
		 *            oResponse The Object returned from BE.
		 * @returns {Object} An Object that contains whether the response is
		 *          valid or not and the error message.
		 */
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
		 * Check if Valid Response Object came from ViewController.
		 *
		 * @param {Object}
		 *            oPreLoadResponse The Object returned from ViewController.
		 * @param {Boolean}
		 *            bShowErrorMessage Whether to show or not the error
		 *            message.
		 */
		var _isValidPreLoad = function(oPreLoadResponse, bShowErrorMessage) {

			var oValidResponse = _isValidResponse(oPreLoadResponse);

			if (!oValidResponse.bIsValid) {
				if ((bShowErrorMessage === undefined)
						|| (bShowErrorMessage === "")
						|| (bShowErrorMessage === " ") || bShowErrorMessage) {
					// Show Error Message
					$.sc.showMessage("messaging-main", oValidResponse.sErrorMessage, "error");
					// Stop Progress bar
					$.sc.stopGlobalProgressBar();
				}
				return false;
			}

			return true;

		};

		/**
		 * Check if Rni is On and show a error message if not.
		 *
		 * @param {Boolean}
		 *            bShowErrorMessage - If it will show message when rni is
		 *            off
		 */
		var _isRniOn = function(bShowErrorMessage) {

			var oRniResponse = $.sc.ajax("api/process/checkRNIStatus", {
				'processRequest' : new processRequest()
			}, false);
			var oValidResponse = _isValidResponse(oRniResponse);

			if (bShowErrorMessage) {
				if (!oValidResponse.bIsValid) {
					$.sc.showMessage("messaging-main", $.sc.locale("commons.pages.error"), "error");
					return false;
				} else if (!oRniResponse.rniOnline) {
					$.sc.showMessage("messaging-main", $.sc.locale("action.longRunningProcessDialog.rnioffline"), "error");
					return false;
				}
			}

			return true;
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
		var _setMonitor = function(oRequest, isMonitored) {
			// Set isMonitored field at the request

			if (oRequest instanceof scheduleRequest) {
				oRequest.monitored = isMonitored;
				return oRequest;
			}

			if (!oRequest.processCriteria) {
				oRequest.processCriteria = new ProcessCriteria();
			}

			oRequest.processCriteria.monitored = isMonitored;
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
		var _monitorDialogEvent = function(e) {
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
				_savePropertyProfile({
					"MONITOR_REQUEST" : iMonitor
				});
			}

			_util._closeActionDialog("#monitor-dialog");

			oMonitorDeferred.resolve();
		};

		/**
		 * Check if Response is back or not.
		 *
		 * @param {Object} -
		 *            The Request Object to send at the check ajax call
		 * @param {oWaitResponseDeferred} -
		 *            The deferred object tha will be resolved when response is
		 *            back.
		 * @param {String} -
		 *            sSuccessMessage - The Success Message.
		 * @returns {Boolean} - Whether the response is back.
		 */
		var _checkResponseStatus = function(oRequest, oWaitResponseDeferred,
				sSuccessMessage) {

			(function fnVerify() {

				var oResponse = $.sc.getJson("api/process/fetch/id", oRequest,
						false, null, sSuccessMessage, true);

				if ($.sc.isValidArray(oResponse.processes)
						&& oResponse.processes[0].isProcessComplete) {
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
		 *
		 * @param {Object} -
		 *            The Response Object came from the first action
		 * @param {Function} -
		 *            The Callback functon send to the action originally.
		 * @param {String} -
		 *            The Success message.
		 */
		var _waitResponse = function(oResponse, fnCallback, sSuccessMessage) {
			var iProcessId = 0;
			var aSelectedIds = [];
			var oRequest = null;
			var oWaitResponseDeferred = $.Deferred();

			if (!$.sc.isNullOrUndefined(oResponse.firstProcess)) {

				iProcessId = oResponse.firstProcess.id;

			} else {

				iProcessId = oResponse.forceLightStatusLRPId;

			}

			oRequest = {
				processList : [ {
					id : iProcessId
				} ]
			};

			$.when(oWaitResponseDeferred).then(
					function() {
						if ($.isFunction(fnCallback)) {

							if (!$.sc.isNullOrUndefined(sSuccessMessage)) {

								$.sc.showMessage("messaging-main",
										sSuccessMessage, "confirm");

							}

							fnCallback(oResponse);
						}
					});

			_checkResponseStatus(oRequest, oWaitResponseDeferred, null);
		};

		/**
		 * Get the Response Object from BE
		 *
		 * @param {String}
		 *            sUrl The url from the ajax call.
		 * @param {Object}
		 *            oRequest The Request Object
		 * @param {Boolean}
		 *            bAsync Whether the Ajax call wil be sync or async.
		 * @returns {Object} The Response Object came from BE.
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

			if (bJson && oRequest) {

				sDataType = 'json';
				sContentType = 'application/json; charset=utf-8';
				if ($.sc.isNullOrUndefined(oRequest)) {
					oRequest = {};
				}

				oData = $.toJSON(oRequest);

			}

			if (bGet) {
				sType = "GET";
				if (sUrl.indexOf("?") == -1) {
					sUrl += "?";
				} else {
					sUrl += "&";
				}
				sUrl += "_" + Math.random();
			}

			$.ajax({
				dataType : sDataType,
				type : sType,
				url : sUrl,
				contentType : sContentType,
				data : oData,
				async : bAsync,
				success : function(oData) {

					oResponse = oData;

				},
				error : function(e) {
					$("#load").empty().load('timeout.jsp');
					$.sc.showMessage("messaging-main", $.sc.locale("commons.pages.error"), "error");
				}
			});

			return oResponse;
		};

		/** load the TAB * */
		/**
		 * @param param
		 *            object
		 */
		var _loadTab = function(param) {

			/** clear all checkbox selected * */
			sensus.widgets.datatable.clearSelectsFunction.call($(".list"));

			sAjaxUrl = param.sUrl;
			oContainerTabs = param.$container_tabs;
			var sBaseUrl = $.address.baseURL();

			/** Set the URL * */
			$.address.value((sAjaxUrl).replace(sBaseUrl, ''));

			/** if is a TAB * */
			if (param.bTab == true) {

				var iSlashIndex = param.sUrl.indexOf('/');
				var aUrl = param.sUrl.slice(iSlashIndex + 1, param.sUrl.length);
				aUrl = aUrl.split('?');
				sAjaxUrl = aUrl[0];

				if (param.bDetailPage) {
					sAjaxUrl += "?id=" + $.address.parameter("id");
				} else {
					$('.tabs').find('li').each(function() {

						$(this).find('a').removeClass('active');
						var sHref = $(this).find('a').attr('href').split('/');

						if (aUrl[0] == sHref[sHref.length - 1]) {

							$(this).find('a').addClass('active');

						}

					});
				}

			}

			if (!param.bInitialLoad && sAjaxUrl.indexOf("initialLoad") == -1) {
				if (sAjaxUrl.indexOf("?") == -1) {
					sAjaxUrl += "?initialLoad=false";
				} else {
					sAjaxUrl += "&initialLoad=false";
				}

			}

			var oResponse = $.sc.ajax(sAjaxUrl, null, false, null, true);

			if (oResponse) {

				oContainerTabs.empty().html(oResponse);
				if (status == "success") {

					if (param.message.bMessage) {

						$.sc.showMessage("messaging-main",
								param.message.sMessage, "confirm");

					} else {

						$.sc.showMessage("messaging-main",
								param.message.sMessage, "error");

					}

				}

			}

			if (param.fnCallBack) {

				param.fnCallBack();

			}

			_util._closeActionDialog("#action-dialog");
			_util._closeActionDialog("#action-dialog-lrp");

		};

		/** Load the first TAB * */
		/**
		 * @param sUrl
		 *            string
		 * @param loadTabs
		 *            object
		 */
		var _loadFirstTab = function(url, loadTabs) {

			/** check if the url is a tab */
			if (url.indexOf('tabs') == -1 || loadTabs) {

				var oResponse = $.sc.ajax(url, null, false, null, true);

				if (oResponse) {

					$("#tabs-content").empty().html(oResponse);

				}

			}
		};

		/**
		 * Method call and show new page. First set the address value to the
		 * actual url, and after ajax call clear all selected smartpoint on
		 * tables
		 */
		var _getPage = function(param) {

			_progressBar._startGlobalProgressBar(function()
				{
					$(".formError, #dialog-map").remove();

					// If no url, get the url from the actual element
					if (param.sUrl == null) {

						sAjaxUrl = $(param.$element).attr("href");

					} else {

						sAjaxUrl = param.sUrl;

					}

					// Set active menu item
					$(".active", oMainMenu).removeClass("active");

					if (sAjaxUrl) {

						$('a', oMainMenu).each(
								function() {

									if (sAjaxUrl.indexOf($(this).attr('href')
											.split('/')[0]) != -1) {

										$(this).addClass("active");
										return false; // break
									}

								});

					}

					sensus.widgets.datatable.clearSelectsFunction.call($(".list"));
					var bTabs = sAjaxUrl.match(/tabs/g);

					if (bTabs) {

						var aUrl = sAjaxUrl.split('.');
						aUrl.splice(1, 1);
						aUrl[(aUrl.length - 3)] = "main";
						sAjaxUrl = aUrl.join('.');

					} else {

						$.address.value(sAjaxUrl);

					}

					if (!param.bInitialLoad && sAjaxUrl.indexOf("dashboard") == -1
							&& sAjaxUrl.indexOf("initialLoad") == -1) {
						if (sAjaxUrl.indexOf("?") == -1) {
							sAjaxUrl += "?initialLoad=false";
						} else {
							sAjaxUrl += "&initialLoad=false";
						}

					}

					var oResponse = $.sc.ajax(sAjaxUrl, null, false, null, true);

					// Validate oResponse
					if (oResponse) {

						sensus.commons.lib.ajax.$container.empty().html(oResponse);
						if (param.message.status == "success") {

							if (param.message.bMessage) {

								$.sc.showMessage("messaging-main",
										param.message.sMessage, "confirm");

							}

						}

						/** If the url is a TABS * */
						if (bTabs) {

							$.sc.loadTab($.extend({}, param, {
								$container_tabs : $('#tabs-content'),
								bTab : true
							}));

						}

						_util._closeActionDialog("#action-dialog");

					}
				}
			);
		};

		/**
		 * Send ajax calls to get the response and validate the response showing
		 * the error message it is invalid.
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
		 *            bBlockScreen - Block Screen if true.
		 * @returns {Object} - The Response Object.
		 */
		var _getJson = function(sUrl, oRequest, bAsync, fnCallback,
				sSuccessMessage, bBlockScreen, bHideProgressBar, bGet) {

			// Start Progress Bar
			if (!bHideProgressBar) {
				$.sc.startProgressBar(null, false);
			}

			var oResponse = $.sc.ajax(sUrl, oRequest, bAsync, true, bGet);
			var oValidResponse = _isValidResponse(oResponse);
			var sResponseSuccessMessage = null;

			// Validate oResponse
			if (!oValidResponse.bIsValid) {

				// Show Error Message
				$.sc.showMessage("messaging-main",
						oValidResponse.sErrorMessage, "error");
				// Stop Progress bar
				$.sc.stopProgressBar(null, false);

			}

			sResponseSuccessMessage = $.sc.getResponseMessage(oResponse, null);

			// Show Success Message if it exists
			if (!$.sc.isNullOrUndefined(sSuccessMessage)
					&& oResponse.operationSuccess) {
				$.sc.showMessage("messaging-main", sSuccessMessage, "confirm");
			}

			if (!$.sc.isNullOrUndefined(sResponseSuccessMessage)
					&& oResponse.operationSuccess) {
				$.sc.showMessage("messaging-main", sResponseSuccessMessage,
						"confirm");
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

			if (($.sc.isNullOrUndefined(bBlockScreen) || !bBlockScreen)
					&& (!bHideProgressBar)) {
				// Stop Progress bar
				$.sc.stopProgressBar(null, false);
			}

			return oResponse;

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
		var _monitor = function(sUrl, oRequest, bAsync, fnCallback, sSuccessMessage, bCheckResponseReturn) {

			// Start Progress Bar
			$.sc.startProgressBar(null, false);

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
				var sSuccessMessageAfter = sSuccessMessage;
				bBlockProgressBar = true;
				fnCallback = function(oResponse) {
					$.sc.waitResponse(oResponse, fnActionCallback, sSuccessMessageAfter);
				};
			}

			if (bBlockProgressBar) {

				sSuccessMessage = null;

			}

			switch (sensus.settings.monitor) {
			case "1":
				oRequest = $.sc.setMonitor(oRequest, true);
				// Ajax call for the action
				$.sc.getJson(sUrl, oRequest, bAsync, fnCallback, sSuccessMessage, bBlockProgressBar);
				break;
			case "2":
				oRequest = $.sc.setMonitor(oRequest, false);
				// Ajax call for the action
				$.sc.getJson(sUrl, oRequest, bAsync, fnCallback, sSuccessMessage, bBlockProgressBar);
				break;
			case "3":
				// Open dialog
				$.sc.launchActionDialog("longRunningProcessDialog",
								sensus.pages.longrunningprocess.dialogSettingsProcess["longRunningProcessDialog"],
								"monitor-dialog");
				$.when(oMonitorDeferred).then(
								function() {

									oRequest = $.sc.setMonitor(oRequest,
													sensus.pages.longrunningprocess.dialogSettingsProcess.longRunningProcessDialog.iMonitor);
									// Ajax call for the action

									$.sc.getJson(sUrl, oRequest, bAsync, fnCallback, sSuccessMessage, bBlockProgressBar);
									sensus.pages.longrunningprocess.dialogSettingsProcess.longRunningProcessDialog.iMonitor = null;
									oMonitorDeferred = $.Deferred();
								}
							);
				break;
			default:
				break;
			}

		};

		var _savePropertyProfile = function(oNewSettings) {

			var fnCallback = function(response) {

				var aSettingsList = [];

				var aSettings = response.settings;

				for ( var iKey in aSettings)
				{
					if (aSettings.hasOwnProperty(iKey))
					{
						// Current Setting
						var oSetting = aSettings[iKey];
						if (oNewSettings)
						{
							if (oNewSettings[oSetting.propertyEnum])
							{
								oSetting.propertyValue = oNewSettings[oSetting.propertyEnum];

								if (sensus.settings[oSetting.propertyEnum])
								{
									sensus.settings[oSetting.propertyEnum] = oNewSettings[oSetting.propertyEnum];
								}
							}
						}

						aSettingsList.push({
							propertyEnum : oSetting.propertyEnum,
							propertyValue : oSetting.propertyValue
						});

					}
				}

				var oRequest = new settingsRequest(aSettingsList);

				$.sc.getJson('api/settings/upsert', oRequest, false, null, $.sc
						.locale("action.savesettings.success"), true);

				if ($(".dataTables_length").find("option:selected").val()) {
					$.sc.stopProgressBar(null, false);
				}

			};

			$.sc.getJson("api/settings/fetchproperties", null, false,
					fnCallback, null, true);

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

		/**
		 * Initialize action buttons
		 *
		 * @param url
		 */
		var _menuPlug = function(url, oMenuPlug) {
			/* Initialize action buttons */

			$('#actions-button').menuPlug(
					{
						content : $('#actions-button').next().html(),
						showSpeed : 400,
						backLink : false,
						actionCallback : function(item) {
							var checkedNum = parseInt($('.checked-count')
									.text());

							if (checkedNum !== 0 && oMenuPlug[item]) {

								$('.message').removeClass("ui-state-error");
								oMenuPlug[item]();

							} else if (!oMenuPlug[item] && checkedNum !== 0) {

								$('.message').removeClass("ui-state-error");
								$.sc.launchActionDialog(item,
										url.dialogSettings[item]);

							} else {

								$('.message').addClass("ui-state-error");
								$('.messaging').hide();

							}

						}

					});

		};

		/**
		 * Get UTC date from object and response with string
		 *
		 * @param dDate
		 *            object
		 * @param sFormat
		 *            Mask to format date
		 * @returns string date in json format
		 */
		var _dateToUTC = function(dDate) {

			var iOffset = parseFloat(sensus.settings.TIME_ZONE_HOURS);
			var iOsOffset = dDate.getTimezoneOffset() / 60;
			var dif = (iOffset + iOsOffset) * (-1);
			var dNewDate = dDate.getTime() + (3600000 * (dif));
			dNewDate = new Date(dNewDate);

			return dNewDate.toSensusString();

		};

		/**
		 * Get UTC date from object and response with string
		 *
		 * @param dDate
		 *            object
		 * @param sFormat
		 *            Mask to format date
		 * @returns string date in json format
		 */
		var _tenantTimeToUTC = function(dDate) {

			var iOffset = parseFloat(sensus.settings.userContext.tenant.timezoneRawOffset);
			var iOsOffset = dDate.getTimezoneOffset() / 60;
			var dif = (iOffset + iOsOffset) * (-1);
			var dNewDate = dDate.getTime() + (3600000 * (dif));
			dNewDate = new Date(dNewDate);

			return dNewDate.toSensusString();
		}

		/**
		 * Format Object
		 *
		 * @param sDate
		 *            String contains date
		 * @param sFormat
		 *            Mask to format date
		 * @returns Formated date
		 */
		var _dateFormat = function(sDate, sFormat, sElseFormat, bApplyTimeZone, iTimeZone) {

			// TODO - Refactor the dateFormat method.

			if (_util._isNullOrUndefined(sDate)) {
				return null;
			}

			/**
			 * Get Date Object from string
			 *
			 * @param sDate
			 *            string, date
			 * @return date date java script format
			 */

			var _getDate = function(sDate, applyTimeZone, iTimeZone)
			{
				var dDate = null;

				if ($.type(sDate) == 'string' && sDate.split("/").length == 3)
				{
					applyTimeZone = false;
					dDate = sDate.split("/");
					dDate[0] = dDate[0] - 1;
					dDate = new Date(dDate[2], dDate[0], dDate[1]);
				}
				else if ($.type(sDate) != "date")
				{
					// Split date in format like
					// "year-month-day-hour-minutes-seconds-milliseconds".
					var aDateFields = sDate.split("-");
					// Consider that new Date() in js uses 0 based month.
					aDateFields[1] = aDateFields[1] - 1;
					// New date with separated fieds.
					dDate = new Date(aDateFields[0], aDateFields[1],
							aDateFields[2], aDateFields[3], aDateFields[4],
							aDateFields[5], aDateFields[6]);
					// Application TimeZone Hours
					var iTimeZoneHours = null;
				}
				else
				{
					dDate = sDate;
				}

				// Check whether has to apply time zone or not
				if ((applyTimeZone === undefined) || (applyTimeZone === "") || (applyTimeZone === " ") || applyTimeZone)
				{
					dDate.setHours(dDate.getHours() + parseInt(sensus.settings.TIME_ZONE_HOURS, 10));
				}

				if (iTimeZone)
				{
					dDate.setHours(dDate.getHours() + iTimeZone);
				}

				aTime = (dDate.getMinutes() < 10) ? [ dDate.getHours(),
						"0" + dDate.getMinutes(), dDate.getSeconds(),
						dDate.getMilliseconds() ] : [ dDate.getHours(),
						dDate.getMinutes(), dDate.getSeconds(),
						dDate.getMilliseconds() ];

				return dDate;

			};

			/**
			 * @param date
			 *            date java script format
			 * @return sDate string date format 'd/m/yy'
			 */
			var fnFormatDateJS = function(date) {

				var dDay = date.getDate(), dMonth = date.getUTCMonth() + 1, dYear = date
						.getFullYear(), sDate = dMonth + "/" + dDay + "/"
						+ dYear;

				return sDate;
			};

			/**
			 * Format Object
			 *
			 * @param sDateFormated
			 *            Date formated with mask 'm/d/yy'
			 * @param sDate
			 *            String cotains date
			 * @param sElseFormat
			 *            Mask to format date
			 * @returns sDateFormated Date formated
			 */
			var formatSimpleDay = function(sDateFormated, sDate, sElseFormat,
					bApplyTimeZone) {

				var dToday = new Date();
				var dYesterday = new Date();

				dYesterday.setDate(dYesterday.getDate() - 1);

				dToday = fnFormatDateJS(dToday);
				dYesterday = fnFormatDateJS(dYesterday);

				if (sDateFormated === dToday) {

					sDateFormated = $.sc.locale("commons.pages.today");

				} else {

					if (sDateFormated === dYesterday) {

						sDateFormated = $.sc.locale("commons.pages.yesterday");

					} else {

						sDateFormated = (sElseFormat === "empty") ? "" : $.datepicker.formatDate(sElseFormat, _getDate(sDate, bApplyTimeZone, iTimeZone));

					}

				}

				return sDateFormated;
			};

			// Format Hour
			var formatHour = function(sFormat, sDateFormat) {

				// Error
				if (!aTime || !aTime.length) {
					return "";
				}

				if (sFormat.indexOf("hh") != -1) {

					sDateFormat = sDateFormat.replace("hh", aTime[0]);

				} else if (sFormat.indexOf("h") != -1) {

					var sHour = (aTime[0] == 0) ? 12
							: ((aTime[0] < 13) ? aTime[0] : aTime[0] - 12);
					sDateFormat = sDateFormat.replace("h", sHour);

				}

				if (sFormat.indexOf("i") != -1) {

					sDateFormat = sDateFormat.replace("i", aTime[1]);

				}

				if (sFormat.indexOf("s") != -1) {

					sDateFormat = (aTime[2] < 11) ? sDateFormat.replace("s",
							"0" + aTime[2]) : sDateFormat
							.replace("s", aTime[2]);
				}

				// Milliseconds
				if (sFormat.indexOf("fff") != -1) {

					sDateFormat = sDateFormat.replace("fff",
							aTime[3] == 0 ? "000" : aTime[3]);

				}

				if (sFormat.indexOf("a") != -1) {

					sDateFormat = sDateFormat.replace(new RegExp("a(\s|$)"),
							aTime[0] >= 12 ? "pm" : "am");

				}

				if (sFormat.indexOf("A") != -1) {

					sDateFormat = sDateFormat.replace(new RegExp("A(( )|$)"),
							aTime[0] >= 12 ? "PM " : "AM ");

				}

				return sDateFormat;
			};

			var sDateFormat = "";
			// Fail
			if (!sDate) {
				return undefined;
			}

			// If format empty or fnSimpleDate, get preferences formats
			if (!sFormat || sFormat === "simpleDate") {

				sFormat = sensus.settings.DATE_FORMAT;

			}

			if (sElseFormat === "simpleDate" || !sElseFormat) {

				sElseFormat = sensus.settings.DATE_FORMAT;

			}

			// Format Simple Day
			if (sFormat === "simpleDay") {

				sFormat = "m/d/yy";
				sDateFormat = $.datepicker.formatDate(sFormat, _getDate(sDate,
						bApplyTimeZone, iTimeZone));
				sDateFormat = formatSimpleDay(sDateFormat, sDate, sElseFormat,
						bApplyTimeZone);
				// Format Hour
				sDateFormat = formatHour(sElseFormat, sDateFormat);

			} else if (sFormat === "dateObj") {
				return _getDate(sDate, bApplyTimeZone, iTimeZone);
			} else {
				// Format date
				sDateFormat = $.datepicker.formatDate(sFormat, _getDate(sDate,
						bApplyTimeZone, iTimeZone));
				// Format Hour
				sDateFormat = formatHour(sFormat, sDateFormat);
			}

			return sDateFormat;

		};

		var _formValidate = function(oDom, sUrl, fnCallBack) {

			$(oDom).validationEngine('attach', {
				validationEventTrigger : 'submit',
				onValidationComplete : function(form, status) {

					if (status) {

						if ($.isFunction(fnCallback)) {

							fnCallback();

						}

					}

				}

			});

		};

		/**
		 * Call function to reload the properties
		 *
		 * @returns object object containing parameters ordination
		 *
		 */
		var _reloadProperties = function() {
			fnReloadProperties();
		};

		/**
		 * Get parameters of ordenation from url
		 *
		 */
		var _getSortExpression = function() {

			var sSort = $.address.parameter('sort');

			var oDirection = {
				asc : "Ascending",
				desc : "Descending"
			}

			if (!sSort || !sSort.replace(/\s/, '').length) {
				return false;
			}

			var aSort = sSort.split('|');

			return [ {
				'field' : aSort[0],
				'direction' : oDirection[aSort[1]]
			} ];

		};

		var _quickSearch = function(oRequest, sSmartpointUrl) {

			var fnCallback = function(response) {

				var iTotal = 0;

				if (response.lightList != null) {
					iTotal = response.lightList.length;
				}
				if (iTotal == 0
						|| iTotal > 1
						|| sensus.settings.userContext.userRole == "ROLE_Role.Analytic User") {

					_getPage($.extend({}, sensus.commons.lib.ajax.param, {
						sUrl : sSmartpointUrl,
						bFilter : true

					}));

				} else {

					var nId = response.lightList[0].id;
					_getPage($.extend({}, sensus.commons.lib.ajax.param, {
						sUrl : "lightDetail?id=" + nId,
						bFilter : true

					}));

				}

			};

			$.sc.getJson("api/light/fetchall", oRequest, false, fnCallback,
					null, $.sc.locale("action.savesettings.success"), true);

		};

		return {

			ajax : _ajax,
			checkResponseStatus : _checkResponseStatus,
			clearFormElements : _clearFormElements,
			dateFormat : _dateFormat,
			dateToUTC : _dateToUTC,
			tenantTimeToUTC : _tenantTimeToUTC,
			formValidate : _formValidate,
			reloadProperties : _reloadProperties,
			getEnumFromValue : _util._getEnumFromValue,
			getSortExpression : _getSortExpression,
			getJson : _getJson,
			getPage : _getPage,
			getResponseMessage : _getResponseMessage,
			isNullOrUndefined : _util._isNullOrUndefined,
			getRowsSelection : _util._getRowsSelection,
			isValidArray : _util._isValidArray,
			isValidPreLoad : _isValidPreLoad,
			isRniOn : _isRniOn,
			launchActionDialog : _util._launchActionDialog,
			closeActionDialog : _util._closeActionDialog,
			loadFirstTab : _loadFirstTab,
			quickSearch : _quickSearch,
			loadTab : _loadTab,
			locale : _util._locale,
			nTruncate : _util._nTruncate,
			menuPlug : _menuPlug,
			messageList : _messageList,
			monitor : _monitor,
			monitorDialogEvent : _monitorDialogEvent,
			replaceAll : _util._replaceAll,
			savePropertyProfile : _savePropertyProfile,
			setMonitor : _setMonitor,
			showMessage : _showMessage,
			startGlobalProgressBar : _progressBar._startGlobalProgressBar,
			stopGlobalProgressBar : _progressBar._stopGlobalProgressBar,
			startProgressBar : _progressBar._startProgressBar,
			stopProgressBar : _progressBar._stopProgressBar,
			waitResponse : _waitResponse
		};

	})();

})(jQuery);
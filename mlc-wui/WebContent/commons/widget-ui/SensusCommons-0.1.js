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

    	/**
    	 *  Load properties of system from method fetchProperties
    	 */
    	var fnReloadProperties = function(){

    		$.ajax({
    			dataType    : 'json',
    			type        : "POST",
    			url         : 'api/settings/fetchproperties',
    			async       : false,
    			success     : function(oData) {

    				sensus.settings = {};

    				//Validating response from fetchProperties
    				if (!(oData === null) || !(oData === undefined)) {

    					if(!(oData.setting === null) || !(oData.setting.length)) {

    						//add properties to namespace
    						for (var iKey in oData.setting) {

    							if((oData.setting[iKey].hasOwnProperty('propertyEnum')) && (oData.setting[iKey].hasOwnProperty('propertyValue'))){
    								sensus.settings[oData.setting[iKey]['propertyEnum']] = oData.setting[iKey]['propertyValue'];
    							}
    						}
    					}

    					if (!(oData.timeZoneHours === null) || !(oData.timeZoneHours === undefined)) {
    						sensus.settings.TIME_ZONE_HOURS = oData.timeZoneHours;
    					}
    					if (!(oData.enums === null) || !(oData.enums === undefined)) {
    						sensus.settings.enums = oData.enums;
    					}
    					if (!(oData.enums === null) || !(oData.enums === undefined)) {
    						sensus.settings.enums = oData.enums;
    					}
    					if (!(oData.userContext === null) || !(oData.userContext === undefined)) {
    						sensus.settings.userContext = oData.userContext;
    					}

    					sensus.settings.DATE_FORMAT = sensus.settings.DATE_FORMAT.toLowerCase().replace("yyyy","yy");
    					sensus.settings.longRunningProcessTime = 10000;
    					sensus.settings.checkRniTime = 10000
    				}
    			}
    		});

    	}

    	fnReloadProperties();



    	/*
    	 * Initialize language settings.
    	 */
    	jQuery.i18n.properties( {
    		name : 'messages',
    		path : "api/settings/fetchmessages/",
    		mode : 'map',
    		encoding : 'ISO-8859-1',
    		language : sensus.settings.language
    	});


    	/*
    	 * Set the locale from the sensus settings.
    	 */
    	$.getScript("thirdparty/jquery/custom/cultures/globalize.culture."+sensus.settings.LANGUAGE.replace("_","-")+".js",function(){

    		Globalize.culture(sensus.settings.LANGUAGE.replace("_","-"));

    	});

		var oMonitorDeferred = $.Deferred();
		var oMainMenu = $("#sensus-menu");
		var sAjaxUrl = null;
		var _new = function(obj){

			return Object.create(obj);

		};

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
			var oProgressbar = $("#progressbar");

			var _startProgressBar = function(sMessage,bTable){

				$('.formError').remove();

				if (bTable == undefined) {
					bTable = false;
				}

				if (oLoading.data("bTable") == undefined){

					if (!oLoading.dialog('isOpen')) {

						oLoading.data("bTable", bTable);

						if (sMessage == null) {

							sMessage = $.sc.locale("commons.pages.updating");

						}

						$('h5', oLoading).text($.sc.locale(sMessage));
						oLoading.dialog('open');
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

			if ($.sc.isValidArray(oResponse.messageList)) {

				return oResponse.messageList[0].text;

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
			if (!messages) {
				return "";
			}
			for ( var i = 0; i < messages.length; i++) {
				messages[i] = "<li>{0}</li>".format(messages[i].text);
			}
			return "<ul class='messaging-details'>{0}</ul>".format(messages.join(""));
		};

		/**
		 * Valid Response Object came from BE.
		 * @param {Object} oResponse
		 *  				The Object returned from BE.
		 * @returns {Object}
		 *					An Object that contains whether the response is valid or not and the error message.
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
		 * Check if Rni is On and show a error message if not.
		 * @param {Boolean} bShowErrorMessage - If it will show message when rni is off
		 */
		var _isRniOn = function(bShowErrorMessage) {

			var oRniResponse = $.sc.ajax(sensus.settings.checkRni, {'processRequest' : new processRequest()}, false);
			var oValidResponse = isValidResponse(oRniResponse);

			if (!oValidResponse.bIsValid && bShowErrorMessage) {
				$.sc.showMessage("messaging-main", $.sc.locale("commons.pages.error") + $.sc.locale("action.longRunningProcessDialog.rnioffline"), "error");
				return false;
			}

			return true;
		};

		/**
		 * Set whether the action is monitor or not at the request.
		 * @param {Object}  oRequest - The request Object that will be changed.
		 * @param {Boolean} isMonitored - The monitored field to be added at the request..
		 * @returns {Object} - The new Request Object.
		 */
		var _setMonitor = function(oRequest, isMonitored) {
			// Set isMonitored field at the request
			for (var key in oRequest) {
				oRequest[key].isMonitored = isMonitored;
			}

			return oRequest;

		};

		/**
		 * Event click for Monitor/Dismiss Dialog.
		 * This function check whether is monitor or not and save the preferences if remember action is checked.
		 * Also it resolve the Deferred used at the prompt dialog.
		 *
		 * @param {Object} - The element.
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
				sensus.util.page.fnSavePropertyProfile( { "MONITOR_REQUEST" : iMonitor } );
			}

			$("#monitor-dialog").dialog('close');

			oMonitorDeferred.resolve();
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

		/** load the TAB **/
		/**
		 * @param param
		 * 			object
		 */
		var _loadTab = function(param) {

			/** clear all checkbox selected **/
			sensus.widgets.datatable.clearSelectsFunction.call($(".list"));

			sAjaxUrl = param.sUrl;
			oContainerTabs = param.$container_tabs;
			var sBaseUrl = $.address.baseURL().replace('list.action','');

			/** Set the URL **/
			$.address.value((sAjaxUrl).replace(sBaseUrl,''));

			/** if is a TAB **/
			if (param.bTab == true) {

				var iSlashIndex = param.sUrl.indexOf('/');
				var aUrl = param.sUrl.slice(iSlashIndex + 1, param.sUrl.length);
				aUrl = aUrl.split('?');

				$('.tabs').find('li').each(function(){

					var sHref = $(this).find('a').attr('href').split('/');

					if(aUrl[0] == sHref[sHref.length-1]){

						$(this).find('a').addClass('active');

					}

				});

			}

			var oResponse = $.sc.ajax(sAjaxUrl, null, false);

			if (oResponse) {

				oContainerTabs.empty().html(oResponse);
				if (status == "success") {

					if (param.message.bMessage){

						$.sc.showMessage("messaging-main", param.message.sMessage , "confirm");

					} else {

						$.sc.showMessage("messaging-main", param.message.sMessage , "error");

					}

				}

			}

			if(param.fnCallBack){

				param.fnCallBack();

			}

			$('#action-dialog, #action-dialog-lrp').dialog('close');

		};

		/** Load the first TAB **/
		/**
		 * @param sUrl
		 * 			string
		 * @param loadTabs
		 * 			object
		 */
		var _loadFirstTab = function(url, loadTabs) {

			var sUrl = ($.address.value()).split('?')[0];

			/** check if the url is a tab */
			if (sUrl.indexOf('tabs') == -1 || loadTabs) {

				var oResponse = $.sc.ajax(sUrl, null, false);

				if (oResponse) {

					$("#tabs-content").empty().html(oResponse);

				}

			}
		};

		/**
		 * Method call and show new page.
		 * First set the address value to the actual url,
		 * and after ajax call clear all selected smartpoint on tables
		 */
		var _getPage = function(param) {

			$.sc.startProgressBar(null,true);
			$(".formError, #dialog-map").remove();

			//If no url, get the url from the actual element
			if (param.sUrl == null){

				//var appRoot = sensus.settings.appRoot.split("/")[1];

				sAjaxUrl = $(param.$element).attr("href");

			} else {

				sAjaxUrl = param.sUrl;

			}
			//Set active menu item
			$(".active", oMainMenu).removeClass("active");

			if (sAjaxUrl) {

				$('a', oMainMenu).each( function() {

					if (sAjaxUrl.contains($(this).attr('href').split('/')[0])) {

						$(this).addClass("active");
						return false; // break
					}

				});

			}

			//sensus.widgets.datatable.clearSelectsFunction.call($(".list"));
			var bTabs = sAjaxUrl.match(/tabs/g);

			if(bTabs){

				var aUrl = sAjaxUrl.split('.');
				aUrl.splice(1,1);
				aUrl[(aUrl.length-3)] = "main";
				sAjaxUrl = aUrl.join('.');

			} else {

				$.address.value(sAjaxUrl);

			}

			var oResponse = $.sc.ajax(sAjaxUrl, null, false, null, true);

			// Validate oResponse
			if (oResponse) {

				sensus.commons.lib.ajax.$container.empty().html(oResponse);
				if (param.message.status == "success") {

					if (param.message.bMessage){

						$.sc.showMessage("messaging-main", param.message.sMessage , "confirm");

					}

				}

				/** If the url is a TABS **/
				if (bTabs) {

					$.sc.loadTab($.extend({}, param, {
						$container_tabs : $('#tabs-content'),
						bTab : true
					}));

				}

				$('#action-dialog').dialog('close');

			}

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
		var _getJson = function(sUrl, oRequest, bAsync, fnCallback, sSuccessMessage, bBlockScreen, bHideProgressBar, bGet){

			// Start Progress Bar
			if(!$.sc.isNullOrUndefined(bHideProgressBar)){

				$.sc.startProgressBar(null,false);

			}

			var oResponse = $.sc.ajax(sUrl, oRequest, bAsync, true, bGet);
			var oValidResponse = _isValidResponse(oResponse);
			var sResponseSuccessMessage = null;

			// Validate oResponse
			if (!oValidResponse.bIsValid) {

				// Show Error Message
				$.sc.showMessage("messaging-main", oValidResponse.sErrorMessage, "error");
				// Stop Progress bar
				$.sc.stopProgressBar(null, false);

				return;
			}

			sResponseSuccessMessage = $.sc.getResponseMessage(oResponse, null);

			// Show Success Message if it exists
			if (!$.sc.isNullOrUndefined(sSuccessMessage)) {
				$.sc.showMessage("messaging-main", sSuccessMessage, "confirm");
			}

			if (!$.sc.isNullOrUndefined(sResponseSuccessMessage)) {
				$.sc.showMessage("messaging-main", sResponseSuccessMessage, "confirm");
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

			if ($.sc.isNullOrUndefined(bBlockScreen) || !bBlockScreen) {
				// Stop Progress bar
				$.sc.stopProgressBar(null, false);
			}

			return oResponse;

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

		var _savePropertyProfile = function (oNewSettings){

			var fnCallback = function(response){

				var aSettingsList = [];

				var aSettings = response.setting;

				for (var iKey in aSettings) {

					if (aSettings.hasOwnProperty(iKey)) {

						// Current Setting
						var oSetting = aSettings[iKey];
						if(oNewSettings){

							if(oNewSettings[oSetting.propertyEnum]){

								oSetting.propertyValue = oNewSettings[oSetting.propertyEnum];

							}

						}

						aSettingsList.push({ propertyEnum : oSetting.propertyEnum, propertyValue : oSetting.propertyValue});

					}

				}

				var oRequest = new settingsRequest(aSettingsList);

				$.sc.getJson('api/settings/upsert', oRequest, false, null, $.sc.locale("action.savesettings.success"),true);

				if($(".dataTables_length").find("option:selected").val()){
					$.sc.stopProgressBar(null, false);
				}

			};

			$.sc.getJson("api/settings/fetchproperties", null, false, fnCallback,null,true);

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
		 * @param url
		 */
		var _menuPlug = function(url, oMenuPlug) {
			/* Initialize action buttons */

			$('#actions-button').menuPlug( {
				content : $('#actions-button').next().html(),
				showSpeed : 400,
				backLink: false,
				actionCallback : function(item) {
					var checkedNum = parseInt($('.checked-count').text());

					if(checkedNum !==0 && oMenuPlug[item]){

						$('.message').removeClass("ui-state-error");
						oMenuPlug[item]();

					} else if(!oMenuPlug[item] && checkedNum !==0){

						$('.message').removeClass("ui-state-error");
						$.sc.launchActionDialog(item, url.dialogSettings[item]);

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
		 * 				object
		 * @param sFormat
		 * 				Mask to format date
		 * @returns
		 * 			string date in json format
		 */
		var _dateToUTC = function(dDate) {

			var iOffset = parseFloat(sensus.settings.TIME_ZONE_HOURS);
			var iOsOffset = dDate.getTimezoneOffset() / 60;
			var dif = (iOffset + iOsOffset)*(-1);
			var dNewDate = dDate.getTime() + (3600000 * (dif));
			dNewDate = new Date(dNewDate);

			return dNewDate.toJSON();

		};

		/**
		 * Get UTC date from object and response with string
		 *
		 * @param dDate
		 * 				object
		 * @param sFormat
		 * 				Mask to format date
		 * @returns
		 * 			string date in json format
		 */
		var _tenantTimeToUTC = function(dDate) {

			var iOffset = parseFloat(sensus.settings.userContext.tenant.timezoneRawOffset);
			var iOsOffset = dDate.getTimezoneOffset() / 60;
			var dif = (iOffset + iOsOffset)*(-1);
			var dNewDate = dDate.getTime() + (3600000 * (dif));
			dNewDate = new Date(dNewDate);

			return dNewDate.toJSON();
		}

		/**
		 * Format Object
		 * @param sDate
		 * 				String contains date
		 * @param sFormat
		 * 				Mask to format date
		 * @returns
		 * 			Formated date
		 */
		var _dateFormat = function(sDate, sFormat, sElseFormat, bApplyTimeZone, bApplyTenantTimeZone) {

			sDate = new Date(sDate);
			sDate = sDate.toISOString().replace('Z','');

			/**
			 * Get Date Object from string
			 *
			 * @param sDate
			 * 			string, date
			 * @return date
			 * 			date java script format
			 */

			var _getDate = function(sDate, applyTimeZone, applyTenantTimeZone) {

				var currentTime = null;
				var dDate = null;
				var aDate = null;
				var aHour = null;

				sDate = sDate.replace(/\//g, "-");

				// Check if is Year
				if (/^\s*(\d{4})-(\d|\d{2})-(\d|\d{2})*/.exec(sDate)) {

					aDate = /^\s*(\d{4})-(\d|\d{2})-(\d|\d{2})*/.exec(sDate);
					dDate = $.datepicker.parseDate('yy/mm/dd',aDate[0].replace(/\-/g, '/'));


				}  else if (/^\s*(\d|\d{2})-(\d|\d{2})-(\d{4})*/.exec(sDate)) {

					aDate = /^\s*(\d|\d{2})-(\d|\d{2})-(\d{4})*/.exec(sDate);
					dDate = $.datepicker.parseDate('mm/dd/yy',aDate[0].replace(/\-/g, '/'));

				} else {

					dDate = new Date(sDate.substring(0, 10) + "," + sDate.substring(sDate.length - 4, sDate.length));

				}
/*
				aHour = /\d{1,2}[:.]\d{1,2}[:.]\d{1,2}[\.]\d{1,3}/.exec(sDate);

				if (aHour) {

					dDate = new Date();
					currentTime = aHour[0].split(":");
					dDate.setHours(currentTime[0]);
					dDate.setMinutes(currentTime[1]);
					var aSTime = currentTime[2].split('.');
					dDate.setSeconds(aSTime[0]);
					dDate.setMilliseconds(aSTime[1]);

				}

*/
				if((applyTimeZone === undefined)||(applyTimeZone === "")||(applyTimeZone === " ")||applyTimeZone){

					var iDateMilliseconds  = dDate.getTime() + (3600000 * parseFloat(sensus.settings.TIME_ZONE_HOURS));
					dDate = new Date(iDateMilliseconds);

				}

				if(applyTenantTimeZone){

					var iDateMilliseconds  = dDate.getTime() + (3600000 * parseFloat(sensus.settings.userContext.tenant.timezoneRawOffset));
					dDate = new Date(iDateMilliseconds);

				}

				aTime = (dDate.getMinutes() < 10) ? [dDate.getHours(), "0"+dDate.getMinutes(), dDate.getSeconds(), dDate.getMilliseconds()]
														: [dDate.getHours(), dDate.getMinutes(), dDate.getSeconds(), dDate.getMilliseconds()];

				return dDate;

			};

			/**
			 * Format Object
			 * @param sDateFormated
			 * 				Date formated with mask 'm/d/yy'
			 * @param sDate
			 * 				String cotains date
			 * @param sElseFormat
			 * 				Mask to format date
			 * @returns sDateFormated
			 * 				Date formated
			 */
			var formatSimpleDay = function(sDateFormated, sDate, sElseFormat, bApplyTimeZone) {

				var dToday = new Date();
				var dYesterday = new Date();

				dYesterday.setDate(dYesterday.getDate()-1);

				dToday = fnFormatDateJS(dToday);
				dYesterday = fnFormatDateJS(dYesterday);

				if(sDateFormated === dToday) {

					sDateFormated = $.sc.locale("commons.pages.today");

				} else {

					if(sDateFormated === dYesterday) {

						sDateFormated = $.sc.locale("commons.pages.yesterday");

					} else {

						sDateFormated = (sElseFormat === "empty") ? "" : $.datepicker.formatDate(sElseFormat, fnGetDate(sDate, bApplyTimeZone));

					}

				}

				return sDateFormated;
			};

			//Format Hour
			var formatHour = function(sFormat, sDateFormat) {

				// Error
				if (!aTime || !aTime.length) {
					return "";
				}

				if (sFormat.indexOf("hh") != -1) {

					sDateFormat = sDateFormat.replace("hh", aTime[0]);

				} else if (sFormat.indexOf("h") != -1) {

					var sHour = (aTime[0] == 0) ? 12 : ((aTime[0] < 13) ? aTime[0] : aTime[0] - 12);
					sDateFormat = sDateFormat.replace("h", sHour);

				}

				if (sFormat.indexOf("i") != -1) {

					sDateFormat = sDateFormat.replace("i", aTime[1]);

				}

				if (sFormat.indexOf("s") != -1) {

					sDateFormat = (aTime[2] < 11) ? sDateFormat.replace("s", "0" + aTime[2]) : sDateFormat.replace("s", aTime[2]);
				}

				// Milliseconds
				if (sFormat.indexOf("fff") != -1) {

					sDateFormat = sDateFormat.replace("fff", aTime[3] == 0 ? "000" : aTime[3] );

				}

				if (sFormat.indexOf("a") != -1) {

					sDateFormat = sDateFormat.replace(new RegExp("a(\s|$)"), aTime[0] >= 12 ? "pm" : "am");

				}

				if (sFormat.indexOf("A") != -1) {

					sDateFormat = sDateFormat.replace(new RegExp("A(( )|$)"), aTime[0] >= 12 ? "PM " : "AM ");

				}

				return sDateFormat;
			};

			var sDateFormat = "";
			// Fail
			if (!sDate) {
				return undefined;
			}

			//If format empty or fnSimpleDate, get preferences formats
			if (!sFormat || sFormat === "simpleDate") {

				sFormat = sensus.settings.DATE_FORMAT;

			}

			if (sElseFormat === "simpleDate" || !sElseFormat) {

				sElseFormat = sensus.settings.DATE_FORMAT;

			}

			//Format Simple Day
			if(sFormat === "simpleDay")	{

				sFormat     = "m/d/yy";
				sDateFormat = $.datepicker.formatDate(sFormat, _getDate(sDate,bApplyTimeZone));
				sDateFormat = formatSimpleDay(sDateFormat,sDate,sElseFormat,bApplyTimeZone);
				//Format Hour
				sDateFormat = formatHour(sElseFormat,sDateFormat);

			} else {

				//Format date
				sDateFormat = $.datepicker.formatDate(sFormat, _getDate(sDate,bApplyTimeZone));
				//Format Hour
				sDateFormat = formatHour(sFormat, sDateFormat);

			}

			return sDateFormat;

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

		/**
		 * Chamar função de recarregar propriedades
		 */
		var _reloadProperties = function(){
			fnReloadProperties();
		};


    	return {

			ajax : _ajax,
			checkResponseStatus : _checkResponseStatus,
			clearFormElements : _clearFormElements,
			dateFormat : _dateFormat,
			dateToUTC : _dateToUTC,
			tenantTimeToUTC : _tenantTimeToUTC,
			formValidate : _formValidate,
			reloadProperties:_reloadProperties,
			getJson : _getJson,
			getPage : _getPage,
			getResponseMessage : _getResponseMessage,
			isNullOrUndefined : _util._isNullOrUndefined,
			isValidArray : _util._isValidArray,
			isRniOn : _isRniOn,
			launchActionDialog : _util._launchActionDialog,
			loadFirstTab : _loadFirstTab,
			loadTab : _loadTab,
			locale : _util._locale,
			menuPlug : _menuPlug,
			messageList : _messageList,
			monitorDialogEvent : _monitorDialogEvent,
			new : _new,
			savePropertyProfile : _savePropertyProfile,
			setMonitor : _setMonitor,
			showMessage : _showMessage,
			startProgressBar : _progressBar._startProgressBar,
			stopProgressBar : _progressBar._stopProgressBar,
			waitResponse : _waitResponse
    	};

   })();

})(jQuery);

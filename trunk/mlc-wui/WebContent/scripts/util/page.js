/**
 * @fileoverview Defines common page-related functionality. Many of those
 *               functions require specific HTML structures, ids or classes to
 *               work correctly.
 * @author Anke Doerfel-Parker
 */

/**
 * The main namespace for page-related functionality.
 */
sensus.util.page = {

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
	showMessage : function(target, message, type) {

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

	},

	/**
	 * Initialized the common messaging area on the page. It hides the main
	 * messaging container and initialized the remove button. Thise expectes
	 * ".messaging" (messaging container) and ".remove" (the remove button) to
	 * be present.
	 */
	initMessaging : function() {

		/** message html template **/
		var sClose             = $.sc.locale("message.action.close"),
			oMessaginMain      = $("#messaging-main"),

			// Errors displayed when a pageAction is called, inserted by decorator_epm_components.jsp
			txtMsgErros        = $("#messageErrorPages").text(),
			oMessageContainer  = '<div id="messaging-main" class="messaging"  style="display:none;"><span class="message"></span><a href="#" class="remove">' + sClose + '</a></div>';

		if (!oMessaginMain.length) {
			$("#bd").prepend(oMessageContainer);
		}

		$('.messaging').hide();


		if (txtMsgErros.replace(/\s/g, "").length) {
			sensus.util.page.showMessage("messaging-main", txtMsgErros, "error");
		}

	},

	/**
	 * Resets the fields in the form with the provide id.
	 *
	 * @param target
	 *            the id of the form to clear
	 */
	clearFormElements : function(target) {

		$("#" + target + " .messaging").hide();

		$("#" + target).find(':input').each(function() {

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

	},

	/**
	 * Hide and start the probressbar. This requires the "#loading" and
	 * "#progress-bar" elements to be present. The progress of the bar is
	 * currently hard-coded.
	 */
		startProgressBar : function (sMessage, bTable) {

			$('.formError').remove();
			var oLoading = $('#loading');
			var oProgressbar = $("#progressbar");

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
					};

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
		},

	/**
	 * Complete, stop and hide the progressbar. This requires the "#loading" and
	 * "#progress-bar" elements to be present.
	 *
	 * @param recordcount
	 *            if a value greater than 0 is provided, the progress-bar will
	 *            complete and stop after a delay instead of immediately
	 */
		stopProgressBar : function (recordCount, bTable) {

			var oProgressbar = $("#progressbar");
			var oLoading = $("#loading");
			if (bTable == undefined){
				bTable = false;
			}

			$()
			if (oLoading.data("bTable") == bTable) {
				if (recordCount > 0) {

					$("#progressbar").progressbar('value', 100);
					setTimeout(function () {
						$("#loading").dialog("close");
						$('.fullscreen').hide();
					}, 50);
				} else {
					$("#progressbar").progressbar('value', 100);
					setTimeout(function () {
						$("#loading").dialog("close");
						$('.fullscreen').hide();
					}, 50);
				}

				oLoading.data("bTable", null);
			}
		},
	/**
	 * Initialize the progress-bar dialog. This requires the "#loading" element.
	 */
		initProgressBar : function () {
			$("#loading").show().dialog( {
				autoOpen : false,
				modal : true,
				dialogClass : 'loading',
				resizable : false
			});
		},

	/**
	 * Parse Url parameters out of the current location's query string.
	 *
	 * @return an object containing all detected parameters as properties
	 */
	getUrlVars : function() {

		var vars = {}, hash;
		var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');

		for ( var i = 0; i < hashes.length; i++) {

			hash = hashes[i].split('=');
			vars[hash[0]] = hash[1];

		}

		return vars;
	},

	/**
	 * Get the URL and split it making a string to be utilized in filter
	 * @returns a string containing all filters to be used in data parameter of the ajaxCall
	 */
	fnGetURLFilters : function (bSaved) {

		var sURL = document.location.href;
		var sParameters = sURL.split('?')[1];
		var sResults = "&";
		var aParam = sParameters.split('&');

		for (var i = 0; i < aParam.length; i++) {

			var sEnum = aParam[i].split('=')[0];
			var sValue = aParam[i].split('=')[1];

			if (((!bSaved && sEnum != 'length') || bSaved)
						&& sEnum != 'iStart'
						&& sEnum != 'iOrder'
						&& sEnum != 'saved'
						&& sValue != 'undefined'
						&& sEnum != 'sd') {

				sResults += sEnum;

				sResults += "=";

				sResults += sValue;

				sResults += "&";

			}

		}

		sResults = sResults.substring(0, sResults.length - 1);

		return sResults;
	},

	/**
	 * Make target element visible by removing the "invisible" class.
	 *
	 * @param selector
	 *            the jQuery selector for the element(s) to remove the
	 *            "invisible" class for
	 */
	showContent : function(selector) {
		$(selector).removeClass("invisible");
	},
	/**
	 * Converts the message array of an ajax callback result object to an HTML
	 * UL list.
	 *
	 * @param messages
	 *            array of message strings
	 * @return HTML for the message list
	 */
	getMessageList : function(messages) {
		if (!messages) {
			return "";
		}
		for ( var i = 0; i < messages.length; i++) {
			//messages[i] = "<li>{0}</li>".format(messages[i].text);
		}
		//return "<ul class='messaging-details'>{0}</ul>".format(messages.join(""));
	},

	/**
	 * Get the position of an array by a key object value.
	 * Returns -1 if key value does not exists.
	 *
	 * @param array
	 * 				The array to get the position.
	 * @param key
	 * 				The key to look for.
	 * @param value
	 * 				The value to look for.
	 * @returns {Integer}
	 * 				The position of the element.
	 */

	fnGetArrayObject : function(array, key, value) {
		var iIndex = 0;
		$.each(array, function(i, e) {
			if (e[key] == value) {
				iIndex = i;
			}
		});

		return iIndex || -1;
	},

	/**
	 * Initialize action buttons
	 * @param url
	 */

	menuPlug : function(url, oMenuPlug) {
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
					sensus.util.actiondialog.launchActionDialog(item, url.dialogSettings[item]);

				} else {

					$('.message').addClass("ui-state-error");
					$('.messaging').hide();

				}

			}
		});
	},

	/**
	 * Get Parameters and create object SearchParameters
	 */
	getSearchParameters : function() {

		var sGroups            			= $.address.parameter('groups'),
			sConfiguration     			= $.address.parameter('configuration'),
			sAlarmType         			= $.address.parameter('alarm_type'),
			sWarningType       			= $.address.parameter('warning_type'),
			sDimmable        			= $.address.parameter('dimmable'),
			sHousing           			= $.address.parameter('housing'),
			sWattage           			= $.address.parameter('wattage'),
			sLampType          			= $.address.parameter('lamptype'),
			sEventSchedule     			= $.address.parameter('event_schedule'),
			sOffsetSchedule    			= $.address.parameter('offset_schedule'),
			sTags              			= $.address.parameter('tags'),
			sStatus            			= $.address.parameter('status'),
			sStreet            			= $.address.parameter('street'),
			sCityName          			= $.address.parameter('city'),
			sZipCode           			= $.address.parameter('zip'),
			sSort              			= unescape($.address.parameter('sort')),
			sQuery             			= $.address.parameter('query'),
			sVoltageRange      			= $.address.parameter('voltage_range'),
			sHousingColor      			= $.address.parameter('housing_color'),
			sModelNumber       			= $.address.parameter('model_number'),
			sDateAddedAfter  			= $.address.parameter('start'),
			sDateAddedBefore			= $.address.parameter('end'),
			sFirmwareVersion   			= $.address.parameter('firmware_version'),
			sColorTemperature  			= $.address.parameter('color_temperature'),
			sBulbSerialNumber          	= $.address.parameter('bulb_serial_number'),
			sLightDriverSerialNumber  	= $.address.parameter('light_driver_serial_number'),
			sUpperAssemblySerialNumber  = $.address.parameter('upper_assembly_serial_number'),
			sLowerAssemblySerialNumber  = $.address.parameter('lower_assembly_serial_number'),
			sEcoMode					= $.address.parameter('ecomode'),
			aSearchParameters  = [];

		if (sSort) {
			sSort = sSort.replace('|', ' ');
		}

		if (sStreet) {
			sStreet = unescape(sStreet);
		}

		sensus.util.page.fnFillListParameters(aSearchParameters, sGroups, 'GROUP_ID');
		sensus.util.page.fnFillListParameters(aSearchParameters, sConfiguration, 'PROTECTED');
		sensus.util.page.fnFillListParameters(aSearchParameters, sAlarmType, 'ALL_ALARMS');
		sensus.util.page.fnFillListParameters(aSearchParameters, sWarningType, 'ALL_WARNINGS');
		 	// Light Type --- Start
		sensus.util.page.fnFillListParameters(aSearchParameters, sDimmable, 'DIMMABLE');
		sensus.util.page.fnFillListParameters(aSearchParameters, sHousing, 'HOUSING');
		sensus.util.page.fnFillListParameters(aSearchParameters, sWattage, 'WATTAGE_RATING');
		sensus.util.page.fnFillListParameters(aSearchParameters, sLampType, 'LAMP_TYPE');
			// sensus.util.page.Light Type --- End
		sensus.util.page.fnFillListParameters(aSearchParameters, sEventSchedule, 'EVENT_SCHEDULE');
		sensus.util.page.fnFillListParameters(aSearchParameters, sOffsetSchedule, 'OFFSET_SCHEDULE');
		sensus.util.page.fnFillListParameters(aSearchParameters, sTags, 'TAG_ID');
		sensus.util.page.fnFillListParameters(aSearchParameters, sStatus, 'ALL_STATUS');
		sensus.util.page.fnFillListParameters(aSearchParameters, sStreet, 'STREET_NAME');
		sensus.util.page.fnFillListParameters(aSearchParameters, sCityName, 'CITY_NAME');
		sensus.util.page.fnFillListParameters(aSearchParameters, sZipCode, 'ZIP_CODE');
		sensus.util.page.fnFillListParameters(aSearchParameters, sVoltageRange, 'INPUT_VOLTAGE_RANGE');
		sensus.util.page.fnFillListParameters(aSearchParameters, sHousingColor, 'HOUSING_COLOR');
		sensus.util.page.fnFillListParameters(aSearchParameters, sModelNumber, 'MODEL_NUMBER');
		sensus.util.page.fnFillListParameters(aSearchParameters, sDateAddedBefore, 'DATE_ADDED_BEFORE');
		sensus.util.page.fnFillListParameters(aSearchParameters, sDateAddedAfter, 'DATE_ADDED_AFTER');
		sensus.util.page.fnFillListParameters(aSearchParameters, sFirmwareVersion, 'FIRMWARE_VERSION');
		sensus.util.page.fnFillListParameters(aSearchParameters, sColorTemperature, 'COLOR_TEMPERATURE');
		sensus.util.page.fnFillListParameters(aSearchParameters, sLightDriverSerialNumber, 'LIGHT_DRIVER_SERIAL_NUMBER');
		sensus.util.page.fnFillListParameters(aSearchParameters, sBulbSerialNumber, 'BULB_SERIAL_NUMBER');
		sensus.util.page.fnFillListParameters(aSearchParameters, sUpperAssemblySerialNumber, 'UPPER_ASSEMBLY_SERIAL_NUMBER');
		sensus.util.page.fnFillListParameters(aSearchParameters, sLowerAssemblySerialNumber, 'LOWER_ASSEMBLY_SERIAL_NUMBER');
		sensus.util.page.fnFillListParameters(aSearchParameters, sEcoMode, 'ECOMODE');
		sensus.util.page.fnFillListParameters(aSearchParameters, sSort, 'SORT');

		if (sQuery && sQuery.contains('|')) {
			if (sQuery.split('|')[0] == '36') {

				sensus.util.page.fnFillListParameters(aSearchParameters, sQuery.split('|')[1], 'FLEXNET_ID');

			} else if (sQuery.split('|')[0] == '12'){

				sensus.util.page.fnFillListParameters(aSearchParameters, sQuery.split('|')[1], 'POLE_ID');

			}
		}

		return aSearchParameters;
	},

	/**
	 * Function util to convert Parameters in object enum
	 */
	fnFillListParameters : function(aSearchParameters, sFilter, sEnum) {

		if (sFilter && sFilter != 'undefined') {

			sFilter = sFilter.replace("%20", " ").replace("%2C", ",");

			var aValues = new Array();

			if (sEnum == "INPUT_VOLTAGE_RANGE") {

				sFilter = sFilter.replace(new RegExp("_","gm")," ");

			} else if (sEnum == "DATE_ADDED_BEFORE" || sEnum == "DATE_ADDED_AFTER") {
				sFilter = sFilter.replace(new RegExp("_","gm")," ");
				sFilter = sensus.util.page.fnDateFormatToUTCFromStringDate(sFilter, sensus.settings.dateFormatMask);

			}

			if (!sFilter.contains("|")) {

				if (sFilter.toLowerCase() != "all") {
					aSearchParameters.push({
						'propertyEnum'  : sEnum,
						'value'         : sFilter
					});
				}

			} else {

				aValues = sFilter.split("|");
				aValues.pop();

				for (g in aValues) {
					if (aValues.hasOwnProperty(g)) {
						aSearchParameters.push({
							'propertyEnum'  : sEnum,
							'value'         : aValues[g]
						});

					}
				}
			}
		}

	},

	getSortExpression : function() {

		var sSort = $.address.parameter('sort');

		if (!sSort || !sSort.replace(/\s/, '').lenght) {
			return false;
		}

		var aSort = sSort.split('|');

		return {
			'field'     : aSort[0],
			'direction' : aSort[1]
		};

	},

	fnDateFormatToUTCFromStringDate : function (sDate, sDateFormatMask){

		var aDate 	= sDate.split("/"),
			aFormat = sDateFormatMask.split("/"),
			oDate 	= {};


		for (var i = 0; i < aFormat.length; i++){
			oDate[aFormat[i]] = parseInt(aDate[i]);
		}

		return $.date.fnTimeToUTC(new Date(oDate.yy, oDate.mm - 1, oDate.dd));
	},

	fnSavePropertyProfile : function (oNewSettings){


		var fnCallback = function(response){

			var aSettingsList = [];

			var aSettings = response.settings;

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

			var oRequest = { 'settingsRequest' : new settingsRequest(aSettingsList) };

			$.ajaxValidator.fnDoCall (sensus.settings.saveProfileSettings, oRequest, false, sensus.commons.lib.ajax.reloadSettings, $.sc.locale("action.savesettings.success"),true);

			if($(".dataTables_length").find("option:selected").val()){

				sensus.util.page.stopProgressBar(null, false);

			}

		};

		$.ajaxValidator.fnDoCall ("systemsettings/ajax.fillProfilePageAction.action", null, false, fnCallback,null,true);

	},

	addDays : function(sDate, nDays){

		dDate = new Date(sDate);
	    return new Date(dDate.getTime() + nDays*24*60*60*1000);

	}
}
/*
 * jQuery PGSI Commons v1.0
 * http://www.qat.com
 *
 * Copyright (c) 2009-2014 Qat Global
 *
 * Authors	: Lucas Oliveira, Raphael Constantino (previous versions)
 * 			  Delcides Junior
 *
 * Date: 2014-08-21 20:50:00 UTC (Thursday, 21 Aug 2014)
 */

(function ($)
{
	$.pgsi = (function ()
	{
		/**
		 * Default configuration for common functionality. These can be changed
		 * using setGeneralConfig.
		 */

		var _oConfig =
		{
			common:
			{
				errorLabel: "commons.pages.error"
			},
			date:
			{
				todayLabel		: "commons.pages.today",
				yesterdayLabel	: "commons.pages.yesterday",
				UTCTimeZone		: "Etc/GMT"
			},
			locale:
			{
				name : 'messages',
				mode : 'map'
			},
			pageLoader:
			{
				pgsiLabel	: "commons.pages.pgsi"
			},
			progressBar:
			{
				oLoading	: $('#loading'),
				oProgressbar: $("#progress-bar"),
				oPreload	: $("#preload"),
				oFullScreen	: $(".fullscreen"),
				initiated	: false,
				updateLabel	:"commons.pages.updating"
			}
		};

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
				$(_oConfig.progressBar.oLoading).show().dialog({
					autoOpen : false,
					modal : true,
					dialogClass : 'loading no-close no-title-bar',
					resizable : false
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
				$.pgsi.progressBar.stop();
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
						_oConfig.progressBar.oProgressbar.progressbar({
							value : 1
						});

						(function progress() {

							var value = _oConfig.progressBar.oProgressbar.progressbar('value');
							_oConfig.progressBar.oProgressbar.progressbar('value', value + 1);

							if (value < 100)
							{
								setTimeout(progress, 200);

								if (value == 99)
								{
									_oConfig.progressBar.oProgressbar.progressbar({
										value : 1
									});
								}
							}

						}());
					}
					//$(".ui-dialog-titlebar").hide();
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

					setTimeout(function() {
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
			var _oListeners = {};

			/**
			 *	@Class
			 */
			var Listener = function(oParam)
			{
				this.evt = oParam.evt;
				this.arguments = oParam.arguments;
				this.fnEventFunction = oParam.fnEventFunction;
			};

			/**
			 *	Method used to check if all dependences complete
			 *	@Param - sEventName
			 * 		String value for name of event
			 *
			 */
			var _isArgumentsDone = function(sEventName)
			{
				if (!_oListeners[sEventName])
				{
					return;
				}
				for (var i in _oListeners[sEventName].arguments)
				{
					if (!_oListeners[sEventName].arguments[i])
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
				return _oListeners[sEventName];
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
			var _wait = function(oParam)
			{

				// Listen for the event.
				var fnEventTriggeredFunction = function (e)
				{
					if (_isArgumentsDone(oParam.eventName))
					{
						if ($.isFunction(oParam.fnCallback))
						{
							oParam.fnCallback();
						}

						_destroy(oParam);
					}
				};

				// Create the event.
				_oListeners[oParam.eventName] = new Listener({
					evt 			: document.createEvent("Event"),
					arguments 		: {},
					fnEventFunction : fnEventTriggeredFunction
				});

				// Define a name for event
				_oListeners[oParam.eventName].evt.initEvent(oParam.eventName, true, true);

				if (oParam.arguments && _util.isValidArray(oParam.arguments))
				{
					$.each(oParam.arguments, function(i, argument){
						_oListeners[oParam.eventName].arguments[argument] = false;
					});
				}

				document.addEventListener(oParam.eventName, _oListeners[oParam.eventName].fnEventFunction);
			};

			var _destroy = function(oParam)
			{
				document.removeEventListener(oParam.eventName, _oListeners[oParam.eventName].fnEventFunction, false);
				delete _oListeners[oParam.eventName];
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
				if (!_oListeners[oParam.eventName])
				{
					return;
				}
				// target can be any Element or other EventTarget.
				for (var i in oParam.arguments)
				{
					_oListeners[oParam.eventName].arguments[oParam.arguments[i]] = true;
				}

				document.dispatchEvent(_oListeners[oParam.eventName].evt);
			};

			return {
				wait 			: _wait,
				notify 			: _notify,
				getEventStatus 	: _getEventStatus
			};
		})();

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
				bGet			: false,	// False for POST type Ajax calls
				bJson			: true,		// If the content being sent is in JSON format
				bInitialLoad 	: true,		// False to set initialLoad parameter as false on request
				fnCallback		: null,		// Callback for succeeded operations
				fnErrorCallback	: null,		// Callback for failed operations
				oDeferred		: null,
				oRequest		: null,		// Request Object
				sContentType	: '',
				sDataType		: null,
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
					return false;
				}

				var oData = '';
				var sType = 'POST';

				if (oCallConfig.bJson)
				{
					oCallConfig.sDataType = 'json';
					oCallConfig.sContentType = 'application/json; charset=utf-8';
					if (_util.isNullOrUndefined(oCallConfig.oRequest))
					{
						oCallConfig.oRequest = {};
					}

					oData = $.toJSON(oCallConfig.oRequest);
				}

				if (oCallConfig.bGet)
				{
					sType = "GET";

					if(oCallConfig.sUrl.indexOf("userId") == -1){
						if(oCallConfig.sUrl.indexOf("?") != -1){
							oCallConfig.sUrl += "&userId=" + pgsi.settings.userContext.userId
						}else{
							oCallConfig.sUrl += "?userId=" + pgsi.settings.userContext.userId
						}
					}
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

				oCallConfig.oResponse = null;

				if (!pgsi.util.page.fnCheckXSS(oData))
				{
					$.ajax({
						dataType 	: oCallConfig.sDataType,
						type 		: sType,
						url			: oCallConfig.sUrl,
						contentType	: oCallConfig.sContentType,
						data		: oData,
						cache		: oCallConfig.bCache,
						async		: oCallConfig.bAsync,
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
							return false;
						},
						complete	: function()
						{
							oCallConfig._afterCall();
						}
					});
				}
			};

			$.fn.fnLoadDropDownList = function(aList) {


				if (aList) {
					var $this = this;
					$.each(aList, function(key, value) {
						$this.append($("<option></option>").attr("value", key)
								.text(value));
					});
				}
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
					var oResponse = oCallConfig.oResponse;

					// Execute Callback function if it exists
					if ($.isFunction(oCallConfig.fnCallback))
					{
						try
						{
							oCallConfig.fnCallback(oResponse);
							if(oResponse.operationSuccess == false ){
								$.pgsi.progressBar.stop();
							}
						}
						catch (e)
						{
							// Show Error
							console.log(e);
						}
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
				return dDate1.getTime() <= dDate2.getTime();
			};

			/**
			 * Parse Date Get a date on string format and return a Date Object
			 *
			 * @param sDateTime
			 *            String contains date and time "03/25/2013 10:00am"
			 * @Param sFormat
			 *            String - the date format "mm/dd/yy"
			 * @return Formated date time
			 */
			var _parseDate = function (sDateTime, sFormat)
			{
				var aDateTime = sDateTime.split(" ");
				var sDate = aDateTime[0];
				var sTime = aDateTime.length > 1 ? aDateTime[1].toLowerCase() : null;
				var iHourFormat = 0;
				var dDate = null;

				// Format date
				if (sDate && sFormat)
				{
					dDate = $.datepicker.parseDate(sFormat, sDate);
				}

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
						if ((sTime.indexOf("pm") != -1) && (sHour != "12")
								|| (sTime.indexOf("am") != -1) && (sHour == "12"))
						{
							iHourFormat = 12;
						}

						sTime = sTime.replace(/(am|pm)/, "").trim();
					}

					sTime = sTime.split(":");

					dDate.setHours(iHourFormat == 12 ? (parseInt(sTime[0], 10) + iHourFormat) : sTime[0]);
					dDate.setMinutes(sTime[1]);

					// Validate if contains seconds
					if (sTime[2])
					{
						dDate.setSeconds(sTime[2]);
					}
				}

				return dDate;
			};

			/**
			 * Get Current Date Call server to get the current date or get from
			 * name-space (pgsi.settings.currentTime) date cache refresh each
			 * ten seconds
			 *
			 * @param bUtc
			 *            Boolean - the flag to apply UTC time-zone
			 * @param the
			 *            server current date
			 */
			var _getCurrentDate = function (bUtc)
			{
				var oNow = new Date();
			    var oUtc = new Date(oNow.getTime() + oNow.getTimezoneOffset() * 60000);
			    var oCurrentDate = _getDateObj(oUtc, {bUserTZ : false});

				if (bUtc)
				{
					// Clone date to removing references
					return new Date(oCurrentDate.getTime());
				}

				// Apply application time-zone
				return _getDateObj(oCurrentDate);
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
				var iHours 		= '';
				var iMinutes 	= '';
				var iSeconds	= '';
				var iMiliSeconds= '';

				if (!_util.isNullOrUndefined(dDate))
				{
					iHours 		= dDate.getHours();
					iMinutes 	= dDate.getMinutes();
					iSeconds	= dDate.getSeconds();
					iMiliSeconds= dDate.getMilliseconds();
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
					return pgsi.settings.user.timeZone;
				}

				if (oTimeZone.sTZId)
				{
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
					if (!_util.isNullOrUndefined(timezoneJS) && !_util.isNullOrUndefined(pgsi.settings.user.timeZone)) {

						// Return user current Time-zone
						return new timezoneJS.Date(pgsi.settings.user.timeZone).getTimezoneOffset();
					}
					else {
						return null;
					}

				}

				if (oTimeZone.iTZMinutes && $.isNumeric(oTimeZone.iTZMinutes))
				{
					// Return the specific time-zone
					return parseInt(oTimeZone.iTZMinutes, 10);
				}

				return 0;
			};

			var _getEpochTime = function (date, oTimeZone)
			{
				var aDateFields;
				var sTimezoneId		= _getTimezoneId(oTimeZone);
				var bFollowsDST 	= true;

				// Date on string format YEAR-MONTH-DAY-HOURS-MINUTES-SECONDS-MILISECONDS
				if ($.type(date) == "string" && date.indexOf("-") != -1)
				{
					aDateFields = date.split("-");

					var oDate = new timezoneJS.Date(aDateFields[0], (aDateFields[1] - 1), aDateFields[2],
							aDateFields[3], aDateFields[4], aDateFields[5], aDateFields[6], _oConfig.date.UTCTimeZone);
					oDate.setTimezone(sTimezoneId);

					if (bFollowsDST === false && oDate.getTimezoneInfo().isDST)
					{
						oDate.setMinutes(oDate.getMinutes() - 60);
					}

					return oDate._timeProxy;
				}

				return null;
			};

			/**
			 * Get date
			 *
			 * @param date
			 *            Date - apply time-zone String - parse date and apply
			 *            time-zone Number - create date and apply time-zone
			 * @param oTimeZone
			 *            Object - the time zone object null/undefined, true or
			 *            bUserTZ equals true: apply user time-zone iTZMinutes:
			 *            (Number) the time-zone offset (minutes) to apply on
			 *            date
			 * @return the date instanced
			 */
			var _getDateObj = function (date, oTimeZone) {

				var aDateFields;
				var iTimezoneOfset	= _getTimezoneOffset(oTimeZone);
				var sTimezoneId		= _getTimezoneId(oTimeZone);

				// Date on string format YEAR-MONTH-DAY-HOURS-MINUTES-SECONDS-MILISECONDS
				if ($.type(date) == "string" && date.indexOf("-") != -1)
				{
					aDateFields = date.split("-");

					var oDate = new timezoneJS.Date(aDateFields[0], (aDateFields[1] - 1), aDateFields[2],
							aDateFields[3], aDateFields[4], aDateFields[5], aDateFields[6], _oConfig.date.UTCTimeZone);
					oDate.setTimezone(sTimezoneId);

					return new Date(oDate.year, oDate.month, oDate.date, oDate.hours, oDate.minutes, oDate.seconds, oDate.milliseconds);
				}

				// Whether the date is numeric and instance of string
				if ($.type(date) == "string" && $.isNumeric(date))
				{
					var dNewDate = new Date(parseInt(date, 10));
					return _applyTimezoneOffset(dNewDate, (iTimezoneOfset+dNewDate.getTimezoneOffset()));
				}

				// Date time
				if ($.type(date) == "number" || $.type(date) == "date")
				{
					if ($.type(date) == "date") {
						date = date.getTime();
					}

					var oDate = new timezoneJS.Date(date, _oConfig.date.UTCTimeZone);
					if (!$.pgsi.isNullOrUndefined(sTimezoneId)) {
						oDate.setTimezone(sTimezoneId);
					}

					// oTimeZone === true, uses local user timezone
					if (oTimeZone === true) {
						return new Date(date);
					}

					return new Date(oDate.year, oDate.month, oDate.date, oDate.hours, oDate.minutes, oDate.seconds, oDate.milliseconds);

				}

				return null;
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

				var dDate = date;

				dDate = _getDateObj(date, oTimeZone);

				sFormat = _formatTime(sFormat, dDate); // Format hour, minutes, seconds and period
				sFormat = $.datepicker.formatDate(sFormat, dDate); // Format the date: year, month and day
				sFormat = _formatSimpleDay(sFormat, dDate); // Set today or Yesterday

				return sFormat;
			};

			/**
			 * Get UTC Date
			 *
			 * @param date
			 *            Date - the date
			 * @return the JSON date
			 */
			var _toUTC = function (date, oTz)
			{
				// Get Time Zone ID
			    var sTimezoneId = _getTimezoneId(oTz);

			    var oDate = new timezoneJS.Date(date, sTimezoneId);

			    oTz = {iTZMinutes : (oDate.getTimezoneOffset())};

				// Return date and add in the end the second and milliseconds.
				return _format(date, "yy-mm-dd-hh-i-s-fff", oTz);
			};

			/**
			 * Is Us Date Format Check if the month representation (mm,MM,m,M)
			 * come first of day representation (dd,DD,d,D) on date format
			 *
			 * @param sFormat
			 *            String - the format
			 * @return Boolean - whether is US date format
			 */
			var _isUSDateFormat = function (sFormat)
			{
				var m = sFormat.toLowerCase().indexOf("m");
				var d = sFormat.toLowerCase().indexOf("d");
				return (m != -1 && m < d) || (d == -1 && m != -1);
			};

			/**
			 *	Return an object containing days, hours, minutes and seconds between two date objects
			 *
			 *	@param dStartDate
			 *	@param dEndDate
			 */
			var _timeBetween = function(dStartDate,dEndDate)
			{
			    if(dStartDate < dEndDate )
			    {
					var oDate 			= {};
					var dStartDate_ms 	= dStartDate.getTime();// Convert both dates to milliseconds
					var dEndDate_ms 	= dEndDate.getTime();// Convert both dates to milliseconds
					var difference_ms 	= dEndDate_ms - dStartDate_ms;// Calculate the difference in milliseconds
					//take out milliseconds
					difference_ms 	= difference_ms/1000;
					oDate.seconds 	= Math.floor(difference_ms % 60);
					difference_ms 	= difference_ms/60;
					oDate.minutes 	= Math.floor(difference_ms % 60);
					difference_ms 	= difference_ms/60;
					oDate.hours 	= Math.floor(difference_ms % 24);
					oDate.days 		= Math.floor(difference_ms/24);

					return oDate;
				}
				else
				{
					return null;
				}
			};

			return ({
				timeBetween		: _timeBetween,
				format			: _format,
				getCurrentDate	: _getCurrentDate,
				getDateObj		: _getDateObj,
				isLess			: _isLess,
				isUSDateFormat	: _isUSDateFormat,
				parseDate		: _parseDate,
				toUTC			: _toUTC,
				getEpochTime    : _getEpochTime
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
				if(_util.isNullOrUndefined(pgsi.settings.enums)
						|| _util.isNullOrUndefined(pgsi.settings.enums[sEnumName]))
				{
					return false;
				}
				return pgsi.settings.enums[sEnumName];
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

				$.each(oEnum, function(o, i){
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

					$.each(oEnum, function(o, i){
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


			/**
			* @param {String} sEnumName - The ENUM full name.
			* @return {Array} - A key/value array with the internationalized value/label pairs for the ENUM
			*/
			var _internationalizeEnumsByLabel = function(sEnumName)
			{
				var oEnum = _fetchByName(sEnumName);

				var aFiltered = [];
				var aLabelKey= [];

				$.each(oEnum, function(o, i){


				    	aFiltered.push(i);

				});

				if(_util.isValidArray(aFiltered))
				{
					for (i in aFiltered)
					{
						var sKey = _fetchI18NByLabel(sEnumName, aFiltered[i].label);
						aLabelKey.push({key:aFiltered[i].value,value:_locale.get(sKey)});
					}
				}

				return aLabelKey;
			};

			return{
				fetchByName				: _fetchByName,
				fetchLabelByValue		: _fetchLabelByValue,
				fetchLabelsByValues		: _fetchLabelsByValues,
				fetchValueByLabel		: _fetchValueByLabel,
				fetchI18NByLabel		: _fetchI18NByLabel,
				internationalizeEnumsByLabel :_internationalizeEnumsByLabel,
				internationalizeByLabel	: _internationalizeByLabel
			};
		})();

		/**
		 * Locale methods
		 */
		var _locale = (function()
		{
			var _init = function (oMessageProperties)
			{
				jQuery.i18n.properties({
					name : _oConfig.locale.name,
					mode : _oConfig.locale.mode,
					language : pgsi.settings.user.language,
					oMessageProperties : oMessageProperties
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
		 * Message manipulation methods.
		 */
		var _message = (function ()
		{
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

			return{
				fromResponse: _fromResponse,
				hide		: _hide
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

				$target.find(':input').each(function() {

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
				$(oDom).validationEngine('attach', {
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

			return{
				attachValidation	: _attachValidation,
				clearElements		: _clearElements
			};

		})();

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
		 * Page Loader methods
		 */
		var _pageLoader = (function()
		{
			var _oLoadDefaults =
			{
				url						: null,
				$content				: null,	// Jquery Object
				$link					: null, // Jquery Object
				callback				: null,
				bUpdateUrl				: true,
				bStartProgressBar		: false,
				bStartGlobalProgressBar : false,
				oMessage				: null
			};

			var _activeMenu = function ($menu, $item)
			{
				$menu.find("a.menu-active").removeClass("menu-active");

				if ($item.length)
				{
					$item.addClass("menu-active");
				}
			};

			/**
			 * Flow control for ajax calls
			 */
			var _ajaxLoad = function(oLoadConfig)
			{

				if(oLoadConfig.bStartGlobalProgressBar != true){
					$.pgsi.progressBar.startGlobal();
				}
				var sUrl="";
				if(oLoadConfig.url.indexOf("?") != -1)
				{
					sUrl = oLoadConfig.url + "&userId=" + pgsi.settings.userContext.userId;
				}
				else
				{
					sUrl = oLoadConfig.url + "?userId=" + pgsi.settings.userContext.userId;
				}

				function fnCallback(oResponse)
				{
					oLoadConfig.$content.html(oResponse);

					// Callback for page loading
					if ($.isFunction(oLoadConfig.callback))
					{
						oLoadConfig.callback();
					}

				}

				_ajax.get({
					bCache: false,
					bInitialLoad : true,
					bJson: false,
					bInitialLoad: oLoadConfig.bInitialLoad,
					sUrl: sUrl,
					fnCallback: fnCallback
				})
			};

			/**
			 * Utility method to get/set the current page
			 */
			var _currentPage = function (sCurrentPage)
			{
				if(_util.isNullOrUndefined(sCurrentPage))
				{
					if($.address.path() === "/")
					{
						return null;
					}
					return $.address.path();
				}
				$.address.value(sCurrentPage);
			};

			var _load = function (oLoadConfig)
			{
				oLoadConfig = $.extend({}, _oLoadDefaults, oLoadConfig);

				if(_util.isNullOrUndefined(oLoadConfig.url))
				{
					return false;
				}

				var _loadThis = function()
				{
					var splitedUrl = oLoadConfig.url.split("?")[0];

					splitedUrl = splitedUrl.split("/")[0];

					// Hide or shows primary.menu
					oLoadConfig.callback = function() {

						if (splitedUrl.toUpperCase().indexOf("DASHBOARD") != -1) {
							$("nav.primary").addClass("hide");
						}

						else {
							$("nav.primary").removeClass("hide");
						}
					}
					var defaultAppMenu = $("nav.primary");
					oLoadConfig.$link = defaultAppMenu.find("li[data-url~='" + splitedUrl + "'] > a");

					_activeMenu(defaultAppMenu, oLoadConfig.$link);

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

			/**
			* Query the current url for the parameterNames
			*/
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
					if (!$.pgsi.isNullOrUndefined(parameters[i])) {
						if (parameters[i] != "|") {
							$.address.parameter(i, parameters[i]);
						}
						else {
							$.address.parameter(i, "");
						}
					}
					else {
						$.address.parameter(i, "");
					}
				}

				$.address.history(true);
			};

			/**
			* Set the page title
			* @param String currentPage
			* @param Boolean bCustomTitle - whether the page name should be extracted from the url
			*/
			var _title = function (currentPage, bCustomTitle)
			{

				var _titlePrefix		= _locale.get(_oConfig.pageLoader.pgsiLabel);
				var _titleSeparator	    = " ";

				if (bCustomTitle) {
					_titleSeparator = " - ";
					$.address.title([_titlePrefix, currentPage].join(_titleSeparator));
				}

				else {
					if(currentPage)
					{
						if (currentPage.indexOf("/") != -1)
						{
							currentPage = currentPage.split("/");
							currentPage.shift();
							currentPage = currentPage[0];
						}
						currentPage = _locale.get("commons.title." + currentPage);
					}
					else
					{
						currentPage = "";
					}

					$.address.title([_titlePrefix, currentPage].join(_titleSeparator));
				}

			};

			return{
				currentPage		: _currentPage,
				load			: _load,
				title 			: _title,
				getQueryString	: _getQueryString,
				setParameter	: _setParameter,
				setParameters	: _setParameters
			};
		})();

		/**
		 * Utility methods for the application.
		 */
		var _util = (function ()
		{
			/**
			 * Validate if a property is null or undefined.
			 *
			 * @param {Object} -
			 *            The property to be validated.
			 * @return {Boolean} - Whether the property is valid or not.
			 */
			var _isNullOrUndefined = function (oProperty)
			{
				if (oProperty === undefined || oProperty === null  || oProperty === "")
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
						// Stop Progress bar
					}
					return false;
				}
				return true;
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

					alert($.pgsi.locale("widget.action.unimplemented", sActionId));

					return;
				}


				oDialogSettings.actionId = sActionId;

				if(oDialogSettings.width  ==  null) {

					oDialogSettings.width = 500;

				}

			//	$.pgsi.clearFormElements(oDialogSettings.form);

				var actionDialogId = "#action-dialog";

				if(sActionId == "tableDialog") {

					actionDialogId = "#action-dialog-lrp";

				}

				if(sActionId == "longRunningProcessRemove") {

					actionDialogId = "#remove-lrp";

				} else if(sActionId == "longRunningProcessAbort") {

					actionDialogId = "#abort-lrp";

				}

				if(!$.pgsi.isNullOrUndefined(sDialogId)){

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
			 * Initializes the action dialog (hides the element if visible).
			 */
			var _initActionDialog = function() {
				$('.action-dialog').hide();
			}


			return{
				isNullOrUndefined	: _isNullOrUndefined,
				isValidArray		: _isValidArray,
				isValidResponse		: _isValidResponse,
				isValidPreLoad      : _isValidPreLoad,
				nTruncate			: _nTruncate,
				replaceAll			: _replaceAll,
				setStrong			: _setStrong,
				_launchActionDialog : _launchActionDialog
			};
		})();

		return {
			/* Utility methods */
			isNullOrUndefined	: _util.isNullOrUndefined,
			isValidArray		: _util.isValidArray,
			isValidResponse		: _util.isValidResponse,
			isValidPreLoad		: _util.isValidPreLoad,
			nTruncate			: _util.nTruncate,
			replaceAll			: _util.replaceAll,
			savePropertyProfile : _util.savePropertyProfile,
			setGeneralConfig	: _setGeneralConfig,
			launchActionDialog : _util._launchActionDialog,
			ajax				: _ajax,
			date                : _date,
			enums               : _enums,
			form				: _form,
			listener 			: _listener,
			locale				: _locale,
			message				: _message,
			pageLoader			: _pageLoader,
			storage				: _storage,
			progressBar			: _progressBar
		};

	})();

})(jQuery);
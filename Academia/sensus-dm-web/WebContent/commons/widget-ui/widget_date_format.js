$(document).ready(function(){
	(function ($) {
		$.date = (function() {

			var get = sensus.locale.get;

			/**
			 * Is Lower
			 * Compare two dates
			 *
			 * @param Date - dDate1
			 * @param Date - dDate2
			 * @return Boolean - Whether the dDate1 is lower or equals than dDate2
			 */
			var fnIsLower = function (dDate1, dDate2) {

				return dDate1.getTime() <= dDate2.getTime();
			};

			/**
			 * Parse Date
			 * Get a date on string format and return a Date Object
			 *
			 * @param sDateTime
			 * 			String contains date and time "03/25/2013 10:00am"
			 * @Param sFormat
			 * 			String - the date format "mm/dd/yy"
			 * @return Formated date time
			 */
			var fnParseDate = function (sDateTime, sFormat) {

				var aDateTime = sDateTime.split(" ");
				var sDate = aDateTime[0];
				var sTime = aDateTime.length > 1 ? aDateTime[1].toLowerCase() : null;
				var iHourFormat = 0;
				var dDate;

				// Format date
				if (sDate && sFormat) {

					dDate = $.datepicker.parseDate(sFormat, sDate);
				}

				// Format time
				if (sTime) {

					if (sTime.indexOf("m") != -1) {

						var sHour = sTime.split(":")[0];

						/** If hours is PM and different 12 PM
						 *		or
						 *	If hours is 12AM
						 *
						 * 	iHourFormat variable receives the value 12 for to pass 24hrs format
						 */
						if ((sTime.indexOf("pm") != -1) && (sHour != "12")
								|| (sTime.indexOf("am") != -1) && (sHour == "12")) {

							iHourFormat = 12;

						}

						sTime = sTime.replace(/(am|pm)/, "").trim();
					}

					sTime = sTime.split(":");

					dDate.setHours(iHourFormat == 12 ? (parseInt(sTime[0], 10) + iHourFormat) : sTime[0]);
					dDate.setMinutes(sTime[1]);

					// Validate if contains seconds
					if (sTime[2]) {
						dDate.setSeconds(sTime[2]);
					}

				}

				return dDate;
			};

			/**
			 * Get Current Date
			 * Call server to get the current date or get
			 * from name-space (sensus.settings.currentTime) date cache refresh each ten seconds
			 *
			 * @param bUtc
			 * 			Boolean - the flag to apply UTC time-zone
			 * @param the server current date
			 */
			var fnGetCurrentDate = function (bUtc) {

				if ($.ajaxValidator.fnIsNullOrUndefined(sensus.settings.currentTime)
						|| $.type(sensus.settings.currentTime) != "date") {

					$.ajaxValidator.fnDoCall("api/util/serverTime", null, false, function(sCurrentDate) {

						sensus.settings.currentTime = fnGetDate(sCurrentDate, {bUserTZ : false});
					});
				}

				if (bUtc) {

					// Clone date to removing references
					return new Date(sensus.settings.currentTime.getTime());
				}

				// Apply application time-zone
				return fnGetDate(sensus.settings.currentTime);
			}

			/**
			 * Set Strong
			 *
			 * @param sValue
			 * 			String - the value
			 * @return the value between "strong" tag
			 */
			var fnSetStrong = function (sValue) {

				return ": <strong>" + sValue + "</strong>";
			};

			/**
			 * Format day with 'Today' or 'Yesterday' labels
			 *
			 * @param sFormatedDate
			 * 			String - the begin of date formated "mm/dd/yy ww"
			 * @param dDate
			 * 			Date - the date
			 * @return the date with formated time "mm/dd/yy Today"
			 */
			var fnFormatDay = function (sFormatedDate, dDate) {

				if (sFormatedDate && sFormatedDate.indexOf("ww") != -1) {

					// Current Date
					var dNow = fnGetCurrentDate();

					// Today
					if (dNow.getDate() === dDate.getDate()) {

						return sFormatedDate.replace("ww", fnSetStrong(get("commons.pages.today")));
					}

					// Yesterday
					if ((dNow.getDate() - 1) === dDate.getDate()) {

						return sFormatedDate.replace("ww", fnSetStrong(get("commons.pages.yesterday")));
					}

					return sFormatedDate.replace("ww", "");
				}

				return sFormatedDate;
			};

			/**
			 * Get Valid Date Format
			 *
			 * @param sFormat
			 * 			String - the format
			 * @return the format validated
			 */
			var fnGetValidDateFormat = function (sFormat) {

				if (sFormat.indexOf("yyyy") != -1) {

					return sFormat.replace("yyyy", "yy");
				}

				return sFormat;
			};

			/**
			 * Format With Zero
			 *
			 * @param iValue
			 * 			Number - the value
			 * @return return a string with zero when value lower than ten
			 */
			var fnFormatWithZero = function (iValue) {

				if (iValue < 10) {

					return "0" + iValue;
				}

				return iValue;
			};

			/**
			 * Get Hours
			 *
			 * @param iHours
			 * 			Number - the hours
			 * @return the hours on 1-12 format
			 */
			var fnGetHour = function (iHours) {

				if (iHours == 0) {

					return 12;
				}

				if (iHours < 13) {

					return iHours;
				}

				return iHours - 12;
			};

			/**
			 * Format Time
			 * Get a format string and replace the keys with the data from date parameter
			 *
			 * @param sFormatedDate
			 * 			String - the begin of date formated "mm/dd/yy h:i:sA"
			 * @param dDate
			 * 			Date - the date
			 * @return the date with formated time "mm/dd/yy 8:12:54AM"
			 */
			var fnFormatTime = function (sFormatedDate, dDate) {

				var iHours 		= '';
				var iMinutes 	= '';
				var iSeconds	= '';

				if (!$.ajaxValidator.fnIsNullOrUndefined(dDate)) {
					iHours 		= dDate.getHours();
					iMinutes 	= dDate.getMinutes();
					iSeconds	= dDate.getSeconds();
				}

				// Hour
				if (sFormatedDate.indexOf("hh") != -1) {

					sFormatedDate = sFormatedDate.replace("hh", iHours);

				} else if (sFormatedDate.indexOf("h") != -1) {

					sFormatedDate = sFormatedDate.replace("h", fnGetHour(iHours));
				}

				// Minutes
				if (sFormatedDate.indexOf("i") != -1) {

					sFormatedDate = sFormatedDate.replace("i", fnFormatWithZero(iMinutes));
				}

				// Seconds
				if (sFormatedDate.indexOf("s") != -1) {

					sFormatedDate = sFormatedDate.replace("s", fnFormatWithZero(iSeconds));
				}

				// Period
				if (sFormatedDate.indexOf("a") != -1) {

					sFormatedDate = sFormatedDate.replace(new RegExp("a(\s|$)"),
							iHours >= 12 ? "'pm'" : "'am'");

				} else if (sFormatedDate.indexOf("A") != -1) {

					sFormatedDate = sFormatedDate.replace(new RegExp("A(( )|$)"),
							iHours >= 12 ? "'PM '" : "'AM '");
				}

				return sFormatedDate;
			};

			/**
			 * Apply Time-zone Offset
			 *
			 * @param dDate
			 * 			Date - the date
			 * @param iTimezoneOffset
			 * 			Number - the time-zone offset (minutes)
			 */
			var fnApplyTimezoneOffset = function (dDate, iTimezoneOffset) {

				// Apply time zone offset when different of null, undefined and zero
				if (iTimezoneOffset) {

					dDate.setMinutes(dDate.getMinutes() + iTimezoneOffset);
				}

				return dDate;
			};

			/**
			 * Get Time-zone Offset (Minutes)
			 *
			 * @param oTimeZone
			 * 			Object - the time zone object
			 * 				null/undefined, true or bUserTZ equals true: apply user time-zone
			 * 				iTZMinutes: (Number) the time-zone offset (minutes) to apply on date
			 * @return the date with time-zone applied
			 */
			var fnGetTimezoneOffset = function (oTimeZone) {

				if ($.ajaxValidator.fnIsNullOrUndefined(oTimeZone)
						|| oTimeZone === true || oTimeZone.bUserTZ) {

					// Return user time-zone
					return parseInt(sensus.settings.timezoneMinutes, 10);
				}

				if (oTimeZone.iTZMinutes && $.isNumeric(oTimeZone.iTZMinutes)) {

					// Return the specific time-zone
					return parseInt(oTimeZone.iTZMinutes, 10);
				}

				return 0;
			};

			/**
			 * Get date
			 *
			 * @param date
			 * 			Date - apply time-zone
			 * 			String - parse date and apply time-zone
			 * 			Number - create date and apply time-zone
			 * @param oTimeZone
			 * 			Object - the time zone object
			 * 				null/undefined, true or bUserTZ equals true: apply user time-zone
			 * 				iTZMinutes: (Number) the time-zone offset (minutes) to apply on date
			 * @return the date instanced
			 */
			var fnGetDate = function (date, oTimeZone) {

				var aDateFields;
				var iTimezoneOfset = fnGetTimezoneOffset(oTimeZone);

				// Date on format string YEAR-MONTH-DAY-HOURS-MINUTES-SECONDS
				if ($.type(date) == "string" && date.indexOf("-") != -1) {

					aDateFields = date.split("-");
					return fnApplyTimezoneOffset(new Date(aDateFields[0], (aDateFields[1] - 1), aDateFields[2],
							aDateFields[3], aDateFields[4], aDateFields[5], aDateFields[6]), iTimezoneOfset);
				}

				// Whether the date is numeric and instance of string
				if ($.type(date) == "string" && $.isNumeric(date)) {
					var dNewDate = new Date(parseInt(date, 10));
					return fnApplyTimezoneOffset(dNewDate, (iTimezoneOfset+dNewDate.getTimezoneOffset()));
				}

				// Date time
				if ($.type(date) == "number") {

					return fnApplyTimezoneOffset(new Date(date), iTimezoneOfset);
				}

				// Instance of date
				if ($.type(date) == "date") {

					// 'new Date' to clone date to removing references
					return fnApplyTimezoneOffset(new Date(date.getTime()), iTimezoneOfset);
				}

				return null;
			};

			/**
			 * Date Format
			 *
			 * D	- 	short day of week - 'Mon' to 'Sun'
			 * DD 	- 	long day of week - 'Monday' to 'Sunday'
			 * d	- 	the day number without zero
			 * dd	-	the day number with zero
			 * M	- 	short month name - 'Jan' to 'Dec'
			 * MM	- 	long month name - 'January' to 'December'
			 * m	- 	the month number without zero
			 * mm 	-	the month number with zero
			 * y	-	the year in tow digits
			 * yy	-	the full year
			 * h	-	hour until 12
			 * hh	-	hour until 23
			 * i	-	minutes
			 * s	-	seconds
			 * a	-	'am' or 'pm' marker
			 * A	-	'AM' or 'PM' marker
			 * simpleDate 	- apply the application format date
			 * day			- apply the application format with 'Today' or 'Yesterday'
			 *
			 * @param date
			 * 			Date - apply time-zone
			 * 			String - parse date and apply time-zone
			 * 			Number - create date and apply time-zone
			 * @param sFormat
			 * 			String - the format - "dd/mm/yy h:i:s A"
			 * @param oTimeZone
			 * 			Object - the time zone object
			 * 				null/undefined, true or bUserTZ equals true: apply user time-zone
			 * 				iTZMinutes: (Number) the time-zone offset (minutes) to apply on date
			 * @return String - the date formated
			 */
			var fnDateFormat = function (date, sFormat, oTimeZone) {

				var dDate;

				if ($.ajaxValidator.fnIsNullOrUndefined(date))
				{
					return null;
				}

				dDate = fnGetDate(date, oTimeZone);

				// Default values for sFormat parameter
				if (!sFormat || sFormat == "simpleDate") {

					sFormat = sensus.settings.dateFormatMask;
				}

				// Set Today or Yesterday Value
				if (sFormat == "day") {

					sFormat = fnGetValidDateFormat(sensus.settings.dateFormatMask) + " ww";
				} else {
					sFormat = fnGetValidDateFormat(sFormat);
				}

				sFormat = fnFormatTime(sFormat, dDate); // Format hour, minutes, seconds and period
				sFormat = $.datepicker.formatDate(sFormat, dDate); // Format the date: year, month and day
				sFormat = fnFormatDay(sFormat, dDate); // Set today or Yesterday

				return sFormat;
			};
			var fnRemoveTimeZoneMachine = function(date){
				var dNewDate;
				dNewDate = fnGetDate(date,date.getTimezoneOffset());
				//dNewDate.setHours(dNewDate.getHours() + dNewDate.getTimezoneOffset() / 60);
				return dNewDate.toJSON();
			};
			/**
			 * Get UTC Date
			 *
			 * @param date
			 * 			Date - the date
			 * @return the JSON date
			 */
			var fnGetUTCDate = function (date, oTz) {

				if (oTz == true) {
					oTz = {iTZMinutes : (parseInt(sensus.settings.timezoneMinutes, 10) * -1)} ;
				}

				// Return date and add in the end the second and milliseconds.
				return (fnDateFormat(date, "yy-mm-dd-hh-i", oTz) + "-00-000");
			};

			/**
			 * Is Us Date Format
			 * Check if the month representation (mm,MM,m,M)
			 * come first of day representation (dd,DD,d,D) on date format
			 *
			 * @param sFormat
			 * 			String - the format
			 * @return Boolean - whether is US date format
			 */
			var fnIsUSDateFormat = function (sFormat) {

				var m = sFormat.toLowerCase().indexOf("m");
				var d = sFormat.toLowerCase().indexOf("d");
				return (m != -1 && m < d) || (d == -1 && m != -1);
			};

			/*
			 * TODO - change public call names
			return {

				getDate			: fnGetDate,
				dateFormat		: fnDateFormat,
				parseDate		: fnParseDate,
				isLower			: fnIsLower,
				getCurrentDate	: fnGetCurrentDate,
				getUTCDate		: fnGetUTCDate,
				isUSDateFormat	: fnIsUSDateFormat
			}
			*/

			return {

				dateFormat 			: fnDateFormat,
				setDateServer		: fnGetCurrentDate,
				setCompareToDate	: fnIsLower,
				parseDate			: fnParseDate,
				fnGetDate			: fnGetDate,
				fnTimeToUTC			: fnGetUTCDate,
				isUSDateFormat		: fnIsUSDateFormat
			}

		})();
	}(jQuery));
});
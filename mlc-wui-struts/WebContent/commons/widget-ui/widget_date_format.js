/**
 *  Widget Filter JavaScript
 *
 *  Copyright 2011, QAT Brazil.
 *
 *  ! Plugin definition :
 *			@fileoverview jQuery plugin to format date from format 'Fri Oct 15 00:00:00 BRT 2010' to mask
 *			@author QAT
 *
 *  yy  = two digits year(2011)
 *  D   = Minified day name(Fri)
 *  M   = Minified month name(Oct)
 *  d   = Two digits day(15)
 *  h   = hour until 12 (12)
 *  hh  = hour until 23(23)
 *  i   = minutes(59)
 *  s   = second(59)
 *  a   = am/pm marker
 *  A   = AM/PM marker
 *  simpleDate     = date mask came from preferences(dd/mm/yy)
 *  empty formate  = date mask came from preferences(dd/mm/yy)
 *  simpleDay      = Today or yesterday or mask wanted ($.date.dateFormat("Fri Oct 15 00:00:00 BRT 2010","simpleDay","Mask Wanted"))
 *
 */
(function ($) {
	$.date = (function() {

		var aTime;

		/**
		 * Define mask from SimpleDay
		 *
		 * @return String Date Format
		 */
		var fnSimpleDay = function() {

			return "m/d/yy";
		};

		/**
		 * Apply timeZone on the date
		 *
		 * @return Object Date in the timeZone
		 */
		var fnFormatterTimeZone = function(oDate) {

			var oUtc               = oDate.getTime();
			var iDateMilliseconds  = oUtc + (3600000 * parseFloat(sensus.settings.timeZoneHours));

			return new Date(iDateMilliseconds);
		};

		/**
		 * Return the simple date format
		 *
		 * @return String Date Format
		 */
		var fnSimpleDate = function() {

			return fnConvertToJSDateFormat(sensus.settings.dateFormatMask);
		};

		/**
		 * Format year from yyyy to yy
		 *
		 * @return String Date Format
		 */
		var fnConvertToJSDateFormat = function(sFormatToConvert) {

			return (sFormatToConvert.indexOf("yyyy") != -1) ? sFormatToConvert.replace("yyyy", "yy") : sFormatToConvert;
		};

		/**
		 * Get Date Object from string
		 *
		 * @param sDate
		 * 			string, date
		 * @return date
		 * 			date java script format
		 */

		var fnGetDate = function(sDate, applyTimeZone) {
		//console.log(sDate);

			var currentTime = null;
			var dDate = null;
			var aDate = null;
			var aHour = null;

			sDate = sDate.replace(/\//g, "-");

			// Check if is Year
			if (/^\s*(\d{4})-(\d|\d{2})-(\d|\d{2})*/.exec(sDate)) {
				aDate = /^\s*(\d{4})-(\d|\d{2})-(\d|\d{2})*/.exec(sDate);
				dDate = $.datepicker.parseDate('yy/m/dd',aDate[0].replace(/\-/g, '/'));

			}  else if (/^\s*(\d|\d{2})-(\d|\d{2})-(\d{4})*/.exec(sDate)) {

				aDate = /^\s*(\d|\d{2})-(\d|\d{2})-(\d{4})*/.exec(sDate);
				dDate = $.datepicker.parseDate('m/dd/yy',aDate[0].replace(/\-/g, '/'));

			} else if(/\d{1,2}[:.]\d{1,2}[:.]\d{1,2}[\.]\d{1,3}/.exec(sDate) || /\d{1,2}[:.]\d{1,2}[:.]\d{1,2}/.exec(sDate)) { //	"20:24:23.456"

				dDate = new Date();

			} else {

				dDate = new Date(sDate.substring(0, 10) + "," + sDate.substring(sDate.length - 4, sDate.length));

			}

			aHour = /\d{1,2}[:.]\d{1,2}[:.]\d{1,2}[\.]\d{1,3}/.exec(sDate);

			if (aHour) {

				currentTime = aHour[0].split(":");
				dDate.setHours(currentTime[0]);
				dDate.setMinutes(currentTime[1]);
				var aSTime = currentTime[2].split('.');
				dDate.setSeconds(aSTime[0]);
				dDate.setMilliseconds(aSTime[1]);

			}


			if((applyTimeZone === undefined)||(applyTimeZone === "")||(applyTimeZone === " ")||applyTimeZone){

				dDate = fnFormatterTimeZone(dDate);

			}

			aTime = (dDate.getMinutes() < 10) ? [dDate.getHours(), "0"+dDate.getMinutes(), dDate.getSeconds(), dDate.getMilliseconds()]
													: [dDate.getHours(), dDate.getMinutes(), dDate.getSeconds(), dDate.getMilliseconds()];

			return dDate;

		};

		/**
		 * @param date
		 * 			date java script format
		 * @return sDate
		 * 			string date format 'd/m/yy'
		 */
		var fnFormatDateJS = function(date) {

			var dDay    = date.getDate(),
				dMonth  = date.getUTCMonth() + 1,
				dYear   = date.getFullYear(),
				sDate   = dMonth + "/" + dDay + "/" + dYear;

			return sDate;
		};

		//Format Hour
		var fnFormatHour = function(sFormat, sDateFormat) {

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

				sDateFormat = sDateFormat.replace(new RegExp("A(( )|$)"), aTime[0] != 0 && aTime[0] >= 12 ? "PM " : "AM ");

			}

			return sDateFormat;
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
		var fnFormatSimpleDay = function(sDateFormated, sDate, sElseFormat, bApplyTimeZone) {

			var dToday = new Date();
			var dYesterday = new Date();

			dYesterday.setDate(dYesterday.getDate()-1);

			dToday = fnFormatDateJS(dToday);
			dYesterday = fnFormatDateJS(dYesterday);

			if(sDateFormated === dToday) {

				sDateFormated = sensus.locale.get("commons.pages.today");

			} else {

				if(sDateFormated === dYesterday) {

					sDateFormated = sensus.locale.get("commons.pages.yesterday");

				} else {

					sDateFormated = (sElseFormat === "empty") ? "" : $.datepicker.formatDate(sElseFormat, fnGetDate(sDate, bApplyTimeZone));

				}

			}

			return sDateFormated;
		};

		return {

			/**
			 * Format Object
			 * @param sDate
			 * 				String contains date
			 * @param sFormat
			 * 				Mask to format date
			 * @returns
			 * 			Formated date
			 */
			dateFormat : function(sDate, sFormat, sElseFormat, bApplyTimeZone) {

				// Fail
				if (!sDate) {
					return undefined;
				}

				//If format empty or fnSimpleDate, get preferences formats
				if (!sFormat || sFormat === "simpleDate") {

					sFormat = fnSimpleDate();

				}

				if (sElseFormat === "simpleDate" || !sElseFormat) {

					sElseFormat = fnSimpleDate();

				}

				sFormat = fnConvertToJSDateFormat(sFormat);

				var sDateFormat = "";

				//Format Simple Day
				if(sFormat === "simpleDay")	{

					sFormat     = fnSimpleDay();
					sDateFormat = $.datepicker.formatDate(sFormat, fnGetDate(sDate,bApplyTimeZone));
					sDateFormat = fnFormatSimpleDay(sDateFormat,sDate,sElseFormat,bApplyTimeZone);
					//Format Hour
					sDateFormat = fnFormatHour(sElseFormat,sDateFormat);

				} else {

					//Format date
					sDateFormat = $.datepicker.formatDate(sFormat, fnGetDate(sDate,bApplyTimeZone));
					//Format Hour
					sDateFormat = fnFormatHour(sFormat, sDateFormat);

				}

				return sDateFormat;
			},

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
			fnTimeToUTC : function(dDate) {

				var iOffset = parseFloat(sensus.settings.timeZoneHours);
				var iOsOffset = dDate.getTimezoneOffset() / 60;
				var dif = (iOffset + iOsOffset)*(-1);
				var dNewDate = dDate.getTime() + (3600000 * (dif));
				dNewDate = new Date(dNewDate);

				return dNewDate.toJSON();
			}
		};

	})();
}(jQuery));
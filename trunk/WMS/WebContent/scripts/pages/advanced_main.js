/**
 * @namespace sensus.pages.advanced
 * @description The main namespace for the Advanced Search Page.
 * @fileoverview Defines the core functionality of the Advanced Search page.
 * @author Alex Tiveron
 */

 /**
 * The main namespace for the Advanced Search Page.
 */
sensus.pages.advanced = {

		setUrl : function() {

			var sPar          = $.address.parameterNames(),
				tagIds 		  = "",
				groupIds      = "",
				eventIds 	  = "",
				offsetIds     = "",
				alarmTypes    = "",
				warningTypes  = "";

	        for (x in sPar) {
				if (sPar.hasOwnProperty(x)) {
	                $.address.parameter(sPar[x], 0);
	            }
	        }

			//FlexNet ID
			if ($("#spSearch").val() != "") {
				$.address.parameter("query", '36|' + $("#spSearch").val());
			}

			//Alerts
			$("#alarm-container").find('input:checked').each(function (i, e) {
				alarmTypes += $(e).val() + "|";
			});

			$.address.parameter("alarm_type", alarmTypes);

			$("#warning-container").find('input:checked').each(function (i, e) {
				warningTypes += $(e).val() + "|";
			});

			$.address.parameter("warning_type", warningTypes);

			//Group
			$("#group-container").find('input:checked').each(function (i, e) {
				groupIds += $(e).val() + "|";
			});

			$.address.parameter("groups", groupIds);

			//Tags
			$("#tag-container").find('input:checked').each(function (i, e) {
				tagIds += $(e).val() + "|";
			});

			$.address.parameter("tags", tagIds);

			//Schedules
			$("#event-container").find('input:checked').each(function (i, e) {
				eventIds += $(e).val() + "|";
			});

			$.address.parameter("eventSchedule", eventIds);

			$("#offset-container").find('input:checked').each(function (i, e) {
				offsetIds += $(e).val() + "|";
			});

			$.address.parameter("offsetSchedule", offsetIds);

			//Address
			if ($("#filter-street").val() != "") {
				$.address.parameter("street", $("#filter-street").val());
			}

			if ($("#filter-city").val() != "") {
				$.address.parameter("city", $("#filter-city").val());
			}

			if ($("#filter-zip").val() != "") {
				$.address.parameter("zip", $("#filter-zip").val());
			}

			//Messages
			$.address.parameter("frequency", $("#frequency").find("option:selected").val());

			//Metrology
			if ($("#voltage").val() != "") {
				$.address.parameter("voltage", $(".voltage").find(":selected").val() + ":" + $("#voltage").val());
			}

			if ($("#current").val() != "") {
				$.address.parameter("current", $(".current").find(":selected").val() + ":" + $("#current").val());
			}

			if ($("#consumption").val() != "") {
				$.address.parameter("consumption", $(".consumption").find(":selected").val() + ":" + $("#consumption").val());
			}

			//Configuration
			$("#locked").find('input:checked').each(function (i, e) {
				$.address.parameter("configuration", 1);
			});

			if ($("#serial").val() != "") {
				$.address.parameter("serial", $("#serial").val());
			}

			if ($("#light-type").find(":selected").val() != 0) {
				$.address.parameter("lighttypes", $("#light-type").find(":selected").text());
			}

			if ($("#wattage").find(":selected").val() != 0) {
				$.address.parameter("wattage", $("#wattage").find(":selected").text());
			}

			if ($("#range").find(":selected").val() != 0) {
				$.address.parameter("range", $("#range").find(":selected").text());
			}

			if ($("#color-temperature").find(":selected").val() != 0) {
				$.address.parameter("colorTemperature", $("#color-temperature").find(":selected").text());
			}

			if ($("#part-number").val() != "") {
				$.address.parameter("partNumber", $("#part-number").val());
			}

			if ($("#firmware").find(":selected").val() != 0) {
				$.address.parameter("firmware", $("#firmware").find(":selected").text());
			}

			//Date
			$("#after").find('input:checked').each(function (i, e) {

				if ($(".after").val() != "") {
					$.address.parameter("after", $(".after").val());
				}

			});

			$("#before").find('input:checked').each(function (i, e) {

				if ($(".before").val() != "") {
					$.address.parameter("before", $(".before").val());
				}

			});

			//Presentation
			$.address.parameter("sort", $("#sort").find(":selected").val());

			$.address.parameter("length", $("#display").find(":selected").val());

		},

		/**
		 * The Date Picker Format
		 */
		datePickerFormat : function() {

			var dateFormat = sensus.settings.dateFormatMask.toLowerCase();

			if (dateFormat.indexOf("yyyy") != -1) {

				dateFormat = dateFormat.replace("yyyy", "yy");

			} else if (dateFormat.indexOf("yy") != -1) {

				dateFormat = dateFormat.replace("yy", "y");

			}

			return dateFormat;
		},

		/**
		 * Validate Date
		 */
		validateDate : function(oDate) {

			var sDate = oDate.val();

			if (sDate){

				try {

					var dateFormat = sensus.settings.dateFormatMask;
					$.datepicker.parseDate(dateFormat, sDate);
					oDate.removeClass("error");

				} catch(e) {

					oDate.addClass("error");
					return false;
				}

			}

			return true;
		}
};
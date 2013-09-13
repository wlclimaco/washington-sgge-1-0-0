//c = celsius
//f = fahrenheit
//sc = scale in celsius
//sf = scale in fahrenheit

/**
 * @fileoverview Jquery plugin to convert temperature
 * @author QATEmployee
 */
(function ($) {

	$.temperature = (function() {

		/**
		 * Function to Convert Celsius to Fahrenheit
		 * @param celsius
		 * 			Number - Celsius value
		 * @returns
		 * 			Number - Fahrenheit value
		 */
		var fnConvertToFahrenheit = function(celsius) {

			return (celsius * 9 / 5) + 32;

		};

		/**
		 * Function to Convert Fahrenheit to Celsius
		 * @param fahrenheit
		 * 			Number - Fahrenheit value
		 * @returns
		 * 			Number - Celsius value
		 */
		var fnConvertToCelsius = function(fahrenheit) {

			return (fahrenheit - 32) * 5 / 9;

		};

		/**
		 * Function to Convert Celsius to Scale
		 * @param fahrenheit
		 * 			Number - Fahrenheit value
		 * @returns
		 * 			Number - Scale in Celsius
		 */
		var fnConvertToScaleInCelsius = function(fahrenheit) {

			return Math.round(fahrenheit / 1.8);

		};

		/**
		 * Function to Convert Fahrenheit to Scale
		 * @param fahrenheit
		 * 			Number - Celsius value
		 * @returns
		 * 			Number - Scale in Fahrenheit
		 */
		var fnConvertToScaleInFahrenheit = function(celsius) {

			return Math.ceil(celsius * 1.8);

		};

		return {

			/**
			 * Convert Temperature
			 * @param value
			 * 			Number - Temperature value
			 * @param sFormat
			 * 			String - Temperature type ("c"-Celsius | "f"-fahrenheit | "sc"-scale in celsius)
			 * @returns temperature
			 * 			Number - Temperature converted
			 */
			convertTemperature : function(value, sFormat) {

				if ((value != null && value != undefined ) && sFormat) {

					if (sFormat === "c") {
						return fnConvertToCelsius(value);
					}

					if (sFormat === "f") {
						return fnConvertToFahrenheit(value);
					}

					if (sFormat === "sc") {
						return fnConvertToScaleInCelsius(value);
					}

					if (sFormat === "sf") {
						return fnConvertToScaleInFahrenheit(value);
					}

				}

				return null;
			}

		};

	})();

}(jQuery));

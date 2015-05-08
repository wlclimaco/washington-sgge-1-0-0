/**
 * @fileoverview Defines common functionality regarding the customized checkbox MLC uses.
 * @author Jose Carlos Pereira
 */
/**
 * The main namespace for checkbox related functionality.
 */
pgsi.util.checkbox = {
	/**
	 * Determine the value of checkbox selected. This method finds the
	 * corresponding value from the original Select element.
	 *
	 *
	 * @param check_selector
	 *            the selector for the checkbox element. This refers to the
	 *            select checkbox.
	 * @return the checkbox value matching the displayed description
	 */

	getOption : function(check_selector) {

		var val    = [],
			count  = 1;

		$(check_selector + " li input:checked").each(function(i) {

			val[i] = $(this).attr('value');

			//Added default values to initialize smartpoint table with Alarm and Warning filters
			if(val[i] == "on") {

				val[i] = count++;

			}

		});

		return val;
	}

}
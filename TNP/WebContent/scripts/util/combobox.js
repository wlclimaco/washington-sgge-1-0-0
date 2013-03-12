/**
 * @fileoverview Defines common functionality regarding the customized combobox MLC uses.
 * @author Anke Doerfel-Parker
 */
/**
 * The main namespace for combobox related functionality.
 */
sensus.util.combobox = {
	/**
	 * Determine the current value of a combobox. The combobox is based on an
	 * HTML Select element and only displays descriptions. This method finds the
	 * corresponding value from the original Select element.
	 * 
	 * @param oComboSelector
	 *            the selector for the combobox element. This refers to the
	 *            visible combobox, not the input that actually holds the value
	 *            for submission.
	 * @return the combobox value matching the displayed description
	 */
	getOption : function(oComboSelector) {

		var val = $(oComboSelector + " + input").val();

		return $(oComboSelector + " option:contains('" + val + "')").filter(function() {
			return $(this).html() == val;
		}).attr('value');

	}
}
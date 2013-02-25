/**
 * @fileoverview Defines common extensions to the jQuery Validator plugin.
 * @author Anke Doerfel-Parker
 */

/**
 * Parse a date field based on a localized format. This validator can be
 * assigned using the name "localdate".
 */
jQuery.validator.addMethod("localdate", function(value, element) {
	return this.optional(element) || $.parseDate(value, sensus.settings.dateFormatMask);
}, sensus.locale.get("validation.date.invalidformat", $.format($.parseDate("02/12/2002", sensus.settings.dateFormatMask), "d")));

/**
 * Parse a time field based on a localized format. This validator can be
 * assigned using the name "localtime".
 */
jQuery.validator.addMethod("localtime", function(value, element) {
	return this.optional(element) || $.parseDate(value);
}, sensus.locale.get("validation.time.invalidformat", $.format($.parseDate("8:45 pm", "h:mm t"), "t")));
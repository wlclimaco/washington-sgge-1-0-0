/**
 * @fileoverview Initialize language settings.
 */
sensus.locale = {

	init : function () {

		jQuery.i18n.properties({
			name : 'messages',
			path : sensus.settings.appRoot + "/scripts/locale/",
			mode : 'map',
			language : sensus.settings.language
		});

		// Set the locale from the sensus settings.
		$.preferCulture(sensus.settings.baseLocale);
	},

	/**
	 * Wrap the retrieval of localized strings in case we want to change the
	 * underlying implementation in the future.
	 *
	 * @param args
	 *	if only one argument is provided, return the lookup result for
	 *	this argument. If more then one argument is provide, use the first
	 *	value as lookup key and the rest of the arguments as data to fill
	 *	in the returned string.
	 * @return localized message
	 */
	get : function () {

		var args, i;

		if (arguments.length == 0) {

			return "";
		}

		if (arguments.length == 1) {

			return jQuery.i18n.prop(arguments[0]);
		}

		if (typeof (arguments[1]) === "string") {

			args = [];

			for (i = 1; i < arguments.length; i = i + 1) {

				args.push(arguments[i]);
			}

			return jQuery.i18n.prop(arguments[0], args);
		}

		return jQuery.i18n.prop(arguments[0], arguments[1]);
	}
}
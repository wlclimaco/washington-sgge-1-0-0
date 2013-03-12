/**
 * @fileoverview. Defines common localization-related functionality and
 *                initializes the localization plugins and customizes the user's
 *                settings.
 * @author Anke Doerfel-Parker
 */

/*
 * Initialize language settings.
 */
jQuery.i18n.properties( {
	name : 'messages',
	path : "api/settings/fetchmessages/",
	mode : 'map',
	language : sensus.settings.baseLocale
});

/*
 * Set the locale from the sensus settings.
 */
$.getScript(sensus.settings.appRoot + "/thirdparty/jquery/custom/cultures/globalize.culture."+sensus.settings.baseLocale.replace("_","-")+".js",function(){

	Globalize.culture(sensus.settings.baseLocale.replace("_","-"));

});

/**
 * Wrap the retrieval of localized strings in case we want to change the
 * underlying implementation in the future.
 *
 * @param args
 *            if only one argument is provided, return the lookup result for
 *            this argument. If more then one argument is provide, use the first
 *            value as lookup key and the rest of the arguments as data to fill
 *            in the returned string.
 * @return localized messsage
 */
sensus.locale.get = function() {

	if (arguments.length == 0) {

		return "";
	}

	if (arguments.length == 1) {

		return jQuery.i18n.prop(arguments[0]);

	}

	if (typeof (arguments[1]) === "string") {

		var args = [];

		for ( var i = 1; i < arguments.length; i++) {
			args.push(arguments[i]);
		}

		return jQuery.i18n.prop(arguments[0], args);
	}

	return jQuery.i18n.prop(arguments[0], arguments[1]);
}

/*
 * Once the login process has been initialized, we may need to initialize locale
 * settings here. The jquery.glob plugin does not itself need initialization but
 * if a "hybrid" locale of defaults and user-specfic settings needs to be
 * created, do it here.
 */


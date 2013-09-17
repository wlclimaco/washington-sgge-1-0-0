/**
 * Session API
 * Read, Create and Remove
 */
sensus.util.session = {

		cache : {},
		defaultKeys : ["SelectedFilters", "Filters", "Columns"],

		api : {
			create : "util/session/update",
			read : "util/session/read",
			remove : "util/session/remove"
		},

		/**
		 * Create Sessions with oData Object value
		 *
		 * @param oData
		 * 		{Object}, data object
		 * 			{
		 *				key : string,
		 *				value : string
		 * 			}
		 */
		create : function (oData) {

			sensus.util.session.cache[oData.key] = null;
			return $.ajaxValidator.fnDoCall(this.api.create, oData, false, null, null, null, true, false);
		},

		/**
		 * Read Sessions
		 *
		 * @param sKey
		 * 			{String}, key of session
		 * @param bCache
		 * 			{Boolean}, is do cache or not
		 * @return oResponse
		 * 			{Object}, sessions stored, could read session or get from cache
		 */
		read : function (sKey, bCache) {

			var oResponse;

			if ($.ajaxValidator.fnIsNullOrUndefined(sKey)) {

				return;
			}

			if (this.cache[sKey]) {

				return this.cache[sKey];
			}

			if ($.ajaxValidator.fnIsNullOrUndefined(bCache)) {

				bCache = true;
			}

			oResponse =  $.ajaxValidator.fnDoCall(this.api.read, [sKey], false, null, null, null, true, false);

			if (oResponse) {

				// Create cache
				if (bCache && oResponse) {

					this.cache = $.extend({}, this.cache, oResponse);
				}

				return oResponse[sKey];
			}

			return null;
		},

		/**
		 * Remove Sessions
		 *
		 * @param aKeys
		 * 			{Array}, keys of session
		 */
		remove : function (aKeys) {

			var i;
			var max;
			var sKey;

			if (!$.isArray(aKeys) || aKeys.length == 0) {

				aKeys = this.defaultKeys;
				this.cache = {};

			} else {

				for (i = 0, max = aKeys.length; i < max; i = i + 1) {

					sKey = aKeys[i];

					if (this.cache[sKey]) {

						this.cache[sKey] = null;
					}
				}
			}

			$.ajaxValidator.fnDoCall(this.api.remove, aKeys, false, false, null, null, true, false);
		}

};
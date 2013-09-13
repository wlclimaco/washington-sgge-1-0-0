/**
 * @namespace sensus.pages.settings
 * @description The main namespace for the Settings Pages (System and Profile).
 * @fileoverview Defines the core functionality of the Settings Page (System and Profile).
 * @author QATEmployee
 */
sensus.pages.settings = {

	api : {
		fetch : "api/settings/fetch",
		upsert : "api/settings/upsert"
	},

	typeSettings : {
		user : "user",
		system : "system"
	},

	type : null,

	oSettings : null,

	/**
	 * Fetch Settings
	 * @return Boolean, if load settings
	 */
	loadSettings : function(propertyResponse) {

		var bReturn = false;

		var fnCallback = function (oResponse) {

			// Set Settings object with the Response from fetch on BE
			sensus.pages.settings.oSettings = sensus.pages.settings.getSettings(oResponse.properties);
			bReturn = true;
		}

		if (propertyResponse) {

			fnCallback(propertyResponse);

		} else {

			$.ajaxValidator.fnDoCall(sensus.pages.settings.api.fetch, {typeSettings: sensus.pages.settings.type}, false, fnCallback);
		}

		return bReturn;
	},

	/**
	 * Get Settings
	 * @param Array
	 * 			aProperties, properties from BE
	 * @return Array of properties
	 * 			key: property name
	 * 			value: property value
	 */
	getSettings : function (aProperties) {

		var oSettings = {};
		var length = aProperties.length;
		var i = 0;
		var oProperty;

		for (; i < length; i = i + 1) {

			oProperty = aProperties[i];

			// Property Name : Property Value
			oSettings[oProperty.propertyName] = oProperty.propertyValue;
		}

		return oSettings;
	},

	/**
	 * Set Settings on page
	 */
	setSettings : function () {

		var oSettings = sensus.pages.settings.oSettings;

		// Select Fields
		$("select", ".settings-context").each(function() {

			var $this = $(this);
			var setting = oSettings[$this.attr("name")];
			var $chonsen;

			// Field <= Property Value
			$this.val(setting);

			// Whether Select Field has Chosen instance
			if ($this.hasClass("chzn-select")) {

				$chonsen = $this.next();
				$chonsen.find("a span").text($this.find("option[value='" + setting + "']").text());
				$chonsen.find(".result-selected", ".chzn-results").removeClass("result-selected");
				$chonsen.find(".active-result:contains(" + setting + ")", ".chzn-results").addClass("result-selected");
			}
		});

		// Radio Fields
		$("input:radio", ".settings-context").each(function() {

			var $this = $(this);

			// Radio Checked whether Property Value is equals
			if ($this.val() == oSettings[$this.attr("name")]) {

				$this.prop("checked", true);
			}
		});
	},

	/**
	 * Get Properties from page
	 * @return Array of properties
	 * 			key: property name
	 * 			value: property value
	 */
	getProperties : function () {

		var aProperties = [];

		// Select Fields and Radio Fields
		$("select, input:radio:checked", ".settings-context").each(function() {

			var $this = $(this);

			// Property Name : Property Value
			aProperties.push(new Property({propertyName : $this.attr("name"), propertyValue : $this.val()}));
		});

		return aProperties;
	},

	/**
	 * Save Settings
	 * Call API to save settings
	 * @param String
	 * 			sSuccessMessageWithLogout, a success message with logout link
	 * @param String
	 * 			sSuccessMessage, a success message without logout link
	 * @return Boolean
	 * 			bReturn, return success (true) or fail (false)
	 */
	saveSettings : function (sSuccessMessageWithLogout, sSuccessMessage) {

		var sOldLanguage = sensus.settings.language;
		var oRequest 	 = new PropertyRequest({properties : sensus.pages.settings.getProperties()});
		var bReturn 	 = false;
		var sMessage 	 = sSuccessMessageWithLogout;

		for (x in oRequest.properties) {

			var object = oRequest.properties[x];

			if (object.propertyName == "LANGUAGE") {

				if (sOldLanguage == object.propertyValue) {

					sMessage = sSuccessMessage;
				}
			}
		}

		var fnCallback = function(oResponse) {

			// Set Settings object with the Request from page
			sensus.pages.settings.oSettings = sensus.pages.settings.getProperties(oRequest.properties);
			bReturn = true;
		}

		$.ajaxValidator.fnDoCall(sensus.pages.settings.api.upsert, oRequest, false, fnCallback, sMessage);

		return bReturn;
	}
}
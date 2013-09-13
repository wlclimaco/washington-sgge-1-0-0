/**
 * @namespace sensus.pages.group
 * @description The creat-main namespace for the Group Page.
 * @fileoverview Defines the core functionality of the Create Group page.
 * @author QATEmployee
 */
sensus.pages.group = {

	isSubmit : false,

	/**
	 * Group Validator
	 *
	 * @param sGroupName
	 * 			[String], the group name to be validate
	 * @return the error message when invalid group name or boolean true when valid group name
	 */
	groupValidator : function (sGroupName) {

		var bExistedGroup = false;
		var oGroupForm = $("#group-form");
		var $type = $("#type");
		var $deviceType = $("#meterType");

		if ((sGroupName.replace(/\s/g, '').length == 0) || ($type.val().length === 0)
				|| sGroupName.match(/^(?:(?:[a-z0-9#]*(?:(?:(?:\s?-\s?|'s\s?|[(?:s)]'\s?|\s)[a-z0-9#]*)?))\s?)*$/i) == null) {

			if (sGroupName.replace(/\s/g, '').length == 0) {
				$('#group-name-create').validationEngine('showPrompt', sensus.locale.get('commons.pages.fieldRequired'), 'red', '', true);
			}

			if ($type.val().length === 0) {
				$type.validationEngine('showPrompt', sensus.locale.get('commons.pages.fieldRequired'), 'red', '', true);
			}

			if ($deviceType.val().length === 0) {
				$deviceType.validationEngine('showPrompt', sensus.locale.get('commons.pages.fieldRequired'), 'red', '', true);
			}

			if (sGroupName.match(/^(?:(?:[a-z0-9#]*(?:(?:(?:\s?-\s?|'s\s?|[(?:s)]'\s?|\s)[a-z0-9#]*)?))\s?)*$/i) == null) {
				$('#group-name-create').validationEngine('showPrompt', sensus.locale.get('commons.pages.specialCharacterInvalid'), 'red', '', true);
			}

			return false;

		} else if ((oGroupForm.validationEngine('validateField', '#group-name-create'))
				|| (oGroupForm.validationEngine('validateField', '#group-description'))
				|| (oGroupForm.validationEngine('validateField', '#type'))
				|| (sensus.settings.serviceType == sensus.constants.services.electric.name
						&& oGroupForm.validationEngine('validateField', '#meterType'))) {

			sensus.util.page.stopProgressBar();
			return false;

		}
		return true;
	},

	/**
	 * Create/Update group action
	 *
	 * @param oForm, sGroupName
	 * 			Object - The form object to submit action
     *          sGroupName - The name of group
	 */
	checkGroupName : function(oForm, sGroupName) {

		if ($.address.parameter('id') && sGroupName == $('#group-old-name').val().trim()) {

			$("#meterType").attr("disabled", false);
			oForm.submit();

		} else if ($('#group-name-create').val().length <= 100) {

			$.ajaxValidator.fnDoCall("api/group/fetch", {type : 'checkGroupName', groupName : sGroupName}, false, function (oResponse) {

				if (!oResponse.isGroupNameUnique) {

					sensus.util.page.showMessage("messaging-main", sensus.locale.get('group.page.alreadyExists'), "error");

					sensus.util.page.stopGlobalProgressBar();

					return;
				}

				oForm.submit();
			}, null, true);
		}
	},

	fnBackToListGroupEvent : function (e) {

		e.preventDefault();

		sensus.util.page.startGlobalProgressBar();

		var sUrl = $(this).attr("href");
		var sSession = sensus.util.session.read("SelectedFilters");

		// Remove validations alerts
		$('.formError').remove();

		sensus.util.session.remove(["SelectedFilters"]);

		if (sSession) {
			sUrl = sUrl + "?initialLoad=false&" + sSession;
		}

		$.fn.pageLoader.load(sUrl, $("#load"), null, function() {

			$.fn.pageLoader.parameter("initialLoad", null);

		}, null, false);
	},

	fnInitGroupType : function (oGroupCreateMeterType) {

		if (sensus.settings.serviceType != sensus.constants.services.electric.name) {

			oGroupCreateMeterType.hide();
		}
	}
};
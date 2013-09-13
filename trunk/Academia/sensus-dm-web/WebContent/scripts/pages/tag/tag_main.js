/**
 * @namespace sensus.pages.tag
 * @description The main namespace for Tag functionality.
 */
sensus.pages.tag = {

	maxCharacterTagName : 100,

	/**
	 * Update Select Tag
	 *
	 */
	updateSelectTag : function () {

		$.ajaxValidator.fnDoCall("api/tag/fetchAll",
				new InquiryTagRequest({endRow : 0, startRow : 0, sortExpressions : [{field : "name", direction : "Ascending"}]}),
				false, function (oResponse) {

					sensus.util.page.createOptions($('#select-tag'), oResponse.tags, "update", true);
				});
	},

	/**
	 * Create Tag
	 * When valid tag name, create the tag and reload the table and chosen components
	 *
	 * @param sTagName
	 * 			[String], the tag name
	 */
	createTag : function (sTagName) {

		var tagValidated = sensus.pages.tag.tagValidator(sTagName);

		if (tagValidated === true) {

			$.ajaxValidator.fnDoCall("api/tag/insert", new InsertRequest({name : sTagName}), false, function (oTagResponse) {

				// Reload Table
				sensus.widgets.datatable.reloadTable(sensus.pages.tag.tagTable);

				// Show success message
				sensus.util.page.showMessage("messaging-main", sensus.locale.get("tag.page.createdSuccess", sTagName), "confirm");

				// Clean input
				$(".chzn-search").find("input:visible").val("");

				// Update tag select
				sensus.pages.tag.updateSelectTag();
			});

		} else {

			$('#select_tag_chzn').validationEngine("showPrompt", tagValidated, "red", "", true);
		}
	},

	/**
	 * Tag Validator
	 *
	 * @param sTagName
	 * 			[String], the tag name to be validate
	 * @return the error message when invalid tag name or boolean true when valid tag name
	 */
	tagValidator : function (sTagName) {

		var bExistedTag = false;

		// null, undefined or empty string
		if (!(sTagName && sTagName.length && sTagName.length > 0)) {

			return sensus.locale.get('tag.page.error.required');
		}

		// existed tag
		$('#select-tag option').each(function() {
			if ($(this).text().toLowerCase() == sTagName.toLowerCase()) {
				bExistedTag = true;
			}
		});

		if (bExistedTag) {
			return sensus.locale.get('tag.page.error.exist');
		}

		// max characters
		if (sTagName.length > sensus.pages.tag.maxCharacterTagName) {

			return sensus.locale.get('commons.pages.max.characterJs', sensus.pages.tag.maxCharacterTagName.toString());
		}

		// valid characters
		if (sTagName.match(/^(?:(?:[a-z0-9#]*(?:(?:(?:\s?-\s?|'s\s?|[(?:s)]'\s?|\s)[a-z0-9#]*)?))\s?)*$/i) == null) {

			return sensus.locale.get('commons.pages.specialCharacterInvalid');
		}

		return true;
	}
};
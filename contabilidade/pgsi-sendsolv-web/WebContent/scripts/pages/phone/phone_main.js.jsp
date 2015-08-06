<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.phone
 * @fileoverview The main namespace for the Phone.
 */

pgsi.pages.phone = {

	filterPhones : function(oContactList){
		var oPhoneList = [];
		var oPhone;

		for (var i = 0; i < oContactList.length; i++) {
			// Check for Contact of type Phone
			if (oContactList[i].contactTypeValue == 2
				|| oContactList[i].contactTypeValue == 5
				|| oContactList[i].contactTypeValue == 8) {

				if ($.pgsi.isNullOrUndefined(oPhone)) {
					oPhoneList.push(oContactList[i]);
				}
			}
		}

		return oPhoneList;
	},

	form : {

		fnInitSelectmenu : function($elem){
			$elem.selectmenu({
				change : function(event, ui) {
					var $select = $(event.target);

					pgsi.util.page.form.initRequiredSelects($select);

				},

				create : function(event, ui) {

					if ($(event.target).hasClass("phone-country")) {
						var sId = $(event.target).prop("id");
						var sSelectors = "#" + sId + "-button" + ", #" + sId + "-menu";

						var sKey;
						var iKeyCount = 0;
						var iKeyCode;

						// Custom behavior on keypress. Search will be made on the <option> value instead of the label
						// Addresses the + in the beggining of the country code labels. Ex.: +1 (US)
						$(sSelectors).keypress(function(e) {

							iKeyCode = e.which;

							if (String.fromCharCode(iKeyCode).toUpperCase() === sKey) {
								iKeyCount++;
							}
							else {
								sKey = String.fromCharCode(iKeyCode).toUpperCase();
								iKeyCount = 0;
							}

							var $phoneCountry = $(event.target);
							var $options = $phoneCountry.find('option[value^="' + sKey + '"]');

							if ($options.length > 0) {
								if (iKeyCount >= $options.length) {
									iKeyCount = 0;
								}

								$phoneCountry.val($options.get(iKeyCount).value);
								$(event.target).selectmenu("refresh").next().outerWidth(125);
							}
						});
					}
				}
			});
		},

		// Layout setup for input fields
	 	fnInitForm : function() {

			$phoneTemplate = $("#phone-template");
	 		pgsi.pages.phone.form.fnInitSelectmenu($phoneTemplate.find("select"));
	 		pgsi.util.page.form.fnInitTolltip($phoneTemplate.find('.error'));

			$phoneTemplate.on("click", ".row-form .icon-remove", function() {

				var $row = $(this).parents(".row-form");

				$row.addClass("hide");

				var $phoneTemplate = $("#phone-template");
				$(this).parents(".row-form").find("input, select").removeClass("required");

				if ($phoneTemplate.find("input[type='radio']:visible:checked").length === 0 ) {
					$firstPhone = $phoneTemplate.find(".row-form:visible:first");
					$firstPhone.find("input[type='radio']").prop("checked", true);
					$firstPhone.find(".close-button-form").addClass("hide");
				}

				$row.find(".model-action").val("delete");

			});

			$phoneTemplate.find(".phone-number, .phone-extension").inputmask("decimal");

			// Default phone code to USA
			$phoneTemplate.find(".phone-country").val("USA").selectmenu("refresh").outerWidth(115);

	 	},

	 	/*
	 	* Filter Phone enums from ContactTypeEnum
		*
		* @param {Array} Array containing key/value internationalized ContactTypeEnum
		* @returns {Array} Filtered array containing Phone enums only
		*/

		filterPhoneEnums : function(aContactTypeEnums) {

			var aPhoneEnums = [];

			for (var i = 0; i < aContactTypeEnums.length; i++) {
				// ContactTypeEnum holds information for both phone and email
		 		// Needs to be filtered properly to contain only phone enum values in this case
		 		// 2 = PHONE_HOME, 5 = PHONE_HOME and 8 = PHONE_CELL
				if (aContactTypeEnums[i].key == 2  || aContactTypeEnums[i].key == 5 || aContactTypeEnums[i].key == 8) {
					aPhoneEnums.push(aContactTypeEnums[i]);
				}
			}

			return aPhoneEnums;
		},


		setFieldSizes : function() {
			$("form .phone-type").next("span").outerWidth(120);
			$("form .phone-country").next("span").outerWidth(125);
			$("form .phone-number").outerWidth(132);
			$("form .phone-extension").outerWidth(82);
			$("form #contact-risk").outerWidth(84);
		},

		fillObject : function() {

			var aPhones = [];
			var oPhone;
			var sPhoneId;
			var sModelAction;
			var iCountryCode;

			// Phone Contact
			$("#phone-template").find(".row-form").each(function() {

				oPhone = new Phone();
				sPhoneId = $(this).find(".phone-id").val();
				sModelAction = $(this).find(".model-action").val();
				iCountryCode = $(this).find(".phone-country").val();
				oPhone.contactTypeValue = $(this).find(".phone-type").val();
				oPhone.id = sPhoneId;
				oPhone.type = "phone";

				if (sModelAction.toUpperCase().indexOf("DELETE") != -1) {
					if (!$.pgsi.isNullOrUndefined(sPhoneId) && sPhoneId.length > 0) {
						oPhone.modelAction = "DELETE";
						aPhones.push(oPhone);
					}
					return;
				}

				if (!$.pgsi.isNullOrUndefined(sPhoneId) && sPhoneId.length > 0) {
					oPhone.modelAction = "UPDATE";
				}

				else {
						oPhone.modelAction = "INSERT";
				}

				oPhone.number = parseInt($(this).find(".phone-number").val());
				oPhone.extension = $(this).find(".phone-extension").val();

				if (!$.pgsi.isNullOrUndefined(oPhone.extension)) {
					oPhone.extension = parseInt(oPhone.extension);
				}

				var sVersion = $(this).find(".phone-version").val();

				if (!$.pgsi.isNullOrUndefined(sVersion) && sVersion.length > 0) {
					oPhone.version = parseInt(sVersion);
				}

				oPhone.country = { code : iCountryCode };

				if ($(this).find('input:checked').prop("checked")) {
					oPhone.priorityValue = 1;
				}

				else{
					oPhone.priorityValue = 2;
				}

				aPhones.push(oPhone);

			});

			return aPhones;
		},
		/*
	 	* Returns a new row for the phone template
	 	*/
	 	createNewNode : function() {
	 		var $copy = $("#phone-template").find(".container").find(".row-form").first().clone();
			$copy.find('input').val("");
			$copy.find(".close-button-form").removeClass("hide");
			$copy.find('input[type="radio"]').attr("checked", false);
			$copy.find(".ui-selectmenu-button").remove();
			$copy.find("select").prop("id", "");
			$copy.find("select, input.phone-number").addClass("required");
			var sCopy = $("<div class='row-form'>" + $copy.html() + "</div>");

			return sCopy;
 		},

		fillFormFields : function(oContactList) {
			var oPhoneList = pgsi.pages.phone.filterPhones(oContactList);

			var oPhone;
			var $phoneTemplate = $("#phone-template");
			var $phone;
			var $copy;
			var iIndex;

			// Iterates over the Phone list and adds new rows
			for (var i=0; i < oPhoneList.length; i++) {
				oPhone = oPhoneList[i];

				$copy = $(pgsi.pages.phone.form.createNewNode());

				if (i > 0) {
					$copy.find(".phone-type").attr("name", $copy.find(".phone-type").attr("name") + i);
					$copy.find(".phone-country").attr("name", $copy.find(".phone-country").attr("name") + i);
					$copy.find(".phone-number").attr("name", $copy.find(".phone-number").attr("name") + i);
				}

				if (i == 0) {
					$phoneTemplate.find(".row-form").remove();
				}

				$phoneTemplate.find(".container").append("<div class='row-form'>" + $copy.html() + "</div>");
				$phoneTemplate = $("#phone-template");

				$phone = $phoneTemplate.find(".container").find(".row-form:last-child");

				pgsi.pages.phone.form.fnInitForm($phone);

				$phone.find(".phone-id").val(oPhone.id);
				$phone.find(".phone-version").val(oPhone.version);
				$phone.find(".phone-type").val(oPhone.contactTypeValue).selectmenu('refresh').nextAll(".ui-selectmenu-button").removeClass("required-field");
				$phone.find(".phone-number").val(oPhone.number);
				$phone.find(".phone-extension").val(oPhone.extension);
				$phone.find(".phone-country").val(oPhone.country.code).selectmenu('refresh').nextAll(".ui-selectmenu-button").removeClass("required-field");

				pgsi.pages.phone.form.setFieldSizes();

				if (oPhone.priorityValue === 1) {
					$phone.find("input[type='radio']").prop("checked", true);
					$phone.find(".close-button-form").addClass("hide");
				}

				else {
					$phone.find(".close-button-form").removeClass("hide");
				}
			}

			$phoneTemplate = $("#phone-template");

			$phoneTemplate.find(".phone-number, .phone-extension").inputmask("decimal");

			// mark the first phone as primary in case no other field is selected
			if ($phoneTemplate.find("input[type='radio']:checked").length == 0 ) {
				$firstPhone = $phoneTemplate.find(".row-form:eq(0)");
				$firstPhone.find("input[type='radio']").prop("checked", true);
				$firstPhone.find(".close-button-form").addClass("hide");
			}
		}
	},

	view : {

		fillFields : function(oContactList) {
			var oPhoneList = oContactList;

			var oPhone;
			var sPhoneList = "";
			var sTypePhone="";

			$("#phone-container").empty();

			for (var i = 0; i < oPhoneList.length; i++) {
				oPhone = oPhoneList[i];

				sPhoneList += '<div class="primary"><span> +'+ oPhone.telefoneTypeEnum+' </span><span>('  + oPhone.ddd + ')<span"> '+oPhone.numero+'</span></div>';
			}

			$("#phone-container").append(sPhoneList);
		}
	}
};


</script>

</sec:authorize>
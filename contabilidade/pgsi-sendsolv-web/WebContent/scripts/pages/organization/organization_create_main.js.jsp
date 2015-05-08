<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
	/**
	 * @namespace pgsi.pages.organization
	 * @fileoverview The main namespace for the Organization Create Page.
	 */

	pgsi.pages.organization.form = {
		fnInitForm : function(sModelAction) {

			$("#payroll-centralized").addClass("required required-field");

			// Removing Mixed and not Centralized options for PayRoll
			var oPayrollTypes = $.pgsi.enums.internationalizeEnumsByLabel("com.prosperitasglobal.sendsolv.model.PayrollTypeEnum");
			var oValidPayrollTypes = new Array();

			for(var i = 0; i < oPayrollTypes.length; i++ ) {
				if (oPayrollTypes[i].key != 2 && oPayrollTypes[i].key != 3) {
					oValidPayrollTypes.push({ "key" : oPayrollTypes[i].key, "value" : oPayrollTypes[i].value });
				}
			}

			$("#payroll-centralized").fnLoadDropDownList(oValidPayrollTypes);

			$("#total-locations").inputmask('Regex');

			pgsi.util.page.business.form.fnInitForm();

			pgsi.pages.organization.form.displayOrganizationFields();
			pgsi.pages.organization.form.setFieldSizes();

			if (!$.pgsi.isNullOrUndefined(sModelAction) && sModelAction.toUpperCase() === "INSERT") {
				pgsi.pages.address.form.setDefaultValues();
			}

			pgsi.pages.address.form.setFieldSizes();
			pgsi.pages.phone.form.setFieldSizes();

		},

		setFieldSizes : function() {
			// adjust specific fields
			$("#organization-name").outerWidth(360);
			$("#dbaname").outerWidth(360);
			pgsi.util.page.business.form.setFieldSizes();
		},

		/**
		 * Validate the fields required at Organization Form
		 */
		validator : $("#business-form").validate({
			ignore : "",

			invalidHandler : function(form, validator) {
				$.each(validator.errorList, function(index, value) {
					$(value.element).addClass("error");

					if (value.element.nodeName.toLowerCase() == 'select') {
						$(value.element).next('span').addClass("error");
					}
				});
			},
			rules: {
				sic: {
					SIC:18
				},
				organizationNaics: {
					NAICS:18
				}
			}
		}),


		/**
		 * Ajax call that will submit the request ajax
		 *
		 * @param {String} sUrlAdress
		 * 			The URL to post
		 * @param {String} sMessage
		 * 			Message that will display after the ajax call
		 */
		ajaxCall : function(sUrlAdress, sMessage, sMainModelAction,bIsReturn) {

			// Remove input masks
			pgsi.util.page.business.form.maskFields.fnUnmask();
			// Validate the form
			var bValidForm = pgsi.pages.organization.form.validator.form();

			if (!bValidForm)
			{
				pgsi.util.page.business.form.maskFields.fnMask();
				return false;
			}
			$.pgsi.progressBar.startGlobal();
			var request = pgsi.pages.organization.form.fnFillRequestObject(sMainModelAction);

			var fnCallback = function(oResponse) {

				if (oResponse.operationSuccess == true) {
					if (!$.pgsi.isNullOrUndefined(oResponse.organizationList[0])
								&& !$.pgsi.isNullOrUndefined(oResponse.organizationList[0].id)) {
							var nOrganizationId = oResponse.organizationList[0].id;
							$.pgsi.pageLoader.load({
								url : "organization/view?tab=info&organizationId="
										+ nOrganizationId,
								$content : $("#load"),
								bStartProgressBar : false
							});
						}
				}

				else {
					pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
				}
			};

			// Insert the Organization
			$.pgsi.ajax.post({
				sUrl : sUrlAdress,
				oRequest : request,
				fnCallback : fnCallback
			});

		},

		fnFillOrganization : function(oResponse) {

				pgsi.util.page.form.fnInitForm();

				var oOrganization = oResponse.organizationList[0];

				// fill organization-specific fields
				$("#payroll-centralized").val(
						oOrganization.isPayrollCentralizedValue);
				$("#payroll-centralized").selectmenu('refresh').nextAll(
						".ui-selectmenu-button").removeClass("required-field");
				$("#total-locations").val(oOrganization.numberOfLocations);

				// fill business fields
				pgsi.util.page.business.form.fillFormFields(oOrganization);
				// fill phone fields
				pgsi.pages.phone.form.fillFormFields(oOrganization.contactList);
				// fill address fields
				pgsi.pages.address.form.fillFormFields(oOrganization.contactList);
		},

		displayOrganizationFields : function(){
				$(".content").find('.organization').removeClass("hide");
				$(".content").find('.location').addClass("hide");
				$(".content").find('.location input').removeClass('required');
		},

		fnFillRequestObject : function(sModelAction) {
				var request = new OrganizationMaintenanceRequest();
				var $payroll = 	$("#payroll-centralized");

				// fill common organization fields - method returns basic business objects (i.e things organizations and orgs have in common)
				request.organization = pgsi.util.page.business.form.fillObject(sModelAction);
				// fill organization-specific fields
				// payroll
				request.organization.isPayrollCentralizedValue = $payroll.val();

				request.organization.numberOfLocations = $('#total-locations').val();
				request.organization.dbaName = $('#dbaname').val();
				request.organization.businessTypeValue = 1;

				if (!$.pgsi.isNullOrUndefined(pgsi.pages.organization.oOrganization)) {
					request.organization.statusValue = pgsi.pages.organization.oOrganization.statusValue;
				}

				pgsi.pages.organization.oOrganization = null;

				//fill in version locking
				request.organization.version = pgsi.version.versionBusiness;

				// fill in phone fields
				// returns a list of phones
				request.organization.contactList = new Array();

				// Address Contact
				// returns a single address field
				var oAddress = pgsi.pages.address.form.fillObject(sModelAction);

				if (!$.pgsi.isNullOrUndefined(oAddress)) {
					request.organization.contactList.push(oAddress);
				}

				var aPhones = pgsi.pages.phone.form.fillObject();

				for (var i=0; i < aPhones.length; i++) {
					request.organization.contactList.push(aPhones[i]);
				}

				return request;
		}

		};
	</script>

</sec:authorize>
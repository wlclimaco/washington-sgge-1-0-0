<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

	<script type="text/javascript">
		pgsi.pages.location.form = {

			fnInitForm: function(sModelAction) {
				pgsi.util.page.form.fnInitForm();
				pgsi.util.page.business.form.fnInitForm();

				if (!$.pgsi.isNullOrUndefined(sModelAction)
					&& sModelAction.toUpperCase() === "INSERT") {
					pgsi.pages.address.form.setDefaultValues();
				}
				pgsi.pages.location.form.setFieldSizes();
				pgsi.pages.location.form.displayLocationFields();

			},

			setFieldSizes : function() {
				// adjust specific fields
				$("#key").outerWidth(180);

				pgsi.util.page.business.form.setFieldSizes();
				pgsi.pages.address.form.setFieldSizes();
				pgsi.pages.phone.form.setFieldSizes();
			},

			/**
			 * Validate the fields required at Location Form
			 */
			validator : $("#business-form").validate({
				ignore : "",
				invalidHandler : function(form, validator) {
					$.each(validator.errorList, function(index, value) {
						if (value.element.nodeName.toLowerCase() == 'select') {
							$(value.element).next('span').addClass("error");
						}
							else {
							$(value.element).addClass("error");
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
			ajaxCall : function(sUrlAdress, sMessage, mainModelAction) {

				// Remove input masks
				pgsi.util.page.business.form.maskFields.fnUnmask();
				// Validate the form
				var bValidForm = pgsi.pages.location.form.validator.form();

				if (!bValidForm)
				{
					pgsi.util.page.business.form.maskFields.fnMask();
					return false;
				}

				var request = pgsi.pages.location.form.fnFillRequestObject(mainModelAction);

				var fnCallback = function (oResponse){
					if (oResponse.operationSuccess == true) {
						if (!$.pgsi.isNullOrUndefined(oResponse.locationList[0]) && !$.pgsi.isNullOrUndefined(oResponse.locationList[0].id)){
							var nLocationId = oResponse.locationList[0].id;
							$.pgsi.pageLoader.load({
								url: "location/view?locationId=" + nLocationId,
								$content: $("#load"),
								bStartProgressBar : false
							});
						}
					}
					else {
						pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
					}

				};

				// Insert the Location
				$.pgsi.ajax.post({
					sUrl : sUrlAdress,
					oRequest : request,
					fnCallback : fnCallback
				});
			},

			fnFillLocation : function(data) {

				pgsi.util.page.form.fnInitForm();

				// fill location-specific fields
				var oLocation = data.locationList[0];
				$("#parentKey").val(oLocation.parentOrganizationId);

				$("#organization-key").val($("#parent-organization-name-field").text());

				// fill business fields
				pgsi.util.page.business.form.fillFormFields(oLocation);
				// fill phone fields
				pgsi.pages.phone.form.fillFormFields(oLocation.contactList);
				// fill address fields
				pgsi.pages.address.form.fillFormFields(oLocation.contactList);
			},

			fnFillRequestObject : function(sModelAction){
				var request = new LocationMaintenanceRequest();

				// fill common location fields - method returns basic business objects (i.e things locations and orgs have in common)
				request.location = pgsi.util.page.business.form.fillObject(sModelAction);

				if (!$.pgsi.isNullOrUndefined(pgsi.pages.location.oLocation)) {
					request.location.statusValue = pgsi.pages.location.oLocation.statusValue;
				}
				pgsi.pages.location.oLocation = null;

				// fill location-specific fields
				request.location.parentOrganizationId = $("#parentKey").val();

				request.location.businessTypeValue = 2;

				//fill in version locking
				request.location.version = pgsi.version.versionBusiness;

				// fill in phone fields
				// returns a list of phones
				request.location.contactList = new Array();

				// Address Contact
				// returns a single address field
				var oAddress = pgsi.pages.address.form.fillObject(sModelAction);

				if (!$.pgsi.isNullOrUndefined(oAddress)) {
					request.location.contactList.push(oAddress);
				}

				var aPhones = pgsi.pages.phone.form.fillObject();

				for (var i=0; i < aPhones.length; i++) {
					request.location.contactList.push(aPhones[i]);
				}

				return request;
			},

			displayLocationFields : function(){
				$(".content").find('.location').removeClass("hide");
				$(".content").find('.organization').addClass("hide");
				$(".content").find('.organization input').removeClass('required');
			}
		};
	</script>

</sec:authorize>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">

pgsi.pages.address = {
		form : {
			makeItOptional : function() {

				$("#address-template").find("input, select").removeClass("required").prop("placeholder", "");

				$("#country").nextAll(".ui-selectmenu-button").removeClass("required-field");

				$("#state").nextAll(".ui-selectmenu-button").removeClass("required-field");

				$("#address-template").on("blur", "input, span", function(){
					var isAnyFieldFilled = false;

					$("#address-template").find("input, select").each(function() {

						if ( (!$.pgsi.isNullOrUndefined($(this).val()) && $(this).val().length > 0)  || $(this).attr("selected") ) {
							isAnyFieldFilled = true;
							return false;
						}
					});

					if (isAnyFieldFilled) {
						// Set address fields as required
						$("#address-template").find("input").addClass("required").prop("placeholder","*");
						$("#address-template").find("select").addClass("required");
						pgsi.util.page.form.fnInitSelectmenu($("#address-template").find("select"));
					}

					else {
						$("#address-template").find("input, select").removeClass("required").removeClass("error").prop("placeholder","");
						$("#address-template").find("span").removeClass("required").removeClass("required-field").removeClass("error").prop("placeholder","");
					}

					$("#street-address-line-2").removeClass("required").prop("placeholder", "");
					$("#street-address-line-3").removeClass("required").prop("placeholder", "");
					$("#street-address-line-4").removeClass("required").prop("placeholder", "");
				});
			},

			setDefaultValues : function() {

				$("#country").val("USA").selectmenu('refresh').nextAll(".ui-selectmenu-button").removeClass("required-field");

				var fnCallback = function(oResponse){
					$("#state").find("option").remove();
					$("#state").prepend("<option></option");
					$("#state").fnLoadDropDownList(oResponse);
					$("#state-column").removeClass("hide");
					$("#state").addClass("required");
					$("#state").selectmenu('refresh').nextAll(".ui-selectmenu-button").addClass("required required-field");
					$.pgsi.progressBar.stop();
				};

				$.pgsi.ajax.get(
				{
					sUrl : "fetchstates?code=USA",
					fnCallback : fnCallback
				});
			},

			setFieldSizes : function() {
				$("#street-address-line-1").outerWidth(324);
				$("#street-address-line-2").outerWidth(324);
				$("#street-address-line-3").outerWidth(324);
				$("#street-address-line-4").outerWidth(324);
				$("#country-button").outerWidth(255);
				$("#city").outerWidth(255);
				$("#state-button").outerWidth(155);
				$("#zip-postal-code").outerWidth(155);
			},

			fillObject : function() {
				var oAddress = new Address;

				// Address Contact
				var sCountryId = $("#country").val();
				var sCountryDescription = $("#country").find('option:selected').html();
				var sStreetAddress1 = $("#street-address-line-1").val();
				var sStreetAddress2 = $("#street-address-line-2").val();
				var sStreetAddress3 = $("#street-address-line-3").val();
				var sStreetAddress4 = $("#street-address-line-4").val();
				var sCity = $("#city").val();
				var sPostalCode = $("#zip-postal-code").val();
				var sState = $("#state").val();
				var iAddressId = $("#address-id").val();

				if (isNaN(iAddressId) || iAddressId == 0) {
					iAddressId = null;
				}

				oAddress.addressLine1 = sStreetAddress1;
				oAddress.addressLine2 = sStreetAddress2;
				oAddress.addressLine3 = sStreetAddress3;
				oAddress.addressLine4 = sStreetAddress4;
				oAddress.cityName = sCity;

				if ($.pgsi.isNullOrUndefined(sState) || sState.length === 0) {
					oAddress.stateProvinceRegion = null;
				}

				else {
					oAddress.stateProvinceRegion = {
						id : parseInt(sState)
					};
				}

				if ($.pgsi.isNullOrUndefined(sCountryId) || sCountryId.length === 0) {
					oAddress.country = null;
				}

				else {
					oAddress.country = {
						code : sCountryId,
						description : sCountryDescription
					};
				}

				oAddress.postalCode = sPostalCode;
				oAddress.id = iAddressId;

				var sModelAction;

				if (iAddressId) {
					sModelAction = "UPDATE";
				}

				else {
					sModelAction = "INSERT";
				}

				var bFilled = false;

				// Remove empty fields
				if (sModelAction === "INSERT") {
					for (var property in oAddress) {
						if (oAddress.hasOwnProperty(property)) {
		    				if ($.pgsi.isNullOrUndefined(oAddress[property]) || oAddress[property].length === 0) {
		    					delete oAddress[property];
		    				}
		    				else {
		    					bFilled = true;
		    				}
						}
					}
				}

				else {
					bFilled = true;
				}

				// Check if object is filled
				if (bFilled) {
					oAddress.priority = 1;
					oAddress.type = "address";
					oAddress.contactTypeValue = 7;
					//fill in version locking
					oAddress.version = pgsi.version.versionAddress;
					oAddress.modelAction = sModelAction;

				}

				else {
					oAddress = null;
				}

				return oAddress;
			},

			fillFormFields : function(oContactList) {

				var oAddressList = [];

				for (var i = 0; i < oContactList.length; i++) {
					// Check for Contact of type Address
					if (oContactList[i].contactTypeValue == 7) {
						if ($.pgsi.isNullOrUndefined(oAddress)) {
							oAddressList.push(oContactList[i]);
						}
					}
				}

				var oAddress = oAddressList[0];

				if (oAddress) {

					if (!$.pgsi.isNullOrUndefined(oAddress.country)) {
						$("#country").val(oAddress.country.code).selectmenu(
							'refresh').nextAll(".ui-selectmenu-button")
							.removeClass("required-field").outerWidth(255);
					}

					$("#address-id").val(oAddress.id);

					$("#street-address-line-1").val(oAddress.addressLine1);
					$("#street-address-line-2").val(oAddress.addressLine2);
					$("#street-address-line-3").val(oAddress.addressLine3);
					$("#street-address-line-4").val(oAddress.addressLine4);
					$("#city").val(oAddress.cityName);
					$("#zip-postal-code").val(oAddress.postalCode);

					pgsi.version.versionAddress = oAddress.version;

					$("#state-column").addClass("hide");

					if (!$.pgsi.isNullOrUndefined(oAddress.country)) {

						var fnCallback = function(oResponse) {

							$("#state").find("option").remove();
							$("#state").fnLoadDropDownList(oResponse);
							$("#state").prepend("<option></option>");
							$("#state-column").removeClass("hide");
							if (oAddress.stateProvinceRegion !== null) {
								$('#state').val(oAddress.stateProvinceRegion.id).selectmenu('refresh').nextAll(".ui-selectmenu-button").removeClass("required-field");
							}
							$.pgsi.progressBar.stop();
						};

						$.pgsi.ajax.get({
							sUrl : "fetchstates?code=" + oAddress.country.code,
							fnCallback : fnCallback
						});
					}
				}
			},

			fnInitForm : function() {

				$("#country").on("selectmenuchange", function( event, ui ) {

					if (!$.pgsi.isNullOrUndefined($(this).val())) {
						$("#state-column").addClass("hide");

						var fnCallback = function(oResponse){
							$("#state").find("option").remove();
							$("#state").prepend("<option></option");
							$("#state").fnLoadDropDownList(oResponse);
							$("#state-column").removeClass("hide");
							$("#state").addClass("required");
							$("#state").selectmenu('refresh').nextAll(".ui-selectmenu-button").addClass("required required-field");
							$("#state-button").outerWidth(255);
							$.pgsi.progressBar.stop();
						};

						$.pgsi.ajax.get(
						{
							sUrl : "fetchstates?code=" + $(this).val(),
							fnCallback : fnCallback
						});
					}

					else {
						$("#state").find("option").remove();
						$("#state").prepend("<option></option");
						$("#state").selectmenu('refresh').nextAll(".ui-selectmenu-button").addClass("required required-field");
						$("#state-column").addClass("hide");
					}

				});

				// member document Issuing State/Province

				$("#idcountry").on("selectmenuchange", function( event, ui ) {

					var fnCallback = function(oResponse){
						$("#idstate").find("option").remove();
						$("#idstate").prepend("<option></option");
						$("#idstate").fnLoadDropDownList(oResponse);
						$('#idstate').nextAll(".ui-selectmenu-button").addClass("required");
						$("#idstate-column").removeClass("hide");
						$("#idstate").selectmenu('refresh');
						$.pgsi.progressBar.stop();
					};

					$.pgsi.ajax.get(
						{
							sUrl : "fetchstates?code=" + $(this).val(),
							fnCallback : fnCallback
						});
				});

				$("#zip-postal-code").inputmask("Regex");


			}
		},

		view : {
			fillFields : function(oContactList){
//debugger
				var oAddress = oContactList;
				var Shtml="",sCidade="",sEstado="";

				for (var i = 0; i < oContactList.length; i++) {
					if (!$.pgsi.isNullOrUndefined(oAddress[i].cidade)) {
						sCidade = oAddress[i].cidade.nome;
					}
					if (!$.pgsi.isNullOrUndefined(oAddress[i].estado)) {
						sEstado = "- "+oAddress[i].estado.abreviacao;
					}
					Shtml = oAddress[i].enderecoType +" "+oAddress[i].logradouro +" "+oAddress[i].numero +" "+oAddress[i].bairro
					+'<br>'+sCidade+" "+sEstado+" "+oAddress[i].cep;

				}
				$("#street-address-line-"+(i + 1)+"-field").text("");
				$("#street-address-line-"+(i + 1)+"-field").append(Shtml)
			}
		}
}

</script>

</sec:authorize>
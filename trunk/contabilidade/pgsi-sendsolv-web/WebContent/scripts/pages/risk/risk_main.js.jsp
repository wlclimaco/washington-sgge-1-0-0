<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">

pgsi.pages.risk = {
		form : {

			removeLoweRiskOptions : function(sCurrentValue, $selectmenu) {

				// Remove lower risk options
				// Enum Values
				// HIGH = 0
				// LOW = 1
				// MEDIUM  = 2
				// UNKNOWN  = 3
				var oRiskValues = $.pgsi.enums.internationalizeEnumsByLabel("com.prosperitasglobal.sendsolv.model.RiskLevelEnum");
				var oFilteredValues = new Array();
				var key;

				sCurrentValue = parseInt(sCurrentValue);

				$.each(oRiskValues, function(index, item) {

					key = parseInt(item.key);

					if (sCurrentValue === 3) {
						oFilteredValues.push(item);
					}

					else if (sCurrentValue === 1 && key !== 3) {
						oFilteredValues.push(item);
					}

					// Medium
					else if (sCurrentValue === 2 && (key !== 3 && key !== 1))  {
						oFilteredValues.push(item);
					}

					// High
					else if (sCurrentValue === 0 && (key !== 3 && key !== 1 && key !== 2)) {
						oFilteredValues.push(item);
					}
				});

				$selectmenu.fnLoadDropDownList(oFilteredValues);
				$selectmenu.val(sCurrentValue).selectmenu("refresh").nextAll(".ui-selectmenu-button").removeClass("required-field").outerWidth(150);
			},

			fillFormFields : function(oRisk) {

				$("#risk-dialog-level-value").val(oRisk.riskLevelValue);

				if ($("#risk-dialog-level-value").val() !== "3") {
					$("#risk-dialog-note").addClass("required").prop("placeholder", "*");
				}

				else {
					$("#risk-dialog-note").removeClass("required").prop("placeholder", "");
				}
				if (!$.pgsi.isNullOrUndefined(oRisk.riskLevelNote) && oRisk.riskLevelNote.length > 0) {
					$("textarea.risk-note").val(oRisk.riskLevelNote);
				}

				$("#risk-dialog-parentkey").val(parseInt(oRisk.parentKey));
				$("#risk-dialog-parentkeytype").val(parseInt(oRisk.parentKeyType));

				$('#risk-dialog-level-value').val(oRisk.riskLevelValue).selectmenu("refresh").nextAll(".ui-selectmenu-button").removeClass("required-field").outerWidth(150);

				<sec:authorize access="!hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
					pgsi.pages.risk.form.removeLoweRiskOptions(oRisk.riskLevelValue, $("#risk-dialog-level-value"));
				</sec:authorize>

			},

			fnInitForm : function(iBusinessType) {

				if ( (iBusinessType === 3) || (iBusinessType === 5) ) {
					$("#pep-dialog-level-value").outerWidth(110);
					$('.pep').removeClass('hide');
					$('#pep-dialog-level-value').fnLoadDropDownList($.pgsi.enums.internationalizeEnumsByLabel("com.prosperitasglobal.sendsolv.model.PoliticallyExposedPersonEnum"));
					pgsi.util.page.form.fnInitSelectmenu($("#pep-dialog-level-value"));
					$("#pep-dialog-level-value").val(parseInt($('#personPepStatus').val(),10)).selectmenu('refresh').nextAll(".ui-selectmenu-button").removeClass("required-field");
				}

				pgsi.util.page.form.fnInitSelectmenu($("#risk-dialog-level-value"));

				$('#risk-dialog-level-value-button').outerWidth(150);

				// Reorder the enum values to 1=LOW; 2=MEDIUM; 0=HIGH; 3=UNKOWN
				var aRiskValues = $.pgsi.enums.internationalizeEnumsByLabel("com.prosperitasglobal.sendsolv.model.RiskLevelEnum");
				var oHighRisk = null;
				aRiskValues =  $.grep(aRiskValues, function(e){
					if (parseInt(e.key) !== 0) {
						return true;
					}

					else {
						oHighRisk = e;
						return false;
					}
				});
				aRiskValues.splice(2, 0, oHighRisk);

				$('#risk-dialog-level-value').fnLoadDropDownList(aRiskValues);

				$('#risk-dialog-level-value').on("selectmenuchange", function( event, ui ) {

					if (ui.item.value != 3) {
						$("#risk-dialog-note").addClass("required").prop("placeholder", "*");
					}

					else {
						$("#risk-dialog-note").removeClass("required").removeClass("error").prop("placeholder", "");
					}

				});

				$("#risk-dialog-level-value-button").focus();


			},

			fillObject : function(iVersionNumber) {

				var oRisk = new Risk();
				oRisk.parentKeyType  = parseInt($("#risk-dialog-parentkeytype").val());
				oRisk.parentKey 	 = parseInt($("#risk-dialog-parentkey").val());
				oRisk.riskLevelValue = parseInt($("#risk-dialog-level-value").val());
				oRisk.riskLevelNote  = $("#risk-dialog-note").val();
				oRisk.modelAction 	 = "UPDATE";

				if (!$.pgsi.isNullOrUndefined(iVersionNumber)) {
					oRisk.version = iVersionNumber;
				}

				return oRisk;
			},


			fnSavedRisk : function(oRequest,fnCallBackRisk){

				var bValidForm = pgsi.pages.risk.form.validator.form();

					$.pgsi.ajax.post({
						 sUrl       : "api/risk/edit",
						 oRequest   : oRequest,
						 fnCallback : fnCallBackRisk
					});

			}

		},

		view: {

			fillFields: function(oQATModel) {

				var sDate;
				var sUser;
				var sNoteText;
				var sNoteList;

				var oRisk = oQATModel.risk;

				if ($.pgsi.isNullOrUndefined(oRisk)) {
					return false;
				}

				if (!$.pgsi.isNullOrUndefined(oQATModel.modifyUser) && !$.pgsi.isNullOrUndefined(oQATModel.modifyDateUTC)) {
					sUser      = oQATModel.modifyUser;
					sDate 	   = $.pgsi.date.format(new Date(oQATModel.modifyDateUTC), "mm/dd/yy h:i A", true);
				}

				else {
					sUser      = oQATModel.createUser;
					sDate 	   = $.pgsi.date.format(new Date(oQATModel.createDateUTC), "mm/dd/yy h:i A", true);
				}

				$("section.risk").find("div.box.note, div.outer-box").remove();
				$("section.risk").find(".label-name").text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.RiskLevelEnum", oRisk.riskLevel) + " " + $.pgsi.locale.get("commons.pages.risk"));

				$("#risk-level-value").val(oRisk.riskLevelValue);

				if (!$.pgsi.isNullOrUndefined(oQATModel.pepStatus)){
					$(".pepStatus").text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.PoliticallyExposedPersonEnum",oQATModel.pepStatus));
					$("#personPepStatus").val(oQATModel.pepStatusValue);
				}

				if (!$.pgsi.isNullOrUndefined(oRisk.riskLevelNote) && oRisk.riskLevelNote.length > 0) {
					sNoteList = "<div class='outer-box spacer'><div class='box note'><span class='bold'>" + sUser + "</span><span class='date'>" + sDate +"</span><p class='riskNote full-text hide'>" + oRisk.riskLevelNote + "</p><p class='text_here'><span class='ellipsis_text'>" + oRisk.riskLevelNote + "</span></p></div></div>";

					$("section.risk").append(sNoteList);
					$("section.risk").find('.text_here').ThreeDots({max_rows:4});
				}
			},

			fillObject : function() {
				var oRisk = new Risk();
				oRisk.modelAction = "UPDATE";
				oRisk.parentKey = $("#business-id").val();
				oRisk.parentKeyType = $("#business-type").val();
				oRisk.riskLevelValue = $("#risk-level-value").val();
				oRisk.riskLevelNote = $("section.risk").find(".riskNote").text();

				return oRisk;
			},
		}

}
</script>

</sec:authorize>
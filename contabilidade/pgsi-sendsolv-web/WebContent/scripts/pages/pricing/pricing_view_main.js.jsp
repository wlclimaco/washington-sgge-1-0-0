<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">

/**
 * @namespace pgsi.pages.pricing
 * @description The main namespace for the Pricing View Page.
 * @author Flavio Tosta
 */

 pgsi.pages.pricing = {
 	view : {

 		applyProductPlanTemplate : function(oProductPlan) {

 			var oTemplate = $("#template").clone();
 			oTemplate.prop('id', "");
 			oTemplate.removeClass('hide');

			// Set Product Plan Title Name
			oTemplate.find('.pricing-header > .pricing-title').text(oProductPlan.name);

			var oThead = oTemplate.find('.pricing-content > .fees > table > thead');
			var oTbody = oTemplate.find('.pricing-content > .fees > table > tbody');

			var oTheadTh = oThead.find('tr:eq(0) > th.heading').clone();
			oThead.find('tr:eq(0) > th.heading').remove();

			var oTbodyTr1Td = oTbody.find('tr:eq(0) > td.cell').clone();
			oTbody.find('tr:eq(0) > td.cell').remove();

			var oTbodyTr = oTbody.find('tr:eq(2)').clone();
			oTbody.find('tr:eq(2)').remove();

			var oPayerContainer = oTemplate.find('.payers');

			var oPayerDiv = oPayerContainer.find('.payer').clone();
			oTemplate.find('.payers > .payer').remove();

			var oPlanCategory = null;
			var oProductPlanApplicability = null;
			var oPaymentType = null;
			var oNextRow = null;
			var oFeeTier = null;
			var bFoundRow = false;

			// Fill currency-payername-tranfermethod pairs
			for (var i = 0; i < oProductPlan.productPlanApplicabilityList.length; i++) {

				oProductPlanApplicability = oProductPlan.productPlanApplicabilityList[i];

				for (var j =0; j < oProductPlanApplicability.payer.paymentTypeList.length; j++) {

					oPaymentType = oProductPlanApplicability.payer.paymentTypeList[j];
					oPayerDiv.text(oPaymentType.currency.code + "-" + oProductPlanApplicability.payer.name + "-" +  $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.PaymentTypeEnum",oPaymentType.type));
					oPayerContainer.append(oPayerDiv.clone().wrap('<div>').parent().html());
				}
			}

			for (var i=0; i < oProductPlan.planCategoryList.length; i++) {

				oPlanCategory = oProductPlan.planCategoryList[i];

				// Fill thead with plan category name
				oTheadTh.text(oPlanCategory.name);

				oThead.find('tr:eq(0)').append(oTheadTh.clone().wrap('<div>').parent().html());

				// Fill allowance values
				oTbodyTr1Td.text(oPlanCategory.callCreditAllowance.toFixed(2));
				oTbody.find('tr:eq(0)').append(oTbodyTr1Td.clone().wrap('<div>').parent().html());

				for (var j=0; j < oPlanCategory.feeTierList.length; j++) {
					bFoundRow = false;
					oFeeTier = oPlanCategory.feeTierList[j];

					if (!$.pgsi.isNullOrUndefined(oFeeTier.maximumValue)) {
						oTbodyTr.find('td:eq(0)').text("USD " + oFeeTier.minimumValue + " to < " + oFeeTier.maximumValue);
					}

					else {
						oTbodyTr.find('td:eq(0)').text("USD " + oFeeTier.minimumValue + "+");
					}

					oTbodyTr.find('td:eq(1)').text(oFeeTier.feeValue.toFixed(2));

					var bRowHasMax, bObjHasMax, iRowMin, iRowMax;
					bObjHasMax = !$.pgsi.isNullOrUndefined(oFeeTier.maximumValue);

					// search for row within the current min max fee value'
					$.each(oTbody.find('tr.fee-row'), function(index, element) {
						bRowHasMax = !$.pgsi.isNullOrUndefined($(element).attr('data-max'));
						iRowMin = parseInt($(element).attr('data-min'));
						iRowMax = parseInt($(element).attr('data-max'));

						if ( (iRowMin === oFeeTier.minimumValue && iRowMax === oFeeTier.maximumValue)
							|| (iRowMin === oFeeTier.minimumValue && (!bRowHasMax && !bObjHasMax)) ) {

							$(element).append(oTbodyTr.find('td:eq(1)').clone().wrap('<div>').parent().html());
							bFoundRow = true;
							return false;
						}
					});

					// add new fee row
					if (!bFoundRow) {
						oTbodyTr.attr('data-min', oFeeTier.minimumValue);
						oTbodyTr.attr('data-max', oFeeTier.maximumValue);
						var oTbodyTrCopy = oTbodyTr.clone();

						for (var k=0; k < i; k++) {
							oTbodyTrCopy.find('td:eq(0)').after("<td class='cell'>-</td>");
						}
						oTbody.append(oTbodyTrCopy.wrap('<div>').parent().html());
					}
				}
			}

			return oTemplate.clone().wrap('<div>').parent().html();

		},

		fill : function(oLocation) {

			if ($.pgsi.isNullOrUndefined(oLocation) || oLocation.length === 0) {
				return;
			}

			var oProductPlan;
			var sProductPlans = "";

			for (var i =0; i < oLocation.businessProductPlanList.length; i++) {
				oProductPlan = oLocation.businessProductPlanList[i];
				sProductPlans += pgsi.pages.pricing.view.applyProductPlanTemplate(oProductPlan);
			}

			$("#pricings").append(sProductPlans);
			$("#location-name").text(oLocation.name);
			$("#location-name-title").text(oLocation.name);

			if (!$.pgsi.isNullOrUndefined($.address.parameter("tab"))) {
				$("#location-name-title").addClass('hide');
				$('#tabs nav.secondary').addClass('hide');
			}


		}
	}
}

</script>

</sec:authorize>
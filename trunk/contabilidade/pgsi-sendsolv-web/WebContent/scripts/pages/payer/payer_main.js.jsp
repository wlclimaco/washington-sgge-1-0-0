<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.member
 * @description The main namespace for the Member List Page.
 * @author Flavio Tosta, Washington Costa
 */

pgsi.pages.payer = {

	/**
	* Set of functions used by the datatables plugin to customize field values
	*/

	// Returns link for edit view
	fnCreateCodeLink : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		return '<a  id= "edit_links" title="View/Edit ' + full.code +'" href="payer/find/state?code=' + full.code + '&countryName='+full.code+'|'+full.name+'" class="edit_link alist">' + full.code +'</a>';
	},
	fnCreateCountryLink : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		return '<a  id= "edit_links" title="View/Edit ' + full.name +'" href="payer/find/state?code=' + full.code + '&countryName='+full.code+'|'+full.name+'" class="edit_link alist">' + full.name +'</a>';
	},

	fnPayerCity : function (val, type, full)
	{

		if (type !== "display")
		{
			return val;
		}
		var sPayerCities = "";
		for (var i = 0; i < full.payerCityList.length;i++){
			sPayerCities = sPayerCities + '<a  id= "edit_links" title="View/Edit ' + full.payerCityList[i].name +'" href="payer/find/branches?id=' + full.payerCityList[i].id + '" class="edit_link alist">' + full.payerCityList[i].name +'</a><br>';
		}

		return sPayerCities;
	},
	fnCreateRequest : function () {
		var oParam = {};

		var sLocatioName = null,
		sMemberFirstName  = null,
		iMemberId = null,
		sMemberLastName = null,
		sOrganizationName = null,
		iRecipientId = null,
		sPrimaryPhoneNumber = null,
		iPinNumber = null;

		sLocatioName = !pgsi.util.page.fnCheckXSS($.address.parameter("location")) ? $.address.parameter("location") : null;
		sMemberFirstName = !pgsi.util.page.fnCheckXSS($.address.parameter("first")) ? $.address.parameter("first") : null;
		iMemberId = !pgsi.util.page.fnCheckXSS($.address.parameter("memberId")) ? $.address.parameter("memberId") : null;
		sMemberLastName = !pgsi.util.page.fnCheckXSS($.address.parameter("last")) ? $.address.parameter("last") : null;
		sOrganizationName = !pgsi.util.page.fnCheckXSS($.address.parameter("organization")) ? $.address.parameter("organization") : null;
		iRecipientId =  !pgsi.util.page.fnCheckXSS($.address.parameter("recipientId")) ? $.address.parameter("recipientId") : null;
		sPrimaryPhoneNumber =  !pgsi.util.page.fnCheckXSS($.address.parameter("phone")) ? $.address.parameter("phone") : null;
		iPinNumber = !pgsi.util.page.fnCheckXSS($.address.parameter("pinNumber")) ? $.address.parameter("pinNumber") : null;

		if (!$.pgsi.isNullOrUndefined(iMemberId)) {
			iMemberId = iMemberId.replace('-', '');
		}

		oParam.criteria = {
			member : {
				firstName : sMemberFirstName,
				participantId : iMemberId,
				lastName : sMemberLastName,
				pinNumber : iPinNumber
			},
			locationName : sLocatioName,
			organizationName : sOrganizationName,
			primaryPhoneNumber : sPrimaryPhoneNumber,
			recipientId : iRecipientId
		};

		return oParam;
	},
	batches : {
		fnCreateRequest : function (oResponse) {
			var sPayerAddressList = "";
			for(i=0;i < oResponse.payerAddressList.length;i++){

				var sBatches = "";
				for(var iCount = 0;iCount < oResponse.payerAddressList[i].payerBranchList.length;iCount++){
					sBatches = sBatches + '<tr><td class="cell">'+oResponse.payerAddressList[i].payerBranchList[iCount].name+'</td></tr>'
				}

				sPayerAddressList = sPayerAddressList +'<div class="pricing">'+
					'<div class="pricing-header">'+
						'<div class="pricing-title">'+oResponse.payerAddressList[i].address+'</div>'+
					'</div>'+
					'<div class="pricing-content">'+
						'<div class="fees">'+
							'<table>'+
								'<thead>'+
									'<tr>'+
										'<th class="heading">Name</th>'+
								'</tr>'+
								'</thead>'+
								'<tbody>'+
									sBatches +
								'</tbody>'+
							'</table>'+
						'</div>'+
					'</div>'+
				'</div>'
			}

			$('#pricings').append(sPayerAddressList);
		}
	},

	payerTable: {

	}
}
</script>

</sec:authorize>
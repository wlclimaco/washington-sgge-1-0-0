<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.recipient
 * @description The main namespace for the Recipient List Page.
 * @author Washington Costa
 */

pgsi.pages.recipient = {

	fnFillMembers : function () {

	},

	fnFillTransferMethods : function(oval, type, full) {

		var oTransferSettingsList = full.transferSettingList;
		var sTransferMethods = "";
		var aCurrencys  = [];

		for (var i=0; i < oTransferSettingsList.length; i++) {
			if($.inArray(oTransferSettingsList[i].productPlanApplicability.paymentType, aCurrencys) === -1){
				sTransferMethods += $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.PaymentTypeEnum",oTransferSettingsList[i].productPlanApplicability.paymentType) + "<br>";
				aCurrencys.push(oTransferSettingsList[i].productPlanApplicability.paymentType);
			}
		}

		return sTransferMethods;

	},

	fnCreateRequest : function () {
		var oParam = {};

		var iMemberId = $.address.parameter("memberId");

		if (!$.pgsi.isNullOrUndefined(iMemberId)) {
			oParam.criteria = {memberId : iMemberId };

			return oParam;
		}
	},


	/**
	* Set of functions used by the datatables plugin to customize field values
	*/

	fnCreateRecipientNameLink : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		var recipientNameLink = '<a  id= "edit_links" title="View/Edit ' + full.firstName + ' ' + full.lastName + '" href="recipient/view?tab=info&recipientId=' + full.id + '" class="edit_link alist">' + full.firstName + ' ' + full.lastName;

		if (full.nameList.length === 0 || $.pgsi.isNullOrUndefined(full.nameList[0].otherName)) {
			 recipientNameLink += '</a>';
		}

		else {
			recipientNameLink += " (" + full.nameList[0].otherName + ")";
  		}

		return recipientNameLink;
	},

	fnCreateIdLink : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		//mask participant id MXX-00000
		var input  = document.createElement("input");
		input.type = "text";
		input.value  = full.participantId;
		var $input = $(input);
		$input.mask('***-99999');
		return '<a  id= "edit_links" title="View/Edit ' + full.participantId + '" href="recipient/view?recipientId=' + full.id + '" class="edit_link alist">' + $input.val()+ '</a>';
	},
	fnCreateLocationListLink : function (val, type, full)
	{
		//if (type !== "display")
		//{
		//	return val;
		//}
		//if (full.employmentInfoList !== null){
		//	return '<a  id= "edit_links" title="View/Edit ' + full.firstName + ' ' + full.lastName + '" href="recipient/view?recipientId=' + full.id + '" class="edit_link alist">' + full.firstName + ' ' + full.lastName + '</a>';
		//}else{
			return '';
	//	}
	},

	// Returns city name
	fnRecipientCity : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		return pgsi.pages.recipient.fnReturnElement(full.contactList).city;
	},

	// Returns country name
	fnRecipientCountry : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		return pgsi.pages.recipient.fnReturnElement(full.contactList).country;
	},

	// Returns state name
	fnRecipientState : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
			return pgsi.pages.recipient.fnReturnElement(full.contactList).state;
	},

	// Returns phone number
	fnRecipientPrimaryPhone : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
			return pgsi.pages.recipient.fnReturnElement(full.contactList).phone;
	},
	// Returns phone number
	fnRecipientStatus : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
			return $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.StatusEnum",full.personStatus);
	},
	fnRecipientSDNStatus : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		return "<a href='javascript:;' class='sdnstatus'>" + $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.sdn.model.SDNStatusEnum",full.sdnstatus) + "</a>";
	},
	fnRecipientRiskLevel : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
			return $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.RiskLevelEnum",full.risk.riskLevel);
	},

	// Returns aData filled with address, city, country and phone info
	fnReturnElement : function (listContact){

		var aData={address:"",city:"",state:"",country:"",phone:""};
		$("#action-dialog").append('<input class="numeric hide" type="text" value=""/>');
		for (var i=0; i < listContact.length; i++) {
			if(listContact[i].type == "address") {
				if (listContact[i].contactType == 'POSTAL_WORK') {
					aData.address = listContact[i].addressLine1;
					aData.city = listContact[i].cityName;
					if(listContact[i].stateProvinceRegion !== null)
					aData.state = listContact[i].stateProvinceRegion.description;
					aData.country = listContact[i].country.code;
				}
			} else if (listContact[i].type == "phone") {
				if(listContact[i].priority == 'PRIMARY')
				{
					$(".numeric").val(listContact[i].number);
					switch (parseInt(listContact[i].number.length,10)) {
						case 7:
							$(".numeric").mask("999-9999");
							break;
						case 8:
							$(".numeric").mask("999-99999");
							break;
						case 9:
							$(".numeric").mask("999-999999");
							break;
						case 10:
							$(".numeric").mask("9-999-999-999");
							break;

					}
					aData.phone = "+"+listContact[i].country.phoneCode +" "+ $(".numeric").val();
				}
			}

		}
		$("#action-dialog").empty();
		return aData;

	},

	recipientTable: {

	}
}
</script>

</sec:authorize>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.member
 * @description The main namespace for the Member List Page.
 * @author Flavio Tosta, Washington Costa
 */

pgsi.pages.member = {

	/**
	* Set of functions used by the datatables plugin to customize field values
	*/

	// Returns link for edit view
	fnCreateMemberNameLink : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		var memberNameLink = '<a  id= "edit_links" title="View/Edit ' + full.firstName + ' ' + full.lastName + '" href="member/view?tab=info&memberId=' + full.id + '" class="edit_link alist">' + full.firstName + ' ' + full.lastName;

		if (full.nameList.length === 0 || $.pgsi.isNullOrUndefined(full.nameList[0].otherName)) {
			 memberNameLink += '</a>';
		}

		else {
			memberNameLink += " (" + full.nameList[0].otherName + ")";
  		}

		return memberNameLink;
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

	fnCreateIdLink : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

			var input  = document.createElement("input");
			input.type = "text";
			input.value  = full.participantId;
			var $input = $(input);
			$input.mask('***-99999');
			return '<a  id= "edit_links" title="View/Edit ' + full.participantId + '" href="member/view?memberId=' + full.id + '" class="edit_link alist">' + $input.val() + '</a>';
	},

	fnCreateLocationListLink : function (val, type, full)
	{
		var sReturn="";
		if (type !== "display")
		{
			return val;
		}

		if (full.employmentInfoList !== null){
			for(i=0;i<full.employmentInfoList.length;i++){
				var oEmploymentInfoList = full.employmentInfoList[i];
				sReturn = sReturn + '<a  id= "edit_links" title="View/Edit ' + oEmploymentInfoList.locationName + '" href="location/view?locationId=' + oEmploymentInfoList.locationId + '" class="edit_link alist">' + oEmploymentInfoList.locationName + '</a> <a  id= "edit_links" title="View/Edit ' + oEmploymentInfoList.organizationName + '" href="organization/view?organizationId=' + oEmploymentInfoList.organizationId + '" class="edit_link alist"> ( ' + oEmploymentInfoList.organizationName + ' ) </a><br>';
			}

		}

		return sReturn;
	},

	// Returns city name
	fnMemberCity : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		return pgsi.pages.member.fnReturnElement(full.contactList).city;
	},

	// Returns country name
	fnMemberCountry : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		return pgsi.pages.member.fnReturnElement(full.contactList).country;
	},

	// Returns state name
	fnMemberState : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		return pgsi.pages.member.fnReturnElement(full.contactList).state;
	},

	// Returns phone number
	fnMemberPrimaryPhone : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		return pgsi.pages.member.fnReturnElement(full.contactList).phone;
	},

	// Returns phone number
	fnMemberStatus : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
			return $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.StatusEnum",full.personStatus);
	},

	fnMemberSDNStatus : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		return "<a href='javascript:;' class='sdnstatus'>" + $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.sdn.model.SDNStatusEnum",full.sdnstatus) + "</a>";
	},

	fnMemberRiskLevel : function (val, type, full)
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
					if (!$.pgsi.isNullOrUndefined(listContact[i].addressLine1)) {
						aData.address = listContact[i].addressLine1;
					}

					if (!$.pgsi.isNullOrUndefined(listContact[i].cityName)) {
						aData.city = listContact[i].cityName;
					}

					if(listContact[i].stateProvinceRegion !== null) {
						aData.state = listContact[i].stateProvinceRegion.description;
					}

					if (listContact[i].country !== null && listContact[i].country.code !== null ) {
						aData.country = listContact[i].country.code;
					}
				}
			}

			else if (listContact[i].type == "phone") {
				if(listContact[i].priority == 'PRIMARY')
				{
					$(".numeric").val(listContact[i].number);

					aData.phone = "+" + listContact[i].country.phoneCode + " " + $(".numeric").val();
				}
			}

		}
		$("#action-dialog").empty();
		return aData;

	},

	memberTable: {

	}
}
</script>

</sec:authorize>
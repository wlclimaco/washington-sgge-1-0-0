<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.organization
 * @description The main namespace for the Organization List Page.
 * @author Washington Costa
 */

pgsi.pages.organization = {

	/**
	* Set of functions used by the datatables plugin to customize field values
	*/

	// Returns link for edit view
	fnCreateOrganizationNameLink : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
			return '<a  id= "edit_links" title="View/Edit ' + full.name + '" href="organization/view?tab=info&organizationId=' + full.id + '" class="edit_link alist">' + full.name + '</a>';
	},

	// Returns city name
	fnOrganizationCity : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		return pgsi.pages.organization.fnReturnElement(full.contactList).city;
	},

	// Returns country name
	fnOrganizationCountry : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		return pgsi.pages.organization.fnReturnElement(full.contactList).country;
	},

	// Returns state name
	fnOrganizationState : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
			return pgsi.pages.organization.fnReturnElement(full.contactList).state;
	},

	// Returns phone number
	fnOrganizationPrimaryPhone : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
			return pgsi.pages.organization.fnReturnElement(full.contactList).phone;
	},
	// Returns phone number
	fnOrganizationStatus : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
			return $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.StatusEnum",full.status);
	},
	fnOrganizationSDNStatus : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
			return "<a href='javascript:;' class='sdnstatus'>" + $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.sdn.model.SDNStatusEnum",full.sdnstatus) + "</a>";
	},
	fnOrganizationRiskLevel : function (val, type, full)
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
					aData.state = listContact[i].stateProvinceRegion.description;
					aData.country = listContact[i].country.code;
				}
			} else if (listContact[i].type == "phone") {

				if(listContact[i].priority == 'PRIMARY')
				{
					$(".numeric").val(listContact[i].number);

					aData.phone = "+"+listContact[i].country.phoneCode +" "+ $(".numeric").val();
				}
			}

		}
		$("#action-dialog").empty();
		return aData;

	},

	organizationTable: {

	}
}
</script>

</sec:authorize>
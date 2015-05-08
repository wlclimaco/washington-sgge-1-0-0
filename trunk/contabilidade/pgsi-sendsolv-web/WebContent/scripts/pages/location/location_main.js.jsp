<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.location
 * @fileoverview The main namespace for the Location List Page.
 */
pgsi.pages.location = {

	/**
	* Set of functions used by the datatables plugin to customize field values
	*/

	// Returns link for edit view
	fnCreateLocationNameLink : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
			return '<a title="View/Edit ' + full.name + '" href="#/location/view?tab=info&locationId=' + full.id + '" class="edit_link">' + full.name + '</a>';
	},

	fnCreateParentLocationNameLink : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		if (!$.pgsi.isNullOrUndefined(full.parentOrganizationName)) {
			return '<a title="View"' + full.parentOrganizationName + ' href="#/organization/view?tab=info&organizationId=' + full.parentOrganizationId + '">' + full.parentOrganizationName + '</a>';
		}
		else {
			return "";
		}
	},

	// Returns city name
	fnLocationCity : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		return pgsi.pages.location.fnReturnElement(full.contactList).city;
	},

	// Returns state name
	fnLocationState : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		return pgsi.pages.location.fnReturnElement(full.contactList).state;
	},

	// Returns phone number
	fnLocationPrimaryPhone : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		return pgsi.pages.location.fnReturnElement(full.contactList).phone;
	},
	// Returns phone number
	fnLocationStatus : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
			return $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.StatusEnum",full.status);
	},
	fnLocationSDNStatus : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
			return $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.sdn.model.SDNStatusEnum",full.sdnstatus);
	},
	fnLocationRiskLevel : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
			return $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.RiskLevelEnum",full.risk.riskLevel);
	},


	// Returns aData filled with address, city, country and phone info
	fnReturnElement : function (listContact){

		var aData={address:"",city:"",state:"",phone:""};
		$("#action-dialog").append('<input class="numeric hide" type="text" value=""/>');
		for(var i=0;i < listContact.length;i++)
		{
			if(listContact[i].type == "address")
			{
				if(listContact[i].contactType == 'POSTAL_WORK')
				{
					aData.address = listContact[i].addressLine1;
					aData.city = listContact[i].cityName;
					aData.state = listContact[i].stateProvinceRegion.description;
				}
			}
			else if(listContact[i].type == "phone")
			{
				if(listContact[i].priority == 'PRIMARY')
				{
					$(".numeric").val(listContact[i].number);

					aData.phone = "+"+listContact[i].country.phoneCode+" "+ $(".numeric").val();
				}
			}

		}
		$("#action-dialog").empty();
		return aData;

	},

	locationTable: {

	}
}
</script>

</sec:authorize>
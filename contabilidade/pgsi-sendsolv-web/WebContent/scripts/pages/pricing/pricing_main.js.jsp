<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.pricing
 * @description The main namespace for the Pricing List Page.
 * @author Flavio Tosta
 */

pgsi.pages.pricing = {

	/**
	* Set of functions used by the datatables plugin to customize field values
	*/
	fnCreateLocationNameLink : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
			return '<a title="View/Edit ' + full.name + '" href="pricing/profile_view?locationId=' + full.id + '" class="edit_link alist">' + full.name + '</a>';
	},

	fnFormatModifyDate : function(val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		if ($.pgsi.isNullOrUndefined(full.modifyDateUTC)) {
			return $.pgsi.date.format(full.createDateUTC, "mm/dd/yy hh:i:s A");
		}

		else {
			return $.pgsi.date.format(full.modifyDateUTC, "mm/dd/yy hh:i:s A");
		}
	},

	fnRenderAuthor : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		if ($.pgsi.isNullOrUndefined(full.modifyUser)) {
			return full.createUser;
		}

		else {
			return full.modifyUser;
		}

	},

	fnCreateDescription : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		if (!$.pgsi.isNullOrUndefined(full.parentOrganizationName)) {
			return '<a title="View "' + full.parentOrganizationName + ' class="alist" href="organization/view?tab=info&organizationId=' + full.parentOrganizationId + '">' + full.parentOrganizationName + '</a>';
		}
		else {
			return "";
		}
	},

	pricingTable: {

	}
}
</script>

</sec:authorize>
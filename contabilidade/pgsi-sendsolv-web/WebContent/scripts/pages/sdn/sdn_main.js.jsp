<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.member
 * @description The main namespace for the Member List Page.
 * @author Flavio Tosta, Washington Costa
 */

pgsi.pages.sdn = {

	fnSDNName : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		return '<a  id= "edit_links" title="View/Edit ' + full.name + '" href="sdn/'+full.businessType.toLowerCase()+'/view?type='+full.businessType.toLowerCase()+'&id=' + full.parentKey + '" class="edit_link alist">' + full.name +'</a>'
	},

	fnBusinessType : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		return $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.cbof.model.BusinessTypeEnum",full.businessType);
	},

	fnSDNType : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		return "<a href='javascript:;' class='sdnstatus'>" + $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.sdn.model.SDNStatusEnum",full.sdnStatus) + "</a>";
	},

	fnSDNDate : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		return $.pgsi.date.format(new Date(full.date), "mm/dd/yy", null)
	},
	sdnTable: {

	}
}
</script>

</sec:authorize>
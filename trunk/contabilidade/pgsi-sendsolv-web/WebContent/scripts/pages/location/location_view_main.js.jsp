<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.location
 * @description The main namespace for the Location View Page.
 * @author Flavio Tosta
 */

pgsi.pages.location.view = {

	fnFillLocation : function(oResponse) {

		<sec:authorize access="!hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
			var $statusTemplate = $("#status-template");
			$statusTemplate.find("a.active").remove();
			$statusTemplate.find("a.deactivate").remove();
			$statusTemplate.find("span.divider").remove();
		</sec:authorize>

		var oLocation = oResponse.locationList[0];

		// fill location specific fields
		$('#parent-organization-name-field').text(oLocation.parentOrganizationName);

		if (!$.pgsi.isNullOrUndefined(iEnrolledMembers)) {
			$("#enrolled-members").text(iEnrolledMembers);
		}

		$('#parent-organization-link').attr("href", "organization/view?tab=info&organizationId=" + oLocation.parentOrganizationId);
		$('#view-parent-organization').attr("href", "organization/view?tab=info&organizationId=" + oLocation.parentOrganizationId);
		$('#parent-organization-link').text(oLocation.parentOrganizationName);
		$("#business-name").val(oLocation.name);

		// Sets the page title
		$.pgsi.pageLoader.title(oLocation.name, true);

		// fill business fields
		pgsi.util.page.business.view.fillFields(oLocation);
		// fill phone fields
		pgsi.pages.phone.view.fillFields(oLocation.contactList);
		// fill address fields
		pgsi.pages.address.view.fillFields(oLocation.contactList);

		pgsi.version.versionBusiness = oLocation.version;

		// fill notes
		pgsi.pages.note.view.fill(oLocation.noteList, oLocation);

		// fill risk
		pgsi.pages.risk.view.fillFields(oLocation);
	},

	displayLocationFields : function(){
		$("#business-view").find('.location').removeClass("hide");
		$("#business-view").find('.organization').addClass("hide");
	}

}
</script>

</sec:authorize>
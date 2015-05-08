<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.organization
 * @description The main namespace for the Organization View Page.
 * @author Flavio Tosta
 */

pgsi.pages.organization.view = {

	fnFillOrganization : function(oResponse) {

		<sec:authorize access="!hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
			var $statusTemplate = $("#status-template");
			$statusTemplate.find("a.active").remove();
			$statusTemplate.find("a.deactivate").remove();
			$statusTemplate.find("span.divider").remove();
		</sec:authorize>

		var oOrganization = oResponse.organizationList[0];

		// fill Organization specific fields
		if (!$.pgsi.isNullOrUndefined(oOrganization.dbaName)) {
			$("#dba-field").text(oOrganization.dbaName);
			$("#dba-field").show().prev().show();
		}

		else {
			$("#dba-field").hide().prev().hide();
		}

		$("#business-id").val(oOrganization.id)
		$("#business-name").val(oOrganization.name);

		// Sets the page title
		$.pgsi.pageLoader.title(oOrganization.name, true);

		$("#total-locations-field").text(oOrganization.numberOfLocations);
		$("#payroll-field").text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.PayrollTypeEnum",oOrganization.isPayrollCentralized));

		// Calculated number of locations enrolled for this organization
		if (!$.pgsi.isNullOrUndefined(iEnrolledLocations) && iEnrolledLocations > 0) {
			$("#enrolled-locations").text(iEnrolledLocations);
			$("#enrolled-locations").removeClass("hide").prev().removeClass("hide");
		}
		else {
			$("#enrolled-locations").addClass("hide").prev().addClass("hide");
		}

		// Calculated number of Member enrolled for this organization
		if (!$.pgsi.isNullOrUndefined(iEnrolledMembers)) {
			$("#enrolled-members").text(iEnrolledMembers);
			$("#enrolled-members").removeClass("hide").prev().removeClass("hide");
		}
		else {
			$("#enrolled-members").addClass("hide").prev().addClass("hide");
		}

		pgsi.version.versionBusiness = oOrganization.version;
		// fill business fields
		pgsi.util.page.business.view.fillFields(oOrganization);
		// fill phone fields
		pgsi.pages.phone.view.fillFields(oOrganization.contactList);
		// fill address fields
		pgsi.pages.address.view.fillFields(oOrganization.contactList);
		// fill risk
		pgsi.pages.risk.view.fillFields(oOrganization);
		// fetch and fill contacts
		pgsi.pages.contact.form.fnAjaxCallFetchAll(oPreLoadResponse.organizationList[0]);

		if (!$.pgsi.isNullOrUndefined(pgsi.pages.note)) {
			// fill notes
			pgsi.pages.note.view.fill(oOrganization.noteList, oOrganization);
		}

		if (!$.pgsi.isNullOrUndefined(pgsi.pages.document)) {
			// fill documents
			pgsi.pages.document.view.fill(oOrganization.documentList, oOrganization);
		}

	},

	displayOrganizationFields : function(){
		$("#business-view").find('.organization').removeClass("hide");
		$("#business-view").find('.location').addClass("hide");
	}

}
</script>

</sec:authorize>
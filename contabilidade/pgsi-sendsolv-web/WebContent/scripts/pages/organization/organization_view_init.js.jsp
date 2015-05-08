<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.organization
 * @description The init namespace for the Organization View Page.
 * @author Flavio Tosta
 */
var iEnrolledLocations = "";
//Receives preloaded data
<c:choose>
	<c:when test="${empty response}">
    	var oPreLoadResponse = null;
    </c:when>
    <c:otherwise>
    	var oPreLoadResponse = ${response};
    </c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${empty enrolled_organization}">
    	var iEnrolledMembers = null;
    </c:when>
    <c:otherwise>
    	var iEnrolledMembers = ${enrolled_organization};
    </c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${empty enrolled_locations}">
    	var iEnrolledLocations = null;
    </c:when>
    <c:otherwise>
    	var iEnrolledLocations = ${enrolled_locations};
    </c:otherwise>
</c:choose>

$(document).ready(function()
{
	// Attach event to add location link
	$("#tabs").find(".add_loc_link").on('click', function() {
		var iOrganizationId = parseInt($('#business-id').val(), 10);
		var sOrganizationName = $('#company-name-field').text();
		$.pgsi.pageLoader.load({
			url: "location/add?organizationId=" + iOrganizationId + "&organizationName=" + sOrganizationName,
			$content: $("#load"),
			bStartProgressBar : false
		});
	});

	//Attach Links to tabs
	$("#organizationInfo").attr('href', "organization/view/info?organizationId=" +	oPreLoadResponse.organizationList[0].id);
	$('#organizationBylocation').attr('href',"location/fetchOrganizationBylocation?locationId=" + oPreLoadResponse.organizationList[0].id);

	var sTab = $.address.parameter("tab");
	var iActiveTab;

	if (!$.pgsi.isNullOrUndefined(sTab) && sTab.length > 0) {
		iActiveTab = $('*[data-tab="' + sTab + '"]').parent().index();
	}

	else {
		var iActiveTab = 0;
	}

	$("#tabs").tabs({

		active : iActiveTab,

		beforeLoad : function(event, ui){
			$.pgsi.progressBar.start();

			$.address.parameter("tab", ui.tab[0].childNodes[1].dataset.tab);
			sTab = $.address.parameter("tab");
			iActiveTab = $('*[data-tab="' + sTab + '"]').parent().index();
		},

		load: function(event, ui) {
			// Info
			if (iActiveTab == 0) {
				pgsi.pages.organization.view.displayOrganizationFields();
				pgsi.pages.organization.view.fnFillOrganization(oPreLoadResponse);
				<sec:authorize access="!hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
					$("#add-contact").remove();
				</sec:authorize>
			}

			$.pgsi.progressBar.stopGlobal();
		}

	});

});
</script>

</sec:authorize>
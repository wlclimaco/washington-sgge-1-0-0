<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.location
 * @description The init namespace for the Location View Page.
 * @author Flavio Tosta
 */

<c:choose>
	<c:when test="${empty response}">
       var oPreLoadResponse = null;
    </c:when>
    <c:otherwise>
    	var oPreLoadResponse = ${response};
    </c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${empty enrolled_members}">
    	var iEnrolledMembers = null;
    </c:when>
    <c:otherwise>
    	var iEnrolledMembers = ${enrolled_members};
    </c:otherwise>
</c:choose>

pgsi.pages.location.view.displayLocationFields();

if (!$.pgsi.isNullOrUndefined(oPreLoadResponse)) {
	pgsi.pages.location.view.fnFillLocation(oPreLoadResponse);
}

//Attach event to add location link
$("#tabs").find(".add_loc_link").on('click', function(){
	var iOrganizationId = oPreLoadResponse.locationList[0].parentOrganizationId;
	var sOrganizationName = oPreLoadResponse.locationList[0].parentOrganizationName;
	$.pgsi.pageLoader.load({
		url: "location/add?organizationId=" + iOrganizationId + "&organizationName=" + sOrganizationName,
		$content: $("#load"),
		bStartProgressBar : false
	});
});

// Remove border bottom for Contacts
$("section.contact").parent().css( {"border":"none"} );

pgsi.pages.contact.form.fnAjaxCallFetchAll(oPreLoadResponse.locationList[0]);

</script>

</sec:authorize>
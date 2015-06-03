<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<script type="text/javascript">
<c:choose>
	<c:when test="${empty response}">
       var oPreLoadResponse = null;
    </c:when>
    <c:otherwise>
    	var oPreLoadResponse = ${response};
    </c:otherwise>
</c:choose>

if (!$.pgsi.isNullOrUndefined(oPreLoadResponse)) {
	pgsi.pages.transportador.view.fnFillTransportador(oPreLoadResponse);
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

$('#edit-business').click(function(e)
{
	var nBusinessType= parseInt($("#business-type").val());

	// Check for Organization or Location

	e.preventDefault();


	pgsi.util.actiondialog.launchActionDialog (
		"insert",
		pgsi.pages.transportador.dialogSettings.insert(
			$('#business-id').val(),
			$('#company-name-field').text(),
			nBusinessType)
	);

});


$("section.contact").parent().css( {"border":"none"} );

$.pgsi.progressBar.stopGlobal();
</script>

<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.organization
 * @fileoverview The init namespace for the Organization Create Page.
 */
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
		<c:when test="${empty number_of_employees}">
	    	var oEmployees = null;
	    </c:when>
	    <c:otherwise>
	    	var oEmployees = ${number_of_employees};
	    </c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${empty number_of_migrant_workers}">
	    	var oMigrantWorkers = null;
	    </c:when>
	    <c:otherwise>
	    	var oMigrantWorkers= ${number_of_migrant_workers};
	    </c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${empty payroll_centralized}">
	    	var oPayroll = null;
	    </c:when>
	    <c:otherwise>
	    	var oPayroll = ${payroll_centralized};
	    </c:otherwise>
	</c:choose>

	if (!$.pgsi.isNullOrUndefined(oPreLoadResponse)) {
		pgsi.pages.organization.oOrganization = oPreLoadResponse.organizationList[0];
	}

	// Set the page title
	var sOrganizationId = $.address.parameter("organizationId");

	if ($.pgsi.isNullOrUndefined(sOrganizationId)) {
		$.pgsi.pageLoader.title($.pgsi.locale.get("commons.pages.organizationaddnew"), true);
	}

	$("#number-members").fnLoadDropDownList(oEmployees);
	$("#number-migrant-members").fnLoadDropDownList(oMigrantWorkers);

	//Get the organizationId parameter from the url
	var iOrganizationId = pgsi.util.page.getParameterFromUrl("organizationId");

		$("#business-form").find(".group-inputs:nth-child(2)").removeClass("border-top");
		pgsi.pages.organization.form.fnInitForm("INSERT");

		$("#number-members").on("selectmenuchange", function(event, ui){

			$("#number-migrant-members").fnLoadDropDownList(oMigrantWorkers);

			$("#number-migrant-members").find("option").each(function(i){

				var iIndex = parseInt(ui.item.index);

				if (i >  iIndex) {
					$(this).remove();
				}
			});

			$("#number-migrant-members").selectmenu("refresh").nextAll(".ui-selectmenu-button").outerWidth(150);

		});

	$("#sic" ).change(function(e) {
		if (!$.pgsi.isNullOrUndefined($(this).val())){
			$('#naics').removeClass('required').prop("placeholder", "*");
		}else{
			$('#naics').removeClass('required').prop("placeholder", "");
		}

	});

	$("#naics" ).change(function(e) {
		if (!$.pgsi.isNullOrUndefined($(this).val())){
			$('#sic').removeClass('required').prop("placeholder", "*");
		}else{
			$('#sic').removeClass('required').prop("placeholder", "");
		}

	});

	// Attaches api request to add organization
	$("#add-button" ).click(function(e) {
		e.preventDefault();
		pgsi.pages.organization.form.ajaxCall("api/organization/add", $.pgsi.locale.get("action.addedorganization.success"), "INSERT");

	});

	$.pgsi.progressBar.stopGlobal();

</script>

</sec:authorize>
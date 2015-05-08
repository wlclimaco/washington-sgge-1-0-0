<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

	<script type="text/javascript">
/**
 * @namespace pgsi.pages.location
 * @fileoverview The init namespace for the Location Create Page.
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

$(document).ready(function()
{
	if (!$.pgsi.isNullOrUndefined(oPreLoadResponse)) {
		pgsi.pages.location.oLocation = oPreLoadResponse.locationList[0];
	}

	// Set the page title
	var sLocationId = $.address.parameter("locationId");

	if ($.pgsi.isNullOrUndefined(sLocationId)) {
		$.pgsi.pageLoader.title($.pgsi.locale.get("commons.pages.locationaddnew"), true);
	}

	// Fill the dropdownlists
	$("#number-members").fnLoadDropDownList(oEmployees);
	$("#number-migrant-members").fnLoadDropDownList(oMigrantWorkers);

	// Get the organizationId parameter from the url
	var iOrganizationId = pgsi.util.page.getParameterFromUrl("organizationId");
	var sOrganizationName = pgsi.util.page.getParameterFromUrl("organizationName");
	var iLocationId = pgsi.util.page.getParameterFromUrl("locationId");

		pgsi.pages.location.form.fnInitForm("INSERT");
		$("#business-form").find(".group-inputs:nth-child(1)").removeClass("border-top");

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


	if (!$.pgsi.isNullOrUndefined(iOrganizationId) && !$.pgsi.isNullOrUndefined(sOrganizationName) ) {
		// Set the organization id field for the organizationn previously displayed
		$("#organization-key").val(sOrganizationName).prop("readonly", true)
		$("#parentKey").val(iOrganizationId);
	}


	$("#organization-key").outerWidth(360);

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

	$("#add-button" ).click(function(e) {
		e.preventDefault();
		var bValidForm = pgsi.pages.location.form.validator.form();
		if (bValidForm)
		{
			pgsi.pages.location.form.ajaxCall("api/location/add", $.pgsi.locale.get("action.addedlocation.success"), "INSERT");
		}
	});
	$(function() {
		function log( message ) {
			$( "<div>" ).text( message ).prependTo( "#log" );
			$( "#log" ).scrollTop( 0 );
		}
		$( "#organization-key" ).autocomplete({
			source: function( request, response ) {
				$.pgsi.ajax.post({
						sUrl 		: "api/organization/fetchall",
						bHideProgressBar : true,
						oRequest 	: {inquiryCriteria:{name:request.term},sortExpressions:[{field:"name",direction:"Descending"}],preQueryCount:true,startPage:0,pageSize:10000},
						fnCallback  : function(oResponse) {
							response( $.map( oResponse.organizationList, function( item ) {
								var iLength = item.contactList.length,
								sAddress = item.contactList,
								sComplet = "";
								if(iLength > 0 ){
									for(i=0;i < iLength;i++){
										if(sAddress[i].type == "address") {
											sComplet = " ( " + sAddress[i].cityName + ", " + sAddress[i].stateProvinceRegion.code + ", " + sAddress[i].country.code + " ) ";
										}
									}
								}
								return {
									label : item.name,
									desc  : sComplet,
									value : item.name,
									id    : item.id
								}
							}));
						}
					});
			},
			minLength: 1,
			select: function( event, ui ) {
				$('#parentKey').val(ui.item.id)
				log( ui.item ?
				"Selected: " + ui.item.label :
				"Nothing selected, input was " + this.value);
			},
			open: function() {
				$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
			},
			close: function() {
				$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
			}
			})
			.autocomplete( "instance" )._renderItem = function( ul, item ) {
			      return $( "<li>" )
			        .append( "<a><span class='label'>" + item.label + "</span><br><span class='desc'>" + item.desc + "</span></a>" )
			        .appendTo( ul );
			};

	});
	$.pgsi.progressBar.stopGlobal();
});
</script>

</sec:authorize>
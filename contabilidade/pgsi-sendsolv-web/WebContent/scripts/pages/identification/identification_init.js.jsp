<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.organization
 * @fileoverview The init namespace for the Identification init Page.
 */
	//Receives preloaded data

	<c:choose>
		<c:when test="${empty known_countries}">
	    	var oIssuingCountries = null;
	    </c:when>
	    <c:otherwise>
	    	var oIssuingCountries = ${known_countries};
	    </c:otherwise>
	</c:choose>


	pgsi.util.page.form.fnInitForm();

	// Fill the dropdownlists
	$("#identification-country").fnLoadDropDownList(oIssuingCountries);

	$("#identification-country").on("selectmenuchange", function( event, ui ) {

		var fnCallbackStates = function(oResponse){

			$("#identification-region").fnLoadDropDownList(oResponse).nextAll(".ui-selectmenu-button").addClass("required").removeClass("hide").prev("select").selectmenu('refresh');
			$("#identification-region-button").outerWidth(220);

			var fnCallbackDocumentTypes = function(oResponse) {
				if (oResponse.operationSuccess === true &&
					 oResponse.documentTypeList.length > 0) {

					var oDocumentList = "<option></option>";
					var key;
					var value;

					$("#document-type").find("option").remove();
					for (var i=0; i < oResponse.documentTypeList.length; i++) {
						key = oResponse.documentTypeList[i].id;
						value = oResponse.documentTypeList[i].name;

						oDocumentList += "<option value=" + key + ">" + value + "</option>";
					}

					$("#document-type").append(oDocumentList).nextAll(".ui-selectmenu-button").addClass("required").removeClass("hide").prev("select").selectmenu('refresh');
					$("#document-type-button").outerWidth(220);
				}
				$.pgsi.progressBar.stop();
			};

			$.pgsi.ajax.post({
				sUrl : "api/document/type",
				fnCallback : fnCallbackDocumentTypes,
				bAsync      : false,
				oRequest : {
					businessTypeValue : 3,
					countryCode : $("#identification-country").val()
				}
			});

		};


		$.pgsi.ajax.get({
				sUrl : "fetchstates?code=" + $(this).val(),
				fnCallback : fnCallbackStates
		});

	});

</script>

</sec:authorize>
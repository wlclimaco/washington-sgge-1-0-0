<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.contact
 * @description The init namespace for the Contact Page.
 */
$(document).ready(function()
{
	<c:choose>
		<c:when test="${empty response}">
			var oPreLoadResponse = null;
		</c:when>
		<c:otherwise>
			var oPreLoadResponse = ${response};
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${empty countries}">
	    	var oCountries = null;
	    </c:when>
	    <c:otherwise>
	    	var oCountries = ${countries};
	    </c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${empty suffix}">
	    	var oSuffix = null;
	    </c:when>
	    <c:otherwise>
	    	var oSuffix = ${suffix};
	    </c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${empty prefix}">
	    	var oPrefix = null;
	    </c:when>
	    <c:otherwise>
	    	var oPrefix = ${prefix};
	    </c:otherwise>
	</c:choose>


	// Fill the dropdownlists
	$("#contact-prefix").fnLoadDropDownList(oPrefix);
	$("#contact-suffix").fnLoadDropDownList(oSuffix);

	//Set up hover effect for buttons
	$( ".btn" ).button();

	//Set up selects elements
	$( "select" ).selectmenu({
		"change" : function(event, ui) {
			pgsi.pages.contact.initRequiredSelects();
		}
	});

	if ($.pgsi.isNullOrUndefined(oPreLoadResponse)) {
		pgsi.pages.contact.form.fnInitForm("INSERT");
	}
	else {
		pgsi.pages.contact.form.fnInitForm();
	}

	//Get the organizationId parameter from the url
	var iContactId = $.address.parameter("contactId");

	//Verify if is the page of edit or add
	if (!iContactId)
	{
		//Show title of Add Contact
		$( "#title-create-contact" ).removeClass( "hide" );

		$( "#contact-type" ).parents( ".group-inputs" ).removeClass( "hide" );

		$( "#add-contact-button" ).click(function(e) {

			e.preventDefault();

			var bValidForm = pgsi.pages.contact.validator.form();
			if (bValidForm)
			{
				pgsi.pages.contact.ajaxCall("api/contact/add", $.pgsi.locale.get("action.addedcontact.success"));
			}
		});

	}
	else
	{
		//Show title of Edit Contact
		$( "#title-edit-contact" ).removeClass( "hide" );

		$( "#contact-social-security-number" ).parents( ".group-inputs" ).removeClass( "hide" );

		$( "#add-contact-button" ).click(function(e) {

			e.preventDefault();

			var bValidForm = pgsi.pages.contact.validator.form();
			if (bValidForm)
			{
				pgsi.pages.contact.ajaxCall("api/contact/edit", $.pgsi.locale.get("action.editedcontact.success"));
			}
		});
	}

	if (!$.pgsi.isNullOrUndefined(oPreLoadResponse)) {
		// set current Liaison object
		pgsi.pages.contact.oContact = oPreLoadResponse.liaisonList[0];
		pgsi.pages.contact.form.fillObject(oPreLoadResponse.liaisonList[0]);
	}

	$('#contact-date-of-birth').datepicker(
		{
			maxDate: "+0D",
			onClose : function(dateText, object) {
				if (isNaN((new Date(dateText)).getTime()) || (new Date(dateText) > new Date)) { $(this).val(""); }
			}
		}
	);

	$("#contact-risk").on("selectmenuchange", function( event, ui ) {
		// 5 ==  Other
		$input  = $("#riskNote");

		if (ui.item.value == "4") {
			$input.removeClass("required required-field");
			$input.removeClass("error");
			$input.prop("placeholder", "");
		}else {
			if (!$input.hasClass("required")) {
				$input.addClass("required required-field");
				$input.prop("placeholder", "*");
			}
		}
	});

	//
	$("#contact-type").on("selectmenuchange", function( event, ui ) {
		// 5 ==  Other
		$input  = $("#contact-title");

		if (ui.item.value != "5") {
			$input.removeClass("required required-field");
			$input.removeClass("error");
			$input.prop("placeholder", "");
		}else {
			if (!$input.hasClass("required")) {
				$input.addClass("required required-field");
				$input.prop("placeholder", "*");
			}
		}
	});
});

</script>

</sec:authorize>
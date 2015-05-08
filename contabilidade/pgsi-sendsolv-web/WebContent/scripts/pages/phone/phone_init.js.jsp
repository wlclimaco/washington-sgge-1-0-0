<%@ taglib prefix='sec'
uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">

<c:choose>
<c:when test="${empty phone_codes}">
var oPhoneCodes = null;
</c:when>
<c:otherwise>
var oPhoneCodes = ${phone_codes};
</c:otherwise>
</c:choose>

var oContactTypeEnums = $.pgsi.enums.internationalizeEnumsByLabel("com.prosperitasglobal.cbof.model.ContactTypeEnum");
var oPhoneEnums = pgsi.pages.phone.form.filterPhoneEnums(oContactTypeEnums);


	// Fill phone dropdown
	$("#phone-template").find(".phone-type").fnLoadDropDownList(oPhoneEnums);

	// Fill country code dropdown
	$("#phone-template").find(".phone-country").fnLoadDropDownList(oPhoneCodes);


	$("#phone-template").find("#primary").prop("checked", true).prop('disabled', true);

	pgsi.pages.phone.form.fnInitForm();

	// Multiple phones functionality
	$("#add-phone").on("click", function() {

		// Create new node
		var $copy = pgsi.pages.phone.form.createNewNode();
		var $container = $("#phone-template").find(".container");

		$container.append($copy);

		var iIndex = $container.find(".row-form").index($copy);

		$copy.find(".phone-type").attr("name", $copy.find(".phone-type").attr("name") + iIndex);
		$copy.find(".phone-country").attr("name", $copy.find(".phone-country").attr("name") + iIndex);
		$copy.find(".phone-number").attr("name", $copy.find(".phone-number").attr("name") + iIndex);

		pgsi.pages.phone.form.fnInitSelectmenu($copy.find("select.phone-country"));

		// Default phone code to USA
		if(iIndex == 1){
			$phoneTemplate.find($("select[name$='phoneCountry"+iIndex+"']" )).val($("select[name$='phoneCountry']" ).val()).prop("placeholder", "").selectmenu("refresh").outerWidth(115);
		}

		else {
			$phoneTemplate.find($("select[name$='phoneCountry"+iIndex+"']" )).val($("select[name$='phoneCountry"+(iIndex - 1)+"']" ).val()).prop("placeholder", "").selectmenu("refresh").outerWidth(115);
		}

		pgsi.pages.phone.form.fnInitForm($copy);


		$copy.find("span").first().focus();
			pgsi.pages.phone.form.setFieldSizes();
		});

</script>

</sec:authorize>
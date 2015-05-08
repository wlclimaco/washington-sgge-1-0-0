<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">

$(document).ready(function()
{

	// Fill the dropdownlists
	<c:choose>
		<c:when test="${empty known_countries}">
	    	var oKnownCountries = null;
	    </c:when>
	    <c:otherwise>
	    	var oKnownCountries = ${known_countries};
	    </c:otherwise>
	</c:choose>

	$("#country").fnLoadDropDownList(oKnownCountries);

	pgsi.pages.address.form.fnInitForm();

});

</script>

</sec:authorize>
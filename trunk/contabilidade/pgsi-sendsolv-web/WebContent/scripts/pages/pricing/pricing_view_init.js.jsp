<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">

/**
 * @namespace pgsi.pages.pricing
 * @description The init namespace for the Pricing View Page.
 * @author Flavio Tosta
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

	pgsi.pages.pricing.view.fill(oPreLoadResponse.locationList[0]);

	$.pgsi.progressBar.stopGlobal();
});

</script>

</sec:authorize>
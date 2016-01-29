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
		<c:when test="${empty regime}">
	    	var oRegime = null;
	    </c:when>
	    <c:otherwise>
	    	var oRegime = ${regime};
	    </c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${empty cnae}">
	    	var oCnae = null;
	    </c:when>
	    <c:otherwise>
	    	var oCnae = ${cnae};
	    </c:otherwise>
	</c:choose>

$(document).ready(function()
{
	$.pgsi.progressBar.stopGlobal();
});
</script>

</sec:authorize>
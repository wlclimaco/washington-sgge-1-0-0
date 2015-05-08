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
		<c:when test="${empty documentType}">
	    	var oPreLoadResponse = null;
	    </c:when>
	    <c:otherwise>
	    	var oPreLoadResponse = ${documentType};
	    </c:otherwise>
	</c:choose>
	pgsi.pages.document.form.fnInitForm();

</script>

</sec:authorize>
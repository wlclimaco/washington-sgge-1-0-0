<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace qat.pages.location
 * @fileoverview The main namespace for the Location List Page.
 */
qat.pages.empresa = {


	empresaTable: {

	}
}
</script>


</sec:authorize>
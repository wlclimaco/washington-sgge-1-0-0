<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
		$(document).ready(function() {

			sensus.util.page.stopGlobalProgressBar();
		});
	</script>
</sec:authorize>
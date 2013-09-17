<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
		$(document).ready(function() {

			sensus.util.page.startProgressBar();

			var _service 	= sensus.settings.serviceType;
			var _services	= sensus.constants.services;

			// When the service was water, show the link report tab
			if (_service != _services.water.name) {

				$("#report").parent().remove();
			}

			// tabs controller
			$.fn.pageLoader.tabs($(".tabs"), $('#tabs-content'));
		});
	</script>
</sec:authorize>
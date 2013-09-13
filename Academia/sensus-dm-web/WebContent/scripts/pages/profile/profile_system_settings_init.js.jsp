<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
	/**
	 * @namespace sensus.pages.settings
	 * @description The init namespace for the System Settings Page.
	 * @fileoverview Initializes the System Settings Page.
	 * @author QATEmployee
	 */
	$(document).ready(function() {

		// Fetch properties from user or system settings
		<c:choose>
			<c:when test="${empty properties}">
				var propertyResponse = null;
			</c:when>
			<c:otherwise>
				var propertyResponse = ${properties};
			</c:otherwise>
		</c:choose>

		sensus.util.page.initMessaging();

		// style buttons
		$(".button").button();

		// set type settings
		sensus.pages.settings.type = sensus.pages.settings.typeSettings.system;

		// load system settings
		sensus.pages.settings.loadSettings(propertyResponse);

		// set system settings on page
		sensus.pages.settings.setSettings();

		// time zone chosen
		$('#timezone').chosen();

		var oTabContent = $("#tabs-content");

		// click events
		// save button
		oTabContent.delegate("#button-save", "click", function(e) {

			e.preventDefault();

			sensus.pages.settings.saveSettings(sensus.locale.get("systemsettings.messageSuccess.logout"), sensus.locale.get("systemsettings.messageSuccess"));

		});

		// cancel button
		oTabContent.delegate("#button-cancel", "click", function(e) {

			sensus.util.page.startProgressBar();

			e.preventDefault();

			sensus.pages.settings.setSettings();

			sensus.util.page.stopProgressBar();
		});


		<sec:authorize access="hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER', 'ROLE_EPM_SYSTEM_OPERATOR')">
			$("#tabs-content").find("select").prop('disabled', 'disabled').trigger("liszt:updated");
		</sec:authorize>

		sensus.util.page.stopGlobalProgressBar();
		sensus.util.page.stopProgressBar();

	});
	</script>

</sec:authorize>
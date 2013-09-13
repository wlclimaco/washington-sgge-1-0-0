<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<script type="text/javascript">
/**
 * @namespace sensus.pages.profile
 * @description The init namespace for the Profile Page.
 * @fileoverview Initializes the profile page.
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
	sensus.pages.settings.type = sensus.pages.settings.typeSettings.user;

	// load user settings
	sensus.pages.settings.loadSettings(propertyResponse);

	// set user settings on page
	sensus.pages.settings.setSettings();

	// time zone chosen
	$('#timezone').chosen({no_results_text: sensus.locale.get('commons.pages.noResultsMatched')});

	var oTabsContent = $("#tabs-content");

	// click events
	// button save
	oTabsContent.delegate("#button-save", "click", function(e) {

		e.preventDefault();

		sensus.pages.settings.saveSettings(sensus.locale.get("action.addssettings.success.logout", sensus.settings.appRoot + '/j_spring_security_logout'), sensus.locale.get("action.addssettings.success"));

		sensus.pages.dm.getSettings();
	});

	// cancel button
	oTabsContent.delegate("#button-cancel", "click", function(e) {

		sensus.util.page.startProgressBar();

		e.preventDefault();

		sensus.pages.settings.setSettings();

		sensus.util.page.stopProgressBar();
	});

	sensus.util.page.stopProgressBar();

	sensus.util.page.stopGlobalProgressBar();
});
</script>
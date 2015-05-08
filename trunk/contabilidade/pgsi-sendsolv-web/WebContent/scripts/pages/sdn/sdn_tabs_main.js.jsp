<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.payments
 * @description The init namespace for the Batch Tabs.
 * @author Flavio Tosta, Washington Costa
 */

pgsi.pages.sdn.tabs = {

	// Get Active tab index
	getActiveTabIndex : function() {
		var sTab = $.address.parameter("tab");
		var iActiveTab;

		if (!$.pgsi.isNullOrUndefined(sTab) && sTab.length > 0) {
			iActiveTab = $('*[data-tab="' + sTab + '"]').parent().index();
		}

		else {
			var iActiveTab = 0;
		}

		return iActiveTab;
	},

	// Edits and display the action button, depending on batch status
	 displayActionButton : function(sBatchFilter) {
	 	var $actionButton = $("#link-fund");

	},

	// Store selected batch status in sessionStorage and set the URL
	storeStatus : function($currentTab) {
		var $anchor = $currentTab.find('a');
		var sStatus = !pgsi.util.page.fnCheckXSS($anchor.attr("data-tab")) ? $anchor.attr("data-tab") : "";
	}
};

</script>

</sec:authorize>
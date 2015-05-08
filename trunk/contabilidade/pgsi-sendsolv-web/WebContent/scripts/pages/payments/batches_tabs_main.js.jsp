<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.payments
 * @description The init namespace for the Batch Tabs.
 * @author Flavio Tosta, Washington Costa
 */

pgsi.pages.payments.tabs = {

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

		switch (sBatchFilter) {
			// Cancel Button
			case "ALL" :
				// Set button text
				$actionButton.find("span").text($.pgsi.locale.get('pages.batches.buttons.cancel'));
				// Set data attributes with status, title and message to display in the dialog
				$actionButton.attr("data-status", "CANCELLED");
				$actionButton.attr("data-title", $.pgsi.locale.get('pages.batches.dialog.cancel.title'));
				$actionButton.attr("data-message", $.pgsi.locale.get('pages.batches.dialog.cancel.message'));
				$actionButton.removeClass("hide");
			break;

			// Approve Spreads button
			case "SPREAD_REVIEW" :
				// Set button text
				$actionButton.find("span").text($.pgsi.locale.get('pages.batches.buttons.approvespreads'));
				// Set data attributes with status, title and message to display in the dialog
				$actionButton.attr("data-status", "READY_FOR_RELEASE");
				$actionButton.attr("data-title", $.pgsi.locale.get('pages.batches.dialog.approveSpreads.title'));
				$actionButton.attr("data-message", $.pgsi.locale.get('pages.batches.dialog.approveSpreads.message'));
				$actionButton.removeClass("hide");
			break;

			// Release Batches Button
			case "READY_FOR_RELEASE" :
				// Set button text
				$actionButton.find("span").text($.pgsi.locale.get('pages.batches.buttons.releasebatches'));
				// Set data attributes with status, title and message to display in the dialog
				$actionButton.attr("data-status", "ACH_PROCESSING");
				$actionButton.attr("data-title", $.pgsi.locale.get('pages.batches.dialog.releaseBatch.title'));
				$actionButton.attr("data-message", $.pgsi.locale.get('pages.batches.dialog.releaseBatch.message'));
				$actionButton.removeClass("hide");
			break;

			default:
				$actionButton.addClass("hide");
			break;
		}
	},

	// Store selected batch status in sessionStorage and set the URL
	storeStatus : function($currentTab) {
		var $anchor = $currentTab.find('a');
		var sStatus = !pgsi.util.page.fnCheckXSS($anchor.attr("data-tab")) ? $anchor.attr("data-tab") : "";

		// Store status in session storage
		if (sStatus === "ALL") {
			sessionStorage.batchesTab = "BATCH_CREATION|PENDING_APPROVAL|SPREAD_REVIEW|READY_FOR_RELEASE|RELEASED|ACH_PROCESSING|ACH_EXCEPTIONS|ON_HOLD";
			$.address.parameter("tab", "ALL");
		}

		else {
			sessionStorage.batchesTab = sStatus;
			$.address.parameter("tab", sessionStorage.batchesTab);
		}
	}
};

</script>

</sec:authorize>
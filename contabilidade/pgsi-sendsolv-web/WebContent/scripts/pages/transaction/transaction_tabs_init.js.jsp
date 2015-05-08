<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.transaction
 * @description The init namespace for the Transaction Tabs.
 * @author Flavio Tosta, Washington Costa
 */

var sTab = $.address.parameter("tab");
var iActiveTab;

if (!$.pgsi.isNullOrUndefined(sTab) && sTab.length > 0) {
	iActiveTab = $('*[data-tab="' + sTab + '"]').parent().index();
}

else {
	var iActiveTab = 0;
	$.address.parameter("tab","all");
}

var displayActionButton = function(sBatchFilter) {
	var $actionButton = $("#link-fund");

	switch(sBatchFilter) {
		// Cancel Button
		case "" :
			// Set button text
			$actionButton.find("span").text($.pgsi.locale.get('pages.batches.buttons.cancel'));
			// Set data attributes with status, title and message to display in the dialog
			$actionButton.attr("data-status", "CANCELLED");
			$actionButton.attr("data-title", $.pgsi.locale.get('pages.batches.dialog.cancel.title'));
			$actionButton.attr("data-message", $.pgsi.locale.get('pages.batches.dialog.cancel.message'));
			$actionButton.removeClass("hide");
		break;

		default:
			$actionButton.addClass("hide");
		break;
	}
};

// Store selected batch status in sessionStorage and set the URL
var storeStatus = function($currentTab) {
	$anchor = $currentTab.find('a');
	// Store status
	sessionStorage.batchesTab = !pgsi.util.page.fnCheckXSS($anchor.attr("data-tab")) ? $anchor.attr("data-tab") : "";
	$.address.parameter("tab", sessionStorage.batchesTab);
};

$("#tabs").tabs({
	active : iActiveTab,

	// Event triggered after plugin is first rendered
	create: function( event, ui ) {
		storeStatus($(ui.tab[0]));
		// Reload Table
		pgsi.util.page.fnReloadTable(pgsi.pages.batches.transactionTables);
		// Display Action Button
		displayActionButton(sessionStorage.batchesTab);
	},

	// Event triggered after a tab is selected
	activate: function( event, ui ) {
		storeStatus($(ui.newTab[0]));
		//Reload Table
		pgsi.util.page.fnReloadTable(pgsi.pages.batches.transactionTable);
		// Display Action Button
		displayActionButton(sessionStorage.batchesTab);

		if(($.address.parameter("tab") == "exceptions")||($.address.parameter("tab") == "cancellations")){
			$('.filter').hide()
		}else{
			$('.filter').show()
		}
	}
});

</script>

</sec:authorize>
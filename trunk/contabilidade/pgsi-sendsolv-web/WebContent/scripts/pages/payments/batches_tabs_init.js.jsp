<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.payments
 * @description The init namespace for the Batch Tabs.
 * @author Flavio Tosta, Washington Costa
 */

var iActiveTab = pgsi.pages.payments.tabs.getActiveTabIndex();

$("#tabs").tabs({
	active : iActiveTab,

	// Event triggered after plugin is first rendered
	create: function( event, ui ) {
		pgsi.pages.payments.tabs.storeStatus($(ui.tab[0]));
		// Reload Table
		//pgsi.util.page.fnReloadTable(pgsi.pages.batches.batchTable);
		// Display Action Button
		pgsi.pages.payments.tabs.displayActionButton(!pgsi.util.page.fnCheckXSS($.address.parameter("tab")) ? $.address.parameter("tab") : null );
	},

	// Event triggered after a tab is selected
	activate: function( event, ui ) {
		pgsi.pages.payments.tabs.storeStatus($(ui.newTab[0]));
		//Reload Table
		pgsi.util.page.fnReloadTable(pgsi.pages.batches.batchTable);
		// Display Action Button
		pgsi.pages.payments.tabs.displayActionButton(!pgsi.util.page.fnCheckXSS($.address.parameter("tab")) ? $.address.parameter("tab") : null );
	}
});

</script>

</sec:authorize>
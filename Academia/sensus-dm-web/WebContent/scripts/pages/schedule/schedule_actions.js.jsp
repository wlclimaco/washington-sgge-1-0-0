<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and 
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
	sensus.pages.systemintelligence.dialogSettings = {

		scheduledEvent : {
			 id : null,
			 eventType: "",
			 eventStatus: "",
			 eventName : "",
			 actionName: "",
			 eventDate : "",
			 repeats:null
		 },

		summary : {

			// The dialog title.
			title : sensus.locale.get("action.processingsummary.title"),

			width : 1678,

			minheight: 700,

			// Whether this dialog requires a smartpoint list.
			requiresSmartpoints : false,

			/**
			 * The function that will be called when the action dialog is launched.
			 *
			 * @param actionDialog
			 *            a reference to the actionDialog objext
			 */
			open : function() {
				sensus.util.page.stopProgressBar();
			},

			action : function(actionDialog) {

				var $actionDialog = $("#action-dialog");

				$actionDialog.summary(sensus.pages.systemintelligence.dialogSettings["scheduledEvent"].id,sensus.pages.systemintelligence.dialogSettings["scheduledEvent"].actionName);

				$actionDialog.dialog('open');
			}
		}
	};
	</script>

</sec:authorize>
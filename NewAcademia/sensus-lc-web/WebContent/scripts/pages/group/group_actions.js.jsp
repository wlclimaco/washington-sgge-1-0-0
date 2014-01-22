<script type="text/javascript">
/**
 * @namespace sensus.pages.group
 * @description The action namespace for the Group Page.
 */

/**
 * @fileoverview Defines the various action dialog options and common behaviors for the group-related actions.
 *
 * @author Anke Doerfel-Parker
 */


/**
 * Return the default error function for the Ajax callback performed by the
 * action dialog. This function is called when the HTTP call failed.
 *
 * @param errorMessage
 *            the message to display if the operation did not work
 * @param bRefresh
 *            whether to refresh the main group datatable
 * @return the function to provide to as 'error' parameter to $.ajax()
 */
sensus.pages.group.getDefaultAjaxErrorFunction = function(errorMessage, bRefresh) {

	if (bRefresh === true) {

		sensus.widgets.datatable.reloadTable(sensus.pages.group.groupTable);

	}

	return function() {

		$.sc.showMessage("messaging-main", errorMessage, "error");
		$.sc.stopProgressBar(0,true);

	};
};

/**
 * Render the default "Selected Group" table. Uses the selectedRows
 * property and the column configuration from the main Group table to
 * retrieve the data to render. Table is rendered and assigned to the
 * sensus.pages.smartpoint.actionDialogTable property.
 *
 * @see sensus.util.datatable.getColumnSetup
 * @see sensus.pages.group.tableColumns
 * @see sensus.widgets.datatable.selectedRows
 */
sensus.pages.group.renderDeleteMessage = function() {

	var nGroups = sensus.widgets.datatable.isAllRows ? sensus.widgets.datatable.deselectedRows : sensus.widgets.datatable.selectedRows;

	if (nGroups.length <= 1 && !sensus.widgets.datatable.isAllRows) {

		return $.sc.locale("groupdelete.warning.nosmartpointsongroup", sensus.widgets.datatable.sSelText);

	} else if (nGroups.length > 1 || sensus.widgets.datatable.isAllRows) {

		return $.sc.locale("groupdelete.warning.genericmessage");

	}
};


/**
 * The main namespace for the group action dialog configurations. Note that the
 * buttons are initialized in a function because their names must be
 * internationalized and cannot be used in the simple JSON notation because
 * property names must be literals.
 *
 * The name of each configuration matches the value behind each option from the
 * action droplist and the action button.
 */
sensus.pages.group.dialogSettings = {

	/**
	 * Delete Group
	 */
	deleteGroup : {

		// The dialog title.
		title : $.sc.locale("action.deletegroup.title"),

		// Whether this dialog requires a smartpoint list.
		requiresSmartpoints : false,

		// The dialog width.
		width : 300,

		// The dialog buttons (submit and cancel).
		buttons : [{
				id : "delete-group-submit",
				text : $.sc.locale("action.deletegroup.submit"),
				click : function() {

					var	nIds   = sensus.widgets.datatable.isAllRows ? sensus.widgets.datatable.deselectedRows : sensus.widgets.datatable.selectedRows;
					var sIdUrl = '';

					var fnCallBack = function(data){
						if (data.operationSuccess){
							sensus.widgets.datatable.reloadTable(sensus.pages.group.groupTable);
						}
					};

					sIdUrl = sensus.widgets.datatable.isAllRows && nIds.length <= 0 ? "" : nIds;

					$(this).dialog('close');
					$.sc.startProgressBar(null,true);

					for (var i = 0; i < nIds.length; i++){
						nIds[i] = parseInt(nIds[i]);
					}

					$.sc.getJson('api/group/delete', {"selectionPaginationIds": nIds,"isAllRows":sensus.widgets.datatable.isAllRows}, false, fnCallBack, null, null, null, null);

					sensus.widgets.datatable.clearSelects();

				}
			}, {
				id: "delete-group-cancel",
				text :$.sc.locale("action.deletegroup.cancel"),
				click : function() {
					$.sc.closeActionDialog("#action-dialog");
				}
		}],

		/**
		 * The function that will be called when the action dialog is launched.
		 *
		 * @param actionDialog
		 *            a reference to the actionDialog objext
		 */
		action : function(actionDialog) {
			sensus.util.page.clearFormElements('delete_group_form');
			$('#action-dialog').empty().append(sensus.pages.group.renderDeleteMessage());
			$("#action-dialog").dialog('open');
		}

	}
};
</script>
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
 * Return the default success function for the Ajax callback performed by the
 * action dialog. This function is called when the call was successful (no HTTP
 * errors). It does not mean the intended operation performed as intended.
 * 
 * @param successMessage
 *            the message to display in case of successful operation
 * @param errorMessage
 *            the message to display if the operation did not work
 * @param bRefresh
 *            whether to refresh the main group datatable
 * @return the function to provide to as 'success' parameter to $.ajax()
 */
sensus.pages.group.getDefaultAjaxSuccessFunction = function(successMessage, errorMessage, bRefresh) {
	return function(data) {
		
		if (bRefresh === true) {
			
			sensus.widgets.datatable.reloadTable(sensus.pages.group.groupTable);
			
		}
		
		sensus.util.page.stopProgressBar();
		
		if (data.result === sensus.constants.ajax.ok) {
			
			sensus.util.page.showMessage("messaging-main", successMessage + sensus.util.page.getMessageList(data.messages), "confirm");
			
		} else {
			
			sensus.util.page.showMessage("messaging-main", errorMessage + sensus.util.page.getMessageList(data.messages), "error");
			
		}
	};
};

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
		
		sensus.util.page.showMessage("messaging-main", errorMessage, "error");
		sensus.util.page.stopProgressBar(0,true);	
		
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

		$('#selected-groups').append(sensus.locale.get("groupdelete.warning.nosmartpointsongroup", sensus.widgets.datatable.sSelText));
		
	} else if (nGroups.length > 1 || sensus.widgets.datatable.isAllRows) {
		
		$('#selected-groups').append(sensus.locale.get("groupdelete.warning.genericmessage"));
		
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
		title : sensus.locale.get("action.deletegroup.title"),
		
		// Whether this dialog requires a smartpoint list.
		requiresSmartpoints : false,
		
		// The dialog width.
		width : 300,
		
		// The dialog buttons (submit and cancel).
		buttons : [{
				id : "delete-group-submit", 
				text : sensus.locale.get("action.deletegroup.submit"),
				click : function() {
				
					var	nIds   = sensus.widgets.datatable.isAllRows ? sensus.widgets.datatable.deselectedRows : sensus.widgets.datatable.selectedRows;
					var sIdUrl = '';
					
					sIdUrl = sensus.widgets.datatable.isAllRows && nIds.length <= 0 ? "" : nIds;
					
					$.address.parameter("iDisplayStart", 0);
					
					$(this).dialog('close');
					
					sensus.util.page.startProgressBar(null,true);
					
					//Send ajax action
					sensus.util.ajaxaction.actionUrlAdress = sensus.settings.deleteGroupUrl;
					sensus.util.ajaxaction.data = { 'groupRequest': new groupRequest(null, null, null, null,'',sensus.widgets.datatable.isAllRows, sIdUrl) };
					sensus.util.ajaxaction.sendActionDefault(sensus.locale.get("action.deletegroup.success"), sensus.locale.get("action.deletegroup.error"));
					
					sensus.widgets.datatable.clearSelects();
				}
			}, {
				id: "delete-group-cancel", 
				text :sensus.locale.get("action.deletegroup.cancel"), 
				click : function() {
					$("#action-dialog").dialog('close');
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
			$('#action-dialog').empty().load(sensus.settings.initiateDeleteGroupInclude, function() {
				$("#action-dialog").removeClass("waiting");
				sensus.pages.group.renderDeleteMessage();
			});
			$("#action-dialog").dialog('open');
		}
	
	}
};
</script>
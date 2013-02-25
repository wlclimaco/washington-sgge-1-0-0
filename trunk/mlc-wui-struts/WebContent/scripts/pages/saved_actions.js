/**
 * Render the default "Selected Saved" table. Uses the selectedRows
 * property and the column configuration from the main Saved table to
 * retrieve the data to render. Table is rendered and assigned to the
 * sensus.pages.smartpoint.actionDialogTable property.
 * 
 * @see sensus.util.datatable.getColumnSetup
 * @see sensus.pages.saved.tableColumns
 * @see sensus.widgets.datatable.selectedRows
 */
sensus.pages.saved.renderDeleteMessage = function() {
	
	$('#delete-search-message').append(sensus.locale.get("searchdelete.warning.genericmessage", sensus.pages.saved.searchName));
	sensus.pages.saved.searchName = "";
};


/**
 * The main namespace for the saved action dialog configurations. Note that the
 * buttons are initialized in a function because their names must be
 * internationalized and cannot be used in the simple JSON notation because
 * property names must be literals.
 * 
 * The name of each configuration matches the value behind each option from the
 * action droplist and the action button.
 * 
 * @author Cristiane Cobo
 */
sensus.pages.saved.dialogSettings = {
		
	deleteSearch : {
		
		/**
		 * The dialog title.
		 */
		title : sensus.locale.get("action.deletesearch.title"),
		
		/**
		 * Whether this dialog requires a smartpoint list.
		 */
		requiresSmartpoints : false,
		
		/**
		 * The dialog width.
		 */
		width : 300,
		/**
		 * The dialog buttons (submit and cancel).
		 */
		buttons : (function() {
			var buttons = {};
			buttons[sensus.locale.get("action.deletesearch.submit")] = function() {
				
				var nSearchId = sensus.pages.saved.searchId;
				$(this).dialog('close');
				sensus.util.page.startProgressBar(null,true);
				
				//Send ajax action
				sensus.util.ajaxaction.actionUrlAdress = sensus.settings.deleteSearchUrl;
				sensus.util.ajaxaction.data = "searchId=" + nSearchId;
				sensus.util.ajaxaction.sendActionDefault(sensus.locale.get("action.deletesearch.success"), sensus.locale.get("action.deletesearch.error"));
				
				sensus.pages.saved.savedId = 0;
			};
			buttons[sensus.locale.get("action.deletesearch.cancel")] = function() {
				sensus.pages.saved.savedId = 0;
				$("#action-dialog").dialog('close');
			};
			return buttons;
		})(),
		/**
		 * The function that will be called when the action dialog is launched.
		 * 
		 * @param actionDialog
		 *            a reference to the actionDialog objext
		 */
		action : function(actionDialog) {
			$('#action-dialog').empty().load(sensus.settings.deleteSearchInclude, function() {
				$("#action-dialog").removeClass("waiting");
				sensus.pages.saved.renderDeleteMessage();
			});
			$("#action-dialog").dialog('open');
		}
	}
};
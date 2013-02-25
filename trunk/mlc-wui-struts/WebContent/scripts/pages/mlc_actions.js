sensus.pages.dialogSettings = {
		
		session : {
			
			/**
			 * The dialog title.
			 */
			title : sensus.locale.get("Session Expired"),
			
			/**
			 * The dialog buttons (submit and cancel).
			 */
			buttons : (function() {
				var buttons = {};
				buttons[sensus.locale.get("Yes, Keep Working")] = function() {
					$(this).dialog('close');
				};
				buttons[sensus.locale.get("No, Logoff")] = function() {
					$.idleTimeout.options.onTimeout.call(this);
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
				$('#action-dialog').empty().load("include.sessionExpired.action", function() {
					actionDialog.removeClass('waiting');
				});
				actionDialog.addClass('waiting');
				actionDialog.dialog('open');
			}	
	}
		
}
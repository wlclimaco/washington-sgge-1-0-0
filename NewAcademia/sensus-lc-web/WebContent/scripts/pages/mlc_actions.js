sensus.pages.dialogSettings = {

	session : 
	{

		/**
		 * The dialog title.
		 */
		title : $.sc.locale("Session Expired"),

		/**
		 * The dialog buttons (submit and cancel).
		 */
		buttons : (function()
		{
			var buttons = {};
			buttons[$.sc.locale("Yes, Keep Working")] = function() 
			{
				$(this).dialog('close');
			};
			buttons[$.sc.locale("No, Logoff")] = function() 
			{
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
		action : function(actionDialog) 
		{
			$('#action-dialog').empty().load("timeOut", function() 
			{
				actionDialog.removeClass('waiting');
			});
			actionDialog.addClass('waiting');
			actionDialog.dialog('open');
		}
	}
}
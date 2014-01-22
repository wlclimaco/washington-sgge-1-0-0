<script type="text/javascript">
	sensus.pages.ecomode.dialogSettings =
	{

		fileFormatDialog :
		{

			/**
			 * The dialog title.
			 */
			title :  $.sc.locale("systemsettings.ecomode.page.dialogTitle"),

			/**
			 * The dialog buttons (submit and cancel).
			 */
			buttons : (function ()
			{
				return {};
			}),

			/**
			 * The function that will be called when the action dialog is launched.
			 *
			 * @param actionDialog
			 *            a reference to the actionDialog objext
			 */
			action : function(actionDialog)
			{
				$('#action-dialog').empty().load("settingsImportEcoMode", function()
				{
					$("#action-dialog").removeClass("waiting");
				});
				$("#action-dialog").dialog('open');
			}
		}
	};
</script>
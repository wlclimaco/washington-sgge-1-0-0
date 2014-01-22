<script type="text/javascript">
/**
 * @namespace subscribe
 * @description The action namespace for the subscribe dialog.
 * @fileoverview Defines the various action dialog options and common behaviors for the group-related actions.
 * @author Rodolfo alves
 */

subscribe = {


		 dialogSettings : {

			 subscribeHelp : {


					title                : $.sc.locale("email.util.alertEmail"),
					width                : 570,
					requiresSmartpoints  : false,
					action               : function(actionDialog) {

						var sId = subscribe.dialogSettings.subscribeHelp.actionId;
						$('#action-dialog').empty()
										   .html('<div id="action_dialog_email"><h2>Email : '+$.sc.locale("sensus.mlc.alert_subtype."+sId+'')+'</h2>'
										   +'<div class="action-dialog ui-dialog-content ui-widget-content" ">'
										   +'<fieldset><ul><li class="align-right highlight"><div class="sui-pad1">'
										   +$.sc.locale("email.util.dialogMessage")+'</div></li></ul></fieldset></div>')
										   .dialog('open');

					},
					/**
					 * Definitions for button actions on the help dialog.
					 */
					buttons : [{
							/**
							 * Checks the checkbox.
							 */
							id : "apply-subscrib",
							text : $.sc.locale("email.util.ok"),
							click : function() {
								var sId = subscribe.dialogSettings.subscribeHelp.actionId;

								var oInput = $("#"+sId);
								oInput.prop("checked", !oInput.prop("checked"));
								sensus.pages.profile.fnCallSubscribe(oInput);

								$(this).dialog('close');

							}

						},

							{

							id: "apply-subscrib-message",
							text :$.sc.locale("email.util.thanks"),
							click : function() {


								$.sc.savePropertyProfile( { "SHOW_SUBSCRIPTION_DIALOG" : "false" } );

								var sId = subscribe.dialogSettings.subscribeHelp.actionId;

								var oInput = $("#"+sId);
								oInput.prop("checked", !oInput.prop("checked"));
								sensus.pages.profile.fnCallSubscribe(oInput);
								$.sc.stopProgressBar();
								$(this).dialog('close');

							}

					},

							/**
							 *
							 */
							{

							id: "apply-subscrib-cancel",
							text :$.sc.locale("email.util.cancel"),
							click : function() {

								$(this).dialog('close');

							}

					}]

		 }
	}
};
</script>
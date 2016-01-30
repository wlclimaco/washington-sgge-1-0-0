/**
 * @fileoverview Defines common functionality to loading action dialogs using the jQuery Dialog plugin.
 * @author Anke Doerfel-Parker
 * @description The main namespace for common action dialog related functionality.
 */
qat.util.actiondialog = {

	/**
	 * Launches the action dialog with the action id and the corresponding
	 * dialog settings. This function expects the dialog to be launched from the
	 * "#action_dialog" element. For an example of dialog settings, see
	 * pages/smartpoint_actions.js.
	 *
	 * @param actionId
	 *            the string code for the action. This is only needed to display
	 *            a warning note for unimplemented actions.
	 * @param the
	 *            settings for the particular action dialog. These settings are
	 *            typically defined in the main namespace for the containing
	 *            page.
	 */
	launchActionDialog : function(actionId, dialogSettings) {


		/** alert user of unumplemeted **/
		if (!dialogSettings) {
			alert(sensus.locale.get("widget.action.unimplemented", actionId));
			return;
		}
		/** set width if it was unset **/
		if(dialogSettings.width == null) {
			dialogSettings.width = 500;
		}
		/** clear form **/
		//sensus.util.page.clearFormElements(dialogSettings.form);

		var actionDialogId = "#action-dialog";

		if(actionId == "errorDialog") {
			actionDialogId = "#action-dialog-Error";
		}

		/** set dialog **/
		var actionDialog = $(actionDialogId).dialog( {
			autoOpen : false,
			title    : dialogSettings.title,
			height   : dialogSettings.height || "auto",
			width    : function() {
				var width;
				if (dialogSettings.width != undefined){
					width = dialogSettings.width;
				}else {
					width = 500;
				}
				return width;
			},
			minheight : function() {
				var minheight;
				if (dialogSettings.minheight != undefined){
					minheight = dialogSettings.minheight;
				}else {
					minheight = 150;
				}
				return minheight;
			},
			modal : true,
			buttons : dialogSettings.buttons,
			dialogClass : dialogSettings.dialogClass,
			resizable : function() {
				var resizable;
				if (dialogSettings.resizable != undefined){
					resizable = dialogSettings.resizable;
				} else {
					resizable = false;
				}
				return resizable;
			},
			open : function () {
				if (jQuery.isFunction(dialogSettings.open)) {
					dialogSettings.open();
				}
				qat.util.page.supressBackspace();

				//Attach event handler to listen for a "Enter" press if "#dialog-submit" is present
				if ($('#dialog-submit').length > 0) {
					$(document).keypress(function(e) {
						if (e.which === 13) {
							$('#dialog-submit').trigger('click');
						}
					});
				}
			},

			close : dialogSettings.close,
			beforeClose : function () {
				// Dettach the event that listens for "keypress"
				$(document).unbind('keypress');
				actionDialog.empty();
				qat.util.page.allowBackspace();
			},
			focus : dialogSettings.focus
		});
		dialogSettings.action(actionDialog);
	},

	/**
	 * Initializes the action dialog (hides the element if visible).s
	 */
	initActionDialog : function() {
		$('.action-dialog').hide();
	}
};
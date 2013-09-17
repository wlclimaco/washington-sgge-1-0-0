/**
 * @fileoverview Defines common functionality to loading action dialogs using the jQuery Dialog plugin.
 * @author Anke Doerfel-Parker
 * @description The main namespace for common action dialog related functionality.
 */
sensus.util.actiondialog = {

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
		sensus.util.page.clearFormElements(dialogSettings.form);

		var actionDialogId = "#action-dialog";

		if(actionId == "tableDialog") {
			actionDialogId = "#action-dialog-lrp";
		} else if(actionId == "repeatSchedule") {
			actionDialogId = "#actionDialogRepeatDefine";
		} else if (actionId == "longRunningProcessDialog") {
			actionDialogId = "#dialog-monitor-dissmiss";
		}

		/** set dialog **/
		var actionDialog = $(actionDialogId).dialog( {
			autoOpen : false,
			title : dialogSettings.title,
			height : dialogSettings.height || "auto",
			width : function() {
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
			dialogClass : 'action-dialog',
			resizable : function() {
				var resizable;
				if (dialogSettings.resizable != undefined){
					resizable = dialogSettings.resizable;
				}else {
					resizable = false;
				}
				return resizable;
			},
			open : dialogSettings.open,
			close : dialogSettings.close,
			beforeClose : dialogSettings.beforeClose,
			focus : dialogSettings.focus
		});
		dialogSettings.action(actionDialog);
	},

	/**
	 * Initializes the action dialog (hides the element if visible).s
	 */
	initActionDialog : function() {
		$('.action-dialog').hide();
	}/*,

	defaultDialogSettings : {

		width : 500,
		modal : true,
		dialogClass : 'action-dialog'
	},

	dialog : function ($dialogDiv, oDialogSettings) {

		if ($dialogDiv) {

			if (oDialogSettings) {

				$.extend(oDialogSettings, this.defaultDialogSettings);

				// If need to load the dialog
				if (oDialogSettings.loadDialogURL) {

					$dialogDiv.load(oDialogSettings.loadDialogURL, function() {

						$dialogDiv.dialog(oDialogSettings);
					});

					return $dialogDiv;
				}

			} else {

				oDialogSettings = this.defaultDialogSettings;
			}

			$dialogDiv.dialog(oDialogSettings);

			return $dialogDiv;
		}

		return null;
	}*/
};
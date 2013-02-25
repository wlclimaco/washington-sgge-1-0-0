/**
 * @fileoverview Defines common functionality to loading action dialogs using the jQuery Dialog plugin.
 * @author Anke Doerfel-Parker
 */
/**
 * The main namespace for common action dialog related functionality.
 */
sensus.util.actiondialog = {
	/**
	 * Launches the action dialog with the action id and the corresponding
	 * dialog settings. This function expects the dialog to be launched from the
	 * "#action_dialog" element. For an example of dialog settings, see
	 * pages/smartpoint_actions.js.
	 *
	 * @param sActionId
	 *            the string code for the action. This is only needed to display
	 *            a warning note for unimplemented actions.
	 * @param the
	 *            settings for the particular action dialog. These settings are
	 *            typically defined in the main namespace for the containing
	 *            page.
	 */
	launchActionDialog : function(sActionId, oDialogSettings, sDialogId) {

		if (!oDialogSettings) {

			alert(sensus.locale.get("widget.action.unimplemented", sActionId));

			return;
		}
		
		oDialogSettings.actionId = sActionId;

		if(oDialogSettings.width  ==  null) {

			oDialogSettings.width = 500;

		}

		sensus.util.page.clearFormElements(oDialogSettings.form);

		var actionDialogId = "#action-dialog";

		if(sActionId == "tableDialog") {

			actionDialogId = "#action-dialog-lrp";

		}

		if(sActionId == "longRunningProcessRemove") {

			actionDialogId = "#remove-lrp";

		} else if(sActionId == "longRunningProcessAbort") {

			actionDialogId = "#abort-lrp";

		}

		if(!$.ajaxValidator.fnIsNullOrUndefined(sDialogId)){

			actionDialogId = "#"+sDialogId;

		}

		var actionDialog = $(actionDialogId).dialog( {
			autoOpen     : false,
			title        : oDialogSettings.title,
			close        : oDialogSettings.close,
			beforeClose  : oDialogSettings.beforeClose,
			width        : function() {

				var width;

				if (oDialogSettings.width != undefined) {

					width = oDialogSettings.width;

				} else {

					width = 500;

				}

				return width;
			},
			minheight : function() {

				var minheight;

				if (oDialogSettings.minheight != undefined) {

					minheight = oDialogSettings.minheight;

				} else {

					minheight = 150;

				}

				return minheight;
			},
			modal        : true,
			buttons      : oDialogSettings.buttons,
			dialogClass  : 'action-dialog',
			resizable    : false
		});

		oDialogSettings.action(actionDialog);
	},

	/**
	 * Initializes the action dialog (hides the element if visible).s
	 */
	initActionDialog : function() {
		$('.action-dialog').hide();
	}
};
/**
 * @fileoverview Defines common functionality to loading action dialogs using the jQuery Dialog plugin.
 * @author Anke Doerfel-Parker
 */
/**
 * The main namespace for common action dialog related functionality.
 */
sensus.util.actiondialog = {
	/**
	 * Launches the action dialog with the action id and the corresponding
	 * dialog settings. This function expects the dialog to be launched from the
	 * "#action_dialog" element. For an example of dialog settings, see
	 * pages/smartpoint_actions.js.
	 *
	 * @param sActionId
	 *            the string code for the action. This is only needed to display
	 *            a warning note for unimplemented actions.
	 * @param the
	 *            settings for the particular action dialog. These settings are
	 *            typically defined in the main namespace for the containing
	 *            page.
	 */
	launchActionDialog : function(sActionId, oDialogSettings, sDialogId) {

		if (!oDialogSettings) {

			alert(sensus.locale.get("widget.action.unimplemented", sActionId));

			return;
		}
		
		oDialogSettings.actionId = sActionId;

		if(oDialogSettings.width  ==  null) {

			oDialogSettings.width = 500;

		}

		sensus.util.page.clearFormElements(oDialogSettings.form);

		var actionDialogId = "#action-dialog";

		if(sActionId == "tableDialog") {

			actionDialogId = "#action-dialog-lrp";

		}

		if(sActionId == "longRunningProcessRemove") {

			actionDialogId = "#remove-lrp";

		} else if(sActionId == "longRunningProcessAbort") {

			actionDialogId = "#abort-lrp";

		}

		if(!$.ajaxValidator.fnIsNullOrUndefined(sDialogId)){

			actionDialogId = "#"+sDialogId;

		}

		var actionDialog = $(actionDialogId).dialog( {
			autoOpen     : false,
			title        : oDialogSettings.title,
			close        : oDialogSettings.close,
			beforeClose  : oDialogSettings.beforeClose,
			width        : function() {

				var width;

				if (oDialogSettings.width != undefined) {

					width = oDialogSettings.width;

				} else {

					width = 500;

				}

				return width;
			},
			minheight : function() {

				var minheight;

				if (oDialogSettings.minheight != undefined) {

					minheight = oDialogSettings.minheight;

				} else {

					minheight = 150;

				}

				return minheight;
			},
			modal        : true,
			buttons      : oDialogSettings.buttons,
			dialogClass  : 'action-dialog',
			resizable    : false
		});

		oDialogSettings.action(actionDialog);
	},

	/**
	 * Initializes the action dialog (hides the element if visible).s
	 */
	initActionDialog : function() {
		$('.action-dialog').hide();
	}
};

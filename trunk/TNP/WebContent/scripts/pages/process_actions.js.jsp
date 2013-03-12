<script type="text/javascript">
/**
 * @namespace sensus.pages.process
 * @description The action namespace for the Process Page.
 */

/**
 * @fileoverview Defines the various action dialog options and common behaviors
 *               for the process-related actions.
 *
 * @author Raphael Constantino
 */
sensus.pages.process.dialogSettings = {

	/** The dialog process abort */
	abort : {

		/**
		 * Whether this dialog requires a smartpoint list.
		 */

		requiresSmartpoints : false,

		/**
		 * The dialog width.
		 */

		width: 300,

		/**
		 * The dialog title.
		 */

		title: $.sc.locale("action.abortprocess.title"),

		/**
		 * The dialog buttons (submit and cancel).
		 */
		buttons : (function() {
			var buttons = {};
			buttons[$.sc.locale("action.abortprocess.submit")] = function() {
				$("#action-dialog").dialog('close');

				//Send ajax action
				sensus.util.ajaxaction.actionUrlAdress = sensus.settings.abortProcessUrl;
				sensus.util.ajaxaction.data = "id=" + sensus.pages.process.processId;
				sensus.util.ajaxaction.sendActionDefault($.sc.locale("action.summary.abort.process.success"), $.sc.locale("action.summary.abort.error"));

				sensus.pages.process.processTableCallUpdate();

				sensus.pages.process.processId = 0;
			};
			buttons[$.sc.locale("action.summary.cancel")] = function() {
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
			sensus.util.page.stopProgressBar();
			actionDialog.dialog('open');

			$('#action-dialog').empty().load(sensus.settings.abortInclude, function() {

				$("#action-dialog").removeClass("waiting");

				return false;
			});
		}
	},


	/** The Dialog for process Summary*/
	summaryInclude : {

		/**
		 * The dialog title.
		 */
		title : $.sc.locale("action.summary.title"),

		/**
		 * Whether this dialog requires a smartpoint list.
		 */
		requiresSmartpoints : false,

		/**
		 * The dialog width.
		 */
		width : 960,

		/**
		 * The function that will be called when the action dialog is launched.
		 *
		 * @param actionDialog
		 *            a reference to the actionDialog objext
		 */
		action : function(actionDialog) {

			$('#action-dialog').empty().load(sensus.settings.summaryInclude, {id: sensus.pages.process.processId, isEqualUserLogged: sensus.pages.process.processUserEquals} , function() {
				$("#action-dialog").removeClass("waiting");
				$(".button").button();
			});
			$("#action-dialog").dialog('open');
		}

	}

};
</script>
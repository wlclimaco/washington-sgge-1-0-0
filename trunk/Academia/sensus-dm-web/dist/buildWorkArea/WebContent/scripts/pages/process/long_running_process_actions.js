/**
 * @namespace sensus.pages.longrunningprocess.dialogSettingsProcess
 * @description The Defines Long Running Processes Dialogs Settings
 * @fileoverview Defines Long Running Processes Dialogs
 * @author Vinicius Silva
 */
sensus.pages.longrunningprocess.dialogSettingsProcess = {

	longRunningProcessDialog : {

		width		: 540,
		title		: sensus.locale.get("action.longRunningProcessMonitor"),
		isMonitor	: true,

		/**
		 * The function that will be called when the action dialog is launched.
		 *
		 * @param actionDialog
		 *            a reference to the actionDialog objext
		 */
		action : function() {

			var oActionDialog = $("#dialog-monitor-dissmiss");

			oActionDialog.dialog("open");
			oActionDialog.dialog("option", "position", "center");

			oActionDialog.empty().load("process/monitorDialog", function() {

				oActionDialog.removeClass("waiting");

				$(".descriptive-button-row a").button();

				$("a.dismiss, a.monitor", oActionDialog).click($.ajaxValidator.fnMonitorDialogEvent);

			});

			sensus.util.page.stopProgressBar(null,false);
		}
	},

	recentRequestsDialog : {

		title: sensus.locale.get("commons.pages.recentRequests"),

		width: 1024,

		minheight: 600,

		height: 600,

		resizable: true,

		beforeClose : function() {

			sensus.widgets.datatable.clearSelectsFunction.call(sensus.pages.longrunningprocess.lrpTable);
		},

		action : function(actionDialog) {

			$.ajax({
				url: 'process/recentsRequest',
				type: 'GET',
				async: false,
				success: function (html, status) {

					actionDialog.html(html);
				}
			});

			actionDialog.dialog("open");
		}
	}
};
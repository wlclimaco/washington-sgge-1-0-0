<script type="text/javascript">
/**
 * @namespace sensus.pages.schedule
 * @description The action namespace for the Schedule Page.
 */

/**
 * @fileoverview Defines the various action dialog options and common behaviors
 *               for the schedule-related actions.
 *
 * @author Raphael Constantino
 */

sensus.pages.schedule.getDefaultAjaxSuccessFunction = function(successMessage, errorMessage, bRefresh) {
	return function(data) {

		$.sc.locale.stopProgressBar();

		if (data.result == sensus.constants.ajax.ok) {

			$.sc.locale.showMessage("messaging-main", $.sc.locale.getMessageList(data.messages), "confirm");

		} else {

			$.sc.locale.showMessage("messaging-main", $.sc.locale.getMessageList(data.messages), "error");

		}

	};
};
//
///**
// * Return the default error function for the Ajax callback performed by the
// * action dialog. This function is called when the HTTP call failed.
// *
// * @param errorMessage
// *            the message to display if the operation did not work
// * @param bRefresh
// *            whether to refresh the main group datatable
// * @return the function to provide to as 'error' parameter to $.ajax()
// */
sensus.pages.schedule.getDefaultAjaxErrorFunction = function(errorMessage, bRefresh) {

	return function() {

		$.sc.locale.showMessage("messaging-main", errorMessage, "error");
		$.sc.locale.stopProgressBar();

	};
};

/**
 * Render the default "Selected Schedule" table. Uses the selectedRows
 * property and the column configuration from the main Schedule table to
 * retrieve the data to render. Table is rendered and assigned to the
 * sensus.pages.smartpoint.actionDialogTable property.
 *
 * @see sensus.util.datatable.getColumnSetup
 * @see sensus.pages.schedule.tableColumns
 * @see sensus.widgets.datatable.selectedRows
 */
sensus.pages.schedule.renderDeleteMessage = function(iLights, sScheduleName) {



		if (sScheduleName.length && iLights > 0) {

			$('#selected-schedule').append($.sc.locale("scheduledelete.warning.smartpointsonschedule", sScheduleName));

		} else if (sScheduleName.length && iLights == 0) {

			$('#selected-schedule').append($.sc.locale("scheduledelete.warning.nosmartpointsonschedule", sScheduleName));

		} else {

			$('#selected-schedule').append($.sc.locale("scheduledelete.warning.genericmessage", sScheduleName));

		}
};

sensus.pages.schedule.dialogSettings = {


		/**
		 * Configuration for the Delete Schedule Dialog
		 */

		deleteSchedule : {

			/**
			 * The dialog title.
			 */
			title : $.sc.locale("action.deleteschedule.title"),

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
			buttons : [{
					id     : "delete-schedule-submit",
					text   : $.sc.locale("action.deleteschedule.submit"),
					click  : function() {

						var nIds = sensus.widgets.datatable.isAllRows ? sensus.widgets.datatable.deselectedRows : sensus.widgets.datatable.selectedRows;
						var idUrl = sensus.widgets.datatable.isAllRows && nIds.length <= 0 ? "" : nIds;

						for (var i = 0; i < nIds.length; i++){
							nIds[i] = parseInt(nIds[i]);
						}

						sensus.pages.longrunningprocess.isClearSelect = true;

						$(this).dialog('close');

						//var oRequest = new scheduleRequest(null,null,null,null,null,null,null,sensus.pages.schedule.scheduleType, sensus.widgets.datatable.isAllRows, nIds, null, null, null);
						var oRequest = {
							"scheduleTypeEnum": "EVENT",
							"selectionPaginationIds": nIds,
							"paginationAllSelected": false,
							"paginationAllSelected":  sensus.widgets.datatable.isAllRows
						};

						var fnCallback = function () {
							sensus.pages.schedule.countSmartpoints = 0;
							sensus.widgets.datatable.clearSelects.call(sensus.pages.schedule.scheduleTable);
							sensus.widgets.datatable.reloadTable(sensus.pages.schedule.scheduleTable);

						}

						$.sc.getJson("api/schedule/delete", oRequest, false, fnCallback, $.sc.locale("action.deleteschedule.success"));

 				}
				}, {
					id     : "delete-schedule-cancel",
					text   :$.sc.locale("action.deleteschedule.cancel"),
					click  : function() {
						sensus.pages.schedule.scheduleId = 0;
						$("#action-dialog").dialog('close');
					}
			}],

			/**
			 * The function that will be called when the action dialog is launched.
			 *
			 * @param actionDialog
			 *            a reference to the actionDialog objext
			 */
			action : function(actionDialog) {
				actionDialog.empty().load("schedule/deleteScheduleInclude", function() {
					actionDialog.removeClass("waiting");

					var iLights 	  = sensus.pages.schedule.countSmartpoints,
						sScheduleName = sensus.pages.schedule.scheduleName;

					if ($('.checkbox input').is(':checked')) {

						if ($('.checkbox input:checked').length > 1) {

							sScheduleName = '';

						} else {

							sScheduleName = $('.checkbox input:checked').parent().parent().find('td:eq(2)').text();
							iLights 	  = parseInt($('.checkbox input:checked').parent().parent().find('td:eq(5)').text());

						}
					}

					sensus.pages.schedule.renderDeleteMessage(iLights, sScheduleName);
					sensus.pages.schedule.isSingleSchedule = false;
				});
				actionDialog.dialog('open');
			}

		}

};
</script>
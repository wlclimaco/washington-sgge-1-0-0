<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager')">
<script type="text/javascript">

sensus.commons.modules.dialogSettings = {

	/**
	 * Configuration for the Edit Light Status dialog.
	 */
	 editLightStatusDetail : {
		/**
		 * The dialog title.
		 */
		title : $.sc.locale("smartpoint.actions.editLight"),

		width: 450,

		/**
		 * Whether this dialog requires a smartpoint list.
		 */
		requiresSmartpoints : false,

		fnReloadLightStatusDetail : function() {

			// force reload occurs on fnUpdateLightAlerts
			sensus.commons.modules.content.lightAlerts.fnUpdateLightAlerts();
			sensus.commons.modules.summaryData.lightState.init();
			sensus.commons.modules.summaryData.lightStatus.init();
			sensus.commons.modules.content.lightLocation.fnUpdateLocation();

		},

		/**
		 * The dialog buttons (submit and cancel).
		 */
		buttons : [{
				id : "edit-light-status-submit",
				text : $.sc.locale("smartpoint.actions.editLightStatus"),
				click : function() {

					if (!$('.radio-list input').is(':checked'))
					{
						$('.radio-list input:first').validationEngine('showPrompt', $.sc.locale("validation.multipleFields"), 'red', '', true);
					}
					else
					{

						var	sTypeStatus = $('.radio-list input:checked').val();

						var oLight = {
							id : parseInt($.address.parameter('id')),
							lifeCycleState : sTypeStatus
						};

						var oRequest = new lightMaintenanceRequest(oLight);

						if (sTypeStatus == "DEACTIVATED")
						{
							// Submit Ajax Action
							$.sc.monitor("api/lighttop/updatestatus", oRequest, false, sensus.commons.modules.reloadData, $.sc.locale("smartpoint.actions.editLightStatus.success"));
							$(this).dialog('close');

						}
						else
						{
							// Submit Ajax Action
							$.sc.monitor("api/lighttop/updatestatus", oRequest, false, sensus.commons.modules.dialogSettings.editLightStatusDetail.fnReloadLightStatusDetail, $.sc.locale("action.longRunningProcessDialog.confirm"), false);
							$(this).dialog('close');
						}

						if (sensus.settings.monitor != 3)
						{
							$(this).dialog('close');
						}
					}
				}
			}, {
				id: "save-search-cancel",
				text : $.sc.locale("action.savesearch.cancel"),
				click : function() {
					$(this).dialog('close');
				}
		}],

		/**
		 * The function that will be called when the action dialog is closed.
		 *
		 * @param actionDialog
		 *            a reference to the actionDialog objext
		 */
		close : function() {
			$('.formError').remove();
		},

		/**
		 * The function that will be called when the action dialog is launched.
		 *
		 * @param actionDialog
		 *            a reference to the actionDialog objext
		 */
		action : function(actionDialog) {

			actionDialog.empty().load("light/editDialog", function() {
				actionDialog.removeClass('waiting');

				sensus.commons.modules.bForceReload = true;
				var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
				var oLight = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
				var sStatus = oLight.lifeCycleState;
				sensus.commons.modules.bForceReload = false;
				if (sStatus)
				{
					$('input:radio[value="'+sStatus+'"]').attr('checked', true);
				}
			});

			actionDialog.addClass('waiting');
			actionDialog.dialog('open');
		}
	},

	alertDetails : {
		/**
		 * The dialog title.
		 */
		title: $.sc.locale("smartpointdetail.page.alertdetail"),

		/**
		 * The dialog width.
		 */
		width : 1000,

		/**
		 * The dialog buttons (submit and cancel).
		 */
		buttons : (function () {
			return {};
		}),

		/**
		 * The function that will be called when the action dialog is launched.
		 *
		 * @param actionDialog
		 *            a reference to the actionDialog objext
		 */
		action : function(actionDialog) {

			$('#action-dialog').empty().load("light/alertDetailDialog" + "?_" + Math.random(), function() {

				var iNotificationHistoryId = sensus.commons.modules.dialogSettings.alertDetails.actionId;
				var oAlert = $("#" + iNotificationHistoryId);
				var oAlertClassification = oAlert.data('alertClassification');
				var oLastOperationalData = oAlert.data('lastOperationalData');
				var sMessageDetail = "";


				fnFillAlertDetail = function(oAlertClassification, oLastOperationalData)
				{

					var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
					var oFirstMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);

					if(oFirstMeter)
					{

						var iLightTimeZone = oFirstMeter.radio.location.timeZoneInfo.offsetInHours;

						if(!$.sc.isNullOrUndefined(oLastOperationalData))
						{
							var sCurrentLast = ((oLastOperationalData.acCurrent) ? oLastOperationalData.acCurrent : "-- ") + "<sup><small>A</small></sup>";
							var sVoltageLast = ((oLastOperationalData.acVoltage) ? oLastOperationalData.acVoltage : "-- ") + "<sup><small>V</small></sup>";
						}
						else
						{
							var sCurrentLast = "-- <sup><small>A</small></sup>";
							var sVoltageLast = "-- <sup><small>V</small></sup>";
						}

						if (oAlertClassification.alertType == 'WARNING')
						{
							$('.summary span').addClass('icon-small-warning');
							$('.summary li.fail').addClass('warning');
						}

						sMessageDetail = "<tr><td>" + $.sc.locale("smartpoint.status."+oAlertClassification.alertType+"") + "</td><td>" + sVoltageLast + "</td><td>" + sCurrentLast + "</td><td>" + $("#light-state").text()+ "</td><td>"+$.sc.locale('commons.pages.event_schedule')+"</td><td>"
						+ $.sc.dateFormat(oAlertClassification.messageDate, sensus.settings.DATE_FORMAT + " h:i:s.fff A", null, false, iLightTimeZone) + "</td></tr>";
						$('.fail').append($.sc.locale('smartpoint.status.' + oAlertClassification.alertSubType));
						$(".summary .small-table tbody").append(sMessageDetail);
					}
				}

				if(!oAlertClassification)
				{
					var fnNotificationHistoryCallback = function(oResponse)
					{
						var aOperationalData = {};
						if(oResponse)
						{

							for ( var i = 0; i <  oResponse.notificationHistories.length; i++)
							{
								if(oResponse.notificationHistories[i].operationalDataValues.length > 0 )
								{
									aOperationalData["acCurrent"] =  oResponse.notificationHistories[i].operationalDataValues[0].value;
									aOperationalData["acVoltage"] =  oResponse.notificationHistories[i].operationalDataValues[1].value;
								}
								else
								{
									aOperationalData = null;
								}
								fnFillAlertDetail(oResponse.notificationHistories[i].alertClassifications[i], aOperationalData)
							}
						}
					}

					var oNotificationHistoryRequest = new notificationHistoryRequest(new NotificationHistoryCriteria(iNotificationHistoryId.split("-")[0], null, null, null, iNotificationHistoryId.split("-").pop()));
					$.sc.getJson('api/light/fetchhistorybyid', oNotificationHistoryRequest, false, fnNotificationHistoryCallback, null, null, null, null);
				}
				else
				{
					fnFillAlertDetail(oAlertClassification, oLastOperationalData);
				}

			});

			actionDialog.dialog('open');

		}
	}
}
</script>
</sec:authorize>
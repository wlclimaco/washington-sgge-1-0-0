sensus.commons.modules.dialogSettings = {
		
	/**
	 * Configuration for the Edit Light Status dialog.
	 */
	 editLightStatusDetail : {
		/**
		 * The dialog title.
		 */
		title : sensus.locale.get("smartpoint.actions.editLight"),

		width: 450,

		/**
		 * Whether this dialog requires a smartpoint list.
		 */
		requiresSmartpoints : false,

		fnReloadLightStatusDetail : function() {
		
			sensus.commons.modules.content.lightAlerts.fnUpdateLightAlerts();
			
			$('#detail-map-container div').remove();

			sensus.commons.modules.bForceReload = true;
			sensus.commons.modules.summaryData.lightState.init();
			sensus.commons.modules.summaryData.lightStatus.init();
			var aData = sensus.commons.modules.summaryData.lightStatus.fnGetResponse().firstLight;
			var objLatLon = [];
			objLatLon.push({
				latitude   : sensus.commons.modules.util.fnGetParameterValue(aData, "LATITUDE", 'value'),
				longitude  : sensus.commons.modules.util.fnGetParameterValue(aData, "LONGITUDE", 'value'),
				status     : aData.currentStatusMessage.lightStatusEnumValue,
				id         : $('#light-rni-id').val()
			});
			$("#detail-map-container").addClass("detail-map");
			sensus.util.mapopen.iZoom = -1;
			sensus.util.mapopen.mapIt('detail-map-container', objLatLon);
			sensus.commons.modules.bForceReload = false;


		},

		/**
		 * The dialog buttons (submit and cancel).
		 */
		buttons : [{
				id : "edit-light-status-submit",
				text : sensus.locale.get("smartpoint.actions.editLightStatus"),
				click : function() {

					if (!$('.radio-list input').is(':checked')) {

						$('.radio-list input:first').validationEngine('showPrompt', sensus.locale.get("validation.multipleFields"), 'red', '', true);

					} else {

						var aId = [$.address.parameter('id')];
						var	sTypeStatus = $('.radio-list input:checked').val();

						if (sTypeStatus == "DEACTIVATED") {

							// Submit Ajax Action
							var oRequest = { 'lightRequest': new lightRequest(null, aId, null, false, sensus.widgets.datatable.fnConvertEnum('LightStatusEnum', sTypeStatus)) };
							$.ajaxValidator.fnDoCall(sensus.settings.updateLightStatus, oRequest, false, sensus.commons.modules.reloadData,sensus.locale.get("smartpoint.actions.editLightStatus.success"));
							$(this).dialog('close');

						} else {

							// Submit Ajax Action
							var oRequest = {'lightRequest': new lightRequest(null, aId, null, false, sensus.widgets.datatable.fnConvertEnum('LightStatusEnum', sTypeStatus)) };
							$.ajaxValidator.fnMonitor(sensus.settings.updateLightStatus, oRequest, false, sensus.commons.modules.dialogSettings.editLightStatusDetail.fnReloadLightStatusDetail, sensus.locale.get("smartpoint.actions.editLightStatus.success"), true);
							$(this).dialog('close');

						}

						if (sensus.settings.monitor != 3) {
							$(this).dialog('close');
						}
					}
				}
			}, {
				id: "save-search-cancel",
				text : sensus.locale.get("action.savesearch.cancel"),
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
			actionDialog.empty().load(sensus.settings.editLightStatusInclude, function() {
				actionDialog.removeClass('waiting');
				
				sensus.commons.modules.bForceReload = true;
				var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
				var oLight = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
				var sStatus = oLight.currentStatusMessage.lightStatusEnum;
				sensus.commons.modules.bForceReload = false;
				if (sStatus) {
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
		title: sensus.locale.get("smartpointdetail.page.alertdetail"),
		
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
	
			$('#action-dialog').empty().load(sensus.settings.alertDetailsInclude + "?_" + Math.random(), function() {
				
				var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
				var oLight = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
				var iStatusMessageId = sensus.commons.modules.dialogSettings.alertDetails.actionId.split("-")[0];
				var iStatusSubtype = sensus.commons.modules.dialogSettings.alertDetails.actionId.split("-")[1];
				var sCurrentLast = sensus.commons.modules.util.fnGetParameterValue(oLight, "AC_CURRENT", 'value') + "<sup><small>A</small></sup>";
				var sVoltageLast = sensus.commons.modules.util.fnGetParameterValue(oLight, "AC_VOLTAGE", 'value') + "<sup><small>V</small></sup>";		
				var sMessageDetail = "";
				var oRequest = [{'id' : iStatusMessageId}];
				var oResponse = $.ajaxValidator.fnDoCall("smartpoint/ajax.fetchStatusMessage.action", {'lightRequest': new lightRequest(null,null,null,null ,null,null,null ,null,null,null,null,null,oRequest )},false,null,null);
				var oStatusMessage = oResponse.statusMessage;
				var sStatusSubtype = '';
				
				for (i in oStatusMessage.statusExceptions) {
					if (oStatusMessage.statusExceptions[i].id == iStatusSubtype) {
						sStatusSubtype = sensus.locale.get(oStatusMessage.statusExceptions[i].labelKey);
					}
				}
				
				if (oStatusMessage.lightStatusEnum == 'WARNING') {
					$('.summary span').addClass('icon-small-warning');
					$('.summary li.fail').addClass('warning');
				}
				
				sMessageDetail = "<tr><td>" + sStatusSubtype + "</td><td>" + sVoltageLast + "</td><td>" + sCurrentLast + "</td><td>" + $("#light-state").text()+ "</td><td>Event Schedule</td><td>" 
				+ $.date.dateFormat(oStatusMessage.date, sensus.settings.dateFormatMask) + " " +  $.date.dateFormat(oStatusMessage.date, 'h:i:s.fff A') + "</td></tr>";							
				$('.fail').append(sStatusSubtype);
				$(".summary .small-table tbody").append(sMessageDetail);
			
					
					
				
			});
			
			actionDialog.dialog('open');
			
		}
	}			
}
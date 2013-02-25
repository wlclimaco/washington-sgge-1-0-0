<script type="text/javascript">
/**
 * @namespace sensus.pages.smartpointdetailhistory
 * @description The action namespace for the SmartPoint Detail Page.
 */

/**
 * @fileoverview Defines the various action dialog options and common behaviors
 *               for the group-related actions.
 * 
 * @author Alexandre Tiveron
 */

sensus.pages.smartpoint.detail.dialogSettings = {
		/**
		 * Configuration for the "Processing Summary" dialog.
		 */
		processingSummary : {
			/**
			 * The dialog title.
			 */
			title : sensus.locale.get("action.processingsummary.title"),
			/**
			 * Whether this dialog requires a smartpoint list.
			 */
			requiresSmartpoints : false,
						
			/**
			 * The function that will be called when the action dialog is launched.
			 * 
			 * @param actionDialog
			 *            a reference to the actionDialog objext
			 */
			action : function(actionDialog) {
				
				$(actionDialog).empty().load(sensus.settings.processingSummary, {processId : sensus.pages.smartpointdetail.processingSummaryData}, function() {
					$(actionDialog).removeClass("waiting");
				});
				$(actionDialog).dialog('open');
				
			}
		
		},
		
		/**
		 * Configuration for the Baseline Eco-Mode Detail Status dialog.
		 */
		 baselineEcoMode : {
			/**
			 * The dialog title.
			 */
			title : sensus.locale.get("smartpoint.actions.ecoModeBaseLine.title"),

			width: 800,

			/**
			 * The dialog buttons (submit and cancel).
			 */
			buttons : [{
					id : "baseline-eco-mode-submit",
					text : sensus.locale.get("smartpoint.actions.ecoModeBaseLine.save"),
					click : function() {

					
						$("#baseline-detail").submit();

					}
				}, {
					id: "baseline-eco-mode-cancel",
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
				sensus.commons.modules.bForceReload = true;
				var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
				var sCurrentType = '';
				var sReplaced = '';
				if(oResponse.firstLight.ecoModeBaseline && oResponse.firstLight.ecoModeBaseline.replacedType){
				
					sCurrentType = oResponse.firstLight.ecoModeBaseline.replacedType;
					sReplaced = oResponse.firstLight.ecoModeBaseline.replacedWattage;
				
				}
				sensus.commons.modules.bForceReload = false;
				var sHtml = '<form id="baseline-detail"  name="baseline-detail" method="post" action="#"><fieldset class="edit-only"><ul><li><label for="">'+sensus.locale.get('smartpoint.actions.replacedType')+':</label><select id="light-type">';
				var aType = sensus.settings.enums.LightTypeEnum;
				var sSelected = '';
				for (var i in aType) {

					if (aType.hasOwnProperty(i)) {

						if(aType[i] == sCurrentType){
						
							sSelected = 'selected';
						
						} else {
						
							sSelected = '';
						
						}
						sHtml += '<option value="'+aType[i]+'" '+sSelected+'>'+aType[i]+'</option>';

					}

				}

				sHtml += '</select></li><li><label for="">'+sensus.locale.get('smartpoint.actions.replacedWattage')+':</label><input type="text" id="baseline-wattage" class="validate[required, custom[integer, min[0], max[5000]]] short" value="'+sReplaced+'"></li>';
				sHtml += '<li class="note"><p>'+sensus.locale.get('smartpoint.actions.ecoModeBaseLine.note')+'</p></li></ul></fieldset></form>';

				actionDialog.html(sHtml);
				actionDialog.dialog('open');
				
				$("#baseline-detail").validationEngine('attach', {
					validationEventTrigger : 'submit',
					scroll : false,
					onValidationComplete   : function(form, status) {

						if (status) {

							var aEcoModeBaseline = [];
							var aParameters = [];
							var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
							var poleId = $.grep(oResponse.firstLight.parameters, function(e) { return e.propertyEnum == "POLE_ID"; })[0].value;
							aParameters.push({'propertyEnum':'POLE_ID', 'value': poleId});
							var oLight = {parameters:aParameters};
							var sType = $('#light-type').val();
							var nWattage = $('#baseline-wattage').val();
							aEcoModeBaseline.push(new ecoModeBaseline (oLight, sType, nWattage));
							var response = $.ajaxValidator.fnDoCall("smartpoint/ajax.updateEcoModeBaseLine.action", {'inquiryEcoModeRequest' : new inquiryEcoModeRequest(aEcoModeBaseline)}, false, sensus.pages.smartpoint.detail.ecoMode.data.reload);
							sensus.commons.modules.bForceReload = true;
							var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
							sensus.commons.modules.bForceReload = false;
							actionDialog.dialog('close');
							sensus.widgets.datatable.reloadTable(sensus.pages.smartpoint.detail.ecoModeTable);
							$('#ecomode_baseline').html(sensus.locale.get("smartpoint.detail.ecoMode.baseline", nWattage, sType));
							
							var nValue = Math.round(oResponse.firstLight.ecoMode);
							nPercent = nValue + '%';
							
							var sEcoModeLabel = sensus.locale.get('smartpoint.detail.ecoMode.Economy');
							
							if(nValue >= 15 && nValue <= 50){
							
								sEcoModeLabel = sensus.locale.get('smartpoint.detail.ecoMode.Value');
							
							} else if(nValue < 15){
							
								sEcoModeLabel = sensus.locale.get('smartpoint.detail.ecoMode.Security');
							
							}
							
							$('#ecomode-percent').next().html(sEcoModeLabel);							
							$('#ecomode-percent').html(nPercent);

						}

					}

				});					

			}

		}

	};
</script>
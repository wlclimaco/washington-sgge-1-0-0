/**
 * @description Control Lights
 * @version     -
 * @file        widget_control_lights.js
 * @author      Alex Tiveron
 *
 *
 */

$.fn.wLightControl = function (bLightList) {

	/**
	 * Configuration for the "control light" dialog.
	 */
	var lightControlDialog = {
		/**
		 * The dialog title.
		 */
		title : $.sc.locale("action.switchlight.title"),
		/**
		 * Whether this dialog requires a smartpoint list.
		 */
		requiresSmartpoints : true,

		/**
		 * The dialog width.
		 */
		width : 600,

		/**
		 * The dialog buttons (submit and cancel).
		 */
		buttons : [{
				id     : "turn-on-submit",
				text   : $.sc.locale("action.switchlight.submit"),
				click  : function() {

					var sBlinkValue = 'NONE';
					var sDimValue = 100;
					var sOverride = 'SCHEDULED';
					var dExpireDate = null;
					var bValidDate = true;
					var bValidValue = true;
					var isAllRows = sensus.widgets.datatable.isAllRows;
					var aIds = isAllRows ? sensus.widgets.datatable.deselectedRows : sensus.widgets.datatable.selectedRows;
					var sValue = $('.control input:checked');

					if(sValue.hasClass('dim')){

						sDimValue = sValue.val();

					} else if(sValue.hasClass('blink')){

						sBlinkValue = sValue.val();

					}

					//Pick the type of 'Manage Override'
					if (sensus.settings.userContext.userRole == 'ROLE_Role.Admin') {

						if ($('ul.radio li input:checked').val() == 'PER_DATE') {

							if (!$("#turnOnOffLightsForm").validationEngine('validateField', $('.date-short:eq(0)')) && !$("#turnOnOffLightsForm").validationEngine('validateField', $('.date-short:eq(1)'))) {

								var sTime = $('#override-time').val();
								var dStartDate = $.datepicker.parseDate(sensus.settings.dateFormatMask, $("#override-date").val());
								var aHour = sTime.split(":");
								var sHour = sTime.toLowerCase().contains("am") ? aHour[0] : (parseInt(aHour[0], 10) < 12 ? parseInt(aHour[0], 10) + 12 : parseInt(aHour[0], 10));
								var sMin = aHour[1].split(" ")[0];

								dStartDate.setHours(sHour);
								dStartDate.setMinutes(sMin);
								dStartDate.setSeconds(00);
								dExpireDate = $.date.fnTimeToUTC(dStartDate);
								sOverride 	= $('ul.radio li input:checked').val();

							} else {

								bValidDate = false;

							}

						} else {

							sOverride = $('ul.radio li input:checked').val();

						}

					}

					var oTypeControl = $('#radio_light label.ui-state-active .label-desc');

					if (oTypeControl.length) {
						if (!oTypeControl.text().length) {
							bValidValue = false;
						}
					}

					if (bValidValue && bValidDate) {

						var fnCallBack = null;
						var bMonitor = false;
						var nId = null;
						var oSearchParameters = { 'searchParameters' :  sensus.util.page.getSearchParameters()};
						var aIds = isAllRows ? sensus.widgets.datatable.deselectedRows : sensus.widgets.datatable.selectedRows;

						if (!bLightList) {

							if(sensus.commons && sensus.commons.modules) {

								fnCallBack = sensus.commons.modules.summaryData.lightInformation.fnUpdateLightInformation;

							}
							bMonitor = true;
							nId =  $.address.parameter('id');
							aIds = [$.address.parameter('id')];
							oSearchParameters = null;
							if(sensus.util.mapopen && sensus.util.mapopen.nId){

								bMonitor = false;
								nId = sensus.util.mapopen.nId;
								aIds = [sensus.util.mapopen.nId];
								//sensus.util.mapopen.nId = null;
								fnCallBack = null;
							}

						}

						var oRequest = {'lightRequest': new lightRequest(nId, aIds, null, isAllRows, null, bMonitor, oSearchParameters, sDimValue, sBlinkValue, sOverride, dExpireDate, false)};
						$.ajaxValidator.fnMonitor("/slc/util/ajax.controlLights.action", oRequest, false, fnCallBack, $.sc.locale("action.longRunningProcessDialog.confirm"), bMonitor);
						$('#control-light-dialog').dialog( "close" );

					} else {

						if (!bValidValue) {

							$('#radio3').validationEngine('showPrompt', $.sc.locale("validation.multipleFields"), 'red', '', true);

						}

					}
					/*
					if (sensus.settings.monitor != 3) {

						$(this).dialog('close');

					}
*/
				}

			},
			{
				id     : "turn-on-cancel",
				text   : $.sc.locale("action.switchlight.cancel"),
				click  : function() {
					$(this).dialog('close');
				}
			}
		],

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
		 * The name of the form to clear when launching the dialog.
		 */
		form : 'switch_lights_form',

		/**
		 * The function that will be called when the action dialog is launched.
		 *
		 * @param actionDialog
		 *            a reference to the actionDialog objext
		 */
		action : function(actionDialog) {

			actionDialog.addClass('waiting');

			if (bLightList) {

				var nCheckedNum = 0;
				if (sensus.widgets.datatable.isAllRows == true) {

					nCheckedNum = sensus.widgets.datatable.allRowsCount - sensus.widgets.datatable.deselectedRows.length;

				} else {

					nCheckedNum = sensus.widgets.datatable.selectedRows.length;

				}

				if ( 0 != nCheckedNum) {

					$('.message').removeClass("ui-state-error");

				} else {

					actionDialog.hide();
					$('.messaging').hide();
					$('.message').addClass("ui-state-error");
					return false;

				}

			}

				actionDialog.empty().load("/slc/util/include.controlLight.action" + "?_" + Math.random(), function() {

					var oControlLights  = $('#radio_light');

					oControlLights.buttonset();

					 //slide the content inner at DIM and BLINK
					$(".control .ui-button-text").mouseenter(function() {

						$(this).find('div').fadeIn('fast');

					}).mouseleave(function(){

						$(this).find('div').fadeOut('fast');

					});

					$('.smartpoint-count').html(nCheckedNum);

					//Set Calendrical Time
					$('#override-time').calendricalTime();

					//Show the Value of DIM and BLINK options
					$("#dim_options a, #blink_options a").mouseenter(function() {

						$(this).addClass('ui-state-hover');

					}).live("click", function(e) {

						e.preventDefault();
						$('.control input:checked').prop('checked', false);

						var sActiveControl = $(this).closest('label');
						var sValue = $(this);

						sValue.next('input').prop('checked', 'checked');

						oControlLights.find('.label-desc').text('');
						oControlLights.find('label').removeClass('ui-state-active');

						sActiveControl.addClass('ui-state-active');
						sActiveControl.find('.label-desc').html('<strong>' + ' ' + sValue.text() + '</strong>');

					}).mouseleave(function() {

						$(this).removeClass('ui-state-hover');

					});

					$('#radio1, #radio4').click(function(){
						oControlLights.find('input:not(#radio1, #radio4)').prop('checked', false);
						oControlLights.find('.label-desc').text('');
					});

					if(!bLightList) {

						oControlLights.find('label').removeClass('ui-state-active');
						oControlLights.find('input').prop('checked', false);

						$('#turnOnOffLightsForm h2, .note').remove();
						$("#turnOnOffLightsForm").addClass("max-form");

						if(sensus.commons && sensus.commons.modules) {

							var oResponse = sensus.commons.modules.summaryData.deviceInformation.fnGetResponse();


						} else {

							var oResponse = sensus.util.mapopen.oResponse;
							$.address.parameter('id', oResponse.firstLight.id);

						}

						if($.grep(oResponse.firstLight.parameters, function(e) { return e.propertyEnum == "DIMMABLE"; })[0].value.toLowerCase() == "true") {

							var oDimOptions = $("#dim_options ul");

							if(oResponse.firstLight.lightIntensitylevels.length){

								oDimOptions.empty();
								for (var i = 0; i < oResponse.firstLight.lightIntensitylevels.length; i++) {
									if (oResponse.firstLight.lightIntensitylevels[i].percentage != 100) {
										oDimOptions.append('<li><a href="" class="ui-button ui-widget ui-state-default ui-button-text-only">'+oResponse.firstLight.lightIntensitylevels[i].percentage+'%</a><input type="radio" class="hide dim" value="'+oResponse.firstLight.lightIntensitylevels[i].percentage+'"></li>');
									}
								}

							} else {

								var aLightLevels = [25,50,75,90];
								oDimOptions.empty();
								for (var i = 0; i < aLightLevels.length; i++) {

									oDimOptions.append('<li><a href="" class="ui-button ui-widget ui-state-default ui-button-text-only">'+aLightLevels[i]+'%</a><input type="radio" class="hide dim" value="'+aLightLevels[i]+'"></li>');

								}

							}

						} else {

							$("#dim_options").parent().unbind("mouseenter mouseleave").parent().addClass("ui-state-disabled");

						}

						var sMsg = '';

						if(oResponse.firstLight.lightStateEnum == 'ON'){

							if(oResponse.firstLight.lightBlinkEnumValue != 0){

								var oBlink = $('#radio3');
								oBlink.next('label').find('input[value="' + oResponse.firstLight.lightBlinkEnum + '"]').prop('checked', true);

								if (oResponse.firstLight.lightBlinkEnumValue == 2) {

									sMsg = ' <strong>' + $.sc.locale('smartpoint.actions.controlLights.slow') + '</strong>';

								} else if (oResponse.firstLight.lightBlinkEnumValue == 1) {

									sMsg = ' <strong>' + $.sc.locale('smartpoint.actions.controlLights.fast') + '</strong>';

								}

								oBlink.next('label').find('.label-desc').html(sMsg);
								$('.control input:checked').closest('label').addClass('ui-state-active');

							} else if(oResponse.firstLight.lightIntensityEnumValue != 0 && oResponse.firstLight.lightIntensityPercentageValue != 100){

								var oDim = $('#radio2');

								oDim.next('label').find('input[value="' + oResponse.firstLight.lightIntensityPercentageValue + '"]').prop('checked', true);
								sMsg = ' <strong>' + oResponse.firstLight.lightIntensityPercentageValue + '%</strong>';
								oDim.next('label').find('.label-desc').html(sMsg);
								$('.control input:checked').closest('label').addClass('ui-state-active');

							} else {

								$('#radio4').prop("checked", true);

							}

						} else {

							$('#radio1').prop("checked", true);

						}

						$('.control input:checked').next('label').addClass('ui-state-active');

						if(oResponse.firstLight.overrideEnum == "PER_DATE"){

							$('#cpExpireSchedule').prop("checked", true);

						} else if(oResponse.firstLight.overrideEnum == "PERMANENT"){

							$('#cpExpireManual').prop("checked", true);

						}

					} else {

						$(".highlight").remove();

					}

					$('ul.radio input[value="SCHEDULED"], ul.radio input[value="PERMANENT"]').click(function() {
						$('#override-date, #override-time').val('');
					});

					$('#override-date, #override-time').focus(function() {
						$('ul.radio input').prop('checked', false);
						$('#cpExpireSchedule', 'ul.radio').prop('checked', true);
					});

					// Manage Override DatePicker and Valid Dates
					var dateFormat = sensus.settings.dateFormatMask;
					var sClassTypeFormat = 'datePt';
					var sManageDate = $('.date-short:first');
					var dValidDate = new Date();

					dValidDate.setTime(dValidDate.getTime() + 60 * 60 * 24 * 45 * 1000);

					if ( dateFormat == 'mm/dd/yy') {

						sClassTypeFormat = 'dateEn';

					}

					sManageDate.datepicker({
						minDate: "+0D",
						maxDate: "+45D",
						dateFormat : sensus.settings.dateFormatMask
					});

					var dEnd = $.datepicker.formatDate('yy-mm-dd', sensus.util.page.addDays(sensus.settings.datenow,45));
					var dToday = $.datepicker.formatDate('yy-mm-dd', new Date(sensus.settings.datenow));
					sManageDate.addClass("short hasDatepicker validate[required, custom["+sClassTypeFormat+"], future["+dToday+"], past["+dEnd+"]");

				});

			actionDialog.removeClass('waiting');
			actionDialog.dialog('open');

		}
	};

	var objThat = $(this);

	objThat.on("click", function() {

		$.sc.launchActionDialog("onOffLight", lightControlDialog, "control-light-dialog");

		return false;

	});
};
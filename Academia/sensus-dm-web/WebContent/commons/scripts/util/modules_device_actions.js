sensus.commons.modules.dialogSettings = {

	premiseID : "",
	parentID : "",
	clientEndPointId: "",

	/** Configuration for the "Demand Response" dialog. */
	DemandResponse : function (bDetail, sActionName, iActionId, filterSearch) {

		return {

			// The dialog title.
			title : sensus.locale.get("action.hanDemandResponse.title"),

			width : 760,

			close : function(actionDialog) {
				$('.formError').remove();
			},

			// The dialog buttons (submit and cancel).
			buttons : (function() {

				var objButtons = {};

				objButtons[sensus.locale.get("action.hanDemandResponse.submit")] = function() {

					var oCreateDemandResponseForm = $("#createDemandResponseForm");

					// Validate the form
					if ((oCreateDemandResponseForm.validationEngine('validateField', '#demandResponseTime')) || (oCreateDemandResponseForm.validationEngine('validateField', '#demandResponseWhen'))
							|| (oCreateDemandResponseForm.validationEngine('validateField', '#duration')) || (oCreateDemandResponseForm.validationEngine('validateField', '#pctHeating'))
							|| (oCreateDemandResponseForm.validationEngine('validateField', '#pctCooling')) || (oCreateDemandResponseForm.validationEngine('validateField', '#enrollment-code'))
							|| (oCreateDemandResponseForm.validationEngine('validateField', '#duty-cycle')) || (oCreateDemandResponseForm.validationEngine('validateField', '#load-adjustment'))) {

						return false;

					} else {

						var oDemandResponseWhen = $("#demandResponseWhen");
						var dDateServer 		= $.date.setDateServer();
						var sDateFormat 		= sensus.settings.dateFormatMask.replace('yyyy','yy');
						var dDateDemandResponse = $.date.parseDate(oDemandResponseWhen.val(), sDateFormat);
						var oDateAndDuration 	= null;
						var oTimeZone 			= null;

						// Lower 2 days of the server date, to verify if the date of Demand response is less than 24 hours in the past
						dDateServer.setDate(dDateServer.getDate() - 2);

						if (bDetail) {

							oTimeZone = sensus.pages.device.module.util.getTimeZoneMinutes(
									sensus.pages.device.module.request.get("device") || {});

							dDateServer = $.date.dateFormat(dDateServer, sDateFormat);
							dDateServer = $.date.parseDate(dDateServer, sDateFormat);

						} else {

							dDateServer = $.date.dateFormat(dDateServer, sDateFormat);
							dDateServer = $.date.parseDate(dDateServer, sDateFormat);

						}

						// Validation if date of Demand Response is greater than 24 hours in the past
						if (!$.ajaxValidator.fnIsNullOrUndefined(dDateServer) && !$.ajaxValidator.fnIsNullOrUndefined(dDateDemandResponse)) {

							if (!($.date.setCompareToDate(dDateServer, dDateDemandResponse))) {

								oDemandResponseWhen.validationEngine('showPrompt', sensus.locale.get("systemintelligence.dialogDemandResponse.dateValidation"), 'red', '', true);

							}  else {

								// Function called to validate the date, and get the duration and valid date for demand response action.
								oDateAndDuration = sensus.util.actionrequestutil.demandResponse["fnValidatorDateAndDuration"]($("#demandResponseWhen"), $("#demandResponseTime"), $("#duration"), oTimeZone);

							}
						}

						sensus.widgets.datatable.clearSelects();

						// Remove the box of validation when clicked
						$('.formError').click(function() {
							$(this).remove();
						});

						if (!$.ajaxValidator.fnIsNullOrUndefined(oDateAndDuration)) {

							var aDevicesClasses	= [];

							$("#toggleClass").find("li.checkbox :checked").each(function() {
								aDevicesClasses.push($(this).val());
							})

							var oHeating = $('#pctHeating');
							var oCooling = $('#pctCooling');

							if (oHeating.val() != "") {
								// Function to Set the field of temperature according with system temperature
								sensus.util.actionrequestutil.demandResponse["fnSetTemperatureValue"](oHeating);
							}

							if (oCooling.val() != "") {
								// Function to Set the field of temperature according with system temperature
								sensus.util.actionrequestutil.demandResponse["fnSetTemperatureValue"](oCooling);
							}

							var demandResponse = new DemandResponse({
								demandResponseDuration : oDateAndDuration.duration,
								enrollmentCode         : $('#enrollment-code').val(),
								heating				   : parseInt($('#pctHeating').val(), 10),
								cooling				   : parseInt($('#pctCooling').val(), 10),
								dutyCycleRate		   : parseInt($('#duty-cycle').val(),10),
								loadAdjustment		   : parseInt($('#load-adjustment').val(),10),
								criticalityLevel	   : parseInt($('#hanCriticality').val(),10),
								randomizeStart		   : $('#randomizeStart').is(":checked"),
								randomizeEnd		   : $('#randomizeEnd').is(":checked"),
								actionType			   : {id: parseInt(iActionId,10)},
								actionTypeName		   : sActionName,
								dateTimeString		   : oDateAndDuration.date,
								onDemand			   : true,
								isMonitored			   : true,
								deviceClasses 		   : aDevicesClasses
							});

							sensus.util.actionrequestutil.fnActionCall('api/deviceop/apply', bDetail, sActionName, iActionId, "commons.pages.demandResponseSucess", filterSearch, demandResponse);

							$("#action-dialog").dialog('close');

						}

						return false;
					}
				}

				objButtons[sensus.locale.get("action.removefromgroup.cancel")] = function() {

					$(this).dialog('close');

				}

				return objButtons;
			})(),

			action : function(actionDialog) {

				actionDialog.load("action/demandResponse", function() {

					$("#createDemandResponseForm").validationEngine('attach');

					// Get DATE of the Server and convert to default format
					var dateToday 			= $.date.setDateServer(true);
					var sDateFormat 		= sensus.settings.dateFormatMask.replace("yyyy", "yy");
					var oDateToday 			= $("#date-today-DR");
					var sDate 				= $("#demandResponseWhen").val();
					var	oDemandResponseWhen = $("#demandResponseWhen");
					var oDemandResponseTime = $('#demandResponseTime');
					var oPctHeating 		= $("#pctHeating");
					var oPctCooling 		= $("#pctCooling");
					var oTimeZoneElement 	= $('.timezone');
					var sTimeZone 			= sensus.locale.get('smartpointdetail.tabs.about.timeZone');
					var sValidateCelsius 	= "validate[min[0],max[25.4]]";
					var sValidateFahrenheit = "validate[min[0],max[45.72]]";
					var sTemperatureType 	= sensus.settings.temperatureType;
					var sClassTypeFormat 	= 'datePt';

					if (sDateFormat == 'mm/dd/yy') {
						sClassTypeFormat = 'dateEn';
					}

					$('.temperature-type').text(sensus.locale.get("systemintelligence.dialogDemandResponse.validInputs."+ sTemperatureType));

					if (sTemperatureType.toLowerCase() === sensus.locale.get("systemsettings.page.temperature.celsius").toLowerCase()) {

						oPctHeating.addClass(sValidateCelsius);
						oPctCooling.addClass(sValidateCelsius);

					} else {

						oPctHeating.addClass(sValidateFahrenheit);
						oPctCooling.addClass(sValidateFahrenheit);

					}

					// Fill the fields when dialog load
					if (sDate == "") {

						// If was in detail page is apply the device time zone, otherwise apply system time zone.
						if (bDetail) {

							var oTimeZone = sensus.pages.device.module.util.getTimeZoneMinutes(
									sensus.pages.device.module.request.get("device") || {});

							// Display the timezone of device
							oTimeZoneElement.text(sTimeZone + ' (' + oTimeZone.sTZName + ') ');

							oDemandResponseWhen.val($.date.dateFormat(dateToday, sDateFormat, oTimeZone));
							oDemandResponseTime.val($.date.dateFormat(dateToday, "h:ia", oTimeZone));

						} else {

							// Display the timezone of system
							oTimeZoneElement.text(sTimeZone + ' (' + sensus.settings.timezone + ') ');

							oDemandResponseWhen.val($.date.dateFormat(dateToday));
							oDemandResponseTime.val($.date.dateFormat(dateToday, "h:ia"));

						}
					}

					// Initialize DatePicker in the field when
					oDemandResponseWhen.datepicker({
						dateFormat: sDateFormat,
						minDate: "-0D",
						onSelect: function() {
							$('.formError').remove();
						}
					});

					// Field with id demandResponseTime
					$('.time').calendricalTime();

					// Toggle Class
					$('#toggleClass').toggle('blind', null, 500);

					$('#click').click(function(e) {

						e.preventDefault();

						$('#toggleClass').toggle('blind', null, 500);

						if ($('span', this).hasClass('ui-icon-triangle-1-e')) {

							$('span', this).switchClass('ui-icon-triangle-1-e', 'ui-icon-triangle-1-s', 500);

						} else {

							$('span', this).switchClass('ui-icon-triangle-1-s', 'ui-icon-triangle-1-e', 500);

						}

					});

					actionDialog.removeClass('waiting');
				});

				actionDialog.addClass('waiting');
				actionDialog.dialog('open');
			}
		}
	},

	/** Connect for the Meter Page */
	connectHanDevice : function (bDetail,item,typeId, filterSearch) {

		return {
			title : "Action - Connect HAN Device",

			buttons : (function() {
				var objButtons = {}
				objButtons["Connect HAN Device"] = function () {

					var oData = {
						sMacAddress : $("#mac_address").val(),
						sInstallCode : $("#install_code").val(),
						sClientEndPointId : $("#device-id", "h1").text(),
						sCustomerId : $("#customer-id").text()
					}

					//Validation the inputs
					if (!sensus.pages.device.module.summaryDatas.actions.connectFunctions.fnValidateConnectHanFields(oData)) {

						var oResponse,
							bValidFields = false;

						if (sensus.commons.modules.dialogSettings.connectHanDevice.bHanExists) {
							// Connect existing

							var devices = sensus.util.actionrequestutil.fnPrepareDevice(true);

							oData.sDeviceId = $("#device-id", "#connect-han-device-form").text();
							oData.sManufacture = null;
							oData.sModelNumber = null;
							oData.iActionId    = typeId
							oData.sActionName  = item;
							oData.devices      = devices;
							oData.sDeviceType = $("#device-type").text();
							oData.sFlexNetId = $("#flexnetid").text();
							oData.sCustomerId = $("#customer-id").text();

							bValidFields = true;

						} else {
							// Connect with Import

							var devices = sensus.util.actionrequestutil.fnPrepareDevice(true);

							oData.sDeviceId = $("#device_name_input").val();
							oData.sManufacture = $("#select-manufacture").val();
							oData.sModelNumber = $("#select-model-number").val();
							oData.sDeviceType = $("#select-device-types :checked").text();
							oData.oInstallCode = $("#install_code").val();
							oData.sFlexNetId = $("#flexnetid").val();
							oData.devices      = devices;

							if (!sensus.pages.device.module.summaryDatas.actions.connectFunctions.fnValidateInsertHanDevice(oData)) {
								oData.sActionName = "sensus.dm.action.import.han.device";
								bValidFields = true;
							}

						}

						if (bValidFields) {

							oResponse = sensus.pages.device.module.summaryDatas.actions.connectFunctions.fnApplyHanDevice(oData);

							sensus.commons.modules.dialogSettings.connectHanDevice.bHanExists = undefined;

							$("#action-dialog").dialog('close');

						}
					}
				};

				objButtons["Cancel"] = function () {
					$(this).dialog('close');
				}

				return objButtons;
			})(),

			action : function(actionDialog) {

				actionDialog.load("action/connect_han_device", function() {

					$("#label-mac-address").removeClass("hide");

					$("#connect-han-device-form").validationEngine('attach');

					// Events to clear error validation
					sensus.pages.device.module.summaryDatas.actions.connectFunctions.fnClearValidationEvents(actionDialog);

					var oDom = {
							actionDialog : actionDialog,
							oMacAddress : $("#mac_address", actionDialog),
							oMacAddressValidate : $("#mac_address_validate_icon", actionDialog),
							oFormInfo : $(".floating-form-info", actionDialog),
							oAddFields : $(".add-fields", actionDialog),
							oDeviceType : $("#device-type", actionDialog),
							oDeviceId : $("#device-id", actionDialog),
							oManufacture : $("#manufacture", actionDialog),
							oModelNumber : $("#model-number", actionDialog),
							oSelectDeviceTypes : $("#select-device-types", actionDialog),
							oSelectManufacture :$("#select-manufacture", actionDialog),
							oSelectModelNumber : $("#select-model-number", actionDialog),
							oCustomerId : $("#customer-id", actionDialog),
							oDeviceTypeId : $("#device-type-id", actionDialog),
							oFlexNetId : $("#flexnetid", actionDialog),
					}

					// Set Mask to the Mac Address field
					if( $.isFunction( $.fn.mask) ) {
						$(".mac").mask("**:**:**:**:**:**:**:**");

						// Used to avoid event blur from plugin.
						// The default behavior from the plugin is clean input field when value does not match with mask.
						$(".mac").unbind("blur");
					}

					// Device Type Event Change
					oDom.actionDialog.delegate("#select-device-types", "change", function() {
						sensus.pages.device.module.summaryDatas.actions.connectHANDevice.fnFillManufactureByType($(this).find("option:selected").attr("name"), oDom);
					});

					// Manufacture Event Change
					oDom.actionDialog.delegate("#select-manufacture", "change", function() {
						sensus.pages.device.module.summaryDatas.actions.connectHANDevice.fnFillModelNumberByManufacture(oDom.oSelectDeviceTypes.find("option:selected").attr("name"), $(this).find("option:selected").attr("name"), oDom);
					});

					oDom.oMacAddress.focusout(function(event) {

						var id 		  	   = $(this).val();
						var oResponse 	   = null;
						var device    	   = null;
						var bValidDevice   = true;

						oDom.oMacAddressValidate.removeClass("hideImportant");
						oDom.oMacAddressValidate.removeClass("icn-valid-form icn-notice-form").addClass('icn-sending');

						// FetchHanDeviceByDevice
						if ($('#mac_address').val().replace(/[_:]/g, '').length) {
							oResponse = sensus.pages.device.module.summaryDatas.actions.connectHANDevice.fnGetHanDeviceByDeviceResponse(id)
						}

						// Get First Device from response
						if (oResponse) {

							device = sensus.pages.device.module.util.getFirstDeviceResponse(oResponse);
						}

						if (!$.ajaxValidator.fnIsNullOrUndefined(device)) {
							if (device.deviceType == sensus.constants.services.electric.lcm.name) {
								if (device.lcmTypeEnum != sensus.constants.services.electric.lcm.name) {
									bValidDevice = false;
								}
							}
						}

						if (device && bValidDevice) {
							sensus.commons.modules.dialogSettings.connectHanDevice.bHanExists = true;
							// Fill Container if exists device
							sensus.pages.device.module.summaryDatas.actions.connectHANDevice.fnFillExistingDevice(device, oDom);
						} else {

							oDom.oAddFields.removeClass("hide");
							oDom.oFormInfo.addClass("hide");
							oDom.oMacAddressValidate.removeClass("icn-sending").addClass("icn-notice-form");

							sensus.commons.modules.dialogSettings.connectHanDevice.bHanExists = false;

							$("#mac_id").text(sensus.locale.get("smartpointdetail.tabs.about.connectHanDeviceText",id));

							sensus.pages.device.module.summaryDatas.actions.connectHANDevice.fnFillNonExistingDevice(oDom);
						}

					});

					oDom.oMacAddress.focus(function() {

						$('.mac_addressformError').remove();

					});

				});

				actionDialog.dialog('open');
			},

			close : function(actionDialog) {

				$('.formError').remove();

			}
		}
	},

	/** Connect for Han/LCM page. */
	connectToHan  : function(bDetail,item,typeId, filterSearch) {

		return {

			title : "Action - Connect HAN Device",

			buttons : (function() {
				var objButtons = {}
				objButtons["Connect to HAN"] = function () {

					var devices;
					var electricMeter;

					var oFetch = sensus.pages.device.module.util.ajaxData.fetchDeviceById();

					$.ajaxValidator.fnDoCall(oFetch.url, oFetch.data, false, function(oResponse) {

						// Fill Customer Id and Client end point. This values came from a cache object.
						if (oResponse.operationSuccess && oResponse.firstDevice) {

							devices = oResponse.firstDevice;
						}

						bValidFields = true;

						var parentId = sensus.commons.modules.dialogSettings.connectHanDevice.iId.toString();
						var childId = devices.radio.flexNetId.toString();


						var electricMeter = [{
							flexNetId : parentId
						}]

						var oData = {
							sInstallCode : $("#install_code").val(),
							sFlexNetId : childId,
							sDeviceId : devices.deviceId,
							sCustomerId : devices.radio.customerId,
							iActionId : typeId,
							sActionName : item,
							sDeviceType : $.address.parameter("typeEnum"),
							devices : electricMeter
						}


						// If there is no validation send the action
						if (!sensus.pages.device.module.summaryDatas.actions.connectFunctions.fnValidateConnectToHanFields(oData, sensus.commons.modules.dialogSettings.connectHanDevice.bHanExists)) {

							var oResponse = sensus.pages.device.module.summaryDatas.actions.connectFunctions.fnApplyHanDevice(oData);

							$("#action-dialog").dialog('close');

						}
					});
				};

				objButtons["Cancel"] = function () {
					$(this).empty();
					$(this).dialog('close');
				}

				return objButtons;
			})(),

			action : function(actionDialog) {

				actionDialog.load("action/connect_han_device", function() {

					var oDeviceId = $("#device_id", actionDialog);

					var oDom = {
						actionDialog : actionDialog,
						oMacAddressValidate : $("#mac_address_validate_icon", actionDialog)
					}

					$("#label-device-id").removeClass("hide");
					$("#connect-han-device-form").validationEngine('attach');

					//Remove div of validation error
					$("#install_code").focus(function() {

						$(".install_codeformError").remove();

					});

					//Remove div of validation error
					oDeviceId.focus(function() {
						$(".device_idformError").remove();
					});

					// Fetch if is a valid device id
					oDeviceId.blur(function() {
						oDom.oMacAddressValidate.removeClass("hideImportant");
						oDom.oMacAddressValidate.removeClass("icn-valid-form icn-notice-form").addClass('icn-sending');

						var device;
						var sDeviceIdValue = oDeviceId.val();

						var fnCallback = function (oResponse) {

							// Get First Device from response
							device = sensus.pages.device.module.util.getFirstDeviceResponse(oResponse);

							if (device) {

								// Change Icon
								oDom.oMacAddressValidate.removeClass("icn-sending").addClass("icn-valid-form");
								sensus.commons.modules.dialogSettings.connectHanDevice.iId = device.radio.flexNetId;
								sensus.commons.modules.dialogSettings.connectHanDevice.sType = device.deviceType;

								sensus.commons.modules.dialogSettings.connectHanDevice.bHanExists = true;

							} else {

								// Change Icon
								oDom.oMacAddressValidate.removeClass("icn-sending").addClass("icn-notice-form");

								sensus.commons.modules.dialogSettings.connectHanDevice.bHanExists = false;
							}
						}

						if (sDeviceIdValue) {
							$.ajaxValidator.fnDoCall(sensus.settings.appRoot + "/api/electricdevice/fetch", {deviceId : sDeviceIdValue ,deviceType:"ELECTRIC_METER"}, false, fnCallback);
						}

					});


				});
				actionDialog.dialog('open');

			},

			close : function(actionDialog) {

				$('.formError').remove();

			}
		}
	},

	disconnectFromChildPage  : function(bDetail,item,typeId, filterSearch){

		return {

			/** The dialog title. */
			title: sensus.locale.get("commons.pages.removeConfirm"),

			/** The dialog width. */
			width : 300,

			/** The dialog buttons (submit and cancel). */
			buttons : (function() {
				var buttons = {};
				buttons[sensus.locale.get("commons.pages.remove")] = function() {

					var actionDialogId = $("#action-dialog"),
						sFlexnetId = $.address.parameter('id'),
						sMessagingSmartpointDetail = 'messaging-smartpoint-detail';

					var fnCallback = function () {

						sensus.pages.device.module.reloadModules();
					}

					var sMessagindId;

					if (bDetail) {
						sMessagindId = "messaging-smartpoint-detail";
					}

					sensus.util.actionrequestutil.fnActionCall('api/importFile/upload/action/importFile', bDetail, item, typeId, "smartpointdetail.tabs.about.sucessRemoveHan", filterSearch);

					actionDialogId.dialog('close');
				};
				buttons[sensus.locale.get("commons.pages.cancel")] = function() {

					var actionDialogId = $("#action-dialog");
					actionDialogId.dialog('close');

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

				var actionDialogId = $("#action-dialog");

				actionDialogId.text(sensus.locale.get("smartpointdetail.tabs.about.removeFromHan", sensus.commons.modules.dialogSettings.clientEndPointId, sensus.commons.modules.dialogSettings.parentID));

				actionDialogId.dialog('open');

			}
		}
	},


	SendHANTextMessageDisconnected : {
		/**
		 * The dialog title.
		 */
		title : sensus.locale.get("action.hanTextMessage.title"),

		buttons : (function () {
			return {};

		}),

		height : 140,

		width : 330,

		action : function(actionDialog) {
			actionDialog.html(sensus.locale.get("commons.pages.hanDisconnected"));
			actionDialog.dialog('open');
		}
	},

	/**
	 * Configuration for the "SendHANTextMessage" dialog.
	 */
	HANTextMessage : function(sActionName, iActionId, bDetail, filterSearch) {

		return {

			// The dialog title.
			title : sensus.locale.get("action.hanTextMessage.title"),

			// Whether this dialog requires a smartpoint list.
			requiresSmartpoints : true,

			width : 760,

			// The dialog buttons (submit and cancel).
			buttons : (function() {

				var objButtons = {};

				objButtons[sensus.locale.get("action.hanTextMessage.submit")] = function() {

					var action 			= "sendHANTextMessage";
					var hanTextMenssage = $('#textMessageHan').val();
					var hanDuration 	= parseInt($('#textMessageDuration').val());
					var hanMessageDate  = $('#hanMessageDate').val();
					var hanMessageTime  = $('#hanMessageTime').val().toLowerCase();
					var bDoIt 			= true;
					var oActionDialog 	= $("#action-dialog");
					var oHanForm 		= $("#createTextHanForm");
					var sMessagindId;

					var fnCheckMessage = function() {

						var oTextMessageDuration = $('#textMessageDuration');
						var sTextMessageDuration = $(oTextMessageDuration).val();

						if ((sTextMessageDuration == '0') || (sTextMessageDuration.search(/^\d+$/) == -1 )) {
							oTextMessageDuration.validationEngine('showPrompt', sensus.locale.get('commons.pages.invalidValue'), 'red', '', true);
							return true;
						}
					}

					sensus.widgets.datatable.clearSelects();

					if ((oHanForm.validationEngine('validateField', '#hanMessageDate'))
							||(oHanForm.validationEngine('validateField', '#hanMessageTime'))
							||(oHanForm.validationEngine('validateField', '#textMessageHan'))
							||(oHanForm.validationEngine('validateField', '#textMessageDuration'))
							|| fnCheckMessage()) {

						bDoIt = false;

					}

					if (bDoIt) {

						var dateFormat 		= sensus.settings.dateFormatMask.replace('yyyy','yy');
						var dateTextMessage	= $.date.parseDate(hanMessageDate + ' ' + hanMessageTime, dateFormat);
						var iTimeZoneValue 	= null;
						var dDateServer;

						if (bDetail) {

							var oTimeZone = sensus.pages.device.module.util.getTimeZoneMinutes(
									sensus.pages.device.module.request.get("device") || {});

							// Get the time zone of device
							if (oTimeZone.iTZMinutes) {
								iTimeZoneValue = parseInt(oTimeZone.iTZMinutes, 10) * (-1);
							}

							// Date Server in UTC
							dDateServer = $.date.setDateServer(true);

							// Apply device time zone in date server
							dDateServer = $.date.dateFormat(dDateServer, dateFormat + " h:ia", oTimeZone);
							dDateServer = $.date.parseDate(dDateServer, dateFormat);

						} else {

							// Date Server with system time zone
							dDateServer = $.date.setDateServer();

							// Get the time zone of system
							iTimeZoneValue = parseInt(sensus.settings.timezoneMinutes, 10) * (-1);

						}

						// Remove time zone passing the date to UTC
						var dDateInUTC = $.date.fnGetDate(dateTextMessage, {iTZMinutes : iTimeZoneValue});

						// Date format for pass to controller
						dDateInUTC = $.date.dateFormat(dDateInUTC, dateFormat + " h:ia", {bUserTZ : false});

						if ($.date.setCompareToDate(dateTextMessage, dDateServer)) {

							$('#hanMessageDate').validationEngine('showPrompt', sensus.locale.get('commons.pages.futureDate'), 'red', '', true);

						} else {

							var sendHanTextMessageAction = new SendHanTextMessageAction({
								textMessage 		   : hanTextMenssage,
								durationHANTextMessage : hanDuration,
								actionType			   : {id: parseInt(iActionId,10)},
								actionTypeName		   : sActionName,
								dateTimeString		   : dDateInUTC,
								onDemand			   : true,
								isMonitored			   : true,
							});

							sensus.util.actionrequestutil.fnActionCall('api/deviceop/apply', bDetail, sActionName, iActionId, "commons.pages.sendHanTextSucess", filterSearch, sendHanTextMessageAction);
							sensus.widgets.datatable.clearSelects();
							oActionDialog.dialog('close');

						}

					} else {

						sensus.util.page.stopProgressBar();
					}
				};

				objButtons[sensus.locale.get("action.removefromgroup.cancel")] = function() {

					$(this).dialog('close');
					$('.formError').remove();
				};

				return objButtons;
			})(),

			action : function(actionDialog) {

				var oActionDialog = $('#action-dialog');

				oActionDialog.load("action/hanMessage", function() {

					var dateToday 			= $.date.setDateServer(true);
					var dateFormat 			= sensus.settings.dateFormatMask.replace('yyyy','yy');
					var oHanMessageDate 	= $("#hanMessageDate");
					var oHanMessageTime 	= $("#hanMessageTime");
					var oSmartPointCount 	= $(".smartpoint-count");
					var sClassTypeFormat 	= '';
					var sFormatTime 		= "h:ia";
					var oTimeZoneElement 	= $(".timezone");
					var sTimeZoneLabel		= sensus.locale.get('smartpointdetail.tabs.about.timeZone');

					if (dateFormat == 'mm/dd/yy') {
						sClassTypeFormat = 'dateEn';
					} else {
						sClassTypeFormat = 'datePt';
					}

					if (bDetail) {

						var oTimeZone = sensus.pages.device.module.util.getTimeZoneMinutes(
							    sensus.pages.device.module.request.get("device") || {});

						// Display the timezone of device
						oTimeZoneElement.text(sTimeZoneLabel + ' (' + oTimeZone.sTZName + ') ');

						oHanMessageDate.val($.date.dateFormat(dateToday, dateFormat, oTimeZone));
						oHanMessageTime.val($.date.dateFormat(dateToday, sFormatTime, oTimeZone));

					} else {

						oTimeZoneElement.text(sTimeZoneLabel + ' (' + sensus.settings.timezone + ') ');

						oHanMessageDate.val($.date.dateFormat(dateToday));
						oHanMessageTime.val($.date.dateFormat(dateToday, sFormatTime));

					}

					oActionDialog.addClass('send-han-message');
					actionDialog.removeClass('waiting');

					$('#group_add_select').combobox( {
						zIndex : 3999
					});

					$('.time').calendricalTime();

					oHanMessageDate.datepicker({
						dateFormat: dateFormat,
						minDate: "0D"
					});

					$("#group_add_select_input").attr("value", sensus.locale.get("widgets.combobox.prompt2"));

					$(".two-line").delegate("#group_add_select_input", "autocompleteclose", function(e) {
						$(this).blur();
					});

					oHanMessageDate.removeClass();
					oHanMessageDate.addClass("datepicker short hasDatepicker validate[required, custom["+sClassTypeFormat+"]]");

					oSmartPointCount.html($('#checked-count').html());

					oHanMessageDate.datepicker({ minDate: "-1M", maxDate: "+0D" });
					oHanMessageTime.addClass("validate[required, custom[timeFormat]]");
				});

				actionDialog.addClass('waiting');
				actionDialog.dialog('open');
			},

			close : function(actionDialog) {
				$('.formError').remove();
			}
		}
	},

	/** Configuration for the "SetupDemandResponse" dialog. */
	SetupDemandResponse : function(bIsDetail, sActionName, iActionId, filterSearch) {

		return {

			/** The dialog title. */
			title : sensus.locale.get("action.hanDemandResponseSetup.title"),

			/** Whether this dialog requires a smartpoint list. */
			requiresSmartpoints : true,

			width : 960,

			/** The dialog buttons (submit and cancel). */
			close : function(actionDialog) {
				$('.ui-dialog').find('.ui-dialog-titlebar-close').show();
			},

			buttons : (function() {

				var objButtons = {};

				objButtons[sensus.locale.get("action.hanDemandResponseSetup.submit")] = function() {

					var oCreateSetupDemandResponseForm = $("#createSetupDemandResponseForm");
					var bDoIt 			= true;
					var $table 			=  $("#relay-table");
					var sMessageSuccess = "commons.pages.SetupDemandResponseTextSucess";
					if (bIsDetail && ($.address.parameter("typeEnum") == "FLEXNET_LCM")) {

						$table.find("tr").each(function() {

							var $this 	= $(this);
							var $code 	= $this.find("#enrollmentCode");
							var $start 	= $this.find("#randomizeStart");
							var $end  	= $this.find("#randomizeEnd");

						    if ($code.validationEngine("validateField", $code)
						    		|| $start.validationEngine("validateField", $start)
						    		|| $end.validationEngine("validateField", $end)) {

						    	bDoIt = false;
						        return false;

						    }

						});

						if (bDoIt) {

							var aLcmRelaysList 	= [];

							// Fill the lcm relay list
							var fnFillLCMRelayObject = function($element) {

								var iCode 	= parseInt($element.find("#enrollmentCode").val(), 10) || 0;
								var iStart 	= parseInt($element.find("#randomizeStart").val(), 10) || 0;
								var iEnd 	= parseInt($element.find("#randomizeEnd").val(), 10) || 0;
								var sClass 	= $element.find("input[type='checkbox']:checked").val();
								var iRelay 	= parseInt($element.attr("name"), 10) || null;

								if (iCode || iStart || iEnd || sClass) {

									var oLCMRelay = new LCMRelay({
										relay 			: iRelay,
										enrollmentCode	: iCode,
										deviceClass 	: sClass,
										startMinutes 	: iStart,
										endMinutes 		: iEnd
									});

									aLcmRelaysList.push(oLCMRelay);
								}

							};

							$table.find("tr").each(function() {

								// Call function to fill the relay list passing the inputs
								fnFillLCMRelayObject($(this));

							});

							if (aLcmRelaysList.length) {

								var oParameters = {
									actionTypeName	: sActionName,
									onDemand		: true,
									isMonitored		: true,
									lcmRelaysList 	: aLcmRelaysList
								};

								sensus.util.actionrequestutil.fnActionCall('api/deviceop/apply', bIsDetail, sActionName, iActionId, sMessageSuccess, filterSearch, oParameters);

								$(this).dialog('close');

							} else {

								$("#relay-table").validationEngine('showPrompt', sensus.locale.get("commons.pages.setupDemandResponse.validationDialog"), 'red', 'topLeft', true);
								return false;

							}
						}

					} else {

						sensus.widgets.datatable.clearSelects();

						if (bDoIt) {

							var enrollmentCode 					= "";
							var oCreateSetupDemandResponseForm 	= $("#createSetupDemandResponseForm");
							var bDoIt 							= true;
							var startMinutes 					= "";
							var endMinutes   					= "";

							if((!$.ajaxValidator.fnIsNullOrUndefined($("#enrollmentCode").val()))&&($("#enrollmentCode").val() != "")){
								enrollmentCode 					= $("#enrollmentCode").val();
							}else{
								enrollmentCode 					= "0";
							}
							if((!$.ajaxValidator.fnIsNullOrUndefined($("#randomizeStart").val()))&&($("#randomizeStart").val() != "")){
								startMinutes 				    =	parseInt($("#randomizeStart").val(),10);
							}else{
								startMinutes				    = 0;
							}
							if((!$.ajaxValidator.fnIsNullOrUndefined($("#randomizeEnd").val()))&&($("#randomizeEnd").val() != "")){
								endMinutes		                = parseInt($("#randomizeEnd").val(),10);
							}else{
								endMinutes                      = 0;
							}

							if(($("#randomizeEnd").val() == "")&&($("#enrollmentCode").val() == "")&&($("#randomizeStart").val()== "")){
								bDoIt = false;
							}

							var setupDemandResponse = new SetupDemandResponse({
								enrollmentCode 	   : enrollmentCode,
								startMinutes 	   : startMinutes,
								endMinutes		   : endMinutes,
								actionType		   : {id : parseInt(iActionId,10) || 0},
								actionTypeName	   : sActionName,
								actionTime		   : new Date(),
								onDemand		   : true,
								isMonitored		   : true,
							});

							if ((oCreateSetupDemandResponseForm.validationEngine('validateField', '#enrollmentCode'))
								|| (oCreateSetupDemandResponseForm.validationEngine('validateField', '#randomizeStart'))
								|| (oCreateSetupDemandResponseForm.validationEngine('validateField', '#randomizeEnd')))	{

								bDoIt = false;
								return false;

							}

							if(bDoIt){
								sensus.util.actionrequestutil.fnActionCall('api/deviceop/apply', bIsDetail, sActionName, iActionId, sMessageSuccess, filterSearch, setupDemandResponse);

							} else {

								$(".demand-response-fields").validationEngine('showPrompt', sensus.locale.get("devicedetail.dialog.setTamper.messageValidation"), 'red', 'topLeft', true);
								return false;

							}

							if ($.address.parameter('id') === undefined) {
								sensus.widgets.datatable.reloadTable(sensus.pages.device.smartpointTable);
							}

							$(this).dialog('close');
						}
					}
				};

				objButtons[sensus.locale.get("action.removefromgroup.cancel")] = function() {

					$(this).dialog('close');
					$('.formError').remove();

				};

				return objButtons;
			})(),

			action : function(actionDialog) {

				var $actionDialog 	= $('#action-dialog');
				var $uiDialog 		= $('.ui-dialog');
				var sWaiting 		= 'waiting';

				// When is the detail page, load the setup demand response specific for FLEXNET_LCM
				if (bIsDetail && ($.address.parameter("typeEnum") == "FLEXNET_LCM")) {

					$actionDialog.load("action/flexnetLcmSetupDemandResponse", function() {

						var $relayTable = $("#relay-table");

						$("#createSetupDemandResponseForm").validationEngine('attach');

						actionDialog.removeClass(sWaiting);

						$("#flexnetLcmLegend").text(sensus.locale.get("smartpointdetail.tabs.about.demandResponseFlexnetLcmSetup", $.address.parameter("id")));

						$relayTable.find("tr").each(function(i, e)  {

							$(e).find("strong").text("Relay "+ (i+1));
							$(e).attr("id", "relay"+ (i+1));
							$(e).attr("name", i+1);

						});

						$(".spanToggle").click(function(e) {

							e.preventDefault();

							var divToggle 	= $(this).parent().parent().find(".toggleClass");
							var $span 		= $("span", this);

							divToggle.toggle("blind", null, 500);

							if ($span.hasClass("ui-icon-triangle-1-e")) {
								$span.switchClass("ui-icon-triangle-1-e", "ui-icon-triangle-1-s", 500);
							} else {
								$span.switchClass("ui-icon-triangle-1-s", "ui-icon-triangle-1-e", 500);
							}
						});

						$relayTable.find("input:checkbox").change( function() {

							var sInput 	= "input";
							var $this 	= $(this);

							$this.parent().siblings().find(sInput).removeAttr("checked");
							$this.parents("div:first").siblings().find(sInput).removeAttr("checked");
						});
					});

				} else {

					$actionDialog.load("action/setupDemandResponse", function() {

						actionDialog.removeClass(sWaiting);

					});
				}

				actionDialog.addClass(sWaiting);
				actionDialog.dialog('open');
			}
		}
	},

	addToGroup : function(bIsDetail, sActionName, iActionId, filterSearch) {

		return {

			// The dialog title.
			title : sensus.locale.get("action.addtogroup.title"),

			// Whether this dialog requires a smartpoint list.
			requiresSmartpoints : true,

			// The dialog buttons (submit and cancel).
			close : function(actionDialog) {
				$('.ui-dialog').find('.ui-dialog-titlebar-close').show();
			},

			buttons : (function() {

				var objButtons = {};

				objButtons[sensus.locale.get("action.addtogroup.submit")] = function() {

						("#group_add_select").combobox;

					var sGroupName = $("#group_add_select_input").val();
					var iGroupId = 0;
					var bHasGroup = false;
					var sDeviceType = $.address.parameter("device_type").replace("|","");

					$('#group_add_select option').each(function() {

						if ($(this).text() == sGroupName) {

							iGroupId = $(this).attr("value");
							bHasGroup = true;
							return false;

						} else {

							bHasGroup = false;
							iGroupId = '';

						}
					});


					sensus.widgets.datatable.clearSelects();

					$('#group_add_select_input, .ui-autocomplete').click(function() {
						$('.formError').remove();
					});

					if (!bHasGroup) {

						$("#add-group-form .ui-autocomplete-input").validationEngine('showPrompt', sensus.locale.get("commons.pages.selectGroup"), 'red', '', true);

					} else {

						var sNoGroup = sGroupName.length == 0 || sGroupName == sensus.locale.get("widgets.combobox.prompt2");

						if (!sNoGroup && bHasGroup) {

							var _paramRequest = sensus.util.filter.paramRequest;
							var _electric = sensus.constants.services.electric;
							var sDeviceType = _paramRequest.getParameter("device_type");
							var sDeviceSubType = _paramRequest.getParameter("device_subtype");

							var oGroup = {id: iGroupId, deviceType: sDeviceType};
							var oRequest = {deviceType : sDeviceType, endRow : 0, startRow : 0, sortExpressions : [{field : "name", direction : "Ascending"}], groups : [oGroup]};
							var oDeviceSearch = {};
							var oSearch = {};

							if (sensus.settings.serviceType == _electric.name) {

								oSearch.deviceTypes = [sDeviceType];

							    if (sDeviceType && sDeviceType == _electric.han.name) {

							    	oGroup.hanDeviceType = sDeviceSubType;

									oDeviceSearch.hanDeviceTypeEnumList = [sDeviceSubType];

									oSearch.hanDeviceSearch = oDeviceSearch;
							    }

								oRequest.deviceSearch = oSearch;
							}

							$('.formError').remove();

							$("#action-dialog").dialog('close');

							sensus.util.page.startProgressBar("widgets.progress.addToGroup");

							function fnCallback (response) {

								$('.select-none').click();
								$("#action-dialog").dialog('close');

								sensus.util.page.showMessage("messaging-main", sensus.locale.get("smartpoint.actions.addgroup.success",sGroupName, "1"), "confirm");
								sensus.widgets.datatable.clearSelects();

							};

							// Add Default Properties.
							sensus.util.actionrequestutil.fnAddDefaultParamToActionRequest(oRequest, filterSearch().deviceSearch, bIsDetail, false);

							$.ajaxValidator.fnDoCall("api/group/insertDevice", oRequest, false, fnCallback);

						} else {

							if (sNoGroup) {

								$("#add-group-form .ui-autocomplete-input").validationEngine('showPrompt', sensus.locale.get("commons.pages.selectGroup"), 'red', '', true);

							} else if (!sensus.widgets.datatable.isAllRows || aIds.length < 0) {

								sensus.util.page.showMessage("messaging-main", sensus.locale.get("commons.pages.nosmartpoint"), "error");
							};

							return false;
						}

						sensus.widgets.datatable.clearSelects();
					};
				}

				objButtons[sensus.locale.get("action.addtogroup.cancel")] = function() {

					$(this).dialog('close');
					$('.formError').remove();
				};

				return objButtons;
			})(),

			action : function (actionDialog) {

				$('#action-dialog').load("action/addGroup", function() {

					var addGroupForm = $('#add-group-form .add-group-control');
					var groupAddSelectInput = $('#group_add_select_input');

					var _paramRequest = sensus.util.filter.paramRequest;

					var sDeviceType = _paramRequest.getParameter("device_type");
					var sDeviceSubType = _paramRequest.getParameter("device_subtype");

					var oInquiryGroupRequest = new InquiryGroupRequest({endRow : 0, startRow : 0, sortExpressions : [{field : "name", direction : "Ascending"}]});
					var oDeviceSearch = {};
					var oSearch = {
							deviceTypes : [sDeviceType]
					};

					if (sDeviceType && sDeviceType == sensus.constants.services.electric.han.name) {

						oDeviceSearch.hanDeviceTypeEnumList = [sDeviceSubType];

						oSearch.hanDeviceSearch = oDeviceSearch;
					}

					oInquiryGroupRequest.deviceSearch = oSearch;

					$.ajaxValidator.fnDoCall("api/group/fetchAll", oInquiryGroupRequest, false, function(oResponse) {

						sensus.util.page.createOptions($('#group_add_select'), oResponse.groups, "combo", false);

						actionDialog.removeClass('waiting');
					});

					$('.ui-dialog-buttonset').delegate('.ui-button', 'click', function () {

						if (groupAddSelectInput.val() != sensus.locale.get('widgets.combobox.prompt2')
								&& groupAddSelectInput.val() != sensus.locale.get('action.addgroup.label.addnewgroup')) {

							addGroupForm.hide();
						}
					});

					$('#action-dialog a').click(function() {

						if (addGroupForm.attr('class') == 'add-group-control s') {

							addGroupForm.hide();
							groupAddSelectInput.val(sensus.locale.get('widgets.combobox.prompt'));

							return false;

						} else {

							addGroupForm.show();
							groupAddSelectInput.val(sensus.locale.get('action.addgroup.label.addnewgroup'));
							$('#group_add_select option[value="0"]').text(sensus.locale.get('action.addgroup.label.addnewgroup'));

							return false;
						}
					});

					actionDialog.removeClass('waiting');

					$('#group_add_select').combobox( {
						zIndex : 3999
					});

					$("fieldset.two-line").delegate('#group_add_select_input', "autocompleteclose", function(event, ui) {
						$(this).blur();
					});

					groupAddSelectInput.attr("value", sensus.locale.get("widgets.combobox.prompt2"));
					$('.smartpoint-count').html($('.checked-count').html());
				});

				$('#action-dialog').keypress(function(e) {

					if (e.keyCode == $.ui.keyCode.ENTER) {

						return false;
					}
				});

				actionDialog.addClass('waiting');
				actionDialog.dialog('open');
				$('.ui-corner-all').die();
			}
		}
	},

	deleteHanDevices : function(bDetail, sActionName, iActionId, filterSearch) {

		return {

			// The dialog title.
			title : sensus.locale.get("commons.pages.confirmDelete"),

			// The dialog width.
			width : 300,

			// The dialog buttons (submit and cancel).
			buttons : (function() {

				var buttons = {};

				buttons[sensus.locale.get("commons.pages.delete")] = function() {

					sensus.util.page.startProgressBar();

					var actionDialogId = $("#action-dialog");

					var fnCallback = function (data) {

						var sMessagindId = "messaging-smartpoint-detail";
						var sMessage 	 = "";

						switch ($.address.parameter('deviceType')) {

							case "HAN_DEVICE" :
								sMessage = sensus.locale.get("smartpointdetail.page.deleteHanSucessfully", sensus.locale.get('smartpoint.page.tab.home_area_network'),$.address.parameter('id'))
								break;
							case "LCM" :
								sMessage = sensus.locale.get("smartpointdetail.page.deleteHanSucessfully", sensus.locale.get('smartpoint.page.tab.sensus_load_control'),$.address.parameter('id'))
								break;
							default:
								sMessage = sensus.locale.get("smartpointdetail.page.deleteHanSucessfully", sensus.locale.get('commons.pages.ELECTRIC_METER'),$.address.parameter('id'))
						}

						$.fn.pageLoader.load("electriclist?deviceType=" + $.address.parameter("deviceType") + "?length=" + $.address.parameter("length") + "?iDisplayStart=" + $.address.parameter("iDisplayStart"), $("#load"));
					}

					sensus.util.actionrequestutil.fnActionCall('api/deviceop/apply', bDetail, sActionName, iActionId, null, filterSearch, null, fnCallback);

					$(this).dialog('close');

					actionDialogId.dialog('close');
				};

				buttons[sensus.locale.get("commons.pages.cancel")] = function() {
					var actionDialogId = $("#action-dialog");
					actionDialogId.dialog('close');
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

				var actionDialogId = $("#action-dialog");
				var sDeviceId 	   = $('.detail-header h1').contents(':not(strong)').text().trim();

				var fnCallback = function (data) {

					var iProcessCount = 0;

					if (!$.ajaxValidator.fnIsNullOrUndefined(data.processItems)) {
						iProcessCount = data.processItems.length;
					}

					if (data.processItems.length > 0) {
						actionDialogId.text(sensus.locale.get("smartpointdetail.dialog.deleteDevice.connectVerify", sDeviceId, sDeviceId));
					} else if ($('#network-status-value').text() === "Connected") {
						actionDialogId.text(sensus.locale.get("smartpointdetail.dialog.deleteDevice", $.address.parameter('deviceType')));
					} else {
						actionDialogId.text(sensus.locale.get("smartpointdetail.dialog.deleteDevice.disconnected", sDeviceId));
					}
				}

				sensus.util.actionrequestutil.fnActionCall('api/deviceop/checkRunningProcess', bDetail, null, null, false, null, null, fnCallback);
				$('#messaging-smartpoint-detail').hide();
				actionDialogId.dialog('open');
			}
		}
	},

	// Configuration for the "removeFromGroup" dialog.
	removeFromGroup : function(bDetail, sActionName, iActionId, filterSearch) {

		return {

			// The dialog title.
			title : sensus.locale.get("action.removefromgroup.title"),

			// Whether this dialog requires a smartpoint list.
			requiresSmartpoints : true,

			// The dialog buttons (submit and cancel).
			close : function(actionDialog) {
				$('.ui-dialog').find('.ui-dialog-titlebar-close').show();
			},

			buttons : (function() {

				var objButtons = {};

				objButtons[sensus.locale.get("action.removefromgroup.submit")] = function() {

					("#group_add_select").combobox;
					var sGroupName = $("#group_add_select_input").val();
					var iGroupId = '';
					var bHasGroup = false;
					var sDeviceType = $.address.parameter("device_type").replace("|","");

					$('#group_add_select option').each(function() {

						if ($(this).text() == sGroupName) {

							iGroupId = $(this).attr("value");
							bHasGroup = true;
							return false;

						} else {

							bHasGroup = false;
							iGroupId = '';

						}
					});

					sensus.widgets.datatable.clearSelects();

					$('#group_add_select_input, .ui-autocomplete').click(function() {

						$('.formError').remove();

					});

					if (!bHasGroup) {

						$("#add-group-form .ui-autocomplete-input").validationEngine('showPrompt', sensus.locale.get("commons.pages.selectGroup"), 'red', '', true);

					} else {

						var sNoGroup = sGroupName.length == 0 || sGroupName == sensus.locale.get("widgets.combobox.prompt2");

						if (!sNoGroup) {

							$('.formError').remove();

							sensus.util.page.startProgressBar("widgets.progress.removeFromGroup");

							var _paramRequest = sensus.util.filter.paramRequest;
							var _electric = sensus.constants.services.electric;
							var sDeviceType = _paramRequest.getParameter("device_type");
							var sDeviceSubType = _paramRequest.getParameter("device_subtype");

							var oGroup = {id: iGroupId, name: sGroupName, deviceType: sDeviceType};
							var oRequest = {deviceType : sDeviceType, endRow : 0, startRow : 0, sortExpressions : [{field : "name", direction : "Ascending"}], groups : [oGroup]};
							var oDeviceSearch = {};
							var oSearch = {};

							if (sensus.settings.serviceType == _electric.name) {

								oSearch.deviceTypes = [sDeviceType];

							    if (sDeviceType && sDeviceType == _electric.han.name) {

							    	oGroup.hanDeviceType = sDeviceSubType;

									oDeviceSearch.hanDeviceTypeEnumList = [sDeviceSubType];

									oSearch.hanDeviceSearch = oDeviceSearch;
							    }

								oRequest.deviceSearch = oSearch;
							}

							// Add Default Properties.
							sensus.util.actionrequestutil.fnAddDefaultParamToActionRequest(oRequest, filterSearch().deviceSearch, bDetail, false);

							function fnCallback (response) {

								$('.select-none').click();
								$("#action-dialog").dialog('close');

								sensus.util.page.showMessage("messaging-main", sensus.locale.get("commons.pages.remove.device.group.success"), "confirm");
								sensus.widgets.datatable.clearSelects();

							};

							$.ajaxValidator.fnDoCall("api/group/deleteDevice", oRequest, false, fnCallback);

							$("#action-dialog").dialog('close');

						} else {

							if (sNoGroup) {
								$("#add-group-form .ui-autocomplete-input").validationEngine('showPrompt', sensus.locale.get("commons.pages.selectGroup"), 'red', '', true);
							} else if (!sensus.widgets.datatable.isAllRows || aIds.length < 0) {
								sensus.util.page.showMessage("action-messaging", sensus.locale.get("validation.removefromgroup.nosmartpoint"), "error");

							};

							sensus.widgets.datatable.clearSelects();

							return false;
						};

						sensus.widgets.datatable.reloadTable(sensus.pages.device.smartpointTable);

					}

				};
				objButtons[sensus.locale.get("action.removefromgroup.cancel")] = function() {

					$(this).dialog('close');
					$('.formError').remove();
				};

				return objButtons;
			})(),

			action : function(actionDialog) {

				$('#action-dialog').load('action/removeGroup', function() {

					var _paramRequest = sensus.util.filter.paramRequest;

					var sDeviceType = _paramRequest.getParameter("device_type");
					var sDeviceSubType = _paramRequest.getParameter("device_subtype");

					var oInquiryGroupRequest = new InquiryGroupRequest({endRow : 0, startRow : 0, sortExpressions : [{field : "name", direction : "Ascending"}]});
					var oDeviceSearch = {};
					var oSearch = {
							deviceTypes : [sDeviceType]
					};

					if (sDeviceType && sDeviceType == sensus.constants.services.electric.han.name) {

						oDeviceSearch.hanDeviceTypeEnumList = [sDeviceSubType];

						oSearch.hanDeviceSearch = oDeviceSearch;
					}

					oInquiryGroupRequest.deviceSearch = oSearch;

					$.ajaxValidator.fnDoCall("api/group/fetchAll", oInquiryGroupRequest, false, function(oResponse) {

						sensus.util.page.createOptions($('#group_add_select'), oResponse.groups, "combo", false);

						actionDialog.removeClass('waiting');
					});

					actionDialog.removeClass('waiting');

					$('#group_add_select').combobox({
						zIndex : 3999
					});

					$("#group_add_select_input").attr("value", sensus.locale.get("widgets.combobox.prompt2"));

					$(".two-line").delegate("#group_add_select_input", "autocompleteclose", function(e) {
						$(this).blur();
					});

					$('.smartpoint-count').html($('#checked-count').html());
				});

				$('#action-dialog').keypress(function(e) {

					if (e.keyCode == $.ui.keyCode.ENTER) {

						return false;
					}
				});

				actionDialog.addClass('waiting');
				actionDialog.dialog('open');
			}
		}
	},

	/** Configuration for the "addTag" dialog. */
	addTag : function(bDetail, sActionName, iActionId, filterSearch) {

		return {

			// The dialog title.
			title : sensus.locale.get("action.addtag.title"),

			// Whether this dialog requires a smartpoint list.
			requiresSmartpoints : true,

			// The dialog buttons (submit and cancel).
			close : function(actionDialog) {
				$('.ui-dialog').find('.ui-dialog-titlebar-close').show();
			},

			buttons : (function() {

				var objButtons = {};

				objButtons[sensus.locale.get("action.addtag.submit")] = function() {

					var sTagName 	= $("#tag_add_select_input").val();
					var	iTagId 		= '';
					var	isAllRows 	= sensus.widgets.datatable.isAllRows;
					var	data 		= '';
					var	bHasTag 	= false;
					var	aIds 		= [];
					var	sIdUrl 		= '';
					var $dialog 	= $("#action-dialog");
					var $tagSelect 	= $('#tag_add_select_input');
					var iTagName 	= sTagName.length;

					// Validate if is empty
					if (!iTagName) {
						// Error : Not Tag name informed
						$("#add_tag_form .ui-autocomplete-input").validationEngine('showPrompt', sensus.locale.get("validation.addtag.notag"), 'red', '', true);
						return false;
					}

					// Validate if is more than 100 characters
					if (iTagName > 100) {
						$tagSelect.validationEngine('showPrompt', sensus.locale.get('commons.pages.max.characterJs', "100"), 'red', '', true);
						return false;
					}

					// Validate invalid
					if ((sTagName.match(/^(?:(?:[a-z0-9#]*(?:(?:(?:\s?-\s?|'s\s?|[(?:s)]'\s?|\s)[a-z0-9#]*)?))\s?)*$/i) == null) || (sTagName.replace(/\s/g,'').length == 0)) {

						$tagSelect.validationEngine('showPrompt', sensus.locale.get('commons.pages.specialCharacterInvalid'), 'red', '', true);
						return false;
					}

					// Check if Tag exist
					$('#tag_add_select option').each(function() {

						if ($(this).text() == sTagName) {
							iTagId = $(this).attr("value");
							bHasTag = true;
							return false;
						} else {
							bHasTag = false;
						}
					});

					sensus.util.page.startProgressBar("widgets.progress.addToTag");

					sensus.commons.modules.dialogSettings.addTagToDevice(sTagName, iTagId, filterSearch, bDetail);

					sensus.widgets.datatable.clearSelects();
					$dialog.dialog('close');

					sensus.util.page.stopProgressBar();
				}

				objButtons[sensus.locale.get("action.addtag.cancel")] = function() {
					$(this).dialog('close');
					$('.formError').remove();
				};

				return objButtons;

			})(),

			action : function(actionDialog) {


				var $dialog = $('#action-dialog');

				$dialog.load("action/addTag", function() {

					$.ajaxValidator.fnDoCall("api/tag/fetchAll",
							new InquiryTagRequest({endRow : 0, startRow : 0, sortExpressions : [{field : "name", direction : "Ascending"}]}),
							false, function(oResponse) {

								sensus.util.page.createOptions($('#tag_add_select'), oResponse.tags, "combo", false);

								actionDialog.removeClass('waiting');
							});

					$('.highlight', '.two-line').html(sensus.locale.get("action.addtag.label.smartpoints", $('#checked-count').html()));
				});

				$dialog.keypress(function(e) {

					if (e.keyCode == $.ui.keyCode.ENTER) {

						return false;
					}
				});

				actionDialog.addClass('waiting');
				actionDialog.dialog('open');
			}
		}
	},


	/*
	 * Function: addTagToDevice
	 * Purpose:  To add tag int the smartPoint(s)
	 * Returns:  string : Tag Name
	 *			 int : Tag Id
	 *			 data: smartpoints id
	 * Inputs:   -
	 */
	addTagToDevice : function(sTagName, iTagId, filterSearch, bDetail) {

		var inquiryDeviceRequest = new InquiryDeviceRequest ({
			tags : [{id: iTagId, name:sTagName}]
		});

		function fnCallback (response) {

			sensus.util.page.showMessage("messaging-main", sensus.locale.get("smartpoint.actions.addtag.success", sTagName, $('#checked-count').text(), "1"), "confirm");
			$('.select-none').click();
		};

		// Add Default Properties.
		sensus.util.actionrequestutil.fnAddDefaultParamToActionRequest(inquiryDeviceRequest, filterSearch().deviceSearch, bDetail, false);

		$.ajaxValidator.fnDoCall("api/tag/insertDevice", inquiryDeviceRequest, false, fnCallback);
	},

	/*
	 * Function: createNewTag
	 * Purpose:  To Create a new tag
	 * Returns:  string : new Tag Name to be create
	 * Inputs:   int : id of the new tag
	 */
	createNewTag : function(newTagName) {

		var iTagId = null;
		var fnCallback = function(data) {

			if (data.operationSuccess) {

				iTagId = data.tags[0].id;
				sensus.util.page.showMessage("messaging-main", sensus.locale.get("tag.page.createdSuccess", newTagName), "confirm");

				// limit the size name
				var sLabel = newTagName;

				if (sLabel.length > 15) {

					sLabel = sLabel.substr(0,15) + '...';
				}


				// Append new tag in the filter Or in the combobox
				if ($(".ui-listcombobox li", "#tag").length < 6) {

					var newTagInFilter = '<li class="checkbox" title="' + newTagName + '"><input type="checkbox" value="' + iTagId + '"> ' + sLabel + '</li>';
					$(newTagInFilter).appendTo("#tag .ui-listcombobox");

				} else {

					var newOptionInTagSelect = '<option value="' + iTagId + '">' + newTagName + '</option>';
					var oNewSelect = $('<select class="listcombobox" id="combobox"></select>');

					if ($("#combobox", "#tag").length) {

						var nComboLength = $("#combobox option", "#tag").length;

						if (nComboLength) {

							$(newOptionInTagSelect).appendTo("#tag .listcombobox");

							$('#combobox_input', '#tag').val((parseInt(nComboLength)+1)+' more...');

						} else {

							$('#combobox', "#tag").remove();

							$(oNewSelect).appendTo("#tag .checkBoxUl");

							$(newOptionInTagSelect).appendTo(oNewSelect);

							$('#combobox', "#tag").combobox();
						}

					} else {

						$('#combobox', "#tag").remove();

						$(oNewSelect).appendTo("#tag .checkBoxUl");

						$(newOptionInTagSelect).appendTo(oNewSelect);

						$('#combobox', "#tag").combobox();
					}
				}

			} else {

				sensus.util.page.showMessage("messaging-main", sensus.locale.get("commons.pages.errorPage"), "error");
			}
		};

		$.ajaxValidator.fnDoCall("api/tag/insert", {"name" : newTagName}, false, fnCallback);

		return iTagId;
	},

	// Configuration for the "removeTag" dialog.
	removeTag : function(bDetail, sActionName, iActionId, filterSearch) {

		return {

			// The dialog title.
			title : sensus.locale.get("action.removetag.title"),

			// Whether this dialog requires a smartpoint list.
			requiresSmartpoints : true,

			close : function(actionDialog) {
				$('.ui-dialog').find('.ui-dialog-titlebar-close').show();
			},

			// The dialog buttons (submit and cancel)
			buttons : (function() {

				var objButtons = {};

				objButtons[sensus.locale.get("action.removetag.submit")] = function() {

					var sTagName = $("#tag_remove_select_input").val();
					var data = '';
					var iTagId = 0;

					$('#tag_remove_select option').each(function() {

						if ($(this).text() == sTagName) {

							iTagId = $(this).attr("value");

						}
					});

					sensus.widgets.datatable.clearSelects();

					var bHasTag = false;

					$('#tag_remove_select option').each(function() {

						if ($(this).text() == sTagName) {
							bHasTag = true;
						}
					});

					$('#tag_remove_select_input, .ui-autocomplete').click(function() {
						$('.formError').remove();
					});

					if (!bHasTag ) {
						$("#remove_tag_form .ui-autocomplete-input").validationEngine('showPrompt', sensus.locale.get("validation.addtag.notag"), 'red', '', true);
					}

					var sNoTag = sTagName.length == 0 || sTagName == sensus.locale.get("widgets.combobox.prompt2");

					if (!sNoTag && bHasTag) {

						$('.formError').remove();

						// Start progress bar
						sensus.util.page.startProgressBar("widgets.progress.removeTag");

						var inquiryDeviceRequest = new InquiryDeviceRequest ({
							tags : [
								{id: iTagId, name: sTagName}
							],
						});

						function fnCallback (response) {
							sensus.util.page.showMessage("messaging-main", sensus.locale.get("smartpoint.actions.removetag.success", sTagName, $('#checked-count').text(), "1"), "confirm");
						};

						// Add Default Properties.
						sensus.util.actionrequestutil.fnAddDefaultParamToActionRequest(inquiryDeviceRequest, filterSearch().deviceSearch, bDetail, false);

						$.ajaxValidator.fnDoCall("api/tag/deleteDevice", inquiryDeviceRequest, false, fnCallback);

						$("#action-dialog").dialog('close');

					} else {

						if (sNoTag) {
							$("#remove_tag_form .ui-autocomplete-input").validationEngine('showPrompt', sensus.locale.get("validation.removetag.notag"), 'red', '', true);
						} else if (!sensus.widgets.datatable.isAllRows || aIds.length < 0) {
							$("#remove_tag_form .ui-autocomplete-input").validationEngine('showPrompt', sensus.locale.get("commons.pages.noTagRemove"), 'red', '', true);
						}

						sensus.widgets.datatable.clearSelects();
						return false;
					};

					sensus.widgets.datatable.reloadTable(sensus.pages.device.smartpointTable);

				};

				objButtons[sensus.locale.get("action.removetag.cancel")] = function() {

					$(this).dialog('close');
					$('.formError').remove();

				};

				return objButtons;
			})(),

			action : function(actionDialog) {

				$('#action-dialog').load("action/removeTag", function() {

					$.ajaxValidator.fnDoCall("api/tag/fetchAll",
							new InquiryTagRequest ({endRow: 0, startRow: 0, sortExpressions : [{field : "name", direction : "Ascending"}]}),
							false, function(oResponse) {

						sensus.util.page.createOptions($('#tag_remove_select'), oResponse.tags, "combo", false);

						actionDialog.removeClass('waiting');
					});

					$("#tag_remove_select_input").attr("value", sensus.locale.get("widgets.combobox.prompt2"));

					$('#tag_remove_select_input').live( "autocompleteclose", function(event, ui) {
						$(this).blur();
					});

					$('.smartpoint-count').html($('.checked-count').html());

					$('.highlight', '.two-line').html(sensus.locale.get('action.removetag.label.smartpoints',$('#checked-count').html()));
				});

				$('#action-dialog').keypress(function(e) {

					if (e.keyCode == $.ui.keyCode.ENTER) {
						return false;
					}

				});

				actionDialog.addClass('waiting');
				actionDialog.dialog('open');
			}
		}
	},

	disconnectHanFromMeterPage : {

		// The dialog title.
		title : sensus.locale.get("commons.pages.removeConfirm"),

		// The dialog width.
		width : 300,

		// The dialog buttons (submit and cancel).
		buttons : (function() {

			var buttons = {};

			buttons[sensus.locale.get("commons.pages.remove")] = function() {

				sensus.util.page.startProgressBar();

				var actionDialogId = $("#action-dialog");

				// Device
				var device = {
					customerId : sensus.commons.modules.dialogSettings.disconnectHanFromMeterPage.customerId,
					deviceId : sensus.commons.modules.dialogSettings.disconnectHanFromMeterPage.idDevice,
					deviceType : sensus.commons.modules.dialogSettings.disconnectHanFromMeterPage.handeviceType,
					deviceType : sensus.commons.modules.dialogSettings.disconnectHanFromMeterPage.handeviceType,
					electricMeterFlexNetId : sensus.commons.modules.dialogSettings.disconnectHanFromMeterPage.parentId,
					flexNetId : sensus.commons.modules.dialogSettings.disconnectHanFromMeterPage.flexNetId,
					specificDeviceTypeEnumValue : parseInt(sensus.commons.modules.dialogSettings.disconnectHanFromMeterPage.specificDeviceTypeEnumValue, 10)
				};

				// Create Action
				var oParameters = new Action({
					actionType : 11,
					actionTypeName : 'sensus.dm.action.disconnect.han.device',
					actionTime :new Date(),
					onDemand : true,
					isMonitored : true,
					devices : [device]
				});

				$.ajaxValidator.fnMonitor('api/importFile/upload/action/importFile', { parameters : oParameters}, false, null, sensus.locale.get("smartpointdetail.tabs.about.sucessRemoveHan"), null, "messaging-smartpoint-detail");

				$(this).dialog('close');

				actionDialogId.dialog('close');
			};

			buttons[sensus.locale.get("commons.pages.cancel")] = function() {
				var actionDialogId = $("#action-dialog");
				actionDialogId.dialog('close');
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

			var actionDialogId = $("#action-dialog");
			var sDeviceId 	   = $('.detail-header h1').contents(':not(strong)').text().trim();

			actionDialogId.text(sensus.locale.get("smartpointdetail.tabs.about.removeHanDevice", sensus.commons.modules.dialogSettings.disconnectHanFromMeterPage.idDevice, sDeviceId));

			actionDialogId.dialog('open');

		}

	},

	/** Configuration for the "setTamperDetectTimeout" dialog. */
	setTamperTimeout : function(bDetail, sActionName, iActionId, filterSearch) {

		return {

			/** The dialog title. */
			title : sensus.locale.get("devicedetail.dialog.setTamper.title"),

			width : 760,

			close : function(actionDialog) {
				$('.ui-dialog').find('.ui-dialog-titlebar-close').show();
			},

			buttons : (function() {

				var objButtons = {};

				objButtons[sensus.locale.get("commons.pages.save")] = function() {

					var $createDemandResponseForm = $("#actionCofigureAlarmsForm");
					var $table 					  = $("#actionCofigureAlarmsForm table");
					var bDoIt 					  = true;

					// Validate the form
					if (($createDemandResponseForm.validationEngine('validateField', '#minutes-relay1'))
							|| ($createDemandResponseForm.validationEngine('validateField', '#minutes-relay2'))
							|| ($createDemandResponseForm.validationEngine('validateField', '#minutes-relay3'))) {

						bDoIt = false;
						return false;

					} else if (bDoIt) {

						bDoIt = false;

						// Verify if at least one input is filled
						$table.find("input").each(function() {

							var sValue = $(this).val();

						    if (sValue) {
						    	bDoIt = true;
						        return false;
						    }

						});

						if (!bDoIt) {

							$("#lcmAlarms").validationEngine('showPrompt', sensus.locale.get("devicedetail.dialog.setTamper.messageValidation"), 'red', 'topLeft', true);
							return false;

						} else {

							var fnCallback = function() {
								sensus.pages.device.module.reloadModules();
							};

							var aLcmRelaysList 	= [];

							// Fill the lcm relay list
							var fnFillLCMRelayObject = function($element) {

								var iMinutes;
								var sValue 	= $element.val();

								if (!$.sc.isNullOrUndefined(sValue) && (sValue != "")) {
									iMinutes = parseInt(sValue, 10);
								}

								var oLCMRelay = new LCMRelay({
									tamperDetectTimeout : iMinutes,
									relay 				: parseInt($element.attr("name"), 10)
								});

								aLcmRelaysList.push(oLCMRelay);

							};

							$table.find("input").each(function() {

								var $this 	 = $(this);
								var sMinutes = $this.val();

								if (sMinutes != "") {
									// Call function to fill the relay list passing the inputs
									fnFillLCMRelayObject($this);
								}

							});

							var oParameters = {
								actionTypeName	: sActionName,
								onDemand		: true,
								isMonitored		: true,
								lcmRelaysList 	: aLcmRelaysList
							};

							sensus.util.actionrequestutil.fnActionCall('api/deviceop/apply', bDetail, sActionName, iActionId, "devicedetail.dialog.setTamper.messageSuccess", filterSearch, oParameters);

							$("#action-dialog").dialog('close');
						}
					}
				};

				objButtons[sensus.locale.get("commons.pages.cancel")] = function() {

					$(this).dialog('close');
					$('.formError').remove();

				};

				return objButtons;
			})(),

			action : function(actionDialog) {

				var $actionDialog = $('#action-dialog');


				$actionDialog.load("action/setTamperTimeout", function() {

					var $setTamperLegend = $("#set-tamper-dialog-legend");

					$("#actionCofigureAlarmsForm").validationEngine('attach');

					if (bDetail) {
						$setTamperLegend.text(sensus.locale.get("devicedetail.dialog.setTamper.legend", ($.address.parameter("id") || "")));
					} else {
						$setTamperLegend.hide();
					}

				});

				$actionDialog.keypress(function(e) {

					if (e.keyCode == $.ui.keyCode.ENTER) {
						return false;
					}

				});

				actionDialog.addClass('waiting');
				actionDialog.dialog('open');
			}
		}
	},
	/** Configuration for the "setTamperDetectTimeout" dialog. */
	getTamperTimeout : function(bDetail, sActionName, iActionId, filterSearch) {

		return {

			/** The dialog title. */
			title : sensus.locale.get("action.hanGetTamperDetectTime.title"),

			width : 760,

			close : function(actionDialog) {
				$('.ui-dialog').find('.ui-dialog-titlebar-close').show();
			},

			buttons : (function() {

				var objButtons = {};

				objButtons[sensus.locale.get("sensus.dm.action.get.tamper.detect.timer")] = function() {


					if (($('#device_relay_one').prop("checked") == false )&&($('#device_relay_two').prop("checked") == false )&&($('#device_relay_three').prop("checked") == false )) {

						$(".advanced-search-container").validationEngine('showPrompt', sensus.locale.get("devicedetail.dialog.setTamper.messageValidation"), 'red', 'topLeft', true);
						return false;

					} else {

						var aLcmRelaysList 	= [];
						if ($('#device_relay_one').prop("checked"))
						{
							var oLCMRelay = new LCMRelay({
								relay 				: parseInt(1, 10),
								tamperDetectTimeout : parseInt(1, 10)
							});
							aLcmRelaysList.push(oLCMRelay);
						}
						if ($('#device_relay_two').prop("checked"))
						{
							var oLCMRelay = new LCMRelay({
								relay 				: parseInt(2, 10),
								tamperDetectTimeout : parseInt(2, 10)
							});
							aLcmRelaysList.push(oLCMRelay);
						}
						if ($('#device_relay_three').prop("checked"))
						{
							var oLCMRelay = new LCMRelay({
								relay 				: parseInt(3, 10),
								tamperDetectTimeout : parseInt(3, 10)
							});
							aLcmRelaysList.push(oLCMRelay);
						}
						var oParameters = {
							actionTypeName	: sActionName,
							onDemand		: true,
							isMonitored		: true,
							lcmRelaysList 	: aLcmRelaysList
						};
						sensus.util.actionrequestutil.fnActionCall('api/deviceop/apply', bDetail, sActionName, iActionId, "devicedetail.dialog.getTamper.messageSuccess", filterSearch, oParameters);

						$("#action-dialog").dialog('close');
					}
				};

				objButtons[sensus.locale.get("commons.pages.cancel")] = function() {

					$(this).dialog('close');
					$('.formError').remove();

				};

				return objButtons;
			})(),

			action : function(actionDialog) {

				var $actionDialog = $('#action-dialog');


				$actionDialog.load("action/getTamperTimeout", function() {

					var $setTamperLegend = $("#get-tamper-dialog-legend");

					$("#actionCofigureAlarmsForm").validationEngine('attach');

					if (bDetail) {
						$setTamperLegend.text(sensus.locale.get("devicedetail.dialog.setTamper.legend", ($.address.parameter("id") || "")));
					} else {
						$setTamperLegend.hide();
					}

				});

				$actionDialog.keypress(function(e) {

					if (e.keyCode == $.ui.keyCode.ENTER) {
						return false;
					}

				});

				actionDialog.addClass('waiting');
				actionDialog.dialog('open');
			}
		}
	},
	getParticipation : function(bDetail, sActionName, iActionId, filterSearch) {

		return {

			/** The dialog title. */
			title : sensus.locale.get("action.hanGetDemandResponseSetup.title"),

			width : 760,

			close : function(actionDialog) {
				$('.ui-dialog').find('.ui-dialog-titlebar-close').show();
			},

			buttons : (function() {

				var objButtons = {};

				objButtons[sensus.locale.get("sensus.dm.action.get.demand.response.setup.status")] = function() {


					if (($('#relay_one_enrollment_group').prop("checked") != true)&&($('#relay_one_device_class').prop("checked") != true)&&($('#relay_one_start_rondomization').prop("checked") != true)&&($('#relay_one_duration_randomization').prop("checked") != true)&&
					($('#relay_two_enrollment_group').prop("checked") != true)&&($('#relay_two_device_class').prop("checked") != true)&&($('#relay_two_start_rondomization').prop("checked") != true)&&($('#relay_two_duration_randomization').prop("checked") != true)&&
					($('#relay_three_enrollment_group').prop("checked") != true)&&($('#relay_three_device_class').prop("checked") != true)&&($('#relay_three_start_rondomization').prop("checked") != true)&&($('#relay_three_duration_randomization').prop("checked") != true))

					{

						$(".advanced-search-container").validationEngine('showPrompt', sensus.locale.get("devicedetail.dialog.setTamper.messageValidation"), 'red', 'topLeft', true);
						return false;

					} else {
						var iEnrollmentCode = null,
						iStartMinutes   = null,
						iEndMinutes     = null,
						sDeviceClass    = null;

						var aLcmRelaysList 	= [];
						if (($('#relay_one_enrollment_group').prop("checked"))||($('#relay_one_device_class').prop("checked"))||($('#relay_one_start_rondomization').prop("checked"))||($('#relay_one_duration_randomization').prop("checked")))
						{
							if ($('#relay_one_enrollment_group').prop("checked")){
								iEnrollmentCode = parseInt(1, 10);
							}else{
								iEnrollmentCode = null;
							}
							if ($('#relay_one_start_rondomization').prop("checked")){
								iStartMinutes = parseInt(1, 10);
							}else{
								iStartMinutes = null;
							}
							if ($('#relay_one_duration_randomization').prop("checked")){
								iEndMinutes = parseInt(1, 10);
							}else{
								iEndMinutes = null;
							}
							if ($('#relay_one_device_class').prop("checked")){
								sDeviceClass = 'HVAC_COMPRESSOR';
							}else{
								sDeviceClass = null;
							}


							var oLCMRelay = new LCMRelay({
								relay 				: parseInt(1, 10),
								enrollmentCode      : iEnrollmentCode,
								startMinutes        : iStartMinutes,
								endMinutes          : iEndMinutes,
								deviceClass         : sDeviceClass
							});
							aLcmRelaysList.push(oLCMRelay);
						}

						if (($('#relay_two_enrollment_group').prop("checked"))||($('#relay_two_device_class').prop("checked"))||($('#relay_two_start_rondomization').prop("checked"))||($('#relay_two_duration_randomization').prop("checked")))
						{
							if ($('#relay_two_enrollment_group').prop("checked")){
								iEnrollmentCode = parseInt(1, 10);
							}else{
								iEnrollmentCode = null;
							}
							if ($('#relay_two_start_rondomization').prop("checked")){
								iStartMinutes = parseInt(1, 10);
							}else{
								iStartMinutes = null;
							}
							if ($('#relay_two_duration_randomization').prop("checked")){
								iEndMinutes = parseInt(1, 10);
							}else{
								iEndMinutes = null;
							}
							if ($('#relay_two_device_class').prop("checked")){
								sDeviceClass = 'HVAC_COMPRESSOR';
							}else{
								sDeviceClass = null;
							}


							var oLCMRelay = new LCMRelay({
								relay 				: parseInt(2, 10),
								enrollmentCode      : iEnrollmentCode,
								startMinutes        : iStartMinutes,
								endMinutes          : iEndMinutes,
								deviceClass         : sDeviceClass
							});
							aLcmRelaysList.push(oLCMRelay);
						}

						if (($('#relay_three_enrollment_group').prop("checked"))||($('#relay_three_device_class').prop("checked"))||($('#relay_three_start_rondomization').prop("checked"))||($('#relay_three_duration_randomization').prop("checked")))
						{
							if ($('#relay_three_enrollment_group').prop("checked")){
								iEnrollmentCode = parseInt(1, 10);
							}else{
								iEnrollmentCode = null;
							}
							if ($('#relay_three_start_rondomization').prop("checked")){
								iStartMinutes = parseInt(1, 10);
							}else{
								iStartMinutes = null;
							}
							if ($('#relay_three_duration_randomization').prop("checked")){
								iEndMinutes = parseInt(1, 10);
							}else{
								iEndMinutes = null;
							}
							if ($('#relay_three_device_class').prop("checked")){
								sDeviceClass = 'HVAC_COMPRESSOR';
							}else{
								sDeviceClass = null;
							}


							var oLCMRelay = new LCMRelay({
								relay 				: parseInt(3, 10),
								enrollmentCode      : iEnrollmentCode,
								startMinutes        : iStartMinutes,
								endMinutes          : iEndMinutes,
								deviceClass         : sDeviceClass
							});
							aLcmRelaysList.push(oLCMRelay);
						}

						if (bDetail && ($.address.parameter("typeEnum") == "FLEXNET_LCM")) {
							var oParameters = {
								actionTypeName	: sActionName,
								onDemand		: true,
								isMonitored		: true,
								lcmRelaysList 	: aLcmRelaysList,
								lcmFlexNet      : false
							};
						}else{
							var oParameters = {
									actionTypeName	: sActionName,
									onDemand		: true,
									isMonitored		: true,
									lcmRelaysList 	: aLcmRelaysList,
									lcmFlexNet      : true
								};

						}
						sensus.util.actionrequestutil.fnActionCall('api/deviceop/apply', bDetail, sActionName, iActionId, "commons.pages.getDemandResponseSetupStatusSucess", filterSearch, oParameters);

						$("#action-dialog").dialog('close');
					}
				};

				objButtons[sensus.locale.get("commons.pages.cancel")] = function() {

					$(this).dialog('close');
					$('.formError').remove();

				};

				return objButtons;
			})(),

			action : function(actionDialog) {

				var $actionDialog = $('#action-dialog');
				var sActionDialog;

				// When is the detail page, load the setup demand response specific for FLEXNET_LCM
				if (bDetail && ($.address.parameter("typeEnum") == "FLEXNET_LCM")) {
					sActionDialog = "action/getParticipation";
				}else{
					sActionDialog = "action/getParticipationLCM";
				}


				$actionDialog.load(sActionDialog, function() {

					var $setTamperLegend = $("#get-participation-dialog-legend");

					$("#actionCofigureAlarmsForm").validationEngine('attach');

					if (bDetail) {
						$setTamperLegend.text(sensus.locale.get("devicedetail.dialog.getParticipation.legend", ($.address.parameter("id") || "")));
					} else {
						$setTamperLegend.text(sensus.locale.get("device.dialog.getParticipation.legend"));
					}

				});

				$actionDialog.keypress(function(e) {

					if (e.keyCode == $.ui.keyCode.ENTER) {
						return false;
					}

				});

				actionDialog.addClass('waiting');
				actionDialog.dialog('open');
			}
		}
	}
};
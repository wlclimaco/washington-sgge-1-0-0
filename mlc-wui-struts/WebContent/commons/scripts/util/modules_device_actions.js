sensus.commons.modules.dialogSettings = {
		
		/**
		 * Configuration for the "removeFromGroup" dialog.
		 */
		 DemandResponse : {
			/**
			 * The dialog title.
			 */
			title : sensus.locale.get("action.hanDemandResponse.title"),
			
			width : 760,
			/**
			 * The dialog buttons (submit and cancel).
			 */
			 
			close : function(actionDialog) {
				$('.ui-dialog').find('.ui-dialog-titlebar-close').show();
			},  
			 
			buttons : (function() {
				
				var objButtons = {};
				objButtons[sensus.locale.get("action.hanDemandResponse.submit")] = function() {

					var aIds = sensus.widgets.datatable.isAllRows ? sensus.widgets.datatable.deselectedRows : sensus.widgets.datatable.selectedRows;
					var sIdUrl = sensus.widgets.datatable.isAllRows && aIds.length <= 0 ? "" : "&smartpointIds=" + aIds.join("&smartpointIds=");
						sIdUrl = sIdUrl === "&smartpointIds=" || sIdUrl === "" ? "&smartpointIds=" + $("#flexnet-id").text() : sIdUrl;
					var action = "demandResponse";
					sensus.widgets.datatable.clearSelects();
										
					var sData ="&duration="+$('#duration').val() + "&enrollmentCode=" + $('#enrollment-code').val()+"&pctSetback="+$('#pct-setback').val()+"&lcmCycle="+$('#lcm-cycle').val()+"&hvacCycle="+$('#hvac-cycle').val()+"&hanCriticality="+$('#hanCriticality').val()+"&randomizeStart="+$('#randomizeStart').is(":checked")+"&randomizeEnd="+$('#randomizeEnd').is(":checked");
					
					if(($("#createDemandResponseForm").validationEngine('validateField', '#duration')) || ($("#createDemandResponseForm").validationEngine('validateField', '#enrollment-code'))|| 
							($("#createDemandResponseForm").validationEngine('validateField', '#pct-setback'))|| ($("#createDemandResponseForm").validationEngine('validateField', '#lcm-cycle'))||
								($("#createDemandResponseForm").validationEngine('validateField', '#hvac-cycle'))) {	
						
						bDoIt = false;
						
					} else {
					
						bDoIt = true;
					
					}	
					if(bDoIt){
						var data = ""+ sData +"&actionName="+action+ "&isAllRows="+sensus.widgets.datatable.isAllRows + sIdUrl + '&' + $.address.queryString();
						
					sensus.pages.longrunningprocess.monitor("smartpoint/ajax.applyActions.action", data);
//					$.ajax({
//						url : "smartpoint/ajax.applyActions.action",
//						async: false,
//						type : 'POST',
//						data : ""+ sData +"&actionName="+action+ "&isAllRows="+sensus.widgets.datatable.isAllRows + sIdUrl + '&' + $.address.queryString(),
//
//						/**
//						 * The success handler. This will be invoked if the HTTP request
//						 * returned correctly. The status of the reuested operation needs to
//						 * be determined from the response object.
//						 */
//						success : function(oResponse) {
//							
//							/* If successful, close dialog and show message */
//							if (oResponse) {
//								
//								if(oResponse.operationSuccess){
//									/** Show message Sucess */
//									sensus.util.page.showMessage("messaging-main", sensus.locale.get("smartpoint.actions.removegroup.success","sGroupName", "1"), "confirm");
//									if($("#flexnet-id").text().length === 0){
//										sensus.widgets.datatable.reloadTable(sensus.pages.smartpoint.smartpointTable);
//									}
//								} else {
//
//									/** Show message Error */
//									sensus.util.page.showMessage("messaging-main", sensus.locale.get("commons.pages.error"), "error");
//
//								}
//							}
//														
//							sensus.util.page.stopProgressBar();							
//							sensus.widgets.datatable.clearSelects();
//
//						}
//						
//					});
					
					$("#action-dialog").dialog('close');
							
						sensus.widgets.datatable.clearSelects();
							
						return false;
						if($("#flexnet-id").text().length === 0){
							sensus.widgets.datatable.reloadTable(sensus.pages.smartpoint.smartpointTable);
						}
					}

				}
				objButtons[sensus.locale.get("action.removefromgroup.cancel")] = function() {
					
					$(this).dialog('close');
					$('.formError').remove();

				}	
				return objButtons;
			})(),
			
			action : function(actionDialog) {
				
				actionDialog.load(sensus.settings.hanDemandResponseInclude, function() {
					
					$('.ui-dialog').find('.ui-dialog-titlebar-close').hide();
					
					actionDialog.removeClass('waiting');
					
				
				});

				actionDialog.addClass('waiting');
				actionDialog.dialog('open');
			}
		},

		connectHanDevice : {
			
			title : "Action - Connect HAN Device",
			
			buttons : (function() {
				var objButtons = {}
				objButtons["Connect HAN Device"] = function () {	
										
					var oData = {
						sMacAddress : $("#mac_address").val(),
						sInstallCode : $("#install_code").val(),
						sClientEndPointId : $("#device-id", "h1").text()
					}
					
					//Validation the inputs
					if (!sensus.commons.modules.summaryData.actions.connectFunctions.fnValidateConnectHanFields(oData)) {
						
						var oResponse,
							bValidFields = false;
						
						if (sensus.commons.modules.dialogSettings.connectHanDevice.bHanExists) {
							
							oData.sDeviceId = null;
							oData.sManufacture = null;
							oData.sModelNumber = null;
							
							oData.sActionName = "ConnectHanDevice";
							
							bValidFields = true;
							
						} else {
							/**
							 * This code goes on sprint 3
							 */
							oData.sDeviceId = $("#device_name_input").val();
							oData.sManufacture = $("#select-manufacture").val();
							oData.sModelNumber = $("#select-model-number").val();
							oData.sDeviceType = $('#select-device-types').val();
							
							if (!sensus.commons.modules.summaryData.actions.connectFunctions.fnValidateInsertHanDevice(oData)) {
								oData.sActionName = "ConnectNonExistingHanDevice";
								bValidFields = true;
							}
							
						}
						
						if (bValidFields) {
							
							oResponse = sensus.commons.modules.summaryData.actions.connectFunctions.fnApplyHanDevice(oData);
							
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
				
				actionDialog.load("commons/pages/controller/actions/connect_han_device.jsp", function() {
					
					$("#label-mac-address").removeClass("hide");
					
					$("#connect-han-device-form").validationEngine('attach');
					
					// Events to clear error validation
					sensus.commons.modules.summaryData.actions.connectFunctions.fnClearValidationEvents(actionDialog);
															
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
							oSelectModelNumber : $("#select-model-number", actionDialog)
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
						sensus.commons.modules.summaryData.actions.connectHANDevice.fnFillManufactureByType($(this).find("option:selected").attr("name"), oDom);
					});
					
					// Manufacture Event Change
					oDom.actionDialog.delegate("#select-manufacture", "change", function() {
						sensus.commons.modules.summaryData.actions.connectHANDevice.fnFillModelNumberByManufacture(oDom.oSelectDeviceTypes.find("option:selected").attr("name"), $(this).find("option:selected").attr("name"), oDom);
					});
					
					oDom.oMacAddress.focusout(function(event) {
 
						var id = $(this).val();
 
							oDom.oMacAddressValidate.removeClass("hideImportant");
							oDom.oMacAddressValidate.removeClass("icn-valid-form icn-notice-form").addClass('icn-sending');
															
							// FetchHanDeviceByDevice
							$.when(sensus.commons.modules.summaryData.actions.connectHANDevice.fnGetHanDeviceByDeviceResponse(id)).then(function (oResponse){
								// Get First Device from response
								var device = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
								
								if (device) { 
									sensus.commons.modules.dialogSettings.connectHanDevice.bHanExists = true;
									// Fill Container if exists device
									sensus.commons.modules.summaryData.actions.connectHANDevice.fnFillExistingDevice(device, oDom);
								} else {
									
									oDom.oAddFields.removeClass("hide");
									oDom.oFormInfo.addClass("hide");
									oDom.oMacAddressValidate.removeClass("icn-sending").addClass("icn-notice-form");
									
									sensus.commons.modules.dialogSettings.connectHanDevice.bHanExists = false;
									
									$("#mac_id").text(sensus.locale.get("smartpointdetail.tabs.about.connectHanDeviceText",id));
									
									/**
									 * This code goes on sprint 3
									 */
									// Fill Form if device does not exist
									sensus.commons.modules.summaryData.actions.connectHANDevice.fnFillNonExistingDevice(oDom);
								}
							})
							
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
			
		},
		
		connectToHan : {
			
			title : "Action - Connect HAN Device",
			
			buttons : (function() {
				var objButtons = {}
				objButtons["Connect to HAN"] = function () {
					
					var oData = {
						sClientEndPointId : $("#device_id").val(),
						sInstallCode : $("#install_code").val(),
						sMacAddress : $.address.parameter("id")
					}

					// If there is no validation send the action
					if (!sensus.commons.modules.summaryData.actions.connectFunctions.fnValidateConnectToHanFields(oData)) {
						oData.sActionName = "ConnectHanDevice";

						var oResponse = sensus.commons.modules.summaryData.actions.connectFunctions.fnApplyHanDevice(oData);

						$(this).dialog('close');
						
					}
					
					
				};
				
				objButtons["Cancel"] = function () {
					$(this).empty();
					$(this).dialog('close');
				}
				
				return objButtons;
			})(),
			
			action : function(actionDialog) {
				
				actionDialog.load("commons/pages/controller/actions/connect_han_device.jsp", function() {
					
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
						
						if (sDeviceIdValue) {
							$.ajax({
								url : "smartpointdetail/include.fetchDeviceById.action",
								data : 'deviceId=' + sDeviceIdValue + "&deviceType=Meter",
								success : function (oResponse) {
									// Get First Device from response
									device = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);

									if (device) { 

										// Change Icon
										oDom.oMacAddressValidate.removeClass("icn-sending").addClass("icn-valid-form");
										
									} else {
										
										// Change Icon
										oDom.oMacAddressValidate.removeClass("icn-sending").addClass("icn-notice-form");
										
										sensus.commons.modules.dialogSettings.connectHanDevice.bHanExists = false;
									}
								}
							})
						}
						
					});
					
				});				
				actionDialog.dialog('open');
			
			},
			
			close : function(actionDialog) {
				
				$('.formError').remove();
				
			}
		},
		
		removeFromHAN : {
			/**
			 * The dialog title.
			 */
			title: sensus.locale.get("commons.pages.removeConfirm"),
			
			/**
			 * The dialog width.
			 */
			width : 300,
			
			/**
			 * The dialog buttons (submit and cancel).
			 */
			buttons : (function() {
				var buttons = {};
				buttons[sensus.locale.get("commons.pages.remove")] = function() {
					
					var actionDialogId = $("#action-dialog"),
						sFlexnetId = $.address.parameter('id'),
						sMessagingSmartpointDetail = 'messaging-smartpoint-detail',
						sUrl = sensus.settings.removeMeterFromHanDevice,
						oData = "macAddress=" + sFlexnetId;

					var fnCallback = function () {
						// Call modules controller component
						sensus.commons.modules.loadModules();
					}
					
					sensus.pages.longrunningprocess.monitor(sensus.settings.removeMeterFromHanDevice, oData, sensus.locale.get("smartpointdetail.tabs.about.sucessRemoveHan"), fnCallback)
					
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

				var actionDialogId = $("#action-dialog"),
					sFlexnetId = $.address.parameter('id');
				
				actionDialogId.text(sensus.locale.get("smartpointdetail.tabs.about.removeFromHan",sFlexnetId));
				
				actionDialogId.dialog('open');
				
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
				actionDialogId.dialog('open');
			}
		},
		
		/**
		 * Configuration for the "SendHANTextMessage" dialog.
		 */
		HANTextMessage : {
			/**
			 * The dialog title.
			 */
			title : sensus.locale.get("action.hanTextMessage.title"),
			/**
			 * Whether this dialog requires a smartpoint list.
			 */
			requiresSmartpoints : true,
			
			width : 760,
			/**
			 * The dialog buttons (submit and cancel).
			 */			 
			buttons : (function() {
				
				var objButtons = {};
				objButtons[sensus.locale.get("action.hanTextMessage.submit")] = function() {
					
					
					var aIds = sensus.widgets.datatable.isAllRows ? sensus.widgets.datatable.deselectedRows : sensus.widgets.datatable.selectedRows;
					var sIdUrl = sensus.widgets.datatable.isAllRows && aIds.length <= 0 ? "" : "&smartpointIds=" + aIds.join("&smartpointIds=");
						sIdUrl = sIdUrl === "&smartpointIds=" || sIdUrl === "" ? "&smartpointIds=" + $.address.parameter('id') : sIdUrl;
					var hanTextMenssage = $('#textMessageHan').val();
					var hanDuration = $('#textMessageDuration').val();
					var hanMessageDate = $('#hanMessageDate').val();
					var hanMessageTime = $('#hanMessageTime').val();
					var action = "sendHANTextMessage";
					var bDoIt = false;
					var oActionDialog = $("#action-dialog");
					
					var fnCheckHours = function(){
						
						var dateFormat =sensus.settings.dateFormatMask.replace('yyyy','yy');
						
						var sDate = $('#hanMessageDate').val();
						var sHour = $("#hanMessageTime").val().replace('am', ' am').replace('pm', ' pm');
						var dDateClient = new Date(sDate + ' ' + sHour);
						var dDateServer = new Date($.date.dateFormat($('#currentDate').val(), dateFormat+" h:i:s a"));

						if (dDateServer > dDateClient) {

							$('#hanMessageTime').validationEngine('showPrompt', sensus.locale.get('commons.pages.futureDate'), 'red', '', true);
							return true;
						}
					}
					
					var fnCheckMessage = function(){
						
						var oTextMessageDuration = $('#textMessageDuration'),
							sTextMessageDuration = $(oTextMessageDuration).val();
						
						if((sTextMessageDuration == '0') || (sTextMessageDuration.search(/^\d+$/) == -1 )){
							oTextMessageDuration.validationEngine('showPrompt', sensus.locale.get('commons.pages.invalidValue'), 'red', '', true);
							return true;
						}
					}
					
					
					sensus.widgets.datatable.clearSelects();
					if(($("#createTextHanForm").validationEngine('validateField', '#hanMessageDate')) ||($("#createTextHanForm").validationEngine('validateField', '#hanMessageTime')) ||($("#createTextHanForm").validationEngine('validateField', '#textMessageHan')) || ($("#createTextHanForm").validationEngine('validateField', '#textMessageDuration')) || fnCheckHours() || fnCheckMessage()) {	
						bDoIt = false;
						
					} else {
					
						bDoIt = true;
					
					}	
					
					if(bDoIt){
						sensus.util.page.startProgressBar();
						var data =  "hanMessageDate="+hanMessageDate+"&hanMessageTime="+hanMessageTime+"&hanTextMenssage=" + hanTextMenssage +"&hanDuration="+hanDuration+"&actionName="+action+ "&isAllRows="+sensus.widgets.datatable.isAllRows + sIdUrl + '&' + $.address.queryString();
						
						sensus.pages.longrunningprocess.monitor("smartpoint/ajax.applyActions.action", data,sensus.locale.get("commons.pages.sendHanTextSucess"));
						
//						$.ajax({
//							url : "smartpoint/ajax.applyActions.action",
//							async: false,
//							type : 'POST',
//							data : "hanMessageDate="+hanMessageDate+"&hanMessageTime="+hanMessageTime+"&hanTextMenssage=" + hanTextMenssage +"&hanDuration="+hanDuration+"&actionName="+action+ "&isAllRows="+sensus.widgets.datatable.isAllRows + sIdUrl + '&' + $.address.queryString(),
//
//							/**
//							 * The success handler. This will be invoked if the HTTP request
//							 * returned correctly. The status of the reuested operation needs to
//							 * be determined from the response object.
//							 */
//							success : function(oResponse) {
//								
//								
//								if (oResponse) {
//									
//									var sDivmessaging = "messaging-main",
//									
//									iSize = $('.messaging-smartpoint-detail').length;
//							
//									if(iSize){
//										sDivmessaging = "messaging-smartpoint-detail";
//									}
//									
//									if(oResponse.operationSuccess){
//										/** Show message Sucess */
//										sensus.util.page.showMessage(sDivmessaging, sensus.locale.get("commons.pages.sendHanTextSucess"), "confirm");
//										if($.address.parameter('id') != undefined){
//										if($.address.parameter('id').length === 0){
//											sensus.widgets.datatable.reloadTable(sensus.pages.smartpoint.smartpointTable);
//										}
//										}
//									} else {
//
//										/** Show message Error */
//										sensus.util.page.showMessage(sDivmessaging, sensus.locale.get("commons.pages.sendHanTextFailure"), "error");
//
//									}
//								}
//															
//								sensus.util.page.stopProgressBar();							
//								sensus.widgets.datatable.clearSelects();
//
//							}
//							
//						});
						sensus.util.page.stopProgressBar();							
    					sensus.widgets.datatable.clearSelects();
						oActionDialog.dialog('close');
					
					}else{
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
				
				$('#action-dialog').load(sensus.settings.hanTextMessageInclude, function() {
					
					var dateToday = $("#currentDate").val();
					$("#hanMessageDate").val($.date.dateFormat(dateToday));
					$('#hanMessageTime').val($.date.dateFormat(dateToday, "h:ia"));
				
					$('#action-dialog').addClass('send-han-message');
					
					var dateFormat =sensus.settings.dateFormatMask.replace('yyyy','yy');
					
					actionDialog.removeClass('waiting');
					
					$('#group_add_select').combobox( {
						zIndex : 3999
					});
					$('.time').calendricalTime();
	
					$('#hanMessageDate').datepicker({
						dateFormat: dateFormat,
						minDate: "0D" 
					});
					
					$("#group_add_select_input").attr("value", sensus.locale.get("widgets.combobox.prompt2"));
					
					$(".two-line").delegate("#group_add_select_input", "autocompleteclose", function(e) {
						$(this).blur();
					});
					
					var dateFormat =sensus.settings.dateFormatMask.replace('yyyy','yy');
					var sClassTypeFormat = '';

					if( dateFormat == 'mm/dd/yy'){					
						sClassTypeFormat = 'dateEn';					
					} else {					
						sClassTypeFormat = 'datePt';					
					}
					$('#hanMessageDate').removeClass();
					$('#hanMessageDate').addClass("datepicker short hasDatepicker validate[required, custom["+sClassTypeFormat+"]]");
					
					$('.smartpoint-count').html($('#checked-count').html());
					$('.time').calendricalTime();
					
					$('.smartpoint-count').html($('#checked-count').html());

					$('.timezone').text(sensus.locale.get('smartpointdetail.tabs.about.timeZone') + ' (' + sensus.settings.timezone + ') ');	
					
					$('#hanMessageDate').datepicker({ minDate: "-1M", maxDate: "+0D" });
					
					var sClassTypeFormat = '';
					
					var sCurrentDate = $('#currentDate').val();
					
					if( dateFormat == 'mm/dd/yy'){
						
						sClassTypeFormat = 'dateEn';
					
					} else {
					
						sClassTypeFormat = 'datePt';
					}

						
					$('#hanMessageDate').addClass("validate[required, custom["+sClassTypeFormat+", future["+$.date.dateFormat(sCurrentDate,"yy-mm-dd")+"]");
					$('#hanMessageTime').addClass("validate[required, custom[timeFormat]]");
				
				});
				
				actionDialog.addClass('waiting');
				actionDialog.dialog('open');
				
				
			},
			
			close : function(actionDialog) {
				$('.formError').remove();
			}
		},
		/**
		 * Configuration for the "removeFromGroup" dialog.
		 */
		 SetupDemandResponse : {
			/**
			 * The dialog title.
			 */
			title : sensus.locale.get("action.hanDemandResponseSetup.title"),
			/**
			 * Whether this dialog requires a smartpoint list.
			 */
			requiresSmartpoints : true,
			
			
			width : 760,
			/**
			 * The dialog buttons (submit and cancel).
			 */
			 
			close : function(actionDialog) {
				$('.ui-dialog').find('.ui-dialog-titlebar-close').show();
			},  
			 
			buttons : (function() {
				
				var objButtons = {};
				objButtons[sensus.locale.get("action.hanDemandResponseSetup.submit")] = function() {
					
					
					var aIds = sensus.widgets.datatable.isAllRows ? sensus.widgets.datatable.deselectedRows : sensus.widgets.datatable.selectedRows;
					var sIdUrl = sensus.widgets.datatable.isAllRows && aIds.length <= 0 ? "" : "&smartpointIds=" + aIds.join("&smartpointIds=");
						sIdUrl = sIdUrl === "&smartpointIds=" || sIdUrl === "" ? "&smartpointIds=" + $("#flexnet-id").text() : sIdUrl;
					var enrollmentCode = $("#enrollmentCode").val();
					randomizeStart = $("#randomizeStart").val();
					randomizeEnd = $("#randomizeEnd").val();
					actionName = "setupDemandResponse";
					var bDoIt = false;
					sensus.widgets.datatable.clearSelects();
					if(($("#createSetupDemandResponseForm").validationEngine('validateField', '#enrollmentCode')) || ($("#createSetupDemandResponseForm").validationEngine('validateField', '#randomizeStart'))|| ($("#createSetupDemandResponseForm").validationEngine('validateField', '#randomizeEnd'))) {	
					
						bDoIt = false;
						
					} else {
					
						bDoIt = true;
					
					}	
					if(bDoIt){
							var data = "enrollmentCode="+ enrollmentCode +"&actionName="+actionName+"&randomizeStart=" + randomizeStart +"&randomizeEnd="+ randomizeEnd + "&isAllRows="+sensus.widgets.datatable.isAllRows + sIdUrl + '&' + $.address.queryString();
							sensus.pages.longrunningprocess.monitor("smartpoint/ajax.applyActions.action", data);
//							$.ajax({
//								url : "smartpoint/ajax.applyActions.action",
//								async: false,
//								type : 'POST',
//								data : "enrollmentCode="+ enrollmentCode +"&actionName="+actionName+"&randomizeStart=" + randomizeStart +"&randomizeEnd="+ randomizeEnd + "&isAllRows="+sensus.widgets.datatable.isAllRows + sIdUrl + '&' + $.address.queryString(),
//
//								/**
//								 * The success handler. This will be invoked if the HTTP request
//								 * returned correctly. The status of the reuested operation needs to
//								 * be determined from the response object.
//								 */
//								success : function(oResponse) {
//								
//									
//									if (oResponse) {
//										
//										if(oResponse.operationSuccess){
//											/** Show message Sucess */
//											sensus.util.page.showMessage("messaging-main", sensus.locale.get("smartpoint.actions.removegroup.success","sGroupName", "1"), "confirm");
//											if($("#flexnet-id").text().length === 0){
//												sensus.widgets.datatable.reloadTable(sensus.pages.smartpoint.smartpointTable);
//											}
//										} else {
//
//											/** Show message Error */
//											sensus.util.page.showMessage("messaging-main", sensus.locale.get("commons.pages.error"), "error");
//
//										}
//									}
//																
//									sensus.util.page.stopProgressBar();							
//									sensus.widgets.datatable.clearSelects();
//
//								}
//								
//							});
							
							$("#action-dialog").dialog('close');
						if($("#flexnet-id").text().length === 0){
							sensus.widgets.datatable.reloadTable(sensus.pages.smartpoint.smartpointTable);
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
				
				$('#action-dialog').load(sensus.settings.hanDemandResponseSetupInclude, function() {
					
					$('.ui-dialog').find('.ui-dialog-titlebar-close').hide();
					
					actionDialog.removeClass('waiting');
					
				
				});



				actionDialog.addClass('waiting');
				actionDialog.dialog('open');
			}
		},
		
		addToGroup : {


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
					
					var aIds = sensus.widgets.datatable.isAllRows ? sensus.widgets.datatable.deselectedRows : sensus.widgets.datatable.selectedRows;
					var sIdUrl = sensus.widgets.datatable.isAllRows && aIds.length <= 0 ? "" : "&smartpointIds=" + aIds.join("&smartpointIds=");
					
					sensus.widgets.datatable.clearSelects();

									
					$('#group_add_select_input, .ui-autocomplete').click(function() {
						
						$('.formError').remove();
						
					});
					
					if (!bHasGroup) {
					
						$("#add-group-form .ui-autocomplete-input").validationEngine('showPrompt', sensus.locale.get("commons.pages.selectGroup"), 'red', '', true);
					
					} else {
					
					
						var sNoGroup = sGroupName.length == 0 || sGroupName == sensus.locale.get("widgets.combobox.prompt2");
						
						if (!sNoGroup && (sensus.widgets.datatable.isAllRows || aIds.length > 0) && bHasGroup) {
							
							$('.formError').remove();
							
							$("#action-dialog").dialog('close');
							
							sensus.util.page.startProgressBar(sensus.locale.get("widgets.progress.addToGroup"));
							
							$.ajax({
								url : sensus.settings.addToGroupUrl,
								type : 'POST',
								data : "groupId=" + iGroupId + "&isAllRows="+sensus.widgets.datatable.isAllRows + sIdUrl + '&' + $.address.queryString(),

								/**
								 * The success handler. This will be invoked if the HTTP request
								 * returned correctly. The status of the reuested operation needs to
								 * be determined from the response object.
								 */
								success : function(data) {
								
									/* If successful, close dialog and show message */
									
									if (data.result === sensus.constants.ajax.ok) {
										
										sensus.util.page.showMessage("messaging-main", sensus.locale.get("smartpoint.actions.addgroup.success",sGroupName, "1"), "confirm");
										$('.select-none').click();
										
									} else {
										
										sensus.util.page.showMessage("messaging-main", data.messages[0], "error");
										
									}
																
									$("#action-dialog").dialog('close');
									sensus.util.page.stopProgressBar();							
									sensus.widgets.datatable.clearSelects();

								}
							/*
							 * At this point there is no error handler. We should consider entering
							 * one. It would take effect if the provided url is not available or the
							 * request returns any other HTTP error.
							 */
							});
							
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
					
			action : function(actionDialog) {
				
				$('.ui-dialog').find('.ui-dialog-titlebar-close').hide();
				var addGroupForm = $('#add-group-form .add-group-control');
				var groupAddSelectInput = $('#group_add_select_input');
				
				$('#action-dialog').load("smartpoint/include.smartpoint.add.group.dialog.action", function() {
					$('.ui-dialog-buttonset').delegate('.ui-button', 'click', function () {
						
						if (groupAddSelectInput.val() != sensus.locale.get('widgets.combobox.prompt2') && groupAddSelectInput.val() != sensus.locale.get('action.addgroup.label.addnewgroup')) {
							
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
		},
		
		deleteHanDevices : {
			
			/**
			 * The dialog title.
			 */
			title : sensus.locale.get("commons.pages.removeConfirm"),
			
			/**
			 * The dialog width.
			 */
			width : 300,
			
			/**
			 * The dialog buttons (submit and cancel).
			 */
			buttons : (function() {
				var buttons = {};
				buttons[sensus.locale.get("commons.pages.remove")] = function() {
					
					sensus.util.page.startProgressBar();
					
					var actionDialogId = $("#action-dialog");
					var aIds = sensus.widgets.datatable.isAllRows ? sensus.widgets.datatable.deselectedRows : sensus.widgets.datatable.selectedRows;
					var sIdUrl = sensus.widgets.datatable.isAllRows && aIds.length <= 0 ? "" : "&smartpointIds=" + aIds.join("&smartpointIds=");
						sIdUrl = sIdUrl === "&smartpointIds=" || sIdUrl === "" ? "&smartpointIds=" + $.address.parameter('id') : sIdUrl;
					var action = "deleteHanDevices";
						
					var fnCallback = function (data) {
						sensus.commons.lib.ajax.do_load($.extend({}, sensus.commons.lib.ajax.param, {
							   sUrl : "smartpoint/ajax.smartpoint.main.action",
							   $container : $('#load'),
							   message : {
									bMessage : true,
									sMessage : "Device ID "+$.address.parameter('deviceType')+" has been seccessfully deleted."
								}
						  }));
					}
					
					oData = 'isAllRows='+sensus.widgets.datatable.isAllRows + sIdUrl + '&' + $.address.queryString()+"&actionName="+action;
					
					sensus.pages.longrunningprocess.monitor("smartpoint/ajax.applyActions.action", oData,"Device ID "+$.address.parameter('deviceType')+" has been successfully deleted.",fnCallback)

					
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
				if($('#network-status-value').text()=== "Disconnected"){
						actionDialogId.text("Device ID "+$.address.parameter('deviceType')+" will be permanently deleted. Confirm delete?");
					}else{
						actionDialogId.text("Device ID "+$.address.parameter('deviceType')+" is currently connected. Delete without disconnecting?");
					}
				actionDialogId.text("Device ID "+$.address.parameter('deviceType')+" and all its history will be permanetally deleted. Confirm delete?");
				
				actionDialogId.dialog('open');
				
			}
			
		},
		
			
		/**
		 * Configuration for the "removeFromGroup" dialog.
		 */
		removeFromGroup : {
			/**
			 * The dialog title.
			 */
			title : sensus.locale.get("action.removefromgroup.title"),
			/**
			 * Whether this dialog requires a smartpoint list.
			 */
			requiresSmartpoints : true,
			/**
			 * The dialog buttons (submit and cancel).
			 */
			 
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
					
					var aIds = sensus.widgets.datatable.isAllRows ? sensus.widgets.datatable.deselectedRows : sensus.widgets.datatable.selectedRows;
					var sIdUrl = sensus.widgets.datatable.isAllRows && aIds.length <= 0 ? "" : "&smartpointIds=" + aIds.join("&smartpointIds=");
					
					sensus.widgets.datatable.clearSelects();
										

									
					$('#group_add_select_input, .ui-autocomplete').click(function() {
						
						$('.formError').remove();
						
					});
					
					if (!bHasGroup) {
					
						$("#add-group-form .ui-autocomplete-input").validationEngine('showPrompt', sensus.locale.get("commons.pages.selectGroup"), 'red', '', true);
					
					} else {
					
						var sNoGroup = sGroupName.length == 0 || sGroupName == sensus.locale.get("widgets.combobox.prompt2");
						
						if (!sNoGroup && (sensus.widgets.datatable.isAllRows || aIds.length > 0)) {
							
							$('.formError').remove();
							
							sensus.util.page.startProgressBar(sensus.locale.get("widgets.progress.removeFromGroup"));
							
							$.ajax({
								url : sensus.settings.removeFromGroupUrl,
								async: false,
								type : 'POST',
								data : "groupId=" + iGroupId + "&isAllRows="+sensus.widgets.datatable.isAllRows + sIdUrl + '&' + $.address.queryString(),

								/**
								 * The success handler. This will be invoked if the HTTP request
								 * returned correctly. The status of the reuested operation needs to
								 * be determined from the response object.
								 */
								success : function(data) {
									
									/* If successful, close dialog and show message */
									if (data.result === sensus.constants.ajax.ok) {
										
										sensus.util.page.showMessage("messaging-main", sensus.locale.get("smartpoint.actions.removegroup.success",sGroupName, "1"), "confirm");
										$('.select-none').click();
										
									} else {
										
										if(data.messages[0] === "[{0}] Devices not in Group."){
											sensus.util.page.showMessage("messaging-main", sensus.locale.get("smartpoint.actions.removegroup.success",sGroupName, "1"), "confirm");
										}else{									
											sensus.util.page.showMessage("messaging-main", sensus.locale.get("commons.pages.error"), "error");
										}
									};
																
									sensus.util.page.stopProgressBar();							
									sensus.widgets.datatable.clearSelects();

								}
								
							});
							
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
						
						sensus.widgets.datatable.reloadTable(sensus.pages.smartpoint.smartpointTable);

					}

				};
				objButtons[sensus.locale.get("action.removefromgroup.cancel")] = function() {
					
					$(this).dialog('close');
					$('.formError').remove();

				};
				
				return objButtons;
			})(),
			
			action : function(actionDialog) {
				
				$('#action-dialog').load(sensus.settings.removeFromGroupInclude, function() {
					
					$('.ui-dialog').find('.ui-dialog-titlebar-close').hide();
					
					actionDialog.removeClass('waiting');
					
					$('#group_add_select').combobox( {
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
		},
				
		/**
		 * Configuration for the "addTag" dialog.
		 */
		addTag : {


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

					var sTagName = $("#tag_add_select_input").val(),
						iTagId = '',
						isAllRows = sensus.widgets.datatable.isAllRows,
						bHasTag = false,
						aIds = [],
						sIdUrl = '';
						
					if (sTagName.length) {
						
						if ((sTagName.match(/^(?:(?:[a-z0-9#]*(?:(?:(?:\s?-\s?|'s\s?|[(?:s)]'\s?|\s)[a-z0-9#]*)?))\s?)*$/i) == null) || (sTagName.replace(/\s/g,'').length == 0)) {

							$('#tag_add_select_input').validationEngine('showPrompt', sensus.locale.get('commons.pages.specialCharacterInvalid'), 'red', '', true);

						} else {

							if (isAllRows) {
								aIds = sensus.widgets.datatable.deselectedRows;
							} else {

								aIds = sensus.widgets.datatable.selectedRows;

								if (aIds.length) {
									sIdUrl = "&smartpointIds=" + aIds.join("&smartpointIds=");
								} else {
									$("#add_tag_form .ui-autocomplete-input").validationEngine('showPrompt', sensus.locale.get("commons.pages.nosmartpoint"), 'red', '', true);
								}
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

							sensus.util.page.startProgressBar(sensus.locale.get("widgets.progress.addToTag"));

							if (bHasTag) {

								sensus.commons.modules.dialogSettings.addTagToSmartpoint(sTagName, iTagId, sIdUrl);
								
								sensus.widgets.datatable.clearSelects();
								$("#action-dialog").dialog('close');
								
							} else {

								if (sTagName.length <= 100) {

									$('#tag_add_select_input').val("");

									var iNewTagId = sensus.commons.modules.dialogSettings.createNewTag(sTagName);

									if (iNewTagId) {
										sensus.commons.modules.dialogSettings.addTagToSmartpoint(sTagName, iNewTagId, sIdUrl);
									}
									
									sensus.widgets.datatable.clearSelects();
									$("#action-dialog").dialog('close');

								} else {
									// Error Message
									$('#tag_add_select_input').validationEngine('showPrompt', sensus.locale.get('commons.pages.max.characterJs','100'), 'red', '', true);

								}

							}

							sensus.util.page.stopProgressBar();							

						}

					} else {
						// Error : Not Tag name informed
						$("#add_tag_form .ui-autocomplete-input").validationEngine('showPrompt', sensus.locale.get("validation.addtag.notag"), 'red', '', true);
					}

				}

				objButtons[sensus.locale.get("action.addtag.cancel")] = function() {
					$(this).dialog('close');
					$('.formError').remove();
				};

				return objButtons;

			})(),

			/**
			 * The function that will be called when the action dialog is launched.
			 * 
			 * @param actionDialog
			 *            a reference to the actionDialog objext
			 */
			action : function(actionDialog) {
				
				$('.ui-dialog').find('.ui-dialog-titlebar-close').hide();
				$('#action-dialog').load(sensus.settings.addTagInclude, function() {
					
					actionDialog.removeClass('waiting');
					$('#tag_add_select').combobox( {
						zIndex : 3999
					});

					$('.highlight', '.two-line').html(sensus.locale.get('action.addtag.label.smartpoints',$('#checked-count').html()));

				});

				$('#action-dialog').keypress(function(e) {

					if (e.keyCode == $.ui.keyCode.ENTER) {
						return false;
					}

				});

				actionDialog.addClass('waiting');
				actionDialog.dialog('open');
			}
		},


		/*
		 * Function: addTagToSmartpoint
		 * Purpose:  To add tag int the smartPoint(s)
		 * Returns:  string : Tag Name
	 	 *			 int : Tag Id 
	 	 *			 string: smartpoints id
		 * Inputs:   -
		 */
		addTagToSmartpoint : function(sTagName, iTagId, sIdUrl) {

			$.ajax({
				url : sensus.settings.addToTagUrl,
				type : 'POST',
				data : "tagId=" + iTagId + "&isAllRows="+sensus.widgets.datatable.isAllRows + sIdUrl + '&' + $.address.queryString(),
				success : function(data) {

					if (data.result === sensus.constants.ajax.ok) {
						sensus.util.page.showMessage("messaging-main", sensus.locale.get("smartpoint.actions.addtag.success", sTagName, $('#checked-count').text(), "1"), "confirm");
						$('.select-none').click();
					} else {
						sensus.util.page.showMessage("messaging-main", data.messages[0], "error");
					}
				}
			});

		},


		/*
		 * Function: createNewTag
		 * Purpose:  To Create a new tag
		 * Returns:  string : new Tag Name to be create
		 * Inputs:   int : id of the new tag
		 */
		createNewTag : function(newTagName) {

			var iTagId = null;

			$.ajax({
				url : sensus.settings.createTag,
				data : "tagName=" + newTagName,
				cache : false,
				async : false,
				success : function(data) {

					if (data.result === sensus.constants.ajax.ok) {
						
						iTagId = data.stringResult[0];
						sensus.util.page.showMessage("messaging-main", sensus.locale.get("tag.page.createdSuccess", newTagName), "confirm");
						
						// limit the size name
						var sLabel = newTagName;


						if (sLabel.length > 15) {
							sLabel = sLabel.substr(0,15) + '...';
						}


						// Append new tag in the filter Or in the combobox
						if ($(".ui-listcombobox li", "#tag").length < 6) {

							var newTagInFilter = '<li class="checkbox" title="' + newTagName + '"><input type="checkbox" value="' + data.stringResult[0] + '"> ' + sLabel + '</li>';
							$(newTagInFilter).appendTo("#tag .ui-listcombobox");

						} else {

							var newOptionInTagSelect = '<option value="' + data.stringResult[0] + '">' + newTagName + '</option>';
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
				}
			});
			
			return iTagId;
			
		},
		
		/**
		 * Configuration for the "removeTag" dialog.
		 */
		removeTag : {
			/**
			 * The dialog title.
			 */
			title : sensus.locale.get("action.removetag.title"),
			/**
			 * Whether this dialog requires a smartpoint list.
			 */
			requiresSmartpoints : true,
			/**
			 * The dialog buttons (submit and cancel).
			 */
			 
			close : function(actionDialog) {
				$('.ui-dialog').find('.ui-dialog-titlebar-close').show();
			},
			 
			buttons : (function() {
				
				var objButtons = {};
				objButtons[sensus.locale.get("action.removetag.submit")] = function() {
					
					var sTagName = $("#tag_remove_select_input").val();
					var iTagId = 0;
					
					$('#tag_remove_select option').each(function() {
					
						if ($(this).text() == sTagName) {
							
							iTagId = $(this).attr("value");
							
						}
					});
					
					var aIds = sensus.widgets.datatable.isAllRows ? sensus.widgets.datatable.deselectedRows : sensus.widgets.datatable.selectedRows;
					var sIdUrl = sensus.widgets.datatable.isAllRows && aIds.length <= 0 ? "" : "&smartpointIds=" + aIds.join("&smartpointIds=");
					
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
					
					if (!sNoTag && (sensus.widgets.datatable.isAllRows || aIds.length > 0) && bHasTag) {
						
						$('.formError').remove();
						
						
						/**
						* Start progress bar
						*/
						sensus.util.page.startProgressBar(sensus.locale.get("widgets.progress.removeTag"));
						
						$.ajax({
							url : sensus.settings.removeFromTagUrl,
							async: false,
							type : 'POST',
							data : "tagId=" + iTagId + "&isAllRows="+sensus.widgets.datatable.isAllRows + sIdUrl + '&' + $.address.queryString(),

							/**
							 * The success handler. This will be invoked if the HTTP request
							 * returned correctly. The status of the reuested operation needs to
							 * be determined from the response object.
							 */
							success : function(data) {
								
								/* If successful, close dialog and show message */
								
								if (data.result === sensus.constants.ajax.ok) {
									
									sensus.util.page.showMessage("messaging-main", sensus.locale.get("smartpoint.actions.removetag.success",sTagName,$('#checked-count').text(), "1"), "confirm");
									$('.select-none').click();
								} else {
									if(data.messages[0] === "[{0}] Device not tagged."){
										sensus.util.page.showMessage("messaging-main", sensus.locale.get("smartpoint.actions.removetag.success",sTagName,$('#checked-count').text(), "1"), "confirm");
									}else{
										sensus.util.page.showMessage("messaging-main", sensus.locale.get("commons.pages.error"), "error");
									}
								};
								
								sensus.util.page.stopProgressBar();							
								sensus.widgets.datatable.clearSelects();

							}
							/*
							 * At this point there is no error handler. We should consider entering
							 * one. It would take effect if the provided url is not available or the
							 * request returns any other HTTP error.
							 */
						});
						
						$("#action-dialog").dialog('close');
			
					} else {	
					
						if (sNoTag) {
							$("#remove_tag_form .ui-autocomplete-input").validationEngine('showPrompt', sensus.locale.get("validation.removetag.notag"), 'red', '', true);	
						} else if (!sensus.widgets.datatable.isAllRows || aIds.length < 0) {
							$("#remove_tag_form .ui-autocomplete-input").validationEngine('showPrompt', sensus.locale.get("commons.pages.nosmartpointRemove"), 'red', '', true);
						}
						
						sensus.widgets.datatable.clearSelects();
						return false;
					};

					sensus.widgets.datatable.reloadTable(sensus.pages.smartpoint.smartpointTable);

				};
				
				objButtons[sensus.locale.get("action.removetag.cancel")] = function() {
					
					$(this).dialog('close');
					$('.formError').remove();
			
				};
				
				return objButtons;
			})(),
			/**
			 * The function that will be called when the action dialog is launched.
			 * 
			 * @param actionDialog
			 *            a reference to the actionDialog objext
			 */
			action : function(actionDialog) {
				
				$('.ui-dialog').find('.ui-dialog-titlebar-close').hide();
				$('#action-dialog').load(sensus.settings.removeTagInclude, function() {
					
					actionDialog.removeClass('waiting');
					
					$('#tag_remove_select').combobox( {
						zIndex : 3999
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
		},
		
		/**
		 * Configuration for the "Apply Demand Reset" dialog.
		 */
		applyDemandReset : {

			// The dialog title.
			title : sensus.locale.get("action.applyeventschedule.title"),

			// Whether this dialog requires a smartpoint list.
			requiresSmartpoints : true,

			// The dialog buttons (submit and cancel).
			buttons : (function() {

				var objButtons = {};

				objButtons[sensus.locale.get("action.addtoschedule.submit")] = function() { 

					$("#add_schedule_select").combobox();

					var sScheduleName = $('#add_schedule_select_input').val();
					var iScheduleId = $('#add_schedule_select').attr('value');
					var aIds = sensus.widgets.datatable.isAllRows ? sensus.widgets.datatable.deselectedRows : sensus.widgets.datatable.selectedRows;
					var sIdUrl = sensus.widgets.datatable.isAllRows && aIds.length <= 0 ? "" : "&smartpointIds=" + aIds.join("&smartpointIds=");

					sensus.widgets.datatable.clearSelects();

					var sNoSchedule = sScheduleName.length == 0 || sScheduleName == sensus.locale.get("widgets.combobox.prompt2");

					if (!sNoSchedule && (sensus.widgets.datatable.isAllRows || aIds.length > 0)) {

						sensus.util.page.startProgressBar(sensus.locale.get("widgets.progress.applyDemandReset"));

						var sUrl = "scheduleId=" + iScheduleId + "&isAllRows="+sensus.widgets.datatable.isAllRows + sIdUrl + '&' + $.address.queryString();

						sensus.pages.longrunningprocess.monitor(sensus.settings.addToScheduleUrl, sUrl);

					} else {

						if (aIds.length == 0) {

							sensus.util.page.showMessage("messaging-main", sensus.locale.get("commons.pages.nosmartpoint"), "error");

						} else if (sNoSchedule) {

							sensus.util.page.showMessage("messaging-main", sensus.locale.get("validation.addtoschedule.noschedule"), "error");
							$("#add-group-form .ui-autocomplete-input").addClass("error");

						}

						sensus.util.selection.clearSelects();
						return false;
					};
				};
				
				objButtons[sensus.locale.get("action.addtoschedule.cancel")] = function() {
					$(this).dialog('close');
				};
				
				return objButtons;
			})(),
			/**
			 * The function that will be called when the action dialog is launched.
			 * 
			 * @param actionDialog
			 *            a reference to the actionDialog objext
			 */
			action : function(actionDialog) {
				
				$('#action-dialog').load(sensus.settings.addToScheduleInclude, {"actionTypeValue" : "0"}, function() {
					
					actionDialog.removeClass('waiting');
					
					$('#add_schedule_select').combobox( {
						zIndex : 3999
					});
					
					$("#add_schedule_select_input").attr("value", sensus.locale.get("widgets.combobox.prompt2"));
					$('.smartpoint-count').html($('#checked-count').html());
					$('.timezone').text(sensus.locale.get('smartpointdetail.tabs.about.timeZone')+' ('+sensus.settings.timezone+') ');
				});
				
				actionDialog.addClass('waiting');
				actionDialog.dialog('open');
			}
		},
		
		/**
		 * Configuration for the "Remove Demand Reset" dialog.
		 */
		removeDemandReset : {
			/**
			 * The dialog title.
			 */
			title : sensus.locale.get("action.removeschedule.title"),
			/**
			 * Whether this dialog requires a smartpoint list.
			 */
			requiresSmartpoints : true,
			/**
			 * The dialog buttons (submit and cancel).
			 */
			buttons : (function() {
				var objButtons = {};
				objButtons[sensus.locale.get("action.removefromschedule.submit")] = function() {
									
					$("#add_schedule_select").combobox();
					
					var sScheduleName = $('#add_schedule_select_input').val();
					var iScheduleId = $('#add_schedule_select').attr('value');
					var aIds = sensus.widgets.datatable.isAllRows ? sensus.widgets.datatable.deselectedRows : sensus.widgets.datatable.selectedRows;
					var sIdUrl = sensus.widgets.datatable.isAllRows && aIds.length <= 0 ? "" : "&smartpointIds=" + aIds.join("&smartpointIds=");
					
					sensus.widgets.datatable.clearSelects();
						
					var sNoSchedule = sScheduleName.length == 0 || sScheduleName == sensus.locale.get("widgets.combobox.prompt2");
					
					if (!sNoSchedule && (sensus.widgets.datatable.isAllRows || aIds.length > 0)) {
						
						sensus.util.page.startProgressBar(sensus.locale.get("widgets.progress.removeDemandReset"));
						
						var sUrl = "scheduleId=" + iScheduleId + "&isAllRows="+sensus.widgets.datatable.isAllRows + sIdUrl + '&' + $.address.queryString();
						
						sensus.pages.longrunningprocess.monitor(sensus.settings.removeFromScheduleUrl, sUrl);
						
					} else {
						
						if (aIds.length == 0) {
							
							sensus.util.page.showMessage("messaging-main", sensus.locale.get("commons.pages.nosmartpoint"), "error");
							
						} else if (sNoSchedule) {
							
							sensus.util.page.showMessage("messaging-main", sensus.locale.get("validation.addtoschedule.noschedule"), "error");
							$("#add-group-form .ui-autocomplete-input").addClass("error");
							
						}
						
						sensus.util.selection.clearSelects();
						return false;
					};
				};
				
				objButtons[sensus.locale.get("action.addtoschedule.cancel")] = function() {
					$(this).dialog('close');
				};
				
				return objButtons;
			})(),
			/**
			 * The function that will be called when the action dialog is launched.
			 * 
			 * @param actionDialog
			 *            a reference to the actionDialog objext
			 */
			action : function(actionDialog) {
				
				$('#action-dialog').load(sensus.settings.removeFromScheduleInclude, {"actionTypeValue" : "0"}, function() {
					
					actionDialog.removeClass('waiting');
					
					$('#add_schedule_select').combobox( {
						zIndex : 3999
					});
					
					$("#add_schedule_select_input").attr("value", sensus.locale.get("widgets.combobox.prompt2"));
					$('.smartpoint-count').html($('#checked-count').html());
				});
				
				actionDialog.addClass('waiting');
				actionDialog.dialog('open');
			}
		},
		
		/**
		 * Configuration for the "Apply Demand Reset" dialog.
		 */
		applyTou : {
			/**
			 * The dialog title.
			 */
			title : sensus.locale.get("action.applyeventschedule.title"),
			/**
			 * Whether this dialog requires a smartpoint list.
			 */
			requiresSmartpoints : true,
			/**
			 * The dialog buttons (submit and cancel).
			 */
			buttons : (function() {
				
				var objButtons = {};
				objButtons[sensus.locale.get("action.addtoschedule.submit")] = function() {
									
					$("#add_schedule_select").combobox();
					
					var sScheduleName = $('#add_schedule_select_input').val();
					var iScheduleId = $('#add_schedule_select').attr('value');
					var aIds = sensus.widgets.datatable.isAllRows ? sensus.widgets.datatable.deselectedRows : sensus.widgets.datatable.selectedRows;
					var sIdUrl = sensus.widgets.datatable.isAllRows && aIds.length <= 0 ? "" : "&smartpointIds=" + aIds.join("&smartpointIds=");
					
					sensus.widgets.datatable.clearSelects();
						
					var sNoSchedule = sScheduleName.length == 0 || sScheduleName == sensus.locale.get("widgets.combobox.prompt2");
					if (!sNoSchedule && (sensus.widgets.datatable.isAllRows || aIds.length > 0)) {
						
						sensus.util.page.startProgressBar(sensus.locale.get("widgets.progress.applyTou"));
						
						var sUrl = "scheduleId=" + iScheduleId + "&isAllRows="+sensus.widgets.datatable.isAllRows + sIdUrl + '&' + $.address.queryString();
						
						sensus.pages.longrunningprocess.monitor(sensus.settings.addToScheduleUrl, sUrl);
						
					} else {
						
						if (aIds.length == 0) {
							
							sensus.util.page.showMessage("messaging-main", sensus.locale.get("commons.pages.nosmartpoint"), "error");
							
						} else if (sNoSchedule) {
							
							sensus.util.page.showMessage("messaging-main", sensus.locale.get("validation.addtoschedule.noschedule"), "error");
							$("#add-group-form .ui-autocomplete-input").addClass("error");
							
						}
						
						sensus.util.selection.clearSelects();
						return false;
					};
				};
				
				objButtons[sensus.locale.get("action.addtoschedule.cancel")] = function() {
					$(this).dialog('close');
				};
				
				return objButtons;
			})(),
			/**
			 * The function that will be called when the action dialog is launched.
			 * 
			 * @param actionDialog
			 *            a reference to the actionDialog objext
			 */
			action : function(actionDialog) {
				
				$('#action-dialog').load(sensus.settings.addToScheduleInclude, {"actionTypeValue" : "1"}, function() {
					
					actionDialog.removeClass('waiting');
					
					$('#add_schedule_select').combobox( {
						zIndex : 3999
					});
					
					$("#add_schedule_select_input").attr("value", sensus.locale.get("widgets.combobox.prompt2"));
					$('.smartpoint-count').html($('#checked-count').html());
				
				});
				actionDialog.addClass('waiting');
				actionDialog.dialog('open');
			}
		}
		
}
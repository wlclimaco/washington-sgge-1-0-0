<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">

<script type="text/javascript">
	pgsi.pages.batches.dialogSettings = {

		changeBatchStatus : function (sTitle, sMessage, oMoneyTransferBatchStatusList, fnCallBackBatchStatus) {

			return {

				title : sTitle,
				width : 650,

				close : function () {},

				buttons : (function () {

					var oButtons = {};

					// Confirm Button
					oButtons[$.pgsi.locale.get("pages.batches.dialog.confirmation")] = {

						id : "dialog-button",

						text : $.pgsi.locale.get("pages.batches.dialog.confirmation"),

						click : function () {

							if(oMoneyTransferBatchStatusList[0].status == "PENDING_APPROVAL"){
								$("#dialogStatusBatchForm").find("textarea").removeClass('required');
							}
							var validator = $("#dialogStatusBatchForm").validate();

							if (validator.form()) {

								var sNoteText = $("#dialogStatusBatchForm").find("textarea").val();

								var oRequest = new MoneyTransferBatchStatusMaintenanceRequest({
									moneyTransferBatchStatusList : oMoneyTransferBatchStatusList,
									note : sNoteText
								});

								if (!$.pgsi.isNullOrUndefined(fnCallBackBatchStatus)) {
									var fnCallBack = fnCallBackBatchStatus;
								}

								else {

									var fnCallBack = function(oResponse) {

										if ($.pgsi.isNullOrUndefined(oResponse)) {
											return false;
										}

										if (oResponse.operationSuccess == true) {
											 // Validations for change pagination when delete one or more groups of last page.
											var iStart;
											var oSettings = pgsi.pages.batches.batchTable.fnSettings();
											 // If exist just one group at last page and this group is deleted, the pagination back to previous page.
											if (((oSettings._iRecordsDisplay - 1) % $('.dataTables_length').find('select').val() === 0))
											{
												iStart = (oSettings._iRecordsDisplay - 1) - oSettings._iDisplayLength;
											}

											$.pgsi.table.reloadTable({
												table 		: pgsi.pages.batches.batchTable,
												iStart 		: iStart
											});
										}

										else{
											pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title","Batch"),true);
										}
									};
								}

								$.pgsi.ajax.post({
									 sUrl 		: 'api/moneyTransferBatchStatus/insert',
									 oRequest 	: oRequest,
									 fnCallback : fnCallBack
								});

								$(this).dialog('close');
							}
						}

					};

					// Cancel Button
					oButtons[$.pgsi.locale.get("pages.batches.dialog.cancel")] = function() {
						$(this).dialog('close');
					};

					return oButtons;

				})(),

				action : function (actionDialog) {

					actionDialog.load("batch/dialogInsertStatus?userId=" + pgsi.settings.userContext.userId, function() {

						actionDialog.dialog('open');
						// Set message text
						if (!$.pgsi.isNullOrUndefined(sMessage)) {
							$("#dialog-status-batch").find(".alert").removeClass('hide').find(".label").text(sMessage);
						}
					});
				}
			}
		},

		changeMoneyTransferStatus : function (sTitle, sMessage, oMoneyTransferStatusList, fnCallback) {
			return {

				title : sTitle,
				width : 650,

				close : function () {},

				buttons : (function () {

					var oButtons = {};

					// Confirm Button
					oButtons[$.pgsi.locale.get("pages.batches.dialog.confirmation")] = {

						id : "dialog-submit",

						text : $.pgsi.locale.get("pages.batches.dialog.confirmation"),

						click : function() {

							if(oMoneyTransferStatusList[0].status =="APPROVED"){
								$('#dialogStatusBatchForm textarea').removeClass('required');
							}
							var validator = $("#dialogStatusBatchForm").validate();
							if (validator.form()) {

								var sNoteText = $("#dialogStatusBatchForm").find("textarea").val();

								var oRequest = new MoneyTransferStatusMaintenanceRequest({
									moneyTransferStatusList : oMoneyTransferStatusList,
									note : sNoteText
								});

								$.pgsi.ajax.post({
									 sUrl 		: 'api/moneyTransferStatus/insert',
									 oRequest 	: oRequest,
									 fnCallback : fnCallback
								});

								$(this).dialog('close');
							}
						}

					};

					// No Button
					oButtons[$.pgsi.locale.get("pages.batches.dialog.cancel")] = function() {

						$(this).dialog('close');
					};

					return oButtons;

				})(),

				action : function (actionDialog) {

					actionDialog.load("batch/dialogInsertStatus?userId=" + pgsi.settings.userContext.userId, function() {

						actionDialog.dialog('open');
						// Set message text
						if (!$.pgsi.isNullOrUndefined(sMessage)) {
							$("#dialog-status-batch").find(".alert").find(".label").text(sMessage);
						}else{
							$("#dialog-status-batch").find(".alert").hide();
						}
					});
				}
			}
		},

		reviewSpreads : function(sTitle, iBatchId,bView) {

			return {

				title : sTitle,
				width : 722,

				close : function () {},

				buttons : (function () {

					var oButtons = {};

					// Close Button
					oButtons[$.pgsi.locale.get("commons.pages.close")] = function () {
						$(this).dialog('close');
					};

					// Save Button
					oButtons[$.pgsi.locale.get("commons.pages.save")] = function() {

						// Get selected rows
						var aMoneyTransfersSelected = pgsi.pages.payments.spreads.table.fnGetSelectedRows();
			    		if (aMoneyTransfersSelected.length === 0) {
			    			return false;
			    		}
			    		var aMoneyTransfers = pgsi.pages.payments.spreads.table.fnMatchRows(aMoneyTransfersSelected, aaData);
			    		var oMoneyTransfer = new Object;
			    		var oRequest;
			    		oRequest = new MoneyTransferMaintenanceRequest({
			    			moneyTransferList : aMoneyTransfers
			    		});

			    		// Update Money Transfers, with new Spread Info
			    		$.pgsi.ajax.post({
							sUrl : "api/payment/updateMoneyTransfer",
							oRequest : oRequest,
							fnCallback : function(oResponse) {
								if (oResponse.operationSuccess == true){
									// Close dialog
									$("#action-dialog").dialog('close');
								}
							}
						});
					};

					// Approve Spreads
					oButtons[$.pgsi.locale.get("pages.batches.buttons.approveSpreads")] = function() {

						// Get selected rows
						var aMoneyTransfersSelected = pgsi.pages.payments.spreads.table.fnGetSelectedRows();
			    		if (aMoneyTransfersSelected.length === 0) {
			    			return false;
			    		}
			    		var aMoneyTransfers = pgsi.pages.payments.spreads.table.fnMatchRows(aMoneyTransfersSelected, aaData);
			    		var oMoneyTransfer = new Object;
			    		var oRequest;
			    		oRequest = new MoneyTransferMaintenanceRequest({
			    			moneyTransferList : aMoneyTransfers
			    		});

			    		// Update Money Transfers, with new Spread Info
			    		$.pgsi.ajax.post({
							sUrl : "api/payment/updateMoneyTransfer",
							oRequest : oRequest,
							fnCallback : function(oResponse) {
								if (oResponse.operationSuccess == true){

									// Insert new batch status
									var oMoneyTransferBatchStatus = new MoneyTransferBatchStatus({
										id : null,
										moneyTransferBatchId : iBatchId,
										actionDueDate : (new Date()).getTime(),
										status : "READY_FOR_RELEASE",
										modelAction : "INSERT"
									});

									var oRequest = new MoneyTransferBatchStatusMaintenanceRequest({
										moneyTransferBatchStatusList : [oMoneyTransferBatchStatus]
									});

									$.pgsi.ajax.post({
										 sUrl 		: 'api/moneyTransferBatchStatus/insert',
										 oRequest 	: oRequest,
										 fnCallback : function(oResponse){
										 	if (oResponse.operationSuccess == true) {

												if(bView){
													pgsi.pages.batches.view.refresh()
												}else{
													$.pgsi.table.reloadTable({
														table 		: pgsi.pages.batches.batchTable,
														iStart 		: 0
													});
												}
												// Close dialog
												$("#action-dialog").dialog('close');
										 	}
										}
									});
								}
							}
						});
					};

					// Approve and Release
					oButtons[$.pgsi.locale.get("pages.batches.buttons.approveRelease")] = function() {
							// Get selected rows
						var aMoneyTransfersSelected = pgsi.pages.payments.spreads.table.fnGetSelectedRows();
			    		if (aMoneyTransfersSelected.length === 0) {
			    			return false;
			    		}
			    		var aMoneyTransfers = pgsi.pages.payments.spreads.table.fnMatchRows(aMoneyTransfersSelected, aaData);
			    		var oMoneyTransfer = new Object;
			    		var oRequest;
			    		oRequest = new MoneyTransferMaintenanceRequest({
			    			moneyTransferList : aMoneyTransfers
			    		});

			    		// Update Money Transfers, with new Spread Info
			    		$.pgsi.ajax.post({
							sUrl : "api/payment/updateMoneyTransfer",
							oRequest : oRequest,
							fnCallback : function(oResponse) {
								if (oResponse.operationSuccess == true){

									// Insert new batch status
									var oMoneyTransferBatchStatus = new MoneyTransferBatchStatus({
										id : null,
										moneyTransferBatchId : iBatchId,
										actionDueDate : (new Date()).getTime(),
										status : "RELEASED",
										modelAction : "INSERT"
									});

									var oRequest = new MoneyTransferBatchStatusMaintenanceRequest({
										moneyTransferBatchStatusList : [oMoneyTransferBatchStatus]
									});

									$.pgsi.ajax.post({
										 sUrl 		: 'api/moneyTransferBatchStatus/insert',
										 oRequest 	: oRequest,
										 fnCallback : function(oResponse) {
										 	if (oResponse.operationSuccess == true) {
										 		// Reload Table
												$.pgsi.table.reloadTable({
													table 		: pgsi.pages.batches.batchTable,
													iStart 		: 0
												});

												// Close dialog
												$("#action-dialog").dialog('close');
										 	}
										}
									});
								}
							}
						});
					};

					return oButtons;

				})(),

				action : function (actionDialog) {

					actionDialog.load("payment/spreadsView?batchesId="+iBatchId+"&userId=" + pgsi.settings.userContext.userId, function() {

						actionDialog.dialog('open');

					});
				}
			}
		},

		updateDateBatchTransaction : function (iId, sStatus, sTitle, sMessage, fnCallBackBatchStatus) {

			return {

				title : sTitle,
				width : 650,

				close : function () {},

				buttons : (function () {

					var oButtons = {};

					// Cancel Button
					oButtons[$.pgsi.locale.get("commons.pages.cancel")] = function() {

						$(this).dialog('close');
					};

					// Confirm Button
					oButtons[$.pgsi.locale.get("commons.pages.save")] = {


						id : "dialog-submit",

						text : $.pgsi.locale.get("commons.pages.save"),

						click : function () {
							var bValidForm = pgsi.pages.batches.view.validator.form();

							if (bValidForm) {

								dDate = new Date($('#date').val());

								$.pgsi.ajax.post({
									 sUrl 		: 'api/payment/update',
									 oRequest 	: new MoneyTransferBatchMaintenanceRequest({
									 	moneyTransferBatch : {
									 		id: parseInt($('#batch-id').val(),10),
									 		modelAction : "UPDATE",
									 		transferDate : dDate.getTime(),
									 		location : {
									 			id : parseInt($('#location-id').val(),10)
									 		},
									 		version : parseInt($('#version-number').val(),10)
									 	}
									 }),
									 fnCallback : fnCallBackBatchStatus
								});

								$(this).dialog('close');
							}
						}

					};

					return oButtons;

				})(),

				action : function (actionDialog) {

					actionDialog.load("batch/updateDateTransactionDialog?userId=" + pgsi.settings.userContext.userId, function() {

						actionDialog.dialog('open');

					});
				}
			}
		},

		reactiveBatch : function (iId, sStatus, sTitle, sMessage, fnCallBackBatchStatus) {

			return {

				title : sTitle,
				width : 650,

				close : function () {},

				buttons : (function () {

					var oButtons = {};

					// Cancel Button
					oButtons[$.pgsi.locale.get("commons.pages.cancel")] = function() {

						$(this).dialog('close');
					};

					// Confirm Button
					oButtons[$.pgsi.locale.get("commons.pages.save")] = {

						id : "dialog-submit",

						text : $.pgsi.locale.get("commons.pages.save"),

						click : function() {
							var bValidForm = pgsi.pages.batches.view.validator.form();

							if (bValidForm) {

								var oMoneyTransferBatchStatusList = new Array();
								var oMoneyTransferBatchStatus;
								var oSettings = new Array();
								var iTime = (new Date($('#date').val())).getTime();
								var sNoteText = $("#dialogStatusBatchForm").find("textarea").val();


								if ($.pgsi.isNullOrUndefined(iId)) {
									oSettings = $.pgsi.table.checkbox(pgsi.pages.batches.batchTable).selected();
								}

								else {
									oSettings.push(iId);
								}

								for (var i = 0; i < oSettings.length; i++) {
									oMoneyTransferBatchStatus = new MoneyTransferBatchStatus({
										id : null,
										moneyTransferBatchId : oSettings[i],
										actionDueDate : iTime,
										status : sStatus,
										modelAction : "INSERT"
									});
									oMoneyTransferBatchStatusList.push(oMoneyTransferBatchStatus);
								}

								var oRequest = new MoneyTransferBatchStatusMaintenanceRequest({
									moneyTransferBatchStatusList : oMoneyTransferBatchStatusList,
									note : sNoteText
								});

								$.pgsi.ajax.post({
									 sUrl 		: 'api/moneyTransferBatchStatus/insert',
									 oRequest 	: oRequest,
									 fnCallback : fnCallBackBatchStatus
								});

								$(this).dialog('close');
							}
						}

					};

					return oButtons;

				})(),

				action : function (actionDialog) {

					actionDialog.load("batch/updateDateTransactionDialog?userId=" + pgsi.settings.userContext.userId, function() {

						actionDialog.dialog('open');

						// Set message text
						$(".updateDate").addClass('hide');
						$(".reactive").removeClass('hide')
						$("#batch-date-transaction").find(".alert").find(".label").text(sMessage);
					});
				}
			}
		},

		createNewBatchFromSelection : function (sTitle, oMoneyTransferBatch) {

			return {

				title : sTitle,
				width : 650,

				close : function () {},

				buttons : (function () {

					var oButtons = {};

					// Confirm Button
					oButtons[$.pgsi.locale.get("pages.batches.dialog.confirmation")] = {

						id : "dialog-submit",

						text : $.pgsi.locale.get("pages.batches.dialog.confirmation"),

						click : function () {
							$.pgsi.ajax.post({
								 sUrl 		: 'api/payment/createNewFromSelection',
								 oRequest 	: { moneyTransferBatch : oMoneyTransferBatch },
								 fnCallback : function(oResponse) {
								 	var iId = oResponse.moneyTransferBatchList[0].id;

								 	if ($.pgsi.isNullOrUndefined(oResponse)) {
										return false;
									}

									if (oResponse.operationSuccess == true) {
										$.pgsi.pageLoader.load({
											url : "payment/batches_view?batchesId="+iId,
											$content : $("#load")
										});
									}
								}
							});
						}

					};

					// Cancel Button
					oButtons[$.pgsi.locale.get("pages.batches.dialog.cancel")] = function() {

						$(this).dialog('close');
					};

					return oButtons;

				})(),

				action : function (actionDialog) {

					actionDialog.load("batch/dialogInsertStatus?userId=" + pgsi.settings.userContext.userId, function() {
						$('#dialog-status-batch').find('textarea').remove();
						$('#dialog-status-batch').find('.label.first').remove();
						$("#dialog-status-batch").find(".alert").find(".label").text($.pgsi.locale.get('pages.batches.dialog.createNewBatchFromSelection.message'));
						actionDialog.dialog('open');
					});
				}
			}
		},

		createOneOffTransaction : function (oTransferSetting, oMember) {

			return {

				title : $.pgsi.locale.get('pages.batches.dialog.createOneOffTransaction.confirmation'),
				width : 970,

				close : function () {},

				buttons : (function () {

					var oButtons = {};

					// Cancel Button
					oButtons[$.pgsi.locale.get("commons.pages.cancel")] = function() {
						$(this).dialog('close');
					};

					// Confirm Button
					oButtons[$.pgsi.locale.get("pages.batches.dialog.confirmation")] = {

						id : "dialog-submit",

						text : $.pgsi.locale.get("pages.batches.dialog.createOneOffTransaction.confirmation"),

						click : function () {

							var validator = $("#dialog-one-off-form").validate();
							var bValidForm = validator.form();

							if (!bValidForm) {
								return;
							}

							var oCurrentDate = new Date();
							var sDateString = oCurrentDate.getFullYear() + "-" + (oCurrentDate.getMonth() + 1) + "-" + oCurrentDate.getDate();
							var iCurrentDate = $.pgsi.date.getEpochTime(sDateString, "Etc/GMT");
							var oMoneyTransferBatchCreateRequest;
							var oMemberMaintenanceRequest;
							var oMoneyTransferBatch;
							var oMoneyTransferCreateRequest;
							var fnCallback1, fnCallback2;
							var paymentPreparationDate = new FrequencyBasedEventCalendar({
								eventDate : iCurrentDate
							});
							var payDate = new FrequencyBasedEventCalendar({
								eventDate : iCurrentDate
							});

							var oLocation = oLocationResponse.locationList[0];
							oLocation = new Location({
								id : oLocation.id,
								fundsTransferDayLimit : oLocation.fundsTransferDayLimit,
								batchApprovalDayLimit : oLocation.batchApprovalDayLimit
							});

							// 2º step insert new TransferSetting. Basically a copy from the one selected plus:
							// TransferTypeEnum.ONE_TIME
							// EffectiveStartDate and EffectiveEndDate == current local date, utc format
							fnCallback1 = function(oResponse){

								if (oResponse.operationSuccess) {
									oMoneyTransferBatch = oResponse.moneyTransferBatchList[0];
									delete oMoneyTransferBatch.currentStatus;
									oTransferSetting = pgsi.pages.transaction.form.fnFillTransferSetting(oTransferSetting);
									oTransferSetting.effectiveStartDate = iCurrentDate;
									oTransferSetting.effectiveEndDate = iCurrentDate;
									oTransferSetting.transferType = "ONE_TIME";
									oTransferSetting.transferTypeValue = 2;

									// 3º Insert MoneyTransfer with the batch and the transferSetting previous inserted
									fnCallback2 = function(oResponse) {

										if (oResponse.operationSuccess) {

                                            oTransferSetting = oResponse.memberList[0].transferSettingList[0];

											$.pgsi.ajax.post({
												sUrl : "api/payment/insertMoneyTransfer",
												oRequest : new MoneyTransferCreateRequest({
													moneyTransferBatch : new MoneyTransferBatch({
														id : oMoneyTransferBatch.id,
														payPreparationDate : oMoneyTransferBatch.payPreparationDate
													}),
													transferSetting : oTransferSetting
												}),
												fnCallback : function(oResponse){
													if (oResponse.operationSuccess) {

														$("#action-dialog").dialog('close');

														$.pgsi.ajax.post({
															sUrl       : "api/member/fetch",
															oRequest   : { id : $("#business-id").val() },

															fnCallback : function(oResponse) {
																pgsi.pages.member.view.fnFillFields(oResponse);
															}
														});
													}

													else {
														pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),false);
													}

												}
											});
										}

										else {
											pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),false);
										}
									};


									oMemberMaintenanceRequest = new MemberMaintenanceRequest({
										member : {
											id              : oMember.id,
											personTypeValue   : oMember.personTypeValue,
											pepStatusValue    : oMember.pepStatusValue,
											personStatusValue : oMember.personStatusValue,
											modelAction 	  : "NONE",
											risk : { modelAction: "NONE" },
											version 		  : oMember.version,
											transferSettingList : [
												oTransferSetting
											]
										}
									});

									$.pgsi.ajax.post({
										sUrl : "api/member/update",
										oRequest : oMemberMaintenanceRequest,
										fnCallback : fnCallback2
									});

								}

								else{
									pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
								}
							};

							// 1º step insert new MoneyTransferBatch or retrieved selected batch from UI
							if ($('#new-batch').prop('checked')) {

								oMoneyTransferBatchCreateRequest	=  new MoneyTransferBatchCreateRequest({
									location : oLocation,
									paymentPreparationDate : paymentPreparationDate,
									payDate : payDate
								});

								$.pgsi.ajax.post({
									sUrl : "api/payment/insertMoneyTransferBatch",
									oRequest : oMoneyTransferBatchCreateRequest,
									fnCallback : fnCallback1
								});
							}


							else {
								var iIndex = parseInt($('#dialog-one-off').find('input[name=batch]:checked').val());
								oMoneyTransferBatch = oBatchResponse.moneyTransferBatchList[iIndex];
								delete oMoneyTransferBatch.currentStatus;

								fnCallback1({
									operationSuccess : true,
									moneyTransferBatchList : [
										oMoneyTransferBatch
									]
								});
							}

						}

					};

					return oButtons;

				})(),

				action : function (actionDialog) {
					actionDialog.load("payment/createOneOffTransaction?locationId=" + oTransferSetting.employmentInfo.locationId + "&userId=" + pgsi.settings.userContext.userId, function() {
						actionDialog.dialog('open');
					});
				}
			}
		}
	}
</script>

</sec:authorize>
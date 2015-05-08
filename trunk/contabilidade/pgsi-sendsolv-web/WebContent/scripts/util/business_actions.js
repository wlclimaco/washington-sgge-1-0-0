/**
 * @namespace pgsi.util.page
 * @fileoverview Defines the settings for the dialogs used by the "Business" entities
 * @author Flavio Tosta, Washington Costa
 */

/**
 * The main namespace for page-related functionality.
 */

pgsi.pages.business.dialogSettings = {

	//========================= DIALOG EDIT ORGANIZATION ============================
	insUpdBusiness : function (iId, sTitle, nBusinessType) {
			var sPage;
			var sAPIRequest;
			var sFetchRequest;
			var fnAjaxCall;
			var fnCallback;

			// Checks for Business Type
			if (nBusinessType === 1) {
				sPage = "organization/editView?organizationId=";
				sAPIRequest = "api/organization/edit";
				sUrl = "api/organization/fetch";
			}

			else {

				if (nBusinessType === 2) {
					sPage = "location/editView?locationId=";
					sAPIRequest = "api/location/edit";
					sUrl = "api/location/fetch";
				}
			}

			return {
				title : $.pgsi.locale.get("pages.business.dialog.title.edit", sTitle),
				width : 1120,

				close : function () {
				},

				buttons : (function () {

					var oButtons = {};

					// Confirm Button
					oButtons[$.pgsi.locale.get("commons.pages.save")] = {

						click : function() {
							var bValidForm;

							pgsi.util.page.business.form.maskFields.fnUnmask();

							if (nBusinessType === 1) {
								bValidForm =  pgsi.pages.organization.form.validator.form();
							}

							else {
								bValidForm = pgsi.pages.location.form.validator.form();
							}

							if (bValidForm) {

								var oBusinessRequest;
								var oRequest = {id:iId};

								// Checks for Business Type
								if (nBusinessType === 1) {

									oBusinessRequest = pgsi.pages.organization.form.fnFillRequestObject("UPDATE");

									var fnFillCallBack = function(oResponse) {
										pgsi.pages.organization.view.fnFillOrganization(oResponse)
										$("#action-dialog").dialog('close');
										$.pgsi.progressBar.stopGlobal();
										$.pgsi.progressBar.stop();
									},

									fnCallbackById = function(oResponse) {
										if (oResponse.operationSuccess == true){
											$.pgsi.ajax.post({
												sUrl 		: sUrl,
												oRequest 	: oRequest,
												fnCallback : fnFillCallBack
											});

										}

										else {
											fnCallBackError = function(oResponse) {
												pgsi.version.versionBusiness = oResponse.organizationList[0].version;
												pgsi.pages.organization.form.fnFillOrganization(oResponse);
												pgsi.pages.organization.form.displayOrganizationFields();
												$("#action-dialog-Error").dialog('close');
											}
											pgsi.pages.sendsolv.fnDialogMessageError(sUrl,oRequest,oResponse,fnCallBackError,$.pgsi.locale.get("commons.dialog.error.title","Organization"),false);
											$.pgsi.progressBar.stop();
										}
										$.pgsi.progressBar.stop();
									}
								}

								else {
									oBusinessRequest = pgsi.pages.location.form.fnFillRequestObject("UPDATE");
									fnCallbackById = function(oResponse){
										if(oResponse.operationSuccess == true){
											$.pgsi.ajax.post({
												sUrl 		: sUrl,
												oRequest 	: oRequest,
												fnCallback : function(oResponse) {
															if(oResponse.operationSuccess == true){
																pgsi.pages.location.view.fnFillLocation(oResponse);
																$("#action-dialog").dialog('close');
															}else{
																pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
															}
												}
											});
										}else{
											fnCallBackError = function(oResponse) {
												pgsi.version.versionBusiness = oResponse.locationList[0].version;
												pgsi.pages.location.form.fnFillLocation(oResponse);
												pgsi.pages.location.form.displayLocationFields();
												$("#action-dialog-Error").dialog('close');
											}
											pgsi.pages.sendsolv.fnDialogMessageError(sUrl,oRequest,oResponse,fnCallBackError,$.pgsi.locale.get("commons.dialog.error.title","Location"),false);

										}
										$.pgsi.progressBar.stop();
									}
								}

								$.pgsi.ajax.post({
							 			sUrl 		: sAPIRequest,
							 			oRequest 	: oBusinessRequest,
							 			fnCallback : fnCallbackById
								});

							}
							else {
								pgsi.util.page.business.form.maskFields.fnMask();
								return false;
							}
						},

						id : "dialog-submit",

						text :  $.pgsi.locale.get("commons.pages.save")
					};

					// Cancel Button
					oButtons[$.pgsi.locale.get("pages.dialog.cancel")] = function() {

						if (nBusinessType === 1) {
							// Set current object to null
							pgsi.pages.organization.oOrganization = null;
						}

						else {
							// Set current object to null
							pgsi.pages.organization.oLocation = null;
						}

						$("#action-dialog").dialog('close');
					};

					return oButtons;
				})(),

				action : function (actionDialog) {

					actionDialog.load(sPage+""+iId+"&userId=" + pgsi.settings.userContext.userId, function() {
						actionDialog.dialog('open');
						pgsi.pages.business.dialog.fnLoadDialog(nBusinessType);

					});
				}
			}
	},
	//========================= DIALOG EDIT LOCATION ============================
	insUpdContact : function (iId, sTitle, oBusinessParent) {

		return {
			title : sTitle,
			width : 1130,

			buttons : (function () {

				var oButtons = {};

				// Cancel Button
				oButtons[$.pgsi.locale.get("commons.pages.cancel")] = function() {

					$("#action-dialog").dialog('close');
				};

				// Confirm Button
				oButtons[$.pgsi.locale.get("commons.pages.save")] = {

					id : "dialog-submit",

					text : $.pgsi.locale.get("commons.pages.save"),

					click : function() {
						var fnFillCallBack = function(oResponse) {

							$("#action-dialog").dialog('close');
							pgsi.pages.contact.form.fnAjaxCallFetchAll(oBusinessParent);
						}

						pgsi.pages.contact.form.fnAjaxCallInsertUpdateContact(fnFillCallBack, pgsi.pages.contact.oContact);
					}

				};

				// Add another contact
				oButtons[$.pgsi.locale.get("pages.contacts.button.save.contact")] = function() {

					var fnFillCallBack = function(oResponse) {

						$("#action-dialog").load("contact/update?contactId="+0);

						// Setting title to "Add new"
						var sTitle = $.pgsi.locale.get("pages.contacts.dialog.note.title.add", $('#company-name-field').text());
						$("#action-dialog").prev('.ui-dialog-titlebar').find(".ui-dialog-title").text(sTitle);

						// Fetch and fill Contacts section
						pgsi.pages.contact.form.fnAjaxCallFetchAll(oBusinessParent);

					}

					pgsi.pages.contact.form.fnAjaxCallInsertUpdateContact(fnFillCallBack, pgsi.pages.contact.oContact);
				};

				return oButtons;
			})(),

			action : function (actionDialog) {

				actionDialog.load("contact/update?contactId="+iId+"&userId=" + pgsi.settings.userContext.userId, function() {
					actionDialog.dialog('open');
					$("#country-button").outerWidth(255);
				});
			}
		}
	},

	//========================= DIALOG EDIT DOCUMENTS ============================
	insUpdDocuments : function (iId,sTitle,sUrl,oRequestDocument,fnCallBack) {

		var iWidth;

		if (oRequestDocument.document.parentKeyValue === 3) {
			iWidth = 500;
		}

		else {
			iWidth = 900;
		}

		return {
			title : sTitle,
			width : iWidth,

			close : function () {

			},

			buttons : (function () {

				var oButtons = {};

				// Confirm Button
				oButtons[$.pgsi.locale.get("commons.pages.save")] = {

					id : "dialog-submit",

					text: $.pgsi.locale.get("commons.pages.save"),

					click : function () {
						// BussinessType Organization
						if (oRequestDocument.document.parentKeyValue == 1) {
							pgsi.pages.document.form.fnAjaxCallInsertUpdateDocument(sUrl,oRequestDocument,iId);
						}

						// BusinessType Member
						else if (oRequestDocument.document.parentKeyValue == 3) {
							pgsi.pages.identification.form.fnAjaxCallInsertUpdate(sUrl, oRequestDocument, iId);
						}
					}
				};

				// Cancel Button
				oButtons[$.pgsi.locale.get("pages.dialog.cancel")] = function() {
					$("#action-dialog").dialog('close');
				};

				return oButtons;

			})(),

			action : function (actionDialog) {

				var iBusinessType = parseInt($('#business-type').val());
				if ($.pgsi.isNullOrUndefined(iBusinessType)) {
					oRequestDocument.document.parentKeyValue ;
				}

				actionDialog.load("document/edit?parentKeyType="+ iBusinessType +"&userId=" + pgsi.settings.userContext.userId, function() {
					actionDialog.dialog('open');

					// BussinessType Organization
					if (oRequestDocument.document.parentKeyValue === 1) {
						pgsi.pages.document.form.fnInitForm();
						pgsi.pages.document.form.fnFillFields(oRequestDocument);

					}

					// BusinessType Member
					else if (oRequestDocument.document.parentKeyValue === 3) {
						pgsi.pages.identification.form.setFieldSizes();
						pgsi.pages.identification.form.fnInitForm();
						pgsi.pages.identification.form.fnFillFields(oRequestDocument);
					}

				});
			}
		}
	},

	//========================= DIALOG EDIT RISk ============================
	insUpdRisk : function (iId, sTitle, oRequest) {

		return {
			title : $.pgsi.locale.get("pages.business.dialog.risk.title.edit", sTitle),

			width : 637,

			close : function () {

			},

			buttons : (function () {

				var oButtons = {};

				// Saved Button
				oButtons[$.pgsi.locale.get("commons.pages.save")] = {

					id : "dialog-submit",

					click : function () {
						$('#pep-dialog-level-value').removeClass('required');

						if (pgsi.pages.risk.form.validator.form()) {
							var oRisk = pgsi.pages.risk.form.fillObject(pgsi.version.versionBusiness);

							var oRequest = new RiskMaintenanceRequest({risk : oRisk});

							var fnCallBackRisk = function(oResponse) {

								if (oRisk.parentKeyType === 1) {
									sPurl = "api/organization/fetch";
								}

								else if (oRisk.parentKeyType === 2) {
									sPurl = "api/location/fetch";
								}

								if (oResponse.operationSuccess == true) {

									$.pgsi.ajax.post({
										 sUrl       : sPurl,
										 oRequest   : {id:iId},
										 fnCallback : function(oResponse) {
											if (oRisk.parentKeyType === 1) {
												pgsi.pages.organization.view.fnFillOrganization(oResponse);
												pgsi.version.versionBusiness = oResponse.organizationList[0].version;
											}

											else if (oRisk.parentKeyType === 2) {
												pgsi.pages.location.view.fnFillLocation(oResponse);
												pgsi.version.versionBusiness = oResponse.locationList[0].version;
											}
											$.pgsi.progressBar.stop();
										}
									});
									$("#action-dialog").dialog('close');
								}

								else {
									fnCallBackErrorRisk = function(oResponse) {
										if (oRisk.parentKeyType === 1) {
											pgsi.version.versionBusiness = oResponse.organizationList[0].version;
											pgsi.pages.organization.view.fnFillOrganization(oResponse);
											pgsi.pages.organization.view.displayOrganizationFields();
											$("#action-dialog-Error").dialog('close');
										}

										else if (oRisk.parentKeyType === 2) {
											pgsi.version.versionBusiness = oResponse.locationList[0].version;
											pgsi.pages.location.view.fnFillLocation(oResponse);
											pgsi.pages.location.view.displayLocationFields();
											$("#action-dialog-Error").dialog('close');
										}
										$.pgsi.progressBar.stop();
									}
									pgsi.pages.sendsolv.fnDialogMessageError(sPurl,{id:iId},oResponse,fnCallBackErrorRisk,$.pgsi.locale.get("commons.dialog.error.title","Location"),false);
								}

							}

							pgsi.pages.risk.form.fnSavedRisk(oRequest,fnCallBackRisk);
						}
					},

					text : $.pgsi.locale.get("commons.pages.save")
				};

				// Cancel Button
				oButtons[$.pgsi.locale.get("pages.dialog.cancel")] = function() {
					$("#action-dialog").dialog('close');
				};

				return oButtons;
			})(),

			action : function (actionDialog) {

				actionDialog.load("risk/risk_create?userId=" + pgsi.settings.userContext.userId, function() {

					actionDialog.dialog('open');

					pgsi.pages.risk.form.fnInitForm();
					pgsi.pages.risk.form.fillFormFields(oRequest.risk);


				});
			}
		}
	},

	insUpdMemberRisk  : function (sTitle, oRequest) {

		return {
			title : $.pgsi.locale.get("pages.business.dialog.risk.title.edit", sTitle),
			width : 637,
			buttons : (function () {

				var oButtons = {};

				oButtons[$.pgsi.locale.get("commons.pages.save")] = function () {

					if (pgsi.pages.risk.form.validator.form()) {
						oRequest.member.risk = pgsi.pages.risk.form.fillObject();

						if ($('#pep-dialog-level-value').length > 0 && !$.pgsi.isNullOrUndefined($('#pep-dialog-level-value').val())){
							oRequest.member.pepStatusValue = parseInt($('#pep-dialog-level-value').val(),10);
						}

						var fnCallBackRisk = function(oResponse) {

							sPurl = "api/member/fetch";

							if (oResponse.operationSuccess == true) {

								$.pgsi.ajax.post({
									 sUrl       : sPurl,
									 oRequest   : { id : oRequest.member.id },

									 fnCallback : function(oResponse) {
										pgsi.pages.risk.view.fillFields(oResponse.memberList[0]);
										pgsi.version.versionMember = oResponse.memberList[0].version;
									}
								});
								$("#action-dialog").dialog('close');
								$.pgsi.progressBar.stop();
							}

							else {
								fnCallBackErrorRisk = function(oResponse) {

									pgsi.version.versionMember = oResponse.memberList[0].version;
									oRequest.member.version = pgsi.version.versionMember;
									pgsi.pages.member.view.fnFillFields(oResponse);
									pgsi.pages.risk.form.fillFormFields(oResponse.recipientList[0].risk);
									$("#action-dialog-Error").dialog('close');
									$.pgsi.progressBar.stop();

								}

								pgsi.pages.sendsolv.fnDialogMessageError(sPurl,{id:oRequest.member.id},oResponse,fnCallBackErrorRisk,$.pgsi.locale.get("commons.dialog.error.title",$.pgsi.locale.get("commons.pages.member")),false);
							}

						}

						$.pgsi.ajax.post({
							 sUrl       : "api/member/update",
							 oRequest   : oRequest,
							 fnCallback : fnCallBackRisk
						});

					}

				};

				// Cancel Button
				oButtons[$.pgsi.locale.get("pages.dialog.cancel")] = function() {
					$("#action-dialog").dialog('close');
				};

				return oButtons;
			})(),

			action : function (actionDialog) {

				actionDialog.load("risk/risk_create?userId=" + pgsi.settings.userContext.userId, function() {

					actionDialog.dialog('open');

					pgsi.pages.risk.form.fnInitForm(3);
					pgsi.pages.risk.form.fillFormFields(oRequest.member.risk);
				});
			}
		}
	},

	//==========================
	insUpdRecipientRisk  : function (sTitle, oRequest) {

		return {
			title : $.pgsi.locale.get("pages.business.dialog.risk.title.edit", sTitle),
			width : 637,
			buttons : (function () {

				var oButtons = {};

				oButtons[$.pgsi.locale.get("commons.pages.save")] = function () {

					if (pgsi.pages.risk.form.validator.form()) {
						oRequest.recipient.risk = pgsi.pages.risk.form.fillObject();
						if (!$.pgsi.isNullOrUndefined($('#pep-dialog-level-value').val())){
							oRequest.recipient.pepStatusValue = parseInt($('#pep-dialog-level-value').val(),10);
						}


						var fnCallBackRisk = function(oResponse) {

							if (oResponse.operationSuccess == true) {

								$.pgsi.ajax.post({
									 sUrl       : "api/recipient/fetch",
									 oRequest   : { id : oRequest.recipient.id },

									fnCallback : function(oResponse) {
										pgsi.pages.risk.view.fillFields(oResponse.recipientList[0]);
										pgsi.version.versionRecipient = oResponse.recipientList[0].version;
									}
								});
								$("#action-dialog").dialog('close');
							}

							else {
								fnCallBackErrorRisk = function(oResponse) {

									pgsi.version.versionRecipient = oResponse.recipientList[0].version;
									pgsi.pages.recipient.view.fnFillFields(oResponse);
									oRequest.recipient.version = pgsi.version.versionRecipient;
									pgsi.pages.risk.form.fillFormFields(oResponse.recipientList[0].risk);
									$("#action-dialog-Error").dialog('close');

								}

								pgsi.pages.sendsolv.fnDialogMessageError("api/recipient/fetch",{id:oRequest.recipient.id},oResponse,fnCallBackErrorRisk,$.pgsi.locale.get("commons.dialog.error.title",$.pgsi.locale.get("commons.pages.recipient")),false);
							}
						}

						$.pgsi.ajax.post({
							 sUrl       : "api/recipient/update",
							 oRequest   : oRequest,
							 fnCallback : fnCallBackRisk
						});
					}

				};

				// Cancel Button
				oButtons[$.pgsi.locale.get("pages.dialog.cancel")] = function() {
					$("#action-dialog").dialog('close');
				};

				return oButtons;
			})(),

			action : function (actionDialog) {

				actionDialog.load("risk/risk_create?userId=" + pgsi.settings.userContext.userId, function() {

					actionDialog.dialog('open');

					pgsi.pages.risk.form.fnInitForm(3);
					pgsi.pages.risk.form.fillFormFields(oRequest.recipient.risk);


				});
			}
		}
	},

	//========================= DIALOG ADD NOTES ============================
	insUpdNote : function (iId,sTitle,sUrl,oRequest,fnCallBack) {

		return {
			title : sTitle,
			width : 637,

			close : function () {

			},

			buttons : (function () {

				var oButtons = {};

				// Saved Button
				oButtons[$.pgsi.locale.get("commons.pages.save")] = {

					click : function () {
						pgsi.pages.note.form.fnAjaxCallInsertUpdateNote(oRequest,sUrl,fnCallBack);
					},

					id : "dialog-submit",

					text : $.pgsi.locale.get("commons.pages.save")

				};

				// Cancel Button
				oButtons[$.pgsi.locale.get("pages.dialog.cancel")] = function() {
					$("#action-dialog").dialog('close');
				};

				return oButtons;
			})(),

			action : function (actionDialog) {

				actionDialog.load("note/note_create?userId=" + pgsi.settings.userContext.userId, function() {

					if(oRequest.note.modelAction =="UPDATE"){
						$('#note').text(oRequest.note.noteText);
					}
					actionDialog.dialog('open');

					pgsi.pages.note.form.fnInitForm();
				});
			}
		}
	},

	//========================= DIALOG EDIT LOCATION ============================
	insUpdMember : function (iId, sTitle, nBusinessType,sType,sUrl) {

		return {
			title : $.pgsi.locale.get("pages.member.dialog.title.edit", sTitle),
			width : 1150,

			close : function () {

			},

			buttons : (function () {

				var oButtons = {};

				// Confirm Button
				oButtons[$.pgsi.locale.get("commons.pages.save")] = function () {
					var fnFillCallBack = function(oResponse) {

						$("#action-dialog").dialog('close');
						$.pgsi.ajax.post({
							sUrl 		: "api/member/fetch",
							oRequest 	: {id : iId},
							fnCallback  : function(oResponse) {
								if(oResponse.operationSuccess == true){
									$("#person_info").find(".col-info #email-container").text("");
									pgsi.pages.member.view.fnFillFields(oResponse);
									pgsi.version.versionMember = oResponse.memberList[0].version;
								}else{
									pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
								}
								$.pgsi.progressBar.stop();
							}
						});

					}
					pgsi.pages.member.create.form.fnAjaxCallInsertUpdateMember($("#member-id").val(),fnFillCallBack);
				};

				// Cancel Button
				oButtons[$.pgsi.locale.get("commons.pages.cancel")] = function() {

					$("#action-dialog").dialog('close');
				};

				return oButtons;
			})(),

			action : function (actionDialog) {

				actionDialog.load("member/edit?memberId="+iId+"&userId=" + pgsi.settings.userContext.userId, function() {
					pgsi.pages.member.create.form.fnInitFormDialog();
					actionDialog.dialog('open');


					if($('#location-id').val() !== "0") {
						$('#location').val($('.view-Location').text())
					}

				});
			}
		}
	},

	//========================= DIALOG EDIT LOCATION ============================
	insUpdRecipient : function (iId, sTitle, nBusinessType,sType,sUrl,bTransfer) {

		return {
			title : sTitle,
			width : 1150,

			buttons : (function () {

				var oButtons = {};

				// Cancel Button
				oButtons[$.pgsi.locale.get("commons.pages.cancel")] = function() {

					$("#action-dialog").dialog('close');
				};

				// Confirm Button
				oButtons[$.pgsi.locale.get("commons.pages.save")] = function () {

					if(bTransfer == true){
						var fnFillCallBack = function(oResponse) {
							$("#action-dialog").dialog('close');
						}
					}else{
						var fnFillCallBack = function(oResponse) {

							pgsi.pages.recipient.create.form.fnAjaxCallFetchAll(iId);
							$("#action-dialog").dialog('close');
						}
					}
					pgsi.pages.recipient.create.form.fnAjaxCallInsertUpdateRecipient($("#recipient-id").val(),fnFillCallBack,bTransfer);
				};

				oButtons[$.pgsi.locale.get("pages.member.view.addtransfer")] = function () {
					var fnFillCallBack = function(oResponse) {
						if(oResponse.operationSuccess == true){
							pgsi.util.actiondialog.launchActionDialog(
								"insUpdTransfer",
								pgsi.pages.transfer.dialogSettings.insUpdTransfer(
									parseInt($('#member-id').val(),10),
									"",
									parseInt($("#personType").val(),10),
									null,
									oResponse.recipientList[0].firstName + ' '+ oResponse.recipientList[0].lastName,
									oResponse.recipientList[0].personStatusValue,
									oResponse.recipientList[0].id)
								);
						}
					}

					pgsi.pages.recipient.create.form.fnAjaxCallInsertUpdateRecipient(0,fnFillCallBack);
				};


				return oButtons;
			})(),

			action : function (actionDialog) {

				actionDialog.load("recipient/edit?recipientId="+iId+"&userId=" + pgsi.settings.userContext.userId, function() {

					if(bTransfer){
						pgsi.pages.recipient.create.form.fnInitFormDialogTransfer();
					} else {
						pgsi.pages.recipient.create.form.fnInitFormDialog();
					}
					actionDialog.dialog('open');


					if($('#location-id').val() !== "0"){
						$('#location').val($('.view-Location').text())
					}

				});
			}
		}
	},

	//========================= DIALOG ADD/EDIT Employment Info ============================
	insUpdEmployment : function(sTitle, oRequest) {

		return {
			title : sTitle,
			width : 652,

			buttons : (function () {

				var oButtons = {};

				// Confirm Button
				oButtons[$.pgsi.locale.get("commons.pages.save")] = {

					id : "dialog-submit",

					text : $.pgsi.locale.get("commons.pages.save"),

					click : function () {
						var validator = $("#createEmploymentForm").validate();
						if (validator.form()) {
							// Fill Employment object with form data
							oRequest.member.employmentInfoList[0] = pgsi.pages.employment.form.fillObject(
								oRequest.member.employmentInfoList[0]
							);

							var fnFetchMember = function(oResponse) {
								sPurl = "api/member/fetch";

								if (oResponse.operationSuccess == true) {

									$.pgsi.ajax.post({
										 sUrl       : sPurl,
										 oRequest   : { id : oRequest.member.id },

										 fnCallback : function(oResponse) {
										 	// Fill Employment info section with new data
											pgsi.pages.employment.view.fill(oResponse.memberList[0]);
											pgsi.version.versionMember = oResponse.memberList[0].version;
											$.pgsi.progressBar.stop();
										}
									});

									$("#action-dialog").dialog('close');
								}

								else {
									fnCallBackError = function(oResponse) {
										pgsi.version.versionMember = oResponse.memberList[0].version;

										$("#action-dialog-Error").dialog('close');

									}

									pgsi.pages.sendsolv.fnDialogMessageError(sPurl,{id:oRequest.member.id},oResponse,fnCallBackError,$.pgsi.locale.get("commons.dialog.error.title",$.pgsi.locale.get("commons.pages.employment")),false);
								}

							};

							$.pgsi.ajax.post({
								 sUrl       : "api/member/update",
								 oRequest   : oRequest,
								 fnCallback : fnFetchMember
							});
						}
					}

				};

				// Cancel Button
				oButtons[$.pgsi.locale.get("commons.pages.cancel")] = function() {

					$("#action-dialog").dialog('close');
				};

				return oButtons;
			})(),

			action : function (actionDialog) {

				actionDialog.load("employment/employment_create?userId=" + pgsi.settings.userContext.userId, function() {
					// Fill form fields with request data
					pgsi.pages.employment.form.fillFormFields(oRequest.member.employmentInfoList[0]);
					pgsi.pages.employment.form.fnInitForm();
					actionDialog.dialog('open');

				});
			}
		}
	},

	//========================= DIALOG DELETE ============================
	deleteDialog : function (sUrl, oRequest, sTitle, fnCallBack, sItemName) {
		return {

				width : 485,
				title : sTitle,

				close : function () {

				},

				buttons : (function () {

					var oButtons = {};

					// Confirm Button
					oButtons[$.pgsi.locale.get("commons.pages.delete")] = {
						click: function () {

							$.pgsi.ajax.post({
								 sUrl       : sUrl,
								 oRequest   : oRequest,
								 fnCallback : fnCallBack
							});

							$("#action-dialog").dialog('close');
						},

						text: $.pgsi.locale.get("commons.pages.delete"),

						id : "dialog-submit"
					},

					// Cancel Button
					oButtons[$.pgsi.locale.get("pages.dialog.cancel")] = function() {
						$("#action-dialog").dialog('close');
					};

					return oButtons;
				})(),

				action : function (actionDialog) {

					actionDialog.load("document/dialogDelete?userId=" + pgsi.settings.userContext.userId, function() {
						$('#errorText').text(sItemName)
						actionDialog.dialog('open');
					});
				}
			}
	},

	errorDialog : function (sUrl,oRequest,sUrlAction,fnCallBack,sTitleButton,sMessageErrorDialog,sError) {

			return {
				width : 485,
				dialogClass : "response-error",

				close : function () {
					$(".ui-front").css({"z-index":"100"});
					$(".ui-widget.ui-dialog").css({"z-index":"500"});
				},

				open : function () {
					$(".ui-front").css({"z-index":"500"});
					$(".ui-widget.ui-dialog.response-error").css({"z-index":"9999"});
				},

				buttons : (function () {

					var oButtons = {};

					// Confirm Button
					oButtons[$.pgsi.locale.get(sTitleButton)] = function () {

						if(sTitleButton != "commons.pages.confirm"){
							$.pgsi.ajax.post({
								 sUrl       : sUrl,
								 oRequest   : oRequest,
								 fnCallback : fnCallBack
							});
						}else{
							$("#action-dialog-Error").dialog('close');
						}
					};

					return oButtons;
				})(),

				action : function (actionDialog) {

					if(sUrlAction.indexOf("userId") == -1){
						if(sUrlAction.indexOf("?") != -1){
							sUrlAction += "&userId=" + pgsi.settings.userContext.userId
						}else{
							sUrlAction += "?userId=" + pgsi.settings.userContext.userId
						}
					}

					actionDialog.load(sUrlAction, function() {
						$('#error').text(sMessageErrorDialog);
						sDate = $.pgsi.date.format(new Date(), "h:i:s A", true);

							if(sError){
								$('#error-time').addClass('hide');
								$('#error-message').text("");
								$('#error-message').append(sError);
							}else{
								$('#error-time').text($.pgsi.locale.get('commons.dialog.error.message.time', sDate));
							}
						actionDialog.dialog('open');
					});
				}
			}
	},

	activeDesactiveDialog : function (sUrl, oRequest, sTitle, fnFillCallBack, sTextMessage,sBottonLabel) {

		return {
			width : 485,
			title : sTitle,

			close : function () {

			},

			buttons : (function () {

				var oButtons = {};

				// Confirm Button
				oButtons[$.pgsi.locale.get(sBottonLabel)] = {

					id : "dialog-submit",

					click : function() {
						$.pgsi.ajax.post({
							sUrl 		: sUrl,
							oRequest 	: oRequest,
							fnCallback  : function(oResponse) {

								if (oResponse.operationSuccess == true){
									 fnFillCallBack(oResponse);
								}else{
									pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
								}
							}
						});

						$("#action-dialog").dialog('close');
					},

					text : $.pgsi.locale.get(sBottonLabel)
				};

				// Cancel Button
				oButtons[$.pgsi.locale.get("pages.dialog.cancel")] = function() {
					$("#action-dialog").dialog('close');
				};

				return oButtons;
			})(),

			action : function (actionDialog) {

				actionDialog.load("document/dialogDelete?userId=" + pgsi.settings.userContext.userId, function() {
					$('#errorText').html(sTextMessage);
					$(".delete-dialog-container .text:eq(1)").hide();
					actionDialog.dialog('open');
				});
			}
		}
	},

	regeneratePin : function(sTitle, oRequest) {
		return {
			title : sTitle,
			width : 480,

			buttons : (function () {

				var oButtons = {};

				// Confirm Button
				oButtons[$.pgsi.locale.get("pages.member.pin.dialog.confirm")] = function () {
					$.pgsi.ajax.post({
						sUrl 		: "api/member/update",
						oRequest 	: oRequest,
						fnCallback  : function(oResponse) {
							if(oResponse.operationSuccess === true) {

								pgsi.pages.member.view.fnInitPinSection(oResponse.memberList[0]);
								$("#action-dialog").dialog('close');

								$.pgsi.ajax.post({
									 sUrl       : "api/member/fetch",
									 oRequest   : { id : oRequest.member.id },

									 fnCallback : function(oResponse) {
										pgsi.version.versionMember = oResponse.memberList[0].version;

										pgsi.util.actiondialog.launchActionDialog(
											"regeneratePinSuccess",
											pgsi.pages.business.dialogSettings.regeneratePinSuccess(oResponse.memberList[0].pinNumber)
										);									}
								});
							}

							else {
								pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
							}
						}
					});
				};

				// Cancel Button
				oButtons[$.pgsi.locale.get("commons.pages.cancel")] = function() {

					$("#action-dialog").dialog('close');
				};

				return oButtons;
			})(),

			action : function (actionDialog) {

				actionDialog.load("member/pin/regenerate?userId=" + pgsi.settings.userContext.userId, function() {

					actionDialog.dialog('open');

				});
			}
		}
	},

	regeneratePinSuccess : function(iPinNumber) {
		return {
			width : 280,

			dialogClass: "pin-dialog no-close no-title-bar",

			buttons : (function () {

				var oButtons = {};

				// Confirm Button
				oButtons[$.pgsi.locale.get("commons.pages.confirm")] = function () {
					$("#action-dialog").dialog('close');
				};

				return oButtons;

			})(),

			action : function (actionDialog) {

				actionDialog.load("member/pin/regenerate/success?userId=" + pgsi.settings.userContext.userId, function() {

					$("#pin-newpin").text(iPinNumber);
					actionDialog.dialog('open');

				});
			}
		}
	}
}
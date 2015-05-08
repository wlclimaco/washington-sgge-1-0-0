<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.transaction
 * @description The main namespace for the Transaction View Page.
 * @author QAT BRAZIL
 */

pgsi.pages.transaction.view = {

	fnPageHeader : function (oBatch)
	{

		var sMessageError = ""
			iId=0;
		oMoneyTransferList = oBatch.moneyTransferList

		$('#batches').attr('href','payment/batches_view?batchesId='+oBatch.id).attr('title','Batch '+oBatch.id).text(' > '+$.pgsi.locale.get("commons.pages.batch") + ' #'+(pgsi.pages.transaction.fnMask(oBatch.key)||oBatch.id))

		for(var i = 0;i<oMoneyTransferList.length;i++){

			if(oMoneyTransferList[i].id === parseInt($.address.parameter("transactionId"),10)){
				$('#location-transaction').text($.pgsi.locale.get("commons.pages.transaction") + ' #'+(pgsi.pages.transaction.fnMask(oMoneyTransferList[i].key) || oMoneyTransferList[i].id));
				sConfirmationNumber = oMoneyTransferList[i].confirmationNumber;
				iId = oMoneyTransferList[i].id;
				sStatus = oMoneyTransferList[i].statusList[oMoneyTransferList[i].statusList.length - 1].status;
				if(!$.pgsi.isNullOrUndefined(oMoneyTransferList[i].statusList[oMoneyTransferList[i].statusList.length - 1].response)){
					if (!$.pgsi.isNullOrUndefined(oMoneyTransferList[i].statusList[oMoneyTransferList[i].statusList.length - 1].response.messageList[1])){
						if (!$.pgsi.isNullOrUndefined(oMoneyTransferList[i].statusList[oMoneyTransferList[i].statusList.length - 1].response.messageList[1].messageInfo)){
							if (!$.pgsi.isNullOrUndefined(oMoneyTransferList[i].statusList[oMoneyTransferList[i].statusList.length - 1].response.messageList[1].messageInfo.internalText)){
								sMessageError = oMoneyTransferList[i].statusList[oMoneyTransferList[i].statusList.length - 1].response.messageList[1].messageInfo.internalText;
							}
						}
					}
				}

				if (!$.pgsi.isNullOrUndefined(oMoneyTransferList[i].key)){
					$('#transaction-id').text("#"+pgsi.util.page.fnInsertMask("***-99999",pgsi.pages.transaction.fnMask(oMoneyTransferList[i].key)));
					$('#transaction').text($.pgsi.locale.get("commons.pages.transaction") +" #"+pgsi.pages.transaction.fnMask(oMoneyTransferList[i].key)+" (" + $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum",sStatus)+")");
				}else{
					$('#transaction-id').text("#"+oMoneyTransferList[i].id);
					$('#transaction').text($.pgsi.locale.get("commons.pages.transaction") +" #"+oMoneyTransferList[i].id+" (" + $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum",sStatus)+")");
				}
				$('#transaction-status').text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum",sStatus));

			}
		}
		$('#create-date').addClass('hide');
		$('.location-sdn').addClass('hide');
		$('.msg-rejected').addClass('hide');
		$('#error-detail').text('');
		$('.content').removeClass('sdn-blocked');
		switch (sStatus)
		{
			case 'PENDING_APPROVAL':

				$('#btn').append(pgsi.pages.transaction.view.fnCreateButton([{label : $.pgsi.locale.get("pages.transaction.page.approve"), id : "approve"},{label : $.pgsi.locale.get("commons.pages.cancel"), id : "cancel"}]));
				break;
			case 'APPROVED':
				$('#btn').append(pgsi.pages.transaction.view.fnCreateButton([{label : $.pgsi.locale.get("pages.transaction.page.return.pending"), id : "pending"},{label : $.pgsi.locale.get("commons.pages.cancel"), id : "cancel"}]));
				$('.error-detail .alert').append('<div class="alert"><span class="icon-nav icon-exclamation-triangle"></span><div class="label" style="width: 90%; font-weight: normal">Changing the original Transfer Setting will <b>NOT</b> affect this transaction.</div></div>');
				$('#create-date').removeClass('hide');
				break;
			case 'MODIFICATION_APPROVED':
				$('#btn').append(pgsi.pages.transaction.view.fnCreateButton([{label : $.pgsi.locale.get("pages.transaction.page.return.pending"), id : "pending"},{label : $.pgsi.locale.get("commons.pages.cancel"), id : "cancel"}]));
				$('.error-detail .alert').append('<div class="alert"><span class="icon-nav icon-exclamation-triangle"></span><div class="label" style="width: 90%; font-weight: normal">Changing the original Transfer Setting will <b>NOT</b> affect this transaction.</div></div>');
				$('#create-date').removeClass('hide');
				break;
			case 'CANCELLATION_APPROVED':
				$('#btn').append(pgsi.pages.transaction.view.fnCreateButton([{label : $.pgsi.locale.get("pages.transaction.page.return.pending"), id : "pending"},{label : $.pgsi.locale.get("commons.pages.cancel"), id : "cancel"}]));
				$('.error-detail .alert').append('<div class="alert"><span class="icon-nav icon-exclamation-triangle"></span><div class="label" style="width: 90%; font-weight: normal">Changing the original Transfer Setting will <b>NOT</b> affect this transaction.</div></div>');
				$('#create-date').removeClass('hide');
				break;
			case 'APPROVED_NOT_FUNDED':
				$('#btn').append(pgsi.pages.transaction.view.fnCreateButton([{label : $.pgsi.locale.get("pages.transaction.page.return.pending"), id : "pending"},{label : $.pgsi.locale.get("commons.pages.cancel"), id : "cancel"}]));
				$('.error-detail .alert').append('<div class="alert"><span class="icon-nav icon-exclamation-triangle"></span><div class="label" style="width: 90%; font-weight: normal">Changing the original Transfer Setting will <b>NOT</b> affect this transaction.</div></div>');
				$('#create-date').removeClass('hide');
				break;
			case 'ORDER_SUBMITTED':
				$('.confirmation-number').append('<div class="label">'+$.pgsi.locale.get("pages.batches.transaction.confirmation.number")+'</div><div class="value">'+sConfirmationNumber+'</div>');
				$('#create-date').removeClass('hide');
				if (!$.pgsi.isNullOrUndefined(sMessageError)){
					$('.msg-rejected').removeClass('hide');
					$('.msg-rejected .label').text(sMessageError);
				}
				pgsi.pages.transaction.view.fnDisableLink();
			case 'PENDING_ACH':
				$('#create-date').removeClass('hide');
				break;
			case 'EXCEPTION':
				$('#btn').append(pgsi.pages.transaction.view.fnCreateButton([{label : $.pgsi.locale.get("pages.transaction.page.resubmit.order"), id : "resudmit"},{label : $.pgsi.locale.get("commons.pages.cancel"), id : "cancel"}]));
				$('.confirmation-number').append('<div class="label">'+$.pgsi.locale.get("pages.batches.transaction.confirmation.number")+'</div><div class="value">'+sConfirmationNumber+'</div>');
				$('#error-detail').append('<hr class="top"><div class="col-all error-detail"><h3>Exception Details</h3><div class="alert"><span class="icon-nav icon-exclamation-triangle"></span><div class=" label" style="width: auto">Data Error <br>'+sMessageError+'</div></div></div><hr>');
				break;
			case 'ON_HOLD':
				$('.location-sdn').removeClass('hide');
				$('.location').addClass('hide');
				$('#create-date').removeClass('hide');
				$('.confirmation-number').append('<div class="label">'+$.pgsi.locale.get("pages.batches.transaction.confirmation.number")+'</div><div class="value">'+sConfirmationNumber+'</div>');
				$('.content').addClass('sdn-blocked');
				break;
			case 'NOT_PAID':
				$('#create-date').removeClass('hide');
				$('#btn').append(pgsi.pages.transaction.view.fnCreateButton([{label : $.pgsi.locale.get("pages.transfer.form.label.request.modification"), id : "modification"},{label : $.pgsi.locale.get("pages.transfer.form.label.request.cancellation"), id : "cancellation"}]));
				$('.confirmation-number').append('<div class="label">'+$.pgsi.locale.get("pages.batches.transaction.confirmation.number")+'</div><div class="value">'+sConfirmationNumber+'</div>');
				break;
			case 'MODIFICATION_SUBMITTED':
				$('.confirmation-number').append('<div class="label">'+$.pgsi.locale.get("pages.batches.transaction.confirmation.number")+'</div><div class="value">'+sConfirmationNumber+'</div>');
				$('#create-date').removeClass('hide');
				if (!$.pgsi.isNullOrUndefined(sMessageError)){
					$('.msg-rejected').removeClass('hide');
					$('.msg-rejected .label').text(sMessageError);
				}
				pgsi.pages.transaction.view.fnDisableLink();
				break;
			case 'CANCELLATION_SUBMITTED':
				$('.confirmation-number').append('<div class="label">'+$.pgsi.locale.get("pages.batches.transaction.confirmation.number")+'</div><div class="value">'+sConfirmationNumber+'</div>');
				$('#create-date').removeClass('hide');
				if (!$.pgsi.isNullOrUndefined(sMessageError)){
					$('.msg-rejected').removeClass('hide');
					$('.msg-rejected .label').text(sMessageError);
				}
				pgsi.pages.transaction.view.fnDisableLink();

				break;
			case 'REJECTED':
				$('.member a').attr('href',"javascripr:;").addClass('black')
				$('.member a:eq(0)').attr('href',"javascripr:;").addClass('hide').addClass('black')
				$('.confirmation-number').append('<div class="label">'+$.pgsi.locale.get("pages.batches.transaction.confirmation.number")+'</div><div class="value">'+sConfirmationNumber+'</div>');
				$('#create-date').removeClass('hide');
				if (!$.pgsi.isNullOrUndefined(sMessageError)){
					$('.msg-rejected').removeClass('hide');
					$('.msg-rejected .label').text(sMessageError);
				}
				pgsi.pages.transaction.view.fnDisableLink();
				break;
			case 'SEIZED':
				$('.confirmation-number').append('<div class="label">'+$.pgsi.locale.get("pages.batches.transaction.confirmation.number")+'</div><div class="value">'+sConfirmationNumber+'</div>');
				$('#create-date').removeClass('hide');
				if (!$.pgsi.isNullOrUndefined(sMessageError)){
					$('.msg-rejected').removeClass('hide');
					$('.msg-rejected .label').text(sMessageError);
				}
				pgsi.pages.transaction.view.fnDisableLink();
				break;
			case 'CANCELLED':
				$('#btn').append(pgsi.pages.transaction.view.fnCreateButton([{label : $.pgsi.locale.get("pages.transaction.page.resubmit.order"), id : "resudmit"},{label : $.pgsi.locale.get("pages.transfer.form.label.request.cancellation"), id : "cancellation"}]));
				$('.confirmation-number').append('<div class="label">'+$.pgsi.locale.get("pages.batches.transaction.confirmation.number")+'</div><div class="value">'+sConfirmationNumber+'</div>');
				break;
			case 'PAID':
				$('#create-date').removeClass('hide');
				$('.confirmation-number').append('<div class="label">'+$.pgsi.locale.get("pages.batches.transaction.confirmation.number")+'</div><div class="value">'+sConfirmationNumber+'</div>');
				break;


		}

		$('.internal').find('.btn').on("click", function(event) {

			event.preventDefault();

			var oMoneyTransferStatus = new MoneyTransferStatus({
					id : null,
					moneyTransferId : iId,
					status : "PENDING_APPROVAL",
					response : null,
					moneyTransferTransaction : null,
					modelAction : "INSERT"
				});


			var fnCallback = function(oResponse) {

				$.pgsi.ajax.post({
					 sUrl 		: 'api/transaction/fetchBatchId',
					 oRequest 	: {id : $.address.parameter('batchId')},
					 fnCallback : function(oReturn){
						if (!$.pgsi.isNullOrUndefined(oReturn) &&
								!$.pgsi.isNullOrUndefined(oReturn.moneyTransferBatchList[0]))
						{
							pgsi.pages.transaction.view.fnBatchInfo(oReturn.moneyTransferBatchList[0]);

							if (!$.pgsi.isNullOrUndefined(oReturn.moneyTransferBatchList[0].moneyTransferList))
							{
								$('#btn').text("")
								$('.list-status').text("")
								oMoneyTransferList = oReturn.moneyTransferBatchList[0].moneyTransferList
								for(var i = 0;i<oMoneyTransferList.length;i++){

									if(oMoneyTransferList[i].id === parseInt($.address.parameter("transactionId"),10)){



										//-- fill the sender (member) information
										pgsi.pages.transaction.view.fnMemberInfo(oMoneyTransferList[i].moneyTransferDetail.member);

										//-- fill the recipient information
										pgsi.pages.transaction.view.fnRecipientInfo(oMoneyTransferList[i].moneyTransferDetail.recipient);

										//-- fill the transfer details
										pgsi.pages.transaction.view.fnTransferInfo(oMoneyTransferList[i]);

										pgsi.pages.transaction.view.fnHistoryStatus(oMoneyTransferList[i]);

									}

								}
							}
							pgsi.pages.transaction.view.fnPageHeader(oReturn.moneyTransferBatchList[0]);
						}
						$.pgsi.progressBar.stop();
					}
				});
			};

			if($(this).hasClass('pending')){
				oMoneyTransferStatus.status = "PENDING_APPROVAL";
				pgsi.util.actiondialog.launchActionDialog (
					"changeMoneyTransferStatus",
					pgsi.pages.transaction.dialogSettings.changeMoneyTransferStatus(
						$.pgsi.locale.get('pages.transactions.dialog.pending.title.singular'),
						$.pgsi.locale.get('pages.batches.view.message.pendingapproval'),
						oMoneyTransferStatus,
						fnCallback
					)
				);
			}else if($(this).hasClass('approve')){
				oMoneyTransferStatus.status = "APPROVED";
				pgsi.util.actiondialog.launchActionDialog (
					"changeMoneyTransferStatus",
					pgsi.pages.transaction.dialogSettings.changeMoneyTransferStatus(
						$.pgsi.locale.get('pages.transactions.dialog.approve.title.singular'),
						$.pgsi.locale.get('pages.batches.view.message.approve'),
						oMoneyTransferStatus,
						fnCallback
					)
				);
			}else if($(this).hasClass('cancel')){
				oMoneyTransferStatus.status = "CANCELLED";
				pgsi.util.actiondialog.launchActionDialog (
					"changeMoneyTransferStatus",
					pgsi.pages.transaction.dialogSettings.changeMoneyTransferStatus(
						$.pgsi.locale.get('pages.transactions.dialog.cancel.title.singular'),
						$.pgsi.locale.get('pages.batches.view.message.cancel'),
						oMoneyTransferStatus,
						fnCallback
					)
				);
			}else if($(this).hasClass('resudmit')){

				oMoneyTransferStatus.status = "APPROVED";
				fnCallTransactionStatus = function(oResponse) {

					if (oResponse.operationSuccess == true){
						if(oResponse.moneyTransferList.length > 0){
							for(var i = 0;i < oResponse.moneyTransferList.length;i++){
								for(var iStatus = 1;iStatus <= oResponse.moneyTransferList[i].statusList.length;iStatus++){
									if((oResponse.moneyTransferList[i].statusList[oResponse.moneyTransferList[i].statusList.length - iStatus].status == "CANCELLATION_APPROVED")||
										(oResponse.moneyTransferList[i].statusList[oResponse.moneyTransferList[i].statusList.length - iStatus].status == "MODIFICATION_APPROVED")||
										(oResponse.moneyTransferList[i].statusList[oResponse.moneyTransferList[i].statusList.length - iStatus].status == "APPROVED")||
										(oResponse.moneyTransferList[i].statusList[oResponse.moneyTransferList[i].statusList.length - iStatus].status == "APPROVED_NOT_FUNDED")){
											oMoneyTransferStatus.status = oResponse.moneyTransferList[i].statusList[oResponse.moneyTransferList[i].statusList.length - iStatus].status;
											break;
									}
								}
							}

						}

					}

					else {
						fnCallBackError = function(oResponse) {
							pgsi.version.versionBusiness = oResponse.organizationList[0].version;
							pgsi.pages.organization.form.fnFillOrganization(oResponse);
							pgsi.pages.organization.form.displayOrganizationFields();
							$("#action-dialog-Error").dialog('close');
						}
						pgsi.pages.sendsolv.fnDialogMessageError(sUrl,oRequest,oResponse,fnCallBackError,$.pgsi.locale.get("commons.dialog.error.title","Organization"),false);
					}
					$.pgsi.progressBar.stop();
				}

				pgsi.pages.transaction.view.fnFetchTransactionbyId({id:parseInt($.address.parameter('transactionId'),10)},fnCallTransactionStatus)

				pgsi.util.actiondialog.launchActionDialog (
					"changeMoneyTransferStatus",
					pgsi.pages.transaction.dialogSettings.changeMoneyTransferStatus(
						$.pgsi.locale.get('pages.transaction.page.resubmit.transaction'),
						$.pgsi.locale.get('pages.batches.dialog.resubmitBatch.message'),
						oMoneyTransferStatus,
						function(oResponse) {

							var bException = true;
							var fnCallBackBatchId = function(oResponse) {

								if (oResponse.operationSuccess == true) {

									if(oResponse.moneyTransferList.length > 0){
										for(var i = 0;i < oResponse.moneyTransferList.length;i++){
											if(oResponse.moneyTransferList[i].statusList[oResponse.moneyTransferList[i].statusList.length - 1].status == "EXCEPTION"){
													bException = false;
											}
										}

									}

									var fnCallBackMoneyTransferBatchStatus = function(oResponse) {

										$.pgsi.ajax.post({
											 sUrl 		: 'api/transaction/fetchBatchId',
											 oRequest 	: {id : $.address.parameter('batchId')},
											 fnCallback : function(oReturn){
												if (!$.pgsi.isNullOrUndefined(oReturn) &&
														!$.pgsi.isNullOrUndefined(oReturn.moneyTransferBatchList[0]))
												{
													pgsi.pages.transaction.view.fnBatchInfo(oReturn.moneyTransferBatchList[0]);

													if (!$.pgsi.isNullOrUndefined(oReturn.moneyTransferBatchList[0].moneyTransferList))
													{
														$('#btn ').text("")
														$('.list-status').text("")
														oMoneyTransferList = oReturn.moneyTransferBatchList[0].moneyTransferList
														for(var i = 0;i<oMoneyTransferList.length;i++){

															if(oMoneyTransferList[i].id === parseInt($.address.parameter("transactionId"),10)){



																//-- fill the sender (member) information
																pgsi.pages.transaction.view.fnMemberInfo(oMoneyTransferList[i].moneyTransferDetail.member);

																//-- fill the recipient information
																pgsi.pages.transaction.view.fnRecipientInfo(oMoneyTransferList[i].moneyTransferDetail.recipient);

																//-- fill the transfer details
																pgsi.pages.transaction.view.fnTransferInfo(oMoneyTransferList[i]);

																pgsi.pages.transaction.view.fnHistoryStatus(oMoneyTransferList[i]);

															}

														}
													}
													pgsi.pages.transaction.view.fnPageHeader(oReturn.moneyTransferBatchList[0]);
												}
												$.pgsi.progressBar.stop();
											}
										});
									}

									if(bException){
										var oMoneyTransferStatusList = new Array();

										var oMoneyTransferBatchStatus = new MoneyTransferBatchStatus({
											id : null,
											moneyTransferBatchId : parseInt($.address.parameter('batchId'),10),
											actionDueDate : (new Date()).getTime(),
											status : "RELEASED",
											modelAction : "INSERT"
										});

										oMoneyTransferStatusList.push(oMoneyTransferBatchStatus);

										var oRequest = new MoneyTransferBatchStatusMaintenanceRequest({
												moneyTransferBatchStatusList : oMoneyTransferStatusList
											});

										$.pgsi.ajax.post({
											 sUrl 		: 'api/moneyTransferBatchStatus/insert',
											 oRequest 	: oRequest,
											 fnCallback : fnCallBackMoneyTransferBatchStatus
										});

									}else{
										fnCallBackMoneyTransferBatchStatus();
									}


								}

								else{
									pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
								}
							}

							pgsi.pages.transaction.view.fnFetchBatchbyId(new MoneyTransferInquiryRequest({criteria:{moneyTransferBatchId:parseInt($.address.parameter('batchId'),10)}}),fnCallBackBatchId)
							$.pgsi.progressBar.stop();
						}
					)
				);
			}else if($(this).hasClass('cancellation')){
				oMoneyTransferStatus.status = "CANCELLATION_APPROVED";
				pgsi.util.actiondialog.launchActionDialog (
					"changeMoneyTransferStatus",
					pgsi.pages.transaction.dialogSettings.changeMoneyTransferStatus(
						$.pgsi.locale.get('pages.transactions.dialog.cancel.title.singular'),
						$.pgsi.locale.get('pages.batches.view.message.cancel'),
						oMoneyTransferStatus,
						fnCallback
					)
				);
			}else if($(this).hasClass('modification')){
				oMoneyTransferStatus.status = "MODIFICATION_APPROVED";
				pgsi.util.actiondialog.launchActionDialog (
					"changeMoneyTransferStatus",
					pgsi.pages.transaction.dialogSettings.changeMoneyTransferStatus(
						$.pgsi.locale.get('pages.transactions.dialog.request.order.title'),
						$.pgsi.locale.get('pages.batches.view.message.resubmit.Order'),
						oMoneyTransferStatus,
						fnCallback
					)
				);
			}

		});

	},
	fnFetchBatchbyId : function (oRequest,fnCallback){
		$.pgsi.ajax.post({
			 sUrl 		: 'api/transaction/transactions',
			 oRequest 	: oRequest,
			 bAsync		: false,
			 fnCallback : fnCallback
		});
	},
	fnFetchTransactionbyId : function (oRequest,fnCallback){
		$.pgsi.ajax.post({
			 sUrl 		: 'api/transaction/transactionByID',
			 oRequest 	: oRequest,
			 bAsync		: false,
			 fnCallback : fnCallback
		});
	},
	fnBatchInfo : function (oBatch)
	{

		$('.batch .alist').attr("href", "payment/batches_view?batchesId=" + oBatch.id);
		$('.batch #batchId').text("#"+(pgsi.pages.transaction.fnMask(oBatch.key) ||oBatch.id) +" - "+$.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.MoneyTransferBatchStatusEnum",oBatch.statusList[oBatch.statusList.length - 1].status));
		$('.batch #location').text(oBatch.location.name + ", " + oBatch.location.parentOrganizationName);
		$('#create-value').text($.pgsi.date.format(new Date(oBatch.createDateUTC), "mm/dd/yy h:i:s A", true));
		$('.location-sdn .security').append('<a href="javascript:;" class="security high">'+oBatch.location.name+'<span class="icon-security icon-shield84"></span>'+$.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.sdn.model.SDNStatusEnum",oBatch.location.sdnstatus)+'</a>')
		$('.location-sdn .organization').text(oBatch.location.parentOrganizationName);
		for(var i = 0 ; i < oBatch.location.contactList.length;i++){
			if(oBatch.location.contactList[i].type.toLowerCase() === "address"){
				$('.batch #region').text(oBatch.location.contactList[i].cityName + ", "+oBatch.location.contactList[i].stateProvinceRegion.code +", "+oBatch.location.contactList[i].country.code);
			}
		}

		$('.batch #transfer-date').text($.pgsi.date.format(new Date(oBatch.transferDate), "mm/dd/yy", null));

	},
	fnMemberInfo : function (oMember)
	{
		sSdnIcons = pgsi.util.page.status.view.fnFillBoxStatusFlags({
			sdnstatus : oMember.sdnstatus,
			risk : {
				riskLevel : oMember.riskLevel
			},
			pepStatus : oMember.pepStatus
		});

		if('&nbsp&nbsp' != sSdnIcons){
			$('#link-edit-memberinfo').append(sSdnIcons+' ');
		}
		var sOtherName = "";
		if (!$.pgsi.isNullOrUndefined(oMember.nameList[0])){
			sOtherName = oMember.nameList[0].otherName;
		}
		$('.member .alist').attr("href", "member/view?tab=info&memberId=" + oMember.id);
		$('.member .parent').text("" + oMember.firstName + ' ' + oMember.lastName + ' ' + sOtherName + ' (#'+ (pgsi.pages.transaction.fnMask(oMember.participantId) || oMember.id) + ') ');
		pgsi.pages.transaction.fnFillSender(oMember,"transaction-detail");
	},
	fnRecipientInfo : function (oRecipient)
	{

		sSdnIcons = pgsi.util.page.status.view.fnFillBoxStatusFlags({
			sdnstatus : oRecipient.sdnstatus,
			risk : {
				riskLevel : oRecipient.riskLevel
			},
			pepStatus : oRecipient.pepStatus
		});
		if('&nbsp&nbsp' != sSdnIcons){
			$('#link-edit-recipientinfo').append(sSdnIcons);
		}
		var sOtherName = "";
		if (!$.pgsi.isNullOrUndefined(oRecipient.nameList[0])){
			sOtherName = oRecipient.nameList[0].otherName;
		}
		$('.recipient .alist').attr("href", "recipient/view?tab=info&recipientId=" + oRecipient.id);
		$('.recipient .parent').text("" + oRecipient.firstName + ' ' + oRecipient.lastName + ' ' + sOtherName + ' (#'+ pgsi.pages.transaction.fnMask(oRecipient.participantId)  + ') ');
		pgsi.pages.transaction.fnFillRecipient(oRecipient,"transaction-detail");
	},
	fnTransferInfo : function (oTransfer)
	{


	//	$('.transfer .form-link').attr("href", "recipient/view?tab=info&recipientId=" + oTransfer.id);
		$('.transfer .parent').text('#'+ (pgsi.pages.transaction.fnMask(oTransfer.transferSetting.key) || oTransfer.transferSetting.id));
		pgsi.pages.transaction.fnFillTransferDetails(oTransfer,"transaction-detail");

		$('.transfer ').find('a.alist').on("click", function(event) {

			event.preventDefault();
			$.address.parameter('transferId',oTransfer.transferSetting.id)
			pgsi.util.actiondialog.launchActionDialog("insUpdTransfer", pgsi.pages.transfer.dialogSettings.insUpdTransfer(
									parseInt(oTransfer.transferSetting.memberId,10),
									"",
									3,
									oTransfer.transferSetting.id,
									null,
									null,
									parseInt(oTransfer.transferSetting.recipientId,10)
								));

		});
	},
	fnHistoryStatus : function (oTransaction)
	{

		sStatus = ""
		for(var i = 0;i < oTransaction.statusList.length;i++){

			sNote = pgsi.pages.transaction.view.fnSearchNote(oTransaction.noteList,oTransaction.statusList[i].createDateUTC);

			if (!$.pgsi.isNullOrUndefined(oTransaction.statusList[i].moneyTransferTransaction)){
				if (!$.pgsi.isNullOrUndefined(oTransaction.statusList[i].response)){
					sStatus = sStatus + '<div class="label newline spacer" style="width: auto"><span class="icon-nav icon-angle-right bullet"></span><b>'+$.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum",oTransaction.statusList[i].status)+' - '+oTransaction.statusList[i].response.messageList[0].text+' <a href="'+oTransaction.statusList[i].status+'" id="'+i+'" class="link-ach-details" title="View Details"><span class="icon-nav icon-search-find"></span></a></b> '+$.pgsi.date.format(new Date(oTransaction.statusList[i].createDateUTC), "mm/dd/yy h:i:s A", true)+'</div>'
				}else{
					sStatus = sStatus + '<div class="label newline spacer" style="width: auto"><span class="icon-nav icon-angle-right bullet"></span><b>'+$.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum",oTransaction.statusList[i].status)+'  <a href="'+oTransaction.statusList[i].status+'" id="'+i+'" class="link-ach-details" title="View Details"><span class="icon-nav icon-search-find"></span></a></b> '+$.pgsi.date.format(new Date(oTransaction.statusList[i].createDateUTC), "mm/dd/yy h:i:s A", true)+'</div>'
				}
			}else{

				if(oTransaction.statusList[i].status == "EXCEPTION"){
						if (!$.pgsi.isNullOrUndefined(oTransaction.statusList[i - 1].moneyTransferTransaction)){
							sStatus = sStatus + '<div class="label newline spacer" style="width: auto"><span class="icon-nav icon-angle-right bullet"></span><b>'+$.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum",oTransaction.statusList[i].status)+' - '+oTransaction.statusList[i].response.messageList[0].text+' <a href="'+oTransaction.statusList[i].status+'" id="'+(i - 1)+'" class="link-ach-details" title="View Details"><span class="icon-nav icon-search-find"></span></a></b> '+$.pgsi.date.format(new Date(oTransaction.statusList[i].createDateUTC), "mm/dd/yy h:i:s A", true)+'</div>'
						}else{
							sStatus = sStatus + '<div class="label newline spacer" style="width: auto"><span class="icon-nav icon-angle-right bullet"></span><b>'+$.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum",oTransaction.statusList[i].status)+'</b> '+$.pgsi.date.format(new Date(oTransaction.statusList[i].createDateUTC), "mm/dd/yy h:i:s A", true)+' by '+oTransaction.statusList[i].createUser+'. <br> <span class="comment">'+sNote+'</span></div>';
						}

				}else{
					sStatus = sStatus + '<div class="label newline spacer" style="width: auto"><span class="icon-nav icon-angle-right bullet"></span><b>'+$.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum",oTransaction.statusList[i].status)+'</b> '+$.pgsi.date.format(new Date(oTransaction.statusList[i].createDateUTC), "mm/dd/yy h:i:s A", true)+' by '+oTransaction.statusList[i].createUser+'. <br> <span class="comment">'+sNote+'</span></div>';
				}
			}
		}
		$('.list-status').append(sStatus);
		$(".list-status").on('click', 'a.link-ach-details', function(e){
			e.preventDefault();
			$.address.parameter("statusId",$(this).attr('id'));
			$.address.parameter("status",$(this).attr('href'));
			pgsi.util.actiondialog.launchActionDialog (
				"dialogTransactionDetail",
				pgsi.pages.transaction.dialogSettings.dialogTransactionDetail(
					$.pgsi.locale.get('pages.transactions.dialog.detail.view.error.title'),
					parseInt($.address.parameter("transactionId"),10)
				)
			);

		});

	},
	fnCreateButton : function(oButtons){
		sButtons = "";
		if (!$.pgsi.isNullOrUndefined(oButtons)) {
			if(oButtons.length > 0){
				for(var i = 0;i<oButtons.length;i++){
					sButtons = sButtons + '<a class="btn ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only '+oButtons[i].id+'" id="'+oButtons[i].id+'" href="javascript:;" role="button"><span class="ui-button-text">'+oButtons[i].label+'</span></a>';
				}
			}
		}
		return sButtons;
	},
	fnSearchNote : function(oNotes,iDateCreate){


		var sNote = "",
		sDateCreate = $.pgsi.date.format(new Date(iDateCreate), "mm/dd/yy h:i:s A", true);
		if (!$.pgsi.isNullOrUndefined(oNotes)) {
			for(var y=0;y< oNotes.length;y++){
				sDateCreateNote = $.pgsi.date.format(new Date(oNotes[y].createDateUTC), "mm/dd/yy h:i:s A", true);
				if(sDateCreateNote == sDateCreate){
					sNote = oNotes[y].noteText;
				}
			}
		}
		return sNote;
	},
	fnDisableLink : function(){
		$('.member a').attr('href',"javascript:;").addClass('black');
		$('.member a:eq(0)').attr('href',"javascript:;").addClass('hide').addClass('black');
		$('.recipient a').attr('href',"javascripr:;").addClass('black');
		$('.recipient a:eq(0)').attr('href',"javascript:;").addClass('hide').addClass('black');
		$('.transfer a').attr('href',"javascript:;").addClass('black');
		$('.transfer a:eq(0)').attr('href',"javascript:;").addClass('hide').addClass('black');
	}

}
</script>

</sec:authorize>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.payments_upcoming
 * @fileoverview The main namespace for the Location List Page.
 */

pgsi.pages.batches.view = {

	refresh : function() {

		var iBatchId = $('#batch-id');

		if (iBatchId.length > 0) {
			if (isNaN(parseInt(iBatchId.val()))) {
				iBatchId = null;
			}

			else {
				iBatchId = iBatchId.val();
			}
		}

		else {
			iBatchId = null;
		}

		if (!$.pgsi.isNullOrUndefined(iBatchId)) {
			$.pgsi.ajax.post({
				sUrl 		: "api/payment/batch_by_id",
				oRequest 	: {id : iBatchId},
				fnCallback  : function(oResponse) {
					if (oResponse.operationSuccess === true) {
						$('.batch-summary').empty();
						$('.tx-summary').text('');
						$('.fund-summary').text('');
						$('.batch-actions').text('');
						pgsi.pages.batches.view.fnCreateViewBatches(oResponse.moneyTransferBatchDTOList[0]);
						$.pgsi.progressBar.stop();
					}

					else {
						pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
					}
				}
			});
		}
	},

	validator : $("#batch-date-transaction").validate({
		ignore : "",
		invalidHandler : function(form, validator) {
			$.each(validator.errorList, function(index, value) {
				$(value.element).addClass("error");

				if (value.element.nodeName.toLowerCase() == 'select') {
					$(value.element).next('span').addClass("error");
				}
			});
		}
	}),

	fnCustomizationFilterBatch : function (){

		if(($('#batch-status').val() == "ON_HOLD")||($('#batch-status').val() == "READY_FOR_RELEASE")){
			$('.checkBoxUl .checkbox:eq(0) label').hide();
			$('.checkBoxUl .checkbox:eq(4) label').hide();
			$('.checkBoxUl .checkbox:eq(6) label').hide();
			$('.checkBoxUl .checkbox:eq(8) label').hide();
			$('.checkBoxUl .checkbox:eq(12) label').hide();
			$('.checkBoxUl .checkbox:eq(14) label').hide();
			$('.checkBoxUl .checkbox:eq(16) label').hide();
			$('.checkBoxUl .checkbox:eq(18) label').hide();
			$('.checkBoxUl .checkbox:eq(20) label').hide();
			$('.checkBoxUl .checkbox:eq(24) label').hide();
			$('.checkBoxUl .checkbox:eq(26) label').hide();
		}else if(($('#batch-status').val() == "PENDING_APPROVAL")||($('#batch-status').val() == "EXPIRED")){
			$('.checkBoxUl .checkbox:eq(4) label').hide();
			$('.checkBoxUl .checkbox:eq(6) label').hide();
			$('.checkBoxUl .checkbox:eq(8) label').hide();
			$('.checkBoxUl .checkbox:eq(12) label').hide();
			$('.checkBoxUl .checkbox:eq(14) label').hide();
			$('.checkBoxUl .checkbox:eq(16) label').hide();
			$('.checkBoxUl .checkbox:eq(18) label').hide();
			$('.checkBoxUl .checkbox:eq(20) label').hide();
			$('.checkBoxUl .checkbox:eq(24) label').hide();
			$('.checkBoxUl .checkbox:eq(26) label').hide();
			$("#link-approve-batch").button("disable");
			$('.tx-summary .btn').button("disable");
		}else if($('#batch-status').val() == "ACH_EXCEPTIONS"){
			$('.checkBoxUl .checkbox:eq(0) label').hide();
			$('.checkBoxUl .checkbox:eq(2) label').hide();
			$('.checkBoxUl .checkbox:eq(4) label').hide();
			$('.checkBoxUl .checkbox:eq(8) label').hide();
			$('.checkBoxUl .checkbox:eq(12) label').hide();
			$('.checkBoxUl .checkbox:eq(18) label').hide();
			$('.checkBoxUl .checkbox:eq(20) label').hide();
			$('.checkBoxUl .checkbox:eq(24) label').hide();
		}
	},

	fnBatchSummary : function(oResponse,oSummary){

	sLocation        = "";
	sLocationAddress = "";
	sCreated         = "";
	sSummary         = "";
	iSDNStatus ="";
	if (!$.pgsi.isNullOrUndefined(oResponse.location)) {
		sLocation = oResponse.location.name +" (" +oResponse.location.parentOrganizationName+" )";
		sSDNStatus = oResponse.location.sdnstatus;
		$('#location-id').val(oResponse.location.id);
		//address location
		for(var i=0;i<oResponse.location.contactList.length;i++){
			if(oResponse.location.contactList[i].type ==  "address"){
				sLocationAddress = oResponse.location.contactList[i].cityName.trim() +", " +oResponse.location.contactList[i].stateProvinceRegion.code.trim()+", "+oResponse.location.country.code;
			}
		}
	}
	if (!$.pgsi.isNullOrUndefined(oResponse.createDateUTC)) {
		sCreated = $.pgsi.date.format(new Date(oResponse.createDateUTC), "mm/dd/yy h:i:s A", true)+ " by "+oResponse.createUser+" User";
	}

	for (var i=0;i<oSummary.length;i++){
		switch (oSummary[i])
		{
			case 'location':
				sSummary = sSummary + '<div class="label first auto" id="location">'+sLocation+'</div><div class="label first auto" id="locationAddress">'+sLocationAddress+'</div>';
				break;
			case 'batch_created':
				sSummary = sSummary + '<div class="label first">'+$.pgsi.locale.get("pages.batches.view.batch.created")+'</div><div class="value" id="created">'+sCreated+'</div>'
				+'<div class="label first comment">'+pgsi.pages.batches.view.fnSearchNote(oResponse.noteList,oResponse.statusList,"BATCH_CREATION")[0]+'</div>'
				break;
			case 'transfer_date':
				sSummary = sSummary +'<div class="label first">'+$.pgsi.locale.get("pages.batches.view.transfer.date")+'</div>'
				+'<div id="transfer-date" class="value">'+pgsi.pages.batches.view.fnValidation($.pgsi.date.format(new Date(oResponse.transferDate), "mm/dd/yy", null))+'</div>'
				+'<a href="javascript:;" title="Change Transfer Date" id="link-change-transfer"><span class="icon-nav icon-pencil"></span>'+$.pgsi.locale.get("pages.batches.view.change")+'</a>'
				break;
			case 'batch_approved':

				sSummary += '<div class="label first">' + $.pgsi.locale.get("pages.batches.view.batch.approved") + '</div>';

				aNoteDateUser = pgsi.pages.batches.view.fnSearchNote(oResponse.noteList,oResponse.statusList,"PENDING_APPROVAL");

				// if date == null
				if (!$.pgsi.isNullOrUndefined(aNoteDateUser[1])) {
					sSummary += '<div class="value">' +$.pgsi.date.format(new Date(aNoteDateUser[1]), "mm/dd/yy h:i:s A", true) + ' ' + aNoteDateUser[2] + '</div>'
							 +  '<div class="label first comment">' +''+ aNoteDateUser[0] + '</div>';
				}

				else {
					sSummary += '<div class="value">--</div>';
				}

				break;
			case 'cancelled':
				sSummary = sSummary +'<div class="label first">'+$.pgsi.locale.get("pages.batches.view.cancelled")+'</div>'
									+'<div class="value">'+$.pgsi.date.format(new Date(pgsi.pages.batches.view.fnSearchNote(oResponse.noteList,oResponse.statusList,"CANCELLED")[1]), "mm/dd/yy h:i:s A", true)+' '+pgsi.pages.batches.view.fnSearchNote(oResponse.noteList,oResponse.statusList,"CANCELLED")[2]+'</div>'
									+'<div class="label first comment">'
									+''+pgsi.pages.batches.view.fnSearchNote(oResponse.noteList,oResponse.statusList,"CANCELLED")[0]
				break;
			case 'prefunded':
				sSummary = sSummary +'<div class="label first">'+$.pgsi.locale.get("pages.batches.view.prefunded")+'</div>'
			+'<div class="value"> -- </div>'
				break;
			case 'spreads_approved':
				var sSpreadsApproved = "--"
				for(var i = 0;i < oResponse.statusList.length;i++){
					if(oResponse.statusList[i].status == "READY_FOR_RELEASE"){
						sSpreadsApproved = pgsi.pages.batches.view.fnValidation($.pgsi.date.format(new Date(oResponse.statusList[i].createDateUTC), "mm/dd/yy h:i:s A", true))
					}
				}
				sSummary = sSummary +'<div class="label first">'+$.pgsi.locale.get("pages.batches.view.spreads.approved")+'</div>'
									+'<div class="value"> '+sSpreadsApproved+' </div>'
				break;
			case 'postfunded':
				sSummary = sSummary +'<div class="label first">'+$.pgsi.locale.get("pages.batches.view.postfunded")+'</div>'
									+'<div class="value"> -- </div>'
				break;
			case 'expired':
				sSummary = sSummary +'<div class="label first">'+$.pgsi.locale.get("pages.batches.view.batch.expired")+'</div>'
									+'<div class="value">'+$.pgsi.date.format(new Date(pgsi.pages.batches.view.fnSearchNote(oResponse.noteList,oResponse.statusList,"EXPIRED")[1]), "mm/dd/yy h:i:s A", true)+' '+pgsi.pages.batches.view.fnSearchNote(oResponse.noteList,oResponse.statusList,"EXPIRED")[2]+'</div>'
									+'<div class="label first comment">'
									+''+pgsi.pages.batches.view.fnSearchNote(oResponse.noteList,oResponse.statusList,"EXPIRED")[0]
				break;
			case 'location_on_hold':
				var sSDNStatusMessage = "";
				if((sSDNStatus == "POSITIVE")||(sSDNStatus == "PENDING_INVESTIGATION")){
					sSDNStatusMessage = '<span class="icon-security icon-shield84"></span>'+$.pgsi.locale.get("pages.batches.transaction.SDN")+' ' +$.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.sdn.model.SDNStatusEnum",sSDNStatus)+'</a>';
					sSummary = sSummary + '<div class="label first auto security high"><a class="security high" href="javascript:;">'+sLocation+' '+sSDNStatusMessage+'</a></div>'
				}else{
					sSDNStatusMessage = ' <span class=""></span>'+$.pgsi.locale.get("pages.batches.transaction.SDN")+' '+$.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.sdn.model.SDNStatusEnum",sSDNStatus)+'</a>';
					sSummary = sSummary +'<a class="" href="javascript:;">'
									+sLocation+ '' +sSDNStatusMessage +'</a>'
									+'<div class="label first auto" id="locationAddress">'+sLocationAddress+'</div>'
				}

		}
	}


		return '<h3>'+$.pgsi.locale.get("pages.batches.view.batch.summary")+'</h3>' + sSummary



	},

	fnTransactions : function(oTransactionList){

		var aActions = [],
		    aData = [];
		var iContV=0,
			iUsdV=0,
			iContT=0,
			sTransaction = "";
			iUsdT=0;
		var value = {type:"",count:0,usd:0}

		for(var i=0;i<oTransactionList.length;i++){
			if($.inArray(oTransactionList[i].statusList[oTransactionList[i].statusList.length-1].status, aData) === -1){
				aData.push(oTransactionList[i].statusList[oTransactionList[i].statusList.length-1].status)
			}
		}

		for(var y=0;y<aData.length;y++){
			iContV=0,iUsdV=0;
			if (!$.pgsi.isNullOrUndefined(oTransactionList)) {
				if (oTransactionList.length > 0) {
					for(var i=0;i<oTransactionList.length;i++){
						if (!$.pgsi.isNullOrUndefined(oTransactionList[i].statusList)) {
							if(oTransactionList[i].statusList.length > 0){
								if(aData[y] == oTransactionList[i].statusList[oTransactionList[i].statusList.length-1].status){
									iContV = iContV +1;
									iUsdV = iUsdV + oTransactionList[i].originAmount.amount;
									iContT = iContT + 1
									iUsdT = iUsdT + oTransactionList[i].originAmount.amount;
								}
							}
						}
					}
				}
			}
			aActions.push({type:aData[y],count:iContV,usd:iUsdV})
		}

		aActions.push({type: $.pgsi.locale.get("pages.batches.view.total") ,count:iContT,usd:iUsdT})
		var thead="",iCont='',fUsd='',
		sMask = "'alias': 'numeric', 'groupSeparator': ',', 'autoGroup': true, 'digits': 2, 'digitsOptional': false, 'prefix': '', 'placeholder': '0'";
		for(var i=0;i<aActions.length;i++){
			if(aActions[i].type != "Total"){
				thead = thead + "<th>"+$.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum",aActions[i].type)+"</th>";
			}else{
				thead = thead + "<th>"+aActions[i].type+"</th>";
			}
			iCont = iCont + "<td>"+aActions[i].count+"</td>" ;
			fUsd  = fUsd  + "<td>"+pgsi.util.page.fnInsertMaskNumeric(sMask,aActions[i].usd)+"</td>" ;
		}

		sTransaction =  '<table id="summary">'
						+'<thead>'
						+'	<tr>'
						+'		<th class="th">&nbsp;</th>'
						+		thead
						+'	</tr>'
						+'</thead>'
						+'<tbody>'
						+'	<tr>'
						+'		<td class="th">Count</td>'
						+	iCont
						+'	</tr>'
						+'	<tr>'
						+'		<td class="th">USD</td>'
						+	fUsd
						+'	</tr>'
						+'</tbody></table>'

		return sTransaction;

	},
	fnTransactionsApproval : function(oResponse){

		return   '<h3>'+$.pgsi.locale.get("pages.batches.view.quick.select")+'</h3>'
				+'<div class="label countTransaction">'+$.pgsi.locale.get("pages.batches.view.transactions.matching","0")+'</div>'
				+'<input type="checkbox" class="first checkbox matching">'
				+'<div class="label">'+$.pgsi.locale.get("pages.batches.view.previous.transaction")+'</div>'
				+'<input type="checkbox" class="first checkbox transaction">'
				+'<div class="label">'+$.pgsi.locale.get("pages.batches.view.transaction.approved")+'</div>'
				+'<input type="checkbox" class="first checkbox approved">'
				+'<div class="label">'+$.pgsi.locale.get("pages.batches.view.transfer.value")+'</div>'
				+'<a href="javascript:;" id="link-approve-tx" class="btn first approve">'+$.pgsi.locale.get("pages.batches.view.approve.selection")+'</a>'
				+'<a href="javascript:;" id="link-disapprove-tx" class="btn cancel">'+$.pgsi.locale.get("pages.batches.view.cancel.selection")+'</a>'
	},
	fnTransactionsCompleted : function(oResponse){

		var dLastUpdate = "",
		   sACHTransfer = "--";
		if (!$.pgsi.isNullOrUndefined(oResponse.currentStatus)) {
			if(oResponse.currentStatus.createDateUTC){
				dLastUpdate = pgsi.pages.batches.view.fnValidation($.pgsi.date.format(new Date(oResponse.currentStatus.createDateUTC), "mm/dd/yy", true));
			}
		}
		for(var i = 0;i < oResponse.statusList.length;i++){
			if(oResponse.statusList[i].status == "ACH_PROCESSING"){
				sACHTransfer = pgsi.pages.batches.view.fnValidation($.pgsi.date.format(new Date(oResponse.statusList[i].createDateUTC), "mm/dd/yy h:i:s A", true))
			}
		}
		return '<div class="label first" style="width: 150px">'+$.pgsi.locale.get("pages.batches.view.transfer.started")+'</div>'
				+'<div class="value"> '+sACHTransfer+' </div>'
				+'<div class="label first" style="width: 150px">'+$.pgsi.locale.get("pages.batches.view.last.status.update")+'</div>'
				+'<div class="value"> '+dLastUpdate+' </div><br>'
	},
	fnTransactionsErrors : function(oResponse){

		var dLastUpdate = "",
			sRealesed = "--";
		if (!$.pgsi.isNullOrUndefined(oResponse.currentStatus)) {
			if(oResponse.currentStatus.createDateUTC){
				dLastUpdate = pgsi.pages.batches.view.fnValidation($.pgsi.date.format(new Date(oResponse.currentStatus.createDateUTC), "mm/dd/yy h:i:s A", true));
			}
		}

		for(var i = 0;i < oResponse.statusList.length;i++){
			if(oResponse.statusList[i].status == "RELEASED"){
				sRealesed = pgsi.pages.batches.view.fnValidation($.pgsi.date.format(new Date(oResponse.statusList[i].createDateUTC), "mm/dd/yy h:i:s A", true))
			}
		}

		return '<div class="label first" style="width: 150px">'+$.pgsi.locale.get("pages.batches.view.released.to.ACH")+'</div>'
			   +'<div class="value"> '+sRealesed+' </div>'
			   +'<div class="label first" style="width: 150px">'+$.pgsi.locale.get("pages.batches.view.last.status.update")+'</div>'
			   +'<div class="value"> '+dLastUpdate+' </div>'
	},

	fnFunding : function(oResponse,oView){
		var sView = "",
			sMask = "'alias': 'numeric', 'groupSeparator': ',', 'autoGroup': true, 'digits': 2, 'digitsOptional': false, 'prefix': '', 'placeholder': '0'";
		sView = '<h3>'+$.pgsi.locale.get("pages.batches.view.funding")+'</h3>';
		for(var i = 0 ; i < oView.length; i++){
			if(oView[i] == "batch_total"){
				sView = sView + '<div class="label first">'+$.pgsi.locale.get("pages.batches.view.batch.total")+'</div> <div class="value">'+pgsi.util.page.fnInsertMaskNumeric(sMask,oResponse.batchTotal)+'</div>'
			}
			if(oView[i] == "payroll_amount"){
				sView = sView + '<div class="label first">'+$.pgsi.locale.get("pages.batches.view.payroll.amount")+'</div> <div class="value">'+pgsi.util.page.fnInsertMaskNumeric(sMask,oResponse.payrollAmount)+'</div>'
			}
			if(oView[i] == "transfer_total"){
				sView = sView + '<div class="label first">'+$.pgsi.locale.get("pages.batches.view.transfer.total")+'</div><div class="value">'+pgsi.util.page.fnInsertMaskNumeric(sMask,oResponse.transferTotal)+'</div>'
			}
		}
		$('.fund-summary').append(sView);
	},

	fnBatchActionApproval : function(oResponse){

		var dDateApp = 0;
		if (!$.pgsi.isNullOrUndefined(oResponse.statusList)) {
			if (oResponse.statusList.length > 0) {
				for(var i = 0; i < oResponse.statusList.length;i++){
					if(oResponse.statusList[i].status == "PENDING_APPROVAL"){
						dDateApp = oResponse.statusList[i].actionDueDate
					}
				}
			}
		}

		return '<h3 style="padding-top: 16px;">'+$.pgsi.locale.get("pages.batches.view.batch.actions")+'</h3>'
		+'<div class="label first">'+$.pgsi.locale.get("pages.batches.view.receive.payroll")+'</div>'
		+'<div class="value">'+pgsi.pages.batches.view.fnValidation($.pgsi.date.format(new Date(oResponse.payrollReceivedDate), "mm/dd/yy", null))+'</div>'
		+'<div class="label first">'+$.pgsi.locale.get("pages.batches.view.approve.by")+'</div>'
		+'<div class="value">'+pgsi.pages.batches.view.fnValidation($.pgsi.date.format(new Date(dDateApp), "mm/dd/yy", true))+'</div>'
		+'<input type="checkbox" class="first spacer" id="payroll">'
		+'<div class="label spacer">'+$.pgsi.locale.get("pages.batches.view.payroll.funds")+'</div>'
		+'<a href="javascript:;" id="link-cancel-batch" class="btn first">'+$.pgsi.locale.get("pages.batches.view.cancel.batch")+'</a>'
		+'<a href="javascript:;" class="btn" id="link-approve-batch">'+$.pgsi.locale.get("pages.batches.view.approve.batch")+'</a>'
		+'<a href="javascript:;" class="btn" id="close">'+$.pgsi.locale.get("pages.batches.view.close")+'</a>'
	},

	fnCreateViewBatches : function(oResponse){

		if (!$.pgsi.isNullOrUndefined(oResponse.moneyTransferBatch.key)){
			var input  = document.createElement("input");
			input.type = "text";
			input.value  = oResponse.moneyTransferBatch.key;
			var $input = $(input);
			$input.mask('***-99999');
		}else{
			input.value  = oResponse.moneyTransferBatch.id;
			var $input = $(input);
		}
		var sStatus = oResponse.moneyTransferBatch.statusList[oResponse.moneyTransferBatch.statusList.length -1].status;
		$('#batch-id').val(oResponse.moneyTransferBatch.id);
		$('#batch-status').val(sStatus);
		$('#title .list').text('Batch #'+$input.val()+' ('+$.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.MoneyTransferBatchStatusEnum",sStatus)+')');
		$('#icon-nav').text('Batch #'+$input.val());
		$('#version-number').val(oResponse.moneyTransferBatch.version);

		// Removes content from the section


		if(sStatus == "PENDING_APPROVAL"){
			$('.batch-summary').append(pgsi.pages.batches.view.fnBatchSummary(oResponse.moneyTransferBatch,["location","batch_created","transfer_date"]) );

			if (!$.pgsi.isNullOrUndefined(oResponse.moneyTransferBatch.moneyTransferList)) {
				$('.tx-summary').append('<h3>'+$.pgsi.locale.get("pages.batches.view.transactions")+'</h3>' + pgsi.pages.batches.view.fnTransactions(oResponse.moneyTransferBatch.moneyTransferList) + pgsi.pages.batches.view.fnTransactionsApproval(oResponse));
			}

			pgsi.pages.batches.view.fnFunding(oResponse.fundingSummary,["batch_total","payroll_amount","transfer_total","prefunds","postfunds"])

			$(".batch-actions").empty().append(pgsi.pages.batches.view.fnBatchActionApproval(oResponse.moneyTransferBatch));



		}else if(sStatus == "CANCELLED"){

			$('.batch-summary').append(pgsi.pages.batches.view.fnBatchSummary(oResponse.moneyTransferBatch,["location","batch_created","batch_approved","cancelled"]) );

			pgsi.pages.batches.view.fnFunding(oResponse.fundingSummary,["batch_total","prefunds","postfunds"])

			if (!$.pgsi.isNullOrUndefined(oResponse.moneyTransferBatch.moneyTransferList)) {
				$('.tx-summary').append('<h3>'+$.pgsi.locale.get("pages.batches.view.transactions")+'</h3>' + pgsi.pages.batches.view.fnTransactions(oResponse.moneyTransferBatch.moneyTransferList));
			}
			$(".batch-actions").empty().append(pgsi.pages.batches.view.fnCreateButton([{label : $.pgsi.locale.get("pages.batches.view.close"), id : "close"}]));

			$('.content.list .filter').hide();

		}else if((sStatus == "BATCH_PROCESSED")||(sStatus == "RELEASED" )){
			$(".batch-actions").empty();
			$('.batch-summary').append(pgsi.pages.batches.view.fnBatchSummary(oResponse.moneyTransferBatch,["location","batch_created","batch_approved","spreads_approved"]) );

			pgsi.pages.batches.view.fnFunding(oResponse.fundingSummary,["batch_total","transfer_total","prefunds","postfunds"])

			if (!$.pgsi.isNullOrUndefined(oResponse.moneyTransferBatch.moneyTransferList)) {
				$('.tx-summary').append('<h3>'+$.pgsi.locale.get("pages.batches.view.transactions")+'</h3>' + pgsi.pages.batches.view.fnTransactionsCompleted(oResponse.moneyTransferBatch) + pgsi.pages.batches.view.fnTransactions(oResponse.moneyTransferBatch.moneyTransferList));
			}

			$('.content.list .filter').hide();

			$(".batch-actions").empty().append(pgsi.pages.batches.view.fnCreateButton([{label : $.pgsi.locale.get("pages.batches.view.close"), id : "close"}]));

		}else if((sStatus == "ACH_EXCEPTIONS")||(sStatus == "ACH_PROCESSING")){

			$('.batch-summary').append(pgsi.pages.batches.view.fnBatchSummary(oResponse.moneyTransferBatch,["location","batch_created","batch_approved","spreads_approved"]) );

			pgsi.pages.batches.view.fnFunding(oResponse.fundingSummary,["batch_total","transfer_total"])

			if (!$.pgsi.isNullOrUndefined(oResponse.moneyTransferBatch.moneyTransferList)) {
				$('.tx-summary').append('<h3>'+$.pgsi.locale.get("pages.batches.view.transactions")+'</h3>' + pgsi.pages.batches.view.fnTransactionsErrors(oResponse.moneyTransferBatch) + pgsi.pages.batches.view.fnTransactions(oResponse.moneyTransferBatch.moneyTransferList));
			}

			$(".batch-actions").empty().append(pgsi.pages.batches.view.fnCreateButton([{label : $.pgsi.locale.get("pages.batches.view.close"), id : "close"}]));

		}else if(sStatus == "EXPIRED"){

			$('.batch-summary').append(pgsi.pages.batches.view.fnBatchSummary(oResponse.moneyTransferBatch,["location","batch_created","batch_approved","transfer_date","expired"]) );

			pgsi.pages.batches.view.fnFunding(oResponse.fundingSummary,["batch_total"])

			if (!$.pgsi.isNullOrUndefined(oResponse.moneyTransferBatch.moneyTransferList)) {
				$('.tx-summary').append('<h3>'+$.pgsi.locale.get("pages.batches.view.transactions")+'</h3>' + pgsi.pages.batches.view.fnTransactions(oResponse.moneyTransferBatch.moneyTransferList));
			}

			$(".batch-actions").empty().append(pgsi.pages.batches.view.fnCreateButton([{label : $.pgsi.locale.get("pages.batches.view.reactivate.batch"), id : "reactivate"},{label : $.pgsi.locale.get("pages.batches.view.cancel.batch"), id : "link-cancel-batch"},{label : $.pgsi.locale.get("pages.batches.view.close"), id : "close"}]));

		}else if(sStatus == "READY_FOR_RELEASE"){

			$('.batch-summary').append(pgsi.pages.batches.view.fnBatchSummary(oResponse.moneyTransferBatch,["location","batch_created","batch_approved","spreads_approved","transfer_date"]) );

			pgsi.pages.batches.view.fnFunding(oResponse.fundingSummary,["batch_total","payroll_amount","transfer_total"])

			if (!$.pgsi.isNullOrUndefined(oResponse.moneyTransferBatch.moneyTransferList)) {
				$('.tx-summary').append('<h3>'+$.pgsi.locale.get("pages.batches.view.transactions")+'</h3>' + pgsi.pages.batches.view.fnTransactions(oResponse.moneyTransferBatch.moneyTransferList));
			}

			$(".batch-actions").empty().append(pgsi.pages.batches.view.fnCreateButton([{label : $.pgsi.locale.get("pages.batches.view.release.batch"),id : "release"},{label : $.pgsi.locale.get("pages.batches.view.cancel.batch"),id : "link-cancel-batch"},{label : $.pgsi.locale.get("pages.batches.view.close"), id : "close"}]));

		}else if(sStatus == "ON_HOLD"){

			$('.batch-summary').append(pgsi.pages.batches.view.fnBatchSummary(oResponse.moneyTransferBatch,["location_on_hold","batch_created","batch_approved","spreads_approved","transfer_date"]) );

			pgsi.pages.batches.view.fnFunding(oResponse.fundingSummary,["batch_total","payroll_amount","transfer_total"])

			if (!$.pgsi.isNullOrUndefined(oResponse.moneyTransferBatch.moneyTransferList)) {
				$('.tx-summary').append('<h3>'+$.pgsi.locale.get("pages.batches.view.transactions")+'</h3>' + pgsi.pages.batches.view.fnTransactions(oResponse.moneyTransferBatch.moneyTransferList));
			}

			$(".batch-actions").empty().append(pgsi.pages.batches.view.fnCreateButton([{label : $.pgsi.locale.get("pages.batches.view.close"), id : "close"}]));

		}else if(sStatus == "SPREAD_REVIEW"){

			$('.batch-summary').append(pgsi.pages.batches.view.fnBatchSummary(oResponse.moneyTransferBatch,["location","batch_created","batch_approved","transfer_date"]) );

			pgsi.pages.batches.view.fnFunding(oResponse.fundingSummary,["batch_total","payroll_amount","transfer_total"])

			if (!$.pgsi.isNullOrUndefined(oResponse.moneyTransferBatch.moneyTransferList)) {
				$('.tx-summary').append('<h3>'+$.pgsi.locale.get("pages.batches.view.transactions")+'</h3>' + pgsi.pages.batches.view.fnTransactions(oResponse.moneyTransferBatch.moneyTransferList));
			}

			$(".batch-actions").empty().append(pgsi.pages.batches.view.fnCreateButton([{label : $.pgsi.locale.get("pages.batches.view.review.spreads.batch"), id : "reviewSpreads"},{label : $.pgsi.locale.get("pages.batches.buttons.approveSpreads"), id : "approveSpreads"},{label : $.pgsi.locale.get("pages.batches.buttons.approveRelease"), id : "approveRelease"},{label : $.pgsi.locale.get("pages.batches.view.cancel.batch"), id : "link-cancel-batch"},{label : $.pgsi.locale.get("pages.batches.view.close"), id : "close"}]));

			//
			$('#link-cancel-batch').addClass('first');
		}

		$(".btn").button();

		pgsi.pages.batches.view.fnCustomizationFilterBatch();



		//call back batch cancel
		var fnCallBack = function(oResponse) {

			if ($.pgsi.isNullOrUndefined(oResponse)) {
				return false;
			}

			if (oResponse.operationSuccess == true) {

				$.pgsi.ajax.post({
					sUrl 		: "api/payment/batch_by_id",
					oRequest 	: {id : parseInt($('#batch-id').val(),10)},
					fnCallback  : function(oResponse) {
						if(oResponse.operationSuccess == true){
							$('.batch-summary').empty();
							$('.tx-summary').text('');
							$('.fund-summary').text('');
							$('.batch-actions').text('');
							pgsi.pages.batches.view.fnCreateViewBatches(oResponse.moneyTransferBatchDTOList[0]);
						}else{
							pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
						}
						$.pgsi.progressBar.stop();
					}
				});
			}

			else{
				pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title","Batch"),true);
			}
		};
		var fnCallBackRelease = function(oResponse) {
			if ($.pgsi.isNullOrUndefined(oResponse)) {
				return false;
			}
			if (oResponse.operationSuccess == true) {
				$.pgsi.pageLoader.load({
					url : "payment/batches",
					$content : $("#load")
				});
			}
		}

		$(".batch-actions a").unbind("click");
		// Attach events to action buttons
		$(".batch-actions a").on('click', function(e){

			var oMoneyTransferStatusList = new Array();

			var oMoneyTransferBatchStatus = new MoneyTransferBatchStatus({
				id : null,
				moneyTransferBatchId : parseInt($('#batch-id').val(),10),
				actionDueDate : (new Date()).getTime(),
				status : null,
				modelAction : "INSERT"
			});

			oMoneyTransferStatusList.push(oMoneyTransferBatchStatus);

			switch ($(this).attr('id')) {

			case "reviewSpreads":
				pgsi.util.actiondialog.launchActionDialog (
			 			"reviewSpreads",
			 			pgsi.pages.batches.dialogSettings.reviewSpreads(
			 				$.pgsi.locale.get('pages.batches.dialog.reviewspread.title', (parseInt($('#batch-id').val(),10)).toString()),
			 				parseInt($('#batch-id').val(),10),
			 				true)
						);

				break;

				// Call dialog for change status to "CANCELLED"
				case "link-cancel-batch":
					oMoneyTransferStatusList[0].status = "CANCELLED";

					pgsi.util.actiondialog.launchActionDialog (
						"changeBatchStatus",
						pgsi.pages.batches.dialogSettings.changeBatchStatus(
							$.pgsi.locale.get('pages.batches.dialog.cancel.title'),
							$.pgsi.locale.get('pages.batches.dialog.cancel.message'),
							oMoneyTransferStatusList,
							fnCallBack
					 	)
					);
				break;

				// Call dialog for change status to "PENDING_APPROVAL"
				case "reactivate":
					oMoneyTransferStatusList[0].status = "PENDING_APPROVAL";

					pgsi.util.actiondialog.launchActionDialog (
						"reactiveBatch",
						pgsi.pages.batches.dialogSettings.reactiveBatch(
							"Reactivate Batch",
							"Do you want to reactivate this batch?",
							oMoneyTransferStatusList,
							fnCallBack
						)
					);
				break;

				// Call dialog for change status to "READY_FOR_RELEASE"
				case "approveSpreads":
						oMoneyTransferStatusList[0].status = "READY_FOR_RELEASE";

						pgsi.util.actiondialog.launchActionDialog (
							"changeBatchStatus",
							pgsi.pages.batches.dialogSettings.changeBatchStatus(
								$.pgsi.locale.get('pages.batches.view.approve.batch'),
								"Do you want to approve spreads this batch?",
								oMoneyTransferStatusList,
								fnCallBack
							)
						);
				break;

				// Call dialog for change status to "RELEASED"
				case "approveRelease" :
					oMoneyTransferStatusList[0].status = "RELEASED";

					pgsi.util.actiondialog.launchActionDialog (
						"changeBatchStatus",
						pgsi.pages.batches.dialogSettings.changeBatchStatus(
							$.pgsi.locale.get('pages.batches.buttons.approveRelease'),
							"Do you want to approve release this batch?",
							oMoneyTransferStatusList,
							fnCallBack
						)
					);
				break;

				// Redirects to bath list page
				case "close":
					e.preventDefault();
					sessionStorage.batchesTab = $('#batch-status').val()
					$.pgsi.pageLoader.load({
						url : "payment/batches?tab="+$('#batch-status').val(),
						$content : $("#load")
					});
				break;

				// Call dialog for change status to "RELEASED"
				case "release":
					oMoneyTransferStatusList[0].status = "RELEASED";

					pgsi.util.actiondialog.launchActionDialog (
						"changeBatchStatus",
						pgsi.pages.batches.dialogSettings.changeBatchStatus(
							 $.pgsi.locale.get('pages.batches.view.release.batch'),
							"Do you want to release this batch?",
							oMoneyTransferStatusList,
							fnCallBackRelease
						)
					);
				break;

				case "link-change-transfer":

					pgsi.util.actiondialog.launchActionDialog (
						"updateDateBatchTransaction",
						pgsi.pages.batches.dialogSettings.updateDateBatchTransaction(
							parseInt($('#batch-id').val(),10),
							null,
							 $.pgsi.locale.get('pages.batches.dialog.update.date'),
							 null,
							 fnCallBack
						)
					);
				break;

				case "link-approve-batch":
					var dDate = new Date();
					var oMoneyTransferBatch = {
						 	id : parseInt($('#batch-id').val(), 10),
						 	modelAction 		: "UPDATE",
						 	payrollReceivedDate : dDate.getTime(),
						 	location: {
						 		id : parseInt($('#location-id').val(), 10)
						 	},
						 	version : parseInt($('#version-number').val(), 10)
					};

					var oRequest = new MoneyTransferBatchMaintenanceRequest({
						moneyTransferBatch : oMoneyTransferBatch
					});

					$.pgsi.ajax.post({
						 sUrl 		: 'api/payment/update',
						 oRequest 	: oRequest,
						 fnCallback : function (oResponse) {
								if ($.pgsi.isNullOrUndefined(oResponse)) {
									return false;
								}

								if (oResponse.operationSuccess == true) {

									var oMoneyTransferStatusList = new Array();

									var oMoneyTransferBatchStatus = new MoneyTransferBatchStatus({
										id : null,
										moneyTransferBatchId : parseInt($('#batch-id').val(),10),
										actionDueDate : (new Date()).getTime(),
										status : "SPREAD_REVIEW",
										modelAction : "INSERT"
									});

									oMoneyTransferStatusList.push(oMoneyTransferBatchStatus);

									var oRequest = new MoneyTransferBatchStatusMaintenanceRequest({
													moneyTransferBatchStatusList : oMoneyTransferStatusList});

									$.pgsi.ajax.post({
										 sUrl 		: 'api/moneyTransferBatchStatus/insert',
										 oRequest 	: oRequest,
										 fnCallback : fnCallBack
									});
								}

								else{
									pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title","Batch"),true);
								}

						 }
					});
				break;
			}
		});

		$("#link-change-transfer").on("click", function(e){
			pgsi.util.actiondialog.launchActionDialog (
				"updateDateBatchTransaction",
				pgsi.pages.batches.dialogSettings.updateDateBatchTransaction(
					parseInt($('#batch-id').val(),10),
					null,
					 $.pgsi.locale.get('pages.batches.dialog.update.date'),
					 null,
					 fnCallBack
				)
			);
		});

		$('#payroll').on("click", function(e){
			var bApprove = true;
			if (!$.pgsi.isNullOrUndefined(oResponse.moneyTransferBatch.moneyTransferList)) {
				for(var i = 0; i < oResponse.moneyTransferBatch.moneyTransferList.length;i++){
					if(oResponse.moneyTransferBatch.moneyTransferList[i].currentStatus.status == "PENDING_APPROVAL"){
						bApprove = false;
					}
				}
			}
			if($(this).is(":checked")){
				if(bApprove){
					$("#link-approve-batch").button("enable")
				}
			}else{
				$("#link-approve-batch").button("disable")
			}
		});

	},

	fnSearchNote : function(oNotes,oStatus,sAction){

		var sNote = "",
		iDateCreate = null,
		sUserCreate = "",
		iModifyDate = 0;
		if (!$.pgsi.isNullOrUndefined(oStatus)) {
			if(oStatus.length > 0){
				for(var i=0;i< oStatus.length;i++){
					if(oStatus[i].status == sAction){
						iDateCreate = oStatus[i].createDateUTC;
						sUserCreate = oStatus[i].createUser;
					}
				}
				if (!$.pgsi.isNullOrUndefined(oNotes)) {
					if(oNotes.length > 0){
						for(var i=0;i< oNotes.length;i++){
							if(oNotes[i].createDateUTC == iDateCreate){
								sNote = oNotes[i].noteText;
							}
						}
					}
				}
			}
		}
		return [sNote,iDateCreate,"by " + sUserCreate +" User"];
	},
	fnValidation : function(sValue){
		if (!$.pgsi.isNullOrUndefined(sValue)) {
			return sValue;
		}else{
		    return "";
		}
	},
	fnCreateButton : function(oButtons){
		sButtons = "";
		$(".batch-actions").empty();
		sTitle = '<h3>'+$.pgsi.locale.get("pages.batches.view.batch.actions")+'</h3>';
		if (!$.pgsi.isNullOrUndefined(oButtons)) {
			if(oButtons.length > 0){
				for(var i = 0;i<oButtons.length;i++){
					sButtons = sButtons + sTitle + '<a id="'+oButtons[i].id+'" class="btn ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" href="javascript:;" role="button"><span class="ui-button-text">'+oButtons[i].label+'</span></a>';
					sTitle = "";
				}
			}
		}
		return sButtons;
	},
	fnCreateTransaction : function(aTransactions,status){
		var aMoneyTransfers = new Array();
		var oMoneyTransfer = new Object;

		for(var i = 0;i < aTransactions.length;i++ ){
			if ((!$.pgsi.isNullOrUndefined(parseInt(aTransactions[i],10)))&&(aTransactions[i] != "")) {
				oMoneyTransfer = new Object;
				oMoneyTransfer.moneyTransferId  			= parseInt(aTransactions[i],10);
				oMoneyTransfer.modelAction					= "INSERT";
				oMoneyTransfer.status						= status;
				aMoneyTransfers.push(oMoneyTransfer);
			}
		}
		return aMoneyTransfers;
	},
	transactionTable: {

	}
}
</script>

</sec:authorize>
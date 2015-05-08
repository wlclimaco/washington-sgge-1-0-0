<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
<script type="text/javascript">
/**
 * @namespacepgsi.pages.batches
 * @description The init namespace for the Location Page.
 */
$(document).ready(function()
{
	var aTotalTransaction = new Array();
	$.pgsi.listener.wait({
		eventName 	: "batchesViewList",
		arguments 	: ["table"],
		fnCallback 	: $.pgsi.progressBar.stopGlobal
	});
	// Pre loaded Filters/Columns
	<c:choose>
		<c:when test="${not empty refresh}">
			var oFilterPreLoad = "refresh";
		</c:when>
		<c:when test="${empty filters}">
			var	oFilterPreLoad = null;
		</c:when>
		<c:otherwise>
			var	oFilterPreLoad = ${filters};
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${not empty refresh}">
			var aaData = "refresh";
		</c:when>
		<c:when test="${empty response}">
			var aaData = null;
		</c:when>
		<c:otherwise>
			var aaData = ${response};
		</c:otherwise>
	</c:choose>

	/** * jQuery dataTable setup ** */
	pgsi.pages.batches.transactionTable = $('#data_list').dataTable($.pgsi.table.setTable(
	{
		id 			: "#data_list",
		sAjaxSource : "api/payment/fetch_transfer_batch_Id",
		bPreLoad	: false,
		sCheckbox 	: "id",

		ajax :
		{
			sObj		: "moneyTransferList",
			oRequest	: MoneyTransferInquiryRequest,
			fnRequest 	: function(){

				if (!$.pgsi.isNullOrUndefined($.address.parameter("transaction_type"))) {

					var a = $.address.parameter("transaction_type").split("|")

					return  new MoneyTransferInquiryRequest({criteria:{moneyTransferBatchId : $.address.parameter("batchesId"),statusList : $.pgsi.enums.fetchLabelsByValues("com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum",a)}});
				}else{
					return  new MoneyTransferInquiryRequest({criteria:{moneyTransferBatchId : $.address.parameter("batchesId")}});
				}
			}

		},

		aoColumns :
		[
		{
			headerData 		: $.pgsi.locale.get("pages.batches.transaction.table.transactionId"),
			order			: "id",
			mRender	 		: pgsi.pages.transaction.fnTransactionLink,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("pages.batches.transaction.table.actions"),
			order			: "status",
			mData	 		: '',
			sDefaultContent : "",
			sClass          : "actioncell",
			bSortable 		: true,
			sWidth			: "7%"
		},
		{
			headerData 		: $.pgsi.locale.get("pages.batches.transaction.table.status"),
			order			: "",
			mRender	 		: pgsi.pages.transaction.fnTransactionStatus,
			sDefaultContent : "",
			bSortable 		: true
		},
		{
			headerData 		: $.pgsi.locale.get("pages.batches.transaction.table.payroll"),
			order			: "state_column",
			mRender	 		:  pgsi.pages.transaction.fnCreateLocationLink,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("pages.batches.transaction.table.payer"),
			order			: "transferDate",
			mRender	 		: pgsi.pages.transaction.fnPayerStatus,
			sDefaultContent : "",
			bSortable 		: true
		},
		{
			headerData 		: $.pgsi.locale.get("pages.batches.transaction.table.member"),
			order			: "country_column",
			mRender	 		: pgsi.pages.transaction.fnMemberName,
			sDefaultContent : "",
			bSortable 		: false
		},

		{
			headerData 		: $.pgsi.locale.get("pages.batches.transaction.table.recipient"),
			order			: "country_column",
			mRender	 		: pgsi.pages.transaction.fnRecipientName,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("pages.batches.transaction.table.transfer.date"),
			order			: "country_column",
			mRender	 		: pgsi.pages.transaction.fnStartpayrollDate,
			sDefaultContent : "",
			bSortable 		: false,
			sClass          : "est"
		},
		{
			headerData 		: $.pgsi.locale.get("pages.batches.transaction.table.total"),
			order			: "country_column",
			mRender	 		: pgsi.pages.transaction.fnTotalUSD,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("pages.batches.transaction.table.fee"),
			order			: "country_column",
			mRender	 		: pgsi.pages.transaction.fnTotalFEE,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("pages.batches.transaction.table.est.rate"),
			order			: "country_column",
			mRender	 		: pgsi.pages.transaction.fnTotalRate,
			sDefaultContent : "",
			bSortable 		: false,
			sClass          : "amt"
		},
		{
			headerData 		: $.pgsi.locale.get("pages.batches.transaction.table.est.transfer"),
			order			: "country_column",
			mRender	 		: pgsi.pages.transaction.fnCurrency,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("pages.batches.transaction.table.SDN"),
			order			: "country_column",
			mRender	 		: pgsi.pages.transaction.fnSDNStatus,
			sDefaultContent : "",
			bSortable 		: false
		},
		],


		aaData : aaData[0],


		oSettings :
		{
			sortEnum      	: "",
			iDefaultCol   	: 0
		},

		rowCallback : function(nRow, aData, iDisplayIndex, oColumn) {

			// Adding sdn class to row
			if (aData.moneyTransferDetail.member.sdnstatus  === "POSITIVE") {

				$(nRow).addClass("sdn1");
			}

			else if (aData.moneyTransferDetail.member.sdnstatus === "PENDING_INVESTIGATION" || aData.moneyTransferDetail.member.sdnstatus === "PENDING_FEDERAL_INVESTIGATION" ) {
				$(nRow).addClass("sdn2");
			}

			var oActionSummary="";
			var sButtonStatus="";
			var sButtonDelete = "";

			sButtonEdit    = '<a class="action view-money-transfer edit_link alist" data-id="' + aData.id + '" title="View/Edit" href="transaction/view?batchId=' + aData.moneyTransferBatchId + '&transactionId='+aData.id+'"><span class="icon-nav icon-pencil"></span></a>'
			sButtonApprove = '<a class="action yes approve-money-transfer" data-id="' + aData.id + '" title="Approve" href="#"><span class="icon-nav icon-check-mark-circle-two"></span></a>'
			sButtonCancel  = '<a class="action no cancel-money-transfer" data-id="' + aData.id + '" title="Cancel" href="#"><span class="icon-nav icon-minus-circle"></span></a>'
			sButtonView    = '<a class="action edit_link alist" data-id="' + aData.id + '" title="View Details for Transaction #['+aData.id+']" href="transaction/view?batchId=' + aData.moneyTransferBatchId + '&transactionId='+aData.id+'"><span class="icon-nav icon-search-find"></span></a>'

			if(aData.statusList.length > 0){
				if(aData.statusList[aData.statusList.length -1 ].status == "PENDING_APPROVAL"){
					if(aData.foreignExchangeRate <= 0){
						sButtonApprove = "";
					}
					sButtonView = "";
				}else if(aData.statusList[aData.statusList.length -1 ].status == "APPROVED"){
					sButtonView = "";
					sButtonApprove = "";
				}else if((aData.statusList[aData.statusList.length -1 ].status == "ON_HOLD")||(aData.statusList[aData.statusList.length -1 ].status == "PENDING_ACH")||(aData.statusList[aData.statusList.length -1 ].status == "PAID")||(aData.statusList[aData.statusList.length -1 ].status == "SEIZED")){
					sButtonEdit = "";
					sButtonApprove = "";
					sButtonCancel = "";
				} else if(aData.statusList[aData.statusList.length -1 ].status == "CANCELLED") {
					str = $('#title .list').text()
					if(str.indexOf("Pending Approval") == -1){
						sButtonEdit = "";
						sButtonApprove = "";
						sButtonCancel = "";
					}else{
						sButtonEdit = "";
						sButtonCancel = "";
					}
				}else if(aData.statusList[aData.statusList.length -1 ].status == "REJECTED") {
					sButtonView = "";
					sButtonApprove = "";
				}
				else if(aData.statusList[aData.statusList.length -1 ].status == "NOT_PAID") {
					sButtonApprove = "";
					sButtonCancel = "";
				}
				else if(aData.statusList[aData.statusList.length -1 ].status == "EXCEPTION") {
					sButtonApprove = "";
					sButtonView = "";
				}else if(aData.foreignExchangeRate <= 0){
					sButtonApprove = "";
				}
				oActionSummary = $("<div>" + sButtonEdit + sButtonApprove + sButtonCancel + sButtonView + "</div>");

				oActionSummary.find('a.approve-money-transfer, a.cancel-money-transfer').click(function(e) {

					e.preventDefault();

					if (pgsi.util.page.fnIsSDNFlagged(aData.sdnstatus)) {
						return;
					}

					e.preventDefault();

					var oMoneyTransferStatus = new MoneyTransferStatus({
							id : null,
							moneyTransferId : $(this).data('id'),
							status : "APPROVED",
							response : null,
							moneyTransferTransaction : null,
							modelAction : "INSERT"
						});

					var oMoneyTransferStatusList = [
						oMoneyTransferStatus
					];

					if ($(this).hasClass('approve-money-transfer')) {

						pgsi.util.actiondialog.launchActionDialog (
						 	"changeMoneyTransferStatus",
						 	pgsi.pages.batches.dialogSettings.changeMoneyTransferStatus(
					 			$.pgsi.locale.get('pages.transactions.dialog.approve.title.singular'),
					 			null,
					 			oMoneyTransferStatusList,
					 			function(oResponse) {
					 				$.pgsi.table.reloadTable({
										table 		: pgsi.pages.batches.transactionTable,
										iStart 		: 0
									});

									pgsi.pages.batches.view.refresh();
					 			}
					 		)
						);
					}

					else if ($(this).hasClass('cancel-money-transfer')) {

						var oMoneyTransferStatus = new MoneyTransferStatus({
							id : null,
							moneyTransferId : $(this).data('id'),
							status : "CANCELLED",
							response : null,
							moneyTransferTransaction : null,
							modelAction : "INSERT"
						});

						var oMoneyTransferStatusList = [
							oMoneyTransferStatus
						];

						pgsi.util.actiondialog.launchActionDialog (
						 	"changeMoneyTransferStatus",
						 	pgsi.pages.batches.dialogSettings.changeMoneyTransferStatus(
					 			$.pgsi.locale.get('pages.transactions.dialog.cancel.title.singular'),
					 			$.pgsi.locale.get('pages.transactions.dialog.cancel.message.singular'),
					 			oMoneyTransferStatusList,
					 			function(oResponse) {
					 				$.pgsi.table.reloadTable({
										table 		: pgsi.pages.batches.transactionTable,
										iStart 		: 0
									});

									pgsi.pages.batches.view.refresh();
					 			}
					 		)
						);
					}

				});

				// Attaches hover effect for displaying edit/delete buttons
				$('td:eq(2)', nRow).append(oActionSummary);
			}

		},
		drawCallback: function (oSettings){

		},

		fnInitComplete: function (oSettings, json)
		{
			$(".dataTables_length select").outerWidth(62).selectmenu({
				appendTo: ".content.list",
  				change: function( event, ui ) {
  					$('#data_list_length').find("select").val(ui.item.value);
  					$("#data_list_length").find("select").trigger("change");
  					$("#load").find(".dataTables_length").find("select").selectmenu("refresh" );
  				}
			});
			$.pgsi.listener.notify({
			    eventName 	: "batchesViewList",
			    arguments 	: ["table"]
			});

		}
	}
	));

	$('#data_list').find('thead tr:eq(0) th:eq(0) input').change(function () {
		var $checkObjs = $('#data_list tbody').find('input.checkObj');
		if ($(this).prop('checked')) {
			$checkObjs.prop('checked', true).trigger('change');
		}
		else {
			$checkObjs.prop('checked', false).trigger('change');
		}

		if ($(this).is(":checked")) {
			$checkObjs.closest("tr").addClass('selected');
		}

		else{
			$checkObjs.closest("tr").removeClass('selected');
		}
	});

	var aFilters = ['transferstatus'];

	var filters = pgsi.util.filter.filterArrayToObject(aFilters);
	pgsi.util.filter.init(oFilterPreLoad, filters, function(oResponse)
	{
		$.pgsi.filter.create(
		{
			element			: ".filter",
			tagsDiv			: ".filter-results-container div.first",
			title			: "Filter Transactions",
			table 			:  pgsi.pages.batches.transactionTable,
			filters 		: oResponse,
			openFirstFilters: 4
		});

	});
	//clear all Filter TODO
	$("#clear-all").on("click", function(e)
	{
		$.address.parameter("transaction_type","");
		pgsi.util.page.fnReloadTable(pgsi.pages.batches.transactionTable);
	});

	//Batch Summary
	pgsi.pages.batches.view.fnCreateViewBatches(aaData.moneyTransferBatchDTOList[0]);


	$('.batch-actions #create').on('click', function(e){

		var aSelectedRows = $('#data_list').data('checkbox').selected();

		if (aSelectedRows.length === 0 ) {
			return false
		}

		var oBatch = aaData.moneyTransferBatchDTOList[0].moneyTransferBatch;

		oBatch.moneyTransferList = $.grep(oBatch.moneyTransferList, function(e){
			if ($.inArray(e.id, aSelectedRows) != -1) {
				return true;
			}

			else {
				return false;
			}
		});

		oBatch.modelAction = "INSERT";
		delete oBatch.currentStatus;

		// Update MoneyTransferBatchStatus
		var oMoneyTransferBatchStatus = new MoneyTransferBatchStatus({
			id : null,
			moneyTransferBatchId : oBatch.id,
			actionDueDate : (new Date()).getTime(),
			status : "PENDING_APPROVAL",
			modelAction : "INSERT"
		});

		oBatch.statusList = [ oMoneyTransferBatchStatus ];

		var oMoneyTransferStatus = new MoneyTransferStatus({
			id : null,
			moneyTransferId : null,
			status : "PENDING_APPROVAL",
			response : null,
			moneyTransferTransaction : null
		});

     	// Update each MoneyTransfer status
		for (var i=0; i < oBatch.moneyTransferList.length; i++) {
			oMoneyTransferStatus.moneyTransferId = oBatch.moneyTransferList[i].id;
			oBatch.moneyTransferList[i].statusList = [oMoneyTransferStatus];
			oBatch.moneyTransferList[i].modelAction = "INSERT";
			delete oBatch.moneyTransferList[i].currentStatus;
		}

		// Call "Create New Batch" dialog
		pgsi.util.actiondialog.launchActionDialog (
	     	"createNewBatchFromSelection",
			pgsi.pages.batches.dialogSettings.createNewBatchFromSelection(
			$.pgsi.locale.get('pages.batches.dialog.createNewBatchFromSelection.title'),
			oBatch
		 	)
 			);
		});

		//approve
		var iTotalTransaction = 0,
			iIsPreviousTransactionApproved,
			iIsSameAmountPreviousTransaction,
			iIsNetPayGreaterTransValue;
		$('.tx-summary .checkbox').on("click", function(e){


			if($('.matching').is(":checked")){
				iIsNetPayGreaterTransValue = true;
			}else{
				iIsNetPayGreaterTransValue = false;
			}
			if($('.transaction').is(":checked")){
				iIsSameAmountPreviousTransaction = true;
			}else{
				iIsSameAmountPreviousTransaction = false;
			}
			if($('.approved').is(":checked")){
				iIsPreviousTransactionApproved = true;
			}else{
					iIsPreviousTransactionApproved = false;
			}

			var oRequest = {
					moneyTransferBatchId            : $.address.parameter("batchesId"),
					isSameAmountPreviousTransaction : iIsSameAmountPreviousTransaction,
					isNetPayGreaterTransValue       : iIsNetPayGreaterTransValue,
					isPreviousTransactionApproved   : iIsPreviousTransactionApproved
			}
			$.pgsi.ajax.post({
				sUrl 		: 'api/payment/MoneyTransferAutoApproval',
				oRequest 	: oRequest,
				bAsync		: false,
				bHideProgressBar : true,
				fnCallback  : function(oResponse) {
					if(!$.pgsi.isNullOrUndefined(oResponse)){
						iTotalTransaction = oResponse.idsToApproveList.length;
						if(iTotalTransaction > 0 ){
							$('.tx-summary .btn').button("enable");
							aTotalTransaction = oResponse.idsToApproveList
						}else{
							$('.tx-summary .btn').button("disable");
						}
						$('.countTransaction').text($.pgsi.locale.get("pages.batches.view.transactions.matching",""+iTotalTransaction+""))
					}
				}
			});
		});

		$('.tx-summary .btn').on("click", function(e){

			var sStatus,
			sIds="";
			aMoneyTransfers = new Array();
			var oMoneyTransfer = new Array();
			if($(this).hasClass('approve')){
				sStatus = "APPROVED"
			}else{
				sStatus = "CANCELLED"
			}

			oMoneyTransfer.push(aTotalTransaction)

			if(oMoneyTransfer.length > 0){
				for(var i = 0;i < oMoneyTransfer.length;i++){
					if(i == (oMoneyTransfer.length -1)){
						sIds = sIds + oMoneyTransfer[i]
					}else{
						if ((!$.pgsi.isNullOrUndefined(oMoneyTransfer[i]))||(oMoneyTransfer[i] != "")) {
							sIds = sIds + oMoneyTransfer[i] + ","
						}
					}
				}
			}else{
				sIds = MoneyTransfer[0]
			}
			aMoneyTransfers = pgsi.pages.batches.view.fnCreateTransaction(sIds.split(","),sStatus)
			var oRequest;
			oRequest = new MoneyTransferStatusMaintenanceRequest({
				moneyTransferStatusList : aMoneyTransfers
			});

			// Update Money Transfers, with new Spread Info
			$.pgsi.ajax.post({
				sUrl : "api/payment/insertTransferStatus",
				oRequest : oRequest,
				fnCallback : function(oResponse) {
					if (oResponse.operationSuccess == true){
						pgsi.util.page.fnReloadTable(pgsi.pages.batches.transactionTable);

					}
				}
			});
		});
});

</script>
</sec:authorize>
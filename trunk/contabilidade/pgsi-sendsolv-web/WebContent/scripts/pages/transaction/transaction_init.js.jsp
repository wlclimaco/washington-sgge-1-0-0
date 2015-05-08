<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">
<script type="text/javascript">
/**
 * @namespace pgsi.pages.transaction
 * @description The init namespace for the Transaction Page.
 */
$(document).ready(function()
{
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

	$.pgsi.listener.wait({
		eventName 	: "transactionList",
		arguments 	: ["table"],
		fnCallback 	: $.pgsi.progressBar.stopGlobal
	});

	/** * jQuery dataTable setup ** */
	pgsi.pages.batches.transactionTable = $('#data_list').dataTable($.pgsi.table.setTable(
	{
		id 			: "#data_list",
		sAjaxSource : "api/transaction/transactions",
		bPreLoad	: false,

		ajax :
		{
			sObj		: "moneyTransferList",
			oRequest	: MoneyTransferInquiryRequest,
			fnRequest 	: function(){

				if (!$.pgsi.isNullOrUndefined($.address.parameter("tab"))) {

					if ($.address.parameter("tab") == "exceptions") {
						return  new MoneyTransferInquiryRequest({criteria:{statusList :["EXCEPTION"]}});
					}

					else if ($.address.parameter("tab") == "cancellations") {
						return  new MoneyTransferInquiryRequest({criteria:{statusList :["CANCELLATION_SUBMITTED","CANCELLED","CANCELLATION_APPROVED"]}});
					}

					else{
						var transactionId = "",
						confirmationNumber = "",
						currencyCode = "",
						locationName = "",
						organizationName = "",
						member = "",
						Tets="",
						statusList="";
						if(($.address.parameter("transactionId") !== "|")&&(!$.pgsi.isNullOrUndefined($.address.parameter("transactionId")))){
							transactionId = $.address.parameter("transactionId").replace(/-/g,"");
						}else{
							transactionId = 	null;
						}
						if(($.address.parameter("number") !== "|")&&(!$.pgsi.isNullOrUndefined($.address.parameter("number")))){
							confirmationNumber = parseInt($.address.parameter("transactionId"),10)
						}else{
							confirmationNumber = 	null;
						}
						if(($.address.parameter("payer") !== "|")&&(!$.pgsi.isNullOrUndefined($.address.parameter("payer")))){
							currencyCode = 	$.address.parameter("payer");
						}else{
							currencyCode = 	null;
						}
						if(($.address.parameter("transaction_type") !== "|")&&(!$.pgsi.isNullOrUndefined($.address.parameter("transaction_type")))){
							//var a = $.address.parameter("transaction_type").split("|")
							statusList = 	$.pgsi.enums.fetchLabelsByValues("com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum",$.address.parameter("transaction_type").split("|"))
							//$.address.parameter("transaction_type");
						}else{
							statusList = 	null;
						}

						if((($.address.parameter("memberId") !== "|")&&(!$.pgsi.isNullOrUndefined($.address.parameter("memberId"))))||
							(($.address.parameter("phone") !== "|")&&(!$.pgsi.isNullOrUndefined($.address.parameter("phone"))))||
							(($.address.parameter("last") !== "|")&&(!$.pgsi.isNullOrUndefined($.address.parameter("last")))) ||
							(($.address.parameter("pinNumber") !== "|")&&(!$.pgsi.isNullOrUndefined($.address.parameter("pinNumber")))) ||
							(($.address.parameter("first") !== "|")&&(!$.pgsi.isNullOrUndefined($.address.parameter("first"))))){

							var memberId = "",primaryPhoneNumber=null,lastName="",firstName="",pinNumber=null;

							if(($.address.parameter("memberId") !== "|")&&(!$.pgsi.isNullOrUndefined($.address.parameter("memberId")))) {
								memberId  = $.address.parameter("memberId").replace(/-/g,"");
							}else{
								memberId  = 	null;
							}
							if(($.address.parameter("phone") !== "|")&&(!$.pgsi.isNullOrUndefined($.address.parameter("phone")))) {
								primaryPhoneNumber = $.address.parameter("phone");
							}
							if(($.address.parameter("last") !== "|")&&(!$.pgsi.isNullOrUndefined($.address.parameter("last")))) {
								lastName = 	$.address.parameter("last");
							}else{
								lastName = 	null;
							}
							if(($.address.parameter("first") !== "|")&&(!$.pgsi.isNullOrUndefined($.address.parameter("first")))) {
								firstName = 	$.address.parameter("first");
							}else{
								firstName = 	null;
							}

							if (($.address.parameter("pinNumber") !== "|")&&(!$.pgsi.isNullOrUndefined($.address.parameter("pinNumber")))) {
								pinNumber = $.address.parameter("pinNumber");
							}

							member = {
								participantId : memberId,
								lastName : lastName,
								firstName : firstName,
								pinNumber : pinNumber
							};

						}else{
							member = null;
						}
						if(($.address.parameter("organization") !== "|")&&(!$.pgsi.isNullOrUndefined($.address.parameter("organization")))){
							organizationName = $.address.parameter("organization");
						}else{
							organizationName = 	null;
						}
						if(($.address.parameter("location") !== "|")&&(!$.pgsi.isNullOrUndefined($.address.parameter("location")))){
							locationName = $.address.parameter("location");
						}else{
							locationName = 	null;
						}

						var criteria = {
							transactionId : transactionId,
							confirmationNumber : confirmationNumber,
							currencyCode : currencyCode,
							locationName : locationName,
							organizationName : organizationName,
							member : member,
							statusList : statusList,
							primaryPhoneNumber : primaryPhoneNumber
						};

						return  new MoneyTransferInquiryRequest({
							criteria : criteria
						});
					}
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
			headerData 		: $.pgsi.locale.get("pages.batches.transaction.table.confirmation"),
			order			: "status",
			mRender	 		: pgsi.pages.transaction.fnConfirmationLink,
			sDefaultContent : "",
			sClass          : "actioncell",
			bSortable 		: true,
			sWidth			: "7%"
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
			headerData 		: $.pgsi.locale.get("pages.batches.page.id"),
			order			: "transferDate",
			mRender	 		: pgsi.pages.transaction.fnMoneyTransferBatchId,
			sDefaultContent : "",
			bSortable 		: true
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
			headerData 		: $.pgsi.locale.get("pages.transaction.table.sent"),
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
			sDefaultContent : "amt",
			bSortable 		: false
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


		<c:choose>
			<c:when test="${not empty refresh}">
				aaData : "refresh",
			</c:when>
			<c:when test="${empty response}">
				aaData : null,
		    </c:when>
		    <c:otherwise>
		    	aaData : ${response},
		    </c:otherwise>
		</c:choose>


		oSettings :
		{
			sortEnum      	: "",
			iDefaultCol   	: 0
		},

		rowCallback : function(nRow, aData, iDisplayIndex, oColumn) {

			// Adding sdn class to row
			if (aData.moneyTransferDetail.member.sdnstatus === "POSITIVE") {

				$(nRow).addClass("sdn1");
			}

			else if (aData.moneyTransferDetail.member.sdnstatus === "PENDING_INVESTIGATION" || aData.moneyTransferDetail.member.sdnstatus === "PENDING_FEDERAL_INVESTIGATION" ) {
				$(nRow).addClass("sdn2");
			}

			var oActionSummary="";
			var sButtonStatus="";
			var sButtonDelete = "";

			sButtonEdit    = '<a class="action alist view-money-transfer" data-id="' + aData.id + '" title="View/Edit" href="transaction/view?transactionId='+aData.id+'><span class="icon-nav icon-pencil"></span></a>'
			sButtonApprove = '<a class="action yes approve-money-transfer" data-id="' + aData.id + '" title="Approve" href="#"><span class="icon-nav icon-check-mark-circle-two"></span></a>'
			sButtonCancel  = '<a class="action no cancel-money-transfer" data-id="' + aData.id + '" title="Cancel" href="#"><span class="icon-nav icon-minus-circle"></span></a>'
			sButtonView    = '<a class="action" data-id="' + aData.id + '" title="View Details for Transaction #[9999999]" href="#"><span class="icon-nav icon-search-find"></span></a>'

			if(aData.statusList.length > 0){
				if(aData.statusList[aData.statusList.length -1 ].status == "PENDING_APPROVAL"){
					sButtonView = "";
					if(aData.foreignExchangeRate <= 0){
						sButtonApprove = "";
					}
				}else if((aData.statusList[aData.statusList.length -1 ].status == "APPROVED")||(aData.statusList[aData.statusList.length -1 ].status == "MODIFICATION_APPROVED")||(aData.statusList[aData.statusList.length -1 ].status == "CANCELLATION_APPROVED")||(aData.statusList[aData.statusList.length -1 ].status == "APPROVED_NOT_FUNDED")){
					sButtonView = "";
					sButtonApprove = "";
				}else if((aData.statusList[aData.statusList.length -1 ].status == "ON_HOLD")||(aData.statusList[aData.statusList.length -1 ].status == "PENDING_ACH")||(aData.statusList[aData.statusList.length -1 ].status == "PAID")||(aData.statusList[aData.statusList.length -1 ].status == "SEIZED")){
					sButtonEdit = "";
					sButtonApprove = "";
					sButtonCancel = "";
				}else if(aData.statusList[aData.statusList.length -1 ].status == "CANCELLED"){
					str = $('#title .list').text()
					if(str.indexOf("Pending Approval") == -1){
						sButtonEdit = "";
						sButtonApprove = "";
						sButtonCancel = "";
					}else{
						sButtonEdit = "";
						sButtonCancel = "";
					}
				}else if(aData.statusList[aData.statusList.length -1 ].status == "REJECTED"){
					sButtonView = "";
					sButtonApprove = "";
				}
				else if(aData.statusList[aData.statusList.length -1 ].status == "NOT_PAID"){
					sButtonApprove = "";
					sButtonCancel = "";
				}else if(aData.foreignExchangeRate <= 0){
					sButtonApprove = "";
				}

				var fnCallback = function(oResponse) {

	 				$.pgsi.table.reloadTable({
						table 		: pgsi.pages.batches.transactionTable,
						iStart 		: 0
					});
				};

				oActionSummary = $("<div>" + sButtonEdit + sButtonApprove + sButtonCancel + sButtonView + "</div>");

				oActionSummary.find('a.approve-money-transfer, a.cancel-money-transfer').click(function(e) {

					e.preventDefault();

					if (pgsi.util.page.fnIsSDNFlagged(aData.sdnstatus)) {
						return;
					}

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
					 			fnCallback
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
					 			fnCallback
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
			    eventName 	: "transactionList",
			    arguments 	: ["table"]
			});

		}
	}
	));

	var aFilters = ['transaction','member','employer','transferstatus'];

	var filters = pgsi.util.filter.filterArrayToObject(aFilters);
	pgsi.util.filter.init(oFilterPreLoad, filters, function(oResponse)
	{
		$.pgsi.filter.create(
		{
			element			: ".filter",
			tagsDiv			: ".filter-results-container div.first",
			title			: $.pgsi.locale.get("commons.pages.filterTitle"),
			table 			:  pgsi.pages.batches.transactionTable,
			filters 		: oResponse,
			openFirstFilters: 4
		});

	});

	//clear all Filter TODO
	$("#clear-all").on("click", function(e)
	{
		$.address.parameter("transactionId")
		$.address.parameter("number","");
		$.address.parameter("payer","");
		$.address.parameter("memberId","");
		$.address.parameter("phone","");
		$.address.parameter("last","");
		$.address.parameter("first","");
		$.address.parameter("organization","");
		$.address.parameter("location","");
		$.address.parameter("transaction_type","");
		$.address.parameter("transactionId","");
		$.address.parameter("pinNumber","");
		pgsi.util.page.fnReloadTable(pgsi.pages.batches.transactionTable);
	});

});
</script>
</sec:authorize>
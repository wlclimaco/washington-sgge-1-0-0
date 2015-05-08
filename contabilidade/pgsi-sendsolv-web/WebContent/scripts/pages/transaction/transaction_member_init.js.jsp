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

	$.pgsi.listener.wait({
		eventName 	: "transactionMemberList",
		arguments 	: ["table"],
		fnCallback 	: $.pgsi.progressBar.stopGlobal
	});

	/** * jQuery dataTable setup ** */
	pgsi.pages.batches.transactionTable = $('#data_list_member').dataTable($.pgsi.table.setTable(
	{
		id 			: "#data_list_member",
		sAjaxSource : "api/transaction/transactions",
		bPreLoad	: true,

		ajax :
		{
			sObj		: "moneyTransferList",
			oRequest	: MoneyTransferInquiryRequest,
			fnRequest 	: function(){

				member = {
					id : parseInt($.address.parameter("memberId"),10)
				};


				var criteria = {
					member : member
				};

				return  new MoneyTransferInquiryRequest({
					criteria : criteria,
					sortExpressions:[{field:"status",direction:"Descending"}],
					preQueryCount:true,
					startPage:0,
					pageSize:25
				});
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
			headerData 		: $.pgsi.locale.get("pages.batches.transaction.table.deliver.method"),
			order			: "transferDate",
			mRender	 		: pgsi.pages.transaction.fnDeliverMethod,
			sDefaultContent : "",
			bSortable 		: false
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
			headerData 		: $.pgsi.locale.get("pages.batches.transaction.table.Transferred"),
			order			: "country_column",
			mRender	 		: pgsi.pages.transaction.fnCurrency,
			sDefaultContent : "",
			bSortable 		: false
		}
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

		},
		drawCallback: function (oSettings){

		},

		fnInitComplete: function (oSettings, json)
		{
			$.pgsi.listener.notify({
			    eventName 	: "transactionMemberList",
			    arguments 	: ["table"]
			});

		}
	}
	));

});
</script>
</sec:authorize>
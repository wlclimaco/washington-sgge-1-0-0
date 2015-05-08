<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
<script type="text/javascript">
/**
 * @namespace pgsi.pages.payments
 * @description The init namespace for the Spread Review Page.
 * @author Flavio Tosta
 */

<c:choose>
	<c:when test="${not empty refresh}">
		var aaData = "refresh";
	</c:when>
	<c:when test="${empty money_transfer_response}">
		var aaData = null;
	</c:when>
	<c:otherwise>
		var aaData = ${money_transfer_response};
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${empty batch_response}">
	   	var oBatch = null;
	   </c:when>
	<c:otherwise>
	  	var oBatch = ${batch_response};
	</c:otherwise>
</c:choose>

pgsi.pages.payments.spreads.view.fnInit(oBatch);


/** * jQuery dataTable setup ** */
	pgsi.pages.payments.spreads.table.oTable = $('#dialog_data_list').dataTable($.pgsi.table.setTable(
	{
		id 			: "#dialog_data_list",
		sAjaxSource : "api/payment/fetch_transfer_batch_Id",
		bPreLoad	: true,
		sCheckbox 	: "id",

		ajax :
		{
			sObj		: "moneyTransferList",
			oRequest	: MoneyTransferInquiryRequest,
			fnRequest 	: function(){
				return new MoneyTransferInquiryRequest({criteria:{
					moneyTransferBatchId : oBatch.moneyTransferBatchList[0].id
				}});
			}
		},

		aoColumns :
		[
		{
			headerData 		: $.pgsi.locale.get("pages.batches.tables.payer"),
			mRender	 		: pgsi.pages.payments.spreads.table.fnDisplayPayerName,
			sDefaultContent : "",
			bSortable 		: false
		},

		{
			headerData 		: $.pgsi.locale.get("pages.batches.tables.currency"),
			mData			: "originAmount.currency.code",
			sDefaultContent : "",
			bSortable 		: false
		},

		{
			headerData 		: $.pgsi.locale.get("pages.batches.tables.effectiveRate"),
			mRender			: pgsi.pages.payments.spreads.table.fnDisplayEffectiveRate,
			sDefaultContent : "",
			bSortable 		: false
		},

		{
			headerData 		: $.pgsi.locale.get("pages.batches.tables.spread"),
			mRender	 		: pgsi.pages.payments.spreads.table.fnDisplaySpreadInput,
			sDefaultContent : "",
			bSortable 		: false
		},

		{
			headerData 		: $.pgsi.locale.get("pages.batches.tables.customerRate"),
			mRender			: pgsi.pages.payments.spreads.table.fnDisplayCustomerRate,
			sDefaultContent : "",
			bSortable 		: false
		}
		],


		aaData : aaData,


		oSettings :
		{
			sortEnum      	: ""
		},

		rowCallback : function(nRow, aData, iDisplayIndex, oColumn) {

		},

		drawCallback: function (oSettings){

		},

		fnInitComplete: function (oSettings, json)
		{
			$selects = $("#dialog_data_list_wrapper").find(".dataTables_length").find("select");

			$selects.outerWidth(62).selectmenu({

  				change: function( event, ui ) {
  					$selects.val(ui.item.value).selectmenu("refresh");
  					$selects.first().trigger("change");
  				}
			});
		}
	}
	));

	$('#dialog_data_list').find('thead tr:eq(0) th:eq(0) input').change(function () {
		var $checkObjs = $('#dialog_data_list tbody').find('input.checkObj');
		if ($(this).prop('checked')) {
			$checkObjs.prop('checked', true).trigger('change');
		}
		else {
			$checkObjs.prop('checked', false).trigger('change');
		}

		if ($(this).is(":checked")) {
			$checkObjs.closest("tr").addClass('selected');
		}else{
			$checkObjs.closest("tr").removeClass('selected');
		}
	});

</script>
</sec:authorize>
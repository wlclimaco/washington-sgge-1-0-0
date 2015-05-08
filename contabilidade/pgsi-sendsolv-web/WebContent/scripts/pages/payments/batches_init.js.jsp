<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
<script type="text/javascript">
/**
 * @namespace pgsi.pages.payments
 * @description The init namespace for the Batch List Page.
 * @author Flavio Tosta, Washington Costa
 */
$(document).ready(function()
{
	$.pgsi.listener.wait({
		eventName 	: "batchesList",
		arguments 	: ["table"],
		fnCallback 	: $.pgsi.progressBar.stopGlobal
	});
	/** * jQuery dataTable setup ** */
	pgsi.pages.batches.batchTable = $('#data_list').dataTable($.pgsi.table.setTable(
	{
		id 			: "#data_list",
		sAjaxSource : "api/payment/fetchAllBatches",
		bPreLoad	: false,
		sCheckbox 	: "id",

		ajax :
		{
			sObj		: "moneyTransferBatchList",
			oRequest	: MoneyTransferBatchInquiryRequest,
			fnRequest 	: function(){

				sessionStorage.batchesTab = $.address.parameter("tab");

				if (($.pgsi.isNullOrUndefined(sessionStorage.batchesTab))||(sessionStorage.batchesTab == "undefined")||(sessionStorage.batchesTab == "ALL")) {
					sessionStorage.batchesTab = "BATCH_CREATION|PENDING_APPROVAL|SPREAD_REVIEW|READY_FOR_RELEASE|RELEASED|ACH_PROCESSING|ACH_EXCEPTIONS|ON_HOLD";
				}

				// Security check
				sessionStorage.batchesTab =  !pgsi.util.page.fnCheckXSS(sessionStorage.batchesTab) ? sessionStorage.batchesTab : null;

				var aMoneyTransferBatchInquiryRequest = new MoneyTransferBatchInquiryRequest({criteria:{statusList: sessionStorage.batchesTab.split("|")}});

				return aMoneyTransferBatchInquiryRequest;
			}
		},

		aoColumns :
		[
		{
			headerData 		: $.pgsi.locale.get("pages.batches.tables.id"),
			order			: "id",
			mRender	 		: pgsi.pages.batches.fnLinkId,
			sDefaultContent : "",
			sWidth			: "5%",
			bSortable 		: true
		},
		{
			headerData 		: $.pgsi.locale.get("pages.batches.tables.batch.status"),
			order			: "status",
			mRender	 		: pgsi.pages.batches.fnBatcheStatus,
			sDefaultContent : "",
			bSortable 		: true
		},
		{
			headerData 		: $.pgsi.locale.get("pages.batches.tables.location"),
			order			: "locationName",
			mRender	 		: pgsi.pages.batches.fnCreateLocationNameLink,
			sDefaultContent : "",
			bSortable 		: true
		},
		{
			headerData 		: $.pgsi.locale.get("pages.batches.tables.region"),
			order			: "state_column",
			mRender	 		: pgsi.pages.batches.fnRegion,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("pages.batches.tables.created"),
			order			: "transferDate",
			mRender	 		: pgsi.pages.batches.fnCreateBatch,
			sDefaultContent : "",
			bSortable 		: true
		},
		{
			headerData 		: $.pgsi.locale.get("pages.batches.tables.transfer"),
			order			: "country_column",
			mRender	 		: pgsi.pages.batches.fnTransferDate,
			sDefaultContent : "",
			bSortable 		: false
		},

		{
			headerData 		: $.pgsi.locale.get("pages.batches.tables.amount"),
			order			: "country_column",
			mRender	 		: pgsi.pages.batches.fnOriginAmount,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("pages.batches.tables.total"),
			order			: "country_column",
			mRender	 		: pgsi.pages.batches.fnTotalTransfer,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("pages.batches.tables.transactions"),
			order			: "country_column",
			mRender	 		: pgsi.pages.batches.fnTotalTransferErrorPend,
			sDefaultContent : "",
			sClass 			: 'action',
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("pages.batches.tables.action"),
			order			: "actionDueDate",
			mRender	 		: pgsi.pages.batches.fnActionDue,
			sDefaultContent : "",
			sClass 			: 'action',
			bSortable 		: true
		},
		{
			headerData 		: $.pgsi.locale.get("pages.batches.tables.comments"),
			order			: "country_column",
			mRender	 		: pgsi.pages.batches.fnNoteList,
			sDefaultContent : "",
			sClass 			: 'comments',
			bSortable 		: false
		},
		],

		oSettings :
		{
			sortEnum      	: "",
			iDefaultCol   	: 10
		},

		rowCallback : function(nRow, aData, iDisplayIndex, oColumn) {

			var oActionSummary;
			var sEditButton = '<a href="payment/batches_view?batchesId=' + aData.id + '" title="'+ $.pgsi.locale.get("pages.batches.tables.review", pgsi.util.page.fnInsertMask("***-99999",aData.key ) || aData.id+"") + '" id="update" class="icon-nav icon-pencil alist icon-small-button"></a>';
			var sDeleteButton = "";
			var sSpreadReviewButton = "";
			var sReadyForReleaseButton = "";
			var sReactivateButton = "";

			sessionStorage.batchesTab = !pgsi.util.page.fnCheckXSS(sessionStorage.batchesTab) ? sessionStorage.batchesTab : "";

			if ((aData.currentStatus.statusValue == 2 ) && (sessionStorage.batchesTab != "PENDING_APPROVAL")) {
				$('td:eq(2)', nRow).addClass('urg-1');
				$('td:eq(10)', nRow).addClass('urg-1');
			}

			// All Batches filter
			switch (aData.currentStatus.status) {

					case "PENDING_APPROVAL":
					case "READY_FOR_RELEASE":
					case "SPREAD_REVIEW":
						// Adding Cancel Button
						sDeleteButton = '<a href="#" class="icon-nav icon-minus-circle deleteDialog icon-small-button cancel" title="' + $.pgsi.locale.get("pages.batches.tables.cancel", pgsi.util.page.fnInsertMask("***-99999",aData.key ) || aData.id+"") + '"></a>';
					break;
					default:
						$(nRow).find('td:eq(0)').find('input').remove();
					break;
			}

			if (aData.currentStatus.status === "SPREAD_REVIEW") {
				sSpreadReviewButton = '<a href="#" class="icon-nav icon-table deleteDialog icon-small-button spreadReview" title="' + $.pgsi.locale.get("pages.batches.tables.reviewSpreads", pgsi.util.page.fnInsertMask("***-99999",aData.key ) || aData.id+"") + '"></a>';
			}

			else if (aData.currentStatus.status === "READY_FOR_RELEASE") {
				sReadyForReleaseButton = '<a href="#" class="icon-nav icon-caret-right icon-small-button release" title="' + $.pgsi.locale.get("pages.batches.view.release.batch", pgsi.util.page.fnInsertMask("***-99999",aData.key ) || aData.id+"") + '"></a>';
			}

			else if (aData.currentStatus.status === "EXPIRED") {
				sReactivateButton = '<a href="#" class="icon-nav icon-check-mark icon-small-button reactivate" title="' + $.pgsi.locale.get("pages.batches.view.reactivateBatch", pgsi.util.page.fnInsertMask("***-99999",aData.key ) || aData.id+"") + '"></a>';
			}
			oActionSummary = $("<div><div>" + sEditButton + sSpreadReviewButton + sReadyForReleaseButton + sReactivateButton + sDeleteButton + "</div></div>");
			oActionSummary.find('a').click(function(e) {

						e.preventDefault();

						var oMoneyTransferStatusList = new Array();

						var oMoneyTransferBatchStatus = new MoneyTransferBatchStatus({
							id : null,
							moneyTransferBatchId : aData.id,
							actionDueDate : (new Date()).getTime(),
							status : null,
							modelAction : "INSERT"
						});

						oMoneyTransferStatusList.push(oMoneyTransferBatchStatus);

						// Attaches cancel event
						if ($(this).hasClass("cancel")) {

							oMoneyTransferStatusList[0].status = "CANCELLED";

							pgsi.util.actiondialog.launchActionDialog (
					 			"changeBatchStatus",
					 			pgsi.pages.batches.dialogSettings.changeBatchStatus(
					 				$.pgsi.locale.get('pages.batches.dialog.cancel.title'),
					 				$.pgsi.locale.get('pages.batches.dialog.cancel.message'),
					 				oMoneyTransferStatusList,
					 				null
					 			)
 							);
						}

						// Attaches spread review event
						else if ($(this).hasClass("spreadReview")) {
							pgsi.util.actiondialog.launchActionDialog (
					 			"reviewSpreads",
					 			pgsi.pages.batches.dialogSettings.reviewSpreads(
					 				$.pgsi.locale.get('pages.batches.dialog.reviewspread.title', (aData.id).toString()),
					 				aData.id,
					 				false
					 			)
 							);
						}

						// Attaches release batch event
						else if ($(this).hasClass('release')) {
							oMoneyTransferStatusList[0].status = "RELEASED";

							pgsi.util.actiondialog.launchActionDialog (
					 			"changeBatchStatus",
					 			pgsi.pages.batches.dialogSettings.changeBatchStatus(
					 				$.pgsi.locale.get('pages.batches.dialog.releaseBatch.title'),
					 				$.pgsi.locale.get('pages.batches.dialog.releaseBatch.message'),
					 				oMoneyTransferStatusList,
					 				null
					 			)
 							);
						}

						// Attaches reactivate batch event
						else if ($(this).hasClass('reactivate')) {
							oMoneyTransferStatusList[0].status = "PENDING_APPROVAL";

							pgsi.util.actiondialog.launchActionDialog (
					 			"changeBatchStatus",
					 			pgsi.pages.batches.dialogSettings.changeBatchStatus(
					 				$.pgsi.locale.get('pages.batches.dialog.reactivate.title'),
					 				$.pgsi.locale.get('pages.batches.dialog.reactivate.message'),
					 				oMoneyTransferStatusList,
					 				null
					 			)
 							);
						}

					});

			// Attaches hover effect for displaying edit/delete buttons
			$('td:eq(1)', nRow).hover(function() {
				$(this).find('.icon-nav').removeClass('hide');
				$(this).append(oActionSummary);
			},
				function() {
					$(this).find('.icon-nav').addClass('hide');
				});

		},

		drawCallback: function (oSettings){

			if ((sessionStorage.batchesTab === "EXPIRED") || (sessionStorage.batchesTab === "COMPLETE") || (sessionStorage.batchesTab === "BATCH_PROCESSED|CANCELLED")) {
				$('.action').hide();
				$('.comments').show();
				$('td.action').hide();

			}

			else if ((!pgsi.util.page.fnCheckXSS($.address.parameter('tab')) && $.address.parameter('tab') === "ALL" ) || ($.pgsi.isNullOrUndefined(sessionStorage.batchesTab))) {
				$('.comments').show();
				$('.action').show();
			}

			else {
				$('.comments').hide();
				$('td.comments').hide();
				$('.action').show();
			}

			$("#link-fund").addClass('disabled');

			if (!$("#link-fund").hasClass('hide')) {
				$('#data_list tbody').find('input.checkObj').on('change', function() {
					if ($('#data_list tbody tr>td input:checkbox:checked').length > 0) {
						$('#link-fund').removeClass("disabled");
					}

					else {
						$("#link-fund").addClass('disabled');
					}

				});
			}



			if((sessionStorage.batchesTab == "BATCH_PROCESSED|CANCELLED")||(sessionStorage.batchesTab == "PENDING_APPROVAL")||
					(sessionStorage.batchesTab == "EXPIRED")||(sessionStorage.batchesTab == "ACH_EXCEPTIONS|ACH_PROCESSING")){
				$('.checkObj').hide();
			}else {
				$('#select-page').show()
			}
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
			    eventName 	: "batchesList",
			    arguments 	: ["table"]
			});
		}
	}
	));


	$('#link-fund').click(function(e) {
		e.preventDefault();

		if (!$(this).hasClass('disabled')) {
			var sStatus   =  !pgsi.util.page.fnCheckXSS($(this).attr("data-status"))  ? $(this).attr("data-status")  : null;
			var sMessage  =  !pgsi.util.page.fnCheckXSS($(this).attr("data-message")) ? $(this).attr("data-message") : null;
			var sTitle    =  !pgsi.util.page.fnCheckXSS($(this).attr("data-title"))   ? $(this).attr("data-title")   : null;

			var oMoneyTransferStatusList = pgsi.pages.batches.fnMoneyTransferBatchStatusListFromTable(sStatus);

			// Call change status dialog
			if ($.pgsi.table.checkbox(pgsi.pages.batches.batchTable).selected().length > 0) {
				pgsi.util.actiondialog.launchActionDialog (
				 	"changeBatchStatus",
				 	pgsi.pages.batches.dialogSettings.changeBatchStatus(
				 		sTitle,
				 		sMessage,
				 		oMoneyTransferStatusList,
				 		null
				 	)
		 		);
			}
		}

	});

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

});

</script>
</sec:authorize>
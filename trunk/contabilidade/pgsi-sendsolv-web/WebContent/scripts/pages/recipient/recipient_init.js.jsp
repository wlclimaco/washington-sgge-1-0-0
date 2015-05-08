
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">
	<script type="text/javascript">
/**
 * @namespace pgsi.pages.recipient
 * @description The init namespace for the Recipient List Page.
 * @author Washington Costa
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
		eventName 	: "recipientList",
		arguments 	: ["table"],
		fnCallback 	: $.pgsi.progressBar.stopGlobal
	});

	/** * jQuery dataTable setup ** */
	pgsi.pages.recipient.recipientTable = $('#data_list').dataTable($.pgsi.table.setTable(
	{
		id 			: "#data_list",
		sAjaxSource : 'api/recipient/fetchall',
		bPreLoad	: true,
		pagingType  : "full",

		ajax :
		{
			sObj		: 'recipientList',
			oRequest	: RecipientInquiryRequest,
			fnRequest 	: pgsi.pages.recipient.fnCreateRequest
		},

		aoColumns :
		[
		{
			headerData 		: $.pgsi.locale.get("commons.pages.ID"),
			order			: "participantId",
			sDefaultContent : "",
			mRender         : pgsi.pages.recipient.fnCreateIdLink,
			bSortable 		: true,
			width           : '10px'

		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.name"),
			order			: "lastName",
			mRender         : pgsi.pages.recipient.fnCreateRecipientNameLink,
			sDefaultContent : "",
			bSortable 		: true,
			width           : '50px',
			sClass          : "name-col"

		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.city"),
			order			: "city_column",
			mRender 		: pgsi.pages.recipient.fnRecipientCity,
			mData	 		: "null",
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.state"),
			order			: "state_column",
			mRender 		: pgsi.pages.recipient.fnRecipientState,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.country"),
			order			: "country_column",
			mRender	 		: pgsi.pages.recipient.fnRecipientCountry,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("pages.recipient.table.title.transfer"),
			order			: "personStatus",
			mRender 		: pgsi.pages.recipient.fnFillTransferMethods,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.sdn.status"),
			order			: "personStatus",
			mRender 	    : pgsi.pages.recipient.fnRecipientSDNStatus,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.risklevel"),
			order			: "risklevel_column",
			mRender 		: pgsi.pages.recipient.fnRecipientRiskLevel,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.status"),
			order			: "status_column",
			mRender		    : pgsi.pages.recipient.fnRecipientStatus,
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
			sortEnum      	: 'com.prosperitasglobal.sendsolv.model.OrganizationOrderByEnum',
			iDefaultCol   	: 0
		},

		/**
		* Callback function used by the datatables plugin called for every row
		*/
		rowCallback : function(nRow, aData, iDisplayIndex, oColumn) {

			// Adding sdn class to row
			if (aData.sdnstatus === "POSITIVE") {

				$(nRow).addClass("sdn1");
			}

			else if (aData.sdnstatus === "PENDING_INVESTIGATION" || aData.sdnstatus === "PENDING_FEDERAL_INVESTIGATION" ) {
				$(nRow).addClass("sdn2");
			}

			var oActionSummary = "";
			var sButtonStatus = "";
			var sButtonDelete = "";

			if (aData.personStatusValue === 1) {
				sButtonStatus = '<a href="#" class="deactivate"><span class="icon-small-button deactivate icon-nav icon-minus-circle" title="Disable ' + aData.firstName + ' ' + aData.lastName + '"></a>'
			}

			else if ((aData.personStatusValue === 2) || (aData.personStatusValue === 3) || (aData.personStatusValue === 4)) {
				sButtonStatus = '<a href="#" class="active"><span class="icon-small-button active icon-nav icon-check-mark" title="' + $.pgsi.locale.get("pages.view.activate") + ' ' + aData.firstName + ' ' + aData.lastName + '"></span></a>'
			}

			<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">

				sButtonDelete = '<a href="#"  class="icon-nav icon-trash-bin deleteDialog icon-small-button" title="' + $.pgsi.locale.get("commons.pages.delete") + ' ' + aData.firstName + ' ' + aData.lastName +'">'
					  + '</a>';
			 </sec:authorize>

			oActionSummary = $('<div><div><a href="recipient/view?tab=info&recipientId=' + aData.id + '" title="'+ $.pgsi.locale.get("commons.pages.edit") + ' ' + aData.firstName + ' ' + aData.lastName +'" id="update" class="icon-nav icon-pencil alist icon-small-button"></a>'
					+ sButtonStatus
					+ sButtonDelete
					+ pgsi.util.page.fnInsertButtonSDNSAR(aData,"recipient")+"</div></div>");

			oActionSummary.find('a.deleteDialog, a.active, a.deactivate ,a.sarDialog').click(function(e) {

				e.preventDefault();

				if (pgsi.util.page.fnIsSDNFlagged(aData.sdnstatus)) {
					return;
				}

				// Attaches Delete Dialog call for delete button

					var oRequest = new RecipientMaintenanceRequest({recipient : {id : aData.id, firstName : aData.firstName}});
					var fnCallBack = function(oResponse) {

						if (oResponse.operationSuccess == true) {

							// Validations for change pagination when delete one or more groups of last page.
								var iStart;
								var oSettings = pgsi.pages.recipient.recipientTable.fnSettings();

									// If exist just one group at last page and this group is deleted, the pagination back to previous page.
									if (((oSettings._iRecordsDisplay - 1) % $('.dataTables_length').find('select').val() === 0)) {
										iStart = (oSettings._iRecordsDisplay - 1) - oSettings._iDisplayLength;
									}

								$.pgsi.table.reloadTable({
									table 		: pgsi.pages.recipient.recipientTable,
									iStart 		: iStart
								});
							}else{
								pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
							}
					}
				if($(this).hasClass('deleteDialog')){
					pgsi.util.actiondialog.launchActionDialog(
						"deleteDialog",
						 pgsi.pages.business.dialogSettings.deleteDialog(
						 	"api/recipient/delete",
						 	 oRequest,
						 	 $.pgsi.locale.get("pages.recipient.dialog.title", oRequest.recipient.firstName + " " + aData.lastName),
						 	 fnCallBack,
						 	 $.pgsi.locale.get("commons.pages.erroView", $.pgsi.locale.get("commons.pages.recipient"))
						 ));

				}else if($(this).hasClass('deactivate')){
					pgsi.util.page.fnUpdateStatus('api/recipient/fetch',parseInt(aData.id,10),'recipient',2,fnCallBack,"Deactivate Recipient for "+ aData.firstName + " " +aData.lastName, "<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.deactivate"),"Recipient")+"<br>" +$.pgsi.locale.get("pages.person.dialog.status.information",$.pgsi.locale.get("pages.view.deactivate"))+"<span>",true);
				}else if($(this).hasClass('active')){
					pgsi.util.page.fnUpdateStatus('api/recipient/fetch',parseInt(aData.id,10),'recipient',1,fnCallBack,"Activate Recipient for "+ aData.firstName + " " +aData.lastName, "<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.activate"),"Recipient")+"<br>" +$.pgsi.locale.get("pages.person.dialog.status.information",$.pgsi.locale.get("pages.view.activate"))+"<span>",true);
				}else if($(this).hasClass('sarDialog')){
					pgsi.util.actiondialog.launchActionDialog(
							"dialogSARDetail",
							 pgsi.pages.sar.dialogSettings.dialogSARDetail(
								 $.pgsi.locale.get("commons.title.table.SAR"),
								 aData.id,
								 "recipient"
							 ));
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

		fnInitComplete: function(oSettings, json)
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
				eventName 	: "recipientList",
				arguments 	: ["table"]
			});
		}
	}
	));

	if (!$.pgsi.isNullOrUndefined(oFilterPreLoad)) {
		// Filters
		var aFilters = ['recipient'];

		var filters = pgsi.util.filter.filterArrayToObject(aFilters);
		pgsi.util.filter.init(oFilterPreLoad, filters, function(oResponse)
		{
			$.pgsi.filter.create(
			{
				element			: ".filter",
				tagsDiv			: ".filter-results-container div.first",
				title			: $.pgsi.locale.get("commons.pages.filterTitle"),
				table 			:  pgsi.pages.recipient.recipientTable,
				filters 		: oResponse
			});
		});
	}

	//clear all Filter TODO
	$("#clear-all").on("click", function(e)
	{
		$.address.parameter("recipientId", "");
		$.address.parameter("memberId", "");
		$.address.parameter("phone", "");
		$.address.parameter("last", "");
		$.address.parameter("first", "");

		pgsi.util.page.fnReloadTable(pgsi.pages.recipient.recipientTable);
	});

});
</script>
</sec:authorize>
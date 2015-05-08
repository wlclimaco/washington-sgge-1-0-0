<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">
<script type="text/javascript">
/**
 * @namespace pgsi.pages.member
 * @description The init namespace for the Member List Page.
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
		eventName 	: "memberList",
		arguments 	: ["table"],
		fnCallback 	: $.pgsi.progressBar.stopGlobal
	});

	/** * jQuery dataTable setup ** */
	pgsi.pages.member.memberTable = $('#data_list').dataTable($.pgsi.table.setTable(
	{
		id 			: "#data_list",
		sAjaxSource : 'api/member/fetchall',
		bPreLoad	: false,
		pagingType  : "full",

		ajax :
		{
			sObj		: 'memberList',
			oRequest	: MemberInquiryRequest,
			fnRequest 	: pgsi.pages.member.fnCreateRequest
		},

		aoColumns :
		[
		{
			headerData 		: $.pgsi.locale.get("commons.pages.id"),
			order			: "participantId",
			sDefaultContent : "",
			mRender         : pgsi.pages.member.fnCreateIdLink,
			bSortable 		: true,
			width           : '10px'

		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.name"),
			order			: "lastName",
			mRender         : pgsi.pages.member.fnCreateMemberNameLink,
			sDefaultContent : "",
			bSortable 		: true,
			width           : '50px',
			sClass          : "name-col"

		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.location"),
			order			: "LOCATION_COLUMN",
			mRender         : pgsi.pages.member.fnCreateLocationListLink,
			sDefaultContent : "",
			bSortable 		: true,
			width           : '50px',
			sClass          : "location-col"

		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.city"),
			order			: "city_column",
			mRender 		: pgsi.pages.member.fnMemberCity,
			mData	 		: "null",
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.state"),
			order			: "state_column",
			mRender 		: pgsi.pages.member.fnMemberState,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.country"),
			order			: "country_column",
			mRender	 		: pgsi.pages.member.fnMemberCountry,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.sdn.status"),
			order			: "personStatus",
			mRender 	    : pgsi.pages.member.fnMemberSDNStatus,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.phone.primary"),
			order			: "phone_column",
			mRender 		: pgsi.pages.member.fnMemberPrimaryPhone,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.risklevel"),
			order			: "risklevel_column",
			mRender 		: pgsi.pages.member.fnMemberRiskLevel,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.status"),
			order			: "status_column",
			mRender		    : pgsi.pages.member.fnMemberStatus,
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

			var oActionSummary="";
			var sButtonStatus="";
			var sButtonDelete = "";

			if (aData.personStatusValue === 1) {
				sButtonStatus = '<a href="#" class="deactivate"><span class="icon-small-button deactivate icon-nav icon-minus-circle" title="Disable ' + aData.firstName + ' ' + aData.lastName + '"></a>'
			}

			else if ((aData.personStatusValue === 2) ||(aData.personStatusValue === 3)|| (aData.personStatusValue === 4)) {
				sButtonStatus = '<a href="#" class="active"><span class="icon-small-button active icon-nav icon-check-mark" title="' + $.pgsi.locale.get("pages.view.activate") + ' ' + aData.firstName + ' ' + aData.lastName + '"></span></a>'
			}

			<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">

				sButtonDelete = '<a href="#"  class="icon-nav icon-trash-bin deleteDialog icon-small-button" title="' + $.pgsi.locale.get("commons.pages.delete") + ' ' + aData.firstName + ' ' + aData.lastName +'">'
					  + '</a>';
			 </sec:authorize>

			oActionSummary = $('<div><div><a href="member/view?tab=info&memberId=' + aData.id + '" title="'+ $.pgsi.locale.get("commons.pages.edit") + ' ' + aData.firstName + ' ' + aData.lastName + '" id="update" class="icon-nav icon-pencil alist icon-small-button"></a>'
					+ sButtonStatus
					+ sButtonDelete
					+pgsi.util.page.fnInsertButtonSDNSAR(aData,"member")+
					'</div></div>');

			oActionSummary.find('a.deleteDialog, a.active, a.deactivate,a.sarDialog').click(function(e) {

				e.preventDefault();

				if (pgsi.util.page.fnIsSDNFlagged(aData.sdnstatus)) {
					return;
				}

				// Attaches Delete Dialog call for delete button

					var oRequest = new MemberMaintenanceRequest({member : {id : aData.id}});
					var fnCallBack = function(oResponse) {

						if (oResponse.operationSuccess == true) {

							// Validations for change pagination when delete one or more groups of last page.
								var iStart;
								var oSettings = pgsi.pages.member.memberTable.fnSettings();

									// If exist just one group at last page and this group is deleted, the pagination back to previous page.
									if (((oSettings._iRecordsDisplay - 1) % $('.dataTables_length').find('select').val() === 0)) {
										iStart = (oSettings._iRecordsDisplay - 1) - oSettings._iDisplayLength;
									}

								$.pgsi.table.reloadTable({
									table 		: pgsi.pages.member.memberTable,
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
						 	"api/member/delete",
						 	 oRequest,
						 	 $.pgsi.locale.get("pages.member.dialog.title", aData.firstName + " " +aData.lastName),
						 	 fnCallBack,
						 	 $.pgsi.locale.get("commons.pages.erroView", $.pgsi.locale.get("commons.pages.member"))
						 ));

				}else if($(this).hasClass('deactivate')){
					pgsi.util.page.fnUpdateStatus('api/member/fetch',parseInt(aData.id,10),'member',2,fnCallBack,"Deactivate Member for "+ aData.firstName + " " +aData.lastName, "<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.deactivate"),"Member")+"<br>" +$.pgsi.locale.get("pages.person.dialog.status.information",$.pgsi.locale.get("pages.view.deactivate"))+"<span>",true);
				}else if($(this).hasClass('active')){
					pgsi.util.page.fnUpdateStatus('api/member/fetch',parseInt(aData.id,10),'member',1,fnCallBack,"Activate Member for "+ aData.firstName + " " +aData.lastName, "<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.activate"),"Member")+"<br>"+$.pgsi.locale.get("pages.person.dialog.status.information",$.pgsi.locale.get("pages.view.activate"))+"<span>",true);
				}else if($(this).hasClass('sarDialog')){
					pgsi.util.actiondialog.launchActionDialog(
							"dialogSARDetail",
							 pgsi.pages.sar.dialogSettings.dialogSARDetail(
								 $.pgsi.locale.get("commons.title.table.SAR"),
								 aData.id,
								 "member"
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
				eventName 	: "memberList",
				arguments 	: ["table"]
			});
		}
	}
	));

	if (!$.pgsi.isNullOrUndefined(oFilterPreLoad)) {
		// Filters
		var aFilters = ['member','employer'];

		var filters = pgsi.util.filter.filterArrayToObject(aFilters);
		pgsi.util.filter.init(oFilterPreLoad, filters, function(oResponse)
		{
			$.pgsi.filter.create(
			{
				element			: ".filter",
				tagsDiv			: ".filter-results-container div.first",
				title			: $.pgsi.locale.get("commons.pages.filterTitle"),
				table 			:  pgsi.pages.member.memberTable,
				filters 		: oResponse
			});
		});
	}

	//clear all Filter TODO
	$("#clear-all").on("click", function(e)
	{
		$.address.parameter("transactionId", "");
		$.address.parameter("number","");
		$.address.parameter("payer","");
		$.address.parameter("memberId","");
		$.address.parameter("phone","");
		$.address.parameter("last","");
		$.address.parameter("first","");
		$.address.parameter("organization","");
		$.address.parameter("location","");
		$.address.parameter("transaction_type","");
		$.address.parameter("pinNumber", "");
		pgsi.util.page.fnReloadTable(pgsi.pages.member.memberTable);
	});

});
</script>
</sec:authorize>
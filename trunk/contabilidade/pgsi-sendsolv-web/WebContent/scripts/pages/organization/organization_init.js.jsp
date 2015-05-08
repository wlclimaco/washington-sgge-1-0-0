<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">
	<script type="text/javascript">
/**
 * @namespace pgsi.pages.organization
 * @description The init namespace for the Organization List Page.
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
		eventName 	: "organizationList",
		arguments 	: ["table"],
		fnCallback 	: $.pgsi.progressBar.stopGlobal
	});

	/** * jQuery dataTable setup ** */
	pgsi.pages.organization.organizationTable = $('#data_list').dataTable($.pgsi.table.setTable(
	{
		id 			: "#data_list",
		sAjaxSource : 'api/organization/fetchall',
		bPreLoad	: true,
		pagingType  : "full",

		ajax :
		{
			sObj		: 'organizationList',
			oRequest	: PagedInquiryRequest,
			fnRequest 	: function(){}
		},

		aoColumns :
		[
		{
			headerData 		: $.pgsi.locale.get("commons.pages.name"),
			order			: "name",
			mRender         : pgsi.pages.organization.fnCreateOrganizationNameLink,
			sDefaultContent : "",
			bSortable 		: true,
			width           : '50px',
			sClass          : "name-col"

		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.city"),
			order			: "city_column",
			mRender 		: pgsi.pages.organization.fnOrganizationCity,
			mData	 		: "null",
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.state"),
			order			: "state_column",
			mRender 		: pgsi.pages.organization.fnOrganizationState,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.country"),
			order			: "country_column",
			mRender	 		: pgsi.pages.organization.fnOrganizationCountry,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.industry"),
			order			: "industry_column",
			mData 		    : 'standardIndustrialClassificationCode.value',
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.sdn.status"),
			order			: "sdn_status_column",
			mRender 	    : pgsi.pages.organization.fnOrganizationSDNStatus,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.phone.primary"),
			order			: "phone_column",
			mRender 		: pgsi.pages.organization.fnOrganizationPrimaryPhone,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.members"),
			order			: "members_column",
			mData 		    : 'numberOfEmployees.name',
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.risklevel"),
			order			: "risklevel_column",
			mRender 		: pgsi.pages.organization.fnOrganizationRiskLevel,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.status"),
			order			: "status_column",
			mRender		    : pgsi.pages.organization.fnOrganizationStatus,
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
			var sButtonStatus = "";
			var sButtonDelete = "";

			<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">

				if (aData.statusValue === 1) {
					sButtonStatus = '<a href="#" class="deactivate"><span class="icon-small-button deactivate icon-nav icon-minus-circle" title="Disable ' + aData.name + '"></a>';
				}

				else if ((aData.statusValue === 2)||(aData.statusValue === 3)|| (aData.statusValue === 4)){
					sButtonStatus = '<a href="#" class="active"><span class="icon-small-button active icon-nav icon-check-mark" title="' + $.pgsi.locale.get("pages.view.activate") + ' ' + aData.name + '"></span></a>';
				}

				sButtonDelete = '<a href="#"  class="icon-nav icon-trash-bin deleteDialog icon-small-button" title="' + $.pgsi.locale.get("commons.pages.delete") + ' ' + aData.name + '"></a>';

			</sec:authorize>

			oActionSummary = $('<div><div><a href="organization/view?tab=info&organizationId=' + aData.id + '" title="' + $.pgsi.locale.get("commons.pages.edit") + ' ' + aData.name +  '" id="update" class="icon-nav icon-pencil alist icon-small-button"></a>'
					+ sButtonStatus
					+ sButtonDelete
					+ '<a href="location/add?organizationId=' + aData.id + '&organizationName=' + aData.name + '" class="icon-nav icon-pin-map addLocation icon-small-button alist" title="' + $.pgsi.locale.get("commons.pages.locationaddnew") + '">'
					+ '</a>'
					+pgsi.util.page.fnInsertButtonSDNSAR(aData,"organization")+"</div></div>");

			oActionSummary.find('a.deleteDialog, a.active, a.deactivate ,a.sarDialog').click(function(e) {

				e.preventDefault();

				if (pgsi.util.page.fnIsSDNFlagged(aData.sdnstatus)) {
					return;
				}

				var fnCallBack = function(oResponse) {

					if (oResponse.operationSuccess == true) {

						// Validations for change pagination when delete one or more groups of last page.
							var iStart;
							var oSettings = pgsi.pages.organization.organizationTable.fnSettings();

								// If exist just one group at last page and this group is deleted, the pagination back to previous page.
								if (((oSettings._iRecordsDisplay - 1) % $('.dataTables_length').find('select').val() === 0)) {
									iStart = (oSettings._iRecordsDisplay - 1) - oSettings._iDisplayLength;
								}

							$.pgsi.table.reloadTable({
								table 		: pgsi.pages.organization.organizationTable,
								iStart 		: iStart
							});
						}

						else {
							pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
						}
				}

				// Attaches Delete Dialog call for delete button
				if ($(this).hasClass('deleteDialog')) {
					var oRequest = new OrganizationMaintenanceRequest({organization : {id : aData.id, name: aData.name }});

					pgsi.util.actiondialog.launchActionDialog(
						"deleteDialog",
						 pgsi.pages.business.dialogSettings.deleteDialog(
						 	"api/organization/delete",
						 	 oRequest,
						 	 $.pgsi.locale.get("pages.organization.dialog.title", oRequest.organization.name),
						 	 fnCallBack,
						 	 $.pgsi.locale.get("commons.pages.erroView", $.pgsi.locale.get("commons.pages.organization"))
						 ));

				}

				// Attaches Active Dialog call for activate button
				else if ($(this).hasClass('active')) {

					pgsi.util.page.fnUpdateStatus('api/organization/fetch',parseInt(aData.id,10),'organization',1,fnCallBack,"Activate Organization for "+ aData.name, "<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.activate"),"Organization")+"<br>" +$.pgsi.locale.get("pages.person.dialog.status.information",$.pgsi.locale.get("pages.view.activate"))+"<span>",true);
				}

				// Attaches Deactive Dialog call for deactivate
				else if ($(this).hasClass('deactivate')) {

					pgsi.util.page.fnUpdateStatus('api/organization/fetch',parseInt(aData.id,10),'organization',2,fnCallBack,"Deactivate Organization for "+ aData.name, "<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.deactivate"),"Organization")+"<br>" +$.pgsi.locale.get("pages.person.dialog.status.information",$.pgsi.locale.get("pages.view.deactivate"))+"<span>",true);
				}else if($(this).hasClass('sarDialog')){
					pgsi.util.actiondialog.launchActionDialog(
							"dialogSARDetail",
							 pgsi.pages.sar.dialogSettings.dialogSARDetail(
								 $.pgsi.locale.get("commons.title.table.SAR"),
								 aData.id,
								 "organization",
								 aData.name,
								 aData.key
							 ));
				}

			});

			// Attaches hover effect for displaying edit/delete buttons
			$('td:eq(0)', nRow).hover(function() {
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
				eventName 	: "organizationList",
				arguments 	: ["table"]
			});
		}
	}
	));

	if (!$.pgsi.isNullOrUndefined(oFilterPreLoad)) {
		// Filters
		var aFilters = ['business'];

		var filters = pgsi.util.filter.filterArrayToObject(aFilters);
		pgsi.util.filter.init(oFilterPreLoad, filters, function(oResponse)
		{
			$.pgsi.filter.create(
			{
				element			: ".filter",
				tagsDiv			: ".filter-results-container div.first",
				title			: $.pgsi.locale.get("commons.pages.filterTitle"),
				table 			:  pgsi.pages.organization.organizationTable,
				filters 		: oResponse
			});
		});
	}

	//clear all Filter TODO
	$("#clear-all").on("click", function(e)
	{
		$.address.parameter("organization","");
		$.address.parameter("location","");
		pgsi.util.page.fnReloadTable(pgsi.pages.organization.organizationTable);
	});

});
</script>
</sec:authorize>
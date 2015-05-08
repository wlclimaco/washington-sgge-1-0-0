<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">
<script type="text/javascript">
/**
 * @namespace pgsi.pages.location
 * @description The init namespace for the Location Page.
 */

$(document).ready(function()
{

	$.pgsi.listener.wait({
		eventName 	: "locationByOrganizationList",
		arguments 	: ["table"],
		fnCallback 	: $.pgsi.progressBar.stopGlobal
	});

	/** * jQuery dataTable setup ** */
	pgsi.pages.location.locationTable = $('#data_list').dataTable($.pgsi.table.setTable(
	{
		id 			: "#data_list",
		sAjaxSource : "api/location/fetchOrganizationBylocation",
		bPreLoad	: true,

		ajax :
		{
			sObj		: "locationList",
			oRequest	: PagedInquiryRequest,
			fnRequest 	: function(){

				var iOrgId = parseInt($.address.parameter("organizationId"), 10);

				if (!$.pgsi.isNullOrUndefined(iOrgId)) {
					return  new PagedInquiryRequest({parentId: iOrgId});
				}
			}
		},

		aoColumns :
		[
		{
			headerData 		: $.pgsi.locale.get("commons.pages.name"),
			order			: "name_column",
			mRender         : pgsi.pages.location.fnCreateLocationNameLink,
			sDefaultContent : "",
			bSortable 		: true,
			sClass          : "name-col"
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.city"),
			order			: "city_column",
			mRender 		: pgsi.pages.location.fnLocationCity,
			mData	 		: "null",
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.state"),
			order			: "state_column",
			mRender 		: pgsi.pages.location.fnLocationState,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.country"),
			order			: "country_column",
			mData	 		: 'country.code',
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.sdn.status"),
			order			: "sdn_status_column",
			mRender 		: pgsi.pages.location.fnLocationSDNStatus,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.phone.primary"),
			order			: "phone_column",
			mRender 		: pgsi.pages.location.fnLocationPrimaryPhone,
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
			mRender 		: pgsi.pages.location.fnLocationRiskLevel,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.status"),
			order			: "status_column",
			mRender		    : pgsi.pages.location.fnLocationStatus,
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

			var oActionSummary = "";
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

			oActionSummary = $('<div><div><a href="location/view?tab=info&locationId=' + aData.id + '" title="View '+aData.name+'" id="update" class="icon-nav icon-pencil alist icon-small-button"></a>'
				+ sButtonStatus
				+ sButtonDelete + "</div></div>");

			oActionSummary.find('a.deleteDialog, a.active, a.deactivate').click(function (e) {
				e.preventDefault();

				if (pgsi.util.page.fnIsSDNFlagged(aData.sdnstatus)) {
					return;
				}

				var fnCallBack = function(oResponse) {

					if (oResponse.operationSuccess == true) {

						// Validations for change pagination when delete one or more groups of last page.
						var iStart;
						var oSettings = pgsi.pages.location.locationTable.fnSettings();

							// If exist just one group at last page and this group is deleted, the pagination back to previous page.
							if (((oSettings._iRecordsDisplay - 1) % $('.dataTables_length').find('select').val() === 0)) {
								iStart = (oSettings._iRecordsDisplay - 1) - oSettings._iDisplayLength;
							}

						$.pgsi.table.reloadTable({
							table 		: pgsi.pages.location.locationTable,
							iStart 		: iStart
						});
					}else{
						pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
					}
				}


				if($(this).hasClass('deleteDialog'))
				{
					// Launch Delete Dialog
					var oRequest = new LocationMaintenanceRequest({location : {id : aData.id, name: aData.name }});

					pgsi.util.actiondialog.launchActionDialog(
						"deleteDialog",
						 pgsi.pages.business.dialogSettings.deleteDialog(
						 	"api/location/delete",
						 	 oRequest,
						 	 $.pgsi.locale.get("pages.location.dialog.title", oRequest.location.name),
						 	 fnCallBack,
						 	 $.pgsi.locale.get("commons.pages.erroView", $.pgsi.locale.get("commons.pages.location"))
						 ));

				}else if($(this).hasClass('active')){

					pgsi.util.actiondialog.launchActionDialog("activeDesactiveDialog", pgsi.pages.business.dialogSettings.activeDesactiveDialog(
					 "api/location/applyStatus",
					 new LocationMaintenanceRequest({location : {id : aData.id,statusValue : 1,businessTypeValue : 2,modelAction:"UPDATE"}}),
					 "Activate Location for "+ aData.name,
					 fnCallBack,
					 "<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.activate"),"Location")+ "<span>",
					 "pages.view.activate"));

				}else if($(this).hasClass('deactivate')){
					pgsi.util.actiondialog.launchActionDialog("activeDesactiveDialog", pgsi.pages.business.dialogSettings.activeDesactiveDialog(
					 "api/location/applyStatus",
					 new LocationMaintenanceRequest({location : {id : aData.id,statusValue : 2,businessTypeValue : 2,modelAction:"UPDATE"}}),
					 "Deactivate Location for "+ aData.name,
					 fnCallBack,
					 "<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.deactivate"),"Location")+ "<span>",
					 "pages.view.deactivate"));
				}
			});

			$('td:eq(0)', nRow).hover (
				function ()
				{
					$(this).find('.icon-nav').removeClass('hide');
					$(this).append(oActionSummary);
				},

				function ()
				{
					$(this).find('.icon-nav').addClass('hide');
				}
			);
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
				eventName 	: "locationByOrganizationList",
				arguments 	: ["table"]
			});
		}
	}
	));

});
</script>
</sec:authorize>
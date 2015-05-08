<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
<script type="text/javascript">
/**
 * @namespace pgsi.pages.member
 * @description The init namespace for the Member List Page.
 * @author Washington Costa
 */
$(document).ready(function()
{

	$.pgsi.listener.wait({
		eventName 	: "sdnList",
		arguments 	: ["table"],
		fnCallback 	: $.pgsi.progressBar.stopGlobal
	});

	/** * jQuery dataTable setup ** */
	pgsi.pages.sdn.sdnTable = $('#data_list').dataTable($.pgsi.table.setTable(
	{
		id 			: "#data_list",
		sAjaxSource : 'api/sdn/fetch',
		bPreLoad	: false,
		pagingType  : "full",

		ajax :
		{
			sObj		: 'sdnHistoryList',
			oRequest	: SdnStatusHistoryInquiryRequest,
			fnRequest 	: function(){

				var aSdnStatusHistoryInquiryRequest;
				if (!$.pgsi.isNullOrUndefined($.address.parameter("tab"))){
					aSdnStatusHistoryInquiryRequest = new SdnStatusHistoryInquiryRequest({criteria:{matchType: $.address.parameter("tab")}});
				}else{
					aSdnStatusHistoryInquiryRequest = new SdnStatusHistoryInquiryRequest({criteria:{matchType: "INDIVIDUAL"}});
				}
				return aSdnStatusHistoryInquiryRequest;
			}

		},

		aoColumns :
		[
		{
			headerData 		: $.pgsi.locale.get("commons.pages.name"),
			order			: "name",
			sDefaultContent : "",
			mRender	 		: pgsi.pages.sdn.fnSDNName,
			bSortable 		: true,
			width           : '10px'

		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.type"),
			order			: "lastName",
			mRender	 		: pgsi.pages.sdn.fnBusinessType,
			sDefaultContent : "",
			bSortable 		: false,
			width           : '50px',
			sClass          : "name-col"

		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.sdn.status"),
			mRender	 		: pgsi.pages.sdn.fnSDNType,
			sDefaultContent : "",
			bSortable 		: false,
			width           : '50px',
			sClass          : "location-col"

		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.date"),
			mRender	 		: pgsi.pages.sdn.fnSDNDate,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("pages.sdn.table.status.reason"),
			order			: "state_column",
			mData	 		: "noteText",
			sDefaultContent : "",
			bSortable 		: false
		}
		],

		oSettings :
		{
			sortEnum      	: 'com.prosperitasglobal.sendsolv.model.OrganizationOrderByEnum',
			iDefaultCol   	: 0
		},

		/**
		* Callback function used by the datatables plugin called for every row
		*/
		rowCallback : function(nRow, aData, iDisplayIndex, oColumn) {
			var oActionSummary = "";
			var sButtonStatus = "";
			var sButtonDelete = "";
			if(aData.businessType.toLowerCase() != "liaison"){
				sButtonDelete = '<a href="#/'+aData.businessType.toLowerCase()+'/view?tab=info&'+aData.businessType.toLowerCase()+'Id='+aData.parentKey+'" title="View '+aData.name+'"><span class="icon-nav icon-search-find"></span></a>';
			}
			oActionSummary = $('<div><div><a class="alist" href="sdn/'+aData.businessType.toLowerCase()+'/view?type='+aData.businessType.toLowerCase()+'&id='+aData.parentKey+'" title="'+aData.name+'"><span class="icon-nav icon-security icon-shield82 icon-small-button"></span></a>'
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
				eventName 	: "sdnList",
				arguments 	: ["table"]
			});
		}
	}
	));

});
</script>
</sec:authorize>
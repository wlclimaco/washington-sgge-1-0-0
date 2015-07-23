<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">
<script type="text/javascript">
/**
 * @namespace pgsi.pages.orcamento
 * @description The init namespace for the Location Page.
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
		eventName 	: "orcamentoList",
		arguments 	: ["table"],
		fnCallback 	: $.pgsi.progressBar.stopGlobal
	});

	/** * jQuery dataTable setup ** */
	pgsi.pages.orcamento.orcamentoTable = $('#data_list').dataTable($.pgsi.table.setTable(
	{
		id 			: "#data_list",
		sAjaxSource : "api/notaFiscal/fetchall/entrada",
		bPreLoad	: true,

		ajax :
		{
			sObj		: "notaFiscalList",
			oRequest	: PagedInquiryRequest,
			fnRequest 	: function(){}
		},

		aoColumns :
		[
		{
			headerData 		: "Numero",
			order			: "name",
			mRender         : pgsi.pages.notaFiscal.fnCreateNumeroLink,
			sDefaultContent : "",
			bSortable 		: false,
			sClass          : "name-col"
		},
		{
			headerData 		: "Serie",
			order			: "organization_column",
			mRender 		: pgsi.pages.notaFiscal.fnSerie,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Ordem",
			order			: "city_column",
			mRender 		: pgsi.pages.notaFiscal.fnOrdem,
			mData	 		: "null",
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Tipo",
			order			: "state_column",
			mRender 		: pgsi.pages.notaFiscal.fnTipo,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Fornecedor",
			order			: "country_column",
			mRender 		: pgsi.pages.notaFiscal.fnFornecedor,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Data Emissão",
			order			: "sdn_status_column",
			mRender 		: pgsi.pages.notaFiscal.fnDataEmissao,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Data Entrada",
			order			: "phone_column",
			mRender 		: pgsi.pages.notaFiscal.fnDataEntrada,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Valor",
			order			: "phone_column",
			mRender 		: pgsi.pages.notaFiscal.fnNFValor,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Status",
			order			: "phone_column",
			mRender 		: pgsi.pages.notaFiscal.fnNFStatus,
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

			oActionSummary = $('<div><div><a href="orcamento/view?tab=info&orcamentoId=' + aData.id + '" title="View '+aData.name+'" id="update" class="icon-nav icon-pencil alist icon-small-button"></a>'
				+ sButtonStatus
				+ sButtonDelete
				+pgsi.util.page.fnInsertButtonSDNSAR(aData,"orcamento")+"</div></div>");

			oActionSummary.find('a.deleteDialog, a.active, a.deactivate ,a.sarDialog').click(function (e) {
				e.preventDefault();

				if (pgsi.util.page.fnIsSDNFlagged(aData.sdnstatus)) {
					return;
				}

				var fnCallBack = function(oResponse) {

					if (oResponse.operationSuccess == true) {

						// Validations for change pagination when delete one or more groups of last page.
						var iStart;
						var oSettings = pgsi.pages.orcamento.orcamentoTable.fnSettings();

							// If exist just one group at last page and this group is deleted, the pagination back to previous page.
							if (((oSettings._iRecordsDisplay - 1) % $('.dataTables_length').find('select').val() === 0)) {
								iStart = (oSettings._iRecordsDisplay - 1) - oSettings._iDisplayLength;
							}

						$.pgsi.table.reloadTable({
							table 		: pgsi.pages.orcamento.orcamentoTable,
							iStart 		: iStart
						});
					}else{
						pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
					}
				}


				if($(this).hasClass('deleteDialog'))
				{
					// Launch Delete Dialog
					var oRequest = new LocationMaintenanceRequest({orcamento : {id : aData.id, name: aData.name }});

					pgsi.util.actiondialog.launchActionDialog(
						"deleteDialog",
						 pgsi.pages.business.dialogSettings.deleteDialog(
						 	"api/orcamento/delete",
						 	 oRequest,
						 	 $.pgsi.locale.get("pages.orcamento.dialog.title", oRequest.orcamento.name),
						 	 fnCallBack,
						 	 $.pgsi.locale.get("commons.pages.erroView", $.pgsi.locale.get("commons.pages.orcamento"))
						 ));

				}else if($(this).hasClass('active')){

					pgsi.util.page.fnUpdateStatus('api/orcamento/fetch',parseInt(aData.id,10),'orcamento',1,fnCallBack,"Activate Location for "+ aData.name, "<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.activate"),"Location")+"<br>" +$.pgsi.locale.get("pages.person.dialog.status.information",$.pgsi.locale.get("pages.view.activate"))+"<span>",true);
				}else if($(this).hasClass('deactivate')){
					pgsi.util.page.fnUpdateStatus('api/orcamento/fetch',parseInt(aData.id,10),'orcamento',2,fnCallBack,"Deactivate Location for "+ aData.name, "<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.deactivate"),"Location")+"<br>" +$.pgsi.locale.get("pages.person.dialog.status.information",$.pgsi.locale.get("pages.view.deactivate"))+"<span>",true);
				}else if($(this).hasClass('sarDialog')){
					pgsi.util.actiondialog.launchActionDialog(
							"dialogSARDetail",
							 pgsi.pages.sar.dialogSettings.dialogSARDetail(
								 $.pgsi.locale.get("commons.title.table.SAR"),
								 aData.id,
								 "orcamento",
								 aData.name,
								 aData.key
							 ));
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
				eventName 	: "orcamentoList",
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
				table 			:  pgsi.pages.orcamento.orcamentoTable,
				filters 		: oResponse
			});
		});
	}

	//clear all Filter TODO
	$("#clear-all").on("click", function(e)
	{
		$.address.parameter("organization","");
		$.address.parameter("orcamento","");
		pgsi.util.page.fnReloadTable(pgsi.pages.orcamento.orcamentoTable);
	});

});
</script>
</sec:authorize>
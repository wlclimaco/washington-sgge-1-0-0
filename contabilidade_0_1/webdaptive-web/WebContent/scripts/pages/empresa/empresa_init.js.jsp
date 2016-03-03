<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<script type="text/javascript">
/**
 * @namespace qat.pages.location
 * @description The init namespace for the Location Page.
 */

$(document).ready(function()
{

	$.qat.listener.wait({
		eventName 	: "locationList",
		arguments 	: ["table"],
		fnCallback 	: $.qat.progressBar.stopGlobal
	});

	<c:choose>
	<c:when test="${empty empresaList}">
    	var oPreLoadResponse = null;
    </c:when>
    <c:otherwise>
    	var oPreLoadResponse = ${empresaList};
    </c:otherwise>
</c:choose>
console.log(oPreLoadResponse)
	/** * jQuery dataTable setup ** */
	qat.pages.empresa.empresaTable = $('#data_list').dataTable($.qat.table.setTable(
	{
		id 			: "#data_list",
		sAjaxSource : "api/empresa/fetchall",
		bPreLoad	: true,
		sCheckbox : "id",

		ajax :
		{
			sObj		: "empresaList",
			oRequest	: qat.model.EmpresaInquiryRequest,
			fnRequest 	: qat.pages.entidade.fnRequestFilter
		},

		aoColumns :
		[
		{
			headerData 		: "CNPJ",
			order			: "name",
			mRender         : qat.pages.entidade.fnCreateEmpresaNameLink,
			sDefaultContent : "",
			bSortable 		: false,
			sClass          : "name-col"
		},
		{
			headerData 		: "Nome Empresa",
			order			: "organization_column",
			mRender 		: qat.pages.entidade.fnCreateNomeLink,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Cnae",
			order			: "city_column",
			mRender 		: qat.pages.entidade.fnCnae,
			mData	 		: "null",
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Email",
			order			: "state_column",
			mRender 		: qat.pages.entidade.fnEmail,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Telefone",
			order			: "country_column",
			mRender 		: qat.pages.entidade.fnTelefone,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Regime",
			order			: "sdn_status_column",
			mRender 		: qat.pages.entidade.fnRegime,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Endereco",
			order			: "phone_column",
			mRender 		: qat.pages.entidade.fnEndereco,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Documentos",
			order			: "phone_column",
			mRender 		: qat.pages.entidade.fnDocumento,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Status",
			order			: "phone_column",
			mRender 		: qat.pages.entidade.fnStatus,
			sDefaultContent : "",
			bSortable 		: false
		},
		],

		<c:choose>
			<c:when test="${not empty refresh}">
				aaData : "refresh",
			</c:when>
			<c:when test="${empty empresaList}">
				aaData : null,
		    </c:when>
		    <c:otherwise>
		    	aaData : ${empresaList},
		    </c:otherwise>
		</c:choose>

		oSettings :
		{
			sortEnum      	: "",
			iDefaultCol   	: 0
		},

		rowCallback : function(nRow, aData, iDisplayIndex, oColumn) {

		},

		fnInitComplete: function (oSettings, json)
		{
			$('.dataTables_paginate:eq(0)').addClass('hide')
			$('.dataTables_info:eq(0)').addClass('hide')
			$('.dataTables_length:eq(0)').addClass('hide')

			$(".dataTables_length select").outerWidth(62).selectmenu({
				appendTo: ".content.list",
  				change: function( event, ui ) {
  					$('#data_list_length').find("select").val(ui.item.value);
  					$("#data_list_length").find("select").trigger("change");
  					$("#load").find(".dataTables_length").find("select").selectmenu("refresh" );
  				}
			});

			$.qat.listener.notify({
				eventName 	: "locationList",
				arguments 	: ["table"]
			});
		}
	}
	));

	//clear all Filter TODO
	$("#resetFilter .reset").on("click", function(e)
	{
		$.address.parameter("id","");
		$.address.parameter("status","");
		qat.util.page.fnReloadTable(qat.pages.empresa.empresaTable);
	});
	$('#resetFilter  .buscar').on("click", function(e)
	{
		e.preventDefault();
		//qat.pages.entidade.fnRequestFilter();
		qat.util.page.fnReloadTable(qat.pages.empresa.empresaTable);
	});
	$("#atualizar").on("click", function(e)
	{
		e.preventDefault();
		qat.util.page.fnReloadTable(qat.pages.empresa.empresaTable);
	});



	var dialog, form,

    dialog = $( "#dialog-form" ).dialog({
      autoOpen: false,
      height: 900,
      width: 500,
      modal: true,
      buttons: {
        "Create an account": "",
        Cancel: function() {
          dialog.dialog( "close" );
        }
      },
      close: function() {
        form[ 0 ].reset();
        allFields.removeClass( "ui-state-error" );
      }
    });

    form = dialog.find( "form" ).on( "submit", function( event ) {
      event.preventDefault();
    });

	$("#add").on("click", function(e)
	{
		e.preventDefault();
		dialog.dialog( "open" );
	});



});
</script>
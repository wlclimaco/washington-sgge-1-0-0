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
		eventName 	: "locationList",
		arguments 	: ["table"],
		fnCallback 	: $.pgsi.progressBar.stopGlobal
	});

	/** * jQuery dataTable setup ** */
	pgsi.pages.empresa.empresaTable = $('#data_list').dataTable($.pgsi.table.setTable(
	{
		id 			: "#data_list",
		sAjaxSource : "api/empresa/fetchall",
		bPreLoad	: true,
		sCheckbox : "id",

		ajax :
		{
			sObj		: "empresaList",
			oRequest	: EmpresaInquiryRequest,
			fnRequest 	: pgsi.pages.entidade.fnRequestFilter
		},

		aoColumns :
		[
		{
			headerData 		: "CNPJ",
			order			: "name",
			mRender         : pgsi.pages.entidade.fnCreateEmpresaNameLink,
			sDefaultContent : "",
			bSortable 		: false,
			sClass          : "name-col"
		},
		{
			headerData 		: "Nome Empresa",
			order			: "organization_column",
			mRender 		: pgsi.pages.entidade.fnCreateNomeLink,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Cnae",
			order			: "city_column",
			mRender 		: pgsi.pages.entidade.fnCnae,
			mData	 		: "null",
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Email",
			order			: "state_column",
			mRender 		: pgsi.pages.entidade.fnEmail,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Telefone",
			order			: "country_column",
			mRender 		: pgsi.pages.entidade.fnTelefone,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Regime",
			order			: "sdn_status_column",
			mRender 		: pgsi.pages.entidade.fnRegime,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Endereco",
			order			: "phone_column",
			mRender 		: pgsi.pages.entidade.fnEndereco,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Documentos",
			order			: "phone_column",
			mRender 		: pgsi.pages.entidade.fnDocumento,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Status",
			order			: "phone_column",
			mRender 		: pgsi.pages.entidade.fnStatus,
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
		pgsi.util.page.fnReloadTable(pgsi.pages.empresa.empresaTable);
	});
	$('#resetFilter  .buscar').on("click", function(e)
	{
		e.preventDefault();
		//pgsi.pages.entidade.fnRequestFilter();
		pgsi.util.page.fnReloadTable(pgsi.pages.empresa.empresaTable);
	});

});
</script>
</sec:authorize>
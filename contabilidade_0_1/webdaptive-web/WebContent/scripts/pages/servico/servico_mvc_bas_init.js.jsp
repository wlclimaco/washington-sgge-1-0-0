<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">
<script type="text/javascript">
/**
 * @namespace qat.pages.location
 * @description The init namespace for the Location Page.
 */

$(document).ready(function()
{

	/** * jQuery dataTable setup ** */
	qat.pages.servico.servicoTable = $('#data_list').dataTable($.qat.table.setTable(
	{
		id 			: "#data_list",
		sAjaxSource : "api/produto/fetchall",
		bPreLoad	: true,
		sCheckbox : "id",

		ajax :
		{
			sObj		: "servicoList",
			oRequest	: ServicoInquiryRequest,
			fnRequest 	: qat.pages.entidade.fnRequestFilter
		},

		aoColumns :
		[
		{
			headerData 		: "CNPJ",
			order			: "name",
			//mRender         : qat.pages.entidade.fnCreateEmpresaNameLink,
			mData           : nome
			sDefaultContent : "",
			bSortable 		: false,
			sClass          : "name-col"
		},
		{
			headerData 		: "Nome Empresa",
			order			: "organization_column",
			//mRender 		: qat.pages.entidade.fnCreateNomeLink,
			mData           : descricao
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

		}
	}
	));
});
</script>
</sec:authorize>
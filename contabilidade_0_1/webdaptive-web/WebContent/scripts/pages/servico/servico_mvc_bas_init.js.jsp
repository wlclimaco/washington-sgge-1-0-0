<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>


<script type="text/javascript">
/**
 * @namespace qat.pages.location
 * @description The init namespace for the Location Page.
 */

$(document).ready(function()
{


		    	var aaData = ${servicoList}


		console.log(aaData)
	/** * jQuery dataTable setup ** */
	//debugger
	qat.pages.servico.servicoTable = $('#data_list').dataTable($.qat.table.setTable(
	{
		id 			: "#data_list",
		sAjaxSource : "api/produto/fetchall",
		bPreLoad	: true,
		sCheckbox : "id",

		ajax :
		{
			sObj		: "servicoList",
			oRequest	: null,
			fnRequest 	: null
		},

		aoColumns :
		[
		{
			headerData 		: "Id",
			order			: "id",
			mData           : "id",
			sDefaultContent : "",
			bSortable 		: false,
			sClass          : "name-col"
		},
		{
			headerData 		: "Nome",
			order			: "nome",
			mData           : "nome",
			sDefaultContent : "",
			bSortable 		: false,
			sClass          : "name-col"
		},
		{
			headerData 		: "Descrição",
			order			: "organization_column",
			mData           : "descricao",
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Valor",
			order			: "organization_column",
			mRender 		: qat.pages.servico.fnValorLink,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Status",
			order			: "organization_column",
			mRender 		: qat.pages.servico.fnStatus,
			//mData           : "descricao",
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Usuario",
			order			: "organization_column",
			mRender 		: qat.pages.servico.fnUsuario,
			//mData           : "descricao",
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Data",
			order			: "organization_column",
			mRender 		: qat.pages.servico.fnDataUltAlt,
			//mData           : "descricao",
			sDefaultContent : "",
			bSortable 		: false
		},
		],

		<c:choose>
			<c:when test="${not empty refresh}">
				aaData : "refresh",
			</c:when>
			<c:when test="${empty servicoList}">
				aaData : null,
		    </c:when>
		    <c:otherwise>
		    	aaData : ${servicoList},
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

	$("#buttonInsert").on("click", function(e)
	{
		e.preventDefault();
		a = new Date();
		oServico = new qat.model.Servico({
			id				: 1,
			nome			:'Washington',
			descricao		:'descricao 123',
			modelAction 	: 'INSERT',
			preco 			: new qat.model.TabPreco({
				id				: 1 ,
				entidadeId 		: 1 ,
				dataMarcacao  	:	a.getTime(),
				precoTypeEnum 	:'PLANO',
				valor 			:1.99,
				dataProInicial 	:	a.getTime(),
				dataProFinal 	:	a.getTime(),
				maxVendProd 	:10,
				modelAction 	: 'INSERT'

			})
		})

		oRequest = new qat.model.reqServico(null,oServico,null,null)
		$.qat.ajax.post({
			sUrl : "qat-webdaptive/site/api/insertServico",
			oRequest : oRequest,
			fnCallback : function(oResponse){
				console.log(oResponse)
			}
		});
	});
});
</script>

<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>


<script type="text/javascript">
/**
 * @namespace qat.pages.location
 * @description The init namespace for the Location Page.
 */

$(document).ready(function()
{


		    	var aaData = ${planoList}


		console.log(aaData)
	/** * jQuery dataTable setup ** */
	//debugger
	qat.pages.plano.planoTable = $('#data_list').dataTable($.qat.table.setTable(
	{
		id 			: "#data_list",
		sAjaxSource : "api/produto/fetchall",
		bPreLoad	: true,
		sCheckbox : "id",

		ajax :
		{
			sObj		: "produtoList",
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
			mRender 		: qat.pages.plano.fnValorLink,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Status",
			order			: "organization_column",
			mRender 		: qat.pages.plano.fnStatus,
			//mData           : "descricao",
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Usuario",
			order			: "organization_column",
			mRender 		: qat.pages.plano.fnUsuario,
			//mData           : "descricao",
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Data",
			order			: "organization_column",
			mRender 		: qat.pages.plano.fnDataUltAlt,
			//mData           : "descricao",
			sDefaultContent : "",
			bSortable 		: false
		},
		],

		<c:choose>
			<c:when test="${not empty refresh}">
				aaData : "refresh",
			</c:when>
			<c:when test="${empty planoList}">
				aaData : null,
		    </c:when>
		    <c:otherwise>
		    	aaData : ${planoList},
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

		oServico = new qat.model.PlanoByServico({
			id					:1,
			planoId			:1,
			servicoId		:1,
			processId		:1,
			modelAction 	:"INSERT"
		});


		oImagem = new qat.model.Imagem({
			local				:"c:/imagems/principal",
			tabelaEnum			: "PLANO",
			nome				: "IMAGEM0001",
			fotoId				: 1,
			principal			: 1,
			emprId				: 1,
			modelAction 	:"INSERT"
		});
		a = new Date();

		oPlano = new qat.model.Plano({

			dataInicio		: a.getTime(),
			dataFinal		: a.getTime(),
			preco			: new qat.model.TabPreco({
				id				: 1 ,
				entidadeId 		: 1 ,
				dataMarcacao  	:	a.getTime(),
				precoTypeEnum 	:'PLANO',
				valor 			:1.99,
				dataProInicial 	:	a.getTime(),
				dataProFinal 	:	a.getTime(),
				maxVendProd 	:10,
				modelAction 	: 'INSERT'

			}),
			numeroContrato	: 201542,
			servicos		: oServico,
			imagens			: oImagem,
			descricao		: "Descricao 000225415454444444444444444",
			titulo			: "Titulo Titulo",
			modelAction 	:"INSERT"
		})

		oRequest = new qat.model.reqPlano(null,oPlano,null,null)
		$.qat.ajax.post({
			sUrl : "qat-webdaptive/site/api/insertPlano",
			oRequest : oRequest,
			fnCallback : function(oResponse){
				console.log(oResponse)
			}
		});
	});
});
</script>

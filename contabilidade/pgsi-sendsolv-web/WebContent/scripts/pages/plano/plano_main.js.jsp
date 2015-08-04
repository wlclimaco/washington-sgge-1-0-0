<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.location
 * @fileoverview The main namespace for the Location List Page.
 */
pgsi.pages.plano = {

	fnTable : function(oResponse) {

		tbory = "";
		if (!$.pgsi.isNullOrUndefined(oResponse.produtoList)) {
			for(var i = 0;i < oResponse.produtoList.length;i++ ){
				var oCnae = oResponse.produtoList[i];
				tbory = tbory + '<tr><td><input type="checkbox" class="checkthis" id="'+oCnae.id+'" /></td><td>'+oCnae.id+'</td><td>'+oCnae.codigo+'</td><td>'+oCnae.produto+'</td><td>'+pgsi.pages.plano.fnPreco(oCnae.precoList)+'</td><tr>';
			}
		}
		return tbory;
	},

	fnPreco : function(oPrecoList) {
		var iPreco = 0;
		if (!$.pgsi.isNullOrUndefined(oPrecoList)) {
			for(var y = 0;y < oPrecoList.length;y++ ){
				if(oPrecoList[y].precoTypeEnum === "PLANO"){
					iPreco = oPrecoList[y].valor ;
				}
			}
		}
		return iPreco;
	},

	fnRequest : function() {
		var oProduto = [];

		$.each( $('#mytable tbody').find('input'), function( i, val ) {

			  if ($(this).is(":checked")) {
				aCnae = new Produto();
				aCnae.id = $(this).attr('id');
				aCnae.emprId = parseInt($.address.parameter("locationId"),10)
				aCnae.parentId = parseInt($.address.parameter("locationId"),10)
				aCnae.modelAction = 'INSERT'
				oProduto.push(aCnae);
			  }

		});
		aPlano = [];
		activityStartDateTimeUTC	= new Date($('#dataInicial').val()).getTime();
		activityStartDateTimeUTA	=  new Date($('#dataFinal').val()).getTime();
		oPlano = new Plano();
		oPlano.produto = aCnae;//oProduto;
		oPlano.dataInicio = activityStartDateTimeUTC
		oPlano.dataFinal  = activityStartDateTimeUTA
		oPlano.numeroContrato = parseInt($('#num-contrato').val(),10);
		oPlano.modelAction = 'INSERT'
		oPlano.processId = 1;
		aPlano.push(oPlano)
		$.pgsi.ajax.post({
			sUrl 		: "api/empresa/add",
			oRequest 	: {empresa:{id: parseInt($.address.parameter("locationId"),10),processId : 1 ,modelAction :"NONE", planoList:aPlano}},
			fnCallback  : pgsi.pages.empresa.view.fnCallbackRefleshPageEmpresa
	   });
	},

	fnCreateRequest : function(){


	}
}
</script>

</sec:authorize>
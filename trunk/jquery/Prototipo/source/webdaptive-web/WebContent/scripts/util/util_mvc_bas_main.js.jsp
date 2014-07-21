<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

qat.model.supermercado.pages  = {

	openPagedFetchWS : function (iId)
	{
	    //if viewLOaddedObject filled by controller don't make a ajax call
		    var oData = new qat.model.fetchByIdRequest(null,iId);
			//rest_post_call('qat-webdaptive/procedure/api/fetchByRequestBAS', oData, fill_data, process_error);
			rest_post_call('qat-sysmgmt-sample/services/rest/SupermercadoService/fetchSupermercadoById', oData, qat.model.supermercado.pages.fill_data, qat.model.supermercado.pages.process_error);
	},
	openPagedFetchWSSuper : function (viewLoadedObject)
	{
		if(viewLoadedObject == null){
			rest_post_call('qat-sysmgmt-sample/services/rest/SupermercadoService/fetchAllSupermercados', {}, qat.model.supermercado.pages.fill_data, qat.model.supermercado.pages.process_error);
		}else{
			qat.model.supermercado.pages.fill_data(viewLoadedObject);
		}
	},

    fill_data :function (oResponse)
	{
		 var marca = '';
		for (var i = 0; i < oResponse.supermercados.length; i++) {
			marca  +=   '<option value="' + oResponse.supermercados[i].superId + '">' + oResponse.supermercados[i].nome + " " +oResponse.supermercados[i].enderecos[0].logradouro+'</option>';
		 }
      $("select#estado").html(marca);
	},
	fill_datas :function (oResponse)
	{
	   console.log('dddd');
	},

	process_error : function (jqXHR, textStatus, errorThrown)
	{
		wd.core.process_xhr_error(jqXHR, textStatus, errorThrown);
	},
	gravar : function (oLista){
		if(oLista.length > 0){
			var oData=[];
			var id = parseInt($('#estado').val());
			for(var i = 0; i< oLista.length;i++ ){
				preco = new qat.model.preco(0, oLista[i].id, id, null,0.0, 1,0.0, null, null);
				oData.push({id:oLista[i].id,precos:[{precoid:0,idProduto:oLista[i].id,supermercadoid:{superId:id}}],tabela:1});
			}
			 var oDatas = new qat.model.reqProdutos(null, oData,true,true);
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/updateProduto', {produtos:oData}, qat.model.supermercado.pages.fill_data2, qat.model.supermercado.pages.process_error);
		}else{
			$('.yui-ge .message').addClass("ui-state-error");
		}
	}
}

</script>

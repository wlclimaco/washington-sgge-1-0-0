<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

qat.model.supermercado.page = {

	openPagedFetchWS : function (iId)
	{
	    //if viewLOaddedObject filled by controller don't make a ajax call
		    var oData = new qat.model.fetchByIdRequest(null,iId);
			//rest_post_call('qat-webdaptive/procedure/api/fetchByRequestBAS', oData, fill_data, process_error);
			rest_post_call('qat-sysmgmt-sample/services/rest/SupermercadoService/fetchSupermercadoById', oData, qat.model.supermercado.page.fill_data, qat.model.supermercado.page.process_error);
	},

    fill_data :function (procResponse)
	{
		qat.model.supermercado.page.openCreateObject(procResponse);
	},

	process_error : function (jqXHR, textStatus, errorThrown)
	{
		wd.core.process_xhr_error(jqXHR, textStatus, errorThrown);
	},
	callCreateObject : function (_iId){
		var	_id   = $('#codId').val();
			_enderecoid  = $('#codEnd').val(),
			_eid  = $('#endId').val(),
			_documenroid  = $('#codDoc').val(),
			_did  = $('#idDoc').val(),
			_nome = $('#nome').val(),
			_site = $('#site').val(),
			_email = $('#email').val(),
			_usuario = $('#usuario').val(),
			_password = $('#password').val(),
			_rg = $('#rg').val(),
			_cpf = $('#cpf').val(),
			_dtnasci = $('#dtnasci').val(),
			_cep = $('#cep').val(),
			_logradouro = $('#logradouro').val(),
			_bairro = $('#bairro').val(),
			_cidade = $('#cidade').val(),
			_estado = $('#estado').val(),
			_numero = $('#numero').val(),
			_complemento = $('#complemento').val();
		return	new qat.model.supermercado(_id,_usuario, _email,_site,_usuario,_password,_enderecoid,_eid,_logradouro,_bairro,_estado,_cidade,_numero,_cep,_nome,_complemento,_documenroid,_did,_rg,_cpf,_nome,_dtnasci);
	},
	openCreateObject : function (oRequest){
		if(oRequest.supermercados != null){
			var produto   = oRequest.supermercados[0];
			var endereco  = oRequest.supermercados[0].enderecos[0];
			var documento = oRequest.supermercados[0].documentos[0];
				 $('#codId').val(produto.superId),
				 $('#codEnd').val(endereco.enderecoid),
				 $('#endId').val(endereco.id),
				 $('#codDoc').val(documento.documentoid),
				 $('#idDoc').val(documento.id),
				 $('#nome').val(documento.razao),
				 $('#site').val(produto.site),
				 $('#email').val(produto.email),
				 $('#usuario').val(produto.usuario),
				 $('#password').val(produto.senha),
				 $('#rg').val(documento.rgInc),
				 $('#cpf').val(documento.cpfCnpj),
				 $('#dtnasci').val(documento.dateNascimento),
				 $('#cep').val(endereco.cep),
				 $('#logradouro').val(endereco.logradouro),
				 $('#bairro').val(endereco.bairro),
				 $('#cidade').val(endereco.cidade),
				 $('#estado').val(endereco.estado),
				 $('#numero').val(endereco.numero),
				 $('#complemento').val(endereco.complemento);
		}
	},
	gravar : function (_iId){
		if(_iId == 0){
			var oData = new qat.model.reqSupermercado(null,qat.model.supermercado.page.callCreateObject(_iId) ,true,true);
			rest_post_call('qat-sysmgmt-sample/services/rest/SupermercadoService/insertSupermercado', oData, qat.model.supermercado.page.fill_data, qat.model.supermercado.page.process_error);
		}else{
			var oData = new qat.model.reqSupermercado(null,qat.model.supermercado.page.callCreateObject(_iId) ,true,true);
			rest_post_call('qat-sysmgmt-sample/services/rest/SupermercadoService/updateSupermercado', oData, qat.model.supermercado.page.fill_data, qat.model.supermercado.page.process_error);
		}
	}
}

</script>

<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.pessoa
 * @fileoverview The main namespace for the Location List Page.
 */
pgsi.pages.pessoa = {

	/**
	* Set of functions used by the datatables plugin to customize field values
	*/

	// Returns link for edit view
	fnCreateClienteNameLink : function (val, type, full)
	{
		var sCnpj="" , returno="";
		if (type !== "display")
		{
			return val;
		}

		if (!$.pgsi.isNullOrUndefined(full.documentos)) {
			for(var i=0;i<full.documentos.length;i++){
				if((full.documentos[i].description == "CNPJ")||(full.documentos[i].description == "CPF")){
					sCnpj = full.documentos[i].numero;
				}
			}
			returno = '<a title="View/Edit ' + sCnpj + '" href="#/pessoa/view?tab=info&pessoaId=' + full.id + '" class="edit_link">' + sCnpj + '</a>';
		}
		return returno;
	},

	fnCreateNomeLink : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		return '<a title="View/Edit ' + full.nome + '" href="#/pessoa/view?tab=info&pessoaId=' + full.id + '" class="edit_link">' + full.nome + '</a>';

	},
	fnProfissao: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		var sProfissao ="";
		if (!$.pgsi.isNullOrUndefined(full.profissao)) {
			for(var i=0;i<full.profissao.length;i++){
				sProfissao = sProfissao + full.profissao[i].profissao  +" - <sup>"+full.profissao[i].renda+"</sup><br>" ;
				if(i === (full.profissao.length - 1)){
					sProfissao = sProfissao + full.profissao[i].profissao  +" - <sup>"+full.profissao[i].renda+" - Atual</sup><br>" ;
				}
			}
		}

		return sProfissao;

	},

	fnEmail: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		var sCnae ="";
		if (!$.pgsi.isNullOrUndefined(full.emails)) {

			for(var i=0;i<full.emails.length;i++){
				sCnae = sCnae + "<sup>"+full.emails[i].email+"</sup><br>" ;
			}
		}

		return sCnae;
	},

	fnTelefone: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		var sCnae ="";
		if (!$.pgsi.isNullOrUndefined(full.telefones)) {
			for(var i=0;i<full.telefones.length;i++){
				sCnae = sCnae + "("+full.telefones[i].ddd+ ") "+full.telefones[i].numero+"<br>";
			}
		}
		return sCnae;
	},

	fnConvenio: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		var sCnae ="";
		if (!$.pgsi.isNullOrUndefined(full.convenioList)) {
			for(var i=0;i<full.convenioList.length;i++){
				sCnae = sCnae +'<a title="View/Edit ' + full.convenioList[i].nome + '" href="#/convenio/view?tab=info&convenioId=' + full.convenioList[i].id + '" class="edit_link">' + full.convenioList[i].nome + '</a><br>';
			}
		}
		return sCnae;

	},

	fnEndereco: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		var sCnae ="";
		if (!$.pgsi.isNullOrUndefined(full.enderecos)) {
			for(var i=0;i<full.enderecos.length;i++){
				sCnae = sCnae + ' '+full.enderecos[i].logradouro + " "+full.enderecos[i].numero + " "+full.enderecos[i].bairro + " "+full.enderecos[i].cidade+'<br>';
			}
		}
		return sCnae;

	},
	fnDocumento: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		var sDocumentos ="";
		if (!$.pgsi.isNullOrUndefined(full.documentos)) {
			for(var i=0;i<full.documentos.length;i++){
				sDocumentos = sDocumentos +  full.documentos[0].description+ " - "+full.documentos[0].numero + "<br>";
			}
		}
		return sDocumentos;
	},

	produtoTable: {

	}
}
</script>


</sec:authorize>
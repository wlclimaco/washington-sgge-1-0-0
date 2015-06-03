<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.cliente
 * @fileoverview The main namespace for the Location List Page.
 */
pgsi.pages.cliente = {

	/**
	* Set of functions used by the datatables plugin to customize field values
	*/

	// Returns link for edit view
	fnCreateClienteNameLink : function (val, type, full)
	{console.log(full)
	var sCnpj="";
		if (type !== "display")
		{
			return val;
		}
		for(var i=0;i<full.documentos.length;i++){
			if(full.documentos[i].type == "CNPJ"){
				sCnpj = full.documentos[i].numero;
			}
		}
			return '<a title="View/Edit ' + sCnpj + '" href="#/cliente/view?tab=info&clienteId=' + full.id + '" class="edit_link">' + sCnpj + '</a>';
	},

	fnCreateNomeLink : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		return '<a title="View/Edit ' + full.nome + '" href="#/cliente/view?tab=info&clienteId=' + full.id + '" class="edit_link">' + full.nome + '</a>';

	},
	fnCnae: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		var sCnae ="";
		for(var i=0;i<full.cnaes.length;i++){
			sCnae = sCnae + full.cnaes[i].cnae  +" - <sup>"+full.cnaes[i].descricao+"</sup><br>" ;
		}
		return sCnae;

	},

	fnEmail: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		return full.emails[0].email;

	},

	fnTelefone: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		return "("+full.telefones[0].ddd+ ") "+full.telefones[0].numero;

	},

	fnRegime: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		return full.regime.nome;

	},

	fnEndereco: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		return full.enderecos[0].logradouro + " "+full.enderecos[0].numero + " "+full.enderecos[0].bairro + " "+full.enderecos[0].cidade;

	},
	fnDocumento: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		var sDocumentos ="";
		for(var i=0;i<full.documentos.length;i++){
			sDocumentos = sDocumentos +  full.documentos[0].description+ " - "+full.documentos[0].numero + "<br>";
		}

		return sDocumentos;
	},

	clienteTable: {

	}
}
</script>


</sec:authorize>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.funcionario
 * @fileoverview The main namespace for the Location List Page.
 */
pgsi.pages.funcionario = {

	/**
	* Set of functions used by the datatables plugin to customize field values
	*/

	// Returns link for edit view
	fnCreateFuncionarioNameLink : function (val, type, full)
	{console.log(full)

		if (type !== "display")
		{
			return val;
		}
			return '<a title="View/Edit ' + full.nome + '" href="#/funcionario/view?tab=info&funcionarioId=' + full.id + '" class="edit_link">' + full.nome + '</a>';
	},

	fnCreateCPFLink : function (val, type, full)
	{
		var sCnpj="";
		if (type !== "display")
		{
			return val;
		}

		for(var i=0;i<full.documentos.length;i++){
			if(full.documentos[i].descricao == "CPF"){
				sCnpj = full.documentos[i].numero;
			}
		}

		return '<a title="View/Edit ' + sCnpj + '" href="#/funcionario/view?tab=info&funcionarioId=' + full.id + '" class="edit_link">' + sCnpj + '</a>';

	},
	fnBeneficios: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		var sCnae ="";
		for(var i=0;i<full.beneficios.length;i++){
			sCnae = sCnae + full.beneficios[i].codigo  +" - <sup>"+full.beneficios[i].descricao+"</sup><br>" ;
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

	funcionarioTable: {

	}
}
</script>


</sec:authorize>
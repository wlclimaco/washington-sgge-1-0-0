<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.location
 * @fileoverview The main namespace for the Location List Page.
 */
pgsi.pages.empresa = {

	/**
	* Set of functions used by the datatables plugin to customize field values
	*/

	// Returns link for edit view
	fnCreateEmpresaNameLink : function (val, type, full)
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
			return '<a title="View/Edit ' + sCnpj + '" href="#/empresa/view?tab=info&locationId=' + full.id + '" class="edit_link">' + sCnpj + '</a>';
	},

	fnCreateNomeLink : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		return '<a title="View"' + full.nome + ' href="#/organization/view?tab=info&organizationId=' + full.id + '">' + full.nome + '</a>';

	},
	fnCnae: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		var sCnae ="";
		for(var i=0;i<full.cnaes.length;i++){
			sCnae = sCnae + full.cnaes[i].number +"<br>" ;
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

		return full.regime;

	},

	fnEndereco: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		return full.enderecos[0].logradouro + " "+full.enderecos[0].numero + " "+full.enderecos[0].bairro + " "+full.enderecos[0].cidade;

	},

	locationTable: {

	}
}
</script>

</sec:authorize>
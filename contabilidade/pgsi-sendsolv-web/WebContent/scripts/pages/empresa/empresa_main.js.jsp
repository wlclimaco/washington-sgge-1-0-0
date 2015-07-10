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
		if (!$.pgsi.isNullOrUndefined(full.documentos)) {
			for(var i=0;i<full.documentos.length;i++){
				if((full.documentos[i].description == "CNPJ")||(full.documentos[i].description == "CPF")){
					sCnpj = full.documentos[i].numero;
				}
			}
			return '<a title="View/Edit ' + sCnpj + '" href="#/empresa/view?tab=info&locationId=' + full.id + '" class="edit_link">' + sCnpj + '</a>';
		}else{
			return '';
		}
	},

	fnCreateNomeLink : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		return '<a title="View/Edit ' + full.nome + '" href="#/empresa/view?tab=info&locationId=' + full.id + '" class="edit_link">' + full.nome + '</a>';

	},
	fnCnae: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		if (!$.pgsi.isNullOrUndefined(full.cnaes)) {
			var sCnae ="";
			for(var i=0;i<full.cnaes.length;i++){
				sCnae = sCnae + full.cnaes[i].cnae  +" - <sup>"+full.cnaes[i].descricao+"</sup><br>" ;
			}
			return sCnae;
		}else{
			return '';
		}

	},

	fnEmail: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		if (!$.pgsi.isNullOrUndefined(full.emails)) {
			var sCnae ="";
			for(var i=0;i<full.emails.length;i++){
				sCnae = sCnae + "<sup>"+full.emails[i].email+"</sup><br>" ;
			}
			return sCnae;
		}else{
			return '';
		}
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

	fnRegime: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		if (!$.pgsi.isNullOrUndefined(full.regime)) {
			return full.regime.nome;
		}
		else{
			return ''
		}
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
		for(var i=0;i<full.documentos.length;i++){
			sDocumentos = sDocumentos +  full.documentos[0].description+ " - "+full.documentos[0].numero + "<br>";
		}

		return sDocumentos;
	},

	locationTable: {

	}
}
</script>


</sec:authorize>
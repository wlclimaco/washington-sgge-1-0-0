<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.location
 * @fileoverview The main namespace for the Location List Page.
 */
pgsi.pages.marca = {

	fnCreateNomeLink : function (val, type, full)
	{ 		if (type !== "display")
		{
			return val;
		}

		return '<a title="View/Edit ' + full.marca + '" href="#/cidade/view?tab=info&pessoaId=' + full.id + '" class="edit_link">' + full.marca + '</a>';

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


	marcaTable: {

	}
}
</script>


</sec:authorize>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.location
 * @fileoverview The main namespace for the Location List Page.
 */
pgsi.pages.cidade = {

	fnCreateNomeLink : function (val, type, full)
	{ 		if (type !== "display")
		{
			return val;
		}

		return '<a title="View/Edit ' + full.nome + '" href="#/cidade/view?tab=info&pessoaId=' + full.id + '" class="edit_link">' + full.nome + '</a>';

	},

	fnEstado : function (val, type, full)
	{ 		if (type !== "display")
		{
			return val;
		}

		if (!$.pgsi.isNullOrUndefined(full.estado)) {
			return '<span>'+full.estado.nome+'</span>';
		}
		else
		{
			return "";
		}
	},

	fnUf : function (val, type, full)
	{ 		if (type !== "display")
		{
			return val;
		}

		if (!$.pgsi.isNullOrUndefined(full.estado)) {
			return '<span>'+full.estado.abreviacao+'</span>';
		}
		else
		{
			return "";
		}
	},


	cidadeTable: {

	}
}
</script>


</sec:authorize>
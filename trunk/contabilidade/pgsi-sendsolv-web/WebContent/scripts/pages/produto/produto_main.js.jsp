<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.produto
 * @fileoverview The main namespace for the Location List Page.
 */
pgsi.pages.produto = {

	fnCreateNomeLink : function (val, type, full)
	{ 		if (type !== "display")
		{
			return val;
		}

		return '<a title="View/Edit ' + full.produto + '" href="#/cidade/view?tab=info&pessoaId=' + full.id + '" class="edit_link">' + full.produto + '</a>';

	},

	fnClassificacao : function (val, type, full)
	{ 		if (type !== "display")
		{
			return val;
		}
		if (!$.pgsi.isNullOrUndefined(full.classificacao)) {
			return '<a title="View/Edit ' + full.classificacao.descricao + '" href="#/cidade/view?tab=info&pessoaId=' + full.classificacao.id + '" class="edit_link">' + full.classificacao.descricao + '</a>';
		}else{
			return "";
		}
	},

	fnUniMed : function (val, type, full)
	{ 		if (type !== "display")
		{
			return val;
		}
		if (!$.pgsi.isNullOrUndefined(full.uniMed.unimedId)) {
			return '<a title="View/Edit ' + full.uniMed.unimedId.sigla + '" href="#/cidade/view?tab=info&pessoaId=' + full.uniMed.unimedId.id + '" class="edit_link">' + full.uniMed.unimedId.sigla + '</a>';
		}else{
			return "";
		}
	},
	fnGrupo : function (val, type, full)
	{ 		if (type !== "display")
		{
			return val;
		}
		if (!$.pgsi.isNullOrUndefined(full.grupo.grupoId)) {
			return '<a title="View/Edit ' + full.grupo.grupoId.grupo + '" href="#/cidade/view?tab=info&pessoaId=' + full.grupo.grupoId.id + '" class="edit_link">' + full.grupo.grupoId.grupo + '</a>';
		}else{
			return "";
		}
	},
	fnSubGrupo : function (val, type, full)
	{ 		if (type !== "display")
		{
			return val;
		}
		if (!$.pgsi.isNullOrUndefined(full.subGrupo.subGrupoId)) {
			return '<a title="View/Edit ' + full.subGrupo.subGrupoId.subGrupo + '" href="#/cidade/view?tab=info&pessoaId=' + full.subGrupo.subGrupoId.id + '" class="edit_link">' + full.subGrupo.subGrupoId.subGrupo + '</a>';
		}else{
			return "";
		}
	},
	fnMarca : function (val, type, full)
	{ 		if (type !== "display")
		{
			return val;
		}
		if (!$.pgsi.isNullOrUndefined(full.marca.marcaId)) {
			return '<a title="View/Edit ' + full.marca.marcaId.marca + '" href="#/cidade/view?tab=info&pessoaId=' + full.marca.marcaId.id + '" class="edit_link">' + full.marca.marcaId.marca + '</a>';
		}else{
			return "";
		}
	},
	fnTabPreco : function (val, type, full)
	{ 		if (type !== "display")
		{
			return val;
		}
		var sTabPreco = ""
		if (!$.pgsi.isNullOrUndefined(full.precoList)) {
				for (var i=0;i<full.precoList.length;i++){
					if(full.precoList[i].precoTypeEnum == "VENDA"){
						sTabPreco = sTabPreco + " "+full.precoList[i].valor+"<br>";
					}
				}
		}
		return sTabPreco;

	},
	fnEstoque : function (val, type, full)
	{ 		if (type !== "display")
		{
			return val;
		}
		var sTabPreco = ""
		if (!$.pgsi.isNullOrUndefined(full.estoqueList)) {
				for (var i=0;i<full.estoqueList.length;i++){
					if(full.estoqueList[i].estoqueTypeEnum == "ATUAL"){
						sTabPreco = sTabPreco + " "+full.estoqueList[i].quant+"<br>";
					}
				}
		}
		return sTabPreco;
	},
	fnStatus : function (val, type, full)
	{ 		if (type !== "display")
		{
			return val;
		}
		var sTabPreco = ""
		if (!$.pgsi.isNullOrUndefined(full.statusList)) {
			sTabPreco = full.statusList[full.statusList.length - 1].status;
		}

		return sTabPreco;
	},

	produtoTable: {

	}
}
</script>


</sec:authorize>
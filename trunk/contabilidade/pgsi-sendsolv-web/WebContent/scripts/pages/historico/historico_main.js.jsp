<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">

pgsi.pages.historico = {

	fnCreateData : function (val, type, full)
	{console.log(full)

		if (type !== "display")
		{
			return val;
		}
		return full.data;
	},
	fnCreateHistoricoItens : function (val, type, full)
	{
		var sHistorico = "";
		if (type !== "display")
		{
			return val;
		}
		if (!$.pgsi.isNullOrUndefined(full.historicoItensList)) {
			for(var i=0;i<full.historicoItensList.length;i++){
				sHistorico = sHistorico + " "+full.historicoItensList[i].acaoType+ " - "+full.historicoItensList[i].tabelaEnum+"<br>";
			}
		}
		return sHistorico;
	},
	historicoTable: {

	}
}

</script>

</sec:authorize>
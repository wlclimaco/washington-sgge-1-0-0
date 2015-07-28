<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">

pgsi.pages.tela = {

	fnCreateData : function (val, type, full)
	{console.log(full)

		if (type !== "display")
		{
			return val;
		}
		return full.data;
	},
	fnCreateTabs : function (val, type, full)
	{
		var sTabs = "";
		if (type !== "display")
		{
			return val;
		}
		if (!$.pgsi.isNullOrUndefined(full.tabs)) {
			for(var i=0;i<full.tabs.length;i++){
				sTabs = sTabs + " "+full.tabs[i].text+ " - "+full.tabs[i].nome+"<br>";
			}
		}
		return sTabs;
	},

	fnCreateFields : function (val, type, full)
	{
		console.log(full)
		var sHistorico = "";
		if (type !== "display")
		{
			return val;
		}

		return sHistorico;
	},
	telaTable: {

	}
}

</script>

</sec:authorize>
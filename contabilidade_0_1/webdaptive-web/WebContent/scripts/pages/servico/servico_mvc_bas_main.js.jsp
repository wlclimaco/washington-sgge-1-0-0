<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>


<script type="text/javascript">
/**
 * @namespace pgsi.pages.location
 * @fileoverview The main namespace for the Location List Page.
 */

qat.pages.servico = {

	fnValorLink : function (val, type, full)
	{

		if (type !== "display")
		{
			return val;
		}

		if(full.preco != null){
			if(full.preco.length > 0){
				return full.preco[full.preco.length - 1].valor;
			}
		}
	},

	fnStatus : function (val, type, full)
	{

		if (type !== "display")
		{
			return val;
		}
		if(full.statusList != null){
			if(full.statusList.length > 0){
				return full.statusList[full.statusList.length - 1].status;
			}
		}else{
			return "";
		}

	},

	fnUsuario : function (val, type, full)
	{

		if (type !== "display")
		{
			return val;
		}


		return 'aaaa'
	},
	fnDataUltAlt : function (val, type, full)
	{

		if (type !== "display")
		{
			return val;
		}


		return 'aaaa'
	},


	servicoTable: {

	}
}
</script>


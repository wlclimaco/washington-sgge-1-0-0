<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>


<script type="text/javascript">
/**
 * @namespace qat.pages.location
 * @fileoverview The main namespace for the Location List Page.
 */
qat.pages.funcionario = {

	enderecoList : function(oEndereco){
		var aObj = [];
		for(var i = 0 ; i< oEndereco.length;i++){
			aObj.push(new enderecoModule.ENDERECO(oEndereco[i]));
		}
		return aObj;
	},


	empresaTable: {

	}
}
</script>



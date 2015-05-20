<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.location
 * @fileoverview The main namespace for the Location List Page.
 */
pgsi.pages.endereco = {
		 fnCreateRequest function(sModelAction){
			 var oEndereco = new Endereco({
				id				: $('#enderecoId').val(),
				logradouro		: $('#logradouro').val(),
				cidade			: $('#cidade').val(),
				estado			: $('#estado').val(),
				bairro			: $('#bairro').val(),
				numero			: $('#numero').val(),
				cep				: $('#cep').val(),
				modelAction 	: sModelAction
			 })
			 return oEndereco;
		 }

}
</script>

</sec:authorize>
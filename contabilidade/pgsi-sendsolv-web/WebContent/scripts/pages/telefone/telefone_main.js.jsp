<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.location
 * @fileoverview The main namespace for the Location List Page.
 */
pgsi.pages.telefone = {

		fnCreateRequest : function(sModelAction){

			oTelefone = [];
			for(var i=0;i< parseInt($('.Editbox9').length,10);i++){
				aTelefone = new Telefone();
				aTelefone.ddd =    $('.ddd:eq('+i+')').val();
				aTelefone.numero = $('.telefone:eq('+i+')').val();
				aTelefone.telefoneTypeEnumValue = $('.Editbox9:eq('+i+')').val();
				aTelefone.modelAction 	 = sModelAction;
				oTelefone.push(aTelefone);
			}

			return oTelefone;
		}
}
</script>

</sec:authorize>
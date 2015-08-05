<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.location
 * @fileoverview The main namespace for the Location List Page.
 */
pgsi.pages.socio = {

		fnCreateRequest : function(sModelAction){

			oSocio = [];
			for(var i=0;i< parseInt($('.socio').length,10);i++){
				aSocio = new Socio();
				aSocio.nome =    $('.socio :eq('+i+')').val();
				aSocio.cota =    $('.cota :eq('+i+')').val();
				aSocio.porcentagem =    $('.porcentagem :eq('+i+')').val();
				aSocio.modelAction 	 = sModelAction;
				oSocio.push(aSocio);
			}

			return oSocio;
		}
}
</script>

</sec:authorize>
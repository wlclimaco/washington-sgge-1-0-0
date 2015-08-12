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
			var dDate = new Date();
			for(var i=0;i< (parseInt($('.socios .form-group').length,10)-1);i++){
				aSocio = new Socio({
					nome               : $('#bookForm').find('[name="book['+i+'].email"]').val(),
					documentos         : [ new Documento({
						documentoType  : "CPF",
						numero         : $('#bookForm').find('[name="book['+i+'].email"]').val(),
						data           : dDate.getTime(),
						modelAction    : sModelAction
					}),
					new Documento({
						documentoType  : "RG",
						numero         : $('#bookForm').find('[name="book['+i+'].email"]').val(),
						data           : dDate.getTime(),
						modelAction    : sModelAction
					})]
					cota 			   : $('#bookForm').find('[name="book['+i+'].email"]').val(),
					porcentagem        : $('#bookForm').find('[name="book['+i+'].email"]').val(),
					modelAction 	   : sModelAction,
					pessoaTypeEnumValue: 6
				});
				oSocio.push(aSocio);
			}

			return oSocio;
		}
}
</script>

</sec:authorize>
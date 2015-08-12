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
			for(var i=0;i< (parseInt($('.telefone .form-group').length,10)-1);i++){
				aTelefone = new Telefone({
					ddd 				  : $('#bookForm').find('[name="book['+i+'].ddd"]').val(),
					numero 				  : $('#bookForm').find('[name="book['+i+'].telefone"]').val(),
					telefoneTypeEnumValue : $('#bookForm').find('[name="book['+i+'].telefoneType"]').val(),
					modelAction 	 	  :	sModelAction
				});
				oTelefone.push(aTelefone);
			}

			return oTelefone;
		}
}
</script>

</sec:authorize>
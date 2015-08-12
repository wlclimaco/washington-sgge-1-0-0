<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.location
 * @fileoverview The main namespace for the Location List Page.
 */
pgsi.pages.email = {

		 fnCreateRequest : function(sModelAction){

			oEmail = [];

			for(var i=0;i< (parseInt($('.email .form-group').length,10)-1);i++){
				aEmail = new Email({
				email 				: $('#bookForm').find('[name="book['+i+'].email"]').val(),
				emailTypeEnumValue  : $('#bookForm').find('[name="book['+i+'].emailTipo"]').val(),
				modelAction 	    : sModelAction
				})
				oEmail.push(aEmail);
			}

			return oEmail;
		 }
}
</script>

</sec:authorize>
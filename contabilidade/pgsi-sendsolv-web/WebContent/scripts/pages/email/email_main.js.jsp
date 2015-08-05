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

			for(var i=0;i< parseInt($('.email-type').length,10);i++){
				aEmail = new Email();
				aEmail.email = $('.email-address:eq('+i+')').val();
				aEmail.emailTypeEnumValue = $('.email-type:eq('+i+')').val();
				aEmail.modelAction 	 = sModelAction;
				oEmail.push(aEmail);
			}

			return oEmail;
		 }
}
</script>

</sec:authorize>
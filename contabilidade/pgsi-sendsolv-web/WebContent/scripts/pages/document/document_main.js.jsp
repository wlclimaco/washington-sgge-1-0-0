<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">

pgsi.pages.documento = {

	fnCreateRequest : function(sModelAction){

		aDocumento = [];

		dDate = new Date();

		if (!$.pgsi.isNullOrUndefined($('#cnpj').val())) {

			var documento = new Documento({
				documentoType  : "CNPJ",
				numero         : $('#cnpj').val(),
				data           : dDate.getTime(),
				modelAction    : sModelAction
			});
			aDocumento.push(documento);

		}
		if (!$.pgsi.isNullOrUndefined($('#ie').val())) {

			var documento = new Documento({
				documentoType  : "IE",
				numero         : $('#ie').val(),
				data           : dDate.getTime(),
				modelAction    : sModelAction
			});
			aDocumento.push(documento);

		}
		if (!$.pgsi.isNullOrUndefined($('#im').val())) {

			var documento = new Documento({
				documentoType  : "IM",
				numero         : $('#im').val(),
				data           : dDate.getTime(),
				modelAction    : sModelAction
			});
			aDocumento.push(documento);

		}

		 return aDocumento;
	 }

}

</script>

</sec:authorize>
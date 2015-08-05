<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">

pgsi.pages.documento = {

	fnCreateRequest : function(sModelAction){

		aDocumento = [];

		var documento = new Documento();

		dDate = new Date();

		if (!$.pgsi.isNullOrUndefined($('#cnpj').val())) {

			documento.documentoType  = "CNPJ";
			documento.numero         = $('#cnpj').val();
			documento.data           = dDate.getTime();
			documento.modelAction 	 = sModelAction
			aDocumento.push(documento);

		}
		if (!$.pgsi.isNullOrUndefined($('#ie').val())) {

			documento.documentoType  = "IE";
			documento.numero         = $('#ie').val();
			documento.data           = dDate.getTime();
			documento.modelAction 	 = sModelAction
			aDocumento.push(documento);

		}
		if (!$.pgsi.isNullOrUndefined($('#im').val())) {

			documento.documentoType  = "IM";
			documento.numero         = $('#im').val();
			documento.data           = dDate.getTime();
			documento.modelAction 	 = sModelAction
			aDocumento.push(documento);

		}

		 return aDocumento;
	 }

}

</script>

</sec:authorize>
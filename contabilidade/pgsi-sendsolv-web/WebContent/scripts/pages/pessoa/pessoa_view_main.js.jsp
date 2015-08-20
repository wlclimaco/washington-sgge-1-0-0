<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">


pgsi.pages.pessoa.view = {

		fillDocumento : function(oDocumentoList,description) {
			var iNumber=0;

			if (!$.pgsi.isNullOrUndefined(oDocumentoList)){
				for (var y=0; y < oDocumentoList.length; y++) {
					if(oDocumentoList[y].description == description){
						iNumber = oDocumentoList[y].numero;
					}
				}
			}

			return iNumber;
		},


		fnCallbackRefleshPageCliente : function() {

			var fnCallBackFetch = function(oResponseFetch) {
				if (oResponseFetch.operationSuccess == true) {
					pgsi.pages.cliente.view.fnFillCliente(oResponseFetch);

					$("#action-dialog").dialog('close');
				}
				else{
					pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponseFetch,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
				}
				$.pgsi.progressBar.stop();
			}


			$.pgsi.ajax.post({
				 sUrl       : "api/cliente/fetch",
				 oRequest   : {id:parseInt($.address.parameter("locationId"),10)},
				 fnCallback : fnCallBackFetch
			});

		},

	fnFillCliente : function(oResponse) {

		var oCliente = oResponse.clienteList[0];

console.log(oCliente)

		$("#nome-field").text(oCliente.nome);

		if(oCliente.tipoPessoa == 2){
			$("#cnpj-field").text(pgsi.pages.pessoa.view.fillDocumento(oCliente.documentos,"CNPJ"));

			$("#im-field").text(pgsi.pages.pessoa.view.fillDocumento(oCliente.documentos,"IM"));

			$("#IE-field").text(pgsi.pages.pessoa.view.fillDocumento(oCliente.documentos,"IE"));
		}else{
			$("#cnpj").text('CPF')
			$("#cnpj-field").text(pgsi.pages.pessoa.view.fillDocumento(oCliente.documentos,"CPF"));

			$("#im").text('Identidade')
			$("#im-field").text(pgsi.pages.pessoa.view.fillDocumento(oCliente.documentos,"RG"));

			$("#ie").text("");
		}


		if(oCliente.tipoPessoa == 2){
			$("#pessoa-tipo-field").text("Juridica");
		}else{
			$("#pessoa-tipo-field").text("Fisica");
		}
		pgsi.pages.phone.view.fillFields(oCliente.telefones);

		pgsi.pages.address.view.fillFields(oCliente.enderecos);
		var sEmail = "";

		for (var i = 0; i < oCliente.emails.length; i++) {
			sEmail = sEmail + oCliente.emails[i].description + " " +oCliente.emails[i].email +"<br>"
		}
		$('#phone-container').append(sEmail);

		// fill notes
		pgsi.pages.note.view.fill(oCliente.notes, "");

	}

}
</script>

</sec:authorize>
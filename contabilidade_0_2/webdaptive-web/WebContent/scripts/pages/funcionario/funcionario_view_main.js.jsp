<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">


qat.pages.funcionario.view = {

	fillCnae : function(oCnaeList) {

			var oCnae = null;
			var sNoteList = "";
			var sDelUpdLinks = "";

			var sDate;
			var sUser;
			var sNoteText;

			var $container = $("section.cnae").find("div.container");

			$("section.cnae").find(".col-title").find('a').unbind("click");

			for (var i=0; i < oCnaeList.length; i++) {
				oCnae = oCnaeList[i];

				sUser = oCnae.createUser;

				if (!$.qat.isNullOrUndefined(oCnae.modifyDateUTC)) {
					sDate = $.qat.date.format(new Date(oCnae.modifyDateUTC), "mm/dd/yy h:i A", true);
				}

				else {
					sDate = $.qat.date.format(new Date(oCnae.createDateUTC), "mm/dd/yy h:i A", true);
				}

				sNoteText = oCnae.description;
				iNoteId = oCnae.id;
				sCnaeNumber = oCnae.number;

				<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
				sDelUpdLinks = "<div class='small-box'><div class='links viewNote'><a href='"+iNoteId+"'  class='ui-subtitle edit' title='" + $.qat.locale.get('commons.pages.edit') + "'> <span class='icon-small-button icon-nav icon-pencil edit'></span> <span>" + $.qat.locale.get('commons.pages.edit') +"</span></a><a href='"+iNoteId+"'  class='ui-subtitle delete' title='" + $.qat.locale.get('commons.pages.delete') + "'> <span class='icon-small-button icon-nav icon-trash-bin delete'></span> <span>"+$.qat.locale.get('commons.pages.delete')+"</span></a></div></div>";
				</sec:authorize>


				sNoteList = sNoteList + "<div class='outer-box'><div class='box note'>" + sDelUpdLinks + "<span class='bold'>" + sUser + "</span><span class='date'>" + sDate +"</span><p class='full-text hide'>" +sCnaeNumber+ "<br>" + sNoteText + "</p><p></p><div class='text_here'><span class='ellipsis_text'>" +sCnaeNumber+ "<br>" + sNoteText + "</span></div></div></div>";
			}

			// Removes content from the session
			$container.empty();

			if (sNoteList.length > 0) {
				$container.append(sNoteList);
				// Limiting content
				// Configuring the initial settings to use
				$container.find('.text_here').ThreeDots({max_rows:4});
			}

			else {
				$("section.notes").find('div.container').append("<p class='empty'>" + $.qat.locale.get("page.business.view.note.empty") + "</p>");
			}

			// Attach add/edit/delete events
			$("section.notes.view  a").click(function(event)
			{

			});
		},

	fnFillFuncionario : function(oResponse) {

		var oFuncionario = oResponse.funcionarioList[0];

console.log(oFuncionario)

	//	<span class="label">Nome/Fantasia</span>
				$("#nome-field").text(oFuncionario.nome);

	//			<span class="label">CNPJ</span>

				for(var i=0;i<oFuncionario.documentos.length;i++){
					if(oFuncionario.documentos[i].type == "CNPJ"){
						$("#cnpj-field").text(oFuncionario.documentos[i].numero);
					}else if(oFuncionario.documentos[i].type == "IM") {
						$("#im-field").text(oFuncionario.documentos[i].numero);
					}else if(oFuncionario.documentos[i].type == "IE") {
						$("#IE-field").text(oFuncionario.documentos[i].numero);
					}
				}

				$("#regime-field").text(oFuncionario.regime);

				qat.pages.phone.view.fillFields(oFuncionario.telefones);

				qat.pages.address.view.fillFields(oFuncionario.enderecos);
				var sEmail = "";

				for (var i = 0; i < oFuncionario.emails.length; i++) {
					sEmail = sEmail + oFuncionario.emails[i].description + " " +oFuncionario.emails[i].email +"<br>"
				}
				$('#phone-container').append(sEmail);

				qat.pages.funcionario.view.fillCnae(oFuncionario.cnaes)

		// Sets the page title
	//	$.qat.pageLoader.title(oLocation.name, true);

		// fill phone fields
//		qat.pages.phone.view.fillFields(oLocation.contactList);
		// fill address fields
//		qat.pages.address.view.fillFields(oLocation.contactList);

//		qat.version.versionBusiness = oLocation.version;

		// fill notes
//		qat.pages.note.view.fill(oLocation.noteList, oLocation);

	},

	displayLocationFields : function(){
		$("#business-view").find('.location').removeClass("hide");
		$("#business-view").find('.organization').addClass("hide");
	}

}
</script>

</sec:authorize>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">


pgsi.pages.empresa.view = {

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

				if (!$.pgsi.isNullOrUndefined(oCnae.modifyDateUTC)) {
					sDate = $.pgsi.date.format(new Date(oCnae.modifyDateUTC), "mm/dd/yy h:i A", true);
				}

				else {
					sDate = $.pgsi.date.format(new Date(oCnae.createDateUTC), "mm/dd/yy h:i A", true);
				}

				sNoteText = oCnae.description;
				iNoteId = oCnae.id;
				sCnaeNumber = oCnae.number;

				<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
				sDelUpdLinks = "<div class='small-box'><div class='links viewNote'><a href='"+iNoteId+"'  class='ui-subtitle edit' title='" + $.pgsi.locale.get('commons.pages.edit') + "'> <span class='icon-small-button icon-nav icon-pencil edit'></span> <span>" + $.pgsi.locale.get('commons.pages.edit') +"</span></a><a href='"+iNoteId+"'  class='ui-subtitle delete' title='" + $.pgsi.locale.get('commons.pages.delete') + "'> <span class='icon-small-button icon-nav icon-trash-bin delete'></span> <span>"+$.pgsi.locale.get('commons.pages.delete')+"</span></a></div></div>";
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
				$("section.notes").find('div.container').append("<p class='empty'>" + $.pgsi.locale.get("page.business.view.note.empty") + "</p>");
			}

			// Attach add/edit/delete events
			$("section.notes.view  a").click(function(event)
			{

			});
		},

	fnFillEmpresa : function(oResponse) {

		var oEmpresa = oResponse.empresaList[0];

console.log(oEmpresa)

	//	<span class="label">Nome/Fantasia</span>
				$("#nome-field").text(oEmpresa.nome);

	//			<span class="label">CNPJ</span>

				for(var i=0;i<oEmpresa.documentos.length;i++){
					if(oEmpresa.documentos[i].type == "CNPJ"){
						$("#cnpj-field").text(oEmpresa.documentos[i].numero);
					}else if(oEmpresa.documentos[i].type == "IM") {
						$("#im-field").text(oEmpresa.documentos[i].numero);
					}else if(oEmpresa.documentos[i].type == "IE") {
						$("#IE-field").text(oEmpresa.documentos[i].numero);
					}
				}

				$("#regime-field").text(oEmpresa.regime);

				pgsi.pages.phone.view.fillFields(oEmpresa.telefones);

				pgsi.pages.address.view.fillFields(oEmpresa.enderecos);
				var sEmail = "";

				for (var i = 0; i < oEmpresa.emails.length; i++) {
					sEmail = sEmail + oEmpresa.emails[i].description + " " +oEmpresa.emails[i].email +"<br>"
				}
				$('#phone-container').append(sEmail);

				pgsi.pages.empresa.view.fillCnae(oEmpresa.cnaes)

		// Sets the page title
	//	$.pgsi.pageLoader.title(oLocation.name, true);

		// fill phone fields
//		pgsi.pages.phone.view.fillFields(oLocation.contactList);
		// fill address fields
//		pgsi.pages.address.view.fillFields(oLocation.contactList);

//		pgsi.version.versionBusiness = oLocation.version;

		// fill notes
//		pgsi.pages.note.view.fill(oLocation.noteList, oLocation);

	},

	displayLocationFields : function(){
		$("#business-view").find('.location').removeClass("hide");
		$("#business-view").find('.organization').addClass("hide");
	}

}
</script>

</sec:authorize>
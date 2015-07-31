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
			if (!$.pgsi.isNullOrUndefined(oCnaeList)){
				if (!$.pgsi.isNullOrUndefined(oCnaeList[0].idCnae)){
					for (var i=0; i < oCnaeList.length; i++) {
						oCnae = oCnaeList[i].idCnae;

						sUser = oCnae.createUser;

						if (!$.pgsi.isNullOrUndefined(oCnae.modifyDateUTC)) {
							sDate = $.pgsi.date.format(new Date(oCnae.modifyDateUTC), "mm/dd/yy h:i A", true);
						}

						else {
							sDate = $.pgsi.date.format(new Date(oCnae.createDateUTC), "mm/dd/yy h:i A", true);
						}

						sNoteText = oCnae.descricao;
						iNoteId = oCnae.id;
						sCnaeNumber = oCnae.codigo + "      Cnae :"+ oCnae.cnae;


						sDelUpdLinks = "<div class='small-box'><div class='links viewNote'><a href='"+iNoteId+"'  class='ui-subtitle edit' title='" + $.pgsi.locale.get('commons.pages.edit') + "'> <span class='icon-small-button icon-nav icon-pencil edit'></span> <span>" + $.pgsi.locale.get('commons.pages.edit') +"</span></a><a href='"+iNoteId+"'  class='ui-subtitle delete' title='" + $.pgsi.locale.get('commons.pages.delete') + "'> <span class='icon-small-button icon-nav icon-trash-bin delete'></span> <span>"+$.pgsi.locale.get('commons.pages.delete')+"</span></a></div></div>";



						sNoteList = sNoteList + "<div class='outer-box'><div class='box note'>" + sDelUpdLinks + "<span class='bold'>" + sUser + "</span><span class='date'>" + sDate +"</span><p class='full-text hide'>" +sCnaeNumber+ "<br>" + sNoteText + "</p><p></p><div class='text_here'><span class='ellipsis_text'>" +sCnaeNumber+ "<br>" + sNoteText + "</span></div></div></div>";
					}
				}
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

		fillFilial : function(oFilialList) {

			var oFilial = null;
			var sNoteList = "";
			var sDelUpdLinks = "";

			var sDate;
			var sUser;
			var sNoteText;
			var sFilialNumber="";
			var sCnpj="";
			var $container = $("section.filial").find("div.container");

			$("section.filial").find(".col-title").find('a').unbind("click");
			if (!$.pgsi.isNullOrUndefined(oFilialList)){
				if (!$.pgsi.isNullOrUndefined(oFilialList)){
					for (var i=0; i < oFilialList.length; i++) {
						oFilial = oFilialList[i];

						sUser = oFilial.nome;

						if (!$.pgsi.isNullOrUndefined(oFilial.modifyDateUTC)) {
							sDate = $.pgsi.date.format(new Date(oFilial.modifyDateUTC), "mm/dd/yy h:i A", true);
						}

						else {
							sDate = $.pgsi.date.format(new Date(oFilial.createDateUTC), "mm/dd/yy h:i A", true);
						}

						sNoteText = oFilial.nome;
						iNoteId = oFilial.id;

						if (!$.pgsi.isNullOrUndefined(oFilial.enderecos)){
							for (var y=0; y < oFilial.enderecos.length; y++) {
								var endereco = oFilial.enderecos[y];
								sFilialNumber = sFilialNumber + "("+endereco.cep + ") "+ endereco.logradouro +" "+endereco.numero+ " "+endereco.cidade.nome+" "+endereco.estado.abreviacao+" <br>";
							}
						}

						if (!$.pgsi.isNullOrUndefined(oFilial.documentos)){
							for (var y=0; y < oFilial.documentos.length; y++) {
								var endereco = oFilial.documentos[y];
								if(endereco.description === "CNPJ"){
									sCnpj = endereco.numero;
								}
							}
						}

						sDelUpdLinks = "<div class='small-box'><div class='links viewNote'><a href='"+iNoteId+"'  class='ui-subtitle edit' title='" + $.pgsi.locale.get('commons.pages.edit') + "'> <span class='icon-small-button icon-nav icon-pencil edit'></span> <span>" + $.pgsi.locale.get('commons.pages.edit') +"</span></a><a href='"+iNoteId+"'  class='ui-subtitle delete' title='" + $.pgsi.locale.get('commons.pages.delete') + "'> <span class='icon-small-button icon-nav icon-trash-bin delete'></span> <span>"+$.pgsi.locale.get('commons.pages.delete')+"</span></a></div></div>";



						sNoteList = sNoteList + "<div class='outer-box'><div class='box note'>" + sDelUpdLinks + "<span class='bold'>" + sUser + "</span><span class='date'>" + sDate +"</span><p class='full-text hide'>" +sFilialNumber+ "<br>" + sNoteText + "</p><p></p><div class='text_here'><span class='ellipsis_text'>" +sFilialNumber+ "<br>" + sCnpj + "</span></div></div></div>";
						sFilialNumber = "";
						sCnpj = "";
					}
				}
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

		fillDeposito : function(oFilialList) {

			var oFilial = null;
			var sNoteList = "";
			var sDelUpdLinks = "";

			var sDate;
			var sUser;
			var sNoteText;
			var sFilialNumber="";
			var sCnpj="";
			var $container = $("section.deposito").find("div.container");

			$("section.deposito").find(".col-title").find('a').unbind("click");
			if (!$.pgsi.isNullOrUndefined(oFilialList)){
				if (!$.pgsi.isNullOrUndefined(oFilialList)){
					for (var i=0; i < oFilialList.length; i++) {
						oFilial = oFilialList[i];

						sUser = oFilial.nome;

						if (!$.pgsi.isNullOrUndefined(oFilial.modifyDateUTC)) {
							sDate = $.pgsi.date.format(new Date(oFilial.modifyDateUTC), "mm/dd/yy h:i A", true);
						}

						else {
							sDate = $.pgsi.date.format(new Date(oFilial.createDateUTC), "mm/dd/yy h:i A", true);
						}

						sNoteText = oFilial.nome;
						iNoteId = oFilial.id;

						if (!$.pgsi.isNullOrUndefined(oFilial.enderecos)){
							for (var y=0; y < oFilial.enderecos.length; y++) {
								var endereco = oFilial.enderecos[y];
								sFilialNumber = sFilialNumber + "("+endereco.cep + ") "+ endereco.logradouro +" "+endereco.numero+ " "+endereco.cidade.nome+" "+endereco.estado.abreviacao+" <br>";
							}
						}

						if (!$.pgsi.isNullOrUndefined(oFilial.documentos)){
							for (var y=0; y < oFilial.documentos.length; y++) {
								var endereco = oFilial.documentos[y];
								if(endereco.description === "CNPJ"){
									sCnpj = endereco.numero;
								}
							}
						}

						sDelUpdLinks = "<div class='small-box'><div class='links viewNote'><a href='"+iNoteId+"'  class='ui-subtitle edit' title='" + $.pgsi.locale.get('commons.pages.edit') + "'> <span class='icon-small-button icon-nav icon-pencil edit'></span> <span>" + $.pgsi.locale.get('commons.pages.edit') +"</span></a><a href='"+iNoteId+"'  class='ui-subtitle delete' title='" + $.pgsi.locale.get('commons.pages.delete') + "'> <span class='icon-small-button icon-nav icon-trash-bin delete'></span> <span>"+$.pgsi.locale.get('commons.pages.delete')+"</span></a></div></div>";



						sNoteList = sNoteList + "<div class='outer-box'><div class='box note'>" + sDelUpdLinks + "<span class='bold'>" + sUser + "</span><span class='date'>" + sDate +"</span><p class='full-text hide'>" +sFilialNumber+ "<br>" + sNoteText + "</p><p></p><div class='text_here'><span class='ellipsis_text'>" +sFilialNumber+ "<br>" + sCnpj + "</span></div></div></div>";
						sFilialNumber = "";
						sCnpj = "";
					}
				}
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

		fillPlano : function(oPlanoList) {

			var oPlano = null;
			var sNoteList = "";
			var sDelUpdLinks = "";

			var sDate;
			var sUser;
			var sNoteText;
			var sPlanoNumber="";
			var sCnpj="";
			var fValor = 0;
			var $container = $("section.plano").find("div.container");

			$("section.plano").find(".col-title").find('a').unbind("click");
			if (!$.pgsi.isNullOrUndefined(oPlanoList)){
				if (!$.pgsi.isNullOrUndefined(oPlanoList)){
					for (var i=0; i < oPlanoList.length; i++) {
						oPlano = oPlanoList[i];


						if (!$.pgsi.isNullOrUndefined(oPlano.modifyDateUTC)) {
							sDate = $.pgsi.date.format(new Date(oPlano.modifyDateUTC), "mm/dd/yy h:i A", true);
						}

						else {
							sDate = $.pgsi.date.format(new Date(oPlano.createDateUTC), "mm/dd/yy h:i A", true);
						}
						if (!$.pgsi.isNullOrUndefined(oPlano.produto)){
							sUser = oPlano.produto.produto;
							//debugger
							if (!$.pgsi.isNullOrUndefined(oPlano.produto.precoList)){
								for (var y=0; y < oPlano.produto.precoList.length; y++) {
									if(oPlano.produto.precoList[y].precoTypeEnum == "PLANO"){
										fValor = oPlano.produto.precoList[y].valor;
									}
								}
							}
						}

							sNoteText = $.pgsi.date.format(new Date(oPlano.dataInicio), "mm/dd/yy h:i A", true)+" - "+$.pgsi.date.format(new Date(oPlano.dataFinal), "mm/dd/yy h:i A", true);
							iNoteId = oPlano.id;
							sCnpj = oPlano.numeroContrato || "0000-00";



						sDelUpdLinks = "<div class='small-box'><div class='links viewNote'><a href='"+iNoteId+"'  class='ui-subtitle edit' title='" + $.pgsi.locale.get('commons.pages.edit') + "'> <span class='icon-small-button icon-nav icon-pencil edit'></span> <span>" + $.pgsi.locale.get('commons.pages.edit') +"</span></a><a href='"+iNoteId+"'  class='ui-subtitle delete' title='" + $.pgsi.locale.get('commons.pages.delete') + "'> <span class='icon-small-button icon-nav icon-trash-bin delete'></span> <span>"+$.pgsi.locale.get('commons.pages.delete')+"</span></a></div></div>";
						sNoteList = sNoteList + "<div class='outer-box'><div class='box note'>" + sDelUpdLinks + "<span class='bold'>" + sUser + "</span><span class='date'>" + sDate +"</span><p class='full-text hide'>" +fValor+ "<br>" + sNoteText + "</p><p></p><div class='text_here'><span class='ellipsis_text'><span>"+fValor+"</span><br><sup>" +sNoteText+ "<br>" + sCnpj + "</sup></span></div></div></div>";
						sNoteText = "";
						sCnpj = "";
						iNoteId = "";
						sUser = "";
						fValor = 0;
					}
				}
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



				$("#cnpj-field").text(pgsi.pages.empresa.view.fillDocumento(oEmpresa.documentos,"CNPJ"));

				$("#im-field").text(pgsi.pages.empresa.view.fillDocumento(oEmpresa.documentos,"IM"));

				$("#IE-field").text(pgsi.pages.empresa.view.fillDocumento(oEmpresa.documentos,"IE"));



				$("#regime-field").text(oEmpresa.regime.descricao);

				pgsi.pages.phone.view.fillFields(oEmpresa.telefones);

				pgsi.pages.address.view.fillFields(oEmpresa.enderecos);
				var sEmail = "";

				for (var i = 0; i < oEmpresa.emails.length; i++) {
					sEmail = sEmail + oEmpresa.emails[i].description + " " +oEmpresa.emails[i].email +"<br>"
				}
				$('#phone-container').append(sEmail);

				pgsi.pages.empresa.view.fillCnae(oEmpresa.cnaes);

				pgsi.pages.empresa.view.fillFilial(oEmpresa.filialList);

				pgsi.pages.empresa.view.fillDeposito(oEmpresa.depositoList);

				pgsi.pages.empresa.view.fillPlano(oEmpresa.planoList);

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
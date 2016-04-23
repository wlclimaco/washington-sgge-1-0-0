<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<script type="text/javascript">
/**
 * @namespace qat.pages.location
 * @fileoverview The main namespace for the Location List Page.
 */
qat.pages.entidade = {

		/**
	* Set of functions used by the datatables plugin to customize field values
	*/

	// Returns link for edit view
	fnCreateEmpresaNameLink : function (val, type, full)
	{
	var sCnpj="";
		if (type !== "display")
		{
			return val;
		}
		if (!$.qat.isNullOrUndefined(full.documentos)) {
			for(var i=0;i<full.documentos.length;i++){
				if((full.documentos[i].documentoType == "CNPJ")||(full.documentos[i].documentoType == "CPF")){
					sCnpj = full.documentos[i].numero;
				}
			}
			return '<a title="View/Edit ' + sCnpj + '" href="#/empresa/view?tab=info&locationId=' + full.id + '" class="edit_link">' + sCnpj + '</a>';
		}else{
			return '';
		}
	},

	fnCreateNomeLink : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		return '<a title="View/Edit ' + full.nome + '" href="#/empresa/view?tab=info&locationId=' + full.id + '" class="edit_link">' + full.nome + '</a>';

	},
	fnCnae: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		if (!$.qat.isNullOrUndefined(full.cnaes)) {
			if (!$.qat.isNullOrUndefined(full.cnaes.idCnae)) {
				var sCnae ="";
				for(var i=0;i<full.cnaes.length;i++){
					sCnae = sCnae + full.cnaes[i].idCnae.cnae  +" - <sup>"+full.cnaes[i].idCnae.descricao+"</sup><br>" ;
				}
				return sCnae;
			}else{
				return '';
			}
		}else{
			return '';
		}

	},

	fnEmail: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		var sCnae ="";
		if (!$.qat.isNullOrUndefined(full.emails)) {

			for(var i=0;i<full.emails.length;i++){
				sCnae = sCnae + "<sup>"+full.emails[i].email+"</sup><br>" ;
			}
		}

		return sCnae;
	},

	fnTelefone: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		var sCnae ="";
		if (!$.qat.isNullOrUndefined(full.telefones)) {
			for(var i=0;i<full.telefones.length;i++){
				sCnae = sCnae + "("+full.telefones[i].ddd+ ") "+full.telefones[i].numero+"<br>";
			}
		}
		return sCnae;
	},

	fnRegime: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		if (!$.qat.isNullOrUndefined(full.regime)) {
			return full.regime.nome;
		}
		else{
			return ''
		}
	},

	fnEndereco: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		var sCnae ="";
		if (!$.qat.isNullOrUndefined(full.enderecos)) {
			for(var i=0;i<full.enderecos.length;i++){
				sCnae = sCnae + ' '+full.enderecos[i].logradouro + " "+full.enderecos[i].numero + " "+full.enderecos[i].bairro + " "+full.enderecos[i].cidade || "" +'<br>';
			}
		}
		return sCnae;

	},
	fnDocumento: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		var sDocumentos ="";
		if (!$.qat.isNullOrUndefined(full.documentos)) {
			for(var i=0;i<full.documentos.length;i++){
				sDocumentos = sDocumentos +  full.documentos[i].documentoType + " - "+full.documentos[i].numero + "<br>";
			}
		}

		return sDocumentos;
	},

	fnStatus: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		var sStatus ="";
		if (!$.qat.isNullOrUndefined(full.statusList[0])) {
			sStatus = // $.qat.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.CdStatusTypeEnum",full.statusList[0].status);
			sStatus = full.statusList[0].status;
		}
		return sStatus;
	},

	fnRequestFilter : function(){
		var oParam = {};

		// filter empresa id
		if (!$.qat.isNullOrUndefined($('#idFilter').val())) {
			sessionStorage.filterId = $('#idFilter').val()
			$.address.parameter('id',$('#idFilter').val())
		}

		// filter empresa Status
		var iIds = '',oIds = [];

		if (!$.qat.isNullOrUndefined($('.statusFilter'))) {
			$.each($('.statusFilter').find('input'), function( i, elem ) {
				if($(this).prop('checked') == true){
					iIds = iIds + "|" + $(this).val();
				}

			});

console.log(iIds);
			sessionStorage.statusId = iIds;
			$.address.parameter('status',iIds)

			var sIds = $.address.parameter('status');
			console.log(sIds)
			if (!$.qat.isNullOrUndefined(sIds)) {
				aIds = sIds.split('|');
				console.log(aIds);
				for(var i = 0; i < aIds.length;i++){

					if (!$.qat.isNullOrUndefined(aIds[i])) {
						if (aIds[i] !="") {
							oIds.push(aIds[i]);
						}
					}
				}

			}
		}
		oParam.criteria = {
				id     : $.address.parameter('id'),
				status : oIds
		}



		return oParam;

	}
}
</script>

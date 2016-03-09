<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<script type="text/javascript">
/**
 * @namespace qat.pages.location
 * @fileoverview The main namespace for the Location List Page.
 */
qat.pages.pessoa = {

		 nowDate : function (){
				a = new Date()
				return a.getTime();
			},

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

	fnProfissao: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		debugger;
		//if (!$.qat.isNullOrUndefined(full.profissao)) {
		//	for(var i=0;i<full.enderecos.length;i++){
		//		sCnae = sCnae + ' '+full.enderecos[i].logradouro + " "+full.enderecos[i].numero + " "+full.enderecos[i].bairro + " "+full.enderecos[i].cidade || "" +'<br>';
	//		}
	//	}
	return "";
	},

	fnSalario: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		//if (!$.qat.isNullOrUndefined(full.regime)) {
		//	return full.regime.nome;
		//}
		//else{
			return ''
		//}
	},

	fnEventos: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		//if (!$.qat.isNullOrUndefined(full.regime)) {
		//	return full.regime.nome;
		//}
		//else{
			return ''
		//}
	},

	fnBeneficios: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		//if (!$.qat.isNullOrUndefined(full.regime)) {
		//	return full.regime.nome;
		//}
		//else{
			return ''
		//}
	},


	fnStatus: function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		var sStatus ="";
		//if (!$.qat.isNullOrUndefined(full.statusList[0])) {
		//	sStatus = // $.qat.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.CdStatusTypeEnum",full.statusList[0].status);
		//	sStatus = full.statusList[0].status;
		//}
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

	},

			createTeste : function(){

				try{
							return {id:0,
				            nome:"TESTE0001",
				            nomePai:"nomePai",
							emprId : 9,
				            nomeMae :"nomeMae",
				            nomeConjugue:"nomeConjugue",
				            estadoCivil:0,
				            tipoPessoa:1,
				            datanasc:qat.pages.pessoa.nowDate(),
				            foto:"foto",
				            matricula:"matricula",
				            dataAdm :qat.pages.pessoa.nowDate(),
				            pessoaTypeEnumValue:3,
				            sexo:1,
							modelAction : "INSERT",
				           	documentos:[{
								documentoTypeEnumValue:1,
								numero : "123456789",
								data : qat.pages.pessoa.nowDate(),
								modelAction : "INSERT",
								estado :{id:1}
							}],
				        	enderecos:[{
								logradouro : "Logradouro",
								cep :"CEP",
								bairro : "BAIRRO",
								numero : "NUMERO",
								complemento :"COMPLEMENTO",
								enderecoTypeValue : 1,
								modelAction : "INSERT",
								cidade : {id:1},
							}],
				        	emails : [{email : "email001@gmail.com",modelAction : "INSERT",emailTypeEnumValue:1},{email : "email002@gmail.com",modelAction : "INSERT",emailTypeEnumValue:2},{email : "email003@gmail.com",modelAction : "INSERT",emailTypeEnumValue:3}],
							telefones:[{ddd :"034",numero:"3315-8065",modelAction : "INSERT",telefoneTypeEnumValue : 1},{ddd :"034",numero:"3315-8065",modelAction : "INSERT",telefoneTypeEnumValue : 2}],
							bancos:[{
								id:0,
								modelAction : "INSERT",
								numCont:"123456789",
								saldo:1.99,
								bancoId :{
									id:0,
									modelAction : "INSERT",
									nome:"BANCO DO BRASIL",
								},
								agenciaId:{
									id:0,
									modelAction : "INSERT",
									nome:"AGENCIA NOME",
									enderecos:[{
										logradouro : "Logradouro",
										cep :"CEP",
										bairro : "BAIRRO",
										numero : "NUMERO",
										complemento :"COMPLEMENTO",
										enderecoTypeValue : 1,
										modelAction : "INSERT",
										cidade : {id:1},
									}],
									emails : [{email : "email001@gmail.com",modelAction : "INSERT",emailTypeEnumValue:1},{email : "email002@gmail.com",modelAction : "INSERT",emailTypeEnumValue:2},{email : "email003@gmail.com",modelAction : "INSERT",emailTypeEnumValue:3}],
									telefones:[{ddd :"034",numero:"3315-8065",modelAction : "INSERT",telefoneTypeEnumValue : 1},{ddd :"034",numero:"3315-8065",modelAction : "INSERT",telefoneTypeEnumValue : 2}],
									gerente:"gerento",
									responsavelConta:"responsavelConta",
									numeroConta:"123456"
								}
							}],
				        	formaPagamentoList:[{}],
				        	condPagList:[{}],
				        	contatoList:[{}],
				        	salarios:[{
								id :0,
								data:qat.pages.pessoa.nowDate(),
								valor:1999.99,
								modelAction : "INSERT",
								profissao : {
									modelAction : "INSERT"
								}
							}],

				        	horarios:[{}],
				        	beneficios:{
								id:0,
								benefId : {
									id : 0,
									nome : "VALE TRANSPORTE",
									codigo : "0001",
									descricao :"DESCRICAO",
									valor 	:1.99,
									porcentagem: 10,
									tipo: "1" ,
									createUser:"system",
									createDateUTC:qat.pages.pessoa.nowDate(),
									modifyUser:"",
									modifyDateUTC:0,
									modelAction: "INSERT"
								},
								idFunc : 1,
								createUser : "system",
								createDateUTC:qat.pages.pessoa.nowDate(),
								modifyUser:"",
								modifyDateUTC:0,
								modelAction:"INSERT"
							},
				        	eventosList:[{
								id:0,
								idEvent : {

									isMensal : true,
									isSistema : true,
									noteText:"NOTE TEXT",
									id : 0,
									nome : "VALE TRANSPORTE",
									codigo : "0001",
									descricao :"DESCRICAO",
									valor 	:1.99,
									porcentagem: 10,
									tipo: "1" ,
									createUser:"system",
									createDateUTC:qat.pages.pessoa.nowDate(),
									modifyUser:"",
									modifyDateUTC:0,
									modelAction: "INSERT"
								},
							    idFunc : 1,
								createUser : "system",
								createDateUTC:qat.pages.pessoa.nowDate(),
								modifyUser:"",
								modifyDateUTC:0,
								modelAction:"INSERT"
							}]
							}

							} catch (e) {
						debugger
				    	console.log(e);
				    }
			}

}
</script>
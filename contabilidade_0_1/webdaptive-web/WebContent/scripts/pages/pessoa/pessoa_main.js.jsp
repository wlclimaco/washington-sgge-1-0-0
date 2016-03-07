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

			createTeste : function(){

				try{
							return {id:0,
				            nome:"TESTE0001",
				            nomePai:"nomePai",
				            nomeMae :"nomeMae",
				            nomeConjugue:"nomeConjugue",
				            estadoCivil:0,
				            tipoPessoa:1,
				            datanasc:qat.pages.pessoa.nowDate(),
				            foto:"foto",
				            matricula:"matricula",
				            dataAdm :qat.pages.pessoa.nowDate(),
				            pessoaTypeEnumValue:1,
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
							bancos:[{}],
				        	formaPagamentoList:[{}],
				        	condPagList:[{}],
				        	contatoList:[{}],
				        	salarios:[{}],
				        	horarios:[{}],
				        	beneficios:[{}],
				        	eventosList:[{}]
							}

							} catch (e) {
						debugger
				    	console.log(e);
				    }
			}

}
</script>
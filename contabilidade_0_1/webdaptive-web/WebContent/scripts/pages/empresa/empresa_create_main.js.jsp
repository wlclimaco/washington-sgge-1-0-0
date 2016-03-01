<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>


<script type="text/javascript">
	qat.pages.empresa.form = {

		nowDate : function (){
			a = new Date()
			return a.getTime();
		},

		createTeste : function(){
						return {id:0,
			            nome:"TESTE0001",
			        	entidadeEnumValue:1,
						regime: {id:0},
						modelAction : "INSERT",
			           	documentos:[{
							documentoTypeEnumValue:1,
							numero : "123456789",
							data : qat.pages.empresa.form.nowDate(),
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
							cidade : {id:1}
						}],
			        	emails : [{email : "email001@gmail.com",emailTypeEnumValue:1},{email : "email002@gmail.com",emailTypeEnumValue:2},{email : "email003@gmail.com",emailTypeEnumValue:3}],
						telefones:[{ddd :"034",numero:"3315-8065",telefoneTypeEnumValue : 1},{ddd :"034",numero:"3315-8065",telefoneTypeEnumValue : 2}],
			        	cnaes:[{idCnae:{id:1}},{idCnae:{id:2}},{idCnae:{id:3}}],
			        	socios:[{cota:"1",porcentagem:"30",documentos : [{documentoTypeEnumValue : 1 , numero: "10101010"}]}],
			        	planoList: [{id:1}],
			        	usuarioList:[{
							login : "lala",
							senha :"123456789",
							pergunta:"Pergunta",
							role : "ADMIN",
							language :"pt",
							nome : "LAERCIO",
							modelAction : "INSERT",
							emails :[{email : "email001@gmail.com",emailTypeEnumValue:1},{email : "email002@gmail.com",emailTypeEnumValue:2},{email : "email003@gmail.com",emailTypeEnumValue:3}]
						}]}
		}

	}
</script>

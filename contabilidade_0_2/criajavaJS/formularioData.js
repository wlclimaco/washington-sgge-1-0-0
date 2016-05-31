//================ PROCESSO
function EmpresaFormModel() {

	var a = [];
	a.push({field :{campo : "id",label:"", ngmodel:'empresa.id', class:"hide", tipo :"Integer",requerid : false ,mask:"",tamanho : '12',msg:'',tipo :'input'}});
	a.push({field :{campo : "nome",label:"Razão Social", ngmodel:'empresa.nome', class:"", tipo :"String",requerid : true ,mask:"",tamanho : '9',msg:'',tipo :'input'}});
	a.push({field :{campo : "numeroFuncionario",label:"Numero Funcionarios", ngmodel:'empresa.numFunc', class:"", validator:['integer'], tipo :"Integer",requerid : true ,mask:"",tamanho : '3',msg:'',tipo :'input'}});
	a.push({field :{campo : "entidadeEnumValue",label:"Tipo Empresa", ngmodel:'empresa.entidadeEnumValue', class:"hide", tipo :"Integer",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	a.push({field :{campo : "regime",label:"Regime Tributario Empresa", ngmodel:'empresa.regime.id', class:"", tipo :"Integer",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'radio',domain :[{label : 'Simples Nacional',value : '1'},{label : 'Lucro Real',value:'2'},{label : 'Lucro Presumido',value:'3'}]}});
	a.push({field :{campo : "statusInicial",label:"Sua Empresa Esta Ativa?", ngmodel:'empresa.statusInicial', class:"", tipo :"Integer",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'radio',domain :[{label : 'Sim',value : 'sim'},{label : 'Não',value:'nao'}]}});

	return a;
}

function DocumentoFormModel() {

	var a = [];

	a.push({field :{campo : "id",label:"", ngmodel:'empresa.documento[0].id', class:"hide", tipo :"Integer",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	a.push({field :{campo : "documentoTypeEnumValue",class:"hide",label:"Documento", ngmodel:'empresa.documento[0].documentoTypeEnumValue', class:"", tipo :"Integer",requerid : false ,mask:"",tamanho : '9',msg:'',tipo :'input'}});
	a.push({field :{campo : "numero",label:"Numero do Documento", ngmodel:'empresa.documento[0].numero', class:"", tipo :"Integer",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	a.push({field :{campo : "data",label:"Data Expedição ", ngmodel:'empresa.documento[0].data', class:"hide", tipo :"Date",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'data'}});
	
	return a;

}

function EnderecoFormModel() {

	var a = [];
	a.push({field :{campo : "id",label:"", ngmodel:'empresa.endereco[0].id', class:"hide", tipo :"Integer",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	a.push({field :{campo : "logradouro",label:"Logradouro", ngmodel:'empresa.endereco[0].logradouro', class:"", tipo :"String",requerid : true ,mask:"",tamanho : '9',msg:'',tipo :'input'}});
	a.push({field :{campo : "numero",label:"Numero", ngmodel:'empresa.endereco[0].numero', class:"", tipo :"String",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	a.push({field :{campo : "bairro",label:"Bairro", ngmodel:'empresa.endereco[0].bairro', class:"", tipo :"String",requerid : false ,mask:"",tamanho : '3',msg:'',tipo :'input'}});
	a.push({field :{campo : "cidade",label:"Municipio", ngmodel:'empresa.endereco[0].cidade', class:"", tipo :"String",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	a.push({field :{campo : "enderecoTypeValue",label:"Tipo de Endereco", ngmodel:'empresa.endereco[0].enderecoTypeValue', class:"hide", tipo :"Integer",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	a.push({field :{campo : "cep",label:"Cep", ngmodel:'empresa.endereco[0].cep', class:"", tipo :"String",requerid : true ,mask:"99.999-999",tamanho : '6',msg:'',tipo :'select2',msg:'Favor Informar o CEP'}});
	a.push({field :{campo : "latitude",label:"Latitude", ngmodel:'empresa.endereco[0].latitude', class:"", tipo :"Double",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	a.push({field :{campo : "longitude",label:"Longitude", ngmodel:'empresa.endereco[0].longitude', class:"", tipo :"Double",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	a.push({field :{campo : "complemento",label:"Complemento", ngmodel:'empresa.endereco[0].complemento', class:"", tipo :"String",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	
	
	return a;

}

function EmailFormModel() {

	var a = [];
	a.push({field :{campo : "id",label:"", ngmodel:'empresa.email[0].id', class:"hide", tipo :"Integer",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	a.push({field :{campo : "typeValue",label:"Tipo Email", ngmodel:'empresa.email[0].typeValue', class:"hide", tipo :"Integer",requerid : false ,mask:"",tamanho : '9',msg:'',tipo :'input'}});
	a.push({field :{campo : "email",label:"Email", ngmodel:'empresa.email[0].email', class:"", tipo :"Integer",requerid : false ,mask:"",tamanho : '12',msg:'',tipo :'input'}});
	a.push({field :{campo : "emailTypeEnumValue",label:"Email Type", ngmodel:'empresa.email[0].emailTypeEnumValue', class:"hide", tipo :"Integer",requerid : false ,mask:"",tamanho : '12',msg:'',tipo :'input'}});
	
	return a;

}

function EstadoFormModel() {

	var a = [];
	a.push({field :{campo : "id",label:"", ngmodel:'empresa.id', class:"hide", tipo :"Integer",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	a.push({field :{campo : "nome",label:"Nome", ngmodel:'empresa.endereco[0].cidade.estado', class:"", tipo :"String",requerid : true ,mask:"",tamanho : '9',msg:'',tipo :'input'}});
	a.push({field :{campo : "abreviacao",label:"Estado", ngmodel:'empresa.endereco[0].cidade.estado.abreviacao', class:"", tipo :"String",requerid : false ,mask:"",tamanho : '3',msg:'',tipo :'input'}});

	return a;

}

function TelefoneFormModel() {

	var a = [];
	a.push({field :{campo : "id",label:"", ngmodel:'empresa.telefone[0].id', class:"hide", tipo :"Integer",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	a.push({field :{campo : "typeValue",label:"Tipo Telefone", ngmodel:'empresa.telefone[0].typeValue', class:"hide", tipo :"Integer",requerid : false ,mask:"",tamanho : '9',msg:'',tipo :'input'}});
	a.push({field :{campo : "ddd",label:"Numero Funcionarios", ngmodel:'empresa.telefone[0].ddd', class:"hide", tipo :"String",requerid : false ,mask:"",tamanho : '3',msg:'',tipo :'input'}});
	a.push({field :{campo : "numero",label:"Telefone", ngmodel:'empresa.telefone[0].numero', class:"(99) 9999-9999", tipo :"Integer",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	a.push({field :{campo : "telefoneTypeEnumValue",label:"Numero Funcionarios", ngmodel:'empresa.telefone[0].telefoneTypeEnumValue', class:"(99) 9999-9999", tipo :"Integer",requerid : false ,mask:"",tamanho : '3',msg:'',tipo :'input'}});

	return a;

}

function CnaeFormModel() {

	var a = [];
	a.push({field :{campo : "id",label:"", ngmodel:'empresa.id', class:"hide", tipo :"Integer",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	a.push({field :{campo : "codigo",label:"Codigo", ngmodel:'empresa.nome', class:"", tipo :"String",requerid : true ,mask:"",tamanho : '9',msg:'',tipo :'input'}});
	a.push({field :{campo : "cnae",label:"Atividade principal da Empresa", ngmodel:'empresa.numFunc', class:"", tipo :"String",requerid : false ,mask:"",tamanho : '3',msg:'',tipo :'input'}});
	a.push({field :{campo : "descricao",label:"Numero Funcionarios", ngmodel:'empresa.numFunc', class:"", tipo :"String",requerid : false ,mask:"",tamanho : '3',msg:'',tipo :'input'}});
	a.push({field :{campo : "abreviado",label:"Numero Funcionarios", ngmodel:'empresa.numFunc', class:"", tipo :"String",requerid : false ,mask:"",tamanho : '3',msg:'',tipo :'input'}});
	
	return a;

}

function EmpresaFormTest() {

	var a = [];
	a.push({
		formType : 'wizard',
		tabs :[{tabName : 'informaçoes usuario',field :{table : [
			EmpresaFormModel()[0],
			EmpresaFormModel()[1],
			EmpresaFormModel()[2],
			EmpresaFormModel()[4],
			EmpresaFormModel()[5],
			EnderecoFormModel()[0],
			EnderecoFormModel()[6],
			EnderecoFormModel()[1],
			EnderecoFormModel()[2],
			EnderecoFormModel()[3],
			EnderecoFormModel()[4],
			EnderecoFormModel()[9],
			CnaeFormModel()[0],
			CnaeFormModel()[1],


			]}}]

	});
	return a;
}
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

function PessoaFormModel() {

	var a = [];
	a.push({field :{campo : "id",label:"", ngmodel:'cliente.id', class:"hide", tipo :"Integer",requerid : false ,mask:"",tamanho : '12',msg:'',tipo :'input'}});
	a.push({field :{campo : "nome",label:"Nome ou Razão social", ngmodel:'empresa.nome', class:"", tipo :"String",requerid : true ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	a.push({field :{campo : "nomeFantasia",label:"Nome Fantasia", ngmodel:'empresa.numFunc', class:"", validator:['integer'], tipo :"Integer",requerid : true ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	a.push({field :{campo : "datanasc",label:"Data nascimento", ngmodel:'empresa.entidadeEnumValue', class:"", tipo :"String",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});

	return a;
}

function InquilinoFormModel() {

	var a = [];
	a.push({field :{campo : "dataEntrada",label:"Data Entrada Condominio", ngmodel:'empresa.entidadeEnumValue', class:"", tipo :"String",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	a.push({field :{campo : "proprietario",label:"Proprietario?", ngmodel:'empresa.statusInicial', class:"", tipo :"Integer",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'radio',domain :[{label : 'Sim',value : 'sim'},{label : 'Não',value:'nao'}]}});

	return a;
}

function SindicoFormModel() {

	var a = [];
	a.push({field :{campo : "dataInicio",label:"Data Inicio Mandado", ngmodel:'empresa.entidadeEnumValue', class:"", tipo :"String",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	a.push({field :{campo : "dataFim",label:"Data Final Mandado", ngmodel:'empresa.entidadeEnumValue', class:"", tipo :"String",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	return a;
}

function DocumentoFormModel(_labelDocument,_type) {

	var a = [];

	a.push({field :{campo : "id",label:"", ngmodel:'empresa.documento[0].id', class:"hide", tipo :"Integer",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	a.push({field :{campo : "documentoTypeEnumValue",class:"hide",label:"Documento", ngmodel:'empresa.documento[0].documentoTypeEnumValue', class:"hide", tipo :"Integer",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	a.push({field :{campo : "numero",label:""+_labelDocument+"", ngmodel:'empresa.documento[0].numero', class:"", tipo :"Integer",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
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
	a.push({field :{campo : "bloco",label:"Bloco", ngmodel:'empresa.endereco[0].bloco', class:"", tipo :"String",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	a.push({field :{campo : "andar",label:"Andar", ngmodel:'empresa.endereco[0].andar', class:"", tipo :"String",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	
	
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

function HoraFuncFormModel() {

	var a = [];
	a.push({field :{campo : "id",label:"", ngmodel:'empresa.id', class:"hide", tipo :"Integer",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	a.push({field :{campo : "data",label:"Data", ngmodel:'empresa.endereco[0].cidade.estado', class:"", tipo :"String",requerid : true ,mask:"",tamanho : '9',msg:'',tipo :'input'}});
	a.push({field :{campo : "horaInicio",label:"Hora Inicio", ngmodel:'empresa.endereco[0].cidade.estado', class:"", tipo :"String",requerid : true ,mask:"",tamanho : '9',msg:'',tipo :'input'}});
	a.push({field :{campo : "horaFim",label:"Hora Final", ngmodel:'empresa.endereco[0].cidade.estado', class:"", tipo :"String",requerid : true ,mask:"",tamanho : '9',msg:'',tipo :'input'}});

	return a;

}

function ConvenioFormModel() {

	var a = [];
	a.push({field :{campo : "id",label:"", ngmodel:'empresa.id', class:"hide", tipo :"Integer",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	a.push({field :{campo : "convenio",label:"Convenio", ngmodel:'empresa.endereco[0].cidade.estado', class:"", tipo :"String",requerid : true ,mask:"",tamanho : '9',msg:'',tipo :'input'}});

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

function TituloFormModel() {

	var a = [];
	a.push({field :{campo : "id",label:"", ngmodel:'empresa.id', class:"hide", tipo :"Integer",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	a.push({field :{campo : "descricao",label:"Descrição", ngmodel:'empresa.nome', class:"", tipo :"String",requerid : true ,mask:"",tamanho : '9',msg:'',tipo :'input'}});
	a.push({field :{campo : "numero",label:"Nro. do documento", ngmodel:'empresa.nome', class:"", tipo :"String",requerid : true ,mask:"",tamanho : '9',msg:'',tipo :'input'}});
	a.push({field :{campo : "parcela",label:"Nro. da parcela", ngmodel:'empresa.numFunc', class:"", tipo :"Integer",requerid : false ,mask:"",tamanho : '3',msg:'',tipo :'input'}});
	a.push({field :{campo : "dataEmissao",label:"Data do lançamento", ngmodel:'empresa.numFunc', class:"", tipo :"Data",requerid : true ,mask:"",tamanho : '3',msg:'',tipo :'input'}});
	a.push({field :{campo : "dataVencimento",label:"Data de vencimento", ngmodel:'empresa.numFunc', class:"", tipo :"Data",requerid : true ,mask:"",tamanho : '3',msg:'',tipo :'input'}});
	a.push({field :{campo : "docId",label:"Nro. Documento Referente", ngmodel:'empresa.numFunc', class:"", tipo :"String",requerid : false ,mask:"",tamanho : '3',msg:'',tipo :'input'}});
	a.push({field :{campo : "observacao",label:"Observações", ngmodel:'empresa.numFunc', class:"", tipo :"String",requerid : false ,mask:"",tamanho : '3',msg:'',tipo :'input'}});
	a.push({field :{campo : "valor",label:"Valor do pagamento", ngmodel:'empresa.numFunc', class:"", tipo :"Double",requerid : true ,mask:"",tamanho : '3',msg:'',tipo :'input'}});
	a.push({field :{campo : "dataPag",label:"Data do pagamento", ngmodel:'empresa.numFunc', class:"", tipo :"Data",requerid : false ,mask:"",tamanho : '3',msg:'',tipo :'input'}});
	
	return a;
}

function ProdutoFormModel() {

	var a = [];
	a.push({field :{campo : "id",label:"", ngmodel:'empresa.id', class:"hide", tipo :"Integer",requerid : false ,mask:"",tamanho : '6',msg:'',tipo :'input'}});
	a.push({field :{campo : "produto",label:"Produto", ngmodel:'empresa.nome', class:"", tipo :"String",requerid : true ,mask:"",tamanho : '9',msg:'',tipo :'input'}});
	a.push({field :{campo : "codigo",label:"Código personalizado", ngmodel:'empresa.nome', class:"", tipo :"String",requerid : true ,mask:"",tamanho : '9',msg:'',tipo :'input'}});
	a.push({field :{campo : "ncm",label:"Nro. da parcela", ngmodel:'empresa.numFunc', class:"", tipo :"Integer",requerid : false ,mask:"",tamanho : '3',msg:'',tipo :'input'}});
	a.push({field :{campo : "cdBarras",label:"Data do lançamento", ngmodel:'empresa.numFunc', class:"", tipo :"Data",requerid : true ,mask:"",tamanho : '3',msg:'',tipo :'input'}});
	a.push({field :{campo : "dataCreate",label:"Data de vencimento", ngmodel:'empresa.numFunc', class:"", tipo :"Data",requerid : true ,mask:"",tamanho : '3',msg:'',tipo :'input'}});
	a.push({field :{campo : "aplicacao",label:"Nro. Documento Referente", ngmodel:'empresa.numFunc', class:"", tipo :"String",requerid : false ,mask:"",tamanho : '3',msg:'',tipo :'input'}});
	a.push({field :{campo : "fracao",label:"Observações", ngmodel:'empresa.numFunc', class:"", tipo :"String",requerid : false ,mask:"",tamanho : '3',msg:'',tipo :'input'}});
	a.push({field :{campo : "classificacao",label:"Unidade tributada", ngmodel:'empresa.numFunc', class:"", tipo :"Double",requerid : true ,mask:"",tamanho : '3',msg:'',tipo :'input'}});
	a.push({field :{campo : "uniMed",label:"Data do pagamento", ngmodel:'empresa.numFunc', class:"", tipo :"Data",requerid : false ,mask:"",tamanho : '3',msg:'',tipo :'input'}});
	a.push({field :{campo : "grupo",label:"Data do pagamento", ngmodel:'empresa.numFunc', class:"", tipo :"Data",requerid : false ,mask:"",tamanho : '3',msg:'',tipo :'input'}});
	a.push({field :{campo : "subGrupo",label:"Data do pagamento", ngmodel:'empresa.numFunc', class:"", tipo :"Data",requerid : false ,mask:"",tamanho : '3',msg:'',tipo :'input'}});
	a.push({field :{campo : "porcao",label:"Data do pagamento", ngmodel:'empresa.numFunc', class:"", tipo :"Data",requerid : false ,mask:"",tamanho : '3',msg:'',tipo :'input'}});
	a.push({field :{campo : "pesoBruto",label:"Data do pagamento", ngmodel:'empresa.numFunc', class:"", tipo :"Data",requerid : false ,mask:"",tamanho : '3',msg:'',tipo :'input'}});
	a.push({field :{campo : "pesoLiquido",label:"Data do pagamento", ngmodel:'empresa.numFunc', class:"", tipo :"Data",requerid : false ,mask:"",tamanho : '3',msg:'',tipo :'input'}});
	a.push({field :{campo : "modoUso",label:"Data do pagamento", ngmodel:'empresa.numFunc', class:"", tipo :"Data",requerid : false ,mask:"",tamanho : '3',msg:'',tipo :'input'}});
	
	
	return a;
}


/*
//Produto
 // dados Gerais
 Nome
CadastrarCategoria
Código personalizado
Unidade tributada
UN
Margem de lucro
Preço de custo
Preço de venda
Inform. adicionais para a NFe
Referência EAN/GTIN
NCM (Encontre NCMs)
Exceção tab IPI
CEST
Anotações internas
Não controlar estoque
 Arquivar (esconder)
Estoque mínimo
Palavras-chave

//dados tributarios
PESOS
Peso unitário líquido (kg)
Peso unitário bruto (kg)
CFOP
CFOP padrão para NFe
ICMS
Situação Tributária
Origem
IPI
Situação tributária
Classe cigarros e bebidas
CNPJ Produtor
Cod. selo controle IPI
Qtd. selo IPI
Cód. enquadramento (buscar)
999
PIS
Situação tributária
Tipo de cálculo Subst. Trib.
	 
COFINS
Situação Tributária

Tipo de cálculo Subst. Trib.
*/

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

function PessoaFormTest() {

	var a = [];
	a.push({
		formType : 'Tab',
		tabs :[{tabName : 'informaçoes usuario',field :{table : [
			PessoaFormModel()[0],
			PessoaFormModel()[1],
			PessoaFormModel()[2],
			PessoaFormModel()[3],
			DocumentoFormModel()[0],
			DocumentoFormModel()[1],
			DocumentoFormModel('CPF ou CNPJ')[2],
			DocumentoFormModel()[0],
			DocumentoFormModel()[1],
			DocumentoFormModel('Inscr Est Subst Trib')[2],
			DocumentoFormModel()[0],
			DocumentoFormModel()[1],
			DocumentoFormModel('Indicador de IE')[2],
			DocumentoFormModel()[0],
			DocumentoFormModel()[1],
			DocumentoFormModel('Inscrição Estadual')[2],
			DocumentoFormModel()[0],
			DocumentoFormModel()[1],
			DocumentoFormModel('Inscrição Municipal')[2],
			DocumentoFormModel()[0],
			DocumentoFormModel()[1],
			DocumentoFormModel('Inscrição Suframa')[2],
			EnderecoFormModel()[0],
			EnderecoFormModel()[6],
			EnderecoFormModel()[1],
			EnderecoFormModel()[2],
			EnderecoFormModel()[3],
			EnderecoFormModel()[4],
			EnderecoFormModel()[9],



			]}}]

	});
	return a;
}

function InquilinoFormTest() {

	var a = [];
	a.push({
		formType : 'Tab',
		tabs :[{tabName : 'informaçoes usuario',field :{table : [
			PessoaFormModel()[0],
			PessoaFormModel()[1],
			PessoaFormModel()[2],
			PessoaFormModel()[3],
			DocumentoFormModel()[0],
			DocumentoFormModel()[1],
			DocumentoFormModel('CPF ou CNPJ')[2],
			InquilinoFormModel()[0],
			InquilinoFormModel()[1],
			EnderecoFormModel()[0],
			EnderecoFormModel()[6],
			EnderecoFormModel()[1],
			EnderecoFormModel()[2],
			EnderecoFormModel()[3],
			EnderecoFormModel()[4],
			EnderecoFormModel()[9],



			]}}]

	});
	return a;
}

function SindicoFormTest() {

	var a = [];
	a.push({
		formType : 'Tab',
		tabs :[{tabName : 'informaçoes usuario',field :{table : [
			PessoaFormModel()[0],
			PessoaFormModel()[1],
			PessoaFormModel()[2],
			PessoaFormModel()[3],
			DocumentoFormModel()[0],
			DocumentoFormModel()[1],
			DocumentoFormModel('CPF ou CNPJ')[2],
			SindicoFormModel()[0],
			SindicoFormModel()[1],
			EnderecoFormModel()[0],
			EnderecoFormModel()[6],
			EnderecoFormModel()[1],
			EnderecoFormModel()[2],
			EnderecoFormModel()[3],
			EnderecoFormModel()[4],
			EnderecoFormModel()[9],



			]}}]

	});
	return a;
}

function MedicoFormTest() {

	var a = [];
	a.push({
		formType : 'Tab',
		tabs :[{tabName : 'informaçoes usuario',field :{table : [
			PessoaFormModel()[0],
			PessoaFormModel()[1],
			PessoaFormModel()[2],
			PessoaFormModel()[3],
			DocumentoFormModel()[0],
			DocumentoFormModel()[1],
			DocumentoFormModel('CPF ou CNPJ')[2],
			DocumentoFormModel()[0],
			DocumentoFormModel()[1],
			DocumentoFormModel('Nº CRM')[2],
			HoraFuncFormModel()[0],
			HoraFuncFormModel()[1],
			HoraFuncFormModel()[2],
			HoraFuncFormModel()[3],
			EnderecoFormModel()[0],
			EnderecoFormModel()[6],
			EnderecoFormModel()[1],
			EnderecoFormModel()[2],
			EnderecoFormModel()[3],
			EnderecoFormModel()[4],
			EnderecoFormModel()[9],



			]}}]

	});
	return a;
}

function PacienteFormTest() {

	var a = [];
	a.push({
		formType : 'Tab',
		tabs :[{tabName : 'informaçoes usuario',field :{table : [
			PessoaFormModel()[0],
			PessoaFormModel()[1],
			PessoaFormModel()[2],
			PessoaFormModel()[3],
			DocumentoFormModel()[0],
			DocumentoFormModel()[1],
			DocumentoFormModel('CPF ou CNPJ')[2],
			DocumentoFormModel()[0],
			DocumentoFormModel()[1],
			DocumentoFormModel('Nº Identidade')[2],
			ConvenioFormModel()[0],
			EnderecoFormModel()[0],
			EnderecoFormModel()[6],
			EnderecoFormModel()[1],
			EnderecoFormModel()[2],
			EnderecoFormModel()[3],
			EnderecoFormModel()[4],
			EnderecoFormModel()[9],



			]}}]

	});
	return a;
}

function AdvogadoFormTest() {

	var a = [];
	a.push({
		formType : 'Tab',
		tabs :[{tabName : 'informaçoes usuario',field :{table : [
			PessoaFormModel()[0],
			PessoaFormModel()[1],
			PessoaFormModel()[2],
			PessoaFormModel()[3],
			DocumentoFormModel()[0],
			DocumentoFormModel()[1],
			DocumentoFormModel('CPF ou CNPJ')[2],
			DocumentoFormModel()[0],
			DocumentoFormModel()[1],
			DocumentoFormModel('Nº OAB')[2],
			HoraFuncFormModel()[0],
			HoraFuncFormModel()[1],
			HoraFuncFormModel()[2],
			HoraFuncFormModel()[3],
			EnderecoFormModel()[0],
			EnderecoFormModel()[6],
			EnderecoFormModel()[1],
			EnderecoFormModel()[2],
			EnderecoFormModel()[3],
			EnderecoFormModel()[4],
			EnderecoFormModel()[9],



			]}}]

	});
	return a;
}

function TituloPagarFormTest() {

	var a = [];
	a.push({
		formType : 'Tab',
		tabs :[{tabName : 'informaçoes usuario',field :{table : [
			TituloFormModel()[0],
			TituloFormModel()[1],
			TituloFormModel()[2],
			TituloFormModel()[3],
			TituloFormModel()[4],
			TituloFormModel()[5],
			TituloFormModel()[6],
			TituloFormModel()[7],
			TituloFormModel()[8],
			TituloFormModel()[9]



			]}}]

	});
	return a;
}



/*


//Produto
 // dados Gerais
 Nome
CadastrarCategoria
Código personalizado
Unidade tributada
UN
Margem de lucro
Preço de custo
Preço de venda
Inform. adicionais para a NFe
Referência EAN/GTIN
NCM (Encontre NCMs)
Exceção tab IPI
CEST
Anotações internas
Não controlar estoque
 Arquivar (esconder)
Estoque mínimo
Palavras-chave

//dados tributarios
PESOS
Peso unitário líquido (kg)
Peso unitário bruto (kg)
CFOP
CFOP padrão para NFe
ICMS
Situação Tributária
Origem
IPI
Situação tributária
Classe cigarros e bebidas
CNPJ Produtor
Cod. selo controle IPI
Qtd. selo IPI
Cód. enquadramento (buscar)
999
PIS
Situação tributária
Tipo de cálculo Subst. Trib.
	 
COFINS
Situação Tributária

Tipo de cálculo Subst. Trib.

*/


/*
//==================
Dados que geralmente não mudam Editar

CNPJ:
Razão Social:
joaquim jose gestao	
Nome fantasia:
Inscr. Est.:
Inscr. Mun.:
CNAE:
Telefone:
349997741	
Endereço:
,	
Bairro:
Cidade/UF:
null/MG	
Local fato gerador:
null	
CEP:
CRT:
undefined	
Modelo nota:
55	
Série nota:
001
Ambiente envio:
Testes		
Dados gerais
Número da NFe (mudar)

{automático}
Natureza da operação
	
 Destinado a consumidor final
Tipo de operação
	
Finalidade da emissão
	
Destino da operação
	
 Possui NF referenciada
Data emissão NF

07/06/2016
Hora emissão NF (atual)

15:18:00
Data saída/entrada

07/06/2016
Hora saída/entrada (atual)

15:18:00
Forma de pagamento
	
Presença do comprador
	
Ins. Est. Subst. Trib.

Dados do destinatário
Listar 50Razão/Nome dest.

CNPJ/CPF destinatário

Telefone

E-mail

Inscr. Suframa

Logradouro

Número

Complemento

Bairro

CEP

Estado
	
Município
	
Indicador de IE
	
Inscr. Estadual

Inscr. Municipal

 Endereço de entrega diferente
Lista de produtos Inserir item
 Não efetuar cálculos automático
 Somar valor do IPI na Base de Cálculo do ICMS
 Não informar tributos da Lei da Transparência
CÓD	NOME DO ITEM	CFOP	QUANT	$ UNIT.	TOTAL PROD	
Nenhum produto preenchido
Informações de serviços
 Informar dados de serviços
Totalizadores (estes valores não podem ser alterados manualmente)
Total BC ICMS

Total ICMS

Total BC ICMS ST

Total ICMS ST

Total bruto dos produtos

Total ICMS desonerado

Total de frete

Total de seguro

Total de desconto

Total de IPI

Total de PIS

Total de PIS ST

Total de COFINS

Total de COFINS ST

Total de II

Total do FCP

ICMS UF remet.

ICMS UF destino

Total Outras Despesas

TOTAL DA NF

 Informar retenções de impostos
Lei da Transparência
Esta lei mostra ao cliente a quantidade de impostos que ele está pagando para o governo ao adquirir a mercadoria. (saiba mais)
Impostos federais

Impostos estaduais

Impostos municipais

Total aproximado

Dados do transporte
Modalidade do frete
	
Listar 50Nome transportador

CNPJ/CPF transport.

Inscr. Est. transp.

Endereço completo

UF transportador
	
Município transp.

Placa veículo

UF veículo
	
Reg. Nac. Trans. Carga

Identificação vagão

Identificação balsa

 Informar retenções de ICMS de transporte
Volumes transportados
Quantidade de vol.

Espécie dos vol.

Marca dos volumes

Numeração dos vol.

Peso líquido

Peso bruto

Números dos lacres

Faturas e duplicatas Editar duplicatas

NÚM	VENC	VALOR	NÚM	VENC	VALOR	NÚM	VENC	VALOR
Nenhuma duplicata preenchida
Número da fatura

Valor original fat.

Valor desconto

Valor líquido fat.

Outras informações
Informações complementares

Informações para o Fisco

Informações de compras
 Informar dados de compras

//==================================*/
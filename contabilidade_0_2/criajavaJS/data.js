//================ PROCESSO
function ProcessModel() {
	a = [];

	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({ field :{campo : "dataProcess", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "parentId", tipo :"Integer",requerid : true ,primaryKey:false,forenkey : false,model:false,xml:true}});
	a.push({field :{campo : "valor", tipo :"Double",requerid : true ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "advogadoList", tipo :"List<Advogado>",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "clienteList", tipo :"List<Cliente>",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "audienciaList", tipo :"List<Audiencia>",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});

	return a;
}

//================ Entidade
function EntidadeModel() {
	a = [];

	a.push({field :{campo : "id" , tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "nome", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "processId", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "entidadeId", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "emprId" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "entidadeEnumValue", tipo :"EntidadeEnum",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "regime"   , tipo:"Regime",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
   	a.push({field :{campo : "documentos" ,tipo:"List<Documento>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "enderecos", tipo:"List<Endereco>" ,requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "emails", tipo:"List<Email>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "telefones", tipo:"List<Telefone>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "cnaes" ,tipo:"List<CnaeEmpresa>" ,requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "statusList", tipo:"List<Status>" ,requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "notes" ,tipo:"List<Note>" ,requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}
//================ AUDITORIA
function AuditoriaModel() {
	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "dataAudiencia", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "valor", tipo :"Double",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "descricao", tipo :"String",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "noteLIst", tipo :"List<Note>",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});

return a;
}

//================ Advogado
function PessoaModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "nome", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "nomePai", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "nomeMae", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "nomeConjugue", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "estadoCivil", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "tipoPessoa", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "datanasc", tipo :"Long",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "foto", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "pessoaTypeEnum", tipo :"PessoaTypeEnum",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "sexo", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "enderecos", tipo :"List<Endereco>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "documentos", tipo :"List<Documento>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "emails", tipo :"List<Email>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "telefones", tipo :"List<Telefone>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "bancos", tipo :"List<BancoPessoa>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "formaPagamentoList", tipo :"List<FormaPgPessoa>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "condPagList", tipo :"List<CondPagPessoa>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "contatoList", tipo :"List<Contato>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});

return a;
}

//================ Convenio
function ConvenioModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Convenio
function ClasseModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "nome", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Convenio
function InterfaceModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "nome", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "local", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Convenio
function ForenKeyModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "tipo", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "requerid", tipo :"Boolean",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "primaryKey", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "forenkey", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "nome", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Convenio
function FieldModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "tamanho", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "tipo", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "requerid", tipo :"Boolean",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "primaryKey", tipo :"Boolean",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "forenkey", tipo :"Boolean",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "model", tipo :"Boolean",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "xml", tipo :"Boolean",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}
//================ Telefone
function TelefoneModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "typeValue", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "parentId", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
   	a.push({field :{campo : "tabelaEnumValue", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "ddd", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "numero", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "telefoneTypeEnumValue", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "processId", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}
function ConhecimentoTransporteModel() {

	var a = [];
	a.push({field :{campo : "id" , tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "IdNota" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
   	a.push({field :{campo : "remetente" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "vrTotalMercadorias" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "apCreIcms" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "fretePorConta" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "placa" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "especie" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "volume" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "pesoLiquido" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "pesoBruto", tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "transportador", tipo :"Transportador",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "marca", tipo :"Marca",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "estado" , tipo :"Estado",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});

	return a;
}
//================ Cidade
function CidadeModel() {

	var a = [];

	a.push({field :{campo : "id" , tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "codigo", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "nome" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
   	a.push({field :{campo : "cdIBGE" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
   	a.push({field :{campo : "cep" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
   	a.push({field :{campo : "municipio" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "estado"   , tipo :"Estado",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});

	return a;
}

//================ Cidade
function NotaFiscalModel() {

	var a = [];

	a.push({field :{campo : "id" , tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "serie" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "ordem" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "numero" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "tipo" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "nfValor" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "dataEmissao" , tipo :"Long",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "dataSaida" , tipo :"Long",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "dataEntrada" , tipo :"Long",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "modelo" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "bxEstoque" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "descItens" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "pcCusto" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "cfop"  , tipo :"Cfop",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "conhecimentoTransporte"    , tipo :"ConhecimentoTransporte",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "empresa"    , tipo :"Empresa",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "pessoa"    , tipo :"Pessoa",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "tributosList" , tipo :"List<Tributos>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "formaPagList" , tipo :"List<FormaPag>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "notaFiscalItens" , tipo :"List<NotaFiscalItens>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "noteList" , tipo :"List<Note>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "contasList", tipo :"List<ContasPagar>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "itensEspeciais" , tipo :"List<ItensEspeciais>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "nfStatusList", tipo :"List<NFStatus>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}
//================ Cidade
function NotaFiscalModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "IdNota", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
   	a.push({field :{campo : "qnt" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "vrUnitario" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "vrDesconto" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "produto"  , tipo :"Produto",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "cfop"    , tipo :"Cfop",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "classificacao"   , tipo :"Classificacao",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "tributosList" , tipo :"List<Tributos>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});

return a;
}
//================ Estado
function EstadoModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "nome", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
   	a.push({field :{campo : "abreviacao", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Tarefa
function TarefaModel() {

	var a = [];
	a.push({field :{campo : "id" , tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "nome" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "descricao", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Empresa
function EmpresaModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Consulta
function ConsultaModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "dataConsulta" , tipo :"Long",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "valor" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "dataMarcacao" , tipo :"Long",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "medico"    , tipo :"Medico",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "paciente" , tipo :"Paciente",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "planoSaude"  , tipo :"PlanoSaude",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "exameList" , tipo :"List<Exame>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ NotaFiscal
function NotaFiscalModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}


//================ PedidoCompras
function PedidoComprasModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Cotacao
function CotacaoModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Exame
function ExameModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Avisos
function AvisosModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}


//================ HoraFunc
function HoraFuncModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Beneficios
function BeneficiosModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Eventos
function EventosModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Usuario
function UsuarioModel() {

	var a = [];
		a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "emprId", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "processId" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "email", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "senha" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "pergunta" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "role" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "language" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "ultAcesso", tipo :"Long",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "tabelaEnumValue" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ ContasPagar
function ContasPagarModel() {

	var a = [];
	a = TituloModel();
	return a;
}


//================ ContasReceber
function ContasReceberModel() {

	var a = [];
	a = TituloModel();
	return a;
}


//================ CondPag
function CondPagModel() {

	var a = [];
		a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "nome" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "valorIni" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "valorFin" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});;
		a.push({field :{campo : "parcelas" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "listTipoPag" , tipo :"List<TipoPag>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ CondPagPessoa
function CondPagPessoaModel() {

	var a = [];
		a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "condPagId" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "parentId" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}


//================ FormaPg
function FormaPgModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "descricao" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "diasPg" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "entrada" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ FormaPgModelPessoa
function FormaPgModelPessoa() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "formaPgId" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "parentId" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}


//================ Banco
function BancoModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "nome", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}


//================ BancoPessoa
function BancoPessoaModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "numCont", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "saldo", tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "bancoId", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});

	return a;
}

//================ BancoPessoa
function TipoPagModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "descricao", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "nome", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});

	return a;
}

//================ ContaCorrente
function ContaCorrenteModel() {

	var a = [];

	a.push({field :{campo : "id" , tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "agencia" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "saldo" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "numeroConta", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "nossoNumero", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "historicoList", tipo :"List<HistoricoMovimento>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}


//================ Caixa
function CaixaModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "saldo", tipo :"Double",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "baixaTituloList", tipo :"List<BaixaTitulo>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Regime
function RegimeModel() {

	var a = [];
	a.push({field :{campo : "id" , tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "nome" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "descricao" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Cnae
function CnaeModel() {

	var a = [];
	a.push({field :{campo : "id" , tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "codigo" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
   	a.push({field :{campo : "cnae" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "descricao" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "abreviado", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Cnae	Empresa
function CnaeEmpresaModel() {

	var a = [];

	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "parentId" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "tabelaEnumValue" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "processId" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "idCnae" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});

	return a;
}

//===========================================================================================NFE=======================================================================================================

function NFBaseModel() {

	var a = [];

		a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}

function NFNotaModel() {

	private long identificadorLocal;

    @Element(name = "infNFe")
    private NFNotaInfo info;
    
    @Element(name = "infNFeSupl", required = false)
    private NFNotaInfoSuplementar infoSuplementar;

    @Element(name = "Signature", required = false)
    private NFSignature assinatura;
	var a = [];

		a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}

function NFNotaInfoModel() {

	var a = [];

		a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
		public static final String IDENT = "NFe";

	@Attribute(name = "Id", required = true)
	private String identificador;

	@Attribute(name = "versao", required = true)
	private String versao;

	@Element(name = "ide", required = true)
	private NFNotaInfoIdentificacao identificacao;

	@Element(name = "emit", required = true)
	private NFNotaInfoEmitente emitente;

	@Element(name = "avulsa", required = false)
	private NFNotaInfoAvulsa avulsa;

	@Element(name = "dest", required = false)
	private NFNotaInfoDestinatario destinatario;

	@Element(name = "retirada", required = false)
	private NFNotaInfoLocal retirada;

	@Element(name = "entrega", required = false)
	private NFNotaInfoLocal entrega;

	@ElementList(entry = "autXML", inline = true, required = false)
	private List<NFPessoaAutorizadaDownloadNFe> pessoasAutorizadasDownloadNFe;

	@ElementList(entry = "det", inline = true, required = true)
	private List<NFNotaInfoItem> itens;

	@Element(name = "total", required = true)
	private NFNotaInfoTotal total;

	@Element(name = "transp", required = true)
	private NFNotaInfoTransporte transporte;

	@Element(name = "cobr", required = false)
	private NFNotaInfoCobranca cobranca;

	@ElementList(entry = "pag", inline = true, required = false)
	private List<NFNotaInfoPagamento> pagamentos;

	@Element(name = "infAdic", required = false)
	private NFNotaInfoInformacoesAdicionais informacoesAdicionais;

	@Element(name = "exporta", required = false)
	private NFNotaInfoExportacao exportacao;

	@Element(name = "compra", required = false)
	private NFNotaInfoCompra compra;

	@Element(name = "cana", required = false)
	private NFNotaInfoCana cana;
	return a;
}
function NFNotaInfoIdentificacaoModel() {

	var a = [];

@Element(name = "cUF", required = true)
    private NFUnidadeFederativa uf;

    @Element(name = "cNF", required = true)
    private String codigoRandomico;

    @Element(name = "natOp", required = true)
    private String naturezaOperacao;

    @Element(name = "indPag", required = true)
    private NFFormaPagamentoPrazo formaPagamento;

    @Element(name = "mod", required = true)
    private NFModelo modelo;

    @Element(name = "serie", required = true)
    private String serie;

    @Element(name = "nNF", required = true)
    private String numeroNota;

    @Element(name = "dhEmi", required = true)
    private DateTime dataHoraEmissao;

    @Element(name = "dhSaiEnt", required = false)
    private DateTime dataHoraSaidaOuEntrada;

    @Element(name = "tpNF", required = true)
    private NFTipo tipo;

    @Element(name = "idDest", required = true)
    private NFIdentificadorLocalDestinoOperacao identificadorLocalDestinoOperacao;

    @Element(name = "cMunFG", required = true)
    private String codigoMunicipio;

    @Element(name = "tpImp", required = true)
    private NFTipoImpressao tipoImpressao;

    @Element(name = "tpEmis", required = true)
    private NFTipoEmissao tipoEmissao;

    @Element(name = "cDV", required = true)
    private Integer digitoVerificador;

    @Element(name = "tpAmb", required = true)
    private NFAmbiente ambiente;

    @Element(name = "finNFe", required = true)
    private NFFinalidade finalidade;

    @Element(name = "indFinal", required = true)
    private NFOperacaoConsumidorFinal operacaoConsumidorFinal;

    @Element(name = "indPres", required = true)
    private NFIndicadorPresencaComprador indicadorPresencaComprador;

    @Element(name = "procEmi", required = true)
    private NFProcessoEmissor programaEmissor;

    @Element(name = "verProc", required = true)
    private String versaoEmissor;

    @Element(name = "dhCont", required = false)
    private DateTime dataHoraContigencia;

    @Element(name = "xJust", required = false)
    private String justificativaEntradaContingencia;

    @ElementList(entry = "NFref", inline = true, required = false)
    private List<NFInfoReferenciada> referenciadas;

 }

function NFNotaInfoSuplementarModel() {

	var a = [];

		public class NFNotaInfoEmitente extends NFBase {

    @Element(name = "CNPJ", required = false)
    private String cnpj;

    @Element(name = "CPF", required = false)
    private String cpf;

    @Element(name = "xNome", required = true)
    private String razaoSocial;

    @Element(name = "xFant", required = false)
    private String nomeFantasia;

    @Element(name = "enderEmit", required = true)
    private NFEndereco endereco;

    @Element(name = "IE", required = true)
    private String inscricaoEstadual;

    @Element(name = "IEST", required = false)
    private String inscricaoEstadualSubstituicaoTributaria;

    @Element(name = "IM", required = false)
    private String inscricaoMunicipal;

    @Element(name = "CNAE", required = false)
    private String classificacaoNacionalAtividadesEconomicas;

    @Element(name = "CRT", required = true)
    private NFRegimeTributario regimeTributario;
	return a;
}

function NFNotaInfoSuplementarModel() {

	var a = [];

		@Element(name = "CNPJ", required = true)
    private String cnpj;

    @Element(name = "xOrgao", required = true)
    private String orgaoEmitente;

    @Element(name = "matr", required = true)
    private String matriculaAgente;

    @Element(name = "xAgente", required = true)
    private String nomeAgente;

    @Element(name = "fone", required = false)
    private String fone;

    @Element(name = "UF", required = true)
    private String uf;

    @Element(name = "nDAR", required = false)
    private String numeroDocumentoArrecadacaoReceita;

    @Element(name = "dEmi", required = false)
    private LocalDate dataEmissaoDocumentoArrecadacao;

    @Element(name = "vDAR", required = false)
    private String valorTotalConstanteDocumentoArrecadacaoReceita;

    @Element(name = "repEmi", required = true)
    private String reparticaoFiscalEmitente;

    @Element(name = "dPag", required = false)
    private LocalDate dataPagamentoDocumentoArrecadacao;
	return a;
}

function NFNotaInfoSuplementarModel() {

	public class NFNotaInfoDestinatario extends NFBase {

	@Element(name = "CNPJ", required = false)
	private String cnpj;

	@Element(name = "CPF", required = false)
	private String cpf;

	@Element(name = "idEstrangeiro", required = false)
	private String idEstrangeiro;

	@Element(name = "xNome", required = false)
	private String razaoSocial;

	@Element(name = "enderDest", required = false)
	private NFEndereco endereco;

	@Element(name = "indIEDest", required = true)
	private NFIndicadorIEDestinatario indicadorIEDestinatario;

	@Element(name = "IE", required = false)
	private String inscricaoEstadual;

	@Element(name = "ISUF", required = false)
	private String inscricaoSuframa;

	@Element(name = "IM", required = false)
	private String inscricaoMunicipal;

	@Element(name = "email", required = false)
	private String email;

	public String getCnpj() {
		return this.cnpj;
	}
	return a;
}


function NFNotaInfoSuplementarModel() {

	var a = [];

		@Element(name = "CNPJ", required = false)
    private String cnpj;

    @Element(name = "CPF", required = false)
    private String cpf;

    @Element(name = "xLgr", required = true)
    private String logradouro;

    @Element(name = "nro", required = true)
    private String numero;

    @Element(name = "xCpl", required = false)
    private String complemento;

    @Element(name = "xBairro", required = true)
    private String bairro;

    @Element(name = "cMun", required = true)
    private String codigoMunicipio;

    @Element(name = "xMun", required = true)
    private String nomeMunicipio;

    @Element(name = "UF", required = true)
    private String uf;
	return a;
}


function NFNotaInfoSuplementarModel() {

	var a = [];

		ublic class NFPessoaAutorizadaDownloadNFe extends NFBase {
    @Element(name = "CNPJ", required = false)
    private String cnpj;

    @Element(name = "CPF", required = false)
    private String cpf;
	return a;
}


function NFNotaInfoItemModel() {

	var a = [];

		@Attribute(name = "nItem", required = true)
    private Integer numeroItem;

    @Element(name = "prod", required = true)
    private NFNotaInfoItemProduto produto;

    @Element(name = "imposto", required = true)
    private NFNotaInfoItemImposto imposto;

    @Element(name = "impostoDevol", required = false)
    private NFImpostoDevolvido impostoDevolvido;

    @Element(name = "infAdProd", required = false)
    private String informacoesAdicionais;

	return a;
}


function NFNotaInfoSuplementarModel() {

	var a = [];

		NFNotaInfoTotal extends NFBase {

    @Element(name = "ICMSTot", required = true)
    private NFNotaInfoICMSTotal icmsTotal;

    @Element(name = "ISSQNtot", required = false)
    private NFNotaInfoISSQNTotal issqnTotal;

    @Element(name = "retTrib", required = false)
    private NFNotaInfoRetencoesTributos retencoesTributos;
	return a;
}

function NFNotaInfoSuplementarModel() {

	var a = [];

		NFNotaInfoTransporte extends NFBase {
    @Element(name = "modFrete", required = true)
    private NFModalidadeFrete modalidadeFrete;

    @Element(name = "transporta", required = false)
    private NFNotaInfoTransportador transportador;

    @Element(name = "retTransp", required = false)
    private NFNotaInfoRetencaoICMSTransporte icmsTransporte;

    @Element(name = "veicTransp", required = false)
    private NFNotaInfoVeiculo veiculo;

    @ElementList(entry = "reboque", inline = true, required = false)
    private List<NFNotaInfoReboque> reboques;

    @Element(name = "vagao", required = false)
    private String vagao;

    @Element(name = "balsa", required = false)
    private String balsa;

    @ElementList(entry = "vol", inline = true, required = false)
    private List<NFNotaInfoVolume> volumes;

	return a;
}


function NFBaseModel() {

	var a = [];

		NFNotaInfoCobranca extends NFBase {
    @Element(name = "fat", required = false)
    private NFNotaInfoFatura fatura;

    @ElementList(entry = "dup", inline = true, required = false)
    private List<NFNotaInfoDuplicata> duplicatas;
	return a;
}


function NFBaseModel() {

	var a = [];

		NFNotaInfoPagamento extends NFBase {

    @Element(name = "tPag", required = true)
    private NFFormaPagamentoMoeda formaPagamentoMoeda;

    @Element(name = "vPag", required = true)
    private String valorPagamento;

    @Element(name = "card", required = false)
    private NFNotaInfoCartao cartao;
	return a;
}


function NFBaseModel() {

	var a = [];

		NFNotaInfoInformacoesAdicionais extends NFBase {
    @Element(name = "infAdFisco", required = false)
    private String informacoesAdicionaisInteresseFisco;

    @Element(name = "infCpl", required = false)
    private String informacoesComplementaresInteresseContribuinte;

    @ElementList(entry = "obsCont", inline = true, required = false)
    private List<NFNotaInfoObservacao> observacoesContribuinte;

    @ElementList(entry = "obsFisco", inline = true, required = false)
    private List<NFNotaInfoObservacao> observacoesFisco;

    @ElementList(entry = "procRef", inline = true, required = false)
    private List<NFNotaInfoProcessoReferenciado> processosRefenciado;
	return a;
}


function NFBaseModel() {

	var a = [];

		a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}


function NFBaseModel() {

	var a = [];

		a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}


function NFBaseModel() {

	var a = [];

		a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}


function NFBaseModel() {

	var a = [];

		a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}



//======================================================================================================================================================================================================

//================ Produto
//================================================================================================================================================================================= Produto =====================================================
function ProdutoParentIdModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "parentId", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "emprId", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "tributacao" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});	
	a.push({field :{campo : "estoqueList" , tipo :"List<Estoque>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "precoList" , tipo :"List<Preco>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "custoList" , tipo :"List<Custo>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "porcaoList" , tipo :"List<Porcao>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "rentabilidadeList" , tipo :"List<Rentabilidade>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "cfopList" , tipo :"List<CfopPessoa>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "dataValidade" , tipo :"Long",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "localizacao" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});	
	a.push({field :{campo : "comissao" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

function ProdutoModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "codigo" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "cdBarras" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "produto" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "dataCreate" , tipo :"Long",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "aplicacao" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});	
	a.push({field :{campo : "fracao" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "classificacao" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "uniMed" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "grupo" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "subGrupo" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "marca" , tipo :"List<MarcaProd>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "porcao" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "pesoBruto" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "pesoLiquido" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "modoUso" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	

	return a;
}

//================ Cfop
function CfopModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "cfop" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "natureza" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "simplificado" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "cfopTypeEnum" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "icms" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "icmsReduzido" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "margemAgregadaST" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "cstPrincipal" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "classFiscal" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "observacao" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Cfop
function CfopParentIdModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "cfop" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "parentId" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Marca
function MarcaModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "marca", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "fabricante", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "emailList", tipo :"List<Email>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "enderecoList", tipo :"List<Endereco>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "telefoneList", tipo :"List<Telefone>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});

	return a;
}

//================ Marca
function MarcaProdutoModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "parentId", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "marcaId", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Grupo
function GrupoModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "grupo", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "descricao", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "subGrupo", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Grupo
function SubGrupoModel() {

	var a = [];
		a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "subGrupo", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "descricao", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Grupo
function UniMedModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "unimed", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "sigla", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Orcamento
function TributacaoModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "parentId"  , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "cstId"  , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "icms" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "st", tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "mva" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "csosnId" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "ipi" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "iat" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "ippt" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "incidencia" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Orcamento
function CustoModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "valor" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "custo" , tipo :"List<CustoItem>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "parentId"  , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	
	return a;
}

//================ OrcamentoItens
function CustoItensModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "parentId"  , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "custo" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "custoDesp" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	
	return a;
}

//================ Orcamento
function EstoqueModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "parentId"  , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "estoqueTypeEnum" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "ultimoMov" , tipo :"Long",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "quant" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	
	return a;
}

//================ Orcamento
function PorcaoModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "parentId"  , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "valor" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "porcaoItens" , tipo :"List<PorcaoItem>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});

	return a;
}

//================ Orcamento
function PorcaoItensModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "parentId"  , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "porcao" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "vd" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "unimed" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "nome", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});

	return a;
}

//================ Orcamento
function RentabilidadeModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "parentId"  , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "rentabilidadeList"  , tipo :"List<RentabilidadeItens>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});

	return a;
}

//================ Orcamento
function RentabilidadeItensModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "parentId"  , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "produto"  , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "valor"  , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "rentabilidadeTypeEnum"  , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});

	return a;
}

//=================================================================================================================================================================================

//================ Site
function SiteModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "nome", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "url", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "quemSomos", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "missao", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "visao", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "titulo", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "logo", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "atorization", tipo :"Boolean",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "siteTypeEnum", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "servicoList", tipo :"List<Servico>",List : ServicoModel() ,requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "planoList", tipo :"List<PlanoBySite>",List :PlanoBySiteModel(),requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});

	return a;
}
//================ Servico
function ServicoModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "nome", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "descricao", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "preco", tipo :"List<Preco>",List : PrecoModel(),requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Plano
function PlanoBySiteModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "parentId" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "plano", tipo :"Plano",List : "Plano",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ PlanoByServico
function PlanoByServicoModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "parentId" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "servico" , tipo :"Servico",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}



//================ Plano
function PlanoModel() {

	var a = [];

		a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "dataInicio", tipo :"Long",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "dataFinal", tipo :"Long",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "numeroContrato", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "descricao", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "titulo", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "precoList", tipo :"List<Preco>",List : "Preco" ,requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "servicoList", tipo :"List<PlanoByServico>",List :"PlanoByServico",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Orcamento
function OrcamentoModel() {

	var a = [];
	a = NotaFiscalModel();
	return a;
}

//================ Orcamento
function EnderecoModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "parentId", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "tabelaEnumValue", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "processId", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "logradouro" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "bairro", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "numero", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "enderecoTypeValue", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "cep", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "complemento", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "cidade", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}
//================ Orcamento
function EmailModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "tabelaEnumValue", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
   	a.push({field :{campo : "parentId", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "typeValue" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "email", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "emailTypeEnumValue", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Orcamento
function TituloModel() {

	var a = [];
		a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "numero", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "dataEmissao", tipo :"Long",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "dataVencimento", tipo :"Long",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "docId", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "valor", tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "observacao", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "financeiroEnumValue", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "parentId", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "listBaixa", tipo :"List<BaixaTitulo>",List : "BaixaTitulo",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Orcamento
function TipoBaixaModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "nome", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "descricao", tipo :"List<BaixaTitulo>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ OrcamentoList
function BaixaTituloModel() {

	var a = [];
		a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "parentId", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "finanId", Integer :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "dataBaixa", tipo :"Long",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "observacao", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "Valor", tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "juros", tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "multa", tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "desconto", tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "tipoBaixaList", tipo :"List<TipoBaixa> ",List :"BaixaTitulo",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});

	return a;
}

//================ Orcamento
function ArquivoModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}
//================ Orcamento
function ClassificacaoModel() {

	var a = [];
	a.push({field :{campo : "id" , tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "descricao", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
   	a.push({field :{campo : "codigo", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});

	return a;
}
//================ Orcamento
function DocumentosModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "tabelaEnumValue", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "parentId", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "processId", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "documentoTypeEnumValue", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "numero" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "data", tipo :"Long",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}
//================ Orcamento
function HistoricoModel() {

	var a = [];
	a.push({field :{campo : "id" , tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "data", tipo :"Long",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "userId", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "emprId", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "tabelaEnumValue", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "acaoEnumValue", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "processId", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});

	return a;
}

//================ HistoricoUtil
function HistoricoUtilModel() {

	var a = [];
	a.push({field :{campo : "id" , tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "dataMovimento", tipo :"Long",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "HistoricoUtilType", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "quantidade", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "parentId", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "tabelaEnumValue", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});

	return a;
}

//================ Historico Itens
function HistoricoItensModel() {

	var a = [];
	a.push({field :{campo : "id" , tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "processId", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "acaoEnumValue", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
   	a.push({field :{campo : "tabelaEnumValue", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
   	a.push({field :{campo : "parentId" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Orcamento
function NoteModel() {

	var a = [];

	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "parentId", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "noteText", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "tabelaEnumValue", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});

	return a;
}

//================ Orcamento
function SocioModel() {

	var a = [];
	a.push({field :{campo : "id" , tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "parentId", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
   	a.push({field :{campo : "nome", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "cota", tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "porcentagem" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Ordem Servico Status
function OrdemServicoStatusModel() {

	var a = [];

	a.push({field :{campo : "id" , tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "parentId", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
   	a.push({field :{campo : "nome", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "cota", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "porcentagem", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});

	return a;
}

//================ Ordem Servico Status
function OrdemServicoTypeModel() {
	var a = [];
	 a.push({field :{campo : "id" , tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
   /* a.push({field :{campo : "typeServico" column="type" />
    a.push({field :{campo : "createUser" column="create_user"/>
	a.push({field :{campo : "createDataUTC" column="create_date"/>
	a.push({field :{campo : "modifyUser" column="modify_user"/>
	a.push({field :{campo : "modifyDataUTC" column="modify_date"/>
	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});*/
	return a;
}

//================ Ordem Servico Status
function OrdemServicoModel() {
	var a = [];
	a.push({field :{campo : "id" , tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
 	a.push({field :{campo : "emprId" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "userId" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
   	a.push({field :{campo : "nome" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "data" , tipo :"Long",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "assunto" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "statusValue" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "OrdemServicoItensList", tipo :"List<OrdemServicoItens>",List : OrdemServicoItensModel(),requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});


	return a;
}

//================ Ordem Servico Itens
function OrdemServicoItensModel() {

	var a = [];
	a.push({field :{campo : "id" 		, tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "data" 		, tipo :"Long",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
   	a.push({field :{campo : "texto" 	, tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "parentId"  , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});


	return a;
}

//================ Orcamento
function StatusModel() {

	var a = [];
	a.push({field :{campo : "id" , tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "dataStatus" , tipo :"Long",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "parentId" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "statusValue" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "acaoEnumValue" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "tabelaEnumValue" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "note" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});

	return a;
}



//================ Orcamento
function AgenciaModel() {

	var a = [];
		a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "nome" 	, tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "gerente" 	, tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "responsavelConta" 	, tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "numeroAgencia" 	, tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "parentId" 	, tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "enderecos" 	, tipo :"List<Endereco>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "emails" 	, tipo :"List<Email>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "telefones" 	, tipo :"List<Telefone>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
		a.push({field :{campo : "contaList" 	, tipo :"List<Conta>",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Orcamento
function ProfissaoModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Orcamento
function SalarioModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}



//================ Orcamento
function PrecoModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "dataMarcacao" , tipo :"Long",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
   	a.push({field :{campo : "precoTypeEnum" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "tabelaEnumValue" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
   	a.push({field :{campo : "valor" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
   	a.push({field :{campo : "dataProInicial" , tipo :"Long",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
   	a.push({field :{campo : "dataProFinal" , tipo :"Long",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
   	a.push({field :{campo : "parentId" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
   	a.push({field :{campo : "emprId" , tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});

	return a;
}




//================ Orcamento
function ContatoModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "parentId", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "dataContato", tipo :"Long",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "nome", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "motivoValue", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "contatoItensList", tipo :"List<ContatoItens>",list : ContatoItensModel(),requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});

	return a;
}

//================ Orcamento
function ContatoItensModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "dataAlt", tipo :"Long",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "texto", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "titulo", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "contatoStatus", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "visto", tipo :"Boolean",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Orcamento
function CondominioModel() {

	var a = [];
	a = EntidadeModel();
	return a;
}

//================ Orcamento
function DepositoModel() {

	var a = [];
	a = EntidadeModel();
	return a;
}

//================ Orcamento
function MarcaProdModel() {

	var a = [];
	a.push({field :{campo : "depositoList", tipo :"Deposito",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "marcaId", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}


//================ Orcamento
function FilialModel() {

	var a = [];
	a = EntidadeModel();
	a.push({field :{campo : "depositoList", tipo :"Deposito",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}
function AdvocaciaModel() {

	var a = [];
	a = EntidadeModel();
	return a;
}

function ClinicaModel() {

	var a = [];
	a = EntidadeModel();
	return a;
}

function CondominioModel() {

	var a = [];
	a = EntidadeModel();
	return a;
}
function dependenciaBase(){
	var a = []

	a.push({dependencia :"Endereco" 	,campos : EnderecoModel()});
	a.push({dependencia :"Email",		 campos : EmailModel()});
	a.push({dependencia :"Documentos"	,campos : DocumentosModel()});
	a.push({dependencia :"Telefone"		,campos : TelefoneModel()});
	a.push({dependencia :"Status"		,campos : StatusModel()});
	a.push({dependencia :"Note"			,campos : NoteModel()});

	return a
}

function dependenciaEntidade(){
	var a = [];
	a = dependenciaBase();
	a.push({dependencia :"Entidade",campos : EntidadeModel()});
	a.push({dependencia :"Regime",campos : RegimeModel()});
	a.push({dependencia :"Cnae",campos : EnderecoModel()});
	a.push({dependencia :"CnaeEmpresa",campos : EnderecoModel()});
	return a
}

function dependenciaEmpresa(){
	var a = [];
	a = dependenciaEntidade();
	a.push({dependencia :"Socios",campos : EnderecoModel()});
	a.push({dependencia :"Plano",campos :  PlanoModel()});
	a.push({dependencia :"Tarefa",campos : TarefaModel()});
	a.push({dependencia :"Filial",campos : EnderecoModel()});
	return a
}

function dependenciaAdvocacia(){
	var a = [];
	a = dependenciaEntidade();
	a.push({dependencia :"Socios",campos : EnderecoModel()});
	a.push({dependencia :"Plano",campos :  PlanoModel()});
	a.push({dependencia :"Tarefa",campos : TarefaModel()});
	a.push({dependencia :"Filial",campos : EnderecoModel()});
	return a
}

function dependenciaClinica(){
	var a = [];
	a = dependenciaEntidade();
	a.push({dependencia :"Socios",campos : EnderecoModel()});
	a.push({dependencia :"Plano",campos :  PlanoModel()});
	a.push({dependencia :"Tarefa",campos : TarefaModel()});
	a.push({dependencia :"Filial",campos : EnderecoModel()});
	return a
}

function dependenciaCondominio(){
	var a = [];
	a = dependenciaEntidade();
	a.push({dependencia :"Socios",campos : EnderecoModel()});
	a.push({dependencia :"Plano",campos :  PlanoModel()});
	a.push({dependencia :"Tarefa",campos : TarefaModel()});
	a.push({dependencia :"Filial",campos : EnderecoModel()});
	return a
}

function dependenciaUsuario(){
	var a = [];
	a.push({dependencia :"Empresa",campos : EnderecoModel()});
	return a

}

function dependenciaFilial(){
	var a = [];
	a = dependenciaEntidade();
	a.push({dependencia :"Deposito",campos : EnderecoModel()});
	return a
}

function dependenciaDeposito(){
	var a = [];
	a = dependenciaEntidade();
	return a
}

function dependenciaCondominio(){
	var a = [];
	a = dependenciaEntidade();
	return a
}

function dependenciaClinica(){
	var a = [];
	a = dependenciaEntidade();
	return a
}

function dependenciaPessoa(){
	var a = [];
	a = dependenciaBase();
	return a
}

function dependenciaAdvogado(){
	var a = [];
	a = dependenciaBase();
	return a
}

function dependenciaCliente(){
	var a = [];
	a = dependenciaBase();
	return a
}

function dependenciaFornecedor(){
	var a = [];
	a = dependenciaBase();
	return a
}

function dependenciaTransportador(){
	var a = [];
	a = dependenciaBase();
	return a
}

function dependenciaMedico(){
	var a = [];
	a = dependenciaBase();
	return a
}

function dependenciaPaciente(){
	var a = [];
	a = dependenciaBase();
	return a
}

function dependenciaSindico(){
	var a = [];
	a = dependenciaBase();
	return a
}

function dependenciaInquilino(){
	var a = [];
	a = dependenciaBase();
	return a
}

function dependenciaFuncionario(){
	var a = [];
	a = dependenciaBase();
	return a
}

function dependenciaServico(){
	var a = [];
	a.push({dependencia :"Preco",campos : PrecoModel()});
	return a
}

function dependenciaSite(){
	var a = [];
	a.push({dependencia :"Servico",campos : ServicoModel()});
	a.push({dependencia :"Plano",campos : PlanoModel()});
	a.push({dependencia :"Status"		,campos : StatusModel()});
	a.push({dependencia :"Note"			,campos : NoteModel()});
	return a
}

function dependenciaContato(){
	var a = [];
	a.push({dependencia :"Contato",campos : ContatoItensModel()});
	a.push({dependencia :"Email",		 campos : EmailModel()});
	a.push({dependencia :"Telefone"		,campos : TelefoneModel()});
	return a
}

function dependenciaPlano(){
	var a = [];
	a.push({dependencia :"Preco",campos : PrecoModel()});
	a.push({dependencia :"Servico",campos : ServicoModel()});
	return a
}
function dependenciaOrdemServico(){
	var a = [];
	a.push({dependencia :"OrdemServicoItens",campos : OrdemServicoItensModel()});
	return a
}

function dependenciaTitulo(){
	var a = [];
	a.push({dependencia :"BaixaTitulo",campos : BaixaTituloModel()});
	a.push({dependencia :"TipoBaixa" ,campos : TipoBaixaModel()});
	return a
}

function dependenciaContasPagar(){
	var a = [];
	a = dependenciaTitulo();
	return a
}

function dependenciaContasReceber(){
	var a = [];
	a = dependenciaTitulo();
	return a
}

function EmpresaModel() {

	var a = [];
	a = EntidadeModel();
/*	a.push({field :{campo : "socios" column="id" select="SocioMap.fetchSocioByEmpresaId"/>
	a.push({field :{campo : "planoList" column="id" select="PlanoMap.fetchPlanoByEmpresa"/>
	a.push({field :{campo : "filialList" column="id" select="FilialMap.fetchAllFilialByEntidade"/>
	a.push({field :{campo : "depositoList" column="id" select="DepositoMap.fetchAllDepositoByEntidade"/>*/
	return a;
}

function dependenciaProdutoParent(){
	var a = [];
	a.push({dependencia :"Estoque",campos : EstoqueModel()});
	a.push({dependencia :"Preco",campos : PrecoModel()});
	a.push({dependencia :"Custo",campos : CustoModel()});
	a.push({dependencia :"Porcao",campos : PorcaoModel()});
	a.push({dependencia :"Rentabilidade",campos : RentabilidadeModel()});
	a.push({dependencia :"CfopParentId",campos : CfopParentIdModel()});
	a.push({dependencia :"Produto",campos : ProdutoModel()});
	return a
}

function dependenciaProduto(){
	var a = [];
	a.push({dependencia :"MarcaProd",campos : MarcaProdModel()});
	return a
}

function dependenciaMarcaProd(){
	var a = [];
	a.push({dependencia :"Marca",campos : MarcaModel()});
	return a
}


function dependenciaMarca(){
	var a = [];
	a.push({dependencia :"Email",campos : EmailModel()});
	a.push({dependencia :"Endereco",campos : EnderecoModel()});
	a.push({dependencia :"Telefone",campos : TelefoneModel()});
	return a
}

function dependenciaCusto(){
	var a = [];
	a.push({dependencia :"CustoItens",campos : CustoItensModel()});
	return a
}

function dependenciaPorcao(){
	var a = [];
	a.push({dependencia :"PorcaoItens",campos : PorcaoItensModel()});
	return a
}

function dependenciaRentabilidade(){
	var a = [];
	a.push({dependencia :"RentabilidadeItens",campos : RentabilidadeItensModel()});
	return a
}






dataModel = function (){

	var oProjet = [];

oProjet.push({
	classes :[{classe : "advogado",model : PessoaModel()},{classe : "Cliente",model : PessoaModel(),dependencias : dependenciaCliente()},{classe :"Fornecedor",model : PessoaModel(),dependencias : dependenciaFornecedor()},{classe :"Transportador",model : PessoaModel(),dependencias : dependenciaTransportador()},{classe : "Medico",model : PessoaModel(),dependencias : dependenciaMedico()},{classe :"Paciente",model : PessoaModel(),dependencias : dependenciaPaciente()},{classe : "Sindico",model : PessoaModel(),dependencias : dependenciaSindico()},{classe :"Inquilino",model : PessoaModel(),dependencias : dependenciaInquilino()},{classe : "Funcionario",model : PessoaModel(),dependencias : dependenciaFuncionario()}],
	interfaces : "Pessoa",
	local : "Pessoa"
})

oProjet.push({
	classes :[{classe : "audiencia",model : AuditoriaModel()},{classe :"Processo" ,model :ProcessModel()}],
	interfaces : "Advogado",
	local : "Advocacia"
})

oProjet.push({
	classes :[{classe :"Convenio",model : ConvenioModel()},{classe :"Cidade",model : CidadeModel()},{classe :"Estado",model : EstadoModel()},{classe :"Tarefa",model : TarefaModel()}],
	interfaces : "Cadastros",
	local : "Cadastros"
})

oProjet.push({
	classes :[{classe :"Consulta",model : ConsultaModel()},{classe :"Exame",model : ExameModel()}],
	interfaces : "Clinica",
	local : "Clinica"
})

oProjet.push({
	classes :[{classe : "NotaFiscalEntrada",model : NotaFiscalModel()},{classe :"PedidoCompras",model : PedidoComprasModel()},{classe :"Cotacao",model : CotacaoModel()}],
	interfaces : "Compras",
	local : "Compras"
})

oProjet.push({
	classes :[{classe :"Avisos",model : AvisosModel()}],
	interfaces : "Condominio",
	local : "Condominio"
})

oProjet.push({
	classes :[{classe :"Eventos",model : EventosModel()},{classe :"Beneficios",model : BeneficiosModel()},{classe :"HoraFunc",model : HoraFuncModel()}],
	interfaces : "Dp",
	local :"Dp"
})

oProjet.push({
	classes :[{classe : "Empresa",model : EmpresaModel(),dependencias : dependenciaEmpresa()},{classe :"Filial",model : EmpresaModel(),dependencias : dependenciaFilial()},{classe :"Deposito",model : EmpresaModel(),dependencias : dependenciaDeposito()},{classe :"Usuario",model : UsuarioModel(),dependencias : dependenciaUsuario()},{classe :"Advocacia",model : AdvocaciaModel(),dependencias : dependenciaAdvocacia()},{classe :"Clinica",model : ClinicaModel(),dependencias : dependenciaClinica()},{classe :"Condominio",model : CondominioModel(),dependencias : dependenciaCondominio()}],
	interfaces : "Empresa",
	local : "Empresa"
})

oProjet.push({
	classes :[{classe : "ContasPagar",model : ContasPagarModel(),dependencias : dependenciaContasPagar()},{classe : "Titulo",model : TituloModel(),dependencias : dependenciaTitulo()},{classe : "BaixaTitulo",model : BaixaTituloModel()},{classe : "TipoBaixa",model : TipoBaixaModel()},{classe :"ContasReceber",model : ContasReceberModel(),dependencias : dependenciaContasReceber()},{classe :"CondPag",model : CondPagModel()},{classe :"FormaPg",model : FormaPgModel()},{classe :"Banco",model : BancoModel()},{classe :"ContaCorrente",model : ContaCorrenteModel()},{classe :"Caixa",model : CaixaModel()}],
	interfaces : "Financeiro",
	local :"Financeiro"
})

oProjet.push({
	classes :[{classe : "Regime",model : RegimeModel()},{classe :"Cfop",model : CfopModel()},{classe :"Cnae",model : CnaeModel()},{classe :"CnaeEmpresa",model : CnaeEmpresaModel()}],
	interfaces : "Fiscal",
	local : "Fiscal"
})

oProjet.push({
	classes :[{classe : "ProdutoParent",model : ProdutoParentIdModel(),dependencias : dependenciaProdutoParent()},{classe : "Produto",model : ProdutoModel(),dependencias : dependenciaProduto()},{classe : "Cfop",model : CfopModel()},{classe : "Marca",model : MarcaModel(),dependencias : dependenciaMarca()},{classe : "MarcaProduto",model : MarcaProdutoModel()},{classe :"Grupo",model : GrupoModel()},{classe :"SubGrupo",model : SubGrupoModel()},{classe :"UniMed",model : UniMedModel()},{classe :"Tributacao",model : TributacaoModel()},{classe :"Custo",model : CustoModel(),dependencias : dependenciaCusto()},{classe :"CustoItens",model : CustoItensModel()},{classe :"Estoque",model : EstoqueModel()},{classe :"Porcao",model : PorcaoModel(),dependencias : dependenciaPorcao()},{classe :"PorcaoItens",model : PorcaoItensModel()},{classe :"Rentabilidade",model : RentabilidadeModel(),dependencias : dependenciaRentabilidade()},{classe :"RentabilidadeItens",model : RentabilidadeItensModel()}],
	interfaces : "Produto",
	local : "Produto"
})

oProjet.push({
	classes :[{classe : "Servico",model : ServicoModel(),dependencias : dependenciaServico()},{classe : "ServicoByPlano",model : PlanoByServicoModel(),dependencias : dependenciaServico()},{classe : "Site",model : SiteModel(),dependencias : dependenciaSite()},{classe :"Contato",model : ContatoModel(),dependencias : dependenciaContato()},{classe :"ContatoItens",model : ContatoItensModel()},{classe :"OrdemServico",model : OrdemServicoModel(),dependencias : dependenciaOrdemServico()},{classe :"OrdemServicoItens",model : OrdemServicoItensModel()},{classe :"Plano",model : PlanoModel()}],
	interfaces : "Site",
	local : "Site"
})

oProjet.push({
	classes :[{classe : "NotaFiscalSaida",model : NotaFiscalModel()},{classe :"Orcamento",model : OrcamentoModel()},{classe :"OrdemServico",model : OrdemServicoModel()}],
	interfaces : "Vendas",
	local : "Vendas"
})
//============== DACD
oProjet.push({
	classes :[{classe : "Endereco",model : EnderecoModel()}],
	interfaces : "Endereco",
	local : "Endereco"
})

oProjet.push({
	classes :[{classe : "Email",model : EmailModel()}],
	interfaces : "Email",
	local : "Email"
})

oProjet.push({
	classes :[{classe : "Telefone",model : TelefoneModel()}],
	interfaces : "Telefone",
	local : "Telefone"
})

oProjet.push({
	classes :[{classe : "Arquivo",model : ArquivoModel()}],
	interfaces : "Arquivo",
	local : "Arquivo"
})

oProjet.push({
	classes :[{classe : "Classificacao",model : ClassificacaoModel()}],
	interfaces : "Classificacao",
	local : "Classificacao"
})

oProjet.push({
	classes :[{classe : "Documento",model : DocumentosModel()}],
	interfaces : "Documento",
	local : "Documento"
})

oProjet.push({
	classes :[{classe : "HistoricoUtil",model : HistoricoUtilModel()},{classe : "Historico",model : HistoricoModel()},{classe : "HistoricoItens",model : HistoricoItensModel()}],
	interfaces : "Historico",
	local : "Historico"
})

oProjet.push({
	classes :[{classe : "Notes",model : NoteModel()}],
	interfaces : "Notes",
	local : "Notes"
})

oProjet.push({
	classes :[{classe : "Socio",model : SocioModel()}],
	interfaces : "Socios",
	local : "Socios"
})

oProjet.push({
	classes :[{classe : "OrdemServico",model : OrdemServicoModel()},{classe : "OrdemServicoType",model : OrdemServicoTypeModel()},{classe : "OrdemServicoStatus",model : OrdemServicoStatusModel()},{classe : "OrdemServicoItens",model : OrdemServicoItensModel()}],
	interfaces : "OrdemServico",
	local : "OrdemServico"
})

oProjet.push({
	classes :[{classe : "Status",model : StatusModel()}],
	interfaces : "Status",
	local : "Status"
})

oProjet.push({
	classes :[{classe : "Tributacao",model : TributacaoModel()}],
	interfaces : "Tributacao",
	local : "Tributacao"
})

oProjet.push({
	classes :[{classe : "Agencia",model : AgenciaModel()}],
	interfaces : "Agencia",
	local : "Agencia"
})

oProjet.push({
	classes :[{classe : "Profissao",model : ProfissaoModel()}],
	interfaces : "Profissao",
	local : "Profissao"
})

oProjet.push({
	classes :[{classe : "Salario",model : SalarioModel()}],
	interfaces : "Salario",
	local : "Salario"
})

oProjet.push({
	classes :[{classe : "Custo",model : CustoModel()}],
	interfaces : "Custo",
	local : "Custo"
})

oProjet.push({
	classes :[{classe : "Estoque",model : EstoqueModel()}],
	interfaces : "Estoque",
	local : "Estoque"
})

oProjet.push({
	classes :[{classe : "Porcao",model : PorcaoModel()}],
	interfaces : "Porcao",
	local : "Porcao"
})

oProjet.push({
	classes :[{classe : "Preco",model : PrecoModel()}],
	interfaces : "Preco",
	local : "Preco"
})

oProjet.push({
	classes :[{classe : "Rentabilidade",model : RentabilidadeModel()}],
	interfaces : "Rentabilidade",
	local : "Rentabilidade"
})

oProjet.push({
	classes :[{classe : "Servico",model : ServicoModel()}],
	interfaces : "Servico",
	local : "Servico"
})

oProjet.push({
	classes :[{classe : "Classes",model : ClasseModel()},{classe : "Interface",model : InterfaceModel()},{classe : "Field",model : FieldModel()}],
	interfaces : "Dicionario",
	local : "Dicionario"
})

return oProjet;
}




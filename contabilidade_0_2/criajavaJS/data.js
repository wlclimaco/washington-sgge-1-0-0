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
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}


//================ ContasReceber
function ContasReceberModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}


//================ CondPag
function CondPagModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}


//================ FormaPg
function FormaPgModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}


//================ Banco
function BancoModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}


//================ ContaCorrente
function ContaCorrenteModel() {

	var a = [];

	a.push({field :{campo : "id" , tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "nome" , tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	a.push({field :{campo : "saldo" , tipo :"Double",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "descricao", tipo :"String",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
	return a;
}


//================ Caixa
function CaixaModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
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

//================ Produto
function ProdutoModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Marca
function MarcaModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Grupo
function GrupoModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Grupo
function SubGrupoModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Grupo
function UniMedModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Site
function SiteModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}


//================ Contato
function ContatoModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}


//================ OrdemServico
function OrdemServicoModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
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
    a.push({field :{campo : "emprId", tipo :"Integer",requerid : false ,primaryKey:false,forenkey : false,model:true,xml:true}});
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
	a.push({field :{campo : "id" , tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
 /*   a.push({field :{campo : "emprId" column="emprId" />
    a.push({field :{campo : "userId" column="userId" />
   	a.push({field :{campo : "nome" column="nome"/>
	a.push({field :{campo : "data" column="data"/>
	a.push({field :{campo : "assunto" column="assunto"/>
	a.push({field :{campo : "createUser" column="create_user"/>
	a.push({field :{campo : "createDataUTC" column="create_date"/>
	a.push({field :{campo : "modifyUser" column="modify_user"/>
	a.push({field :{campo : "modifyDataUTC" column="modify_date"/>
	a.push({field :{campo : "typeId"    column="typeId" select="fetchTypeByOrdem"  />
	a.push({field :{campo : "ordemStatusList" column="id" select="OrdemServicoItensMap.fetchAllOrdemServicoItensByOrdemServico"/>
	var a = [];*/
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Ordem Servico Itens
function OrdemServicoItensModel() {
	/* a.push({field :{campo : "id" , tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
    a.push({field :{campo : "parentId" column="parentId" />
   	a.push({field :{campo : "nome" column="nome"/>
	a.push({field :{campo : "cota" column="cota"/>
	a.push({field :{campo : "porcentagem" column="porcentagem" />
	a.push({field :{campo : "createUser" column="create_user"/>
	a.push({field :{campo : "createDataUTC" column="create_date"/>
	a.push({field :{campo : "modifyUser" column="modify_user"/>
	a.push({field :{campo : "modifyDataUTC" column="modify_date"/>
	var a = [];*/
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
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
function AgenciaModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
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
function CustoModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Orcamento
function EstoqueModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Orcamento
function PorcaoModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Orcamento
function PrecoModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Orcamento
function RentabilidadeModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
	return a;
}

//================ Orcamento
function ServicoModel() {

	var a = [];
	a.push({field :{campo : "id", tipo :"Integer",requerid : true ,primaryKey:true,forenkey : false,model:true,xml:true}});
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

function EmpresaModel() {

	var a = [];
	a = EntidadeModel();
/*	a.push({field :{campo : "socios" column="id" select="SocioMap.fetchSocioByEmpresaId"/>
	a.push({field :{campo : "planoList" column="id" select="PlanoMap.fetchPlanoByEmpresa"/>
	a.push({field :{campo : "filialList" column="id" select="FilialMap.fetchAllFilialByEntidade"/>
	a.push({field :{campo : "depositoList" column="id" select="DepositoMap.fetchAllDepositoByEntidade"/>*/
	return a;
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
	classes :[{classe : "ContasPagar",model : ContasPagarModel()},{classe :"ContasReceber",model : ContasReceberModel()},{classe :"CondPag",model : CondPagModel()},{classe :"FormaPg",model : FormaPgModel()},{classe :"Banco",model : BancoModel()},{classe :"ContaCorrente",model : ContaCorrenteModel()},{classe :"Caixa",model : CaixaModel()}],
	interfaces : "Financeiro",
	local :"Financeiro"
})

oProjet.push({
	classes :[{classe : "Regime",model : RegimeModel()},{classe :"Cfop",model : CfopModel()},{classe :"Cnae",model : CnaeModel()},{classe :"CnaeEmpresa",model : CnaeEmpresaModel()}],
	interfaces : "Fiscal",
	local : "Fiscal"
})

oProjet.push({
	classes :[{classe : "Produto",model : ProdutoModel()},{classe :"Marca",model : MarcaModel()},{classe :"Grupo",model : GrupoModel()},{classe :"SubGrupo",model : SubGrupoModel()},{classe :"UniMed",model : UniMedModel()}],
	interfaces : "Produto",
	local : "Produto"
})

oProjet.push({
	classes :[{classe : "Site",model : SiteModel()},{classe :"Contato",model : ContatoModel()},{classe :"OrdemServico",model : OrdemServicoModel()},{classe :"Plano",model : PlanoModel()}],
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
	classes :[{classe : "Historico",model : HistoricoModel()},{classe : "HistoricoItens",model : HistoricoItensModel()}],
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




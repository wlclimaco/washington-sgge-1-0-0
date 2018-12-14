// ====================================================== Dois Valores ===============================================
function DVProcesso() {
    var a = [];
    a.push({
        field: {
            id: "103",
            name: "Processo",
            doisValorType: [{
                    id: "142",
                    tipo: "STATUS",
                    descricao: "STATUS",
                    doisValor: [
                        { value: "4", nome: "Arquivado", descricao: "Arquivado" },
                        { value: "1", nome: "Ativo", descricao: "Ativo" },
                        { value: "5", nome: "Em grau de recurso", descricao: "Em grau de recurso" },
                        { value: "3", nome: "Encerrado", descricao: "Encerrado" },
                        { value: "2", nome: "Suspenso", descricao: "Suspenso" }
                    ]
                },
                {
                    id: "143",
                    tipo: "TIPO ENVOLVIMENTO",
                    descricao: "TIPO ENVOLVIMENTO",
                    doisValor: [
                        { value: "PARTE_ATIVA", nome: "Parte Ativa", descricao: "Parte Ativa" },
                        { value: "PARTE_PASSIVA", nome: "Parte Passiva", descricao: "Parte Passiva" },
                        { value: "TERCEIRO", nome: "Terceiro", descricao: "Terceiro" },
                        { value: "ASSISTENTE", nome: "Assistente", descricao: "Assistente" },
                        { value: "FISCAL", nome: "Fiscal", descricao: "Fiscal" },
                        { value: "TESTEMUNHA", nome: "Testemunha", descricao: "Testemunha" },
                        { value: "VITIMA", nome: "Vítima", descricao: "Vítima" },
                        { value: "OUTROS", nome: "Outros", descricao: "Outros" },
                    ]
                },
                {
                    id: "144",
                    tipo: "ENVOLVIMENTO",
                    descricao: "ENVOLVIMENTO",
                    doisValor: [
                        { value: '63', nome: 'Acusador', descricao: 'Acusador' },
                        { value: '2', nome: 'Agravante', descricao: 'Agravante' },
                        { value: '3', nome: 'Apelante', descricao: 'Apelante' },
                        { value: '1', nome: 'Autor', descricao: 'Autor' },
                        { value: '4', nome: 'Embargante', descricao: 'Embargante' },
                        { value: '5', nome: 'Excipiente', descricao: 'Excipiente' },
                        { value: '6', nome: 'Exequente', descricao: 'Exequente' },
                        { value: '7', nome: 'Impetrante', descricao: 'Impetrante' },
                        { value: '8', nome: 'Impugnante', descricao: 'Impugnante' },
                        { value: '9', nome: 'Interpelante', descricao: 'Interpelante' },
                        { value: '10', nome: 'Inventariante', descricao: 'Inventariante' },
                        { value: '11', nome: 'Notificante', descricao: 'Notificante' },
                        { value: '12', nome: 'Opoente', descricao: 'Opoente' },
                        { value: '13', nome: 'Reclamante', descricao: 'Reclamante' },
                        { value: '14', nome: 'Reconvinte', descricao: 'Reconvinte' },
                        { value: '15', nome: 'Recorrente', descricao: 'Recorrente' },
                        { value: '61', nome: 'Requerente', descricao: 'Requerente' },
                        { value: '16', nome: 'Sujeito Ativo', descricao: 'Sujeito Ativo' }
                    ]
                },
                {
                    id: "145",
                    tipo: "SITUACAO",
                    descricao: "SITUACAO",
                    doisValor: [
                        { value: '2', nome: 'Conselho Nacional de Justiça', descricao: 'Conselho Nacional de Justiça' },
                        { value: '8', nome: 'Justiça dos Estados e do Distrito Federal e Territórios', descricao: 'Justiça dos Estados e do Distrito Federal e Territórios' },
                        { value: '5', nome: 'Justiça do Trabalho', descricao: 'Justiça do Trabalho' },
                        { value: '6', nome: 'Justiça Eleitoral', descricao: 'Justiça Eleitoral' },
                        { value: '4', nome: 'Justiça Federal', descricao: 'Justiça Federal' },
                        { value: '7', nome: 'Justiça Militar da União', descricao: 'Justiça Militar da União' },
                        { value: '9', nome: 'Justiça Militar Estadual', descricao: 'Justiça Militar Estadual' },
                        { value: '3', nome: 'Superior Tribunal de Justiça', descricao: 'Superior Tribunal de Justiça' },
                        { value: '1', nome: 'Supremo Tribunal Federal', descricao: 'Supremo Tribunal Federal' }
                    ]
                },
                {
                    id: "146",
                    tipo: "INSTANCIA",
                    descricao: "INSTANCIA",
                    doisValor: [
                        { value: 'PRIMEIRA_INSTANCIA', nome: '1ª Instância', descricao: '1ª Instância' },
                        { value: 'SEGUNDA_INSTANCIA', nome: '2ª Instância', descricao: '2ª Instância' },
                        { value: 'TERCEIRA_INSTANCIA', nome: '3ª Instância', descricao: '3ª Instância' },
                        { value: 'QUARTA_INSTANCIA', nome: '4ª Instância', descricao: '4ª Instância' }
                    ]
                },
                {
                    id: "147",
                    tipo: "JUSTICA",
                    descricao: "JUSTICA",
                    doisValor: [
                        { value: '1', nome: 'Supremo Tribunal Federal', descricao: 'Supremo Tribunal Federal' },
                        { value: '3', nome: 'Superior Tribunal de Justiça', descricao: 'Superior Tribunal de Justiça' },
                        { value: '4', nome: 'Justiça Federal', descricao: 'Justiça Federal' },
                        { value: '5', nome: 'Justiça do Trabalho', descricao: 'Justiça do Trabalho' },
                        { value: '8', nome: 'Justica dos Estados e do Distrito Federal e Territórios', descricao: 'Justica dos Estados e do Distrito Federal e Territórios' }
                    ]
                },
                {
                    id: "148",
                    tipo: "TRIBUNAL",
                    descricao: "TRIBUNAL",
                    doisValor: [
                        { value: '1', nome: 'Tribunal de Justiça do Acre', descricao: 'Tribunal de Justiça do Acre' },
                        { value: '7', nome: 'Tribunal de Justiça de Alagoas', descricao: 'Tribunal de Justiça de Alagoas' },
                        { value: '8', nome: 'Tribunal de Justiça do Amazonas', descricao: 'Tribunal de Justiça do Amazonas' },
                        { value: '11', nome: 'Tribunal de Justiça da Bahia', descricao: 'Tribunal de Justiça da Bahia' },
                        { value: '16', nome: 'Tribunal de Justiça do Ceará', descricao: 'Tribunal de Justiça do Ceará' },
                        { value: '53', nome: 'Tribunal de Justiça do Distrito Federal e dos Territórios - DF', descricao: 'Tribunal de Justiça do Distrito Federal e dos Territórios - DF' },
                        { value: '19', nome: 'Tribunal de Justiça do Espírito Santo', descricao: 'Tribunal de Justiça do Espírito Santo' },
                        { value: '20', nome: 'Tribunal de Justiça de Goiás', descricao: 'Tribunal de Justiça de Goiás' },
                        { value: '22', nome: 'Tribunal de Justiça do Maranhão', descricao: 'Tribunal de Justiça do Maranhão' },
                        { value: '29', nome: 'Tribunal de Justiça de Mato Grosso', descricao: 'Tribunal de Justiça de Mato Grosso' },
                        { value: '26', nome: 'Tribunal de Justiça de Mato Grosso do Sul', descricao: 'Tribunal de Justiça de Mato Grosso do Sul' },
                        { value: '24', nome: 'Tribunal de Justiça de Minas Gerais', descricao: 'Tribunal de Justiça de Minas Gerais' },
                        { value: '34', nome: 'Tribunal de Justiça do Paraná', descricao: 'Tribunal de Justiça do Paraná' },
                        { value: '51', nome: 'Tribunal de Justiça do Pará - PA', descricao: 'Tribunal de Justiça do Pará - PA' },
                        { value: '33', nome: 'Tribunal de Justiça do Piauí', descricao: 'Tribunal de Justiça do Piauí' },
                        { value: '40', nome: 'Tribunal de Justiça do Rio Grande do Norte', descricao: 'Tribunal de Justiça do Rio Grande do Norte' },
                        { value: '43', nome: 'Tribunal de Justiça de Rio Grande do Sul', descricao: 'Tribunal de Justiça de Rio Grande do Sul' },
                        { value: '37', nome: 'Tribunal de Justiça do Rio de Janeiro', descricao: 'Tribunal de Justiça do Rio de Janeiro' },
                        { value: '42', nome: 'Tribunal de Justiça de Rondônia', descricao: 'Tribunal de Justiça de Rondônia' },
                        { value: '45', nome: 'Tribunal de Justiça de Santa Catarina', descricao: 'Tribunal de Justiça de Santa Catarina' },
                        { value: '4', nome: 'Tribunal de Justiça de São Paulo', descricao: 'Tribunal de Justiça de São Paulo' }
                    ]
                },
                {
                    id: "149",
                    tipo: "LOCALIDADE",
                    descricao: "LOCALIDADE",
                    doisValor: [
                        { value: 'PRIMEIRA_INSTANCIA', nome: '1ª Instância', descricao: '1ª Instância' },
                        { value: 'SEGUNDA_INSTANCIA', nome: '2ª Instância', descricao: '2ª Instância' },
                        { value: 'TERCEIRA_INSTANCIA', nome: '3ª Instância', descricao: '3ª Instância' },
                        { value: 'QUARTA_INSTANCIA', nome: '4ª Instância', descricao: '4ª Instância' }
                    ]
                },
                {
                    id: "150",
                    tipo: "CAPITUR POR",
                    descricao: "CAPITUR POR",
                    doisValor: [
                        { value: 'PRIMEIRA_INSTANCIA', nome: '1ª Instância', descricao: '1ª Instância' },
                        { value: 'SEGUNDA_INSTANCIA', nome: '2ª Instância', descricao: '2ª Instância' },
                        { value: 'TERCEIRA_INSTANCIA', nome: '3ª Instância', descricao: '3ª Instância' },
                        { value: 'QUARTA_INSTANCIA', nome: '4ª Instância', descricao: '4ª Instância' }
                    ]
                },
                {
                    id: "151",
                    tipo: "CAPTURA AUTOMATICA",
                    descricao: "CAPTURA AUTOMATICA",
                    doisValor: [
                        { value: 'NAO_CAPTURAR', nome: 'Não capturar', descricao: 'Não capturar' },
                        { value: 'DIARIA', nome: 'Diária', descricao: 'Diária' },
                        { value: 'SEMANAL', nome: 'Semanal', descricao: 'Semanal' },
                        { value: 'MENSAL', nome: 'Mensal', descricao: 'Mensal' }

                    ]
                }, {
                    id: "152",
                    tipo: "GRUPO TRABALHO",
                    descricao: "GRUPO TRABALHO",
                    doisValor: [
                        { value: '1', nome: 'Admintradores', descricao: 'Admintradores' },
                        { value: '2', nome: 'Civel', descricao: 'Civil' },
                        { value: '3', nome: 'Criminal', descricao: 'Criminal' },
                        { value: '4', nome: 'Trabalhista', descricao: 'Trabalhista' }

                    ]
                }, {
                    id: "153",
                    tipo: "NATUREZA",
                    descricao: "NATUREZA",
                    doisValor: [

                        { value: '1', nome: 'Cível', descricao: 'Cível' },
                        { value: '2', nome: 'Eleitoral', descricao: 'Eleitoral' },
                        { value: '3', nome: 'Militar', descricao: 'Militar' },
                        { value: '4', nome: 'Penal', descricao: 'Penal' },
                        { value: '5', nome: 'Previdenciária', descricao: 'Previdenciária' },
                        { value: '6', nome: 'Societário', descricao: 'Societário' },
                        { value: '7', nome: 'Trabalhista', descricao: 'Trabalhista' },
                        { value: '8', nome: 'Tributária', descricao: 'Tributária' }

                    ]
                }
            ]
        },
    });


    return a;
};



//===============================================================================================
function NFNotaInfoItem() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "numeroItem", tipo: "Integer", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "produto", tipo: "NFNotaInfoItemProduto", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "imposto", tipo: "NFNotaInfoItemImposto", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "impostoDevolvido", tipo: "NFImpostoDevolvido", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "informacoesAdicionais", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });

    return a;
};





function NFNotaInfoItemProduto() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "codigo", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "codigoDeBarras", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "descricao", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "ncm", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "nomeclaturaValorAduaneiroEstatistica", tipo: "List<String>", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "codigoEspecificadorSituacaoTributaria", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "extipi", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "cfop", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "unidadeComercial", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "quantidadeComercial", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorUnitario", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorTotalBruto", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "codigoDeBarrasTributavel", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "unidadeTributavel", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "quantidadeTributavel", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorUnitarioTributavel", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorFrete", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorSeguro", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorDesconto", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorOutrasDespesasAcessorias", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "compoeValorNota", tipo: "NFProdutoCompoeValorNota", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "declaracoesImportacao", tipo: "List<NFNotaInfoItemProdutoDeclaracaoImportacao>", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "detalhesExportacao", tipo: "List<NFNotaInfoItemDetalheExportacao>", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "numeroPedidoCliente", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "numeroPedidoItemCliente", tipo: "Integer", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "numeroControleFCI", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "veiculo", tipo: "NFNotaInfoItemProdutoVeiculo", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "medicamentos", tipo: "List<NFNotaInfoItemProdutoMedicamento>", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "armamentos", tipo: "List<NFNotaInfoItemProdutoArmamento>", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "combustivel", tipo: "NFNotaInfoItemProdutoCombustivel", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "numeroRECOPI", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });

    return a;
};




function NFNotaInfoItemProdutoDeclaracaoImportacao() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "numeroRegistro", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "dataRegistro", tipo: "Long", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "localDesembaraco", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "ufDesembaraco", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "dataDesembaraco", tipo: "Long", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "transporteInternacional", tipo: "NFViaTransporteInternacional", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorAFRMM", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "formaImportacaoIntermediacao", tipo: "NFFormaImportacaoIntermediacao", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "cnpj", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "ufTerceiro", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "codigoExportador", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "adicoes", tipo: "List<NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao>", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });

    return a;
};

function NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "numero", tipo: "Integer", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "sequencial", tipo: "Integer", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "codigoFabricante", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "desconto", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "numeroAtoConcessorioDrawback", tipo: "Long", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });

    return a;
};

function NFNotaInfoItemDetalheExportacao() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "atoConcessorioDrawback", tipo: "Long", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "exportacaoIndireta", tipo: "NFNotaInfoItemExportacaoIndireta", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    return a;
};

function NFNotaInfoItemExportacaoIndireta() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "numeroRegistroExportacao", tipo: "Long", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "chaveAcessoNFe", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "quantidadeItemEfetivamenteExportado", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });

    return a;
};

function NFNotaInfoItemProdutoVeiculo() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "tipoOperacao", tipo: "NFNotaInfoItemProdutoVeiculoTipoOperacao", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "chassi", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "codigoCor", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "descricaoCor", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "potencia", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "cilindrada", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "pesoLiquido", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "pesoBruto", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "numeroSerie", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "tipoCombustivel", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "numeroMotor", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "capacidadeMaximaTracao", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "distanciaEntreEixos", tipo: "Integer", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "anoModeloFabricacao", tipo: "Integer", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "anoFabricacao", tipo: "Integer", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "tipoPintura", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "tipoVeiculo", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "especieVeiculo", tipo: "Integer", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "condicaoChassi", tipo: "NFNotaInfoItemProdutoVeiculoCondicaoChassi", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "condicao", tipo: "NFNotaInfoItemProdutoVeiculoCondicao", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "codigoMarcaModelo", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "corDENATRAN", tipo: "NFNotaInfoVeiculoCor", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "lotacao", tipo: "Integer", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "restricao", tipo: "NFNotaInfoItemProdutoVeiculoRestricao", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    return a;
};

function NFNotaInfoItemProdutoMedicamento() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "lote", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "quantidade", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "dataFabricacao", tipo: "Long", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "dataValidade", tipo: "Long", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "precoMaximoConsumidor", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });

    return a;
};





function NFNotaInfoItemProdutoArmamento() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "tipo", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "numeroSerieArma", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "numeroSerieCano", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "descricao", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });

    return a;
};



function NFNotaInfoItemProdutoCombustivel() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "codigoProdutoANP", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualGasNatural", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "codigoAutorizacaoCOFIF", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "quantidade", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "uf", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "cide", tipo: "NFNotaInfoItemProdutoCombustivelCIDE", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    return a;
};

function NFNotaInfoItemProdutoCombustivelCIDE() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "quantidadeBCCIDE", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorAliquota", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valor", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    return a;
};

function NFImpostoDevolvido() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualDevolucao", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "informacaoIPIDevolvido", tipo: "NFInformacaoImpostoDevolvido", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });

    return a;
};


function NFInformacaoImpostoDevolvido() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorIPIDevolvido", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });

    return a;
};


function NFNotaInfoItemImposto() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorTotalTributos", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "icms", tipo: "NFNotaInfoItemImpostoICMS", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "ipi", tipo: "NFNotaInfoItemImpostoIPI", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "impostoImportacao", tipo: "NFNotaInfoItemImpostoImportacao", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "issqn", tipo: "NFNotaInfoItemImpostoISSQN", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "pis", tipo: "NFNotaInfoItemImpostoPIS", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "pisst", tipo: "NFNotaInfoItemImpostoPISST", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "cofins", tipo: "NFNotaInfoItemImpostoCOFINS", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "cofinsst", tipo: "NFNotaInfoItemImpostoCOFINSST", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "icmsUfDestino", tipo: "NFNotaInfoItemImpostoICMSUFDestino", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });

    return a;
};

function NFNotaInfoItemImpostoICMS() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "icms00", tipo: "NFNotaInfoItemImpostoICMS00", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "icms10", tipo: "NFNotaInfoItemImpostoICMS10", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "icms20", tipo: "NFNotaInfoItemImpostoICMS20", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "icms30", tipo: "NFNotaInfoItemImpostoICMS30", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "icms40", tipo: "NFNotaInfoItemImpostoICMS40", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "icms51", tipo: "NFNotaInfoItemImpostoICMS51", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "icms60", tipo: "NFNotaInfoItemImpostoICMS60", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "icms70", tipo: "NFNotaInfoItemImpostoICMS70", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "icms90", tipo: "NFNotaInfoItemImpostoICMS90", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "icmsPartilhado", tipo: "NFNotaInfoItemImpostoICMSPartilhado", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "icmsst", tipo: "NFNotaInfoItemImpostoICMSST", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "icmssn101", tipo: "NFNotaInfoItemImpostoICMSSN101", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "icmssn102", tipo: "NFNotaInfoItemImpostoICMSSN102", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "icmssn201", tipo: "NFNotaInfoItemImpostoICMSSN201", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "icmssn202", tipo: "NFNotaInfoItemImpostoICMSSN202", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "icmssn500", tipo: "NFNotaInfoItemImpostoICMSSN500", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "icmssn900", tipo: "NFNotaInfoItemImpostoICMSSN900", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });

    return a;
};



function NFNotaInfoItemImpostoICMS00() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "origem", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "situacaoTributaria", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "modalidadeBCICMS", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBaseCalculo", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualAliquota", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorTributo", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    return a;
};





function NFNotaInfoItemImpostoICMS10() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "origem", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "situacaoTributaria", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "modalidadeBCICMS", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBaseCalculo", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualAliquota", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorTributo", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "modalidadeBCICMSST", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualMargemValorICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualReducaoBCICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBCICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualAliquotaImpostoICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });

    return a;
};



function NFNotaInfoItemImpostoICMS20() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "origem", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "situacaoTributaria", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "modalidadeBCICMS", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualReducaoBC", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBCICMS", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualAliquota", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorTributo", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorICMSDesoneracao", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "desoneracao", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    return a;
};


function NFNotaInfoItemImpostoICMS30() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "origem", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "situacaoTributaria", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "modalidadeBCICMSST", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualMargemValorAdicionadoICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualReducaoBCICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBCICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualAliquotaImpostoICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorImpostoICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorICMSDesoneracao", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "desoneracao", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });

    return a;
};


function NFNotaInfoItemImpostoICMS40() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "origem", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "situacaoTributaria", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorICMSDesoneracao", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "motivoDesoneracaoICMS", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });

    return a;
};


function NFNotaInfoItemImpostoICMS51() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "origem", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "situacaoTributaria", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "modalidadeBCICMS", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualReducaoBC", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBCICMS", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualICMS", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorICMSOperacao", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualDiferimento", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorICMSDiferimento", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorICMS", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });

    return a;
};



function NFNotaInfoItemImpostoICMS60() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "origem", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "situacaoTributaria", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBCICMSSTRetido", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorICMSSTRetido", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });

    return a;
};


function NFNotaInfoItemImpostoICMS70() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "origem", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "situacaoTributaria", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "modalidadeBCICMS", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualReducaoBC", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBC", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualAliquota", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorTributo", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "modalidadeBCICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualMargemValorAdicionadoICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualReducaoBCICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBCST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualAliquotaImpostoICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorICMSDesoneracao", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "desoneracao", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });

    return a;
};


function NFNotaInfoItemImpostoICMS90() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "origem", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "situacaoTributaria", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "modalidadeBCICMS", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBC", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualReducaoBC", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualAliquota", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorTributo", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "modalidadeBCICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualMargemValorAdicionadoICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualReducaoBCICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBCST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualAliquotaImpostoICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorICMSDesoneracao", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "desoneracao", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });


    return a;
};


function NFNotaInfoItemImpostoICMSPartilhado() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "origem", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "situacaoTributaria", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "modalidadeBCICMS", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBCICMS", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualReducaoBC", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualAliquotaImposto", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorICMS", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "modalidadeBCICMSST", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualMargemValorAdicionadoICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualReducaoBCICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBCICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualAliquotaImpostoICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualBCOperacaoPropria", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "ufICMSST", tipo: "Estado", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });

    return a;
};


function NFNotaInfoItemImpostoICMSST() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "origem", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "situacaoTributaria", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBCICMSSTRetidoUFRemetente", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorICMSSTRetidoUFRemetente", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBCICMSSTUFDestino", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorICMSSTUFDestino", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });

    return a;
};



function NFNotaInfoItemImpostoICMSSN101() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "origem", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "situacaoOperacaoSN", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualAliquotaAplicavelCalculoCreditoSN", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorCreditoICMSSN", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });

    return a;
};




function NFNotaInfoItemImpostoICMSSN102() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "origem", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "situacaoOperacaoSN", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    return a;
};



function NFNotaInfoItemImpostoICMSSN201() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "origem", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "situacaoOperacaoSN", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "modalidadeBCICMSST", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualMargemValorAdicionadoICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualReducaoBCICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBCICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualAliquotaImpostoICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualAliquotaAplicavelCalculoCreditoSN", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorCreditoICMSSN", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });


    return a;
};


function NFNotaInfoItemImpostoICMSSN202() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "origem", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "situacaoOperacaoSN", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "modalidadeBCICMSST", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualMargemValorAdicionadoICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualReducaoBCICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBCICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualAliquotaImpostoICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });


    return a;
};


function NFNotaInfoItemImpostoICMSSN500() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "origem", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "situacaoOperacaoSN", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBCICMSSTRetido", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorICMSSTRetido", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });


    return a;
};


function NFNotaInfoItemImpostoICMSSN900() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "origem", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "situacaoOperacaoSN", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "modalidadeBCICMS", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBCICMS", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualReducaoBC", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualAliquotaImposto", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorICMS", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "modalidadeBCICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualMargemValorAdicionadoICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualReducaoBCICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBCICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualAliquotaImpostoICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorICMSST", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualAliquotaAplicavelCalculoCreditoSN", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });


    return a;
};




function NFNotaInfoItemImpostoIPI() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "classeEnquadramento", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "cnpjProdutor", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "codigoSelo", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "quantidadeSelo", tipo: "BigInteger", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "codigoEnquadramento", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "tributado", tipo: "NFNotaInfoItemImpostoIPITributado", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "naoTributado", tipo: "NFNotaInfoItemImpostoIPINaoTributado", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });


    return a;
};


function NFNotaInfoItemImpostoIPITributado() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "situacaoTributaria", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBaseCalculo", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualAliquota", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "quantidade", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorUnidadeTributavel", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorTributo", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });

    return a;
};




function NFNotaInfoItemImpostoIPINaoTributado() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "situacaoTributaria", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });


    return a;
};


function NFNotaInfoItemImpostoImportacao() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBaseCalculo", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorDespesaAduaneira", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorImpostoImportacao", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorIOF", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });


    return a;
};


function NFNotaInfoItemImpostoISSQN() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBaseCalculo", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorAliquota", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valor", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "codigoMunicipio", tipo: "Integer", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "itemListaServicos", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorDeducao", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorOutro", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorDescontoIncondicionado", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorDescontoCondicionado", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorRetencaoISS", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "indicadorExigibilidadeISS", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "codigoServico", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "codigoMunicipioIncidenciaImposto", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "codigoPais", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "numeroProcesso", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "indicadorIncentivoFiscal", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });

    return a;
};

function NFNotaInfoItemImpostoPIS() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "aliquota", tipo: "NFNotaInfoItemImpostoPISAliquota", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "quantidade", tipo: "NFNotaInfoItemImpostoPISQuantidade", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "naoTributado", tipo: "NFNotaInfoItemImpostoPISNaoTributado", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "outrasOperacoes", tipo: "NFNotaInfoItemImpostoPISOutrasOperacoes", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });


    return a;
};

function NFNotaInfoItemImpostoPISAliquota() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "situacaoTributaria", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBaseCalculo", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualAliquota", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorTributo", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    return a;
};

function NFNotaInfoItemImpostoPISQuantidade() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "situacaoTributaria", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "quantidadeVendida", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorAliquota", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorTributo", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });



    return a;
};


function NFNotaInfoItemImpostoPISNaoTributado() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "situacaoTributaria", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });



    return a;
};

function NFNotaInfoItemImpostoPISOutrasOperacoes() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "situacaoTributaria", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBaseCalculo", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualAliquota", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "quantidadeVendida", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorAliquota", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorTributo", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });


    return a;
};

function NFNotaInfoItemImpostoPISST() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBaseCalculo", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualAliquota", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "quantidadeVendida", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorAliquota", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorTributo", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });


    return a;
};

function NFNotaInfoItemImpostoCOFINS() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "aliquota", tipo: "NFNotaInfoItemImpostoCOFINSAliquota", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "quantidade", tipo: "NFNotaInfoItemImpostoCOFINSQuantidade", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "naoTributavel", tipo: "NFNotaInfoItemImpostoCOFINSNaoTributavel", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "outrasOperacoes", tipo: "NFNotaInfoItemImpostoCOFINSOutrasOperacoes", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });


    return a;
};

function NFNotaInfoItemImpostoCOFINSAliquota() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "situacaoTributaria", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBaseCalulo", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualAliquota", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valor", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });


    return a;
};


function NFNotaInfoItemImpostoCOFINSQuantidade() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "situacaoTributaria", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "quantidadeVendida", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorAliquota", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorTributo", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });

    return a;
};


function NFNotaInfoItemImpostoCOFINSNaoTributavel() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "situacaoTributaria", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });

    return a;
};

function NFNotaInfoItemImpostoCOFINSOutrasOperacoes() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "situacaoTributaria", tipo: "DoisValores", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBaseCalculo", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualCOFINS", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "quantidadeVendida", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorAliquota", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorCOFINS", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    return a;
};


function NFNotaInfoItemImpostoCOFINSST() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBaseCalculo", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualAliquota", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "quantidadeVendida", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorAliquotaCOFINS", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorCOFINS", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });


    return a;
};


function NFNotaInfoItemImpostoICMSUFDestino() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorBaseCalculoDestino", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualRelativoFundoCombatePobrezaDestino", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualAliquotaInternaDestino", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualInterestadual", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualProvisorioPartilha", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorRelativoFundoCombatePobrezaDestino", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorICMSInterestadualDestino", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorICMSInterestadualRemetente", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });

    return a;
};


function NFImpostoDevolvido() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "percentualDevolucao", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "informacaoIPIDevolvido", tipo: "NFInformacaoImpostoDevolvido", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });

    return a;
};


function NFInformacaoImpostoDevolvido() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });
    a.push({ field: { campo: "valorIPIDevolvido", tipo: "String", requerid: false, primaryKey: false, forenkey: false, model: true, xml: true } });


    return a;
};






function NFNotaInfo() {
    var a = [];
    a.push({ field: { campo: "id", tipo: "Integer", requerid: true, primaryKey: true, forenkey: false, model: true, xml: true } });

    a.push({
        field: {
            campo: "identificador",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "versao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "identificacao",
            tipo: "NFNotaInfoIdentificacao",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "emitente",
            tipo: "NFNotaInfoEmitente",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "avulsa",
            tipo: "NFNotaInfoAvulsa",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "destinatario",
            tipo: "NFNotaInfoDestinatario",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "retirada",
            tipo: "NFNotaInfoLocal",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "entrega",
            tipo: "NFNotaInfoLocal",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "pessoasAutorizadasDownloadNFe",
            tipo: "List<NFPessoaAutorizadaDownloadNFe>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "itens",
            tipo: "List<NotaFiscalItens>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "total",
            tipo: "NFNotaInfoTotal",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "transporte",
            tipo: "NFNotaInfoTransporte",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "cobranca",
            tipo: "NFNotaInfoCobranca",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "pagamentos",
            tipo: "List<NFNotaInfoPagamento>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "informacoesAdicionais",
            tipo: "NFNotaInfoInformacoesAdicionais",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "exportacao",
            tipo: "NFNotaInfoExportacao",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "compra",
            tipo: "NFNotaInfoCompra",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "cana",
            tipo: "NFNotaInfoCana",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}


function NFNotaInfoIdentificacao() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });


    a.push({
        field: {
            campo: "uf",
            tipo: "Estado",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "codigoRandomico",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "naturezaOperacao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "formaPagamento",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "modelo",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "serie",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "numeroNota",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "dataHoraEmissao",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "dataHoraSaidaOuEntrada",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "tipo",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "identificadorLocalDestinoOperacao",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "codigoMunicipio",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "tipoImpressao",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "tipoEmissao",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "digitoVerificador",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "ambiente",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "finalidade",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "operacaoConsumidorFinal",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });


    a.push({
        field: {
            campo: "indicadorPresencaComprador",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "programaEmissor",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "versaoEmissor",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });


    a.push({
        field: {
            campo: "dataHoraContigencia",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "justificativaEntradaContingencia",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "referenciadas",
            tipo: "List<NFInfoReferenciada>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}


function NFInfoModelo1Por1AReferenciada() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "uf",
            tipo: "Estado",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "anoMesEmissaoNFe",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "cnpj",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "modeloDocumentoFiscal",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "serie",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "numeroDocumentoFiscal",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}


function NFInfoReferenciada() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });


    a.push({
        field: {
            campo: "chaveAcesso",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "modelo1por1Referenciada",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "infoNFProdutorRuralReferenciada",
            tipo: "NFInfoProdutorRuralReferenciada",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "chaveAcessoCTReferenciada",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "cupomFiscalReferenciado",
            tipo: "NFInfoCupomFiscalReferenciado",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}


function NFInfoProdutorRuralReferenciada() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "ufEmitente",
            tipo: "Estado",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "anoMesEmissao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "cnpjEmitente",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "cpfEmitente",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "ieEmitente",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "modeloDocumentoFiscal",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "serieDocumentoFiscal",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "numeroDocumentoFiscal",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });


    return a;
}

function NFInfoCupomFiscalReferenciado() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "modeloDocumentoFiscal",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "numeroOrdemSequencialECF",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "numeroContadorOrdemOperacao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

function NFNotaInfoEmitente() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "cnpj",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "cpf",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "razaoSocial",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "nomeFantasia",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "endereco",
            tipo: "Endereco",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "inscricaoEstadual",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "inscricaoEstadualSubstituicaoTributaria",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "inscricaoMunicipal",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "classificacaoNacionalAtividadesEconomicas",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "regimeTributario",
            tipo: "Regime",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}


function NFNotaInfoAvulsa() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "cnpj",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "orgaoEmitente",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "matriculaAgente",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "nomeAgente",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "fone",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "uf",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "numeroDocumentoArrecadacaoReceita",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "dataEmissaoDocumentoArrecadacao",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorTotalConstanteDocumentoArrecadacaoReceita",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "reparticaoFiscalEmitente",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "dataPagamentoDocumentoArrecadacao",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

function NFNotaInfoDestinatario() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cnpj",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "cpf",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "idEstrangeiro",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "razaoSocial",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "endereco",
            tipo: "Endereco",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "indicadorIEDestinatario",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "inscricaoEstadual",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "inscricaoSuframa",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "inscricaoMunicipal",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "email",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

function NFNotaInfoLocal() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cnpj",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cpf",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "logradouro",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "numero",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "complemento",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "bairro",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "codigoMunicipio",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "nomeMunicipio",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "uf",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

function NFPessoaAutorizadaDownloadNFe() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cnpj",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cpf",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

function NFNotaInfoTotal() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "icmsTotal",
            tipo: "NFNotaInfoICMSTotal",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "issqnTotal",
            tipo: "NFNotaInfoISSQNTotal",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "retencoesTributos",
            tipo: "NFNotaInfoRetencoesTributos",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

function NFNotaInfoICMSTotal() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "baseCalculoICMS",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorTotalICMS",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorICMSDesonerado",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorICMSFundoCombatePobreza",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorICMSPartilhaDestinatario",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorICMSPartilhaRementente",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "baseCalculoICMSST",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorTotalICMSST",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorTotalDosProdutosServicos",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorTotalFrete",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorTotalSeguro",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorTotalDesconto",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorTotalII",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorTotalIPI",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorPIS",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorCOFINS",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "outrasDespesasAcessorias",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "iCMSOpInter",
            tipo: "ICMSOpInter",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorTotalNFe",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorTotalTributos",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

function NFNotaInfoISSQNTotal() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorTotalServicosSobNaoIncidenciaNaoTributadosICMS",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "baseCalculoISS",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorTotalISS",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorPISsobreServicos",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorCOFINSsobreServicos",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataPrestacaoServico",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorDeducao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorOutros",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorTotalDescontoIncondicionado",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorTotalDescontoCondicionado",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorTotalRetencaoISS",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "tributacao",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

function NFNotaInfoRetencoesTributos() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorRetidoPIS",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorRetidoCOFINS",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorRetidoCSLL",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "baseCalculoIRRF",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorRetidoIRRF",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "baseCalculoRetencaoPrevidenciaSocial",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorRetencaoPrevidenciaSocial",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}




function NFNotaInfoTransporte() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "modalidadeFrete",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "transportador",
            tipo: "NFNotaInfoTransportador",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "icmsTransporte",
            tipo: "NFNotaInfoRetencaoICMSTransporte",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "veiculo",
            tipo: "NFNotaInfoVeiculo",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "reboques",
            tipo: "List<NFNotaInfoReboque>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "vagao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "balsa",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}


function NFNotaInfoRetencaoICMSTransporte() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorServico",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "bcRetencaoICMS",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "aliquotaRetencao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorICMSRetido",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cfop",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "codigoMunicipioOcorrenciaFatoGeradorICMSTransporte",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}


function NFNotaInfoTransportador() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cnpj",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "cpf",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "razaoSocial",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "inscricaoEstadual",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "enderecoComplemento",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "nomeMunicipio",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "uf",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

function NFNotaInfoVeiculo() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "placaVeiculo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "uf",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "registroNacionalTransportadorCarga",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

function NFNotaInfoReboque() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "placaVeiculo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "uf",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "registroNacionalTransportadorCarga",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}


function NFNotaInfoCobranca() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "fatura",
            tipo: "NFNotaInfoFatura",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "duplicatas",
            tipo: "NFNotaInfoDuplicata",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

function NFNotaInfoDuplicata() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "numeroDuplicata",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "dataVencimento",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorDuplicata",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}



function NFNotaInfoFatura() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "numeroFatura",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorOriginalFatura",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorDesconto",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorLiquidoFatura",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

function NFNotaInfoCartao() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tipoIntegracao",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "cnpj",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "operadoraCartao",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "numeroAutorizacaoOperacaoCartao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

function NFNotaInfoPagamento() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "formaPagamentoMoeda",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorPagamento",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "cartao",
            tipo: "NFNotaInfoCartao",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

function NFNotaInfoInformacoesAdicionais() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "informacoesAdicionaisInteresseFisco",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "informacoesComplementaresInteresseContribuinte",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "observacoesContribuinte",
            tipo: "List<NFNotaInfoObservacao>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "observacoesFisco",
            tipo: "List<NFNotaInfoObservacao>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "processosRefenciado",
            tipo: "List<NFNotaInfoProcessoReferenciado>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

function NFNotaInfoObservacao() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "identificacaoCampo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "conteudoCampo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

function NFNotaInfoProcessoReferenciado() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "identificadorProcessoOuAtoConcessorio",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "indicadorOrigemProcesso",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}


function NFNotaInfoExportacao() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "ufEmbarqueProduto",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "localEmbarqueProdutos",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "localDespachoProdutos",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

function NFNotaInfoCompra() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "notaDeEmpenho",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "pedido",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "contrato",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}


function NFNotaInfoCana() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "safra",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "referencia",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "fornecimentosDiario",
            tipo: "List<NFNotaInfoCanaFornecimentoDiario>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "deducoes",
            tipo: "List<NFNotaInfoCanaDeducao>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "quantidadeTotalMes",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "quantidadeTotalAnterior",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "quantidadeTotalGeral",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorFornecimento",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorTotalDeducao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorLiquidoFornecimento",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}



function NFNotaInfoCanaFornecimentoDiario() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dia",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "quantidade",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}



function NFNotaInfoCanaDeducao() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "descricaoDeducao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorDeducao",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}





function NFNota() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "identificadorLocal",
            tipo: "long",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "info",
            tipo: "NFNotaInfo",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "infoSuplementar",
            tipo: "NFNotaInfoSuplementar",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "assinatura",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tipo",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}


function NFNotaInfoSuplementar() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "qrCode",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}



























//Boleto    User_Roles( user_role_id, username, role,parentId,tabelaEnumValue,emprId,processId,create_user,c
function user_rolesModel() {
    a = [];
    a.push({
        field: {
            campo: "user_role_id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "username",
            default: 0,
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "role",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//Boleto    
function rolesModel() {
    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "role",
            default: 0,
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "status",
            default: 0,
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "pagina",
            tipo: "Pagina",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//Boleto	
function BoletoModel() {

    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "ativarBolOnLine",
            default: 0,
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tipoBoleto",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "agencia",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cedente",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "juros",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tipoCalcMora",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "mora",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "instrucoes",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "demonstrativo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "impJuros",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//carne
function CarneModel() {

    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "carneBotelo",
            tipo: "Integer",
            default: 0,
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "carneNormal",
            tipo: "Integer",
            default: 0,
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "localPag",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "instr1",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "instr2",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "instr3",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "instr4",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//carne
function PlanoByEmpresaModel() {

    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "numContrato",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataInicio",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "dataFim",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valor",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "planoId",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//entrada
function EntradaModel() {

    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorTotalFixo",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "manterPrecoVendaProd",
            default: 0,
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//fiscal
function FiscalModel() {

    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "princAtividade",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "regime",
            tipo: "Regime",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "aliqSimples",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "aliqICMS",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "aliqPIS",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "aliqCONFINS",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "aliqIRPJ",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "aliqCLSS",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//alertas
function AlertasModel() {

    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "estoqMin",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            default: 0,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "estoqMax",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            default: 0,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "erroNFe",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            default: 0,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "pdCompra",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            default: 0,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nvCliente",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            default: 0,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "retCaixa",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            default: 0,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//ConfigBlueSoft
function ConfigBlueSoftModel() {

    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "ativBlue",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            default: 0,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "url",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "token",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

// geral
function GeralModel() {

    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "fusoHorario",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "casasDecimais",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "diasCartaCobr",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "infPosicionarMouse",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cnpjCPFUnico",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "impCodPersonalizado",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "logListRelImp",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "obsProdFinProd",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "ativNFCe",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            default: 0,
            xml: true
        }
    });
    return a;
}

//ConfigBlueSoft
function ConfigOSModel() {

    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "impr2Via",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            default: 0,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "imprAss",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            default: 0,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "imprResumo",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            default: 0,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "imprDetHorz",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            default: 0,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "diasGarantia",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            default: 0,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "observ",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//config Prod
function ConfProdutoModel() {

    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cfop",
            tipo: "Cfop",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "icmsSitTrib",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "icmsOrigem",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "icmsModalidadeBC",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "icmsRedBaseCalc",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "icmsAliq",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "icmsMotDesoneracao",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "icmsModBCST",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "icmsMargValAdic",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "icmsRedBaseCalcST",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "icmsPrecoUnitPautaST",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "icmsAliqST",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "ipiSitTrib",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "ipiClasCigarroBebida",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "ipiCNPJProd",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "ipiCodSeloCont",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "ipiQtdSelo",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "ipiCodEnquad",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "ipiTipCalc",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "ipiAliq",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "pisSitTrib",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "pisAliq",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "pisValUnidtrib",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "pistipoCalcSubstTrib",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "pisAliqST",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "pisValorAliqST",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cofinsSubstTrib",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cofinsAliq",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cofinsValorAliq",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cofinsTipoCalcSubstTrib",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cofinsAliqST",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cofinsValorAliqST",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });



    return a;
}


//conf SMTP
function SMTPModel() {

    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "servSMTP",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "porta",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "endEmail",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "usuario",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "senha",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "seguranca",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "ativSMTP",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            default: 0,
            model: true,
            xml: true
        }
    });
    return a;
}


//conf NF-e
function ConfNFEModel() {

    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "presCompr",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "destConsFinal",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "preencherDataHora",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "icmsPadrao",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "ipiPadrao",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "pisPadrao",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cofinsPadrao",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "ambienteEnvio",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "servMsmNota",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "serieEnvio",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "anexarXmlEmail",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "idCSC",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cSC",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "informacaoAdd",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "certificado",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "senha",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "salvarSenha",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cfopPadrao",
            tipo: "Cfop",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tokenNFCe",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "logoDanfe",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "modelo",
            tipo: "DoisValor",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "tipoImpressao",
            tipo: "DoisValor",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "tipoEmissao",
            tipo: "DoisValor",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}


//conf vendas
function VendasModel() {

    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "descontoMaxVenda",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "observacao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "imprSegVia",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "imprAssinatura",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "imprResumoFinanc",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "atuaPrecoClonar",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "imprColUnidade",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "bloquearvendProdSemEstoq",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "addDespCalcImposto",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "retSubstTribICMS",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}


//config
function ConfiguracaoModel() {

    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "confCabecalho",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "confGeral",
            tipo: "ConfigGeral",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "confNFe",
            tipo: "ConfiguracaoNFe",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "confFiscal",
            tipo: "ConfigFiscal",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "confProd",
            tipo: "ConfigProduto",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "confVendas",
            tipo: "ConfigVendas",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "confCMTP",
            tipo: "ConfigSMTP",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "confAlertas",
            tipo: "ConfigAlertas",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "confEntrada",
            tipo: "ConfigEntrada",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "confCarne",
            tipo: "ConfigCarne",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "configOS",
            tipo: "ConfigOS",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "confBlueSoft",
            tipo: "ConfigBlueSoft",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "boletoList",
            tipo: "List<Boleto>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

function DoisValorModel() {
    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "value",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "pagina",
            tipo: "Pagina",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "descricao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "doisValorType",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

function DoisValorTypeModel() {
    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "tipo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "descricao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "pagina",
            tipo: "Pagina",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

function InsertUtilModel() {
    a = [];
    a.push({
        field: {
            campo: "parentId",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "processId",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tabelaEnumValue",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "emprId",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ PROCESSO
function ClienteCompromissoModel() {
    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "ClienteId",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "CompromissoId",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//================ PROCESSO
function ProcessoClienteModel() {
    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "ClienteId",
            tipo: "Cliente",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "ProcessoId",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//================ PROCESSO
function ProcessModel() {

    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataProcess",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataFim",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valor",
            tipo: "Float",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "acao",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "natureza",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "senha",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "assunto",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "tipoProcesso",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "statusProc",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "grupoTrabalho",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "descricaoProc",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "processo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "situacao",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "instancia",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "orgao",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "npadraocnj",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "npadrao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "agendarCap",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "distribuido",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorProvisionado",
            tipo: "Float",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorAcao",
            tipo: "Float",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "observacaoProc",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "justica",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "tribunal",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "instancia1",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "localidade",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "capturpor",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "numeroprocesso",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "capautomatica",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "pasta",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "enviarEmail",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "monitorar",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "enviarMdgTelefone",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "quando",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "processo",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "fundamentacaoJuridica",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "fatos",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "pretensoesCliente",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "estrategia",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "retringirProcesso",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "usuariosRestricaoProc",
            tipo: "List<Usuario>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "tituloList",
            tipo: "List<ContasReceber>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "clienteList",
            tipo: "List<ProcessoCliente>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "advogadoList",
            tipo: "List<Advogados>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "audienciaList",
            tipo: "List<Compromisso>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "processoStatusList",
            tipo: "List<ProcessoStatus>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "envolvList",
            tipo: "List<Envolvidos>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "arquivos",
            tipo: "List<Arquivo>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "envolvidosExterno",
            tipo: "List<ParticipanteExterno>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Advogados
function AdvogadosModel() {

    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "advogado",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "advogadoPrincipal",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Advogados
function ProcessoUsuariosModel() {

    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "usuarioId",
            tipo: "Usuario",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "processo",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

function DiasHorasModel() {

    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "horaInicio",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "horaFinal",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "diasSemanas",
            tipo: "List<DoisValores>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ public class Advogado extends Pessoa
function AdvogadoModel() {
    a = [];
    a = PessoaModel();

    a.push({
        field: {
            campo: "tempoAtendimento",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "oab",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "mediaAtendimento",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "maxAtendimento",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "maxEncaixe",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "estado",
            tipo: "Estado",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "tipoOab",
            tipo: "DoisValor",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "horasTrabalhos",
            tipo: "List<DiasHoras>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "especialidades",
            tipo: "List<Especialidade>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "compromissos",
            tipo: "List<Compromisso>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "processos",
            tipo: "List<Processo>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;

}
//================ ServicoAndPlano
function DiasHorasModel() {
    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "diasSemanas",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "horaInicio",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "horaFinal",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "diario",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "semanal",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "quinzenal",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "mensal",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "anual",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ ServicoAndPlano
function EspecialidadeModel() {
    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//================ ServicoAndPlano
function CompromissoModel() {
    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "titulo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tipoCompromisso",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "vinculado",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "horaFinal",
            tipo: "DiasHoras",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "local",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "responsavel",
            tipo: "Advogado",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "enviarEmail",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "enviarMdgTelefone",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "quando",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "participante",
            tipo: "List<Cliente>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "participanteExterno",
            tipo: "List<ParticipanteExterno>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "documentos",
            tipo: "List<Documento>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}




//================ ServicoAndPlano
function ProcessoStatusModel() {
    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataProcesso",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "note",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "statusProcess",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}
//================ ServicoAndPlano
function EnvolvidosModel() {
    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cliente",
            tipo: "Cliente",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tipoEnvolvido",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "envolvimento",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "bCliente",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//================ ServicoAndPlano
function ParticipanteExternoModel() {
    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "email",
            tipo: "List<Email>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "telefones",
            tipo: "List<Telefone>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}
//================ ServicoAndPlano
function ServicoAndPlanoModel() {

    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataInicio",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valor",
            tipo: "Double",
            requerid: true,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "servicoPlanoEnumValue",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "servicoList",
            tipo: "Servico",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "planoList",
            tipo: "Plano",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//================ Entidade
function EntidadeModel() {
    a = [];

    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "statusEmpresa",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "permissaotypeenumvalue",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "razao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "entidadeId",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "numFunc",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "statusInicial",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "entidadeEnumValue",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "regime",
            tipo: "Regime",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "documentos",
            tipo: "List<Documento>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "enderecos",
            tipo: "List<Endereco>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "emails",
            tipo: "List<Email>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "telefones",
            tipo: "List<Telefone>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cnaes",
            tipo: "List<CnaeEmpresa>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "statusList",
            tipo: "List<Status>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "notes",
            tipo: "List<Note>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dtAbertura",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "configuracao",
            tipo: "Configuracao",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "configuracaoNFe",
            tipo: "ConfiguracaoNFe",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "usuarios",
            tipo: "List<Usuario>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "bancos",
            tipo: "List<BancoPessoa>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "planosServicos",
            tipo: "PlanoByEmpresa",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "socios",
            tipo: "List<Socio>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "siteList",
            tipo: "List<Site>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "responsavel",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "notificacoes",
            tipo: "List<NotificationPreferences>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}
//================ AUDITORIA
function AuditoriaModel() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataAudiencia",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valor",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "descricao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "noteLIst",
            tipo: "List<Note>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}
//================ Advogado
function ProdutoDTOModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "status",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "produto",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "codigo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nCM",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "codbarra",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataCadastro",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "exceçãoIPI",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cEST",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "informAdicionaisParaNFe",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "anotainternas",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "unidTributada",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "grupo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "subGrupo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "marca",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "pesolíquido",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "pesobruto",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cFOPPadraoNFe",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "IcmsSitTributaria",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "iCMSOrigem",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "iPISitTributaria",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "classeCigarrosBebidas",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cNPJProdutor",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "codControleIPI",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "qtdSeloIPI",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "codEnquadramento",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tipoCalculo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "aliquotaIPI",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "pISSituaTributaria",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorUnidtribPIS",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tipocalculoSubstTrib",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorTribPISST",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cOFINSSituatributaria",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorTribCOFINS",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tipoCalculoSubstTrib",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "aliquotaCOFINSST",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "estMinimo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "estAtual",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "estMaximo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "margemLucro",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "precoVenda",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "precoCusto",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;

}


function PessoaTipoModel() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "pessoaTypeEnum",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Advogado
function PessoaModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "nomeFantasia",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "regime",
            tipo: "Regime",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "nomePai",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nomeMae",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nomeConjugue",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "estadoCivil",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "datanasc",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tipoPessoa",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "foto",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "pessoaTypeEnum",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "sexo",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "enderecos",
            tipo: "List<Endereco>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "documentos",
            tipo: "List<Documento>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "emails",
            tipo: "List<Email>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "telefones",
            tipo: "List<Telefone>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "bancos",
            tipo: "List<BancoPessoa>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "formaPagamentoList",
            tipo: "List<FormaPgPessoa>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "condPagList",
            tipo: "List<CondPagPessoa>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "contatoList",
            tipo: "List<Contato>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "pessoaTipo",
            tipo: "List<pessoaTipo>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//================ Convenio
function ConvenioModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "razao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cnpj",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "registroANS",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "retornoDias",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "versaoTISS",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "numeroAtualGuia",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "endereco",
            tipo: "Endereco",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "telefones",
            tipo: "List<Telefone>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "emails",
            tipo: "List<Emails>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "percentual2Procs",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "percentual3Procs",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "percentual4Procs",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "contratosConvenio",
            tipo: "List<ContratoConvenio>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Convenio
function ContratoConvenioModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "codigoOperadora",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "Contratado",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "contaRecebimento",
            tipo: "Conta",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "loginWS",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "senhaWS",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Convenio
function ClasseModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}


//=================================

function GroupMenuModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            createSeq: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "pagina_id",
            tipo: "List<CategoriaMenu>",
            requerid: false,
            tipoLigacao: { tipo: "OneToMany", ligacao: "groupMenu_id" },
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}


//================ Convenio
function CategoriaMenuModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            createSeq: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "categoriaMenu_id",
            tipo: "List<Menu>",
            requerid: false,
            tipoLigacao: { tipo: "OneToMany", ligacao: "categoriaMenu_id" },
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}


//Boleto    
function MenuModel() {
    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            createSeq: true,
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "nome",
            default: 0,
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "label",
            default: 0,
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "status",
            default: 0,
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "nivel",
            default: 0,
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "permissao",
            default: 0,
            tipo: "Role",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "help",
            tipo: "Help",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "pagina_id",
            tipo: "Pagina",
            requerid: false,
            tipoLigacao: { tipo: "OneToOne", ligacao: "pagina_id" },
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//Boleto    
//tipoLigacao: { tipo: "OneToMany", ligacao: "fiend_id" },
function PaginaModel() {
    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            createSeq: true,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "pagina",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "status",
            default: 0,
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "help",
            tipo: "Help",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "tab_id",
            tipo: "Tab",
            requerid: false,
            tipoLigacao: { tipo: "OneToMany", ligacao: "tab_id" },
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "help_id",
            tipo: "Help",
            requerid: false,
            tipoLigacao: { tipo: "OneToOne", ligacao: "help_id" },
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//Boleto    
function HelpModel() {
    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            createSeq: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "titulo",
            default: 0,
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "status",
            default: 0,
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "texto",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}




function TabModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            createSeq: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "label",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "order",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "fields",
            tipo: "List<Field>",
            tipoLigacao: { tipo: "OneToMany", ligacao: "fiend_id" },
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//================ Convenio

// requerid: false,
// primaryKey: false,
// forenkey: true,
// tipoLigacao: { tipo: "OneToOne", ligacao: "estado_id" },
//================ Convenio
function EntidadeModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            createSeq: true,
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "descricao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "fields",
            tipo: "List<Field>",
            tipoLigacao: { tipo: "OneToMany", ligacao: "fiend_id" },
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}
//Boleto    
function FieldModel() {

    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            createSeq: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "nome",
            default: 0,
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "status",
            default: 0,
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "obrigatorio",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "tipo",
            default: 0,
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "label",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "tootip",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "ordem",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "help",
            tipo: "Help",
            tipoLigacao: { tipo: "OneToOne", ligacao: "help_id" },
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "validacao",
            tipo: "List<Validacao>",
            tipoLigacao: { tipo: "OneToMany", ligacao: "validacao_id" },
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "dominio",
            tipo: "List<Dominio>",
            tipoLigacao: { tipo: "OneToMany", ligacao: "dominio_id" },
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "role",
            tipo: "List<Role>",
            tipoLigacao: { tipo: "OneToMany", ligacao: "role_id" },
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//Boleto    
function ValidacaoModel() {
    a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            createSeq: true,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "Error",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "validacaoJS",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "tipo",
            default: 0,
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

function DominioModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            createSeq: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valor",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}
//tipoLigacao: { tipo: "OneToMany", ligacao: "fiend_id" },
//================ Convenio
function InterfaceModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "local",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Convenio
function ForenKeyModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tipo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "requerid",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "primaryKey",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "forenkey",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Convenio
function FieldModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tamanho",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tipo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "requerid",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "primaryKey",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "forenkey",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "model",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "xml",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}
//================ Telefone
function TelefoneModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "typeValue",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "ddd",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "numero",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "telefoneTypeEnumValue",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

function NotaFiscalItensModel() {

    a.push({
        field: {
            campo: "numeroRegistro",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataRegistro",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "localDesembaraco",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "ufDesembaraco",
            tipo: "Estado",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataDesembaraco",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "transporteInternacional",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorAFRMM",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "tpIntermedio",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "cnpj",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "ufTerceiro",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "codigoExportador",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "descricao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "produto",
            tipo: "ProdutoEmpresa",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "cfop",
            tipo: "Cfop",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "quantidade",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorUnitario",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorTotalBruto",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorFrete",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorSeguro",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorDesconto",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valorOutrasDespesasAcessorias",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "numeroRECOPI",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

function ConhecimentoTransporteModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "IdNota",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "remetente",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "vrTotalMercadorias",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "apCreIcms",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "fretePorConta",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "placa",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "especie",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "volume",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "pesoLiquido",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "pesoBruto",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "transportador",
            tipo: "Transportador",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "marca",
            tipo: "Marca",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "estado",
            tipo: "Estado",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "registroNacionalTransportadorCarga",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "vagao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "balsa",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}
//================ Cidade
function CidadeModel() {

    var a = [];

    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "codigo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cdIBGE",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cep",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "municipio",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "estado",
            tipo: "Estado",
            requerid: false,
            primaryKey: false,
            forenkey: true,
            tipoLigacao: { tipo: "OneToOne", ligacao: "estado_id" },
            model: true,
            xml: true
        }
    });

    return a;
}

//================ Cidade
function CepModel() {

    var a = [];

    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "codigo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cdIBGE",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cep",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "municipio",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "estado",
            tipo: "Estado",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//================ Cidade
function NotaFiscalModel() {

    var a = [];

    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "serie",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "ordem",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "numero",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tipo",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nfValor",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataEmissao",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataSaida",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataEntrada",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "modelo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "bxEstoque",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "descItens",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "pcCusto",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cfop",
            tipo: "Cfop",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "conhecimentoTransporte",
            tipo: "ConhecimentoTransporte",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "empresa",
            tipo: "Empresa",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "pessoa",
            tipo: "Pessoa",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tributosList",
            tipo: "List<Tributos>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "formaPagList",
            tipo: "List<FormaPag>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "notaFiscalItens",
            tipo: "List<NotaFiscalItens>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "noteList",
            tipo: "List<Note>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "contasList",
            tipo: "List<ContasPagar>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "itensEspeciais",
            tipo: "List<ItensEspeciais>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nfStatusList",
            tipo: "List<NFStatus>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Estado
function EstadoModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            createSeq: true,
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "abreviacao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Tarefa
function TarefaModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "descricao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Empresa
function EmpresaModel() {

    var a = [];

    a = EntidadeModel();

    a.push({
        field: {
            campo: "planoList",
            tipo: "List<Plano>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "qntFilial",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "qntDeposito",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "filialList",
            tipo: "List<Filial>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "depositoList",
            tipo: "List<Deposito>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "contaCorrenteList",
            tipo: "List<ContaCorrente>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "tarefaList",
            tipo: "List<TarefaEnt>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "parceiroId",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "contabilidadeId",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "permissaoTypeEnum",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//================ Medico
function EspecialidadeMedicoModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "especialidade",
            tipo: "Especialidade",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "conselho",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "registro",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "uf",
            tipo: "Estado",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}
//================ Consulta

//================ Medico
function MedicoModel() {

    var a = [];
    var a = PessoaModel();
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "especialidadeMedica",
            tipo: "EspecialidadeMedico",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "anamnesePadrao",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "evolucaoPadrao",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "grauParticipacaoPadrao",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "conveniosAceito",
            tipo: "List<MedicoConvenio>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "msginforNaAgenda",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "horaDia",
            tipo: "List<HoraDias>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}


//================ Consulta


function AnamneseModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataRelatorio",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "queixaPrincipal",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "historiaDoencaAtual",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "medicamentoUso",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "etilismo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tabagismo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "usoSubstanciaIlicitas",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "praticaAtividadesFisicas",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dst",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "exameFisico",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "conduta",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "doencas",
            tipo: "List<DoisValores>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "historicoFamiliar",
            tipo: "List<DoisValores>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Consulta

function EquipamentoModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nomeEquipamento",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "proprietario",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "gradeHorarios",
            tipo: "DiasHorarios",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}


//================ Consulta

function KitModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nomeKit",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tabela",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "produtos",
            tipo: "List<ProdutoKit>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

function ProdutoKitModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "quantidade",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "produto",
            tipo: "Produto",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "variavel",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

function ProcedimentoModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nomeProcedimento",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tipo",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valor",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tempoMinutos",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "maxPacienteHorario",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "diasRetorno",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "equipamentoPadrao",
            tipo: "Equipamento",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "grupo",
            tipo: "GrupoProcedimento",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "sigla",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "codigo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "obrigarRespTempo",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "SolicitarIndicClinica",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "limitarConvenios",
            tipo: "Convenio",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "limitarLocais",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "avisosLembretesAgendar",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "enviarEmailSMS",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "textoEmail",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "textoSMS",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "kits",
            tipo: "List<Kit>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "participantes",
            tipo: "List<Participantes>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}
//================ Consulta


function ConsultaModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataConsulta",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "anamnese",
            tipo: "List<Anamnese>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valor",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataMarcacao",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "medico",
            tipo: "Medico",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "paciente",
            tipo: "Paciente",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "planoSaude",
            tipo: "PlanoSaude",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "exameList",
            tipo: "List<Exame>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}


//================ PedidoCompras
function PedidoComprasModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Cotacao
function CotacaoModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Exame
function ExameModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Avisos
function AvisosModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}


//================ HoraFunc
function HoraFuncModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Beneficios
function BeneficiosModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Eventos
function EventosModel() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "codigo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "descricao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valor",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "porcentagem",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tipo",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "isSistema",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "isMensal",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//transaction (    token ,inicioSession ,finalSession ,userId ,emprId ,processId ,create_date ,create_user ,modify_date ,modify_user         )
function TransactionModel() {

    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "token",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "inicioSession",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "finalSession",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "userId",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}


//================ Usuario
function UsuarioModel() {
    //users( id, emprId, processId, username, password, pergunta, role, language, ultAcesso, tabelaEnumValue,create_user,create_date,modify_user,modify_date)values ( 10110, 1, 1, 'username_4', 'password_4', 'p
    //id,  username ,telefone ,emprId ,processId ,password ,pergunta ,language ,ultAcesso ,tabelaEnumValue ,create_date ,create_user ,modify_date ,modify_user   
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "telefone",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "username",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "password",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "pergunta",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cpf",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "email",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "senha",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "role",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "language",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "ultAcesso",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ ContasPagar
function ContasPagarModel() {

    var a = [];
    a = TituloModel();

    a.push({
        field: {
            campo: "fornecedor",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

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
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorIni",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorFin",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });;
    a.push({
        field: {
            campo: "parcelas",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "listTipoPag",
            tipo: "List<TipoPag>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ CondPagPessoa
function CondPagPessoaModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "condPagId",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}


//================ FormaPg
function FormaPgModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "descricao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "observacao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "diasPg",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "parcelamentoMax",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "parcelamentoSemJuros",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "entrada",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "juros",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "taxaFixa",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "descAvista",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "conta",
            tipo: "Conta",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "tipoDoc",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "qntIntervalo",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "intervalo",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//================ FormaPgModelPessoa
function FormaPgModelPessoa() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "formaPgId",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}


//================ Banco
function BancoModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "site",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "codigo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "descricao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}


//================ BancoPessoa
function BancoPessoaModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "numCont",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "saldo",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "bancoId",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//================ BancoPessoa
function TipoPagModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "descricao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}
//================ ContaCorrente
function ContaModel() {

    var a = [];

    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "descricao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "numeroConta",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "saldo",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataUltLanc",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tipoConta",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "observacao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}
//================ ContaCorrente
function ContaCorrenteModel() {

    var a = [];

    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "agencia",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "saldo",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "numeroConta",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nossoNumero",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "statusConta",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "historicoList",
            tipo: "List<HistoricoMovimento>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}


//================ Caixa
function CaixaModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "saldo",
            tipo: "Double",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "baixaTituloList",
            tipo: "List<BaixaTitulo>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Regime
function RegimeModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "descricao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Cnae
function CnaeModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "codigo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cnae",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "descricao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "abreviado",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Cnae	Empresa
function CnaeEmpresaModel() {

    var a = [];

    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "idCnae",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

function ProdutoParentIdModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tributacao",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "estoqueList",
            tipo: "List<Estoque>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "precoList",
            tipo: "List<Preco>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "custoList",
            tipo: "List<Custo>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "porcaoList",
            tipo: "List<Porcao>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "rentabilidadeList",
            tipo: "List<Rentabilidade>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cfopList",
            tipo: "List<CfopPessoa>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataValidade",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "localizacao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "comissao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}


function ProdutoModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "cdBarras",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nCM",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "cEST",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });


    a.push({
        field: {
            campo: "excTabIPI",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "produto",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataCreate",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "quant",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "unimed",
            tipo: "UniMed",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });


    a.push({
        field: {
            campo: "marca",
            tipo: "Marca",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });



    return a;
}

//================ Categoria
function ProdutoEmpresaModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "prodId",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });


    a.push({
        field: {
            campo: "informAdicionaisParaNFe",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "anotainternas",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "dataCadastro",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "estoqueList",
            tipo: "List<Estoque>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "aplicacao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "fracao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "InfaddNFe",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "AnotInt",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "custoList",
            tipo: "List<Preco>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "rentabilidadeList",
            tipo: "List<Rentabilidade>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "porcaoList",
            tipo: "List<Porcao>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "precoList",
            tipo: "List<Preco>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "margem",
            tipo: "MargemList",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "margemlucro",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "tributacao",
            tipo: "Tributacao",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "codigo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "uniMed",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "categoria",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "marca",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "pesoBruto",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "pesoLiquido",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "modoUso",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;

}


//================ Categoria
function ICMSModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "prodId",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "sitTributaria",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "origem",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "modalidadeBC",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "redBase",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });


    a.push({
        field: {
            campo: "aliqICMS",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });


    a.push({
        field: {
            campo: "motDesoneracao",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });


    return a;

}

//================ Categoria
function IPIModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "prodId",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "sitTributaria",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "classeCigarrosBebidas",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cNPJProdutor",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "codControleIPI",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "qtdSeloIPI",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "codEnquadramento",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tipoCalculo",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "aliquotaIPI",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Categoria
function COFINSModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "prodId",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "sitTributaria",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorTribCOFINS",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tipoCalculoSubstTrib",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "aliquotaCOFINSST",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;

}

//================ Categoria
function ICMSOpInterModel() {


    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "prodid",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "perICMSUFDest",
            tipo: "Double",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "vrBaseCalcUFDest",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "aliqIntUFDest",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "aliqInterestadual",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "percProvPart",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "vrICMSPartUFDest",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "vrICMSPartUFReme",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "vrICMSRelFCPUFDest",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;

}


//================ Categoria
function PISModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "prodId",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "pISSituaTributaria",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorUnidtribPIS",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tipocalculoSubstTrib",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorTribPISST",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;

}

//================ Categoria
function TributacaoModel() {


    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "descricao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "prodId",
            tipo: "Produto",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cfop",
            tipo: "Cfop",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "imposto",
            tipo: "NFNotaInfoItemImposto",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "impostoDevolvido",
            tipo: "impostoDevolvido",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}


//================ Categoria
function CategoriaModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "categoria",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "margem",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Cfop
function CfopModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cfop",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "natureza",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "simplificado",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cfopTypeEnum",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "icms",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "icmsReduzido",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "margemAgregadaST",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cstPrincipal",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "classFiscal",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "observacao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Cfop
function CfopParentIdModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cfop",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Marca
function MarcaModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "marca",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "fabricante",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "emailList",
            tipo: "List<Email>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "enderecoList",
            tipo: "List<Endereco>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "telefoneList",
            tipo: "List<Telefone>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//================ Marca
function MarcaProdutoModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "marcaId",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Grupo
function GrupoModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "grupo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "descricao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "subGrupo",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Grupo
function SubGrupoModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "subGrupo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "descricao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Grupo
function UniMedModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "unimed",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "sigla",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Orcamento
function CustoModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valor",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "custo",
            tipo: "List<CustoItem>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//================ OrcamentoItens
function CustoItensModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "custo",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "custoDesp",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//================ Orcamento
function EstoqueModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "estoqueTypeEnum",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "ultimoMov",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "quant",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//================ Orcamento
function PorcaoModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valor",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "porcaoItens",
            tipo: "List<PorcaoItem>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//================ Orcamento
function PorcaoItensModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "porcao",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "vd",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "unimed",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//================ Orcamento
function RentabilidadeModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "rentabilidadeList",
            tipo: "List<RentabilidadeItens>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//================ Orcamento
function RentabilidadeItensModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "produto",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valor",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "rentabilidadeTypeEnum",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//=================================================================================================================================================================================


function ServicoByPlano() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "rentabilidadeTypeEnum",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//================ Site


function SiteModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "comoTrabalhamos",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "url",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "quemSomos",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "missao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "visao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "titulo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "logo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "atorization",
            tipo: "Boolean",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "siteTypeEnum",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "empresa",
            tipo: "Empresa",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "servicoList",
            tipo: "List<Servico>",
            List: ServicoModel(),
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "planoList",
            tipo: "List<PlanoBySite>",
            List: PlanoBySiteModel(),
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}
//================ Servico
function ServicoModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            createSeq: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "descricao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "preco",
            tipo: "List<Preco>",
            tipoLigacao: { tipo: "ManyToMany", ligacao: "preco_id" },
            List: PrecoModel(),
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Plano
function PlanoBySiteModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true,
            createSeq: true
        }
    });
    a.push({
        field: {
            campo: "plano_id",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "plano",
            tipo: "Plano",
            tipoLigacao: { tipo: "OneToOne", ligacao: "plano_id" },
            List: "Plano",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ PlanoByServico
function PlanoByServicoModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            createSeq: true,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "servico",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}



//================ Plano
function PlanoModel() {

    var a = [];

    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            createSeq: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataInicio",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataFinal",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "numeroContrato",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "descricao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "titulo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cor",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "precoList",
            tipo: "List<Preco>",
            List: "Preco",
            tipoLigacao: { tipo: "OneToMany", ligacao: "plano_id" },
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "servicoList",
            tipo: "List<PlanoByServico>",
            List: "PlanoByServico",
            tipoLigacao: { tipo: "OneToMany", ligacao: "plano_id" },
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
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
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "codIbge",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "pais",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "logradouro",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "bairro",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "numero",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "enderecoTypeValue",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cep",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "latitude",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "longitude",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "complemento",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cidade",
            tipo: "Cidade",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}
//================ Orcamento
function EmailModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "typeValue",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "email",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "emailTypeEnumValue",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Orcamento
function TituloModel() {




    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "numero",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "formapg",
            tipo: "FormaPg",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "parcela",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataEmissao",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataVencimento",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "observacao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "descricao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "financeiroEnumValue",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "listBaixa",
            tipo: "List<BaixaTitulo>",
            List: "BaixaTitulo",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "dataPagamento",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "docId",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "tipoDoc",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "formaCadastro",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "intervalo",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "categoria",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "valor",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "situacao",
            tipo: "DoisValores",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//================ Orcamento
function TipoBaixaModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "descricao",
            tipo: "List<BaixaTitulo>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ OrcamentoList
function BaixaTituloModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "conta",
            tipo: "Conta",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "finanId",
            Integer: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataBaixa",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "observacao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valor",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "seguro",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "outros",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "juros",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "multa",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "desconto",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tipoBaixaList",
            tipo: "List<TipoBaixa> ",
            List: "BaixaTitulo",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//================ Orcamento
function ArquivoModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "local",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tipo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "tamanho",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}
//================ Orcamento
function ClassificacaoModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "descricao",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "codigo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}
//================ Orcamento
function DocumentosModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "documentoTypeEnumValue",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "numero",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "data",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}
//================ Orcamento
function HistoricoModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "data",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "userId",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "acaoEnumValue",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//================ HistoricoUtil
function HistoricoUtilModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataMovimento",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "HistoricoUtilType",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "quantidade",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });


    return a;
}

//================ Historico Itens
function HistoricoItensModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "acaoEnumValue",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Orcamento
function NoteModel() {

    var a = [];

    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "noteText",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//================ Orcamento
function SocioModel() {
    // id,    socioAdm ,cota ,porcentagem ,parentId ,tabelaEnumValue ,emprId ,processId ,create_date ,create_user ,modify_date ,modify_user ,pessoa ,dataProlabore ,valorProlabore 
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "socioAdm",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "pessoa",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valorProlabore",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataProlabore",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cota",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "porcentagem",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Ordem Servico Status
function OrdemServicoStatusModel() {

    var a = [];

    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cota",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "porcentagem",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//================ Ordem Servico Status
function OrdemServicoTypeModel() {
    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
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
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            createSeq: true,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "userId",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "data",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "assunto",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "statusValue",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "OrdemServicoItensList",
            tipo: "List<OrdemServicoItens>",
            List: OrdemServicoItensModel(),
            tipoLigacao: { tipo: "OneToMany", ligacao: "ordemServico_id" },
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });


    return a;
}

//================ Ordem Servico Itens
function OrdemServicoItensModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            createSeq: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "data",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "texto",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });


    return a;
}

//================ Orcamento
function StatusModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataStatus",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "statusValue",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "acaoEnumValue",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "note",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}



//================ Orcamento
function AgenciaModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "gerente",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "responsavelConta",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "numeroAgencia",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "enderecos",
            tipo: "List<Endereco>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "emails",
            tipo: "List<Email>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "telefones",
            tipo: "List<Telefone>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "contaList",
            tipo: "List<Conta>",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Orcamento
function ProfissaoModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}

//================ Orcamento
function SalarioModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}



//================ Orcamento
function PrecoModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataMarcacao",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "precoTypeEnum",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "valor",
            tipo: "Double",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataProInicial",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataProFinal",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}


//================ Orcamento
function ContatoModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            createSeq: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "lido",
            tipo: "Integer",
            requerid: false,
            defauld: 0,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataContato",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "nome",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "email",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "telefone",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "assunto",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "texto",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "motivoValue",
            tipo: "Integer",
            defauld: 0,
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    a.push({
        field: {
            campo: "contatosList",
            tipo: "List<ContatoItens>",
            requerid: false,
            tipoLigacao: { tipo: "OneToMany", ligacao: "contato_id" },
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });

    return a;
}

//================ Orcamento
function ContatoItensModel() {

    var a = [];
    a.push({
        field: {
            campo: "id",
            tipo: "Integer",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            createSeq: true,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "dataAlt",
            tipo: "Long",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "texto",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "titulo",
            tipo: "String",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "contatoStatus",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "visto",
            tipo: "Boolean",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
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
    a.push({
        field: {
            campo: "depositoList",
            tipo: "Deposito",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "marcaId",
            tipo: "Integer",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    return a;
}


//================ Orcamento
function FilialModel() {

    var a = [];
    a = EntidadeModel();
    a.push({
        field: {
            campo: "depositoList",
            tipo: "Deposito",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
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

function dependenciaBase() {
    var a = []

    a.push({
        dependencia: "Endereco",
        campos: EnderecoModel()
    });
    a.push({
        dependencia: "Email",
        campos: EmailModel()
    });
    a.push({
        dependencia: "Documentos",
        campos: DocumentosModel()
    });
    a.push({
        dependencia: "Telefone",
        campos: TelefoneModel()
    });
    a.push({
        dependencia: "Status",
        campos: StatusModel()
    });
    a.push({
        dependencia: "Note",
        campos: NoteModel()
    });

    return a
}

function dependenciaConf() {
    var a = []

    a.push({
        dependencia: "Boleto",
        campos: BoletoModel()
    });
    a.push({
        dependencia: "configCarne",
        campos: CarneModel()
    });
    a.push({
        dependencia: "configEntrada",
        campos: EntradaModel()
    });
    a.push({
        dependencia: "configFiscal",
        campos: FiscalModel()
    });
    a.push({
        dependencia: "configAlertas",
        campos: AlertasModel()
    });
    a.push({
        dependencia: "configGeral",
        campos: GeralModel()
    });
    a.push({
        dependencia: "configProduto",
        campos: ConfProdutoModel()
    });
    a.push({
        dependencia: "configSMTP",
        campos: SMTPModel()
    });
    a.push({
        dependencia: "ConfiguracaoNFE",
        campos: ConfNFEModel()
    });
    a.push({
        dependencia: "configVendas",
        campos: VendasModel()
    });
    a.push({
        dependencia: "Configuracao",
        campos: ConfiguracaoModel()
    });

    a.push({
        dependencia: "configOS",
        campos: ConfigOSModel()
    });

    a.push({
        dependencia: "confAlertas",
        campos: AlertasModel()
    });

    a.push({
        dependencia: "confBlueSoft",
        campos: ConfigBlueSoftModel()
    });

    return a
}

function dependenciaEntidade() {
    var a = [];
    a = dependenciaBase();
    a = dependenciaConf();
    a.push({
        dependencia: "Entidade",
        campos: EntidadeModel()
    });
    a.push({
        dependencia: "Regime",
        campos: RegimeModel()
    });
    a.push({
        dependencia: "Cnae",
        campos: EnderecoModel()
    });
    a.push({
        dependencia: "CnaeEmpresa",
        campos: EnderecoModel()
    });
    return a
}

function dependenciaEmpresa() {
    var a = [];
    a = dependenciaEntidade();
    a.push({
        dependencia: "Socios",
        campos: EnderecoModel()
    });
    a.push({
        dependencia: "Plano",
        campos: PlanoModel()
    });
    a.push({
        dependencia: "Tarefa",
        campos: TarefaModel()
    });
    a.push({
        dependencia: "Filial",
        campos: EnderecoModel()
    });
    return a
}

function dependenciaAdvocacia() {
    var a = [];
    a = dependenciaEntidade();
    a.push({
        dependencia: "Socios",
        campos: EnderecoModel()
    });
    a.push({
        dependencia: "Plano",
        campos: PlanoModel()
    });
    a.push({
        dependencia: "Tarefa",
        campos: TarefaModel()
    });
    a.push({
        dependencia: "Filial",
        campos: EnderecoModel()
    });
    return a
}

function dependenciaClinica() {
    var a = [];
    a = dependenciaEntidade();
    a.push({
        dependencia: "Socios",
        campos: EnderecoModel()
    });
    a.push({
        dependencia: "Plano",
        campos: PlanoModel()
    });
    a.push({
        dependencia: "Tarefa",
        campos: TarefaModel()
    });
    a.push({
        dependencia: "Filial",
        campos: EnderecoModel()
    });
    return a
}

function dependenciaCondominio() {
    var a = [];
    a = dependenciaEntidade();
    a.push({
        dependencia: "Socios",
        campos: EnderecoModel()
    });
    a.push({
        dependencia: "Plano",
        campos: PlanoModel()
    });
    a.push({
        dependencia: "Tarefa",
        campos: TarefaModel()
    });
    a.push({
        dependencia: "Filial",
        campos: EnderecoModel()
    });
    return a
}

function dependenciaUsuario() {
    var a = [];
    a.push({
        dependencia: "Empresa",
        campos: EnderecoModel()
    });
    return a

}

function dependenciaFilial() {
    var a = [];
    a = dependenciaEntidade();
    a.push({
        dependencia: "Deposito",
        campos: EnderecoModel()
    });
    return a
}

function dependenciaDeposito() {
    var a = [];
    a = dependenciaEntidade();
    return a
}

function dependenciaCondominio() {
    var a = [];
    a = dependenciaEntidade();
    return a
}

function dependenciaClinica() {
    var a = [];
    a = dependenciaEntidade();
    return a
}

function dependenciaPessoa() {
    var a = [];
    a = dependenciaBase();
    return a
}

function dependenciaAdvogado() {
    var a = [];
    a = dependenciaBase();
    return a
}

function dependenciaCliente() {
    var a = [];
    a = dependenciaBase();
    return a
}

function dependenciaFornecedor() {
    var a = [];
    a = dependenciaBase();
    return a
}

function dependenciaTransportador() {
    var a = [];
    a = dependenciaBase();
    return a
}

function dependenciaMedico() {
    var a = [];
    a = dependenciaBase();
    return a
}

function dependenciaPaciente() {
    var a = [];
    a = dependenciaBase();
    return a
}

function dependenciaSindico() {
    var a = [];
    a = dependenciaBase();
    return a
}

function dependenciaInquilino() {
    var a = [];
    a = dependenciaBase();
    return a
}

function dependenciaFuncionario() {
    var a = [];
    a = dependenciaBase();
    return a
}

function dependenciaServico() {
    var a = [];
    a.push({
        dependencia: "Preco",
        campos: PrecoModel()
    });
    return a
}

function dependenciaSite() {
    var a = [];
    a.push({
        dependencia: "Servico",
        campos: ServicoModel()
    });
    a.push({
        dependencia: "Plano",
        campos: PlanoModel()
    });
    a.push({
        dependencia: "Status",
        campos: StatusModel()
    });
    a.push({
        dependencia: "Note",
        campos: NoteModel()
    });
    a.push({
        dependencia: "ServicoAndPlano",
        campos: ServicoAndPlanoModel()
    });
    return a
}

function dependenciaContato() {
    var a = [];
    a.push({
        dependencia: "Contato",
        campos: ContatoItensModel()
    });
    a.push({
        dependencia: "Email",
        campos: EmailModel()
    });
    a.push({
        dependencia: "Telefone",
        campos: TelefoneModel()
    });
    return a
}

function dependenciaPlano() {
    var a = [];
    a.push({
        dependencia: "Preco",
        campos: PrecoModel()
    });
    a.push({
        dependencia: "Servico",
        campos: ServicoModel()
    });
    return a
}

function dependenciaOrdemServico() {
    var a = [];
    a.push({
        dependencia: "OrdemServicoItens",
        campos: OrdemServicoItensModel()
    });
    return a
}

function dependenciaTitulo() {
    var a = [];
    a.push({
        dependencia: "BaixaTitulo",
        campos: BaixaTituloModel()
    });
    a.push({
        dependencia: "TipoBaixa",
        campos: TipoBaixaModel()
    });
    return a
}

function dependenciaContasPagar() {
    var a = [];
    a = dependenciaTitulo();
    return a
}

function dependenciaContasReceber() {
    var a = [];
    a = dependenciaTitulo();
    return a
}

function dependenciaProdutoParent() {
    var a = [];
    a.push({
        dependencia: "Estoque",
        campos: EstoqueModel()
    });
    a.push({
        dependencia: "Preco",
        campos: PrecoModel()
    });
    a.push({
        dependencia: "Custo",
        campos: CustoModel()
    });
    a.push({
        dependencia: "Porcao",
        campos: PorcaoModel()
    });
    a.push({
        dependencia: "Rentabilidade",
        campos: RentabilidadeModel()
    });
    a.push({
        dependencia: "CfopParentId",
        campos: CfopParentIdModel()
    });
    a.push({
        dependencia: "Produto",
        campos: ProdutoModel()
    });
    return a
}

function dependenciaProduto() {
    var a = [];
    a.push({
        dependencia: "MarcaProd",
        campos: MarcaProdModel()
    });
    return a
}

function dependenciaMarcaProd() {
    var a = [];
    a.push({
        dependencia: "Marca",
        campos: MarcaModel()
    });
    return a
}


function dependenciaMarca() {
    var a = [];
    a.push({
        dependencia: "Email",
        campos: EmailModel()
    });
    a.push({
        dependencia: "Endereco",
        campos: EnderecoModel()
    });
    a.push({
        dependencia: "Telefone",
        campos: TelefoneModel()
    });
    return a
}

function dependenciaCusto() {
    var a = [];
    a.push({
        dependencia: "CustoItens",
        campos: CustoItensModel()
    });
    return a
}

function dependenciaPorcao() {
    var a = [];
    a.push({
        dependencia: "PorcaoItens",
        campos: PorcaoItensModel()
    });
    return a
}

function dependenciaRentabilidade() {
    var a = [];
    a.push({
        dependencia: "RentabilidadeItens",
        campos: RentabilidadeItensModel()
    });
    return a
}




dataModel = function() {

    var oProjet = [];

    oProjet.push({
        classes: [{
            classe: "advogado",
            model: AdvogadoModel()
        }, {
            classe: "PessoaTipo",
            model: PessoaTipoModel(),
        }, {
            classe: "Pessoa",
            model: PessoaModel(),
            dependencias: dependenciaCliente()
        }, {
            classe: "Cliente",
            model: PessoaModel(),
            dependencias: dependenciaCliente()
        }, {
            classe: "Fornecedor",
            model: PessoaModel(),
            dependencias: dependenciaFornecedor()
        }, {
            classe: "Transportador",
            model: PessoaModel(),
            dependencias: dependenciaTransportador()
        }, {
            classe: "Medico",
            model: PessoaModel(),
            dependencias: dependenciaMedico()
        }, {
            classe: "Paciente",
            model: PessoaModel(),
            dependencias: dependenciaPaciente()
        }, {
            classe: "Sindico",
            model: PessoaModel(),
            dependencias: dependenciaSindico()
        }, {
            classe: "Inquilino",
            model: PessoaModel(),
            dependencias: dependenciaInquilino()
        }, {
            classe: "Funcionario",
            model: PessoaModel(),
            dependencias: dependenciaFuncionario()
        }],
        interfaces: "Pessoa",
        local: "Pessoa"
    })


    oProjet.push({
        classes: [{
            classe: "processoStatus",
            model: ProcessoStatusModel()
        }, {
            classe: "diasHoras",
            model: DiasHorasModel()
        }, {
            classe: "Especialidade",
            model: EspecialidadeModel()
        }, {
            classe: "Compromisso",
            model: CompromissoModel()
        }, {
            classe: "advogados",
            model: AdvogadosModel()
        }, {
            classe: "Envolvidos",
            model: EnvolvidosModel()
        }, {
            classe: "ParticipanteExterno",
            model: ParticipanteExternoModel()
        }, {
            classe: "Processo",
            model: ProcessModel()
        }, {
            classe: "ClienteCompromisso",
            model: ClienteCompromissoModel()
        }, {
            classe: "Arquivo",
            model: ArquivoModel()
        }, {
            classe: "ProcessoUsuarios",
            model: ProcessoUsuariosModel()
        }, {
            classe: "ProcessoCliente",
            model: ProcessoClienteModel()
        }, {
            classe: "DiasHoras",
            model: DiasHorasModel()
        }],
        doisValor: { "nome": "processo", "data": DVProcesso() },
        interfaces: "Advogado",
        local: "Advocacia"
    })
    oProjet.push({
        classes: [{
            classe: "Convenio",
            model: ConvenioModel()
        }, {
            classe: "Cidade",
            model: CidadeModel()
        }, {
            classe: "Estado",
            model: EstadoModel()
        }, {
            classe: "Tarefa",
            model: TarefaModel()
        }],
        interfaces: "Cadastros",
        local: "Cadastros"
    })

    oProjet.push({
        classes: [{
            classe: "Consulta",
            model: ConsultaModel()
        }, {
            classe: "Exame",
            model: ExameModel()
        }],
        interfaces: "Clinica",
        local: "Clinica"
    })

    oProjet.push({
        classes: [{
            classe: "NotaFiscalEntrada",
            model: NotaFiscalModel()
        }, {
            classe: "PedidoCompras",
            model: PedidoComprasModel()
        }, {
            classe: "Cotacao",
            model: CotacaoModel()
        }],
        interfaces: "Compras",
        local: "Compras"
    })

    oProjet.push({
        classes: [{
            classe: "Avisos",
            model: AvisosModel()
        }],
        interfaces: "Condominio",
        local: "Condominio"
    })

    oProjet.push({
        classes: [{
            classe: "Eventos",
            model: EventosModel()
        }, {
            classe: "Beneficios",
            model: BeneficiosModel()
        }, {
            classe: "HoraFunc",
            model: HoraFuncModel()
        }],
        interfaces: "Dp",
        local: "Dp"
    })

    oProjet.push({
        classes: [{
            classe: "Empresa",
            model: EmpresaModel(),
            dependencias: dependenciaEmpresa()
        }, {
            classe: "Entidade",
            model: EmpresaModel(),
            dependencias: dependenciaEmpresa()
        }, {
            classe: "Transaction",
            model: TransactionModel(),
        }, {
            classe: "Filial",
            model: EmpresaModel(),
            dependencias: dependenciaFilial()
        }, {
            classe: "Deposito",
            model: EmpresaModel(),
            dependencias: dependenciaDeposito()
        }, {
            classe: "Usuario",
            model: UsuarioModel(),
            dependencias: dependenciaUsuario()
        }, {
            classe: "Users",
            model: UsuarioModel(),
            dependencias: dependenciaUsuario()
        }, {
            classe: "User_Roles",
            model: user_rolesModel()
        }, {
            classe: "Advocacia",
            model: AdvocaciaModel(),
            dependencias: dependenciaAdvocacia()
        }, {
            classe: "Clinica",
            model: ClinicaModel(),
            dependencias: dependenciaClinica()
        }, {
            classe: "Condominio",
            model: CondominioModel(),
            dependencias: dependenciaCondominio()
        }],
        interfaces: "Empresa",
        local: "Empresa"
    })



    oProjet.push({
        classes: [{
            classe: "NFNota",
            model: NFNota()
        }, {
            classe: "NFNotaInfo",
            model: NFNotaInfo()
        }, {
            classe: "NFInfoCupomFiscalReferenciado",
            model: NFInfoCupomFiscalReferenciado()
        }, {
            classe: "NFNotaInfoIdentificacao",
            model: NFNotaInfoIdentificacao()
        }, {
            classe: "NFInfoModelo1Por1AReferenciada",
            model: NFInfoModelo1Por1AReferenciada()
        }, {
            classe: "NFInfoReferenciada",
            model: NFInfoReferenciada()
        }, {
            classe: "NFInfoProdutorRuralReferenciada",
            model: NFInfoProdutorRuralReferenciada()
        }, {
            classe: "NFNotaInfoEmitente",
            model: NFNotaInfoEmitente()
        }, {
            classe: "NFNotaInfoAvulsa",
            model: NFNotaInfoAvulsa()
        }, {
            classe: "NFNotaInfoDestinatario",
            model: NFNotaInfoDestinatario()
        }, {
            classe: "NFNotaInfoLocal",
            model: NFNotaInfoLocal()
        }, {
            classe: "NFPessoaAutorizadaDownloadNFe",
            model: NFPessoaAutorizadaDownloadNFe()
        }, {
            classe: "NFNotaInfoTotal",
            model: NFNotaInfoTotal()
        }, {
            classe: "NFNotaInfoICMSTotal",
            model: NFNotaInfoICMSTotal()
        }, {
            classe: "NFNotaInfoISSQNTotal",
            model: NFNotaInfoISSQNTotal()
        }, {
            classe: "NFNotaInfoRetencoesTributos",
            model: NFNotaInfoRetencoesTributos()
        }, {
            classe: "NFNotaInfoTransporte",
            model: NFNotaInfoTransporte()
        }, {
            classe: "NFNotaInfoRetencaoICMSTransporte",
            model: NFNotaInfoRetencaoICMSTransporte()
        }, {
            classe: "NFNotaInfoTransportador",
            model: NFNotaInfoTransportador()
        }, {
            classe: "NFNotaInfoVeiculo",
            model: NFNotaInfoVeiculo()
        }, {
            classe: "NFNotaInfoReboque",
            model: NFNotaInfoReboque()
        }, {
            classe: "NFNotaInfoCobranca",
            model: NFNotaInfoCobranca()
        }, {
            classe: "NFNotaInfoDuplicata",
            model: NFNotaInfoDuplicata()
        }, {
            classe: "NFNotaInfoFatura",
            model: NFNotaInfoFatura()
        }, {
            classe: "NFNotaInfoCartao",
            model: NFNotaInfoCartao()
        }, {
            classe: "NFNotaInfoPagamento",
            model: NFNotaInfoPagamento()
        }, {
            classe: "NFNotaInfoInformacoesAdicionais",
            model: NFNotaInfoInformacoesAdicionais()
        }, {
            classe: "NFNotaInfoObservacao",
            model: NFNotaInfoObservacao()
        }, {
            classe: "NFNotaInfoProcessoReferenciado",
            model: NFNotaInfoProcessoReferenciado()
        }, {
            classe: "NFNotaInfoExportacao",
            model: NFNotaInfoExportacao()
        }, {
            classe: "NFNotaInfoCompra",
            model: NFNotaInfoCompra()
        }, {
            classe: "NFNotaInfoCana",
            model: NFNotaInfoCana()
        }, {
            classe: "NFNotaInfoCanaFornecimentoDiario",
            model: NFNotaInfoCanaFornecimentoDiario()
        }, {
            classe: "NFNotaInfoCanaDeducao",
            model: NFNotaInfoCanaDeducao()
        }, {
            classe: "NFNotaInfoSuplementar",
            model: NFNotaInfoSuplementar()
        }],
        interfaces: "NFe",
        local: "NFe"
    })

    oProjet.push({
        classes: [{
            classe: "NFNotaInfoItem",
            model: NFNotaInfoItem(),
        }, {
            classe: "NFNotaInfoItemProduto",
            model: NFNotaInfoItemProduto()
        }, {
            classe: "NFNotaInfoItemProdutoDeclaracaoImportacao",
            model: NFNotaInfoItemProdutoDeclaracaoImportacao()
        }, {
            classe: "NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao",
            model: NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao()
        }, {
            classe: "NFNotaInfoItemDetalheExportacao",
            model: NFNotaInfoItemDetalheExportacao()
        }, {
            classe: "NFNotaInfoItemExportacaoIndireta",
            model: NFNotaInfoItemExportacaoIndireta()
        }, {
            classe: "NFNotaInfoItemProdutoVeiculo",
            model: NFNotaInfoItemProdutoVeiculo()
        }, {
            classe: "NFNotaInfoItemProdutoMedicamento",
            model: NFNotaInfoItemProdutoMedicamento()
        }, {
            classe: "NFNotaInfoItemProdutoArmamento",
            model: NFNotaInfoItemProdutoArmamento()
        }, {
            classe: "NFNotaInfoItemProdutoCombustivel",
            model: NFNotaInfoItemProdutoCombustivel()
        }, {
            classe: "NFNotaInfoItemProdutoCombustivelCIDE",
            model: NFNotaInfoItemProdutoCombustivelCIDE()
        }, {
            classe: "NFImpostoDevolvido",
            model: NFImpostoDevolvido()
        }, {
            classe: "NFInformacaoImpostoDevolvido",
            model: NFInformacaoImpostoDevolvido()
        }, {
            classe: "NFNotaInfoItemImposto",
            model: NFNotaInfoItemImposto()
        }, {
            classe: "NFNotaInfoItemImpostoICMS",
            model: NFNotaInfoItemImpostoICMS()
        }, {
            classe: "NFNotaInfoItemImpostoICMS00",
            model: NFNotaInfoItemImpostoICMS00()
        }, {
            classe: "NFNotaInfoItemImpostoICMS10",
            model: NFNotaInfoItemImpostoICMS10()
        }, {
            classe: "NFNotaInfoItemImpostoICMS20",
            model: NFNotaInfoItemImpostoICMS20()
        }, {
            classe: "NFNotaInfoItemImpostoICMS30",
            model: NFNotaInfoItemImpostoICMS30()
        }, {
            classe: "NFNotaInfoItemImpostoICMS40",
            model: NFNotaInfoItemImpostoICMS40()
        }, {
            classe: "NFNotaInfoItemImpostoICMS51",
            model: NFNotaInfoItemImpostoICMS51()
        }, {
            classe: "NFNotaInfoItemImpostoICMS60",
            model: NFNotaInfoItemImpostoICMS60()
        }, {
            classe: "NFNotaInfoItemImpostoICMS70",
            model: NFNotaInfoItemImpostoICMS70()
        }, {
            classe: "NFNotaInfoItemImpostoICMS90",
            model: NFNotaInfoItemImpostoICMS90()
        }, {
            classe: "NFNotaInfoItemImpostoICMSPartilhado",
            model: NFNotaInfoItemImpostoICMSPartilhado()
        }, {
            classe: "NFNotaInfoItemImpostoICMSST",
            model: NFNotaInfoItemImpostoICMSST()
        }, {
            classe: "NFNotaInfoItemImpostoICMSSN101",
            model: NFNotaInfoItemImpostoICMSSN101()
        }, {
            classe: "NFNotaInfoItemImpostoICMSSN102",
            model: NFNotaInfoItemImpostoICMSSN102()
        }, {
            classe: "NFNotaInfoItemImpostoICMSSN201",
            model: NFNotaInfoItemImpostoICMSSN201()
        }, {
            classe: "NFNotaInfoItemImpostoICMSSN202",
            model: NFNotaInfoItemImpostoICMSSN202()
        }, {
            classe: "NFNotaInfoItemImpostoICMSSN500",
            model: NFNotaInfoItemImpostoICMSSN500()
        }, {
            classe: "NFNotaInfoItemImpostoICMSSN900",
            model: NFNotaInfoItemImpostoICMSSN900()
        }, {
            classe: "NFNotaInfoItemImpostoIPI",
            model: NFNotaInfoItemImpostoIPI()
        }, {
            classe: "NFNotaInfoItemImpostoIPITributado",
            model: NFNotaInfoItemImpostoIPITributado()
        }, {
            classe: "NFNotaInfoItemImpostoIPINaoTributado",
            model: NFNotaInfoItemImpostoIPINaoTributado()
        }, {
            classe: "NFNotaInfoItemImpostoImportacao",
            model: NFNotaInfoItemImpostoImportacao()
        }, {
            classe: "NFNotaInfoItemImpostoISSQN",
            model: NFNotaInfoItemImpostoISSQN()
        }, {
            classe: "NFNotaInfoItemImpostoPIS",
            model: NFNotaInfoItemImpostoPIS()
        }, {
            classe: "NFNotaInfoItemImpostoPISAliquota",
            model: NFNotaInfoItemImpostoPISAliquota()
        }, {
            classe: "NFNotaInfoItemImpostoPISQuantidade",
            model: NFNotaInfoItemImpostoPISQuantidade()
        }, {
            classe: "NFNotaInfoItemImpostoPISNaoTributado",
            model: NFNotaInfoItemImpostoPISNaoTributado()
        }, {
            classe: "NFNotaInfoItemImpostoPISOutrasOperacoes",
            model: NFNotaInfoItemImpostoPISOutrasOperacoes()
        }, {
            classe: "NFNotaInfoItemImpostoPISST",
            model: NFNotaInfoItemImpostoPISST()
        }, {
            classe: "NFNotaInfoItemImpostoCOFINS",
            model: NFNotaInfoItemImpostoCOFINS()
        }, {
            classe: "NFNotaInfoItemImpostoCOFINSAliquota",
            model: NFNotaInfoItemImpostoCOFINSAliquota()
        }, {
            classe: "NFNotaInfoItemImpostoCOFINSQuantidade",
            model: NFNotaInfoItemImpostoCOFINSQuantidade()
        }, {
            classe: "NFNotaInfoItemImpostoCOFINSNaoTributavel",
            model: NFNotaInfoItemImpostoCOFINSNaoTributavel()
        }, {
            classe: "NFNotaInfoItemImpostoCOFINSOutrasOperacoes",
            model: NFNotaInfoItemImpostoCOFINSOutrasOperacoes()
        }, {
            classe: "NFNotaInfoItemImpostoCOFINSST",
            model: NFNotaInfoItemImpostoCOFINSST()
        }, {
            classe: "NFNotaInfoItemImpostoICMSUFDestino",
            model: NFNotaInfoItemImpostoICMSUFDestino()
        }, {
            classe: "NFImpostoDevolvido",
            model: NFImpostoDevolvido()
        }, {
            classe: "NFInformacaoImpostoDevolvido",
            model: NFInformacaoImpostoDevolvido()
        }],
        interfaces: "NFNotaInfoItem",
        local: "NFNotaInfoItem"
    })

    oProjet.push({
        classes: [{
            classe: "ContasPagar",
            model: ContasPagarModel(),
            dependencias: dependenciaContasPagar()
        }, {
            classe: "Titulo",
            model: TituloModel(),
            dependencias: dependenciaTitulo()
        }, {
            classe: "BaixaTitulo",
            model: BaixaTituloModel()
        }, {
            classe: "TipoBaixa",
            model: TipoBaixaModel()
        }, {
            classe: "ContasReceber",
            model: ContasReceberModel(),
            dependencias: dependenciaContasReceber()
        }, {
            classe: "CondPag",
            model: CondPagModel()
        }, {
            classe: "FormaPg",
            model: FormaPgModel()
        }, {
            classe: "Banco",
            model: BancoModel()
        }, {
            classe: "ContaCorrente",
            model: ContaCorrenteModel() //ContaModel
        }, {
            classe: "Caixa",
            model: CaixaModel()
        }, {
            classe: "Conta",
            model: ContaModel()
        }],
        interfaces: "Financeiro",
        local: "Financeiro"
    })

    oProjet.push({
        classes: [{
            classe: "Regime",
            model: RegimeModel()
        }, {
            classe: "Cfop",
            model: CfopModel()
        }, {
            classe: "Cnae",
            model: CnaeModel()
        }, {
            classe: "CnaeEmpresa",
            model: CnaeEmpresaModel()
        }],
        interfaces: "Fiscal",
        local: "Fiscal"
    })

    oProjet.push({
            classes: [{
                classe: "ProdutoEmpresa",
                model: ProdutoEmpresaModel(),
                dependencias: dependenciaProdutoParent()
            }, {
                classe: "Produto",
                model: ProdutoModel(),
                dependencias: dependenciaProduto()
            }, {
                classe: "Cfop",
                model: CfopModel()
            }, {
                classe: "Marca",
                model: MarcaModel(),
                dependencias: dependenciaMarca()
            }, {
                classe: "MarcaProduto",
                model: MarcaProdutoModel()
            }, {
                classe: "Grupo",
                model: GrupoModel()
            }, {
                classe: "SubGrupo",
                model: SubGrupoModel()
            }, {
                classe: "UniMed",
                model: UniMedModel()
            }, {
                classe: "Tributacao",
                model: TributacaoModel()
            }, {
                classe: "Icms",
                model: ICMSModel()
            }, {
                classe: "Pis",
                model: PISModel()
            }, {
                classe: "Ipi",
                model: IPIModel()
            }, {
                classe: "Cofins",
                model: COFINSModel()
            }, {
                classe: "ICMSOpInter",
                model: ICMSOpInterModel()
            }, { // ICMSOpInter
                classe: "Custo",
                model: CustoModel(),
                dependencias: dependenciaCusto()
            }, {
                classe: "CustoItens",
                model: CustoItensModel()
            }, {
                classe: "Estoque",
                model: EstoqueModel()
            }, {
                classe: "Porcao",
                model: PorcaoModel(),
                dependencias: dependenciaPorcao()
            }, {
                classe: "PorcaoItens",
                model: PorcaoItensModel()
            }, {
                classe: "Rentabilidade",
                model: RentabilidadeModel(),
                dependencias: dependenciaRentabilidade()
            }, {
                classe: "RentabilidadeItens",
                model: RentabilidadeItensModel()
            }, {
                classe: "Categoria",
                model: CategoriaModel()
            }],
            interfaces: "Produto",
            local: "Produto"
        })
        //ProdutoEmpresaModel
    oProjet.push({
        classes: [{
            classe: "ProdutoEmpresa",
            model: ProdutoEmpresaModel()
        }, {
            classe: "Servico",
            model: ServicoModel(),
            dependencias: dependenciaServico()
        }, {
            classe: "PlanoByServico",
            model: PlanoByServicoModel(),
            dependencias: dependenciaServico()
        }, {
            classe: "Site",
            model: SiteModel(),
            dependencias: dependenciaSite()
        }, {
            classe: "Contato",
            model: ContatoModel(),
            dependencias: dependenciaContato()
        }, {
            classe: "ContatoItens",
            model: ContatoItensModel()
        }, {
            classe: "OrdemServico",
            model: OrdemServicoModel(),
            dependencias: dependenciaOrdemServico()
        }, {
            classe: "OrdemServicoItens",
            model: OrdemServicoItensModel()
        }, {
            classe: "Plano",
            model: PlanoModel()
        }, {
            classe: "PlanoByEmpresa",
            model: PlanoByEmpresaModel()
        }, {
            classe: "ServicoAndPlano",
            model: ServicoAndPlanoModel()
        }],
        interfaces: "Site",
        local: "Site"
    })

    oProjet.push({
            classes: [{
                    classe: "NotaFiscal",
                    model: NotaFiscalModel()
                }, {
                    classe: "NotaFiscalSaida",
                    model: NotaFiscalModel()
                },
                {
                    classe: "NotaFiscalEntrada",
                    model: NotaFiscalModel()
                }, {
                    classe: "Orcamento",
                    model: OrcamentoModel()
                }, {
                    classe: "OrdemServico",
                    model: OrdemServicoModel()
                }, {
                    classe: "ConhecimentoTransporte",
                    model: ConhecimentoTransporteModel()
                }, {
                    classe: "NotaFiscalItens",
                    model: NotaFiscalItensModel()
                }
            ],
            interfaces: "Vendas",
            local: "Vendas"
        })
        //============== DACD
    oProjet.push({
        classes: [{
            classe: "Endereco",
            model: EnderecoModel()
        }],
        interfaces: "Endereco",
        local: "Endereco"
    })

    oProjet.push({
        classes: [{
            classe: "Email",
            model: EmailModel()
        }],
        interfaces: "Email",
        local: "Email"
    })

    oProjet.push({
        classes: [{
            classe: "Telefone",
            model: TelefoneModel()
        }],
        interfaces: "Telefone",
        local: "Telefone"
    })

    oProjet.push({
        classes: [{
            classe: "Arquivo",
            model: ArquivoModel()
        }],
        interfaces: "Arquivo",
        local: "Arquivo"
    })

    oProjet.push({
        classes: [{
            classe: "Classificacao",
            model: ClassificacaoModel()
        }],
        interfaces: "Classificacao",
        local: "Classificacao"
    })

    oProjet.push({
        classes: [{
            classe: "Documento",
            model: DocumentosModel()
        }],
        interfaces: "Documento",
        local: "Documento"
    })

    oProjet.push({
        classes: [{
            classe: "HistoricoUtil",
            model: HistoricoUtilModel()
        }, {
            classe: "Historico",
            model: HistoricoModel()
        }, {
            classe: "HistoricoItens",
            model: HistoricoItensModel()
        }],
        interfaces: "Historico",
        local: "Historico"
    })

    oProjet.push({
        classes: [{
            classe: "Notes",
            model: NoteModel()
        }],
        interfaces: "Notes",
        local: "Notes"
    })

    oProjet.push({
        classes: [{
            classe: "Socio",
            model: SocioModel()
        }],
        interfaces: "Socios",
        local: "Socios"
    })

    oProjet.push({
        classes: [{
            classe: "OrdemServico",
            model: OrdemServicoModel()
        }, {
            classe: "OrdemServicoType",
            model: OrdemServicoTypeModel()
        }, {
            classe: "OrdemServicoStatus",
            model: OrdemServicoStatusModel()
        }, {
            classe: "OrdemServicoItens",
            model: OrdemServicoItensModel()
        }],
        interfaces: "OrdemServico",
        local: "OrdemServico"
    })

    oProjet.push({
        classes: [{
            classe: "Status",
            model: StatusModel()
        }],
        interfaces: "Status",
        local: "Status"
    })

    oProjet.push({
        classes: [{
            classe: "Tributacao",
            model: TributacaoModel()
        }],
        interfaces: "Tributacao",
        local: "Tributacao"
    })

    oProjet.push({
        classes: [{
            classe: "Agencia",
            model: AgenciaModel()
        }],
        interfaces: "Agencia",
        local: "Agencia"
    })

    oProjet.push({
        classes: [{
            classe: "Profissao",
            model: ProfissaoModel()
        }],
        interfaces: "Profissao",
        local: "Profissao"
    })

    oProjet.push({
        classes: [{
            classe: "Salario",
            model: SalarioModel()
        }],
        interfaces: "Salario",
        local: "Salario"
    })

    oProjet.push({
        classes: [{
            classe: "Custo",
            model: CustoModel()
        }],
        interfaces: "Custo",
        local: "Custo"
    })

    oProjet.push({
        classes: [{
            classe: "Estoque",
            model: EstoqueModel()
        }],
        interfaces: "Estoque",
        local: "Estoque"
    })

    oProjet.push({
        classes: [{
            classe: "Porcao",
            model: PorcaoModel()
        }],
        interfaces: "Porcao",
        local: "Porcao"
    })

    oProjet.push({
        classes: [{
            classe: "Preco",
            model: PrecoModel()
        }],
        interfaces: "Preco",
        local: "Preco"
    })

    oProjet.push({
        classes: [{
            classe: "DoisValor",
            model: DoisValorModel()
        }, {
            classe: "DoisValorType",
            model: DoisValorTypeModel()
        }],
        interfaces: "DoisValor",
        local: "DoisValor"
    })

    oProjet.push({
        classes: [{
            classe: "Configuracao",
            model: ConfiguracaoModel(),
            dependencias: dependenciaConf()
        }, {
            classe: "Boleto",
            model: BoletoModel()
        }, {
            classe: "ConfigCarne",
            model: CarneModel()
        }, {
            classe: "ConfigEntrada",
            model: EntradaModel()
        }, {
            classe: "ConfigFiscal",
            model: FiscalModel()
        }, {
            classe: "ConfigAlertas",
            model: AlertasModel()
        }, {
            classe: "ConfigGeral",
            model: GeralModel()
        }, {
            classe: "ConfigProduto",
            model: ConfProdutoModel()
        }, {
            classe: "ConfigSMTP",
            model: SMTPModel()
        }, {
            classe: "ConfiguracaoNFe",
            model: ConfNFEModel()
        }, {
            classe: "ConfigVendas",
            model: VendasModel()
        }, {
            classe: "ConfigOS",
            model: ConfigOSModel()
        }, {
            classe: "ConfAlertas",
            model: AlertasModel()
        }, {
            classe: "ConfBlueSoft",
            model: ConfigBlueSoftModel()
        }],
        interfaces: "Configuracao",
        local: "Configuracao"
    })

    oProjet.push({
        classes: [{
            classe: "Rentabilidade",
            model: RentabilidadeModel()
        }],
        interfaces: "Rentabilidade",
        local: "Rentabilidade"
    })

    oProjet.push({
        classes: [{
            classe: "Servico",
            model: ServicoModel()
        }],
        interfaces: "Servico",
        local: "Servico"
    })

    oProjet.push({
        classes: [{
            classe: "GroupMenu",
            model: GroupMenuModel()
        }, {
            classe: "CategoriaMenu",
            model: CategoriaMenuModel()
        }, {
            classe: "Menu",
            model: MenuModel()
        }, {
            classe: "Help",
            model: HelpModel()
        }, {
            classe: "Pagina",
            model: PaginaModel()
        }, {
            classe: "Tab",
            model: TabModel()
        }, {
            classe: "Entidade",
            model: EntidadeModel()
        }, {
            classe: "Field",
            model: FieldModel()
        }, {
            classe: "Validacao",
            model: ValidacaoModel()
        }, {
            classe: "Dominio",
            model: DominioModel()
        }],
        interfaces: "Dicionario",
        local: "Dicionario"
    })

    return oProjet;
}
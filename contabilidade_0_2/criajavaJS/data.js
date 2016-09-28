

function NFNotaInfo() {
    a = [];
    
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
    a = [];
    
    
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
    a = [];

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
    a = [];


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
    a = [];

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
    a = [];
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
    a = [];
    
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
    a = [];
    
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
    a = [];
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
    a = [];
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
    a = [];
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
	    a = [];
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
	var  a = [];
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
    var 	    a = [];
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
a = [];
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
    a = [];
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
    a = [];
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
    a = [];
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
    a = [];
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
    a = [];
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
    a = [];
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
    a = [];
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
    a = [];
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
    a = [];
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
    a = [];
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
    a = [];
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
    a = [];
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
    a = [];
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
    a = [];
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
    a = [];
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
    a = [];
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
    a = [];
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
    a = [];
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
    a = [];

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
    
       return a;
}


function NFNotaInfoSuplementar () {

 a = [];

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



























//Boleto    
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
            default : 0,
            tipo: "String",
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
            default : 0,
            tipo: "String",
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
            default : 0,
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
function paginaModel() {
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
            campo: "pagina",
            default : "",
            tipo: "String",
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
            default : 0,
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
            campo: "menu",
            default : 0,
            tipo: "Menu",
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
            campo: "filds",
            tipo: "Field",
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
function menuModel() {
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
            default : 0,
            tipo: "String",
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
            default : 0,
            tipo: "String",
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
            default : 0,
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
            default : 0,
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
            default : 0,
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
    return a;
}

//Boleto    
function helpModel() {
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
            default : 0,
            tipo: "String",
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
            default : 0,
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

//Boleto    
function fieldModel() {
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
            default : 0,
            tipo: "String",
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
            default : 0,
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
            default : 0,
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
            campo: "validacao",
            tipo: "Validacao",
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
            tipo: "Role",
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
function validacaoModel() {
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
            campo: "tipo",
            default : 0,
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
            default : 0,
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
            default : 0,
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
            default : 0,
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
            default : 0,
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
            default : 0,
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
            default : 0,
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
            default : 0,
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
            default : 0,
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
            default : 0,
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
            default : 0,
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
            default : 0,
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
            default : 0,
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
            default : 0,
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
            default : 0,
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
            default : 0,
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
            default : 0,
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
            default : 0,
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
            default:0,
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
            requerid: true,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "advogadoList",
            tipo: "List<Advogado>",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "clienteList",
            tipo: "List<Cliente>",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "audienciaList",
            tipo: "List<Audiencia>",
            requerid: true,
            primaryKey: true,
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
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "servicoList",
            tipo: "Servico",
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "planoList",
            tipo: "Plano",
            requerid: true,
            primaryKey: true,
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
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "noteLIst",
            tipo: "List<Note>",
            requerid: true,
            primaryKey: true,
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
            forenkey: false,
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

//================ Usuario
function UsuarioModel() {

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
            requerid: true,
            primaryKey: true,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cpf",
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
            campo: "entrada",
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

//===========================================================================================NFE=======================================================================================================
/*
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

*/

//======================================================================================================================================================================================================

//================ Produto
//================================================================================================================================================================================= Produto =====================================================
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
            requerid: true,
            primaryKey: true,
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
            campo: "estoque",
            tipo: "EstoqueList",
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
            tipo: "CustoList",
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
            tipo: "PrecoList",
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
            campo: "prodId",
            tipo: "Produto",
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
            campo: "iCMS",
            tipo: "Icms",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "pIS",
            tipo: "Pis",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "cOFINS",
            tipo: "Cofins",
            requerid: false,
            primaryKey: false,
            forenkey: false,
            model: true,
            xml: true
        }
    });
    a.push({
        field: {
            campo: "iPI",
            tipo: "IPI",
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
            xml: true
        }
    });
    a.push({
        field: {
            campo: "plano",
            tipo: "Plano",
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
            campo: "pais",
            tipo: "String",
            requerid: true,
            primaryKey: true,
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
            tipo: "String",
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
            tipo: "String",
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
            campo: "docId",
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
            campo: "Valor",
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
            xml: true
        }
    });
    a.push({
        field: {
            campo: "ledo",
            tipo: "Integer",
            requerid: true,
            defauld : 0,
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
            defauld : 0,
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
function ContatoItensModel() {

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

function EmpresaModel() {

    var a = [];
    a = EntidadeModel();
    /*	a.push({field :{campo : "socios" column="id" select="SocioMap.fetchSocioByEmpresaId"/>
    	a.push({field :{campo : "planoList" column="id" select="PlanoMap.fetchPlanoByEmpresa"/>
    	a.push({field :{campo : "filialList" column="id" select="FilialMap.fetchAllFilialByEntidade"/>
    	a.push({field :{campo : "depositoList" column="id" select="DepositoMap.fetchAllDepositoByEntidade"/>*/
    return a;
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
            model: PessoaModel()
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
            classe: "audiencia",
            model: AuditoriaModel()
        }, {
            classe: "Processo",
            model: ProcessModel()
        }],
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
            classe: "UserRoles",
            model: user_rolesModel()
        }, {
            classe: "Role",
            model: rolesModel()
        }, {
            classe: "Pagina",
            model: paginaModel()
        }, {
            classe: "Validacao",
            model: validacaoModel()
        }, {
            classe: "Field",
            model: fieldModel()
        }, {
            classe: "Ajuda",
            model: helpModel()
        }, {
            classe: "Menu",
            model: menuModel()
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
            classe: "NFNotaInfoEmitente",
            model: NFNotaInfoEmitente()
        },{
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
            model: ContaCorrenteModel()
        }, {
            classe: "Caixa",
            model: CaixaModel()
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

    oProjet.push({
        classes: [{
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
        },{
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
            },{
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
            }
            , {
                classe: "ConhecimentoTransporte",
                model: ConhecimentoTransporteModel()
            }
            , {
                classe: "NotaFiscalItens",
                model: NotaFiscalItensModel()
            }],
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
        }
        ],
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
            classe: "Classes",
            model: ClasseModel()
        }, {
            classe: "Interface",
            model: InterfaceModel()
        }, {
            classe: "Field",
            model: FieldModel()
        }],
        interfaces: "Dicionario",
        local: "Dicionario"
    })

    return oProjet;
}

INSERT INTO Configuracao(id,  confCabecalho, confGeral, confNFe, confFiscal, confProd, confVendas, confCMTP, confAlertas, confEntrada, confCarne, configOS, confBlueSoft,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
(1001,'confCabecalho_0', 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000,100000,1,1,1,'system',1471371790961,'rod',1471371790961);

INSERT INTO Configuracao(id,  confCabecalho, confGeral, confNFe, confFiscal, confProd, confVendas, confCMTP, confAlertas, confEntrada, confCarne, configOS, confBlueSoft,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
( 1000,'confCabecalho_1', 10001, 10001, 10001, 10001, 10001, 10001, 10001, 10001, 10001, 10001, 10001,100000,1,1,1,'system',1471371794016,'rod',1471371794016);

INSERT INTO Configuracao(id,  confCabecalho, confGeral, confNFe, confFiscal, confProd, confVendas, confCMTP, confAlertas, confEntrada, confCarne, configOS, confBlueSoft,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
( 1002,'confCabecalho_2', 10002, 10002, 10002, 10002, 10002, 10002, 10002, 10002, 10002, 10002, 10002,100000,1,1,1,'system',1471371794981,'rod',1471371794981);

INSERT INTO Boleto( ativarBolOnLine, tipoBoleto, agencia, cedente, juros, tipoCalcMora, mora, instrucoes, demonstrativo, impJuros,create_user,create_date,modify_user,modify_date)values
(10000, 10000, 10000, 10000, 10.00, 10000, 10.00, 'instrucoes_0', 'demonstrativo_0', 10000,'system',1471371796437,'rod',1471371796437);

INSERT INTO Boleto( ativarBolOnLine, tipoBoleto, agencia, cedente, juros, tipoCalcMora, mora, instrucoes, demonstrativo, impJuros,create_user,create_date,modify_user,modify_date)values
( 10001, 10001, 10001, 10001, 10.00, 10001, 10.00, 'instrucoes_1', 'demonstrativo_1', 10001,'system',1471371796437,'rod',1471371796437);

INSERT INTO Boleto( ativarBolOnLine, tipoBoleto, agencia, cedente, juros, tipoCalcMora, mora, instrucoes, demonstrativo, impJuros,create_user,create_date,modify_user,modify_date)values
( 10002, 10002, 10002, 10002, 10.00, 10002, 10.00, 'instrucoes_2', 'demonstrativo_2', 10002,'system',1471371796437,'rod',1471371796437);

INSERT INTO configCarne( carneBotelo, carneNormal, localPag, instr1, instr2, instr3, instr4,create_user,create_date,modify_user,modify_date)values
(10000, 10000, 'localPag_0', 'instr1_0', 'instr2_0', 'instr3_0', 'instr4_0','system',1471371796437,'rod',1471371796437);

INSERT INTO configCarne( carneBotelo, carneNormal, localPag, instr1, instr2, instr3, instr4,create_user,create_date,modify_user,modify_date)values
( 10001, 10001, 'localPag_1', 'instr1_1', 'instr2_1', 'instr3_1', 'instr4_1','system',1471371796437,'rod',1471371796437);

INSERT INTO configCarne( carneBotelo, carneNormal, localPag, instr1, instr2, instr3, instr4,create_user,create_date,modify_user,modify_date)values
( 10002, 10002, 'localPag_2', 'instr1_2', 'instr2_2', 'instr3_2', 'instr4_2','system',1471371796437,'rod',1471371796437);

INSERT INTO configEntrada( valorTotalFixo, manterPrecoVendaProd,create_user,create_date,modify_user,modify_date)values
(10000, 10000,'system',1471371796438,'rod',1471371796438);

INSERT INTO configEntrada( valorTotalFixo, manterPrecoVendaProd,create_user,create_date,modify_user,modify_date)values
( 10001, 10001,'system',1471371796438,'rod',1471371796438);

INSERT INTO configEntrada( valorTotalFixo, manterPrecoVendaProd,create_user,create_date,modify_user,modify_date)values
( 10002, 10002,'system',1471371796438,'rod',1471371796438);

INSERT INTO configFiscal( princAtividade, regime, aliqSimples, aliqICMS, aliqPIS, aliqCONFINS, aliqIRPJ, aliqCLSS,create_user,create_date,modify_user,modify_date)values
(10000, 10000, 10.00, 10.00, 10.00, 10.00, 10.00, 10.00,'system',1471371796438,'rod',1471371796438);

INSERT INTO configFiscal( princAtividade, regime, aliqSimples, aliqICMS, aliqPIS, aliqCONFINS, aliqIRPJ, aliqCLSS,create_user,create_date,modify_user,modify_date)values
( 10001, 10001, 10.00, 10.00, 10.00, 10.00, 10.00, 10.00,'system',1471371796438,'rod',1471371796438);

INSERT INTO configFiscal( princAtividade, regime, aliqSimples, aliqICMS, aliqPIS, aliqCONFINS, aliqIRPJ, aliqCLSS,create_user,create_date,modify_user,modify_date)values
( 10002, 10002, 10.00, 10.00, 10.00, 10.00, 10.00, 10.00,'system',1471371796438,'rod',1471371796438);

INSERT INTO configAlertas( estoqMin, estoqMax, erroNFe, pdCompra, nvCliente, retCaixa,create_user,create_date,modify_user,modify_date)values
(10000, 10000, 10000, 10000, 10000, 10000,'system',1471371796438,'rod',1471371796438);

INSERT INTO configAlertas( estoqMin, estoqMax, erroNFe, pdCompra, nvCliente, retCaixa,create_user,create_date,modify_user,modify_date)values
( 10001, 10001, 10001, 10001, 10001, 10001,'system',1471371796438,'rod',1471371796438);

INSERT INTO configAlertas( estoqMin, estoqMax, erroNFe, pdCompra, nvCliente, retCaixa,create_user,create_date,modify_user,modify_date)values
( 10002, 10002, 10002, 10002, 10002, 10002,'system',1471371796438,'rod',1471371796438);

INSERT INTO configGeral( fusoHorario, casasDecimais, diasCartaCobr, infPosicionarMouse, cnpjCPFUnico, impCodPersonalizado, logListRelImp, obsProdFinProd, ativNFCe,create_user,create_date,modify_user,modify_date)values
(10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000,'system',1471371796438,'rod',1471371796438);

INSERT INTO configGeral( fusoHorario, casasDecimais, diasCartaCobr, infPosicionarMouse, cnpjCPFUnico, impCodPersonalizado, logListRelImp, obsProdFinProd, ativNFCe,create_user,create_date,modify_user,modify_date)values
( 10001, 10001, 10001, 10001, 10001, 10001, 10001, 10001, 10001,'system',1471371796438,'rod',1471371796438);

INSERT INTO configGeral( fusoHorario, casasDecimais, diasCartaCobr, infPosicionarMouse, cnpjCPFUnico, impCodPersonalizado, logListRelImp, obsProdFinProd, ativNFCe,create_user,create_date,modify_user,modify_date)values
( 10002, 10002, 10002, 10002, 10002, 10002, 10002, 10002, 10002,'system',1471371796438,'rod',1471371796438);

INSERT INTO configProduto( cfop, icmsSitTrib, icmsOrigem, icmsModalidadeBC, icmsRedBaseCalc, icmsAliq, icmsMotDesoneracao, icmsModBCST, icmsMargValAdic, icmsRedBaseCalcST, icmsPrecoUnitPautaST, icmsAliqST, ipiSitTrib, ipiClasCigarroBebida, ipiCNPJProd, ipiCodSeloCont, ipiQtdSelo, ipiCodEnquad, ipiTipCalc, ipiAliq, pisSitTrib, pisAliq, pisValUnidtrib, pistipoCalcSubstTrib, pisAliqST, pisValorAliqST, cofinsSubstTrib, cofinsAliq, cofinsValorAliq, cofinsTipoCalcSubstTrib, cofinsAliqST, cofinsValorAliqST,create_user,create_date,modify_user,modify_date)values
(10000, 10000, 10000, 10000, 10.00, 10.00, 10000, 10000, 10.00, 10.00, 10.00, 10.00, 10000, 10.00, 'ipiCNPJProd_0', 'ipiCodSeloCont_0', 10.00, 10000, 10000, 10.00, 10000, 10.00, 10.00, 10000, 10.00, 10.00, 10000, 10.00, 10.00, 10000, 10.00, 10.00,'system',1471371796438,'rod',1471371796438);

INSERT INTO configProduto( cfop, icmsSitTrib, icmsOrigem, icmsModalidadeBC, icmsRedBaseCalc, icmsAliq, icmsMotDesoneracao, icmsModBCST, icmsMargValAdic, icmsRedBaseCalcST, icmsPrecoUnitPautaST, icmsAliqST, ipiSitTrib, ipiClasCigarroBebida, ipiCNPJProd, ipiCodSeloCont, ipiQtdSelo, ipiCodEnquad, ipiTipCalc, ipiAliq, pisSitTrib, pisAliq, pisValUnidtrib, pistipoCalcSubstTrib, pisAliqST, pisValorAliqST, cofinsSubstTrib, cofinsAliq, cofinsValorAliq, cofinsTipoCalcSubstTrib, cofinsAliqST, cofinsValorAliqST,create_user,create_date,modify_user,modify_date)values
( 10001, 10001, 10001, 10001, 10.00, 10.00, 10001, 10001, 10.00, 10.00, 10.00, 10.00, 10001, 10.00, 'ipiCNPJProd_1', 'ipiCodSeloCont_1', 10.00, 10001, 10001, 10.00, 10001, 10.00, 10.00, 10001, 10.00, 10.00, 10001, 10.00, 10.00, 10001, 10.00, 10.00,'system',1471371796438,'rod',1471371796438);

INSERT INTO configProduto( cfop, icmsSitTrib, icmsOrigem, icmsModalidadeBC, icmsRedBaseCalc, icmsAliq, icmsMotDesoneracao, icmsModBCST, icmsMargValAdic, icmsRedBaseCalcST, icmsPrecoUnitPautaST, icmsAliqST, ipiSitTrib, ipiClasCigarroBebida, ipiCNPJProd, ipiCodSeloCont, ipiQtdSelo, ipiCodEnquad, ipiTipCalc, ipiAliq, pisSitTrib, pisAliq, pisValUnidtrib, pistipoCalcSubstTrib, pisAliqST, pisValorAliqST, cofinsSubstTrib, cofinsAliq, cofinsValorAliq, cofinsTipoCalcSubstTrib, cofinsAliqST, cofinsValorAliqST,create_user,create_date,modify_user,modify_date)values
( 10002, 10002, 10002, 10002, 10.00, 10.00, 10002, 10002, 10.00, 10.00, 10.00, 10.00, 10002, 10.00, 'ipiCNPJProd_2', 'ipiCodSeloCont_2', 10.00, 10002, 10002, 10.00, 10002, 10.00, 10.00, 10002, 10.00, 10.00, 10002, 10.00, 10.00, 10002, 10.00, 10.00,'system',1471371796438,'rod',1471371796438);

INSERT INTO configSMTP( servSMTP, porta, endEmail, usuario, senha, seguranca, ativSMTP,create_user,create_date,modify_user,modify_date)values
('servSMTP_0', 'porta_0', 'endEmail_0', 'usuario_0', 'senha_0', 10000, 10000,'system',1471371796438,'rod',1471371796438);

INSERT INTO configSMTP( servSMTP, porta, endEmail, usuario, senha, seguranca, ativSMTP,create_user,create_date,modify_user,modify_date)values
( 'servSMTP_1', 'porta_1', 'endEmail_1', 'usuario_1', 'senha_1', 10001, 10001,'system',1471371796438,'rod',1471371796438);

INSERT INTO configSMTP( servSMTP, porta, endEmail, usuario, senha, seguranca, ativSMTP,create_user,create_date,modify_user,modify_date)values
( 'servSMTP_2', 'porta_2', 'endEmail_2', 'usuario_2', 'senha_2', 10002, 10002,'system',1471371796438,'rod',1471371796438);

INSERT INTO ConfiguracaoNFE( presCompr, destConsFinal, preencherDataHora, icmsPadrao, ipiPadrao, pisPadrao, cofinsPadrao, ambienteEnvio, servMsmNota, serieEnvio, anexarXmlEmail, idCSC, cSC, informacaoAdd, certificado, senha, salvarSenha, cfopPadrao, tokenNFCe, logoDanfe,create_user,create_date,modify_user,modify_date)values
(10000, 10000, 10000, 10.00, 10.00, 10.00, 10.00, 10000, 10.00, 100, 10000, 'idCSC_0', 'cSC_0', 'informacaoAdd_0', 'certificado_0', 'senha_0', 10000, 10000, 'tokenNFCe_0', 'logoDanfe_0','system',1471371796438,'rod',1471371796438);

INSERT INTO ConfiguracaoNFE( presCompr, destConsFinal, preencherDataHora, icmsPadrao, ipiPadrao, pisPadrao, cofinsPadrao, ambienteEnvio, servMsmNota, serieEnvio, anexarXmlEmail, idCSC, cSC, informacaoAdd, certificado, senha, salvarSenha, cfopPadrao, tokenNFCe, logoDanfe,create_user,create_date,modify_user,modify_date)values
( 10001, 10001, 10001, 10.00, 10.00, 10.00, 10.00, 10001, 10.00, 1000, 10001, 'idCSC_1', 'cSC_1', 'informacaoAdd_1', 'certificado_1', 'senha_1', 10001, 10001, 'tokenNFCe_1', 'logoDanfe_1','system',1471371796438,'rod',1471371796438);

INSERT INTO ConfiguracaoNFE( presCompr, destConsFinal, preencherDataHora, icmsPadrao, ipiPadrao, pisPadrao, cofinsPadrao, ambienteEnvio, servMsmNota, serieEnvio, anexarXmlEmail, idCSC, cSC, informacaoAdd, certificado, senha, salvarSenha, cfopPadrao, tokenNFCe, logoDanfe,create_user,create_date,modify_user,modify_date)values
( 10002, 10002, 10002, 10.00, 10.00, 10.00, 10.00, 10002, 10.00, 1002, 10002, 'idCSC_2', 'cSC_2', 'informacaoAdd_2', 'certificado_2', 'senha_2', 10002, 10002, 'tokenNFCe_2', 'logoDanfe_2','system',1471371796438,'rod',1471371796438);

INSERT INTO configVendas( descontoMaxVenda, observacao, imprSegVia, imprAssinatura, imprResumoFinanc, atuaPrecoClonar, imprColUnidade, bloquearvendProdSemEstoq, addDespCalcImposto, retSubstTribICMS,create_user,create_date,modify_user,modify_date)values
(10.00, 'observacao_0', 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000,'system',1471371796439,'rod',1471371796439);

INSERT INTO configVendas( descontoMaxVenda, observacao, imprSegVia, imprAssinatura, imprResumoFinanc, atuaPrecoClonar, imprColUnidade, bloquearvendProdSemEstoq, addDespCalcImposto, retSubstTribICMS,create_user,create_date,modify_user,modify_date)values
( 10.00, 'observacao_1', 10001, 10001, 10001, 10001, 10001, 10001, 10001, 10001,'system',1471371796439,'rod',1471371796439);

INSERT INTO configVendas( descontoMaxVenda, observacao, imprSegVia, imprAssinatura, imprResumoFinanc, atuaPrecoClonar, imprColUnidade, bloquearvendProdSemEstoq, addDespCalcImposto, retSubstTribICMS,create_user,create_date,modify_user,modify_date)values
( 10.00, 'observacao_2', 10002, 10002, 10002, 10002, 10002, 10002, 10002, 10002,'system',1471371796439,'rod',1471371796439);

INSERT INTO Configuracao( confCabecalho, confGeral, confNFe, confFiscal, confProd, confVendas, confCMTP, confAlertas, confEntrada, confCarne, configOS, confBlueSoft,create_user,create_date,modify_user,modify_date)values
('confCabecalho_0', 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000,'system',1471371796439,'rod',1471371796439);

INSERT INTO Configuracao( confCabecalho, confGeral, confNFe, confFiscal, confProd, confVendas, confCMTP, confAlertas, confEntrada, confCarne, configOS, confBlueSoft,create_user,create_date,modify_user,modify_date)values
( 'confCabecalho_1', 10001, 10001, 10001, 10001, 10001, 10001, 10001, 10001, 10001, 10001, 10001,'system',1471371796439,'rod',1471371796439);

INSERT INTO Configuracao( confCabecalho, confGeral, confNFe, confFiscal, confProd, confVendas, confCMTP, confAlertas, confEntrada, confCarne, configOS, confBlueSoft,create_user,create_date,modify_user,modify_date)values
( 'confCabecalho_2', 10002, 10002, 10002, 10002, 10002, 10002, 10002, 10002, 10002, 10002, 10002,'system',1471371796439,'rod',1471371796439);

INSERT INTO configOS( impr2Via, imprAss, imprResumo, imprDetHorz, diasGarantia, observ,create_user,create_date,modify_user,modify_date)values
(10000, 10000, 10000, 10000, 10000, 'observ_0','system',1471371796439,'rod',1471371796439);

INSERT INTO configOS( impr2Via, imprAss, imprResumo, imprDetHorz, diasGarantia, observ,create_user,create_date,modify_user,modify_date)values
( 10001, 10001, 10001, 10001, 10001, 'observ_1','system',1471371796439,'rod',1471371796439);

INSERT INTO configOS( impr2Via, imprAss, imprResumo, imprDetHorz, diasGarantia, observ,create_user,create_date,modify_user,modify_date)values
( 10002, 10002, 10002, 10002, 10002, 'observ_2','system',1471371796439,'rod',1471371796439);

INSERT INTO confAlertas( estoqMin, estoqMax, erroNFe, pdCompra, nvCliente, retCaixa,create_user,create_date,modify_user,modify_date)values
(10000, 10000, 10000, 10000, 10000, 10000,'system',1471371796439,'rod',1471371796439);

INSERT INTO confAlertas( estoqMin, estoqMax, erroNFe, pdCompra, nvCliente, retCaixa,create_user,create_date,modify_user,modify_date)values
( 10001, 10001, 10001, 10001, 10001, 10001,'system',1471371796439,'rod',1471371796439);

INSERT INTO confAlertas( estoqMin, estoqMax, erroNFe, pdCompra, nvCliente, retCaixa,create_user,create_date,modify_user,modify_date)values
( 10002, 10002, 10002, 10002, 10002, 10002,'system',1471371796439,'rod',1471371796439);

INSERT INTO confBlueSoft( ativBlue, url, token,create_user,create_date,modify_user,modify_date)values
(10000, 'url_0', 'token_0','system',1471371796439,'rod',1471371796439);

INSERT INTO confBlueSoft( ativBlue, url, token,create_user,create_date,modify_user,modify_date)values
( 10001, 'url_1', 'token_1','system',1471371796439,'rod',1471371796439);

INSERT INTO confBlueSoft( ativBlue, url, token,create_user,create_date,modify_user,modify_date)values
( 10002, 'url_2', 'token_2','system',1471371796439,'rod',1471371796439);

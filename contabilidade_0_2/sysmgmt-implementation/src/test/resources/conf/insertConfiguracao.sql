
INSERT INTO Configuracao( id, confGeral, confNFe, confFiscal, confProd, confVendas, confCMTP, confEntrada, confCarne,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
( 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000,10000,1,1,1,'system',1469738374486,'rod',1469738374486);

INSERT INTO Configuracao( id, confGeral, confNFe, confFiscal, confProd, confVendas, confCMTP, confEntrada, confCarne,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
( 1001, 1001, 1001, 1001, 1001, 1001, 1001, 1001, 1001,10000,1,1,1,'system',1469738374486,'rod',1469738374486);

INSERT INTO Configuracao( id, confGeral, confNFe, confFiscal, confProd, confVendas, confCMTP, confEntrada, confCarne,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
( 1002, 1002, 1002, 1002, 1002, 1002, 1002, 1002, 1002,10000,1,1,1,'system',1469738374486,'rod',1469738374486);

INSERT INTO Boleto( id, ativarBolOnLine, tipoBoleto, agencia, cedente, juros, tipoCalcMora, mora, instrucoes, demonstrativo, impJuros,create_user,create_date,modify_user,modify_date)values
( 1000, true, 1000, 1000, 1000, 10.00, 1000, 10.00, 'instrucoes_0', 'demonstrativo_0', true,'system',1469738374486,'rod',1469738374486);

INSERT INTO Boleto( id, ativarBolOnLine, tipoBoleto, agencia, cedente, juros, tipoCalcMora, mora, instrucoes, demonstrativo, impJuros,create_user,create_date,modify_user,modify_date)values
( 1001, true, 1001, 1001, 1001, 10.00, 1001, 10.00, 'instrucoes_1', 'demonstrativo_1', true,'system',1469738374487,'rod',1469738374487);

INSERT INTO Boleto( id, ativarBolOnLine, tipoBoleto, agencia, cedente, juros, tipoCalcMora, mora, instrucoes, demonstrativo, impJuros,create_user,create_date,modify_user,modify_date)values
( 1002, true, 1002, 1002, 1002, 10.00, 1002, 10.00, 'instrucoes_2', 'demonstrativo_2', true,'system',1469738374487,'rod',1469738374487);

INSERT INTO configCarne( id, carneBotelo, carneNormal,create_user,create_date,modify_user,modify_date)values
( 1000, true, true,'system',1469738374487,'rod',1469738374487);

INSERT INTO configCarne( id, carneBotelo, carneNormal,create_user,create_date,modify_user,modify_date)values
( 1001, true, true,'system',1469738374487,'rod',1469738374487);

INSERT INTO configCarne( id, carneBotelo, carneNormal,create_user,create_date,modify_user,modify_date)values
( 1002, true, true,'system',1469738374487,'rod',1469738374487);

INSERT INTO configEntrada( id, valorTotalFixo, manterPrecoVendaProd,create_user,create_date,modify_user,modify_date)values
( 1000, true, true,'system',1469738374487,'rod',1469738374487);

INSERT INTO configEntrada( id, valorTotalFixo, manterPrecoVendaProd,create_user,create_date,modify_user,modify_date)values
( 1001, true, true,'system',1469738374487,'rod',1469738374487);

INSERT INTO configEntrada( id, valorTotalFixo, manterPrecoVendaProd,create_user,create_date,modify_user,modify_date)values
( 1002, true, true,'system',1469738374487,'rod',1469738374487);

INSERT INTO configFiscal( id, princAtividade, regime, aliqSimples,create_user,create_date,modify_user,modify_date)values
( 1000, 1000, 1000, 10.00,'system',1469738374487,'rod',1469738374487);

INSERT INTO configFiscal( id, princAtividade, regime, aliqSimples,create_user,create_date,modify_user,modify_date)values
( 1001, 1001, 1001, 10.00,'system',1469738374487,'rod',1469738374487);

INSERT INTO configFiscal( id, princAtividade, regime, aliqSimples,create_user,create_date,modify_user,modify_date)values
( 1002, 1002, 1002, 10.00,'system',1469738374487,'rod',1469738374487);

INSERT INTO configAlertas( id, estoqMin, estoqMax, erroNFe, pdCompra,create_user,create_date,modify_user,modify_date)values
( 1000, true, true, true, true,'system',1469738374487,'rod',1469738374487);

INSERT INTO configAlertas( id, estoqMin, estoqMax, erroNFe, pdCompra,create_user,create_date,modify_user,modify_date)values
( 1001, true, true, true, true,'system',1469738374487,'rod',1469738374487);

INSERT INTO configAlertas( id, estoqMin, estoqMax, erroNFe, pdCompra,create_user,create_date,modify_user,modify_date)values
( 1002, true, true, true, true,'system',1469738374487,'rod',1469738374487);

INSERT INTO configGeral( id, fusoHorario, casasDecimais, diasCartaCobr, infPosicionarMouse, cnpjCPFUnico, impCodPersonalizado, logListRelImp, obsProdFinProd,create_user,create_date,modify_user,modify_date)values
( 1000, 1000, 1000, 1000, true, true, true, true, true,'system',1469738374487,'rod',1469738374487);

INSERT INTO configGeral( id, fusoHorario, casasDecimais, diasCartaCobr, infPosicionarMouse, cnpjCPFUnico, impCodPersonalizado, logListRelImp, obsProdFinProd,create_user,create_date,modify_user,modify_date)values
( 1001, 1001, 1001, 1001, true, true, true, true, true,'system',1469738374487,'rod',1469738374487);

INSERT INTO configGeral( id, fusoHorario, casasDecimais, diasCartaCobr, infPosicionarMouse, cnpjCPFUnico, impCodPersonalizado, logListRelImp, obsProdFinProd,create_user,create_date,modify_user,modify_date)values
( 1002, 1002, 1002, 1002, true, true, true, true, true,'system',1469738374487,'rod',1469738374487);

INSERT INTO configProduto( id, cfop, icmsSitTrib, icmsOrigem, icmsModalidadeBC, icmsRedBaseCalc, icmsAliq, icmsMotDesoneracao, icmsModBCST, icmsMargValAdic, icmsRedBaseCalcST, icmsPrecoUnitPautaST, icmsAliqST, ipiSitTrib, ipiClasCigarroBebida, ipiCNPJProd, ipiCodSeloCont, ipiQtdSelo, ipiCodEnquad, ipiTipCalc, ipiAliq, pisSitTrib, pisAliq, pisValUnidtrib, pistipoCalcSubstTrib, pisAliqST, pisValorAliqST, cofinsSubstTrib, cofinsAliq, cofinsValorAliq, cofinsTipoCalcSubstTrib, cofinsAliqST, cofinsValorAliqST,create_user,create_date,modify_user,modify_date)values
( 1000, 1000, 1000, 1000, 1000, 10.00, 10.00, 1000, 1000, 10.00, 10.00, 10.00, 10.00, 1000, 10.00, 'ipiCNPJProd_0', 'ipiCodSeloCont_0', 10.00, 1000, 1000, 10.00, 1000, 10.00, 10.00, 1000, 10.00, 10.00, 1000, 10.00, 10.00, 1000, 10.00, 10.00,'system',1469738374487,'rod',1469738374487);

INSERT INTO configProduto( id, cfop, icmsSitTrib, icmsOrigem, icmsModalidadeBC, icmsRedBaseCalc, icmsAliq, icmsMotDesoneracao, icmsModBCST, icmsMargValAdic, icmsRedBaseCalcST, icmsPrecoUnitPautaST, icmsAliqST, ipiSitTrib, ipiClasCigarroBebida, ipiCNPJProd, ipiCodSeloCont, ipiQtdSelo, ipiCodEnquad, ipiTipCalc, ipiAliq, pisSitTrib, pisAliq, pisValUnidtrib, pistipoCalcSubstTrib, pisAliqST, pisValorAliqST, cofinsSubstTrib, cofinsAliq, cofinsValorAliq, cofinsTipoCalcSubstTrib, cofinsAliqST, cofinsValorAliqST,create_user,create_date,modify_user,modify_date)values
( 1001, 1001, 1001, 1001, 1001, 10.00, 10.00, 1001, 1001, 10.00, 10.00, 10.00, 10.00, 1001, 10.00, 'ipiCNPJProd_1', 'ipiCodSeloCont_1', 10.00, 1001, 1001, 10.00, 1001, 10.00, 10.00, 1001, 10.00, 10.00, 1001, 10.00, 10.00, 1001, 10.00, 10.00,'system',1469738374487,'rod',1469738374487);

INSERT INTO configProduto( id, cfop, icmsSitTrib, icmsOrigem, icmsModalidadeBC, icmsRedBaseCalc, icmsAliq, icmsMotDesoneracao, icmsModBCST, icmsMargValAdic, icmsRedBaseCalcST, icmsPrecoUnitPautaST, icmsAliqST, ipiSitTrib, ipiClasCigarroBebida, ipiCNPJProd, ipiCodSeloCont, ipiQtdSelo, ipiCodEnquad, ipiTipCalc, ipiAliq, pisSitTrib, pisAliq, pisValUnidtrib, pistipoCalcSubstTrib, pisAliqST, pisValorAliqST, cofinsSubstTrib, cofinsAliq, cofinsValorAliq, cofinsTipoCalcSubstTrib, cofinsAliqST, cofinsValorAliqST,create_user,create_date,modify_user,modify_date)values
( 1002, 1002, 1002, 1002, 1002, 10.00, 10.00, 1002, 1002, 10.00, 10.00, 10.00, 10.00, 1002, 10.00, 'ipiCNPJProd_2', 'ipiCodSeloCont_2', 10.00, 1002, 1002, 10.00, 1002, 10.00, 10.00, 1002, 10.00, 10.00, 1002, 10.00, 10.00, 1002, 10.00, 10.00,'system',1469738374487,'rod',1469738374487);

INSERT INTO configSMTP( id, servSMTP, porta, endEmail, usuario, senha, seguranca,create_user,create_date,modify_user,modify_date)values
( 1000, 'servSMTP_0', 'porta_0', 'endEmail_0', 'usuario_0', 'senha_0', 1000,'system',1469738374487,'rod',1469738374487);

INSERT INTO configSMTP( id, servSMTP, porta, endEmail, usuario, senha, seguranca,create_user,create_date,modify_user,modify_date)values
( 1001, 'servSMTP_1', 'porta_1', 'endEmail_1', 'usuario_1', 'senha_1', 1001,'system',1469738374487,'rod',1469738374487);

INSERT INTO configSMTP( id, servSMTP, porta, endEmail, usuario, senha, seguranca,create_user,create_date,modify_user,modify_date)values
( 1002, 'servSMTP_2', 'porta_2', 'endEmail_2', 'usuario_2', 'senha_2', 1002,'system',1469738374487,'rod',1469738374487);

INSERT INTO ConfiguracaoNFE( id, presCompr, destConsFinal, preencherDataHora, icmsPadrao, ipiPadrao, pisPadrao, cofinsPadrao, ambienteEnvio, servMsmNota, serieEnvio, anexarXmlEmail, idCSC, cSC, informacaoAdd, certificado, senha, salvarSenha, cfopPadrao, confSMTP,create_user,create_date,modify_user,modify_date)values
( 1000, 1000, true, true, 10.00, 10.00, 10.00, 10.00, 1000, 10.00, 'serieEnvio_0', true, 'idCSC_0', 'cSC_0', 'informacaoAdd_0', 'certificado_0', 'senha_0', true, 1000, 1000,'system',1469738374487,'rod',1469738374487);

INSERT INTO ConfiguracaoNFE( id, presCompr, destConsFinal, preencherDataHora, icmsPadrao, ipiPadrao, pisPadrao, cofinsPadrao, ambienteEnvio, servMsmNota, serieEnvio, anexarXmlEmail, idCSC, cSC, informacaoAdd, certificado, senha, salvarSenha, cfopPadrao, confSMTP,create_user,create_date,modify_user,modify_date)values
( 1001, 1001, true, true, 10.00, 10.00, 10.00, 10.00, 1001, 10.00, 'serieEnvio_1', true, 'idCSC_1', 'cSC_1', 'informacaoAdd_1', 'certificado_1', 'senha_1', true, 1001, 1001,'system',1469738374487,'rod',1469738374487);

INSERT INTO ConfiguracaoNFE( id, presCompr, destConsFinal, preencherDataHora, icmsPadrao, ipiPadrao, pisPadrao, cofinsPadrao, ambienteEnvio, servMsmNota, serieEnvio, anexarXmlEmail, idCSC, cSC, informacaoAdd, certificado, senha, salvarSenha, cfopPadrao, confSMTP,create_user,create_date,modify_user,modify_date)values
( 1002, 1002, true, true, 10.00, 10.00, 10.00, 10.00, 1002, 10.00, 'serieEnvio_2', true, 'idCSC_2', 'cSC_2', 'informacaoAdd_2', 'certificado_2', 'senha_2', true, 1002, 1002,'system',1469738374487,'rod',1469738374487);

INSERT INTO configVendas( id, descontoMaxVenda, observacao, imprSegVia, imprAssinatura, imprResumoFinanc, atuaPrecoClonar, imprColUnidade, bloquearvendProdSemEstoq, addDespCalcImposto, retSubstTribICMS,create_user,create_date,modify_user,modify_date)values
( 1000, 10.00, true, true, true, true, true, true, true, true, true,'system',1469738374488,'rod',1469738374488);

INSERT INTO configVendas( id, descontoMaxVenda, observacao, imprSegVia, imprAssinatura, imprResumoFinanc, atuaPrecoClonar, imprColUnidade, bloquearvendProdSemEstoq, addDespCalcImposto, retSubstTribICMS,create_user,create_date,modify_user,modify_date)values
( 1001, 10.00, true, true, true, true, true, true, true, true, true,'system',1469738374488,'rod',1469738374488);

INSERT INTO configVendas( id, descontoMaxVenda, observacao, imprSegVia, imprAssinatura, imprResumoFinanc, atuaPrecoClonar, imprColUnidade, bloquearvendProdSemEstoq, addDespCalcImposto, retSubstTribICMS,create_user,create_date,modify_user,modify_date)values
( 1002, 10.00, true, true, true, true, true, true, true, true, true,'system',1469738374488,'rod',1469738374488);


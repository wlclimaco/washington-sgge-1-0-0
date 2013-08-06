{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2009  Juliana Tamizou                       }
{                                                                              }
{ Colaboradores nesse arquivo: Isaque Pinheiro                                 }
{                                                                              }
{  Voc� pode obter a �ltima vers�o desse arquivo na pagina do  Projeto ACBr    }
{ Componentes localizado em      http://www.sourceforge.net/projects/acbr      }
{                                                                              }
{  Esta biblioteca � software livre; voc� pode redistribu�-la e/ou modific�-la }
{ sob os termos da Licen�a P�blica Geral Menor do GNU conforme publicada pela  }
{ Free Software Foundation; tanto a vers�o 2.1 da Licen�a, ou (a seu crit�rio) }
{ qualquer vers�o posterior.                                                   }
{                                                                              }
{  Esta biblioteca � distribu�da na expectativa de que seja �til, por�m, SEM   }
{ NENHUMA GARANTIA; nem mesmo a garantia impl�cita de COMERCIABILIDADE OU      }
{ ADEQUA��O A UMA FINALIDADE ESPEC�FICA. Consulte a Licen�a P�blica Geral Menor}
{ do GNU para mais detalhes. (Arquivo LICEN�A.TXT ou LICENSE.TXT)              }
{                                                                              }
{  Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral Menor do GNU junto}
{ com esta biblioteca; se n�o, escreva para a Free Software Foundation, Inc.,  }
{ no endere�o 59 Temple Street, Suite 330, Boston, MA 02111-1307 USA.          }
{ Voc� tamb�m pode obter uma copia da licen�a em:                              }
{ http://www.opensource.org/licenses/lgpl-license.php                          }
{                                                                              }
{ Daniel Sim�es de Almeida  -  daniel@djsystem.com.br  -  www.djsystem.com.br  }
{              Pra�a Anita Costa, 34 - Tatu� - SP - 18270-410                  }
{                                                                              }
{******************************************************************************}

unit ACBrLFDBlocos;

interface

uses
  SysUtils, Classes, DateUtils, ACBrTXTClass, contnrs;

Const
  /// C�digo da Situa��o Tribut�ria referente ao IPI.
  ipiEntradaRecuperacaoCredito = '00' ; // Entrada com recupera��o de cr�dito
  ipiEntradaTributradaZero     = '01' ; // Entrada tributada com al�quota zero
  ipiEntradaIsenta             = '02' ; // Entrada isenta
  ipiEntradaNaoTributada       = '03' ; // Entrada n�o-tributada
  ipiEntradaImune              = '04' ; // Entrada imune
  ipiEntradaComSuspensao       = '05' ; // Entrada com suspens�o
  ipiOutrasEntradas            = '49' ; // Outras entradas
  ipiSaidaTributada            = '50' ; // Sa�da tributada
  ipiSaidaTributadaZero        = '51' ; // Sa�da tributada com al�quota zero
  ipiSaidaIsenta               = '52' ; // Sa�da isenta
  ipiSaidaNaoTributada         = '53' ; // Sa�da n�o-tributada
  ipiSaidaImune                = '54' ; // Sa�da imune
  ipiSaidaComSuspensao         = '55' ; // Sa�da com suspens�o
  ipiOutrasSaidas              = '99' ; // Outras sa�das

type
  /// Indicador de movimento - TOpenBlocos
  TACBrLIndicadorMovimento = (imlComDados, // 0- Bloco com dados informados;
                             imlSemDados  // 1- Bloco sem dados informados.
                             );

  /// C�digo indicador de circula��o
  TACBrIndicadorCirculacao = (icReal, // 0- Movimenta��o (circula��o) real do ite
                              icSimbolica // 1- Movimenta��o (circula��o) simb�lica do item
                              );

  /// Indicador do tipo de totaliza��o do ECF
  TACBrTipoTotalECF = (itDiario, // 0 - Total do dia
                       itMensal // 1 - Total do m�s
                       );

  /// Indicador de observa��es do Mapa-Resumo ECF
  TACBrObsMapaResumo = (ioSemObservacoes, // 0- MR-ECF sem observa��o
                        ioComObservacoes // 1- MR-ECF com observa��o(�es)
                        );

  TACBrDataInventario = (di0, // 0- Levantado no �ltimo dia do ano civil, coincidente com a data do balan�o
                         di1, // 1- Levantado no �ltimo dia do ano civil, divergente da data do balan�o
                         di2, // 2- Levantado na data do balan�o, divergente do �ltimo dia do ano civil
                         di3 // 3- Levantado em data divergente da data do �ltimo balan�o e do �ltimo dia do ano civil
                         );

  TACBrTotalAquisicaoPrestacao = (isSubtotalAquisicaoInterna, // 0- Subtotal das aquisi��es internas
                                  isSubtotalAquisicaoOutros, // 1- Subtotal das aquisi��es de outros munic�pios
                                  isSubtotalAquisicaoExterior, // 2- Subtotal das aquisi��es do exterior
                                  isTotalAquisicoes, // 3- Total das aquisi��es do per�odo
                                  isSubtotalPrestacaoInterna, // 4- Subtotal das presta��es internas
                                  isSubtotalPrestacaoOutros, // 5- Subtotal das presta��es para outros munic�pios
                                  isSubtotalPrestacaoExterior, // 6- Subtotal das presta��es para o exterior
                                  isTotalPrestacoes // 7- Total das presta��es do per�odo
                                  );

  TACBrTipoDeducaoISS = (tdCompensacaoAMaior, // 0- Compensa��o do ISS calculado a maior
                         tdIncentivoCultura, // 1- Benef�cio fiscal por incentivo � cultura
                         tdDecisaoJudicial, // 2- Decis�o administrativa ou judicial
                         tdOutros // 9- Outros
                         );

  /// Indicador do tipo de compensa��o do ISS
  TACBrTipoCompensacaoISS = (ciCancelamento, // 0- Cancelamento de nota fiscal
                             ciGlosaValor, // 1- Glosa de valor faturado
                             ciErroValor, // 2- Erro de preenchimento de valor faturado
                             ciErroValorDeclarado, // 3- Erro de preenchimento de valor faturado
                             ciErroValorDeducao, // 4- Erro de preenchimento de valor de dedu��o da base de c�lculo
                             ciErroValorISSRetido, // 5- Erro de preenchimento de valor de ISS retido
                             ciOutros // 9- Outros
                             );

  /// Indicador de habilita��o profissional
  TACBrHabilitacaoProfissional = (hpHabilitado, // 0- Profissional habilitado
                                  hpNaoHabilitado // 1- Profissional n�o habilitado
                                  );

  /// Indicador de escolaridade
  TACBrEscolaridade = (neSuperior, // 0- N�vel superior
                            neMedio // 1- N�vel m�dio
                            );

  /// Indicador de participa��o societ�ria
  TACBrParticipacaoSocietaria = (psSocio, // 0- S�cio
                                 psNaoSocio // 1- N�o s�cio
                                 );

  {Juliana Tamizou - come�a aqui}
  /// Vers�o do Leiaute do arquivo - TRegistro0000
  TACBrLVersaoLeiaute      = (vlVersao1001,  // C�digo 1001 - Vers�o 1.0.0.1
                             vlVersao1002,  // C�digo 1002 - Vers�o 1.0.0.2
                             vlVersao1003,  // C�digo 1003 - Vers�o 1.0.0.3
                             vlVersao1004,  // C�digo 1004 - Vers�o 1.0.0.4
                             vlVersao1005,  // C�digo 1005 - Vers�o 1.0.0.5
                             vlVersao2000   // C�digo 2000 - Vers�o 2.0.0.0
                             );
  /// C�digo da finalidade do arquivo - TRegistro0000
  TACBrLCodFinalidade      = (ralRegular,                       // 00 - Remessa regular do arquivo original
                             ralSubstituto,                    // 01 - Remessa do arquivo substituto
                             ralDadosAdicionais,               // 02 - Remessa de arquivo com dados adicionais a arquivo anteriormente remetido
                             ralIntimacaoEsp,                  // 03 - Remessa de arquivo requerido por intima��o espec�fica
                             ralCorrecaoIDP,                   // 04 - Remessa de arquivo requerido para corre��o do �ndice de Participa��o dos Munic�pios
                             ralPubDiarioOficial,              // 05 - Remessa de arquivo requerido por ato publicado no Di�rio Oficial
                             ralSintegraRegular,               // 15 - Sintegra - remessa regular de arquivo das opera��es interestaduais
                             ralSintegraSubstituto,            // 16 - Sintegra - remessa de arquivo substituto das opera��es interestaduais
                             ralSintegraDadosAdicionais,       // 17 - Sintegra - remessa de arquivo com dados adicionais das opera��es interestaduais
                             ralSintegraRegularICMSST,         // 18 - Sintegra - remessa regular de arquivo das opera��es interestaduais com substitui��o tribut�ria do ICMS
                             ralSintegraSubstitutoICMSST,      // 19 - Sintegra - remessa de arquivo substituto das opera��es interestaduais com substitui��o tribut�ria do ICMS
                             ralSintegraDadosAdicionaisICMSST, // 20 - Sintegra - remessa de arquivo com dados adicionais das opera��es interestaduais com substitui��o tribut�ria do ICMS
                             ralRegularSefin,                  // 25 - Remessa para a Sefin/Mun de arquivo de reten��es do ISS efetuadas por terceiros
                             ralSubstitutoSefin,               // 26 - Remessa para a Sefin/Mun de arquivo substituto de reten��es do ISS efetuadas por terceiros
                             ralDadosAdicionaisSefin,          // 27 - Remessa para a Sefin/Mun de arquivo com dados adicionais de reten��es do ISS efetuadas por terceiros
                             ralEmissaoDocumento,              // 30 - Emiss�o de documento
                             ralEmissaoDocAvulso,              // 31 - Emiss�o de documento fiscal avulso por reparti��o fiscal
                             ralSolicAuditorFical,             // 61 - Solicita��o de Auditor-Fiscal da Secretaria da Receita Previdenci�ria atrav�s de MPF
                             ralEntregaSecretariaReceita,      // 62 - Entrega na Secretaria da Receita Previdenci�ria - movimento anual de �rg�o p�blico, conforme intima��o
                             ralInfComplementarSefaz           // 90 - Remessa de informa��es complementares para a Sefaz da unidade da federa��o de origem
                             );


  /// Situa��o do Documento
  TACBrlSituacaoDocto = (sdlRegular,                     // 00 - Documento regular
                        sdlExtempRegular,               // 01 - Escritura��o extempor�nea de documento regular
                        sdlCancelado,                   // 02 - Documento cancelado
                        sdlCancelamentoDocAnterior,     // 03 - Cancelamento de cupom fiscal anterior
                        sdlCanceladoExtemp,             // 04 - Escritura��o extempor�nea de documento cancelado
                        sdlDesfazimentoNegocio,         // 05 - Desfazimento de neg�cio
                        sdlDocumentoReferenciado,       // 06 - Documento referenciado
                        sdlRegularSimples,              // 07 - Documento regular - Simples Nacional
                        sdlExtempRegularSimples,        // 08 - Documento regular extempor�neo - Simples Nacional
                        sdlLancDoctoregular,            // 50 - Lan�amento de documento regular
                        sdlLancExtempDoctoRegular,      // 51 - Lan�amento de documento regular extempor�neo
                        sdlLancDoctoCancelado,          // 52 - Lan�amento de documento cancelado
                        sdlLancCancelamentoDocAnterior, // 53 - Lan�amento de cancelamento de cupom fiscal anterior
                        sdlLancCanceladoExtemp,         // 54 - Lan�amento de documento cancelado extempor�neo
                        sdlLancDesfazimentoNegocio,     // 55 - Lan�amento de desfazimento de neg�cio
                        sdlLancDocumentoReferenciado,   // 56 - Lan�amento de documento referenciado
                        sdlLancDoctoOutrasSituacoes,    // 58 - Lan�amento de documento em outras situa��es de repercuss�o nula
                        sdlLancDoctoRepercNevativa      // 59 - Lan�amento de documento com repercuss�o negativa
                        );

  /// Tipo do item � Atividades Industriais, Comerciais e Servi�os:
  TACBrTipoItem = (tiMercadoria,           // 00 � Mercadoria para Revenda
                   tiMateriaPrima,         // 01 � Mat�ria-Prima;
                   tiProdutoIntermediario, // 02 - Produto intermedi�rio;
                   tiProdutoemfabricacao,  // 03 - Produto em fabrica��o;
                   tiProdutoAcabado,       // 04 � Produto Acabado;
                   tiEmbalagem,            // 05 � Embalagem;
                   tiOutras                // 99 � Outras
                   );

  /// Indicador do tipo de opera��o:
  TACBrLTipoOperacao = (tplEntradaAquisicao, // 0 - Entrada
                       tplSaidaPrestacao    // 1 - Sa�da
                       );

  /// Indicador do emitente do documento fiscal
  TACBrlEmitente = (edlEmissaoPropria,         // 0 - Emiss�o pr�pria
                   edlTerceiros               // 1 - Terceiro
                   );

  /// Indicador do tipo de pagamento
  TACBrlTipoPagamento = (tplVista,             // 0 - � Vista
                        tplPrazo              // 1 - A Prazo
                        );

  /// Indicador do tipo do frete
  TACBrTipoFrete = (tflPorContaEmitente,      // 0 - Por conta do emitente
                    tflPorContaDestinatario,  // 1 - Por conta do destinat�rio
                    tflSemIndicacaoFrete,     // 2 - Sem indica��o de frete
                    tflPorContaTerceiros      // 3 - Por conta de terceiros
                    );

  /// Indicador da origem do processo
  TACBrlOrigemProcesso = (oplSefaz,            // 0 - Sefaz
                         oplJusticaFederal,   // 1 - Justi�a Federal
                         oplJusticaEstadual,  // 2 - Justi�a Estadual
                         oplOutros,           // 9 - Outros
                         oplNenhum           // Preencher vazio
                         );
  /// Indicador do tipo de opera��o
  TACBrTipoOperacaoST = (toCombustiveisLubrificantes, // 0 - Combust�veis e Lubrificantes
                         toLeasingVeiculos            // 1 - leasing de ve�culos ou faturamento direto
                         );

  TACBrDoctoArrecada = (daEstadualArrecadacao,  // 0 - Documento Estadual de Arrecada��o
                        daGNRE,                 // 1 - GNRE
                        daMunicipalArrecadacao, // 2 - Documento de arrecada��o municipal
                        daFederalArrecadacao,   // 3 - Documento de arrecada��o federal
                        daOutros                // 9 - Outros: descrever
                        );

  /// Indicador do tipo de transporte
  TACBrTipoTransporte = (ttRodoviario,         // 0 � Rodovi�rio
                         ttFerroviario,        // 1 � Ferrovi�rio
                         ttRodoFerroviario,    // 2 � Rodo-Ferrovi�rio
                         ttAquaviario,         // 3 � Aquavi�rio
                         ttDutoviario,         // 4 � Dutovi�rio
                         ttAereo,              // 5 � A�reo
                         ttOutros              // 9 � Outros
                         );

  /// Indicador do tipo de t�tulo de cr�dito
  TACBrTipoTitulo = (tcDuplicata,             // 00- Duplicata
                     tcCheque,                // 01- Cheque
                     tcPromissoria,           // 02- Promiss�ria
                     tcRecibo,                // 03- Recibo
                     tcOutros                 // 99- Outros (descrever)
                     );

  /// Indicador de tipo de refer�ncia da base de c�lculo do ICMS (ST) do produto farmac�utico
  TACBrTipoBaseMedicamento = (bmCalcTabeladoSugerido,           // 0 - Base de c�lculo referente ao pre�o tabelado ou pre�o m�ximo sugerido;
                              bmCalListNegativa,                // 1 - Base de c�lculo referente � Lista Negativa;
                              bmCalListaPositiva,               // 2 - Base de c�lculo referente � Lista Positiva;
                              bmCalListNeutra                   // 3 - Base de c�lculo referente � Lista Neutra
                              );
  /// Tipo Produto
  TACBrTipoProduto = (tpSimilar,   // 0 - Similar
                      tpGenerico,  // 1 - Gen�rico
                      tpReferencia // 2 - �tico ou de Marca
                      );

  /// Indicador do tipo da arma de fogo
  TACBrTipoArmaFogo = (tafPermitido,     // 0 - Permitido
                       tafRestrito       // 1 - Restrito
                       );

  /// Indicador do tipo de opera��o com ve�culo
  TACBrTipoOperacaoVeiculo = (tovVendaPConcess,   // 0 - Venda para concession�ria
                              tovFaturaDireta,    // 1 - Faturamento direto
                              tovVendaDireta,     // 2 - Venda direta
                              tovVendaOutros      // 9 - Outros
                              );

  /// Indicador do tipo de receita
  TACBrTipoReceita = (trPropria,   // 0 - Receita pr�pria
                      trTerceiro   // 1 - Receita de terceiros
                      );

  /// Indicador do tipo do ve�culo transportador
  TACBrTipoVeiculo = (tvEmbarcacao,
                      tvEmpuradorRebocador
                      );

  /// Indicador do tipo da navega��o
  TACBrTipoNavegacao = (tnInterior,
                        tnCabotagem
                        );

  /// Indicador do tipo de tarifa aplicada:
  TACBrTipoTarifa = (tipExp,     // 0 - Exp
                     tipEnc,     // 1 - Enc
                     tipCI,      // 2 - CI
                     tipOutra    // 9 - Outra
                     );

  /// Indicador da natureza do frete
  TACBrNaturezaFrete = (nfNegociavel,      // 0 - Negociavel
                        nfNaoNegociavel    // 1 - N�o Negociavel
                        );

  /// Indicador do tipo de receita
  TACBrIndTipoReceita = (recServicoPrestado,          // 0 - Receita pr�pria - servi�os prestados;
                         recCobrancaDebitos,          // 1 - Receita pr�pria - cobran�a de d�bitos;
                         recVendaMerc,                // 2 - Receita pr�pria - venda de mercadorias;
                         recServicoPrePago,           // 3 - Receita pr�pria - venda de servi�o pr�-pago;
                         recOutrasProprias,           // 4 - Outras receitas pr�prias;
                         recTerceiroCoFaturamento,    // 5 - Receitas de terceiros (co-faturamento);
                         recTerceiroOutras            // 9 - Outras receitas de terceiros
                         );

  /// Indicador do tipo de servi�o prestado
  TACBrServicoPrestado = (spTelefonia,                // 0- Telefonia;
                          spComunicacaoDados,         // 1- Comunica��o de dados;
                          spTVAssinatura,             // 2- TV por assinatura;
                          spAcessoInternet,           // 3- Provimento de acesso � Internet;
                          spMultimidia,               // 4- Multim�dia;
                          spOutros                    // 9- Outros
                          );

  /// Indicador de movimento
  TACBrlMovimentoST = (mstlSemOperacaoST,   // 0 - Sem opera��es com ST
                      mstlComOperacaoST    // 1 - Com opera��es de ST
                      );

  /// Indicador do tipo de ajuste
  TACBrTipoAjuste = (ajDebito,            // 0 - Ajuste a d�bito;
                     ajCredito            // 1- Ajuste a cr�dito
                     );

  /// Indicador da origem do documento vinculado ao ajuste
  TACBrOrigemDocto = (odPorcessoJudicial, // 0 - Processo Judicial;
                      odProcessoAdminist, // 1 - Processo Administrativo;
                      odPerDcomp,         // 2 - PER/DCOMP;
                      odOutros            //9 � Outros.
                      );

  /// Indicador de propriedade/posse do item
  TACBrlPosseItem = (pilInformante,           // 0- Item de propriedade do informante e em seu poder;
                    pilInformanteNoTerceiro, // 1- Item de propriedade do informante em posse de terceiros;
                    pilTerceiroNoInformante  // 2- Item de propriedade de terceiros em posse do informante
                    );

  /// Identificador de medi��o
  TACBrMedicao = (medAnalogico,            // 0 - anal�gico;
                  medDigital               // 1 � digital
                  );

  /// C�digo de grupo de tens�o
  TACBrGrupoTensao = (gtNenhum,      // '' - Vazio. Para uso quando o documento for cancelado.
                      gtA1,          // 01 - A1 - Alta Tens�o (230kV ou mais)
                      gtA2,          // 02 - A2 - Alta Tens�o (88 a 138kV)
                      gtA3,          // 03 - A3 - Alta Tens�o (69kV)
                      gtA3a,         // 04 - A3a - Alta Tens�o (30kV a 44kV)
                      gtA4,          // 05 - A4 - Alta Tens�o (2,3kV a 25kV)
                      gtAS,          // 06 - AS - Alta Tens�o Subterr�neo 06
                      gtB107,        // 07 - B1 - Residencial 07
                      gtB108,        // 08 - B1 - Residencial Baixa Renda 08
                      gtB209,        // 09 - B2 - Rural 09
                      gtB2Rural,     // 10 - B2 - Cooperativa de Eletrifica��o Rural
                      gtB2Irrigacao, // 11 - B2 - Servi�o P�blico de Irriga��o
                      gtB3,          // 12 - B3 - Demais Classes
                      gtB4a,         // 13 - B4a - Ilumina��o P�blica - rede de distribui��o
                      gtB4b          // 14 - B4b - Ilumina��o P�blica - bulbo de l�mpada
                      );

  /// C�digo de classe de consumo de energia el�trica ou g�s
  TACBrClasseConsumo = (ccComercial,         // 01 - Comercial
                        ccConsumoProprio,    // 02 - Consumo Pr�prio
                        ccIluminacaoPublica, // 03 - Ilumina��o P�blica
                        ccIndustrial,        // 04 - Industrial
                        ccPoderPublico,      // 05 - Poder P�blico
                        ccResidencial,       // 06 - Residencial
                        ccRural,             // 07 - Rural
                        ccServicoPublico     // 08 -Servi�o P�blico
                        );

  /// C�digo de tipo de Liga��o
  TACBrTipoLigacao = (tlNenhum,              // '' - Para uso quando o documento for cancelado
                      tlMonofasico,          // 1 - Monof�sico
                      tlBifasico,            // 2 - Bif�sico
                      tlTrifasico            // 3 - Trif�sico
                      );

  /// C�digo dispositivo autorizado
  TACBrDispositivo = (cdaFormSeguranca,  // 00 - Formul�rio de Seguran�a
                      cdaFSDA,           // 01 - FS-DA � Formul�rio de Seguran�a para Impress�o de DANFE
                      cdaNFe,            // 02 � Formul�rio de seguran�a - NF-e
                      cdaFormContinuo,   // 03 - Formul�rio Cont�nuo
                      cdaBlocos,         // 04 � Blocos
                      cdaJogosSoltos     // 05 - Jogos Soltos
                      );

  /// C�digo do Tipo de Assinante
  TACBrTipoAssinante = (assComercialIndustrial,    // 1 - Comercial/Industrial
                        assPodrPublico,            // 2 - Poder P�blico
                        assResidencial,            // 3 - Residencial/Pessoa f�sica
                        assPublico,                // 4 - P�blico
                        assSemiPublico,            // 5 - Semi-P�blico
                        assOutros                  // 6 - Outros
                        );

  // Indicador de Entrada de Dados
  TACBrTipoEntradaDados = (enDigitacao,      // 0 - Digita��o de dados
                           enImportacaoTXT,  // 1 - Importa��o de arquivo texto
                           enValidacaoTXT    // 2 - Valida��o de arquivo texto
                           );


  // Indicador de  conte�do do arquivo
  TACBrConteudoArquivo = (coDocFiscal,        // 0 - Registros de documento fiscal
                          coEscrFiscal,       // 1 - Lan�amentos de escritura��o fiscal
                          coControlesFiscais, // 2 - Lan�amentos de controles fiscais
                          coInfEconoFiscal,   // 3 - Registros de informa��o econ�mico-fiscal
                          coEscrContabil,     // 4 - Lan�amentos de escritura��o cont�bil
                          coDemoContabeis,    // 5 - Registros de demonstra��es da cont�beis
                          coExtratos          // 6 - Registros de extratos de documentos fiscais ou cont�beis
                          );


  // Indicador de Escritura��o Fiscal
  TACBrTipoEscrFiscal = (esSimplificada,     // 0 - Simplificada
                         esIntermediaria,    // 1 - Intermedi�ria
                         esIntegral,         // 2 - Integral
                         esNaoObrigado       // 3 - N�o obrigado
                         );

  // Indicador de Escritura��o Cont�bil
  TACBrTipoEscrContabil = (ecCompletaDigital,     // 0 - Completa, registrada em arquivo digital
                           ecCompletaPapel,       // 1 - Completa, registrada em papel, microfilme, fichas avulsas, ou fichas/folhas cont�nuas
                           ecSimplificadaDigital, // 2 - Simplificada, registrada em arquivo digital
                           ecSimplificadaPapel,   // 3 - Simplificada, registrada papel, microfilme, fichas avulsas, ou fichas/folhas cont�nuas
                           ecLivroCaixaDigital,   // 4 - Livro Caixa, registrado em arquivo digital
                           ecLivroCaixaPapel,     // 5- Livro Caixa, registrado papel, microfilme, fichas avulsas, ou fichas/folhas cont�nuas
                           ecNaoObrigado          // 6- N�o obrigado
                           );


  TACBrTipoTributacao = (tAliqInformada,    // 0 - Al�quota informada
                         tInicioAtividade,  // 1 - In�cio de atividades (percentual m�nimo)
                         tAliqNaoInformada, // 2 - Al�quota n�o informada (percentual m�ximo)
                         tValorFixo         // 3 - Tributa��o por valor fixo (sem reten��o)
                         );


  TACBrAnexoRT = (aAnexoIII,  // 3 - Anexo III
                  aAnexoIV,   // 4 - Anexo IV
                  aAnexoV     // 5 - Anexo V
                  );

   TACBrAnexoCRD = (aAnexoI,  // 1 - Anexo I
                    aAnexoII  // 2 - Anexo II
                    );


  {Tabela de Benef�cios Fiscais do ICMS}
  TACBrCODBFICMS = (bNenhum,
                    bDF001,  // DF001 Regime Especial para Atacadista - TARE
                    bPE001,  // Programa de Desenvolvimento do Estado de Pernambuco - Prodepe
                    bPE002,  // Programa de Desenvolvimento da Ind�stria Naval e de Mec�nica Pesada Associada do Estado de Pernambuco - Prodinpe
                    bPE003,  // Programa de Apoio �s Empresas de Base Tecnol�gica - Probatec
                    bPE004,  // Programa de Desenvolvimento da Ind�stria de Cal�ados
                    bPE005,  // Centrais de Distribui��o de Supermercados e Lojas de Departamentos
                    pPE006,  // Atacadistas de Alimentos, Bebidas, Produtos de Higiene Pessoal e Limpeza
                    pPE007   // Ind�strias do P�lo T�xtil e de Confec��es
                    );

  {Tabela de Benef�cios Fiscais do ISS}
  TACBrCODBFISS = (bSNenhum,
                   bSDF001   // DF001 Programa de Incentivo � Arte e � Cultura
                    );

  {Indicador de Autera��o}
  TACBrIndAlteracao = (alPerda, // 0 - Perda
                       alGanho  // 1 - Ganho
                       );

  {Indicador de Tipo de Complemento de ICMS}
  TACBrIndCompICMS = ( cSemValor,         // 00- Sem valor de cr�dito do ICMS a complementar;
                       cDifICMSST,        // 01- Complemento relativo � diferen�a do ICMS da substitui��o tribut�ria calculado a menor;
                       cDifAliqAtivoFixo, // 02- Complemento do diferencial de al�quotas do ICMS relativo a aquisi��es para o ativo fixo;
                       cAliqUsoConsumo,   // 03- Complemento do diferencial de al�quotas do ICMS relativo a aquisi��es para uso e/ou consumo;
                       cAliqOutrasSitu,   // 04- Complemento do diferencial de al�quotas do ICMS relativo a outras situa��es;
                       cAntecTributaria,  // 05- Complemento relativo � antecipa��o tribut�ria;
                       cProgBenFical,     // 06- Complemento relativo a programa de benef�cio fiscal;
                       cOutrasSituacoes   // 99- Outras situa��es (descrever em observa��es)
                      );


  { TOpenBlocos }

  TOpenBlocos = class
  private
    FCOD_MUN: Integer;
    FIND_MOV: TACBrLIndicadorMovimento;    /// Indicador de movimento: 0- Bloco com dados informados, 1- Bloco sem dados informados.
  public
    property IND_MOV: TACBrLIndicadorMovimento read FIND_MOV write FIND_MOV;
    property COD_MUN: Integer read FCOD_MUN write FCOD_MUN;
  end;

  TACBrLFDRegistros = class(TObjectList)
  public
    function AchaUltimoPai(ANomePai, ANomeFilho: String): Integer;
  end;

implementation

{ TOpenBlocos }

{ TACBrLFDRegistros }

function TACBrLFDRegistros.AchaUltimoPai(ANomePai, ANomeFilho: String): Integer;
begin
  Result := Count - 1;
  if Result < 0 then
    raise Exception.CreateFmt('O registro %:0s deve ser filho do registro %:1s, e n�o existe nenhum %:1s pai!', [ANomeFilho, ANomePai]);
end;

end.

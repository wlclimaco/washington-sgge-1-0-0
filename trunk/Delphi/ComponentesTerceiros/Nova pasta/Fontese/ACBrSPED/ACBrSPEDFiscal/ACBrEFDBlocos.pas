{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2009   Isaque Pinheiro                      }
{                                                                              }
{ Colaboradores nesse arquivo:                                                 }
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

{******************************************************************************
|* Historico
|*
|* 10/04/2009: Isaque Pinheiro
|*  - Cria��o e distribui��o da Primeira Versao
*******************************************************************************}

unit ACBrEFDBlocos;

interface

uses
  SysUtils, Classes, DateUtils, ACBrTXTClass;

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

  /// C�digo da Situa��o Tribut�ria referente ao PIS.
  pisValorAliquotaNormal        = '01' ; // Opera��o Tribut�vel (base de c�lculo = valor da opera��o al�quota normal (cumulativo/n�o cumulativo)).
  pisValorAliquotaDiferenciada  = '02' ; // Opera��o Tribut�vel (base de c�lculo = valor da opera��o (al�quota diferenciada)).
  pisQtdeAliquotaUnidade        = '03' ; // Opera��o Tribut�vel (base de c�lculo = quantidade vendida x al�quota por unidade de produto).
  pisMonofaticaAliquotaZero     = '04' ; // Opera��o Tribut�vel (tributa��o monof�sica (al�quota zero)).
  pisAliquotaZero               = '06' ; // Opera��o Tribut�vel (al�quota zero).
  pisIsentaContribuicao         = '07' ; // Opera��o Isenta da Contribui��o.
  pisSemIncidenciaContribuicao  = '08' ; // Opera��o Sem Incid�ncia da Contribui��o.
  pisSuspensaoContribuicao      = '09' ; // Opera��o com Suspens�o da Contribui��o.
  pisOutrasOperacoes            = '99' ; // Outras Opera��es,

  /// C�digo da Situa��o Tribut�ria referente ao COFINS.
  cofinsValorAliquotaNormal       = '01' ; // Opera��o Tribut�vel (base de c�lculo = valor da opera��o al�quota normal (cumulativo/n�o cumulativo)).
  cofinsValorAliquotaDiferenciada = '02' ; // Opera��o Tribut�vel (base de c�lculo = valor da opera��o (al�quota diferenciada)).
  cofinsQtdeAliquotaUnidade       = '03' ; // Opera��o Tribut�vel (base de c�lculo = quantidade vendida x al�quota por unidade de produto).
  cofinsMonofaticaAliquotaZero    = '04' ; // Opera��o Tribut�vel (tributa��o monof�sica (al�quota zero)).
  cofinsAliquotaZero              = '06' ; // Opera��o Tribut�vel (al�quota zero).
  cofinsIsentaContribuicao        = '07' ; // Opera��o Isenta da Contribui��o.
  cofinsSemIncidenciaContribuicao = '08' ; // Opera��o Sem Incid�ncia da Contribui��o.
  cofinsSuspensaoContribuicao     = '09' ; // Opera��o com Suspens�o da Contribui��o.
  cofinsOutrasOperacoes           = '99' ; // Outras Opera��es,

type
  /// Indicador de movimento - TOpenBlocos
  TACBrIndicadorMovimento = (imComDados, // 0- Bloco com dados informados;
                             imSemDados  // 1- Bloco sem dados informados.
                             );
  /// Perfil de apresenta��o do arquivo fiscal - TRegistro0000
  TACBrPerfil             = (pfPerfilA, // A � Perfil A
                             pfPerfilB, // B � Perfil B
                             pfPerfilC  // C � Perfil C
                             );
  /// Indicador de tipo de atividade - TRegistro0000
  TACBrAtividade          = (atIndustrial, // 0 � Industrial ou equiparado a industrial
                             atOutros      // 1 � Outros.
                             );
  /// Vers�o do Leiaute do arquivo - TRegistro0000
  TACBrVersaoLeiaute      = (vlVersao100,  // C�digo 001 - Vers�o 100 Ato COTEPE 01/01/2008
                             vlVersao101,  // C�digo 002 - Vers�o 101 Ato COTEPE 01/01/2009
                             vlVersao102,  // C�digo 003 - Vers�o 102 Ato COTEPE 01/01/2010
                             vlVersao103,  // C�digo 004 - Vers�o 103 Ato COTEPE 01/01/2011
                             vlVersao104,  // C�digo 005 - Vers�o 104 Ato COTEPE 01/01/2012
                             vlVersao105,  // C�digo 006 - Vers�o 105 Ato COTEPE 01/07/2012
                             vlVersao106   // C�digo 007
                             );
  /// C�digo da finalidade do arquivo - TRegistro0000
  TACBrCodFinalidade      = (raOriginal,     // 0 - Remessa do arquivo original
                             raSubstituto    // 1 - Remessa do arquivo substituto
                             );
  /// Tipo do item � Atividades Industriais, Comerciais e Servi�os:
  TACBrTipoItem = (tiMercadoriaRevenda,    // 00 � Mercadoria para Revenda
                   tiMateriaPrima,         // 01 � Mat�ria-Prima;
                   tiEmbalagem,            // 02 � Embalagem;
                   tiProdutoProcesso,      // 03 � Produto em Processo;
                   tiProdutoAcabado,       // 04 � Produto Acabado;
                   tiSubproduto,           // 05 � Subproduto;
                   tiProdutoIntermediario, // 06 � Produto Intermedi�rio;
                   tiMaterialConsumo,      // 07 � Material de Uso e Consumo;
                   tiAtivoImobilizado,     // 08 � Ativo Imobilizado;
                   tiServicos,             // 09 � Servi�os;
                   tiOutrosInsumos,        // 10 � Outros Insumos;
                   tiOutras                // 99 � Outras
                   );
  /// Indicador do tipo de opera��o:
  TACBrTipoOperacao = (tpEntradaAquisicao, // 0 - Entrada
                       tpSaidaPrestacao    // 1 - Sa�da
                       );
  /// Indicador do emitente do documento fiscal
  TACBrEmitente = (edEmissaoPropria,         // 0 - Emiss�o pr�pria
                   edTerceiros               // 1 - Terceiro
                   );
  /// Indicador do tipo de pagamento
  TACBrTipoPagamento = (tpVista,             // 0 - � Vista
                        tpPrazo,             // 1 - A Prazo
                        tpSemPagamento,      // 9 - Sem pagamento
                        tpNenhum             // Preencher vazio
                        );
  /// Indicador do tipo do frete
  TACBrTipoFrete = (tfPorContaTerceiros,     // 0 - Por conta de terceiros
                    tfPorContaEmitente,      // 1 - Por conta do emitente
                    tfPorContaDestinatario,  // 2 - Por conta do destinat�rio
                    tfSemCobrancaFrete,      // 9 - Sem cobran�a de frete
                    tfNenhum                 // Preencher vazio
                    );

  /// Indicador do tipo do frete da opera��o de redespacho
  TACBrTipoFreteRedespacho = (frSemRedespacho,         // 0 � Sem redespacho
                              frPorContaEmitente,      // 1 - Por conta do emitente
                              frPorContaDestinatario,  // 2 - Por conta do destinat�rio
                              frOutros,                // 9 � Outros
                              frNenhum                 // Preencher vazio
                              );
  /// Indicador da origem do processo
  TACBrOrigemProcesso = (opSefaz,            // 0 - Sefaz
                         opJusticaFederal,   // 1 - Justi�a Federal
                         opJusticaEstadual,  // 2 - Justi�a Estadual
                         opSecexRFB,         // 3 - Secex/RFB
                         opOutros,           // 9 - Outros
                         opNenhum           // Preencher vazio
                         );
  /// Indicador do tipo de opera��o
  TACBrTipoOperacaoST = (toCombustiveisLubrificantes, // 0 - Combust�veis e Lubrificantes
                         toLeasingVeiculos            // 1 - leasing de ve�culos ou faturamento direto
                         );
  TACBrDoctoArrecada = (daEstadualArrecadacao,  // 0 - Documento Estadual de Arrecada��o
                        daGNRE                  // 1 - GNRE
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
  /// Documento de importa��o
  TACBrDoctoImporta = (diImportacao,           // 0 � Declara��o de Importa��o
                       diSimplificadaImport    // 1 � Declara��o Simplificada de Importa��o
                       );
  /// Indicador do tipo de t�tulo de cr�dito
  TACBrTipoTitulo = (tcDuplicata,             // 00- Duplicata
                     tcCheque,                // 01- Cheque
                     tcPromissoria,           // 02- Promiss�ria
                     tcRecibo,                // 03- Recibo
                     tcOutros                 // 99- Outros (descrever)
                     );

  /// Movimenta��o f�sica do ITEM/PRODUTO:
  TACBrMovimentacaoFisica = (mfSim,           // 0 - Sim
                             mfNao            // 1 - N�o
                             );
  /// Indicador de per�odo de apura��o do IPI
  TACBrApuracaoIPI = (iaMensal,               // 0 - Mensal
                      iaDecendial             // 1 - Decendial
                      );
  /// Indicador de tipo de refer�ncia da base de c�lculo do ICMS (ST) do produto farmac�utico
  TACBrTipoBaseMedicamento = (bmCalcTabeladoSugerido,           // 0 - Base de c�lculo referente ao pre�o tabelado ou pre�o m�ximo sugerido;
                              bmCalMargemAgregado,              // 1 - Base c�lculo � Margem de valor agregado;
                              bmCalListNegativa,                // 2 - Base de c�lculo referente � Lista Negativa;
                              bmCalListaPositiva,               // 3 - Base de c�lculo referente � Lista Positiva;
                              bmCalListNeutra                   // 4 - Base de c�lculo referente � Lista Neutra
                              );
  /// Tipo Produto
  TACBrTipoProduto = (tpSimilar,   // 0 - Similar
                      tpGenerico,  // 1 - Gen�rico
                      tpMarca      // 2 - �tico ou de Marca
                      );
  /// Indicador do tipo da arma de fogo
  TACBrTipoArmaFogo = (tafPermitido,     // 0 - Permitido
                       tafRestrito       // 1 - Restrito
                       );
  /// Indicador do tipo de opera��o com ve�culo
  TACBrTipoOperacaoVeiculo = (tovVendaPConcess,   // 0 - Venda para concession�ria
                              tovFaturaDireta,    // 1 - Faturamento direto
                              tovVendaDireta,     // 2 - Venda direta
                              tovVendaDConcess,   // 3 - Venda da concession�ria
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
  /// Situa��o do Documento
  TACBrSituacaoDocto = (sdRegular,                 // 00 - Documento regular
                        sdExtempRegular,           // 01 - Escritura��o extempor�nea de documento regular
                        sdCancelado,               // 02 - Documento cancelado
                        sdCanceladoExtemp,         // 03 - Escritura��o extempor�nea de documento cancelado
                        sdDoctoDenegado,           // 04 - NF-e ou CT-e - denegado
                        sdDoctoNumInutilizada,     // 05 - NF-e ou CT-e - Numera��o inutilizada
                        sdFiscalCompl,             // 06 - Documento Fiscal Complementar
                        sdExtempCompl,             // 07 - Escritura��o extempor�nea de documento complementar
                        sdRegimeEspecNEsp          // 08 - Documento Fiscal emitido com base em Regime Especial ou Norma Espec�fica
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
  TACBrMovimentoST = (mstSemOperacaoST,   // 0 - Sem opera��es com ST
                      mstComOperacaoST    // 1 - Com opera��es de ST
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
  TACBrPosseItem = (piInformante,           // 0- Item de propriedade do informante e em seu poder;
                    piInformanteNoTerceiro, // 1- Item de propriedade do informante em posse de terceiros;
                    piTerceiroNoInformante  // 2- Item de propriedade de terceiros em posse do informante
                    );
  /// Informe o tipo de documento
  TACBrTipoDocto = (docDeclaracaoExportacao,           // 0 - Declara��o de Exporta��o;
                    docDeclaracaoSimplesExportacao     // 1 - Declara��o Simplificada de Exporta��o.
                    );
  /// Preencher com
  TACBrExportacao = (exDireta,             // 0 - Exporta��o Direta
                     exIndireta            // 1 - Exporta��o Indireta
                     );
  /// Informa��o do tipo de conhecimento de embarque
  TACBrConhecEmbarque = (ceAWB,            //01 � AWB;
                         ceMAWB,           //02 � MAWB;
                         ceHAWB,           //03 � HAWB;
                         ceCOMAT,          //04 � COMAT;
                         ceRExpressas,     //06 � R. EXPRESSAS;
                         ceEtiqREspressas, //07 � ETIQ. REXPRESSAS;
                         ceHrExpressas,    //08 � HR. EXPRESSAS;
                         ceAV7,            //09 � AV7;
                         ceBL,             //10 � BL;
                         ceMBL,            //11 � MBL;
                         ceHBL,            //12 � HBL;
                         ceCTR,            //13 � CRT;
                         ceDSIC,           //14 � DSIC;
                         ceComatBL,        //16 � COMAT BL;
                         ceRWB,            //17 � RWB;
                         ceHRWB,           //18 � HRWB;
                         ceTifDta,         //19 � TIF/DTA;
                         ceCP2,            //20 � CP2;
                         ceNaoIATA,        //91 � N�O IATA;
                         ceMNaoIATA,       //92 � MNAO IATA;
                         ceHNaoIATA,       //93 � HNAO IATA;
                         ceCOutros         //99 � OUTROS.
                         );
  /// Identificador de medi��o
  TACBrMedicao = (medAnalogico,            // 0 - anal�gico;
                  medDigital               // 1 � digital
                  );
  /// Tipo de movimenta��o do bem ou componente
  TACBrMovimentoBens = (mbcSI,             // SI = Saldo inicial de bens imobilizados
                        mbcIM,             // IM = Imobiliza��o de bem individual
                        mbcIA,             // IA = Imobiliza��o em Andamento - Componente
                        mbcCI,             // CI = Conclus�o de Imobiliza��o em Andamento � Bem Resultante
                        mbcMC,             // MC = Imobiliza��o oriunda do Ativo Circulante
                        mbcBA,             // BA = Baixa do Saldo de ICMS - Fim do per�odo de apropria��o
                        mbcAT,             // AT = Aliena��o ou Transfer�ncia
                        mbcPE,             // PE = Perecimento, Extravio ou Deteriora��o
                        mbcOT              // OT = Outras Sa�das do Imobilizado
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
  /// Motivo do Invent�rio
  TACBrMotivoInventario = (miFinalPeriodo,
                           miMudancaTributacao,
                           miBaixaCadastral,
                           miRegimePagamento,
                           miDeterminacaoFiscos
                           );

  TOpenBlocos = class
  private
    FIND_MOV: TACBrIndicadorMovimento;    /// Indicador de movimento: 0- Bloco com dados informados, 1- Bloco sem dados informados.
  public
    property IND_MOV: TACBrIndicadorMovimento read FIND_MOV write FIND_MOV;
  end;
  // Fu��es do ACBrEPCBlocos.
  Function StrToCodVer(AValue: string): TACBrVersaoLeiaute;
  Function CodVerToStr(AValue: TACBrVersaoLeiaute): string;

implementation

{ TOpenBlocos }

function StrToCodVer(AValue: string): TACBrVersaoLeiaute;
begin
   if AValue = '001' then
      Result := vlVersao100
   else
   if AValue = '002' then
      Result := vlVersao101
   else
   if AValue = '003' then
      Result := vlVersao102
   else
   if AValue = '004' then
      Result := vlVersao103
   else
   if AValue = '005' then
      Result := vlVersao104
   else
   if AValue = '006' then
      Result := vlVersao105
   else
   if AValue = '007' then
      Result := vlVersao106
      ;
end;

function CodVerToStr(AValue: TACBrVersaoLeiaute): string;
begin
   if AValue = vlVersao100 then
      Result := '001'
   else
   if AValue = vlVersao101 then
      Result := '002'
   else
   if AValue = vlVersao102 then
      Result := '003'
   else
   if AValue = vlVersao103 then
      Result := '004'
   else
   if AValue = vlVersao104 then
      Result := '005'
   else
   if AValue = vlVersao105 then
      Result := '006'
   else
   if AValue = vlVersao106 then
      Result := '007'
      ;

end;


end.
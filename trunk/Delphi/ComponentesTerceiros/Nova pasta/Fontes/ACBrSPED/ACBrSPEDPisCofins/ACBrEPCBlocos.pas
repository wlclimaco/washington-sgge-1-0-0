{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2010   Isaque Pinheiro                      }
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
|* 07/12/2010: Isaque Pinheiro
|*  - Cria��o e distribui��o da Primeira Versao
|* 11/01/2011:Alessandro Yamasaki
|*  - Ajustes referente aos conteudos da Origem do Processo
|*  - Criado 'Local da execu��o do servi�o',
|*  - Criado 'Base de C�lculo do Cr�dito'
|*  - Criado 'Origem Credito'
|*
*******************************************************************************}

unit ACBrEPCBlocos;

interface

uses
  SysUtils, Classes, DateUtils, ACBrTXTClass, ACBrSpedUtils;

type
  /// Vers�o do Leiaute do arquivo - TRegistro0000
  TACBrCodVer = (
                 vlVersao100,  // C�digo 001 - Vers�o 100 ADE Cofis n� 31/2010 de 01/01/2011
                 vlVersao101,  // C�digo 002 - Vers�o 101 ADE Cofis n� 34/2010 de 01/01/2011
                 vlVersao200,  // C�digo 002 - Vers�o 200 ADE Cofis n� 20/2012
                 vlVersao201   // C�digo 003 - Vers�o 201 ADE Cofis n� 20/2012 de 14/03/2012
                );
  TACBrVersaoLeiaute = TACBrCodVer;

  /// Indicador de movimento - TOpenBlocos
  TACBrIndMov = (
                 imComDados, // 0- Bloco com dados informados;
                 imSemDados  // 1- Bloco sem dados informados.
                );
  TACBrIndicadorMovimento = TACBrIndMov;

  // Tipo de Escritura��o
  TACBrTipoEscrit = (
                     tpEscrOriginal,     // 0 - Original
                     tpEscrRetificadora  // 1 - Retificadora
                   );
  // Tipo de Escritura��o
  TACBrTipoEscrituracao = TACBrTipoEscrit;

  // Indicador de situa��o especial
  TACBrIndSitEsp = (
                    indSitAbertura,      // 0 - Abertura
                    indSitCisao,         // 1 - Cis�o
                    indSitFusao,         // 2 - Fus�o
                    indSitIncorporacao,  // 3 - Incorpora��o
                    indSitEncerramento,  // 4 - Encerramento
                    indNenhum            // 5 - Vazio
                  );
  TACBrIndicadorSituacaoEspecial = TACBrIndSitEsp;

  // Indicador da natureza da pessoa juridica
  TACBrIndNatPJ = (
                   indNatPJSocEmpresariaGeral, // 0 - Sociedade empres�ria geral
                   indNatPJSocCooperativa,     // 1 - Sociedade Cooperativa
                   indNatPJEntExclusivaFolhaSal// 2 - Entidade sujeita ao PIS/Pasep exclusivamente com base  na folha de sal�rios
                 );
  TACBrIndicadorNaturezaPJ = TACBrIndNatPJ;

  //Indicador de tipo de atividade prepoderante
  TACBrIndAtiv = (
                  indAtivIndustrial,       // 0 - Industrial ou equiparado a industrial
                  indAtivPrestadorServico, // 1 - Prestador de servi�os
                  indAtivComercio,         // 2 - Atividade de com�rcios
                  indAtivoFincanceira,     // 3 - Atividade Financeira
                  indAtivoImobiliaria,     // 4 - Atividade Imobili�ria
                  indAtivoOutros           // 9 - Outros
               );
  TACBrIndicadorAtividade = TACBrIndAtiv;

  //Codigo indicador da incidencia tribut�ria no per�odo (0110)
  TACBrCodIncTrib = (
                     codEscrOpIncNaoCumulativo, // 1 - Escritura��o de opera��es com incidencia exclusivamente no regime n�o cumulativo
                     codEscrOpIncCumulativo,    // 2 - Escritura��o de opera��es com incidencia exclusivamente no regime cumulativo
                     codEscrOpIncAmbos          // 3 - Escritura��o de opera��es com incidencia nos regimes cumulativo e n�o cumulativo
                   );
  TACBrCodIndIncTributaria = TACBrCodIncTrib;


  //C�digo indicador de  m�todo  de apropria��o de  cr�ditos  comuns, no caso  de incidencia no regime n�o cumulativo(COD_INC_TRIB = 1 ou 3)(0110)
  TACBrIndAproCred = (
                       indMetodoApropriacaoDireta,   // 0 - M�todo de apropria��o direta
                       indMetodoDeRateioProporcional // 1 - M�todo de rateio proporcional(Receita Bruta);
                     );

  //C�digo indicador do Tipo de Contribui��o Apurada no Per�odo(0110)
  TACBrCodTipoCont = (
                       codIndTipoConExclAliqBasica, // 1 - Apura��o da Contribui��o Exclusivamente a Al�quota B�sica
                       codIndTipoAliqEspecificas    // 2 - Apura��o da Contribui��o a Al�quotas Espec�ficas (Diferenciadas e/ou por Unidade de Medida de Produto)
                     );
  TACBrCodIndTipoCon = TACBrCodTipoCont;

  //C�digo indicador do crit�rio de escritura��o e apura��o adotado
  TACBrIndRegCum = (
                     codRegimeCaixa,                   // 1 � Regime de Caixa � Escritura��o consolidada (Registro F500);
                     codRegimeCompetEscritConsolidada, // 2 � Regime de Compet�ncia - Escritura��o consolidada (Registro F550);
                     codRegimeCompetEscritDetalhada    // 9 � Regime de Compet�ncia - Escritura��o detalhada, com base nos registros dos Blocos �A�, �C�, �D� e �F�.
                   );
  TACBrCodIndCritEscrit = TACBrIndRegCum;

  /// Tipo do item � Atividades Industriais, Comerciais e Servi�os:
  TACBrTipoItem = (
                    tiMercadoriaRevenda,    // 00 � Mercadoria para Revenda
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
  TACBrIndOper = (
                   itoContratado,     // 0 - Servi�o Contratado pelo Estabelecimento
                   itoPrestado        // 1 - Servi�o Prestado pelo Estabelecimento
                 );
  TACBrIndicadorTpOperacao = TACBrIndOper;

  /// Indicador do emitente do documento fiscal:
  TACBrIndEmit = (
                  iedfProprio,       // 0 - Emiss�o pr�pria
                  iedfTerceiro       // 1 - Emiss�o de Terceiros
                 );
  TACBrIndicadorEmitenteDF = TACBrIndEmit;

  /// Indicador do tipo de pagamento
  TACBrIndPgto = (
                  tpVista,             // 0 - � Vista
                  tpPrazo,             // 1 - A Prazo
                  tpSemPagamento,      // 9 - Sem pagamento
                  tpNenhum             // Preencher vazio
                 );
  TACBrTipoPagamento = TACBrIndPgto;

  //C�digo da Base de C�lculo do Cr�dito - {NAT_BC_CRED} - 4.3.7 - Tabela Base de C�lculo do Cr�dito
  TACBrNatBcCred = (
                    bccVazio,                         // ''   // vazio.
                    bccAqBensRevenda,                 // '01' // Aquisi��o de bens para revenda
                    bccAqBensUtiComoInsumo,           // '02' // Aquisi��o de bens utilizados como insumo
                    bccAqServUtiComoInsumo,           // '03' // Aquisi��o de servi�os utilizados como insumo
                    bccEnergiaEletricaTermica,        // '04' // Energia el�trica e t�rmica, inclusive sob a forma de vapor
                    bccAluguelPredios,                // '05' // Alugu�is de pr�dios
                    bccAluguelMaqEquipamentos,        // '06' // Alugu�is de m�quinas e equipamentos
                    bccArmazenagemMercadoria,         // '07' // Armazenagem de mercadoria e frete na opera��o de venda
                    bccConArrendamentoMercantil,      // '08' // Contrapresta��es de arrendamento mercantil
                    bccMaqCredDepreciacao,            // '09' // M�quinas, equipamentos e outros bens incorporados ao ativo imobilizado (cr�dito sobre encargos de deprecia��o).
                    bccMaqCredAquisicao,              // '10' // M�quinas, equipamentos e outros bens incorporados ao ativo imobilizado (cr�dito com base no valor de aquisi��o).
                    bccAmortizacaoDepreciacaoImoveis, // '11' // Amortiza��o e Deprecia��o de edifica��es e benfeitorias em im�veis
                    bccDevolucaoSujeita,              // '12' // Devolu��o de Vendas Sujeitas � Incid�ncia N�o-Cumulativa
                    bccOutrasOpeComDirCredito,        // '13' // Outras Opera��es com Direito a Cr�dito
                    bccAtTransporteSubcontratacao,    // '14' // Atividade de Transporte de Cargas � Subcontrata��o
                    bccAtImobCustoIncorrido,          // '15' // Atividade Imobili�ria � Custo Incorrido de Unidade Imobili�ria
                    bccAtImobCustoOrcado,             // '16' // Atividade Imobili�ria � Custo Or�ado de unidade n�o conclu�da
                    bccAtPresServ,                    // '17' // Atividade de Presta��o de Servi�os de Limpeza, Conserva��o e Manuten��o � vale-transporte, vale-refei��o ou vale-alimenta��o, fardamento ou uniforme.
                    bccEstoqueAberturaBens            // '18' // Estoque de abertura de bens
                  );
  TACBrBaseCalculoCredito = TACBrNatBcCred;

  // Indicador da Origem do Cr�dito
  TACBrIndOrigCred = (
                       opcMercadoInterno,      // 0 � Opera��o no Mercado Interno
                       opcImportacao ,         // 1 � Opera��o de Importa��o
                       opcVazio                // Vazio.
                     );
  TACBrOrigemCredito = TACBrIndOrigCred;




  /// Perfil de apresenta��o do arquivo fiscal - TRegistro0000
//  TACBrPerfil  = (
//                   pfPerfilA , // A � Perfil A
//                   pfPerfilB , // B � Perfil B
//                   pfPerfilC   // C � Perfil C
//                  );

  /// Indicador de tipo de atividade - TRegistro0000
//  TACBrAtividade   = (
//                       atIndustrial,         // 0 � Industrial ou equiparado a industrial
//                       atPrestadorDeServicos,// 1 - Prestador de servi�os
//                       atComercio,           // 2 - Atividade de com�rcio
//                       atFinanceira,         // 3 - Atividade financeira
//                       atImobiliaria,        // 4 - Atividade imobiliaria
//                       atOutros = 9          // 9 - Outros
//                      );

  /// C�digo da finalidade do arquivo - TRegistro0000
//  TACBrCodFinalidade = (
//                         raOriginal,     // 0 - Remessa do arquivo original
//                         raSubstituto    // 1 - Remessa do arquivo substituto
//                        );
  /// Indicador do tipo de opera��o:
  TACBrIndTipoOper = (
                      tpEntradaAquisicao, // 0 - Entrada
                      tpSaidaPrestacao    // 1 - Sa�da
                     );
  TACBrTipoOperacao = TACBrIndTipoOper;

  /// Indicador do emitente do documento fiscal
  TACBrEmitente = (
                    edEmissaoPropria,         // 0 - Emiss�o pr�pria
                    edTerceiros               // 1 - Terceiro
                   );
  /// Indicador do tipo do frete
  TACBrIndFrt = (
                 tfPorContaEmitente,     // 0 - Por conta de terceiros
                 tfPorContaDestinatario, // 1 - Por conta do emitente
                 tfPorContaTerceiros,    // 2 - Por conta do destinat�rio
                 tfSemCobrancaFrete,     // 9 - Sem cobran�a de frete
                 tfNenhum
                );
  TACBrTipoFrete = TACBrIndFrt;

  /// Indicador do tipo do frete da opera��o de redespacho
//  TACBrTipoFreteRedespacho = (
//                               frSemRedespacho,         // 0 � Sem redespacho
//                               frPorContaEmitente,      // 1 - Por conta do emitente
//                               frPorContaDestinatario,  // 2 - Por conta do destinat�rio
//                               frOutros,                // 9 � Outros
//                               frNenhum                 // Preencher vazio
//                              );
  /// Indicador da origem do processo
  TACBrOrigemProcesso = (
                          opJusticaFederal,   // 1 - Justi�a Federal'
                          opSecexRFB,         // 3 � Secretaria da Receita Federal do Brasil
                          opOutros,           // 9 - Outros
                          opNenhum           // Preencher vazio
                         );
  ///
//  TACBrDoctoArrecada = (
//                         daEstadualArrecadacao,  // 0 - Documento Estadual de Arrecada��o
//                         daGNRE                  // 1 - GNRE
//                        );
  /// Indicador do tipo de transporte
//  TACBrTipoTransporte = (
//                          ttRodoviario,         // 0 � Rodovi�rio
//                          ttFerroviario,        // 1 � Ferrovi�rio
//                          ttRodoFerroviario,    // 2 � Rodo-Ferrovi�rio
//                          ttAquaviario,         // 3 � Aquavi�rio
//                          ttDutoviario,         // 4 � Dutovi�rio
//                          ttAereo,              // 5 � A�reo
//                          ttOutros              // 9 � Outros
//                         );
  /// Documento de importa��o
  TACBrDoctoImporta = (
                        diImportacao,           // 0 � Declara��o de Importa��o
                        diSimplificadaImport    // 1 � Declara��o Simplificada de Importa��o
                       );
  /// Indicador do tipo de t�tulo de cr�dito
//  TACBrTipoTitulo = (
//                      tcDuplicata,             // 00- Duplicata
//                      tcCheque,                // 01- Cheque
//                      tcPromissoria,           // 02- Promiss�ria
//                      tcRecibo,                // 03- Recibo
//                      tcOutros                 // 99- Outros (descrever)
//                     );

  /// Movimenta��o f�sica do ITEM/PRODUTO:
  TACBrIndMovFisica = (
                        mfSim,           // 0 - Sim
                        mfNao            // 1 - N�o
                             );
  TACBrMovimentacaoFisica = TACBrIndMovFisica;

  /// Indicador de per�odo de apura��o do IPI
  TACBrApuracaoIPI = (
                       iaMensal,               // 0 - Mensal
                       iaDecendial,            // 1 - Decendial
                       iaVazio
                      );
  /// Indicador de tipo de refer�ncia da base de c�lculo do ICMS (ST) do produto farmac�utico
//  TACBrTipoBaseMedicamento = (
//                               bmCalcTabeladoSugerido,           // 0 - Base de c�lculo referente ao pre�o tabelado ou pre�o m�ximo sugerido;
//                               bmCalMargemAgregado,              // 1 - Base c�lculo � Margem de valor agregado;
//                               bmCalListNegativa,                // 2 - Base de c�lculo referente � Lista Negativa;
//                               bmCalListaPositiva,               // 3 - Base de c�lculo referente � Lista Positiva;
//                               bmCalListNeutra                   // 4 - Base de c�lculo referente � Lista Neutra
//                              );
  /// Tipo Produto
//  TACBrTipoProduto = (
//                       tpSimilar,   // 0 - Similar
//                       tpGenerico,  // 1 - Gen�rico
//                       tpMarca      // 2 - �tico ou de Marca
//                      );
  /// Indicador do tipo da arma de fogo
//  TACBrTipoArmaFogo = (
//                        tafPermitido,     // 0 - Permitido
//                        tafRestrito       // 1 - Restrito
//                       );
  /// Indicador do tipo de opera��o com ve�culo
//  TACBrTipoOperacaoVeiculo = (
//                               tovVendaPConcess,   // 0 - Venda para concession�ria
//                               tovFaturaDireta,    // 1 - Faturamento direto
//                               tovVendaDireta,     // 2 - Venda direta
//                               tovVendaDConcess,   // 3 - Venda da concession�ria
//                               tovVendaOutros      // 9 - Outros
//                              );
  /// Indicador do tipo de receita
//  TACBrTipoReceita = (
//                       trPropria,   // 0 - Receita pr�pria
//                       trTerceiro   // 1 - Receita de terceiros
//                      );

  /// Indicador do tipo do ve�culo transportador
//  TACBrTipoVeiculo = (
//                       tvEmbarcacao,
//                       tvEmpuradorRebocador
//                      );
  /// Indicador do tipo da navega��o
//  TACBrTipoNavegacao = (
//                         tnInterior,
//                         tnCabotagem
//                        );
  /// Situa��o do Documento
  /// C�digo da situa��o do documento fiscal:
  /// C�digo da situa��o do documento fiscal:
  TACBrCodSit = (
                 sdfRegular,                 // 00 � Documento regular
                 sdfExtRegular,              // 01 - Escritura��o extempor�nea de documento regular
                 sdfCancelado,               // 02 � Documento cancelado
                 sdfExtCancelado,            // 03 Escritura��o extempor�nea de documento cancelado
                 sdfDenegado,                // 04 NF-e ou CT-e � denegado
                 sdfInutilizado,             // 05 NF-e ou CT-e - Numera��o inutilizada
                 sdfComplementar,            // 06 Documento Fiscal Complementar
                 sdfExtComplementar,         // 07 Escritura��o extempor�nea de documento complementar
                 sdfEspecial                 // 08 Documento Fiscal emitido com base em Regime Especial ou Norma Espec�fica
                 );
  TACBrSituacaoDF = TACBrCodSit;

  /// C�digo da situa��o do documento fiscal (Registro 1900):
  TACBrCodSitF = (
                  csffRegular,            // 00 � Documento regular
                  csfCancelado,           // 02 � Documento cancelado
                  csfOutros               // 99 � Outros
                  );

(*
  TACBrSituacaoDocto = (
                         sdRegular,                 // 00 - Documento regular
                         sdExtempRegular,           // 01 - Escritura��o extempor�nea de documento regular
                         sdCancelado,               // 02 - Documento cancelado
                         sdCanceladoExtemp,         // 03 - Escritura��o extempor�nea de documento cancelado
                         sdDoctoDenegado,           // 04 - NF-e ou CT-e - denegado
                         sdDoctoNumInutilizada,     // 05 - NF-e ou CT-e - Numera��o inutilizada
                         sdFiscalCompl,             // 06 - Documento Fiscal Complementar
                         sdExtempCompl,             // 07 - Escritura��o extempor�nea de documento complementar
                         sdRegimeEspecNEsp          // 08 - Documento Fiscal emitido com base em Regime Especial ou Norma Espec�fica
                        );
*)
  /// Indicador do tipo de tarifa aplicada:
//  TACBrTipoTarifa = (
//                      tipExp,     // 0 - Exp
//                      tipEnc,     // 1 - Enc
//                      tipCI,      // 2 - CI
//                      tipOutra    // 9 - Outra
//                     );
  /// Indicador da natureza do frete
//  TACBrNaturezaFrete = (
//                         nfNegociavel,      // 0 - Negociavel
//                         nfNaoNegociavel    // 1 - N�o Negociavel
//                        );

  //INDICADOR DE NATUREZA DE FRETE CONTRATADO
  TACBrNaturezaFrtContratado = (
                                 nfcVendaOnusEstVendedor,    //0 - Opera��es de vendas, com �nus suportado pelo estabelecimento vendedor
                                 nfcVendaOnusAdquirente,     //1 - Opera��es de vendas, com �nus suportado pelo adquirente
                                 nfcCompraGeraCred,          //2 - Opera��es de compras (bens para revenda, mat�riasprima e outros produtos, geradores de cr�dito)
                                 nfcCompraNaoGeraCred,       //3 - Opera��es de compras (bens para revenda, mat�riasprima e outros produtos, n�o geradores de cr�dito)
                                 nfcTransfAcabadosPJ,        //4 - Transfer�ncia de produtos acabados entre estabelecimentos da pessoa jur�dica
                                 nfcTransfNaoAcabadosPJ,     //5 - Transfer�ncia de produtos em elabora��o entre estabelecimentos da pessoa jur�dica
                                 nfcOutras                   //9 - Outras.
                                );

  /// Indicador do tipo de receita
//  TACBrIndTipoReceita = (
//                          recServicoPrestado,          // 0 - Receita pr�pria - servi�os prestados;
//                          recCobrancaDebitos,          // 1 - Receita pr�pria - cobran�a de d�bitos;
//                          recVendaMerc,                // 2 - Receita pr�pria - venda de mercadorias;
//                          recServicoPrePago,           // 3 - Receita pr�pria - venda de servi�o pr�-pago;
//                          recOutrasProprias,           // 4 - Outras receitas pr�prias;
//                          recTerceiroCoFaturamento,    // 5 - Receitas de terceiros (co-faturamento);
//                          recTerceiroOutras            // 9 - Outras receitas de terceiros
//                         );
  /// Indicador do tipo de servi�o prestado
//  TACBrServicoPrestado = (
//                           spTelefonia,                // 0- Telefonia;
//                           spComunicacaoDados,         // 1- Comunica��o de dados;
//                           spTVAssinatura,             // 2- TV por assinatura;
//                           spAcessoInternet,           // 3- Provimento de acesso � Internet;
//                           spMultimidia,               // 4- Multim�dia;
//                           spOutros                    // 9- Outros
//                          );
  /// Indicador de movimento
//  TACBrMovimentoST = (
//                       mstSemOperacaoST,   // 0 - Sem opera��es com ST
//                       mstComOperacaoST    // 1 - Com opera��es de ST
//                      );
  /// Indicador do tipo de ajuste
//  TACBrTipoAjuste = (
//                      ajDebito,            // 0 - Ajuste a d�bito;
//                      ajCredito            // 1- Ajuste a cr�dito
//                     );
  /// Indicador da origem do documento vinculado ao ajuste
//  TACBrOrigemDocto = (
//                       odPorcessoJudicial, // 0 - Processo Judicial;
//                       odProcessoAdminist, // 1 - Processo Administrativo;
//                       odPerDcomp,         // 2 - PER/DCOMP;
//                       odOutros            // 9 � Outros.
//                      );
  /// Indicador de propriedade/posse do item
//  TACBrPosseItem = (
//                     piInformante,           // 0- Item de propriedade do informante e em seu poder;
//                     piInformanteNoTerceiro, // 1- Item de propriedade do informante em posse de terceiros;
//                     piTerceiroNoInformante  // 2- Item de propriedade de terceiros em posse do informante
//                    );
  /// Informe o tipo de documento
//  TACBrTipoDocto = (
//                     docDeclaracaoExportacao,           // 0 - Declara��o de Exporta��o;
//                     docDeclaracaoSimplesExportacao     // 1 - Declara��o Simplificada de Exporta��o.
//                    );
  /// Preencher com
//  TACBrExportacao = (
//                      exDireta,             // 0 - Exporta��o Direta
//                      exIndireta            // 1 - Exporta��o Indireta
//                     );
  /// Informa��o do tipo de conhecimento de embarque
//  TACBrConhecEmbarque = (
//                          ceAWB,            //01 � AWB;
//                          ceMAWB,           //02 � MAWB;
//                          ceHAWB,           //03 � HAWB;
//                          ceCOMAT,          //04 � COMAT;
//                          ceRExpressas,     //06 � R. EXPRESSAS;
//                          ceEtiqREspressas, //07 � ETIQ. REXPRESSAS;
//                          ceHrExpressas,    //08 � HR. EXPRESSAS;
//                          ceAV7,            //09 � AV7;
//                          ceBL,             //10 � BL;
//                          ceMBL,            //11 � MBL;
//                          ceHBL,            //12 � HBL;
//                          ceCTR,            //13 � CRT;
//                          ceDSIC,           //14 � DSIC;
//                          ceComatBL,        //16 � COMAT BL;
//                          ceRWB,            //17 � RWB;
//                          ceHRWB,           //18 � HRWB;
//                          ceTifDta,         //19 � TIF/DTA;
//                          ceCP2,            //20 � CP2;
//                          ceNaoIATA,        //91 � N�O IATA;
//                          ceMNaoIATA,       //92 � MNAO IATA;
//                          ceHNaoIATA,       //93 � HNAO IATA;
//                          ceCOutros         //99 � OUTROS.
//                         );
  /// Identificador de medi��o
//  TACBrMedicao = (
//                   medAnalogico,            // 0 - anal�gico;
//                   medDigital               // 1 � digital
//                  );
  /// Tipo de movimenta��o do bem ou componente
//  TACBrMovimentoBens = (
//                         mbcSI,             // SI = Saldo inicial de bens imobilizados
//                         mbcIM,             // IM = Imobiliza��o de bem individual
//                         mbcIA,             // IA = Imobiliza��o em Andamento - Componente
//                         mbcCI,             // CI = Conclus�o de Imobiliza��o em Andamento � Bem Resultante
//                         mbcMC,             // MC = Imobiliza��o oriunda do Ativo Circulante
//                         mbcBA,             // BA = Baixa do Saldo de ICMS - Fim do per�odo de apropria��o
//                         mbcAT,             // AT = Aliena��o ou Transfer�ncia
//                         mbcPE,             // PE = Perecimento, Extravio ou Deteriora��o
//                         mbcOT              // OT = Outras Sa�das do Imobilizado
//                        );
  /// C�digo de grupo de tens�o
//  TACBrGrupoTensao = (
//                       gtA1,          // 01 - A1 - Alta Tens�o (230kV ou mais)
//                       gtA2,          // 02 - A2 - Alta Tens�o (88 a 138kV)
//                       gtA3,          // 03 - A3 - Alta Tens�o (69kV)
//                       gtA3a,         // 04 - A3a - Alta Tens�o (30kV a 44kV)
//                       gtA4,          // 05 - A4 - Alta Tens�o (2,3kV a 25kV)
//                       gtAS,          // 06 - AS - Alta Tens�o Subterr�neo 06
//                       gtB107,        // 07 - B1 - Residencial 07
//                       gtB108,        // 08 - B1 - Residencial Baixa Renda 08
//                       gtB209,        // 09 - B2 - Rural 09
//                       gtB2Rural,     // 10 - B2 - Cooperativa de Eletrifica��o Rural
//                       gtB2Irrigacao, // 11 - B2 - Servi�o P�blico de Irriga��o
//                       gtB3,          // 12 - B3 - Demais Classes
//                       gtB4a,         // 13 - B4a - Ilumina��o P�blica - rede de distribui��o
//                       gtB4b          // 14 - B4b - Ilumina��o P�blica - bulbo de l�mpada
//                      );
  /// C�digo de classe de consumo de energia el�trica ou g�s
//  TACBrClasseConsumo = (
//                         ccComercial,         // 01 - Comercial
//                         ccConsumoProprio,    // 02 - Consumo Pr�prio
//                         ccIluminacaoPublica, // 03 - Ilumina��o P�blica
//                         ccIndustrial,        // 04 - Industrial
//                         ccPoderPublico,      // 05 - Poder P�blico
//                         ccResidencial,       // 06 - Residencial
//                         ccRural,             // 07 - Rural
//                         ccServicoPublico     // 08 -Servi�o P�blico
//                        );
  /// C�digo de tipo de Liga��o
//  TACBrTipoLigacao = (
//                       tlMonofasico,          // 1 - Monof�sico
//                       tlBifasico,            // 2 - Bif�sico
//                       tlTrifasico            // 3 - Trif�sico
//                      );
  /// C�digo dispositivo autorizado
//  TACBrDispositivo = (
//                       cdaFormSeguranca,  // 00 - Formul�rio de Seguran�a
//                       cdaFSDA,           // 01 - FS-DA � Formul�rio de Seguran�a para Impress�o de DANFE
//                       cdaNFe,            // 02 � Formul�rio de seguran�a - NF-e
//                       cdaFormContinuo,   // 03 - Formul�rio Cont�nuo
//                       cdaBlocos,         // 04 � Blocos
//                       cdaJogosSoltos     // 05 - Jogos Soltos
//                      );
  /// C�digo do Tipo de Assinante
//  TACBrTipoAssinante = (
//                         assComercialIndustrial,    // 1 - Comercial/Industrial
//                         assPodrPublico,            // 2 - Poder P�blico
//                         assResidencial,            // 3 - Residencial/Pessoa f�sica
//                         assPublico,                // 4 - P�blico
//                         assSemiPublico,            // 5 - Semi-P�blico
//                         assOutros                  // 6 - Outros
//                        );
  /// C�digo da natureza da conta/grupo de contas
  TACBrNaturezaConta = (
                         ncgAtivo,        // 01 - Contas de ativo
                         ncgPassivo,      // 02 - Contas de passivo
                         ncgLiquido,      // 03 - Patrim�nio l�quido
                         ncgResultado,    // 04 - Contas de resultado
                         ncgCompensacao,  // 05 - Contas de compensa��o
                         ncgOutras        // 09 - Outras
                        );

  /// C�digo da tabela de modelo de documento fiscais:
//  TACBrCodModeloDoc = (
//                       dfiNFSTransporte,          // 07 - Nota Fiscal de Servi�o de Transporte
//                       dfiConhecimentoRodoviario, // 08 - Conhecimento de Transporte Rodovi�rio de Cargas
//                       dfiConhecimentoAvulso,     // 8B - Conhecimento de Transporte de Cargas Avulso
//                       dfiConhecimentoAquaviario, // 09 - Conhecimento de Transporte Aquavi�rio de Cargas
//                       dfiConhecimentoAereo,      // 10 - Conhecimento A�rio
//                       dfiConhecimentoFerroviario,// 11 - Conhecimento de Transporte Ferrovi�rio de Cargas
//                       dfiConhecimentoMultimodal, // 26 - Conhecimento de Transporte Multimodal de Cargas
//                       dfiNFTranspFerro,          // 27 - Nota Fiscal de Transporte Ferrovi�rio de Cargas
//                       dfiCTE,                    // 57 - Conhecimento de Transporte Eletr�nico - CT-e
//                       dfiBilheteRedoviario,      // 13 - Bilhete de passagem Rodovi�rio
//                       dfiBilheteAquaviario,      // 14 - Bilhete de passagem Aquavi�rio
//                       dfiBilheteBagagem,         // 15 - Bilhete de passagem e Nota de Bagagem
//                       dfiBilheteFerroviario,     // 16 - Bilhete de passagem Ferrrovi�rio
//                       dfiResumoMovimento,        // 18 - Resumo de Movimento Di�rio
//                       dfiCFBilhete,              // 2E - Cupom Fiscal Bilhete de Passagem
//                       dfiNFSComunicacao,         // 21 - Nota Fiscal de Servi�o de Comunica��o
//                       dfiNFSTelecomunicacao      // 22 - Nota Fiscal de Servi�o de Telecomunica��o
//                      );

  ///C�digo da Situa��o Tribut�ria referente ao ICMS.
  TACBrCstIcms = (
                   sticmsTributadaIntegralmente                              , // '000' //	Tributada integralmente
                   sticmsTributadaComCobracaPorST                            , // '010' //	Tributada e com cobran�a do ICMS por substitui��o tribut�ria
                   sticmsComReducao                                          , // '020' //	Com redu��o de base de c�lculo
                   sticmsIsentaComCobracaPorST                               , // '030' //	Isenta ou n�o tributada e com cobran�a do ICMS por substitui��o tribut�ria
                   sticmsIsenta                                              , // '040' //	Isenta
                   sticmsNaoTributada                                        , // '041' //	N�o tributada
                   sticmsSuspensao                                           , // '050' //	Suspens�o
                   sticmsDiferimento                                         , // '051' //	Diferimento
                   sticmsCobradoAnteriormentePorST                           , // '060' //	ICMS cobrado anteriormente por substitui��o tribut�ria
                   sticmsComReducaoPorST                                     , // '070' //	Com redu��o de base de c�lculo e cobran�a do ICMS por substitui��o tribut�ria
                   sticmsOutros                                              , // '090' //	Outros
                   sticmsEstrangeiraImportacaoDiretaTributadaIntegralmente   , // '100' // Estrangeira - Importa��o direta - Tributada integralmente
                   sticmsEstrangeiraImportacaoDiretaTributadaComCobracaPorST , // '110' // Estrangeira - Importa��o direta - Tributada e com cobran�a do ICMS por substitui��o tribut�ria
                   sticmsEstrangeiraImportacaoDiretaComReducao               , // '120' // Estrangeira - Importa��o direta - Com redu��o de base de c�lculo
                   sticmsEstrangeiraImportacaoDiretaIsentaComCobracaPorST    , // '130' // Estrangeira - Importa��o direta - Isenta ou n�o tributada e com cobran�a do ICMS por substitui��o tribut�ria
                   sticmsEstrangeiraImportacaoDiretaIsenta                   , // '140' // Estrangeira - Importa��o direta - Isenta
                   sticmsEstrangeiraImportacaoDiretaNaoTributada             , // '141' // Estrangeira - Importa��o direta - N�o tributada
                   sticmsEstrangeiraImportacaoDiretaSuspensao                , // '150' // Estrangeira - Importa��o direta - Suspens�o
                   sticmsEstrangeiraImportacaoDiretaDiferimento              , // '151' // Estrangeira - Importa��o direta - Diferimento
                   sticmsEstrangeiraImportacaoDiretaCobradoAnteriormentePorST, // '160' // Estrangeira - Importa��o direta - ICMS cobrado anteriormente por substitui��o tribut�ria
                   sticmsEstrangeiraImportacaoDiretaComReducaoPorST          , // '170' // Estrangeira - Importa��o direta - Com redu��o de base de c�lculo e cobran�a do ICMS por substitui��o tribut�ria
                   sticmsEstrangeiraImportacaoDiretaOutros                   , // '190' // Estrangeira - Importa��o direta - Outras
                   sticmsEstrangeiraAdqMercIntTributadaIntegralmente         , // '200' // Estrangeira - Adquirida no mercado interno - Tributada integralmente
                   sticmsEstrangeiraAdqMercIntTributadaComCobracaPorST       , // '210' // Estrangeira - Adquirida no mercado interno - Tributada e com cobran�a do ICMS por substitui��o tribut�ria
                   sticmsEstrangeiraAdqMercIntComReducao                     , // '220' // Estrangeira - Adquirida no mercado interno - Com redu��o de base de c�lculo
                   sticmsEstrangeiraAdqMercIntIsentaComCobracaPorST          , // '230' // Estrangeira - Adquirida no mercado interno - Isenta ou n�o tributada e com cobran�a do ICMS por substitui��o tribut�ria
                   sticmsEstrangeiraAdqMercIntIsenta                         , // '240' // Estrangeira - Adquirida no mercado interno - Isenta
                   sticmsEstrangeiraAdqMercIntNaoTributada                   , // '241' // Estrangeira - Adquirida no mercado interno - N�o tributada
                   sticmsEstrangeiraAdqMercIntSuspensao                      , // '250' // Estrangeira - Adquirida no mercado interno - Suspens�o
                   sticmsEstrangeiraAdqMercIntDiferimento                    , // '251' // Estrangeira - Adquirida no mercado interno - Diferimento
                   sticmsEstrangeiraAdqMercIntCobradoAnteriormentePorST      , // '260' // Estrangeira - Adquirida no mercado interno - ICMS cobrado anteriormente por substitui��o tribut�ria
                   sticmsEstrangeiraAdqMercIntComReducaoPorST                , // '270' // Estrangeira - Adquirida no mercado interno - Com redu��o de base de c�lculo e cobran�a do ICMS por substitui��o tribut�ria
                   sticmsEstrangeiraAdqMercIntOutros                         , // '290' // Estrangeira - Adquirida no mercado interno - Outras
                   sticmsSimplesNacionalTributadaComPermissaoCredito         , // '101' // Simples Nacional - Tributada pelo Simples Nacional com permiss�o de cr�dito
                   sticmsSimplesNacionalTributadaSemPermissaoCredito         , // '102' // Simples Nacional - Tributada pelo Simples Nacional sem permiss�o de cr�dito
                   sticmsSimplesNacionalIsencaoPorFaixaReceitaBruta          , // '103' // Simples Nacional - Isen��o do ICMS no Simples Nacional para faixa de receita bruta
                   sticmsSimplesNacionalTributadaComPermissaoCreditoComST    , // '201' // Simples Nacional - Tributada pelo Simples Nacional com permiss�o de cr�dito e com cobran�a do ICMS por substitui��o tribut�ria
                   sticmsSimplesNacionalTributadaSemPermissaoCreditoComST    , // '202' // Simples Nacional - Tributada pelo Simples Nacional sem permiss�o de cr�dito e com cobran�a do ICMS por substitui��o tribut�ria
                   sticmsSimplesNacionalIsencaoPorFaixaReceitaBrutaComST     , // '203' // Simples Nacional - Isen��o do ICMS no Simples Nacional para faixa de receita bruta e com cobran�a do ICMS por substitui��o tribut�ria
                   sticmsSimplesNacionalImune                                , // '300' // Simples Nacional - Imune
                   sticmsSimplesNacionalNaoTributada                         , // '400' // Simples Nacional - N�o tributada pelo Simples Nacional
                   sticmsSimplesNacionalCobradoAnteriormentePorST            , // '500' // Simples Nacional - ICMS cobrado anteriormente por substitui��o tribut�ria (substitu�do) ou por antecipa��o
                   sticmsSimplesNacionalOutros                                 // '900' // Simples Nacional - Outros
                );
  TACBrSituacaoTribICMS = TACBrCstIcms;

  /// C�digo da Situa��o Tribut�ria referente ao IPI.
  TACBrCstIpi = (
                 stipiEntradaRecuperacaoCredito ,// '00' // Entrada com recupera��o de cr�dito
                 stipiEntradaTributradaZero     ,// '01' // Entrada tributada com al�quota zero
                 stipiEntradaIsenta             ,// '02' // Entrada isenta
                 stipiEntradaNaoTributada       ,// '03' // Entrada n�o-tributada
                 stipiEntradaImune              ,// '04' // Entrada imune
                 stipiEntradaComSuspensao       ,// '05' // Entrada com suspens�o
                 stipiOutrasEntradas            ,// '49' // Outras entradas
                 stipiSaidaTributada            ,// '50' // Sa�da tributada
                 stipiSaidaTributadaZero        ,// '51' // Sa�da tributada com al�quota zero
                 stipiSaidaIsenta               ,// '52' // Sa�da isenta
                 stipiSaidaNaoTributada         ,// '53' // Sa�da n�o-tributada
                 stipiSaidaImune                ,// '54' // Sa�da imune
                 stipiSaidaComSuspensao         ,// '55' // Sa�da com suspens�o
                 stipiOutrasSaidas               // '99' // Outras sa�das
                );
  TACBrSituacaoTribIPI = TACBrCstIpi;

  /// C�digo da Situa��o Tribut�ria referente ao PIS.
  TACBrCstPis = (
                  stpisValorAliquotaNormal,                            // '01' // Opera��o Tribut�vel com Al�quota B�sica   // valor da opera��o al�quota normal (cumulativo/n�o cumulativo)).
                  stpisValorAliquotaDiferenciada,                      // '02' // Opera��o Tribut�vel com Al�quota Diferenciada // valor da opera��o (al�quota diferenciada)).
                  stpisQtdeAliquotaUnidade,                            // '03' // Opera��o Tribut�vel com Al�quota por Unidade de Medida de Produto // quantidade vendida x al�quota por unidade de produto).
                  stpisMonofaticaAliquotaZero,                         // '04' // Opera��o Tribut�vel Monof�sica - Revenda a Al�quota Zero
                  stpisValorAliquotaPorST,                             // '05' // Opera��o Tribut�vel por Substitui��o Tribut�ria
                  stpisAliquotaZero,                                   // '06' // Opera��o Tribut�vel a Al�quota Zero
                  stpisIsentaContribuicao,                             // '07' // Opera��o Isenta da Contribui��o
                  stpisSemIncidenciaContribuicao,                      // '08' // Opera��o sem Incid�ncia da Contribui��o
                  stpisSuspensaoContribuicao,                          // '09' // Opera��o com Suspens�o da Contribui��o
                  stpisOutrasOperacoesSaida,                           // '49' // Outras Opera��es de Sa�da
                  stpisOperCredExcRecTribMercInt,                      // '50' // Opera��o com Direito a Cr�dito - Vinculada Exclusivamente a Receita Tributada no Mercado Interno
                  stpisOperCredExcRecNaoTribMercInt,                   // '51' // Opera��o com Direito a Cr�dito � Vinculada Exclusivamente a Receita N�o Tributada no Mercado Interno
                  stpisOperCredExcRecExportacao ,                      // '52' // Opera��o com Direito a Cr�dito - Vinculada Exclusivamente a Receita de Exporta��o
                  stpisOperCredRecTribNaoTribMercInt,                  // '53' // Opera��o com Direito a Cr�dito - Vinculada a Receitas Tributadas e N�o-Tributadas no Mercado Interno
                  stpisOperCredRecTribMercIntEExportacao,              // '54' // Opera��o com Direito a Cr�dito - Vinculada a Receitas Tributadas no Mercado Interno e de Exporta��o
                  stpisOperCredRecNaoTribMercIntEExportacao,           // '55' // Opera��o com Direito a Cr�dito - Vinculada a Receitas N�o-Tributadas no Mercado Interno e de Exporta��o
                  stpisOperCredRecTribENaoTribMercIntEExportacao,      // '56' // Opera��o com Direito a Cr�dito - Vinculada a Receitas Tributadas e N�o-Tributadas no Mercado Interno, e de Exporta��o
                  stpisCredPresAquiExcRecTribMercInt,                  // '60' // Cr�dito Presumido - Opera��o de Aquisi��o Vinculada Exclusivamente a Receita Tributada no Mercado Interno
                  stpisCredPresAquiExcRecNaoTribMercInt,               // '61' // Cr�dito Presumido - Opera��o de Aquisi��o Vinculada Exclusivamente a Receita N�o-Tributada no Mercado Interno
                  stpisCredPresAquiExcExcRecExportacao,                // '62' // Cr�dito Presumido - Opera��o de Aquisi��o Vinculada Exclusivamente a Receita de Exporta��o
                  stpisCredPresAquiRecTribNaoTribMercInt,              // '63' // Cr�dito Presumido - Opera��o de Aquisi��o Vinculada a Receitas Tributadas e N�o-Tributadas no Mercado Interno
                  stpisCredPresAquiRecTribMercIntEExportacao,          // '64' // Cr�dito Presumido - Opera��o de Aquisi��o Vinculada a Receitas Tributadas no Mercado Interno e de Exporta��o
                  stpisCredPresAquiRecNaoTribMercIntEExportacao,       // '65' // Cr�dito Presumido - Opera��o de Aquisi��o Vinculada a Receitas N�o-Tributadas no Mercado Interno e de Exporta��o
                  stpisCredPresAquiRecTribENaoTribMercIntEExportacao,  // '66' // Cr�dito Presumido - Opera��o de Aquisi��o Vinculada a Receitas Tributadas e N�o-Tributadas no Mercado Interno, e de Exporta��o
                  stpisOutrasOperacoes_CredPresumido,                  // '67' // Cr�dito Presumido - Outras Opera��es
                  stpisOperAquiSemDirCredito,                          // '70' // Opera��o de Aquisi��o sem Direito a Cr�dito
                  stpisOperAquiComIsensao,                             // '71' // Opera��o de Aquisi��o com Isen��o
                  stpisOperAquiComSuspensao,                           // '72' // Opera��o de Aquisi��o com Suspens�o
                  stpisOperAquiAliquotaZero,                           // '73' // Opera��o de Aquisi��o a Al�quota Zero
                  stpisOperAqui_SemIncidenciaContribuicao,             // '74' // Opera��o de Aquisi��o sem Incid�ncia da Contribui��o
                  stpisOperAquiPorST,                                  // '75' // Opera��o de Aquisi��o por Substitui��o Tribut�ria
                  stpisOutrasOperacoesEntrada,                         // '98' // Outras Opera��es de Entrada
                  stpisOutrasOperacoes                                 // '99' // Outras Opera��es
                 );
  TACBrSituacaoTribPIS = TACBrCstPis;

  /// C�digo da Situa��o Tribut�ria referente ao COFINS.
  TACBrCstCofins = (
                    stcofinsValorAliquotaNormal,                           // '01' // Opera��o Tribut�vel com Al�quota B�sica                           // valor da opera��o al�quota normal (cumulativo/n�o cumulativo)).
                    stcofinsValorAliquotaDiferenciada,                     // '02' // Opera��o Tribut�vel com Al�quota Diferenciada                     // valor da opera��o (al�quota diferenciada)).
                    stcofinsQtdeAliquotaUnidade,                           // '03' // Opera��o Tribut�vel com Al�quota por Unidade de Medida de Produto // quantidade vendida x al�quota por unidade de produto).
                    stcofinsMonofaticaAliquotaZero,                        // '04' // Opera��o Tribut�vel Monof�sica - Revenda a Al�quota Zero
                    stcofinsValorAliquotaPorST,                            // '05' // Opera��o Tribut�vel por Substitui��o Tribut�ria
                    stcofinsAliquotaZero,                                  // '06' // Opera��o Tribut�vel a Al�quota Zero
                    stcofinsIsentaContribuicao,                            // '07' // Opera��o Isenta da Contribui��o
                    stcofinsSemIncidenciaContribuicao,                     // '08' // Opera��o sem Incid�ncia da Contribui��o
                    stcofinsSuspensaoContribuicao,                         // '09' // Opera��o com Suspens�o da Contribui��o
                    stcofinsOutrasOperacoesSaida,                          // '49' // Outras Opera��es de Sa�da
                    stcofinsOperCredExcRecTribMercInt,                     // '50' // Opera��o com Direito a Cr�dito - Vinculada Exclusivamente a Receita Tributada no Mercado Interno
                    stcofinsOperCredExcRecNaoTribMercInt,                  // '51' // Opera��o com Direito a Cr�dito - Vinculada Exclusivamente a Receita N�o-Tributada no Mercado Interno
                    stcofinsOperCredExcRecExportacao ,                     // '52' // Opera��o com Direito a Cr�dito - Vinculada Exclusivamente a Receita de Exporta��o
                    stcofinsOperCredRecTribNaoTribMercInt,                 // '53' // Opera��o com Direito a Cr�dito - Vinculada a Receitas Tributadas e N�o-Tributadas no Mercado Interno
                    stcofinsOperCredRecTribMercIntEExportacao,             // '54' // Opera��o com Direito a Cr�dito - Vinculada a Receitas Tributadas no Mercado Interno e de Exporta��o
                    stcofinsOperCredRecNaoTribMercIntEExportacao,          // '55' // Opera��o com Direito a Cr�dito - Vinculada a Receitas N�o Tributadas no Mercado Interno e de Exporta��o
                    stcofinsOperCredRecTribENaoTribMercIntEExportacao,     // '56' // Opera��o com Direito a Cr�dito - Vinculada a Receitas Tributadas e N�o-Tributadas no Mercado Interno e de Exporta��o
                    stcofinsCredPresAquiExcRecTribMercInt,                 // '60' // Cr�dito Presumido - Opera��o de Aquisi��o Vinculada Exclusivamente a Receita Tributada no Mercado Interno
                    stcofinsCredPresAquiExcRecNaoTribMercInt,              // '61' // Cr�dito Presumido - Opera��o de Aquisi��o Vinculada Exclusivamente a Receita N�o-Tributada no Mercado Interno
                    stcofinsCredPresAquiExcExcRecExportacao,               // '62' // Cr�dito Presumido - Opera��o de Aquisi��o Vinculada Exclusivamente a Receita de Exporta��o
                    stcofinsCredPresAquiRecTribNaoTribMercInt,             // '63' // Cr�dito Presumido - Opera��o de Aquisi��o Vinculada a Receitas Tributadas e N�o-Tributadas no Mercado Interno
                    stcofinsCredPresAquiRecTribMercIntEExportacao,         // '64' // Cr�dito Presumido - Opera��o de Aquisi��o Vinculada a Receitas Tributadas no Mercado Interno e de Exporta��o
                    stcofinsCredPresAquiRecNaoTribMercIntEExportacao,      // '65' // Cr�dito Presumido - Opera��o de Aquisi��o Vinculada a Receitas N�o-Tributadas no Mercado Interno e de Exporta��o
                    stcofinsCredPresAquiRecTribENaoTribMercIntEExportacao, // '66' // Cr�dito Presumido - Opera��o de Aquisi��o Vinculada a Receitas Tributadas e N�o-Tributadas no Mercado Interno e de Exporta��o
                    stcofinsOutrasOperacoes_CredPresumido,                 // '67' // Cr�dito Presumido - Outras Opera��es
                    stcofinsOperAquiSemDirCredito,                         // '70' // Opera��o de Aquisi��o sem Direito a Cr�dito
                    stcofinsOperAquiComIsensao,                            // '71' // Opera��o de Aquisi��o com Isen��o
                    stcofinsOperAquiComSuspensao,                          // '72' // Opera��o de Aquisi��o com Suspens�o
                    stcofinsOperAquiAliquotaZero,                          // '73' // Opera��o de Aquisi��o a Al�quota Zero
                    stcofinsOperAqui_SemIncidenciaContribuicao,            // '74' // Opera��o de Aquisi��o sem Incid�ncia da Contribui��o
                    stcofinsOperAquiPorST,                                 // '75' // Opera��o de Aquisi��o por Substitui��o Tribut�ria
                    stcofinsOutrasOperacoesEntrada,                        // '98' // Outras Opera��es de Entrada
                    stcofinsOutrasOperacoes                                // '99' // Outras Opera��es
                  );
  TACBrSituacaoTribCOFINS = TACBrCstCofins;

  // Local da Execu��o do Servi�o
  TACBrLocalExecServico = (
                            lesExecutPais,     // 0 � Executado no Pa�s;
                            lesExecutExterior  // 1 � Executado no Exterior, cujo resultado se verifique no Pa�s.
                          );




  //C�digo indicador da tabela de incidencia, conforme anexo III
  TACBrIndCodIncidencia = (
                            codIndiTabNaoTem,
                            codIndTabI,     // 01 - Tabela I
                            codIndTabII,    // 02 - Tabela II
                            codIndTabIII,   // 03 - Tabela III
                            codIndTabIV,    // 04 - Tabela IV
                            codIndTabV,     // 05 - Tabela V
                            codIndTabVI,    // 06 - Tabela VI
                            codIndTabVII,   // 07 - Tabela VII
                            codIndTabVIII,  // 08 - Tabela VIII
                            codIndTabIX,    // 09 - Tabela IX
                            codIndTabX,     // 10 - Tabela X
                            codIndTabXI,    // 11 - Tabela XI
                            codIndiTabXII   // 12 - Tabela XII
                          );
  //Indicador do tipo de conta (0500)
  TACBrIndCTA = (
                   indCTASintetica,  //S Sint�tica
                   indCTAnalitica    //A Analitica
                );
  //Indicador da apura��o das contribui��es e cr�ditos, na escritura��o das opera��es por NF-e e ECF (C010)
  TACBrIndEscrituracao = (
                          IndEscriConsolidado,     //1 � Apura��o com base nos registros de consolida��o das opera��es por NF-e (C180 e C190) e por ECF (C490);
                          IndEscriIndividualizado  //2 � Apura��o com base no registro individualizado de NF-e (C100 e C170) e de ECF (C400)
                         );

  //Indicador do tipo de ajuste
  TACBrIndAJ = (
                  indAjReducao,  // '0' // Ajuste de redu��o;
                  indAjAcressimo // '1' // Ajuste de acr�scimo.
                );
  //4.3.8 - Tabela C�digo de Ajustes de Contribui��o ou Cr�ditos:
  TACBrCodAj = (
                  codAjAcaoJudicial,      // '01' // Ajuste Oriundo de A��o Judicial
                  codAjProAdministrativo, // '02' // Ajuste Oriundo de Processo Administrativo
                  codAjLegTributaria,     // '03' // Ajuste Oriundo da Legisla��o Tribut�ria
                  codAjEspRTI,            // '04' // Ajuste Oriundo Especificamente do RTT
                  codAjOutrasSituacaoes,  // '05' // Ajuste Oriundo de Outras Situa��es
                  codAjEstorno            // '06' // Estorno
                );
  //Indicador da Natureza da Receita
  TACBrIndNatRec = (
                    inrNaoCumulativa = 0, // 0 // Receita de Natureza N�o Cumulativa
                    inrCumulativa    = 1  // 1 // Receita de Natureza Cumulativa
                   );
  //Natureza do Cr�dito Diferido, vinculado � receita tributada no mercado interno, a descontar
  TACBrNatCredDesc = (
                      ncdAliqBasica,       //'01' // Cr�dito a Al�quota B�sica;
                      ncdAliqDiferenciada, //'02' // Cr�dito a Al�quota Diferenciada;
                      ncdAliqUnidProduto,  //'03' // Cr�dito a Al�quota por Unidade de Produto;
                      ncdPresAgroindustria //'04' // Cr�dito Presumido da Agroind�stria.
                   );
  //4.3.6 - Tabela C�digo de Tipo de Cr�dito
  TACBrCodCred = (
//                  C�DIGOS VINCULADOS � RECEITA TRIBUTADA NO MERCADO INTERNO - Grupo 100
                    ccRTMIAliqBasica,        // '101' // Cr�dito vinculado � receita tributada no mercado interno - Al�quota B�sica
                    ccRTMIAliqDiferenciada,  // '102' // Cr�dito vinculado � receita tributada no mercado interno - Al�quotas Diferenciadas
                    ccRTMIAliqUnidProduto,   // '103' // Cr�dito vinculado � receita tributada no mercado interno - Al�quota por Unidade de Produto
                    ccRTMIEstAbertura,       // '104' // Cr�dito vinculado � receita tributada no mercado interno - Estoque de Abertura
                    ccRTMIAquiEmbalagem,     // '105' // Cr�dito vinculado � receita tributada no mercado interno - Aquisi��o Embalagens para revenda
                    ccRTMIPreAgroindustria,  // '106' // Cr�dito vinculado � receita tributada no mercado interno - Presumido da Agroind�stria
                    ccRTMIImportacao,        // '108' // Cr�dito vinculado � receita tributada no mercado interno - Importa��o
                    ccRTMIAtivImobiliaria,   // '109' // Cr�dito vinculado � receita tributada no mercado interno - Atividade Imobili�ria
                    ccRTMIOutros,            // '199' // Cr�dito vinculado � receita tributada no mercado interno - Outros
                  //C�DIGOS VINCULADOS � RECEITA N�O TRIBUTADA NO MERCADO INTERNO - Grupo 200
                    ccRNTMIAliqBasica,       // '201' // Cr�dito vinculado � receita n�o tributada no mercado interno - Al�quota B�sica
                    ccRNTMIAliqDiferenciada, // '202' // Cr�dito vinculado � receita n�o tributada no mercado interno - Al�quotas Diferenciadas
                    ccRNTMIAliqUnidProduto,  // '203' // Cr�dito vinculado � receita n�o tributada no mercado interno - Al�quota por Unidade de Produto
                    ccRNTMIEstAbertura,      // '204' // Cr�dito vinculado � receita n�o tributada no mercado interno - Estoque de Abertura
                    ccRNTMIAquiEmbalagem,    // '205' // Cr�dito vinculado � receita n�o tributada no mercado interno - Aquisi��o Embalagens para revenda
                    ccRNTMIPreAgroindustria, // '206' // Cr�dito vinculado � receita n�o tributada no mercado interno - Presumido da Agroind�stria
                    ccRNTMIImportacao,       // '208' // Cr�dito vinculado � receita n�o tributada no mercado interno - Importa��o
                    ccRNTMIOutros,           // '299' // Cr�dito vinculado � receita n�o tributada no mercado interno - Outros
                  //C�DIGOS VINCULADOS � RECEITA DE EXPORTA��O - Grupo 300
                    ccREAliqBasica,          // '301' // Cr�dito vinculado � receita de exporta��o - Al�quota B�sica
                    ccREAliqDiferenciada,    // '302' // Cr�dito vinculado � receita de exporta��o - Al�quotas Diferenciadas
                    ccREAliqUnidProduto,     // '303' // Cr�dito vinculado � receita de exporta��o - Al�quota por Unidade de Produto
                    ccREEstAbertura,         // '304' // Cr�dito vinculado � receita de exporta��o - Estoque de Abertura
                    ccREAquiEmbalagem,       // '305' // Cr�dito vinculado � receita de exporta��o - Aquisi��o Embalagens para revenda
                    ccREPreAgroindustria,    // '306' // Cr�dito vinculado � receita de exporta��o - Presumido da Agroind�stria
                    ccREPreAgroindustriaPCR, // '307' // Cr�dito vinculado � receita de exporta��o - Presumido da Agroind�stria � Pass�vel de Compensa��o e/ou Ressarcimento
                    ccREImportacao,          // '308' // Cr�dito vinculado � receita de exporta��o - Importa��o
                    ccREOutros               // '399' // Cr�dito vinculado � receita de exporta��o - Outros
                 );
  //Indicador do Tipo de Sociedade Cooperativa:
  TACBrIndTipCoop = (
                      itcProdAgropecuaria, // '01' // Cooperativa de Produ��o Agropecu�ria;
                      itcConsumo,          // '02' // Cooperativa de Consumo;
                      itcCredito,          // '03' // Cooperativa de Cr�dito;
                      itcEletRural,        // '04' // Cooperativa de Eletrifica��o Rural;
                      itcTransCargas,      // '05' // Cooperativa de Transporte Rodovi�rio de Cargas;
                      itcMedicos,          // '06' // Cooperativa de M�dicos;
                      itcOutras            // '99' // Outras.
                     );
  //Indicador de Cr�dito Oriundo de:
  TACBrIndCredOri = (
                      icoOperProprias   = 0, // 0 // Opera��es pr�prias
                      icoEvenFusaoCisao = 1 // 1 // Evento de incorpora��o, cis�o ou fus�o
                     );

  //Indicador do tipo de receita:
  TACBrIndRec = (
                  irPropServPrestados         = 0,  // 0 // Receita pr�pria - servi�os prestados;
                  irPropCobDebitos            = 1,  // 1 // Receita pr�pria - cobran�a de d�bitos;
                  irPropServPrePagAnterior    = 2,  // 2 // Receita pr�pria - venda de servi�o pr�-pago � faturamento de per�odos anteriores;
                  irPropServPrePagAtual       = 3,  // 3 // Receita pr�pria - venda de servi�o pr�-pago � faturamento no per�odo;
                  irPropServOutrosComunicacao = 4,  // 4 // Outras receitas pr�prias de servi�os de comunica��o e telecomunica��o;
                  irCFaturamento              = 5,  // 5 // Receita pr�pria - co-faturamento;
                  irServAFaturar              = 6,  // 6 // Receita pr�pria � servi�os a faturar em per�odo futuro;
                  irNaoAcumulativa            = 7,  // 7 // Outras receitas pr�prias de natureza n�o-cumulativa;
                  irTerceiros                 = 8,  // 8 // Outras receitas de terceiros
                  irOutras                    = 9   // 9 // Outras receitas
                 );

  //Indicador de op��o de utiliza��o do cr�dito dispon�vel no per�odo:
  TACBrIndDescCred = (
                       idcTotal   = 0, // 0 // Utiliza��o do valor total para desconto da contribui��o apurada no per�odo, no Registro M200;
                       idcParcial = 1  // 1 // Utiliza��o de valor parcial para desconto da contribui��o apurada no per�odo, no Registro M200
                     );

  //4.3.5 - Tabela C�digo de Contribui��o Social Apurada
  TACBrCodCont = (
                    ccNaoAcumAliqBasica ,                // 01 // Contribui��o n�o-cumulativa apurada a al�quota b�sica
                    ccNaoAcumAliqDiferenciada ,          // 02 // Contribui��o n�o-cumulativa apurada a al�quotas diferenciadas
                    ccNaoAcumAliqUnidProduto ,           // 03 // Contribui��o n�o-cumulativa apurada a al�quota por unidade de medida de produto
                    ccNaoAcumAliqBasicaAtivImobiliaria , // 04 // Contribui��o n�o-cumulativa apurada a al�quota b�sica - Atividade Imobili�ria
                    ccApuradaPorST ,                     // 31 // Contribui��o apurada por substitui��o tribut�ria
                    ccApuradaPorSTManaus ,               // 32 // Contribui��o apurada por substitui��o tribut�ria - Vendas � Zona Franca de Manaus
                    ccAcumAliqBasica ,                   // 51 // Contribui��o cumulativa apurada a al�quota b�sica
                    ccAcumAliqDiferenciada ,             // 52 // Contribui��o cumulativa apurada a al�quotas diferenciadas
                    ccAcumAliqUnidProduto ,              // 53 // Contribui��o cumulativa apurada a al�quota por unidade de medida de produto
                    ccAcumAliqBasicaAtivImobiliaria ,    // 54 // Contribui��o cumulativa apurada a al�quota b�sica - Atividade Imobili�ria
                    ccApuradaAtivImobiliaria ,           // 70 // Contribui��o apurada da Atividade Imobili�ria - RET
                    ccApuradaSCPNaoCumulativa ,          // 71 // Contribui��o apurada de SCP - Incid�ncia N�o Cumulativa
                    ccApuradaSCPCumulativa  ,            // 72 // Contribui��o apurada de SCP - Incid�ncia Cumulativa
                    ccPISPasepSalarios                   // 99 // Contribui��o para o PIS/Pasep - Folha de Sal�rios
                 );

   //Indicador de Natureza da Reten��o na Fonte:
   TACBrIndNatRetFonte = (
                           indRetOrgAutarquiasFundFederais, // 01 - Reten��o por �rg�os, Autarquias e Funda��es Federais
                           indRetEntAdmPublicaFederal,      // 02 - Reten��o por outras Entidades da Administra��o P�blica Federal
                           indRetPesJuridicasDireitoPri,    // 03 - Reten��o por Pessoas Jur�dicas de Direito Privado
                           indRecolhimentoSociedadeCoop,    // 04 - Recolhimento por Sociedade Cooperativa
                           indRetFabricanteMaqVeiculos,     // 05 - Reten��o por Fabricante de M�quinas e Ve�culos
                           indOutrasRetencoes               // 99 - Outras Reten��es
                          );

   //Indicador de Origem de Dedu��es Diversas:
   TACBrIndOrigemDiversas = (

                              indCredPreMed,              // 01 � Cr�ditos Presumidos - Medicamentos
                              indCredAdmRegCumulativoBeb, // 02 � Cr�ditos Admitidos no Regime Cumulativo � Bebidas Frias
                              indContribSTZFM,            // 03 � Contribui��o Paga pelo Substituto Tribut�rio - ZFM
                              indSTNaoOCFatoGeradorPres,  // 04 � Substitui��o Tribut�ria � N�o Ocorr�ncia do Fato Gerador Presumido
                              indOutrasDeducoes           // 99 - Outras Dedu��es

                             );

   //Indicador da Natureza da Dedu��o:
   TACBrIndNatDeducao = (
                          indNaoAcumulativa,  // 0 � Dedu��o de Natureza N�o Cumulativa
                          indAcumulativa // 1 � Dedu��o de Natureza Cumulativa
                        );

   //Indicador do Tipo da Opera��o (RegsitroF100 - IND_OPER):
   TACBrIndTpOperacaoReceita = (
                          indRepCustosDespesasEncargos, //0 � Opera��o Representativa de Aquisi��o, Custos, Despesa ou Encargos, Sujeita � Incid�ncia de Cr�dito de PIS/Pasep ou Cofins (CST 50 a 66).
                          indRepReceitaAuferida,        //1 � Opera��o Representativa de Receita Auferida Sujeita ao Pagamento da Contribui��o para o PIS/Pasep e da Cofins (CST 01, 02, 03 ou 05).
                          indRepReceitaNaoAuferida      //2 - Opera��o Representativa de Receita Auferida N�o Sujeita ao Pagamento da Contribui��o para o PIS/Pasep e da Cofins (CST 04, 06, 07, 08, 09, 49 ou 99).
                        );

   //Indicador da composi��o da receita recebida no per�odo (RegsitroF525 - IND_REC):
   TACBrInd_Rec = (
                   crCliente,          //01- Clientes
                   crAdministradora,   //02- Administradora de cart�o de d�bito/cr�dito
                   crTituloDeCredito,  //03- T�tulo de cr�dito - Duplicata, nota promiss�ria, cheque, etc.
                   crDocumentoFiscal,  //04- Documento fiscal
                   crItemVendido,      //05- Item vendido (produtos e servi�os)
                   crOutros            //99- Outros (Detalhar no campo 10 � Informa��o Complementar)
                 );
   TACBrIndicadorDaComposicaoDaReceitaRecebida = TACBrInd_Rec;

  TOpenBlocos = class
  private
    FIND_MOV: TACBrIndMov;    /// Indicador de movimento: 0- Bloco com dados informados, 1- Bloco sem dados informados.
  public
    property IND_MOV: TACBrIndMov read FIND_MOV write FIND_MOV;
  end;

const
cstcofins01 = stcofinsValorAliquotaNormal;
cstcofins02 = stcofinsValorAliquotaDiferenciada;
cstcofins03 = stcofinsQtdeAliquotaUnidade;
cstcofins04 = stcofinsMonofaticaAliquotaZero;
cstcofins05 = stcofinsValorAliquotaPorST;
cstcofins06 = stcofinsAliquotaZero;
cstcofins07 = stcofinsIsentaContribuicao;
cstcofins08 = stcofinsSemIncidenciaContribuicao;
cstcofins09 = stcofinsSuspensaoContribuicao;
cstcofins49 = stcofinsOutrasOperacoesSaida;
cstcofins50 = stcofinsOperCredExcRecTribMercInt;
cstcofins51 = stcofinsOperCredExcRecNaoTribMercInt;
cstcofins52 = stcofinsOperCredExcRecExportacao ;
cstcofins53 = stcofinsOperCredRecTribNaoTribMercInt;
cstcofins54 = stcofinsOperCredRecTribMercIntEExportacao;
cstcofins55 = stcofinsOperCredRecNaoTribMercIntEExportacao;
cstcofins56 = stcofinsOperCredRecTribENaoTribMercIntEExportacao;
cstcofins60 = stcofinsCredPresAquiExcRecTribMercInt;
cstcofins61 = stcofinsCredPresAquiExcRecNaoTribMercInt;
cstcofins62 = stcofinsCredPresAquiExcExcRecExportacao;
cstcofins63 = stcofinsCredPresAquiRecTribNaoTribMercInt;
cstcofins64 = stcofinsCredPresAquiRecTribMercIntEExportacao;
cstcofins65 = stcofinsCredPresAquiRecNaoTribMercIntEExportacao;
cstcofins66 = stcofinsCredPresAquiRecTribENaoTribMercIntEExportacao;
cstcofins67 = stcofinsOutrasOperacoes_CredPresumido;
cstcofins70 = stcofinsOperAquiSemDirCredito;
cstcofins71 = stcofinsOperAquiComIsensao;
cstcofins72 = stcofinsOperAquiComSuspensao;
cstcofins73 = stcofinsOperAquiAliquotaZero;
cstcofins74 = stcofinsOperAqui_SemIncidenciaContribuicao;
cstcofins75 = stcofinsOperAquiPorST;
cstcofins98 = stcofinsOutrasOperacoesEntrada;
cstcofins99 = stcofinsOutrasOperacoes;

cstpis01 = stpisValorAliquotaNormal;
cstpis02 = stpisValorAliquotaDiferenciada;
cstpis03 = stpisQtdeAliquotaUnidade;
cstpis04 = stpisMonofaticaAliquotaZero;
cstpis05 = stpisValorAliquotaPorST;
cstpis06 = stpisAliquotaZero;
cstpis07 = stpisIsentaContribuicao;
cstpis08 = stpisSemIncidenciaContribuicao;
cstpis09 = stpisSuspensaoContribuicao;
cstpis49 = stpisOutrasOperacoesSaida;
cstpis50 = stpisOperCredExcRecTribMercInt;
cstpis51 = stpisOperCredExcRecNaoTribMercInt;
cstpis52 = stpisOperCredExcRecExportacao;
cstpis53 = stpisOperCredRecTribNaoTribMercInt;
cstpis54 = stpisOperCredRecTribMercIntEExportacao;
cstpis55 = stpisOperCredRecNaoTribMercIntEExportacao;
cstpis56 = stpisOperCredRecTribENaoTribMercIntEExportacao;
cstpis60 = stpisCredPresAquiExcRecTribMercInt;
cstpis61 = stpisCredPresAquiExcRecNaoTribMercInt;
cstpis62 = stpisCredPresAquiExcExcRecExportacao;
cstpis63 = stpisCredPresAquiRecTribNaoTribMercInt;
cstpis64 = stpisCredPresAquiRecTribMercIntEExportacao;
cstpis65 = stpisCredPresAquiRecNaoTribMercIntEExportacao;
cstpis66 = stpisCredPresAquiRecTribENaoTribMercIntEExportacao;
cstpis67 = stpisOutrasOperacoes_CredPresumido;
cstpis70 = stpisOperAquiSemDirCredito;
cstpis71 = stpisOperAquiComIsensao;
cstpis72 = stpisOperAquiComSuspensao;
cstpis73 = stpisOperAquiAliquotaZero;
cstpis74 = stpisOperAqui_SemIncidenciaContribuicao;
cstpis75 = stpisOperAquiPorST;
cstpis98 = stpisOutrasOperacoesEntrada;
cstpis99 = stpisOutrasOperacoes;

csticms000 = sticmsTributadaIntegralmente;
csticms010 = sticmsTributadaComCobracaPorST;
csticms020 = sticmsComReducao;
csticms030 = sticmsIsentaComCobracaPorST;
csticms040 = sticmsIsenta;
csticms041 = sticmsNaoTributada;
csticms050 = sticmsSuspensao;
csticms051 = sticmsDiferimento;
csticms060 = sticmsCobradoAnteriormentePorST;
csticms070 = sticmsComReducaoPorST;
csticms090 = sticmsOutros;
csticms100 = sticmsEstrangeiraImportacaoDiretaTributadaIntegralmente;
csticms110 = sticmsEstrangeiraImportacaoDiretaTributadaComCobracaPorST;
csticms120 = sticmsEstrangeiraImportacaoDiretaComReducao;
csticms130 = sticmsEstrangeiraImportacaoDiretaIsentaComCobracaPorST;
csticms140 = sticmsEstrangeiraImportacaoDiretaIsenta;
csticms141 = sticmsEstrangeiraImportacaoDiretaNaoTributada;
csticms150 = sticmsEstrangeiraImportacaoDiretaSuspensao;
csticms151 = sticmsEstrangeiraImportacaoDiretaDiferimento;
csticms160 = sticmsEstrangeiraImportacaoDiretaCobradoAnteriormentePorST;
csticms170 = sticmsEstrangeiraImportacaoDiretaComReducaoPorST;
csticms190 = sticmsEstrangeiraImportacaoDiretaOutros;
csticms200 = sticmsEstrangeiraAdqMercIntTributadaIntegralmente;
csticms210 = sticmsEstrangeiraAdqMercIntTributadaComCobracaPorST;
csticms220 = sticmsEstrangeiraAdqMercIntComReducao;
csticms230 = sticmsEstrangeiraAdqMercIntIsentaComCobracaPorST;
csticms240 = sticmsEstrangeiraAdqMercIntIsenta;
csticms241 = sticmsEstrangeiraAdqMercIntNaoTributada;
csticms250 = sticmsEstrangeiraAdqMercIntSuspensao;
csticms251 = sticmsEstrangeiraAdqMercIntDiferimento;
csticms260 = sticmsEstrangeiraAdqMercIntCobradoAnteriormentePorST;
csticms270 = sticmsEstrangeiraAdqMercIntComReducaoPorST;
csticms290 = sticmsEstrangeiraAdqMercIntOutros;
csticms101 = sticmsSimplesNacionalTributadaComPermissaoCredito;
csticms102 = sticmsSimplesNacionalTributadaSemPermissaoCredito;
csticms103 = sticmsSimplesNacionalIsencaoPorFaixaReceitaBruta;
csticms201 = sticmsSimplesNacionalTributadaComPermissaoCreditoComST;
csticms202 = sticmsSimplesNacionalTributadaSemPermissaoCreditoComST;
csticms203 = sticmsSimplesNacionalIsencaoPorFaixaReceitaBrutaComST;
csticms300 = sticmsSimplesNacionalImune;
csticms400 = sticmsSimplesNacionalNaoTributada;
csticms500 = sticmsSimplesNacionalCobradoAnteriormentePorST;
csticms900 = sticmsSimplesNacionalOutros;

cstipi00 = stipiEntradaRecuperacaoCredito;
cstipi01 = stipiEntradaTributradaZero;
cstipi02 = stipiEntradaIsenta;
cstipi03 = stipiEntradaNaoTributada;
cstipi04 = stipiEntradaImune;
cstipi05 = stipiEntradaComSuspensao;
cstipi49 = stipiOutrasEntradas;
cstipi50 = stipiSaidaTributada;
cstipi51 = stipiSaidaTributadaZero;
cstipi52 = stipiSaidaIsenta;
cstipi53 = stipiSaidaNaoTributada;
cstipi54 = stipiSaidaImune;
cstipi55 = stipiSaidaComSuspensao;
cstipi99 = stipiOutrasSaidas;

sdRegular = sdfRegular;
sdExtempRegular = sdfExtRegular;
sdCancelado = sdfCancelado;
sdCanceladoExtemp = sdfExtCancelado;
sdDoctoDenegado = sdfDenegado;
sdDoctoNumInutilizada = sdfInutilizado;
sdFiscalCompl = sdfComplementar;
sdExtempCompl = sdfExtComplementar;
sdRegimeEspecNEsp = sdfEspecial;

function CodVerToStr(AValue: TACBrCodVer): string;
function StrToCodVer(AValue: string): TACBrCodVer;
function TipoEscritToStr(AValue: TACBrTipoEscrit): string;
function StrToTipoEscrit(AValue: string): TACBrTipoEscrit;
function IndNatPJToStr(AValue: TACBrIndNatPJ): string;
function StrToIndNatPJ(AValue: string): TACBrIndNatPJ;
function IndAtivToStr(AValue: TACBrIndAtiv): string;
function StrToIndAtiv(AValue: string): TACBrIndAtiv;
function CodIncTribToStr(AValue: TACBrCodIncTrib): string;
function StrToCodIncTrib(AValue: string): TACBrCodIncTrib;
function IndAproCredToStr(AValue: TACBrIndAproCred): string;
function StrToIndAproCred(AValue: string): TACBrIndAproCred;
function CodTipoContToStr(AValue: TACBrCodTipoCont): string;
function StrToCodTipoCont(AValue: string): TACBrCodTipoCont;
function IndRegCumToStr(AValue: TACBrIndRegCum): string;
function StrToIndRegCum(AValue: string): TACBrIndRegCum;
function TipoItemToStr(AValue: TACBrTipoItem): string;
function StrToTipoItem(AValue: string): TACBrTipoItem;
function IndOperToStr(AVAlue: TACBrIndOper): string;
function StrToIndOper(AVAlue: string): TACBrIndOper;
function IndEmitToStr(AValue: TACBrIndEmit): string;
function StrToIndEmit(AValue: string): TACBrIndEmit;
function CodSitToStr(AValue: TACBrCodSit): string;
function StrToCodSit(AValue: string): TACBrCodSit;
function CodSitFToStr(AValue: TACBrCodSitF): string;
function StrToCodSitF(AValue: string): TACBrCodSitF;
function IndPgtoToStr(AValue: TACBrIndPgto): string;
function StrToIndPgto(AValue: string): TACBrIndPgto;
function NatBcCredToStr(AValue: TACBrNatBcCred): string;
function StrToNatBcCred(AValue: string): TACBrNatBcCred;
function IndOrigCredToStr(AValue: TACBrIndOrigCred): string;
function StrToIndOrigCred(AValue: String): TACBrIndOrigCred;
function CstPisToStr(AValue: TACBrCstPis): string;
function StrToCstPis(AValue: String): TACBrCstPis;
function CstCofinsToStr(AValue: TACBrCstCofins): string;
function StrToCstCofins(AValue: String): TACBrCstCofins;
function CstIcmsToStr(AValue: TACBrCstIcms): string;
function StrToCstIcms(AValue: String): TACBrCstIcms;
function CstIpiToStr(AValue: TACBrCstIpi): string;
function StrToCstIpi(AValue: String): TACBrCstIpi;
function IndTipoOperToStr(AVAlue: TACBrIndTipoOper): string;
function StrToIndTipoOper(AVAlue: string): TACBrIndTipoOper;
function IndMovFisicaToStr(AValue: TACBrIndMovFisica): string;
function StrToIndMovFisica(AValue: string): TACBrIndMovFisica;
function IndFrtToStr(AValue: TACBrIndFrt): string;
function StrToIndFrt(AValue: string): TACBrIndFrt;

implementation

{ TOpenBlocos }

//function StrToEnumerado(const s: string; const AString:
//  array of string; const AEnumerados: array of variant): variant;
//var
//  iFor: integer;
//  bOK: boolean;
//begin
//  result := -1;
//  for iFor := Low(AString) to High(AString) do
//  begin
//    if AnsiSameText(s, AString[iFor]) then
//    begin
//       Result := AEnumerados[iFor];
//       Break;
//    end;
//  end;
//  bOK := Result <> -1;
//  if not bOK then
//    Result := AEnumerados[0];
//end;

//function EnumeradoToStr(const t: variant; const AString:
//  array of string; const AEnumerados: array of variant): variant;
//var
//  iFor: integer;
//begin
//  Result := '';
//  for iFor := Low(AEnumerados) to High(AEnumerados) do
//  begin
//    if t = AEnumerados[iFor] then
//    begin
//      Result := AString[iFor];
//      Break;
//    end;
//  end;
//end;

function CodVerToStr(AValue: TACBrCodVer): string;
begin
   if AValue = vlVersao100 then
      Result := '001'
   else
   if AValue = vlVersao101 then
      Result := '002'
   else
   if AValue = vlVersao200 then
      Result := '002'
   else
   if AValue = vlVersao201 then
      Result := '003'
end;

function StrToCodVer(AValue: string): TACBrCodVer;
begin
   if AValue = '001' then
      Result := vlVersao100
   else
   if AValue = '002' then
      Result := vlVersao200
   else
   if AValue = '003' then
      Result := vlVersao201;
end;

function TipoEscritToStr(AValue: TACBrTipoEscrit): string;
begin
   Result := IntToStr( Integer( AValue ) + 1 );

//   Result := EnumeradoToStr(AValue, ['0', '1'], [tpEscrOriginal,
//                                                 tpEscrRetificadora]);
end;

function StrToTipoEscrit(AValue: string): TACBrTipoEscrit;
begin
   Result := TACBrTipoEscrit( StrToIntDef( AValue, 0) );

//   Result := StrToEnumerado(AValue, ['0', '1'], [tpEscrOriginal,
//                                                 tpEscrRetificadora]);
end;

function IndSitEspToStr(AValue: TACBrIndSitEsp): String;
begin
   if AValue = indNenhum then
      Result := ''
   else
      Result := IntToStr( Integer( AValue ) + 1 );

//   Result := EnumeradoToStr(AValue, ['0', '1', '2', '3', '4', ''], [indSitAbertura,
//                                                                    indSitCisao,
//                                                                    indSitFusao,
//                                                                    indSitIncorporacao,
//                                                                    indSitEncerramento,
//                                                                    indNenhum]);
end;

function StrToIndSitEsp(AValue: string): TACBrIndSitEsp;
begin
   Result := TACBrIndSitEsp( StrToIntDef( AValue, 0) );

//   Result := StrToEnumerado(AValue, ['0', '1', '2', '3', '4', ''], [indSitAbertura,
//                                                                    indSitCisao,
//                                                                    indSitFusao,
//                                                                    indSitIncorporacao,
//                                                                    indSitEncerramento,
//                                                                    indNenhum]);
end;

function IndNatPJToStr(AValue: TACBrIndNatPJ): string;
begin
   Result := IntToStr( Integer( AValue ) + 1 );

//   Result := EnumeradoToStr(AValue, ['0', '1', '2'], [indNatPJSocEmpresariaGeral,
//                                                      indNatPJSocCooperativa,
//                                                      indNatPJEntExclusivaFolhaSal]);
end;

function StrToIndNatPJ(AValue: string): TACBrIndNatPJ;
begin
   Result := TACBrIndNatPJ( StrToIntDef( AValue, 0) );

//   Result := StrToEnumerado(AValue, ['0', '1', '2'], [indNatPJSocEmpresariaGeral,
//                                                      indNatPJSocCooperativa,
//                                                      indNatPJEntExclusivaFolhaSal]);
end;

function IndAtivToStr(AValue: TACBrIndAtiv): string;
begin
   if AValue = indAtivoOutros then
      Result := '9'
   else
      Result := IntToStr( Integer( AValue ) + 1 );

//   Result := EnumeradoToStr(AValue, ['0', '1', '2', '3', '4', '9'], [indAtivIndustrial,
//                                                                     indAtivPrestadorServico,
//                                                                     indAtivComercio,
//                                                                     indAtivoFincanceira,
//                                                                     indAtivoImobiliaria,
//                                                                     indAtivoOutros]);
end;

function StrToIndAtiv(AValue: string): TACBrIndAtiv;
begin
   if AValue = '9' then
      Result := indAtivoOutros
   else
      Result := TACBrIndAtiv( StrToIntDef( AValue, 9) );
end;

function CodIncTribToStr(AValue: TACBrCodIncTrib): string;
begin
   Result := IntToStr( Integer( AValue ) + 1 );

//   Result := EnumeradoToStr(AValue, ['1', '2', '3'], [codEscrOpIncNaoCumulativo,
//                                                      codEscrOpIncCumulativo,
//                                                      codEscrOpIncAmbos]);
end;

function StrToCodIncTrib(AValue: string): TACBrCodIncTrib;
begin
   Result := TACBrCodIncTrib( StrToIntDef( AValue, 1) -1 );

//   Result := StrToEnumerado(AValue, ['1', '2', '3'], [codEscrOpIncNaoCumulativo,
//                                                      codEscrOpIncCumulativo,
//                                                      codEscrOpIncAmbos]);
end;

function IndAproCredToStr(AValue: TACBrIndAproCred): string;
begin
   Result := IntToStr( Integer( AValue ) + 1 );

//   Result := EnumeradoToStr(AValue, ['1', '2'], [indMetodoApropriacaoDireta,
//                                                 indMetodoDeRateioProporcional]);
end;

function StrToIndAproCred(AValue: string): TACBrIndAproCred;
begin
   Result := TACBrIndAproCred( StrToIntDef( AValue, 1) -1 );

//   Result := StrToEnumerado(AValue, ['1', '2'], [indMetodoApropriacaoDireta,
//                                                 indMetodoDeRateioProporcional]);
end;

function CodTipoContToStr(AValue: TACBrCodTipoCont): string;
begin
   Result := IntToStr( Integer( AValue ) + 1 );
end;

function StrToCodTipoCont(AValue: string): TACBrCodTipoCont;
begin
   Result := TACBrCodTipoCont( StrToIntDef( AValue, 0) -1 );
end;

function IndRegCumToStr(AValue: TACBrIndRegCum): string;
begin
   if AValue = codRegimeCompetEscritDetalhada then
      Result := '9'
   else
      Result := IntToStr( Integer( AValue ) + 1 );

//   Result := EnumeradoToStr(AValue, ['1', '2', '9'], [codRegimeCaixa,
//                                                      codRegimeCompetEscritConsolidada,
//                                                      codRegimeCompetEscritDetalhada]);
end;

function StrToIndRegCum(AValue: string): TACBrIndRegCum;
begin
   if AValue = '9' then
      Result := codRegimeCompetEscritDetalhada
   else
      Result := TACBrIndRegCum( StrToIntDef( AValue, 1) -1 );

//   Result := StrToEnumerado(AValue, ['1', '2', '9'], [codRegimeCaixa,
//                                                      codRegimeCompetEscritConsolidada,
//                                                      codRegimeCompetEscritDetalhada]);
end;

function TipoItemToStr(AValue: TACBrTipoItem): string;
begin
   if AValue = tiOutras then
      Result := '99'
   else
      Result := FormatFloat('00', Integer( AValue ));

//   Result := EnumeradoToStr(AValue, ['00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '99'],
//                                    [tiMercadoriaRevenda,
//                                     tiMateriaPrima,
//                                     tiEmbalagem,
//                                     tiProdutoProcesso,
//                                     tiProdutoAcabado,
//                                     tiSubproduto,
//                                     tiProdutoIntermediario,
//                                     tiMaterialConsumo,
//                                     tiAtivoImobilizado,
//                                     tiServicos,
//                                     tiOutrosInsumos,
//                                     tiOutras]);
end;

function StrToTipoItem(AValue: string): TACBrTipoItem;
begin
   if AValue = '99' then
      Result := tiOutras
   else
      Result := TACBrTipoItem( StrToIntDef( AValue, 0) );

//   Result := StrToEnumerado(AValue, ['00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '99'],
//                                    [tiMercadoriaRevenda,
//                                     tiMateriaPrima,
//                                     tiEmbalagem,
//                                     tiProdutoProcesso,
//                                     tiProdutoAcabado,
//                                     tiSubproduto,
//                                     tiProdutoIntermediario,
//                                     tiMaterialConsumo,
//                                     tiAtivoImobilizado,
//                                     tiServicos,
//                                     tiOutrosInsumos,
//                                     tiOutras]);
end;

function IndOperToStr(AVAlue: TACBrIndOper): string;
begin
   Result := IntToStr( Integer( AValue ) + 1 );

//   Result := EnumeradoToStr(AVAlue, ['0', '1'], [itoContratado,
//                                                 itoPrestado]);
end;

function StrToIndOper(AVAlue: string): TACBrIndOper;
begin
   Result := TACBrIndOper( StrToIntDef( AValue, 0) );

//   Result := StrToEnumerado(AVAlue, ['0', '1'], [itoContratado,
//                                                 itoPrestado]);
end;

function IndEmitToStr(AValue: TACBrIndEmit): string;
begin
   Result := IntToStr( Integer( AValue ) + 1 );

//   Result := EnumeradoToStr(AValue, ['0', '1'], [iedfProprio,
//                                                 iedfTerceiro]);
end;

function StrToIndEmit(AValue: string): TACBrIndEmit;
begin
   Result := TACBrIndEmit( StrToIntDef( AValue, 0) );

//   Result := StrToEnumerado(AValue, ['0', '1'], [iedfProprio,
//                                                 iedfTerceiro]);
end;

function CodSitToStr(AValue: TACBrCodSit): string;
begin
   Result := FormatFloat('00', Integer( AValue ) );

//   Result := EnumeradoToStr(AValue, ['00', '01', '02', '03', '04', '05', '06', '07', '08'],
//                                    [sdfRegular,
//                                     sdfExtRegular,
//                                     sdfCancelado,
//                                     sdfExtCancelado,
//                                     sdfDenegado,
//                                     sdfInutilizado,
//                                     sdfComplementar,
//                                     sdfExtComplementar,
//                                     sdfEspecial]);
end;

function StrToCodSit(AValue: string): TACBrCodSit;
begin
   Result := TACBrCodSit( StrToIntDef( AValue, 0) );

//   Result := StrToEnumerado(AValue, ['00', '01', '02', '03', '04', '05', '06', '07', '08'],
//                                    [sdfRegular,
//                                     sdfExtRegular,
//                                     sdfCancelado,
//                                     sdfExtCancelado,
//                                     sdfDenegado,
//                                     sdfInutilizado,
//                                     sdfComplementar,
//                                     sdfExtComplementar,
//                                     sdfEspecial]);
end;

function CodSitFToStr(AValue: TACBrCodSitF): string;
begin
   if AValue = csffRegular then
      Result := '00'
   else
   if AValue = csfCancelado then
      Result := '02'
   else
   if AValue = csfOutros then
      Result := '99';
end;

function StrToCodSitF(AValue: string): TACBrCodSitF;
begin
   if AValue = '00' then
      Result := csffRegular
   else
   if AValue = '02' then
      Result := csfCancelado
   else
   if AValue = '99' then
      Result := csfOutros;
end;

function IndPgtoToStr(AValue: TACBrIndPgto): string;
begin
{
Indicador do tipo de pagamento:
0- � vista;
1- A prazo;
9- Sem pagamento.
13  IND_PGTO
Obs.: A partir de 01/07/2012 passar� a ser:
Indicador do tipo de pagamento:
0- � vista;
1- A prazo;
2 - Outros
}
   if AValue = tpSemPagamento then
      Result := '9'
   else
   if AValue = tpNenhum then
      Result := ''
   else
      Result := IntToStr( Integer( AValue ) );
end;

function StrToIndPgto(AValue: string): TACBrIndPgto;
begin
   if AValue = '9' then
      Result := tpSemPagamento
   else
   if AValue = ''  then
      Result := tpNenhum
   else
      Result := TACBrIndPgto( StrToIntDef( AValue, 9) );
end;

function NatBcCredToStr(AValue: TACBrNatBcCred): string;
begin
   if AValue = bccVazio then
      Result := ''
   else
      Result := FormatFloat('00', Integer( AValue ) + 1 );

//   Result := EnumeradoToStr(AValue, ['', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18'],
//                                    [bccVazio,                         // ''   // vazio.
//                                     bccAqBensRevenda,                 // '01' // Aquisi��o de bens para revenda
//                                     bccAqBensUtiComoInsumo,           // '02' // Aquisi��o de bens utilizados como insumo
//                                     bccAqServUtiComoInsumo,           // '03' // Aquisi��o de servi�os utilizados como insumo
//                                     bccEnergiaEletricaTermica,        // '04' // Energia el�trica e t�rmica, inclusive sob a forma de vapor
//                                     bccAluguelPredios,                // '05' // Alugu�is de pr�dios
//                                     bccAluguelMaqEquipamentos,        // '06' // Alugu�is de m�quinas e equipamentos
//                                     bccArmazenagemMercadoria,         // '07' // Armazenagem de mercadoria e frete na opera��o de venda
//                                     bccConArrendamentoMercantil,      // '08' // Contrapresta��es de arrendamento mercantil
//                                     bccMaqCredDepreciacao,            // '09' // M�quinas, equipamentos e outros bens incorporados ao ativo imobilizado (cr�dito sobre encargos de deprecia��o).
//                                     bccMaqCredAquisicao,              // '10' // M�quinas, equipamentos e outros bens incorporados ao ativo imobilizado (cr�dito com base no valor de aquisi��o).
//                                     bccAmortizacaoDepreciacaoImoveis, // '11' // Amortiza��o e Deprecia��o de edifica��es e benfeitorias em im�veis
//                                     bccDevolucaoSujeita,              // '12' // Devolu��o de Vendas Sujeitas � Incid�ncia N�o-Cumulativa
//                                     bccOutrasOpeComDirCredito,        // '13' // Outras Opera��es com Direito a Cr�dito
//                                     bccAtTransporteSubcontratacao,    // '14' // Atividade de Transporte de Cargas � Subcontrata��o
//                                     bccAtImobCustoIncorrido,          // '15' // Atividade Imobili�ria � Custo Incorrido de Unidade Imobili�ria
//                                     bccAtImobCustoOrcado,             // '16' // Atividade Imobili�ria � Custo Or�ado de unidade n�o conclu�da
//                                     bccAtPresServ,                    // '17' // Atividade de Presta��o de Servi�os de Limpeza, Conserva��o e Manuten��o � vale-transporte, vale-refei��o ou vale-alimenta��o, fardamento ou uniforme.
//                                     bccEstoqueAberturaBens            // '18' // Estoque de abertura de bens
//                                     ]);
end;

function StrToNatBcCred(AValue: string): TACBrNatBcCred;
begin
   if AValue = '' then
      Result := bccVazio
   else
      Result := TACBrNatBcCred( StrToIntDef( AValue, 1) );

//   Result := StrToEnumerado(AValue, ['', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18'],
//                                    [bccVazio,                         // ''   // vazio.
//                                     bccAqBensRevenda,                 // '01' // Aquisi��o de bens para revenda
//                                     bccAqBensUtiComoInsumo,           // '02' // Aquisi��o de bens utilizados como insumo
//                                     bccAqServUtiComoInsumo,           // '03' // Aquisi��o de servi�os utilizados como insumo
//                                     bccEnergiaEletricaTermica,        // '04' // Energia el�trica e t�rmica, inclusive sob a forma de vapor
//                                     bccAluguelPredios,                // '05' // Alugu�is de pr�dios
//                                     bccAluguelMaqEquipamentos,        // '06' // Alugu�is de m�quinas e equipamentos
//                                     bccArmazenagemMercadoria,         // '07' // Armazenagem de mercadoria e frete na opera��o de venda
//                                     bccConArrendamentoMercantil,      // '08' // Contrapresta��es de arrendamento mercantil
//                                     bccMaqCredDepreciacao,            // '09' // M�quinas, equipamentos e outros bens incorporados ao ativo imobilizado (cr�dito sobre encargos de deprecia��o).
//                                     bccMaqCredAquisicao,              // '10' // M�quinas, equipamentos e outros bens incorporados ao ativo imobilizado (cr�dito com base no valor de aquisi��o).
//                                     bccAmortizacaoDepreciacaoImoveis, // '11' // Amortiza��o e Deprecia��o de edifica��es e benfeitorias em im�veis
//                                     bccDevolucaoSujeita,              // '12' // Devolu��o de Vendas Sujeitas � Incid�ncia N�o-Cumulativa
//                                     bccOutrasOpeComDirCredito,        // '13' // Outras Opera��es com Direito a Cr�dito
//                                     bccAtTransporteSubcontratacao,    // '14' // Atividade de Transporte de Cargas � Subcontrata��o
//                                     bccAtImobCustoIncorrido,          // '15' // Atividade Imobili�ria � Custo Incorrido de Unidade Imobili�ria
//                                     bccAtImobCustoOrcado,             // '16' // Atividade Imobili�ria � Custo Or�ado de unidade n�o conclu�da
//                                     bccAtPresServ,                    // '17' // Atividade de Presta��o de Servi�os de Limpeza, Conserva��o e Manuten��o � vale-transporte, vale-refei��o ou vale-alimenta��o, fardamento ou uniforme.
//                                     bccEstoqueAberturaBens            // '18' // Estoque de abertura de bens
//                                    ]);
end;

function IndOrigCredToStr(AValue: TACBrIndOrigCred): string;
begin
   if AValue = opcVazio then
      Result := ''
   else
      Result := IntToStr( Integer( AValue ) + 1 );

//   Result := EnumeradoToStr(AValue, ['0', '1', ''],
//                                    [opcMercadoInterno,      // 0 � Opera��o no Mercado Interno
//                                     opcImportacao,          // 1 � Opera��o de Importa��o
//                                     opcVazio                // Vazio.
//                                    ]);
end;

function StrToIndOrigCred(AValue: String): TACBrIndOrigCred;
begin
   if AValue = '' then
      Result := opcVazio
   else
      Result := TACBrIndOrigCred( StrToIntDef( AValue, 0) );

//   Result := StrToEnumerado(AValue, ['0', '1', ''],
//                                    [opcMercadoInterno,      // 0 � Opera��o no Mercado Interno
//                                     opcImportacao,          // 1 � Opera��o de Importa��o
//                                     opcVazio                // Vazio.
//                                ]);
end;

function CstPisToStr(AValue: TACBrCstPis): string;
begin
   Result := CstPis[ Integer( AValue ) ];
end;

function StrToCstPis(AValue: String): TACBrCstPis;
var
ifor: Integer;
begin
   for ifor := 0 to High(CstPis) do
   begin
      if AValue = CstPis[ifor] then
      begin
         Result := TACBrCstPis( ifor );
         Break;
      end;
   end;
end;

function CstCofinsToStr(AValue: TACBrCstCofins): string;
begin
   Result := CstCofins[ Integer( AValue ) ];
end;

function StrToCstCofins(AValue: String): TACBrCstCofins;
var
ifor: Integer;
begin
   for ifor := 0 to High(CstCofins) do
   begin
      if AValue = CstCofins[ifor] then
      begin
         Result := TACBrCstCofins( ifor );
         Break;
      end;
   end;
end;

function CstIcmsToStr(AValue: TACBrCstIcms): string;
begin
   Result := CstIcms[ Integer( AValue ) ];
end;

function StrToCstIcms(AValue: String): TACBrCstIcms;
var
ifor: Integer;
begin
   for ifor := 0 to High(CstIcms) do
   begin
      if AValue = CstIcms[ifor] then
      begin
         Result := TACBrCstIcms( ifor );
         Break;
      end;
   end;
end;

function CstIpiToStr(AValue: TACBrCstIpi): string;
begin
   Result := CstIpi[ Integer( AValue ) ];
end;

function StrToCstIpi(AValue: String): TACBrCstIpi;
var
ifor: Integer;
begin
   for ifor := 0 to High(CstIpi) do
   begin
      if AValue = CstIpi[ifor] then
      begin
         Result := TACBrCstIpi( ifor );
         Break;
      end;
   end;
end;

function IndTipoOperToStr(AValue: TACBrIndTipoOper): string;
begin
   Result := IntToStr( Integer( AValue ) );
end;

function StrToIndTipoOper(AValue: string): TACBrIndTipoOper;
begin
   Result := TACBrIndTipoOper( StrToIntDef( AValue, 0) );
end;

function IndFrtToStr(AValue: TACBrIndFrt): string;
begin
{
  TACBrIndFrt = (
                 tfPorContaEmitente,     // 0 - Por conta de terceiros
                 tfPorContaDestinatario, // 1 - Por conta do emitente
                 tfPorContaTerceiros,    // 2 - Por conta do destinat�rio
                 tfSemCobrancaFrete,     // 9 - Sem cobran�a de frete
                 tfNenhum
Indicador do tipo do frete:
0- Por conta de terceiros;
1- Por conta do emitente;
2- Por conta do destinat�rio;
9- Sem cobran�a de frete.
17  IND_FRT
Obs.: A partir de 01/01/2012 passar� a ser:
Indicador do tipo do frete:
0- Por conta do emitente;
1- Por conta do destinat�rio/remetente;
2- Por conta de terceiros;
9- Sem cobran�a de frete.

   if DT_INI >= EncodeDate(2012,01,01) then
   begin
}
      if AValue = tfSemCobrancaFrete then
      begin
         Result := '9';
         Exit;
      end
      else
      if AValue = tfNenhum then
      begin
         Result := '';
         Exit;
      end;
      Result := IntToStr( Integer( AValue ) );
//   end;
end;

function StrToIndFrt(AValue: string): TACBrIndFrt;
begin
   if AValue = '9' then
   begin
      Result := tfSemCobrancaFrete;
      Exit;
   end
   else
   if AValue = '' then
   begin
      Result := tfNenhum;
      Exit;
   end;
   Result := TACBrIndFrt( StrToIntDef( AValue, 0) );
end;

function IndMovFisicaToStr(AValue: TACBrIndMovFisica): string;
begin
   Result := IntToStr( Integer( AValue ) );
end;

function StrToIndMovFisica(AValue: string): TACBrIndMovFisica;
begin
   Result := TACBrIndMovFisica( StrToIntDef( AValue, 0) );
end;

end.

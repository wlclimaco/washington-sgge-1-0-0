{******************************************************************************}
{ Projeto: Componente ACBrNFe                                                  }
{  Biblioteca multiplataforma de componentes Delphi para emiss�o de Nota Fiscal}
{ eletr�nica - NFe - http://www.nfe.fazenda.gov.br                             }
{                                                                              }
{ Direitos Autorais Reservados (c) 2008 Wemerson Souto                         }
{                                       Daniel Simoes de Almeida               }
{                                       Andr� Ferreira de Moraes               }
{                                                                              }
{ Colaboradores nesse arquivo:                                                 }
{                                                                              }
{  Voc� pode obter a �ltima vers�o desse arquivo na pagina do Projeto ACBr     }
{ Componentes localizado em http://www.sourceforge.net/projects/acbr           }
{                                                                              }
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
|* 16/12/2008: Wemerson Souto
|*  - Doa��o do componente para o Projeto ACBr
|* 20/08/2009: Caique Rodrigues
|*  - Doa��o units para gera��o do Danfe via QuickReport
|* 20/11/2009: Peterson de Cerqueira Matos
|*             E-mail: peterson161@yahoo.com - Tel: (11) 7197-1474 / 8059-4055
|*  - Componente e Units do QuickReport clonados
|*    e transformados em FORTES REPORT
|* 27/01/2010: Peterson de Cerqueira Matos
|*  - Inclus�o de comandos na procedure "InitDados" para ajuste da largura da
|*    coluna "C�digo do Produto" que foi definida no componente "ACBrNFeDANFeRL"
|*  - Em casos de DANFE's com mais de uma p�gina, a partir da segunda o canhoto
|*    nao � mais exibido
|* 05/02/2010: Peterson de Cerqueira Matos
|*  - Altera��o da quantidade de casas decimais dos campos 'QUANTIDADE' e
|*    'VALOR UNIT�RIO' para 4 casas, conforme consta no 'MANUAL DE INTEGRA��O
|*    DO CONTRIBUINTE'
|*  - Corre��o na distribui��o dos caracteres entre os 'DADOS ADICIONAIS' e a
|*    'CONTINUA��O DOS DADOS ADICIONAIS'
|*  - Inclus�o dos campos 'USU�RIO' e 'SISTEMA' no rodap� do DANFE (s� folha 1)
|*  - Inclus�o dos campos 'SITE', 'EMAIL' e 'FAX' no quadro do emitente
|*  - Inclus�o do 'RESUMO' da NF-e no canhoto
|* 10/02/2010: Peterson de Cerqueira Matos
|*  - Inser��o da fun��o 'BuscaDireita', que auxiliar� a corre��o da
|*    exibi��o dos 'DADOS ADICIONAIS' para evitar que a �ltima palavra do
|*    quadro fique pela metade devido � limita��o da quantidade de caracteres
|*  - Corre��o da formata��o de CPF, no caso de NF-e emitida para pessoa f�sica
|* 13/02/2010: Peterson de Cerqueira Matos
|*  - Altera��o da fonte do memo 'rlmObsItem' de ARIAL para COURIER NEW
|* 15/03/2010: Felipe Feltes
|*  - Adequa��o na se��o 'USES' para ser utilizado em CLX
|* 19/03/2010: Peterson de Cerqueira Matos
|*  - Tratamento das propriedades "FormularioContinuo", "ExpandirLogoMarca" e
|*    "MostrarPreview" de "ACBrNFeDANFeClass"
|*  - Tratamento da propriedade "PosCanhoto" de "ACBrNFeDANFeRLClass"
|* 22/03/2010: Peterson de Cerqueira Matos
|*  - Tratamento das margens em "ACBrNFeDANFeClass"
|*  - Tratamento da propriedade "FonteDANFE" de "ACBrNFeDANFeRLClass"
|* 13/04/2010: Peterson de Cerqueira Matos
|*  - Adequa��o � NF-e 2.0, Manual de Integra��o do Contribuinte 4.0.1NT2009.006
|*  - Tratamento das propriedades "_vUnCom" e "_qCom"
|*  - Exibi��o da "hora de sa�da"
|*  - Oculta��o do quadro "FATURA" quando n�o houver duplicatas
|*  - Corre��o na exibi��o das informa��es complementares
|*  - Corre��o na exibi��o do tipo de frete
|*  - Acr�scimo da coluna "Valor Desconto"
|*  - Corre��o na exibi��o da coluna CST. Quando o emitente for "Simples
|*    Nacional - CRT=1", ser� exibida a informa��o CSOSN ao inv�s do CST
|*  - Altera��o no layout do quadro "IDENTIFICA��O DO EMITENTE"
|* 26/04/2010: Peterson de Cerqueira Matos
|*  - Adapta��o dos comandos que utilizavam CSOSN string para CSOSN tipificado
|* 19/06/2010: Peterson de Cerqueira Matos
|*  - Admiss�o de quebra de linha nos dados adicionais do produto (infAdProd).
|*    O Caractere ponto-e-v�rgula ';' ser� considerado quebra de linha
|* 20/07/2010: Peterson de Cerqueira Matos
|*  - Acr�scimo do case 0 na configura��o das casas decimais da quantidade
|*    e do valor unit�rio
|* 28/07/2010: Peterson de Cerqueira Matos
|*  - Implementa��o da quantidade de itens por p�gina
|*  - Admiss�o de quebra de linha nas informa��es complementares.
|*    O Caractere ponto-e-v�rgula ';' ser� considerado quebra de linha
|* 10/08/2010: Peterson de Cerqueira Matos
|*  - Tratamento do tamanho da fonte da raz�o social do emitente
|* 01/03/2011: Fernando Emiliano David Nunes
|*  - Quando DPEC, nao estava imprimindo Data e Motivo da Conting�ncia
|*  - Quando DPEC, nao estava imprimindo o valor FProtocoloNFe
|* 24/03/2011: Fernando Emiliano David Nunes
|*  - Alterei a funcao FormatarFone para tratar casos onde o DDD vem com ZERO somando 3 digitos
|* 18/05/2011: Peterson de Cerqueira Matos
|*  - Corre��o da exibi��o das duplicatas. As duplicatas s�o exibidas da direita
|*    para a esquerda, at� o limite de 60 duplicatas. Desta forma a altura do
|*    quadro duplicatas fica vari�vel, de acordo com a quantidade de linhas
|* 20/05/2011: Peterson de Cerqueira Matos
|*  - Tratamento da propriedade "ExibirResumoCanhoto_Texto"
|* 23/05/2011: Waldir Paim
|*  - In�cio da prepara��o para Lazarus: Somente utiliza TClientDataSet quando
|*    estiver no Delphi. Obrigat�ria a utiliza��o da vers�o 3.70B ou superior
|*    do Fortes Report. Download dispon�vel em
|*    http://sourceforge.net/projects/fortesreport/files/
|* 13/12/2011: Peterson de Cerqueira Matos
|*  - Conserto da exibi��o da base e do valor do ICMS de cada item no caso
|*    de ser simples nacional, pois estava saindo zerado. (Este c�digo de
|*    exibi��o dos itens foi copiado do DANFE em QuickReport)
|* 24/02/2012: Peterson de Cerqueira Matos
|*  - Corre��o da exibi��o dos itens quando o emitente � CRT = 2
|* 22/03/2013: Peterson de Cerqueira Matos
|*  - Impress�o dos detalhamentos espec�ficos e do desconto em percentual
******************************************************************************}


{$I ACBr.inc}
unit ACBrNFeDANFeRLRetrato;

interface

uses
  SysUtils, Variants, Classes,
  {$IFDEF CLX}
  QGraphics, QControls, QForms, QDialogs, QExtCtrls, Qt, QStdCtrls,
  {$ELSE}
    {$IFDEF MSWINDOWS}Windows, Messages, {$ENDIF}
    Graphics, Controls, Forms, Dialogs, ExtCtrls, MaskUtils, StdCtrls,
  {$ENDIF}
  RLReport, RLFilters, RLPDFFilter,
  {$IFDEF BORLAND}
  XMLIntf, XMLDoc,
    {$IF CompilerVersion >= 22}
      Vcl.Imaging.jpeg,
    {$ELSE}
      jpeg,
    {$IFEND}
  {$ENDIF}
  ACBrNFeDANFeRL, pcnConversao, RLBarcode, DB, StrUtils;

type
  TfrlDANFeRLRetrato = class(TfrlDANFeRL)
    rlbEmitente: TRLBand;
    rliEmitente: TRLDraw;
    RLDraw6: TRLDraw;
    rliChave: TRLDraw;
    RLDraw8: TRLDraw;
    RLDraw9: TRLDraw;
    RLDraw10: TRLDraw;
    RLDraw11: TRLDraw;
    rllDANFE: TRLLabel;
    rllDocumento1: TRLLabel;
    rllDocumento2: TRLLabel;
    rllTipoEntrada: TRLLabel;
    rllTipoSaida: TRLLabel;
    rliTipoEntrada: TRLDraw;
    rllEntradaSaida: TRLLabel;
    rllNumNF1: TRLLabel;
    rllSERIE1: TRLLabel;
    rliChave2: TRLDraw;
    rliChave3: TRLDraw;
    rlbCodigoBarras: TRLBarcode;
    rlbCabecalhoItens: TRLBand;
    rlbDadosAdicionais: TRLBand;
    rlsRectProdutos: TRLDraw;
    lblDadosDoProduto: TRLLabel;
    RLDraw50: TRLDraw;
    RLLabel23: TRLLabel;
    RLDraw51: TRLDraw;
    rlsDivProd1: TRLDraw;
    rlsDivProd2: TRLDraw;
    rlsDivProd3: TRLDraw;
    rlsDivProd4: TRLDraw;
    rlsDivProd5: TRLDraw;
    rlsDivProd6: TRLDraw;
    rlsDivProd7: TRLDraw;
    rlsDivProd8: TRLDraw;
    rlsDivProd9: TRLDraw;
    rlsDivProd10: TRLDraw;
    rlsDivProd11: TRLDraw;
    rlsDivProd12: TRLDraw;
    rlsDivProd13: TRLDraw;
    RLDraw54: TRLDraw;
    rllChaveAcesso: TRLLabel;
    rllDadosVariaveis1a: TRLLabel;
    rllDadosVariaveis1b: TRLLabel;
    rllDadosVariaveis3_Descricao: TRLLabel;
    RLLabel28: TRLLabel;
    RLLabel29: TRLLabel;
    RLLabel30: TRLLabel;
    RLLabel31: TRLLabel;
    RLLabel77: TRLLabel;
    RLLabel78: TRLLabel;
    RLLabel82: TRLLabel;
    lblCST: TRLLabel;
    RLLabel84: TRLLabel;
    RLLabel85: TRLLabel;
    RLLabel86: TRLLabel;
    RLLabel87: TRLLabel;
    RLLabel88: TRLLabel;
    RLLabel89: TRLLabel;
    RLLabel90: TRLLabel;
    RLLabel91: TRLLabel;
    RLLabel92: TRLLabel;
    RLLabel93: TRLLabel;
    RLLabel94: TRLLabel;
    RLLabel95: TRLLabel;
    RLLabel96: TRLLabel;
    RLLabel97: TRLLabel;
    RLLabel98: TRLLabel;
    rlmEmitente: TRLMemo;
    rlmEndereco: TRLMemo;
    rllFone: TRLLabel;
    rliLogo: TRLImage;
    rllNatOperacao: TRLLabel;
    rllDadosVariaveis3: TRLLabel;
    rllInscricaoEstadual: TRLLabel;
    rllInscrEstSubst: TRLLabel;
    rllCNPJ: TRLLabel;
    rlmDadosAdicionais: TRLMemo;
    rllChave: TRLLabel;
    rllEmitente: TRLLabel;
    rlbCodigoBarrasFS: TRLBarcode;
    rllXmotivo: TRLLabel;
    rllDadosVariaveis1c: TRLLabel;
    rlbDestinatario: TRLBand;
    RLLabel18: TRLLabel;
    RLLabel32: TRLLabel;
    rllDestNome: TRLLabel;
    RLLabel35: TRLLabel;
    rllDestEndereco: TRLLabel;
    RLLabel39: TRLLabel;
    rllDestCidade: TRLLabel;
    RLLabel40: TRLLabel;
    rllDestFone: TRLLabel;
    RLLabel36: TRLLabel;
    rllDestBairro: TRLLabel;
    RLLabel41: TRLLabel;
    rllDestUF: TRLLabel;
    RLLabel33: TRLLabel;
    rllDestCNPJ: TRLLabel;
    RLLabel37: TRLLabel;
    rllDestCEP: TRLLabel;
    RLLabel42: TRLLabel;
    rllDestIE: TRLLabel;
    RLLabel34: TRLLabel;
    rllEmissao: TRLLabel;
    RLLabel38: TRLLabel;
    rllSaida: TRLLabel;
    RLLabel43: TRLLabel;
    rllHoraSaida: TRLLabel;
    RLDraw16: TRLDraw;
    RLDraw17: TRLDraw;
    RLDraw22: TRLDraw;
    RLDraw23: TRLDraw;
    RLDraw20: TRLDraw;
    RLDraw19: TRLDraw;
    RLDraw24: TRLDraw;
    RLDraw21: TRLDraw;
    RLDraw18: TRLDraw;
    RLDraw15: TRLDraw;
    rlbFatura: TRLBand;
    RLLabel19: TRLLabel;
    rllFatNum1: TRLLabel;
    rllFatNum5: TRLLabel;
    rllFatNum9: TRLLabel;
    rllFatData1: TRLLabel;
    rllFatData5: TRLLabel;
    rllFatData9: TRLLabel;
    rllFatValor1: TRLLabel;
    rllFatValor5: TRLLabel;
    rllFatValor9: TRLLabel;
    rllFatNum2: TRLLabel;
    rllFatNum6: TRLLabel;
    rllFatNum10: TRLLabel;
    rllFatData2: TRLLabel;
    rllFatData6: TRLLabel;
    rllFatData10: TRLLabel;
    rllFatValor2: TRLLabel;
    rllFatValor6: TRLLabel;
    rllFatValor10: TRLLabel;
    rllFatNum3: TRLLabel;
    rllFatNum7: TRLLabel;
    rllFatNum11: TRLLabel;
    rllFatData3: TRLLabel;
    rllFatData7: TRLLabel;
    rllFatData11: TRLLabel;
    rllFatValor3: TRLLabel;
    rllFatValor7: TRLLabel;
    rllFatValor11: TRLLabel;
    rllFatNum4: TRLLabel;
    rllFatNum8: TRLLabel;
    rllFatNum12: TRLLabel;
    rllFatData4: TRLLabel;
    rllFatData8: TRLLabel;
    rllFatData12: TRLLabel;
    rllFatValor4: TRLLabel;
    rllFatValor8: TRLLabel;
    rllFatValor12: TRLLabel;
    rliFatura1: TRLDraw;
    rliFatura2: TRLDraw;
    rliFatura3: TRLDraw;
    rliFatura: TRLDraw;
    rlbImposto: TRLBand;
    RLLabel20: TRLLabel;
    RLLabel44: TRLLabel;
    rllBaseICMS: TRLLabel;
    RLLabel49: TRLLabel;
    rllValorFrete: TRLLabel;
    RLLabel45: TRLLabel;
    rllValorICMS: TRLLabel;
    RLLabel50: TRLLabel;
    rllValorSeguro: TRLLabel;
    RLLabel51: TRLLabel;
    rllDescontos: TRLLabel;
    RLLabel46: TRLLabel;
    rllBaseICMSST: TRLLabel;
    RLLabel52: TRLLabel;
    rllAcessorias: TRLLabel;
    RLLabel47: TRLLabel;
    rllValorICMSST: TRLLabel;
    RLLabel53: TRLLabel;
    rllValorIPI: TRLLabel;
    RLLabel48: TRLLabel;
    rllTotalProdutos: TRLLabel;
    RLLabel54: TRLLabel;
    rllTotalNF: TRLLabel;
    RLDraw30: TRLDraw;
    RLDraw32: TRLDraw;
    RLDraw33: TRLDraw;
    RLDraw34: TRLDraw;
    RLDraw35: TRLDraw;
    RLDraw36: TRLDraw;
    RLDraw31: TRLDraw;
    RLDraw29: TRLDraw;
    RLDraw37: TRLDraw;
    rlbTransportadora: TRLBand;
    RLLabel21: TRLLabel;
    RLLabel55: TRLLabel;
    rllTransNome: TRLLabel;
    RLLabel63: TRLLabel;
    rllTransEndereco: TRLLabel;
    RLLabel67: TRLLabel;
    rllTransQTDE: TRLLabel;
    RLLabel68: TRLLabel;
    rllTransEspecie: TRLLabel;
    RLLabel69: TRLLabel;
    rllTransMarca: TRLLabel;
    RLLabel56: TRLLabel;
    RLLabel64: TRLLabel;
    rllTransCidade: TRLLabel;
    RLLabel70: TRLLabel;
    rllTransNumeracao: TRLLabel;
    rllTransModFrete: TRLLabel;
    RLLabel59: TRLLabel;
    rllTransCodigoANTT: TRLLabel;
    RLLabel60: TRLLabel;
    rllTransPlaca: TRLLabel;
    RLLabel71: TRLLabel;
    rllTransPesoBruto: TRLLabel;
    RLLabel61: TRLLabel;
    rllTransUFPlaca: TRLLabel;
    RLLabel65: TRLLabel;
    rllTransUF: TRLLabel;
    RLLabel62: TRLLabel;
    rllTransCNPJ: TRLLabel;
    RLLabel66: TRLLabel;
    rllTransIE: TRLLabel;
    RLLabel72: TRLLabel;
    rllTransPesoLiq: TRLLabel;
    RLDraw38: TRLDraw;
    RLDraw39: TRLDraw;
    RLDraw46: TRLDraw;
    RLDraw45: TRLDraw;
    RLDraw41: TRLDraw;
    RLDraw44: TRLDraw;
    RLDraw47: TRLDraw;
    RLDraw48: TRLDraw;
    RLDraw49: TRLDraw;
    RLDraw42: TRLDraw;
    RLDraw40: TRLDraw;
    RLLabel25: TRLLabel;
    RLLabel26: TRLLabel;
    rlbItens: TRLBand;
    rlbISSQN: TRLBand;
    RLLabel24: TRLLabel;
    RLLabel73: TRLLabel;
    RLLabel74: TRLLabel;
    RLLabel75: TRLLabel;
    RLLabel76: TRLLabel;
    RLDraw56: TRLDraw;
    RLDraw57: TRLDraw;
    RLDraw58: TRLDraw;
    RLDraw52: TRLDraw;
    LinhaDescricao: TRLDraw;
    LinhaNCM: TRLDraw;
    LinhaCST: TRLDraw;
    LinhaCFOP: TRLDraw;
    LinhaUnidade: TRLDraw;
    LinhaQuantidade: TRLDraw;
    LinhaValorUnitario: TRLDraw;
    LinhaValorTotal: TRLDraw;
    LinhaBaseICMS: TRLDraw;
    LinhaValorICMS: TRLDraw;
    LinhaValorIPI: TRLDraw;
    LinhaAliqICMS: TRLDraw;
    LinhaAliqIPI: TRLDraw;
    LinhaCodigo: TRLDraw;
    LinhaFinal: TRLDraw;
    rliMarcaDagua1: TRLImage;
    txtCodigo: TRLDBText;
    txtNCM: TRLDBText;
    txtCST: TRLDBText;
    txtCFOP: TRLDBText;
    txtUnidade: TRLDBText;
    txtQuantidade: TRLDBText;
    txtValorUnitario: TRLDBText;
    txtValorTotal: TRLDBText;
    txtBaseICMS: TRLDBText;
    txtValorICMS: TRLDBText;
    txtValorIPI: TRLDBText;
    txtAliqICMS: TRLDBText;
    txtAliqIPI: TRLDBText;
    rllPageNumber: TRLSystemInfo;
    rllLastPage: TRLSystemInfo;
    rlbAvisoContingencia: TRLBand;
    rllAvisoContingencia: TRLLabel;
    rlbContinuacaoInformacoesComplementares: TRLBand;
    RLLabel16: TRLLabel;
    rlmContinuacaoDadosAdicionais: TRLMemo;
    rllHomologacao: TRLLabel;
    rlmDadosAdicionaisAuxiliar: TRLMemo;
    rlmDescricao: TRLDBMemo;
    LinhaDCSuperior: TRLDraw;
    LinhaDCInferior: TRLDraw;
    LinhaDCEsquerda: TRLDraw;
    LinhaDCDireita: TRLDraw;
    rllCabFatura1: TRLLabel;
    rllCabFatura2: TRLLabel;
    rllCabFatura3: TRLLabel;
    RLDraw69: TRLDraw;
    rllISSQNValorServicos: TRLLabel;
    rllISSQNBaseCalculo: TRLLabel;
    rllISSQNValorISSQN: TRLLabel;
    rllISSQNInscricao: TRLLabel;
    rlbObsItem: TRLBand;
    LinhaFimItens: TRLDraw;
    LinhaFimObsItem: TRLDraw;
    LinhaInicioItem: TRLDraw;
    rlmObsItem: TRLMemo;
    LinhaObsItemEsquerda: TRLDraw;
    LinhaObsItemDireita: TRLDraw;
    RLDraw70: TRLDraw;
    RLDraw71: TRLDraw;
    rlmDescricaoProduto: TRLMemo;
    rlmCodProd: TRLMemo;
    rlmSiteEmail: TRLMemo;
    rlbReciboHeader: TRLBand;
    rliCanhoto: TRLDraw;
    rliCanhoto1: TRLDraw;
    rliCanhoto2: TRLDraw;
    rllRecebemosDe: TRLLabel;
    rllDataRecebimento: TRLLabel;
    rllIdentificacao: TRLLabel;
    rliCanhoto3: TRLDraw;
    rllNFe: TRLLabel;
    rllNumNF0: TRLLabel;
    rllSERIE0: TRLLabel;
    rllResumo: TRLLabel;
    rlbDivisaoRecibo: TRLBand;
    rliDivisao: TRLDraw;
    rllUsuario: TRLLabel;
    rllSistema: TRLLabel;
    rllCabFatura4: TRLLabel;
    rllCabFatura5: TRLLabel;
    rllCabFatura6: TRLLabel;
    rllCabFatura7: TRLLabel;
    rllCabFatura8: TRLLabel;
    rllCabFatura9: TRLLabel;
    rllCabFatura10: TRLLabel;
    rllCabFatura11: TRLLabel;
    rllCabFatura12: TRLLabel;
    lblPercValorDesc: TRLLabel;
    RLLabel11: TRLLabel;
    RLDraw1: TRLDraw;
    txtValorDesconto: TRLDBText;
    RLDraw2: TRLDraw;
    rllContingencia: TRLLabel;
    RLDraw4: TRLDraw;
    rllFatNum13: TRLLabel;
    rllFatData13: TRLLabel;
    rllFatValor13: TRLLabel;
    rllFatNum14: TRLLabel;
    rllFatData14: TRLLabel;
    rllFatValor14: TRLLabel;
    rllFatNum15: TRLLabel;
    rllFatData15: TRLLabel;
    rllFatValor15: TRLLabel;
    rllFatNum16: TRLLabel;
    rllFatData16: TRLLabel;
    rllFatValor16: TRLLabel;
    rllFatNum17: TRLLabel;
    rllFatData17: TRLLabel;
    rllFatValor17: TRLLabel;
    rllFatNum18: TRLLabel;
    rllFatData18: TRLLabel;
    rllFatValor18: TRLLabel;
    rllFatNum19: TRLLabel;
    rllFatData19: TRLLabel;
    rllFatValor19: TRLLabel;
    rllFatNum20: TRLLabel;
    rllFatData20: TRLLabel;
    rllFatValor20: TRLLabel;
    rllFatNum21: TRLLabel;
    rllFatData21: TRLLabel;
    rllFatValor21: TRLLabel;
    rllFatNum22: TRLLabel;
    rllFatData22: TRLLabel;
    rllFatValor22: TRLLabel;
    rllFatNum23: TRLLabel;
    rllFatData23: TRLLabel;
    rllFatValor23: TRLLabel;
    rllFatNum24: TRLLabel;
    rllFatData24: TRLLabel;
    rllFatValor24: TRLLabel;
    rllFatNum25: TRLLabel;
    rllFatData25: TRLLabel;
    rllFatValor25: TRLLabel;
    rllFatNum26: TRLLabel;
    rllFatData26: TRLLabel;
    rllFatValor26: TRLLabel;
    rllFatNum27: TRLLabel;
    rllFatData27: TRLLabel;
    rllFatValor27: TRLLabel;
    rllFatNum28: TRLLabel;
    rllFatData28: TRLLabel;
    rllFatValor28: TRLLabel;
    rllFatNum29: TRLLabel;
    rllFatData29: TRLLabel;
    rllFatValor29: TRLLabel;
    rllFatNum30: TRLLabel;
    rllFatData30: TRLLabel;
    rllFatValor30: TRLLabel;
    rllFatNum31: TRLLabel;
    rllFatData31: TRLLabel;
    rllFatValor31: TRLLabel;
    rllFatNum32: TRLLabel;
    rllFatData32: TRLLabel;
    rllFatValor32: TRLLabel;
    rllFatNum33: TRLLabel;
    rllFatData33: TRLLabel;
    rllFatValor33: TRLLabel;
    rllFatNum34: TRLLabel;
    rllFatData34: TRLLabel;
    rllFatValor34: TRLLabel;
    rllFatNum35: TRLLabel;
    rllFatData35: TRLLabel;
    rllFatValor35: TRLLabel;
    rllFatNum36: TRLLabel;
    rllFatData36: TRLLabel;
    rllFatValor36: TRLLabel;
    rllFatNum37: TRLLabel;
    rllFatData37: TRLLabel;
    rllFatValor37: TRLLabel;
    rllFatNum38: TRLLabel;
    rllFatData38: TRLLabel;
    rllFatValor38: TRLLabel;
    rllFatNum39: TRLLabel;
    rllFatData39: TRLLabel;
    rllFatValor39: TRLLabel;
    rllFatNum40: TRLLabel;
    rllFatData40: TRLLabel;
    rllFatValor40: TRLLabel;
    rllFatNum41: TRLLabel;
    rllFatData41: TRLLabel;
    rllFatValor41: TRLLabel;
    rllFatNum42: TRLLabel;
    rllFatData42: TRLLabel;
    rllFatValor42: TRLLabel;
    rllFatNum43: TRLLabel;
    rllFatData43: TRLLabel;
    rllFatValor43: TRLLabel;
    rllFatNum44: TRLLabel;
    rllFatData44: TRLLabel;
    rllFatValor44: TRLLabel;
    rllFatNum45: TRLLabel;
    rllFatData45: TRLLabel;
    rllFatValor45: TRLLabel;
    rllFatNum46: TRLLabel;
    rllFatData46: TRLLabel;
    rllFatValor46: TRLLabel;
    rllFatNum47: TRLLabel;
    rllFatData47: TRLLabel;
    rllFatValor47: TRLLabel;
    rllFatNum48: TRLLabel;
    rllFatData48: TRLLabel;
    rllFatValor48: TRLLabel;
    rllFatNum49: TRLLabel;
    rllFatData49: TRLLabel;
    rllFatValor49: TRLLabel;
    rllFatNum50: TRLLabel;
    rllFatData50: TRLLabel;
    rllFatValor50: TRLLabel;
    rllFatNum51: TRLLabel;
    rllFatData51: TRLLabel;
    rllFatValor51: TRLLabel;
    rllFatNum52: TRLLabel;
    rllFatData52: TRLLabel;
    rllFatValor52: TRLLabel;
    rllFatNum53: TRLLabel;
    rllFatData53: TRLLabel;
    rllFatValor53: TRLLabel;
    rllFatNum54: TRLLabel;
    rllFatData54: TRLLabel;
    rllFatValor54: TRLLabel;
    rllFatNum55: TRLLabel;
    rllFatData55: TRLLabel;
    rllFatValor55: TRLLabel;
    rllFatNum56: TRLLabel;
    rllFatData56: TRLLabel;
    rllFatValor56: TRLLabel;
    rllFatNum57: TRLLabel;
    rllFatData57: TRLLabel;
    rllFatValor57: TRLLabel;
    rllFatNum58: TRLLabel;
    rllFatData58: TRLLabel;
    rllFatValor58: TRLLabel;
    rllFatNum59: TRLLabel;
    rllFatData59: TRLLabel;
    rllFatValor59: TRLLabel;
    rllFatNum60: TRLLabel;
    rllFatData60: TRLLabel;
    rllFatValor60: TRLLabel;
    RLLabel12: TRLLabel;
    procedure RLNFeBeforePrint(Sender: TObject; var PrintIt: Boolean);
    procedure rlbEmitenteBeforePrint(Sender: TObject;
      var PrintIt: Boolean);
    procedure rlbItensAfterPrint(Sender: TObject);
    procedure rlbDadosAdicionaisBeforePrint(Sender: TObject;
      var PrintIt: Boolean);
    procedure rlbItensBeforePrint(Sender: TObject; var PrintIt: Boolean);
    procedure rlbEmitenteAfterPrint(Sender: TObject);
    procedure rlbCabecalhoItensBeforePrint(Sender: TObject;
      var PrintIt: Boolean);
    procedure FormCreate(Sender: TObject);
  private
    FRecebemoDe : string;
    procedure InitDados;
    procedure Header;
    procedure Emitente;
    procedure Destinatario;
    procedure EnderecoRetirada;
    procedure EnderecoEntrega;
    procedure Imposto;
    procedure Transporte;
    procedure DadosAdicionais;
    procedure Observacoes;
    procedure Itens;
    procedure ISSQN;
    procedure AddFatura;
    procedure ConfigureDataSource;
  public

  end;

implementation

uses ACBrNFeUtil, ACBrDFeUtil, pcnNFe, ACBrNFeDANFeRLClass;

var
q, iQuantItens, iItemAtual: Integer;
sRetirada, sEntrega: WideString;


{$R *.dfm}

procedure TfrlDANFeRLRetrato.RLNFeBeforePrint(Sender: TObject;
  var PrintIt: Boolean);
begin
  q := 0;
  with RLNFe.Margins do
    begin
      TopMargin := FMargemSuperior * 10;
      BottomMargin := FMargemInferior * 10;
      LeftMargin := FMargemEsquerda * 10;
      RightMargin := FMargemDireita * 10;
    end;

  ConfigureDataSource;
  InitDados;

//  Removido para que o quadro Fatura seja mostrado mesmo a vista
//  if FNFe.Cobr.Dup.Count > 0 then
//    rlbFatura.Visible := True
//  else
//    rlbFatura.Visible := False;

  RLNFe.Title := Copy (FNFe.InfNFe.Id, 4, 44);
end;

procedure TfrlDANFeRLRetrato.rlbEmitenteBeforePrint(Sender: TObject;
  var PrintIt: Boolean);
begin
  rlbCodigoBarras.BringToFront;
  if RLNFe.PageNumber > 1 then
    begin
      rlbISSQN.Visible := False;
      rlbDadosAdicionais.Visible := False;
      rlbReciboHeader.Visible := False;
      rlbDivisaoRecibo.Visible := False;
      if iQuantItens > q then
        begin
          rlbCabecalhoItens.Visible := True;
          lblDadosDoProduto.Caption := 'CONTINUA��O DOS DADOS DO PRODUTO / SERVI�OS';
          rliMarcaDagua1.Top := 300;
        end
      else
        rlbCabecalhoItens.Visible := False;

    end;
end;

procedure TfrlDANFeRLRetrato.InitDados;
var i, b, h, iAlturaCanhoto: Integer;
begin
  // Carrega logomarca
  if (FLogo <> '') and FileExists (FLogo) then
     rliLogo.Picture.LoadFromFile(FLogo);

  if (FMarcaDagua <> '') and FileExists (FMarcaDagua) then
    begin
      rliMarcaDagua1.Picture.LoadFromFile(FMarcaDagua);
    end;

  // Exibe o resumo da NF-e no canhoto
  if FResumoCanhoto = True then
    begin
      if FResumoCanhoto_Texto <> '' then
        rllResumo.Caption := FResumoCanhoto_Texto
      else
        begin
          rllResumo.Caption := 'EMISS�O: ' +
                           FormatDateTime('DD/MM/YYYY', FNFe.Ide.dEmi) +
                           '  -  ' +
                           'DEST. / REM.: ' + FNFe.Dest.xNome + '  -  ' +
                           'VALOR TOTAL: R$ ' +
                           DFeUtil.FormatFloat(FNFe.Total.ICMSTot.vNF,
                           '###,###,###,##0.00');
        end; // if FResumoCanhoto_Texto <> ''
      rllResumo.Visible := True;
      iAlturaCanhoto := 25;
    end // if FResumoCanhoto = True
  else
    begin
      rllResumo.Visible := False;
      iAlturaCanhoto := 15;
    end;

  rliCanhoto1.Top := iAlturaCanhoto;
  rliCanhoto2.Top := rliCanhoto1.Top;
  rliCanhoto2.Height := (rliCanhoto.Top + rliCanhoto.Height) - rliCanhoto2.Top;
  rllDataRecebimento.Top := rliCanhoto1.Top + 3;
  rllIdentificacao.Top := rliCanhoto1.Top + 3;

  // Exibe o desenvolvedor do sistema
  if FSsitema <> '' then
    begin
      rllSistema.Caption := FSsitema;
      rllSistema.Visible := True;
    end
  else
    rllSistema.Visible := False;

  // Exibe o nome do usu�rio
  if FUsuario <> '' then
    begin
      rllUsuario.Caption := 'DATA / HORA DA IMPRESS�O: ' +
                            DateTimeToStr(Now) + ' - ' + FUsuario;
      rllUsuario.Visible := True;
    end
  else
    rllUsuario.Visible := False;

  // Exibe a informa��o de Ambiente de Homologa��o
  if FNFe.Ide.tpAmb = taHomologacao then
    begin
      rllHomologacao.Caption := 'AMBIENTE DE HOMOLOGA��O - NF-E SEM VALOR FISCAL';
      rllHomologacao.Visible := True;
    end
  else
    begin
      rllHomologacao.Caption := '';
      rllHomologacao.Visible := False;
    end;

  // Exibe a informa��o correta no label da chave de acesso

   if FNFe.procNFe.cStat > 0 then
    begin
      if FNFe.procNFe.cStat = 100 then
        begin
          rlbCodigoBarras.Visible := True;
          rllXMotivo.Visible := False;
          rllDadosVariaveis3_Descricao.Caption := 'PROTOCOLO DE AUTORIZA��O DE USO';
          rllDadosVariaveis3_Descricao.Visible := True;
        end;
      // Adicionados o 151 e 155 ou a propriedade FNFeCancelada=True - Alterado por Jorge Henrique em 22/02/2013
      if ((FNFe.procNFe.cStat in [101, 151, 155]) or (FNFeCancelada)) then
        begin
          rlbCodigoBarras.Visible := False;
          rllXmotivo.Caption := 'NF-e CANCELADA';
          rllXmotivo.Visible := True;
          rllDadosVariaveis3_Descricao.Caption := 'PROTOCOLO DE HOMOLOGA��O DE CANCELAMENTO';
          rllDadosVariaveis3_Descricao.Visible := True;
        end;
      // cStat de denegacao correto eh o 110 e nao 102 - Alterado por Jorge Henrique em 22/02/2013
      if FNFe.procNFe.cStat = 110 then
        begin
          rlbCodigoBarras.Visible := False;
          rllXmotivo.Caption := 'NF-e DENEGADA';
          rllXmotivo.Visible := True;
          rllDadosVariaveis3_Descricao.Caption := 'PROTOCOLO DE DENEGA��O DE USO';
          rllDadosVariaveis3_Descricao.Visible := True;
        end;

      // Aproveitei o codigo adicionado ao DanfeQR pelo Italo em 27/01/2012
      if not FNFe.procNFe.cStat in [100, 101, 110, 151, 155] then
        begin
          rlbCodigoBarras.Visible := False;
          rllXmotivo.Caption := FNFe.procNFe.xMotivo;
          rllXmotivo.Visible := True;
          rllDadosVariaveis3_Descricao.Visible := False;
          rllDadosVariaveis3.Visible := False;
        end;
    end else begin
      if (FNFe.Ide.tpEmis in [teNormal, teSCAN]) then
        begin
          rlbCodigoBarras.Visible := False;
          rllXmotivo.Caption := 'NF-E N�O ENVIADA PARA SEFAZ';
          rllXMotivo.Visible := True;
          rllDadosVariaveis3_Descricao.Visible := False;
          rllDadosVariaveis3.Visible := False;
        end;
    end;

  // Ajusta a largura da coluna "C�digo do Produto"
  txtCodigo.Width := FLarguraCodProd;
  rlmCodProd.Width := FLarguraCodProd;
  rlsDivProd1.Left := FLarguraCodProd + 2;
  LinhaDescricao.Left :=  FLarguraCodProd + 2;
  rlmDescricaoProduto.Left := rlsDivProd1.Left + 2;
  rlmDescricaoProduto.Width := (rlsDivProd2.Left - rlsDivProd1.Left) - 3;
  rlmDescricao.Left := LinhaDescricao.Left + 2;
  rlmDescricao.Width := (LinhaNCM.Left - LinhaDescricao.Left) - 24;
  rlmDescricaoProduto.Lines.BeginUpdate;
  rlmDescricaoProduto.Lines.Clear;
  rlmCodProd.Lines.BeginUpdate;
  rlmCodProd.Lines.Clear;

  // ajusta a posi��o do 'c�digo do produto'
  if rlmCodProd.Width > 90 then
    begin
      rlmCodProd.Top := 18;
      rlmCodProd.Height := 7;
    end
  else
    begin
      rlmCodProd.Top := 14;
      rlmCodProd.Height := 14;
    end;

  // Se a largura da coluna 'C�digo do produto' for suficiente,
  // exibe o t�tulo da coluna sem abrevia��es
  if rlmCodProd.Width > 113 then
    rlmCodProd.Lines.Add('C�DIGO DO PRODUTO / SERVI�O')
  else
    rlmCodProd.Lines.Add('C�DIGO DO PROD. / SERV.');

  // Ajusta a posi��o da coluna 'Descri��o do produto'
  if rlmDescricaoProduto.Width > 128 then
    begin
      rlmDescricaoProduto.Top := 18;
      rlmDescricaoProduto.Height := 7;
    end
  else
    begin
      rlmDescricaoProduto.Top := 14;
      rlmDescricaoProduto.Height := 14;
    end;

  // Se a largura da coluna 'Descri��o do produto' for suficiente,
  // exibe o t�tulo da coluna sem abrevia��es
  if rlmDescricaoProduto.Width > 72 then
    rlmDescricaoProduto.Lines.Add('DESCRI��O DO PRODUTO / SERVI�O')
  else
    rlmDescricaoProduto.Lines.Add('DESCR. PROD. / SERV.');

  rlmCodProd.Lines.EndUpdate;
  rlmDescricaoProduto.Lines.EndUpdate;

  // Posiciona o canhoto do DANFE no cabe�alho ou rodap�
  case FPosCanhoto of
    pcCabecalho:
      begin
        rlbReciboHeader.BandType := btHeader;
        rlbDivisaoRecibo.BandType := btHeader;
        rlbReciboHeader.Top := 0;
        rlbDivisaoRecibo.Top := rlbReciboHeader.Top + rlbDivisaoRecibo.Height;
      end;                                                                     
    pcRodape:
      begin
        rlbReciboHeader.BandType := btFooter;
        rlbDivisaoRecibo.BandType := btFooter;
        rlbDivisaoRecibo.Top := rlbDadosAdicionais.Top + rlbDadosAdicionais.Height;
        rlbReciboHeader.Top := rlbDivisaoRecibo.Top + rlbDivisaoRecibo.Height;
      end;
  end;

  // Oculta alguns itens do DANFE
  if FFormularioContinuo = True then
    begin
      rllRecebemosDe.Visible := False;
      rllResumo.Visible := False;
      rllDataRecebimento.Visible := False;
      rllIdentificacao.Visible := False;
      rllNFe.Visible := False;
      rliCanhoto.Visible := False;
      rliCanhoto1.Visible := False;
      rliCanhoto2.Visible := False;
      rliCanhoto3.Visible := False;
      rliDivisao.Visible := False;
      rliTipoEntrada.Visible := False;
      rllDANFE.Visible := False;
      rllDocumento1.Visible := False;
      rllDocumento2.Visible := False;
      rllTipoEntrada.Visible := False;
      rllTipoSaida.Visible := False;
      rllEmitente.Visible := False;
      rliLogo.Visible := False;
      rlmEmitente.Visible := False;
      rlmEndereco.Visible := False;
      rllFone.Visible := False;
      rlmSiteEmail.Visible := False;
      rliEmitente.Visible := False;
      rllChaveAcesso.Visible := False;
      rliChave.Visible := False;
      rliChave2.Visible := False;
      rliChave3.Visible := False;
    end;

  // Expande a logomarca
  if FExpandirLogoMarca = True then
    begin
      rlmEmitente.Visible := False;
      rlmEndereco.Visible := False;
      rllFone.Visible := False;
      rlmSiteEmail.Visible := False;
      with rliLogo do
        begin
          Height := 101;
          Width := 268;
          Top := 14;
          Left := 2;
          Scaled := False;
          Stretch := True;
        end;
    end;

  DadosAdicionais;
  Header;
  Emitente;
  Destinatario;
  Imposto;
  Itens;
  ISSQN;
  Transporte;
  AddFatura;
  Observacoes;

  // Altera a fonte do DANFE
  case FNomeFonte of
    nfArial:
      for b := 0 to (RLNFe.ControlCount - 1) do
        for i := 0 to ((TRLBand(RLNFe.Controls[b]).ControlCount) - 1) do
          begin
            if TRLLabel((TRLBand(RLNFe.Controls[b])).Controls[i]).Tag
                                                                     <> 20 then
              TRLLabel((TRLBand(RLNFe.Controls[b])).Controls[i]).Font.Name :=
                                                                      'Arial';
              if TRLLabel((TRLBand(RLNFe.Controls[b])).Controls[i]).Tag = 3 then
                TRLLabel((TRLBand(RLNFe.Controls[b])).Controls[i]).Font.Size :=
               (TRLLabel((TRLBand(RLNFe.Controls[b])).Controls[i]).Font.Size) - 1;
          end;

    nfCourierNew:
      begin
        for b := 0 to (RLNFe.ControlCount - 1) do
          for i := 0 to ((TRLBand(RLNFe.Controls[b]).ControlCount) - 1) do
            begin
              TRLLabel((TRLBand(RLNFe.Controls[b])).Controls[i]).Font.Name :=
                                                                'Courier New';
              if (TRLLabel((TRLBand(RLNFe.Controls[b])).Controls[i]).Tag = 0) or
                (TRLLabel((TRLBand(RLNFe.Controls[b])).Controls[i]).Tag = 703) or
                (TRLLabel((TRLBand(RLNFe.Controls[b])).Controls[i]).Tag = 704) or
                (TRLLabel((TRLBand(RLNFe.Controls[b])).Controls[i]).Tag = 705) then
                TRLLabel((TRLBand(RLNFe.Controls[b])).Controls[i]).Font.Size :=
               (TRLLabel((TRLBand(RLNFe.Controls[b])).Controls[i]).Font.Size) - 1;

              if TRLLabel((TRLBand(RLNFe.Controls[b])).Controls[i]).Tag = 705 then
                TRLLabel((TRLBand(RLNFe.Controls[b])).Controls[i]).Top :=
                (TRLLabel((TRLBand(RLNFe.Controls[b])).Controls[i]).Top) - 1;
            end;

        rllNumNF1.Font.Size := rllNumNF1.Font.Size -2;
        rllNumNF1.Top := rllNumNF1.Top + 1;
        rllChave.Font.Size := rllChave.Font.Size - 1;
        rllFone.Font.Size := rllFone.Font.Size - 1;
      end;

    nfTimesNewRoman:
      for b := 0 to (RLNFe.ControlCount - 1) do
        for i := 0 to ((TRLBand(RLNFe.Controls[b]).ControlCount) - 1) do
          begin
            if TRLLabel((TRLBand(RLNFe.Controls[b])).Controls[i]).Tag
                                                                     <> 20 then
              TRLLabel((TRLBand(RLNFe.Controls[b])).Controls[i]).Font.Name :=
                                                             'Times New Roman';
              if TRLLabel((TRLBand(RLNFe.Controls[b])).Controls[i]).Tag = 3 then
                TRLLabel((TRLBand(RLNFe.Controls[b])).Controls[i]).Font.Size :=
               (TRLLabel((TRLBand(RLNFe.Controls[b])).Controls[i]).Font.Size) - 1;
          end;
  end;

  // Dados em negrito
  if FNegrito then
    begin
      for b := 0 to (RLNFe.ControlCount - 1) do
        for i := 0 to ((TRLBand(RLNFe.Controls[b]).ControlCount) - 1) do
          begin
            if TRLLabel((TRLBand(RLNFe.Controls[b])).Controls[i]).Tag = 703 then
              TRLLabel((TRLBand(RLNFe.Controls[b])).Controls[i]).Font.Style := [fsBold];
          end;
    end
  else
    begin
      for b := 0 to (RLNFe.ControlCount - 1) do
        for i := 0 to ((TRLBand(RLNFe.Controls[b]).ControlCount) - 1) do
          begin
            if TRLLabel((TRLBand(RLNFe.Controls[b])).Controls[i]).Tag = 703 then
              TRLLabel((TRLBand(RLNFe.Controls[b])).Controls[i]).Font.Style := [];
          end;
    end;

  // Altera a fonte da Raz�o Social do Emitente
  rlmEmitente.Font.Size := FTamanhoFonte_RazaoSocial;

  // Verifica se ser� exibida a 'continua��o das informa��es complementares'
  if rlmDadosAdicionaisAuxiliar.Lines.Count > iLimiteLinhas then
    begin
      rlbContinuacaoInformacoesComplementares.Visible := True;
      h := (rlmContinuacaoDadosAdicionais.Top +
            rlmContinuacaoDadosAdicionais.Height) + 2;
      LinhaDCInferior.Top := h;
      h := (h - LinhaDCSuperior.Top) + 1;
      LinhaDCEsquerda.Height := h;
      LinhaDCDireita.Height := h;
    end
  else
    rlbContinuacaoInformacoesComplementares.Visible := False;

  iQuantItens := FNFe.Det.Count;
end;

procedure TfrlDANFeRLRetrato.Header;
var sChaveContingencia: String;
begin
  with FNFe.InfNFe, FNFe.Ide do
  begin
     rllChave.Caption := NotaUtil.FormatarChaveAcesso (Copy (FNFe.InfNFe.Id, 4, 44));
     rlbCodigoBarras.Caption := Copy (FNFe.InfNFe.Id, 4, 44);
     rllNumNF0.Caption := 'N� ' + FormatFloat ('000,000,000', nNF);
     rllNumNF1.Caption := 'N� ' + FormatFloat ('000,000,000', nNF);
     rllSERIE0.Caption := 'S�RIE ' + IntToStr(Serie);
     rllSERIE1.Caption := 'S�RIE ' + IntToStr(Serie);
     rllNatOperacao.Caption :=  NatOp;
     if tpNF = tnEntrada then // = entrada
        rllEntradaSaida.Caption := '0'
     else
        rllEntradaSaida.Caption := '1';

    rllEmissao.Caption   := DFeUtil.FormatDate(DateToStr(dEmi));
    rllSaida.Caption     := IfThen(DSaiEnt <> 0,
                                      DFeUtil.FormatDate(DateToStr(dSaiEnt)));
    rllHoraSaida.Caption := IfThen(hSaiEnt <> 0, FormatDateTime('hh:nn:ss', hSaiEnt));

    if FNFe.Ide.tpEmis in [teNormal, teSCAN] then
      begin
        if FNFe.procNFe.cStat > 0 then
          begin
            rllDadosVariaveis1a.Visible := True;
            rllDadosVariaveis1b.Visible := True;
            rllDadosVariaveis1c.Visible := True;
          end
        else
          begin
            rllDadosVariaveis1a.Visible := False;
            rllDadosVariaveis1b.Visible := False;
            rllDadosVariaveis1c.Visible := False;
          end;
        rlbCodigoBarrasFS.Visible := False;
        // Alteracao aplicada para corrigir a impressao do protocolo da NFe
        // quando emitindo DANFE candelado.
        // Alterado por Jorge Henrique em 22/02/2013
        if FProtocoloNFe <> '' then
           rllDadosVariaveis3.Caption:= FProtocoloNFe
        else
          rllDadosVariaveis3.Caption := FNFe.procNFe.nProt + ' ' +DateTimeToStr(FNFe.procNFe.dhRecbto);
        rllAvisoContingencia.Visible := False;
        rlbAvisoContingencia.Visible := False;
      end
    else if FNFe.Ide.tpEmis in [teContingencia, teFSDA] then
      begin
        sChaveContingencia := NotaUtil.GerarChaveContingencia(FNFe);
        rllDadosVariaveis1a.Visible := False;
        rllDadosVariaveis1b.Visible := False;
        rllDadosVariaveis1c.Visible := False;
        rlbCodigoBarras.Visible := True;
        rlbCodigoBarrasFS.Caption := sChaveContingencia;
        rlbCodigoBarrasFS.Visible := True;
        rllDadosVariaveis3_Descricao.Caption := 'DADOS DA NF-E';
        rllDadosVariaveis3.Caption :=
                          NotaUtil.FormatarChaveContigencia(sChaveContingencia);
        rllAvisoContingencia.Caption := 'DANFE em Conting�ncia - ' +
                                'Impresso em decorr�ncia de problemas t�cnicos';
        if (dhCont > 0) and (xJust > '') then
          rllContingencia.Caption :=
                    'Data / Hora da entrada em conting�ncia: ' +
                    FormatDateTime('dd/mm/yyyy hh:nn:ss', dhCont) +
                    '   Motivo: ' + xJust;
        rllAvisoContingencia.Visible := True;
        rlbAvisoContingencia.Visible := True;
      end
    else if FNFe.Ide.tpEmis = teDPEC then
      begin
        rllDadosVariaveis1a.Visible := True;
        rllDadosVariaveis1b.Visible := True;
        rllDadosVariaveis1c.Visible := False;
        rlbCodigoBarras.Visible := True;
        rlbCodigoBarrasFS.Visible := False;
        rllDadosVariaveis3_Descricao.Caption := 'N�MERO DE REGISTRO DPEC';

        if FProtocoloNFe <> '' then
          rllDadosVariaveis3.Caption := FProtocoloNFe
        else
          rllDadosVariaveis3.Caption := FNFe.procNFe.nProt + ' ' +
                                          DateTimeToStr(FNFe.procNFe.dhRecbto);

        rllAvisoContingencia.Caption := 'DANFE em Conting�ncia - DPEC ' +
                        'regularmente recebida pela Receita Federal do Brasil';

        if (dhCont > 0) and (xJust > '') then
          rllContingencia.Caption :=
                    'Data / Hora da entrada em conting�ncia: ' +
                    FormatDateTime('dd/mm/yyyy hh:nn:ss', dhCont) +
                    '   Motivo: ' + xJust;

        rllAvisoContingencia.Visible := True;
        rlbAvisoContingencia.Visible := True;
      end;
  end;
end;

procedure TfrlDANFeRLRetrato.Emitente;
begin
  //emit
  with FNFe.Emit do
    begin
      if FRecebemoDe = '' then
        FRecebemoDe := rllRecebemosDe.Caption;

      rllRecebemosDe.Caption := Format (FRecebemoDe, [ XNome ]);
      rllInscricaoEstadual.Caption := IE;
      rllInscrEstSubst.caption := IEST;
      rllCNPJ.Caption := DFeUtil.FormatarCNPJ(CNPJCPF );
      rlmEmitente.Lines.Text   := XNome;
      with EnderEmit do
        begin
          rlmEndereco.Lines.Clear;
          if xCpl > '' then
            rlmEndereco.Lines.add (XLgr + IfThen (Nro = '0', '', ', ' + Nro) +
                                                ' ' + XCpl + ' - ' + XBairro)
          else
            rlmEndereco.Lines.add (XLgr + IfThen (Nro = '0', '', ', ' + Nro) +
                                                              ' - ' + XBairro);

          rlmEndereco.Lines.add ('CEP: ' + FormatarCEP(IntToStr(CEP)) +
                                                    ' - ' + XMun + ' - ' + UF);

        if FFax <> '' then
          begin
            rllFone.Caption := 'TEL: ' + FormatarFone(Fone) +
                                      ' - FAX: ' + FormatarFone(FFax);
            rllFone.Font.Size := 7;
          end
        else
          begin
            rllFone.Caption := 'TEL: ' + FormatarFone(Fone);
            rllFone.Font.Size := 8;
          end;
      end;
    end;

    if (FSite <> '') or (FEmail <> '') then
      begin
        rlmSiteEmail.Lines.BeginUpdate;
        rlmSiteEmail.Lines.Clear;
        if FSite <> '' then
          rlmSiteEmail.Lines.Add(FSite);
        if FEmail <> '' then
          rlmSiteEmail.Lines.Add(FEmail);
        rlmSiteEmail.Lines.EndUpdate;
        rlmSiteEmail.Visible := True;
        rlmEndereco.Top := 48;
        rllFone.Top := 82;
        rlmSiteEmail.Top := 92;
      end
    else
      begin
        rlmSiteEmail.Visible := False;
        rlmEndereco.Top := 58;
        rllFone.Top := 96;
      end;
end;

procedure TfrlDANFeRLRetrato.Destinatario;
begin
  // destinatario
  with FNFe.Dest do
    begin
      if Length(CNPJCPF) = 14 then
        rllDestCNPJ.Caption := DFeUtil.FormatarCNPJ(CNPJCPF)
      else
        if Length(CNPJCPF) = 11 then
          rllDestCNPJ.Caption := DFeUtil.FormatarCPF(CNPJCPF)
        else
          rllDestCNPJ.Caption := CNPJCPF;

      rllDestIE.Caption   := IE;
      rllDestNome.Caption := XNome;
      with EnderDest do
        begin
          if xCpl > '' then
            rllDestEndereco.Caption := XLgr + IfThen (Nro = '0', '', ', ' + Nro)
                                                                  + ' ' + xCpl
          else
            rllDestEndereco.Caption := XLgr + IfThen (Nro = '0', '', ', ' + Nro);
          rllDestBairro.Caption := XBairro;
          rllDestCidade.Caption := XMun;
          rllDestUF.Caption := UF;
          rllDestCEP.Caption := FormatarCEP(IntToStr(CEP));
          rllDestFONE.Caption := FormatarFone(Fone);
        end;
    end;
end;

procedure TfrlDANFeRLRetrato.EnderecoEntrega;
var sEndereco: WideString;
sCNPJ: String;
begin
  if FNFe.Entrega.xLgr > '' then
    begin
      with FNFe.Entrega do
        begin
          if Trim (CNPJCPF) <>  '' then
            sCNPJ := DFeUtil.FormatarCNPJ(CNPJCPF)
          else
            sCNPJ := DFeUtil.FormatarCPF(CNPJCPF);

          if xCpl > '' then
            sEndereco := XLgr + IfThen (Nro = '0', '', ', ' + Nro) + ' - ' + xCpl
          else
            sEndereco := XLgr + IfThen (Nro = '0', '', ', ' + Nro);

          sEntrega := 'LOCAL DE ENTREGA: ' + sEndereco + ' - ' + xBairro +
                      ' - ' + xMun + '-' + UF + '  CNPJ: ' + sCNPJ;
        end;
    end;
end;

procedure TfrlDANFeRLRetrato.EnderecoRetirada;
var sEndereco: WideString;
sCNPJ: String;
begin
  if FNFe.Retirada.xLgr > '' then
    begin
      with FNFe.Retirada do
        begin
          if Trim (CNPJCPF) <>  '' then
            sCNPJ := DFeUtil.FormatarCNPJ(CNPJCPF)
          else
            sCNPJ := DFeUtil.FormatarCPF(CNPJCPF);

          if xCpl > '' then
            sEndereco := XLgr + IfThen (Nro = '0', '', ', ' + Nro) + ' - ' + xCpl
          else
            sEndereco := XLgr + IfThen (Nro = '0', '', ', ' + Nro);

          sRetirada := 'LOCAL DE RETIRADA: ' + sEndereco + ' - ' + xBairro +
                      ' - ' + xMun + '-' + UF + '  CNPJ: ' + sCNPJ;
        end;
    end;
end;

procedure TfrlDANFeRLRetrato.Imposto;
begin
  with FNFe.Total.ICMSTot do
  begin
    rllBaseICMS.Caption      := DFeUtil.FormatFloat(VBC, '###,###,###,##0.00');
    rllValorICMS.Caption     := DFeUtil.FormatFloat(VICMS, '###,###,###,##0.00');
    rllBaseICMSST.Caption     := DFeUtil.FormatFloat(VBCST, '###,###,###,##0.00');
    rllValorICMSST.Caption    := DFeUtil.FormatFloat(VST, '###,###,###,##0.00');
    rllTotalProdutos.Caption := DFeUtil.FormatFloat(VProd, '###,###,###,##0.00');
    rllValorFrete.Caption    := DFeUtil.FormatFloat(VFrete, '###,###,###,##0.00');
    rllValorSeguro.Caption   := DFeUtil.FormatFloat(VSeg, '###,###,###,##0.00');
    rllDescontos.Caption     := DFeUtil.FormatFloat(VDesc, '###,###,###,##0.00');
    rllAcessorias.Caption    := DFeUtil.FormatFloat(VOutro, '###,###,###,##0.00');
    rllValorIPI.Caption      := DFeUtil.FormatFloat(VIPI, '###,###,###,##0.00');
    rllTotalNF.Caption       := DFeUtil.FormatFloat(VNF, '###,###,###,##0.00');
  end;
end;

procedure TfrlDANFeRLRetrato.Transporte;
begin
  with FNFe.Transp do
    begin
      case modFrete of
        mfContaEmitente: rllTransModFrete.Caption := '0 - EMITENTE';
        mfContaDestinatario: rllTransModFrete.Caption := '1 - DEST/REM';
        mfContaTerceiros: rllTransModFrete.Caption := '2 - TERCEIROS';
        mfSemFrete: rllTransModFrete.Caption := '9 - SEM FRETE';
      end;

      with Transporta do
        begin
          if Trim (CNPJCPF) <> '' then
            begin
              if length(Trim (CNPJCPF)) <= 11 then
                rllTransCNPJ.Caption := DFeUtil.FormatarCPF(CNPJCPF)
              else
                rllTransCNPJ.Caption := DFeUtil.FormatarCNPJ(CNPJCPF);
            end
          else
            rllTransCNPJ.Caption := '';

          rllTransNome.Caption := XNome;
          rllTransIE.Caption := IE;
          rllTransEndereco.Caption := XEnder;
          rllTransCidade.Caption := XMun;
          rllTransUF.Caption := UF;
        end;
    end;

  with FNFe.Transp.VeicTransp do
  begin
    rllTransCodigoANTT.Caption := RNTC;
    rllTransPlaca.Caption   :=  Placa;
    rllTransUFPlaca.Caption :=  UF;
  end;

  if FNFe.Transp.Vol.Count > 0 then
   begin
     with FNFe.Transp.Vol[0] do
      begin
        if qVol > 0 then
          rllTransQTDE.Caption       :=  IntToStr(QVol);
        rllTransEspecie.Caption    :=  Esp  ;
        rllTransMarca.Caption      :=  Marca;
        rllTransNumeracao.Caption  :=  NVol ;
        if pesoL > 0 then
          rllTransPesoLiq.Caption    :=  DFeUtil.FormatFloat(PesoL,
                                                        '###,###,###,##0.000');
        if pesoB > 0 then
          rllTransPesoBruto.Caption  :=  DFeUtil.FormatFloat(PesoB,
                                                        '###,###,###,##0.000');
      end;
   end
  else
   begin
     rllTransQTDE.Caption       :=  '';
     rllTransEspecie.Caption    :=  '';
     rllTransMarca.Caption      :=  '';
     rllTransNumeracao.Caption  :=  '';
     rllTransPesoLiq.Caption    :=  '';
     rllTransPesoBruto.Caption  :=  '';
   end;
end;

procedure TfrlDANFeRLRetrato.DadosAdicionais;
var sInfCompl, sInfAdFisco, sInfContr, sObsFisco, sObsProcRef, sInfInteira,
    sProtocolo, sSuframa : WideString;
    sIndProc: String;
    i: Integer;
begin
  rlmDadosAdicionaisAuxiliar.Lines.BeginUpdate;
  rlmDadosAdicionaisAuxiliar.Lines.Clear;

  // Protocolo de autoriza��o, nos casos de emiss�o em conting�ncia
  if (FNFe.Ide.tpEmis in [teContingencia, teFSDA, teDPEC]) and
                                              (FNFe.procNFe.cStat = 100) then
    begin
      sProtocolo := 'PROTOCOLO DE AUTORIZA��O DE USO: ' +
                     FNFe.procNFe.nProt + ' ' + DateTimeToStr(FNFe.procNFe.dhRecbto);
      InsereLinhas(sProtocolo, iLimiteCaracteresLinha, rlmDadosAdicionaisAuxiliar);
    end;

  // Inscri��o Suframa
  if FNFe.Dest.ISUF > '' then
    begin
      sSuframa := 'INSCRI��O SUFRAMA: ' + FNFe.Dest.ISUF;
      InsereLinhas(sSuframa, iLimiteCaracteresLinha, rlmDadosAdicionaisAuxiliar);
    end;

  // Endere�o de retirada
  if FNFe.Retirada.xLgr > '' then
    begin
      EnderecoRetirada;
      sRetirada := sRetirada;
      InsereLinhas(sRetirada, iLimiteCaracteresLinha, rlmDadosAdicionaisAuxiliar);
    end;

  // Endere�o de entrega
  if FNFe.Entrega.xLgr > '' then
    begin
      EnderecoEntrega;
      sEntrega := sEntrega;
      InsereLinhas(sEntrega, iLimiteCaracteresLinha, rlmDadosAdicionaisAuxiliar);
    end;

  // Informa��es de interesse do fisco
  if FNFe.InfAdic.infAdFisco > '' then
    begin
      if FNFe.InfAdic.infCpl > '' then
        sInfAdFisco := FNFe.InfAdic.infAdFisco + '; '
      else
        sInfAdFisco := FNFe.InfAdic.infAdFisco;
    end
  else
    sInfAdFisco := '';

  // Informa��es de interesse do contribuinte
  if FNFe.InfAdic.infCpl > '' then
    sInfCompl := FNFe.InfAdic.infCpl
  else
    sInfCompl := '';

  // Informa��es de uso livre do contribuinte com "xCampo" e "xTexto"
  if FNFe.InfAdic.obsCont.Count > 0 then
    begin
      sInfContr := '';
      for i := 0 to (FNFe.InfAdic.obsCont.Count - 1) do
        begin
          if FNFe.InfAdic.obsCont.Items[i].Index =
                                          (FNFe.InfAdic.obsCont.Count - 1) then
            sInfContr := sInfContr + FNFe.InfAdic.obsCont.Items[i].xCampo +
                              ': ' + FNFe.InfAdic.obsCont.Items[i].xTexto
          else
            sInfContr := sInfContr + FNFe.InfAdic.obsCont.Items[i].xCampo +
                            ': ' + FNFe.InfAdic.obsCont.Items[i].xTexto + '; ';
        end; // for i := 0 to (FNFe.InfAdic.obsCont.Count - 1)
      if (sInfCompl > '') or (sInfAdFisco > '') then
        sInfContr := sInfContr + '; '
    end // if FNFe.InfAdic.obsCont.Count > 0
  else
    sInfContr := '';

  // Informa��es de uso livre do fisco com "xCampo" e "xTexto"
  if FNFe.InfAdic.obsFisco.Count > 0 then
    begin
      sObsFisco := '';
      for i := 0 to (FNFe.InfAdic.obsFisco.Count - 1) do
        begin
          if FNFe.InfAdic.obsFisco.Items[i].Index =
                                          (FNFe.InfAdic.obsFisco.Count - 1) then
            sObsFisco := sObsFisco + FNFe.InfAdic.obsFisco.Items[i].xCampo +
                              ': ' + FNFe.InfAdic.obsFisco.Items[i].xTexto
          else
            sObsFisco := sObsFisco + FNFe.InfAdic.obsFisco.Items[i].xCampo +
                            ': ' + FNFe.InfAdic.obsFisco.Items[i].xTexto + '; ';
        end; // for i := 0 to (FNFe.InfAdic.obsFisco.Count - 1)
      if (sInfCompl > '') or (sInfAdFisco > '') then
        sObsFisco := sObsFisco + '; '
    end // if FNFe.InfAdic.obsFisco.Count > 0
  else
    sObsFisco := '';

  // Informa��es do processo referenciado
  if FNFe.InfAdic.procRef.Count > 0 then
    begin
      for i := 0 to (FNFe.InfAdic.procRef.Count - 1) do
        begin
          case FNFe.InfAdic.procRef.Items[i].indProc of
            ipSEFAZ: sIndProc := 'SEFAZ';
            ipJusticaFederal: sIndProc := 'JUSTI�A FEDERAL';
            ipJusticaEstadual: sIndProc := 'JUSTI�A ESTADUAL';
            ipSecexRFB: sIndProc := 'SECEX / RFB';
            ipOutros: sIndProc := 'OUTROS';
          end;

          if FNFe.InfAdic.procRef.Items[i].Index =
                                          (FNFe.InfAdic.procRef.Count - 1) then
            sObsProcRef := sObsProcRef + 'PROCESSO OU ATO CONCESS�RIO N�: ' +
                           FNFe.InfAdic.procRef.Items[i].nProc + ' - ORIGEM: ' +
                           sIndProc
          else
            sObsProcRef := sObsProcRef + 'PROCESSO OU ATO CONCESS�RIO N�: ' +
                           FNFe.InfAdic.procRef.Items[i].nProc + ' - ORIGEM: ' +
                           sIndProc + '; ';
        end; // for i := 0 to (FNFe.InfAdic.procRef.Count - 1)
      if (sInfCompl > '') or (sInfAdFisco > '') then
        sObsProcRef := sObsProcRef + '; '
    end // if FNFe.InfAdic.procRef.Count > 0
  else
    sObsProcRef := '';

  sInfInteira := sInfAdFisco + sObsFisco + sObsProcRef + sInfContr + sInfCompl;
  InsereLinhas(sInfInteira, iLimiteCaracteresLinha, rlmDadosAdicionaisAuxiliar);
  rlmDadosAdicionaisAuxiliar.Lines.EndUpdate;
end;

procedure TfrlDANFeRLRetrato.Observacoes;
var i, iMaximoLinhas, iRestanteLinhas: Integer;
sTexto: WideString;
begin
  rlmDadosAdicionais.Lines.BeginUpdate;
  rlmDadosAdicionais.Lines.Clear;

  if rlmDadosAdicionaisAuxiliar.Lines.Count > iLimiteLinhas then
    begin
      iMaximoLinhas := iLimiteLinhas;
      iRestanteLinhas := rlmDadosAdicionaisAuxiliar.Lines.Count - iLimiteLinhas;
      rlmContinuacaoDadosAdicionais.Lines.BeginUpdate;
      sTexto := '';
      for i := 0 to (iRestanteLinhas - 1) do
        begin
          sTexto := sTexto +
                  rlmDadosAdicionaisAuxiliar.Lines.Strings[(iMaximoLinhas + i)];
        end;

      InsereLinhas(sTexto, iLimiteCaracteresContinuacao, rlmContinuacaoDadosAdicionais);
      rlmContinuacaoDadosAdicionais.Lines.Text :=
                        StringReplace(rlmContinuacaoDadosAdicionais.Lines.Text,
                        ';', '', [rfReplaceAll, rfIgnoreCase]);
      rlmContinuacaoDadosAdicionais.Lines.EndUpdate;
    end
  else
    iMaximoLinhas := rlmDadosAdicionaisAuxiliar.Lines.Count;

  for i := 0 to (iMaximoLinhas - 1) do
    begin
      rlmDadosAdicionais.Lines.Add(rlmDadosAdicionaisAuxiliar.Lines.Strings[i]);
    end;

  rlmDadosAdicionais.Lines.Text := StringReplace(rlmDadosAdicionais.Lines.Text,
                                   ';', '', [rfReplaceAll, rfIgnoreCase]);

  rlmDadosAdicionais.Lines.EndUpdate;
end;

procedure TfrlDANFeRLRetrato.Itens;
var nItem, i : Integer ;
sCST, sBCICMS, sBCICMSST, sALIQICMS, sVALORICMS, sVALORICMSST, sALIQIPI,
sVALORIPI, vAux: String;
sDetalhamentoEspecifico: WideString;
dPercDesc: Double;
begin
  for nItem := 0 to (FNFe.Det.Count - 1) do
    begin
      with FNFe.Det.Items[nItem] do
        begin
          with Prod do
            begin
              with Imposto.ICMS do
                begin
                  sALIQIPI   := '0,00' ;
                  sVALORIPI  := '0,00' ;

                  cdsItens.Append ;
                  cdsItens.FieldByName('CODIGO').AsString := CProd;
                  cdsItens.FieldByName('EAN').AsString := cEAN;
                  if FImprimirDetalhamentoEspecifico = True then
                    begin
                      sDetalhamentoEspecifico := #13#10;
                      if Prod.veicProd.chassi > '' then // XML de ve�culo novo
                        begin
                          if dv_tpOp in FDetVeiculos then
                            begin
                              case Prod.veicProd.tpOP of
                                toVendaConcessionaria: vAux := '1-VENDA CONCESSION�RIA';
                                toFaturamentoDireto:   vAux := '2-FAT. DIRETO CONS. FINAL';
                                toVendaDireta:         vAux := '3-VENDA DIRETA';
                                toOutros:              vAux := '0-OUTROS';
                              end;
                              sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'TIPO DA OPERA��O: ' + vAux + #13#10;
                            end;

                          if dv_Chassi in FDetVeiculos then
                            sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'CHASSI: ' + Prod.veicProd.chassi + #13#10;

                          if dv_cCor in FDetVeiculos then
                            sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'C�DIGO DA COR: ' + Prod.veicProd.cCor + #13#10;

                          if dv_xCor in FDetVeiculos then
                            sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'NOME DA COR: ' + Prod.veicProd.xCor + #13#10;

                          if dv_pot in FDetVeiculos then
                            sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'POT�NCIA DO MOTOR: ' + Prod.veicProd.pot + #13#10;

                          if dv_cilin in FDetVeiculos then
                            sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'CILINDRADAS: ' + Prod.veicProd.Cilin + #13#10;

                          if dv_pesoL in FDetVeiculos then
                            sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'PESO L�QUIDO: ' + Prod.veicProd.pesoL + #13#10;

                          if dv_pesoB in FDetVeiculos then
                            sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'PESO BRUTO: ' + Prod.veicProd.pesoB + #13#10;

                          if dv_nSerie in FDetVeiculos then
                            sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'N�MERO DE S�RIE: ' + Prod.veicProd.nSerie + #13#10;

                          if dv_tpComb in FDetVeiculos then
                            begin
                              case StrToInt(Prod.veicProd.tpComb) of
                                 1: vAux := '01-�LCOOL';
                                 2: vAux := '02-GASOLINA';
                                 3: vAux := '03-DIESEL';
                                 4: vAux := '04-GASOG�NIO';
                                 5: vAux := '05-G�S METANO';
                                 6: vAux := '06-ELETRICO/F INTERNA';
                                 7: vAux := '07-ELETRICO/F EXTERNA';
                                 8: vAux := '08-GASOLINA/GNC';
                                 9: vAux := '09-�LCOOL/GNC';
                                10: vAux := '10-DIESEL / GNC';
                                11: vAux := '11-VIDE CAMPO OBSERVA��O';
                                12: vAux := '12-�LCOOL/GNV';
                                13: vAux := '13-GASOLINA/GNV';
                                14: vAux := '14-DIESEL/GNV';
                                15: vAux := '15-G�S NATURAL VEICULAR';
                                16: vAux := '16-�LCOOL/GASOLINA';
                                17: vAux := '17-GASOLINA/�LCOOL/GNV';
                                18: vAux := '18-GASOLINA/EL�TRICO'
                              else
                                vAux := Prod.veicProd.tpComb;
                              end;
                              sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'COMBUST�VEL: ' + vAux + #13#10;
                            end;

                          if dv_nMotor in FDetVeiculos then
                            sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'N�MERO DO MOTOR: ' + Prod.veicProd.nMotor + #13#10;

                          if dv_CMT in FDetVeiculos then
                            sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'CAP. M�X. TRA��O: ' + Prod.veicProd.CMT + #13#10;

                          if dv_dist in FDetVeiculos then
                            sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'DIST�NCIA ENTRE EIXOS: ' + Prod.veicProd.dist + #13#10;

                          if dv_anoMod in FDetVeiculos then
                            sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'ANO DO MODELO: ' + IntToStr(Prod.veicProd.anoMod) + #13#10;

                          if dv_anoFab in FDetVeiculos then
                            sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'ANO DE FABRICA��O: ' + IntToStr(Prod.veicProd.anoFab) + #13#10;

                          if dv_tpPint in FDetVeiculos then
                            sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'TIPO DE PINTURA: ' + Prod.veicProd.tpPint + #13#10;

                          if dv_tpVeic in FDetVeiculos then
                            begin
                              case Prod.veicProd.tpVeic of
                                1:  vAux := '01-BICICLETA';
                                2:  vAux := '02-CICLOMOTOR';
                                3:  vAux := '03-MOTONETA';
                                4:  vAux := '04-MOTOCICLETA';
                                5:  vAux := '05-TRICICLO';
                                6:  vAux := '06-AUTOM�VEL';
                                7:  vAux := '07-MICROONIBUS';
                                8:  vAux := '08-ONIBUS';
                                9:  vAux := '09-BONDE';
                                10: vAux := '10-REBOQUE';
                                11: vAux := '11-SEMI-REBOQUE';
                                12: vAux := '12-CHARRETE';
                                13: vAux := '13-CAMIONETA';
                                14: vAux := '14-CAMINH�O';
                                15: vAux := '15-CARRO�A';
                                16: vAux := '16-CARRO DE M�O';
                                17: vAux := '17-CAMINH�O TRATOR';
                                18: vAux := '18-TRATOR DE RODAS';
                                19: vAux := '19-TRATOR DE ESTEIRAS';
                                20: vAux := '20-TRATOR MISTO';
                                21: vAux := '21-QUADRICICLO';
                                22: vAux := '22-CHASSI/PLATAFORMA';
                                23: vAux := '23-CAMINHONETE';
                                24: vAux := '24-SIDE-CAR';
                                25: vAux := '25-UTILIT�RIO';
                                26: vAux := '26-MOTOR-CASA'
                              else
                                vAux := IntToStr(Prod.veicProd.tpVeic);
                              end;
                              sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'TIPO DE VE�CULO: ' + vAux + #13#10;
                            end;

                          if dv_espVeic in FDetVeiculos then
                            begin
                              case Prod.veicProd.espVeic of
                                1: vAux := '01-PASSAGEIRO';
                                2: vAux := '02-CARGA';
                                3: vAux := '03-MISTO';
                                4: vAux := '04-CORRIDA';
                                5: vAux := '05-TRA��O';
                                6: vAux := '06-ESPECIAL';
                                7: vAux := '07-COLE��O'
                              else
                                vAux := IntToStr(Prod.veicProd.espVeic);
                              end;
                              sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'ESP�CIE DO VE�CULO: ' + vAux + #13#10;
                            end;

                          if dv_VIN in FDetVeiculos then
                            begin
                              if Prod.veicProd.VIN = 'R' then
                                vAux := 'R-REMARCADO'
                              else
                                if Prod.veicProd.VIN = 'N' then
                                  vAux := 'N-NORMAL'
                                else
                                  vAux := Prod.veicProd.VIN;
                              sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'VIN (CHASSI): ' + vAux + #13#10;
                            end;

                          if dv_condVeic in FDetVeiculos then
                            begin
                              case Prod.veicProd.condVeic of
                                cvAcabado:     vAux := '1-ACABADO';
                                cvInacabado:   vAux := '2-INACABADO';
                                cvSemiAcabado: vAux := '3-SEMI-ACABADO';
                              end;
                              sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'CONDI��O DO VE�CULO: ' + vAux + #13#10;
                            end;

                          if dv_cMod in FDetVeiculos then
                            sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'C�DIGO MARCA MODELO: ' + Prod.veicProd.cMod + #13#10;

                          if dv_cCorDENATRAN in FDetVeiculos then
                            begin
                              case StrToInt(Prod.veicProd.cCorDENATRAN) of
                                1:  vAux := '01-AMARELO';
                                2:  vAux := '02-AZUL';
                                3:  vAux := '03-BEGE';
                                4:  vAux := '04-BRANCA';
                                5:  vAux := '05-CINZA';
                                6:  vAux := '06-DOURADA';
                                7:  vAux := '07-GREN�';
                                8:  vAux := '08-LARANJA';
                                9:  vAux := '09-MARROM';
                                10: vAux := '10-PRATA';
                                11: vAux := '11-PRETA';
                                12: vAux := '12-ROSA';
                                13: vAux := '13-ROXA';
                                14: vAux := '14-VERDE';
                                15: vAux := '15-VERMELHA';
                                16: vAux := '16-FANTASIA'
                              else
                                vAux := Prod.veicProd.cCorDENATRAN;
                              end;
                              sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'C�DIGO COR DENATRAN: ' + vAux + #13#10;
                            end;

                          if dv_lota in FDetVeiculos then
                            sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'CAPACIDADE M�XIMA DE LOTA��O: ' + IntToStr(Prod.veicProd.lota) + #13#10;

                          if dv_tpRest in FDetVeiculos then
                            begin
                              case Prod.veicProd.tpRest of
                                0: vAux := '0-N�O H�';
                                1: vAux := '1-ALIENA��O FIDUCI�RIA';
                                2: vAux := '2-RESERVA DE DOMIC�LIO';
                                3: vAux := '3-RESERVA DE DOM�NIO';
                                4: vAux := '4-PENHOR DE VE�CULOS';
                                9: vAux := '9-OUTRAS'
                              else
                                vAux := IntToStr(Prod.veicProd.tpRest);
                              end;
                              sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'RESTRI��O: ' + vAux;
                            end;

                          cdsItens.FieldByName('DESCRICAO').AsString := xProd + sDetalhamentoEspecifico;
                        end // // if Prod.veicProd.chassi > ''
                      else
                        begin
                          if Prod.med.Count > 0 then
                            begin
                              for i := 0 to Prod.med.Count - 1 do
                                begin
                                  if dm_nLote in FDetMedicamentos then
                                    sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'N�MERO DO LOTE: ' + Prod.med.Items[i].nLote + #13#10;

                                  if dm_qLote in FDetMedicamentos then
                                    sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'QUANTIDADE DO LOTE: ' + FormatFloat('###,##0.000', Prod.med.Items[i].qLote) + #13#10;

                                  if dm_dFab in FDetMedicamentos then
                                    sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'DATA DE FABRICA��O: ' + DateToStr(Prod.med.Items[i].dFab) + #13#10;

                                  if dm_dVal in FDetMedicamentos then
                                    sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'DATA DE VALIDADE: ' + DateToStr(Prod.med.Items[i].dVal) + #13#10;

                                  if dm_vPMC in FDetMedicamentos then
                                    sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'PRE�O M�X. CONSUMIDOR: R$ ' + FormatFloat('###,##0.00', Prod.med.Items[i].vPMC) + #13#10;

                                  if (sDetalhamentoEspecifico > '') and (sDetalhamentoEspecifico <> #13#10) then
                                    begin
                                      if i = Prod.med.Count - 1 then
                                        sDetalhamentoEspecifico := sDetalhamentoEspecifico
                                      else
                                        sDetalhamentoEspecifico := sDetalhamentoEspecifico + #13#10;
                                    end;

                                end;  // for i := 0 to Prod.med.Count - 1

                              cdsItens.FieldByName('DESCRICAO').AsString := xProd + sDetalhamentoEspecifico;
                            end // if Prod.med.Count > 0
                          else
                            begin
                              if Prod.arma.Count > 0 then
                                begin
                                  for i := 0 to Prod.arma.Count - 1 do
                                    begin
                                      if da_tpArma in FDetArmamentos then
                                        begin
                                          case Prod.arma.Items[i].tpArma of
                                            taUsoPermitido: vAux := '0-USO PERMITIDO';
                                            taUsoRestrito:  vAux := '1-USO RESTRITO';
                                          end;
                                          sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'TIPO DE ARMA: ' + vAux + #13#10;
                                        end;

                                      if da_nSerie in FDetArmamentos then
                                        sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'No. S�RIE: ARMA' + Prod.arma.Items[i].nSerie + #13#10;

                                      if da_nCano in FDetArmamentos then
                                        sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'No. S�RIE CANO: ' + Prod.arma.Items[i].nCano + #13#10;

                                      if da_descr in FDetArmamentos then
                                        sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'DESCRI��O ARMA: ' + Prod.arma.Items[i].descr + #13#10;

                                      if (sDetalhamentoEspecifico > '') and (sDetalhamentoEspecifico <> #13#10) then
                                        begin
                                          if i = Prod.arma.Count - 1 then
                                            sDetalhamentoEspecifico := sDetalhamentoEspecifico
                                          else
                                            sDetalhamentoEspecifico := sDetalhamentoEspecifico + #13#10;
                                        end;

                                    end;  // for i := 0 to Prod.arma.Count - 1

                                  cdsItens.FieldByName('DESCRICAO').AsString := xProd + sDetalhamentoEspecifico;
                                end  // if Prod.arma.Count > 0
                              else
                                begin
                                  if Prod.comb.cProdANP > 0 then
                                    begin
                                      if dc_cProdANP in FDetCombustiveis then
                                        sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'C�D. PRODUTO ANP: ' + IntToStr(Prod.comb.cProdANP) + #13#10;

                                      if dc_CODIF in FDetCombustiveis then
                                        if Prod.comb.CODIF > '' then
                                          sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'AUTORIZA��O/CODIF: ' + Prod.comb.CODIF + #13#10;

                                      if dc_qTemp in FDetCombustiveis then
                                        if Prod.comb.qTemp > 0 then
                                          sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'QTD. FATURADA TEMP. AMBIENTE: ' + FormatFloat('###,##0.0000', Prod.comb.qTemp) + #13#10;

                                      if dc_UFCons in FDetCombustiveis then
                                        sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'UF DE CONSUMO: ' + Prod.comb.UFcons + #13#10;

                                      if Prod.comb.CIDE.qBCProd > 0 then
                                        begin
                                          if dc_qBCProd in FDetCombustiveis then
                                            sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'BASE DE C�LCULO CIDE: ' + FormatFloat('###,##0.0000', Prod.comb.CIDE.qBCProd) + #13#10;

                                          if dc_vAliqProd in FDetCombustiveis then
                                            sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'AL�QUOTA CIDE: ' + FormatFloat('###,##0.0000', Prod.comb.CIDE.vAliqProd) + #13#10;

                                          if dc_vCIDE in FDetCombustiveis then
                                            sDetalhamentoEspecifico := sDetalhamentoEspecifico + 'VALOR CIDE: ' + FormatFloat('###,##0.00', Prod.comb.CIDE.vCIDE);
                                        end;  // if Prod.comb.CIDE.qBCProd > 0

                                      cdsItens.FieldByName('DESCRICAO').AsString := xProd + sDetalhamentoEspecifico;
                                    end  // if Prod.comb.cProdANP > 0
                                  else
                                    cdsItens.FieldByName('DESCRICAO').AsString := XProd;
                                end; // else if Prod.arma.Count > 0
                            end // else if Prod.med.Count > 0
                        end; // else if Prod.veicProd.chassi > ''
                    end // if FImprimirDetalhamentoEspecifico = True
                  else
                    cdsItens.FieldByName('DESCRICAO').AsString := XProd;

                  cdsItens.FieldByName('NCM').AsString := NCM;
                  cdsItens.FieldByName('CFOP').AsString := CFOP;
                  cdsItens.FieldByName('QTDE').AsString := FormatFloat(format(sDisplayFormat ,[FCasasDecimaisqCom,0]),qCom);
                  cdsItens.FieldByName('VALOR').AsString := FormatFloat(format(sDisplayFormat ,[FCasasDecimaisvUnCom,0]),vUnCom);
                  cdsItens.FieldByName('UNIDADE').AsString := UCom;
                  cdsItens.FieldByName('TOTAL').AsString :=
                                      FormatFloat('###,###,###,##0.00', vProd);

                  if FImprimirDescPorc = True then
                    begin
                      dPercDesc := (vDesc * 100) / vProd;
                      cdsItens.FieldByName('VALORDESC').AsString :=
                                      FormatFloat('###,###,###,##0.00', dPercDesc);
                    end
                  else
                    cdsItens.FieldByName('VALORDESC').AsString :=
                                      FormatFloat('###,###,###,##0.00', vDesc);

                  if FNFe.Emit.CRT in [crtRegimeNormal, crtSimplesExcessoReceita] then
                    begin
                      if CSTICMSToStr(CST) > '' then
                        sCST := OrigToStr(orig) + CSTICMSToStr(CST)
                      else
                        sCST := '';

                      sBCICMS      := FormatFloat('###,###,###,##0.00', VBC);
                      sALIQICMS    := FormatFloat('###,###,###,##0.00', PICMS);
                      sVALORICMS   := FormatFloat('###,###,###,##0.00', VICMS);
                      sALIQICMS    := FormatFloat('###,###,###,##0.00', pICMS);
                      sBCICMSST    := FormatFloat('###,###,###,##0.00', vBCST);
                      sVALORICMSST := FormatFloat('###,###,###,##0.00', vICMSST);

                      cdsItens.FieldByName('CST').AsString         := sCST;
                      cdsItens.FieldByName('BICMS').AsString       := sBCICMS;
                      cdsItens.FieldByName('ALIQICMS').AsString    := sALIQICMS;
                      cdsItens.FieldByName('VALORICMS').AsString   := sVALORICMS;
                      cdsItens.FieldByName('BICMSST').AsString     := sBCICMSST;
                      cdsItens.FieldByName('VALORICMSST').AsString := sVALORICMSST;

                      lblCST.Caption := 'CST';
                      lblCST.Font.Size := 5;
                      lblCST.Top := 18;
                      txtCST.DataField := 'CST';
                    end; //FNFe.Emit.CRT = crtRegimeNormal

                  if FNFe.Emit.CRT = crtSimplesNacional then
                    begin
                      //=============  Trecho copiado do Danfe em Quick Report =======================
                      //==============================================================================
                      // Adicionado para imprimir al�quotas
                      //==============================================================================
                      if CSOSNIcmsToStr(Imposto.ICMS.CSOSN) > '' then
                        cdsItens.FieldByName('CSOSN').AsString := OrigToStr(orig) + CSOSNIcmsToStr(Imposto.ICMS.CSOSN)
                      else
                        cdsItens.FieldByName('CSOSN').AsString := '';

                      //==============================================================================
                      // Resetando valores das ql�quotas
                      //==============================================================================
                      sBCICMS    := '0,00';
                      sALIQICMS  := '0,00';
                      sVALORICMS := '0,00';

                 {     case CSOSN of
                        csosn900:
                          begin
                            sBCICMS    := FormatFloat('#,##0.00', VBC);
                            sALIQICMS  := FormatFloat('#,##0.00', PICMS);
                            sVALORICMS := FormatFloat('#,##0.00', VICMS);
                         end;
                      end;    }

                      sBCICMS      := FormatFloat('###,###,###,##0.00', VBC);
                      sALIQICMS    := FormatFloat('###,###,###,##0.00', PICMS);
                      sVALORICMS   := FormatFloat('###,###,###,##0.00', VICMS);
                      sALIQICMS    := FormatFloat('###,###,###,##0.00', pICMS);
                      sBCICMSST    := FormatFloat('###,###,###,##0.00', vBCST);
                      sVALORICMSST := FormatFloat('###,###,###,##0.00', vICMSST);

                      cdsItens.FieldByName('CST').AsString         := sCST;
                      cdsItens.FieldByName('BICMS').AsString       := sBCICMS;
                      cdsItens.FieldByName('ALIQICMS').AsString    := sALIQICMS;
                      cdsItens.FieldByName('VALORICMS').AsString   := sVALORICMS;
                      cdsItens.FieldByName('BICMSST').AsString     := sBCICMSST;
                      cdsItens.FieldByName('VALORICMSST').AsString := sVALORICMSST;
                      //===========  Final do trecho copiado do Danfe em Quick Report ===============

                      lblCST.Caption := 'CSOSN';
                      lblCST.Font.Size := 4;
                      lblCST.Top := 19;
                      txtCST.DataField := 'CSOSN';

                    end; //FNFe.Emit.CRT = crtSimplesNacional
                end; // with Imposto.ICMS do

              with Imposto.IPI do
                begin
                  if (CST = ipi00) or (CST = ipi49) or
                     (CST = ipi50) or (CST = ipi99) then
                    begin
                      sALIQIPI  := FormatFloat('##0.00', PIPI) ;
                      sVALORIPI := FormatFloat('##0.00', VIPI) ;
                    end
                end;

              cdsItens.FieldByName('ALIQIPI').AsString := sALIQIPI;
              cdsItens.FieldByName('VALORIPI').AsString := sVALORIPI;
              cdsItens.Post ;
            end; // with Prod do
        end; //  with FNFe.Det.Items[nItem] do
    end; //  for nItem := 0 to ( FNFe.Det.Count - 1 ) do

   cdsItens.First ;
end;

procedure TfrlDANFeRLRetrato.ISSQN;
begin
  with FNFe.Total.ISSQNtot do
    begin
      if FNFe.Emit.IM > '' then
        begin
          rlbISSQN.Visible := True;
          rllISSQNInscricao.Caption := FNFe.Emit.IM;
          rllISSQNValorServicos.Caption :=
                                DFeUtil.FormatFloat(FNFe.Total.ISSQNtot.vServ,
                                '###,###,##0.00');
          rllISSQNBaseCalculo.Caption :=
                                  DFeUtil.FormatFloat(FNFe.Total.ISSQNtot.vBC,
                                  '###,###,##0.00');
          rllISSQNValorISSQN.Caption :=
                                  DFeUtil.FormatFloat(FNFe.Total.ISSQNtot.vISS,
                                  '###,###,##0.00');
        end
      else
        rlbISSQN.Visible := False
    end;
end;

procedure TfrlDANFeRLRetrato.AddFatura;
var x, iQuantDup, iLinhas, iColunas, iPosQuadro, iAltLinha,
    iAltQuadro1Linha, iAltQuadro, iAltBand, iFolga: Integer;
begin
  //zera
  iQuantDup := 0;
  for x := 1 to 60 do
    begin
      TRLLabel (FindComponent ('rllFatNum'   + intToStr (x))).Caption := '';
      TRLLabel (FindComponent ('rllFatData'  + intToStr (x))).Caption := '';
      TRLLabel (FindComponent ('rllFatValor' + intToStr (x))).Caption := '';
    end;

  case FNFe.Ide.indPag of
  ipVista:
    begin
      TRLLabel (FindComponent('rllFatNum1')).AutoSize := True;
      TRLLabel (FindComponent('rllFatNum1')).Caption  := 'PAGAMENTO A VISTA';
      iQuantDup := 1;

      for x := 0 to 11 do
        TRLLabel(FindComponent('rllCabFatura' + intToStr (x + 1))).Visible := False;

      rliFatura1.Visible := False;
      rliFatura2.Visible := False;
      rliFatura3.Visible := False;
    end;  // ipVista

  ipPrazo:
    begin
      if FNFe.Cobr.Dup.Count = 0 then
        begin
          TRLLabel (FindComponent('rllFatNum1')).AutoSize := True;
          TRLLabel (FindComponent('rllFatNum1')).Caption  := 'PAGAMENTO A PRAZO';
          iQuantDup := 1;

          for x := 0 to 11 do
            TRLLabel(FindComponent('rllCabFatura' + intToStr (x + 1))).Visible := False;

          rliFatura1.Visible := False;
          rliFatura2.Visible := False;
          rliFatura3.Visible := False;
        end
      else
        begin
          if FNFe.Cobr.Dup.Count > 60 then
            iQuantDup := 60
          else
            iQuantDup := FNFe.Cobr.Dup.Count;

          //adiciona
          for x := 0 to (iQuantDup - 1) do
            with FNFe.Cobr.Dup[ x ] do
             begin
               TRLLabel (FindComponent ('rllFatNum'   + intToStr (x + 1))).Caption :=
                                                                              NDup;
               TRLLabel (FindComponent ('rllFatData'  + intToStr (x + 1))).Caption :=
                                              DFeUtil.FormatDate(DateToStr(DVenc));
               TRLLabel (FindComponent ('rllFatValor' + intToStr (x + 1))).Caption :=
                                                        DFeUtil.FormatFloat(VDup);
             end;
        end; // if FNFe.Cobr.Dup.Count = 0
    end;  // ipPrazo

  ipOutras:
    begin
      rlbFatura.Visible := False;
    end;  // ipOutras
  end; // case FNFe.Ide.indPag

  {=============== Ajusta o tamanho do quadro das faturas ===============}
  if iQuantDup > 0 then
    begin
      iColunas := 4; // Quantidade de colunas
      iAltLinha := 13;  // Altura de cada linha
      iPosQuadro := 12; // Posi��o (Top) do Quadro
      iAltQuadro1Linha := 27; // Altura do quadro com 1 linha
      iFolga := 5; // Dist�ncia entre o final da Band e o final do quadro

      if (iQuantDup mod iColunas) = 0 then // Quantidade de linhas
        iLinhas := iQuantDup div iColunas
      else
        iLinhas := (iQuantDup div iColunas) + 1;

      if iLinhas = 1 then
        iAltQuadro := iAltQuadro1Linha
      else
        iAltQuadro := iAltQuadro1Linha + ((iLinhas - 1) * iAltLinha);

      iAltBand := iPosQuadro + iAltQuadro + iFolga;

      rlbFatura.Height := iAltBand;
      rliFatura.Height := iAltQuadro;
      rliFatura1.Height := iAltQuadro;
      rliFatura2.Height := iAltQuadro;
      rliFatura3.Height := iAltQuadro;
    end; // if iQuantDup > 0
end;

procedure TfrlDANFeRLRetrato.rlbItensAfterPrint(Sender: TObject);
var h: Integer;
str: WideString;
begin
  q := q + 1;
  if FNFe.Det.Items[q - 1].infAdProd > '' then
    begin
      rlmObsItem.Lines.BeginUpdate;
      rlmObsItem.Lines.Clear;
      str := StringReplace((FNFe.Det.Items[q - 1].infAdProd), ';',
                                          #13#10, [rfReplaceAll, rfIgnoreCase]);
      rlmObsItem.Lines.Add(str);
      rlmObsItem.Lines.EndUpdate;
      rlbObsItem.Visible := True;

      h := (rlmObsItem.Top + rlmObsItem.Height) + 2;
      LinhaFimObsItem.Top := h;
      h := h + 1;
      LinhaObsItemEsquerda.Height := h;
      LinhaObsItemDireita.Height := h;
      if iQuantItens > q then
        LinhaInicioItem.Visible := True
      else
        LinhaInicioItem.Visible := False;
    end
  else
    rlbObsItem.Visible := False;

end;

procedure TfrlDANFeRLRetrato.rlbDadosAdicionaisBeforePrint(Sender: TObject;
  var PrintIt: Boolean);
begin
  if FPosCanhoto = pcCabecalho then
    begin
      rlbReciboHeader.Visible := False;
      rlbDivisaoRecibo.Visible := False;
    end;

  // Posiciona a Marca D'�gua
  rliMarcaDagua1.Top := rlbCabecalhoItens.Top +
                     ((rlbDadosAdicionais.Top - rlbCabecalhoItens.Top) div 2) -
                     (rliMarcaDagua1.Height div 2);
    
end;

procedure TfrlDANFeRLRetrato.rlbItensBeforePrint(Sender: TObject;
  var PrintIt: Boolean);
begin
  // Controla os itens por p�gina
  iItemAtual := iItemAtual + 1;

  if FProdutosPorPagina = 0 then
    rlbItens.PageBreaking := pbNone
  else
    begin
      if iItemAtual = FProdutosPorPagina then
        rlbItens.PageBreaking := pbBeforePrint
      else
        rlbItens.PageBreaking := pbNone;
    end; // if FProdutosPorPagina = 0
end;

procedure TfrlDANFeRLRetrato.rlbEmitenteAfterPrint(Sender: TObject);
begin
  iItemAtual := 0;
end;

procedure TfrlDANFeRLRetrato.ConfigureDataSource;
begin
  self.rlmDescricao.DataSource := DataSource1;
  self.RLNFe.DataSource := DataSource1;
  self.txtCodigo.DataSource := DataSource1;
  self.txtNCM.DataSource := DataSource1;
  self.txtCST.DataSource := DataSource1;
  self.txtCFOP.DataSource := DataSource1;
  self.txtUnidade.DataSource := DataSource1;
  self.txtQuantidade.DataSource := DataSource1;
  self.txtValorUnitario.DataSource := DataSource1;
  self.txtValorTotal.DataSource := DataSource1;
  self.txtBaseICMS.DataSource := DataSource1;
  self.txtValorICMS.DataSource := DataSource1;
  self.txtValorIPI.DataSource := DataSource1;
  self.txtAliqICMS.DataSource := DataSource1;
  self.txtAliqIPI.DataSource := DataSource1;
  self.txtValorDesconto.DataSource := DataSource1;
end;

procedure TfrlDANFeRLRetrato.rlbCabecalhoItensBeforePrint(Sender: TObject;
  var PrintIt: Boolean);
begin
  if FImprimirDescPorc = True then
    lblPercValorDesc.Caption := 'PERC.(%)'
  else
    lblPercValorDesc.Caption := 'VALOR';
end;

procedure TfrlDANFeRLRetrato.FormCreate(Sender: TObject);
begin
  inherited;

  iLimiteLinhas := 10;
  iLinhasUtilizadas := 0;
  iLimiteCaracteresLinha := 81;
  iLimiteCaracteresContinuacao := 129;
end;

end.

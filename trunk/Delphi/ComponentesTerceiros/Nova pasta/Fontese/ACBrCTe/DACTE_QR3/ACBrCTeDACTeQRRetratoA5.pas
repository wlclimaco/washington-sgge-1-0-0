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
|* 16/02/2012: Italo Jurisato Junior
******************************************************************************}

{$I ACBr.inc}

unit ACBrCTeDACTeQRRetratoA5;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, ExtCtrls, QuickRpt, QRCtrls, XMLIntf, XMLDoc, 
  JPEG, ACBrCTeQRCodeBar, pcnConversao, DB,
  DBClient, ACBrCTeDACTeQR {, qrRRect};

type
  TfrmDACTeQRRetratoA5 = class(TfrmDACTeQR)
    qrb_08_Itens: TQRBand;
    qrdbtTpDoc1: TQRDBText;
    cdsDocumentos: TClientDataSet;
    qrdbtCnpjEmitente1: TQRDBText;
    qrdbtDocumento1: TQRDBText;
    qrdbtDocumento2: TQRDBText;
    qrdbtCnpjEmitente2: TQRDBText;
    qrdbtTpDoc2: TQRDBText;
    cdsDocumentosTIPO_1: TStringField;
    cdsDocumentosCNPJCPF_1: TStringField;
    cdsDocumentosDOCUMENTO_1: TStringField;
    cdsDocumentosTIPO_2: TStringField;
    cdsDocumentosCNPJCPF_2: TStringField;
    cdsDocumentosDOCUMENTO_2: TStringField;
    QRShape29: TQRShape;
    qrb_01_Recibo: TQRBand;
    qrsLinhaH03: TQRShape;
    qrmEmitente: TQRMemo;
    qrmDadosEmitente: TQRMemo;
    qriLogo: TQRImage;
    qriBarCode: TQRImage;
    qrlNumCte: TQRLabel;
    QRLabel17: TQRLabel;
    QRLabel18: TQRLabel;
    QRLabel6: TQRLabel;
    QRLabel8: TQRLabel;
    QRLabel21: TQRLabel;
    QRLabel23: TQRLabel;
    QRLabel25: TQRLabel;
    QRLabel33: TQRLabel;
    QRLabel74: TQRLabel;
    qrlChave: TQRLabel;
    qrlPageNumber: TQRLabel;
    qrlSerie: TQRLabel;
    qrlModelo: TQRLabel;
    qrlEmissao: TQRLabel;
    qrlModal: TQRLabel;
    qrlProtocolo: TQRLabel;
    qrlTipoCte: TQRLabel;
    qrlDescricao: TQRLabel;
    QRLabel77: TQRLabel;
    QRLabel2: TQRLabel;
    QRLabel9: TQRLabel;
    qrlTipoServico: TQRLabel;
    QRLabel28: TQRLabel;
    qrlTomaServico: TQRLabel;
    QRLabel78: TQRLabel;
    qrlFormaPagamento: TQRLabel;
    qrlInscSuframa: TQRLabel;
    qrb_07_HeaderItens: TQRBand;
    QRLabel91: TQRLabel;
    QRLabel92: TQRLabel;
    QRLabel96: TQRLabel;
    QRLabel100: TQRLabel;
    QRLabel106: TQRLabel;
    QRLabel109: TQRLabel;
    QRShape34: TQRShape;
    QRShape28: TQRShape;
    QRShape35: TQRShape;
    qrsFimItens: TQRShape;
    qrb_09_Obs: TQRBand;
    qrb_02_Cabecalho: TQRChildBand;
    QRLabel29: TQRLabel;
    qrlNatOperacao: TQRLabel;
    QRLabel12: TQRLabel;
    qrlOrigPrestacao: TQRLabel;
    QRLabel14: TQRLabel;
    qrlDestPrestacao: TQRLabel;
    QRLabel13: TQRLabel;
    QRLabel16: TQRLabel;
    QRLabel22: TQRLabel;
    QRLabel24: TQRLabel;
    QRLabel26: TQRLabel;
    qrlRazaoRemet: TQRLabel;
    qrlEnderecoRemet1: TQRLabel;
    qrlEnderecoRemet2: TQRLabel;
    qrlMunRemet: TQRLabel;
    qrlCnpjRemet: TQRLabel;
    qrlPaisRemet: TQRLabel;
    QRLabel27: TQRLabel;
    QRLabel30: TQRLabel;
    QRLabel31: TQRLabel;
    QRLabel32: TQRLabel;
    QRLabel79: TQRLabel;
    qrlRazaoDest: TQRLabel;
    qrlEnderecoDest1: TQRLabel;
    qrlEnderecoDest2: TQRLabel;
    QRLabel93: TQRLabel;
    qrlInscEstRemet: TQRLabel;
    QRLabel95: TQRLabel;
    qrlFoneRemet: TQRLabel;
    qrlCEPRemet: TQRLabel;
    QRLabel98: TQRLabel;
    qrlMunDest: TQRLabel;
    qrlPaisDest: TQRLabel;
    qrlCnpjDest: TQRLabel;
    QRLabel114: TQRLabel;
    qrlInscEstDest: TQRLabel;
    QRLabel116: TQRLabel;
    qrlFoneDest: TQRLabel;
    qrlCEPDest: TQRLabel;
    QRLabel119: TQRLabel;
    qrmObs: TQRMemo;
    qrlMsgTeste: TQRLabel;
    qrb_03_DadosDACTe: TQRChildBand;
    QRLabel1: TQRLabel;
    qrlProdPredominante: TQRLabel;
    QRLabel4: TQRLabel;
    qrlOutrasCaracCarga: TQRLabel;
    QRLabel34: TQRLabel;
    qrlVlrTotalMerc: TQRLabel;
    QRLabel35: TQRLabel;
    QRLabel36: TQRLabel;
    QRLabel41: TQRLabel;
    QRLabel43: TQRLabel;
    QRLabel5: TQRLabel;
    qrlNomeSeguradora: TQRLabel;
    QRLabel37: TQRLabel;
    qrlRespSeguroMerc: TQRLabel;
    QRLabel39: TQRLabel;
    qrlNroApolice: TQRLabel;
    QRLabel40: TQRLabel;
    qrlNroAverbacao: TQRLabel;
    QRShape8: TQRShape;
    QRShape7: TQRShape;
    qrb_04_DadosNotaFiscal: TQRChildBand;
    qrb_05_Complemento: TQRChildBand;
    QRLabel44: TQRLabel;
    qrmCompNome1: TQRMemo;
    QRLabel46: TQRLabel;
    qrmCompValor1: TQRMemo;
    QRLabel42: TQRLabel;
    qrmCompNome2: TQRMemo;
    QRLabel45: TQRLabel;
    qrmCompValor2: TQRMemo;
    QRLabel47: TQRLabel;
    qrmCompNome3: TQRMemo;
    QRLabel48: TQRLabel;
    qrmCompValor3: TQRMemo;
    QRLabel49: TQRLabel;
    qrlVlrTotServico: TQRLabel;
    QRLabel50: TQRLabel;
    qrlVlrTotReceber: TQRLabel;
    QRShape18: TQRShape;
    QRShape16: TQRShape;
    QRShape15: TQRShape;
    QRShape19: TQRShape;
    QRShape6: TQRShape;
    QRLabel61: TQRLabel;
    QRLabel62: TQRLabel;
    QRLabel63: TQRLabel;
    QRLabel64: TQRLabel;
    qrmComplChave1: TQRMemo;
    qrmComplValor1: TQRMemo;
    qrmComplChave2: TQRMemo;
    qrmComplValor2: TQRMemo;
    qrsQuadro01: TQRShape;
    qrsQuadro04: TQRShape;
    qrsQuadro05: TQRShape;
    qrsQuadro07: TQRShape;
    qrsQuadro08: TQRShape;
    qrsLinhaV10: TQRShape;
    qrsLinhaV04: TQRShape;
    qrsLinhaH01: TQRShape;
    qrsLinhaH02: TQRShape;
    qrsLinhaV09: TQRShape;
    qrsLinhaV08: TQRShape;
    qrsLinhaV06: TQRShape;
    qrsLinhaV05: TQRShape;
    qrsLinhaH04: TQRShape;
    qrsLinhaV07: TQRShape;
    qrsLinhaV01: TQRShape;
    qrsLinhaV11: TQRShape;
    qrsLinhaH05: TQRShape;
    QRShape55: TQRShape;
    QRShape9: TQRShape;
    QRShape56: TQRShape;
    QRShape58: TQRShape;
    QRShape59: TQRShape;
    QRShape60: TQRShape;
    QRShape61: TQRShape;
    QRShape62: TQRShape;
    qrb_17_Sistema: TQRChildBand;
    QRShape10: TQRShape;
    QRLabel65: TQRLabel;
    QRShape2: TQRShape;
    QRLabel66: TQRLabel;
    QRLabel70: TQRLabel;
    QRShape53: TQRShape;
    QRLabel15: TQRLabel;
    QRSysData1: TQRSysData;
    qrlblSistema: TQRLabel;
    qrmQtdUnidMedida1: TQRMemo;
    qrmQtdUnidMedida2: TQRMemo;
    qrmQtdUnidMedida3: TQRMemo;
    qrmQtdUnidMedida5: TQRMemo;
    qrb_06_ValorPrestacao: TQRChildBand;
    QRShape46: TQRShape;
    QRShape48: TQRShape;
    QRShape49: TQRShape;
    QRShape50: TQRShape;
    QRShape51: TQRShape;
    QRShape52: TQRShape;
    QRLabel3: TQRLabel;
    QRLabel132: TQRLabel;
    QRLabel133: TQRLabel;
    QRLabel134: TQRLabel;
    QRLabel135: TQRLabel;
    QRLabel136: TQRLabel;
    QRLabel137: TQRLabel;
    QRLabel138: TQRLabel;
    QRLabel139: TQRLabel;
    QRLabel140: TQRLabel;
    qrlSerie2: TQRLabel;
    qrlNumCTe2: TQRLabel;
    QRLabel143: TQRLabel;
    qrb_12_ModAereo: TQRChildBand;
    qrb_13_ModAquaviario: TQRChildBand;
    qrb_14_ModFerroviario: TQRChildBand;
    qrb_15_ModDutoviario: TQRChildBand;
    QRShape47: TQRShape;
    QRShape54: TQRShape;
    QRShape63: TQRShape;
    QRShape64: TQRShape;
    QRShape65: TQRShape;
    QRShape66: TQRShape;
    QRShape67: TQRShape;
    QRShape68: TQRShape;
    QRShape69: TQRShape;
    QRLabel141: TQRLabel;
    QRLabel142: TQRLabel;
    QRLabel144: TQRLabel;
    QRLabel145: TQRLabel;
    QRLabel146: TQRLabel;
    QRLabel147: TQRLabel;
    QRLabel148: TQRLabel;
    QRLabel149: TQRLabel;
    QRLabel150: TQRLabel;
    QRLabel153: TQRLabel;
    qrlTrecho: TQRLabel;
    qrlTarifaValor: TQRLabel;
    qrlTarifaCodigo: TQRLabel;
    qrlTarifaCL: TQRLabel;
    qrlMinuta: TQRLabel;
    qrlDadosRetira: TQRLabel;
    qrlAWB: TQRLabel;
    QRLabel154: TQRLabel;
    QRLabel155: TQRLabel;
    qrlCaracAdServico: TQRLabel;
    qrlCaracAdTransporte: TQRLabel;
    QRShape57: TQRShape;
    QRShape72: TQRShape;
    QRLabel156: TQRLabel;
    qrlContaCorrente: TQRLabel;
    QRLabel157: TQRLabel;
    qrlLojaAgenteEmissor: TQRLabel;
    qrlRetira: TQRLabel;
    QRShape70: TQRShape;
    QRShape73: TQRShape;
    QRLabel151: TQRLabel;
    QRShape74: TQRShape;
    QRLabel152: TQRLabel;
    qrlPortoEmbarque: TQRLabel;
    QRLabel158: TQRLabel;
    qrlPortoDestino: TQRLabel;
    QRShape75: TQRShape;
    QRLabel159: TQRLabel;
    qrlIndNavioRebocador: TQRLabel;
    QRShape76: TQRShape;
    QRLabel160: TQRLabel;
    qrlIndConteiners: TQRLabel;
    QRShape77: TQRShape;
    QRLabel162: TQRLabel;
    qrlBCAFRMM: TQRLabel;
    QRLabel164: TQRLabel;
    qrlValorAFRMM: TQRLabel;
    QRLabel166: TQRLabel;
    qrlTipoNav: TQRLabel;
    QRLabel168: TQRLabel;
    qrlDirecao: TQRLabel;
    QRShape78: TQRShape;
    QRShape79: TQRShape;
    QRShape80: TQRShape;
    qrb_01_Recibo_Aereo: TQRChildBand;
    QRLabel19: TQRLabel;
    QRShape81: TQRShape;
    QRShape82: TQRShape;
    QRLabel57: TQRLabel;
    QRLabel60: TQRLabel;
    QRLabel69: TQRLabel;
    QRLabel161: TQRLabel;
    QRLabel67: TQRLabel;
    QRLabel68: TQRLabel;
    QRLabel72: TQRLabel;
    QRLabel163: TQRLabel;
    qrb_11_ModRodLot104: TQRChildBand;
    QRShape4: TQRShape;
    QRShape30: TQRShape;
    QRShape83: TQRShape;
    QRShape84: TQRShape;
    QRShape85: TQRShape;
    QRShape86: TQRShape;
    QRShape87: TQRShape;
    QRShape89: TQRShape;
    QRShape90: TQRShape;
    QRShape92: TQRShape;
    QRLabel167: TQRLabel;
    QRLabel169: TQRLabel;
    QRLabel170: TQRLabel;
    QRLabel171: TQRLabel;
    QRLabel172: TQRLabel;
    QRLabel173: TQRLabel;
    QRLabel174: TQRLabel;
    QRLabel177: TQRLabel;
    QRLabel179: TQRLabel;
    QRLabel181: TQRLabel;
    QRLabel182: TQRLabel;
    QRLabel183: TQRLabel;
    qrmUF2: TQRMemo;
    qrmTipo2: TQRMemo;
    qrmRNTRC2: TQRMemo;
    qrmPlaca2: TQRMemo;
    qrmCNPJForn: TQRMemo;
    qrmNumCompra: TQRMemo;
    qrlNomeMotorista2: TQRLabel;
    qrlLacres2: TQRLabel;
    qrlCPFMotorista2: TQRLabel;
    qrb_18_Recibo: TQRChildBand;
    QRShape91: TQRShape;
    QRShape93: TQRShape;
    QRShape94: TQRShape;
    QRShape95: TQRShape;
    QRShape96: TQRShape;
    QRShape97: TQRShape;
    QRLabel175: TQRLabel;
    QRLabel176: TQRLabel;
    QRLabel180: TQRLabel;
    QRLabel184: TQRLabel;
    QRLabel185: TQRLabel;
    QRLabel186: TQRLabel;
    QRLabel187: TQRLabel;
    QRLabel188: TQRLabel;
    QRLabel189: TQRLabel;
    QRLabel190: TQRLabel;
    QRLabel191: TQRLabel;
    qrlSerie3: TQRLabel;
    qrlNumCTe3: TQRLabel;
    QRShape98: TQRShape;
    qrmCNPJPg: TQRMemo;
    QRShape88: TQRShape;
    qrlVariavel1: TQRLabel;
    qriBarCode2: TQRImage;
    QRShape99: TQRShape;
    qrmQtdUnidMedida4: TQRMemo;
    QRLabel73: TQRLabel;
    QRShape100: TQRShape;
    qrsQuadro03: TQRShape;
    QRShape101: TQRShape;
    QRShape102: TQRShape;
    QRShape103: TQRShape;
    qrsQuadro02: TQRShape;
    QRLabel52: TQRLabel;
    qrlSitTrib: TQRLabel;
    QRShape22: TQRShape;
    QRLabel55: TQRLabel;
    QRLabel56: TQRLabel;
    QRLabel54: TQRLabel;
    QRLabel53: TQRLabel;
    QRLabel58: TQRLabel;
    QRShape20: TQRShape;
    QRShape23: TQRShape;
    QRShape25: TQRShape;
    QRShape26: TQRShape;
    qrlBaseCalc: TQRLabel;
    qrlAliqICMS: TQRLabel;
    qrlVlrICMS: TQRLabel;
    qrlRedBaseCalc: TQRLabel;
    qrlICMS_ST: TQRLabel;
    QRLabel10: TQRLabel;
    QRLabel11: TQRLabel;
    lblCIOT: TQRLabel;
    QRLabel83: TQRLabel;
    QRLabel84: TQRLabel;
    qrlRntrcEmpresa: TQRLabel;
    qrlCIOT: TQRLabel;
    qrlLotacao: TQRLabel;
    qrlDtPrevEntrega: TQRLabel;
    qrsCIOT: TQRShape;
    QRShape36: TQRShape;
    QRShape37: TQRShape;
    QRShape38: TQRShape;
    QRShape24: TQRShape;
    QRLabel85: TQRLabel;
    QRShape1: TQRShape;
    QRLabel20: TQRLabel;
    QRShape5: TQRShape;
    qrmObsFisco: TQRMemo;
    QRShape3: TQRShape;
    procedure QRCTeBeforePrint(Sender: TCustomQuickRep; var PrintReport: Boolean);
    procedure qrb_01_ReciboBeforePrint(Sender: TQRCustomBand; var PrintBand: Boolean);
    procedure qrb_02_CabecalhoBeforePrint(Sender: TQRCustomBand; var PrintBand: Boolean);
    procedure qrb_03_DadosDACTeBeforePrint(Sender: TQRCustomBand;
      var PrintBand: Boolean);
    procedure qrb_04_DadosNotaFiscalBeforePrint(Sender: TQRCustomBand;
      var PrintBand: Boolean);
    procedure qrb_05_ComplementoBeforePrint(Sender: TQRCustomBand;
      var PrintBand: Boolean);
    procedure qrb_06_ValorPrestacaoBeforePrint(Sender: TQRCustomBand;
      var PrintBand: Boolean);
    procedure qrb_07_HeaderItensBeforePrint(Sender: TQRCustomBand;
      var PrintBand: Boolean);
    procedure qrb_08_ItensBeforePrint(Sender: TQRCustomBand; var PrintBand: Boolean);
    procedure qrb_09_ObsBeforePrint(Sender: TQRCustomBand; var PrintBand: Boolean);
    procedure qrb_17_SistemaBeforePrint(Sender: TQRCustomBand;
      var PrintBand: Boolean);
    procedure qrb_12_ModAereoBeforePrint(Sender: TQRCustomBand;
      var PrintBand: Boolean);
    procedure qrb_13_ModAquaviarioBeforePrint(Sender: TQRCustomBand;
      var PrintBand: Boolean);
    procedure qrb_14_ModFerroviarioBeforePrint(Sender: TQRCustomBand;
      var PrintBand: Boolean);
    procedure qrb_15_ModDutoviarioBeforePrint(Sender: TQRCustomBand;
      var PrintBand: Boolean);
    procedure qrb_01_Recibo_AereoBeforePrint(Sender: TQRCustomBand;
      var PrintBand: Boolean);
    procedure qrb_11_ModRodLot104BeforePrint(Sender: TQRCustomBand;
      var PrintBand: Boolean);
    procedure qrb_18_ReciboBeforePrint(Sender: TQRCustomBand;
      var PrintBand: Boolean);
  private
    FTotalPages: integer;
    procedure Itens;
  public
    procedure ProtocoloCTe(const sProtocolo: string);
  end;

implementation

uses
  StrUtils, DateUtils, 
  ACBrDFeUtil, ACBrCTeUtil;

{$R *.dfm}

const
  // Alterado por Italo em 13/04/2012
  _NUM_ITEMS_PAGE1      = 6; //esse valor eh dobrado por ter 2 NF por linha
//  _NUM_ITEMS_PAGE1      = 26; //esse valor eh dobrado por ter 2 NF por linha
  _NUM_ITEMS_OTHERPAGES = 88;

var
  FProtocoloCTe : string;
  nItemControle : Integer;
  Fracionado    : Integer;
  Versao        : Integer;

procedure TfrmDACTeQRRetratoA5.Itens;
var
  I, J, K, Item : Integer;
begin
  if QRCTe.PageNumber > 0 then
    exit;

  cdsDocumentos.CreateDataSet;
  cdsDocumentos.Open;
  Item := 0;
  //Varrendo NF comum
  for I := 0 to (FCTe.Rem.InfNF.Count - 1) do
  begin
    with FCTe.Rem.InfNF.Items[I] do
    begin
      if (Item mod 2) = 0 then
      begin
        cdsDocumentos.Append;

        cdsDocumentosTIPO_1.AsString := 'NF';
        cdsDocumentosCNPJCPF_1.AsString := DFeUtil.FormatarCNPJ(FCTe.Rem.CNPJCPF);
        cdsDocumentosDOCUMENTO_1.AsString := serie + '-' + nDoc;
      end
      else
      begin
        cdsDocumentosTIPO_2.AsString := 'NF';
        cdsDocumentosCNPJCPF_2.AsString := DFeUtil.FormatarCNPJ(FCTe.Rem.CNPJCPF);
        cdsDocumentosDOCUMENTO_2.AsString := serie + '-' + nDoc;

        cdsDocumentos.Post;
      end;
      inc(Item);
    end;
  end;
  //Varrendo NFe
  for I := 0 to (FCTe.Rem.InfNFE.Count - 1) do
  begin
    with FCTe.Rem.InfNFE.Items[I] do
    begin
      if (Item mod 2) = 0 then
      begin
        cdsDocumentos.Append;
        cdsDocumentosTIPO_1.AsString := 'NF-E';
        // Alterado por Italo em 17/05/2011
        cdsDocumentosCNPJCPF_1.AsString := CTeUtil.FormatarChaveAcesso(chave, True);
      end
      else
      begin
        cdsDocumentosTIPO_2.AsString := 'NF-E';
        // Alterado por Italo em 17/05/2011
        cdsDocumentosCNPJCPF_2.AsString := CTeUtil.FormatarChaveAcesso(chave, True);
        cdsDocumentos.Post;
      end;
      inc(Item);
    end;
  end;
  //Varrendo Outros
  for I := 0 to (FCTe.Rem.InfOutros.Count - 1) do
  begin
    with FCTe.Rem.InfOutros.Items[I] do
    begin
      if (Item mod 2) = 0 then
      begin
        cdsDocumentos.Append;
        // Alterado por Italo em 18/04/2012
        // TpcteTipoDocumento = (tdDeclaracao, tdDutoviario, tdOutros);
        case tpDoc of
         tdDeclaracao: begin
                        cdsDocumentosTIPO_1.AsString      := 'DECLAR';
                        cdsDocumentosCNPJCPF_1.AsString   := DFeUtil.FormatarCNPJ(FCTe.Rem.CNPJCPF);
                        cdsDocumentosDOCUMENTO_1.AsString := 'Declara��o Doc.: ' + nDoc;
                       end;
         tdDutoviario: begin
                        cdsDocumentosTIPO_1.AsString      := 'DUTO';
                        cdsDocumentosCNPJCPF_1.AsString   := DFeUtil.FormatarCNPJ(FCTe.Rem.CNPJCPF);
                        cdsDocumentosDOCUMENTO_1.AsString := 'Dutovi�rio Doc.: ' + nDoc;
                       end;
         tdOutros:     begin
                        cdsDocumentosTIPO_1.AsString      := 'Outros';
                        cdsDocumentosCNPJCPF_1.AsString   := DFeUtil.FormatarCNPJ(FCTe.Rem.CNPJCPF);
                        cdsDocumentosDOCUMENTO_1.AsString := copy( trim(descOutros), 1, 20 ) + ' Doc.: '+ nDoc;
                       end;
        end;
//        cdsDocumentosTIPO_1.AsString := descOutros;
//        cdsDocumentosCNPJCPF_1.AsString := DFeUtil.FormatarCNPJ(FCTe.Rem.CNPJCPF);
      end
      else
      begin
        // Alterado por Italo em 18/04/2012
        // TpcteTipoDocumento = (tdDeclaracao, tdDutoviario, tdOutros);
        case tpDoc of
         tdDeclaracao: begin
                        cdsDocumentosTIPO_2.AsString      := 'DECLAR';
                        cdsDocumentosCNPJCPF_2.AsString   := DFeUtil.FormatarCNPJ(FCTe.Rem.CNPJCPF);
                        cdsDocumentosDOCUMENTO_2.AsString := 'Declara��o Doc.: ' + nDoc;
                       end;
         tdDutoviario: begin
                        cdsDocumentosTIPO_2.AsString      := 'DUTO';
                        cdsDocumentosCNPJCPF_2.AsString   := DFeUtil.FormatarCNPJ(FCTe.Rem.CNPJCPF);
                        cdsDocumentosDOCUMENTO_2.AsString := 'Dutovi�rio Doc.: ' + nDoc;
                       end;
         tdOutros:     begin
                        cdsDocumentosTIPO_2.AsString      := 'Outros';
                        cdsDocumentosCNPJCPF_2.AsString   := DFeUtil.FormatarCNPJ(FCTe.Rem.CNPJCPF);
                        cdsDocumentosDOCUMENTO_2.AsString := copy( trim(descOutros), 1, 20 ) + ' Doc.: '+ nDoc;
                       end;
        end;
//        cdsDocumentosTIPO_2.AsString := descOutros;
//        cdsDocumentosCNPJCPF_2.AsString := DFeUtil.FormatarCNPJ(FCTe.Rem.CNPJCPF);
        cdsDocumentos.Post;
      end;
      inc(Item);
    end;
  end;
 //Varrendo Documentos de Transporte anterior
 // Incluido / Alterado por Italo em 13/12/2010
  for I := 0 to (FCTe.infCTeNorm.emiDocAnt.Count - 1) do
  begin
    // Em Papel
    // Alterado por Italo em 27/12/2010
    for J := 0 to (FCTe.infCTeNorm.emiDocAnt.Items[I].idDocAnt.Count - 1) do
    begin
      for K := 0 to (FCTe.infCTeNorm.emiDocAnt.Items[I].idDocAnt.Items[J].idDocAntPap.Count - 1) do
      begin
        with FCTe.infCTeNorm.emiDocAnt.Items[I].idDocAnt.Items[J].idDocAntPap.Items[K] do
        begin
          if (Item mod 2) = 0 then
          begin
            cdsDocumentos.Append;

            case tpDoc of
             daCTRC: cdsDocumentosTIPO_1.AsString := 'CTRC';
             daCTAC: cdsDocumentosTIPO_1.AsString := 'CTAC';
             daACT:  cdsDocumentosTIPO_1.AsString := 'ACT';
             daNF7:  cdsDocumentosTIPO_1.AsString := 'NF M7';
             daNF27: cdsDocumentosTIPO_1.AsString := 'NF M27';
             daCAN:  cdsDocumentosTIPO_1.AsString := 'CAN';
             daCTMC: cdsDocumentosTIPO_1.AsString := 'CTMC';
             daATRE: cdsDocumentosTIPO_1.AsString := 'ATRE';
             daDTA:  cdsDocumentosTIPO_1.AsString := 'DTA';
             daCAI:  cdsDocumentosTIPO_1.AsString := 'CAI';
             daCCPI: cdsDocumentosTIPO_1.AsString := 'CCPI';
             daCA:   cdsDocumentosTIPO_1.AsString := 'CA';
             daTIF:  cdsDocumentosTIPO_1.AsString := 'TIF';
             daOutros: cdsDocumentosTIPO_1.AsString := 'Outros';
            end;
            cdsDocumentosCNPJCPF_1.AsString := DFeUtil.FormatarCNPJ(FCTe.infCTeNorm.emiDocAnt.Items[I].CNPJCPF);
            cdsDocumentosDOCUMENTO_1.AsString := serie + '-' + IntToStr(nDoc);
          end
          else
          begin
            case tpDoc of
             daCTRC: cdsDocumentosTIPO_2.AsString := 'CTRC';
             daCTAC: cdsDocumentosTIPO_2.AsString := 'CTAC';
             daACT:  cdsDocumentosTIPO_2.AsString := 'ACT';
             daNF7:  cdsDocumentosTIPO_2.AsString := 'NF M7';
             daNF27: cdsDocumentosTIPO_2.AsString := 'NF M27';
             daCAN:  cdsDocumentosTIPO_2.AsString := 'CAN';
             daCTMC: cdsDocumentosTIPO_2.AsString := 'CTMC';
             daATRE: cdsDocumentosTIPO_2.AsString := 'ATRE';
             daDTA:  cdsDocumentosTIPO_2.AsString := 'DTA';
             daCAI:  cdsDocumentosTIPO_2.AsString := 'CAI';
             daCCPI: cdsDocumentosTIPO_2.AsString := 'CCPI';
             daCA:   cdsDocumentosTIPO_2.AsString := 'CA';
             daTIF:  cdsDocumentosTIPO_2.AsString := 'TIF';
             daOutros: cdsDocumentosTIPO_2.AsString := 'Outros';
            end;
            cdsDocumentosCNPJCPF_2.AsString := DFeUtil.FormatarCNPJ(FCTe.infCTeNorm.emiDocAnt.Items[I].CNPJCPF);
            cdsDocumentosDOCUMENTO_2.AsString := serie + '-' + IntToStr(nDoc);

            cdsDocumentos.Post;
          end;
          inc(Item);
        end;
      end;
    end;

    // Eletr�nico
    // Alterado por Italo em 27/12/2010
    for J := 0 to (FCTe.infCTeNorm.emiDocAnt.Items[I].idDocAnt.Count - 1) do
    begin
      for K := 0 to (FCTe.infCTeNorm.emiDocAnt.Items[I].idDocAnt.Items[J].idDocAntEle.Count - 1) do
      begin
        with FCTe.infCTeNorm.emiDocAnt.Items[I].idDocAnt.Items[J].idDocAntEle.Items[K] do
        begin
          if (Item mod 2) = 0 then
          begin
            cdsDocumentos.Append;

            cdsDocumentosTIPO_1.AsString := 'CT-E';
            // Alterado por Italo em 17/05/2011
            cdsDocumentosCNPJCPF_1.AsString := CTeUtil.FormatarChaveAcesso(chave, True);
          end
          else
          begin
            cdsDocumentosTIPO_2.AsString := 'CT-E';
            // Alterado por Italo em 17/05/2011
            cdsDocumentosCNPJCPF_2.AsString := CTeUtil.FormatarChaveAcesso(chave, True);

            cdsDocumentos.Post;
          end;
          inc(Item);
        end;
      end;
    end;

  end;

  cdsDocumentos.First;
end;

procedure TfrmDACTeQRRetratoA5.ProtocoloCTe(const sProtocolo: string);
begin
  FProtocoloCTe := sProtocolo;
end;

procedure TfrmDACTeQRRetratoA5.QRCTeBeforePrint(Sender: TCustomQuickRep; var PrintReport: Boolean);
var
  nRestItens, nTotalItens : Integer;
begin
  inherited;

{$IFDEF PL_103}
  Versao := 103;
{$ENDIF}
{$IFDEF PL_104}
  Versao := 104;
{$ENDIF}

  Itens;
  nItemControle := 0;
  FTotalPages   := 1;
  nTotalItens   := 0;

  // Incluido por Italo em 20/04/2011
  if (FCTe.Rem.InfNF.Count > 0)
   then nTotalItens := FCTe.Rem.InfNF.Count
   else begin
    if (FCTe.Rem.InfNFE.Count > 0)
     then nTotalItens := FCTe.Rem.InfNFE.Count
     else begin
      if (FCTe.Rem.InfOutros.Count > 0)
       then nTotalItens := FCTe.Rem.InfOutros.Count;
     end;
   end;

  // Incluido por Italo em 06/05/2011
  qrb_11_ModRodLot104.Height     := 0;
  qrb_12_ModAereo.Height         := 0;
  qrb_13_ModAquaviario.Height    := 0;
  qrb_14_ModFerroviario.Height   := 0;
  qrb_15_ModDutoviario.Height    := 0;

  case FCTe.Ide.modal of
   mdRodoviario: begin
                  // Incluido por Italo em 26/04/2011
                  if FCTe.Rodo.Lota = ltNao
                   then begin
                    Fracionado                 := 10
                   end
                   else begin
                    Fracionado                 := 0;
                    qrb_11_ModRodLot104.Height := 108;
                   end;
                 end;
   mdAereo: begin
             qrb_12_ModAereo.Height := 97;
            end;
   mdAquaviario: begin
                  qrb_13_ModAquaviario.Height    := 0;
                 end;
   mdFerroviario: begin
                   qrb_14_ModFerroviario.Height   := 0;
                  end;
   mdDutoviario: begin
                  qrb_15_ModDutoviario.Height    := 0;
                 end;
  end;

  // Alterado por Italo em 20/04/2011
  if (nTotalItens > (_NUM_ITEMS_PAGE1 + Fracionado)) then
  begin
    nRestItens := nTotalItens - (_NUM_ITEMS_PAGE1 + Fracionado);
    if nRestItens <= (_NUM_ITEMS_OTHERPAGES + Fracionado) then
      Inc(FTotalPages)
    else
    begin
      Inc(FTotalPages, nRestItens div (_NUM_ITEMS_OTHERPAGES + Fracionado));
      if (nRestItens mod (_NUM_ITEMS_OTHERPAGES + Fracionado)) > 0 then
        Inc(FTotalPages)
    end;
  end;

  QRCTe.ReportTitle:='CT-e: ' + FormatFloat( '000,000,000', FCTe.Ide.nCT );

  QRCTe.Page.TopMargin    := FMargemSuperior * 100;
  QRCTe.Page.BottomMargin := FMargemInferior * 100;
  QRCTe.Page.LeftMargin   := FMargemEsquerda * 100;
  QRCTe.Page.RightMargin  := FMargemDireita  * 100;

  // Incluido por Italo em 26/04/2011
//  qrbObs.Height              := 72;
//  qrbRecibo.Height           := 68;
//  qrbSistema.Height          := 16;
//  qrbDadosExcEmitente.Height := 40;
end;

procedure TfrmDACTeQRRetratoA5.qrb_01_ReciboBeforePrint(Sender: TQRCustomBand; var PrintBand: Boolean);
begin
  inherited;
  // Incluido/Alterado por Italo em 20/04/2011 / 27/04/2011
  PrintBand := (QRCTe.PageNumber = 1) and (FCTe.Ide.modal <> mdAereo) and (FPosRecibo = prCabecalho);

  qrlSerie2.Caption  := FormatFloat( '000', FCTe.Ide.serie);
  qrlNumCte2.Caption := FormatFloat( '000,000,000', FCTe.Ide.nCT );
  // Incluido por Italo em 27/04/2011
  // TpcteTipoCTe = (tcNormal, tcComplemento, tcAnulacao, tcSubstituto);
  qrb_01_Recibo.Enabled := (FCTe.Ide.tpCTe = tcNormal);
end;

procedure TfrmDACTeQRRetratoA5.qrb_01_Recibo_AereoBeforePrint(
  Sender: TQRCustomBand; var PrintBand: Boolean);
begin
  inherited;
  PrintBand := (QRCTe.PageNumber = 1) and (FCTe.Ide.modal = mdAereo);

  qrb_01_Recibo_Aereo.Enabled := (FCTe.Ide.tpCTe = tcNormal);
end;

procedure TfrmDACTeQRRetratoA5.qrb_02_CabecalhoBeforePrint(Sender: TQRCustomBand; var PrintBand: Boolean);
var
 strChaveContingencia: string;
begin
  inherited;
  // Comentado por Italo em 20/04/2011
//  PrintBand := QRCTe.PageNumber = 1;

  if Trim(FLogo) <> '' then
   begin
   qriLogo.Picture.LoadFromFile(FLogo);
   end;
  // Alterado por Italo em 17/05/2012
  if FExpandirLogoMarca then
   begin
    qriLogo.top:=2;
    qriLogo.Left:=2;
    qriLogo.Height:=142;
    qriLogo.Width:=330;
    qriLogo.Stretch:=true;
    qrmEmitente.Enabled:=False;
    qrmDadosEmitente.Enabled:=False;
   end;

  qrlModal.Caption := TpModalToStrText(FCTe.Ide.modal);
  qrlModelo.Caption := FCTe.Ide.modelo;
  qrlSerie.Caption := FormatFloat( '000', FCTe.Ide.serie);
  qrlNumCte.Caption := FormatFloat( '000,000,000', FCTe.Ide.nCT );
  qrlPageNumber.Caption := format('%2.2d', [QRCTe.PageNumber]) + '/' + format('%2.2d', [FTotalPages]);
  qrlEmissao.Caption := DFeUtil.FormatDateTime(DateTimeToStr(FCTe.Ide.dhEmi));
  SetBarCodeImage(Copy(FCTe.InfCTe.Id, 4, 44), qriBarCode);
  qrlChave.Caption := CTeUtil.FormatarChaveAcesso(Copy(FCTe.InfCTe.Id, 4, 44));

  // Incluido por Italo em 17/05/2012
  if not FExpandirLogoMarca then
   begin
    qrmEmitente.Enabled:=True;
    qrmDadosEmitente.Enabled:=True;
    // Emitente
    with FCTe.Emit do
    begin
      qrmEmitente.Lines.Text := XNome;

      qrmDadosEmitente.Lines.Clear;
      with EnderEmit do
      begin
//      qrmDadosEmitente.Lines.Add(XLgr + IfThen(Nro = '0', '', ', ' + Nro) + ' ' + XCpl + ' ' + XBairro);
        // Alterado por Italo em 10/03/2011
        qrmDadosEmitente.Lines.Add(XLgr + IfThen(Nro = '0', '', ', ' + Nro));
        if XCpl<>'' then qrmDadosEmitente.Lines.Add(XCpl);
        if XBairro<>'' then qrmDadosEmitente.Lines.Add(XBairro);
        qrmDadosEmitente.Lines.Add('CEP: ' + DFeUtil.FormatarCEP(FormatFloat( '00000000', CEP )) + ' - ' + XMun + ' - ' + UF);
      end;
//    qrmDadosEmitente.Lines.Add('CNPJ: ' + DFeUtil.FormatarCNPJ(CNPJ) + ' INSCRI��O ESTADUAL: ' + IE);
      // Alterado por Italo em 10/03/2011
      qrmDadosEmitente.Lines.Add('CNPJ: ' + DFeUtil.FormatarCNPJ(CNPJ));
      qrmDadosEmitente.Lines.Add('INSCRI��O ESTADUAL: ' + IE);
      qrmDadosEmitente.Lines.Add('TELEFONE: ' + DFeUtil.FormatarFone(EnderEmit.Fone));

      if Trim(FUrl) <> '' then
        qrmDadosEmitente.Lines.Add(FUrl);
    end;
   end;

  qrlTipoCte.Caption := tpCTToStrText(FCTe.Ide.tpCTe);
  qrlTipoServico.Caption := TpServToStrText(FCTe.Ide.tpServ);
  // Alterado por Italo em 30/12/2010
  if FCTe.Ide.Toma4.xNome = ''
   then qrlTomaServico.Caption := TpTomadorToStrText(FCTe.Ide.Toma03.Toma)
   else qrlTomaServico.Caption := TpTomadorToStrText(FCTe.Ide.Toma4.toma);
  qrlFormaPagamento.Caption := tpforPagToStrText(FCTe.Ide.forPag);

  // As Linhas abaixo foram inseridas por Italo em 31/08/2010
  // Normal **************************************************************
  if FCTe.Ide.tpEmis in [teNormal, teSCAN] then
   begin
    // Incluidas por Italo em 01/01/2012
    qrlVariavel1.Enabled := True;
    qriBarCode2.Enabled  := False;
    if FCTe.procCTe.cStat = 100
     then qrlDescricao.Caption := 'PROTOCOLO DE AUTORIZA��O DE USO';

    if FCTe.procCTe.cStat = 101
     then qrlDescricao.Caption := 'PROTOCOLO DE HOMOLOGA��O DE CANCELAMENTO';

    // Alterado de 102 para 110 por Italo em 27/01/2012
    if FCTe.procCTe.cStat = 110
     then qrlDescricao.Caption := 'PROTOCOLO DE DENEGA��O DE USO';

    if FProtocoloCTE <> ''
     then qrlProtocolo.Caption := FProtocoloCTE
     else qrlProtocolo.Caption := FCTe.procCTe.nProt + '   ' +
                                     DFeUtil.SeSenao(FCTe.procCTe.dhRecbto <> 0,
                                      DateTimeToStr(FCTe.procCTe.dhRecbto), '');
   end;

  // As Linhas abaixo foram inseridas por Italo em 28/01/2011
  // Contingencia ********************************************************

  if FCTe.Ide.tpEmis in [teContingencia, teFSDA] then
   begin
    // Incluido por Italo em 20/04/2012
    if FCTe.procCTe.cStat in [100, 101, 110] 
     then begin
      qrlVariavel1.Enabled := True;
      qriBarCode2.Enabled  := False;
      if FCTe.procCTe.cStat = 100
       then qrlDescricao.Caption := 'PROTOCOLO DE AUTORIZA��O DE USO';

      if FCTe.procCTe.cStat = 101
       then qrlDescricao.Caption := 'PROTOCOLO DE HOMOLOGA��O DE CANCELAMENTO';

      if FCTe.procCTe.cStat = 110
       then qrlDescricao.Caption := 'PROTOCOLO DE DENEGA��O DE USO';

      if FProtocoloCTE <> ''
       then qrlProtocolo.Caption := FProtocoloCTE
       else qrlProtocolo.Caption := FCTe.procCTe.nProt + '   ' +
                                    DFeUtil.SeSenao(FCTe.procCTe.dhRecbto <> 0,
                                        DateTimeToStr(FCTe.procCTe.dhRecbto), '');
     end
     else begin
      // Incluidas por Italo em 01/01/2012
      qrlVariavel1.Enabled := False;
      qriBarCode2.Enabled  := True;

      strChaveContingencia := CTeUtil.GerarChaveContingencia(FCTe);
      SetBarCodeImage(strChaveContingencia, qriBarCode2);
      qrlDescricao.Caption := 'DADOS DO CT-E';
      qrlProtocolo.Caption := CTeUtil.FormatarChaveContingencia(strChaveContingencia);
     end;
   end;
   
  // DPEC ****************************************************************
  if FCTe.Ide.tpEmis = teDPEC then
   begin
    qrlDescricao.Caption := 'N�MERO DE REGISTRO DPEC';
    qrlProtocolo.Caption := FProtocoloCTE;
   end;

  qrlInscSuframa.Caption := FCTe.Dest.ISUF;
end;

procedure TfrmDACTeQRRetratoA5.qrb_03_DadosDACTeBeforePrint(
  Sender: TQRCustomBand; var PrintBand: Boolean);
var
 i: Integer;
begin
  inherited;
  // Incluido por Italo em 20/04/2011
  PrintBand := QRCTe.PageNumber = 1;

  qrlNatOperacao.Caption := FormatFloat('0000', FCTe.Ide.CFOP) + ' - ' + FCTe.Ide.natOp;
  qrlOrigPrestacao.Caption := FCTe.Ide.xMunIni + ' - ' + FCTe.Ide.UFIni + ' - ' + FormatFloat('000', FCTe.Ide.cMunIni);
  qrlDestPrestacao.Caption := FCTe.Ide.xMunFim + ' - ' + FCTe.Ide.UFFim + ' - ' + FormatFloat('000', FCTe.Ide.cMunFim);

  //DADOS REMETENTE
  qrlRazaoRemet.Caption := FCTe.Rem.xNome;
  qrlEnderecoRemet1.Caption := FCTe.Rem.EnderReme.xLgr + ', ' + FCTe.Rem.EnderReme.nro;
  qrlEnderecoRemet2.Caption := FCTe.Rem.EnderReme.xCpl + ' - ' + FCTe.Rem.EnderReme.xBairro;
  qrlCEPRemet.Caption := DFeUtil.FormatarCEP(FormatFloat( '00000000', FCTe.Rem.EnderReme.CEP ));
  qrlMunRemet.Caption := FCTe.Rem.EnderReme.xMun+' - '+FCTe.Rem.EnderReme.UF;
  qrlCnpjRemet.Caption := DFeUtil.FormatarCNPJ(FCTe.Rem.CNPJCPF);
  qrlPaisRemet.Caption := FCTe.Rem.EnderReme.xPais;
  qrlInscEstRemet.Caption := FCTe.Rem.IE;
  qrlFoneRemet.Caption := DFeUtil.FormatarFone(FCTe.Rem.fone);

  //DADOS DESTINATARIO
  qrlRazaoDest.Caption := FCTe.Dest.xNome;
  qrlEnderecoDest1.Caption := FCTe.Dest.EnderDest.xLgr + ', ' + FCTe.Dest.EnderDest.nro;
  qrlEnderecoDest2.Caption := FCTe.Dest.EnderDest.xCpl + ' - ' + FCTe.Dest.EnderDest.xBairro;
  qrlCEPDest.Caption := DFeUtil.FormatarCEP(FormatFloat( '00000000', FCTe.Dest.EnderDest.CEP));
  qrlMunDest.Caption := FCTe.Dest.EnderDest.xMun+' - '+FCTe.Dest.EnderDest.UF;
  qrlCnpjDest.Caption := DFeUtil.FormatarCNPJ(FCTe.Dest.CNPJCPF);
  qrlPaisDest.Caption := FCTe.Dest.EnderDest.xPais;
  qrlInscEstDest.Caption := FCTe.Dest.IE;
  qrlFoneDest.Caption := DFeUtil.FormatarFone(FCTe.Dest.fone);

  (*
  //DADOS EXPEDIDOR
  qrlRazaoExped.Caption := FCTe.Exped.xNome;
  qrlEnderecoExped1.Caption := FCTe.Exped.EnderExped.xLgr + ', ' + FCTe.Exped.EnderExped.nro;
  qrlEnderecoExped2.Caption := FCTe.Exped.EnderExped.xCpl + ' - ' + FCTe.Exped.EnderExped.xBairro;
  qrlCEPExped.Caption := DFeUtil.FormatarCEP(FormatFloat( '00000000', FCTe.Exped.EnderExped.CEP));
  qrlMunExped.Caption := FCTe.Exped.EnderExped.xMun+' - '+FCTe.Exped.EnderExped.UF;
  qrlCnpjExped.Caption := DFeUtil.FormatarCNPJ(FCTe.Exped.CNPJCPF);
  qrlPaisExped.Caption := FCTe.Exped.EnderExped.xPais;
  qrlInscEstExped.Caption := FCTe.Exped.IE;
  qrlFoneExped.Caption := DFeUtil.FormatarFone(FCTe.Exped.fone);

  //DADOS RECEBEDOR
  qrlRazaoReceb.Caption := FCTe.Receb.xNome;
  qrlEnderecoReceb1.Caption := FCTe.Receb.EnderReceb.xLgr + ', ' + FCTe.Receb.EnderReceb.nro;
  qrlEnderecoReceb2.Caption := FCTe.Receb.EnderReceb.xCpl + ' - ' + FCTe.Receb.EnderReceb.xBairro;
  qrlCEPReceb.Caption := DFeUtil.FormatarCEP(FormatFloat( '00000000', FCTe.Receb.EnderReceb.CEP));
  qrlMunReceb.Caption := FCTe.Receb.EnderReceb.xMun+' - '+FCTe.Receb.EnderReceb.UF;
  qrlCnpjReceb.Caption := DFeUtil.FormatarCNPJ(FCTe.Receb.CNPJCPF);
  qrlPaisReceb.Caption := FCTe.Receb.EnderReceb.xPais;
  qrlInscEstReceb.Caption := FCTe.Receb.IE;
  qrlFoneReceb.Caption := DFeUtil.FormatarFone(FCTe.Receb.fone);

  if FCTe.Ide.Toma4.xNome = ''
   then begin
    case FCTe.Ide.Toma03.Toma of
    tmRemetente:
      begin
        qrlRazaoToma.Caption := FCTe.Rem.xNome;
        qrlEnderecoToma.Caption := FCTe.Rem.EnderReme.xLgr + ', ' + FCTe.Rem.EnderReme.nro + ' - ' + FCTe.Rem.EnderReme.xCpl + ' - ' + FCTe.Rem.EnderReme.xBairro;
        qrlCEPToma.Caption := DFeUtil.FormatarCEP(FormatFloat( '00000000', FCTe.Rem.EnderReme.CEP));
        qrlMunToma.Caption := FCTe.Rem.EnderReme.xMun+' - '+FCTe.Rem.EnderReme.UF;
        qrlCnpjToma.Caption := DFeUtil.FormatarCNPJ(FCTe.Rem.CNPJCPF);
        qrlPaisToma.Caption := FCTe.Rem.EnderReme.xPais;
        qrlInscEstToma.Caption := FCTe.Rem.IE;
        qrlFoneToma.Caption := DFeUtil.FormatarFone(FCTe.Rem.fone);
      end;
    tmExpedidor:
      begin
        qrlRazaoToma.Caption := FCTe.Exped.xNome;
        qrlEnderecoToma.Caption := FCTe.Exped.EnderExped.xLgr + ', ' + FCTe.Exped.EnderExped.nro + ' - ' + FCTe.Exped.EnderExped.xCpl + ' - ' + FCTe.Exped.EnderExped.xBairro;
        qrlCEPToma.Caption := DFeUtil.FormatarCEP(FormatFloat( '00000000', FCTe.Exped.EnderExped.CEP));
        qrlMunToma.Caption := FCTe.Exped.EnderExped.xMun+' - '+FCTe.Exped.EnderExped.UF;
        qrlCnpjToma.Caption := DFeUtil.FormatarCNPJ(FCTe.Exped.CNPJCPF);
        qrlPaisToma.Caption := FCTe.Exped.EnderExped.xPais;
        qrlInscEstToma.Caption := FCTe.Exped.IE;
        qrlFoneToma.Caption := DFeUtil.FormatarFone(FCTe.Exped.fone);
      end;
    tmRecebedor:
      begin
        qrlRazaoToma.Caption := FCTe.Receb.xNome;
        qrlEnderecoToma.Caption := FCTe.Receb.EnderReceb.xLgr + ', ' + FCTe.Receb.EnderReceb.nro + ' - ' + FCTe.Receb.EnderReceb.xCpl + ' - ' + FCTe.Receb.EnderReceb.xBairro;
        qrlCEPToma.Caption := DFeUtil.FormatarCEP(FormatFloat( '00000000', FCTe.Receb.EnderReceb.CEP));
        qrlMunToma.Caption := FCTe.Receb.EnderReceb.xMun+' - '+FCTe.Receb.EnderReceb.UF;
        qrlCnpjToma.Caption := DFeUtil.FormatarCNPJ(FCTe.Receb.CNPJCPF);
        qrlPaisToma.Caption := FCTe.Receb.EnderReceb.xPais;
        qrlInscEstToma.Caption := FCTe.Receb.IE;
        qrlFoneToma.Caption := DFeUtil.FormatarFone(FCTe.Receb.fone);
      end;
    tmDestinatario:
      begin
        qrlRazaoToma.Caption := FCTe.Dest.xNome;
        qrlEnderecoToma.Caption := FCTe.Dest.EnderDest.xLgr + ', ' + FCTe.Dest.EnderDest.nro + ' - ' + FCTe.Dest.EnderDest.xCpl + ' - ' + FCTe.Dest.EnderDest.xBairro;
        qrlCEPToma.Caption := DFeUtil.FormatarCEP(FormatFloat( '00000000', FCTe.Dest.EnderDest.CEP));
        qrlMunToma.Caption := FCTe.Dest.EnderDest.xMun+' - '+FCTe.Dest.EnderDest.UF;
        qrlCnpjToma.Caption := DFeUtil.FormatarCNPJ(FCTe.Dest.CNPJCPF);
        qrlPaisToma.Caption := FCTe.Dest.EnderDest.xPais;
        qrlInscEstToma.Caption := FCTe.Dest.IE;
        qrlFoneToma.Caption := DFeUtil.FormatarFone(FCTe.Dest.fone);
      end;
    end;
   end
   else begin
    qrlRazaoToma.Caption := FCTe.Ide.Toma4.xNome;
    qrlEnderecoToma.Caption := FCTe.Ide.Toma4.EnderToma.xLgr + ', ' + FCTe.Ide.Toma4.EnderToma.nro + ' - ' + FCTe.Ide.Toma4.EnderToma.xCpl + ' - ' + FCTe.Ide.Toma4.EnderToma.xBairro;
    qrlCEPToma.Caption := DFeUtil.FormatarCEP(FormatFloat( '00000000', FCTe.Ide.Toma4.EnderToma.CEP));
    qrlMunToma.Caption := FCTe.Ide.Toma4.EnderToma.xMun+' - '+FCTe.Ide.Toma4.EnderToma.UF;
    qrlCnpjToma.Caption := DFeUtil.FormatarCNPJ(FCTe.Ide.Toma4.CNPJCPF);
    qrlPaisToma.Caption := FCTe.Ide.Toma4.EnderToma.xPais;
    qrlInscEstToma.Caption := FCTe.Ide.Toma4.IE;
    qrlFoneToma.Caption := DFeUtil.FormatarFone(FCTe.Ide.Toma4.fone);
   end;
  *)
  qrlProdPredominante.Caption := FCTe.InfCarga.proPred;
  qrlOutrasCaracCarga.Caption := FCTe.InfCarga.xOutCat;
{$IFDEF PL_103}
  qrlVlrTotalMerc.Caption := CTeUtil.FormatarValor(msk15x2, FCTe.InfCarga.vMerc);
{$ENDIF}
{$IFDEF PL_104}
  qrlVlrTotalMerc.Caption := CTeUtil.FormatarValor(msk15x2, FCTe.InfCarga.vCarga);
{$ENDIF}

  {
  for i := 1 to 4 do
    TQRLabel(FindComponent('qrlQtdUndMedida' + intToStr(i))).Caption := '';

  for i := 0 to FCTe.InfCarga.InfQ.Count - 1 do
    TQRLabel(FindComponent('qrlQtdUndMedida' + intToStr(i + 1))).Caption :=
      CTeUtil.FormatarValor(msk6x3, FCTe.InfCarga.InfQ.Items[i].qCarga) + '/' +
      FCTe.InfCarga.InfQ.Items[i].tpMed;
  }
  // Incluido por Italo em 28/02/2011
  qrmQtdUnidMedida1.Lines.Clear;
  qrmQtdUnidMedida2.Lines.Clear;
  qrmQtdUnidMedida3.Lines.Clear;
  qrmQtdUnidMedida4.Lines.Clear;
  // Incluido por Italo em 01/01/2012
  qrmQtdUnidMedida5.Lines.Clear;

  for i := 0 to FCTe.InfCarga.InfQ.Count - 1 do
   begin
    // Alterado por Italo em 17/05/2012
    //UnidMed = (uM3,uKG, uTON, uUNIDADE, uLITROS, uMMBTU);
    case FCTe.InfCarga.InfQ.Items[i].cUnid of
          uM3: qrmQtdUnidMedida4.Lines.Add(CTeUtil.FormatarValor(msk6x3,
                 FCTe.InfCarga.InfQ.Items[i].qCarga));
          uKg: begin
                if uppercase(trim(FCTe.InfCarga.InfQ.Items[i].tpMed))='PESO BRUTO'
                then qrmQtdUnidMedida1.Lines.Add(CTeUtil.FormatarValor(msk6x3,
                        FCTe.InfCarga.InfQ.Items[i].qCarga))
                else
                if uppercase(trim(FCTe.InfCarga.InfQ.Items[i].tpMed))='PESO BASE DE CALCULO'
                then qrmQtdUnidMedida2.Lines.Add(CTeUtil.FormatarValor(msk6x3,
                        FCTe.InfCarga.InfQ.Items[i].qCarga))
                else
                if uppercase(trim(FCTe.InfCarga.InfQ.Items[i].tpMed))='PESO BC'
                then qrmQtdUnidMedida2.Lines.Add(CTeUtil.FormatarValor(msk6x3,
                        FCTe.InfCarga.InfQ.Items[i].qCarga))
                else qrmQtdUnidMedida3.Lines.Add(CTeUtil.FormatarValor(msk6x3,
                        FCTe.InfCarga.InfQ.Items[i].qCarga));
               end;
         uTON: begin
                if uppercase(trim(FCTe.InfCarga.InfQ.Items[i].tpMed))='PESO BRUTO'
                then qrmQtdUnidMedida1.Lines.Add(CTeUtil.FormatarValor(msk6x3,
                        FCTe.InfCarga.InfQ.Items[i].qCarga))
                else
                if uppercase(trim(FCTe.InfCarga.InfQ.Items[i].tpMed))='PESO BASE DE CALCULO'
                then qrmQtdUnidMedida2.Lines.Add(CTeUtil.FormatarValor(msk6x3,
                        FCTe.InfCarga.InfQ.Items[i].qCarga))
                else
                if uppercase(trim(FCTe.InfCarga.InfQ.Items[i].tpMed))='PESO BC'
                then qrmQtdUnidMedida2.Lines.Add(CTeUtil.FormatarValor(msk6x3,
                        FCTe.InfCarga.InfQ.Items[i].qCarga))
                else qrmQtdUnidMedida3.Lines.Add(CTeUtil.FormatarValor(msk6x3,
                        FCTe.InfCarga.InfQ.Items[i].qCarga));
               end;
     uUNIDADE: qrmQtdUnidMedida5.Lines.Add(CTeUtil.FormatarValor(msk6x3,
                 FCTe.InfCarga.InfQ.Items[i].qCarga) + '/' + FCTe.InfCarga.InfQ.Items[i].tpMed);
     uLITROS:  qrmQtdUnidMedida5.Lines.Add(CTeUtil.FormatarValor(msk6x3,
                 FCTe.InfCarga.InfQ.Items[i].qCarga) + '/' + FCTe.InfCarga.InfQ.Items[i].tpMed);
     uMMBTU:   qrmQtdUnidMedida5.Lines.Add(CTeUtil.FormatarValor(msk6x3,
                 FCTe.InfCarga.InfQ.Items[i].qCarga) + '/' + FCTe.InfCarga.InfQ.Items[i].tpMed);
    end;
    (*
    case i of
     0,4,8: qrmQtdUnidMedida1.Lines.Add(CTeUtil.FormatarValor(msk6x3,
            FCTe.InfCarga.InfQ.Items[i].qCarga) + '/' + FCTe.InfCarga.InfQ.Items[i].tpMed);
     1,5,9: qrmQtdUnidMedida2.Lines.Add(CTeUtil.FormatarValor(msk6x3,
            FCTe.InfCarga.InfQ.Items[i].qCarga) + '/' + FCTe.InfCarga.InfQ.Items[i].tpMed);
     2,6,10: qrmQtdUnidMedida3.Lines.Add(CTeUtil.FormatarValor(msk6x3,
             FCTe.InfCarga.InfQ.Items[i].qCarga) + '/' + FCTe.InfCarga.InfQ.Items[i].tpMed);
     3,7,11: qrmQtdUnidMedida4.Lines.Add(CTeUtil.FormatarValor(msk6x3,
            FCTe.InfCarga.InfQ.Items[i].qCarga) + '/' + FCTe.InfCarga.InfQ.Items[i].tpMed);
    end;
    *)
   end;

  qrlNomeSeguradora.Caption := '';
  qrlRespSeguroMerc.Caption := '';
  qrlNroApolice.Caption := '';
  qrlNroAverbacao.Caption := '';

  if FCTe.InfSeg.Count > 0 then
  begin
    qrlNomeSeguradora.Caption := FCTe.InfSeg.Items[0].xSeg;
    qrlRespSeguroMerc.Caption := TpRspSeguroToStrText(FCTe.InfSeg.Items[0].respSeg);
    qrlNroApolice.Caption := FCTe.InfSeg.Items[0].nApol;
    qrlNroAverbacao.Caption := FCTe.InfSeg.Items[0].nAver;
  end;

  qrmCompNome1.Lines.Clear;
  qrmCompNome2.Lines.Clear;
  qrmCompNome3.Lines.Clear;
  qrmCompValor1.Lines.Clear;
  qrmCompValor2.Lines.Clear;
  qrmCompValor3.Lines.Clear;

  for i := 0 to FCTe.vPrest.comp.Count - 1 do
  begin
    case i of
      0,3,6,9:
        begin
          qrmCompNome1.Lines.Add(FCTe.vPrest.comp[i].xNome);
          qrmCompValor1.Lines.Add(CTeUtil.FormatarValor(msk10x2, FCTe.vPrest.comp[i].vComp));
        end;
      1,4,7,10:
        begin
          qrmCompNome2.Lines.Add(FCTe.vPrest.comp[i].xNome);
          qrmCompValor2.Lines.Add(CTeUtil.FormatarValor(msk10x2, FCTe.vPrest.comp[i].vComp));
        end;
      2,5,8,11:
        begin
          qrmCompNome3.Lines.Add(FCTe.vPrest.comp[i].xNome);
          qrmCompValor3.Lines.Add(CTeUtil.FormatarValor(msk10x2, FCTe.vPrest.comp[i].vComp));
        end;
    end;
  end;

  qrlVlrTotServico.Caption := CTeUtil.FormatarValor(msk13x2, FCTe.vPrest.vTPrest);
  qrlVlrTotReceber.Caption := CTeUtil.FormatarValor(msk13x2, FCTe.vPrest.vRec);

  qrlSitTrib.Caption := CSTICMSToStr(FCTe.Imp.ICMS.SituTrib)+'-'+
                        CSTICMSToStrTagPosText(FCTe.Imp.ICMS.SituTrib);

{$IFDEF PL_103}
  case FCTe.Imp.ICMS.SituTrib of
    cst00:
      begin
        qrlRedBaseCalc.Caption := '';
        qrlBaseCalc.Caption    := CTeUtil.FormatarValor(msk9x2, FCTe.Imp.ICMS.CST00.vBC);
//        qrlAliqICMS.Caption    := CTeUtil.FormatarValor(mskAliq, FCTe.Imp.ICMS.CST00.pICMS);
        qrlAliqICMS.Caption    := CTeUtil.FormatarValor(msk4x2, FCTe.Imp.ICMS.CST00.pICMS);
        qrlVlrICMS.Caption     := CTeUtil.FormatarValor(msk4x2, FCTe.Imp.ICMS.CST00.vICMS);
        qrlICMS_ST.Caption     := '';
      end;
    cst20:
      begin
        qrlRedBaseCalc.Caption := CTeUtil.FormatarValor(mskAliq, FCTe.Imp.ICMS.CST20.pRedBC);
        qrlBaseCalc.Caption    := CTeUtil.FormatarValor(msk9x2, FCTe.Imp.ICMS.CST20.vBC);
//        qrlAliqICMS.Caption    := CTeUtil.FormatarValor(mskAliq, FCTe.Imp.ICMS.CST20.pICMS);
        qrlAliqICMS.Caption    := CTeUtil.FormatarValor(msk4x2, FCTe.Imp.ICMS.CST20.pICMS);
        qrlVlrICMS.Caption     := CTeUtil.FormatarValor(msk4x2, FCTe.Imp.ICMS.CST20.vICMS);
        qrlICMS_ST.Caption     := '';
        // CTeUtil.FormatarValor(msk4x2, FCTe.Imp.ICMS.CST20.vICMS);
      end;
    cst40:
      begin
        qrlRedBaseCalc.Caption := '';
        qrlBaseCalc.Caption    := '';
        qrlAliqICMS.Caption    := '';
        qrlVlrICMS.Caption     := '';
        qrlICMS_ST.Caption     := '';
      end;
    cst41:
      begin
        qrlRedBaseCalc.Caption := '';
        qrlBaseCalc.Caption    := '';
        qrlAliqICMS.Caption    := '';
        qrlVlrICMS.Caption     := '';
        qrlICMS_ST.Caption     := '';
      end;
    cst45:
      begin
        qrlRedBaseCalc.Caption := '';
        qrlBaseCalc.Caption    := '';
        qrlAliqICMS.Caption    := '';
        qrlVlrICMS.Caption     := '';
        qrlICMS_ST.Caption     := '';
      end;
    cst51:
      begin
        qrlRedBaseCalc.Caption := '';
        qrlBaseCalc.Caption    := '';
        qrlAliqICMS.Caption    := '';
        qrlVlrICMS.Caption     := '';
        qrlICMS_ST.Caption     := '';
      end;
    cst80:
      begin
        qrlRedBaseCalc.Caption := '';
        qrlBaseCalc.Caption    := CTeUtil.FormatarValor(msk9x2, FCTe.Imp.ICMS.CST80.vBC);
//        qrlAliqICMS.Caption    := CTeUtil.FormatarValor(mskAliq, FCTe.Imp.ICMS.CST80.pICMS);
        qrlAliqICMS.Caption    := CTeUtil.FormatarValor(msk4x2, FCTe.Imp.ICMS.CST80.pICMS);
        qrlVlrICMS.Caption     := CTeUtil.FormatarValor(msk9x2, FCTe.Imp.ICMS.CST80.vICMS);
        qrlICMS_ST.Caption     := CTeUtil.FormatarValor(msk9x2, FCTe.Imp.ICMS.CST80.vCred);
      end;
    cst81:
      begin
        qrlRedBaseCalc.Caption := CTeUtil.FormatarValor(mskAliq, FCTe.Imp.ICMS.CST81.pRedBC);
        qrlBaseCalc.Caption    := CTeUtil.FormatarValor(msk9x2, FCTe.Imp.ICMS.CST81.vBC);
//        qrlAliqICMS.Caption    := CTeUtil.FormatarValor(mskAliq, FCTe.Imp.ICMS.CST81.pICMS);
        qrlAliqICMS.Caption    := CTeUtil.FormatarValor(msk4x2, FCTe.Imp.ICMS.CST81.pICMS);
        qrlVlrICMS.Caption     := CTeUtil.FormatarValor(msk9x2, FCTe.Imp.ICMS.CST81.vICMS);
        qrlICMS_ST.Caption     := '';
        // CTeUtil.FormatarValor(msk9x2, FCTe.Imp.ICMS.CST81.vICMS);
      end;
    cst90:
      begin
        qrlRedBaseCalc.Caption := CTeUtil.FormatarValor(mskAliq, FCTe.Imp.ICMS.CST90.pRedBC);
        qrlBaseCalc.Caption    := CTeUtil.FormatarValor(msk9x2, FCTe.Imp.ICMS.CST90.vBC);
//        qrlAliqICMS.Caption    := CTeUtil.FormatarValor(mskAliq, FCTe.Imp.ICMS.CST90.pICMS);
        qrlAliqICMS.Caption    := CTeUtil.FormatarValor(msk4x2, FCTe.Imp.ICMS.CST90.pICMS);
        qrlVlrICMS.Caption     := CTeUtil.FormatarValor(msk9x2, FCTe.Imp.ICMS.CST90.vICMS);
        qrlICMS_ST.Caption     := CTeUtil.FormatarValor(msk4x2, FCTe.Imp.ICMS.CST90.vCred);
      end;
  end;
{$ENDIF}
{$IFDEF PL_104}
  case FCTe.Imp.ICMS.SituTrib of
    cst00:
      begin
        qrlRedBaseCalc.Caption := '';
        qrlBaseCalc.Caption    := CTeUtil.FormatarValor(msk9x2, FCTe.Imp.ICMS.ICMS00.vBC);
        qrlAliqICMS.Caption    := CTeUtil.FormatarValor(msk4x2, FCTe.Imp.ICMS.ICMS00.pICMS);
        qrlVlrICMS.Caption     := CTeUtil.FormatarValor(msk4x2, FCTe.Imp.ICMS.ICMS00.vICMS);
        qrlICMS_ST.Caption     := '';
      end;
    cst20:
      begin
        qrlRedBaseCalc.Caption := CTeUtil.FormatarValor(mskAliq, FCTe.Imp.ICMS.ICMS20.pRedBC);
        qrlBaseCalc.Caption    := CTeUtil.FormatarValor(msk9x2, FCTe.Imp.ICMS.ICMS20.vBC);
        qrlAliqICMS.Caption    := CTeUtil.FormatarValor(msk4x2, FCTe.Imp.ICMS.ICMS20.pICMS);
        qrlVlrICMS.Caption     := CTeUtil.FormatarValor(msk4x2, FCTe.Imp.ICMS.ICMS20.vICMS);
        qrlICMS_ST.Caption     := '';
      end;
    cst40:
      begin
        qrlRedBaseCalc.Caption := '';
        qrlBaseCalc.Caption    := '';
        qrlAliqICMS.Caption    := '';
        qrlVlrICMS.Caption     := '';
        qrlICMS_ST.Caption     := '';
      end;
    cst41:
      begin
        qrlRedBaseCalc.Caption := '';
        qrlBaseCalc.Caption    := '';
        qrlAliqICMS.Caption    := '';
        qrlVlrICMS.Caption     := '';
        qrlICMS_ST.Caption     := '';
      end;
    cst45:
      begin
        qrlRedBaseCalc.Caption := '';
        qrlBaseCalc.Caption    := '';
        qrlAliqICMS.Caption    := '';
        qrlVlrICMS.Caption     := '';
        qrlICMS_ST.Caption     := '';
      end;
    cst51:
      begin
        qrlRedBaseCalc.Caption := '';
        qrlBaseCalc.Caption    := '';
        qrlAliqICMS.Caption    := '';
        qrlVlrICMS.Caption     := '';
        qrlICMS_ST.Caption     := '';
      end;
    cst60:
      begin
        qrlRedBaseCalc.Caption := '';
        qrlBaseCalc.Caption    := CTeUtil.FormatarValor(msk9x2, FCTe.Imp.ICMS.ICMS60.vBCSTRet);
        qrlAliqICMS.Caption    := CTeUtil.FormatarValor(msk4x2, FCTe.Imp.ICMS.ICMS60.pICMSSTRet);
        qrlVlrICMS.Caption     := CTeUtil.FormatarValor(msk9x2, FCTe.Imp.ICMS.ICMS60.vICMSSTRet);
        qrlICMS_ST.Caption     := CTeUtil.FormatarValor(msk9x2, FCTe.Imp.ICMS.ICMS60.vCred);
      end;
    cst90:
      begin
        qrlRedBaseCalc.Caption := CTeUtil.FormatarValor(mskAliq, FCTe.Imp.ICMS.ICMS90.pRedBC);
        qrlBaseCalc.Caption    := CTeUtil.FormatarValor(msk9x2, FCTe.Imp.ICMS.ICMS90.vBC);
        qrlAliqICMS.Caption    := CTeUtil.FormatarValor(msk4x2, FCTe.Imp.ICMS.ICMS90.pICMS);
        qrlVlrICMS.Caption     := CTeUtil.FormatarValor(msk9x2, FCTe.Imp.ICMS.ICMS90.vICMS);
        qrlICMS_ST.Caption     := CTeUtil.FormatarValor(msk4x2, FCTe.Imp.ICMS.ICMS90.vCred);
      end;
    // Incluido por Italo em 05/12/2011 (contribui��o de Doni Dephi)  
    cstICMSOutraUF:
      begin
        qrlRedBaseCalc.Caption := CTeUtil.FormatarValor(mskAliq, FCTe.Imp.ICMS.ICMSOutraUF.pRedBCOutraUF);
        qrlBaseCalc.Caption    := CTeUtil.FormatarValor(msk9x2, FCTe.Imp.ICMS.ICMSOutraUF.vBCOutraUF);
        qrlAliqICMS.Caption    := CTeUtil.FormatarValor(msk4x2, FCTe.Imp.ICMS.ICMSOutraUF.pICMSOutraUF);
        qrlVlrICMS.Caption     := CTeUtil.FormatarValor(msk9x2, FCTe.Imp.ICMS.ICMSOutraUF.vICMSOutraUF);
        qrlICMS_ST.Caption     := '';
      end;
    cstICMSSN:
      begin
        qrlRedBaseCalc.Caption := '';
        qrlBaseCalc.Caption    := '';
        qrlAliqICMS.Caption    := '';
        qrlVlrICMS.Caption     := '';
        qrlICMS_ST.Caption     := '';
      end;
  end;
{$ENDIF}
end;

procedure TfrmDACTeQRRetratoA5.qrb_04_DadosNotaFiscalBeforePrint(
  Sender: TQRCustomBand; var PrintBand: Boolean);
begin
  inherited;
  // Incluido por Italo em 20/04/2011
  PrintBand := QRCTe.PageNumber = 1;

  // Imprime os dados da da Nota Fiscal se o Tipo de CTe for Normal
  // Incluido / Alterado por Italo e Doni em 24/09/2010
  qrb_04_DadosNotaFiscal.Enabled:=(FCTe.Ide.tpCTe = tcNormal);
end;

procedure TfrmDACTeQRRetratoA5.qrb_05_ComplementoBeforePrint(
  Sender: TQRCustomBand; var PrintBand: Boolean);
var
 i: Integer;
begin
  inherited;
  // Incluido por Italo em 20/04/2011
  PrintBand := QRCTe.PageNumber = 1;

  // Imprime a lista dos CT-e Complementados se o Tipo de CTe for Complemento
  // Incluido / Alterado por Italo e Doni em 24/09/2010
  qrmComplChave1.Lines.Clear;
  qrmComplValor1.Lines.Clear;
  qrmComplChave2.Lines.Clear;
  qrmComplValor2.Lines.Clear;
  qrb_05_Complemento.Enabled:=(FCTe.Ide.tpCTe = tcComplemento);

  for i := 0 to FCTe.InfCTeComp.Count - 1 do
  begin
    case i of
      0..4:
        begin
          qrmComplChave1.Lines.Add(FCTe.InfCTeComp[i].Chave);
          qrmComplValor1.Lines.Add(CTeUtil.FormatarValor(msk10x2, FCTe.InfCTeComp[i].vPresComp.vTPrest));
        end;
      5..9:
        begin
          qrmComplChave2.Lines.Add(FCTe.InfCTeComp[i].Chave);
          qrmComplValor2.Lines.Add(CTeUtil.FormatarValor(msk10x2, FCTe.InfCTeComp[i].vPresComp.vTPrest));
        end;
    end;
  end;
end;

procedure TfrmDACTeQRRetratoA5.qrb_06_ValorPrestacaoBeforePrint(
  Sender: TQRCustomBand; var PrintBand: Boolean);
begin
  inherited;
  // Incluido por Italo em 20/04/2011
  PrintBand := QRCTe.PageNumber = 1;
end;

procedure TfrmDACTeQRRetratoA5.qrb_07_HeaderItensBeforePrint(
  Sender: TQRCustomBand; var PrintBand: Boolean);
begin
  inherited;
  // Imprime os Documentos Origin�rios se o Tipo de CTe for Normal
  // Incluido / Alterado por Italo e Doni em 24/09/2010
  // qrbHeaderItens.Enabled:=(FCTe.Ide.tpCTe = tcNormal);
end;

procedure TfrmDACTeQRRetratoA5.qrb_08_ItensBeforePrint(Sender: TQRCustomBand; var PrintBand: Boolean);
var
  i : integer;
begin
  inherited;

  // Imprime os Documentos Origin�rios se o Tipo de CTe for Normal
  // Incluido / Alterado por Italo e Doni em 24/09/2010
  // TpcteTipoCTe = (tcNormal, tcComplemento, tcAnulacao, tcSubstituto);
  qrb_08_Itens.Enabled:=(FCTe.Ide.tpCTe = tcNormal);

  for i := 1 to 2 do
    if Trim(cdsDocumentos.FieldByName('DOCUMENTO_' + IntToStr(i)).AsString) = '' then
      TQRDBText(FindComponent('qrdbtCnpjEmitente' + intToStr(i))).Width := 325
    else
      TQRDBText(FindComponent('qrdbtCnpjEmitente' + intToStr(i))).Width := 128;

  Inc(nItemControle);

  if QRCTe.PageNumber = 1 then
    if QRCTe.RecordCount < (_NUM_ITEMS_PAGE1 + Fracionado) then
      qrsFimItens.Enabled := (nItemControle = QRCTe.RecordCount)
    else
      qrsFimItens.Enabled := (nItemControle = (_NUM_ITEMS_PAGE1 + Fracionado))
  else
  begin
    qrsFimItens.Enabled := (nItemControle = (_NUM_ITEMS_OTHERPAGES + Fracionado)) or
      (QRCTe.RecordNumber = QRCTe.RecordCount) or
      (cdsDocumentos.Eof);
  end;

  if qrsFimItens.Enabled then
    nItemControle := 0;
end;

procedure TfrmDACTeQRRetratoA5.qrb_09_ObsBeforePrint(
  Sender: TQRCustomBand; var PrintBand: Boolean);
var
 i: integer;
begin
  inherited;
  PrintBand := QRCTe.PageNumber = 1;

  qrb_11_ModRodLot104.Enabled := False;

  with FCTe.Rodo do
  begin
    qrlRntrcEmpresa.Caption := RNTRC;

{$IFDEF PL_103}
    qrsCIOT.Enabled := False;
    lblCIOT.Enabled := False;
    qrlCIOT.Enabled := False;
{$ENDIF}
{$IFDEF PL_104}
    qrsCIOT.Enabled := True;
    lblCIOT.Enabled := True;
    qrlCIOT.Enabled := True;
    qrlCIOT.Caption := CIOT;
{$ENDIF}

    case Lota of
      ltNao: begin
              qrlLotacao.Caption          := 'N�O';
             end;
      ltsim: begin
              qrlLotacao.Caption          := 'SIM';
              qrb_11_ModRodLot104.Enabled := True;
             end;
    end;

    qrlDtPrevEntrega.Caption := FormatDateTime('DD/MM/YYYY', dPrev);
  end;

  qrmObs.Lines.BeginUpdate; // Linha inserida por Italo em 31/08/2010
  qrmObs.Lines.Clear;

  qrmObs.Lines.Add(StringReplace( FCTe.Compl.xObs, '&lt;BR&gt;', #13#10, [rfReplaceAll,rfIgnoreCase] ) );

  for i := 0 to FCTe.Compl.ObsCont.Count-1 do
   with FCTe.Compl.ObsCont.Items[i] do
    begin
     qrmObs.Lines.Add( StringReplace( xCampo, '&lt;BR&gt;', #13#10, [rfReplaceAll,rfIgnoreCase] )+': '+
                       StringReplace( xTexto, '&lt;BR&gt;', #13#10, [rfReplaceAll,rfIgnoreCase] ) );
    end;

  if FCTe.Ide.tpEmis in [teContingencia, teFSDA]
   then begin
    if not (FCTe.procCTe.cStat in [100, 101, 110])
     then qrmObs.Lines.Add('DACTE em Conting�ncia - Impresso em decorr�ncia de problemas t�cnicos.');
   end;

  if FCTe.Ide.tpEmis = teDPEC
   then qrmObs.Lines.Add('DACTE em Conting�ncia - DPEC regularmente recebida pela Receita Federal do Brasil');

  qrmObs.Lines.Text:=StringReplace(qrmObs.Lines.Text,';',#13,[rfReplaceAll]);
  qrmObs.Lines.EndUpdate;

  qrmObsFisco.Lines.BeginUpdate;
  qrmObsFisco.Lines.Clear;

  for i := 0 to FCTe.Compl.ObsFisco.Count-1 do
   with FCTe.Compl.ObsFisco.Items[i] do
    begin
     qrmObsFisco.Lines.Add( StringReplace( xCampo, '&lt;BR&gt;', #13#10, [rfReplaceAll,rfIgnoreCase] )+': '+
                            StringReplace( xTexto, '&lt;BR&gt;', #13#10, [rfReplaceAll,rfIgnoreCase] ) );
    end;

  qrmObsFisco.Lines.Text := StringReplace( qrmObsFisco.Lines.Text, ';', #13, [rfReplaceAll] );
  qrmObsFisco.Lines.EndUpdate;

  qrlMsgTeste.Visible := False;
  qrlMsgTeste.Enabled := False;

  if FCTe.Ide.tpAmb = taHomologacao then
   begin
    qrlMsgTeste.Caption := 'AMBIENTE DE HOMOLOGA��O - SEM VALOR FISCAL';
    qrlMsgTeste.Visible := True;
    qrlMsgTeste.Enabled := True;
   end else
   begin
    if FCTe.procCTe.cStat > 0 then
     begin
      if FCTe.procCTe.cStat = 101 then
       begin
        qrlMsgTeste.Caption := 'CT-e CANCELADO';
        qrlMsgTeste.Visible := True;
        qrlMsgTeste.Enabled := True;
       end;

      if FCTe.procCTe.cStat = 110 then
       begin
        qrlMsgTeste.Caption := 'CT-e DENEGADO';
        qrlMsgTeste.Visible := True;
        qrlMsgTeste.Enabled := True;
       end;

      if not FCTe.procCTe.cStat in [101, 110, 100] then
       begin
        qrlMsgTeste.Caption := FCTe.procCTe.xMotivo;
        qrlMsgTeste.Visible := True;
        qrlMsgTeste.Enabled := True;
       end;
     end else
     begin
      qrlMsgTeste.Caption := 'CT-E N�O ENVIADO PARA SEFAZ';
      qrlMsgTeste.Visible := True;
      qrlMsgTeste.Enabled := True;
     end;
   end;

  qrlMsgTeste.Repaint;
end;

procedure TfrmDACTeQRRetratoA5.qrb_11_ModRodLot104BeforePrint(
  Sender: TQRCustomBand; var PrintBand: Boolean);
var
 i: Integer;
begin
  inherited;

  // Incluido por Italo em 18/10/2011
  PrintBand := QRCTe.PageNumber = 1;

  qrmTipo2.Lines.Clear;
  qrmPlaca2.Lines.Clear;
  qrmUF2.Lines.Clear;
  qrmRNTRC2.Lines.Clear;

  for i:= 0 to FCTe.Rodo.veic.Count - 1 do
  begin
   if TpPropriedadeToStr(FCTe.Rodo.veic.Items[i].tpProp) = 'P'
    then qrmTipo2.Lines.Add('Pr�prio')
    else qrmTipo2.Lines.Add('Terceiro');
   qrmPlaca2.Lines.Add(FCTe.Rodo.veic.Items[i].placa);
   qrmUF2.Lines.Add(FCTe.Rodo.veic.Items[i].UF);
   qrmRNTRC2.Lines.Add(FCTe.Rodo.veic.Items[i].Prop.RNTRC);
  end;

  qrmCNPJForn.Lines.Clear;
  qrmNumCompra.Lines.Clear;
  qrmCNPJPg.Lines.Clear;

{$IFDEF PL_104}
  for i := 0 to FCTe.Rodo.valePed.Count -1 do
  begin
   qrmCNPJForn.Lines.Add(DFeUtil.FormatarCNPJ(FCTe.Rodo.valePed.Items[i].CNPJForn));
   qrmNumCompra.Lines.Add(FCTe.Rodo.valePed.Items[i].nCompra);
   qrmCNPJPg.Lines.Add(DFeUtil.FormatarCNPJ(FCTe.Rodo.valePed.Items[i].CNPJPg));
  end;
{$ENDIF}

  qrlNomeMotorista2.Caption := '';
  qrlCPFMotorista2.Caption  := '';
  qrlLacres2.Caption        := '';
  if FCTe.Rodo.moto.Count>0
   then begin
    qrlNomeMotorista2.Caption := FCTe.Rodo.moto.Items[0].xNome;
    qrlCPFMotorista2.Caption  := DFeUtil.FormatarCNPJ(FCTe.Rodo.moto.Items[0].CPF);
   end;

  for i := 0 to FCTe.Rodo.Lacres.Count - 1 do
  begin
   qrlLacres2.Caption := qrlLacres2.Caption + FCTe.Rodo.Lacres.Items[i].nLacre + '/';
  end;
end;

procedure TfrmDACTeQRRetratoA5.qrb_12_ModAereoBeforePrint(
  Sender: TQRCustomBand; var PrintBand: Boolean);
begin
  inherited;
  // Incluido por Italo em 06/05/2011
  PrintBand := QRCTe.PageNumber = 1;
  {*
  ** Alterado por: Brian Collo Gon�alves e Eric Helfenstens
  ** Data: 17/03/2011
  ** Descri��o: Incluida banda para os dados especificos do Modal A�reo
  ** e carregamento de suas respectivas informa��es.
  *}
  qrb_12_ModAereo.Enabled := (FCTe.Ide.tpCTe = tcNormal) and (FCTe.Ide.modal = mdAereo);

  qrlCaracAdServico.Caption    := FCTe.Compl.xCaracSer;
  qrlCaracAdTransporte.Caption := FCTe.Compl.xCaracAd;

  with FCTe.Aereo do
  begin
    qrlAWB.Caption           := nOCA;
  {$IFDEF PL_103}
    qrlTrecho.Caption        := tarifa.trecho;
    qrlContaCorrente.Caption := cIATA; // ??? Conta Corrente ???
  {$ENDIF}
    qrlTarifaCL.Caption      := tarifa.CL;
    qrlTarifaCodigo.Caption  := tarifa.cTar;
    qrlTarifaValor.Caption   := FormatCurr('###,###,##0.00', tarifa.vTar);
  {$IFDEF PL_104}
    qrlContaCorrente.Caption := IdT; // ??? Conta Corrente ???
  {$ENDIF}
    qrlMinuta.Caption        := FormatFloat('0000000000', nMinu);

    qrlLojaAgenteEmissor.Caption := xLAgEmi;
  end;

  if FCte.Ide.retira = rtSim
   then qrlRetira.Caption := 'SIM'
   else qrlRetira.Caption := 'N�O';
  qrlDadosRetira.Caption  := FCte.Ide.xdetretira;
end;

procedure TfrmDACTeQRRetratoA5.qrb_13_ModAquaviarioBeforePrint(
  Sender: TQRCustomBand; var PrintBand: Boolean);
begin
  inherited;
  // Incluido por Italo em 06/05/2011
  PrintBand := QRCTe.PageNumber = 1;
  qrb_13_ModAquaviario.Enabled := (FCTe.Ide.tpCTe = tcNormal) and (FCTe.Ide.modal = mdAquaviario);
end;

procedure TfrmDACTeQRRetratoA5.qrb_14_ModFerroviarioBeforePrint(
  Sender: TQRCustomBand; var PrintBand: Boolean);
var
 i: Integer;
begin
  inherited;
  // Incluido por Italo em 06/05/2011
  PrintBand := QRCTe.PageNumber = 1;
  qrb_14_ModFerroviario.Enabled := (FCTe.Ide.tpCTe = tcNormal) and (FCTe.Ide.modal = mdFerroviario);

  qrlPortoEmbarque.Caption     := FCTe.Aquav.prtEmb;
  qrlPortoDestino.Caption      := FCTe.Aquav.prtDest;
  qrlIndNavioRebocador.Caption := FCTe.Aquav.xNavio;

  qrlBCAFRMM.Caption    := FormatCurr('###,###,##0.00', FCTe.Aquav.vPrest);
  qrlValorAFRMM.Caption := FormatCurr('###,###,##0.00', FCTe.Aquav.vAFRMM);

  case FCTe.Aquav.tpNav of
   tnInterior:  qrlTipoNav.Caption := 'INTERIOR';
   tnCabotagem: qrlTipoNav.Caption := 'CABOTAGEM';
  end;

  case FCTe.Aquav.direc of
   drNorte: qrlDirecao.Caption := 'NORTE';
   drLeste: qrlDirecao.Caption := 'LESTE';
   drSul:   qrlDirecao.Caption := 'SUL';
   drOeste: qrlDirecao.Caption := 'OESTE';
  end;

  qrlIndConteiners.Caption := '';
  for i := 0 to FCTe.Aquav.Lacre.Count - 1 do
   begin
    if i = 0
     then qrlIndConteiners.Caption := FCTe.Aquav.Lacre.Items[i].nLacre
     else qrlIndConteiners.Caption := qrlIndConteiners.Caption + '/' + FCTe.Aquav.Lacre.Items[i].nLacre;
   end;
end;

procedure TfrmDACTeQRRetratoA5.qrb_15_ModDutoviarioBeforePrint(
  Sender: TQRCustomBand; var PrintBand: Boolean);
begin
  inherited;
  // Incluido por Italo em 06/05/2011
  PrintBand := QRCTe.PageNumber = 1;
  qrb_15_ModDutoviario.Enabled := (FCTe.Ide.tpCTe = tcNormal) and (FCTe.Ide.modal = mdDutoviario);
end;

procedure TfrmDACTeQRRetratoA5.qrb_17_SistemaBeforePrint(Sender: TQRCustomBand;
  var PrintBand: Boolean);
begin
  inherited;
  // Incluido por Italo em 20/04/2011
  PrintBand := QRCTe.PageNumber = 1;

  qrlblSistema.Caption := FSistema + ' - ' + FUsuario;
end;

procedure TfrmDACTeQRRetratoA5.qrb_18_ReciboBeforePrint(
  Sender: TQRCustomBand; var PrintBand: Boolean);
begin
  inherited;
  // Incluido Novamente por Italo em 10/11/2011
  // Incluido/Alterado por Italo em 20/04/2011 / 27/04/2011 / 04/07/2011
  PrintBand := (QRCTe.PageNumber = 1);

  qrlSerie3.Caption  := FormatFloat( '000', FCTe.Ide.serie);
  qrlNumCte3.Caption := FormatFloat( '000,000,000', FCTe.Ide.nCT );

  // Incluido/Aterado por Italo em 27/04/2011 / 04/07/2011 / 10/05/2012
  // TpcteTipoCTe = (tcNormal, tcComplemento, tcAnulacao, tcSubstituto);
  if PrintBand
   then begin
    qrb_18_Recibo.Enabled := (FCTe.Ide.tpCTe = tcNormal) and (FCTe.Ide.modal <> mdAereo) and (FPosRecibo = prRodape);
    if qrb_18_Recibo.Enabled
     then qrb_18_Recibo.Height  := 68
     else qrb_18_Recibo.Height  := 0;
   end;
end;

end.


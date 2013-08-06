{******************************************************************************}
{ Projeto: ACBr Monitor                                                        }
{  Executavel multiplataforma que faz usocdo conjunto de componentes ACBr para }
{ criar uma interface de comunicação com equipamentos de automacao comercial.  }

{ Direitos Autorais Reservados (c) 2010 Daniel Simões de Almeida               }

{ Colaboradores nesse arquivo:     2005 Fábio Rogério Baía                     }

{  Você pode obter a última versão desse arquivo na página do Projeto ACBr     }
{ Componentes localizado em      http://www.sourceforge.net/projects/acbr      }

{  Este programa é software livre; você pode redistribuí-lo e/ou modificá-lo   }
{ sob os termos da Licença Pública Geral GNU, conforme publicada pela Free     }
{ Software Foundation; tanto a versão 2 da Licença como (a seu critério)       }
{ qualquer versão mais nova.                                                   }

{  Este programa é distribuído na expectativa de ser útil, mas SEM NENHUMA     }
{ GARANTIA; nem mesmo a garantia implícita de COMERCIALIZAÇÃO OU DE ADEQUAÇÃO A}
{ QUALQUER PROPÓSITO EM PARTICULAR. Consulte a Licença Pública Geral GNU para  }
{ obter mais detalhes. (Arquivo LICENCA.TXT ou LICENSE.TXT)                    }

{  Você deve ter recebido uma cópia da Licença Pública Geral GNU junto com este}
{ programa; se não, escreva para a Free Software Foundation, Inc., 59 Temple   }
{ Place, Suite 330, Boston, MA 02111-1307, USA. Você também pode obter uma     }
{ copia da licença em:  http://www.opensource.org/licenses/gpl-license.php     }

{ Daniel Simões de Almeida  -  daniel@djsystem.com.br  -  www.djsystem.com.br  }
{       Rua Coronel Aureliano de Camargo, 973 - Tatuí - SP - 18270-170         }

{******************************************************************************}

{$mode objfpc}{$H+}

unit ACBrMonitor1;

interface

uses
  SysUtils, Classes, Forms,
  CmdUnit, ACBrECF, ACBrDIS, ACBrGAV, ACBrDevice, ACBrCHQ, ACBrLCB, ACBrRFD, { Unit do ACBr }
  Dialogs, ExtCtrls, Menus, Buttons, StdCtrls, ComCtrls, Controls, Graphics,
  Spin, MaskEdit, EditBtn, ACBrBAL, ACBrETQ, ACBrSocket, ACBrCEP, ACBrIBGE,
  blcksock, ACBrValidador, ACBrGIF, ACBrBoleto, ACBrBoletoFCFortesFr, ACBrEAD;

const
  {$I versao.txt}
  CEstados: array[TACBrECFEstado] of string =
    ('Não Inicializada', 'Desconhecido', 'Livre', 'Venda',
    'Pagamento', 'Relatório', 'Bloqueada', 'Requer Z', 'Requer X',
    'Não Fiscal');
  CBufferMemoResposta = 10000;              { Maximo de Linhas no MemoResposta }
  _C = 'tYk*5W@';

type

  { TFrmACBrMonitor }

  TFrmACBrMonitor = class(TForm)
    ACBrBoleto1: TACBrBoleto;
    ACBrBoletoFCFortes1 : TACBrBoletoFCFortes ;
    ACBrCEP1 : TACBrCEP ;
    ACBrEAD1: TACBrEAD;
    ACBrECF1: TACBrECF;
    ACBrGIF1 : TACBrGIF ;
    ACBrIBGE1 : TACBrIBGE ;
    ACBrValidador1 : TACBrValidador ;
    ApplicationProperties1: TApplicationProperties;
    bBALAtivar: TBitBtn;
    bBALTestar: TBitBtn;
    bCEPTestar: TButton;
    bCHQTestar: TBitBtn;
    bDISAnimar: TBitBtn;
    bDISLimpar: TBitBtn;
    bDISTestar: TBitBtn;
    bECFAtivar: TBitBtn;
    bECFLeituraX: TBitBtn;
    bECFTestar: TBitBtn;
    Bevel1: TBevel;
    Bevel2: TBevel;
    Bevel3: TBevel;
    bExecECFTeste: TBitBtn;
    bGAVAbrir: TBitBtn;
    bGAVAtivar: TBitBtn;
    bGAVEstado: TBitBtn;
    bIBGETestar: TButton;
    bLCBAtivar: TBitBtn;
    bLCBSerial: TBitBtn;
    bRFDID: TButton;
    bRFDINI: TButton;
    bRFDINISalvar: TButton;
    bRFDMF: TBitBtn;
    bRSAeECFc: TButton;
    bRSALoadKey: TButton;
    bRSAPrivKey: TButton;
    bRSAPubKey: TButton;
    bTCAtivar: TBitBtn;
    cbBALModelo: TComboBox;
    cbBALPorta: TComboBox;
    cbCEPWebService: TComboBox;
    cbCHQModelo: TComboBox;
    cbCHQPorta: TComboBox;
    cbComandos: TCheckBox;
    cbDISModelo: TComboBox;
    cbDISPorta: TComboBox;
    cbECFModelo: TComboBox;
    cbECFPorta: TComboBox;
    cbETQModelo: TComboBox;
    cbETQPorta: TComboBox;
    cbGAVAcaoAberturaAntecipada: TComboBox;
    cbGAVModelo: TComboBox;
    cbGAVPorta: TComboBox;
    cbGAVStrAbre: TComboBox;
    cbLCBDispositivo: TComboBox;
    cbLCBPorta: TComboBox;
    cbLCBSufixo: TComboBox;
    cbLCBSufixoLeitor: TComboBox;
    cbLog: TCheckBox;
    cbRFDModelo: TComboBox;
    cbSenha: TCheckBox;
    cbxBOLBanco: TComboBox;
    cbxBOLEmissao: TComboBox;
    cbxBOLFiltro: TComboBox;
    cbxBOLF_J: TComboBox;
    cbxBOLLayout: TComboBox;
    cbxBOLUF: TComboBox;
    cbxCNAB: TComboBox;
    cbxTCModelo: TComboBox;
    chCHQVerForm: TCheckBox;
    chECFArredondaMFD: TCheckBox;
    chECFArredondaPorQtd: TCheckBox;
    chECFDescrGrande: TCheckBox;
    chECFSinalGavetaInvertido: TCheckBox;
    chECFIgnorarTagsFormatacao: TCheckBox;
    chLCBExcluirSufixo: TCheckBox;
    chRFD: TCheckBox;
    chRFDIgnoraMFD: TCheckBox;
    ckgBOLMostrar: TCheckGroup;
    deBOLDirArquivo: TDirectoryEdit;
    deBOLDirLogo: TDirectoryEdit;
    deBolDirRemessa: TDirectoryEdit;
    deBolDirRetorno: TDirectoryEdit;
    deUSUDataCadastro: TDateEdit;
    deRFDDataSwBasico: TDateEdit;
    edCEPChaveBuscarCEP: TEdit;
    edCEPTestar: TEdit;
    edCHQBemafiINI: TEdit;
    edCHQCidade: TEdit;
    edCHQFavorecido: TEdit;
    edCONProxyHost: TEdit;
    edCONProxyPass: TEdit;
    edCONProxyPort: TEdit;
    edCONProxyUser: TEdit;
    edECFLog: TEdit;
    edEntTXT: TEdit;
    edIBGECodNome: TEdit;
    edLCBPreExcluir: TEdit;
    edLogArq: TEdit;
    edPortaTCP: TEdit;
    edUSUCNPJ: TEdit;
    edRFDDir: TEdit;
    edUSUEndereco: TEdit;
    edUSUIE: TEdit;
    edUSURazaoSocial: TEdit;
    edSaiTXT: TEdit;
    edSenha: TEdit;
    edSH_Aplicativo: TEdit;
    edSH_CNPJ: TEdit;
    edSH_COO: TEdit;
    edSH_IE: TEdit;
    edSH_IM: TEdit;
    edSH_Linha1: TEdit;
    edSH_Linha2: TEdit;
    edSH_NumeroAP: TEdit;
    edSH_RazaoSocial: TEdit;
    edSH_VersaoAP: TEdit;
    edtBOLAgencia: TEdit;
    edtBOLBairro: TEdit;
    edtBOLCEP: TMaskEdit;
    edtBOLCidade: TEdit;
    edtBOLCNPJ: TMaskEdit;
    edtBOLComplemento: TEdit;
    edtBOLConta: TEdit;
    edtBOLDigitoAgencia: TEdit;
    edtBOLDigitoConta: TEdit;
    edtBOLLogradouro: TEdit;
    edtBOLNumero: TEdit;
    edtBOLRazaoSocial: TEdit;
    edtBOLSH: TEdit;
    edTCArqPrecos: TEdit;
    edTCNaoEncontrado: TEdit;
    edtCodCliente: TEdit;
    edtCodTransmissao: TEdit;
    edtConvenio: TEdit;
    edTCPort: TEdit;
    edTimeOutTCP: TEdit;
    edtModalidade: TEdit;
    gbCEP: TGroupBox;
    gbCEPProxy: TGroupBox;
    gbCEPTestar: TGroupBox;
    gbCHQDados: TGroupBox;
    gbLog: TGroupBox;
    gbRFDECF: TGroupBox;
    gbSenha: TGroupBox;
    gbTCP: TGroupBox;
    gbTXT: TGroupBox;
    GroupBox1: TGroupBox;
    GroupBox2: TGroupBox;
    Image1 : TImage ;
    Label1: TLabel;
    Label10: TLabel;
    Label11: TLabel;
    Label12: TLabel;
    Label13: TLabel;
    Label14: TLabel;
    Label15: TLabel;
    Label16: TLabel;
    Label17: TLabel;
    Label18: TLabel;
    Label19: TLabel;
    Label2: TLabel;
    Label20: TLabel;
    Label21: TLabel;
    Label22: TLabel;
    Label23: TLabel;
    Label24: TLabel;
    Label25: TLabel;
    Label26: TLabel;
    Label27: TLabel;
    Label28: TLabel;
    Label29: TLabel;
    Label3: TLabel;
    Label30: TLabel;
    Label31: TLabel;
    Label32: TLabel;
    Label4: TLabel;
    Label43: TLabel;
    Label44: TLabel;
    Label45: TLabel;
    Label46: TLabel;
    Label47: TLabel;
    Label48: TLabel;
    Label49: TLabel;
    Label5: TLabel;
    Label50: TLabel;
    Label51: TLabel;
    Label52: TLabel;
    Label53: TLabel;
    Label54: TLabel;
    Label55: TLabel;
    Label56: TLabel;
    Label57: TLabel;
    Label58: TLabel;
    Label59: TLabel;
    Label6: TLabel;
    Label60: TLabel;
    Label61: TLabel;
    Label62: TLabel;
    Label63: TLabel;
    Label64: TLabel;
    Label65: TLabel;
    Label66: TLabel;
    Label67: TLabel;
    Label68: TLabel;
    Label69: TLabel;
    Label7: TLabel;
    Label70: TLabel;
    Label71: TLabel;
    Label72: TLabel;
    Label73: TLabel;
    Label74: TLabel;
    Label75: TLabel;
    Label76: TLabel;
    Label77: TLabel;
    Label78: TLabel;
    Label79: TLabel;
    Label8: TLabel;
    Label80: TLabel;
    Label82: TLabel;
    Label83: TLabel;
    Label84: TLabel;
    Label85: TLabel;
    Label86: TLabel;
    Label87: TLabel;
    Label88: TLabel;
    Label89: TLabel;
    Label9: TLabel;
    Label90: TLabel;
    Label91: TLabel;
    Label92: TLabel;
    lAdSufixo: TLabel;
    lblBOLAgencia: TLabel;
    lblBOLBairro: TLabel;
    lblBOLBanco: TLabel;
    lblBOLCep: TLabel;
    lblBOLCidade: TLabel;
    lblBOLComplemento: TLabel;
    lblBOLConta: TLabel;
    lblBOLCPFCNPJ: TLabel;
    lblBOLDigAgencia: TLabel;
    lblBOLDigConta: TLabel;
    lblBOLDirLogo: TLabel;
    lblBOLEmissao: TLabel;
    lblBOLLogradouro: TLabel;
    lblBOLNomeRazao: TLabel;
    lblBOLNumero: TLabel;
    lblBOLPessoa: TLabel;
    lCEPCEP: TLabel;
    lCEPChave: TLabel;
    lCEPProxyPorta: TLabel;
    lCEPProxySenha: TLabel;
    lCEPProxyServidor: TLabel;
    lCEPProxyUsuario: TLabel;
    lCEPWebService: TLabel;
    lGAVEstado: TLabel;
    lIBGECodNome: TLabel;
    lLCBCodigoLido: TPanel;
    lNumPortaTCP: TLabel;
    lRFDID: TLabel;
    lRFDMarca: TLabel;
    lTimeOutTCP: TLabel;
    meUSUHoraCadastro: TMaskEdit;
    meRFDHoraSwBasico: TMaskEdit;
    mRFDINI: TMemo;
    mRSAKey: TMemo;
    mTCConexoes: TMemo;
    PageControl1: TPageControl;
    pgECFParams: TPageControl;
    pgCadastro: TPageControl;
    pgSwHouse: TPageControl;
    Panel4: TPanel;
    pConfig: TPanel;
    pgBoleto: TPageControl;
    pgConRFD: TPageControl;
    rbLCBFila: TRadioButton;
    rbLCBTeclado: TRadioButton;
    rbTCP: TRadioButton;
    rbTXT: TRadioButton;
    sbCHQBemafiINI: TSpeedButton;
    sbCHQSerial: TSpeedButton;
    sbDirRFD: TSpeedButton;
    sbECFLog: TSpeedButton;
    sbECFSerial: TSpeedButton;
    sbLog: TSpeedButton;
    sbTCArqPrecosEdit: TSpeedButton;
    sbTCArqPrecosFind: TSpeedButton;
    sedBALIntervalo: TSpinEdit;
    sedECFIntervalo: TSpinEdit;
    sedECFTimeout: TSpinEdit;
    sedGAVIntervaloAbertura: TSpinEdit;
    sedIntervalo: TSpinEdit;
    seDISIntByte: TSpinEdit;
    seDISIntervalo: TSpinEdit;
    seDISPassos: TSpinEdit;
    sedLCBIntervalo: TSpinEdit;
    sedLogLinhas: TSpinEdit;
    SelectDirectoryDialog1 : TSelectDirectoryDialog ;
    lblCep: TLabel;
    sbSobre : TSpeedButton ;
    sedECFMaxLinhasBuffer: TSpinEdit;
    sedECFPaginaCodigo: TSpinEdit;
    sedECFLinhasEntreCupons: TSpinEdit;
    seUSUCROCadastro: TSpinEdit;
    seUSUGTCadastro: TFloatSpinEdit;
    seUSUNumeroCadastro: TSpinEdit;
    shpLCB: TShape;
    shpTC: TShape;
    spBOLCopias: TSpinEdit;
    StatusBar1: TStatusBar;
    ImageList1: TImageList;
    ACBrCHQ1: TACBrCHQ;
    ACBrGAV1: TACBrGAV;
    ACBrDIS1: TACBrDIS;
    pmTray: TPopupMenu;
    Restaurar1: TMenuItem;
    Encerrar1: TMenuItem;
    Ocultar1: TMenuItem;
    N1: TMenuItem;
    pBotoes: TPanel;
    btMinimizar: TBitBtn;
    bConfig: TBitBtn;
    ACBrLCB1: TACBrLCB;
    pPrincipal: TPanel;
    pCmd: TPanel;
    mCmd: TMemo;
    pTopCmd: TPanel;
    pRespostas: TPanel;
    mResp: TMemo;
    pTopRespostas: TPanel;
    Splitter1: TSplitter;
    TabControl1: TTabControl;
    TabSheet1: TTabSheet;
    tsContaBancaria: TTabSheet;
    tsECFParamI: TTabSheet;
    tsECFParamII: TTabSheet;
    tsCadUsuario: TTabSheet;
    tsCadSwH: TTabSheet;
    tsCadSwDados: TTabSheet;
    tsCadSwChaveRSA: TTabSheet;
    tsCadastro: TTabSheet;
    TrayIcon1: TTrayIcon;
    bCancelar: TBitBtn;
    Timer1: TTimer;
    TcpServer: TACBrTCPServer;
    sbProcessando: TStatusBar;
    OpenDialog1: TOpenDialog;
    ACBrRFD1: TACBrRFD;
    ACBrBAL1: TACBrBAL;
    ACBrETQ1: TACBrETQ;
    TCPServerTC: TACBrTCPServer;
    TimerTC: TTimer;
    tsACBrBoleto: TTabSheet;
    tsBAL: TTabSheet;
    tsBoleto: TTabSheet;
    tsCedente: TTabSheet;
    tsCHQ: TTabSheet;
    tsConsultas: TTabSheet;
    tsDIS: TTabSheet;
    tsECF: TTabSheet;
    tsETQ: TTabSheet;
    tsGAV: TTabSheet;
    tsLCB: TTabSheet;
    tsMonitor: TTabSheet;
    tsRFD: TTabSheet;
    tsRFDConfig: TTabSheet;
    tsRFDINI: TTabSheet;
    tsTC: TTabSheet;
    procedure ACBrEAD1GetChavePrivada(var Chave: AnsiString);
    procedure ACBrGIF1Click(Sender : TObject) ;
    procedure ApplicationProperties1Exception(Sender: TObject; E: Exception);
    procedure ApplicationProperties1Minimize(Sender: TObject);
    procedure ApplicationProperties1Restore(Sender: TObject);
    procedure bCEPTestarClick(Sender : TObject) ;
    procedure bIBGETestarClick(Sender : TObject) ;
    procedure bRSAeECFcClick(Sender : TObject) ;
    procedure cbxBOLFiltroChange ( Sender: TObject ) ;
    procedure cbxBOLF_JChange ( Sender: TObject ) ;
    procedure cbCEPWebServiceChange(Sender : TObject) ;
    procedure chECFArredondaMFDClick(Sender: TObject);
    procedure chECFIgnorarTagsFormatacaoClick(Sender: TObject);
    procedure chRFDChange(Sender : TObject) ;
    procedure deBOLDirArquivoExit ( Sender: TObject ) ;
    procedure deBOLDirLogoExit ( Sender: TObject ) ;
    procedure deBolDirRemessaExit ( Sender: TObject ) ;
    procedure deBolDirRetornoExit ( Sender: TObject ) ;
    procedure deUSUDataCadastroExit(Sender : TObject) ;
    procedure deRFDDataSwBasicoExit(Sender : TObject) ;
    procedure FormClose(Sender: TObject; var CloseAction: TCloseAction);{%h-}
    procedure FormCreate(Sender: TObject);
    procedure ACBrECF1MsgAguarde(Mensagem: string);
    procedure ACBrECF1MsgPoucoPapel(Sender: TObject);
    procedure bConfigClick(Sender: TObject);
    procedure cbLogClick(Sender: TObject);
    procedure edOnlyNumbers(Sender: TObject; var Key: char);
    procedure bECFTestarClick(Sender: TObject);
    procedure bECFLeituraXClick(Sender: TObject);
    procedure bECFAtivarClick(Sender: TObject);
    procedure meUSUHoraCadastroExit(Sender : TObject) ;
    procedure meRFDHoraSwBasicoExit(Sender : TObject) ;
    procedure sbSobreClick(Sender : TObject) ;
    procedure sedECFLinhasEntreCuponsChange(Sender: TObject);
    procedure sedECFMaxLinhasBufferChange(Sender: TObject);
    procedure sedECFPaginaCodigoChange(Sender: TObject);
    procedure TcpServerConecta(const TCPBlockSocket: TTCPBlockSocket;
      var Enviar: ansistring);{%h-}
    procedure TcpServerDesConecta(const TCPBlockSocket: TTCPBlockSocket;
      Erro: integer; ErroDesc: string);{%h-}
    procedure TcpServerRecebeDados(const TCPBlockSocket: TTCPBlockSocket;
      const Recebido: ansistring; var Enviar: ansistring);{%h-}
    procedure TCPServerTCConecta(const TCPBlockSocket : TTCPBlockSocket ;
      var Enviar : AnsiString) ;{%H-}
    procedure TCPServerTCDesConecta(const TCPBlockSocket : TTCPBlockSocket ;
      Erro : Integer ; ErroDesc : String) ;{%H-}
    procedure TCPServerTCRecebeDados(const TCPBlockSocket : TTCPBlockSocket ;
      const Recebido : AnsiString ; var Enviar : AnsiString) ;
    procedure TrayIcon1Click(Sender: TObject);
    procedure tsACBrBoletoShow(Sender : TObject) ;
    procedure tsECFShow(Sender: TObject);
    procedure Ocultar1Click(Sender: TObject);
    procedure Restaurar1Click(Sender: TObject);
    procedure Encerrar1Click(Sender: TObject);
    procedure cbGAVModeloChange(Sender: TObject);
    procedure cbGAVPortaChange(Sender: TObject);
    procedure bGAVEstadoClick(Sender: TObject);
    procedure bGAVAbrirClick(Sender: TObject);
    procedure cbDISModeloChange(Sender: TObject);
    procedure cbDISPortaChange(Sender: TObject);
    procedure bDISLimparClick(Sender: TObject);
    procedure bDISTestarClick(Sender: TObject);
    procedure btMinimizarClick(Sender: TObject);
    procedure rbTCPTXTClick(Sender: TObject);
    procedure cbCHQModeloChange(Sender: TObject);
    procedure cbCHQPortaChange(Sender: TObject);
    procedure cbECFModeloChange(Sender: TObject);
    procedure cbECFPortaChange(Sender: TObject);
    procedure chECFArredondaPorQtdClick(Sender: TObject);
    procedure FormDestroy(Sender: TObject);
    procedure bCancelarClick(Sender: TObject);
    procedure FormCloseQuery(Sender: TObject; var CanClose: boolean);
    procedure DoACBrTimer(Sender: TObject);
    procedure chECFDescrGrandeClick(Sender: TObject);
    procedure bCHQTestarClick(Sender: TObject);
    procedure cbLCBPortaChange(Sender: TObject);
    procedure bLCBAtivarClick(Sender: TObject);
    procedure edLCBSufLeituraKeyPress(Sender: TObject; var Key: char);
    procedure chLCBExcluirSufixoClick(Sender: TObject);
    procedure edLCBPreExcluirChange(Sender: TObject);
    procedure ACBrLCB1LeCodigo(Sender: TObject);
    procedure AumentaTempoHint(Sender: TObject);
    procedure DiminuiTempoHint(Sender: TObject);
    procedure cbLCBSufixoLeitorChange(Sender: TObject);
    procedure FormShortCut(Key: integer; Shift: TShiftState;{%h-}
      var Handled: boolean);{%h-}
    procedure cbGAVStrAbreChange(Sender: TObject);
    procedure bExecECFTesteClick(Sender: TObject);
    procedure chECFSinalGavetaInvertidoClick(Sender: TObject);
    procedure sedLCBIntervaloChanged(Sender: TObject);
    procedure sedECFTimeoutChanged(Sender: TObject);
    procedure sedGAVIntervaloAberturaChanged(Sender: TObject);
    procedure bGAVAtivarClick(Sender: TObject);
    procedure tsGAVShow(Sender: TObject);
    procedure cbGAVAcaoAberturaAntecipadaChange(Sender: TObject);
    procedure edCHQFavorecidoChange(Sender: TObject);
    procedure edCHQCidadeChange(Sender: TObject);
    procedure sbCHQBemafiINIClick(Sender: TObject);
    procedure edCHQBemafiINIExit(Sender: TObject);
    procedure ACBrECF1AguardandoRespostaChange(Sender: TObject);
    procedure bLCBSerialClick(Sender: TObject);
    procedure rbLCBTecladoClick(Sender: TObject);
    procedure tsLCBShow(Sender: TObject);
    procedure sedECFIntervaloChanged(Sender: TObject);
    procedure seDISPassosChanged(Sender: TObject);
    procedure seDISIntervaloChanged(Sender: TObject);
    procedure seDISIntByteChanged(Sender: TObject);
    procedure bDISAnimarClick(Sender: TObject);
    procedure bRFDINILerClick(Sender: TObject);
    procedure bRFDINISalvarClick(Sender: TObject);
    procedure bRFDMFClick(Sender: TObject);
    procedure sbDirRFDClick(Sender: TObject);
    procedure edSH_AplicativoChange(Sender: TObject);
    procedure edSH_NumeroAPChange(Sender: TObject);
    procedure tsRFDShow(Sender: TObject);
    procedure bRSALoadKeyClick(Sender: TObject);
    procedure ACBrRFD1GetKeyRSA(var PrivateKey_RSA: string);
    procedure cbRFDModeloChange(Sender: TObject);
    procedure bRFDIDClick(Sender: TObject);
    procedure tsRFDINIShow(Sender: TObject);
    procedure seUSUGTCadastroKeyPress(Sender: TObject; var Key: char);
    procedure seUSUGTCadastroExit(Sender: TObject);
    procedure tsRFDRSAShow(Sender: TObject);
    procedure cbSenhaClick(Sender: TObject);
    procedure bRSAPrivKeyClick(Sender: TObject);
    procedure bRSAPubKeyClick(Sender: TObject);
    procedure edECFLogChange(Sender: TObject);
    procedure sbLogClick(Sender: TObject);
    procedure sbECFLogClick(Sender: TObject);
    procedure cbBALModeloChange(Sender: TObject);
    procedure cbBALPortaChange(Sender: TObject);
    procedure sedBALIntervaloChanged(Sender: TObject);
    procedure bBALAtivarClick(Sender: TObject);
    procedure bBALTestarClick(Sender: TObject);
    procedure sbECFSerialClick(Sender: TObject);
    procedure cbETQModeloChange(Sender: TObject);
    procedure cbETQPortaChange(Sender: TObject);
    procedure bTCAtivarClick(Sender: TObject);
    procedure tsTCShow(Sender: TObject);
    procedure cbxTCModeloChange(Sender: TObject);
    procedure sbTCArqPrecosEditClick(Sender: TObject);
    procedure sbTCArqPrecosFindClick(Sender: TObject);
    procedure TimerTCTimer(Sender: TObject);
    procedure sbCHQSerialClick(Sender: TObject);
  private
    ACBrMonitorINI: string;
    Inicio: boolean;
    ArqSaiTXT, ArqSaiTMP, ArqEntTXT, ArqLogTXT: string;
    fsCmd: TACBrCmd;
    fsProcessar : TStringList ;
    NewLines: ansistring;
    fsDisWorking: boolean;
    fsRFDIni: string;
    fsRFDLeuParams: boolean;
    fsHashSenha: integer;
    fsCNPJSWOK: boolean;
    TipoCMD: string;
    pCanClose: boolean;

    fsSLPrecos: TStringList;
    fsDTPrecos: integer;

    fsLinesLog: ansistring;

    procedure DesInicializar ;
    procedure Inicializar;
    procedure EscondeConfig;
    procedure ExibeConfig;
    procedure LerIni;
    procedure SalvarIni;
    procedure AjustaLinhasLog;

    procedure LerSW;
    function LerChaveSWH: ansistring;
    procedure SalvarSW;

    procedure Processar;
    procedure Resposta(Comando, Resposta: ansistring);

    procedure AddLinesLog;

    procedure SetDisWorking(const Value: boolean);
  public
    Conexao: TTCPBlockSocket;

    property DISWorking: boolean read fsDisWorking write SetDisWorking;

    procedure SalvarConfBoletos;

    procedure AvaliaEstadoTsECF;
    procedure AvaliaEstadoTsGAV;
    procedure AvaliaEstadoTsLCB;
    procedure AvaliaEstadoTsRFD;
    procedure AvaliaEstadoTsBAL;
    procedure AvaliaEstadoTsTC;
  end;

var
  FrmACBrMonitor: TFrmACBrMonitor;

implementation

uses IniFiles, TypInfo, LCLType, strutils,
  UtilUnit,
  DoECFUnit, DoGAVUnit, DoCHQUnit, DoDISUnit, DoLCBUnit, DoACBrUnit, DoBALUnit,
  DoBoletoUnit, DoCEPUnit, DoIBGEUnit,
  {$IFDEF MSWINDOWS} sndkey32, {$ENDIF}
  {$IFDEF LINUX} unix, baseunix, termio, {$ENDIF}
  ACBrECFNaoFiscal, ACBrUtil, ACBrConsts, Math, Sobre, DateUtils,
  ConfiguraSerial,
  DoECFBemafi32, DoECFObserver, DoETQUnit;

{$R *.lfm}

{-------------------------------- TFrmACBrMonitor -----------------------------}
procedure TFrmACBrMonitor.FormCreate(Sender: TObject);
var
  iECF: TACBrECFModelo;
  iCHQ: TACBrCHQModelo;
  iDIS: TACBrDISModelo;
  iBAL: TACBrBALModelo;
  iCEP: TACBrCEPWebService;
  IBanco: TACBrTipoCobranca;
  AppDir : String ;
  ILayout: TACBrBolLayOut;
begin
  {$IFDEF LINUX}
   FpUmask(0);
  {$ENDIF}

  mResp.Clear;
  mCmd.Clear;

  fsCmd       := TACBrCmd.Create;
  fsProcessar := TStringList.Create;

  Inicio     := True;
  ArqSaiTXT  := '';
  ArqSaiTMP  := '';
  ArqEntTXT  := '';
  ArqLogTXT  := '';
  Conexao    := nil;
  NewLines   := '';
  DISWorking := False;

  pCanClose      := False;
  fsRFDIni       := '';
  fsRFDLeuParams := False;
  fsCNPJSWOK     := False;

  TipoCMD := 'A'; {Tipo de Comando A - ACBr, B - Bematech, D - Daruma}

  { Definindo constantes de Verdadeiro para TrueBoolsStrs }
  SetLength(TrueBoolStrs, 5);
  TrueBoolStrs[0] := 'True';
  TrueBoolStrs[1] := 'T';
  TrueBoolStrs[2] := 'Verdadeiro';
  TrueBoolStrs[3] := 'V';
  TrueBoolStrs[4] := 'Ok';

  { Definindo constantes de Falso para FalseBoolStrs }
  SetLength(FalseBoolStrs, 3);
  FalseBoolStrs[0] := 'False';
  FalseBoolStrs[1] := 'F';
  FalseBoolStrs[2] := 'Falso';


  { Criando lista de Bancos disponiveis }
  cbxBOLBanco.Items.Clear;
  IBanco:= Low(TACBrTipoCobranca);
  while IBanco <= High(TACBrTipoCobranca) do
  begin
    cbxBOLBanco.Items.Add(GetEnumName(TypeInfo(TACBrTipoCobranca), Integer(IBanco)));
    Inc(IBanco);
  end;

  { Criando lista de Layouts de Boleto disponiveis }
  cbxBOLLayout.Items.Clear;
  ILayout:= Low(TACBrBolLayOut);
  while ILayout <= High(TACBrBolLayOut) do
  begin
    cbxBOLLayout.Items.Add(GetEnumName(TypeInfo(TACBrBolLayOut), Integer(ILayout)));
    Inc(ILayout);
  end;

  { Criando lista modelos de ECFs disponiveis }
  cbECFModelo.Items.Clear;
  cbECFModelo.Items.Add('Procurar');
  iECF := Low(TACBrECFModelo);
  while iECF <= High(TACBrECFModelo) do
  begin
    cbECFModelo.Items.Add(GetEnumName(TypeInfo(TACBrECFModelo), integer(iECF)));
    Inc(iECF);
  end;
  AvaliaEstadoTsECF;

  AvaliaEstadoTsGAV;

  AvaliaEstadoTsLCB;

  AvaliaEstadoTsTC;
  fsSLPrecos := TStringList.Create;
  fsSLPrecos.NameValueSeparator := '|';
  fsDTPrecos := 0;

  { Criando lista modelos de Impres.Cheque disponiveis }
  cbCHQModelo.Items.Clear;
  iCHQ := Low(TACBrCHQModelo);
  while iCHQ <= High(TACBrCHQModelo) do
  begin
    cbCHQModelo.Items.Add(GetEnumName(TypeInfo(TACBrCHQModelo), integer(iCHQ)));
    Inc(iCHQ);
  end;

  { Criando lista Displays disponiveis }
  cbDISModelo.Items.Clear;
  iDIS := Low(TACBrDISModelo);
  while iDIS <= High(TACBrDISModelo) do
  begin
    cbDISModelo.Items.Add(GetEnumName(TypeInfo(TACBrDISModelo), integer(iDIS)));
    Inc(iDIS);
  end;

  { Criando lista Balanças disponiveis }
  cbBALModelo.Items.Clear;
  iBAL := Low(TACBrBALModelo);
  while iBAL <= High(TACBrBALModelo) do
  begin
    cbBALModelo.Items.Add(GetEnumName(TypeInfo(TACBrBALModelo), integer(iBAL)));
    Inc(iBAL);
  end;

  { Criando lista modelos de ECFs disponiveis }
  cbCEPWebService.Items.Clear;
  iCEP := Low(TACBrCEPWebService);
  while iCEP <= High(TACBrCEPWebService) do
  begin
    cbCEPWebService.Items.Add(GetEnumName(TypeInfo(TACBrCEPWebService), integer(iCEP)));
    Inc(iCEP);
  end;

  TrayIcon1.Icon.Assign(Self.Icon);
  TrayIcon1.Hint := 'ACBrMonitor ' + Versao;
  TrayIcon1.BalloonTitle := TrayIcon1.Hint;
  TrayIcon1.BalloonHint := 'Projeto ACBr' + sLineBreak + 'http://acbr.sf.net';

  Caption := 'ACBrMonitor ' + Versao + ' - ACBr: ' + ACBR_VERSAO;
  PageControl1.ActivePageIndex := 0;

  {$IFDEF LINUX}
  rbLCBTeclado.Caption := 'Dispositivo';
  cbLCBSufixo.Hint := 'Use a Sinaxe:  #NNN' + sLineBreak +
    'Onde: NNN = Numero do caracter ASC em Decimal' + sLineBreak +
    '      a adicionar no final do código lido.' +
    sLineBreak + sLineBreak +
    'Para vários caracteres use a , (virgula) como separador';
  cbLCBSufixo.Items.Clear;
  cbLCBSufixo.Items.Add('#13 | Enter');
  cbLCBSufixo.Items.Add('#10 | LF');
  cbLCBSufixo.Items.Add('#13,#13 | 2 x Enter');
  cbLCBSufixo.Items.Add('#18 | PgUp');
  cbLCBSufixo.Items.Add('#09 | Tab');
  cbLCBSufixo.Items.Add('#24 | Down');
  {$ELSE}
  lAdSufixo.Caption := 'Adicionar Sufixo "SndKey32"';
  {$ENDIF}
  lAdSufixo.Hint := cbLCBSufixo.Hint;

  chRFD.Font.Style := chRFD.Font.Style + [fsBold];
  chRFD.Font.Color := clRed;

  deBOLDirLogo.Text:= ExtractFilePath(Application.ExeName)+'Logos'+PathDelim;

  pgBoleto.ActivePageIndex := 0;
  cbxBOLF_JChange(Self);
  cbxBOLFiltroChange(Self);

  pgCadastro.ActivePageIndex := 0;
  pgSwHouse.ActivePageIndex  := 0;

  Application.Title := Caption;

  AppDir := ExtractFilePath(Application.ExeName);

  if FileExists( AppDir + 'banner_acbrmonitor.gif' ) then
   begin
     ACBrGIF1.LoadFromFile( AppDir + 'banner_acbrmonitor.gif' );
     ACBrGIF1.Transparent := True;
     ACBrGIF1.Start;
   end
  else
     ACBrGIF1.Visible := False;

  Timer1.Enabled := True;
end;

procedure TFrmACBrMonitor.FormClose(Sender: TObject; var CloseAction: TCloseAction);
begin
  DesInicializar;

  Timer1.Enabled  := False;
  TimerTC.Enabled := False;

  TcpServer.OnDesConecta := nil;
  TCPServerTC.OnDesConecta := nil;
end;

procedure TFrmACBrMonitor.ApplicationProperties1Exception(Sender: TObject;
  E: Exception);
begin
  mResp.Lines.Add(E.Message);
  if cbLog.Checked then
    WriteToTXT(ArqLogTXT, 'Exception: ' + E.Message);

  StatusBar1.Panels[0].Text := 'Exception';
  //  MessageDlg( E.Message,mtError,[mbOk],0) ;
end;

procedure TFrmACBrMonitor.ACBrEAD1GetChavePrivada(var Chave: AnsiString);
begin
  Chave := LerChaveSWH;

  if Chave = '' then
  begin
     Chave := mRSAKey.Text ;
     if copy(Chave,1,5) <> '-----' then
        Chave := '' ;
  end ;

  if Chave = '' then
     raise Exception.Create( 'Chave RSA Privada não especificada.'+sLineBreak+
                             '- Selecione a aba "Chave RSA"'+sLineBreak+
                             '- Calcule sua Chave Privada'+sLineBreak+
                             '- Salve as configurações'+sLineBreak+
                             '- Distribua a sua Chave Privada com o arquivo '+sLineBreak+
                             '  criptografado "swh.ini"' ) ;
end;

procedure TFrmACBrMonitor.ACBrGIF1Click(Sender : TObject) ;
begin
  OpenURL('http://www.djsystem.com.br/acbr/sac/');
end;

procedure TFrmACBrMonitor.ApplicationProperties1Minimize(Sender: TObject);
begin
  if WindowState <> wsMinimized then
    Application.Minimize;

  Visible := False;
  Application.ShowMainForm := False;
end;

procedure TFrmACBrMonitor.ApplicationProperties1Restore(Sender: TObject);
begin
  Application.BringToFront;
end;

procedure TFrmACBrMonitor.bCEPTestarClick(Sender : TObject) ;
Var
  AMsg : String ;
  I : Integer ;
begin
  with ACBrCEP1 do
  begin
     WebService  := TACBrCEPWebService( cbCEPWebService.ItemIndex );
     ChaveAcesso := edCEPChaveBuscarCEP.Text;
     ProxyHost   := edCONProxyHost.Text;
     ProxyPort   := edCONProxyPort.Text;
     ProxyUser   := edCONProxyUser.Text;
     ProxyPass   := edCONProxyPass.Text;

     if BuscarPorCEP( edCEPTestar.Text ) > 0 then
     begin
       AMsg := IntToStr(Enderecos.Count) + ' Endereço(s) encontrado(s)' +
               sLineBreak + sLineBreak ;

       For I := 0 to Enderecos.Count-1 do
       begin
         with Enderecos[I] do
         begin
            AMsg := AMsg +
                    'CEP: '+CEP + sLineBreak +
                    'Logradouro: '+Tipo_Logradouro+ ' ' +Logradouro + sLineBreak +
                    'Complemento: '+Complemento + sLineBreak +
                    'Bairro: '+Bairro + sLineBreak +
                    'Municipio: '+Municipio + ' - IBGE: '+IBGE_Municipio + sLineBreak +
                    'UF: '+UF + ' - IBGE: '+ IBGE_UF + sLineBreak + sLineBreak ;
         end ;
       end ;
     end
     else
        AMsg := 'Nenhum Endereço encontrado' ;

     MessageDlg(AMsg,mtInformation,[mbOK],0);
  end ;
end;

procedure TFrmACBrMonitor.bIBGETestarClick(Sender : TObject) ;
Var
  AMsg : String ;
  I, Cod : Integer ;
begin
  with ACBrIBGE1 do
  begin
     ProxyHost  := edCONProxyHost.Text;
     ProxyPort  := edCONProxyPort.Text;
     ProxyUser  := edCONProxyUser.Text;
     ProxyPass  := edCONProxyPass.Text;

     Cod := StrToIntDef( edIBGECodNome.Text, 0 ) ;

     if Cod > 0 then
        I := BuscarPorCodigo( Cod )
     else
        I := BuscarPorNome( edIBGECodNome.Text );

     if I > 0 then
     begin
       AMsg := IntToStr(Cidades.Count) + ' Cidade(s) encontrada(s)' +
               sLineBreak + sLineBreak ;

       For I := 0 to Cidades.Count-1 do
       begin
         with Cidades[I] do
         begin
            AMsg := AMsg +
                    'Cod UF: '+IntToStr(CodUF) + sLineBreak +
                    'UF: '+UF + sLineBreak +
                    'Cod.Município: '+IntToStr(CodMunicio)  + sLineBreak +
                    'Município: '+Municipio  + sLineBreak +
                    'Área: '+FormatFloat('0.00', Area) + sLineBreak + sLineBreak;
         end ;
       end ;
     end
     else
        AMsg := 'Nenhuma Cidade encontrada' ;

     MessageDlg(AMsg,mtInformation,[mbOK],0);
  end ;
end;

procedure TFrmACBrMonitor.bRSAeECFcClick(Sender : TObject) ;
var
   NomeSH, ArqXML : String ;
begin
  NomeSH := edSH_RazaoSocial.Text ;
  if NomeSH = '' then
     NomeSH := 'Sua SoftwareHouse' ;

  if not InputQuery('Sw.House','Entre com o nome da Sw.House', NomeSH ) then
     exit ;

  if SelectDirectoryDialog1.Execute then
  begin
     ArqXML := PathWithDelim(SelectDirectoryDialog1.FileName) + NomeSH + '.xml' ;
     if FileExists( ArqXML )  then
        if MessageDlg( 'Arquivo já existe, sobrescrever ?',
                       mtConfirmation, mbYesNoCancel, 0) <> mrYes then
           exit ;

     if ACBrEAD1.GerarXMLeECFc( NomeSH, SelectDirectoryDialog1.FileName ) then
        MessageDlg('Arquivo: '+ArqXML+' criado', mtInformation, [mbOK], 0 );
  end ;
end;

procedure TFrmACBrMonitor.cbxBOLFiltroChange ( Sender: TObject ) ;
begin
   deBOLDirArquivo.Enabled := (cbxBOLFiltro.ItemIndex > 0) ;
end;

procedure TFrmACBrMonitor.cbxBOLF_JChange ( Sender: TObject ) ;
begin
   if cbxBOLF_J.ItemIndex = 0 then
    begin
      lblBOLCPFCNPJ.Caption := 'C.P.F';
      lblBOLNomeRazao.Caption := 'Nome';
      //edtBOLCNPJ.EditMask := '999.999.999-99;1';
    end
   else
    begin
      lblBOLCPFCNPJ.Caption := 'C.N.P.J';
      lblBOLNomeRazao.Caption := 'Razão Social';
     // edtBOLCNPJ.EditMask := '99.999.999/9999-99;1';
   end;
end;

procedure TFrmACBrMonitor.cbCEPWebServiceChange(Sender : TObject) ;
begin
  ACBrCEP1.WebService := TACBrCEPWebService( cbCEPWebService.ItemIndex ) ;
  edCEPChaveBuscarCEP.Enabled := (ACBrCEP1.WebService in [wsBuscarCep, wsCepLivre]) ;
end;

procedure TFrmACBrMonitor.chECFArredondaMFDClick(Sender: TObject);
begin
   ACBrECF1.ArredondaItemMFD := ((chECFArredondaMFD.Enabled) and
                                 (chECFArredondaMFD.Checked))
end;

procedure TFrmACBrMonitor.chECFIgnorarTagsFormatacaoClick(Sender: TObject);
begin
  ACBrECF1.IgnorarTagsFormatacao := chECFIgnorarTagsFormatacao.Checked;
end;

procedure TFrmACBrMonitor.chRFDChange(Sender : TObject) ;
begin
  ACBrECF1.Desativar;

  if chRFD.Checked then
    ACBrECF1.RFD := ACBrRFD1
  else
    ACBrECF1.RFD := nil;

  AvaliaEstadoTsRFD;
  AvaliaEstadoTsECF;
end;

procedure TFrmACBrMonitor.deBOLDirArquivoExit ( Sender: TObject ) ;
begin
   if trim(deBOLDirArquivo.Text) <> '' then
   begin
     if not DirectoryExists(deBOLDirArquivo.Text) then
     begin
       deBOLDirArquivo.SetFocus;
       raise Exception.Create('Diretorio destino do Arquivo não encontrado.');
     end;
   end;
end;

procedure TFrmACBrMonitor.deBOLDirLogoExit ( Sender: TObject ) ;
begin
   if trim(deBOLDirLogo.Text) <> '' then
   begin
     if not DirectoryExists(deBOLDirLogo.Text) then
     begin
       deBOLDirLogo.SetFocus;
       raise Exception.Create('Diretorio de Logos não encontrado.');
     end;
   end;
end;

procedure TFrmACBrMonitor.deBolDirRemessaExit ( Sender: TObject ) ;
begin
   if trim(deBolDirRemessa.Text) <> '' then
   begin
     if not DirectoryExists(deBolDirRemessa.Text) then
     begin
       deBolDirRemessa.SetFocus;
       raise Exception.Create('Diretorio de Arquivos Remessa não encontrado.');
     end;
   end;
end;

procedure TFrmACBrMonitor.deBolDirRetornoExit ( Sender: TObject ) ;
begin
   if trim(deBolDirRetorno.Text) <> '' then
   begin
     if not DirectoryExists(deBolDirRetorno.Text) then
     begin
       deBolDirRetorno.Clear;
       raise Exception.Create('Diretorio de Arquivos Retorno não encontrado.');
     end;
   end;
end;

procedure TFrmACBrMonitor.deUSUDataCadastroExit(Sender : TObject) ;
begin
   if (deUSUDataCadastro.Date = 0) then
   begin
      mResp.Lines.Add( 'Data Inválida' );
      deUSUDataCadastro.SetFocus;
   end ;
end;

procedure TFrmACBrMonitor.deRFDDataSwBasicoExit(Sender : TObject) ;
begin
   if (deRFDDataSwBasico.Date = 0) then
   begin
      mResp.Lines.Add( 'Data Inválida' );
      deRFDDataSwBasico.SetFocus;
   end ;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.FormDestroy(Sender: TObject);
begin
  fsCmd.Free;
  fsProcessar.Free;

  fsSLPrecos.Free;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.FormCloseQuery(Sender: TObject; var CanClose: boolean);
begin
  CanClose := True;

  if pConfig.Visible then
  begin
    MessageDlg('Por favor "Salve" ou "Cancele" as configurações ' +
      'efetuadas antes de fechar o programa',
      mtWarning, [mbOK], 0);
    CanClose := False;
    exit;
  end;

  CanClose := pCanClose or (WindowState = wsMinimized) ;

  if not CanClose then
    Application.Minimize;
end;


{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.Restaurar1Click(Sender: TObject);
begin
  pmTray.Close;
  if WindowState <> wsMaximized then
    WindowState := wsNormal;
  Visible := True;
  Application.ShowMainForm := True;
  Application.Restore;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.Ocultar1Click(Sender: TObject);
begin
  Application.Minimize;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.Encerrar1Click(Sender: TObject);
begin
  pCanClose := True;
  Close;
end;

{------------------------- Procedures de Uso Interno --------------------------}
procedure TFrmACBrMonitor.Inicializar;
var
  Ini: TIniFile;
  ArqIni: string;
  Txt: string;
  Erro: string;
  IpList : TStringList ;
  I : Integer ;
begin
  Timer1.Enabled := False;
  Inicio := False;
  Erro := '';
  ACBrMonitorINI := ExtractFilePath(Application.ExeName) + 'ACBrMonitor.ini';

  if not FileExists(ACBrMonitorINI) then //verifica se o arq. de config existe
  begin                                    //se nao existir vai para as configs
    MessageDlg('Bem vindo ao ACBrMonitor',
      'Bem vindo ao ACBrMonitor,' + sLineBreak + sLineBreak +
      'Por favor configure o ACBrMonitor, ' + sLineBreak +
      'informando o Método de Monitoramento, e a configuração ' + sLineBreak +
      'dos Equipamentos de Automação ligados a essa máquina.' +
      sLineBreak + sLineBreak +
      'IMPORTANTE: Após configurar o Método de Monitoramento' + sLineBreak +
      ' o ACBrMonitor deve ser reiniciado', mtInformation, [mbOK], 0);
    bConfig.Click;
    exit;
  end;

  while not Visible do
  begin
    Application.ProcessMessages;
    sleep(200);
  end;

  try
    LerIni;

    Application.Minimize;
  except
    on E: Exception do
      Erro := Erro + sLineBreak + E.Message;
  end;

  EscondeConfig;

  try
    AjustaLinhasLog;  { Diminui / Ajusta o numero de linhas do Log }
  except
    on E: Exception do
      Erro := Erro + sLineBreak + E.Message;
  end;

  try
    bConfig.Enabled := True;
    Timer1.Interval := sedIntervalo.Value;
    Timer1.Enabled := rbTXT.Checked;
    TcpServer.Ativo := rbTCP.Checked;

    mResp.Lines.Add('ACBr Monitor Ver.' + Versao);
    mResp.Lines.Add('Aguardando comandos ACBr');
  except
    on E: Exception do
      Erro := Erro + sLineBreak + E.Message;
  end;

  try
    if rbTCP.Checked then
    begin
      if TcpServer.Ativo then
      begin
        try
          Txt := 'Endereço Local: ['+TcpServer.TCPBlockSocket.LocalName + '],   IP: ';
          with TcpServer.TCPBlockSocket do
          begin
             IpList := TStringList.Create;
             try
                ResolveNameToIP( LocalName, IpList);
                For I := 0 to IpList.Count-1 do
                   if pos(':',IpList[I]) = 0 then
                      Txt := Txt + '   '+IpList[I] ;
             finally
                IpList.Free;
             end ;
          end ;
        except
        end;
        mResp.Lines.Add(Txt);
        mResp.Lines.Add('Porta: [' + TcpServer.Port + ']');
      end;
    end
    else
    begin
      mResp.Lines.Add('Monitorando Comandos TXT em: ' + ArqEntTXT);
      mResp.Lines.Add('Respostas gravadas em: ' + ArqSaiTXT);
    end;

    if cbLog.Checked then
      mResp.Lines.Add('Log de comandos será gravado em: ' + ArqLogTXT);

    { Se for NAO fiscal, desliga o AVISO antes de ativar }
    if ACBrECF1.Modelo = ecfNaoFiscal then
    begin
      ArqIni := (ACBrECF1.ECF as TACBrECFNaoFiscal).NomeArqINI;
      if FileExists(ArqIni) then
      begin
        Ini := TIniFile.Create(ArqIni);
        try
          Ini.WriteString('Variaveis', 'Aviso_Legal', 'NAO');
        finally
          Ini.Free;
        end;
      end;
    end;
  except
    on E: Exception do
      Erro := Erro + sLineBreak + E.Message;
  end;

  if Erro <> '' then
    raise Exception.Create(Erro);
end;

procedure TFrmACBrMonitor.DesInicializar ;
begin
  ACBrECF1.Desativar ;
  ACBrCHQ1.Desativar ;
  ACBrGAV1.Desativar ;
  ACBrDIS1.Desativar ;
  ACBrLCB1.Desativar ;
  ACBrBAL1.Desativar ;
  ACBrETQ1.Desativar ;
  TCPServer.Desativar ;
  TCPServerTC.Desativar ;
end ;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.AjustaLinhasLog;

  procedure AjustaLogFile(AFile: string; LinhasMax: integer);
  var
    LogNew, LogOld: TStringList;
    I: integer;
  begin
    if not FileExists(AFile) then
      exit;

    LogOld := TStringList.Create;
    try
      LogOld.LoadFromFile(AFile);
      if LogOld.Count > LinhasMax then
      begin
        mResp.Lines.Add('Ajustando o tamanho do arquivo: ' + AFile);
        mResp.Lines.Add('Numero de Linhas atual: ' + IntToStr(LogOld.Count));
        mResp.Lines.Add('Reduzindo para: ' + IntToStr(LinhasMax) + ' linhas');

        { Se for muito grande é mais rápido copiar para outra lista do que Deletar }
        if (LogOld.Count - LinhasMax) > LinhasMax then
        begin
          LogNew := TStringList.Create;
          try
            LogNew.Clear;

            for I := LinhasMax downto 1 do
              LogNew.Add(LogOld[LogOld.Count - I]);

            LogNew.SaveToFile(AFile);
          finally
            LogNew.Free;
          end;
        end
        else
        begin
          { Existe alguma maneira mais rápida de fazer isso ??? }
          LogOld.BeginUpdate;
          while LogOld.Count > LinhasMax do
            LogOld.Delete(0);
          LogOld.EndUpdate;
          LogOld.SaveToFile(AFile);
        end;
      end;
    finally
      LogOld.Free;
    end;
  end;
begin
  if (sedLogLinhas.Value <= 0) then
    exit;

  // Ajustado LOG do ACBrMonitor //
  if (cbLog.Checked) then
    AjustaLogFile(ArqLogTXT, sedLogLinhas.Value);

  // Ajustado LOG do ECF //
  if (edECFLog.Text <> '') then
    AjustaLogFile(edECFLog.Text, sedLogLinhas.Value);
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.LerIni;
var
  Ini: TIniFile;
  ECFAtivado, CHQAtivado, GAVAtivado, DISAtivado, BALAtivado, ETQAtivado: boolean;
  Senha, ECFDeviceParams, CHQDeviceParams: string;
begin
  Ini := TIniFile.Create(ACBrMonitorINI);

  ECFAtivado := ACBrECF1.Ativo;
  CHQAtivado := ACBrCHQ1.Ativo;
  GAVAtivado := ACBrGAV1.Ativo;
  DISAtivado := ACBrDIS1.Ativo;
  BALAtivado := ACBrBAL1.Ativo;
  ETQAtivado := ACBrETQ1.Ativo;

  try
    { Lendo Senha }
    //     Ini.ReadString('ACBrMonitor','TXT_Saida','SAI.TXT');
    fsHashSenha := StrToIntDef(LeINICrypt(INI, 'ACBrMonitor', 'HashSenha', _C), -1);

    if fsHashSenha < 1 then  { INI antigo não tinha essa chave }
    begin
      Senha := Ini.ReadString('ACBrMonitor', 'Senha', '');
      if Senha <> '' then
        fsHashSenha := StringCrc16(Senha);
    end;

    if fsHashSenha > 0 then
    begin
      cbSenha.Checked := True;
      edSenha.Text := 'NADAAQUI';
    end;

    { Parametros do Monitor }
    rbTCP.Checked := Ini.ReadBool('ACBrMonitor', 'Modo_TCP', False);
    rbTXT.Checked := Ini.ReadBool('ACBrMonitor', 'Modo_TXT', False);
    edPortaTCP.Text := IntToStr(Ini.ReadInteger('ACBrMonitor', 'TCP_Porta', 3434));
    edTimeOutTCP.Text := IntToStr(Ini.ReadInteger('ACBrMonitor', 'TCP_TimeOut', 10000));
    edEntTXT.Text := Ini.ReadString('ACBrMonitor', 'TXT_Entrada', 'ENT.TXT');
    edSaiTXT.Text := Ini.ReadString('ACBrMonitor', 'TXT_Saida', 'SAI.TXT');
    sedIntervalo.Value := Ini.ReadInteger('ACBrMonitor', 'Intervalo', 50);
    edLogArq.Text := Ini.ReadString('ACBrMonitor', 'Arquivo_Log', 'LOG.TXT');
    cbLog.Checked := Ini.ReadBool('ACBrMonitor', 'Gravar_Log', False) and
      (edLogArq.Text <> '');
    sedLogLinhas.Value := Ini.ReadInteger('ACBrMonitor', 'Linhas_Log', 0);
    cbComandos.Checked := Ini.ReadBool('ACBrMonitor', 'Comandos_Remotos', False);

    ArqEntTXT := AcertaPath(edEntTXT.Text);
    ArqSaiTXT := AcertaPath(edSaiTXT.Text);
    ArqSaiTMP := ChangeFileExt(ArqSaiTXT, '.tmp');
    ArqLogTXT := AcertaPath(edLogArq.Text);

    TcpServer.Port    := edPortaTCP.Text;
    TcpServer.TimeOut := StrToIntDef( edTimeOutTCP.Text, 10000);

    { Parametros do ECF }
    ECFDeviceParams := Ini.ReadString('ECF', 'SerialParams', '');
    cbECFModelo.ItemIndex := Ini.ReadInteger('ECF', 'Modelo', 0) + 1;
    cbECFModeloChange(Self);
    cbECFPorta.Text := Ini.ReadString('ECF', 'Porta', 'Procurar');
    sedECFTimeout.Value := Ini.ReadInteger('ECF', 'Timeout', 3);
    sedECFIntervalo.Value := Ini.ReadInteger('ECF', 'IntervaloAposComando', 100);
    sedECFMaxLinhasBuffer.Value := Ini.ReadInteger('ECF', 'MaxLinhasBuffer', 0);
    sedECFPaginaCodigo.Value := Ini.ReadInteger('ECF', 'PaginaCodigo', 0);
    sedECFLinhasEntreCupons.Value := Ini.ReadInteger('ECF', 'LinhasEntreCupons', 0);
    chECFArredondaPorQtd.Checked := Ini.ReadBool('ECF', 'ArredondamentoPorQtd', False);
    chECFArredondaMFD.Checked := Ini.ReadBool('ECF', 'ArredondamentoItemMFD', False);
    chECFDescrGrande.Checked := Ini.ReadBool('ECF', 'DescricaoGrande', True);
    chECFSinalGavetaInvertido.Checked := Ini.ReadBool('ECF', 'GavetaSinalInvertido', False);
    chECFIgnorarTagsFormatacao.Checked := Ini.ReadBool('ECF', 'IgnorarTagsFormatacao', False);
    edECFLog.Text := Ini.ReadString('ECF', 'ArqLog', '');

    { Parametros do CHQ }
    cbCHQModelo.ItemIndex := Ini.ReadInteger('CHQ', 'Modelo', 0);
    cbCHQModeloChange(Self);
    cbCHQPorta.Text := Ini.ReadString('CHQ', 'Porta', '');
    chCHQVerForm.Checked := Ini.ReadBool('CHQ', 'VerificaFormulario', False);
    edCHQFavorecido.Text := Ini.ReadString('CHQ', 'Favorecido', '');
    edCHQCidade.Text := Ini.ReadString('CHQ', 'Cidade', '');
    edCHQBemafiINI.Text := Ini.ReadString('CHQ', 'PathBemafiINI', '');
    CHQDeviceParams := Ini.ReadString('CHQ', 'SerialParams', '');

    { Parametros do GAV }
    cbGAVModelo.ItemIndex := Ini.ReadInteger('GAV', 'Modelo', 0);
    cbGAVModeloChange(Self);
    cbGAVPorta.Text := Ini.ReadString('GAV', 'Porta', '');
    cbGAVStrAbre.Text := Ini.ReadString('GAV', 'StringAbertura', '');
    sedGAVIntervaloAbertura.Value :=
      Ini.ReadInteger('GAV', 'AberturaIntervalo', ACBrGAV1.AberturaIntervalo);
    cbGAVAcaoAberturaAntecipada.ItemIndex :=
      Ini.ReadInteger('GAV', 'AcaoAberturaAntecipada', 1);

    { Parametros do DIS }
    cbDISModelo.ItemIndex := Ini.ReadInteger('DIS', 'Modelo', 0);
    cbDISModeloChange(Self);
    cbDISPorta.Text := Ini.ReadString('DIS', 'Porta', '');
    seDISIntervalo.Value := Ini.ReadInteger('DIS', 'Intervalo', 300);
    seDISPassos.Value := Ini.ReadInteger('DIS', 'Passos', 1);
    seDISIntByte.Value := Ini.ReadInteger('DIS', 'IntervaloEnvioBytes', 3);

    { Parametros do LCB }
    cbLCBPorta.Text := Ini.ReadString('LCB', 'Porta', 'Sem Leitor');
    cbLCBPortaChange(Self);
    sedLCBIntervalo.Value := Ini.ReadInteger('LCB', 'Intervalo', 100);
    cbLCBSufixoLeitor.Text := Ini.ReadString('LCB', 'SufixoLeitor', '#13');
    chLCBExcluirSufixo.Checked := Ini.ReadBool('LCB', 'ExcluirSufixo', False);
    edLCBPreExcluir.Text := Ini.ReadString('LCB', 'PrefixoAExcluir', '');
    cbLCBSufixo.Text := Ini.ReadString('LCB', 'SufixoIncluir', '');
    cbLCBDispositivo.Text := Ini.ReadString('LCB', 'Dispositivo', '');
    rbLCBTeclado.Checked := Ini.ReadBool('LCB', 'Teclado', True);
    rbLCBFila.Checked := not rbLCBTeclado.Checked;
    ACBrLCB1.Device.ParamsString := Ini.ReadString('LCB', 'Device', '');

    { Parametros do RFD }
    chRFD.Checked := INI.ReadBool('RFD', 'GerarRFD', False);
    chRFDIgnoraMFD.Checked := INI.ReadBool('RFD', 'IgnoraECF_MFD', True);
    edRFDDir.Text := INI.ReadString('RFD', 'DirRFD', edRFDDir.Text);

    { Parametros do BAL }
    cbBALModelo.ItemIndex := Ini.ReadInteger('BAL', 'Modelo', 0);
    cbBALModeloChange(Self);
    cbBALPorta.Text := Ini.ReadString('BAL', 'Porta', '');
    sedBALIntervalo.Value := Ini.ReadInteger('BAL', 'Intervalo', 200);

    { Parametros do ETQ }
    cbETQModelo.ItemIndex := Ini.ReadInteger('ETQ', 'Modelo', 0);
    cbETQModeloChange(Self);
    cbETQPorta.Text := Ini.ReadString('ETQ', 'Porta', '');

    { Parametros do TC }
    cbxTCModelo.ItemIndex := Ini.ReadInteger('TC', 'Modelo', 0);
    cbxTCModeloChange(Self);
    edTCArqPrecos.Text := IntToStr(Ini.ReadInteger('TC', 'TCP_Porta', 6500));
    edTCArqPrecos.Text := Ini.ReadString('TC', 'Arq_Precos', 'PRICETAB.TXT');
    edTCNaoEncontrado.Text :=
      Ini.ReadString('TC', 'Nao_Econtrado', 'PRODUTO|NAO ENCONTRADO');

    { Parametros do CEP }
    cbCEPWebService.ItemIndex := Ini.ReadInteger('CEP', 'WebService', 0);
    cbCEPWebServiceChange(Self);
    edCEPChaveBuscarCEP.Text := Ini.ReadString('CEP', 'Chave_BuscarCEP', '');
    edCONProxyHost.Text := Ini.ReadString('CEP', 'Proxy_Host', '');
    edCONProxyPort.Text := Ini.ReadString('CEP', 'Proxy_Port', '');
    edCONProxyUser.Text := Ini.ReadString('CEP', 'Proxy_User', '');
    edCONProxyPass.Text := LeINICrypt(INI, 'CEP', 'Proxy_Pass', _C) ;

    {Parametros do Boleto - Cliente}
    edtBOLRazaoSocial.Text  := ini.ReadString('BOLETO', 'Cedente.Nome', '');
    edtBOLCNPJ.Text         := ini.ReadString('BOLETO', 'Cedente.CNPJCPF', '');
    edtBOLLogradouro.Text   := ini.ReadString('BOLETO', 'Cedente.Logradouro', '');
    edtBOLNumero.Text       := ini.ReadString('BOLETO', 'Cedente.Numero', '');
    edtBOLBairro.Text       := ini.ReadString('BOLETO', 'Cedente.Bairro', '');
    edtBOLCidade.Text       := ini.ReadString('BOLETO', 'Cedente.Cidade', '');
    edtBOLCEP.Text          := ini.ReadString('BOLETO', 'Cedente.CEP', '');
    edtBOLComplemento.Text  := ini.ReadString('BOLETO', 'Cedente.Complemento', '');
    cbxBOLUF.Text           := ini.ReadString('BOLETO', 'Cedente.UF', '');
    cbxBOLEmissao.ItemIndex := ini.ReadInteger('BOLETO', 'Cedente.RespEmis', -1);
    cbxBOLF_J.ItemIndex     := ini.ReadInteger('BOLETO','Cedente.Pessoa',-1);
    edtCodTransmissao.Text  := ini.ReadString('BOLETO', 'Cedente.CodTransmissao','');
    edtModalidade.Text      := ini.ReadString('BOLETO','Cedente.Modalidade','');
    edtConvenio.Text        := ini.ReadString('BOLETO','Cedente.Convenio','');

    {Parametros do Boleto - Banco}
    cbxBOLBanco.ItemIndex    := Ini.ReadInteger('BOLETO', 'Banco', 0);
    edtBOLConta.Text         := ini.ReadString('BOLETO', 'Conta', '');
    edtBOLDigitoConta.Text   := ini.ReadString('BOLETO', 'DigitoConta', '');
    edtBOLAgencia.Text       := ini.ReadString('BOLETO', 'Agencia', '');
    edtBOLDigitoAgencia.Text := ini.ReadString('BOLETO', 'DigitoAgencia', '');
    edtCodCliente.Text       := ini.ReadString('BOLETO', 'CodCedente', '');

    {Parametros do Boleto - Boleto}
    deBOLDirLogo.Text        := ini.ReadString('BOLETO', 'DirLogos',
                                ExtractFilePath(Application.ExeName)+'Logos'+PathDelim);
    edtBOLSH.Text            := ini.ReadString('BOLETO', 'SoftwareHouse', '');
    spBOLCopias.Value        := ini.ReadInteger('BOLETO', 'Copias', 1);
    ckgBOLMostrar.Checked[0] := Ini.ReadBool('BOLETO', 'Preview', True);
    ckgBOLMostrar.Checked[1] := ini.ReadBool('BOLETO', 'Setup', True);
    cbxBOLLayout.ItemIndex   := ini.ReadInteger('BOLETO', 'Layout', 0);
    cbxBOLFiltro.ItemIndex   := ini.ReadInteger('BOLETO', 'Filtro', 0);
    deBOLDirArquivo.Text     := ini.ReadString('BOLETO', 'DirArquivoBoleto','');
    deBolDirRemessa.Text     := ini.ReadString('BOLETO', 'DirArquivoRemessa','');
    deBolDirRetorno.Text     := ini.ReadString('BOLETO', 'DirArquivoRetorno','');
    cbxCNAB.ItemIndex        := ini.ReadInteger('BOLETO', 'CNAB',0);
  finally
    Ini.Free;
  end;

  LerSW;

  with ACBrECF1 do
  begin
    Desativar;
    Modelo := TACBrECFModelo(Max(cbECFModelo.ItemIndex - 1, 0));
    Porta  := cbECFPorta.Text;
    if ECFDeviceParams <> '' then
      Device.ParamsString := ECFDeviceParams;
    TimeOut := sedECFTimeout.Value;
    IntervaloAposComando := sedECFIntervalo.Value;
    MaxLinhasBuffer := sedECFMaxLinhasBuffer.Value;
    PaginaDeCodigo := sedECFPaginaCodigo.Value;
    LinhasEntreCupons := sedECFLinhasEntreCupons.Value;
    ArredondaPorQtd := chECFArredondaPorQtd.Checked;
    ArredondaItemMFD := ((chECFArredondaMFD.Enabled) and
                         (chECFArredondaMFD.Checked));
    DescricaoGrande := chECFDescrGrande.Checked;
    GavetaSinalInvertido := chECFSinalGavetaInvertido.Checked;
    BloqueiaMouseTeclado := False;
    ExibeMensagem := False;
    IgnorarTagsFormatacao := chECFIgnorarTagsFormatacao.Checked;
    ArqLOG := edECFLog.Text;
    Ativo := ECFAtivado;
  end;

  with ACBrCHQ1 do
  begin
    Desativar;
    Modelo := TACBrCHQModelo(cbCHQModelo.ItemIndex);
    Porta  := cbCHQPorta.Text;
    if CHQDeviceParams <> '' then
      Device.ParamsString := CHQDeviceParams;
    Favorecido := edCHQFavorecido.Text;
    Cidade := edCHQCidade.Text;

    if edCHQBemafiINI.Text <> '' then
    begin
      try
        ArquivoBemaFiINI := edCHQBemafiINI.Text;
        mResp.Lines.Add('Arquivo de Cheques: ' + ArquivoBemaFiINI +
          sLineBreak + ' lido com sucesso.');
      except
        on E: Exception do
          mResp.Lines.Add(E.Message);
      end;
    end;
    Ativo := CHQAtivado;
  end;

  with ACBrGAV1 do
  begin
    Desativar;
    StrComando := cbGAVStrAbre.Text;
    AberturaIntervalo := sedGAVIntervaloAbertura.Value;
    AberturaAntecipada := TACBrGAVAberturaAntecipada(
      cbGAVAcaoAberturaAntecipada.ItemIndex);
    Modelo := TACBrGAVModelo(cbGAVModelo.ItemIndex);
    Porta := cbGAVPorta.Text;
    Ativo := (GAVAtivado or (pos('serial', LowerCase(ModeloStr)) > 0));
  end;

  with ACBrDIS1 do
  begin
    Desativar;
    Intervalo := seDISIntervalo.Value;
    Passos := seDISPassos.Value;
    IntervaloEnvioBytes := seDISIntByte.Value;
    Modelo := TACBrDISModelo(cbDISModelo.ItemIndex);
    Porta := cbDISPorta.Text;
    Ativo := DISAtivado;
  end;

  with ACBrLCB1 do
  begin
    Desativar;
    Porta := cbLCBPorta.Text;
    Intervalo := sedLCBIntervalo.Value;
    Sufixo := cbLCBSufixoLeitor.Text;
    ExcluirSufixo := chLCBExcluirSufixo.Checked;
    PrefixoAExcluir := edLCBPreExcluir.Text;
    UsarFila := rbLCBFila.Checked;

    { SndKey32.pas só funciona no Windows pois usa a API  "keybd_event" }
    if (ACBrLCB1.Porta <> 'Sem Leitor') and (ACBrLCB1.Porta <> '') then
      ACBrLCB1.Ativar;
  end;

  with ACBrRFD1 do
  begin
    DirRFD       := edRFDDir.Text;
    IgnoraEcfMfd := chRFDIgnoraMFD.Checked;

    if chRFD.Checked then
    begin
       if FileExists( ArqINI ) then
          ACBrRFD1.LerINI;
    end
  end;

  with ACBrBAL1 do
  begin
    Desativar;
    Intervalo := sedBALIntervalo.Value;
    Modelo := TACBrBALModelo(cbBALModelo.ItemIndex);
    Porta := cbBALPorta.Text;
    Ativo := BALAtivado;
  end;

  with ACBrETQ1 do
  begin
    Desativar;
    Modelo := TACBrETQModelo(cbETQModelo.ItemIndex);
    Porta := cbETQPorta.Text;
    Ativo := ETQAtivado;
  end;

  with ACBrCEP1 do
  begin
    WebService := TACBrCEPWebService(cbCEPWebService.ItemIndex) ;
    ChaveAcesso:= edCEPChaveBuscarCEP.Text;
    ProxyHost  := edCONProxyHost.Text;
    ProxyPort  := edCONProxyPort.Text;
    ProxyUser  := edCONProxyUser.Text;
    ProxyPass  := edCONProxyPass.Text;
  end ;

  with ACBrIBGE1 do
  begin
    ProxyHost  := edCONProxyHost.Text;
    ProxyPort  := edCONProxyPort.Text;
    ProxyUser  := edCONProxyUser.Text;
    ProxyPass  := edCONProxyPass.Text;
  end ;

  with ACBrBoleto1 do
  begin
    Cedente.Nome := edtBOLRazaoSocial.Text;

    if cbxBOLF_J.ItemIndex = 0 then
      Cedente.TipoInscricao := pFisica
    else
      Cedente.TipoInscricao := pJuridica ;

    Cedente.CNPJCPF := edtBOLCNPJ.Text;

    Cedente.Logradouro    := edtBOLLogradouro.Text;
    Cedente.NumeroRes     := edtBOLNumero.Text;
    Cedente.Bairro        := edtBOLBairro.Text;
    Cedente.Cidade        := edtBOLCidade.Text;
    Cedente.CEP           := edtBOLCEP.Text;
    Cedente.Complemento   := edtBOLComplemento.Text;
    Cedente.UF            := cbxBOLUF.Text;
    Cedente.CodigoCedente := edtCodCliente.Text;

    Cedente.Agencia       := edtBOLAgencia.Text;
    Cedente.AgenciaDigito := edtBOLDigitoAgencia.Text;
    Cedente.Conta         := edtBOLConta.Text;
    Cedente.ContaDigito   := edtBOLDigitoConta.Text;

    case cbxBOLEmissao.ItemIndex of
      0: Cedente.ResponEmissao := tbCliEmite;
      1: Cedente.ResponEmissao := tbBancoEmite;
      2: Cedente.ResponEmissao := tbBancoReemite;
      3: Cedente.ResponEmissao := tbBancoNaoReemite;
    end;

    if cbxCNAB.ItemIndex = 0 then
       LayoutRemessa := c240
    else
       LayoutRemessa := c400;

    Banco.TipoCobranca := TACBrTipoCobranca( cbxBOLBanco.ItemIndex );
    DirArqRemessa := deBolDirRemessa.Text;
    DirArqRetorno := deBolDirRetorno.Text;
  end;

  with ACBrBoletoFCFortes1 do
  begin
    Filtro := TACBrBoletoFCFiltro( cbxBOLFiltro.ItemIndex ) ;
    LayOut := TACBrBolLayOut( cbxBOLLayout.ItemIndex ) ;

    NumCopias      := spBOLCopias.Value;
    SoftwareHouse  := edtBOLSH.Text;
    DirLogo        := deBOLDirLogo.Text;
    MostrarPreview := ckgBOLMostrar.Checked[0];
    MostrarSetup   := ckgBOLMostrar.Checked[1];
    if Filtro = fiHTML then
       NomeArquivo := deBOLDirArquivo.Text + PathDelim + 'boleto.html'
    else
       NomeArquivo := deBOLDirArquivo.Text + PathDelim + 'boleto.pdf';
  end;

  if cbxTCModelo.ItemIndex > 0 then
    bTCAtivar.Click;

end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.LerSW;
var
  INI: TIniFile;
  ArqSWH, Pass, Chave: ansistring;
begin
  ArqSWH := ExtractFilePath(Application.ExeName) + 'swh.ini';
  if not FileExists(ArqSWH) then
  begin
     mResp.Lines.Add( 'ATENÇÃO: Arquivo "swh.ini" não encontrado.'+sLineBreak+
                      '     Nenhuma Chave RSA Privada pode ser lida.'+sLineBreak);
     exit ;
  end ;

  Ini := TIniFile.Create(ArqSWH);
  try
    edSH_CNPJ.Text := LeINICrypt(INI, 'SWH', 'CNPJ', IntToStrZero(fsHashSenha, 8));
    Pass := IntToStrZero(StringCrc16(edSH_CNPJ.Text + IntToStrZero(fsHashSenha, 8)), 8);

    if LeINICrypt(INI, 'SWH', 'Verifica', Pass) <> 'ARQUIVO SWH.INI ESTA OK' then
        raise Exception.Create('Arquivo "swh.ini" inválido.') ;

    edSH_RazaoSocial.Text := LeINICrypt(INI, 'SWH', 'RazaoSocial', Pass);
    edSH_COO.Text         := LeINICrypt(INI, 'SWH', 'COO', Pass);
    edSH_IE.Text          := LeINICrypt(INI, 'SWH', 'IE', Pass);
    edSH_IM.Text          := LeINICrypt(INI, 'SWH', 'IM', Pass);
    edSH_Aplicativo.Text  := LeINICrypt(INI, 'SWH', 'Aplicativo', Pass);
    edSH_NumeroAP.Text    := LeINICrypt(INI, 'SWH', 'NumeroAplicativo', Pass);
    edSH_VersaoAP.Text    := LeINICrypt(INI, 'SWH', 'VersaoAplicativo', Pass);
    edSH_Linha1.Text      := LeINICrypt(INI, 'SWH', 'Linha1', Pass);
    edSH_Linha2.Text      := LeINICrypt(INI, 'SWH', 'Linha2', Pass);

    ACBrRFD1.SH_RazaoSocial      := edSH_RazaoSocial.Text;
    ACBrRFD1.SH_COO              := edSH_COO.Text;
    ACBrRFD1.SH_CNPJ             := edSH_CNPJ.Text;
    ACBrRFD1.SH_IE               := edSH_IE.Text;
    ACBrRFD1.SH_IM               := edSH_IM.Text;
    ACBrRFD1.SH_NomeAplicativo   := edSH_Aplicativo.Text;
    ACBrRFD1.SH_NumeroAplicativo := edSH_NumeroAP.Text;
    ACBrRFD1.SH_VersaoAplicativo := edSH_VersaoAP.Text;
    ACBrRFD1.SH_Linha1           := edSH_Linha1.Text;
    ACBrRFD1.SH_Linha2           := edSH_Linha2.Text;
  finally
    Ini.Free;
  end;

  try
    Chave := '';
    ACBrEAD1GetChavePrivada( Chave );
    mRSAKey.Text := '- Chave válida encontrada no arquivo "swh.ini"'+sLineBreak+
                    '- Conteudo omitido por segurança. '+sLineBreak+
                    '- Chave será utilizada para assinatura digital';
  except
     mRSAKey.Text := 'ATENÇÃO: Chave RSA Privada NÃO pode ser lida no arquivo "swh.ini".';
     mResp.Lines.Add( mRSAKey.Text + sLineBreak );
  end ;
end;

{------------------------------------------------------------------------------}
function TFrmACBrMonitor.LerChaveSWH: ansistring;
var
  INI: TIniFile;
  Pass: string;
begin
  Result := '';
  Ini := TIniFile.Create(ExtractFilePath(Application.ExeName) + 'swh.ini');
  try
    Pass := LeINICrypt(INI, 'SWH', 'CNPJ', IntToStrZero(fsHashSenha, 8));
    Pass := IntToStrZero(StringCrc16(Pass + IntToStrZero(fsHashSenha, 8)), 8);

    if LeINICrypt(INI, 'SWH', 'Verifica', Pass) = 'ARQUIVO SWH.INI ESTA OK' then
      Result := Trim(LeINICrypt(INI, 'SWH', 'RSA', Pass));
  finally
    Ini.Free;
  end;
end;


{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.SalvarIni;
var
  Ini: TIniFile;
  OldMonitoraTXT, OldMonitoraTCP: boolean;
begin
  if cbSenha.Checked and (edSenha.Text <> 'NADAAQUI') and (edSenha.Text <> '') then
    fsHashSenha := StringCrc16(edSenha.Text);

  if pConfig.Visible and chRFD.Checked and (fsHashSenha < 1) then
  begin
    PageControl1.ActivePageIndex := 0;
    cbSenha.Checked := True;
    edSenha.SetFocus;
    raise Exception.Create('Para trabalhar com RFD é necessário definir uma Senha ' +
                           'para proteger sua Chave Privada');
  end;

  Ini := TIniFile.Create(ACBrMonitorINI);
  try
    // Verificando se modificou o Modo de Monitoramento //
    OldMonitoraTCP := Ini.ReadBool('ACBrMonitor', 'Modo_TCP', False);
    OldMonitoraTXT := Ini.ReadBool('ACBrMonitor', 'Modo_TXT', False);

    // Parametros do Monitor //
    Ini.WriteBool(    'ACBrMonitor', 'Modo_TCP', rbTCP.Checked);
    Ini.WriteBool(    'ACBrMonitor', 'Modo_TXT', rbTXT.Checked);
    Ini.WriteInteger( 'ACBrMonitor', 'TCP_Porta', StrToIntDef(edPortaTCP.Text, 3434));
    Ini.WriteInteger( 'ACBrMonitor', 'TCP_TimeOut', StrToIntDef(edTimeOutTCP.Text, 10000));
    Ini.WriteString(  'ACBrMonitor', 'TXT_Entrada', edEntTXT.Text);
    Ini.WriteString(  'ACBrMonitor', 'TXT_Saida', edSaiTXT.Text);
    Ini.WriteInteger( 'ACBrMonitor', 'Intervalo', sedIntervalo.Value);
    GravaINICrypt(INI,'ACBrMonitor', 'HashSenha', IntToStrZero(fsHashSenha, 8), _C);

    Ini.WriteBool(   'ACBrMonitor', 'Gravar_Log', cbLog.Checked);
    Ini.WriteString( 'ACBrMonitor', 'Arquivo_Log', edLogArq.Text);
    Ini.WriteInteger('ACBrMonitor', 'Linhas_Log', sedLogLinhas.Value);
    Ini.WriteBool(   'ACBrMonitor', 'Comandos_Remotos', cbComandos.Checked);

    { Parametros do ECF }
    Ini.WriteInteger('ECF', 'Modelo', max(cbECFModelo.ItemIndex - 1, 0));
    Ini.WriteString( 'ECF', 'Porta', cbECFPorta.Text);
    Ini.WriteString( 'ECF', 'SerialParams', ACBrECF1.Device.ParamsString);
    Ini.WriteInteger('ECF', 'Timeout', sedECFTimeout.Value);
    Ini.WriteInteger('ECF', 'IntervaloAposComando', sedECFIntervalo.Value);
    Ini.WriteInteger('ECF', 'MaxLinhasBuffer', sedECFMaxLinhasBuffer.Value);
    Ini.WriteInteger('ECF', 'PaginaCodigo', sedECFPaginaCodigo.Value);
    Ini.WriteInteger('ECF', 'LinhasEntreCupons', sedECFLinhasEntreCupons.Value);
    Ini.WriteBool(   'ECF', 'ArredondamentoPorQtd', chECFArredondaPorQtd.Checked);
    Ini.WriteBool(   'ECF', 'ArredondamentoItemMFD', chECFArredondaMFD.Checked);
    Ini.WriteBool(   'ECF', 'DescricaoGrande', chECFDescrGrande.Checked);
    Ini.WriteBool(   'ECF', 'GavetaSinalInvertido', chECFSinalGavetaInvertido.Checked);
    Ini.WriteBool(   'ECF', 'IgnorarTagsFormatacao', chECFIgnorarTagsFormatacao.Checked);
    Ini.WriteString( 'ECF', 'ArqLog', edECFLog.Text);

    { Parametros do CHQ }
    Ini.WriteInteger('CHQ', 'Modelo', cbCHQModelo.ItemIndex);
    Ini.WriteString( 'CHQ', 'Porta', cbCHQPorta.Text);
    Ini.WriteString( 'CHQ', 'SerialParams', ACBrCHQ1.Device.ParamsString);
    Ini.WriteBool(   'CHQ', 'VerificaFormulario', chCHQVerForm.Checked);
    Ini.WriteString( 'CHQ', 'Favorecido', edCHQFavorecido.Text);
    Ini.WriteString( 'CHQ', 'Cidade', edCHQCidade.Text);
    Ini.WriteString( 'CHQ', 'PathBemafiINI', edCHQBemafiINI.Text);

    { Parametros do GAV }
    Ini.WriteInteger('GAV', 'Modelo', cbGAVModelo.ItemIndex);
    Ini.WriteString( 'GAV', 'Porta', cbGAVPorta.Text);
    Ini.WriteString( 'GAV', 'StringAbertura', cbGAVStrAbre.Text);
    Ini.WriteInteger('GAV', 'AberturaIntervalo', sedGAVIntervaloAbertura.Value);
    Ini.WriteInteger('GAV', 'AcaoAberturaAntecipada',
      cbGAVAcaoAberturaAntecipada.ItemIndex);

    { Parametros do DIS }
    Ini.WriteInteger('DIS', 'Modelo', cbDISModelo.ItemIndex);
    Ini.WriteString( 'DIS', 'Porta', cbDISPorta.Text);
    Ini.WriteInteger('DIS', 'Intervalo', seDISIntervalo.Value);
    Ini.WriteInteger('DIS', 'Passos', seDISPassos.Value);
    Ini.WriteInteger('DIS', 'IntervaloEnvioBytes', seDISIntByte.Value);

    { Parametros do LCB }
    Ini.WriteString( 'LCB', 'Porta', cbLCBPorta.Text);
    Ini.WriteInteger('LCB', 'Intervalo', sedLCBIntervalo.Value);
    Ini.WriteString( 'LCB', 'SufixoLeitor', cbLCBSufixoLeitor.Text);
    Ini.WriteBool(   'LCB', 'ExcluirSufixo', chLCBExcluirSufixo.Checked);
    Ini.WriteString( 'LCB', 'PrefixoAExcluir', edLCBPreExcluir.Text);
    Ini.WriteString( 'LCB', 'SufixoIncluir', cbLCBSufixo.Text);
    Ini.WriteString( 'LCB', 'Dispositivo', cbLCBDispositivo.Text);
    Ini.WriteBool(   'LCB', 'Teclado', rbLCBTeclado.Checked);
    Ini.WriteString( 'LCB', 'Device', ACBrLCB1.Device.ParamsString);

    { Parametros do RFD }
    INI.WriteBool(  'RFD', 'GerarRFD', chRFD.Checked);
    INI.WriteString('RFD', 'DirRFD', edRFDDir.Text);
    INI.WriteBool(  'RFD', 'IgnoraECF_MFD', chRFDIgnoraMFD.Checked);

    { Parametros do BAL }
    Ini.WriteInteger('BAL', 'Modelo', cbBALModelo.ItemIndex);
    Ini.WriteString( 'BAL', 'Porta', cbBALPorta.Text);
    Ini.WriteInteger('BAL', 'Intervalo', sedBALIntervalo.Value);

    { Parametros do ETQ }
    Ini.WriteInteger('ETQ', 'Modelo', cbETQModelo.ItemIndex);
    Ini.WriteString( 'ETQ', 'Porta', cbETQPorta.Text);

    { Parametros do CEP }
    Ini.WriteInteger( 'CEP', 'WebService', cbCEPWebService.ItemIndex );
    Ini.WriteString(  'CEP', 'Chave_BuscarCEP', edCEPChaveBuscarCEP.Text);
    Ini.WriteString(  'CEP', 'Proxy_Host', edCONProxyHost.Text);
    Ini.WriteString(  'CEP', 'Proxy_Port', edCONProxyPort.Text);
    Ini.WriteString(  'CEP', 'Proxy_User', edCONProxyUser.Text);
    GravaINICrypt(Ini,'CEP', 'Proxy_Pass',edCONProxyPass.Text, _C) ;

    { Parametros do TC }
    Ini.WriteInteger('TC', 'Modelo', cbxTCModelo.ItemIndex);
    Ini.WriteInteger('TC', 'TCP_Porta', StrToIntDef(edTCArqPrecos.Text, 6500));
    Ini.WriteString( 'TC', 'Arq_Precos', edTCArqPrecos.Text);
    Ini.WriteString( 'TC', 'Nao_Econtrado', edTCNaoEncontrado.Text);
  finally
    Ini.Free;
  end;

  fsLinesLog := 'Configuração geral gravada com sucesso' ;
  AddLinesLog;

  SalvarConfBoletos;
  SalvarSW;

  if chRFD.Checked then
  begin
    with ACBrRFD1 do
    begin
       if Ativo then
       begin
          CONT_RazaoSocial      := edUSURazaoSocial.Text;
          CONT_CNPJ             := edUSUCNPJ.Text;
          CONT_Endereco         := edUSUEndereco.Text;
          CONT_IE               := edUSUIE.Text;
          CONT_NumUsuario       := seUSUNumeroCadastro.Value;
          CONT_DataHoraCadastro := deUSUDataCadastro.Date ;
          try
             CONT_DataHoraCadastro := CONT_DataHoraCadastro +
                                      StrToTime(meUSUHoraCadastro.Text, ':');
          except
          end ;
          CONT_CROCadastro      := seUSUCROCadastro.Value;
          CONT_GTCadastro       := seUSUGTCadastro.Value;
          ECF_RFDID             := lRFDID.Caption;
          ECF_DataHoraSwBasico  := deRFDDataSwBasico.Date;
          try
             ECF_DataHoraSwBasico := ECF_DataHoraSwBasico +
                                     StrToTime(meRFDHoraSwBasico.Text, ':');
          except
          end ;

          GravarINI;

          fsLinesLog := 'Dados do RFD gravados com sucesso' ;
          AddLinesLog;
       end ;
    end ;
  end;

  if (OldMonitoraTXT <> rbTXT.Checked) or (OldMonitoraTCP <> rbTCP.Checked) then
  begin
    MessageDlg('ACBrMonitor',
      'O Método de Monitoramento do ACBrMonitor foi modificado' +
      sLineBreak + sLineBreak + 'Será necessário reinicar o ACBrMonitor',
      mtInformation, [mbOK], 0);
    Application.Terminate;
  end;
end;

procedure TFrmACBrMonitor.SalvarConfBoletos;
var
  Ini: TIniFile;
  TrimedCNPJ, TrimedCEP : String ;
begin
   TrimedCNPJ := OnlyNumber(edtBOLCNPJ.Text) ;
   TrimedCEP  := OnlyNumber(edtBOLCEP.Text);

   if pConfig.Visible and (TrimedCNPJ <> '') then
   begin
     with ACBrValidador1 do
     begin
       if cbxBOLF_J.ItemIndex = 0 then
         TipoDocto := docCPF
       else
         TipoDocto := docCNPJ;

       Documento := edtBOLCNPJ.Text;
       try
          Validar;    // Dispara Exception se Documento estiver errado
       except
          PageControl1.ActivePage := tsACBrBoleto;
          pgBoleto.ActivePage     := tsCedente;
          edtBOLCNPJ.SetFocus;
          raise ;
       end ;

       edtBOLCNPJ.Text := Formatar;
     end;
   end ;

   Ini := TIniFile.Create(ACBrMonitorINI);
   try
     {Parametros do Boleto - Cliente}
     ini.WriteString('BOLETO', 'Cedente.Nome', edtBOLRazaoSocial.Text);
     ini.WriteString('BOLETO', 'Cedente.CNPJCPF', ifthen(TrimedCNPJ='','',edtBOLCNPJ.Text));
     ini.WriteString('BOLETO', 'Cedente.Logradouro', edtBOLLogradouro.Text);
     ini.WriteString('BOLETO', 'Cedente.Numero', edtBOLNumero.Text);
     ini.WriteString('BOLETO', 'Cedente.Bairro', edtBOLBairro.Text);
     ini.WriteString('BOLETO', 'Cedente.Cidade', edtBOLCidade.Text);
     ini.WriteString('BOLETO', 'Cedente.CEP', ifthen(TrimedCEP='','',edtBOLCEP.Text));
     ini.WriteString('BOLETO', 'Cedente.Complemento', edtBOLComplemento.Text);
     ini.WriteString('BOLETO', 'Cedente.UF', cbxBOLUF.Text);
     ini.WriteInteger('BOLETO','Cedente.RespEmis', cbxBOLEmissao.ItemIndex);
     ini.WriteInteger('BOLETO','Cedente.Pessoa',cbxBOLF_J.ItemIndex);
     ini.WriteString('BOLETO', 'Cedente.CodTransmissao', edtCodTransmissao.Text) ;
     ini.WriteString('BOLETO', 'Cedente.Modalidade', edtModalidade.Text);
     ini.WriteString('BOLETO', 'Cedente.Convenio', edtConvenio.Text);

     {Parametros do Boleto - Banco}
     Ini.WriteInteger('BOLETO','Banco', max(cbxBOLBanco.ItemIndex, 0));
     ini.WriteString('BOLETO', 'Conta', edtBOLConta.Text);
     ini.WriteString('BOLETO', 'DigitoConta', edtBOLDigitoConta.Text);
     ini.WriteString('BOLETO', 'Agencia', edtBOLAgencia.Text);
     ini.WriteString('BOLETO', 'DigitoAgencia', edtBOLDigitoAgencia.Text);
     ini.WriteString('BOLETO', 'CodCedente', edtCodCliente.Text);

     {Parametros do Boleto - Boleto}
     ini.WriteString('BOLETO', 'DirLogos', PathWithoutDelim( deBOLDirLogo.Text ) );
     ini.WriteString('BOLETO', 'SoftwareHouse', edtBOLSH.Text);
     ini.WriteInteger('BOLETO', 'Copias', spBOLCopias.Value);
     Ini.WriteBool('BOLETO', 'Preview', ckgBOLMostrar.Checked[0]);
     ini.WriteBool('BOLETO', 'Setup', ckgBOLMostrar.Checked[1]);
     ini.WriteInteger('BOLETO', 'Layout', cbxBOLLayout.ItemIndex);
     ini.WriteInteger('BOLETO', 'Filtro', cbxBOLFiltro.ItemIndex);
     ini.WriteString('BOLETO', 'DirArquivoBoleto',PathWithoutDelim( deBOLDirArquivo.Text ));
     ini.WriteString('BOLETO', 'DirArquivoRemessa',PathWithoutDelim( deBolDirRemessa.Text ));
     ini.WriteString('BOLETO', 'DirArquivoRetorno',PathWithoutDelim( deBolDirRetorno.Text ));
     ini.WriteInteger('BOLETO','CNAB',cbxCNAB.ItemIndex);
   finally
      ini.Free;
   end;

   fsLinesLog := 'Configuração de Boletos gravada com sucesso' ;
   AddLinesLog;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.SalvarSW;
var
  INI: TIniFile;
  Pass, Chave: ansistring;
begin
  with ACBrRFD1 do
  begin
     SH_CNPJ             := edSH_CNPJ.Text ;
     SH_RazaoSocial      := edSH_RazaoSocial.Text;
     SH_COO              := edSH_COO.Text;
     SH_IE               := edSH_IE.Text;
     SH_IM               := edSH_IM.Text;
     SH_NomeAplicativo   := edSH_Aplicativo.Text;
     SH_NumeroAplicativo := edSH_NumeroAP.Text;
     SH_VersaoAplicativo := edSH_VersaoAP.Text;
     SH_Linha1           := edSH_Linha1.Text;
     SH_Linha2           := edSH_Linha2.Text;
  end ;

  try
    Chave := '';
    ACBrEAD1GetChavePrivada( Chave );
  except
  end ;

  Ini := TIniFile.Create(ExtractFilePath(Application.ExeName) + 'swh.ini');
  try
    GravaINICrypt(INI, 'SWH', 'CNPJ', ACBrRFD1.SH_CNPJ, IntToStrZero(fsHashSenha, 8));
    Pass := IntToStrZero(StringCrc16(ACBrRFD1.SH_CNPJ +
      IntToStrZero(fsHashSenha, 8)), 8);

    GravaINICrypt(INI, 'SWH', 'Verifica', 'ARQUIVO SWH.INI ESTA OK', Pass);
    GravaINICrypt(INI, 'SWH', 'RazaoSocial', ACBrRFD1.SH_RazaoSocial, Pass);
    GravaINICrypt(INI, 'SWH', 'COO', ACBrRFD1.SH_COO, Pass);
    GravaINICrypt(INI, 'SWH', 'IE', ACBrRFD1.SH_IE, Pass);
    GravaINICrypt(INI, 'SWH', 'IM', ACBrRFD1.SH_IM, Pass);
    GravaINICrypt(INI, 'SWH', 'Aplicativo', ACBrRFD1.SH_NomeAplicativo, Pass);
    GravaINICrypt(INI, 'SWH', 'NumeroAplicativo', ACBrRFD1.SH_NumeroAplicativo, Pass);
    GravaINICrypt(INI, 'SWH', 'VersaoAplicativo', ACBrRFD1.SH_VersaoAplicativo, Pass);
    GravaINICrypt(INI, 'SWH', 'Linha1', ACBrRFD1.SH_Linha1, Pass);
    GravaINICrypt(INI, 'SWH', 'Linha2', ACBrRFD1.SH_Linha2, Pass);

    if copy( mRSAKey.Text, 1, 5) = '-----' then
      GravaINICrypt(INI, 'SWH', 'RSA', Trim(mRSAKey.Text), Pass)

    else
     begin
        if (Chave = '') and chRFD.Checked then
        begin
          PageControl1.ActivePage := tsCadastro;
          pgCadastro.ActivePage   := tsCadSwH;
          pgSwHouse.ActivePage    := tsCadSwChaveRSA;

          raise Exception.Create('Para trabalhar com RFD é necessário ' +
            'definir uma Chave Privada');
        end ;
    end;

  finally
    Ini.Free;
  end;

  fsLinesLog := 'Dados da Sw.House gravados com sucesso' ;
  AddLinesLog;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.EscondeConfig;
begin
  pConfig.Visible := False;

  bConfig.Caption := '&Configurar';
  bConfig.Glyph := nil;
  ImageList1.GetBitmap(11, bConfig.Glyph);
  bCancelar.Visible := False;
  btMinimizar.Visible := True;
  Application.ProcessMessages;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.ExibeConfig;
var
  Senha: ansistring;
  SenhaOk: boolean;
  HashSenha: integer;
begin
  SenhaOk := (fsHashSenha < 1);
  if not SenhaOk then
  begin
    Senha := '';
    if InputQuery('Configuração', 'Digite a Senha de Configuração', True, Senha) then
    begin
      Senha := Trim(Senha);
      HashSenha := StringCrc16(Senha);
      SenhaOk := (HashSenha = fsHashSenha);
    end;
  end;

  if not SenhaOk then
    raise Exception.Create('Senha [' + Senha + '] inválida');

  fsCNPJSWOK := False;
  pConfig.Visible := True;

  bConfig.Caption := '&Salvar';
  bConfig.Glyph := nil;
  ImageList1.GetBitmap(12, bConfig.Glyph);
  bCancelar.Visible := True;
  btMinimizar.Visible := False;
  PageControl1.ActivePageIndex := 0;

  Application.ProcessMessages;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.Processar;
var
  Linha: ansistring;
begin
  if NewLines <> '' then
    fsProcessar.Add(NewLines);

  NewLines := '';

  while fsProcessar.Count > 0 do
  begin
    // Atualiza Memo de Entrada //
    mCmd.Lines.Assign( fsProcessar );
    Application.ProcessMessages;

    { Objeto BOLETO pode receber comandos com várias Linhas,
      portanto deve processar todas linhas de uma só vez... }
    if UpperCase(Copy(fsProcessar[0],1,6)) = 'BOLETO' then
     begin
       Linha := Trim(fsProcessar.Text);
       fsProcessar.Clear ;
     end
    else
     begin
       Linha := Trim(fsProcessar[0]) ;
       fsProcessar.Delete(0);
     end;

    if Linha <> '' then
    begin
      sbProcessando.Panels[1].Text := Linha;

      try
        if pos('.', Linha) = 0 then              { Comandos do ACBrMonitor }
          Linha := 'ACBR.' + Linha;

		{ Interpretanto o Comando }
        fsCmd.Comando := Linha;

        if fsCmd.Objeto = 'ACBR' then
          DoACBr(fsCmd)
        else if fsCmd.Objeto = 'ECF' then
          DoECF(fsCmd)
        else if fsCmd.Objeto = 'GAV' then
          DoGAV(fsCmd)
        else if fsCmd.Objeto = 'CHQ' then
          DoCHQ(fsCmd)
        else if fsCmd.Objeto = 'DIS' then
          DoDIS(fsCmd)
        else if fsCmd.Objeto = 'LCB' then
          DoLCB(fsCmd)
        else if fsCmd.Objeto = 'BAL' then
          DoBAL(fsCmd)
        else if fsCmd.Objeto = 'ETQ' then
          DoETQ(fsCmd)
        else if fsCmd.Objeto = 'BOLETO' then
          DoBoleto(fsCmd)
        else if fsCmd.Objeto = 'CEP' then
          DoCEP(fsCmd)
        else if fsCmd.Objeto = 'IBGE' then
          DoIBGE(fsCmd);

        // Atualiza Memo de Entrada //
        mCmd.Lines.Assign( fsProcessar );

        Resposta(Linha, 'OK: ' + fsCmd.Resposta);
        Application.ProcessMessages;

      except
        on E: Exception do
          Resposta(Linha, 'ERRO: ' + E.Message);
      end;

      sbProcessando.Panels[1].Text := '';
    end;
  end;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.Resposta(Comando, Resposta: Ansistring);
var
   SL : TStringList ;
begin
  if rbTCP.Checked then
  begin
    if Assigned(Conexao) then
    begin
      Resposta := StringReplace(Resposta, chr(3), '', [rfReplaceAll]);
      Conexao.SendString(Resposta);
      Conexao.SendByte(3);
    end;
  end;

  if rbTXT.Checked then
  begin
     { Primeiro salva em Temporário para que a gravação de todos os Bytes ocorra
       antes que a aplicação controladora do ACBrMonitor tente ler o arquivo de
       Resposta incompleto }
    TryDeleteFile(ArqSaiTMP, 1000); // Tenta apagar por até 1 segundo

    if FileExists(ArqSaiTXT) then
       RenameFile(ArqSaiTXT, ArqSaiTMP); { GravaArqResp faz append se arq. existir }

    if TipoCMD = 'A' then
     begin
       WriteToTXT(ArqSaiTMP, Resposta);
       RenameFile(ArqSaiTMP, ArqSaiTXT);
     end

    else if TipoCMD = 'B' then
     begin
       if copy(Resposta, 1, 3) <> 'OK:' then
        begin
          WriteToTXT(ExtractFilePath(ArqSaiTMP) + 'STATUS.TXT', '0,0,0');
        end
       else
        begin
          WriteToTXT(ExtractFilePath(ArqSaiTMP) + 'STATUS.TXT', '6,0,0');
          Resposta := StringReplace(Resposta, 'OK: ', '', [rfReplaceAll]);
          Resposta := StringReplace(Resposta, '/', '', [rfReplaceAll]);
          Resposta := StringReplace(Resposta, ':', '', [rfReplaceAll]);
          WriteToTXT(ArqSaiTMP, Resposta);
          RenameFile(ArqSaiTMP, ArqSaiTXT);
        end;
     end

    else if TipoCMD = 'D' then
     begin
       if copy(Resposta, 1, 3) <> 'OK:' then
        begin
          WriteToTXT(ExtractFilePath(ArqSaiTMP) + 'DARUMA.RET', '-27;006;000;000');
        end
       else
        begin
          Resposta := StringReplace(Resposta, 'OK: ', '', [rfReplaceAll]);
          Resposta := StringReplace(Resposta, '/', '', [rfReplaceAll]);
          Resposta := StringReplace(Resposta, ':', '', [rfReplaceAll]);
          Resposta := '001;006;000;000;' + Resposta;
          WriteToTXT(ArqSaiTMP, Resposta);
          RenameFile(ArqSaiTMP, ExtractFilePath(ArqSaiTMP) + 'DARUMA.RET');
        end;
     end;

  end;

  mResp.Lines.Add(Comando + sLineBreak + Resposta);
  if mResp.Lines.Count > CBufferMemoResposta then
  begin
     SL := TStringList.Create;
     try
        SL.Assign( mResp.Lines );
        SL.BeginUpdate;
        while SL.Count > CBufferMemoResposta do
           SL.Delete(0);
        SL.EndUpdate;

        mResp.Lines.Assign( SL );
        mResp.SelStart := mResp.Lines.Count;
     finally
        SL.Free;
     end ;
  end ;
  pTopRespostas.Caption := 'Respostas envidas ('+IntToStr(mResp.Lines.Count)+' linhas)';

  if cbLog.Checked then
    WriteToTXT(ArqLogTXT, Comando + sLineBreak + Resposta, True, True);
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.btMinimizarClick(Sender: TObject);
begin
  Ocultar1Click(Sender);
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.bCancelarClick(Sender: TObject);
begin
  EscondeConfig;
  DesInicializar ;
  Inicializar;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.bConfigClick(Sender: TObject);
begin
  if pConfig.Visible then
  begin
    SalvarIni;
    EscondeConfig;

    DesInicializar;  { Re-Inicializa, para as alteraçoes fazerem efeito }
    Inicializar;
  end
  else
    ExibeConfig;

  fsRFDLeuParams := False;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.rbTCPTXTClick(Sender: TObject);
begin
  gbTCP.Enabled := rbTCP.Checked;
  gbTXT.Enabled := rbTXT.Checked;

  if rbTXT.Checked then
  begin
    if edENTTXT.Text = '' then
      edENTTXT.Text := 'ENT.TXT';

    if edSAITXT.Text = '' then
      edSAITXT.Text := 'SAI.TXT';
  end
  else
  begin
    if edPortaTCP.Text = '' then
      edPortaTCP.Text := '3434';

    if edTimeOutTCP.Text = '' then
      edTimeOutTCP.Text := '10000';
  end;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.cbSenhaClick(Sender: TObject);
begin
  gbSenha.Enabled := cbSenha.Checked;
  if not cbSenha.Checked then
  begin
    fsHashSenha := -1;
    edSenha.Text := '';
  end;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.cbLogClick(Sender: TObject);
begin
  gbLog.Enabled := cbLog.Checked;

  if cbLog.Checked and (edLogArq.Text = '') then
    edLogArq.Text := 'LOG.TXT';
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.sbLogClick(Sender: TObject);
begin
  OpenURL(ExtractFilePath(Application.ExeName) + edLogArq.Text);
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.edOnlyNumbers(Sender: TObject; var Key: char);
begin
  if not (Key in ['0'..'9', #13, #8]) then
    Key := #0;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.ACBrECF1MsgAguarde(Mensagem: string);
begin
  StatusBar1.Panels[1].Text :=
    StringReplace(Mensagem, sLineBreak, ' ', [rfReplaceAll]);
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.ACBrECF1MsgPoucoPapel(Sender: TObject);
begin
  StatusBar1.Panels[1].Text := 'ATENÇAO. Pouco papel';
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.DoACBrTimer(Sender: TObject);
var
  MS     : TMemoryStream;
  Linhas : TStringList;
  S      : AnsiString;
begin
  Timer1.Enabled := False;

  if Inicio then
  begin
    Inicializar;
    exit;
  end;

  try
    if FileExists(ArqEntTXT) then  { Existe arquivo para ler ? }
    try
      Linhas := TStringList.Create;

      TipoCMD := 'A';
      if (UpperCase(ExtractFileName(ArqEntTXT)) = 'BEMAFI32.CMD') then
        TipoCMD := 'B'
      else if (UpperCase(ExtractFileName(ArqEntTXT)) = 'DARUMA.CMD') then
        TipoCMD := 'D';

      { Lendo em MemoryStream temporário para nao apagar comandos nao processados }
      MS := TMemoryStream.Create;
      try
        MS.LoadFromFile(ArqEntTXT);
        MS.Position := 0;
        SetLength(S, MS.Size);
        MS.ReadBuffer(PChar(S)^, MS.Size);
        Linhas.Text := S;
      finally
        MS.Free;
      end;

      TryDeleteFile(ArqEntTXT, 1000); // Tenta apagar por até 1 segundo

      if TipoCMD = 'B' then
        Linhas.Text := TraduzBemafi( Linhas.Text )
      else if TipoCMD = 'D' then
        Linhas.Text := TraduzObserver( Linhas.Text );

      fsProcessar.AddStrings( Linhas ) ;
    finally
      Linhas.Free ;
    end ;

    Processar;
  finally
    Timer1.Enabled := True;
  end;
end;

{---------------------------------- ACBrECF -----------------------------------}
{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.tsECFShow(Sender: TObject);
begin
  AvaliaEstadoTsECF;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.cbECFModeloChange(Sender: TObject);
begin
  try
    if ACBrECF1.Ativo then
      bECFAtivar.Click;

    ACBrECF1.Modelo := TACBrECFModelo(Max(cbECFModelo.ItemIndex - 1, 0))
  finally
    if cbECFModelo.Text <> 'Procurar' then
      cbECFModelo.ItemIndex := integer(ACBrECF1.Modelo) + 1;
    cbECFPorta.Text := ACBrECF1.Porta;
  end;

  AvaliaEstadoTsECF;
end;

procedure TFrmACBrMonitor.AvaliaEstadoTsECF;
begin
  bECFAtivar.Enabled :=
    ((ACBrECF1.Modelo <> ecfNenhum) or
    (cbECFModelo.Text = 'Procurar'));

  cbECFPorta.Enabled      := bECFAtivar.Enabled;
  sedECFTimeout.Enabled   := bECFAtivar.Enabled;
  sedECFIntervalo.Enabled := bECFAtivar.Enabled;
  tsECFParamI.Enabled     := bECFAtivar.Enabled;
  tsECFParamII.Enabled    := bECFAtivar.Enabled;

  bECFTestar.Enabled := ACBrECF1.Ativo;
  bECFLeituraX.Enabled := ACBrECF1.Ativo;

  bECFAtivar.Glyph := nil;
  if ACBrECF1.Ativo then
  begin
    bECFAtivar.Caption := '&Desativar';
    ImageList1.GetBitmap(6, bECFAtivar.Glyph);
  end
  else
  begin
    bECFAtivar.Caption := '&Ativar';
    ImageList1.GetBitmap(5, bECFAtivar.Glyph);
  end;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.cbECFPortaChange(Sender: TObject);
begin
  try
    if ACBrECF1.Ativo then
      bECFAtivar.Click;

    ACBrECF1.Porta := cbECFPorta.Text;
  finally
    cbECFPorta.Text := ACBrECF1.Porta;
  end;
end;


{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.sbECFSerialClick(Sender: TObject);
begin
  frConfiguraSerial := TfrConfiguraSerial.Create(self);

  try
    if ACBrECF1.Ativo then
      bECFAtivar.Click;

    frConfiguraSerial.Device.Porta := ACBrECF1.Device.Porta;
    frConfiguraSerial.cmbPortaSerial.Text := cbECFPorta.Text;
    frConfiguraSerial.Device.ParamsString := ACBrECF1.Device.ParamsString;

    if frConfiguraSerial.ShowModal = mrOk then
    begin
      cbECFPorta.Text := frConfiguraSerial.Device.Porta;
      ACBrECF1.Device.ParamsString := frConfiguraSerial.Device.ParamsString;
    end;
  finally
    FreeAndNil(frConfiguraSerial);
    AvaliaEstadoTsECF;
  end;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.sedECFTimeoutChanged(Sender: TObject);
begin
  ACBrECF1.TimeOut := sedECFTimeout.Value;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.sedECFIntervaloChanged(Sender: TObject);
begin
  ACBrECF1.IntervaloAposComando := sedECFIntervalo.Value;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.chECFArredondaPorQtdClick(Sender: TObject);
begin
  ACBrECF1.ArredondaPorQtd := chECFArredondaPorQtd.Checked;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.chECFDescrGrandeClick(Sender: TObject);
begin
  ACBrECF1.DescricaoGrande := chECFDescrGrande.Checked;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.chECFSinalGavetaInvertidoClick(Sender: TObject);
begin
  ACBrECF1.GavetaSinalInvertido := chECFSinalGavetaInvertido.Checked;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.edECFLogChange(Sender: TObject);
begin
  ACBrECF1.ArqLOG := edECFLog.Text;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.sbECFLogClick(Sender: TObject);
begin
  OpenURL(ExtractFilePath(Application.ExeName) + edECFLog.Text);
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.bECFAtivarClick(Sender: TObject);
begin
  if bECFAtivar.Caption = '&Ativar' then
  begin
    Self.Enabled := False;

    try
      if cbECFModelo.ItemIndex = 0 then
        if not ACBrECF1.AcharECF(True, False) then
        begin
          MessageDlg('Nenhum ECF encontrado.', mtInformation, [mbOK], 0);
          exit;
        end;

      if chRFD.Checked then
      begin
        with ACBrRFD1 do
        begin
           DirRFD       := edRFDDir.Text;
           IgnoraEcfMfd := chRFDIgnoraMFD.Checked;
        end ;
      end ;

      ACBrECF1.Ativar;
    finally
      Self.Enabled := True;

      cbECFModelo.ItemIndex := integer(ACBrECF1.Modelo) + 1;
      cbECFPorta.Text := ACBrECF1.Porta;
    end;
  end
  else
    ACBrECF1.Desativar;

  AvaliaEstadoTsECF;
  AvaliaEstadoTsRFD;
end;

procedure TFrmACBrMonitor.meUSUHoraCadastroExit(Sender : TObject) ;
begin
  try
     StrToTime(meUSUHoraCadastro.Text,':');
  except
     mResp.Lines.Add( 'Hora Inválida' );
     meUSUHoraCadastro.SetFocus;
  end ;
end;

procedure TFrmACBrMonitor.meRFDHoraSwBasicoExit(Sender : TObject) ;
begin
  try
     StrToTime(meRFDHoraSwBasico.Text,':');
  except
     mResp.Lines.Add( 'Hora Inválida' );
     meRFDHoraSwBasico.SetFocus;
  end ;
end;

procedure TFrmACBrMonitor.sbSobreClick(Sender : TObject) ;
begin
  frmSobre := TfrmSobre.Create(self);
  try
    frmSobre.lVersao.Caption := 'Ver: ' + Versao;
    frmSobre.ShowModal;
  finally
    FreeAndNil(frmSobre);
  end;
end;

procedure TFrmACBrMonitor.sedECFLinhasEntreCuponsChange(Sender: TObject);
begin
   ACBrECF1.LinhasEntreCupons := sedECFLinhasEntreCupons.Value;
end;

procedure TFrmACBrMonitor.sedECFMaxLinhasBufferChange(Sender: TObject);
begin
   ACBrECF1.MaxLinhasBuffer := sedECFMaxLinhasBuffer.Value;
end;

procedure TFrmACBrMonitor.sedECFPaginaCodigoChange(Sender: TObject);
begin
   ACBrECF1.PaginaDeCodigo := sedECFPaginaCodigo.Value;
end;

procedure TFrmACBrMonitor.TcpServerConecta(const TCPBlockSocket: TTCPBlockSocket;
  var Enviar: ansistring);
Var
  Resp : String ;
begin

  Conexao := TCPBlockSocket;
  mCmd.Lines.Clear;
  fsProcessar.Clear;
  Resp := 'ACBrMonitor Ver. ' + Versao + sLineBreak +
    'Conectado em: ' + FormatDateTime('dd/mm/yy hh:nn:ss', now) + sLineBreak +
    'Máquina: ' + Conexao.GetRemoteSinIP + sLineBreak +
    'Esperando por comandos.' ;

  Resposta('', Resp);
end;

procedure TFrmACBrMonitor.TcpServerDesConecta(const TCPBlockSocket: TTCPBlockSocket;
  Erro: integer; ErroDesc: string);
var
  Resp : String ;
begin
  Conexao := TCPBlockSocket;
  Resp := 'ALERTA: Fim da Conexão com: ' +
    Conexao.GetRemoteSinIP + ' em: ' +
    FormatDateTime('dd/mm/yy hh:nn:ss', now) ;

  mResp.Lines.Add(Resp);
end;

procedure TFrmACBrMonitor.TcpServerRecebeDados(const TCPBlockSocket: TTCPBlockSocket;
  const Recebido: AnsiString; var Enviar: AnsiString);
var
  CmdEnviado: AnsiString;
begin
  Conexao := TCPBlockSocket;
  { Le o que foi enviado atravez da conexao TCP }
  CmdEnviado := Trim(Recebido);
  if CmdEnviado <> '' then
  begin
    NewLines := CmdEnviado;
    Processar;
  end;
end;

procedure TFrmACBrMonitor.TCPServerTCConecta(
  const TCPBlockSocket : TTCPBlockSocket ; var Enviar : AnsiString) ;
var
  IP, Id: ansistring;
  Indice: integer;
begin
  TCPBlockSocket.SendString( '#ok' ) ;

  Id := Trim(TCPBlockSocket.RecvPacket( 1000 ));
  IP := TCPBlockSocket.GetRemoteSinIP ;
  Indice := mTCConexoes.Lines.IndexOf(IP);
  if Indice < 0 then
  begin
     mTCConexoes.Lines.Add(IP);
     fsLinesLog := 'T.C. Inicio Conexão IP: [' + IP + '] ID: [' +Id +']' +
                   ' em: ' +FormatDateTime('dd/mm/yy hh:nn:ss', now);
     AddLinesLog;
  end;
end;

procedure TFrmACBrMonitor.TCPServerTCDesConecta(
  const TCPBlockSocket : TTCPBlockSocket ; Erro : Integer ; ErroDesc : String) ;
Var
  IP : String ;
  Indice : Integer ;
begin
  IP  := TCPBlockSocket.GetRemoteSinIP ;
  fsLinesLog := 'T.C. Fim Conexão IP: ['+ IP + '] em: '+
                FormatDateTime('dd/mm/yy hh:nn:ss', now ) ;
  AddLinesLog ;

  Indice := mTCConexoes.Lines.IndexOf( IP ) ;
  if Indice >= 0 then
     mTCConexoes.Lines.Delete( Indice );
end;

procedure TFrmACBrMonitor.TCPServerTCRecebeDados(
  const TCPBlockSocket : TTCPBlockSocket ; const Recebido : AnsiString ;
  var Enviar : AnsiString) ;
Var
  Comando, Linha : AnsiString ;
  Indice, P1, P2 : Integer ;
begin
  { Le o que foi enviado atravez da conexao TCP }
  Comando := StringReplace(Trim(Recebido),#0,'',[rfReplaceAll]) ;  // Remove nulos

  if pos( '#live', Comando ) > 0 then
  begin
     Comando := StringReplace(Comando,'#live','',[rfReplaceAll]) ; // Remove #live
     TCPBlockSocket.Tag := 0 ;                      // Zera falhas de #live?
  end ;

  if Comando = '' then
     exit ;

  fsLinesLog := 'TC: ['+TCPBlockSocket.GetRemoteSinIP+'] RX: <- ['+Comando+']' ;
  AddLinesLog ;

  if copy(Comando,1,1) = '#' then
  begin
     Comando  := copy( Comando, 2, Length(Comando)) ;
     P1       := 0 ;
     P2       := 0 ;
     Indice   := fsSLPrecos.IndexOfName( Comando ) ;
     if Indice >= 0 then
      begin
        Linha := fsSLPrecos[ Indice ] ;
        P1    := Pos('|',Linha) ;
        P2    := PosAt('|',Linha,3) ;
      end
     else
        Linha := edTCNaoEncontrado.Text ;

     if P2 = 0 then
        P2 := Length( Linha )+1 ;

     Enviar := '#' + copy( Linha, P1+1, P2-P1-1 ) ;
     Enviar := LeftStr(Enviar,45) ;

     TCPBlockSocket.Tag := 0 ;  // Zera falhas de #live?
     fsLinesLog := '     TX: -> ['+Enviar+']' ;
     AddLinesLog ;
  end ;
end;

procedure TFrmACBrMonitor.TrayIcon1Click(Sender: TObject);
begin
  TrayIcon1.PopUpMenu.PopUp;
end;

procedure TFrmACBrMonitor.tsACBrBoletoShow(Sender : TObject) ;
begin
  pgBoleto.ActivePageIndex := 0 ;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.bECFTestarClick(Sender: TObject);
begin
  ACBrECF1.TestarDialog;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.bECFLeituraXClick(Sender: TObject);
var
  wAtivo: boolean;
begin
  wAtivo := ACBrECF1.Ativo;

  try
    ACBrECF1.Ativar;
    ACBrECF1.LeituraX;
  finally
    ACBrECF1.Ativo := wAtivo;
  end;
end;

{------------------------------------ ACBrCHQ ---------------------------------}
procedure TFrmACBrMonitor.cbCHQPortaChange(Sender: TObject);
begin
  if ACBrCHQ1.Modelo <> chqImpressoraECF then
  begin
    try
      ACBrCHQ1.Desativar;
      ACBrCHQ1.Porta := cbCHQPorta.Text;
    finally
      cbCHQPorta.Text := ACBrCHQ1.Porta;
    end;
  end;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.edCHQFavorecidoChange(Sender: TObject);
begin
  ACBrCHQ1.Favorecido := edCHQFavorecido.Text;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.edCHQCidadeChange(Sender: TObject);
begin
  ACBrCHQ1.Cidade := edCHQCidade.Text;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.bCHQTestarClick(Sender: TObject);
var
  wAtivo: boolean;
begin
  wAtivo := ACBrCHQ1.Ativo;

  try
   {  ACBrCHQ1.Ativar ;
     ACBrCHQ1.Banco     := '001' ;
     ACBrCHQ1.Cidade    := IfThen(edCHQCidade.Text='',
                                    'Nome da sua Cidade',edCHQCidade.Text) ;
     ACBrCHQ1.Favorecido:= IfThen(edCHQFavorecido.Text='',
                                     'Nome do Favorecido', edCHQFavorecido.Text) ;
     ACBrCHQ1.Observacao:= 'Texto de Observacao' ;
     ACBrCHQ1.Valor     := 123456.12 ;
     ACBrCHQ1.ImprimirCheque ;}
  finally
    ACBrCHQ1.Ativo := wAtivo;
  end;
end;

{------------------------------------ ACBrGAV ---------------------------------}
{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.tsGAVShow(Sender: TObject);
begin
  AvaliaEstadoTsGAV;
end;

procedure TFrmACBrMonitor.bGAVAtivarClick(Sender: TObject);
begin
  if bGAVAtivar.Caption = '&Ativar' then
    ACBrGAV1.Ativar
  else
    ACBrGAV1.Desativar;

  AvaliaEstadoTsGAV;
end;

procedure TFrmACBrMonitor.cbGAVPortaChange(Sender: TObject);
begin
  if ACBrGAV1.Modelo <> gavImpressoraECF then
  begin
    try
      ACBrGAV1.Desativar;
      ACBrGAV1.Porta := cbGAVPorta.Text;
    finally
      cbGAVPorta.Text := ACBrGAV1.Porta;
    end;
  end;

  AvaliaEstadoTsGAV;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.cbGAVStrAbreChange(Sender: TObject);
begin
  ACBrGAV1.StrComando := cbGAVStrAbre.Text;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.sedGAVIntervaloAberturaChanged(Sender: TObject);
begin
  ACBrGAV1.AberturaIntervalo := sedGAVIntervaloAbertura.Value;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.cbGAVAcaoAberturaAntecipadaChange(Sender: TObject);
begin
  ACBrGAV1.AberturaAntecipada :=
    TACBrGAVAberturaAntecipada(
    cbGAVAcaoAberturaAntecipada.ItemIndex);
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.bGAVEstadoClick(Sender: TObject);
begin
  if not ACBrGAV1.Ativo then
    ACBrGAV1.Ativar;

  if ACBrGAV1.GavetaAberta then
    lGAVEstado.Caption := 'Aberta'
  else
    lGAVEstado.Caption := 'Fechada';
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.bGAVAbrirClick(Sender: TObject);
begin
  try
    tsGAV.Enabled := False;
    lGAVEstado.Caption := 'AGUARDE';

    ACBrGAV1.AbreGaveta;
  finally
    tsGAV.Enabled := True;
    bGAVEstado.Click;
  end;
end;

{------------------------------------ ACBrDIS ---------------------------------}
procedure TFrmACBrMonitor.cbDISPortaChange(Sender: TObject);
begin
  try
    ACBrDIS1.Desativar;
    ACBrDIS1.Porta := cbDISPorta.Text;
  finally
    cbDISPorta.Text := ACBrDIS1.Porta;
  end;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.seDISIntervaloChanged(Sender: TObject);
begin
  ACBrDIS1.Intervalo := seDISIntervalo.Value;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.seDISPassosChanged(Sender: TObject);
begin
  ACBrDIS1.Passos := seDISPassos.Value;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.seDISIntByteChanged(Sender: TObject);
begin
  ACBrDIS1.IntervaloEnvioBytes := seDISIntByte.Value;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.bDISLimparClick(Sender: TObject);
begin
  ACBrDIS1.LimparDisplay;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.bDISTestarClick(Sender: TObject);
begin
  ACBrDIS1.Ativar;
  ACBrDIS1.ExibirLinha(1, 'Projeto ACBr');
  ACBrDIS1.ExibirLinha(2, 'http://acbr.sf.net');
end;

procedure TFrmACBrMonitor.bDISAnimarClick(Sender: TObject);
begin
  ACBrDIS1.Ativar;
  ACBrDIS1.LimparDisplay;
  ACBrDIS1.ExibirLinha(1, padC('Projeto ACBr', ACBrDIS1.Colunas)
    , efeDireita_Esquerda);
  ACBrDIS1.ExibirLinha(2, padC('http://acbr.sf.net', ACBrDIS1.Colunas)
    , efeEsquerda_Direita);
end;

{------------------------------------ ACBrLCB ---------------------------------}
{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.tsLCBShow(Sender: TObject);
begin
  AvaliaEstadoTsLCB;
end;

procedure TFrmACBrMonitor.cbLCBPortaChange(Sender: TObject);
begin
  try
    ACBrLCB1.Desativar;
    ACBrLCB1.Porta := cbLCBPorta.Text;
  finally
    cbLCBPorta.Text := ACBrLCB1.Porta;
  end;

  AvaliaEstadoTsLCB;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.bLCBSerialClick(Sender: TObject);
begin
  ACBrLCB1.Desativar;
  frConfiguraSerial := TfrConfiguraSerial.Create(self);

  try
    frConfiguraSerial.Device.Porta := ACBrLCB1.Device.Porta;
    frConfiguraSerial.cmbPortaSerial.Text := cbLCBPorta.Text;
    frConfiguraSerial.Device.ParamsString := ACBrLCB1.Device.ParamsString;

    if frConfiguraSerial.ShowModal = mrOk then
    begin
      cbLCBPorta.Text := frConfiguraSerial.Device.Porta;
      ACBrLCB1.Device.ParamsString := frConfiguraSerial.Device.ParamsString;
    end;
  finally
    FreeAndNil(frConfiguraSerial);
    AvaliaEstadoTsLCB;
  end;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.sedLCBIntervaloChanged(Sender: TObject);
begin
  ACBrLCB1.Intervalo := sedLCBIntervalo.Value;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.rbLCBTecladoClick(Sender: TObject);
begin
  cbLCBSufixo.Enabled := rbLCBTeclado.Checked;
  cbLCBDispositivo.Enabled := rbLCBTeclado.Checked;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.bLCBAtivarClick(Sender: TObject);
begin
  sedLCBIntervalo.Value := ACBrLCB1.Intervalo;
  if bLCBAtivar.Caption = '&Ativar' then
    ACBrLCB1.Ativar
  else
    ACBrLCB1.Desativar;

  AvaliaEstadoTsLCB;
end;

procedure TFrmACBrMonitor.AvaliaEstadoTsLCB;
begin
  bLCBAtivar.Enabled := ((cbLCBPorta.Text <> 'Sem Leitor') and
    (cbLCBPorta.ItemIndex > 0));
  cbLCBSufixo.Enabled := bLCBAtivar.Enabled;
  cbLCBSufixoLeitor.Enabled := bLCBAtivar.Enabled;
  cbLCBDispositivo.Enabled := bLCBAtivar.Enabled;
  edLCBPreExcluir.Enabled := bLCBAtivar.Enabled;
  chLCBExcluirSufixo.Enabled := bLCBAtivar.Enabled;
  sedLCBIntervalo.Enabled := bLCBAtivar.Enabled;
  bLCBSerial.Enabled := bLCBAtivar.Enabled;
  rbLCBTeclado.Enabled := bLCBAtivar.Enabled;
  rbLCBFila.Enabled := bLCBAtivar.Enabled;

  rbLCBTecladoClick(Self);

  bLCBAtivar.Glyph := nil;
  if ACBrLCB1.Ativo then
  begin
    bLCBAtivar.Caption := '&Desativar';
    shpLCB.Brush.Color := clLime;
    ImageList1.GetBitmap(6, bLCBAtivar.Glyph);
  end
  else
  begin
    bLCBAtivar.Caption := '&Ativar';
    shpLCB.Brush.Color := clRed;
    ImageList1.GetBitmap(5, bLCBAtivar.Glyph);
  end;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.cbLCBSufixoLeitorChange(Sender: TObject);
begin
  ACBrLCB1.Sufixo := cbLCBSufixoLeitor.Text;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.edLCBSufLeituraKeyPress(Sender: TObject; var Key: char);
begin
  if not (Key in ['0'..'9', '#', ',', #13, #8]) then
    Key := #0;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.chLCBExcluirSufixoClick(Sender: TObject);
begin
  ACBrLCB1.ExcluirSufixo := chLCBExcluirSufixo.Checked;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.edLCBPreExcluirChange(Sender: TObject);
begin
  ACBrLCB1.PrefixoAExcluir := edLCBPreExcluir.Text;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.AumentaTempoHint(Sender: TObject);
begin
  Application.HintHidePause := 15000;
end;

procedure TFrmACBrMonitor.DiminuiTempoHint(Sender: TObject);
begin
  Application.HintHidePause := 5000;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.ACBrLCB1LeCodigo(Sender: TObject);
var
  Codigo: ansistring;
    {$IFDEF LINUX}
  fd, I: integer;
  C: char;
    {$ENDIF}
begin
  lLCBCodigoLido.Caption := Converte(ACBrLCB1.UltimaLeitura);

  mResp.Lines.Add('LCB -> ' + ACBrLCB1.UltimoCodigo);

  if rbLCBTeclado.Checked then
  begin
    Codigo := ACBrLCB1.UltimoCodigo;
    if Codigo = '' then
      exit;

     {$IFDEF MSWINDOWS}
    Codigo := Codigo + Trim(cbLCBSufixo.Text);
    SendKeys(PChar(Codigo), True);
     {$ENDIF}

    { Alguem sabe como enviar as teclas para o Buffer do KDE ??? }
     {$IFDEF LINUX}
    Codigo := Codigo + TraduzComando(cbLCBSufixo.Text);
    fd := FileOpen(Trim(cbLCBDispositivo.Text), O_WRONLY + O_NONBLOCK);
    if fd < 0 then
      Writeln('Erro ao abrir o dispositivo: ' + Trim(cbLCBDispositivo.Text))
    else
      try
        for I := 1 to length(Codigo) do
        begin
          C := Codigo[I];
          FpIOCtl(fd, TIOCSTI, @C);
        end;
      finally
        FileClose(fd);
      end;

    //   WriteToTXT('/dev/stdin',Codigo,False);
    //   RunCommand('echo','"TESTE'+Codigo+'" > /dev/tty1',true) ;
     {$ENDIF}
  end;
end;


{---------------------------------- ACBrRFD -----------------------------------}
{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.tsRFDShow(Sender: TObject);
begin
  pgConRFD.ActivePageIndex := 0;

  AvaliaEstadoTsRFD;

  mRFDINI.Lines.Clear;
  fsRFDIni := '';
end;

procedure TFrmACBrMonitor.AvaliaEstadoTsRFD;
var
  MM: string;
  I: integer;
  SL: TStringList;
  Ini: TIniFile;
begin
  bRFDMF.Enabled   := ACBrECF1.Ativo;
  edRFDDir.Enabled := not bRFDMF.Enabled;
  cbRFDModelo.Enabled := bRFDMF.Enabled;

  tsRFDINI.Enabled := ACBrECF1.Ativo and ACBrRFD1.Ativo;

  lRFDID.Caption := ACBrRFD1.ECF_RFDID;
  deRFDDataSwBasico.Date    := ACBrRFD1.ECF_DataHoraSwBasico ;
  deRFDDataSwBasico.Enabled := tsRFDINI.Enabled;
  meRFDHoraSwBasico.Text    := FormatDateTime('hh:nn', ACBrRFD1.ECF_DataHoraSwBasico);
  meRFDHoraSwBasico.Enabled := tsRFDINI.Enabled;

  if ACBrECF1.Ativo then
    gbRFDECF.Hint := 'Selecione o Modelo do ECF'
  else
    pgConRFD.Hint := 'Para Configurar o RFD é necessário ativar o ECF e' + sLineBreak +
      'Selecionar a opção para Geração de RFD';

  if ACBrECF1.Ativo and ACBrRFD1.Ativo then
  begin
    if (copy(lRFDID.Caption, 1, Length(ACBrECF1.RFDID)) <> ACBrECF1.RFDID) or
      (cbRFDModelo.Items.Count = 0) then
    begin
      if copy(lRFDID.Caption, 1, Length(ACBrECF1.RFDID)) <> ACBrECF1.RFDID then
        lRFDID.Caption := ACBrECF1.RFDID;

      MM := ACBrRFD1.AchaRFDID(lRFDID.Caption);
      lRFDMarca.Caption := Trim(copy(MM, 1, pos('|', MM + '|') - 1));

      SL := TStringList.Create;
      Ini := TIniFile.Create(ACBrRFD1.ArqRFDID);
      try
        Ini.ReadSectionValues('Modelos', SL);

        cbRFDModelo.Items.Clear;
        for I := 0 to SL.Count - 1 do
        begin
          if copy(SL[I], 1, 2) = copy(lRFDID.Caption, 1, 2) then
            cbRFDModelo.Items.Add(SL[I]);

          if copy(SL[I], 1, 3) = lRFDID.Caption then
            cbRFDModelo.Text := SL[I];
        end;
      finally
        SL.Free;
        Ini.Free;
      end;

      ACBrRFD1.ECF_RFDID := lRFDID.Caption;

      if not fsRFDLeuParams then
      begin
        edUSURazaoSocial.Text     := ACBrRFD1.CONT_RazaoSocial;
        edUSUEndereco.Text        := ACBrRFD1.CONT_Endereco;
        edUSUCNPJ.Text            := ACBrRFD1.CONT_CNPJ;
        edUSUIE.Text              := ACBrRFD1.CONT_IE;
        seUSUNumeroCadastro.Value := ACBrRFD1.CONT_NumUsuario;
        deUSUDataCadastro.Date    := ACBrRFD1.CONT_DataHoraCadastro ;
        seUSUCROCadastro.Value    := ACBrRFD1.CONT_CROCadastro;
        meUSUHoraCadastro.Text    := FormatDateTime('hh:nn', ACBrRFD1.CONT_DataHoraCadastro) ;
        seUSUGTCadastro.Value     := ACBrRFD1.CONT_GTCadastro;

        fsRFDLeuParams := True;
      end;
    end;
  end;
end;

procedure TFrmACBrMonitor.sbDirRFDClick(Sender: TObject);
begin
  OpenURL(ACBrRFD1.DirRFD);
end;

procedure TFrmACBrMonitor.bRFDMFClick(Sender: TObject);
var
  SL: TStringList;
begin
  if not ACBrECF1.Ativo then
    raise Exception.Create('ECF não está ativo');

  SL := TStringList.Create;
  try
    SL.Clear;
    ACBrECF1.LeituraMemoriaFiscalSerial(now, now, SL);

    mResp.Lines.AddStrings(SL);
  finally
    SL.Free;
  end;
end;

procedure TFrmACBrMonitor.cbRFDModeloChange(Sender: TObject);
begin
  lRFDID.Caption := copy(cbRFDModelo.Text, 1, 3);
end;


procedure TFrmACBrMonitor.seUSUGTCadastroKeyPress(Sender: TObject; var Key: char);
begin
  if Key in [',', '.'] then
    Key := DecimalSeparator;

  if not (Key in ['0'..'9', #13, #8, DecimalSeparator]) then
    Key := #0;
end;

procedure TFrmACBrMonitor.seUSUGTCadastroExit(Sender: TObject);
begin
  ACBrRFD1.CONT_GTCadastro :=
    StrToFloatDef(seUSUGTCadastro.Text, ACBrRFD1.CONT_GTCadastro);
  seUSUGTCadastro.Text := FormatFloat('0.00', ACBrRFD1.CONT_GTCadastro);
end;

{------------------------------ Aba Chave RSA --------------------------------}
procedure TFrmACBrMonitor.tsRFDRSAShow(Sender: TObject);
begin
  if mRSAKey.Text = '' then
    mRSAKey.Text := LerChaveSWH;
end;

procedure TFrmACBrMonitor.bRSALoadKeyClick(Sender: TObject);
begin
  OpenDialog1.Filter := 'Arquivos KEY|*.key|Arquivos PEM|*.pem|Todos Arquivos|*.*';

  if OpenDialog1.Execute then
     mRSAKey.Lines.LoadFromFile(OpenDialog1.FileName);
end;

procedure TFrmACBrMonitor.ACBrRFD1GetKeyRSA(var PrivateKey_RSA: string);
begin
  PrivateKey_RSA := LerChaveSWH;
end;

procedure TFrmACBrMonitor.bRSAPrivKeyClick(Sender: TObject);
Var
  ChavePublica, ChavePrivada : AnsiString ;
begin
  if mRSAKey.Text <> '' then
    raise Exception.Create('Você já possui uma chave RSA.');

  ChavePrivada := '' ;
  ChavePublica := '' ;

  ACBrEAD1.GerarChaves( ChavePublica, ChavePrivada );

  mRSAKey.Lines.Text := StringReplace( ChavePrivada, #10, sLineBreak, [rfReplaceAll] );

(*
  try
     { Executando o "openssl.exe"
       Sintaxe de comandos extraidas de:  http://www.madboa.com/geek/openssl/ }

    RunCommand('openssl', 'genrsa -out id.rsa 1024', True, 0);

    { Lendo a resposta }
    try
      mRSAKey.Clear;
      mRSAKey.Lines.LoadFromFile('id.rsa');
    except
      raise Exception.Create('Erro ao gerar Chave Privada, usando o "openssl"');
    end;
  finally
    DeleteFile('id.rsa');  // Removendo a chave privada do disco ;
  end;
*)
end;

procedure TFrmACBrMonitor.bRSAPubKeyClick(Sender: TObject);
var
  //SL: TStringList;
  Chave, NomeArq : AnsiString ;
begin
  mResp.Lines.Add('Calculando Chave Pública através da Chave Privada');
  Chave  := ACBrEAD1.CalcularChavePublica;
  Chave  := StringReplace( Chave, #10, sLineBreak, [rfReplaceAll] );
  NomeArq:= ExtractFilePath( Application.ExeName ) + 'pub_key.pem' ;

  WriteToTXT( NomeArq, Chave, False, False );
  mResp.Lines.Add( Chave );
  mResp.Lines.Add('Chave pública gravada no arquivo: '+sLineBreak+NomeArq );

(*
  ChDir(ExtractFilePath(Application.ExeName));
  SL := TStringList.Create;
  try
    { Gravando a chave RSA temporariamente no DirLog }
    mRSAKey.Lines.SaveToFile('id.rsa');

     { Executando o "openssl.exe"
       Sintaxe de comandos extraidas de:  http://www.madboa.com/geek/openssl/ }

    RunCommand('openssl', 'rsa -in id.rsa -pubout -out rsakey.pub', True, 0);

    { Lendo a resposta }
    try
      SL.Clear;
      SL.LoadFromFile('rsakey.pub');

      mResp.Lines.AddStrings(SL);
      mResp.Lines.Add('');
      mResp.Lines.Add('Chave pública gravada no arquivo: "rsakey.pub"');
    except
      raise Exception.Create('Erro ao gerar Chave Publica, usando o "openssl"');
    end;
  finally
    DeleteFile('id.rsa');  // Removendo a chave privada do disco ;
  end;
*)
end;

procedure TFrmACBrMonitor.edSH_AplicativoChange(Sender: TObject);
begin
  ACBrRFD1.SH_NomeAplicativo := edSH_Aplicativo.Text;
end;

procedure TFrmACBrMonitor.edSH_NumeroAPChange(Sender: TObject);
begin
  ACBrRFD1.SH_NumeroAplicativo := edSH_NumeroAP.Text;
end;

{------------------------------ Aba Arquivos  --------------------------------}
procedure TFrmACBrMonitor.tsRFDINIShow(Sender: TObject);
begin
  if fsRFDIni = '' then
    mRFDINI.Clear;
end;

procedure TFrmACBrMonitor.bRFDINILerClick(Sender: TObject);
begin
  if fsRFDLeuParams then   { Pode ter modificações pendentes da Aba Usuário }
    ACBrRFD1.GravarINI;

  mRFDINI.Lines.LoadFromFile(ACBrRFD1.ArqINI);
  fsRFDIni := 'acbrrfd';
end;

procedure TFrmACBrMonitor.bRFDIDClick(Sender: TObject);
begin
  mRFDINI.Lines.LoadFromFile(ACBrRFD1.ArqRFDID);
  fsRFDIni := 'rfdid';
end;

procedure TFrmACBrMonitor.bRFDINISalvarClick(Sender: TObject);
begin
  if fsRFDIni = '' then
    exit;

  if fsRFDIni = 'acbrrfd' then
  begin
    try
      mRFDINI.Lines.SaveToFile(ACBrRFD1.ArqINI);
      ACBrRFD1.Desativar;        { Desativa e Ativa para re-ler ACBrRFD.ini }
    finally
      ACBrRFD1.Ativar;
    end;
  end
  else
    mRFDINI.Lines.SaveToFile(ACBrRFD1.ArqRFDID);
end;



{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.cbCHQModeloChange(Sender: TObject);
begin
  try
    ACBrCHQ1.Desativar;
    ACBrCHQ1.Modelo := TACBrCHQModelo(cbCHQModelo.ItemIndex);

    if ACBrCHQ1.Modelo = chqImpressoraECF then
      ACBrCHQ1.ECF := ACBrECF1;
  finally
    cbCHQModelo.ItemIndex := integer(ACBrCHQ1.Modelo);
    cbCHQPorta.Text := ACBrCHQ1.Porta;
  end;

  bCHQTestar.Enabled := (ACBrCHQ1.Modelo <> chqNenhuma);
  cbCHQPorta.Enabled := bCHQTestar.Enabled and
    (ACBrCHQ1.Modelo <> chqImpressoraECF);
  chCHQVerForm.Enabled := bCHQTestar.Enabled;
  gbCHQDados.Enabled := bCHQTestar.Enabled;
  edCHQBemafiINI.Enabled := bCHQTestar.Enabled;
  sbCHQBemafiINI.Enabled := bCHQTestar.Enabled;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.cbGAVModeloChange(Sender: TObject);
begin
  try
    ACBrGAV1.Desativar;
    ACBrGAV1.Modelo := TACBrGAVModelo(cbGAVModelo.ItemIndex);

    if ACBrGAV1.Modelo = gavImpressoraECF then
      ACBrGAV1.ECF := ACBrECF1;
  finally
    cbGAVModelo.ItemIndex := integer(ACBrGAV1.Modelo);
    cbGAVPorta.Text := ACBrGAV1.Porta;
    sedGAVIntervaloAbertura.Value := ACBrGAV1.AberturaIntervalo;
  end;

  AvaliaEstadoTsGAV;
end;

procedure TFrmACBrMonitor.AvaliaEstadoTsGAV;
begin
  bGAVAtivar.Enabled := (ACBrGAV1.Modelo <> gavNenhuma);
  bGAVEstado.Enabled := ACBrGAV1.Ativo;
  bGAVAbrir.Enabled := bGAVEstado.Enabled;
  cbGAVPorta.Enabled := not (ACBrGAV1.Modelo in [gavImpressoraECF, gavNenhuma]);
  cbGAVStrAbre.Enabled := (ACBrGAV1.Modelo = gavImpressoraComum);
  sedGAVIntervaloAbertura.Enabled := bGAVAtivar.Enabled;
  cbGAVAcaoAberturaAntecipada.Enabled := bGAVAtivar.Enabled;

  bGAVAtivar.Glyph := nil;
  if ACBrGAV1.Ativo then
  begin
    bGAVAtivar.Caption := '&Desativar';
    ImageList1.GetBitmap(6, bGAVAtivar.Glyph);
  end
  else
  begin
    bGAVAtivar.Caption := '&Ativar';
    ImageList1.GetBitmap(5, bGAVAtivar.Glyph);
  end;
end;


{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.cbDISModeloChange(Sender: TObject);
begin
  try
    ACBrDIS1.Desativar;
    ACBrDIS1.Modelo := TACBrDISModelo(cbDISModelo.ItemIndex);
  finally
    cbDISModelo.ItemIndex := integer(ACBrDIS1.Modelo);
    cbDISPorta.Text := ACBrDIS1.Porta;
  end;

  bDISTestar.Enabled := (ACBrDIS1.Modelo <> disNenhum);
  bDISLimpar.Enabled := bDISTestar.Enabled;
  bDISAnimar.Enabled := bDISTestar.Enabled;
  seDISPassos.Enabled := bDISTestar.Enabled;
  seDISIntervalo.Enabled := bDISTestar.Enabled;
  cbDISPorta.Enabled := bDISTestar.Enabled and
    (not (ACBrDIS1.Modelo in [disGertecTeclado, disKeytecTeclado]));
  seDISIntByte.Enabled := bDISTestar.Enabled and (not cbDISPorta.Enabled);
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.FormShortCut(Key: integer; Shift: TShiftState;
  var Handled: boolean);
begin
  if (Key = VK_HELP) or (Key = VK_F1) then
    sbSobre.Click;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.bExecECFTesteClick(Sender: TObject);
var
  Arquivo: string;
  OldAtivo: boolean;
begin
  OldAtivo := ACBrECF1.Ativo;
  try
    ACBrECF1.Desativar;
    {$IFDEF LINUX}
    Arquivo := 'ECFTeste';
    {$ELSE}
    Arquivo := 'ECFTeste.exe';
    {$ENDIF}

    Arquivo := ExtractFilePath(Application.ExeName) + Arquivo;

    if not FileExists(Arquivo) then
      MessageDlg('Programa: "' + Arquivo + '" não encontrado.', mtError, [mbOK], 0)
    else
      RunCommand(Arquivo, '', True);
  finally
    ACBrECF1.Ativo := OldAtivo;
  end;

  AvaliaEstadoTsECF;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.sbCHQBemafiINIClick(Sender: TObject);
begin
  OpenDialog1.Filter := 'Arquivos ini|*.ini|Arquivos INI|*.INI';
  OpenDialog1.FileName := edCHQBemafiINI.Text;

  if OpenDialog1.Execute then
  begin
    edCHQBemafiINI.Text := OpenDialog1.FileName;
    ACBrCHQ1.ArquivoBemaFiINI := edCHQBemafiINI.Text;
  end;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.edCHQBemafiINIExit(Sender: TObject);
begin
  ACBrCHQ1.ArquivoBemaFiINI := edCHQBemafiINI.Text;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.ACBrECF1AguardandoRespostaChange(Sender: TObject);
begin
  { ECF sendo usado junto LCB, deve desabilitar o LCB enquando o ECF estiver
    ocupado imprimindo, para evitar de enviar códigos na hora indevida, como
    por exemplo, quando o EDIT / GET do Campos código não está com o FOCO }
  if ACBrLCB1.Ativo then
    if ACBrECF1.AguardandoResposta then
      ACBrLCB1.Intervalo := 0
    else
      ACBrLCB1.Intervalo := sedLCBIntervalo.Value;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.SetDisWorking(const Value: boolean);
begin
  if ACBrLCB1.Ativo then
    if Value then
      ACBrLCB1.Intervalo := 0
    else
      ACBrLCB1.Intervalo := sedLCBIntervalo.Value;

  fsDisWorking := Value;
end;

{---------------------------------- ACBrBAL -----------------------------------}
procedure TFrmACBrMonitor.cbBALModeloChange(Sender: TObject);
begin
  try
    ACBrBAL1.Desativar;
    if cbBALModelo.ItemIndex >= 0 then
      ACBrBAL1.Modelo := TACBrBALModelo(cbBALModelo.ItemIndex)
    else
      ACBrBAL1.Modelo := balNenhum;
  finally
    cbBALModelo.ItemIndex := integer(ACBrBAL1.Modelo);
    cbBALPorta.Text := ACBrBAL1.Porta;
  end;

  AvaliaEstadoTsBAL;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.AvaliaEstadoTsBAL;
begin
  bBALAtivar.Enabled := (ACBrBAL1.Modelo <> balNenhum);
  bBALTestar.Enabled := ACBrBAL1.Ativo;
  cbBALPorta.Enabled := bBALAtivar.Enabled;
  sedBALIntervalo.Enabled := bBALAtivar.Enabled;

  bBALAtivar.Glyph := nil;
  if ACBrBAL1.Ativo then
  begin
    bBALAtivar.Caption := '&Desativar';
    ImageList1.GetBitmap(6, bBALAtivar.Glyph);
  end
  else
  begin
    bBALAtivar.Caption := '&Ativar';
    ImageList1.GetBitmap(5, bBALAtivar.Glyph);
  end;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.cbBALPortaChange(Sender: TObject);
begin
  try
    if ACBrBAL1.Ativo then
      bBALAtivar.Click;

    ACBrBAL1.Porta := cbBALPorta.Text;
  finally
    cbBALPorta.Text := ACBrBAL1.Porta;
  end;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.sedBALIntervaloChanged(Sender: TObject);
begin
  ACBrBal1.Intervalo := sedIntervalo.Value;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.bBALAtivarClick(Sender: TObject);
begin
  if bBALAtivar.Caption = '&Ativar' then
  begin
    ACBrBAL1.Ativar;

    ACBrBAL1.LePeso;
    if ACBrBAL1.UltimaResposta = '' then
    begin
      ACBrBAL1.Desativar;
      mResp.Lines.Add('BAL -> Balança não responde!');
    end;
  end
  else
    ACBrBAL1.Desativar;

  AvaliaEstadoTsBAL;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.bBALTestarClick(Sender: TObject);
begin
  ACBrBAL1.LePeso;
  if ACBrBAL1.UltimaResposta <> '' then
    mResp.Lines.Add(Format('BAL -> Peso Lido: %f', [ACBrBAL1.UltimoPesoLido]))
  else
    mResp.Lines.Add('BAL -> Timeout');
end;

procedure TFrmACBrMonitor.cbETQModeloChange(Sender: TObject);
begin
  try
    ACBrETQ1.Desativar;
    if cbETQModelo.ItemIndex >= 0 then
      ACBrETQ1.Modelo := TACBrETQModelo(cbETQModelo.ItemIndex)
    else
      ACBrETQ1.Modelo := etqNenhum;
  finally
    cbETQModelo.ItemIndex := integer(ACBrETQ1.Modelo);
    cbETQPorta.Text := ACBrETQ1.Porta;
  end;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.cbETQPortaChange(Sender: TObject);
begin
  try
    ACBrETQ1.Porta := cbETQPorta.Text;
  finally
    cbETQPorta.Text := ACBrETQ1.Porta;
  end;
end;


{-------------------------- Terminal de Consulta ------------------------------}
procedure TFrmACBrMonitor.tsTCShow(Sender: TObject);
begin
  AvaliaEstadoTsTC;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.cbxTCModeloChange(Sender: TObject);
begin
  TCPServerTC.Ativo := False;
  TimerTC.Enabled := False;
  AvaliaEstadoTsTC;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.bTCAtivarClick(Sender: TObject);
begin
  if not TCPServerTC.Ativo then
     TCPServerTC.Port := edTCPort.Text;

  if not FileExists(edTCArqPrecos.Text) then
    raise Exception.Create('Arquivo de Preços não encontrado em: [' +
      edTCArqPrecos.Text + ']');

  TCPServerTC.Ativo := (bTCAtivar.Caption = '&Ativar');
  TimerTC.Enabled := TCPServerTC.Ativo;

  AvaliaEstadoTsTC;

  mResp.Lines.Add( 'Servidor de Terminal de Consulta: '+
          IfThen( TCPServerTC.Ativo, 'ATIVADO', 'DESATIVADO' ) );
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.sbTCArqPrecosEditClick(Sender: TObject);
begin
  OpenURL(edTCArqPrecos.Text);
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.AvaliaEstadoTsTC;
begin
  edTCPort.Enabled := (cbxTCModelo.ItemIndex > 0);
  edTCArqPrecos.Enabled := edTCPort.Enabled;
  sbTCArqPrecosEdit.Enabled := edTCPort.Enabled;
  sbTCArqPrecosFind.Enabled := edTCPort.Enabled;

  bTCAtivar.Enabled := edTCPort.Enabled and (StrToIntDef(edTCPort.Text, 0) > 0);

  bTCAtivar.Glyph := nil;
  if TCPServerTC.Ativo then
  begin
    bTCAtivar.Caption := '&Desativar';
    shpTC.Brush.Color := clLime;
    ImageList1.GetBitmap(6, bTCAtivar.Glyph);
  end
  else
  begin
    bTCAtivar.Caption := '&Ativar';
    shpTC.Brush.Color := clRed;
    ImageList1.GetBitmap(5, bTCAtivar.Glyph);
    mTCConexoes.Lines.Clear;
  end;
end;


{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.AddLinesLog;
begin
  if fsLinesLog <> '' then
  begin
    mResp.Lines.Add(fsLinesLog);
    if cbLog.Checked then
      WriteToTXT(ArqLogTXT, fsLinesLog, True, True);
    fsLinesLog := '';
  end;
end;


{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.TimerTCTimer(Sender: TObject);
var
  I: integer;
  AConnection : TTCPBlockSocket ;
begin
  // Verificando se o arquivo de Preços foi atualizado //
  if FileAge(edTCArqPrecos.Text) > fsDTPrecos then
  begin
    fsSLPrecos.Clear;
    fsSLPrecos.LoadFromFile(edTCArqPrecos.Text);
    fsDTPrecos := FileAge(edTCArqPrecos.Text);
  end;

  with TCPServerTC.ThreadList.LockList do
  try
     for I := 0 to Count-1 do
     begin
        AConnection := TACBrTCPServerThread(Items[I]).TCPBlockSocket ;
        Try
           AConnection.Tag := AConnection.Tag + 1 ;
           AConnection.SendString('#live?');
           if AConnection.Tag > 10 then   // 10 Falhas no #live?... desconecte
              AConnection.CloseSocket ;
        except
           AConnection.CloseSocket ;
        end ;
     end ;
  finally
     TCPServerTC.ThreadList.UnlockList;
  end;
end;

{------------------------------------------------------------------------------}
procedure TFrmACBrMonitor.sbTCArqPrecosFindClick(Sender: TObject);
begin
  OpenDialog1.Filter := 'Arquivos txt|*.txt|Arquivos TXT|*.TXT';

  if OpenDialog1.Execute then
  begin
    edTCArqPrecos.Text := OpenDialog1.FileName;
    fsDTPrecos := 0; // Força re-leitura
  end;
end;

(*
procedure TFrmACBrMonitor.TCPServerTCConecta(
  const TCPBlockSocket: TTCPBlockSocket; var Enviar: String);
 Var IP, Resp, Id : String ;
     Indice : Integer ;
begin
  TCPServerTC.OnRecebeDados := nil ;
  try
     TCPBlockSocket.SendString('#ok') ;
     Id := Trim(TCPBlockSocket.RecvPacket(2000)) ;
     TCPBlockSocket.SendString('#alwayslive');
     Resp := Trim(TCPBlockSocket.RecvPacket(2000)) ;
     if Resp <> '#alwayslive_ok' then
     begin
        fsLinesLog := 'Resposta Inválida do T.C.' ;
        AddLinesLog ;
        TCPBlockSocket.CloseSocket ;
     end ;

     IP := TCPBlockSocket.GetRemoteSinIP ;

     Indice := mTCConexoes.Lines.IndexOf( IP ) ;
     if Indice < 0 then
     begin
        mTCConexoes.Lines.Add( IP ) ;
        fsLinesLog := 'Inicio Conexão TC: ['+Id+'] IP: ['+ IP +
                      '] em: ['+FormatDateTime('dd/mm/yy hh:nn:ss', now )+']' ;
        AddLinesLog ;
     end ;
  finally
     TCPServerTC.OnRecebeDados := TCPServerTCRecebeDados ;
  end ;
end;

procedure TFrmACBrMonitor.TCPServerTCDesConecta(
  const TCPBlockSocket: TTCPBlockSocket; Erro: Integer; ErroDesc: String);
 Var IP : String ;
     Indice : Integer ;
begin
  IP  := TCPBlockSocket.GetRemoteSinIP ;
  fsLinesLog := 'Fim Conexão TC IP: ['+ IP + '] em: '+
                FormatDateTime('dd/mm/yy hh:nn:ss', now ) ;
  AddLinesLog ;

  Indice := mTCConexoes.Lines.IndexOf( IP ) ;
  if Indice >= 0 then
     mTCConexoes.Lines.Delete( Indice );
end;

procedure TFrmACBrMonitor.TCPServerTCRecebeDados(
  const TCPBlockSocket: TTCPBlockSocket; const Recebido: String;
  var Enviar: String);
begin
  { Le o que foi enviado atravez da conexao TCP }
  fsTCComando := Trim(Recebido) ;
  if fsTCComando = '' then
     exit ;

  if fsTCComando = '#live' then
     exit ;

  fsLinesLog := 'TC: ['+TCPBlockSocket.GetRemoteSinIP+
                '] RX: <- ['+fsTCComando+']' ;
  AddLinesLog ;

  if copy(fsTCComando,1,1) = '#' then
  begin
     fsTCResposta := '' ;
     BuscaPreco ;

     if fsTCResposta <> '' then
     begin
        TCPBlockSocket.SendString( fsTCResposta ) ;
        fsLinesLog := '     TX: -> ['+fsTCResposta+']' ;
        AddLinesLog ;
     end ;
  end ;
end;
*)
procedure TFrmACBrMonitor.sbCHQSerialClick(Sender: TObject);
begin
  frConfiguraSerial := TfrConfiguraSerial.Create(self);

  try
    if ACBrCHQ1.Ativo then
      ACBrCHQ1.Desativar;

    frConfiguraSerial.Device.Porta := ACBrCHQ1.Device.Porta;
    frConfiguraSerial.cmbPortaSerial.Text := cbCHQPorta.Text;
    frConfiguraSerial.Device.ParamsString := ACBrCHQ1.Device.ParamsString;

    if frConfiguraSerial.ShowModal = mrOk then
    begin
      cbCHQPorta.Text := frConfiguraSerial.Device.Porta;
      ACBrCHQ1.Device.ParamsString := frConfiguraSerial.Device.ParamsString;
    end;
  finally
    FreeAndNil(frConfiguraSerial);
  end;

end;

end.


{$I ACBr.inc}

unit Unit1;

interface

uses IniFiles, ShellAPI, pcnRetConsReciNFe,
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, ExtCtrls, Buttons, ComCtrls, OleCtrls, SHDocVw,
  ACBrNFe, pcnConversao, ACBrNFeDANFEClass, ACBrNFeDANFERave, ACBrUtil,
  pcnNFeW, pcnNFeRTXT, pcnAuxiliar, ACBrDFeUtil,
  XMLIntf, XMLDoc, ACBrNFeDANFERaveCB, BrvXml, Grids, BrvDbGrids, BrvDbGrid, BrvBitBtn, DBXFirebird,
  FMTBcd, DB, SqlExpr, DBClient, Menus, XDBGrids, mxExport, BrvRetCon, BrvCustomEdit, BrvEditNum,
  Mask, BrvCustomMaskEdit, BrvEditDate, IBCustomDataSet, IBTable, IBDatabase, Provider, BrvEdit;

type
  TForm1 = class(TForm)
    OpenDialog1: TOpenDialog;
    ACBrNFe1: TACBrNFe;
    ACBrNFeDANFERave1: TACBrNFeDANFERave;
    BrvXMLNFE: TBrvXML;
    Label29: TLabel;
    DataSource1: TDataSource;
    xml: TClientDataSet;
    PopupMenu1: TPopupMenu;
    Detalhar1: TMenuItem;
    Imprimir1: TMenuItem;
    mxExcel: TmxDataSetExport;
    PopupMenu2: TPopupMenu;
    MenuItem1: TMenuItem;
    MenuItem2: TMenuItem;
    GerarPDF1: TMenuItem;
    Cancelar1: TMenuItem;
    Deletar1: TMenuItem;
    DataSource2: TDataSource;
    SQLConnection1: TSQLConnection;
    DataSetProvider1: TDataSetProvider;
    SQLDataSet1: TSQLDataSet;
    SQLTable1: TSQLTable;
    SQLTable1XMOTIVO: TStringField;
    SQLTable1CHNFE: TStringField;
    SQLTable1DHRECBTO: TStringField;
    SQLTable1NPROT: TStringField;
    SQLTable1IDE_MOD: TStringField;
    SQLTable1IDE_NATOP: TStringField;
    SQLTable1IDE_NNF: TStringField;
    SQLTable1IDE_SERIE: TStringField;
    SQLTable1IDE_TPIMP: TStringField;
    SQLTable1IDE_TPEMIS: TStringField;
    SQLTable1IDE_CDV: TStringField;
    SQLTable1IDE_TPAMB: TStringField;
    SQLTable1IDE_DEMI: TStringField;
    SQLTable1IDE_DSAIENT: TStringField;
    SQLTable1IDE_HSAIENT: TStringField;
    SQLTable1EMIT_XNOME: TStringField;
    SQLTable1EMIT_CNPJ: TStringField;
    SQLTable1EMIT_IE: TStringField;
    SQLTable1EMIT_CRT: TStringField;
    SQLTable1ENDEREMIT_XLGR: TStringField;
    SQLTable1ENDEREMIT_NRO: TStringField;
    SQLTable1ENDEREMIT_XBAIRRO: TStringField;
    SQLTable1ENDEREMIT_XMUN: TStringField;
    SQLTable1ENDEREMIT_CEP: TStringField;
    SQLTable1ENDEREMIT_FONE: TStringField;
    SQLTable1ENDEREMIT_UF: TStringField;
    SQLTable1DEST_XNOME: TStringField;
    SQLTable1DEST_CNPJ: TStringField;
    SQLTable1DEST_CPF: TStringField;
    SQLTable1DEST_IE: TStringField;
    SQLTable1DEST_EMAIL: TStringField;
    SQLTable1ENDERDEST_XLGR: TStringField;
    SQLTable1ENDERDEST_NRO: TStringField;
    SQLTable1ENDERDEST_XBAIRRO: TStringField;
    SQLTable1ENDERDEST_XMUN: TStringField;
    SQLTable1ENDERDEST_CEP: TStringField;
    SQLTable1ENDERDEST_FONE: TStringField;
    SQLTable1ENDERDEST_UF: TStringField;
    SQLTable1XML: TStringField;
    SQLTable1STMANIFESTO: TIntegerField;
    Timer1: TTimer;
    Label40: TLabel;
    ConfirmarOperao1: TMenuItem;
    CidenciadaOperao1: TMenuItem;
    DesconhecimentoOperao1: TMenuItem;
    OperaoNoRealizada1: TMenuItem;
    EnviarEmail1: TMenuItem;
    CartadeCorreo1: TMenuItem;
    SQLDataSet1XMOTIVO: TStringField;
    SQLDataSet1CHNFE: TStringField;
    SQLDataSet1DHRECBTO: TStringField;
    SQLDataSet1NPROT: TStringField;
    SQLDataSet1IDE_MOD: TStringField;
    SQLDataSet1IDE_NATOP: TStringField;
    SQLDataSet1IDE_NNF: TStringField;
    SQLDataSet1IDE_SERIE: TStringField;
    SQLDataSet1IDE_TPIMP: TStringField;
    SQLDataSet1IDE_TPEMIS: TStringField;
    SQLDataSet1IDE_CDV: TStringField;
    SQLDataSet1IDE_TPAMB: TStringField;
    SQLDataSet1IDE_DEMI: TStringField;
    SQLDataSet1IDE_DSAIENT: TStringField;
    SQLDataSet1IDE_HSAIENT: TStringField;
    SQLDataSet1EMIT_XNOME: TStringField;
    SQLDataSet1EMIT_CNPJ: TStringField;
    SQLDataSet1EMIT_IE: TStringField;
    SQLDataSet1EMIT_CRT: TStringField;
    SQLDataSet1ENDEREMIT_XLGR: TStringField;
    SQLDataSet1ENDEREMIT_NRO: TStringField;
    SQLDataSet1ENDEREMIT_XBAIRRO: TStringField;
    SQLDataSet1ENDEREMIT_XMUN: TStringField;
    SQLDataSet1ENDEREMIT_CEP: TStringField;
    SQLDataSet1ENDEREMIT_FONE: TStringField;
    SQLDataSet1ENDEREMIT_UF: TStringField;
    SQLDataSet1DEST_XNOME: TStringField;
    SQLDataSet1DEST_CNPJ: TStringField;
    SQLDataSet1DEST_CPF: TStringField;
    SQLDataSet1DEST_IE: TStringField;
    SQLDataSet1DEST_EMAIL: TStringField;
    SQLDataSet1ENDERDEST_XLGR: TStringField;
    SQLDataSet1ENDERDEST_NRO: TStringField;
    SQLDataSet1ENDERDEST_XBAIRRO: TStringField;
    SQLDataSet1ENDERDEST_XMUN: TStringField;
    SQLDataSet1ENDERDEST_CEP: TStringField;
    SQLDataSet1ENDERDEST_FONE: TStringField;
    SQLDataSet1ENDERDEST_UF: TStringField;
    SQLDataSet1XML: TStringField;
    SQLDataSet1STMANIFESTO: TIntegerField;
    DataSetProvider2: TDataSetProvider;
    PageControl1: TPageControl;
    TabSheet14: TTabSheet;
    Panel7: TPanel;
    GroupBox1: TGroupBox;
    CheckBox1: TCheckBox;
    CheckBox2: TCheckBox;
    CheckBox3: TCheckBox;
    CheckBox4: TCheckBox;
    GroupBox7: TGroupBox;
    Label31: TLabel;
    Label32: TLabel;
    BrvEditDate1: TBrvEditDate;
    BrvEditDate2: TBrvEditDate;
    GroupBox8: TGroupBox;
    BrvEditNum1: TBrvEditNum;
    GroupBox9: TGroupBox;
    Label33: TLabel;
    Label34: TLabel;
    BrvEditNum2: TBrvEditNum;
    BrvEditNum3: TBrvEditNum;
    GroupBox10: TGroupBox;
    CheckBox7: TCheckBox;
    CheckBox8: TCheckBox;
    GroupBox11: TGroupBox;
    CheckBox5: TCheckBox;
    CheckBox6: TCheckBox;
    Panel8: TPanel;
    BtnExcel: TBrvBitBtn;
    Panel9: TPanel;
    Panel10: TPanel;
    BrvDbGrid2: TBrvDbGrid;
    TabSheet15: TTabSheet;
    Panel2: TPanel;
    Panel3: TPanel;
    PageControl3: TPageControl;
    TabSheet12: TTabSheet;
    Panel4: TPanel;
    Label36: TLabel;
    Edit2: TEdit;
    BrvDbGrid1: TBrvDbGrid;
    TabSheet13: TTabSheet;
    Panel6: TPanel;
    Label30: TLabel;
    Edit1: TEdit;
    BrvBitBtn1: TBrvBitBtn;
    TabSheet1: TTabSheet;
    PageControl2: TPageControl;
    TabSheet5: TTabSheet;
    MemoResp: TMemo;
    TabSheet6: TTabSheet;
    WBResposta: TWebBrowser;
    TabSheet8: TTabSheet;
    memoLog: TMemo;
    TabSheet9: TTabSheet;
    trvwNFe: TTreeView;
    TabSheet10: TTabSheet;
    memoRespWS: TMemo;
    Dados: TTabSheet;
    MemoDados: TMemo;
    TabSheet11: TTabSheet;
    TreeViewRetornoConsulta: TTreeView;
    Panel1: TPanel;
    btnStatusServ: TButton;
    btnConsultarChave: TButton;
    btnCancelarChave: TButton;
    btnConsultarRecibo: TButton;
    btnConsCad: TButton;
    btnManifDestConfirmacao: TButton;
    btnEnviarEmail: TButton;
    btnCartadeCorrecao: TButton;
    btnNfeDestinadas: TButton;
    Button1: TButton;
    TabSheet2: TTabSheet;
    Panel5: TPanel;
    PageControl4: TPageControl;
    TabSheet4: TTabSheet;
    Panel11: TPanel;
    Label76: TLabel;
    Edit35: TEdit;
    BrvDbGrid4: TBrvDbGrid;
    TabSheet7: TTabSheet;
    Panel12: TPanel;
    Label77: TLabel;
    Edit36: TEdit;
    BrvBitBtn5: TBrvBitBtn;
    TabSheet16: TTabSheet;
    PageControl5: TPageControl;
    TabSheet17: TTabSheet;
    Memo2: TMemo;
    TabSheet19: TTabSheet;
    WebBrowser1: TWebBrowser;
    TabSheet20: TTabSheet;
    Memo3: TMemo;
    TabSheet21: TTabSheet;
    TreeView1: TTreeView;
    TabSheet22: TTabSheet;
    Memo4: TMemo;
    TabSheet23: TTabSheet;
    Memo5: TMemo;
    TabSheet24: TTabSheet;
    TreeView2: TTreeView;
    TabSheet26: TTabSheet;
    PageControl6: TPageControl;
    TabSheet27: TTabSheet;
    SpeedButton11: TSpeedButton;
    SpeedButton12: TSpeedButton;
    SpeedButton13: TSpeedButton;
    Label78: TLabel;
    Label79: TLabel;
    Label80: TLabel;
    GroupBox18: TGroupBox;
    Label81: TLabel;
    Label82: TLabel;
    SpeedButton14: TSpeedButton;
    Label83: TLabel;
    SpeedButton15: TSpeedButton;
    edtCaminho: TEdit;
    edtSenha: TEdit;
    edtNumSerie: TEdit;
    GroupBox19: TGroupBox;
    Label84: TLabel;
    SpeedButton16: TSpeedButton;
    SpeedButton17: TSpeedButton;
    edtLogoMarca: TEdit;
    edtPathLogs: TEdit;
    ckSalvar: TCheckBox;
    rgTipoDanfe: TRadioGroup;
    rgFormaEmissao: TRadioGroup;
    GroupBox20: TGroupBox;
    Label85: TLabel;
    ckVisualizar: TCheckBox;
    cbUF: TComboBox;
    rgTipoAmb: TRadioGroup;
    GroupBox21: TGroupBox;
    Label86: TLabel;
    Label87: TLabel;
    Label88: TLabel;
    Label89: TLabel;
    edtProxyHost: TEdit;
    edtProxyPorta: TEdit;
    edtProxyUser: TEdit;
    edtProxySenha: TEdit;
    GroupBox22: TGroupBox;
    Label90: TLabel;
    Label91: TLabel;
    Label92: TLabel;
    Label93: TLabel;
    Label94: TLabel;
    Label95: TLabel;
    Label96: TLabel;
    Label97: TLabel;
    Label98: TLabel;
    Label99: TLabel;
    Label100: TLabel;
    Label101: TLabel;
    Label102: TLabel;
    edtEmitCNPJ: TEdit;
    edtEmitIE: TEdit;
    edtEmitRazao: TEdit;
    edtEmitFantasia: TEdit;
    edtEmitFone: TEdit;
    edtEmitCEP: TEdit;
    edtEmitLogradouro: TEdit;
    edtEmitNumero: TEdit;
    edtEmitComp: TEdit;
    edtEmitBairro: TEdit;
    edtEmitCodCidade: TEdit;
    edtEmitCidade: TEdit;
    edtEmitUF: TEdit;
    GroupBox23: TGroupBox;
    Label103: TLabel;
    Label104: TLabel;
    Label105: TLabel;
    Label106: TLabel;
    Label107: TLabel;
    Label108: TLabel;
    edtSmtpHost: TEdit;
    edtSmtpPort: TEdit;
    edtSmtpUser: TEdit;
    edtSmtpPass: TEdit;
    edtEmailAssunto: TEdit;
    cbEmailSSL: TCheckBox;
    Memo6: TMemo;
    RadioGroup2: TRadioGroup;
    EdtDsArquiv: TEdit;
    Edit3: TEdit;
    BitBtn2: TBitBtn;
    RadioGroup12: TRadioGroup;
    Edit4: TEdit;
    Button2: TButton;
    btnConsultar: TButton;
    Button3: TButton;
    btnCancCTe: TButton;
    Button4: TButton;
    btnGerarPDF: TButton;
    Button5: TButton;
    btnEnvDPEC: TButton;
    btnConsultarDPEC: TButton;
    btnImportarXML: TButton;
    btnValidarXML: TButton;
    Button6: TButton;
    Button7: TButton;
    GroupBox12: TGroupBox;
    CheckBox9: TCheckBox;
    CheckBox10: TCheckBox;
    CheckBox11: TCheckBox;
    CheckBox15: TCheckBox;
    TabSheet39: TTabSheet;
    Panel16: TPanel;
    DbgNotas: TBrvDbGrid;
    DbgEmails: TBrvDbGrid;
    mmEmailMsg: TMemo;
    Panel18: TPanel;
    LblQtReg: TLabel;
    BtnEnviar: TBrvBitBtn;
    Panel19: TPanel;
    Label48: TLabel;
    BrvBitBtn7: TBrvBitBtn;
    BrvEdit1: TBrvEdit;
    BrvBitBtn8: TBrvBitBtn;
    BrvBitBtn10: TBrvBitBtn;
    GroupBox13: TGroupBox;
    CheckBox16: TCheckBox;
    CheckBox17: TCheckBox;
    GroupBox14: TGroupBox;
    CheckBox18: TCheckBox;
    CheckBox19: TCheckBox;
    CheckBox20: TCheckBox;
    CheckBox21: TCheckBox;
    GroupBox15: TGroupBox;
    Label47: TLabel;
    Label49: TLabel;
    BrvEditDate3: TBrvEditDate;
    BrvEditDate4: TBrvEditDate;
    GroupBox16: TGroupBox;
    BrvEditNum4: TBrvEditNum;
    GroupBox17: TGroupBox;
    BrvEditNum5: TBrvEditNum;
    Label50: TLabel;
    EnviarDPEC1: TMenuItem;
    TabSheet40: TTabSheet;
    Panel17: TPanel;
    GroupBox24: TGroupBox;
    CheckBox22: TCheckBox;
    CheckBox23: TCheckBox;
    CheckBox24: TCheckBox;
    CheckBox25: TCheckBox;
    GroupBox25: TGroupBox;
    Label51: TLabel;
    Label52: TLabel;
    BrvEditDate5: TBrvEditDate;
    BrvEditDate6: TBrvEditDate;
    GroupBox26: TGroupBox;
    BrvEditNum6: TBrvEditNum;
    GroupBox27: TGroupBox;
    Label53: TLabel;
    Label54: TLabel;
    BrvEditNum7: TBrvEditNum;
    BrvEditNum8: TBrvEditNum;
    GroupBox28: TGroupBox;
    CheckBox26: TCheckBox;
    CheckBox27: TCheckBox;
    CheckBox28: TCheckBox;
    GroupBox29: TGroupBox;
    CheckBox29: TCheckBox;
    CheckBox30: TCheckBox;
    GroupBox30: TGroupBox;
    CheckBox31: TCheckBox;
    CheckBox32: TCheckBox;
    CheckBox33: TCheckBox;
    BrvDbGrid8: TBrvDbGrid;
    TabSheet3: TTabSheet;
    Panel13: TPanel;
    btnConsultarNFSePeriodo: TButton;
    btnGerarRPS: TButton;
    btnCancNFSe: TButton;
    btnGerarEnviarNFSe: TButton;
    btnConsultarNFSeRPS: TButton;
    btnImprimir: TButton;
    btnGerarLoteRPS: TButton;
    PageControl7: TPageControl;
    TabSheet29: TTabSheet;
    Panel15: TPanel;
    Label46: TLabel;
    Edit6: TEdit;
    BrvBitBtn6: TBrvBitBtn;
    PageControl8: TPageControl;
    TabSheet31: TTabSheet;
    Memo1: TMemo;
    TabSheet32: TTabSheet;
    WebBrowser2: TWebBrowser;
    TabSheet33: TTabSheet;
    Memo7: TMemo;
    TabSheet34: TTabSheet;
    TreeView3: TTreeView;
    TabSheet35: TTabSheet;
    Memo8: TMemo;
    TabSheet36: TTabSheet;
    Memo9: TMemo;
    TabSheet37: TTabSheet;
    TreeView4: TTreeView;
    Label57: TLabel;
    Panel20: TPanel;
    BrvBitBtn2: TBrvBitBtn;
    BrvBitBtn3: TBrvBitBtn;
    BrvBitBtn4: TBrvBitBtn;
    BrvBitBtn9: TBrvBitBtn;
    Panel14: TPanel;
    Label41: TLabel;
    Label42: TLabel;
    Label43: TLabel;
    Label44: TLabel;
    Label45: TLabel;
    Label35: TLabel;
    Label55: TLabel;
    Label56: TLabel;
    Label58: TLabel;
    BrvRetCon2: TBrvRetCon;
    BrvRetCon3: TBrvRetCon;
    BrvRetCon4: TBrvRetCon;
    BrvRetCon5: TBrvRetCon;
    BrvRetCon1: TBrvRetCon;
    EdtQtdeReg: TBrvRetCon;
    BrvRetCon6: TBrvRetCon;
    BrvRetCon7: TBrvRetCon;
    BrvRetCon9: TBrvRetCon;
    GroupBox31: TGroupBox;
    CheckBox34: TCheckBox;
    CheckBox35: TCheckBox;
    ExportarTXT1: TMenuItem;
    BrvBitBtn11: TBrvBitBtn;
    TabSheet18: TTabSheet;
    TabSheet25: TTabSheet;
    TabSheet28: TTabSheet;
    TabSheet30: TTabSheet;
    procedure sbtnCaminhoCertClick(Sender: TObject);
    procedure sbtnLogoMarcaClick(Sender: TObject);
    procedure sbtnPathSalvarClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure btnSalvarConfigClick(Sender: TObject);
    procedure btnStatusServClick(Sender: TObject);
    procedure btnCriarEnviarClick(Sender: TObject);
    procedure btnInutilizarClick(Sender: TObject);
    procedure ACBrNFe1StatusChange(Sender: TObject);
    procedure sbtnGetCertClick(Sender: TObject);
    procedure btnConsCadClick(Sender: TObject);
    procedure btnEnviarEmailClick(Sender: TObject);
    procedure btnConsultarReciboClick(Sender: TObject);
    procedure ACBrNFe1GerarLog(const Mensagem: String);
    procedure btnImportarXMLClick(Sender: TObject);
    procedure lblMouseEnter(Sender: TObject);
    procedure lblMouseLeave(Sender: TObject);
    procedure btnConsultarChaveClick(Sender: TObject);
    procedure btnCancelarChaveClick(Sender: TObject);
    procedure btnGerarTXTClick(Sender: TObject);
    procedure btnCarregarXMLEnviarClick(Sender: TObject);
    procedure btnCartadeCorrecaoClick(Sender: TObject);
    procedure btnValidarAssinaturaClick(Sender: TObject);
    procedure btnManifDestConfirmacaoClick(Sender: TObject);
    procedure btnNfeDestinadasClick(Sender: TObject);
    procedure btnImprimirCCeClick(Sender: TObject);
    procedure btnEnviarEventoClick(Sender: TObject);
    procedure btnCriarEnviarNFCeClick(Sender: TObject);
    procedure tsNFeEnter(Sender: TObject);
    procedure tsNFCeEnter(Sender: TObject);
    procedure Button2Click(Sender: TObject);
    procedure BrvBitBtn1Click(Sender: TObject);
    procedure Button1Click(Sender: TObject);
    procedure Timer1Timer(Sender: TObject);

  private
    { Private declarations }
    procedure GravarConfiguracao ;
 //   procedure GerarNFCe(NumNFe : String);
    procedure LoadXML(MyMemo: TMemo; MyWebBrowser: TWebBrowser);

    procedure LoadConsulta201(XML: string);
  public
    { Public declarations }
   procedure gerarDadosInicial();
  end;

var
  Form1    : TForm1;
  NrSenha  : String;
  NrCertif : String;
  CdEstado : String;
  NrChaDoc : String;
  CjEmpres : String;
  TpAmbiente : TpcnTipoEmissao;
  BoVisualizar : boolean;
  gTipe : Integer;

implementation

uses FileCtrl, pcnNFe, ufrmStatus, ACBrNFeNotasFiscais, DateUtils, ACBrNFeUtil, UfrmFunctions;

const
  SELDIRHELP = 1000;

{$R *.dfm}

procedure TForm1.gerarDadosInicial();
var
   OK : boolean;
begin
      NrSenha  := edtSenha.Text;
      NrCertif := edtNumSerie.Text;
      CdEstado := cbUF.Text;
      NrChaDoc := Edit2.Text;
      CjEmpres := edtEmitCNPJ.Text;
      TpAmbiente  := StrToTpEmis(OK,IntToStr(rgFormaEmissao.ItemIndex+1));
      BoVisualizar := ckSalvar.Checked;
end;


procedure TForm1.GravarConfiguracao;
Var IniFile : String ;
    Ini     : TIniFile ;
    StreamMemo : TMemoryStream;
begin
  IniFile := ChangeFileExt( Application.ExeName, '.ini') ;

  Ini := TIniFile.Create( IniFile );
  try
      Ini.WriteString( 'Certificado','Caminho' ,edtCaminho.Text) ;
      Ini.WriteString( 'Certificado','Senha'   ,edtSenha.Text) ;
      Ini.WriteString( 'Certificado','NumSerie',edtNumSerie.Text) ;

      Ini.WriteInteger( 'Geral','DANFE'       ,rgTipoDanfe.ItemIndex) ;
      Ini.WriteInteger( 'Geral','FormaEmissao',rgFormaEmissao.ItemIndex) ;
      Ini.WriteString( 'Geral','LogoMarca'   ,edtLogoMarca.Text) ;
      Ini.WriteBool(   'Geral','Salvar'      ,ckSalvar.Checked) ;
      Ini.WriteString( 'Geral','PathSalvar'  ,edtPathLogs.Text) ;

      Ini.WriteString( 'WebService','UF'        ,cbUF.Text) ;
      Ini.WriteInteger( 'WebService','Ambiente'  ,rgTipoAmb.ItemIndex) ;
      Ini.WriteBool(   'WebService','Visualizar',ckVisualizar.Checked) ;

      Ini.WriteString( 'Proxy','Host'   ,edtProxyHost.Text) ;
      Ini.WriteString( 'Proxy','Porta'  ,edtProxyPorta.Text) ;
      Ini.WriteString( 'Proxy','User'   ,edtProxyUser.Text) ;
      Ini.WriteString( 'Proxy','Pass'   ,edtProxySenha.Text) ;

      Ini.WriteString( 'Emitente','CNPJ'       ,edtEmitCNPJ.Text) ;
      Ini.WriteString( 'Emitente','IE'         ,edtEmitIE.Text) ;
      Ini.WriteString( 'Emitente','RazaoSocial',edtEmitRazao.Text) ;
      Ini.WriteString( 'Emitente','Fantasia'   ,edtEmitFantasia.Text) ;
      Ini.WriteString( 'Emitente','Fone'       ,edtEmitFone.Text) ;
      Ini.WriteString( 'Emitente','CEP'        ,edtEmitCEP.Text) ;
      Ini.WriteString( 'Emitente','Logradouro' ,edtEmitLogradouro.Text) ;
      Ini.WriteString( 'Emitente','Numero'     ,edtEmitNumero.Text) ;
      Ini.WriteString( 'Emitente','Complemento',edtEmitComp.Text) ;
      Ini.WriteString( 'Emitente','Bairro'     ,edtEmitBairro.Text) ;
      Ini.WriteString( 'Emitente','CodCidade'  ,edtEmitCodCidade.Text) ;
      Ini.WriteString( 'Emitente','Cidade'     ,edtEmitCidade.Text) ;
      Ini.WriteString( 'Emitente','UF'         ,edtEmitUF.Text) ;

      Ini.WriteString( 'Email','Host'    ,edtSmtpHost.Text) ;
      Ini.WriteString( 'Email','Port'    ,edtSmtpPort.Text) ;
      Ini.WriteString( 'Email','User'    ,edtSmtpUser.Text) ;
      Ini.WriteString( 'Email','Pass'    ,edtSmtpPass.Text) ;
      Ini.WriteString( 'Email','Assunto' ,edtEmailAssunto.Text) ;
      Ini.WriteBool(   'Email','SSL'     ,cbEmailSSL.Checked ) ;

      Ini.WriteString( 'Import','DiretorioImportacao' ,EdtDsArquiv.Text) ;
      Ini.WriteString( 'Import','DiretorioExportacao' ,Edit3.Text) ;
      Ini.WriteString( 'Import','TempoImpotacao'      ,Edit4.Text) ;
      Ini.WriteString( 'Import','Importacao'          ,IntToStr(RadioGroup2.ItemIndex)) ;

      StreamMemo := TMemoryStream.Create;
      mmEmailMsg.Lines.SaveToStream(StreamMemo);
      StreamMemo.Seek(0,soFromBeginning);
      Ini.WriteBinaryStream( 'Email','Mensagem',StreamMemo) ;
      StreamMemo.Free;
  finally
     Ini.Free ;
  end;

end;

//procedure TForm1.LerConfiguracao;
//Var IniFile  : String ;
//    Ini     : TIniFile ;
//    Ok : Boolean;
//    StreamMemo : TMemoryStream;
//begin
//  IniFile := ChangeFileExt( Application.ExeName, '.ini') ;
//
//  Ini := TIniFile.Create( IniFile );
//  try
//      {$IFDEF ACBrNFeOpenSSL}
//         edtCaminho.Text  := Ini.ReadString( 'Certificado','Caminho' ,'') ;
//         edtSenha.Text    := Ini.ReadString( 'Certificado','Senha'   ,'') ;
//         ACBrNFe1.Configuracoes.Certificados.Certificado  := edtCaminho.Text;
//         ACBrNFe1.Configuracoes.Certificados.Senha        := edtSenha.Text;
//      {$ELSE}
//         edtNumSerie.Text := Ini.ReadString( 'Certificado','NumSerie','') ;
//         ACBrNFe1.Configuracoes.Certificados.NumeroSerie := edtNumSerie.Text;
//         edtNumSerie.Text := ACBrNFe1.Configuracoes.Certificados.NumeroSerie;
//      {$ENDIF}
//
//      rgFormaEmissao.ItemIndex := Ini.ReadInteger( 'Geral','FormaEmissao',0) ;
//      ckSalvar.Checked    := Ini.ReadBool(   'Geral','Salvar'      ,True) ;
//      edtPathLogs.Text    := Ini.ReadString( 'Geral','PathSalvar'  ,'') ;
//      ACBrNFe1.Configuracoes.Geral.FormaEmissao := StrToTpEmis(OK,IntToStr(rgFormaEmissao.ItemIndex+1));
//      ACBrNFe1.Configuracoes.Geral.Salvar       := ckSalvar.Checked;
//      ACBrNFe1.Configuracoes.Geral.PathSalvar   := edtPathLogs.Text;
//
//      cbUF.ItemIndex       := cbUF.Items.IndexOf(Ini.ReadString( 'WebService','UF','SP')) ;
//      rgTipoAmb.ItemIndex  := Ini.ReadInteger( 'WebService','Ambiente'  ,0) ;
//      ckVisualizar.Checked :=Ini.ReadBool(    'WebService','Visualizar',False) ;
//      ACBrNFe1.Configuracoes.WebServices.UF         := cbUF.Text;
//      ACBrNFe1.Configuracoes.WebServices.Ambiente   := StrToTpAmb(Ok,IntToStr(rgTipoAmb.ItemIndex+1));
//      ACBrNFe1.Configuracoes.WebServices.Visualizar := ckVisualizar.Checked;
//
//      edtProxyHost.Text  := Ini.ReadString( 'Proxy','Host'   ,'') ;
//      edtProxyPorta.Text := Ini.ReadString( 'Proxy','Porta'  ,'') ;
//      edtProxyUser.Text  := Ini.ReadString( 'Proxy','User'   ,'') ;
//      edtProxySenha.Text := Ini.ReadString( 'Proxy','Pass'   ,'') ;
//      ACBrNFe1.Configuracoes.WebServices.ProxyHost := edtProxyHost.Text;
//      ACBrNFe1.Configuracoes.WebServices.ProxyPort := edtProxyPorta.Text;
//      ACBrNFe1.Configuracoes.WebServices.ProxyUser := edtProxyUser.Text;
//      ACBrNFe1.Configuracoes.WebServices.ProxyPass := edtProxySenha.Text;
//
//      rgTipoDanfe.ItemIndex     := Ini.ReadInteger( 'Geral','DANFE'       ,0) ;
//      edtLogoMarca.Text         := Ini.ReadString( 'Geral','LogoMarca'   ,'') ;
//      if ACBrNFe1.DANFE <> nil then
//       begin
//         ACBrNFe1.DANFE.TipoDANFE  := StrToTpImp(OK,IntToStr(rgTipoDanfe.ItemIndex+1));
//         ACBrNFe1.DANFE.Logo       := edtLogoMarca.Text;
//       end;
//
//      edtEmitCNPJ.Text       := Ini.ReadString( 'Emitente','CNPJ'       ,'') ;
//      edtEmitIE.Text         := Ini.ReadString( 'Emitente','IE'         ,'') ;
//      edtEmitRazao.Text      := Ini.ReadString( 'Emitente','RazaoSocial','') ;
//      edtEmitFantasia.Text   := Ini.ReadString( 'Emitente','Fantasia'   ,'') ;
//      edtEmitFone.Text       := Ini.ReadString( 'Emitente','Fone'       ,'') ;
//      edtEmitCEP.Text        := Ini.ReadString( 'Emitente','CEP'        ,'') ;
//      edtEmitLogradouro.Text := Ini.ReadString( 'Emitente','Logradouro' ,'') ;
//      edtEmitNumero.Text     := Ini.ReadString( 'Emitente','Numero'     ,'') ;
//      edtEmitComp.Text       := Ini.ReadString( 'Emitente','Complemento','') ;
//      edtEmitBairro.Text     := Ini.ReadString( 'Emitente','Bairro'     ,'') ;
//      edtEmitCodCidade.Text  := Ini.ReadString( 'Emitente','CodCidade'  ,'') ;
//      edtEmitCidade.Text     :=Ini.ReadString( 'Emitente','Cidade'     ,'') ;
//      edtEmitUF.Text         := Ini.ReadString( 'Emitente','UF'         ,'') ;
//
//      edtSmtpHost.Text      := Ini.ReadString( 'Email','Host'   ,'') ;
//      edtSmtpPort.Text      := Ini.ReadString( 'Email','Port'   ,'') ;
//      edtSmtpUser.Text      := Ini.ReadString( 'Email','User'   ,'') ;
//      edtSmtpPass.Text      := Ini.ReadString( 'Email','Pass'   ,'') ;
//      edtEmailAssunto.Text  := Ini.ReadString( 'Email','Assunto','') ;
//      cbEmailSSL.Checked    := Ini.ReadBool(   'Email','SSL'    ,False) ;
//
//      EdtDsArquiv.Text      := Ini.ReadString( 'Import','DiretorioImportacao'   ,'') ;
//      Edit3.Text            := Ini.ReadString( 'Import','DiretorioExportacao'   ,'') ;
//      Edit4.Text            := Ini.ReadString( 'Import','TempoImpotacao'   ,'') ;
//      RadioGroup2.ItemIndex := StrToInt(Ini.ReadString( 'Import','Importacao'   ,'')) ;
//
//
//      StreamMemo := TMemoryStream.Create;
//      Ini.ReadBinaryStream( 'Email','Mensagem',StreamMemo) ;
//      mmEmailMsg.Lines.LoadFromStream(StreamMemo);
//      StreamMemo.Free;
//  finally
//     Ini.Free ;
//  end;
//
//end;

procedure TForm1.LoadConsulta201(XML: String);
var
  DOM: IXMLDocument;
  lXML: String;

  procedure AddNodes(XMLNode: IXMLNode; TreeNode: TTreeNode);
  var
    Index: Integer;
    NewNode: TTreeNode;
    Value: string;
  begin
    if XMLNode.nodeType in [ntTEXT, ntCDATA, ntCOMMENT] then
      Value := XMLNode.text
    else
      Value := XMLNode.nodeName;
    NewNode := TreeViewRetornoConsulta.Items.AddChild(TreeNode, {XMLNode.NodeName +} ' ' + Value);
    for Index := 0 to XMLNode.childNodes.Count - 1 do
      AddNodes(XMLNode.childNodes[Index], NewNode);
  end;

  function ReplaceStr( Fonte, De, Para:string ):string;
  begin
    result:=fonte;
    while pos(de,result) <> 0 do
      result:=copy(result, 1, pos(de,result)-1 )+Para+copy(result,pos(de,result)+length(de),length(result) );
  end;

  function LimpaXML(XML: String; TagRemover:String): String;
  begin
    Result := XML;
    while pos('<'+TagRemover,Result) <> 0 do
    begin
      Result := ReplaceStr(Result,
                           '<'+TagRemover+
                              RetornarConteudoEntre(Result,'<'+TagRemover,'</'+TagRemover+'>')+
                           '</'+TagRemover+'>','');
    end;
  end;
begin
  DOM := TXMLDocument.Create(nil);
  try
    lXML := LimpaXML(XML,'Signature');
    DOM.LoadFromXML(lXML);
    DOM.Active := True;
    TreeViewRetornoConsulta.Items.BeginUpdate;
    TreeViewRetornoConsulta.Items.Clear;
    AddNodes(dom.DocumentElement, nil);
    TreeViewRetornoConsulta.TopItem := TreeViewRetornoConsulta.Items[0];
  finally
    TreeViewRetornoConsulta.Items.EndUpdate;
  end;
end;

procedure TForm1.LoadXML(MyMemo: TMemo; MyWebBrowser: TWebBrowser);
begin
  MyMemo.Lines.SaveToFile(PathWithDelim(ExtractFileDir(application.ExeName))+'temp.xml');
  MyWebBrowser.Navigate(PathWithDelim(ExtractFileDir(application.ExeName))+'temp.xml');
end;

procedure TForm1.sbtnCaminhoCertClick(Sender: TObject);
begin
  OpenDialog1.Title := 'Selecione o Certificado';
  OpenDialog1.DefaultExt := '*.pfx';
  OpenDialog1.Filter := 'Arquivos PFX (*.pfx)|*.pfx|Todos os Arquivos (*.*)|*.*';
  OpenDialog1.InitialDir := ExtractFileDir(application.ExeName);
  if OpenDialog1.Execute then
  begin
    edtCaminho.Text := OpenDialog1.FileName;
  end;
end;

procedure TForm1.sbtnLogoMarcaClick(Sender: TObject);
begin
  OpenDialog1.Title := 'Selecione o Logo';
  OpenDialog1.DefaultExt := '*.bmp';
  OpenDialog1.Filter := 'Arquivos BMP (*.bmp)|*.bmp|Todos os Arquivos (*.*)|*.*';
  OpenDialog1.InitialDir := ExtractFileDir(application.ExeName);
  if OpenDialog1.Execute then
  begin
    edtLogoMarca.Text := OpenDialog1.FileName;
  end;
end;

procedure TForm1.sbtnPathSalvarClick(Sender: TObject);
var
  Dir: string;
begin
  if Length(edtPathLogs.Text) <= 0 then
     Dir := ExtractFileDir(application.ExeName)
  else
     Dir := edtPathLogs.Text;

  if SelectDirectory(Dir, [sdAllowCreate, sdPerformCreate, sdPrompt],SELDIRHELP) then
    edtPathLogs.Text := Dir;
end;

procedure TForm1.Timer1Timer(Sender: TObject);
var
SR: TSearchRec;
i: integer;
destino,origem:String;
begin

      xml := ListarXmlDiretorio(NrSenha,NrCertif,CdEstado,EdtDsArquiv.Text,EdtDsArquiv.Text+'\teste',TpAmbiente,BoVisualizar);
      if xml <> nil then
      begin
            xml.Insert;
    //        xml.Open;
            DataSource1.DataSet   := xml;
            xml.ApplyUpdates(1);
            MemoResp.Lines.Text   :=  UTF8Encode(xml.FieldByName('xml').AsString);
            memoRespWS.Lines.Text :=  UTF8Encode(xml.FieldByName('xml').AsString);
            LoadXML(MemoResp, WBResposta);
      end;

end;

procedure TForm1.FormCreate(Sender: TObject);
begin
      LerConfiguracao;
//      tsNFe.PageControl.Visible := true;
      TabSheet1.TabVisible      := false;
      SQLConnection1.Connected := true;
      if Edit4.Text <> '' then
      begin
            Timer1.Interval := StrToInt(Edit4.Text);
      end else
           Timer1.Enabled := false;

end;

procedure TForm1.btnSalvarConfigClick(Sender: TObject);
begin
 GravarConfiguracao;
 LerConfiguracao;
end;

procedure TForm1.btnStatusServClick(Sender: TObject);
 var
    RetWS : AnsiString;
begin
      gerarDadosInicial;
      RetWS := ACBrNFe_StatusServico(NrSenha,NrCertif,CdEstado,TpAmbiente,BoVisualizar);
      MemoResp.Lines.Text :=  RetWS;
      memoRespWS.Lines.Text :=  UTF8Encode(RetWS);
      LoadXML(MemoResp, WBResposta);
      LoadConsulta201(RetWS);
end;
procedure TForm1.Button1Click(Sender: TObject);
begin

      gerarDadosInicial;
      NrChaDoc := Edit2.Text;
      xml := ACBrNFe_ListarNotasManifesto(NrSenha,NrCertif,CdEstado,TpAmbiente,BoVisualizar,NrChaDoc,CjEmpres,2);
      xml.Open;

      DataSource1.DataSet := xml;
      MemoResp.Lines.Text :=  UTF8Encode(xml.FieldByName('xml').AsString);
      memoRespWS.Lines.Text :=  UTF8Encode(xml.FieldByName('xml').AsString);
      LoadXML(MemoResp, WBResposta);
end;

procedure TForm1.Button2Click(Sender: TObject);
begin
      TabSheet15.PageControl.Visible := true;
      PageControl3.ActivePage := TabSheet15;
      Panel3.Height := 432;
      PageControl2.Align := alClient;

end;



procedure TForm1.btnManifDestConfirmacaoClick(Sender: TObject);
var
 Chave, idLote, CNPJ: string;
 lMsg: string;
begin
  Chave:='';
  if not(InputQuery('WebServices Eventos: Manif. Destinatario - Conf. Operacao', 'Chave da NF-e', Chave)) then
     exit;
  Chave := Trim(OnlyNumber(Chave));
  idLote := '1';
  if not(InputQuery('WebServices Eventos: Manif. Destinatario - Conf. Operacao', 'Identificador de controle do Lote de envio do Evento', idLote)) then
     exit;
  CNPJ := '';
  if not(InputQuery('WebServices Eventos: Manif. Destinatario - Conf. Operacao', 'CNPJ ou o CPF do autor do Evento', CNPJ)) then
     exit;

  ACBrNFe1.EventoNFe.Evento.Clear;
  with ACBrNFe1.EventoNFe.Evento.Add do
   begin
     infEvento.chNFe := Chave;
     infEvento.CNPJ   := CNPJ;
     infEvento.dhEvento := now;
     infEvento.tpEvento := teManifDestConfirmacao;
   end;
  ACBrNFe1.EnviarEventoNFe(StrToInt(IDLote));

  with AcbrNFe1.WebServices.EnvEvento.EventoRetorno.retEvento.Items[0].RetInfEvento do
  begin
    lMsg:=
    'Id: '+Id+#13+
    'tpAmb: '+TpAmbToStr(tpAmb)+#13+
    'verAplic: '+verAplic+#13+
    'cOrgao: '+IntToStr(cOrgao)+#13+
    'cStat: '+IntToStr(cStat)+#13+
    'xMotivo: '+xMotivo+#13+
    'chNFe: '+chNFe+#13+
    'tpEvento: '+TpEventoToStr(tpEvento)+#13+
    'xEvento: '+xEvento+#13+
    'nSeqEvento: '+IntToStr(nSeqEvento)+#13+
    'CNPJDest: '+CNPJDest+#13+
    'emailDest: '+emailDest+#13+
    'dhRegEvento: '+DateTimeToStr(dhRegEvento)+#13+
    'nProt: '+nProt;
  end;
  ShowMessage(lMsg);

  MemoResp.Lines.Text := UTF8Encode(ACBrNFe1.WebServices.EnvEvento.RetWS);
  memoRespWS.Lines.Text := UTF8Encode(ACBrNFe1.WebServices.EnvEvento.RetornoWS);
//  ACBrNFe1.WebServices.EnvEvento.EventoRetorno.retEvento.Items[0].XXXX
  LoadXML(MemoResp, WBResposta);
end;

procedure TForm1.btnNfeDestinadasClick(Sender: TObject);
begin        // NfeDestinadas(NrSenha,NrCertificado,uf,CNPJ, IndNFe, IndEmi, ultNSU:String): OleVariant;
  gerarDadosInicial;
  xml.data := ACBrNFe_NfeDestinadas(NrSenha,NrCertif,CdEstado,CjEmpres,'2','0','0',TpAmbiente,BoVisualizar);
  xml.Open;
  DataSource1.DataSet := xml;
end;

procedure TForm1.btnCriarEnviarClick(Sender: TObject);
var
 vAux, vNumLote : String;
begin
  if not(InputQuery('WebServices Enviar', 'Numero da Nota', vAux)) then
    exit;

  if not(InputQuery('WebServices Enviar', 'Numero do Lote', vNumLote)) then
    exit;

  vNumLote := OnlyNumber(vNumLote);

  if Trim(vNumLote) = '' then
   begin
     MessageDlg('Número do Lote inválido.',mtError,[mbok],0);
     exit;
   end;

  ACBrNFe1.NotasFiscais.Clear;

  ACBrNFe1.Configuracoes.Geral.ModeloDF := moNFe;

  ACBrNFe1.Enviar(vNumLote,True);

  MemoResp.Lines.Text := UTF8Encode(ACBrNFe1.WebServices.Retorno.RetWS);
  memoRespWS.Lines.Text := UTF8Encode(ACBrNFe1.WebServices.Retorno.RetornoWS);
  LoadXML(MemoResp, WBResposta);

 MemoDados.Lines.Add('');
 MemoDados.Lines.Add('Envio NFe');
 MemoDados.Lines.Add('tpAmb: '+ TpAmbToStr(ACBrNFe1.WebServices.Retorno.TpAmb));
 MemoDados.Lines.Add('verAplic: '+ ACBrNFe1.WebServices.Retorno.verAplic);
 MemoDados.Lines.Add('cStat: '+ IntToStr(ACBrNFe1.WebServices.Retorno.cStat));
 MemoDados.Lines.Add('cUF: '+ IntToStr(ACBrNFe1.WebServices.Retorno.cUF));
 MemoDados.Lines.Add('xMotivo: '+ ACBrNFe1.WebServices.Retorno.xMotivo);
 MemoDados.Lines.Add('cMsg: '+ IntToStr(ACBrNFe1.WebServices.Retorno.cMsg));
 MemoDados.Lines.Add('xMsg: '+ ACBrNFe1.WebServices.Retorno.xMsg);
 MemoDados.Lines.Add('Recibo: '+ ACBrNFe1.WebServices.Retorno.Recibo);
 MemoDados.Lines.Add('Protocolo: '+ ACBrNFe1.WebServices.Retorno.Protocolo);
// MemoDados.Lines.Add('cStat: '+ ACBrNFe1.WebServices.Retorno.NFeRetorno;

{ ACBrNFe1.WebServices.Retorno.NFeRetorno.ProtNFe.Items[0].tpAmb
 ACBrNFe1.WebServices.Retorno.NFeRetorno.ProtNFe.Items[0].verAplic
 ACBrNFe1.WebServices.Retorno.NFeRetorno.ProtNFe.Items[0].chNFe
 ACBrNFe1.WebServices.Retorno.NFeRetorno.ProtNFe.Items[0].dhRecbto
 ACBrNFe1.WebServices.Retorno.NFeRetorno.ProtNFe.Items[0].nProt
 ACBrNFe1.WebServices.Retorno.NFeRetorno.ProtNFe.Items[0].digVal
 ACBrNFe1.WebServices.Retorno.NFeRetorno.ProtNFe.Items[0].cStat
 ACBrNFe1.WebServices.Retorno.NFeRetorno.ProtNFe.Items[0].xMotivo }

  ACBrNFe1.NotasFiscais.Clear;
end;

procedure TForm1.btnInutilizarClick(Sender: TObject);
var
 Modelo, Serie, Ano, NumeroInicial, NumeroFinal, Justificativa : String;
begin
 if not(InputQuery('WebServices Inutilização ', 'Ano',    Ano)) then
    exit;
 if not(InputQuery('WebServices Inutilização ', 'Modelo', Modelo)) then
    exit;
 if not(InputQuery('WebServices Inutilização ', 'Serie',  Serie)) then
    exit;
 if not(InputQuery('WebServices Inutilização ', 'Número Inicial', NumeroInicial)) then
    exit;
 if not(InputQuery('WebServices Inutilização ', 'Número Inicial', NumeroFinal)) then
    exit;
 if not(InputQuery('WebServices Inutilização ', 'Justificativa', Justificativa)) then
    exit;
  ACBrNFe1.WebServices.Inutiliza(edtEmitCNPJ.Text, Justificativa, StrToInt(Ano), StrToInt(Modelo), StrToInt(Serie), StrToInt(NumeroInicial), StrToInt(NumeroFinal));
  MemoResp.Lines.Text :=  UTF8Encode(ACBrNFe1.WebServices.Inutilizacao.RetWS);
  memoRespWS.Lines.Text :=  UTF8Encode(ACBrNFe1.WebServices.Inutilizacao.RetornoWS);  
  LoadXML(MemoResp, WBResposta);

{  ACBrNFe1.WebServices.Inutilizacao.TpAmb
  ACBrNFe1.WebServices.Inutilizacao.verAplic
  ACBrNFe1.WebServices.Inutilizacao.cStat
  ACBrNFe1.WebServices.Inutilizacao.xMotivo
  ACBrNFe1.WebServices.Inutilizacao.cUF
  ACBrNFe1.WebServices.Inutilizacao.Ano
  ACBrNFe1.WebServices.Inutilizacao.CNPJ
  ACBrNFe1.WebServices.Inutilizacao.Modelo
  ACBrNFe1.WebServices.Inutilizacao.Serie
  ACBrNFe1.WebServices.Inutilizacao.NumeroInicial
  ACBrNFe1.WebServices.Inutilizacao.NumeroFinal
  ACBrNFe1.WebServices.Inutilizacao.dhRecbto
  ACBrNFe1.WebServices.Inutilizacao.Protocolo    }
end;

procedure TForm1.ACBrNFe1StatusChange(Sender: TObject);
begin
  case ACBrNFe1.Status of
    stIdle :
    begin
      if ( frmStatus <> nil ) then
        frmStatus.Hide;
    end;
    stNFeStatusServico :
    begin
      if ( frmStatus = nil ) then
        frmStatus := TfrmStatus.Create(Application);
      frmStatus.lblStatus.Caption := 'Verificando Status do servico...';
      frmStatus.Show;
      frmStatus.BringToFront;
    end;
    stNFeRecepcao :
    begin
      if ( frmStatus = nil ) then
        frmStatus := TfrmStatus.Create(Application);
      frmStatus.lblStatus.Caption := 'Enviando dados da NFe...';
      frmStatus.Show;
      frmStatus.BringToFront;
    end;
    stNfeRetRecepcao :
    begin
      if ( frmStatus = nil ) then
        frmStatus := TfrmStatus.Create(Application);
      frmStatus.lblStatus.Caption := 'Recebendo dados da NFe...';
      frmStatus.Show;
      frmStatus.BringToFront;
    end;
    stNfeConsulta :
    begin
      if ( frmStatus = nil ) then
        frmStatus := TfrmStatus.Create(Application);
      frmStatus.lblStatus.Caption := 'Consultando NFe...';
      frmStatus.Show;
      frmStatus.BringToFront;
    end;
    stNfeCancelamento :
    begin
      if ( frmStatus = nil ) then
        frmStatus := TfrmStatus.Create(Application);
      frmStatus.lblStatus.Caption := 'Enviando cancelamento de NFe...';
      frmStatus.Show;
      frmStatus.BringToFront;
    end;
    stNfeInutilizacao :
    begin
      if ( frmStatus = nil ) then
        frmStatus := TfrmStatus.Create(Application);
      frmStatus.lblStatus.Caption := 'Enviando pedido de Inutilização...';
      frmStatus.Show;
      frmStatus.BringToFront;
    end;
    stNFeRecibo :
    begin
      if ( frmStatus = nil ) then
        frmStatus := TfrmStatus.Create(Application);
      frmStatus.lblStatus.Caption := 'Consultando Recibo de Lote...';
      frmStatus.Show;
      frmStatus.BringToFront;
    end;
    stNFeCadastro :
    begin
      if ( frmStatus = nil ) then
        frmStatus := TfrmStatus.Create(Application);
      frmStatus.lblStatus.Caption := 'Consultando Cadastro...';
      frmStatus.Show;
      frmStatus.BringToFront;
    end;
    stNFeEnvDPEC :
    begin
      if ( frmStatus = nil ) then
        frmStatus := TfrmStatus.Create(Application);
      frmStatus.lblStatus.Caption := 'Enviando DPEC...';
      frmStatus.Show;
      frmStatus.BringToFront;
    end;
    stNFeConsultaDPEC :
    begin
      if ( frmStatus = nil ) then
        frmStatus := TfrmStatus.Create(Application);
      frmStatus.lblStatus.Caption := 'Consultando DPEC...';
      frmStatus.Show;
      frmStatus.BringToFront;
    end;
    stNFeEmail :
    begin
      if ( frmStatus = nil ) then
        frmStatus := TfrmStatus.Create(Application);
      frmStatus.lblStatus.Caption := 'Enviando Email...';
      frmStatus.Show;
      frmStatus.BringToFront;
    end;
    stNFeCCe :
    begin
      if ( frmStatus = nil ) then
        frmStatus := TfrmStatus.Create(Application);
      frmStatus.lblStatus.Caption := 'Enviando Carta de Correção...';
      frmStatus.Show;
      frmStatus.BringToFront;
    end;
    stNFeEvento :
    begin
      if ( frmStatus = nil ) then
        frmStatus := TfrmStatus.Create(Application);
      frmStatus.lblStatus.Caption := 'Enviando Evento...';
      frmStatus.Show;
      frmStatus.BringToFront;
    end;
  end;
  Application.ProcessMessages;
end;

procedure TForm1.BrvBitBtn1Click(Sender: TObject);
var
    RetWS : AnsiString;
    Chave, idLote, CNPJ, Protocolo, Justificativa : string;
begin
      if gTipe = 1 then
          RetWS := ACBrNFe_ConsultaNFeChave(NrSenha,NrCertif,CdEstado,Edit1.Text,TpAmbiente,BoVisualizar)
      else if gTipe = 2 then
      begin
          Protocolo:='';
          if not(InputQuery('WebServices Eventos: Cancelamento', 'Protocolo de Autorização', Protocolo)) then
             exit;
          Justificativa := 'Justificativa do Cancelamento';
          if not(InputQuery('WebServices Eventos: Cancelamento', 'Justificativa do Cancelamento', Justificativa)) then
         exit;
          ACBrNFe_CancelarNFePelaChave(NrSenha,NrCertif,CdEstado,Edit1.Text,'0',edtEmitCNPJ.Text,Protocolo,Justificativa,TpAmbiente,BoVisualizar);
      end;
      PageControl3.ActivePage := TabSheet1;
end;

procedure TForm1.sbtnGetCertClick(Sender: TObject);
begin
   {$IFNDEF ACBrNFeOpenSSL}
   edtNumSerie.Text := ACBrNFe1.Configuracoes.Certificados.SelecionarCertificado;
   {$ENDIF}
end;


procedure TForm1.btnConsCadClick(Sender: TObject);
var
 UF, Documento : String;
 Return : AnsiString;
begin
     if not(InputQuery('WebServices Consulta Cadastro ', 'UF do Documento a ser Consultado:',    UF)) then
        exit;
     if not(InputQuery('WebServices Consulta Cadastro ', 'Documento(CPF/CNPJ)',    Documento)) then
        exit;

      Documento :=  Trim(DFeUtil.LimpaNumero(Documento));
      Return := ACBrNFe_ConsCadDestinatario(NrSenha,NrCertif,UF,Trim(DFeUtil.LimpaNumero(Documento)),TpAmbiente,BoVisualizar);

      MemoResp.Lines.Text :=  UTF8Encode(Return);
      memoRespWS.Lines.Text :=  UTF8Encode(Return);
      LoadXML(MemoResp, WBResposta);

end;

procedure TForm1.btnGerarTXTClick(Sender: TObject);
var
   vAux, vNumLote : String;
begin
  if not(InputQuery('WebServices Enviar', 'Numero da Nota', vAux)) then
    exit;

  if not(InputQuery('WebServices Enviar', 'Numero do Lote', vNumLote)) then
    exit;

  vNumLote := OnlyNumber(vNumLote);

  if Trim(vNumLote) = '' then
   begin
     MessageDlg('Número do Lote inválido.',mtError,[mbok],0);
     exit;
   end;

  ACBrNFe1.NotasFiscais.Clear;

  //GerarNFe(vAux);

  ACBrNFe1.NotasFiscais.SaveToTXT({caminho e nome do arquivo TXT});
end;

procedure TForm1.btnEnviarEmailClick(Sender: TObject);
var
 Para : String;
 CC: Tstrings;
begin
  if not(InputQuery('Enviar Email', 'Email de destino', Para)) then
    exit;

  OpenDialog1.Title := 'Selecione a NFE';
  OpenDialog1.DefaultExt := '*-nfe.XML';
  OpenDialog1.Filter := 'Arquivos NFE (*-nfe.XML)|*-nfe.XML|Arquivos XML (*.XML)|*.XML|Todos os Arquivos (*.*)|*.*';
  OpenDialog1.InitialDir := ACBrNFe1.Configuracoes.Geral.PathSalvar;
  if OpenDialog1.Execute then
  begin
    ACBrNFe1.NotasFiscais.Clear;
    ACBrNFe1.NotasFiscais.LoadFromFile(OpenDialog1.FileName);
    CC:=TstringList.Create;
    CC.Add('andrefmoraes@gmail.com'); //especifique um email válido
    CC.Add('anfm@zipmail.com.br');    //especifique um email válido
    ACBrNFe1.NotasFiscais.Items[0].EnviarEmail(edtSmtpHost.Text
                                             , edtSmtpPort.Text
                                             , edtSmtpUser.Text
                                             , edtSmtpPass.Text
                                             , edtSmtpUser.Text
                                             , Para
                                             , edtEmailAssunto.Text
                                             , mmEmailMsg.Lines
                                             , cbEmailSSL.Checked // SSL - Conexão Segura
                                             , True //Enviar PDF junto
                                             , CC //Lista com emails que serão enviado cópias - TStrings
                                             , nil // Lista de anexos - TStrings
                                             , False  //Pede confirmação de leitura do email
                                             , False  //Aguarda Envio do Email(não usa thread)
                                             , 'ACBrNFe2' // Nome do Rementente
                                             , cbEmailSSL.Checked ); // Auto TLS
    CC.Free;
  end;
end;

procedure TForm1.btnConsultarReciboClick(Sender: TObject);
var
  aux : String;
begin
      if not(InputQuery('Consultar Recibo Lote', 'Número do Recibo', aux)) then
      exit;
      MemoResp.Lines.Text :=  UTF8Encode(ACBrNFe_ConsultarReciboLoteNFe(NrSenha,NrCertif,CdEstado,TpAmbiente,BoVisualizar,StrToInt(aux)));
      memoRespWS.Lines.Text :=  UTF8Encode(ACBrNFe1.WebServices.Recibo.RetornoWS);
      LoadXML(MemoResp, WBResposta);
end;

procedure TForm1.ACBrNFe1GerarLog(const Mensagem: String);
begin
 memoLog.Lines.Add(Mensagem);
end;

procedure TForm1.btnImportarXMLClick(Sender: TObject);
var
  i, j, k, n  : integer;
  Nota, Node, NodePai, NodeItem: TTreeNode;
  NFeRTXT: TNFeRTXT;
begin
  OpenDialog1.FileName  :=  '';
  OpenDialog1.Title := 'Selecione a NFE';
  OpenDialog1.DefaultExt := '*-nfe.XML';
  OpenDialog1.Filter := 'Arquivos NFE (*-nfe.XML)|*-nfe.XML|Arquivos XML (*.XML)|*.XML|Arquivos TXT (*.TXT)|*.TXT|Todos os Arquivos (*.*)|*.*';
  OpenDialog1.InitialDir := ACBrNFe1.Configuracoes.Geral.PathSalvar;
  if OpenDialog1.Execute then
  begin
    ACBrNFe1.NotasFiscais.Clear;
    //tenta TXT
    ACBrNFe1.NotasFiscais.Add;
    NFeRTXT := TNFeRTXT.Create(ACBrNFe1.NotasFiscais.Items[0].NFe);
    NFeRTXT.CarregarArquivo(OpenDialog1.FileName);
    if NFeRTXT.LerTxt then
       NFeRTXT.Free
    else
    begin
       NFeRTXT.Free;
       //tenta XML
       ACBrNFe1.NotasFiscais.Clear;
       try
          ACBrNFe1.NotasFiscais.LoadFromFile(OpenDialog1.FileName);
       except
          ShowMessage('Arquivo NFe Inválido');
          exit;
       end;
    end;

    trvwNFe.Items.Clear;

    for n:=0 to ACBrNFe1.NotasFiscais.Count-1 do
    begin
    with ACBrNFe1.NotasFiscais.Items[n].NFe do
     begin

       Nota := trvwNFe.Items.Add(nil,infNFe.ID);
       trvwNFe.Items.AddChild(Nota,'ID= ' +infNFe.ID);
       Node := trvwNFe.Items.AddChild(Nota,'procNFe');
       trvwNFe.Items.AddChild(Node,'tpAmb= '     +TpAmbToStr(procNFe.tpAmb));
       trvwNFe.Items.AddChild(Node,'verAplic= '  +procNFe.verAplic);
       trvwNFe.Items.AddChild(Node,'chNFe= '     +procNFe.chNFe);
       trvwNFe.Items.AddChild(Node,'dhRecbto= '  +DateTimeToStr(procNFe.dhRecbto));
       trvwNFe.Items.AddChild(Node,'nProt= '     +procNFe.nProt);
       trvwNFe.Items.AddChild(Node,'digVal= '    +procNFe.digVal);
       trvwNFe.Items.AddChild(Node,'cStat= '     +IntToStr(procNFe.cStat));
       trvwNFe.Items.AddChild(Node,'xMotivo= '   +procNFe.xMotivo);

       Node := trvwNFe.Items.AddChild(Nota,'Ide');
       trvwNFe.Items.AddChild(Node,'cNF= '     +IntToStr(Ide.cNF));
       trvwNFe.Items.AddChild(Node,'natOp= '   +Ide.natOp );
       trvwNFe.Items.AddChild(Node,'indPag= '  +IndpagToStr(Ide.indPag));
       trvwNFe.Items.AddChild(Node,'modelo= '  +IntToStr(Ide.modelo));
       trvwNFe.Items.AddChild(Node,'serie= '   +IntToStr(Ide.serie));
       trvwNFe.Items.AddChild(Node,'nNF= '     +IntToStr(Ide.nNF));
       trvwNFe.Items.AddChild(Node,'dEmi= '    +DateToStr(Ide.dEmi));
       trvwNFe.Items.AddChild(Node,'dSaiEnt= ' +DateToStr(Ide.dSaiEnt));
       trvwNFe.Items.AddChild(Node,'hSaiEnt= ' +DateToStr(Ide.hSaiEnt));
       trvwNFe.Items.AddChild(Node,'tpNF= '    +tpNFToStr(Ide.tpNF));
       trvwNFe.Items.AddChild(Node,'finNFe= '  +FinNFeToStr(Ide.finNFe));
       trvwNFe.Items.AddChild(Node,'verProc= ' +Ide.verProc);
       trvwNFe.Items.AddChild(Node,'cUF= '     +IntToStr(Ide.cUF));
       trvwNFe.Items.AddChild(Node,'cMunFG= '  +IntToStr(Ide.cMunFG));
       trvwNFe.Items.AddChild(Node,'tpImp= '   +TpImpToStr(Ide.tpImp));
       trvwNFe.Items.AddChild(Node,'tpEmis= '  +TpEmisToStr(Ide.tpEmis));
       trvwNFe.Items.AddChild(Node,'cDV= '     +IntToStr(Ide.cDV));
       trvwNFe.Items.AddChild(Node,'tpAmb= '   +TpAmbToStr(Ide.tpAmb));
       trvwNFe.Items.AddChild(Node,'finNFe= '  +FinNFeToStr(Ide.finNFe));
       trvwNFe.Items.AddChild(Node,'procEmi= ' +procEmiToStr(Ide.procEmi));
       trvwNFe.Items.AddChild(Node,'verProc= ' +Ide.verProc);
       trvwNFe.Items.AddChild(Node,'dhCont= '  +DateTimeToStr(Ide.dhCont));
       trvwNFe.Items.AddChild(Node,'xJust= '   +Ide.xJust);

       for i:=0 to Ide.NFref.Count-1 do
        begin
          if Ide.NFref.Items[i].refNFe <> '' then
          begin
            Node := trvwNFe.Items.AddChild(Node,'NFRef'+IntToStrZero(i+1,3));
            trvwNFe.Items.AddChild(Node,'refNFe= ' +Ide.NFref.Items[i].refNFe);
            trvwNFe.Items.AddChild(Node,'cUF= '    +IntToStr(Ide.NFref.Items[i].RefNF.cUF));
            trvwNFe.Items.AddChild(Node,'AAMM= '   +Ide.NFref.Items[i].RefNF.AAMM);
            trvwNFe.Items.AddChild(Node,'CNPJ= '   +Ide.NFref.Items[i].RefNF.CNPJ);
            trvwNFe.Items.AddChild(Node,'modelo= ' +IntToStr(Ide.NFref.Items[i].RefNF.modelo));
            trvwNFe.Items.AddChild(Node,'serie= '  +IntToStr(Ide.NFref.Items[i].RefNF.serie));
            trvwNFe.Items.AddChild(Node,'nNF= '    +IntToStr(Ide.NFref.Items[i].RefNF.nNF));
          end;

          if Ide.NFref.Items[i].RefECF.nCOO <> '' then
          begin
            Node := trvwNFe.Items.AddChild(Node,'refECF'+IntToStrZero(i+1,3));
            trvwNFe.Items.AddChild(Node,'mod= '  +ECFModRefToStr(Ide.NFref.Items[i].RefECF.modelo));
            trvwNFe.Items.AddChild(Node,'nECF= ' +Ide.NFref.Items[i].RefECF.nECF);
            trvwNFe.Items.AddChild(Node,'nCOO= ' +Ide.NFref.Items[i].RefECF.nCOO);
          end;
        end;

       Node := trvwNFe.Items.AddChild(Nota,'Emit');
       trvwNFe.Items.AddChild(Node,'CNPJCPF= ' +Emit.CNPJCPF);
       trvwNFe.Items.AddChild(Node,'IE='       +Emit.IE);
       trvwNFe.Items.AddChild(Node,'xNome='    +Emit.xNome);
       trvwNFe.Items.AddChild(Node,'xFant='    +Emit.xFant );
       trvwNFe.Items.AddChild(Node,'IEST='     +Emit.IEST);
       trvwNFe.Items.AddChild(Node,'IM='       +Emit.IM);
       trvwNFe.Items.AddChild(Node,'CNAE='     +Emit.CNAE);
       trvwNFe.Items.AddChild(Node,'CRT='      +CRTToStr(Emit.CRT));

       Node := trvwNFe.Items.AddChild(Node,'EnderEmit');
       trvwNFe.Items.AddChild(Node,'Fone='    +Emit.EnderEmit.fone);
       trvwNFe.Items.AddChild(Node,'CEP='     +IntToStr(Emit.EnderEmit.CEP));
       trvwNFe.Items.AddChild(Node,'xLgr='    +Emit.EnderEmit.xLgr);
       trvwNFe.Items.AddChild(Node,'nro='     +Emit.EnderEmit.nro);
       trvwNFe.Items.AddChild(Node,'xCpl='    +Emit.EnderEmit.xCpl);
       trvwNFe.Items.AddChild(Node,'xBairro=' +Emit.EnderEmit.xBairro);
       trvwNFe.Items.AddChild(Node,'cMun='    +IntToStr(Emit.EnderEmit.cMun));
       trvwNFe.Items.AddChild(Node,'xMun='    +Emit.EnderEmit.xMun);
       trvwNFe.Items.AddChild(Node,'UF'       +Emit.EnderEmit.UF);
       trvwNFe.Items.AddChild(Node,'cPais='   +IntToStr(Emit.EnderEmit.cPais));
       trvwNFe.Items.AddChild(Node,'xPais='   +Emit.EnderEmit.xPais);

       if Avulsa.CNPJ  <> '' then
        begin
          Node := trvwNFe.Items.AddChild(Nota,'Avulsa');
          trvwNFe.Items.AddChild(Node,'CNPJ='    +Avulsa.CNPJ);
          trvwNFe.Items.AddChild(Node,'xOrgao='  +Avulsa.xOrgao);
          trvwNFe.Items.AddChild(Node,'matr='    +Avulsa.matr );
          trvwNFe.Items.AddChild(Node,'xAgente=' +Avulsa.xAgente);
          trvwNFe.Items.AddChild(Node,'fone='    +Avulsa.fone);
          trvwNFe.Items.AddChild(Node,'UF='      +Avulsa.UF);
          trvwNFe.Items.AddChild(Node,'nDAR='    +Avulsa.nDAR);
          trvwNFe.Items.AddChild(Node,'dEmi='    +DateToStr(Avulsa.dEmi));
          trvwNFe.Items.AddChild(Node,'vDAR='    +FloatToStr(Avulsa.vDAR));
          trvwNFe.Items.AddChild(Node,'repEmi='  +Avulsa.repEmi);
          trvwNFe.Items.AddChild(Node,'dPag='    +DateToStr(Avulsa.dPag));
        end;
       Node := trvwNFe.Items.AddChild(Nota,'Dest');
       trvwNFe.Items.AddChild(Node,'CNPJCPF= ' +Dest.CNPJCPF);
       trvwNFe.Items.AddChild(Node,'IE='       +Dest.IE);
       trvwNFe.Items.AddChild(Node,'ISUF='     +Dest.ISUF);
       trvwNFe.Items.AddChild(Node,'xNome='    +Dest.xNome);
       trvwNFe.Items.AddChild(Node,'email='    +Dest.Email);

       Node := trvwNFe.Items.AddChild(Node,'EnderDest');
       trvwNFe.Items.AddChild(Node,'Fone='    +Dest.EnderDest.Fone);
       trvwNFe.Items.AddChild(Node,'CEP='     +IntToStr(Dest.EnderDest.CEP));
       trvwNFe.Items.AddChild(Node,'xLgr='    +Dest.EnderDest.xLgr);
       trvwNFe.Items.AddChild(Node,'nro='     +Dest.EnderDest.nro);
       trvwNFe.Items.AddChild(Node,'xCpl='    +Dest.EnderDest.xCpl);
       trvwNFe.Items.AddChild(Node,'xBairro=' +Dest.EnderDest.xBairro);
       trvwNFe.Items.AddChild(Node,'cMun='    +IntToStr(Dest.EnderDest.cMun));
       trvwNFe.Items.AddChild(Node,'xMun='    +Dest.EnderDest.xMun);
       trvwNFe.Items.AddChild(Node,'UF='      +Dest.EnderDest.UF );
       trvwNFe.Items.AddChild(Node,'cPais='   +IntToStr(Dest.EnderDest.cPais));
       trvwNFe.Items.AddChild(Node,'xPais='   +Dest.EnderDest.xPais);

       for I := 0 to Det.Count-1 do
        begin
          with Det.Items[I] do
           begin
               NodeItem := trvwNFe.Items.AddChild(Nota,'Produto'+IntToStrZero(I+1,3));
               trvwNFe.Items.AddChild(NodeItem,'nItem='  +IntToStr(Prod.nItem) );
               trvwNFe.Items.AddChild(NodeItem,'cProd='  +Prod.cProd );
               trvwNFe.Items.AddChild(NodeItem,'cEAN='   +Prod.cEAN);
               trvwNFe.Items.AddChild(NodeItem,'xProd='  +Prod.xProd);
               trvwNFe.Items.AddChild(NodeItem,'NCM='    +Prod.NCM);
               trvwNFe.Items.AddChild(NodeItem,'EXTIPI=' +Prod.EXTIPI);
               //trvwNFe.Items.AddChild(NodeItem,'genero=' +IntToStr(Prod.genero));
               trvwNFe.Items.AddChild(NodeItem,'CFOP='   +Prod.CFOP);
               trvwNFe.Items.AddChild(NodeItem,'uCom='   +Prod.uCom);
               trvwNFe.Items.AddChild(NodeItem,'qCom='   +FloatToStr(Prod.qCom)) ;
               trvwNFe.Items.AddChild(NodeItem,'vUnCom=' +FloatToStr(Prod.vUnCom)) ;
               trvwNFe.Items.AddChild(NodeItem,'vProd='  +FloatToStr(Prod.vProd)) ;

               trvwNFe.Items.AddChild(NodeItem,'cEANTrib=' +Prod.cEANTrib);
               trvwNFe.Items.AddChild(NodeItem,'uTrib='    +Prod.uTrib);
               trvwNFe.Items.AddChild(NodeItem,'qTrib='    +FloatToStr(Prod.qTrib));
               trvwNFe.Items.AddChild(NodeItem,'vUnTrib='  +FloatToStr(Prod.vUnTrib)) ;

               trvwNFe.Items.AddChild(NodeItem,'vFrete='      +FloatToStr(Prod.vFrete)) ;
               trvwNFe.Items.AddChild(NodeItem,'vSeg='        +FloatToStr(Prod.vSeg)) ;
               trvwNFe.Items.AddChild(NodeItem,'vDesc='       +FloatToStr(Prod.vDesc)) ;
               trvwNFe.Items.AddChild(NodeItem,'vOutro='      +FloatToStr(Prod.vOutro)) ;
               trvwNFe.Items.AddChild(NodeItem,'indTot='      +indTotToStr(Prod.IndTot)) ;
               trvwNFe.Items.AddChild(NodeItem,'xPed='        +Prod.xPed) ;
               trvwNFe.Items.AddChild(NodeItem,'nItemPedido=' +IntToStr(Prod.nItemPed)) ;

               trvwNFe.Items.AddChild(NodeItem,'infAdProd=' +infAdProd);

               for J:=0 to Prod.DI.Count-1 do
                begin
                  if Prod.DI.Items[j].nDi <> '' then
                   begin
                     with Prod.DI.Items[j] do
                      begin
                        NodePai := trvwNFe.Items.AddChild(NodeItem,'DI'+IntToStrZero(J+1,3));
                        trvwNFe.Items.AddChild(NodePai,'nDi='         +nDi);
                        trvwNFe.Items.AddChild(NodePai,'dDi='         +DateToStr(dDi));
                        trvwNFe.Items.AddChild(NodePai,'xLocDesemb='  +xLocDesemb);
                        trvwNFe.Items.AddChild(NodePai,'UFDesemb='    +UFDesemb);
                        trvwNFe.Items.AddChild(NodePai,'dDesemb='     +DateToStr(dDesemb));
                        trvwNFe.Items.AddChild(NodePai,'cExportador=' +cExportador);;

                        for K:=0 to adi.Count-1 do
                         begin
                           with adi.Items[K] do
                            begin
                              Node := trvwNFe.Items.AddChild(NodePai,'LADI'+IntToStrZero(K+1,3));
                              trvwNFe.Items.AddChild(Node,'nAdicao='     +IntToStr(nAdicao)) ;
                              trvwNFe.Items.AddChild(Node,'nSeqAdi='     +IntToStr(nSeqAdi)) ;
                              trvwNFe.Items.AddChild(Node,'cFabricante=' +cFabricante);
                              trvwNFe.Items.AddChild(Node,'vDescDI='     +FloatToStr(vDescDI));
                            end;
                         end;
                      end;
                   end
                  else
                    Break;
                end;

              if Prod.veicProd.chassi <> '' then
               begin
                 Node := trvwNFe.Items.AddChild(NodeItem,'Veiculo');
                 with Prod.veicProd do
                  begin
                    trvwNFe.Items.AddChild(Node,'tpOP='     +tpOPToStr(tpOP));
                    trvwNFe.Items.AddChild(Node,'chassi='   +chassi) ;
                    trvwNFe.Items.AddChild(Node,'cCor='     +cCor);
                    trvwNFe.Items.AddChild(Node,'xCor='     +xCor);
                    trvwNFe.Items.AddChild(Node,'pot='      +pot);
                    trvwNFe.Items.AddChild(Node,'Cilin='      +Cilin);
                    trvwNFe.Items.AddChild(Node,'pesoL='    +pesoL);
                    trvwNFe.Items.AddChild(Node,'pesoB='    +pesoB);
                    trvwNFe.Items.AddChild(Node,'nSerie='   +nSerie);
                    trvwNFe.Items.AddChild(Node,'tpComb='   +tpComb);
                    trvwNFe.Items.AddChild(Node,'nMotor='   +nMotor);
                    trvwNFe.Items.AddChild(Node,'CMT='     +CMT);
                    trvwNFe.Items.AddChild(Node,'dist='     +dist);
                    //trvwNFe.Items.AddChild(Node,'RENAVAM='  +RENAVAM);
                    trvwNFe.Items.AddChild(Node,'anoMod='   +IntToStr(anoMod));
                    trvwNFe.Items.AddChild(Node,'anoFab='   +IntToStr(anoFab));
                    trvwNFe.Items.AddChild(Node,'tpPint='   +tpPint);
                    trvwNFe.Items.AddChild(Node,'tpVeic='   +IntToStr(tpVeic));
                    trvwNFe.Items.AddChild(Node,'espVeic='  +IntToStr(espVeic));
                    trvwNFe.Items.AddChild(Node,'VIN='      +VIN);
                    trvwNFe.Items.AddChild(Node,'condVeic=' +condVeicToStr(condVeic));
                    trvwNFe.Items.AddChild(Node,'cMod='     +cMod);
                  end;
               end;

               for J:=0 to Prod.med.Count-1 do
                begin
                  Node := trvwNFe.Items.AddChild(NodeItem,'Medicamento'+IntToStrZero(J+1,3) );
                  with Prod.med.Items[J] do
                   begin
                     trvwNFe.Items.AddChild(Node,'nLote=' +nLote) ;
                     trvwNFe.Items.AddChild(Node,'qLote=' +FloatToStr(qLote)) ;
                     trvwNFe.Items.AddChild(Node,'dFab='  +DateToStr(dFab)) ;
                     trvwNFe.Items.AddChild(Node,'dVal='  +DateToStr(dVal)) ;
                     trvwNFe.Items.AddChild(Node,'vPMC='  +FloatToStr(vPMC)) ;
                    end;
                end;

               for J:=0 to Prod.arma.Count-1 do
                begin
                  Node := trvwNFe.Items.AddChild(NodeItem,'Arma'+IntToStrZero(J+1,3));
                  with Prod.arma.Items[J] do
                   begin
                     trvwNFe.Items.AddChild(Node,'nSerie=' +nSerie) ;
                     trvwNFe.Items.AddChild(Node,'tpArma=' +tpArmaToStr(tpArma)) ;
                     trvwNFe.Items.AddChild(Node,'nCano='  +nCano) ;
                     trvwNFe.Items.AddChild(Node,'descr='  +descr) ;
                    end;
                end;

               if (Prod.comb.cProdANP > 0) then
                begin
                 NodePai := trvwNFe.Items.AddChild(NodeItem,'Combustivel');
                 with Prod.comb do
                  begin
                    trvwNFe.Items.AddChild(NodePai,'cProdANP=' +IntToStr(cProdANP)) ;
                    trvwNFe.Items.AddChild(NodePai,'CODIF='    +CODIF) ;
                    trvwNFe.Items.AddChild(NodePai,'qTemp='    +FloatToStr(qTemp)) ;
                    trvwNFe.Items.AddChild(NodePai,'UFcons='    +UFcons) ;                    

                    Node := trvwNFe.Items.AddChild(NodePai,'CIDE'+IntToStrZero(I+1,3));
                    trvwNFe.Items.AddChild(Node,'qBCprod='   +FloatToStr(CIDE.qBCprod)) ;
                    trvwNFe.Items.AddChild(Node,'vAliqProd=' +FloatToStr(CIDE.vAliqProd)) ;
                    trvwNFe.Items.AddChild(Node,'vCIDE='     +FloatToStr(CIDE.vCIDE)) ;

                    Node := trvwNFe.Items.AddChild(NodePai,'ICMSComb'+IntToStrZero(I+1,3));
                    trvwNFe.Items.AddChild(Node,'vBCICMS='   +FloatToStr(ICMS.vBCICMS)) ;
                    trvwNFe.Items.AddChild(Node,'vICMS='     +FloatToStr(ICMS.vICMS)) ;
                    trvwNFe.Items.AddChild(Node,'vBCICMSST=' +FloatToStr(ICMS.vBCICMSST)) ;
                    trvwNFe.Items.AddChild(Node,'vICMSST='   +FloatToStr(ICMS.vICMSST)) ;

                    if (ICMSInter.vBCICMSSTDest>0) then
                     begin
                       Node := trvwNFe.Items.AddChild(NodePai,'ICMSInter'+IntToStrZero(I+1,3));
                       trvwNFe.Items.AddChild(Node,'vBCICMSSTDest=' +FloatToStr(ICMSInter.vBCICMSSTDest)) ;
                       trvwNFe.Items.AddChild(Node,'vICMSSTDest='   +FloatToStr(ICMSInter.vICMSSTDest)) ;
                     end;

                    if (ICMSCons.vBCICMSSTCons>0) then
                     begin
                       Node := trvwNFe.Items.AddChild(NodePai,'ICMSCons'+IntToStrZero(I+1,3));
                       trvwNFe.Items.AddChild(Node,'vBCICMSSTCons=' +FloatToStr(ICMSCons.vBCICMSSTCons)) ;
                       trvwNFe.Items.AddChild(Node,'vICMSSTCons='   +FloatToStr(ICMSCons.vICMSSTCons)) ;
                       trvwNFe.Items.AddChild(Node,'UFCons='        +ICMSCons.UFcons) ;
                     end;
                  end;
               end;

               with Imposto do
                begin
                   NodePai := trvwNFe.Items.AddChild(NodeItem,'Imposto');

                   if ISSQN.cSitTrib = ISSQNcSitTribVazio then
                   begin
                     Node := trvwNFe.Items.AddChild(NodePai,'ICMS');
                     with ICMS do
                      begin
                        trvwNFe.Items.AddChild(Node,'CST=' +CSTICMSToStr(CST));
                        trvwNFe.Items.AddChild(Node,'CSOSN=' +CSOSNIcmsToStr(CSOSN));
                        trvwNFe.Items.AddChild(Node,'orig='  +OrigToStr(ICMS.orig));
                        trvwNFe.Items.AddChild(Node,'modBC=' +modBCToStr(ICMS.modBC));
                        trvwNFe.Items.AddChild(Node,'pRedBC=' +FloatToStr(ICMS.pRedBC));
                        trvwNFe.Items.AddChild(Node,'vBC='   +FloatToStr(ICMS.vBC));
                        trvwNFe.Items.AddChild(Node,'pICMS=' +FloatToStr(ICMS.pICMS));
                        trvwNFe.Items.AddChild(Node,'vICMS=' +FloatToStr(ICMS.vICMS));
                        trvwNFe.Items.AddChild(Node,'modBCST='  +modBCSTToStr(ICMS.modBCST));
                        trvwNFe.Items.AddChild(Node,'pMVAST='   +FloatToStr(ICMS.pMVAST));
                        trvwNFe.Items.AddChild(Node,'pRedBCST=' +FloatToStr(ICMS.pRedBCST));
                        trvwNFe.Items.AddChild(Node,'vBCST='    +FloatToStr(ICMS.vBCST));
                        trvwNFe.Items.AddChild(Node,'pICMSST='  +FloatToStr(ICMS.pICMSST));
                        trvwNFe.Items.AddChild(Node,'vICMSST='  +FloatToStr(ICMS.vICMSST));
                        trvwNFe.Items.AddChild(Node,'vBCSTRet='   +FloatToStr(ICMS.vBCSTRet));
                        trvwNFe.Items.AddChild(Node,'vICMSSTRet=' +FloatToStr(ICMS.vICMSSTRet));
                        trvwNFe.Items.AddChild(Node,'pCredSN='   +FloatToStr(ICMS.pCredSN));
                        trvwNFe.Items.AddChild(Node,'vCredICMSSN='   +FloatToStr(ICMS.vCredICMSSN));
                      end;
                   end
                   else
                   begin
                     Node := trvwNFe.Items.AddChild(NodePai,'ISSQN');
                     with ISSQN do
                      begin
                        trvwNFe.Items.AddChild(Node,'vBC='       +FloatToStr(vBC));
                        trvwNFe.Items.AddChild(Node,'vAliq='     +FloatToStr(vAliq));
                        trvwNFe.Items.AddChild(Node,'vISSQN='    +FloatToStr(vISSQN));
                        trvwNFe.Items.AddChild(Node,'cMunFG='    +IntToStr(cMunFG));
                        trvwNFe.Items.AddChild(Node,'cListServ=' +IntToStr(cListServ));
                      end;
                   end;

                   if (IPI.vBC > 0) then
                    begin
                      Node := trvwNFe.Items.AddChild(NodePai,'IPI');
                      with IPI do
                       begin
                         trvwNFe.Items.AddChild(Node,'CST='       +CSTIPIToStr(CST)) ;
                         trvwNFe.Items.AddChild(Node,'clEnq='    +clEnq);
                         trvwNFe.Items.AddChild(Node,'CNPJProd=' +CNPJProd);
                         trvwNFe.Items.AddChild(Node,'cSelo='    +cSelo);
                         trvwNFe.Items.AddChild(Node,'qSelo='    +IntToStr(qSelo));
                         trvwNFe.Items.AddChild(Node,'cEnq='     +cEnq);

                         trvwNFe.Items.AddChild(Node,'vBC='    +FloatToStr(vBC));
                         trvwNFe.Items.AddChild(Node,'qUnid='  +FloatToStr(qUnid));
                         trvwNFe.Items.AddChild(Node,'vUnid='  +FloatToStr(vUnid));
                         trvwNFe.Items.AddChild(Node,'pIPI='   +FloatToStr(pIPI));
                         trvwNFe.Items.AddChild(Node,'vIPI='   +FloatToStr(vIPI));
                       end;
                    end;

                   if (II.vBc > 0) then
                    begin
                      Node := trvwNFe.Items.AddChild(NodePai,'II');
                      with II do
                       begin
                         trvwNFe.Items.AddChild(Node,'vBc='      +FloatToStr(vBc));
                         trvwNFe.Items.AddChild(Node,'vDespAdu=' +FloatToStr(vDespAdu));
                         trvwNFe.Items.AddChild(Node,'vII='      +FloatToStr(vII));
                         trvwNFe.Items.AddChild(Node,'vIOF='     +FloatToStr(vIOF));
                       end;
                    end;

                   Node := trvwNFe.Items.AddChild(NodePai,'PIS');
                   with PIS do
                    begin
                      trvwNFe.Items.AddChild(Node,'CST=' +CSTPISToStr(CST));

                      if (CST = pis01) or (CST = pis02) then
                       begin
                         trvwNFe.Items.AddChild(Node,'vBC='  +FloatToStr(PIS.vBC));
                         trvwNFe.Items.AddChild(Node,'pPIS=' +FloatToStr(PIS.pPIS));
                         trvwNFe.Items.AddChild(Node,'vPIS=' +FloatToStr(PIS.vPIS));
                       end
                      else if CST = pis03 then
                       begin
                         trvwNFe.Items.AddChild(Node,'qBCProd='   +FloatToStr(PIS.qBCProd));
                         trvwNFe.Items.AddChild(Node,'vAliqProd=' +FloatToStr(PIS.vAliqProd));
                         trvwNFe.Items.AddChild(Node,'vPIS='      +FloatToStr(PIS.vPIS));
                       end
                      else if CST = pis99 then
                       begin
                         trvwNFe.Items.AddChild(Node,'vBC='       +FloatToStr(PIS.vBC));
                         trvwNFe.Items.AddChild(Node,'pPIS='      +FloatToStr(PIS.pPIS));
                         trvwNFe.Items.AddChild(Node,'qBCProd='   +FloatToStr(PIS.qBCProd));
                         trvwNFe.Items.AddChild(Node,'vAliqProd=' +FloatToStr(PIS.vAliqProd));
                         trvwNFe.Items.AddChild(Node,'vPIS='      +FloatToStr(PIS.vPIS));
                       end;
                    end;

                   if (PISST.vBc>0) then
                    begin
                      Node := trvwNFe.Items.AddChild(NodePai,'PISST');
                      with PISST do
                       begin
                         trvwNFe.Items.AddChild(Node,'vBc='       +FloatToStr(vBc));
                         trvwNFe.Items.AddChild(Node,'pPis='      +FloatToStr(pPis));
                         trvwNFe.Items.AddChild(Node,'qBCProd='   +FloatToStr(qBCProd));
                         trvwNFe.Items.AddChild(Node,'vAliqProd=' +FloatToStr(vAliqProd));
                         trvwNFe.Items.AddChild(Node,'vPIS='      +FloatToStr(vPIS));
                       end;
                      end;

                   Node := trvwNFe.Items.AddChild(NodePai,'COFINS');
                   with COFINS do
                    begin
                      trvwNFe.Items.AddChild(Node,'CST=' +CSTCOFINSToStr(CST));

                      if (CST = cof01) or (CST = cof02)   then
                       begin
                         trvwNFe.Items.AddChild(Node,'vBC='     +FloatToStr(COFINS.vBC));
                         trvwNFe.Items.AddChild(Node,'pCOFINS=' +FloatToStr(COFINS.pCOFINS));
                         trvwNFe.Items.AddChild(Node,'vCOFINS=' +FloatToStr(COFINS.vCOFINS));
                       end
                      else if CST = cof03 then
                       begin
                         trvwNFe.Items.AddChild(Node,'qBCProd='   +FloatToStr(COFINS.qBCProd));
                         trvwNFe.Items.AddChild(Node,'vAliqProd=' +FloatToStr(COFINS.vAliqProd));
                         trvwNFe.Items.AddChild(Node,'vCOFINS='   +FloatToStr(COFINS.vCOFINS));
                       end
                      else if CST = cof99 then
                       begin
                         trvwNFe.Items.AddChild(Node,'vBC='       +FloatToStr(COFINS.vBC));
                         trvwNFe.Items.AddChild(Node,'pCOFINS='   +FloatToStr(COFINS.pCOFINS));
                         trvwNFe.Items.AddChild(Node,'qBCProd='   +FloatToStr(COFINS.qBCProd));
                         trvwNFe.Items.AddChild(Node,'vAliqProd=' +FloatToStr(COFINS.vAliqProd));
                         trvwNFe.Items.AddChild(Node,'vCOFINS='   +FloatToStr(COFINS.vCOFINS));
                       end;
                    end;

                   if (COFINSST.vBC > 0) then
                    begin
                      Node := trvwNFe.Items.AddChild(NodePai,'COFINSST');
                      with COFINSST do
                       begin
                         trvwNFe.Items.AddChild(Node,'vBC='       +FloatToStr(vBC));
                         trvwNFe.Items.AddChild(Node,'pCOFINS='   +FloatToStr(pCOFINS));
                         trvwNFe.Items.AddChild(Node,'qBCProd='   +FloatToStr(qBCProd));
                         trvwNFe.Items.AddChild(Node,'vAliqProd=' +FloatToStr(vAliqProd));
                         trvwNFe.Items.AddChild(Node,'vCOFINS='   +FloatToStr(vCOFINS));
                       end;
                    end;
                end;
             end;
          end ;

       NodePai := trvwNFe.Items.AddChild(Nota,'Total');
       Node := trvwNFe.Items.AddChild(NodePai,'ICMSTot');
       trvwNFe.Items.AddChild(Node,'vBC='     +FloatToStr(Total.ICMSTot.vBC));
       trvwNFe.Items.AddChild(Node,'vICMS='   +FloatToStr(Total.ICMSTot.vICMS)) ;
       trvwNFe.Items.AddChild(Node,'vBCST='   +FloatToStr(Total.ICMSTot.vBCST)) ;
       trvwNFe.Items.AddChild(Node,'vST='     +FloatToStr(Total.ICMSTot.vST)) ;
       trvwNFe.Items.AddChild(Node,'vProd='   +FloatToStr(Total.ICMSTot.vProd)) ;
       trvwNFe.Items.AddChild(Node,'vFrete='  +FloatToStr(Total.ICMSTot.vFrete)) ;
       trvwNFe.Items.AddChild(Node,'vSeg='    +FloatToStr(Total.ICMSTot.vSeg)) ;
       trvwNFe.Items.AddChild(Node,'vDesc='   +FloatToStr(Total.ICMSTot.vDesc)) ;
       trvwNFe.Items.AddChild(Node,'vII='     +FloatToStr(Total.ICMSTot.vII)) ;
       trvwNFe.Items.AddChild(Node,'vIPI='    +FloatToStr(Total.ICMSTot.vIPI)) ;
       trvwNFe.Items.AddChild(Node,'vPIS='    +FloatToStr(Total.ICMSTot.vPIS)) ;
       trvwNFe.Items.AddChild(Node,'vCOFINS=' +FloatToStr(Total.ICMSTot.vCOFINS)) ;
       trvwNFe.Items.AddChild(Node,'vOutro='  +FloatToStr(Total.ICMSTot.vOutro)) ;
       trvwNFe.Items.AddChild(Node,'vNF='     +FloatToStr(Total.ICMSTot.vNF)) ;

       if Total.ISSQNtot.vServ > 0 then
        begin
          Node := trvwNFe.Items.AddChild(NodePai,'ISSQNtot');
          trvwNFe.Items.AddChild(Node,'vServ='   +FloatToStr(Total.ISSQNtot.vServ)) ;
          trvwNFe.Items.AddChild(Node,'vBC='     +FloatToStr(Total.ISSQNTot.vBC)) ;
          trvwNFe.Items.AddChild(Node,'vISS='    +FloatToStr(Total.ISSQNTot.vISS)) ;
          trvwNFe.Items.AddChild(Node,'vPIS='    +FloatToStr(Total.ISSQNTot.vPIS)) ;
          trvwNFe.Items.AddChild(Node,'vCOFINS=' +FloatToStr(Total.ISSQNTot.vCOFINS)) ;
        end;

       Node := trvwNFe.Items.AddChild(NodePai,'retTrib');
       trvwNFe.Items.AddChild(Node,'vRetPIS='   +FloatToStr(Total.retTrib.vRetPIS)) ;
       trvwNFe.Items.AddChild(Node,'vRetCOFINS='+FloatToStr(Total.retTrib.vRetCOFINS)) ;
       trvwNFe.Items.AddChild(Node,'vRetCSLL='  +FloatToStr(Total.retTrib.vRetCSLL)) ;
       trvwNFe.Items.AddChild(Node,'vBCIRRF='   +FloatToStr(Total.retTrib.vBCIRRF)) ;
       trvwNFe.Items.AddChild(Node,'vIRRF='     +FloatToStr(Total.retTrib.vIRRF)) ;
       trvwNFe.Items.AddChild(Node,'vBCRetPrev='+FloatToStr(Total.retTrib.vBCRetPrev)) ;
       trvwNFe.Items.AddChild(Node,'vRetPrev='  +FloatToStr(Total.retTrib.vRetPrev)) ;

       NodePai := trvwNFe.Items.AddChild(Nota,'Transp');
       Node := trvwNFe.Items.AddChild(NodePai,'Transporta');
       trvwNFe.Items.AddChild(Node,'modFrete=' +modFreteToStr(Transp.modFrete));
       trvwNFe.Items.AddChild(Node,'CNPJCPF='  +Transp.Transporta.CNPJCPF);
       trvwNFe.Items.AddChild(Node,'xNome='    +Transp.Transporta.xNome);
       trvwNFe.Items.AddChild(Node,'IE='       +Transp.Transporta.IE);
       trvwNFe.Items.AddChild(Node,'xEnder='   +Transp.Transporta.xEnder);
       trvwNFe.Items.AddChild(Node,'xMun='     +Transp.Transporta.xMun);
       trvwNFe.Items.AddChild(Node,'UF='       +Transp.Transporta.UF);

       Node := trvwNFe.Items.AddChild(NodePai,'retTransp');
       trvwNFe.Items.AddChild(Node,'vServ='    +FloatToStr(Transp.retTransp.vServ)) ;
       trvwNFe.Items.AddChild(Node,'vBCRet='   +FloatToStr(Transp.retTransp.vBCRet)) ;
       trvwNFe.Items.AddChild(Node,'pICMSRet=' +FloatToStr(Transp.retTransp.pICMSRet)) ;
       trvwNFe.Items.AddChild(Node,'vICMSRet=' +FloatToStr(Transp.retTransp.vICMSRet)) ;
       trvwNFe.Items.AddChild(Node,'CFOP='     +Transp.retTransp.CFOP);
       trvwNFe.Items.AddChild(Node,'cMunFG='   +FloatToStr(Transp.retTransp.cMunFG));

       Node := trvwNFe.Items.AddChild(NodePai,'veicTransp');
       trvwNFe.Items.AddChild(Node,'placa='  +Transp.veicTransp.placa);
       trvwNFe.Items.AddChild(Node,'UF='     +Transp.veicTransp.UF);
       trvwNFe.Items.AddChild(Node,'RNTC='   +Transp.veicTransp.RNTC);

       for I:=0 to Transp.Reboque.Count-1 do
        begin
          Node := trvwNFe.Items.AddChild(NodePai,'Reboque'+IntToStrZero(I+1,3));
          with Transp.Reboque.Items[I] do
           begin
             trvwNFe.Items.AddChild(Node,'placa=' +placa) ;
             trvwNFe.Items.AddChild(Node,'UF='    +UF) ;
             trvwNFe.Items.AddChild(Node,'RNTC='  +RNTC) ;
           end;
        end;

       for I:=0 to Transp.Vol.Count-1 do
        begin
          Node := trvwNFe.Items.AddChild(NodePai,'Volume'+IntToStrZero(I+1,3));
          with Transp.Vol.Items[I] do
           begin
             trvwNFe.Items.AddChild(Node,'qVol='  +IntToStr(qVol)) ;
             trvwNFe.Items.AddChild(Node,'esp='   +esp);
             trvwNFe.Items.AddChild(Node,'marca=' +marca);
             trvwNFe.Items.AddChild(Node,'nVol='  +nVol);
             trvwNFe.Items.AddChild(Node,'pesoL=' +FloatToStr(pesoL)) ;
             trvwNFe.Items.AddChild(Node,'pesoB'  +FloatToStr(pesoB)) ;

             for J:=0 to Lacres.Count-1 do
              begin
                Node := trvwNFe.Items.AddChild(Node,'Lacre'+IntToStrZero(I+1,3)+IntToStrZero(J+1,3) );
                trvwNFe.Items.AddChild(Node,'nLacre='+Lacres.Items[J].nLacre) ;
              end;
           end;
        end;

       NodePai := trvwNFe.Items.AddChild(Nota,'Cobr');
       Node    := trvwNFe.Items.AddChild(NodePai,'Fat');
       trvwNFe.Items.AddChild(Node,'nFat='  +Cobr.Fat.nFat);
       trvwNFe.Items.AddChild(Node,'vOrig=' +FloatToStr(Cobr.Fat.vOrig)) ;
       trvwNFe.Items.AddChild(Node,'vDesc=' +FloatToStr(Cobr.Fat.vDesc)) ;
       trvwNFe.Items.AddChild(Node,'vLiq='  +FloatToStr(Cobr.Fat.vLiq)) ;

       for I:=0 to Cobr.Dup.Count-1 do
        begin
          Node    := trvwNFe.Items.AddChild(NodePai,'Duplicata'+IntToStrZero(I+1,3));
          with Cobr.Dup.Items[I] do
           begin
             trvwNFe.Items.AddChild(Node,'nDup='  +nDup) ;
             trvwNFe.Items.AddChild(Node,'dVenc=' +DateToStr(dVenc));
             trvwNFe.Items.AddChild(Node,'vDup='  +FloatToStr(vDup)) ;
           end;
        end;

       NodePai := trvwNFe.Items.AddChild(Nota,'InfAdic');
       trvwNFe.Items.AddChild(NodePai,'infCpl='     +InfAdic.infCpl);
       trvwNFe.Items.AddChild(NodePai,'infAdFisco=' +InfAdic.infAdFisco);

       for I:=0 to InfAdic.obsCont.Count-1 do
        begin
          Node := trvwNFe.Items.AddChild(NodePai,'obsCont'+IntToStrZero(I+1,3));
          with InfAdic.obsCont.Items[I] do
           begin
             trvwNFe.Items.AddChild(Node,'xCampo=' +xCampo) ;
             trvwNFe.Items.AddChild(Node,'xTexto=' +xTexto);
           end;
        end;

         for I:=0 to InfAdic.obsFisco.Count-1 do
          begin
            Node := trvwNFe.Items.AddChild(NodePai,'obsFisco'+IntToStrZero(I+1,3));
            with InfAdic.obsFisco.Items[I] do
             begin
                trvwNFe.Items.AddChild(Node,'xCampo=' +xCampo) ;
                trvwNFe.Items.AddChild(Node,'xTexto=' +xTexto);
             end;
          end;

         for I:=0 to InfAdic.procRef.Count-1 do
          begin
            Node := trvwNFe.Items.AddChild(NodePai,'procRef'+IntToStrZero(I+1,3));
            with InfAdic.procRef.Items[I] do
             begin
               trvwNFe.Items.AddChild(Node,'nProc='   +nProc) ;
               trvwNFe.Items.AddChild(Node,'indProc=' +indProcToStr(indProc));
             end;
          end;

         if (exporta.UFembarq <> '') then
          begin
            Node := trvwNFe.Items.AddChild(Nota,'exporta');
            trvwNFe.Items.AddChild(Node,'UFembarq='   +exporta.UFembarq) ;
            trvwNFe.Items.AddChild(Node,'xLocEmbarq=' +exporta.xLocEmbarq);
          end;

         if (compra.xNEmp <> '') then
          begin
            Node := trvwNFe.Items.AddChild(Nota,'compra');
            trvwNFe.Items.AddChild(Node,'xNEmp=' +compra.xNEmp) ;
            trvwNFe.Items.AddChild(Node,'xPed='  +compra.xPed);
            trvwNFe.Items.AddChild(Node,'xCont=' +compra.xCont);
          end;
     end;
       PageControl2.ActivePageIndex := 3;
     end;

  end;
end;

procedure TForm1.lblMouseEnter(Sender: TObject);
begin
 TLabel(Sender).Font.Style := [fsBold,fsUnderline];
end;

procedure TForm1.lblMouseLeave(Sender: TObject);
begin
 TLabel(Sender).Font.Style := [fsBold];
end;


procedure TForm1.btnConsultarChaveClick(Sender: TObject);
begin
      TabSheet15.PageControl.Visible := true;
      PageControl3.ActivePage := TabSheet13;
      Panel3.Height := 100;
      PageControl2.Align := alClient;
      gTipe := 1;
end;

procedure TForm1.btnCancelarChaveClick(Sender: TObject);
begin
      TabSheet15.PageControl.Visible := true;
      PageControl3.ActivePage := TabSheet13;
      Panel3.Height := 100;
      PageControl2.Align := alClient;
      gTipe := 2;
end;

procedure TForm1.btnCarregarXMLEnviarClick(Sender: TObject);
begin
  OpenDialog1.Title := 'Selecione a NFE';
  OpenDialog1.DefaultExt := '*-nfe.XML';
  OpenDialog1.Filter := 'Arquivos NFE (*-nfe.XML)|*-nfe.XML|Arquivos XML (*.XML)|*.XML|Todos os Arquivos (*.*)|*.*';
  OpenDialog1.InitialDir := ACBrNFe1.Configuracoes.Geral.PathSalvar;
  if OpenDialog1.Execute then
  begin
    ACBrNFe1.NotasFiscais.Clear;
    ACBrNFe1.NotasFiscais.LoadFromFile(OpenDialog1.FileName);

    ACBrNFe1.NotasFiscais.GerarNFe;
    ACBrNFe1.Enviar(1,True);

    MemoResp.Lines.Text := UTF8Encode(ACBrNFe1.WebServices.Retorno.RetWS);
    memoRespWS.Lines.Text := UTF8Encode(ACBrNFe1.WebServices.Retorno.RetornoWS);
    LoadXML(MemoResp, WBResposta);

   MemoDados.Lines.Add('');
   MemoDados.Lines.Add('Envio NFe');
   MemoDados.Lines.Add('tpAmb: '+ TpAmbToStr(ACBrNFe1.WebServices.Retorno.TpAmb));
   MemoDados.Lines.Add('verAplic: '+ ACBrNFe1.WebServices.Retorno.verAplic);
   MemoDados.Lines.Add('cStat: '+ IntToStr(ACBrNFe1.WebServices.Retorno.cStat));
   MemoDados.Lines.Add('cUF: '+ IntToStr(ACBrNFe1.WebServices.Retorno.cUF));
   MemoDados.Lines.Add('xMotivo: '+ ACBrNFe1.WebServices.Retorno.xMotivo);
   MemoDados.Lines.Add('cMsg: '+ IntToStr(ACBrNFe1.WebServices.Retorno.cMsg));
   MemoDados.Lines.Add('xMsg: '+ ACBrNFe1.WebServices.Retorno.xMsg);
   MemoDados.Lines.Add('Recibo: '+ ACBrNFe1.WebServices.Retorno.Recibo);
   MemoDados.Lines.Add('Protocolo: '+ ACBrNFe1.WebServices.Retorno.Protocolo);
  end;
end;

procedure TForm1.btnCartadeCorrecaoClick(Sender: TObject);
var
 Chave, idLote, CNPJ, nSeqEvento, Correcao : string;
begin
  if not(InputQuery('WebServices Eventos: Carta de Correção', 'Chave da NF-e', Chave)) then
     exit;
  Chave := Trim(OnlyNumber(Chave));
  idLote := '1';
  if not(InputQuery('WebServices Eventos: Carta de Correção', 'Identificador de controle do Lote de envio do Evento', idLote)) then
     exit;
  CNPJ := copy(Chave,7,14);
  if not(InputQuery('WebServices Eventos: Carta de Correção', 'CNPJ ou o CPF do autor do Evento', CNPJ)) then
     exit;
  nSeqEvento := '1';
  if not(InputQuery('WebServices Eventos: Carta de Correção', 'Sequencial do evento para o mesmo tipo de evento', nSeqEvento)) then
     exit;
  Correcao := 'Correção a ser considerada, texto livre. A correção mais recente substitui as anteriores.';
  if not(InputQuery('WebServices Eventos: Carta de Correção', 'Correção a ser considerada', Correcao)) then
     exit;
  ACBrNFe1.EventoNFe.Evento.Clear;

  with ACBrNFe1.EventoNFe.Evento.Add do
   begin
     infEvento.chNFe := Chave;
     infEvento.CNPJ   := CNPJ;
     infEvento.dhEvento := now;
     infEvento.tpEvento := teCCe;
     infEvento.nSeqEvento := StrToInt(nSeqEvento);
     infEvento.detEvento.xCorrecao := Correcao;
   end;
  ACBrNFe1.EnviarEventoNFe(StrToInt(idLote));

  MemoResp.Lines.Text := UTF8Encode(ACBrNFe1.WebServices.EnvEvento.RetWS);
  LoadXML(MemoResp, WBResposta);
end;

procedure TForm1.btnValidarAssinaturaClick(Sender: TObject);
var
  Msg : String;
begin
  OpenDialog1.Title := 'Selecione a NFE';
  OpenDialog1.DefaultExt := '*-nfe.XML';
  OpenDialog1.Filter := 'Arquivos NFE (*-nfe.XML)|*-nfe.XML|Arquivos XML (*.XML)|*.XML|Todos os Arquivos (*.*)|*.*';
  OpenDialog1.InitialDir := ACBrNFe1.Configuracoes.Geral.PathSalvar;
  if OpenDialog1.Execute then
  begin
    ACBrNFe1.NotasFiscais.Clear;
    ACBrNFe1.NotasFiscais.LoadFromFile(OpenDialog1.FileName);
    if not ACBrNFe1.NotasFiscais.ValidaAssinatura(Msg) then
      MemoDados.Lines.Add('Erro: '+Msg)
    else
      ShowMessage('Assinatura Válida');  
  end;
end;

procedure TForm1.btnImprimirCCeClick(Sender: TObject);
begin
  OpenDialog1.Title := 'Selecione a NFE';
  OpenDialog1.DefaultExt := '*.XML';
  OpenDialog1.Filter := 'Arquivos XML (*.XML)|*.XML|Todos os Arquivos (*.*)|*.*';
  OpenDialog1.InitialDir := ACBrNFe1.Configuracoes.Geral.PathSalvar;
  if OpenDialog1.Execute then
  begin
    ACBrNFe1.NotasFiscais.Clear;
    ACBrNFe1.NotasFiscais.LoadFromFile(OpenDialog1.FileName);
  end;
    
  OpenDialog1.Title := 'Selecione o Evento';
  OpenDialog1.DefaultExt := '*.XML';
  OpenDialog1.Filter := 'Arquivos XML (*.XML)|*.XML|Todos os Arquivos (*.*)|*.*';
  OpenDialog1.InitialDir := ACBrNFe1.Configuracoes.Geral.PathSalvar;
  if OpenDialog1.Execute then
  begin
    ACBrNFe1.EventoNFe.Evento.Clear;
    ACBrNFe1.EventoNFe.LerXML(OpenDialog1.FileName) ;
    ACBrNFe1.ImprimirEvento;
  end;
//  LoadXML(MemoResp, WBResposta);
end;

procedure TForm1.btnEnviarEventoClick(Sender: TObject);
var
 Para : String;
 CC, Evento: Tstrings;
begin
  if not(InputQuery('Enviar Email', 'Email de destino', Para)) then
    exit;
    
  OpenDialog1.Title := 'Selecione a NFE';
  OpenDialog1.DefaultExt := '*.XML';
  OpenDialog1.Filter := 'Arquivos XML (*.XML)|*.XML|Todos os Arquivos (*.*)|*.*';
  OpenDialog1.InitialDir := ACBrNFe1.Configuracoes.Geral.PathSalvar;
  if OpenDialog1.Execute then
  begin
    ACBrNFe1.NotasFiscais.Clear;
    ACBrNFe1.NotasFiscais.LoadFromFile(OpenDialog1.FileName);
  end;

  OpenDialog1.Title := 'Selecione ao Evento';
  OpenDialog1.DefaultExt := '*.XML';
  OpenDialog1.Filter := 'Arquivos XML (*.XML)|*.XML|Todos os Arquivos (*.*)|*.*';
  OpenDialog1.InitialDir := ACBrNFe1.Configuracoes.Geral.PathSalvar;
  if OpenDialog1.Execute then
  begin
    Evento := TStringList.Create;
    Evento.Clear;
    Evento.Add(OpenDialog1.FileName);
    ACBrNFe1.EventoNFe.Evento.Clear;
    ACBrNFe1.EventoNFe.LerXML(OpenDialog1.FileName) ;
    CC:=TstringList.Create;
    CC.Add('andrefmoraes@gmail.com'); //especifique um email válido
    CC.Add('anfm@zipmail.com.br');    //especifique um email válido
    ACBrNFe1.EnviarEmailEvento(edtSmtpHost.Text
                             , edtSmtpPort.Text
                             , edtSmtpUser.Text
                             , edtSmtpPass.Text
                             , edtSmtpUser.Text
                             , Para
                             , edtEmailAssunto.Text
                             , mmEmailMsg.Lines
                             , cbEmailSSL.Checked // SSL - Conexão Segura
                             , True //Enviar PDF junto
                             , CC //Lista com emails que serão enviado cópias - TStrings
                             , Evento // Lista de anexos - TStrings
                             , False  //Pede confirmação de leitura do email
                             , False  //Aguarda Envio do Email(não usa thread)
                             , 'ACBrNFe2' // Nome do Rementente
                             , cbEmailSSL.Checked ); // Auto TLS
    CC.Free;
    Evento.Free;
  end;
end;

procedure TForm1.btnCriarEnviarNFCeClick(Sender: TObject);
var
 vAux, vNumLote, vSincrono : String;
 Sincrono : boolean;
begin
  if not(InputQuery('WebServices Enviar', 'Numero da Nota', vAux)) then
    exit;

  if not(InputQuery('WebServices Enviar', 'Numero do Lote', vNumLote)) then
    exit;

  vSincrono := '1';
  if not(InputQuery('WebServices Enviar', 'Envio Síncrono(1=Sim, 0=Não)', vSincrono)) then
    exit;

  vNumLote := OnlyNumber(vNumLote);

  if Trim(vNumLote) = '' then
   begin
     MessageDlg('Número do Lote inválido.',mtError,[mbok],0);
     exit;
   end;

  if (Trim(vSincrono) <> '1') and
     (Trim(vSincrono) <> '0') then
   begin
     MessageDlg('Valor Inválido.',mtError,[mbok],0);
     exit;
   end;

  if (Trim(vSincrono) = '1') then
    Sincrono := True
  else
    Sincrono := False;  

  ACBrNFe1.NotasFiscais.Clear;

  ACBrNFe1.Configuracoes.Geral.ModeloDF := moNFCe;  
  //GerarNFCe(vAux);

  ACBrNFe1.Enviar(vNumLote,True,Sincrono);

  MemoResp.Lines.Text := UTF8Encode(ACBrNFe1.WebServices.Retorno.RetWS);
  memoRespWS.Lines.Text := UTF8Encode(ACBrNFe1.WebServices.Retorno.RetornoWS);
  LoadXML(MemoResp, WBResposta);

  MemoDados.Lines.Add('');
  MemoDados.Lines.Add('Envio NFe');
  MemoDados.Lines.Add('tpAmb: '+ TpAmbToStr(ACBrNFe1.WebServices.Retorno.TpAmb));
  MemoDados.Lines.Add('verAplic: '+ ACBrNFe1.WebServices.Retorno.verAplic);
  MemoDados.Lines.Add('cStat: '+ IntToStr(ACBrNFe1.WebServices.Retorno.cStat));
  MemoDados.Lines.Add('cUF: '+ IntToStr(ACBrNFe1.WebServices.Retorno.cUF));
  MemoDados.Lines.Add('xMotivo: '+ ACBrNFe1.WebServices.Retorno.xMotivo);
  MemoDados.Lines.Add('cMsg: '+ IntToStr(ACBrNFe1.WebServices.Retorno.cMsg));
  MemoDados.Lines.Add('xMsg: '+ ACBrNFe1.WebServices.Retorno.xMsg);
  MemoDados.Lines.Add('Recibo: '+ ACBrNFe1.WebServices.Retorno.Recibo);
  MemoDados.Lines.Add('Protocolo: '+ ACBrNFe1.WebServices.Retorno.Protocolo);

  ACBrNFe1.NotasFiscais.Clear;
end;



procedure TForm1.tsNFeEnter(Sender: TObject);
begin
  ACBrNFe1.Configuracoes.Geral.ModeloDF := moNFe;
end;

procedure TForm1.tsNFCeEnter(Sender: TObject);
begin
  ACBrNFe1.Configuracoes.Geral.ModeloDF := moNFe;
end;

end.





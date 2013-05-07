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
  Mask, BrvCustomMaskEdit, BrvEditDate, IBCustomDataSet, IBTable, IBDatabase, Provider, BrvEdit,
  DBCtrls, BrvDBComboListBox, BrvComboBox, BrvDbEdit, BrvLabel, XDBCtrls;

type
  TForm1 = class(TForm)
    OpenDialog1: TOpenDialog;
    ACBrNFeDANFERave1: TACBrNFeDANFERave;
    BrvXMLNFE: TBrvXML;
    Label29: TLabel;
    DataSource1: TDataSource;
    xml: TClientDataSet;
    mxExcel: TmxDataSetExport;
    PopupMenu2: TPopupMenu;
    MenuItem1: TMenuItem;
    MenuItem2: TMenuItem;
    GerarPDF1: TMenuItem;
    Cancelar1: TMenuItem;
    Deletar1: TMenuItem;
    DataSource2: TDataSource;
    SQLConnection1: TSQLConnection;
    DtTitular: TDataSetProvider;
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
    Panel21: TPanel;
    BrvLabel1: TBrvLabel;
    BrvLabel3: TBrvLabel;
    BrvLabel4: TBrvLabel;
    BrvLabel5: TBrvLabel;
    BrvLabel6: TBrvLabel;
    BrvLabel8: TBrvLabel;
    BrvLabel10: TBrvLabel;
    BrvLabel11: TBrvLabel;
    BrvLabel50: TBrvLabel;
    BrvDbEdit1: TBrvDbEdit;
    BrvDbEdit2: TBrvDbEdit;
    BrvDbEdit3: TBrvDbEdit;
    EdtRsTitula: TBrvRetCon;
    BrvDbEdit4: TBrvDbEdit;
    BrvDbEdit8: TBrvDbEdit;
    BrvDbEdit10: TBrvDbEdit;
    BrvDbEdit41: TBrvDbEdit;
    BrvDbEdit43: TBrvDbEdit;
    GroupBox2: TGroupBox;
    CbxSnItens: TCheckBox;
    CbxSnConhec: TCheckBox;
    CbxSnCreImp: TCheckBox;
    BrvDbEdit42: TBrvDbEdit;
    EdtCdEstEmi: TBrvRetCon;
    EdtDsFiscal: TBrvRetCon;
    EdtCdFiscal: TBrvRetCon;
    PgcDadosNF: TPageControl;
    TbsItens: TTabSheet;
    DbgF002: TBrvDbGrid;
    TbsImpostos: TTabSheet;
    GroupBox3: TGroupBox;
    BrvLabel12: TBrvLabel;
    BrvLabel13: TBrvLabel;
    BrvLabel14: TBrvLabel;
    BrvDbEdit11: TBrvDbEdit;
    BrvDbEdit12: TBrvDbEdit;
    BrvDbEdit13: TBrvDbEdit;
    GroupBox4: TGroupBox;
    BrvLabel15: TBrvLabel;
    BrvLabel16: TBrvLabel;
    BrvDbEdit14: TBrvDbEdit;
    BrvDbEdit15: TBrvDbEdit;
    GroupBox5: TGroupBox;
    BrvLabel18: TBrvLabel;
    BrvDbEdit17: TBrvDbEdit;
    GroupBox6: TGroupBox;
    BrvLabel17: TBrvLabel;
    BrvLabel20: TBrvLabel;
    BrvLabel21: TBrvLabel;
    BrvLabel19: TBrvLabel;
    BrvDbEdit16: TBrvDbEdit;
    BrvDbEdit19: TBrvDbEdit;
    BrvDbEdit20: TBrvDbEdit;
    DBCheckBox1: TDBCheckBox;
    BrvDbEdit18: TBrvDbEdit;
    GrbTransp: TGroupBox;
    BrvLabel22: TBrvLabel;
    BrvLabel9: TBrvLabel;
    BrvLabel23: TBrvLabel;
    BrvDBComboListBox1: TBrvDBComboListBox;
    DBCheckBox2: TDBCheckBox;
    DBCheckBox3: TDBCheckBox;
    BrvDbEdit9: TBrvDbEdit;
    EdtRsTransp: TBrvRetCon;
    BrvDbEdit21: TBrvDbEdit;
    TbsRetencao: TTabSheet;
    GroupBox32: TGroupBox;
    BrvLabel30: TBrvLabel;
    BrvLabel32: TBrvLabel;
    BrvLabel35: TBrvLabel;
    BrvDbEdit28: TBrvDbEdit;
    BrvDbEdit27: TBrvDbEdit;
    BrvDbEdit26: TBrvDbEdit;
    GroupBox33: TGroupBox;
    BrvLabel33: TBrvLabel;
    BrvLabel36: TBrvLabel;
    BrvLabel37: TBrvLabel;
    BrvDbEdit29: TBrvDbEdit;
    BrvDbEdit30: TBrvDbEdit;
    BrvDbEdit31: TBrvDbEdit;
    GroupBox34: TGroupBox;
    BrvLabel31: TBrvLabel;
    BrvLabel34: TBrvLabel;
    BrvLabel38: TBrvLabel;
    BrvDbEdit32: TBrvDbEdit;
    BrvDbEdit33: TBrvDbEdit;
    BrvDbEdit34: TBrvDbEdit;
    GroupBox35: TGroupBox;
    BrvLabel39: TBrvLabel;
    BrvLabel40: TBrvLabel;
    BrvLabel41: TBrvLabel;
    BrvDbEdit35: TBrvDbEdit;
    BrvDbEdit37: TBrvDbEdit;
    BrvDbEdit36: TBrvDbEdit;
    GroupBox36: TGroupBox;
    BrvLabel42: TBrvLabel;
    BrvLabel43: TBrvLabel;
    BrvLabel44: TBrvLabel;
    BrvDbEdit38: TBrvDbEdit;
    BrvDbEdit39: TBrvDbEdit;
    BrvDbEdit40: TBrvDbEdit;
    GroupBox37: TGroupBox;
    BrvLabel45: TBrvLabel;
    BrvLabel46: TBrvLabel;
    BrvLabel47: TBrvLabel;
    BrvDbEdit22: TBrvDbEdit;
    BrvDbEdit23: TBrvDbEdit;
    BrvDbEdit24: TBrvDbEdit;
    GroupBox38: TGroupBox;
    CbxPIS: TCheckBox;
    CbxCOFINS: TCheckBox;
    CbxCSLL: TCheckBox;
    CbxIRRF: TCheckBox;
    CbxISSQN: TCheckBox;
    CbxINSS: TCheckBox;
    TbsParcela: TTabSheet;
    DbgN003: TBrvDbGrid;
    Panel22: TPanel;
    BtnGerParcel: TBrvBitBtn;
    BtnCancelParcel: TBrvBitBtn;
    TbsDadAdi: TTabSheet;
    DBMemo1: TDBMemo;
    Panel23: TPanel;
    Bevel2: TBevel;
    GroupBox39: TGroupBox;
    BrvLabel27: TBrvLabel;
    BrvLabel2: TBrvLabel;
    BrvLabel28: TBrvLabel;
    BrvDbEdit6: TBrvDbEdit;
    BrvDbEdit25: TBrvDbEdit;
    EdtTtProdut: TBrvEditNum;
    BtnSalvar: TBrvBitBtn;
    BtnCancelar: TBrvBitBtn;
    gbGeral: TGroupBox;
    Label59: TLabel;
    sbtnLogoMarca: TSpeedButton;
    sbtnPathSalvar: TSpeedButton;
    lblSchemas: TLabel;
    sbtSchemas: TSpeedButton;
    Label60: TLabel;
    sbtnPrestLogo: TSpeedButton;
    Label61: TLabel;
    Label62: TLabel;
    Edit5: TEdit;
    Edit7: TEdit;
    CheckBox12: TCheckBox;
    edtSchemas: TEdit;
    edtPrestLogo: TEdit;
    edtPrefeitura: TEdit;
    edtEmitCidade: TComboBox;
    Label63: TLabel;
    edtSenhaWeb: TEdit;
    Label64: TLabel;
    edtUserWeb: TEdit;
    CheckBox13: TCheckBox;
    ComboBox1: TComboBox;
    GroupBox45: TGroupBox;
    ComboBox2: TComboBox;
    PopupMenu1: TPopupMenu;
    Detalhar1: TMenuItem;
    Imprimir1: TMenuItem;
    CheckBox14: TCheckBox;
    Label65: TLabel;
    MaskEdit2: TMaskEdit;
    Label66: TLabel;
    ComboBox3: TComboBox;
    Edit8: TEdit;
    Edit9: TEdit;
    Label67: TLabel;
    Edit10: TEdit;
    Edit11: TEdit;
    Label68: TLabel;
    Label69: TLabel;
    Edit12: TEdit;
    Label71: TLabel;
    Edit14: TEdit;
    Edit15: TEdit;
    Label72: TLabel;
    Label73: TLabel;
    Edit16: TEdit;
    Label75: TLabel;
    Memo10: TMemo;
    CheckBox36: TCheckBox;
    TabSheet38: TTabSheet;
    TabSheet41: TTabSheet;
    PageControl9: TPageControl;
    TabSheet42: TTabSheet;
    PageControl10: TPageControl;
    TabSheet43: TTabSheet;
    TabSheet44: TTabSheet;
    Panel26: TPanel;
    Panel27: TPanel;
    Panel28: TPanel;
    Panel29: TPanel;
    BrvDbGrid3: TBrvDbGrid;
    Panel30: TPanel;
    XDBNavigator1: TXDBNavigator;
    Panel31: TPanel;
    Panel32: TPanel;
    Panel33: TPanel;
    Panel34: TPanel;
    BrvDbGrid5: TBrvDbGrid;
    Panel35: TPanel;
    XDBNavigator2: TXDBNavigator;
    TabSheet45: TTabSheet;
    Panel36: TPanel;
    Panel37: TPanel;
    Panel38: TPanel;
    Panel39: TPanel;
    BrvDbGrid6: TBrvDbGrid;
    Panel40: TPanel;
    XDBNavigator3: TXDBNavigator;
    TabSheet46: TTabSheet;
    Panel41: TPanel;
    Panel42: TPanel;
    Panel43: TPanel;
    Panel44: TPanel;
    BrvDbGrid7: TBrvDbGrid;
    Panel45: TPanel;
    XDBNavigator4: TXDBNavigator;
    Panel46: TPanel;
    Panel47: TPanel;
    Panel48: TPanel;
    Panel49: TPanel;
    BrvDbGrid9: TBrvDbGrid;
    Panel50: TPanel;
    XDBNavigator5: TXDBNavigator;
    TabSheet47: TTabSheet;
    Panel51: TPanel;
    Panel52: TPanel;
    Panel53: TPanel;
    BrvDbGrid10: TBrvDbGrid;
    Panel55: TPanel;
    GroupBox46: TGroupBox;
    CheckBox37: TCheckBox;
    CheckBox38: TCheckBox;
    CheckBox39: TCheckBox;
    CheckBox40: TCheckBox;
    GroupBox47: TGroupBox;
    CheckBox41: TCheckBox;
    CheckBox42: TCheckBox;
    GroupBox48: TGroupBox;
    Label70: TLabel;
    Label74: TLabel;
    BrvEditNum9: TBrvEditNum;
    BrvEditNum10: TBrvEditNum;
    BrvEditNum13: TBrvEditNum;
    Label111: TLabel;
    GroupBox49: TGroupBox;
    CheckBox43: TCheckBox;
    CheckBox44: TCheckBox;
    GroupBox50: TGroupBox;
    CheckBox45: TCheckBox;
    CheckBox46: TCheckBox;
    CheckBox47: TCheckBox;
    CheckBox48: TCheckBox;
    GroupBox51: TGroupBox;
    CheckBox49: TCheckBox;
    CheckBox50: TCheckBox;
    GroupBox52: TGroupBox;
    CheckBox51: TCheckBox;
    CheckBox52: TCheckBox;
    GroupBox53: TGroupBox;
    Label109: TLabel;
    Label110: TLabel;
    Label112: TLabel;
    BrvEditNum11: TBrvEditNum;
    BrvEditNum12: TBrvEditNum;
    BrvEditNum14: TBrvEditNum;
    GroupBox54: TGroupBox;
    CheckBox53: TCheckBox;
    CheckBox54: TCheckBox;
    CheckBox55: TCheckBox;
    CheckBox56: TCheckBox;
    GroupBox55: TGroupBox;
    CheckBox57: TCheckBox;
    CheckBox58: TCheckBox;
    GroupBox56: TGroupBox;
    CheckBox59: TCheckBox;
    CheckBox60: TCheckBox;
    GroupBox57: TGroupBox;
    Label113: TLabel;
    Label114: TLabel;
    Label115: TLabel;
    BrvEditNum15: TBrvEditNum;
    BrvEditNum16: TBrvEditNum;
    BrvEditNum17: TBrvEditNum;
    GroupBox58: TGroupBox;
    CheckBox61: TCheckBox;
    CheckBox62: TCheckBox;
    CheckBox63: TCheckBox;
    CheckBox64: TCheckBox;
    GroupBox59: TGroupBox;
    CheckBox65: TCheckBox;
    CheckBox66: TCheckBox;
    GroupBox60: TGroupBox;
    CheckBox67: TCheckBox;
    CheckBox68: TCheckBox;
    GroupBox61: TGroupBox;
    Label116: TLabel;
    Label117: TLabel;
    Label118: TLabel;
    BrvEditNum18: TBrvEditNum;
    BrvEditNum19: TBrvEditNum;
    BrvEditNum20: TBrvEditNum;
    SqlDSTitula: TSQLDataSet;
    DSTitula: TDataSource;
    DtProduto: TDataSetProvider;
    SqlDSProduto: TSQLDataSet;
    DSProduto: TDataSource;
    SqlDSTitulaCDTITULA: TIntegerField;
    SqlDSTitulaTPTITULA: TStringField;
    SqlDSTitulaRAZAOSOCIAL: TStringField;
    SqlDSTitulaCNPJ: TStringField;
    SqlDSTitulaCODCIDADE: TIntegerField;
    SqlDSTitulaIM: TStringField;
    SqlDSTitulaIE: TStringField;
    SqlDSTitulaNOMEFANTASIA: TStringField;
    SqlDSTitulaSTATUS: TStringField;
    SqlDSTitulaCLIENTE: TStringField;
    SqlDSTitulaFORNECEDOR: TStringField;
    SqlDSTitulaCONTADOR: TStringField;
    SqlDSTitulaTRANSPORTADOR: TStringField;
    Label1: TLabel;
    DBEdit1: TDBEdit;
    Label2: TLabel;
    DBEdit2: TDBEdit;
    Label3: TLabel;
    DBEdit3: TDBEdit;
    Label4: TLabel;
    DBEdit4: TDBEdit;
    Label5: TLabel;
    DBEdit5: TDBEdit;
    Label6: TLabel;
    DBEdit6: TDBEdit;
    Label7: TLabel;
    DBEdit7: TDBEdit;
    Label8: TLabel;
    DBEdit8: TDBEdit;
    Label9: TLabel;
    DBEdit9: TDBEdit;
    Label10: TLabel;
    DBEdit10: TDBEdit;
    Label11: TLabel;
    DBEdit11: TDBEdit;
    Label12: TLabel;
    DBEdit12: TDBEdit;
    Label13: TLabel;
    DBEdit13: TDBEdit;
    DSUniMed: TDataSource;
    SqlDSUniMed: TSQLDataSet;
    DTUniMed: TDataSetProvider;
    SQLTable2: TSQLTable;
    SQLTable2CDTITULA: TIntegerField;
    SQLTable2TPTITULA: TStringField;
    SQLTable2RAZAOSOCIAL: TStringField;
    SQLTable2CNPJ: TStringField;
    SQLTable2CODCIDADE: TIntegerField;
    SQLTable2IM: TStringField;
    SQLTable2IE: TStringField;
    SQLTable2NOMEFANTASIA: TStringField;
    SQLTable2STATUS: TStringField;
    SQLTable2CLIENTE: TStringField;
    SQLTable2FORNECEDOR: TStringField;
    SQLTable2CONTADOR: TStringField;
    SQLTable2TRANSPORTADOR: TStringField;
    procedure sbtnCaminhoCertClick(Sender: TObject);
    procedure sbtnLogoMarcaClick(Sender: TObject);
    procedure sbtnPathSalvarClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure btnSalvarConfigClick(Sender: TObject);
    procedure btnStatusServClick(Sender: TObject);
    procedure btnConsCadClick(Sender: TObject);
    procedure ACBrNFe1GerarLog(const Mensagem: String);
    procedure lblMouseEnter(Sender: TObject);
    procedure lblMouseLeave(Sender: TObject);
    procedure btnConsultarChaveClick(Sender: TObject);
    procedure btnCancelarChaveClick(Sender: TObject);
    procedure btnNfeDestinadasClick(Sender: TObject);
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
   procedure LerConfiguracaoAqui();

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

//procedure TForm1.LerConfiguracaoAqui();
//Var IniFile  : String ;
//    Ini     : TIniFile ;
//    Ok : Boolean;
//    StreamMemo : TMemoryStream;
//begin
//  IniFile := ChangeFileExt( Application.ExeName, '.ini') ;
//
//  Ini := TIniFile.Create( IniFile );
//  try
//      edtCaminho.Text  := Ini.ReadString( 'Certificado','Caminho' ,'') ;
//      edtSenha.Text    := Ini.ReadString( 'Certificado','Senha'   ,'') ;
//
//      rgFormaEmissao.ItemIndex := Ini.ReadInteger( 'Geral','FormaEmissao',0) ;
//      ckSalvar.Checked    := Ini.ReadBool(   'Geral','Salvar'      ,True) ;
//      edtPathLogs.Text    := Ini.ReadString( 'Geral','PathSalvar'  ,'') ;
//
//      cbUF.ItemIndex       := cbUF.Items.IndexOf(Ini.ReadString( 'WebService','UF','SP')) ;
//      rgTipoAmb.ItemIndex  := Ini.ReadInteger( 'WebService','Ambiente'  ,0) ;
//      ckVisualizar.Checked :=Ini.ReadBool(    'WebService','Visualizar',False) ;
//
//      edtProxyHost.Text  := Ini.ReadString( 'Proxy','Host'   ,'') ;
//      edtProxyPorta.Text := Ini.ReadString( 'Proxy','Porta'  ,'') ;
//      edtProxyUser.Text  := Ini.ReadString( 'Proxy','User'   ,'') ;
//      edtProxySenha.Text := Ini.ReadString( 'Proxy','Pass'   ,'') ;
//
//
//      rgTipoDanfe.ItemIndex     := Ini.ReadInteger( 'Geral','DANFE'       ,0) ;
//      edtLogoMarca.Text         := Ini.ReadString( 'Geral','LogoMarca'   ,'') ;
//
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
//      StreamMemo := TMemoryStream.Create;
//      Ini.ReadBinaryStream( 'Email','Mensagem',StreamMemo) ;
//      mmEmailMsg.Lines.LoadFromStream(StreamMemo);
//      StreamMemo.Free;
//  finally
//     Ini.Free ;
//  end;
//
//end;
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

      Ini.WriteInteger('Geral','DANFE'       ,rgTipoDanfe.ItemIndex) ;
      Ini.WriteInteger('Geral','FormaEmissao',rgFormaEmissao.ItemIndex) ;
      Ini.WriteString( 'Geral','LogoMarca'   ,edtLogoMarca.Text) ;
      Ini.WriteBool(   'Geral','Salvar'      ,ckSalvar.Checked) ;
      Ini.WriteString( 'Geral','PathSalvar'  ,edtPathLogs.Text) ;
      Ini.WriteString( 'Geral','Schemas'   , edtSchemas.Text);
      Ini.WriteString( 'Geral','LogoMarcaNFSe' , edtLogoMarca.Text);
      Ini.WriteString( 'Geral','PrestLogo' , edtPrestLogo.Text);
      Ini.WriteString( 'Geral','Prefeitura', edtPrefeitura.Text);


      Ini.WriteString('WebService', 'SenhaWeb'  , edtSenhaWeb.Text);
      Ini.WriteString('WebService', 'UserWeb'   , edtUserWeb.Text);
      Ini.WriteString('WebService','UF'        ,cbUF.Text) ;
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

      Ini.WriteBool( 'Boleto','tela' ,CheckBox14.Checked) ;
      Ini.WriteBool( 'Boleto','remessa' ,CheckBox36.Checked) ;
      Ini.WriteString( 'Boleto','banco' ,Trim(Copy(ComboBox2.Text,1,3))) ;
      Ini.WriteString( 'Boleto','NrBoletos' ,MaskEdit2.Text) ;
      Ini.WriteInteger( 'Boleto','Layout' ,ComboBox2.ItemIndex) ;
      Ini.WriteString( 'Boleto','CodAgencia' ,Edit8.Text) ;
      Ini.WriteString( 'Boleto','DigAgencia' ,Edit9.Text) ;
      Ini.WriteString( 'Boleto','CodCedente' ,Edit10.Text) ;
      Ini.WriteString( 'Boleto','DigCedente' ,Edit11.Text) ;
      Ini.WriteString( 'Boleto','NossoNumero' ,Edit12.Text) ;
      Ini.WriteString( 'Boleto','NrDoc' ,Edit14.Text) ;
      Ini.WriteString( 'Boleto','Carteira' ,Edit15.Text) ;
      Ini.WriteString( 'Boleto','Convenio' ,Edit16.Text) ;
      Ini.WriteString( 'Boleto','Intrucoes' ,Memo10.Lines.Text) ;

      StreamMemo := TMemoryStream.Create;
      mmEmailMsg.Lines.SaveToStream(StreamMemo);
      StreamMemo.Seek(0,soFromBeginning);
      Ini.WriteBinaryStream( 'Email','Mensagem',StreamMemo) ;
      StreamMemo.Free;
  finally
     Ini.Free ;
  end;

end;

procedure TForm1.LerConfiguracaoAqui();
Var IniFile  : String ;
    Ini     : TIniFile ;
    Ok : Boolean;
    StreamMemo : TMemoryStream;
begin
  IniFile := ChangeFileExt( Application.ExeName, '.ini') ;

  Ini := TIniFile.Create( IniFile );
  try

         edtCaminho.Text  := Ini.ReadString( 'Certificado','Caminho' ,'') ;
         edtSenha.Text    := Ini.ReadString( 'Certificado','Senha'   ,'') ;


      rgFormaEmissao.ItemIndex := Ini.ReadInteger( 'Geral','FormaEmissao',0) ;
      ckSalvar.Checked    := Ini.ReadBool(   'Geral','Salvar'      ,True) ;
      edtPathLogs.Text    := Ini.ReadString( 'Geral','PathSalvar'  ,'') ;

      cbUF.ItemIndex       := cbUF.Items.IndexOf(Ini.ReadString( 'WebService','UF','SP')) ;
      rgTipoAmb.ItemIndex  := Ini.ReadInteger( 'WebService','Ambiente'  ,0) ;
      ckVisualizar.Checked :=Ini.ReadBool(    'WebService','Visualizar',False) ;

      edtProxyHost.Text  := Ini.ReadString( 'Proxy','Host'   ,'') ;
      edtProxyPorta.Text := Ini.ReadString( 'Proxy','Porta'  ,'') ;
      edtProxyUser.Text  := Ini.ReadString( 'Proxy','User'   ,'') ;
      edtProxySenha.Text := Ini.ReadString( 'Proxy','Pass'   ,'') ;


      rgTipoDanfe.ItemIndex     := Ini.ReadInteger( 'Geral','DANFE'       ,0) ;
      edtLogoMarca.Text         := Ini.ReadString( 'Geral','LogoMarca'   ,'') ;

      edtEmitCNPJ.Text       := Ini.ReadString( 'Emitente','CNPJ'       ,'') ;
      edtEmitIE.Text         := Ini.ReadString( 'Emitente','IE'         ,'') ;
      edtEmitRazao.Text      := Ini.ReadString( 'Emitente','RazaoSocial','') ;
      edtEmitFantasia.Text   := Ini.ReadString( 'Emitente','Fantasia'   ,'') ;
      edtEmitFone.Text       := Ini.ReadString( 'Emitente','Fone'       ,'') ;
      edtEmitCEP.Text        := Ini.ReadString( 'Emitente','CEP'        ,'') ;
      edtEmitLogradouro.Text := Ini.ReadString( 'Emitente','Logradouro' ,'') ;
      edtEmitNumero.Text     := Ini.ReadString( 'Emitente','Numero'     ,'') ;
      edtEmitComp.Text       := Ini.ReadString( 'Emitente','Complemento','') ;
      edtEmitBairro.Text     := Ini.ReadString( 'Emitente','Bairro'     ,'') ;
      edtEmitCodCidade.Text  := Ini.ReadString( 'Emitente','CodCidade'  ,'') ;
      edtEmitCidade.Text     :=Ini.ReadString( 'Emitente','Cidade'     ,'') ;
      edtEmitUF.Text         := Ini.ReadString( 'Emitente','UF'         ,'') ;

      edtSmtpHost.Text      := Ini.ReadString( 'Email','Host'   ,'') ;
      edtSmtpPort.Text      := Ini.ReadString( 'Email','Port'   ,'') ;
      edtSmtpUser.Text      := Ini.ReadString( 'Email','User'   ,'') ;
      edtSmtpPass.Text      := Ini.ReadString( 'Email','Pass'   ,'') ;
      edtEmailAssunto.Text  := Ini.ReadString( 'Email','Assunto','') ;
      cbEmailSSL.Checked    := Ini.ReadBool(   'Email','SSL'    ,False) ;

      EdtDsArquiv.Text := Ini.ReadString( 'Import','DiretorioImportacao' ,'') ;
      Edit3.Text       := Ini.ReadString( 'Import','DiretorioExportacao' ,'') ;
      Edit4.Text       := Ini.ReadString( 'Import','TempoImpotacao'      ,'') ;
      RadioGroup2.ItemIndex := StrToInt(Ini.ReadString( 'Import','Importacao',''));

      CheckBox14.Checked := Ini.ReadBool( 'Boleto','tela' ,false) ;
      CheckBox36.Checked := Ini.ReadBool( 'Boleto','remessa' ,false) ;
      ComboBox2.Text     := Ini.ReadString( 'Boleto','banco' ,'') ;
      MaskEdit2.Text := Ini.ReadString( 'Boleto','NrBoletos' ,'') ;
      ComboBox2.ItemIndex := Ini.ReadInteger( 'Boleto','Layout' ,0) ;
      Edit8.Text  :=  Ini.ReadString( 'Boleto','CodAgencia' ,'') ;
      Edit9.Text  :=  Ini.ReadString( 'Boleto','DigAgencia' ,'') ;
      Edit10.Text := Ini.ReadString( 'Boleto','CodCedente' ,'') ;
      Edit11.Text := Ini.ReadString( 'Boleto','DigCedente' ,'') ;
      Edit12.Text := Ini.ReadString( 'Boleto','NossoNumero' ,'') ;
      Edit14.Text := Ini.ReadString( 'Boleto','NrDoc' ,'') ;
      Edit15.Text  :=  Ini.ReadString( 'Boleto','Carteira' ,'') ;
      Edit16.Text  := Ini.ReadString( 'Boleto','Convenio' ,'') ;
      Memo10.Lines.Text := Ini.ReadString( 'Boleto','Intrucoes' ,'') ;

      StreamMemo := TMemoryStream.Create;
      Ini.ReadBinaryStream( 'Email','Mensagem',StreamMemo) ;
      mmEmailMsg.Lines.LoadFromStream(StreamMemo);
      StreamMemo.Free;
  finally
     Ini.Free ;
  end;

end;

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

      xml := ListarXmlDiretorio(EdtDsArquiv.Text,EdtDsArquiv.Text+'\teste');
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
       LerConfiguracaoAqui;
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
 LerConfiguracaoAqui;
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

procedure TForm1.btnNfeDestinadasClick(Sender: TObject);
begin        // NfeDestinadas(NrSenha,NrCertificado,uf,CNPJ, IndNFe, IndEmi, ultNSU:String): OleVariant;
  gerarDadosInicial;
  xml.data := ACBrNFe_NfeDestinadas(NrSenha,NrCertif,CdEstado,CjEmpres,'2','0','0',TpAmbiente,BoVisualizar);
  xml.Open;
  DataSource1.DataSet := xml;
end;


//procedure TForm1.ACBrNFe1StatusChange(Sender: TObject);
//begin
//  case ACBrNFe1.Status of
//    stIdle :
//    begin
//      if ( frmStatus <> nil ) then
//        frmStatus.Hide;
//    end;
//    stNFeStatusServico :
//    begin
//      if ( frmStatus = nil ) then
//        frmStatus := TfrmStatus.Create(Application);
//      frmStatus.lblStatus.Caption := 'Verificando Status do servico...';
//      frmStatus.Show;
//      frmStatus.BringToFront;
//    end;
//    stNFeRecepcao :
//    begin
//      if ( frmStatus = nil ) then
//        frmStatus := TfrmStatus.Create(Application);
//      frmStatus.lblStatus.Caption := 'Enviando dados da NFe...';
//      frmStatus.Show;
//      frmStatus.BringToFront;
//    end;
//    stNfeRetRecepcao :
//    begin
//      if ( frmStatus = nil ) then
//        frmStatus := TfrmStatus.Create(Application);
//      frmStatus.lblStatus.Caption := 'Recebendo dados da NFe...';
//      frmStatus.Show;
//      frmStatus.BringToFront;
//    end;
//    stNfeConsulta :
//    begin
//      if ( frmStatus = nil ) then
//        frmStatus := TfrmStatus.Create(Application);
//      frmStatus.lblStatus.Caption := 'Consultando NFe...';
//      frmStatus.Show;
//      frmStatus.BringToFront;
//    end;
//    stNfeCancelamento :
//    begin
//      if ( frmStatus = nil ) then
//        frmStatus := TfrmStatus.Create(Application);
//      frmStatus.lblStatus.Caption := 'Enviando cancelamento de NFe...';
//      frmStatus.Show;
//      frmStatus.BringToFront;
//    end;
//    stNfeInutilizacao :
//    begin
//      if ( frmStatus = nil ) then
//        frmStatus := TfrmStatus.Create(Application);
//      frmStatus.lblStatus.Caption := 'Enviando pedido de Inutilização...';
//      frmStatus.Show;
//      frmStatus.BringToFront;
//    end;
//    stNFeRecibo :
//    begin
//      if ( frmStatus = nil ) then
//        frmStatus := TfrmStatus.Create(Application);
//      frmStatus.lblStatus.Caption := 'Consultando Recibo de Lote...';
//      frmStatus.Show;
//      frmStatus.BringToFront;
//    end;
//    stNFeCadastro :
//    begin
//      if ( frmStatus = nil ) then
//        frmStatus := TfrmStatus.Create(Application);
//      frmStatus.lblStatus.Caption := 'Consultando Cadastro...';
//      frmStatus.Show;
//      frmStatus.BringToFront;
//    end;
//    stNFeEnvDPEC :
//    begin
//      if ( frmStatus = nil ) then
//        frmStatus := TfrmStatus.Create(Application);
//      frmStatus.lblStatus.Caption := 'Enviando DPEC...';
//      frmStatus.Show;
//      frmStatus.BringToFront;
//    end;
//    stNFeConsultaDPEC :
//    begin
//      if ( frmStatus = nil ) then
//        frmStatus := TfrmStatus.Create(Application);
//      frmStatus.lblStatus.Caption := 'Consultando DPEC...';
//      frmStatus.Show;
//      frmStatus.BringToFront;
//    end;
//    stNFeEmail :
//    begin
//      if ( frmStatus = nil ) then
//        frmStatus := TfrmStatus.Create(Application);
//      frmStatus.lblStatus.Caption := 'Enviando Email...';
//      frmStatus.Show;
//      frmStatus.BringToFront;
//    end;
//    stNFeCCe :
//    begin
//      if ( frmStatus = nil ) then
//        frmStatus := TfrmStatus.Create(Application);
//      frmStatus.lblStatus.Caption := 'Enviando Carta de Correção...';
//      frmStatus.Show;
//      frmStatus.BringToFront;
//    end;
//    stNFeEvento :
//    begin
//      if ( frmStatus = nil ) then
//        frmStatus := TfrmStatus.Create(Application);
//      frmStatus.lblStatus.Caption := 'Enviando Evento...';
//      frmStatus.Show;
//      frmStatus.BringToFront;
//    end;
//  end;
//  Application.ProcessMessages;
//end;

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

procedure TForm1.ACBrNFe1GerarLog(const Mensagem: String);
begin
 memoLog.Lines.Add(Mensagem);
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

end.





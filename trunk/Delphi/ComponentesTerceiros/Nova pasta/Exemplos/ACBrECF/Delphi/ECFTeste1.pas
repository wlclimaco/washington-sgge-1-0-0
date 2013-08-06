{$I ACBr.inc}

unit ECFTeste1;

interface

uses ACBrECF, ACBrRFD, ACBrBase, ACBrDevice, ACBrECFClass, ACBrConsts,
  {$IFDEF Delphi6_UP} StrUtils, DateUtils, Types, {$ELSE} ACBrD5, FileCtrl,{$ENDIF}
  SysUtils, Classes, Graphics,
  Controls, Forms, Dialogs, StdCtrls,  ComCtrls, Buttons, ExtCtrls,
  Menus, Spin, jpeg, OleCtrls, SHDocVw
  {$IFDEF Delphi7},XPMan{$ENDIF}, ACBrAAC;

type
  TForm1 = class(TForm)
    StatusBar1: TStatusBar;
    MainMenu1: TMainMenu;
    Principal1: TMenuItem;
    Sair1: TMenuItem;
    Sobre1: TMenuItem;
    N1: TMenuItem;
    Ativcar1: TMenuItem;
    Desativar1: TMenuItem;
    N2: TMenuItem;
    Testar1: TMenuItem;
    Variaveis1: TMenuItem;
    DataHora1: TMenuItem;
    NumECF1: TMenuItem;
    NSrie1: TMenuItem;
    NVerso1: TMenuItem;
    N3: TMenuItem;
    PoucoPapel1: TMenuItem;
    Relatrios1: TMenuItem;
    LeituraX1: TMenuItem;
    ReduoZ1: TMenuItem;
    N4: TMenuItem;
    AliquotasICMS1: TMenuItem;
    FormasdePagamento1: TMenuItem;
    AbrirCupom1: TMenuItem;
    N5: TMenuItem;
    VenderItem1: TMenuItem;
    CancelarItemVendido1: TMenuItem;
    N6: TMenuItem;
    Sub1: TMenuItem;
    EfetuarPagamento1: TMenuItem;
    FecharCupom1: TMenuItem;
    CancelaCupom1: TMenuItem;
    N8: TMenuItem;
    Variveis1: TMenuItem;
    NUltimoCupom1: TMenuItem;
    SubTotal1: TMenuItem;
    TotalPago1: TMenuItem;
    N9: TMenuItem;
    RelatorioGerencial1: TMenuItem;
    N10: TMenuItem;
    FechaRelatrio1: TMenuItem;
    Dispositivos1: TMenuItem;
    Gaveta1: TMenuItem;
    GavetaAberta1: TMenuItem;
    AbreGaveta1: TMenuItem;
    Cheque1: TMenuItem;
    CancelaImpressoCheque1: TMenuItem;
    ImprimeCheque1: TMenuItem;
    ChequePronto1: TMenuItem;
    Utilitrios1: TMenuItem;
    HorarioVerao1: TMenuItem;
    ImpactoAgulhas1: TMenuItem;
    N7: TMenuItem;
    EnviaComando1: TMenuItem;
    TestaPodeAbrirCupom1: TMenuItem;
    ACBrECF1: TACBrECF;
    CarregaComprovantesNAOFiscais1: TMenuItem;
    HorarioVerao2: TMenuItem;
    Arredonda1: TMenuItem;
    MudaArredondamento1: TMenuItem;
    NumLoja1: TMenuItem;
    NumCRO1: TMenuItem;
    N11: TMenuItem;
    TestedeVelocidade1: TMenuItem;
    N12: TMenuItem;
    LeituradeMemoriaFiscal1: TMenuItem;
    CapturaporNReduaoZ1: TMenuItem;
    CapturaporPeriodo1: TMenuItem;
    ImprimeporNReduaoZ1: TMenuItem;
    ImprimeporPeriodo1: TMenuItem;
    ProgramaAliquota1: TMenuItem;
    N13: TMenuItem;
    ProgramaComprovanteNAOFiscal1: TMenuItem;
    ProgramaFormadePagamento1: TMenuItem;
    CorrigeEstadodeErro1: TMenuItem;
    N14: TMenuItem;
    CarregaUnidadesdeMedida1: TMenuItem;
    ProgramaUnidadeMedida1: TMenuItem;
    N15: TMenuItem;
    AbreRelatorioGerencial1: TMenuItem;
    ImprimeLinhaRelatorio1: TMenuItem;
    ListaRelatorioGerencial1: TMenuItem;
    N17: TMenuItem;
    PularLinhas1: TMenuItem;
    N18: TMenuItem;
    LerTodasasVariveis1: TMenuItem;
    MFD1: TMenuItem;
    Termica1: TMenuItem;
    PageControl1: TPageControl;
    TabSheet1: TTabSheet;
    TabSheet2: TTabSheet;
    mResp: TMemo;
    Panel1: TPanel;
    mBobina: TMemo;
    Panel2: TPanel;
    cbMemoHTML: TCheckBox;
    bBobinaParams: TButton;
    bBobinaLimpar: TButton;
    Equipamento1: TMenuItem;
    N19: TMenuItem;
    Flags1: TMenuItem;
    MapaResumo1: TMenuItem;
    DadosReducaoZ1: TMenuItem;
    N20: TMenuItem;
    CNPJIE1: TMenuItem;
    NumCRZ1: TMenuItem;
    NumCOOInicial1: TMenuItem;
    VendaBruta1: TMenuItem;
    GrandeTotal1: TMenuItem;
    TotalCancelamentos1: TMenuItem;
    TotalDescontos1: TMenuItem;
    TotalAcrescimos1: TMenuItem;
    N21: TMenuItem;
    N22: TMenuItem;
    TotalSubstituicaoTributaria1: TMenuItem;
    TotalNaoTributado1: TMenuItem;
    TotalIsencao1: TMenuItem;
    Aliquotas1: TMenuItem;
    LerTotaisAliquotas1: TMenuItem;
    FormasdePagamento2: TMenuItem;
    ComprovantesNaoFiscais1: TMenuItem;
    LerTotaisFormadePagamento1: TMenuItem;
    LerTotaisComprovanetNaoFiscal1: TMenuItem;
    UltimoItemVendido1: TMenuItem;
    N23: TMenuItem;
    LeituraMFD1: TMenuItem;
    PorCOO1: TMenuItem;
    PorPeriodo1: TMenuItem;
    Estado1: TMenuItem;
    Cupom1: TMenuItem;
    CupomVinculado1: TMenuItem;
    CupomVinculadoCompleto2: TMenuItem;
    N27: TMenuItem;
    AbreCupomVinculado1: TMenuItem;
    ImprimeLinhaCupomVinculado2: TMenuItem;
    NoFiscal1: TMenuItem;
    NoFiscalCompleto1: TMenuItem;
    N16: TMenuItem;
    AbreNoFiscal1: TMenuItem;
    RegistraItemNaoFiscal1: TMenuItem;
    SubTotalizaNaoFiscal1: TMenuItem;
    EfetuaPagamentoNaoFiscal1: TMenuItem;
    FechaNoFiscal1: TMenuItem;
    N24: TMenuItem;
    CancelaNoFiscal1: TMenuItem;
    NumCCF1: TMenuItem;
    NumCOO1: TMenuItem;
    N25: TMenuItem;
    IdentificaConsumidor1: TMenuItem;
    ACBrRFD1: TACBrRFD;
    TabSheet3: TTabSheet;
    TabSheet4: TTabSheet;
    Label1: TLabel;
    Label4: TLabel;
    Label5: TLabel;
    Label7: TLabel;
    cbxModelo: TComboBox;
    cbxPorta: TComboBox;
    chTentar: TCheckBox;
    chBloqueia: TCheckBox;
    chExibeMsg: TCheckBox;
    chArredondaPorQtd: TCheckBox;
    chGavetaSinalInvertido: TCheckBox;
    Label6: TLabel;
    mMsg: TMemo;
    Label9: TLabel;
    edLog: TEdit;
    SbArqLog: TSpeedButton;
    Label2: TLabel;
    mEnviado: TMemo;
    Label17: TLabel;
    pBotoes: TPanel;
    bAtivar: TBitBtn;
    PageControl2: TPageControl;
    TabSheet5: TTabSheet;
    TabSheet6: TTabSheet;
    Label8: TLabel;
    Label13: TLabel;
    edSH_RazaoSocial: TEdit;
    edSH_COO: TEdit;
    Label10: TLabel;
    Label11: TLabel;
    Label12: TLabel;
    edSH_CNPJ: TEdit;
    edSH_IE: TEdit;
    edSH_IM: TEdit;
    Label14: TLabel;
    edSH_Aplicativo: TEdit;
    Label15: TLabel;
    edSH_NumeroAP: TEdit;
    Label16: TLabel;
    edSH_VersaoAP: TEdit;
    Label18: TLabel;
    edSH_Linha1: TEdit;
    Label19: TLabel;
    edSH_Linha2: TEdit;
    Panel3: TPanel;
    chRFD: TCheckBox;
    Label3: TLabel;
    edDirRFD: TEdit;
    sbDirRFD: TSpeedButton;
    Panel4: TPanel;
    bRFDLer: TButton;
    mRFDParam: TMemo;
    bRFDSalvar: TButton;
    seTimeOut: TSpinEdit;
    seIntervaloAposComando: TSpinEdit;
    IE1: TMenuItem;
    Image1: TImage;
    wbBobina: TWebBrowser;
    N26: TMenuItem;
    DataMovimento1: TMenuItem;
    DadosUltimaReduoZ1: TMenuItem;
    btSerial: TBitBtn;
    chDescricaoGrande: TCheckBox;
    CortaPapel1: TMenuItem;
    N28: TMenuItem;
    estedeVinculado1: TMenuItem;
    Label20: TLabel;
    edOperador: TEdit;
    Sangria1: TMenuItem;
    Suprimento1: TMenuItem;
    N29: TMenuItem;
    edMsgTrabalhando: TEdit;
    Label21: TLabel;
    ProgramaRelatrioGerencial1: TMenuItem;
    RelatriosGerenciais1: TMenuItem;
    CarregaRelatriosGerenciais1: TMenuItem;
    LegendaInmetroproximoItem1: TMenuItem;
    CancelaItemVendidoParcial1: TMenuItem;
    N30: TMenuItem;
    CancelaDescontoAcrescimoItem1: TMenuItem;
    CancelaDescontoAcrescimoSubTotal1: TMenuItem;
    N31: TMenuItem;
    N32: TMenuItem;
    ConsultaRegistradorECF1: TMenuItem;
    N33: TMenuItem;
    EstornaMeiodePagamento1: TMenuItem;
    DeCodificaTexto1: TMenuItem;
    N34: TMenuItem;
    AchaAliquotaporIndice1: TMenuItem;
    AchaAliquotaporValor1: TMenuItem;
    N35: TMenuItem;
    AcharMeioPagamentoporIndice1: TMenuItem;
    AcharMeiodePagametoporDescrio1: TMenuItem;
    N36: TMenuItem;
    AchaCNFporIndice1: TMenuItem;
    AchaCNFporDescrio1: TMenuItem;
    N37: TMenuItem;
    AchaRGporIndice1: TMenuItem;
    AchaRGporDescrio1: TMenuItem;
    N38: TMenuItem;
    PorCOO2: TMenuItem;
    PorDatadeMovimento1: TMenuItem;
    LeituraSerialMFD1: TMenuItem;
    PorCOO3: TMenuItem;
    PorPeriodo2: TMenuItem;
    UsuarioAual1: TMenuItem;
    Modelo1: TMenuItem;
    N39: TMenuItem;
    ArquivoMFDDLL1: TMenuItem;
    PorCOO4: TMenuItem;
    PorPeriodo3: TMenuItem;
    N40: TMenuItem;
    otalSubstituicaoTributariaISSQN1: TMenuItem;
    otalNaoTributadoISSQN1: TMenuItem;
    otalIsencao1: TMenuItem;
    NumGNF1: TMenuItem;
    TabSheet9: TTabSheet;
    Button2: TButton;
    mRZ: TMemo;
    Label37: TLabel;
    Button1: TButton;
    N41: TMenuItem;
    LerTroco1: TMenuItem;
    Label22: TLabel;
    speLinBuf: TSpinEdit;
    NumSerieMFD: TMenuItem;
    ParametroDescontoISSQN1: TMenuItem;
    N42: TMenuItem;
    mModeloStr: TMenuItem;
    tbsMenuFiscal: TTabSheet;
    grpMenuFiscalOpcoes: TGroupBox;
    btnMenuFiscalLX: TButton;
    btnMenuFiscalLMFC: TButton;
    btnMenuFiscalLMFS: TButton;
    btnMenuFiscalMFDEspelho: TButton;
    btnMenuFiscalMFDArq: TButton;
    btnMenuFiscalRelMeiosPagto: TButton;
    btnMenuFiscalRelDAVEmitidos: TButton;
    btnMenuFiscalRelIdentPAFECF: TButton;
    dlgDialogoSalvar: TSaveDialog;
    pgcMenuFiscalTipo: TPageControl;
    tbsMenuFiscalTipoData: TTabSheet;
    Label24: TLabel;
    Label25: TLabel;
    edtDtInicial: TDateTimePicker;
    edtDtFinal: TDateTimePicker;
    tbsMenuFiscalTipoCOO: TTabSheet;
    Label26: TLabel;
    Label30: TLabel;
    edtCOOInicial: TSpinEdit;
    edtCOOFinal: TSpinEdit;
    chkMenuFiscalCotepe1704: TCheckBox;
    chkMenuFiscalGerarArquivo: TCheckBox;
    Label31: TLabel;
    btnIdentificaPafECF: TButton;
    RelatorioGerencialcomformatacao1: TMenuItem;
    btnMenuFiscalConfigPAFECF: TButton;
    SubModelo1: TMenuItem;
    N43: TMenuItem;
    DAV1: TMenuItem;
    DAVOS1: TMenuItem;
    NumerodeReduesZrestantes1: TMenuItem;
    btnMenuFiscalNotaPaulista: TButton;
    chArredondamentoItemMFD: TCheckBox;
    tsTagsImpressao: TTabSheet;
    speBarrasLargura: TSpinEdit;
    BitBtn6: TBitBtn;
    MemoTesteTags: TMemo;
    chBarrasImprimeTexto: TCheckBox;
    speBarrasAltura: TSpinEdit;
    Label28: TLabel;
    Label27: TLabel;
    Label23: TLabel;
    chIgnorarTagsFormatacao: TCheckBox;
    cbxUF: TComboBox;
    Label29: TLabel;
    chbCupomMania: TCheckBox;
    procedure cbxModeloChange(Sender: TObject);
    procedure Sair1Click(Sender: TObject);
    procedure bAtivarClick(Sender: TObject);
    procedure cbxPortaChange(Sender: TObject);
    procedure Ativar1Click(Sender: TObject);
    procedure Desativar1Click(Sender: TObject);
    procedure chTentarClick(Sender: TObject);
    procedure chBloqueiaClick(Sender: TObject);
    procedure chExibeMsgClick(Sender: TObject);
    procedure mMsgChange(Sender: TObject);
    procedure Testar1Click(Sender: TObject);
    procedure ACBrECF1MsgAguarde(Mensagem : String);
    procedure DataHora1Click(Sender: TObject);
    procedure NumECF1Click(Sender: TObject);
    procedure NSrie1Click(Sender: TObject);
    procedure NVerso1Click(Sender: TObject);
    procedure NumUltimoCupom1Click(Sender: TObject);
    procedure PoucoPapel1Click(Sender: TObject);
    procedure LeituraX1Click(Sender: TObject);
    procedure ReduoZ1Click(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure Sobre1Click(Sender: TObject);
    procedure AliquotasICMS1Click(Sender: TObject);
    procedure FormasdePagamento1Click(Sender: TObject);
    procedure AbreGaveta1Click(Sender: TObject);
    procedure GavetaAberta1Click(Sender: TObject);
    procedure ChequePronto1Click(Sender: TObject);
    procedure CancelaImpressoCheque1Click(Sender: TObject);
    procedure HorarioVerao1Click(Sender: TObject);
    procedure ImpactoAgulhas1Click(Sender: TObject);
    procedure TestaPodeAbrirCupom1Click(Sender: TObject);
    procedure NUltimoCupom1Click(Sender: TObject);
    procedure SubTotal1Click(Sender: TObject);
    procedure TotalPago1Click(Sender: TObject);
    procedure AbrirCupom1Click(Sender: TObject);
    procedure CancelaCupom1Click(Sender: TObject);
    procedure VenderItem1Click(Sender: TObject);
    procedure CancelarItemVendido1Click(Sender: TObject);
    procedure Sub1Click(Sender: TObject);
    procedure EfetuarPagamento1Click(Sender: TObject);
    procedure FecharCupom1Click(Sender: TObject);
    procedure EnviaComando1Click(Sender: TObject);
    procedure ACBrECF1AguardandoRespostaChange(Sender: TObject);
    procedure CarregaComprovantesNAOFiscais1Click(Sender: TObject);
    procedure FechaRelatrio1Click(Sender: TObject);
    procedure HorarioVerao2Click(Sender: TObject);
    procedure Arredonda1Click(Sender: TObject);
    procedure MudaArredondamento1Click(Sender: TObject);
    procedure NumLoja1Click(Sender: TObject);
    procedure NumCRO1Click(Sender: TObject);
    procedure TestedeVelocidade1Click(Sender: TObject);
    procedure chArredondaPorQtdClick(Sender: TObject);
    procedure CapturaporNReduaoZ1Click(Sender: TObject);
    procedure ImprimeporNReduaoZ1Click(Sender: TObject);
    procedure CapturaporPeriodo1Click(Sender: TObject);
    procedure ImprimeporPeriodo1Click(Sender: TObject);
    procedure ProgramaAliquota1Click(Sender: TObject);
    procedure ProgramaComprovanteNAOFiscal1Click(Sender: TObject);
    procedure ACBrECF1MsgPoucoPapel(Sender: TObject);
    procedure ProgramaFormadePagamento1Click(Sender: TObject);
    procedure CorrigeEstadodeErro1Click(Sender: TObject);
    procedure ImprimeCheque1Click(Sender: TObject);
    procedure CarregaUnidadesdeMedida1Click(Sender: TObject);
    procedure ProgramaUnidadeMedida1Click(Sender: TObject);
    procedure AbreRelatorioGerencial1Click(Sender: TObject);
    procedure AbreCupomVinculado1Click(Sender: TObject);
    procedure ImprimeLinhaRelatorio1Click(Sender: TObject);
    procedure ImprimeLinhaVinculado1Click(Sender: TObject);
    procedure ListaRelatorioGerencial1Click(Sender: TObject);
    procedure ListaCupomVinculado1Click(Sender: TObject);
    procedure PularLinhas1Click(Sender: TObject);
    procedure chGavetaSinalInvertidoClick(Sender: TObject);
    procedure LerTodasasVariveis1Click(Sender: TObject);
    procedure MFD1Click(Sender: TObject);
    procedure Termica1Click(Sender: TObject);
    procedure edLogChange(Sender: TObject);
    procedure SbArqLogClick(Sender: TObject);
    procedure cbMemoHTMLClick(Sender: TObject);
    procedure bBobinaLimparClick(Sender: TObject);
    procedure bBobinaParamsClick(Sender: TObject);
    procedure ACBrECF1BobinaAdicionaLinhas(const Linhas, Operacao: String);
    procedure DadosReducaoZ1Click(Sender: TObject);
    procedure CNPJIE1Click(Sender: TObject);
    procedure NumCRZ1Click(Sender: TObject);
    procedure NumCOOInicial1Click(Sender: TObject);
    procedure VendaBruta1Click(Sender: TObject);
    procedure GrandeTotal1Click(Sender: TObject);
    procedure TotalCancelamentos1Click(Sender: TObject);
    procedure TotalDescontos1Click(Sender: TObject);
    procedure TotalAcrescimos1Click(Sender: TObject);
    procedure TotalSubstituicaoTributaria1Click(Sender: TObject);
    procedure TotalNaoTributado1Click(Sender: TObject);
    procedure TotalIsencao1Click(Sender: TObject);
    procedure LerTotaisAliquotas1Click(Sender: TObject);
    procedure LerTotaisFormadePagamento1Click(Sender: TObject);
    procedure LerTotaisComprovanetNaoFiscal1Click(Sender: TObject);
    procedure UltimoItemVendido1Click(Sender: TObject);
    procedure PorCOO1Click(Sender: TObject);
    procedure PorPeriodo1Click(Sender: TObject);
    procedure Estado1Click(Sender: TObject);
    procedure NoFiscalCompleto1Click(Sender: TObject);
    procedure AbreNoFiscal1Click(Sender: TObject);
    procedure RegistraItemNaoFiscal1Click(Sender: TObject);
    procedure SubTotalizaNaoFiscal1Click(Sender: TObject);
    procedure EfetuaPagamentoNaoFiscal1Click(Sender: TObject);
    procedure FechaNoFiscal1Click(Sender: TObject);
    procedure CancelaNoFiscal1Click(Sender: TObject);
    procedure NumCCF1Click(Sender: TObject);
    procedure NumCOO1Click(Sender: TObject);
    procedure IdentificaConsumidor1Click(Sender: TObject);
    procedure edDirRFDChange(Sender: TObject);
    procedure sbDirRFDClick(Sender: TObject);
    procedure bRFDLerClick(Sender: TObject);
    procedure bRFDSalvarClick(Sender: TObject);
    procedure chRFDClick(Sender: TObject);
    procedure seTimeOutChange(Sender: TObject);
    procedure seIntervaloAposComandoChange(Sender: TObject);
    procedure edSH_RazaoSocialChange(Sender: TObject);
    procedure edSH_COOChange(Sender: TObject);
    procedure edSH_CNPJChange(Sender: TObject);
    procedure edSH_IEChange(Sender: TObject);
    procedure edSH_IMChange(Sender: TObject);
    procedure edSH_AplicativoChange(Sender: TObject);
    procedure edSH_NumeroAPChange(Sender: TObject);
    procedure edSH_VersaoAPChange(Sender: TObject);
    procedure edSH_Linha1Change(Sender: TObject);
    procedure edSH_Linha2Change(Sender: TObject);
    procedure IE1Click(Sender: TObject);
    procedure Image1Click(Sender: TObject);
    procedure otalNoFiscal1Click(Sender: TObject);
    procedure DataMovimento1Click(Sender: TObject);
    procedure DadosUltimaReduoZ1Click(Sender: TObject);
    procedure TotalNoFiscal1Click(Sender: TObject);
    procedure btSerialClick(Sender: TObject);
    
    procedure chDescricaoGrandeClick(Sender: TObject);
    procedure CortaPapel1Click(Sender: TObject);
    procedure edMsgTrabalhandoChange(Sender: TObject);
    procedure edOperadorChange(Sender: TObject);
    procedure Sangria1Click(Sender: TObject);
    procedure Suprimento1Click(Sender: TObject);
    procedure TestedeVinculado1Click(Sender: TObject);
    procedure ProgramaRelatrioGerencial1Click(Sender: TObject);
    procedure CarregaRelatriosGerenciais1Click(Sender: TObject);
    procedure LegendaInmetroproximoItem1Click(Sender: TObject);
    procedure CancelaItemVendidoParcial1Click(Sender: TObject);
    procedure CancelaDescontoAcrescimoItem1Click(Sender: TObject);
    procedure CancelaDescontoAcrescimoSubTotal1Click(Sender: TObject);
    procedure ConsultaRegistradorECF1Click(Sender: TObject);
    procedure EstornaMeiodePagamento1Click(Sender: TObject);
    procedure DeCodificaTexto1Click(Sender: TObject);
    procedure AchaAliquotaporIndice1Click(Sender: TObject);
    procedure AchaAliquotaporValor1Click(Sender: TObject);
    procedure AcharMeioPagamentoporIndice1Click(Sender: TObject);
    procedure AcharMeiodePagametoporDescrio1Click(Sender: TObject);
    procedure AchaCNFporIndice1Click(Sender: TObject);
    procedure AchaCNFporDescrio1Click(Sender: TObject);
    procedure AchaRGporIndice1Click(Sender: TObject);
    procedure AchaRGporDescrio1Click(Sender: TObject);
    procedure PorCOO2Click(Sender: TObject);
    procedure PorDatadeMovimento1Click(Sender: TObject);
    procedure PorCOO3Click(Sender: TObject);
    procedure PorPeriodo2Click(Sender: TObject);
    procedure UsuarioAual1Click(Sender: TObject);
    procedure Modelo1Click(Sender: TObject);
    procedure PorCOO4Click(Sender: TObject);
    procedure PorPeriodo3Click(Sender: TObject);
    procedure otalSubstituicaoTributariaISSQN1Click(Sender: TObject);
    procedure otalNaoTributadoISSQN1Click(Sender: TObject);
    procedure otalIsencao1Click(Sender: TObject);
    procedure NumGNF1Click(Sender: TObject);
    procedure Button1Click(Sender: TObject);
    procedure Button2Click(Sender: TObject);
    procedure LerTroco1Click(Sender: TObject);
    procedure speLinBufChange(Sender: TObject);
    procedure NumSerieMFDClick(Sender: TObject);
    procedure ParametroDescontoISSQN1Click(Sender: TObject);
    procedure mModeloStrClick(Sender: TObject);
    procedure btnMenuFiscalLXClick(Sender: TObject);
    procedure btnMenuFiscalLMFCClick(Sender: TObject);
    procedure btnMenuFiscalLMFSClick(Sender: TObject);
    procedure btnMenuFiscalMFDEspelhoClick(Sender: TObject);
    procedure btnMenuFiscalMFDArqClick(Sender: TObject);
    procedure btnMenuFiscalRelMeiosPagtoClick(Sender: TObject);
    procedure btnMenuFiscalRelDAVEmitidosClick(Sender: TObject);
    procedure btnMenuFiscalRelIdentPAFECFClick(Sender: TObject);
    procedure btnIdentificaPafECFClick(Sender: TObject);
    procedure RelatorioGerencialcomformatacao1Click(Sender: TObject);
    procedure btnMenuFiscalConfigPAFECFClick(Sender: TObject);
    procedure ACBrECF1ChangeEstado(const EstadoAnterior,
      EstadoAtual: TACBrECFEstado);
    procedure SubModelo1Click(Sender: TObject);
    procedure DAV1Click(Sender: TObject);
    procedure DAVOS1Click(Sender: TObject);
    procedure NumerodeReduesZrestantes1Click(Sender: TObject);
    procedure btnMenuFiscalNotaPaulistaClick(Sender: TObject);
    procedure chArredondamentoItemMFDClick(Sender: TObject);
    procedure chBarrasImprimeTextoClick(Sender: TObject);
    procedure chIgnorarTagsFormatacaoClick(Sender: TObject);
    procedure BitBtn6Click(Sender: TObject);
    procedure speBarrasLarguraChange(Sender: TObject);
    procedure speBarrasAlturaChange(Sender: TObject);
    procedure chbCupomManiaClick(Sender: TObject);
  private
    { Private declarations }
    Function Converte( cmd : String) : String;
    procedure TrataErros(Sender: TObject; E: Exception);
    function EstadoECF: String;
    Procedure GravarINI ;
    Procedure LerINI ;

    procedure WB_LoadHTML(WebBrowser: TWebBrowser; HTMLCode: string);
    procedure WB_ScrollToBottom(WebBrowser1: TWebBrowser);
    procedure WB_ScrollToTop(WebBrowser1: TWebBrowser);

  public
    { Public declarations }
    Procedure AtualizaMemos(VerificaEstado : Boolean = true) ;
  end;

const
  ECFTeste_VERSAO = '2.01' ;
  Estados : array[TACBrECFEstado] of string =
    ('N�o Inicializada', 'Desconhecido', 'Livre', 'Venda',
    'Pagamento', 'Relat�rio', 'Bloqueada', 'Requer Z', 'Requer X', 'Nao Fiscal' );
   _C = 'tYk*5W@' ;

var
  Form1: TForm1;

implementation

uses ACBrUtil, ACBrECFBematech, VendeItem, EfetuaPagamento,
     Relatorio, Sobre, TypInfo, Math, ActiveX, MSHTML, IniFiles,
  ConfiguraSerial, ACBrPAFClass, RelatorioGerencialFormatado, uDAV, uDAVOS;

{$R *.dfm}

procedure TForm1.FormCreate(Sender: TObject);
Var I : TACBrECFModelo ;
begin
  cbxModelo.Items.Clear ;
  For I := Low(TACBrECFModelo) to High(TACBrECFModelo) do
     cbxModelo.Items.Add( GetEnumName(TypeInfo(TACBrECFModelo), integer(I) ) ) ;
  cbxModelo.Items[0] := 'Procurar' ;
  cbxModelo.ItemIndex := 0 ;

  cbxPorta.Items.Clear;
  ACBrECF1.Device.AcharPortasSeriais( cbxPorta.Items );
  cbxPorta.Items.Insert(0,'Procurar') ;
  cbxPorta.Items.Add('LPT1') ;
  cbxPorta.Items.Add('LPT2') ;
  cbxPorta.Items.Add('LPT3') ;
  cbxPorta.Items.Add('/dev/ttyS0') ;
  cbxPorta.Items.Add('/dev/ttyS1') ;
  cbxPorta.Items.Add('/dev/ttyUSB0') ;
  cbxPorta.Items.Add('/dev/ttyUSB1') ;
  cbxPorta.Items.Add('c:\temp\ecf.txt') ;
  cbxPorta.Items.Add('/tmp/ecf.txt') ;

  mMsgChange( Sender );
  Application.OnException := TrataErros ;
  PageControl1.ActivePageIndex := 0 ;

  LerINI ;
  
  if FileExists('ACBrECFMemoParams.ini') then
     ACBrECF1.MemoParams.LoadFromFile('ACBrECFMemoParams.ini');

  cbMemoHTML.Checked := ( ACBrECF1.MemoParams.Values['HTML'] = '1' ) ;

  if (not chRFD.Checked) and DirectoryExists( ACBrRFD1.DirRFD ) then
     chRFD.Checked := true ;
end;

{-----------------------------------------------------------------------------}
Procedure TForm1.TrataErros(Sender: TObject; E: Exception);
begin
  mResp.Lines.Add( E.Message );
  StatusBar1.Panels[0].Text := 'Exception' ;
  AtualizaMemos( False ) ;
  StatusBar1.Panels[2].Text := E.Message ;
//  PageControl1.ActivePageIndex := 1 ;
//  MessageDlg( E.Message,mtError,[mbOk],0) ;
end ;

procedure TForm1.AtualizaMemos(VerificaEstado : Boolean = true) ;
begin
  mEnviado.Text := Converte( ACBrECF1.ComandoEnviado );
  mResp.Lines.Add( Converte( ACBrECF1.RespostaComando) );
  mResp.Lines.Add('- + - + - + - + - + - + - + - + - + - + - + -') ;
  if VerificaEstado then
     StatusBar1.Panels[0].Text :=  EstadoECF ;
end;

Function TForm1.EstadoECF : String ;
begin
  try
     Result :=  Estados[ ACBrECF1.Estado ] ;
     { GetEnumName(TypeInfo(TACBrECFEstado), integer( ACBrECF1.Estado ) ) ;}
  except
     Result := 'Falha ao ler' ;
     mResp.Lines.Add( '**** Falha ao ler ESTADO do ECF ****' );
  end ;
end ;

function TForm1.Converte(cmd: String): String;
var A : Integer ;
begin
  Result := '' ;                                    
  For A := 1 to length( cmd ) do
  begin
     if (Ord(cmd[A]) < 32) or (Ord(cmd[A]) > 127) then
        Result := Result + '#' + IntToStr(ord( cmd[A] ))
     else
        Result := Result + cmd[A] ;
  end ;
end;

procedure TForm1.cbxModeloChange(Sender: TObject);
begin
  try
     ACBrECF1.Modelo := TACBrECFModelo( cbxModelo.ItemIndex ) ;
  except
     cbxModelo.ItemIndex := Integer( ACBrECF1.Modelo ) ;
     raise ;
  end ;
end;

procedure TForm1.Sair1Click(Sender: TObject);
begin
  close ;
end;

procedure TForm1.bAtivarClick(Sender: TObject);
begin
  if bAtivar.Caption = 'Ativar' then
     Ativar1Click( Sender )
  else
     Desativar1Click( Sender );
end;

procedure TForm1.cbxPortaChange(Sender: TObject);
begin
  try
    ACBrECF1.Porta := cbxPorta.Text ;
  finally
     cbxPorta.Text := ACBrECF1.Porta ;
  end ;
end;

procedure TForm1.Ativar1Click(Sender: TObject);
begin
  try
     Self.Enabled := False ;
     ACBrECF1.Porta := cbxPorta.Text ;
     
     if cbxModelo.ItemIndex = 0 then
        if not ACBrECF1.AcharECF(true,False) then
        begin
           MessageDlg('Nenhum ECF encontrado.',mtInformation,[mbOk],0) ;
           exit ;
        end ;

     ACBrECF1.Ativar ;

     btSerial.Enabled := False ;
     bAtivar.Caption := 'Desativar' ;
     mResp.Lines.Add( 'Ativar' );
     AtualizaMemos ;

     GravarINI ;
     
     if PageControl1.ActivePageIndex = 0 then
        PageControl1.ActivePageIndex := 1 ;
  finally
     Self.Enabled := True ;
     cbxModelo.ItemIndex := Integer(ACBrECF1.Modelo) ;
     cbxPorta.Text       := ACBrECF1.Porta ;
  end ;
end;

procedure TForm1.Desativar1Click(Sender: TObject);
begin
  ACBrECF1.Desativar ;
  bAtivar.Caption := 'Ativar' ;
  mResp.Lines.Add( 'Desativar' );
  AtualizaMemos ;
  btSerial.Enabled := True ;
end;

procedure TForm1.chTentarClick(Sender: TObject);
begin
  ACBrECF1.ReTentar := chTentar.Checked ;
end;

procedure TForm1.chBloqueiaClick(Sender: TObject);
begin
  ACBrECF1.BloqueiaMouseTeclado := chBloqueia.Checked ;
end;

procedure TForm1.chExibeMsgClick(Sender: TObject);
begin
  ACBrECF1.ExibeMensagem := chExibeMsg.Checked ;
end;

procedure TForm1.chArredondaPorQtdClick(Sender: TObject);
begin
  ACBrECF1.ArredondaPorQtd := chArredondaPorQtd.Checked ;
end;

procedure TForm1.chDescricaoGrandeClick(Sender: TObject);
begin
  ACBrECF1.DescricaoGrande := chDescricaoGrande.Checked ;
end;

procedure TForm1.mMsgChange(Sender: TObject);
Var Msg : String ;
    L : Integer ;
begin
  Msg := '' ;
  For L := 0 to mMsg.Lines.Count - 1 do
  begin
     Msg := Msg + mMsg.Lines[L]+#10
  end ;

  ACBrECF1.MsgAguarde := copy(Msg, 1, Length(Msg)-1 ) ;
end;

procedure TForm1.Testar1Click(Sender: TObject);
begin
  ACBrECF1.TestarDialog ;
  AtualizaMemos ;
end;

procedure TForm1.ACBrECF1MsgAguarde(Mensagem : String);
begin
  StatusBar1.Panels[2].Text := StringReplace(Mensagem,#10,' ',[rfReplaceAll]) ;
end;

procedure TForm1.DataHora1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'Data/Hora: '+ DateTimeToStr( ACBrECF1.DataHora ) ) ;
  AtualizaMemos ;
end;

procedure TForm1.NumECF1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'N.ECF: ('+ ACBrECF1.NumECF+')' );
  AtualizaMemos ;
end;

procedure TForm1.NSrie1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'N.S�rie: ('+ ACBrECF1.NumSerie+')' );
  AtualizaMemos ;
end;

procedure TForm1.NVerso1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'N.Vers�o: '+ ACBrECF1.NumVersao );
  AtualizaMemos ;
end;

procedure TForm1.NumUltimoCupom1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'N.�ltimo Cupom: ('+ ACBrECF1.NumCupom+')' );
  AtualizaMemos ;
end;

procedure TForm1.PoucoPapel1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'Pouco Papel: '+
     IfThen( ACBrECF1.PoucoPapel , 'SIM', 'NAO') );
  AtualizaMemos ;
end;

procedure TForm1.LeituraX1Click(Sender: TObject);
begin
  ACBrECF1.LeituraX ;
  mResp.Lines.Add( 'Leitura X');
  AtualizaMemos ;
end;

procedure TForm1.ReduoZ1Click(Sender: TObject);
Var Resp : TModalResult ;
begin
  if ACBrECF1.Estado <> estRequerZ then
  begin
     if MessageDlg('A Redu��o Z pode Bloquear o seu ECF at� a 12:00pm'+#10+#10+
                  'Continua assim mesmo ?',mtWarning,mbYesNoCancel,0) <> mrYes then
        exit ;

     if MessageDlg('Voc� tem certeza ?',mtWarning,mbYesNoCancel,0) <> mrYes then
        exit ;
  end ;

  Resp := MessageDlg('Envia hora atual ?',mtConfirmation,mbYesNoCancel,0) ;

  if Resp = mrYes then
     ACBrECF1.ReducaoZ( now )
  else if Resp = mrNo then
     ACBrECF1.ReducaoZ( ) 
  else
     exit ;

  mResp.Lines.Add( 'Redu�ao Z');
  AtualizaMemos ;
end;

procedure TForm1.Sobre1Click(Sender: TObject);
begin
  ACBrAboutDialog ;
end;

procedure TForm1.AliquotasICMS1Click(Sender: TObject);
var A : Integer ;
begin
  ACBrECF1.CarregaAliquotas ;

  for A := 0 to ACBrECF1.Aliquotas.Count -1 do
  begin
     mResp.Lines.Add( 'Aliquota: '+IntToStrZero( ACBrECF1.Aliquotas[A].Sequencia,2)+
                      ' Indice: '+ACBrECF1.Aliquotas[A].Indice +' -> '+
                      FloatToStr( ACBrECF1.Aliquotas[A].Aliquota ) + ' Tipo: '+
                      ACBrECF1.Aliquotas[A].Tipo );
  end ;
  mResp.Lines.Add('---------------------------------');
end;

procedure TForm1.LerTotaisAliquotas1Click(Sender: TObject);
Var A : Integer ;
begin
  ACBrECF1.LerTotaisAliquota ;

  for A := 0 to ACBrECF1.Aliquotas.Count -1 do
  begin
     mResp.Lines.Add( 'Aliquota: '+ACBrECF1.Aliquotas[A].Indice +' - '+
                      FloatToStr( ACBrECF1.Aliquotas[A].Aliquota ) + ' Tipo: '+
                      ACBrECF1.Aliquotas[A].Tipo+ ' -> '+
                      FormatFloat('###,##0.00', ACBrECF1.Aliquotas[A].Total ) );
  end ;
  mResp.Lines.Add('---------------------------------');
end;


procedure TForm1.FormasdePagamento1Click(Sender: TObject);
var A : Integer ;
begin
  ACBrECF1.CarregaFormasPagamento ;

  for A := 0 to ACBrECF1.FormasPagamento.Count -1 do
  begin
     if ACBrECF1.FormasPagamento[A].Descricao <> '' then
        mResp.Lines.Add( 'Forma Pagto: '+ACBrECF1.FormasPagamento[A].Indice+' -> '+
           ACBrECF1.FormasPagamento[A].Descricao+'  Permite Vinculado: '+
           IfThen( ACBrECF1.FormasPagamento[A].PermiteVinculado,'S','N'));
  end ;
  mResp.Lines.Add('---------------------------------');
end;

procedure TForm1.LerTotaisFormadePagamento1Click(Sender: TObject);
var A : Integer ;
begin
  ACBrECF1.LerTotaisFormaPagamento ;

  for A := 0 to ACBrECF1.FormasPagamento.Count -1 do
  begin
     if ACBrECF1.FormasPagamento[A].Descricao <> '' then
        mResp.Lines.Add( 'Forma Pagto: '+ACBrECF1.FormasPagamento[A].Indice+' - '+
           ACBrECF1.FormasPagamento[A].Descricao+'  -> '+
           FormatFloat('###,##0.00',ACBrECF1.FormasPagamento[A].Total)) ;
  end ;
  mResp.Lines.Add('---------------------------------');
end;

procedure TForm1.LerTroco1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'Total do Troco: '+ FormatFloat('#,###,##0.00', ACBrECF1.TotalTroco) ) ;
  AtualizaMemos ;
end;

procedure TForm1.CarregaComprovantesNAOFiscais1Click(Sender: TObject);
var A : Integer ;
begin
  ACBrECF1.CarregaComprovantesNaoFiscais ;

  for A := 0 to ACBrECF1.ComprovantesNaoFiscais.Count -1 do
  begin
     if ACBrECF1.ComprovantesNaoFiscais[A].Descricao <> '' then
        mResp.Lines.Add( 'CNF: '+ACBrECF1.ComprovantesNaoFiscais[A].Indice+' -> '+
           ACBrECF1.ComprovantesNaoFiscais[A].Descricao+'  Permite Vinculado: '+
           IfThen( ACBrECF1.ComprovantesNaoFiscais[A].PermiteVinculado,
                            'S','N')+
           ' - FPG associada: '+ACBrECF1.ComprovantesNaoFiscais[A].FormaPagamento);
  end ;
  mResp.Lines.Add('---------------------------------');
end;

procedure TForm1.CarregaRelatriosGerenciais1Click(Sender: TObject);
var
  A : Integer ;
begin
  ACBrECF1.CarregaRelatoriosGerenciais ;

  for A := 0 to ACBrECF1.RelatoriosGerenciais.Count -1 do
  begin
     if ACBrECF1.RelatoriosGerenciais[A].Descricao <> '' then
        mResp.Lines.Add( 'RG: '+ACBrECF1.RelatoriosGerenciais[A].Indice+' -> '
        + PadL(ACBrECF1.RelatoriosGerenciais[A].Descricao, 15) + ' CER:'
        + IntToStr(ACBrECF1.RelatoriosGerenciais[A].Contador)) ; 
  end;
  mResp.Lines.Add('---------------------------------');

end;

procedure TForm1.LerTotaisComprovanetNaoFiscal1Click(Sender: TObject);
var A : Integer ;
begin
  ACBrECF1.LerTotaisComprovanteNaoFiscal ;

  for A := 0 to ACBrECF1.ComprovantesNaoFiscais.Count -1 do
  begin
     if ACBrECF1.ComprovantesNaoFiscais[A].Descricao <> '' then
        mResp.Lines.Add( 'CNF: '+ACBrECF1.ComprovantesNaoFiscais[A].Indice+' - '+
           ACBrECF1.ComprovantesNaoFiscais[A].Descricao+' CON ('+
           IntToStrZero(ACBrECF1.ComprovantesNaoFiscais[A].Contador, 4)+') -> '+
           FormatFloat('###,##0.00', ACBrECF1.ComprovantesNaoFiscais[A].Total)) ;
  end ;
  mResp.Lines.Add('---------------------------------');
end;

procedure TForm1.AbreGaveta1Click(Sender: TObject);
begin
  ACBrECF1.AbreGaveta ;
  mResp.Lines.Add( 'AbreGaveta');
  AtualizaMemos ;
end;

procedure TForm1.GavetaAberta1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'Gaveta Aberta: '+
     IfThen( ACBrECF1.GavetaAberta,'SIM','NAO'));
  AtualizaMemos ;
end;

procedure TForm1.HorarioVerao1Click(Sender: TObject);
begin
  ACBrECF1.MudaHorarioVerao ;
  mResp.Lines.Add( 'MudaHorarioVerao');
  AtualizaMemos ;
end;

procedure TForm1.ImpactoAgulhas1Click(Sender: TObject);
begin
  ACBrECF1.ImpactoAgulhas ;
  mResp.Lines.Add( 'ImpactoAgulhas');
  AtualizaMemos ;
end;

procedure TForm1.TestaPodeAbrirCupom1Click(Sender: TObject);
var Est : String ;
begin

  Est := Estados[ ACBrECF1.Estado ] ;

  try
     ACBrECF1.TestaPodeAbrirCupom ;

     mResp.Lines.Add( 'Pode Abrir Cupom.. OK') ;
  except
     mResp.Lines.Add( 'NAO Pode Abrir Cupom..') ;
     mResp.Lines.Add( 'pois o estado Atual � '+Est) ;
     AtualizaMemos ;

     raise ;
  end ;
  
  AtualizaMemos ;

end;

procedure TForm1.NUltimoCupom1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'Num Ultimo Cupom: '+ ACBrECF1.NumCupom );
  AtualizaMemos ;
end;

procedure TForm1.SubTotal1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'SubTotal: '+  FloatToStr( ACBrECF1.Subtotal ) );
  AtualizaMemos ;
end;

procedure TForm1.TotalPago1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'Total Pago: '+  FloatToStr( ACBrECF1.TotalPago ) );
  AtualizaMemos ;
end;

procedure TForm1.AbrirCupom1Click(Sender: TObject);
Var Consumidor : String ;
    CPF, NOME, ENDERECO : String ;
    P : Integer ;
begin
  Consumidor := ' ' ;
  if ACBrECF1.Consumidor.Documento <> '' then
     Consumidor := ACBrECF1.Consumidor.Documento ;
  if ACBrECF1.Consumidor.Nome <> '' then
     Consumidor := Consumidor+'|'+ACBrECF1.Consumidor.Nome ;
  if ACBrECF1.Consumidor.Endereco <> '' then
     Consumidor := Consumidor+'|'+ACBrECF1.Consumidor.Endereco ;

  if InputQuery('Abre Cupom',
                'Se necess�rio, Informe o Documento | Nome | Endereco'+sLineBreak +
                'Nota: Use o sinal pipe (|) para separar os campos' ,Consumidor) then
  begin
     wbBobina.Navigate('about:blank');
     mBobina.Clear ;

     Consumidor := Trim(Consumidor) ;
     P := pos('|',Consumidor+'|') ;
     CPF        := copy(Consumidor,1,P-1) ;
     Consumidor := copy(Consumidor,P+1,Length(Consumidor) ) ;
     P := pos('|',Consumidor+'|') ;
     NOME       := copy(Consumidor,1,P-1) ;
     Consumidor := copy(Consumidor,P+1,Length(Consumidor) ) ;
     P := pos('|',Consumidor+'|') ;
     ENDERECO   := copy(Consumidor,1,P-1) ;

     ACBrECF1.AbreCupom( CPF, NOME, ENDERECO );
     mResp.Lines.Add( 'AbreCupom' );
     AtualizaMemos ;
  end ;
end;

procedure TForm1.CancelaCupom1Click(Sender: TObject);
begin
  ACBrECF1.CancelaCupom ;
  mResp.Lines.Add( 'CancelaCupom' );
  AtualizaMemos ;
end;

procedure TForm1.VenderItem1Click(Sender: TObject);
begin
  frVendeItem.Show ;
end;

procedure TForm1.LegendaInmetroproximoItem1Click(Sender: TObject);
begin
  ACBrECF1.LegendaInmetroProximoItem;
end;

procedure TForm1.CancelarItemVendido1Click(Sender: TObject);
Var
  Item : String ;
begin
  Item := '1' ;
  if InputQuery('Cancelar Item Vendido',
                'Informe o n�mero da Sequencia de Venda', Item ) then
  begin
     ACBrECF1.CancelaItemVendido( StrToIntDef(Item,0) );
     mResp.Lines.Add( 'Cancela Item Vendido: '+Item );
     AtualizaMemos ;
  end ;
end;

procedure TForm1.CancelaItemVendidoParcial1Click(Sender: TObject);
Var
  Item : String ;
  Qdte : String;
begin
  Item := '1' ;
  if InputQuery('Cancelar Item Vendido',
                'Informe o n�mero da Sequencia de Venda', Item ) then

  if InputQuery('Quantidade do Item a cancelar',
                'Informe quantidade do Item a cancelar', Qdte ) then
  begin
     ACBrECF1.CancelaItemVendidoParcial( StrToIntDef(Item,0), StrToFloatDef(Qdte, 0) );
     mResp.Lines.Add( 'Cancela Item Vendido Parcial: '+Item + ', Qdte: ' + Qdte);
     AtualizaMemos ;
  end ;
end;

procedure TForm1.CancelaDescontoAcrescimoItem1Click(Sender: TObject);
Var
  Item : String ;
begin
  Item := '1' ;
  if InputQuery('Cancelar DescontoAcrescimo do Item Vendido',
                'Informe o n�mero da Sequencia de Venda', Item ) then
  begin
     ACBrECF1.CancelaDescontoAcrescimoItem( StrToIntDef(Item,0) );
     mResp.Lines.Add( 'Cancelar AcrescimoDesconto: '+Item );
     AtualizaMemos ;
  end ;

end;

procedure TForm1.Sub1Click(Sender: TObject);
Var Desc, Obs : String ;
begin
  Desc := '0' ;
  Obs := '';

  if ACBrECF1.ModeloStr = 'DataRegis' then
     InputQuery('Subtotaliza Cupom',
                'Se Necess�rio digite alguma Observa�ao (at� 8 linhas)'+#10+
                'O sinal | (pipe) ser� convertido para #10 (quebra de linha)'+#10+
                'A Observa��o tamb�m pode ser enviada no metodo FechaCupom' ,
                Obs );

  if InputQuery('Subtotaliza Cupom',
                'Digite Valor negativo para Desconto'+#10+
                'ou Valor Positivo para Acrescimo' , Desc ) then
  begin
     ACBrECF1.SubtotalizaCupom( StrToFloat(Desc), Obs );
     mResp.Lines.Add( 'Subtotaliza Cupom: '+ Desc );
     AtualizaMemos ;
  end ;
end;

procedure TForm1.SubModelo1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'SubModelo: ('+ ACBrECF1.SubModeloECF+')' );
  AtualizaMemos ;
end;

procedure TForm1.CancelaDescontoAcrescimoSubTotal1Click(Sender: TObject);
Var
  Info : String ;
  Tipo : Char ;
begin
  Info := 'D' ;

  if  InputQuery('Cancela DescontoAcrescimo SubTotal do Cupom',
              'Digite "A" para cancelar Acrescimo ou "D" para Desconto' , Info) then
  begin
     Tipo :=  Info[1];
     ACBrECF1.CancelaDescontoAcrescimoSubTotal(Tipo);
     mResp.Lines.Add( 'Cancela DescontoAcrescimo SubTotal do Cupom: '+ Tipo );
     AtualizaMemos ;
  end ;

end;

procedure TForm1.EfetuarPagamento1Click(Sender: TObject);
begin
  if not (ACBrECF1.Modelo in [ecfDataRegis, ecfFiscNET]) then
     if ACBrECF1.Estado <> estPagamento then
        MessageDlg('Impressora nao est� em Estado de Pagamento'+#10+
                   'Primeiro use SubTotaliza Cupom' ,mtWarning,[mbOk],0) ;

  frPagamento.Show ;
  frPagamento.TipoCupom := 'F' ;
end;

procedure TForm1.FecharCupom1Click(Sender: TObject);
Var
  Obs : String ;
  IndiceBMP : String;
begin
  Obs := 'Componentes ACBr|http://acbr.sourceforge.net' ;
  IndiceBMP :=  '0';
  if InputQuery('Fechar Cupom',
                'Se Necess�rio digite alguma Observa�ao (at� 8 linhas)'+#10+
                'O sinal | (pipe) ser� convertido para #10 (quebra de linha)' ,
                Obs ) then
  begin
     if (ACBrECF1.Modelo = ecfDaruma) and (ACBrECF1.MFD) then
       if Not InputQuery('Impressao de imagem BMP ',
                  'Digite o Indice do BMP que deseja utilizar' ,
                   IndiceBMP ) then
        Exit;

     // informa��es que devem ir no rodap� do cupom obrigatoriamente
     // conforme a legisla��o do paf-ecf
     // preencha somente as informa��es que for utilizar, o que n�o foi informado
     // n�o ser� impresso
     ACBrECF1.InfoRodapeCupom.MD5 := '12345678901234567890123456789012';
     ACBrECF1.InfoRodapeCupom.Dav := '0000000001';
     ACBrECF1.InfoRodapeCupom.DavOs := '0000000002';
     ACBrECF1.InfoRodapeCupom.PreVenda := '0000000003';
     ACBrECF1.InfoRodapeCupom.CupomMania := cbxUF.Text = 'RJ';
     ACBrECF1.InfoRodapeCupom.MinasLegal := cbxUF.Text = 'MG';
     ACBrECF1.InfoRodapeCupom.ParaibaLegal := cbxUF.Text = 'PB';
     ACBrECF1.InfoRodapeCupom.NotaLegalDF.Imprimir := cbxUF.Text = 'DF';

     if ACBrECF1.InfoRodapeCupom.NotaLegalDF.Imprimir then
     begin
       ACBrECF1.InfoRodapeCupom.NotaLegalDF.ProgramaDeCredito := True;
       ACBrECF1.InfoRodapeCupom.NotaLegalDF.ValorICMS := 123456.99;
       ACBrECF1.InfoRodapeCupom.NotaLegalDF.ValorISS  := 123456.88;
     end;

     Obs := StringReplace(Obs,'|',#10,[rfReplaceAll,rfIgnoreCase]) ;
     ACBrECF1.FechaCupom( Obs, StrToIntDef(IndiceBMP, 0) );
     mResp.Lines.Add( 'Fecha Cupom: '+#10+Obs );
     AtualizaMemos ;
  end ;
end;

procedure TForm1.EnviaComando1Click(Sender: TObject);
Var CMD1,CMD2, C : String ;
    A : Integer ;
    T : String ;
begin
  CMD1 := '' ;
  T    := '0'  ;
  if InputQuery('Enviar Comando',
                'Digite o comando de acordo com a Sintaxe da Impressora'+#10+#10+
                'Para Caracteres ASC use #nnn  Ex: #006 = chr(006)'+#10+#10+
                'Exemplo: #006 ir� imprimir uma Leitura X na Bematech' ,
                CMD1 ) then
    if (not (ACBrECF1.Modelo = ecfBematech)) or
       InputQuery('Enviar Comando BEMATECH',
                  'Digite o tamanho em Bytes do Retorno esperado'+#10+#10+
                  'NAO adcione os 3 bytes de ACK+ST1+ST2',
                  T ) then
     begin
        CMD2   := '' ;
        A      := 1 ;

        if ACBrECF1.ECF is TACBrECFBematech then
           (ACBrECF1.ECF as TACBrECFBematech).BytesResp := StrToIntDef(T,0) ;


        while A <= length( CMD1 ) do
        begin
           C := copy( CMD1, A, 1) ;

           if C = '#' then
            begin
              CMD2 := CMD2 + chr( StrToIntDef(copy(CMD1,A+1,3),0) ) ;
              A := A + 3 ;
            end
           else
              CMD2 := CMD2 + C ;

           A := A + 1 ;
        end ;

        ACBrECF1.EnviaComando( CMD2 );
        mResp.Lines.Add( 'Envia Comando: '+CMD1 );
        AtualizaMemos ;
     end ;
end;

procedure TForm1.FechaRelatrio1Click(Sender: TObject);
begin
  ACBrECF1.FechaRelatorio ;
end;

procedure TForm1.ACBrECF1AguardandoRespostaChange(Sender: TObject);
begin
  if ACBrECF1.AguardandoResposta then
     StatusBar1.Panels[0].Text := 'Processando...'
  else
     StatusBar1.Panels[0].Text := '' ;
end;

procedure TForm1.HorarioVerao2Click(Sender: TObject);
begin
  mResp.Lines.Add( 'Hor�rio de Ver�o: '+
     IfThen( ACBrECF1.HorarioVerao , 'SIM', 'NAO') );
  AtualizaMemos ;
end;

procedure TForm1.Arredonda1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'Arredondamento: '+
     IfThen( ACBrECF1.Arredonda , 'SIM', 'NAO') );
  AtualizaMemos ;
end;

procedure TForm1.MudaArredondamento1Click(Sender: TObject);
Var Resp : TModalResult ;
begin
  Resp := MessageDlg('Arredondar ?',mtConfirmation,mbYesNoCancel,0) ;
  if Resp <> mrCancel then
  begin
    ACBrECF1.MudaArredondamento( (Resp = mrYes) ) ;
    mResp.Lines.Add( 'MudaArredondamento');
    AtualizaMemos ;
  end ;
end;

procedure TForm1.NumLoja1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'NUM Loja: ('+ ACBrECF1.NumLoja+')' );
  AtualizaMemos ;
end;

procedure TForm1.NumCRO1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'Num.CRO: ('+ ACBrECF1.NumCRO+')' );
  AtualizaMemos ;
end;

procedure TForm1.TestedeVelocidade1Click(Sender: TObject);
Var cItens, cCupons : String ;
    nItens, nCupons, I, J : Integer ;
    tIni,tFim : TDateTime ;
    Resp        : TModalResult ;
    SubTot, Desc: Double ;
begin
  ACBrECF1.CarregaFormasPagamento ;
  if ACBrECF1.FormasPagamento.Count < 1 then
     raise Exception.Create('Nenhuma Forma de Pagamento programada no ECF');

  cItens := '10' ;
  if not InputQuery('Teste de Velocidade',
                'Numero de Itens a imprimir:', cItens ) then
     exit ;

  cCupons := '1' ;
  if not InputQuery('Teste de Velocidade',
                'Numero de Cupons a imprimir:', cCupons ) then
     exit ;
     
  Resp := MessageDlg('Monitorar estado do ECF ?',mtConfirmation,
                             mbYesNoCancel,0) ;
  if Resp = mrCancel then
     exit ;

  nItens := StrToIntDef(cItens,0) ;
  if nItens < 1 then
     exit ;
  nCupons := StrToIntDef(cCupons,0) ;
  if nCupons < 1 then
     exit ;

  wbBobina.Navigate('about:blank');
  Form1.Enabled := False ;
  try
     For J := 1 to nCupons do
     begin
	    tIni := Now ;
  		mResp.Lines.Add('Imprimindo '+cItens+ ' itens.') ;
  		mResp.Lines.Add('Iniciando Cupom: '+DateTimeToStr(tIni)) ;
  		ACBrECF1.AbreCupom();
  		mResp.Lines.Add('Cupom Aberto: '+  FormatFloat('###.##',SecondSpan(tIni,Now))+' segundos') ;
  		if Resp = mrYes then
     	   mResp.Lines.Add('Estado ECF: '+EstadoECF) ;

  	    For i := 1 to nItens do
  		begin
     	if i = 1 then
           if Resp = mrYes then
              mResp.Lines.Add('Estado ECF: '+EstadoECF) ;

           ACBrECF1.VendeItem( IntToStrZero(i,3),
                               'DESCRICAO PRODUTO: '+IntToStrZero(i,3),
                               'NN',1,i/100,0,'UN') ;
       { Aguarda 1 segundo ou at� o ECF ficar Em linha novamente }
       ACBrECF1.EmLinha( 1 ) ;
       mResp.Lines.Add('Item '+IntToStr(i)+': '+  FormatFloat('###.##',SecondSpan(tIni,Now))+' segundos');
       { Semelhante ao "AguardaImpressao := True", por�m � mais r�pido, pois no
         m�todo "VerificaFimImpressao" alem de verificado o "EmLinha" tamb�m �
         solicitado o Status do ECF }
     end ;

     SubTot := ACBrECF1.Subtotal ;
     Desc   := 0 ;
     if SubTot >= 1  then
        Desc := Frac( SubTot ) * -1 ;
     ACBrECF1.SubtotalizaCupom( Desc );
     mResp.Lines.Add('SubTotalizado: '+  FormatFloat('###.##',SecondSpan(tIni,Now))+' segundos');

     if Resp = mrYes then
        mResp.Lines.Add('Estado ECF: '+EstadoECF) ;

//   ACBrECF1.AbreGaveta ;

     { Executando todos os Pagamentos disponiveis }
(*   Parcela := max(  RoundTo(ACBrECF1.Subtotal/ACBrECF1.FormasPagamento.Count,-2),
                   0.01) ;
     For i := 1 to ACBrECF1.FormasPagamento.Count - 1 do
        Try
           ACBrECF1.EfetuaPagamento(ACBrECF1.FormasPagamento[i].Indice,  Parcela,
                               'OBSERVACAO PAGAMENTO: '+ IntToStrZero(i+1,2) );
        except
           Break ;
        end ;
*)
     { Efetuando ultimo pagamento no Item 0, deve zerar o Saldo a pagar }
     ACBrECF1.EfetuaPagamento(ACBrECF1.FormasPagamento[0].Indice,
        (ACBrECF1.Subtotal - ACBrECF1.TotalPago), 'ZERANDO SALDO A PAGAR RESTANTE');
     mResp.Lines.Add('Pagamento Efetuado: '+  FormatFloat('###.##',SecondSpan(tIni,Now))+' segundos');
     if Resp = mrYes then
        mResp.Lines.Add('Estado ECF: '+EstadoECF) ;

     ACBrECF1.FechaCupom('TESTE DE CUPOM');
     tFim := Now ;
     mResp.Lines.Add('Finalizado em: '+DateTimeToStr(tFim)) ;
     mResp.Lines.Add('Diferen�a: '+ FormatFloat('###.##',SecondSpan(tIni,tFim))+' segundos' ) ;
     mResp.Lines.Add('---------------------------------');
     AtualizaMemos ;
   end;
     finally
     Form1.Enabled := True ;
   end ;

end;

procedure TForm1.CapturaporNReduaoZ1Click(Sender: TObject);
Var Linhas : TStringList ;
    cRedIni, cRedFim : String ;
    I, nRedIni, nRedFim : Integer ;
begin
  cRedIni := '0' ;
  cRedFim := '0' ;

  if not InputQuery('Captura da Memoria Fiscal',
                'Entre com o a Redu�ao Z Inicial:', cRedIni ) then
     exit ;
  nRedIni := StrToIntDef(cRedIni,-1) ;
  if nRedIni < 0 then exit ;

  if not InputQuery('Captura da Memoria Fiscal',
                'Entre com o a Redu�ao Z Final:', cRedFim ) then
     exit ;
  nRedFim := StrToIntDef(cRedFim,-1) ;
  if nRedFim < 0 then exit ;

  Linhas := TStringList.Create ;
  try
     ACBrECF1.LeituraMemoriaFiscalSerial(nRedIni, nRedFim, Linhas);

     For I := 0 to Linhas.Count - 1 do
        mResp.Lines.Add(Linhas[I]) ;
  finally
     Linhas.Free ;
  end ;
  mResp.Lines.Add('---------------------------------');
end;

procedure TForm1.ImprimeporNReduaoZ1Click(Sender: TObject);
Var cRedIni, cRedFim : String ;
    nRedIni, nRedFim : Integer ;
begin
  cRedIni := '0' ;
  cRedFim := '0' ;

  if not InputQuery('Impress�o da Memoria Fiscal',
                'Entre com o a Redu�ao Z Inicial:', cRedIni ) then
     exit ;
  nRedIni := StrToIntDef(cRedIni,-1) ;
  if nRedIni < 0 then exit ;

  if not InputQuery('Impress�o da Memoria Fiscal',
                'Entre com o a Redu�ao Z Final:', cRedFim ) then
     exit ;
  nRedFim := StrToIntDef(cRedFim,-1) ;
  if nRedFim < 0 then exit ;

  ACBrECF1.LeituraMemoriaFiscal(nRedIni, nRedFim);
  mResp.Lines.Add('Leitura da Memoria Fiscal por Redu�ao');
end;

procedure TForm1.CapturaporPeriodo1Click(Sender: TObject);
Var Linhas : TStringList ;
    cDatIni, cDatFim : String ;
    dDatIni, dDatFim : TDateTime ;
    I : Integer ;
begin
  cDatIni := '01/'+FormatDateTime('mm/yy',now) ;
  cDatFim := FormatDateTime('dd/mm/yy',now) ;

  if not InputQuery('Captura da Memoria Fiscal',
                'Entre com o a Data Inicial (DD/MM/AA):', cDatIni ) then
     exit ;
  try
     dDatIni := StrToDateTime( StringReplace(cDatIni,'/', DateSeparator,
                                [rfReplaceAll] ) ) ;
  except
     exit ;
  end ;

  if not InputQuery('Captura da Memoria Fiscal',
                'Entre com o a Data Final (DD/MM/AA):', cDatFim ) then
     exit ;
  try
     dDatFim := StrToDateTime( StringReplace(cDatFim,'/', DateSeparator,
                                [rfReplaceAll] ) ) ;
  except
     exit
  end ;

  Linhas := TStringList.Create ;
  try
     ACBrECF1.LeituraMemoriaFiscalSerial(dDatIni, dDatFim, Linhas);

     For I := 0 to Linhas.Count - 1 do
        mResp.Lines.Add(Linhas[I]) ;
  finally
     Linhas.Free ;
  end ;
  mResp.Lines.Add('---------------------------------');
end;

procedure TForm1.ImprimeporPeriodo1Click(Sender: TObject);
Var cDatIni, cDatFim : String ;
    dDatIni, dDatFim : TDateTime ;
begin
  cDatIni := '01/'+FormatDateTime('mm/yy',now) ;
  cDatFim := FormatDateTime('dd/mm/yy',now) ;

  if not InputQuery('Captura da Memoria Fiscal',
                'Entre com o a Data Inicial (DD/MM/AA):', cDatIni ) then
     exit ;
  try
     dDatIni := StrToDateTime( StringReplace(cDatIni,'/', DateSeparator,
                                [rfReplaceAll] ) ) ;
  except
     exit ;
  end ;

  if not InputQuery('Captura da Memoria Fiscal',
                'Entre com o a Data Final (DD/MM/AA):', cDatFim ) then
     exit ;
  try
     dDatFim := StrToDateTime( StringReplace(cDatFim,'/', DateSeparator,
                                [rfReplaceAll] ) ) ;
  except
     exit
  end ;

  ACBrECF1.LeituraMemoriaFiscal(dDatIni, dDatFim );
  mResp.Lines.Add('Leitura da Memoria Fiscal por Datas');
end;

procedure TForm1.ProgramaAliquota1Click(Sender: TObject);
Var cAliq : String ;
    nAliq : Double ;
    Tipo  : Char ;
    Resp  : TModalResult ;
begin
  cAliq := '18,00' ;

  if not InputQuery('Programa�ao de Aliquotas',
                    'Entre com o valor da Aliquota:', cAliq ) then
     exit ;

  cAliq := StringReplace(StringReplace(cAliq,'.',DecimalSeparator,[]),
                                             ',',DecimalSeparator,[]) ;
  nAliq := StrToFloatDef(cAliq,0) ;
  if nAliq = 0 then
     exit ;

  Resp := MessageDlg('Aliquota do ICMS ?'+sLineBreak+'SIM = ICMS, NAO = ISS',
                mtConfirmation,mbYesNoCancel,0) ;
  case Resp of
    mrCancel : exit ;
    mrYes    : Tipo := 'T' ;
  else ;
    Tipo := 'S' ;
  end;

  if MessageDlg('A aliquota: ['+FloatToStr(nAliq)+'] do Tipo: ['+Tipo+
                '] ser� programada.'+sLineBreak+sLineBreak+
                'Cuidado !! A programa��o de Aliquotas � irreversivel'+sLineBreak+
                'Confirma a opera��o ?',mtConfirmation,mbYesNoCancel,0) <> mrYes then
     exit ;

  ACBrECF1.ProgramaAliquota(nAliq,Tipo);
  AliquotasICMS1Click(Sender);
end;

procedure TForm1.ProgramaFormadePagamento1Click(Sender: TObject);
Var cDescricao : String ;
    Vinculado  : Boolean ;
    Resp       : TModalResult ;
begin
  cDescricao := 'CARTAO' ;
  Vinculado  := true ;

  if not InputQuery('Programa�ao de Formas de Pagamento (FPG)',
                    'Entre com a Descri�ao:', cDescricao ) then
     exit ;

  if not (ACBrECF1.Modelo in [ecfBematech, ecfNaoFiscal, ecfMecaf]) then
  begin
     Resp := MessageDlg('Permite Vinculado nessa Forma de Pagamento ?',
                   mtConfirmation,mbYesNoCancel,0) ;
     if Resp = mrCancel then
        exit
     else
        Vinculado := (Resp = mrYes) ;
  end ;

  if MessageDlg('A Forma de Pagamento: ['+cDescricao+'] '+
                'ser� programada.'+sLineBreak+sLineBreak+
                'Cuidado !! A programa��o de Formas de Pagamento � irreversivel'+sLineBreak+
                'Confirma a opera��o ?',mtConfirmation,mbYesNoCancel,0) <> mrYes then
     exit ;

  ACBrECF1.ProgramaFormaPagamento(cDescricao,Vinculado);
  FormasdePagamento1Click(Sender);
end;

procedure TForm1.ProgramaComprovanteNAOFiscal1Click(Sender: TObject);
Var cDescricao, cTipo : String ;
begin
  cDescricao := 'CARTAO' ;
  cTipo      := '' ;

  if not InputQuery('Programa�ao de Comprovantes NAO Fiscais (CNF)',
                    'Entre com a Descri�ao:', cDescricao ) then
     exit ;

  case ACBrECF1.Modelo of
    ecfSchalter :
       if not InputQuery('Comprovantes NAO Fiscal '+ACBrECF1.ModeloStr,
                         'Entre com a String do parametro "Tipo".'+sLineBreak+
                         'D - Permite Desconto e Item '+sLineBreak+
                         'A - Permite Acrescimo no Subtotal'+sLineBreak+
                         'C - Permite Cancelamento de Item'+sLineBreak+
                         'P - Obriga forma de Pagamento'+sLineBreak+sLineBreak+
                         'Vnn - Obriga emissao de vinculado na Forma de '+
                         'Pagamento nn'+sLineBreak+sLineBreak+
                         'Se vazio assume Default = "DAC"'+sLineBreak+
                         'Exemplos:  V04 -> Torna a Emissao do Cupom Fiscal '+
                         'Vinculado obrigat�ria para a Forma de Pagamento 04',
                         cTipo ) then
          exit ;

    ecfDaruma :
       if not InputQuery('Comprovantes NAO Fiscal '+ACBrECF1.ModeloStr,
                         'Entre com a String do parametro "Tipo".'+sLineBreak+
                         'V  Comprovante Vinculado'+sLineBreak+
                         '+  Entrada de Recursos'+sLineBreak+
                         '-  Saida de Recursos'+sLineBreak+sLineBreak+
                         'Se vazio assume Default = "V"'+sLineBreak+
                         'Informe Apenas uma das Op�oes',
                         cTipo ) then
          exit ;

    ecfSweda, ecfQuattro :
       if not InputQuery('Comprovantes NAO Fiscal '+ACBrECF1.ModeloStr,
                         'Entre com a String do parametro "Tipo".'+sLineBreak+
                         '&  Cria�ao de um novo Grupo (Titulo)'+sLineBreak+
                         '+  Entrada de Recursos'+sLineBreak+
                         '-  Saida de Recursos'+sLineBreak+sLineBreak+
                         'Se vazio assume Default = "+"'+sLineBreak+
                         'Informe Apenas uma das Op�oes',
                         cTipo ) then
          exit ;

    ecfFiscNET, ecfICash :
       if not InputQuery('Comprovantes NAO Fiscal '+ACBrECF1.ModeloStr,
                         'Entre com a String do parametro "Tipo".'+sLineBreak+
                         '+  Entrada de Recursos'+sLineBreak+
                         '-  Saida de Recursos'+sLineBreak+sLineBreak+
                         'Se vazio assume Default = "+"'+sLineBreak+
                         'Informe Apenas uma das Op�oes',
                         cTipo ) then
          exit ;

  end;

  if MessageDlg('O Comprovante Nao Fiscal: ['+cDescricao+'] '+
                IfThen(ACBrECF1.Modelo in
                       [ecfDaruma,ecfSchalter,ecfSweda,ecfQuattro,ecfFiscNET],
                       ' do Tipo: ['+cTipo+'] ','') +
                'ser� programado.'+sLineBreak+sLineBreak+
                'Cuidado !! A programa��o de CNFs � irreversivel'+sLineBreak+
                'Confirma a opera��o ?',mtConfirmation,mbYesNoCancel,0) <> mrYes then
     exit ;

  ACBrECF1.ProgramaComprovanteNaoFiscal(cDescricao,cTipo);
  CarregaComprovantesNAOFiscais1Click(Sender);
end;

procedure TForm1.ProgramaRelatrioGerencial1Click(Sender: TObject);
var
   Descricao : String;
begin
  if not InputQuery('Programa�ao de Relat�rios Gerenciais',
                    'Entre com a Descri��o do Relat�rio Gerencial:', Descricao ) then
     exit ;
  if MessageDlg('O Relat�rio: [' + Descricao + '] ser� programado.'+sLineBreak+sLineBreak+
                'Cuidado a programa��o de Relat�rios Gerenciais � irreversivel'+sLineBreak+
                'Confirma a opera��o ?',mtConfirmation,mbYesNoCancel,0) <> mrYes then
     exit ;
  ACBrECF1.ProgramaRelatoriosGerenciais( Descricao );
  CarregaRelatriosGerenciais1Click(Sender)
end;

procedure TForm1.ACBrECF1MsgPoucoPapel(Sender: TObject);
begin
  mResp.Lines.Add('ATEN��O... POUCO PAPEL') ;
end;

procedure TForm1.CorrigeEstadodeErro1Click(Sender: TObject);
begin
  ACBrECF1.CorrigeEstadoErro ;
end;

procedure TForm1.ChequePronto1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'Cheque Pronto: '+
     IfThen( ACBrECF1.ChequePronto,'SIM','NAO'));
  AtualizaMemos ;
end;

procedure TForm1.ImprimeCheque1Click(Sender: TObject);
Var sValor : String ;
    dValor : Double ;
    sBanco, sFavorecido, sCidade : String ;
begin
  sValor      := '10,00' ;
  sBanco      := '001' ;
  sFavorecido := 'Projeto ACBr' ;
  sCidade     := 'Sao Paulo' ;

  if not InputQuery('Impress�o de Cheque',
                    'Entre com o valor do Cheque:', sValor ) then
     exit ;

  sValor := StringReplace(StringReplace(sValor,'.',DecimalSeparator,[]),
                                               ',',DecimalSeparator,[]) ;
  dValor := StrToFloatDef(sValor,0) ;
  if dValor = 0 then
     exit ;

  if not InputQuery('Impress�o de Cheque',
                    'Entre com o Numero do Banco', sBanco ) then
     exit ;

  if not InputQuery('Impress�o de Cheque',
                    'Entre com o Favorecido', sFavorecido ) then
     exit ;

  if not InputQuery('Impress�o de Cheque',
                    'Entre com a Cidade', sCidade ) then
     exit ;

  while not ACBrECF1.ChequePronto do
     if (MessageDlg('Favor inserir o cheque e pressionar OK',
          mtConfirmation,[mbOk,mbCancel],0) = mrCancel) then
        exit ;

  ACBrECF1.ImprimeCheque(sBanco,dValor,sFavorecido,sCidade,now,
      'TESTE DE IMPRESSAO DE CHEQUE');

  mResp.Lines.Add('ImprimeCheque Banco:'+sBanco+
                  ' Valor:'+sValor+
                  ' Favorecido:'+sFavorecido+
                  ' Cidade:'+sCidade+
                  ' Data:'+FormatDateTime('dd/mm/yy',now) );
  AtualizaMemos ;
end;

procedure TForm1.CancelaImpressoCheque1Click(Sender: TObject);
begin
  ACBrECF1.CancelaImpressaoCheque ;
  mResp.Lines.Add( 'CancelaImpressaoCheque');
  AtualizaMemos ;
end;


procedure TForm1.CarregaUnidadesdeMedida1Click(Sender: TObject);
var
   A: Integer;
begin
  ACBrECF1.CarregaUnidadesMedida ;

  for A := 0 to ACBrECF1.UnidadesMedida.Count -1 do
  begin
     if ACBrECF1.UnidadesMedida[A].Descricao <> '' then
        mResp.Lines.Add( 'Unid Medida: '+ACBrECF1.UnidadesMedida[A].Indice+' -> '+
           ACBrECF1.UnidadesMedida[A].Descricao);
  end ;
  mResp.Lines.Add('---------------------------------');

end;

procedure TForm1.ProgramaUnidadeMedida1Click(Sender: TObject);
var
   um:String;
begin
  if not InputQuery('Programa�ao de Unidades de Medida',
                    'Entre com a Descri��o da Unidade de Medida:', um ) then
     exit ;
  if MessageDlg('A Unidade de Medida: ['+um+'] ser� programada.'+sLineBreak+sLineBreak+
                'Cuidado a programa��o de Unidades de Medida � irreversivel'+sLineBreak+
                'Confirma a opera��o ?',mtConfirmation,mbYesNoCancel,0) <> mrYes then
     exit ;
  ACBrECF1.ProgramaUnidadeMedida( um );
end;

procedure TForm1.AbreRelatorioGerencial1Click(Sender: TObject);
Var
  IndiceStr : String;
begin
  IndiceStr :=  '1';
  if not InputQuery('Abertura de Relat�rio Gerencial',
                    'Digite o Indice do Relat�rio Gerencial a ser utilizado',
                    IndiceStr ) then
     exit ;
  ACBrECF1.AbreRelatorioGerencial(StrToIntDef(IndiceStr, 0)) ;
end;

procedure TForm1.AbreCupomVinculado1Click(Sender: TObject);
Var COO, CodFormaPagamento, CodComprovanteNaoFiscal : String;
    sValor : String ;
    dValor : Double ;
begin
  COO := ACBrECF1.NumCupom ;
  CodFormaPagamento := '01' ;
  CodComprovanteNaoFiscal := ' ' ;
  sValor := '0' ;

  if not InputQuery('Abertura de Cupom Vinculado',
                    'Digite o Cod.Forma Pagamento utilizada no cupom anterior',
                    CodFormaPagamento ) then
     exit ;

  if not InputQuery('Abertura de Cupom Vinculado',
                    'Digite o Cod.Comprovante N�o Fiscal'+sLineBreak+
                    '(N�o � necess�rio na maioria dos modelos)',
                     CodComprovanteNaoFiscal ) then
     exit ;

  if not InputQuery('Abertura de Cupom Vinculado',
                    'Digite o Valor a vincular no cupom anterior'+sLineBreak+
                    '(N�o � necess�rio em alguns modelos)',
                    sValor ) then
     exit ;


  sValor := StringReplace(StringReplace(sValor,'.',DecimalSeparator,[]),
                                               ',',DecimalSeparator,[]) ;
  dValor := StrToFloatDef(sValor,0) ;
  if dValor = 0 then
     exit ;

  if Trim(CodComprovanteNaoFiscal) <> '' then
     ACBrECF1.AbreCupomVinculado( COO,CodFormaPagamento,CodComprovanteNaoFiscal,
                                  dValor)
  else
     ACBrECF1.AbreCupomVinculado(COO,CodFormaPagamento,dValor) ;
end;

procedure TForm1.ImprimeLinhaRelatorio1Click(Sender: TObject);
var
   Linha :String;
begin
  if not InputQuery('Inpress�o de Linha N�O Fiscal',
                    'Digite a linha a imprimir',
                    Linha ) then
     exit ;
  ACBrECF1.LinhaRelatorioGerencial( Linha, 1 );
end;

procedure TForm1.ImprimeLinhaVinculado1Click(Sender: TObject);
var
   Linha :String;
begin
  if not InputQuery('Digite a linha a imprimir',
                    '', Linha ) then
     exit ;
  ACBrECF1.LinhaCupomVinculado( Linha );
end;

procedure TForm1.ListaRelatorioGerencial1Click(Sender: TObject);
begin
  frRelatorio.TipoRelatorio := 'G' ;
  frRelatorio.ShowModal ;
end;

procedure TForm1.RelatorioGerencialcomformatacao1Click(Sender: TObject);
begin
  frmGerencialFormatado := TfrmGerencialFormatado.Create(Self);
  try
    frmGerencialFormatado.ShowModal;
  finally
    FreeAndNil(frmGerencialFormatado);
  end;
end;

procedure TForm1.ListaCupomVinculado1Click(Sender: TObject);
begin
  MessageDlg('Para imprimir um Cupom Vinculado voc� deve ter '+
             'informa�oes dos Pagamentos Efetuados no �ltimo Cupom Fiscal',
             mtInformation,[mbOk],0) ;
  frRelatorio.TipoRelatorio := 'V' ;
  frRelatorio.ShowModal ;
end;

procedure TForm1.PularLinhas1Click(Sender: TObject);
Var Linhas : String ;
begin
  Linhas := IntToStr( ACBrECF1.LinhasEntreCupons ) ;
  if not InputQuery('Pular Linhas',
                    'Digite o Numero de Linhas a Pular', Linhas ) then
     exit ;

  ACBrECF1.PulaLinhas( StrToIntDef(Linhas,0) ) ;
end;

procedure TForm1.chGavetaSinalInvertidoClick(Sender: TObject);
begin
  ACBrECF1.GavetaSinalInvertido := chGavetaSinalInvertido.Checked ;
end;

procedure TForm1.LerTodasasVariveis1Click(Sender: TObject);
begin
  DataHora1.Click ;
  NumECF1.Click ;
  NumLoja1.Click ;
  NSrie1.Click ;
  NVerso1.Click ;
  NumCRO1.Click ;
  NUltimoCupom1.Click ;
  SubTotal1.Click ;
  TotalPago1.Click ;

  PoucoPapel1.Click ;
  HorarioVerao2.Click ;
  Arredonda1.Click ;

  AliquotasICMS1.Click ;
  FormasdePagamento1.Click ;
  CarregaComprovantesNAOFiscais1.Click ;
  CarregaUnidadesdeMedida1.Click ;
end;

procedure TForm1.MFD1Click(Sender: TObject);
begin
  mResp.Lines.Add( '� MFD: '+
     IfThen( ACBrECF1.MFD , 'SIM', 'NAO') );
  AtualizaMemos ;
end;

procedure TForm1.Termica1Click(Sender: TObject);
begin
  mResp.Lines.Add( '� Termica: '+
     IfThen( ACBrECF1.Termica , 'SIM', 'NAO') );
  AtualizaMemos ;
end;

procedure TForm1.edLogChange(Sender: TObject);
begin
  ACBrECF1.ArqLOG := edLog.Text ;
end;

procedure TForm1.SbArqLogClick(Sender: TObject);
begin
  OpenURL( ExtractFilePath( Application.ExeName ) + edLog.Text);
end;

procedure TForm1.cbMemoHTMLClick(Sender: TObject);
begin
  if cbMemoHTML.Checked then
   begin
     ACBrECF1.MemoParams.Values['HTML'] := '1' ;
     wbBobina.BringToFront ;
   end
  else
   begin
     ACBrECF1.MemoParams.Values['HTML'] := '0' ;
     wbBobina.SendToBack ;
   end ;

  mBobina.Visible  := not cbMemoHTML.Checked ;
  ACBrECF1.MemoLeParams ;
end;

procedure TForm1.bBobinaLimparClick(Sender: TObject);
begin
  wbBobina.Navigate('about:blank');
  mBobina.Clear ;
  if bBobinaParams.Caption = 'Salvar' then
  begin
     cbMemoHTMLClick(Sender);
     bBobinaParams.Caption := 'Parametros' ;
  end ;
end;

procedure TForm1.bBobinaParamsClick(Sender: TObject);
begin
  if bBobinaParams.Caption = 'Parametros' then
   begin
     mBobina.Text := ACBrECF1.MemoParams.Text ;
     mBobina.Visible  := True ;
     wbBobina.SendToBack ;
     bBobinaParams.Caption := 'Salvar' ;
   end
  else
   begin
     ACBrECF1.MemoParams.Text := mBobina.Text ;
     ACBrECF1.MemoParams.SaveToFile('ACBrECFMemoParams.ini');
     cbMemoHTMLClick(Sender);
     bBobinaParams.Caption := 'Parametros' ;
     bBobinaLimpar.Click ;
   end ;
end;

procedure TForm1.ACBrECF1BobinaAdicionaLinhas(const Linhas,
  Operacao: String);
begin
  if bBobinaParams.Caption = 'Salvar' then
  begin
     mBobina.Clear ;
     cbMemoHTMLClick(nil);
     bBobinaParams.Caption := 'Parametros' ;
  end ;

  WB_LoadHTML(wbBobina, mBobina.Text);
  Application.ProcessMessages ;

  WB_ScrollToBottom(wbBobina);
end;

procedure TForm1.ACBrECF1ChangeEstado(const EstadoAnterior,
  EstadoAtual: TACBrECFEstado);
var
  sEstAnterior, sEstAtual: String;
begin
  case EstadoAnterior of
    estNaoInicializada: sEstAnterior := 'estNaoInicializada';
    estDesconhecido: sEstAnterior := 'estDesconhecido';
    estLivre: sEstAnterior := 'estLivre';
    estVenda: sEstAnterior := 'estVenda';
    estPagamento: sEstAnterior := 'estPagamento';
    estRelatorio: sEstAnterior := 'estRelatorio';
    estBloqueada: sEstAnterior := 'estBloqueada';
    estRequerZ: sEstAnterior := 'estRequerZ';
    estRequerX: sEstAnterior := 'estRequerX';
    estNaoFiscal: sEstAnterior := 'estNaoFiscal';
  end;

  case EstadoAtual of
    estNaoInicializada: sEstAtual := 'estNaoInicializada';
    estDesconhecido: sEstAtual := 'estDesconhecido';
    estLivre: sEstAtual := 'estLivre';
    estVenda: sEstAtual := 'estVenda';
    estPagamento: sEstAtual := 'estPagamento';
    estRelatorio: sEstAtual := 'estRelatorio';
    estBloqueada: sEstAtual := 'estBloqueada';
    estRequerZ: sEstAtual := 'estRequerZ';
    estRequerX: sEstAtual := 'estRequerX';
    estNaoFiscal: sEstAtual := 'estNaoFiscal';
  end;

  StatusBar1.Panels[1].Text := Format('Anterior: %s - Atual: %s', [sEstAnterior, sEstAtual]);
end;

procedure TForm1.WB_LoadHTML(WebBrowser: TWebBrowser; HTMLCode: string);
var
  sl: TStringList;
  ms: TMemoryStream;
begin
  WebBrowser.Navigate('about:blank');
  while WebBrowser.ReadyState < READYSTATE_INTERACTIVE do
   Application.ProcessMessages;

  if Assigned(WebBrowser.Document) then
  begin
    sl := TStringList.Create;
    try
      ms := TMemoryStream.Create;
      try
        sl.Text := HTMLCode;
        sl.SaveToStream(ms);
        ms.Seek(0, 0);
        (WebBrowser.Document as IPersistStreamInit).Load(TStreamAdapter.Create(ms));
      finally
        ms.Free;
      end;
    finally
      sl.Free;
    end;
  end;
end;

procedure TForm1.WB_ScrollToTop(WebBrowser1: TWebBrowser);
var
 scrollpos: Integer;
 pw : IHTMLWindow2;
 Doc: IHTMLDocument2;
begin
 Doc := WebBrowser1.Document as IHTMLDocument2;
 pw := IHTMLWindow2(Doc.parentWindow);
 scrollPos := pw.screen.height;
 pw.scrollBy(0, -scrollpos);
end;

procedure TForm1.WB_ScrollToBottom(WebBrowser1: TWebBrowser);
var
 scrollpos: Integer;
 pw : IHTMLWindow2;
 Doc: IHTMLDocument2;
begin
 Doc := WebBrowser1.Document as IHTMLDocument2;
 pw := IHTMLWindow2(Doc.parentWindow);
 scrollPos := pw.screen.height;
 pw.scrollBy(0, scrollpos);
end;

procedure TForm1.DadosReducaoZ1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'Dados da Redu��o Z' + sLineBreak + ACBrECF1.DadosReducaoZ );
  AtualizaMemos ;
end;

procedure TForm1.CNPJIE1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'CNPJ: ('+ ACBrECF1.CNPJ+')' );
  AtualizaMemos ;
end;

procedure TForm1.IE1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'IE: ('+ ACBrECF1.IE+')' );
  AtualizaMemos ;
end;

procedure TForm1.NumCRZ1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'Num CRZ: ('+ ACBrECF1.NumCRZ+')' );
  AtualizaMemos ;
end;

procedure TForm1.NumCOOInicial1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'Num NumCOOInicial: ('+ ACBrECF1.NumCOOInicial+')' );
  AtualizaMemos ;
end;

procedure TForm1.VendaBruta1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'VendaBruta: ('+ FloatToStr(ACBrECF1.VendaBruta)+')' );
  AtualizaMemos ;
end;

procedure TForm1.GrandeTotal1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'GrandeTotal: ('+ FloatToStr(ACBrECF1.GrandeTotal)+')' );
  AtualizaMemos ;
end;

procedure TForm1.TotalCancelamentos1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'TotalCancelamentos: ('+ FloatToStr(ACBrECF1.TotalCancelamentos)+')' );
  AtualizaMemos ;
end;

procedure TForm1.TotalDescontos1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'TotalDescontos: ('+ FloatToStr(ACBrECF1.TotalDescontos)+')' );
  AtualizaMemos ;
end;

procedure TForm1.TotalAcrescimos1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'TotalAcrescimos: ('+ FloatToStr(ACBrECF1.TotalAcrescimos)+')' );
  AtualizaMemos ;
end;

procedure TForm1.otalIsencao1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'TotalIsencaoISSQN: ('+ FloatToStr(ACBrECF1.TotalIsencaoISSQN)+')' );
  AtualizaMemos ;
end;

procedure TForm1.otalNaoTributadoISSQN1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'TotalNaoTributadoISSQN: ('+ FloatToStr(ACBrECF1.TotalNaoTributadoISSQN)+')' );
  AtualizaMemos ;
end;

procedure TForm1.otalNoFiscal1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'TotalNaoFiscal: ('+ FloatToStr(ACBrECF1.TotalNaoFiscal)+')' );
  AtualizaMemos ;
end;

procedure TForm1.otalSubstituicaoTributariaISSQN1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'TotalSubstituicaoTributariaISSQN: ('+ FloatToStr(ACBrECF1.TotalSubstituicaoTributariaISSQN)+')' );
  AtualizaMemos ;
end;

procedure TForm1.TotalSubstituicaoTributaria1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'TotalSubstituicaoTributaria: ('+ FloatToStr(ACBrECF1.TotalSubstituicaoTributaria)+')' );
  AtualizaMemos ;
end;

procedure TForm1.TotalNaoTributado1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'TotalNaoTributado: ('+ FloatToStr(ACBrECF1.TotalNaoTributado)+')' );
  AtualizaMemos ;
end;

procedure TForm1.TotalIsencao1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'TotalIsencao: ('+ FloatToStr(ACBrECF1.TotalIsencao)+')' );
  AtualizaMemos ;
end;

procedure TForm1.UltimoItemVendido1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'NumUltItem: ('+ IntToStr(ACBrECF1.NumUltItem)+')' );
  AtualizaMemos ;
end;

procedure TForm1.ParametroDescontoISSQN1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'Parametro Desconto ISSQN: '+
     IfThen( ACBrECF1.ParamDescontoISSQN , 'SIM', 'NAO') );
  AtualizaMemos ;
end;

procedure TForm1.PorCOO1Click(Sender: TObject);
Var Linhas : TStringList ;
    cCOOIni, cCOOFim : String ;
    I, nCOOIni, nCOOFim : Integer ;
begin
  cCOOIni := '0' ;
  cCOOFim := '0' ;

  if not InputQuery('Captura da MFD',
                'Entre com o COO Inicial:', cCOOIni ) then
     exit ;
  nCOOIni := StrToIntDef(cCOOIni,-1) ;
  if nCOOIni < 0 then exit ;

  if not InputQuery('Captura da MFD',
                'Entre com o COO Final:', cCOOFim ) then
     exit ;
  nCOOFim := StrToIntDef(cCOOFim,-1) ;
  if nCOOFim < 0 then exit ;

  Linhas := TStringList.Create ;
  try
     ACBrECF1.LeituraMFDSerial(nCOOIni, nCOOFim, Linhas );

     For I := 0 to Linhas.Count - 1 do
        mResp.Lines.Add(Linhas[I]) ;
  finally
     Linhas.Free ;
  end ;
  mResp.Lines.Add('---------------------------------');
end;

procedure TForm1.PorPeriodo1Click(Sender: TObject);
Var Linhas : TStringList ;
    cDatIni, cDatFim : String ;
    dDatIni, dDatFim : TDateTime ;
    I : Integer ;
begin
  cDatIni := '01/'+FormatDateTime('mm/yy',now) ;
  cDatFim := FormatDateTime('dd/mm/yy',now) ;

  if not InputQuery('Captura da MFD',
                'Entre com o a Data Inicial (DD/MM/AA):', cDatIni ) then
     exit ;
  try
     dDatIni := StrToDateTime( StringReplace(cDatIni,'/', DateSeparator,
                                [rfReplaceAll] ) ) ;
  except
     exit ;
  end ;

  if not InputQuery('Captura da MFD',
                'Entre com o a Data Final (DD/MM/AA):', cDatFim ) then
     exit ;
  try
     dDatFim := StrToDateTime( StringReplace(cDatFim,'/', DateSeparator,
                                [rfReplaceAll] ) ) ;
  except
     exit
  end ;

  Linhas := TStringList.Create ;
  try
     ACBrECF1.LeituraMFDSerial(dDatIni, dDatFim, Linhas);

     For I := 0 to Linhas.Count - 1 do
        mResp.Lines.Add(Linhas[I]) ;
  finally
     Linhas.Free ;
  end ;
  mResp.Lines.Add('---------------------------------');
end;

procedure TForm1.Estado1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'Estado: '+ Estados[ ACBrECF1.Estado ] );
  AtualizaMemos ;
end;

procedure TForm1.NoFiscalCompleto1Click(Sender: TObject);
  Var Valor, CodCNF, CodFPG : String ;
begin
  CodCNF := '01' ;
  CodFPG := '01' ;
  Valor  := '0' ;

  if not InputQuery('Comprovante N�o Fiscal Completo',
                    'Entre com o indice do Comprovante N�o Fiscal', CodCNF ) then
     exit ;

  if not InputQuery('Comprovante N�o Fiscal Completo',
                    'Entre com o Valor do Comprovante N�o Fiscal', Valor ) then
     exit ;

  if not InputQuery('Comprovante N�o Fiscal Completo',
                    'Entre com o indice da Forma de Pagamento', CodFPG ) then
     exit ;

  ACBrECF1.NaoFiscalCompleto(CodCNF, StrToFloatDef( Valor,0 ), CodFPG,
     'TESTE DE COMPROVANTE NAO FISCAL');
  mResp.Lines.Add( 'Nao Fiscal Completo: '+ CodCNF +' '+ Valor+' '+
                   CodFPG );
  AtualizaMemos ;
end;

procedure TForm1.AbreNoFiscal1Click(Sender: TObject);
  Var CPF_CNPJ : String ;
begin
  if not InputQuery('Abre Comprovante N�o Fiscal',
                    'Se necess�rio, informe o CPF ou CNPJ do cliente', CPF_CNPJ ) then
     exit ;

  ACBrECF1.AbreNaoFiscal(CPF_CNPJ);
  mResp.Lines.Add( 'Abre N�o Fiscal: '+ CPF_CNPJ );
  AtualizaMemos ;
end;

procedure TForm1.RegistraItemNaoFiscal1Click(Sender: TObject);
  Var Valor, CodCNF : String ;
begin
  CodCNF := '01' ;
  Valor  := '0' ;

  if not InputQuery('Registra Item N�o Fiscal',
                    'Entre com o indice do Comprovante N�o Fiscal', CodCNF ) then
     exit ;

  if not InputQuery('Registra Item N�o Fiscal',
                    'Entre com o Valor do Comprovante N�o Fiscal', Valor ) then
     exit ;

  ACBrECF1.RegistraItemNaoFiscal(CodCNF, StrToFloatDef(Valor,0),
     'TESTE DE COMPROVANTE NAO FISCAL');
  mResp.Lines.Add( 'Registra Item Nao Fiscal: '+ CodCNF +' '+ Valor );
  AtualizaMemos ;
end;

procedure TForm1.SubTotalizaNaoFiscal1Click(Sender: TObject);
Var Desc : String ;
begin
  Desc := '0' ;

  if InputQuery('Subtotaliza N�o Fiscal',
                'Digite Valor negativo para Desconto'+#10+
                'ou Valor Positivo para Acrescimo' , Desc ) then
  begin
     ACBrECF1.SubtotalizaNaoFiscal( StrToFloat(Desc) );
     mResp.Lines.Add( 'Subtotaliza N�o Fiscal '+ Desc );
     AtualizaMemos ;
  end ;
end;

procedure TForm1.EfetuaPagamentoNaoFiscal1Click(Sender: TObject);
begin
  frPagamento.Show ;
  frPagamento.TipoCupom := 'N' ;
end;

procedure TForm1.FechaNoFiscal1Click(Sender: TObject);
Var
  Obs : String ;
  IndiceBMP: String;
begin
  Obs := 'Componentes ACBr|http://acbr.sourceforge.net' ;
  IndiceBMP :=  '0';
  if InputQuery('Fecha N�o Fiscal',
                'Se Necess�rio digite alguma Observa�ao (at� 8 linhas)'+#10+
                'O sinal | (pipe) ser� convertido para #10 (quebra de linha)' ,
                Obs ) then
  begin
     if (ACBrECF1.Modelo = ecfDaruma) and (ACBrECF1.MFD) then
       InputQuery('Impressao de imagem BMP ',
                  'Digite o Indice do BMP que deseja utilizar' ,
                   IndiceBMP );

     Obs := StringReplace(Obs,'|',#10,[rfReplaceAll,rfIgnoreCase]) ;
     ACBrECF1.FechaNaoFiscal( Obs, StrToIntDef(IndiceBMP, 0) );
     mResp.Lines.Add( 'Fecha N�o Fiscal: '+#10+Obs );
     AtualizaMemos ;
  end ;
end;

procedure TForm1.CancelaNoFiscal1Click(Sender: TObject);
begin
  ACBrECF1.CancelaNaoFiscal ;
  mResp.Lines.Add( 'Cancela N�o Fiscal' );
  AtualizaMemos ;
end;

procedure TForm1.NumCCF1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'Num.CCF: ('+ ACBrECF1.NumCCF +')' );
  AtualizaMemos ;
end;

procedure TForm1.NumCOO1Click(Sender: TObject);
begin
  NUltimoCupom1Click(Sender);
end;

procedure TForm1.IdentificaConsumidor1Click(Sender: TObject);
Var CPF, NOME, ENDERECO : String ;
begin
  CPF      := ACBrECF1.Consumidor.Documento ;
  NOME     := ACBrECF1.Consumidor.Nome ;
  ENDERECO := ACBrECF1.Consumidor.Endereco ;

  InputQuery('Identifica Consumidor',
             'Informe o Documento' ,CPF) ;
  InputQuery('Identifica Consumidor',
             'Informe o Nome do Consumidor' ,NOME) ;
  InputQuery('Identifica Consumidor',
             'Se necess�rios, informe o Endere�o do Consumidor' ,ENDERECO) ;

  ACBrECF1.IdentificaConsumidor( CPF, NOME, ENDERECO );
end;

procedure TForm1.edDirRFDChange(Sender: TObject);
begin
  ACBrRFD1.DirRFD := edDirRFD.Text ;
end;

procedure TForm1.sbDirRFDClick(Sender: TObject);
begin
  OpenURL( ACBrRFD1.DirRFD );
end;

procedure TForm1.bRFDLerClick(Sender: TObject);
begin
  if not ACBrRFD1.Ativo then
     raise Exception.Create('ACBrRFD n�o est� ativo');
     
  mRFDParam.Lines.LoadFromFile(ACBrRFD1.ArqINI);
end;

procedure TForm1.bRFDSalvarClick(Sender: TObject);
 Var OldAtivo : Boolean ;
begin
  OldAtivo := ACBrRFD1.Ativo ;
  try
     mRFDParam.Lines.SaveToFile(ACBrRFD1.ArqINI);
     ACBrRFD1.Desativar ;
  finally
     ACBrRFD1.Ativo := OldAtivo ;
  end ;
end;

procedure TForm1.chRFDClick(Sender: TObject);
 Var OldAtivo : Boolean ;
begin
  OldAtivo := ACBrECF1.Ativo ;
  try
     try
        ACBrECF1.Desativar ;

        if chRFD.Checked then
           ACBrECF1.RFD := ACBrRFD1
        else
           ACBrECF1.RFD := nil ;
     except
        chRFD.OnClick := nil ;
        chRFD.Checked := Assigned( ACBrECF1.RFD )  ;
        chRFD.OnClick := chRFDClick ;

        raise ;
     end ;
  finally
     ACBrECF1.Ativo := OldAtivo ;
  end ;
end;

procedure TForm1.GravarINI;
  Var ArqINI : String ;
      INI : TIniFile ;
begin
  ArqINI := ChangeFileExt( Application.ExeName,'.ini' ) ;

  INI := TIniFile.Create(ArqINI);
  try
     INI.WriteInteger('ECF','Modelo',cbxModelo.ItemIndex);
     INI.WriteString('ECF','Porta',cbxPorta.Text);
     INI.WriteInteger('ECF','TimeOut',seTimeOut.Value);
     INI.WriteInteger('ECF','IntervaloAposComando',seIntervaloAposComando.Value);
     INI.WriteBool('ECF','TentarNovamente',chTentar.Checked);
     INI.WriteBool('ECF','BloqueiaMouseTeclado',chBloqueia.Checked);
     INI.WriteBool('ECF','ExibeMsgAguarde',chExibeMsg.Checked);
     INI.WriteBool('ECF','ArredondaPorQtd',chArredondaPorQtd.Checked);
     INI.WriteBool('ECF','GavetaSinalInvertido',chGavetaSinalInvertido.Checked);
     INI.WriteBool('ECF','DescricaoGrande',chDescricaoGrande.Checked);
     INI.WriteString('ECF','MensagemAguarde',StringReplace(mMsg.Text,sLineBreak,'|',[rfReplaceAll]));
     INI.WriteString('ECF','ArqLog',edLog.Text);
     INI.WriteString('ECF','SerialParams',ACBrECF1.Device.ParamsString);
     INI.WriteString('ECF','Operador',ACBrECF1.Operador);

     INI.WriteBool('RFD','GerarRFD',chRFD.Checked);
     INI.WriteString('RFD','DirRFD',edDirRFD.Text);
     INI.WriteString('RFD','SH_RazaoSocial',edSH_RazaoSocial.Text);
     INI.WriteString('RFD','SH_COO',edSH_COO.Text);
     INI.WriteString('RFD','SH_CNPJ',edSH_CNPJ.Text);
     INI.WriteString('RFD','SH_IE',edSH_IE.Text);
     INI.WriteString('RFD','SH_IM',edSH_IM.Text);
     INI.WriteString('RFD','SH_Aplicativo',edSH_Aplicativo.Text);
     INI.WriteString('RFD','SH_NumeroAplicativo',edSH_NumeroAP.Text);
     INI.WriteString('RFD','SH_VersaoAplicativo',edSH_VersaoAP.Text);
     INI.WriteString('RFD','SH_Linha1',edSH_Linha1.Text);
     INI.WriteString('RFD','SH_Linha2',edSH_Linha2.Text);
  finally
     INI.Free ;
  end ;
end;

procedure TForm1.LerINI;
  Var ArqINI : String ;
      INI : TIniFile ;
begin
  ArqINI := ChangeFileExt( Application.ExeName,'.ini' ) ;

  INI := TIniFile.Create(ArqINI);
  try
     cbxModelo.ItemIndex := INI.ReadInteger('ECF','Modelo',cbxModelo.ItemIndex);
     cbxModeloChange(nil);
     cbxPorta.Text := INI.ReadString('ECF','Porta',cbxPorta.Text);
     seTimeOut.Value := INI.ReadInteger('ECF','TimeOut',seTimeOut.Value);
     seIntervaloAposComando.Value := INI.ReadInteger('ECF','IntervaloAposComando',seIntervaloAposComando.Value);
     chTentar.Checked := INI.ReadBool('ECF','TentarNovamente',chTentar.Checked);
     chBloqueia.Checked := INI.ReadBool('ECF','BloqueiaMouseTeclado',chBloqueia.Checked);
     chExibeMsg.Checked := INI.ReadBool('ECF','ExibeMsgAguarde',chExibeMsg.Checked);
     chArredondaPorQtd.Checked := INI.ReadBool('ECF','ArredondaPorQtd',chArredondaPorQtd.Checked);
     chDescricaoGrande.Checked := INI.ReadBool('ECF','DescricaoGrande',chDescricaoGrande.Checked);
     chGavetaSinalInvertido.Checked := INI.ReadBool('ECF','GavetaSinalInvertido',chGavetaSinalInvertido.Checked);
     mMsg.Text := StringReplace(INI.ReadString('ECF','MensagemAguarde',mMsg.Text),'|',sLineBreak,[rfReplaceAll]);
     edLog.Text := INI.ReadString('ECF','ArqLog',edLog.Text);
     ACBrECF1.Device.ParamsString := INI.ReadString('ECF','SerialParams','');
     edOperador.Text := INI.ReadString('ECF','Operador','');

     chRFD.Checked := INI.ReadBool('RFD','GerarRFD',chRFD.Checked);
     edDirRFD.Text := INI.ReadString('RFD','DirRFD',edDirRFD.Text);
     edSH_RazaoSocial.Text := INI.ReadString('RFD','SH_RazaoSocial',edSH_RazaoSocial.Text);
     edSH_COO.Text := INI.ReadString('RFD','SH_COO',edSH_COO.Text);
     edSH_CNPJ.Text := INI.ReadString('RFD','SH_CNPJ',edSH_CNPJ.Text);
     edSH_IE.Text := INI.ReadString('RFD','SH_IE',edSH_IE.Text);
     edSH_IM.Text := INI.ReadString('RFD','SH_IM',edSH_IM.Text);
     edSH_Aplicativo.Text := INI.ReadString('RFD','SH_Aplicativo',edSH_Aplicativo.Text);
     edSH_NumeroAP.Text := INI.ReadString('RFD','SH_NumeroAplicativo',edSH_NumeroAP.Text);
     edSH_VersaoAP.Text := INI.ReadString('RFD','SH_VersaoAplicativo',edSH_VersaoAP.Text);
     edSH_Linha1.Text := INI.ReadString('RFD','SH_Linha1',edSH_Linha1.Text);
     edSH_Linha2.Text := INI.ReadString('RFD','SH_Linha2',edSH_Linha2.Text);
  finally
     INI.Free ;
  end ;
end;

procedure TForm1.seTimeOutChange(Sender: TObject);
begin
  ACBrECF1.TimeOut := seTimeOut.Value ;
end;

procedure TForm1.seIntervaloAposComandoChange(Sender: TObject);
begin
  ACBrECF1.IntervaloAposComando := seIntervaloAposComando.Value ;
end;

procedure TForm1.edSH_RazaoSocialChange(Sender: TObject);
begin
  ACBrRFD1.SH_RazaoSocial := edSH_RazaoSocial.Text ;
end;

procedure TForm1.edSH_COOChange(Sender: TObject);
begin
  ACBrRFD1.SH_COO := edSH_COO.Text ;
end;

procedure TForm1.edSH_CNPJChange(Sender: TObject);
begin
  ACBrRFD1.SH_CNPJ := edSH_CNPJ.Text ;
end;

procedure TForm1.edSH_IEChange(Sender: TObject);
begin
  ACBrRFD1.SH_IE := edSH_IE.Text ;
end;

procedure TForm1.edSH_IMChange(Sender: TObject);
begin
  ACBrRFD1.SH_IM := edSH_IM.Text ;
end;

procedure TForm1.edSH_AplicativoChange(Sender: TObject);
begin
  ACBrRFD1.SH_NomeAplicativo := edSH_Aplicativo.Text ;
end;

procedure TForm1.edSH_NumeroAPChange(Sender: TObject);
begin
  ACBrRFD1.SH_NumeroAplicativo := edSH_NumeroAP.Text ;
end;

procedure TForm1.edSH_VersaoAPChange(Sender: TObject);
begin
  ACBrRFD1.SH_VersaoAplicativo := edSH_VersaoAP.Text ;
end;

procedure TForm1.edSH_Linha1Change(Sender: TObject);
begin
  ACBrRFD1.SH_Linha1 := edSH_Linha1.Text ;
end;

procedure TForm1.edSH_Linha2Change(Sender: TObject);
begin
  ACBrRFD1.SH_Linha2 := edSH_Linha2.Text ;
end;

procedure TForm1.Image1Click(Sender: TObject);
begin
  frmSobre := TfrmSobre.Create( self );
  try
     frmSobre.lVersao.Caption := 'Ver: '+ECFTeste_VERSAO+' ACBr '+ACBR_VERSAO ;
     frmSobre.ShowModal ;
  finally
     FreeAndNil(frmSobre) ;
  end ;
end;

procedure TForm1.DataMovimento1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'Data Movimento: ('+ FormatDateTime('dd/mm/yy',
                   ACBrECF1.DataMovimento) +')' );
  AtualizaMemos ;
end;

procedure TForm1.DAV1Click(Sender: TObject);
begin
  frmDAV := TfrmDAV.Create(Self);
  try
    frmDAV.ShowModal;
  finally
    FreeAndNil(frmDAV);
  end;
end;

procedure TForm1.DAVOS1Click(Sender: TObject);
begin
  frmDAVOS := TfrmDAVOS.Create(Self);
  try
    frmDAVOS.ShowModal;
  finally
    FreeAndNil(frmDAVOS);
  end;
end;

procedure TForm1.DadosUltimaReduoZ1Click(Sender: TObject);
Var
  AIni : TMemIniFile ;
  AStringList : TStringList ;
  Resp  : String ;
  AVal  : Double ;
  ADate : TDateTime ;
  AStr  : String ;
begin
  Resp := ACBrECF1.DadosUltimaReducaoZ ;
  mResp.Lines.Add( 'Dados da Ultima Redu��o Z' + sLineBreak + Resp );

  AStringList := TStringList.Create ;
  AIni := TMemIniFile.Create( 'DadosUltimaReducaoZ.ini' ) ;
  try
     AStringList.Text := Resp ;
     AIni.SetStrings(AStringList);

     // Lendo a Data do Movimento
     ADate := AIni.ReadDateTime('ECF','DataMovimento', 0) ;
     ShowMessage('Data do Movimento'+sLineBreak+DateToStr(ADate));

     // Lendo o NumCOOInicial
     AStr := AIni.ReadString('ECF','NumCOOInicial', '') ;
     ShowMessage('COO Inicial'+AStr);

     // Lendo a Venda Bruta:
     AVal := AIni.ReadFloat('Totalizadores','VendaBruta', 0) ;
     ShowMessage('Venda Bruta'+sLineBreak+FormatFloat('0.00',AVal));

  finally
     AIni.Free ;
     AStringList.Free ;
  end ;

  AtualizaMemos ;
end;

procedure TForm1.TotalNoFiscal1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'TotalNaoFiscal: ('+ FloatToStr(ACBrECF1.TotalNaoFiscal)+')' );
  AtualizaMemos ;
end;

procedure TForm1.btSerialClick(Sender: TObject);
  Var frConfiguraSerial : TfrConfiguraSerial ;
begin
  frConfiguraSerial := TfrConfiguraSerial.Create(self);

  try
    frConfiguraSerial.Device.Porta        := ACBrECF1.Device.Porta ;
    frConfiguraSerial.cmbPortaSerial.Text := cbxPorta.Text ;
    frConfiguraSerial.Device.ParamsString := ACBrECF1.Device.ParamsString ;

    if frConfiguraSerial.ShowModal = mrOk then
    begin
       cbxPorta.Text                := frConfiguraSerial.Device.Porta ;
       ACBrECF1.Device.ParamsString := frConfiguraSerial.Device.ParamsString ;
    end ;
  finally
     FreeAndNil( frConfiguraSerial ) ;
  end ;
end;

procedure TForm1.Button1Click(Sender: TObject);
begin
  ACBrECF1.AbreRelatorioGerencial;
  ACBRECF1.LinhaRelatorioGerencial('LINHA NORMAL 1');
  ACBRECF1.LinhaRelatorioGerencial(#14+'EXPANDIDO 1 LINHA');
  ACBRECF1.LinhaRelatorioGerencial('LINHA NORMAL 1');
  ACBRECF1.LinhaRelatorioGerencial(#15+'ON/OFF MODO CONDENSADO'+#18);
  ACBRECF1.LinhaRelatorioGerencial(#27+'W1'+'ON/OFF MODO EXPANDIDO'+#27+'W0');
  ACBRECF1.FechaRelatorio;
end;

procedure TForm1.Button2Click(Sender: TObject);
var
  I: integer;
begin
  ACBrECF1.DadosReducaoZ;

  mRZ.Clear;
  with ACBrECF1.DadosReducaoZClass do
  begin
     mRZ.Lines.Add( 'Data Impressora    : ' + DateToStr( DataDaImpressora ) );
     mRZ.Lines.Add( 'Numero S�rie       : ' + NumeroDeSerie );
     mRZ.Lines.Add( 'Numero S�rie MFD   : ' + NumeroDeSerieMFD );
     mRZ.Lines.Add( 'Numero ECF         : ' + NumeroDoECF );
     mRZ.Lines.Add( 'Numero Loja        : ' + NumeroDaLoja );
     mRZ.Lines.Add( 'Numero COO Inicial : ' + NumeroCOOInicial );

     mRZ.Lines.Add( '{ REDU��O Z }');
     mRZ.Lines.Add( 'Data Movimento  : ' +DateToStr( DataDoMovimento ) );
     mRZ.Lines.Add( '' );
     mRZ.Lines.Add( '{ CONTADORES }');
     mRZ.Lines.Add( 'COO  : ' + IntToStr(COO) );
     mRZ.Lines.Add( 'GNF  : ' + IntToStr(GNF) );
     mRZ.Lines.Add( 'CRO  : ' + IntToStr(CRO) );
     mRZ.Lines.Add( 'CRZ  : ' + IntToStr(CRZ) );
     mRZ.Lines.Add( 'CCF  : ' + IntToStr(CCF) );
     mRZ.Lines.Add( 'CFD  : ' + IntToStr(CFD) );
     mRZ.Lines.Add( 'CDC  : ' + IntToStr(CDC) );
     mRZ.Lines.Add( 'GRG  : ' + IntToStr(GRG) );
     mRZ.Lines.Add( 'GNFC : ' + IntToStr(GNFC) );
     mRZ.Lines.Add( 'CFC  : ' + IntToStr(CFC) );
     mRZ.Lines.Add( 'NCN  : ' + IntToStr(NCN) );
     mRZ.Lines.Add( 'CCDC : ' + IntToStr(CCDC  ) );
     mRZ.Lines.Add( '' );

     mRZ.Lines.Add( '{ TOTALIZADORES }' );
     mRZ.Lines.Add( 'Grande Total      : ' + FormatFloat('###,##0.00', ValorGrandeTotal) );
     mRZ.Lines.Add( 'VendaBruta        : ' + FormatFloat('###,##0.00', ValorVendaBruta) );
     mRZ.Lines.Add( 'CancelamentoICMS  : ' + FormatFloat('###,##0.00', CancelamentoICMS) );
     mRZ.Lines.Add( 'DescontoICMS      : ' + FormatFloat('###,##0.00', DescontoICMS) );
     mRZ.Lines.Add( 'CancelamentoISSQN : ' + FormatFloat('###,##0.00', CancelamentoISSQN) );
     mRZ.Lines.Add( 'DescontoISSQN     : ' + FormatFloat('###,##0.00', DescontoISSQN) );
     mRZ.Lines.Add( 'CancelamentoOPNF  : ' + FormatFloat('###,##0.00', CancelamentoOPNF) );
     mRZ.Lines.Add( 'DescontoOPNF      : ' + FormatFloat('###,##0.00', DescontoOPNF) );
     mRZ.Lines.Add( 'VendaLiquida      : ' + FormatFloat('###,##0.00', VendaLiquida) );
     mRZ.Lines.Add( 'AcrescimoICMS     : ' + FormatFloat('###,##0.00', AcrescimoICMS) );
     mRZ.Lines.Add( 'AcrescimoISSQN    : ' + FormatFloat('###,##0.00', AcrescimoISSQN) );
     mRZ.Lines.Add( 'AcrescimoOPNF     : ' + FormatFloat('###,##0.00', AcrescimoOPNF) );
     mRZ.Lines.Add( '' );

     mRZ.Lines.Add( '{ ICMS }' );
     for I := 0 to ICMS.Count -1 do
     begin
         mRZ.Lines.Add( 'Indice    : ' + ICMS[I].Indice );
         mRZ.Lines.Add( 'Tipo      : ' + ICMS[I].Tipo );
         mRZ.Lines.Add( 'Aliquota  : ' + FormatFloat('0.00', ICMS[I].Aliquota) );
         mRZ.Lines.Add( 'Total     : ' + FormatFloat('###,##0.00', ICMS[I].Total) );
     end;
     mRZ.Lines.Add( 'TotalICMS         : ' + FormatFloat('###,##0.00', TotalICMS) );
     mRZ.Lines.Add( 'SubstituicaoTributariaICMS: ' + FormatFloat('###,##0.00', SubstituicaoTributariaICMS) );
     mRZ.Lines.Add( 'IsentoICMS                : ' + FormatFloat('###,##0.00', IsentoICMS) );
     mRZ.Lines.Add( 'NaoTributadoICMS          : ' + FormatFloat('###,##0.00', NaoTributadoICMS) );
     mRZ.Lines.Add( '' );

     mRZ.Lines.Add( '{ ISSQN }' );
     for I := 0 to ISSQN.Count -1 do
     begin
         mRZ.Lines.Add( 'Indice    : ' + ISSQN[I].Indice );
         mRZ.Lines.Add( 'Tipo      : ' + ISSQN[I].Tipo );
         mRZ.Lines.Add( 'Aliquota  : ' + FormatFloat('0.00', ISSQN[I].Aliquota) );
         mRZ.Lines.Add( 'Total     : ' + FormatFloat('###,##0.00', ISSQN[I].Total) );
     end;
     mRZ.Lines.Add( 'TotalISSQN        : ' + FormatFloat('###,##0.00', TotalISSQN) );
     mRZ.Lines.Add( 'SubstituicaoTributariaISSQN: ' + FormatFloat('###,##0.00', SubstituicaoTributariaISSQN) );
     mRZ.Lines.Add( 'IsentoISSQN                : ' + FormatFloat('###,##0.00', IsentoISSQN) );
     mRZ.Lines.Add( 'NaoTributadoISSQN          : ' + FormatFloat('###,##0.00', NaoTributadoISSQN) );
     mRZ.Lines.Add( '' );

     mRZ.Lines.Add( '{ TOTALIZADORES N�O FISCAIS }' );
     for I := 0 to TotalizadoresNaoFiscais.Count -1 do
     begin
         mRZ.Lines.Add( 'Indice     : ' + TotalizadoresNaoFiscais[I].Indice );
         mRZ.Lines.Add( 'Descri��o  : ' + TotalizadoresNaoFiscais[I].Descricao );
         mRZ.Lines.Add( 'Forma Pagto: ' + TotalizadoresNaoFiscais[I].FormaPagamento );
         mRZ.Lines.Add( 'Total      : ' + FormatFloat('###,##0.00', TotalizadoresNaoFiscais[I].Total) );
     end;
     mRZ.Lines.Add( 'TotalOperacaoNaoFiscal : ' + FormatFloat('###,##0.00', TotalOperacaoNaoFiscal) );
     mRZ.Lines.Add( '' );

     mRZ.Lines.Add( '{ RELAT�RIO GERENCIAL }' );
     for I := 0 to RelatorioGerencial.Count -1 do
     begin
         mRZ.Lines.Add( 'Indice     : ' + RelatorioGerencial[I].Indice );
         mRZ.Lines.Add( 'Descri��o  : ' + RelatorioGerencial[I].Descricao );
     end;
     mRZ.Lines.Add( '' );

     mRZ.Lines.Add( '{ MEIOS DE PAGAMENTO }' );
     for I := 0 to MeiosDePagamento.Count -1 do
     begin
         mRZ.Lines.Add( 'Indice     : ' + MeiosDePagamento[I].Indice );
         mRZ.Lines.Add( 'Descri��o  : ' + MeiosDePagamento[I].Descricao );
         mRZ.Lines.Add( 'Total      : ' + FormatFloat('###,##0.00', MeiosDePagamento[I].Total) );
     end;
     mRZ.Lines.Add( 'Total Troco : ' + FormatFloat('###,##0.00', TotalTroco) );
  end;
end;

procedure TForm1.TestedeVinculado1Click(Sender: TObject);
Var cCupons, cFPG, cCOO : String ;
    nCupons, J : Integer ;
    tIni : TDateTime ;
begin
  ACBrECF1.CarregaFormasPagamento ;
  if ACBrECF1.FormasPagamento.Count < 1 then
     raise Exception.Create('Nenhuma Forma de Pagamento programada no ECF');

  cCupons := '1' ;
  if not InputQuery('Teste de Vinculado',
                'Numero de Cupons a imprimir:', cCupons ) then
     exit ;

  cFPG := '02' ;
  if not InputQuery('Teste de Vinculado',
                'Forma de Pagamento a utilizar:', cFPG ) then
     exit ;

  if ACBrECF1.AchaFPGIndice(cFPG) = nil then
     raise Exception.Create('Forma de pagamento '+cFPG+' n�o encontrada');
     
  nCupons := StrToIntDef(cCupons,0) ;
  if nCupons < 1 then
     exit ;

  For J := 1 to nCupons do
  begin
     tIni := Now ;
     mResp.Lines.Add('Iniciando Cupom: '+IntToStr(nCupons)+ ' - '+ DateTimeToStr(tIni) ) ;
     ACBrECF1.AbreCupom();
     mResp.Lines.Add('Cupom Aberto: '+  FormatFloat('###.##',SecondSpan(tIni,Now))+' segundos') ;

     ACBrECF1.VendeItem( '7654321',
                         'TESTE DE PRODUTO, CUPOM: '+IntToStrZero(nCupons,3),
                         'NN',1,1,0,'UN') ;
     mResp.Lines.Add('Item Vendido: '+  FormatFloat('###.##',SecondSpan(tIni,Now))+' segundos');

     ACBrECF1.SubtotalizaCupom( );
     mResp.Lines.Add('SubTotalizado: '+  FormatFloat('###.##',SecondSpan(tIni,Now))+' segundos');

     { Efetuando pagamento na FPG informada }
     ACBrECF1.EfetuaPagamento(cFPG, 1 , 'TESTE DE VINCULADO', True);
     mResp.Lines.Add('Pagamento Efetuado: '+  FormatFloat('###.##',SecondSpan(tIni,Now))+' segundos');

     ACBrECF1.FechaCupom('TESTE DE CUPOM VINCULADO');
     mResp.Lines.Add('Finalizado Cupom: '+FormatFloat('###.##',SecondSpan(tIni,Now))+' segundos') ;

     cCOO := ACBrECF1.NumCupom ;
     ACBrECF1.CupomVinculado(cCOO, cFPG, 1, frRelatorio.mRelat.Lines, 2);
{
     ACBrECF1.AbreCupomVinculado(cCOO, cFPG, 1);
     mResp.Lines.Add('Abrindo Vinculado: '+FormatFloat('###.##',SecondSpan(tIni,Now))+' segundos') ;
     ACBrECF1.LinhaCupomVinculado( frRelatorio.mRelat.Lines.Text ) ;
     ACBrECF1.PulaLinhas  ;
     ACBrECF1.AcionaGuilhotina ;
     ACBrECF1.LinhaCupomVinculado( frRelatorio.mRelat.Lines.Text ) ;
     mResp.Lines.Add('Imprimindo Linhas Vinculado: '+FormatFloat('###.##',SecondSpan(tIni,Now))+' segundos') ;
     ACBrECF1.FechaRelatorio ;
     mResp.Lines.Add('Finalizado Vinculado: '+FormatFloat('###.##',SecondSpan(tIni,Now))+' segundos') ;
}
     mResp.Lines.Add('---------------------------------');
     AtualizaMemos ;
  end ;
end;

procedure TForm1.CortaPapel1Click(Sender: TObject);
 Var Resp : TModalResult ;
begin
  Resp := MessageDlg('Corte Parcial ?',mtConfirmation,mbYesNoCancel,0) ;

  if Resp = mrCancel then
     exit ;

  ACBrECF1.CortaPapel( (Resp = mrYes) );
end;

procedure TForm1.Sangria1Click(Sender: TObject);
  Var CNF, FPG, cValor : String ;
      Valor : Double ;
begin
  CNF := 'SANGRIA' ;
  FPG := 'DINHEIRO' ;
  cValor := '0' ;

  if not InputQuery('Sangria',
                    'Entre com o Valor da Sangria', cValor ) then
     exit ;
  Valor := StrToIntDef(cValor,-1) ;
  if Valor <= 0 then
     exit ;

  if not InputQuery('Sangria',
                    'Entre com a Descri��o do Comprovante N�o Fiscal', CNF ) then
     exit ;

  if not InputQuery('Sangria',
                    'Entre com a Descri��o da Forrma de Pagamento', FPG ) then
     exit ;

  ACBrECF1.Sangria( Valor, 'TESTE DE SANGRIA', CNF, FPG ) ;

  mResp.Lines.Add( 'Sangria: '+ FloatToStr(Valor)+' '+ CNF +' '+FPG );
  AtualizaMemos ;
end;

procedure TForm1.Suprimento1Click(Sender: TObject);
  Var CNF, FPG, cValor : String ;
      Valor : Double ;
begin
  CNF := 'SUPRIMENTO' ;
  FPG := 'DINHEIRO' ;
  cValor := '0' ;

  if not InputQuery('Suprimento',
                    'Entre com o Valor do Suprimento', cValor ) then
     exit ;
  Valor := StrToFloatDef(cValor,-1) ;
  if Valor <= 0 then
     exit ;

  if not InputQuery('Suprimento',
                    'Entre com a Descri��o do Comprovante N�o Fiscal', CNF ) then
     exit ;

  if not InputQuery('Suprimento',
                    'Entre com a Descri��o da Forrma de Pagamento', FPG ) then
     exit ;

  ACBrECF1.Suprimento( Valor, 'TESTE DE SUPRIMENTO' , CNF, FPG ) ;

  mResp.Lines.Add( 'Suprimento: '+ FloatToStr(Valor)+' '+ CNF +' '+FPG );
  AtualizaMemos ;
end;

procedure TForm1.edOperadorChange(Sender: TObject);
begin
  ACBrECF1.Operador := edOperador.Text ;
end;

procedure TForm1.edMsgTrabalhandoChange(Sender: TObject);
begin
  ACBrECF1.MsgTrabalhando := edMsgTrabalhando.Text ;
end;

procedure TForm1.ConsultaRegistradorECF1Click(Sender: TObject);
var
   Indice : String;
   Linhas : String ;
begin
  if not InputQuery('Consulta Registrador ECF',
                    'Entre com o Indice da Informa��o:', Indice ) then
    Exit;

  Linhas := ACBrECF1.RetornaInfoECF( Indice );

  mResp.Lines.Add('Informa��o: ' + Linhas) ;
  AtualizaMemos();
end;

procedure TForm1.EstornaMeiodePagamento1Click(Sender: TObject);
begin
  frPagamento.Estado:= 'Estorno';
  frPagamento.Show ;
end;

procedure TForm1.DeCodificaTexto1Click(Sender: TObject);
Var
  Operacao : String;
  Texto    : String;
  Resposta : String;
begin
  Operacao:= 'C';
  if not InputQuery('De/Codificacao',
                'Informe a opera��o a ser realizada "C", "D" ou "V"', Operacao) then
    exit;

  Texto:= 'ACBr';
  if not InputQuery('Texto a ser de/codificado ou verificado',
                'Informe o texto a ser decodificado', Texto ) then
    exit;

  ACBrECF1.DeCodificaTexto(Operacao[1],Texto, Resposta);

  mResp.Lines.Add( 'Resposta: '+ Resposta );
  AtualizaMemos ;
end;

procedure TForm1.AchaAliquotaporIndice1Click(Sender: TObject);
var
  Aliquota  : TACBrECFAliquota;
  Indice    : String;
begin
  ACBrECF1.LerTotaisAliquota ;

  if not InputQuery('Acha Aliquota por Indice',
                    'Entre com o Indice:', Indice ) then
    Exit;

  Aliquota  :=  ACBrECF1.AchaICMSIndice(Indice);

  if Aliquota <> Nil then
  begin
    mResp.Lines.Add('Indice  : ' + Aliquota.Indice);
    mResp.Lines.Add('Aliquota: ' + FormatFloat('###,##0.00',Aliquota.Aliquota));
    mResp.Lines.Add('Valor atual do totalizador R$ ' + FormatFloat('###,##0.00',Aliquota.Total));
  end
  else
    mResp.Lines.Add('Indice (' + Indice + ') n�o encontrado!');

  AtualizaMemos();
end;

procedure TForm1.AchaAliquotaporValor1Click(Sender: TObject);
var
  Aliquota  : TACBrECFAliquota;
  ValorStr  : String;
  Valor     : Double;
begin
  ACBrECF1.LerTotaisAliquota ;

  if not InputQuery('Acha Aliquota por Valor',
                    'Entre com o Valor:', ValorStr ) then
    Exit;

  Valor     := StrToFloatDef(ValorStr, 0);
  Aliquota  :=  ACBrECF1.AchaICMSAliquota(Valor);

  if Aliquota <> Nil then
  begin
    mResp.Lines.Add('Indice  : ' + Aliquota.Indice);
    mResp.Lines.Add('Aliquota: ' + FormatFloat('###,##0.00',Aliquota.Aliquota));
    mResp.Lines.Add('Valor atual do totalizador R$ ' + FormatFloat('###,##0.00',Aliquota.Total));
  end
  else
    mResp.Lines.Add('Aliquota (' + FloatToStr(Valor) + ') n�o encontrada!');

  AtualizaMemos();

end;

procedure TForm1.AcharMeioPagamentoporIndice1Click(Sender: TObject);
var
  FormaPagto: TACBrECFFormaPagamento;
  Indice    : String;
begin
  ACBrECF1.LerTotaisFormaPagamento ;

  if not InputQuery('Acha Forma Pagamento por Indice',
                    'Entre com o Indice:', Indice ) then
    Exit;

  FormaPagto  :=  ACBrECF1.AchaFPGIndice(Indice);

  if FormaPagto <> Nil then
  begin
    mResp.Lines.Add('Indice   : ' + FormaPagto.Indice);
    mResp.Lines.Add('Descri��o: ' + FormaPagto.Descricao);
    mResp.Lines.Add('Valor atual do totalizador R$ ' + FormatFloat('###,##0.00',FormaPagto.Total));
  end
  else
    mResp.Lines.Add('Indice (' + Indice + ') n�o encontrado!');

  AtualizaMemos();
end;

procedure TForm1.AcharMeiodePagametoporDescrio1Click(Sender: TObject);
var
  FormaPagto: TACBrECFFormaPagamento;
  Descricao : String;
begin
  ACBrECF1.LerTotaisFormaPagamento ;

  if not InputQuery('Acha Forma Pagamento por Descri��o',
                    'Entre com a descri��o:', Descricao ) then
    Exit;

  FormaPagto  :=  ACBrECF1.AchaFPGDescricao(Descricao);

  if FormaPagto <> Nil then
  begin
    mResp.Lines.Add('Indice   : ' + FormaPagto.Indice);
    mResp.Lines.Add('Descri��o: ' + FormaPagto.Descricao);
    mResp.Lines.Add('Valor atual do totalizador R$ ' + FormatFloat('###,##0.00',FormaPagto.Total));
  end
  else
    mResp.Lines.Add('Forma de Pagamento (' + Descricao + ') n�o encontrada!');

  AtualizaMemos();

end;

procedure TForm1.AchaCNFporIndice1Click(Sender: TObject);
var
  CNF     : TACBrECFComprovanteNaoFiscal;
  Indice  : String;
begin
  ACBrECF1.LerTotaisComprovanteNaoFiscal ;

  if not InputQuery('Acha CNF por Indice',
                    'Entre com o Indice:', Indice ) then
    Exit;

  CNF  :=  ACBrECF1.AchaCNFIndice(Indice);

  if CNF <> Nil then
  begin
    mResp.Lines.Add('Indice   : ' + CNF.Indice);
    mResp.Lines.Add('CON      : ' + IntToStrZero(CNF.Contador, 4));
    mResp.Lines.Add('Descri��o: ' + CNF.Descricao);
    mResp.Lines.Add('Valor atual do totalizador R$ ' + FormatFloat('###,##0.00',CNF.Total));
  end
  else
    mResp.Lines.Add('Indice (' + Indice + ') n�o encontrado!');

  AtualizaMemos();
end;

procedure TForm1.AchaCNFporDescrio1Click(Sender: TObject);
var
  CNF       : TACBrECFComprovanteNaoFiscal;
  Descricao : String;
begin
  ACBrECF1.LerTotaisComprovanteNaoFiscal ;

  if not InputQuery('Acha CNF por Descri��o',
                    'Entre com o descricao:', Descricao ) then
    Exit;

  CNF  :=  ACBrECF1.AchaCNFDescricao(Descricao);

  if CNF <> Nil then
  begin
    mResp.Lines.Add('Indice   : ' + CNF.Indice);
    mResp.Lines.Add('CON      : ' + IntToStrZero(CNF.Contador, 4));
    mResp.Lines.Add('Descri��o: ' + CNF.Descricao);
    mResp.Lines.Add('Valor atual do totalizador R$ ' + FormatFloat('###,##0.00',CNF.Total));
  end
  else
    mResp.Lines.Add('CNF (' + Descricao + ') n�o encontrado!');

  AtualizaMemos();
end;

procedure TForm1.AchaRGporIndice1Click(Sender: TObject);
var
  RG      : TACBrECFRelatorioGerencial;
  Indice  : String;
begin
  ACBrECF1.CarregaRelatoriosGerenciais ;

  if not InputQuery('Acha Relat�rio Gerencial por Indice',
                    'Entre com o Indice:', Indice ) then
    Exit;

  RG  :=  ACBrECF1.AchaRGIndice(Indice);

  if RG <> Nil then
  begin
    mResp.Lines.Add('Indice   : ' + RG.Indice);
    mResp.Lines.Add('Descri��o: ' + RG.Descricao);
    mResp.Lines.Add('CER:     : ' + FormatFloat('0000',RG.Contador));
  end
  else
    mResp.Lines.Add('Indice (' + Indice + ') n�o encontrado!');

  AtualizaMemos(); 
end;

procedure TForm1.AchaRGporDescrio1Click(Sender: TObject);
var
  RG       : TACBrECFRelatorioGerencial;
  Descricao: String;
begin
  ACBrECF1.CarregaRelatoriosGerenciais ;

  if not InputQuery('Acha Relat�rio Gerencial por Indice',
                    'Entre com o Indice:', Descricao ) then
    Exit;

  RG  :=  ACBrECF1.AchaRGDescricao(Descricao);

  if RG <> Nil then
  begin
    mResp.Lines.Add('Indice   : ' + RG.Indice);
    mResp.Lines.Add('Descri��o: ' + RG.Descricao);
    mResp.Lines.Add('CER:     : ' + FormatFloat('0000',RG.Contador));
  end
  else
    mResp.Lines.Add('Relat�rio Gerencial (' + Descricao + ') n�o encontrado!');

  AtualizaMemos();
end;

procedure TForm1.PorCOO2Click(Sender: TObject);
Var Linhas : TStringList ;
    cCOO   : String ;
    I, nCOO: Integer ;
begin
  cCOO  := '0' ;

  if not InputQuery('Captura da MFD',
                'Entre com o COO que deseja capturar:', cCOO ) then
     exit ;

  nCOO  := StrToIntDef(cCOO,-1) ;

  if nCOO < 0 then exit ;

  Linhas := TStringList.Create ;
  try
     ACBrECF1.LeituraMFDSerial(nCOO, nCOO, Linhas);

     For I := 0 to Linhas.Count - 1 do
        mResp.Lines.Add(Linhas[I]) ;
  finally
     Linhas.Free ;
  end ;
  mResp.Lines.Add('---------------------------------');
end;

procedure TForm1.PorDatadeMovimento1Click(Sender: TObject);
Var Linhas : TStringList ;
    cData  : String ;
    dData  : TDateTime ;
    I : Integer ;
begin
  cData := FormatDateTime('dd/mm/yy',now) ;

  if not InputQuery('Captura da MFD',
                'Entre com o a Data do Movimento (DD/MM/AA):', cData ) then
     exit ;
  try
     dData  := StrToDateTime( StringReplace(cData,'/', DateSeparator,
                                [rfReplaceAll] ) ) ;
  except
     exit ;
  end ;

  Linhas := TStringList.Create ;
  try
     ACBrECF1.LeituraMFDSerial(dData, dData, Linhas);

     For I := 0 to Linhas.Count - 1 do
        mResp.Lines.Add(Linhas[I]) ;
  finally
     Linhas.Free ;
  end ;
  mResp.Lines.Add('---------------------------------');

end;

procedure TForm1.PorCOO3Click(Sender: TObject);
Var
  Arquivo: String ;
  cCOOIni, cCOOFim : String ;
  nCOOIni, nCOOFim : Integer ;
begin
  Arquivo := 'c:\temp\teste.txt' ;
  if not InputQuery('Espelho da MFD DLL',
                    'Nome Arquivo:', Arquivo ) then
     exit ;

  cCOOIni := '0' ;
  cCOOFim := '0' ;

  if not InputQuery('Espelho da MFD DLL',
                'Entre com o COO Inicial:', cCOOIni ) then
     exit ;
  nCOOIni := StrToIntDef(cCOOIni,-1) ;
  if nCOOIni < 0 then exit ;

  if not InputQuery('Espelho da MFD DLL',
                'Entre com o COO Final:', cCOOFim ) then
     exit ;
  nCOOFim := StrToIntDef(cCOOFim,-1) ;
  if nCOOFim < 0 then exit ;

  ACBrECF1.EspelhoMFD_DLL(nCOOIni, nCOOFim, Arquivo);
  mResp.Lines.Add('---------------------------------');
end;

procedure TForm1.PorPeriodo2Click(Sender: TObject);
Var
  Arquivo: String ;
  cDatIni, cDatFim : String ;
  dDatIni, dDatFim : TDateTime ;
begin
  Arquivo := 'c:\temp\teste.txt' ;
  if not InputQuery('Espelho da MFD DLL',
                    'Nome Arquivo:', Arquivo ) then
     exit ;

  cDatIni := '01/'+FormatDateTime('mm/yy',now) ;
  cDatFim := FormatDateTime('dd/mm/yy',now) ;

  if not InputQuery('Espelho da MFD DLL',
                'Entre com o a Data Inicial (DD/MM/AA):', cDatIni ) then
     exit ;
  try
     dDatIni := StrToDateTime( StringReplace(cDatIni,'/', DateSeparator,
                                [rfReplaceAll] ) ) ;
  except
     exit ;
  end ;

  if not InputQuery('Captura da MFD',
                'Entre com o a Data Final (DD/MM/AA):', cDatFim ) then
     exit ;
  try
     dDatFim := StrToDateTime( StringReplace(cDatFim,'/', DateSeparator,
                                [rfReplaceAll] ) ) ;
  except
     exit
  end ;

  ACBrECF1.EspelhoMFD_DLL(dDatIni, dDatFim, Arquivo);
  mResp.Lines.Add('---------------------------------');

end;

procedure TForm1.UsuarioAual1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'UsuarioAtual: ('+ ACBrECF1.UsuarioAtual+')' );
  AtualizaMemos ;
end;

procedure TForm1.Modelo1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'Modelo: ('+ ACBrECF1.ModeloStr+')' );
  AtualizaMemos ;
end;

procedure TForm1.PorCOO4Click(Sender: TObject);
Var
  Arquivo: String ;
  cCOOIni, cCOOFim : String ;
  nCOOIni, nCOOFim : Integer ;
begin
  Arquivo := 'c:\temp\teste.txt' ;
  if not InputQuery('Arquivo da MFD DLL',
                    'Nome Arquivo:', Arquivo ) then
     exit ;

  cCOOIni := '0' ;
  cCOOFim := '0' ;

  if not InputQuery('Arquivo da MFD DLL',
                'Entre com o COO Inicial:', cCOOIni ) then
     exit ;
  nCOOIni := StrToIntDef(cCOOIni,-1) ;
  if nCOOIni < 0 then exit ;

  if not InputQuery('Arquivo da MFD DLL',
                'Entre com o COO Final:', cCOOFim ) then
     exit ;
  nCOOFim := StrToIntDef(cCOOFim,-1) ;
  if nCOOFim < 0 then exit ;

  ACBrECF1.ArquivoMFD_DLL(nCOOIni, nCOOFim, Arquivo);
  mResp.Lines.Add('---------------------------------');
end;

procedure TForm1.PorPeriodo3Click(Sender: TObject);
Var
  Arquivo: String ;
  cDatIni, cDatFim : String ;
  dDatIni, dDatFim : TDateTime ;
begin
  Arquivo := 'c:\temp\teste.txt' ;
  if not InputQuery('Arquivo da MFD DLL',
                    'Nome Arquivo:', Arquivo ) then
     exit ;

  cDatIni := '01/'+FormatDateTime('mm/yy',now) ;
  cDatFim := FormatDateTime('dd/mm/yy',now) ;

  if not InputQuery('Arquivo da MFD DLL',
                'Entre com o a Data Inicial (DD/MM/AA):', cDatIni ) then
     exit ;
  try
     dDatIni := StrToDateTime( StringReplace(cDatIni,'/', DateSeparator,
                                [rfReplaceAll] ) ) ;
  except
     exit ;
  end ;

  if not InputQuery('Arquivo da MFD',
                'Entre com o a Data Final (DD/MM/AA):', cDatFim ) then
     exit ;
  try
     dDatFim := StrToDateTime( StringReplace(cDatFim,'/', DateSeparator,
                                [rfReplaceAll] ) ) ;
  except
     exit
  end ;

  ACBrECF1.ArquivoMFD_DLL(dDatIni, dDatFim, Arquivo);
  mResp.Lines.Add('---------------------------------');

end;

procedure TForm1.NumGNF1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'Num.GNF: ('+ ACBrECF1.NumGNF +')' );
  AtualizaMemos ;
end;

procedure TForm1.speLinBufChange(Sender: TObject);
begin
  ACBrECF1.MaxLinhasBuffer := speLinBuf.Value ;
end;

procedure TForm1.NumSerieMFDClick(Sender: TObject);
begin
  mResp.Lines.Add( 'N.S�rie MFD: ('+ ACBrECF1.NumSerieMFD+')' );
  AtualizaMemos ;
end;

procedure TForm1.mModeloStrClick(Sender: TObject);
begin
  mResp.Lines.Add( 'ModeloStr: ('+ ACBrECF1.ModeloStr+')' );
  AtualizaMemos ;
end;

procedure TForm1.btnIdentificaPafECFClick(Sender: TObject);
begin
  ACBrECF1.IdentificaPAF('Demo ACBrECF', 'AXAXAXAXAXAXAXAXAXAXAXAXAXAXAXAX');
  ShowMessage('Identifica��o feita com sucesso.');
end;

procedure TForm1.btnMenuFiscalConfigPAFECFClick(Sender: TObject);
var
  Parametros: TACBrECFInfoPaf;
begin
  if Assigned(ACBrECF1.AAC) then
    ACBrECF1.PafMF_RelParametrosConfiguracao(ACBrECF1.AAC.IdentPAF.Paf)
  else
  begin
    Parametros := TACBrECFInfoPaf.Create;
    try
      Parametros.TipoFuncionamento   := tpfEmRede;
      Parametros.TipoDesenvolvimento := tpdExclusivoTerceirizado;
      Parametros.IntegracaoPAFECF    := tpiRetaguarda;

      Parametros.RealizaPreVenda              := True;
      Parametros.RealizaDAVECF                := True;
      Parametros.RealizaDAVNaoFiscal          := True;
      Parametros.RealizaDAVOS                 := True;
      Parametros.DAVConfAnexoII               := True;
      Parametros.RealizaLancamentoMesa        := True;
      Parametros.IndiceTecnicoProd            := True;
      Parametros.BarSimilarECFRestaurante     := True;
      Parametros.BarSimilarECFComum           := True;
      Parametros.BarSimilarBalanca            := True;
      Parametros.UsaImpressoraNaoFiscal       := True;
      Parametros.DAVDiscrFormula              := True;
      Parametros.ImpedeVendaVlrZero           := True;
      Parametros.AcumulaVolumeDiario          := True;
      Parametros.ArmazenaEncerranteIniFinal   := True;
      Parametros.EmiteContrEncerrAposREDZLEIX := True;
      Parametros.IntegradoComBombas           := True;
      Parametros.CriaAbastDivergEncerrante    := True;
      Parametros.CadastroPlacaBomba           := True;
      Parametros.TransportePassageiro         := True;
      Parametros.TotalizaValoresLista         := True;
      Parametros.TransfPreVenda               := True;
      Parametros.TransfDAV                    := True;
      Parametros.RecompoeGT                   := True;
      Parametros.EmitePED                     := True;
      Parametros.CupomMania                   := True;
      Parametros.MinasLegal                   := True;
      Parametros.NotaLegalDF                  := True;
      Parametros.ParaibaLegal                 := True;
      Parametros.TrocoEmCartao                := True;

      ACBrECF1.PafMF_RelParametrosConfiguracao(Parametros);
    finally
      Parametros.Free;
    end;
  end;
end;

procedure TForm1.btnMenuFiscalLMFCClick(Sender: TObject);
var
  PathArquivo: string;
begin
  if chkMenuFiscalGerarArquivo.Checked then
  begin
    if dlgDialogoSalvar.Execute then
    begin
      PathArquivo := dlgDialogoSalvar.FileName;

      if chkMenuFiscalCotepe1704.Checked then
      begin
        if pgcMenuFiscalTipo.ActivePageIndex = 0 then
          ACBrECF1.PafMF_LMFC_Cotepe1704(edtDtInicial.Date, edtDtFinal.Date, PathArquivo)
        else
          ACBrECF1.PafMF_LMFC_Cotepe1704(edtCOOInicial.Value, edtCOOFinal.Value, PathArquivo);
      end
      else
      begin
        if pgcMenuFiscalTipo.ActivePageIndex = 0 then
          ACBrECF1.PafMF_LMFC_Espelho(edtDtInicial.Date, edtDtFinal.Date, PathArquivo)
        else
          ACBrECF1.PafMF_LMFC_Espelho(edtCOOInicial.Value, edtCOOFinal.Value, PathArquivo);
      end;

      ShowMessage(Format('Arquivo gerado com sucesso em:'#13#10' "%s"', [PathArquivo]));
    end;
  end
  else
  begin
    if pgcMenuFiscalTipo.ActivePageIndex = 0 then
      ACBrECF1.PafMF_LMFC_Impressao(edtDtInicial.Date, edtDtFinal.Date)
    else
      ACBrECF1.PafMF_LMFC_Impressao(edtCOOInicial.Value, edtCOOFinal.Value);
  end;
end;

procedure TForm1.btnMenuFiscalLMFSClick(Sender: TObject);
var
  PathArquivo: string;
begin
  if chkMenuFiscalGerarArquivo.Checked then
  begin
    if dlgDialogoSalvar.Execute then
    begin
      PathArquivo := dlgDialogoSalvar.FileName;
      if pgcMenuFiscalTipo.ActivePageIndex = 0 then
        ACBrECF1.PafMF_LMFS_Espelho(edtDtInicial.Date, edtDtFinal.Date, PathArquivo)
      else
        ACBrECF1.PafMF_LMFS_Espelho(edtCOOInicial.Value, edtCOOFinal.Value, PathArquivo);

      ShowMessage(Format('Arquivo gerado com sucesso em:'#13#10' "%s"', [PathArquivo]));
    end;
  end
  else
  begin
    if pgcMenuFiscalTipo.ActivePageIndex = 0 then
      ACBrECF1.PafMF_LMFS_Impressao(edtDtInicial.Date, edtDtFinal.Date)
    else
      ACBrECF1.PafMF_LMFS_Impressao(edtCOOInicial.Value, edtCOOFinal.Value);
  end;
end;

procedure TForm1.btnMenuFiscalLXClick(Sender: TObject);
begin
  ACBrECF1.PafMF_LX_Impressao;
end;

procedure TForm1.btnMenuFiscalMFDArqClick(Sender: TObject);
var
  PathArquivo: string;
begin
  if dlgDialogoSalvar.Execute then
  begin
    PathArquivo := dlgDialogoSalvar.FileName;

    if pgcMenuFiscalTipo.ActivePageIndex = 0 then
      ACBrECF1.PafMF_MFD_Cotepe1704(edtDtInicial.Date, edtDtFinal.Date, PathArquivo)
    else
      ACBrECF1.PafMF_MFD_Cotepe1704(edtCOOInicial.Value, edtCOOFinal.Value, PathArquivo);

    ShowMessage(Format('Arquivo gerado com sucesso em:'#13#10' "%s"', [PathArquivo]));
  end;
end;

procedure TForm1.btnMenuFiscalMFDEspelhoClick(Sender: TObject);
var
  PathArquivo: string;
begin
  if dlgDialogoSalvar.Execute then
  begin
    PathArquivo := dlgDialogoSalvar.FileName;

    if pgcMenuFiscalTipo.ActivePageIndex = 0 then
      ACBrECF1.PafMF_MFD_Espelho(edtDtInicial.Date, edtDtFinal.Date, PathArquivo)
    else
      ACBrECF1.PafMF_MFD_Espelho(edtCOOInicial.Value, edtCOOFinal.Value, PathArquivo);

    ShowMessage(Format('Arquivo gerado com sucesso em:'#13#10' "%s"', [PathArquivo]));
  end;
end;

procedure TForm1.btnMenuFiscalRelDAVEmitidosClick(Sender: TObject);
var
  DAVs: TACBrECFDAVs;
  I: Integer;
const
  TipoDAV: array[0..1] of string = ('PEDIDO', 'ORCAMENTO');
  Valores: array[0..3] of Double = (1.00, 2.00, 3.50, 10.45);
  Datas:   array[0..4] of string = ('30/12/2000', '01/01/2011', '25/02/2010', '04/02/2011', '13/04/2011');
begin
  DAVs := TACBrECFDAVs.Create;
  try
    for I := 1 to 25 do
    begin
      with DAVs.New do
      begin
        Numero    := Format('%10.10d', [I]);
        COO_Dav   := RandomRange(0, 999999);
        COO_Cupom := RandomRange(0, 999999);
        Titulo    := RandomFrom(TipoDAV);
        DtEmissao := StrToDate(RandomFrom(Datas));
        Valor     := RandomFrom(Valores)
      end;
    end;

    ACBrECF1.PafMF_RelDAVEmitidos(DAVs, 'REFERENCIA: 00/00/0000 A 00/00/0000', 0);
  finally
    DAVs.Free;
  end;
end;

procedure TForm1.btnMenuFiscalRelIdentPAFECFClick(Sender: TObject);
var
  IdentPaf: TACBrECFIdentificacaoPAF;
  I: Integer;
begin
  // Se est� usando o AAC, basta informar o Objeto IdentPAF //
  // Se NAO est� usando o AAC, o Objeto IdentPAF deve ser inst�nciado e populado //
  if Assigned( ACBrECF1.AAC ) then
    ACBrECF1.PafMF_RelIdentificacaoPafECF( ACBrECF1.AAC.IdentPAF, 0)
  else
  begin
    IdentPaf := TACBrECFIdentificacaoPAF.Create;
    try
      IdentPaf.NumeroLaudo := 'ABC1234567890'; // retirar do laudo
      IdentPaf.VersaoER    := '01.06'; // retirar do laudo

      // preencher dados da empresa conforme o que foi informado no laudo de an�lise
      IdentPaf.Empresa.RazaoSocial := 'Razao social Empresa';
      IdentPaf.Empresa.CNPJ        := '01.222.333/00001-99';
      IdentPaf.Empresa.Endereco    := 'Rua da Felicidade, 1';
      IdentPaf.Empresa.Cidade      := 'SAO PAULO';
      IdentPaf.Empresa.Uf          := 'SP';
      IdentPaf.Empresa.Cep         := '99.999-999';
      IdentPaf.Empresa.Telefone    := '(99)1111.2222';
      IdentPaf.Empresa.Contato     := 'Nome do Contato';

      IdentPaf.Paf.Nome              := 'DemoECF';// preencher conforme o laudo
      IdentPaf.Paf.Versao            := 'v01.01.01'; // vers�o atual do aplicativo
      IdentPaf.Paf.PrincipalExe.Nome := UpperCase(ExtractFileName(ParamStr(0)));
      IdentPaf.Paf.PrincipalExe.MD5  := StringOfChar('X', 32); // md5 atual do aplicativo

      IdentPaf.ArquivoListaAutenticados.Nome := 'lista_arquivos.txt'; // nome do arquivo contendo a lista de autenticados
      IdentPaf.ArquivoListaAutenticados.MD5  := 'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA'; // md5 do arquivo, mesmo que vai impresso nos cupons

      // adicionar os arquivos adicionados ao arquivo da lista de autenticados
      for I := 1 to 5 do
      begin
        with IdentPaf.OutrosArquivos.New do
        begin
          Nome := Format('Arquivo %3.3d', [I]);
          MD5  := StringOfChar('X', 32);
        end;
      end;

      // ecfs autorizados para funcionamento na m�quina
      IdentPaf.ECFsAutorizados.clear;
      for I := 1 to 3 do
      begin
        with IdentPaf.ECFsAutorizados.New do
          NumeroSerie := StringOfChar('A', 15) ;
      end;

      ACBrECF1.PafMF_RelIdentificacaoPafECF(IdentPaf, 0);
    finally
      IdentPaf.Free;
    end;
  end;
end;

procedure TForm1.btnMenuFiscalRelMeiosPagtoClick(Sender: TObject);
var
  FormasPagamento: TACBrECFFormasPagamento;
  I: Integer;
const
  arrayTipoDoc: array[0..2] of String = ('Cupom Fiscal', 'Compr. N�o Fiscal', 'Nota Fiscal');
  arrayDescrFormaPagto: array[0..3] of string = ('Dinheiro', 'Cheque', 'Cart�o Cr�dito', 'Cart�o D�bito');
  arrayDataLancamento: array[0..4] of String = ('01/01/2010', '31/12/2010', '04/05/2011', '02/01/2010', '03/05/2011');
  arrayValores: array[0..4] of Double = (10.56, 14.23, 0.00, 12.00, 1.20);
begin
  FormasPagamento := TACBrECFFormasPagamento.Create;
  try
    for I := 1 to 25 do
    begin
      with FormasPagamento.New do
      begin
        Descricao := RandomFrom(arrayDescrFormaPagto);
        Data      := StringToDateTime(RandomFrom(arrayDataLancamento), 'dd/mm/yyyy');
        Total     := RandomFrom(arrayValores);
        TipoDoc   := RandomFrom(arrayTipoDoc);
      end;
    end;

    ACBrECF1.PafMF_RelMeiosPagamento(
      FormasPagamento,
      'PERIODO DE 01/01/2000 A 31/12/2000',
      0
    );
  finally
    FormasPagamento.Free;
  end;
end;

procedure TForm1.NumerodeReduesZrestantes1Click(Sender: TObject);
begin
  mResp.Lines.Add( 'N�mero de Redu��es Z restantes: ('+ ACBrECF1.NumReducoesZRestantes +')' );
  AtualizaMemos ;
end;

procedure TForm1.btnMenuFiscalNotaPaulistaClick(Sender: TObject);
var
  DirArquivos: string;
begin
  DirArquivos := ExtractFilePath(ParamStr(0)) + 'CAT52';
  if not DirectoryExists(DirArquivos) then
    ForceDirectories(DirArquivos);

  ACBrECF1.PafMF_GerarCAT52(edtDtInicial.Date, edtDtFinal.Date, DirArquivos);

  ShowMessage(Format('Arquivos gerados com sucesso em:'#13#10' "%s"', [DirArquivos]));
end;

procedure TForm1.chArredondamentoItemMFDClick(Sender: TObject);
begin
  ACBrECF1.ArredondaItemMFD := chArredondamentoItemMFD.Checked ;
end;

procedure TForm1.chBarrasImprimeTextoClick(Sender: TObject);
begin
  ACBrECF1.ConfigBarras.MostrarCodigo := chBarrasImprimeTexto.Checked;
end;

procedure TForm1.chIgnorarTagsFormatacaoClick(Sender: TObject);
begin
  ACBrECF1.IgnorarTagsFormatacao := chIgnorarTagsFormatacao.Checked;
end;

procedure TForm1.BitBtn6Click(Sender: TObject);
begin
  if ACBrECF1.Estado <> estRelatorio then
  begin
    ACBrECF1.CorrigeEstadoErro ;
    ACBrECF1.AbreRelatorioGerencial;
  end ;

  ACBrECF1.ConfigBarras.LarguraLinha  := speBarrasLargura.Value;
  ACBrECF1.ConfigBarras.Altura        := speBarrasAltura.Value;
  ACBrECF1.ConfigBarras.MostrarCodigo := chBarrasImprimeTexto.Checked;
  ACBrECF1.IgnorarTagsFormatacao      := chIgnorarTagsFormatacao.Checked;

  ACBrECF1.LinhaRelatorioGerencial( MemoTesteTags.Text );
end;

procedure TForm1.speBarrasLarguraChange(Sender: TObject);
begin
  ACBrECF1.ConfigBarras.LarguraLinha  := speBarrasLargura.Value;
end;

procedure TForm1.speBarrasAlturaChange(Sender: TObject);
begin
  ACBrECF1.ConfigBarras.Altura := speBarrasAltura.Value;
end;

procedure TForm1.chbCupomManiaClick(Sender: TObject);
begin
  ACBrECF1.InfoRodapeCupom.CupomMania := chbCupomMania.Checked;
end;

end.


unit UMov0025;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, BrvListParam, ImgList, Menus, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop,
  StdCtrls, BrvBitBtn, Grids, BrvDbGrids, BrvDbGrid, DB, DBClient, BrvClientDataSet, ComCtrls,
  BrvRetCon, BrvLabel, Mask, DBCtrls, BrvDbEdit, BrvDBComboListBox, BrvEdit, BrvEditNum, BrvString,
  BrvDigito, BrvComboBox, BrvXml, BrvServerProgress, BrvCustomEdit;

type
  TMov0025 = class(TMov0000)
    CpF013: TBrvClientDataSet;
    DsF013: TDataSource;
    PgcNF: TPageControl;
    TbsPreLan: TTabSheet;
    TbsNota: TTabSheet;
    DbgF013: TBrvDbGrid;
    PnlControl: TPanel;
    Label1: TLabel;
    BtnManual: TBrvBitBtn;
    BtnLancar: TBrvBitBtn;
    Panel1: TPanel;
    BrvDbEdit1: TBrvDbEdit;
    BrvLabel1: TBrvLabel;
    EdtDsEmpres: TBrvRetCon;
    BrvDbEdit2: TBrvDbEdit;
    BrvLabel3: TBrvLabel;
    BrvDbEdit3: TBrvDbEdit;
    EdtRsTitula: TBrvRetCon;
    BrvDbEdit4: TBrvDbEdit;
    BrvDbEdit5: TBrvDbEdit;
    BrvLabel4: TBrvLabel;
    BrvLabel5: TBrvLabel;
    BrvLabel6: TBrvLabel;
    BrvLabel7: TBrvLabel;
    BrvDbEdit7: TBrvDbEdit;
    EdtDsEvento: TBrvRetCon;
    PgcDadosNF: TPageControl;
    BrvDbEdit8: TBrvDbEdit;
    BrvDbEdit10: TBrvDbEdit;
    BrvLabel8: TBrvLabel;
    BrvLabel10: TBrvLabel;
    TbsDadAdi: TTabSheet;
    DBMemo1: TDBMemo;
    BrvLabel11: TBrvLabel;
    TbsImpostos: TTabSheet;
    Panel3: TPanel;
    GroupBox1: TGroupBox;
    BrvLabel27: TBrvLabel;
    BrvDbEdit6: TBrvDbEdit;
    BrvLabel2: TBrvLabel;
    BrvDbEdit25: TBrvDbEdit;
    BrvLabel28: TBrvLabel;
    BrvLabel29: TBrvLabel;
    TbsRetencao: TTabSheet;
    CcF001: TBrvClientDataSet;
    DsF001: TDataSource;
    BtnSalvar: TBrvBitBtn;
    BrvDbEdit41: TBrvDbEdit;
    CcF002: TBrvClientDataSet;
    DsF002: TDataSource;
    BrvDbEdit43: TBrvDbEdit;
    BrvLabel50: TBrvLabel;
    TbsContabil: TTabSheet;
    TbsParcela: TTabSheet;
    DbgN003: TBrvDbGrid;
    CcN003: TBrvClientDataSet;
    DsN003: TDataSource;
    CcB004: TBrvClientDataSet;
    DsB004: TDataSource;
    DbgB004: TBrvDbGrid;
    GroupBox2: TGroupBox;
    CbxSnItens: TCheckBox;
    CbxSnConhec: TCheckBox;
    CbxSnCreImp: TCheckBox;
    BtnCancelar: TBrvBitBtn;
    TbsItens: TTabSheet;
    DbgF002: TBrvDbGrid;
    GroupBox3: TGroupBox;
    BrvLabel12: TBrvLabel;
    BrvDbEdit11: TBrvDbEdit;
    BrvDbEdit12: TBrvDbEdit;
    BrvLabel13: TBrvLabel;
    BrvLabel14: TBrvLabel;
    BrvDbEdit13: TBrvDbEdit;
    GroupBox4: TGroupBox;
    BrvLabel15: TBrvLabel;
    BrvDbEdit14: TBrvDbEdit;
    BrvDbEdit15: TBrvDbEdit;
    BrvLabel16: TBrvLabel;
    GroupBox5: TGroupBox;
    BrvLabel18: TBrvLabel;
    BrvDbEdit17: TBrvDbEdit;
    GroupBox6: TGroupBox;
    BrvLabel17: TBrvLabel;
    BrvDbEdit16: TBrvDbEdit;
    BrvDbEdit19: TBrvDbEdit;
    BrvLabel20: TBrvLabel;
    BrvLabel21: TBrvLabel;
    BrvDbEdit20: TBrvDbEdit;
    DBCheckBox1: TDBCheckBox;
    BrvLabel19: TBrvLabel;
    BrvDbEdit18: TBrvDbEdit;
    GrbTransp: TGroupBox;
    BrvLabel22: TBrvLabel;
    BrvDBComboListBox1: TBrvDBComboListBox;
    DBCheckBox2: TDBCheckBox;
    DBCheckBox3: TDBCheckBox;
    BrvLabel9: TBrvLabel;
    BrvDbEdit9: TBrvDbEdit;
    EdtRsTransp: TBrvRetCon;
    BrvLabel23: TBrvLabel;
    BrvDbEdit21: TBrvDbEdit;
    GroupBox8: TGroupBox;
    BrvLabel30: TBrvLabel;
    BrvLabel32: TBrvLabel;
    BrvLabel35: TBrvLabel;
    BrvDbEdit28: TBrvDbEdit;
    BrvDbEdit27: TBrvDbEdit;
    BrvDbEdit26: TBrvDbEdit;
    GroupBox9: TGroupBox;
    BrvLabel33: TBrvLabel;
    BrvDbEdit29: TBrvDbEdit;
    BrvDbEdit30: TBrvDbEdit;
    BrvLabel36: TBrvLabel;
    BrvLabel37: TBrvLabel;
    BrvDbEdit31: TBrvDbEdit;
    GroupBox10: TGroupBox;
    BrvLabel31: TBrvLabel;
    BrvLabel34: TBrvLabel;
    BrvLabel38: TBrvLabel;
    BrvDbEdit32: TBrvDbEdit;
    BrvDbEdit33: TBrvDbEdit;
    BrvDbEdit34: TBrvDbEdit;
    GroupBox11: TGroupBox;
    GroupBox12: TGroupBox;
    BrvDbEdit35: TBrvDbEdit;
    BrvDbEdit37: TBrvDbEdit;
    BrvDbEdit36: TBrvDbEdit;
    BrvDbEdit38: TBrvDbEdit;
    BrvDbEdit39: TBrvDbEdit;
    BrvDbEdit40: TBrvDbEdit;
    BrvLabel39: TBrvLabel;
    BrvLabel40: TBrvLabel;
    BrvLabel41: TBrvLabel;
    BrvLabel42: TBrvLabel;
    BrvLabel43: TBrvLabel;
    BrvLabel44: TBrvLabel;
    GroupBox13: TGroupBox;
    BrvDbEdit22: TBrvDbEdit;
    BrvDbEdit23: TBrvDbEdit;
    BrvDbEdit24: TBrvDbEdit;
    BrvLabel45: TBrvLabel;
    BrvLabel46: TBrvLabel;
    BrvLabel47: TBrvLabel;
    GroupBox14: TGroupBox;
    CbxPIS: TCheckBox;
    CbxCOFINS: TCheckBox;
    CbxCSLL: TCheckBox;
    CbxIRRF: TCheckBox;
    CbxISSQN: TCheckBox;
    CbxINSS: TCheckBox;
    DbgB008: TBrvDbGrid;
    CcB008: TBrvClientDataSet;
    DsB008: TDataSource;
    EdtTtProdut: TBrvEditNum;
    EdtStGerDup: TBrvRetCon;
    BrvDigito: TBrvDigito;
    CcN002: TBrvClientDataSet;
    EdtCdForPag: TBrvRetCon;
    EdtNrChave: TBrvEdit;
    BrvString: TBrvString;
    BrvDbEdit42: TBrvDbEdit;
    EdtCdEstEmp: TBrvRetCon;
    BrvLabel24: TBrvLabel;
    CbxFinali: TBrvComboBox;
    EdtCdEstEmi: TBrvRetCon;
    EdtCdEmpEst: TBrvRetCon;
    EdtDsFiscal: TBrvRetCon;
    EdtCdFiscal: TBrvRetCon;
    EdtCdGruEmp: TBrvRetCon;
    CpNfeDet: TBrvClientDataSet;
    CpNFePro: TBrvClientDataSet;
    CpNFeFat: TBrvClientDataSet;
    BrvXMLNFE: TBrvXML;
    QpXml: TBrvClientDataSet;
    BtnAtualizar: TBrvBitBtn;
    Bevel1: TBevel;
    Bevel2: TBevel;
    CpF014: TBrvClientDataSet;
    CpF015: TBrvClientDataSet;
    CpF017: TBrvClientDataSet;
    CpB012: TBrvClientDataSet;
    CcB002: TBrvClientDataSet;
    Panel2: TPanel;
    BtnGerParcel: TBrvBitBtn;
    BtnCancelParcel: TBrvBitBtn;
    CcB004FinNot: TBrvClientDataSet;
    CcB008FinNot: TBrvClientDataSet;
    BrvServerProgress: TBrvServerProgress;
    CpS004: TBrvClientDataSet;
    procedure FormCreate(Sender: TObject);
    procedure BtnManualClick(Sender: TObject);
    procedure BtnLancarClick(Sender: TObject);
    procedure BtnSalvarClick(Sender: TObject);
    procedure BrvDbEdit1BrOnBeforeConsulta(Sender: TObject);
    procedure DbgF014Columns0BrOnAfterConsul(Sender: TObject);
    procedure DbgF014Columns2BrOnAfterConsul(Sender: TObject);
    procedure DbgF014Columns2BrOnBeforeConsul(Sender: TObject);
    procedure DbgF014Columns4BrOnAfterConsul(Sender: TObject);
    procedure CbxSnItensClick(Sender: TObject);
    procedure CcB008AfterInsert(DataSet: TDataSet);
    procedure CcB004AfterScroll(DataSet: TDataSet);
    procedure DbgB004Columns4BrOnBeforeConsul(Sender: TObject);
    procedure CbxPISClick(Sender: TObject);
    procedure CbxCOFINSClick(Sender: TObject);
    procedure CbxCSLLClick(Sender: TObject);
    procedure CbxIRRFClick(Sender: TObject);
    procedure CbxINSSClick(Sender: TObject);
    procedure CbxISSQNClick(Sender: TObject);
    procedure CcF002AfterPost(DataSet: TDataSet);
    procedure CcF002BeforePost(DataSet: TDataSet);
    procedure EdtNrChaveExit(Sender: TObject);
    procedure CbxSnConhecClick(Sender: TObject);
    procedure BtnCancelarClick(Sender: TObject);
    procedure BtnAtualizarClick(Sender: TObject);
    procedure CpF014AfterScroll(DataSet: TDataSet);
    procedure BrvDbEdit27Exit(Sender: TObject);
    procedure BrvDbEdit30Exit(Sender: TObject);
    procedure BrvDbEdit33Exit(Sender: TObject);
    procedure BrvDbEdit39Exit(Sender: TObject);
    procedure BrvDbEdit37Exit(Sender: TObject);
    procedure BrvDbEdit23Exit(Sender: TObject);
    procedure DbgF013DblClick(Sender: TObject);
    procedure DbgB004Columns4BrOnAfterConsul(Sender: TObject);
    procedure CcB004AfterInsert(DataSet: TDataSet);
    procedure CcB008BeforePost(DataSet: TDataSet);
    procedure BtnGerParcelClick(Sender: TObject);
    procedure BtnCancelParcelClick(Sender: TObject);
    procedure FormCloseQuery(Sender: TObject; var CanClose: Boolean);
    procedure FinalidadeEntradaValidate(Sender: TField);
    procedure FinalidadeGetText(Sender: TField; var Text: String; DisplayText: Boolean);
  private
    function ValidarNotaFiscal: String;
    function NotaValida: Boolean;
    function ContinuarValidandoProduto: Boolean;
    function TotalProduto: Currency;
    function TipoOperacao: String;
    function CalcularDataVenFixa(const pDtVenAnt: TDateTime;
                                                          pNrDiaFix, pQtDiaVen: Integer): TDateTime;
    function LancaDentroDoMes(pCdEmpres: Integer; pDtEntrad: TDateTime): Boolean;
    function CorrigeValor(pVrXml: String): Currency;
    function CarregarProduto(pCdEmbala: AnsiString; pCdFornec: Integer; pCdRefFab,
                             pCdNcm: AnsiString): Integer;
    function ProximoLanctoB004(pCdsB004: OleVariant): Integer;

    procedure GerarTabelasTemporarias;
    procedure ValidaCodOperacao;
    procedure CriarTabelaContasPagar;
    procedure FinalizarDigitacaoNota;
    procedure CarregarDadosChaveNFe;
    procedure CarregarDadosNota;
    procedure CarregarEmpresa;
    procedure CarregarDadosItens;
    procedure VerificaParametros;
    procedure ContabilizarRetencoes;

    procedure TotalizarItensNota(var pBsIcms, pVrIcms, pBsSubTri, pVrSubTri, pTtNota, pVrIpi: Real;
                                                                              var pResult: Boolean);
    procedure FreteIncideIpi(pSnFreIpi: String; pVrFrete, pVrTotal: Real; pCcF002: tDataSet;
                                                                                  var pVrIpi: Real);
    procedure CarregarParcelas(pCdEvento: String; pVrConNot: Real; pDtEmiNot: TDate);
    procedure CarregarParametrosEvento(pCdEvento: String;
                                       var pQtParcel, pNrPriPar, pNrIntPar: Integer;
                                       var pSnAcuPri: String; var pNrDiaFix: Integer);
    procedure CalcularDadosParcela(pNrParcel, pQtParcel, pNrDiaFix, pNrPriPar, pNrIntPar: Integer;
                 pVrDuplic: Real; pSnAcuPri: String; var pVrParcel: Real; var pDtParcel: TDateTime);

    procedure CalcularDadosDocumento(var pNrOrdem, pNoParcel: Integer; var pDtEmissa: TDate;
                                     var pDtParcel: TDateTime; var pDtDiaVen, pVrInterv,
                                         pVrInPrPa: Integer; var pVrConta, pVrDocume: Real;
                                     var pSnAcuPri: String);
    procedure VerificarCodigoBarra(pCdBarra, pNrDocto, pNrOrdem: String);
    procedure CalculaRetencao(pCheck: TCheckBox; pNrParam, pCdEmpres: Integer; pCampoBs, pCampoPc,
                                                                         pCampoVr, pDsAliq: String);
    procedure SomaTotalProdutos(pCdsItens: Variant);
    procedure TratarDadosCabecalho(pSnEnable: Boolean);
    procedure EncontrarCFOP(pCdEstado, pTpOpeFis, pTpFinEnt, pCdSitTri: AnsiString;
                            var pNrSeqFis, pCdOpeFis : Integer);

    procedure CalcularPisCofins(pCdGruEmp: Integer; pCdNcm, pTpFinEnt: AnsiString; pVrProdut: Real;
                                var pCdCstPis, pCdCstCof: AnsiString; var pPcAliPis, pPcAliCof,
                                pBsImpPis, pBsImpCof, pVrImpPis, pVrImpCof: Real);

    procedure LocalizarParametro(pNrParam: String);
    procedure ContabilizaRetencao(pDsCampo: String; pNrParam: string; var pNrLancto: Integer);
    { Private declarations }
  public
    gnrLancto : Integer;
    gDsComple : TStrings;
    { Public declarations }
  end;

var
  Mov0025: TMov0025;

implementation

uses UDmSrvApl, UClaSrv, UDmFis;

{$R *.dfm}

procedure TMov0025.GerarTabelasTemporarias;
begin
      if (CcF001.Active) then
      begin
            CcF001.EmptyDataSet;
            CcF001.Close;
      end;

      CcF001.FieldDefs.Clear;
      CcF001.FieldDefs.Add('PCPIS'   , ftFloat, 0);
      CcF001.FieldDefs.Add('VRPIS'   , ftFloat, 0);
      CcF001.FieldDefs.Add('BSPIS'   , ftFloat, 0);

      CcF001.FieldDefs.Add('PCCOFINS', ftFloat, 0);
      CcF001.FieldDefs.Add('VRCOFINS', ftFloat, 0);
      CcF001.FieldDefs.Add('BSCOFINS', ftFloat, 0);

      CcF001.FieldDefs.Add('PCCSLL'  , ftFloat, 0);
      CcF001.FieldDefs.Add('VRCSLL'  , ftFloat, 0);
      CcF001.FieldDefs.Add('BSCSLL'  , ftFloat, 0);

      CcF001.FieldDefs.Add('PCIRRF'  , ftFloat, 0);
      CcF001.FieldDefs.Add('VRIRRF'  , ftFloat, 0);
      CcF001.FieldDefs.Add('BSIRRF'  , ftFloat, 0);

      CcF001.FieldDefs.Add('PCALIINS', ftFloat, 0);
      CcF001.FieldDefs.Add('VRINSS'  , ftFloat, 0);
      CcF001.FieldDefs.Add('BSINSS'  , ftFloat, 0);

      CcF001.FieldDefs.Add('PCALIISS', ftFloat, 0);
      CcF001.FieldDefs.Add('VRISSQST', ftFloat, 0);
      CcF001.FieldDefs.Add('BSISSQN' , ftFloat, 0);

      CcF001.FieldDefs.Add('BSICMS'  , ftFloat, 0);
      CcF001.FieldDefs.Add('BSSUBTRI', ftFloat, 0);
      CcF001.FieldDefs.Add('PCALIICM', ftFloat, 0);

      CcF001.FieldDefs.Add('VRCONNOT', ftFloat, 0);
      CcF001.FieldDefs.Add('VRDESACE', ftFloat, 0);
      CcF001.FieldDefs.Add('VRFRETE' , ftFloat, 0);
      CcF001.FieldDefs.Add('VRICMS'  , ftFloat, 0);
      CcF001.FieldDefs.Add('VRIPI'   , ftFloat, 0);
      CcF001.FieldDefs.Add('VRISENOT', ftFloat, 0);
      CcF001.FieldDefs.Add('VROUTNOT', ftFloat, 0);
      CcF001.FieldDefs.Add('VRSEGURO', ftFloat, 0);
      CcF001.FieldDefs.Add('VRSUBTRI', ftFloat, 0);
      CcF001.FieldDefs.Add('NRNOTA'  , ftInteger, 0);
      CcF001.FieldDefs.Add('NRPRELAN', ftInteger, 0);
      CcF001.FieldDefs.Add('NRSEQDOC', ftInteger, 0);
      CcF001.FieldDefs.Add('CDEMPRES', ftInteger, 0);
      CcF001.FieldDefs.Add('CDEVENTO', ftInteger, 0);
      CcF001.FieldDefs.Add('CDTITULA', ftInteger, 0);
      CcF001.FieldDefs.Add('CDTRANSP', ftInteger, 0);

      CcF001.FieldDefs.Add('NRSEQFIS', ftInteger, 0);
      CcF001.FieldDefs.Add('CDFISCAL', ftInteger, 0);

      CcF001.FieldDefs.Add('DTEMINOT', ftDateTime,0);
      CcF001.FieldDefs.Add('DTENTRAD', ftDateTime,0);
      CcF001.FieldDefs.Add('DSMODENF', ftString,  3);
      CcF001.FieldDefs.Add('NRSERINF', ftString,  6);
      CcF001.FieldDefs.Add('NRSUSENF', ftString,  6);
      CcF001.FieldDefs.Add('SNDESPIC', ftString,  1);
      CcF001.FieldDefs.Add('SNFREICM', ftString,  1);
      CcF001.FieldDefs.Add('SNFREIPI', ftString,  1);
      CcF001.FieldDefs.Add('TPFRETE' , ftString,  1);
      CcF001.FieldDefs.Add('CJTITULA', ftString, 14);
      CcF001.FieldDefs.Add('NRCHADOC', ftString, 50);
      CcF001.FieldDefs.Add('CDESTEMI', ftString,  2);
      CcF001.FieldDefs.Add('STNOTA'  , ftString,  1);
      CcF001.FieldDefs.Add('TXDADADI', ftMemo  ,  0);
      CcF001.CreateDataSet;

      TDateField(CcF001.FieldByName('DTEMINOT')).EditMask := '!99/99/0000;1;_';
      TDateField(CcF001.FieldByName('DTENTRAD')).EditMask := '!99/99/0000;1;_';

      TNumericField(CcF001.FieldByName('PCPIS'   )).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('VRPIS'   )).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('BSPIS'   )).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('PCCOFINS')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('VRCOFINS')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('BSCOFINS')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('PCCSLL'  )).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('VRCSLL'  )).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('BSCSLL'  )).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('PCIRRF'  )).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('VRIRRF'  )).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('BSIRRF'  )).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('PCALIINS')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('VRINSS'  )).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('BSINSS'  )).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('PCALIISS')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('VRISSQST')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('BSISSQN' )).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('BSICMS'  )).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('BSSUBTRI')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('PCALIICM')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('VRCONNOT')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('VRDESACE')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('VRFRETE' )).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('VRICMS'  )).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('VRIPI'   )).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('VRISENOT')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('VROUTNOT')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('VRSEGURO')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF001.FieldByName('VRSUBTRI')).DisplayFormat := '#,##0.0000';

      if (CcF002.Active) then
      begin
            CcF002.EmptyDataSet;
            CcF002.Close;
      end;

      CcF002.FieldDefs.Clear;
      CcF002.FieldDefs.Add('NRSEQDOC', ftInteger, 0);
      CcF002.FieldDefs.Add('NRSEQUEN', ftInteger, 0);

      CcF002.FieldDefs.Add('CDPRODUT', ftInteger, 0);
      CcF002.FieldDefs.Add('DSPRODUT', ftString, 50);

      CcF002.FieldDefs.Add('NRSEQFIS', ftInteger, 0);
      CcF002.FieldDefs.Add('CDOPEFIS', ftInteger, 0);

      CcF002.FieldDefs.Add('QTPRODUT', ftFloat, 0);
      CcF002.FieldDefs.Add('VRUNITAR', ftFloat, 0);
      CcF002.FieldDefs.Add('PCDESCON', ftFloat, 0);
      CcF002.FieldDefs.Add('VRDESCON', ftFloat, 0);
      CcF002.FieldDefs.Add('VRTOTAL' , ftFloat, 0);
      CcF002.FieldDefs.Add('VRIPI'   , ftFloat, 0);
      CcF002.FieldDefs.Add('BSSUBTRI', ftFloat, 0);
      CcF002.FieldDefs.Add('VRSUBTRI', ftFloat, 0);
      CcF002.FieldDefs.Add('PCALIISS', ftFloat, 0);

      CcF002.FieldDefs.Add('BSICMS'  , ftFloat, 0);
      CcF002.FieldDefs.Add('PCALIICM', ftFloat, 0);
      CcF002.FieldDefs.Add('CDSITTRI', ftString,3);
      CcF002.FieldDefs.Add('VRICMS'  , ftFloat, 0);
      CcF002.FieldDefs.Add('PCDEBAIC', ftFloat, 0);

      CcF002.FieldDefs.Add('CDCSTCOF', ftFloat, 0);
      CcF002.FieldDefs.Add('BSIMPCOF', ftFloat, 0);
      CcF002.FieldDefs.Add('PCALICOF', ftFloat, 0);
      CcF002.FieldDefs.Add('VRIMPCOF', ftFloat, 0);

      CcF002.FieldDefs.Add('CDCSTPIS', ftString, 2);
      CcF002.FieldDefs.Add('BSIMPPIS', ftFloat, 0);
      CcF002.FieldDefs.Add('PCALIPIS', ftFloat, 0);
      CcF002.FieldDefs.Add('VRIMPPIS', ftFloat, 0);

      CcF002.FieldDefs.Add('VRISENTA', ftFloat, 0);
      CcF002.FieldDefs.Add('VROUTRA' , ftFloat, 0);

      CcF002.FieldDefs.Add('TPFINENT', ftString, 1);
      CcF002.FieldDefs.Add('CDNCM'   , ftString,15);

      //Chave do sistema antigo
      CcF002.FieldDefs.Add('CDTITULA', ftInteger, 0);
      CcF002.FieldDefs.Add('CDEMPRES', ftInteger, 0);
      CcF002.FieldDefs.Add('DSMODENF', ftString,  3);
      CcF002.FieldDefs.Add('NRSERINF', ftString,  6);
      CcF002.FieldDefs.Add('NRNOTA'  , ftInteger, 0);

      CcF002.CreateDataSet;

      CcF002.FieldByName('TpFinEnt').OnValidate := FinalidadeEntradaValidate;
      CcF002.FieldByName('TpFinEnt').OnGetText  := FinalidadeGetText;

      TNumericField(CcF002.FieldByName('QTPRODUT')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF002.FieldByName('VRUNITAR')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF002.FieldByName('PCDESCON')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF002.FieldByName('VRDESCON')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF002.FieldByName('VRTOTAL' )).DisplayFormat := '#,##0.0000';
      TNumericField(CcF002.FieldByName('VRIPI'   )).DisplayFormat := '#,##0.0000';
      TNumericField(CcF002.FieldByName('BSSUBTRI')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF002.FieldByName('VRSUBTRI')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF002.FieldByName('PCALIISS')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF002.FieldByName('PCALIICM')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF002.FieldByName('BSICMS'  )).DisplayFormat := '#,##0.0000';
      TNumericField(CcF002.FieldByName('VRICMS'  )).DisplayFormat := '#,##0.0000';
      TNumericField(CcF002.FieldByName('CDCSTCOF')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF002.FieldByName('BSIMPCOF')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF002.FieldByName('PCALICOF')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF002.FieldByName('VRIMPCOF')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF002.FieldByName('BSIMPPIS')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF002.FieldByName('PCALIPIS')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF002.FieldByName('VRIMPPIS')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF002.FieldByName('VRISENTA')).DisplayFormat := '#,##0.0000';
      TNumericField(CcF002.FieldByName('VROUTRA' )).DisplayFormat := '#,##0.0000';
      TNumericField(CcF002.FieldByName('PCDEBAIC')).DisplayFormat := '#,##0.0000';

      if (CcB004.Active) then
      begin
            CcB004.EmptyDataSet;
            CcB004.Close;
      end;

      gnrLancto := -1;
      CcB004.FieldDefs.Clear;
      CcB004.FieldDefs.Add('nrLancto', ftinteger , 0);
      CcB004.FieldDefs.Add('nrplano' , ftinteger , 0);
      CcB004.FieldDefs.Add('dsplano' , ftstring  ,50);
      CcB004.FieldDefs.Add('nrconcre', ftinteger , 0);
      CcB004.FieldDefs.Add('nmconcre', ftstring  ,50);
      CcB004.FieldDefs.Add('nrcondeb', ftinteger , 0);
      CcB004.FieldDefs.Add('nmcondeb', ftString  ,50);
      CcB004.FieldDefs.Add('cdhistor', ftinteger , 0);
      CcB004.FieldDefs.Add('dshistor', ftString  ,50);
      CcB004.FieldDefs.Add('vrlancto', ftFloat   , 0);
      CcB004.FieldDefs.Add('CdEmpres', ftinteger , 0);
      CcB004.FieldDefs.Add('nrdocto' , ftString  ,30);
      CcB004.FieldDefs.Add('nmformul', ftstring  ,20);
      CcB004.FieldDefs.Add('dtlancto', ftDate    , 0);
      CcB004.FieldDefs.Add('SnEncerr', ftstring  , 1);
      CcB004.FieldDefs.Add('NrClaCre', ftstring  ,60);
      CcB004.FieldDefs.Add('NrClaDeb', ftstring  ,60);
      CcB004.CreateDataSet;

      TFloatField(CcB004.FieldByName('vrlancto')).DisplayFormat := '#0.00';

      CriarTabelaContasPagar;

      if (CcB008.Active) then
      begin
            CcB008.EmptyDataSet;
            CcB008.Close;
      end;

      CcB008.FieldDefs.Clear;
      CcB008.FieldDefs.Add('nrLancto', ftinteger , 0);
      CcB008.FieldDefs.Add('CdCenCus', ftinteger , 0);
      CcB008.FieldDefs.Add('DsCenCus', ftString  ,50);
      CcB008.FieldDefs.Add('VrLancto', ftFloat   , 0);
      CcB008.FieldDefs.Add('TpLancto', ftString  , 1);
      CcB008.CreateDataSet;

      TFloatField(CcB008.FieldByName('VrLancto')).DisplayFormat := '#0.00';
end;

procedure TMov0025.CriarTabelaContasPagar;
begin
      if (CcN003.Active) then
      begin
            CcN003.EmptyDataSet;
            CcN003.Close;
      end;

      CcN003.FieldDefs.Clear;
      CcN003.FieldDefs.Add('NrConta' , ftinteger , 0);
      CcN003.FieldDefs.Add('NrOrdem' , ftinteger , 0);
      CcN003.FieldDefs.Add('DtVencto', ftDate    , 0);
      CcN003.FieldDefs.Add('VrDocto' , ftFloat   , 0);
      CcN003.FieldDefs.Add('CdBarra' , ftString  ,50);
      CcN003.CreateDataSet;

      CcN003.IndexDefs.Clear;
      CcN003.IndexDefs.Add('IdxOrdem', 'NrOrdem', [ixUnique]);
      CcN003.IndexName := 'IdxOrdem';

      TDateField(CcN003.FieldByName('DtVencto')).EditMask := '!99/99/0000;1;_';
      TFloatField(CcN003.FieldByName('VrDocto')).DisplayFormat := '#0.00';

      if (CcN002.Active) then
      begin
            CcN002.EmptyDataSet;
            CcN002.Close;
      end;

      CcN002.FieldDefs.Clear;
      CcN002.FieldDefs.Add('NrConta' , ftInteger , 0);
      CcN002.FieldDefs.Add('CDEMPRES', ftInteger , 0);
      CcN002.FieldDefs.Add('CDFORPAG', ftInteger , 0);
      CcN002.FieldDefs.Add('CDTITULA', ftInteger , 0);
      CcN002.FieldDefs.Add('CDUSUARI', ftInteger , 0);
      CcN002.FieldDefs.Add('DTEMIDOC', ftDatetime, 0);
      CcN002.FieldDefs.Add('DTPROCES', ftDatetime, 0);
      CcN002.FieldDefs.Add('NMARQEDI', ftString  ,30);
      CcN002.FieldDefs.Add('NRDOCTO' , ftInteger , 0);
      CcN002.FieldDefs.Add('NRPREFAT', ftInteger , 0);
      CcN002.FieldDefs.Add('TPCONTA' , ftString  , 1);
      CcN002.FieldDefs.Add('VRORIGEM', ftFloat   , 0);
      CcN002.CreateDataSet;
end;

procedure TMov0025.BtnAtualizarClick(Sender: TObject);
begin
      inherited;
      CpF013.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(187, '<%CdEmpres%>;' +
                                                  DmSrvApl.BrvDicionario.CorpCommaCodes, self.name);
end;

procedure TMov0025.BrvDbEdit1BrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      BrvDbEdit1.BrParams.Clear;
      BrvDbEdit1.BrParams.Add('<%CdEmpres%>;' + BrvDbEdit1.BrDicionario.CorpCommaCodes);
end;

procedure TMov0025.BrvDbEdit23Exit(Sender: TObject);
begin
      inherited;
      if (not CcF001.FieldByName('VrIssqSt').ReadOnly) then
      begin
            CcF001.FieldByName('VRISSQST').AsFloat :=
                               (CcF001.FieldByName('BSISSQN').AsFloat *
                                CcF001.FieldByName('PCALIISS').AsFloat) / 100;
      end;
end;

procedure TMov0025.BrvDbEdit27Exit(Sender: TObject);
begin
      inherited;
      if (not CcF001.FieldByName('VRPIS').ReadOnly) then
      begin
            CcF001.FieldByName('VRPIS').AsFloat := (CcF001.FieldByName('BSPIS').AsFloat *
                                                    CcF001.FieldByName('PCPIS').AsFloat) / 100;
      end;
end;

procedure TMov0025.BrvDbEdit30Exit(Sender: TObject);
begin
      inherited;
      if (not CcF001.FieldByName('VRCOFINS').ReadOnly) then
      begin
            CcF001.FieldByName('VRCOFINS').AsFloat := (CcF001.FieldByName('BSCOFINS').AsFloat *
                                                       CcF001.FieldByName('PCCOFINS').AsFloat) /100;
      end;
end;

procedure TMov0025.BrvDbEdit33Exit(Sender: TObject);
begin
      inherited;
      if (not CcF001.FieldByName('VRCSLL').ReadOnly) then
      begin
            CcF001.FieldByName('VRCSLL').AsFloat := (CcF001.FieldByName('BSCSLL').AsFloat *
                                                     CcF001.FieldByName('PCCSLL').AsFloat) / 100;
      end;
end;

procedure TMov0025.BrvDbEdit37Exit(Sender: TObject);
begin
      inherited;
      if (not CcF001.FieldByName('VRIRRF').ReadOnly) then
      begin
            CcF001.FieldByName('VRIRRF').AsFloat := (CcF001.FieldByName('BSIRRF').AsFloat *
                                                     CcF001.FieldByName('PCIRRF').AsFloat) / 100;
      end;
end;

procedure TMov0025.BrvDbEdit39Exit(Sender: TObject);
begin
      inherited;
      if (not CcF001.FieldByName('VRINSS').ReadOnly) then
      begin
            CcF001.FieldByName('VRINSS').AsFloat := (CcF001.FieldByName('BSINSS').AsFloat *
                                                     CcF001.FieldByName('PCALIINS').AsFloat) / 100;
      end;
end;

procedure TMov0025.CarregarDadosChaveNFe;
begin
      CcF001.FieldByName('NrChaDoc').AsString := EdtNrChave.Text;
      CcF001.FieldByName('DsModeNf').AsString := Copy(EdtNrChave.Text, 21, 2);

      CcF001.FieldByName('NrSeriNf').AsString := BrvString.RemoverZerosEsquerda(
                                                                      Copy(EdtNrChave.Text, 23, 3));

      CcF001.FieldByName('NrNota').AsString   := Copy(EdtNrChave.Text, 26, 9);

      CcF001.FieldByName('CjTitula').AsString := Copy(EdtNrChave.Text, 7, 14);
      BrvDbEdit42.BrValidarEntrada;
end;

procedure TMov0025.EdtNrChaveExit(Sender: TObject);
var pCpXML : TClientDataSet;
    CdEmpresa: String;
begin
      if (CcF002.Active) and (CcF002.RecordCount > 0) then
      begin
            Raise Exception.Create('Nota fiscal já possui itens digitados');
      end else
      begin
            if (Trim(EdtNrChave.Text) > '') then
            begin
                  if Length(Trim(EdtNrChave.Text)) < 44 then
                  begin
                        Raise Exception.Create('Chave inválida');
                  end else
                  begin
                        CarregarDadosChaveNFe;
                        //Verificar
                        CpNfeDet.Close;
                        CpNFePro.Close;
                        CpNFeFat.Close;

                        QpXml.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(
                                            68, '<%NrChaDoc%>;' + EdtNrChave.Text, Name);

                        if (QpXml.Eof) or (QpXml.FieldByName('TxXML').AsString = '') then
                        begin
                              if  MessageDlg('Nota nao encontrada na base local deseja buscar na'
                                          +'receita?', MtConfirmation,[MbYes, MbNo], 0) = IdYes then
                               begin
                                     try

                                         pCpXML := TClientDataSet.Create(Self);
                                         pCpXML.FieldDefs.Clear;
                                         pCpXML.FieldDefs.Add('CjEmpres', ftString,   20);
                                         pCpXML.FieldDefs.Add('NrChaDoc', ftString,   255);
                                         pCpXML.FieldDefs.Add('CdEstado', ftString,   255);
                                         pCpXML.FieldDefs.Add('NrCertif', ftString,   255);
                                         pCpXML.FieldDefs.Add('NrSenCer', ftString,   255);
                                         pCpXML.FieldDefs.Add('Cdempres', ftString,   255);
                                         pCpXML.CreateDataSet;
                                         CdEmpresa   := InputBox('Informe a empresa',
                                                                   'Favor informar EMPRESA !!', '');
                                         pCpXML.Open;
                                         pCpXML.Append;

                                         CpS004.Data := DmSrvApl.BrvDicionario.
                                                      RetornaDadosSqlCadastro(286, '<%CdEmpres%>;'
                                                                                 + CdEmpresa, Name);

                                         pCpXML.FieldByName('NrChaDoc').AsString := EdtNrChave.Text;
                                         pCpXML.FieldByName('CjEmpres').AsString :=
                                                            CpS004.FieldByName('CjEmpres').AsString;
                                         pCpXML.FieldByName('CdEstado').AsString :=
                                                            CpS004.FieldByName('CdEstado').AsString;
                                         pCpXML.FieldByName('NrCertif').AsString :=
                                                            CpS004.FieldByName('NrCertif').AsString;
                                         pCpXML.FieldByName('NrSenCer').AsString :=
                                                            CpS004.FieldByName('NrSenCer').AsString;
                                         pCpXML.FieldByName('Cdempres').AsString :=
                                                            CpS004.FieldByName('Cdempres').AsString;
                                         pCpXML.Post;
                                         BrvServerProgress.Start(Self.Caption, 'Enviando/Recebendo dados Receita', 100, 10);
                                         UDmFis.DmFis.GerarStatusManifesto(pCpXML) ;
                                         BrvServerProgress.Stop;
                                         CarregarDadosChaveNFe;
                                          //Verificar
                                         CpNfeDet.Close;
                                         CpNFePro.Close;
                                         CpNFeFat.Close;
                                         QpXml.Data := DmSrvApl.BrvDicionario.
                                                         RetornaDadosSqlCadastro(68, '<%NrChaDoc%>;'
                                                                           + EdtNrChave.Text, Name);
                                     finally
                                         BrvServerProgress.Stop;
                                     end;

                               end;
                        end;

                        BrvXMLNFE.BrXMLOriginal.Text := QpXml.FieldByName('TxXML').AsString;
                        BrvXMLNFE.ProcessarXml;

                        if (CpNfeDet.Active) and (CpNfeDet.RecordCount > 0) then
                        begin
                              CbxSnItens.Checked := True;
                              CarregarDadosNota;
                              CarregarEmpresa;
                              CarregarDadosItens;
                              //CarregarDocumentosPagar;
                        end;
                  end;
            end;
      end;
end;

function TMov0025.CarregarProduto(pCdEmbala : AnsiString; pCdFornec : Integer;
                                  pCdRefFab : AnsiString; pCdNcm    : AnsiString) : Integer;
var lCPProduto : TClientDataSet;
begin
      try
          Result := 0;

          lCPProduto := TClientDataSet.Create(Self);
          lCPProduto.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(196,
                                                            '<%CdEmbala%>;' + pCdEmbala, Self.Name);

          if lCPProduto.FieldByName('CdProdut').AsInteger > 0 then
          begin
                Result := lCPProduto.FieldByName('CdProdut').AsInteger;
          end else
          begin
                lCPProduto.Close;
                lCPProduto.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(197,
                                                      '<%CdTitula%>;' + IntToStr(pCdFornec) + #13 +
                                                      '<%CdRefFab%>;' + pCdRefFab, Self.Name);

                if lCPProduto.FieldByName('CdProdut').AsInteger > 0 then
                begin
                      Result := lCPProduto.FieldByName('CdProdut').AsInteger;
                end;
          end;
      finally
          lCPProduto.Close;
      end;
end;

procedure TMov0025.CarregarDadosItens;
var lCdProdut : Integer;
    lNrSeqFis : Integer;
    lCdFiscal : Integer;
    lTpFinEnt : AnsiString;
    lCdCstPis : AnsiString;
    lCdCstCof : AnsiString;
    lPcAliPis : Real;
    lPcAliCof : Real;
    lBsImpPis : Real;
    lBsImpCof : Real;
    lVrImpPis : Real;
    lVrImpCof : Real;
    lVrIpi    : Real;
    lVrTotPro : Real;
    lVrIcms   : Real;
    lPcIcms   : Real;
    lPcDeBaIc : Real;
    lCdNcm    : AnsiString;
begin
      CpNFePro.First;

      lTpFinEnt := CbxFinali.Values[CbxFinali.itemIndex];

      CcF002.AfterPost                          := Nil;
      CcF002.FieldByName('TpFinEnt').OnValidate := Nil;

      while not CpNFePro.eof do
      begin
            lVrIpi    := StrToFloat(StringReplace(CpNFePro.FieldByName('IpiTrib_vIpi').AsString,
                                                                         '.', ',', [rfReplaceAll]));

            lVrIcms   := StrToFloatDef(StringReplace(CpNFePro.FieldByName('ICMS_vICMS').AsString,
                                                                      '.', ',', [rfReplaceAll]), 0);
            lPcIcms   := StrToFloatDef(StringReplace(CpNFePro.FieldByName('ICMS_pICMS').AsString,
                                                                      '.', ',', [rfReplaceAll]), 0);

            lPcDeBaIc := StrToFloatDef(StringReplace(CpNFePro.FieldByName('ICMS_pRedBC').AsString,
                                                                      '.', ',', [rfReplaceAll]), 0);

            lVrTotPro := CorrigeValor(CpNFePro.FieldByName('prod_vProd').AsString) + lVrIpi -
                         CorrigeValor(CpNFePro.FieldByName('prod_vDesc').AsString);


            lCdProdut := CarregarProduto(CpNFePro.FieldByName('Prod_cEAN').AsString,
                                         CcF001.FieldByname('CdTitula').AsInteger,
                                         CpNFePro.FieldByName('Prod_CProd').AsString,
                                         CpNFePro.FieldByName('prod_NCM').AsString);

            if lCdProdut > 0 then
            begin
                  lCdNcm := CpNFePro.FieldByName('prod_NCM').AsString;
                  EncontrarCFOP(EdtCdEmpEst.Text, TipoOperacao, lTpFinEnt,
                                   CpNFePro.FieldByName('ICMS_CST').AsString, lNrSeqFis, lCdFiscal);

                  CalcularPisCofins(StrToInt(EdtCdGruEmp.Text),   lCdNcm,    lTpFinEnt,
                                    lVrTotPro, lCdCstPis, lCdCstCof, lPcAliPis, lPcAliCof,
                                    lBsImpPis, lBsImpCof, lVrImpPis, lVrImpCof);

                  DbgF002.SelectedField := CcF002.FieldByName('CdProdut');

                  CcF002.Append;
                  CcF002.FieldByName('CdProdut').AsInteger := lCdProdut;

                  DbgF002.SelectedField := CcF002.FieldByName('DsProdut');

                  CcF002.FieldByName('TpFinEnt').AsString  := lTpFinEnt;
                  CcF002.FieldByName('CdNcm').AsString     := lCdNcm;

                  CcF002.FieldByName('CdSitTri').AsString  :=
                                               CpNFePro.FieldByName('ICMS_CST').AsString;

                  CcF002.FieldByName('NrSeqFis').AsInteger := lNrSeqFis;
                  CcF002.FieldByName('CdOpeFis').AsInteger := lCdFiscal;
                  CcF002.FieldByName('QtProdut').AsString  :=
                              StringReplace(CpNFePro.FieldByName('Prod_qCom').AsString,
                                                                          '.', ',', [rfReplaceAll]);

                  CcF002.FieldByName('VrUnitar').AsString   :=
                              StringReplace(CpNFePro.FieldByName('prod_vUnCom').AsString,
                                                                          '.', ',', [rfReplaceAll]);

                  CcF002.FieldByName('VrUnitar').AsFloat    :=
                              CcF002.FieldByName('VrUnitar').AsFloat  +
                                      (lVrIpi / CcF002.FieldByName('QtProdut').AsFloat);

                  CcF002.FieldByName('VrDescon').AsCurrency :=
                                     CorrigeValor(CpNFePro.FieldByName('prod_vDesc').AsString);

                  if (CcF002.FieldByName('VrDescon').AsFloat > 0) then
                  begin
                        CcF002.FieldByName('PcDescon').AsFloat :=
                                                    (CcF002.FieldByName('VrDescon').AsFloat * 100) /
                                                     CcF002.FieldByName('VrUnitar').AsFloat;
                  end else
                  begin
                        CcF002.FieldByName('PcDescon').AsFloat := 0;
                  end;

                  CcF002.FieldByName('BsSubTri').AsFloat   := StrToFloatDef(
                                      StringReplace(CpNFePro.FieldByName('ICMS_vBCST').AsString,
                                                                      '.', ',', [rfReplaceAll]), 0);
                  CcF002.FieldByName('VrSubTri').AsFloat   := StrToFloatDef(
                                      StringReplace(CpNFePro.FieldByName('ICMS_vICMSST').AsString,
                                                                      '.', ',', [rfReplaceAll]), 0);

                  CcF002.FieldByName('VrOutra').AsFloat    := TotalProduto +
                                             CcF002.FieldByName('VrSubTri').AsFloat;
                  CcF002.FieldByName('CdCstPis').AsString  := lCdCstPis;
                  CcF002.FieldByName('CdCstCof').AsString  := lCdCstCof;
                  CcF002.FieldByName('PcAliPis').AsFloat   := lPcAliPis;
                  CcF002.FieldByName('PcAliCof').AsFloat   := lPcAliCof;
                  CcF002.FieldByName('BsImpPis').AsFloat   := lBsImpPis;
                  CcF002.FieldByName('BsImpCof').AsFloat   := lBsImpCof;
                  CcF002.FieldByName('VrImpPis').AsFloat   := lVrImpPis;
                  CcF002.FieldByName('VrImpCof').AsFloat   := lVrImpCof;
                  CcF002.FieldByName('VrIpi'   ).AsFloat   := lvripi;
                  CcF002.FieldByName('BsICMS'  ).AsFloat   := TotalProduto;
                  CcF002.FieldByName('VrIcms'  ).AsFloat   := lVrIcms;
                  CcF002.FieldByName('PcAliICM').AsFloat   := lPcIcms;
                  CcF002.FieldByName('PcDeBaIc').AsFloat   := lPcDeBaIc;

                  CcF001.FieldByName('BsIcms'  ).AsFloat   := 0;
                  CcF001.FieldByName('VrIseNot').AsFloat   := 0;
                  CcF002.Post;
            end else
            begin
                  Raise Exception.Create('Não foi possível encontrar o codigo do produto ' +
                                                      CpNFePro.FieldByName('Prod_xProd').AsString);
            end;

            CpNFePro.Next;
      end;

      CcF002.First;
      CcF002.AfterPost                          := CcF002AfterPost;
      CcF002.FieldByName('TpFinEnt').OnValidate := FinalidadeEntradaValidate;

      CcF002AfterPost(Nil);

      BrvDbEdit43.Enabled;
end;

procedure TMov0025.CarregarEmpresa;
var lCPInfAux : TClientDataSet;
begin
      try
          lCPInfAux := TClientDataSet.Create(Self);
          lCPInfAux.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(195, '<%CjEmpres%>;' +
                                             CpNfeDet.FieldByName('dest_CNPJ').AsString, Self.Name);

          if (not CpNfeDet.Eof) then
          begin
                CcF001.FieldByName('CdEmpres').AsInteger :=
                                                        lCPInfAux.FieldByName('CdEmpres').AsInteger;

                BrvDbEdit1.BrValidarEntrada;

                if (CcF001.FieldByName('CdEmpres').AsInteger = 0) then
                begin
                      Raise Exception.Create('Esta NFe esta destinada para ' +
                                             lCPInfAux.FieldByName('CdEmpres').AsString  + '-' +
                                             lCPInfAux.FieldByName('RsEmpres').AsString  + #13 +
                                             'Você não esta trabalhando com esta filial.');
                end;
          end else
          begin
                Raise Exception.Create('Não foi encontrado empresa cadastrada para o CNPJ do' +
                                       ' destinatário da NFe');
          end;
      finally
          FreeAndNil(lCPInfAux);
      end;
end;

procedure TMov0025.CarregarDadosNota;
var lDtEmissa : AnsiString;
begin
      lDtEmissa := CpNfeDet.FieldByName('ide_dEmi').AsString;
    //  lDtEmissa := Copy(lDtEmissa, 9, 2) + '/' + Copy(lDtEmissa, 6, 2) + '/' + Copy(lDtEmissa, 1,4);

      CcF001.FieldByName('DtEmiNot').AsString   := lDtEmissa;
      CcF001.FieldByName('VrConNot').AsFloat    := CorrigeValor(
                                                   CpNfeDet.FieldByName('ICMSTot_vNF').AsString);
      CcF001.FieldByName('VrOutNot').AsString   := CcF001.FieldByName('VrConNot').AsString;
      CcF001.FieldByName('VrDesAce').AsCurrency := CorrigeValor(
                                                   CpNfeDet.FieldByName('ICMSTot_vOutro').AsString);
      CcF001.FieldByName('VrSeguro').AsCurrency := CorrigeValor(
                                                   CpNfeDet.FieldByName('ICMSTot_vSeg').AsString);
      CcF001.FieldByName('VrFrete').AsCurrency := CorrigeValor(
                                                   CpNfeDet.FieldByName('ICMSTot_vFrete').AsString);
end;

function TMov0025.CorrigeValor(pVrXml : String) : Currency;
begin
      Result := StrToCurrDef(StringReplace(pVrXml, '.', ',', [rfReplaceAll]), 0);
end;

procedure TMov0025.CpF014AfterScroll(DataSet: TDataSet);
begin
      inherited;
      if (CpF014.RecordCount > 0) then
      begin
            CpF017.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(188,
            '<%NrPreLan%>;' + CpF014.FieldByName('NrPreLan').AsString + Chr(13) +
            '<%NrLancto%>;' + CpF014.FieldByName('NrLancto').AsString, self.Name);
      end else
      begin
            CpF017.Close;
      end;
end;

procedure TMov0025.BtnCancelarClick(Sender: TObject);
begin
      inherited;
      if  MessageDlg('Confirma cancelamento da digitação?', MtConfirmation,
                     [MbYes, MbNo], 0) = IdYes then
      begin
            PgcNF.ActivePageIndex := 0;
            CbxFinali.itemindex   := 0;
            EdtNrChave.text       := '';
            EdtCdEstEmp.text      := '';
            EdtCdEstEmi.text      := '';
            EdtRsTitula.text      := '';
            EdtDsEmpres.text      := '';
            EdtDsEvento.text      := '';
            EdtCdFiscal.text      := '';
            EdtDsFiscal.text      := '';
            CpF013.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(187, '<%CdEmpres%>;' +
                                                  DmSrvApl.BrvDicionario.CorpCommaCodes, self.name);
      end;
end;

procedure TMov0025.BtnCancelParcelClick(Sender: TObject);
begin
      inherited;
      if (CcN003.RecordCount > 0) then
      begin
            if (MessageDlg('Deseja realmente cancelar as parcelas lançadas?', mtConfirmation,
                                                                     [mbyes, mbno], 0) = mrYes) then
            begin
                  CriarTabelaContasPagar;
            end;
      end else
      begin
            CriarTabelaContasPagar;
      end;
end;

procedure TMov0025.BtnGerParcelClick(Sender: TObject);
var lVrCartei : Extended;
begin
      inherited;
      lVrCartei := (CcF001.FieldByName('VrConNot').AsFloat) -
                   (CcF001.FieldByName('VRPIS'   ).AsFloat  +
                    CcF001.FieldByName('VRCOFINS').AsFloat  +
                    CcF001.FieldByName('VRCSLL'  ).AsFloat  +
                    CcF001.FieldByName('VRIRRF'  ).AsFloat  +
                    CcF001.FieldByName('VRISSQST').AsFloat  +
                    CcF001.FieldByName('VRINSS'  ).AsFloat);

      if (EdtStGerDup.Text = 'S') and (lVrCartei > 0) then
      begin
            if (EdtStGerDup.Text = 'S') and (lVrCartei > 0) then
            begin
                  CarregarParcelas(CcF001.FieldByName('CdEvento').AsString,
                                   lVrCartei,
                                   CcF001.FieldByName('DtEmiNot').AsDateTime);
            end else
            begin
                  MessageDlg('Evento não gera documentos.', mtInformation, [mbok], 0);
            end;
      end;

end;

procedure TMov0025.LocalizarParametro(pNrParam : String);
begin
      if (not CpB012.Locate('NrSeqPar', pNrParam, [loCaseInsensitive])) then
      begin
            raise Exception.Create('Parâmetros de contabilização não localizado!' + Chr(13) +
                             'Verifique...');
      end;
end;

procedure TMov0025.VerificaParametros;
var
    lNrIdx    : Integer;
    lLnParame : String;
    lNrSeqPar : String;
begin
      for lNrIdx := 0 to gStlParCon.Count-1 do
      begin
            lLnParame := gStlParCon.Strings[lNrIdx];
            lNrSeqPar := lNrSeqPar + Copy(lLnParame, 1, Pos(';', lLnParame)-1) + ',';
      end;

      lNrSeqPar := Copy(lNrSeqPar, 1, length(lNrSeqPar)-1);

      CpB012.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(177,
                              '<%TpFormul%>;' + UpperCase(copy(Self.Name, 1,3)) + Chr(13) +
                              '<%NrSeqFor%>;' + copy(Self.Name, 4,4) + Chr(13) +
                              '<%CdEmpres%>;' + CcF001.FieldByName('CdEmpres').AsString + Chr(13) +
                              '<%NrSeqPar%>;' + lNrSeqPar, UpperCase(Self.Name));

      if (gStlParCon.Count <> CpB012.RecordCount) then
      begin
            raise Exception.Create('Parâmetros de contabilização não cadastrados!' + Chr(13) +
                                   'Verifique...');
      end;

      LocalizarParametro('3');
      LocalizarParametro('4');
      LocalizarParametro('5');
      LocalizarParametro('6');
      LocalizarParametro('7');
      LocalizarParametro('8');
      LocalizarParametro('9');
end;

procedure TMov0025.BtnLancarClick(Sender: TObject);
begin
      inherited;
      BtnManualClick(nil);
      CcF001.FieldByName('CdEmpres').AsInteger := CpF013.FieldByName('CdEmpres').AsInteger;
      CcF001.FieldByName('NrPreLan').AsInteger := CpF013.FieldByName('NrPreLan').AsInteger;
      BrvDbEdit1.BrValidarEntrada;
      EdtNrChave.Text := CpF013.FieldByName('NrChaDoc').AsString;

      VerificaParametros;
      LocalizarParametro('3');

      //Contabil
      CpF014.Close;
      CpF014.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(198, '<%nrprelan%>;' +
                                            CpF013.FieldByName('NrPreLan').AsString , self.Name);
      CpF014.Open;

      try
          CcB004.AfterInsert := nil;

          while not CpF014.eof  do
          begin
                CcB002.Close;
                CcB002.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(202,
                                '<%NrPlano%>;' + CpF014.FieldByName('nrplano' ).AsString + Chr(13) +
                                '<%NrConta%>;' + CpF014.FieldByName('nrconta').AsString,
                                Self.name);
                CcB002.Open;

                CcB004.Append;
                CcB004.FieldByName('nrlancto').Asinteger := CpF014.FieldByName('nrlancto').Asinteger;
                CcB004.FieldByName('CdEmpres').Asinteger := CcF001.FieldByName('CdEmpres').Asinteger;
                CcB004.FieldByName('dtlancto').AsDateTime:= DmSrvApl.BrvDicionario.DataHoraServer;
                CcB004.FieldByName('nrconcre').Asinteger := CpB012.FieldByName('nrconcre').Asinteger;
                CcB004.FieldByName('nrcondeb').Asinteger := CpF014.FieldByName('nrconta' ).Asinteger;
                CcB004.FieldByName('vrlancto').AsFloat   := CpF014.FieldByName('vrlancto').AsFloat;
                CcB004.FieldByName('nrdocto' ).AsString  := CcF001.FieldByName('NrNota'  ).AsString;
                CcB004.FieldByName('cdhistor').Asinteger := CpF014.FieldByName('cdhistor').Asinteger;
                CcB004.FieldByName('dshistor').AsString  := CpF014.FieldByName('dshistor').AsString;
                CcB004.FieldByName('nmformul').Asstring  := Self.Name;
                CcB004.FieldByName('SnEncerr').Asstring  := 'N';
                CcB004.FieldByName('NrClaCre').Asstring  := CpF014.FieldByName('NrClassi').AsString;
                CcB004.FieldByName('NrClaDeb').Asstring  := CcB002.FieldByName('NrClassi').AsString;
                CcB004.FieldByName('nrplano' ).Asinteger := CpF014.FieldByName('nrplano' ).Asinteger;
                CcB004.FieldByName('dsplano' ).Asstring  := CpF014.FieldByName('dsplano' ).Asstring;
                CcB004.FieldByName('nmconcre').Asstring  := CcB002.FieldByName('NmConta' ).AsString;
                CcB004.FieldByName('nmcondeb').AsString  := CpF014.FieldByName('nmconta' ).Asstring;
                CcB004.Post;

                CpF017.First;

                while not CpF017.eof do
                begin
                      CcB008.Append;
                      CcB008.FieldByName('nrLancto').AsInteger :=
                                                           CpF017.FieldByName('NrLancto').AsInteger;
                      CcB008.FieldByName('CdCenCus').AsInteger :=
                                                           CpF017.FieldByName('CdCenCus').AsInteger;
                      CcB008.FieldByName('DsCenCus').AsString  :=
                                 DmSrvApl.BrvDicionario.RetornaValorColunaTabela('B006', 'DSCenCus',
                                               'CdCenCus', CpF017.FieldByName('CdCenCus').AsString);
                      CcB008.FieldByName('TpLancto').AsString  := 'D';
                      CcB008.FieldByName('VrLancto').AsFloat   :=
                                                             CpF017.FieldByName('VrLancto').AsFloat;

                      CcB008.Post;

                      CpF017.Next;
                end;

                CpF014.Next;
         end;
      finally
          CcB004.AfterInsert := CcB004AfterInsert;
      end;

      //Contas a Pagar
      CpF015.Close;
      CpF015.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(199, '<%nrprelan%>;' +
                                               CpF013.FieldByName('NrPreLan').AsString , self.Name);
      CpF015.Open;
      CpF015.First;

      while not CpF015.Eof do
      begin
            CcN003.Append;
            CcN003.FieldByName('NrOrdem' ).AsInteger  := CpF015.FieldByName('NrParcel').AsInteger;
            CcN003.FieldByName('DtVencto').AsDateTime := CpF015.FieldByName('DtVencto').AsDateTime;
            CcN003.FieldByName('VrDocto' ).AsFloat    := CpF015.FieldByName('VrDocto' ).AsFloat;
            CcN003.Post;

            CpF015.Next;
      end;
end;

procedure TMov0025.SomaTotalProdutos(pCdsItens : Variant);
var lCdsAuxIte : TClientDataSet;
    lVrSubTri  : Currency;
    lBsSubTri  : Currency;
begin
      try
          lVrSubTri             := 0;
          lBsSubTri             := 0;
          EdtTtProdut.BrAsFloat := 0;
          lCdsAuxIte            := TClientDataSet.Create(nil);
          lCdsAuxIte.Data       := pCdsItens;

          while not lCdsAuxIte.Eof do
          begin
                EdtTtProdut.BrAsFloat := EdtTtProdut.BrAsFloat                        +
                                        (lCdsAuxIte.FieldByName('VRTOTAL').AsFloat   +
                                         lCdsAuxIte.FieldByName('VrIpi').AsFloat      +
                                         lCdsAuxIte.FieldByName('VrSubTri').AsFloat);

                lVrSubTri := lVrSubTri + lCdsAuxIte.FieldByName('VrSubTri').AsFloat;
                lBsSubTri := lBsSubTri + lCdsAuxIte.FieldByName('BsSubTri').AsFloat;

                lCdsAuxIte.Next;
          end;

          CcF001.FieldByName('VrSubTri').AsCurrency := lVrSubTri;
          CcF001.FieldByName('BsSubTri').AsCurrency := lBsSubTri;

          lCdsAuxIte.Close;
      finally
          FreeAndNil(lCdsAuxIte);
      end;
end;

procedure TMov0025.EncontrarCFOP(pCdEstado, pTpOpeFis, pTpFinEnt, pCdSitTri: AnsiString;
                                 var pNrSeqFis, pCdOpeFis : Integer);
var lCdsCodFis: TClientDataSet;
begin
      try
          lCdsCodFis := TClientDataSet.Create(Self);
          lCdsCodFis.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(192,
                             '<%CdSeqEst%>;' + pCdEstado + #13 + '<%TpOpeFis%>;' + pTpOpeFis + #13 +
                             '<%TpFinEnt%>;' + pTpFinEnt + #13 + '<%CdSitTri%>;' + pCdSitTri,
                             Self.Name);
          pNrSeqFis := lCdsCodFis.FieldByName('NrSeqFis').AsInteger;
          pCdOpeFis := lCdsCodFis.FieldByName('CdFiscal').AsInteger;
          lCdsCodFis.Close;
      finally
          FreeAndNil(lCdsCodFis);
      end;
end;

function TMov0025.TipoOperacao : String;
begin
      Result := 'E';

      if (EdtCdEstEmp.Text <> CcF001.FieldByName('CdEstEmi').AsString) then
      begin
            Result := 'I';
      end;
end;

procedure TMov0025.CalcularPisCofins(pCdGruEmp : Integer;        pCdNcm    : AnsiString;
                                     pTpFinEnt : AnsiString;     pVrProdut : Real;
                                 var pCdCstPis : AnsiString; var pCdCstCof : AnsiString;
                                 var pPcAliPis : Real;       var pPcAliCof : Real;
                                 var pBsImpPis : Real;       var pBsImpCof : Real;
                                 var pVrImpPis : Real;       var pVrImpCof : Real);
var lCdsConfPis : TClientDataSet;
begin
      try
          lCdsConfPis := TClientDataSet.Create(Self);
          lCdsConfPis.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(193,
                                                    '<%CdGruEmp%>;' + IntToStr(pCdGruEmp) + #13 +
                                                    '<%TpFinEnt%>;' + pTpFinEnt           + #13 +
                                                    '<%CdNcm%>;'    + pCdNcm, Self.Name);

          if lCdsConfPis.eof then
          begin
                Raise Exception.Create('Produto sem configuração do PIS/COFINS');
          end else
          begin
                //-- Cálculo PIS
                pCdCstPis := lCdsConfPis.FieldByName('CdCstPis').AsString;
                pPcAliPis := lCdsConfPis.FieldByName('PcAliPis').AsFloat;

                pBsImpPis := (pVrProdut / 100) * lCdsConfPis.FieldByName('PcBasPis').AsFloat;
                pVrImpPis := (pBsImpPis / 100) * pPcAliPis;
                //-- FIM Cálculo PIS

                //-- Cálculo COFINS
                pCdCstCof := lCdsConfPis.FieldByName('CdCstCof').AsString;
                pPcAliCof := lCdsConfPis.FieldByName('PcAliCof').AsFloat;

                pBsImpCof := (pVrProdut / 100) * lCdsConfPis.FieldByName('PcBasCof').AsFloat;
                pVrImpCof := (pBsImpCof / 100) * pPcAliCof;
                //-- FIM Cálculo COFINS

          end;
      finally
          FreeAndNil(lCdsConfPis);
      end;
end;

procedure TMov0025.FinalidadeEntradaValidate(Sender: TField);
var lCdCstPis : AnsiString;
    lCdCstCof : AnsiString;
    lPcAliPis : Real;
    lPcAliCof : Real;
    lBsImpPis : Real;
    lBsImpCof : Real;
    lVrImpPis : Real;
    lVrImpCof : Real;
    lNrSeqFis : Integer;
    lCdOpeFis : Integer;
begin
      EncontrarCFOP(EdtCdEmpEst.Text, TipoOperacao, Sender.Value,
                                     CcF002.FieldByName('CdSitTri').AsString, lNrSeqFis, lCdOpeFis);


      CalcularPisCofins(StrToInt(EdtCdGruEmp.Text),
                        CcF002.FieldByName('CdNcm').AsString,
                        Sender.Value,
                        CcF002.FieldByName('VRTOTAL').AsFloat,
                        lCdCstPis, lCdCstCof, lPcAliPis,
                        lPcAliCof, lBsImpPis, lBsImpCof,
                        lVrImpPis, lVrImpCof);

      CcF002.FieldByName('NrSeqFis').AsInteger := lNrSeqFis;
      CcF002.FieldByName('CdOpeFis').AsInteger := lNrSeqFis;
      CcF002.FieldByName('CdCstPis').AsString  := lCdCstPis;
      CcF002.FieldByName('CdCstCof').AsString  := lCdCstCof;
      CcF002.FieldByName('PcAliPis').AsFloat   := lPcAliPis;
      CcF002.FieldByName('PcAliCof').AsFloat   := lPcAliCof;
      CcF002.FieldByName('BsImpPis').AsFloat   := lBsImpPis;
      CcF002.FieldByName('BsImpCof').AsFloat   := lBsImpCof;
      CcF002.FieldByName('VrImpPis').AsFloat   := lVrImpPis;
      CcF002.FieldByName('VrImpCof').AsFloat   := lVrImpCof;
end;

procedure TMov0025.FinalidadeGetText(Sender: TField; var Text: String; DisplayText: Boolean);
begin
      if Sender.AsString <> '' then
      begin
            case Sender.AsString[1] of
                 'C': Text := 'Consumo';
                 'I': Text := 'Insumo';
                 'A': Text := 'Ativo';
                 'O': Text := 'Outros';
            end;
      end;
end;

procedure TMov0025.BtnManualClick(Sender: TObject);
begin
      inherited;
      PgcNF.ActivePageIndex := 1;
      PgcDadosNF.ActivePageIndex := 0;

      GerarTabelasTemporarias;
      gDsComple.Text := '';

      CcF001.Append;
      CcF001.FieldByName('DTENTRAD').AsDateTime := Now();
      CcF001.FieldByName('SNDESPIC').AsString   := 'N';
      CcF001.FieldByName('SNFREICM').AsString   := 'N';
      CcF001.FieldByName('SNFREIPI').AsString   := 'N';
      CcF001.FieldByName('VRCONNOT').AsFloat    := 0;
      CcF001.FieldByName('TPFRETE' ).AsString   := 'C';
      CcF001.FieldByName('STNOTA'  ).AsString   := 'F';

      if (not DmSrvApl.BrvDicionario.UserGroupAdm) then
      begin
            CcF001.FieldByName('DTENTRAD').ReadOnly :=
                       Not DmSrvApl.BrvDicionario.TemPermissaoAlteracaoAtributo('F001', 'DTENTRAD');
      end;

      CbxSnItens.Checked := True;
      CbxSnItensClick(CbxSnItens);

      TratarDadosCabecalho(False);

      CbxPIS.Checked    := False;
      CbxPISClick(nil);
      CbxCOFINS.Checked := False;
      CbxCOFINSClick(nil);
      CbxCSLL.Checked   := False;
      CbxCSLLClick(nil);
      CbxIRRF.Checked   := False;
      CbxIRRFClick(nil);
      CbxISSQN.Checked  := False;
      CbxISSQNClick(nil);
      CbxINSS.Checked   := False;
      CbxINSSClick(nil);

      CbxFinali.SetFocus;
end;

function  TMov0025.ValidarNotaFiscal : String;
Var lDtDia   : Word;
    lDtAno   : Word;
    lDtMesHoj: Word;
    lDtMesEnt: Word;
    lVrTotCal: Real;
    lSnIncorr: String;
    lVrTotCC : Real;
    lSnSair  : String;
    lVrTotCon: Currency;
begin
      if  CcF001.FieldByName('CdEstEmi').AsString = '' then
      begin
            raise Exception.Create('O estado do fornecedor não pode ser nulo');
      end;

      if CcF001.FieldByName('BSPIS').AsFloat > CcF001.FieldByName('VrConNot').AsFloat then
      begin
            raise Exception.Create('Base de IPI maior que o total da nota.');
      end;

      if CcF001.FieldByName('BSCOFINS').AsFloat > CcF001.FieldByName('VrConNot').AsFloat then
      begin
            raise Exception.Create('Base de COFINS maior que o total da nota.');
      end;

      if CcF001.FieldByName('BSCSLL').AsFloat > CcF001.FieldByName('VrConNot').AsFloat then
      begin
            raise Exception.Create('Base de CSLL maior que o total da nota.');
      end;

      if CcF001.FieldByName('BSIRRF').AsFloat > CcF001.FieldByName('VrConNot').AsFloat then
      begin
            raise Exception.Create('Base de IRRF maior que o total da nota.');
      end;

      if CcF001.FieldByName('BSISSQN').AsFloat > CcF001.FieldByName('VrConNot').AsFloat then
      begin
            raise Exception.Create('Base de ISSQN maior que o total da nota.');
      end;

      if CcF001.FieldByName('BSINSS').AsFloat > CcF001.FieldByName('VrConNot').AsFloat then
      begin
            raise Exception.Create('Base de INSS maior que o total da nota.');
      end;

      if  CcF001.FieldByName('NRSERINF').AsString = '' then
      begin
            raise Exception.Create('Número de série deve ser informado');
      end;

      if CcF001.FieldByName('NRNOTA').AsInteger = 0 then
      begin
            raise Exception.Create('Número da nota deve ser informado');
      end;

      if CbxSnItens.Checked = false then
      begin
            lVrTotCal := CcF001.FieldByName('BsIcms'  ).AsFloat +
                         CcF001.FieldByName('BsSubtri').AsFloat +
                         CcF001.FieldByName('VrIseNot').AsFloat +
                         CcF001.FieldByName('VrOutNot').AsFloat;

            if StrToFloat(FormatFloat('0.00', lVrTotCal)) <>
               StrToFloat(FormatFloat('0.00', CcF001.FieldByName('VRCONNOT').AsFloat)) then
            begin
                  PgcDadosNF.ActivePageIndex := 1;
                  BrvDbEdit11.SetFocus;
                  raise Exception.Create('Valor fiscal incorreto, por favor, verificar ' +
                                         '(Base de cálculo - Valor isentas ou outras)!');
            end;
      end;

      DecodeDate(CcF001.FieldByName('DtEntrad').AsDateTime, lDtAno, lDtMesEnt, lDtDia);
      DecodeDate(DmSrvApl.BrvDicionario.DataServer, lDtAno, lDtMesHoj, lDtDia);

      if (Not LancaDentroDoMes(CcF001.FieldByName('CdEmpres').AsInteger,
                               CcF001.FieldByName('DtEntrad').AsDateTime)) and
         (lDtMesEnt <> lDtMesHoj) then
      begin
            raise Exception.Create('Tentativa de lançamento de nota fiscal do mês passado. ' + #13 +
                                   'Não permitido passar para Controladoria.');
      end;

      // O valor total dos CC não pode ser superior ao total da nota
      CcB004.First;
      if CcB004.RecordCount > 0 then
      begin
            lSnSair  := 'N';
            lSnIncorr:= 'N';
            while (not CcB004.Eof) and (lSnSair = 'N') do
            begin
                  if (CcB004.FieldByName('NrConDeb').AsInteger = 0) or
                     (CcB004.FieldByName('CdHistor').AsInteger = 0) or
                     (CcB004.FieldByName('NrConCre').AsInteger = 0) then
                  begin
                        MessageDlg('Favor, informar os lançamentos contábeis!', MtError, [MbOk], 0);
                        lSnSair  := 'S';
                        lSnIncorr:= 'S';
                  end;

                  CcB004.Next;
            end;

            CcB004.First;
      end else
      begin
            MessageDlg('Favor, informar os lançamentos contábeis!', MtError, [MbOk], 0);
            lSnSair   := 'S';
            lSnIncorr := 'S';
      end;

      lVrTotCon := 0;
      while lSnSair = 'N' do
      begin
            CcB008.First;

            lVrTotCC := 0;

            while not CcB008.Eof do
            begin
                  if CcB008.FieldByName('CdCenCus').AsInteger > 0 then
                  begin
                        lVrTotCC := lVrTotCC + CcB008.FieldByName('VrLancto').AsFloat;
                  end;
                  CcB008.Next;
            end;

            if StrToFloat(FormatFloat('0.00', lVrTotCC)) >
               StrToFloat(FormatFloat('0.00', CcB004.FieldByName('VrLancto').AsFloat)) then
            begin
                  MessageDlg('Valor do Centro de Custo não confere!' , MtError, [MbOk], 0);

                  lSnIncorr := 'S';
                  lSnSair   := 'S';
            end;

            lVrTotCon := lVrTotCon + CcB004.FieldByName('VrLancto').AsFloat;
            CcB004.Next;
            if CcB004.Eof then
            begin
                  lSnSair  := 'S';
            end;
      end;

      if lSnIncorr = 'S' then
      begin
            Result := 'Erros';
      end else
      begin
            if Result <> 'Erros' then
            begin
                  if CbxSnItens.Checked then
                  begin
                        if (not NotaValida) then
                        begin
                              Result := 'Erros';
                        end;
                  end;
            end;
      end;
end;

function TMov0025.NotaValida : Boolean;
var lBsSubTri : Real;
    lVrSubTri : Real;
    lTtNota   : Real;
    lVrIpi    : Real;
    lVrFiscal : Real;
    lBsIcms   : Real;
    lVrIcms   : Real;
begin
      Result := True;

      if CcF001.FieldByName('CdEvento').AsInteger = 0 then
      begin
            MessageDlg('Evento não foi informado.', MtError, [MbOk], 0);
            Result := False;
      end;

      if (Result) then
      begin
            if (CcF001.FieldByName('DtEmiNot').AsString = '') then
            begin
                  MessageDlg('Data de emissão deve informada.', MtError, [MbOk], 0);
                  Result := False;
            end else
            begin
                  if (CcF001.FieldByName('DtEmiNot').AsDateTime >
                      CcF001.FieldByName('DTEntrad').AsDateTime) then
                  begin
                        MessageDlg('Data de emissão não pode ser maior ' +
                                   'que data de entrada', MtError, [MbOk], 0);
                        Result := False;
                  end;
            end;
      end;

      TotalizarItensNota(lBsIcms, lVrIcms, lBsSubTri, lVrSubTri, lTtNota, lVrIpi, Result);

      if Result then
      begin
            lVrFiscal := CcF001.FieldByName('BsIcms'  ).AsFloat +
                        CcF001.FieldByName('VrIseNot').AsFloat +
                        CcF001.FieldByName('VrOutNot').AsFloat;

            if (lVrFiscal <> CcF001.FieldByName('VrConNot').AsFloat) then
            begin
                  PgcDadosNF.ActivePageIndex := 1;
                  BrvDbEdit11.SetFocus;
                  MessageDlg('Valor fiscal incorreto, por favor, verificar!', MtError, [MbOk], 0);
                  Result := False;
            end;
      end;

      if Result then
      begin
            lVrFiscal := CcF001.FieldByName('BsIcms'  ).AsFloat +
                         CcF001.FieldByName('VrIseNot').AsFloat +
                         CcF001.FieldByName('VrOutNot').AsFloat;

            if Abs(lVrFiscal - lTtNota) > 0.02 then
            begin
                  PgcDadosNF.ActivePageIndex := 1;
                  BrvDbEdit11.SetFocus;
                  MessageDlg('Valor fiscal incorreto com relação ao valor total dos produtos, ' +
                             'por favor, verificar!', MtError, [MbOk], 0);
                  Result := False;
            end;
      end;
end;

function TMov0025.ContinuarValidandoProduto : Boolean;
begin
      Result := True;

      if CcF002.FieldByName('VrUnitar').AsFloat <= 0 then
      begin
            MessageDlg('Valor unitário do produto não pode zero.', MtError, [MbOk], 0);
            Result := False;
      end else
      begin
            if CcF002.FieldByName('VrIpi').AsFloat < 0 then
            begin
                  MessageDlg('Valor do IPI do produto não pode ser menor ' +
                                               'que zero', MtError, [MbOk], 0);
                  Result := False;
            end;
      end;
end;

procedure TMov0025.TotalizarItensNota(var pBsIcms, pVrIcms, pBsSubTri, pVrSubTri, pTtNota,
                                                                pVrIpi: Real; var pResult: Boolean);

var lQtItNota : Integer;
    lVrFrete  : Currency;
    lVrIsenta : Currency;
    lVrOutras : Currency;
    lVrDescon : Currency;
begin
      pBsIcms   := 0;
      pVrIcms   := 0;
      pTtNota   := 0;
      pVrSubTri := 0;
      pVrIpi    := 0;
      lVrIsenta := 0;
      lVrOutras := 0;
      lQtItNota := 0;
      lVrDescon := 0;

      CcF002.First;

      while (not CcF002.Eof) and (pResult) do
      begin
            if CcF002.FieldByName('QtProdut').AsFloat <= 0 then
            begin
                  MessageDlg('Quantidade do produto não pode ser zero.', MtError, [MbOk], 0);
                  pResult := False;
            end else
            begin
                  pResult := ContinuarValidandoProduto
            end;

            if CcF002.FieldByName('BsSubTri').AsFloat > 0 then
            begin
                  pBsSubTri := pBsSubTri + CcF002.FieldByName('BsSubTri').AsFloat;
                  pVrSubTri := pVrSubTri + CcF002.FieldByName('VrSubTri').AsFloat;
            end;

            if CcF002.FieldByName('BsIcms').AsFloat > 0 then
            begin
                  pBsIcms := pBsIcms + CcF002.FieldByName('VrTotal').AsFloat *
                                     ((100 - CcF002.FieldByName('PcDeBaIc').AsFloat) / 100);
            end;

            if CcF002.FieldByName('VrIcms').AsFloat > 0 then
            begin
                  pVrIcms := pVrIcms + CcF002.FieldByName('VrIcms').AsFloat;
            end;

            pTtNota := pTtNota + CcF002.FieldByName('VrTotal').AsFloat;
            pVrIpi  := pVrIpi  + CcF002.FieldByName('VrIpi').AsFloat;

            Inc(lQtItNota);

            lVrIsenta := lVrIsenta + CcF002.FieldByName('VrIsenta').AsFloat;
            lVrOutras := lVrOutras + CcF002.FieldByName('VrOutra').AsFloat;

            CcF002.Next;
      end;

      if (CcF001.FieldByName('VrIseNot').AsCurrency <> lVrIsenta) then
      begin
            MessageDlg('Valor total de isentas da NF não confere com o valor total de isenta dos ' +
                       'itens.', MtError, [MbOk], 0);
            pResult := False;
      end;

      if Abs(CcF001.FieldByName('VrOutNot').AsCurrency  -
            (CcF001.FieldByName('VrFrete').AsCurrency   +
             CcF001.FieldByName('VrSeguro').AsCurrency  + lVrOutras)) > 0.02 then
      begin
            MessageDlg('Valor total de outras da NF não confere com o valor total de outras dos ' +
                       'itens.', MtError, [MbOk], 0);
            pResult := False;
      end;

      FreteIncideIpi(CcF001.FieldByName('SnFreIpi').AsString,
                     CcF001.FieldByName('VrFrete').AsFloat,
                     EdtTtProdut.BrAsFloat, CcF002, pVrIpi);

      if CcF001.FieldByName('TpFrete').AsString = 'N' then
      begin
            lVrFrete := CcF001.FieldByName('VrFrete').AsFloat;
      end else
      begin
            lVrFrete := 0;
      end;

      pTtNota := (pTtNota + lVrFrete  + CcF001.FieldByName('VrSeguro').AsFloat +
                                        CcF001.FieldByName('VrDesAce').AsFloat + pVrIpi + pVrSubTri);

      pBsIcms   := StrToFloat(FormatFloat('0.00', pBsIcms));
      pVrIcms   := StrToFloat(FormatFloat('0.00', pVrIcms));
      pBsSubTri := StrToFloat(FormatFloat('0.00', pBsSubTri));
      pVrSubTri := StrToFloat(FormatFloat('0.00', pVrSubTri));
      pTtNota   := StrToFloat(FormatFloat('0.00', pTtNota));
      pVrIpi    := StrToFloat(FormatFloat('0.00', pVrIpi));

      if  not (CcF001.State in [DsEdit]) then
      begin
            CcF001.Edit;
      end;

      CcF001.FieldByName('BsIcms').AsString   :=
                                        FormatFloat('0.00', CcF001.FieldByName('BsIcms'  ).AsFloat);

      CcF001.FieldByName('VrIcms').AsString   :=
                                        FormatFloat('0.00', CcF001.FieldByName('VrIcms'  ).AsFloat);

      CcF001.FieldByName('BsSubTri').AsString :=
                                        FormatFloat('0.00', CcF001.FieldByName('BsSubTri').AsFloat);

      CcF001.FieldByName('VrConNot').AsString :=
                                        FormatFloat('0.00', CcF001.FieldByName('VrConNot').AsFloat);

      CcF001.FieldByName('VrIpi').AsString    :=
                                        FormatFloat('0.00', CcF001.FieldByName('VrIpi'   ).AsFloat);

    //FRETE INCIDE NA BASE DE CALCULO DO ICMS-----------------------------------
      if CcF001.FieldByName('SnFreIcm').AsString = 'S' then
      begin
            pBsIcms := pBsIcms +  CcF001.FieldByName('VrFrete').AsFloat;
            pVrIcms := pVrIcms + (CcF001.FieldByName('VrFrete').AsFloat *
                                 (CcF001.FieldByName('ALIQICMS').AsFloat / 100));
      end;

    //DESPESAS E ACESSORIOS INCIDE NA BASE DE CALCULO DO ICMS-------------------
      if CcF001.FieldByName('SNDESPIC').AsString = 'S' then
      begin
            pBsIcms := pBsIcms + CcF001.FieldByName('VRDESACE').AsFloat;
            pVrIcms := pVrIcms + (CcF001.FieldByName('VRDESACE').AsFloat *
                                 (CcF001.FieldByName('ALIQICMS').AsFloat / 100));
      end;
end;

procedure TMov0025.CarregarParametrosEvento(pCdEvento: String;
                                            var pQtParcel, pNrPriPar, pNrIntPar: Integer;
                                            var pSnAcuPri: String; var pNrDiaFix: Integer);
var lCpG009 : TClientDataSet;
begin
      try
          lCpG009 := TClientDataSet.Create(Self);

          lCpG009.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(190,
                                                            '<%CdEvento%>;' + pCdEvento, Self.Name);
          pQtParcel := lCpG009.FieldByName('QtParcel').AsInteger;
          pNrPriPar := lCpG009.FieldByName('NrDiaVen').AsInteger;
          pNrIntPar := lCpG009.FieldByName('QtDiaPar').AsInteger;
          pSnAcuPri := lCpG009.FieldByName('SnPriAcu').AsString;
          pNrDiaFix := lCpG009.FieldByName('DtDiaVen').AsInteger;
      finally
          FreeAndNil(lCpG009);
      end;
end;

procedure TMov0025.CarregarParcelas(pCdEvento: String; pVrConNot: Real; pDtEmiNot: TDate);
var lVrConta  : Real;
    lNrOrdem  : Integer;
    lNoParcel : Integer;
    lDtEmissa : TDate;
    lDtParcel : TDateTime;
    lDtDiaVen : Integer;
    lVrInterv : Integer;
    lVrInPrPa : Integer;
    lVrDocume : Real;
    lSnAcuPri : String;
begin
      if (not CcN003.Active) or
         (CcN003.Active and (MessageDlg('Deseja recalcular as parcelas?', mtConfirmation,
                                                                    [mbyes, mbno], 0) = mryes)) then
      begin
            lVrConta  := pVrConNot;
            lDtEmissa := pDtEmiNot;
            lDtParcel := pDtEmiNot;

            CarregarParametrosEvento(pCdEvento, lNoParcel, lVrInPrPa, lVrInterv,
                                     lSnAcuPri, lDtDiaVen);

            CriarTabelaContasPagar;

            for lNrOrdem := 1 to lNoParcel do
            begin
                  CalcularDadosDocumento(lNrOrdem,  lNoParcel, lDtEmissa, lDtParcel,
                                         lDtDiaVen, lVrInterv, lVrInPrPa, lVrConta,
                                         lVrDocume, lSnAcuPri);

                  CcN003.Append;
                  CcN003.FieldByName('NrOrdem' ).AsInteger   := lNrOrdem;
                  CcN003.FieldByName('DtVencto').AsDateTime  := lDtParcel;
                  CcN003.FieldByName('VrDocto' ).AsFloat     := lVrDocume;
                  CcN003.Post;
            end;
      end;
end;

procedure TMov0025.CalcularDadosDocumento(var pNrOrdem, pNoParcel: Integer; var pDtEmissa: TDate;
                                     var pDtParcel: TDateTime; var pDtDiaVen, pVrInterv,
                                         pVrInPrPa: Integer; var pVrConta, pVrDocume: Real;
                                     var pSnAcuPri: String);
begin
      CalcularDadosParcela(pNrOrdem, pNoParcel, pDtDiaVen, pVrInPrPa,
                           pVrInterv, pVrConta,  pSnAcuPri, pVrDocume, pDtParcel);
end;

procedure TMov0025.CalcularDadosParcela(pNrParcel : Integer;     pQtParcel : Integer;
                                        pNrDiaFix : Integer;     pNrPriPar : Integer;
                                        pNrIntPar : Integer;     pVrDuplic : Real;
                                        pSnAcuPri : String;  var pVrParcel : Real;
                                    var pDtParcel : TDateTime);

  function CalcularDtVenFix(pDtParcel : TDateTime; pNrDiaFix : Integer;
                                                   pNrPriPar : Integer): TDateTime;
  var lDtDia : Word;
      lDtMes : Word;
      lDtAno : Word;
  begin
        DecodeDate(pDtParcel, lDtAno, lDtMes, lDtDia);

        if (lDtMes = 2) and
           (pNrDiaFix > 28) then
        begin
              pNrDiaFix := 28;
        end;

        Result := EncodeDate(lDtAno, lDtMes, pNrDiaFix);

        if Result <= (pDtParcel + pNrPriPar) then
        begin
              Result := IncMonth(Result, 1);
        end;
  end;

begin
      if pNrParcel = 1 then
      begin
            pVrParcel := pVrDuplic / pQtParcel;
            pVrParcel := StrToFloat(FormatFloat('0.00', pVrParcel));

            if (pSnAcuPri = 'S') then
            begin
                  pVrParcel := pVrDuplic - (pVrParcel * (pQtParcel-1));
            end;

            if pNrDiaFix = 0 then
            begin
                  pDtParcel := pDtParcel + pNrPriPar;
            end else
            begin
                  pDtParcel := CalcularDtVenFix(pDtParcel, pNrDiaFix, pNrPriPar);
            end;

      end else
      begin
            if (pSnAcuPri = 'S') then
            begin
                  if pNrParcel = 2 then
                  begin
                        pVrParcel := pVrDuplic / pQtParcel;
                        pVrParcel := StrToFloat(FormatFloat('0.00', pVrParcel));
                  end;
            end else
            begin
                  if (pNrParcel = pQtParcel) and ((pQtParcel * pVrParcel) <> pVrDuplic) then
                  begin
                        pVrParcel := pVrDuplic - (pVrParcel * (pQtParcel-1));
                  end;
            end;

            if pNrDiaFix = 0 then
            begin
                  pDtParcel := pDtParcel + pNrIntPar;
            end else
            begin
                  pDtParcel := CalcularDataVenFixa(pDtParcel, pNrDiaFix, 0);
            end;
      end;
end;

function TMov0025.CalcularDataVenFixa(const pDtVenAnt: TDateTime; pNrDiaFix: Integer;
                                            pQtDiaVen: Integer): TDateTime;
var lDtDia : Word;
    lDtMes : Word;
    lDtAno : Word;
begin
      DecodeDate(pDtVenAnt, lDtAno, lDtMes, lDtDia);
      Result := EncodeDate(lDtAno, lDtMes, pNrDiaFix);

      if Result <= (pDtVenAnt + pQtDiaVen) then
      begin
            Result := IncMonth(Result, 1);
      end;
end;

procedure TMov0025.FreteIncideIpi(pSnFreIpi: String; pVrFrete: Real; pVrTotal: Real;
                                  pCcF002: tDataSet; var pVrIpi: Real);
var lDsBookMa : TBookMark;
    lPcIpi    : Real;
    lTtProdut : Real;
begin
      if (pSnFreIpi = 'S') then
      begin
            lDsBookMa := pCcF002.GetBookmark;
            pCcF002.DisableControls;

            pCcF002.First;

            while not pCcF002.Eof do
            begin
                  lPcIpi    := pCcF002.FieldByName('TxIpi').AsFloat / 100;

                  lTtProdut := pCcF002.FieldByName('QtProdut').AsFloat *
                               pCcF002.FieldByName('VrUnitar').AsFloat;

                  pVrIpi   := pVrIpi + ((pVrFrete * (lTtProdut / pVrTotal)) * lPcIpi);

                  pCcF002.Next;
            end;

            pCcF002.GotoBookmark(lDsBookMa);
            pCcF002.EnableControls;
      end;
end;

procedure TMov0025.ValidaCodOperacao;
begin
      if CcF002.Active then
      begin
            CcF002.DisableControls;
            CcF002.First;

            while not CcF002.Eof do
            begin
                  if CcF002.FieldByName('NRSEQFIS').AsInteger = 0 then
                  begin
                        CcF002.Edit;
                        CcF002.FieldByName('NRSEQFIS').AsInteger :=
                                                           CcF001.FieldByName('NRSEQFIS').AsInteger;
                        CcF002.Post;
                  end;

                  CcF002.Next;
            end;

            CcF002.EnableControls;
      end;
end;

procedure TMov0025.VerificarCodigoBarra(pCdBarra : String; pNrDocto :String; pNrOrdem :String);
var lCdBarBan : String;
    lCdBarMoe : String;
    lCdBarDig : String;
    lNrBarVen : String;
    lVrBarDoc : String;
    lNrBarLiv : String;
    lCdDigito : Integer;
begin
      if length(pCdBarra) = 44 then
      begin
            lCdBarBan := Copy(pCdBarra, 1,  3);
            lCdBarMoe := Copy(pCdBarra, 4,  1);
            lCdBarDig := Copy(pCdBarra, 5,  1);
            lNrBarVen := Copy(pCdBarra, 6,  4);
            lVrBarDoc := Copy(pCdBarra, 10, 8) + Copy(pCdBarra, 18, 2);
            lNrBarLiv := Copy(pCdBarra, 20, 25);

            BrvDigito.Codigo := lCdBarBan + lCdBarMoe + lNrBarVen + lVrBarDoc + lNrBarLiv;
            lCdDigito := StrToInt(BrvDigito.Digito);
      end else
      begin
            if length(pCdBarra) = 47 then
            begin
                  lCdBarBan := Copy(pCdBarra, 1,  3);
                  lCdBarMoe := Copy(pCdBarra, 4,  1);
                  lCdBarDig := Copy(pCdBarra, 33, 1);
                  lNrBarVen := Copy(pCdBarra, 34, 4);
                  lVrBarDoc := Copy(pCdBarra, 38, 8) + Copy(pCdBarra, 46, 2);
                  lNrBarLiv := Copy(pCdBarra, 5,  5) + Copy(pCdBarra, 11, 10) +
                              Copy(pCdBarra, 22, 10);

                  BrvDigito.Codigo := lCdBarBan + lCdBarMoe + lNrBarVen + lVrBarDoc + lNrBarLiv;
                  lCdDigito := StrToInt(BrvDigito.Digito);
            end else
            begin
                  raise Exception.Create('Tamanho do código de barras incorreto.');
            end;
      end;

      if (lCdDigito <> StrToInt(lCdBarDig)) then
      begin
            raise Exception.Create('Código de barra do documento ' + pNrDocto + ' ordem ' +
                                    pNrOrdem + ' esta incorreto.');
      end;
end;

procedure TMov0025.BtnSalvarClick(Sender: TObject);
var VrDocto  : Real;
    VrNota   : Real;
    VrCartei : Real;
    DsMensag : String;
begin
      VerificaParametros;

      BtnSalvar.Setfocus;

      DsMensag := 'Deseja realmente continuar?';
       if ((CbxPIS.Checked) and
           (CcF001.FieldByName('BSPIS').AsFloat  < CcF001.FieldByName('VrConNot').AsFloat)) or
           ((CbxCOFINS.Checked) and
           (CcF001.FieldByName('BSCOFINS').AsFloat < CcF001.FieldByName('VrConNot').AsFloat)) or
           ((CbxCSLL.Checked) and
           (CcF001.FieldByName('BSCSLL').AsFloat < CcF001.FieldByName('VrConNot').AsFloat)) or
           ((CbxIRRF.Checked) and
           (CcF001.FieldByName('BSIRRF').AsFloat < CcF001.FieldByName('VrConNot').AsFloat)) or
           ((CbxISSQN.Checked) and
           (CcF001.FieldByName('BSISSQN').AsFloat < CcF001.FieldByName('VrConNot').AsFloat)) or
           ((CbxINSS.Checked) and
           (CcF001.FieldByName('BSINSS').AsFloat < CcF001.FieldByName('VrConNot').AsFloat)) then
      begin
            DsMensag := 'ATENÇÃO!!! Foram encontradas bases de cálculo com valor inferior ' +
                        'ao total da nota fiscal. Deseja continuar mesmo assim?';
      end;

      VrDocto := 0;

      VrCartei := (CcF001.FieldByName('VrConNot').AsFloat) -
                  (CcF001.FieldByName('VRPIS'   ).AsFloat  +
                   CcF001.FieldByName('VRCOFINS').AsFloat  +
                   CcF001.FieldByName('VRCSLL'  ).AsFloat  +
                   CcF001.FieldByName('VRIRRF'  ).AsFloat  +
                   CcF001.FieldByName('VRISSQST').AsFloat  +
                   CcF001.FieldByName('VRINSS'  ).AsFloat);

      if (CcF002.Active) and (CcF002.State in [DsInsert, DsEdit]) then
      begin
            CcF002.Post;
      end;

      if ValidarNotaFiscal = 'Erros' then
      begin
            raise Exception.Create('Foram encontradas inconsistências.');
      end;

      ValidaCodOperacao();

      if (EdtStGerDup.Text = 'S') and (VrCartei > 0) then
      begin
            if not CcN003.Active then
            begin
                  CarregarParcelas(CcF001.FieldByName('CdEvento').AsString,
                                   VrCartei,
                                   CcF001.FieldByName('DtEmiNot').AsDateTime);
            end;

            CcN003.First;

            while not CcN003.Eof do
            begin
                  if CcN003.FieldByName('CdBarra').AsString <> '' then
                  begin
                        VerificarCodigoBarra(CcN003.FieldByName('CdBarra').AsString,
                                             CcF001.FieldByName('NrNota' ).AsString,
                                             CcN003.FieldByName('NrOrdem').AsString);
                  end;

                  VrDocto := VrDocto + CcN003.FieldByName('VrDocto').AsFloat;

                  CcN003.Next;
            end;

            VrDocto := StrToFloat(FormatFloat('#0.00', VrDocto));
            VrNota  := StrToFloat(FormatFloat('#0.00', VrCartei));

            if VrDocto <> VrNota then
            begin
                  PgcDadosNF.ActivePageIndex := 4;
                  if DbgN003.CanFocus then
                  begin
                        DbgN003.SetFocus;
                  end;
                  raise Exception.Create('Valor total da nota incorreto, em relação ao valor '+
                                         'total das parcelas.');
            end;
      end;

      gDsComple.text :=  gDsComple.text + ' ' + EdtRsTitula.Text;

      CcN002.Append;
      CcN002.FieldByName('NRCONTA' ).AsInteger := DmSrvApl.BrvDicionario.ProxNumeroSequencial(
                                                                                 'N002', 'NRCONTA');
      CcN002.FieldByName('CDEMPRES').AsInteger := CcF001.FieldByName('CdEmpres').AsInteger;
      CcN002.FieldByName('CDFORPAG').AsInteger := StrToInt(EdtCdForPag.Text);
      CcN002.FieldByName('CDTITULA').AsInteger := CcF001.FieldByName('CdTitula').AsInteger;
      CcN002.FieldByName('CDUSUARI').AsInteger := DmSrvApl.BrvDicionario.UserCode;
      CcN002.FieldByName('DTEMIDOC').AsDateTime:= CcF001.FieldByName('DTEMINOT').AsDateTime;
      CcN002.FieldByName('DTPROCES').AsDateTime:= DmSrvApl.BrvDicionario.DataHoraServer;
      CcN002.FieldByName('NMARQEDI').AsString  := '';
      CcN002.FieldByName('NRDOCTO' ).AsInteger := CcF001.FieldByName('NrNota').AsInteger;
      CcN002.FieldByName('NRPREFAT').AsInteger := 0;
      CcN002.FieldByName('TPCONTA' ).AsString  := 'P';
      CcN002.FieldByName('VRORIGEM').AsFloat   := CcF001.FieldByName('VrConNot').AsInteger;
      CcN002.Post;

      CcN003.First;

      while not CcN003.eof do
      begin
            CcN003.Edit;
            CcN003.FieldByName('NrConta').AsInteger := CcN002.FieldByName('NrConta').AsInteger;
            CcN003.Post;

            CcN003.Next;
      end;

      if MessageDlg(DsMensag, mtConfirmation, [mbYes, mbNo], 0) = mrYes then
      begin
            CcF001.Edit;
            CcF001.FieldByName('NrSeqDoc').AsInteger :=
                                    DmSrvApl.BrvDicionario.ProxNumeroSequencial('F001', 'NrSeqDoc');
            CcF001.Post;

            try
                CcF002.AfterPost := nil;
                CcF002.First;

                while not CcF002.Eof do
                begin
                      CcF002.Edit;
                      CcF002.FieldByName('NRSEQDOC').AsInteger :=
                                                           CcF001.FieldByName('NRSEQDOC').AsInteger;
                      CcF002.FieldByName('CDEMPRES').AsInteger :=
                                                           CcF001.FieldByName('CDEMPRES').AsInteger;
                      CcF002.FieldByName('CDTITULA').AsInteger :=
                                                           CcF001.FieldByName('CDTITULA').AsInteger;
                      CcF002.FieldByName('NRNOTA'  ).AsInteger :=
                                                           CcF001.FieldByName('NRNOTA'  ).AsInteger;
                      CcF002.FieldByName('DSMODENF').AsString  :=
                                                           CcF001.FieldByName('DSMODENF').AsString;
                      CcF002.FieldByName('NRSERINF').AsString  :=
                                                           CcF001.FieldByName('NRSERINF').AsString;
                      CcF002.Post;

                      CcF002.Next;
                end;
            finally
                CcF002.AfterPost := CcF002AfterPost;
            end;

            ContabilizarRetencoes;

            FinalizarDigitacaoNota;
            PgcNF.ActivePageIndex := 0;
            BtnAtualizarClick(BtnAtualizar);
      end;
end;

procedure TMov0025.ContabilizaRetencao(pDsCampo: String; pNrParam : String; var pNrLancto: Integer);
begin
      if (CcF001.FieldByName(pDsCampo).AsFloat > 0) then
      begin
            LocalizarParametro(pNrParam);

            CcB004FinNot.Append;
            CcB004FinNot.FieldByName('nrlancto').Asinteger := pnrLancto;
            CcB004FinNot.FieldByName('CdEmpres').Asinteger :=
                                                           CcF001.FieldByName('CdEmpres').Asinteger;
            CcB004FinNot.FieldByName('dtlancto').AsDateTime:= DmSrvApl.BrvDicionario.DataHoraServer;
            CcB004FinNot.FieldByName('nrconcre').Asinteger :=
                                                           CpB012.FieldByName('nrconcre').Asinteger;
            CcB004FinNot.FieldByName('nrcondeb').Asinteger :=
                                                           CpB012.FieldByName('nrcondeb').Asinteger;
            CcB004FinNot.FieldByName('vrlancto').AsFloat   :=
                                                           CcF001.FieldByName(pDsCampo  ).AsFloat;
            CcB004FinNot.FieldByName('nrdocto' ).AsString  :=
                                                           CcF001.FieldByName('NrNota'  ).AsString;
            CcB004FinNot.FieldByName('cdhistor').Asinteger :=
                                                           CpB012.FieldByName('cdhistor').Asinteger;
            CcB004FinNot.FieldByName('nmformul').Asstring  := Self.Name;
            CcB004FinNot.FieldByName('SnEncerr').Asstring  := 'N';
            CcB004FinNot.FieldByName('NrClaCre').Asstring  :=
                                                            CpB012.FieldByName('NrClaCre').AsString;
            CcB004FinNot.FieldByName('NrClaDeb').Asstring  :=
                                                            CpB012.FieldByName('NrClaDeb').AsString;
            CcB004FinNot.FieldByName('nrplano' ).Asinteger :=
                                                           CpB012.FieldByName('nrplano' ).Asinteger;
            CcB004FinNot.Post;

            if (CpB012.FieldByName('CdCeCuCr').AsInteger > 0) then
            begin
                  CcB008FinNot.Append;
                  CcB008FinNot.FieldByName('nrLancto').AsInteger := pnrLancto;
                  CcB008FinNot.FieldByName('CdCenCus').AsInteger :=
                                                           CpB012.FieldByName('CdCeCuCr').AsInteger;
                  CcB008FinNot.FieldByName('VrLancto').AsFloat   :=
                                                           CcF001.FieldByName(pDsCampo  ).AsFloat;
                  CcB008FinNot.FieldByName('TpLancto').AsString  := 'C';
                  CcB008FinNot.Post;
            end;

            if (CpB012.FieldByName('CdCeCuDe').AsInteger > 0) then
            begin
                  CcB008FinNot.Append;
                  CcB008FinNot.FieldByName('nrLancto').AsInteger := pnrLancto;
                  CcB008FinNot.FieldByName('CdCenCus').AsInteger :=
                                                           CpB012.FieldByName('CdCeCuDe').AsInteger;
                  CcB008FinNot.FieldByName('VrLancto').AsFloat   :=
                                                           CcF001.FieldByName(pDsCampo  ).AsFloat;
                  CcB008FinNot.FieldByName('TpLancto').AsString  := 'D';
                  CcB008FinNot.Post;
            end;

            inc(pNrLancto);
      end;
end;

procedure TMov0025.ContabilizarRetencoes;
var lnrLancto : Integer;
begin
      CcB004.First;

      while not CcB004.eof do
      begin
            CcB004.Edit;
            CcB004.FieldByName('nrdocto').AsString := CcF001.FieldByName('NrNota').AsString;
            CcB004.Post;

            CcB004.Next;
      end;

      CcB004.First;

      CcB004FinNot.Data := CcB004.Data;
      CcB008FinNot.Data := CcB008.Data;

      lnrLancto := CcB004FinNot.RecordCount + 1;

      VerificaParametros;

      //PIS ----------------------------------------------------------------------------------------
      ContabilizaRetencao('VRPIS'   , '4', lNrLancto);

      //COFINS -------------------------------------------------------------------------------------
      ContabilizaRetencao('VRCOFINS', '5', lNrLancto);

      //CSLL ---------------------------------------------------------------------------------------
      ContabilizaRetencao('VRCSLL'  , '6', lNrLancto);

      //IRRF ---------------------------------------------------------------------------------------
      ContabilizaRetencao('VRIRRF'  , '7', lNrLancto);

      //ISSQN --------------------------------------------------------------------------------------
      ContabilizaRetencao('VRISSQST', '8', lNrLancto);

      //INSS --------------------------------------------------------------------------------------
      ContabilizaRetencao('VRINSS'  , '9', lNrLancto);
end;

procedure TMov0025.FinalizarDigitacaoNota;
var lCpParCon : TClientDataSet;
    lConexao  : TSDmAdmClient;
    lDsResult : AnsiString;
begin
      try
          lCpParCon := TClientDataSet.Create(Self);

          lCpParCon.FieldDefs.Clear;
          lCpParCon.FieldDefs.Add('B004'    ,   ftMemo,  0);
          lCpParCon.FieldDefs.Add('B008'    ,   ftMemo,  0);
          lCpParCon.FieldDefs.Add('NmFormul', ftString, 20);
          lCpParCon.FieldDefs.Add('CdUsuari', ftInteger, 0);
          lCpParCon.FieldDefs.Add('NrPlano' , ftInteger, 0);
          lCpParCon.CreateDataSet;

          lCpParCon.Append;
          lCpParCon.FieldByName('B004'    ).Value    := CcB004FinNot.XMLData;
          lCpParCon.FieldByName('B008'    ).Value    := CcB008FinNot.XMLData;
          lCpParCon.FieldByName('NmFormul').AsString := Self.Name;
          lCpParCon.FieldByName('CdUsuari').AsInteger:= DmSrvApl.BrvDicionario.UserCode;
          lCpParCon.FieldByName('NrPlano' ).AsString :=
                                     DmSrvApl.BrvDicionario.RetornaValorColunaTabela('B013',
                                    'NrPlano', 'CdEmpres', CcF001.FieldByName('CdEmpres').AsString);
          lCpParCon.Post;

          try
              BrvServerProgress.Start(Self.Caption, 'Gravando Nota', 100, 100);
              lConexao  := TSDmAdmClient.Create(DmSrvApl.SrvAplica.DBXConnection);
              lDsResult := lConexao.FinalizarDigitacaoNota(DmSrvApl.BrvDicionario.Credencial,
                                                           CcF001.Data,
                                                           CcF002.Data,
                                                           CcN002.Data,
                                                           CcN003.Data,
                                                           lCpParCon.Data);
          finally
              BrvServerProgress.Stop;
          end;

          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
      finally
          FreeAndNil(lCpParCon);
          FreeAndNil(lConexao);
      end;
end;

procedure TMov0025.CalculaRetencao(pCheck : TCheckBox; pNrParam, pCdEmpres : Integer;
                                   pCampoBs, pCampoPc, pCampoVr, pDsAliq : String);
var lPcAliq : Real;
begin
      if pCheck.Checked then
      begin
            lPcAliq := DmSrvApl.BrvDicionario.ParametroDaEmpresa(pNrParam, pCdEmpres);

            CcF001.FieldByName(pCampoBs).ReadOnly := False;
            CcF001.FieldByName(pCampoPc).ReadOnly := False;
            CcF001.FieldByName(pCampoVr).ReadOnly := False;

            if (lPcAliq > 0) then
            begin
                  CcF001.FieldByName(pCampoBs).AsFloat := CcF001.FieldByName('VrConNot').AsFloat;
                  CcF001.FieldByName(pCampoPc).AsFloat := lPcAliq;
                  CcF001.FieldByName(pCampoVr).AsFloat :=
                                             (CcF001.FieldByName(pCampoBs).AsFloat * lPcAliq) / 100;
            end else
            begin
                  ShowMessage('Parâmetro fiscal não encontrado para o cálculo [ ' + pDsAliq + ' ]');
            end;
      end else
      begin
            CcF001.FieldByName(pCampoBs).ReadOnly := False;
            CcF001.FieldByName(pCampoPc).ReadOnly := False;
            CcF001.FieldByName(pCampoVr).ReadOnly := False;
            CcF001.FieldByName(pCampoBs).AsFloat  := 0;
            CcF001.FieldByName(pCampoPc).AsFloat  := 0;
            CcF001.FieldByName(pCampoVr).AsFloat  := 0;
            CcF001.FieldByName(pCampoBs).ReadOnly := True;
            CcF001.FieldByName(pCampoPc).ReadOnly := True;
            CcF001.FieldByName(pCampoVr).ReadOnly := True;
      end;
end;

procedure TMov0025.CbxINSSClick(Sender: TObject);
begin
      inherited;
      CalculaRetencao(CbxINSS   , 25, CcF001.FieldByName('CdEmpres').AsInteger,
                      'BSINSS'  , 'PCALIINS'  , 'VRINSS'  , 'INSS');
end;

procedure TMov0025.CbxIRRFClick(Sender: TObject);
begin
      inherited;
      CalculaRetencao(CbxIRRF   , 24, CcF001.FieldByName('CdEmpres').AsInteger,
                      'BSIRRF'  , 'PCIRRF'  , 'VRIRRF'  , 'IRRF');
end;

procedure TMov0025.CbxISSQNClick(Sender: TObject);
begin
      inherited;
      CalculaRetencao(CbxISSQN  , 26, CcF001.FieldByName('CdEmpres').AsInteger,
                      'BSISSQN'  , 'PCALIISS'  , 'VRISSQST'  , 'ISSQN');
end;

procedure TMov0025.CbxCSLLClick(Sender: TObject);
begin
      inherited;
      CalculaRetencao(CbxCSLL   , 23, CcF001.FieldByName('CdEmpres').AsInteger,
                      'BSCSLL'  , 'PCCSLL'  , 'VRCSLL'  , 'CSLL');
end;

procedure TMov0025.CbxCOFINSClick(Sender: TObject);
begin
      inherited;
      CalculaRetencao(CbxCOFINS , 22, CcF001.FieldByName('CdEmpres').AsInteger,
                      'BSCOFINS', 'PCCOFINS', 'VRCOFINS', 'COFINS');
end;

procedure TMov0025.CbxPISClick(Sender: TObject);
begin
      inherited;
      CalculaRetencao(CbxPIS    , 21, CcF001.FieldByName('CdEmpres').AsInteger,
                      'BSPIS'   , 'PCPIS'   , 'VRPIS'   , 'PIS'   );
end;

procedure TMov0025.CbxSnConhecClick(Sender: TObject);
begin
      inherited;
      GrbTransp.Enabled := not CbxSnConhec.Checked;
end;

procedure TMov0025.CbxSnItensClick(Sender: TObject);
var QtRegist : Integer;
    NrIndice : Integer;
begin
      inherited;

      TbsItens.Visible    := CbxSnItens.Checked;
      TbsItens.TabVisible := CbxSnItens.Checked;

      if (CbxSnItens.Checked) then
      begin
            PgcDadosNF.ActivePageIndex := 0;
      end;

      if  CbxSnItens.Checked then // com itens
      begin
            CbxSnConhec.Checked := False;
            CbxSnConhecClick(Self);
      end else
      begin
            if (CcF002.Active) then
            begin
                  QtRegist := CcF002.RecordCount;
                  if QtRegist  = 0 then
                  begin
                        CbxSnItens.Checked  := False;
                        TbsItens.Visible := CbxSnItens.Checked;
                        TbsItens.TabVisible := CbxSnItens.Checked;
                        PgcDadosNF.ActivePageIndex := 1;
                  end else
                  begin
                        CbxSnItens.Checked := True;
                        raise Exception.create('Não é possível tornar este registro em ' +
                                               'NF sem itens, porque existe(m) '         +
                                         IntToStr(QtRegist) + ' item(ns) cadastrado(s).');
                  end;
            end;
      end;

      CbxSnConhec.Enabled := not CbxSnItens.Checked;
end;

procedure TMov0025.CcB004AfterInsert(DataSet: TDataSet);
begin
      inherited;
      CcB004.FieldByName('NrLancto').AsInteger := CcB004.RecordCount + 1;

      VerificaParametros;
      LocalizarParametro('3');
      CcB004.FieldByName('nrconcre').AsInteger := CpB012.FieldByName('nrconcre').Asinteger;
      CcB004.FieldByName('nmconcre').AsString  := CpB012.FieldByName('nmconcre').AsString;
end;

procedure TMov0025.CcB004AfterScroll(DataSet: TDataSet);
begin
      inherited;

      if (CcB004.RecordCount > 0) then
      begin
            CcB008.Filtered := False;
            CcB008.Filter   := 'NrLancto = ' + CcB004.FieldByName('NrLancto').AsString;
            CcB008.Filtered := True;
      end else
      begin
            CcB008.Filtered := False;
      end;
end;

function TMov0025.ProximoLanctoB004(pCdsB004: OleVariant): Integer;
var lCpB004: TClientDataSet;
begin
      try
          lCpB004 := TClientDataSet.Create(self);
          lCpB004.Data := pCdsB004;

          if (lCpB004.RecordCount = 0) then
          begin
                Result := 1;
          end else
          begin
                Result := lCpB004.RecordCount + 1;
          end;

      finally
          FreeAndNil(lCpB004);
      end;
end;

procedure TMov0025.CcB008AfterInsert(DataSet: TDataSet);
begin
      inherited;
      if (CcB004.State in [dsEdit, dsInsert]) then
      begin
            CcB004.Post;
      end;

      if (CcB004.RecordCount > 0) then
      begin
            CcB008.FieldByName('NrLancto').AsInteger := CcB004.FieldByName('NrLancto').AsInteger;
            CcB008.FieldByName('TpLancto').AsString  := 'D';
      end else
      begin
            raise Exception.Create('Não existem lançamentos contabeis!');
      end;
end;

procedure TMov0025.CcB008BeforePost(DataSet: TDataSet);
begin
      inherited;
      if (CcB008.FieldByName('CdCenCus').AsInteger = 0) then
      begin
            raise Exception.Create('Informe um Centro de Custo!');
      end;
end;

procedure TMov0025.CcF002AfterPost(DataSet: TDataSet);
begin
     inherited;
      SomaTotalProdutos(CcF002.Data);
end;

procedure TMov0025.CcF002BeforePost(DataSet: TDataSet);
begin
      inherited;

      if (CcF002.FieldByName('QtProdut').AsFloat <= 0) then
      begin
            raise Exception.Create('Quantidade tem que ser maior que zero.');
      end;

      if (CcF002.FieldByName('VrUnitar').AsFloat <= 0) then
      begin
            raise Exception.Create('Valor tem que ser maior que zero.');
      end;

      if (CcF002.FieldByName('VrDescon').AsFloat > 0) then
      begin
            CcF002.FieldByName('PCDESCON').AsFloat :=
                                                  (CcF002.FieldByName('VrDescon').AsFloat * 100) /
                                                   CcF002.FieldByName('VrUnitar').AsFloat;
      end;

      CcF002.FieldByName('VRTOTAL').AsFloat := TotalProduto;

      TratarDadosCabecalho(True);
end;

procedure TMov0025.TratarDadosCabecalho(pSnEnable: Boolean);
begin
      CcF001.FieldByName('CdEmpres').ReadOnly := pSnEnable;
      CcF001.FieldByName('CdTitula').ReadOnly := pSnEnable;
      CcF001.FieldByName('NrSeriNF').ReadOnly := pSnEnable;
      CcF001.FieldByName('NrNota'  ).ReadOnly := pSnEnable;
end;

function TMov0025.TotalProduto : Currency;
begin
      Result := (CcF002.FieldByName('QtProdut').AsFloat  * CcF002.FieldByName('VrUnitar').AsFloat) -
                 CcF002.FieldByName('VrDescon').AsFloat;

      Result := StrToFloat(FormatFloat('#0.00', Result));
end;

procedure TMov0025.DbgB004Columns4BrOnAfterConsul(Sender: TObject);
begin
      inherited;
      if (CcB004.FieldByName('NrConDeb').AsString = '') then
      begin
            MessageDlg('Informe uma Conta Débito válida!', mtError, [mbok], 0);
            Abort;
      end;
end;

procedure TMov0025.DbgB004Columns4BrOnBeforeConsul(Sender: TObject);
begin
      inherited;
      if (CcB004.FieldByName('NrPlano').AsInteger > 0) then
      begin
            DbgB004.Columns[4].BrParams.Clear;
            DbgB004.Columns[4].BrParams.Add('<%NrPlano%>;' +CcB004.FieldByName('NrPlano').AsString);
      end else
      begin
            Raise Exception.Create('Informe um Plano de Contas válido!');
      end;
end;

procedure TMov0025.DbgF013DblClick(Sender: TObject);
begin
      inherited;
      BtnLancarClick(nil);
end;

procedure TMov0025.DbgF014Columns0BrOnAfterConsul(Sender: TObject);
begin
      inherited;
      if (CcB004.FieldByName('NrPlano').AsString = '') then
      begin
            MessageDlg('Informe um plano válido!', mtError, [mbok], 0);
            Abort;
      end;
end;

procedure TMov0025.DbgF014Columns2BrOnAfterConsul(Sender: TObject);
begin
      inherited;
      if (CcB004.FieldByName('NrConCre').AsString = '') then
      begin
            MessageDlg('Informe uma Conta Crédito válida!', mtError, [mbok], 0);
            Abort;
      end;
end;

procedure TMov0025.DbgF014Columns2BrOnBeforeConsul(Sender: TObject);
begin
      inherited;
      if (CcB004.FieldByName('NrPlano').AsInteger > 0) then
      begin
            DbgB004.Columns[2].BrParams.Clear;
            DbgB004.Columns[2].BrParams.Add('<%NrPlano%>;' +CcB004.FieldByName('NrPlano').AsString);
      end else
      begin
            Raise Exception.Create('Informe um Plano de Contas válido!');
      end;
end;

procedure TMov0025.DbgF014Columns4BrOnAfterConsul(Sender: TObject);
begin
      inherited;
      if (CcB004.FieldByName('CdHistor').AsString = '') then
      begin
            MessageDlg('Informe um histórico válido!', mtError, [mbok], 0);
            Abort;
      end;
end;

procedure TMov0025.FormCloseQuery(Sender: TObject; var CanClose: Boolean);
begin
  inherited;
begin

      if (PgcNF.ActivePageIndex = 1) then
      begin
            if  MessageDlg('Confirma cancelamento da digitação?', MtConfirmation,
                           [MbYes, MbNo], 0) = IdYes then
            begin
                  CanClose := True;
            end else
            begin
                  CanClose := False;
            end;
      end;

      if CanClose then
      begin
            PgcNF.ActivePageIndex := 0;
            CbxFinali.itemindex   := 0;
            EdtNrChave.text       := '';
            EdtCdEstEmp.text      := '';
            EdtCdEstEmi.text      := '';
            EdtRsTitula.text      := '';
            EdtDsEmpres.text      := '';
            EdtDsEvento.text      := '';
            EdtCdFiscal.text      := '';
            EdtDsFiscal.text      := '';
            CpF013.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(187, '<%CdEmpres%>;' +
                                                  DmSrvApl.BrvDicionario.CorpCommaCodes, self.name);
      end;

      inherited;
end;
end;

procedure TMov0025.FormCreate(Sender: TObject);
begin
      inherited;

      gDsComple := TStringList.Create;
      TbsPreLan.TabVisible  := False;
      TbsNota.TabVisible    := False;
      PgcNF.ActivePageIndex := 0;
      PgcNF.Visible := True;

      CpF013.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(187, '<%CdEmpres%>;' +
                                                  DmSrvApl.BrvDicionario.CorpCommaCodes, self.name);
end;

function TMov0025.LancaDentroDoMes(pCdEmpres: Integer; pDtEntrad: TDateTime): Boolean;
var lConexao  : TSDmAdmClient;
    lCcParam  : TClientDataSet;
    lDsResult : AnsiString;
    lDtDia    : Word;
    lDtMes    : Word;
    lDtAno    : Word;
begin
      try
          Result := False;

          DecodeDate(pDtEntrad, lDtAno, lDtMes, lDtDia);
          lCcParam := TClientDataSet.create(self);
          lCcParam.FieldDefs.Clear;
          lCcParam.FieldDefs.Add('CdEmpres', ftInteger, 0);
          lCcParam.FieldDefs.Add('NrAnoFis', ftInteger, 0);
          lCcParam.FieldDefs.Add('NrMesFis', ftInteger, 0);
          lCcParam.CreateDataSet;

          lCcParam.Append;
          lCcParam.FieldByName('CdEmpres').AsInteger := CcF001.FieldByName('CdEmpres').AsInteger;
          lCcParam.FieldByName('NrAnoFis').AsInteger := lDtAno;
          lCcParam.FieldByName('NrMesFis').AsInteger := lDtMes;
          lCcParam.Post;

          lConexao  := TSDmAdmClient.Create(DmSrvApl.SrvAplica.DBXConnection);
          lDsResult := lConexao.PeriodoContabilValido(DmSrvApl.BrvDicionario.Credencial,
                                                                                     lCcParam.Data);
          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
          Result := True;
      finally
          FreeAndNil(lConexao);
          FreeAndNil(lCcParam);
      end;
end;

initialization
      RegisterClass(TMov0025);

finalization
      UnRegisterClass(TMov0025);

end.

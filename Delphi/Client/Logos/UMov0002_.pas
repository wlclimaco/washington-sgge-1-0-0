unit UMov0002;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, StdCtrls, CheckLst,
  ComCtrls, BrvEdit, Mask, BrvEditDate, BrvEditNum, BrvBitBtn, Grids, DBGrids, DB,
  DBClient, BrvDbGrids, BrvDbGrid, Menus, BrvComboBox, BrvRetCon, UClaSrv, BrvMesAno,
  BrvGeraRelatorio, BrvCheckListBox;

type
  TMov0002 = class(TMov0000)
    PgcFundo: TPageControl;
    TbsFiltro: TTabSheet;
    TbsProces: TTabSheet;
    TbsCarga: TTabSheet;
    Panel3: TPanel;
    DtsFiltro1: TDataSource;
    Splitter2: TSplitter;
    PgcProces: TPageControl;
    TbsRota: TTabSheet;
    TbsCidade: TTabSheet;
    TbsTomado: TTabSheet;
    TbsCTRC: TTabSheet;
    CdsRotas: TClientDataSet;
    DtsRotas: TDataSource;
    DbgRotas: TBrvDbGrid;
    Panel18: TPanel;
    BrvBitBtn2: TBrvBitBtn;
    CdsFiltro1: TClientDataSet;
    CdsFiltro1NmFiltro: TStringField;
    CdsFiltro1DsFiltro: TStringField;
    BrvBitBtn3: TBrvBitBtn;
    CdsFiltro2: TClientDataSet;
    StringField1: TStringField;
    StringField2: TStringField;
    DtsFiltro2: TDataSource;
    DbgCidade: TBrvDbGrid;
    CdsCidade: TClientDataSet;
    DtsCidade: TDataSource;
    Panel2: TPanel;
    BrvBitBtn4: TBrvBitBtn;
    BrvBitBtn5: TBrvBitBtn;
    CdsTotal: TClientDataSet;
    GbxProces: TGroupBox;
    PgbProces: TProgressBar;
    PopRotas: TPopupMenu;
    MarcarTodas1: TMenuItem;
    DesmarcarTodas1: TMenuItem;
    PopCidade: TPopupMenu;
    MenuItem1: TMenuItem;
    MenuItem2: TMenuItem;
    Panel4: TPanel;
    BrvBitBtn6: TBrvBitBtn;
    BrvBitBtn7: TBrvBitBtn;
    CdsPesCTRC: TClientDataSet;
    DtsTomado: TDataSource;
    PopTomado: TPopupMenu;
    MenuItem3: TMenuItem;
    MenuItem4: TMenuItem;
    DbgTomado: TBrvDbGrid;
    CdsFiltro3: TClientDataSet;
    StringField3: TStringField;
    StringField4: TStringField;
    DtsFiltro3: TDataSource;
    Panel5: TPanel;
    BrvBitBtn8: TBrvBitBtn;
    DbgCtrc: TBrvDbGrid;
    StringField5: TStringField;

    CdsTomado: TClientDataSet;
    CdsTomadoCdTomado: TIntegerField;
    CdsTomadoNmTomado: TStringField;
    CdsTomadoNmCidade: TStringField;
    CdsTomadoCdEstado: TStringField;
    CdsTomadoQtCtrc: TIntegerField;
    CdsTomadoNrPeso: TFloatField;
    CdsTomadoVrMercad: TFloatField;
    CdsTomadoQtDiaVen: TIntegerField;
    CdsTomadoDtPreEnt: TDateField;
    CdsTomadoCdCtrc: TMemoField;
    CdsTomadoCdRota: TMemoField;
    CdsTomadoStVencim: TStringField;

    CdsCTRC: TClientDataSet;
    DtsCTRC: TDataSource;
    PopCtrc: TPopupMenu;
    MenuItem5: TMenuItem;
    MenuItem6: TMenuItem;
    CdsFiltro4: TClientDataSet;
    StringField6: TStringField;
    StringField7: TStringField;
    DtsFiltro4: TDataSource;
    BrvBitBtn9: TBrvBitBtn;
    Panel6: TPanel;
    BrvBitBtn10: TBrvBitBtn;
    GroupBox4: TGroupBox;
    Label38: TLabel;
    LblTtMerCar: TLabel;
    LblTtPesCar: TLabel;
    Label41: TLabel;
    Label42: TLabel;
    LblTtCtrCar: TLabel;
    Panel7: TPanel;
    Label10: TLabel;
    EdtCdCarga: TBrvEditNum;
    PgcCarga: TPageControl;
    TbsDadBas: TTabSheet;
    Label16: TLabel;
    Label19: TLabel;
    Label20: TLabel;
    Label23: TLabel;
    Label25: TLabel;
    Label28: TLabel;
    Label29: TLabel;
    Label32: TLabel;
    LblDsTpVeic: TBrvRetCon;
    EdtDsCarga: TEdit;
    EdtNrMalote: TBrvEditNum;
    EdtTpVeicul: TBrvEditNum;
    EdtKmPreCar: TBrvEditNum;
    EdtDtPreRet: TBrvEditDate;
    EdtQtCapPes: TBrvRetCon;
    EdtQtCapVol: TBrvRetCon;
    TabSheet2: TTabSheet;
    TabSheet3: TTabSheet;
    EdtCdMotori: TBrvEditNum;
    LblNmMotori: TBrvRetCon;
    EdtCdVeicul: TBrvEditNum;
    LblDsVeicul: TBrvRetCon;
    Label44: TLabel;
    EdtCdMotor1: TBrvEditNum;
    LblNmMotor1: TBrvRetCon;
    EdtCdVeicu1: TBrvEditNum;
    LblDsVeicu1: TBrvRetCon;
    Label47: TLabel;
    EdtCdMotor2: TBrvEditNum;
    LblNmMotor2: TBrvRetCon;
    EdtCdVeicu2: TBrvEditNum;
    LblDsVeicu2: TBrvRetCon;
    Label51: TLabel;
    EdtCdMotor3: TBrvEditNum;
    LblNmMotor3: TBrvRetCon;
    EdtCdVeicu3: TBrvEditNum;
    LblDsVeicu3: TBrvRetCon;
    Label55: TLabel;
    Label56: TLabel;
    Label58: TLabel;
    EdtCdEmpEsc: TBrvEditNum;
    LblNmEmpEsc: TBrvRetCon;
    CbxTpEscolt: TBrvComboBox;
    DbgConhec: TBrvDBGrid;
    DtsCarga: TDataSource;
    CdsCarga: TClientDataSet;
    BrvBitBtn11: TBrvBitBtn;
    BrvBitBtn12: TBrvBitBtn;
    BrvBitBtn13: TBrvBitBtn;
    BrvBitBtn14: TBrvBitBtn;
    BrvBitBtn15: TBrvBitBtn;
    EdtQtPeso1: TBrvRetCon;
    EdtQtVolum1: TBrvRetCon;
    EdtQtPeso2: TBrvRetCon;
    EdtQtVolum2: TBrvRetCon;
    EdtQtPeso3: TBrvRetCon;
    EdtQtVolum3: TBrvRetCon;
    EdtQtPeso4: TBrvRetCon;
    EdtQtVolum4: TBrvRetCon;
    Label40: TLabel;
    EdtCdEmpDes: TBrvEditNum;
    LblDsEmpDes: TBrvRetCon;
    CdsRota: TClientDataSet;
    SbtImprim: TBrvSpeedButton;
    BrvGerRel: TBrvGeraRelatorio;
    PnlEmpres: TPanel;
    LblEmpres: TLabel;
    EdtCdEmpres: TBrvEditNum;
    LblDsEmpres: TBrvRetCon;
    BtnProsse: TBrvBitBtn;
    PnlFiltro: TPanel;
    Label12: TLabel;
    Label1: TLabel;
    Label13: TLabel;
    Label11: TLabel;
    Label57: TLabel;
    Label22: TLabel;
    Label24: TLabel;
    Label26: TLabel;
    LblNmCidade: TBrvRetCon;
    LblRsDestin: TBrvRetCon;
    LblRsTomado: TBrvRetCon;
    LblDsPraca: TBrvRetCon;
    EdtCdRota: TBrvEditNum;
    EdtCdTomado: TBrvEditNum;
    EdtCdDestin: TBrvEditNum;
    EdtDtPreIni: TBrvEditDate;
    EdtDtPreFim: TBrvEditDate;
    EdtCpCidade: TBrvEdit;
    Panel1: TPanel;
    BrvBitBtn1: TBrvBitBtn;
    BtnVoltar: TBrvBitBtn;
    GroupBox6: TGroupBox;
    EdtTtCtrTom: TBrvEditNum;
    Label54: TLabel;
    EdtTtPesTom: TBrvEditNum;
    Label59: TLabel;
    Label60: TLabel;
    EdtTtMerTom: TBrvEditNum;
    GroupBox7: TGroupBox;
    Label61: TLabel;
    Label62: TLabel;
    Label63: TLabel;
    EdtTtCtToMa: TBrvEditNum;
    EdtTtPeToMa: TBrvEditNum;
    EdtTtMeToMa: TBrvEditNum;
    CdsTotMar: TClientDataSet;
    GroupBox2: TGroupBox;
    Label2: TLabel;
    Label3: TLabel;
    Label5: TLabel;
    EdtTtCtrRot: TBrvEditNum;
    EdtTtPesRot: TBrvEditNum;
    EdtTtMerRot: TBrvEditNum;
    GroupBox3: TGroupBox;
    Label8: TLabel;
    Label14: TLabel;
    Label15: TLabel;
    EdtTtCtRoMa: TBrvEditNum;
    EdtTtPeRoMa: TBrvEditNum;
    EdtTtMeRoMa: TBrvEditNum;
    GroupBox1: TGroupBox;
    Label4: TLabel;
    Label7: TLabel;
    Label9: TLabel;
    EdtTtCtrCid: TBrvEditNum;
    EdtTtPesCid: TBrvEditNum;
    EdtTtMerCid: TBrvEditNum;
    GroupBox5: TGroupBox;
    Label43: TLabel;
    Label45: TLabel;
    Label46: TLabel;
    EdtTtCtCiMa: TBrvEditNum;
    EdtTtPeCiMa: TBrvEditNum;
    EdtTtMeCiMa: TBrvEditNum;
    GroupBox8: TGroupBox;
    Label6: TLabel;
    Label17: TLabel;
    Label18: TLabel;
    EdtTtCtrCtr: TBrvEditNum;
    EdtTtPesCtr: TBrvEditNum;
    EdtTtMerCtr: TBrvEditNum;
    GroupBox9: TGroupBox;
    Label49: TLabel;
    Label50: TLabel;
    Label53: TLabel;
    EdtTtCtCtMa: TBrvEditNum;
    EdtTtPeCtMa: TBrvEditNum;
    EdtTtMeCtMa: TBrvEditNum;
    Panel26: TPanel;
    Panel8: TPanel;
    Label64: TLabel;
    Panel11: TPanel;
    Label66: TLabel;
    Panel9: TPanel;
    Label65: TLabel;
    Panel27: TPanel;
    Label80: TLabel;
    CdsParams: TClientDataSet;
    CdsParamsTpVeicul: TIntegerField;
    CdsParamsCdVeicul: TStringField;
    CdsParamsCdsCtrc: TBlobField;
    CblCdEstado: TBrvCheckListBox;
    CblTpTransp: TBrvCheckListBox;
    Label67: TLabel;
    EdtNrPesCom: TBrvEditNum;
    Label68: TLabel;
    Label69: TLabel;
    Label70: TLabel;
    Label71: TLabel;
    DbgFiltro: TBrvDbGrid;
    procedure FormCreate(Sender: TObject);
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure BrvBitBtn1Click(Sender: TObject);
    procedure BrvBitBtn2Click(Sender: TObject);
    procedure BrvBitBtn3Click(Sender: TObject);
    procedure BrvBitBtn4Click(Sender: TObject);
    procedure CdsRotasBeforeDelete(DataSet: TDataSet);
    procedure MarcarTodas1Click(Sender: TObject);
    procedure DesmarcarTodas1Click(Sender: TObject);
    procedure MenuItem1Click(Sender: TObject);
    procedure MenuItem2Click(Sender: TObject);
    procedure MenuItem3Click(Sender: TObject);
    procedure MenuItem4Click(Sender: TObject);
    procedure BrvBitBtn6Click(Sender: TObject);
    procedure BrvBitBtn7Click(Sender: TObject);
    procedure BrvBitBtn8Click(Sender: TObject);
    procedure MenuItem5Click(Sender: TObject);
    procedure MenuItem6Click(Sender: TObject);
    procedure BrvBitBtn9Click(Sender: TObject);
    procedure BrvBitBtn10Click(Sender: TObject);
    procedure BrvBitBtn14Click(Sender: TObject);
    procedure BrvBitBtn15Click(Sender: TObject);
    procedure BrvBitBtn5Click(Sender: TObject);
    procedure SbtImprimClick(Sender: TObject);
    procedure BtnProsseClick(Sender: TObject);
    procedure BtnVoltarClick(Sender: TObject);
    procedure EdtCdRotaBrOnBeforeConsulta(Sender: TObject);
    procedure DbgTomadoCellClick(Column: TColumn);
    procedure DbgRotasCellClick(Column: TColumn);
    procedure DbgCidadeCellClick(Column: TColumn);
    procedure DbgCtrcCellClick(Column: TColumn);
    procedure MenuItem7Click(Sender: TObject);
    procedure MenuItem8Click(Sender: TObject);
  private
    gCdCtrc   : String;
    gCdEstado : AnsiString;
    function TipoSelecionado(var pDsTipSel : String): String;
    procedure CarregarFiltroPrincipal(pDsTipSel : String);
    function ComplementoInstrucaoSqlFiltroPrincipal: String;
    function ComplementoInstrucaoSqlFiltroRotas: String;
    function ComplementoFiltroGeral(TpFiltro : AnsiString) : String;
    procedure CarregarFiltroRotas;
    procedure CarregarFiltroCidades;
    procedure ProcessarTomadores;
    procedure CarregarFiltroTomadores;
    function RotasSelecionadas: String;
    function CidadesSelecionadas: String;
    procedure CarregarFiltroCTRC;
    procedure MostrarCarga(pDsDesCar : AnsiString);
    function FiltroParaCarga: String;
    procedure CarregarConhecimentos;
    function FiltroDoCtrc: String;
    function FiltroDoTomador: String;
    procedure ValidarSalvamento;
    procedure EnviarDadosServidorAplicacao(pStCarga : String);
    procedure CTRCsDosTomadores(var pCdTomado : String; var pCdCtrc : String;
                                var pCdRotas  : String);
    procedure RotaCliente(pCdClient : Integer;        pCdLocEnt : Integer;
                      var pCdRota   : AnsiString; var pDsPraca  : AnsiString);
    function SituacaoVencimento(pQtDiaVen : Integer;
                                pDtAgenda : TDateTime;
                                pStVenAtu : AnsiString) : AnsiString;
    procedure TotalizaMarcados(pCdsGrade    : OleVariant;  pEdtQtCtrc   : TBrvEditNum;
                               pEdtNrPesMer : TBrvEditNum; pEdtVrMercad : TBrvEditNum);
    function RetornaPraca(pCdsGrade : TDataSet) : AnsiString;
    function EstadosMarcados : AnsiString;
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Mov0002: TMov0002;

implementation

uses UDmSrvApl;

{$R *.dfm}

var gDtServer : TDateTime;

procedure TMov0002.BrvBitBtn10Click(Sender: TObject);
begin
      PgcFundo.ActivePage := TbsProces;
end;

procedure TMov0002.BrvBitBtn14Click(Sender: TObject);
begin
      ValidarSalvamento;
      EnviarDadosServidorAplicacao('P');
end;

procedure TMov0002.ValidarSalvamento;
var lConexao  : TSDmTraClient;
    lCdVeicul : AnsiString;
    lDsMenSer : AnsiString;
    lDsComWhe : AnsiString;
    lQtPesCtr : Real;

begin
      if EdtDsCarga.Text = '' then
      begin
            raise Exception.Create('Informe a descrição da carga');
      end;

      if EdtNrMalote.BrAsInteger = 0 then
      begin
            raise Exception.Create('Informe o número do malote');
      end;

      if (EdtTpVeicul.BrAsInteger = 0) and (EdtCdVeicul.BrAsInteger = 0) then
      begin
            raise Exception.Create('Informe um tipo de veículo ou 1 veículo');
      end;

      if CbxTpEscolt.ItemIndex <> 0 then
      begin
            if EdtCdEmpEsc.BrAsInteger = 0 then
            begin
                  raise Exception.Create('Informe a empresa de escolta');
            end;
      end;

      CdsCarga.First;

      if CdsCarga.Eof then
      begin
            raise Exception.Create('Não há conhecimentos na carga');
      end;

      if (EdtTpVeicul.BrAsInteger = 0) and (EdtCdVeicul.BrAsInteger = 0) then
      begin
            raise Exception.Create('Informe o tipo de veículo ou pelos o veículo 1');
      end;

      if EdtCdVeicul.BrAsInteger > 0 then
      begin
            lCdVeicul := EdtCdVeicul.Text;
      end;

      if EdtCdVeicu1.BrAsInteger > 0 then
      begin
            lCdVeicul := lCdVeicul + ',' + EdtCdVeicu1.Text;
      end;

      if EdtCdVeicu2.BrAsInteger > 0 then
      begin
            lCdVeicul := lCdVeicul + ',' + EdtCdVeicu2.Text;
      end;

      if EdtCdVeicu3.BrAsInteger > 0 then
      begin
            lCdVeicul := lCdVeicul + ',' + EdtCdVeicu3.Text;
      end;

      lDsComWhe := '(';
      lQtPesCtr := 0;
      CdsCarga.First;

      while not CdsCarga.eof do
      begin
            lQtPesCtr := lQtPesCtr + CdsCarga.FieldByName('NrPeso').AsFloat;

            lDsComWhe := lDsComWhe +
                  ' (T002.CdEmpres = '      + CdsCarga.FieldByName('CdEmpres').AsString +
                  '  and T002.DsModeNf = "' + CdsCarga.FieldByName('DsModeNf').AsString +
                  '" and T002.NrSeriNf = "' + CdsCarga.FieldByName('NrSeriNf').AsString +
                  '" and T002.CdCtrc   = '  + CdsCarga.FieldByName('CdCtrc').AsString   +
                  ') or ';

            CdsCarga.Next;
      end;

      lDsComWhe := Copy(lDsComWhe, 1, Length(lDsComWhe) -4) + ')';
      lConexao  := TSDmTraClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          lDsMenSer := lConexao.ValidarCarga(DmSrvApl.BrvDicionario.Credencial,
                                             EdtTpVeicul.Text, lCdVeicul, lQtPesCtr,
                                             lDsComWhe);

          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsMenSer);
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TMov0002.BrvBitBtn15Click(Sender: TObject);
begin
      if EdtCdVeicul.BrAsInteger = 0 then
      begin
            raise Exception.Create('Informe um veículo');
      end;
      ValidarSalvamento;

      EnviarDadosServidorAplicacao('G');
end;

procedure TMov0002.EnviarDadosServidorAplicacao(pStCarga : String);
var lConexao  : TSDmTraClient;
    lDsResult : String;
    lCdCarga  : Integer;
begin
      lConexao := TSDmTraClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          lConexao.SalvarCarga(
                   DmSrvApl.BrvDicionario.Credencial, lDsResult, lCdCarga,
                   EdtDsCarga.Text,           EdtNrMalote.BrAsInteger,
                   EdtTpVeicul.BrAsInteger,   EdtKmPreCar.BrAsInteger,
                   EdtDtPreRet.BrAsDateTime,
                   StrToInt(CbxTpEscolt.Values[CbxTpEscolt.ItemIndex]),
                   EdtCdEmpEsc.BrAsInteger,   Name,                     pStCarga,

                   EdtCdVeicul.BrAsInteger,   EdtCdMotori.BrAsInteger,
                   EdtCdVeicu1.BrAsInteger,   EdtCdMotor1.BrAsInteger,
                   EdtCdVeicu2.BrAsInteger,   EdtCdMotor2.BrAsInteger,
                   EdtCdVeicu3.BrAsInteger,   EdtCdMotor3.BrAsInteger, CdsCarga.Data,
                   EdtCdEmpDes.BrAsInteger,   EdtNrPesCom.BrAsFloat);


          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);

          EdtCdCarga.BrAsInteger := lCdCarga;

          MessageDlg('Carga ' + EdtCdCarga.Text + ' processada com sucesso',
                                                               mtInformation, [mbok], 0);

          EdtCdCarga.Text          := '0';
          EdtDsCarga.Text          := '';
          EdtNrMalote.BrAsInteger  := 0;
          EdtTpVeicul.BrAsInteger  := 0;
          EdtTpVeicul.BrValidarEntrada;
          EdtKmPreCar.BrAsInteger  := 0;
          EdtDtPreRet.Text         := '  /  /    ';
          CbxTpEscolt.ItemIndex    := 0;
          EdtCdEmpEsc.BrAsInteger  := 0;
          EdtCdEmpEsc.BrValidarEntrada;

          EdtCdVeicul.BrAsInteger  := 0;
          EdtCdVeicul.BrValidarEntrada;

          EdtCdVeicu1.BrAsInteger  := 0;
          EdtCdVeicu1.BrValidarEntrada;

          EdtCdVeicu2.BrAsInteger  := 0;
          EdtCdVeicu2.BrValidarEntrada;

          EdtCdVeicu3.BrAsInteger  := 0;
          EdtCdVeicu3.BrValidarEntrada;

          EdtCdMotori.BrAsInteger  := 0;
          EdtCdMotori.BrValidarEntrada;

          EdtCdMotor1.BrAsInteger  := 0;
          EdtCdMotor1.BrValidarEntrada;

          EdtCdMotor2.BrAsInteger  := 0;
          EdtCdMotor2.BrValidarEntrada;

          EdtCdMotor3.BrAsInteger  := 0;
          EdtCdMotor3.BrValidarEntrada;

          PgcFundo.ActivePage := TbsFiltro;
      finally
          FreeAndNil(lConexao);
      end;
end;

function TMov0002.EstadosMarcados: AnsiString;
var lNrIndice : Byte;
begin
      Result := '';

      for lNrIndice := 0 to CblCdEstado.Items.Count -1 do
      begin
            if CblCdEstado.Checked[lNrIndice] then
            begin
                  Result := Result + ',' + QuotedStr(CblCdEstado.Values[lNrIndice]);
            end;
      end;

      if Result > '' then
      begin
            Result := Copy(Result, 2, 350);
      end;
end;

procedure TMov0002.SbtImprimClick(Sender: TObject);
begin
      ValidarSalvamento;

      BrvGerRel.IniciarRelatorio('QRL0003', 'G', Name);

      BrvGerRel.InserirParametroSQL('DsCarga',  EdtDsCarga.Text);
      BrvGerRel.InserirParametroSQL('NrMalote', EdtNrMalote.Text);
      BrvGerRel.InserirParametroSQL('DsTpVeic', LblDsTpVeic.Text);
      BrvGerRel.InserirParametroSQL('DsVeicul', LblDsVeicul.Text);
      BrvGerRel.InserirParametroSQL('QtVolum1', EdtQtVolum1.Text);
      BrvGerRel.InserirParametroSQL('NmMotori', LblNmMotori.Text);
      BrvGerRel.InserirParametroSQL('QtPeso1', EdtQtPeso1.Text);

      BrvGerRel.InserirParametroSQL('DsVeicu1', LblDsVeicu1.Text);
      BrvGerRel.InserirParametroSQL('QtVolum2', EdtQtVolum2.Text);
      BrvGerRel.InserirParametroSQL('NmMotor1', LblNmMotor1.Text);
      BrvGerRel.InserirParametroSQL('QtPeso2',  EdtQtPeso2.Text);

      BrvGerRel.InserirParametroSQL('DtPreRet',  EdtDtPreRet.Text);
      BrvGerRel.InserirParametroSQL('QtCapVol',  EdtQtCapVol.Text);
      BrvGerRel.InserirParametroSQL('QtCapPes',  EdtQtCapPes.Text);
      BrvGerRel.InserirParametroSQL('KmPreCar',  EdtKmPreCar.Text);
      BrvGerRel.InserirParametroSQL('DsEmpDes',  LblDsEmpDes.Text);
      BrvGerRel.InserirParametroSQL('SnSalvo',   'N');

      BrvGerRel.ImprimirRelatorio;
end;

function TMov0002.SituacaoVencimento(pQtDiaVen : Integer;
                                     pDtAgenda : TDateTime;
                                     pStVenAtu : AnsiString) : AnsiString;
begin
      Result := '0';

      if PDtAgenda > 1 then
      begin
            if pQtDiaVen < 0  then
            begin
                  Result := '3';
            end else
            begin
                  Result := '2';
            end;
      end else
      begin
            if pQtDiaVen < 0  then
            begin
                  Result := '1';
            end;
      end;

      if pStVenAtu > Result then
      begin
            Result := pStVenAtu;
      end;

end;

procedure TMov0002.BrvBitBtn1Click(Sender: TObject);
begin
      if CblTpTransp.BrCheckedCount = 0 then
      begin
            raise Exception.Create('Pelo menos um tipo deve ser selecionado');
      end;

      if CblCdEstado.BrCheckedCount = 0 then
      begin
            raise Exception.Create('Pelo menos um estado deve ser selecionado');
      end;

      if not EdtDtPreIni.BrDataVazia then
      begin
            if not EdtDtPreIni.BrDataValida then
            begin
                  raise Exception.Create('Data prevista inicial está inválida');
            end;
      end;

      if not EdtDtPreFim.BrDataVazia then
      begin
            if not EdtDtPreFim.BrDataValida then
            begin
                  raise Exception.Create('Data prevista final está inválida');
            end;
      end;

      if (not EdtDtPreIni.BrDataVazia) and (not EdtDtPreFim.BrDataVazia) and
         (EdtDtPreIni.BrAsDate > EdtDtPreFim.BrAsDate) then
      begin
            raise Exception.Create('Data prevista inicial não pode ser maior que final');
      end;

      try
          PgbProces.Position := 0;
          GbxProces.Visible  := True;
          GbxProces.Refresh;

          CdsRotas.Tag       := 1;

          ProcessarTomadores;
      finally
          GbxProces.Visible  := False;
          CdsRotas.Tag       := 0;
      end;
end;

procedure TMov0002.ProcessarTomadores;
begin
      try
          PgbProces.Position := 0;
          GbxProces.Visible  := True;
          GbxProces.Refresh;

          if not CdsTomado.Active then
          begin
                CdsTomado.IndexDefs.Clear;
                CdsTomado.IndexDefs.Add('Key1', 'CdTomado', [ixPrimary,ixUnique]);

                CdsTomado.CreateDataSet;
               (CdsTomado.FieldByName('QtCtrc') as
                                      TIntegerField).DisplayFormat := '0';
               (CdsTomado.FieldByName('NrPeso') as
                                      TFloatField).DisplayFormat   := '#,###,##0.00';
               (CdsTomado.FieldByName('VrMercad')as
                                      TFloatField).DisplayFormat   := '#,###,##0.00';

                CdsTomado.IndexName := 'Key1';
                CdsTomado.SetKey;
          end else
          begin
                CdsTomado.EmptyDataSet;
          end;

          CdsTomado.Tag      := 1;

          CarregarFiltroTomadores;
      finally
          GbxProces.Visible  := False;
          CdsTomado.Tag      := 0;
      end;
end;

function TMov0002.RetornaPraca(pCdsGrade: TDataSet): AnsiString;
begin
      try
          pCdsGrade.Filter   := 'SnMarcad = ' + Quotedstr('S');
          pCdsGrade.Filtered := True;
          pCdsGrade.First;

          Result := pCdsGrade.FieldByName('DsPraca').AsString;
      finally
          pCdsGrade.Filtered := False;
      end;
end;

Procedure TMov0002.RotaCliente(pCdClient : Integer;        pCdLocEnt : Integer;
                           var pCdRota   : AnsiString; var pDsPraca  : AnsiString);
var lDsParams : String;
begin
      lDsParams := '<%CdClient%>;' + IntToStr(pCdClient) + #13 +
                   '<%CdEmpres%>;' + EdtCdEmpres.Text    + #13 +
                   '<%CdLocEnt%>;' + IntToStr(pCdLocEnt);

      CdsRota.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(60, lDsParams, Name);

      pCdRota  := IntToStr(CdsRota.FieldByName('CdRota').AsInteger); // tem rota zerada!!!
      pDsPraca := CdsRota.FieldByName('DsPraca').AsString;
end;

procedure TMov0002.CarregarFiltroTomadores;
var lDsComWhe : Ansistring;
    lDsParams : Ansistring;
    lDsTipSel : String;
    lCdRota   : AnsiString;
    lDsPraca  : AnsiString;

    lTtCtrc   : Integer;
    lQtDiaVen : Integer;

    lTtPeso   : Real;
    lTtMercad : Real;

    lDtAgenda : TDateTime;
begin
      lDsComWhe := ComplementoInstrucaoSqlFiltroPrincipal;

      gCdEstado := EstadosMarcados;

      lDsComWhe := lDsComWhe + ' and Coalesce(T002.CdEstEnt, T002.CdEstDes) in (' + gCdEstado + ')';

      lDsParams := '<%CdEmpres%>;' + EdtCdEmpres.Text + #13 +
                   '<%TpTransp%>;' + TipoSelecionado(lDsTipSel) + #13 +
                   '<%DsComWhe%>;' + lDsComWhe;

      CdsPesCTRC.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(37, lDsParams, Name);
      CdsTomado.DisableControls;
      CdsTomado.Filtered := True;

      gDtServer     := DmSrvApl.BrvDicionario.DataServer - 1;
      lTtCtrc       := 0;
      lTtPeso       := 0;
      lTtMercad     := 0;

      PgbProces.Max := CdsPesCTRC.RecordCount;

      while not CdsPesCTRC.Eof do
      begin
            RotaCliente(CdsPesCTRC.FieldByName('CdDestin').AsInteger,
                        CdsPesCTRC.FieldByName('CdLocEnt').AsInteger,
                        lCdRota, lDsPraca);

            if (EdtCdRota.Text = '0') or (EdtCdRota.Text = lCdRota) then
            begin
                  CdsPesCTRC.Edit;

                  case CdsPesCTRC.FieldByName('SnFrete').AsString[1] of
                       'R': begin
                                  CdsPesCTRC.FieldByName('CdClient').AsInteger :=
                                            CdsPesCTRC.FieldByName('CdRemete').AsInteger;
                                  CdsPesCTRC.FieldByName('RsClient').AsString  :=
                                            CdsPesCTRC.FieldByName('RsRemete').AsString;
                            end;

                       'D': begin
                                  CdsPesCTRC.FieldByName('CdClient').AsInteger :=
                                             CdsPesCTRC.FieldByName('CdDestin').AsInteger;
                                  CdsPesCTRC.FieldByName('RsClient').AsString  :=
                                             CdsPesCTRC.FieldByName('RsDestin').AsString;
                            end;

                       'C': begin
                                  CdsPesCTRC.FieldByName('CdClient').AsInteger :=
                                             CdsPesCTRC.FieldByName('CdConsig').AsInteger;
                                  CdsPesCTRC.FieldByName('RsClient').AsString  :=
                                             CdsPesCTRC.FieldByName('RsConsig').AsString;
                            end;
                  end;


                  CdsPesCTRC.FieldByName('CdRota').AsString := lCdRota;
                  CdsPesCTRC.FieldByName('DsPraca').AsString := lDsPraca;

                  CdsPesCTRC.Post;

                  lQtDiaVen := Round(CdsPesCTRC.FieldByName('DtPreEnt').AsDateTime - gDtServer);

                  CdsTomado.Filter   := 'CdTomado = ' +
                                         CdsPesCTRC.FieldByName('CdClient').AsString;

                  if not CdsTomado.Eof then
                  begin
                        CdsTomado.Edit;

                        CdSTomado.FieldByName('CdCtrc').AsString    :=
                                 CdSTomado.FieldByName('CdCtrc').AsString + ', ' +
                                 CdsPesCTRC.FieldByName('CdCtrc').AsString;

                        CdSTomado.FieldByName('CdRota').AsString    :=
                                 CdSTomado.FieldByName('CdRota').AsString + ', ' +
                                 CdsPesCTRC.FieldByName('CdRota').AsString;

                        if lQtDiaVen < CdSTomado.FieldByName('QtDiaVen').AsInteger then
                        begin
                              CdSTomado.FieldByName('QtDiaVen').AsInteger := lQtDiaVen;
                        end;
                  end else
                  begin
                        CdsTomado.Append;
                        CdSTomado.FieldByName('CdTomado').AsInteger :=
                                             CdsPesCTRC.FieldByName('CdClient').AsInteger;
                        CdSTomado.FieldByName('NmTomado').AsString  :=
                                             CdsPesCTRC.FieldByName('RsClient').AsString;

                        case CdsPesCTRC.FieldByName('SnFrete').AsString[1] of
                             'R': begin
                                        CdSTomado.FieldByName('NmCidade').AsString :=
                                             CdsPesCTRC.FieldByName('NmCidRem').AsString;
                                        CdSTomado.FieldByName('CdEstado').AsString :=
                                             CdsPesCTRC.FieldByName('CdEstRem').AsString;
                                  end;
                             'D': begin
                                        CdSTomado.FieldByName('NmCidade').AsString :=
                                             CdsPesCTRC.FieldByName('NmCidDes').AsString;
                                        CdSTomado.FieldByName('CdEstado').AsString :=
                                             CdsPesCTRC.FieldByName('CdEstDes').AsString;
                                  end;
                             'C': begin
                                        CdSTomado.FieldByName('NmCidade').AsString :=
                                             CdsPesCTRC.FieldByName('NmCidCon').AsString;
                                        CdSTomado.FieldByName('CdEstado').AsString :=
                                             CdsPesCTRC.FieldByName('CdEstCon').AsString;
                                  end;

                        end;

                        CdsTomado.FieldByName('SnMarcad').AsString  := 'S';
                        CdsTomado.FieldByName('QtDiaVen').AsInteger := lQtDiaVen;
                        CdsTomado.FieldByName('CdCtrc').AsString    :=
                                             CdsPesCTRC.FieldByName('CdCtrc').AsString;
                        CdsTomado.FieldByName('CdRota').AsString   :=
                                             CdsPesCTRC.FieldByName('CdRota').AsString;
                  end;

                  CdsTomado.FieldByName('StVencim').AsString :=
                            SituacaoVencimento(CdSTomado.FieldByName('QtDiaVen').AsInteger,
                                               CdsPesCTRC.FieldByName('DtAgenda').AsDateTime,
                                               CdsTomado.FieldByName('StVencim').AsString);

                  CdsTomado.FieldByName('QtCtrc').AsInteger :=
                            CdSTomado.FieldByName('QtCtrc').AsInteger + 1;
                  CdsTomado.FieldByName('NrPeso').AsFloat   :=
                            CdSTomado.FieldByName('NrPeso').AsFloat   +
                            CdsPesCTRC.FieldByName('NrPeso').AsFloat;
                  CdsTomado.FieldByName('VrMercad').AsFloat :=
                            CdSTomado.FieldByName('VrMercad').AsFloat +
                            CdsPesCTRC.FieldByName('VrMercad').AsFloat;

                  if CdsTomado.FieldByName('DtPreEnt').AsDateTime <
                     CdsPesCTRC.FieldByName('DtPreEnt').AsDateTime then
                  begin
                        CdsTomado.FieldByName('DtPreEnt').AsDateTime :=
                                         CdsPesCTRC.FieldByName('DtPreEnt').AsDateTime;
                  end;

                  CdsTomado.Post;

                  lTtCtrc   := lTtCtrc   + 1;
                  lTtPeso   := lTtPeso   + CdsPesCTRC.FieldByName('NrPeso').AsFloat;
                  lTtMercad := lTtMercad + CdsPesCTRC.FieldByName('VrMercad').AsFloat;
            end;

            CdsPesCTRC.Next;

            PgbProces.StepIt;
      end;

      EdtTtCtrTom.BrAsInteger := lTtCtrc;
      EdtTtPesTom.BrAsFloat   := lTtPeso;
      EdtTtMerTom.BrAsFloat   := lTtMercad;

      EdtTtCtToMa.BrAsInteger := lTtCtrc;
      EdtTtPeToMa.BrAsFloat   := lTtPeso;
      EdtTtMeToMa.BrAsFloat   := lTtMercad;

      CarregarFiltroPrincipal(lDsTipSel);
      DbgFiltro.DataSource := DtsFiltro1;

      CdsTomado.Filtered   := False;
      CdsTomado.First;
      CdsTomado.EnableControls;

      PgcProces.ActivePage := TbsTomado;
      PgcFundo.ActivePage  := TbsProces;
end;

procedure TMov0002.BrvBitBtn7Click(Sender: TObject);
begin
      try
          PgbProces.Position := 0;
          GbxProces.Visible  := True;
          GbxProces.Refresh;

          CdsRotas.Tag       := 1;

          CarregarFiltroRotas;
      finally
          GbxProces.Visible  := False;
          CdsRotas.Tag       := 0;
      end;
end;

procedure TMov0002.CTRCsDosTomadores(var pCdTomado : String; var pCdCtrc : String;
                                     var pCdRotas  : String);
begin
      pCdCTRC   := '';
      pCdTomado := '';
      pCdRotas  := '';

      CdsTomado.DisableControls;
      CdsTomado.First;

      while not CdsTomado.Eof do
      begin
            if CdsTomado.FieldByName('SnMarcad').AsString = 'S' then
            begin
                  if pCdCTRC <> '' then
                  begin
                        pCdCTRC   := pCdCTRC   + ', ';
                        pCdTomado := pCdTomado + ', ';
                        pCdRotas  := pCdRotas  + ', ';
                  end;

                  pCdCTRC   := pCdCTRC   + CdsTomado.FieldByName('CdCtrc').AsString;
                  pCdTomado := pCdTomado + CdsTomado.FieldByName('CdTomado').AsString;
                  pCdRotas  := pCdRotas  + CdsTomado.FieldByName('CdRota').AsString;
            end;

            CdsTomado.Next;
      end;

      CdsTomado.First;
      CdsTomado.EnableControls;

      if pCdCTRC = '' then
      begin
            raise Exception.Create('Nenhum tomador foi selecionado.');
      end;
end;

procedure TMov0002.CarregarFiltroRotas;
var lDsParams : String;
    lDsComWhe : String;
    lDsTipSel : String;
    lCdTomado : String;
    lCdRotas  : String;

    lTtCtrc   : Integer;
    lTtPeso   : Real;
    lTtMercad : Real;

    lQtDias   : Real;
    lDtServer : TDate;
begin
      lDsComWhe := ComplementoInstrucaoSqlFiltroPrincipal;

      // =-=-=-=-=
      CTRCsDosTomadores(lCdTomado, gCdCtrc, lCdRotas);

      lDsParams := '<%CdRotas%>;' + lCdRotas;
      CdsRotas.DisableControls;
      CdsRotas.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(34, lDsParams, Name);

      //--==--==--==-- Mostrar conhecimentos sem rota
      CdsRotas.Append;
      CdsRotas.FieldByName('SnMarcad').AsString := 'S';
      CdsRotas.FieldByName('CdRota').AsInteger  := 0;
      CdsRotas.FieldByName('QtCtrc').AsInteger  := 0;
      CdsRotas.Post;

      CdsRotas.First;
      //--==--==--==--

      lTtCtrc       := 0;
      lTtPeso       := 0;
      lTtMercad     := 0;
      PgbProces.Max := CdsRotas.RecordCount;

      (CdsRotas.FieldByName('QtCtrc') as TFmTBcdField).DisplayFormat  := '0';
      (CdsRotas.FieldByName('NrPeso') as TFmTBcdField).DisplayFormat  := '###,###,##0.00';
      (CdsRotas.FieldByName('VrMercad')as TFmTBcdField).DisplayFormat := '###,###,##0.00';

      lDsParams := '<%CdEmpres%>;' + EdtCdEmpres.Text           + #13 +
                   '<%TpTransp%>;' + TipoSelecionado(lDsTipSel) + #13 +
                   '<%DsComWhe%>;' + lDsComWhe + ' ' + ComplementoFiltroGeral('P') + #13 +
                   '<%CdCtrc%>;'   + gCdCtrc;

      CdsTotal.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(36, lDsParams, Name);
      lDtServer     := DmSrvApl.BrvDicionario.DataServer - 1;

      while not CdsRotas.Eof do
      begin
            CdsTotal.Filtered := False;
            CdsTotal.Filter   := 'CdRota = ' + CdsRotas.FieldByName('CdRota').AsString;
            CdsTotal.Filtered := True;

            if not CdsTotal.eof then
            begin
                  CdsRotas.Edit;
                  CdsRotas.FieldByName('QtCtrc').AsInteger :=
                                        CdsTotal.FieldByName('QtCtrc').AsInteger;
                  CdsRotas.FieldByName('NrPeso').AsFloat   :=
                                        CdsTotal.FieldByName('NrPeso').AsFloat;
                  CdsRotas.FieldByName('VrMercad').AsFloat :=
                                        CdsTotal.FieldByName('VrMercad').AsFloat;
                  CdsRotas.FieldByName('QtDiaVen').AsInteger :=
                           Round(CdsTotal.FieldByName('DtPreEnt').AsDateTime - lDtServer);
                  CdsRotas.FieldByName('DtPreEnt').AsDateTime :=
                                        CdsTotal.FieldByName('DtPreEnt').AsDateTime;
                  CdsRotas.FieldByName('StVencim').AsString :=
                            SituacaoVencimento(CdsRotas.FieldByName('QtDiaVen').AsInteger,
                                               CdsTotal.FieldByName('DtAgenda').AsDateTime,
                                               CdsRotas.FieldByName('StVencim').AsString);

                  CdsRotas.Post;

                  if CdsRotas.FieldByName('QtCtrc').AsInteger > 0 then
                  begin
                        lTtCtrc   := lTtCtrc   + CdsRotas.FieldByName('QtCtrc').AsInteger;
                        lTtPeso   := lTtPeso   + CdsRotas.FieldByName('NrPeso').AsFloat;
                        lTtMercad := lTtMercad + CdsRotas.FieldByName('VrMercad').AsFloat;
                  end;
            end;

            CdsRotas.Next;
            PgbProces.StepIt;
      end;

      //--==--==
      CdsRotas.Filter   := 'QtCtrc = 0';
      CdsRotas.Filtered := True;

      while not CdsRotas.Eof do
      begin
            CdsRotas.Delete;
      end;

      CdsRotas.Filtered := False;
      //--==--==

      EdtTtCtrRot.BrAsInteger := lTtCtrc;
      EdtTtPesRot.BrAsFloat   := lTtPeso;
      EdtTtMerRot.BrAsFloat   := lTtMercad;

      EdtTtCtRoMa.BrAsInteger := lTtCtrc;
      EdtTtPeRoMa.BrAsFloat   := lTtPeso;
      EdtTtMeRoMa.BrAsFloat   := lTtMercad;

      CdsRotas.EnableControls;
      CdsRotas.First;

      CdsFiltro2.Data := CdsFiltro1.Data;
      CdsFiltro2.Append;
      CdsFiltro2.FieldByName('NmFiltro').AsString := 'Tomadores';
      CdsFiltro2.FieldByName('DsFiltro').AsString := lCdTomado;
      CdsFiltro2.Post;

      DbgFiltro.DataSource := DtsFiltro2;
      PgcProces.ActivePage := TbsRota;
end;

procedure TMov0002.CdsRotasBeforeDelete(DataSet: TDataSet);
begin
      if DataSet.Tag = 0 then
      begin
           Abort;
      end;
end;

function TMov0002.ComplementoInstrucaoSqlFiltroPrincipal : String;
begin
      Result := ' ';

      //if EdtCdRota.BrAsInteger > 0 then
      //begin
      //      Result := Result + ' and T005.CdRota = ' + EdtCdRota.Text;
      //end;

      Result := Result + ComplementoFiltroGeral('P');
end;

function TMov0002.ComplementoFiltroGeral(TpFiltro : AnsiString) : String;
begin
      Result := ' ';

      if EdtCdTomado.BrAsInteger > 0 then
      begin
            Result := Result +
            'and ((T002.SnFrete = "R" and T002.CdRemete = ' + EdtCdTomado.Text + ') or ' +
            '     (T002.SnFrete = "D" and T002.CdDestin = ' + EdtCdTomado.Text + ') or ' +
            '     (T002.SnFrete = "C" and T002.CdConsig = ' + EdtCdTomado.Text + '))';
      end;

      if EdtCdDestin.BrAsInteger > 0 then
      begin
            Result := Result + ' and T002.CdDestin = ' + EdtCdDestin.Text;
      end;

      if Trim(EdtCpCidade.Text) > '' then
      begin
            if TpFiltro = 'P' then
            begin
                  Result := Result + ' and Coalesce(G0114.CpCidade, Coalesce(G0113.CpCidade, ' +
                                     ' Coalesce(G0112.CpCidade, G0111.CpCidade))) = "'         +
                                       EdtCpCidade.Text + '"';
            end else
            begin
                  Result := Result + ' and G003.CpCidade = "' + EdtCpCidade.Text + '"';
            end;
      end;

      if not EdtDtPreIni.BrDataVazia then
      begin
            Result := Result + ' and T002.DtPreEnt >= "' + EdtDtPreIni.BrAsDataSQL + '"';
      end;

      if not EdtDtPreFim.BrDataVazia then
      begin
            Result := Result + ' and T002.DtPreEnt <= "' + EdtDtPreFim.BrAsDataSQL + '"';
      end;
end;

function TMov0002.ComplementoInstrucaoSqlFiltroRotas : String;
begin
      Result := ComplementoFiltroGeral('D');
end;

procedure TMov0002.CarregarFiltroPrincipal(pDsTipSel : String);
begin
      if not CdsFiltro1.Active then
      begin
            CdsFiltro1.CreateDataSet;
      end;

      CdsFiltro1.EmptyDataSet;

      CdsFiltro1.Append;
      CdsFiltro1.FieldByName('NmFiltro').AsString := 'Empresa';
      CdsFiltro1.FieldByName('DsFiltro').AsString := EdtCdEmpres.Text + ' - ' +
                                                     LblDsEmpres.Text;
      CdsFiltro1.Post;

      if EdtCdRota.BrAsInteger > 0 then
      begin
            CdsFiltro1.Append;
            CdsFiltro1.FieldByName('NmFiltro').AsString := 'Rota';
            CdsFiltro1.FieldByName('DsFiltro').AsString := EdtCdRota.Text + ' - ' +
                                                           LblDsPraca.Text;
            CdsFiltro1.Post;
      end;

      if EdtCdTomado.BrAsInteger > 0 then
      begin
            CdsFiltro1.Append;
            CdsFiltro1.FieldByName('NmFiltro').AsString := 'Tomador';
            CdsFiltro1.FieldByName('DsFiltro').AsString := EdtCdTomado.Text + ' - ' +
                                                           LblRsTomado.Text;
            CdsFiltro1.Post;
      end;

      if EdtCdDestin.BrAsInteger > 0 then
      begin
            CdsFiltro1.Append;
            CdsFiltro1.FieldByName('NmFiltro').AsString := 'Destinatário';
            CdsFiltro1.FieldByName('DsFiltro').AsString := EdtCdDestin.Text + ' - ' +
                                                           LblRsDestin.Text;
            CdsFiltro1.Post;
      end;

      if EdtCpCidade.Text <> '' then
      begin
            CdsFiltro1.Append;
            CdsFiltro1.FieldByName('NmFiltro').AsString := 'Cidade';
            CdsFiltro1.FieldByName('DsFiltro').AsString := EdtCpCidade.Text + ' - ' +
                                                           LblNmCidade.Text;
            CdsFiltro1.Post;
      end;

      if not EdtDtPreIni.BrDataVazia then
      begin
            CdsFiltro1.Append;
            CdsFiltro1.FieldByName('NmFiltro').AsString := 'Data inicial';
            CdsFiltro1.FieldByName('DsFiltro').AsString := EdtDtPreIni.Text;
            CdsFiltro1.Post;
      end;

      if not EdtDtPreFim.BrDataVazia then
      begin
            CdsFiltro1.Append;
            CdsFiltro1.FieldByName('NmFiltro').AsString := 'Data final';
            CdsFiltro1.FieldByName('DsFiltro').AsString := EdtDtPreFim.Text;
            CdsFiltro1.Post;
      end;

      if gCdEstado > '' then
      begin
            CdsFiltro1.Append;
            CdsFiltro1.FieldByName('NmFiltro').AsString := 'Estados';
            CdsFiltro1.FieldByName('DsFiltro').AsString := gCdEstado;
            CdsFiltro1.Post;
      end;

      CdsFiltro1.Append;
      CdsFiltro1.FieldByName('NmFiltro').AsString := 'Tipos';
      CdsFiltro1.FieldByName('DsFiltro').AsString := pDsTipSel;
      CdsFiltro1.Post;
end;

function TMov0002.TipoSelecionado(var pDsTipSel : String) : String;
var NrSequen : Integer;
begin
      Result    := '';
      pDsTipSel := '';
      for NrSequen := 0 to CblTpTransp.Items.Count - 1 do
      begin
            if CblTpTransp.Checked[NrSequen] then
            begin
                  Result := Result + '"' +
                            Copy(CblTpTransp.Items.Strings[NrSequen], 1, 1) + '", ';
                  pDsTipSel := pDsTipSel +
                               CblTpTransp.Items.Strings[NrSequen] + '; ';
            end;
      end;

      Delete(Result, Length(Result)-1, 1);
end;

procedure TMov0002.TotalizaMarcados(pCdsGrade    : OleVariant;  pEdtQtCtrc   : TBrvEditNum;
                                    pEdtNrPesMer : TBrvEditNum; pEdtVrMercad : TBrvEditNum);
begin
      try
          CdsTotMar.Data := pCdsGrade;
          pEdtQtCtrc.BrAsInteger   := 0;
          pEdtNrPesMer.BrAsInteger := 0;
          pEdtVrMercad.BrAsInteger := 0;

          CdsTotMar.Filter   := 'SnMarcad = ' + QuotedStr('S');
          CdsTotMar.Filtered := True;

          while not CdsTotMar.Eof do
          begin
                pEdtQtCtrc.BrAsInteger := pEdtQtCtrc.BrAsInteger  +
                                            CdsTotMar.FieldByName('QtCtrc').AsInteger;
                pEdtNrPesMer.BrAsFloat := pEdtNrPesMer.BrAsFloat +
                                            CdsTotMar.FieldByName('NrPeso').AsFloat;
                pEdtVrMercad.BrAsFloat := pEdtVrMercad.BrAsFloat +
                                            CdsTotMar.FieldByName('VrMercad').AsFloat;

                CdsTotMar.Next;
          end;
      finally
          CdsTotMar.Close;
      end;
end;

procedure TMov0002.BrvBitBtn2Click(Sender: TObject);
begin
      DbgFiltro.DataSource := DtsFiltro1;
      PgcProces.ActivePage  := TbsTomado;
end;

procedure TMov0002.BrvBitBtn3Click(Sender: TObject);
begin
      try
          PgbProces.Position := 0;
          GbxProces.Visible  := True;
          GbxProces.Refresh;

          CdsCidade.Tag       := 1;

          CarregarFiltroCidades;
      finally
          GbxProces.Visible  := False;
          CdsCidade.Tag      := 0;
      end;
end;

procedure TMov0002.CarregarFiltroCidades;
var lCdRotas  : String;
    lDsComWhe : String;
    lDsParams : String;
    lDsTipSel : String;

    lTtCtrc   : Integer;
    lTtPeso   : Real;
    lTtMercad : Real;

    lDtBanco  : TDate;
begin
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-=-=-= Processando as rotas selecionadas
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      lCdRotas := RotasSelecionadas;

      CdsFiltro3.Data := CdsFiltro2.Data;
      CdsFiltro3.Append;
      CdsFiltro3.FieldByName('NmFiltro').AsString := 'Rotas';
      CdsFiltro3.FieldByName('DsFiltro').AsString := lCdRotas;
      CdsFiltro3.Post;
      DbgFiltro.DataSource := DtsFiltro3;
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

      lDsComWhe := ComplementoInstrucaoSqlFiltroRotas;

      lDsParams := '<%CdEmpres%>;' + EdtCdEmpres.Text           + #13 +
                   '<%TpTransp%>;' + TipoSelecionado(lDsTipSel) + #13 +
                   '<%CdRotas%>;'  + lCdRotas                   + #13 +
                   '<%CdCtrc%>;'   + gCdCtrc                    + #13 +
                   '<%DsComWhe%>;' + lDsComWhe;
      CdsCidade.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(
                                                                    107, lDsParams, Name);

      CdsCidade.IndexDefs.Clear;
      CdsCidade.IndexDefs.Add('Key1', 'CdRota;CpCidade', [ixPrimary,ixUnique]);
      CdsCidade.IndexName := 'Key1';
      CdsCidade.SetKey;

      CdsCidade.First;

      lTtCtrc       := 0;
      lTtPeso       := 0;
      lTtMercad     := 0;
      lDtBanco      := DmSrvApl.BrvDicionario.DataServer;

      PgbProces.Max := CdsCidade.RecordCount;

      (CdsCidade.FieldByName('QtCtrc') as TFmTBcdField).DisplayFormat  := '0';
      (CdsCidade.FieldByName('NrPeso') as TFmTBcdField).DisplayFormat  := '###,###,##0.00';
      (CdsCidade.FieldByName('VrMercad')as TFmTBcdField).DisplayFormat := '###,###,##0.00';

      while not CdsCidade.Eof do
      begin
            CdsCidade.Edit;
            CdsCidade.FieldByName('QtDiaVen').AsInteger :=
                     Round(CdsCidade.FieldByName('DtPreEnt').AsDateTime - (lDtBanco - 1));
            CdsCidade.FieldByName('StVencim').AsString :=
                            SituacaoVencimento(CdsCidade.FieldByName('QtDiaVen').AsInteger,
                                               CdsCidade.FieldByName('DtAgenda').AsDateTime,
                                               CdsCidade.FieldByName('StVencim').AsString);


            CdsCidade.Post;

            lTtCtrc   := lTtCtrc   + CdsCidade.FieldByName('QtCtrc').AsInteger;
            lTtPeso   := lTtPeso   + CdsCidade.FieldByName('NrPeso').AsFloat;
            lTtMercad := lTtMercad + CdsCidade.FieldByName('VrMercad').AsFloat;

            CdsCidade.Next;

            PgbProces.StepIt;
      end;

      EdtTtCtrCid.BrAsInteger := lTtCtrc;
      EdtTtPesCid.BrAsFloat   := lTtPeso;
      EdtTtMerCid.BrAsFloat   := lTtMercad;

      EdtTtCtCiMa.BrAsInteger := lTtCtrc;
      EdtTtPeCiMa.BrAsFloat   := lTtPeso;
      EdtTtMeCiMa.BrAsFloat   := lTtMercad;

      CdsCidade.First;
      CdsCidade.EnableControls;
      PgcProces.ActivePage := TbsCidade;


      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-=-=-= Processando as rotas selecionadas
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      {lCdRotas := RotasSelecionadas;

      CdsFiltro3.Data      := CdsFiltro2.Data;
      CdsFiltro3.Append;
      CdsFiltro3.FieldByName('NmFiltro').AsString := 'Rotas';
      CdsFiltro3.FieldByName('DsFiltro').AsString := lCdRotas;
      CdsFiltro3.Post;
      DbgFiltro.DataSource := DtsFiltro3;
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

      lDsComWhe := ComplementoInstrucaoSqlFiltroRotas;

      lDsParams := '<%CdEmpres%>;' + EdtCdEmpres.Text + #13 +
                   '<%TpTransp%>;' + TipoSelecionado(lDsTipSel) + #13 +
                   '<%CdRotas%>;'  + lCdRotas + #13 +
                   '<%CdCtrc%>;'   + gCdCtrc  + #13 +
                   '<%DsComWhe%>;' + lDsComWhe;
      CdsCidade.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(35, lDsParams, Name);
      CdsCidade.IndexDefs.Clear;
      CdsCidade.IndexDefs.Add('Key1', 'CpCidade', [ixPrimary,ixUnique]);
      CdsCidade.IndexName := 'Key1';
      CdsCidade.SetKey;
      CdsCidade.DisableControls;
      CdsCidade.First;

      lTtCtrc       := 0;
      lTtPeso       := 0;
      lTtMercad     := 0;
      PgbProces.Max := CdsCidade.RecordCount;

      (CdsCidade.FieldByName('QtCtrc') as TFmTBcdField).DisplayFormat  := '0';
      (CdsCidade.FieldByName('NrPeso') as TFmTBcdField).DisplayFormat  := '###,###,##0.00';
      (CdsCidade.FieldByName('VrMercad')as TFmTBcdField).DisplayFormat := '###,###,##0.00';

      while not CdsCidade.Eof do
      begin
            if CdsCidade.FieldByName('QtCtrc').AsInteger = 0 then
            begin
                  lDsParams := '<%CdEmpres%>;' + EdtCdEmpres.Text + #13 +
                               '<%TpTransp%>;' + TipoSelecionado(lDsTipSel) + #13 +
                               '<%DsComWhe%>;' + lDsComWhe +
                                              'and G011.CpCidade = ' +
                                              CdsCidade.FieldByName('CpCidade').AsString +
                                              #13 +
                               '<%CdRota%>;'   + CdsCidade.FieldByName('CdRota').AsString+
                               #13 +
                               '<%CdCtrc%>;'   + gCdCtrc  + #13;

                  CdsTotal.Data :=
                            DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(36, lDsParams,
                                                                                    Name);
                  CdsCidade.Edit;
                  CdsCidade.FieldByName('QtCtrc').AsInteger :=
                                        CdsTotal.FieldByName('QtCtrc').AsInteger;
                  CdsCidade.FieldByName('NrPeso').AsFloat   :=
                                        CdsTotal.FieldByName('NrPeso').AsFloat;
                  CdsCidade.FieldByName('VrMercad').AsFloat :=
                                        CdsTotal.FieldByName('VrMercad').AsFloat;
                  CdsCidade.FieldByName('QtDiaVen').AsInteger :=
                           Round(CdsTotal.FieldByName('DtPreEnt').AsDateTime -
                                (DmSrvApl.BrvDicionario.DataServer - 1));

                  if CdsCidade.FieldByName('DtPreEnt').AsDateTime <
                                    CdsTotal.FieldByName('DtPreEnt').AsDateTime then
                  begin
                        CdsCidade.FieldByName('DtPreEnt').AsDateTime :=
                                        CdsTotal.FieldByName('DtPreEnt').AsDateTime;
                  end;

                  CdsCidade.Post;

                  if CdsCidade.FieldByName('QtCtrc').AsInteger = 0 then
                  begin
                        CdsCidade.Delete;
                  end else
                  begin
                        lTtCtrc   := lTtCtrc   + CdsCidade.FieldByName('QtCtrc').AsInteger;
                        lTtPeso   := lTtPeso   + CdsCidade.FieldByName('NrPeso').AsFloat;
                        lTtMercad := lTtMercad + CdsCidade.FieldByName('VrMercad').AsFloat;
                        CdsCidade.Next;
                  end;
            end else
            begin
                  CdsCidade.Next;
            end;

            PgbProces.StepIt;
      end;

      LblTtCtrCid.Caption := FormatFloat('##,0', lTtCtrc);
      LblTtPesCid.Caption := FormatFloat('###,###,##0.00', lTtPeso);
      LblTtMerCid.Caption := FormatFloat('###,###,##0.00', lTtMercad);

      CdsCidade.First;
      CdsCidade.EnableControls;
      PgcProces.ActivePage := TbsCidade;}
end;

function TMov0002.CidadesSelecionadas : String;
begin
      Result := '';
      CdsCidade.DisableControls;
      CdsCidade.First;
      while not CdsCidade.Eof do
      begin
            if CdsCidade.FieldByName('SnMarcad').AsString = 'S' then
            begin
                  if Result <> ''  then
                  begin
                        Result := Result + ', '
                  end;
                  Result := Result+ CdsCidade.FieldByName('CpCidade').AsString;
            end;
            CdsCidade.Next;
      end;

      CdsCidade.First;
      CdsCidade.EnableControls;

      if Result = '' then
      begin
            raise Exception.Create('Pelo menos uma cidade deve ser selecionada.');
      end;
end;

function TMov0002.RotasSelecionadas : String;
begin
      Result := '';
      CdsRotas.DisableControls;
      CdsRotas.First;
      while not CdsRotas.Eof do
      begin
            if CdsRotas.FieldByName('SnMarcad').AsString = 'S' then
            begin
                  if Result <> ''  then
                  begin
                        Result := Result + ', '
                  end;
                  Result := Result+ CdsRotas.FieldByName('CdRota').AsString;
            end;
            CdsRotas.Next;
      end;

      CdsRotas.First;
      CdsRotas.EnableControls;

      if Result = '' then
      begin
            raise Exception.Create('Pelo menos uma rota deve ser selecionada.');
      end;
end;

procedure TMov0002.BrvBitBtn4Click(Sender: TObject);
begin
      DbgFiltro.DataSource  := DtsFiltro2;
      PgcProces.ActivePage  := TbsRota;
end;

procedure TMov0002.BrvBitBtn5Click(Sender: TObject);
begin
  inherited;
      try
          PgbProces.Position := 0;
          PgbProces.Max      := CdsPesCTRC.RecordCount;
          GbxProces.Visible  := True;
          GbxProces.Refresh;

          CdsCtrc.DisableControls;
          CdsCtrc.Tag       := 1;
          CdsTomado.DisableControls;

          CarregarFiltroCTRC;
      finally
          GbxProces.Visible  := False;

          CdsTomado.First;
          CdsTomado.EnableControls;

          CdsCtrc.Tag      := 0;
          CdsCtrc.EnableControls;
      end;
end;

procedure TMov0002.BrvBitBtn6Click(Sender: TObject);
begin
      PgcFundo.ActivePage  := TbsFiltro;
end;

procedure TMov0002.CarregarFiltroCTRC;
var lTtCtrc   : Integer;
    lTtPeso   : Real;
    lTtMercad : Real;

    lDsFiltro : AnsiString;
begin
      CdsCTRC.Data  := CdsPesCTRC.Data;
      (CdsCTRC.FieldByName('NrPeso') as TFmTBcdField).DisplayFormat   := '###,###,##0.00';
      (CdsCTRC.FieldByName('VrMercad')as TFmTBcdField).DisplayFormat  := '###,###,##0.00';

      PgbProces.Max := CdsPesCTRC.RecordCount +
                       CdsTomado.RecordCount  +
                       CdsCtrc.RecordCount;

      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-= Processando tomadores marcados
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      CdsCtrc.First;
      CdsTomado.Filtered := True;

      while not CdsCtrc.Eof do
      begin
            CdsTomado.Filter := 'CdTomado = ' + CdsCtrc.FieldByName('CdClient').AsString;

            if not CdsTomado.Eof then
            begin
                  if CdsTomado.FieldByName('SnMarcad').AsString <> 'S' then
                  begin
                        CdsCtrc.Delete;
                  end else
                  begin

                        CdsCtrc.Next;
                  end;
            end else
            begin
                  CdsCtrc.Delete;
            end;

            PgbProces.StepIt;
      end;

      CdsTomado.Filtered := False;

      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-= Processando cidades marcadas
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      CdsCidade.DisableControls;
      CdsCidade.Filtered := True;
      CdsCtrc.First;

      while not CdsCtrc.Eof do
      begin
            CdsCidade.Filter :=
                    'CpCidade = ' + QuotedStr(CdsCtrc.FieldByName('CpCidDes').AsString) + ' and ' +
                    'CdRota   = ' + CdsCtrc.FieldByName('CdRota').AsString;

            if not CdsCidade.Eof then
            begin
                  if CdsCidade.FieldByName('SnMarcad').AsString <> 'S' then
                  begin
                        CdsCtrc.Delete;
                  end else
                  begin
                        CdsCtrc.Next;
                  end;
            end else
            begin
                  CdsCtrc.Delete;
            end;

            PgbProces.StepIt;
      end;
      CdsCidade.Filtered := False;

      CdsCidade.First;
      CdsCidade.EnableControls;
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-= Processando Filtro
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      CdsCidade.First;
      lDsFiltro := '';

      while not CdsCidade.Eof do
      begin
            if CdsCidade.FieldByName('SnMarcad').AsString = 'S' then
            begin
                  if lDsFiltro <> ''  then
                  begin
                        lDsFiltro := lDsFiltro + ', '
                  end;

                  lDsFiltro := lDsFiltro + CdsCidade.FieldByName('CpCidade').AsString;
            end;
            CdsCidade.Next;
            PgbProces.StepIt;
      end;

      if lDsFiltro = '' then
      begin
            raise Exception.Create('Nenhuma cidade foi selecionada.');
      end;

      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-= Processando Totalizadores dos CTRC
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      CdsCtrc.First;

      lTtCtrc   := 0;
      lTtPeso   := 0;
      lTtMercad := 0;

      while not CdsCtrc.Eof do
      begin
            inc(lTtCtrc);
            lTtPeso   := lTtPeso   + CdsCtrc.FieldByName('NrPeso').AsFloat;
            lTtMercad := lTtMercad + CdsCtrc.FieldByName('VrMercad').AsFloat;

            CdsCtrc.Edit;
            CdsCtrc.FieldByName('QtDiaVen').AsInteger :=
                           Round(CdsCtrc.FieldByName('DtPreEnt').AsDateTime -
                                (DmSrvApl.BrvDicionario.DataServer - 1));
            CdsCtrc.FieldByName('StVencim').AsString :=
                            SituacaoVencimento(CdsCtrc.FieldByName('QtDiaVen').AsInteger,
                                               CdsCtrc.FieldByName('DtAgenda').AsDateTime,
                                               CdsCtrc.FieldByName('StVencim').AsString);

            CdsCtrc.Post;

            CdsCtrc.Next;
            PgbProces.StepIt;
      end;

      CdsFiltro4.Data := CdsFiltro3.Data;
      CdsFiltro4.Append;
      CdsFiltro4.FieldByName('NmFiltro').AsString := 'Cidades';
      CdsFiltro4.FieldByName('DsFiltro').AsString := lDsFiltro;
      CdsFiltro4.Post;
      DbgFiltro.DataSource := DtsFiltro4;

      CdsCtrc.First;

      EdtTtCtrCtr.BrAsInteger := lTtCtrc;
      EdtTtPesCtr.BrAsFloat   := lTtPeso;
      EdtTtMerCtr.BrAsFloat   := lTtMercad;

      EdtTtCtCtMa.BrAsInteger := lTtCtrc;
      EdtTtPeCtMa.BrAsFloat   := lTtPeso;
      EdtTtMeCtMa.BrAsFloat   := lTtMercad;

      PgcProces.ActivePage := TbsCTRC;
end;

procedure TMov0002.BrvBitBtn8Click(Sender: TObject);
begin
      DbgFiltro.DataSource  := DtsFiltro3;
      PgcProces.ActivePage  := TbsCidade;
end;

procedure TMov0002.BrvBitBtn9Click(Sender: TObject);
begin
      CarregarConhecimentos;

      case PgcProces.ActivePageIndex of
           0: MostrarCarga('');
           1: MostrarCarga(RetornaPraca(CdsRotas));
           2: MostrarCarga(RetornaPraca(CdsCidade));
           3: MostrarCarga(RetornaPraca(CdsCTRC));
      end;
end;

procedure TMov0002.BtnProsseClick(Sender: TObject);
begin
      inherited;
      if EdtCdEmpres.BrAsInteger = 0 then
      begin
            raise Exception.Create('Uma empresa deve ser informada.');
      end else
      begin
            PnlEmpres.Enabled := False;
            PnlFiltro.Visible := True;

            CblCdEstado.CheckAll(cbChecked);
      end;
end;

procedure TMov0002.BtnVoltarClick(Sender: TObject);
begin
      PnlEmpres.Enabled := True;
      PnlFiltro.Visible := False;
end;

procedure TMov0002.CarregarConhecimentos;
var lDsParams : String;
    lDsTipSel : String;
begin
      lDsParams := '<%CdEmpres%>;' + EdtCdEmpres.Text           + #13 +
                   '<%TpTransp%>;' + TipoSelecionado(lDsTipSel) + #13 +
                   FiltroParaCarga;

      CdsCarga.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(43,lDsParams, Name);

      (CdsCarga.FieldByName('NrPeso') as TFmTBcdField).DisplayFormat   := '#,###,##0.00';
      (CdsCarga.FieldByName('VrMercad')as TFmTBcdField).DisplayFormat  := '#,###,##0.00';
      (CdsCarga.FieldByName('VrTotPre')as TFmTBcdField).DisplayFormat  := '#,###,##0.00';
      (CdsCarga.FieldByName('VrMaxCar') as TFmTBcdField).DisplayFormat := '#,###,##0.00';
end;

function TMov0002.FiltroDoCtrc : String;
begin
      Result := '';

      CdsCTRC.DisableControls;
      CdsCTRC.First;
      while not CdsCTRC.Eof do
      begin
            if CdsCTRC.FieldByName('SnMarcad').AsString = 'S' then
            begin
                  Result := Result +
                        '(T008.CdEmpres   = ' + CdsCTRC.FieldByName('CdEmpres').AsString +
                        ' and T008.CdCtrc = ' + CdsCTRC.FieldByName('CdCtrc').AsString   + ') or ';



                  {if Result <> '' then
                  begin
                        Result := Result + ', ';
                  end;

                  Result := Result + CdsCTRC.FieldByName('CdCtrc').AsString;}
            end;

            CdsCTRC.Next;
      end;

      CdsCTRC.EnableControls;

      if Result = '' then
      begin
            raise Exception.Create('Informe ao menos um CTRC');
      end else
      begin
            Delete(Result, Length(Result) -3, 3);
            Result := '<%DsComWhe%>; and (' + Result + ')';

            //Result := '<%DsComWhe%>; and T008.CdCtrc in (' + Result + ')';
      end;

end;

function TMov0002.FiltroDoTomador : String;
begin
      CdsCTRC.Data  := CdsPesCTRC.Data;
      (CdsCTRC.FieldByName('NrPeso') as TFmTBcdField).DisplayFormat  := '#,###,##0.00';
      (CdsCTRC.FieldByName('VrMercad')as TFmTBcdField).DisplayFormat := '#,###,##0.00';

      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-= Processando tomadores marcados
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      CdsTomado.DisableControls;
      CdsCtrc.Tag := 1;

      CdsCtrc.First;
      CdsTomado.Filtered := True;

      while not CdsCtrc.Eof do
      begin
            CdsTomado.Filter := 'CdTomado = ' + CdsCtrc.FieldByName('CdClient').AsString;

            if not CdsTomado.Eof then
            begin
                  if CdsTomado.FieldByName('SnMarcad').AsString <> 'S' then
                  begin
                        CdsCtrc.Delete;
                  end else
                  begin
                        CdsCtrc.Next;
                  end;
            end else
            begin
                  CdsCtrc.Delete;
            end;
      end;

      CdsTomado.Filtered := False;

      CdsTomado.First;
      CdsTomado.EnableControls;

      CdsCtrc.Tag := 0;
      CdsCtrc.First;
      if CdsCtrc.Eof then
      begin
            raise Exception.Create('Informe ao menos um tomador');
      end;

      Result := FiltroDoCtrc;
end;

function TMov0002.FiltroParaCarga : String;
begin
      Result := '';

      if  PgcProces.ActivePage = TbsCtrc then
      begin
            Result := FiltroDoCtrc;
      end
      else if PgcProces.ActivePage = TbsTomado then
           begin
                 Result := FiltroDoTomador;
           end
      else if PgcProces.ActivePage = TbsRota then
           begin
                 Result := FiltroDoTomador +
                   ' and T006.CdRota in (' + RotasSelecionadas + ') ' +
                   ' and T006.CpCidade = T002.CpCidDes';
           end
      else if PgcProces.ActivePage = TbsCidade then
           begin
                 Result := FiltroDoTomador +
                   ' and T006.CdRota   in (' + RotasSelecionadas + ') ' +
                   ' and T006.CpCidade = T002.CpCidDes' +
                   ' and T002.CpCidDes in (' + CidadesSelecionadas + ')';
           end
      ;

      Result := Result + ComplementoFiltroGeral('D');
end;

procedure TMov0002.MostrarCarga(pDsDesCar : AnsiString);
var lTtCtrc   : Integer;
    lTtPeso   : Real;
    lTtMercad : Real;
begin
      lTtCtrc   := 0;
      lTtPeso   := 0;
      lTtMercad := 0;

      CdsCarga.DisableControls;
      CdsCarga.First;
      while not CdsCarga.Eof do
      begin
            inc(lTtCtrc);
            lTtPeso   := lTtPeso   + CdsCarga.FieldByName('NrPeso').AsFloat;
            lTtMercad := lTtMercad + CdsCarga.FieldByName('VrMercad').AsFloat;

            CdsCarga.Next;
      end;

      CdsCarga.First;
      CdsCarga.EnableControls;

      LblTtCtrCar.Caption := FormatFloat('##,0', lTtCtrc);
      LblTtPesCar.Caption := FormatFloat('#,###,##0.00', lTtPeso);
      LblTtMerCar.Caption := FormatFloat('#,###,##0.00', lTtMercad);
      EdtDsCarga.Text     := pDsDesCar;

      PgcCarga.ActivePage := TbsDadBas;
      PgcFundo.ActivePage := TbsCarga;
end;

procedure TMov0002.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      (Sender as TBrvEditNum).BrParams.Clear;
      (Sender as TBrvEditNum).BrParams.Add('<%CdEmpres%>;' +
                                                 EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

procedure TMov0002.EdtCdRotaBrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      EdtCdRota.BrParams.Clear;
      EdtCdRota.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.Text);
end;

procedure TMov0002.FormCreate(Sender: TObject);
var lNrAba    : Integer;
    lDsValues : String;
    lDsItems  : String;
begin
      inherited;

      DmSrvApl.BrvDicionario.CarregarDominiosDoAtributo(
                                                 'T003', 'TPESCOLT', lDsValues, lDsItems);

      CbxTpEscolt.Values.CommaText := lDsValues;
      CbxTpEscolt.Items.CommaText  := lDsItems;

      CbxTpEscolt.Values.Insert(0, '0');
      CbxTpEscolt.Items.Insert(0, 'Sem escolta');
      CbxTpEscolt.ItemIndex := 0;

      for lNrAba := 0 to PgcFundo.PageCount -1 do
      begin
            PgcFundo.Pages[lNrAba].TabVisible := False;
      end;

      PgcFundo.ActivePage := TbsFiltro;

      for lNrAba := 0 to PgcProces.PageCount -1 do
      begin
            PgcProces.Pages[lNrAba].TabVisible := False;
      end;

      PgcProces.ActivePage := TbsRota;
end;

procedure TMov0002.MarcarTodas1Click(Sender: TObject);
begin
      DbgRotas.SetarTodasColunas('SnMarcad', 'S');
      TotalizaMarcados(CdsRotas.Data, EdtTtCtRoMa, EdtTtPeRoMa, EdtTtMeRoMa);
end;

procedure TMov0002.MenuItem1Click(Sender: TObject);
begin
      DbgCidade.SetarTodasColunas('SnMarcad', 'S');
      TotalizaMarcados(CdsCidade.Data, EdtTtCtCiMa, EdtTtPeCiMa, EdtTtMeCiMa);
end;

procedure TMov0002.MenuItem2Click(Sender: TObject);
begin
      DbgCidade.SetarTodasColunas('SnMarcad', 'N');
      TotalizaMarcados(CdsCidade.Data, EdtTtCtCiMa, EdtTtPeCiMa, EdtTtMeCiMa);
end;

procedure TMov0002.MenuItem3Click(Sender: TObject);
begin
      DbgTomado.SetarTodasColunas('SnMarcad', 'S');
      TotalizaMarcados(CdsTomado.Data, EdtTtCtToMa, EdtTtPeToMa, EdtTtMeToMa);
end;

procedure TMov0002.MenuItem4Click(Sender: TObject);
begin
      DbgTomado.SetarTodasColunas('SnMarcad', 'N');
      TotalizaMarcados(CdsTomado.Data, EdtTtCtToMa, EdtTtPeToMa, EdtTtMeToMa);
end;

procedure TMov0002.MenuItem5Click(Sender: TObject);
begin
      DbgCtrc.SetarTodasColunas('SnMarcad', 'S');
      TotalizaMarcados(CdsCTRC.Data, EdtTtCtCtMa, EdtTtPeCtMa, EdtTtMeCtMa);
end;

procedure TMov0002.MenuItem6Click(Sender: TObject);
begin
      DbgCtrc.SetarTodasColunas('SnMarcad', 'N');
      TotalizaMarcados(CdsCTRC.Data, EdtTtCtCtMa, EdtTtPeCtMa, EdtTtMeCtMa);
end;

procedure TMov0002.MenuItem7Click(Sender: TObject);
begin
      inherited;
      CblCdEstado.CheckAll(cbChecked);
end;

procedure TMov0002.MenuItem8Click(Sender: TObject);
begin
      inherited;
      CblCdEstado.CheckAll(cbUnchecked);
end;

procedure TMov0002.DbgCidadeCellClick(Column: TColumn);
begin
      inherited;

      if UpperCase(Column.FieldName) = 'SNMARCAD' then
      begin
            TotalizaMarcados(CdsCidade.Data, EdtTtCtCiMa, EdtTtPeCiMa, EdtTtMeCiMa);
      end;
end;

procedure TMov0002.DbgCtrcCellClick(Column: TColumn);
begin
      inherited;

      if UpperCase(Column.FieldName) = 'SNMARCAD' then
      begin
            TotalizaMarcados(CdsCTRC.Data, EdtTtCtCtMa, EdtTtPeCtMa, EdtTtMeCtMa);
      end;
end;

procedure TMov0002.DbgRotasCellClick(Column: TColumn);
begin
      inherited;

      if UpperCase(Column.FieldName) = 'SNMARCAD' then
      begin
            TotalizaMarcados(CdsRotas.Data, EdtTtCtRoMa, EdtTtPeRoMa, EdtTtMeRoMa);
      end;
end;

procedure TMov0002.DbgTomadoCellClick(Column : TColumn);
begin
      inherited;

      if UpperCase(Column.FieldName) = 'SNMARCAD' then
      begin
            TotalizaMarcados(CdsTomado.Data, EdtTtCtToMa, EdtTtPeToMa, EdtTtMeToMa);
      end;
end;

procedure TMov0002.DesmarcarTodas1Click(Sender: TObject);
begin
      DbgRotas.SetarTodasColunas('SnMarcad', 'N');
      TotalizaMarcados(CdsRotas.Data, EdtTtCtRoMa, EdtTtPeRoMa, EdtTtMeRoMa);
end;

initialization
      RegisterClass(TMov0002);

finalization
      UnRegisterClass(TMov0002);

end.


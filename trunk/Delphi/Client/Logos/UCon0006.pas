unit UCon0006;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, BrvRetCon, StdCtrls, BrvEditNum,
  CheckLst, Grids, BrvDbGrids, BrvDbGrid, ComCtrls, BrvBitBtn, DB, DBClient, BrvClientDataSet,
  BrvImgBot, Menus, BrvExcel, BrvListParam, ImgList, BrvCustomEdit, BrvCheckListBox,
  BrvServerProgress, mxExport;

type
  TCon0006 = class(TMov0000)
    CPT013: TBrvClientDataSet;
    CPT012: TBrvClientDataSet;
    DsT012: TDataSource;
    DsT013: TDataSource;
    DsT014: TDataSource;
    CPT014: TBrvClientDataSet;
    popCpT012: TPopupMenu;
    MarcarTodos1: TMenuItem;
    DesmarcarTodos1: TMenuItem;
    popCpT013: TPopupMenu;
    MarcarTodos2: TMenuItem;
    DesmarcarTodos2: TMenuItem;
    popCpT014: TPopupMenu;
    MarcarTodos3: TMenuItem;
    DesmarcarTodos3: TMenuItem;
    N1: TMenuItem;
    Detalhar1: TMenuItem;
    PgcFundo: TPageControl;
    TbsFiltro: TTabSheet;
    Panel4: TPanel;
    Panel1: TPanel;
    BtnPesquisa: TBrvBitBtn;
    Panel7: TPanel;
    LblTpTransp: TLabel;
    LblCdEmpres: TLabel;
    CblCdEmpres: TBrvCheckListBox;
    TbsConsulta: TTabSheet;
    Panel3: TPanel;
    Splitter2: TSplitter;
    Panel2: TPanel;
    LblQtReg: TLabel;
    BtnVoltar: TBrvBitBtn;
    Panel5: TPanel;
    StgFiltros: TStringGrid;
    PgcGuia: TPageControl;
    TbsRemete: TTabSheet;
    DbgClientes: TBrvDbGrid;
    TbsCidade: TTabSheet;
    DbgCidades: TBrvDbGrid;
    TbsConhec: TTabSheet;
    DbgCidCtrc: TBrvDbGrid;
    Panel6: TPanel;
    Label1: TLabel;
    LblNrPeso: TBrvRetCon;
    LblVrMercad: TBrvRetCon;
    Label3: TLabel;
    CblTpTransp: TBrvCheckListBox;
    BrvListParam: TBrvListParam;
    BrvServerProgress: TBrvServerProgress;
    mxExcel: TmxDataSetExport;
    BtnDetaExce: TBrvBitBtn;
    procedure FormCreate(Sender: TObject);
    procedure PgcGuiaChange(Sender: TObject);
    procedure MarcarTodos1Click(Sender: TObject);
    procedure DesmarcarTodos1Click(Sender: TObject);
    procedure MarcarTodos2Click(Sender: TObject);
    procedure DesmarcarTodos2Click(Sender: TObject);
    procedure MarcarTodos3Click(Sender: TObject);
    procedure DesmarcarTodos3Click(Sender: TObject);
    procedure Detalhar1Click(Sender: TObject);
    procedure BtnDetaExceClick(Sender: TObject);
    procedure BrvBitBtn1Click(Sender: TObject);
    procedure BtnVoltarClick(Sender: TObject);
    procedure BtnPesquisaClick(Sender: TObject);
  private
    { Private declarations }
    procedure Totaliza(CdsArg : TClientDataSet);
    function  ClientesSelecionados: WideString;
    function  CidadesSelecionadas: WideString;
    procedure SetaEmpresas;
    procedure SetaTipoTransporte;
    procedure SetaClientes;
    procedure SetaCidades;
    procedure RecuperaListaRemetentes;
    procedure RecuperaListaCidades;
    procedure RecuperaListaCTRCs;
    procedure DetalharCTRC();
    procedure ValidaEntradaDados;
    procedure AtivaGuia(pNrIndice : Integer);
  public
    { Public declarations }
  end;

var
  Con006: TCon0006;

implementation

uses UDmSrvApl, BrvFuncoesXE, BrvDominiosXE, UDmTra;

var gDsClient : WideString;
    gTpTransp : WideString;
    gDsCidade : WideString;
    gDsEmpres : String;
    gNrPosT12 : Integer;
    gNrPosT13 : Integer;
    gNrPosT14 : Integer;

{$R *.dfm}

//***********************************************************************************************//

function TCon0006.ClientesSelecionados(): WideString;
var   DsClientes  :   WideString;
begin
      DsClientes  :=  RetornaSelecionados(CpT012, 'CdRemete');
      if Trim(DsClientes) = '' then
      begin
            BtnVoltarClick(nil);
            raise Exception.Create('Nenhum Remetente foi selecionado!');
      end;
      Result      :=  DsClientes;
end;

function TCon0006.CidadesSelecionadas(): WideString;
var   DsCidades  :   WideString;
begin
      DsCidades  :=  RetornaSelecionados(CpT013, 'CpCidade');
      if Trim(DsCidades) = '' then
      begin
            BtnVoltarClick(nil);
            raise Exception.Create('Nenhuma Cidade foi selecionada!');
      end;
      Result     :=  DsCidades;
end;

//***********************************************************************************************//

procedure TCon0006.BrvBitBtn1Click(Sender: TObject);
begin
      inherited;
      try
          ExportaBrvExcel(CPT014.Data);
      finally
          //StatusMSG(StbDetalhes, 'OK, dados exportados.');
      end;
end;

procedure TCon0006.BtnDetaExceClick(Sender: TObject);
var lNrIndice : Integer;
    lCtT014   : TClientDataSet;
begin
      if (BtnDetaExce.Hint = 'Excel') then
      begin
            try
                lCtT014 := TClientDataSet.Create(Self);
                lCtT014.Data := CPT014.Data;
                mxExcel.DataSet := lCtT014;
                mxExcel.DataSet.Filtered := False;
                mxExcel.DataSet.Filter   := 'SnMarcad <> ' + QuotedStr('N');
                mxExcel.DataSet.Filtered := True;
                mxExcel.Captions.Clear;
                for lNrIndice := 0 to DbgCidCtrc.Columns.Count-1 do
                begin
                      mxExcel.Captions.Add(DbgCidCtrc.Columns[lNrIndice].Title.Caption);
                end;
                mxExcel.Select;
            finally
                FreeAndNil(lCtT014);
            end;
      end else if (BtnDetaExce.Hint = 'Detalhar') then
      begin
            if (PgcGuia.ActivePageIndex < 3) then
            begin
                  AtivaGuia(PgcGuia.ActivePageIndex + 1);
            end;
      end;
end;

procedure TCon0006.BtnPesquisaClick(Sender: TObject);
begin
      inherited;
      gDsEmpres := '';
      gTpTransp := '';
      gDsClient := '';
      gDsCidade := '';
      gNrPosT12 := 0;
      gNrPosT13 := 0;
      gNrPosT14 := 0;

      if (CPT012.Active) then
      begin
            CPT012.EmptyDataSet;
            CPT012.Close;
      end;
      if (CPT013.Active) then
      begin
            CPT013.EmptyDataSet;
            CPT013.Close;
      end;
      if (CPT014.Active) then
      begin
            CPT014.EmptyDataSet;
            CPT014.Close;
      end;

      TbsRemete.TabVisible := False;
      TbsCidade.TabVisible := False;
      TbsConhec.TabVisible := False;

      ValidaEntradaDados;

      AtivaGuia(0);
      PgcFundo.ActivePage := TbsConsulta;
end;

procedure TCon0006.AtivaGuia(pNrIndice : Integer);
begin
      PgcGuia.ActivePageIndex := pNrIndice;
      PgcGuiaChange(nil);
end;

procedure TCon0006.ValidaEntradaDados;
var lLsTpDomini : TStringList;
begin
      if (CblCdEmpres.BrCheckedCount = 0) then
      begin
            CblCdEmpres.SetFocus;
            raise Exception.Create('Selecione a(s) ' + LblCdEmpres.Caption + '!');
      end;

      if (CblTpTransp.BrCheckedCount = 0) then
      begin
            CblTpTransp.SetFocus;
            raise Exception.Create('Selecione o(s) ' + LblTpTransp.Caption + '!');
      end;
end;

procedure TCon0006.BtnVoltarClick(Sender: TObject);
begin
      inherited;
      if (PgcGuia.ActivePageIndex = 0) then
      begin
            PgcFundo.ActivePage := TbsFiltro;
      end else
      begin
            AtivaGuia(PgcGuia.ActivePageIndex - 1);
      end;
end;

//***********************************************************************************************//

procedure TCon0006.RecuperaListaRemetentes;
var lDsEmpres : String;
    lTpTransp : WideString;
begin
      { Empresas }
      lDsEmpres :=  gDsEmpres;
      SetaEmpresas;

      { Tipo de Transporte }
      lTpTransp := gTpTransp;
      SetaTipoTransporte;

      if ((lDsEmpres <> gDsEmpres)  or
          (lTpTransp <> gTpTransp)  or not
          (CpT012.Active         )) then
      begin
            try
                BrvServerProgress.Start(Self.Caption, 'Buscando Remetentes...', 100, 10);
                CpT012.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(53,
                                                                 BrvListParam.AsBrParam, Self.Name);
            finally
                BrvServerProgress.Stop;
                if (CpT012.RecordCount = 0) then
                begin
                      raise Exception.Create('Nenhum remetente foi encontrado!!!');
                end;
                FormataCamposDecimais(CpT012);
                gNrPosT12 := 0;
            end;
      end;

      Totaliza(CpT012);

      if (gNrPosT12 > 0) then
      begin
            CpT012.MoveBy(gNrPosT12-1);
            gNrPosT12 := 0;
      end;
end;

//***********************************************************************************************//

procedure TCon0006.RecuperaListaCidades();
var NrSequen    : Word;
    StTemTip    : Boolean;
    lDsEmpres : String;
    lTpTransp : WideString;
    lDsClient : WideString;
begin
      { Empresas }
      lDsEmpres := gDsEmpres;
      SetaEmpresas;

      { Tipo de Transporte }
      lTpTransp := gTpTransp;
      SetaTipoTransporte;

      { Clientes }
      lDsClient := gDsClient;
      if (CPT012.Active) then
      begin
            SetaClientes;
      end else
      begin
            BrvListParam.Add('CdClient', '', '', '""');
      end;

      if ((lDsEmpres <> gDsEmpres)  or
          (lTpTransp <> gTpTransp)  or
          (lDsClient <> gDsClient)  or not
          (CpT013.Active         )) then
      begin
            try
                BrvServerProgress.Start(Self.Caption, 'Buscando Cidades...', 100, 10);
                CpT013.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(55,
                                                                 BrvListParam.AsBrParam, Self.Name);
            finally
                BrvServerProgress.Stop;
                FormataCamposDecimais(CpT013);
                gNrPosT13 := 0;
            end;
      end;

      Totaliza(CpT013);

      if (gNrPosT13 > 0) then
      begin
            CpT013.MoveBy(gNrPosT13-1);
            gNrPosT13 := 0;
      end;
end;

//***********************************************************************************************//

procedure TCon0006.RecuperaListaCTRCs();
var NrSequen  : Word;
    StTemTip  : Boolean;
    lDsEmpres : String;
    lTpTransp : WideString;
    lDsClient : WideString;
    lDsCidade : WideString;
begin
      { Empresas }
      lDsEmpres := gDsEmpres;
      SetaEmpresas;

      { Tipo de Transporte }
      lTpTransp := gTpTransp;
      SetaTipoTransporte;

      { Clientes }
      lDsClient := gDsClient;
      if (CPT012.Active) then
      begin
            SetaClientes;
      end else
      begin
            BrvListParam.Add('CdClient', '', '', '""');
      end;

      { Cidades }
      lDsCidade := gDsCidade;
      if (CPT013.Active) then
      begin
            SetaCidades;
      end else
      begin
            BrvListParam.Add('CpCidade', '', '', '""');
      end;

      if ((lDsEmpres <> gDsEmpres)  or
          (lTpTransp <> gTpTransp)  or
          (lDsClient <> gDsClient)  or
          (lDsCidade <> gDsCidade)  or not
          (CpT014.Active         )) then
      begin
            try
                BrvServerProgress.Start(Self.Caption, 'Buscando Conhecimentos...', 100, 10);
                CpT014.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(56,
                                                                 BrvListParam.AsBrParam, Self.Name);
            finally
                BrvServerProgress.Stop;
                FormataCamposDecimais(CpT014);
                gNrPosT14 := 0;
            end;
      end;

      Totaliza(CpT014);

      if (gNrPosT14 > 0) then
      begin
            CpT014.MoveBy(gNrPosT14-1);
            gNrPosT14 := 0;
      end;
end;

//***********************************************************************************************//

procedure TCon0006.Totaliza(CdsArg : TClientDataSet);
var   lVlMercad, lVlPeso  : Double ;
      lBookMark : TBookmark;
begin

      CdsArg.DisableControls;
      CdsArg.First;

      while not CdsArg.Eof do
      begin
            lVlMercad := lVlMercad + CdsArg.FieldByName('VrMercad').AsFloat;
            lVlPeso   := lVlPeso   + CdsArg.FieldByName('NrPeso').AsFloat;

            CdsArg.Next;
      end;

      LblNrPeso.Text   := FormatFloat('###,###,##0.00',   lVlPeso);
      LblVrMercad.Text := FormatFloat('###,###,##0.00', lVlMercad);

      CdsArg.First;
      CdsArg.EnableControls;

      BrvListParam.SetStgParam(StgFiltros);
      LblQtReg.Caption := FormatFloat('0', CdsArg.RecordCount) + ' Registro(s)';
end;

//***********************************************************************************************//

procedure TCon0006.FormCreate(Sender: TObject);
var lNrIndice : Integer;
begin
      inherited;
      TbsFiltro.TabVisible   := False;
      TbsConsulta.TabVisible := False;
      PgcFundo.ActivePage    := TbsFiltro;

      CarregaEmpresas(CblCdEmpres, True);
      CarregaDominios(CblTpTransp, 'T002', 'TPTRANSP', True);

      StgFiltros.ColWidths[0] := 135;
      StgFiltros.ColWidths[1] := 635;
end;

procedure TCon0006.MarcarTodos1Click(Sender: TObject);
begin
      DbgClientes.SetarTodasColunas('SnMarcad', 'S');
end;

procedure TCon0006.DesmarcarTodos1Click(Sender: TObject);
begin
      DbgClientes.SetarTodasColunas('SnMarcad', 'N');
end;

procedure TCon0006.MarcarTodos2Click(Sender: TObject);
begin
      DbgCidades.SetarTodasColunas('SnMarcad', 'S');
end;

procedure TCon0006.DesmarcarTodos2Click(Sender: TObject);
begin
      DbgCidades.SetarTodasColunas('SnMarcad', 'N');
end;

procedure TCon0006.MarcarTodos3Click(Sender: TObject);
begin
      DbgCidCtrc.SetarTodasColunas('SnMarcad', 'S');
end;

procedure TCon0006.DesmarcarTodos3Click(Sender: TObject);
begin
      DbgCidCtrc.SetarTodasColunas('SnMarcad', 'N');
end;

procedure TCon0006.Detalhar1Click(Sender: TObject);
begin
      inherited;
      DetalharCTRC();
end;

procedure TCon0006.DetalharCTRC();
begin
      inherited;
      DmTra.VisualizarConhecimento(CPT014);
end;

//***********************************************************************************************//

procedure TCon0006.PgcGuiaChange(Sender: TObject);
begin
      BrvListParam.Clear;

      if PgcGuia.ActivePageIndex = 0 then
      begin
            if((CpT013.Active) and (gNrPosT13 = 0)) then
            begin
                  gNrPosT13 := CpT013.RecNo;
            end;
            if((CpT014.Active) and (gNrPosT14 = 0)) then
            begin
                  gNrPosT14 := CpT014.RecNo;
            end;

            RecuperaListaRemetentes;
            BtnDetaExce.Hint        := 'Detalhar';
            BtnDetaExce.Caption     := '&Detalhar';
            BtnDetaExce.BrTipoBotao := BrBtnPesqui;
            if (PgcFundo.ActivePageIndex = 1) then
            begin
                  DbgClientes.SetFocus;
            end;
      end;

      if PgcGuia.ActivePageIndex = 1 then
      begin
            if((CpT012.Active) and (gNrPosT12 = 0)) then
            begin
                  gNrPosT12 := CpT012.RecNo;
            end;
            if((CpT014.Active) and (gNrPosT14 = 0)) then
            begin
                  gNrPosT14 := CpT014.RecNo;
            end;

            RecuperaListaCidades;
            BtnDetaExce.Hint        := 'Detalhar';
            BtnDetaExce.Caption     := '&Detalhar';
            BtnDetaExce.BrTipoBotao := BrBtnPesqui;
            DbgCidades.SetFocus;
      end;

      if PgcGuia.ActivePageIndex = 2 then
      begin
            if((CpT012.Active) and (gNrPosT12 = 0)) then
            begin
                  gNrPosT12 := CpT012.RecNo;
            end;
            if((CpT013.Active) and (gNrPosT13 = 0)) then
            begin
                  gNrPosT13 := CpT013.RecNo;
            end;

            RecuperaListaCTRCs;
            BtnDetaExce.Hint        := 'Excel';
            BtnDetaExce.Caption     := '&Excel';
            BtnDetaExce.BrTipoBotao := BrBtnExcel;
            DbgCidCtrc.SetFocus;
      end;
end;

//***********************************************************************************************//
{ Empresa(s) selecionada(s) pelo usuário }
procedure TCon0006.SetaEmpresas();
begin
      gDsEmpres := CblCdEmpres.BrCheckedList;
      BrvListParam.Add('CdEmpAtu', LblCdEmpres.Caption, gDsEmpres, gDsEmpres);
end;

//***********************************************************************************************//
{ Tipos de Transporte selecionados pelo usuário }
procedure TCon0006.SetaTipoTransporte();
var lLsTpTransp : TStringList;
begin
      try
          lLsTpTransp := CblTpTransp.BrCheckedListDominio;
          gTpTransp := lLsTpTransp[0];
          BrvListParam.Add('TpTransp', LblTpTransp.Caption, lLsTpTransp[1], lLsTpTransp[0]);
      finally
          FreeAndNil(lLsTpTransp);
      end;
end;

//***********************************************************************************************//
{ Clientes selecionados pelo usuário }
procedure TCon0006.SetaClientes();
begin
      gDsClient :=  '';
      gDsClient :=  ClientesSelecionados;
      BrvListParam.Add('CdClient', 'Remetente(s)', gDsClient, gDsClient);
end;

//***********************************************************************************************//
{ Cidades selecionadas pelo usuário }
procedure TCon0006.SetaCidades();
begin
      gDsCidade :=  '';
      gDsCidade :=  CidadesSelecionadas;
      BrvListParam.Add('CpCidade', 'Cidade(s)', gDsCidade, gDsCidade);
end;

//***********************************************************************************************//

initialization
      RegisterClass(TCon0006);

finalization
      UnRegisterClass(TCon0006);

end.

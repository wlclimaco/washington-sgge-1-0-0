unit UCon0007;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, BrvRetCon, StdCtrls, BrvEditNum,
  CheckLst, Grids, BrvDbGrids, BrvDbGrid, ComCtrls, BrvBitBtn, DB, DBClient, BrvClientDataSet,
  BrvImgBot, Menus, BrvExcel, BrvCustomEdit, BrvListParam, ImgList, BrvCheckListBox,
  BrvServerProgress;

type
  TCon0007 = class(TMov0000)
    DsT015: TDataSource;
    CPT015: TBrvClientDataSet;
    PgcFundo: TPageControl;
    TbsFiltro: TTabSheet;
    Panel4: TPanel;
    Panel2: TPanel;
    BtnPesquisa: TBrvBitBtn;
    TbsConsulta: TTabSheet;
    Panel3: TPanel;
    Splitter2: TSplitter;
    Panel5: TPanel;
    LblQtReg: TLabel;
    BtnExcel: TBrvBitBtn;
    BtnVoltar: TBrvBitBtn;
    Panel6: TPanel;
    StgFiltros: TStringGrid;
    BdgRegistros: TBrvDbGrid;
    BrvListParam: TBrvListParam;
    BrvServerProgress: TBrvServerProgress;
    BrvExcel: TBrvExcel;
    Panel1: TPanel;
    LblCdEmpres: TLabel;
    CblCdEmpres: TBrvCheckListBox;
    LblCdVeicul: TLabel;
    EdtCdVeicul: TBrvEditNum;
    EdtDsVeicul: TBrvRetCon;
    procedure Button1Click(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure BtnExcelClick(Sender: TObject);
    procedure BtnPesquisaClick(Sender: TObject);
    procedure BtnVoltarClick(Sender: TObject);
  private
    procedure RecuperaDados;
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Con0007: TCon0007;

implementation

uses UDmSrvApl, BrvFuncoesXE, BrvDominiosXE;

{$R *.dfm}

procedure TCon0007.BtnExcelClick(Sender: TObject);
var lLsColuna : TStringList;
    lCtP002   : TClientDataSet;
    lNrIdx    : Integer;
begin
      lLsColuna := TStringList.Create;

      for lNrIdx := 0 to BdgRegistros.Columns.Count - 1 do
      begin
            lLsColuna.Add(BdgRegistros.Columns[lNrIdx].FieldName);
      end;

      try
          Screen.Cursor := crHourGlass;
          lCtP002       := TClientDataSet.Create(Self);
          lCtP002.Data  := CpT015.Data;

          for lNrIdx := 0 to BdgRegistros.Columns.Count - 1 do
          begin
                lLsColuna.Add(BdgRegistros.Columns[lNrIdx].FieldName);
                lCtP002.FieldByName(BdgRegistros.Columns[lNrIdx].FieldName).DisplayLabel :=
                                                         BdgRegistros.Columns[lNrIdx].Title.Caption;
          end;

          lCtP002.First;
          BrvExcel.BrDataSet := CpT015;
          BrvExcel.Execute(lLsColuna, '', nil);
      finally
          FreeAndNil(lLsColuna);
          Screen.Cursor := crDefault;
      end;
end;

procedure TCon0007.BtnPesquisaClick(Sender: TObject);
begin
      RecuperaDados;
      FormataCamposDecimais(CpT015);
      if CpT015.RecordCount > 0 then
      begin
            PgcFundo.ActivePage := TbsConsulta;
            LblQtReg.Caption := FormatFloat('0', CpT015.RecordCount) + ' Registro(s)';
      end else
      begin
            MessageDlg('Nenhum registro encontrado!!!', mtInformation, [mbok], 0);
      end;

end;

procedure TCon0007.BtnVoltarClick(Sender: TObject);
begin
      PgcFundo.ActivePage := TbsFiltro;
end;

procedure TCon0007.RecuperaDados();
var
    lDsEmpres : String;
    lNrIdx    : Integer;
begin
      if (CblCdEmpres.BrCheckedCount = 0) then
      begin
            CblCdEmpres.SetFocus;
            raise Exception.Create('Selecione a(s) ' + LblCdEmpres.Caption + '!');
      end else
      begin
            lDsEmpres := CblCdEmpres.BrCheckedList;
            BrvListParam.Add('CdEmpres', LblCdEmpres.Caption, lDsEmpres, lDsEmpres);
      end;

      if EdtCdVeicul.BrAsInteger > 0 then
      begin
            BrvListParam.Add('CdVeicul', LblCdVeicul.Caption, EdtCdVeicul.Text,
                                                      ' and Cargas.CdVeicul = ' + EdtCdVeicul.Text);
      end else
      begin
            BrvListParam.Add('CdVeicul', '', '', '');
      end;

      try
          BrvServerProgress.Start(Self.Caption, 'Reunindo informações', 100, 10);
          CpT015.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(57,
                                                             BrvListParam.AsBrParam, Self.Name);
      finally
          BrvServerProgress.Stop;
      end;
      BrvListParam.SetStgParam(StgFiltros);
end;

procedure TCon0007.Button1Click(Sender: TObject);
var  lDataServidor : TDateTime;
     lDataString   : String;
begin
      lDataString   :=  DmSrvApl.BrvDicionario.DataServerStr;
      lDataServidor :=  DmSrvApl.BrvDicionario.DataServer;
      ShowMessage(lDataString);
end;

procedure TCon0007.FormCreate(Sender: TObject);
var lNrIdx : Integer;
begin
      inherited;
      TbsFiltro.TabVisible   := False;
      TbsConsulta.TabVisible := False;
      PgcFundo.ActivePage    := TbsFiltro;

      CarregaEmpresas(CblCdEmpres, True);
end;

initialization
      RegisterClass(TCon0007);

finalization
      UnRegisterClass(TCon0007);

end.

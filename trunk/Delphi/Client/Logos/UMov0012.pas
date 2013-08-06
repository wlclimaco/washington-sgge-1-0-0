unit UMov0012;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, StdCtrls, BrvComboBox,
  BrvRetCon, BrvEditNum, Mask, BrvEditDate, DbClient, Grids, BrvDbGrids, BrvDbGrid, BrvBitBtn, DB,
  BrvClientDataSet, Menus, BrvListParam, ImgList, BrvCustomEdit;

type
  TMov0012 = class(TMov0000)
    PnlCabeca: TPanel;
    Label1: TLabel;
    Label17: TLabel;
    EdtCdArmaze: TBrvEditNum;
    EdtNmArmaze: TBrvRetCon;
    CbxStOpeLog: TBrvComboBox;
    Label2: TLabel;
    bdgRegistros: TBrvDbGrid;
    BtnLocali: TBrvBitBtn;
    DsW005: TDataSource;
    CPW005: TBrvClientDataSet;
    PnlOperacao: TPanel;
    BtnCancel: TBrvBitBtn;
    BtnSalvar: TBrvBitBtn;
    BtnAtualiza : TBrvBitBtn;
    PopSuspensa: TPopupMenu;
    SuspenderestaAtividade1: TMenuItem;
    AtivarestaAtividade1: TMenuItem;
    SuspenderTodas1: TMenuItem;
    AtivarTodas1: TMenuItem;
    Label3: TLabel;
    LblQtdeNF: TLabel;
    CbxDsTipMov: TBrvComboBox;
    procedure FormCreate(Sender: TObject);
    procedure BtnLocaliClick(Sender: TObject);
    procedure CbxStOpeLogChange(Sender: TObject);
    procedure bdgRegistrosDrawColumnCell(Sender: TObject; const Rect: TRect; DataCol: Integer;
      Column: TColumn; State: TGridDrawState);
    procedure BtnAtualizaClick(Sender: TObject);
    procedure BtnCancelClick(Sender: TObject);
    procedure BtnSalvarClick(Sender: TObject);
    procedure SuspenderestaAtividade1Click(Sender: TObject);
    procedure AtivarestaAtividade1Click(Sender: TObject);
    procedure CPW005AfterPost(DataSet: TDataSet);
    procedure SuspenderTodas1Click(Sender: TObject);
    procedure AtivarTodas1Click(Sender: TObject);
    procedure bdgRegistrosDblClick(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Mov0012: TMov0012;

implementation

uses UDmSrvApl, UClaSrv, UMov0013;

{$R *.dfm}

procedure TMov0012.BtnAtualizaClick(Sender: TObject);
begin
      inherited;
      BtnLocaliClick(BtnLocali);
end;

procedure TMov0012.BtnCancelClick(Sender: TObject);
begin
      inherited;
      CPW005.Close;
      BtnSalvar.Visible := False;
      PnlCabeca.Enabled := True;
      PnlOperacao.Visible := False;
end;

procedure TMov0012.CbxStOpeLogChange(Sender: TObject);
begin
      inherited;
      if (CbxStOpeLog.Values.Strings[CbxStOpeLog.ItemIndex] = 'A') or
         (CbxStOpeLog.Values.Strings[CbxStOpeLog.ItemIndex] = 'S') then
      begin
            bdgRegistros.ReadOnly  := False;
            BtnAtualiza.Visible    := False;
            bdgRegistros.PopupMenu := PopSuspensa;
      end else
      begin
            bdgRegistros.ReadOnly  := True;
            BtnSalvar.Visible      := False;
            BtnAtualiza.Visible    := True;
            bdgRegistros.PopupMenu := Nil;
      end;
end;

procedure TMov0012.CPW005AfterPost(DataSet: TDataSet);
begin
      inherited;
      BtnSalvar.Visible := True;
end;

procedure TMov0012.FormCreate(Sender: TObject);
var lDsDomini : String;
    lVrDomini : String;
begin
      inherited;

      DmSrvApl.BrvDicionario.CarregarDominiosDoAtributo('W005', 'StOpeLog',
                                                                lVrDomini, lDsDomini);

      CbxStOpeLog.Items.Clear;
      CbxStOpeLog.Values.Clear;

      CbxStOpeLog.Items.CommaText  := lDsDomini;
      CbxStOpeLog.Values.CommaText := lVrDomini;

      CbxStOpeLog.Items.Insert(0, 'Todos');
      CbxStOpeLog.Values.Insert(0, '0');

      CbxStOpeLog.ItemIndex := 0;
      CbxDsTipMov.ItemIndex := 0;

      CbxStOpeLog.Items.Delete(CbxStOpeLog.Items.IndexOf('Finalizada'));
      CbxStOpeLog.Values.Delete(CbxStOpeLog.Values.IndexOf('F'));
end;

procedure TMov0012.SuspenderestaAtividade1Click(Sender: TObject);
begin
      inherited;
      if (CPW005.FieldByName('StOpeLog').AsString <> 'S') then
      begin
            CPW005.Edit;
            CPW005.FieldByName('StOpeLog').AsString := 'S';
            CPW005.FieldByName('DsOpeLog').AsString := 'Suspensa';
            CPW005.Post;
      end;
end;

procedure TMov0012.SuspenderTodas1Click(Sender: TObject);
begin
      inherited;
      try
          CPW005.DisableControls;
          CPW005.First;

          while not CPW005.Eof do
          begin
                if (CPW005.FieldByName('StOpeLog').AsString <> 'S') then
                begin
                      CPW005.Edit;
                      CPW005.FieldByName('StOpeLog').AsString := 'S';
                      CPW005.FieldByName('DsOpeLog').AsString := 'Suspensa';
                      CPW005.Post;
                end;

                CPW005.Next;
          end;
      finally
          CPW005.EnableControls;
      end;
end;

procedure TMov0012.AtivarestaAtividade1Click(Sender: TObject);
begin
      inherited;
      if (CPW005.FieldByName('StOpeLog').AsString <> 'A') then
      begin
            CPW005.Edit;
            CPW005.FieldByName('StOpeLog').AsString := 'A';
            CPW005.FieldByName('DsOpeLog').AsString := 'Aguardando';
            CPW005.Post;
      end;
end;

procedure TMov0012.AtivarTodas1Click(Sender: TObject);
begin
      inherited;
      try
          CPW005.DisableControls;
          CPW005.First;

          while not CPW005.Eof do
          begin
                if (CPW005.FieldByName('StOpeLog').AsString <> 'A') then
                begin
                      CPW005.Edit;
                      CPW005.FieldByName('StOpeLog').AsString := 'A';
                      CPW005.FieldByName('DsOpeLog').AsString := 'Aguardando';
                      CPW005.Post;
                end;

                CPW005.Next;
          end;
      finally
          CPW005.EnableControls;
      end;
end;

procedure TMov0012.bdgRegistrosDblClick(Sender: TObject);
begin
      inherited;
      if (CPW005.FieldByName('CdTipAti').AsInteger < 4) then
      begin
            try
                Mov0013 := TMov0013.Create(Self);
                Mov0013.EdtNrOpeLog.Text := CPW005.FieldByName('NrOpeLog').AsString;
                Mov0013.EdtNmUsuGer.Text := CPW005.FieldByName('NmUsuExe').AsString;
                Mov0013.EdtNmUsuExe.Text := CPW005.FieldByName('NmUsuExe').AsString;
                Mov0013.EdtDtGeraca.Text := CPW005.FieldByName('DtGeraca').AsString;
                Mov0013.EdtDtIniOpe.Text := CPW005.FieldByName('DtIniOpe').AsString;
                Mov0013.EdtDtFimOpe.Text := CPW005.FieldByName('DtFimOpe').AsString;
                Mov0013.EdtQtProSid.Text := CPW005.FieldByName('QtProSid').AsString;
                Mov0013.EdtDsTipAti.Text := CPW005.FieldByName('DsTipAti').AsString;

                Mov0013.AbrirDadosOperacaoLogistica(EdtCdArmaze.Text,
                                                    CPW005.FieldByName('NrOpeLog').AsString);


                Mov0013.BtnSalvar.Visible  := (CPW005.FieldByName('StOpeLog').AsString = 'D');
                Mov0013.BtnRefazer.Visible := (CPW005.FieldByName('StOpeLog').AsString = 'D');
                Mov0013.ShowModal;

                if (Mov0013.ModalResult = mrOk) then
                begin
                      BtnLocaliClick(BtnLocali);
                end;

            finally
                FreeAndNil(Mov0013);
            end;
      end;
end;

Procedure TMov0012.bdgRegistrosDrawColumnCell(Sender: TObject; const Rect: TRect; DataCol: Integer;
                                                            Column: TColumn; State: TGridDrawState);
begin
      inherited;
      if (CPW005.UpdateStatus <> usUnmodified) then
      begin
            if not (gdSelected in State) then
            begin
                  bdgRegistros.Canvas.FillRect(Rect);
                  bdgRegistros.Canvas.Brush.Color := clSkyBlue;
                  bdgRegistros.Canvas.Font.Color  := clNavy;
                  bdgRegistros.DefaultDrawDataCell(Rect, Column.Field, State);
            end else
            begin
                  bdgRegistros.Canvas.FillRect(Rect);
                  bdgRegistros.Canvas.Font.Color  := clBlack;
                  bdgRegistros.DefaultDrawDataCell(Rect, Column.Field, State);
            end;
      end else
      begin
            bdgRegistros.Canvas.FillRect(Rect);
            bdgRegistros.Canvas.Font.Color  := clBlack;
            bdgRegistros.DefaultDrawDataCell(Rect, Column.Field, State);
      end;
end;


procedure TMov0012.BtnLocaliClick(Sender: TObject);
var lStOpeLog : String;
    lDsDomini : String;
    lVrDomini : String;
begin
      inherited;
      if (StrToIntDef(EdtCdArmaze.Text, 0) = 0) then
      begin
           raise Exception.Create('Informe o Armazém');
      end;

      if (CbxStOpeLog.Values[CbxStOpeLog.ItemIndex] = '0') then
      begin
            lStOpeLog := '<%StOpeLog%>; And StOpeLog not in ("C", "F") ';
      end else
      begin
            lStOpeLog := '<%StOpeLog%>; And StOpeLog = ' +
                                               QuotedStr(CbxStOpeLog.Values[CbxStOpeLog.ItemIndex]);
      end;

      CPW005.Close;
      CPW005.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(135,
                                                     '<%CdArmaze%>;' + EdtCdArmaze.Text + Chr(13) +
                                                     '<%CdTipAti%>;' +
                                                     CbxDsTipMov.Values[CbxDsTipMov.ItemIndex] +
                                                     chr(13) + lStOpeLog, Self.Name);
      CPW005.Open;

      LblQtdeNF.Caption := FormatFloat('0', CPW005.RecordCount);

      PnlCabeca.Enabled   := False;
      PnlOperacao.Visible := True;
end;

procedure TMov0012.BtnSalvarClick(Sender: TObject);
var lDsListOp : AnsiString;
    lConexao  : TSDmWmsClient;
    lDsResult : AnsiString;
begin
      inherited;

      try
          CPW005.First;
          CPW005.DisableControls;

          while not CPW005.eof do
          begin
                if (CPW005.UpdateStatus <> usUnmodified) then
                begin
                      lDsListOp := lDsListOp + CPW005.FieldByName('NrOpeLog').AsString + ',' +
                                               CPW005.FieldByName('NrPriori').AsString + ',' +
                                               CPW005.FieldByName('StOpeLog').AsString + Chr(13);
                end;

                CPW005.Next;
          end;

          lConexao := TSDmWmsClient.Create(DmSrvApl.SrvAplica.DBXConnection);
          lDsResult := lConexao.GravaPrioridadeSituacaoOpeLog(EdtCdArmaze.Text, lDsListOp);
          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
          BtnSalvar.Visible := False;
      finally
          FreeAndNil(lConexao);
          BtnLocaliClick(BtnLocali);
          CPW005.EnableControls;
      end;

end;

initialization
      RegisterClass(TMov0012);

finalization
      UnRegisterClass(TMov0012);

end.

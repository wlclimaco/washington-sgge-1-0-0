unit UMov0013;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, StdCtrls, BrvBitBtn, Grids,
  BrvDbGrids, BrvDbGrid, BrvRetCon, DB, DBClient, BrvClientDataSet, ComCtrls, BrvLogScreen,
  BrvImgBot, BrvImage, SqlExpr, BrvListParam, ImgList, Menus;

type
  TMov0013 = class(TMov0000)
    PnlOperacao: TPanel;
    BtnCancel: TBrvBitBtn;
    BtnSalvar: TBrvBitBtn;
    Panel3: TPanel;
    Panel4: TPanel;
    Splitter2: TSplitter;
    Panel5: TPanel;
    Panel6: TPanel;
    Splitter3: TSplitter;
    BtnRefazer: TBrvBitBtn;
    DbgW006: TBrvDbGrid;
    DbgW007: TBrvDbGrid;
    Panel7: TPanel;
    Label1: TLabel;
    EdtNrOpeLog: TBrvRetCon;
    Label2: TLabel;
    EdtNmUsuGer: TBrvRetCon;
    Label3: TLabel;
    EdtNmUsuExe: TBrvRetCon;
    Label4: TLabel;
    Label5: TLabel;
    Label6: TLabel;
    EdtDtGeraca: TBrvRetCon;
    EdtDtIniOpe: TBrvRetCon;
    EdtDtFimOpe: TBrvRetCon;
    Label7: TLabel;
    EdtNrNfOrig: TBrvRetCon;
    Label8: TLabel;
    EdtNrSENFOR: TBrvRetCon;
    DtsW006: TDataSource;
    CdsW006: TClientDataSet;
    CdsW007: TClientDataSet;
    DtsW007: TDataSource;
    DtsW008: TDataSource;
    CdsW008: TBrvClientDataSet;
    Label9: TLabel;
    Label10: TLabel;
    PgcColeta: TPageControl;
    TbsConfAtual: TTabSheet;
    TabSheet2: TTabSheet;
    DbgW008: TBrvDbGrid;
    DbgW008H: TBrvDbGrid;
    CdsW008H: TBrvClientDataSet;
    DtsW008H: TDataSource;
    Label11: TLabel;
    EdtQtProSid: TBrvRetCon;
    Label12: TLabel;
    EdtNrFornec: TBrvRetCon;
    BtnCancelar: TBrvBitBtn;
    EdtDsTipAti: TBrvRetCon;
    Label13: TLabel;
    EdtQtProduto: TBrvRetCon;
    BrvLogScreen: TBrvLogScreen;
    procedure BtnCancelClick(Sender: TObject);
    procedure CdsW006AfterScroll(DataSet: TDataSet);
    procedure BtnRefazerClick(Sender: TObject);
    procedure CdsW007AfterScroll(DataSet: TDataSet);
    procedure BtnSalvarClick(Sender: TObject);
    procedure DbgW006DrawColumnCell(Sender: TObject; const Rect: TRect; DataCol: Integer;
      Column: TColumn; State: TGridDrawState);
    procedure BtnCancelarClick(Sender: TObject);
  private
    { Private declarations }
  public
    gCdArmaze : String;
    { Public declarations }
    Procedure AbrirDadosOperacaoLogistica(pCdArmaze, pNrOpeLog: String);
  end;

var
  Mov0013: TMov0013;

implementation

uses UDmSrvApl, UClaSrv;

{$R *.dfm}

{ TMov0013 }

procedure TMov0013.AbrirDadosOperacaoLogistica(pCdArmaze, pNrOpeLog: String);
var lDsFiltro : String;
begin
      gCdArmaze := pCdArmaze;

      lDsFiltro := '<%CdArmaze%>;' + pCdArmaze + Chr(13) + '<%NrOpeLog%>;' + pNrOpeLog;

      CdsW006.Close;
      CdsW006.Data  := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(136, lDsFiltro, self.Name);
      CdsW006.Open;

      CdsW007.Close;
      CdsW007.Data  := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(137, lDsFiltro, self.Name);
      CdsW007.Open;

      CdsW008.Filtered := False;
      CdsW008.Close;
      CdsW008.Data  := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(138, lDsFiltro, self.Name);
      CdsW008.Open;

      CdsW008H.Close;
      CdsW008H.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(139, lDsFiltro, self.Name);
      CdsW008H.Open;

      CdsW007.Filtered := False;
      CdsW007.Filter   := 'NrNfOrig <> ''''';
      CdsW007.Filtered := True;

      EdtNrNfOrig.Text := CdsW007.FieldByName('NrNfOrig').AsString;
      EdtNrSENFOR.Text := CdsW007.FieldByName('NrSENFOR').AsString;
      EdtNrFornec.Text := CdsW007.FieldByName('NrFornec').AsString;

      CdsW007.Filtered := False;
      CdsW007.Filter   := 'NrSeqOpe = ' + CdsW006.FieldByName('NrSeqOpe').AsString;
      CdsW007.Filtered := True;

      CdsW008.Filtered := False;
      CdsW008.Filter   := 'NrSeqOpe = ' + CdsW006.FieldByName('NrSeqOpe').AsString;
      CdsW008.Filtered := True;
end;

procedure TMov0013.BtnRefazerClick(Sender: TObject);
var lConexao  : TSDmWmsClient;
    lDsResult : AnsiString;
begin
      inherited;
      if (MessageDlg('Tem certeza que esta atividade deverá ser refeita pelo operador?',
                     mtConfirmation, [mbYes, mbNo], 0) = mrYes) then
      begin
            try
                lConexao := TSDmWmsClient.Create(DmSrvApl.SrvAplica.DBXConnection);
                lDsResult := lConexao.RefazerAtividade(gCdArmaze, EdtNrOpeLog.Text);
                DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
                ModalResult := mrOk;
            finally
                FreeAndNil(lConexao);
            end;
      end;
end;

procedure TMov0013.BtnSalvarClick(Sender: TObject);
var lConexao  : TSDmWmsClient;
    lDsResult : AnsiString;
begin
      inherited;
      if (MessageDlg('Tem certeza que deseja aprovar esta atividade?', mtConfirmation,
          [mbYes, mbNo], 0) = MrYes) then
      begin

            try
                lConexao := TSDmWmsClient.Create(DmSrvApl.SrvAplica.DBXConnection);
                lDsResult := lConexao.GravaPrioridadeSituacaoOpeLog(gCdArmaze,
                                                         Trim(EdtNrOpeLog.Text) + ',9,F' + Chr(13));
                DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);

                DmSrvApl.GravaLogScreen(DmSrvApl.BrvDicionario.UserCode,
                                        BrvLogScreen.Execute(panel3, Self),
                                        Self.Name,
                                        DmSrvApl.BrvDicionario.Credencial,
                                        ExtractFilePath(Application.ExeName),
                                        DmSrvApl.SrvAplica);

                BtnSalvar.Visible := False;
            finally
                FreeAndNil(lConexao);
                ModalResult := mrOk;
            end;
      end;
end;

procedure TMov0013.BtnCancelarClick(Sender: TObject);
var lConexao  : TSDmWmsClient;
    lDsResult : AnsiString;
begin
      inherited;
      if (MessageDlg('Tem certeza que deseja cancelar esta atividade?', mtConfirmation,
          [mbYes, mbNo], 0) = MrYes) then
      begin

            try
                lConexao := TSDmWmsClient.Create(DmSrvApl.SrvAplica.DBXConnection);
                lDsResult := lConexao.CancelarOpeLog(Trim(EdtNrOpeLog.Text),
                                                 Formatfloat('0', DmSrvApl.BrvDicionario.UserCode));
                DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
                BtnSalvar.Visible := False;
            finally
                FreeAndNil(lConexao);
                ModalResult := mrOk;
            end;
      end;
end;

procedure TMov0013.BtnCancelClick(Sender: TObject);
begin
      inherited;
      ModalResult := mrCancel;
end;

procedure TMov0013.CdsW006AfterScroll(DataSet: TDataSet);
begin
      inherited;
      CdsW007.Filtered := False;
      CdsW007.Filter   := 'NrSeqOpe = ' + CdsW006.FieldByName('NrSeqOpe').AsString;
      CdsW007.Filtered := True;
end;

procedure TMov0013.CdsW007AfterScroll(DataSet: TDataSet);
var lqtsoma : Integer;
begin
      inherited;
      CdsW008.Filtered := False;
      CdsW008.Filter   := 'NrSeqOpe = ' + CdsW006.FieldByName('NrSeqOpe').AsString;
      CdsW008.Filtered := True;

      if CdsW008.Active then
      begin
            try
                CdsW008.First;
                CdsW008.DisableControls;

                lqtsoma := 0;

                while not CdsW008.Eof do
                begin
                      lqtsoma := lqtsoma + CdsW008.FieldByName('Qtprodut').AsInteger;
                      CdsW008.Next;
                end;

                EdtQtProduto.Text := FormatFloat('0', lqtsoma);
            finally
                CdsW008.EnableControls;
            end;
      end;

end;

procedure TMov0013.DbgW006DrawColumnCell(Sender: TObject; const Rect: TRect; DataCol: Integer;
  Column: TColumn; State: TGridDrawState);
begin
      inherited;
      if (CdsW006.FieldByName('StIteOpe').AsString = 'D') then
      begin
            DbgW006.Canvas.FillRect(Rect);
            DbgW006.Canvas.Brush.Color := clSkyBlue;
            DbgW006.Canvas.Font.Color  := clNavy;
            DbgW006.DefaultDrawDataCell(Rect, Column.Field, State);
      end else
      begin
            if (CdsW006.FieldByName('StIteOpe').AsString = 'C') then
            begin
                  DbgW006.Canvas.FillRect(Rect);
                  DbgW006.Canvas.Brush.Color := clSilver;
                  DbgW006.Canvas.Font.Color  := clNavy;
                  DbgW006.DefaultDrawDataCell(Rect, Column.Field, State);
            end;
      end;
end;

end.

unit UMov0035;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, StdCtrls, Buttons, BrvBitBtn, BrvRetCon, BrvCustomEdit, BrvEdit, BrvListParam,
  ImgList, Menus, ExtCtrls, BrvSpeedButton, BrvDbNavCop, Grids, BrvDbGrids, BrvDbGrid, DB, DBClient,
  BrvServerProgress;

type
  TMOV0035 = class(TMov0000)
    PnlArquiv: TPanel;
    Label1: TLabel;
    Label2: TLabel;
    EdtNmArquiv: TBrvEdit;
    EdtCdArmaze: TBrvEdit;
    EdtNmArmaze: TBrvRetCon;
    BtnImport: TBrvBitBtn;
    CcW005: TClientDataSet;
    DtsW005: TDataSource;
    PnlDados: TPanel;
    BrvDbGrid1: TBrvDbGrid;
    Panel1: TPanel;
    BtnRetornar: TBrvBitBtn;
    BtnGravar: TBrvBitBtn;
    BrvServerProgress: TBrvServerProgress;
    Panel2: TPanel;
    Panel3: TPanel;
    Panel4: TPanel;
    Panel5: TPanel;
    Panel6: TPanel;
    Panel8: TPanel;
    Panel9: TPanel;
    Panel11: TPanel;
    Panel12: TPanel;
    Panel13: TPanel;
    Panel14: TPanel;
    Panel15: TPanel;
    Panel16: TPanel;
    Panel10: TPanel;
    Panel17: TPanel;
    Panel18: TPanel;
    Panel19: TPanel;
    Panel20: TPanel;
    Panel21: TPanel;
    Panel22: TPanel;
    Panel23: TPanel;
    procedure BtnImportClick(Sender: TObject);
    procedure BtnRetornarClick(Sender: TObject);
    procedure BtnGravarClick(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  MOV0035: TMOV0035;

implementation

uses UDmSrvApl, BrvFuncoesXE, UClaSrv;

{$R *.dfm}

procedure TMOV0035.BtnGravarClick(Sender: TObject);
var lCnxServer  : TSDmWmsClient;
    lDsResult : AnsiString;
    lCcParam  : TClientDataSet;
begin
      inherited;

      try
            lCcParam  := TClientDataSet.Create(Self);
            lCcParam.FieldDefs.Clear;
            lCcParam.FieldDefs.Add('pNmFormul', ftString , 50);
            lCcParam.FieldDefs.Add('pCdArmaze', ftInteger,  0);
            lCcParam.FieldDefs.Add('pXmlW005' ,    ftMemo,  0);
            lCcParam.CreateDataSet;

            lCcParam.Append;
            lCcParam.FieldByName('pNmFormul').AsString  := Self.Name;
            lCcParam.FieldByName('pCdArmaze').AsInteger := StrToInt(EdtCdArmaze.Text);
            lCcParam.FieldByName('pXmlW005' ).AsString  := CcW005.XMLData;
            lCcParam.Post;

            try
                BrvServerProgress.Start(Self.Caption, 'Importando...', 100, 10);
                lCnxServer := TSDmWmsClient.Create(DmSrvApl.SrvAplica.DBXConnection);
                lDsResult  := lCnxServer.GravarOpeLogEndere(DmSrvApl.BrvDicionario.Credencial,
                                                                                  lCcParam.XMLData);
            finally
                BrvServerProgress.Stop;
            end;

            DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
            MessageDlg('Arquivo processado com sucesso!!!', mtInformation, [mbok], 0);
            BtnRetornarClick(BtnRetornar);
      finally
            FreeAndNil(lCnxServer);
            FreeAndNil(lCcParam);
      end;
end;

procedure TMOV0035.BtnImportClick(Sender: TObject);
var lTxArquiv : TStrings;
begin
      inherited;

      if (StrToIntDef(EdtCdArmaze.Text, 0) = 0) then
      begin
            MessageDlg('Armazém não informado!', mtWarning, [mbOk], 0);
      end else
      begin
            Try
                lTxArquiv := TStringList.Create();

                if (FileExists(EdtNmArquiv.Text)) then
                begin
                      lTxArquiv.LoadFromFile(EdtNmArquiv.Text);
                      lTxArquiv.Insert(0, 'SQENDDES;NRRUADES;NRPREDES;NRNIVDES;DSBLODES;' +
                                          'SQENDMAS;NRRUAMAS;NRPREMAS;NRNIVMAS;DSBLOMAS;' +
                                          'NRDOCTO;NRORDEM');

                      BrvFuncoesXE.StrToClientDataSet(lTxArquiv.Text, CcW005, []);

                      if (CcW005.RecordCount = 0) then
                      begin
                            MessageDlg('Não foram localizados registros no arquivo!', mtWarning,
                                                                                               [mbok], 0);
                            BtnRetornarClick(BtnRetornar);
                      end else
                      begin
                            PnlArquiv.Enabled := False;
                            BtnImport.Enabled := False;
                            PnlDados.Visible  := True;
                      end;
                end else
                begin
                      MessageDlg('Arquivo não localizado!', mtWarning, [mbOk], 0);
                end;

            Finally
                FreeAndNil(lTxArquiv);
            End;
      end;
end;

procedure TMOV0035.BtnRetornarClick(Sender: TObject);
begin
      inherited;
      CcW005.EmptyDataSet;
      CcW005.Close;
      CcW005.FieldDefs.Clear;
      PnlDados.Visible  := False;
      PnlArquiv.Enabled := True;
      BtnImport.Enabled := True;
      EdtCdArmaze.SetFocus;
end;

initialization
      RegisterClass(TMov0035);

finalization
      UnRegisterClass(TMov0035);

end.

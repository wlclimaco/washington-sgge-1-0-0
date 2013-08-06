unit UMov0028;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, BrvListParam, ImgList, Menus, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop,
  BrvRetCon, Mask, BrvMesAno, StdCtrls, BrvBitBtn, BrvLabel, BrvEditNum, ComCtrls, BrvExport, DB,
  DBClient, BrvClientDataSet, BrvEdit, DBCtrls, BrvDbEdit, BrvServerProgress, BrvCustomMaskEdit,
  BrvCustomEdit;

type
  TMov0028 = class(TMov0000)
    PnlFilter: TPanel;
    EdtCdEmpres: TBrvEditNum;
    BrvLabel1: TBrvLabel;
    BtnAbrir: TBrvBitBtn;
    EdtDtMesAno: TBrvMesAno;
    BrvLabel2: TBrvLabel;
    LblDsEmpres: TBrvRetCon;
    BtnFechar: TBrvBitBtn;
    BrvExport: TBrvExport;
    PnlWork: TPanel;
    PnlData: TPanel;
    Splitter1: TSplitter;
    TrvPlano: TTreeView;
    CpB002: TBrvClientDataSet;
    BrvLabel3: TBrvLabel;
    BrvLabel4: TBrvLabel;
    BrvLabel5: TBrvLabel;
    BrvLabel6: TBrvLabel;
    LblAuxDebCre: TBrvLabel;
    BrvLabel8: TBrvLabel;
    BrvLabel9: TBrvLabel;
    LblDebCre: TBrvLabel;
    CpB003: TBrvClientDataSet;
    BtnGravar: TBrvBitBtn;
    BtnCancelar: TBrvBitBtn;
    BrvDbEdit1: TBrvDbEdit;
    DtsB002: TDataSource;
    BrvDbEdit2: TBrvDbEdit;
    BrvDbEdit3: TBrvDbEdit;
    DbtSdAbertu: TBrvDbEdit;
    DtsB003: TDataSource;
    DbtSdDebito: TBrvDbEdit;
    DbtSdCredito: TBrvDbEdit;
    BrvServerProgress: TBrvServerProgress;
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure BtnAbrirClick(Sender: TObject);
    procedure BtnFecharClick(Sender: TObject);
    procedure TrvPlanoChange(Sender: TObject; Node: TTreeNode);
    procedure FormCreate(Sender: TObject);
    procedure DbtSdAbertuKeyUp(Sender: TObject; var Key: Word; Shift: TShiftState);
    procedure BtnCancelarClick(Sender: TObject);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure BtnGravarClick(Sender: TObject);
  private
    procedure CarregarPlanoDeContas;
    procedure AtivarCtrlSaldo(pSnAtiva: Boolean);
    { Private declarations }
  public
    { Public declarations }
    gNrPlano  : Integer;
    gNrConAnt : String;
    gSnAltera : Boolean;
  end;

var
  Mov0028: TMov0028;

implementation

uses UDmSrvApl, UClaSrv, UDmCtb;

{$R *.dfm}

procedure TMov0028.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      EdtCdEmpres.BrParams.Clear;
      EdtCdEmpres.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

procedure TMov0028.FormClose(Sender: TObject; var Action: TCloseAction);
begin
      inherited;
      if (BtnFechar.Enabled) or (BtnAbrir.Enabled) then
      begin
            Action := caFree;
      end else
      begin
            Action := caNone;
            Raise Exception.Create('Salve suas alterações antes de sair');
      end;
end;

procedure TMov0028.FormCreate(Sender: TObject);
var DtDia : Word;
    DtMes : Word;
    DtAno : Word;
begin
      inherited;
      DecodeDate(DmSrvApl.BrvDicionario.DataHoraServer, DtAno, DtMes, DtDia);
      EdtDtMesAno.AsMes :=  DtMes;
      EdtDtMesAno.AsAno :=  DtAno;
end;

procedure TMov0028.AtivarCtrlSaldo(pSnAtiva : Boolean);
begin
      DBtSdAbertu.Enabled  := pSnAtiva;
      DBtSdDebito.Enabled  := pSnAtiva;
      DBtSdCredito.Enabled := pSnAtiva;

      if (pSnAtiva) then
      begin
            DBtSdAbertu.Color  := clWindow;
            DBtSdDebito.Color  := clWindow;
            DBtSdCredito.Color := clWindow;
      end else
      begin
            DBtSdAbertu.Color  := clSilver;
            DBtSdDebito.Color  := clSilver;
            DBtSdCredito.Color := clSilver;
      end;
end;

procedure TMov0028.TrvPlanoChange(Sender: TObject; Node: TTreeNode);
begin
      inherited;

      if gSnAltera then
      begin
            BtnCancelarClick(nil);
      end;

      if TrvPlano.Items.Count > 0 then
      begin
            if  (PString(TrvPlano.Selected.Data)^ <> gNrConAnt) and
                (PString(TrvPlano.Selected.Data)^ <> '')
               { and (Not SnAbrir)} then
            begin
                  gNrConAnt    := PString(TrvPlano.Selected.Data)^;
                  CpB002.Data  := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(214,
                                          '<%NrPlano%>;' + FormatFloat('0', gNrPlano) + Chr(13) +
                                          '<%NrConta%>;' + PString(TrvPlano.Selected.Data)^,
                                          Self.Name);

                  if (CpB002.FieldByName('SNRECLAN').AsString = 'N') then
                  begin
                        CpB003.Close;
                        AtivarCtrlSaldo(False);
                        LblDebCre.Caption    := '';
                  end else
                  begin
                        AtivarCtrlSaldo(True);

                        CpB003.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(215,
                              '<%CdEmpres%>;' + EdtCdEmpres.Text  + Chr(13) +
                              '<%DtMes%>;'    + FormatFloat('0', EdtDtMesAno.AsMes) + Chr(13) +
                              '<%DtAno%>;'    + FormatFloat('0', EdtDtMesAno.AsAno) + Chr(13) +
                              '<%NrConta%>;'  + gNrConAnt         + Chr(13) +
                              '<%NrPlano%>;'  + FormatFloat('0', gNrPlano), Self.Name);
                  end;
            end else
            begin
                  CpB002.Close;
                  CpB003.Close;
                  AtivarCtrlSaldo(False);
            end;
      end;
end;

procedure TMov0028.BtnAbrirClick(Sender: TObject);
begin
      inherited;
      gSnAltera := False;

      if (EdtCdEmpres.BrAsInteger = 0) then
      begin
            raise Exception.Create('Informe uma empresa válida!');
      end;

      if not (EdtDtMesAno.DataValida) then
      begin
            raise Exception.Create('Informe um período válido!');
      end;

      gNrPlano := DmCtb.PlanoContasEmpresa(EdtCdEmpres.BrAsInteger,
                                                               StrToDate('01/' + EdtDtMesAno.text));

      if (gNrPlano = 0) then
      begin
            raise Exception.Create('Plano não encontrado!' + Chr(13) +
                                   'Verifique a empresa e o período informados...');
      end;

      CarregarPlanoDeContas;

      EdtCdEmpres.Enabled := False;
      EdtDtMesAno.Enabled := False;
      BtnAbrir.Enabled    := False;
      BtnFechar.Enabled   := True;
      PnlWork.Visible     := True;
end;

procedure TMov0028.BtnCancelarClick(Sender: TObject);
begin
      inherited;
      CpB003.Cancel;

      BtnCancelar.Enabled :=  False;
      BtnGravar.Enabled   :=  False;
      BtnFechar.Enabled   :=  True;

      if gSnAltera then
      begin
            gSnAltera := False;
            gNrConAnt := '';
      end;
end;

procedure TMov0028.BtnFecharClick(Sender: TObject);
begin
      inherited;
      CpB002.Close;
      CpB003.Close;
      AtivarCtrlSaldo(False);
      EdtCdEmpres.Enabled := True;
      EdtDtMesAno.Enabled := True;
      BtnAbrir.Enabled    := True;
      BtnFechar.Enabled   := False;
      PnlWork.Visible := False;
      EdtCdEmpres.SetFocus;
end;

procedure TMov0028.BtnGravarClick(Sender: TObject);
var lConexao  : TSDmAdmClient;
    lDsResult : AnsiString;
begin
      inherited;
      CpB003.FieldByName('CDEMPRES').AsString  := EdtCdEmpres.Text;
      CpB003.FieldByName('DTMES'   ).AsInteger := EdtDtMesAno.AsMes;
      CpB003.FieldByName('DTANO'   ).AsInteger := EdtDtMesAno.AsAno;
      CpB003.FieldByName('NRCONTA' ).AsString  := PString(TrvPlano.Selected.Data)^;
      CpB003.FieldByName('NRPLANO' ).AsInteger := gNrPlano;
      CpB003.Post;
      gSnAltera := False;

      try
          try
              BrvServerProgress.Start(Self.Caption, 'Gravando Saldo', 100, 10);
              lConexao  := TSDmAdmClient.Create(DmSrvApl.SrvAplica.DBXConnection);
              lDsResult := lConexao.GravarSaldoPlano(DmSrvApl.BrvDicionario.Credencial, CpB003.Data,
                                                                                         Self.Name);
          finally
              BrvServerProgress.Stop;
          end;

          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
      finally
          FreeAndNil(lConexao);
          BtnGravar.Enabled   := False;
          BtnCancelar.Enabled := False;
          BtnFechar.Enabled   := True;
      end;
end;

procedure TMov0028.CarregarPlanoDeContas;
var lConexao     : TSDmConClient;
    lDsResult    : String;
    lDsPlano     : String;
begin
      lConexao := TSDmConClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          lDsPlano := lConexao.AbrirPlanoContas(DmSrvApl.BrvDicionario.Credencial,
                                                lDsResult, gNrPlano, 2, 0,
                                                EdtCdEmpres.BrAsInteger);

          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
          BrvExport.XMLToTreeView(lDsPlano, TrvPlano);
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TMov0028.DbtSdAbertuKeyUp(Sender: TObject; var Key: Word; Shift: TShiftState);
begin
      inherited;
      if not gSnAltera then
      begin
            if (CpB003.IsEmpty) then
            begin
                  CpB003.Append;
            end;

            BtnGravar.Enabled   := True;
            BtnCancelar.Enabled := True;
            gSnAltera           := True;
            BtnFechar.Enabled   := False;
      end
end;

initialization
      RegisterClass(TMov0028);

finalization
      UnRegisterClass(TMov0028);

end.

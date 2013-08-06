unit UMov0008;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, StdCtrls, BrvEditNum,
  Mask, DBCtrls, BrvDbRetCon, BrvEditDate, BrvRetCon, DB, DBClient, BrvClientDataSet,
  BrvBitBtn, ComCtrls, Grids, BrvDbGrids, BrvDbGrid, DBGrids, UClaSrv, BrvAlertProgress,
  BrvServerProgress, BrvListParam, ImgList, Menus, BrvCustomEdit;

type
  TMov0008 = class(TMov0000)
    BtnAbrCon: TBrvSpeedButton;
    BtnFecCon: TBrvSpeedButton;
    PnlCabeca: TPanel;
    Label1: TLabel;
    LblNrPlano: TLabel;
    EdtCdEmpres: TBrvEditNum;
    EdtDsEmpres: TBrvRetCon;
    CdsPeriod: TBrvClientDataSet;
    BtnAnteri: TBrvBitBtn;
    BtnEncerr: TBrvBitBtn;
    BtnProxim: TBrvBitBtn;
    BrvServerProgress: TBrvServerProgress;
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure BtnAbrConClick(Sender: TObject);
    procedure BtnFecConClick(Sender: TObject);
    procedure BtnAnteriClick(Sender: TObject);
    procedure BtnEncerrClick(Sender: TObject);
  private
    procedure VerificarPeriodo;
    function PeriodoAnterior(pDtPeriod: String) : String;
    function ProximoPeriodo(pDtPeriod: String): String;
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Mov0008: TMov0008;

implementation

uses UDmSrvApl;

{$R *.dfm}

procedure TMov0008.BtnAbrConClick(Sender: TObject);
begin
      NavBarra.SetFocus;

      if EdtDsEmpres.Text = '' then
      begin
           raise Exception.Create('Informe a empresa');
      end;

      if (LblNrPlano.Caption = '') or (LblNrPlano.Caption = '0') then
      begin
            raise Exception.Create('Não há plano de contas definido para essa empresa');
      end;

      EdtCdEmpres.ReadOnly := True;
      BtnAbrCon.Enabled    := False;
      BtnFecCon.Enabled    := True;
      PnlFundo.Visible     := True;

      VerificarPeriodo;
end;

procedure TMov0008.VerificarPeriodo;
var lDtDia    : Word;
    lDtMes    : Word;
    lDtAno    : Word;
    lDsPeriod : String;
begin
      CdsPeriod.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(99, '', Name);

      if CdsPeriod.Eof then
      begin
            DecodeDate(DmSrvApl.BrvDicionario.DataServer, lDtAno, lDtMes, lDtDia);
            lDsPeriod := IntToStr(lDtMes) + '/' + IntToStr(lDtAno);

            BtnAnteri.Caption := 'Abrir ' + PeriodoAnterior(lDsPeriod);

            BtnEncerr.Caption := 'Encerrar ';
            BtnEncerr.Enabled := False;

            BtnProxim.Caption :=  'Abrir ' + ProximoPeriodo(lDsPeriod);
      end else
      begin
            BtnAnteri.Caption := 'Abrir ' + PeriodoAnterior(
                                              CdsPeriod.FieldByName('DtPeriod').AsString);
            BtnEncerr.Caption := 'Encerrar ' + CdsPeriod.FieldByName('DtPeriod').AsString;
            CdsPeriod.Last;

            BtnProxim.Caption :=  'Abrir ' + ProximoPeriodo(
                                              CdsPeriod.FieldByName('DtPeriod').AsString);
      end;
end;

function TMov0008.PeriodoAnterior(pDtPeriod : String) : String;
var lDtMes : Integer;
    lDtAno : Integer;
begin
      try
          lDtMes := StrToInt(Copy(pDtPeriod, 1, Pos('/', pDtPeriod) - 1));
          lDtAno := StrToInt(Copy(pDtPeriod, Pos('/', pDtPeriod) + 1, 10));

          if lDtMes = 1 then
          begin
                lDtMes := 12;
                Dec(lDtAno);
          end else
          begin
                Dec(lDtMes);
          end;
      except
          MessageDlg('Não foi possível encontrar o período anterior', mtError, [mbOk], 0);
          raise;
      end;

      Result := IntToStr(lDtMes) + '/' + IntToStr(lDtAno);
end;

function TMov0008.ProximoPeriodo(pDtPeriod : String) : String;
var lDtMes : Integer;
    lDtAno : Integer;
begin
      try
          lDtMes := StrToInt(Copy(pDtPeriod, 1, Pos('/', pDtPeriod) - 1));
          lDtAno := StrToInt(Copy(pDtPeriod, Pos('/', pDtPeriod) + 1, 10));

          if lDtMes = 12 then
          begin
                lDtMes := 1;
                inc(lDtAno);
          end else
          begin
                inc(lDtMes);
          end;
      except
          MessageDlg('Não foi possível encontrar o período anterior', mtError, [mbOk], 0);
          raise;
      end;

      Result := IntToStr(lDtMes) + '/' + IntToStr(lDtAno);
end;

procedure TMov0008.BtnAnteriClick(Sender: TObject);
var lConexao     : TSDmConClient;
    lDsResult    : String;
    lDtAno       : String;
    lDtMes       : String;
begin
      lDtAno := (Sender as TBitBtn).Caption;

      Delete(lDtAno, 1, Pos(' ', lDtAno));

      lDtMes := Copy(lDtAno, 1, Pos('/', lDtAno) -1);
      Delete(lDtAno, 1, Pos('/', lDtAno));

      lConexao := TSDmConClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          try
              BrvServerProgress.Start(Self.Caption, 'Abrindo periododo contabil...', 10, 100);
              lConexao.AbreFechaPeriodoContabil(DmSrvApl.BrvDicionario.Credencial,
                                                lDsResult,
                                                StrToInt(lDtAno),
                                                StrToInt(lDtMes),
                                                EdtCdEmpres.BrAsInteger, 'A');
          finally
              BrvServerProgress.Stop;
          end;

          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);

          MessageDlg('Processo finalizado com sucesso!', mtInformation, [mbok], 0);
          BtnFecConClick(nil);
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TMov0008.BtnEncerrClick(Sender: TObject);
var lConexao     : TSDmConClient;
    lDsResult    : String;
    lDtAno       : String;
    lDtMes       : String;
begin
      lDtAno := (Sender as TBitBtn).Caption;

      Delete(lDtAno, 1, Pos(' ', lDtAno));

      lDtMes := Copy(lDtAno, 1, Pos('/', lDtAno) -1);
      Delete(lDtAno, 1, Pos('/', lDtAno));

      lConexao := TSDmConClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          try
              BrvServerProgress.Start(Self.Caption, 'Encerrando periododo contabil...', 10, 100);
              lConexao.EncerrarPeriodoContabil(DmSrvApl.BrvDicionario.Credencial,
                                               lDsResult,
                                               StrToInt(lDtAno),
                                               StrToInt(lDtMes),
                                               EdtCdEmpres.BrAsInteger,
                                               StrToInt(LblNrPlano.Caption));

          finally
              BrvServerProgress.Stop;
          end;

          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);

          MessageDlg('Processo finalizado com sucesso!', mtInformation, [mbok], 0);
          BtnFecConClick(nil);
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TMov0008.BtnFecConClick(Sender: TObject);
begin
      PnlFundo.Visible     := False;
      EdtCdEmpres.ReadOnly := False;
      BtnAbrCon.Enabled    := True;
      BtnFecCon.Enabled    := False;
end;

procedure TMov0008.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      EdtCdEmpres.BrParams.Clear;
      EdtCdEmpres.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

initialization
      RegisterClass(TMov0008);

finalization
      UnRegisterClass(TMov0008);

end.



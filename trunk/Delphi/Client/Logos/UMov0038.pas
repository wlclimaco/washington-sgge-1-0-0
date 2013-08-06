unit UMov0038;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, BrvListParam, ImgList, Menus, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop,
  StdCtrls, Spin, BrvBitBtn, DB, DBClient, BrvClientDataSet, BrvString, BrvRelAsc, BrvServerProgress,
  BrvDigito;

type
  TMov0038 = class(TMov0000)
    Panel6: TPanel;
    Panel1: TPanel;
    BtnGerar: TBrvBitBtn;
    LblChaves: TLabel;
    SedQtChaves: TSpinEdit;
    RelAsc: TBrvRelAsc;
    CpTabLib: TBrvClientDataSet;
    BrvListParam: TBrvListParam;
    BrvServerProgress: TBrvServerProgress;
    BrvDigito: TBrvDigito;
    CcParams: TBrvClientDataSet;
    CcParamsNrLibera: TStringField;
    CcParamsNmForm: TStringField;
    procedure BtnGerarClick(Sender: TObject);
  private
    procedure GerarListagem(pDsNumero:AnsiString; pCdUsuari:Integer);
    procedure AtualizarChaves(pDsNumero:AnsiString; pCdUsuari:Integer);
  public
    { Public declarations }
  end;

var
  Mov0038: TMov0038;

implementation

uses UClaSrv, UDmSrvApl;

{$R *.dfm}

procedure TMov0038.BtnGerarClick(Sender: TObject);
var lQtChaves : Integer;
    lQtCount  : Integer;
    lNrSortea : Integer;
    lCdUsuari : Integer;
    lDsNumero : AnsiString;
    lDsNumAtu : AnsiString;
begin
      inherited;
      try
          BrvServerProgress.Start(Self.Caption, 'Reunindo informações', 100, 10);
          BrvListParam.Clear;
          lQtChaves := StrToInt(SedQtChaves.Text);
          lCdUsuari := DmSrvApl.BrvDicionario.UserCode;

          if ((lQtChaves > 0) and (lQtChaves <= 30)) then
          begin
                BrvListParam.Add('CdUsuari', '', '', IntToStr(lCdUsuari));
                CpTabLib.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(273,
                                                                 BrvListParam.AsBrParam, Self.Name);

                if (CpTabLib.RecordCount > 0) then
                begin
                      CpTabLib.Filtered := False;
                      CpTabLib.Filter   := 'CdUsuari = ' + IntToStr(lCdUsuari);
                      CpTabLib.Filtered := True;
                      if (CpTabLib.RecordCount > lQtChaves) then
                      begin
                            lQtChaves := CpTabLib.RecordCount;
                      end;
                      CpTabLib.Filtered := False;
                      lQtCount := 1;
                      while (not CpTabLib.Eof) and (lQtCount <= lQtChaves) do
                      begin
                            if (CpTabLib.FieldByName('CdUsuari').AsInteger <> lCdUsuari) then
                            begin
                                  lNrSortea := (lQtCount * lQtCount) + (lQtCount * 2);
                                  CpTabLib.RecNo := lNrSortea;
                                  CpTabLib.Edit;
                                  CpTabLib.FieldByName('CdUsuari').AsInteger := lCdUsuari;
                                  CpTabLib.Post;
                            end;

                            BrvDigito.Codigo := CpTabLib.FieldByName('NrLibera').AsString;
                            lDsNumero        := lDsNumero + ',' +
                                                          BrvDigito.Codigo + BrvDigito.Digito;
                            lDsNumAtu        := lDsNumAtu + ',' +
                                                          CpTabLib.FieldByName('NrLibera').AsString;

                            inc(lQtCount);
                            CpTabLib.Next;
                      end;

                      Delete(lDsNumAtu, 1, 1);
                      Delete(lDsNumero, 1, 1);

                      GerarListagem(lDsNumero, lCdUsuari);
                      AtualizarChaves(lDsNumAtu, lCdUsuari);
                end else
                begin
                      MessageDlg('Nenhum registro encontrado!!!', mtInformation, [mbok], 0);
                end;
          end;
      finally
          CpTabLib.Close;
          BrvServerProgress.Stop;
      end;
end;

procedure TMov0038.GerarListagem(pDsNumero:AnsiString; pCdUsuari:Integer);
var lDsLinha : AnsiString;
begin
      RelAsc.NovoRelatorio(pCdUsuari, '', Self.Name , Self.Caption);
      RelAsc.NovaLinha('        CHAVES PARA LIBERAÇÃO');
      RelAsc.NovaLinha(StringOfChar('-', 40));

      while Length(pDsNumero) > 0 do
      begin
            lDsLinha  := '[ ]' + Trim(Copy(pDsNumero, 1, 29));
            lDsLinha  := StringReplace(lDsLinha, ',', '  [ ]', [rfReplaceAll]);

            RelAsc.NovaLinha(lDsLinha);
            Delete(pDsNumero, 1, 30);
      end;
end;

procedure TMov0038.AtualizarChaves(pDsNumero:AnsiString; pCdUsuari:Integer);
var lSrvAdm   : TSDmAdmClient;
    lDsResult : String;
begin
      try
          if (CcParams.Active) then
          begin
                CcParams.EmptyDataSet;
                CcParams.Close;
          end;

          CcParams.CreateDataSet;
          CcParams.Append;
          CcParams.FieldByName('NrLibera').AsString := pDsNumero;
          CcParams.FieldByName('NmForm'  ).AsString := Self.Name;
          CcParams.Post;

          lSrvAdm   := TSDmAdmClient.Create(DmSrvApl.SrvAplica.DBXConnection);
          lDsResult := lSrvAdm.AtualizarChaves(DmSrvApl.BrvDicionario.Credencial, CcParams.Data);
          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);

          //MessageDlg('Gravado com sucesso!!!', mtInformation, [mbok], 0);
      finally
          FreeAndNil(lSrvAdm);
      end;

end;

initialization
      RegisterClass(TMov0038);

finalization
      UnRegisterClass(TMov0038);

end.

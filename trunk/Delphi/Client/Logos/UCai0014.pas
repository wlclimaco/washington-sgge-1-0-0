unit UCai0014;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, StdCtrls, Buttons, BrvBitBtn, BrvListParam, ImgList, Menus, ExtCtrls,
  BrvSpeedButton, BrvDbNavCop, BrvCustomEdit, BrvEditNum, BrvDigito, DB, BrvClientDataSet,
  BrvServerProgress, BrvEdit;

type
  TCai0014 = class(TMov0000)
    Panel1: TPanel;
    LblChaves: TLabel;
    Panel6: TPanel;
    BtnConfirmar: TBrvBitBtn;
    BtnCancelar: TBrvBitBtn;
    BrvDigito: TBrvDigito;
    BrvServerProgress: TBrvServerProgress;
    EdtNrChaAut: TBrvEdit;
    MemTxDadMov: TMemo;
    Label1: TLabel;
    procedure BtnConfirmarClick(Sender: TObject);
    procedure BtnCancelarClick(Sender: TObject);
  private
    { Private declarations }
  public
    var gNmForm : String;
  end;

var
  Cai0014 : TCai0014;

implementation

uses UClaSrv, UDmSrvApl;

{$R *.dfm}

procedure TCai0014.BtnCancelarClick(Sender: TObject);
begin
      inherited;
      ModalResult := mrCancel;
end;

procedure TCai0014.BtnConfirmarClick(Sender: TObject);
var lDsAutAux : String;
    lDsDigAux : String;
    lCcParam  : TBrvClientDataSet;
    lSrvAdm   : TSDmAdmClient;
    lDsResult : String;
begin
      inherited;
      EdtNrChaAut.Text := Trim(EdtNrChaAut.Text);

      if (Trim(EdtNrChaAut.Text) <> '') then
      begin
            lDsAutAux := Copy(EdtNrChaAut.Text, 1, Length(EdtNrChaAut.Text)-2);
            lDsDigAux := Copy(EdtNrChaAut.Text, Length(EdtNrChaAut.Text)-1, 2);

            BrvDigito.Codigo := lDsAutAux;

            if (lDsDigAux = BrvDigito.Digito) then
            begin
                  try
                      lCcParam := TBrvClientDataSet.Create(Self);
                      lCcParam.BrDicionario := DmSrvApl.BrvDicionario;
                      lCcParam.FieldDefs.Clear;
                      lCcParam.FieldDefs.Add('NrLibera', ftString, 20);
                      lCcParam.FieldDefs.Add('NmForm'  , ftString, 20);
                      lCcParam.CreateDataSet;

                      lCcParam.Append;
                      lCcParam.FieldByName('NrLibera').AsString := lDsAutAux;
                      lCcParam.FieldByName('NmForm'  ).AsString := gNmForm;
                      lCcParam.Post;

                      try
                          BrvServerProgress.Start(Self.Caption, 'Reunindo informações', 100, 10);
                          lSrvAdm   := TSDmAdmClient.Create(DmSrvApl.SrvAplica.DBXConnection);
                          lDsResult := lSrvAdm.ValidarChaveLiberacao(
                                                  DmSrvApl.BrvDicionario.Credencial, lCcParam.Data);
                      finally
                          BrvServerProgress.Stop;
                      end;

                      DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);

                      if (Copy(lDsResult, 1,1) = '1') then
                      begin
                            raise Exception.Create(Copy(lDsResult, 2, Length(lDsResult)));
                      end;

                  finally
                      FreeAndNil(lCcParam);
                  end;

                  ModalResult := mrOk;
            end else
            begin
                  EdtNrChaAut.SelectAll;
                  EdtNrChaAut.SetFocus;
                  MessageDlg('Chave de Autorização incorreta!' + Chr(13) + 'Tente novamente...',
                                                                                mtError, [mbok], 0);
            end;

      end else
      begin
            EdtNrChaAut.SetFocus;
            MessageDlg('Informe uma Chave de Autorização!!!', mtWarning, [mbok], 0);
      end;
end;

initialization
      RegisterClass(TCai0014);

finalization
      UnRegisterClass(TCai0014);

end.

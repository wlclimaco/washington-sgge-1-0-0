unit UCai0001;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms, IniFiles,
  Dialogs, UCai0000, StdCtrls, Buttons, BrvBitBtn, ExtCtrls, BrvSenha, UClaSrv,
  UDmSrvApl, BrvEdit, BrvCustomEdit;

type
  TCai0001 = class(TCai0000)
    Label1: TLabel;
    Label2: TLabel;
    EdtDsLogin: TBrvEdit;
    EdtDsSenha: TEdit;
    BrvSenha: TBrvSenha;
    procedure BbtOkClick(Sender: TObject);
    procedure FormShow(Sender: TObject);
  private
    function AlterarSenha(pDsLogin : String) : Boolean;
    procedure ValidarLogin(pDsLogin : String; var pNrSenha : Integer;
                                              var pCdUsuari : Integer;
                                              var pNmUsuari : String);
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Cai0001: TCai0001;

implementation

uses UCai0002;

{$R *.dfm}

procedure TCai0001.BbtOkClick(Sender: TObject);
var lNrSenha  : Integer;
    lCdUsuari : Integer;
    lNmUsuari : String;
begin
      if EdtDsLogin.Text = ''  then
      begin
            raise Exception.Create('Informe seu login de acesso');
      end;

      ValidarLogin(EdtDsLogin.Text, lNrSenha, lCdUsuari, lNmUsuari);

      // primeiro acesso
      if lNrSenha = 0 then
      begin
            if AlterarSenha(EdtDsLogin.Text) then
            begin
                  ModalResult := MrOk;
            end else
            begin
                  ModalResult := MrCancel;
            end;
      end else
      begin
            if EdtDsSenha.Text = '' then
            begin
                  raise Exception.Create('Informe sua senha de acesso.');
            end;

            BrvSenha.Login := EdtDsLogin.Text;
            BrvSenha.Senha := EdtDsSenha.Text;

            if BrvSenha.Codigo <> lNrSenha then
            begin
                  EdtDsSenha.Text := '';
                  raise Exception.Create('Senha inválida');
            end;

            ModalResult := mrOk;
      end;

      if ModalResult = mrOk then
      begin
            DmSrvApl.BrvDicionario.UserCode  := lCdUsuari;
            DmSrvApl.BrvDicionario.UserLogin := EdtDsLogin.Text;
            DmSrvApl.BrvDicionario.UserName  := lNmusuari;
      end;
end;

procedure TCai0001.FormShow(Sender: TObject);
begin
      inherited;
      BringToFront;

      if EdtDsLogin.Text <> '' then
      begin
            EdtDsSenha.SetFocus;
      end;
end;

procedure TCai0001.ValidarLogin(pDsLogin : String; var pNrSenha : Integer;
                                                   var pCdUsuari : Integer;
                                                   var pNmUsuari : String);
var lConexao : TSDmRWClient;
    lDsResult: String;
begin
      lConexao := TSDmRWClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          lConexao.AutenticarUsuario(DmSrvApl.BrvDicionario.Credencial, lDsResult,
                                     pDsLogin, pNrSenha, pCdUsuari, pNmUsuari);

          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);

      finally
          FreeAndNil(lConexao);
      end;
end;

function TCai0001.AlterarSenha(pDsLogin : String) : Boolean;
begin
      Cai0002 := TCai0002.Create(Self);

      try
          Cai0002.BrvSenha.Login := pDsLogin;

          if Cai0002.ShowModal = MrOk then
          begin
                Result := True;
          end else
          begin
                Result := False;
          end;
      finally
          FreeAndNil(Cai0002);
      end;
end;

end.

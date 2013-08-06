unit UCai0002;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UCai0000, StdCtrls, Buttons, BrvBitBtn, ExtCtrls, BrvSenha, UClaSrv;

type
  TCai0002 = class(TCai0000)
    Label1: TLabel;
    Label3: TLabel;
    EdtDsNewSe1: TEdit;
    EdtDsNewSe2: TEdit;
    BrvSenha: TBrvSenha;
    procedure BbtOkClick(Sender: TObject);
  private
    procedure AtualizarSenha;
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Cai0002: TCai0002;

implementation

uses UDmSrvApl;

{$R *.dfm}

procedure TCai0002.BbtOkClick(Sender: TObject);
begin
      inherited;

      if EdtDsNewSe1.Text <> EdtDsNewSe2.Text then
      begin
            raise Exception.Create('Nova senha difere da confirmação.');
      end;

      BrvSenha.Senha := EdtDsNewSe1.Text;
      AtualizarSenha;
      ModalResult := mrOk;
end;

procedure TCai0002.AtualizarSenha;
var lConexao  : TSDmRWClient;
    lDsResult : String;
begin
      lConexao := TSDmRWClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          lConexao.AtualizarSenhaUsuario(DmSrvApl.BrvDicionario.Credencial, lDsResult,
                                         BrvSenha.Login, BrvSenha.Codigo);

          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);

      finally
          FreeAndNil(lConexao);
      end;
end;

end.

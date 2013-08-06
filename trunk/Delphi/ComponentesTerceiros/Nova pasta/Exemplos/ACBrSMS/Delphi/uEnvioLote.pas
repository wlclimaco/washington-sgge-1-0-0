unit uEnvioLote;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls;

type
  TfrmEnvioLote = class(TForm)
    btnTrocarChip: TButton;
    btnCancelar: TButton;
    OpenDialog1: TOpenDialog;
    procedure btnTrocarChipClick(Sender: TObject);
    procedure btnCancelarClick(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  frmEnvioLote: TfrmEnvioLote;

implementation

uses
  uPrincipal, ACBrSMSClass;

{$R *.dfm}

procedure TfrmEnvioLote.btnCancelarClick(Sender: TObject);
begin
  Self.Close;
end;

procedure TfrmEnvioLote.btnTrocarChipClick(Sender: TObject);
var
  Indices: String;
  LoteMsgs: TACBrSMSMensagens;
begin
  if OpenDialog1.Execute then
  begin
    LoteMsgs := TACBrSMSMensagens.Create;
    try
      // envio de lote apartir de arquivo
      // cada linha do arquivo � uma mensagem, seguinte o padr�o:
      // 1122223333|Mensagem que deseja enviar
      //
      LoteMsgs.LoadFromFrile(OpenDialog1.FileName);

      {
        Pode ser populada a m�o utilizado o m�todo Add, assim o usu�rio pode
        montar a lista a partir de uma tabela no banco de dados por exemplo

        Exemplo:

        while not Tabela.Eof do
        begin
          with LoteMsgs.Add do
          begin
            Telefone := '1122223333';
            Mensagem := 'Mensagem que deseja enviar';
          end;

          Tabela.Next;
        end;
      }

      frmPrincipal.ACBrSMS1.EnviarSMSLote(LoteMsgs, Indices);
      ShowMessage('Enviadas: ' + Indices);
    finally
      LoteMsgs.Free;
    end;
  end;
end;

end.

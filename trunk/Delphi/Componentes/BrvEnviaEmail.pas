unit BrvEnviaEmail;

interface

uses
  Windows, IdSMTPBase, IdSMTP, IdComponent, IdTCPConnection, IdTCPClient,
  IdExplicitTLSClientServerBase, IdMessageClient, IdPOP3, IdBaseComponent, IdMessage,
  ComCtrls, Controls, StdCtrls, ExtCtrls, Buttons, Classes, IdAttachment, Forms,
  SysUtils, Dialogs;

type
  TFrmMail = class(TForm)
    GroupBox1: TGroupBox;
    EdtDs_de: TEdit;
    GroupBox2: TGroupBox;
    GroupBox3: TGroupBox;
    BitBtn1: TBitBtn;
    BitBtn2: TBitBtn;
    Memo1: TMemo;
    GroupBox4: TGroupBox;
    EdtDs_Assunto: TEdit;
    IdMessage: TIdMessage;
    IdPOP3: TIdPOP3;
    IdSMTP: TIdSMTP;
    Panel2: TPanel;
    LblDsProces: TLabel;
    PgbProgres: TProgressBar;
    procedure BitBtn2Click(Sender: TObject);
    procedure BitBtn1Click(Sender: TObject);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
  private
    { Private declarations }
      gDsEmaDes : String; // e-mail do destinatário
      gDsSuject : String; // Assunto
      gDsBody   : String; // Corpo do e-mail
      gDsFroAdr : String; // e-mail remetente
      gNmFrom   : String; // nome do remetente
      gNmHosSmt : String; // Host do smtp
      gDsPasswo : String; // Senha
      gSnMensag : String; // Mostra Mensagem?
      gDsAnexo  : String;
    procedure EnviarEmail; // Anexo
  public
    Ds_to:String;
    Procedure AssociaTo(DsTo:String);

    { Public declarations }
  end;

var
  FrmMail: TFrmMail;
  Disconecta:Boolean;

implementation

{$R *.DFM}

procedure TFrmMail.BitBtn2Click(Sender: TObject);
begin
     Close;
end;

procedure TFrmMail.BitBtn1Click(Sender: TObject);
begin
     if Pos('@',EdtDs_de.Text)>0 then
     begin
           raise Exception.Create('Nome do usuário não informado');
     end;

      gDsEmaDes := Ds_To;
      gDsSuject := EdtDs_Assunto.Text;
      gDsBody   := Memo1.Lines.Text;
      gDsFroAdr := '';
      gNmFrom   := '';
      gNmHosSmt := '';
      gDsPasswo := '';
      gSnMensag := 'S';
      gDsAnexo  := '';
end;


Procedure TfrmMail.AssociaTo(DsTo:String);
begin
     Ds_To:=Dsto;
end;

procedure TFrmMail.FormClose(Sender: TObject; var Action: TCloseAction);
begin
     Action:=CaFree;
end;

procedure TFrmMail.EnviarEmail;
var lStlAnexo : TStrings;
    lNoAnexo  : Integer;
begin
      if gNmHosSmt <> '' then
      begin
            Refresh;
            // Dados da Mensagem
            IdMessage.Recipients.EMailAddresses := gDsEmaDes;  // email do Destinatário
            IdMessage.Subject                   := gDsSuject;  // assunto
            IdMessage.Body.Text                 := gDsBody;    // texto da mensagem
            IdMessage.From.Address              := gDsFroAdr;  // Email do Remetente
            IdMessage.From.Name                 := gNmFrom;    // Nome do Remetente, que aparecerá qdo chegar.

            //Manipulando os Anexos
          //  for Anexo := 0 to lbxAnexos.Items.Count-1 do
          //    TIdAttachment.Create(IdMessage.MessageParts, TFileName(lbxAnexos.Items.Strings[Anexo]));

          // até no Delphi 7 era assim, mas pra variar, não é mais
{            if gDsAnexo <> '' then
            begin
                  lStlAnexo := TStringList.Create;

                  try
                      lStlAnexo.CommaText := gDsAnexo;

                      for lNoAnexo := 0 to lStlAnexo.Count -1 do
                      begin
                            if lStlAnexo.Strings[lNoAnexo] <> '' then
                            begin
                                  TIdAttachment.Create(IdMessage.MessageParts, TFileName(lStlAnexo.Strings[lNoAnexo]));
                            end;
                      end;
                  finally
                      FreeAndNil(lStlAnexo);
                  end;
            end;
 }
            IdSMTP.Host                        := gNmHosSmt;  // servidor SMTP do seu provedor
          //  IdSMTP.AuthenticationType := atNone {atLogin};  // sem autenticação (já foi feita no POP3)
            // !! -- caso seu provedor não use POP3, apague as configurações POP3, mude a autenticação para atLogin
            // !! -- e habilite as 2 linhas abaixo:
            //IdSMTP.Username                    := gDsFroAdr; // seu login de e-mail
            //IdSMTP.Password                    := gDsPasswo; // sua senha  -- Recomenda-se usar uma Edit com máscara!!

            PgbProgres.Position := 1;
            LblDsProces.Caption := 'Conectando ...';
            LblDsProces.Refresh;

            try
                IdSMTP.Connect;
                try
                    try
                        PgbProgres.Position := 2;
                        LblDsProces.Caption := 'Enviando ...';
                        LblDsProces.Refresh;
                        IdSMTP.Send(IdMessage);
                    except
                        MessageDlg('Erro ao enviar e-mail!', mtError, [mbok], 0);
                        raise;
                    end;
                finally
                    PgbProgres.Position := 3;
                    PgbProgres.StepIt;
                    LblDsProces.Caption := 'Desconectando ...';
                    LblDsProces.Refresh;
                    IdSMTP.Disconnect;
                end;
            except
                MessageDlg('Erro ao tentar conectar-se com servidor smtp',
                                                                  mtError, [mbok], 0);
                raise;
            end;

            LblDsProces.Caption := 'Finalizado.';
            LblDsProces.Refresh;

            PgbProgres.Position := 4;
            if gSnMensag = 'S' then
            begin
                  MessageDlg('e-mail enviado com sucesso!', mtInformation, [mbok], 0);
            end;
      end;
end;

end.

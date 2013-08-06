unit UFrmAtuEnd;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, Mask, BrvEditNum, ExtCtrls, Buttons, BrvBitBtn, BrvIP;

type
  TFrmAtuEnd = class(TForm)
    Panel1: TPanel;
    Label1: TLabel;
    Label2: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    Panel2: TPanel;
    BtnOk: TBrvBitBtn;
    BtnCancel: TBrvBitBtn;
    EdtIpIntern: TEdit;
    EdtIpExtern: TEdit;
    BrvIP: TBrvlIP;
    Label5: TLabel;
    EdtNoIpLoca: TEdit;
    EdtNoPorta: TEdit;
    procedure BtnCancelClick(Sender: TObject);
    procedure BtnOkClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  FrmAtuEnd: TFrmAtuEnd;

implementation

{$R *.dfm}

procedure TFrmAtuEnd.BtnCancelClick(Sender: TObject);
begin
      ModalResult := mrCancel;
end;

procedure TFrmAtuEnd.BtnOkClick(Sender: TObject);
begin
      if EdtIpIntern.Text = '' then
      begin
            raise Exception.Create('Informe o endereço IP interno.');
      end;

      if EdtIpExtern.Text = '' then
      begin
            raise Exception.Create('Informe o endereço IP externo.');
      end;

      if (StrToIntDef(EdtNoPorta.Text, 0) = 0)  then
      begin
            raise Exception.Create('Informe o número da porta de conexão');
      end;

      ModalResult := mrOk;
end;

procedure TFrmAtuEnd.FormCreate(Sender: TObject);
begin
      BrvIP.BrComputadorLocal := True;
      EdtNoIpLoca.Text := BrvIp.BrEnderecoIP;
end;

end.

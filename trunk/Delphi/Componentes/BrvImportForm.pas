unit BrvImportForm;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  ExtCtrls, StdCtrls, BrvEdit, Buttons, BrvBitBtn;

type
  TFrmImport = class(TForm)
    Panel1: TPanel;
    Label1: TLabel;
    EdtNmOrigem: TBrvEdit;
    BitBtn1: TBrvBitBtn;
    BitBtn2: TBrvBitBtn;
    RgpOperac: TRadioGroup;
    procedure BitBtn1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  FrmImport: TFrmImport;

implementation

{$R *.DFM}

procedure TFrmImport.BitBtn1Click(Sender: TObject);
begin
      if EdtNmOrigem.Text = ''  then
      begin
            raise Exception.Create('Informe o arquivo de origem');
      end;

      if not FileExists(EdtNmOrigem.Text) then
      begin
            raise Exception.Create('Arquivo informado não existe');
      end;

      ModalResult := MrOk;
end;

end.

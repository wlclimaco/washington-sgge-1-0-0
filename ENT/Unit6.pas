unit Unit6;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs,Unit5, StdCtrls;

type
  TForm6 = class(TForm)
    Button1: TButton;
    Edit1: TEdit;
    Label1: TLabel;
    procedure Button1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form6: TForm6;

implementation

{$R *.dfm}

procedure TForm6.Button1Click(Sender: TObject);
begin
  { Classproduto := TProduto.Create;
   Classproduto.FCODPRO := StrToInt(Edit1.Text);
   Label1.Caption := IntToStr(Classproduto.FCODPRO);
   Classproduto.Free;  }
end;

end.

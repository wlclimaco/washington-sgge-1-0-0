unit Unit61;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls;

type
  TForm61 = class(TForm)
    Edit1: TEdit;
    Edit2: TEdit;
    Edit3: TEdit;
    Button1: TButton;
    Button2: TButton;
    procedure Button1Click(Sender: TObject);
    procedure Button2Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form61: TForm61;

implementation

uses Unit2;

{$R *.dfm}
var
    DataModule2 : TDataModule2;
procedure TForm61.Button1Click(Sender: TObject);


begin
  DataModule2.baixarestoque(11,'3',10);
end;

procedure TForm61.Button2Click(Sender: TObject);
begin
    DataModule2.aumentarestoque(11,'3',10);
end;

end.

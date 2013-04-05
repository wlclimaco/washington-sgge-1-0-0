unit Unit1;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls;

type
  TForm1 = class(TForm)
    Button1: TButton;
    Edit1: TEdit;
    Edit2: TEdit;
    procedure Button1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;
function MaiorValor(chNFe,CNPJ,nProt,xJust:String):String;stdcall;
var
  Form1: TForm1;

implementation

{$R *.dfm}
function MaiorValor(chNFe,CNPJ,nProt,xJust:String):String;external 'Project1.dll';
procedure TForm1.Button1Click(Sender: TObject);
var
x, y, resultado : double;

 begin
  Showmessage(MaiorValor('','','','')) ;
 end;


end.

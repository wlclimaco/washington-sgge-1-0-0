unit Unit66;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DB, DBTables, StdCtrls, ComCtrls;

type
  TForm66 = class(TForm)
    Button1: TButton;
    ProgressBar1: TProgressBar;
    procedure Button1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form66: TForm66;

implementation

uses Unit2;

{$R *.dfm}

procedure TForm66.Button1Click(Sender: TObject);
var
  cdpro,x,cont :Integer;
  preco,estoque :double;
  pre : string;
begin
  cont := 1;
DataModule2.Query3.Active := True;
  ProgressBar1.Min := 0 ;
  ProgressBar1.Max := DataModule2.Query3.RecordCount;
while (not DataModule2.Query3.Eof)do
begin
  x:=0;

  cdpro := DataModule2.Query3.fieldbyname('codpro').AsInteger;
  DataModule2.Query4.SQL.Text := 'select *from transfer where codpro ='+IntToStr(cdpro) ;
  DataModule2.Query4.Active := true;
  estoque := DataModule2.Query4.fieldbyname('estq3').AsFloat;
  DataModule2.Query4.Active := false;
  DataModule2.Query5.Active := false;
  DataModule2.Query5.SQL.Text := 'select *from precos where codpro ='+IntToStr(cdpro) ;
  DataModule2.Query5.Active := true;
  preco := DataModule2.Query5.fieldbyname('prec3').AsFloat;
  DataModule2.Query5.Active := false;
  pre := FloatToStr(preco);
  while x <= 6 do
  begin
    if(pre[x]= ',')then
    begin
      pre[x]:= '.';
      inc(x);
    end;
    inc(x);
  end;
  DataModule2.query5.SQL.Text := 'update produtos set estoque ='+FloatToStr(estoque)+' where codpro = '+IntToStr(cdpro);
 // showmessage(DataModule2.query5.SQL.Text);
  DataModule2.query5.ExecSQL;
  ProgressBar1.Position := cont;
  cont := cont + 1 ;
  DataModule2.Query3.Next;
end;
end;

end.

unit Unit77;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls;

type
  TForm77 = class(TForm)
    Button1: TButton;
    Memo1: TMemo;
    procedure Button1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form77: TForm77;

implementation

uses Unit2, DB;

{$R *.dfm}

procedure TForm77.Button1Click(Sender: TObject);
var
  pre,produto :string;
  valor : Double;
  x:Integer;
begin
DataModule2.Query9.SQL.Text := 'select *from produtos';
DataModule2.Query9.Active := True;
while not DataModule2.Query9.Eof do
begin
  valor := dataModule2.Query9.fieldbyname('precovenda').AsCurrency;
  pre := FloatToStr(valor);
  produto := dataModule2.Query9.fieldbyname('produto').AsString;
    x := 0;
  while x <= 6 do
  begin
    if(pre[x]= ',')then
    begin
      pre[x]:= '.';
      inc(x);
    end;
    inc(x);
  end;
  //===========
    x := 0;
    while x <= 20 do
  begin
    if(produto[x]= '''') or (produto[x]= '`')  then
    begin
      produto[x]:= ' ';
      inc(x);
    end;
    inc(x);
  end;


Memo1.Lines.Add('values('+ IntToStr(dataModule2.Query9.fieldbyname('codpro').asinteger)+','''+produto+''','+IntToStr(dataModule2.Query9.fieldbyname('codcli').asinteger)+','+pre+','+inttostr(0)+',''F'')');
DataModule2.Query8.SQL.Text := 'insert into etiquetas (codpro,produto,codcli,preco,quantidade,Imprime)values('+ IntToStr(dataModule2.Query9.fieldbyname('codpro').asinteger)+','''+dataModule2.Query9.fieldbyname('produto').AsString +''','+IntToStr(dataModule2.Query9.fieldbyname('codcli').asinteger)+','+pre+','+inttostr(0)+',''F'')';
//ShowMessage(DataModule2.Query8.SQL.Text);
DataModule2.Query8.ExecSQL;

DataModule2.Query9.Next;
end;
end;

end.

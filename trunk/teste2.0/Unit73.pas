unit Unit73;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DB, DBTables, StdCtrls, DBCtrls, Grids, DBGrids, Buttons,
  IBCustomDataSet, IBUpdateSQL, IBTable, IBQuery;

type
  TForm73 = class(TForm)
    Edit1: TEdit;
    DBText1: TDBText;
    DataSource1: TDataSource;
    DataSource2: TDataSource;
    Edit2: TEdit;
    DBGrid1: TDBGrid;
    SpeedButton1: TSpeedButton;
    IBQuery1: TIBQuery;
    Query1: TIBQuery;
    tbletiques1: TIBTable;
    tbletiques1ETIQUETA: TIntegerField;
    tbletiques1CODPRO: TIntegerField;
    tbletiques1PRODUTO: TIBStringField;
    tbletiques1CODCLI: TIntegerField;
    tbletiques1QUANTIDADE: TFloatField;
    tbletiques1IMPRIME: TIntegerField;
    Query2: TIBQuery;
    tbletiques1PRECO: TFloatField;
    DataSource3: TDataSource;
    SpeedButton2: TSpeedButton;
    procedure Button1Click(Sender: TObject);
    procedure Edit2Exit(Sender: TObject);
    procedure FormActivate(Sender: TObject);
    procedure SpeedButton1Click(Sender: TObject);
    procedure Edit1Exit(Sender: TObject);
    procedure SpeedButton2Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form73: TForm73;

implementation

uses Unit2, Unit74, Unit1;

{$R *.dfm}

procedure TForm73.Button1Click(Sender: TObject);
begin
query2.Active := false;
query2.SQL.Text := 'select *from etiquetas' ;
query2.Active := True;
form74.RLReport1.Preview();
end;

procedure TForm73.Edit2Exit(Sender: TObject);
var
  x,codpro,quantidade,codcli:Integer;
  produto,ref : string;
  preco : String;
begin
if (edit1.Text <> '')then
   query1.SQL.Text := 'select *from Prod_serv where cod_produto ='+edit1.Text;
   query1.Active := true;
   x:= 0;
   codpro   := query1.fieldbyname('cod_produto').AsInteger;
   produto  := query1.fieldbyname('DESCR_PRODUTO').AsString;
   codcli   := query1.fieldbyname('derivacao').Asinteger;
   ref      := query1.fieldbyname('REF').AsString;
   preco    := CurrToStr(query1.fieldbyname('precovenda').AsCurrency);
   quantidade := StrToInt(Edit2.text);

   while quantidade > x do
   begin

   tbletiques1.Insert;
   tbletiques1codpro.AsInteger := codpro;
   tbletiques1Produto.AsString := produto;
   tbletiques1Codcli.AsString := ref ;
   tbletiques1Preco.AsCurrency := StrToCurr(PRECO);
   tbletiques1Quantidade.AsInteger := x;
   tbletiques1.Post;

   Inc(x);
   end;
   tbletiques1.Active := False;
   tbletiques1.Active := true;
end;

procedure TForm73.FormActivate(Sender: TObject);
begin
   query1.SQL.Text := 'delete from etiqueta';
   query1.ExecSQL;
   tbletiques1.Active := False;
   tbletiques1.Active := true;
end;

procedure TForm73.SpeedButton1Click(Sender: TObject);
begin
query2.Active := false;
query2.SQL.Text := 'select *from etiqueta' ;
query2.Active := True;
form74.RLReport1.Preview();
end;

procedure TForm73.Edit1Exit(Sender: TObject);
begin
    IBQuery1.Active := False;
    IBQuery1.ParamByName('codpro').AsInteger := StrToInt(Edit1.Text);
    IBQuery1.Active := true;
end;

procedure TForm73.SpeedButton2Click(Sender: TObject);
begin
   query1.SQL.Text := 'delete from etiqueta';
   query1.ExecSQL;
   tbletiques1.Active := False;
   tbletiques1.Active := true;
end;

end.
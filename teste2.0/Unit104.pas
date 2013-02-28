unit Unit104;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DB, DBTables, ComCtrls, StdCtrls, Mask;

type
  TForm104 = class(TForm)
    Button1: TButton;
    MaskEdit1: TMaskEdit;
    ProgressBar1: TProgressBar;
    Query1: TQuery;
    MaskEdit2: TMaskEdit;
    Table1: TTable;
    Table1Codpro: TFloatField;
    Table1Ref: TStringField;
    Table1Qtentrada: TFloatField;
    Table1Data: TDateField;
    Table1Qtsaida: TFloatField;
    Table1Estoque: TFloatField;
    Table1Estoquemesant: TFloatField;
    procedure Button1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form104: TForm104;

implementation

uses Unit2;

{$R *.dfm}

procedure TForm104.Button1Click(Sender: TObject);
begin
Query1.Active := False;
Query1.SQL.Text := 'select codpro,sum(quantidade) as total  from nfsaidas2 where dtmovimento between '''+FormatDateTime('dd/MM/yyyy',StrToDateTime(MaskEdit1.Text) ) +''' and '''+FormatDateTime('dd/MM/yyyy',StrToDateTime(MaskEdit2.Text) )+''' group by codpro ';
Query1.Active := tRUE;
SHOWMESSAGE(Query1.SQL.Text);
while(not Query1.Eof)do
begin
Table1.Insert;
Table1Codpro.Asinteger := Query1.fieldbyname('codpro').asInteger;
Table1Qtsaida.AsFloat := Query1.fieldbyname('total').AsFloat;
Table1.Post;
Query1.Next;
end;
end;

end.

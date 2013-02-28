unit Unit67;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, Buttons, ExtCtrls, Grids, DBGrids, DB, DBTables;

type
  TForm67 = class(TForm)
    DBGrid1: TDBGrid;
    Panel1: TPanel;
    Panel2: TPanel;
    Edit1: TEdit;
    ComboBox1: TComboBox;
    SpeedButton1: TSpeedButton;
    Query1: TQuery;
    DataSource1: TDataSource;
    procedure DBGrid1DblClick(Sender: TObject);
    procedure SpeedButton1Click(Sender: TObject);
  private
    { Private declarations }
  public
    function retornacodpro(const teste :string):Integer;
  end;

var
  Form67: TForm67;

implementation

uses Unit60, Unit2, Unit53;

{$R *.dfm}

function TForm67.retornacodpro(const teste :string):Integer;
var a :integer;
begin
 result := DBGrid1.Fields[0].asinteger;
 close();
end;

procedure TForm67.DBGrid1DblClick(Sender: TObject);
begin
FRMNFENTRADAS.Edit1.Text := IntToStr(DBGrid1.Fields[0].asinteger);
FRMPRODUTOS.Edit1.Text := IntToStr(DBGrid1.Fields[0].asinteger);
close();
end;

procedure TForm67.SpeedButton1Click(Sender: TObject);
VAR
  SQL : sTRING;
begin
  qUERY1.Active := fALSE;
  qUERY1.SQL.Clear;
  qUERY1.SQL.Text := 'SELECT *FROM PRODUTOS WHERE CODPRO > 0 ';
  IF (ComboBox1.ItemIndex = 0)THEN
  BEGIN
    qUERY1.SQL.Add('AND CODPRO = '+eDIT1.Text );
  end;
  IF (ComboBox1.ItemIndex = 1)THEN
  BEGIN
    qUERY1.SQL.Add('AND PRODUTO LIKE ''%'+eDIT1.Text+'%''' );
  end;
  IF (ComboBox1.ItemIndex = 2)THEN
  BEGIN
    qUERY1.SQL.Add('AND CODIND = '+eDIT1.Text );
  end;
  IF (ComboBox1.ItemIndex = 3)THEN
  BEGIN
    qUERY1.SQL.Add('AND REF LIKE ''%'+eDIT1.Text+'%''' );
  end;
    IF (ComboBox1.ItemIndex = 4)THEN
  BEGIN
    qUERY1.SQL.Add(' ' );
  end;
  qUERY1.Active := TRUE;
end;

end.

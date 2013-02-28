unit Unit4;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Grids, DBGrids, StdCtrls, Buttons, ExtCtrls, DB, DBTables;

type
  TCONSBAIXAS = class(TForm)
    DBGrid1: TDBGrid;
    Panel1: TPanel;
    Label1: TLabel;
    Edit1: TEdit;
    GroupBox1: TGroupBox;
    SpeedButton1: TSpeedButton;
    RadioButton1: TRadioButton;
    RadioButton2: TRadioButton;
    RadioButton3: TRadioButton;
    Panel2: TPanel;
    SpeedButton2: TSpeedButton;
    SpeedButton3: TSpeedButton;
    Query1: TQuery;
    DataSource1: TDataSource;
    Query1CODIBX: TFloatField;
    Query1PRODUTO: TStringField;
    Query1REF: TStringField;
    Query1CLASS: TStringField;
    Query1LOCAL: TStringField;
    Query1DATA: TDateField;
    Query1LOJ1: TSmallintField;
    Query1ESTQ1: TFloatField;
    Query1LOJ2: TSmallintField;
    Query1ESTQ2: TFloatField;
    Query1LOJ3: TSmallintField;
    Query1ESTQ3: TFloatField;
    Query1UNIT: TFloatField;
    Query1TOTAL: TFloatField;
    procedure SpeedButton1Click(Sender: TObject);
    procedure SpeedButton3Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  CONSBAIXAS: TCONSBAIXAS;

implementation

uses Unit2, Urelbaixas;

{$R *.dfm}

procedure TCONSBAIXAS.SpeedButton1Click(Sender: TObject);
begin
   if(RadioButton1.Checked = true)then
   BEGIN
    DataModule2.QYBAIXAS.Active := FALSE;
    DataModule2.QYBAIXAS.sql.Text := 'select *from baixas where produto like ''%'+Edit1.Text+'%''';
  // SHOWmESSAGE(query1.sql.Text);
    DataModule2.QYBAIXAS.Active := true;
   END;
   if(RadioButton2.Checked = true)then
   BEGIN
    DataModule2.QYBAIXAS.Active := FALSE;
    DataModule2.QYBAIXAS.sql.Text := 'select *from baixas where data = '''+Edit1.Text+'''';
  // SHOWmESSAGE(query1.sql.Text);
    DataModule2.QYBAIXAS.Active := true;
   END;
   if(RadioButton3.Checked = true)then
   BEGIN
    DataModule2.QYBAIXAS.Active := FALSE;
    DataModule2.QYBAIXAS.sql.Text := 'select *from baixas where CODIBX = '+Edit1.Text;
  // SHOWmESSAGE(query1.sql.Text);
    DataModule2.QYBAIXAS.Active := true;
   END;

end;

procedure TCONSBAIXAS.SpeedButton3Click(Sender: TObject);
begin
RelBaixas.RLReport1.Preview();
end;

end.

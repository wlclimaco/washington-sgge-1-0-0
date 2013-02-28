unit Unit32;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Unit1, StdCtrls, Mask, DBCtrls, Buttons, U_DBBButton, ExtCtrls,
  DB, DBTables;

type
  TFRMBAIXAS = class(TForm1)
    Label1: TLabel;
    DBEdit1: TDBEdit;
    Label2: TLabel;
    DBEdit2: TDBEdit;
    Label3: TLabel;
    DBEdit3: TDBEdit;
    Label4: TLabel;
    DBEdit4: TDBEdit;
    Label5: TLabel;
    DBEdit5: TDBEdit;
    Label6: TLabel;
    DBEdit6: TDBEdit;
    Label7: TLabel;
    DBEdit7: TDBEdit;
    Label8: TLabel;
    DBEdit8: TDBEdit;
    Label9: TLabel;
    DBEdit9: TDBEdit;
    Label10: TLabel;
    DBEdit10: TDBEdit;
    Label11: TLabel;
    DBEdit11: TDBEdit;
    Label12: TLabel;
    DBEdit12: TDBEdit;
    Label13: TLabel;
    DBEdit13: TDBEdit;
    Label14: TLabel;
    DBEdit14: TDBEdit;
    Table1: TTable;
    UpdateSQL1: TUpdateSQL;
    DataSource1: TDataSource;
    Table1CODIBX: TFloatField;
    Table1PRODUTO: TStringField;
    Table1REF: TStringField;
    Table1CLASS: TStringField;
    Table1LOCAL: TStringField;
    Table1DATA: TDateField;
    Table1LOJ1: TSmallintField;
    Table1ESTQ1: TFloatField;
    Table1LOJ2: TSmallintField;
    Table1ESTQ2: TFloatField;
    Table1LOJ3: TSmallintField;
    Table1ESTQ3: TFloatField;
    Table1UNIT: TFloatField;
    Table1TOTAL: TFloatField;
    procedure FormActivate(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  FRMBAIXAS: TFRMBAIXAS;

implementation

uses Unit2;

{$R *.dfm}

procedure TFRMBAIXAS.FormActivate(Sender: TObject);
begin
  inherited;
  table1.Active := true;
end;

end.

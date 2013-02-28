unit Unit90;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DBCtrls, StdCtrls, Buttons, U_DBBButton, DB, Mask, DBTables,
  ExtCtrls;

type
  TForm85 = class(TForm)
    Query1: TQuery;
    Query2: TQuery;
    Panel1: TPanel;
    Panel2: TPanel;
    Panel3: TPanel;
    Table1: TTable;
    Table1Id: TAutoIncField;
    Table1Datamovimento: TDateField;
    Table1Total: TCurrencyField;
    Table1Dinheiro: TCurrencyField;
    Table1Cheque: TCurrencyField;
    Table1Cartaocredito: TCurrencyField;
    Table1Cartaodebito: TCurrencyField;
    Table1Promissoria: TCurrencyField;
    Label2: TLabel;
    DBEdit1: TDBEdit;
    DataSource1: TDataSource;
    Label3: TLabel;
    DBEdit2: TDBEdit;
    Label4: TLabel;
    DBEdit3: TDBEdit;
    Label5: TLabel;
    DBEdit4: TDBEdit;
    Label6: TLabel;
    DBEdit5: TDBEdit;
    Label7: TLabel;
    DBEdit6: TDBEdit;
    Label8: TLabel;
    DBEdit7: TDBEdit;
    DBButton1: TDBButton;
    DBButton2: TDBButton;
    DBButton3: TDBButton;
    DBNavigator1: TDBNavigator;
    Table2: TTable;
    Table2Id: TAutoIncField;
    Table2Cdvendedora: TFloatField;
    Table2Data: TDateField;
    Table2Valorvenda: TCurrencyField;
    Label1: TLabel;
    DataSource2: TDataSource;
    Label9: TLabel;
    DBEdit9: TDBEdit;
    Label10: TLabel;
    DBEdit10: TDBEdit;
    DBButton4: TDBButton;
    DBButton5: TDBButton;
    DBButton6: TDBButton;
    DBNavigator2: TDBNavigator;
    DBLookupComboBox1: TDBLookupComboBox;
    DataSource3: TDataSource;
    Bevel1: TBevel;
    Label11: TLabel;
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form85: TForm85;

implementation

uses Unit2;

{$R *.dfm}

end.

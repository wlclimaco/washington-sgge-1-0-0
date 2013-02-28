unit Unit64;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Unit1, DB, DBTables, Grids, DBGrids, DBCtrls, StdCtrls, Buttons,
  U_DBBButton, ExtCtrls;

type
  TFrmExcluirtrasnf = class(TForm1)
    DBGrid1: TDBGrid;
    UpdateSQL1: TUpdateSQL;
    Table1: TTable;
    DataSource1: TDataSource;
    Query1: TQuery;
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  FrmExcluirtrasnf: TFrmExcluirtrasnf;

implementation

uses Unit2;

{$R *.dfm}

end.

unit Unit65;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DBTables, DB, DBCtrls, StdCtrls, Buttons, U_DBBButton, Grids,
  DBGrids, ExtCtrls;

type
  TForm65 = class(TForm)
    Panel1: TPanel;
    DBGrid1: TDBGrid;
    Panel2: TPanel;
    DBButton4: TDBButton;
    DBButton5: TDBButton;
    Panel3: TPanel;
    DBButton1: TDBButton;
    DBButton2: TDBButton;
    DBButton3: TDBButton;
    DBNavigator1: TDBNavigator;
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
  Form65: TForm65;

implementation

{$R *.dfm}

end.

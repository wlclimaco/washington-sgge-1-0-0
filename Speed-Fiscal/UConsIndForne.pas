unit UConsIndForne;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DB, IBCustomDataSet, IBQuery, Grids, DBGrids, ExtCtrls;

type
  TForm17 = class(TForm)
    Panel1: TPanel;
    DBGRID1: TDBGrid;
    IBQuery1: TIBQuery;
    DataSource1: TDataSource;
    procedure DBGRID1DblClick(Sender: TObject);
    procedure DBGRID1DrawColumnCell(Sender: TObject; const Rect: TRect;
      DataCol: Integer; Column: TColumn; State: TGridDrawState);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form17: TForm17;

implementation

uses Unit10;

{$R *.dfm}

procedure TForm17.DBGRID1DblClick(Sender: TObject);
begin
Form10.TXTIND_FORN.Text :=  IntToStr(dbGrid1.Fields[0].AsInteger);
Form17.Close();
end;

procedure TForm17.DBGRID1DrawColumnCell(Sender: TObject; const Rect: TRect;
  DataCol: Integer; Column: TColumn; State: TGridDrawState);
begin
  if State = [] then
  begin
    if IBQuery1.RecNo mod 2 = 1 then
      DBGrid1.Canvas.Brush.Color := clAqua
    else
      DBGrid1.Canvas.Brush.Color := clWhite;
  end;
  DBGrid1.DefaultDrawColumnCell(Rect, DataCol, Column, State);
end;

end.

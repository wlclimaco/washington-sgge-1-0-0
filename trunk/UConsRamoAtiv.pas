unit UConsRamoAtiv;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DB, IBCustomDataSet, IBQuery, Grids, DBGrids, ExtCtrls;

type
  TFRMCONSRAMO = class(TForm)
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
  FRMCONSRAMO: TFRMCONSRAMO;

implementation

uses Unit1, Unit10;

{$R *.dfm}

procedure TFRMCONSRAMO.DBGRID1DblClick(Sender: TObject);
begin
Form10.TXTRAMO.Text :=  IntToStr(dbGrid1.Fields[0].AsInteger);
FRMCONSRAMO.Close();
end;

procedure TFRMCONSRAMO.DBGRID1DrawColumnCell(Sender: TObject; const Rect: TRect;
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

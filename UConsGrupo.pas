unit UConsGrupo;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DB, IBCustomDataSet, IBQuery, Grids, DBGrids, ExtCtrls;

type
  TFrmConsGrupo = class(TForm)
    Panel1: TPanel;
    dbgrid1: TDBGrid;
    IBQuery1: TIBQuery;
    DataSource1: TDataSource;
    procedure dbgrid1DblClick(Sender: TObject);
    procedure dbgrid1DrawColumnCell(Sender: TObject; const Rect: TRect;
      DataCol: Integer; Column: TColumn; State: TGridDrawState);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  FrmConsGrupo: TFrmConsGrupo;

implementation

uses Unit1, UProd_Serv, UFormCadProServ;

{$R *.dfm}

procedure TFrmConsGrupo.dbgrid1DblClick(Sender: TObject);
begin
FRMCADPROD.TxtGrupo.Text := IntToStr(dbGrid1.Fields[0].AsInteger);
FrmConsGrupo.Close();
end;

procedure TFrmConsGrupo.dbgrid1DrawColumnCell(Sender: TObject;
  const Rect: TRect; DataCol: Integer; Column: TColumn;
  State: TGridDrawState);
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

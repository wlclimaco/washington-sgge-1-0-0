unit UConsTipoTitulo;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DB, IBCustomDataSet, IBQuery, Grids, DBGrids, ExtCtrls;

type
  TConsTipoTitulo = class(TForm)
    Panel1: TPanel;
    dbgrid1: TDBGrid;
    IBQuery1: TIBQuery;
    DataSource1: TDataSource;
    procedure dbgrid1DrawColumnCell(Sender: TObject; const Rect: TRect;
      DataCol: Integer; Column: TColumn; State: TGridDrawState);
    procedure dbgrid1DblClick(Sender: TObject);
  private
    { Private declarations }
  public
   function InsertCodRet(const ret :Integer):Boolean;
  end;

var
  ConsTipoTitulo: TConsTipoTitulo;
  CodRet: Integer;
implementation

uses Unit7, Unit8;

{$R *.dfm}
function TConsTipoTitulo.InsertCodRet(const ret :Integer):Boolean;
begin
 CodRet := ret;
end;
procedure TConsTipoTitulo.dbgrid1DrawColumnCell(Sender: TObject; const Rect: TRect;
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

procedure TConsTipoTitulo.dbgrid1DblClick(Sender: TObject);
begin
if (CodRet = 1)then
   Form7.TXTTIPO_TIT.Text := intToStr(dbGrid1.Fields[0].AsInteger);
   ConsTipoTitulo.Close;
end;

end.

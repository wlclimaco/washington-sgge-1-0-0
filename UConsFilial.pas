unit UConsFilial;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DB, IBCustomDataSet, IBQuery, Grids, DBGrids, ExtCtrls;

type
  TForm12 = class(TForm)
    Panel1: TPanel;
    DBGrid1: TDBGrid;
    IBQuery1: TIBQuery;
    DataSource1: TDataSource;
    procedure DBGrid1DblClick(Sender: TObject);
    procedure DBGrid1DrawColumnCell(Sender: TObject; const Rect: TRect;
      DataCol: Integer; Column: TColumn; State: TGridDrawState);
    procedure FormCreate(Sender: TObject);
  private
    { Private declarations }
  public
    function InsertCodRet(const ret :Integer):Boolean;
  end;

var
  Form12: TForm12;
  CodRet: Integer;
implementation

uses UFormCadProServ, Unit8;

{$R *.dfm}
function TForm12.InsertCodRet(const ret :Integer):Boolean;
begin
 CodRet := ret;


end;

procedure TForm12.DBGrid1DblClick(Sender: TObject);
begin
  if (CodRet = 1)then
    FRMCADPROD.txtfilial.Text := IntToStr(dbGrid1.Fields[1].AsInteger);
  if (CodRet = 2)then
    Form7.txtfilial.Text := IntToStr(dbGrid1.Fields[1].AsInteger);
Form12.Close();
end;

procedure TForm12.DBGrid1DrawColumnCell(Sender: TObject; const Rect: TRect;
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

procedure TForm12.FormCreate(Sender: TObject);
begin
IBQuery1.Active := True;
end;

end.

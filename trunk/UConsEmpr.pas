unit UConsEmpr;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DB, IBCustomDataSet, IBQuery, Grids, DBGrids, ExtCtrls;

type
  TForm11 = class(TForm)
    Panel1: TPanel;
    dbgrid1: TDBGrid;
    IBQuery1: TIBQuery;
    DataSource1: TDataSource;
    procedure dbgrid1DblClick(Sender: TObject);
    procedure dbgrid1DrawColumnCell(Sender: TObject; const Rect: TRect;
      DataCol: Integer; Column: TColumn; State: TGridDrawState);
    procedure FormCreate(Sender: TObject);
  private
    { Private declarations }
  public
    function InsertCodRet(const ret :Integer):Boolean;
  end;

var
  Form11: TForm11;
  CodRet: Integer;

implementation

uses UFormCadProServ, UProd_Serv, Unit8;

{$R *.dfm}

function TForm11.InsertCodRet(const ret :Integer):Boolean;
begin
 CodRet := ret;


end;

procedure TForm11.dbgrid1DblClick(Sender: TObject);
begin
  if (CodRet = 1)then
    FRMCADPROD.txtempresa.Text := IntToStr(dbGrid1.Fields[0].AsInteger);
  if (CodRet = 2)then
    Form7.txtempresa.Text := IntToStr(dbGrid1.Fields[0].AsInteger);
  Form11.Close();
end;

procedure TForm11.dbgrid1DrawColumnCell(Sender: TObject; const Rect: TRect;
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

procedure TForm11.FormCreate(Sender: TObject);
begin
IBQuery1.Active := True;
end;

end.

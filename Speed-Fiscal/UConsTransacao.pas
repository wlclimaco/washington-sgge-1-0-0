unit UConsTransacao;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DB, IBCustomDataSet, IBQuery, Grids, DBGrids, ExtCtrls;

type
  TConsTransacao = class(TForm)
    Panel1: TPanel;
    DBGrid1: TDBGrid;
    IBQuery1: TIBQuery;
    DataSource1: TDataSource;
    procedure DBGrid1DblClick(Sender: TObject);
    procedure DBGrid1DrawColumnCell(Sender: TObject; const Rect: TRect;
      DataCol: Integer; Column: TColumn; State: TGridDrawState);
  private
    { Private declarations }
  public
    function InsertCodRet(const ret :Integer):Boolean;
  end;

var
  ConsTransacao: TConsTransacao;
  CodRet :Integer;
implementation

uses Unit1, Unit8;

{$R *.dfm}
function TConsTransacao.InsertCodRet(const ret :Integer):Boolean;
begin
 CodRet := ret;


end;
procedure TConsTransacao.DBGrid1DblClick(Sender: TObject);
begin
  if (CodRet = 1)then
    Form7.txtnatureza.Text := IntToStr(dbGrid1.Fields[0].AsInteger);
ConsTransacao.Close();
end;

procedure TConsTransacao.DBGrid1DrawColumnCell(Sender: TObject;
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

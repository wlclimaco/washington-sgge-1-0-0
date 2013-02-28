unit UFrmConsRep;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DB, IBCustomDataSet, IBQuery, Grids, DBGrids, ExtCtrls;

type
  TFrmConsRep = class(TForm)
    Panel1: TPanel;
    dbgrid1: TDBGrid;
    IBQuery1: TIBQuery;
    DataSource1: TDataSource;
    procedure dbgrid1DblClick(Sender: TObject);
  private
    { Private declarations }
  public
    function recebe(const numero :Integer):Integer;
  end;

var
  FrmConsRep: TFrmConsRep;
  CodForm : Integer;
implementation

uses Unit10, UConsEmpr;

{$R *.dfm}

function TFrmConsRep.recebe(const numero :Integer):Integer;
begin
   CodForm := numero;
   result := 1;
end;

procedure TFrmConsRep.dbgrid1DblClick(Sender: TObject);
begin
IF(CodForm = 1)then
  Form10.TXTCODREPR.Text := IntToStr(dbGrid1.Fields[0].AsInteger);
IF(CodForm = 2)then
IF(CodForm = 3)then
IF(CodForm = 4)then
IF(CodForm = 5)then


Form11.Close();
end;

end.

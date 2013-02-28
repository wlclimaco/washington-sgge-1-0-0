unit Unit6;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Grids, DBGrids, StdCtrls, Buttons, ExtCtrls, DB, DBTables;

type
  TCONSBOLETAS = class(TForm)
    DBGrid1: TDBGrid;
    Panel1: TPanel;
    Panel2: TPanel;
    SpeedButton2: TSpeedButton;
    SpeedButton3: TSpeedButton;
    Label1: TLabel;
    Query1: TQuery;
    DataSource1: TDataSource;
    procedure SpeedButton2Click(Sender: TObject);
    procedure SpeedButton3Click(Sender: TObject);
    procedure FormActivate(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  CONSBOLETAS: TCONSBOLETAS;

implementation

uses Unit2, URELBOLETAS;

{$R *.dfm}

procedure TCONSBOLETAS.SpeedButton2Click(Sender: TObject);
begin
RelBoletas.RLReport1.Preview();
end;

procedure TCONSBOLETAS.SpeedButton3Click(Sender: TObject);
begin
close();
end;

procedure TCONSBOLETAS.FormActivate(Sender: TObject);
begin
query1.Active := true;
end;

end.

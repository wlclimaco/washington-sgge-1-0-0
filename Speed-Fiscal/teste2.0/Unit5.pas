unit Unit5;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Grids, DBGrids, StdCtrls, Buttons, ExtCtrls, DB, DBTables;

type
  TCONSBANCOS = class(TForm)
    DBGrid1: TDBGrid;
    Panel1: TPanel;
    Panel2: TPanel;
    SpeedButton2: TSpeedButton;
    SpeedButton3: TSpeedButton;
    Label1: TLabel;
    Query1: TQuery;
    DataSource1: TDataSource;
    procedure SpeedButton3Click(Sender: TObject);
    procedure SpeedButton2Click(Sender: TObject);
    procedure FormActivate(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  CONSBANCOS: TCONSBANCOS;

implementation

uses Unit2, Urelbancos;

{$R *.dfm}

procedure TCONSBANCOS.SpeedButton3Click(Sender: TObject);
begin
RelBancos.RLReport1.Preview();
end;

procedure TCONSBANCOS.SpeedButton2Click(Sender: TObject);
begin
CLOSE();
end;

procedure TCONSBANCOS.FormActivate(Sender: TObject);
begin
query1.Active := true;
end;

end.

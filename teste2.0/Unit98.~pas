unit Unit98;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DB, DBTables, Buttons, Grids, DBGrids, StdCtrls, ExtCtrls, Mask,
  IBCustomDataSet, IBQuery;

type
  TForm98 = class(TForm)
    Panel1: TPanel;
    Label1: TLabel;
    Edit1: TEdit;
    Panel2: TPanel;
    Panel3: TPanel;
    DBGrid1: TDBGrid;
    SpeedButton1: TSpeedButton;
    DataSource1: TDataSource;
    MaskEdit1: TMaskEdit;
    MaskEdit2: TMaskEdit;
    Label2: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    Label5: TLabel;
    SpeedButton2: TSpeedButton;
    Query1: TIBQuery;
    procedure SpeedButton1Click(Sender: TObject);
    procedure SpeedButton2Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form98: TForm98;

implementation

uses Unit2, Unit99, Unit1;

{$R *.dfm}

procedure TForm98.SpeedButton1Click(Sender: TObject);
begin
query1.SQL.Text := ' SELECT *FROM NFfiscalitens N ,prod_serv P,NFfiscal E WHERE E.DCNUMERO = N.DCNUMERO AND N.cod_item = P.cod_produto ';
if (Edit1.Text <> '')then
begin
 query1.SQL.Add('and N.cod_item = '+Edit1.Text );
end;
 query1.SQL.Add('and E.dtentrada between '''+formatDateTime('mm/dd/yyyy', StrToDate(MaskEdit1.Text))+''' and '''+formatDateTime('mm/dd/yyyy', StrToDate(MaskEdit2.Text))+''' ');
 query1.Active := True;

 


end;

procedure TForm98.SpeedButton2Click(Sender: TObject);
begin
Form99.RLReport1.Preview();
end;

end.

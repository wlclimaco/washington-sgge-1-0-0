unit Unit96;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Buttons, Mask, StdCtrls, ExtCtrls, DB, DBTables, Grids, DBGrids,
  RLXLSFilter, RLFilters, RLPDFFilter;

type
  TForm96 = class(TForm)
    Panel1: TPanel;
    Label1: TLabel;
    Edit1: TEdit;
    MaskEdit1: TMaskEdit;
    MaskEdit2: TMaskEdit;
    Label2: TLabel;
    SpeedButton1: TSpeedButton;
    Query1: TQuery;
    Query2: TQuery;
    DataSource1: TDataSource;
    DBGrid1: TDBGrid;
    SpeedButton2: TSpeedButton;
    procedure SpeedButton1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form96: TForm96;

implementation

uses Unit2;

{$R *.dfm}

procedure TForm96.SpeedButton1Click(Sender: TObject);
begin
query1.SQL.Text := ' select  * from nfentradas where dcnumero > 0 ';
if (Edit1.Text <> '')then
begin
 query1.SQL.Add('and dcnumero = '+Edit1.Text );
end;
 query1.SQL.Add('and dtentrada between '''+formatDateTime('mm/dd/yyyy', StrToDate(MaskEdit1.Text))+''' and '''+formatDateTime('mm/dd/yyyy', StrToDate(MaskEdit2.Text))+''' ');
 //ShowMEssage(query1.SQL.text);
 query1.Active := True;

 //query2.SQL.Text := 'select *from nfentradasitens where dcnumero + '++' and   ;



 end;

end.

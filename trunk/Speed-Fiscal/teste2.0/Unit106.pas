unit Unit106;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Buttons, StdCtrls, Mask, DB, DBTables;

type
  TForm106 = class(TForm)
    Query1: TQuery;
    Query2: TQuery;
    MaskEdit1: TMaskEdit;
    MaskEdit2: TMaskEdit;
    Label1: TLabel;
    Label2: TLabel;
    SpeedButton1: TSpeedButton;
    DataSource1: TDataSource;
    DataSource2: TDataSource;
    procedure SpeedButton1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form106: TForm106;

implementation

uses Unit2, Unit105;

{$R *.dfm}

procedure TForm106.SpeedButton1Click(Sender: TObject);
begin
query1.Active := false;
query2.Active := false;
query1.SQL.Text := 'select *from movimentodiario where datamovimento BETWEEN '''+FormatDateTime('mm/DD/yyyy',StrToDate(MaskEdit1.text)) +''' AND '''+FormatDateTime('mm/DD/yyyy',StrToDate(MaskEdit2.text))+ '''  ORDER BY  DATAMOVIMENTO  ';
SHOWMESSAGE(query1.SQL.Text);
query2.SQL.Text := 'select *from vendasvendedora where data BETWEEN '''+FormatDateTime('mm/DD/yyyy',StrToDate(MaskEdit1.text)) +''' AND '''+FormatDateTime('mm/DD/yyyy',StrToDate(MaskEdit2.text))+ ''' order by data ';
SHOWMESSAGE(query2.SQL.Text);
query1.Active := True;
query2.Active := True;
form105.RLReport1.Preview();
end;

end.

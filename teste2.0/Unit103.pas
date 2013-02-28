unit Unit103;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DB, Buttons, StdCtrls, Mask, ExtCtrls, DBTables, TeEngine,
  Series, TeeProcs, Chart, DbChart, IBCustomDataSet, IBQuery;

type
  TForm103 = class(TForm)
    Panel1: TPanel;
    Label1: TLabel;
    MaskEdit1: TMaskEdit;
    MaskEdit2: TMaskEdit;
    Label2: TLabel;
    Label3: TLabel;
    SpeedButton1: TSpeedButton;
    Panel2: TPanel;
    DataSource1: TDataSource;
    DBChart1: TDBChart;
    Query1: TIBQuery;
    Series1: TBarSeries;
    procedure SpeedButton1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form103: TForm103;

implementation

uses Unit2, Unit1;

{$R *.dfm}

procedure TForm103.SpeedButton1Click(Sender: TObject);
begin
QUERY1.SQL.Text := 'SELECT CODPRO ,SUM(QUANTIDADE) FROM NFSAIDAS2 WHERE DTMOVIMENTO between '''+FormatDateTime('DD/MM/YYYY',StrToDate(MaskEdit1.text))+''' and '''+FormatDateTime('DD/MM/YYYY',StrToDate(MaskEdit2.text))+''' group by CODPRO  ';
end;

end.

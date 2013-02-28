unit Unit111;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, ExtCtrls, Mask, Grids, DBGrids, DB, DBTables, Buttons;

type
  TForm111 = class(TForm)
    Panel1: TPanel;
    Label1: TLabel;
    MaskEdit1: TMaskEdit;
    Label3: TLabel;
    RadioGroup1: TRadioGroup;
    RadioButton1: TRadioButton;
    RadioButton2: TRadioButton;
    MaskEdit2: TMaskEdit;
    Query1: TQuery;
    DataSource1: TDataSource;
    DBGrid1: TDBGrid;
    SpeedButton1: TSpeedButton;
    procedure SpeedButton1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form111: TForm111;

implementation

uses Unit2, Unit112;

{$R *.dfm}

procedure TForm111.SpeedButton1Click(Sender: TObject);
begin
query1.Active := False;
query1.SQL.Text := 'select m.datamovimento,m.total,r.vendabruta,sum(m.total - r.vendabruta) from redleitura r ,movimentodiario m where r.dtmovimento = m.datamovimento  and m.datamovimento between '''+FormatDateTime('mm/DD/yyyy',StrToDate(MaskEdit1.text)) +''' AND '''+FormatDateTime('mm/DD/yyyy',StrToDate(MaskEdit2.text))+ '''  group by m.datamovimento,m.total,r.vendabruta';
query1.Active := True;
Form112.RLReport1.Preview();
end;

end.

unit Unit108;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Grids, DBGrids, DB, DBTables, Buttons, StdCtrls, Mask, ExtCtrls,
  IBCustomDataSet, IBQuery;

type
  TForm108 = class(TForm)
    Panel1: TPanel;
    Label1: TLabel;
    MaskEdit1: TMaskEdit;
    Label2: TLabel;
    MaskEdit2: TMaskEdit;
    Label3: TLabel;
    SpeedButton1: TSpeedButton;
    DataSource1: TDataSource;
    DBGrid1: TDBGrid;
    RadioGroup1: TRadioGroup;
    RadioButton1: TRadioButton;
    RadioButton2: TRadioButton;
    GroupBox1: TGroupBox;
    ComboBox1: TComboBox;
    Query1: TIBQuery;
    Query1DATA: TDateField;
    Query1CODVEND: TIntegerField;
    Query1NOME: TIBStringField;
    Query1TOTAL: TFloatField;
    procedure SpeedButton1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form108: TForm108;

implementation

uses Unit2, Unit109, Unit110, Unit1;

{$R *.dfm}

procedure TForm108.SpeedButton1Click(Sender: TObject);
begin
if (RadioButton1.Checked = True) then
begin
Query1.Active := false;
//Query1.sql.Clear;
if(ComboBox1.ItemIndex = 0 )then
Query1.sql.Text := 'Select v.data, v.codvend,e.nome ,sum(v.valorvenda) as total  from vendasvendedora v ,empregad e where v.codvend = e.id and v.data between '''+FormatDateTime('mm/DD/yyyy',StrToDate(MaskEdit1.text)) +''' AND '''+FormatDateTime('mm/DD/yyyy',StrToDate(MaskEdit2.text))+ ''' group by v.data, v.codvend,e.nome'
else
if(ComboBox1.ItemIndex = 1 )then
Query1.sql.Text := 'Select v.data, v.codvend,e.nome ,sum(v.valorvenda) as total  from vendasvendedora v ,empregad e where v.codvend = e.id and v.data between '''+FormatDateTime('mm/DD/yyyy',StrToDate(MaskEdit1.text)) +''' AND '''+FormatDateTime('mm/DD/yyyy',StrToDate(MaskEdit2.text))+ '''and v.cdvend = '+IntToStr(6)+'  group by v.data, v.codvend,e.nome'
else
if(ComboBox1.ItemIndex = 2 )then
Query1.sql.Text := 'Select v.data, v.codvend,e.nome ,sum(v.valorvenda) as total  from vendasvendedora v ,empregad e where v.codvend = e.id and v.data between '''+FormatDateTime('mm/DD/yyyy',StrToDate(MaskEdit1.text)) +''' AND '''+FormatDateTime('mm/DD/yyyy',StrToDate(MaskEdit2.text))+ '''and v.cdvend = '+IntToStr(8)+' group by v.data, v.codvend,e.nome'
else
if(ComboBox1.ItemIndex = 3 )then
Query1.sql.Text := 'Select v.data, v.codvend,e.nome ,sum(v.valorvenda) as total  from vendasvendedora v ,empregad e where v.codvend = e.id and v.data between '''+FormatDateTime('mm/DD/yyyy',StrToDate(MaskEdit1.text)) +''' AND '''+FormatDateTime('mm/DD/yyyy',StrToDate(MaskEdit2.text))+ '''and v.cdvend = '+IntToStr(5)+' group by v.data, v.codvend,e.nome'
else
  ShowMessage('Favor marcar a  opção de vendedora !');
Query1.Active := true;
Form109.RLReport1.Preview();
end
 else
  if (RadioButton2.Checked = True) then
  begin
   Query1.Active := false;
   Query1.sql.Clear;
   Query1.sql.Text := 'Select v.cdvendedora,e.nome ,sum(v.valorvenda) as total  from vendasvendedora v ,empregad e where v.cdvendedora = e.codemp and v.data between '''+FormatDateTime('mm/DD/yyyy',StrToDate(MaskEdit1.text)) +''' AND '''+FormatDateTime('mm/DD/yyyy',StrToDate(MaskEdit2.text))+ ''' group by v.cdvendedora,e.nome';
   Query1.Active := True;
   Form110.RLReport1.Preview();
  end
  else
    ShowMessage('Favor escolher uma opção para o modelo do relatorio');
end;

end.

unit Unit100;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, ExtCtrls, DB, DBTables, Buttons, Mask;

type
  TForm88 = class(TForm)
    MaskEdit1: TMaskEdit;
    Label1: TLabel;
    SpeedButton1: TSpeedButton;
    Query1: TQuery;
    RadioGroup1: TRadioGroup;
    RadioButton1: TRadioButton;
    RadioButton2: TRadioButton;
    RadioButton3: TRadioButton;
    DataSource1: TDataSource;
    procedure SpeedButton1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form88: TForm88;

implementation

uses Unit2, Unit101;

{$R *.dfm}

procedure TForm88.SpeedButton1Click(Sender: TObject);
begin
  if(RadioButton1.Checked = True)then
  begin
  Query1.SQL.Text := 'select *from titulospagar2 where Status = F and dtlancamento >= '''+MaskEdit1.Text+''' ';
  end;
    if(RadioButton2.Checked = True)then
  begin
    Query1.SQL.Text := 'select *from titulospagar2 where Status = A and dtlancamento >= '''+MaskEdit1.Text+''' ';
  end;
    if(RadioButton3.Checked = True)then
  begin
    Query1.SQL.Text := 'select *from titulospagar2 where  dtlancamento >= '''+MaskEdit1.Text+''' ';
  end;
Query1.Active := True;
Form91.RLReport1.Preview();
end;

end.

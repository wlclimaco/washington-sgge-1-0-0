unit UConsForn;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, CustomCellSource, DataCellSource, GridPrint, ScrollView,
  CustomGridViewControl, CustomGridView, GridView, DB, IBCustomDataSet,
  IBQuery, StdCtrls, Buttons, ExtCtrls, Grids, DBGrids;

type
  TConsForn = class(TForm)
    Panel1: TPanel;
    GroupBox2: TGroupBox;
    RadioButton1: TRadioButton;
    RadioButton2: TRadioButton;
    RadioButton3: TRadioButton;
    RadioButton4: TRadioButton;
    RadioButton5: TRadioButton;
    RadioButton6: TRadioButton;
    GroupBox3: TGroupBox;
    SpeedButton1: TSpeedButton;
    Edit1: TEdit;
    Panel2: TPanel;
    IBQuery1: TIBQuery;
    DataSource1: TDataSource;
    RadioButton12: TRadioButton;
    DBGrid1: TDBGrid;
    procedure SpeedButton1Click(Sender: TObject);
    procedure DBGrid1DblClick(Sender: TObject);
    procedure DBGrid1DrawColumnCell(Sender: TObject; const Rect: TRect;
      DataCol: Integer; Column: TColumn; State: TGridDrawState);
  private
    { Private declarations }
  public
    function InsertCodRet(const ret :Integer):Boolean;
  end;

var
  ConsForn: TConsForn;
  CodRet: Integer;
  codcli: Integer;
implementation

uses Unit1, Unit10, Unit8, UConsEmpr;

{$R *.dfm}
function TConsForn.InsertCodRet(const ret :Integer):Boolean;
begin
 CodRet := ret;


end;
procedure TConsForn.SpeedButton1Click(Sender: TObject);
begin
IBQuery1.Active := fALSE;
IBQuery1.SQL.Clear;
IBQuery1.SQL.Add('select * from CLIENTES_FORNECEDOR WHERE cod_part >0');
IF(RadioButton1.Checked = tRUE)THEN
  IBQuery1.SQL.Add('AND NOME LIKE ''%'+eDIT1.Text+'%''');
IF(RadioButton2.Checked = tRUE)THEN
  IBQuery1.SQL.Add('AND CPF LIKE ''%'+eDIT1.Text+'%''');
IF(RadioButton3.Checked = tRUE)THEN
  IBQuery1.SQL.Add('AND IE LIKE ''%'+eDIT1.Text+'%''');
IF(RadioButton4.Checked = tRUE)THEN
  IBQuery1.SQL.Add('AND TPPESSOA = ''%'+eDIT1.Text+'%''');
IF(RadioButton5.Checked = tRUE)THEN
  IBQuery1.SQL.Add('AND REPRESENTANTE = '+eDIT1.Text);
IF(RadioButton6.Checked = tRUE)THEN
  IBQuery1.SQL.Add('AND RAMO_ATIVIDADE  = '+eDIT1.Text);
IF(RadioButton12.Checked = tRUE)THEN
  IBQuery1.SQL.Add(' ');
//  sHOWmeSSAGE(IBQuery1.SQL.TEXT);
IBQuery1.Active := tRUE;

end;

procedure TConsForn.DBGrid1DblClick(Sender: TObject);
begin
  if (CodRet = 1)then
    Form10.ActResultEdit1.Text := IntToStr(dbGrid1.Fields[0].AsInteger);
  if (CodRet = 2)then
    Form7.ActResultEdit1.Text := IntToStr(dbGrid1.Fields[0].AsInteger);
 ConsForn.Close();
end;

procedure TConsForn.DBGrid1DrawColumnCell(Sender: TObject;
  const Rect: TRect; DataCol: Integer; Column: TColumn;
  State: TGridDrawState);
begin
  if State = [] then
  begin
    if IBQuery1.RecNo mod 2 = 1 then
      DBGrid1.Canvas.Brush.Color := clAqua
    else
      DBGrid1.Canvas.Brush.Color := clWhite;
  end;
  DBGrid1.DefaultDrawColumnCell(Rect, DataCol, Column, State);
end;

end.


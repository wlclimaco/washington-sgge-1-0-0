unit UConsCep;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DB, IBCustomDataSet, IBQuery, Grids, DBGrids, StdCtrls, Buttons,
  ExtCtrls;

type
  TFRMCONSCEP = class(TForm)
    Panel1: TPanel;
    GroupBox1: TGroupBox;
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
    DBGrid1: TDBGrid;
    IBQuery1: TIBQuery;
    DataSource1: TDataSource;
    RadioButton7: TRadioButton;
    RadioButton8: TRadioButton;
    RadioButton9: TRadioButton;
    RadioButton10: TRadioButton;
    RadioButton11: TRadioButton;
    rb1: TRadioButton;
    procedure SpeedButton1Click(Sender: TObject);
    procedure DBGrid1DblClick(Sender: TObject);
    procedure DBGrid1DrawColumnCell(Sender: TObject; const Rect: TRect;
      DataCol: Integer; Column: TColumn; State: TGridDrawState);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  FRMCONSCEP: TFRMCONSCEP;

implementation

uses Unit10;

{$R *.dfm}

procedure TFRMCONSCEP.SpeedButton1Click(Sender: TObject);
begin
IBQuery1.Active := fALSE;
IBQuery1.SQL.Clear;
IBQuery1.SQL.Add('SELECT *FROM CEP WHERE NOCEP >0');
IF(RadioButton1.Checked = tRUE)THEN
  IBQuery1.SQL.Add('AND TIPOLOGRADOURO LIKE ''%'+eDIT1.Text+'%''');
IF(RadioButton2.Checked = tRUE)THEN
  IBQuery1.SQL.Add('AND DSLOGRADOURO LIKE ''%'+eDIT1.Text+'%''');
IF(RadioButton3.Checked = tRUE)THEN
  IBQuery1.SQL.Add('AND DSBAIRRO LIKE ''%'+eDIT1.Text+'%''');
IF(RadioButton4.Checked = tRUE)THEN
  IBQuery1.SQL.Add('AND DSCIDADE LIKE ''%'+eDIT1.Text+'%''');
IF(RadioButton5.Checked = tRUE)THEN
  IBQuery1.SQL.Add('AND DSESTADO LIKE ''%'+eDIT1.Text+'%''');
IF(RadioButton6.Checked = tRUE)THEN
  IBQuery1.SQL.Add('AND NOCEP  = '+eDIT1.Text);
IF(RadioButton7.Checked = tRUE)THEN
  IBQuery1.SQL.Add('ORDER BY TIPOLOGRADOURO');
IF(RadioButton8.Checked = tRUE)THEN
  IBQuery1.SQL.Add('ORDER BY DSLOGRADOURO');
IF(RadioButton9.Checked = tRUE)THEN
  IBQuery1.SQL.Add('ORDER BY DSBAIRRO');
IF(RadioButton10.Checked = tRUE)THEN
  IBQuery1.SQL.Add('ORDER BY DSCIDADE');
IF(RadioButton11.Checked = tRUE)THEN
  IBQuery1.SQL.Add('ORDER BY DSESTADO');
IF(rb1.Checked = tRUE)THEN
  IBQuery1.SQL.Add('ORDER BY NOCEP');
  sHOWmeSSAGE(IBQuery1.SQL.TEXT);
IBQuery1.Active := tRUE;
end;

procedure TFRMCONSCEP.DBGrid1DblClick(Sender: TObject);
begin
FORM10.TXTCEP.Text := IntToStr(dbGrid1.Fields[0].AsInteger);
FRMCONSCEP.Close();
end;

procedure TFRMCONSCEP.DBGrid1DrawColumnCell(Sender: TObject;
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

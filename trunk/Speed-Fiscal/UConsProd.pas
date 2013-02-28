unit UConsProd;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DB, IBCustomDataSet, IBQuery, Grids, DBGrids, Buttons, StdCtrls,
  ExtCtrls;

type
  TForm15 = class(TForm)
    Panel1: TPanel;
    GroupBox1: TGroupBox;
    GroupBox2: TGroupBox;
    GroupBox3: TGroupBox;
    Edit1: TEdit;
    RadioButton1: TRadioButton;
    RadioButton2: TRadioButton;
    RadioButton3: TRadioButton;
    RadioButton4: TRadioButton;
    RadioButton5: TRadioButton;
    CheckBox1: TCheckBox;
    CheckBox2: TCheckBox;
    CheckBox3: TCheckBox;
    CheckBox4: TCheckBox;
    CheckBox5: TCheckBox;
    SpeedButton1: TSpeedButton;
    Panel2: TPanel;
    DBGrid1: TDBGrid;
    IBQuery1: TIBQuery;
    DataSource1: TDataSource;
    RadioButton6: TRadioButton;
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
  Form15: TForm15;
  CodRet: Integer;
implementation

uses UFormCadProServ, Unit7, Unit8;

{$R *.dfm}
function TForm15.InsertCodRet(const ret :Integer):Boolean;
begin
 CodRet := ret;
end;

procedure TForm15.SpeedButton1Click(Sender: TObject);
begin
IBQuery1.Active := FALSE;
IBQuery1.SQL.Clear;
IBQuery1.SQL.Text := 'SELECT *FROM Prod_Serv where Cod_produto > 0  ';
if (RadioButton1.Checked = True) then
   IBQuery1.SQL.Add('and Cod_produto = '+Edit1.Text);
if (RadioButton2.Checked = True) then
   IBQuery1.SQL.Add('and REF like ''%'+Edit1.Text+'%''');
if (RadioButton3.Checked = True) then
   IBQuery1.SQL.Add('and descr_produto like ''%'+Edit1.Text+'%''');
if (RadioButton4.Checked = True) then
   IBQuery1.SQL.Add('and derivacao like ''%'+Edit1.Text+'%''');
if (RadioButton5.Checked = True) then
   IBQuery1.SQL.Add('and derivacao NCM ''%'+Edit1.Text+'%''');
if (RadioButton6.Checked = True) then
   IBQuery1.SQL.Add('and STATUS = ''A''');
   IBQuery1.SQL.Add('Order by  ') ;

 if (CheckBox1.Checked = true)then
   IBQuery1.SQL.Add('Cod_produto ') ;
  if (CheckBox2.Checked = true)then
     IBQuery1.SQL.Add('Ref ') ;
 if (CheckBox3.Checked = true)then
    IBQuery1.SQL.Add('descr_produto ') ;
 if (CheckBox4.Checked = true)then
     IBQuery1.SQL.Add('derivacao ') ;
 if (CheckBox5.Checked = true)then
     IBQuery1.SQL.Add('NCM ') ;

  IBQuery1.Active := true;


end;

procedure TForm15.DBGrid1DblClick(Sender: TObject);
begin
if (CodRet = 1)then
   FRMCADPROD.ActResultEdit1.Text := intToStr(dbGrid1.Fields[0].AsInteger);
if (CodRet = 2)then
   Form7.TxtCodPro.text := intToStr(dbGrid1.Fields[0].AsInteger);
   Form15.Close;
end;

procedure TForm15.DBGrid1DrawColumnCell(Sender: TObject; const Rect: TRect;
  DataCol: Integer; Column: TColumn; State: TGridDrawState);
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

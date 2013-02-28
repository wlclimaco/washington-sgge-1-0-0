unit Unit9;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Grids, DBGrids, StdCtrls, Buttons, ExtCtrls, DB, DBTables;

type
  TCONSCLIENTES = class(TForm)
    DBGrid1: TDBGrid;
    Panel1: TPanel;
    Label1: TLabel;
    SpeedButton1: TSpeedButton;
    Edit1: TEdit;
    GroupBox1: TGroupBox;
    RadioButton1: TRadioButton;
    RadioButton2: TRadioButton;
    RadioButton3: TRadioButton;
    RadioButton4: TRadioButton;
    Panel2: TPanel;
    SpeedButton2: TSpeedButton;
    SpeedButton3: TSpeedButton;
    RadioButton5: TRadioButton;
    RadioButton6: TRadioButton;
    RadioButton7: TRadioButton;
    RadioButton8: TRadioButton;
    Query1: TQuery;
    DataSource1: TDataSource;
    procedure SpeedButton1Click(Sender: TObject);
    procedure SpeedButton3Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  CONSCLIENTES: TCONSCLIENTES;

implementation

uses Unit2, Unit63;

{$R *.dfm}

procedure TCONSCLIENTES.SpeedButton1Click(Sender: TObject);
begin
  if(RadioButton1.Checked = true)then
   BEGIN
    query1.Active := FALSE;
    query1.sql.Text := 'select *from CLIENTE where CODCLI = '+Edit1.Text;
  // SHOWmESSAGE(query1.sql.Text);
    query1.Active := true;
   END;
   if(RadioButton2.Checked = true)then
   BEGIN
    query1.Active := FALSE;
    query1.sql.Text := 'select *from CLIENTE where CGC like ''%'+Edit1.Text+'%''';
  // SHOWmESSAGE(query1.sql.Text);
    query1.Active := true;
   END;
   if(RadioButton3.Checked = true)then
   BEGIN
    query1.Active := FALSE;
    query1.sql.Text := 'select *from CLIENTE where FANTASIA like ''%'+Edit1.Text+'%''';
  // SHOWmESSAGE(query1.sql.Text);
    query1.Active := true;
   END;
   if(RadioButton4.Checked = true)then
   BEGIN
    query1.Active := FALSE;
    query1.sql.Text := 'select *from CLIENTE where razao like ''%'+Edit1.Text+'%''';  // SHOWmESSAGE(query1.sql.Text);
    query1.Active := true;
   END;
   if(RadioButton5.Checked = true)then
   BEGIN
    query1.Active := FALSE;
    query1.sql.Text := 'select *from CLIENTE where endereco like ''%'+Edit1.Text+'%''';
  // SHOWmESSAGE(query1.sql.Text);
    query1.Active := true;
   END;
   if(RadioButton6.Checked = true)then
   BEGIN
    query1.Active := FALSE;
    query1.sql.Text := 'select *from CLIENTE where municipio like ''%'+Edit1.Text+'%''';
  // SHOWmESSAGE(query1.sql.Text);
    query1.Active := true;
   END;
   if(RadioButton7.Checked = true)then
   BEGIN
    query1.Active := FALSE;
    query1.sql.Text := 'select *from CLIENTE where uf like ''%'+Edit1.Text+'%''';
  // SHOWmESSAGE(query1.sql.Text);
    query1.Active := true;
   END;
      if(RadioButton8.Checked = true)then
   BEGIN
    query1.Active := FALSE;
    query1.sql.Text := 'select *from CLIENTE where incr like ''%'+Edit1.Text+'%''';
  // SHOWmESSAGE(query1.sql.Text);
    query1.Active := true;
   END;
end;

procedure TCONSCLIENTES.SpeedButton3Click(Sender: TObject);
begin
form63.RLReport1.Preview();
end;

end.

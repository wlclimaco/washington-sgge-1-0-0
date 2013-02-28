unit Unit7;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Grids, DBGrids, StdCtrls, Buttons, ExtCtrls, DB, DBTables;

type
  TCONSCAP = class(TForm)
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
    Query1: TQuery;
    DataSource1: TDataSource;
    RadioButton5: TRadioButton;
    procedure SpeedButton1Click(Sender: TObject);
    procedure SpeedButton2Click(Sender: TObject);
    procedure SpeedButton3Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  CONSCAP: TCONSCAP;

implementation

uses Unit2, URelCap;

{$R *.dfm}

procedure TCONSCAP.SpeedButton1Click(Sender: TObject);
begin
   if(RadioButton1.Checked = true)then
   BEGIN
    Query1.Active := FALSE;
    Query1.sql.Text := 'select *from cap where codplc like ''%'+Edit1.Text+'%''';
  // SHOWmESSAGE(query1.sql.Text);
    Query1.Active := true;
   END;
   if(RadioButton2.Checked = true)then
   BEGIN
    Query1.Active := FALSE;
    Query1.sql.Text := 'select *from cap where descricao like ''%'+Edit1.Text+'%''';
  // SHOWmESSAGE(query1.sql.Text);
    Query1.Active := true;
   END;
   if(RadioButton3.Checked = true)then
   BEGIN
    Query1.Active := FALSE;
    Query1.sql.Text := 'select *from cap where codcli = '+Edit1.Text;
  // SHOWmESSAGE(query1.sql.Text);
    Query1.Active := true;
    end;
      if(RadioButton4.Checked = true)then
   BEGIN
    Query1.Active := FALSE;
    Query1.sql.Text := 'select *from cap where razao like ''%'+Edit1.Text+'%''';
  // SHOWmESSAGE(query1.sql.Text);
    Query1.Active := true;
   END;
    if(RadioButton5.Checked = true)then
   BEGIN
    Query1.Active := FALSE;
    Query1.sql.Text := 'select *from cap where DOCNUM = '+Edit1.Text;
  // SHOWmESSAGE(query1.sql.Text);
    Query1.Active := true;
    end;
end;

procedure TCONSCAP.SpeedButton2Click(Sender: TObject);
begin
 RelCap.RLReport1.Preview();
end;

procedure TCONSCAP.SpeedButton3Click(Sender: TObject);
begin
CLOSE();
end;

end.

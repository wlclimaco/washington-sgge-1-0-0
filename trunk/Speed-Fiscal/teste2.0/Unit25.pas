unit Unit25;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Grids, DBGrids, StdCtrls, Buttons, ExtCtrls, DB, DBTables;

type
  TCONSPRODUTOS = class(TForm)
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
    procedure SpeedButton1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  CONSPRODUTOS: TCONSPRODUTOS;

implementation

uses Unit2;

{$R *.dfm}

procedure TCONSPRODUTOS.SpeedButton1Click(Sender: TObject);
begin
  if(RadioButton1.Checked = true)then
   BEGIN
    query1.Active := FALSE;
    query1.sql.Text := 'select *from PRODUTOS where CODPRO = '+Edit1.Text;
    query1.Active := true;
   END
   ELSE
      if(RadioButton2.Checked = true)then
       BEGIN
        query1.Active := FALSE;
        query1.sql.Text := 'select *from PRODUTOS where REF LIKE ''%'+Edit1.Text+'%''' ;
        query1.Active := true;
       end
       ELSE
         if(RadioButton3.Checked = true)then
           BEGIN
            query1.Active := FALSE;
            query1.sql.Text := 'select *from PRODUTOS where PRODUTO LIKE ''%'+Edit1.Text+'%''';
            query1.Active := true;
           end
         ELSE
            if(RadioButton4.Checked = true)then
            BEGIN
             query1.Active := FALSE;
             query1.sql.Text := 'select *from PRODUTOS where CODIND = '+Edit1.Text;
             query1.Active := true;
            end
            ELSE
            BEGIN
             query1.Active := FALSE;
             query1.sql.Text := 'select *from PRODUTOS ';
             query1.Active := true;
             END;


end;

end.

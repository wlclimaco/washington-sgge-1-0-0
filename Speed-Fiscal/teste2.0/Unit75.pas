unit Unit75;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, Buttons, ExtCtrls, Grids, DBGrids;

type
  TForm75 = class(TForm)
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
    procedure SpeedButton1Click(Sender: TObject);
    procedure SpeedButton3Click(Sender: TObject);
    procedure SpeedButton2Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form75: TForm75;

implementation

uses Unit2, Unit73, Unit74;

{$R *.dfm}

procedure TForm75.SpeedButton1Click(Sender: TObject);
begin

  form73.Query2.Active := FALSE;
  form73.Query2.SQL.Clear;
  form73.Query2.SQL.Add('select *from produtos ' );
  if(RadioButton1.Checked = true) then
      form73.Query2.SQL.Add(' where codPRO = '+ Edit1.Text )
  else
     if (RadioButton2.Checked = true) then
        form73.Query2.SQL.Add(' where  loja = '+ Edit1.Text )
     else
       if (RadioButton3.Checked = true) then
           form73.Query2.SQL.Add(' where  produto  like %'+Edit1.Text+'%' )
       else
         if(RadioButton4.Checked = true) then
            form73.Query2.SQL.Add(' where  codind  = '+Edit1.Text )
          else
         if(RadioButton5.Checked = true) then
           form73.Query2.SQL.Add(' where  REF  = '''+Edit1.Text+'''' ) ;
   form73.Query2.Active := True;
end;

procedure TForm75.SpeedButton3Click(Sender: TObject);
begin
form74.RLReport1.Preview();
end;

procedure TForm75.SpeedButton2Click(Sender: TObject);
begin
Close();
end;

end.

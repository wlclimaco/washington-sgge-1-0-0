unit Unit22;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Grids, DBGrids, StdCtrls, Buttons, ExtCtrls;

type
  TconsMOVCAIXA = class(TForm)
    DBGrid1: TDBGrid;
    Panel1: TPanel;
    Label1: TLabel;
    SpeedButton1: TSpeedButton;
    Edit1: TEdit;
    Panel2: TPanel;
    SpeedButton2: TSpeedButton;
    SpeedButton3: TSpeedButton;
    GroupBox1: TGroupBox;
    RadioButton1: TRadioButton;
    RadioButton2: TRadioButton;
    RadioButton4: TRadioButton;
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  consMOVCAIXA: TconsMOVCAIXA;

implementation

uses Unit2;

{$R *.dfm}

end.

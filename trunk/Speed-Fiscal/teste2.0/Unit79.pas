unit Unit79;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, Buttons, Mask, ExtCtrls;

type
  TForm79 = class(TForm)
    Panel1: TPanel;
    Panel2: TPanel;
    Panel3: TPanel;
    Label1: TLabel;
    GroupBox1: TGroupBox;
    RadioButton1: TRadioButton;
    RadioButton2: TRadioButton;
    MaskEdit1: TMaskEdit;
    MaskEdit2: TMaskEdit;
    Label2: TLabel;
    Label3: TLabel;
    BitBtn1: TBitBtn;
    procedure BitBtn1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form79: TForm79;

implementation

uses Unit2;

{$R *.dfm}

procedure TForm79.BitBtn1Click(Sender: TObject);
begin
  DataModule2.Query9.Active := FALSE;
  IF (RadioButton1.Checked) THEN
      DataModule2.Query9.SQL.Text := 'SELECT *FROM TITULOSPAGAR2 WHERE DTVENCIMENTO BETWEEN '''+MaskEdit1.Text+'''  AND '''+MaskEdit2.Text+''' AND TPSITUACAO = ''P''';
  IF (RadioButton2.Checked) THEN
      DataModule2.Query9.SQL.Text := 'SELECT *FROM TITULOSPAGAR2 WHERE DTVENCIMENTO BETWEEN '''+MaskEdit1.Text+'''  AND '''+MaskEdit2.Text+''' AND TPSITUACAO = ''A''';
  DataModule2.Query9.Active := True;
end;

end.

unit BrvDialogoImpressaoForm;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, Buttons, Printers, Spin, ExtCtrls, BrvBitBtn;

type
  TFrmDlgImpres = class(TForm)
    Panel1: TPanel;
    BotImprim: TBrvBitBtn;
    SbtCancel: TBrvBitBtn;
    GbxImpres: TGroupBox;
    Label1: TLabel;
    CbxImpres: TComboBox;
    GbxCoipas: TGroupBox;
    Label2: TLabel;
    SdtNrCopias: TSpinEdit;
    GbxProprie: TGroupBox;
    CbxConden: TCheckBox;
    procedure FormCreate(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  FrmDlgImpres: TFrmDlgImpres;

implementation

{$R *.DFM}

procedure TFrmDlgImpres.FormCreate(Sender: TObject);
begin
      CbxImpres.items.clear;
      CbxImpres.items          :=  Printer.Printers;
      CbxImpres.ItemIndex      :=  Printer.PrinterIndex;
end;

end.

unit Unit74;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, RLReport, RLPreviewForm, RLSaveDialog, RLPrintDialog;

type
  TForm74 = class(TForm)
    RLReport1: TRLReport;
    RLDetailGrid1: TRLDetailGrid;
    RLDBText1: TRLDBText;
    rldbtxtCODPRO: TRLDBText;
    RLDBText3: TRLDBText;
    RLDBText2: TRLDBText;
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form74: TForm74;

implementation

uses Unit73;

{$R *.dfm}

end.

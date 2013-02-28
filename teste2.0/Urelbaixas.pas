unit Urelbaixas;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, RLReport, RLFilters, RLXLSFilter, RLPrintDialog, RLPreviewForm;

type
  TRelBaixas = class(TForm)
    RLReport1: TRLReport;
    RLBand1: TRLBand;
  //  RLPrintDialogSetup1: TRLPrintDialogSetup;
    RLXLSFilter1: TRLXLSFilter;
    RLBand2: TRLBand;
    RLBand3: TRLBand;
    RLLabel2: TRLLabel;
    RLLabel3: TRLLabel;
    RLLabel1: TRLLabel;
    RLLabel4: TRLLabel;
    RLLabel5: TRLLabel;
    RLLabel6: TRLLabel;
    RLLabel7: TRLLabel;
    RLDBText1: TRLDBText;
    RLDBText2: TRLDBText;
    RLDBText3: TRLDBText;
    RLDBText4: TRLDBText;
    RLDBText5: TRLDBText;
    RLDBText6: TRLDBText;
    RLBand4: TRLBand;
    RLSystemInfo1: TRLSystemInfo;
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  RelBaixas: TRelBaixas;

implementation

uses Unit4;

{$R *.dfm}

end.

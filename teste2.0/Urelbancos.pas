unit Urelbancos;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, RLReport;

type
  TRelBancos = class(TForm)
    RLReport1: TRLReport;
    RLBand1: TRLBand;
    RLLabel2: TRLLabel;
    RLLabel3: TRLLabel;
    RLLabel4: TRLLabel;
    RLLabel5: TRLLabel;
    RLBand2: TRLBand;
    RLLabel1: TRLLabel;
    RLBand3: TRLBand;
    RLDBText1: TRLDBText;
    RLDBText2: TRLDBText;
    RLDBText3: TRLDBText;
    RLDBText4: TRLDBText;
    RLDBText5: TRLDBText;
    RLDBText6: TRLDBText;
    RLBand4: TRLBand;
    RLSystemInfo1: TRLSystemInfo;
    RLLabel6: TRLLabel;
    RLLabel7: TRLLabel;
    RLLabel8: TRLLabel;
    RLLabel9: TRLLabel;
    RLDBText7: TRLDBText;
    RLDBText8: TRLDBText;
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  RelBancos: TRelBancos;

implementation

uses Unit2;

{$R *.dfm}

end.

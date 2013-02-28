unit Unit112;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, RLReport, RLParser;

type
  TForm112 = class(TForm)
    RLReport1: TRLReport;
    RLBand1: TRLBand;
    RLBand3: TRLBand;
    RLBand4: TRLBand;
    RLBand5: TRLBand;
    RLDBText1: TRLDBText;
    RLDBText2: TRLDBText;
    RLDBText3: TRLDBText;
    RLDBText4: TRLDBText;
    RLLabel1: TRLLabel;
    RLLabel2: TRLLabel;
    RLLabel3: TRLLabel;
    RLLabel4: TRLLabel;
    RLExpressionParser1: TRLExpressionParser;
    RLDBResult1: TRLDBResult;
    RLDBResult2: TRLDBResult;
    RLDBResult3: TRLDBResult;
    RLLabel5: TRLLabel;
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form112: TForm112;

implementation

uses Unit111;

{$R *.dfm}

end.

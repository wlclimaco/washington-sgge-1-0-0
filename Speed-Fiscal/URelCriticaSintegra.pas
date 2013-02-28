unit URelCriticaSintegra;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, RLReport;

type
  TRelCriticaSintegra = class(TForm)
    RLReport1: TRLReport;
    RLBand1: TRLBand;
    RLBand2: TRLBand;
    RLBand3: TRLBand;
    RLBand4: TRLBand;
    RLDBText1: TRLDBText;
    RLDBText2: TRLDBText;
    RLDBText3: TRLDBText;
    RLSystemInfo1: TRLSystemInfo;
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  RelCriticaSintegra: TRelCriticaSintegra;

implementation

uses Unit82;

{$R *.dfm}

end.

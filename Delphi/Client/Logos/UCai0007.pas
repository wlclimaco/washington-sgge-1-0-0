unit UCai0007;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UCai0000, StdCtrls, Buttons, BrvBitBtn, ExtCtrls, BrvSpeedButton, BrvDbNavCop,
  OleCtrls, SHDocVw;

type
  TCai0007 = class(TCai0000)
    NavBarra: TBrvDbNavCop;
    BrvSpeedButton1: TBrvSpeedButton;
    RchDsHelp: TWebBrowser;
    procedure BrvSpeedButton1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
    procedure MostrarPagina(pDsHelp : String);
  end;

var
  Cai0007: TCai0007;

implementation

{$R *.dfm}

{ TCai0007 }

procedure TCai0007.BrvSpeedButton1Click(Sender: TObject);
begin
      Close;
end;

procedure TCai0007.MostrarPagina(pDsHelp: String);
var lDsPatTem : array[0..255] of char;
    lDsPatHtm : String;

    pDsHtml   : TStrings;
begin
      try
          pDsHtml := TStringList.Create;
          pDsHtml.Text := pDsHelp;

          GetTempPath(255, lDsPatTem);
          lDsPatHtm := lDsPatTem + 'help.htm';
          pDsHtml.SaveToFile(lDsPatHtm);
          RchDsHelp.Navigate(lDsPatHtm);
      finally
          FreeAndNil(pDsHtml);
      end;
end;

end.

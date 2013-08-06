unit USRA0000;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UCad0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop;

type
  TSRA0000 = class(TCad0000)
    procedure FormCreate(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  SRA0000: TSRA0000;

implementation

{$R *.dfm}

procedure TSRA0000.FormCreate(Sender: TObject);
begin
      // método retirado para que não cause exceção

end;

end.

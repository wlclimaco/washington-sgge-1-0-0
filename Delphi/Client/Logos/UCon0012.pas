unit UCon0012;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, StdCtrls, BrvBitBtn, BrvRetCon,
  BrvEditNum;

type
  TCon0012 = class(TMov0000)
    Panel1: TPanel;
    PnlEmpres: TPanel;
    LblEmpres: TLabel;
    EdtCdEmpres: TBrvEditNum;
    LblDsEmpres: TBrvRetCon;
    BtnProsse: TBrvBitBtn;
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Con0012: TCon0012;

implementation

{$R *.dfm}

end.

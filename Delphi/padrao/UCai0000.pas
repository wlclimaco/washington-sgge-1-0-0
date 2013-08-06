unit UCai0000;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, Buttons, BrvBitBtn, ExtCtrls;

type
  TCai0000 = class(TForm)
    Panel1: TPanel;
    Panel2: TPanel;
    BbtOk: TBrvBitBtn;
    BbtCancel: TBrvBitBtn;
  private
    { Private declarations }
  public
    gVrParam  : String;
    { Public declarations }
  end;

var
  Cai0000: TCai0000;

implementation

{$R *.dfm}

end.

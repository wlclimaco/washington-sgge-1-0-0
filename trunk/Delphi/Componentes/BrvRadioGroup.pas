unit BrvRadioGroup;

interface

uses
  SysUtils, Classes, Controls, StdCtrls, ExtCtrls;

type
  TBrvRadioGroup = class(TRadioGroup)
  private
    { Private declarations }
  protected
    { Protected declarations }
  public
    { Public declarations }
  published
    { Published declarations }
    property OnMouseDown;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Bravo Standard', [TBrvRadioGroup]);
end;

end.

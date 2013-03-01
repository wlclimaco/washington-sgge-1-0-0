unit ActSpinNumber;

interface

uses
  SysUtils, Classes, Controls, StdCtrls, ActSpin;

type
  TActSpinNumber = class(TActSpinEdit)
  private
    { Private declarations }
  protected
    { Protected declarations }
  public
    { Public declarations }
  published
    { Published declarations }
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Active Standard', [TActSpinNumber]);
end;

end.

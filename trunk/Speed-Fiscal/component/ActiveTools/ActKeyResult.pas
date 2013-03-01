unit ActKeyResult;

interface

uses
  Windows, Messages, SysUtils, Classes;

type
  TActKeyResult = class(TComponent)
  private
    FValue: string;
    procedure SetValue(const Value: string);
    { Private declarations }
  protected
    { Protected declarations }
  public
    { Public declarations }
  published
    { Published declarations }
    property Value: string read FValue write SetValue;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Active Controls', [TActKeyResult]);
end;

{ TActKeyResult }

procedure TActKeyResult.SetValue(const Value: String);
begin
  if Value <> FValue then
    FValue := Value;
end;

end.

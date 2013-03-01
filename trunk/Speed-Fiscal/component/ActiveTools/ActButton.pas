unit ActButton;

interface

uses
  Windows, Messages, SysUtils, Classes, Controls, StdCtrls, Buttons, ActLanguage;

type
  TActButton = class(TBitBtn)
  private
    FResourceID: Integer;
    FLanguage: TActLanguage;
    procedure SetLanguage(const Value: TActLanguage);
    procedure SetResourceID(const Value: Integer);
    { Private declarations }
  protected
    { Protected declarations }
  public
    { Public declarations }
    ResourceStr: String;
  published
    { Published declarations }
    property Language: TActLanguage read FLanguage write SetLanguage;
    property ResourceID: Integer read FResourceID write SetResourceID;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Active Standard', [TActButton]);
end;

{ TActButton }

procedure TActButton.SetLanguage(const Value: TActLanguage);
var
  Buff: array[0..255] of char;
begin
  FLanguage := Value;
  if not (csDesigning in ComponentState) then
  begin
    if FLanguage <> nil then
    begin
      LoadString(FLanguage.HandleLib, FResourceID, @Buff, SizeOf(Buff));
      ResourceStr := Buff;
      if ResourceStr <> '' then
        Caption := ResourceStr;
    end;
  end;
end;

procedure TActButton.SetResourceID(const Value: Integer);
begin
  if Value <> FResourceID then
    FResourceID := Value;
end;

end.

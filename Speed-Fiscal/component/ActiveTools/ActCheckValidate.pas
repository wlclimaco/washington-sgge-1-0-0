unit ActCheckValidate;

interface

uses
  Windows, Messages, SysUtils, Classes, ActCustomEdit, ActDateEdit, ActTimeEdit,
  ActCheckBox, ActCurrencyEdit;

type
  TActCheckValidate = class(TComponent)
  private
    { Private declarations }
    FOnValidate: TGetStrProc;
    function StrLeft(const S: String; Const Sep: Char): String;
  protected
    { Protected declarations }
  public
    { Public declarations }
    function Validate: Boolean;
    constructor Create(AOwner: TComponent); override;
  published
    { Published declarations }
    property OnValidate: TGetStrProc read FOnValidate write FOnValidate;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Active Controls', [TActCheckValidate]);
end;

{ TActCheckValidate }

constructor TActCheckValidate.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
end;

function TActCheckValidate.StrLeft(const S: String;
  const Sep: Char): String;
var
  I: Integer;
begin
  I := AnsiPos(Sep, S);
  if I = 0 then
  Result := S
  else
  Result := Copy(S, 1, I - 1);
end;

function TActCheckValidate.Validate: Boolean;
var
  I: Integer;
begin
  Result := True;
  for I := 0 to Owner.ComponentCount - 1 do
  begin
    if (Owner.Components[I] is TActDateEdit) then
    begin
      if (Owner.Components[I] as TActDateEdit).NotNull then
      begin
        if ((Owner.Components[I] as TActDateEdit).DateText = '00/00/0000') then
        begin
          FOnValidate(StrLeft((Owner.Components[I] as TActDateEdit).EditLabel.Caption, ':'));
          if (Owner.Components[I] as TActDateEdit).CanFocus then
            (Owner.Components[I] as TActDateEdit).SetFocus;
          Result := False;
          Exit;
        end;
      end;
    end;
    if (Owner.Components[I] is TActCurrencyEdit) then
    begin
      if (Owner.Components[I] as TActCurrencyEdit).NotNull then
      begin
        if ((Owner.Components[I] as TActCurrencyEdit).Value = 0.00) then
        begin
          FOnValidate(StrLeft((Owner.Components[I] as TActCurrencyEdit).EditLabel.Caption, ':'));
          if (Owner.Components[I] as TActCurrencyEdit).CanFocus then
            (Owner.Components[I] as TActCurrencyEdit).SetFocus;
          Result := False;
          Exit;
        end;
      end;
    end;
    if (Owner.Components[I] is TActCustomEdit) then
    begin
      if (Owner.Components[I] as TActCustomEdit).NotNull then
      begin
        if (Trim((Owner.Components[I] as TActCustomEdit).Text) = '') then
        begin
          FOnValidate(StrLeft((Owner.Components[I] as TActCustomEdit).EditLabel.Caption, ':'));
          if (Owner.Components[I] as TActCustomEdit).CanFocus then
            (Owner.Components[I] as TActCustomEdit).SetFocus;
          Result := False;
          Exit;
        end;
      end;
    end;
  end;
end;

end.

unit ActCheckBox;

interface

uses
  Windows, Messages, SysUtils, Classes, Controls, StdCtrls, Forms;

type
  TActCheckBox = class(TCheckBox)
  private
    FNotNull: Boolean;
    FEnterByTab: Boolean;
    procedure SetNotNull(const Value: Boolean);
    procedure SetEnterByTab(const Value: Boolean);
    { Private declarations }
  protected
    { Protected declarations }
    procedure KeyPress(var Key: Char); override;
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
  published
    { Published declarations }
    property EnterByTab: Boolean read FEnterByTab write SetEnterByTab default True;
    property NotNull: Boolean read FNotNull write SetNotNull default False;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Active Standard', [TActCheckBox]);
end;

{ TActCheckBox }

constructor TActCheckBox.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
  FEnterByTab := True;
end;

procedure TActCheckBox.KeyPress(var Key: Char);
var
  FKeyTemp: TCustomForm;
begin
  if Key = #13 then
  begin
    if FEnterByTab then
    begin
      FKeyTemp := GetParentForm(Self);
      SendMessage(FKeyTemp.Handle, WM_NEXTDLGCTL, 0, 0);
      Key := #0;
    end;
  end;
  if Key <> #0 then
    inherited KeyPress(Key);
end;

procedure TActCheckBox.SetEnterByTab(const Value: Boolean);
begin
  if Value <> FEnterByTab then
    FEnterByTab := Value;
end;

procedure TActCheckBox.SetNotNull(const Value: Boolean);
begin
  if Value <> FNotNull then
    FNotNull := Value;
end;

end.

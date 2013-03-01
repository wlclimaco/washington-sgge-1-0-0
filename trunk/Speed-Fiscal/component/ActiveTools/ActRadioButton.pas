unit ActRadioButton;

interface

uses
  Windows, Messages, SysUtils, Classes, Controls, StdCtrls, Forms;

type
  TActRadioButton = class(TRadioButton)
  private
    FEnterByTab: Boolean;
    procedure SetEnterByTab(const Value: Boolean);
    { Private declarations }
  protected
    { Protected declarations }
    procedure KeyPress(var Key: Char); override;
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
  published
    { Published declarations }
    property EnterByTab: Boolean read FEnterByTab write SetEnterByTab default True;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Active Standard', [TActRadioButton]);
end;

{ TActRadioButton }

constructor TActRadioButton.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
  FEnterByTab := True;
end;

destructor TActRadioButton.Destroy;
begin
  inherited Destroy;
end;

procedure TActRadioButton.KeyPress(var Key: Char);
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

procedure TActRadioButton.SetEnterByTab(const Value: Boolean);
begin
  if Value <> FEnterByTab then
    FEnterByTab := Value;
end;

end.

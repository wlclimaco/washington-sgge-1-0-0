unit ActPanel;

interface

uses
  Windows, Messages, SysUtils, Classes, Controls, StdCtrls, ExtCtrls, Graphics;

type
  TActPanel = class(TPanel)
  private
    FCaption2: String;
    FColorFont: TColor;
    FBorder: Boolean;
    FColorBorder: TColor;
    FEnabledAll: Boolean;
    { Private declarations }
    procedure SetColorFont(const Value: TColor);
    procedure SetBorder(const Value: Boolean);
    procedure SetColorBorder(const Value: TColor);
    procedure SetEnabledAll(const Value: Boolean);
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    procedure Paint; override;
  published
    { Published declarations }
    property Caption2: String read FCaption2 write FCaption2;
    property ColorFont: TColor read FColorFont write SetColorFont;
    property ColorBorder: TColor read FColorBorder write SetColorBorder;
    property Border: Boolean read FBorder write SetBorder;
    property EnabledAll: Boolean read FEnabledAll write SetEnabledAll default True;
  end;

{$R ActPanel.res}

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Active Controls', [TActPanel]);
end;

{ TActPanel }


constructor TActPanel.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
  BevelInner := bvNone;
  BevelOuter := bvNone;
  Color := $00AB663D;
  FColorFont := $00E5D0C4;
  FColorBorder := $00E5D0C4;
  Caption := '';
  FEnabledAll := True;
end;

procedure TActPanel.Paint;
begin
  inherited;
  if FBorder then
  begin
    Canvas.Pen.Color := FColorBorder;
    Canvas.Rectangle(ClientRect);
  end;
end;

procedure TActPanel.SetBorder(const Value: Boolean);
begin
  if Value <> FBorder then
  begin
    FBorder := Value;
    Repaint;
  end;
end;

procedure TActPanel.SetColorBorder(const Value: TColor);
begin
  if Value <> FColorBorder then
  begin
    FColorBorder := Value;
    Repaint;
  end;
end;

procedure TActPanel.SetColorFont(const Value: TColor);
begin
  if Value <> FColorFont then
  begin
    FColorFont := Value;
    Font.Color := FColorFont;
    Repaint;
  end;
end;

procedure TActPanel.SetEnabledAll(const Value: Boolean);
var
  I: Integer;
begin
  if FEnabledAll <> Value then
  begin
    FEnabledAll := Value;
    for I := 0 to Owner.ComponentCount - 1 do
      if TWinControl(Owner.Components[I]).Parent = Self then
        TCustomEdit(Owner.Components[I]).Enabled := FEnabledAll;
  end;
end;

end.

unit ActLabel;

interface

uses
  Windows, Messages, SysUtils, Classes, Controls, StdCtrls, Graphics, Forms;

type
  TOnMouseOverEvent = procedure(Sender: TObject) of object;
  TOnMouseOutEvent = procedure(Sender: TObject) of object;

type
  TActLabel = class(TLabel)
  private
    FOnMouseOut: TOnMouseOutEvent;
    FOnMouseOver: TOnMouseOverEvent;
    FFontColorOut: TColor;
    FFontColorOver: TColor;
    FLink: Boolean;
    procedure CMMouseEnter(var AMsg: TMessage); message CM_MOUSEENTER;
    procedure CMMouseLeave(var AMsg: TMessage); message CM_MOUSELEAVE;
    procedure SetFontColorOver(const Value: TColor);
    procedure SetFontColorOut(const Value: TColor);
    procedure SetLink(const Value: Boolean);
    { Private declarations }
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
  published
    { Published declarations }
    property OnMouseOver: TOnMouseOverEvent read FOnMouseOver write FOnMouseOver;
    property OnMouseOut: TOnMouseOutEvent read FOnMouseOut write FOnMouseOut;
    property FontColorOver: TColor read FFontColorOver write SetFontColorOver;
    property FontColorOut: TColor read FFontColorOut write SetFontColorOut;
    property Link: Boolean read FLink write SetLink;
  end;

procedure Register;

implementation

uses Variants;

{$J+}
{$R ActLabel.res}

procedure Register;
begin
  RegisterComponents('Active Standard', [TActLabel]);
end;

{ TActLabel }

procedure TActLabel.CMMouseEnter(var AMsg: TMessage);
begin
  if not (csDesigning in ComponentState) then
  begin
    if FLink and Enabled then
    begin
      Cursor := crHandPoint;
      Font.Color := FFontColorOver;
      Font.Style := Font.Style + [fsUnderline];
    end;
  end;
  if Assigned(FOnMouseOver) then
    FOnMouseOver(Self);
end;

procedure TActLabel.CMMouseLeave(var AMsg: TMessage);
begin
  if not (csDesigning in ComponentState) then
  begin
    if FLink and Enabled then
    begin
      Cursor := crDefault;
      Font.Color := FFontColorOut;
      Font.Style := Font.Style - [fsUnderline];
    end;
  end;
  if Assigned(FOnMouseOut) then
    FOnMouseOut(Self);
end;

constructor TActLabel.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
  FLink := False;
  FFontColorOver := clRed;
  FFontColorOut := clBlue;
  Screen.Cursors[crHandPoint] := LoadCursor(HInstance, 'ACTHAND');
end;

procedure TActLabel.SetFontColorOver(const Value: TColor);
begin
  if Value <> FFontColorOver then
    FFontColorOver := Value;
  if FLink then
    Font.Color := Value;
end;

procedure TActLabel.SetFontColorOut(const Value: TColor);
begin
  if Value <> FFontColorOut then
    FFontColorOut := Value;
  if FLink then
    Font.Color := Value;
end;

procedure TActLabel.SetLink(const Value: Boolean);
begin
  if Value <> FLink then
  begin
    FLink := Value;
    if not FLink then
      Font.Color := clBlack;
  end;
  if FLink then
  begin
    Font.Color := FFontColorOut;
    Font.Style := Font.Style - [fsUnderline];
  end;
end;

end.

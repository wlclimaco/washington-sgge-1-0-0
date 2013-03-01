unit ActMemo;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, ExtCtrls, ActLanguage;

type
  TActMemo = class(TCustomMemo)
  private
    { Private declarations }
    FMemoLabel: TBoundLabel;
    FEnter: Boolean;
    FLabelSpacing: Integer;
    FLabelPosition: TLabelPosition;
    FParentColor: Boolean;
    FValidate: Boolean;
    FVersion: String;
    FAlignment: TAlignment;
    FColorDisabled: TColor;
    FColorOnNotFocus: TColor;
    FColorEnabled: TColor;
    FColorOnFocus: TColor;
    FOnExit: TNotifyEvent;
    FOnEnter: TNotifyEvent;
    FResourceID: Integer;
    FLanguage: TActLanguage;
    procedure SetLabelPosition(const Value: TLabelPosition);
    procedure SetLabelSpacing(const Value: Integer);
    procedure SetAlignment(const Value: TAlignment);
    procedure SetColor(const Value: TColor);
    procedure SetColorDisabled(const Value: TColor);
    procedure SetColorOnFocus(const Value: TColor);
    procedure SetColorOnNotFocus(const Value: TColor);
    procedure SetParentColor(const Value: Boolean);
    procedure SetValidade(const Value: Boolean);
    procedure SetVersion(const Value: String);
    procedure SetLanguage(const Value: TActLanguage);
    procedure SetResourceID(const Value: Integer);
  protected
    { Protected declarations }
    procedure SetParent(AParent: TWinControl); override;
    procedure Notification(AComponent: TComponent; Operation: TOperation); override;
    procedure SetName(const Value: TComponentName); override;
    procedure CMVisiblechanged(var Message: TMessage);
      message CM_VISIBLECHANGED;
    procedure CMEnabledchanged(var Message: TMessage);
      message CM_ENABLEDCHANGED;
    procedure CMBidimodechanged(var Message: TMessage);
      message CM_BIDIMODECHANGED;
    procedure SetEnabled(Value: Boolean); override;
    procedure Loaded; override;
    procedure DoEnter; override;
    procedure DoExit; override;
  public
    { Public declarations }
    ResourceStr: String;
    constructor Create(AOwner: TComponent); override;
    procedure SetBounds(ALeft: Integer; ATop: Integer; AWidth: Integer; AHeight: Integer); override;
    procedure CreateParams(var Params: TCreateParams); override;
    procedure SetupInternalLabel;
  published
    { Published declarations }
    property MemoLabel: TBoundLabel read FMemoLabel;
    property LabelPosition: TLabelPosition read FLabelPosition write SetLabelPosition;
    property LabelSpacing: Integer read FLabelSpacing write SetLabelSpacing;
    property Alignment: TAlignment read FAlignment write SetAlignment;
    property AutoSelect;
    property Color: TColor read FColorEnabled write SetColor default clWindow;
    property ColorOnFocus: TColor read FColorOnFocus write SetColorOnFocus;
    property ColorOnNotFocus: TColor read FColorOnNotFocus write SetColorOnNotFocus;
    property ColorDisabled: TColor read FColorDisabled write SetColorDisabled default clBtnFace;
    property Enabled: Boolean read GetEnabled write SetEnabled default True;
    property OnEnter: TNotifyEvent read FOnEnter write FOnEnter;
    property OnExit: TNotifyEvent read FOnExit write FOnExit;
    property ParentColor: Boolean read FParentColor write SetParentColor default False;
    property Validate: Boolean read FValidate write SetValidade default False;
    property Version: String read FVersion write SetVersion stored False;
    property Language: TActLanguage read FLanguage write SetLanguage;
    property ResourceID: Integer read FResourceID write SetResourceID;

    property Align;
    property Anchors;
    property BevelEdges;
    property BevelInner;
    property BevelKind default bkNone;
    property BevelOuter;
    property BiDiMode;
    property BorderStyle;
    property Constraints;
    property Ctl3D;
    property DragCursor;
    property DragKind;
    property DragMode;
    property Font;
    property HideSelection;
    property ImeMode;
    property ImeName;
    property Lines;
    property MaxLength;
    property OEMConvert;
    property ParentBiDiMode;
    property ParentCtl3D;
    property ParentFont;
    property ParentShowHint;
    property PopupMenu;
    property ReadOnly;
    property ScrollBars;
    property ShowHint;
    property TabOrder;
    property TabStop;
    property Visible;
    property WantReturns;
    property WantTabs;
    property WordWrap;
    property OnChange;
    property OnClick;
    property OnContextPopup;
    property OnDblClick;
    property OnDragDrop;
    property OnDragOver;
    property OnEndDock;
    property OnEndDrag;
    property OnKeyDown;
    property OnKeyPress;
    property OnKeyUp;
    property OnMouseDown;
    property OnMouseMove;
    property OnMouseUp;
    property OnStartDock;
    property OnStartDrag;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Active Standard', [TActMemo]);
end;

{ TActMemo }

procedure TActMemo.CMBidimodechanged(var Message: TMessage);
begin
  inherited;
  FMemoLabel.BiDiMode := BiDiMode;
end;

procedure TActMemo.CMEnabledchanged(var Message: TMessage);
begin
  inherited;
  FMemoLabel.Enabled := Enabled;
end;

procedure TActMemo.CMVisiblechanged(var Message: TMessage);
begin
  inherited;
  FMemoLabel.Visible := Visible;
end;

constructor TActMemo.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
  FAlignment := taLeftJustify;
  FColorDisabled := clBtnFace;
  FColorEnabled := clWindow;
  FColorOnFocus := $00F8E4D8;
  FColorOnNotFocus := clWindow;
  FEnter := False;
  FVersion := '1.0.0';
  SetupInternalLabel;
  LabelSpacing := 11;
  LabelPosition := lpLeft;
end;

procedure TActMemo.CreateParams(var Params: TCreateParams);
const
  Alignments: array[TAlignment] of Word = (ES_LEFT, ES_RIGHT, ES_CENTER);
begin
  inherited CreateParams(Params);
  Params.Style := Params.Style or ES_MULTILINE or Alignments[FAlignment];
end;

procedure TActMemo.Loaded;
begin
  if Enabled then
    inherited Color := FColorEnabled
  else
    inherited Color := FColorDisabled;
end;

procedure TActMemo.Notification(AComponent: TComponent;
  Operation: TOperation);
begin
  inherited Notification(AComponent, Operation);
  if (AComponent = FMemoLabel) and (Operation = opRemove) then
    FMemoLabel := nil;
end;

procedure TActMemo.SetAlignment(const Value: TAlignment);
var
  SelSt, SelLe: Integer;
begin
  if FAlignment <> Value then
  begin
    SelSt := SelStart;
    SelLe := SelLength;
    FAlignment := Value;
    RecreateWnd;
    SelStart := SelSt;
    SelLength := SelLe;
  end;
end;

procedure TActMemo.SetBounds(ALeft, ATop, AWidth, AHeight: Integer);
begin
  inherited SetBounds(ALeft, ATop, AWidth, AHeight);
  SetLabelPosition(FLabelPosition);
end;

procedure TActMemo.SetColor(const Value: TColor);
begin
  if FColorEnabled <> Value then
  begin
    FColorEnabled := Value;
    if Enabled then
      inherited Color := Value;
    if (Parent <> nil) and (FColorEnabled <> Parent.Brush.Color) then
      FParentColor := False;
  end;
end;

procedure TActMemo.SetColorDisabled(const Value: TColor);
begin
  if FColorDisabled <> Value then
  begin
    FColorDisabled := Value;
    if (not Enabled) then
      inherited Color := Value;
  end;
end;

procedure TActMemo.SetColorOnFocus(const Value: TColor);
begin
  if Value <> FColorOnFocus then
    FColorOnFocus := Value;
end;

procedure TActMemo.SetColorOnNotFocus(const Value: TColor);
begin
  if Value <> FColorOnNotFocus then
    FColorOnNotFocus := Value;
end;

procedure TActMemo.SetEnabled(Value: Boolean);
begin
  if Enabled then
    inherited Color := FColorEnabled
  else
    inherited Color := FColorDisabled;
end;

procedure TActMemo.SetLabelPosition(const Value: TLabelPosition);
var
  P: TPoint;
begin
  if FMemoLabel = nil then Exit;
  FLabelPosition := Value;
  case Value of
    lpAbove: P := Point(Left, Top - FMemoLabel.Height - FLabelSpacing);
    lpBelow: P := Point(Left, Top + Height + FLabelSpacing);
    lpLeft : P := Point(Left - FMemoLabel.Width - FLabelSpacing,
                    Top + ((Height - FMemoLabel.Height) div 2));
    lpRight: P := Point(Left + Width + FLabelSpacing,
                    Top + ((Height - FMemoLabel.Height) div 2));
  end;
  FMemoLabel.SetBounds(P.x, P.y, FMemoLabel.Width, FMemoLabel.Height);
end;

procedure TActMemo.SetLabelSpacing(const Value: Integer);
begin
  FLabelSpacing := Value;
  SetLabelPosition(FLabelPosition);
end;

procedure TActMemo.SetName(const Value: TComponentName);
begin
  if (csDesigning in ComponentState) and ((FMemoLabel.GetTextLen = 0) or
     (CompareText(FMemoLabel.Caption, Name) = 0)) then
    FMemoLabel.Caption := Value;
  inherited SetName(Value);
  if csDesigning in ComponentState then
    Text := '';
end;

procedure TActMemo.SetParent(AParent: TWinControl);
begin
  inherited SetParent(AParent);
  if FMemoLabel = nil then exit;
  FMemoLabel.Parent := AParent;
  FMemoLabel.Visible := True;
end;

procedure TActMemo.SetParentColor(const Value: Boolean);
begin
  if FParentColor <> Value then
  begin
    FParentColor := Value;
    if FParentColor and (Parent <> nil) then
      FColorEnabled := Parent.Brush.Color;
    SetEnabled(Enabled);
  end;
end;

procedure TActMemo.SetupInternalLabel;
begin
  if Assigned(FMemoLabel) then Exit;
  FMemoLabel := TBoundLabel.Create(Self);
  FMemoLabel.FreeNotification(Self);
end;

procedure TActMemo.SetValidade(const Value: Boolean);
begin
  if Value <> FValidate then
    FValidate := Value;
end;

procedure TActMemo.SetVersion(const Value: String);
begin
  { Somente Leitura }
end;

procedure TActMemo.SetLanguage(const Value: TActLanguage);
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
        FMemoLabel.Caption := ResourceStr + ':';
      LabelPosition := lpLeft;
    end;
  end;
end;

procedure TActMemo.SetResourceID(const Value: Integer);
begin
  if Value <> FResourceID then
    FResourceID := Value;
end;

procedure TActMemo.DoEnter;
begin
  Color := FColorOnFocus;
  if Assigned(FOnEnter) then
    FOnEnter(Self);
  if csLButtonDown in ControlState then
    FEnter := True;
  if AutoSelect then
    SelectAll;
  inherited DoEnter;
end;

procedure TActMemo.DoExit;
begin
  Color := FColorOnNotFocus;
  if Assigned(FOnExit) then
    FOnExit(Self);
  inherited DoExit;
end;

end.

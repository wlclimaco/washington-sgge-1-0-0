unit ActComboBox;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, ExtCtrls, ActLanguage;

type
  TActComboBox = class(TCustomComboBox)
  private
    { Private declarations }
    FComboLabel: TBoundLabel;
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
    FNotNull: Boolean;
    FEnterByTab: Boolean;
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
    procedure SetComboLabel(const Value: TBoundLabel);
    procedure SetNotNull(const Value: Boolean);
    procedure SetEnterByTab(const Value: Boolean);
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
    procedure DoEnter; override;
    procedure DoExit; override;
    procedure KeyDown(var Key: Word; Shift: TShiftState); override;
    procedure KeyPress(var Key: Char); override;
    procedure SetEnabled(Value: Boolean); override;
    procedure Loaded; override;
  public
    { Public declarations }
    ResourceStr: String;
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
    procedure SetBounds(ALeft: Integer; ATop: Integer; AWidth: Integer; AHeight: Integer); override;
    procedure CreateParams(var Params: TCreateParams); override;
    procedure SetupInternalLabel;
  published
    { Published declarations }
    property ComboLabel: TBoundLabel read FComboLabel write SetComboLabel;
    property LabelPosition: TLabelPosition read FLabelPosition write SetLabelPosition;
    property LabelSpacing: Integer read FLabelSpacing write SetLabelSpacing;
    property Alignment: TAlignment read FAlignment write SetAlignment;
    property Color: TColor read FColorEnabled write SetColor default clWindow;
    property ColorOnFocus: TColor read FColorOnFocus write SetColorOnFocus;
    property ColorOnNotFocus: TColor read FColorOnNotFocus write SetColorOnNotFocus;
    property ColorDisabled: TColor read FColorDisabled write SetColorDisabled default clBtnFace;
    property Enabled: Boolean read GetEnabled write SetEnabled default True;
    property EnterByTab: Boolean read FEnterByTab write SetEnterByTab default True;
    property NotNull: Boolean read FNotNull write SetNotNull default False;
    property ParentColor: Boolean read FParentColor write SetParentColor default False;
    property Validate: Boolean read FValidate write SetValidade default False;
    property Version: String read FVersion write SetVersion stored False;
    property ResourceID: Integer read FResourceID write SetResourceID;
    property Language: TActLanguage read FLanguage write SetLanguage;
    property AutoComplete default True;
    property AutoDropDown default False;
    property AutoCloseUp default False;
    property BevelEdges;
    property BevelInner;
    property BevelKind default bkNone;
    property BevelOuter;
    property Style; {Must be published before Items}
    property Anchors;
    property BiDiMode;
    property CharCase;
    property Constraints;
    property Ctl3D;
    property DragCursor;
    property DragKind;
    property DragMode;
    property DropDownCount;
    property Font;
    property ImeMode;
    property ImeName;
    property ItemHeight;
    property ItemIndex default -1;
    property MaxLength;
    property ParentBiDiMode;
    property ParentCtl3D;
    property ParentFont;
    property ParentShowHint;
    property PopupMenu;
    property ShowHint;
    property Sorted;
    property TabOrder;
    property TabStop;
    property Text;
    property Visible;
    property OnChange;
    property OnClick;
    property OnEnter: TNotifyEvent read FOnEnter write FOnEnter;
    property OnExit: TNotifyEvent read FOnExit write FOnExit;
    property OnCloseUp;
    property OnContextPopup;
    property OnDblClick;
    property OnDragDrop;
    property OnDragOver;
    property OnDrawItem;
    property OnDropDown;
    property OnEndDock;
    property OnEndDrag;
    property OnKeyDown;
    property OnKeyPress;
    property OnKeyUp;
    property OnMeasureItem;
    property OnSelect;
    property OnStartDock;
    property OnStartDrag;
    property Items; { Must be published after OnMeasureItem }
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Active Standard', [TActComboBox]);
end;

{ TActComboBox }

procedure TActComboBox.CMBidimodechanged(var Message: TMessage);
begin
  inherited;
  FComboLabel.BiDiMode := BiDiMode;
end;

procedure TActComboBox.CMEnabledchanged(var Message: TMessage);
begin
  inherited;
  FComboLabel.Enabled := Enabled;
end;

 procedure TActComboBox.CMVisiblechanged(var Message: TMessage);
begin
  inherited;
  FComboLabel.Visible := Visible;
end;

constructor TActComboBox.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
  FAlignment := taLeftJustify;
  FColorDisabled := clBtnFace;
  FColorEnabled := clWindow;
  FColorOnFocus := $00F8E4D8;
  FColorOnNotFocus := clWindow;
  FEnter := False;
  FEnterByTab := True;
  FVersion := '1.0.0';
  LabelSpacing := 11;
  LabelPosition := lpLeft;
  Text := '';  
  SetupInternalLabel;
end;

procedure TActComboBox.CreateParams(var Params: TCreateParams);
const
  Alignments: array[TAlignment] of Word = (ES_LEFT, ES_RIGHT, ES_CENTER);
begin
  inherited CreateParams(Params);
  Params.Style := Params.Style or ES_MULTILINE or Alignments[FAlignment];
end;

destructor TActComboBox.Destroy;
begin
  inherited Destroy;
end;

procedure TActComboBox.DoEnter;
begin
  Color := FColorOnFocus;
  if Assigned(FOnEnter) then
    FOnEnter(Self);
  if csLButtonDown in ControlState then
    FEnter := True;
  inherited DoEnter;
end;

procedure TActComboBox.DoExit;
begin
  Color := FColorOnNotFocus;
  if Assigned(FOnExit) then
    FOnExit(Self);
  inherited DoExit;
end;

procedure TActComboBox.KeyDown(var Key: Word; Shift: TShiftState);
begin
  inherited;
  FEnter := False;
end;

procedure TActComboBox.KeyPress(var Key: Char);
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

procedure TActComboBox.Loaded;
begin
  inherited;
  if Enabled then
    inherited Color := FColorEnabled
 else
    inherited Color := FColorDisabled;
end;

procedure TActComboBox.Notification(AComponent: TComponent;
  Operation: TOperation);
begin
  inherited Notification(AComponent, Operation);
  if (AComponent = FComboLabel) and (Operation = opRemove) then
    FComboLabel := nil;
end;

procedure TActComboBox.SetAlignment(const Value: TAlignment);
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

procedure TActComboBox.SetBounds(ALeft, ATop, AWidth, AHeight: Integer);
begin
  inherited SetBounds(ALeft, ATop, AWidth, AHeight);
  SetLabelPosition(FLabelPosition);
end;

procedure TActComboBox.SetColor(const Value: TColor);
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

procedure TActComboBox.SetColorDisabled(const Value: TColor);
begin
  if FColorDisabled <> Value then
  begin
    FColorDisabled := Value;
    if (not Enabled) then
      inherited Color := Value;
  end;
end;

procedure TActComboBox.SetColorOnFocus(const Value: TColor);
begin
  if Value <> FColorOnFocus then
    FColorOnFocus := Value;
end;

procedure TActComboBox.SetColorOnNotFocus(const Value: TColor);
begin
  if Value <> FColorOnNotFocus then
    FColorOnNotFocus := Value;
end;

procedure TActComboBox.SetComboLabel(const Value: TBoundLabel);
begin
  if Value <> FComboLabel then
  begin
    FComboLabel := Value;
    Refresh;
  end;
end;

procedure TActComboBox.SetEnabled(Value: Boolean);
begin
  inherited;
  if Enabled then
    inherited Color := FColorEnabled
  else
    inherited Color := FColorDisabled;
end;

procedure TActComboBox.SetEnterByTab(const Value: Boolean);
begin
  if Value <> FEnterByTab then
    FEnterByTab := Value;
end;

procedure TActComboBox.SetLabelPosition(const Value: TLabelPosition);
var
  P: TPoint;
begin
  if FComboLabel = nil then Exit;
  FLabelPosition := Value;
  case Value of
    lpAbove: P := Point(Left, Top - FComboLabel.Height - FLabelSpacing);
    lpBelow: P := Point(Left, Top + Height + FLabelSpacing);
    lpLeft : P := Point(Left - FComboLabel.Width - FLabelSpacing,
                    Top + ((Height - FComboLabel.Height) div 2));
    lpRight: P := Point(Left + Width + FLabelSpacing,
                    Top + ((Height - FComboLabel.Height) div 2));
  end;
  FComboLabel.SetBounds(P.x, P.y, FComboLabel.Width, FComboLabel.Height);
end;

procedure TActComboBox.SetLabelSpacing(const Value: Integer);
begin
  FLabelSpacing := Value;
  SetLabelPosition(FLabelPosition);
end;

procedure TActComboBox.SetLanguage(const Value: TActLanguage);
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
        FComboLabel.Caption := ResourceStr + ':';
      LabelPosition := lpLeft;
    end;
  end;
end;

procedure TActComboBox.SetName(const Value: TComponentName);
begin
  if (csDesigning in ComponentState) and ((FComboLabel.GetTextLen = 0) or
     (CompareText(FComboLabel.Caption, Name) = 0)) then
    FComboLabel.Caption := Value;
  inherited SetName(Value);
  if csDesigning in ComponentState then
    Text := '';
end;

procedure TActComboBox.SetNotNull(const Value: Boolean);
begin
  if Value <> FNotNull then
    FNotNull := Value;
end;

procedure TActComboBox.SetParent(AParent: TWinControl);
begin
  inherited SetParent(AParent);
  if FComboLabel = nil then Exit;
  FComboLabel.Parent := AParent;
  FComboLabel.Visible := True;
end;

procedure TActComboBox.SetParentColor(const Value: Boolean);
begin
  if FParentColor <> Value then
  begin
    FParentColor := Value;
    if FParentColor and (Parent <> nil) then
      FColorEnabled := Parent.Brush.Color;
    SetEnabled(Enabled);
  end;
end;

procedure TActComboBox.SetResourceID(const Value: Integer);
begin
  if Value <> FResourceID then
    FResourceID := Value;
end;

procedure TActComboBox.SetupInternalLabel;
begin
  if Assigned(FComboLabel) then Exit;
  FComboLabel := TBoundLabel.Create(Self);
  FComboLabel.FreeNotification(Self);
end;

procedure TActComboBox.SetValidade(const Value: Boolean);
begin
  if Value <> FValidate then
    FValidate := Value;
end;

procedure TActComboBox.SetVersion(const Value: String);
begin
  { Somente Leitura }
end;

end.

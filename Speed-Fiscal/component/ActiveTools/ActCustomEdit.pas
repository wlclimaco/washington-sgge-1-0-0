unit ActCustomEdit;

interface

uses
  Windows, Forms, Messages, SysUtils, Classes, Controls, StdCtrls, ExtCtrls,
  Graphics, WinTypes, ActLanguage;

type
  TActCustomEdit = class(TCustomLabeledEdit)
  private
    { Private declarations }
    FCanvas: TCanvas;
    FAlignment: TAlignment;
    FEnter, FParentColor: Boolean;
    FColorDisabled, FColorEnabled: TColor;
    FNotNull: Boolean;
    FColorOnNotFocus: TColor;
    FColorOnFocus: TColor;
    FOnEnter: TNotifyEvent;
    FOnExit: TNotifyEvent;
    FEnterByTab: Boolean;
    FVersion: String;
    procedure SetAlignment(Value: TAlignment);
    procedure SetColor(Value: TColor);
    procedure SetColorDisabled(Value: TColor);
    procedure SetParentColor(Value: Boolean);
    procedure WMGetDlgCode(var Msg: TWMGetDlgCode); message WM_GETDLGCODE;
    procedure CMPARENTCOLORCHANGED(var M: TMessage); message CM_PARENTCOLORCHANGED;
    procedure SetNotNull(const Value: Boolean);
    procedure SetColorOnFocus(const Value: TColor);
    procedure SetColorOnNotFocus(const Value: TColor);
    procedure SetEnterByTab(const Value: Boolean);
    procedure SetVersion(const Value: String);
  protected
    { Protected declarations }
    procedure MouseDown(Button: TMouseButton; Shift: TShiftState; X, Y: Integer); override;
    procedure DoEnter; override;
    procedure DoExit; override;
    procedure KeyDown(var Key: Word; Shift: TShiftState); override;
    procedure KeyPress(var Key: Char); override;
    procedure SetEnabled(Value: Boolean); override;
    procedure Loaded; override;
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
    procedure CreateParams(var Params: TCreateParams); override;
  published
    { Published declarations }
    property Alignment: TAlignment read FAlignment write SetAlignment;
    property Color: TColor read FColorEnabled write SetColor default clWindow;
    property ColorOnFocus: TColor read FColorOnFocus write SetColorOnFocus;
    property ColorOnNotFocus: TColor read FColorOnNotFocus write SetColorOnNotFocus;
    property ColorDisabled: TColor read FColorDisabled write SetColorDisabled default clBtnFace;
    property Enabled: Boolean read GetEnabled write SetEnabled default True;
    property EnterByTab: Boolean read FEnterByTab write SetEnterByTab default True;
    property LabelPosition default lpLeft;
    property LabelSpacing default 11;
    property NotNull: Boolean read FNotNull write SetNotNull default False;
    property ParentColor: Boolean read FParentColor write SetParentColor default False;
    property Version: String read FVersion write SetVersion stored False;
    property OnEnter: TNotifyEvent read FOnEnter write FOnEnter;
    property OnExit: TNotifyEvent read FOnExit write FOnExit;
    property EditLabel;
    property Anchors;
    property AutoSelect;
    property AutoSize;
    property BevelEdges;
    property BevelInner;
    property BevelKind;
    property BevelOuter;
    property BiDiMode;
    property BorderStyle;
    property CharCase;
    property Constraints;
    property Ctl3D;
    property DragCursor;
    property DragKind;
    property DragMode;
    property Font;
    property HideSelection;
    property ImeMode;
    property ImeName;
    property MaxLength;
    property OEMConvert;
    property ParentBiDiMode;
    property ParentCtl3D;
    property ParentFont;
    property ParentShowHint;
    property PasswordChar;
    property PopupMenu;
    property ReadOnly;
    property ShowHint;
    property TabOrder;
    property TabStop;
    property Text;
    property Visible;
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


implementation

{ TActCustomEdit }

constructor TActCustomEdit.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
  FCanvas := TControlCanvas.Create;
  TControlCanvas(FCanvas).Control := Self;
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
end;

procedure TActCustomEdit.CreateParams(var Params: TCreateParams);
const
  Alignments: array[TAlignment] of Word = (ES_LEFT, ES_RIGHT, ES_CENTER);
begin
  Params.Style := Params.Style or ES_MULTILINE or Alignments[FAlignment];
  inherited CreateParams(Params);
end;

procedure TActCustomEdit.Loaded;
begin
  inherited;
  if Enabled then
    inherited Color := FColorEnabled
  else
    inherited Color := FColorDisabled;
end;

procedure TActCustomEdit.MouseDown(Button: TMouseButton; Shift: TShiftState; X, Y: Integer);
begin
  if (Button = mbLeft) or (ssLeft in Shift) then
  begin
    if FEnter = True then
    begin
      FEnter := False;
      if AutoSelect then
        SelectAll;
    end;
  end;
  inherited MouseDown(Button, Shift, X, Y);
end;

procedure TActCustomEdit.KeyDown(var Key: Word; Shift: TShiftState);
begin
  inherited KeyDown(Key, Shift);
  FEnter := False;
end;

procedure TActCustomEdit.KeyPress(var Key: Char);
var
  FKeyTemp: TCustomForm;
begin
  inherited KeyPress(Key);
  if Key = #13 then
  begin
    if FEnterByTab then
    begin
      Key := #0;
      FKeyTemp := GetParentForm(Self);
      SendMessage(FKeyTemp.Handle, WM_NEXTDLGCTL, 0, 0);
    end;
  end;
end;

procedure TActCustomEdit.SetAlignment(Value: TAlignment);
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

procedure TActCustomEdit.SetColor(Value: TColor);
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

procedure TActCustomEdit.SetColorDisabled(Value: TColor);
begin
  if FColorDisabled <> Value then
  begin
    FColorDisabled := Value;
    if (not Enabled) then
      inherited Color := Value;
  end;
end;

procedure TActCustomEdit.SetEnabled(Value: Boolean);
begin
  inherited;
  if Enabled then
    inherited Color := FColorEnabled
  else
    inherited Color := FColorDisabled;
end;

procedure TActCustomEdit.SetParentColor(Value: Boolean);
begin
  if FParentColor <> Value then
  begin
    FParentColor := Value;
    if FParentColor and (Parent <> nil) then
      FColorEnabled := Parent.Brush.Color;
    SetEnabled(Enabled);
  end;
end;

procedure TActCustomEdit.CMPARENTCOLORCHANGED(var M: TMessage);
begin
  if FParentColor and (Parent <> nil) then
    FColorEnabled := Parent.Brush.Color;
  if Parent <> nil then
    Invalidate;
  SetEnabled(Enabled);
end;

procedure TActCustomEdit.WMGetDlgCode(var Msg: TWMGetDlgCode);
begin
  Msg.Result := DLGC_WANTCHARS or DLGC_WANTARROWS;
end;

procedure TActCustomEdit.SetNotNull(const Value: Boolean);
begin
  if Value <> FNotNull then
    FNotNull := Value;
end;

procedure TActCustomEdit.SetColorOnFocus(const Value: TColor);
begin
  if Value <> FColorOnFocus then
    FColorOnFocus := Value;
end;

procedure TActCustomEdit.SetColorOnNotFocus(const Value: TColor);
begin
  if Value <> FColorOnNotFocus then
    FColorOnNotFocus := Value;
end;

procedure TActCustomEdit.DoEnter;
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

procedure TActCustomEdit.DoExit;
begin
  Color := FColorOnNotFocus;
  if Assigned(FOnExit) then
    FOnExit(Self);
  inherited DoExit;
end;

procedure TActCustomEdit.SetEnterByTab(const Value: Boolean);
begin
  if Value <> FEnterByTab then
    FEnterByTab := Value;
end;

procedure TActCustomEdit.SetVersion(const Value: String);
begin
  { Read Only }
end;

destructor TActCustomEdit.Destroy;
begin
  inherited Destroy;
end;

end.


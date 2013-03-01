unit ActCurrencyEdit;

interface

uses
  Windows, SysUtils, Messages, Classes, Controls, Forms, Graphics, Dialogs,
  Menus, StdCtrls, Buttons, ExtCtrls, Clipbrd, ActCustomEdit;

type
  TActCurrencyEdit = class(TActCustomEdit)
  private
    FAlignment: TAlignment;
    FAutoHideCalculator: Boolean;
    FButton: TSpeedButton;
    FCanvas: TControlCanvas;
    FDecimals: Byte;
    FZeroLength: Byte;
    FGlyph: TBitmap;
    FHintBtn: string;
    FKey: Char;
    FMaxValue: Extended;
    FMinValue: Extended;
    FShowButton: Boolean;
    FShowSeparator: Boolean;
    FWinBack: TWinControl;
    FOnBtnClick: TNotifyEvent;
    FOnOutOfRange: TNotifyEvent;
    procedure ConvertText;
    function StrZero(Number: string; Len: Integer): string;
    function ExtractInvalidChar(S: string): string;
    procedure ButtonClick(Sender: TObject);
    procedure ChangeGlyph(Sender: TObject);
    procedure CheckLimitText;
    procedure CreateButton;
    procedure EvalDecSeparator;
    procedure EvaluateKey;
    procedure InvertValue;
    procedure SetAlignment(Alig: TAlignment);
    procedure SetButtonSize;
    procedure SetClientRect;
    procedure SetDecimals(Dec: Byte);
    procedure SetEnabledBtn(Value: Boolean);
    procedure SetGlyph(Value: TBitmap);
    procedure SetHintBtn(Value: string);
    procedure SetShowButton(const Value: Boolean);
    procedure SetShowCaret;
    procedure SetShowSeparator(Value: Boolean);
    procedure SetValue(Val: Extended);
    procedure SetZeroLength(Value: Byte);
    procedure ValidateKey;
    procedure CMEnter(var Msg: TCMEnter); message CM_ENTER;
    procedure CMExit(var Msg: TCMExit); message CM_EXIT;
    procedure WMMouse(var Msg: TWMMouse); message WM_LBUTTONDOWN;
    procedure WMPaint(var Msg: TWMPaint); message WM_PAINT;
    procedure WMSize(var Msg: TWMSize); message WM_SIZE;
    procedure WMPaste(var Msg: TMessage); message WM_PASTE;
    procedure WMSetFocus(var Msg: TMessage); message WM_SETFOCUS;
    function CheckRange: Boolean;
    function GetCursorHeight: Integer;
    function GetEnabledBtn: Boolean;
    function GetTextMargins: TPoint;
    function GetValue: Extended;
    function StrToValue(S: string): Extended;
  protected
    procedure CreateWnd; override;
    procedure KeyPress(var Key: Char); override;
  public
    procedure CreateParams(var Params: TCreateParams); override;
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
    procedure ShowCalculator;
  published
    property Alignment: TAlignment read FAlignment write SetAlignment default taRightJustify;
    property AutoHideCalculator: Boolean read FAutoHideCalculator write FAutoHideCalculator default True;
    property Anchors;
    property AutoSelect;
    property AutoSize;
    property BorderStyle;
    property Color;
    property Constraints;
    property Ctl3D;
    property Decimals: Byte read FDecimals write SetDecimals default 2;
    property DragCursor;
    property DragKind;
    property DragMode;
    property Enabled;
    property EnabledBtn: Boolean read GetEnabledBtn write SetEnabledBtn default True;
    property Font;
    property Glyph: TBitmap read FGlyph write SetGlyph;
    property HideSelection;
    property HintBtn: string read FHintBtn write SetHintBtn;
    property MaxLength;
    property MaxValue: Extended read FMaxValue write FMaxValue;
    property MinValue: Extended read FMinValue write FMinValue;
    property ParentColor;
    property ParentCtl3D;
    property ParentFont;
    property ParentShowHint;
    property PasswordChar;
    property PopupMenu;
    property ReadOnly;
    property ShowHint;
    property ShowButton: Boolean read FShowButton write SetShowButton default False;
    property ShowSeparator: Boolean read FShowSeparator write SetShowSeparator default True;
    property TabOrder;
    property TabStop;
    property Value: Extended read GetValue write SetValue;
    property Visible;
    property ZeroLength: Byte read FZeroLength write SetZeroLength default 0;
    property OnBtnClick: TNotifyEvent read FOnBtnClick write FOnBtnClick;
    property OnChange;
    property OnClick;
    property OnContextPopup;
    property OnDblClick;
    property OnDragDrop;
    property OnDragOver;
    property OnEndDock;
    property OnEndDrag;
    property OnEnter;
    property OnExit;
    property OnKeyDown;
    property OnKeyPress;
    property OnKeyUp;
    property OnMouseDown;
    property OnMouseMove;
    property OnMouseUp;
    property OnOutOfRange: TNotifyEvent read FOnOutOfRange write FOnOutOfRange;
    property OnStartDock;
    property OnStartDrag;
  end;

  TFrmActCurrencyEdit = class(TForm)
    Btn0: TSpeedButton;
    Btn1: TSpeedButton;
    Btn2: TSpeedButton;
    Btn3: TSpeedButton;
    Btn4: TSpeedButton;
    Btn5: TSpeedButton;
    Btn6: TSpeedButton;
    Btn7: TSpeedButton;
    Btn8: TSpeedButton;
    Btn9: TSpeedButton;
    BtnSep: TSpeedButton;
    BtnAdd: TSpeedButton;
    BtnSub: TSpeedButton;
    BtnDiv: TSpeedButton;
    BtnMult: TSpeedButton;
    BtnPerc: TSpeedButton;
    BtnC: TSpeedButton;
    BtnCE: TSpeedButton;
    BtnRes: TSpeedButton;
    BtnSgn: TSpeedButton;
    BtnCancel: TSpeedButton;
    BtnOK: TSpeedButton;
    PanBack: TPanel;
    PanDisplay: TPanel;
    Bevel1: TBevel;
    procedure FormCreate(Sender: TObject);
    procedure FormDeactivate(Sender: TObject);
    procedure FormKeyPress(Sender: TObject; var Key: Char);
    procedure BtnNumClick(Sender: TObject);
    procedure BtnCEClick(Sender: TObject);
    procedure BtnCClick(Sender: TObject);
    procedure BtnBaseOpClick(Sender: TObject);
    procedure BtnResClick(Sender: TObject);
    procedure BtnSgnClick(Sender: TObject);
    procedure BtnPercClick(Sender: TObject);
    procedure BtnCancelClick(Sender: TObject);
    procedure BtnOKClick(Sender: TObject);
    procedure PanMouseDown(Sender: TObject; Button: TMouseButton;
      Shift: TShiftState; X, Y: Integer);
    procedure PanMouseMove(Sender: TObject; Shift: TShiftState; X, Y: Integer);
  private
    FEnterChanged: Boolean;
    FLastEnter: string;
    FLastX: Integer;
    FLastY: Integer;
    FOnwerEdt: TActCurrencyEdit;
    FOperator: Char;
    FValBuffer: Double;
    procedure CalcOperation;
    procedure EvalKey(Key: Char);
    procedure ResetCalc;
    procedure SendKeyToDiplay(Key: Char);
    procedure ShowResult;
    function GetDisplayValue: Double;
  end;

var
  FrmActCurrencyEdit: TFrmActCurrencyEdit;

procedure Register;

implementation

{$J+}
{$R *.RES}
{$R *.DFM}

const
  IMAGE_RES = 'NUMED_CALC';

type
  TEvBtnNumEdit = class(TSpeedButton)
  private
    FCanvas: TControlCanvas;
  protected
    procedure Paint; override;
  public
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
  end;

constructor TEvBtnNumEdit.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
  FCanvas := TControlCanvas.Create;
  FCanvas.Control := Self;
end;

destructor TEvBtnNumEdit.Destroy;
begin
  FCanvas.Free;
  inherited Destroy;
end;

procedure TEvBtnNumEdit.Paint;
var
  R: TRect;
begin
  inherited;
  if FState = bsUp then
    begin
      R := ClientRect;
      Frame3D(FCanvas, R, clBtnHighLight, clWindowFrame, 1);
      Frame3D(FCanvas, R, clBtnFace, clBtnShadow, 1);
    end;
end;

constructor TActCurrencyEdit.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
  FCanvas := TControlCanvas.Create;
  FCanvas.Control := Self;
  FAutoHideCalculator := True;
  FAlignment := taRightJustify;
  FDecimals := 2;
  FZeroLength := 0;
  FShowSeparator := True;
  FShowButton := False;
  CreateButton;
end;

destructor TActCurrencyEdit.Destroy;
begin
  FGlyph.Free;
  FButton.Free;
  FWinBack.Free;
  FCanvas.Free;
  inherited Destroy;
end;

procedure TActCurrencyEdit.CreateParams(var Params: TCreateParams);
const
  Alignments: array[TAlignment] of DWORD = (ES_LEFT, ES_RIGHT, ES_CENTER);
begin
  inherited CreateParams(Params);
  Params.Style := Params.Style or ES_AUTOHSCROLL or ES_MULTILINE or
    Alignments[FAlignment] or WS_CLIPCHILDREN;
end;

procedure TActCurrencyEdit.SetZeroLength(Value: Byte);
begin
  if Value <> FZeroLength then
    begin
      FZeroLength := Value;
      MaxLength := FZeroLength;
      ConvertText;
    end;
end;

procedure TActCurrencyEdit.ConvertText;
begin
  if not (csDesigning in ComponentState) then
    begin
      if Text = Name then
        Text := StrZero('0', FZeroLength)
      else
        Text := StrZero(ExtractInvalidChar(Text), FZeroLength)
    end
  else if Text <> Name then
    Text := StrZero(ExtractInvalidChar(Text), FZeroLength);
  if Focused then
    SelectAll;
end;

function TActCurrencyEdit.StrZero(Number: string; Len: Integer): string;
begin
  if Length(Number) > Len then
    Number := Copy(Number, Length(Number) - Len + 1, Len)
  else
    while Length(Number) < Len do
      Number := '0' + Number;
  Result := Number;
end;

function TActCurrencyEdit.ExtractInvalidChar(S: string): string;
var
  I: Integer;
begin
  Result := '';
  for I := 1 to Length(S) do
    if S[I] in ['0'..'9'] then
      Result := Result + S[I];
end;

procedure TActCurrencyEdit.CreateWnd;
begin
  inherited CreateWnd;
  SetClientRect;
end;

procedure TActCurrencyEdit.SetAlignment(Alig: TAlignment);
begin
  if Alig <> FAlignment then
    begin
      FAlignment := Alig;
      RecreateWnd;
      if Focused then
        SelectAll;
    end;
end;

procedure TActCurrencyEdit.SetDecimals(Dec: Byte);
begin
  FDecimals := Dec;
  if (GetValue = 0) and (csDesigning in ComponentState) then
    Text := Name
  else
    if FZeroLength > 0 then
      Text := StrZero(FloatToStr(GetValue), FZeroLength)
    else
      Text := FloatToStrF(GetValue, ffFixed, 20, FDecimals);
  if Focused then
    SelectAll;
  Invalidate;
end;

procedure TActCurrencyEdit.SetEnabledBtn(Value: Boolean);
begin
  if FButton <> nil then
    FButton.Enabled := Value;
end;

procedure TActCurrencyEdit.SetGlyph(Value: TBitmap);
begin
  if Value = nil then
    FGlyph.Handle := LoadBitmap(HInstance, IMAGE_RES)
  else
    FGlyph.Assign(Value);
end;

procedure TActCurrencyEdit.SetHintBtn(Value: string);
begin
  if FHintBtn <> Value then
    begin
      FHintBtn := Value;
      FButton.Hint := Value;
    end;
end;

procedure TActCurrencyEdit.SetShowButton(const Value: Boolean);
begin
  FShowButton := Value;
  if not FShowButton then
    begin
      FWinBack.SetBounds(0, 0, 0, 0);
      FButton.SetBounds(0, 0, 0, 0);
      SetClientRect;
    end
  else
    begin
      SetButtonSize;
      SetClientRect;
    end;
  Invalidate;
end;

procedure TActCurrencyEdit.SetShowSeparator(Value: Boolean);
begin
  if FShowSeparator <> Value then
    begin
      FShowSeparator := Value;
      Invalidate;
    end;
end;

procedure TActCurrencyEdit.SetValue(Val: Extended);
begin
  if (Val = 0) and (csDesigning in ComponentState) then
    Text := Name
  else
    if FZeroLength > 0 then
      Text := StrZero(FloatToStr(Val), FZeroLength)
    else
      Text := FloatToStrF(Val, ffFixed, 20, FDecimals);
  if Focused then
    SelectAll;
  Invalidate;
end;

function TActCurrencyEdit.GetValue: Extended;
begin
  if (Text = Name) then
    Result := 0
  else
    Result := StrToValue(Text);
end;

function TActCurrencyEdit.StrToValue(S: string): Extended;
var
  N: string;
  I: Integer;
begin
  N := '0';
  for I := 1 to Length(S) do
    if ((S[I] = DecimalSeparator) and (Pos(DecimalSeparator, N) = 0))
      or (S[I] in ['0'..'9']) then
      N := N + S[I];
  Result := StrToFloat(N);
  if (N <> '0') and (Pos('-', S) = 1) then
    Result := -StrToFloat(N)
end;

function TActCurrencyEdit.GetEnabledBtn: Boolean;
begin
  Result := (FButton <> nil) and FButton.Enabled;
end;

procedure TActCurrencyEdit.ChangeGlyph(Sender: TObject);
var
  NG: Byte;
begin
  FButton.Glyph := FGlyph;
  NG := 1;
  if not FGlyph.Empty then
    if (FGlyph.Width mod FGlyph.Height) = 0 then
      begin
        NG := FGlyph.Width div FGlyph.Height;
        if NG > 4 then
          NG := 1;
      end;
  FButton.NumGlyphs := NG;
end;

procedure TActCurrencyEdit.CMEnter(var Msg: TCMEnter);
begin
  if FZeroLength > 0 then
    Text := StrZero(FloatToStr(GetValue), FZeroLength)
  else
    Text := FloatToStrF(GetValue, ffFixed, 20, FDecimals);
  inherited;
  SelectAll;
end;

procedure TActCurrencyEdit.CMExit(var Msg: TCMExit);
begin
  if FZeroLength > 0 then
    Text := StrZero(FloatToStr(GetValue), FZeroLength)
  else
    Text := FloatToStrF(GetValue, ffFixed, 20, FDecimals);
  if CheckRange then
    begin
      Invalidate;
      inherited;
    end;
end;

procedure TActCurrencyEdit.WMMouse(var Msg: TWMMouse);
begin
  if not Focused then
    begin
      inherited;
      SelectAll;
    end
  else
    inherited;
end;

procedure TActCurrencyEdit.WMPaint(var Msg: TWMPaint);
var
  X: Integer;
  Rect: TRect;
  DC: HDC;
  PS: TPaintStruct;
  S: string;
  Margins: TPoint;
begin
  if Focused and not (csPaintCopy in ControlState) then
    begin
      inherited;
      Exit;
    end;
  if (csDesigning in ComponentState) and (GetValue = 0) then
    S := Name
  else if FShowSeparator then
  begin
    if FZeroLength > 0 then
      S := StrZero(FloatToStr(GetValue), FZeroLength)
    else
      S := FloatToStrF(GetValue, ffNumber, 20, FDecimals);
  end
  else
  begin
    if FZeroLength > 0 then
      S := StrZero(FloatToStr(GetValue), FZeroLength)
    else
      S := FloatToStrF(GetValue, ffFixed, 20, FDecimals);
  end;
  DC := Msg.DC;
  if DC = 0 then
    DC := BeginPaint(Handle, PS);
  FCanvas.Handle := DC;
  try
    FCanvas.Font := Font;
    if not Enabled then
      FCanvas.Font.Color := clGrayText;
    SendMessage(Handle, EM_GETRECT, 0, Longint(@Rect));
    if not (NewStyleControls and Ctl3D) and (BorderStyle = bsSingle) then
      begin
        FCanvas.Brush.Color := clWindowFrame;
        FCanvas.FrameRect(Rect);
        InflateRect(Rect, -1, -1);
      end;
    FCanvas.Brush.Color := Color;
    Margins := GetTextMargins;
    X := Margins.X;
    case FAlignment of
      taRightJustify: X := Rect.Right - FCanvas.TextWidth(S) - Margins.X;
      taCenter: X := (Rect.Left + Rect.Right - FCanvas.TextWidth(S)) div 2;
    end;
    FCanvas.TextRect(Rect, X, Margins.Y, S);
  finally
    FCanvas.Handle := 0;
    if Msg.DC = 0 then
      EndPaint(Handle, PS);
  end;
end;

procedure TActCurrencyEdit.WMSize(var Msg: TWMSize);
begin
  inherited;
  SetButtonSize;
  SetClientRect;
end;

procedure TActCurrencyEdit.WMPaste(var Msg: TMessage);
var
  S: string;
  I: Integer;
  Key: Char;
begin
  if Clipboard.HasFormat(CF_TEXT) then
    begin
      S := FloatToStr(StrToValue(Clipboard.AsText));
      Text := '';
      for I := 1 to Length(S) do
        begin
          Key := S[I];
          KeyPress(Key);
          if Key <> #0 then
            Text := Text + Key;
        end;
      SelStart := Length(Text);
    end;
end;

procedure TActCurrencyEdit.WMSetFocus(var Msg: TMessage);
begin
  inherited;
  SetShowCaret;
end;

procedure TActCurrencyEdit.SetShowCaret;
begin
  CreateCaret(Handle, 0, 1, GetCursorHeight);
  ShowCaret(Handle);
end;

function TActCurrencyEdit.GetCursorHeight: Integer;
var
  DC: HDC;
  SaveFont: HFont;
  Metrics: TTextMetric;
begin
  DC := GetDC(0);
  try
    SaveFont := SelectObject(DC, Font.Handle);
    GetTextMetrics(DC, Metrics);
    SelectObject(DC, SaveFont);
  finally
    ReleaseDC(0, DC);
  end;
  Result := Metrics.tmHeight;
end;

procedure TActCurrencyEdit.KeyPress(var Key: Char);
var
  Form: TCustomForm;
begin
  inherited KeyPress(Key);
  if Ord(Key) in [VK_ESCAPE, VK_RETURN]  then
    begin
      Form := GetParentForm(Self);
      if Form <> nil then
        PostMessage(Form.Handle, CN_KEYDOWN, Ord(Key), 1);
      Key := #0;
    end
  else if (Key in ['c', 'C']) and FButton.Enabled then
    begin
      FButton.OnClick(FButton);
      Key := #0;
    end
  else
    begin
      FKey := Key;
      CheckLimitText;
      if FKey <> #0 then
        ValidateKey;
      if FKey = '-' then
        InvertValue;
      if FKey = DecimalSeparator then
        EvalDecSeparator;
      if FKey <> #0 then
        EvaluateKey;
      Key := FKey;
    end;
end;

procedure TActCurrencyEdit.ValidateKey;
begin
  if FKey in ['.', ','] then
    FKey := DecimalSeparator;
  if not (FKey in ['0'..'9', '.', ',', '-', #08, ^X, ^C, ^V])
    or ((GetValue < 0) and (SelStart = 0) and (SelLength = 0) and (FKey <> '-'))
    or ((FKey = DecimalSeparator) and (FDecimals = 0)) then
    begin
      MessageBeep(0);
      FKey := #0;
    end;
end;

procedure TActCurrencyEdit.InvertValue;
var
  I, L: Integer;
begin
  L := Length(Text);
  if SelLength <> L then
    begin
      I := SelStart;
      if GetValue > 0 then
        Text := '-' + Text
      else if GetValue < 0 then
        Text := Copy(Text, 2, L);
      SelStart := I + Length(Text) - L;
      FKey := #0;
    end;
end;

procedure TActCurrencyEdit.EvalDecSeparator;
var
  I, L: Integer;
begin
  L := Length(Text);
  I := Pos(DecimalSeparator, Text );
  if (I > 0) and (SelLength <> L) then
    begin
      SelStart := I;
      FKey := #0;
    end
  else if (SelLength <> L) and ((SelStart + FDecimals) < L) then
    begin
      I := SelStart;
      Text := Copy(Text, 1, SelStart + FDecimals);
      SelStart := I;
    end;
end;

procedure TActCurrencyEdit.EvaluateKey;
var
  I, L: Integer;
begin
  L := Length(Text);
  I := Pos(DecimalSeparator, Text );
  if (I <> 0) and (SelStart >= I) and ((L - I) >= FDecimals) and (SelLength = 0) then
    begin
      if (SelStart < L) then
        begin
          I := SelStart;
          Text := Copy(Text, 1, L - 1 );
          SelStart := I;
        end
      else if (FKey <> #08) then
        begin
          MessageBeep(0);
          FKey := #0;
        end;
    end;
end;

function TActCurrencyEdit.CheckRange: Boolean;
begin
  Result := True;
  if ((FMaxValue <> 0) or (FMinValue <> 0)) and
    ((GetValue > FMaxValue) or (GetValue < FMinValue)) then
    begin
      if Assigned(FOnOutOfRange) then
        FOnOutOfRange(Self)
      else
        MessageBeep(0);
      SelectAll;
      SetFocus;
      Result := False;
    end
end;

function TActCurrencyEdit.GetTextMargins: TPoint;
var
  DC: HDC;
  SaveFont: HFont;
  I: Integer;
  SysMetrics, Metrics: TTextMetric;
begin
  if NewStyleControls then
    begin
      if BorderStyle = bsNone then
        I := 0
      else if Ctl3D then
        I := 1
      else
        I := 2;
      Result.X := SendMessage(Handle, EM_GETMARGINS, 0, 0) and $0000FFFF + I;
      Result.Y := I;
    end
  else
    begin
      if BorderStyle = bsNone then
        I := 0
      else
        begin
          DC := GetDC(0);
          GetTextMetrics(DC, SysMetrics);
          SaveFont := SelectObject(DC, Font.Handle);
          GetTextMetrics(DC, Metrics);
          SelectObject(DC, SaveFont);
          ReleaseDC(0, DC);
          I := SysMetrics.tmHeight;
          if I > Metrics.tmHeight then
            I := Metrics.tmHeight;
          I := I div 4;
        end;
      Result.X := I;
      Result.Y := I;
    end;
end;

procedure TActCurrencyEdit.CreateButton;
begin
  FWinBack := TWinControl.Create(Self);
  FWinBack.Parent := Self;
  TEdit(FWinBack).Color := clBtnFace;
  FButton := TEvBtnNumEdit.Create(Self);
  FButton.Parent := FWinBack;
  FButton.OnClick := ButtonClick;
  FGlyph := TBitmap.Create;
  FGlyph.OnChange := ChangeGlyph;
  FGlyph.Handle := LoadBitmap(HInstance, IMAGE_RES);
end;

procedure TActCurrencyEdit.SetClientRect;
var
  Rect: TRect;
begin
  SendMessage(Handle, EM_GETRECT, 0, Longint(@Rect));
  Rect.Bottom := ClientHeight + 1;
  Rect.Right := ClientWidth - FButton.Width - 2;
  Rect.Top := 0;
  Rect.Left := 0;
  SendMessage(Handle, EM_SETRECTNP, 0, LongInt(@Rect));
  SendMessage(Handle, EM_GETRECT, 0, LongInt(@Rect));
end;

procedure TActCurrencyEdit.SetButtonSize;
begin
  if FShowButton then
    begin
      FWinBack.SetBounds(ClientWidth - ClientHeight - 1, 0,
        ClientHeight + 1, ClientHeight);
      FButton.SetBounds(1, 0, ClientHeight, ClientHeight);
    end
  else
    begin
      FWinBack.SetBounds(0, 0, 0, 0);
      FButton.SetBounds(0, 0, 0, 0);
    end;
end;

procedure TActCurrencyEdit.CheckLimitText;
var
  Margins: TPoint;
  I: Integer;
begin
  if FKey in ['0'..'9', '.', ',', '-'] then
    begin
      FCanvas.Font.Assign(Font);
      Margins := GetTextMargins;
      I := ClientWidth - FButton.Width - (Margins.X * 2) -
        FCanvas.TextWidth(Text) - 3;
      if (FCanvas.TextWidth(FKey) > I) and (SelLength = 0) and not
        ((GetValue < 0) and (FKey = '-')) then
        FKey := #0;
    end;
end;

procedure TActCurrencyEdit.ButtonClick(Sender: TObject);
begin
  if Assigned(FOnBtnClick) then
    FOnBtnClick(Self)
  else
    ShowCalculator;
end;

procedure TActCurrencyEdit.ShowCalculator;
var
  Pos: TPoint;
begin
  if frmActCurrencyEdit = nil then
    Application.CreateForm(TfrmActCurrencyEdit, frmActCurrencyEdit);
  Pos := Point(0, 0);
  Pos := Self.ClientToScreen(Pos);
  if (Pos.X + 135) > Screen.DesktopWidth then
    frmActCurrencyEdit.Left := Pos.X - (frmActCurrencyEdit.Width - Width) - 2
  else
    frmActCurrencyEdit.Left := Pos.X - 2;
  if (Pos.Y + 205) > Screen.DesktopHeight then
    frmActCurrencyEdit.Top := Pos.Y - frmActCurrencyEdit.Height - 2
  else
    frmActCurrencyEdit.Top := Pos.Y + Height - 3;
  frmActCurrencyEdit.FOnwerEdt := Self;
  frmActCurrencyEdit.ResetCalc;
  frmActCurrencyEdit.FValBuffer := GetValue;
  frmActCurrencyEdit.PanDisplay.Caption := FloatToStr(GetValue);
  frmActCurrencyEdit.Show;
end;


procedure TFrmActCurrencyEdit.FormCreate(Sender: TObject);
begin
  ResetCalc;
end;

procedure TFrmActCurrencyEdit.FormDeactivate(Sender: TObject);
begin
  if FOnwerEdt.FAutoHideCalculator then
    Close;
end;

procedure TFrmActCurrencyEdit.FormKeyPress(Sender: TObject; var Key: Char);
begin
  EvalKey(Key);
end;

procedure TFrmActCurrencyEdit.BtnNumClick(Sender: TObject);
begin
  EvalKey(TSpeedButton(Sender).Caption[1]);
end;

procedure TFrmActCurrencyEdit.EvalKey(Key: Char);
begin
  if Key in [',', '.', '·'] then
    Key := DecimalSeparator;
  if (Key in ['0'..'9', Chr(VK_BACK)]) or ((Key = DecimalSeparator) and
    (Pos(DecimalSeparator, FLastEnter) = 0)) then
    SendKeyToDiplay(Key)
  else if Key = '+' then
    BtnBaseOpClick(BtnAdd)
  else if Key = '-' then
    BtnBaseOpClick(BtnSub)
  else if Key = '/' then
    BtnBaseOpClick(BtnDiv)
  else if Key = '*' then
    BtnBaseOpClick(BtnMult)
  else if Key = '%' then
    BtnPercClick(BtnPerc)
  else if Key in ['=', Chr(VK_RETURN)] then
    BtnResClick(BtnRes)
  else if Key = Chr(VK_ESCAPE) then
    BtnCEClick(BtnCE)
  else if Key in ['l', 'L'] then
    BtnCClick(BtnC)
  else if Key in ['t', 'T'] then
    BtnSgnClick(BtnSgn)
  else if Key in ['c', 'C'] then
    BtnCancelClick(BtnCancel)
  else if Key in ['o', 'O'] then
    BtnOKClick(BtnOK);
end;

procedure TFrmActCurrencyEdit.SendKeyToDiplay(Key: Char);
begin
  if (Key = Chr(VK_BACK)) and (FLastEnter <> '') then
    begin
      FLastEnter := Copy(FLastEnter, 1, Length(FLastEnter) - 1);
      if FLastEnter = '' then
        PanDisplay.Caption := '0'
      else
        PanDisplay.Caption := FLastEnter;
    end
  else if (Key <> Chr(VK_BACK)) and (Length(FLastEnter) < 14) then
    begin
      FEnterChanged := True;
      FLastEnter := FLastEnter + Key;
      PanDisplay.Caption := FLastEnter;
    end;
end;

procedure TFrmActCurrencyEdit.ResetCalc;
begin
  FValBuffer := 0;
  FLastEnter := '';
  FOperator := #0;
  FEnterChanged := False;
end;

procedure TFrmActCurrencyEdit.BtnCEClick(Sender: TObject);
begin
  FLastEnter := '';
  PanDisplay.Caption := '0';
end;

procedure TFrmActCurrencyEdit.BtnCClick(Sender: TObject);
begin
  ResetCalc;
  PanDisplay.Caption := '0';
end;

function TFrmActCurrencyEdit.GetDisplayValue: Double;
begin
  if FLastEnter <> '' then
    Result := StrToFloat(FLastEnter)
  else
    Result := 0;
end;

procedure TFrmActCurrencyEdit.BtnBaseOpClick(Sender: TObject);
begin
  if FEnterChanged and (FOperator <> #0) then
    begin
      CalcOperation;
      ShowResult;
      FLastEnter := '';
    end
  else if (FValBuffer = 0) or (FLastEnter <> '') then
    begin
      FValBuffer := GetDisplayValue;
      FLastEnter := '';
    end;
  FOperator := TSpeedButton(Sender).Caption[1];
  FEnterChanged := False;
end;

procedure TFrmActCurrencyEdit.CalcOperation;
var
  X: Double;
begin
  X := GetDisplayValue;
  if FOperator = '-' then
    FValBuffer := FValBuffer - X
  else if FOperator = '+' then
    FValBuffer := FValBuffer + X
  else if (FOperator = '÷') and (X = 0) then
    begin
      ResetCalc;
      PanDisplay.Caption := 'E';
    end
  else if FOperator = '÷' then
    FValBuffer := FValBuffer / X
  else if FOperator = '×' then
    FValBuffer := FValBuffer * X;
end;

procedure TFrmActCurrencyEdit.BtnResClick(Sender: TObject);
begin
  if FEnterChanged and (FOperator <> #0) then
    begin
      CalcOperation;
      ShowResult;
      FLastEnter := '';
      FOperator := #0;
      FEnterChanged := False;
    end;
end;

procedure TFrmActCurrencyEdit.ShowResult;
var
  S: string;
begin
  if PanDisplay.Caption <> 'E' then
    begin
      if Abs(FValBuffer) >= 1E+15 then
        S := 'E'
      else
        S := FloatToStr(FValBuffer);
      PanDisplay.Caption := S;
    end;
end;

procedure TFrmActCurrencyEdit.BtnSgnClick(Sender: TObject);
begin
  if (FValBuffer <> 0) and (FLastEnter = '') then
    begin
      FValBuffer := -FValBuffer;
      ShowResult;
    end
  else if (FLastEnter <> '') then
    begin
      if (FLastEnter[1] = '-') then
        FLastEnter := Copy(FLastEnter, 2, Length(FLastEnter) - 1)
      else
        FLastEnter := '-' + FLastEnter;
      PanDisplay.Caption := FLastEnter;
    end;
end;

procedure TFrmActCurrencyEdit.BtnPercClick(Sender: TObject);
var
  X: Double;
  S: string;
begin
  if FLastEnter <> '' then
    begin
      X := GetDisplayValue;
      S := FloatToStr(FValBuffer * (X / 100));
      if (Length(S) > 14) then
        S := Copy(S, 1, 14);
      PanDisplay.Caption := S;
      FLastEnter := S;
    end;
end;

procedure TFrmActCurrencyEdit.BtnCancelClick(Sender: TObject);
begin
  Close;
end;

procedure TFrmActCurrencyEdit.BtnOKClick(Sender: TObject);
begin
  if PanDisplay.Caption <> 'E' then
    begin
      FOnwerEdt.Text := PanDisplay.Caption;
      FOnwerEdt.SetFocus;
      FOnwerEdt.SelectAll;
    end;
  Close;
end;

procedure TFrmActCurrencyEdit.PanMouseDown(Sender: TObject;
  Button: TMouseButton; Shift: TShiftState; X, Y: Integer);
begin
  FLastX := X;
  FLastY := Y;
end;

procedure TFrmActCurrencyEdit.PanMouseMove(Sender: TObject;
  Shift: TShiftState; X, Y: Integer);
begin
  if ssLeft in Shift then
    begin
      Left := Left + (X - FLastX);
      Top := Top + (Y - FLastY);
    end;
end;

procedure Register;
begin
  RegisterComponents('Active Standard', [TActCurrencyEdit]);
end;

end.

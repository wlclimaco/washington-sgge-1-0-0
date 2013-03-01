{*******************************************************}
{                                                       }
{         Copyright (c) FINANCES Ltd                    }
{         2003   ver. 1                                 }
{                                                       }
{         www.finances-ltd.narod.ru                     }
{         finances-ltd@narod.ru                         }
{                                                       }
{*******************************************************}

{
Full control over windows Balloon

Properties:
    AutoCloseMode [acNone, acAction, acTimeOut, acAutoCalc]
    BackGroundColor
    TextColor
    BalloonIcon [bNoIcon, bInfo, bWarning, bError]
    TimeOut
    Title
    Prompt
    Font
    Showing

Methods:
    Show(X, Y : integer)
    Show(Control : TWinControl)
    Close

Events:
    BeforShow
    AfterShow
    AfterClose
    OnClick
}

unit ActHintBalloon;

interface

uses Windows, SysUtils, Messages, Classes, Graphics, Controls, ExtCtrls, Forms;

const
  TOOLTIPS_CLASS = 'tooltips_class32';
    TTS_ALWAYSTIP = $01;
    TTS_NOPREFIX = $02;
    TTS_BALLOON = $40;
    TTF_SUBCLASS = $0010;
    TTF_TRANSPARENT = $0100;
    TTF_CENTERTIP = $0002;
    TTM_ADDTOOL = $0400 + 50;
    TTM_SETTITLE = ($0400 + 32);
    ICC_WIN95_CLASSES = $000000FF;
    TTF_TRACK               = $0020;
    TTM_DELTOOLA             = $0400 + 5;
    TTM_TRACKACTIVATE        = $0400 + 17;  // wParam = TRUE/FALSE start end  lparam = LPTOOLINFO
    TTM_SETTIPBKCOLOR        = $0400 + 19;
    TTM_SETTIPTEXTCOLOR      = $0400 + 20;
    TTM_TRACKPOSITION        = $0400 + 18;  // lParam = dwPos
    TTM_SETMAXTIPWIDTH       = $0400 + 24;  ///D
    TTF_IDISHWND             = $0001;


  type
  TOOLINFO = packed record
    cbSize: Integer;
    uFlags: Integer;
    hwnd: THandle;
    uId: Integer;
    rect: TRect;
    hinst: THandle;
    lpszText: PWideChar;
    lParam: Integer;
  end;

  TAutoCloseMode = (acNone, acAction, acTimeOut, acAutoCalc);
  TBalloonIconType = (bNoIcon, bInfo, bWarning, bError);

  TActHintBalloon = class(TComponent)
  private
    FBalloonHandle : THandle;
    POldWndProc, PNewWndproc: Pointer;
    FToolInfo: TOOLINFO;
    FShowing : boolean;
    FBackGroundColor: TColor;
    FTextColor: TColor;
    FBalloonIcon : TBalloonIconType;
    FTitleText : string;
    FMessageText : TStrings;
    FFont : TFont;
    FAutoCloseMode: TAutoCloseMode;
    FTimeOut: Integer;
    FTimer: TTimer;
    OldOnActiveControlChange: TNotifyEvent;
    OldOnActiveFormChange: TNotifyEvent;
    FOnClick: TNotifyEvent;
    FAfterShow: TNotifyEvent;
    FBeforeShow: TNotifyEvent;
    FAfterClose: TNotifyEvent;
    FMaxTipWidth : integer;
    procedure FCreateBalloonWnd;
    procedure FNewWinProc (var Msg: TMessage);
    procedure FSetTitleText(Value : string);
    procedure FSetMessageText(Value : TStrings);
    procedure FSetOnClick(Value : TNotifyEvent);
    procedure FSetFont(Value : TFont);
    procedure FApplyFont;
    procedure FSetMaxTipWidth(Value : integer);
    procedure InitializeBalloon;
    procedure OnTimer(sender : TObject);
    procedure OnActiveControlChange(sender : TObject);
    procedure OnActiveFormChange(sender : TObject);
  public
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
    procedure Show(X, Y: integer); overload;
    procedure Show(Control: TControl); overload;
    procedure Close;
    property Showing: boolean read FShowing;
  published
    property AutoCloseMode: TAutoCloseMode read FAutoCloseMode write FAutoCloseMode default acNone;
    property BackGroundColor: TColor read FBackGroundColor write FBackGroundColor default clInfoBk;
    property BalloonIcon: TBalloonIconType read FBalloonIcon write FBalloonIcon default BInfo;
    property TimeOut: Integer read FTimeOut write FTimeOut default 5000;
    property Title: string read FTitleText write FSetTitleText;
    property Prompt: TStrings read FMessageText write FSetMessageText;
    property Font: TFont read FFont write FSetFont;
    property MaxTipWidth : integer read FMaxTipWidth write FSetMaxTipWidth default -1; ///D if -1 then control will bi arranged automatically ...
    property AfterShow: TNotifyEvent read FAfterShow write FAfterShow;
    property BeforeShow: TNotifyEvent read FBeforeShow write FBeforeShow;
    property AfterClose: TNotifyEvent read FAfterClose write FAfterClose;
    property OnClick: TNotifyEvent read FOnClick write FSetOnClick;
 end;

procedure Register;

var
  Balloon_Control: TActHintBalloon;
  BalloonKeyboardHookHandle,
  BalloonMouseHookHandle: HHook;

implementation

{$WARN SYMBOL_DEPRECATED OFF}

{Routines}

function Balloon_Key_HooK(code: Integer; wparam: WPARAM; lparam: LPARAM): LRESULT stdcall;
begin
  if (Code = 0) and ((lParam and $40000000) = 0) then
  begin
    if BalloonKeyboardHookHandle <> 0 then
      UnhookWindowsHookEx(BalloonKeyboardHookHandle);
    if BalloonMouseHookHandle <> 0 then
      UnhookWindowsHookEx(BalloonMouseHookHandle);
    BalloonKeyboardHookHandle := 0;
    BalloonMouseHookHandle := 0;
    Balloon_Control.Close;
    Result := 0;
 end
 else Result := CallNextHookEx(BalloonKeyboardHookHandle, Code, wParam, lParam);
end;

function Balloon_Mouse_HookProc(Code:integer; wParam: WPARAM; lParam: LPARAM): LRESULT stdcall;
begin
  if not (csDestroying in Balloon_Control.ComponentState) and
        ((wParam = WM_LBUTTONDOWN) or (wParam = WM_LBUTTONDBLCLK) or
         (wParam = WM_RBUTTONDOWN) or (wParam = WM_RBUTTONDBLCLK) or
         (wParam = WM_MBUTTONDOWN) or (wParam = WM_MBUTTONDBLCLK) or
         (wParam = WM_MOUSEWHEEL)) or (wParam = WM_MOUSEMOVE) then
  begin
    if BalloonKeyboardHookHandle <> 0 then
      UnhookWindowsHookEx(BalloonKeyboardHookHandle);
    if BalloonMouseHookHandle <> 0 then
      UnhookWindowsHookEx(BalloonMouseHookHandle);
    BalloonKeyboardHookHandle := 0;
    BalloonMouseHookHandle := 0;
    Balloon_Control.Close;
    Result := 0;
  end
  else
   Result := CallNextHookEx(BalloonMouseHookHandle, Code, wParam, lParam);
end;

function StrToWChar(const InStr: String): PWideChar;
var
  buff: PWideChar;
begin
  GetMem(Buff, 512);
  StringToWideChar(InStr, PWideChar(Buff), 256);
  Result := PWideChar(Buff);
end;

{ TAPIBaloon }

constructor TActHintBalloon.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);

  FShowing := false;
  FBackGroundColor := clInfoBk;
  FTextColor :=       clBtnText;
  FBalloonIcon := BInfo;

  if not (csDesigning in ComponentState) then
  begin
    FTimer := TTimer.Create(Self.Owner);
    FTimer.Interval := 2000;
    FTimer.OnTimer := OnTimer;
    FTimer.Enabled := false;
  end;

  FTimeOut := 5000;
  Balloon_Control := Self;

  //font
  FFont := TFont.Create;
  FFont.Style := [];
  FFont.Charset := DEFAULT_CHARSET;
  FFont.Size := 8;
  FFont.Name := 'Tahoma';

  //creating control
  FCreateBalloonWnd;
  if not (csDesigning in ComponentState) then
    FTitleText := Application.Title;
  FMessageText := TStringList.Create;
  FMaxTipWidth := -1;
//  if FBalloonHandle = 0 then showmessage(SysErrorMessage(GetLastError));
end;

destructor TActHintBalloon.Destroy;
begin
  if FBalloonHandle <> 0 then DestroyWindow(FBalloonHandle);
  if Assigned (PNewWndProc) then
    FreeObjectInstance(PNewWndProc);
  if Assigned(FFont) then
    FFont.Free;
  FMessageText.Free;
  inherited Destroy;
end;

procedure TActHintBalloon.FNewWinProc (var Msg: TMessage);
begin
  Msg.Result := 0;
  case Msg.Msg of
      WM_LBUTTONDOWN : begin
           if Showing then begin
             if Assigned(FOnClick) then FOnClick(self);
             Close;
           end;
//             SendMessage(FBalloonHandle, TTM_TRACKACTIVATE, 0, integer(@FtoolInfo));
           end;
      TTM_TRACKACTIVATE :
           case Msg.WParam of
           0 : begin
                FShowing := false;
                if Assigned(FToolInfo.lpszText) then FreeMem(FToolInfo.lpszText);
               end;
           1:  FShowing := true;
           end
  end;
  Msg.Result :=  CallWindowProc(POldWndProc, FBalloonHandle, Msg.Msg, Msg.wParam, Msg.lParam);
end;

procedure TActHintBalloon.FSetMessageText(Value: TStrings);
begin
  if Trim(Value.Text) <> '' then FMessageText.Assign(Value);
end;

procedure TActHintBalloon.FSetTitleText(Value: string);
begin
  if Value <> '' then FTitleText := Value;
end;

procedure TActHintBalloon.FSetOnClick(Value: TNotifyEvent);
begin
  if Assigned(Value) then FOnClick := Value;
end;

procedure TActHintBalloon.FSetFont(Value: TFont);
begin
  if Assigned(Value) then FFont.Assign(Value);
end;

procedure TActHintBalloon.Close;
begin
  if FShowing then begin
    FTimer.Enabled := False;
    SendMessage(FBalloonHandle, TTM_TRACKACTIVATE, 0, integer(@FtoolInfo));
    if (csDestroying in ComponentState) then Exit;
    if Assigned(FAfterClose) then FAfterClose(Self);
    if FAutoCloseMode = acAction then begin
      Screen.OnActiveControlChange := OldOnActiveControlChange;
      Screen.OnActiveFormChange := OldOnActiveFormChange;
    end;
  end;
end;

procedure TActHintBalloon.InitializeBalloon;
begin
  if FShowing then Close;
  FToolInfo.lpszText := StrToWChar(Trim(FMessageText.Text));
  SendMessage(FBalloonHandle, TTM_DELTOOLA, 0, lparam(@FToolInfo));
  SendMessage(FBalloonHandle, TTM_ADDTOOL, 0, lparam(@FToolInfo));
  SendMessage(FBalloonHandle, TTM_SETTIPBKCOLOR, ColorToRgb(FbackGroundColor), 0);
  SendMessage(FBalloonHandle, TTM_SETTITLE, wparam(Ord(FBalloonIcon) mod 4), lparam(PChar(FTitleText)));
  FApplyFont;
  SendMessage(FBalloonHandle, TTM_SETTIPTEXTCOLOR, ColorToRgb(FTextColor), 0);
  case FAutoCloseMode of
    acAction: FTimer.Interval := 2000;
    acTimeOut: if FTimeOut > 500 then
                 FTimer.Interval := FTimeOut
               else FTimer.Interval := 500;
    acAutoCalc: begin
                  FTimer.Interval := (Length(FTitleText) + Length(FMessageText.Text)) * 100;
                  if FTimer.Interval < 2000 then
                    FTimer.Interval := 2000;
                end;
  end;
  FTimer.Enabled := FAutoCloseMode <> acNone;
end;

procedure TActHintBalloon.Show(Control: TControl);
begin
  if Assigned(FBeforeShow) then FBeforeShow(Self);

  if (Control is TWinControl) then
    GetWindowRect((Control as TWinControl).Handle, FToolInfo.Rect)
  else begin
    GetWindowRect((Control.Parent as TWinControl).Handle, FToolInfo.Rect);
    with FToolInfo.Rect do begin
      Left := Left + Control.Left + GetSystemMetrics(SM_CXFIXEDFRAME);
      Top := Top + Control.Top +
             GetSystemMetrics(SM_CYCAPTION) + GetSystemMetrics(SM_CYFIXEDFRAME);
      Right := Left + Control.Width;
      Bottom := Top + Control.Height;
    end;
  end;

  SendMessage(FBalloonHandle, TTM_TRACKPOSITION, 0,
              lparam(MAKELONG(FToolInfo.Rect.Left + (FToolInfo.Rect.Right - FToolInfo.Rect.Left) div 2,
                              FToolInfo.Rect.Top + (FToolInfo.Rect.Bottom - FToolInfo.Rect.Top) div 2)));
  InitializeBalloon;
  SendMessage(FBalloonHandle, TTM_SETMAXTIPWIDTH, 0, LPARAM(FMaxTipWidth) ); ///D
  SendMessage(FBalloonHandle, TTM_TRACKACTIVATE, 1, lparam(@FToolInfo));
  if Assigned(FAfterShow) then FAfterShow(Self);
end;

procedure TActHintBalloon.Show(X, Y: integer);
begin
  if Assigned(FBeforeShow) then FBeforeShow(Self);
  FToolInfo.rect.Left := X;
  FToolInfo.rect.Top := Y;
  SendMessage(FBalloonHandle, TTM_TRACKPOSITION, 0, lparam(MAKELONG(X, Y)));
  InitializeBalloon;
  SendMessage(FBalloonHandle, TTM_SETMAXTIPWIDTH, 0, LPARAM(FMaxTipWidth) );  ///D
  SendMessage(FBalloonHandle, TTM_TRACKACTIVATE, 1, lparam(@FToolInfo));
  if Assigned(FAfterShow) then FAfterShow(Self);
end;


procedure TActHintBalloon.FCreateBalloonWnd;
begin
  //creating window
  FBalloonHandle := CreateWindow(TOOLTIPS_CLASS, nil,
    WS_POPUP or TTS_NOPREFIX or TTS_BALLOON or TTS_ALWAYSTIP or TTF_IDISHWND,
    0, 0, 0, 0, 0, 0, HInstance, nil);
  if FBalloonHandle <> 0 then begin
    //registering new windows procedure
    PNewWndProc := MakeObjectInstance(FNewWinProc);
    POldWndProc := Pointer (SetWindowLong(FBalloonHandle, GWL_WNDPROC, Longint(PNewWndProc)));

    SetWindowPos(FBalloonHandle, HWND_TOPMOST, 0, 0, 0, 0,
        SWP_NOACTIVATE or SWP_NOMOVE or SWP_NOSIZE);
    FToolInfo.uFlags := TTF_TRACK {or TTF_CENTERTIP } {or TTF_TRANSPARENT  or //no mouse events are recieved by window} ;
    FToolInfo.cbSize := SizeOf(FToolInfo);
  end
end;

procedure TActHintBalloon.FApplyFont;
 var hFont : integer;
     FntBold, FntItalic, FntUnderLine, FntStrikeOut, FntCharset, FntSize : DWORD;
     FntName : PChar;
begin
  if fsBold in FFont.Style then FntBold := 700 else FntBold := 400;
  if fsItalic in FFont.Style then FntItalic := 1 else FntItalic := 0;
  if fsUnderLine in FFont.Style then FntUnderLine := 1 else FntUnderLine := 0;
  if fsStrikeOut in FFont.Style then FntStrikeOut := 1 else FntStrikeOut := 0;
  FntCharset := FFont.Charset;
  FntSize := FFont.Height;
  FntName := PChar(FFont.Name);
  FTextColor := FFont.Color;
  hFont := CreateFont(FntSize, 0, 0, 0, FntBold, FntItalic, FntUnderLine, FntStrikeOut, FntCharset,
                      OUT_DEFAULT_PRECIS, CLIP_DEFAULT_PRECIS, DEFAULT_QUALITY,
                      DEFAULT_PITCH or FF_DONTCARE, FntName);

  SendMessage(FBalloonHandle, WM_SETFONT, hFont, 0);
end;

procedure TActHintBalloon.OnTimer(Sender: TObject);
begin
  FTimer.Enabled := False;
  if (AutoCloseMode = acAction) and
     not (CSDesigning in ComponentState) then begin
    BalloonKeyboardHookHandle := SetWindowsHookEx(WH_KEYBOARD, Balloon_Key_HooK, hInstance, GetCurrentThreadID);
    BalloonMouseHookHandle := SetWindowsHookEx(WH_MOUSE, Balloon_Mouse_HookProc, HInstance, GetCurrentThreadID);
    OldOnActiveControlChange := Screen.OnActiveControlChange;
    Screen.OnActiveControlChange := OnActiveControlChange;
    OldOnActiveFormChange := Screen.OnActiveFormChange;
    Screen.OnActiveFormChange := OnActiveFormChange;
  end
  else Close;
end;

procedure TActHintBalloon.OnActiveControlChange(sender : TObject);
begin
  Close;
  if not (csDestroying in ComponentState) and Assigned(OldOnActiveControlChange) then
    OldOnActiveControlChange(sender);
end;

procedure TActHintBalloon.OnActiveFormChange(sender : TObject);
begin
  Close;
  if not (csDestroying in ComponentState) and Assigned(OldOnActiveFormChange) then
    OnActiveFormChange(sender);
end;

procedure Register;
begin
  RegisterComponents('Active Controls', [TActHintBalloon]);
end;

procedure TActHintBalloon.FSetMaxTipWidth(Value: integer);
begin
  if FMaxTipWidth <> Value then FMaxTipWidth := Value;
end;

end.

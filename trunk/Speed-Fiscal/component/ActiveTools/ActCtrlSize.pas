unit ActCtrlSize;

{$D-}    { debug information            }
{$I+}    { I/O checking                 }
{$L-}    { local symbol information     }
{$R+}    { range checking               }
{$S+}    { stack overflow checking      }
{$Y-}    { symbol reference information }

interface

uses
  {$IFDEF VER80}
  WinTypes, WinProcs,
  {$ELSE}
  Windows,
  {$ENDIF}
  Forms, Messages, SysUtils, Classes, Graphics, Controls, Dialogs;

type
  TGrabHandlePosition = (ghTopLeft, ghTopMiddle, ghTopRight, ghRightMiddle,
                         ghBottomRight, ghBottomMiddle, ghBottomLeft, ghLeftMiddle);
  TPositiveInteger = 0..MaxInt;
  TGrabEvent = procedure(Sender: TObject; sx,sy: integer) of object;

  TGrabHandle = class(TCustomControl)
  private
     FCaptured: boolean;
     FControl: TControl;
     FHandlePosition: TGrabHandlePosition;
     FOnDrag: TGrabEvent;
     FOnEndDrag: TGrabEvent;
     FOnStartDrag: TGrabEvent;
     FVisible: boolean;
     procedure SetControl(const c: TControl);
     procedure SetHandlePosition(const p: TGrabHandlePosition);
     procedure SetPosition;
     procedure SetVisible(const v: boolean);
  protected
     procedure Notification(AComponent: TComponent; AOperation: TOperation); override;
     procedure WmMouseDown(var msg: TWmLButtonDown); message WM_LBUTTONDOWN;
     procedure WmMouseMove(var msg: TWmMouseMove); message WM_MOUSEMOVE;
     procedure WmMouseUp(var msg: TWmLButtonUp); message WM_LBUTTONUP;
  public
     constructor Create(AOwner: TComponent); override;
     procedure ResetPosition;
     property Control: TControl read FControl write SetControl;
     property HandlePosition: TGrabHandlePosition read FHandlePosition write SetHandlePosition;
     property Visible: boolean read FVisible write SetVisible;
     property OnDrag: TGrabEvent read FOnDrag write FOnDrag;
     property OnEndDrag: TGrabEvent read FOnEndDrag write FOnEndDrag;
     property OnStartDrag: TGrabEvent read FOnStartDrag write FOnStartDrag;
  end;

  TActControlSize = class(TComponent)
  private
     FAllowMove: boolean;
     FAllowResize: boolean;
     FCanvas: TCanvas;
     FControl: TControl;
     FGrabHandles: array [TGrabHandlePosition] of TGrabHandle;
     FLastSizeRect: TRect;
     FMinimumMove: TPositiveInteger;
     FMoved: boolean;
     FMoving: boolean;
     FNewWndProc: Pointer;
     FOldWndProc: Pointer;
     FOnEndMove: TNotifyEvent;
     FOnEndSize: TNotifyEvent;
     FOnStartMove: TNotifyEvent;
     FOnStartSize: TNotifyEvent;
     FParentControl: TWinControl;
     FParentRect: TRect;
     FStartMovePos: TPoint;

     procedure DoSubclass;
     procedure DrawMoveRect(const sx,sy: integer);
     procedure DrawSizeRect(Sender: TObject; sx,sy: integer);
     procedure EndMoveControl(const x,y: Smallint);
     procedure FreeCanvas;
     procedure GetCanvas;
     function  GetControlBoundsRect(Sender: TObject; sx,sy: integer): TRect;
     procedure MoveControl(const x,y: Smallint);
     procedure OnDragHandle(Sender: TObject; sx,sy: integer);
     procedure OnEndDragHandle(Sender: TObject; sx,sy: integer);
     procedure OnStartDragHandle(Sender: TObject; sx,sy: integer);
     procedure SetAllowMove(const v: boolean);
     procedure SetAllowResize(const v: boolean);
     procedure SetControl(const c: TControl);
     procedure SetVisible(const v: boolean);
     procedure StartMoveControl(const x,y: Smallint);
     procedure UnDoSubclass;
  protected
     procedure Notification(AComponent: TComponent; AOperation: TOperation); override;
     procedure SubclassProc(var msg: TMessage); virtual;
     procedure ReSetHandles;
  public
     constructor Create(AOwner: TComponent); override;
  published
     property AllowMove: boolean read FAllowMove write SetAllowMove default true;
     property AllowResize: boolean read FAllowResize write SetAllowResize default true;
     property Control: TControl read FControl write SetControl;
     property MinimumMove: TPositiveInteger read FMinimumMove write FMinimumMove default 3;
     property OnEndMove: TNotifyEvent read FOnEndMove write FOnEndMove;
     property OnEndSize: TNotifyEvent read FOnEndSize write FOnEndSize;
     property OnStartMove: TNotifyEvent read FOnStartMove write FOnStartMove;
     property OnStartSize: TNotifyEvent read FOnStartSize write FOnStartSize;
  end;

procedure Register;

implementation

{$R *.R32}

procedure Register;
begin
  RegisterComponents('Active Controls', [TActControlSize]);
end;

function NormaliseRect(const rct: TRect): TRect;
begin
  result := rct;
  if rct.Left > rct.Right then
  begin
    Result.Left := rct.Right;
    Result.Right := rct.Left;
  end;
  if rct.Top > rct.Bottom then begin
    Result.Top := rct.Bottom;
    Result.Bottom := rct.Top;
  end;
end;

function FindParent(const comp: TComponent): TWinControl;
   function UpOne(const comp: TComponent): TComponent;
   begin
      if comp is TControl then
         Result := TControl(comp).Parent
      else
         Result := comp.Owner;
   end;
var
   o: TComponent;
begin
   Result := nil;
   if Assigned(comp) then begin
      o := UpOne(comp);
      while Assigned(o) and not (o is TWinControl) do
         o := UpOne(o);
      if o is TWinControl then
         Result := TWinControl(o);
   end;
end;

{ TGrabHandle }

const
  HandleCursors: array [TGrabHandlePosition] of TCursor = (crSizeNWSE, crSizeNS,
                                                           crSizeNESW, crSizeWE,
                                                           crSizeNWSE, crSizeNS,
                                                           crSizeNESW, crSizeWE);
constructor TGrabHandle.Create(AOwner: TComponent);
begin
   inherited Create(AOwner);
   Color := clBlack;
   Cursor := HandleCursors[FHandlePosition];
   Width := 5;
   Height := 5;
   inherited Visible := false;
   FVisible := false;
   FCaptured := false;
   Parent := nil;
end;

procedure TGrabHandle.Notification(AComponent: TComponent; AOperation: TOperation);
begin
   inherited Notification(AComponent, AOperation);
   if (AComponent = FControl) and (AOperation = opRemove) then
      Control := nil;
end;

procedure TGrabHandle.ResetPosition;
begin
   SetPosition;
end;

procedure TGrabHandle.SetControl(const c: TControl);
begin
   if csDestroying in ComponentState then exit;
   if FControl <> c then begin
      FControl := c;
      if Assigned(FControl) then begin
         Parent := FindParent(FControl);
         HandleNeeded;
         SetPosition;
      end else begin
         DestroyWindowHandle;
         Parent := nil;
      end;
   end;
end;

procedure TGrabHandle.SetHandlePosition(const p: TGrabHandlePosition);
begin
   if FHandlePosition <> p then begin
      FHandlePosition := p;
      Cursor := HandleCursors[FHandlePosition];
      SetPosition;
   end;
end;

procedure TGrabHandle.SetPosition;
var
   x,y: integer;
begin
   if Assigned(FControl) then begin
      with FControl do
        case FHandlePosition of
          ghTopMiddle:     begin
                           x := Left + Width div 2;
                           y := Top;
                           end;
          ghTopRight:      begin
                           x := Left + Width;
                           y := Top;
                           end;
          ghRightMiddle:   begin
                           x := Left + Width;
                           y := Top + Height div 2;
                           end;
          ghBottomRight:   begin
                           x := Left + Width;
                           y := Top + Height;
                           end;
          ghBottomMiddle:  begin
                           x := Left + Width div 2;
                           y := Top + Height;
                           end;
          ghBottomLeft:    begin
                           x := Left;
                           y := Top + Height;
                           end;
          ghLeftMiddle:    begin
                           x := Left;
                           y := Top + Height div 2;
                           end;
          else             begin
                           x := Left;
                           y := Top;
                           end;
         end;
      Left := x - Width div 2;
      Top := y - Height div 2;
      inherited Visible := FVisible;
   end else
      inherited Visible := false;
end;

procedure TGrabHandle.SetVisible(const v: boolean);
begin
   if FVisible <> v then begin
      FVisible := v;
      inherited Visible := FVisible and Assigned(FControl);
   end;
end;

procedure TGrabHandle.WmMouseDown(var msg: TWmLButtonDown);
var
   pt: TPoint;
begin
   if not FCaptured and ((MK_LBUTTON and msg.keys) <> 0) then begin
      SetCaptureControl(Self);
      FCaptured := true;
      if Assigned(FOnStartDrag) then begin
         pt := ClientToScreen(Point(msg.xpos,msg.ypos));
         FOnStartDrag(Self,pt.x,pt.y);
      end;
   end;
end;

procedure TGrabHandle.WmMouseMove(var msg: TWmMouseMove);
var
   pt: TPoint;
begin
   inherited;
   if FCaptured and Assigned(FOnDrag) then begin
      pt := ClientToScreen(Point(msg.xpos,msg.ypos));
      FOnDrag(Self,pt.x,pt.y);
   end;
end;

procedure TGrabHandle.WmMouseUp(var msg: TWmLButtonUp);
var
   pt: TPoint;
begin
   inherited;
   if FCaptured then begin
      pt := ClientToScreen(Point(msg.xpos,msg.ypos));
      if (MK_LBUTTON and msg.keys) = 0 then begin
         SetCaptureControl(nil);
         FCaptured := false;
         if Assigned(FOnEndDrag) then
            FOnEndDrag(Self,pt.x,pt.y);
      end;
   end;
end;

{ TActControlSize }

constructor TActControlSize.Create(AOwner: TComponent);
var
   h: TGrabHandlePosition;
begin
   inherited Create(AOwner);
   FControl := nil;
   FParentControl := nil;
   FNewWndProc := nil;
   FOldWndProc := nil;
   FAllowResize := true;
   FAllowMove := true;
   FStartMovePos := Point(-1,-1);
   FMinimumMove := 3;
   if not (csDesigning in ComponentState) then
      for h := low(TGrabHandlePosition) to High(TGrabHandlePosition) do begin
         FGrabHandles[h] := TGrabHandle.Create(Self);
         with FGrabHandles[h] do begin
            HandlePosition := h;
            OnStartDrag := Self.OnStartDragHandle;
            OnDrag := Self.OnDragHandle;
            OnEndDrag := Self.OnEndDragHandle;
            Control := Self.FControl;
            Visible := Self.FAllowResize;
         end;
      end
   else
      for h := low(TGrabHandlePosition) to High(TGrabHandlePosition) do
         FGrabHandles[h] := nil;
end;

procedure TActControlSize.DoSubclass;
begin
   if not (csDesigning in ComponentState) and (FControl is TWinControl) then begin
      UndoSubclass;
      FNewWndProc := MakeObjectInstance(SubclassProc);
      FOldWndProc := Pointer(GetWindowLong(TWinControl(FControl).Handle, GWL_WNDPROC));
      SetWindowLong(TWinControl(FControl).Handle, GWL_WNDPROC, Longint(FNewWndProc));
   end;
end;

procedure TActControlSize.DrawMoveRect(const sx,sy: integer);
var
   l,t: integer;
   rct: TRect;
begin
   if not Assigned(FControl) or
      not Assigned(FCanvas) then exit;

   l := FControl.Left + sx - FStartMovePos.x;
   t := FControl.Top + sy - FStartMovePos.y;
   IntersectRect(rct,Rect(l,t,l+FControl.Width-1,t+FControl.Height-1),FParentRect);
   with FParentControl, rct do begin
      TopLeft := ClientToScreen(TopLeft);
      BottomRight := ClientToScreen(BottomRight);
   end;
   with FCanvas do begin
      with FLastSizeRect do
         Rectangle(Left,Top,Right,Bottom);
      with rct do
         Rectangle(Left,Top,Right,Bottom);
   end;
   FLastSizeRect := rct;
end;

procedure TActControlSize.DrawSizeRect(Sender: TObject; sx,sy: integer);
var
   rct: TRect;
begin
   if not Assigned(FControl) or
      not Assigned(FCanvas) then exit;

   IntersectRect(rct,NormaliseRect(GetControlBoundsRect(sender,sx,sy)),FParentRect);
   with FParentControl, rct do begin
      TopLeft := ClientToScreen(TopLeft);
      BottomRight := ClientToScreen(BottomRight);
   end;
   with FCanvas do begin
      with FLastSizeRect do
         Rectangle(Left,Top,Right,Bottom);
      with rct do
         Rectangle(Left,Top,Right,Bottom);
   end;
   FLastSizeRect := rct;
end;

procedure TActControlSize.EndMoveControl(const x,y: Smallint);
begin
  if FMoving and FMoved then begin
    DrawMoveRect(x,y);
    FLastSizeRect := rect(-1,-1,-1,-1);
    DrawMoveRect(x,y);
    FreeCanvas;
    FControl.Left := FControl.Left + x - FStartMovePos.x;
    FControl.Top := FControl.Top + y - FStartMovePos.y;
    ReSetHandles;
    if Assigned(FOnEndMove) then
      FOnEndMove(self);
  end;
  SetVisible(FAllowResize);
  FMoving := false;
  ReleaseCapture;
  FStartMovePos := Point(-1,-1);
end;

procedure TActControlSize.FreeCanvas;
var
  h: THandle;
begin
  if Assigned(FCanvas) then begin
    h := FCanvas.Handle;
    FCanvas.Handle := 0;
    ReleaseDC(0,h);
    FCanvas.Free;
    FCanvas := nil;
  end;
end;

procedure TActControlSize.GetCanvas;
var
   h: THandle;
begin
   h := 0;
   FCanvas := TCanvas.Create;
   with FCanvas do
   try
      h := GetDC(0);
      Handle := h;
      with Brush do begin
         Color := clBlack;
         Style := bsClear;
      end;
      with Pen do begin
         Color := clBlack;
         Style := psSolid;
         Mode := pmNot;
         Width := 2;
      end;
   except
      Handle := 0;
      if h <> 0 then
         ReleaseDC(0,h);
      Free;
      FCanvas := nil;
   end;
end;

function TActControlSize.GetControlBoundsRect(Sender: TObject; sx,sy: integer): TRect;
var
   pt: TPoint;
begin
   pt := FParentControl.ScreenToClient(Point(sx,sy));
   Result := FControl.BoundsRect;
   with Result do
      case TGrabHandle(Sender).FHandlePosition of
          ghTopLeft:       begin
                           Left := pt.x;
                           Top := pt.y;
                           end;
          ghTopMiddle:     begin
                           Top := pt.y;
                           end;
          ghTopRight:      begin
                           Right := pt.x;
                           Top := pt.y;
                           end;
          ghRightMiddle:   begin
                           Right := pt.x;
                           end;
          ghBottomRight:   begin
                           Right := pt.x;
                           Bottom := pt.y;
                           end;
          ghBottomMiddle:  begin
                           Bottom := pt.y;
                           end;
          ghBottomLeft:    begin
                           Left := pt.x;
                           Bottom := pt.y;
                           end;
          ghLeftMiddle:    begin
                           Left := pt.x;
                           end;
      end;
end;

procedure TActControlSize.MoveControl(const x,y: Smallint);
begin
   if not FMoving then exit;
   if not FMoved then begin
      FMoved := (abs(x - FStartMovePos.x) >= FMinimumMove) or
                (abs(y - FStartMovePos.y) >= FMinimumMove);
      if FMoved then begin
         GetCanvas;
         if Assigned(FOnStartMove) then
            FOnStartMove(self);
      end;
   end;
   if FMoved then
      DrawMoveRect(x,y);
end;

procedure TActControlSize.Notification(AComponent: TComponent; AOperation: TOperation);
begin
   inherited Notification(AComponent, AOperation);
   if (AComponent = FControl) and (AOperation = opRemove) then
      Control := nil;
end;

procedure TActControlSize.OnDragHandle(Sender: TObject; sx,sy: integer);
begin
   DrawSizeRect(Sender,sx,sy);
end;

procedure TActControlSize.OnEndDragHandle(Sender: TObject; sx,sy: integer);
var
   rct: TRect;
begin
   if not Assigned(FControl) then exit;

   DrawSizeRect(Sender,sx,sy);
   FLastSizeRect := rect(-1,-1,-1,-1);
   DrawSizeRect(Sender,sx,sy);
   FreeCanvas;

   IntersectRect(rct,NormaliseRect(GetControlBoundsRect(sender,sx,sy)),FParentRect);
   with rct do
      FControl.SetBounds(Left,Top,Right-Left,Bottom-Top);

   ReSetHandles;
   SetVisible(true);  { must have been visible to start dragging }
   if Assigned(FOnEndSize) then
      FOnEndSize(Self);
end;

procedure TActControlSize.OnStartDragHandle(Sender: TObject; sx,sy: integer);
begin
   if Assigned(FOnStartSize) then
      FOnStartSize(Self);

   SetVisible(false);
{   if Assigned(FControl.Parent) then}
      FControl.Parent.Update;  { to repaint under invisible GrabHandles }
   FControl.Update;            { to repaint under invisible GrabHandles }

   FParentControl := FindParent(FControl);
   if Assigned(FParentControl) then
      FParentRect := FParentControl.ClientRect
   else
      FParentRect := rect(0,0,0,0);
   GetCanvas;
   FLastSizeRect := rect(-1,-1,-1,-1);
   DrawSizeRect(Sender,sx,sy);
end;

procedure TActControlSize.ReSetHandles;
var
   h: TGrabHandlePosition;
begin
   for h := low(TGrabHandlePosition) to High(TGrabHandlePosition) do begin
      if Assigned(FGrabHandles[h]) then
         FGrabHandles[h].ResetPosition;
   end;
end;

procedure TActControlSize.SetAllowMove(const v: boolean);
begin
   if FAllowMove <> v then begin
      FAllowMove := v;
      if not (csDesigning in ComponentState) then begin
         if FAllowMove then
            DoSubclass
         else
            UnDoSubclass;
      end;
   end;
end;

procedure TActControlSize.SetAllowResize(const v: boolean);
begin
   if FAllowResize <> v then begin
      FAllowResize := v;
      SetVisible(FAllowResize);
   end;
end;

procedure TActControlSize.SetControl(const c: TControl);
var
   h: TGrabHandlePosition;
begin
   if csDestroying in ComponentState then exit;
   if FControl <> c then begin
      UnDoSubclass;
      SetVisible(false);
      FControl := c;
      for h := low(TGrabHandlePosition) to high(TGrabHandlePosition) do
         if Assigned(FGrabHandles[h]) then
            FGrabHandles[h].Control := FControl;
      DoSubclass;
      SetVisible(FAllowResize);
   end;
end;

procedure TActControlSize.SetVisible(const v: boolean);
var
   h: TGrabHandlePosition;
begin
   for h := low(TGrabHandlePosition) to high(TGrabHandlePosition) do
      if Assigned(FGrabHandles[h]) then
         FGrabHandles[h].Visible := v;
end;

procedure TActControlSize.StartMoveControl(const x,y: Smallint);
begin
   if FStartMovePos.X <> -1 then exit;
   FStartMovePos := Point(x,y);
   FParentControl := FindParent(FControl);
   FParentControl := FindParent(FControl);
   if Assigned(FParentControl) then
      FParentRect := FParentControl.ClientRect
   else
      FParentRect := rect(0,0,0,0);
   FLastSizeRect := rect(-1,-1,-1,-1);
   FMoved := false;
   FMoving := true;
   SetCapture(TWinControl(FControl).Handle);
   SetVisible(false);
   if FControl.Parent <> nil then
      FControl.Parent.Update;  { to repaint under invisible GrabHandles }
   FControl.Update;            { to repaint under invisible GrabHandles }
end;

procedure TActControlSize.SubclassProc(var msg: TMessage);
begin
   if FAllowMove and (msg.msg = WM_LBUTTONDOWN) then begin
      {$R-}
      StartMoveControl(LOWORD(msg.lParam),HIWORD(msg.lParam));
      {$R+}
      msg.Result := 1;
   end else if  FAllowMove and (msg.msg = WM_LBUTTONUP) then begin
      {$R-}
      EndMoveControl(LOWORD(msg.lParam),HIWORD(msg.lParam));
      {$R+}
      msg.Result := 1;
   end else if FAllowMove and (msg.msg = WM_MOUSEMOVE) then begin
      {$R-}
      MoveControl(LOWORD(msg.lParam),HIWORD(msg.lParam));
      {$R+}
      msg.Result := 1;
   end else
      msg.Result := CallWindowProc(FOldWndProc, TWinControl(FControl).Handle, Msg.Msg, Msg.wParam, Msg.lParam);
   if msg.Msg = WM_DESTROY then
      UndoSubclass
   else if msg.Msg = WM_WINDOWPOSCHANGED then
      ReSetHandles;
end;

procedure TActControlSize.UndoSubclass;
begin
   if (FControl is TWinControl) and Assigned(FNewWndProc) and Assigned(FOldWndProc) then begin
     SetWindowLong(TWinControl(FControl).Handle, GWL_WNDPROC, Longint(FOldWndProc));
     FreeObjectInstance(FNewWndProc);
     FNewWndProc := nil;
     FOldWndProc := nil;
  end;
end;
 
end.

unit ActButtonEdit;

interface

uses
  Windows, Messages, SysUtils, Classes, Controls, StdCtrls, ExtCtrls, Graphics,
  Buttons, ActCustomEdit, Forms;

type
  TActButtonEdit = class(TActCustomEdit)
  private
    { Private declarations }
    FButton: TSpeedButton;
    FEditorEnabled: Boolean;
    FOnButtonClick: TNotifyEvent;
    FAlignment: TAlignment;
    procedure SetGlyph(Pic: TBitmap);
    function GetGlyph: TBitmap;
    procedure SetNumGlyphs(ANumber: Integer);
    function GetNumGlyphs:Integer;
    procedure SetEditRect;
    procedure WMSize(var Message: TWMSize); message WM_SIZE;
    procedure CMEnter(var Message: TCMGotFocus); message CM_ENTER;
    procedure CMExit(var Message: TCMExit); message CM_EXIT;
    procedure WMPaste(var Message: TWMPaste);  message WM_PASTE;
    procedure WMCut(var Message: TWMCut); message WM_CUT;
    procedure SetAlignment(const Value: TAlignment);
  protected
    { Protected declarations }
    procedure ButtonClick (Sender: TObject); virtual;
    procedure CreateWnd; override;
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
    property Button: TSpeedButton read FButton;
    procedure CreateParams(var Params: TCreateParams); override;
  published
    { Published declarations }
    property Alignment: TAlignment read FAlignment write SetAlignment;
    property EditorEnabled: Boolean read FEditorEnabled write FEditorEnabled default True;
    property Glyph: TBitmap read GetGlyph write SetGlyph;
    property NumGlyphs: Integer read GetNumGlyphs write SetNumGlyphs;
    property OnButtonClick: TNotifyEvent read FOnButtonClick write FOnButtonClick;
  end;

procedure Register;

implementation

{$R *.RES}

procedure Register;
begin
  RegisterComponents('Active Standard', [TActButtonEdit]);
end;

{ TActButtonEdit }

constructor TActButtonEdit.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
  FButton := TSpeedButton.Create(Self);
  FButton.Parent := Self;
  if csDesigning in ComponentState then
    FButton.Visible := True
  else
    FButton.Visible := False;
  FButton.OnClick := ButtonClick;
  FButton.Cursor := crArrow;
  ControlStyle := ControlStyle - [csSetCaption];
  FEditorEnabled := True;
  FButton.Glyph.Handle := LoadBitmap(HInstance, 'BUTTON_OPEN2');
end;

destructor TActButtonEdit.Destroy;
begin
  FButton.Free;
  inherited Destroy;
end;

procedure TActButtonEdit.SetGlyph(Pic: TBitmap);
Begin
  FButton.Glyph.Assign(Pic);
end;

function TActButtonEdit.GetGlyph: TBitmap;
Begin
  Result := FButton.Glyph;
end;

procedure TActButtonEdit.SetNumGlyphs(ANumber: Integer);
Begin
  FButton.NumGlyphs := ANumber;
end;

function TActButtonEdit.GetNumGlyphs: Integer;
begin
  Result := FButton.NumGlyphs;
end;

procedure TActButtonEdit.CreateWnd;
begin
  inherited CreateWnd;
  SetEditRect;
end;

procedure TActButtonEdit.SetEditRect;
var
  Loc: TRect;
begin
  SendMessage(Handle, EM_GETRECT, 0, LongInt(@Loc));
  Loc.Top := 0;
  Loc.Left := 3;
  Loc.Bottom := ClientHeight + 1;
  Loc.Right := ClientWidth - FButton.Width + 1;
  SendMessage(Handle, EM_SETRECTNP, 0, LongInt(@Loc));
  SendMessage(Handle, EM_GETRECT, 0, LongInt(@Loc));
end;

procedure TActButtonEdit.WMSize(var Message: TWMSize);
begin
  inherited;
  if FButton <> nil then
  begin
    FButton.Height := Height - 4;
    FButton.Width := FButton.Height + 4;
    if NewStyleControls and Ctl3D then
      FButton.SetBounds(Width - FButton.Width - 4, 0, FButton.Width, FButton.Height)
    else
      FButton.SetBounds(Width - FButton.Width - 1, 1, FButton.Width, FButton.Height + 2);
    SetEditRect;
  end;
end;

procedure TActButtonEdit.ButtonClick(Sender: TObject);
begin
  if ReadOnly then
    MessageBeep(0)
  else
  if Assigned(FOnButtonClick) then
    FOnButtonClick(Self);
end;

procedure TActButtonEdit.WMPaste(var Message: TWMPaste);
begin
  if not FEditorEnabled or ReadOnly then
    Exit;
  inherited;
end;

procedure TActButtonEdit.WMCut(var Message: TWMPaste);
begin
  if not FEditorEnabled or ReadOnly then
    Exit;
  inherited;
end;

procedure TActButtonEdit.CMExit(var Message: TCMExit);
begin
  SetSelStart(0);
  FButton.Visible := False;
  inherited;
end;

procedure TActButtonEdit.CMEnter(var Message: TCMGotFocus);
begin
  FButton.Visible := True;
  if AutoSelect and not (csLButtonDown in ControlState) then
    SelectAll;
  inherited;
end;

procedure TActButtonEdit.CreateParams(var Params: TCreateParams);
const
  Alignments: array[TAlignment] of Word = (ES_LEFT, ES_RIGHT, ES_CENTER);
begin
  inherited CreateParams(Params);
  Params.Style := Params.Style or ES_MULTILINE or Alignments[FAlignment];
end;

procedure TActButtonEdit.SetAlignment(const Value: TAlignment);
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

end.


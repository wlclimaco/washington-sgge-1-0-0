unit ActNumberEdit;

interface

uses
  Windows, Messages, SysUtils, Classes, Controls, StdCtrls, ExtCtrls,
  ActCustomEdit, ActHintBalloon;

type
  TActNumberEdit = class(TActCustomEdit)
  private
    FValue: Integer;
    ActHint: TActHintBalloon;
    procedure SetValue(const Value: Integer);
    { Private declarations }
  protected
    { Protected declarations }
    function IsValidChar(Key: Char): Boolean; virtual;
    procedure KeyPress(var Key: Char); override;
    procedure Change; override;
    procedure DoExit; override;
  public
    { Public declarations }
    constructor Create(AOWner: TComponent); override;
    destructor Destroy; override;
  published
    { Published declarations }
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
    property Color;
    property Constraints;
    property Ctl3D;
    property DragCursor;
    property DragKind;
    property DragMode;
    property EditLabel;
    property Enabled;
    property Font;
    property HideSelection;
    property ImeMode;
    property ImeName;
    property LabelPosition;
    property LabelSpacing;
    property MaxLength;
    property OEMConvert;
    property ParentBiDiMode;
    property ParentColor;
    property ParentCtl3D;
    property ParentFont;
    property ParentShowHint;
    property PasswordChar;
    property PopupMenu;
    property ReadOnly;
    property ShowHint;
    property TabOrder;
    property TabStop;
//    property Text;
    property Visible;
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
    property OnStartDock;
    property OnStartDrag;
    property Value: Integer read FValue write SetValue;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Active Standard', [TActNumberEdit]);
end;

{ TActNumberEdit }

procedure TActNumberEdit.Change;
begin
  inherited;
  try
    if Text <> '' then
      FValue := StrToInt(Text)
    else
      FValue := 0;  
  except
    FValue := 0;
  end;
end;

constructor TActNumberEdit.Create(AOWner: TComponent);
begin
  inherited;
  Text := '';
  FValue := 0;
  ActHint := TActHintBalloon.Create(Self);
  ActHint.BalloonIcon := bError;
  ActHint.AutoCloseMode := acAction;
  ActHint.TimeOut := 10000;
  ActHint.Title := 'Caractere inaceitável.';
  ActHint.Prompt.Text := 'Você pode digitar apenas números aqui.';
end;

destructor TActNumberEdit.Destroy;
begin
  ActHint.Free;
  inherited Destroy;
end;

procedure TActNumberEdit.DoExit;
begin
  ActHint.Close;
  inherited DoExit;
end;

function TActNumberEdit.IsValidChar(Key: Char): Boolean;
begin
  Result := (not(Key in ['.'])) and ((Key in ['0'..'9']) or (Key < #32) or (Key = Char(VK_BACK)) or (Key = Char(VK_DELETE)));
end;

procedure TActNumberEdit.KeyPress(var Key: Char);
var
  Pos_X: Integer;
  Rect: TRect;
begin
  if not IsValidChar(Key) then
  begin
    Key := #0;
    Perform(EM_POSFROMCHAR, SelStart, 0);
    Pos_X := Perform(EM_POSFROMCHAR, SelStart + SelLength - 1, 0);
    GetWindowRect(Self.Handle, Rect);
    ActHint.Show(Rect.Left + Pos_X + 9, Rect.Bottom - 5);
    MessageBeep(0)
  end;
  if Key <> #0 then
  begin
    ActHint.Close;
    inherited KeyPress(Key);
  end;
end;

procedure TActNumberEdit.SetValue(const Value: Integer);
begin
  if Value <> FValue then
  begin
    FValue := Value;
    if FValue > 0 then
      Text := IntToStr(Value);
  end;
end;

end.

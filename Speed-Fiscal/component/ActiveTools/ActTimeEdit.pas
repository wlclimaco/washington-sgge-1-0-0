unit ActTimeEdit;

interface

uses
  Windows, SysUtils, Classes, Controls, StdCtrls, ActMaskEdit;

type

  TActTimeEdit = class(TActMaskEdit)
  private
    { Private declarations }
    FValue: TTime;
    procedure SetValue(const Value: TTime);
  protected
    { Protected declarations }
    procedure DoExit; override;
    procedure KeyPress(var Key: Char); override;
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
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
    property Value: TTime read FValue write SetValue;
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
  end;

  procedure Register;

implementation


procedure Register;
begin
  RegisterComponents('Active Standard', [TActTimeEdit]);
end;

constructor TActTimeEdit.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
  EditMask := '99' + TimeSeparator + '99;1; ';
  MaxLength := 5;
  Width := 41;
end;

procedure TActTimeEdit.DoExit;
begin
  if Trim(Text) = ':' then
    FValue := 0
  else
    try
      FValue := StrToTime(Text);
      Text := TimeToStr(FValue);
    except
      on EConvertError do



        raise EConvertError.Create('Hora "' + Text + '" inválida. Informe uma hora válida.');
    end;
  inherited DoExit;
end;

procedure TActTimeEdit.KeyPress(var Key: Char);
begin
  if not (Key in ['0'..'9', TimeSeparator, #8, #13, #28]) then
  begin
    Key := #0;
    MessageBeep($ffff);
  end;
  inherited KeyPress(Key);
end;

procedure TActTimeEdit.SetValue(const Value: TTime);
begin
  try
    Text := TimeToStr(Value);
    FValue := Value;
  except
    on EConvertError do
      raise EConvertError.Create('Hora inválida.');
  end;
end;

end.

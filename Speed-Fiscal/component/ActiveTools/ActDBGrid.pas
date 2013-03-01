unit ActDBGrid;

interface

uses
  SysUtils, Classes, Controls, Grids, DBGrids, Windows, Graphics, VDBConsts,
  Dialogs;

type
  TActDBGrid = class(TDBGrid)
  private
    FColorSelected: TColor;
    FDeleteRecno: Boolean;
    procedure SetColorSelected(const Value: TColor);
    procedure SetDeleteRecno(const Value: Boolean);
    { Private declarations }
  protected
    { Protected declarations }
    procedure DrawColumnCell(const Rect: TRect; DataCol: Integer;
      Column: TColumn; State: TGridDrawState); override;
    procedure KeyDown(var Key: Word; Shift: TShiftState); override;      
  public
    { Public declarations }
    property Canvas;
    property SelectedRows;
    constructor Create(AOwner: TComponent); override;
  published
    { Published declarations }
    property ColorSelected: TColor read FColorSelected write SetColorSelected;
    property DeleteRecno: Boolean read FDeleteRecno write SetDeleteRecno;
property Align;
    property BorderStyle;
    property Color;
    property Columns stored False; //StoreColumns;
    property Ctl3D;
    property DataSource;
    property DefaultDrawing;
    property DragCursor;
    property DragMode;
    property Enabled;
    property FixedColor;
    property Font;
    property ImeMode;
    property ImeName;
    property Options;
    property ParentColor;
    property ParentCtl3D;
    property ParentFont;
    property ParentShowHint;
    property PopupMenu;
    property ReadOnly;
    property ShowHint;
    property TabOrder;
    property TabStop;
    property TitleFont;
    property Visible;
    property OnCellClick;
    property OnColEnter;
    property OnColExit;
    property OnColumnMoved;
    property OnDrawDataCell;  { absoleto }
    property OnDrawColumnCell;
    property OnDblClick;
    property OnDragDrop;
    property OnDragOver;
    property OnEditButtonClick;
    property OnEndDrag;
    property OnEnter;
    property OnExit;
    property OnKeyDown;
    property OnKeyPress;
    property OnKeyUp;
    property OnStartDrag;
    property OnTitleClick;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Active Controls', [TActDBGrid]);
end;

{ TActDBGrid }

constructor TActDBGrid.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
  FColorSelected := $00F8E4D8;
  FDeleteRecno := False;
end;

procedure TActDBGrid.DrawColumnCell(const Rect: TRect; DataCol: Integer;
  Column: TColumn; State: TGridDrawState);
begin
  inherited DrawColumnCell(Rect, DataCol, Column, State);
  if (gdSelected in State) or (Self.SelectedRows.CurrentRowSelected)  then
  begin
    Self.Canvas.Font.Color := clBlack;
    Self.Canvas.Brush.Color := FColorSelected;
    Self.Canvas.FillRect(Rect);
    Self.DefaultDrawColumnCell(Rect, DataCol, Column, State);
  end;
end;

procedure TActDBGrid.KeyDown(var Key: Word; Shift: TShiftState);

  function DeletePrompt: Boolean;
  var
    Msg: string;
  begin
    Msg := SDeleteRecordQuestion;
    Result := not (dgConfirmDelete in Options) or
      (MessageDlg(Msg, mtConfirmation, mbOKCancel, 0) <> idCancel);
  end;

begin
  if ((DeleteRecno = False) and (Shift = [ssCtrl]) and (Key = VK_DELETE)) THEN
    Abort;

  if ((DeleteRecno = True) and (Key = VK_DELETE)) then
    if (not ReadOnly) and not DataSource.DataSet.IsEmpty and
      DataSource.DataSet.CanModify and DeletePrompt then
      DataSource.DataSet.Delete;

  inherited;
end;

procedure TActDBGrid.SetColorSelected(const Value: TColor);
begin
  if Value <> FColorSelected then
    FColorSelected := Value;
end;

procedure TActDBGrid.SetDeleteRecno(const Value: Boolean);
begin
  if Value <> FDeleteRecno then
    FDeleteRecno := Value;
end;

end.

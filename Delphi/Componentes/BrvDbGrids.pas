unit BrvDbGrids;
{*******************************************************}
{                                                       }
{       Bravo Logistica                                 }
{                                                       }
{       Uberaba, 29 de abril de 2011                    }
{                                                       }
{       Jefferson Ferreira Cardozo                      }
{                                                       }
{*******************************************************}

{$R-}

interface

uses Windows, SysUtils, Messages, Classes, Controls, Forms, StdCtrls,
  Graphics, Grids, DBCtrls, Db, Menus, ImgList, ExtCtrls, Buttons, BrvDb, RTLConsts,
  BrvBitBtn, BrvImgBot, DbClient, BrvDicionario;

type
  TColumnValue = (cvColor, cvWidth, cvFont, cvAlignment, cvReadOnly, cvTitleColor,
    cvTitleCaption, cvTitleAlignment, cvTitleFont, cvImeMode, cvImeName);
  TColumnValues = set of TColumnValue;

const
  ColumnTitleValues = [cvTitleColor..cvTitleFont];
  cm_DeferLayout = WM_USER + 100;

{ TColumn defines internal storage for column attributes.  If IsStored is
  True, values assigned to properties are stored in this object, the grid-
  or field-based default sources are not modified.  Values read from
  properties are the previously assigned value, if any, or the grid- or
  field-based default values if nothing has been assigned to that property.
  This class also publishes the column attribute properties for persistent
  storage.

  If IsStored is True, the column does not maintain local storage of
  property values.  Assignments to column properties are passed through to
  the underlying grid- or field-based default sources.  }
type
  TColumn = class;
  TBrCustomDBGrid = class;

  TColumnTitle = class(TPersistent)
  private
    FColumn: TColumn;
    FCaption: string;
    FFont: TFont;
    FColor: TColor;
    FAlignment: TAlignment;
    procedure FontChanged(Sender: TObject);
    function GetAlignment: TAlignment;
    function GetColor: TColor;
    function GetCaption: string;
    function GetFont: TFont;
    function IsAlignmentStored: Boolean;
    function IsColorStored: Boolean;
    function IsFontStored: Boolean;
    function IsCaptionStored: Boolean;
    procedure SetAlignment(Value: TAlignment);
    procedure SetColor(Value: TColor);
    procedure SetFont(Value: TFont);
    procedure SetCaption(const Value: string); virtual;
  protected
    procedure RefreshDefaultFont;
  public
    constructor Create(Column: TColumn);
    destructor Destroy; override;
    procedure Assign(Source: TPersistent); override;
    function DefaultAlignment: TAlignment;
    function DefaultColor: TColor;
    function DefaultFont: TFont;
    function DefaultCaption: string;
    procedure RestoreDefaults; virtual;
    property Column: TColumn read FColumn;
  published
    property Alignment: TAlignment read GetAlignment write SetAlignment
      stored IsAlignmentStored;
    property Caption: string read GetCaption write SetCaption stored IsCaptionStored;
    property Color: TColor read GetColor write SetColor stored IsColorStored;
    property Font: TFont read GetFont write SetFont stored IsFontStored;
  end;

  TColumnButtonStyle = (cbsAuto, cbsEllipsis, cbsNone, cbsCalculadora,
                        cbsConsulta, cbsData);

 TEventoBeforeCheck = procedure(Sender: TObject; Column : TColumn) of object;
 TBrvPer = (BrLer, BrAlterar, BrIncluir, BrExcluir);
 TBrvPermis = Set of TBrvPer;

  TColumn = class(TCollectionItem)
  private
    FField: TField;
    FFieldName: string;
    FColor: TColor;
    FWidth: Integer;
    FTitle: TColumnTitle;
    FFont: TFont;
    FImeMode: TImeMode;
    FImeName: TImeName;
    FPickList: TStrings;
    FPopupMenu: TPopupMenu;
    FDropDownRows: Cardinal;
    FButtonStyle: TColumnButtonStyle;
    FAlignment: TAlignment;
    FReadonly: Boolean;
    FAssignedValues: TColumnValues;
    FVisible: Boolean;
    FExpanded: Boolean;
    FStored: Boolean;
// Bravo
    gDsPickVa  : TStrings;
    gNrQueCon  : Integer;
    gDsConfig  : TStrings;
    gDsParams  : TStrings;
    gOnBefCon  : TNotifyEvent;
    gOnAftCon  : TNotifyEvent;
    gOnAftChe  : TNotifyEvent;
    gOnBefChe  : TEventoBeforeCheck;
    gCdPermis  : TBrvPermis;
    gVlChecke  : String;
    gVlUnChec  : String;
    gVlHalChe  : Boolean;
    gSnSavCli  : Boolean;
    procedure SetParametros(Value: TStrings);
    procedure SetFields(Value : TStrings);
    procedure SetConsulta(Value : Integer);
    procedure SetPickValue(Value : TStrings);
    function  GetPickValue : TStrings;
    procedure SetValueChecked(Value : String);
    procedure SetValueUnchecked(Value : String);
// =-=-=-=-=-=-=-=-=-
    procedure FontChanged(Sender: TObject);
    function  GetAlignment: TAlignment;
    function  GetColor: TColor;
    function  GetExpanded: Boolean;
    function  GetField: TField;
    function  GetFont: TFont;
    function  GetImeMode: TImeMode;
    function  GetImeName: TImeName;
    function  GetParentColumn: TColumn;
    function  GetPickList: TStrings;
    function  GetReadOnly: Boolean;
    function  GetShowing: Boolean;
    function  GetWidth: Integer;
    function  GetVisible: Boolean;
    function  IsAlignmentStored: Boolean;
    function  IsColorStored: Boolean;
    function  IsFontStored: Boolean;
    function  IsImeModeStored: Boolean;
    function  IsImeNameStored: Boolean;
    function  IsReadOnlyStored: Boolean;
    function  IsWidthStored: Boolean;
    procedure SetAlignment(Value: TAlignment); virtual;
    procedure SetButtonStyle(Value: TColumnButtonStyle);
    procedure SetColor(Value: TColor);
    procedure SetExpanded(Value: Boolean);
    procedure SetField(Value: TField); virtual;
    procedure SetFieldName(const Value: String);
    procedure SetFont(Value: TFont);
    procedure SetImeMode(Value: TImeMode); virtual;
    procedure SetImeName(Value: TImeName); virtual;
    procedure SetPickList(Value: TStrings);
    procedure SetPopupMenu(Value: TPopupMenu);
    procedure SetReadOnly(Value: Boolean); virtual;
    procedure SetTitle(Value: TColumnTitle);
    procedure SetWidth(Value: Integer); virtual;
    procedure SetVisible(Value: Boolean);
    function GetExpandable: Boolean;
  protected
    function  CreateTitle: TColumnTitle; virtual;
    function  GetGrid: TBrCustomDBGrid;
    function GetDisplayName: string; override;
    procedure RefreshDefaultFont;
    procedure SetIndex(Value: Integer); override;
    property IsStored: Boolean read FStored write FStored default True;
  public
    constructor Create(Collection: TCollection); override;
    destructor Destroy; override;
    procedure Assign(Source: TPersistent); override;
    function  DefaultAlignment: TAlignment;
    function  DefaultColor: TColor;
    function  DefaultFont: TFont;
    function  DefaultImeMode: TImeMode;
    function  DefaultImeName: TImeName;
    function  DefaultReadOnly: Boolean;
    function  DefaultWidth: Integer;
    function  Depth: Integer;
    procedure RestoreDefaults; virtual;
    property  Grid: TBrCustomDBGrid read GetGrid;
    property  AssignedValues: TColumnValues read FAssignedValues;
    property  Expandable: Boolean read GetExpandable;
    property  Field: TField read GetField write SetField;
    property  ParentColumn: TColumn read GetParentColumn;
    property  Showing: Boolean read GetShowing;
  published
    property  Alignment: TAlignment read GetAlignment write SetAlignment
      stored IsAlignmentStored;
    property  ButtonStyle: TColumnButtonStyle read FButtonStyle write SetButtonStyle
      default cbsAuto;
    property  Color: TColor read GetColor write SetColor stored IsColorStored;
    property  DropDownRows: Cardinal read FDropDownRows write FDropDownRows default 7;
    property  Expanded: Boolean read GetExpanded write SetExpanded default True;
    property  FieldName: String read FFieldName write SetFieldName;
    property  Font: TFont read GetFont write SetFont stored IsFontStored;
    property  ImeMode: TImeMode read GetImeMode write SetImeMode stored IsImeModeStored;
    property  ImeName: TImeName read GetImeName write SetImeName stored IsImeNameStored;
    property  PickList: TStrings read GetPickList write SetPickList;
    property  PopupMenu: TPopupMenu read FPopupMenu write SetPopupMenu;
    property  ReadOnly: Boolean read GetReadOnly write SetReadOnly
      stored IsReadOnlyStored;
    property  Title: TColumnTitle read FTitle write SetTitle;
    property  Width: Integer read GetWidth write SetWidth stored IsWidthStored;
    property  Visible: Boolean read GetVisible write SetVisible;
//=-=-=-=-=- Bravo
    property BrOnBeforeConsul  : TNotifyEvent read gOnBefCon    write gOnBefCon;
    property BrOnAfterConsul   : TNotifyEvent read gOnAftCon    write gOnAftCon;
    property BrOnAfterCheck    : TNotifyEvent read gOnAftChe    write gOnAftChe;
    property BrOnBeforeCheck   : TEventoBeforeCheck
                                             read gOnBefChe    write gOnBefChe;
    property BrConsulta        : Integer      read gNrQueCon    write SetConsulta;
    property BrParams          : TStrings     read gDsParams    write SetParametros;
    property BrConfigurar      : TStrings     read gDsConfig    write SetFields;
    property BrPickValue       : TStrings     read GetPickValue write SetPickValue;
    property BrPermissao       : TBrvPermis   read gCdPermis    write gCdPermis;
    property BrValueChecked    : String       read gVlChecke    write SetValueChecked;
    property BrValueUnchecked  : String       read gVlUnChec    write SetValueUnchecked;
    property BrValueHalfChecked: Boolean      read gVlHalChe    write gVlHalChe;
    property BrSaveOnClick     : Boolean      read gSnSavCli    write gSnSavCli;
//=-=-=-=-=-=-=-=
  end;

  TColumnClass = class of TColumn;

  TDBGridColumnsState = (csDefault, csCustomized);

  TDBGridColumns = class(TCollection)
  private
    FGrid: TBrCustomDBGrid;
    function GetColumn(Index: Integer): TColumn;
    function InternalAdd: TColumn;
    procedure SetColumn(Index: Integer; Value: TColumn);
    procedure SetState(NewState: TDBGridColumnsState);
    function GetState: TDBGridColumnsState;
  protected
    function GetOwner: TPersistent; override;
    procedure Update(Item: TCollectionItem); override;
  public
    constructor Create(Grid: TBrCustomDBGrid; ColumnClass: TColumnClass);
    function  Add: TColumn;
    procedure LoadFromFile(const Filename: string);
    procedure LoadFromStream(S: TStream);
    procedure RestoreDefaults;
    procedure RebuildColumns;
    procedure SaveToFile(const Filename: string);
    procedure SaveToStream(S: TStream);
    property State: TDBGridColumnsState read GetState write SetState;
    property Grid: TBrCustomDBGrid read FGrid;
    property Items[Index: Integer]: TColumn read GetColumn write SetColumn; default;
  end;

  TGridDataLink = class(TDataLink)
  private
    FGrid: TBrCustomDBGrid;
    FFieldCount: Integer;
    FFieldMap: array of Integer;
    FModified: Boolean;
    FInUpdateData: Boolean;
    FSparseMap: Boolean;
    function GetDefaultFields: Boolean;
    function GetFields(I: Integer): TField;
  protected
    procedure ActiveChanged; override;
    procedure BuildAggMap;
    procedure DataSetChanged; override;
    procedure DataSetScrolled(Distance: Integer); override;
    procedure FocusControl(Field: TFieldRef); override;
    procedure EditingChanged; override;
    function IsAggRow(Value: Integer): Boolean; virtual;
    procedure LayoutChanged; override;
    procedure RecordChanged(Field: TField); override;
    procedure UpdateData; override;
    function  GetMappedIndex(ColIndex: Integer): Integer;
  public
    constructor Create(AGrid: TBrCustomDBGrid);
    destructor Destroy; override;
    function AddMapping(const FieldName: string): Boolean;
    procedure ClearMapping;
    procedure Modified;
    procedure Reset;
    property DefaultFields: Boolean read GetDefaultFields;
    property FieldCount: Integer read FFieldCount;
    property Fields[I: Integer]: TField read GetFields;
    property SparseMap: Boolean read FSparseMap write FSparseMap;
  end;

  TBookmarkList = class
  private
    FList: array of TBookmark;
    FGrid: TBrCustomDBGrid;
    FCache: TBookmark;
    FCacheIndex: Integer;
    FCacheFind: Boolean;
    FLinkActive: Boolean;
    function GetCount: Integer;
    function GetCurrentRowSelected: Boolean;
    function GetItem(Index: Integer): TBookmark;
    procedure SetCurrentRowSelected(Value: Boolean);
// =-=-=-=- Bravo
    procedure DeleteItem(Index: Integer);
    procedure DataChanged(Sender: TObject);
    procedure InsertItem(Index: Integer; Item: TBookmark);
  protected
    function CurrentRow: TBookmark;
    function Compare(const Item1, Item2: TBookmark): Integer;
    procedure LinkActive(Value: Boolean);
  public
    constructor Create(AGrid: TBrCustomDBGrid);
    destructor Destroy; override;
    procedure Clear;           // free all bookmarks
    procedure Delete;          // delete all selected rows from dataset
    function  Find(const Item: TBookmark; var Index: Integer): Boolean;
    function  IndexOf(const Item: TBookmark): Integer;
    function  Refresh: Boolean;// drop orphaned bookmarks; True = orphans found
    property Count: Integer read GetCount;
    property CurrentRowSelected: Boolean read GetCurrentRowSelected
      write SetCurrentRowSelected;
    property Items[Index: Integer]: TBookmark read GetItem; default;
  end;

  TDBGridOption = (dgEditing, dgAlwaysShowEditor, dgTitles, dgIndicator,
    dgColumnResize, dgColLines, dgRowLines, dgTabs, dgRowSelect,
    dgAlwaysShowSelection, dgConfirmDelete, dgCancelOnExit, dgMultiSelect);
  TDBGridOptions = set of TDBGridOption;

  { The DBGrid's DrawDataCell virtual method and OnDrawDataCell event are only
    called when the grid's Columns.State is csDefault.  This is for compatibility
    with existing code. These routines don't provide sufficient information to
    determine which column is being drawn, so the column attributes aren't
    easily accessible in these routines.  Column attributes also introduce the
    possibility that a column's field may be nil, which would break existing
    DrawDataCell code.   DrawDataCell, OnDrawDataCell, and DefaultDrawDataCell
    are obsolete, retained for compatibility purposes. }
  TDrawDataCellEvent = procedure (Sender: TObject; const Rect: TRect; Field: TField;
    State: TGridDrawState) of object;

  { The DBGrid's DrawColumnCell virtual method and OnDrawColumnCell event are
    always called, when the grid has defined column attributes as well as when
    it is in default mode.  These new routines provide the additional
    information needed to access the column attributes for the cell being
    drawn, and must support nil fields.  }

  TDrawColumnCellEvent = procedure (Sender: TObject; const Rect: TRect;
    DataCol: Integer; Column: TColumn; State: TGridDrawState) of object;
  TDBGridClickEvent = procedure (Column: TColumn) of object;

  TBrCustomDBGrid = class(TCustomGrid)
  private
    FIndicators: TImageList;
    FTitleFont: TFont;
    FReadOnly: Boolean;
    FOriginalImeName: TImeName;
    FOriginalImeMode: TImeMode;
    FUserChange: Boolean;
    FIsESCKey: Boolean;
    FLayoutFromDataset: Boolean;
    FOptions: TDBGridOptions;
    FTitleOffset, FIndicatorOffset: Byte;
    FUpdateLock: Byte;
    FLayoutLock: Byte;
    FInColExit: Boolean;
    FDefaultDrawing: Boolean;
    FSelfChangingTitleFont: Boolean;
    FSelecting: Boolean;
    FSelRow: Integer;
    FDataLink: TGridDataLink;
    FOnColEnter: TNotifyEvent;
    FOnColExit: TNotifyEvent;
    FOnDrawDataCell: TDrawDataCellEvent;
    FOnDrawColumnCell: TDrawColumnCellEvent;
    FEditText: string;
    FColumns: TDBGridColumns;
    FVisibleColumns: TList;
    FBookmarks: TBookmarkList;
    FSelectionAnchor: TBookmark;
    FOnEditButtonClick: TNotifyEvent;
    FOnColumnMoved: TMovedEvent;
    FOnCellClick: TDBGridClickEvent;
    FOnTitleClick:TDBGridClickEvent;
    FDragCol: TColumn;
// -=-=-= Bravo
    FStlReadOn : TFontStyles;
    FCorReaOnl : TColor;
    FOnRowExit : TNotifyEvent;
    FSnDisMem  : Boolean;
    FTmWidChe  : Integer;
    FTmHeiChe  : Integer;
    FDsOption  : TDbGridOptions;
    gTpOrdem   : Char;
    gNrColCli  : Integer;
    gStlDraCol : TStrings;
    gSnZebrad  : Boolean;
    function  EncontrarTextoPickList(Column : TColumn) : String;
    procedure ProcessarMemo(const Rect: TRect; Column: TColumn);
    procedure ProcessarOutrosCampos(const Rect : TRect; Column : TColumn);
    procedure MostrarMemo(Column : TColumn);
    procedure ProcessarString(const Rect: TRect; Column: TColumn);
    procedure AlterarString(Column : TColumn; Click : Boolean);
    procedure MemoKeyPress(Sender: TObject; var Key: Char);
    procedure MostrarSetaOrdenacao(pNrPosLef : Integer; pColumn: TColumn;
                                   pNrColCli : Integer);
    procedure CliqueNoTitulo(Column: TColumn);
    procedure VerificarValorPickList(Column: TColumn);
    function ProcessarCorLinha(pDsColor: TColor): TColor;
    function ProcessarCorGradeZebrada(State: TGridDrawState) : TColor;
    function StrToColor(pDsCor: String): TColor;
// =-=-=-=-=-=-=
    function AcquireFocus: Boolean;
    procedure DataChanged;
    procedure EditingChanged;
    function GetDataSource: TDataSource;
    function GetFieldCount: Integer;
    function GetFields(FieldIndex: Integer): TField;
    function GetSelectedField: TField;
    function GetSelectedIndex: Integer;
    procedure InternalLayout;
    procedure MoveCol(RawCol, Direction: Integer);
    function PtInExpandButton(X,Y: Integer; var MasterCol: TColumn): Boolean;
    procedure ReadColumns(Reader: TReader);
    procedure RecordChanged(Field: TField);
    procedure SetIme;
    procedure SetColumns(Value: TDBGridColumns);
    procedure SetDataSource(Value: TDataSource);
    procedure SetOptions(Value: TDBGridOptions);
    procedure SetSelectedField(Value: TField);
    procedure SetSelectedIndex(Value: Integer);
    procedure SetTitleFont(Value: TFont);
    procedure TitleFontChanged(Sender: TObject);
    procedure UpdateData;
    procedure UpdateActive;
    procedure UpdateIme;
    procedure UpdateScrollBar;
    procedure UpdateRowCount;
    procedure WriteColumns(Writer: TWriter);
    procedure CMBiDiModeChanged(var Message: TMessage); message CM_BIDIMODECHANGED;
    procedure CMExit(var Message: TMessage); message CM_EXIT;
    procedure CMFontChanged(var Message: TMessage); message CM_FONTCHANGED;
    procedure CMParentFontChanged(var Message: TMessage); message CM_PARENTFONTCHANGED;
    procedure CMDeferLayout(var Message); message cm_DeferLayout;
    procedure CMDesignHitTest(var Msg: TCMDesignHitTest); message CM_DESIGNHITTEST;
    procedure WMSetCursor(var Msg: TWMSetCursor); message WM_SETCURSOR;
    procedure WMSize(var Message: TWMSize); message WM_SIZE;
    procedure WMVScroll(var Message: TWMVScroll); message WM_VSCROLL;
    procedure WMIMEStartComp(var Message: TMessage); message WM_IME_STARTCOMPOSITION;
    procedure WMSetFocus(var Message: TWMSetFocus); message WM_SetFOCUS;
    procedure WMKillFocus(var Message: TMessage); message WM_KillFocus;
  protected
    FUpdateFields: Boolean;
    FAcquireFocus: Boolean;
// =-=-=-=-=-=-=-= Bravo
    procedure RowExit; dynamic;
// =-=-=-=-=-=-=-=-=-=-=-=-=
    function  RawToDataColumn(ACol: Integer): Integer;
    function  DataToRawColumn(ACol: Integer): Integer;
    function  AcquireLayoutLock: Boolean;
    procedure BeginLayout;
    procedure BeginUpdate;
    procedure CalcSizingState(X, Y: Integer; var State: TGridState;
      var Index: Longint; var SizingPos, SizingOfs: Integer;
      var FixedInfo: TGridDrawInfo); override;
    procedure CancelLayout;
    function  CanEditAcceptKey(Key: Char): Boolean; override;
    function  CanEditModify: Boolean; override;
    function  CanEditShow: Boolean; override;
    procedure CellClick(Column: TColumn); dynamic;
    procedure ColumnMoved(FromIndex, ToIndex: Longint); override;
    function CalcTitleRect(Col: TColumn; ARow: Integer;
      var MasterCol: TColumn): TRect;
    function ColumnAtDepth(Col: TColumn; ADepth: Integer): TColumn;
    procedure ColEnter; dynamic;
    procedure ColExit; dynamic;
    procedure ColWidthsChanged; override;
    function  CreateColumns: TDBGridColumns; dynamic;
    function  CreateEditor: TInplaceEdit; override;
    procedure CreateWnd; override;
    procedure DeferLayout;
    procedure DefineFieldMap; virtual;
    procedure DefineProperties(Filer: TFiler); override;
    procedure DrawCell(ACol, ARow: Longint; ARect: TRect; AState: TGridDrawState); override;
    procedure DrawDataCell(const Rect: TRect; Field: TField;
      State: TGridDrawState); dynamic; { obsolete }
    procedure DrawColumnCell(const Rect: TRect; DataCol: Integer;
      Column: TColumn; State: TGridDrawState); dynamic;
    procedure EditButtonClick; dynamic;
    procedure EndLayout;
    procedure EndUpdate;
    function  GetColField(DataCol: Integer): TField;
    function  GetEditLimit: Integer; override;
    function  GetEditMask(ACol, ARow: Longint): string; override;
    function  GetEditText(ACol, ARow: Longint): string; override;
    function  GetFieldValue(ACol: Integer): string;
    function  HighlightCell(DataCol, DataRow: Integer; const Value: string;
      AState: TGridDrawState): Boolean; virtual;
    procedure KeyDown(var Key: Word; Shift: TShiftState); override;
    procedure KeyPress(var Key: Char); override;
    procedure InvalidateTitles;
    procedure LayoutChanged; virtual;
    procedure LinkActive(Value: Boolean); virtual;
    procedure Loaded; override;
    procedure MouseDown(Button: TMouseButton; Shift: TShiftState;
      X, Y: Integer); override;
    procedure MouseUp(Button: TMouseButton; Shift: TShiftState;
      X, Y: Integer); override;
    procedure Notification(AComponent: TComponent; Operation: TOperation); override;
    procedure Scroll(Distance: Integer); virtual;
    procedure SetColumnAttributes; virtual;
    procedure SetEditText(ACol, ARow: Longint; const Value: string); override;
    function  StoreColumns: Boolean;
    procedure TimedScroll(Direction: TGridScrollDirection); override;
    procedure TitleClick(Column: TColumn); dynamic;
    procedure TopLeftChanged; override;
    function UseRightToLeftAlignmentForField(const AField: TField;
      Alignment: TAlignment): Boolean;
    function BeginColumnDrag(var Origin, Destination: Integer;
      const MousePt: TPoint): Boolean; override;
    function CheckColumnDrag(var Origin, Destination: Integer;
      const MousePt: TPoint): Boolean; override;
    function EndColumnDrag(var Origin, Destination: Integer;
      const MousePt: TPoint): Boolean; override;
    property Columns: TDBGridColumns read FColumns write SetColumns;
    property DefaultDrawing: Boolean read FDefaultDrawing write FDefaultDrawing default True;
    property DataLink: TGridDataLink read FDataLink;
    property IndicatorOffset: Byte read FIndicatorOffset;
    property LayoutLock: Byte read FLayoutLock;
    property Options: TDBGridOptions read FOptions write SetOptions
      default [dgEditing, dgTitles, dgIndicator, dgColumnResize, dgColLines,
      dgRowLines, dgTabs, dgConfirmDelete, dgCancelOnExit];
    property ParentColor default False;
    property ReadOnly: Boolean read FReadOnly write FReadOnly default False;
    property SelectedRows: TBookmarkList read FBookmarks;
    property TitleFont: TFont read FTitleFont write SetTitleFont;
    property UpdateLock: Byte read FUpdateLock;
    property OnColEnter: TNotifyEvent read FOnColEnter write FOnColEnter;
    property OnColExit: TNotifyEvent read FOnColExit write FOnColExit;
    property OnDrawDataCell: TDrawDataCellEvent read FOnDrawDataCell
      write FOnDrawDataCell; { obsolete }
    property OnDrawColumnCell: TDrawColumnCellEvent read FOnDrawColumnCell
      write FOnDrawColumnCell;
    property OnEditButtonClick: TNotifyEvent read FOnEditButtonClick
      write FOnEditButtonClick;
    property OnColumnMoved: TMovedEvent read FOnColumnMoved write FOnColumnMoved;
    property OnCellClick: TDBGridClickEvent read FOnCellClick write FOnCellClick;
    property OnTitleClick: TDBGridClickEvent read FOnTitleClick write FOnTitleClick;
// =-=-=-=-= Bravo
    property BrOnRowExit     : TNotifyEvent read FOnRowExit write FOnRowExit;
    property BrShowMemo      : Boolean      read FSnDisMem  write FSnDisMem;
    property BrReadOnlyStyle : TFontStyles read FStlReadOn write FStlReadOn;
    property BrReadOnlyColor : TColor      read FCorReaOnl write FCorReaOnl;
// =-=-=-=-=-=-=-=-=-=-=-=-=-=
  public
  // =-=-=-=- Bravo
    procedure OrdenarClientDataSet(CCOrdena: TClientDataSet; pNmColuna: String;
                                   pSnReInic : Boolean; pNrColCli :Integer);
 // =-=-=-=-=-=-=-=-=-=-=
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
    procedure DefaultDrawDataCell(const Rect: TRect; Field: TField;
      State: TGridDrawState); { obsolete }
    procedure DefaultDrawColumnCell(const Rect: TRect; DataCol: Integer;
      Column: TColumn; State: TGridDrawState);
    procedure DefaultHandler(var Msg); override;
    function ExecuteAction(Action: TBasicAction): Boolean; override;
    procedure ShowPopupEditor(Column: TColumn; X: Integer = Low(Integer);
      Y: Integer = Low(Integer)); dynamic;
    function UpdateAction(Action: TBasicAction): Boolean; override;
    function ValidFieldIndex(FieldIndex: Integer): Boolean;
    property EditorMode;
    property FieldCount: Integer read GetFieldCount;
    property Fields[FieldIndex: Integer]: TField read GetFields;
    property SelectedField: TField read GetSelectedField write SetSelectedField;
    property SelectedIndex: Integer read GetSelectedIndex write SetSelectedIndex;
    property DataSource: TDataSource read GetDataSource write SetDataSource;
  end;

  TBrDbGrids = class(TBrCustomDBGrid)
  public
// =-=-=-=-=-= Bravo
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
    procedure  ValidarColuna(pNoColuna : Integer);
// =-=-=-=-=-=-=
    property Canvas;
    property SelectedRows;
  private
// =-=-=-=-=-= Bravo
    gDiciona   : TBrvDicionario;
    procedure ValidarEntrada;
    procedure DefinirCursor(NrColuna: Integer);
    procedure BrSetDrawColumn(const Value: TStrings);
  protected
    procedure ColExit; override;
    procedure RowExit; override;
  published
    property BrOnRowExit;
    property BrShowMemo;
    property BrReadOnlyStyle;
    property BrReadOnlyColor;
//  =-=-=-=-=-=-=-=
    property Align;
    property Anchors;
    property BiDiMode;
    property BorderStyle;
    property Color;
    property Columns stored False; //StoreColumns;
    property Constraints;
    property Ctl3D;
    property DataSource;
    property DefaultDrawing;
    property DragCursor;
    property DragKind;
    property DragMode;
    property Enabled;
    property FixedColor;
    property Font;
    property ImeMode;
    property ImeName;
    property Options;
    property ParentBiDiMode;
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
    property OnDrawDataCell;  { obsolete }
    property OnDrawColumnCell;
    property OnDblClick;
    property OnDragDrop;
    property OnDragOver;
    property OnEditButtonClick;
    property OnEndDock;
    property OnEndDrag;
    property OnEnter;
    property OnExit;
    property OnKeyDown;
    property OnKeyPress;
    property OnKeyUp;
    property OnMouseDown;
    procedure MouseMove(Shift: TShiftState; X, Y: Integer); override;
//    property OnMouseMove;
    property OnMouseUp;
    property OnStartDock;
    property OnStartDrag;
    property OnTitleClick;
// =-=-=-=-= Bravo
    property BrDicionario     : TBrvDicionario read gDiciona   write gDiciona;
    property BrDrawColumn     : TStrings       read gStlDraCol write BrSetDrawColumn;
    property BrGradeZebrada   : Boolean        read gSnZebrad  write gSnZebrad;
// =-=-=-=-=-=-=-=-=-=-=
  end;

  function BrLwCase(pDsCaract : Char) : Char;
  function BrUpCase(pDsCaract : Char) : Char;

const
  IndicatorWidth = 11;

implementation

uses Math, DBConsts, Dialogs, BrvCalculadora, BrvCalendario, BrvConsulta, BrvString;
// =-=-=-=-=-=-= Bravo
{$R BRVDBGRIDS.RES}

const
  bmArrow      = 'BRDBGARROW';
  bmEdit       = 'BRDBEDIT';
  bmInsert     = 'BRDBINSERT';
  bmMultiDot   = 'BRDBMULTIDOT';
  bmMultiArrow = 'BRDBMULTIARROW';
  bmSetaAcima  = 'BRSETAACIMA';
  bmSetaAbaixo = 'BRSETABAIXO';
// =-=-=-=-=-=-=-=-=-=

Const  crCheck = 5;
       crPen   = 6;

  MaxMapSize = (MaxInt div 2) div SizeOf(Integer);  { 250 million }

{ Error reporting }

procedure RaiseGridError(const S: string);
begin
  raise EInvalidGridOperation.Create(S);
end;

procedure KillMessage(Wnd: HWnd; Msg: Integer);
// Delete the requested message from the queue, but throw back
// any WM_QUIT msgs that PeekMessage may also return
var
  M: TMsg;
begin
  M.Message := 0;
  if PeekMessage(M, Wnd, Msg, Msg, pm_Remove) and (M.Message = WM_QUIT) then
    PostQuitMessage(M.wparam);
end;

{ TDBGridInplaceEdit }

{ TDBGridInplaceEdit adds support for a button on the in-place editor,
  which can be used to drop down a table-based lookup list, a stringlist-based
  pick list, or (if button style is esEllipsis) fire the grid event
  OnEditButtonClick.  }

type
  TEditStyle = (esSimple, esEllipsis, esPickList, esDataList, esCalculadora,
                esConsulta, esData);
  TPopupListbox = class;

  TDBGridInplaceEdit = class(TInplaceEdit)
  private
    FButtonWidth: Integer;
    FDataList: TDBLookupListBox;
    FPickList: TPopupListbox;
    FActiveList: TWinControl;
    FLookupSource: TDatasource;
    FEditStyle: TEditStyle;
    FListVisible: Boolean;
    FTracking: Boolean;
    FPressed: Boolean;
// =-=-=-=-=-=-=-= Bravo
    gDsPickVa : TPopUpListBox;
    procedure ChamarConsulta;
    procedure ChamarCalculadora;
    procedure ChamarCalendario;
// =-=-=-=-=-=-=-=
    procedure ListMouseUp(Sender: TObject; Button: TMouseButton;
      Shift: TShiftState; X, Y: Integer);
    procedure SetEditStyle(Value: TEditStyle);
    procedure StopTracking;
    procedure TrackButton(X,Y: Integer);
    procedure CMCancelMode(var Message: TCMCancelMode); message CM_CancelMode;
    procedure WMCancelMode(var Message: TMessage); message WM_CancelMode;
    procedure WMKillFocus(var Message: TMessage); message WM_KillFocus;
    procedure WMLButtonDblClk(var Message: TWMLButtonDblClk); message wm_LButtonDblClk;
    procedure WMPaint(var Message: TWMPaint); message wm_Paint;
    procedure WMSetCursor(var Message: TWMSetCursor); message WM_SetCursor;
    function OverButton(const P: TPoint): Boolean;
    function ButtonRect: TRect;
  protected
// Bravo
    procedure KeyPress(var Key: Char); override;
// =-=-=-=-=-=
    procedure BoundsChanged; override;
    procedure CloseUp(Accept: Boolean);
    procedure DoDropDownKeys(var Key: Word; Shift: TShiftState);
    procedure DropDown;
    procedure KeyDown(var Key: Word; Shift: TShiftState); override;
    procedure MouseDown(Button: TMouseButton; Shift: TShiftState;
      X, Y: Integer); override;
    procedure MouseMove(Shift: TShiftState; X, Y: Integer); override;
    procedure MouseUp(Button: TMouseButton; Shift: TShiftState;
      X, Y: Integer); override;
    procedure PaintWindow(DC: HDC); override;
    procedure UpdateContents; override;
    procedure WndProc(var Message: TMessage); override;
    property  EditStyle: TEditStyle read FEditStyle write SetEditStyle;
    property  ActiveList: TWinControl read FActiveList write FActiveList;
    property  DataList: TDBLookupListBox read FDataList;
    property  PickList: TPopupListbox read FPickList;
  public
    constructor Create(Owner: TComponent); override;
  end;

{ TPopupListbox }

  TPopupListbox = class(TCustomListbox)
  private
    FSearchText: String;
    FSearchTickCount: Longint;
  protected
    procedure CreateParams(var Params: TCreateParams); override;
    procedure CreateWnd; override;
    procedure KeyPress(var Key: Char); override;
    procedure MouseUp(Button: TMouseButton; Shift: TShiftState; X, Y: Integer); override;
  end;

procedure TPopupListBox.CreateParams(var Params: TCreateParams);
begin
  inherited CreateParams(Params);
  with Params do
  begin
    Style := Style or WS_BORDER;
    ExStyle := WS_EX_TOOLWINDOW or WS_EX_TOPMOST;
    AddBiDiModeExStyle(ExStyle);
    WindowClass.Style := CS_SAVEBITS;
  end;
end;

procedure TPopupListbox.CreateWnd;
begin
  inherited CreateWnd;
  Windows.SetParent(Handle, 0);
  CallWindowProc(DefWndProc, Handle, wm_SetFocus, 0, 0);
end;

procedure TPopupListbox.Keypress(var Key: Char);
var
  TickCount: Integer;
begin
  case Key of
    #8, #27: FSearchText := '';
    #32..#255:
      begin
        TickCount := GetTickCount;
        if TickCount - FSearchTickCount > 2000 then FSearchText := '';
        FSearchTickCount := TickCount;
        if Length(FSearchText) < 32 then FSearchText := FSearchText + Key;
        SendMessage(Handle, LB_SelectString, WORD(-1), Longint(PChar(FSearchText)));
        Key := #0;
      end;
  end;
  inherited Keypress(Key);
end;

procedure TPopupListbox.MouseUp(Button: TMouseButton; Shift: TShiftState;
  X, Y: Integer);
begin
  inherited MouseUp(Button, Shift, X, Y);
  TDBGridInPlaceEdit(Owner).CloseUp((X >= 0) and (Y >= 0) and
      (X < Width) and (Y < Height));
end;


constructor TDBGridInplaceEdit.Create(Owner: TComponent);
begin
  inherited Create(Owner);
  FLookupSource := TDataSource.Create(Self);
  FButtonWidth := GetSystemMetrics(SM_CXVSCROLL);
  FEditStyle := esSimple;
end;

procedure TDBGridInplaceEdit.BoundsChanged;
var
  R: TRect;
begin
  SetRect(R, 2, 2, Width - 2, Height);
  if FEditStyle <> esSimple then
    if not TBrCustomDBGrid(Owner).UseRightToLeftAlignment then
      Dec(R.Right, FButtonWidth)
    else
      Inc(R.Left, FButtonWidth - 2);
  SendMessage(Handle, EM_SETRECTNP, 0, LongInt(@R));
  SendMessage(Handle, EM_SCROLLCARET, 0, 0);
  if SysLocale.FarEast then
    SetImeCompositionWindow(Font, R.Left, R.Top);
end;

procedure TDBGridInplaceEdit.CloseUp(Accept: Boolean);
var
  MasterField: TField;
  ListValue: Variant;
begin
  if FListVisible then
  begin
    if GetCapture <> 0 then SendMessage(GetCapture, WM_CANCELMODE, 0, 0);
    if FActiveList = FDataList then
      ListValue := FDataList.KeyValue
    else
      if FPickList.ItemIndex <> -1 then
// Bravo
          ListValue := gDsPickVa.Items[fPickList.ItemIndex];
//        ListValue := FPickList.Items[FPicklist.ItemIndex];
// =-=-=-=-=-=-=
    SetWindowPos(FActiveList.Handle, 0, 0, 0, 0, 0, SWP_NOZORDER or
      SWP_NOMOVE or SWP_NOSIZE or SWP_NOACTIVATE or SWP_HIDEWINDOW);
    FListVisible := False;
    if Assigned(FDataList) then
      FDataList.ListSource := nil;
    FLookupSource.Dataset := nil;
    Invalidate;
    if Accept then
      if FActiveList = FDataList then
        with TBrCustomDBGrid(Grid), Columns[SelectedIndex].Field do
        begin
          MasterField := DataSet.FieldByName(KeyFields);
          if MasterField.CanModify and FDataLink.Edit then
            MasterField.Value := ListValue;
        end
      else
        if (TVarData(ListValue).VType <> varNull) and EditCanModify then
          with TBrCustomDBGrid(Grid), Columns[SelectedIndex].Field do
            Text := ListValue;
  end;
end;

procedure TDBGridInplaceEdit.DoDropDownKeys(var Key: Word; Shift: TShiftState);
begin
  case Key of
    VK_UP, VK_DOWN:
      if ssAlt in Shift then
      begin
        if FListVisible then CloseUp(True) else DropDown;
        Key := 0;
      end;
    VK_RETURN, VK_ESCAPE:
      if FListVisible and not (ssAlt in Shift) then
      begin
        CloseUp(Key = VK_RETURN);
        Key := 0;
      end;
  end;
end;

procedure TDBGridInplaceEdit.DropDown;
var
  P: TPoint;
  I,J,Y: Integer;
  Column: TColumn;
begin
  if not FListVisible and Assigned(FActiveList) then
  begin
    FActiveList.Width := Width;
    with TBrCustomDBGrid(Grid) do
      Column := Columns[SelectedIndex];
    if FActiveList = FDataList then
    with Column.Field do
    begin
      FDataList.Color := Color;
      FDataList.Font := Font;
      FDataList.RowCount := Column.DropDownRows;
      FLookupSource.DataSet := LookupDataSet;
      FDataList.KeyField := LookupKeyFields;
      FDataList.ListField := LookupResultField;
      FDataList.ListSource := FLookupSource;
      FDataList.KeyValue := DataSet.FieldByName(KeyFields).Value;
{      J := Column.DefaultWidth;
      if J > FDataList.ClientWidth then
        FDataList.ClientWidth := J;
}    end
    else
    begin
      FPickList.Color := Color;
      FPickList.Font := Font;
      FPickList.Items := Column.Picklist;
// =-=-=-=-= Bravo
      gDsPickVa.Items := Column.BrPickValue;
//=-=-=-=-=
      if FPickList.Items.Count >= Integer(Column.DropDownRows) then
        FPickList.Height := Integer(Column.DropDownRows) * FPickList.ItemHeight + 4
      else
        FPickList.Height := FPickList.Items.Count * FPickList.ItemHeight + 4;
      if Column.Field.IsNull then
        FPickList.ItemIndex := -1
      else
        FPickList.ItemIndex := FPickList.Items.IndexOf(Column.Field.Text);
      J := FPickList.ClientWidth;
      for I := 0 to FPickList.Items.Count - 1 do
      begin
        Y := FPickList.Canvas.TextWidth(FPickList.Items[I]);
        if Y > J then J := Y;
      end;
      FPickList.ClientWidth := J;
    end;
    P := Parent.ClientToScreen(Point(Left, Top));
    Y := P.Y + Height;
    if Y + FActiveList.Height > Screen.Height then Y := P.Y - FActiveList.Height;
    SetWindowPos(FActiveList.Handle, HWND_TOP, P.X, Y, 0, 0,
      SWP_NOSIZE or SWP_NOACTIVATE or SWP_SHOWWINDOW);
    FListVisible := True;
    Invalidate;
    Windows.SetFocus(Handle);
  end;
end;

type
  TWinControlCracker = class(TWinControl) end;

procedure TDBGridInplaceEdit.KeyDown(var Key: Word; Shift: TShiftState);
begin
  if (EditStyle = esEllipsis) and (Key = VK_RETURN) and (Shift = [ssCtrl]) then
  begin
    TBrCustomDBGrid(Grid).EditButtonClick;
    KillMessage(Handle, WM_CHAR);
  end
  else
    inherited KeyDown(Key, Shift);
end;

procedure TDBGridInplaceEdit.ListMouseUp(Sender: TObject; Button: TMouseButton;
  Shift: TShiftState; X, Y: Integer);
begin
  if Button = mbLeft then
    CloseUp(PtInRect(FActiveList.ClientRect, Point(X, Y)));
end;

procedure TDBGridInplaceEdit.MouseDown(Button: TMouseButton; Shift: TShiftState;
  X, Y: Integer);
begin
  if (Button = mbLeft) and (FEditStyle <> esSimple) and
    OverButton(Point(X,Y)) then
  begin
    if FListVisible then
      CloseUp(False)
    else
    begin
      MouseCapture := True;
      FTracking := True;
      TrackButton(X, Y);
      if Assigned(FActiveList) then
        DropDown;
    end;
  end;

  inherited MouseDown(Button, Shift, X, Y);
end;

procedure TDBGridInplaceEdit.MouseMove(Shift: TShiftState; X, Y: Integer);
var
  ListPos: TPoint;
  MousePos: TSmallPoint;
begin
  if FTracking then
  begin
    TrackButton(X, Y);
    if FListVisible then
    begin
      ListPos := FActiveList.ScreenToClient(ClientToScreen(Point(X, Y)));
      if PtInRect(FActiveList.ClientRect, ListPos) then
      begin
        StopTracking;
        MousePos := PointToSmallPoint(ListPos);
        SendMessage(FActiveList.Handle, WM_LBUTTONDOWN, 0, Integer(MousePos));
        Exit;
      end;
    end;
  end;
  inherited MouseMove(Shift, X, Y);
end;

procedure TDBGridInplaceEdit.MouseUp(Button: TMouseButton; Shift: TShiftState;
  X, Y: Integer);
var
  WasPressed: Boolean;
begin
  WasPressed := FPressed;
  StopTracking;
// =-=-=-=-=-=-= Bravo
  if (Button = mbLeft) and
     ((FEditStyle = esEllipsis)    or (FEditStyle = esConsulta) or
      (FEditStyle = esCalculadora) or (FEditStyle = esData))  and
     (WasPressed) then
  begin
        case FEditStyle of
             esConsulta    : ChamarConsulta;
             esCalculadora : ChamarCalculadora;
             esData        : ChamarCalendario;
             else TBrCustomDBGrid(Grid).EditButtonClick;
        end;
  end;
// =-=-=-=-=-=-=-=-=

  inherited MouseUp(Button, Shift, X, Y);
end;

procedure TDBGridInplaceEdit.PaintWindow(DC: HDC);
var
  R: TRect;
  Flags: Integer;
  W, X, Y: Integer;
begin
  if FEditStyle <> esSimple then
  begin
    R := ButtonRect;
    Flags := 0;
    if FEditStyle in [esDataList, esPickList] then
    begin
      if FActiveList = nil then
        Flags := DFCS_INACTIVE
      else if FPressed then
        Flags := DFCS_FLAT or DFCS_PUSHED;
      DrawFrameControl(DC, R, DFC_SCROLL, Flags or DFCS_SCROLLCOMBOBOX);
    end
    else   { esEllipsis }
    begin
      if FPressed then Flags := BF_FLAT;
      DrawEdge(DC, R, EDGE_RAISED, BF_RECT or BF_MIDDLE or Flags);
      X := R.Left + ((R.Right - R.Left) shr 1) - 1 + Ord(FPressed);
      Y := R.Top + ((R.Bottom - R.Top) shr 1) - 1 + Ord(FPressed);
      W := FButtonWidth shr 3;
      if W = 0 then W := 1;
//=-=-=-=-=-= Bravo
      case FEditStyle of
           esEllipsis : begin
                              PatBlt(DC, X, Y, W, W, BLACKNESS);
                              PatBlt(DC, X - (W * 2), Y, W, W, BLACKNESS);
                              PatBlt(DC, X + (W * 2), Y, W, W, BLACKNESS);
                        end;
           esConsulta : begin
                               X := X -5;
                               Y := Y -5;
                            // =-=-=-=-=
                               PatBlt(DC, X + 3, Y + 2, 2, 2, BLACKNESS);
                               PatBlt(DC, X + 7, Y + 2, 2, 2, BLACKNESS);

                               PatBlt(DC, X + 2, Y + 4, 8, 1, BLACKNESS);

                               PatBlt(DC, X + 1, Y + 5, 1, 2, BLACKNESS);
                               PatBlt(DC, X + 2, Y + 5, 1, 2, WHITENESS);
                               PatBlt(DC, X + 3, Y + 5, 4, 2, BLACKNESS);
                               PatBlt(DC, X + 7, Y + 5, 1, 2, WHITENESS);
                               PatBlt(DC, X + 8, Y + 5, 3, 2, BLACKNESS);

                               PatBlt(DC, X + 1, Y + 7, 4, 1, BLACKNESS);
                               PatBlt(DC, X + 7, Y + 7, 4, 1, BLACKNESS);

                               PatBlt(DC, X + 1, Y + 8, 1, 1, BLACKNESS);
                               PatBlt(DC, X + 2, Y + 8, 1, 1, WHITENESS);
                               PatBlt(DC, X + 3, Y + 8, 1, 1, BLACKNESS);

                               PatBlt(DC, X + 8, Y + 8, 1, 1, BLACKNESS);
                               PatBlt(DC, X + 9, Y + 8, 1, 1, WHITENESS);
                               PatBlt(DC, X + 10, Y + 8, 1, 1, BLACKNESS);

                               PatBlt(DC, X + 1, Y + 9, 3, 1, BLACKNESS);
                               PatBlt(DC, X + 8, Y + 9, 3, 1, BLACKNESS);
                        end;
           esCalculadora : begin
                               X := X -5;
                               Y := Y -5;
                            // =-=-=-=-= cabecalho
                               PatBlt(DC, X + 2, Y + 1, 8, 1, BLACKNESS);

                            // =-=-=-=-= Lateral
                               for W := 1 to 9 do
                               begin
                                     PatBlt(DC, X + 1,  Y + 1 + W, 1, 1, BLACKNESS);
                                     PatBlt(DC, X + 10, Y + 1 + W, 1, 1, BLACKNESS);
                               end;
                            // =-=-=-=-= rodapé
                               PatBlt(DC, X + 2, Y + 11, 8, 1, BLACKNESS);
                            // =-=-=-=-= display
                               PatBlt(DC, X + 3, Y + 3, 6, 1, WHITENESS);

                            // =-=-=-=-= primeira linha botoes
                               Y := Y + 5;
                               for W := 1 to 3 do
                               begin
                                     PatBlt(DC, X + 3, Y, 1, 1, BLACKNESS);
                                     PatBlt(DC, X + 4, Y, 1, 1, WHITENESS);
                                     PatBlt(DC, X + 5, Y, 1, 1, BLACKNESS);
                                     PatBlt(DC, X + 6, Y, 1, 1, WHITENESS);
                                     PatBlt(DC, X + 7, Y, 1, 1, BLACKNESS);
                                     PatBlt(DC, X + 8, Y, 1, 1, WHITENESS);

                                     Y := Y + 2;
                               end;
                         end;
           esData:   begin
                               X := X -5;
                               Y := Y -5;
                            // =-=-=-=-=
                               PatBlt(DC, X + 0, Y + 00, 9, 2, BLACKNESS);
                               PatBlt(DC, X + 1, Y + 00, 1, 1, BLACKNESS);
                               PatBlt(DC, X + 1, Y + 01, 7, 1, WHITENESS);
                               PatBlt(DC, X + 0, Y + 02, 9, 1, BLACKNESS);

                               PatBlt(DC, X + 2, Y + 04, 2, 2, BLACKNESS);
                               PatBlt(DC, X + 2, Y + 07, 2, 2, BLACKNESS);

                               PatBlt(DC, X + 5, Y + 04, 2, 2, BLACKNESS);
                               PatBlt(DC, X + 5, Y + 07, 2, 2, BLACKNESS);

                               PatBlt(DC, X + 0, Y + 03, 1, 7, BLACKNESS);
                               PatBlt(DC, X + 8, Y + 03, 1, 7, BLACKNESS);
                               PatBlt(DC, X + 0, Y + 10, 9, 1, BLACKNESS);
                         end;
      end;
// =-=-=-=-=-=-=-=
    end;
    ExcludeClipRect(DC, R.Left, R.Top, R.Right, R.Bottom);
  end;
  inherited PaintWindow(DC);
end;

procedure TDBGridInplaceEdit.SetEditStyle(Value: TEditStyle);
begin
  if Value = FEditStyle then Exit;
  FEditStyle := Value;
  case Value of
    esPickList:
      begin
        if FPickList = nil then
        begin
// =-=-=-=-=-=-= Bravo
          gDsPickVa         := TPopupListbox.Create(Self);
          gDsPickVa.Visible := False;
          gDsPickVa.Parent  := Self;
// =-=-=-=-=-=-=-=-=-=-=-=
          FPickList := TPopupListbox.Create(Self);
          FPickList.Visible := False;
          FPickList.Parent := Self;
          FPickList.OnMouseUp := ListMouseUp;
          FPickList.IntegralHeight := True;
          FPickList.ItemHeight := 11;
        end;
        FActiveList := FPickList;
      end;
    esDataList:
      begin
        if FDataList = nil then
        begin
          FDataList := TPopupDataList.Create(Self);
          FDataList.Visible := False;
          FDataList.Parent := Self;
          FDataList.OnMouseUp := ListMouseUp;
        end;
        FActiveList := FDataList;
      end;
  else  { cbsNone, cbsEllipsis, or read only field }
    FActiveList := nil;
  end;
  with TBrCustomDBGrid(Grid) do
    Self.ReadOnly := Columns[SelectedIndex].ReadOnly;
  Repaint;
end;

procedure TDBGridInplaceEdit.StopTracking;
begin
  if FTracking then
  begin
    TrackButton(-1, -1);
    FTracking := False;
    MouseCapture := False;
  end;
end;

procedure TDBGridInplaceEdit.TrackButton(X,Y: Integer);
var
  NewState: Boolean;
  R: TRect;
begin
  R := ButtonRect;
  NewState := PtInRect(R, Point(X, Y));
  if FPressed <> NewState then
  begin
    FPressed := NewState;
    InvalidateRect(Handle, @R, False);
  end;
end;

procedure TDBGridInplaceEdit.UpdateContents;
var
  Column: TColumn;
  NewStyle: TEditStyle;
  MasterField: TField;
begin
  with TBrCustomDBGrid(Grid) do
    Column := Columns[SelectedIndex];
  NewStyle := esSimple;
  case Column.ButtonStyle of
   cbsEllipsis: NewStyle := esEllipsis;
// =-=-=-=-=-=-=-=-=-= Bravo
   cbsConsulta    : NewStyle := esConsulta;
   cbsCalculadora : NewStyle := esCalculadora;
   cbsData        : NewStyle := esData;
// =-=-=-=-=-=-=-=
   cbsAuto:
     if Assigned(Column.Field) then
     with Column.Field do
     begin
       { Show the dropdown button only if the field is editable }
       if FieldKind = fkLookup then
       begin
         MasterField := Dataset.FieldByName(KeyFields);
         { Column.DefaultReadonly will always be True for a lookup field.
           Test if Column.ReadOnly has been assigned a value of True }
         if Assigned(MasterField) and MasterField.CanModify and
           not ((cvReadOnly in Column.AssignedValues) and Column.ReadOnly) then
           with TBrCustomDBGrid(Grid) do
             if not ReadOnly and DataLink.Active and not Datalink.ReadOnly then
               NewStyle := esDataList
       end
       else
       if Assigned(Column.Picklist) and (Column.PickList.Count > 0) and
         not Column.Readonly then
         NewStyle := esPickList
       else if DataType in [ftDataset, ftReference] then
         NewStyle := esEllipsis;
     end;
  end;
  EditStyle := NewStyle;
// =-=-=-=-=-= Bravo
  inherited UpdateContents; // ativo novamente em 21/02/2008 jefferson
    if (Assigned(Column.Field)) and
       (not TBrCustomDBGrid(Grid).ReadOnly) and
       (TBrCustomDBGrid(Grid).DataLink.Active) and
       (not TBrCustomDBGrid(Grid).Datalink.ReadOnly) then
    begin
          EditMask  := TBrCustomDBGrid(Grid).GetEditMask(
                                             TBrCustomDBGrid(Grid).Col,
                                             TBrCustomDBGrid(Grid).Row);
          Text      := TBrCustomDBGrid(Grid).EncontrarTextoPickList(Column);
          MaxLength := TBrCustomDBGrid(Grid).GetEditLimit;
    end;
// =-=-=-=-=-=-=-=-=-=-=-
  Font.Assign(Column.Font);
  ImeMode := Column.ImeMode;
  ImeName := Column.ImeName;
end;

procedure TDBGridInplaceEdit.CMCancelMode(var Message: TCMCancelMode);
begin
  if (Message.Sender <> Self) and (Message.Sender <> FActiveList) then
    CloseUp(False);
end;

procedure TDBGridInplaceEdit.WMCancelMode(var Message: TMessage);
begin
  StopTracking;
  inherited;
end;

procedure TDBGridInplaceEdit.WMKillFocus(var Message: TMessage);
begin
  if not SysLocale.FarEast then inherited
  else
  begin
    ImeName := Screen.DefaultIme;
    ImeMode := imDontCare;
    inherited;
    if HWND(Message.WParam) <> TBrCustomDBGrid(Grid).Handle then
      ActivateKeyboardLayout(Screen.DefaultKbLayout, KLF_ACTIVATE);
  end;
  CloseUp(False);
end;

function TDBGridInplaceEdit.ButtonRect: TRect;
begin
  if not TBrCustomDBGrid(Owner).UseRightToLeftAlignment then
    Result := Rect(Width - FButtonWidth, 0, Width, Height)
  else
    Result := Rect(0, 0, FButtonWidth, Height);
end;

function TDBGridInplaceEdit.OverButton(const P: TPoint): Boolean;
begin
  Result := PtInRect(ButtonRect, P);
end;

procedure TDBGridInplaceEdit.WMLButtonDblClk(var Message: TWMLButtonDblClk);
begin
  with Message do
  if (FEditStyle <> esSimple) and OverButton(Point(XPos, YPos)) then
    Exit;
  inherited;
end;

procedure TDBGridInplaceEdit.WMPaint(var Message: TWMPaint);
begin
  PaintHandler(Message);
end;

procedure TDBGridInplaceEdit.WMSetCursor(var Message: TWMSetCursor);
var
  P: TPoint;
begin
  GetCursorPos(P);
  P := ScreenToClient(P);
  if (FEditStyle <> esSimple) and OverButton(P) then
    Windows.SetCursor(LoadCursor(0, idc_Arrow))
  else
    inherited;
end;

procedure TDBGridInplaceEdit.WndProc(var Message: TMessage);
begin
  case Message.Msg of
    wm_KeyDown, wm_SysKeyDown, wm_Char:
      if EditStyle in [esPickList, esDataList] then
      with TWMKey(Message) do
      begin
        DoDropDownKeys(CharCode, KeyDataToShiftState(KeyData));
        if (CharCode <> 0) and FListVisible then
        begin
          with TMessage(Message) do
            SendMessage(FActiveList.Handle, Msg, WParam, LParam);
          Exit;
        end;
      end
  end;
  inherited;
end;

// =-=-=-= Bravo DbGridInplaceEdit Inicio
procedure TDBGridInplaceEdit.ChamarConsulta;
var lBrConsul : TBrvConsulta;
begin
      if TBrDbGrids(Grid).BrDicionario = nil then
      begin
            raise Exception.Create('Consulta chamada pela grade ' +
                                   TBrDbGrids(Grid).Name +
                                   ' no formulário ' + TBrDbGrids(Grid).Owner.Name +
                                   ' está com o dicionário de dados indefinido.');
      end;

      if not TBrDbGrids(Grid).Columns[
                              TBrDbGrids(Grid).SelectedIndex].ReadOnly then
      begin
        // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        // =-=-=-=-=-  Executando BeforeConsulta =-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if Assigned(TBrDbGrids(Grid).Columns[
                     TBrDbGrids(Grid).SelectedIndex].BrOnBeforeConsul) then
            begin
                  TBrDbGrids(Grid).Columns[
                          TBrDbGrids(Grid).SelectedIndex].BrOnBeforeConsul(
                                TBrDbGrids(Grid).Columns[TBrDbGrids(Grid).SelectedIndex]);
            end;
        // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        // =-=-=-=-=-  Executando a consulta  -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
           if  TBrDbGrids(Grid).Columns[TBrDbGrids(Grid).SelectedIndex].BrConsulta > 0 then
           begin
                  try
                      lBrConsul := TBrvConsulta.Create(Owner);
                      lBrConsul.BrQueryCode        := TBrDbGrids(Grid).Columns[
                                               TBrDbGrids(Grid).SelectedIndex].BrConsulta;
                      lBrConsul.BrDataSource       := TBrDbGrids(Grid).DataSource;
                      lBrConsul.BrDicionario       := TBrDbGrids(Grid).BrDicionario;
                      lBrConsul.BrConfigurar.Text  := TBrDbGrids(Grid).Columns[
                                        TBrDbGrids(Grid).SelectedIndex].BrConfigurar.Text;
                      lBrConsul.BrParams.Text      := TBrDbGrids(Grid).Columns[
                                            TBrDbGrids(Grid).SelectedIndex].BrParams.Text;
                      lBrConsul.BrExecute('');
                  finally
                      FreeAndNil(lBrConsul);
                  end;
           end;

        // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        // =-=-=-=-=-  Executando AfterConsulta  =-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
           if Assigned(TBrDbGrids(Grid).Columns[
                                     TBrDbGrids(Grid).SelectedIndex].BrOnAfterConsul) then
           begin
                 TBrDbGrids(Grid).Columns[
                           TBrDbGrids(Grid).SelectedIndex].BrOnAfterConsul(
                                TBrDbGrids(Grid).Columns[TBrDbGrids(Grid).SelectedIndex]);
           end;
      end;
end;

procedure TDBGridInplaceEdit.ChamarCalculadora;
var lBrvCalcula : TBrvCalculadora;
    lVlAtual    : Real;
    lVlRetorn   : Real;
begin
      if not TBrDbGrids(Grid).Columns[TBrDbGrids(Grid).SelectedIndex].ReadOnly then
      begin
            lBrvCalcula := TBrvCalculadora.Create(Self);

            try
                lVlRetorn := TBrDbGrids(Grid).Columns[
                                            TBrDbGrids(Grid).SelectedIndex].Field.AsFloat;
                lVlAtual := lBrvCalcula.Execute(lVlRetorn);

                if lVlRetorn <> lVlAtual then
                begin
                      if not (TBrDbGrids(Grid).DataSource.State in
                                                                  [dsInsert, dsEdit]) then
                      begin
                            TBrDbGrids(Grid).DataSource.DataSet.Edit;
                      end;

                      case TBrDbGrids(Grid).Columns[
                                         TBrDbGrids(Grid).SelectedIndex].Field.DataType of
                           ftInteger : TBrDbGrids(Grid).Columns[TBrDbGrids(Grid).
                                           SelectedIndex].Field.AsString :=
                                                               FormatFloat('0', lVlAtual);
                           ftFloat   : TBrDbGrids(Grid).Columns[TBrDbGrids(Grid).
                                         SelectedIndex].Field.AsFloat   := lVlAtual;
                      end;
                end;
            finally
                FreeAndNil(lBrvCalcula);
            end;
      end;
end;

procedure  TDBGridInplaceEdit.ChamarCalendario;
var lBrvCalend : TBrvCalendario;
    lSnExecut  : Boolean;
    lDtAtual   : TDate;
begin
      SetFocus;
      if not ReadOnly then
      begin
            lBrvCalend := TBrvCalendario.Create(Self);

            try
                lDtAtual := TBrDbGrids(Grid).Columns[TBrDbGrids(Grid).
                                                          SelectedIndex].Field.AsDateTime;
                lBrvCalend.Execute(lDtAtual, lSnExecut);

                if lSnExecut then
                begin
                      if not (TBrDbGrids(Grid).DataSource.State in
                                                                 [dsInsert, dsEdit]) then
                      begin
                            TBrDbGrids(Grid).DataSource.DataSet.Edit;
                      end;

                      TBrDbGrids(Grid).Columns[TBrDbGrids(Grid).
                                              SelectedIndex].Field.AsDateTime := lDtAtual;
                end;
            finally
                FreeAndNil(lBrvCalend);
            end;
      end;
end;

// Bravo =-=-=-=-
procedure TDBGridInplaceEdit.KeyPress(var Key: Char);
begin
      if (TBrDbGrids(Grid).Columns[
                   TBrDbGrids(Grid).SelectedIndex].Field is TBrStringField) then
      begin
            case (TBrDbGrids(Grid).Columns[
                  TBrDbGrids(Grid).SelectedIndex].Field as TBrStringField).BrCharCase of
                 BrSfUpperCase : key := BrUpCase(key);
                 BrSfLowerCase : key := BrLwCase(key);
            end;
      end;

      inherited KeyPress(Key);
end;
// =-=-=-=-=-=-=-=

{ TGridDataLink }

type
  TIntArray = array[0..MaxMapSize] of Integer;
  PIntArray = ^TIntArray;

constructor TGridDataLink.Create(AGrid: TBrCustomDBGrid);
begin
  inherited Create;
  FGrid := AGrid;
  VisualControl := True;
end;

destructor TGridDataLink.Destroy;
begin
  ClearMapping;
  inherited Destroy;
end;

function TGridDataLink.GetDefaultFields: Boolean;
var
  I: Integer;
begin
  Result := True;
  if DataSet <> nil then Result := DataSet.DefaultFields;
  if Result and SparseMap then
  for I := 0 to FFieldCount-1 do
    if FFieldMap[I] < 0 then
    begin
      Result := False;
      Exit;
    end;
end;

function TGridDataLink.GetFields(I: Integer): TField;
begin
  if (0 <= I) and (I < FFieldCount) and (FFieldMap[I] >= 0) then
    Result := DataSet.FieldList[FFieldMap[I]]
  else
    Result := nil;
end;

function TGridDataLink.AddMapping(const FieldName: string): Boolean;
var
  Field: TField;
  NewSize: Integer;
begin
  Result := True;
  if FFieldCount >= MaxMapSize then RaiseGridError(STooManyColumns);
  if SparseMap then
    Field := DataSet.FindField(FieldName)
  else
    Field := DataSet.FieldByName(FieldName);

  if FFieldCount = Length(FFieldMap) then
  begin
    NewSize := Length(FFieldMap);
    if NewSize = 0 then
      NewSize := 8
    else
      Inc(NewSize, NewSize);
    if (NewSize < FFieldCount) then
      NewSize := FFieldCount + 1;
    if (NewSize > MaxMapSize) then
      NewSize := MaxMapSize;
    SetLength(FFieldMap, NewSize);
  end;
  if Assigned(Field) then
  begin
    FFieldMap[FFieldCount] := Dataset.FieldList.IndexOfObject(Field);
    Field.FreeNotification(FGrid);
  end
  else
    FFieldMap[FFieldCount] := -1;
  Inc(FFieldCount);
end;

procedure TGridDataLink.ActiveChanged;
begin
  FGrid.LinkActive(Active);
  FModified := False;
end;

procedure TGridDataLink.ClearMapping;
begin
  FFieldMap := nil;
  FFieldCount := 0;
end;

procedure TGridDataLink.Modified;
begin
  FModified := True;
end;

procedure TGridDataLink.DataSetChanged;
begin
  FGrid.DataChanged;
  FModified := False;
end;

procedure TGridDataLink.DataSetScrolled(Distance: Integer);
begin
  FGrid.Scroll(Distance);
end;

procedure TGridDataLink.LayoutChanged;
var
  SaveState: Boolean;
begin
  { FLayoutFromDataset determines whether default column width is forced to
    be at least wide enough for the column title.  }
  SaveState := FGrid.FLayoutFromDataset;
  FGrid.FLayoutFromDataset := True;
  try
    FGrid.LayoutChanged;
  finally
    FGrid.FLayoutFromDataset := SaveState;
  end;
  inherited LayoutChanged;
end;

procedure TGridDataLink.FocusControl(Field: TFieldRef);
begin
  if Assigned(Field) and Assigned(Field^) then
  begin
    FGrid.SelectedField := Field^;
    if (FGrid.SelectedField = Field^) and FGrid.AcquireFocus then
    begin
      Field^ := nil;
      FGrid.ShowEditor;
    end;
  end;
end;

procedure TGridDataLink.EditingChanged;
begin
  FGrid.EditingChanged;
end;

procedure TGridDataLink.RecordChanged(Field: TField);
begin
  FGrid.RecordChanged(Field);
  FModified := False;
end;

procedure TGridDataLink.UpdateData;
begin
  FInUpdateData := True;
  try
    if FModified then FGrid.UpdateData;

    FModified := False;
  finally
    FInUpdateData := False;
  end;
end;

function TGridDataLink.GetMappedIndex(ColIndex: Integer): Integer;
begin
  if (0 <= ColIndex) and (ColIndex < FFieldCount) then
    Result := FFieldMap[ColIndex]
  else
    Result := -1;
end;

procedure TGridDataLink.Reset;
begin
  if FModified then RecordChanged(nil) else Dataset.Cancel;
end;

function TGridDataLink.IsAggRow(Value: Integer): Boolean;
begin
  Result := False;
end;

procedure TGridDataLink.BuildAggMap;
begin
end;

{ TColumnTitle }
constructor TColumnTitle.Create(Column: TColumn);
begin
  inherited Create;
  FColumn := Column;
  FFont := TFont.Create;
  FFont.Assign(DefaultFont);
  FFont.OnChange := FontChanged;
end;

destructor TColumnTitle.Destroy;
begin
  FFont.Free;
  inherited Destroy;
end;

procedure TColumnTitle.Assign(Source: TPersistent);
begin
  if Source is TColumnTitle then
  begin
    if cvTitleAlignment in TColumnTitle(Source).FColumn.FAssignedValues then
      Alignment := TColumnTitle(Source).Alignment;
    if cvTitleColor in TColumnTitle(Source).FColumn.FAssignedValues then
      Color := TColumnTitle(Source).Color;
    if cvTitleCaption in TColumnTitle(Source).FColumn.FAssignedValues then
      Caption := TColumnTitle(Source).Caption;
    if cvTitleFont in TColumnTitle(Source).FColumn.FAssignedValues then
      Font := TColumnTitle(Source).Font;
  end
  else
    inherited Assign(Source);
end;

function TColumnTitle.DefaultAlignment: TAlignment;
begin
  Result := taLeftJustify;
end;

function TColumnTitle.DefaultColor: TColor;
var
  Grid: TBrCustomDBGrid;
begin
  Grid := FColumn.GetGrid;
  if Assigned(Grid) then
    Result := Grid.FixedColor
  else
    Result := clBtnFace;
end;

function TColumnTitle.DefaultFont: TFont;
var
  Grid: TBrCustomDBGrid;
begin
  Grid := FColumn.GetGrid;
  if Assigned(Grid) then
    Result := Grid.TitleFont
  else
    Result := FColumn.Font;
end;

function TColumnTitle.DefaultCaption: string;
var
  Field: TField;
begin
  Field := FColumn.Field;
  if Assigned(Field) then
    Result := Field.DisplayName
  else
    Result := FColumn.FieldName;
end;

procedure TColumnTitle.FontChanged(Sender: TObject);
begin
  Include(FColumn.FAssignedValues, cvTitleFont);
  FColumn.Changed(True);
end;

function TColumnTitle.GetAlignment: TAlignment;
begin
  if cvTitleAlignment in FColumn.FAssignedValues then
    Result := FAlignment
  else
    Result := DefaultAlignment;
end;

function TColumnTitle.GetColor: TColor;
begin
  if cvTitleColor in FColumn.FAssignedValues then
    Result := FColor
  else
    Result := DefaultColor;
end;

function TColumnTitle.GetCaption: string;
begin
  if cvTitleCaption in FColumn.FAssignedValues then
    Result := FCaption
  else
    Result := DefaultCaption;
end;

function TColumnTitle.GetFont: TFont;
var
  Save: TNotifyEvent;
  Def: TFont;
begin
  if not (cvTitleFont in FColumn.FAssignedValues) then
  begin
    Def := DefaultFont;
    if (FFont.Handle <> Def.Handle) or (FFont.Color <> Def.Color) then
    begin
      Save := FFont.OnChange;
      FFont.OnChange := nil;
      FFont.Assign(DefaultFont);
      FFont.OnChange := Save;
    end;
  end;
  Result := FFont;
end;

function TColumnTitle.IsAlignmentStored: Boolean;
begin
  Result := (cvTitleAlignment in FColumn.FAssignedValues) and
    (FAlignment <> DefaultAlignment);
end;

function TColumnTitle.IsColorStored: Boolean;
begin
  Result := (cvTitleColor in FColumn.FAssignedValues) and
    (FColor <> DefaultColor);
end;

function TColumnTitle.IsFontStored: Boolean;
begin
  Result := (cvTitleFont in FColumn.FAssignedValues);
end;

function TColumnTitle.IsCaptionStored: Boolean;
begin
  Result := (cvTitleCaption in FColumn.FAssignedValues) and
    (FCaption <> DefaultCaption);
end;

procedure TColumnTitle.RefreshDefaultFont;
var
  Save: TNotifyEvent;
begin
  if (cvTitleFont in FColumn.FAssignedValues) then Exit;
  Save := FFont.OnChange;
  FFont.OnChange := nil;
  try
    FFont.Assign(DefaultFont);
  finally
    FFont.OnChange := Save;
  end;
end;

procedure TColumnTitle.RestoreDefaults;
var
  FontAssigned: Boolean;
begin
  FontAssigned := cvTitleFont in FColumn.FAssignedValues;
  FColumn.FAssignedValues := FColumn.FAssignedValues - ColumnTitleValues;
  FCaption := '';
  RefreshDefaultFont;
  { If font was assigned, changing it back to default may affect grid title
    height, and title height changes require layout and redraw of the grid. }
  FColumn.Changed(FontAssigned);
end;

procedure TColumnTitle.SetAlignment(Value: TAlignment);
begin
  if (cvTitleAlignment in FColumn.FAssignedValues) and (Value = FAlignment) then Exit;
  FAlignment := Value;
  Include(FColumn.FAssignedValues, cvTitleAlignment);
  FColumn.Changed(False);
end;

procedure TColumnTitle.SetColor(Value: TColor);
begin
  if (cvTitleColor in FColumn.FAssignedValues) and (Value = FColor) then Exit;
  FColor := Value;
  Include(FColumn.FAssignedValues, cvTitleColor);
  FColumn.Changed(False);
end;

procedure TColumnTitle.SetFont(Value: TFont);
begin
  FFont.Assign(Value);
end;

procedure TColumnTitle.SetCaption(const Value: string);
var
  Grid: TBrCustomDBGrid;
begin
  if Column.IsStored then
  begin
    if (cvTitleCaption in FColumn.FAssignedValues) and (Value = FCaption) then Exit;
    FCaption := Value;
    Include(Column.FAssignedValues, cvTitleCaption);
    Column.Changed(False);
  end
  else
  begin
    Grid := Column.GetGrid;
    if Assigned(Grid) and (Grid.Datalink.Active) and Assigned(Column.Field) then
      Column.Field.DisplayLabel := Value;
  end;
end;


{ TColumn }

constructor TColumn.Create(Collection: TCollection);
var
  Grid: TBrCustomDBGrid;
begin
// =-=-=-=-=-=-= Bravo
    gDsConfig  := TStringList.Create;
    gDsParams  := TStringList.Create;
    gDsPickVa  := TStringList.Create;
// =-=-=-=-=-=-=-=-=

  Grid := nil;
  if Assigned(Collection) and (Collection is TDBGridColumns) then
    Grid := TDBGridColumns(Collection).Grid;
  if Assigned(Grid) then Grid.BeginLayout;
  try
    inherited Create(Collection);
    FDropDownRows := 7;
    FButtonStyle := cbsAuto;
    FFont := TFont.Create;
    FFont.Assign(DefaultFont);
    FFont.OnChange := FontChanged;
    FImeMode := imDontCare;
    FImeName := Screen.DefaultIme;
    FTitle := CreateTitle;
    FVisible := True;
    FExpanded := True;
    FStored := True;
  finally
    if Assigned(Grid) then Grid.EndLayout;
  end;
end;

destructor TColumn.Destroy;
begin
// =-=-=-=-=-= Bravo
  gDsConfig.Destroy;
  gDsParams.Destroy;
  gDsPickVa.Destroy;
// =-=-=-=-=-=-=-=

  FTitle.Free;
  FFont.Free;
  FPickList.Free;
  inherited Destroy;
end;

procedure TColumn.Assign(Source: TPersistent);
begin
  if Source is TColumn then
  begin
    if Assigned(Collection) then Collection.BeginUpdate;
    try
      RestoreDefaults;
      FieldName := TColumn(Source).FieldName;
      if cvColor in TColumn(Source).AssignedValues then
        Color := TColumn(Source).Color;
      if cvWidth in TColumn(Source).AssignedValues then
        Width := TColumn(Source).Width;
      if cvFont in TColumn(Source).AssignedValues then
        Font := TColumn(Source).Font;
      if cvImeMode in TColumn(Source).AssignedValues then
        ImeMode := TColumn(Source).ImeMode;
      if cvImeName in TColumn(Source).AssignedValues then
        ImeName := TColumn(Source).ImeName;
      if cvAlignment in TColumn(Source).AssignedValues then
        Alignment := TColumn(Source).Alignment;
      if cvReadOnly in TColumn(Source).AssignedValues then
        ReadOnly := TColumn(Source).ReadOnly;
      Title := TColumn(Source).Title;
      DropDownRows := TColumn(Source).DropDownRows;
      ButtonStyle := TColumn(Source).ButtonStyle;
      PickList := TColumn(Source).PickList;
      PopupMenu := TColumn(Source).PopupMenu;
      FVisible := TColumn(Source).FVisible;
      FExpanded := TColumn(Source).FExpanded;
    finally
      if Assigned(Collection) then Collection.EndUpdate;
    end;
  end
  else
    inherited Assign(Source);
end;

function TColumn.CreateTitle: TColumnTitle;
begin
  Result := TColumnTitle.Create(Self);
end;

function TColumn.DefaultAlignment: TAlignment;
begin
  if Assigned(Field) then
    Result := FField.Alignment
  else
    Result := taLeftJustify;
end;

function TColumn.DefaultColor: TColor;
var
  Grid: TBrCustomDBGrid;
begin
  Grid := GetGrid;
  if Assigned(Grid) then
    Result := Grid.Color
  else
    Result := clWindow;
end;

function TColumn.DefaultFont: TFont;
var
  Grid: TBrCustomDBGrid;
begin
  Grid := GetGrid;
  if Assigned(Grid) then
    Result := Grid.Font
  else
    Result := FFont;
end;

function TColumn.DefaultImeMode: TImeMode;
var
  Grid: TBrCustomDBGrid;
begin
  Grid := GetGrid;
  if Assigned(Grid) then
    Result := Grid.ImeMode
  else
    Result := FImeMode;
end;

function TColumn.DefaultImeName: TImeName;
var
  Grid: TBrCustomDBGrid;
begin
  Grid := GetGrid;
  if Assigned(Grid) then
    Result := Grid.ImeName
  else
    Result := FImeName;
end;

function TColumn.DefaultReadOnly: Boolean;
var
  Grid: TBrCustomDBGrid;
begin
  Grid := GetGrid;
  Result := (Assigned(Grid) and Grid.ReadOnly) or
    (Assigned(Field) and FField.ReadOnly);
end;

function TColumn.DefaultWidth: Integer;
var
  W: Integer;
  RestoreCanvas: Boolean;
  TM: TTextMetric;
begin
  if GetGrid = nil then
  begin
    Result := 64;
    Exit;
  end;
  with GetGrid do
  begin
    if Assigned(Field) then
    begin
      RestoreCanvas := not HandleAllocated;
      if RestoreCanvas then
        Canvas.Handle := GetDC(0);
      try
        Canvas.Font := Self.Font;
        GetTextMetrics(Canvas.Handle, TM);
        Result := Field.DisplayWidth * (Canvas.TextWidth('0') - TM.tmOverhang)
          + TM.tmOverhang + 4;
        if dgTitles in Options then
        begin
          Canvas.Font := Title.Font;
          W := Canvas.TextWidth(Title.Caption) + 4;
          if Result < W then
            Result := W;
        end;
      finally
        if RestoreCanvas then
        begin
          ReleaseDC(0,Canvas.Handle);
          Canvas.Handle := 0;
        end;
      end;
    end
    else
      Result := DefaultColWidth;
  end;
end;

procedure TColumn.FontChanged;
begin
  Include(FAssignedValues, cvFont);
  Title.RefreshDefaultFont;
  Changed(False);
end;

function TColumn.GetAlignment: TAlignment;
begin
  if cvAlignment in FAssignedValues then
    Result := FAlignment
  else
    Result := DefaultAlignment;
end;

function TColumn.GetColor: TColor;
begin
  if cvColor in FAssignedValues then
    Result := FColor
  else
    Result := DefaultColor;
end;

function TColumn.GetExpanded: Boolean;
begin
  Result := FExpanded and Expandable;
end;

function TColumn.GetField: TField;
var
  Grid: TBrCustomDBGrid;
begin    { Returns Nil if FieldName can't be found in dataset }
  Grid := GetGrid;
  if (FField = nil) and (Length(FFieldName) > 0) and Assigned(Grid) and
    Assigned(Grid.DataLink.DataSet) then
  with Grid.Datalink.Dataset do
    if Active or (not DefaultFields) then
      SetField(FindField(FieldName));
  Result := FField;
end;

function TColumn.GetFont: TFont;
var
  Save: TNotifyEvent;
begin
  if not (cvFont in FAssignedValues) and (FFont.Handle <> DefaultFont.Handle) then
  begin
    Save := FFont.OnChange;
    FFont.OnChange := nil;
    FFont.Assign(DefaultFont);
    FFont.OnChange := Save;
  end;
  Result := FFont;
end;

function TColumn.GetGrid: TBrCustomDBGrid;
begin
  if Assigned(Collection) and (Collection is TDBGridColumns) then
    Result := TDBGridColumns(Collection).Grid
  else
    Result := nil;
end;

function TColumn.GetDisplayName: string;
begin
  Result := FFieldName;
  if Result = '' then Result := inherited GetDisplayName;
end;

function TColumn.GetImeMode: TImeMode;
begin
  if cvImeMode in FAssignedValues then
    Result := FImeMode
  else
    Result := DefaultImeMode;
end;

function TColumn.GetImeName: TImeName;
begin
  if cvImeName in FAssignedValues then
    Result := FImeName
  else
    Result := DefaultImeName;
end;

function TColumn.GetParentColumn: TColumn;
var
  Col: TColumn;
  Fld: TField;
  I: Integer;
begin
  Result := nil;
  Fld := Field;
  if (Fld <> nil) and (Fld.ParentField <> nil) and (Collection <> nil) then
    for I := Index - 1 downto 0 do
    begin
      Col := TColumn(Collection.Items[I]);
      if Fld.ParentField = Col.Field then
      begin
        Result := Col;
        Exit;
      end;
    end;
end;

function TColumn.GetPickList: TStrings;
begin
  if FPickList = nil then
    FPickList := TStringList.Create;
  Result := FPickList;
end;

function TColumn.GetReadOnly: Boolean;
begin
  if cvReadOnly in FAssignedValues then
    Result := FReadOnly
  else
    Result := DefaultReadOnly;
end;

function TColumn.GetShowing: Boolean;
var
  Col: TColumn;
begin
  Result := not Expanded and Visible;
  if Result then
  begin
    Col := Self;
    repeat
      Col := Col.ParentColumn;
    until (Col = nil) or not Col.Expanded;
    Result := Col = nil;
  end;
end;

function TColumn.GetVisible: Boolean;
var
  Col: TColumn;
begin
  Result := FVisible;
  if Result then
  begin
    Col := ParentColumn;
    Result := Result and ((Col = nil) or Col.Visible);
  end;
end;

function TColumn.GetWidth: Integer;
begin
  if not Showing then
    Result := -1
  else if cvWidth in FAssignedValues then
    Result := FWidth
  else
    Result := DefaultWidth;
end;

function TColumn.IsAlignmentStored: Boolean;
begin
  Result := (cvAlignment in FAssignedValues) and (FAlignment <> DefaultAlignment);
end;

function TColumn.IsColorStored: Boolean;
begin
  Result := (cvColor in FAssignedValues) and (FColor <> DefaultColor);
end;

function TColumn.IsFontStored: Boolean;
begin
  Result := (cvFont in FAssignedValues);
end;

function TColumn.IsImeModeStored: Boolean;
begin
  Result := (cvImeMode in FAssignedValues) and (FImeMode <> DefaultImeMode);
end;

function TColumn.IsImeNameStored: Boolean;
begin
  Result := (cvImeName in FAssignedValues) and (FImeName <> DefaultImeName);
end;

function TColumn.IsReadOnlyStored: Boolean;
begin
  Result := (cvReadOnly in FAssignedValues) and (FReadOnly <> DefaultReadOnly);
end;

function TColumn.IsWidthStored: Boolean;
begin
  Result := (cvWidth in FAssignedValues) and (FWidth <> DefaultWidth);
end;

procedure TColumn.RefreshDefaultFont;
var
  Save: TNotifyEvent;
begin
  if cvFont in FAssignedValues then Exit;
  Save := FFont.OnChange;
  FFont.OnChange := nil;
  try
    FFont.Assign(DefaultFont);
  finally
    FFont.OnChange := Save;
  end;
end;

procedure TColumn.RestoreDefaults;
var
  FontAssigned: Boolean;
begin
  FontAssigned := cvFont in FAssignedValues;
  FTitle.RestoreDefaults;
  FAssignedValues := [];
  RefreshDefaultFont;
  FPickList.Free;
  FPickList := nil;
  ButtonStyle := cbsAuto;
  Changed(FontAssigned);
end;

procedure TColumn.SetAlignment(Value: TAlignment);
var
  Grid: TBrCustomDBGrid;
begin
  if IsStored then
  begin
    if (cvAlignment in FAssignedValues) and (Value = FAlignment) then Exit;
    FAlignment := Value;
    Include(FAssignedValues, cvAlignment);
    Changed(False);
  end
  else
  begin
    Grid := GetGrid;
    if Assigned(Grid) and (Grid.Datalink.Active) and Assigned(Field) then
      Field.Alignment := Value;
  end;
end;

procedure TColumn.SetButtonStyle(Value: TColumnButtonStyle);
begin
  if Value = FButtonStyle then Exit;
  FButtonStyle := Value;
  Changed(False);
end;

procedure TColumn.SetColor(Value: TColor);
begin
  if (cvColor in FAssignedValues) and (Value = FColor) then Exit;
  FColor := Value;
  Include(FAssignedValues, cvColor);
  Changed(False);
end;

procedure TColumn.SetField(Value: TField);
begin
  if FField = Value then Exit;
  if Assigned(FField) and
     (GetGrid <> nil) then
    FField.RemoveFreeNotification(GetGrid);
  FField := Value;
  if Assigned(Value) then
  begin
    if GetGrid <> nil then
      FField.FreeNotification(GetGrid);
    FFieldName := Value.FullName;
  end;
  if not IsStored then
  begin
    if Value = nil then
      FFieldName := '';
    RestoreDefaults;
  end;
  Changed(False);
end;

procedure TColumn.SetFieldName(const Value: String);
var
  AField: TField;
  Grid: TBrCustomDBGrid;
begin
  AField := nil;
  Grid := GetGrid;
  if Assigned(Grid) and Assigned(Grid.DataLink.DataSet) and
    not (csLoading in Grid.ComponentState) and (Length(Value) > 0) then
      AField := Grid.DataLink.DataSet.FindField(Value); { no exceptions }
  FFieldName := Value;
  SetField(AField);
  Changed(False);
end;

procedure TColumn.SetFont(Value: TFont);
begin
  FFont.Assign(Value);
  Include(FAssignedValues, cvFont);
  Changed(False);
end;

procedure TColumn.SetImeMode(Value: TImeMode);
begin
  if (cvImeMode in FAssignedValues) or (Value <> DefaultImeMode) then
  begin
    FImeMode := Value;
    Include(FAssignedValues, cvImeMode);
  end;
  Changed(False);
end;

procedure TColumn.SetImeName(Value: TImeName);
begin
  if (cvImeName in FAssignedValues) or (Value <> DefaultImeName) then
  begin
    FImeName := Value;
    Include(FAssignedValues, cvImeName);
  end;
  Changed(False);
end;

procedure TColumn.SetIndex(Value: Integer);
var
  Grid: TBrCustomDBGrid;
  Fld: TField;
  I, OldIndex: Integer;
  Col: TColumn;
begin
  OldIndex := Index;
  Grid := GetGrid;

  if IsStored then
  begin
    Grid.BeginLayout;
    try
      I := OldIndex + 1;  // move child columns along with parent
      while (I < Collection.Count) and (TColumn(Collection.Items[I]).ParentColumn = Self) do
        Inc(I);
      Dec(I);
      if OldIndex > Value then   // column moving left
      begin
        while I > OldIndex do
        begin
          Collection.Items[I].Index := Value;
          Inc(OldIndex);
        end;
        inherited SetIndex(Value);
      end
      else
      begin
        inherited SetIndex(Value);
        while I > OldIndex do
        begin
          Collection.Items[OldIndex].Index := Value;
          Dec(I);
        end;
      end;
    finally
      Grid.EndLayout;
    end;
  end
  else
  begin
    if (Grid <> nil) and Grid.Datalink.Active then
    begin
      if Grid.AcquireLayoutLock then
      try
        Col := Grid.ColumnAtDepth(Grid.Columns[Value], Depth);
        if (Col <> nil) then
        begin
          Fld := Col.Field;
          if Assigned(Fld) then
            Field.Index := Fld.Index;
        end;
      finally
        Grid.EndLayout;
      end;
    end;
    inherited SetIndex(Value);
  end;
end;

procedure TColumn.SetPickList(Value: TStrings);
begin
  if Value = nil then
  begin
    FPickList.Free;
    FPickList := nil;
    Exit;
  end;
  PickList.Assign(Value);
end;

procedure TColumn.SetPopupMenu(Value: TPopupMenu);
begin
  FPopupMenu := Value;
  if Value <> nil then Value.FreeNotification(GetGrid);
end;

procedure TColumn.SetReadOnly(Value: Boolean);
var
  Grid: TBrCustomDBGrid;
begin
  Grid := GetGrid;
  if not IsStored and Assigned(Grid) and Grid.Datalink.Active and Assigned(Field) then
    Field.ReadOnly := Value
  else
  begin
    if (cvReadOnly in FAssignedValues) and (Value = FReadOnly) then Exit;
    FReadOnly := Value;
    Include(FAssignedValues, cvReadOnly);
    Changed(False);
  end;
end;

procedure TColumn.SetTitle(Value: TColumnTitle);
begin
  FTitle.Assign(Value);
end;

procedure TColumn.SetWidth(Value: Integer);
var
  Grid: TBrCustomDBGrid;
  TM: TTextMetric;
  DoSetWidth: Boolean;
begin
  DoSetWidth := IsStored;
  if not DoSetWidth then
  begin
    Grid := GetGrid;
    if Assigned(Grid) then
    begin
      if Grid.HandleAllocated and Assigned(Field) and Grid.FUpdateFields then
      with Grid do
      begin
        Canvas.Font := Self.Font;
        GetTextMetrics(Canvas.Handle, TM);
        Field.DisplayWidth := (Value + (TM.tmAveCharWidth div 2) - TM.tmOverhang - 3)
          div TM.tmAveCharWidth;
      end;
      if (not Grid.FLayoutFromDataset) or (cvWidth in FAssignedValues) then
        DoSetWidth := True;
    end
    else
      DoSetWidth := True;
  end;
  if DoSetWidth then
  begin
    if ((cvWidth in FAssignedValues) or (Value <> DefaultWidth))
      and (Value <> -1) then
    begin
      FWidth := Value;
      Include(FAssignedValues, cvWidth);
    end;
    Changed(False);
  end;
end;

procedure TColumn.SetVisible(Value: Boolean);
begin
  if Value <> FVisible then
  begin
    FVisible := Value;
    Changed(True);
  end;
end;

procedure TColumn.SetExpanded(Value: Boolean);
const
  Direction: array [Boolean] of ShortInt = (-1,1);
var
  Grid: TBrCustomDBGrid;
  WasShowing: Boolean;
begin
  if Value <> FExpanded then
  begin
    Grid := GetGrid;
    WasShowing := (Grid <> nil) and Grid.Columns[Grid.SelectedIndex].Showing;
    FExpanded := Value;
    Changed(True);
    if (Grid <> nil) and WasShowing then
    begin
      if not Grid.Columns[Grid.SelectedIndex].Showing then
        // The selected cell was hidden by this expand operation
        // Select 1st child (next col = 1) when parent is expanded
        // Select child's parent (prev col = -1) when parent is collapsed
        Grid.MoveCol(Grid.Col, Direction[FExpanded]);
    end;
  end;
end;

function TColumn.Depth: Integer;
var
  Col: TColumn;
begin
  Result := 0;
  Col := ParentColumn;
  if Col <> nil then Result := Col.Depth + 1;
end;

function TColumn.GetExpandable: Boolean;
var
  Fld: TField;
begin
  Fld := Field;
  Result := (Fld <> nil) and (Fld.DataType in [ftADT, ftArray]);
end;

// Bravo inicio tcolumn
procedure TColumn.SetParametros(Value: TStrings);
begin
      gDsParams.Assign(Value);
end;

procedure TColumn.SetFields(Value: TStrings);
begin
      gDsConfig.Assign(Value);
end;

procedure TColumn.SetConsulta(Value : Integer);
begin
      gNrQueCon := Value;

      if gNrQueCon > 0 then
      begin
            ButtonStyle := cbsConsulta;
      end;
end;

function  TColumn.GetPickVAlue : TStrings;
begin
      if gDsPickVa = nil then
      begin
            gDsPickVa := TStringList.Create;
      end;

      Result := gDsPickVa;
end;

procedure TColumn.SetPickValue(Value : TStrings);
begin
      if Value = nil then
      begin
            gDsPickVa.Free;
            gDsPickVa := nil;
            Exit;
      end;

      gDsPickVa.Assign(Value);
end;

procedure TColumn.SetValueChecked(Value : String);
begin
      gVlChecke := Value;
      SetButtonStyle(cbsNone);
end;

procedure TColumn.SetValueUnchecked(Value : String);
begin
      gVlUnChec := Value;
      SetButtonStyle(cbsNone);
end;

// =-=-=-=-=-=-=-=-=-=-=-=-=

{ TDBGridColumns }

constructor TDBGridColumns.Create(Grid: TBrCustomDBGrid; ColumnClass: TColumnClass);
begin
  inherited Create(ColumnClass);
  FGrid := Grid;
end;

function TDBGridColumns.Add: TColumn;
begin
  Result := TColumn(inherited Add);
end;

function TDBGridColumns.GetColumn(Index: Integer): TColumn;
begin
  Result := TColumn(inherited Items[Index]);
end;

function TDBGridColumns.GetOwner: TPersistent;
begin
  Result := FGrid;
end;

procedure TDBGridColumns.LoadFromFile(const Filename: string);
var
  S: TFileStream;
begin
  S := TFileStream.Create(Filename, fmOpenRead);
  try
    LoadFromStream(S);
  finally
    S.Free;
  end;
end;

type
  TColumnsWrapper = class(TComponent)
  private
    FColumns: TDBGridColumns;
  published
    property Columns: TDBGridColumns read FColumns write FColumns;
  end;

procedure TDBGridColumns.LoadFromStream(S: TStream);
var
  Wrapper: TColumnsWrapper;
begin
  Wrapper := TColumnsWrapper.Create(nil);
  try
    Wrapper.Columns := FGrid.CreateColumns;
    S.ReadComponent(Wrapper);
    Assign(Wrapper.Columns);
  finally
    Wrapper.Columns.Free;
    Wrapper.Free;
  end;
end;

procedure TDBGridColumns.RestoreDefaults;
var
  I: Integer;
begin
  BeginUpdate;
  try
    for I := 0 to Count-1 do
      Items[I].RestoreDefaults;
  finally
    EndUpdate;
  end;
end;

procedure TDBGridColumns.RebuildColumns;

  procedure AddFields(Fields: TFields; Depth: Integer);
  var
    I: Integer;
  begin
    Inc(Depth);
    for I := 0 to Fields.Count-1 do
    begin
      Add.FieldName := Fields[I].FullName;
      if Fields[I].DataType in [ftADT, ftArray] then
        AddFields((Fields[I] as TObjectField).Fields, Depth);
    end;
  end;

begin
  if Assigned(FGrid) and Assigned(FGrid.DataSource) and
    Assigned(FGrid.Datasource.Dataset) then
  begin
    FGrid.BeginLayout;
    try
      Clear;
      AddFields(FGrid.Datasource.Dataset.Fields, 0);
    finally
      FGrid.EndLayout;
    end
  end
  else
    Clear;
end;

procedure TDBGridColumns.SaveToFile(const Filename: string);
var
  S: TStream;
begin
  S := TFileStream.Create(Filename, fmCreate);
  try
    SaveToStream(S);
  finally
    S.Free;
  end;
end;

procedure TDBGridColumns.SaveToStream(S: TStream);
var
  Wrapper: TColumnsWrapper;
begin
  Wrapper := TColumnsWrapper.Create(nil);
  try
    Wrapper.Columns := Self;
    S.WriteComponent(Wrapper);
  finally
    Wrapper.Free;
  end;
end;

procedure TDBGridColumns.SetColumn(Index: Integer; Value: TColumn);
begin
  Items[Index].Assign(Value);
end;

procedure TDBGridColumns.SetState(NewState: TDBGridColumnsState);
begin
  if NewState = State then Exit;
  if NewState = csDefault then
    Clear
  else
    RebuildColumns;
end;

procedure TDBGridColumns.Update(Item: TCollectionItem);
var
  Raw: Integer;
begin
  if (FGrid = nil) or (csLoading in FGrid.ComponentState) then Exit;
  if Item = nil then
  begin
    FGrid.LayoutChanged;
  end
  else
  begin
    Raw := FGrid.DataToRawColumn(Item.Index);
    FGrid.InvalidateCol(Raw);
    FGrid.ColWidths[Raw] := TColumn(Item).Width;
  end;
end;

function TDBGridColumns.InternalAdd: TColumn;
begin
  Result := Add;
  Result.IsStored := False;
end;

function TDBGridColumns.GetState: TDBGridColumnsState;
begin
  Result := TDBGridColumnsState((Count > 0) and Items[0].IsStored);
end;

{ TBookmarkList }

constructor TBookmarkList.Create(AGrid: TBrCustomDBGrid);
begin
  inherited Create;
  SetLength(FList, 0);
  FGrid := AGrid;
end;

destructor TBookmarkList.Destroy;
begin
  Clear;
  inherited Destroy;
end;

procedure TBookmarkList.Clear;
begin
  if Length(FList) = 0 then Exit;
  SetLength(FList, 0);
  FGrid.Invalidate;
end;

function TBookmarkList.Compare(const Item1, Item2: TBookmark): Integer;
begin
  with FGrid.Datalink.Datasource.Dataset do
    Result := CompareBookmarks(TBookmark(Item1), TBookmark(Item2));
end;

function TBookmarkList.CurrentRow: TBookmark;
begin
  if not FLinkActive then RaiseGridError(sDataSetClosed);
  Result := FGrid.Datalink.Datasource.Dataset.Bookmark;
end;

function TBookmarkList.GetCurrentRowSelected: Boolean;
var
  Index: Integer;
begin
  Result := Find(CurrentRow, Index);
end;

function TBookmarkList.Find(const Item: TBookmark; var Index: Integer): Boolean;
var
  L, H, I, C: Integer;
begin
  if (Item = FCache) and (FCacheIndex >= 0) then
  begin
    Index := FCacheIndex;
    Result := FCacheFind;
    Exit;
  end;
  Result := False;
  L := 0;
  H := Length(FList) - 1;
  while L <= H do
  begin
    I := (L + H) shr 1;
    C := Compare(FList[I], Item);
    if C < 0 then L := I + 1 else
    begin
      H := I - 1;
      if C = 0 then
      begin
        Result := True;
        L := I;
      end;
    end;
  end;
  Index := L;
  FCache := Item;
  FCacheIndex := Index;
  FCacheFind := Result;
end;

function TBookmarkList.GetCount: Integer;
begin
  Result := Length(FList);
end;

function TBookmarkList.GetItem(Index: Integer): TBookmark;
begin
  Result := FList[Index];
end;

function TBookmarkList.IndexOf(const Item: TBookmark): Integer;
begin
  if not Find(Item, Result) then
    Result := -1;
end;

procedure TBookmarkList.InsertItem(Index: Integer; Item: TBookmark);
begin
  if (Index < 0) or (Index > Count) then
    raise EListError.Create(SListIndexError);
  SetLength(FList, Count + 1);
  if Index < Count - 1 then
  begin
    Move(FList[Index], FList[Index + 1],
      (Count - Index - 1) * SizeOf(Pointer));
    // The slot we opened up with the Move above has a dangling pointer we don't want finalized
    PPointer(@FList[Index])^ := nil;
  end;
  FList[Index] := Item;
  DataChanged(TObject(Item));
end;

procedure TBookmarkList.LinkActive(Value: Boolean);
begin
  Clear;
  FLinkActive := Value;
end;

procedure TBookmarkList.DataChanged(Sender: TObject);
begin
  FCache := nil;
  FCacheIndex := -1;
end;

procedure TBookmarkList.Delete;
var
  I: Integer;
begin
  with FGrid.Datalink.Datasource.Dataset do
  begin
    DisableControls;
    try
      for I := Length(FList)-1 downto 0 do
      begin
        Bookmark := FList[I];
        Delete;
        DeleteItem(I);
      end;
    finally
      EnableControls;
    end;
  end;
end;

procedure TBookmarkList.DeleteItem(Index: Integer);
var
  Temp: Pointer;
begin
  if (Index < 0) or (Index >= Count) then
    raise EListError.Create(SListIndexError);
  Temp := FList[Index];
  // The Move below will overwrite this slot, so we need to finalize it first
  FList[Index] := nil;
  if Index < Count-1 then
  begin
    System.Move(FList[Index + 1], FList[Index],
      (Count - Index - 1) * SizeOf(Pointer));
    // Make sure we don't finalize the item that was in the last position.
    PPointer(@FList[Count-1])^ := nil;
  end;
  SetLength(FList, Count-1);
  DataChanged(Temp);
end;

function TBookmarkList.Refresh: Boolean;
var
  I: Integer;
begin
  Result := False;
  with FGrid.DataLink.Datasource.Dataset do
  try
    CheckBrowseMode;
    for I := Length(FList) - 1 downto 0 do
      if not BookmarkValid(TBookmark(FList[I])) then
      begin
        Result := True;
        DeleteItem(I);
      end;
  finally
    UpdateCursorPos;
    if Result then FGrid.Invalidate;
  end;
end;

procedure TBookmarkList.SetCurrentRowSelected(Value: Boolean);
var
  Index: Integer;
  Current: TBookmark;
begin
  Current := CurrentRow;
  if (Length(Current) = 0) or (Find(Current, Index) = Value) then Exit;
  if Value then
    InsertItem(Index, Current)
  else
    DeleteItem(Index);
  FGrid.InvalidateRow(FGrid.Row);
end;

{ TBrCustomDBGrid }

var
  DrawBitmap: TBitmap;
  UserCount: Integer;

procedure UsesBitmap;
begin
  if UserCount = 0 then
    DrawBitmap := TBitmap.Create;
  Inc(UserCount);
end;

procedure ReleaseBitmap;
begin
  Dec(UserCount);
  if UserCount = 0 then DrawBitmap.Free;
end;

procedure WriteText(ACanvas: TCanvas; ARect: TRect; DX, DY: Integer;
  const Text: string; Alignment: TAlignment; ARightToLeft: Boolean);
const
  AlignFlags : array [TAlignment] of Integer =
    ( DT_LEFT or DT_WORDBREAK or DT_EXPANDTABS or DT_NOPREFIX,
      DT_RIGHT or DT_WORDBREAK or DT_EXPANDTABS or DT_NOPREFIX,
      DT_CENTER or DT_WORDBREAK or DT_EXPANDTABS or DT_NOPREFIX );
  RTL: array [Boolean] of Integer = (0, DT_RTLREADING);
var
  B, R: TRect;
  Hold, Left: Integer;
  I: TColorRef;
begin
  I := ColorToRGB(ACanvas.Brush.Color);
  if GetNearestColor(ACanvas.Handle, I) = I then
  begin                       { Use ExtTextOut for solid colors }
    { In BiDi, because we changed the window origin, the text that does not
      change alignment, actually gets its alignment changed. }
    if (ACanvas.CanvasOrientation = coRightToLeft) and (not ARightToLeft) then
      ChangeBiDiModeAlignment(Alignment);
    case Alignment of
      taLeftJustify:
        Left := ARect.Left + DX;
      taRightJustify:
        Left := ARect.Right - ACanvas.TextWidth(Text) - 3;
    else { taCenter }
      Left := ARect.Left + (ARect.Right - ARect.Left) shr 1
        - (ACanvas.TextWidth(Text) shr 1);
    end;
    ACanvas.TextRect(ARect, Left, ARect.Top + DY, Text);
  end
  else begin                  { Use FillRect and Drawtext for dithered colors }
    DrawBitmap.Canvas.Lock;
    try
      with DrawBitmap, ARect do { Use offscreen bitmap to eliminate flicker and }
      begin                     { brush origin tics in painting / scrolling.    }
        Width := Max(Width, Right - Left);
        Height := Max(Height, Bottom - Top);
        R := Rect(DX, DY, Right - Left - 1, Bottom - Top - 1);
        B := Rect(0, 0, Right - Left, Bottom - Top);
      end;
      with DrawBitmap.Canvas do
      begin
        Font := ACanvas.Font;
        Font.Color := ACanvas.Font.Color;
        Brush := ACanvas.Brush;
        Brush.Style := bsSolid;
        FillRect(B);
        SetBkMode(Handle, TRANSPARENT);
        if (ACanvas.CanvasOrientation = coRightToLeft) then
          ChangeBiDiModeAlignment(Alignment);
        DrawText(Handle, PChar(Text), Length(Text), R,
          AlignFlags[Alignment] or RTL[ARightToLeft]);
      end;
      if (ACanvas.CanvasOrientation = coRightToLeft) then
      begin
        Hold := ARect.Left;
        ARect.Left := ARect.Right;
        ARect.Right := Hold;
      end;
      ACanvas.CopyRect(ARect, DrawBitmap.Canvas, B);
    finally
      DrawBitmap.Canvas.Unlock;
    end;
  end;
end;

constructor TBrCustomDBGrid.Create(AOwner: TComponent);
var
  Bmp: TBitmap;
begin
  inherited Create(AOwner);
  inherited DefaultDrawing := False;
  FAcquireFocus := True;
  Bmp := TBitmap.Create;
  try
    Bmp.LoadFromResourceName(HInstance, bmArrow);
    FIndicators := TImageList.CreateSize(Bmp.Width, Bmp.Height);
    FIndicators.AddMasked(Bmp, clWhite);
    Bmp.LoadFromResourceName(HInstance, bmEdit);
    FIndicators.AddMasked(Bmp, clWhite);
    Bmp.LoadFromResourceName(HInstance, bmInsert);
    FIndicators.AddMasked(Bmp, clWhite);
    Bmp.LoadFromResourceName(HInstance, bmMultiDot);
    FIndicators.AddMasked(Bmp, clWhite);
    Bmp.LoadFromResourceName(HInstance, bmMultiArrow);
    FIndicators.AddMasked(Bmp, clWhite);
  finally
    Bmp.Free;
  end;
  FTitleOffset := 1;
  FIndicatorOffset := 1;
  FUpdateFields := True;
  FOptions := [dgEditing, dgTitles, dgIndicator, dgColumnResize,
    dgColLines, dgRowLines, dgTabs, dgConfirmDelete, dgCancelOnExit];
  if SysLocale.PriLangID = LANG_KOREAN then
    Include(FOptions, dgAlwaysShowEditor);
  DesignOptionsBoost := [goColSizing];
  VirtualView := True;
  UsesBitmap;
  ScrollBars := ssHorizontal;
  inherited Options := [goFixedHorzLine, goFixedVertLine, goHorzLine,
    goVertLine, goColSizing, goColMoving, goTabs, goEditing];
  FColumns := CreateColumns;
  FVisibleColumns := TList.Create;
  inherited RowCount := 2;
  inherited ColCount := 2;
  FDataLink := TGridDataLink.Create(Self);
  Color := clWindow;
  ParentColor := False;
  FTitleFont := TFont.Create;
  FTitleFont.OnChange := TitleFontChanged;
  FSaveCellExtents := False;
  FUserChange := True;
  FDefaultDrawing := True;
  FBookmarks := TBookmarkList.Create(Self);
  HideEditor;

// =-=-=-=-=-=-= Bravo
  FSnDisMem := True;
  FDsOption := [];
  FCorReaOnl := clMaroon;
  FStlReadOn := [fsItalic];
  // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
  // =-=-=-=-=-= Setando o tamanho do CheckBox =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
  // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
     with TBitmap.Create do
     begin
           try
               Handle := LoadBitmap(0, PChar(32759));
               FTmWidChe := Width div 4;
               FTmHeiChe := Height div 3;
           finally
               Free;
           end;
     end;

     gStlDraCol := TStringList.Create;
     gStlDraCol.Add('Não remova essas duas linhas de formatação:');
     gStlDraCol.Add('CampoTabela;Operador;ValorComparativo;Cor;');
// =-=-=-=-=-=-=-=-=-=-=-=-=
end;

destructor TBrCustomDBGrid.Destroy;
begin
  FColumns.Free;
  FColumns := nil;
  FVisibleColumns.Free;
  FVisibleColumns := nil;
  FDataLink.Free;
  FDataLink := nil;
  FIndicators.Free;
  FTitleFont.Free;
  FTitleFont := nil;
  FBookmarks.Free;
  FBookmarks := nil;
  inherited Destroy;
  ReleaseBitmap;

// =-=-=-=-=-=-= Bravo
  FreeAndNil(gStlDraCol);
end;

function TBrCustomDBGrid.AcquireFocus: Boolean;
begin
  Result := True;
  if FAcquireFocus and CanFocus and not (csDesigning in ComponentState) then
  begin
    SetFocus;
    Result := Focused or (InplaceEditor <> nil) and InplaceEditor.Focused;
  end;
end;

function TBrCustomDBGrid.RawToDataColumn(ACol: Integer): Integer;
begin
  Result := ACol - FIndicatorOffset;
end;

function TBrCustomDBGrid.DataToRawColumn(ACol: Integer): Integer;
begin
  Result := ACol + FIndicatorOffset;
end;

function TBrCustomDBGrid.AcquireLayoutLock: Boolean;
begin
  Result := (FUpdateLock = 0) and (FLayoutLock = 0);
  if Result then BeginLayout;
end;

procedure TBrCustomDBGrid.BeginLayout;
begin
  BeginUpdate;
  if FLayoutLock = 0 then Columns.BeginUpdate;
  Inc(FLayoutLock);
end;

procedure TBrCustomDBGrid.BeginUpdate;
begin
  Inc(FUpdateLock);
end;

procedure TBrCustomDBGrid.CancelLayout;
begin
  if FLayoutLock > 0 then
  begin
    if FLayoutLock = 1 then
      Columns.EndUpdate;
    Dec(FLayoutLock);
    EndUpdate;
  end;
end;

function TBrCustomDBGrid.CanEditAcceptKey(Key: Char): Boolean;
begin
  with Columns[SelectedIndex] do
    Result := FDatalink.Active and Assigned(Field) and Field.IsValidChar(Key);
end;

function TBrCustomDBGrid.CanEditModify: Boolean;
begin
  Result := False;
  if not ReadOnly and FDatalink.Active and not FDatalink.Readonly then
  with Columns[SelectedIndex] do
    if (not ReadOnly) and Assigned(Field) and Field.CanModify
      and (not (Field.DataType in ftNonTextTypes) or Assigned(Field.OnSetText)) then
    begin
      FDatalink.Edit;
      Result := FDatalink.Editing;
      if Result then FDatalink.Modified;
    end;
end;

function TBrCustomDBGrid.CanEditShow: Boolean;
begin
  if (Columns[SelectedIndex].gVlChecke <> '') and
     (Columns[SelectedIndex].gVlUnChec <> '') then
  begin
        Result := False;
  end else
  begin
        Result := (LayoutLock = 0) and inherited CanEditShow;
  end;
end;

procedure TBrCustomDBGrid.CellClick(Column: TColumn);
var lSnReaOnl : Boolean;
begin
      if Column.Field <> nil then
      begin
            if not (csDesigning in ComponentState) then
            begin
                  if (Column.Field.DataType in [ftMemo, ftOraClob]) or
                     ((Column.Field.DataType in [ftString, ftWideString]) and
                      (Column.Field.Size > 500)) then
                  begin
                        BeginUpdate;
                        MostrarMemo(Column);
                        EndUpdate;
                  end else
                  begin
                        if (Datalink.Active) and (not Datalink.Readonly) and
                           (not Column.ReadOnly) then
                        begin
                              BeginUpdate;

                              if (Datalink <> nil) and (Datalink.Active) and
                                 (Assigned(Column.Field))                then
                              begin
                                    case Column.Field.DataType of
                                         FtString,
                                         ftWideString  : AlterarString(Column, True);
                                    end;
                              end;

                              if (Column.gVlChecke <> '') and
                                 (Column.gVlUnChec <> '') then
                              begin
                                    RecreateWnd;

                                    if (Column.gSnSavCli) and
                                       (DataSource.DataSet.State in [dsInsert, dsEdit])
                                                                                      then
                                    begin
                                          DataSource.DataSet.Post;
                                    end;
                              end;

                              EndUpdate;
                        end;
                  end;
            end;

            if Assigned(FOnCellClick) then FOnCellClick(Column);
      end;
end;

procedure TBrCustomDBGrid.ColEnter;
begin
// Bravo =-=-=-=-= SetValueUnchecked
      if not (csDesigning in ComponentState) then
      begin
            if not ReadOnly and Datalink.Active and not Datalink.Readonly and
               (Columns[SelectedIndex].Field <> nil) and
               (Columns[SelectedIndex].FieldName <> '')  and
               (FDsOption = []) then
            begin
                  BeginUpdate;

                  if  (Columns[SelectedIndex].Field.DataType in [FtMemo, ftOraClob]) then
                  begin
                        FDsOption    := Self.Options;
                        Self.Options := Self.Options - [dgEditing];
                  end else
                  begin
                        if Columns[SelectedIndex].Field.DataType in
                                                             [FtString, ftWideString] then
                        begin
                              if (Columns[SelectedIndex].gVlChecke <> '') or
                                 (Columns[SelectedIndex].gVlUnChec <> '') then
                              begin
                                    FDsOption    := Self.Options;
                                    Self.Options := Self.Options - [dgEditing];
                              end;
                        end;
                  end;

                  EndUpdate;
            end;
      end;
// =-=-=-=-=-=-=-=-=

     UpdateIme;
     if Assigned(FOnColEnter) then FOnColEnter(Self);
end;

procedure TBrCustomDBGrid.ColExit;
begin
// Bravo =-=-=-=-= SetValueUnchecked
      if not (csDesigning in ComponentState) then
      begin
            if not ReadOnly and Datalink.Active and not Datalink.Readonly and
               (Columns[SelectedIndex].FieldName <> '')  and
               (FDsOption <> []) then
            begin
                  BeginUpdate;
                  if (Columns[SelectedIndex].Field.DataType in [ftMemo, ftOraClob]) then
                  begin
                        Self.Options := FDsOption;
                        FDsOption    := [];
                  end else
                  begin
                        if Columns[SelectedIndex].Field.DataType in
                                                             [FtString, ftWideString] then
                        begin
                              if (Columns[SelectedIndex].gVlChecke <> '') or
                                 (Columns[SelectedIndex].gVlUnChec <> '') then
                              begin
                                    Self.Options := FDsOption;
                                    FDsOption    := [];
                              end else
                              begin
                                    VerificarValorPickList(Columns[SelectedIndex]);
                              end;
                        end;
                  end;
                  EndUpdate;
            end else
            begin
                 if not ReadOnly and Datalink.Active and
                    not Datalink.Readonly and
                    (DataSource.DataSet.State in [dsInsert, dsEdit]) and
                   (Columns[SelectedIndex].FieldName <> '') and
                   (Columns[SelectedIndex].Field.DataType in [FtString, FtWideString]) then
                 begin
                       BeginUpdate;
                       VerificarValorPickList(Columns[SelectedIndex]);
                       EndUpdate;
                 end;
            end;
      end;
// =-=-=-=-=-=-=
  if Assigned(FOnColExit) then FOnColExit(Self);
end;

procedure TBrCustomDBGrid.VerificarValorPickList(Column : TColumn);
var lVlColuna : String;
    lNmColuna : String;
    lNoOpcao  : Integer;
    lBrString : TBrvString;
    lSnAchou  : Boolean;
begin
      if Column.BrPickValue.Text <> '' then
      begin
            lBrString := TBrvString.Create(Owner);

            try
                lBrString.Split(Column.BrPickValue.CommaText, ',');
                lNmColuna := Column.FieldName;
                lVlColuna := DataSource.DataSet.FieldByName(lNmColuna).AsString;

                lNoOpcao := 0;
                lSnAchou := False;
                while (lNoOpcao < lBrString.BrSplitLista.Count) and (not lSnAchou) do
                begin
                      if lBrString.BrSplitLista.Strings[lNoOpcao] = lVlColuna then
                      begin
                            lSnAchou := True;
                      end;
                      inc(lNoOpcao);
                end;

                if not lSnAchou then
                begin
                      MessageDlg('Valor inválido para este campo', mtError, [mbOk], 0);
                      EndUpdate;
                      abort;
                end;
            finally
                FreeAndNil(lBrString);
            end;
      end;
end;

procedure TBrCustomDBGrid.ColumnMoved(FromIndex, ToIndex: Longint);
begin
  FromIndex := RawToDataColumn(FromIndex);
  ToIndex := RawToDataColumn(ToIndex);
  Columns[FromIndex].Index := ToIndex;
  if Assigned(FOnColumnMoved) then FOnColumnMoved(Self, FromIndex, ToIndex);
end;

procedure TBrCustomDBGrid.ColWidthsChanged;
var
  I: Integer;
begin
  inherited ColWidthsChanged;
  if (FDatalink.Active or (FColumns.State = csCustomized)) and
    AcquireLayoutLock then
  try
    for I := FIndicatorOffset to ColCount - 1 do
      FColumns[I - FIndicatorOffset].Width := ColWidths[I];
  finally
    EndLayout;
  end;
end;

function TBrCustomDBGrid.CreateColumns: TDBGridColumns;
begin
  Result := TDBGridColumns.Create(Self, TColumn);
end;

function TBrCustomDBGrid.CreateEditor: TInplaceEdit;
begin
  Result := TDBGridInplaceEdit.Create(Self);
end;

procedure TBrCustomDBGrid.CreateWnd;
begin
  BeginUpdate;   { prevent updates in WMSize message that follows WMCreate }
  try
    inherited CreateWnd;
  finally
    EndUpdate;
  end;
  UpdateRowCount;
  UpdateActive;
  UpdateScrollBar;
  FOriginalImeName := ImeName;
  FOriginalImeMode := ImeMode;
end;

procedure TBrCustomDBGrid.DataChanged;
begin
  if not HandleAllocated then Exit;
  UpdateRowCount;
  UpdateScrollBar;
  UpdateActive;
  InvalidateEditor;
  ValidateRect(Handle, nil);
  Invalidate;
end;

procedure TBrCustomDBGrid.DefaultHandler(var Msg);
var
  P: TPopupMenu;
  Cell: TGridCoord;
begin
  inherited DefaultHandler(Msg);
  if TMessage(Msg).Msg = wm_RButtonUp then
    with TWMRButtonUp(Msg) do
    begin
      Cell := MouseCoord(XPos, YPos);
      if (Cell.X < FIndicatorOffset) or (Cell.Y < 0) then Exit;
      P := Columns[RawToDataColumn(Cell.X)].PopupMenu;
      if (P <> nil) and P.AutoPopup then
      begin
        SendCancelMode(nil);
        P.PopupComponent := Self;
        with ClientToScreen(SmallPointToPoint(Pos)) do
          P.Popup(X, Y);
        Result := 1;
      end;
    end;
end;

procedure TBrCustomDBGrid.DeferLayout;
var
  M: TMsg;
begin
  if HandleAllocated and
    not PeekMessage(M, Handle, cm_DeferLayout, cm_DeferLayout, pm_NoRemove) then
    PostMessage(Handle, cm_DeferLayout, 0, 0);
  CancelLayout;
end;

procedure TBrCustomDBGrid.DefineFieldMap;
var
  I: Integer;
begin
  if FColumns.State = csCustomized then
  begin   { Build the column/field map from the column attributes }
    DataLink.SparseMap := True;
    for I := 0 to FColumns.Count-1 do
      FDataLink.AddMapping(FColumns[I].FieldName);
  end
  else   { Build the column/field map from the field list order }
  begin
    FDataLink.SparseMap := False;
    with Datalink.Dataset do
      for I := 0 to FieldList.Count - 1 do
        with FieldList[I] do if Visible then Datalink.AddMapping(FullName);
  end;
end;

function TBrCustomDBGrid.UseRightToLeftAlignmentForField(const AField: TField;
  Alignment: TAlignment): Boolean;
begin
  Result := False;
  if IsRightToLeft then
    Result := OkToChangeFieldAlignment(AField, Alignment);
end;

procedure TBrCustomDBGrid.DefaultDrawDataCell(const Rect: TRect; Field: TField;
  State: TGridDrawState);
var
  Alignment: TAlignment;
  Value: string;
begin
  Alignment := taLeftJustify;
  Value := '';
  if Assigned(Field) then
  begin
    Alignment := Field.Alignment;
    Value := Field.DisplayText;
  end;
  WriteText(Canvas, Rect, 2, 2, Value, Alignment,
    UseRightToLeftAlignmentForField(Field, Alignment));
end;

procedure TBrCustomDBGrid.DefaultDrawColumnCell(const Rect: TRect;
  DataCol: Integer; Column: TColumn; State: TGridDrawState);
var
  Value: string;
begin
  Value := '';
  if Assigned(Column.Field) then
    Value := Column.Field.DisplayText;
  WriteText(Canvas, Rect, 2, 2, Value, Column.Alignment,
    UseRightToLeftAlignmentForField(Column.Field, Column.Alignment));
end;

procedure TBrCustomDBGrid.ReadColumns(Reader: TReader);
begin
  Columns.Clear;
  Reader.ReadValue;
  Reader.ReadCollection(Columns);
end;

procedure TBrCustomDBGrid.WriteColumns(Writer: TWriter);
begin
  if Columns.State = csCustomized then
    Writer.WriteCollection(Columns)
  else  // ancestor state is customized, ours is not
    Writer.WriteCollection(nil);
end;

procedure TBrCustomDBGrid.DefineProperties(Filer: TFiler);
var
  StoreIt: Boolean;
  vState: TDBGridColumnsState;
begin
  vState := Columns.State;
  if Filer.Ancestor = nil then
    StoreIt := vState = csCustomized
  else
    if vState <> TBrCustomDBGrid(Filer.Ancestor).Columns.State then
      StoreIt := True
    else
      StoreIt := (vState = csCustomized) and
        (not CollectionsEqual(Columns, TBrCustomDBGrid(Filer.Ancestor).Columns, Nil, Nil));

  Filer.DefineProperty('Columns', ReadColumns, WriteColumns, StoreIt);
end;

function TBrCustomDBGrid.ColumnAtDepth(Col: TColumn; ADepth: Integer): TColumn;
begin
  Result := Col;
  while (Result <> nil) and (Result.Depth > ADepth) do
    Result := Result.ParentColumn;
end;

function TBrCustomDBGrid.CalcTitleRect(Col: TColumn; ARow: Integer;
  var MasterCol: TColumn): TRect;
var
  I,J: Integer;
  InBiDiMode: Boolean;
  DrawInfo: TGridDrawInfo;
begin
  MasterCol := ColumnAtDepth(Col, ARow);
  if MasterCol = nil then Exit;

  I := DataToRawColumn(MasterCol.Index);
  if I >= LeftCol then
    J := MasterCol.Depth
  else
  begin
    I := LeftCol;
    if Col.Depth > ARow then
      J := ARow
    else
      J := Col.Depth;
  end;

  Result := CellRect(I, J);

  InBiDiMode := UseRightToLeftAlignment and
                (Canvas.CanvasOrientation = coLeftToRight);

  for I := Col.Index to Columns.Count-1 do
  begin
    if ColumnAtDepth(Columns[I], ARow) <> MasterCol then Break;
    if not InBiDiMode then
    begin
      J := CellRect(DataToRawColumn(I), ARow).Right;
      if J = 0 then Break;
      Result.Right := Max(Result.Right, J);
    end
    else
    begin
      J := CellRect(DataToRawColumn(I), ARow).Left;
      if J >= ClientWidth then Break;
      Result.Left := J;
    end;
  end;
  J := Col.Depth;
  if (J <= ARow) and (J < FixedRows-1) then
  begin
    CalcFixedInfo(DrawInfo);
    Result.Bottom := DrawInfo.Vert.FixedBoundary - DrawInfo.Vert.EffectiveLineWidth;
  end;
end;

procedure TBrCustomDBGrid.DrawCell(ACol, ARow: Longint; ARect: TRect; AState: TGridDrawState);
var
  FrameOffs: Byte;

  function RowIsMultiSelected: Boolean;
  var
    Index: Integer;
  begin
    Result := (dgMultiSelect in Options) and Datalink.Active and
      FBookmarks.Find(Datalink.Datasource.Dataset.Bookmark, Index);
  end;

  procedure DrawTitleCell(ACol, ARow: Integer; Column: TColumn; var AState: TGridDrawState);
  const
    ScrollArrows: array [Boolean, Boolean] of Integer =
      ((DFCS_SCROLLRIGHT, DFCS_SCROLLLEFT), (DFCS_SCROLLLEFT, DFCS_SCROLLRIGHT));
  var
    MasterCol: TColumn;
    TitleRect, TextRect, ButtonRect: TRect;
    I: Integer;
    InBiDiMode: Boolean;
  begin
    TitleRect := CalcTitleRect(Column, ARow, MasterCol);

    if MasterCol = nil then
    begin
      Canvas.FillRect(ARect);
      Exit;
    end;

    Canvas.Font := MasterCol.Title.Font;
    Canvas.Brush.Color := MasterCol.Title.Color;
    if [dgRowLines, dgColLines] * Options = [dgRowLines, dgColLines] then
      InflateRect(TitleRect, -1, -1);
    TextRect := TitleRect;
    I := GetSystemMetrics(SM_CXHSCROLL);
    if ((TextRect.Right - TextRect.Left) > I) and MasterCol.Expandable then
    begin
      Dec(TextRect.Right, I);
      ButtonRect := TitleRect;
      ButtonRect.Left := TextRect.Right;
      I := SaveDC(Canvas.Handle);
      try
        Canvas.FillRect(ButtonRect);
        InflateRect(ButtonRect, -1, -1);
        IntersectClipRect(Canvas.Handle, ButtonRect.Left,
          ButtonRect.Top, ButtonRect.Right, ButtonRect.Bottom);
        InflateRect(ButtonRect, 1, 1);
        { DrawFrameControl doesn't draw properly when orienatation has changed.
          It draws as ExtTextOut does. }
        InBiDiMode := Canvas.CanvasOrientation = coRightToLeft;
        if InBiDiMode then { stretch the arrows box }
          Inc(ButtonRect.Right, GetSystemMetrics(SM_CXHSCROLL) + 4);
        DrawFrameControl(Canvas.Handle, ButtonRect, DFC_SCROLL,
          ScrollArrows[InBiDiMode, MasterCol.Expanded] or DFCS_FLAT);
      finally
        RestoreDC(Canvas.Handle, I);
      end;
    end;
    with MasterCol.Title do
      WriteText(Canvas, TextRect, FrameOffs, FrameOffs, Caption, Alignment,
        IsRightToLeft);
    if [dgRowLines, dgColLines] * Options = [dgRowLines, dgColLines] then
    begin
      InflateRect(TitleRect, 1, 1);
      DrawEdge(Canvas.Handle, TitleRect, BDR_RAISEDINNER, BF_BOTTOMRIGHT);
      DrawEdge(Canvas.Handle, TitleRect, BDR_RAISEDINNER, BF_TOPLEFT);
    end;
    AState := AState - [gdFixed];  // prevent box drawing later
  end;

var
  OldActive: Integer;
  Indicator: Integer;
  Highlight: Boolean;
  Value: string;
  DrawColumn: TColumn;
  MultiSelected: Boolean;
  ALeft: Integer;
begin
  if csLoading in ComponentState then
  begin
    Canvas.Brush.Color := Color;
    Canvas.FillRect(ARect);
    Exit;
  end;

  Dec(ARow, FTitleOffset);
  Dec(ACol, FIndicatorOffset);

  if (gdFixed in AState) and ([dgRowLines, dgColLines] * Options =
    [dgRowLines, dgColLines]) then
  begin
    InflateRect(ARect, -1, -1);
    FrameOffs := 1;
  end
  else
    FrameOffs := 2;

  if (gdFixed in AState) and (ACol < 0) then
  begin
    Canvas.Brush.Color := FixedColor;
    Canvas.FillRect(ARect);
    if Assigned(DataLink) and DataLink.Active  then
    begin
      MultiSelected := False;
      if ARow >= 0 then
      begin
        OldActive := FDataLink.ActiveRecord;
        try
          FDatalink.ActiveRecord := ARow;
          MultiSelected := RowIsMultiselected;
        finally
          FDatalink.ActiveRecord := OldActive;
        end;
      end;
      if (ARow = FDataLink.ActiveRecord) or MultiSelected then
      begin
        Indicator := 0;
        if FDataLink.DataSet <> nil then
          case FDataLink.DataSet.State of
            dsEdit: Indicator := 1;
            dsInsert: Indicator := 2;
            dsBrowse:
              if MultiSelected then
                if (ARow <> FDatalink.ActiveRecord) then
                  Indicator := 3
                else
                  Indicator := 4;  // multiselected and current row
          end;
        FIndicators.BkColor := FixedColor;
        ALeft := ARect.Right - FIndicators.Width - FrameOffs;
        if Canvas.CanvasOrientation = coRightToLeft then Inc(ALeft);
        FIndicators.Draw(Canvas, ALeft,
          (ARect.Top + ARect.Bottom - FIndicators.Height) shr 1, Indicator, True);
        if ARow = FDatalink.ActiveRecord then
          FSelRow := ARow + FTitleOffset;
      end;
    end;
  end
  else with Canvas do
  begin
    DrawColumn := Columns[ACol];
    if not DrawColumn.Showing then Exit;
    if not (gdFixed in AState) then
    begin
      Font := DrawColumn.Font;
      Brush.Color := DrawColumn.Color;
    end;
    if ARow < 0 then
      DrawTitleCell(ACol, ARow + FTitleOffset, DrawColumn, AState)
    else if (FDataLink = nil) or not FDataLink.Active then
      FillRect(ARect)
    else
    begin
      Value := '';
      OldActive := FDataLink.ActiveRecord;
      try
        FDataLink.ActiveRecord := ARow;
        if Assigned(DrawColumn.Field) then
          Value := DrawColumn.Field.DisplayText;
        Highlight := HighlightCell(ACol, ARow, Value, AState);
        if Highlight then
        begin
          Brush.Color := clHighlight;
          Font.Color := clHighlightText;
        end;
        if not Enabled then
          Font.Color := clGrayText;
        if FDefaultDrawing then
          WriteText(Canvas, ARect, 2, 2, Value, DrawColumn.Alignment,
            UseRightToLeftAlignmentForField(DrawColumn.Field, DrawColumn.Alignment));
        if Columns.State = csDefault then
          DrawDataCell(ARect, DrawColumn.Field, AState);
        DrawColumnCell(ARect, ACol, DrawColumn, AState);
      finally
        FDataLink.ActiveRecord := OldActive;
      end;
      if FDefaultDrawing and (gdSelected in AState)
        and ((dgAlwaysShowSelection in Options) or Focused)
        and not (csDesigning in ComponentState)
        and not (dgRowSelect in Options)
        and (UpdateLock = 0)
        and (ValidParentForm(Self).ActiveControl = Self) then
        Windows.DrawFocusRect(Handle, ARect);
    end;
  end;
  if (gdFixed in AState) and ([dgRowLines, dgColLines] * Options =
    [dgRowLines, dgColLines]) then
  begin
    InflateRect(ARect, 1, 1);
    DrawEdge(Canvas.Handle, ARect, BDR_RAISEDINNER, BF_BOTTOMRIGHT);
    DrawEdge(Canvas.Handle, ARect, BDR_RAISEDINNER, BF_TOPLEFT);
  end;
end;

procedure TBrCustomDBGrid.DrawDataCell(const Rect: TRect; Field: TField;
  State: TGridDrawState);
begin
  if Assigned(FOnDrawDataCell) then FOnDrawDataCell(Self, Rect, Field, State);
end;

procedure TBrCustomDBGrid.DrawColumnCell(const Rect: TRect; DataCol: Integer;
  Column: TColumn; State: TGridDrawState);
var Loc      : TRect;
    DsColuna : String;
begin
// =-=-=-=-= Bravo
    try
        if  (Column.FieldName <> '') and (Column.Field <> nil) then
        begin
              // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
              // =-=-=-=-=-=- Modificando a fonte do ReadOnly -=-=-=-=-=-=-=-=-=-=-=-=-=-=
              // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
              if (Column.ReadOnly) or (Column.Field.ReadOnly) then
              begin
                    Canvas.Font.Color   := FCorReaOnl;
                    Canvas.Font.Style   := FStlReadOn;
                    Canvas.Brush.Color  := clWindow;
              end;

              if gStlDraCol.Count > 2 then
              begin
                    Canvas.Brush.Color := ProcessarCorLinha(Canvas.Brush.Color);
              end else
              begin
                    if gSnZebrad then
                    begin
                          Canvas.Brush.Color := ProcessarCorGradeZebrada(State);
                    end;
              end;


              if (Column.BrPickValue <> nil) and (Column.BrPickValue.Count > 0) then
              begin
                    DsColuna := EncontrarTextoPickList(Column);
                    Loc      := Rect;
                    Inc(Loc.Top, 2);
                    Inc(Loc.Left, 2);

                    Canvas.TextRect(Rect, Loc.Left, Loc.Top, DsColuna);
              end else
              begin
                    if Column.Field <> nil then
                    begin
                          BeginUpdate;

                          Canvas.lock;
                          Canvas.FillRect(Rect);

                          case Column.Field.DataType of
                               FtMemo,
                               FtOraClob : ProcessarMemo(Rect, Column);
                               FtString,
                               ftWideString : ProcessarString(Rect, Column);
                               else        ProcessarOutrosCampos(Rect, Column);

                          end;
                          Canvas.unlock;

                          EndUpdate;
                    end;
              end;
        end;
    except
        raise Exception.Create('Coluna ' + Column.FieldName + ' não encontrada');
    end;

    if (gTpOrdem = 'B') or (gTpOrdem = 'C') then
    begin
          if not Assigned(OnDrawColumnCell) then
          begin
                MostrarSetaOrdenacao(Rect.Left, Column, gNrColCli);
          end;
    end;

//=-=-=-=-=-=-=-=-=-=
  if Assigned(OnDrawColumnCell) then
     OnDrawColumnCell(Self, Rect, DataCol, Column, State);
end;

function TBrCustomDBGrid.ProcessarCorLinha(pDsColor : TColor) : TColor;
var lNrLinha : Integer;
    lBrvString : TBrvString;

    lNmCampo  : String;
    lDsOperac : String;
    lDsValor  : String;
    lDsCor    : String;
begin
      Result   := pDsColor;
      lNrLinha := 2;

      lBrvString := TBrvString.Create(self);
      try
          while lNrLinha < gStlDraCol.Count do
          begin
                if Trim(gStlDraCol.Strings[lNrLinha]) <> '' then
                begin
                      lBrvString.Split(gStlDraCol.Strings[lNrLinha], ';');
                      lNmCampo  := lBrvString.BrSplitLista.Strings[0];
                      lDsOperac := lBrvString.BrSplitLista.Strings[1];
                      lDsValor  := lBrvString.BrSplitLista.Strings[2];
                      lDsCor    := lBrvString.BrSplitLista.Strings[3];

                      if lDsOperac = '<' then
                      begin
                            if DataSource.DataSet.FieldByName(lNmCampo).AsString <
                                                                             lDsValor then
                            begin
                                  Result   := StrToColor(lDsCor);
                                  lNrLinha := gStlDraCol.Count;
                            end;
                      end
                      else if lDsOperac = '<=' then
                           begin
                                 if DataSource.DataSet.FieldByName(lNmCampo).AsString <=
                                                                             lDsValor then
                                 begin
                                       Result   := StrToColor(lDsCor);
                                       lNrLinha := gStlDraCol.Count;
                                 end;
                           end
                      else if lDsOperac = '=' then
                           begin
                                 if DataSource.DataSet.FieldByName(lNmCampo).AsString =
                                                                             lDsValor then
                                 begin
                                       Result   := StrToColor(lDsCor);
                                       lNrLinha := gStlDraCol.Count;
                                 end;
                           end
                      else if lDsOperac = '>=' then
                           begin
                                 if DataSource.DataSet.FieldByName(lNmCampo).AsString >=
                                                                             lDsValor then
                                 begin
                                       Result   := StrToColor(lDsCor);
                                       lNrLinha := gStlDraCol.Count;
                                 end;
                           end
                      else if lDsOperac = '>' then
                           begin
                                 if DataSource.DataSet.FieldByName(lNmCampo).AsString >
                                                                             lDsValor then
                                 begin
                                       Result   := StrToColor(lDsCor);
                                       lNrLinha := gStlDraCol.Count;
                                 end;
                           end
                      ;
                end;

                inc(lNrLinha);
          end;
      finally
          FreeAndNil(lBrvString);
      end;
end;

function TBrCustomDBGrid.ProcessarCorGradeZebrada(State: TGridDrawState) : TColor;
begin
      if DataSource.DataSet is TClientDataSet then
      begin
            if Odd(TClientDataSet(DataSource.DataSet).RecNo) then
            begin
                  Result := clSkyBlue;
            end else
            begin
                  Result := clWhite;
            end;

            if (gdFocused in State) then
            begin
                  Result := clWindowFrame;
            end;
      end;
end;

function TBrCustomDBGrid.StrToColor(pDsCor : String) : TColor;
begin
      Result := clPurple;
      pDsCor := LowerCase(pDsCor);


      if pDsCor = 'clblack' then
      begin
            Result := clBlack;
      end
      else if pDsCor = 'clwhite' then
           begin
                 Result := clwhite;
           end
      else if pDsCor = 'clblue' then
           begin
                 Result := clblue;
           end
      else if pDsCor = 'clred' then
           begin
                 Result := clred;
           end
      else if pDsCor = 'clyellow' then
           begin
                 Result := clyellow;
           end
      else if pDsCor = 'clgreen' then
           begin
                 Result := clgreen;
           end
      else if pDsCor = 'clgray' then
           begin
                 Result := clGray;
           end
      else if pDsCor = 'clbtnface' then
           begin
                 Result := clBtnFace;
           end
      else if pDsCor = 'clnavy' then
           begin
                 Result := clnavy;
           end
      else if pDsCor = 'clsilver' then
           begin
                 Result := clsilver;
           end
      else if pDsCor = 'cllime' then
           begin
                 Result := cllime;
           end
      else if pDsCor = 'clmaroon' then
           begin
                 Result := clMaroon;
           end
      else if pDsCor = 'clolive' then
           begin
                 Result := clolive;
           end
      else if pDsCor = 'clfuchsia' then
           begin
                 Result := clFuchsia;
           end
      else if pDsCor = 'clteal' then
           begin
                 Result := clteal;
           end
      else if pDsCor = 'clpurple' then
           begin
                 Result := clpurple;
           end
      else if pDsCor = 'clmoneygreen' then
           begin
                 Result := clMoneyGreen;
           end
      else if pDsCor = 'clskyblue' then
           begin
                 Result := clSkyBlue;
           end
      ;
end;

// =-=-=-=-=-=-= Bravo
procedure TBrCustomDBGrid.MostrarSetaOrdenacao(pNrPosLef : Integer;  pColumn: TColumn;
                                               pNrColCli : Integer);
var lImgBmp   : TBitMap;
    lNoPosLef : Integer;
    lNoPosTop : Integer;
begin
      Canvas.Brush.Style := bsClear;

      if pColumn.Index = pNrColCli then
      begin
            lImgBmp := TBitMap.Create;

            try
                if gTpOrdem = 'B' then
                begin
                      lImgBmp.LoadFromResourceName(HInstance, bmSetaAbaixo);
                end else
                begin
                      lImgBmp.LoadFromResourceName(HInstance, bmSetaAcima);
                end;

                lImgBmp.TransparentColor := clWhite;
                lImgBmp.Transparent := True;
                lImgBmp.TransparentMode := tmAuto;

                lNoPosLef   := pNrPosLef + 2;
                lNoPosTop   := 8;

                if pColumn.Width <
                            (Canvas.TextWidth(pColumn.Title.Caption) + lImgBmp.Width) then
                begin
                      pColumn.Width := (Canvas.TextWidth(pColumn.Title.Caption) + 4) + 5;
                end;

                pColumn.Title.Caption := '   ' + Trim(pColumn.Title.Caption);
                Canvas.Draw(lNoPosLef, lNoPosTop, lImgBmp);
            finally
                lImgBmp.Free;
            end;
     end else
     begin
           pColumn.Title.Caption := Trim(pColumn.Title.Caption);
     end;
end;

procedure TBrCustomDBGrid.EditButtonClick;
begin
  if Assigned(FOnEditButtonClick) then
    FOnEditButtonClick(Self)
  else
    ShowPopupEditor(Columns[SelectedIndex]);
end;

procedure TBrCustomDBGrid.EditingChanged;
begin
  if dgIndicator in Options then InvalidateCell(0, FSelRow);
end;

procedure TBrCustomDBGrid.EndLayout;
begin
  if FLayoutLock > 0 then
  begin
    try
      try
        if FLayoutLock = 1 then
          InternalLayout;
      finally
        if FLayoutLock = 1 then
          FColumns.EndUpdate;
      end;
    finally
      Dec(FLayoutLock);
      EndUpdate;
    end;
  end;
end;

procedure TBrCustomDBGrid.EndUpdate;
begin
  if FUpdateLock > 0 then
    Dec(FUpdateLock);
end;

function TBrCustomDBGrid.GetColField(DataCol: Integer): TField;
begin
  Result := nil;
  if (DataCol >= 0) and FDatalink.Active and (DataCol < Columns.Count) then
    Result := Columns[DataCol].Field;
end;

function TBrCustomDBGrid.GetDataSource: TDataSource;
begin
  Result := FDataLink.DataSource;
end;

function TBrCustomDBGrid.GetEditLimit: Integer;
begin
  Result := 0;
  if Assigned(SelectedField) and (SelectedField.DataType in [ftString, ftWideString]) then
    Result := SelectedField.Size;
end;

function TBrCustomDBGrid.GetEditMask(ACol, ARow: Longint): string;
begin
  Result := '';
  if FDatalink.Active then
  with Columns[RawToDataColumn(ACol)] do
    if Assigned(Field) then
      Result := Field.EditMask;
end;

function TBrCustomDBGrid.GetEditText(ACol, ARow: Longint): string;
begin
  Result := '';
  if FDatalink.Active then
  with Columns[RawToDataColumn(ACol)] do
    if Assigned(Field) then
      Result := Field.Text;
  FEditText := Result;
end;

function TBrCustomDBGrid.GetFieldCount: Integer;
begin
  Result := FDatalink.FieldCount;
end;

function TBrCustomDBGrid.GetFields(FieldIndex: Integer): TField;
begin
  Result := FDatalink.Fields[FieldIndex];
end;

function TBrCustomDBGrid.GetFieldValue(ACol: Integer): string;
var
  Field: TField;
begin
  Result := '';
  Field := GetColField(ACol);
  if Field <> nil then Result := Field.DisplayText;
end;

function TBrCustomDBGrid.GetSelectedField: TField;
var
  Index: Integer;
begin
  Index := SelectedIndex;
  if Index <> -1 then
    Result := Columns[Index].Field
  else
    Result := nil;
end;

function TBrCustomDBGrid.GetSelectedIndex: Integer;
begin
  Result := RawToDataColumn(Col);
end;

function TBrCustomDBGrid.HighlightCell(DataCol, DataRow: Integer;
  const Value: string; AState: TGridDrawState): Boolean;
var
  Index: Integer;
begin
  Result := False;
  if (dgMultiSelect in Options) and Datalink.Active then
    Result := FBookmarks.Find(Datalink.Datasource.Dataset.Bookmark, Index);
  if not Result then
    Result := (gdSelected in AState)
      and ((dgAlwaysShowSelection in Options) or Focused)
        { updatelock eliminates flicker when tabbing between rows }
      and ((UpdateLock = 0) or (dgRowSelect in Options));
end;

procedure TBrCustomDBGrid.KeyDown(var Key: Word; Shift: TShiftState);
var
  KeyDownEvent: TKeyEvent;

  procedure ClearSelection;
  begin
    if (dgMultiSelect in Options) then
    begin
      FBookmarks.Clear;
      FSelecting := False;
    end;
  end;

  procedure DoSelection(Select: Boolean; Direction: Integer);
  var
    AddAfter: Boolean;
  begin
    AddAfter := False;
    BeginUpdate;
    try
      if (dgMultiSelect in Options) and FDatalink.Active then
        if Select and (ssShift in Shift) then
        begin
          if not FSelecting then
          begin
            FSelectionAnchor := FBookmarks.CurrentRow;
            FBookmarks.CurrentRowSelected := True;
            FSelecting := True;
            AddAfter := True;
          end
          else
          with FBookmarks do
          begin
            AddAfter := Compare(CurrentRow, FSelectionAnchor) <> -Direction;
            if not AddAfter then
              CurrentRowSelected := False;
          end
        end
        else
          ClearSelection;
// Bravo =-=-=-=-=-=-=-=
      RowExit;
// =-=-=-=-=
      FDatalink.MoveBy(Direction);
      if AddAfter then FBookmarks.CurrentRowSelected := True;
    finally
      EndUpdate;
    end;
  end;

  procedure NextRow(Select: Boolean);
  begin
    with FDatalink.Dataset do
    begin
      if (State = dsInsert) and not Modified and not FDatalink.FModified then
        if FDataLink.EOF then Exit else Cancel
      else

        DoSelection(Select, 1);
      if FDataLink.EOF and CanModify and (not ReadOnly) and (dgEditing in Options) then
        Append;
    end;
  end;

  procedure PriorRow(Select: Boolean);
  begin
    with FDatalink.Dataset do
      if (State = dsInsert) and not Modified and FDataLink.EOF and
        not FDatalink.FModified then
        Cancel
      else
        DoSelection(Select, -1);
  end;

  procedure Tab(GoForward: Boolean);
  var
    ACol, Original: Integer;
  begin
    ACol := Col;
    Original := ACol;
    BeginUpdate;    { Prevent highlight flicker on tab to next/prior row }
    try
      while True do
      begin
        if GoForward then
          Inc(ACol) else
          Dec(ACol);
        if ACol >= ColCount then
        begin
          NextRow(False);
          ACol := FIndicatorOffset;
        end
        else if ACol < FIndicatorOffset then
        begin
          PriorRow(False);
          ACol := ColCount - FIndicatorOffset;
        end;
        if ACol = Original then Exit;
        if TabStops[ACol] then
        begin
          MoveCol(ACol, 0);
          Exit;
        end;
      end;
    finally
      EndUpdate;
    end;
  end;

  function DeletePrompt: Boolean;
  var
    Msg: string;
  begin
    if (FBookmarks.Count > 1) then
      Msg := SDeleteMultipleRecordsQuestion
    else
      Msg := SDeleteRecordQuestion;
    Result := not (dgConfirmDelete in Options) or
      (MessageDlg(Msg, mtConfirmation, mbOKCancel, 0) <> idCancel);
  end;

const
  RowMovementKeys = [VK_UP, VK_PRIOR, VK_DOWN, VK_NEXT, VK_HOME, VK_END];

begin

  KeyDownEvent := OnKeyDown;
  if Assigned(KeyDownEvent) then KeyDownEvent(Self, Key, Shift);
  if not FDatalink.Active or not CanGridAcceptKey(Key, Shift) then Exit;

// ====== Bravo
     if (not (key in RowMovementKeys)) and (not (key in [37,39])) and
        (key <> VK_TAB) then
     begin
           if not (csDesigning in ComponentState) then
           begin
                 if (Datalink.Active) and (not Datalink.Readonly) then
                 begin
                       BeginUpdate;

                       if (Datalink <> nil) and (Datalink.Active) and
                          (Assigned(Columns[SelectedIndex].Field)) then
                       begin
                             case Columns[SelectedIndex].Field.DataType of
                                  FtMemo,
                                  ftOraClob : MostrarMemo(Columns[SelectedIndex]);
                                  FtString,
                                  ftWideString : AlterarString(Columns[SelectedIndex],
                                                                                   False);
                             end;
                       end;

                       EndUpdate;
                 end;
           end;
     end;
//============

  if UseRightToLeftAlignment then
    if Key = VK_LEFT then
      Key := VK_RIGHT
    else if Key = VK_RIGHT then
      Key := VK_LEFT;
  with FDatalink.DataSet do
    if ssCtrl in Shift then
    begin
      if (Key in RowMovementKeys) then ClearSelection;
// Bravo =-=-=-=-=-=-=-=
      case Key of
        VK_UP, VK_PRIOR: begin
                               RowExit;
                               FDataLink.MoveBy(-FDatalink.ActiveRecord);
                         end;
        VK_DOWN, VK_NEXT: begin
                                RowExit;
                                FDataLink.MoveBy(FDatalink.BufferCount -
                                                    FDatalink.ActiveRecord - 1);
                          end;
        VK_LEFT: MoveCol(FIndicatorOffset, 1);
        VK_RIGHT: MoveCol(ColCount - 1, -1);
        VK_HOME: begin
                       RowExit;
                       First;
                 end;
        VK_END: begin
                      RowExit;
                      Last;
                end;
// =-=-=-=-=-=-=-=
        VK_DELETE:
          if (not ReadOnly) and not IsEmpty
            and CanModify and DeletePrompt then
          if FBookmarks.Count > 0 then
            FBookmarks.Delete
          else
            Delete;
      end
    end
    else
      case Key of
        VK_UP: PriorRow(True);
        VK_DOWN: NextRow(True);
        VK_LEFT:
          if dgRowSelect in Options then
            PriorRow(False) else
            MoveCol(Col - 1, -1);
        VK_RIGHT:
          if dgRowSelect in Options then
            NextRow(False) else
            MoveCol(Col + 1, 1);
        VK_HOME:
          if (ColCount = FIndicatorOffset+1)
            or (dgRowSelect in Options) then
          begin
            ClearSelection;
            First;
          end
          else
            MoveCol(FIndicatorOffset, 1);
        VK_END:
          if (ColCount = FIndicatorOffset+1)
            or (dgRowSelect in Options) then
          begin
            ClearSelection;
            Last;
          end
          else
            MoveCol(ColCount - 1, -1);
        VK_NEXT:
          begin
            ClearSelection;
// Bravo =-=-=-=-=-=-=-=
            RowExit;
// =-=-=-=-=-=-=-=
            FDataLink.MoveBy(VisibleRowCount);
          end;
        VK_PRIOR:
          begin
            ClearSelection;
// Bravo =-=-=-=-=-=-=-=
            RowExit;
// =-=-=-=-=-=-=-=
            FDataLink.MoveBy(-VisibleRowCount);
          end;
        VK_INSERT:
          if CanModify and (not ReadOnly) and (dgEditing in Options) then
          begin
            ClearSelection;
            Insert;
          end;
        VK_TAB: if not (ssAlt in Shift) then Tab(not (ssShift in Shift));
        VK_ESCAPE:
          begin
            if SysLocale.PriLangID = LANG_KOREAN then
              FIsESCKey := True;
            FDatalink.Reset;
            ClearSelection;
            if not (dgAlwaysShowEditor in Options) then HideEditor;
          end;
        VK_F2: EditorMode := True;
      end;
end;

procedure TBrCustomDBGrid.KeyPress(var Key: Char);
begin
  FIsESCKey := False;
  if not (dgAlwaysShowEditor in Options) and (Key = #13) then
    FDatalink.UpdateData;
  inherited KeyPress(Key);
end;

{ InternalLayout is called with layout locks and column locks in effect }
procedure TBrCustomDBGrid.InternalLayout;

  function FieldIsMapped(F: TField): Boolean;
  var
    X: Integer;
  begin
    Result := False;
    if F = nil then Exit;
    for X := 0 to FDatalink.FieldCount-1 do
      if FDatalink.Fields[X] = F then
      begin
        Result := True;
        Exit;
      end;
  end;

  procedure CheckForPassthroughs;  // check for Columns.State flip-flop
  var
    SeenPassthrough: Boolean;
    I, J: Integer;
    Column: TColumn;
  begin
    SeenPassthrough := False;
    for I := 0 to FColumns.Count-1 do
      if not FColumns[I].IsStored then
        SeenPassthrough := True
      else if SeenPassthrough then
      begin  // we have both persistent and non-persistent columns.  Kill the latter
        for J := FColumns.Count-1 downto 0 do
        begin
          Column := FColumns[J];
          if not Column.IsStored then
            Column.Free;
        end;
        Exit;
      end;
  end;

  procedure ResetColumnFieldBindings;
  var
    I, J, K: Integer;
    Fld: TField;
    Column: TColumn;
  begin
    if FColumns.State = csDefault then
    begin
       { Destroy columns whose fields have been destroyed or are no longer
         in field map }
      if (not FDataLink.Active) and (FDatalink.DefaultFields) then
        FColumns.Clear
      else
        for J := FColumns.Count-1 downto 0 do
          with FColumns[J] do
          if not Assigned(Field)
            or not FieldIsMapped(Field) then Free;
      I := FDataLink.FieldCount;
      if (I = 0) and (FColumns.Count = 0) then Inc(I);
      for J := 0 to I-1 do
      begin
        Fld := FDatalink.Fields[J];
        if Assigned(Fld) then
        begin
          K := J;
           { Pointer compare is valid here because the grid sets matching
             column.field properties to nil in response to field object
             free notifications.  Closing a dataset that has only default
             field objects will destroy all the fields and set associated
             column.field props to nil. }
          while (K < FColumns.Count) and (FColumns[K].Field <> Fld) do
            Inc(K);
          if K < FColumns.Count then
            Column := FColumns[K]
          else
          begin
            Column := FColumns.InternalAdd;
            Column.Field := Fld;
          end;
        end
        else
          Column := FColumns.InternalAdd;
        Column.Index := J;
      end;
    end
    else
    begin
      { Force columns to reaquire fields (in case dataset has changed) }
      for I := 0 to FColumns.Count-1 do
        FColumns[I].Field := nil;
    end;
  end;

  procedure MeasureTitleHeights;
  var
    I, J, K, D, B: Integer;
    RestoreCanvas: Boolean;
    Heights: array of Integer;
  begin
    RestoreCanvas := not HandleAllocated;
    if RestoreCanvas then
      Canvas.Handle := GetDC(0);
    try
      Canvas.Font := Font;
      K := Canvas.TextHeight('Wg') + 3;
      if dgRowLines in Options then
        Inc(K, GridLineWidth);
      DefaultRowHeight := K;
      B := GetSystemMetrics(SM_CYHSCROLL);
      if dgTitles in Options then
      begin
        SetLength(Heights, FTitleOffset+1);
        for I := 0 to FColumns.Count-1 do
        begin
          Canvas.Font := FColumns[I].Title.Font;
          D := FColumns[I].Depth;
          if D <= High(Heights) then
          begin
            J := Canvas.TextHeight('Wg') + 4;
            if FColumns[I].Expandable and (B > J) then
              J := B;
            Heights[D] := Max(J, Heights[D]);
          end;
        end;
        if Heights[0] = 0 then
        begin
          Canvas.Font := FTitleFont;
          Heights[0] := Canvas.TextHeight('Wg') + 4;
        end;
        for I := 0 to High(Heights)-1 do
          RowHeights[I] := Heights[I];
      end;
    finally
      if RestoreCanvas then
      begin
        ReleaseDC(0,Canvas.Handle);
        Canvas.Handle := 0;
      end;
    end;
  end;

var
  I, J: Integer;
begin
  if (csLoading in ComponentState) then Exit;

  if HandleAllocated then KillMessage(Handle, cm_DeferLayout);

  CheckForPassthroughs;
  FIndicatorOffset := 0;
  if dgIndicator in Options then
    Inc(FIndicatorOffset);
  FDatalink.ClearMapping;
  if FDatalink.Active then DefineFieldMap;
  DoubleBuffered := (FDatalink.Dataset <> nil) and FDatalink.Dataset.ObjectView;
  ResetColumnFieldBindings;
  FVisibleColumns.Clear;
  for I := 0 to FColumns.Count-1 do
    if FColumns[I].Showing then FVisibleColumns.Add(FColumns[I]);
  ColCount := FColumns.Count + FIndicatorOffset;
  inherited FixedCols := FIndicatorOffset;
  FTitleOffset := 0;
  if dgTitles in Options then
  begin
    FTitleOffset := 1;
    if (FDatalink <> nil) and (FDatalink.Dataset <> nil)
      and FDatalink.Dataset.ObjectView then
    begin
      for I := 0 to FColumns.Count-1 do
      begin
        if FColumns[I].Showing then
        begin
          J := FColumns[I].Depth;
          if J >= FTitleOffset then FTitleOffset := J+1;
        end;
      end;
    end;
  end;
  UpdateRowCount;
  MeasureTitleHeights;
  SetColumnAttributes;
  UpdateActive;
  Invalidate;
end;

procedure TBrCustomDBGrid.LayoutChanged;
begin
  if AcquireLayoutLock then
    EndLayout;
end;

procedure TBrCustomDBGrid.LinkActive(Value: Boolean);
var
  Comp: TComponent;
  I: Integer;
begin
  if not Value then HideEditor;
  FBookmarks.LinkActive(Value);
  try
    LayoutChanged;
  finally
    for I := ComponentCount-1 downto 0 do
    begin
      Comp := Components[I];   // Free all the popped-up subgrids
      if (Comp is TBrCustomDBGrid)
        and (TBrCustomDBGrid(Comp).DragKind = dkDock) then
        Comp.Free;
    end;
    UpdateScrollBar;
    if Value and (dgAlwaysShowEditor in Options) then ShowEditor;
  end;
end;

procedure TBrCustomDBGrid.Loaded;
begin
  inherited Loaded;
  if FColumns.Count > 0 then
    ColCount := FColumns.Count;
  LayoutChanged;
end;

function TBrCustomDBGrid.PtInExpandButton(X,Y: Integer; var MasterCol: TColumn): Boolean;
var
  Cell: TGridCoord;
  R: TRect;
begin
  MasterCol := nil;
  Result := False;
  Cell := MouseCoord(X,Y);
  if (Cell.Y < FTitleOffset) and FDatalink.Active
    and (Cell.X >= FIndicatorOffset)
    and (RawToDataColumn(Cell.X) < Columns.Count) then
  begin
    R := CalcTitleRect(Columns[RawToDataColumn(Cell.X)], Cell.Y, MasterCol);
    if not UseRightToLeftAlignment then
      R.Left := R.Right - GetSystemMetrics(SM_CXHSCROLL)
    else
      R.Right := R.Left + GetSystemMetrics(SM_CXHSCROLL);
    Result := MasterCol.Expandable and PtInRect(R, Point(X,Y));
  end;
end;

procedure TBrCustomDBGrid.MouseDown(Button: TMouseButton; Shift: TShiftState;
  X, Y: Integer);
var
  Cell: TGridCoord;
  OldCol,OldRow: Integer;
  MasterCol: TColumn;
begin
  if not AcquireFocus then Exit;
  if (ssDouble in Shift) and (Button = mbLeft) then
  begin
    DblClick;
    Exit;
  end;

  if Sizing(X, Y) then
  begin
    FDatalink.UpdateData;
    inherited MouseDown(Button, Shift, X, Y);
    Exit;
  end;

  Cell := MouseCoord(X, Y);
  if (Cell.X < 0) and (Cell.Y < 0) then
  begin
    inherited MouseDown(Button, Shift, X, Y);
    Exit;
  end;

  if (DragKind = dkDock) and (Cell.X < FIndicatorOffset) and
    (Cell.Y < FTitleOffset) and (not (csDesigning in ComponentState)) then
  begin
    BeginDrag(false);
    Exit;
  end;

  if PtInExpandButton(X,Y, MasterCol) then
  begin
    MasterCol.Expanded := not MasterCol.Expanded;
    ReleaseCapture;
    UpdateDesigner;
    Exit;
  end;

  if ((csDesigning in ComponentState) or (dgColumnResize in Options)) and
    (Cell.Y < FTitleOffset) then
  begin
    FDataLink.UpdateData;
    inherited MouseDown(Button, Shift, X, Y);
    Exit;
  end;

  if FDatalink.Active then
    with Cell do
    begin
      BeginUpdate;   { eliminates highlight flicker when selection moves }
      try
        FDatalink.UpdateData; // validate before moving
        HideEditor;
        OldCol := Col;
        OldRow := Row;
        if (Y >= FTitleOffset) and (Y - Row <> 0) then
        begin
// Bravo =-=-=-=-=-=-=-=
          RowExit;
// =-=-=-=-=-=-=-=
          FDatalink.MoveBy(Y - Row);
        end;
        if X >= FIndicatorOffset then
          MoveCol(X, 0);
        if (dgMultiSelect in Options) and FDatalink.Active then
          with FBookmarks do
          begin
            FSelecting := False;
            if ssCtrl in Shift then
              CurrentRowSelected := not CurrentRowSelected
            else
            begin
              Clear;
              CurrentRowSelected := True;
            end;
          end;
        if (Button = mbLeft) and
          (((X = OldCol) and (Y = OldRow)) or (dgAlwaysShowEditor in Options)) then
          ShowEditor         { put grid in edit mode }
        else
          InvalidateEditor;  { draw editor, if needed }
      finally
        EndUpdate;
      end;
    end;
end;

procedure TBrCustomDBGrid.MouseUp(Button: TMouseButton; Shift: TShiftState;
  X, Y: Integer);
var
  Cell: TGridCoord;
  SaveState: TGridState;
begin
  SaveState := FGridState;
  inherited MouseUp(Button, Shift, X, Y);
  if (SaveState = gsRowSizing) or (SaveState = gsColSizing) or
    ((InplaceEditor <> nil) and (InplaceEditor.Visible) and
     (PtInRect(InplaceEditor.BoundsRect, Point(X,Y)))) then Exit;
  Cell := MouseCoord(X,Y);
  if (Button = mbLeft) and (Cell.X >= FIndicatorOffset) and (Cell.Y >= 0) then
    if Cell.Y < FTitleOffset then
      TitleClick(Columns[RawToDataColumn(Cell.X)])
    else
      CellClick(Columns[SelectedIndex]);
end;

procedure TBrCustomDBGrid.MoveCol(RawCol, Direction: Integer);
var
  OldCol: Integer;
begin
  FDatalink.UpdateData;
  if RawCol >= ColCount then
    RawCol := ColCount - 1;
  if RawCol < FIndicatorOffset then RawCol := FIndicatorOffset;
  if Direction <> 0 then
  begin
    while (RawCol < ColCount) and (RawCol >= FIndicatorOffset) and
      (ColWidths[RawCol] <= 0) do
      Inc(RawCol, Direction);
    if (RawCol >= ColCount) or (RawCol < FIndicatorOffset) then Exit;
  end;
  OldCol := Col;
  if RawCol <> OldCol then
  begin
    if not FInColExit then
    begin
      FInColExit := True;
      try
        ColExit;
      finally
        FInColExit := False;
      end;
      if Col <> OldCol then Exit;
    end;
    if not (dgAlwaysShowEditor in Options) then HideEditor;
    Col := RawCol;
    ColEnter;
  end;
end;

procedure TBrCustomDBGrid.Notification(AComponent: TComponent;
  Operation: TOperation);
var
  I: Integer;
  NeedLayout: Boolean;
begin
  inherited Notification(AComponent, Operation);
  if (Operation = opRemove) then
  begin
    if (AComponent is TPopupMenu) then
    begin
      for I := 0 to Columns.Count-1 do
        if Columns[I].PopupMenu = AComponent then
          Columns[I].PopupMenu := nil;
    end
    else if (FDataLink <> nil) then
      if (AComponent = DataSource)  then
        DataSource := nil
      else if (AComponent is TField) then
      begin
        NeedLayout := False;
        BeginLayout;
        try
          for I := 0 to Columns.Count-1 do
            with Columns[I] do
              if Field = AComponent then
              begin
                Field := nil;
                NeedLayout := True;
              end;
        finally
          if NeedLayout and Assigned(FDatalink.Dataset)
            and not FDatalink.Dataset.ControlsDisabled then
            EndLayout
          else
            DeferLayout;
        end;
      end;
  end;
end;

procedure TBrCustomDBGrid.RecordChanged(Field: TField);
var
  I: Integer;
  CField: TField;
begin
  if not HandleAllocated then Exit;
  if Field = nil then
    Invalidate
  else
  begin
    for I := 0 to Columns.Count - 1 do
      if Columns[I].Field = Field then
        InvalidateCol(DataToRawColumn(I));
  end;
  CField := SelectedField;
  if ((Field = nil) or (CField = Field)) and
    (Assigned(CField) and (CField.Text <> FEditText) and
    ((SysLocale.PriLangID <> LANG_KOREAN) or FIsESCKey)) then
  begin
    InvalidateEditor;
    if InplaceEditor <> nil then InplaceEditor.Deselect;
  end;
end;

procedure TBrCustomDBGrid.Scroll(Distance: Integer);
var
  OldRect, NewRect: TRect;
  RowHeight: Integer;
begin
  if not HandleAllocated then Exit;
  OldRect := BoxRect(0, Row, ColCount - 1, Row);
  if (FDataLink.ActiveRecord >= RowCount - FTitleOffset) then UpdateRowCount;
  UpdateScrollBar;
  UpdateActive;
  NewRect := BoxRect(0, Row, ColCount - 1, Row);
  ValidateRect(Handle, @OldRect);
  InvalidateRect(Handle, @OldRect, False);
  InvalidateRect(Handle, @NewRect, False);
  if Distance <> 0 then
  begin
    HideEditor;
    try
      if Abs(Distance) > VisibleRowCount then
      begin
        Invalidate;
        Exit;
      end
      else
      begin
        RowHeight := DefaultRowHeight;
        if dgRowLines in Options then Inc(RowHeight, GridLineWidth);
        if dgIndicator in Options then
        begin
          OldRect := BoxRect(0, FSelRow, ColCount - 1, FSelRow);
          InvalidateRect(Handle, @OldRect, False);
        end;
        NewRect := BoxRect(0, FTitleOffset, ColCount - 1, 1000);
        ScrollWindowEx(Handle, 0, -RowHeight * Distance, @NewRect, @NewRect,
          0, nil, SW_Invalidate);
        if dgIndicator in Options then
        begin
          NewRect := BoxRect(0, Row, ColCount - 1, Row);
          InvalidateRect(Handle, @NewRect, False);
        end;
      end;
    finally
      if dgAlwaysShowEditor in Options then ShowEditor;
    end;
  end;
  if UpdateLock = 0 then Update;
end;

procedure TBrCustomDBGrid.SetColumns(Value: TDBGridColumns);
begin
  Columns.Assign(Value);
end;

function ReadOnlyField(Field: TField): Boolean;
var
  MasterField: TField;
begin
  Result := Field.ReadOnly;
  if not Result and (Field.FieldKind = fkLookup) then
  begin
    Result := True;
    if Field.DataSet = nil then Exit;
    MasterField := Field.Dataset.FindField(Field.KeyFields);
    if MasterField = nil then Exit;
    Result := MasterField.ReadOnly;
  end;
end;

procedure TBrCustomDBGrid.SetColumnAttributes;
var
  I: Integer;
begin
  for I := 0 to FColumns.Count-1 do
  with FColumns[I] do
  begin
    TabStops[I + FIndicatorOffset] := Showing and not ReadOnly and DataLink.Active and
      Assigned(Field) and not (Field.FieldKind = fkCalculated) and not ReadOnlyField(Field);
    ColWidths[I + FIndicatorOffset] := Width;
  end;
  if (dgIndicator in Options) then
    ColWidths[0] := IndicatorWidth;
end;

procedure TBrCustomDBGrid.SetDataSource(Value: TDataSource);
begin
  if Value = FDatalink.Datasource then Exit;
  FBookmarks.Clear;
  FDataLink.DataSource := Value;
  if Value <> nil then Value.FreeNotification(Self);
end;

procedure TBrCustomDBGrid.SetEditText(ACol, ARow: Longint; const Value: string);
begin
  FEditText := Value;
end;

procedure TBrCustomDBGrid.SetOptions(Value: TDBGridOptions);
const
  LayoutOptions = [dgEditing, dgAlwaysShowEditor, dgTitles, dgIndicator,
    dgColLines, dgRowLines, dgRowSelect, dgAlwaysShowSelection];
var
  NewGridOptions: TGridOptions;
  ChangedOptions: TDBGridOptions;
begin
  if FOptions <> Value then
  begin
    NewGridOptions := [];
    if dgColLines in Value then
      NewGridOptions := NewGridOptions + [goFixedVertLine, goVertLine];
    if dgRowLines in Value then
      NewGridOptions := NewGridOptions + [goFixedHorzLine, goHorzLine];
    if dgColumnResize in Value then
      NewGridOptions := NewGridOptions + [goColSizing, goColMoving];
    if dgTabs in Value then Include(NewGridOptions, goTabs);
    if dgRowSelect in Value then
    begin
      Include(NewGridOptions, goRowSelect);
      Exclude(Value, dgAlwaysShowEditor);
      Exclude(Value, dgEditing);
    end;
    if dgEditing in Value then Include(NewGridOptions, goEditing);
    if dgAlwaysShowEditor in Value then Include(NewGridOptions, goAlwaysShowEditor);
    inherited Options := NewGridOptions;
    if dgMultiSelect in (FOptions - Value) then FBookmarks.Clear;
    ChangedOptions := (FOptions + Value) - (FOptions * Value);
    FOptions := Value;
    if ChangedOptions * LayoutOptions <> [] then LayoutChanged;
  end;
end;

procedure TBrCustomDBGrid.SetSelectedField(Value: TField);
var
  I: Integer;
begin
  if Value = nil then Exit;
  for I := 0 to Columns.Count - 1 do
    if Columns[I].Field = Value then
      MoveCol(DataToRawColumn(I), 0);
end;

procedure TBrCustomDBGrid.SetSelectedIndex(Value: Integer);
begin
  MoveCol(DataToRawColumn(Value), 0);
end;

procedure TBrCustomDBGrid.SetTitleFont(Value: TFont);
begin
  FTitleFont.Assign(Value);
  if dgTitles in Options then LayoutChanged;
end;

function TBrCustomDBGrid.StoreColumns: Boolean;
begin
  Result := Columns.State = csCustomized;
end;

procedure TBrCustomDBGrid.TimedScroll(Direction: TGridScrollDirection);
begin
  if FDatalink.Active then
  begin
    with FDatalink do
    begin
      if sdUp in Direction then
      begin
// Bravo =-=-=-=-=-=-=-=
        RowExit;
// =-=-=-=-=-=-=-=
        FDataLink.MoveBy(-ActiveRecord - 1);
        Exclude(Direction, sdUp);
      end;
      if sdDown in Direction then
      begin
// Bravo =-=-=-=-=-=-=-=
        RowExit;
// =-=-=-=-=-=-=-=
        FDataLink.MoveBy(RecordCount - ActiveRecord);
        Exclude(Direction, sdDown);
      end;
    end;
    if Direction <> [] then inherited TimedScroll(Direction);
  end;
end;

procedure TBrCustomDBGrid.TitleClick(Column: TColumn);
begin
      if ((DataSource.DataSet is TClientDataSet) and
          (DataSource.DataSet.Active           ) and
          (not (DataSource.DataSet.State in [dsEdit, dsInsert]))) then
      begin
            // =-=-=-=-=-= Bravo - Ordenação da grade com clique no título
            CliqueNoTitulo(Column);
      end else
      begin
            gTpOrdem  := '0';
      end;

      if Assigned(FOnTitleClick) then FOnTitleClick(Column);
end;

procedure TBrCustomDBGrid.CliqueNoTitulo(Column : TColumn);
begin
      if not (Column.Field.DataType in [ftMemo, ftOraClob]) then
      begin
            OrdenarClientDataSet((DataSource.DataSet as TClientDataSet),
                                  Column.FieldName, False, Column.Index);
      end else
      begin
            MessageDlg('Campo do tipo texto não pode ser ordenado.', mtInformation,
                                                                               [MbOk], 0);
      end;
end;

// =-=-=-= Bravo
procedure TBrCustomDBGrid.OrdenarClientDataSet(CCOrdena : TClientDataSet;
                                               pNmColuna : String; pSnReInic : Boolean;
                                               pNrColCli :Integer);
var lNrIdxCon : Shortint;
    lSnMesmo  : Boolean;
begin
      gNrColCli := pNrColCli;

      lSnMesmo  := False;
      lNrIdxCon := CCOrdena.IndexDefs.IndexOf('IndexOrdGri');

      if lNrIdxCon >= 0 then
      begin
            if CCOrdena.IndexDefs[lNrIdxCon].Fields = pNmColuna then
            begin
                  lSnMesmo := True;

                  if ixDescending in CCOrdena.IndexDefs[lNrIdxCon].Options then
                  begin
                        lSnMesmo := False;
                  end;
            end;

            try
                CCOrdena.DeleteIndex('IndexOrdGri');
            except
            end;
            CCOrdena.IndexDefs.Delete(lNrIdxCon);
      end;

      if pSnReInic then // reiniciando a consulta
      begin
            //Mantendo a ordenação anterior
            lSnMesmo := gTpOrdem = 'C';
      end;

      if lSnMesmo then
      begin
            CCOrdena.IndexDefs.Add('IndexOrdGri', pNmColuna,
                                                        [ixCaseInsensitive,ixDescending]);
            gTpOrdem := 'C';
      end else
      begin
            CCOrdena.IndexDefs.Add('IndexOrdGri', pNmColuna, [ixCaseInsensitive]);
            gTpOrdem := 'B';
      end;

      CCOrdena.IndexName := 'IndexOrdGri';
end;

procedure TBrCustomDBGrid.TitleFontChanged(Sender: TObject);
begin
  if (not FSelfChangingTitleFont) and not (csLoading in ComponentState) then
    ParentFont := False;
  if dgTitles in Options then LayoutChanged;
end;

procedure TBrCustomDBGrid.UpdateActive;
var
  NewRow: Integer;
  Field: TField;
begin
  if FDatalink.Active and HandleAllocated and not (csLoading in ComponentState) then
  begin
    NewRow := FDatalink.ActiveRecord + FTitleOffset;
    if Row <> NewRow then
    begin
      if not (dgAlwaysShowEditor in Options) then HideEditor;
      MoveColRow(Col, NewRow, False, False);
      InvalidateEditor;
    end;
    Field := SelectedField;
    if Assigned(Field) and (Field.Text <> FEditText) then
      InvalidateEditor;
  end;
end;

procedure TBrCustomDBGrid.UpdateData;
var
  Field: TField;
begin
  Field := SelectedField;
  if Assigned(Field) then
    Field.Text := FEditText;
end;

procedure TBrCustomDBGrid.UpdateRowCount;
var
  OldRowCount: Integer;
begin
  OldRowCount := RowCount;
  if RowCount <= FTitleOffset then RowCount := FTitleOffset + 1;
  FixedRows := FTitleOffset;
  with FDataLink do
    if not Active or (RecordCount = 0) or not HandleAllocated then
      RowCount := 1 + FTitleOffset
    else
    begin
      RowCount := 1000;
      FDataLink.BufferCount := VisibleRowCount;
      RowCount := RecordCount + FTitleOffset;
      if dgRowSelect in Options then TopRow := FixedRows;
      UpdateActive;
    end;
  if OldRowCount <> RowCount then Invalidate;
end;

procedure TBrCustomDBGrid.UpdateScrollBar;
var
  SIOld, SINew: TScrollInfo;
begin
  if FDatalink.Active and HandleAllocated then
    with FDatalink.DataSet do
    begin
      SIOld.cbSize := sizeof(SIOld);
      SIOld.fMask := SIF_ALL;
      GetScrollInfo(Self.Handle, SB_VERT, SIOld);
      SINew := SIOld;
      if IsSequenced then
      begin
        SINew.nMin := 1;
        SINew.nPage := Self.VisibleRowCount;
        SINew.nMax := Integer(DWORD(RecordCount) + SINew.nPage - 1);
        if State in [dsInactive, dsBrowse, dsEdit] then
          SINew.nPos := RecNo;  // else keep old pos
      end
      else
      begin
        SINew.nMin := 0;
        SINew.nPage := 0;
        SINew.nMax := 4;
        if FDataLink.BOF then SINew.nPos := 0
        else if FDataLink.EOF then SINew.nPos := 4
        else SINew.nPos := 2;
      end;
      if (SINew.nMin <> SIOld.nMin) or (SINew.nMax <> SIOld.nMax) or
        (SINew.nPage <> SIOld.nPage) or (SINew.nPos <> SIOld.nPos) then
        SetScrollInfo(Self.Handle, SB_VERT, SINew, True);
    end;
end;

function TBrCustomDBGrid.ValidFieldIndex(FieldIndex: Integer): Boolean;
begin
  Result := DataLink.GetMappedIndex(FieldIndex) >= 0;
end;

procedure TBrCustomDBGrid.CMParentFontChanged(var Message: TMessage);
begin
  inherited;
  if ParentFont then
  begin
    FSelfChangingTitleFont := True;
    try
      TitleFont := Font;
    finally
      FSelfChangingTitleFont := False;
    end;
    LayoutChanged;
  end;
end;

procedure TBrCustomDBGrid.CMBiDiModeChanged(var Message: TMessage);
var
  Loop: Integer;
begin
  inherited;
  for Loop := 0 to ComponentCount - 1 do
    if Components[Loop] is TBrCustomDBGrid then
      with Components[Loop] as TBrCustomDBGrid do
        { Changing the window, echos down to the subgrid }
        if Parent <> nil then
          Parent.BiDiMode := Self.BiDiMode;
end;

procedure TBrCustomDBGrid.CMExit(var Message: TMessage);
begin
  try
    if FDatalink.Active then
      with FDatalink.Dataset do
        if (dgCancelOnExit in Options) and (State = dsInsert) and
          not Modified and not FDatalink.FModified then
          Cancel else
          FDataLink.UpdateData;
  except
    SetFocus;
    raise;
  end;
  inherited;
end;

procedure TBrCustomDBGrid.CMFontChanged(var Message: TMessage);
var
  I: Integer;
begin
  inherited;
  BeginLayout;
  try
    for I := 0 to Columns.Count-1 do
      Columns[I].RefreshDefaultFont;
  finally
    EndLayout;
  end;
end;

procedure TBrCustomDBGrid.CMDeferLayout(var Message);
begin
  if AcquireLayoutLock then
    EndLayout
  else
    DeferLayout;
end;

procedure TBrCustomDBGrid.CMDesignHitTest(var Msg: TCMDesignHitTest);
var
  MasterCol: TColumn;
begin
  inherited;
  if (Msg.Result = 1) and ((FDataLink = nil) or
    ((Columns.State = csDefault) and
     (FDataLink.DefaultFields or (not FDataLink.Active)))) then
    Msg.Result := 0
  else if (Msg.Result = 0) and (FDataLink <> nil) and (FDataLink.Active)
    and (Columns.State = csCustomized)
    and PtInExpandButton(Msg.XPos, Msg.YPos, MasterCol) then
    Msg.Result := 1;
end;

procedure TBrCustomDBGrid.WMSetCursor(var Msg: TWMSetCursor);
begin
  if (csDesigning in ComponentState) and
      ((FDataLink = nil) or
       ((Columns.State = csDefault) and
        (FDataLink.DefaultFields or not FDataLink.Active))) then
    Windows.SetCursor(LoadCursor(0, IDC_ARROW))
  else inherited;
end;

procedure TBrCustomDBGrid.WMSize(var Message: TWMSize);
begin
  inherited;
  if UpdateLock = 0 then UpdateRowCount;
  InvalidateTitles;
end;

procedure TBrCustomDBGrid.WMVScroll(var Message: TWMVScroll);
var
  SI: TScrollInfo;
begin
  if not AcquireFocus then Exit;
  if FDatalink.Active then
    with Message, FDataLink.DataSet do
// Bravo =-=-=-=-=-=-=-=
      case ScrollCode of
        SB_LINEUP: begin
                         RowExit;
                         FDataLink.MoveBy(-FDatalink.ActiveRecord - 1);
                   end;
        SB_LINEDOWN: begin
                           RowExit;
                           FDataLink.MoveBy(FDatalink.RecordCount -
                                                        FDatalink.ActiveRecord);
                     end;
        SB_PAGEUP: begin
                         RowExit;
                         FDataLink.MoveBy(-VisibleRowCount);
                   end;
        SB_PAGEDOWN: begin
                           RowExit;
                           FDataLink.MoveBy(VisibleRowCount);
                     end;
// =-=-=-=-=-=-=-=

        SB_THUMBPOSITION:
          begin
            if IsSequenced then
            begin
              SI.cbSize := sizeof(SI);
              SI.fMask := SIF_ALL;
              GetScrollInfo(Self.Handle, SB_VERT, SI);
              if SI.nTrackPos <= 1 then First
              else if SI.nTrackPos >= RecordCount then Last
              else RecNo := SI.nTrackPos;
            end
            else
// Bravo =-=-=-=-=-=-=-=
              case Pos of
                0: begin
                         RowExit;
                         First;
                   end;
                1: begin
                         RowExit;
                         FDataLink.MoveBy(-VisibleRowCount);
                   end;
                2: Exit;
                3: begin
                         RowExit;
                         FDataLink.MoveBy(VisibleRowCount);
                   end;
                4: begin
                         RowExit;
                         Last;
                   end;
              end;
// =-=-=-=-=-=-=-=
          end;
        SB_BOTTOM: Last;
        SB_TOP: First;
      end;
end;

procedure TBrCustomDBGrid.SetIme;
var
  Column: TColumn;
begin
  if not SysLocale.FarEast then Exit;
  if Columns.Count = 0 then Exit;

  ImeName := FOriginalImeName;
  ImeMode := FOriginalImeMode;
  Column := Columns[SelectedIndex];
  if Column.IsImeNameStored then ImeName := Column.ImeName;
  if Column.IsImeModeStored then ImeMode := Column.ImeMode;

  if InplaceEditor <> nil then
  begin
    TDBGridInplaceEdit(Self).ImeName := ImeName;
    TDBGridInplaceEdit(Self).ImeMode := ImeMode;
  end;
end;

procedure TBrCustomDBGrid.UpdateIme;
begin
  if not SysLocale.FarEast then Exit;
  SetIme;
  SetImeName(ImeName);
  SetImeMode(Handle, ImeMode);
end;

procedure TBrCustomDBGrid.WMIMEStartComp(var Message: TMessage);
begin
  inherited;
  ShowEditor;
end;

procedure TBrCustomDBGrid.WMSetFocus(var Message: TWMSetFocus);
begin
  if not ((InplaceEditor <> nil) and
    (Message.FocusedWnd = InplaceEditor.Handle)) then SetIme;
  inherited;
end;

procedure TBrCustomDBGrid.WMKillFocus(var Message: TMessage);
begin
  if not SysLocale.FarEast then inherited
  else
  begin
    ImeName := Screen.DefaultIme;
    ImeMode := imDontCare;
    inherited;
    if not ((InplaceEditor <> nil) and
      (HWND(Message.WParam) = InplaceEditor.Handle)) then
      ActivateKeyboardLayout(Screen.DefaultKbLayout, KLF_ACTIVATE);
  end;
end;

{ Defer action processing to datalink }

function TBrCustomDBGrid.ExecuteAction(Action: TBasicAction): Boolean;
begin
  Result := (DataLink <> nil) and DataLink.ExecuteAction(Action);
end;

function TBrCustomDBGrid.UpdateAction(Action: TBasicAction): Boolean;
begin
  Result := (DataLink <> nil) and DataLink.UpdateAction(Action);
end;

procedure TBrCustomDBGrid.ShowPopupEditor(Column: TColumn; X, Y: Integer);
var
  SubGrid: TBrCustomDBGrid;
  DS: TDataSource;
  I: Integer;
  FloatRect: TRect;
  Cmp: TControl;
begin
  if not ((Column.Field <> nil) and (Column.Field is TDataSetField)) then  Exit;

  // find existing popup for this column field, if any, and show it
  for I := 0 to ComponentCount-1 do
    if Components[I] is TBrCustomDBGrid then
    begin
      SubGrid := TBrCustomDBGrid(Components[I]);
      if (SubGrid.DataSource <> nil) and
        (SubGrid.DataSource.DataSet = (Column.Field as TDatasetField).NestedDataset) and
        SubGrid.CanFocus then
      begin
        SubGrid.Parent.Show;
        SubGrid.SetFocus;
        Exit;
      end;
    end;

  // create another instance of this kind of grid
  SubGrid := TBrCustomDBGrid(TComponentClass(Self.ClassType).Create(Self));
  try
    DS := TDataSource.Create(SubGrid); // incestuous, but easy cleanup
    DS.Dataset := (Column.Field as TDatasetField).NestedDataset;
    DS.DataSet.CheckBrowseMode;
    SubGrid.DataSource := DS;
    SubGrid.Columns.State := Columns.State;
    SubGrid.Columns[0].Expanded := True;
    SubGrid.Visible := False;
    SubGrid.FloatingDockSiteClass := TCustomDockForm;
    FloatRect.TopLeft := ClientToScreen(CellRect(Col, Row).BottomRight);
    if X > Low(Integer) then FloatRect.Left := X;
    if Y > Low(Integer) then FloatRect.Top := Y;
    FloatRect.Right := FloatRect.Left + Width;
    FloatRect.Bottom := FloatRect.Top + Height;
    SubGrid.ManualFloat(FloatRect);
//    SubGrid.ManualDock(nil,nil,alClient);
    SubGrid.Parent.BiDiMode := Self.BiDiMode; { This carries the BiDi setting }
    I := SubGrid.CellRect(SubGrid.ColCount-1, 0).Right;
    if (I > 0) and (I < Screen.Width div 2) then
      SubGrid.Parent.ClientWidth := I
    else
      SubGrid.Parent.Width := Screen.Width div 4;
    SubGrid.Parent.Height := Screen.Height div 4;
    SubGrid.Align := alClient;
    SubGrid.DragKind := dkDock;
    SubGrid.Color := Color;
    SubGrid.Ctl3D := Ctl3D;
    SubGrid.Cursor := Cursor;
    SubGrid.Enabled := Enabled;
    SubGrid.FixedColor := FixedColor;
    SubGrid.Font := Font;
    SubGrid.HelpContext := HelpContext;
    SubGrid.IMEMode := IMEMode;
    SubGrid.IMEName := IMEName;
    SubGrid.Options := Options;
    Cmp := Self;
    while (Cmp <> nil) and (TBrCustomDBGrid(Cmp).PopupMenu = nil) do
      Cmp := Cmp.Parent;
    if Cmp <> nil then
      SubGrid.PopupMenu := TBrCustomDBGrid(Cmp).PopupMenu;
    SubGrid.TitleFont := TitleFont;
    SubGrid.Visible := True;
    SubGrid.Parent.Show;
  except
    SubGrid.Free;
    raise;
  end;
end;

procedure TBrCustomDBGrid.CalcSizingState(X, Y: Integer;
  var State: TGridState; var Index, SizingPos, SizingOfs: Integer;
  var FixedInfo: TGridDrawInfo);
var
  R: TGridCoord;
begin
  inherited CalcSizingState(X, Y, State, Index, SizingPos, SizingOfs, FixedInfo);
  if (State = gsColSizing) and (FDataLink <> nil)
    and (FDatalink.Dataset <> nil) and FDataLink.Dataset.ObjectView then
  begin
    R := MouseCoord(X, Y);
    R.X := RawToDataColumn(R.X);
    if (R.X >= 0) and (R.X < Columns.Count) and (Columns[R.X].Depth > R.Y) then
      State := gsNormal;
  end;
end;

function TBrCustomDBGrid.CheckColumnDrag(var Origin, Destination: Integer;
  const MousePt: TPoint): Boolean;
var
  I, ARow: Integer;
  DestCol: TColumn;
begin
  Result := inherited CheckColumnDrag(Origin, Destination, MousePt);
  if Result and (FDatalink.Dataset <> nil) and FDatalink.Dataset.ObjectView then
  begin
    assert(FDragCol <> nil);
    ARow := FDragCol.Depth;
    if Destination <> Origin then
    begin
      DestCol := ColumnAtDepth(Columns[RawToDataColumn(Destination)], ARow);
      if DestCol.ParentColumn <> FDragCol.ParentColumn then
        if Destination < Origin then
          DestCol := Columns[FDragCol.ParentColumn.Index+1]
        else
        begin
          I := DestCol.Index;
          while DestCol.ParentColumn <> FDragCol.ParentColumn do
          begin
            Dec(I);
            DestCol := Columns[I];
          end;
        end;
      if (DestCol.Index > FDragCol.Index) then
      begin
        I := DestCol.Index + 1;
        while (I < Columns.Count) and (ColumnAtDepth(Columns[I],ARow) = DestCol) do
          Inc(I);
        DestCol := Columns[I-1];
      end;
      Destination := DataToRawColumn(DestCol.Index);
    end;
  end;
end;

function TBrCustomDBGrid.BeginColumnDrag(var Origin, Destination: Integer;
  const MousePt: TPoint): Boolean;
var
  I, ARow: Integer;
begin
  Result := inherited BeginColumnDrag(Origin, Destination, MousePt);
  if Result and (FDatalink.Dataset <> nil) and FDatalink.Dataset.ObjectView then
  begin
    ARow := MouseCoord(MousePt.X, MousePt.Y).Y;
    FDragCol := ColumnAtDepth(Columns[RawToDataColumn(Origin)], ARow);
    if FDragCol = nil then Exit;
    I := DataToRawColumn(FDragCol.Index);
    if Origin <> I then Origin := I;
    Destination := Origin;
  end;
end;

function TBrCustomDBGrid.EndColumnDrag(var Origin, Destination: Integer;
  const MousePt: TPoint): Boolean;
begin
  Result := inherited EndColumnDrag(Origin, Destination, MousePt);
  FDragCol := nil;
end;

procedure TBrCustomDBGrid.InvalidateTitles;
var
  R: TRect;
  DrawInfo: TGridDrawInfo;
begin
  if HandleAllocated and (dgTitles in Options) then
  begin
    CalcFixedInfo(DrawInfo);
    R := Rect(0, 0, Width, DrawInfo.Vert.FixedBoundary);
    InvalidateRect(Handle, @R, False);
  end;
end;

procedure TBrCustomDBGrid.TopLeftChanged;
begin
  InvalidateTitles;
  inherited TopLeftChanged;
end;

// =-=-= Bravo inicio TCustomDbGrid
procedure TBrCustomDBGrid.RowExit;
begin
      if Assigned(FOnRowExit) then FOnRowExit(Self);
end;

function TBrCustomDBGrid.EncontrarTextoPickList(Column : TColumn) : String;
var x        : Integer;
begin
      x      := 0;
      Result := '';

      while (x < Column.BrPickValue.Count) and (Result = '') do
      begin
            if Column.Field.Text = Column.BrPickValue.Strings[x] then
            begin
                  Result := Column.PickList.Strings[x];
            end;

            inc(x);
      end;

      if  (Result = '') and (Column <> nil) then
      begin
            Result := Column.Field.Text;
      end;
end;

procedure TBrCustomDBGrid.ProcessarMemo(const Rect: TRect; Column: TColumn);
var Loc : TRect;
    x   : Integer;
begin
      Loc := Rect;

      if FSnDisMem then
      begin
            try
                Inc(Loc.Top, 2);
                Inc(Loc.Left, 2);
                DrawText(Canvas.Handle, PChar(Column.Field.AsString),
                         Length(Column.Field.AsString), Loc,
                         DT_WORDBREAK or DT_NOPREFIX);
            except
                   SetTextAlign(Canvas.Handle, TA_CENTER);
                   x := (Rect.Right + Rect.Left) div 2;
                   Canvas.TextRect(Rect, x, Rect.Top + 2,
                                                      Column.Field.DisplayText);

                   SetTextAlign(Canvas.Handle, TA_LEFT);
            end;
      end else
      begin
            SetTextAlign(Canvas.Handle, TA_CENTER);
            x := (Rect.Right + Rect.Left) div 2;
            Canvas.TextRect(Rect, x, Rect.Top + 2, Column.Field.DisplayText);

            SetTextAlign(Canvas.Handle, TA_LEFT);
      end;
end;

procedure TBrCustomDBGrid.ProcessarString(const Rect: TRect; Column: TColumn);
var Loc  : TRect;
    Style: UINT;
begin
      if (Column.gVlChecke <> '') and (Column.gVlUnChec <> '') then
      begin
            Loc        := Rect;
            Loc.Left   := Rect.Left + (Rect.Right - Rect.Left - FTmWidChe)
                                                                          div 2;
            Loc.Right  := Loc.Left  + FTmWidChe;
            Loc.Top    := Rect.Top  + (Rect.Bottom - Rect.Top - FTmWidChe)
                                                                          div 2;
            Loc.Bottom := Loc.Top   + FTmHeiChe;

            if Column.Field.AsString = Column.gVlChecke then
            begin
                  Style := DFCS_CHECKED;
            end else
            begin
                 if Column.Field.AsString = Column.gVlUnChec then
                 begin
                       Style := DFCS_BUTTONCHECK;
                 end else
                 begin
                       if Column.gVlHalChe then
                       begin
                             Style := DFCS_BUTTON3STATE or DFCS_CHECKED;
                       end else
                       begin
                             Style := DFCS_BUTTONCHECK;
                       end;
                 end;
            end;

            DrawFrameControl(Canvas.Handle, Loc, DFC_BUTTON, style);
      end else
      begin
            ProcessarOutrosCampos(Rect, Column);
      end;
end;

procedure TBrCustomDBGrid.ProcessarOutrosCampos(const Rect: TRect;
                                                              Column: TColumn);
var vAlignment: TAlignment;
    x         : Integer;
begin
      x          := 0;
      vAlignment := Column.Field.Alignment;

      case vAlignment of
           taRightJustify: begin
                                 SetTextAlign(Canvas.Handle, TA_RIGHT);
                                 x := Rect.Right - 2;
                           end;
           taLeftJustify:  begin
                                 SetTextAlign(Canvas.Handle, TA_LEFT);
                                 x := Rect.Left + 2;
                           end;
           taCenter:       begin
                                 SetTextAlign(Canvas.Handle, TA_CENTER);
                                 x := (Rect.Right + Rect.Left) div 2;
                           end;
      end;

      Canvas.TextRect(Rect, x, Rect.Top + 2, Column.Field.DisplayText);
      SetTextAlign(Canvas.Handle, TA_LEFT);
end;

procedure TBrCustomDBGrid.MostrarMemo(Column : TColumn);
var FrmMemo   : TForm;
    PnlFundo  : TPanel;
    PnlRodape : TPanel;
    DbMemo    : TMemo;
    BbtOk     : TBrvBitBtn;
    BbtCancel : TBrvBitBtn;
begin
      FrmMemo                 := TForm.CreateNew(Self);
      FrmMemo.Position        := poScreenCenter;
      FrmMemo.Width           := 550;
      FrmMemo.Height          := 340;
      FrmMemo.BorderStyle     := bsSizeable;
      FrmMemo.BorderIcons     := [biSystemMenu,biMaximize];
      FrmMemo.Caption         := Column.Field.DisplayLabel;

      PnlFundo                := TPanel.Create(Self);
      PnlFundo.Parent         := FrmMemo;
      PnlFundo.Align          := alClient;
      PnlFundo.BorderStyle    := bsSingle;
      PnlFundo.Caption        := '';

      PnlRodape               := TPanel.Create(Self);
      PnlRodape.Parent        := PnlFundo;
      PnlRodape.Align         := alBottom;
      PnlRodape.BorderStyle   := bsSingle;
      PnlRodape.Caption       := '';

      BbtOk                   := TBrvBitBtn.Create(Self);
      BbtOk.Parent            := PnlRodape;
      BbtOk.Top               := 5;
      BbtOk.Left              := 5;
      BbtOk.BrTipoBotao       := BrBtnOk;
      BbtOk.Caption           := '&Ok';
      BbtOk.ModalResult       := mrOk;

      BbtCancel               := TBrvBitBtn.Create(Self);
      BbtCancel.Parent        := PnlRodape;
      BbtCancel.Top           := 5;
      BbtCancel.Left          := 5 + BbtOk.Width + 5;
      BbtCancel.BrTipoBotao   := BrBtnCancel;
      BbtCancel.Caption       := '&Cancelar';
      BbtCancel.ModalResult   := mrCancel;

      DbMemo                  := TMemo.Create(Self);
      DbMemo.Parent           := PnlFundo;
      DbMemo.Align            := alClient;
      DbMemo.Lines.Text       := DataSource.DataSet.FieldByName(Column.FieldName).AsString;
      DbMemo.ReadOnly         := Column.Field.ReadOnly;
      DbMemo.ScrollBars       := ssBoth;
      DbMemo.TabOrder         := 0;
      DbMemo.Font.Name        := 'Courier New';
      DbMemo.OnKeyPress       := MemoKeyPress;

      if (Column.Field is TBrMemoField) then
      begin
            case (Column.Field as TBrMemoField).BrCharCase of
                  BrSfUpperCase : DbMemo.Tag := 1;
                  BrSfLowerCase : DbMemo.Tag := 2;
            end;
      end;


      DbMemo.ReadOnly := ReadOnly or Column.ReadOnly or Column.Field.ReadOnly;

      if  FrmMemo.ShowModal = MrOk then
      begin
           if  not DbMemo.ReadOnly then
           begin
                 if not (DataSource.State in [DsEdit, DsInsert]) then
                 begin
                       DataSource.DataSet.Edit;
                 end;

                 DataSource.DataSet.FieldByName(Column.FieldName).AsString :=
                                                              DbMemo.LInes.Text;
           end;
      end;

      FrmMemo.Destroy;
end;

procedure TBrCustomDBGrid.MemoKeyPress(Sender : TObject; var Key : Char);
begin
      case (Sender as TMemo).Tag of
           1 : key := BrUpCase(key);
           2 : key := BrLwCase(key);
      end;
end;

procedure TBrCustomDBGrid.AlterarString(Column : TColumn; Click : Boolean);
begin
      if (Column.gVlChecke <> '') and (Column.gVlUnChec <> '') and Click then
      begin
             if not (DataSource.State in [DsEdit, DsInsert]) then
             begin
                   DataSource.DataSet.Edit;
             end;

             if not Column.ReadOnly then
             begin
                   if Assigned(Column.gOnBefChe) then
                   begin
                         Column.gOnBefChe(Self, Column);
                   end;

                   if Column.Field.AsString = Column.gVlChecke then
                   begin
                         Column.Field.AsString := Column.gVlUnChec;
                   end else
                   begin
                         Column.Field.AsString := Column.gVlChecke;
                   end;

                   if Assigned(Column.gOnAftChe) then
                   begin
                         Column.gOnAftChe(Self);
                   end;
             end;
      end;
end;

// =-=-= Bravo
constructor TBrDbGrids.Create(AOwner: TComponent);
begin
    inherited Create(Aowner);
    Screen.Cursors[crCheck] := LoadCursor(HInstance, 'BRDBCHECK');
    Screen.Cursors[crPen]   := LoadCursor(HInstance, 'BRDBPEN');
    BrGradeZebrada          := True;
end;

destructor TBrDbGrids.Destroy;
begin
     inherited Destroy;
end;

procedure TBrDbGrids.MouseMove(Shift: TShiftState; X, Y: Integer);
var Coord    : TGridCoord;
    NrColuna : Integer;
begin
      Coord    := MouseCoord(X, Y);

      if  Coord.Y > 0 then  // Número da linha
      begin
            NrColuna := Coord.X - 1;

            if  NrColuna > 0 then
            begin
                  DefinirCursor(NrColuna);
            end;
      end;

      inherited MouseMove(Shift, X, Y);
end;

procedure TBrDbGrids.DefinirCursor(NrColuna : Integer);
begin
      if (DataSource.DataSet <> nil) and (Columns[NrColuna].Field <> Nil) then
      begin
            if (DataSource.DataSet.Active) then
            begin
                  case Columns[NrColuna].Field.DataType of
                    ftBoolean : Cursor := crDefault;
      //            ftBoolean : Cursor := crCheck;
                    ftString,
                    ftWideString: begin
                                      if  Columns[NrColuna].BrValueChecked <> '' then
                                      begin
                                            Cursor := crDefault
      //                                    Cursor := crCheck;
                                      end else
                                      begin
                                            Cursor := crDefault;
                                      end;
                                end;
                    ftMemo,
                    ftOraClob : Cursor := crPen;
                 else
                    Cursor := crDefault;
                 end;
            end;
      end;
end;

procedure  TBrDbGrids.ValidarColuna(pNoColuna : Integer);
begin
      if (pNoColuna >= 0) and (pNoColuna < ColCount)  then
      begin
            SelectedIndex := pNoColuna;
            ValidarEntrada;
      end;
end;

procedure TBrDbGrids.ValidarEntrada;
var lBrConsul : TBrvConsulta;
begin
      if  (not Columns[SelectedIndex].ReadOnly) and
          (DataSource.DataSet.State in [dsEdit, dsInsert]) then
      begin
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-  Executando OnBeforeConsulta =-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if Assigned(Columns[SelectedIndex].BrOnBeforeConsul) then
            begin
                  Columns[SelectedIndex].BrOnBeforeConsul(Columns[SelectedIndex]);
            end;

         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-  Executando a consulta  -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            try
                lBrConsul := TBrvConsulta.Create(Owner);
                lBrConsul.BrQueryCode        := Columns[SelectedIndex].BrConsulta;
                lBrConsul.BrDataSource       := DataSource;
                lBrConsul.BrDicionario       := gDiciona;
                lBrConsul.BrConfigurar.Text  := Columns[SelectedIndex].BrConfigurar.Text;
                lBrConsul.BrParams.Text      := Columns[SelectedIndex].BrParams.Text;
                lBrConsul.BrValidarEntrada;
            finally
                FreeAndNil(lBrConsul);
            end;
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-  Executando AfterConsulta  =-=-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if Assigned(Columns[SelectedIndex].BrOnAfterConsul) then
            begin
                  Columns[SelectedIndex].BrOnAfterConsul(Columns[SelectedIndex]);
            end;
      end;
end;

procedure TBrDbGrids.ColExit;
begin
      if Columns[SelectedIndex].BrConsulta <> 0 then
      begin
            ValidarEntrada;
      end;

      inherited ColExit;
end;

procedure TBrDbGrids.RowExit;
begin
      if Columns[SelectedIndex].BrConsulta <> 0 then
      begin
            FDatalink.UpdateData; // validate before moving
            HideEditor;
            ValidarEntrada;
      end;

      inherited RowExit;
end;

procedure TBrDbGrids.BrSetDrawColumn(const Value: TStrings);
begin
      gStlDraCol.Assign(Value);
end;

// =-=-=-=-=-=-=-=-=-=-=-=-=-=-= Bravo
// =-=-=-=-= Funcões para maiúscula e minúscula
// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
function BrLwCase(pDsCaract : Char) : Char;
begin
      case pDsCaract of
           'Á' : Result := 'á';
           'É' : Result := 'é';
           'Í' : Result := 'í';
           'Ó' : Result := 'ó';
           'Ú' : Result := 'ú';
           'Ã' : Result := 'ã';
           'Õ' : Result := 'õ';
           'Â' : Result := 'â';
           'Ê' : Result := 'ê';
           'Î' : Result := 'î';
           'Ô' : Result := 'ô';
           'Û' : Result := 'û';
           'Ä' : Result := 'ä';
           'Ë' : Result := 'ë';
           'Ï' : Result := 'ï';
           'Ö' : Result := 'ö';
           'Ü' : Result := 'ü';
           'À' : Result := 'à';
           'È' : Result := 'è';
           'Ì' : Result := 'ì';
           'Ò' : Result := 'ò';
           'Ù' : Result := 'ù';
           'Ç' : Result := 'ç';
           else  Result := LowerCase(pDsCaract + ' ')[1];
      end;
end;

function BrUpCase(pDsCaract : Char) : Char;
begin
      case pDsCaract of
           'á' : Result := 'Á';
           'é' : Result := 'É';
           'í' : Result := 'Í';
           'ó' : Result := 'Ó';
           'ú' : Result := 'Ú';
           'ã' : Result := 'Ã';
           'õ' : Result := 'Õ';
           'â' : Result := 'Â';
           'ê' : Result := 'Ê';
           'î' : Result := 'Î';
           'ô' : Result := 'Ô';
           'û' : Result := 'Û';
           'ä' : Result := 'Ä';
           'ë' : Result := 'Ë';
           'ï' : Result := 'Ï';
           'ö' : Result := 'Ö';
           'ü' : Result := 'Ü';
           'à' : Result := 'À';
           'è' : Result := 'È';
           'ì' : Result := 'Ì';
           'ò' : Result := 'Ò';
           'ù' : Result := 'Ù';
           'ç' : Result := 'Ç';
           else  Result := UpperCase(pDsCaract + ' ')[1];
      end;
end;

end.

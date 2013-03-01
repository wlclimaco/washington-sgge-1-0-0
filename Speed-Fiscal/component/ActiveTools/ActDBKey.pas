unit ActDBKey;

interface

uses
  Windows, Messages, SysUtils, Classes, DB, DBCtrls, ActKeyResult;

type
  TActDBKey = class(TComponent)
  private
    FDataLink: TFieldDataLink;
    FKeyResult: TActKeyResult;
    function GetDataField: string;
    procedure SetDataField(const Value: string);
    function GetDataSource: TDataSource;
    procedure SetDataSource(const Value: TDataSource);
    procedure SetKeyResult(const Value: TActKeyResult);
    { Private declarations }
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
    procedure Update;
  published
    { Published declarations }
    property DataField: string read GetDataField write SetDataField;
    property DataSource: TDataSource read GetDataSource write SetDataSource;
    property KeyResult: TActKeyResult read FKeyResult write SetKeyResult;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Active Controls', [TActDBKey]);
end;

{ TActDBKey }

constructor TActDBKey.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
  FDataLink := TFieldDataLink.Create;
  FDataLink.Control := Self;
end;

destructor TActDBKey.Destroy;
begin
  FDataLink.Free;
  inherited Destroy;
end;

function TActDBKey.GetDataField: string;
begin
  Result := FDataLink.FieldName;
end;

function TActDBKey.GetDataSource: TDataSource;
begin
  Result := FDataLink.DataSource;
end;

procedure TActDBKey.SetDataField(const Value: string);
begin
  if Value <> FDataLink.FieldName then
    FDataLink.FieldName := Value;
end;

procedure TActDBKey.SetDataSource(const Value: TDataSource);
begin
  if not (FDataLink.DataSourceFixed and (csLoading in ComponentState)) then
    FDataLink.DataSource := Value;
  if Value <> nil then
    Value.FreeNotification(Self)
end;

procedure TActDBKey.SetKeyResult(const Value: TActKeyResult);
begin
  FKeyResult := Value;
end;

procedure TActDBKey.Update;
begin
  if (Assigned(FDataLink)) and (FDataLink.Active) then
    KeyResult.Value := FDataLink.Field.AsString;
end;

end.

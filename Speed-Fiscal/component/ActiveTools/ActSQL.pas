unit ActSQL;

interface

uses
  SysUtils, Classes;

type
  TActSQL = class(TComponent)
  private
    FTable: String;
    FSQL: TStrings;
    FField: String;
    FKey: String;
    FSetLen: Integer;
    procedure SetTable(const Value: String);
    procedure SetSetLen(const Value: Integer);
    { Private declarations }
  protected
    { Protected declarations }
  public
    { Public declarations }
    Fields, Values: array of String;
    procedure Select;
    procedure Insert;
    procedure Update;
    procedure Delete;
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
  published
    { Published declarations }
    property SQL: TStrings read FSQL write FSQL;
    property Table: String read FTable write SetTable;
    property Field: String read FField write FField;
    property Key: String read FKey write FKey;
    property SetLen: Integer read FSetLen write SetSetLen;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Active Controls', [TActSQL]);
end;

{ TActSQL }

constructor TActSQL.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
  FSQL := TStringList.Create;
end;

procedure TActSQL.Delete;
begin
  FSQL.Clear;
  FSQL.Add('delete from ' + FTable + ' where ' + FField + '=' + FKey);
end;

destructor TActSQL.Destroy;
begin
  FSQL.Free;
  inherited Destroy;
end;

procedure TActSQL.Insert;
var
  SQLFields: String;
  SQLValues: String;
  Max, I: Integer;
begin
  SQLFields := '';
  SQLValues := '';
  Max := Length(Fields) - 1;

  for I := 0 to Max do
  begin
    if I < Max then
    begin
      SQLFields := SQLFields + Fields[I] + ', ';
      SQLValues := SQLValues + Values[I] + ', ';
    end
    else
    begin
      SQLFields := SQLFields + Fields[I];
      SQLValues := SQLValues + Values[I];
    end;
  end;
  FSQL.Clear;
  FSQL.Add('insert into ' + FTable + '(' + SQLFields + ')' + ' values(' + SQLValues + ')');
end;

procedure TActSQL.Select;
var
  Max, I: Integer;
begin
  Max := Length(Fields) - 1;

  FSQL.Clear;
  FSQL.Add('select ' + FField + ', ');

  for I := 0 to Max do
  begin
    if I < Max then
      FSQL.Add(Fields[I] + ', ')
    else
      FSQL.Add(Fields[I]);
  end;
  FSQL.Add(' from ' + FTable);
  FSQL.Add(' where ' + FField + '=' + FKey);
end;

procedure TActSQL.SetSetLen(const Value: Integer);
begin
  if Value <> FSetLen then
  begin
    FSetLen := Value;
    SetLength(Fields, FSetLen);
    SetLength(Values, FSetLen);
  end;
end;

procedure TActSQL.SetTable(const Value: String);
begin
  if Value <> FTable then
    FTable := Value;
end;

procedure TActSQL.Update;
var
  Max, I: Integer;
begin
  Max := Length(Fields) - 1;

  FSQL.Clear;
  FSQL.Add('update ' + FTable + ' set ');

  for I := 0 to Max do
  begin
    if I < Max then
      FSQL.Add(Fields[I] + '=' + Values[I] + ', ')
    else
      FSQL.Add(Fields[I] + '=' + Values[I]);
  end;
  FSQL.Add(' where ' + FField + '=' + FKey);
end;

end.

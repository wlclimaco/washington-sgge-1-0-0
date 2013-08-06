unit BrvConnection;

interface

uses
  SysUtils, Classes, DB, SqlExpr, BrvOracle, BrvInterbase;

type
  TBrvConnection = class(TSQLConnection)
  private
    function TraduzirInstrucaoSQL(SQL : String) : String;
    { Private declarations }
  protected
    procedure DoConnect; override;
    { Protected declarations }
  public
    function ExecuteSQL(SQL: string; Params: TParams=nil; Cursor: Pointer=nil): Integer;
    constructor Create(AOwner : TComponent); override;
    destructor  Destroy; override;
    { Public declarations }
  published
    { Published declarations }
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Bravo Banco', [TBrvConnection]);
end;

constructor TBrvConnection.Create(AOwner : TComponent);
begin
      inherited Create(AOwner);
end;

destructor TBrvConnection.Destroy;
begin
      inherited  destroy;
end;

function TBrvConnection.ExecuteSQL(SQL: string; Params: TParams = nil;
                                                          Cursor: Pointer = nil): Integer;
begin
      SQL    := TraduzirInstrucaoSQL(SQL);
      Result := Execute(SQL, Params, Cursor);
end;

function TBrvConnection.TraduzirInstrucaoSQL(SQL : String) : String;
var DsOracle : TBrvOracle;
    DsInterb : TBrvInterbase;
begin
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=- Traduzindo a select para o banco atual =-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      if  DriverName = 'ORACLE' then
      begin
            DsOracle := TBrvOracle.Create(Self);
            Result   := DsOracle.SintaxeOracle(SQL);
            DsOracle.Destroy;
      end else
      begin // 'INTRBASE'
            DsInterb := TBrvInterbase.Create(Self);
            Result   := DsInterb.SintaxeInterbase(SQL);
            DsInterb.Destroy;
      end;
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
end;

procedure TBrvConnection.DoConnect;
begin
      inherited DoConnect;
      
      if UpperCase(DriverName) = 'ORACLE' then
      begin
            ExecuteDirect('alter session set nls_numeric_characters = ''.,''');
      end;
end;

end.


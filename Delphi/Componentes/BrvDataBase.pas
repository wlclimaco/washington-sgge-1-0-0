unit BrvDataBase;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  Db, DBTables, Bde, BrvOracle, BrvInterbase;

type
  TBrvDatabase = class(TDatabase)
  private
    { Private declarations }
    function TraduzirInstrucaoSQL(SQL : String) : String;
  protected
    { Protected declarations }
  public
    { Public declarations }
    function ExecuteSQL(SQL: string; Params: TParams = nil;
             Cache: Boolean = False; Cursor: phDBICur = nil): Integer;
    constructor Create(AOwner : TComponent); override;
    destructor  Destroy;                     override;
  published
    { Published declarations }
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Bravo Banco', [TBrvDatabase]);
end;

constructor TBrvDatabase.Create(AOwner : TComponent);
begin
      inherited Create(AOwner);
end;

destructor TBrvDatabase.Destroy;
begin
      inherited  destroy;
end;

function TBrvDatabase.ExecuteSQL(SQL: string; Params: TParams = nil;
  Cache: Boolean = False; Cursor: phDBICur = nil): Integer;
begin
      SQL    := TraduzirInstrucaoSQL(SQL);
      Result := Execute(SQL, Params, Cache, Cursor);
end;

function TBrvDatabase.TraduzirInstrucaoSQL(SQL : String) : String;
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

end.


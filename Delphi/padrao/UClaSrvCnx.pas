//
// Created by the DataSnap proxy generator.
// 25/2/2013 17:47:20
//

unit UClaSrvCnx;

interface

uses DBXCommon, DBXClient, DBXJSON, DSProxy, Classes, SysUtils, DB, SqlExpr, DBXDBReaders, DBXJSONReflect;

type
  TDSMCnxClient = class(TDSAdminClient)
  private
    FExecuteCommandCommand: TDBXCommand;
  public
    constructor Create(ADBXConnection: TDBXConnection); overload;
    constructor Create(ADBXConnection: TDBXConnection; AInstanceOwner: Boolean); overload;
    destructor Destroy; override;

    function ExecuteCommand(pNmComan: String; pXmlData : String): AnsiString;
  end;

implementation

function TDSMCnxClient.ExecuteCommand(pNmComan: String; pXmlData: String): AnsiString;
begin
      if FExecuteCommandCommand = nil then
      begin
            FExecuteCommandCommand := FDBXConnection.CreateCommand;
            FExecuteCommandCommand.CommandType := TDBXCommandTypes.DSServerMethod;
            FExecuteCommandCommand.Text := 'TDSMCnx.ExecuteCommand';
            FExecuteCommandCommand.Prepare;
      end;

      FExecuteCommandCommand.Parameters[0].Value.SetWideString(pNmComan);
      FExecuteCommandCommand.Parameters[1].Value.SetWideString(pXmlData);
      FExecuteCommandCommand.ExecuteUpdate;

      Result := FExecuteCommandCommand.Parameters[2].Value.GetAnsiString;
end;

constructor TDSMCnxClient.Create(ADBXConnection: TDBXConnection);
begin
  inherited Create(ADBXConnection);
end;

constructor TDSMCnxClient.Create(ADBXConnection: TDBXConnection; AInstanceOwner: Boolean);
begin
  inherited Create(ADBXConnection, AInstanceOwner);
end;

destructor TDSMCnxClient.Destroy;
begin
      FreeAndNil(FExecuteCommandCommand);
      inherited;
end;

end.

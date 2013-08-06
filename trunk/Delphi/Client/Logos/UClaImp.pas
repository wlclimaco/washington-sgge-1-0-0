//
// Created by the DataSnap proxy generator.
// 16/8/2011 07:46:06
//
unit UClaImp;

interface

uses DBXCommon, DBXClient, DBXJSON, DSProxy, Classes, SysUtils, DB, SqlExpr, DBXDBReaders, DBXJSONReflect;

type
  TSDmImpClient = class(TDSAdminClient)
  private
    FSqlConnImpAfterConnectCommand: TDBXCommand;
    FGerarRelatorioCommand: TDBXCommand;
  public
    constructor Create(ADBXConnection: TDBXConnection); overload;
    constructor Create(ADBXConnection: TDBXConnection; AInstanceOwner: Boolean); overload;
    destructor Destroy; override;
    procedure SqlConnImpAfterConnect(Sender: TObject);
    function GerarRelatorio(pDsCreden: string; var pDsResult: string; pDsParams: OleVariant; pData: OleVariant): OleVariant;
  end;

  TSDmRWClient = class(TDSAdminClient)
  private
    FSqlConnRWAfterConnectCommand: TDBXCommand;
    FEntradaClienteCommand: TDBXCommand;
    FClienteAutenticadoCommand: TDBXCommand;
    FRetornaQuantidadeRegistroTabelaCommand: TDBXCommand;
    FRetornaDadosSqlCadastroCommand: TDBXCommand;
    FRetornaDadosSqlFixaCommand: TDBXCommand;
    FAutenticarUsuarioCommand: TDBXCommand;
    FAtualizarSenhaUsuarioCommand: TDBXCommand;
    FExecutarInstrucaoSqlCommand: TDBXCommand;
    FChavePrimariaCommand: TDBXCommand;
    FProxNumeroSequencialCommand: TDBXCommand;
    FDataHoraServerCommand: TDBXCommand;
    FValorDefaultCommand: TDBXCommand;
    FEncontrarInstrucaoSQLConsultaCommand: TDBXCommand;
    FEncontrarInstrucaoSQLCommand: TDBXCommand;
    FTipoBancoDadosCommand: TDBXCommand;
    FAtualizarDicionarioDadosCommand: TDBXCommand;
    FVerificarNovaVersaoClienteCommand: TDBXCommand;
    FParametroDaEmpresaCommand: TDBXCommand;
    FGravarTelasAbertaUsuarioCommand: TDBXCommand;
  public
    constructor Create(ADBXConnection: TDBXConnection); overload;
    constructor Create(ADBXConnection: TDBXConnection; AInstanceOwner: Boolean); overload;
    destructor Destroy; override;
    procedure SqlConnRWAfterConnect(Sender: TObject);
    function EntradaCliente(pNoIp: string; pNmComput: string; pNrConTCP: string; pDsCreden: string): string;
    procedure ClienteAutenticado(pDsCreden: string; var pDsResult: string; pCdUsuari: Integer; pDsLogin: string; pNrConTcp: string);
    function RetornaQuantidadeRegistroTabela(pDsCreden: string; var pDsResult: string; pNmTabela: string): Integer;
    function RetornaDadosSqlCadastro(pDsCreden: string; var pDsResult: string; pNrSql: Integer; pDsParams: string; pNmFormul: string): OleVariant;
    function RetornaDadosSqlFixa(pDsCreden: string; var pDsResult: string; pDsSql: string): OleVariant;
    procedure AutenticarUsuario(pDsCreden: string; var pDsResult: string; pDsLogin: string; var pCdSenha: Integer; var pCdUsuari: Integer; var pNmUsuari: string);
    procedure AtualizarSenhaUsuario(pDsCreden: string; var pDsResult: string; pDsLogin: string; pNrSenha: Integer);
    function ExecutarInstrucaoSql(pDsCreden: string; var pDsResult: string; pDsSql: string): Integer;
    procedure ChavePrimaria(pDsCreden: string; var pDsResult: string; pNmTabela: string; var pStlChave: string);
    function ProxNumeroSequencial(pDsCreden: string; var pDsResult: string; pNmTabela: string; pNmAtribu: string): Integer;
    procedure DataHoraServer(pDsCreden: string; var pDsResult: string; var pDtDatHor: TDateTime);
    procedure ValorDefault(pDsCreden: string; var pDsResult: string; var pVrDefaul: string);
    procedure EncontrarInstrucaoSQLConsulta(pDsCreden: string; var pDsResult: string; pNrQueCon: Integer; var pDsSql: string);
    procedure EncontrarInstrucaoSQL(pDsCreden: string; var pDsResult: string; pNrQuery: Integer; var pDsSql: string; pNmFormul: string);
    function TipoBancoDados(pDsCreden: string; var pDsResult: string; var pDtForBco: string; var pNrSrVers: Integer; var pNrSrVeSu: Integer): OleVariant;
    procedure AtualizarDicionarioDados(pDsCreden: string; var pDsResult: string);
    function VerificarNovaVersaoCliente(pDsCreden: string; var pDsResult: string; pCdsClient: OleVariant): OleVariant;
    function ParametroDaEmpresa(pDsCreden: string; var pDsResult: string; pNrParame: Integer; pCdEmpres: Integer): OleVariant;
    procedure GravarTelasAbertaUsuario(pDsCreden: string; var pDsResult: string; pCdsTelas: OleVariant);
  end;

implementation

procedure TSDmImpClient.SqlConnImpAfterConnect(Sender: TObject);
begin
  if FSqlConnImpAfterConnectCommand = nil then
  begin
    FSqlConnImpAfterConnectCommand := FDBXConnection.CreateCommand;
    FSqlConnImpAfterConnectCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FSqlConnImpAfterConnectCommand.Text := 'TSDmImp.SqlConnImpAfterConnect';
    FSqlConnImpAfterConnectCommand.Prepare;
  end;
  if not Assigned(Sender) then
    FSqlConnImpAfterConnectCommand.Parameters[0].Value.SetNull
  else
  begin
    FMarshal := TDBXClientCommand(FSqlConnImpAfterConnectCommand.Parameters[0].ConnectionHandler).GetJSONMarshaler;
    try
      FSqlConnImpAfterConnectCommand.Parameters[0].Value.SetJSONValue(FMarshal.Marshal(Sender), True);
      if FInstanceOwner then
        Sender.Free
    finally
      FreeAndNil(FMarshal)
    end
    end;
  FSqlConnImpAfterConnectCommand.ExecuteUpdate;
end;

function TSDmImpClient.GerarRelatorio(pDsCreden: string; var pDsResult: string; pDsParams: OleVariant; pData: OleVariant): OleVariant;
begin
  if FGerarRelatorioCommand = nil then
  begin
    FGerarRelatorioCommand := FDBXConnection.CreateCommand;
    FGerarRelatorioCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FGerarRelatorioCommand.Text := 'TSDmImp.GerarRelatorio';
    FGerarRelatorioCommand.Prepare;
  end;
  FGerarRelatorioCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FGerarRelatorioCommand.Parameters[1].Value.SetWideString(pDsResult);
  FGerarRelatorioCommand.Parameters[2].Value.AsVariant := pDsParams;
  FGerarRelatorioCommand.Parameters[3].Value.AsVariant := pData;
  FGerarRelatorioCommand.ExecuteUpdate;
  pDsResult := FGerarRelatorioCommand.Parameters[1].Value.GetWideString;
  Result := FGerarRelatorioCommand.Parameters[4].Value.AsVariant;
end;


constructor TSDmImpClient.Create(ADBXConnection: TDBXConnection);
begin
  inherited Create(ADBXConnection);
end;


constructor TSDmImpClient.Create(ADBXConnection: TDBXConnection; AInstanceOwner: Boolean);
begin
  inherited Create(ADBXConnection, AInstanceOwner);
end;


destructor TSDmImpClient.Destroy;
begin
  FreeAndNil(FSqlConnImpAfterConnectCommand);
  FreeAndNil(FGerarRelatorioCommand);
  inherited;
end;

procedure TSDmRWClient.SqlConnRWAfterConnect(Sender: TObject);
begin
  if FSqlConnRWAfterConnectCommand = nil then
  begin
    FSqlConnRWAfterConnectCommand := FDBXConnection.CreateCommand;
    FSqlConnRWAfterConnectCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FSqlConnRWAfterConnectCommand.Text := 'TSDmRW.SqlConnRWAfterConnect';
    FSqlConnRWAfterConnectCommand.Prepare;
  end;
  if not Assigned(Sender) then
    FSqlConnRWAfterConnectCommand.Parameters[0].Value.SetNull
  else
  begin
    FMarshal := TDBXClientCommand(FSqlConnRWAfterConnectCommand.Parameters[0].ConnectionHandler).GetJSONMarshaler;
    try
      FSqlConnRWAfterConnectCommand.Parameters[0].Value.SetJSONValue(FMarshal.Marshal(Sender), True);
      if FInstanceOwner then
        Sender.Free
    finally
      FreeAndNil(FMarshal)
    end
    end;
  FSqlConnRWAfterConnectCommand.ExecuteUpdate;
end;

function TSDmRWClient.EntradaCliente(pNoIp: string; pNmComput: string; pNrConTCP: string; pDsCreden: string): string;
begin
  if FEntradaClienteCommand = nil then
  begin
    FEntradaClienteCommand := FDBXConnection.CreateCommand;
    FEntradaClienteCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FEntradaClienteCommand.Text := 'TSDmRW.EntradaCliente';
    FEntradaClienteCommand.Prepare;
  end;
  FEntradaClienteCommand.Parameters[0].Value.SetWideString(pNoIp);
  FEntradaClienteCommand.Parameters[1].Value.SetWideString(pNmComput);
  FEntradaClienteCommand.Parameters[2].Value.SetWideString(pNrConTCP);
  FEntradaClienteCommand.Parameters[3].Value.SetWideString(pDsCreden);
  FEntradaClienteCommand.ExecuteUpdate;
  Result := FEntradaClienteCommand.Parameters[4].Value.GetWideString;
end;

procedure TSDmRWClient.ClienteAutenticado(pDsCreden: string; var pDsResult: string; pCdUsuari: Integer; pDsLogin: string; pNrConTcp: string);
begin
  if FClienteAutenticadoCommand = nil then
  begin
    FClienteAutenticadoCommand := FDBXConnection.CreateCommand;
    FClienteAutenticadoCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FClienteAutenticadoCommand.Text := 'TSDmRW.ClienteAutenticado';
    FClienteAutenticadoCommand.Prepare;
  end;
  FClienteAutenticadoCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FClienteAutenticadoCommand.Parameters[1].Value.SetWideString(pDsResult);
  FClienteAutenticadoCommand.Parameters[2].Value.SetInt32(pCdUsuari);
  FClienteAutenticadoCommand.Parameters[3].Value.SetWideString(pDsLogin);
  FClienteAutenticadoCommand.Parameters[4].Value.SetWideString(pNrConTcp);
  FClienteAutenticadoCommand.ExecuteUpdate;
  pDsResult := FClienteAutenticadoCommand.Parameters[1].Value.GetWideString;
end;

function TSDmRWClient.RetornaQuantidadeRegistroTabela(pDsCreden: string; var pDsResult: string; pNmTabela: string): Integer;
begin
  if FRetornaQuantidadeRegistroTabelaCommand = nil then
  begin
    FRetornaQuantidadeRegistroTabelaCommand := FDBXConnection.CreateCommand;
    FRetornaQuantidadeRegistroTabelaCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FRetornaQuantidadeRegistroTabelaCommand.Text := 'TSDmRW.RetornaQuantidadeRegistroTabela';
    FRetornaQuantidadeRegistroTabelaCommand.Prepare;
  end;
  FRetornaQuantidadeRegistroTabelaCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FRetornaQuantidadeRegistroTabelaCommand.Parameters[1].Value.SetWideString(pDsResult);
  FRetornaQuantidadeRegistroTabelaCommand.Parameters[2].Value.SetWideString(pNmTabela);
  FRetornaQuantidadeRegistroTabelaCommand.ExecuteUpdate;
  pDsResult := FRetornaQuantidadeRegistroTabelaCommand.Parameters[1].Value.GetWideString;
  Result := FRetornaQuantidadeRegistroTabelaCommand.Parameters[3].Value.GetInt32;
end;

function TSDmRWClient.RetornaDadosSqlCadastro(pDsCreden: string; var pDsResult: string; pNrSql: Integer; pDsParams: string; pNmFormul: string): OleVariant;
begin
  if FRetornaDadosSqlCadastroCommand = nil then
  begin
    FRetornaDadosSqlCadastroCommand := FDBXConnection.CreateCommand;
    FRetornaDadosSqlCadastroCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FRetornaDadosSqlCadastroCommand.Text := 'TSDmRW.RetornaDadosSqlCadastro';
    FRetornaDadosSqlCadastroCommand.Prepare;
  end;
  FRetornaDadosSqlCadastroCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FRetornaDadosSqlCadastroCommand.Parameters[1].Value.SetWideString(pDsResult);
  FRetornaDadosSqlCadastroCommand.Parameters[2].Value.SetInt32(pNrSql);
  FRetornaDadosSqlCadastroCommand.Parameters[3].Value.SetWideString(pDsParams);
  FRetornaDadosSqlCadastroCommand.Parameters[4].Value.SetWideString(pNmFormul);
  FRetornaDadosSqlCadastroCommand.ExecuteUpdate;
  pDsResult := FRetornaDadosSqlCadastroCommand.Parameters[1].Value.GetWideString;
  Result := FRetornaDadosSqlCadastroCommand.Parameters[5].Value.AsVariant;
end;

function TSDmRWClient.RetornaDadosSqlFixa(pDsCreden: string; var pDsResult: string; pDsSql: string): OleVariant;
begin
  if FRetornaDadosSqlFixaCommand = nil then
  begin
    FRetornaDadosSqlFixaCommand := FDBXConnection.CreateCommand;
    FRetornaDadosSqlFixaCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FRetornaDadosSqlFixaCommand.Text := 'TSDmRW.RetornaDadosSqlFixa';
    FRetornaDadosSqlFixaCommand.Prepare;
  end;
  FRetornaDadosSqlFixaCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FRetornaDadosSqlFixaCommand.Parameters[1].Value.SetWideString(pDsResult);
  FRetornaDadosSqlFixaCommand.Parameters[2].Value.SetWideString(pDsSql);
  FRetornaDadosSqlFixaCommand.ExecuteUpdate;
  pDsResult := FRetornaDadosSqlFixaCommand.Parameters[1].Value.GetWideString;
  Result := FRetornaDadosSqlFixaCommand.Parameters[3].Value.AsVariant;
end;

procedure TSDmRWClient.AutenticarUsuario(pDsCreden: string; var pDsResult: string; pDsLogin: string; var pCdSenha: Integer; var pCdUsuari: Integer; var pNmUsuari: string);
begin
  if FAutenticarUsuarioCommand = nil then
  begin
    FAutenticarUsuarioCommand := FDBXConnection.CreateCommand;
    FAutenticarUsuarioCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FAutenticarUsuarioCommand.Text := 'TSDmRW.AutenticarUsuario';
    FAutenticarUsuarioCommand.Prepare;
  end;
  FAutenticarUsuarioCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FAutenticarUsuarioCommand.Parameters[1].Value.SetWideString(pDsResult);
  FAutenticarUsuarioCommand.Parameters[2].Value.SetWideString(pDsLogin);
  FAutenticarUsuarioCommand.Parameters[3].Value.SetInt32(pCdSenha);
  FAutenticarUsuarioCommand.Parameters[4].Value.SetInt32(pCdUsuari);
  FAutenticarUsuarioCommand.Parameters[5].Value.SetWideString(pNmUsuari);
  FAutenticarUsuarioCommand.ExecuteUpdate;
  pDsResult := FAutenticarUsuarioCommand.Parameters[1].Value.GetWideString;
  pCdSenha := FAutenticarUsuarioCommand.Parameters[3].Value.GetInt32;
  pCdUsuari := FAutenticarUsuarioCommand.Parameters[4].Value.GetInt32;
  pNmUsuari := FAutenticarUsuarioCommand.Parameters[5].Value.GetWideString;
end;

procedure TSDmRWClient.AtualizarSenhaUsuario(pDsCreden: string; var pDsResult: string; pDsLogin: string; pNrSenha: Integer);
begin
  if FAtualizarSenhaUsuarioCommand = nil then
  begin
    FAtualizarSenhaUsuarioCommand := FDBXConnection.CreateCommand;
    FAtualizarSenhaUsuarioCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FAtualizarSenhaUsuarioCommand.Text := 'TSDmRW.AtualizarSenhaUsuario';
    FAtualizarSenhaUsuarioCommand.Prepare;
  end;
  FAtualizarSenhaUsuarioCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FAtualizarSenhaUsuarioCommand.Parameters[1].Value.SetWideString(pDsResult);
  FAtualizarSenhaUsuarioCommand.Parameters[2].Value.SetWideString(pDsLogin);
  FAtualizarSenhaUsuarioCommand.Parameters[3].Value.SetInt32(pNrSenha);
  FAtualizarSenhaUsuarioCommand.ExecuteUpdate;
  pDsResult := FAtualizarSenhaUsuarioCommand.Parameters[1].Value.GetWideString;
end;

function TSDmRWClient.ExecutarInstrucaoSql(pDsCreden: string; var pDsResult: string; pDsSql: string): Integer;
begin
  if FExecutarInstrucaoSqlCommand = nil then
  begin
    FExecutarInstrucaoSqlCommand := FDBXConnection.CreateCommand;
    FExecutarInstrucaoSqlCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FExecutarInstrucaoSqlCommand.Text := 'TSDmRW.ExecutarInstrucaoSql';
    FExecutarInstrucaoSqlCommand.Prepare;
  end;
  FExecutarInstrucaoSqlCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FExecutarInstrucaoSqlCommand.Parameters[1].Value.SetWideString(pDsResult);
  FExecutarInstrucaoSqlCommand.Parameters[2].Value.SetWideString(pDsSql);
  FExecutarInstrucaoSqlCommand.ExecuteUpdate;
  pDsResult := FExecutarInstrucaoSqlCommand.Parameters[1].Value.GetWideString;
  Result := FExecutarInstrucaoSqlCommand.Parameters[3].Value.GetInt32;
end;

procedure TSDmRWClient.ChavePrimaria(pDsCreden: string; var pDsResult: string; pNmTabela: string; var pStlChave: string);
begin
  if FChavePrimariaCommand = nil then
  begin
    FChavePrimariaCommand := FDBXConnection.CreateCommand;
    FChavePrimariaCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FChavePrimariaCommand.Text := 'TSDmRW.ChavePrimaria';
    FChavePrimariaCommand.Prepare;
  end;
  FChavePrimariaCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FChavePrimariaCommand.Parameters[1].Value.SetWideString(pDsResult);
  FChavePrimariaCommand.Parameters[2].Value.SetWideString(pNmTabela);
  FChavePrimariaCommand.Parameters[3].Value.SetWideString(pStlChave);
  FChavePrimariaCommand.ExecuteUpdate;
  pDsResult := FChavePrimariaCommand.Parameters[1].Value.GetWideString;
  pStlChave := FChavePrimariaCommand.Parameters[3].Value.GetWideString;
end;

function TSDmRWClient.ProxNumeroSequencial(pDsCreden: string; var pDsResult: string; pNmTabela: string; pNmAtribu: string): Integer;
begin
  if FProxNumeroSequencialCommand = nil then
  begin
    FProxNumeroSequencialCommand := FDBXConnection.CreateCommand;
    FProxNumeroSequencialCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FProxNumeroSequencialCommand.Text := 'TSDmRW.ProxNumeroSequencial';
    FProxNumeroSequencialCommand.Prepare;
  end;
  FProxNumeroSequencialCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FProxNumeroSequencialCommand.Parameters[1].Value.SetWideString(pDsResult);
  FProxNumeroSequencialCommand.Parameters[2].Value.SetWideString(pNmTabela);
  FProxNumeroSequencialCommand.Parameters[3].Value.SetWideString(pNmAtribu);
  FProxNumeroSequencialCommand.ExecuteUpdate;
  pDsResult := FProxNumeroSequencialCommand.Parameters[1].Value.GetWideString;
  Result := FProxNumeroSequencialCommand.Parameters[4].Value.GetInt32;
end;

procedure TSDmRWClient.DataHoraServer(pDsCreden: string; var pDsResult: string; var pDtDatHor: TDateTime);
begin
  if FDataHoraServerCommand = nil then
  begin
    FDataHoraServerCommand := FDBXConnection.CreateCommand;
    FDataHoraServerCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FDataHoraServerCommand.Text := 'TSDmRW.DataHoraServer';
    FDataHoraServerCommand.Prepare;
  end;
  FDataHoraServerCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FDataHoraServerCommand.Parameters[1].Value.SetWideString(pDsResult);
  FDataHoraServerCommand.Parameters[2].Value.AsDateTime := pDtDatHor;
  FDataHoraServerCommand.ExecuteUpdate;
  pDsResult := FDataHoraServerCommand.Parameters[1].Value.GetWideString;
  pDtDatHor := FDataHoraServerCommand.Parameters[2].Value.AsDateTime;
end;

procedure TSDmRWClient.ValorDefault(pDsCreden: string; var pDsResult: string; var pVrDefaul: string);
begin
  if FValorDefaultCommand = nil then
  begin
    FValorDefaultCommand := FDBXConnection.CreateCommand;
    FValorDefaultCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FValorDefaultCommand.Text := 'TSDmRW.ValorDefault';
    FValorDefaultCommand.Prepare;
  end;
  FValorDefaultCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FValorDefaultCommand.Parameters[1].Value.SetWideString(pDsResult);
  FValorDefaultCommand.Parameters[2].Value.SetWideString(pVrDefaul);
  FValorDefaultCommand.ExecuteUpdate;
  pDsResult := FValorDefaultCommand.Parameters[1].Value.GetWideString;
  pVrDefaul := FValorDefaultCommand.Parameters[2].Value.GetWideString;
end;

procedure TSDmRWClient.EncontrarInstrucaoSQLConsulta(pDsCreden: string; var pDsResult: string; pNrQueCon: Integer; var pDsSql: string);
begin
  if FEncontrarInstrucaoSQLConsultaCommand = nil then
  begin
    FEncontrarInstrucaoSQLConsultaCommand := FDBXConnection.CreateCommand;
    FEncontrarInstrucaoSQLConsultaCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FEncontrarInstrucaoSQLConsultaCommand.Text := 'TSDmRW.EncontrarInstrucaoSQLConsulta';
    FEncontrarInstrucaoSQLConsultaCommand.Prepare;
  end;
  FEncontrarInstrucaoSQLConsultaCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FEncontrarInstrucaoSQLConsultaCommand.Parameters[1].Value.SetWideString(pDsResult);
  FEncontrarInstrucaoSQLConsultaCommand.Parameters[2].Value.SetInt32(pNrQueCon);
  FEncontrarInstrucaoSQLConsultaCommand.Parameters[3].Value.SetWideString(pDsSql);
  FEncontrarInstrucaoSQLConsultaCommand.ExecuteUpdate;
  pDsResult := FEncontrarInstrucaoSQLConsultaCommand.Parameters[1].Value.GetWideString;
  pDsSql := FEncontrarInstrucaoSQLConsultaCommand.Parameters[3].Value.GetWideString;
end;

procedure TSDmRWClient.EncontrarInstrucaoSQL(pDsCreden: string; var pDsResult: string; pNrQuery: Integer; var pDsSql: string; pNmFormul: string);
begin
  if FEncontrarInstrucaoSQLCommand = nil then
  begin
    FEncontrarInstrucaoSQLCommand := FDBXConnection.CreateCommand;
    FEncontrarInstrucaoSQLCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FEncontrarInstrucaoSQLCommand.Text := 'TSDmRW.EncontrarInstrucaoSQL';
    FEncontrarInstrucaoSQLCommand.Prepare;
  end;
  FEncontrarInstrucaoSQLCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FEncontrarInstrucaoSQLCommand.Parameters[1].Value.SetWideString(pDsResult);
  FEncontrarInstrucaoSQLCommand.Parameters[2].Value.SetInt32(pNrQuery);
  FEncontrarInstrucaoSQLCommand.Parameters[3].Value.SetWideString(pDsSql);
  FEncontrarInstrucaoSQLCommand.Parameters[4].Value.SetWideString(pNmFormul);
  FEncontrarInstrucaoSQLCommand.ExecuteUpdate;
  pDsResult := FEncontrarInstrucaoSQLCommand.Parameters[1].Value.GetWideString;
  pDsSql := FEncontrarInstrucaoSQLCommand.Parameters[3].Value.GetWideString;
end;

function TSDmRWClient.TipoBancoDados(pDsCreden: string; var pDsResult: string; var pDtForBco: string; var pNrSrVers: Integer; var pNrSrVeSu: Integer): OleVariant;
begin
  if FTipoBancoDadosCommand = nil then
  begin
    FTipoBancoDadosCommand := FDBXConnection.CreateCommand;
    FTipoBancoDadosCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FTipoBancoDadosCommand.Text := 'TSDmRW.TipoBancoDados';
    FTipoBancoDadosCommand.Prepare;
  end;
  FTipoBancoDadosCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FTipoBancoDadosCommand.Parameters[1].Value.SetWideString(pDsResult);
  FTipoBancoDadosCommand.Parameters[2].Value.SetWideString(pDtForBco);
  FTipoBancoDadosCommand.Parameters[3].Value.SetInt32(pNrSrVers);
  FTipoBancoDadosCommand.Parameters[4].Value.SetInt32(pNrSrVeSu);
  FTipoBancoDadosCommand.ExecuteUpdate;
  pDsResult := FTipoBancoDadosCommand.Parameters[1].Value.GetWideString;
  pDtForBco := FTipoBancoDadosCommand.Parameters[2].Value.GetWideString;
  pNrSrVers := FTipoBancoDadosCommand.Parameters[3].Value.GetInt32;
  pNrSrVeSu := FTipoBancoDadosCommand.Parameters[4].Value.GetInt32;
  Result := FTipoBancoDadosCommand.Parameters[5].Value.AsVariant;
end;

procedure TSDmRWClient.AtualizarDicionarioDados(pDsCreden: string; var pDsResult: string);
begin
  if FAtualizarDicionarioDadosCommand = nil then
  begin
    FAtualizarDicionarioDadosCommand := FDBXConnection.CreateCommand;
    FAtualizarDicionarioDadosCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FAtualizarDicionarioDadosCommand.Text := 'TSDmRW.AtualizarDicionarioDados';
    FAtualizarDicionarioDadosCommand.Prepare;
  end;
  FAtualizarDicionarioDadosCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FAtualizarDicionarioDadosCommand.Parameters[1].Value.SetWideString(pDsResult);
  FAtualizarDicionarioDadosCommand.ExecuteUpdate;
  pDsResult := FAtualizarDicionarioDadosCommand.Parameters[1].Value.GetWideString;
end;

function TSDmRWClient.VerificarNovaVersaoCliente(pDsCreden: string; var pDsResult: string; pCdsClient: OleVariant): OleVariant;
begin
  if FVerificarNovaVersaoClienteCommand = nil then
  begin
    FVerificarNovaVersaoClienteCommand := FDBXConnection.CreateCommand;
    FVerificarNovaVersaoClienteCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FVerificarNovaVersaoClienteCommand.Text := 'TSDmRW.VerificarNovaVersaoCliente';
    FVerificarNovaVersaoClienteCommand.Prepare;
  end;
  FVerificarNovaVersaoClienteCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FVerificarNovaVersaoClienteCommand.Parameters[1].Value.SetWideString(pDsResult);
  FVerificarNovaVersaoClienteCommand.Parameters[2].Value.AsVariant := pCdsClient;
  FVerificarNovaVersaoClienteCommand.ExecuteUpdate;
  pDsResult := FVerificarNovaVersaoClienteCommand.Parameters[1].Value.GetWideString;
  Result := FVerificarNovaVersaoClienteCommand.Parameters[3].Value.AsVariant;
end;

function TSDmRWClient.ParametroDaEmpresa(pDsCreden: string; var pDsResult: string; pNrParame: Integer; pCdEmpres: Integer): OleVariant;
begin
  if FParametroDaEmpresaCommand = nil then
  begin
    FParametroDaEmpresaCommand := FDBXConnection.CreateCommand;
    FParametroDaEmpresaCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FParametroDaEmpresaCommand.Text := 'TSDmRW.ParametroDaEmpresa';
    FParametroDaEmpresaCommand.Prepare;
  end;
  FParametroDaEmpresaCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FParametroDaEmpresaCommand.Parameters[1].Value.SetWideString(pDsResult);
  FParametroDaEmpresaCommand.Parameters[2].Value.SetInt32(pNrParame);
  FParametroDaEmpresaCommand.Parameters[3].Value.SetInt32(pCdEmpres);
  FParametroDaEmpresaCommand.ExecuteUpdate;
  pDsResult := FParametroDaEmpresaCommand.Parameters[1].Value.GetWideString;
  Result := FParametroDaEmpresaCommand.Parameters[4].Value.AsVariant;
end;

procedure TSDmRWClient.GravarTelasAbertaUsuario(pDsCreden: string; var pDsResult: string; pCdsTelas: OleVariant);
begin
  if FGravarTelasAbertaUsuarioCommand = nil then
  begin
    FGravarTelasAbertaUsuarioCommand := FDBXConnection.CreateCommand;
    FGravarTelasAbertaUsuarioCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FGravarTelasAbertaUsuarioCommand.Text := 'TSDmRW.GravarTelasAbertaUsuario';
    FGravarTelasAbertaUsuarioCommand.Prepare;
  end;
  FGravarTelasAbertaUsuarioCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FGravarTelasAbertaUsuarioCommand.Parameters[1].Value.SetWideString(pDsResult);
  FGravarTelasAbertaUsuarioCommand.Parameters[2].Value.AsVariant := pCdsTelas;
  FGravarTelasAbertaUsuarioCommand.ExecuteUpdate;
  pDsResult := FGravarTelasAbertaUsuarioCommand.Parameters[1].Value.GetWideString;
end;


constructor TSDmRWClient.Create(ADBXConnection: TDBXConnection);
begin
  inherited Create(ADBXConnection);
end;


constructor TSDmRWClient.Create(ADBXConnection: TDBXConnection; AInstanceOwner: Boolean);
begin
  inherited Create(ADBXConnection, AInstanceOwner);
end;


destructor TSDmRWClient.Destroy;
begin
  FreeAndNil(FSqlConnRWAfterConnectCommand);
  FreeAndNil(FEntradaClienteCommand);
  FreeAndNil(FClienteAutenticadoCommand);
  FreeAndNil(FRetornaQuantidadeRegistroTabelaCommand);
  FreeAndNil(FRetornaDadosSqlCadastroCommand);
  FreeAndNil(FRetornaDadosSqlFixaCommand);
  FreeAndNil(FAutenticarUsuarioCommand);
  FreeAndNil(FAtualizarSenhaUsuarioCommand);
  FreeAndNil(FExecutarInstrucaoSqlCommand);
  FreeAndNil(FChavePrimariaCommand);
  FreeAndNil(FProxNumeroSequencialCommand);
  FreeAndNil(FDataHoraServerCommand);
  FreeAndNil(FValorDefaultCommand);
  FreeAndNil(FEncontrarInstrucaoSQLConsultaCommand);
  FreeAndNil(FEncontrarInstrucaoSQLCommand);
  FreeAndNil(FTipoBancoDadosCommand);
  FreeAndNil(FAtualizarDicionarioDadosCommand);
  FreeAndNil(FVerificarNovaVersaoClienteCommand);
  FreeAndNil(FParametroDaEmpresaCommand);
  FreeAndNil(FGravarTelasAbertaUsuarioCommand);
  inherited;
end;

end.


//
// Created by the DataSnap proxy generator.
// 05/07/2012 11:24:59
//

unit UServerClasses;

interface

uses DBXCommon, DBXClient, DBXJSON, DSProxy, Classes, SysUtils, DB, SqlExpr, DBXDBReaders, DBXJSONReflect;

type
  TSDmLogosClient = class(TDSAdminClient)
  private
    FPegaDadosCommand: TDBXCommand;
    FTransacaoCommand: TDBXCommand;
    FAlteraHistoricoCommand: TDBXCommand;
    FRoolBackCommand: TDBXCommand;
  public
    constructor Create(ADBXConnection: TDBXConnection); overload;
    constructor Create(ADBXConnection: TDBXConnection; AInstanceOwner: Boolean); overload;
    destructor Destroy; override;
    function PegaDados: OleVariant;
    function Transacao: string;
    function AlteraHistorico(pDsHistor: string): string;
    function RoolBack: string;
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

  TSDmSisClient = class(TDSAdminClient)
  private
    FSqlConnSisAfterConnectCommand: TDBXCommand;
    FExcluirAtributoIndiceSecundarioCommand: TDBXCommand;
    FExcluirIndiceSecundarioCommand: TDBXCommand;
    FExcluirChaveEstrangeiraCommand: TDBXCommand;
    FExcluirAtributoChaveEstrangeiraCommand: TDBXCommand;
    FExcluirTabelaCommand: TDBXCommand;
    FExcluirAtributoCommand: TDBXCommand;
    FExcluirAtributoPrimarioCommand: TDBXCommand;
    FAtualizarPosicaoChaveCommand: TDBXCommand;
    FExcluirFormularioDinamicoCommand: TDBXCommand;
    FSalvarObjetosFormDinamicoCommand: TDBXCommand;
    FAtribuirProviderFormDinamicoCommand: TDBXCommand;
    FLiberarProviderFormDinamicoCommand: TDBXCommand;
    FOrdemInicialFormDinamicoCommand: TDBXCommand;
    FSalvarConfigConsultaUsuarioCommand: TDBXCommand;
    FSalvarColunasConsultaUsuarioCommand: TDBXCommand;
    FAtualizarUtilizacaoFormularioCommand: TDBXCommand;
    FReorganizarOrdenacaoMenuCommand: TDBXCommand;
    FMoverMenuCommand: TDBXCommand;
    FAtualizaEmpresasSelecionadasUsuarioCommand: TDBXCommand;
    FAtualizarLogCommand: TDBXCommand;
  public
    constructor Create(ADBXConnection: TDBXConnection); overload;
    constructor Create(ADBXConnection: TDBXConnection; AInstanceOwner: Boolean); overload;
    destructor Destroy; override;
    procedure SqlConnSisAfterConnect(Sender: TObject);
    procedure ExcluirAtributoIndiceSecundario(pDsCreden: string; var pDsResult: string; pNmIndice: string; pNmAtribu: string);
    procedure ExcluirIndiceSecundario(pDsCreden: string; var pDsResult: string; pNmIndice: string);
    procedure ExcluirChaveEstrangeira(pDsCreden: string; var pDsResult: string; pNmChaEst: string);
    procedure ExcluirAtributoChaveEstrangeira(pDsCreden: string; var pDsResult: string; pNmChaEst: string; pNmAtribu: string);
    procedure ExcluirTabela(pDsCreden: string; var pDsResult: string; pNmTabela: string);
    procedure ExcluirAtributo(pDsCreden: string; var pDsResult: string; pNmTabela: string; pNmAtribu: string);
    procedure ExcluirAtributoPrimario(pDsCreden: string; var pDsResult: string; pNmTabela: string; pNmAtribu: string);
    procedure AtualizarPosicaoChave(pDsCreden: string; var pDsResult: string; pTpChave: string; pNmChaEst: string; pNmTabela: string; pNrNewPos: Integer; pNrOldPos: Integer);
    procedure ExcluirFormularioDinamico(pDsCreden: string; var pDsResult: string; pNrForDin: string);
    procedure SalvarObjetosFormDinamico(pDsCreden: string; var pDsResult: string; pNrForDin: string; pData: OleVariant; pDsOrdIni: string; pNmTabela: string; pCdsConsu: OleVariant; pNmFrmOri: string; pCdsRetCo: OleVariant);
    procedure AtribuirProviderFormDinamico(pDsCreden: string; var pDsResult: string; var pNmProvid: string; pNmTabela: string; pNrForDin: Integer);
    procedure LiberarProviderFormDinamico(pDsCreden: string; var pDsResult: string; pNmProvid: string);
    function OrdemInicialFormDinamico(pDsCreden: string; var pDsResult: string; pNmTabela: string): OleVariant;
    procedure SalvarConfigConsultaUsuario(pDsCreden: string; var pDsResult: string; pNrQueCon: Integer; pCdUsuari: Integer; pNmTabOrd: string; pNmAtrOrd: string; pNmTabPes: string; pNmAtrPes: string; pNmTabPe2: string; pNmAtrPe2: string; pDsLocal2: string; pTpMaiMin: string; pTpWhere: Integer);
    procedure SalvarColunasConsultaUsuario(pDsCreden: string; var pDsResult: string; pNrQueCon: Integer; pCdUsuari: Integer; pData: OleVariant; pNmFormul: string);
    procedure AtualizarUtilizacaoFormulario(pDsCreden: string; var pDsResult: string; pCdUsuari: Integer; pNrForDin: Integer; pTpFormul: string; pNrSeqFor: Integer);
    procedure ReorganizarOrdenacaoMenu(pDsCreden: string; var pDsResult: string; pNrMenPai: Integer);
    procedure MoverMenu(pDsCreden: string; var pDsResult: string; pNrMenPai: Integer; pNrOldOrd: Integer; pNrNewOrd: Integer);
    procedure AtualizaEmpresasSelecionadasUsuario(pDsCreden: string; var pDsResult: string; pCdEmpres: string; pCdUsuari: Integer);
    procedure AtualizarLog(pDsCreden: string; var pDsResult: string; pNmTabela: string; pCdUsuari: Integer; pVrChave: string; pTpOperac: Byte; pNmFormul: string; pCdsLog: OleVariant);
  end;

  TSDmTraClient = class(TDSAdminClient)
  private
    FSqlConnTraAfterConnectCommand: TDBXCommand;
    FSalvarCargaCommand: TDBXCommand;
    FValidarCargaCommand: TDBXCommand;
  public
    constructor Create(ADBXConnection: TDBXConnection); overload;
    constructor Create(ADBXConnection: TDBXConnection; AInstanceOwner: Boolean); overload;
    destructor Destroy; override;
    procedure SqlConnTraAfterConnect(Sender: TObject);
    procedure SalvarCarga(pDsCreden: string; var pDsResult: string; var pCdCarga: Integer; pDsDesCar: string; pNrMalote: Integer; pTpVeicul: Integer; pKmPreCar: Integer; pDtPreRet: TDateTime; pTpEscolt: Integer; pCdEmpEsc: Integer; pNmFrmOri: string; pStCarga: string; pCdVeicu1: Integer; pCdMotor1: Integer; pCdVeicu2: Integer; pCdMotor2: Integer; pCdVeicu3: Integer; pCdMotor3: Integer; pCdVeicu4: Integer; pCdMotor4: Integer; pCdsCTRC: OleVariant; pCdEmpDes: Integer);
    function ValidarCarga(pDsCreden: string; pCdsParams: OleVariant): AnsiString;
  end;

  TSDmConClient = class(TDSAdminClient)
  private
    FSqlConnConAfterConnectCommand: TDBXCommand;
    FLancamentoPatrimonialCommand: TDBXCommand;
    FAbrirPlanoContasCommand: TDBXCommand;
    FEfetuarLancamentosContabeisCommand: TDBXCommand;
    FBalanceteCommand: TDBXCommand;
    FAbreFechaPeriodoContabilCommand: TDBXCommand;
    FEncerrarPeriodoContabilCommand: TDBXCommand;
  public
    constructor Create(ADBXConnection: TDBXConnection); overload;
    constructor Create(ADBXConnection: TDBXConnection; AInstanceOwner: Boolean); overload;
    destructor Destroy; override;
    procedure SqlConnConAfterConnect(Sender: TObject);
    procedure LancamentoPatrimonial(pDsCreden: string; var pDsResult: string; pCdBem: Integer);
    function AbrirPlanoContas(pDsCreden: string; var pDsResult: string; pNrPlano: Integer; pTpConta: Integer; pQtNivel: Integer): string;
    procedure EfetuarLancamentosContabeis(pDsCreden: string; var pDsResult: string; pNrPlano: Integer; pNmFormul: string; pCdsLancto: OleVariant);
    function Balancete(pDsCreden: string; var pDsResult: string; pNrPlano: Integer; pTpConta: Integer; pQtNivel: Integer; pCdEmpres: string; pDtInicio: TDateTime; pDtFinal: TDateTime): string;
    procedure AbreFechaPeriodoContabil(pDsCreden: string; var pDsResult: string; pDtAno: Integer; pDtMes: Integer; pCdEmpres: Integer; pTpOperac: string);
    procedure EncerrarPeriodoContabil(pDsCreden: string; var pDsResult: string; pDtAno: Integer; pDtMes: Integer; pCdEmpres: Integer; pNrPlano: Integer);
  end;

  TSDmCRPClient = class(TDSAdminClient)
  private
    FSqlConnCRPAfterConnectCommand: TDBXCommand;
    FFaturarCommand: TDBXCommand;
    FLancarContasPagarReceberCommand: TDBXCommand;
  public
    constructor Create(ADBXConnection: TDBXConnection); overload;
    constructor Create(ADBXConnection: TDBXConnection; AInstanceOwner: Boolean); overload;
    destructor Destroy; override;
    procedure SqlConnCRPAfterConnect(Sender: TObject);
    function Faturar(pDsCreden: string; var pDsResult: string; pNmFormul: string; pDtFatura: TDateTime; pCdTitula: Integer; pVrLancto: Double; pCdEmpres: Integer; pNrPreCar: Integer; pNrPlano: Integer; pVrDescon: Double; pCdsN006: OleVariant): Integer;
    procedure LancarContasPagarReceber(pDsCreden: string; var pDsResult: string; pNmFormul: string; pCdHistor: Integer; pTpPagRec: string; pVrLancto: Double; pCdForPag: Integer; pCdTitula: Integer; pCdEmpres: Integer; pNrDocto: Integer; pDtEmissa: TDateTime; pNrPreCar: Integer; pNrOrdem: Integer; pDtVencto: TDateTime; pCdEvento: Integer; pTpPagto: string; pNrConCon: Integer; pDsComHis: string; pNrPlano: Integer);
  end;

  TSDmWmsClient = class(TDSAdminClient)
  private
    FSqlConnWMSAfterConnectCommand: TDBXCommand;
    FProximaAtividadeCommand: TDBXCommand;
    FConferenciaEntradaIniciarCommand: TDBXCommand;
    FConferenciaEntradaFinalizarCommand: TDBXCommand;
    FConferenciaSaidaIniciarCommand: TDBXCommand;
    FConferenciaSaidaFinalizarCommand: TDBXCommand;
    FGravarOperacaoLogisticaCommand: TDBXCommand;
    FGravaPrioridadeSituacaoOpeLogCommand: TDBXCommand;
    FRefazerAtividadeCommand: TDBXCommand;
  public
    constructor Create(ADBXConnection: TDBXConnection); overload;
    constructor Create(ADBXConnection: TDBXConnection; AInstanceOwner: Boolean); overload;
    destructor Destroy; override;
    procedure SqlConnWMSAfterConnect(Sender: TObject);
    function ProximaAtividade(pCdUsuari: Integer; var pNrMapa: AnsiString; var pDsAtivid: AnsiString): AnsiString;
    function ConferenciaEntradaIniciar(pCdUsuari: Integer; pNrMapa: AnsiString; var pNrNota: AnsiString; var pNrFornec: AnsiString; var pDsLista: AnsiString): AnsiString;
    function ConferenciaEntradaFinalizar(pCdUsuari: Integer; pNrMapa: string; pDsLista: string; pSnCancel: string; pQtProSId: Integer): AnsiString;
    function ConferenciaSaidaIniciar(pCdUsuari: Integer; pNrMapa: AnsiString; var pNrNota: AnsiString; var pNrFornec: AnsiString; var pDsLista: AnsiString): AnsiString;
    function ConferenciaSaidaFinalizar(pCdUsuari: Integer; pNrMapa: string; pDsLista: string; pCdClient: string; pSnCancel: string; pQtProSId: Integer): AnsiString;
    function GravarOperacaoLogistica(pDsCreden: AnsiString; pCdUsuari: Integer; pNmFormul: AnsiString; pCdArmaze: Integer; pCdTipOpe: Integer; pCdsW005: OleVariant; pCdsW006: OleVariant; pCdsW007: OleVariant): AnsiString;
    function GravaPrioridadeSituacaoOpeLog(pCdArmaze: AnsiString; PDsListOp: AnsiString): AnsiString;
    function RefazerAtividade(pCdArmaze: AnsiString; pNrOpeLog: AnsiString): AnsiString;
  end;

implementation

function TSDmLogosClient.PegaDados: OleVariant;
begin
  if FPegaDadosCommand = nil then
  begin
    FPegaDadosCommand := FDBXConnection.CreateCommand;
    FPegaDadosCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FPegaDadosCommand.Text := 'TSDmLogos.PegaDados';
    FPegaDadosCommand.Prepare;
  end;
  FPegaDadosCommand.ExecuteUpdate;
  Result := FPegaDadosCommand.Parameters[0].Value.AsVariant;
end;

function TSDmLogosClient.Transacao: string;
begin
  if FTransacaoCommand = nil then
  begin
    FTransacaoCommand := FDBXConnection.CreateCommand;
    FTransacaoCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FTransacaoCommand.Text := 'TSDmLogos.Transacao';
    FTransacaoCommand.Prepare;
  end;
  FTransacaoCommand.ExecuteUpdate;
  Result := FTransacaoCommand.Parameters[0].Value.GetWideString;
end;

function TSDmLogosClient.AlteraHistorico(pDsHistor: string): string;
begin
  if FAlteraHistoricoCommand = nil then
  begin
    FAlteraHistoricoCommand := FDBXConnection.CreateCommand;
    FAlteraHistoricoCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FAlteraHistoricoCommand.Text := 'TSDmLogos.AlteraHistorico';
    FAlteraHistoricoCommand.Prepare;
  end;
  FAlteraHistoricoCommand.Parameters[0].Value.SetWideString(pDsHistor);
  FAlteraHistoricoCommand.ExecuteUpdate;
  Result := FAlteraHistoricoCommand.Parameters[1].Value.GetWideString;
end;

function TSDmLogosClient.RoolBack: string;
begin
  if FRoolBackCommand = nil then
  begin
    FRoolBackCommand := FDBXConnection.CreateCommand;
    FRoolBackCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FRoolBackCommand.Text := 'TSDmLogos.RoolBack';
    FRoolBackCommand.Prepare;
  end;
  FRoolBackCommand.ExecuteUpdate;
  Result := FRoolBackCommand.Parameters[0].Value.GetWideString;
end;


constructor TSDmLogosClient.Create(ADBXConnection: TDBXConnection);
begin
  inherited Create(ADBXConnection);
end;


constructor TSDmLogosClient.Create(ADBXConnection: TDBXConnection; AInstanceOwner: Boolean);
begin
  inherited Create(ADBXConnection, AInstanceOwner);
end;


destructor TSDmLogosClient.Destroy;
begin
  FreeAndNil(FPegaDadosCommand);
  FreeAndNil(FTransacaoCommand);
  FreeAndNil(FAlteraHistoricoCommand);
  FreeAndNil(FRoolBackCommand);
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

procedure TSDmSisClient.SqlConnSisAfterConnect(Sender: TObject);
begin
  if FSqlConnSisAfterConnectCommand = nil then
  begin
    FSqlConnSisAfterConnectCommand := FDBXConnection.CreateCommand;
    FSqlConnSisAfterConnectCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FSqlConnSisAfterConnectCommand.Text := 'TSDmSis.SqlConnSisAfterConnect';
    FSqlConnSisAfterConnectCommand.Prepare;
  end;
  if not Assigned(Sender) then
    FSqlConnSisAfterConnectCommand.Parameters[0].Value.SetNull
  else
  begin
    FMarshal := TDBXClientCommand(FSqlConnSisAfterConnectCommand.Parameters[0].ConnectionHandler).GetJSONMarshaler;
    try
      FSqlConnSisAfterConnectCommand.Parameters[0].Value.SetJSONValue(FMarshal.Marshal(Sender), True);
      if FInstanceOwner then
        Sender.Free
    finally
      FreeAndNil(FMarshal)
    end
    end;
  FSqlConnSisAfterConnectCommand.ExecuteUpdate;
end;

procedure TSDmSisClient.ExcluirAtributoIndiceSecundario(pDsCreden: string; var pDsResult: string; pNmIndice: string; pNmAtribu: string);
begin
  if FExcluirAtributoIndiceSecundarioCommand = nil then
  begin
    FExcluirAtributoIndiceSecundarioCommand := FDBXConnection.CreateCommand;
    FExcluirAtributoIndiceSecundarioCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FExcluirAtributoIndiceSecundarioCommand.Text := 'TSDmSis.ExcluirAtributoIndiceSecundario';
    FExcluirAtributoIndiceSecundarioCommand.Prepare;
  end;
  FExcluirAtributoIndiceSecundarioCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FExcluirAtributoIndiceSecundarioCommand.Parameters[1].Value.SetWideString(pDsResult);
  FExcluirAtributoIndiceSecundarioCommand.Parameters[2].Value.SetWideString(pNmIndice);
  FExcluirAtributoIndiceSecundarioCommand.Parameters[3].Value.SetWideString(pNmAtribu);
  FExcluirAtributoIndiceSecundarioCommand.ExecuteUpdate;
  pDsResult := FExcluirAtributoIndiceSecundarioCommand.Parameters[1].Value.GetWideString;
end;

procedure TSDmSisClient.ExcluirIndiceSecundario(pDsCreden: string; var pDsResult: string; pNmIndice: string);
begin
  if FExcluirIndiceSecundarioCommand = nil then
  begin
    FExcluirIndiceSecundarioCommand := FDBXConnection.CreateCommand;
    FExcluirIndiceSecundarioCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FExcluirIndiceSecundarioCommand.Text := 'TSDmSis.ExcluirIndiceSecundario';
    FExcluirIndiceSecundarioCommand.Prepare;
  end;
  FExcluirIndiceSecundarioCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FExcluirIndiceSecundarioCommand.Parameters[1].Value.SetWideString(pDsResult);
  FExcluirIndiceSecundarioCommand.Parameters[2].Value.SetWideString(pNmIndice);
  FExcluirIndiceSecundarioCommand.ExecuteUpdate;
  pDsResult := FExcluirIndiceSecundarioCommand.Parameters[1].Value.GetWideString;
end;

procedure TSDmSisClient.ExcluirChaveEstrangeira(pDsCreden: string; var pDsResult: string; pNmChaEst: string);
begin
  if FExcluirChaveEstrangeiraCommand = nil then
  begin
    FExcluirChaveEstrangeiraCommand := FDBXConnection.CreateCommand;
    FExcluirChaveEstrangeiraCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FExcluirChaveEstrangeiraCommand.Text := 'TSDmSis.ExcluirChaveEstrangeira';
    FExcluirChaveEstrangeiraCommand.Prepare;
  end;
  FExcluirChaveEstrangeiraCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FExcluirChaveEstrangeiraCommand.Parameters[1].Value.SetWideString(pDsResult);
  FExcluirChaveEstrangeiraCommand.Parameters[2].Value.SetWideString(pNmChaEst);
  FExcluirChaveEstrangeiraCommand.ExecuteUpdate;
  pDsResult := FExcluirChaveEstrangeiraCommand.Parameters[1].Value.GetWideString;
end;

procedure TSDmSisClient.ExcluirAtributoChaveEstrangeira(pDsCreden: string; var pDsResult: string; pNmChaEst: string; pNmAtribu: string);
begin
  if FExcluirAtributoChaveEstrangeiraCommand = nil then
  begin
    FExcluirAtributoChaveEstrangeiraCommand := FDBXConnection.CreateCommand;
    FExcluirAtributoChaveEstrangeiraCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FExcluirAtributoChaveEstrangeiraCommand.Text := 'TSDmSis.ExcluirAtributoChaveEstrangeira';
    FExcluirAtributoChaveEstrangeiraCommand.Prepare;
  end;
  FExcluirAtributoChaveEstrangeiraCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FExcluirAtributoChaveEstrangeiraCommand.Parameters[1].Value.SetWideString(pDsResult);
  FExcluirAtributoChaveEstrangeiraCommand.Parameters[2].Value.SetWideString(pNmChaEst);
  FExcluirAtributoChaveEstrangeiraCommand.Parameters[3].Value.SetWideString(pNmAtribu);
  FExcluirAtributoChaveEstrangeiraCommand.ExecuteUpdate;
  pDsResult := FExcluirAtributoChaveEstrangeiraCommand.Parameters[1].Value.GetWideString;
end;

procedure TSDmSisClient.ExcluirTabela(pDsCreden: string; var pDsResult: string; pNmTabela: string);
begin
  if FExcluirTabelaCommand = nil then
  begin
    FExcluirTabelaCommand := FDBXConnection.CreateCommand;
    FExcluirTabelaCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FExcluirTabelaCommand.Text := 'TSDmSis.ExcluirTabela';
    FExcluirTabelaCommand.Prepare;
  end;
  FExcluirTabelaCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FExcluirTabelaCommand.Parameters[1].Value.SetWideString(pDsResult);
  FExcluirTabelaCommand.Parameters[2].Value.SetWideString(pNmTabela);
  FExcluirTabelaCommand.ExecuteUpdate;
  pDsResult := FExcluirTabelaCommand.Parameters[1].Value.GetWideString;
end;

procedure TSDmSisClient.ExcluirAtributo(pDsCreden: string; var pDsResult: string; pNmTabela: string; pNmAtribu: string);
begin
  if FExcluirAtributoCommand = nil then
  begin
    FExcluirAtributoCommand := FDBXConnection.CreateCommand;
    FExcluirAtributoCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FExcluirAtributoCommand.Text := 'TSDmSis.ExcluirAtributo';
    FExcluirAtributoCommand.Prepare;
  end;
  FExcluirAtributoCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FExcluirAtributoCommand.Parameters[1].Value.SetWideString(pDsResult);
  FExcluirAtributoCommand.Parameters[2].Value.SetWideString(pNmTabela);
  FExcluirAtributoCommand.Parameters[3].Value.SetWideString(pNmAtribu);
  FExcluirAtributoCommand.ExecuteUpdate;
  pDsResult := FExcluirAtributoCommand.Parameters[1].Value.GetWideString;
end;

procedure TSDmSisClient.ExcluirAtributoPrimario(pDsCreden: string; var pDsResult: string; pNmTabela: string; pNmAtribu: string);
begin
  if FExcluirAtributoPrimarioCommand = nil then
  begin
    FExcluirAtributoPrimarioCommand := FDBXConnection.CreateCommand;
    FExcluirAtributoPrimarioCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FExcluirAtributoPrimarioCommand.Text := 'TSDmSis.ExcluirAtributoPrimario';
    FExcluirAtributoPrimarioCommand.Prepare;
  end;
  FExcluirAtributoPrimarioCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FExcluirAtributoPrimarioCommand.Parameters[1].Value.SetWideString(pDsResult);
  FExcluirAtributoPrimarioCommand.Parameters[2].Value.SetWideString(pNmTabela);
  FExcluirAtributoPrimarioCommand.Parameters[3].Value.SetWideString(pNmAtribu);
  FExcluirAtributoPrimarioCommand.ExecuteUpdate;
  pDsResult := FExcluirAtributoPrimarioCommand.Parameters[1].Value.GetWideString;
end;

procedure TSDmSisClient.AtualizarPosicaoChave(pDsCreden: string; var pDsResult: string; pTpChave: string; pNmChaEst: string; pNmTabela: string; pNrNewPos: Integer; pNrOldPos: Integer);
begin
  if FAtualizarPosicaoChaveCommand = nil then
  begin
    FAtualizarPosicaoChaveCommand := FDBXConnection.CreateCommand;
    FAtualizarPosicaoChaveCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FAtualizarPosicaoChaveCommand.Text := 'TSDmSis.AtualizarPosicaoChave';
    FAtualizarPosicaoChaveCommand.Prepare;
  end;
  FAtualizarPosicaoChaveCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FAtualizarPosicaoChaveCommand.Parameters[1].Value.SetWideString(pDsResult);
  FAtualizarPosicaoChaveCommand.Parameters[2].Value.SetWideString(pTpChave);
  FAtualizarPosicaoChaveCommand.Parameters[3].Value.SetWideString(pNmChaEst);
  FAtualizarPosicaoChaveCommand.Parameters[4].Value.SetWideString(pNmTabela);
  FAtualizarPosicaoChaveCommand.Parameters[5].Value.SetInt32(pNrNewPos);
  FAtualizarPosicaoChaveCommand.Parameters[6].Value.SetInt32(pNrOldPos);
  FAtualizarPosicaoChaveCommand.ExecuteUpdate;
  pDsResult := FAtualizarPosicaoChaveCommand.Parameters[1].Value.GetWideString;
end;

procedure TSDmSisClient.ExcluirFormularioDinamico(pDsCreden: string; var pDsResult: string; pNrForDin: string);
begin
  if FExcluirFormularioDinamicoCommand = nil then
  begin
    FExcluirFormularioDinamicoCommand := FDBXConnection.CreateCommand;
    FExcluirFormularioDinamicoCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FExcluirFormularioDinamicoCommand.Text := 'TSDmSis.ExcluirFormularioDinamico';
    FExcluirFormularioDinamicoCommand.Prepare;
  end;
  FExcluirFormularioDinamicoCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FExcluirFormularioDinamicoCommand.Parameters[1].Value.SetWideString(pDsResult);
  FExcluirFormularioDinamicoCommand.Parameters[2].Value.SetWideString(pNrForDin);
  FExcluirFormularioDinamicoCommand.ExecuteUpdate;
  pDsResult := FExcluirFormularioDinamicoCommand.Parameters[1].Value.GetWideString;
end;

procedure TSDmSisClient.SalvarObjetosFormDinamico(pDsCreden: string; var pDsResult: string; pNrForDin: string; pData: OleVariant; pDsOrdIni: string; pNmTabela: string; pCdsConsu: OleVariant; pNmFrmOri: string; pCdsRetCo: OleVariant);
begin
  if FSalvarObjetosFormDinamicoCommand = nil then
  begin
    FSalvarObjetosFormDinamicoCommand := FDBXConnection.CreateCommand;
    FSalvarObjetosFormDinamicoCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FSalvarObjetosFormDinamicoCommand.Text := 'TSDmSis.SalvarObjetosFormDinamico';
    FSalvarObjetosFormDinamicoCommand.Prepare;
  end;
  FSalvarObjetosFormDinamicoCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FSalvarObjetosFormDinamicoCommand.Parameters[1].Value.SetWideString(pDsResult);
  FSalvarObjetosFormDinamicoCommand.Parameters[2].Value.SetWideString(pNrForDin);
  FSalvarObjetosFormDinamicoCommand.Parameters[3].Value.AsVariant := pData;
  FSalvarObjetosFormDinamicoCommand.Parameters[4].Value.SetWideString(pDsOrdIni);
  FSalvarObjetosFormDinamicoCommand.Parameters[5].Value.SetWideString(pNmTabela);
  FSalvarObjetosFormDinamicoCommand.Parameters[6].Value.AsVariant := pCdsConsu;
  FSalvarObjetosFormDinamicoCommand.Parameters[7].Value.SetWideString(pNmFrmOri);
  FSalvarObjetosFormDinamicoCommand.Parameters[8].Value.AsVariant := pCdsRetCo;
  FSalvarObjetosFormDinamicoCommand.ExecuteUpdate;
  pDsResult := FSalvarObjetosFormDinamicoCommand.Parameters[1].Value.GetWideString;
end;

procedure TSDmSisClient.AtribuirProviderFormDinamico(pDsCreden: string; var pDsResult: string; var pNmProvid: string; pNmTabela: string; pNrForDin: Integer);
begin
  if FAtribuirProviderFormDinamicoCommand = nil then
  begin
    FAtribuirProviderFormDinamicoCommand := FDBXConnection.CreateCommand;
    FAtribuirProviderFormDinamicoCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FAtribuirProviderFormDinamicoCommand.Text := 'TSDmSis.AtribuirProviderFormDinamico';
    FAtribuirProviderFormDinamicoCommand.Prepare;
  end;
  FAtribuirProviderFormDinamicoCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FAtribuirProviderFormDinamicoCommand.Parameters[1].Value.SetWideString(pDsResult);
  FAtribuirProviderFormDinamicoCommand.Parameters[2].Value.SetWideString(pNmProvid);
  FAtribuirProviderFormDinamicoCommand.Parameters[3].Value.SetWideString(pNmTabela);
  FAtribuirProviderFormDinamicoCommand.Parameters[4].Value.SetInt32(pNrForDin);
  FAtribuirProviderFormDinamicoCommand.ExecuteUpdate;
  pDsResult := FAtribuirProviderFormDinamicoCommand.Parameters[1].Value.GetWideString;
  pNmProvid := FAtribuirProviderFormDinamicoCommand.Parameters[2].Value.GetWideString;
end;

procedure TSDmSisClient.LiberarProviderFormDinamico(pDsCreden: string; var pDsResult: string; pNmProvid: string);
begin
  if FLiberarProviderFormDinamicoCommand = nil then
  begin
    FLiberarProviderFormDinamicoCommand := FDBXConnection.CreateCommand;
    FLiberarProviderFormDinamicoCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FLiberarProviderFormDinamicoCommand.Text := 'TSDmSis.LiberarProviderFormDinamico';
    FLiberarProviderFormDinamicoCommand.Prepare;
  end;
  FLiberarProviderFormDinamicoCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FLiberarProviderFormDinamicoCommand.Parameters[1].Value.SetWideString(pDsResult);
  FLiberarProviderFormDinamicoCommand.Parameters[2].Value.SetWideString(pNmProvid);
  FLiberarProviderFormDinamicoCommand.ExecuteUpdate;
  pDsResult := FLiberarProviderFormDinamicoCommand.Parameters[1].Value.GetWideString;
end;

function TSDmSisClient.OrdemInicialFormDinamico(pDsCreden: string; var pDsResult: string; pNmTabela: string): OleVariant;
begin
  if FOrdemInicialFormDinamicoCommand = nil then
  begin
    FOrdemInicialFormDinamicoCommand := FDBXConnection.CreateCommand;
    FOrdemInicialFormDinamicoCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FOrdemInicialFormDinamicoCommand.Text := 'TSDmSis.OrdemInicialFormDinamico';
    FOrdemInicialFormDinamicoCommand.Prepare;
  end;
  FOrdemInicialFormDinamicoCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FOrdemInicialFormDinamicoCommand.Parameters[1].Value.SetWideString(pDsResult);
  FOrdemInicialFormDinamicoCommand.Parameters[2].Value.SetWideString(pNmTabela);
  FOrdemInicialFormDinamicoCommand.ExecuteUpdate;
  pDsResult := FOrdemInicialFormDinamicoCommand.Parameters[1].Value.GetWideString;
  Result := FOrdemInicialFormDinamicoCommand.Parameters[3].Value.AsVariant;
end;

procedure TSDmSisClient.SalvarConfigConsultaUsuario(pDsCreden: string; var pDsResult: string; pNrQueCon: Integer; pCdUsuari: Integer; pNmTabOrd: string; pNmAtrOrd: string; pNmTabPes: string; pNmAtrPes: string; pNmTabPe2: string; pNmAtrPe2: string; pDsLocal2: string; pTpMaiMin: string; pTpWhere: Integer);
begin
  if FSalvarConfigConsultaUsuarioCommand = nil then
  begin
    FSalvarConfigConsultaUsuarioCommand := FDBXConnection.CreateCommand;
    FSalvarConfigConsultaUsuarioCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FSalvarConfigConsultaUsuarioCommand.Text := 'TSDmSis.SalvarConfigConsultaUsuario';
    FSalvarConfigConsultaUsuarioCommand.Prepare;
  end;
  FSalvarConfigConsultaUsuarioCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FSalvarConfigConsultaUsuarioCommand.Parameters[1].Value.SetWideString(pDsResult);
  FSalvarConfigConsultaUsuarioCommand.Parameters[2].Value.SetInt32(pNrQueCon);
  FSalvarConfigConsultaUsuarioCommand.Parameters[3].Value.SetInt32(pCdUsuari);
  FSalvarConfigConsultaUsuarioCommand.Parameters[4].Value.SetWideString(pNmTabOrd);
  FSalvarConfigConsultaUsuarioCommand.Parameters[5].Value.SetWideString(pNmAtrOrd);
  FSalvarConfigConsultaUsuarioCommand.Parameters[6].Value.SetWideString(pNmTabPes);
  FSalvarConfigConsultaUsuarioCommand.Parameters[7].Value.SetWideString(pNmAtrPes);
  FSalvarConfigConsultaUsuarioCommand.Parameters[8].Value.SetWideString(pNmTabPe2);
  FSalvarConfigConsultaUsuarioCommand.Parameters[9].Value.SetWideString(pNmAtrPe2);
  FSalvarConfigConsultaUsuarioCommand.Parameters[10].Value.SetWideString(pDsLocal2);
  FSalvarConfigConsultaUsuarioCommand.Parameters[11].Value.SetWideString(pTpMaiMin);
  FSalvarConfigConsultaUsuarioCommand.Parameters[12].Value.SetInt32(pTpWhere);
  FSalvarConfigConsultaUsuarioCommand.ExecuteUpdate;
  pDsResult := FSalvarConfigConsultaUsuarioCommand.Parameters[1].Value.GetWideString;
end;

procedure TSDmSisClient.SalvarColunasConsultaUsuario(pDsCreden: string; var pDsResult: string; pNrQueCon: Integer; pCdUsuari: Integer; pData: OleVariant; pNmFormul: string);
begin
  if FSalvarColunasConsultaUsuarioCommand = nil then
  begin
    FSalvarColunasConsultaUsuarioCommand := FDBXConnection.CreateCommand;
    FSalvarColunasConsultaUsuarioCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FSalvarColunasConsultaUsuarioCommand.Text := 'TSDmSis.SalvarColunasConsultaUsuario';
    FSalvarColunasConsultaUsuarioCommand.Prepare;
  end;
  FSalvarColunasConsultaUsuarioCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FSalvarColunasConsultaUsuarioCommand.Parameters[1].Value.SetWideString(pDsResult);
  FSalvarColunasConsultaUsuarioCommand.Parameters[2].Value.SetInt32(pNrQueCon);
  FSalvarColunasConsultaUsuarioCommand.Parameters[3].Value.SetInt32(pCdUsuari);
  FSalvarColunasConsultaUsuarioCommand.Parameters[4].Value.AsVariant := pData;
  FSalvarColunasConsultaUsuarioCommand.Parameters[5].Value.SetWideString(pNmFormul);
  FSalvarColunasConsultaUsuarioCommand.ExecuteUpdate;
  pDsResult := FSalvarColunasConsultaUsuarioCommand.Parameters[1].Value.GetWideString;
end;

procedure TSDmSisClient.AtualizarUtilizacaoFormulario(pDsCreden: string; var pDsResult: string; pCdUsuari: Integer; pNrForDin: Integer; pTpFormul: string; pNrSeqFor: Integer);
begin
  if FAtualizarUtilizacaoFormularioCommand = nil then
  begin
    FAtualizarUtilizacaoFormularioCommand := FDBXConnection.CreateCommand;
    FAtualizarUtilizacaoFormularioCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FAtualizarUtilizacaoFormularioCommand.Text := 'TSDmSis.AtualizarUtilizacaoFormulario';
    FAtualizarUtilizacaoFormularioCommand.Prepare;
  end;
  FAtualizarUtilizacaoFormularioCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FAtualizarUtilizacaoFormularioCommand.Parameters[1].Value.SetWideString(pDsResult);
  FAtualizarUtilizacaoFormularioCommand.Parameters[2].Value.SetInt32(pCdUsuari);
  FAtualizarUtilizacaoFormularioCommand.Parameters[3].Value.SetInt32(pNrForDin);
  FAtualizarUtilizacaoFormularioCommand.Parameters[4].Value.SetWideString(pTpFormul);
  FAtualizarUtilizacaoFormularioCommand.Parameters[5].Value.SetInt32(pNrSeqFor);
  FAtualizarUtilizacaoFormularioCommand.ExecuteUpdate;
  pDsResult := FAtualizarUtilizacaoFormularioCommand.Parameters[1].Value.GetWideString;
end;

procedure TSDmSisClient.ReorganizarOrdenacaoMenu(pDsCreden: string; var pDsResult: string; pNrMenPai: Integer);
begin
  if FReorganizarOrdenacaoMenuCommand = nil then
  begin
    FReorganizarOrdenacaoMenuCommand := FDBXConnection.CreateCommand;
    FReorganizarOrdenacaoMenuCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FReorganizarOrdenacaoMenuCommand.Text := 'TSDmSis.ReorganizarOrdenacaoMenu';
    FReorganizarOrdenacaoMenuCommand.Prepare;
  end;
  FReorganizarOrdenacaoMenuCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FReorganizarOrdenacaoMenuCommand.Parameters[1].Value.SetWideString(pDsResult);
  FReorganizarOrdenacaoMenuCommand.Parameters[2].Value.SetInt32(pNrMenPai);
  FReorganizarOrdenacaoMenuCommand.ExecuteUpdate;
  pDsResult := FReorganizarOrdenacaoMenuCommand.Parameters[1].Value.GetWideString;
end;

procedure TSDmSisClient.MoverMenu(pDsCreden: string; var pDsResult: string; pNrMenPai: Integer; pNrOldOrd: Integer; pNrNewOrd: Integer);
begin
  if FMoverMenuCommand = nil then
  begin
    FMoverMenuCommand := FDBXConnection.CreateCommand;
    FMoverMenuCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FMoverMenuCommand.Text := 'TSDmSis.MoverMenu';
    FMoverMenuCommand.Prepare;
  end;
  FMoverMenuCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FMoverMenuCommand.Parameters[1].Value.SetWideString(pDsResult);
  FMoverMenuCommand.Parameters[2].Value.SetInt32(pNrMenPai);
  FMoverMenuCommand.Parameters[3].Value.SetInt32(pNrOldOrd);
  FMoverMenuCommand.Parameters[4].Value.SetInt32(pNrNewOrd);
  FMoverMenuCommand.ExecuteUpdate;
  pDsResult := FMoverMenuCommand.Parameters[1].Value.GetWideString;
end;

procedure TSDmSisClient.AtualizaEmpresasSelecionadasUsuario(pDsCreden: string; var pDsResult: string; pCdEmpres: string; pCdUsuari: Integer);
begin
  if FAtualizaEmpresasSelecionadasUsuarioCommand = nil then
  begin
    FAtualizaEmpresasSelecionadasUsuarioCommand := FDBXConnection.CreateCommand;
    FAtualizaEmpresasSelecionadasUsuarioCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FAtualizaEmpresasSelecionadasUsuarioCommand.Text := 'TSDmSis.AtualizaEmpresasSelecionadasUsuario';
    FAtualizaEmpresasSelecionadasUsuarioCommand.Prepare;
  end;
  FAtualizaEmpresasSelecionadasUsuarioCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FAtualizaEmpresasSelecionadasUsuarioCommand.Parameters[1].Value.SetWideString(pDsResult);
  FAtualizaEmpresasSelecionadasUsuarioCommand.Parameters[2].Value.SetWideString(pCdEmpres);
  FAtualizaEmpresasSelecionadasUsuarioCommand.Parameters[3].Value.SetInt32(pCdUsuari);
  FAtualizaEmpresasSelecionadasUsuarioCommand.ExecuteUpdate;
  pDsResult := FAtualizaEmpresasSelecionadasUsuarioCommand.Parameters[1].Value.GetWideString;
end;

procedure TSDmSisClient.AtualizarLog(pDsCreden: string; var pDsResult: string; pNmTabela: string; pCdUsuari: Integer; pVrChave: string; pTpOperac: Byte; pNmFormul: string; pCdsLog: OleVariant);
begin
  if FAtualizarLogCommand = nil then
  begin
    FAtualizarLogCommand := FDBXConnection.CreateCommand;
    FAtualizarLogCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FAtualizarLogCommand.Text := 'TSDmSis.AtualizarLog';
    FAtualizarLogCommand.Prepare;
  end;
  FAtualizarLogCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FAtualizarLogCommand.Parameters[1].Value.SetWideString(pDsResult);
  FAtualizarLogCommand.Parameters[2].Value.SetWideString(pNmTabela);
  FAtualizarLogCommand.Parameters[3].Value.SetInt32(pCdUsuari);
  FAtualizarLogCommand.Parameters[4].Value.SetWideString(pVrChave);
  FAtualizarLogCommand.Parameters[5].Value.SetUInt8(pTpOperac);
  FAtualizarLogCommand.Parameters[6].Value.SetWideString(pNmFormul);
  FAtualizarLogCommand.Parameters[7].Value.AsVariant := pCdsLog;
  FAtualizarLogCommand.ExecuteUpdate;
  pDsResult := FAtualizarLogCommand.Parameters[1].Value.GetWideString;
end;


constructor TSDmSisClient.Create(ADBXConnection: TDBXConnection);
begin
  inherited Create(ADBXConnection);
end;


constructor TSDmSisClient.Create(ADBXConnection: TDBXConnection; AInstanceOwner: Boolean);
begin
  inherited Create(ADBXConnection, AInstanceOwner);
end;


destructor TSDmSisClient.Destroy;
begin
  FreeAndNil(FSqlConnSisAfterConnectCommand);
  FreeAndNil(FExcluirAtributoIndiceSecundarioCommand);
  FreeAndNil(FExcluirIndiceSecundarioCommand);
  FreeAndNil(FExcluirChaveEstrangeiraCommand);
  FreeAndNil(FExcluirAtributoChaveEstrangeiraCommand);
  FreeAndNil(FExcluirTabelaCommand);
  FreeAndNil(FExcluirAtributoCommand);
  FreeAndNil(FExcluirAtributoPrimarioCommand);
  FreeAndNil(FAtualizarPosicaoChaveCommand);
  FreeAndNil(FExcluirFormularioDinamicoCommand);
  FreeAndNil(FSalvarObjetosFormDinamicoCommand);
  FreeAndNil(FAtribuirProviderFormDinamicoCommand);
  FreeAndNil(FLiberarProviderFormDinamicoCommand);
  FreeAndNil(FOrdemInicialFormDinamicoCommand);
  FreeAndNil(FSalvarConfigConsultaUsuarioCommand);
  FreeAndNil(FSalvarColunasConsultaUsuarioCommand);
  FreeAndNil(FAtualizarUtilizacaoFormularioCommand);
  FreeAndNil(FReorganizarOrdenacaoMenuCommand);
  FreeAndNil(FMoverMenuCommand);
  FreeAndNil(FAtualizaEmpresasSelecionadasUsuarioCommand);
  FreeAndNil(FAtualizarLogCommand);
  inherited;
end;

procedure TSDmTraClient.SqlConnTraAfterConnect(Sender: TObject);
begin
  if FSqlConnTraAfterConnectCommand = nil then
  begin
    FSqlConnTraAfterConnectCommand := FDBXConnection.CreateCommand;
    FSqlConnTraAfterConnectCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FSqlConnTraAfterConnectCommand.Text := 'TSDmTra.SqlConnTraAfterConnect';
    FSqlConnTraAfterConnectCommand.Prepare;
  end;
  if not Assigned(Sender) then
    FSqlConnTraAfterConnectCommand.Parameters[0].Value.SetNull
  else
  begin
    FMarshal := TDBXClientCommand(FSqlConnTraAfterConnectCommand.Parameters[0].ConnectionHandler).GetJSONMarshaler;
    try
      FSqlConnTraAfterConnectCommand.Parameters[0].Value.SetJSONValue(FMarshal.Marshal(Sender), True);
      if FInstanceOwner then
        Sender.Free
    finally
      FreeAndNil(FMarshal)
    end
    end;
  FSqlConnTraAfterConnectCommand.ExecuteUpdate;
end;

procedure TSDmTraClient.SalvarCarga(pDsCreden: string; var pDsResult: string; var pCdCarga: Integer; pDsDesCar: string; pNrMalote: Integer; pTpVeicul: Integer; pKmPreCar: Integer; pDtPreRet: TDateTime; pTpEscolt: Integer; pCdEmpEsc: Integer; pNmFrmOri: string; pStCarga: string; pCdVeicu1: Integer; pCdMotor1: Integer; pCdVeicu2: Integer; pCdMotor2: Integer; pCdVeicu3: Integer; pCdMotor3: Integer; pCdVeicu4: Integer; pCdMotor4: Integer; pCdsCTRC: OleVariant; pCdEmpDes: Integer);
begin
  if FSalvarCargaCommand = nil then
  begin
    FSalvarCargaCommand := FDBXConnection.CreateCommand;
    FSalvarCargaCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FSalvarCargaCommand.Text := 'TSDmTra.SalvarCarga';
    FSalvarCargaCommand.Prepare;
  end;
  FSalvarCargaCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FSalvarCargaCommand.Parameters[1].Value.SetWideString(pDsResult);
  FSalvarCargaCommand.Parameters[2].Value.SetInt32(pCdCarga);
  FSalvarCargaCommand.Parameters[3].Value.SetWideString(pDsDesCar);
  FSalvarCargaCommand.Parameters[4].Value.SetInt32(pNrMalote);
  FSalvarCargaCommand.Parameters[5].Value.SetInt32(pTpVeicul);
  FSalvarCargaCommand.Parameters[6].Value.SetInt32(pKmPreCar);
  FSalvarCargaCommand.Parameters[7].Value.AsDateTime := pDtPreRet;
  FSalvarCargaCommand.Parameters[8].Value.SetInt32(pTpEscolt);
  FSalvarCargaCommand.Parameters[9].Value.SetInt32(pCdEmpEsc);
  FSalvarCargaCommand.Parameters[10].Value.SetWideString(pNmFrmOri);
  FSalvarCargaCommand.Parameters[11].Value.SetWideString(pStCarga);
  FSalvarCargaCommand.Parameters[12].Value.SetInt32(pCdVeicu1);
  FSalvarCargaCommand.Parameters[13].Value.SetInt32(pCdMotor1);
  FSalvarCargaCommand.Parameters[14].Value.SetInt32(pCdVeicu2);
  FSalvarCargaCommand.Parameters[15].Value.SetInt32(pCdMotor2);
  FSalvarCargaCommand.Parameters[16].Value.SetInt32(pCdVeicu3);
  FSalvarCargaCommand.Parameters[17].Value.SetInt32(pCdMotor3);
  FSalvarCargaCommand.Parameters[18].Value.SetInt32(pCdVeicu4);
  FSalvarCargaCommand.Parameters[19].Value.SetInt32(pCdMotor4);
  FSalvarCargaCommand.Parameters[20].Value.AsVariant := pCdsCTRC;
  FSalvarCargaCommand.Parameters[21].Value.SetInt32(pCdEmpDes);
  FSalvarCargaCommand.ExecuteUpdate;
  pDsResult := FSalvarCargaCommand.Parameters[1].Value.GetWideString;
  pCdCarga := FSalvarCargaCommand.Parameters[2].Value.GetInt32;
end;

function TSDmTraClient.ValidarCarga(pDsCreden: string; pCdsParams: OleVariant): AnsiString;
begin
  if FValidarCargaCommand = nil then
  begin
    FValidarCargaCommand := FDBXConnection.CreateCommand;
    FValidarCargaCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FValidarCargaCommand.Text := 'TSDmTra.ValidarCarga';
    FValidarCargaCommand.Prepare;
  end;
  FValidarCargaCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FValidarCargaCommand.Parameters[1].Value.AsVariant := pCdsParams;
  FValidarCargaCommand.ExecuteUpdate;
  Result := FValidarCargaCommand.Parameters[2].Value.GetAnsiString;
end;


constructor TSDmTraClient.Create(ADBXConnection: TDBXConnection);
begin
  inherited Create(ADBXConnection);
end;


constructor TSDmTraClient.Create(ADBXConnection: TDBXConnection; AInstanceOwner: Boolean);
begin
  inherited Create(ADBXConnection, AInstanceOwner);
end;


destructor TSDmTraClient.Destroy;
begin
  FreeAndNil(FSqlConnTraAfterConnectCommand);
  FreeAndNil(FSalvarCargaCommand);
  FreeAndNil(FValidarCargaCommand);
  inherited;
end;

procedure TSDmConClient.SqlConnConAfterConnect(Sender: TObject);
begin
  if FSqlConnConAfterConnectCommand = nil then
  begin
    FSqlConnConAfterConnectCommand := FDBXConnection.CreateCommand;
    FSqlConnConAfterConnectCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FSqlConnConAfterConnectCommand.Text := 'TSDmCon.SqlConnConAfterConnect';
    FSqlConnConAfterConnectCommand.Prepare;
  end;
  if not Assigned(Sender) then
    FSqlConnConAfterConnectCommand.Parameters[0].Value.SetNull
  else
  begin
    FMarshal := TDBXClientCommand(FSqlConnConAfterConnectCommand.Parameters[0].ConnectionHandler).GetJSONMarshaler;
    try
      FSqlConnConAfterConnectCommand.Parameters[0].Value.SetJSONValue(FMarshal.Marshal(Sender), True);
      if FInstanceOwner then
        Sender.Free
    finally
      FreeAndNil(FMarshal)
    end
    end;
  FSqlConnConAfterConnectCommand.ExecuteUpdate;
end;

procedure TSDmConClient.LancamentoPatrimonial(pDsCreden: string; var pDsResult: string; pCdBem: Integer);
begin
  if FLancamentoPatrimonialCommand = nil then
  begin
    FLancamentoPatrimonialCommand := FDBXConnection.CreateCommand;
    FLancamentoPatrimonialCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FLancamentoPatrimonialCommand.Text := 'TSDmCon.LancamentoPatrimonial';
    FLancamentoPatrimonialCommand.Prepare;
  end;
  FLancamentoPatrimonialCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FLancamentoPatrimonialCommand.Parameters[1].Value.SetWideString(pDsResult);
  FLancamentoPatrimonialCommand.Parameters[2].Value.SetInt32(pCdBem);
  FLancamentoPatrimonialCommand.ExecuteUpdate;
  pDsResult := FLancamentoPatrimonialCommand.Parameters[1].Value.GetWideString;
end;

function TSDmConClient.AbrirPlanoContas(pDsCreden: string; var pDsResult: string; pNrPlano: Integer; pTpConta: Integer; pQtNivel: Integer): string;
begin
  if FAbrirPlanoContasCommand = nil then
  begin
    FAbrirPlanoContasCommand := FDBXConnection.CreateCommand;
    FAbrirPlanoContasCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FAbrirPlanoContasCommand.Text := 'TSDmCon.AbrirPlanoContas';
    FAbrirPlanoContasCommand.Prepare;
  end;
  FAbrirPlanoContasCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FAbrirPlanoContasCommand.Parameters[1].Value.SetWideString(pDsResult);
  FAbrirPlanoContasCommand.Parameters[2].Value.SetInt32(pNrPlano);
  FAbrirPlanoContasCommand.Parameters[3].Value.SetInt32(pTpConta);
  FAbrirPlanoContasCommand.Parameters[4].Value.SetInt32(pQtNivel);
  FAbrirPlanoContasCommand.ExecuteUpdate;
  pDsResult := FAbrirPlanoContasCommand.Parameters[1].Value.GetWideString;
  Result := FAbrirPlanoContasCommand.Parameters[5].Value.GetWideString;
end;

procedure TSDmConClient.EfetuarLancamentosContabeis(pDsCreden: string; var pDsResult: string; pNrPlano: Integer; pNmFormul: string; pCdsLancto: OleVariant);
begin
  if FEfetuarLancamentosContabeisCommand = nil then
  begin
    FEfetuarLancamentosContabeisCommand := FDBXConnection.CreateCommand;
    FEfetuarLancamentosContabeisCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FEfetuarLancamentosContabeisCommand.Text := 'TSDmCon.EfetuarLancamentosContabeis';
    FEfetuarLancamentosContabeisCommand.Prepare;
  end;
  FEfetuarLancamentosContabeisCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FEfetuarLancamentosContabeisCommand.Parameters[1].Value.SetWideString(pDsResult);
  FEfetuarLancamentosContabeisCommand.Parameters[2].Value.SetInt32(pNrPlano);
  FEfetuarLancamentosContabeisCommand.Parameters[3].Value.SetWideString(pNmFormul);
  FEfetuarLancamentosContabeisCommand.Parameters[4].Value.AsVariant := pCdsLancto;
  FEfetuarLancamentosContabeisCommand.ExecuteUpdate;
  pDsResult := FEfetuarLancamentosContabeisCommand.Parameters[1].Value.GetWideString;
end;

function TSDmConClient.Balancete(pDsCreden: string; var pDsResult: string; pNrPlano: Integer; pTpConta: Integer; pQtNivel: Integer; pCdEmpres: string; pDtInicio: TDateTime; pDtFinal: TDateTime): string;
begin
  if FBalanceteCommand = nil then
  begin
    FBalanceteCommand := FDBXConnection.CreateCommand;
    FBalanceteCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FBalanceteCommand.Text := 'TSDmCon.Balancete';
    FBalanceteCommand.Prepare;
  end;
  FBalanceteCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FBalanceteCommand.Parameters[1].Value.SetWideString(pDsResult);
  FBalanceteCommand.Parameters[2].Value.SetInt32(pNrPlano);
  FBalanceteCommand.Parameters[3].Value.SetInt32(pTpConta);
  FBalanceteCommand.Parameters[4].Value.SetInt32(pQtNivel);
  FBalanceteCommand.Parameters[5].Value.SetWideString(pCdEmpres);
  FBalanceteCommand.Parameters[6].Value.AsDateTime := pDtInicio;
  FBalanceteCommand.Parameters[7].Value.AsDateTime := pDtFinal;
  FBalanceteCommand.ExecuteUpdate;
  pDsResult := FBalanceteCommand.Parameters[1].Value.GetWideString;
  Result := FBalanceteCommand.Parameters[8].Value.GetWideString;
end;

procedure TSDmConClient.AbreFechaPeriodoContabil(pDsCreden: string; var pDsResult: string; pDtAno: Integer; pDtMes: Integer; pCdEmpres: Integer; pTpOperac: string);
begin
  if FAbreFechaPeriodoContabilCommand = nil then
  begin
    FAbreFechaPeriodoContabilCommand := FDBXConnection.CreateCommand;
    FAbreFechaPeriodoContabilCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FAbreFechaPeriodoContabilCommand.Text := 'TSDmCon.AbreFechaPeriodoContabil';
    FAbreFechaPeriodoContabilCommand.Prepare;
  end;
  FAbreFechaPeriodoContabilCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FAbreFechaPeriodoContabilCommand.Parameters[1].Value.SetWideString(pDsResult);
  FAbreFechaPeriodoContabilCommand.Parameters[2].Value.SetInt32(pDtAno);
  FAbreFechaPeriodoContabilCommand.Parameters[3].Value.SetInt32(pDtMes);
  FAbreFechaPeriodoContabilCommand.Parameters[4].Value.SetInt32(pCdEmpres);
  FAbreFechaPeriodoContabilCommand.Parameters[5].Value.SetWideString(pTpOperac);
  FAbreFechaPeriodoContabilCommand.ExecuteUpdate;
  pDsResult := FAbreFechaPeriodoContabilCommand.Parameters[1].Value.GetWideString;
end;

procedure TSDmConClient.EncerrarPeriodoContabil(pDsCreden: string; var pDsResult: string; pDtAno: Integer; pDtMes: Integer; pCdEmpres: Integer; pNrPlano: Integer);
begin
  if FEncerrarPeriodoContabilCommand = nil then
  begin
    FEncerrarPeriodoContabilCommand := FDBXConnection.CreateCommand;
    FEncerrarPeriodoContabilCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FEncerrarPeriodoContabilCommand.Text := 'TSDmCon.EncerrarPeriodoContabil';
    FEncerrarPeriodoContabilCommand.Prepare;
  end;
  FEncerrarPeriodoContabilCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FEncerrarPeriodoContabilCommand.Parameters[1].Value.SetWideString(pDsResult);
  FEncerrarPeriodoContabilCommand.Parameters[2].Value.SetInt32(pDtAno);
  FEncerrarPeriodoContabilCommand.Parameters[3].Value.SetInt32(pDtMes);
  FEncerrarPeriodoContabilCommand.Parameters[4].Value.SetInt32(pCdEmpres);
  FEncerrarPeriodoContabilCommand.Parameters[5].Value.SetInt32(pNrPlano);
  FEncerrarPeriodoContabilCommand.ExecuteUpdate;
  pDsResult := FEncerrarPeriodoContabilCommand.Parameters[1].Value.GetWideString;
end;


constructor TSDmConClient.Create(ADBXConnection: TDBXConnection);
begin
  inherited Create(ADBXConnection);
end;


constructor TSDmConClient.Create(ADBXConnection: TDBXConnection; AInstanceOwner: Boolean);
begin
  inherited Create(ADBXConnection, AInstanceOwner);
end;


destructor TSDmConClient.Destroy;
begin
  FreeAndNil(FSqlConnConAfterConnectCommand);
  FreeAndNil(FLancamentoPatrimonialCommand);
  FreeAndNil(FAbrirPlanoContasCommand);
  FreeAndNil(FEfetuarLancamentosContabeisCommand);
  FreeAndNil(FBalanceteCommand);
  FreeAndNil(FAbreFechaPeriodoContabilCommand);
  FreeAndNil(FEncerrarPeriodoContabilCommand);
  inherited;
end;

procedure TSDmCRPClient.SqlConnCRPAfterConnect(Sender: TObject);
begin
  if FSqlConnCRPAfterConnectCommand = nil then
  begin
    FSqlConnCRPAfterConnectCommand := FDBXConnection.CreateCommand;
    FSqlConnCRPAfterConnectCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FSqlConnCRPAfterConnectCommand.Text := 'TSDmCRP.SqlConnCRPAfterConnect';
    FSqlConnCRPAfterConnectCommand.Prepare;
  end;
  if not Assigned(Sender) then
    FSqlConnCRPAfterConnectCommand.Parameters[0].Value.SetNull
  else
  begin
    FMarshal := TDBXClientCommand(FSqlConnCRPAfterConnectCommand.Parameters[0].ConnectionHandler).GetJSONMarshaler;
    try
      FSqlConnCRPAfterConnectCommand.Parameters[0].Value.SetJSONValue(FMarshal.Marshal(Sender), True);
      if FInstanceOwner then
        Sender.Free
    finally
      FreeAndNil(FMarshal)
    end
    end;
  FSqlConnCRPAfterConnectCommand.ExecuteUpdate;
end;

function TSDmCRPClient.Faturar(pDsCreden: string; var pDsResult: string; pNmFormul: string; pDtFatura: TDateTime; pCdTitula: Integer; pVrLancto: Double; pCdEmpres: Integer; pNrPreCar: Integer; pNrPlano: Integer; pVrDescon: Double; pCdsN006: OleVariant): Integer;
begin
  if FFaturarCommand = nil then
  begin
    FFaturarCommand := FDBXConnection.CreateCommand;
    FFaturarCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FFaturarCommand.Text := 'TSDmCRP.Faturar';
    FFaturarCommand.Prepare;
  end;
  FFaturarCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FFaturarCommand.Parameters[1].Value.SetWideString(pDsResult);
  FFaturarCommand.Parameters[2].Value.SetWideString(pNmFormul);
  FFaturarCommand.Parameters[3].Value.AsDateTime := pDtFatura;
  FFaturarCommand.Parameters[4].Value.SetInt32(pCdTitula);
  FFaturarCommand.Parameters[5].Value.SetDouble(pVrLancto);
  FFaturarCommand.Parameters[6].Value.SetInt32(pCdEmpres);
  FFaturarCommand.Parameters[7].Value.SetInt32(pNrPreCar);
  FFaturarCommand.Parameters[8].Value.SetInt32(pNrPlano);
  FFaturarCommand.Parameters[9].Value.SetDouble(pVrDescon);
  FFaturarCommand.Parameters[10].Value.AsVariant := pCdsN006;
  FFaturarCommand.ExecuteUpdate;
  pDsResult := FFaturarCommand.Parameters[1].Value.GetWideString;
  Result := FFaturarCommand.Parameters[11].Value.GetInt32;
end;

procedure TSDmCRPClient.LancarContasPagarReceber(pDsCreden: string; var pDsResult: string; pNmFormul: string; pCdHistor: Integer; pTpPagRec: string; pVrLancto: Double; pCdForPag: Integer; pCdTitula: Integer; pCdEmpres: Integer; pNrDocto: Integer; pDtEmissa: TDateTime; pNrPreCar: Integer; pNrOrdem: Integer; pDtVencto: TDateTime; pCdEvento: Integer; pTpPagto: string; pNrConCon: Integer; pDsComHis: string; pNrPlano: Integer);
begin
  if FLancarContasPagarReceberCommand = nil then
  begin
    FLancarContasPagarReceberCommand := FDBXConnection.CreateCommand;
    FLancarContasPagarReceberCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FLancarContasPagarReceberCommand.Text := 'TSDmCRP.LancarContasPagarReceber';
    FLancarContasPagarReceberCommand.Prepare;
  end;
  FLancarContasPagarReceberCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FLancarContasPagarReceberCommand.Parameters[1].Value.SetWideString(pDsResult);
  FLancarContasPagarReceberCommand.Parameters[2].Value.SetWideString(pNmFormul);
  FLancarContasPagarReceberCommand.Parameters[3].Value.SetInt32(pCdHistor);
  FLancarContasPagarReceberCommand.Parameters[4].Value.SetWideString(pTpPagRec);
  FLancarContasPagarReceberCommand.Parameters[5].Value.SetDouble(pVrLancto);
  FLancarContasPagarReceberCommand.Parameters[6].Value.SetInt32(pCdForPag);
  FLancarContasPagarReceberCommand.Parameters[7].Value.SetInt32(pCdTitula);
  FLancarContasPagarReceberCommand.Parameters[8].Value.SetInt32(pCdEmpres);
  FLancarContasPagarReceberCommand.Parameters[9].Value.SetInt32(pNrDocto);
  FLancarContasPagarReceberCommand.Parameters[10].Value.AsDateTime := pDtEmissa;
  FLancarContasPagarReceberCommand.Parameters[11].Value.SetInt32(pNrPreCar);
  FLancarContasPagarReceberCommand.Parameters[12].Value.SetInt32(pNrOrdem);
  FLancarContasPagarReceberCommand.Parameters[13].Value.AsDateTime := pDtVencto;
  FLancarContasPagarReceberCommand.Parameters[14].Value.SetInt32(pCdEvento);
  FLancarContasPagarReceberCommand.Parameters[15].Value.SetWideString(pTpPagto);
  FLancarContasPagarReceberCommand.Parameters[16].Value.SetInt32(pNrConCon);
  FLancarContasPagarReceberCommand.Parameters[17].Value.SetWideString(pDsComHis);
  FLancarContasPagarReceberCommand.Parameters[18].Value.SetInt32(pNrPlano);
  FLancarContasPagarReceberCommand.ExecuteUpdate;
  pDsResult := FLancarContasPagarReceberCommand.Parameters[1].Value.GetWideString;
end;


constructor TSDmCRPClient.Create(ADBXConnection: TDBXConnection);
begin
  inherited Create(ADBXConnection);
end;


constructor TSDmCRPClient.Create(ADBXConnection: TDBXConnection; AInstanceOwner: Boolean);
begin
  inherited Create(ADBXConnection, AInstanceOwner);
end;


destructor TSDmCRPClient.Destroy;
begin
  FreeAndNil(FSqlConnCRPAfterConnectCommand);
  FreeAndNil(FFaturarCommand);
  FreeAndNil(FLancarContasPagarReceberCommand);
  inherited;
end;

procedure TSDmWmsClient.SqlConnWMSAfterConnect(Sender: TObject);
begin
  if FSqlConnWMSAfterConnectCommand = nil then
  begin
    FSqlConnWMSAfterConnectCommand := FDBXConnection.CreateCommand;
    FSqlConnWMSAfterConnectCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FSqlConnWMSAfterConnectCommand.Text := 'TSDmWms.SqlConnWMSAfterConnect';
    FSqlConnWMSAfterConnectCommand.Prepare;
  end;
  if not Assigned(Sender) then
    FSqlConnWMSAfterConnectCommand.Parameters[0].Value.SetNull
  else
  begin
    FMarshal := TDBXClientCommand(FSqlConnWMSAfterConnectCommand.Parameters[0].ConnectionHandler).GetJSONMarshaler;
    try
      FSqlConnWMSAfterConnectCommand.Parameters[0].Value.SetJSONValue(FMarshal.Marshal(Sender), True);
      if FInstanceOwner then
        Sender.Free
    finally
      FreeAndNil(FMarshal)
    end
    end;
  FSqlConnWMSAfterConnectCommand.ExecuteUpdate;
end;

function TSDmWmsClient.ProximaAtividade(pCdUsuari: Integer; var pNrMapa: AnsiString; var pDsAtivid: AnsiString): AnsiString;
begin
  if FProximaAtividadeCommand = nil then
  begin
    FProximaAtividadeCommand := FDBXConnection.CreateCommand;
    FProximaAtividadeCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FProximaAtividadeCommand.Text := 'TSDmWms.ProximaAtividade';
    FProximaAtividadeCommand.Prepare;
  end;
  FProximaAtividadeCommand.Parameters[0].Value.SetInt32(pCdUsuari);
  FProximaAtividadeCommand.Parameters[1].Value.SetAnsiString(pNrMapa);
  FProximaAtividadeCommand.Parameters[2].Value.SetAnsiString(pDsAtivid);
  FProximaAtividadeCommand.ExecuteUpdate;
  pNrMapa := FProximaAtividadeCommand.Parameters[1].Value.GetAnsiString;
  pDsAtivid := FProximaAtividadeCommand.Parameters[2].Value.GetAnsiString;
  Result := FProximaAtividadeCommand.Parameters[3].Value.GetAnsiString;
end;

function TSDmWmsClient.ConferenciaEntradaIniciar(pCdUsuari: Integer; pNrMapa: AnsiString; var pNrNota: AnsiString; var pNrFornec: AnsiString; var pDsLista: AnsiString): AnsiString;
begin
  if FConferenciaEntradaIniciarCommand = nil then
  begin
    FConferenciaEntradaIniciarCommand := FDBXConnection.CreateCommand;
    FConferenciaEntradaIniciarCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FConferenciaEntradaIniciarCommand.Text := 'TSDmWms.ConferenciaEntradaIniciar';
    FConferenciaEntradaIniciarCommand.Prepare;
  end;
  FConferenciaEntradaIniciarCommand.Parameters[0].Value.SetInt32(pCdUsuari);
  FConferenciaEntradaIniciarCommand.Parameters[1].Value.SetAnsiString(pNrMapa);
  FConferenciaEntradaIniciarCommand.Parameters[2].Value.SetAnsiString(pNrNota);
  FConferenciaEntradaIniciarCommand.Parameters[3].Value.SetAnsiString(pNrFornec);
  FConferenciaEntradaIniciarCommand.Parameters[4].Value.SetAnsiString(pDsLista);
  FConferenciaEntradaIniciarCommand.ExecuteUpdate;
  pNrNota := FConferenciaEntradaIniciarCommand.Parameters[2].Value.GetAnsiString;
  pNrFornec := FConferenciaEntradaIniciarCommand.Parameters[3].Value.GetAnsiString;
  pDsLista := FConferenciaEntradaIniciarCommand.Parameters[4].Value.GetAnsiString;
  Result := FConferenciaEntradaIniciarCommand.Parameters[5].Value.GetAnsiString;
end;

function TSDmWmsClient.ConferenciaEntradaFinalizar(pCdUsuari: Integer; pNrMapa: string; pDsLista: string; pSnCancel: string; pQtProSId: Integer): AnsiString;
begin
  if FConferenciaEntradaFinalizarCommand = nil then
  begin
    FConferenciaEntradaFinalizarCommand := FDBXConnection.CreateCommand;
    FConferenciaEntradaFinalizarCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FConferenciaEntradaFinalizarCommand.Text := 'TSDmWms.ConferenciaEntradaFinalizar';
    FConferenciaEntradaFinalizarCommand.Prepare;
  end;
  FConferenciaEntradaFinalizarCommand.Parameters[0].Value.SetInt32(pCdUsuari);
  FConferenciaEntradaFinalizarCommand.Parameters[1].Value.SetWideString(pNrMapa);
  FConferenciaEntradaFinalizarCommand.Parameters[2].Value.SetWideString(pDsLista);
  FConferenciaEntradaFinalizarCommand.Parameters[3].Value.SetWideString(pSnCancel);
  FConferenciaEntradaFinalizarCommand.Parameters[4].Value.SetInt32(pQtProSId);
  FConferenciaEntradaFinalizarCommand.ExecuteUpdate;
  Result := FConferenciaEntradaFinalizarCommand.Parameters[5].Value.GetAnsiString;
end;

function TSDmWmsClient.ConferenciaSaidaIniciar(pCdUsuari: Integer; pNrMapa: AnsiString; var pNrNota: AnsiString; var pNrFornec: AnsiString; var pDsLista: AnsiString): AnsiString;
begin
  if FConferenciaSaidaIniciarCommand = nil then
  begin
    FConferenciaSaidaIniciarCommand := FDBXConnection.CreateCommand;
    FConferenciaSaidaIniciarCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FConferenciaSaidaIniciarCommand.Text := 'TSDmWms.ConferenciaSaidaIniciar';
    FConferenciaSaidaIniciarCommand.Prepare;
  end;
  FConferenciaSaidaIniciarCommand.Parameters[0].Value.SetInt32(pCdUsuari);
  FConferenciaSaidaIniciarCommand.Parameters[1].Value.SetAnsiString(pNrMapa);
  FConferenciaSaidaIniciarCommand.Parameters[2].Value.SetAnsiString(pNrNota);
  FConferenciaSaidaIniciarCommand.Parameters[3].Value.SetAnsiString(pNrFornec);
  FConferenciaSaidaIniciarCommand.Parameters[4].Value.SetAnsiString(pDsLista);
  FConferenciaSaidaIniciarCommand.ExecuteUpdate;
  pNrNota := FConferenciaSaidaIniciarCommand.Parameters[2].Value.GetAnsiString;
  pNrFornec := FConferenciaSaidaIniciarCommand.Parameters[3].Value.GetAnsiString;
  pDsLista := FConferenciaSaidaIniciarCommand.Parameters[4].Value.GetAnsiString;
  Result := FConferenciaSaidaIniciarCommand.Parameters[5].Value.GetAnsiString;
end;

function TSDmWmsClient.ConferenciaSaidaFinalizar(pCdUsuari: Integer; pNrMapa: string; pDsLista: string; pCdClient: string; pSnCancel: string; pQtProSId: Integer): AnsiString;
begin
  if FConferenciaSaidaFinalizarCommand = nil then
  begin
    FConferenciaSaidaFinalizarCommand := FDBXConnection.CreateCommand;
    FConferenciaSaidaFinalizarCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FConferenciaSaidaFinalizarCommand.Text := 'TSDmWms.ConferenciaSaidaFinalizar';
    FConferenciaSaidaFinalizarCommand.Prepare;
  end;
  FConferenciaSaidaFinalizarCommand.Parameters[0].Value.SetInt32(pCdUsuari);
  FConferenciaSaidaFinalizarCommand.Parameters[1].Value.SetWideString(pNrMapa);
  FConferenciaSaidaFinalizarCommand.Parameters[2].Value.SetWideString(pDsLista);
  FConferenciaSaidaFinalizarCommand.Parameters[3].Value.SetWideString(pCdClient);
  FConferenciaSaidaFinalizarCommand.Parameters[4].Value.SetWideString(pSnCancel);
  FConferenciaSaidaFinalizarCommand.Parameters[5].Value.SetInt32(pQtProSId);
  FConferenciaSaidaFinalizarCommand.ExecuteUpdate;
  Result := FConferenciaSaidaFinalizarCommand.Parameters[6].Value.GetAnsiString;
end;

function TSDmWmsClient.GravarOperacaoLogistica(pDsCreden: AnsiString; pCdUsuari: Integer; pNmFormul: AnsiString; pCdArmaze: Integer; pCdTipOpe: Integer; pCdsW005: OleVariant; pCdsW006: OleVariant; pCdsW007: OleVariant): AnsiString;
begin
  if FGravarOperacaoLogisticaCommand = nil then
  begin
    FGravarOperacaoLogisticaCommand := FDBXConnection.CreateCommand;
    FGravarOperacaoLogisticaCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FGravarOperacaoLogisticaCommand.Text := 'TSDmWms.GravarOperacaoLogistica';
    FGravarOperacaoLogisticaCommand.Prepare;
  end;
  FGravarOperacaoLogisticaCommand.Parameters[0].Value.SetAnsiString(pDsCreden);
  FGravarOperacaoLogisticaCommand.Parameters[1].Value.SetInt32(pCdUsuari);
  FGravarOperacaoLogisticaCommand.Parameters[2].Value.SetAnsiString(pNmFormul);
  FGravarOperacaoLogisticaCommand.Parameters[3].Value.SetInt32(pCdArmaze);
  FGravarOperacaoLogisticaCommand.Parameters[4].Value.SetInt32(pCdTipOpe);
  FGravarOperacaoLogisticaCommand.Parameters[5].Value.AsVariant := pCdsW005;
  FGravarOperacaoLogisticaCommand.Parameters[6].Value.AsVariant := pCdsW006;
  FGravarOperacaoLogisticaCommand.Parameters[7].Value.AsVariant := pCdsW007;
  FGravarOperacaoLogisticaCommand.ExecuteUpdate;
  Result := FGravarOperacaoLogisticaCommand.Parameters[8].Value.GetAnsiString;
end;

function TSDmWmsClient.GravaPrioridadeSituacaoOpeLog(pCdArmaze: AnsiString; PDsListOp: AnsiString): AnsiString;
begin
  if FGravaPrioridadeSituacaoOpeLogCommand = nil then
  begin
    FGravaPrioridadeSituacaoOpeLogCommand := FDBXConnection.CreateCommand;
    FGravaPrioridadeSituacaoOpeLogCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FGravaPrioridadeSituacaoOpeLogCommand.Text := 'TSDmWms.GravaPrioridadeSituacaoOpeLog';
    FGravaPrioridadeSituacaoOpeLogCommand.Prepare;
  end;
  FGravaPrioridadeSituacaoOpeLogCommand.Parameters[0].Value.SetAnsiString(pCdArmaze);
  FGravaPrioridadeSituacaoOpeLogCommand.Parameters[1].Value.SetAnsiString(PDsListOp);
  FGravaPrioridadeSituacaoOpeLogCommand.ExecuteUpdate;
  Result := FGravaPrioridadeSituacaoOpeLogCommand.Parameters[2].Value.GetAnsiString;
end;

function TSDmWmsClient.RefazerAtividade(pCdArmaze: AnsiString; pNrOpeLog: AnsiString): AnsiString;
begin
  if FRefazerAtividadeCommand = nil then
  begin
    FRefazerAtividadeCommand := FDBXConnection.CreateCommand;
    FRefazerAtividadeCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FRefazerAtividadeCommand.Text := 'TSDmWms.RefazerAtividade';
    FRefazerAtividadeCommand.Prepare;
  end;
  FRefazerAtividadeCommand.Parameters[0].Value.SetAnsiString(pCdArmaze);
  FRefazerAtividadeCommand.Parameters[1].Value.SetAnsiString(pNrOpeLog);
  FRefazerAtividadeCommand.ExecuteUpdate;
  Result := FRefazerAtividadeCommand.Parameters[2].Value.GetAnsiString;
end;


constructor TSDmWmsClient.Create(ADBXConnection: TDBXConnection);
begin
  inherited Create(ADBXConnection);
end;


constructor TSDmWmsClient.Create(ADBXConnection: TDBXConnection; AInstanceOwner: Boolean);
begin
  inherited Create(ADBXConnection, AInstanceOwner);
end;


destructor TSDmWmsClient.Destroy;
begin
  FreeAndNil(FSqlConnWMSAfterConnectCommand);
  FreeAndNil(FProximaAtividadeCommand);
  FreeAndNil(FConferenciaEntradaIniciarCommand);
  FreeAndNil(FConferenciaEntradaFinalizarCommand);
  FreeAndNil(FConferenciaSaidaIniciarCommand);
  FreeAndNil(FConferenciaSaidaFinalizarCommand);
  FreeAndNil(FGravarOperacaoLogisticaCommand);
  FreeAndNil(FGravaPrioridadeSituacaoOpeLogCommand);
  FreeAndNil(FRefazerAtividadeCommand);
  inherited;
end;

end.


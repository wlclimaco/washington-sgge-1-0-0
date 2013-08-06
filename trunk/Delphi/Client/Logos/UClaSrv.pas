//
// Created by the DataSnap proxy generator.
// 25/4/2013 10:05:52
//

unit UClaSrv;

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
    FConsultaConhecimentosCommand: TDBXCommand;
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
    function ConsultaConhecimentos(pDsCreden: string; var pDsResult: string; pCdEmpres: string; pCdRemete: string; pCdDestin: string; pCdCarga: string; pCdVeicul: string; pCdMotori: string; pDtEmiIni: string; pDtEmiFim: string; pCdCtrc: string; pNrNota: string; pNrFatura: string; pNrRps: string; pTpCarga: string; pCdTomado: string): OleVariant;
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
    FAtualizarLogScreenCommand: TDBXCommand;
    FAtualizarPosicaoAtributoCommand: TDBXCommand;
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
    procedure AtualizarLogScreen(pDsCreden: string; var pDsResult: string; pCdUsuari: Integer; pNmFormul: string; pDtLogScr: string; pCdsLog: OleVariant);
    function AtualizarPosicaoAtributo(pDsCreden: string; pNmTabela: string; pNrNewPos: Integer; pNrOldPos: Integer): AnsiString;
  end;

  TSDmTraClient = class(TDSAdminClient)
  private
    FSqlConnTraAfterConnectCommand: TDBXCommand;
    FSalvarCargaCommand: TDBXCommand;
    FValidarCargaCommand: TDBXCommand;
    FCancelarCargaCommand: TDBXCommand;
    FGravarDadosCargaCommand: TDBXCommand;
    FGravarOcorrenciaMotoristaCommand: TDBXCommand;
    FProximaCargaCommand: TDBXCommand;
    FGravaDataEntregaCTRCCommand: TDBXCommand;
    FGravaContaCorrenteMotoristaCommand: TDBXCommand;
    FGravarAcertoCargaCommand: TDBXCommand;
  public
    constructor Create(ADBXConnection: TDBXConnection); overload;
    constructor Create(ADBXConnection: TDBXConnection; AInstanceOwner: Boolean); overload;
    destructor Destroy; override;
    procedure SqlConnTraAfterConnect(Sender: TObject);
    procedure SalvarCarga(pDsCreden: string; var pDsResult: string; var pCdCarga: Integer; pCdsCarga: OleVariant);
    function ValidarCarga(pDsCreden: AnsiString; pTpVeicul: AnsiString; pCdVeicul: AnsiString; pQtPesCtr: Double; pDsComWhe: AnsiString): AnsiString;
    function CancelarCarga(pDsCreden: AnsiString; pCdCarga: Integer; pNmFormul: AnsiString): AnsiString;
    function GravarDadosCarga(pDsCreden: AnsiString; pCdsCarga: OleVariant; pDsColuna: AnsiString; pNmFormul: AnsiString): AnsiString;
    function GravarOcorrenciaMotorista(pDsCreden: AnsiString; pCdsOcorre: OleVariant): AnsiString;
    function ProximaCarga: Integer;
    function GravaDataEntregaCTRC(pDsCreden: AnsiString; pCdsCTRC: OleVariant): AnsiString;
    function GravaContaCorrenteMotorista(pDsCreden: AnsiString; pCdsT019: OleVariant; pCdsLancto: OleVariant; pNrPlano: Integer; pNmFormul: AnsiString): AnsiString;
    function GravarAcertoCarga(pDsCreden: string; pCdsParams: string): AnsiString;
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
    FPlanoContasEmpresaCommand: TDBXCommand;
  public
    constructor Create(ADBXConnection: TDBXConnection); overload;
    constructor Create(ADBXConnection: TDBXConnection; AInstanceOwner: Boolean); overload;
    destructor Destroy; override;
    procedure SqlConnConAfterConnect(Sender: TObject);
    procedure LancamentoPatrimonial(pDsCreden: string; var pDsResult: string; pCdBem: Integer);
    function AbrirPlanoContas(pDsCreden: string; var pDsResult: string; pNrPlano: Integer; pTpConta: Integer; pQtNivel: Integer; pCdEmpres: Integer): string;
    procedure EfetuarLancamentosContabeis(pDsCreden: string; var pDsResult: string; pNrPlano: Integer; pNmFormul: string; pCdsLancto: OleVariant);
    function Balancete(pDsCreden: string; var pDsResult: string; pNrPlano: Integer; pTpConta: Integer; pQtNivel: Integer; pCdEmpres: string; pDtInicio: TDateTime; pDtFinal: TDateTime): string;
    procedure AbreFechaPeriodoContabil(pDsCreden: string; var pDsResult: string; pDtAno: Integer; pDtMes: Integer; pCdEmpres: Integer; pTpOperac: string);
    procedure EncerrarPeriodoContabil(pDsCreden: string; var pDsResult: string; pDtAno: Integer; pDtMes: Integer; pCdEmpres: Integer; pNrPlano: Integer);
    function PlanoContasEmpresa(pDsCreden: string; var pDsResult: string; pCdEmpres: Integer; pDtRefere: TDateTime): Integer;
  end;

  TSDmWmsClient = class(TDSAdminClient)
  private
    FSqlConnWMSAfterConnectCommand: TDBXCommand;
    FTestaConexaoCommand: TDBXCommand;
    FAutenticaUsuarioColetorCommand: TDBXCommand;
    FProximaAtividadeCommand: TDBXCommand;
    FAtividadeNaoConvocadaCommand: TDBXCommand;
    FConsultaAtividadeCommand: TDBXCommand;
    FConferenciaEntradaIniciarCommand: TDBXCommand;
    FConferenciaEntradaFinalizarCommand: TDBXCommand;
    FConferenciaSaidaIniciarCommand: TDBXCommand;
    FConferenciaSaidaFinalizarCommand: TDBXCommand;
    FGravarOperacaoLogisticaCommand: TDBXCommand;
    FGravaPrioridadeSituacaoOpeLogCommand: TDBXCommand;
    FRefazerAtividadeCommand: TDBXCommand;
    FCancelarOpeLogCommand: TDBXCommand;
    FGravarOpeLogEndereCommand: TDBXCommand;
    FConsultaPalletEnderecamentoCommand: TDBXCommand;
    FFechaPalletEnderecamentoCommand: TDBXCommand;
  public
    constructor Create(ADBXConnection: TDBXConnection); overload;
    constructor Create(ADBXConnection: TDBXConnection; AInstanceOwner: Boolean); overload;
    destructor Destroy; override;
    procedure SqlConnWMSAfterConnect(Sender: TObject);
    function TestaConexao(pIdTeste: AnsiString; pDsMensag: AnsiString): AnsiString;
    function AutenticaUsuarioColetor(pDsLogin: string; var pCdSenha: Integer; var pCdUsuari: Integer; var pNmUsuari: string): Boolean;
    function ProximaAtividade(pCdUsuari: Integer; var pNrMapa: AnsiString; var pDsAtivid: AnsiString): AnsiString;
    function AtividadeNaoConvocada(pCdUsuari: Integer; var pDsLista: AnsiString): AnsiString;
    function ConsultaAtividade(pNrFornec: AnsiString; pSgTipAti: AnsiString; var pNrOpeLog: AnsiString): AnsiString;
    function ConferenciaEntradaIniciar(pCdUsuari: Integer; pNrMapa: AnsiString; var pNrNota: AnsiString; var pNrFornec: AnsiString; var pDsLista: AnsiString): AnsiString;
    function ConferenciaEntradaFinalizar(pCdUsuari: Integer; pNrMapa: string; pDsLista: string; pSnCancel: string; pQtProSId: Integer): AnsiString;
    function ConferenciaSaidaIniciar(pCdUsuari: Integer; pNrMapa: AnsiString; var pNrNota: AnsiString; var pNrFornec: AnsiString; var pDsLista: AnsiString): AnsiString;
    function ConferenciaSaidaFinalizar(pCdUsuari: Integer; pNrMapa: string; pDsLista: string; pCdClient: string; pSnCancel: string; pQtProSId: Integer): AnsiString;
    function GravarOperacaoLogistica(pDsCreden: AnsiString; pCdUsuari: Integer; pNmFormul: AnsiString; pCdArmaze: Integer; pCdTipOpe: Integer; pCdsW005: OleVariant; pCdsW006: OleVariant; pCdsW007: OleVariant): AnsiString;
    function GravaPrioridadeSituacaoOpeLog(pCdArmaze: AnsiString; PDsListOp: AnsiString): AnsiString;
    function RefazerAtividade(pCdArmaze: AnsiString; pNrOpeLog: AnsiString): AnsiString;
    function CancelarOpeLog(pNrOpeLog: AnsiString; pCdUsuari: AnsiString): AnsiString;
    function GravarOpeLogEndere(pDsCreden: AnsiString; pDtParam: OleVariant): AnsiString;
    function ConsultaPalletEnderecamento(pCdUsuari: Integer; pNrPallet: AnsiString; var pDsLista: AnsiString): AnsiString;
    function FechaPalletEnderecamento(pLsPallet: AnsiString): AnsiString;
  end;

  TSDmAdmClient = class(TDSAdminClient)
  private
    FGravarPreLancamentoNFEntradaCommand: TDBXCommand;
    FFinalizarPreLancamentoNFEntradaCommand: TDBXCommand;
    FAutorizaPreLancamentoNFEntradaCommand: TDBXCommand;
    FMontaLotePreLancamentoNFEntradaCommand: TDBXCommand;
    FRecepcionaLotePreLancamentoNFEntradaCommand: TDBXCommand;
    FPeriodoContabilValidoCommand: TDBXCommand;
    FFinalizarDigitacaoNotaCommand: TDBXCommand;
    FEfetuarLancamentosContabeisCommand: TDBXCommand;
    FGravarSaldoPlanoCommand: TDBXCommand;
    FBaixarDocumentoCommand: TDBXCommand;
    FFaturarCommand: TDBXCommand;
    FLancarContasPagarReceberCommand: TDBXCommand;
    FBuscarNFeManifestoCommand: TDBXCommand;
    FGravarRNCCommand: TDBXCommand;
    FGravarDisposicaoRNCCommand: TDBXCommand;
    FGravarRevisoesDataEntregaCommand: TDBXCommand;
    FLiberarDesmontarCargaCommand: TDBXCommand;
    FAtualizarOperacaoArmazemCommand: TDBXCommand;
    FAtualizarChavesCommand: TDBXCommand;
    FValidarChaveLiberacaoCommand: TDBXCommand;
    FCancelarNotaFiscalCommand: TDBXCommand;
    FGravarXMLNotaFiscalCommand: TDBXCommand;
  public
    constructor Create(ADBXConnection: TDBXConnection); overload;
    constructor Create(ADBXConnection: TDBXConnection; AInstanceOwner: Boolean); overload;
    destructor Destroy; override;
    function GravarPreLancamentoNFEntrada(pDsCreden: AnsiString; pDtF013: OleVariant; pDtF014: OleVariant; pDtF015: OleVariant; pDtF017: OleVariant): AnsiString;
    function FinalizarPreLancamentoNFEntrada(pDsCreden: AnsiString; pNrPleLan: Integer): AnsiString;
    function AutorizaPreLancamentoNFEntrada(pDsCreden: AnsiString; pDtF013: OleVariant): AnsiString;
    function MontaLotePreLancamentoNFEntrada(pDsCreden: AnsiString; pDtF013: OleVariant; pNrLote: Integer; pDsLote: AnsiString): AnsiString;
    function RecepcionaLotePreLancamentoNFEntrada(pDsCreden: AnsiString; pDtF012: OleVariant): AnsiString;
    function PeriodoContabilValido(pDsCreden: AnsiString; pDtF018: OleVariant): AnsiString;
    function FinalizarDigitacaoNota(pDsCreden: AnsiString; pDtF001: OleVariant; pDtF002: OleVariant; pDtN002: OleVariant; pDtN003: OleVariant; pDtParCon: OleVariant): AnsiString;
    function EfetuarLancamentosContabeis(pDsCreden: string; pDtParCon: OleVariant): AnsiString;
    function GravarSaldoPlano(pDsCreden: string; pDtSalCon: OleVariant; pNmFormul: string): AnsiString;
    function BaixarDocumento(pDsCreden: string; pTpOperac: string; pDtN002: OleVariant; pDtParCon: OleVariant; pCdHistor: Integer; pDtBaixa: TDateTime): AnsiString;
    function Faturar(pDsCreden: string; pDtParam: OleVariant): OleVariant;
    function LancarContasPagarReceber(pDsCreden: string; pNmFormul: string; pCdHistor: Integer; pTpPagRec: string; pVrLancto: Double; pCdForPag: Integer; pCdTitula: Integer; pCdEmpres: Integer; pNrDocto: Integer; pDtEmissa: TDateTime; pNrPreCar: Integer; pNrOrdem: Integer; pDtVencto: TDateTime; pCdEvento: Integer; pTpPagto: string; pNrConCon: Integer; pDsComHis: string; pCdCenCus: Integer; pNrPlano: Integer): AnsiString;
    function BuscarNFeManifesto(pDsCreden: string; opStatus: Integer; NrChadoc: string; cnpj: string; NrCertif: string; NrSenCer: string; CdEstado: string; CdEmpres: string): string;
    function GravarRNC(pDsCreden: string; pDtParam: OleVariant): AnsiString;
    function GravarDisposicaoRNC(pDsCreden: string; pDtParam: OleVariant): string;
    function GravarRevisoesDataEntrega(pDsCreden: string; pDtParam: OleVariant): AnsiString;
    function LiberarDesmontarCarga(pDsCreden: string; pDtParam: OleVariant): AnsiString;
    function AtualizarOperacaoArmazem(pDsCreden: string; pDtParam: OleVariant): AnsiString;
    function AtualizarChaves(pDsCreden: string; pDtParam: OleVariant): AnsiString;
    function ValidarChaveLiberacao(pDsCreden: string; pDtParam: OleVariant): AnsiString;
    function CancelarNotaFiscal(pDsCreden: string; pDtParam: OleVariant): AnsiString;
    function GravarXMLNotaFiscal(pDsCreden: string; pDtParam: OleVariant): AnsiString;
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

function TSDmRWClient.ConsultaConhecimentos(pDsCreden: string; var pDsResult: string; pCdEmpres: string; pCdRemete: string; pCdDestin: string; pCdCarga: string; pCdVeicul: string; pCdMotori: string; pDtEmiIni: string; pDtEmiFim: string; pCdCtrc: string; pNrNota: string; pNrFatura: string; pNrRps: string; pTpCarga: string; pCdTomado: string): OleVariant;
begin
  if FConsultaConhecimentosCommand = nil then
  begin
    FConsultaConhecimentosCommand := FDBXConnection.CreateCommand;
    FConsultaConhecimentosCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FConsultaConhecimentosCommand.Text := 'TSDmRW.ConsultaConhecimentos';
    FConsultaConhecimentosCommand.Prepare;
  end;
  FConsultaConhecimentosCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FConsultaConhecimentosCommand.Parameters[1].Value.SetWideString(pDsResult);
  FConsultaConhecimentosCommand.Parameters[2].Value.SetWideString(pCdEmpres);
  FConsultaConhecimentosCommand.Parameters[3].Value.SetWideString(pCdRemete);
  FConsultaConhecimentosCommand.Parameters[4].Value.SetWideString(pCdDestin);
  FConsultaConhecimentosCommand.Parameters[5].Value.SetWideString(pCdCarga);
  FConsultaConhecimentosCommand.Parameters[6].Value.SetWideString(pCdVeicul);
  FConsultaConhecimentosCommand.Parameters[7].Value.SetWideString(pCdMotori);
  FConsultaConhecimentosCommand.Parameters[8].Value.SetWideString(pDtEmiIni);
  FConsultaConhecimentosCommand.Parameters[9].Value.SetWideString(pDtEmiFim);
  FConsultaConhecimentosCommand.Parameters[10].Value.SetWideString(pCdCtrc);
  FConsultaConhecimentosCommand.Parameters[11].Value.SetWideString(pNrNota);
  FConsultaConhecimentosCommand.Parameters[12].Value.SetWideString(pNrFatura);
  FConsultaConhecimentosCommand.Parameters[13].Value.SetWideString(pNrRps);
  FConsultaConhecimentosCommand.Parameters[14].Value.SetWideString(pTpCarga);
  FConsultaConhecimentosCommand.Parameters[15].Value.SetWideString(pCdTomado);
  FConsultaConhecimentosCommand.ExecuteUpdate;
  pDsResult := FConsultaConhecimentosCommand.Parameters[1].Value.GetWideString;
  Result := FConsultaConhecimentosCommand.Parameters[16].Value.AsVariant;
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
  FreeAndNil(FConsultaConhecimentosCommand);
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

procedure TSDmSisClient.AtualizarLogScreen(pDsCreden: string; var pDsResult: string; pCdUsuari: Integer; pNmFormul: string; pDtLogScr: string; pCdsLog: OleVariant);
begin
  if FAtualizarLogScreenCommand = nil then
  begin
    FAtualizarLogScreenCommand := FDBXConnection.CreateCommand;
    FAtualizarLogScreenCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FAtualizarLogScreenCommand.Text := 'TSDmSis.AtualizarLogScreen';
    FAtualizarLogScreenCommand.Prepare;
  end;
  FAtualizarLogScreenCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FAtualizarLogScreenCommand.Parameters[1].Value.SetWideString(pDsResult);
  FAtualizarLogScreenCommand.Parameters[2].Value.SetInt32(pCdUsuari);
  FAtualizarLogScreenCommand.Parameters[3].Value.SetWideString(pNmFormul);
  FAtualizarLogScreenCommand.Parameters[4].Value.SetWideString(pDtLogScr);
  FAtualizarLogScreenCommand.Parameters[5].Value.AsVariant := pCdsLog;
  FAtualizarLogScreenCommand.ExecuteUpdate;
  pDsResult := FAtualizarLogScreenCommand.Parameters[1].Value.GetWideString;
end;

function TSDmSisClient.AtualizarPosicaoAtributo(pDsCreden: string; pNmTabela: string; pNrNewPos: Integer; pNrOldPos: Integer): AnsiString;
begin
  if FAtualizarPosicaoAtributoCommand = nil then
  begin
    FAtualizarPosicaoAtributoCommand := FDBXConnection.CreateCommand;
    FAtualizarPosicaoAtributoCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FAtualizarPosicaoAtributoCommand.Text := 'TSDmSis.AtualizarPosicaoAtributo';
    FAtualizarPosicaoAtributoCommand.Prepare;
  end;
  FAtualizarPosicaoAtributoCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FAtualizarPosicaoAtributoCommand.Parameters[1].Value.SetWideString(pNmTabela);
  FAtualizarPosicaoAtributoCommand.Parameters[2].Value.SetInt32(pNrNewPos);
  FAtualizarPosicaoAtributoCommand.Parameters[3].Value.SetInt32(pNrOldPos);
  FAtualizarPosicaoAtributoCommand.ExecuteUpdate;
  Result := FAtualizarPosicaoAtributoCommand.Parameters[4].Value.GetAnsiString;
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
  FreeAndNil(FAtualizarLogScreenCommand);
  FreeAndNil(FAtualizarPosicaoAtributoCommand);
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

procedure TSDmTraClient.SalvarCarga(pDsCreden: string; var pDsResult: string; var pCdCarga: Integer; pCdsCarga: OleVariant);
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
  FSalvarCargaCommand.Parameters[3].Value.AsVariant := pCdsCarga;
  FSalvarCargaCommand.ExecuteUpdate;
  pDsResult := FSalvarCargaCommand.Parameters[1].Value.GetWideString;
  pCdCarga := FSalvarCargaCommand.Parameters[2].Value.GetInt32;
end;

function TSDmTraClient.ValidarCarga(pDsCreden: AnsiString; pTpVeicul: AnsiString; pCdVeicul: AnsiString; pQtPesCtr: Double; pDsComWhe: AnsiString): AnsiString;
begin
  if FValidarCargaCommand = nil then
  begin
    FValidarCargaCommand := FDBXConnection.CreateCommand;
    FValidarCargaCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FValidarCargaCommand.Text := 'TSDmTra.ValidarCarga';
    FValidarCargaCommand.Prepare;
  end;
  FValidarCargaCommand.Parameters[0].Value.SetAnsiString(pDsCreden);
  FValidarCargaCommand.Parameters[1].Value.SetAnsiString(pTpVeicul);
  FValidarCargaCommand.Parameters[2].Value.SetAnsiString(pCdVeicul);
  FValidarCargaCommand.Parameters[3].Value.SetDouble(pQtPesCtr);
  FValidarCargaCommand.Parameters[4].Value.SetAnsiString(pDsComWhe);
  FValidarCargaCommand.ExecuteUpdate;
  Result := FValidarCargaCommand.Parameters[5].Value.GetAnsiString;
end;

function TSDmTraClient.CancelarCarga(pDsCreden: AnsiString; pCdCarga: Integer; pNmFormul: AnsiString): AnsiString;
begin
  if FCancelarCargaCommand = nil then
  begin
    FCancelarCargaCommand := FDBXConnection.CreateCommand;
    FCancelarCargaCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FCancelarCargaCommand.Text := 'TSDmTra.CancelarCarga';
    FCancelarCargaCommand.Prepare;
  end;
  FCancelarCargaCommand.Parameters[0].Value.SetAnsiString(pDsCreden);
  FCancelarCargaCommand.Parameters[1].Value.SetInt32(pCdCarga);
  FCancelarCargaCommand.Parameters[2].Value.SetAnsiString(pNmFormul);
  FCancelarCargaCommand.ExecuteUpdate;
  Result := FCancelarCargaCommand.Parameters[3].Value.GetAnsiString;
end;

function TSDmTraClient.GravarDadosCarga(pDsCreden: AnsiString; pCdsCarga: OleVariant; pDsColuna: AnsiString; pNmFormul: AnsiString): AnsiString;
begin
  if FGravarDadosCargaCommand = nil then
  begin
    FGravarDadosCargaCommand := FDBXConnection.CreateCommand;
    FGravarDadosCargaCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FGravarDadosCargaCommand.Text := 'TSDmTra.GravarDadosCarga';
    FGravarDadosCargaCommand.Prepare;
  end;
  FGravarDadosCargaCommand.Parameters[0].Value.SetAnsiString(pDsCreden);
  FGravarDadosCargaCommand.Parameters[1].Value.AsVariant := pCdsCarga;
  FGravarDadosCargaCommand.Parameters[2].Value.SetAnsiString(pDsColuna);
  FGravarDadosCargaCommand.Parameters[3].Value.SetAnsiString(pNmFormul);
  FGravarDadosCargaCommand.ExecuteUpdate;
  Result := FGravarDadosCargaCommand.Parameters[4].Value.GetAnsiString;
end;

function TSDmTraClient.GravarOcorrenciaMotorista(pDsCreden: AnsiString; pCdsOcorre: OleVariant): AnsiString;
begin
  if FGravarOcorrenciaMotoristaCommand = nil then
  begin
    FGravarOcorrenciaMotoristaCommand := FDBXConnection.CreateCommand;
    FGravarOcorrenciaMotoristaCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FGravarOcorrenciaMotoristaCommand.Text := 'TSDmTra.GravarOcorrenciaMotorista';
    FGravarOcorrenciaMotoristaCommand.Prepare;
  end;
  FGravarOcorrenciaMotoristaCommand.Parameters[0].Value.SetAnsiString(pDsCreden);
  FGravarOcorrenciaMotoristaCommand.Parameters[1].Value.AsVariant := pCdsOcorre;
  FGravarOcorrenciaMotoristaCommand.ExecuteUpdate;
  Result := FGravarOcorrenciaMotoristaCommand.Parameters[2].Value.GetAnsiString;
end;

function TSDmTraClient.ProximaCarga: Integer;
begin
  if FProximaCargaCommand = nil then
  begin
    FProximaCargaCommand := FDBXConnection.CreateCommand;
    FProximaCargaCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FProximaCargaCommand.Text := 'TSDmTra.ProximaCarga';
    FProximaCargaCommand.Prepare;
  end;
  FProximaCargaCommand.ExecuteUpdate;
  Result := FProximaCargaCommand.Parameters[0].Value.GetInt32;
end;

function TSDmTraClient.GravaDataEntregaCTRC(pDsCreden: AnsiString; pCdsCTRC: OleVariant): AnsiString;
begin
  if FGravaDataEntregaCTRCCommand = nil then
  begin
    FGravaDataEntregaCTRCCommand := FDBXConnection.CreateCommand;
    FGravaDataEntregaCTRCCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FGravaDataEntregaCTRCCommand.Text := 'TSDmTra.GravaDataEntregaCTRC';
    FGravaDataEntregaCTRCCommand.Prepare;
  end;
  FGravaDataEntregaCTRCCommand.Parameters[0].Value.SetAnsiString(pDsCreden);
  FGravaDataEntregaCTRCCommand.Parameters[1].Value.AsVariant := pCdsCTRC;
  FGravaDataEntregaCTRCCommand.ExecuteUpdate;
  Result := FGravaDataEntregaCTRCCommand.Parameters[2].Value.GetAnsiString;
end;

function TSDmTraClient.GravaContaCorrenteMotorista(pDsCreden: AnsiString; pCdsT019: OleVariant; pCdsLancto: OleVariant; pNrPlano: Integer; pNmFormul: AnsiString): AnsiString;
begin
  if FGravaContaCorrenteMotoristaCommand = nil then
  begin
    FGravaContaCorrenteMotoristaCommand := FDBXConnection.CreateCommand;
    FGravaContaCorrenteMotoristaCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FGravaContaCorrenteMotoristaCommand.Text := 'TSDmTra.GravaContaCorrenteMotorista';
    FGravaContaCorrenteMotoristaCommand.Prepare;
  end;
  FGravaContaCorrenteMotoristaCommand.Parameters[0].Value.SetAnsiString(pDsCreden);
  FGravaContaCorrenteMotoristaCommand.Parameters[1].Value.AsVariant := pCdsT019;
  FGravaContaCorrenteMotoristaCommand.Parameters[2].Value.AsVariant := pCdsLancto;
  FGravaContaCorrenteMotoristaCommand.Parameters[3].Value.SetInt32(pNrPlano);
  FGravaContaCorrenteMotoristaCommand.Parameters[4].Value.SetAnsiString(pNmFormul);
  FGravaContaCorrenteMotoristaCommand.ExecuteUpdate;
  Result := FGravaContaCorrenteMotoristaCommand.Parameters[5].Value.GetAnsiString;
end;

function TSDmTraClient.GravarAcertoCarga(pDsCreden: string; pCdsParams: string): AnsiString;
begin
  if FGravarAcertoCargaCommand = nil then
  begin
    FGravarAcertoCargaCommand := FDBXConnection.CreateCommand;
    FGravarAcertoCargaCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FGravarAcertoCargaCommand.Text := 'TSDmTra.GravarAcertoCarga';
    FGravarAcertoCargaCommand.Prepare;
  end;
  FGravarAcertoCargaCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FGravarAcertoCargaCommand.Parameters[1].Value.SetWideString(pCdsParams);
  FGravarAcertoCargaCommand.ExecuteUpdate;
  Result := FGravarAcertoCargaCommand.Parameters[2].Value.GetAnsiString;
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
  FreeAndNil(FCancelarCargaCommand);
  FreeAndNil(FGravarDadosCargaCommand);
  FreeAndNil(FGravarOcorrenciaMotoristaCommand);
  FreeAndNil(FProximaCargaCommand);
  FreeAndNil(FGravaDataEntregaCTRCCommand);
  FreeAndNil(FGravaContaCorrenteMotoristaCommand);
  FreeAndNil(FGravarAcertoCargaCommand);
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

function TSDmConClient.AbrirPlanoContas(pDsCreden: string; var pDsResult: string; pNrPlano: Integer; pTpConta: Integer; pQtNivel: Integer; pCdEmpres: Integer): string;
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
  FAbrirPlanoContasCommand.Parameters[5].Value.SetInt32(pCdEmpres);
  FAbrirPlanoContasCommand.ExecuteUpdate;
  pDsResult := FAbrirPlanoContasCommand.Parameters[1].Value.GetWideString;
  Result := FAbrirPlanoContasCommand.Parameters[6].Value.GetWideString;
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

function TSDmConClient.PlanoContasEmpresa(pDsCreden: string; var pDsResult: string; pCdEmpres: Integer; pDtRefere: TDateTime): Integer;
begin
  if FPlanoContasEmpresaCommand = nil then
  begin
    FPlanoContasEmpresaCommand := FDBXConnection.CreateCommand;
    FPlanoContasEmpresaCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FPlanoContasEmpresaCommand.Text := 'TSDmCon.PlanoContasEmpresa';
    FPlanoContasEmpresaCommand.Prepare;
  end;
  FPlanoContasEmpresaCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FPlanoContasEmpresaCommand.Parameters[1].Value.SetWideString(pDsResult);
  FPlanoContasEmpresaCommand.Parameters[2].Value.SetInt32(pCdEmpres);
  FPlanoContasEmpresaCommand.Parameters[3].Value.AsDateTime := pDtRefere;
  FPlanoContasEmpresaCommand.ExecuteUpdate;
  pDsResult := FPlanoContasEmpresaCommand.Parameters[1].Value.GetWideString;
  Result := FPlanoContasEmpresaCommand.Parameters[4].Value.GetInt32;
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
  FreeAndNil(FPlanoContasEmpresaCommand);
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

function TSDmWmsClient.TestaConexao(pIdTeste: AnsiString; pDsMensag: AnsiString): AnsiString;
begin
  if FTestaConexaoCommand = nil then
  begin
    FTestaConexaoCommand := FDBXConnection.CreateCommand;
    FTestaConexaoCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FTestaConexaoCommand.Text := 'TSDmWms.TestaConexao';
    FTestaConexaoCommand.Prepare;
  end;
  FTestaConexaoCommand.Parameters[0].Value.SetAnsiString(pIdTeste);
  FTestaConexaoCommand.Parameters[1].Value.SetAnsiString(pDsMensag);
  FTestaConexaoCommand.ExecuteUpdate;
  Result := FTestaConexaoCommand.Parameters[2].Value.GetAnsiString;
end;

function TSDmWmsClient.AutenticaUsuarioColetor(pDsLogin: string; var pCdSenha: Integer; var pCdUsuari: Integer; var pNmUsuari: string): Boolean;
begin
  if FAutenticaUsuarioColetorCommand = nil then
  begin
    FAutenticaUsuarioColetorCommand := FDBXConnection.CreateCommand;
    FAutenticaUsuarioColetorCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FAutenticaUsuarioColetorCommand.Text := 'TSDmWms.AutenticaUsuarioColetor';
    FAutenticaUsuarioColetorCommand.Prepare;
  end;
  FAutenticaUsuarioColetorCommand.Parameters[0].Value.SetWideString(pDsLogin);
  FAutenticaUsuarioColetorCommand.Parameters[1].Value.SetInt32(pCdSenha);
  FAutenticaUsuarioColetorCommand.Parameters[2].Value.SetInt32(pCdUsuari);
  FAutenticaUsuarioColetorCommand.Parameters[3].Value.SetWideString(pNmUsuari);
  FAutenticaUsuarioColetorCommand.ExecuteUpdate;
  pCdSenha := FAutenticaUsuarioColetorCommand.Parameters[1].Value.GetInt32;
  pCdUsuari := FAutenticaUsuarioColetorCommand.Parameters[2].Value.GetInt32;
  pNmUsuari := FAutenticaUsuarioColetorCommand.Parameters[3].Value.GetWideString;
  Result := FAutenticaUsuarioColetorCommand.Parameters[4].Value.GetBoolean;
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

function TSDmWmsClient.AtividadeNaoConvocada(pCdUsuari: Integer; var pDsLista: AnsiString): AnsiString;
begin
  if FAtividadeNaoConvocadaCommand = nil then
  begin
    FAtividadeNaoConvocadaCommand := FDBXConnection.CreateCommand;
    FAtividadeNaoConvocadaCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FAtividadeNaoConvocadaCommand.Text := 'TSDmWms.AtividadeNaoConvocada';
    FAtividadeNaoConvocadaCommand.Prepare;
  end;
  FAtividadeNaoConvocadaCommand.Parameters[0].Value.SetInt32(pCdUsuari);
  FAtividadeNaoConvocadaCommand.Parameters[1].Value.SetAnsiString(pDsLista);
  FAtividadeNaoConvocadaCommand.ExecuteUpdate;
  pDsLista := FAtividadeNaoConvocadaCommand.Parameters[1].Value.GetAnsiString;
  Result := FAtividadeNaoConvocadaCommand.Parameters[2].Value.GetAnsiString;
end;

function TSDmWmsClient.ConsultaAtividade(pNrFornec: AnsiString; pSgTipAti: AnsiString; var pNrOpeLog: AnsiString): AnsiString;
begin
  if FConsultaAtividadeCommand = nil then
  begin
    FConsultaAtividadeCommand := FDBXConnection.CreateCommand;
    FConsultaAtividadeCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FConsultaAtividadeCommand.Text := 'TSDmWms.ConsultaAtividade';
    FConsultaAtividadeCommand.Prepare;
  end;
  FConsultaAtividadeCommand.Parameters[0].Value.SetAnsiString(pNrFornec);
  FConsultaAtividadeCommand.Parameters[1].Value.SetAnsiString(pSgTipAti);
  FConsultaAtividadeCommand.Parameters[2].Value.SetAnsiString(pNrOpeLog);
  FConsultaAtividadeCommand.ExecuteUpdate;
  pNrOpeLog := FConsultaAtividadeCommand.Parameters[2].Value.GetAnsiString;
  Result := FConsultaAtividadeCommand.Parameters[3].Value.GetAnsiString;
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

function TSDmWmsClient.CancelarOpeLog(pNrOpeLog: AnsiString; pCdUsuari: AnsiString): AnsiString;
begin
  if FCancelarOpeLogCommand = nil then
  begin
    FCancelarOpeLogCommand := FDBXConnection.CreateCommand;
    FCancelarOpeLogCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FCancelarOpeLogCommand.Text := 'TSDmWms.CancelarOpeLog';
    FCancelarOpeLogCommand.Prepare;
  end;
  FCancelarOpeLogCommand.Parameters[0].Value.SetAnsiString(pNrOpeLog);
  FCancelarOpeLogCommand.Parameters[1].Value.SetAnsiString(pCdUsuari);
  FCancelarOpeLogCommand.ExecuteUpdate;
  Result := FCancelarOpeLogCommand.Parameters[2].Value.GetAnsiString;
end;

function TSDmWmsClient.GravarOpeLogEndere(pDsCreden: AnsiString; pDtParam: OleVariant): AnsiString;
begin
  if FGravarOpeLogEndereCommand = nil then
  begin
    FGravarOpeLogEndereCommand := FDBXConnection.CreateCommand;
    FGravarOpeLogEndereCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FGravarOpeLogEndereCommand.Text := 'TSDmWms.GravarOpeLogEndere';
    FGravarOpeLogEndereCommand.Prepare;
  end;
  FGravarOpeLogEndereCommand.Parameters[0].Value.SetAnsiString(pDsCreden);
  FGravarOpeLogEndereCommand.Parameters[1].Value.AsVariant := pDtParam;
  FGravarOpeLogEndereCommand.ExecuteUpdate;
  Result := FGravarOpeLogEndereCommand.Parameters[2].Value.GetAnsiString;
end;

function TSDmWmsClient.ConsultaPalletEnderecamento(pCdUsuari: Integer; pNrPallet: AnsiString; var pDsLista: AnsiString): AnsiString;
begin
  if FConsultaPalletEnderecamentoCommand = nil then
  begin
    FConsultaPalletEnderecamentoCommand := FDBXConnection.CreateCommand;
    FConsultaPalletEnderecamentoCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FConsultaPalletEnderecamentoCommand.Text := 'TSDmWms.ConsultaPalletEnderecamento';
    FConsultaPalletEnderecamentoCommand.Prepare;
  end;
  FConsultaPalletEnderecamentoCommand.Parameters[0].Value.SetInt32(pCdUsuari);
  FConsultaPalletEnderecamentoCommand.Parameters[1].Value.SetAnsiString(pNrPallet);
  FConsultaPalletEnderecamentoCommand.Parameters[2].Value.SetAnsiString(pDsLista);
  FConsultaPalletEnderecamentoCommand.ExecuteUpdate;
  pDsLista := FConsultaPalletEnderecamentoCommand.Parameters[2].Value.GetAnsiString;
  Result := FConsultaPalletEnderecamentoCommand.Parameters[3].Value.GetAnsiString;
end;

function TSDmWmsClient.FechaPalletEnderecamento(pLsPallet: AnsiString): AnsiString;
begin
  if FFechaPalletEnderecamentoCommand = nil then
  begin
    FFechaPalletEnderecamentoCommand := FDBXConnection.CreateCommand;
    FFechaPalletEnderecamentoCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FFechaPalletEnderecamentoCommand.Text := 'TSDmWms.FechaPalletEnderecamento';
    FFechaPalletEnderecamentoCommand.Prepare;
  end;
  FFechaPalletEnderecamentoCommand.Parameters[0].Value.SetAnsiString(pLsPallet);
  FFechaPalletEnderecamentoCommand.ExecuteUpdate;
  Result := FFechaPalletEnderecamentoCommand.Parameters[1].Value.GetAnsiString;
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
  FreeAndNil(FTestaConexaoCommand);
  FreeAndNil(FAutenticaUsuarioColetorCommand);
  FreeAndNil(FProximaAtividadeCommand);
  FreeAndNil(FAtividadeNaoConvocadaCommand);
  FreeAndNil(FConsultaAtividadeCommand);
  FreeAndNil(FConferenciaEntradaIniciarCommand);
  FreeAndNil(FConferenciaEntradaFinalizarCommand);
  FreeAndNil(FConferenciaSaidaIniciarCommand);
  FreeAndNil(FConferenciaSaidaFinalizarCommand);
  FreeAndNil(FGravarOperacaoLogisticaCommand);
  FreeAndNil(FGravaPrioridadeSituacaoOpeLogCommand);
  FreeAndNil(FRefazerAtividadeCommand);
  FreeAndNil(FCancelarOpeLogCommand);
  FreeAndNil(FGravarOpeLogEndereCommand);
  FreeAndNil(FConsultaPalletEnderecamentoCommand);
  FreeAndNil(FFechaPalletEnderecamentoCommand);
  inherited;
end;

function TSDmAdmClient.GravarPreLancamentoNFEntrada(pDsCreden: AnsiString; pDtF013: OleVariant; pDtF014: OleVariant; pDtF015: OleVariant; pDtF017: OleVariant): AnsiString;
begin
  if FGravarPreLancamentoNFEntradaCommand = nil then
  begin
    FGravarPreLancamentoNFEntradaCommand := FDBXConnection.CreateCommand;
    FGravarPreLancamentoNFEntradaCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FGravarPreLancamentoNFEntradaCommand.Text := 'TSDmAdm.GravarPreLancamentoNFEntrada';
    FGravarPreLancamentoNFEntradaCommand.Prepare;
  end;
  FGravarPreLancamentoNFEntradaCommand.Parameters[0].Value.SetAnsiString(pDsCreden);
  FGravarPreLancamentoNFEntradaCommand.Parameters[1].Value.AsVariant := pDtF013;
  FGravarPreLancamentoNFEntradaCommand.Parameters[2].Value.AsVariant := pDtF014;
  FGravarPreLancamentoNFEntradaCommand.Parameters[3].Value.AsVariant := pDtF015;
  FGravarPreLancamentoNFEntradaCommand.Parameters[4].Value.AsVariant := pDtF017;
  FGravarPreLancamentoNFEntradaCommand.ExecuteUpdate;
  Result := FGravarPreLancamentoNFEntradaCommand.Parameters[5].Value.GetAnsiString;
end;

function TSDmAdmClient.FinalizarPreLancamentoNFEntrada(pDsCreden: AnsiString; pNrPleLan: Integer): AnsiString;
begin
  if FFinalizarPreLancamentoNFEntradaCommand = nil then
  begin
    FFinalizarPreLancamentoNFEntradaCommand := FDBXConnection.CreateCommand;
    FFinalizarPreLancamentoNFEntradaCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FFinalizarPreLancamentoNFEntradaCommand.Text := 'TSDmAdm.FinalizarPreLancamentoNFEntrada';
    FFinalizarPreLancamentoNFEntradaCommand.Prepare;
  end;
  FFinalizarPreLancamentoNFEntradaCommand.Parameters[0].Value.SetAnsiString(pDsCreden);
  FFinalizarPreLancamentoNFEntradaCommand.Parameters[1].Value.SetInt32(pNrPleLan);
  FFinalizarPreLancamentoNFEntradaCommand.ExecuteUpdate;
  Result := FFinalizarPreLancamentoNFEntradaCommand.Parameters[2].Value.GetAnsiString;
end;

function TSDmAdmClient.AutorizaPreLancamentoNFEntrada(pDsCreden: AnsiString; pDtF013: OleVariant): AnsiString;
begin
  if FAutorizaPreLancamentoNFEntradaCommand = nil then
  begin
    FAutorizaPreLancamentoNFEntradaCommand := FDBXConnection.CreateCommand;
    FAutorizaPreLancamentoNFEntradaCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FAutorizaPreLancamentoNFEntradaCommand.Text := 'TSDmAdm.AutorizaPreLancamentoNFEntrada';
    FAutorizaPreLancamentoNFEntradaCommand.Prepare;
  end;
  FAutorizaPreLancamentoNFEntradaCommand.Parameters[0].Value.SetAnsiString(pDsCreden);
  FAutorizaPreLancamentoNFEntradaCommand.Parameters[1].Value.AsVariant := pDtF013;
  FAutorizaPreLancamentoNFEntradaCommand.ExecuteUpdate;
  Result := FAutorizaPreLancamentoNFEntradaCommand.Parameters[2].Value.GetAnsiString;
end;

function TSDmAdmClient.MontaLotePreLancamentoNFEntrada(pDsCreden: AnsiString; pDtF013: OleVariant; pNrLote: Integer; pDsLote: AnsiString): AnsiString;
begin
  if FMontaLotePreLancamentoNFEntradaCommand = nil then
  begin
    FMontaLotePreLancamentoNFEntradaCommand := FDBXConnection.CreateCommand;
    FMontaLotePreLancamentoNFEntradaCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FMontaLotePreLancamentoNFEntradaCommand.Text := 'TSDmAdm.MontaLotePreLancamentoNFEntrada';
    FMontaLotePreLancamentoNFEntradaCommand.Prepare;
  end;
  FMontaLotePreLancamentoNFEntradaCommand.Parameters[0].Value.SetAnsiString(pDsCreden);
  FMontaLotePreLancamentoNFEntradaCommand.Parameters[1].Value.AsVariant := pDtF013;
  FMontaLotePreLancamentoNFEntradaCommand.Parameters[2].Value.SetInt32(pNrLote);
  FMontaLotePreLancamentoNFEntradaCommand.Parameters[3].Value.SetAnsiString(pDsLote);
  FMontaLotePreLancamentoNFEntradaCommand.ExecuteUpdate;
  Result := FMontaLotePreLancamentoNFEntradaCommand.Parameters[4].Value.GetAnsiString;
end;

function TSDmAdmClient.RecepcionaLotePreLancamentoNFEntrada(pDsCreden: AnsiString; pDtF012: OleVariant): AnsiString;
begin
  if FRecepcionaLotePreLancamentoNFEntradaCommand = nil then
  begin
    FRecepcionaLotePreLancamentoNFEntradaCommand := FDBXConnection.CreateCommand;
    FRecepcionaLotePreLancamentoNFEntradaCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FRecepcionaLotePreLancamentoNFEntradaCommand.Text := 'TSDmAdm.RecepcionaLotePreLancamentoNFEntrada';
    FRecepcionaLotePreLancamentoNFEntradaCommand.Prepare;
  end;
  FRecepcionaLotePreLancamentoNFEntradaCommand.Parameters[0].Value.SetAnsiString(pDsCreden);
  FRecepcionaLotePreLancamentoNFEntradaCommand.Parameters[1].Value.AsVariant := pDtF012;
  FRecepcionaLotePreLancamentoNFEntradaCommand.ExecuteUpdate;
  Result := FRecepcionaLotePreLancamentoNFEntradaCommand.Parameters[2].Value.GetAnsiString;
end;

function TSDmAdmClient.PeriodoContabilValido(pDsCreden: AnsiString; pDtF018: OleVariant): AnsiString;
begin
  if FPeriodoContabilValidoCommand = nil then
  begin
    FPeriodoContabilValidoCommand := FDBXConnection.CreateCommand;
    FPeriodoContabilValidoCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FPeriodoContabilValidoCommand.Text := 'TSDmAdm.PeriodoContabilValido';
    FPeriodoContabilValidoCommand.Prepare;
  end;
  FPeriodoContabilValidoCommand.Parameters[0].Value.SetAnsiString(pDsCreden);
  FPeriodoContabilValidoCommand.Parameters[1].Value.AsVariant := pDtF018;
  FPeriodoContabilValidoCommand.ExecuteUpdate;
  Result := FPeriodoContabilValidoCommand.Parameters[2].Value.GetAnsiString;
end;

function TSDmAdmClient.FinalizarDigitacaoNota(pDsCreden: AnsiString; pDtF001: OleVariant; pDtF002: OleVariant; pDtN002: OleVariant; pDtN003: OleVariant; pDtParCon: OleVariant): AnsiString;
begin
  if FFinalizarDigitacaoNotaCommand = nil then
  begin
    FFinalizarDigitacaoNotaCommand := FDBXConnection.CreateCommand;
    FFinalizarDigitacaoNotaCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FFinalizarDigitacaoNotaCommand.Text := 'TSDmAdm.FinalizarDigitacaoNota';
    FFinalizarDigitacaoNotaCommand.Prepare;
  end;
  FFinalizarDigitacaoNotaCommand.Parameters[0].Value.SetAnsiString(pDsCreden);
  FFinalizarDigitacaoNotaCommand.Parameters[1].Value.AsVariant := pDtF001;
  FFinalizarDigitacaoNotaCommand.Parameters[2].Value.AsVariant := pDtF002;
  FFinalizarDigitacaoNotaCommand.Parameters[3].Value.AsVariant := pDtN002;
  FFinalizarDigitacaoNotaCommand.Parameters[4].Value.AsVariant := pDtN003;
  FFinalizarDigitacaoNotaCommand.Parameters[5].Value.AsVariant := pDtParCon;
  FFinalizarDigitacaoNotaCommand.ExecuteUpdate;
  Result := FFinalizarDigitacaoNotaCommand.Parameters[6].Value.GetAnsiString;
end;

function TSDmAdmClient.EfetuarLancamentosContabeis(pDsCreden: string; pDtParCon: OleVariant): AnsiString;
begin
  if FEfetuarLancamentosContabeisCommand = nil then
  begin
    FEfetuarLancamentosContabeisCommand := FDBXConnection.CreateCommand;
    FEfetuarLancamentosContabeisCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FEfetuarLancamentosContabeisCommand.Text := 'TSDmAdm.EfetuarLancamentosContabeis';
    FEfetuarLancamentosContabeisCommand.Prepare;
  end;
  FEfetuarLancamentosContabeisCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FEfetuarLancamentosContabeisCommand.Parameters[1].Value.AsVariant := pDtParCon;
  FEfetuarLancamentosContabeisCommand.ExecuteUpdate;
  Result := FEfetuarLancamentosContabeisCommand.Parameters[2].Value.GetAnsiString;
end;

function TSDmAdmClient.GravarSaldoPlano(pDsCreden: string; pDtSalCon: OleVariant; pNmFormul: string): AnsiString;
begin
  if FGravarSaldoPlanoCommand = nil then
  begin
    FGravarSaldoPlanoCommand := FDBXConnection.CreateCommand;
    FGravarSaldoPlanoCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FGravarSaldoPlanoCommand.Text := 'TSDmAdm.GravarSaldoPlano';
    FGravarSaldoPlanoCommand.Prepare;
  end;
  FGravarSaldoPlanoCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FGravarSaldoPlanoCommand.Parameters[1].Value.AsVariant := pDtSalCon;
  FGravarSaldoPlanoCommand.Parameters[2].Value.SetWideString(pNmFormul);
  FGravarSaldoPlanoCommand.ExecuteUpdate;
  Result := FGravarSaldoPlanoCommand.Parameters[3].Value.GetAnsiString;
end;

function TSDmAdmClient.BaixarDocumento(pDsCreden: string; pTpOperac: string; pDtN002: OleVariant; pDtParCon: OleVariant; pCdHistor: Integer; pDtBaixa: TDateTime): AnsiString;
begin
  if FBaixarDocumentoCommand = nil then
  begin
    FBaixarDocumentoCommand := FDBXConnection.CreateCommand;
    FBaixarDocumentoCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FBaixarDocumentoCommand.Text := 'TSDmAdm.BaixarDocumento';
    FBaixarDocumentoCommand.Prepare;
  end;
  FBaixarDocumentoCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FBaixarDocumentoCommand.Parameters[1].Value.SetWideString(pTpOperac);
  FBaixarDocumentoCommand.Parameters[2].Value.AsVariant := pDtN002;
  FBaixarDocumentoCommand.Parameters[3].Value.AsVariant := pDtParCon;
  FBaixarDocumentoCommand.Parameters[4].Value.SetInt32(pCdHistor);
  FBaixarDocumentoCommand.Parameters[5].Value.AsDateTime := pDtBaixa;
  FBaixarDocumentoCommand.ExecuteUpdate;
  Result := FBaixarDocumentoCommand.Parameters[6].Value.GetAnsiString;
end;

function TSDmAdmClient.Faturar(pDsCreden: string; pDtParam: OleVariant): OleVariant;
begin
  if FFaturarCommand = nil then
  begin
    FFaturarCommand := FDBXConnection.CreateCommand;
    FFaturarCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FFaturarCommand.Text := 'TSDmAdm.Faturar';
    FFaturarCommand.Prepare;
  end;
  FFaturarCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FFaturarCommand.Parameters[1].Value.AsVariant := pDtParam;
  FFaturarCommand.ExecuteUpdate;
  Result := FFaturarCommand.Parameters[2].Value.AsVariant;
end;

function TSDmAdmClient.LancarContasPagarReceber(pDsCreden: string; pNmFormul: string; pCdHistor: Integer; pTpPagRec: string; pVrLancto: Double; pCdForPag: Integer; pCdTitula: Integer; pCdEmpres: Integer; pNrDocto: Integer; pDtEmissa: TDateTime; pNrPreCar: Integer; pNrOrdem: Integer; pDtVencto: TDateTime; pCdEvento: Integer; pTpPagto: string; pNrConCon: Integer; pDsComHis: string; pCdCenCus: Integer; pNrPlano: Integer): AnsiString;
begin
  if FLancarContasPagarReceberCommand = nil then
  begin
    FLancarContasPagarReceberCommand := FDBXConnection.CreateCommand;
    FLancarContasPagarReceberCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FLancarContasPagarReceberCommand.Text := 'TSDmAdm.LancarContasPagarReceber';
    FLancarContasPagarReceberCommand.Prepare;
  end;
  FLancarContasPagarReceberCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FLancarContasPagarReceberCommand.Parameters[1].Value.SetWideString(pNmFormul);
  FLancarContasPagarReceberCommand.Parameters[2].Value.SetInt32(pCdHistor);
  FLancarContasPagarReceberCommand.Parameters[3].Value.SetWideString(pTpPagRec);
  FLancarContasPagarReceberCommand.Parameters[4].Value.SetDouble(pVrLancto);
  FLancarContasPagarReceberCommand.Parameters[5].Value.SetInt32(pCdForPag);
  FLancarContasPagarReceberCommand.Parameters[6].Value.SetInt32(pCdTitula);
  FLancarContasPagarReceberCommand.Parameters[7].Value.SetInt32(pCdEmpres);
  FLancarContasPagarReceberCommand.Parameters[8].Value.SetInt32(pNrDocto);
  FLancarContasPagarReceberCommand.Parameters[9].Value.AsDateTime := pDtEmissa;
  FLancarContasPagarReceberCommand.Parameters[10].Value.SetInt32(pNrPreCar);
  FLancarContasPagarReceberCommand.Parameters[11].Value.SetInt32(pNrOrdem);
  FLancarContasPagarReceberCommand.Parameters[12].Value.AsDateTime := pDtVencto;
  FLancarContasPagarReceberCommand.Parameters[13].Value.SetInt32(pCdEvento);
  FLancarContasPagarReceberCommand.Parameters[14].Value.SetWideString(pTpPagto);
  FLancarContasPagarReceberCommand.Parameters[15].Value.SetInt32(pNrConCon);
  FLancarContasPagarReceberCommand.Parameters[16].Value.SetWideString(pDsComHis);
  FLancarContasPagarReceberCommand.Parameters[17].Value.SetInt32(pCdCenCus);
  FLancarContasPagarReceberCommand.Parameters[18].Value.SetInt32(pNrPlano);
  FLancarContasPagarReceberCommand.ExecuteUpdate;
  Result := FLancarContasPagarReceberCommand.Parameters[19].Value.GetAnsiString;
end;

function TSDmAdmClient.BuscarNFeManifesto(pDsCreden: string; opStatus: Integer; NrChadoc: string; cnpj: string; NrCertif: string; NrSenCer: string; CdEstado: string; CdEmpres: string): string;
begin
  if FBuscarNFeManifestoCommand = nil then
  begin
    FBuscarNFeManifestoCommand := FDBXConnection.CreateCommand;
    FBuscarNFeManifestoCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FBuscarNFeManifestoCommand.Text := 'TSDmAdm.BuscarNFeManifesto';
    FBuscarNFeManifestoCommand.Prepare;
  end;
  FBuscarNFeManifestoCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FBuscarNFeManifestoCommand.Parameters[1].Value.SetInt32(opStatus);
  FBuscarNFeManifestoCommand.Parameters[2].Value.SetWideString(NrChadoc);
  FBuscarNFeManifestoCommand.Parameters[3].Value.SetWideString(cnpj);
  FBuscarNFeManifestoCommand.Parameters[4].Value.SetWideString(NrCertif);
  FBuscarNFeManifestoCommand.Parameters[5].Value.SetWideString(NrSenCer);
  FBuscarNFeManifestoCommand.Parameters[6].Value.SetWideString(CdEstado);
  FBuscarNFeManifestoCommand.Parameters[7].Value.SetWideString(CdEmpres);
  FBuscarNFeManifestoCommand.ExecuteUpdate;
  Result := FBuscarNFeManifestoCommand.Parameters[8].Value.GetWideString;
end;

function TSDmAdmClient.GravarRNC(pDsCreden: string; pDtParam: OleVariant): AnsiString;
begin
  if FGravarRNCCommand = nil then
  begin
    FGravarRNCCommand := FDBXConnection.CreateCommand;
    FGravarRNCCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FGravarRNCCommand.Text := 'TSDmAdm.GravarRNC';
    FGravarRNCCommand.Prepare;
  end;
  FGravarRNCCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FGravarRNCCommand.Parameters[1].Value.AsVariant := pDtParam;
  FGravarRNCCommand.ExecuteUpdate;
  Result := FGravarRNCCommand.Parameters[2].Value.GetAnsiString;
end;

function TSDmAdmClient.GravarDisposicaoRNC(pDsCreden: string; pDtParam: OleVariant): string;
begin
  if FGravarDisposicaoRNCCommand = nil then
  begin
    FGravarDisposicaoRNCCommand := FDBXConnection.CreateCommand;
    FGravarDisposicaoRNCCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FGravarDisposicaoRNCCommand.Text := 'TSDmAdm.GravarDisposicaoRNC';
    FGravarDisposicaoRNCCommand.Prepare;
  end;
  FGravarDisposicaoRNCCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FGravarDisposicaoRNCCommand.Parameters[1].Value.AsVariant := pDtParam;
  FGravarDisposicaoRNCCommand.ExecuteUpdate;
  Result := FGravarDisposicaoRNCCommand.Parameters[2].Value.GetWideString;
end;

function TSDmAdmClient.GravarRevisoesDataEntrega(pDsCreden: string; pDtParam: OleVariant): AnsiString;
begin
  if FGravarRevisoesDataEntregaCommand = nil then
  begin
    FGravarRevisoesDataEntregaCommand := FDBXConnection.CreateCommand;
    FGravarRevisoesDataEntregaCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FGravarRevisoesDataEntregaCommand.Text := 'TSDmAdm.GravarRevisoesDataEntrega';
    FGravarRevisoesDataEntregaCommand.Prepare;
  end;
  FGravarRevisoesDataEntregaCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FGravarRevisoesDataEntregaCommand.Parameters[1].Value.AsVariant := pDtParam;
  FGravarRevisoesDataEntregaCommand.ExecuteUpdate;
  Result := FGravarRevisoesDataEntregaCommand.Parameters[2].Value.GetAnsiString;
end;

function TSDmAdmClient.LiberarDesmontarCarga(pDsCreden: string; pDtParam: OleVariant): AnsiString;
begin
  if FLiberarDesmontarCargaCommand = nil then
  begin
    FLiberarDesmontarCargaCommand := FDBXConnection.CreateCommand;
    FLiberarDesmontarCargaCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FLiberarDesmontarCargaCommand.Text := 'TSDmAdm.LiberarDesmontarCarga';
    FLiberarDesmontarCargaCommand.Prepare;
  end;
  FLiberarDesmontarCargaCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FLiberarDesmontarCargaCommand.Parameters[1].Value.AsVariant := pDtParam;
  FLiberarDesmontarCargaCommand.ExecuteUpdate;
  Result := FLiberarDesmontarCargaCommand.Parameters[2].Value.GetAnsiString;
end;

function TSDmAdmClient.AtualizarOperacaoArmazem(pDsCreden: string; pDtParam: OleVariant): AnsiString;
begin
  if FAtualizarOperacaoArmazemCommand = nil then
  begin
    FAtualizarOperacaoArmazemCommand := FDBXConnection.CreateCommand;
    FAtualizarOperacaoArmazemCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FAtualizarOperacaoArmazemCommand.Text := 'TSDmAdm.AtualizarOperacaoArmazem';
    FAtualizarOperacaoArmazemCommand.Prepare;
  end;
  FAtualizarOperacaoArmazemCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FAtualizarOperacaoArmazemCommand.Parameters[1].Value.AsVariant := pDtParam;
  FAtualizarOperacaoArmazemCommand.ExecuteUpdate;
  Result := FAtualizarOperacaoArmazemCommand.Parameters[2].Value.GetAnsiString;
end;

function TSDmAdmClient.AtualizarChaves(pDsCreden: string; pDtParam: OleVariant): AnsiString;
begin
  if FAtualizarChavesCommand = nil then
  begin
    FAtualizarChavesCommand := FDBXConnection.CreateCommand;
    FAtualizarChavesCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FAtualizarChavesCommand.Text := 'TSDmAdm.AtualizarChaves';
    FAtualizarChavesCommand.Prepare;
  end;
  FAtualizarChavesCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FAtualizarChavesCommand.Parameters[1].Value.AsVariant := pDtParam;
  FAtualizarChavesCommand.ExecuteUpdate;
  Result := FAtualizarChavesCommand.Parameters[2].Value.GetAnsiString;
end;

function TSDmAdmClient.ValidarChaveLiberacao(pDsCreden: string; pDtParam: OleVariant): AnsiString;
begin
  if FValidarChaveLiberacaoCommand = nil then
  begin
    FValidarChaveLiberacaoCommand := FDBXConnection.CreateCommand;
    FValidarChaveLiberacaoCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FValidarChaveLiberacaoCommand.Text := 'TSDmAdm.ValidarChaveLiberacao';
    FValidarChaveLiberacaoCommand.Prepare;
  end;
  FValidarChaveLiberacaoCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FValidarChaveLiberacaoCommand.Parameters[1].Value.AsVariant := pDtParam;
  FValidarChaveLiberacaoCommand.ExecuteUpdate;
  Result := FValidarChaveLiberacaoCommand.Parameters[2].Value.GetAnsiString;
end;

function TSDmAdmClient.CancelarNotaFiscal(pDsCreden: string; pDtParam: OleVariant): AnsiString;
begin
  if FCancelarNotaFiscalCommand = nil then
  begin
    FCancelarNotaFiscalCommand := FDBXConnection.CreateCommand;
    FCancelarNotaFiscalCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FCancelarNotaFiscalCommand.Text := 'TSDmAdm.CancelarNotaFiscal';
    FCancelarNotaFiscalCommand.Prepare;
  end;
  FCancelarNotaFiscalCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FCancelarNotaFiscalCommand.Parameters[1].Value.AsVariant := pDtParam;
  FCancelarNotaFiscalCommand.ExecuteUpdate;
  Result := FCancelarNotaFiscalCommand.Parameters[2].Value.GetAnsiString;
end;

function TSDmAdmClient.GravarXMLNotaFiscal(pDsCreden: string; pDtParam: OleVariant): AnsiString;
begin
  if FGravarXMLNotaFiscalCommand = nil then
  begin
    FGravarXMLNotaFiscalCommand := FDBXConnection.CreateCommand;
    FGravarXMLNotaFiscalCommand.CommandType := TDBXCommandTypes.DSServerMethod;
    FGravarXMLNotaFiscalCommand.Text := 'TSDmAdm.GravarXMLNotaFiscal';
    FGravarXMLNotaFiscalCommand.Prepare;
  end;
  FGravarXMLNotaFiscalCommand.Parameters[0].Value.SetWideString(pDsCreden);
  FGravarXMLNotaFiscalCommand.Parameters[1].Value.AsVariant := pDtParam;
  FGravarXMLNotaFiscalCommand.ExecuteUpdate;
  Result := FGravarXMLNotaFiscalCommand.Parameters[2].Value.GetAnsiString;
end;


constructor TSDmAdmClient.Create(ADBXConnection: TDBXConnection);
begin
  inherited Create(ADBXConnection);
end;


constructor TSDmAdmClient.Create(ADBXConnection: TDBXConnection; AInstanceOwner: Boolean);
begin
  inherited Create(ADBXConnection, AInstanceOwner);
end;


destructor TSDmAdmClient.Destroy;
begin
  FreeAndNil(FGravarPreLancamentoNFEntradaCommand);
  FreeAndNil(FFinalizarPreLancamentoNFEntradaCommand);
  FreeAndNil(FAutorizaPreLancamentoNFEntradaCommand);
  FreeAndNil(FMontaLotePreLancamentoNFEntradaCommand);
  FreeAndNil(FRecepcionaLotePreLancamentoNFEntradaCommand);
  FreeAndNil(FPeriodoContabilValidoCommand);
  FreeAndNil(FFinalizarDigitacaoNotaCommand);
  FreeAndNil(FEfetuarLancamentosContabeisCommand);
  FreeAndNil(FGravarSaldoPlanoCommand);
  FreeAndNil(FBaixarDocumentoCommand);
  FreeAndNil(FFaturarCommand);
  FreeAndNil(FLancarContasPagarReceberCommand);
  FreeAndNil(FBuscarNFeManifestoCommand);
  FreeAndNil(FGravarRNCCommand);
  FreeAndNil(FGravarDisposicaoRNCCommand);
  FreeAndNil(FGravarRevisoesDataEntregaCommand);
  FreeAndNil(FLiberarDesmontarCargaCommand);
  FreeAndNil(FAtualizarOperacaoArmazemCommand);
  FreeAndNil(FAtualizarChavesCommand);
  FreeAndNil(FValidarChaveLiberacaoCommand);
  FreeAndNil(FCancelarNotaFiscalCommand);
  FreeAndNil(FGravarXMLNotaFiscalCommand);
  inherited;
end;

end.


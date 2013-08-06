unit BrvDicionario;

interface

uses Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs, DBClient, Winsock,
     BrvString, ComCtrls, SqlExpr, Registry, SConnect, BrvDb, BrvCripto;

type

  TSqlNormal = record
       Text     : String;
  end;

  TTabela = record
       NmTabela : String;
       NrInicio : Integer;
       NrFinal  : Integer;
  end;

  TAtributo = record
       NmColuna : String;
       TpAtribu : String;
       DsAtribu : String;
       TpMascar : String;
       DsMascar : String;
       SnKey    : Boolean;
       CdPermis : Byte;
       VrDomini : String;
       DsDomini : String;
       DsHelp   : String;
       DsHint   : String;
       TmAtribu : Integer;
  end;

  TColuna = record
       NmColuna : String;
  end;

  TDcCamada = (Server, Client);

  TBrvDicionario = class(TComponent)
  private
    { Private declarations }
    gDtForBco  : String; // Formato stiring para data no banco
    gBcoBravo  : TBcoBravo;
    FNmTabela  : array of TTabela;    // Tabelas
    FNmAtribu  : array of TAtributo;  // Atributos
    gCdsChaEst : TClientDataSet;      // Atributo Estrangeiro
    FDsSQLNor  : TClientDataSet;      // Descrição da select;
    FDsSQLCon  : TClientDataSet;      // Descrição da select de consulta
    FNrTabela  : Integer;
    FNrAtribu  : Integer;
    FSQLConnection : TSQLConnection;

    gBrString : TBrvString;

    FDsLogin  : String;   // Login do usuário conectado.
    FNmUsuari : String;   // Nome do usuário conectado.
    FRsEmpres : String;   // Razão social da empresa selecionada.
    FCdUsuari : Integer;  // Código do usuário conectado.
    FCdEmpres : TStrings; // Lista dos códigos das empresas selecionadas.
    FNmEmpres : TStrings; // Lista dos nomes das empresas selecionadas.
    FDsDatSql : String;   // FormatDateTime para envio de datas para o banco
    FDsDaTiSq : String;   // FormatDateTime para envio de datas e hora para o banco
    FCdComEmp : String;   // Códigos das empresas selecionadas separados por vírgula.
    FStConAlt : Boolean;  // Confirma Alterações na grid?
    FStAcesso : String;   // Tipo de acesso do usuário {local/remoto/ambos}.
    FNrSeqAce : Integer;  // Sequencial de acesso.
    FCdBanco  : Integer;  // Código do banco de dados
    FDcCamada : TDcCamada;
    gDsCreden : String;   // numero da conexão com servidor de aplicação criptografado
    gNrClVers : Integer;  // versão da aplicação cliente
    gNrClVeSu : Integer;  // sub-versão do cliente
    gNrSrVers : Integer;  // versão da aplicação servidora
    gNrSrVeSu : Integer;  // sub-versão do servidor
    gSnGruAdm : Boolean;  // usuário pertence a grupo administrativo
    gDsCreImp : String;   // numero da conexão com servidor de Impressao criptografado

    procedure CarregarDicionarioServidor(pDsPanel: TStatusPanel; pPgbProgre: TProgressBar);
    function  CarregarDicionarioCliente(pDsPanel  : TStatusPanel;
                                        pPgbProgre: TProgressBar) : Boolean;
    procedure MostrarErroExecucaoServer(pDsMensag: String);
    procedure CarregarChavePrimariaCliente(pNmTabela: String;
                                       var pStlChave: TStrings);
    function  CarregarTabela(pNmTabela : String) : Boolean;
    function ProxNumeroSequencialCliente(pNmTabela, pNmAtribu: String): Integer;
    function ProxNumeroSequencialServer(pNmTabela, pNmAtribu: String): Integer;
    procedure CriarTabelaQuery;
    procedure CriarTabelaQueryConsulta;
    procedure EncontrarConfiguracaoAtributo(
                     pNmAtribu : String;      pNrAtrIni : Integer; pNrAtrFim : Integer;
                 var pDsAtribu : String;  var pTpAtribu : String;  var pTpMascar : String;
                 var pDsMascar : String;  var pDsHelp   : String;  var pDsHint   : String;
                 var pTmAtribu : Integer; var pCdPermis : Integer; var pVrDomini : String;
                 var pDsDomini : String;  var pSnKey    : Boolean; var pDsDisFor : String);
    procedure CarregarChaveEstrangeira(pNmChaEst: String);
    procedure CriarTabelaChaveEstrangeira;
    function ValorDefaultCliente(pVrDefaul: String): String;
    function  RetornaDadosSqlFixaCliente(pDsSql : String): OleVariant;
    function  RetornaDadosSqlFixaServer(pDsSql : String): OleVariant;
    function  PermissaoAtributo(pNmTabela : String; pNmAtribu : String) : Byte;
    procedure SetCodigoEmpresa(Value : TStrings);
    procedure SetNomeEmpresas(Value : TStrings);
    function ValorDefaultServer(pVrDefaul: String): String;
    function DadosTabelaVazia: OleVariant;
    function ExcluirExpressaoParametro(pDsSql : String; pDsConteu: String): String;
    function ExcluirExpressaoParametroParenteses(pDsSql: String;
                                                 pNrPosPar: Integer): String;
    function ExcluirExpressaoParametroWhere(pDsSql: String;     pNrPosFim : Integer;
                                            pNrPosAnt: Integer; pDsAnteri : String): String;
    function RetornaDadosSqlCadastroCliente(pNrSql: Integer;
      pDsParams: String; pNmFormul : String): OleVariant;
    function RetornaDadosSqlCadastroServer(pNrSql: Integer;
      pDsParams: String; pNmFormul : String): OleVariant;
    function ParametroDaEmpresaServer(pNrParame, pCdEmpres: Integer): Variant;
    function EhFeriado(pDtFeriad : TDate; pCpCidade: String;
                                          pCdSeqEst: Integer) : Boolean;
  protected
    { Protected declarations }
  public
    { Public declarations }
    SocketConnection : TSocketConnection;
    AppServer        : String;
    constructor Create(AOwner : TComponent); override;
    destructor  Destroy;                     override;
    function    CarregarDicionario(pDsPanel   : TStatusPanel; pPgbProgre : TProgressBar;
                                   pDsCreden  : String) : Boolean;
    function  CriptografarCredencial(pNrCreden: Integer; pTpServer : String) : String;
    function  DescriptografarCredencial(pDsCreden : String) : Integer;
    procedure ChavePrimaria(pNmTabela : String; var pStlChave : TStrings);
    function  ProxNumeroSequencial(pNmTabela: String; pNmAtribu : String): Integer;
    procedure CarregarDominiosDoAtributo(pNmTabela, pNmAtribu: String;
                                         var pVrDomini, pDsDomini: String);
    procedure AtributoConfiguracao(
                                   pNmTabela : String;      pNmAtribu : String;
                               var pDsAtribu : String;  var pTpAtribu : String;
                               var pTpMascar : String;  var pDsMascar : String;
                               var pDsHelp   : String;  var pDsHint   : String;
                               var pTmAtribu : Integer; var pCdPermis : Integer;
                               var pVrDomini : String;  var pDsDomini : String;
                               var pSnKey    : Boolean; var pDsDisFor : String);
    function ValorDefault(pVrDefaul : String) : String;
    function DataHoraServer : TDateTime;
    function HoraServer : TTime;
    function DataServer : TDate;
    function DataHoraServerStr : String;
    function HoraServerStr : String;
    function DataServerStr : String;
    function DataExtenso(pDtRefere : TDate=0) : String;
    function DataServerExtenso : String;

    procedure AtributoDominioValores(pNmTabela : String;
                                       pNmAtribu : String; var pDsDomini : TStrings;
                                                           var pVrDomini : TStrings);
    procedure AtributosDaTabela(pNmTabela: String; var pDsAtribu: TStrings);
    function  AtributoChave(pNmTabela : String; pNmAtribu : String) : Boolean;
    procedure AtributoEstrangeiro(pNmChaEst: String; var pStlChave: TStrings);
    function  AtributoEhChavePrimaria(pNmTabela : String; pNmAtribu : String) : Boolean;
    function  EncontrarInstrucaoSQLConsulta(pCdQuery : Integer) : String;
    function  AbrirConsultaInicializacao(pCdQuery : Integer) : Boolean;
    function  RetornaDadosSqlFixa(pDsSql: String): OleVariant;
    function  RetornaDadosSqlCadastro(pNrSql    : Integer; pDsParams: String;
                                      pNmFormul : String): OleVariant;
    procedure VerificarMensagemServidorAplicacao(pDsResult: String);
    function  TraduzirInstrucaoSQL(pDsSql : String) : String;
    function  AtributoDescricaoDominio(pNmTabela : String; pNmAtribu : String;
                                       pVrDomini : String) : String;
    function  AtributoDisplayFormat(pNmTabela : String; pNmAtribu : String) : String;
    function  AtributoDisplayLabel(pNmTabela : String; pNmAtribu : String) : String;
    function  AtributoTipoCampo(pNmTabela : String; pNmAtribu : String) : String;
    function  AtributoTipoCaracter(pNmTabela : String; pNmAtribu : String) : String;
    function  AtributoExisteTabela(pNmTabela : String; pNmAtribu : String) : Boolean;
    function  TemPermissaoExclusaoAtributo(NmTabela : String;
                                           NmAtribu : String) : Boolean;
    function  TemPermissaoAlteracaoAtributo(NmTabela : String;
                                            NmAtribu : String) : Boolean;
    function  TemPermissaoInclusaoAtributo(NmTabela : String;
                                           NmAtribu : String) : Boolean;
    function  TemPermissaoVisualizacaoAtributo(NmTabela : String;
                                               NmAtribu : String) : Boolean;
    function  TemPermissaoTotalAtributo(NmTabela : String;
                                        NmAtribu : String) : Boolean;
    function  PermissaoUsuarioAtributo(NmTabela : String; NmAtribu : String) : Byte;
    function  EncontrarInstrucaoSQL(pCdQuery : Integer; pNmOrigem : String) : String;
    function  SubstituirParametrosSQL(pDsSql    : String; pDsParams : TStrings) : String;
    procedure VerificarUsuarioGrupoAdministrador;
    procedure AtualizarLog(pNmTabela, pDsChaPri: String; pTpOperac: Integer;
                           pCdsLog   : OleVariant; pNmFormul : String;
                           pCdUsuari : Integer;    pTxSql    : String);
    procedure AtualizarDicionarioServer;
    function  ParametroDaEmpresa(pNrParame : Integer; pCdEmpres : Integer) : Variant;
    function  CorpCommaCodesNames : String;
    function  ProximoValorSequencial(pCdEmpres : Integer; pCdParam: Integer): Integer;
    function  ParametroEmpresa(pCdEmpres : Integer; pNrParame: Integer): String;
    function  DataProximoDiaUtil(pDtBase: TDate; pQtDias: Integer): TDate;
    function  RetornaValorColunaTabela(pNmTabela : String; pNmColRet : String;
                                       pNmColFil : String; pNmValFil : String) : String;

  published
    { Published declarations }
    property SQLConnection    : TSQLConnection    read FSQLConnection write FSQLConnection;
    property UserCode         : Integer           read FCdUsuari      write FCdUsuari;
    property UserLogin        : String            read FDsLogin       write FDsLogin;
    property UserName         : String            read FNmUsuari      write FNmUsuari;
    property CorpName         : String            read FRsEmpres      write FRsEmpres;
    property CorpCodes        : TStrings          read FCdEmpres      write SetCodigoEmpresa;
    property CorpNames        : TStrings          read FNmEmpres      write SetNomeEmpresas;
    property CorpCommaCodes   : String            read FCdComEmp      write FCdComEmp;
    property UserGroupAdm     : Boolean           read gSnGruAdm      write gSnGruAdm;
    property DateSql          : String            read FDsDatSql      write FDsDatSql;
    property DateTimeSql      : String            read FDsDaTiSq      write FDsDaTiSq;
    property AlterConfirm     : Boolean           read FStConAlt      write FStConAlt;
    property Acesso           : String            read FStAcesso      write FStAcesso;
    property NumeroAcesso     : Integer           read FNrSeqAce      write FNrSeqAce;
    property CodigoBanco      : Integer           read FCdBanco       write FCdBanco;
    property Camada           : TDcCamada         read FDcCamada      write FDcCamada;
    property Credencial       : String            read gDsCreden      write gDsCreden;
    property CredencialImp    : String            read gDsCreImp      write gDsCreImp;
    property TipoBancoDados   : TBcoBravo         read gBcoBravo      write gBcoBravo;
    property FormatoDataBanco : String            read gDtForBco      write gDtForBco;
    property VersaoCliente    : Integer           read gNrClVers      write gNrClVers;
    property VersaoClienteSub : Integer           read gNrClVeSu      write gNrClVeSu;
    property VersaoServidor   : Integer           read gNrSrVers      write gNrSrVers;
    property VersaoServidorSub: Integer           read gNrSrVeSu      write gNrSrVeSu;
  end;

procedure Register;

implementation

uses BrvDataSet, BrvClientDataSet, DB, BrvProvider, Provider, BrvOracle, UClaSrv;

constructor TBrvDicionario.Create(AOwner : TComponent);
begin
      inherited Create(AOwner);

      FCdEmpres := TStringList.Create;
      FNmEmpres := TStringList.Create;
      gBrString := TBrvString.Create(Self);

      FDsSQLNor  := TClientDataSet.Create(Self);
      FDsSQLCon  := TClientDataSet.Create(Self);
      gCdsChaEst := TClientDataSet.Create(Self);
      gBcoBravo  := BcoOracle;

      SocketConnection := TSocketConnection.Create(Nil);
end;

destructor TBrvDicionario.Destroy;
begin
      FreeAndNil(FDsSQLNor);
      FreeAndNil(FDsSQLCon);
      FreeAndNil(gCdsChaEst);

      FCdEmpres.Destroy;
      FNmEmpres.Destroy;

      FreeAndNil(gBrString);
      inherited  destroy;
end;

function TBrvDicionario.CriptografarCredencial(pNrCreden : Integer;
                                               pTpServer : String) : String;
var lBrvCripto : TBrvCripto;
begin
      //utilizado pelo cliente para criptografar a credencial recebida pelo servidor
      lBrvCripto := TBrvCripto.Create(Self);
      try
          lBrvCripto.Text := IntToStr(pNrCreden);
          Result          := lBrvCripto.Criptografia;
      finally
          FreeAndNil(lBrvCripto);
      end;

      if pTpServer = 'A' then // Aplicacao
      begin
            gDsCreden := Result;
      end else
      begin
            gDsCreImp := Result;
      end;
end;

function TBrvDicionario.DescriptografarCredencial(pDsCreden: String): Integer;
var lBrvCripto : TBrvCripto;
begin
      //utilizado pelo servidor para descriptografar a credencial enviada pelo cliente
      lBrvCripto := TBrvCripto.Create(Self);
      try
          lBrvCripto.Criptografia := pDsCreden;
          Result                  := StrToInt(lBrvCripto.Text);
      finally
          FreeAndNil(lBrvCripto);
      end;
end;

procedure TBrvDicionario.MostrarErroExecucaoServer(pDsMensag : String);
begin
      Delete(pDsMensag, 1, Pos(';', pDsMensag));

      raise Exception.Create('Mensagem do servidor de aplicação:' + #13#13 + pDsMensag);
end;

procedure TBrvDicionario.CriarTabelaQuery;
begin
      FDsSQLNor.Close;
      FDsSQLNor.FieldDefs.Clear;

      FDsSQLNor.FieldDefs.Add('NrQuery',   ftInteger, 0, False);
      FDsSQLNor.FieldDefs.Add('DsSql',     ftMemo,    0, False);


      FDsSQLNor.IndexDefs.Clear;
      FDsSQLNor.IndexDefs.Add('Key1', 'NrQuery', [ixPrimary,ixUnique]);
      FDsSQLNor.CreateDataSet;

      FDsSQLNor.IndexName := 'Key1';
      FDsSQLNor.SetKey;
end;

procedure TBrvDicionario.CriarTabelaChaveEstrangeira;
begin
      gCdsChaEst.Close;
      gCdsChaEst.FieldDefs.Clear;

      gCdsChaEst.FieldDefs.Add('NmChaEst',   ftString, 30, False);
      gCdsChaEst.FieldDefs.Add('NmTabela',   ftString, 15, False);
      gCdsChaEst.FieldDefs.Add('NmAtribu',   ftString,  8, False);
      gCdsChaEst.FieldDefs.Add('NrSeqKey',   FtInteger, 0, False);


      gCdsChaEst.IndexDefs.Clear;
      gCdsChaEst.IndexDefs.Add('Key1', 'NmChaEst;NmAtribu;NrSeqKey', [ixPrimary,ixUnique]);
      gCdsChaEst.CreateDataSet;

      gCdsChaEst.IndexName := 'Key1';
      gCdsChaEst.SetKey;
end;

procedure TBrvDicionario.CriarTabelaQueryConsulta;
begin
      FDsSQLCon.Close;
      FDsSQLCon.FieldDefs.Clear;

      FDsSQLCon.FieldDefs.Add('NrQuery',   ftInteger, 0, False);
      FDsSQLCon.FieldDefs.Add('DsSql',     ftMemo,    0, False);
      FDsSQLCon.FieldDefs.Add('SnIniAbe',  ftString,  1, False);


      FDsSQLCon.IndexDefs.Clear;
      FDsSQLCon.IndexDefs.Add('Key1', 'NrQuery', [ixPrimary,ixUnique]);
      FDsSQLCon.CreateDataSet;

      FDsSQLCon.IndexName := 'Key1';
      FDsSQLCon.SetKey;
end;

// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// =-=-=-= Rotinas de inicialização do dicionário
// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
function TBrvDicionario.CarregarDicionario(pDsPanel   : TStatusPanel;
                                           pPgbProgre : TProgressBar;
                                           pDsCreden  : String) : Boolean;
var lDsTexAnt  : String;
begin
      Result    := True;
      gDsCreden := pDsCreden;
      if pDsPanel <> nil then
      begin
            lDsTexAnt  := pDsPanel.Text;
      end;

      FNrTabela := 0;
      FNrAtribu := 0;

      if FDcCamada = Server then
      begin
            CarregarDicionarioServidor(pDsPanel, pPgbProgre);
      end else
      begin
            Result := CarregarDicionarioCliente(pDsPanel, pPgbProgre);
      end;

      if pDsPanel <> Nil then
      begin
            pDsPanel.Text := lDsTexAnt;
      end;
end;

procedure TBrvDicionario.VerificarUsuarioGrupoAdministrador;
var QpPerMen  : TClientDataset;
    lDsWhere  : String;
begin
      QpPerMen := TClientDataSet.Create(Self);
      try
          lDsWhere := '<%CdUsuari%>;' + IntToStr(UserCode);
          QpPerMen.Data := RetornaDadosSqlCadastro(24, lDsWhere, Name);

          gSnGruAdm := not QpPerMen.Eof;

      finally
          FreeAndNil(QpPerMen);
      end;

end;

function TBrvDicionario.CarregarDicionarioCliente(pDsPanel   : TStatusPanel;
                                                  pPgbProgre : TProgressBar) : Boolean;
var lQtRegAux : Integer;
    lNrQuery  : Integer;
    lConexao  : TSDmRWClient;
    lDsResult : String;
begin
      Result    := True;
      lConexao  := TSDmRWClient.Create(FSQLConnection.DBXConnection);

      try
          gBcoBravo := lConexao.TipoBancoDados(gDsCreden, lDsResult, gDtForBco, gNrSrVers,
                                               gNrSrVeSu);

          if gNrSrVers <> gNrClVers then
          begin
                MessageDlg('O servidor de aplicação conectado está na versão ' +
                           IntToStr(gNrSrVers) + '.' + IntToStr(gNrSrVeSu) +
                           ' e a aplicação cliente está na versão ' +
                           IntToStr(gNrClVers) + '.' + IntToStr(gNrClVeSu) + '.' +
                           #13#13 +
                           'Essas versõs possuem incompatibilicades de funções, ' +
                           'por esse motivo a aplicação será finalizada.'        +
                           #13#13 +
                           'Atualize a versão de sua aplicação e tente novamente.',
                           mtInformation, [mbOk], 0);
                Result := False;
          end;

          if Result then
          begin
                  if UserCode > 0 then
                  begin
                        VerificarUsuarioGrupoAdministrador;
                  end;
               // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
               // =-=-=-=-=-=  Carregando Tabelas  -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
               // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                  lQtRegAux  := lConexao.RetornaQuantidadeRegistroTabela(gDsCreden,
                                                                       lDsResult, 'S008');
                  if lDsResult[1] <> '0' then
                  begin
                        MostrarErroExecucaoServer(lDsResult);
                  end;
                  SetLength(FNmTabela, lQtRegAux + 1);

               // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
               // =-=-=-=-=-=  Carregando Atributos da tabela =-=-=-=-=-=-=-=-=-=-=-=-=-=
               // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                  lQtRegAux  := lConexao.RetornaQuantidadeRegistroTabela(gDsCreden,
                                                                       lDsResult, 'S009');
                  if lDsResult[1] <> '0' then
                  begin
                        MostrarErroExecucaoServer(lDsResult);
                  end;
                  SetLength(FNmAtribu, lQtRegAux + 1);
               // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
               // =-=-=-=-=-=  Carregando Chaves Estrangeiras =-=-=-=-=-=-=-=-=-=-=-=-=-=
               // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                  CriarTabelaChaveEstrangeira;
               // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
               // =-=-=-=-=-=  Carregando Instruções SQL  =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
               // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                  CriarTabelaQuery;

                  if pPgbProgre <> nil then
                  begin
                        pPgbProgre.Position := 0;
                  end;

                  if pDsPanel <> nil then
                  begin
                        pDsPanel.Text       := 'Carregando instruções Sql ...';
                  end;

               // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
               // =-=-=-=-=-=  Carregando Instruções SQL de consulta -=-=-=-=-=-=-=-=-=-=
               // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                  CriarTabelaQueryConsulta;

                  if pPgbProgre <> nil then
                  begin
                        pPgbProgre.Position := 0;
                  end;

                  if pDsPanel <> nil then
                  begin
                        pDsPanel.Text := 'Carregando instruções Sql de consulta ...';
                  end;
          end;
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TBrvDicionario.CarregarDicionarioServidor(pDsPanel   : TStatusPanel;
                                                    pPgbProgre : TProgressBar);
var QryAuxili : TBrvDataSet;
    NrQuery   : Integer;
begin
      try
          QryAuxili               := TBrvDataSet.Create(Self);
          QryAuxili.Name          := 'QryCarga_CarDicSrv';
          QryAuxili.BrDicionario  := Self;
          QryAuxili.GetMetadata   := False;
          QryAuxili.SQLConnection := FSQLConnection;
       // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
       // =-=-=-=-=-=  Carregando Tabelas  -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
       // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          QryAuxili.CommandText := 'Select Count(NmTabela) as QtTabela From Tabela';
          QryAuxili.Open;

          SetLength(FNmTabela, QryAuxili.FieldByName('QtTabela').AsInteger + 1);

          QryAuxili.Close;
       // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
       // =-=-=-=-=-=  Carregando Atributos da tabela =-=-=-=-=-=-=-=-=-=-=-=-=-=-=
       // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          QryAuxili.CommandText := 'Select Count(NmTabela) as QtTabela From TabAtrib';
          QryAuxili.Open;

          SetLength(FNmAtribu, QryAuxili.FieldByName('QtTabela').AsInteger + 1);

          QryAuxili.Close;
       // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
       // =-=-=-=-=-=  Carregando Instruções SQL  =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
       // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          CriarTabelaQuery;

          if pPgbProgre <> nil then
          begin
                pPgbProgre.Position := 0;
          end;

          QryAuxili.Close;
       // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
       // =-=-=-=-=-=  Carregando Instruções SQL de consulta -=-=-=-=-=-=-=-=-=-=-=
       // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          CriarTabelaQueryConsulta;

          if pDsPanel <> nil then
          begin
                pDsPanel.Text := 'Carregando instruções Sql de consulta ...';
          end;
       finally
          FreeAndNil(QryAuxili);
       end;
end;

procedure TBrvDicionario.AtributoConfiguracao(pNmTabela : String;      pNmAtribu : String;
                                          var pDsAtribu : String;  var pTpAtribu : String;
                                          var pTpMascar : String;  var pDsMascar : String;
                                          var pDsHelp   : String;  var pDsHint   : String;
                                          var pTmAtribu : Integer; var pCdPermis : Integer;
                                          var pVrDomini : String;  var pDsDomini : String;
                                          var pSnKey    : Boolean; var pDsDisFor : String);
var lNrTabela : Integer;
    lSnAchou  : Boolean;
begin
      pDsAtribu := pNmAtribu;
      pTpAtribu := ' ';
      pTpMascar := ' ';
      pDsMascar := '';
      pDsHelp   := '';
      pDsHint   := '';
      pTmAtribu := 0;
      pCdPermis := 0;
      pDsDomini := '';
      pVrDomini := '';
      lSnAchou  := False;
      pSnKey    := False;
      pNmTabela := UpperCase(pNmTabela);
      pNmAtribu := UpperCase(pNmAtribu);

      lNrTabela := 0;

      while (lNrTabela < FNrTabela) and (not lSnAchou) do
      begin
            if  FNmTabela[lNrTabela].NmTabela = pNmTabela then
            begin
                  lSnAchou  := True;
            end else
            begin
                  inc(lNrTabela);
            end;
      end;

      if not lSnAchou then
      begin
            lSnAchou  := CarregarTabela(pNmTabela);
            lNrTabela := FNrTabela;
      end;

      if lSnAchou then
      begin
            EncontrarConfiguracaoAtributo(pNmAtribu, FNmTabela[lNrTabela].NrInicio,
                                                     FNmTabela[lNrTabela].NrFinal,
                                                     pDsAtribu, pTpAtribu, pTpMascar,
                                                     pDsMascar, pDsHelp,   pDsHint,
                                                     pTmAtribu, pCdPermis, pVrDomini,
                                                     pDsDomini, pSnKey,    pDsDisFor);
      end;
end;

procedure TBrvDicionario.AtributosDaTabela(pNmTabela     : String;
                                           var pDsAtribu : TStrings);
var lNmAtribu : String;
    lDsAtribu : String;
    lTpAtribu : String;
    lTpMascar : String;
    lDsMascar : String;
    lDsHelp   : String;
    lDsHint   : String;
    lTmAtribu : Integer;
    lCdPermis : Integer;
    lVrDomini : String;
    lDsDomini : String;
    lSnKey    : Boolean;
    lDsDisFor : String;
    lNrTabela : Integer;
    lSnAchou  : Boolean;
    lNrAtribu : Integer;
begin
      pNmTabela := UpperCase(pNmTabela);

      lNrTabela := 0;

      while (lNrTabela < FNrTabela) and (not lSnAchou) do
      begin
            if  FNmTabela[lNrTabela].NmTabela = pNmTabela then
            begin
                  lSnAchou  := True;
            end else
            begin
                  inc(lNrTabela);
            end;
      end;

      if not lSnAchou then
      begin
            lSnAchou  := CarregarTabela(pNmTabela);
            lNrTabela := FNrTabela;
      end;

      pDsAtribu := TStringList.Create;
      if lSnAchou then
      begin
            lNrAtribu := FNmTabela[lNrTabela].NrInicio;

            while (lNrAtribu <= FNmTabela[lNrTabela].NrFinal) and
                  (lNrAtribu <= FNrAtribu) do
            begin
                  pDsAtribu.Add(FNmAtribu[lNrAtribu].NmColuna);
                  inc(lNrAtribu);
            end;
      end;
end;

function TBrvDicionario.DataHoraServer : TDateTime;
var lConexao  : TSDmRWClient;
    lDsResult : String;
begin
      Result := Now;

      if FDcCamada <> Server then
      begin
            lConexao  := TSDmRWClient.Create(FSQLConnection.DBXConnection);

            try
                lConexao.DataHoraServer(gDsCreden, lDsResult, Result);
                if lDsResult[1] <> '0' then
                begin
                      MostrarErroExecucaoServer(lDsResult);
                end;
            finally
                FreeAndNil(lConexao);
            end;
      end;



{      if FDcCamada = Server then
      begin
            Result := Now;
      end else
      begin
            lConexao  := TSDmRWClient.Create(FSQLConnection.DBXConnection);

            try
                lConexao.DataHoraServer(gDsCreden, lDsResult, Result);
                if lDsResult[1] <> '0' then
                begin
                      MostrarErroExecucaoServer(lDsResult);
                end;
            finally
                FreeAndNil(lConexao);
            end;
      end;   }
end;

function TBrvDicionario.DataHoraServerStr: String;
begin
      if Trim(gDtForBco) = '' then
      begin
            MessageDlg('Formato de data para dicionário não está definido. ' + #13#13 +
                       'yyyy/mm/dd será assumido', MtInformation, [mbOk], 0);
            gDtForBco := 'yyyy/mm/dd';
      end;

      Result := FormatDateTime(gDtForBco + ' hh:mm:ss', DataHoraServer);
end;

function TBrvDicionario.HoraServer : TTime;
begin
      if FDcCamada = Server then
      begin
            Result := Time;
      end else
      begin
            Result := DataHoraServer;
      end;
end;

function TBrvDicionario.HoraServerStr: String;
begin
      Result := TimeToStr(HoraServer);
end;

function TBrvDicionario.DataServer : TDate;
begin
      if FDcCamada = Server then
      begin
            Result := Date;
      end else
      begin
            Result := Int(DataHoraServer);
      end;
end;

function TBrvDicionario.DataServerExtenso: String;
begin
      Result := FormatDateTime('ddddddddd', DataServer);
end;

function TBrvDicionario.DataExtenso(pDtRefere : TDate=0) : String;
begin
      if pDtRefere = 0 then
      begin
            Result := DataServerExtenso;
      end else
      begin
            Result := FormatDateTime('ddddddddd', pDtRefere);
      end;
end;

function TBrvDicionario.DataServerStr: String;
begin
      if Trim(gDtForBco) = '' then
      begin
            MessageDlg('Formato de data para dicionário não está definido. ' + #13#13 +
                       'yyyy/mm/dd será assumido', MtInformation, [mbOk], 0);
            gDtForBco := 'yyyy/mm/dd';
      end;

      Result := FormatDateTime(gDtForBco, DataServer);
end;

function TBrvDicionario.ValorDefault(pVrDefaul: String): String;
begin
      if FDcCamada = Server then
      begin
            Result := ValorDefaultServer(pVrDefaul);
      end else
      begin
            Result := ValorDefaultCliente(pVrDefaul);
      end;
end;

function TBrvDicionario.ValorDefaultServer(pVrDefaul : String) : String;
var lDtAno : Word;
    lDtMes : Word;
    lDtDia : Word;
begin
      if UpperCase(pVrDefaul) = '<%DATE%>' then
      begin
            Result := FormatDateTime('dd/mm/yyyy', DataServer);
      end
      else if UpperCase(pVrDefaul) = '<%TIME%>' then
           begin
                 Result := FormatDateTime('hh:mm:ss', HoraServer);
           end
      else if UpperCase(pVrDefaul) = '<%DATETIME%>' then
           begin
                 Result := FormatDateTime('dd/mm/yyyy hh:mm:ss', DataHoraServer);
           end
      else if UpperCase(pVrDefaul) = '<%ANO%>' then
           begin
                 DecodeDate(Date, lDtAno, lDtMes, lDtDia);
                 Result := FormatFloat('0000', lDtAno);
           end
      else if UpperCase(pVrDefaul) = '<%MES%>' then
           begin
                 DecodeDate(Date, lDtAno, lDtMes, lDtDia);
                 Result := FormatFloat('00', lDtMes);
           end
      else if UpperCase(pVrDefaul) = '<%DIA%>' then
           begin
                 DecodeDate(Date, lDtAno, lDtMes, lDtDia);
                 Result := FormatFloat('00', lDtDia);
           end
      else Result := pVrDefaul;
end;

function TBrvDicionario.ValorDefaultCliente(pVrDefaul : String) : String;
var lConexao  : TSDmRWClient;
    lDsResult : String;
begin
      if UpperCase(pVrDefaul) = '<%USERNAME%>' then
      begin
            Result := UserName;
      end
      else if UpperCase(pVrDefaul) = '<%USERCODE%>' then
           begin
                 Result := IntToStr(UserCode);
           end
      else if UpperCase(pVrDefaul) = '<%USERLOGIN%>' then
           begin
                 Result := UserLogin;
           end
      else begin
                 lConexao  := TSDmRWClient.Create(FSQLConnection.DBXConnection);

                 try
                     lConexao.ValorDefault(gDsCreden, lDsResult, pVrDefaul);
                     if lDsResult[1] <> '0' then
                     begin
                           MostrarErroExecucaoServer(lDsResult);
                     end;

                     Result := pVrDefaul;
                 finally
                     FreeAndNil(lConexao);
                 end;
           end;
end;

procedure TBrvDicionario.AtributoDominioValores(pNmTabela : String; pNmAtribu : String;
                                            var pDsDomini : TStrings;
                                            var pVrDomini : TStrings);
var lDsAtribu : String;
    lTpAtribu : String;
    lTpMascar : String;
    lDsMascar : String;
    lDsHelp   : String;
    lDsHint   : String;
    lTmAtribu : Integer;
    lCdPermis : Integer;
    lVrDomini : String;
    lDsDomini : String;
    lSnKey    : Boolean;
    lDsDisFor : String;
begin
      AtributoConfiguracao(pNmTabela, pNmAtribu, lDsAtribu, lTpAtribu,
                           lTpMascar, lDsMascar, lDshelp,   lDsHint,   lTmAtribu,
                           lCdPermis, lVrDomini, lDsDomini, lSnKey,    lDsDisfor);

      pDsDomini := TStringList.Create;
      pVrDomini := TStringList.Create;

      pDsDomini.Clear;
      pVrDomini.Clear;

      pDsDomini.CommaText := lDsDomini;
      pVrDomini.CommaText := lVrDomini;
end;

function TBrvDicionario.TraduzirInstrucaoSQL(pDsSql : String) : String;
var lBrvOracle : TBrvOracle;
begin
      if gBcoBravo = BcoOracle then
      begin
            lBrvOracle := TBrvOracle.Create(Self);

            try
                try
                     Result := lBrvOracle.SintaxeOracle(pDsSql);
                finally
                     FreeAndNil(lBrvOracle);
                end;
            except
                on E: Exception do
                      begin
                            raise Exception.create('Erro ao executar instrução SQL: ' +
                                  #13#13 + pDsSql + #13#13 + E.Message);
                      end;
            end;
      end else
      begin
            Result := pDsSql;
      end;
end;

function  TBrvDicionario.AtributoDisplayFormat(pNmTabela : String;
                                               pNmAtribu : String) : String;
var lDsAtribu : String;
    lTpAtribu : String;
    lTpMascar : String;
    lDsMascar : String;
    lDsHelp   : String;
    lDsHint   : String;
    lTmAtribu : Integer;
    lCdPermis : Integer;
    lVrDomini : String;
    lDsDomini : String;
    lSnKey    : Boolean;
    lDsDisFor : String;
begin
      AtributoConfiguracao(pNmTabela, pNmAtribu, lDsAtribu, lTpAtribu,
                           lTpMascar, lDsMascar, lDshelp,   lDsHint,   lTmAtribu,
                           lCdPermis, lVrDomini, lDsDomini, lSnKey,    lDsDisfor);

      Result := lDsDisFor;
end;

function  TBrvDicionario.AtributoDisplayLabel(pNmTabela : String;
                                              pNmAtribu : String) : String;
var lDsAtribu : String;
    lTpAtribu : String;
    lTpMascar : String;
    lDsMascar : String;
    lDsHelp   : String;
    lDsHint   : String;
    lTmAtribu : Integer;
    lCdPermis : Integer;
    lVrDomini : String;
    lDsDomini : String;
    lSnKey    : Boolean;
    lDsDisFor : String;
begin
      AtributoConfiguracao(pNmTabela, pNmAtribu, lDsAtribu, lTpAtribu,
                           lTpMascar, lDsMascar, lDshelp,   lDsHint,   lTmAtribu,
                           lCdPermis, lVrDomini, lDsDomini, lSnKey,    lDsDisfor);

      Result := lDsAtribu;
end;

function  TBrvDicionario.AtributoTipoCampo(pNmTabela : String; pNmAtribu : String) : String;
var lDsAtribu : String;
    lTpAtribu : String;
    lTpMascar : String;
    lDsMascar : String;
    lDsHelp   : String;
    lDsHint   : String;
    lTmAtribu : Integer;
    lCdPermis : Integer;
    lVrDomini : String;
    lDsDomini : String;
    lSnKey    : Boolean;
    lDsDisFor : String;
begin
      AtributoConfiguracao(pNmTabela, pNmAtribu, lDsAtribu, lTpAtribu,
                           lTpMascar, lDsMascar, lDshelp,   lDsHint,   lTmAtribu,
                           lCdPermis, lVrDomini, lDsDomini, lSnKey,    lDsDisfor);
      Result := lTpAtribu;
end;

function  TBrvDicionario.AtributoDescricaoDominio(pNmTabela : String; pNmAtribu : String;
                                                  pVrDomini : String) : String;
var lDsAtribu : String;
    lTpAtribu : String;
    lTpMascar : String;
    lDsMascar : String;
    lDsHelp   : String;
    lDsHint   : String;
    lTmAtribu : Integer;
    lCdPermis : Integer;
    lVrDomini : String;
    lDsDomini : String;
    lSnKey    : Boolean;
    lDsDisFor : String;

    StlVrDomini : TStrings;
    StlDsDomini : TStrings;

    lNrDomini   : Integer;
begin
      AtributoConfiguracao(pNmTabela, pNmAtribu, lDsAtribu, lTpAtribu,
                           lTpMascar, lDsMascar, lDshelp,   lDsHint,   lTmAtribu,
                           lCdPermis, lVrDomini, lDsDomini, lSnKey,    lDsDisfor);

      StlVrDomini := TStringList.Create;
      StlDsDomini := TStringList.Create;

      try
          StlVrDomini.CommaText := lVrDomini;
          StlDsDomini.CommaText := lDsDomini;

          lNrDomini := 0;
          Result    := pVrDomini;

          while (lNrDomini < StlVrDomini.Count) do
          begin
                if StlVrDomini.Strings[lNrDomini] = pVrDomini then
                begin
                      try
                          Result := StlDsDomini.Strings[lNrDomini];
                          lNrDomini := StlVrDomini.Count;
                      except
                          lNrDomini := StlVrDomini.Count;
                          MessageDlg('Não foi possível determinar a descrição '+
                                     'do valor de domínio "' + pVrDomini + '" ' +
                                     'para o atributo "' +  pNmAtribu + '" da ' +
                                     'tabela "' + pNmTabela + '"', MtInformation,
                                     [MbOk], 0);
                      end;
                end;

                Inc(lNrDomini);
          end;
      finally
          StlVrDomini.Destroy;
          StlDsDomini.Destroy;
      end;
end;

function  TBrvDicionario.AtributoTipoCaracter(pNmTabela: String; pNmAtribu: String): String;
var lDsAtribu : String;
    lTpAtribu : String;
    lTpMascar : String;
    lDsMascar : String;
    lDsHelp   : String;
    lDsHint   : String;
    lTmAtribu : Integer;
    lCdPermis : Integer;
    lVrDomini : String;
    lDsDomini : String;
    lSnKey    : Boolean;
    lDsDisFor : String;
begin
      AtributoConfiguracao(pNmTabela, pNmAtribu, lDsAtribu, lTpAtribu,
                           lTpMascar, lDsMascar, lDshelp,   lDsHint,   lTmAtribu,
                           lCdPermis, lVrDomini, lDsDomini, lSnKey,    lDsDisfor);

      Result := lTpAtribu
end;

function  TBrvDicionario.AtributoChave(pNmTabela : String; pNmAtribu : String) : Boolean;
var lStlColuna : TStrings;
    lNrColuna  : Integer;
begin
      lStlColuna := TStringList.Create;
      Result := False;
      ChavePrimaria(pNmTabela, lStlColuna);
      lNrColuna := 0;

      while (not Result) and (lNrColuna < lStlColuna.Count) do
      begin
            if  lStlColuna.Strings[lNrColuna] = pNmAtribu then
            begin
                  Result := True;
            end;

            inc(lNrColuna);
      end;
      lStlColuna.Destroy;
end;

function  TBrvDicionario.AtributoExisteTabela(pNmTabela : String;
                                              pNmAtribu : String) : Boolean;
var lStlColuna : TStrings;
    lNrColuna  : Integer;
begin
      lStlColuna := TStringList.Create;
      Result    := False;
      lNrColuna  := 0;

      AtributosDaTabela(pNmTabela, lStlColuna);

      while (not Result) and (lNrColuna < lStlColuna.Count) do
      begin
            if  lStlColuna.Strings[lNrColuna] = pNmAtribu then
            begin
                  Result := True;
            end;

            inc(lNrColuna);
      end;

      lStlColuna.Destroy;
end;

function TBrvDicionario.AtributoEhChavePrimaria(pNmTabela : String;
                                                pNmAtribu : String) : Boolean;
var lDsChaPri : TStrings;
begin
      try
          lDsChaPri := TStringList.Create;
          ChavePrimaria(pNmTabela, lDsChaPri);
          Result  := Pos(pNmAtribu, lDsChaPri.Text) > 0;
      finally
          FreeAndNil(lDsChaPri);
      end;
end;

procedure TBrvDicionario.AtributoEstrangeiro(pNmChaEst : String; var pStlChave : TStrings);
var lNrChaEst : Integer;
    lSnAchou  : Boolean;
begin
      pStlChave.Clear;
      gCdsChaEst.FindNearest([pNmChaEst]);
      lSnAchou := gCdsChaEst.FieldByName('NmChaEst').AsString = pNmChaEst;

      if not lSnAchou then
      begin
            CarregarChaveEstrangeira(pNmChaEst);
            gCdsChaEst.FindNearest([pNmChaEst]);
            lSnAchou := gCdsChaEst.FieldByName('NmChaEst').AsString = pNmChaEst;
      end;

      if lSnAchou then
      begin
            while (gCdsChaEst.FieldByName('NmChaEst').AsString = pNmChaEst) and
                  (not gCdsChaEst.Eof) do
            begin
                  pStlChave.Add(gCdsChaEst.FieldByName('NmAtribu').AsString);
                  gCdsChaEst.Next;
            end;
      end;
end;

procedure TBrvDicionario.CarregarChaveEstrangeira(pNmChaEst : String);
var CcAuxili : TClientDataSet;
    lDsSql   : String;
begin
      CcAuxili    := TClientDataSet.Create(Self);

      lDsSql := 'Select S013.*                ' +
                ' From  S013 S013 ' +
                ' Where S013.NmChaEst = ' + #39 + pNmChaEst + #39 +
                ' Order by S013.NrSeqKey';
      try

          ccAuxili.Data := RetornaDadosSqlFixa(lDsSql);

          CcAuxili.First;

          while not CcAuxili.Eof do
          begin
                gCdsChaEst.Append;
                gCdsChaEst.FieldByName('NmChaEst').AsString :=
                                       CcAuxili.FieldByName('NmChaEst').AsString;
                gCdsChaEst.FieldByName('NmTabela').AsString :=
                                       CcAuxili.FieldByName('NmTabela').AsString;
                gCdsChaEst.FieldByName('NmAtribu').AsString :=
                                       CcAuxili.FieldByName('NmAtribu').AsString;
                gCdsChaEst.FieldByName('NrSeqKey').AsString :=
                                       CcAuxili.FieldByName('NrSeqKey').AsString;
                CcAuxili.Next;
          end;
      finally
          FreeAndNil(CcAuxili);
      end;
end;

procedure TBrvDicionario.ChavePrimaria(pNmTabela : String; var pStlChave : TStrings);
var NrTabela : Integer;
    SnAchou  : Boolean;
begin
      if FDcCamada = Server then
      begin
            NrTabela := 0;
            SnAchou  := False;

            while (NrTabela < FNrTabela) and (not SnAchou) do
            begin
                  if  FNmTabela[NrTabela].NmTabela = pNmTabela then
                  begin
                        SnAchou := True;
                  end else
                  begin
                        inc(NrTabela);
                  end;
            end;

            if not SnAchou then
            begin
                  SnAchou := CarregarTabela(pNmTabela);
                  NrTabela := FNrTabela;
            end;

            if SnAchou then
            begin
                  for NrTabela := FNmTabela[NrTabela].NrInicio to
                                                          FNmTabela[NrTabela].NrFinal do
                  begin
                        if FNmAtribu[NrTabela].SnKey  then
                        begin
                              pStlChave.Add(FNmAtribu[NrTabela].NmColuna);
                        end;
                  end;
            end;
      end else
      begin
            CarregarChavePrimariaCliente(pNmTabela, pStlChave);
      end;
end;

function TBrvDicionario.CorpCommaCodesNames: String;
var lNrLinha : Integer;
begin
      Result := '';
      for lNrLinha := 0 to FCdEmpres.Count -1 do
      begin
            if Result <> '' then
            begin
                  Result := Result + ',';
            end;

            Result := Result + '"' + FCdEmpres.Strings[lNrLinha] + ' - ' +
                                     FNmEmpres.Strings[lNrLinha] + '"';
      end;
end;

procedure TBrvDicionario.CarregarChavePrimariaCliente(
                                            pNmTabela : String; var pStlChave : TStrings);
var lConexao  : TSDmRWClient;
    lDsResult : String;
    lStlChave : String;
begin
      lConexao  := TSDmRWClient.Create(FSQLConnection.DBXConnection);

      try
          lConexao.ChavePrimaria(gDsCreden, lDsResult, pNmTabela, lStlChave);
          if lDsResult[1] <> '0' then
          begin
                MostrarErroExecucaoServer(lDsResult);
          end;

          pStlChave.CommaText := lStlChave;
      finally
          FreeAndNil(lConexao);
      end;
end;

function TBrvDicionario.ProxNumeroSequencialServer(pNmTabela : String;
                                                    pNmAtribu : String) : Integer;
var CcAuxili : TClientDataSet;
    QcAuxili : TBrvDataSet;
    lDsSql   : String;
begin
      CcAuxili    := TClientDataSet.Create(Self);

      QcAuxili               := TBrvDataSet.Create(Self);
      QcAuxili.BrDicionario  := Self;
      QcAuxili.GetMetadata   := False;
      QcAuxili.SQLConnection := FSQLConnection;

      lDsSql := 'Select S009.VrAtual,  S009.VrInterv ' +
                ' From   S009 S009 ' +
                ' Where  S009.NmTabela = ' + #39 + pNmTabela            + #39 +
                ' and    S009.NmAtribu = ' + #39 + UpperCase(pNmAtribu) + #39;
      try

          ccAuxili.Data := RetornaDadosSqlFixaServer(lDsSql);
          Result := ccAuxili.FieldByName('VrAtual').AsInteger +
                    ccAuxili.FieldByName('VrInterv').AsInteger;

          lDsSql := 'Update S009 set S009.VrAtual = ' + IntToStr(Result) +
                    ' Where  S009.NmTabela = ' + #39 + pNmTabela + #39 +
                    ' and    S009.NmAtribu = ' + #39 + pNmAtribu + #39;
          QcAuxili.CommandText := lDsSql;
          QcAuxili.ExecSQL(true);
      finally
          FreeAndNil(CcAuxili);
          FreeAndNil(QcAuxili);
      end;
end;

function TBrvDicionario.ProxNumeroSequencialCliente(pNmTabela : String;
                                                    pNmAtribu : String) : Integer;
var lConexao  : TSDmRWClient;
    lDsResult : String;
begin
      lConexao  := TSDmRWClient.Create(FSQLConnection.DBXConnection);

      try
          Result := lConexao.ProxNumeroSequencial(gDsCreden, lDsResult,
                                                  pNmTabela, pNmAtribu);

          if lDsResult[1] <> '0' then
          begin
                MostrarErroExecucaoServer(lDsResult);
          end;
      finally
          FreeAndNil(lConexao);
      end;
end;

function TBrvDicionario.ProxNumeroSequencial(pNmTabela : String;
                                             pNmAtribu : String) : Integer;
begin
      if FDcCamada = Server then
      begin
            Result := ProxNumeroSequencialServer(pNmTabela, pNmAtribu);
      end else
      begin
            Result := ProxNumeroSequencialCliente(pNmTabela, pNmAtribu);
      end;
end;

procedure TBrvDicionario.EncontrarConfiguracaoAtributo(
                 pNmAtribu : String;      pNrAtrIni : Integer; pNrAtrFim : Integer;
             var pDsAtribu : String;  var pTpAtribu : String;  var pTpMascar : String;
             var pDsMascar : String;  var pDsHelp   : String;  var pDsHint   : String;
             var pTmAtribu : Integer; var pCdPermis : Integer; var pVrDomini : String;
             var pDsDomini : String;  var pSnKey    : Boolean; var pDsDisFor : String);
var lNrAtribu : Integer;
begin
      lNrAtribu := pNrAtrIni;

      while (lNrAtribu <= pNrAtrFim) and (lNrAtribu <= FNrAtribu) do
      begin
            if FNmAtribu[lNrAtribu].NmColuna = pNmAtribu then
            begin
                  pDsAtribu := FNmAtribu[lNrAtribu].DsAtribu;
                  pTpAtribu := FNmAtribu[lNrAtribu].TpAtribu;
                  pTpMascar := FNmAtribu[lNrAtribu].TpMascar;
                  pDsMascar := FNmAtribu[lNrAtribu].DsMascar;
                  pDsHelp   := FNmAtribu[lNrAtribu].DsHelp;
                  pDsHint   := FNmAtribu[lNrAtribu].DsHint;
                  pTmAtribu := FNmAtribu[lNrAtribu].TmAtribu;
                  pCdPermis := FNmAtribu[lNrAtribu].CdPermis;
                  pVrDomini := FNmAtribu[lNrAtribu].VrDomini;
                  pDsDomini := FNmAtribu[lNrAtribu].DsDomini;
                  pSnKey    := FNmAtribu[lNrAtribu].SnKey;
                  pDsDisFor := '';

                  if pTpAtribu = 'I' then // inteiro
                  begin
                        pDsDisFor := '#,000';
                  end
                  else if pTpAtribu = 'D' then // Data
                       begin
                             pDsDisFor := 'dd/mm/yyyy';
                       end
                  else if pTpAtribu = 'N' then // Numero
                       begin
                             pDsDisFor := '#,000.' + StringOfChar('0', pTmAtribu);
                       end
                  ;

                  lNrAtribu := FNrAtribu;
            end;

            Inc(lNrAtribu);
      end;
end;

function TBrvDicionario.EncontrarInstrucaoSQL(pCdQuery  : Integer;
                                              pNmOrigem : String) : String;
var QryAuxili : TClientDataset;
    lDsSql    : String;
    lConexao  : TSDmRWClient;
    lDsResult : String;
begin
      if not FDsSqlNor.FindKey([pCdQuery]) then
      begin
            FDsSqlNor.Append;
            FDsSqlNor.FieldByName('NrQuery').AsInteger := pCdQuery;

            if FDcCamada = Server then
            begin
                  lDsSql := 'Select TxSql From S007 Where NrQuery = ' + IntToStr(pCdQuery);
                  try
                      QryAuxili      := TClientDataSet.Create(Self);
                      QryAuxili.Data := RetornaDadosSqlFixaServer(lDsSql);
                      FDsSqlNor.FieldByName('DsSql').AsString :=
                                           QryAuxili.FieldByName('TxSql').AsString;
                  finally
                      FreeAndNil(QryAuxili);
                  end;
            end else
            begin
                  lConexao  := TSDmRWClient.Create(FSQLConnection.DBXConnection);

                  try
                      lConexao.EncontrarInstrucaoSQL(
                                       gDsCreden, lDsResult, pCdQuery, lDsSql, pNmOrigem);

                      if lDsResult[1] <> '0' then
                      begin
                            MostrarErroExecucaoServer(lDsResult);
                      end;
                  finally
                      FreeAndNil(lConexao);
                  end;
                  FDsSqlNor.FieldByName('DsSql').AsString    := lDsSql;
            end;

            FDsSqlNor.Post;
      end;

      Result  := FDsSqlNor.FieldByName('DsSql').AsString;
end;

function TBrvDicionario.EncontrarInstrucaoSQLConsulta(pCdQuery : Integer) : String;
var QryAuxili : TClientDataSet;
    lDsSql    : String;

    lConexao  : TSDmRWClient;
    lDsResult : String;
begin
      if not FDsSqlCon.FindKey([pCdQuery]) then
      begin
            FDsSqlCon.Append;
            FDsSqlCon.FieldByName('NrQuery').AsInteger := pCdQuery;

            if FDcCamada = Server then
            begin
                  lDsSql := 'Select S016.*     ' +
                            ' From   S016 S016 ' +
                            ' Where  S016.NrQueCon = ' + IntToStr(pCdQuery);
                  try
                      QryAuxili      := TClientDataSet.Create(Self);
                      QryAuxili.Data := RetornaDadosSqlFixaServer(lDsSql);
                      FDsSqlCon.FieldByName('DsSql').AsString :=
                                           QryAuxili.FieldByName('TxSqlCon').AsString;
                      FDsSqlCon.FieldByName('SnIniAbe').AsString := 'N';
                  finally
                      FreeAndNil(QryAuxili);
                  end;
            end else
            begin
                  lConexao  := TSDmRWClient.Create(FSQLConnection.DBXConnection);

                  try
                      lConexao.EncontrarInstrucaoSQLConsulta(
                                              gDsCreden, lDsResult, pCdQuery, lDsSql);

                      if lDsResult[1] <> '0' then
                      begin
                            MostrarErroExecucaoServer(lDsResult);
                      end;
                  finally
                      FreeAndNil(lConexao);
                  end;
                  FDsSqlCon.FieldByName('DsSql').AsString    := lDsSql;
                  FDsSqlCon.FieldByName('SnIniAbe').AsString := 'N';
            end;

            FDsSqlCon.Post;
      end;

      Result := FDsSqlCon.FieldByName('DsSql').AsString;
end;

function TBrvDicionario.AbrirConsultaInicializacao(pCdQuery : Integer) : Boolean;
begin
      if not FDsSqlCon.FindKey([pCdQuery]) then
      begin
            EncontrarInstrucaoSQLConsulta(pCdQuery);
      end;

      Result := FDsSqlCon.FieldByName('SnIniAbe').AsString = 'S';
end;

function TBrvDicionario.CarregarTabela(pNmTabela : String) : Boolean;
var CcAuxili : TClientDataSet;
    lDsSql   : String;
begin
      Result := False;

      if Pos('$', pNmTabela) = 0 then // '$' tabela do banco
      begin
            CcAuxili    := TClientDataSet.Create(Self);

            lDsSql := 'Select S009.*,                ' +
                      '       (Select Count(S011.NrSeqKey)                 ' +
                      '         From S011 S011                             ' +
                      '         Where S011.NmTabela = S009.NmTabela and    ' +
                      '               S011.NmAtribu = S009.NmAtribu) as SnKey ' +
                      ' From   S009 S009 ' +
                      ' Where  S009.NmTabela = ' + #39 + pNmTabela + #39;
            try
                ccAuxili.Data := RetornaDadosSqlFixa(lDsSql);

                if not CcAuxili.IsEmpty then // tabela não encontrada
                begin
                      Result := True;

                      Inc(FNrTabela);
                      FNmTabela[FNrTabela].NmTabela := pNmTabela;
                      FNmTabela[FNrTabela].NrInicio := FNrAtribu + 1;
                      FNmTabela[FNrTabela].NrFinal  := FNrAtribu;
                      CcAuxili.First;

                      while not CcAuxili.Eof do
                      begin
                            inc(FNmTabela[FNrTabela].NrFinal);
                            inc(FNrAtribu);

                            FNmAtribu[FNrAtribu].NmColuna :=
                                          CcAuxili.FieldByName('NmAtribu').AsString;
                            FNmAtribu[FNrAtribu].DsAtribu :=
                                           CcAuxili.FieldByName('DsAtribu').AsString;
                            FNmAtribu[FNrAtribu].TpMascar :=
                                          CcAuxili.FieldByName('TpMascar').AsString;
                            FNmAtribu[FNrAtribu].DsMascar :=
                                          CcAuxili.FieldByName('DsMascar').AsString;
                            FNmAtribu[FNrAtribu].SnKey    :=
                                             '1' = CcAuxili.FieldByName('SnKey').AsString;
                            FNmAtribu[FNrAtribu].DsHint   :=
                                          CcAuxili.FieldByName('DsHint').AsString;
                            FNmAtribu[FNrAtribu].DsHelp   :=
                                          CcAuxili.FieldByName('DsHelp').AsString;
                            FNmAtribu[FNrAtribu].TmAtribu :=
                                          CcAuxili.FieldByName('TmAtribu').AsInteger;
                            FNmAtribu[FNrAtribu].TpAtribu :=
                                          CcAuxili.FieldByName('TpAtribu').AsString;

                            FNmAtribu[FNrAtribu].CdPermis := PermissaoAtributo(pNmTabela,
                                                           FNmAtribu[FNrAtribu].NmColuna);

                            CarregarDominiosDoAtributo(pNmTabela,
                                                       FNmAtribu[FNrAtribu].NmColuna,
                                                       FNmAtribu[FNrAtribu].VrDomini,
                                                       FNmAtribu[FNrAtribu].DsDomini);
                            CcAuxili.Next;
                      end;
                end;
            finally
                FreeAndNil(CcAuxili);
            end;
      end;
end;

procedure TBrvDicionario.CarregarDominiosDoAtributo(
                                               pNmTabela : String;
                                               pNmAtribu : String; var pVrDomini : String;
                                           var pDsDomini : String);
var CcAuxili : TClientDataSet;
    lDsSql   : String;
begin
      pDsDomini := '';
      pVrDomini := '';
      CcAuxili  := TClientDataSet.Create(Self);
      pNmAtribu := UpperCase(pNmAtribu);

      lDsSql    := 'Select S010.VrDomini, S010.DsDomini ' +
                   ' From   S010 S010 ' +
                   ' Where  S010.NmTabela = ' + #39 + pNmTabela + #39 +
                   ' and    S010.NmAtribu = ' + #39 + pNmAtribu + #39;
      try
          ccAuxili.Data := RetornaDadosSqlFixa(lDsSql);

          CcAuxili.First;

          while not CcAuxili.Eof do
          begin
                pDsDomini   := pDsDomini + ',"' +
                               CcAuxili.FieldByName('DsDomini').AsString + '"';
                pVrDomini   := pVrDomini + ',"' +
                               CcAuxili.FieldByName('VrDomini').AsString + '"';
                CcAuxili.Next;
          end;

          delete(pDsDomini, 1, 1);
          delete(pVrDomini, 1, 1);
      finally
          FreeAndNil(CcAuxili);
      end;
end;

function TBrvDicionario.ParametroDaEmpresaServer(pNrParame : Integer;
                                                            pCdEmpres: Integer) : Variant;
var lCdsParam : TClientDataSet;
    lDsParam  : String;
begin
      try
           lDsParam := '<%CdEmpres%>;' + IntToStr(pCdEmpres) + #13 +
                       '<%NrParame%>;' + IntToStr(pNrParame);

           lCdsParam := TClientDataSet.Create(Self);
           lCdsParam.Data := RetornaDadosSqlCadastro(62, lDsParam, Name);
           Result := lCdsParam.FieldByName('VrParame').Value;

      finally
           FreeAndNil(lCdsParam);
      end;

end;

function TBrvDicionario.ParametroDaEmpresa(pNrParame : Integer;
                                           pCdEmpres : Integer) : Variant;
var lConexao  : TSDmRWClient;
    lDsResult : String;
begin
      if FDcCamada = Server then
      begin
            Result := ParametroDaEmpresaServer(pNrParame, pCdEmpres);
      end else
      begin
            lConexao  := TSDmRWClient.Create(FSQLConnection.DBXConnection);

            try
                Result := lConexao.ParametroDaEmpresa(gDsCreden, lDsResult,
                                                                   pNrParame, pCdEmpres);
                if lDsResult[1] <> '0' then
                begin
                      MostrarErroExecucaoServer(lDsResult);
                end;
            finally
                FreeAndNil(lConexao);
            end;
      end;
end;

function  TBrvDicionario.PermissaoAtributo(pNmTabela : String; pNmAtribu : String) : Byte;
var lDataSet  : TClientDataSet;
    lCdPerGru : Integer;
begin
{
 tabela de permissões e valores (quanto menor, melhor)
 +------------+-----+-----+-----+-----+-----+
 | Permissão  | Ler | Alt | Inc | Exc | Gru |
 +------------+-----+-----+-----+-----+-----+
 | Usuário    |  4  |  3  |  2  |  1  |  0  |
 +------------+-----+-----+-----+-----+-----+
 | Grupo      |  3  |  2  |  1  |  0  |     |
 +------------+-----+-----+-----+-----+-----+
}
      if  gSnGruAdm or (FDcCamada = Server) then
      begin
            Result := 1; // Permissão máxima
      end else
      begin
            try
                lDataSet := TClientDataSet.Create(Self);
                lDataSet.Data := RetornaDadosSqlCadastro(31,
                                        '<%CdUsuari%>;' + IntToStr(UserCode) + #13 +
                                        '<%NmTabela%>;' + pNmTabela          + #13 +
                                        '<%NmAtribu%>;' + pNmAtribu,
                                        Name);

                if lDataSet.Eof then
                begin
                      Result :=  1; // Permissão máxima
                end else
                begin
                      Result := 9;

                      while not lDataSet.Eof do
                      begin
                            if Trim(lDataSet.FieldByName('TpPerUsu').AsString) = '' then
                            begin
                                  // Sem Restrição par ao usuário
                                  if Trim(lDataSet.FieldByName('TpPerGru').AsString) = '' then
                                  begin
                                        // Sem Restrição para o Grupo
                                        Result :=  1; // Permissão máxima
                                  end else
                                  begin
                                        lCdPerGru := lDataSet.FieldByName('TpPerGru').AsInteger + 1;

                                        if Result > lCdPerGru then
                                        begin
                                              Result := lCdPerGru;
                                        end;
                                  end;
                            end else
                            begin
                                  if Result >
                                           lDataSet.FieldByName('TpPerUsu').AsInteger then
                                  begin
                                        Result :=
                                               lDataSet.FieldByName('TpPerUsu').AsInteger;
                                  end;
                            end;
                            lDataSet.Next;
                      end;
                end;
            finally
                FreeAndNil(lDataSet);
            end;
      end;
end;

procedure TBrvDicionario.SetCodigoEmpresa(Value : TStrings);
begin
      FCdempres.Assign(Value);
end;

procedure TBrvDicionario.SetNomeEmpresas(Value : TStrings);
begin
      FNmEmpres.Assign(Value);
end;

// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
function TBrvDicionario.RetornaDadosSqlFixa(pDsSql: String): OleVariant;
begin
      if FDcCamada = Server then
      begin
            Result := RetornaDadosSqlFixaServer(pDsSql);
      end else
      begin
            Result := RetornaDadosSqlFixaCliente(pDsSql);
      end;
end;

function TBrvDicionario.RetornaDadosSqlCadastro(pNrSql    : Integer;
                                                pDsParams : String;
                                                pNmFormul : String): OleVariant;
begin
      if FDcCamada = Server then
      begin
            Result := RetornaDadosSqlCadastroServer(pNrSql, pDsParams, pNmFormul);
      end else
      begin
            Result := RetornaDadosSqlCadastroCliente(pNrSql, pDsParams, pNmFormul);
      end;
end;

function TBrvDicionario.RetornaDadosSqlCadastroServer(pNrSql    : Integer;
                                                      pDsParams : String;
                                                      pNmFormul : String): OleVariant;
var lDsSql : String;
    lDsParams : TStrings;
begin
      lDsSql := EncontrarInstrucaoSQL(pNrSql, pNmFormul);
      try
          lDsParams := TStringList.Create;
          lDsParams.Text := pDsParams;
          lDsSql := SubstituirParametrosSQL(lDsSql, lDsParams);
      finally
          FreeAndNil(lDsParams);
      end;

      try
          Result := RetornaDadosSqlFixaServer(lDsSql);
      except
          on E : Exception do
          begin
                raise Exception.Create(E.Message + #13#13 +
                                      'QueryCode = ' + IntToStr(pNrSql));
          end;
      end;
end;

function TBrvDicionario.RetornaDadosSqlCadastroCliente(pNrSql    : Integer;
                                                       pDsParams : String;
                                                       pNmFormul : String): OleVariant;
var lConexao  : TSDmRWClient;
    lDsResult : String;
begin
      lConexao  := TSDmRWClient.Create(FSQLConnection.DBXConnection);

      try
       // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
       // =-=-=-=-=-=  Carregando Tabelas  -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
       // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          Screen.Cursor := crHourGlass;
          Result  := lConexao.RetornaDadosSqlCadastro(gDsCreden, lDsResult,
                                                      pNrSql,    pDsParams, pNmFormul);
          if lDsResult[1] <> '0' then
          begin
                MostrarErroExecucaoServer(lDsResult);
          end;
      finally
          FreeAndNil(lConexao);
          Screen.Cursor := crDefault;
      end;
end;

function TBrvDicionario.DadosTabelaVazia : OleVariant;
begin
      Result := RetornaDadosSqlFixa('Select NmTabela From S008 Where NmTabela is null');
end;

function TBrvDicionario.RetornaDadosSqlFixaServer(pDsSql: String): OleVariant;
var CcAuxili : TClientDataSet;
    QcAuxili : TSQLDataSet;
    DpAuxili : TBrvProvider;
begin
      try
          QcAuxili      := TSqlDataSet.Create(Self);
          DpAuxili      := TBrvProvider.Create(Self);
          CcAuxili      := TClientDataSet.Create(Self);

          try
              pDsSql := TraduzirInstrucaoSQL(pDsSql);
          except
              Result := DadosTabelaVazia;
              raise;
          end;

          QcAuxili.GetMetadata   := False;
          QcAuxili.SQLConnection := FSQLConnection;

          DpAuxili.DataSet := QcAuxili;
          DpAuxili.Name    := 'DpAuxili' + IntToStr(Random(500000));
          DpAuxili.Options := [poAllowCommandText,poUseQuoteChar];

          CcAuxili.ProviderName := DpAuxili.Name;
          ccAuxili.CommandText  := pDsSql;
          try
              ccAuxili.Open;
              Result := ccAuxili.Data;
          except
              on E : Exception do
              begin
                    Result := DadosTabelaVazia;
                    raise Exception.Create('Erro ao Executar instrução SQL:' + #13#13 +
                                           pDsSql + #13#13 +
                                           E.Message);
              end;
          end;
      finally
          CcAuxili.Close;

          FreeAndNil(CcAuxili);
          FreeAndNil(DpAuxili);
          FreeAndNil(QcAuxili);
      end;
end;

function TBrvDicionario.RetornaValorColunaTabela(pNmTabela : String;
                                                 pNmColRet : String;
                                                 pNmColFil : String;
                                                 pNmValFil : String): String;
var lDsSql    : AnsiString;
    lCpDadTab : TClientDataSet;
begin
      try
          lDsSql := ' Select ' + pNmColRet + ' From ' +  pNmTabela +
                    ' Where '  + pNmColFil + ' = '    +  pNmValFil;

          lCpDadTab      := TClientDataSet.Create(Self);
          lCpDadTab.Data := RetornaDadosSqlFixa(lDsSql);

          Result := lCpDadTab.Fields[0].AsString;

          lCpDadTab.Close;
      finally
          FreeAndNil(lCpDadTab);
      end;
end;

function TBrvDicionario.RetornaDadosSqlFixaCliente(pDsSql : String): OleVariant;
var lConexao  : TSDmRWClient;
    lDsResult : String;
begin
      lConexao  := TSDmRWClient.Create(FSQLConnection.DBXConnection);

      try
       // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
       // =-=-=-=-=-=  Carregando Tabelas  -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
       // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          Screen.Cursor := crHourGlass;
          Result  := lConexao.RetornaDadosSqlFixa(gDsCreden, lDsResult, pDsSql);
          if lDsResult[1] <> '0' then
          begin
                MostrarErroExecucaoServer(lDsResult);
          end;
      finally
          FreeAndNil(lConexao);
          Screen.Cursor := crDefault;
      end;
end;

procedure TBrvDicionario.VerificarMensagemServidorAplicacao(pDsResult : String);
begin
      if pDsResult = '' then
      begin
            //raise Exception.Create('Sem resposta do servidor!');
      end else
      begin
            if Copy(pDsResult, 1, 3) = '1; ' then
            begin
                  Delete(pDsResult, 1, 3);
                  raise Exception.Create('LogosApl: ' + pDsResult);
            end;
      end;
end;

// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// =-=-=-=-=-=-= Procedimentos para Substituir Parâmetros da SQL
// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
function TBrvDicionario.SubstituirParametrosSQL(pDsSql    : String;
                                                pDsParams : TStrings) : String;
var lNrLinha  : Integer ;
    lDsParam  : String ;
    lDsConteu : String ;
    lNrPosPon : Integer ;
begin
      Result := pDsSql;
      if Pos('%>', Result) <> 0 then
      begin
            Result := StringReplace(Result, '<%EMPRESAS%>', CorpCommaCodes, [rfReplaceAll]);

            for lNrLinha := 0 to pDsParams.Count -1 do
            begin
                  lNrPosPon := Pos(';', pDsParams.Strings[lNrLinha]);
                  lDsParam  := Copy(pDsParams.Strings[lNrLinha], 1 , lNrPosPon -1);
                  lDsConteu := Copy(pDsParams.Strings[lNrLinha], lNrPosPon + 1,
                                                     Length(pDsParams.Strings[lNrLinha]));

                  if  lDsParam = lDsConteu then
                  begin
                        Result := ExcluirExpressaoParametro(Result, lDsConteu);
                  end else
                  begin
                        Result := StringReplace(Result, lDsParam, lDsConteu,
                                                             [rfReplaceAll,rfIgnoreCase]);
                  end;
            end;
      end;
end;

function TBrvDicionario.ExcluirExpressaoParametro(pDsSql:    String;
                                                  pDsConteu: String): String;
var lNrPosFim : Integer;
    lNrPosAnt : Integer;
    lDsAnteri : String;
begin
      lNrPosFim := Pos(pDsConteu, pDsSql);
   // =-=-=-=-=-=-=-=-=-=-=-=-=
   // Excluindo parâmetro
   // =-=-=-=-=-=-=-=-=-=-=-=-=
      System.Delete(pDsSql, lNrPosFim, Length(pDsConteu));

   // =-=-=-=-=-=-=-=-=-=-=-=-=
   // Excluindo espaco antes do parâmetro
   // =-=-=-=-=-=-=-=-=-=-=-=-=
      if  (pDsSql[lNrPosFim] = ')') or (pDsSql[lNrPosFim] = '') then
      begin
           Dec(lNrPosFim);
      end;

      while ((pDsSql[lNrPosFim] = ' ') or (pDsSql[lNrPosFim] = #13) or
             (pDsSql[lNrPosFim] = #10)) and (pDsSql <> '') do
      begin
            System.Delete(pDsSql, lNrPosFim, 1);
            Dec(lNrPosFim);
      end;
   // =-=-=-=-=-=-=-=-=-=-=-=-=
   // Excluindo operador lógico
   // =-=-=-=-=-=-=-=-=-=-=-=-=
      while (pDsSql[lNrPosFim] <> ' ') and (pDsSql[lNrPosFim] <> #13) and
            (pDsSql[lNrPosFim] <> #10) and (pDsSql <> '') do
      begin
            System.Delete(pDsSql, lNrPosFim, 1);
            Dec(lNrPosFim);
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=
   // Excluindo espaco antes do operador lógico
   // =-=-=-=-=-=-=-=-=-=-=-=-=
      while ((pDsSql[lNrPosFim] = ' ') or (pDsSql[lNrPosFim] = #13) or
             (pDsSql[lNrPosFim] = #10)) and (pDsSql <> '') do
      begin
            System.Delete(pDsSql, lNrPosFim, 1);
            Dec(lNrPosFim);
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=
   // Excluindo coluna de comparação
   // =-=-=-=-=-=-=-=-=-=-=-=-=
      while (pDsSql[lNrPosFim] <> ' ') and (pDsSql[lNrPosFim] <> #13) and
            (pDsSql[lNrPosFim] <> #10) and (pDsSql[lNrPosFim] <> '(')  and
            (pDsSql <> '') do
      begin
            System.Delete(pDsSql, lNrPosFim, 1);
            Dec(lNrPosFim);
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=
   // Excluindo espaco antes da coluna de comparação
   // =-=-=-=-=-=-=-=-=-=-=-=-=
      while ((pDsSql[lNrPosFim] = ' ') or (pDsSql[lNrPosFim] = #13) or
             (pDsSql[lNrPosFim] = #10)) and (pDsSql <> '') do
      begin
            System.Delete(pDsSql, lNrPosFim, 1);
            Dec(lNrPosFim);
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=
   // Econtrando operador antes da coluna de comparação
   // =-=-=-=-=-=-=-=-=-=-=-=-=
      lNrPosAnt := lNrPosFim;
      lDsAnteri := '';

      while (pDsSql[lNrPosAnt] <> ' ') and (pDsSql[lNrPosAnt] <> #13) and
            (pDsSql[lNrPosAnt] <> #10) and
            (pDsSql <> '') do
      begin
            lDsAnteri := pDsSql[lNrPosAnt] + lDsAnteri;
            Dec(lNrPosAnt);
      end;

      if  UpperCase(lDsAnteri) <> 'WHERE' then
      begin
            if  Pos('(', lDsAnteri) > 0 then
            begin
                  pDsSql := ExcluirExpressaoParametroParenteses(pDsSql, lNrPosAnt);
            end else
            begin
               // =-=-=-=-=-=-=-=-=-=-=-=-=
               // Excluindo operador antes da coluna de comparação
               // =-=-=-=-=-=-=-=-=-=-=-=-=
                  System.Delete(pDsSql, lNrPosAnt + 1, Length(lDsAnteri));
            end;
      end else
      begin
            pDsSql := ExcluirExpressaoParametroWhere(pDsSql, lNrPosFim,
                                                     lNrPosAnt, lDsAnteri);
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=

      Result := pDsSql;
end;

function TBrvDicionario.ExcluirExpressaoParametroParenteses(pDsSql    : String;
                                                            pNrPosPar : Integer) : String;
var lNrPosAtu : Integer;
    lDsParent : String;
    lDsAnteri : String;
    lqtparent : Integer;
    lnrposini : Integer;
begin
      lNrPosAtu := pNrPosPar + 2;
      lnrposini := lNrPosAtu;
      lDsParent := '';
      lqtparent := 1;

      while lqtparent > 0 do
      begin
            if pDsSql[lNrPosAtu] = '(' then
            begin
                  Inc(lqtparent);
                  lDsParent := lDsParent + pDsSql[lNrPosAtu];
            end else
            begin
                  if pDsSql[lNrPosAtu] = ')' then
                  begin
                        Dec(lqtparent);

                        if lqtparent > 0 then
                        begin
                              lDsParent := lDsParent + pDsSql[lNrPosAtu];
                        end;
                  end else
                  begin
                        lDsParent := lDsParent + pDsSql[lNrPosAtu];
                  end;
            end;
            Inc(lNrPosAtu);
      end;

      lDsParent := Trim(lDsParent);

      if  lDsParent <> '' then
      begin
            System.Delete(pDsSql, lnrposini, lNrPosAtu - lnrposini - 1);

            while (lDsParent[1] <> ' ') and (lDsParent[1] <> #13) and
                  (lDsParent[1] <> #10) and (lDsParent <> '') do
            begin
                  System.Delete(lDsParent, 1, 1);
            end;

            System.Insert(lDsParent, pDsSql, lnrposini);
      end else
      begin
            System.Delete(pDsSql, lnrposini - 1, lNrPosAtu - lnrposini + 1);
            lnrposini := lnrposini - 1 - (lNrPosAtu - lnrposini);

            while ((pDsSql[lnrposini] = ' ') or (pDsSql[lnrposini] = #13) or
                   (pDsSql[lnrposini] = #10)) and (lnrposini > 0) do
            begin
                  System.Delete(pDsSql, lnrposini, 1);
                  Dec(lnrposini);
            end;

            if  pDsSql[lnrposini] = '(' then
            begin
                  Inc(lnrposini);
                  while ((pDsSql[lnrposini] = ' ') or (pDsSql[lnrposini] = #13) or
                         (pDsSql[lnrposini] = #10)) and (lnrposini > 0) do
                  begin
                        System.Delete(pDsSql, lnrposini, 1);
                  end;

                  while (pDsSql[lnrposini] <> ' ') and (pDsSql[lnrposini] <> #13) and
                        (pDsSql[lnrposini] <> #10) and (pDsSql[lnrposini] <> ')') and
                        (lnrposini > 0) do
                  begin
                        System.Delete(pDsSql, lnrposini, 1);
                  end;
            end else
            begin
               // =-=-=-=-=-=-=-=-=-=-=-=-=
               // Econtrando operador antes dos parenteses
               // =-=-=-=-=-=-=-=-=-=-=-=-=
                  lDsAnteri := '';
                  lNrPosAtu := lnrposini;

                  while (pDsSql[lNrPosAtu] <> ' ') and (pDsSql[lNrPosAtu] <> #13) and
                        (pDsSql[lNrPosAtu] <> #10) and (pDsSql[lNrPosAtu] <> '(') and
                        (lNrPosAtu > 0) do
                  begin
                        lDsAnteri := Copy(pDsSql, lNrPosAtu, 1) + lDsAnteri;
                        Dec(lNrPosAtu);
                  end;

                  lDsAnteri := Trim(lDsAnteri);

                  if  UpperCase(lDsAnteri) <> 'WHERE' then
                  begin
                        while (pDsSql[lnrposini] <> ' ') and
                              (pDsSql[lnrposini] <> #13) and
                              (pDsSql[lnrposini] <> #10) and
                              (pDsSql[lnrposini] <> '(') and
                              (lnrposini > 0) do
                        begin
                              System.Delete(pDsSql, lnrposini, 1);
                              Dec(lnrposini);
                        end;
                  end else
                  begin
                        pDsSql := ExcluirExpressaoParametroWhere(pDsSql,
                                              lnrposini + 1, lNrPosAtu, lDsAnteri);
                  end;
            end;
      end;

      Result := pDsSql;
end;

function TBrvDicionario.ExcluirExpressaoParametroWhere(pDsSql    : String;
                                                       pNrPosFim : Integer;
                                                       pNrPosAnt : Integer;
                                                       pDsAnteri : String) : String;
var lNrPosPos : Integer;
    lDsPoster : String;
begin
      lNrPosPos := pNrPosFim + 1;
      lDsPoster := '';

      if  (pDsSql[lNrPosPos] = ' ') or (pDsSql[lNrPosPos] = #10) or
          (pDsSql[lNrPosPos] = #13) then
      begin
         // =-=-=-=-=-=-=-=-=-=-=-=-=
         // Ignorando espaços após a coluna de comparação
         // =-=-=-=-=-=-=-=-=-=-=-=-=

            while ((pDsSql[lNrPosPos] = ' ') or (pDsSql[lNrPosPos] = #10) or
                   (pDsSql[lNrPosPos] = #13))                          and
                  ((lNrPosPos <= Length(pDsSql))) do
            begin
                  Inc(lNrPosPos);
            end;
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=
   // Encontrando operador depois da coluna de comparação
   // =-=-=-=-=-=-=-=-=-=-=-=-=
      while (pDsSql[lNrPosPos] <> ' ') and (pDsSql[lNrPosPos] <> #13) and
            (pDsSql[lNrPosPos] <> #10) and (lNrPosPos <= Length(pDsSql)) do
      begin
            lDsPoster := lDsPoster + pDsSql[lNrPosPos];
            Inc(lNrPosPos);
      end;

      lDsPoster := Trim(lDsPoster);

      if  lDsPoster = ''  then
      begin
            System.Delete(pDsSql, pNrPosAnt + 1, Length(pDsAnteri));
      end else
      begin
            System.Delete(pDsSql, pNrPosFim + 1, lNrPosPos - pNrPosFim - 1);
      end;

      Result := pDsSql;
end;

// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// =-= Rotinas de verificação de permissões no atributo -=-=-=-=-=-=-=-=-=-=-=-=
// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
function TBrvDicionario.TemPermissaoExclusaoAtributo(NmTabela : String;
                                                     NmAtribu : String) : Boolean;
begin
      Result := PermissaoUsuarioAtributo(NmTabela, NmAtribu) >= 4;
end;

function TBrvDicionario.TemPermissaoAlteracaoAtributo(NmTabela : String;
                                                      NmAtribu : String) : Boolean;
begin
      Result := PermissaoUsuarioAtributo(NmTabela, NmAtribu) >= 3;
end;

function TBrvDicionario.TemPermissaoInclusaoAtributo(NmTabela : String;
                                                     NmAtribu : String) : Boolean;
begin
      Result := PermissaoUsuarioAtributo(NmTabela, NmAtribu) >= 2;
end;

function TBrvDicionario.TemPermissaoVisualizacaoAtributo(NmTabela : String;
                                                         NmAtribu : String) : Boolean;
begin
      Result := PermissaoUsuarioAtributo(NmTabela, NmAtribu) >= 1;
end;

function TBrvDicionario.TemPermissaoTotalAtributo(NmTabela : String;
                                                  NmAtribu : String) : Boolean;
begin
      Result := PermissaoUsuarioAtributo(NmTabela, NmAtribu) >= 4;
end;

function TBrvDicionario.PermissaoUsuarioAtributo(NmTabela : String;
                                                 NmAtribu : String) : Byte;
var NrTabela : Integer;
    SnAchou  : Boolean;
    NrAtribu : Integer;
begin
      Result   := 0;
      NrTabela := 0;
      SnAchou  := False;

      while (NrTabela < FNrTabela) and (not SnAchou) do
      begin
            if  FNmTabela[NrTabela].NmTabela = NmTabela then
            begin
                  SnAchou := True;
            end else
            begin
                  inc(NrTabela);
            end;
      end;

      if not SnAchou then
      begin
            SnAchou  := CarregarTabela(NmTabela);
            NrTabela := FNrTabela;
      end;

      if SnAchou then
      begin
            NrAtribu := FNmTabela[NrTabela].NrInicio;

            while (NrAtribu <= FNmTabela[NrTabela].NrFinal) and (NrAtribu <= FNrAtribu) do
            begin
                  if FNmAtribu[NrAtribu].NmColuna = NmAtribu then
                  begin
                        Result   := FNmAtribu[NrAtribu].CdPermis;
                        NrAtribu := FNrAtribu;
                  end;

                  Inc(NrAtribu);
            end;
      end;
end;

procedure TBrvDicionario.AtualizarDicionarioServer;
var lConexao  : TSDmRWClient;
    lDsResult : String;
begin
      lConexao  := TSDmRWClient.Create(FSQLConnection.DBXConnection);

      try
          lConexao.AtualizarDicionarioDados(gDsCreden, lDsResult);

          if lDsResult[1] <> '0' then
          begin
                MostrarErroExecucaoServer(lDsResult);
          end;
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TBrvDicionario.AtualizarLog(pNmTabela : String;  pDsChaPri : String;
                                      pTpOperac : Integer; pCdsLog   : OleVariant;
                                      pNmFormul : String;  pCdUsuari : Integer;
                                      pTxSql    : String);
var lConexao    : TSDmSisClient;
    lDsResult   : String;
    QryAuxili   : TSqlDataSet;
    CdsAuxili   : TClientDataSet;
    DtsAuxili   : TDataSetProvider;
    lCdsLog     : TClientDataSet;
begin
      lCdsLog := TClientDataSet.Create(Self);

      try
          lCdsLog.Data := pCdsLog;
          lCdsLog.First;

          if not lCdsLog.Eof then
          begin
                if FDcCamada = Client then
                begin
                      lConexao := TSDmSisClient.Create(FSQLConnection.DBXConnection);

                      try
                          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                          // =-=-=-= Criarndo Provider no servidor de aplicação
                          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                          lConexao.AtualizarLog(gDsCreden, lDsResult, pNmTabela, pCdUsuari,
                                                pDsChaPri, pTpOperac, pNmFormul, pCdsLog);

                          VerificarMensagemServidorAplicacao(lDsResult);
                      finally
                          FreeAndNil(lConexao);
                      end;
                end else
                begin
                      if Trim(pNmFormul) = '' then
                      begin
                            raise Exception.Create(
                                       'Não informado o formulário para gravação do log');
                      end;

                      if pCdUsuari = 0 then
                      begin
                            raise Exception.Create(
                                           'Não informado usuário para gravação do log');
                      end;

                      try
                          QryAuxili               := TSqlDataSet.Create(Self);
                          QryAuxili.Name          := 'QryLog_GravaLogDicionario';
                          QryAuxili.GetMetadata   := False;
                          QryAuxili.SQLConnection := FSQLConnection;
//                          QryAuxili.BrDicionario  := Self;

                          DtsAuxili               := TDataSetProvider.Create(Self);
                          DtsAuxili.Name          := 'DtsLog_GravaLogDicionario';
                          DtsAuxili.Options       := [poAllowCommandText,poUseQuoteChar];
                          DtsAuxili.DataSet       := QryAuxili;

                          CdsAuxili               := TClientDataSet.Create(Self);
                          CdsAuxili.Name          := 'CdsLog_GravaLogDicionario';
//                          CdsAuxili.BrDicionario  := Self;
//                          CdsAuxili.BrGravaLog    := False;
                          CdsAuxili.ProviderName  := 'DtsLog_GravaLogDicionario';
                          CdsAuxili.CommandText   := ' Select S039.* '  +
                                                     ' From S039 S039 ' +
                                                     ' Where S039.NmTabela is null';
                          CdsAuxili.Open;

                          while not lCdsLog.Eof do
                          begin
                                CdsAuxili.Append;
                                CdsAuxili.FieldByName('NmTabela').AsString   := pNmTabela;
                                CdsAuxili.FieldByName('NmAtribu').AsString   :=
                                          lCdsLog.FieldByName('NmAtribu').AsString;
                                CdsAuxili.FieldByName('CdUsuari').AsInteger  := pCdUsuari;
                                CdsAuxili.FieldByName('DtAltera').AsDateTime :=
                                          DataHoraServer;
                                CdsAuxili.FieldByName('VrChave').AsString    := pDsChaPri;
                                CdsAuxili.FieldByName('TpOperac').AsInteger  := pTpOperac;
                                CdsAuxili.FieldByName('VrAtual').AsString    :=
                                          lCdsLog.FieldByName('VrAtual').AsString;
                                CdsAuxili.FieldByName('NmFormul').AsString   := pNmFormul;
                                CdsAuxili.FieldByName('TxSql').AsString      := pTxSql;
                                CdsAuxili.Post;

                                lCdsLog.Next;
                          end;
                          CdsAuxili.ApplyUpdates(0);
                      finally
                          FreeAndNil(QryAuxili);
                          FreeAndNil(DtsAuxili);
                          FreeAndNil(CdsAuxili);
                      end;
                end;
          end;
      finally
          FreeAndNil(lCdsLog);
      end;
end;

function TBrvDicionario.ProximoValorSequencial(pCdEmpres : Integer;
                                               pCdParam : Integer): Integer;
var lCdsParam : TClientDataSet;
    lNmSequen : String;
begin
      try
          lNmSequen := ParametroEmpresa(pCdEmpres, pCdParam);

          lCdsParam := TClientDataSet.Create(Self);
          lCdsParam.Data := RetornaDadosSqlFixa('Select ' + lNmSequen + '.NextVal From Dual');
          Result        := lCdsParam.Fields[0].AsInteger;

      finally
          FreeAndNil(lCdsParam);
      end;
end;

function TBrvDicionario.ParametroEmpresa(pCdEmpres : Integer; pNrParame : Integer): String;
var lCdsParam : TClientDataSet;
    lDsParam  : String;
begin
      try
          lDsParam  := '<%CdEmpres%>;'  + IntToStr(pCdEmpres) + #13 +
                       '<%NrParame%>;'  + IntToStr(pNrParame);

          lCdsParam      := TClientDataSet.Create(Self);
          lCdsParam.Data := RetornaDadosSqlCadastro(79, lDsParam, Name);

          Result := lCdsParam.FieldByName('VrParame').AsString;
      finally
          FreeAndNil(lCdsParam);
      end;
end;

function TBrvDicionario.DataProximoDiaUtil(pDtBase : TDate; pQtDias : Integer) : TDate;
var lSnDiaUti : Boolean;
    lCPEmpUsu : TClientDataSet;
begin
      lSnDiaUti := False;
      Result   := pDtBase + pQtDias;

      try
          lCPEmpUsu      := TClientDataSet.Create(nil);
          lCPEmpUsu.Data := RetornaDadosSqlFixa(' Select S004.CpCidade, G003.CdSeqEst ' +
                                                ' From   S005, S004, G003 ' +
                                                ' Where  S005.CdEmpres = S004.CdEmpres ' +
                                                ' and    S004.CpCidade = G003.CpCidade ' +
                                                ' and    S005.CdUsuari = ' +
                                                IntToStr(UserCode));
          while (not lSnDiaUti) do
          begin
                if (DayOfWeek(Result) in [1, 7]) or
                   (EhFeriado(Result, lCPEmpUsu.FieldByName('CpCidade').AsString,
                                      lCPEmpUsu.FieldByName('CdSeqEst').AsInteger)) then
                begin
                      if pQtDias < 0 then
                      begin
                            Result := Result - 1;
                      end else
                      begin
                            Result := Result + 1;
                      end;
                end else
                begin
                      lSnDiaUti := True;
                end;
          end;
      finally
          FreeAndNil(lCPEmpUsu);
      end;
end;

function TBrvDicionario.EhFeriado(pDtFeriad : TDate; pCpCidade: String;
                                                     pCdSeqEst: Integer) : Boolean;
var lDtDia : Word ;
    lDtMes : Word ;
    lDtAno : Word ;
    lCdsDiaFer : TClientDataSet;

begin
      Result := False;
      DecodeDate(pDtFeriad, lDtAno, lDtMes, lDtDia);

      lCdsDiaFer := TClientDataSet.Create(Self);

      try
          lCdsDiaFer.Data := RetornaDadosSqlFixa(
              ' Select G016.CdFeriad ' +
              ' From   G016 G016 ' +
              ' Where  (G016.DtAnoFer = ' +  IntToStr(lDtAno) + ' or ' +
                                      'G016.DtAnoFer = 0 or G016.DtAnoFer is null) and ' +
              '        G016.DtMesFer = ' + IntToStr(lDtMes) + ' and ' +
              '        G016.DtDiaFer = ' + IntToStr(lDtDia) + ' and ' +
              '       (G016.CpCidade = ' + #39 + pCpCidade + #39 +
                                                       ' or G016.CpCidade is null) and ' +
              '       (G016.CdSeqEst = ' + IntToStr(pCdSeqEst) +
                                                       ' or G016.CdSeqEst is null)');

          if  not lCdsDiaFer.IsEmpty then
          begin
                Result := True;
          end;
      finally
          FreeAndNil(lCdsDiaFer);
      end;
end;

procedure Register;
begin
      RegisterComponents('Bravo Banco', [TBrvDicionario]);
end;

end.

unit BrvGeraRelatorio;

interface

uses
  SysUtils, Classes, DbClient, DB, BrvDicionario, uClaImp, SqlExpr, BrvRelAsc, ShellApi,
  Windows, BrvServerProgress;

type
  TBrvGeraRelatorio = class(TComponent)
  private
    { Private declarations }
    gCdsParams : TClientDataSet;
    gCdsData   : TClientDataSet;
    gDsDicion  : TBrvDicionario;
    gSQLConImp : TSQLConnection;
    gRelAsc    : TBrvRelAsc;
    gTpRelato  : String;
    procedure MostrarRelatorioGeradoCaracter(pDsRelato: WideString);
    procedure MostrarRelatorioGeradoGrafico(pCdsResult : TClientDataSet);
    function NomeArquivoPDFGerado: String;
    procedure CriarTabelasTemporarias;
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
    procedure IniciarRelatorio(pNmRelato, pTpRelato, pNmForm: String);
    procedure InserirParametroSQL(pNmParam : String; pDsParam : String);
    procedure ImprimirRelatorio(pBrvServerProgress: TBrvServerProgress = nil);
  published
    { Published declarations }
    property BrDicionario     : TBrvDicionario  read gDsDicion  write gDsDicion;
    property SQLConnectionImp : TSQLConnection  read gSQLConImp write gSQLConImp;
    property BrCdsData        : TClientDataSet  read gCdsData   write gCdsData;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Bravo Relatorio', [TBrvGeraRelatorio]);
end;

{ TBrvGeraRelatorio }

constructor TBrvGeraRelatorio.Create(AOwner: TComponent);
begin
      inherited;
      CriarTabelasTemporarias;
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-=-= Criando Rel Asc
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      gRelAsc := TBrvRelAsc.Create(Self);
end;

procedure TBrvGeraRelatorio.CriarTabelasTemporarias;
begin
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-=-= Criando tabela de parâmetros
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      gCdsParams := TClientDataSet.Create(Self);
      gCdsParams.FieldDefs.Clear;

      gCdsParams.FieldDefs.Add('NmParam',   ftString, 010, False);
      gCdsParams.FieldDefs.Add('DsParam',   ftString, 100, False);
      gCdsParams.CreateDataSet;
end;

destructor TBrvGeraRelatorio.Destroy;
begin
      FreeAndNil(gCdsParams);
      FreeAndNil(gRelAsc);
      inherited;
end;

procedure TBrvGeraRelatorio.IniciarRelatorio(pNmRelato : String; pTpRelato: String;
                                             pNmForm   : String);
begin
      if gDsDicion = nil then
      begin
            raise Exception.Create(Owner.Name + '.' + Name +
                                                   ': dicioinário de dados não definido');
      end;

      gTpRelato := pTpRelato;

      gCdsParams.EmptyDataSet;

      InserirParametroSQL('TpRelato', pTpRelato);
      InserirParametroSQL('NmRelato', pNmRelato);
      InserirParametroSQL('NmEmpres', gDsDicion.CorpNames[0]);
      InserirParametroSQL('NmOrigem', pNmForm);
end;

procedure TBrvGeraRelatorio.InserirParametroSQL(pNmParam : String; pDsParam : String);
begin
      if pNmParam = '' then
      begin
            raise Exception.Create('Nome do parâmetro da SQL não pode ser nulo.');
      end;

      gCdsParams.Append;
      gCdsParams.FieldByName('NmParam').AsString := pNmParam;
      gCdsParams.FieldByName('DsParam').AsString := pDsParam;
      gCdsParams.Post;
end;

procedure TBrvGeraRelatorio.ImprimirRelatorio(pBrvServerProgress: TBrvServerProgress = nil);
var lConexao   : TSDmImpClient;
    lDsResult  : String;
    lDsRelato  : WideString;
    lCdsResult : TClientDataSet;
    lData      : OleVariant;
begin
      if gSQLConImp = nil then
      begin
            raise Exception.Create(Owner.Name + '.' + Name +
                                   ': SqlConnection de impressão não definido');
      end;

      lConexao := TSDmImpClient.Create(gSQLConImp.DBXConnection);

      try
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-=-= Criando Provider no servidor de aplicação
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

          if gCdsData <> nil then
          begin
                lData := gCdsData.Data;
          end;

          if gTpRelato = 'C' then
          begin
                lDsRelato := lConexao.GerarRelatorio(gDsDicion.CredencialImp,
                                                     lDsResult, gCdsParams.Data,
                                                     lData);

                gDsDicion.VerificarMensagemServidorAplicacao(lDsResult);

                if (pBrvServerProgress <> nil) then
                begin
                      pBrvServerProgress.Stop;
                end;

                MostrarRelatorioGeradoCaracter(lDsRelato);
          end else
          begin
                try
                     lCdsResult := TClientDataSet.Create(Self);
                     try
                         lCdsResult.Data := lConexao.GerarRelatorio(
                                                     gDsDicion.CredencialImp,
                                                     lDsResult, gCdsParams.Data,
                                                     lData);
                     finally
                         gDsDicion.VerificarMensagemServidorAplicacao(lDsResult);
                     end;

                     if (pBrvServerProgress <> nil) then
                     begin
                           pBrvServerProgress.Stop;
                     end;

                     MostrarRelatorioGeradoGrafico(lCdsResult);
                finally
                     FreeAndNil(lCdsResult);
                end;
          end;
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TBrvGeraRelatorio.MostrarRelatorioGeradoCaracter(pDsRelato: WideString);
begin
      try
          gRelAsc.NovoRelatorio(gDsDicion.UserCode, '', '', '');
          gRelAsc.InserirTexto(pDsRelato);
          gRelAsc.MostrarListagemModal;
      finally
          gRelAsc.FecharListagem;
      end;
end;

procedure TBrvGeraRelatorio.MostrarRelatorioGeradoGrafico(pCdsResult : TClientDataSet);
var Pdir      : Pchar;
    lNmArquiv : String;
begin
      lNmArquiv := NomeArquivoPDFGerado;

      (pCdsResult.FieldByName('BiRelato') as TBlobField).SaveToFile(lNmArquiv);

      try
          GetMem(pDir,256);
          StrPCopy(pDir, lNmArquiv);
          ShellExecute(0, nil, pChar(lNmArquiv), nil, Pdir, SW_NORMAL);
      finally
          FreeMem(pdir,256);
      end;
end;

function TBrvGeraRelatorio.NomeArquivoPDFGerado : String;
var lNmDirApl : String;
    lDtAtual  : String;
var lDsPatTmp : array[0..255] of char;
begin
      GetTempPath(255, lDsPatTmp);
      lNmDirApl := lDsPatTmp;

      lDtAtual := gDsDicion.DataHoraServerStr;
      lDtAtual := StringReplace(lDtAtual, '/', '', [rfReplaceAll]);
      lDtAtual := StringReplace(lDtAtual, ':', '', [rfReplaceAll]);
      lDtAtual := StringReplace(lDtAtual, ' ', '', [rfReplaceAll]);

      Result := lNmDirApl + 'Cli_' +
                           FormatFloat('000000', gDsDicion.UserCode) + '_' +
                                                                        lDtAtual + '.pdf';
end;

end.

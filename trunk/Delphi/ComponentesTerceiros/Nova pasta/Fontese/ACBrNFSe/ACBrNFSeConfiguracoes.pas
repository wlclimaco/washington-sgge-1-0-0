{$I ACBr.inc}

unit ACBrNFSeConfiguracoes;

interface

uses
{$IFNDEF ACBrNFSeOpenSSL}
  ACBrCAPICOM_TLB, ACBrMSXML2_TLB,
  JwaWinCrypt, JwaWinType,
{$ENDIF}
  Classes, Sysutils, pnfsConversao, pcnConversao;

{$IFNDEF ACBrNFSeOpenSSL}
  const CAPICOM_STORE_NAME = 'My'; //My CA Root AddressBook
{$ENDIF}

type

 TProvedorClass = Class;

 TConfigCidade = record
    VersaoSoap: String;
    Prefixo2: String;
    Prefixo3: String;
    Prefixo4: String;
    Identificador: String;
    NameSpaceEnvelope: String;
    AssinaRPS: Boolean;
    AssinaLote: Boolean;
 end;

 TConfigSchema = record
    VersaoCabecalho: String;
    VersaoDados: String;
    VersaoXML: String;
    NameSpaceXML: String;
    Cabecalho: String;
    ServicoEnviar: String;
    ServicoConSit: String;
    ServicoConLot: String;
    ServicoConRps: String;
    ServicoConNfse: String;
    ServicoCancelar: String;
    ServicoGerar: String;
    ServicoEnviarSincrono: String;
    DefTipos: String;
  end;

 TConfigURL = record
    HomNomeCidade:String;
    HomRecepcaoLoteRPS: String;
    HomConsultaLoteRPS: String;
    HomConsultaNFSeRPS: String;
    HomConsultaSitLoteRPS: String;
    HomConsultaNFSe: String;
    HomCancelaNFSe: String;
    HomGerarNFSe: String;
    HomRecepcaoSincrono: String;
    ProNomeCidade:String;
    ProRecepcaoLoteRPS: String;
    ProConsultaLoteRPS: String;
    ProConsultaNFSeRPS: String;
    ProConsultaSitLoteRPS: String;
    ProConsultaNFSe: String;
    ProCancelaNFSe: String;
    ProGerarNFSe: String;
    ProRecepcaoSincrono: String;
  end;

 TCertificadosConf = class(TComponent)
  private
    FAssinaLote: Boolean;
    FAssinaRPS: Boolean;
    FSenhaCert: AnsiString;
    {$IFNDEF ACBrNFSeOpenSSL}
       FNumeroSerie: AnsiString;
       FDataVenc: TDateTime;
       FInformacao: AnsiString;
       procedure SetNumeroSerie(const Value: AnsiString);
       function GetNumeroSerie: AnsiString;
       function GetDataVenc: TDateTime;
       function GetInformacao: AnsiString;
    {$ELSE}
       FCertificado: AnsiString;
    {$ENDIF}
  public
    {$IFNDEF ACBrNFSeOpenSSL}
       function SelecionarCertificado:AnsiString;
       function GetCertificado: ICertificate2;
    {$ENDIF}
  published
    {$IFNDEF ACBrNFSeOpenSSL}
       property NumeroSerie: AnsiString read GetNumeroSerie write SetNumeroSerie;
       property DataVenc: TDateTime     read GetDataVenc;
       property Informacao: AnsiString  read GetInformacao;
    {$ELSE}
       property Certificado: AnsiString read FCertificado write FCertificado;
    {$ENDIF}
    property AssinaRPS: Boolean  read FAssinaRPS;
    property AssinaLote: Boolean read FAssinaLote;
    property Senha: AnsiString   read FSenhaCert write FSenhaCert;
  end;

 TWebServicesConf = Class(TComponent)
  private
    FProvedorClass: TProvedorClass;

    FSalvar: Boolean;
    FVisualizar : Boolean;
    FAmbiente: TpcnTipoAmbiente;
    FAmbienteCodigo: Integer;
    FProxyHost: String;
    FProxyPort: String;
    FProxyUser: String;
    FProxyPass: String;
    FAguardarConsultaRet : Cardinal;
    FTentativas : Integer;
    FIntervaloTentativas : Cardinal;
    FAjustaAguardaConsultaRet : Boolean;
    FCodigoMunicipio: Integer;
    FPrefixo2: String;
    FPrefixo3: String;
    FPrefixo4: String;
    FProvedor: TnfseProvedor;
    FxProvedor: String;
    FVersaoSoap: String;
    FIdentificador: String;
    FNameSpace: String;
    FSenhaWeb: AnsiString;
    FUserWeb: String;

    // Schemas
    FVersaoCabecalho: String;
    FVersaoDados: String;
    FVersaoXML: String;
    FURL: String;
    FCabecalho: String;
    FServicoEnviar: String;
    FServicoConSit: String;
    FServicoConLot: String;
    FServicoConRps: String;
    FServicoConNfse: String;
    FServicoCancelar: String;
    FServicoGerar: String;
    FServicoEnviarSincrono: String;
    FDefTipos: String;

    // URLs
    FHomNomeCidade:String;
    FHomRecepcaoLoteRPS: String;
    FHomConsultaLoteRPS: String;
    FHomConsultaNFSeRPS: String;
    FHomConsultaSitLoteRPS: String;
    FHomConsultaNFSe: String;
    FHomCancelaNFSe: String;
    FHomGerarNFSe: String;
    FHomRecepcaoSincrono: String;
    FProNomeCidade:String;
    FProRecepcaoLoteRPS: String;
    FProConsultaLoteRPS: String;
    FProConsultaNFSeRPS: String;
    FProConsultaSitLoteRPS: String;
    FProConsultaNFSe: String;
    FProCancelaNFSe: String;
    FProGerarNFSe: String;
    FProRecepcaoSincrono: String;
    FConsultaLoteAposEnvio: Boolean;

    procedure SetAmbiente(AValue: TpcnTipoAmbiente);
    procedure SetTentativas(const Value: Integer);
    procedure SetIntervaloTentativas(const Value: Cardinal);
  public
    constructor Create(AOwner: TComponent); override;
    procedure SetConfigMunicipio(aPath: String = '');
  published
    property Salvar: Boolean               read FSalvar     write FSalvar     default False;
    property Visualizar: Boolean           read FVisualizar write FVisualizar default False;
    property Ambiente: TpcnTipoAmbiente    read FAmbiente   write SetAmbiente default taHomologacao;
    property AmbienteCodigo: Integer       read FAmbienteCodigo;
    property ProxyHost: String             read FProxyHost  write FProxyHost;
    property ProxyPort: String             read FProxyPort  write FProxyPort;
    property ProxyUser: String             read FProxyUser  write FProxyUser;
    property ProxyPass: String             read FProxyPass  write FProxyPass;
    property AguardarConsultaRet: Cardinal read FAguardarConsultaRet write FAguardarConsultaRet;
    property Tentativas : Integer read FTentativas write SetTentativas default 18;
    property IntervaloTentativas : Cardinal read FIntervaloTentativas write SetIntervaloTentativas;
    property AjustaAguardaConsultaRet : Boolean read FAjustaAguardaConsultaRet write FAjustaAguardaConsultaRet;
    property CodigoMunicipio: Integer read FCodigoMunicipio write FCodigoMunicipio;
    property Prefixo2: String read FPrefixo2;
    property Prefixo3: String read FPrefixo3;
    property Prefixo4: String read FPrefixo4;
    property Provedor: TnfseProvedor read FProvedor;
    property xProvedor: String read FxProvedor;
    property VersaoSoap: String read FVersaoSoap;
    property Identificador: String read FIdentificador;
    property NameSpace: String read FNameSpace;
    property SenhaWeb: AnsiString read FSenhaWeb write FSenhaWeb;
    property UserWeb: String read FUserWeb write FUserWeb;
    property ConsultaLoteAposEnvio: Boolean read FConsultaLoteAposEnvio write FConsultaLoteAposEnvio;

    // Schemas
    property VersaoCabecalho: String read FVersaoCabecalho;
    property VersaoDados: String read FVersaoDados;
    property VersaoXML: String read FVersaoXML;
    property URL: String read FURL;
    property Cabecalho: String read FCabecalho;
    property ServicoEnviar: String read FServicoEnviar;
    property ServicoConSit: String read FServicoConSit;
    property ServicoConLot: String read FServicoConLot;
    property ServicoConRps: String read FServicoConRps;
    property ServicoConNfse: String read FServicoConNfse;
    property ServicoCancelar: String read FServicoCancelar;
    property ServicoGerar: String read FServicoGerar;
    property ServicoEnviarSincrono: String read FServicoEnviarSincrono;
    property DefTipos: String read FDefTipos;

    // URLs
    property HomNomeCidade: String read FHomNomeCidade;
    property HomRecepcaoLoteRPS: String read FHomRecepcaoLoteRPS;
    property HomConsultaLoteRPS: String read FHomConsultaLoteRPS;
    property HomConsultaNFSeRPS: String read FHomConsultaNFSeRPS;
    property HomConsultaSitLoteRPS: String read FHomConsultaSitLoteRPS;
    property HomConsultaNFSe: String read FHomConsultaNFSe;
    property HomCancelaNFSe: String read FHomCancelaNFSe;
    property HomGerarNFSe: String read FHomGerarNFSe;
    property HomRecepcaoSincrono: String read FHomRecepcaoSincrono;
    property ProNomeCidade: String read FProNomeCidade;
    property ProRecepcaoLoteRPS: String read FProRecepcaoLoteRPS;
    property ProConsultaLoteRPS: String read FProConsultaLoteRPS;
    property ProConsultaNFSeRPS: String read FProConsultaNFSeRPS;
    property ProConsultaSitLoteRPS: String read FProConsultaSitLoteRPS;
    property ProConsultaNFSe: String read FProConsultaNFSe;
    property ProCancelaNFSe: String read FProCancelaNFSe;
    property ProGerarNFSe: String read FProGerarNFSe;
    property ProRecepcaoSincrono: String read FProRecepcaoSincrono;
  end;

 TGeralConf = class(TComponent)
  private
    FSalvar: Boolean;
    FPathSalvar: String;
    FPathSchemas: String;
    function GetPathSalvar: String;
  public
    constructor Create(AOwner: TComponent); override;
    function Save(AXMLName: String; AXMLFile: WideString; aPath: String = ''): Boolean;
  published
    property Salvar: Boolean read FSalvar write FSalvar default False;
    property PathSalvar: String read GetPathSalvar write FPathSalvar;
    property PathSchemas: String read FPathSchemas write FPathSchemas;
  end;

 TArquivosConf = class(TComponent)
  private
    FSalvar   : Boolean;
    FMensal   : Boolean;
    FLiteral  : Boolean;
    FEmissaoPathNFSe  : Boolean;
    FPathNFSe : String;
    FPathCan  : String;
    FPathRPS: String;
    FPathGer: String;
  public
    constructor Create(AOwner: TComponent); override;
    function GetPathCan: String;
    function GetPathGer: String;
    function GetPathRPS: String;
    function GetPathNFSe(Data : TDateTime = 0): String;
  published
    property Salvar     : Boolean read FSalvar  write FSalvar  default False;
    property PastaMensal: Boolean read FMensal  write FMensal  default False;
    property AdicionarLiteral: Boolean read FLiteral write FLiteral default False;
    property EmissaoPathNFSe: Boolean read FEmissaoPathNFSe write FEmissaoPathNFSe default False;
    property PathNFSe : String read FPathNFSe  write FPathNFSe;
    property PathCan : String read FPathCan  write FPathCan;
    property PathRPS : String read FPathRPS  write FPathRPS;
    property PathGer : String read FPathGer  write FPathGer;
  end;

 TConfiguracoes = class(TComponent)
  private
    FGeral: TGeralConf;
    FWebServices: TWebServicesConf;
    FCertificados: TCertificadosConf;
    FArquivos: TArquivosConf;
  public
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
  published
    property Geral: TGeralConf read FGeral;
    property WebServices: TWebServicesConf read FWebServices;
    property Certificados: TCertificadosConf read FCertificados;
    property Arquivos: TArquivosConf read FArquivos;
  end;

 TProvedorClass = Class
  private

  public
   Constructor Create;

   function GetConfigCidade(ACodCidade, AAmbiente: Integer): TConfigCidade; Virtual; Abstract;
   function GetConfigSchema(ACodCidade: Integer): TConfigSchema; Virtual; Abstract;
   function GetConfigURL(ACodCidade: Integer): TConfigURL; Virtual; Abstract;
   function GetURI(URI: String): String; Virtual; Abstract;
   function GetAssinarXML(Acao: TnfseAcao): Boolean; Virtual; Abstract;
   // Sugest�o de Rodrigo Catelli
   function GetValidarLote: Boolean; Virtual; Abstract;

   function Gera_TagI(Acao: TnfseAcao; Prefixo3, Prefixo4, NameSpaceDad, Identificador, URI: String): AnsiString; Virtual; Abstract;
   function Gera_CabMsg(Prefixo2, VersaoLayOut, VersaoDados, NameSpaceCab: String; ACodCidade: Integer): AnsiString; Virtual; Abstract;
   function Gera_DadosSenha(CNPJ, Senha: String): AnsiString; Virtual; Abstract;
   function Gera_TagF(Acao: TnfseAcao; Prefixo3: String): AnsiString; Virtual; Abstract;

   function Gera_DadosMsgEnviarLote(Prefixo3, Prefixo4, Identificador,
                                    NameSpaceDad, VersaoDados, VersaoXML,
                                    NumeroLote, CNPJ, IM, QtdeNotas: String;
                                    Notas, TagI, TagF: AnsiString): AnsiString; Virtual; Abstract;
   function Gera_DadosMsgConsSitLote(Prefixo3, Prefixo4, NameSpaceDad,
                                     VersaoXML, Protocolo, CNPJ, IM: String;
                                     TagI, TagF: AnsiString): AnsiString; Virtual; Abstract;
   function Gera_DadosMsgConsLote(Prefixo3, Prefixo4, NameSpaceDad,
                                  VersaoXML, Protocolo, CNPJ, IM: String;
                                  TagI, TagF: AnsiString): AnsiString; Virtual; Abstract;
   function Gera_DadosMsgConsNFSeRPS(Prefixo3, Prefixo4, NameSpaceDad, VersaoXML,
                                     NumeroRps, SerieRps, TipoRps, CNPJ, IM: String;
                                     TagI, TagF: AnsiString): AnsiString; Virtual; Abstract;
   function Gera_DadosMsgConsNFSe(Prefixo3, Prefixo4, NameSpaceDad, VersaoXML,
                                  CNPJ, IM: String;
                                  DataInicial, DataFinal: TDateTime;
                                  TagI, TagF: AnsiString; NumeroNFSe: string = ''): AnsiString; Virtual; Abstract;
   function Gera_DadosMsgCancelarNFSe(Prefixo4, NameSpaceDad, NumeroNFSe, CNPJ, IM,
                                      CodMunicipio, CodCancelamento: String;
                                      TagI, TagF: AnsiString): AnsiString; Virtual; Abstract;
   function Gera_DadosMsgGerarNFSe(Prefixo3, Prefixo4, Identificador,
                                   NameSpaceDad, VersaoDados, VersaoXML,
                                   NumeroLote, CNPJ, IM, QtdeNotas: String;
                                   Notas, TagI, TagF: AnsiString): AnsiString; Virtual; Abstract;
   function Gera_DadosMsgEnviarSincrono(Prefixo3, Prefixo4, Identificador,
                                        NameSpaceDad, VersaoDados, VersaoXML,
                                        NumeroLote, CNPJ, IM, QtdeNotas: String;
                                        Notas, TagI, TagF: AnsiString): AnsiString; Virtual; Abstract;

   function GeraEnvelopeRecepcionarLoteRPS(URLNS: String; CabMsg, DadosMsg, DadosSenha: AnsiString): AnsiString; Virtual; Abstract;
   function GeraEnvelopeConsultarSituacaoLoteRPS(URLNS: String; CabMsg, DadosMsg, DadosSenha: AnsiString): AnsiString; Virtual; Abstract;
   function GeraEnvelopeConsultarLoteRPS(URLNS: String; CabMsg, DadosMsg, DadosSenha: AnsiString): AnsiString; Virtual; Abstract;
   function GeraEnvelopeConsultarNFSeporRPS(URLNS: String; CabMsg, DadosMsg, DadosSenha: AnsiString): AnsiString; Virtual; Abstract;
   function GeraEnvelopeConsultarNFSe(URLNS: String; CabMsg, DadosMsg, DadosSenha: AnsiString): AnsiString; Virtual; Abstract;
   function GeraEnvelopeCancelarNFSe(URLNS: String; CabMsg, DadosMsg, DadosSenha: AnsiString): AnsiString; Virtual; Abstract;
   function GeraEnvelopeGerarNFSe(URLNS: String; CabMsg, DadosMsg, DadosSenha: AnsiString): AnsiString; Virtual; Abstract;
   function GeraEnvelopeRecepcionarSincrono(URLNS: String; CabMsg, DadosMsg, DadosSenha: AnsiString): AnsiString; Virtual; Abstract;

   function GetSoapAction(Acao: TnfseAcao; NomeCidade: String): String; Virtual; Abstract;
   function GetRetornoWS(Acao: TnfseAcao; RetornoWS: AnsiString): AnsiString; Virtual; Abstract;

   function GeraRetornoNFSe(Prefixo: String; RetNFSe: AnsiString; NomeCidade: String): AnsiString; Virtual; Abstract;
   function GetLinkNFSe(ACodMunicipio, ANumeroNFSe: Integer; ACodVerificacao: String; AAmbiente: Integer): String; Virtual; Abstract;

  end;

implementation

uses
 IniFiles, DateUtils, Math, StrUtils, ACBrUtil, ACBrNFSe, ACBrNFSeUtil, ACBrDFeUtil,
 ACBrProvedorGinfesV3, ACBrProvedorPublica, ACBrProvedorRJ,
 ACBrProvedorTiplan, ACBrProvedorISSNet, ACBrProvedorWebISS,
 ACBrProvedorProdemge, ACBrProvedorISSIntel, ACBrProvedorGovBR,
 ACBrProvedorRecife, ACBrProvedorSimplISS, ACBrProvedorThema,
 ACBrProvedorEquiplano, ACBrProvedorfintelISS, ACBrProvedorDigifred,
 ACBrProvedorBetha, ACBrProvedorBetim, ACBrProvedorSaatri,
 ACBrProvedorAbaco, ACBrProvedorGoiania, ACBrProvedorIssCuritiba,
 ACBrProvedorBHISS, ACBrProvedorNatal, ACBrProvedorISSDigital,
 ACBrProvedorISSe, ACBrProvedor4R, ACBrProvedorGovDigital;

{ TConfiguracoes }

constructor TConfiguracoes.Create(AOwner: TComponent);
begin
 inherited Create( AOwner );

 FGeral      := TGeralConf.Create(Self);
 FGeral.Name := 'GeralConf';

{$IFDEF COMPILER6_UP}
  FGeral.SetSubComponent( true ); { para gravar no DFM/XFM }
{$ENDIF}

 FCertificados      := TCertificadosConf.Create(self);
 FCertificados.Name := 'CertificadosConf';

{$IFDEF COMPILER6_UP}
  FCertificados.SetSubComponent( true ); { para gravar no DFM/XFM }
{$ENDIF}

 FWebServices      := TWebServicesConf.Create(self);
 FWebServices.Name := 'WebServicesConf';

{$IFDEF COMPILER6_UP}
  FWebServices.SetSubComponent( true ); { para gravar no DFM/XFM }
{$ENDIF}

 FArquivos      := TArquivosConf.Create(self);
 FArquivos.Name := 'ArquivosConf';

{$IFDEF COMPILER6_UP}
  FArquivos.SetSubComponent( true ); { para gravar no DFM/XFM }
{$ENDIF}
end;

destructor TConfiguracoes.Destroy;
begin
 FGeral.Free;
 FWebServices.Free;
 FCertificados.Free;
 FArquivos.Free;

 inherited;
end;

{ TGeralConf }

constructor TGeralConf.Create(AOwner: TComponent);
begin
 Inherited Create( AOwner );

 FSalvar      := False;
 FPathSalvar  := '';
 FPathSchemas := '';
end;

function TGeralConf.GetPathSalvar: String;
begin
 if DFeUtil.EstaVazio(FPathSalvar)
  then Result := DFeUtil.PathAplication
  else Result := FPathSalvar;

 Result := NotaUtil.PathWithDelim( Trim(Result) );
end;

function TGeralConf.Save(AXMLName: String; AXMLFile: WideString; aPath: String = ''): Boolean;
var
 vSalvar: TStrings;
begin
 Result := False;
 vSalvar := TStringList.Create;
 try
  try
   if DFeUtil.NaoEstaVazio(ExtractFilePath(AXMLName))
    then begin
     aPath := ExtractFilePath(AXMLName);
     AXMLName := StringReplace(AXMLName,aPath,'',[rfIgnoreCase]);
    end
    else begin
     if DFeUtil.EstaVazio(aPath)
      then aPath := PathSalvar
      else aPath := PathWithDelim(aPath);
    end;

   vSalvar.Text := AXMLFile;
   if not DirectoryExists( aPath )
    then ForceDirectories( aPath );

   vSalvar.SaveToFile( aPath + AXMLName);
   Result := True;
  except on E: Exception do
   raise Exception.Create('Erro ao salvar. '+E.Message);
  end;
 finally
  vSalvar.Free;
 end;
end;

{ TWebServicesConf }

constructor TWebServicesConf.Create(AOwner: TComponent);
begin
 Inherited Create( AOwner );

 FSalvar     := False;
 FVisualizar := False;
 FAmbiente   := taHomologacao;
 if FAmbiente=taProducao
  then FAmbienteCodigo := 1
  else FAmbienteCodigo := 2;

 FPrefixo2      := 'ns2:';
 FPrefixo3      := 'ns3:';
 FPrefixo4      := 'ns4:';
 FProvedor      := proNenhum;
 FxProvedor     := '';
 FVersaoSoap    := '';
 FIdentificador := 'Id';
 FNameSpace     := '';
 FConsultaLoteAposEnvio := True;
end;

procedure TWebServicesConf.SetAmbiente(AValue: TpcnTipoAmbiente);
begin
 FAmbiente := AValue;
 if AValue=taProducao
  then FAmbienteCodigo := 1
  else FAmbienteCodigo := 2;
end;

{ TCertificadosConf }

{$IFNDEF ACBrNFSeOpenSSL}
function TCertificadosConf.GetCertificado: ICertificate2;
var
 Store          : IStore3;
 Certs          : ICertificates2;
 Cert           : ICertificate2;
 i              : Integer;
 xmldoc         : IXMLDOMDocument3;
 xmldsig        : IXMLDigitalSignature;
 dsigKey        : IXMLDSigKey;
 SigKey         : IXMLDSigKeyEx;
 PrivateKey     : IPrivateKey;
 hCryptProvider : HCRYPTPROV;
 XML            : String;
begin
 if DFeUtil.EstaVazio( FNumeroSerie )
  then raise Exception.Create('N�mero de S�rie do Certificado Digital n�o especificado !');

 Result := nil;
 Store := CoStore.Create;
 Store.Open(CAPICOM_CURRENT_USER_STORE, CAPICOM_STORE_NAME, CAPICOM_STORE_OPEN_MAXIMUM_ALLOWED);

 Certs := Store.Certificates as ICertificates2;
 for i:= 1 to Certs.Count do
  begin
   Cert := IInterface(Certs.Item[i]) as ICertificate2;
   if Cert.SerialNumber = FNumeroSerie
    then begin
     if DFeUtil.EstaVazio(NumCertCarregado)
      then NumCertCarregado := Cert.SerialNumber;

     if CertStoreMem = nil
      then begin
       CertStoreMem := CoStore.Create;
       CertStoreMem.Open(CAPICOM_MEMORY_STORE, 'Memoria', CAPICOM_STORE_OPEN_MAXIMUM_ALLOWED);
       CertStoreMem.Add(Cert);
      end;

     PrivateKey := Cert.PrivateKey;

     if (FSenhaCert <> '') and PrivateKey.IsHardwareDevice
      then begin
       PrivateKey := Cert.PrivateKey;

       XML := XML + '<Signature xmlns="http://www.w3.org/2000/09/xmldsig#">'+
                      '<SignedInfo>'+
                       '<CanonicalizationMethod Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315"/>'+
                       '<SignatureMethod Algorithm="http://www.w3.org/2000/09/xmldsig#rsa-sha1" />'+
                       '<Reference URI="#">'+
                        '<Transforms>'+
                         '<Transform Algorithm="http://www.w3.org/2000/09/xmldsig#enveloped-signature" />'+
                         '<Transform Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315" />'+
                        '</Transforms>'+
                        '<DigestMethod Algorithm="http://www.w3.org/2000/09/xmldsig#sha1" />'+
                        '<DigestValue>'+
                        '</DigestValue>'+
                       '</Reference>'+
                      '</SignedInfo>'+
                      '<SignatureValue>'+
                      '</SignatureValue>'+
                      '<KeyInfo>'+
                      '</KeyInfo>'+
                     '</Signature>';

       xmldoc := CoDOMDocument50.Create;
       xmldoc.async              := False;
       xmldoc.validateOnParse    := False;
       xmldoc.preserveWhiteSpace := True;
       xmldoc.loadXML(XML);
       xmldoc.setProperty('SelectionNamespaces', DSIGNS);

       xmldsig := CoMXDigitalSignature50.Create;
       xmldsig.signature := xmldoc.selectSingleNode('.//ds:Signature');
       xmldsig.store := CertStoreMem;

       dsigKey := xmldsig.createKeyFromCSP(PrivateKey.ProviderType, PrivateKey.ProviderName, PrivateKey.ContainerName, 0);
       if (dsigKey = nil)
        then raise Exception.Create('Erro ao criar a chave do CSP.');

       SigKey := dsigKey as IXMLDSigKeyEx;
       SigKey.getCSPHandle( hCryptProvider );

       try
        CryptSetProvParam( hCryptProvider , PP_SIGNATURE_PIN, LPBYTE(FSenhaCert), 0 );
       finally
        CryptReleaseContext(hCryptProvider, 0);
       end;

       SigKey  := nil;
       dsigKey := nil;
       xmldsig := nil;
       xmldoc  := nil;
      end;

     Result      := Cert;
     FDataVenc   := Cert.ValidToDate;
     FInformacao := Cert.SubjectName;
     break;
    end;
  end;

 if not(Assigned(Result))
  then raise Exception.Create('Certificado Digital n�o encontrado!');
end;

function TCertificadosConf.GetNumeroSerie: AnsiString;
begin
 Result := Trim(UpperCase(StringReplace(FNumeroSerie,' ','',[rfReplaceAll] )));
end;

procedure TCertificadosConf.SetNumeroSerie(const Value: AnsiString);
begin
 FNumeroSerie := Trim(UpperCase(StringReplace(Value,' ','',[rfReplaceAll] )));
end;

function TCertificadosConf.SelecionarCertificado: AnsiString;
var
 Store  : IStore3;
 Certs  : ICertificates2;
 Certs2 : ICertificates2;
 Cert   : ICertificate2;
begin
 Store := CoStore.Create;
 Store.Open(CAPICOM_CURRENT_USER_STORE, CAPICOM_STORE_NAME, CAPICOM_STORE_OPEN_MAXIMUM_ALLOWED);

 Certs  := Store.Certificates as ICertificates2;
 Certs2 := Certs.Select('Certificado(s) Digital(is) dispon�vel(is)', 'Selecione o Certificado Digital para uso no aplicativo', false);

 if not(Certs2.Count = 0)
  then begin
   Cert         := IInterface(Certs2.Item[1]) as ICertificate2;
   FNumeroSerie := Cert.SerialNumber;
   FDataVenc    := Cert.ValidToDate;
   FInformacao  := Cert.SubjectName
  end;

 Result := FNumeroSerie;
end;

function TCertificadosConf.GetDataVenc: TDateTime;
begin
 if DFeUtil.NaoEstaVazio(FNumeroSerie)
  then begin
   if FDataVenc = 0
    then GetCertificado;
   Result := FDataVenc;
  end
 else Result := 0;
end;

function TCertificadosConf.GetInformacao: AnsiString;
begin
 if DFeUtil.NaoEstaVazio(FNumeroSerie)
  then begin
   if FInformacao = ''
    then GetCertificado;
    Result := UpperCase(FInformacao);
  end
 else Result := '';
end;
{$ENDIF}

Procedure TWebServicesConf.SetConfigMunicipio(aPath: String = '');
var
 Ok:           Boolean;
 ConfigCidade: TConfigCidade;
 ConfigSchema: TConfigSchema;
 ConfigURL:    TConfigURL;
begin
 FxProvedor := CodCidadeToProvedor(FCodigoMunicipio);
 FProvedor  := StrToProvedor(Ok, FxProvedor);

 if Provedor = proNenhum
  then raise Exception.Create('C�digo do Municipio ['+ IntToStr(FCodigoMunicipio) +'] n�o Encontrado.');

 FProvedorClass.Free;

 case FProvedor of
  proGINFES:      FProvedorClass := TProvedorGinfesV3.Create;
  proPublica:     FProvedorClass := TProvedorPublica.Create;
  proRJ:          FProvedorClass := TProvedorRJ.Create;
  proTiplan:      FProvedorClass := TProvedorTiplan.Create;
  proISSNet:      FProvedorClass := TProvedorISSNet.Create;
  proWebISS:      FProvedorClass := TProvedorWebISS.Create;
  proProdemge:    FProvedorClass := TProvedorProdemge.Create;
  proISSIntel:    FProvedorClass := TProvedorISSIntel.Create;
  proGovBR:       FProvedorClass := TProvedorGovBR.Create;
  proRecife:      FProvedorClass := TProvedorRecife.Create;
  proSimplISS:    FProvedorClass := TProvedorSimplISS.Create;
  proThema:       FProvedorClass := TProvedorThema.Create;
  proEquiplano:   FProvedorClass := TProvedorEquiplano.Create;
  profintelISS:   FProvedorClass := TProvedorfintelISS.Create;
  proDigifred:    FProvedorClass := TProvedorDigifred.Create;
  proBetha:       FProvedorClass := TProvedorBetha.Create;
  proBetim:       FProvedorClass := TProvedorBetim.Create;
  proSaatri:      FProvedorClass := TProvedorSaatri.Create;
  proAbaco:       FProvedorClass := TProvedorAbaco.Create;
  proGoiania:     FProvedorClass := TProvedorGoiania.Create;
  proIssCuritiba: FProvedorClass := TProvedorIssCuritiba.Create;
  proBHISS:       FProvedorClass := TProvedorBHISS.Create;
  proNatal:       FProvedorClass := TProvedorNatal.Create;
  proISSDigital:  FProvedorClass := TProvedorISSDigital.Create;
  proISSe:        FProvedorClass := TProvedorISSe.Create;
  pro4R:          FProvedorClass := TProvedor4R.Create;
  proGovDigital:  FProvedorClass := TProvedorGovDigital.Create;
 end;

 ConfigCidade   := FProvedorClass.GetConfigCidade(FCodigoMunicipio, FAmbienteCodigo);

 FVersaoSoap    := ConfigCidade.VersaoSoap;
 FPrefixo2      := ConfigCidade.Prefixo2;
 FPrefixo3      := ConfigCidade.Prefixo3;
 FPrefixo4      := ConfigCidade.Prefixo4;
 FIdentificador := ConfigCidade.Identificador;
 FNameSpace     := ConfigCidade.NameSpaceEnvelope;

 TConfiguracoes( Self.Owner ).Certificados.FAssinaRPS  := ConfigCidade.AssinaRPS;
 TConfiguracoes( Self.Owner ).Certificados.FAssinaLote := ConfigCidade.AssinaLote;

 ConfigSchema := FProvedorClass.GetConfigSchema(FCodigoMunicipio);

 FVersaoCabecalho       := ConfigSchema.VersaoCabecalho;
 FVersaoDados           := ConfigSchema.VersaoDados;
 FVersaoXML             := ConfigSchema.VersaoXML;
 FURL                   := ConfigSchema.NameSpaceXML;
 FCabecalho             := ConfigSchema.Cabecalho;
 FServicoEnviar         := ConfigSchema.ServicoEnviar;
 FServicoConSit         := ConfigSchema.ServicoConSit;
 FServicoConLot         := ConfigSchema.ServicoConLot;
 FServicoConRps         := ConfigSchema.ServicoConRps;
 FServicoConNfse        := ConfigSchema.ServicoConNfse;
 FServicoCancelar       := ConfigSchema.ServicoCancelar;
 FServicoGerar          := ConfigSchema.ServicoGerar;
 FServicoEnviarSincrono := ConfigSchema.ServicoEnviarSincrono;
 FDefTipos              := ConfigSchema.DefTipos;

 ConfigURL := FProvedorClass.GetConfigURL(FCodigoMunicipio);

 FHomNomeCidade         := ConfigURL.HomNomeCidade;
 FHomRecepcaoLoteRPS    := ConfigURL.HomRecepcaoLoteRPS;
 FHomConsultaLoteRPS    := ConfigURL.HomConsultaLoteRPS;
 FHomConsultaNFSeRPS    := ConfigURL.HomConsultaNFSeRPS;
 FHomConsultaSitLoteRPS := ConfigURL.HomConsultaSitLoteRPS;
 FHomConsultaNFSe       := ConfigURL.HomConsultaNFSe;
 FHomCancelaNFSe        := ConfigURL.HomCancelaNFSe;
 FHomGerarNFSe          := ConfigURL.HomGerarNFSe;
 FHomRecepcaoSincrono   := ConfigURL.HomRecepcaoSincrono;

 FProNomeCidade         := ConfigURL.ProNomeCidade;
 FProRecepcaoLoteRPS    := ConfigURL.ProRecepcaoLoteRPS;
 FProConsultaLoteRPS    := ConfigURL.ProConsultaLoteRPS;
 FProConsultaNFSeRPS    := ConfigURL.ProConsultaNFSeRPS;
 FProConsultaSitLoteRPS := ConfigURL.ProConsultaSitLoteRPS;
 FProConsultaNFSe       := ConfigURL.ProConsultaNFSe;
 FProCancelaNFSe        := ConfigURL.ProCancelaNFSe;
 FProGerarNFSe          := ConfigURL.ProGerarNFSe;
 FProRecepcaoSincrono   := ConfigURL.ProRecepcaoSincrono;
end;

procedure TWebServicesConf.SetIntervaloTentativas(const Value: Cardinal);
begin
 if (Value > 0) and (Value < 1000)
  then FIntervaloTentativas := 1000
  else FIntervaloTentativas := Value;
end;

procedure TWebServicesConf.SetTentativas(const Value: Integer);
begin
 if Value <= 0
  then FTentativas := 18
  else FTentativas := Value;
end;

{ TArquivosConf }

constructor TArquivosConf.Create(AOwner: TComponent);
begin
 inherited;

end;

function TArquivosConf.GetPathCan: String;
var
 wDia, wMes, wAno : Word;
 Dir : String;
begin
 if DFeUtil.EstaVazio(FPathCan)
  then Dir := TConfiguracoes( Self.Owner ).Geral.PathSalvar
  else Dir := FPathCan;

 if FMensal
  then begin
   DecodeDate(Now, wAno, wMes, wDia);
   if Pos(IntToStr(wAno)+IntToStrZero(wMes,2),Dir) <= 0
    then Dir := PathWithDelim(Dir)+IntToStr(wAno)+IntToStrZero(wMes,2);
  end;

 if FLiteral
  then begin
   if copy(Dir,length(Dir)-2,3) <> 'Can'
    then Dir := PathWithDelim(Dir)+'Can';
  end;

 if not DirectoryExists(Dir)
  then ForceDirectories(Dir);

 Result := Dir;
end;

function TArquivosConf.GetPathNFSe(Data: TDateTime): String;
var
 wDia, wMes, wAno : Word;
 Dir : String;
begin
 if DFeUtil.EstaVazio(FPathNFSe)
  then Dir := TConfiguracoes( Self.Owner ).Geral.PathSalvar
  else Dir := FPathNFSe;

 if FMensal
  then begin
   if Data = 0
    then Data := Now;
   DecodeDate(Data, wAno, wMes, wDia);
   if Pos(IntToStr(wAno)+IntToStrZero(wMes,2),Dir) <= 0
    then Dir := PathWithDelim(Dir)+IntToStr(wAno)+IntToStrZero(wMes,2);
  end;

 if FLiteral
  then begin
   if copy(Dir,length(Dir)-2,3) <> 'NFSe'
    then Dir := PathWithDelim(Dir)+'NFSe';
  end;

 if not DirectoryExists(Dir)
  then ForceDirectories(Dir);

 Result := Dir;
end;

function TArquivosConf.GetPathRPS: String;
var
 wDia, wMes, wAno : Word;
 Dir : String;
begin
 if DFeUtil.EstaVazio(FPathRPS)
  then Dir := TConfiguracoes( Self.Owner ).Geral.PathSalvar
  else Dir := FPathRPS;

 if FMensal
  then begin
   DecodeDate(Now, wAno, wMes, wDia);
   if Pos(IntToStr(wAno)+IntToStrZero(wMes,2),Dir) <= 0
    then Dir := PathWithDelim(Dir)+IntToStr(wAno)+IntToStrZero(wMes,2);
  end;

 if FLiteral
  then begin
   if copy(Dir,length(Dir)-2,3) <> 'RPS'
    then Dir := PathWithDelim(Dir)+'RPS';
  end;

 if not DirectoryExists(Dir)
  then ForceDirectories(Dir);

 Result := Dir;
end;

function TArquivosConf.GetPathGer: String;
var
 wDia, wMes, wAno : Word;
 Dir : String;
begin
 if DFeUtil.EstaVazio(FPathGer)
  then Dir := TConfiguracoes( Self.Owner ).Geral.PathSalvar
  else Dir := FPathGer;

 if FMensal
  then begin
   DecodeDate(Now, wAno, wMes, wDia);
   if Pos(IntToStr(wAno)+IntToStrZero(wMes,2),Dir) <= 0
    then Dir := PathWithDelim(Dir)+IntToStr(wAno)+IntToStrZero(wMes,2);
  end;

 if FLiteral
  then begin
   if copy(Dir,length(Dir)-2,3) <> 'Ger'
    then Dir := PathWithDelim(Dir)+'Ger';
  end;

 if not DirectoryExists(Dir)
  then ForceDirectories(Dir);

 Result := Dir;
end;

{ TProvedorClass }

constructor TProvedorClass.Create;
begin
 {----}
end;

end.

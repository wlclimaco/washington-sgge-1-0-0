{******************************************************************************}
{ Projeto: Componente ACBrCTe                                                  }
{  Biblioteca multiplataforma de componentes Delphi para emiss�o de Nota Fiscal}
{ eletr�nica - CTe - http://www.CTe.fazenda.gov.br                             }
{                                                                              }
{ Direitos Autorais Reservados (c) 2008 Wemerson Souto                         }
{                                       Daniel Simoes de Almeida               }
{                                       Andr� Ferreira de Moraes               }
{                                                                              }
{ Desenvolvimento                                                              }
{         de Cte: Wiliam Zacarias da Silva Rosa                                }
{                                                                              }
{  Voc� pode obter a �ltima vers�o desse arquivo na pagina do Projeto ACBr     }
{ Componentes localizado em http://www.sourceforge.net/projects/acbr           }
{                                                                              }
{                                                                              }
{  Esta biblioteca � software livre; voc� pode redistribu�-la e/ou modific�-la }
{ sob os termos da Licen�a P�blica Geral Menor do GNU conforme publicada pela  }
{ Free Software Foundation; tanto a vers�o 2.1 da Licen�a, ou (a seu crit�rio) }
{ qualquer vers�o posterior.                                                   }
{                                                                              }
{  Esta biblioteca � distribu�da na expectativa de que seja �til, por�m, SEM   }
{ NENHUMA GARANTIA; nem mesmo a garantia impl�cita de COMERCIABILIDADE OU      }
{ ADEQUA��O A UMA FINALIDADE ESPEC�FICA. Consulte a Licen�a P�blica Geral Menor}
{ do GNU para mais detalhes. (Arquivo LICEN�A.TXT ou LICENSE.TXT)              }
{                                                                              }
{  Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral Menor do GNU junto}
{ com esta biblioteca; se n�o, escreva para a Free Software Foundation, Inc.,  }
{ no endere�o 59 Temple Street, Suite 330, Boston, MA 02111-1307 USA.          }
{ Voc� tamb�m pode obter uma copia da licen�a em:                              }
{ http://www.opensource.org/licenses/lgpl-license.php                          }
{                                                                              }
{ Daniel Sim�es de Almeida  -  daniel@djsystem.com.br  -  www.djsystem.com.br  }
{              Pra�a Anita Costa, 34 - Tatu� - SP - 18270-410                  }
{                                                                              }
{******************************************************************************}

{******************************************************************************
|* Historico
|*
|* 16/12/2008: Wemerson Souto
|*  - Doa��o do componente para o Projeto ACBr
******************************************************************************}
{$I ACBr.inc}

unit ACBrCTeConfiguracoes;

interface

uses
 {$IFNDEF ACBrCTeOpenSSL}
  ACBrCAPICOM_TLB, JwaWinCrypt, JwaWinType, ACBrMSXML2_TLB,
 {$ENDIF}
  Classes, Sysutils, pcnConversao, ActiveX;

{$IFNDEF ACBrCTeOpenSSL}
  const CAPICOM_STORE_NAME = 'My'; //My CA Root AddressBook
{$ENDIF}

type

  TCertificadosConf = class(TComponent)
  private
    FSenhaCert: AnsiString;
    {$IFDEF ACBrCTeOpenSSL}
       FCertificado: AnsiString;
    {$ELSE}
       FNumeroSerie: AnsiString;
       FDataVenc: TDateTime;
       procedure SetNumeroSerie(const Value: AnsiString);
       function GetNumeroSerie: AnsiString;
       function GetDataVenc: TDateTime;
    {$ENDIF}
  public
    {$IFNDEF ACBrCTeOpenSSL}
       function SelecionarCertificado:AnsiString;
       function GetCertificado: ICertificate2;
    {$ENDIF}
  published
    {$IFDEF ACBrCTeOpenSSL}
       property Certificado: AnsiString read FCertificado write FCertificado;
    {$ELSE}
       property NumeroSerie: AnsiString read GetNumeroSerie write SetNumeroSerie;
       property DataVenc: TDateTime read GetDataVenc;
    {$ENDIF}
       property Senha: AnsiString read FSenhaCert write FSenhaCert;
  end;

  TWebServicesConf = Class(TComponent)
  private
    FVisualizar : Boolean;
    FUF: String;
    FUFCodigo: Integer;
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
    procedure SetUF(AValue: String);
    procedure SetAmbiente(AValue: TpcnTipoAmbiente);
    procedure SetTentativas(const Value: Integer);
    procedure SetIntervaloTentativas(const Value: Cardinal);
  public
    constructor Create(AOwner: TComponent); override ;
  published
    property Visualizar: Boolean read FVisualizar write FVisualizar
      default False ;
    property UF: String read FUF write SetUF;
    property UFCodigo: Integer read FUFCodigo;
    property Ambiente: TpcnTipoAmbiente read FAmbiente write SetAmbiente
      default taHomologacao ;
    property AmbienteCodigo: Integer read FAmbienteCodigo;
    property ProxyHost: String read FProxyHost write FProxyHost;
    property ProxyPort: String read FProxyPort write FProxyPort;
    property ProxyUser: String read FProxyUser write FProxyUser;
    property ProxyPass: String read FProxyPass write FProxyPass;
    property AguardarConsultaRet : Cardinal read FAguardarConsultaRet write FAguardarConsultaRet;
    property Tentativas : Integer read FTentativas write SetTentativas default 5;
    property IntervaloTentativas : Cardinal read FIntervaloTentativas write SetIntervaloTentativas;
    property AjustaAguardaConsultaRet : Boolean read FAjustaAguardaConsultaRet write FAjustaAguardaConsultaRet;
  end;

  TGeralConf = class(TComponent)
  private
    FFormaEmissao: TpcnTipoEmissao;
    FFormaEmissaoCodigo: Integer;
    FSalvar: Boolean;
    FAtualizarXMLCancelado: Boolean;
    FPathSalvar: String;
    FPathSchemas: String;
  {$IFDEF ACBrCTeOpenSSL}
    FIniFinXMLSECAutomatico: boolean;
  {$ENDIF}
    procedure SetFormaEmissao(AValue: TpcnTipoEmissao);
    function GetPathSalvar: String;
  public
    constructor Create(AOwner: TComponent); override ;
    function Save(AXMLName: String; AXMLFile: WideString; aPath: String = ''): Boolean;
  published
    property FormaEmissao: TpcnTipoEmissao read FFormaEmissao
      write SetFormaEmissao default teNormal ;
    property FormaEmissaoCodigo: Integer read FFormaEmissaoCodigo;
    property Salvar: Boolean read FSalvar write FSalvar default False;
    property AtualizarXMLCancelado: Boolean read FAtualizarXMLCancelado write FAtualizarXMLCancelado default True ;
    property PathSalvar: String read GetPathSalvar write FPathSalvar;
    property PathSchemas: String read FPathSchemas write FPathSchemas;
  {$IFDEF ACBrCTeOpenSSL}
    property IniFinXMLSECAutomatico: Boolean read FIniFinXMLSECAutomatico write FIniFinXMLSECAutomatico;
  {$ENDIF}
  end;

  TArquivosConf = class(TComponent)
  private
    FSalvar   : Boolean;
    FMensal   : Boolean;
    FLiteral  : Boolean;
    FEmissaoPathCTe  : Boolean;
    FPathCTe  : String;
    FPathCan  : String;
    FPathInu  : String;
    FPathDPEC : String;
    FPathEvento : String;
  public
    constructor Create(AOwner: TComponent); override ;
    function GetPathCan: String;
    function GetPathDPEC: String;
    function GetPathInu: String;
    function GetPathCTe(Data : TDateTime = 0): String;
    function GetPathEvento: String;
  published
    property Salvar     : Boolean read FSalvar  write FSalvar  default False ;
    property PastaMensal: Boolean read FMensal  write FMensal  default False ;
    property AdicionarLiteral: Boolean read FLiteral write FLiteral default False ;
    property EmissaoPathCTe: Boolean read FEmissaoPathCte write FEmissaoPathCTe default False ;
    property PathCTe : String read FPathCTe  write FPathCTe;
    property PathCan : String read FPathCan  write FPathCan;
    property PathInu : String read FPathInu  write FPathInu;
    property PathDPEC: String read FPathDPEC write FPathDPEC;
    property PathEvento : String read FPathEvento write FPathEvento;
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
    property Geral: TGeralConf read FGeral ;
    property WebServices: TWebServicesConf read FWebServices ;
    property Certificados: TCertificadosConf read FCertificados ;
    property Arquivos: TArquivosConf read FArquivos ;
  end;

implementation

uses ACBrCteUtil,  Math, StrUtils, ACBrUtil, ACBrDFeUtil, DateUtils;

{ TConfiguracoes }

constructor TConfiguracoes.Create(AOwner: TComponent);
begin
  inherited Create( AOwner ) ;

  FGeral      := TGeralConf.Create(Self);
  FGeral.Name := 'GeralConf' ;
  {$IFDEF COMPILER6_UP}
   FGeral.SetSubComponent( true );{ para gravar no DFM/XFM }
  {$ENDIF}

  FWebServices      := TWebServicesConf.Create(self);
  FWebServices.Name := 'WebServicesConf' ;
  {$IFDEF COMPILER6_UP}
   FWebServices.SetSubComponent( true );{ para gravar no DFM/XFM }
  {$ENDIF}

  FCertificados      := TCertificadosConf.Create(self);
  FCertificados.Name := 'CertificadosConf' ;
  {$IFDEF COMPILER6_UP}
   FCertificados.SetSubComponent( true );{ para gravar no DFM/XFM }
  {$ENDIF}

  FArquivos      := TArquivosConf.Create(self);
  FArquivos.Name := 'ArquivosConf' ;
  {$IFDEF COMPILER6_UP}
   FArquivos.SetSubComponent( true );{ para gravar no DFM/XFM }
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

  FFormaEmissao          := teNormal;
  FFormaEmissaoCodigo    := StrToInt(TpEmisToStr(FFormaEmissao));
  FSalvar                := False;
  FAtualizarXMLCancelado := True;
  FPathSalvar            := '' ;
  FPathSchemas           := '' ;
{$IFDEF ACBrCTeOpenSSL}
  FIniFinXMLSECAutomatico:=True;
{$ENDIF}
end;

function TGeralConf.GetPathSalvar: String;
begin
  if DFeUtil.EstaVazio(FPathSalvar) then
    Result := DFeUtil.PathAplication
  else
    Result := FPathSalvar;

  Result := PathWithDelim( Trim(Result) ) ;
end;

function TGeralConf.Save(AXMLName: String; AXMLFile: WideString; aPath: String = ''): Boolean;
var
  vSalvar: TStrings;
begin
  Result  := False;
  vSalvar := TStringList.Create;
  try
    try
      if DFeUtil.NaoEstaVazio(ExtractFilePath(AXMLName)) then
       begin
         aPath    := ExtractFilePath(AXMLName);
         AXMLName := StringReplace(AXMLName,aPath,'',[rfIgnoreCase]);
       end
      else
       begin
         if DFeUtil.EstaVazio(aPath) then
            aPath := PathSalvar
         else
            aPath := PathWithDelim(aPath);
       end;

      vSalvar.Text := AXMLFile;
      if not DirectoryExists( aPath ) then
         ForceDirectories( aPath );

      vSalvar.SaveToFile( aPath + AXMLName);
      Result := True;
    except on E: Exception do
      raise Exception.Create('Erro ao salvar .'+E.Message);
    end;
  finally
    vSalvar.Free;
  end;
end;

procedure TGeralConf.SetFormaEmissao(AValue: TpcnTipoEmissao);
begin
  FFormaEmissao       := AValue;
  FFormaEmissaoCodigo := StrToInt(TpEmisToStr(FFormaEmissao));
end;

{ TWebServicesConf }

constructor TWebServicesConf.Create(AOwner: TComponent);
begin
  Inherited Create( AOwner );

  FUF             := NFeUF[24];
  FUFCodigo       := NFeUFCodigo[24];
  FAmbiente       := taHomologacao;
  FVisualizar     := False ;
  FAmbienteCodigo := StrToInt(TpAmbToStr(FAmbiente));
end;

procedure TWebServicesConf.SetAmbiente(AValue: TpcnTipoAmbiente);
begin
  FAmbiente       := AValue;
  FAmbienteCodigo := StrToInt(TpAmbToStr(AValue));
end;

procedure TWebServicesConf.SetIntervaloTentativas(const Value: Cardinal);
begin
  if (Value > 0) and (Value < 1000) then
     FIntervaloTentativas := 1000
  else
     FIntervaloTentativas := Value;
end;

procedure TWebServicesConf.SetTentativas(const Value: Integer);
begin
  if Value <= 0 then
     FTentativas := 5
  else
     FTentativas := Value;
end;

procedure TWebServicesConf.SetUF(AValue: String);
var
  Codigo, i: Integer;
begin
  Codigo := -1 ;
  for i:= 0 to High(NFeUF) do
  begin
    if NFeUF[I] = AValue then
      Codigo := NFeUFCodigo[I];
  end;

  if Codigo < 0 then
     raise Exception.Create('UF inv�lida');

  FUF       := AValue;
  FUFCodigo := Codigo;
end;

{ TCertificadosConf }

{$IFNDEF ACBrCTeOpenSSL}
function TCertificadosConf.GetCertificado: ICertificate2;
var
  Store : IStore3;
  Certs : ICertificates2;
  Cert  : ICertificate2;
  i     : Integer;

  xmldoc  : IXMLDOMDocument3;
  xmldsig : IXMLDigitalSignature;
  dsigKey : IXMLDSigKey;
  SigKey  : IXMLDSigKeyEx;

  PrivateKey     : IPrivateKey;
  hCryptProvider : HCRYPTPROV;

  XML : String;
begin
  CoInitialize(nil); // PERMITE O USO DE THREAD
  if DFeUtil.EstaVazio( FNumeroSerie ) then
    raise Exception.Create('N�mero de S�rie do Certificado Digital n�o especificado !');

  Result := nil;
  Store  := CoStore.Create;
  Store.Open(CAPICOM_CURRENT_USER_STORE, CAPICOM_STORE_NAME, CAPICOM_STORE_OPEN_MAXIMUM_ALLOWED);

  Certs := Store.Certificates as ICertificates2;
  for i:= 1 to Certs.Count do
  begin
    Cert := IInterface(Certs.Item[i]) as ICertificate2;
    if Cert.SerialNumber = FNumeroSerie then
    begin
      if DFeUtil.EstaVazio(NumCertCarregado) then
         NumCertCarregado := Cert.SerialNumber;

      PrivateKey := Cert.PrivateKey;

      if  CertStoreMem = nil then
       begin
         CertStoreMem := CoStore.Create;
         CertStoreMem.Open(CAPICOM_MEMORY_STORE, 'Memoria', CAPICOM_STORE_OPEN_MAXIMUM_ALLOWED);
         CertStoreMem.Add(Cert);

         if (FSenhaCert <> '') and PrivateKey.IsHardwareDevice then
          begin
            XML := XML + '<Signature xmlns="http://www.w3.org/2000/09/xmldsig#"><SignedInfo><CanonicalizationMethod Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315"/><SignatureMethod Algorithm="http://www.w3.org/2000/09/xmldsig#rsa-sha1" />';
            XML := XML + '<Reference URI="#">';
            XML := XML + '<Transforms><Transform Algorithm="http://www.w3.org/2000/09/xmldsig#enveloped-signature" /><Transform Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315" /></Transforms><DigestMethod Algorithm="http://www.w3.org/2000/09/xmldsig#sha1" />';
            XML := XML + '<DigestValue></DigestValue></Reference></SignedInfo><SignatureValue></SignatureValue><KeyInfo></KeyInfo></Signature>';

            xmldoc                    := CoDOMDocument50.Create;
            xmldoc.async              := False;
            xmldoc.validateOnParse    := False;
            xmldoc.preserveWhiteSpace := True;
            xmldoc.loadXML(XML);
            xmldoc.setProperty('SelectionNamespaces', DSIGNS);

            xmldsig           := CoMXDigitalSignature50.Create;
            xmldsig.signature := xmldoc.selectSingleNode('.//ds:Signature');
            xmldsig.store     := CertStoreMem;

            dsigKey := xmldsig.createKeyFromCSP(PrivateKey.ProviderType, PrivateKey.ProviderName, PrivateKey.ContainerName, 0);
            if (dsigKey = nil) then
               raise Exception.Create('Erro ao criar a chave do CSP.');

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
       end;

      Result    := Cert;
      FDataVenc := Cert.ValidToDate;
      break;
    end;
  end;

  if not(Assigned(Result)) then
    raise Exception.Create('Certificado Digital n�o encontrado!');
   CoUninitialize;
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
  CoInitialize(nil); // PERMITE O USO DE THREAD
  Store := CoStore.Create;
  Store.Open(CAPICOM_CURRENT_USER_STORE, CAPICOM_STORE_NAME, CAPICOM_STORE_OPEN_MAXIMUM_ALLOWED);

  Certs  := Store.Certificates as ICertificates2;
  Certs2 := Certs.Select('Certificado(s) Digital(is) dispon�vel(is)', 'Selecione o Certificado Digital para uso no aplicativo', false);

  if not(Certs2.Count = 0) then
  begin
    Cert         := IInterface(Certs2.Item[1]) as ICertificate2;
    FNumeroSerie := Cert.SerialNumber;
    FDataVenc    := Cert.ValidToDate;
  end;

  Result := FNumeroSerie;
  CoUninitialize;
end;

function TCertificadosConf.GetDataVenc: TDateTime;
begin
 if DFeUtil.NaoEstaVazio(FNumeroSerie) then
  begin
    if FDataVenc = 0 then
       GetCertificado;
    Result := FDataVenc;
  end
 else
    Result := 0;
end;
{$ENDIF}

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
  if DFeUtil.EstaVazio(FPathCan) then
     Dir := TConfiguracoes( Self.Owner ).Geral.PathSalvar
  else
     Dir := FPathCan;

  if FMensal then
   begin
     DecodeDate(Now, wAno, wMes, wDia);
     if Pos(IntToStr(wAno)+IntToStrZero(wMes,2),Dir) <= 0 then
        Dir := PathWithDelim(Dir)+IntToStr(wAno)+IntToStrZero(wMes,2);
   end;

  if FLiteral then
   begin
     if copy(Dir,length(Dir)-2,3) <> 'Can' then
        Dir := PathWithDelim(Dir)+'Can';
   end;

  if not DirectoryExists(Dir) then
     ForceDirectories(Dir);

  Result  := Dir;
end;

function TArquivosConf.GetPathDPEC: String;
var
  wDia, wMes, wAno : Word;
  Dir : String;
begin
  if DFeUtil.EstaVazio(FPathDPEC) then
     Dir := TConfiguracoes( Self.Owner ).Geral.PathSalvar
  else
     Dir := FPathDPEC;

  if FMensal then
   begin
     DecodeDate(Now, wAno, wMes, wDia);
     if Pos(IntToStr(wAno)+IntToStrZero(wMes,2),Dir) <= 0 then
        Dir := PathWithDelim(Dir)+IntToStr(wAno)+IntToStrZero(wMes,2);
   end;

  if FLiteral then
   begin
     if copy(Dir,length(Dir)-3,4) <> 'DPEC' then
        Dir := PathWithDelim(Dir)+'DPEC';
   end;

  if not DirectoryExists(Dir) then
     ForceDirectories(Dir);

  Result := Dir;
end;

function TArquivosConf.GetPathInu: String;
var
  wDia, wMes, wAno : Word;
  Dir : String;
begin
  if DFeUtil.EstaVazio(FPathInu) then
     Dir := TConfiguracoes( Self.Owner ).Geral.PathSalvar
  else
     Dir := FPathInu;

  if FMensal then
   begin
     DecodeDate(Now, wAno, wMes, wDia);
     if Pos(IntToStr(wAno)+IntToStrZero(wMes,2),Dir) <= 0 then
        Dir := PathWithDelim(Dir)+IntToStr(wAno)+IntToStrZero(wMes,2);
   end;

  if FLiteral then
   begin
     if copy(Dir,length(Dir)-2,3) <> 'Inu' then
        Dir := PathWithDelim(Dir)+'Inu';
   end;

  if not DirectoryExists(Dir) then
     ForceDirectories(Dir);

  Result := Dir;
end;

function TArquivosConf.GetPathCTe(Data : TDateTime = 0): String;
var
  wDia, wMes, wAno : Word;
  Dir : String;
begin
  if DFeUtil.EstaVazio(FPathCTe) then
     Dir := TConfiguracoes( Self.Owner ).Geral.PathSalvar
  else
     Dir := FPathCTe;

  if FMensal then
   begin
     if Data = 0 then
        Data := Now;
     DecodeDate(Data, wAno, wMes, wDia);
     if Pos(IntToStr(wAno)+IntToStrZero(wMes,2),Dir) <= 0 then
        Dir := PathWithDelim(Dir)+IntToStr(wAno)+IntToStrZero(wMes,2);
   end;

  if FLiteral then
   begin
     if copy(Dir,length(Dir)-2,3) <> 'CTe' then
        Dir := PathWithDelim(Dir)+'CTe';
   end;

  if not DirectoryExists(Dir) then
     ForceDirectories(Dir);

  Result := Dir;
end;

function TArquivosConf.GetPathEvento: String;
var
  wDia, wMes, wAno : Word;
  Dir : String;
begin
  if DFeUtil.EstaVazio(FPathEvento) then
     Dir := TConfiguracoes( Self.Owner ).Geral.PathSalvar
  else
     Dir := FPathEvento;

  if FMensal then
   begin
     DecodeDate(Now, wAno, wMes, wDia);
     if Pos(IntToStr(wAno) + IntToStrZero(wMes, 2), Dir) <= 0 then
        Dir := PathWithDelim(Dir) + IntToStr(wAno) + IntToStrZero(wMes, 2);
   end;

  if FLiteral then
   begin
     if copy(Dir, length(Dir) - 2, 3) <> 'Evento' then
        Dir := PathWithDelim(Dir) + 'Evento';
   end;

  if not DirectoryExists(Dir) then
     ForceDirectories(Dir);

  Result  := Dir;
end;

end.

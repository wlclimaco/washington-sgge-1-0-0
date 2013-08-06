{******************************************************************************}
{ Projeto: Componente ACBrCTe                                                  }
{  Biblioteca multiplataforma de componentes Delphi para emiss�o de Nota Fiscal}
{ eletr�nica - CTe - http://www.CTe.fazenda.gov.br                             }
{                                                                              }
{ Direitos Autorais Reservados (c) 2008 Wiliam Zacarias da Silva Rosa          }
{                                       Wemerson Souto                         }
{                                       Daniel Simoes de Almeida               }
{                                       Andr� Ferreira de Moraes               }
{                                                                              }
{ Colaboradores nesse arquivo:                                                 }
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
{ Wiliam Zacarias da Silva Rosa  -  wrosa2009@yahoo.com.br -  www.motta.com.br }
{                                                                              }
{******************************************************************************}

{*******************************************************************************
|* Historico
|*
|* 16/12/2008: Wemerson Souto
|*  - Doa��o do componente para o Projeto ACBr
|* 10/08/2009 : Wiliam Zacarias da Silva Rosa
|*  - Criadas classes e procedimentos para acesso aos webservices
|* 08/03/2010 : Bruno - Rhythmus Informatica
|* Corrigida fun��o DoCTeRecepcao
*******************************************************************************}
{$I ACBr.inc}

unit ACBrCTeWebServices;

interface

uses Classes, SysUtils,
  {$IFDEF VCL} Dialogs, {$ELSE} QDialogs, {$ENDIF}
  {$IFDEF ACBrCTeOpenSSL}
    HTTPSend,
  {$ELSE}
     SOAPHTTPTrans, WinInet, ACBrCAPICOM_TLB,
     {SoapHTTPClient, }SOAPConst,{ JwaWinCrypt,}
  {$ENDIF}
  pcnAuxiliar, pcnConversao, pcteRetConsCad,
  ACBrCTeConfiguracoes, ACBrCteConhecimentos,
  pcteRetConsReciCTe, pcteProcCte, pcteRetCancCTe, pcteConsReciCTe,
  pcteEnvEventoCTe, pcteRetEnvEventoCTe,
  pcteRetEnvCTe, ActiveX;

type

  TWebServicesBase = Class
  private
  	procedure DoCTeStatusServico;
    procedure DoCTeConsultaCadastro;
    procedure DoCTeConsulta;
    procedure DoCTeCancelamento;
    procedure DoCTeInutilizacao;
    procedure DoCTeRecepcao;
    procedure DoCTeRetRecepcao;
    procedure DoCTeRecibo;
    procedure DoCTeEnvEvento;

    {$IFDEF ACBrCTeOpenSSL}
       procedure ConfiguraHTTP( HTTP : THTTPSend; Action : AnsiString);
    {$ELSE}
       procedure ConfiguraReqResp( ReqResp : THTTPReqResp);
       procedure OnBeforePost(const HTTPReqResp: THTTPReqResp; Data:Pointer);
    {$ENDIF}
  protected
    FCabMsg: AnsiString;
    FDadosMsg: AnsiString;
    FRetornoWS: AnsiString;
    FRetWS: AnsiString;
    FMsg: AnsiString;
    FURL: WideString;
    FConfiguracoes: TConfiguracoes;
    FACBrCTe : TComponent;
    procedure LoadMsgEntrada;
    procedure LoadURL;
  public
    constructor Create(AOwner : TComponent); virtual;
    function Executar: Boolean;virtual;

    property CabMsg: AnsiString read FCabMsg;
    property DadosMsg: AnsiString read FDadosMsg;
    property RetornoWS: AnsiString read FRetornoWS;
    property RetWS: AnsiString read FRetWS;
    property Msg: AnsiString read FMsg;
  end;

  TCTeStatusServico = Class(TWebServicesBase)
  private
    FtpAmb : TpcnTipoAmbiente;
    FverAplic : String;
    FcStat : Integer;
    FxMotivo : String;
    FcUF : Integer;
    FdhRecbto : TDateTime;
    FTMed : Integer;
    FdhRetorno : TDateTime;
    FxObs :  String;
  public
    function Executar: Boolean; override;

    property tpAmb : TpcnTipoAmbiente read FtpAmb;
    property verAplic : String read FverAplic;
    property cStat : Integer read FcStat;
    property xMotivo : String read FxMotivo;
    property cUF : Integer read FcUF;
    property dhRecbto : TDateTime read FdhRecbto;
    property TMed : Integer read FTMed;
    property dhRetorno : TDateTime read FdhRetorno;
    property xObs :  String read FxObs;
  end;

  TCTeRecepcao = Class(TWebServicesBase)
  private
    FLote: Integer;
    FRecibo : String;
    FCTes : TConhecimentos;
    FTpAmb: TpcnTipoAmbiente;
    FverAplic: String;
    FcStat: Integer;
    FcUF : Integer;
    FxMotivo: String;
    FdhRecbto: TDateTime;
    FTMed: Integer;
    function GetLote: Integer;
  public
    constructor Create(AOwner : TComponent; ACTes : TConhecimentos);reintroduce;
    function Executar: Boolean; override;

    property Recibo: String read FRecibo;
    property TpAmb: TpcnTipoAmbiente read FTpAmb;
    property verAplic: String read FverAplic;
    property cStat: Integer read FcStat;
    property cUF: Integer read FcUF;
    property xMotivo: String read FxMotivo;
    property dhRecbto: TDateTime read FdhRecbto;
    property TMed: Integer read FTMed;
    property Lote: Integer read GetLote write FLote;
  end;

  TCteRetRecepcao = Class(TWebServicesBase)
  private
    FRecibo: String;
    FProtocolo: String;
    FChaveCte: String;
    FCTes: TConhecimentos;
    FCteRetorno: TRetConsReciCTe;
    FTpAmb: TpcnTipoAmbiente;
    FverAplic: String;
    FcStat: Integer;
    FcUF: Integer;
    FxMotivo: String;
    FcMsg: Integer;
    FxMsg: String;
    function Confirma(AInfProt: TProtCteCollection): Boolean;
  public
    constructor Create(AOwner : TComponent; AConhecimentos : TConhecimentos);reintroduce;
    destructor destroy; override;
    // Incluido por Italo em 04/12/2012 (Sujest�o de Nilton Olher)
    procedure Clear;
    function Executar: Boolean; override;

    property TpAmb: TpcnTipoAmbiente read FTpAmb;
    property verAplic: String read FverAplic;
    property cStat: Integer read FcStat;
    property cUF: Integer read FcUF;
    property xMotivo: String read FxMotivo;
    property cMsg: Integer read FcMsg;
    property xMsg: String read FxMsg;
    property Recibo: String read FRecibo write FRecibo;
    property Protocolo: String read FProtocolo write FProtocolo;
    property ChaveCte: String read FChaveCte write FChaveCte;
    property CteRetorno: TRetConsReciCte read FCteRetorno write FCteRetorno;
  end;

  TCteRecibo = Class(TWebServicesBase)
  private
    FRecibo: String;
    FCTeRetorno: TRetConsReciCTe;
    FTpAmb: TpcnTipoAmbiente;
    FverAplic: String;
    FcStat: Integer;
    FxMotivo: String;
    FcUF: Integer;
    FxMsg: String;
    FcMsg: Integer;
  public
    constructor Create(AOwner : TComponent);reintroduce;
    destructor destroy; override;
    // Incluido por Italo em 03/01/2013 (Sujest�o de Nilton Olher)
    procedure Clear;
    function Executar: Boolean; override;

    property TpAmb: TpcnTipoAmbiente read FTpAmb;
    property verAplic: String read FverAplic;
    property cStat: Integer read FcStat;
    property xMotivo: String read FxMotivo;
    property cUF: Integer read FcUF;
    property xMsg: String read FxMsg;
    property cMsg: Integer read FcMsg;
    property Recibo: String read FRecibo write FRecibo;
    property CTeRetorno: TRetConsReciCTe read FCTeRetorno write FCTeRetorno;
  end;

  TCTeConsulta = Class(TWebServicesBase)
  private
    FCTeChave: WideString;
    FProtocolo: WideString;
    FDhRecbto: TDateTime;
    FXMotivo: WideString;
    FTpAmb : TpcnTipoAmbiente;
    FverAplic : String;
    FcStat : Integer;
    FcUF : Integer;
    FdigVal : String;
    FprotCTe: TProcCTe;
    FretCancCTe: TRetCancCTe;
  public
    constructor Create(AOwner : TComponent); reintroduce;
    destructor Destroy; override;
    // Incluido por Italo em 03/01/2013 (Sujest�o de Nilton Olher)
    procedure Clear;
    function Executar: Boolean;override;

    property CTeChave: WideString read FCTeChave write FCTeChave;
    property Protocolo: WideString read FProtocolo write FProtocolo;
    property DhRecbto: TDateTime read FDhRecbto write FDhRecbto;
    property XMotivo: WideString read FXMotivo write FXMotivo;
    property TpAmb: TpcnTipoAmbiente read FTpAmb;
    property verAplic: String read FverAplic;
    property cStat: Integer read FcStat;
    property cUF: Integer read FcUF;
    property digVal: String read FdigVal;
    property protCTe: TProcCTe read FprotCTe write FprotCTe;
    property retCancCTe: TRetCancCTe read FretCancCTe write FretCancCTe;
  end;

  TCTeCancelamento = Class(TWebServicesBase)
  private
    FCTeChave: WideString;
    FProtocolo: WideString;
    FJustificativa: WideString;
    FTpAmb: TpcnTipoAmbiente;
    FverAplic: String;
    FcStat: Integer;
    FxMotivo: String;
    FcUF: Integer;
    FDhRecbto: TDateTime;
    FXML_ProcCancCTe: AnsiString;
    procedure SetJustificativa(AValue: WideString);
  public
    // Incluido por Italo em 03/01/2013 (Sujest�o de Nilton Olher)
    procedure Clear;
    function Executar: Boolean;override;

    property TpAmb: TpcnTipoAmbiente read FTpAmb;
    property verAplic: String read FverAplic;
    property cStat: Integer read FcStat;
    property xMotivo: String read FxMotivo;
    property cUF: Integer read FcUF;
    property DhRecbto: TDateTime read FDhRecbto;
    property CTeChave: WideString read FCTeChave write FCTeChave;
    property Protocolo: WideString read FProtocolo write FProtocolo;
    property Justificativa: WideString read FJustificativa write SetJustificativa;
    property XML_ProcCancCTe: AnsiString read FXML_ProcCancCTe write FXML_ProcCancCTe;
  end;

  TCTeInutilizacao = Class(TWebServicesBase)
  private
    FCTeChave: WideString;
    FProtocolo: string;
    FModelo: Integer;
    FSerie: Integer;
    FCNPJ: String;
    FAno: Integer;
    FNumeroInicial: Integer;
    FNumeroFinal: Integer;
    FJustificativa: WideString;
    FTpAmb: TpcnTipoAmbiente;
    FverAplic: String;
    FcStat: Integer;
    FxMotivo : String;
    FcUF: Integer;
    FdhRecbto: TDateTime;
    FXML_ProcInutCTe: AnsiString;
    procedure SetJustificativa(AValue: WideString);
  public
    // Incluido por Italo em 03/01/2013 (Sujest�o de Nilton Olher)
    procedure Clear;
    function Executar: Boolean;override;

    property CTeChave: WideString read FCTeChave write FCTeChave;
    property Protocolo: String read FProtocolo write FProtocolo;
    property Modelo: Integer read FModelo write FModelo;
    property Serie: Integer read FSerie write FSerie;
    property CNPJ: String read FCNPJ write FCNPJ;
    property Ano: Integer read FAno write FAno;
    property NumeroInicial: Integer read FNumeroInicial write FNumeroInicial;
    property NumeroFinal: Integer read FNumeroFinal write FNumeroFinal;
    property Justificativa: WideString read FJustificativa write SetJustificativa;
    property TpAmb: TpcnTipoAmbiente read FTpAmb;
    property verAplic: String read FverAplic;
    property cStat: Integer read FcStat;
    property xMotivo : String read FxMotivo;
    property cUF: Integer read FcUF;
    property dhRecbto: TDateTime read FdhRecbto;
    property XML_ProcInutCTe: AnsiString read FXML_ProcInutCTe write FXML_ProcInutCTe;
  end;

  TCTeConsultaCadastro = Class(TWebServicesBase)
  private
    FverAplic: String;
    FcStat: Integer;
    FxMotivo: String;
    FUF: String;
    FIE: String;
    FCNPJ: String;
    FCPF: String;
    FcUF: Integer;
    FdhCons: TDateTime;
    FRetConsCad : TRetConsCad;
    procedure SetCNPJ(const Value: String);
    procedure SetCPF(const Value: String);
    procedure SetIE(const Value: String);
  public
    destructor Destroy; override;
    // Incluido por Italo em 03/01/2013 (Sujest�o de Nilton Olher)
    procedure Clear;
    function Executar: Boolean;override;

    property verAplic: String read FverAplic;
    property cStat: Integer read FcStat;
    property xMotivo: String read FxMotivo;
    property DhCons: TDateTime read FdhCons;
    property cUF: Integer read FcUF;
    property RetConsCad: TRetConsCad read FRetConsCad;

    property UF:   String read FUF write FUF;
    property IE:   String read FIE write SetIE;
    property CNPJ: String read FCNPJ write SetCNPJ;
    property CPF:  String read FCPF write SetCPF;
  end;

  TCTeEnvEvento = Class(TWebServicesBase)
  private
    FidLote: Integer;
    Fversao: String;
    FEvento: TEventoCTe;
    FcStat: Integer;
    FxMotivo: String;
    FTpAmb: TpcnTipoAmbiente;
    FEventoRetorno: TRetEventoCTe;
  public
    constructor Create(AOwner : TComponent; AEvento : TEventoCTe); reintroduce;
    destructor Destroy; override;
    // Incluido por Italo em 03/01/2013 (Sujest�o de Nilton Olher)
    procedure Clear;
    function Executar: Boolean; override;

    property idLote: Integer               read FidLote      write FidLote;
    property versao: String                read Fversao      write Fversao;
    property cStat: Integer                read FcStat;
    property xMotivo: String               read FxMotivo;
    property TpAmb: TpcnTipoAmbiente       read FTpAmb;
    property EventoRetorno: TRetEventoCTe  read FEventoRetorno;
  end;

  TWebServices = Class(TWebServicesBase)
  private
    FACBrCTe : TComponent;
    FStatusServico: TCTeStatusServico;
    FEnviar: TCTeRecepcao;
    FRetorno: TCTeRetRecepcao;
    FRecibo: TCTeRecibo;
    FConsulta: TCTeConsulta;
    FCancelamento: TCTeCancelamento;
    FInutilizacao: TCTeInutilizacao;
    FConsultaCadastro: TCTeConsultaCadastro;
    FEnvEvento: TCTeEnvEvento;
  public
    constructor Create(AFCTe: TComponent);reintroduce;
    destructor Destroy; override;
    function Envia(ALote: Integer): Boolean;
    procedure Cancela(AJustificativa: String);
    procedure Inutiliza(CNPJ, AJustificativa: String; Ano, Modelo, Serie, NumeroInicial, NumeroFinal : Integer);
//  published
    property ACBrCTe: TComponent read FACBrCTe write FACBrCTe;
    property StatusServico: TCTeStatusServico read FStatusServico write FStatusServico;
    property Enviar: TCTeRecepcao read FEnviar write FEnviar;
    property Retorno: TCTeRetRecepcao read FRetorno write FRetorno;
    property Recibo: TCTeRecibo read FRecibo write FRecibo;
    property Consulta: TCTeConsulta read FConsulta write FConsulta;
    property Cancelamento: TCTeCancelamento read FCancelamento write FCancelamento;
    property Inutilizacao: TCTeInutilizacao read FInutilizacao write FInutilizacao;
    property ConsultaCadastro: TCTeConsultaCadastro read FConsultaCadastro write FConsultaCadastro;
    property EnvEvento: TCTeEnvEvento read FEnvEvento write FEnvEvento;
  end;

implementation

uses {$IFDEF ACBrCTeOpenSSL}
        ssl_openssl,
     {$ENDIF}
     ACBrUtil, ACBrCTeUtil, ACBrCTe, ACBrDFeUtil,
     pcnGerador, pcnCabecalho, pcnLeitor,
     pcteConsStatServ, pcteRetConsStatServ,
     pcteConsCad,
     pcteConsSitCTe, pcteRetConsSitCTe,
     pcteCancCTe,
     pcteInutCTe, pcteRetInutCTe;

{$IFNDEF ACBrCTeOpenSSL}
const
  INTERNET_OPTION_CLIENT_CERT_CONTEXT = 84;
{$ENDIF}

{ TWebServicesBase }
constructor TWebServicesBase.Create(AOwner: TComponent);
begin
  FConfiguracoes := TConfiguracoes( TACBrCTe( AOwner ).Configuracoes );
  FACBrCTe       := TACBrCTe( AOwner );
end;

{$IFDEF ACBrCTeOpenSSL}
procedure TWebServicesBase.ConfiguraHTTP( HTTP : THTTPSend; Action : AnsiString);
begin
  if FileExists(FConfiguracoes.Certificados.Certificado) then
    HTTP.Sock.SSL.PFXfile := FConfiguracoes.Certificados.Certificado
  else
    HTTP.Sock.SSL.PFX     := FConfiguracoes.Certificados.Certificado;

  HTTP.Sock.SSL.KeyPassword := FConfiguracoes.Certificados.Senha;

  HTTP.ProxyHost := FConfiguracoes.WebServices.ProxyHost;
  HTTP.ProxyPort := FConfiguracoes.WebServices.ProxyPort;
  HTTP.ProxyUser := FConfiguracoes.WebServices.ProxyUser;
  HTTP.ProxyPass := FConfiguracoes.WebServices.ProxyPass;

  // Linha abaixo comentada por Italo em 08/09/2010
//  HTTP.Sock.RaiseExcept := True;

  HTTP.MimeType  := 'text/xml; charset=utf-8';
  HTTP.UserAgent := '';
  HTTP.Protocol  := '1.1';

  HTTP.AddPortNumberToHost := False;
  HTTP.Headers.Add(Action);
end;

{$ELSE}
procedure TWebServicesBase.ConfiguraReqResp( ReqResp : THTTPReqResp);
begin
  if FConfiguracoes.WebServices.ProxyHost <> '' then
   begin
     ReqResp.Proxy    := FConfiguracoes.WebServices.ProxyHost+':'+FConfiguracoes.WebServices.ProxyPort;
     ReqResp.UserName := FConfiguracoes.WebServices.ProxyUser;
     ReqResp.Password := FConfiguracoes.WebServices.ProxyPass;
   end;
  ReqResp.OnBeforePost := OnBeforePost;
end;

procedure TWebServicesBase.OnBeforePost(const HTTPReqResp: THTTPReqResp;
  Data: Pointer);
var
  Cert         : ICertificate2;
  CertContext  : ICertContext;
  PCertContext : Pointer;
  ContentHeader: string;
begin
  Cert        := FConfiguracoes.Certificados.GetCertificado;
  CertContext :=  Cert as ICertContext;
  CertContext.Get_CertContext(Integer(PCertContext));

  if not InternetSetOption(Data, INTERNET_OPTION_CLIENT_CERT_CONTEXT, PCertContext,SizeOf(CertContext)*5) then
   begin
     if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
        TACBrCTe( FACBrCTe ).OnGerarLog('ERRO: Erro OnBeforePost: ' + IntToStr(GetLastError));
     raise Exception.Create( 'Erro OnBeforePost: ' + IntToStr(GetLastError) );
   end;

   if trim(FConfiguracoes.WebServices.ProxyUser) <> '' then begin
     if not InternetSetOption(Data, INTERNET_OPTION_PROXY_USERNAME, PChar(FConfiguracoes.WebServices.ProxyUser), Length(FConfiguracoes.WebServices.ProxyUser)) then
       raise Exception.Create( 'Erro OnBeforePost: ' + IntToStr(GetLastError) );
   end;
   if trim(FConfiguracoes.WebServices.ProxyPass) <> '' then begin
     if not InternetSetOption(Data, INTERNET_OPTION_PROXY_PASSWORD, PChar(FConfiguracoes.WebServices.ProxyPass),Length (FConfiguracoes.WebServices.ProxyPass)) then
       raise Exception.Create( 'Erro OnBeforePost: ' + IntToStr(GetLastError) );
   end;

  ContentHeader := Format(ContentTypeTemplate, ['application/soap+xml; charset=utf-8']);
  HttpAddRequestHeaders(Data, PChar(ContentHeader), Length(ContentHeader), HTTP_ADDREQ_FLAG_REPLACE);
end;
{$ENDIF}

procedure TWebServicesBase.DoCTeCancelamento;
var
  CancCTe: TcancCTe;
begin
  CancCTe        := TcancCTe.Create;
  CancCTe.chCTe  := TCTeCancelamento(Self).CTeChave;
  CancCTe.tpAmb  := TpcnTipoAmbiente(FConfiguracoes.WebServices.AmbienteCodigo-1);
  CancCTe.nProt  := TCTeCancelamento(Self).Protocolo;
  CancCTe.xJust  := TCTeCancelamento(Self).Justificativa;
  
  CancCTe.GerarXML;

{$IFDEF ACBrCTeOpenSSL}
  if not(CTeUtil.Assinar(CancCTe.Gerador.ArquivoFormatoXML, TConfiguracoes(FConfiguracoes).Certificados.Certificado , TConfiguracoes(FConfiguracoes).Certificados.Senha, FDadosMsg, FMsg)) then
    begin
      if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
         TACBrCTe( FACBrCTe ).OnGerarLog('ERRO: Falha ao assinar Cancelamento Conhecimento Eletr�nico '+LineBreak+FMsg);
      raise Exception.Create('Falha ao assinar Cancelamento Conhecimento Eletr�nico '+LineBreak+FMsg);
    end;
{$ELSE}
  if not(CTeUtil.Assinar(CancCTe.Gerador.ArquivoFormatoXML, TConfiguracoes(FConfiguracoes).Certificados.GetCertificado , FDadosMsg, FMsg)) then
    begin
      if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
         TACBrCTe( FACBrCTe ).OnGerarLog('Falha ao assinar Cancelamento Conhecimento Eletr�nico '+LineBreak+FMsg);
      raise Exception.Create('Falha ao assinar Cancelamento Conhecimento Eletr�nico '+LineBreak+FMsg);
    end;
{$ENDIF}

  if not(CTeUtil.Valida(FDadosMsg, FMsg, TACBrCTe( FACBrCTe ).Configuracoes.Geral.PathSchemas)) then
     begin
       if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
          TACBrCTe( FACBrCTe ).OnGerarLog('Falha na valida��o dos dados do cancelamento '+LineBreak+FMsg);
       raise Exception.Create('Falha na valida��o dos dados do cancelamento '+LineBreak+FMsg);
     end;

  CancCTe.Free;

//  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8_STD+'>', '', [rfReplaceAll] );
//  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8+'>', '', [rfReplaceAll] );
{$IFDEF ACBrCTeOpenSSL}
  FDadosMsg := StringReplace( FDadosMsg, '<?xml version="1.0"?>', '', [rfReplaceAll] );
{$ENDIF}
end;

procedure TWebServicesBase.DoCTeConsulta;
var
  ConsSitCTe : TConsSitCTe;
begin
  ConsSitCTe        := TConsSitCTe.Create;
  ConsSitCTe.TpAmb  := TpcnTipoAmbiente(FConfiguracoes.WebServices.AmbienteCodigo-1);
  ConsSitCTe.chCTe  := TCTeConsulta(Self).CTeChave;

  ConsSitCTe.GerarXML;

  FDadosMsg := ConsSitCTe.Gerador.ArquivoFormatoXML;
  ConsSitCTe.Free;

//  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8_STD+'>', '', [rfReplaceAll] );
//  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8+'>', '', [rfReplaceAll] );
//  FDadosMsg := StringReplace( FDadosMsg, '<?xml version="1.0"?>', '', [rfReplaceAll] );
end;

procedure TWebServicesBase.DoCTeInutilizacao;
var
  InutCTe: TinutCTe;
begin
  InutCTe        := TinutCTe.Create;
  InutCTe.tpAmb  := TpcnTipoAmbiente(FConfiguracoes.WebServices.AmbienteCodigo-1);
  InutCTe.cUF    := FConfiguracoes.WebServices.UFCodigo;
  InutCTe.ano    := TCTeInutilizacao(Self).Ano;
  InutCTe.CNPJ   := TCTeInutilizacao(Self).CNPJ;
  InutCTe.modelo := TCTeInutilizacao(Self).Modelo;
  InutCTe.serie  := TCTeInutilizacao(Self).Serie;
  InutCTe.nCTIni := TCTeInutilizacao(Self).NumeroInicial;
  InutCTe.nCTFin := TCTeInutilizacao(Self).NumeroFinal;
  InutCTe.xJust  := TCTeInutilizacao(Self).Justificativa;

  InutCTe.GerarXML;

{$IFDEF ACBrCTeOpenSSL}
  if not(CTeUtil.Assinar(InutCTe.Gerador.ArquivoFormatoXML, TConfiguracoes(FConfiguracoes).Certificados.Certificado , TConfiguracoes(FConfiguracoes).Certificados.Senha, FDadosMsg, FMsg)) then
     begin
       if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
          TACBrCTe( FACBrCTe ).OnGerarLog('Falha ao assinar Inutiliza��o Conhecimento Eletr�nico '+LineBreak+FMsg);
       raise Exception.Create('Falha ao assinar Inutiliza��o Conhecimento Eletr�nico '+LineBreak+FMsg);
     end;
{$ELSE}
  if not(CTeUtil.Assinar(InutCTe.Gerador.ArquivoFormatoXML, TConfiguracoes(FConfiguracoes).Certificados.GetCertificado , FDadosMsg, FMsg)) then
     begin
       if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
          TACBrCTe( FACBrCTe ).OnGerarLog('Falha ao assinar Inutiliza��o Conhecimento Eletr�nico '+LineBreak+FMsg);
       raise Exception.Create('Falha ao assinar Inutiliza��o Conhecimento Eletr�nico '+LineBreak+FMsg);
     end;
{$ENDIF}

  TCTeInutilizacao(Self).CTeChave := InutCTe.ID;

  InutCTe.Free;

//  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8_STD+'>', '', [rfReplaceAll] );
//  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8+'>', '', [rfReplaceAll] );
//  FDadosMsg := StringReplace( FDadosMsg, '<?xml version="1.0"?>', '', [rfReplaceAll] );
end;

procedure TWebServicesBase.DoCTeConsultaCadastro;
var
  Cabecalho: TCabecalho;
  ConCadCTe: TConsCad;
begin
  Cabecalho             := TCabecalho.Create;
  Cabecalho.Versao      := CTecabMsg;
  Cabecalho.VersaoDados := CTeconsCad;

  Cabecalho.GerarXML;

  FCabMsg := Cabecalho.Gerador.ArquivoFormatoXML;
  Cabecalho.Free;

  FCabMsg := StringReplace( FCabMsg, '<'+ENCODING_UTF8_STD+'>', '', [rfReplaceAll] );
  FCabMsg := StringReplace( FCabMsg, '<'+ENCODING_UTF8+'>', '', [rfReplaceAll] );
  FCabMsg := StringReplace( FCabMsg, '<?xml version="1.0"?>', '', [rfReplaceAll] );

  ConCadCTe        := TConsCad.Create;
  ConCadCTe.UF     := TCTeConsultaCadastro(Self).UF;
  ConCadCTe.IE     := TCTeConsultaCadastro(Self).IE;
  ConCadCTe.CNPJ   := TCTeConsultaCadastro(Self).CNPJ;
  ConCadCTe.CPF    := TCTeConsultaCadastro(Self).CPF;

  ConCadCTe.GerarXML;

  FDadosMsg := ConCadCTe.Gerador.ArquivoFormatoXML;

  ConCadCTe.Free;

  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8_STD+'>', '', [rfReplaceAll] );
  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8+'>', '', [rfReplaceAll] );
  FDadosMsg := StringReplace( FDadosMsg, '<?xml version="1.0"?>', '', [rfReplaceAll] );
end;

procedure TWebServicesBase.DoCTeRecepcao;
var
  i    : Integer;
  vCtes: WideString;
begin
  vCtes := '';
  for i := 0 to TCTeRecepcao(Self).FCTes.Count-1 do
    vCtes := vCtes + TCTeRecepcao(Self).FCTes.Items[I].XML;

  vCtes := StringReplace( vCtes, '<?xml version="1.0" encoding="UTF-8" ?>', '', [rfReplaceAll] );
  vCtes := StringReplace( vCtes, '<?xml version="1.0" encoding="UTF-8"?>' , '', [rfReplaceAll] );

  FDadosMsg := '<enviCTe xmlns="http://www.portalfiscal.inf.br/cte" versao="'+CTeenviCTe+'">'+
               '<idLote>'+IntToStr(TCTeRecepcao(Self).Lote)+'</idLote>'+vCtes+'</enviCTe>';

  if Length(FDadosMsg) > (500 * 1024) then
   begin
      if Assigned(TACBrCTe(Self.FACBrCTe).OnGerarLog) then
         TACBrCTe(Self.FACBrCTe).OnGerarLog('ERRO: Tamanho do XML de Dados superior a 500 Kbytes. Tamanho atual: '+FloatToStr(Int(Length(FDadosMsg)/500))+' Kbytes');
      raise Exception.Create('ERRO: Tamanho do XML de Dados superior a 500 Kbytes. Tamanho atual: '+FloatToStr(Int(Length(FDadosMsg)/500))+' Kbytes');
      exit;
   end;
end;

procedure TWebServicesBase.DoCTeRetRecepcao;
var
  ConsReciCTe: TConsReciCTe;
begin
  ConsReciCTe        := TConsReciCTe.Create;
  ConsReciCTe.tpAmb  := TpcnTipoAmbiente(FConfiguracoes.WebServices.AmbienteCodigo-1);
  ConsReciCTe.nRec   := TCTeRetRecepcao(Self).Recibo;

  ConsReciCTe.GerarXML;

  FDadosMsg := ConsReciCTe.Gerador.ArquivoFormatoXML;
  ConsReciCTe.Free;

//  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8_STD+'>', '', [rfReplaceAll] );
//  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8+'>', '', [rfReplaceAll] );
//  FDadosMsg := StringReplace( FDadosMsg, '<?xml version="1.0"?>', '', [rfReplaceAll] );
end;

procedure TWebServicesBase.DoCTeRecibo;
var
  Cabecalho: TCabecalho;
  ConsReciCTe: TConsReciCTe;
begin
  Cabecalho             := TCabecalho.Create;
  Cabecalho.Versao      := CTecabMsg;
  Cabecalho.VersaoDados := CTeconsReciCTe;

  Cabecalho.GerarXML;

  FCabMsg := Cabecalho.Gerador.ArquivoFormatoXML;
  Cabecalho.Free;

  ConsReciCTe        := TConsReciCTe.Create;
  ConsReciCTe.tpAmb  := TpcnTipoAmbiente(FConfiguracoes.WebServices.AmbienteCodigo-1);
  ConsReciCTe.nRec   := TCTeRecibo(Self).Recibo;

  ConsReciCTe.GerarXML;

  FDadosMsg := ConsReciCTe.Gerador.ArquivoFormatoXML;
  ConsReciCTe.Free;
end;

procedure TWebServicesBase.DoCTeStatusServico;
var
  ConsStatServ: TConsStatServ;
begin
  ConsStatServ       := TConsStatServ.create;
  ConsStatServ.TpAmb := TpcnTipoAmbiente(FConfiguracoes.WebServices.AmbienteCodigo-1);
  ConsStatServ.CUF   := FConfiguracoes.WebServices.UFCodigo;

  ConsStatServ.GerarXML;

  FDadosMsg := ConsStatServ.Gerador.ArquivoFormatoXML;
  ConsStatServ.Free;

//  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8_STD+'>', '', [rfReplaceAll] );
//  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8+'>', '', [rfReplaceAll] );
//  FDadosMsg := StringReplace( FDadosMsg, '<?xml version="1.0"?>', '', [rfReplaceAll] );
end;

function TWebServicesBase.Executar: Boolean;
begin
  Result := False;
  LoadMsgEntrada;
  LoadURL;
end;

procedure TWebServicesBase.LoadMsgEntrada;
begin
  if self is TCTeStatusServico then
    DoCTeStatusServico
  else if self is TCTeRecepcao then
    DoCTeRecepcao
  else if self is TCTeRetRecepcao then
    DoCTeRetRecepcao
  else if self is TCTeRecibo then
    DoCTeRecibo
  else if self is TCTeConsulta then
    DoCTeConsulta
  else if self is TCTeCancelamento then
    DoCTeCancelamento
  else if self is TCTeInutilizacao then
    DoCTeInutilizacao
  else if self is TCTeConsultaCadastro then
    DoCTeConsultaCadastro
  else if Self is TCTeEnvEvento then
    DoCTeEnvEvento
end;

procedure TWebServicesBase.LoadURL;
begin
  if self is TCTeStatusServico then
    FURL := CTeUtil.GetURL(FConfiguracoes.WebServices.UFCodigo, FConfiguracoes.WebServices.AmbienteCodigo, FConfiguracoes.Geral.FormaEmissaoCodigo, LayCTeStatusServico)
  else if self is TCTeRecepcao then
    FURL := CTeUtil.GetURL(FConfiguracoes.WebServices.UFCodigo, FConfiguracoes.WebServices.AmbienteCodigo, FConfiguracoes.Geral.FormaEmissaoCodigo, LayCTeRecepcao)
  else if (self is TCTeRetRecepcao) or (self is TCTeRecibo) then
    FURL := CTeUtil.GetURL(FConfiguracoes.WebServices.UFCodigo, FConfiguracoes.WebServices.AmbienteCodigo, FConfiguracoes.Geral.FormaEmissaoCodigo, LayCTeRetRecepcao)
  else if self is TCTeConsulta then
    FURL := CTeUtil.GetURL(FConfiguracoes.WebServices.UFCodigo, FConfiguracoes.WebServices.AmbienteCodigo, FConfiguracoes.Geral.FormaEmissaoCodigo, LayCTeConsultaCT)
  else if self is TCTeCancelamento then
    FURL := CTeUtil.GetURL(FConfiguracoes.WebServices.UFCodigo, FConfiguracoes.WebServices.AmbienteCodigo, FConfiguracoes.Geral.FormaEmissaoCodigo, LayCTeCancelamento)
  else if self is TCTeInutilizacao then
    FURL := CTeUtil.GetURL(FConfiguracoes.WebServices.UFCodigo, FConfiguracoes.WebServices.AmbienteCodigo, FConfiguracoes.Geral.FormaEmissaoCodigo, LayCTeInutilizacao)
  else if self is TCTeConsultaCadastro then
    FURL := CTeUtil.GetURL(FConfiguracoes.WebServices.UFCodigo, FConfiguracoes.WebServices.AmbienteCodigo, FConfiguracoes.Geral.FormaEmissaoCodigo, LayCTeCadastro)
  else if self is TCTeEnvEvento then
    FURL := CTeUtil.GetURL(FConfiguracoes.WebServices.UFCodigo, FConfiguracoes.WebServices.AmbienteCodigo, FConfiguracoes.Geral.FormaEmissaoCodigo, LayCTeEvento)
end;

procedure TWebServicesBase.DoCTeEnvEvento;
var
  EventoCTe : TEventoCTe;
  i : integer;
begin
  EventoCTe        := TEventoCTe.Create;
  EventoCTe.idLote := TCTeEnvEvento(Self).idLote;

  for i := 0 to TCTeEnvEvento(Self).FEvento.Evento.Count-1 do
   begin
     with EventoCTe.Evento.Add do
      begin
        infEvento.tpAmb      := TpcnTipoAmbiente(FConfiguracoes.WebServices.AmbienteCodigo-1);
        infEvento.CNPJ       := TCTeEnvEvento(Self).FEvento.Evento[i].InfEvento.CNPJ;
        infEvento.chCTe      := TCTeEnvEvento(Self).FEvento.Evento[i].InfEvento.chCTe;
        infEvento.dhEvento   := TCTeEnvEvento(Self).FEvento.Evento[i].InfEvento.dhEvento;
        infEvento.tpEvento   := TCTeEnvEvento(Self).FEvento.Evento[i].InfEvento.tpEvento;
        infEvento.nSeqEvento := TCTeEnvEvento(Self).FEvento.Evento[i].InfEvento.nSeqEvento;

        case InfEvento.tpEvento of
          teCancelamento:
          begin
            infEvento.detEvento.nProt := TCTeEnvEvento(Self).FEvento.Evento[i].InfEvento.detEvento.nProt;
            infEvento.detEvento.xJust := TCTeEnvEvento(Self).FEvento.Evento[i].InfEvento.detEvento.xJust;
          end;
          teEncerramento:
          begin
          (*
            infEvento.detEvento.nProt := TCTeEnvEvento(Self).FEvento.Evento[i].InfEvento.detEvento.nProt;
            infEvento.detEvento.dtEnc := TCTeEnvEvento(Self).FEvento.Evento[i].InfEvento.detEvento.dtEnc;
            infEvento.detEvento.cUF   := TCTeEnvEvento(Self).FEvento.Evento[i].InfEvento.detEvento.cUF;
            infEvento.detEvento.cMun  := TCTeEnvEvento(Self).FEvento.Evento[i].InfEvento.detEvento.cMun;
          *)  
          end;
        end;
      end;
   end;

  EventoCTe.GerarXML;

  {$IFDEF ACBrCTeOpenSSL}
  if not(CTeUtil.Assinar(EventoCTe.Gerador.ArquivoFormatoXML, TConfiguracoes(FConfiguracoes).Certificados.Certificado , TConfiguracoes(FConfiguracoes).Certificados.Senha, FDadosMsg, FMsg)) then
     begin
       if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
          TACBrCTe( FACBrCTe ).OnGerarLog('Falha ao assinar o Envio de Evento '+LineBreak+FMsg);
       raise EACBrCTeException.Create('Falha ao assinar o Envio de Evento '+LineBreak+FMsg);
     end;
  {$ELSE}
  if not(CTeUtil.Assinar(EventoCTe.Gerador.ArquivoFormatoXML, TConfiguracoes(FConfiguracoes).Certificados.GetCertificado , FDadosMsg, FMsg)) then
     begin
       if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
          TACBrCTe( FACBrCTe ).OnGerarLog('Falha ao assinar o Envio de Evento '+LineBreak+FMsg);
       raise EACBrCTeException.Create('Falha ao assinar o Envio de Evento '+LineBreak+FMsg);
     end;
  {$ENDIF}

  if not(CTeUtil.Valida(FDadosMsg, FMsg, TACBrCTe( FACBrCTe ).Configuracoes.Geral.PathSchemas)) then
     begin
       if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
          TACBrCTe( FACBrCTe ).OnGerarLog('Falha na valida��o dos dados do Envio de Evento '+LineBreak+FMsg);
       raise EACBrCTeException.Create('Falha na valida��o dos dados do Envio de Evento '+LineBreak+FMsg);
     end;

  for i := 0 to TCTeEnvEvento(Self).FEvento.Evento.Count-1 do
   begin
      TCTeEnvEvento(Self).FEvento.Evento[i].InfEvento.id := EventoCTe.Evento[i].InfEvento.id;
   end;
  EventoCTe.Free;

  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8_STD+'>', '', [rfReplaceAll] );
  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8+'>', '', [rfReplaceAll] );
  FDadosMsg := StringReplace( FDadosMsg, '<?xml version="1.0"?>', '', [rfReplaceAll] );
end;

{ TWebServices }
procedure TWebServices.Cancela(AJustificativa: String);
begin
// retirado por recomenda��o do documento disponivel em http://www.nfe.fazenda.gov.br/PORTAL/docs/Consumo_Indevido_Aplicacao_Cliente_v1.00.pdf
{  if TACBrCTe( FACBrCTe ).Configuracoes.Geral.FormaEmissao = teNormal then
   begin
     if not(Self.StatusServico.Executar) then
      begin
        if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
           TACBrCTe( FACBrCTe ).OnGerarLog(Self.StatusServico.Msg);
        raise Exception.Create(Self.StatusServico.Msg);
      end;
   end;}

  if not(Self.Consulta.Executar) then
     begin
       if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
          TACBrCTe( FACBrCTe ).OnGerarLog(Self.Consulta.Msg);
       raise Exception.Create(Self.Consulta.Msg);
     end;

  Self.Cancelamento.CTeChave      := Self.Consulta.CTeChave;
  Self.Cancelamento.Protocolo     := Self.Consulta.FProtocolo;
  Self.Cancelamento.Justificativa := AJustificativa;
  if not(Self.Cancelamento.Executar) then
     begin
       if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
          TACBrCTe( FACBrCTe ).OnGerarLog(Self.Cancelamento.Msg);
       raise Exception.Create(Self.Cancelamento.Msg);
     end;
end;

procedure TWebServices.Inutiliza(CNPJ, AJustificativa: String; Ano, Modelo, Serie, NumeroInicial, NumeroFinal : Integer);
begin
// retirado por recomenda��o do documento disponivel em http://www.nfe.fazenda.gov.br/PORTAL/docs/Consumo_Indevido_Aplicacao_Cliente_v1.00.pdf
{  if TACBrCTe( FACBrCTe ).Configuracoes.Geral.FormaEmissao = teNormal then
   begin
     if not(Self.StatusServico.Executar) then
      begin
        if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
           TACBrCTe( FACBrCTe ).OnGerarLog(Self.StatusServico.Msg);
         raise Exception.Create('WebService Consulta Status servi�o:'+LineBreak+
                               '- Inativo ou Inoperante tente novamente.'+LineBreak+
                               Self.StatusServico.Msg);
      end;
   end;}
  CNPJ := OnlyNumber(CNPJ);
  if not ValidarCNPJ(CNPJ) then
     raise Exception.Create('CNPJ '+CNPJ+' inv�lido.');

  Self.Inutilizacao.CTeChave      := 'ID';
  Self.Inutilizacao.CNPJ          := CNPJ;
  Self.Inutilizacao.Modelo        := Modelo;
  Self.Inutilizacao.Serie         := Serie;
  Self.Inutilizacao.Ano           := Ano;
  Self.Inutilizacao.NumeroInicial := NumeroInicial;
  Self.Inutilizacao.NumeroFinal   := NumeroFinal;
  Self.Inutilizacao.Justificativa := AJustificativa;

  if not(Self.Inutilizacao.Executar) then
     begin
       if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
          TACBrCTe( FACBrCTe ).OnGerarLog(Self.Inutilizacao.Msg);
       raise Exception.Create(Self.Inutilizacao.Msg);
     end;
end;

constructor TWebServices.Create(AFCTe: TComponent);
begin
 inherited Create( AFCTe );
  FACBrCTe          := TACBrCTe(AFCTe);
  FStatusServico    := TCTeStatusServico.Create(AFCTe);
  FEnviar           := TCTeRecepcao.Create(AFCTe, TACBrCTe(AFCTe).Conhecimentos);
  FRetorno          := TCTeRetRecepcao.Create(AFCTe, TACBrCTe(AFCTe).Conhecimentos);
  FRecibo           := TCTeRecibo.create(AFCTe);
  FConsulta         := TCTeConsulta.Create(AFCTe);
  FCancelamento     := TCTeCancelamento.Create(AFCTe);
  FInutilizacao     := TCTeInutilizacao.Create(AFCTe);
  FConsultaCadastro := TCTeConsultaCadastro.Create(AFCTe);
  FEnvEvento        := TCTeEnvEvento.Create(AFCTe,TACBrCTe(AFCTe).EventoCTe);
end;

destructor TWebServices.Destroy;
begin
  FStatusServico.Free;
  FEnviar.Free;
  FRetorno.Free;
  FRecibo.Free;
  FConsulta.Free;
  FCancelamento.Free;
  FInutilizacao.Free;
  FConsultaCadastro.Free;
  FEnvEvento.Free;
  inherited;
end;

function TWebServices.Envia(ALote: Integer): Boolean;
begin
// retirado por recomenda��o do documento disponivel em http://www.nfe.fazenda.gov.br/PORTAL/docs/Consumo_Indevido_Aplicacao_Cliente_v1.00.pdf
{  if not(Self.StatusServico.Executar) then
     begin
       if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
          TACBrCTe( FACBrCTe ).OnGerarLog(Self.StatusServico.Msg);
       raise Exception.Create(Self.StatusServico.Msg);
     end;}

  self.Enviar.FLote := ALote;
  if not(Self.Enviar.Executar) then
     begin
       if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
          TACBrCTe( FACBrCTe ).OnGerarLog(Self.Enviar.Msg);
       raise Exception.Create(Self.Enviar.Msg);
     end;

  Self.Retorno.Recibo := Self.Enviar.Recibo;
  if not(Self.Retorno.Executar) then
     begin
       if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
          TACBrCTe( FACBrCTe ).OnGerarLog(Self.Retorno.Msg);
       raise Exception.Create(Self.Retorno.Msg);
     end;
  Result := true;
end;

{ TCTeStatusServico }
function TCTeStatusServico.Executar: Boolean;
var
  CTeRetorno: TRetConsStatServ;
  aMsg  : string;
  Texto : String;
  Acao  : TStringList;
  Stream: TMemoryStream;
  StrStream: TStringStream;

  {$IFDEF ACBrCTeOpenSSL}
     HTTP: THTTPSend;
  {$ELSE}
     ReqResp: THTTPReqResp;
  {$ENDIF}
begin
  {Result :=} inherited Executar;

  Result := False;
  
  Acao   := TStringList.Create;
  Stream := TMemoryStream.Create;
  FcStat := 0;

  Texto := '<?xml version="1.0" encoding="utf-8"?>';
  Texto := Texto + '<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">';
  Texto := Texto +   '<soap12:Header>';
  Texto := Texto +     '<cteCabecMsg xmlns="http://www.portalfiscal.inf.br/cte/wsdl/CteStatusServico">';
  Texto := Texto +       '<cUF>'+IntToStr(FConfiguracoes.WebServices.UFCodigo)+'</cUF>';
  Texto := Texto +       '<versaoDados>'+CTeconsStatServ+'</versaoDados>';
  Texto := Texto +     '</cteCabecMsg>';
  Texto := Texto +   '</soap12:Header>';
  Texto := Texto +   '<soap12:Body>';
  Texto := Texto +     '<cteDadosMsg xmlns="http://www.portalfiscal.inf.br/cte/wsdl/CteStatusServico">';
  Texto := Texto + FDadosMsg;
  Texto := Texto +     '</cteDadosMsg>';
  Texto := Texto +   '</soap12:Body>';
  Texto := Texto + '</soap12:Envelope>';

  Acao.Text := Texto;

  {$IFDEF ACBrCTeOpenSSL}
     Acao.SaveToStream(Stream);
     HTTP := THTTPSend.Create;
  {$ELSE}
     ReqResp := THTTPReqResp.Create(nil);
     ConfiguraReqResp( ReqResp );
     ReqResp.URL := Trim(FURL);
     ReqResp.UseUTF8InHeader := True;
     ReqResp.SoapAction := 'http://www.portalfiscal.inf.br/cte/wsdl/CteStatusServico/cteStatusServicoCT';
  {$ENDIF}

  try
    TACBrCTe( FACBrCTe ).SetStatus( stCTeStatusServico );
    if FConfiguracoes.Geral.Salvar then
      FConfiguracoes.Geral.Save(FormatDateTime('yyyymmddhhnnss',Now)+'-ped-sta.xml', FDadosMsg);

    try
      {$IFDEF ACBrCTeOpenSSL}
         HTTP.Document.LoadFromStream(Stream);
         ConfiguraHTTP(HTTP,'SOAPAction: "http://www.portalfiscal.inf.br/cte/wsdl/CteStatusServico/cteStatusServicoCT"');
         HTTP.HTTPMethod('POST', FURL);
         StrStream := TStringStream.Create('');
         StrStream.CopyFrom(HTTP.Document, 0);

         FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
         FRetWS := SeparaDados( FRetornoWS, 'cteStatusServicoCTResult');
         StrStream.Free;
      {$ELSE}
         ReqResp.Execute(Acao.Text, Stream);
         StrStream := TStringStream.Create('');
         StrStream.CopyFrom(Stream, 0);
         FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
         FRetWS := SeparaDados( FRetornoWS, 'cteStatusServicoCTResult');
         StrStream.Free;
      {$ENDIF}
      CTeRetorno := TRetConsStatServ.Create;
      CTeRetorno.Leitor.Arquivo := FRetWS;
      CTeRetorno.LerXml;

      TACBrCTe( FACBrCTe ).SetStatus( stCTeIdle );
      aMsg := 'Ambiente : '+TpAmbToStr(CTeRetorno.tpAmb)+LineBreak+
              'Vers�o Aplicativo : '+CTeRetorno.verAplic+LineBreak+
              'Status C�digo : '+IntToStr(CTeRetorno.cStat)+LineBreak+
              'Status Descri��o : '+CTeRetorno.xMotivo+LineBreak+
              'UF : '+CodigoParaUF(CTeRetorno.cUF)+LineBreak+
              'Recebimento : '+DFeUtil.SeSenao(CTeRetorno.DhRecbto = 0, '', DateTimeToStr(CTeRetorno.dhRecbto))+LineBreak+
              'Tempo M�dio : '+IntToStr(CTeRetorno.TMed)+LineBreak+
              'Retorno : '+ DFeUtil.SeSenao(CTeRetorno.dhRetorno = 0, '', DateTimeToStr(CTeRetorno.dhRetorno))+LineBreak+
              'Observa��o : '+CTeRetorno.xObs;
      if FConfiguracoes.WebServices.Visualizar then
        ShowMessage(aMsg);

      if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
         TACBrCTe( FACBrCTe ).OnGerarLog(aMsg);

      FtpAmb     := CTeRetorno.tpAmb;
      FverAplic  := CTeRetorno.verAplic;
      FcStat     := CTeRetorno.cStat;
      FxMotivo   := CTeRetorno.xMotivo;
      FcUF       := CTeRetorno.cUF;
      FdhRecbto  := CTeRetorno.dhRecbto;
      FTMed      := CTeRetorno.TMed;
      FdhRetorno := CTeRetorno.dhRetorno;
      FxObs      := CTeRetorno.xObs;

      if TACBrCTe( FACBrCTe ).Configuracoes.WebServices.AjustaAguardaConsultaRet then
         TACBrCTe( FACBrCTe ).Configuracoes.WebServices.AguardarConsultaRet := FTMed * 1000;

      FMsg   := CTeRetorno.XMotivo + LineBreak+CTeRetorno.XObs;

      Result := (CTeRetorno.CStat = 107); // 107 = Servi�o em Opera��o
      CTeRetorno.Free;

      if FConfiguracoes.Geral.Salvar then
        FConfiguracoes.Geral.Save(FormatDateTime('yyyymmddhhnnss',Now)+'-sta.xml', FRetWS);

    except on E: Exception do
      begin
       if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
          TACBrCTe( FACBrCTe ).OnGerarLog('WebService Consulta Status servi�o:'+LineBreak+
                                          '- Inativo ou Inoperante tente novamente.'+LineBreak+
                                          '- '+E.Message);
       raise Exception.Create('WebService Consulta Status servi�o:'+LineBreak+
                              '- Inativo ou Inoperante tente novamente.'+LineBreak+
                              '- '+E.Message);
      end;
    end;

  finally
    {$IFDEF ACBrCTeOpenSSL}
       HTTP.Free;
    {$ELSE}
      ReqResp.Free;
    {$ENDIF}
    Acao.Free;
    Stream.Free;
    DFeUtil.ConfAmbiente;
    TACBrCTe( FACBrCTe ).SetStatus( stCTeIdle );
  end;
end;

{ TCTeRecepcao }
constructor TCTeRecepcao.Create(AOwner: TComponent; ACTes: TConhecimentos);
begin
  inherited Create(AOwner);
  FCTes := ACTes;
end;

function TCTeRecepcao.Executar: Boolean;
var
  CTeRetorno: TretEnvCTe;
  aMsg  : string;
  Texto : String; //  Texto : WideString;
  Acao  : TStringList;
  Stream: TMemoryStream;
  StrStream: TStringStream;

  {$IFDEF ACBrCTeOpenSSL}
     HTTP: THTTPSend;
  {$ELSE}
     ReqResp: THTTPReqResp;
  {$ENDIF}
begin
  inherited Executar;

  Result := False;
  Acao   := TStringList.Create;
  Stream := TMemoryStream.Create;
  FcStat := 0;

  Texto := '<?xml version="1.0" encoding="utf-8"?>';
  Texto := Texto + '<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">';
  Texto := Texto +   '<soap12:Header>';
  Texto := Texto +     '<cteCabecMsg xmlns="http://www.portalfiscal.inf.br/cte/wsdl/CteRecepcao">';
  Texto := Texto +       '<cUF>'+IntToStr(FConfiguracoes.WebServices.UFCodigo)+'</cUF>';
  Texto := Texto +       '<versaoDados>'+CTeenviCTe+'</versaoDados>';
  Texto := Texto +     '</cteCabecMsg>';
  Texto := Texto +   '</soap12:Header>';
  Texto := Texto +   '<soap12:Body>';
  Texto := Texto +     '<cteDadosMsg xmlns="http://www.portalfiscal.inf.br/cte/wsdl/CteRecepcao">';
  Texto := Texto + FDadosMsg;
  Texto := Texto +     '</cteDadosMsg>';
  Texto := Texto +   '</soap12:Body>';
  Texto := Texto + '</soap12:Envelope>';

  Acao.Text := Texto;

  {$IFDEF ACBrCTeOpenSSL}
     Acao.SaveToStream(Stream);
     HTTP := THTTPSend.Create;
  {$ELSE}
     ReqResp := THTTPReqResp.Create(nil);
     ConfiguraReqResp( ReqResp );
     ReqResp.URL := Trim(FURL);
     ReqResp.UseUTF8InHeader := True;
     ReqResp.SoapAction := 'http://www.portalfiscal.inf.br/cte/wsdl/CteRecepcao/cteRecepcaoLote';
  {$ENDIF}

  try
    TACBrCTe( FACBrCTe ).SetStatus( stCTeRecepcao );
    if FConfiguracoes.Geral.Salvar then
      FConfiguracoes.Geral.Save(IntToStr(Lote)+'-env-lot.xml', FDadosMsg);
    try
      {$IFDEF ACBrCTeOpenSSL}
         HTTP.Document.LoadFromStream(Stream);
         ConfiguraHTTP(HTTP,'SOAPAction: "http://www.portalfiscal.inf.br/cte/wsdl/CteRecepcao/cteRecepcaoLote"');
         HTTP.HTTPMethod('POST', FURL);

         StrStream := TStringStream.Create('');
         StrStream.CopyFrom(HTTP.Document, 0);
         FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
         FRetWS := SeparaDados( FRetornoWS, 'cteRecepcaoLoteResult');
         StrStream.Free;
      {$ELSE}
         ReqResp.Execute(Acao.Text, Stream);
         StrStream := TStringStream.Create('');
         StrStream.CopyFrom(Stream, 0);
         FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
         FRetWS := SeparaDados( FRetornoWS, 'cteRecepcaoLoteResult');
         StrStream.Free;
      {$ENDIF}
      CTeRetorno := TretEnvCTe.Create;
      CTeRetorno.Leitor.Arquivo := FRetWS;
      CTeRetorno.LerXml;

      TACBrCTe( FACBrCTe ).SetStatus( stCTeIdle );
      aMsg := 'Ambiente : '+TpAmbToStr(CTeRetorno.tpAmb)+LineBreak+
                      'Vers�o Aplicativo : '+CTeRetorno.verAplic+LineBreak+
                      'Status C�digo : '+IntToStr(CTeRetorno.cStat)+LineBreak+
                      'Status Descri��o : '+CTeRetorno.xMotivo+LineBreak+
                      'UF : '+CodigoParaUF(CTeRetorno.cUF)+LineBreak+
                      'Recebimento : '+DFeUtil.SeSenao(CTeRetorno.InfRec.dhRecbto = 0, '', DateTimeToStr(CTeRetorno.InfRec.dhRecbto))+LineBreak+
                      'Tempo M�dio : '+IntToStr(CTeRetorno.infRec.tMed)+LineBreak+
                      'N�mero Recibo: '+CTeRetorno.infRec.nRec;
      if FConfiguracoes.WebServices.Visualizar then
        ShowMessage(aMsg);

      if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
         TACBrCTe( FACBrCTe ).OnGerarLog(aMsg);

      FtpAmb    := CTeRetorno.tpAmb;
      FverAplic := CTeRetorno.verAplic;
      FcStat    := CTeRetorno.cStat;
      FxMotivo  := CTeRetorno.xMotivo;
      FcUF      := CTeRetorno.cUF;
      FdhRecbto := CTeRetorno.infRec.dhRecbto;
      FTMed     := CTeRetorno.infRec.tMed;
      FRecibo   := CTeRetorno.infRec.nRec;
      FMsg      := CTeRetorno.XMotivo;

      Result := (CTeRetorno.CStat = 103); // 103 = Lote Recebido

      CTeRetorno.Free;

      if FConfiguracoes.Geral.Salvar then
        FConfiguracoes.Geral.Save(IntToStr(Lote)+'-rec.xml', FRetWS);

    except on E: Exception do
      begin
        raise Exception.Create('WebService Retorno de Recep��o:'+LineBreak+
                               '- Inativo ou Inoperante tente novamente.'+LineBreak+
                               '- '+E.Message);
      end;
    end;

  finally
    {$IFDEF ACBrCTeOpenSSL}
       HTTP.Free;
    {$ELSE}
      ReqResp.Free;
    {$ENDIF}
    Acao.Free;
    Stream.Free;
    DFeUtil.ConfAmbiente;
    TACBrCTe( FACBrCTe ).SetStatus( stCTeIdle );
  end;
end;

function TCTeRecepcao.GetLote: Integer;
begin
  Result := FLote;
end;

{ TCteRetRecepcao }

// Incluido por Italo em 04/12/2012 (Sujest�o de Nilton Olher)
procedure TCteRetRecepcao.Clear;
var
  i, j : Integer;
begin
  // Limpa Dados do retorno;
  FMsg      := '';
  FverAplic := '';
  FcStat    := 0;
  FxMotivo  := '';

  // Limpa Dados dos retornos dos conhecimentos;
  for i := 0 to FCTeRetorno.ProtCTe.Count - 1 do
    begin
      for j := 0 to FCTes.Count - 1 do
        begin
          if FCTeRetorno.ProtCTe.Items[i].chCTe = StringReplace(FCTes.Items[j].CTe.InfCTe.Id, 'CTe', '', [rfIgnoreCase])
            then begin
              FCTes.Items[j].Confirmada           := False;
              FCTes.Items[j].Msg                  := '';
              FCTes.Items[j].CTe.procCTe.verAplic := '';
              FCTes.Items[j].CTe.procCTe.chCTe    := '';
              FCTes.Items[j].CTe.procCTe.dhRecbto := 0;
              FCTes.Items[j].CTe.procCTe.nProt    := '';
              FCTes.Items[j].CTe.procCTe.digVal   := '';
              FCTes.Items[j].CTe.procCTe.cStat    := 0;
              FCTes.Items[j].CTe.procCTe.xMotivo  := '';
            end;
        end;
    end;
end;

function TCteRetRecepcao.Confirma(AInfProt: TProtCteCollection): Boolean;
var
  i,j     : Integer;
  AProcCTe: TProcCte;
begin
  Result := False;

  //Setando os retornos dos conhecimentos;
  for i := 0 to AInfProt.Count-1 do
  begin
    for j := 0 to FCTes.Count-1 do
    begin
      if AInfProt.Items[i].chCTe = StringReplace(FCTes.Items[j].CTe.InfCTe.Id,'CTe','',[rfIgnoreCase]) then
       begin
         FCTes.Items[j].Confirmada           := (AInfProt.Items[i].cStat = 100); // 100 = Autorizao o Uso
         FCTes.Items[j].Msg                  := AInfProt.Items[i].xMotivo;
         FCTes.Items[j].CTe.procCTe.tpAmb    := AInfProt.Items[i].tpAmb;
         FCTes.Items[j].CTe.procCTe.verAplic := AInfProt.Items[i].verAplic;
         FCTes.Items[j].CTe.procCTe.chCTe    := AInfProt.Items[i].chCTe;
         FCTes.Items[j].CTe.procCTe.dhRecbto := AInfProt.Items[i].dhRecbto;
         FCTes.Items[j].CTe.procCTe.nProt    := AInfProt.Items[i].nProt;
         FCTes.Items[j].CTe.procCTe.digVal   := AInfProt.Items[i].digVal;
         FCTes.Items[j].CTe.procCTe.cStat    := AInfProt.Items[i].cStat;
         FCTes.Items[j].CTe.procCTe.xMotivo  := AInfProt.Items[i].xMotivo;

         if FConfiguracoes.Geral.Salvar or DFeUtil.NaoEstaVazio(FCTes.Items[j].NomeArq) then
          begin
            if FileExists(PathWithDelim(FConfiguracoes.Geral.PathSalvar)+AInfProt.Items[i].chCTe+'-cte.xml') and
               FileExists(PathWithDelim(FConfiguracoes.Geral.PathSalvar)+FCTeRetorno.nRec+'-pro-rec.xml') then
             begin
               AProcCTe := TProcCTe.Create;
               AProcCTe.PathCTe := PathWithDelim(FConfiguracoes.Geral.PathSalvar)+AInfProt.Items[i].chCTe+'-cte.xml';
               AProcCTe.PathRetConsReciCTe := PathWithDelim(FConfiguracoes.Geral.PathSalvar)+FCTeRetorno.nRec+'-pro-rec.xml';
               AProcCTe.GerarXML;
               if DFeUtil.NaoEstaVazio(AProcCTe.Gerador.ArquivoFormatoXML) then
                begin
                  if DFeUtil.NaoEstaVazio(FCTes.Items[j].NomeArq) then
                     AProcCTe.Gerador.SalvarArquivo(FCTes.Items[j].NomeArq)
                  else
                     AProcCTe.Gerador.SalvarArquivo(PathWithDelim(FConfiguracoes.Geral.PathSalvar)+AInfProt.Items[i].chCTe+'-cte.xml');
                end;
               AProcCTe.Free;
             end;
          end;
         if FConfiguracoes.Arquivos.Salvar then
            if FConfiguracoes.Arquivos.EmissaoPathCTe then
               FCTes.Items[j].SaveToFile(PathWithDelim(FConfiguracoes.Arquivos.GetPathCTe(FCTes.Items[j].CTe.Ide.dhEmi))+StringReplace(FCTes.Items[j].CTe.InfCTe.Id,'CTe','',[rfIgnoreCase])+'-cte.xml')
            else
               FCTes.Items[j].SaveToFile(PathWithDelim(FConfiguracoes.Arquivos.GetPathCTe)+StringReplace(FCTes.Items[j].CTe.InfCTe.Id,'CTe','',[rfIgnoreCase])+'-cte.xml');
         break;
       end;
    end;
  end;

  //Verificando se existe algum Conhecimento confirmada
  for i := 0 to FCTes.Count-1 do
  begin
    if FCTes.Items[i].Confirmada then
    begin
      Result := True;
      break;
    end;
  end;

  //Verificando se existe algum Conhecimento nao confirmada
  for i := 0 to FCTes.Count-1 do
  begin
    if not(FCTes.Items[i].Confirmada) then
    begin
      FMsg := 'Conhecimento(s) n�o confirmado(s):'+LineBreak;
      break;
    end;
  end;

  //Montando a mensagem de retorno para os Conhecimento nao confirmadas
  for i := 0 to FCTes.Count-1 do
  begin
    if not(FCTes.Items[i].Confirmada) then
      FMsg := FMsg+IntToStr(FCTes.Items[i].CTe.Ide.nCT)+'->'+FCTes.Items[i].Msg+LineBreak;
  end;
end;

constructor TCteRetRecepcao.Create(AOwner: TComponent;
  AConhecimentos: TConhecimentos);
begin
  inherited Create(AOwner);
  FCTes := AConhecimentos;
end;

destructor TCteRetRecepcao.destroy;
begin
   if assigned(FCteRetorno) then
      FCteRetorno.Free;
   inherited;
end;

function TCteRetRecepcao.Executar: Boolean;

 function Processando: Boolean;
 var
    aMsg  : string;
    Texto : String;
    Acao  : TStringList;
    Stream: TMemoryStream;
    StrStream: TStringStream;
    {$IFDEF ACBrCTeOpenSSL}
       HTTP: THTTPSend;
    {$ELSE}
       ReqResp: THTTPReqResp;
    {$ENDIF}
 begin
    Acao   := TStringList.Create;
    Stream := TMemoryStream.Create;

    Texto := '<?xml version="1.0" encoding="utf-8"?>';
    Texto := Texto + '<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">';
    Texto := Texto +   '<soap12:Header>';
    Texto := Texto +     '<cteCabecMsg xmlns="http://www.portalfiscal.inf.br/cte/wsdl/CteRetRecepcao">';
    Texto := Texto +       '<cUF>'+IntToStr(FConfiguracoes.WebServices.UFCodigo)+'</cUF>';
    Texto := Texto +       '<versaoDados>'+CTeconsReciCTe+'</versaoDados>';
    Texto := Texto +     '</cteCabecMsg>';
    Texto := Texto +   '</soap12:Header>';
    Texto := Texto +   '<soap12:Body>';
    Texto := Texto +     '<cteDadosMsg xmlns="http://www.portalfiscal.inf.br/cte/wsdl/CteRetRecepcao">';
    Texto := Texto + FDadosMsg;
    Texto := Texto +     '</cteDadosMsg>';
    Texto := Texto +   '</soap12:Body>';
    Texto := Texto + '</soap12:Envelope>';

    Acao.Text := Texto;

    {$IFDEF ACBrCTeOpenSSL}
       Acao.SaveToStream(Stream);
       HTTP := THTTPSend.Create;
    {$ELSE}
       ReqResp := THTTPReqResp.Create(nil);
       ConfiguraReqResp( ReqResp );
       ReqResp.URL := Trim(FURL);
       ReqResp.UseUTF8InHeader := True;
       ReqResp.SoapAction := 'http://www.portalfiscal.inf.br/cte/wsdl/CteRetRecepcao/cteRetRecepcao';
    {$ENDIF}

    try
      TACBrCTe( FACBrCTe ).SetStatus( stCTeRetRecepcao );
      if assigned(FCTeRetorno) then
         FCTeRetorno.Free;

      if FConfiguracoes.Geral.Salvar then
        FConfiguracoes.Geral.Save(Recibo+'-ped-rec.xml', FDadosMsg);

      {$IFDEF ACBrCTeOpenSSL}
         HTTP.Document.LoadFromStream(Stream);
         ConfiguraHTTP(HTTP,'SOAPAction: "http://www.portalfiscal.inf.br/cte/wsdl/CteRetRecepcao/cteRetRecepcao"');
         HTTP.HTTPMethod('POST', FURL);

         StrStream := TStringStream.Create('');
         StrStream.CopyFrom(HTTP.Document, 0);
         FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
         FRetWS := SeparaDados( FRetornoWS, 'cteRetRecepcaoResult');
         StrStream.Free;
      {$ELSE}
         ReqResp.Execute(Acao.Text, Stream);
         StrStream := TStringStream.Create('');
         StrStream.CopyFrom(Stream, 0);
         FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
         FRetWS := SeparaDados( FRetornoWS, 'cteRetRecepcaoResult');
         StrStream.Free;
      {$ENDIF}
      if FConfiguracoes.Geral.Salvar then
         FConfiguracoes.Geral.Save(Recibo+'-pro-rec.xml', FRetWS);

      FCteRetorno := TRetConsReciCTe.Create;
      FCteRetorno.Leitor.Arquivo := FRetWS;
      FCteRetorno.LerXML;

      TACBrCTe( FACBrCTe ).SetStatus( stCTeIdle );
      aMsg := 'Ambiente : '+TpAmbToStr(FCTeRetorno.TpAmb)+LineBreak+
              'Vers�o Aplicativo : '+FCTeRetorno.verAplic+LineBreak+
              'Recibo : '+FCTeRetorno.nRec+LineBreak+
              'Status C�digo : '+IntToStr(FCTeRetorno.cStat)+LineBreak+
              'Status Descri��o : '+FCTeRetorno.xMotivo+LineBreak+
              'UF : '+CodigoParaUF(FCTeRetorno.cUF)+LineBreak;
      if FConfiguracoes.WebServices.Visualizar then
        ShowMessage(aMsg);

      if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
         TACBrCTe( FACBrCTe ).OnGerarLog(aMsg);

      FTpAmb    := FCTeRetorno.TpAmb;
      FverAplic := FCTeRetorno.verAplic;
      FcStat    := FCTeRetorno.cStat;
      FcUF      := FCTeRetorno.cUF;
      FMsg      := FCTeRetorno.xMotivo;
      FxMotivo  := FCTeRetorno.xMotivo;
//      FcMsg     := FCTeRetorno.cMsg;
//      FxMsg     := FCTeRetorno.xMsg;

      Result := FCTeRetorno.CStat = 105; // 105 = Lote em Processamento
      if FCTeRetorno.CStat = 104 then    // 104 = Lote Processado
      begin
         FMsg     := FCTeRetorno.ProtCTe.Items[0].xMotivo;
         FxMotivo := FCTeRetorno.ProtCte.Items[0].xMotivo;
      end;

    finally
      {$IFDEF ACBrCTeOpenSSL}
         HTTP.Free;
      {$ELSE}
         ReqResp.Free;
      {$ENDIF}
      Acao.Free;
      Stream.Free;
      DFeUtil.ConfAmbiente;
      TACBrCTe( FACBrCTe ).SetStatus( stCTeIdle );
    end;
 end;

var
  vCont: Integer;
begin
  {Result :=} inherited Executar;
  Result := False;
  FcStat := 0;

  TACBrCTe( FACBrCTe ).SetStatus( stCTeRetRecepcao );
  Sleep(TACBrCTe( FACBrCTe ).Configuracoes.WebServices.AguardarConsultaRet);
  vCont := 1000;
  while Processando do
  begin
    if TACBrCTe( FACBrCTe ).Configuracoes.WebServices.IntervaloTentativas > 0 then
       sleep(TACBrCTe( FACBrCTe ).Configuracoes.WebServices.IntervaloTentativas)
    else
       sleep(vCont);

    if vCont > (TACBrCTe( FACBrCTe ).Configuracoes.WebServices.Tentativas * 1000) then
      break;

    vCont := vCont + 1000;
  end;
  TACBrCTe( FACBrCTe ).SetStatus( stCTeIdle );

  if FCTeRetorno.CStat = 104 then  // 104 = Lote Processado
   begin
    Result     := Confirma(FCTeRetorno.ProtCTe);
    fChaveCTe  := FCTeRetorno.ProtCTe.Items[0].chCTe;
    fProtocolo := FCTeRetorno.ProtCTe.Items[0].nProt;
    fcStat     := FCTeRetorno.ProtCTe.Items[0].cStat;
   end;
end;

{ TCteRecibo }

// Incluido por Italo em 03/01/2013 (Sujest�o de Nilton Olher)
procedure TCteRecibo.Clear;
begin
  // Limpa Dados do retorno;
  FMsg      := '';
  FverAplic := '';
  FcStat    := 0;
  FxMotivo  := '';
end;

constructor TCteRecibo.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
end;

destructor TCteRecibo.destroy;
begin
   if assigned(FCTeRetorno) then
      FCTeRetorno.Free;
  inherited;
end;

function TCteRecibo.Executar: Boolean;
var
 aMsg  : string;
 Texto : String;
 Acao  : TStringList;
 Stream: TMemoryStream;
 StrStream: TStringStream;
// MotivoAux : String;
 {$IFDEF ACBrCTeOpenSSL}
    HTTP: THTTPSend;
 {$ELSE}
    ReqResp: THTTPReqResp;
 {$ENDIF}
begin
  if assigned(FCTeRetorno) then
   FCTeRetorno.Free;

  {Result :=} inherited Executar;

  Acao   := TStringList.Create;
  Stream := TMemoryStream.Create;
  FcStat := 0;

  Texto := '<?xml version="1.0" encoding="utf-8"?>';
  Texto := Texto + '<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">';
  Texto := Texto +   '<soap12:Header>';
  Texto := Texto +     '<cteCabecMsg xmlns="http://www.portalfiscal.inf.br/cte/wsdl/CteRetRecepcao">';
  Texto := Texto +       '<cUF>'+IntToStr(FConfiguracoes.WebServices.UFCodigo)+'</cUF>';
  Texto := Texto +       '<versaoDados>'+CTeconsReciCTe+'</versaoDados>';
  Texto := Texto +     '</cteCabecMsg>';
  Texto := Texto +   '</soap12:Header>';
  Texto := Texto +   '<soap12:Body>';
  Texto := Texto +     '<cteDadosMsg xmlns="http://www.portalfiscal.inf.br/cte/wsdl/CteRetRecepcao">';
  Texto := Texto + FDadosMsg;
  Texto := Texto +     '</cteDadosMsg>';
  Texto := Texto +   '</soap12:Body>';
  Texto := Texto + '</soap12:Envelope>';

  Acao.Text := Texto;

  {$IFDEF ACBrCTeOpenSSL}
    Acao.SaveToStream(Stream);
    HTTP := THTTPSend.Create;
  {$ELSE}
     ReqResp := THTTPReqResp.Create(nil);
     ConfiguraReqResp( ReqResp );
     ReqResp.URL := Trim(FURL);
     ReqResp.UseUTF8InHeader := True;
     ReqResp.SoapAction := 'http://www.portalfiscal.inf.br/cte/wsdl/CteRetRecepcao/cteRetRecepcao';
  {$ENDIF}

 FCTeRetorno := TRetConsReciCTe.Create;
 try
   TACBrCTe( FACBrCTe ).SetStatus( stCTeRetRecepcao );

   if FConfiguracoes.Geral.Salvar then
     FConfiguracoes.Geral.Save(Recibo+'-ped-rec.xml', FDadosMsg);

   {$IFDEF ACBrCTeOpenSSL}
      HTTP.Document.LoadFromStream(Stream);
      ConfiguraHTTP(HTTP,'SOAPAction: "http://www.portalfiscal.inf.br/cte/wsdl/CteRetRecepcao/cteRetRecepcao"');
      HTTP.HTTPMethod('POST', FURL);

      StrStream := TStringStream.Create('');
      StrStream.CopyFrom(HTTP.Document, 0);
      FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
      FRetWS := SeparaDados( FRetornoWS, 'cteRetRecepcaoResult');
      StrStream.Free;
   {$ELSE}
      ReqResp.Execute(Acao.Text, Stream);
      StrStream := TStringStream.Create('');
      StrStream.CopyFrom(Stream, 0);
      FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
      FRetWS := SeparaDados( FRetornoWS, 'cteRetRecepcaoResult');
      StrStream.Free;
   {$ENDIF}

   if FConfiguracoes.Geral.Salvar then
    FConfiguracoes.Geral.Save(Recibo+'-pro-rec.xml', FRetWS);

   FCTeRetorno.Leitor.Arquivo := FRetWS;
   FCTeRetorno.LerXML;

   TACBrCTe( FACBrCTe ).SetStatus( stCTeIdle );
   aMsg := 'Ambiente : '+TpAmbToStr(FCTeRetorno.TpAmb)+LineBreak+
           'Vers�o Aplicativo : '+FCTeRetorno.verAplic+LineBreak+
           'Recibo : '+FCTeRetorno.nRec+LineBreak+
           'Status C�digo : '+IntToStr(FCTeRetorno.cStat)+LineBreak+
           'Status Descri��o : '+FCTeRetorno.xMotivo+LineBreak+
           'UF : '+CodigoParaUF(FCTeRetorno.cUF)+LineBreak;
   if FConfiguracoes.WebServices.Visualizar then
     ShowMessage(aMsg);

//   if FCTeRetorno.ProtCTe.Count > 0 then
//     MotivoAux := FCTeRetorno.ProtCTe.Items[0].xMotivo
//   else
//     MotivoAux := '';

   if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
      TACBrCTe( FACBrCTe ).OnGerarLog(aMsg);

   FTpAmb    := FCTeRetorno.TpAmb;
   FverAplic := FCTeRetorno.verAplic;
   FcStat    := FCTeRetorno.cStat;
   FxMotivo  := FCTeRetorno.xMotivo;
   FcUF      := FCTeRetorno.cUF;
   FMsg      := FCTeRetorno.xMotivo;
//   FxMotivo  := MotivoAux;

//   Result := FCTeRetorno.CStat = 105; // Lote em Processamento
//   FMsg   := MotivoAux;
   Result := FCTeRetorno.CStat = 104;

 finally
   {$IFDEF ACBrCTeOpenSSL}
      HTTP.Free;
   {$ELSE}
      ReqResp.Free;
   {$ENDIF}
   Acao.Free;
   Stream.Free;
   DFeUtil.ConfAmbiente;
   TACBrCTe( FACBrCTe ).SetStatus( stCTeIdle );
 end;
end;

{ TCTeConsulta }

// Incluido por Italo em 03/01/2013 (Sujest�o de Nilton Olher)
procedure TCTeConsulta.Clear;
begin
  // Limpa Dados do retorno;
  FMsg      := '';
  FverAplic := '';
  FcStat    := 0;
  FxMotivo  := '';
end;

constructor TCTeConsulta.Create(AOwner: TComponent);
begin
  FConfiguracoes := TConfiguracoes( TACBrCTe( AOwner ).Configuracoes );
  FACBrCTe       := TACBrCTe( AOwner );

  FprotCTe:= TProcCTe.Create;
  FretCancCTe:= TRetCancCTe.Create;
end;

destructor TCTeConsulta.Destroy;
begin
  FprotCTe.Free;
  FretCancCTe.Free;
end;

function TCTeConsulta.Executar: Boolean;
var
  CTeRetorno: TRetConsSitCTe;
  aMsg : String;
  AProcCTe: TProcCTe;
  i: Integer;
  Texto : String;
  Acao  : TStringList;
  Stream: TMemoryStream;
  StrStream: TStringStream;
  wAtualiza: boolean;

  {$IFDEF ACBrCTeOpenSSL}
     HTTP: THTTPSend;
  {$ELSE}
     ReqResp: THTTPReqResp;
  {$ENDIF}
begin
  {Result :=} inherited Executar;

  Acao   := TStringList.Create;
  Stream := TMemoryStream.Create;
  FcStat := 0;

  Texto := '<?xml version="1.0" encoding="utf-8"?>';
  Texto := Texto + '<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">';
  Texto := Texto +   '<soap12:Header>';
  Texto := Texto +     '<cteCabecMsg xmlns="http://www.portalfiscal.inf.br/cte/wsdl/CteConsulta">';
  Texto := Texto +       '<cUF>'+IntToStr(FConfiguracoes.WebServices.UFCodigo)+'</cUF>';
  Texto := Texto +       '<versaoDados>'+CTeconsSitCTe+'</versaoDados>';
  Texto := Texto +     '</cteCabecMsg>';
  Texto := Texto +   '</soap12:Header>';
  Texto := Texto +   '<soap12:Body>';
  Texto := Texto +     '<cteDadosMsg xmlns="http://www.portalfiscal.inf.br/cte/wsdl/CteConsulta">';
  Texto := Texto + FDadosMsg;
  Texto := Texto +     '</cteDadosMsg>';
  Texto := Texto +   '</soap12:Body>';
  Texto := Texto + '</soap12:Envelope>';

  Acao.Text := Texto;

  {$IFDEF ACBrCTeOpenSSL}
     Acao.SaveToStream(Stream);
     HTTP := THTTPSend.Create;
  {$ELSE}
     ReqResp := THTTPReqResp.Create(nil);
     ConfiguraReqResp( ReqResp );
     ReqResp.URL := Trim(FURL);
     ReqResp.UseUTF8InHeader := True;
     ReqResp.SoapAction := 'http://www.portalfiscal.inf.br/cte/wsdl/CteConsulta/cteConsultaCT';
  {$ENDIF}

  CTeRetorno := TRetConsSitCTe.Create;
  try
    TACBrCTe( FACBrCTe ).SetStatus( stCTeConsulta );
    if FConfiguracoes.Geral.Salvar then
      FConfiguracoes.Geral.Save(FCTeChave+'-ped-sit.xml', FDadosMsg);

    {$IFDEF ACBrCTeOpenSSL}
       HTTP.Document.LoadFromStream(Stream);
       ConfiguraHTTP(HTTP,'SOAPAction: "http://www.portalfiscal.inf.br/cte/wsdl/CteConsulta/cteConsultaCT"');
       HTTP.HTTPMethod('POST', FURL);

       StrStream := TStringStream.Create('');
       StrStream.CopyFrom(HTTP.Document, 0);
       FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
       FRetWS := SeparaDados( FRetornoWS, 'cteConsultaCTResult');
       StrStream.Free;
    {$ELSE}
       ReqResp.Execute(Acao.Text, Stream);
       StrStream := TStringStream.Create('');
       StrStream.CopyFrom(Stream, 0);
       FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
       FRetWS := SeparaDados( FRetornoWS, 'cteConsultaCTResult');
       StrStream.Free;
    {$ENDIF}

    CTeRetorno.Leitor.Arquivo := FRetWS;
    CTeRetorno.LerXML;

    FTpAmb      := CTeRetorno.TpAmb;
    FverAplic   := CTeRetorno.verAplic;
    FcStat      := CTeRetorno.cStat;
    FxMotivo    := CTeRetorno.xMotivo;
    FcUF        := CTeRetorno.cUF;
    FCTeChave   := CTeRetorno.chCTe;
    FMsg        := CTeRetorno.XMotivo;

    // Comentado por Italo em 16/11/2011
//    FprotCTe    := CTeRetorno.protCTe;    //Arrumar
//    FretCancCTe := CTeRetorno.retCancCTe; //Arrumar

    FprotCTe.PathCTe            := CTeRetorno.protCTe.PathCTe;
    FprotCTe.PathRetConsReciCTe := CTeRetorno.protCTe.PathRetConsReciCTe;
    FprotCTe.PathRetConsSitCTe  := CTeRetorno.protCTe.PathRetConsSitCTe;
    FprotCTe.PathRetConsSitCTe  := CTeRetorno.protCTe.PathRetConsSitCTe;
    FprotCTe.tpAmb              := CTeRetorno.protCTe.tpAmb;
    FprotCTe.verAplic           := CTeRetorno.protCTe.verAplic;
    FprotCTe.chCTe              := CTeRetorno.protCTe.chCTe;
    FprotCTe.dhRecbto           := CTeRetorno.protCTe.dhRecbto;
    FprotCTe.nProt              := CTeRetorno.protCTe.nProt;
    FprotCTe.digVal             := CTeRetorno.protCTe.digVal;
    FprotCTe.cStat              := CTeRetorno.protCTe.cStat;
    FprotCTe.xMotivo            := CTeRetorno.protCTe.xMotivo;

    FretCancCTe.tpAmb    := CTeRetorno.retCancCTe.tpAmb;
    FretCancCTe.verAplic := CTeRetorno.retCancCTe.verAplic;
    FretCancCTe.cStat    := CTeRetorno.retCancCTe.cStat;
    FretCancCTe.xMotivo  := CTeRetorno.retCancCTe.xMotivo;
    FretCancCTe.cUF      := CTeRetorno.retCancCTe.cUF;
    FretCancCTe.chCTe    := CTeRetorno.retCancCTe.chCTe;
    FretCancCTe.dhRecbto := CTeRetorno.retCancCTe.dhRecbto;
    FretCancCTe.nProt    := CTeRetorno.retCancCTe.nProt;

    FProtocolo := DFeUtil.SeSenao(DFeUtil.NaoEstaVazio(CTeRetorno.retCancCTe.nProt),CTeRetorno.retCancCTe.nProt,CTeRetorno.protCTe.nProt);
    FDhRecbto  := DFeUtil.SeSenao(CTeRetorno.retCancCTe.dhRecbto <> 0,CTeRetorno.retCancCTe.dhRecbto,CTeRetorno.protCTe.dhRecbto);

    TACBrCTe( FACBrCTe ).SetStatus( stCteIdle );
    aMsg := 'Identificador : '+CTeRetorno.protCTe.chCTe+LineBreak+
            'Ambiente : '+TpAmbToStr(CTeRetorno.TpAmb)+LineBreak+
            'Vers�o Aplicativo : '+CTeRetorno.verAplic+LineBreak+
            'Status C�digo : '+IntToStr(CTeRetorno.CStat)+LineBreak+
            'Status Descri��o : '+CTeRetorno.xMotivo+LineBreak+
            'UF : '+CodigoParaUF(CTeRetorno.cUF)+LineBreak+
            'Chave Acesso : '+FCTeChave+LineBreak+
            'Recebimento : '+DateTimeToStr(FDhRecbto)+LineBreak+
            'Protocolo : '+FProtocolo+LineBreak+
            'Digest Value : '+CTeRetorno.protCTe.digVal+LineBreak;
    if FConfiguracoes.WebServices.Visualizar then
      ShowMessage(aMsg);

    if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
       TACBrCTe( FACBrCTe ).OnGerarLog(aMsg);

    Result := (CTeRetorno.CStat in [100, 101, 110]);
    // 100 = Autorizado o Uso
    // 101 = Cancelamento Homologado
    // 110 = Uso Denegado

    if FConfiguracoes.Geral.Salvar  then
      FConfiguracoes.Geral.Save(FCTeChave+'-sit.xml', FRetWS);

    for i := 0 to TACBrCTe( FACBrCTe ).Conhecimentos.Count-1 do
     begin
        if StringReplace(TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].CTe.infCTe.ID,'CTe','',[rfIgnoreCase]) = FCTeChave then
         begin
            watualiza := true;
            if ((CTeRetorno.CStat = 101) and // 101 = Cancelamento Homologado
                (FConfiguracoes.Geral.AtualizarXMLCancelado=false)) then
               wAtualiza := False;

            TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].Confirmada := (CTeRetorno.cStat = 100); // 100 = Autorizado o Uso
            if wAtualiza then
            begin
              TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].Msg                  := CTeRetorno.xMotivo;
              TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].CTe.procCTe.tpAmb    := CTeRetorno.tpAmb;
              TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].CTe.procCTe.verAplic := CTeRetorno.verAplic;
              TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].CTe.procCTe.chCTe    := FCTeChave;
              TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].CTe.procCTe.dhRecbto := FDhRecbto;
              TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].CTe.procCTe.nProt    := FProtocolo;
              TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].CTe.procCTe.digVal   := CTeRetorno.protCTe.digVal;
              TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].CTe.procCTe.cStat    := CTeRetorno.cStat;
              TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].CTe.procCTe.xMotivo  := CTeRetorno.xMotivo;
            end;

            if ((FileExists(PathWithDelim(FConfiguracoes.Geral.PathSalvar)+FCTeChave+'-cte.xml') or DFeUtil.NaoEstaVazio(TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].NomeArq))
               and wAtualiza) then
            begin
             AProcCTe := TProcCTe.Create;
             if DFeUtil.NaoEstaVazio(TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].NomeArq) then
                AProcCTe.PathCTe := TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].NomeArq
             else
                AProcCTe.PathCTe := PathWithDelim(FConfiguracoes.Geral.PathSalvar)+FCTeChave+'-cte.xml';
             AProcCTe.PathRetConsSitCTe := PathWithDelim(FConfiguracoes.Geral.PathSalvar)+FCTeChave+'-sit.xml';
             AProcCTe.GerarXML;
             if DFeUtil.NaoEstaVazio(AProcCTe.Gerador.ArquivoFormatoXML) then
                AProcCTe.Gerador.SalvarArquivo(AProcCTe.PathCTe);
             AProcCTe.Free;
            end;

            if FConfiguracoes.Arquivos.Salvar and wAtualiza then
            begin
              if FConfiguracoes.Arquivos.EmissaoPathCTe then
                 TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].SaveToFile(PathWithDelim(FConfiguracoes.Arquivos.GetPathCTe(TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].CTe.Ide.dhEmi))+StringReplace(TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].CTe.InfCTe.Id,'CTe','',[rfIgnoreCase])+'-cte.xml')
              else
                 TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].SaveToFile(PathWithDelim(FConfiguracoes.Arquivos.GetPathCTe)+StringReplace(TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].CTe.InfCTe.Id,'CTe','',[rfIgnoreCase])+'-cte.xml');
            end;

            break;
         end;
     end;

    //CTeRetorno.Free;

    if (TACBrCTe( FACBrCTe ).Conhecimentos.Count <= 0) then
     begin
       if FConfiguracoes.Geral.Salvar then
        begin
          if FileExists(PathWithDelim(FConfiguracoes.Geral.PathSalvar)+FCTeChave+'-cte.xml') then
           begin
             AProcCTe := TProcCTe.Create;
             AProcCTe.PathCTe := PathWithDelim(FConfiguracoes.Geral.PathSalvar)+FCTeChave+'-cte.xml';
             AProcCTe.PathRetConsSitCTe := PathWithDelim(FConfiguracoes.Geral.PathSalvar)+FCTeChave+'-sit.xml';
             AProcCTe.GerarXML;
             if DFeUtil.NaoEstaVazio(AProcCTe.Gerador.ArquivoFormatoXML) then
                AProcCTe.Gerador.SalvarArquivo(AProcCTe.PathCTe);
             AProcCTe.Free;
           end;
        end;
     end;

  finally
    {$IFDEF ACBrCTeOpenSSL}
       HTTP.Free;
    {$ELSE}
      ReqResp.Free;
    {$ENDIF}
    CTeRetorno.Free;
    Acao.Free;
    Stream.Free;
    DFeUtil.ConfAmbiente;
    TACBrCTe( FACBrCTe ).SetStatus( stCTeIdle );
  end;
end;

{ TCTeCancelamento }

// Incluido por Italo em 03/01/2013 (Sujest�o de Nilton Olher)
procedure TCTeCancelamento.Clear;
begin
  // Limpa Dados do retorno;
  FMsg      := '';
  FverAplic := '';
  FcStat    := 0;
  FxMotivo  := '';
end;

function TCTeCancelamento.Executar: Boolean;
var
  CTeRetorno: TRetCancCTe;
  aMsg: string;
  i : Integer;
  Texto : String;
  Acao  : TStringList;
  Stream: TMemoryStream;
  StrStream: TStringStream;
//  wPROC: TStringList;
  {$IFDEF ACBrCTeOpenSSL}
     HTTP: THTTPSend;
  {$ELSE}
     ReqResp: THTTPReqResp;
  {$ENDIF}
begin
  {Result :=} inherited Executar;

  Acao   := TStringList.Create;
  Stream := TMemoryStream.Create;
  FcStat := 0;

  Texto := '<?xml version="1.0" encoding="utf-8"?>';
  Texto := Texto + '<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">';
  Texto := Texto +   '<soap12:Header>';
  Texto := Texto +     '<cteCabecMsg xmlns="http://www.portalfiscal.inf.br/cte/wsdl/CteCancelamento">';
  Texto := Texto +       '<cUF>'+IntToStr(FConfiguracoes.WebServices.UFCodigo)+'</cUF>';
  Texto := Texto +       '<versaoDados>'+CTecancCTe+'</versaoDados>';
  Texto := Texto +     '</cteCabecMsg>';
  Texto := Texto +   '</soap12:Header>';
  Texto := Texto +   '<soap12:Body>';
  Texto := Texto +     '<cteDadosMsg xmlns="http://www.portalfiscal.inf.br/cte/wsdl/CteCancelamento">';
  Texto := Texto + FDadosMsg;
  Texto := Texto +     '</cteDadosMsg>';
  Texto := Texto +   '</soap12:Body>';
  Texto := Texto + '</soap12:Envelope>';

  Acao.Text := Texto;

  {$IFDEF ACBrCTeOpenSSL}
     Acao.SaveToStream(Stream);
     HTTP := THTTPSend.Create;
  {$ELSE}
     ReqResp := THTTPReqResp.Create(nil);
     ConfiguraReqResp( ReqResp );
     ReqResp.URL := Trim(FURL);
     ReqResp.UseUTF8InHeader := True;
     ReqResp.SoapAction := 'http://www.portalfiscal.inf.br/cte/wsdl/CteCancelamento/cteCancelamentoCT';
  {$ENDIF}

  CTeRetorno := TRetCancCTe.Create;
  try
    TACBrCTe( FACBrCTe ).SetStatus( stCTeCancelamento );

    if FConfiguracoes.Geral.Salvar then
      FConfiguracoes.Geral.Save(FCTeChave+'-ped-can.xml', FDadosMsg);

    if FConfiguracoes.Arquivos.Salvar then
      FConfiguracoes.Geral.Save(FCTeChave+'-ped-can.xml', FDadosMsg, FConfiguracoes.Arquivos.GetPathCan );

    {$IFDEF ACBrCTeOpenSSL}
       HTTP.Document.LoadFromStream(Stream);
       ConfiguraHTTP(HTTP,'SOAPAction: "http://www.portalfiscal.inf.br/cte/wsdl/CteCancelamento/cteCancelamentoCT"');
       HTTP.HTTPMethod('POST', FURL);

       StrStream := TStringStream.Create('');
       StrStream.CopyFrom(HTTP.Document, 0);
       FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
       FRetWS := SeparaDados( FRetornoWS, 'cteCancelamentoCTResult');
       StrStream.Free;
    {$ELSE}
       ReqResp.Execute(Acao.Text, Stream);
       StrStream := TStringStream.Create('');
       StrStream.CopyFrom(Stream, 0);
       FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
       FRetWS := SeparaDados( FRetornoWS, 'cteCancelamentoCTResult');
       StrStream.Free;
    {$ENDIF}

    CTeRetorno.Leitor.Arquivo := FRetWS;
    CTeRetorno.LerXml;

    TACBrCTe( FACBrCTe ).SetStatus( stCTeIdle );
    aMsg := 'Identificador : '+ CTeRetorno.chCTe+LineBreak+
            'Ambiente : '+TpAmbToStr(CTeRetorno.TpAmb)+LineBreak+
            'Vers�o Aplicativo : '+CTeRetorno.verAplic+LineBreak+
            'Status C�digo : '+IntToStr(CTeRetorno.cStat)+LineBreak+
            'Status Descri��o : '+CTeRetorno.xMotivo+LineBreak+
            'UF : '+CodigoParaUF(CTeRetorno.cUF)+LineBreak+
            'Chave Acesso : '+CTeRetorno.chCTe+LineBreak+
            'Recebimento : '+DFeUtil.SeSenao(CTeRetorno.DhRecbto = 0, '', DateTimeToStr(CTeRetorno.DhRecbto))+LineBreak+
            'Protocolo : '+CTeRetorno.nProt+LineBreak;

    if FConfiguracoes.WebServices.Visualizar then
      ShowMessage(aMsg);

    if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
       TACBrCTe( FACBrCTe ).OnGerarLog(aMsg);

    FTpAmb     := CTeRetorno.TpAmb;
    FverAplic  := CTeRetorno.verAplic;
    FcStat     := CTeRetorno.cStat;
    FxMotivo   := CTeRetorno.xMotivo;
    FcUF       := CTeRetorno.cUF;
    FDhRecbto  := CTeRetorno.dhRecbto;
    Fprotocolo := CTeRetorno.nProt;
    FMsg       := CTeRetorno.XMotivo;

    Result := (CTeRetorno.CStat = 101); // 101 = Cancelamento Homologado

    for i := 0 to TACBrCTe( FACBrCTe ).Conhecimentos.Count-1 do
     begin
        if StringReplace(TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].CTe.infCTe.ID,'CTe','',[rfIgnoreCase]) = CTeRetorno.chCTE then
         begin
           if (FConfiguracoes.Geral.AtualizarXMLCancelado) then
           begin
              TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].Msg                  := CTeRetorno.xMotivo;
              TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].CTe.procCTe.tpAmb    := CTeRetorno.tpAmb;
              TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].CTe.procCTe.verAplic := CTeRetorno.verAplic;
              TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].CTe.procCTe.chCTe    := CTeRetorno.chCTe;
              TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].CTe.procCTe.dhRecbto := CTeRetorno.dhRecbto;
              TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].CTe.procCTe.nProt    := CTeRetorno.nProt;
              TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].CTe.procCTe.cStat    := CTeRetorno.cStat;
              TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].CTe.procCTe.xMotivo  := CTeRetorno.xMotivo;
           end;

           if FConfiguracoes.Arquivos.Salvar or DFeUtil.NaoEstaVazio(TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].NomeArq) then
            begin
              if ((CTeRetorno.CStat = 101) and // 101 = Cancelamento Homologado
                  (FConfiguracoes.Geral.AtualizarXMLCancelado)) then
              begin
                 if DFeUtil.NaoEstaVazio(TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].NomeArq) then
                    TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].SaveToFile(TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].NomeArq)
                 else
                 begin
                    if FConfiguracoes.Arquivos.EmissaoPathCTe then
                       TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].SaveToFile(PathWithDelim(FConfiguracoes.Arquivos.GetPathCTe(TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].CTe.Ide.dhEmi))+StringReplace(TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].CTe.InfCTe.Id,'CTe','',[rfIgnoreCase])+'-cte.xml')
                    else
                       TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].SaveToFile(PathWithDelim(FConfiguracoes.Arquivos.GetPathCTe)+StringReplace(TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].CTe.InfCTe.Id,'CTe','',[rfIgnoreCase])+'-cte.xml');
                 end;
              end;
            end;

           break;
         end;
     end;

    //CTeRetorno.Free;

    if FConfiguracoes.Geral.Salvar then
      FConfiguracoes.Geral.Save(FCTeChave+'-can.xml', FRetWS);

    if FConfiguracoes.Arquivos.Salvar then
      FConfiguracoes.Geral.Save(FCTeChave+'-can.xml', FRetWS, FConfiguracoes.Arquivos.GetPathCan );

    //gerar arquivo proc de cancelamento
    if CTeRetorno.cStat=101 then
    begin
//      wProc := TStringList.Create;

      // Alterado por Italo em 03/04/2013
      Texto := '<?xml version="1.0" encoding="UTF-8" ?>';
 {$IFDEF PL_103}
      Texto := Texto + '<procCancCTe versao="1.03" xmlns="http://www.portalfiscal.inf.br/cte">';
 {$ENDIF}
 {$IFDEF PL_104}
      Texto := Texto + '<procCancCTe versao="1.04" xmlns="http://www.portalfiscal.inf.br/cte">';
 {$ENDIF}
      Texto := Texto + FDadosMSG;
      Texto := Texto + FRetWS;
      Texto := Texto + '</procCancCTe>';

      (*
      wProc.Add('<?xml version="1.0" encoding="UTF-8" ?>');
 {$IFDEF PL_103}
      wProc.Add('<procCancCTe versao="1.03" xmlns="http://www.portalfiscal.inf.br/cte">');
 {$ENDIF}
 {$IFDEF PL_104}
      wProc.Add('<procCancCTe versao="1.04" xmlns="http://www.portalfiscal.inf.br/cte">');
 {$ENDIF}
      wProc.Add(FDadosMSG);
      wProc.Add(FRetWS);
      wProc.Add('</procCancCTe>');
      *)

      FXML_ProcCancCTe := Texto; //wProc.Text;
//      wProc.Free;
      if FConfiguracoes.Geral.Salvar then
         FConfiguracoes.Geral.Save(FCTeChave+'-ProcCancCTe.xml', FXML_ProcCancCTe);

      if FConfiguracoes.Arquivos.Salvar then
         FConfiguracoes.Geral.Save(FCTeChave+'-ProcCancCTe.xml', FXML_ProcCancCTe, FConfiguracoes.Arquivos.GetPathCan );
    end;

  finally
    {$IFDEF ACBrCTeOpenSSL}
       HTTP.Free;
    {$ELSE}
      ReqResp.Free;
    {$ENDIF}
    Acao.Free;
    Stream.Free;
    CTeRetorno.Free;
    DFeUtil.ConfAmbiente;
    TACBrCTe( FACBrCTe ).SetStatus( stCTeIdle );
  end;
end;

procedure TCTeCancelamento.SetJustificativa(AValue: WideString);
begin
  if DFeUtil.EstaVazio(AValue) then
   begin
     if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
        TACBrCTe( FACBrCTe ).OnGerarLog('ERRO: Informar uma Justificativa para cancelar o Conhecimento Eletr�nico');
     raise Exception.Create('Informar uma Justificativa para cancelar o Conhecimento Eletr�nico')
   end
  else
    AValue := DFeUtil.TrataString(AValue);

  if Length(AValue) < 15 then
   begin
     if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
        TACBrCTe( FACBrCTe ).OnGerarLog('ERRO: A Justificativa para Cancelamento do Conhecimento Eletr�nico deve ter no minimo 15 caracteres');
     raise Exception.Create('A Justificativa para Cancelamento do Conhecimento Eletr�nico deve ter no minimo 15 caracteres')
   end
  else
    FJustificativa := Trim(AValue);
end;

{ TCTeInutilizacao }

// Incluido por Italo em 03/01/2013 (Sujest�o de Nilton Olher)
procedure TCTeInutilizacao.Clear;
begin
  // Limpa Dados do retorno;
  FMsg      := '';
  FverAplic := '';
  FcStat    := 0;
  FxMotivo  := '';
end;

function TCTeInutilizacao.Executar: Boolean;
var
  CTeRetorno: TRetInutCTe;
  aMsg  : string;
  Texto : String;
  Acao  : TStringList;
  Stream: TMemoryStream;
  StrStream: TStringStream;
//  wProc  : TStringList;
  {$IFDEF ACBrCTeOpenSSL}
     HTTP: THTTPSend;
  {$ELSE}
     ReqResp: THTTPReqResp;
  {$ENDIF}
begin
  {Result :=} inherited Executar;

  Acao   := TStringList.Create;
  Stream := TMemoryStream.Create;
  FcStat := 0;

  Texto := '<?xml version="1.0" encoding="utf-8"?>';
  Texto := Texto + '<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">';
  Texto := Texto +   '<soap12:Header>';
  Texto := Texto +     '<cteCabecMsg xmlns="http://www.portalfiscal.inf.br/cte/wsdl/CteInutilizacao">';
  Texto := Texto +       '<cUF>'+IntToStr(FConfiguracoes.WebServices.UFCodigo)+'</cUF>';
  Texto := Texto +       '<versaoDados>'+CTeinutCTe+'</versaoDados>';
  Texto := Texto +     '</cteCabecMsg>';
  Texto := Texto +   '</soap12:Header>';
  Texto := Texto +   '<soap12:Body>';
  Texto := Texto +     '<cteDadosMsg xmlns="http://www.portalfiscal.inf.br/cte/wsdl/CteInutilizacao">';
  Texto := Texto + FDadosMsg;
  Texto := Texto +     '</cteDadosMsg>';
  Texto := Texto +   '</soap12:Body>';
  Texto := Texto + '</soap12:Envelope>';

  Acao.Text := Texto;

  {$IFDEF ACBrCTeOpenSSL}
     Acao.SaveToStream(Stream);
     HTTP := THTTPSend.Create;
  {$ELSE}
     ReqResp := THTTPReqResp.Create(nil);
     ConfiguraReqResp( ReqResp );
     ReqResp.URL := Trim(FURL);
     ReqResp.UseUTF8InHeader := True;
     ReqResp.SoapAction := 'http://www.portalfiscal.inf.br/cte/wsdl/CteInutilizacao/cteInutilizacaoCT';
  {$ENDIF}

  // Alterado por Italo em 10/02/2012
  CTeRetorno := TRetInutCTe.Create;

  try
    TACBrCTe( FACBrCTe ).SetStatus( stCTeInutilizacao );
    if FConfiguracoes.Geral.Salvar then
      FConfiguracoes.Geral.Save(FormatDateTime('yyyymmddhhnnss',Now)+FCTeChave+'-ped-inu.xml', FDadosMsg);

    if FConfiguracoes.Arquivos.Salvar then
      FConfiguracoes.Geral.Save(FormatDateTime('yyyymmddhhnnss',Now)+FCTeChave+'-ped-inu.xml', FDadosMsg, FConfiguracoes.Arquivos.GetPathInu);

    {$IFDEF ACBrCTeOpenSSL}
       HTTP.Document.LoadFromStream(Stream);
       ConfiguraHTTP(HTTP,'SOAPAction: "http://www.portalfiscal.inf.br/cte/wsdl/CteInutilizacao/cteInutilizacaoCT"');
       HTTP.HTTPMethod('POST', FURL);

       StrStream := TStringStream.Create('');
       StrStream.CopyFrom(HTTP.Document, 0);
       FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
       FRetWS := SeparaDados( FRetornoWS, 'cteInutilizacaoCTResult');
       StrStream.Free;
    {$ELSE}
       ReqResp.Execute(Acao.Text, Stream);
       StrStream := TStringStream.Create('');
       StrStream.CopyFrom(Stream, 0);
       FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
       FRetWS := SeparaDados( FRetornoWS, 'cteInutilizacaoCTResult');
       StrStream.Free;
    {$ENDIF}

    CTeRetorno.Leitor.Arquivo := FRetWS;
    CTeRetorno.LerXml;

    TACBrCTe( FACBrCTe ).SetStatus( stCTeIdle );
    aMsg := 'Ambiente : '+TpAmbToStr(CTeRetorno.TpAmb)+LineBreak+
            'Vers�o Aplicativo : '+CTeRetorno.verAplic+LineBreak+
            'Status C�digo : '+IntToStr(CTeRetorno.cStat)+LineBreak+
            'Status Descri��o : '+CTeRetorno.xMotivo+LineBreak+
            'UF : '+CodigoParaUF(CTeRetorno.cUF)+LineBreak+
            'Recebimento : '+DFeUtil.SeSenao(CTeRetorno.DhRecbto = 0, '', DateTimeToStr(CTeRetorno.dhRecbto));
    if FConfiguracoes.WebServices.Visualizar then
      ShowMessage(aMsg);

    if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
       TACBrCTe( FACBrCTe ).OnGerarLog(aMsg);

    FTpAmb     := CTeRetorno.TpAmb;
    FverAplic  := CTeRetorno.verAplic;
    FcStat     := CTeRetorno.cStat;
    FxMotivo   := CTeRetorno.xMotivo;
    FcUF       := CTeRetorno.cUF;
    FdhRecbto  := CTeRetorno.dhRecbto;
    Fprotocolo := CTeRetorno.nProt;
    FMsg       := CTeRetorno.XMotivo;

    Result := (CTeRetorno.cStat = 102); // 102 = Inutiliza��o
    // Alterado por Italo em 15/03/2011 
    // CTeRetorno.Free;

    if FConfiguracoes.Geral.Salvar then
      FConfiguracoes.Geral.Save(FormatDateTime('yyyymmddhhnnss',Now)+FCTeChave+'-inu.xml', FRetWS);

    if FConfiguracoes.Arquivos.Salvar then
      FConfiguracoes.Geral.Save(FormatDateTime('yyyymmddhhnnss',Now)+FCTeChave+'-inu.xml', FRetWS, FConfiguracoes.Arquivos.GetPathInu);

    //gerar arquivo proc de inutilizacao
    if CTeRetorno.cStat=102 then
    begin
//      wProc := TStringList.Create;

      // Alterado por Italo em 03/04/2013
      Texto := '<?xml version="1.0" encoding="UTF-8" ?>';
 {$IFDEF PL_103}
      Texto := Texto + '<ProcInutCTe versao="1.03" xmlns="http://www.portalfiscal.inf.br/cte">';
 {$ENDIF}
 {$IFDEF PL_104}
      Texto := Texto + '<ProcInutCTe versao="1.04" xmlns="http://www.portalfiscal.inf.br/cte">';
 {$ENDIF}
      Texto := Texto + FDadosMSG;
      Texto := Texto + FRetWS;
      Texto := Texto + '</ProcInutCTe>';

      (*
      wProc.Add('<?xml version="1.0" encoding="UTF-8" ?>');
 {$IFDEF PL_103}
      wProc.Add('<ProcInutCTe versao="1.03" xmlns="http://www.portalfiscal.inf.br/cte">');
 {$ENDIF}
 {$IFDEF PL_104}
      wProc.Add('<ProcInutCTe versao="1.04" xmlns="http://www.portalfiscal.inf.br/cte">');
 {$ENDIF}
      wProc.Add(FDadosMSG);
      wProc.Add(FRetWS);
      wProc.Add('</ProcInutCTe>');
      *)

      FXML_ProcInutCTe := Texto; //wProc.Text;

//      wProc.Free;

      if FConfiguracoes.Geral.Salvar then
         FConfiguracoes.Geral.Save(FormatDateTime('yyyymmddhhnnss',Now)+FCTeChave+'-ProcInutCTe.xml', FXML_ProcInutCTe);
      if FConfiguracoes.Arquivos.Salvar then
         FConfiguracoes.Geral.Save(FormatDateTime('yyyymmddhhnnss',Now)+FCTeChave+'-ProcInutCTe.xml', FXML_ProcInutCTe, FConfiguracoes.Arquivos.GetPathInu );
    end;

  finally
    {$IFDEF ACBrCTeOpenSSL}
       HTTP.Free;
    {$ELSE}
      ReqResp.Free;
    {$ENDIF}
    Acao.Free;
    Stream.Free;
    CTeRetorno.Free;
    DFeUtil.ConfAmbiente;
    TACBrCTe( FACBrCTe ).SetStatus( stCTeIdle );
  end;
end;

procedure TCTeInutilizacao.SetJustificativa(AValue: WideString);
begin
  if DFeUtil.EstaVazio(AValue) then
   begin
     if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
        TACBrCTe( FACBrCTe ).OnGerarLog('ERRO: Informar uma Justificativa para Inutiliza��o de numera��o do Conhecimento Eletr�nico');
     raise Exception.Create('Informar uma Justificativa para Inutiliza��o de numera��o do Conhecimento Eletr�nico')
   end
  else
    AValue := DFeUtil.TrataString(AValue);

  if Length(Trim(AValue)) < 15 then
   begin
     if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
        TACBrCTe( FACBrCTe ).OnGerarLog('ERRO: A Justificativa para Inutiliza��o de numera��o do Conhecimento Eletr�nico deve ter no minimo 15 caracteres');
     raise Exception.Create('A Justificativa para Inutiliza��o de numera��o do Conhecimento Eletr�nico deve ter no minimo 15 caracteres')
   end
  else
    FJustificativa := Trim(AValue);
end;

{ TCTeConsultaCadastro }

// Incluido por Italo em 03/01/2013 (Sujest�o de Nilton Olher)
procedure TCTeConsultaCadastro.Clear;
begin
  // Limpa Dados do retorno;
  FMsg      := '';
  FverAplic := '';
  FcStat    := 0;
  FxMotivo  := '';
end;

destructor TCTeConsultaCadastro.destroy;
begin
  FRetConsCad.Free;

  inherited;
end;

function TCTeConsultaCadastro.Executar: Boolean;
var
  aMsg  : String;
  Texto : String;
  Acao  : TStringList;
  Stream: TMemoryStream;
  StrStream: TStringStream;
  {$IFDEF ACBrCTeOpenSSL}
     HTTP: THTTPSend;
  {$ELSE}
     ReqResp: THTTPReqResp;
  {$ENDIF}
begin
  // Alterado por Italo em  25/01/2012
  if assigned(FRetConsCad) then
     FreeAndNil(FRetConsCad);

  inherited Executar;

  Acao := TStringList.Create;
  Stream := TMemoryStream.Create;
  FcStat := 0;

  Texto := '<?xml version="1.0" encoding="utf-8"?>';
  Texto := Texto + '<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">';
  Texto := Texto +   '<soap12:Header>';
  Texto := Texto +     '<nfeCabecMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/CadConsultaCadastro2">';
  Texto := Texto +       '<cUF>'+IntToStr(UFparaCodigo(TCTeConsultaCadastro(Self).UF))+'</cUF>';
  Texto := Texto +       '<versaoDados>'+CTeconsCad+'</versaoDados>';
  Texto := Texto +     '</nfeCabecMsg>';
  Texto := Texto +   '</soap12:Header>';
  Texto := Texto +   '<soap12:Body>';
  Texto := Texto +     '<nfeDadosMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/CadConsultaCadastro2">';
  Texto := Texto +       FDadosMsg;
  Texto := Texto +     '</nfeDadosMsg>';
  Texto := Texto +   '</soap12:Body>';
  Texto := Texto +'</soap12:Envelope>';

  Acao.Text := Texto;

  {$IFDEF ACBrCTeOpenSSL}
     Acao.SaveToStream(Stream);
     HTTP := THTTPSend.Create;
  {$ELSE}
     ReqResp := THTTPReqResp.Create(nil);
     ConfiguraReqResp( ReqResp );
     ReqResp.URL := Trim(FURL);
     ReqResp.UseUTF8InHeader := True;
     ReqResp.SoapAction := 'http://www.portalfiscal.inf.br/nfe/wsdl/CadConsultaCadastro2';
  {$ENDIF}
  try
    TACBrCTe( FACBrCTe ).SetStatus( stCTeCadastro );

    if FConfiguracoes.Geral.Salvar then
      FConfiguracoes.Geral.Save(FormatDateTime('yyyymmddhhnnss',Now)+'-ped-cad.xml', FDadosMsg);
    FRetWS := '';
    {$IFDEF ACBrCTeOpenSSL}
       HTTP.Document.LoadFromStream(Stream);
       ConfiguraHTTP(HTTP,'SOAPAction: "http://www.portalfiscal.inf.br/nfe/wsdl/CadConsultaCadastro2"');
       HTTP.HTTPMethod('POST', FURL);

       StrStream := TStringStream.Create('');
       StrStream.CopyFrom(HTTP.Document, 0);
       FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
       FRetWS := SeparaDados( FRetornoWS,'consultaCadastro2Result');
       StrStream.Free;
    {$ELSE}
       ReqResp.Execute(Acao.Text, Stream);
       StrStream := TStringStream.Create('');
       StrStream.CopyFrom(Stream, 0);
       FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
       FRetWS := SeparaDados( FRetornoWS,'consultaCadastro2Result');
       StrStream.Free;
    {$ENDIF}

    FRetConsCad := TRetConsCad.Create;
    FRetConsCad.Leitor.Arquivo := FRetWS;
    FRetConsCad.LerXml;

    TACBrCTe( FACBrCTe ).SetStatus( stCTeIdle );
    aMsg := 'Vers�o Aplicativo : '+FRetConsCad.verAplic+LineBreak+
            'Status C�digo : '+IntToStr(FRetConsCad.cStat)+LineBreak+
            'Status Descri��o : '+FRetConsCad.xMotivo+LineBreak+
            'UF : '+CodigoParaUF(FRetConsCad.cUF)+LineBreak+
            'Consulta : '+DateTimeToStr(FRetConsCad.dhCons);
    if FConfiguracoes.WebServices.Visualizar then
      ShowMessage(aMsg);

    if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
       TACBrCTe( FACBrCTe ).OnGerarLog(aMsg);

    FverAplic := FRetConsCad.verAplic;
    FcStat    := FRetConsCad.cStat;
    FxMotivo  := FRetConsCad.xMotivo;
    FdhCons   := FRetConsCad.dhCons;
    FcUF      := FRetConsCad.cUF;
    FMsg      := FRetConsCad.XMotivo;

    if FRetConsCad.cStat = 111 then
     begin
       FUF   := FRetConsCad.UF;
       FIE   := FRetConsCad.IE;
       FCNPJ := FRetConsCad.CNPJ;
       FCPF  := FRetConsCad.CPF;
     end;

   Result := (FRetConsCad.cStat in [111, 112]);

    if FConfiguracoes.Geral.Salvar then
      FConfiguracoes.Geral.Save(FormatDateTime('yyyymmddhhnnss',Now)+'-cad.xml', FRetWS);
  finally
    {$IFDEF ACBrCTeOpenSSL}
       HTTP.Free;
    {$ELSE}
      ReqResp.Free;
    {$ENDIF}
    Acao.Free;
    Stream.Free;
    DFeUtil.ConfAmbiente;
    TACBrCTe( FACBrCTe ).SetStatus( stCTeIdle );
  end;
end;

procedure TCTeConsultaCadastro.SetCNPJ(const Value: String);
begin
  if DFeUtil.NaoEstaVazio(Value) then
   begin
     FIE  := '';
     FCPF := '';
   end;
  FCNPJ := Value;
end;

procedure TCTeConsultaCadastro.SetCPF(const Value: String);
begin
  if DFeUtil.NaoEstaVazio(Value) then
   begin
     FIE   := '';
     FCNPJ := '';
   end;
  FCPF := Value;
end;

procedure TCTeConsultaCadastro.SetIE(const Value: String);
begin
  if DFeUtil.NaoEstaVazio(Value) then
   begin
     FCNPJ := '';
     FCPF  := '';
   end;
  FIE := Value;
end;

{ TCTeEnvEvento }

// Incluido por Italo em 03/01/2013 (Sujest�o de Nilton Olher)
procedure TCTeEnvEvento.Clear;
begin
  // Limpa Dados do retorno;
  FMsg      := '';
  FcStat    := 0;
  FxMotivo  := '';
end;

constructor TCTeEnvEvento.Create(AOwner: TComponent; AEvento: TEventoCTe);
begin
  inherited Create(AOwner);

  FEvento := AEvento;
end;

destructor TCTeEnvEvento.Destroy;
begin
  if Assigned(FEventoRetorno) then
     FEventoRetorno.Free;
  inherited;
end;

function TCTeEnvEvento.Executar: Boolean;
var
  aMsg, NomeArq: string;
  Texto : String;
  Acao  : TStringList;
  Stream: TMemoryStream;
  StrStream: TStringStream;
//  wProc  : TStringList;
  i,j : integer;
  Leitor : TLeitor;
  {$IFDEF ACBrCTeOpenSSL}
     HTTP: THTTPSend;
  {$ELSE}
     ReqResp: THTTPReqResp;
  {$ENDIF}
begin
  FEvento.idLote := idLote;
  if Assigned(FEventoRetorno) then
     FEventoRetorno.Free;

  inherited Executar;

  Acao   := TStringList.Create;
  Stream := TMemoryStream.Create;
  FcStat := 0;

  Texto := '<?xml version="1.0" encoding="utf-8"?>';
  Texto := Texto + '<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">';
  Texto := Texto +   '<soap12:Header>';
  Texto := Texto +     '<cteCabecMsg xmlns="http://www.portalfiscal.inf.br/cte/wsdl/CTeRecepcaoEvento">';
  Texto := Texto +       '<cUF>'+IntToStr(FConfiguracoes.WebServices.UFCodigo)+'</cUF>';
  Texto := Texto +       '<versaoDados>'+CTeEventoCTe+'</versaoDados>';
  Texto := Texto +     '</cteCabecMsg>';
  Texto := Texto +   '</soap12:Header>';
  Texto := Texto +   '<soap12:Body>';
  Texto := Texto +     '<cteDadosMsg xmlns="http://www.portalfiscal.inf.br/cte/wsdl/CTeRecepcaoEvento">';
  Texto := Texto +       FDadosMsg;
  Texto := Texto +     '</cteDadosMsg>';
  Texto := Texto +   '</soap12:Body>';
  Texto := Texto +'</soap12:Envelope>';

  Acao.Text := Texto;

   {$IFDEF ACBrCTeOpenSSL}
     Acao.SaveToStream(Stream);
     HTTP := THTTPSend.Create;
  {$ELSE}
     ReqResp := THTTPReqResp.Create(nil);
     ConfiguraReqResp( ReqResp );
     ReqResp.URL := FURL;
     ReqResp.UseUTF8InHeader := True;
     ReqResp.SoapAction := 'http://www.portalfiscal.inf.br/cte/wsdl/CTeRecepcaoEvento/cteRecepcaoEvento';
  {$ENDIF}

  try
    TACBrCTe( FACBrCTe ).SetStatus( stCTeEvento );

    if FConfiguracoes.Geral.Salvar then
      FConfiguracoes.Geral.Save(IntToStr(FEvento.idLote)+ '-ped-evento.xml', FDadosMsg);

    if FConfiguracoes.Arquivos.Salvar then
      FConfiguracoes.Geral.Save(IntToStr(FEvento.idLote)+ '-ped-evento.xml', FDadosMsg, FConfiguracoes.Arquivos.GetPathCTe);

    {$IFDEF ACBrCTeOpenSSL}
       HTTP.Document.LoadFromStream(Stream);
       ConfiguraHTTP(HTTP,'SOAPAction: "http://www.portalfiscal.inf.br/cte/wsdl/CTeRecepcaoEvento/cteRecepcaoEvento"');
       HTTP.HTTPMethod('POST', FURL);

       StrStream := TStringStream.Create('');
       StrStream.CopyFrom(HTTP.Document, 0);
       FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
       FRetWS := SeparaDados( FRetornoWS,'cteRecepcaoEventoResult');
       StrStream.Free;
    {$ELSE}
       ReqResp.Execute(Acao.Text, Stream);
       StrStream := TStringStream.Create('');
       StrStream.CopyFrom(Stream, 0);
       FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
       FRetWS := SeparaDados( FRetornoWS,'cteRecepcaoEventoResult');
       StrStream.Free;
    {$ENDIF}

    if FConfiguracoes.Geral.Salvar then
      FConfiguracoes.Geral.Save(IntToStr(FEvento.idLote) + '-eve.xml', FRetWS);

    if FConfiguracoes.Arquivos.Salvar then
      FConfiguracoes.Geral.Save(IntToStr(FEvento.idLote) + '-eve.xml', FRetWS, FConfiguracoes.Arquivos.GetPathCTe);

    FEventoRetorno                := TRetEventoCTe.Create;
    FEventoRetorno.Leitor.Arquivo := FRetWS;
    FEventoRetorno.LerXml;

    TACBrCTe( FACBrCTe ).SetStatus( stCTeIdle );
    aMsg := 'Ambiente : '+TpAmbToStr(EventoRetorno.retEvento.Items[0].RetInfEvento.tpAmb)+LineBreak+
            'Vers�o Aplicativo : '+EventoRetorno.retEvento.Items[0].RetInfEvento.verAplic+LineBreak+
            'Status C�digo : '+IntToStr(EventoRetorno.retEvento.Items[0].RetInfEvento.cStat)+LineBreak+
            'Status Descri��o : '+EventoRetorno.retEvento.Items[0].RetInfEvento.xMotivo+LineBreak+
            'Recebimento : '+DFeUtil.SeSenao(EventoRetorno.retEvento.Items[0].RetInfEvento.dhRegEvento = 0, '',
                                              DateTimeToStr(EventoRetorno.retEvento.Items[0].RetInfEvento.dhRegEvento));

    if FConfiguracoes.WebServices.Visualizar then
      ShowMessage(aMsg);

    if Assigned(TACBrCTe( FACBrCTe ).OnGerarLog) then
       TACBrCTe( FACBrCTe ).OnGerarLog(aMsg);

    FcStat   := EventoRetorno.retEvento.Items[0].RetInfEvento.cStat;
    FxMotivo := EventoRetorno.retEvento.Items[0].RetInfEvento.xMotivo;
    FMsg     := EventoRetorno.retEvento.Items[0].RetInfEvento.xMotivo;
    FTpAmb   := EventoRetorno.retEvento.Items[0].RetInfEvento.tpAmb;

    Result   := (FcStat = 128) or (FcStat = 134) or (FcStat = 135) or (FcStat = 136);

    if FConfiguracoes.Geral.Salvar then
      FConfiguracoes.Geral.Save(IntToStr(FEvento.idLote) + '-eve.xml', FRetWS);

    if FConfiguracoes.Arquivos.Salvar then
      FConfiguracoes.Geral.Save(IntToStr(FEvento.idLote) + '-eve.xml', FRetWS, FConfiguracoes.Arquivos.GetPathCTe);

    //gerar arquivo proc de evento
    if Result then
    begin
      Leitor := TLeitor.Create;
      for i := 0 to FEvento.Evento.Count-1 do
       begin
        for j := 0 to EventoRetorno.retEvento.Count-1 do
         begin
           if FEvento.Evento.Items[i].InfEvento.chCTe = EventoRetorno.retEvento.Items[j].RetInfEvento.chCTe then
            begin
              FEvento.Evento.Items[i].RetInfEvento.nProt       := EventoRetorno.retEvento.Items[j].RetInfEvento.nProt;
              FEvento.Evento.Items[i].RetInfEvento.dhRegEvento := EventoRetorno.retEvento.Items[j].RetInfEvento.dhRegEvento;
              FEvento.Evento.Items[i].RetInfEvento.cStat       := EventoRetorno.retEvento.Items[j].RetInfEvento.cStat;
              FEvento.Evento.Items[i].RetInfEvento.chCTe       := EventoRetorno.retEvento.Items[j].RetInfEvento.chCTe;

//              wProc := TStringList.Create;

              // Alterado por Italo em 03/04/2013
              Texto := '<?xml version="1.0" encoding="UTF-8" ?>';
              Texto := Texto + '<procEventoCTe versao="' + CTeEventoCTe + '" xmlns="http://www.portalfiscal.inf.br/cte">';
              Texto := Texto + '<eventoCTe versao="' + CTeEventoCTe + '">';
              Leitor.Arquivo := FDadosMSG;
              Texto := Texto + UTF8Encode(Leitor.rExtrai(1, 'infEvento', '', i + 1));
              Texto := Texto + '</infEvento>';
              Texto := Texto + '<Signature xmlns="http://www.w3.org/2000/09/xmldsig#">';
              Leitor.Arquivo := FDadosMSG;
              Texto := Texto + UTF8Encode(Leitor.rExtrai(1, 'SignedInfo', '', i + 1));
              Texto := Texto + '</SignedInfo>';
              Leitor.Arquivo := FDadosMSG;
              Texto := Texto + UTF8Encode(Leitor.rExtrai(1, 'SignatureValue', '', i + 1));
              Texto := Texto + '</SignatureValue>';
              Leitor.Arquivo := FDadosMSG;
              Texto := Texto + UTF8Encode(Leitor.rExtrai(1, 'KeyInfo', '', i + 1));
              Texto := Texto + '</KeyInfo>';
              Texto := Texto + '</Signature>';
              Texto := Texto + '</eventoCTe>';
              Texto := Texto + '<retEventoCTe versao="' + CTeEventoCTe + '">';
              Leitor.Arquivo := FRetWS;
              Texto := Texto + UTF8Encode(Leitor.rExtrai(1, 'infEvento', '', j + 1));
              Texto := Texto + '</infEvento>';
              Texto := Texto + '</retEventoCTe>';
              Texto := Texto + '</procEventoCTe>';

              (*
              wProc.Add('<?xml version="1.0" encoding="UTF-8" ?>');
              wProc.Add('<procEventoCTe versao="' + CTeEventoCTe + '" xmlns="http://www.portalfiscal.inf.br/cte">');

              wProc.Add('<eventoCTe versao="' + CTeEventoCTe + '">');
              Leitor.Arquivo := FDadosMSG;
              wProc.Add(UTF8Encode(Leitor.rExtrai(1, 'infEvento', '', i + 1)));
              wProc.Add('</infEvento>');

              wProc.Add('<Signature xmlns="http://www.w3.org/2000/09/xmldsig#">');
              Leitor.Arquivo := FDadosMSG;
              wProc.Add(UTF8Encode(Leitor.rExtrai(1, 'SignedInfo', '', i + 1)));
              wProc.Add('</SignedInfo>');
              Leitor.Arquivo := FDadosMSG;
              wProc.Add(UTF8Encode(Leitor.rExtrai(1, 'SignatureValue', '', i + 1)));
              wProc.Add('</SignatureValue>');
              Leitor.Arquivo := FDadosMSG;
              wProc.Add(UTF8Encode(Leitor.rExtrai(1, 'KeyInfo', '', i + 1)));
              wProc.Add('</KeyInfo>');
              wProc.Add('</Signature>');

              wProc.Add('</eventoCTe>');

              wProc.Add('<retEventoCTe versao="' + CTeEventoCTe + '">');
              Leitor.Arquivo := FRetWS;
              wProc.Add(UTF8Encode(Leitor.rExtrai(1, 'infEvento', '', j + 1)));
              wProc.Add('</infEvento>');
              wProc.Add('</retEventoCTe>');

              wProc.Add('</procEventoCTe>');
              *)
              EventoRetorno.retEvento.Items[j].RetInfEvento.XML := Texto; //wProc.Text;

              FEvento.Evento.Items[i].RetInfEvento.XML := Texto; //wProc.Text;

              NomeArq := FEvento.Evento.Items[i].InfEvento.chCTe +
                         FEvento.Evento.Items[i].InfEvento.TipoEvento +
                         IntToStr(FEvento.Evento.Items[i].InfEvento.nSeqEvento) +
                         '-procEventoCTe.xml';

              if FConfiguracoes.Geral.Salvar then
                 FConfiguracoes.Geral.Save(NomeArq, Texto {wProc.Text});

              if FConfiguracoes.Arquivos.Salvar then
                 FConfiguracoes.Geral.Save(NomeArq, Texto {wProc.Text}, FConfiguracoes.Arquivos.GetPathCTe);

//              wProc.Free;
              break;
            end;
         end;
       end;
      Leitor.Free;
    end;
  finally
    {$IFDEF ACBrCTeOpenSSL}
       HTTP.Free;
    {$ELSE}
       ReqResp.Free;
    {$ENDIF}
    Acao.Free;
    Stream.Free;
    DFeUtil.ConfAmbiente;
    TACBrCTe( FACBrCTe ).SetStatus( stCTeIdle );
  end;
end;

end.

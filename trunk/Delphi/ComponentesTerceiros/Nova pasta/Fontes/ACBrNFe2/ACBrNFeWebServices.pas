{******************************************************************************}
{ Projeto: Componente ACBrNFe                                                  }
{  Biblioteca multiplataforma de componentes Delphi para emiss�o de Nota Fiscal}
{ eletr�nica - NFe - http://www.nfe.fazenda.gov.br                             }
{                                                                              }
{ Direitos Autorais Reservados (c) 2008 Wemerson Souto                         }
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
{******************************************************************************}

{******************************************************************************
|* Historico
|*
|* 16/12/2008: Wemerson Souto
|*  - Doa��o do componente para o Projeto ACBr
|* 09/04/2012: Italo
|*  - Inclu�do envio de evento
|* 17/07/2012: Italo
|*  - Inclu�do Consulta de NFe pelo Destinat�rio
|* 18/07/2012: Italo
|*  - Inclu�do Download da NFe
|* 28/09/2012: Italo
|*  - Suporte a NFe 3.0 - NFC-e
******************************************************************************}
{$I ACBr.inc}

unit ACBrNFeWebServices;

interface

uses Classes, SysUtils,
  {$IFDEF CLX} QDialogs,{$ELSE} Dialogs,{$ENDIF}
  {$IFDEF ACBrNFeOpenSSL}
    HTTPSend,
  {$ELSE}
     SoapHTTPClient, SOAPHTTPTrans, SOAPConst, JwaWinCrypt, WinInet, ACBrCAPICOM_TLB,
  {$ENDIF}
  pcnNFe, pcnNFeW,
  pcnRetConsReciNFe, pcnRetConsCad, pcnAuxiliar, pcnConversao, pcnRetDPEC,
  pcnProcNFe, pcnRetCancNFe, pcnCCeNFe, pcnRetCCeNFe,
  pcnEnvEventoNFe, pcnRetEnvEventoNFe, pcnRetConsSitNFe,
  pcnConsNFeDest, pcnRetConsNFeDest,
  pcnDownloadNFe, pcnRetDownloadNFe,
  ACBrNFeNotasFiscais,
  ACBrNFeConfiguracoes;

type

  TWebServicesBase = Class
  private
    procedure DoNFeStatusServico;
    procedure DoNFeRecepcao;
    procedure DoNFeRetRecepcao;
    procedure DoNFeRecibo;
    procedure DoNFeConsulta;
    procedure DoNFeCancelamento;
    procedure DoNFeInutilizacao;
    procedure DoNFeConsultaCadastro;
    procedure DoNFeEnvDPEC;
    procedure DoNFeConsultaDPEC;
    procedure DoNFeCartaCorrecao;
    procedure DoNFeEnvEvento;
    procedure DoNFeConsNFeDest;
    procedure DoNFeDownloadNFe; 
    {$IFDEF ACBrNFeOpenSSL}
       procedure ConfiguraHTTP( HTTP : THTTPSend; Action : AnsiString);
    {$ELSE}
       procedure ConfiguraReqResp( ReqResp : THTTPReqResp);
       procedure OnBeforePost(const HTTPReqResp: THTTPReqResp; Data:Pointer);
    {$ENDIF}
  protected
    FCabMsg: WideString;
    FDadosMsg: AnsiString;
    FRetornoWS: AnsiString;
    FRetWS: AnsiString;
    FMsg: AnsiString;
    FURL: WideString;
    FConfiguracoes: TConfiguracoes;
    FACBrNFe : TComponent;
    FPathArqEnv: AnsiString;
    FPathArqResp: AnsiString;
    procedure LoadMsgEntrada;
    procedure LoadURL;
  public
    function Executar: Boolean;virtual;
    constructor Create(AOwner : TComponent); virtual;
    property CabMsg: WideString read FCabMsg;
    property DadosMsg: AnsiString read FDadosMsg;
    property RetornoWS: AnsiString read FRetornoWS;
    property RetWS: AnsiString read FRetWS;
    property Msg: AnsiString read FMsg;
    property PathArqEnv: AnsiString read FPathArqEnv;
    property PathArqResp: AnsiString read FPathArqResp;
  end;

  TNFeStatusServico = Class(TWebServicesBase)
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

  TNFeRecepcao = Class(TWebServicesBase)
  private
    FLote: String;
    FRecibo : String;
    FNotasFiscais : TNotasFiscais;
    FTpAmb: TpcnTipoAmbiente;
    FverAplic: String;
    FcStat: Integer;
    FcUF: Integer;
    FxMotivo: String;
    FdhRecbto: TDateTime;
    FTMed: Integer;
    FSincrono: Boolean;
    function GetLote: String;
  public
    function Executar: Boolean; override;
    constructor Create(AOwner : TComponent; ANotasFiscais : TNotasFiscais);reintroduce;
    property Recibo: String read FRecibo;
    property TpAmb: TpcnTipoAmbiente read FTpAmb;
    property verAplic: String read FverAplic;
    property cStat: Integer read FcStat;
    property cUF: Integer read FcUF;
    property xMotivo: String read FxMotivo;
    property dhRecbto: TDateTime read FdhRecbto;
    property TMed: Integer read FTMed;
    property Lote: String read GetLote write FLote;
    property Sincrono: Boolean read FSincrono;
  end;

  TNFeRetRecepcao = Class(TWebServicesBase)
  private
    FRecibo: String;
    FProtocolo: String;
    FChaveNFe: String;
    FNotasFiscais: TNotasFiscais;
    FNFeRetorno: TRetConsReciNFe;
    FTpAmb: TpcnTipoAmbiente;
    FverAplic: String;
    FcStat: Integer;
    FcUF: Integer;
    FxMotivo: String;
    FcMsg: Integer;
    FxMsg: String;
    function Confirma(AInfProt: TProtNFeCollection): Boolean;
  public
    function Executar: Boolean; override;
    constructor Create(AOwner : TComponent; ANotasFiscais : TNotasFiscais);reintroduce;
    destructor Destroy; override;
    property TpAmb: TpcnTipoAmbiente read FTpAmb;
    property verAplic: String read FverAplic;
    property cStat: Integer read FcStat;
    property cUF: Integer read FcUF;
    property xMotivo: String read FxMotivo;
    property cMsg: Integer read FcMsg;
    property xMsg: String read FxMsg;
    property Recibo: String read FRecibo write FRecibo;
    property Protocolo: String read FProtocolo write FProtocolo;
    property ChaveNFe: String read FChaveNFe write FChaveNFe;
    property NFeRetorno: TRetConsReciNFe read FNFeRetorno write FNFeRetorno;
  end;

  TNFeRecibo = Class(TWebServicesBase)
  private
    FRecibo: String;
    FNFeRetorno: TRetConsReciNFe;
    FTpAmb: TpcnTipoAmbiente;
    FverAplic: String;
    FcStat: Integer;
    FxMotivo: String;
    FcUF: Integer;
    FxMsg: String;
    FcMsg: Integer;
  public
    function Executar: Boolean; override;
    constructor Create(AOwner : TComponent);reintroduce;
    destructor Destroy; override;
    property TpAmb: TpcnTipoAmbiente read FTpAmb;
    property verAplic: String read FverAplic;
    property cStat: Integer read FcStat;
    property xMotivo: String read FxMotivo;
    property cUF: Integer read FcUF;
    property xMsg: String read FxMsg;
    property cMsg: Integer read FcMsg;
    property Recibo: String read FRecibo write FRecibo;
    property NFeRetorno: TRetConsReciNFe read FNFeRetorno write FNFeRetorno;
  end;

  TNFeConsulta = Class(TWebServicesBase)
  private
    FNFeChave: WideString;
    FProtocolo: WideString;
    FDhRecbto: TDateTime;
    FXMotivo: WideString;
    FTpAmb : TpcnTipoAmbiente;
    FverAplic : String;
    FcStat : Integer;
    FcUF : Integer;
    FprotNFe: TProcNFe;
    FretCancNFe: TRetCancNFe;
    FprocEventoNFe: TRetEventoNFeCollection; {eventos_juaumkiko}
  public
    constructor Create(AOwner : TComponent); reintroduce;
    destructor Destroy; override;

    function Executar: Boolean;override;
    property NFeChave: WideString read FNFeChave write FNFeChave;
    property Protocolo: WideString read FProtocolo write FProtocolo;
    property DhRecbto: TDateTime read FDhRecbto write FDhRecbto;
    property XMotivo: WideString read FXMotivo write FXMotivo;
    property TpAmb: TpcnTipoAmbiente read FTpAmb;
    property verAplic: String read FverAplic;
    property cStat: Integer read FcStat;
    property cUF: Integer read FcUF;
    property protNFe: TProcNFe read FprotNFe write FprotNFe;
    property retCancNFe: TRetCancNFe read FretCancNFe write FretCancNFe;
    property procEventoNFe: TRetEventoNFeCollection read FprocEventoNFe write FprocEventoNFe;
  end;

  TNFeCancelamento = Class(TWebServicesBase)
  private
    FNFeChave: WideString;
    FProtocolo: WideString;
    FJustificativa: WideString;
    FTpAmb: TpcnTipoAmbiente;
    FverAplic: String;
    FcStat: Integer;
    FxMotivo: String;
    FcUF: Integer;
    FDhRecbto: TDateTime;
    FXML_ProcCancNFe: AnsiString;
    procedure SetJustificativa(AValue: WideString);
  public
    function Executar: Boolean;override;
    property TpAmb: TpcnTipoAmbiente read FTpAmb;
    property verAplic: String read FverAplic;
    property cStat: Integer read FcStat;
    property xMotivo: String read FxMotivo;
    property cUF: Integer read FcUF;
    property DhRecbto: TDateTime read FDhRecbto;
    property NFeChave: WideString read FNFeChave write FNFeChave;
    property Protocolo: WideString read FProtocolo write FProtocolo;
    property Justificativa: WideString read FJustificativa write SetJustificativa;
    property XML_ProcCancNFe: AnsiString read FXML_ProcCancNFe write FXML_ProcCancNFe;
  end;

  TNFeInutilizacao = Class(TWebServicesBase)
  private
    FID: WideString;
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
    FXML_ProcInutNFe: AnsiString;
    procedure SetJustificativa(AValue: WideString);
  public
    function Executar: Boolean;override;
    property ID: WideString read FID write FID;
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
    property XML_ProcInutNFe: AnsiString read FXML_ProcInutNFe write FXML_ProcInutNFe;
  end;

  TNFeConsultaCadastro = Class(TWebServicesBase)
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
    function Executar: Boolean;override;
    destructor Destroy; override;
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

  TNFeEnvDPEC = Class(TWebServicesBase)
  private
    FId: String;
    FverAplic: String;
    FcStat: Integer;
    FTpAmb: TpcnTipoAmbiente;
    FxMotivo: String;
    FdhRegDPEC: TDateTime;
    FnRegDPEC: String;
    FNFeChave: String;
    FXML_ProcDPEC: AnsiString;
  public
    function Executar: Boolean;override;
    property ID: String read FId;
    property verAplic: String read FverAplic;
    property cStat: Integer read FcStat;
    property TpAmb: TpcnTipoAmbiente read FTpAmb;
    property xMotivo: String read FxMotivo;
    property DhRegDPEC: TDateTime read FdhRegDPEC;
    property nRegDPEC: String read FnRegDPEC;
    property NFeChave: String read FNFeChave;
    property XML_ProcDPEC: AnsiString read FXML_ProcDpec write FXML_ProcDpec;
  end;

  TNFeConsultaDPEC = Class(TWebServicesBase)
  private
    FverAplic: String;
    FcStat: Integer;
    FTpAmb: TpcnTipoAmbiente;
    FxMotivo: String;
    //FretDPEC: TRetDPEC;
    FnRegDPEC: String;
    FNFeChave: String;
    FdhRegDPEC: TDateTime;
    procedure SetNFeChave(const Value: String);
    procedure SetnRegDPEC(const Value: String);
  public
    function Executar: Boolean;override;
    property verAplic: String read FverAplic;
    property cStat: Integer read FcStat;
    property TpAmb: TpcnTipoAmbiente read FTpAmb;
    property xMotivo: String read FxMotivo;
    property dhRegDPEC: TDateTime read FdhRegDPEC;
    //property retDPEC: TRetDPEC read FretDPEC;

    property nRegDPEC: String read FnRegDPEC write SetnRegDPEC;
    property NFeChave: String read FNFeChave write SetNFeChave;
  end;

  {Carta de Corre��o}
  TNFeCartaCorrecao = Class(TWebServicesBase)
  private
    FidLote: Integer;
    Fversao: String;
    FCCe   : TCCeNFe;
    FcStat: Integer;
    FxMotivo: String;
    FTpAmb: TpcnTipoAmbiente;
    FCCeRetorno: TRetCCeNFe;
  public
    constructor Create(AOwner : TComponent; ACCe : TCCeNFe);reintroduce;
    destructor Destroy; override;
    function Executar: Boolean; override;

    property idLote: Integer               read FidLote      write FidLote;
    property versao: String                read Fversao      write Fversao;
    property cStat: Integer                read FcStat;
    property xMotivo: String               read FxMotivo;
    property TpAmb: TpcnTipoAmbiente       read FTpAmb;
    property CCeRetorno: TRetCCeNFe        read FCCeRetorno;
  end;

  {Enviar Evento}
  TNFeEnvEvento = Class(TWebServicesBase)
  private
    FidLote: Integer;
    Fversao: String;
    FEvento: TEventoNFe;
    FcStat: Integer;
    FxMotivo: String;
    FTpAmb: TpcnTipoAmbiente;
    FEventoRetorno: TRetEventoNFe;
  public
    constructor Create(AOwner : TComponent; AEvento : TEventoNFe);reintroduce;
    destructor Destroy; override;
    function Executar: Boolean; override;

    property idLote: Integer               read FidLote      write FidLote;
    property versao: String                read Fversao      write Fversao;
    property cStat: Integer                read FcStat;
    property xMotivo: String               read FxMotivo;
    property TpAmb: TpcnTipoAmbiente       read FTpAmb;
    property EventoRetorno: TRetEventoNFe  read FEventoRetorno;
  end;

  TNFeConsNFeDest = Class(TWebServicesBase)
  private
    FtpAmb: TpcnTipoAmbiente;
    FCNPJ: String;
    FindEmi: TpcnIndicadorEmissor;
    FindNFe: TpcnIndicadorNFe;
    FultNSU: String;
    FretConsNFeDest: TretConsNFeDest;
  public
    constructor Create(AOwner : TComponent);reintroduce;
    destructor Destroy; override;
    function Executar: Boolean; override;

    property tpAmb: TpcnTipoAmbiente         read FtpAmb;
    property CNPJ: String                    read FCNPJ           write FCNPJ;
    property indNFe: TpcnIndicadorNFe        read FindNFe         write FindNFe;
    property indEmi: TpcnIndicadorEmissor    read FindEmi         write FindEmi;
    property ultNSU: String                  read FultNSU         write FultNSU;
    property retConsNFeDest: TretConsNFeDest read FretConsNFeDest write FretConsNFeDest;
  end;

  TNFeDownloadNFe = Class(TWebServicesBase)
  private
    FtpAmb: TpcnTipoAmbiente;
    FCNPJ: String;
    FDownload: TDownLoadNFe;
    FretDownloadNFe: TretDownloadNFe;
  public
    constructor Create(AOwner : TComponent; ADownload : TDownloadNFe); reintroduce;
    destructor Destroy; override;
    function Executar: Boolean; override;

    property tpAmb: TpcnTipoAmbiente        read FtpAmb;
    property CNPJ: String                   read FCNPJ            write FCNPJ;
    property retDownloadNFe: TretDownloadNFe read FretDownloadNFe write FretDownloadNFe;
  end;

  TNFeEnvioWebService = Class(TWebServicesBase)
  private
    FXMLEnvio: String;
    FURLEnvio: String;
    FSoapActionEnvio: String;
  public
    function Executar: Boolean; override;
    property XMLEnvio: String read FXMLEnvio write FXMLEnvio;
    property URLEnvio: String read FURLEnvio write FURLEnvio;
    property SoapActionEnvio: String read FSoapActionEnvio write FSoapActionEnvio;
  end;

  TWebServices = Class(TWebServicesBase)
  private
    FACBrNFe : TComponent;
    FStatusServico: TNFeStatusServico;
    FEnviar: TNFeRecepcao;
    FRetorno: TNFeRetRecepcao;
    FRecibo: TNFeRecibo;
    FConsulta: TNFeConsulta;
    FCancelamento: TNFeCancelamento;
    FInutilizacao: TNFeInutilizacao;
    FConsultaCadastro: TNFeConsultaCadastro;
    FEnviaDPEC: TNFeEnvDPEC;
    FConsultaDPEC: TNFeConsultaDPEC;
    FCartaCorrecao: TNFeCartaCorrecao;
    FEnvEvento: TNFeEnvEvento;
    FConsNFeDest: TNFeConsNFeDest;
    FDownloadNFe: TNFeDownloadNFe;
    FEnvioWebService: TNFeEnvioWebService;
  public
    constructor Create(AFNotaFiscalEletronica: TComponent);reintroduce;
    destructor Destroy; override;
    function Envia(ALote: Integer; const ASincrono: Boolean = False): Boolean; overload;
    function Envia(ALote: String; const ASincrono: Boolean = False): Boolean; overload;
    procedure Cancela(AJustificativa: String);
    procedure Inutiliza(CNPJ, AJustificativa: String; Ano, Modelo, Serie, NumeroInicial, NumeroFinal : Integer);
  //published
    property ACBrNFe: TComponent read FACBrNFe write FACBrNFe;
    property StatusServico: TNFeStatusServico read FStatusServico write FStatusServico;
    property Enviar: TNFeRecepcao read FEnviar write FEnviar;
    property Retorno: TNFeRetRecepcao read FRetorno write FRetorno;
    property Recibo: TNFeRecibo read FRecibo write FRecibo;
    property Consulta: TNFeConsulta read FConsulta write FConsulta;
    property Cancelamento: TNFeCancelamento read FCancelamento write FCancelamento;
    property Inutilizacao: TNFeInutilizacao read FInutilizacao write FInutilizacao;
    property ConsultaCadastro: TNFeConsultaCadastro read FConsultaCadastro write FConsultaCadastro;
    property EnviarDPEC: TNFeEnvDPEC read FEnviaDPEC write FEnviaDPEC;
    property ConsultaDPEC: TNFeConsultaDPEC read FConsultaDPEC write FConsultaDPEC;
    property CartaCorrecao: TNFeCartaCorrecao read FCartaCorrecao write FCartaCorrecao;
    property EnvEvento: TNFeEnvEvento read FEnvEvento write FEnvEvento;
    property ConsNFeDest: TNFeConsNFeDest read FConsNFeDest write FConsNFeDest;
    property DownloadNFe: TNFeDownloadNFe read FDownloadNFe write FDownloadNFe;
    property EnvioWebService: TNFeEnvioWebService read FEnvioWebService write FEnvioWebService;
  end;

implementation

uses {$IFDEF ACBrNFeOpenSSL}
        ssl_openssl,
     {$ENDIF}
     ACBrUtil, ACBrNFeUtil, ACBrNFe, ACBrDFeUtil,
     pcnGerador, pcnCabecalho,
     pcnConsStatServ, pcnRetConsStatServ,
     pcnCancNFe, pcnConsSitNFe,
     pcnInutNFe, pcnRetInutNFe,
     pcnRetEnvNFe, pcnConsReciNFe ,
     pcnConsCad,
     pcnNFeR, pcnLeitor,
     pcnEnvDPEC, pcnConsDPEC, Math, pcnEventoNFe;

{$IFNDEF ACBrNFeOpenSSL}
const
  INTERNET_OPTION_CLIENT_CERT_CONTEXT = 84;
{$ENDIF}

{ TWebServicesBase }
constructor TWebServicesBase.Create(AOwner: TComponent);
begin
  FConfiguracoes := TConfiguracoes( TACBrNFe( AOwner ).Configuracoes );
  FACBrNFe       := TACBrNFe( AOwner );
end;

{$IFDEF ACBrNFeOpenSSL}
procedure TWebServicesBase.ConfiguraHTTP( HTTP : THTTPSend; Action : AnsiString);
begin
  if FileExists(FConfiguracoes.Certificados.Certificado) then
    HTTP.Sock.SSL.PFXfile   := FConfiguracoes.Certificados.Certificado
  else
    HTTP.Sock.SSL.PFX       := FConfiguracoes.Certificados.Certificado;

  HTTP.Sock.SSL.KeyPassword := FConfiguracoes.Certificados.Senha;

  HTTP.ProxyHost  := FConfiguracoes.WebServices.ProxyHost;
  HTTP.ProxyPort  := FConfiguracoes.WebServices.ProxyPort;
  HTTP.ProxyUser  := FConfiguracoes.WebServices.ProxyUser;
  HTTP.ProxyPass  := FConfiguracoes.WebServices.ProxyPass;

//  HTTP.Sock.RaiseExcept := True;

  if (pos('SCERECEPCAORFB',UpperCase(FURL)) <= 0) and
     (pos('SCECONSULTARFB',UpperCase(FURL)) <= 0) then
     HTTP.MimeType := 'application/soap+xml; charset=utf-8'
  else
     HTTP.MimeType := 'text/xml; charset=utf-8';

  HTTP.UserAgent := '';
  HTTP.Protocol := '1.1' ;
  HTTP.AddPortNumberToHost := False;
  HTTP.Headers.Add(Action);
end;

{$ELSE}
procedure TWebServicesBase.ConfiguraReqResp( ReqResp : THTTPReqResp);
begin
  if FConfiguracoes.WebServices.ProxyHost <> '' then
   begin
     ReqResp.Proxy        := FConfiguracoes.WebServices.ProxyHost+':'+FConfiguracoes.WebServices.ProxyPort;
     ReqResp.UserName     := FConfiguracoes.WebServices.ProxyUser;
     ReqResp.Password     := FConfiguracoes.WebServices.ProxyPass;
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
  Cert := FConfiguracoes.Certificados.GetCertificado;
  CertContext :=  Cert as ICertContext;
  CertContext.Get_CertContext(Integer(PCertContext));

  if not InternetSetOption(Data, INTERNET_OPTION_CLIENT_CERT_CONTEXT, PCertContext,SizeOf(CERT_CONTEXT)) then
   begin
     if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
        TACBrNFe( FACBrNFe ).OnGerarLog('ERRO: Erro OnBeforePost: ' + IntToStr(GetLastError));
     raise EACBrNFeException.Create( 'Erro OnBeforePost: ' + IntToStr(GetLastError) );
   end;

   if trim(FConfiguracoes.WebServices.ProxyUser) <> '' then begin
     if not InternetSetOption(Data, INTERNET_OPTION_PROXY_USERNAME, PChar(FConfiguracoes.WebServices.ProxyUser), Length(FConfiguracoes.WebServices.ProxyUser)) then
       raise EACBrNFeException.Create( 'Erro OnBeforePost: ' + IntToStr(GetLastError) );
   end;
   if trim(FConfiguracoes.WebServices.ProxyPass) <> '' then begin
     if not InternetSetOption(Data, INTERNET_OPTION_PROXY_PASSWORD, PChar(FConfiguracoes.WebServices.ProxyPass),Length (FConfiguracoes.WebServices.ProxyPass)) then
       raise EACBrNFeException.Create( 'Erro OnBeforePost: ' + IntToStr(GetLastError) );
   end;

  if (pos('SCERECEPCAORFB',UpperCase(FURL)) <= 0) and
     (pos('SCECONSULTARFB',UpperCase(FURL)) <= 0) then
   begin
     ContentHeader := Format(ContentTypeTemplate, ['application/soap+xml; charset=utf-8']);
     HttpAddRequestHeaders(Data, PChar(ContentHeader), Length(ContentHeader), HTTP_ADDREQ_FLAG_REPLACE);
   end;
  HTTPReqResp.CheckContentType;
//  HTTPReqResp.ConnectTimeout := 20000;
end;
{$ENDIF}

procedure TWebServicesBase.DoNFeCancelamento;
var
  CancNFe: TcancNFe;
begin
  CancNFe := TcancNFe.Create;
  CancNFe.schema  := TsPL006;
  CancNFe.chNFe   := TNFeCancelamento(Self).NFeChave;
  CancNFe.tpAmb   := TpcnTipoAmbiente(FConfiguracoes.WebServices.AmbienteCodigo-1);
  CancNFe.nProt   := TNFeCancelamento(Self).Protocolo;
  CancNFe.xJust   := TNFeCancelamento(Self).Justificativa;

  if (FConfiguracoes.Geral.ModeloDF = moNFCe) then 
     CancNFe.Versao := NFCeCanc
  else 
     CancNFe.Versao := NFeCancNFe;

  CancNFe.GerarXML;

{$IFDEF ACBrNFeOpenSSL}
  if not(NotaUtil.Assinar(CancNFe.Gerador.ArquivoFormatoXML, TConfiguracoes(FConfiguracoes).Certificados.Certificado , TConfiguracoes(FConfiguracoes).Certificados.Senha, FDadosMsg, FMsg)) then
    begin
      if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
         TACBrNFe( FACBrNFe ).OnGerarLog('ERRO: Falha ao assinar Cancelamento Nota Fiscal Eletr�nica '+LineBreak+FMsg);
      raise EACBrNFeException.Create('Falha ao assinar Cancelamento Nota Fiscal Eletr�nica '+LineBreak+FMsg);
    end;
{$ELSE}
  if not(NotaUtil.Assinar(CancNFe.Gerador.ArquivoFormatoXML, TConfiguracoes(FConfiguracoes).Certificados.GetCertificado , FDadosMsg, FMsg)) then
     begin
       if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
          TACBrNFe( FACBrNFe ).OnGerarLog('Falha ao assinar Cancelamento Nota Fiscal Eletr�nica '+LineBreak+FMsg);
       raise EACBrNFeException.Create('Falha ao assinar Cancelamento de Nota Fiscal Eletr�nica '+LineBreak+FMsg);
     end;
{$ENDIF}

  if not(NotaUtil.Valida(FDadosMsg, FMsg, TACBrNFe( FACBrNFe ).Configuracoes.Geral.PathSchemas, FConfiguracoes.Geral.ModeloDF)) then
     begin
       if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
          TACBrNFe( FACBrNFe ).OnGerarLog('Falha na valida��o dos dados do cancelamento '+LineBreak+FMsg);
       raise EACBrNFeException.Create('Falha na valida��o dos dados do cancelamento '+LineBreak+FMsg);
     end;

  CancNFe.Free;

  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8_STD+'>', '', [rfReplaceAll] ) ;
  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8+'>', '', [rfReplaceAll] ) ;
  FDadosMsg := StringReplace( FDadosMsg, '<?xml version="1.0"?>', '', [rfReplaceAll] ) ;
end;

procedure TWebServicesBase.DoNFeCartaCorrecao;
var
  CCeNFe : TCCeNFe;
  i, f : integer;
  // Incluido por Italo em 21/09/2012
  Eventos, Evento, Lote, EventosAssinados: AnsiString;
begin
  CCeNFe := TCCeNFe.Create;
  CCeNFe.schema := TsPL006;
  CCeNFe.idLote                         := TNFeCartaCorrecao(Self).idLote;
  for i := 0 to TNFeCartaCorrecao(Self).FCCe.Evento.Count-1 do
   begin
     with CCeNFe.Evento.Add do
      begin
        infEvento.cOrgao               := TNFeCartaCorrecao(Self).FCCe.Evento[i].InfEvento.cOrgao;
        infEvento.tpAmb                := TpcnTipoAmbiente(FConfiguracoes.WebServices.AmbienteCodigo-1);
        infEvento.CNPJ                 := TNFeCartaCorrecao(Self).FCCe.Evento[i].InfEvento.CNPJ;
        infEvento.chNFe                := TNFeCartaCorrecao(Self).FCCe.Evento[i].InfEvento.chNFe;
        infEvento.dhEvento             := TNFeCartaCorrecao(Self).FCCe.Evento[i].InfEvento.dhEvento;
        infEvento.tpEvento             := TNFeCartaCorrecao(Self).FCCe.Evento[i].InfEvento.tpEvento;
        infEvento.nSeqEvento           := TNFeCartaCorrecao(Self).FCCe.Evento[i].InfEvento.nSeqEvento;
        infEvento.versaoEvento         := TNFeCartaCorrecao(Self).FCCe.Evento[i].InfEvento.versaoEvento;
        infEvento.detEvento.versao     := TNFeCartaCorrecao(Self).FCCe.Evento[i].InfEvento.detEvento.versao;
        infEvento.detEvento.descEvento := TNFeCartaCorrecao(Self).FCCe.Evento[i].InfEvento.detEvento.descEvento;
        infEvento.detEvento.xCorrecao  := TNFeCartaCorrecao(Self).FCCe.Evento[i].InfEvento.detEvento.xCorrecao;
        infEvento.detEvento.xCondUso   := TNFeCartaCorrecao(Self).FCCe.Evento[i].InfEvento.detEvento.xCondUso;
      end;
   end;

  // Incluido por Italo em 18/02/2013
  if (FConfiguracoes.Geral.ModeloDF = moNFCe) then 
     CCeNFe.Versao := NFCeCCe
  else 
     CCeNFe.Versao := NFeCCeNFe;

  CCeNFe.GerarXML;

  // Incluido por Italo em 21/09/2012

  // Separa os grupos <evento> e coloca na vari�vel Eventos
  i       := Pos( '<evento ', CCeNFe.Gerador.ArquivoFormatoXML );
  Lote    := Copy( CCeNFe.Gerador.ArquivoFormatoXML, 1, i - 1 );
  Eventos := SeparaDados( CCeNFe.Gerador.ArquivoFormatoXML, 'envEvento' );
  i       := Pos( '<evento ', Eventos );
  Eventos := Copy( Eventos, i, length(Eventos) );

  EventosAssinados := '';

  // Realiza a assinatura para cada evento
  while Eventos <> '' do
   begin
    f := Pos( '</evento>', Eventos );

    if f > 0
     then begin
      Evento  := Copy( Eventos, 1, f + 8 );
      Eventos := Copy( Eventos, f + 9, length(Eventos) );

  {$IFDEF ACBrNFeOpenSSL}
      if not(NotaUtil.Assinar(Evento, TConfiguracoes(FConfiguracoes).Certificados.Certificado , TConfiguracoes(FConfiguracoes).Certificados.Senha, FDadosMsg, FMsg)) then
         begin
           if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
              TACBrNFe( FACBrNFe ).OnGerarLog('Falha ao assinar o Envio de Evento '+LineBreak+FMsg);
           raise EACBrNFeException.Create('Falha ao assinar o Envio de Evento '+LineBreak+FMsg);
         end;
  {$ELSE}
      if not(NotaUtil.Assinar(Evento, TConfiguracoes(FConfiguracoes).Certificados.GetCertificado , FDadosMsg, FMsg)) then
         begin
           if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
              TACBrNFe( FACBrNFe ).OnGerarLog('Falha ao assinar o Envio de Evento '+LineBreak+FMsg);
           raise EACBrNFeException.Create('Falha ao assinar o Envio de Evento '+LineBreak+FMsg);
         end;
  {$ENDIF}

      EventosAssinados := EventosAssinados + FDadosMsg;
     end
     else Eventos := '';
   end;

  //Corrigido por Jo�o Henrique em 28/09/2012
  //<?xml version="1.0"?> n�o estava ficando no in�cio do arquivo
  //FDadosMsg := Lote + EventosAssinados + '</envEvento>';
  f := Pos( '?>', EventosAssinados );
  if f <> 0 then
    FDadosMsg := copy(EventosAssinados,1,f+1) +
                 Lote +
                 copy(EventosAssinados,f+2,Length(EventosAssinados)) +
                 '</envEvento>'
  else
    FDadosMsg := Lote + EventosAssinados + '</envEvento>';

(*
  {$IFDEF ACBrNFeOpenSSL}
  if not(NotaUtil.Assinar(CCeNFe.Gerador.ArquivoFormatoXML, TConfiguracoes(FConfiguracoes).Certificados.Certificado , TConfiguracoes(FConfiguracoes).Certificados.Senha, FDadosMsg, FMsg)) then
     begin
       if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
          TACBrNFe( FACBrNFe ).OnGerarLog('Falha ao assinar Carta de Corre��o Eletr�nica '+LineBreak+FMsg);
       raise EACBrNFeException.Create('Falha ao assinar Carta de Corre��o Eletr�nica '+LineBreak+FMsg);
     end;
  {$ELSE}
  if not(NotaUtil.Assinar(CCeNFe.Gerador.ArquivoFormatoXML, TConfiguracoes(FConfiguracoes).Certificados.GetCertificado , FDadosMsg, FMsg)) then
     begin
       if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
          TACBrNFe( FACBrNFe ).OnGerarLog('Falha ao assinar Carta de Corre��o Eletr�nica '+LineBreak+FMsg);
       raise EACBrNFeException.Create('Falha ao assinar Carta de Corre��o Eletr�nica '+LineBreak+FMsg);
     end;
  {$ENDIF}
*)

  if not(NotaUtil.Valida(FDadosMsg, FMsg, TACBrNFe( FACBrNFe ).Configuracoes.Geral.PathSchemas, FConfiguracoes.Geral.ModeloDF)) then
     begin
       if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
          TACBrNFe( FACBrNFe ).OnGerarLog('Falha na valida��o dos dados da carta de corre��o '+LineBreak+FMsg);
       raise EACBrNFeException.Create('Falha na valida��o dos dados da carta de corre��o '+LineBreak+FMsg);
     end;

  for i := 0 to TNFeCartaCorrecao(Self).FCCe.Evento.Count-1 do
   begin
      TNFeCartaCorrecao(Self).FCCe.Evento[i].InfEvento.id := CCeNFe.Evento[i].InfEvento.id;
   end;
  CCeNFe.Free;

  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8_STD+'>', '', [rfReplaceAll] ) ;
  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8+'>', '', [rfReplaceAll] ) ;
  FDadosMsg := StringReplace( FDadosMsg, '<?xml version="1.0"?>', '', [rfReplaceAll] ) ;
end;

procedure TWebServicesBase.DoNFeConsulta;
var
  ConsSitNFe : TConsSitNFe;
begin
  ConsSitNFe    := TConsSitNFe.Create;
  ConsSitNFe.schema := TsPL006;
  ConsSitNFe.TpAmb := TpcnTipoAmbiente(FConfiguracoes.WebServices.AmbienteCodigo-1);
  ConsSitNFe.chNFe  := TNFeConsulta(Self).NFeChave;

  if (FConfiguracoes.Geral.ModeloDF = moNFCe) then 
     ConsSitNFe.Versao := NFCeConsSit
  else 
     ConsSitNFe.Versao := NFeConsSitNFe;

  ConsSitNFe.GerarXML;

  FDadosMsg := ConsSitNFe.Gerador.ArquivoFormatoXML;
  ConsSitNFe.Free;

  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8_STD+'>', '', [rfReplaceAll] ) ;
  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8+'>', '', [rfReplaceAll] ) ;
  FDadosMsg := StringReplace( FDadosMsg, '<?xml version="1.0"?>', '', [rfReplaceAll] ) ;  
end;

procedure TWebServicesBase.DoNFeInutilizacao;
var
  InutNFe: TinutNFe;
begin
  InutNFe := TinutNFe.Create;
  InutNFe.schema  := TsPL006;
  InutNFe.tpAmb   := TpcnTipoAmbiente(FConfiguracoes.WebServices.AmbienteCodigo-1);
  InutNFe.cUF     := FConfiguracoes.WebServices.UFCodigo;
  InutNFe.ano     := TNFeInutilizacao(Self).Ano;
  InutNFe.CNPJ    := TNFeInutilizacao(Self).CNPJ;
  InutNFe.modelo  := TNFeInutilizacao(Self).Modelo;
  InutNFe.serie   := TNFeInutilizacao(Self).Serie;
  InutNFe.nNFIni  := TNFeInutilizacao(Self).NumeroInicial;
  InutNFe.nNFFin  := TNFeInutilizacao(Self).NumeroFinal;
  InutNFe.xJust   := TNFeInutilizacao(Self).Justificativa;

  if (FConfiguracoes.Geral.ModeloDF = moNFCe) then 
     InutNFe.Versao := NFCeInut
  else 
     InutNFe.Versao := NFeInutNFe;

  InutNFe.GerarXML;

{$IFDEF ACBrNFeOpenSSL}
  if not(NotaUtil.Assinar(InutNFe.Gerador.ArquivoFormatoXML, TConfiguracoes(FConfiguracoes).Certificados.Certificado , TConfiguracoes(FConfiguracoes).Certificados.Senha, FDadosMsg, FMsg)) then
     begin
       if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
          TACBrNFe( FACBrNFe ).OnGerarLog('Falha ao assinar Inutiliza��o Nota Fiscal Eletr�nica '+LineBreak+FMsg);
       raise EACBrNFeException.Create('Falha ao assinar Inutiliza��o Nota Fiscal Eletr�nica '+LineBreak+FMsg);
     end;
{$ELSE}
  if not(NotaUtil.Assinar(InutNFe.Gerador.ArquivoFormatoXML, TConfiguracoes(FConfiguracoes).Certificados.GetCertificado , FDadosMsg, FMsg)) then
     begin
       if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
          TACBrNFe( FACBrNFe ).OnGerarLog('Falha ao assinar Inutiliza��o Nota Fiscal Eletr�nica '+LineBreak+FMsg);
       raise EACBrNFeException.Create('Falha ao assinar Inutiliza��o Nota Fiscal Eletr�nica '+LineBreak+FMsg);
     end;
{$ENDIF}

  TNFeInutilizacao(Self).ID := InutNFe.ID;

  InutNFe.Free;

  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8_STD+'>', '', [rfReplaceAll] ) ;
  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8+'>', '', [rfReplaceAll] ) ;
  FDadosMsg := StringReplace( FDadosMsg, '<?xml version="1.0"?>', '', [rfReplaceAll] ) ;
end;

procedure TWebServicesBase.DoNFeConsultaCadastro;
var
  ConCadNFe: TConsCad;
begin
  ConCadNFe := TConsCad.Create;
  ConCadNFe.schema := TsPL006;
  ConCadNFe.UF     := TNFeConsultaCadastro(Self).UF;
  ConCadNFe.IE     := TNFeConsultaCadastro(Self).IE;
  ConCadNFe.CNPJ   := TNFeConsultaCadastro(Self).CNPJ;
  ConCadNFe.CPF    := TNFeConsultaCadastro(Self).CPF;

  if (FConfiguracoes.Geral.ModeloDF = moNFCe) then 
     ConCadNFe.Versao := NFCeConsCad
  else 
     ConCadNFe.Versao := NFeConsCad;

  ConCadNFe.GerarXML;

  FDadosMsg := ConCadNFe.Gerador.ArquivoFormatoXML;

  ConCadNFe.Free;

  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8_STD+'>', '', [rfReplaceAll] ) ;
  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8+'>', '', [rfReplaceAll] ) ;
  FDadosMsg := StringReplace( FDadosMsg, '<?xml version="1.0"?>', '', [rfReplaceAll] ) ;
end;

procedure TWebServicesBase.DoNFeEnvDPEC;
var
  EnvDPEC: TEnvDPEC;
  i : Integer;
begin
  EnvDPEC := TEnvDPEC.Create;
  EnvDPEC.schema := TsPL005c;

  TACBrNFe( FACBrNFe ).NotasFiscais.GerarNFe; //Gera NFe pra pegar a Chave
  if TACBrNFe( FACBrNFe ).Configuracoes.Geral.Salvar then
     TACBrNFe( FACBrNFe ).NotasFiscais.SaveToFile; // Se tiver configurado pra salvar, salva as NFes

  with EnvDPEC.infDPEC do
   begin
     ID := TACBrNFe( FACBrNFe ).NotasFiscais.Items[0].NFe.Emit.CNPJCPF;

     IdeDec.cUF   := TACBrNFe( FACBrNFe ).Configuracoes.WebServices.UFCodigo;
     ideDec.tpAmb := TACBrNFe( FACBrNFe ).Configuracoes.WebServices.Ambiente;
     ideDec.verProc := ACBRNFE_VERSAO;
     ideDec.CNPJ := TACBrNFe( FACBrNFe ).NotasFiscais.Items[0].NFe.Emit.CNPJCPF;
     ideDec.IE   := TACBrNFe( FACBrNFe ).NotasFiscais.Items[0].NFe.Emit.IE;

     for i:= 0 to TACBrNFe( FACBrNFe ).NotasFiscais.Count-1 do
      begin
        with resNFe.Add do
         begin
           chNFe   := StringReplace(TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.infNFe.id,'NFe','',[rfReplaceAll]);
           CNPJCPF := TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.dest.CNPJCPF;
           UF      := TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.dest.enderdEST.UF;
           vNF     := TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.Total.ICMSTot.vNF;
           vICMS   := TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.Total.ICMSTot.vICMS;
           vST     := TACBrNFe( FACBrNFe ).NotasFiscais.Items[I].NFe.Total.ICMSTot.vST;
         end;
      end;
   end;

  if (FConfiguracoes.Geral.ModeloDF = moNFCe) then 
     EnvDPEC.Versao := NFCeEnvDPEC
  else 
     EnvDPEC.Versao := NFeEnvDPEC;

  EnvDPEC.GerarXML;

{$IFDEF ACBrNFeOpenSSL}
  if not(NotaUtil.Assinar(EnvDPEC.Gerador.ArquivoFormatoXML, TConfiguracoes(FConfiguracoes).Certificados.Certificado , TConfiguracoes(FConfiguracoes).Certificados.Senha, FDadosMsg, FMsg)) then
     begin
       if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
          TACBrNFe( FACBrNFe ).OnGerarLog('Falha ao assinar DPEC '+LineBreak+FMsg);
       raise EACBrNFeException.Create('Falha ao assinar DPEC '+LineBreak+FMsg);
     end;
{$ELSE}
  if not(NotaUtil.Assinar(EnvDPEC.Gerador.ArquivoFormatoXML, TConfiguracoes(FConfiguracoes).Certificados.GetCertificado , FDadosMsg, FMsg)) then
     begin
       if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
          TACBrNFe( FACBrNFe ).OnGerarLog('Falha ao assinar DPEC '+LineBreak+FMsg);
       raise EACBrNFeException.Create('Falha ao assinar DPEC '+LineBreak+FMsg);
     end;
{$ENDIF}
  EnvDPEC.Free ;

  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8_STD+'>', '', [rfReplaceAll] ) ;
  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8+'>', '', [rfReplaceAll] ) ;
  FDadosMsg := StringReplace( FDadosMsg, '<?xml version="1.0"?>', '', [rfReplaceAll] ) ;
end;

procedure TWebServicesBase.DoNFeConsultaDPEC;
var
  ConsDPEC: TConsDPEC;
begin
  ConsDPEC := TConsDPEC.Create;
  ConsDPEC.schema   := TsPL005c;
  ConsDPEC.tpAmb    := TpcnTipoAmbiente(FConfiguracoes.WebServices.AmbienteCodigo-1);
  ConsDPEC.verAplic := NfVersao;
  ConsDPEC.nRegDPEC := TNFeConsultaDPEC(Self).nRegDPEC;
  ConsDPEC.chNFe    := TNFeConsultaDPEC(Self).NFeChave;

  if (FConfiguracoes.Geral.ModeloDF = moNFCe) then 
     ConsDPEC.Versao := NFCeConsDPEC
  else 
     ConsDPEC.Versao := NFeConsDPEC;

  ConsDPEC.GerarXML;

  FDadosMsg := ConsDPEC.Gerador.ArquivoFormatoXML;

  ConsDPEC.Free;

  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8_STD+'>', '', [rfReplaceAll] ) ;
  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8+'>', '', [rfReplaceAll] ) ;
  FDadosMsg := StringReplace( FDadosMsg, '<?xml version="1.0"?>', '', [rfReplaceAll] ) ;
end;

procedure TWebServicesBase.DoNFeRecepcao;
var
  i: Integer;
  vNotas: WideString;
  indSinc, Versao: String;
begin
  if (FConfiguracoes.Geral.ModeloDF = moNFCe) then 
   begin
    if (TNFeRecepcao(Self).Sincrono) then 
       indSinc := '<indSinc>1</indSinc>'
    else 
       indSinc := '<indSinc>0</indSinc>';
    Versao := NFCeEnvi;
   end
  else 
   begin
    indSinc := '';
    Versao := NFenviNFe;
   end;

  vNotas := '';
  for i := 0 to TNFeRecepcao(Self).FNotasFiscais.Count-1 do
    vNotas := vNotas + '<NFe'+RetornarConteudoEntre(TNFeRecepcao(Self).FNotasFiscais.Items[I].XML,'<NFe','</NFe>')+'</NFe>';

  FDadosMsg := '<enviNFe xmlns="http://www.portalfiscal.inf.br/nfe" versao="' + Versao + '">'+
                '<idLote>'+TNFeRecepcao(Self).Lote+'</idLote>'+
                indSinc +
                vNotas +
               '</enviNFe>';

  if Length(FDadosMsg) > (500 * 1024) then
   begin
      if Assigned(TACBrNFe(Self.FACBrNFe).OnGerarLog) then
         TACBrNFe(Self.FACBrNFe).OnGerarLog('ERRO: Tamanho do XML de Dados superior a 500 Kbytes. Tamanho atual: '+FloatToStr(Int(Length(FDadosMsg)/500))+' Kbytes');
      raise EACBrNFeException.Create('ERRO: Tamanho do XML de Dados superior a 500 Kbytes. Tamanho atual: '+FloatToStr(Int(Length(FDadosMsg)/500))+' Kbytes');
      exit;
   end;
end;

procedure TWebServicesBase.DoNFeRetRecepcao;
var
  ConsReciNFe: TConsReciNFe;
begin
  ConsReciNFe   := TConsReciNFe.Create;
  ConsReciNFe.schema := TsPL006;
  ConsReciNFe.tpAmb  := TpcnTipoAmbiente(FConfiguracoes.WebServices.AmbienteCodigo-1);
  ConsReciNFe.nRec   := TNFeRetRecepcao(Self).Recibo;

  if (FConfiguracoes.Geral.ModeloDF = moNFCe) then 
     ConsReciNFe.Versao := NFCeConsReci
  else 
     ConsReciNFe.Versao := NFeConsReciNFe;

  ConsReciNFe.GerarXML;

  FDadosMsg := ConsReciNFe.Gerador.ArquivoFormatoXML;
  ConsReciNFe.Free;
  
  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8_STD+'>', '', [rfReplaceAll] ) ;
  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8+'>', '', [rfReplaceAll] ) ;
  FDadosMsg := StringReplace( FDadosMsg, '<?xml version="1.0"?>', '', [rfReplaceAll] ) ;
end;

procedure TWebServicesBase.DoNFeRecibo;
var
  ConsReciNFe: TConsReciNFe;
begin
  ConsReciNFe   := TConsReciNFe.Create;
  ConsReciNFe.schema := TsPL006;
  ConsReciNFe.tpAmb  := TpcnTipoAmbiente(FConfiguracoes.WebServices.AmbienteCodigo-1);
  ConsReciNFe.nRec   := TNFeRecibo(Self).Recibo;

  if (FConfiguracoes.Geral.ModeloDF = moNFCe) then 
     ConsReciNFe.Versao := NFCeConsReci
  else 
     ConsReciNFe.Versao := NFeConsReciNFe;

  ConsReciNFe.GerarXML;

  FDadosMsg := ConsReciNFe.Gerador.ArquivoFormatoXML;
  ConsReciNFe.Free;

  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8_STD+'>', '', [rfReplaceAll] ) ;
  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8+'>', '', [rfReplaceAll] ) ;
  FDadosMsg := StringReplace( FDadosMsg, '<?xml version="1.0"?>', '', [rfReplaceAll] ) ;  
end;

procedure TWebServicesBase.DoNFeStatusServico;
var
  ConsStatServ: TConsStatServ;
begin
  ConsStatServ := TConsStatServ.create;
  ConsStatServ.schema := TsPL006;
  ConsStatServ.TpAmb  := TpcnTipoAmbiente(FConfiguracoes.WebServices.AmbienteCodigo-1);
  ConsStatServ.CUF    := FConfiguracoes.WebServices.UFCodigo;

  if (FConfiguracoes.Geral.ModeloDF = moNFCe) then 
     ConsStatServ.Versao := NFCeConsStatServ
  else 
     ConsStatServ.Versao := NFeConsStatServ;

  ConsStatServ.GerarXML;

  FDadosMsg := ConsStatServ.Gerador.ArquivoFormatoXML;
  ConsStatServ.Free;

  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8_STD+'>', '', [rfReplaceAll] ) ;
  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8+'>', '', [rfReplaceAll] ) ;
  FDadosMsg := StringReplace( FDadosMsg, '<?xml version="1.0"?>', '', [rfReplaceAll] ) ;
end;

procedure TWebServicesBase.DoNFeEnvEvento;
var
  EventoNFe : TEventoNFe;
  i, f : integer;
  Eventos, Evento, Lote, EventosAssinados: AnsiString;
begin
  EventoNFe        := TEventoNFe.Create;
  EventoNFe.schema := TsPL006;
  EventoNFe.idLote := TNFeEnvEvento(Self).idLote;
  for i := 0 to TNFeEnvEvento(Self).FEvento.Evento.Count-1 do
   begin
     with EventoNFe.Evento.Add do
      begin
        infEvento.tpAmb                := TpcnTipoAmbiente(FConfiguracoes.WebServices.AmbienteCodigo-1);
        infEvento.CNPJ                 := TNFeEnvEvento(Self).FEvento.Evento[i].InfEvento.CNPJ;
        infEvento.cOrgao               := TNFeEnvEvento(Self).FEvento.Evento[i].InfEvento.cOrgao;
        infEvento.chNFe                := TNFeEnvEvento(Self).FEvento.Evento[i].InfEvento.chNFe;
        infEvento.dhEvento             := TNFeEnvEvento(Self).FEvento.Evento[i].InfEvento.dhEvento;
        infEvento.tpEvento             := TNFeEnvEvento(Self).FEvento.Evento[i].InfEvento.tpEvento;
        infEvento.nSeqEvento           := TNFeEnvEvento(Self).FEvento.Evento[i].InfEvento.nSeqEvento;
        case InfEvento.tpEvento of
          teCCe:
          begin
            infEvento.detEvento.xCorrecao  := TNFeEnvEvento(Self).FEvento.Evento[i].InfEvento.detEvento.xCorrecao;
            infEvento.detEvento.xCondUso   := TNFeEnvEvento(Self).FEvento.Evento[i].InfEvento.detEvento.xCondUso;
          end;
          teCancelamento:
          begin
            infEvento.detEvento.nProt  := TNFeEnvEvento(Self).FEvento.Evento[i].InfEvento.detEvento.nProt;
            infEvento.detEvento.xJust  := TNFeEnvEvento(Self).FEvento.Evento[i].InfEvento.detEvento.xJust;
          end;
          teManifDestOperNaoRealizada:
          begin
            infEvento.detEvento.xJust  := TNFeEnvEvento(Self).FEvento.Evento[i].InfEvento.detEvento.xJust;
          end;
        end;
      end;
   end;

  if (FConfiguracoes.Geral.ModeloDF = moNFCe) then 
     EventoNFe.Versao := NFCeEvento
  else 
     EventoNFe.Versao := NFeEventoNFe;

  EventoNFe.GerarXML;

  // Incluido por Italo em 14/09/2012

  // Separa os grupos <evento> e coloca na vari�vel Eventos
  i       := Pos( '<evento ', EventoNFe.Gerador.ArquivoFormatoXML );
  Lote    := Copy( EventoNFe.Gerador.ArquivoFormatoXML, 1, i - 1 );
  Eventos := SeparaDados( EventoNFe.Gerador.ArquivoFormatoXML, 'envEvento' );
  i       := Pos( '<evento ', Eventos );
  Eventos := Copy( Eventos, i, length(Eventos) );

  EventosAssinados := '';

  // Realiza a assinatura para cada evento
  while Eventos <> '' do
   begin
    f := Pos( '</evento>', Eventos );

    if f > 0
     then begin
      Evento  := Copy( Eventos, 1, f + 8 );
      Eventos := Copy( Eventos, f + 9, length(Eventos) );

  {$IFDEF ACBrNFeOpenSSL}
      if not(NotaUtil.Assinar(Evento, TConfiguracoes(FConfiguracoes).Certificados.Certificado , TConfiguracoes(FConfiguracoes).Certificados.Senha, FDadosMsg, FMsg)) then
         begin
           if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
              TACBrNFe( FACBrNFe ).OnGerarLog('Falha ao assinar o Envio de Evento '+LineBreak+FMsg);
           raise EACBrNFeException.Create('Falha ao assinar o Envio de Evento '+LineBreak+FMsg);
         end;
  {$ELSE}
      if not(NotaUtil.Assinar(Evento, TConfiguracoes(FConfiguracoes).Certificados.GetCertificado , FDadosMsg, FMsg)) then
         begin
           if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
              TACBrNFe( FACBrNFe ).OnGerarLog('Falha ao assinar o Envio de Evento '+LineBreak+FMsg);
           raise EACBrNFeException.Create('Falha ao assinar o Envio de Evento '+LineBreak+FMsg);
         end;
  {$ENDIF}

      EventosAssinados := EventosAssinados + FDadosMsg;
     end
     else Eventos := '';
   end;

  //Corrigido por Jo�o Henrique em 28/09/2012
  //<?xml version="1.0"?> n�o estava ficando no in�cio do arquivo
  //FDadosMsg := Lote + EventosAssinados + '</envEvento>';
  f := Pos( '?>', EventosAssinados );
  if f <> 0 then
    FDadosMsg := copy(EventosAssinados,1,f+1) +
                 Lote +
                 copy(EventosAssinados,f+2,Length(EventosAssinados)) +
                 '</envEvento>'
  else
    FDadosMsg := Lote + EventosAssinados + '</envEvento>';

(*
  {$IFDEF ACBrNFeOpenSSL}
  if not(NotaUtil.Assinar(EventoNFe.Gerador.ArquivoFormatoXML, TConfiguracoes(FConfiguracoes).Certificados.Certificado , TConfiguracoes(FConfiguracoes).Certificados.Senha, FDadosMsg, FMsg)) then
     begin
       if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
          TACBrNFe( FACBrNFe ).OnGerarLog('Falha ao assinar o Envio de Evento '+LineBreak+FMsg);
       raise EACBrNFeException.Create('Falha ao assinar o Envio de Evento '+LineBreak+FMsg);
     end;
  {$ELSE}
  if not(NotaUtil.Assinar(EventoNFe.Gerador.ArquivoFormatoXML, TConfiguracoes(FConfiguracoes).Certificados.GetCertificado , FDadosMsg, FMsg)) then
     begin
       if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
          TACBrNFe( FACBrNFe ).OnGerarLog('Falha ao assinar o Envio de Evento '+LineBreak+FMsg);
       raise EACBrNFeException.Create('Falha ao assinar o Envio de Evento '+LineBreak+FMsg);
     end;
  {$ENDIF}
*)

  if not(NotaUtil.Valida(FDadosMsg, FMsg, TACBrNFe( FACBrNFe ).Configuracoes.Geral.PathSchemas, FConfiguracoes.Geral.ModeloDF)) then
     begin
       if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
          TACBrNFe( FACBrNFe ).OnGerarLog('Falha na valida��o dos dados do Envio de Evento '+LineBreak+FMsg);
       raise EACBrNFeException.Create('Falha na valida��o dos dados do Envio de Evento '+LineBreak+FMsg);
     end;

  for i := 0 to TNFeEnvEvento(Self).FEvento.Evento.Count-1 do
   begin
      TNFeEnvEvento(Self).FEvento.Evento[i].InfEvento.id := EventoNFe.Evento[i].InfEvento.id;
   end;
  EventoNFe.Free;

  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8_STD+'>', '', [rfReplaceAll] ) ;
  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8+'>', '', [rfReplaceAll] ) ;
  FDadosMsg := StringReplace( FDadosMsg, '<?xml version="1.0"?>', '', [rfReplaceAll] ) ;
end;

function TWebServicesBase.Executar: Boolean;
begin
  Result := False;
  LoadMsgEntrada;
  LoadURL;
end;

procedure TWebServicesBase.LoadMsgEntrada;
begin
  if self is TNFeStatusServico then
    DoNFeStatusServico
  else if self is TNFeRecepcao then
    DoNFeRecepcao
  else if self is TNFeRetRecepcao then
    DoNFeRetRecepcao
  else if self is TNFeRecibo then
    DoNFeRecibo
  else if self is TNFeConsulta then
    DONFeConsulta
  else if self is TNFeCancelamento then
    DONFeCancelamento
  else if self is TNFeInutilizacao then
    DoNFeInutilizacao
  else if self is TNFeConsultaCadastro then
    DoNFeConsultaCadastro
  else if self is TNFeEnvDPEC then
    DoNFeEnvDPEC
  else if self is TNFeConsultaDPEC then
    DoNFeConsultaDPEC
  else if Self is TNFeCartaCorrecao then
    DoNFeCartaCorrecao
  else if Self is TNFeEnvEvento then
    DoNFeEnvEvento
  else if Self is TNFeConsNFeDest then
    DoNFeConsNFeDest
  else if Self is TNFeDownloadNFe then 
    DoNFeDownloadNFe;
end;

procedure TWebServicesBase.LoadURL;
begin
  if self is TNFeStatusServico then
    FURL  := NotaUtil.GetURL(FConfiguracoes.WebServices.UFCodigo, FConfiguracoes.WebServices.AmbienteCodigo, FConfiguracoes.Geral.FormaEmissaoCodigo, LayNfeStatusServico, FConfiguracoes.Geral.ModeloDF)
  else if self is TNFeRecepcao then
    FURL  := NotaUtil.GetURL(FConfiguracoes.WebServices.UFCodigo, FConfiguracoes.WebServices.AmbienteCodigo, FConfiguracoes.Geral.FormaEmissaoCodigo, LayNfeRecepcao, FConfiguracoes.Geral.ModeloDF)
  else if (self is TNFeRetRecepcao) or (self is TNFeRecibo) then
    FURL  := NotaUtil.GetURL(FConfiguracoes.WebServices.UFCodigo, FConfiguracoes.WebServices.AmbienteCodigo, FConfiguracoes.Geral.FormaEmissaoCodigo, LayNfeRetRecepcao, FConfiguracoes.Geral.ModeloDF)
  else if self is TNFeConsulta then
    FURL  := NotaUtil.GetURL(FConfiguracoes.WebServices.UFCodigo, FConfiguracoes.WebServices.AmbienteCodigo, FConfiguracoes.Geral.FormaEmissaoCodigo, LayNfeConsulta, FConfiguracoes.Geral.ModeloDF)
  else if self is TNFeCancelamento then
    FURL  := NotaUtil.GetURL(FConfiguracoes.WebServices.UFCodigo, FConfiguracoes.WebServices.AmbienteCodigo, FConfiguracoes.Geral.FormaEmissaoCodigo, LayNfeCancelamento, FConfiguracoes.Geral.ModeloDF)
  else if self is TNFeInutilizacao then
    FURL  := NotaUtil.GetURL(FConfiguracoes.WebServices.UFCodigo, FConfiguracoes.WebServices.AmbienteCodigo, FConfiguracoes.Geral.FormaEmissaoCodigo, LayNfeInutilizacao, FConfiguracoes.Geral.ModeloDF)
  else if self is TNFeConsultaCadastro then
    FURL  := NotaUtil.GetURL(UFparaCodigo(TNFeConsultaCadastro(Self).UF), FConfiguracoes.WebServices.AmbienteCodigo, FConfiguracoes.Geral.FormaEmissaoCodigo, LayNfeCadastro, FConfiguracoes.Geral.ModeloDF)
  else if self is TNFeEnvDPEC then
    FURL  := NotaUtil.GetURL(FConfiguracoes.WebServices.UFCodigo, FConfiguracoes.WebServices.AmbienteCodigo, FConfiguracoes.Geral.FormaEmissaoCodigo, LayNfeEnvDPEC, FConfiguracoes.Geral.ModeloDF)
  else if self is TNFeConsultaDPEC then
    FURL  := NotaUtil.GetURL(FConfiguracoes.WebServices.UFCodigo, FConfiguracoes.WebServices.AmbienteCodigo, FConfiguracoes.Geral.FormaEmissaoCodigo, LayNfeConsultaDPEC, FConfiguracoes.Geral.ModeloDF)
  else if self is TNFeCartaCorrecao then
    FURL  := NotaUtil.GetURL(FConfiguracoes.WebServices.UFCodigo, FConfiguracoes.WebServices.AmbienteCodigo, FConfiguracoes.Geral.FormaEmissaoCodigo, LayNFeCCe, FConfiguracoes.Geral.ModeloDF)
  else if self is TNFeEnvEvento then
  begin
    //Verifica��o necess�ria pois somente os eventos de Cancelamento e CCe ser�o tratados pela SEFAZ do estado
    //os outros eventos como manifestacao de destinat�rios ser�o tratados diretamente pela RFB
    if not ((self as TNFeEnvEvento).FEvento.Evento.Items[0].InfEvento.tpEvento
            in [teCCe,teCancelamento]) then
      FURL  := NotaUtil.GetURL(FConfiguracoes.WebServices.UFCodigo, FConfiguracoes.WebServices.AmbienteCodigo, FConfiguracoes.Geral.FormaEmissaoCodigo, LayNFeEventoAN, FConfiguracoes.Geral.ModeloDF)
    else
      FURL  := NotaUtil.GetURL(FConfiguracoes.WebServices.UFCodigo, FConfiguracoes.WebServices.AmbienteCodigo, FConfiguracoes.Geral.FormaEmissaoCodigo, LayNFeEvento, FConfiguracoes.Geral.ModeloDF)
  end
  else if self is TNFeConsNFeDest then
    FURL  := NotaUtil.GetURL(FConfiguracoes.WebServices.UFCodigo, FConfiguracoes.WebServices.AmbienteCodigo, FConfiguracoes.Geral.FormaEmissaoCodigo, LayNFeConsNFeDest, FConfiguracoes.Geral.ModeloDF)
  else if self is TNFeDownloadNFe then 
    FURL  := NotaUtil.GetURL(FConfiguracoes.WebServices.UFCodigo, FConfiguracoes.WebServices.AmbienteCodigo, FConfiguracoes.Geral.FormaEmissaoCodigo, LayNFeDownloadNFe, FConfiguracoes.Geral.ModeloDF)
end;

procedure TWebServicesBase.DoNFeConsNFeDest;
var
  ConsNFeDest: TConsNFeDest;
begin
  ConsNFeDest := TConsNFeDest.create;
  ConsNFeDest.schema := TsPL006;
  ConsNFeDest.TpAmb  := TpcnTipoAmbiente(FConfiguracoes.WebServices.AmbienteCodigo-1);
  ConsNFeDest.CNPJ   := TNFeConsNFeDest(Self).CNPJ;
  ConsNFeDest.indNFe := TNFeConsNFeDest(Self).indNFe;
  ConsNFeDest.indEmi := TNFeConsNFeDest(Self).indEmi;
  ConsNFeDest.ultNSU := TNFeConsNFeDest(Self).ultNSU;

  if (FConfiguracoes.Geral.ModeloDF = moNFCe) then 
     ConsNFeDest.Versao := NFCeConsNFeDest
  else 
     ConsNFeDest.Versao := NFeConsNFeDest;

  ConsNFeDest.GerarXML;

  FDadosMsg := ConsNFeDest.Gerador.ArquivoFormatoXML;
  ConsNFeDest.Free;

  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8_STD+'>', '', [rfReplaceAll] ) ;
  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8+'>', '', [rfReplaceAll] ) ;
  FDadosMsg := StringReplace( FDadosMsg, '<?xml version="1.0"?>', '', [rfReplaceAll] ) ;
end;

procedure TWebServicesBase.DoNFeDownloadNFe;
var
  DownloadNFe: TDownloadNFe;
  i: integer;
begin
  DownloadNFe := TDownloadNFe.create;
  DownloadNFe.schema := TsPL006;
  DownloadNFe.TpAmb  := TpcnTipoAmbiente(FConfiguracoes.WebServices.AmbienteCodigo-1);
  DownloadNFe.CNPJ   := TNFeDownloadNFe(Self).FDownload.CNPJ;

  for i := 0 to TNFeDownloadNFe(Self).FDownload.Chaves.Count - 1 do
   begin
     with DownloadNFe.Chaves.Add do
      begin
        chNFe := TNFeDownloadNFe(Self).FDownload.Chaves[i].chNFe;
//        chNFe := TDownloadNFe(Self).Chaves.Items[i].chNFe;
      end;
   end;

  if (FConfiguracoes.Geral.ModeloDF = moNFCe) then 
     DownloadNFe.Versao := NFCeDownloadNFe
  else 
     DownloadNFe.Versao := NFeDownloadNFe;

  DownloadNFe.GerarXML;

  FDadosMsg := DownloadNFe.Gerador.ArquivoFormatoXML;
  DownloadNFe.Free;

  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8_STD+'>', '', [rfReplaceAll] ) ;
  FDadosMsg := StringReplace( FDadosMsg, '<'+ENCODING_UTF8+'>', '', [rfReplaceAll] ) ;
  FDadosMsg := StringReplace( FDadosMsg, '<?xml version="1.0"?>', '', [rfReplaceAll] ) ;
end;

{ TWebServices }

procedure TWebServices.Cancela(AJustificativa: String);
begin
//retirado por recomenda��o do documento dispon�vel em http://www.nfe.fazenda.gov.br/PORTAL/docs/Consumo_Indevido_Aplicacao_Cliente_v1.00.pdf
{  if TACBrNFe( FACBrNFe ).Configuracoes.Geral.FormaEmissao = teNormal then
   begin
     if not(Self.StatusServico.Executar) then
      begin
        if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
           TACBrNFe( FACBrNFe ).OnGerarLog(Self.StatusServico.Msg);
        raise EACBrNFeException.Create(Self.StatusServico.Msg);
      end;
   end;}

  if not(Self.Consulta.Executar) then
     begin
       if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
          TACBrNFe( FACBrNFe ).OnGerarLog(Self.Consulta.Msg);
       raise EACBrNFeException.Create(Self.Consulta.Msg);
     end;

  Self.Cancelamento.NFeChave      := Self.Consulta.FNFeChave;
  Self.Cancelamento.Protocolo     := Self.Consulta.FProtocolo;
  Self.Cancelamento.Justificativa := AJustificativa;
  if not(Self.Cancelamento.Executar) then
     begin
       if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
          TACBrNFe( FACBrNFe ).OnGerarLog(Self.Cancelamento.Msg);
       raise EACBrNFeException.Create(Self.Cancelamento.Msg);
     end;
end;

procedure TWebServices.Inutiliza(CNPJ, AJustificativa: String; Ano, Modelo, Serie, NumeroInicial, NumeroFinal : Integer);
begin
//retirado por recomenda��o do documento dispon�vel em http://www.nfe.fazenda.gov.br/PORTAL/docs/Consumo_Indevido_Aplicacao_Cliente_v1.00.pdf
{  if TACBrNFe( FACBrNFe ).Configuracoes.Geral.FormaEmissao = teNormal then
   begin
     if not(Self.StatusServico.Executar) then
      begin
        if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
           TACBrNFe( FACBrNFe ).OnGerarLog(Self.StatusServico.Msg);
          raise EACBrNFeException.Create(Self.StatusServico.Msg);
      end;
   end;}
  CNPJ := OnlyNumber(CNPJ);
  if not ValidarCNPJ(CNPJ) then
     raise EACBrNFeException.Create('CNPJ '+CNPJ+' inv�lido.');

  Self.Inutilizacao.CNPJ   := CNPJ;
  Self.Inutilizacao.Modelo := Modelo;
  Self.Inutilizacao.Serie  := Serie;
  Self.Inutilizacao.Ano    := Ano;
  Self.Inutilizacao.NumeroInicial := NumeroInicial;
  Self.Inutilizacao.NumeroFinal   := NumeroFinal;
  Self.Inutilizacao.Justificativa := AJustificativa;

  if not(Self.Inutilizacao.Executar) then
     begin
       if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
          TACBrNFe( FACBrNFe ).OnGerarLog(Self.Inutilizacao.Msg);
       raise EACBrNFeException.Create(Self.Inutilizacao.Msg);
     end;
end;

constructor TWebServices.Create(AFNotaFiscalEletronica: TComponent);
begin
 inherited Create( AFNotaFiscalEletronica );
  FACBrNFe          := TACBrNFe(AFNotaFiscalEletronica);
  FStatusServico    := TNFeStatusServico.Create(AFNotaFiscalEletronica);
  FEnviar           := TNFeRecepcao.Create(AFNotaFiscalEletronica, TACBrNFe(AFNotaFiscalEletronica).NotasFiscais);
  FRetorno          := TNFeRetRecepcao.Create(AFNotaFiscalEletronica, TACBrNFe(AFNotaFiscalEletronica).NotasFiscais);
  FRecibo           := TNFeRecibo.Create(AFNotaFiscalEletronica);
  FConsulta         := TNFeConsulta.Create(AFNotaFiscalEletronica);
  FCancelamento     := TNFeCancelamento.Create(AFNotaFiscalEletronica);
  FInutilizacao     := TNFeInutilizacao.Create(AFNotaFiscalEletronica);
  FConsultaCadastro := TNFeConsultaCadastro.Create(AFNotaFiscalEletronica);
  FEnviaDPEC        := TNFeEnvDPEC.Create(AFNotaFiscalEletronica);
  FConsultaDPEC     := TNFeConsultaDPEC.Create(AFNotaFiscalEletronica);
  FCartaCorrecao    := TNFeCartaCorrecao.Create(AFNotaFiscalEletronica,TACBrNFe(AFNotaFiscalEletronica).CartaCorrecao.CCe);
  FEnvEvento        := TNFeEnvEvento.Create(AFNotaFiscalEletronica,TACBrNFe(AFNotaFiscalEletronica).EventoNFe);
  FConsNFeDest      := TNFeConsNFeDest.Create(AFNotaFiscalEletronica);
  FDownloadNFe      := TNFeDownloadNFe.Create(AFNotaFiscalEletronica, TACBrNFe(AFNotaFiscalEletronica).DownloadNFe.Download);
  FEnvioWebService  := TNFeEnvioWebService.Create(AFNotaFiscalEletronica);
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
  FEnviaDPEC.Free;
  FConsultaDPEC.Free;
  FCartaCorrecao.Free;
  FEnvEvento.Free;
  FConsNFeDest.Free;
  FDownloadNFe.Free;
  FEnvioWebService.Free;
  inherited;
end;

function TWebServices.Envia(ALote: Integer; const ASincrono: Boolean): Boolean;
begin
  Result := Envia(IntToStr(ALote), ASincrono);
end;

function TWebServices.Envia(ALote: String; const ASincrono: Boolean): Boolean;
begin
//retirado por recomenda��o do documento dispon�vel em http://www.nfe.fazenda.gov.br/PORTAL/docs/Consumo_Indevido_Aplicacao_Cliente_v1.00.pdf
{  if not(Self.StatusServico.Executar) then
     begin
       if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
          TACBrNFe( FACBrNFe ).OnGerarLog(Self.StatusServico.Msg);
       raise EACBrNFeException.Create(Self.StatusServico.Msg);
     end;      }

  self.Enviar.FLote := ALote;
  self.Enviar.FSincrono := ASincrono;

  if not(Self.Enviar.Executar) then
     begin
       if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
          TACBrNFe( FACBrNFe ).OnGerarLog(Self.Enviar.Msg);
       raise EACBrNFeException.Create(Self.Enviar.Msg);
     end;

  if (FConfiguracoes.Geral.ModeloDF = moNFe) or (not ASincrono) then 
   begin
    Self.Retorno.Recibo := Self.Enviar.Recibo;
    if not(Self.Retorno.Executar) then
       begin
         if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
            TACBrNFe( FACBrNFe ).OnGerarLog(Self.Retorno.Msg);
         raise EACBrNFeException.Create(Self.Retorno.Msg);
       end;
   end;

  Result := true;
end;

{ TNFeStatusServico }
function TNFeStatusServico.Executar: Boolean;
var
  NFeRetorno: TRetConsStatServ;
  aMsg: string;
  Texto : String;
  Acao  : TStringList ;
  Stream: TMemoryStream;
  StrStream: TStringStream;

  {$IFDEF ACBrNFeOpenSSL}
     HTTP: THTTPSend;
  {$ELSE}
     ReqResp: THTTPReqResp;
  {$ENDIF}
begin
  inherited Executar;

  Result := False;

  Acao := TStringList.Create;
  Stream := TMemoryStream.Create;

  Texto := '<?xml version="1.0" encoding="utf-8"?>';
  Texto := Texto + '<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">';
  Texto := Texto +   '<soap12:Header>';
  Texto := Texto +     '<nfeCabecMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2">';
  Texto := Texto +       '<cUF>'+IntToStr(FConfiguracoes.WebServices.UFCodigo)+'</cUF>';

  if (FConfiguracoes.Geral.ModeloDF = moNFCe) then 
     Texto := Texto + '<versaoDados>' + NFCeConsStatServ + '</versaoDados>'
  else 
     Texto := Texto + '<versaoDados>' + NFeConsStatServ + '</versaoDados>';

  Texto := Texto +     '</nfeCabecMsg>';
  Texto := Texto +   '</soap12:Header>';
  Texto := Texto +   '<soap12:Body>';
  Texto := Texto +     '<nfeDadosMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2">';
  Texto := Texto + FDadosMsg;
  Texto := Texto +     '</nfeDadosMsg>';
  Texto := Texto +   '</soap12:Body>';
  Texto := Texto +'</soap12:Envelope>';

  Acao.Text := Texto;

  {$IFDEF ACBrNFeOpenSSL}
     Acao.SaveToStream(Stream);
     HTTP := THTTPSend.Create;
  {$ELSE}
     ReqResp := THTTPReqResp.Create(nil);
     ConfiguraReqResp( ReqResp );
     ReqResp.URL := FURL;
     ReqResp.UseUTF8InHeader := True;

{     if FConfiguracoes.WebServices.UFCodigo = 29 then //Bahia est� usando SOAP ACTION diferente
        ReqResp.SoapAction := 'http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2/nfeStatusServicoNF2'
     else}
     ReqResp.SoapAction := 'http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2';
  {$ENDIF}

  try
    TACBrNFe( FACBrNFe ).SetStatus( stNFeStatusServico );
    if FConfiguracoes.Geral.Salvar then
     begin
       FPathArqEnv := FormatDateTime('yyyymmddhhnnss',Now)+'-ped-sta.xml';
       FConfiguracoes.Geral.Save(FPathArqEnv, FDadosMsg);
     end;

    try
      {$IFDEF ACBrNFeOpenSSL}
         HTTP.Document.LoadFromStream(Stream);
         ConfiguraHTTP(HTTP,'SOAPAction: "http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2"');
         HTTP.HTTPMethod('POST', FURL);
         StrStream := TStringStream.Create('');
         StrStream.CopyFrom(HTTP.Document, 0);
         FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
         FRetWS := SeparaDados( FRetornoWS,'nfeStatusServicoNF2Result');
         StrStream.Free;
      {$ELSE}
         ReqResp.Execute(Acao.Text, Stream);
         StrStream := TStringStream.Create('');
         StrStream.CopyFrom(Stream, 0);
         FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
         FRetWS := SeparaDados( FRetornoWS,'nfeStatusServicoNF2Result');
         StrStream.Free;
      {$ENDIF}
      NFeRetorno := TRetConsStatServ.Create;
      NFeRetorno.Leitor.Arquivo := FRetWS;
      NFeRetorno.LerXml;

      TACBrNFe( FACBrNFe ).SetStatus( stIdle );
      aMsg := //'Vers�o Leiaute : '+NFeRetorno.verAplic+LineBreak+
              'Ambiente : '+TpAmbToStr(NFeRetorno.tpAmb)+LineBreak+
              'Vers�o Aplicativo : '+NFeRetorno.verAplic+LineBreak+
              'Status C�digo : '+IntToStr(NFeRetorno.cStat)+LineBreak+
              'Status Descri��o : '+NFeRetorno.xMotivo+LineBreak+
              'UF : '+CodigoParaUF(NFeRetorno.cUF)+LineBreak+
              'Recebimento : '+DFeUtil.SeSenao(NFeRetorno.DhRecbto = 0, '', DateTimeToStr(NFeRetorno.dhRecbto))+LineBreak+
              'Tempo M�dio : '+IntToStr(NFeRetorno.TMed)+LineBreak+
              'Retorno : '+ DFeUtil.SeSenao(NFeRetorno.dhRetorno = 0, '', DateTimeToStr(NFeRetorno.dhRetorno))+LineBreak+
              'Observa��o : '+NFeRetorno.xObs+LineBreak;
      if FConfiguracoes.WebServices.Visualizar then
        ShowMessage(aMsg);

      if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
         TACBrNFe( FACBrNFe ).OnGerarLog(aMsg);

      FtpAmb    := NFeRetorno.tpAmb;
      FverAplic := NFeRetorno.verAplic;
      FcStat    := NFeRetorno.cStat;
      FxMotivo  := NFeRetorno.xMotivo;
      FcUF      := NFeRetorno.cUF;
      FdhRecbto := NFeRetorno.dhRecbto;
      FTMed     := NFeRetorno.TMed;
      FdhRetorno:= NFeRetorno.dhRetorno;
      FxObs     := NFeRetorno.xObs;

      if TACBrNFe( FACBrNFe ).Configuracoes.WebServices.AjustaAguardaConsultaRet then
         TACBrNFe( FACBrNFe ).Configuracoes.WebServices.AguardarConsultaRet := FTMed*1000;

      FMsg   := NFeRetorno.XMotivo+ LineBreak+NFeRetorno.XObs;
      Result := (NFeRetorno.CStat = 107);
      NFeRetorno.Free;

      if FConfiguracoes.Geral.Salvar then
       begin
         FPathArqResp := FormatDateTime('yyyymmddhhnnss',Now)+'-sta.xml';
         FConfiguracoes.Geral.Save(FPathArqResp, FRetWS);
       end;

    except on E: Exception do
      begin
       if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
          TACBrNFe( FACBrNFe ).OnGerarLog('WebService Consulta Status servi�o:'+LineBreak+
                                          '- Inativo ou Inoperante tente novamente.'+LineBreak+
                                          '- '+E.Message);
       raise EACBrNFeException.Create('WebService Consulta Status servi�o:'+LineBreak+
                              '- Inativo ou Inoperante tente novamente.'+LineBreak+
                              '- '+E.Message);
      end;
    end;
  finally
    {$IFDEF ACBrNFeOpenSSL}
      HTTP.Free;
    {$ELSE}
      ReqResp.Free;
    {$ENDIF}
    Acao.Free;
    Stream.Free;
    NotaUtil.ConfAmbiente;
    TACBrNFe( FACBrNFe ).SetStatus( stIdle );
  end;
end;

{ TNFeRecepcao }
constructor TNFeRecepcao.Create(AOwner : TComponent;
  ANotasFiscais: TNotasFiscais);
begin
  inherited Create(AOwner);
  FNotasFiscais := ANotasFiscais;
end;

function TNFeRecepcao.Executar: Boolean;
var
  NFeRetorno: TretEnvNFe;
  NFeRetornoSincrono: TRetConsSitNFe;
  chNFe, SoapAction, aMsg: string;
  nfeAutorizacaoLote : boolean;
  Texto : string;
  Acao  : TStringList ;
  Stream: TMemoryStream;
  StrStream: TStringStream;
  i: integer;
  AProcNFe: TProcNFe;

  {$IFDEF ACBrNFeOpenSSL}
     HTTP: THTTPSend;
  {$ELSE}
     ReqResp: THTTPReqResp;
  {$ENDIF}
begin
  inherited Executar;

  Acao := TStringList.Create;
  Stream := TMemoryStream.Create;

  if (FConfiguracoes.Geral.ModeloDF = moNFCe) and
     (FConfiguracoes.WebServices.UFCodigo <> 13)  then
   begin
     SoapAction := 'http://www.portalfiscal.inf.br/nfe/wsdl/NfeAutorizacao';
     nfeAutorizacaoLote := True;
   end
  else
   begin
     SoapAction := 'http://www.portalfiscal.inf.br/nfe/wsdl/NfeRecepcao2';
     nfeAutorizacaoLote := False;
   end;


  Texto := '<?xml version="1.0" encoding="utf-8"?>';
  Texto := Texto + '<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">';
  Texto := Texto +   '<soap12:Header>';
  Texto := Texto +     '<nfeCabecMsg xmlns="'+SoapAction+'">';
  Texto := Texto +       '<cUF>'+IntToStr(FConfiguracoes.WebServices.UFCodigo)+'</cUF>';

  if (FConfiguracoes.Geral.ModeloDF = moNFCe) then
     Texto := Texto + '<versaoDados>' + NFCeEnvi + '</versaoDados>'
  else 
     Texto := Texto + '<versaoDados>' + NFenviNFe + '</versaoDados>';

  Texto := Texto +     '</nfeCabecMsg>';
  Texto := Texto +   '</soap12:Header>';
  Texto := Texto +   '<soap12:Body>';
  Texto := Texto +     '<nfeDadosMsg xmlns="'+SoapAction+'">';
  Texto := Texto + FDadosMsg;
  Texto := Texto +     '</nfeDadosMsg>';
  Texto := Texto +   '</soap12:Body>';
  Texto := Texto +'</soap12:Envelope>';

  Acao.Text := Texto;

//  if assigned(TACBrNFe( FACBrNFe ).WebServices.Retorno.NFeRetorno) then
//     TACBrNFe( FACBrNFe ).WebServices.Retorno.NFeRetorno.Free;

  {$IFDEF ACBrNFeOpenSSL}
     Acao.SaveToStream(Stream);
     HTTP := THTTPSend.Create;
  {$ELSE}
     ReqResp := THTTPReqResp.Create(nil);
     ConfiguraReqResp( ReqResp );
     ReqResp.URL := FURL;
     ReqResp.UseUTF8InHeader := True;
     ReqResp.SoapAction := SoapAction;
  {$ENDIF}

  try
    TACBrNFe( FACBrNFe ).SetStatus( stNFeRecepcao );
    if FConfiguracoes.Geral.Salvar then
     begin
       FPathArqEnv := Lote+'-env-lot.xml';
       FConfiguracoes.Geral.Save(FPathArqEnv, FDadosMsg);
     end;
    {$IFDEF ACBrNFeOpenSSL}
       HTTP.Document.LoadFromStream(Stream);
       ConfiguraHTTP(HTTP,'SOAPAction: "'+SoapAction+'"');
       HTTP.HTTPMethod('POST', FURL);

       StrStream := TStringStream.Create('');
       StrStream.CopyFrom(HTTP.Document, 0);
       FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
       if nfeAutorizacaoLote then
          FRetWS := SeparaDados( FRetornoWS,'nfeAutorizacaoLoteResult')
       else
          FRetWS := SeparaDados( FRetornoWS,'nfeRecepcaoLote2Result');
       StrStream.Free;
    {$ELSE}
       ReqResp.Execute(Acao.Text, Stream);
       StrStream := TStringStream.Create('');
       StrStream.CopyFrom(Stream, 0);
       FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
       if nfeAutorizacaoLote then
          FRetWS := SeparaDados( FRetornoWS,'nfeAutorizacaoLoteResult')
       else
          FRetWS := SeparaDados( FRetornoWS,'nfeRecepcaoLote2Result');
       StrStream.Free;
    {$ENDIF}

    if (FConfiguracoes.Geral.ModeloDF = moNFCe) and FSincrono then
     begin
       NFeRetornoSincrono := TRetConsSitNFe.Create;
       
       if pos('retEnviNFe',FRetWS) > 0 then
          NFeRetornoSincrono.Leitor.Arquivo := StringReplace(FRetWS,'retEnviNFe','retConsSitNFe',[rfReplaceAll,rfIgnoreCase])
       else if pos('retConsReciNFe',FRetWS) > 0 then
          NFeRetornoSincrono.Leitor.Arquivo := StringReplace(FRetWS,'retConsReciNFe','retConsSitNFe',[rfReplaceAll,rfIgnoreCase])
       else
          NFeRetornoSincrono.Leitor.Arquivo := FRetWS;

       NFeRetornoSincrono.LerXml;

       TACBrNFe( FACBrNFe ).SetStatus( stIdle );

       aMsg := 'Ambiente : '+TpAmbToStr(NFeRetornoSincrono.TpAmb)+LineBreak+
               'Vers�o Aplicativo : '+NFeRetornoSincrono.verAplic+LineBreak+
               'Status C�digo : '+IntToStr(NFeRetornoSincrono.protNFe.cStat)+LineBreak+
               'Status Descri��o : '+NFeRetornoSincrono.protNFe.xMotivo+LineBreak+
               'UF : '+CodigoParaUF(NFeRetornoSincrono.cUF)+LineBreak+
               'dhRecbto : '+DateTimeToStr(NFeRetornoSincrono.dhRecbto)+LineBreak+
               'chNFe : '+NFeRetornoSincrono.chNfe+LineBreak;

       if FConfiguracoes.WebServices.Visualizar then
          ShowMessage(aMsg);

       if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
          TACBrNFe( FACBrNFe ).OnGerarLog(aMsg);

       FTpAmb    := NFeRetornoSincrono.TpAmb;
       FverAplic := NFeRetornoSincrono.verAplic;
       FcStat    := NFeRetornoSincrono.protNFe.cStat;
       FcUF      := NFeRetornoSincrono.cUF;
       FMsg      := NFeRetornoSincrono.protNFe.xMotivo;
       FxMotivo  := NFeRetornoSincrono.protNFe.xMotivo;
       chNFe     := NFeRetornoSincrono.ProtNFe.chNFe;

       Result := (NFeRetornoSincrono.cStat = 104);

       if NFeRetornoSincrono.cStat = 104 then
        begin
          for i:= 0 to TACBrNFe( FACBrNFe ).NotasFiscais.Count-1 do
           begin
             if StringReplace(TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.infNFe.ID,'NFe','',[rfIgnoreCase]) = chNFe then
              begin
                TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].Confirmada           := (NFeRetornoSincrono.protNFe.cStat in [100, 150]);
                TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].Msg                  := NFeRetornoSincrono.protNFe.xMotivo;
                TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.procNFe.tpAmb    := NFeRetornoSincrono.tpAmb;
                TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.procNFe.verAplic := NFeRetornoSincrono.verAplic;
                TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.procNFe.chNFe    := NFeRetornoSincrono.ProtNFe.chNFe;
                TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.procNFe.dhRecbto := NFeRetornoSincrono.protNFe.dhRecbto;
                TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.procNFe.nProt    := NFeRetornoSincrono.ProtNFe.nProt;
                TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.procNFe.digVal   := NFeRetornoSincrono.protNFe.digVal;
                TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.procNFe.cStat    := NFeRetornoSincrono.protNFe.cStat;
                TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.procNFe.xMotivo  := NFeRetornoSincrono.protNFe.xMotivo;
                if (FileExists(PathWithDelim(FConfiguracoes.Geral.PathSalvar) + chNFe + '-nfe.xml')) or
                    DFeUtil.NaoEstaVazio(TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NomeArq) then
                 begin
                   AProcNFe:=TProcNFe.Create;

                   if DFeUtil.NaoEstaVazio(TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NomeArq) then
                      AProcNFe.PathNFe:=TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NomeArq
                   else
                      AProcNFe.PathNFe:=PathWithDelim(FConfiguracoes.Geral.PathSalvar) + chNFe + '-nfe.xml';

                   AProcNFe.PathRetConsSitNFe:='';
                   AProcNFe.PathRetConsReciNFe:='';
//                   AProcNFe.PathRetConsSitNFe:=PathWithDelim(FConfiguracoes.Geral.PathSalvar) + chNFe + '-sit.xml';

                   if (FConfiguracoes.Geral.ModeloDF = moNFCe) then
                      AProcNFe.Versao := NFCeEnvi
                   else
                      AProcNFe.Versao := NFenviNFe;

                   AProcNFe.GerarXML;
                   if DFeUtil.NaoEstaVazio(AProcNFe.Gerador.ArquivoFormatoXML) then
                      AProcNFe.Gerador.SalvarArquivo(AProcNFe.PathNFe);

                   AProcNFe.Free;
                 end;

             if FConfiguracoes.Arquivos.Salvar then
              begin
                if FConfiguracoes.Arquivos.EmissaoPathNFe then
                   TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].SaveToFile(PathWithDelim(FConfiguracoes.Arquivos.GetPathNFe(TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.Ide.dEmi))+StringReplace(TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.InfNFe.Id,'NFe','',[rfIgnoreCase])+'-nfe.xml')
                else
                   TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].SaveToFile(PathWithDelim(FConfiguracoes.Arquivos.GetPathNFe)+StringReplace(TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.InfNFe.Id,'NFe','',[rfIgnoreCase])+'-nfe.xml');
                end;
              end;
          break;
        end;
//        Result :=  Confirma(NFeRetornoS.ProtNFe);
//        FChaveNfe  := NFeRetornoS.ProtNFe.Items[0].chNFe;
//        FProtocolo := NFeRetornoS.ProtNFe.Items[0].nProt;
//        FcStat     := NFeRetornoS.ProtNFe.Items[0].cStat;
//        FMsg       := NFeRetornoS.ProtNFe.Items[0].xMotivo;
//        FxMotivo   := NFeRetornoS.ProtNFe.Items[0].xMotivo;
        end;

      NFeRetornoSincrono.Free;

      if  FConfiguracoes.Geral.Salvar then
       begin
         FPathArqResp := FRecibo+'-pro-rec.xml';
         FConfiguracoes.Geral.Save(FPathArqResp, FRetWS);
       end;
     end
    else
     begin
       NFeRetorno := TretEnvNFe.Create;
       NFeRetorno.Leitor.Arquivo := FRetWS;
       NFeRetorno.LerXml;

       TACBrNFe( FACBrNFe ).SetStatus( stIdle );
       aMsg := //'Vers�o Leiaute : '+NFeRetorno.Versao+LineBreak+
               'Ambiente : '+TpAmbToStr(NFeRetorno.TpAmb)+LineBreak+
               'Vers�o Aplicativo : '+NFeRetorno.verAplic+LineBreak+
               'Status C�digo : '+IntToStr(NFeRetorno.cStat)+LineBreak+
               'Status Descri��o : '+NFeRetorno.xMotivo+LineBreak+
               'UF : '+CodigoParaUF(NFeRetorno.cUF)+LineBreak+
               'Recibo : '+NFeRetorno.infRec.nRec+LineBreak+
               'Recebimento : '+DFeUtil.SeSenao(NFeRetorno.InfRec.dhRecbto = 0, '', DateTimeToStr(NFeRetorno.InfRec.dhRecbto))+LineBreak+
               'Tempo M�dio : '+IntToStr(NFeRetorno.InfRec.TMed)+LineBreak;
       if FConfiguracoes.WebServices.Visualizar then
          ShowMessage(aMsg);

       if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
          TACBrNFe( FACBrNFe ).OnGerarLog(aMsg);

       FTpAmb    := NFeRetorno.TpAmb;
       FverAplic := NFeRetorno.verAplic;
       FcStat    := NFeRetorno.cStat;
       FxMotivo  := NFeRetorno.xMotivo;
       FdhRecbto := NFeRetorno.infRec.dhRecbto;
       FTMed     := NFeRetorno.infRec.tMed;
       FcUF      := NFeRetorno.cUF;

       FMsg    := NFeRetorno.xMotivo;
       FRecibo := NFeRetorno.infRec.nRec;
       Result := (NFeRetorno.CStat = 103);

       NFeRetorno.Free;

       if FConfiguracoes.Geral.Salvar then
        begin
          FPathArqResp := Lote+'-rec.xml';
          FConfiguracoes.Geral.Save(FPathArqResp, FRetWS);
        end;
     end;


  finally
    {$IFDEF ACBrNFeOpenSSL}
       HTTP.Free;
    {$ELSE}
      ReqResp.Free;
    {$ENDIF}
    Acao.Free;
    Stream.Free;
    NotaUtil.ConfAmbiente;
    TACBrNFe( FACBrNFe ).SetStatus( stIdle );
  end;
end;

function TNFeRecepcao.GetLote: String;
begin
  Result := Trim(FLote);
end;

{ TNFeRetRecepcao }
function TNFeRetRecepcao.Confirma(AInfProt: TProtNFeCollection): Boolean;
var
  i,j : Integer;
  AProcNFe: TProcNFe;
begin
  Result := False;

  //Setando os retornos das notas fiscais;
  for i:= 0 to AInfProt.Count-1 do
  begin
    for j:= 0 to FNotasFiscais.Count-1 do
    begin
      if AInfProt.Items[i].chNFe = StringReplace(FNotasFiscais.Items[j].NFe.InfNFe.Id,'NFe','',[rfIgnoreCase]) then
       begin
         FNotasFiscais.Items[j].Confirmada := (AInfProt.Items[i].cStat in [100,150]);
         FNotasFiscais.Items[j].Msg        := AInfProt.Items[i].xMotivo;
         FNotasFiscais.Items[j].NFe.procNFe.tpAmb    := AInfProt.Items[i].tpAmb;
         FNotasFiscais.Items[j].NFe.procNFe.verAplic := AInfProt.Items[i].verAplic;
         FNotasFiscais.Items[j].NFe.procNFe.chNFe    := AInfProt.Items[i].chNFe;
         FNotasFiscais.Items[j].NFe.procNFe.dhRecbto := AInfProt.Items[i].dhRecbto;
         FNotasFiscais.Items[j].NFe.procNFe.nProt    := AInfProt.Items[i].nProt;
         FNotasFiscais.Items[j].NFe.procNFe.digVal   := AInfProt.Items[i].digVal;
         FNotasFiscais.Items[j].NFe.procNFe.cStat    := AInfProt.Items[i].cStat;
         FNotasFiscais.Items[j].NFe.procNFe.xMotivo  := AInfProt.Items[i].xMotivo;
         if FConfiguracoes.Geral.Salvar or DFeUtil.NaoEstaVazio(FNotasFiscais.Items[j].NomeArq) then
          begin
            if FileExists(PathWithDelim(FConfiguracoes.Geral.PathSalvar)+AInfProt.Items[i].chNFe+'-nfe.xml') and
               FileExists(PathWithDelim(FConfiguracoes.Geral.PathSalvar)+FNFeRetorno.nRec+'-pro-rec.xml') then
             begin
               AProcNFe:=TProcNFe.Create;
               AProcNFe.PathNFe:=PathWithDelim(FConfiguracoes.Geral.PathSalvar)+AInfProt.Items[i].chNFe+'-nfe.xml';
               AProcNFe.PathRetConsReciNFe:=PathWithDelim(FConfiguracoes.Geral.PathSalvar)+FNFeRetorno.nRec+'-pro-rec.xml';


               if (FConfiguracoes.Geral.ModeloDF = moNFCe) then 
                  AProcNFe.Versao := NFCeEnvi
               else 
                  AProcNFe.Versao := NFenviNFe;

               AProcNFe.GerarXML;
               if DFeUtil.NaoEstaVazio(AProcNFe.Gerador.ArquivoFormatoXML) then
                begin
                  if DFeUtil.NaoEstaVazio(FNotasFiscais.Items[j].NomeArq) then
                     AProcNFe.Gerador.SalvarArquivo(FNotasFiscais.Items[j].NomeArq)
                  else
                     AProcNFe.Gerador.SalvarArquivo(PathWithDelim(FConfiguracoes.Geral.PathSalvar)+AInfProt.Items[i].chNFe+'-nfe.xml');
                end;
               AProcNFe.Free;
             end;
          end;
         if FConfiguracoes.Arquivos.Salvar then
            if FConfiguracoes.Arquivos.EmissaoPathNFe then
               FNotasFiscais.Items[j].SaveToFile(PathWithDelim(FConfiguracoes.Arquivos.GetPathNFe(FNotasFiscais.Items[j].NFe.Ide.dEmi))+StringReplace(FNotasFiscais.Items[j].NFe.InfNFe.Id,'NFe','',[rfIgnoreCase])+'-nfe.xml')
            else
               FNotasFiscais.Items[j].SaveToFile(PathWithDelim(FConfiguracoes.Arquivos.GetPathNFe)+StringReplace(FNotasFiscais.Items[j].NFe.InfNFe.Id,'NFe','',[rfIgnoreCase])+'-nfe.xml');
         break;
       end;
    end;
  end;

  //Verificando se existe alguma nota confirmada
  for i:= 0 to FNotasFiscais.Count-1 do
  begin
    if FNotasFiscais.Items[i].Confirmada then
    begin
      Result := True;
      break;
    end;
  end;

  //Verificando se existe alguma nota nao confirmada
  for i:= 0 to FNotasFiscais.Count-1 do
  begin
    if not(FNotasFiscais.Items[i].Confirmada) then
    begin
      FMsg   := 'Nota(s) n�o confirmadas:'+LineBreak;
      break;
    end;
  end;

  //Montando a mensagem de retorno para as notas nao confirmadas
  for i:= 0 to FNotasFiscais.Count-1 do
  begin
    if not(FNotasFiscais.Items[i].Confirmada) then
      FMsg:= FMsg+IntToStr(FNotasFiscais.Items[i].NFe.Ide.nNF)+'->'+FNotasFiscais.Items[i].Msg+LineBreak;
  end;
end;

constructor TNFeRetRecepcao.Create(AOwner : TComponent;
  ANotasFiscais: TNotasFiscais);
begin
  inherited Create(AOwner);
  FNotasFiscais := ANotasFiscais;
end;

destructor TNFeRetRecepcao.destroy;
begin
   if assigned(FNFeRetorno) then
      FNFeRetorno.Free;
   inherited;
end;

function TNFeRetRecepcao.Executar: Boolean;
  function Processando: Boolean;
  var
    SoapAction, aMsg: string;
    nfeAutorizacaoLote : boolean;
    Texto : String;
    Acao  : TStringList ;
    Stream: TMemoryStream;
    StrStream: TStringStream;
    {$IFDEF ACBrNFeOpenSSL}
       HTTP: THTTPSend;
    {$ELSE}
       ReqResp: THTTPReqResp;
    {$ENDIF}
  begin
    Acao := TStringList.Create;
    Stream := TMemoryStream.Create;

    if assigned(FNFeRetorno) then
       FNFeRetorno.Free;

    if (FConfiguracoes.Geral.ModeloDF = moNFCe) and
       (FConfiguracoes.WebServices.UFCodigo <> 13)  then
     begin
       SoapAction := 'http://www.portalfiscal.inf.br/nfe/wsdl/NfeRetAutorizacao';
       nfeAutorizacaoLote := True;
     end
    else
     begin
       SoapAction := 'http://www.portalfiscal.inf.br/nfe/wsdl/NfeRetRecepcao2';
       nfeAutorizacaoLote := False;
     end;


    Texto := '<?xml version="1.0" encoding="utf-8"?>';
    Texto := Texto + '<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">';
    Texto := Texto +   '<soap12:Header>';
    Texto := Texto +     '<nfeCabecMsg xmlns="'+SoapAction+'">';
    Texto := Texto +       '<cUF>'+IntToStr(FConfiguracoes.WebServices.UFCodigo)+'</cUF>';

    if (FConfiguracoes.Geral.ModeloDF = moNFCe) then 
       Texto := Texto + '<versaoDados>' + NFCeConsReci + '</versaoDados>'
    else 
       Texto := Texto + '<versaoDados>' + NFeConsReciNFe + '</versaoDados>';

    Texto := Texto +     '</nfeCabecMsg>';
    Texto := Texto +   '</soap12:Header>';
    Texto := Texto +   '<soap12:Body>';
    Texto := Texto +     '<nfeDadosMsg xmlns="'+SoapAction+'">';
    Texto := Texto + FDadosMsg;
    Texto := Texto +     '</nfeDadosMsg>';
    Texto := Texto +   '</soap12:Body>';
    Texto := Texto +'</soap12:Envelope>';

    Acao.Text := Texto;

    {$IFDEF ACBrNFeOpenSSL}
       Acao.SaveToStream(Stream);
       HTTP := THTTPSend.Create;
    {$ELSE}
       ReqResp := THTTPReqResp.Create(nil);
       ConfiguraReqResp( ReqResp );
       ReqResp.URL := FURL;
       ReqResp.UseUTF8InHeader := True;
       ReqResp.SoapAction := SoapAction;
    {$ENDIF}

    FNFeRetorno := TRetConsReciNFe.Create;
    try
      TACBrNFe( FACBrNFe ).SetStatus( stNfeRetRecepcao );

      if FConfiguracoes.Geral.Salvar then
       begin
         FPathArqEnv := Recibo+'-ped-rec.xml';
         FConfiguracoes.Geral.Save(FPathArqEnv, FDadosMsg);
       end;
      {$IFDEF ACBrNFeOpenSSL}
         HTTP.Document.LoadFromStream(Stream);
         ConfiguraHTTP(HTTP,'SOAPAction: "'+SoapAction+'"');
         HTTP.HTTPMethod('POST', FURL);

         StrStream := TStringStream.Create('');
         StrStream.CopyFrom(HTTP.Document, 0);
         FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
         if nfeAutorizacaoLote then
           FRetWS := SeparaDados( FRetornoWS,'nfeRetAutorizacaoLoteResult')
         else
           FRetWS := SeparaDados( FRetornoWS,'nfeRetRecepcao2Result');
         StrStream.Free;
      {$ELSE}
         ReqResp.Execute(Acao.Text, Stream);
         StrStream := TStringStream.Create('');
         StrStream.CopyFrom(Stream, 0);
         FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
         if nfeAutorizacaoLote then
           FRetWS := SeparaDados( FRetornoWS,'nfeRetAutorizacaoLoteResult')
         else
           FRetWS := SeparaDados( FRetornoWS,'nfeRetRecepcao2Result');
         StrStream.Free;
      {$ENDIF}
      if FConfiguracoes.Geral.Salvar then
       begin
         FPathArqResp := Recibo+'-pro-rec.xml';
         FConfiguracoes.Geral.Save(FPathArqResp, FRetWS);
       end;
      FNFeRetorno.Leitor.Arquivo := FRetWS;
      FNFeRetorno.LerXML;

      TACBrNFe( FACBrNFe ).SetStatus( stIdle );
      aMsg := //'Vers�o Leiaute : '+FNFeRetorno.Versao+LineBreak+
              'Ambiente : '+TpAmbToStr(FNFeRetorno.TpAmb)+LineBreak+
              'Vers�o Aplicativo : '+FNFeRetorno.verAplic+LineBreak+
              'Recibo : '+FNFeRetorno.nRec+LineBreak+
              'Status C�digo : '+IntToStr(FNFeRetorno.cStat)+LineBreak+
              'Status Descri��o : '+FNFeRetorno.xMotivo+LineBreak+
              'UF : '+CodigoParaUF(FNFeRetorno.cUF)+LineBreak+
              'cMsg : '+IntToStr(FNFeRetorno.cMsg)+LineBreak+
              'xMsg : '+FNFeRetorno.xMsg+LineBreak;
      if FConfiguracoes.WebServices.Visualizar then
         ShowMessage(aMsg);

      if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
         TACBrNFe( FACBrNFe ).OnGerarLog(aMsg);

      FTpAmb    := FNFeRetorno.TpAmb;
      FverAplic := FNFeRetorno.verAplic;
      FcStat    := FNFeRetorno.cStat;
      FcUF      := FNFeRetorno.cUF;
      FMsg      := FNFeRetorno.xMotivo;
      FxMotivo  := FNFeRetorno.xMotivo;
      FcMsg     := FNFeRetorno.cMsg;
      FxMsg     := FNFeRetorno.xMsg;

      Result := FNFeRetorno.CStat = 105;
      if FNFeRetorno.CStat = 104 then
      begin
         FMsg   := FNFeRetorno.ProtNFe.Items[0].xMotivo;
         FxMotivo  := FNFeRetorno.ProtNFe.Items[0].xMotivo;
      end;

    finally
      {$IFDEF ACBrNFeOpenSSL}
        HTTP.Free;
      {$ELSE}
        ReqResp.Free;
      {$ENDIF}
      Acao.Free;
      Stream.Free;
      NotaUtil.ConfAmbiente;
      TACBrNFe( FACBrNFe ).SetStatus( stIdle );
    end;
  end;

var
  vCont: Integer;
begin
  inherited Executar;
  Result := False;

  TACBrNFe( FACBrNFe ).SetStatus( stNfeRetRecepcao );
  Sleep(TACBrNFe( FACBrNFe ).Configuracoes.WebServices.AguardarConsultaRet);
  vCont := 1000;
  while Processando do
  begin
    if TACBrNFe( FACBrNFe ).Configuracoes.WebServices.IntervaloTentativas > 0 then
       sleep(TACBrNFe( FACBrNFe ).Configuracoes.WebServices.IntervaloTentativas)
    else
       sleep(vCont);

    if vCont > (TACBrNFe( FACBrNFe ).Configuracoes.WebServices.Tentativas*1000) then
      break;

    vCont := vCont +1000;
  end;
  TACBrNFe( FACBrNFe ).SetStatus( stIdle );

  if FNFeRetorno.CStat = 104 then
   begin
    Result := Confirma(FNFeRetorno.ProtNFe);
    fChaveNfe  := FNFeRetorno.ProtNFe.Items[0].chNFe;
    fProtocolo := FNFeRetorno.ProtNFe.Items[0].nProt;
    fcStat     := FNFeRetorno.ProtNFe.Items[0].cStat;
   end;
end;

{ TNFeRecibo }
constructor TNFeRecibo.Create(AOwner : TComponent);
begin
  inherited Create(AOwner);
end;

destructor TNFeRecibo.destroy;
begin
   if assigned(FNFeRetorno) then
      FNFeRetorno.Free;
  inherited;
end;

function TNFeRecibo.Executar: Boolean;
var
 aMsg: string;
 Texto : String;
 Acao  : TStringList ;
 Stream: TMemoryStream;
 StrStream: TStringStream;
 {$IFDEF ACBrNFeOpenSSL}
    HTTP: THTTPSend;
 {$ELSE}
    ReqResp: THTTPReqResp;
 {$ENDIF}
begin
  if assigned(FNFeRetorno) then
    FNFeRetorno.Free;
    
  inherited Executar;

  Acao := TStringList.Create;
  Stream := TMemoryStream.Create;

  Texto := '<?xml version="1.0" encoding="utf-8"?>';
  Texto := Texto + '<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">';
  Texto := Texto +   '<soap12:Header>';
  Texto := Texto +     '<nfeCabecMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/NfeRetRecepcao2">';
  Texto := Texto +       '<cUF>'+IntToStr(FConfiguracoes.WebServices.UFCodigo)+'</cUF>';
  Texto := Texto +       '<versaoDados>'+NFeconsReciNFe+'</versaoDados>';
  Texto := Texto +     '</nfeCabecMsg>';
  Texto := Texto +   '</soap12:Header>';
  Texto := Texto +   '<soap12:Body>';
  Texto := Texto +     '<nfeDadosMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/NfeRetRecepcao2">';
  Texto := Texto + FDadosMsg;
  Texto := Texto +     '</nfeDadosMsg>';
  Texto := Texto +   '</soap12:Body>';
  Texto := Texto +'</soap12:Envelope>';

  Acao.Text := Texto;

  {$IFDEF ACBrNFeOpenSSL}
     Acao.SaveToStream(Stream);  
     HTTP := THTTPSend.Create;
  {$ELSE}
     ReqResp := THTTPReqResp.Create(nil);
     ConfiguraReqResp( ReqResp );
     ReqResp.URL := FURL;
     ReqResp.UseUTF8InHeader := True;
     ReqResp.SoapAction := 'http://www.portalfiscal.inf.br/nfe/wsdl/NfeRetRecepcao2';
  {$ENDIF}

 FNFeRetorno := TRetConsReciNFe.Create;
 try
   TACBrNFe( FACBrNFe ).SetStatus( stNfeRetRecepcao );

   if FConfiguracoes.Geral.Salvar then
    begin
      FPathArqEnv := Recibo+'-ped-rec.xml';
      FConfiguracoes.Geral.Save(FPathArqEnv, FDadosMsg);
    end;
   {$IFDEF ACBrNFeOpenSSL}
      HTTP.Document.LoadFromStream(Stream);
      ConfiguraHTTP(HTTP,'SOAPAction: "http://www.portalfiscal.inf.br/nfe/wsdl/NfeRetRecepcao2"');
      HTTP.HTTPMethod('POST', FURL);

      StrStream := TStringStream.Create('');
      StrStream.CopyFrom(HTTP.Document, 0);
      FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
      FRetWS := SeparaDados( FRetornoWS,'nfeRetRecepcao2Result');
      StrStream.Free;
   {$ELSE}
      ReqResp.Execute(Acao.Text, Stream);
      StrStream := TStringStream.Create('');
      StrStream.CopyFrom(Stream, 0);
      FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
      FRetWS := SeparaDados( FRetornoWS,'nfeRetRecepcao2Result');
      StrStream.Free;
   {$ENDIF}
   if FConfiguracoes.Geral.Salvar then
    begin
      FPathArqResp := Recibo+'-pro-rec.xml';
      FConfiguracoes.Geral.Save(FPathArqResp, FRetWS);
    end;
   FNFeRetorno.Leitor.Arquivo := FRetWS;
   FNFeRetorno.LerXML;

   TACBrNFe( FACBrNFe ).SetStatus( stIdle );
   aMsg := //'Vers�o Leiaute : '+FNFeRetorno.Versao+LineBreak+
           'Ambiente : '+TpAmbToStr(FNFeRetorno.TpAmb)+LineBreak+
           'Vers�o Aplicativo : '+FNFeRetorno.verAplic+LineBreak+
           'Recibo : '+FNFeRetorno.nRec+LineBreak+
           'Status C�digo : '+IntToStr(FNFeRetorno.cStat)+LineBreak+
           'Status Descri��o : '+FNFeRetorno.ProtNFe.Items[0].xMotivo+LineBreak+
           'UF : '+CodigoParaUF(FNFeRetorno.cUF)+LineBreak;
   if FConfiguracoes.WebServices.Visualizar then
     ShowMessage(aMsg);

   if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
      TACBrNFe( FACBrNFe ).OnGerarLog(aMsg);

   FTpAmb    := FNFeRetorno.TpAmb;
   FverAplic := FNFeRetorno.verAplic;
   FcStat    := FNFeRetorno.cStat;
   FxMotivo  := FNFeRetorno.xMotivo;
   FcUF      := FNFeRetorno.cUF;
   FxMsg     := FNFeRetorno.xMsg;
   FcMsg     := FNFeRetorno.cMsg;

   Result := FNFeRetorno.CStat = 104;
   FMsg   := FNFeRetorno.xMotivo;

 finally
   {$IFDEF ACBrNFeOpenSSL}
      HTTP.Free;
   {$ELSE}
      ReqResp.Free;
   {$ENDIF}
   Acao.Free;
   Stream.Free;
   NotaUtil.ConfAmbiente;
   TACBrNFe( FACBrNFe ).SetStatus( stIdle );
 end;
end;

{ TNFeConsulta }
constructor TNFeConsulta.Create(AOwner: TComponent);
begin
  FConfiguracoes := TConfiguracoes( TACBrNFe( AOwner ).Configuracoes );
  FACBrNFe       := TACBrNFe( AOwner );

  FprotNFe:= TProcNFe.Create;
  FretCancNFe:= TRetCancNFe.Create;
  FprocEventoNFe := TRetEventoNFeCollection.Create(AOwner);
end;

destructor TNFeConsulta.Destroy;
begin
  FprotNFe.Free;
  FretCancNFe.Free;
  if Assigned(FprocEventoNFe) then
    FprocEventoNFe.Free;
end;

function TNFeConsulta.Executar: Boolean;
var
  NFeRetorno: TRetConsSitNFe;
  aMsg, aEventos: WideString;
  AProcNFe: TProcNFe;
  i, j : Integer;
  Texto : String;
  Acao  : TStringList ;
  Stream: TMemoryStream;
  StrStream: TStringStream;
  wAtualiza, NFCancelada: Boolean;

  {$IFDEF ACBrNFeOpenSSL}
     HTTP: THTTPSend;
  {$ELSE}
     ReqResp: THTTPReqResp;
  {$ENDIF}
begin
  inherited Executar;

  Acao := TStringList.Create;
  Stream := TMemoryStream.Create;

  Texto := '<?xml version="1.0" encoding="utf-8"?>';
  Texto := Texto + '<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">';
  Texto := Texto +   '<soap12:Header>';
  Texto := Texto +     '<nfeCabecMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2">';
  Texto := Texto +       '<cUF>'+IntToStr(FConfiguracoes.WebServices.UFCodigo)+'</cUF>';

  if (FConfiguracoes.Geral.ModeloDF = moNFCe) then 
     Texto := Texto + '<versaoDados>' + NFCeConsSit + '</versaoDados>'
  else 
     Texto := Texto + '<versaoDados>' + NFeConsSitNFe + '</versaoDados>';

  Texto := Texto +     '</nfeCabecMsg>';
  Texto := Texto +   '</soap12:Header>';
  Texto := Texto +   '<soap12:Body>';
  Texto := Texto +     '<nfeDadosMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2">';
  Texto := Texto + FDadosMsg;
  Texto := Texto +     '</nfeDadosMsg>';
  Texto := Texto +   '</soap12:Body>';
  Texto := Texto +'</soap12:Envelope>';

  Acao.Text := Texto;

  {$IFDEF ACBrNFeOpenSSL}
     Acao.SaveToStream(Stream);
     HTTP := THTTPSend.Create;
  {$ELSE}
     ReqResp := THTTPReqResp.Create(nil);
     ConfiguraReqResp( ReqResp );
     ReqResp.URL := FURL;
     ReqResp.UseUTF8InHeader := True;
     ReqResp.SoapAction := 'http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2';
  {$ENDIF}
  NFeRetorno := TRetConsSitNFe.Create;
  try
    TACBrNFe( FACBrNFe ).SetStatus( stNfeConsulta );
    if FConfiguracoes.Geral.Salvar then
     begin
       FPathArqEnv := FNFeChave+'-ped-sit.xml';
       FConfiguracoes.Geral.Save(FPathArqEnv, FDadosMsg);
     end;

    {$IFDEF ACBrNFeOpenSSL}
       HTTP.Document.LoadFromStream(Stream);
       ConfiguraHTTP(HTTP,'SOAPAction: "http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2"');
       HTTP.HTTPMethod('POST', FURL);

       StrStream := TStringStream.Create('');
       StrStream.CopyFrom(HTTP.Document, 0);
       FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
       FRetWS := SeparaDados( FRetornoWS,'nfeConsultaNF2Result');
       StrStream.Free;
    {$ELSE}
       ReqResp.Execute(Acao.Text, Stream);
       StrStream := TStringStream.Create('');
       StrStream.CopyFrom(Stream, 0);
       FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
       FRetWS := SeparaDados( FRetornoWS,'nfeConsultaNF2Result');
       StrStream.Free;
    {$ENDIF}
    if FConfiguracoes.Geral.Salvar  then
     begin
       FPathArqResp := FNFeChave+'-sit.xml';
       FConfiguracoes.Geral.Save(FPathArqResp, FRetWS);
     end;

    NFeRetorno.Leitor.Arquivo := FRetWS;
    NFeRetorno.LerXML;

    NFCancelada := False;

    if NFeRetorno.procEventoNFe.Count > 0 then
      aEventos := '=====================================================' +
                  LineBreak +
                  '================== Eventos da NF-e ==================' +
                  LineBreak +
                  '====================================================='+
                  LineBreak + '' + LineBreak +
                  'Quantidade total de eventos: ' +
                  IntToStr(NFeRetorno.procEventoNFe.Count);

    // <retConsSitNFe> - Retorno da consulta da situa��o da NF-e
    // Este � o status oficial da NF-e
    FTpAmb      := NFeRetorno.TpAmb;
    FverAplic   := NFeRetorno.verAplic;
    FcStat      := NFeRetorno.cStat;
    FxMotivo    := NFeRetorno.xMotivo;
    FcUF        := NFeRetorno.cUF;
    FNFeChave   := NFeRetorno.chNFe;

    // Verifica se a nota fiscal est� cancelada pelo m�todo antigo. Se estiver,
    // ent�o NFCancelada ser� True e j� atribui Protocolo, Data e Mensagem
    if NFeRetorno.retCancNFe.cStat > 0 then
      begin
        FretCancNFe.tpAmb    := NFeRetorno.retCancNFe.tpAmb;
        FretCancNFe.verAplic := NFeRetorno.retCancNFe.verAplic;
        FretCancNFe.cStat    := NFeRetorno.retCancNFe.cStat;
        FretCancNFe.xMotivo  := NFeRetorno.retCancNFe.xMotivo;
        FretCancNFe.cUF      := NFeRetorno.retCancNFe.cUF;
        FretCancNFe.chNFE    := NFeRetorno.retCancNFe.chNFE;
        FretCancNFe.dhRecbto := NFeRetorno.retCancNFe.dhRecbto;
        FretCancNFe.nProt    := NFeRetorno.retCancNFe.nProt;

        NFCancelada := True;
        FProtocolo  := NFeRetorno.retCancNFe.nProt;
        FDhRecbto   := NFeRetorno.retCancNFe.dhRecbto;
        FMsg        := NFeRetorno.xMotivo;
      end;

    // <protNFe> - Retorno dos dados do ENVIO da NF-e
    // Consider�-los apenas se n�o existir nenhum evento de cancelamento (110111)
    FprotNFe.Schema             := NFeRetorno.protNFe.Schema;
    FprotNFe.PathNFe            := NFeRetorno.protNFe.PathNFe;
    FprotNFe.PathRetConsReciNFe := NFeRetorno.protNFe.PathRetConsReciNFe;
    FprotNFe.PathRetConsSitNFe  := NFeRetorno.protNFe.PathRetConsSitNFe;
    FprotNFe.PathRetConsSitNFe  := NFeRetorno.protNFe.PathRetConsSitNFe;
    FprotNFe.tpAmb              := NFeRetorno.protNFe.tpAmb;
    FprotNFe.verAplic           := NFeRetorno.protNFe.verAplic;
    FprotNFe.chNFe              := NFeRetorno.protNFe.chNFe;
    FprotNFe.dhRecbto           := NFeRetorno.protNFe.dhRecbto;
    FprotNFe.nProt              := NFeRetorno.protNFe.nProt;
    FprotNFe.digVal             := NFeRetorno.protNFe.digVal;
    FprotNFe.cStat              := NFeRetorno.protNFe.cStat;
    FprotNFe.xMotivo            := NFeRetorno.protNFe.xMotivo;

    //{eventos_juaumkiko}
    FprocEventoNFe.Clear;
    for I := 0 to NFeRetorno.procEventoNFe.Count -1 do
    begin
      FprocEventoNFe.Add;
      FprocEventoNFe.Items[I].RetEventoNFe.idLote := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.idLote;
      FprocEventoNFe.Items[I].RetEventoNFe.tpAmb := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.tpAmb;
      FprocEventoNFe.Items[I].RetEventoNFe.verAplic := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.verAplic;
      FprocEventoNFe.Items[I].RetEventoNFe.cOrgao := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.cOrgao;
      FprocEventoNFe.Items[I].RetEventoNFe.cStat := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.cStat;
      FprocEventoNFe.Items[I].RetEventoNFe.xMotivo := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.xMotivo;
      FprocEventoNFe.Items[I].RetEventoNFe.InfEvento.ID := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.InfEvento.ID;
      FprocEventoNFe.Items[I].RetEventoNFe.InfEvento.tpAmb := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.InfEvento.tpAmb;
      FprocEventoNFe.Items[I].RetEventoNFe.InfEvento.CNPJ := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.InfEvento.CNPJ;
      FprocEventoNFe.Items[I].RetEventoNFe.InfEvento.chNFe := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.InfEvento.chNFe;
      FprocEventoNFe.Items[I].RetEventoNFe.InfEvento.dhEvento := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.InfEvento.dhEvento;
      FprocEventoNFe.Items[I].RetEventoNFe.InfEvento.TpEvento := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.InfEvento.TpEvento;
      FprocEventoNFe.Items[I].RetEventoNFe.InfEvento.nSeqEvento := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.InfEvento.nSeqEvento;
      FprocEventoNFe.Items[I].RetEventoNFe.InfEvento.VersaoEvento := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.InfEvento.VersaoEvento;
      FprocEventoNFe.Items[I].RetEventoNFe.InfEvento.DetEvento.xCorrecao := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.InfEvento.DetEvento.xCorrecao;
      FprocEventoNFe.Items[I].RetEventoNFe.InfEvento.DetEvento.xCondUso := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.InfEvento.DetEvento.xCondUso;
      FprocEventoNFe.Items[I].RetEventoNFe.InfEvento.DetEvento.nProt := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.InfEvento.DetEvento.nProt;
      FprocEventoNFe.Items[I].RetEventoNFe.InfEvento.DetEvento.xJust := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.InfEvento.DetEvento.xJust;
      FprocEventoNFe.Items[I].RetEventoNFe.retEvento.Clear;
      for j := 0 to NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.retEvento.Count-1 do
      begin
        FprocEventoNFe.Items[I].RetEventoNFe.retEvento.Add;
        FprocEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.Id := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.Id;
        FprocEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.tpAmb := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.tpAmb;
        FprocEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.verAplic := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.verAplic;
        FprocEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.cOrgao := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.cOrgao;
        FprocEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.cStat := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.cStat;
        FprocEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.xMotivo := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.xMotivo;
        FprocEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.chNFe := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.chNFe;
        FprocEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.tpEvento := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.tpEvento;
        FprocEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.xEvento := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.xEvento;
        FprocEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.nSeqEvento := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.nSeqEvento;
        FprocEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.CNPJDest := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.CNPJDest;
        FprocEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.emailDest := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.emailDest;
        FprocEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.dhRegEvento := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.dhRegEvento;
        FprocEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.nProt := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.nProt;
        FprocEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.XML := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.XML;

        aEventos := aEventos + LineBreak + '' + LineBreak +
                    'N�mero de sequ�ncia: ' + IntToStr(NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.InfEvento.nSeqEvento) + LineBreak +
                    'C�digo do evento: ' + TpEventoToStr(NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.InfEvento.TpEvento) + LineBreak +
                    'Descri��o do evento: ' + NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.InfEvento.DescEvento + LineBreak +
                    'Status do evento: ' + IntToStr(NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.cStat) + LineBreak +
                    'Descri��o do status: ' + NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.xMotivo + LineBreak +
                    'Protocolo: ' + NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.nProt + LineBreak +
                    'Data / hora do registro: ' + DateTimeToStr(NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.dhRegEvento);

        if NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.tpEvento = teCancelamento then
          begin
            NFCancelada := True;
            FProtocolo  := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.nProt;
            FDhRecbto   := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.dhRegEvento;
            FMsg        := NFeRetorno.procEventoNFe.Items[I].RetEventoNFe.retEvento.Items[j].RetInfEvento.xMotivo;
          end;
      end;
    end;

    //FProtocolo  := DFeUtil.SeSenao(DFeUtil.NaoEstaVazio(NFeRetorno.retCancNFe.nProt),NFeRetorno.retCancNFe.nProt,NFeRetorno.protNFe.nProt);
    //FDhRecbto   := DFeUtil.SeSenao(NFeRetorno.retCancNFe.dhRecbto <> 0,NFeRetorno.retCancNFe.dhRecbto,NFeRetorno.protNFe.dhRecbto);
    //FMsg        := NFeRetorno.XMotivo;

    if NFCancelada = False then
      begin
        FProtocolo := NFeRetorno.protNFe.nProt;
        FDhRecbto  := NFeRetorno.protNFe.dhRecbto;
        FMsg       := NFeRetorno.protNFe.xMotivo;
      end;

    TACBrNFe( FACBrNFe ).SetStatus( stIdle );

    aMsg := //'Vers�o Leiaute : '+NFeRetorno.Versao+LineBreak+
            'Identificador : '     + FNFeChave + LineBreak +
            'Ambiente : '          + TpAmbToStr(FTpAmb) + LineBreak +
            'Vers�o Aplicativo : ' + FverAplic + LineBreak+
            'Status C�digo : '     + IntToStr(FcStat) + LineBreak+
            'Status Descri��o : '  + FXMotivo + LineBreak +
            'UF : '                + CodigoParaUF(FcUF) + LineBreak +
            'Chave Acesso : '      + FNFeChave + LineBreak +
            'Recebimento : '       + DateTimeToStr(FDhRecbto) + LineBreak +
            'Protocolo : '         + FProtocolo + LineBreak +
            'Digest Value : '      + NFeRetorno.protNFe.digVal + LineBreak;

    if NFeRetorno.procEventoNFe.Count > 0 then
      aMsg := aMsg + LineBreak + aEventos;

    if FConfiguracoes.WebServices.Visualizar then
      ShowMessage(aMsg);

    if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
       TACBrNFe( FACBrNFe ).OnGerarLog(aMsg);

    Result := (NFeRetorno.CStat in [100,101,110,150,151,155]);

    for i:= 0 to TACBrNFe( FACBrNFe ).NotasFiscais.Count-1 do
     begin
        if StringReplace(TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.infNFe.ID,'NFe','',[rfIgnoreCase]) = FNFeChave then
         begin
            watualiza:=true;
            if ((NFeRetorno.CStat in [101,151,155]) and
                (FConfiguracoes.Geral.AtualizarXMLCancelado=false)) then
               wAtualiza:=False;

            TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].Confirmada := (NFeRetorno.cStat in [100,150]);
            if wAtualiza then
            begin
              TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].Msg                  := NFeRetorno.xMotivo;
              TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.procNFe.tpAmb    := NFeRetorno.tpAmb;
              TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.procNFe.verAplic := NFeRetorno.verAplic;
              TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.procNFe.chNFe    := NFeRetorno.chNfe;
              TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.procNFe.dhRecbto := FDhRecbto;
              TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.procNFe.nProt    := FProtocolo;
              TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.procNFe.digVal   := NFeRetorno.protNFe.digVal;
              TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.procNFe.cStat    := NFeRetorno.cStat;
              TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.procNFe.xMotivo  := NFeRetorno.xMotivo;
            end;

            if ((FileExists(PathWithDelim(FConfiguracoes.Geral.PathSalvar)+FNFeChave+'-nfe.xml') or DFeUtil.NaoEstaVazio(TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NomeArq))
               and wAtualiza) then
            begin
             AProcNFe:=TProcNFe.Create;
             if DFeUtil.NaoEstaVazio(TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NomeArq) then
                AProcNFe.PathNFe:=TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NomeArq
             else
                AProcNFe.PathNFe:=PathWithDelim(FConfiguracoes.Geral.PathSalvar)+FNFeChave+'-nfe.xml';
             AProcNFe.PathRetConsSitNFe:=PathWithDelim(FConfiguracoes.Geral.PathSalvar)+FNFeChave+'-sit.xml';

             if (FConfiguracoes.Geral.ModeloDF = moNFCe) then 
                AProcNFe.Versao := NFCeEnvi
             else 
                AProcNFe.Versao := NFenviNFe;

             AProcNFe.GerarXML;
             if DFeUtil.NaoEstaVazio(AProcNFe.Gerador.ArquivoFormatoXML) then
                AProcNFe.Gerador.SalvarArquivo(AProcNFe.PathNFe);
             AProcNFe.Free;
            end;

            if FConfiguracoes.Arquivos.Salvar and wAtualiza then
            begin
              if FConfiguracoes.Arquivos.EmissaoPathNFe then
                 TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].SaveToFile(PathWithDelim(FConfiguracoes.Arquivos.GetPathNFe(TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.Ide.dEmi))+StringReplace(TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.InfNFe.Id,'NFe','',[rfIgnoreCase])+'-nfe.xml')
              else
                 TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].SaveToFile(PathWithDelim(FConfiguracoes.Arquivos.GetPathNFe)+StringReplace(TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.InfNFe.Id,'NFe','',[rfIgnoreCase])+'-nfe.xml');
            end;

            break;
         end;
     end;

    //NFeRetorno.Free;

    if (TACBrNFe( FACBrNFe ).NotasFiscais.Count <= 0) then
     begin
       if FConfiguracoes.Geral.Salvar then
        begin
          if FileExists(PathWithDelim(FConfiguracoes.Geral.PathSalvar)+FNFeChave+'-nfe.xml') then
           begin
             AProcNFe:=TProcNFe.Create;
             AProcNFe.PathNFe:=PathWithDelim(FConfiguracoes.Geral.PathSalvar)+FNFeChave+'-nfe.xml';
             AProcNFe.PathRetConsSitNFe:=PathWithDelim(FConfiguracoes.Geral.PathSalvar)+FNFeChave+'-sit.xml';

             if (FConfiguracoes.Geral.ModeloDF = moNFCe) then 
                AProcNFe.Versao := NFCeEnvi
             else 
                AProcNFe.Versao := NFenviNFe;

             AProcNFe.GerarXML;
             if DFeUtil.NaoEstaVazio(AProcNFe.Gerador.ArquivoFormatoXML) then
                AProcNFe.Gerador.SalvarArquivo(AProcNFe.PathNFe);
             AProcNFe.Free;
           end;
        end;
     end;
  finally
    {$IFDEF ACBrNFeOpenSSL}
       HTTP.Free;
    {$ELSE}
      ReqResp.Free;
    {$ENDIF}
    NFeRetorno.Free; //(se descomentar essa linha n�o ser� poss�vel ler a propriedade ACBrNFe1.WebServices.Consulta.protNFe.nProt)
    Acao.Free;
    Stream.Free;
    NotaUtil.ConfAmbiente;
    TACBrNFe( FACBrNFe ).SetStatus( stIdle );
  end;
end;

{ TNFeCancelamento }
function TNFeCancelamento.Executar: Boolean;
var
  NFeRetorno: TRetCancNFe;
  aMsg: string;
  i : Integer;
  Texto : String;
  Acao  : TStringList ;
  Stream: TMemoryStream;
  StrStream: TStringStream;
  wPROC: TStringList;
  {$IFDEF ACBrNFeOpenSSL}
     HTTP: THTTPSend;
  {$ELSE}
     ReqResp: THTTPReqResp;
  {$ENDIF}
begin
  inherited Executar;

  Acao := TStringList.Create;
  Stream := TMemoryStream.Create;

  Texto := '<?xml version="1.0" encoding="utf-8"?>';
  Texto := Texto + '<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">';
  Texto := Texto +   '<soap12:Header>';
  Texto := Texto +     '<nfeCabecMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/NfeCancelamento2">';
  Texto := Texto +       '<cUF>'+IntToStr(FConfiguracoes.WebServices.UFCodigo)+'</cUF>';

  if (FConfiguracoes.Geral.ModeloDF = moNFCe) then 
     Texto := Texto + '<versaoDados>' + NFCeCanc + '</versaoDados>'
  else 
     Texto := Texto + '<versaoDados>' + NFeCancNFe + '</versaoDados>';

  Texto := Texto +     '</nfeCabecMsg>';
  Texto := Texto +   '</soap12:Header>';
  Texto := Texto +   '<soap12:Body>';
  Texto := Texto +     '<nfeDadosMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/NfeCancelamento2">';
  Texto := Texto + FDadosMsg;
  Texto := Texto +     '</nfeDadosMsg>';
  Texto := Texto +   '</soap12:Body>';
  Texto := Texto +'</soap12:Envelope>';

  Acao.Text := Texto;

  {$IFDEF ACBrNFeOpenSSL}
     Acao.SaveToStream(Stream);  
     HTTP := THTTPSend.Create;
  {$ELSE}
     ReqResp := THTTPReqResp.Create(nil);
     ConfiguraReqResp( ReqResp );
     ReqResp.URL := FURL;
     ReqResp.UseUTF8InHeader := True;
     ReqResp.SoapAction := 'http://www.portalfiscal.inf.br/nfe/wsdl/NfeCancelamento2';
  {$ENDIF}

  NFeRetorno := TRetCancNFe.Create;
  try
    TACBrNFe( FACBrNFe ).SetStatus( stNfeCancelamento );

    if FConfiguracoes.Geral.Salvar then
     begin
       FPathArqEnv := FNFeChave+'-ped-can.xml';
       FConfiguracoes.Geral.Save(FPathArqEnv, FDadosMsg);
     end;

    if FConfiguracoes.Arquivos.Salvar then
      FConfiguracoes.Geral.Save(FNFeChave+'-ped-can.xml', FDadosMsg, FConfiguracoes.Arquivos.GetPathCan );

    {$IFDEF ACBrNFeOpenSSL}
       HTTP.Document.LoadFromStream(Stream);
       ConfiguraHTTP(HTTP,'SOAPAction: "http://www.portalfiscal.inf.br/nfe/wsdl/NfeCancelamento2"');
       HTTP.HTTPMethod('POST', FURL);

       StrStream := TStringStream.Create('');
       StrStream.CopyFrom(HTTP.Document, 0);
       FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
       FRetWS := SeparaDados( FRetornoWS,'nfeCancelamentoNF2Result');
       StrStream.Free;
    {$ELSE}
       ReqResp.Execute(Acao.Text, Stream);
       StrStream := TStringStream.Create('');
       StrStream.CopyFrom(Stream, 0);
       FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
       FRetWS := SeparaDados( FRetornoWS,'nfeCancelamentoNF2Result');
       StrStream.Free;
    {$ENDIF}

    if FConfiguracoes.Geral.Salvar then
     begin
       FPathArqResp := FNFeChave+'-can.xml';
       FConfiguracoes.Geral.Save(FPathArqResp, FRetWS);
     end;

    if FConfiguracoes.Arquivos.Salvar then
      FConfiguracoes.Geral.Save(FNFeChave+'-can.xml', FRetWS, FConfiguracoes.Arquivos.GetPathCan );

    NFeRetorno.Leitor.Arquivo := FRetWS;
    NFeRetorno.LerXml;

    TACBrNFe( FACBrNFe ).SetStatus( stIdle );
    aMsg := //'Vers�o Leiaute : '+NFeRetorno.Versao+LineBreak+
            'Identificador : '+ NFeRetorno.chNFE+LineBreak+
            'Ambiente : '+TpAmbToStr(NFeRetorno.TpAmb)+LineBreak+
            'Vers�o Aplicativo : '+NFeRetorno.verAplic+LineBreak+
            'Status C�digo : '+IntToStr(NFeRetorno.cStat)+LineBreak+
            'Status Descri��o : '+NFeRetorno.xMotivo+LineBreak+
            'UF : '+CodigoParaUF(NFeRetorno.cUF)+LineBreak+
            'Chave Acesso : '+NFeRetorno.chNFE+LineBreak+
            'Recebimento : '+DFeUtil.SeSenao(NFeRetorno.DhRecbto = 0, '', DateTimeToStr(NFeRetorno.DhRecbto))+LineBreak+
            'Protocolo : '+NFeRetorno.nProt+LineBreak;

    if FConfiguracoes.WebServices.Visualizar then
      ShowMessage(aMsg);

    if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
       TACBrNFe( FACBrNFe ).OnGerarLog(aMsg);

    FTpAmb    := NFeRetorno.TpAmb;
    FverAplic := NFeRetorno.verAplic;
    FcStat    := NFeRetorno.cStat;
    FxMotivo  := NFeRetorno.xMotivo;
    FcUF      := NFeRetorno.cUF;
    FDhRecbto := NFeRetorno.dhRecbto;
    Fprotocolo:= NFeRetorno.nProt;

    FMsg   := NFeRetorno.XMotivo;
    Result := (NFeRetorno.CStat in [101,151,155]);

    for i:= 0 to TACBrNFe( FACBrNFe ).NotasFiscais.Count-1 do
     begin
        if StringReplace(TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.infNFe.ID,'NFe','',[rfIgnoreCase]) = NFeRetorno.chNFE then
         begin
           if (FConfiguracoes.Geral.AtualizarXMLCancelado) then
           begin
              TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].Msg        := NFeRetorno.xMotivo;
              TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.procNFe.tpAmb    := NFeRetorno.tpAmb;
              TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.procNFe.verAplic := NFeRetorno.verAplic;
              TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.procNFe.chNFe    := NFeRetorno.chNFe;
              TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.procNFe.dhRecbto := NFeRetorno.dhRecbto;
              TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.procNFe.nProt    := NFeRetorno.nProt;
              TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.procNFe.cStat    := NFeRetorno.cStat;
              TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.procNFe.xMotivo  := NFeRetorno.xMotivo;
           end;

           if FConfiguracoes.Arquivos.Salvar or DFeUtil.NaoEstaVazio(TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NomeArq) then
            begin
              if ((NFeRetorno.CStat in [101,151,155]) and
                  (FConfiguracoes.Geral.AtualizarXMLCancelado)) then
              begin
                 if DFeUtil.NaoEstaVazio(TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NomeArq) then
                    TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].SaveToFile(TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NomeArq)
                 else
                 begin
                    if FConfiguracoes.Arquivos.EmissaoPathNFe then
                       TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].SaveToFile(PathWithDelim(FConfiguracoes.Arquivos.GetPathNFe(TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.Ide.dEmi))+StringReplace(TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.InfNFe.Id,'NFe','',[rfIgnoreCase])+'-nfe.xml')
                    else
                       TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].SaveToFile(PathWithDelim(FConfiguracoes.Arquivos.GetPathNFe)+StringReplace(TACBrNFe( FACBrNFe ).NotasFiscais.Items[i].NFe.InfNFe.Id,'NFe','',[rfIgnoreCase])+'-nfe.xml');
                 end;
              end;
            end;

           break;
         end;
     end;

    //gerar arquivo proc de cancelamento
    if (NFeRetorno.cStat in [101,151,155]) then
    begin
      wProc := TStringList.Create;
      wProc.Add('<?xml version="1.0" encoding="UTF-8" ?>');
      wProc.Add('<procCancNFe versao="2.00" xmlns="http://www.portalfiscal.inf.br/nfe">');
      wProc.Add(FDadosMSG);
      wProc.Add(FRetWS);
      wProc.Add('</procCancNFe>');
      FXML_ProcCancNFe:=wProc.Text;
      wProc.Free;
      if FConfiguracoes.Geral.Salvar then
         FConfiguracoes.Geral.Save(FNFeChave+'-ProcCancNFe.xml', FXML_ProcCancNFe);

      if FConfiguracoes.Arquivos.Salvar then
        FConfiguracoes.Geral.Save(FNFeChave+'-ProcCancNFe.xml', FXML_ProcCancNFe, FConfiguracoes.Arquivos.GetPathCan );
    end;

  finally
    {$IFDEF ACBrNFeOpenSSL}
       HTTP.Free;
    {$ELSE}
      ReqResp.Free;
    {$ENDIF}
    Acao.Free;
    Stream.Free;
    NFeRetorno.Free; //(se descomentar essa linha n�o ser� poss�vel ler a propriedade ACBrNFe1.WebServices.Consulta.protNFe)
    NotaUtil.ConfAmbiente;
    TACBrNFe( FACBrNFe ).SetStatus( stIdle );
  end;
end;

procedure TNFeCancelamento.SetJustificativa(AValue: WideString);
begin
  if DFeUtil.EstaVazio(AValue) then
   begin
     if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
        TACBrNFe( FACBrNFe ).OnGerarLog('ERRO: Informar uma Justificativa para cancelar a Nota Fiscal Eletronica');
     raise EACBrNFeException.Create('Informar uma Justificativa para cancelar a Nota Fiscal Eletronica')
   end
  else
    AValue := DFeUtil.TrataString(AValue);

  if Length(AValue) < 15 then
   begin
     if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
        TACBrNFe( FACBrNFe ).OnGerarLog('ERRO: A Justificativa para Cancelamento da Nota Fiscal Eletronica deve ter no minimo 15 caracteres');
     raise EACBrNFeException.Create('A Justificativa para Cancelamento da Nota Fiscal Eletronica deve ter no minimo 15 caracteres')
   end
  else
    FJustificativa := Trim(AValue);
end;

{ TNFeInutilizacao }
function TNFeInutilizacao.Executar: Boolean;
var
  NFeRetorno: TRetInutNFe;
  aMsg: string;
  Texto : String;
  Acao  : TStringList ;
  Stream: TMemoryStream;
  StrStream: TStringStream;
  wProc  : TStringList ;
  {$IFDEF ACBrNFeOpenSSL}
     HTTP: THTTPSend;
  {$ELSE}
     ReqResp: THTTPReqResp;
  {$ENDIF}
begin
  inherited Executar;

  Acao := TStringList.Create;
  Stream := TMemoryStream.Create;

  Texto := '<?xml version="1.0" encoding="utf-8"?>';
  Texto := Texto + '<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">';
  Texto := Texto +   '<soap12:Header>';
  Texto := Texto +     '<nfeCabecMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2">';
  Texto := Texto +       '<cUF>'+IntToStr(FConfiguracoes.WebServices.UFCodigo)+'</cUF>';

  if (FConfiguracoes.Geral.ModeloDF = moNFCe) then 
     Texto := Texto + '<versaoDados>' + NFCeInut + '</versaoDados>'
  else 
     Texto := Texto + '<versaoDados>' + NFeInutNFe + '</versaoDados>';

  Texto := Texto +     '</nfeCabecMsg>';
  Texto := Texto +   '</soap12:Header>';
  Texto := Texto +   '<soap12:Body>';
  Texto := Texto +     '<nfeDadosMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2">';
  Texto := Texto + FDadosMsg;
  Texto := Texto +     '</nfeDadosMsg>';
  Texto := Texto +   '</soap12:Body>';
  Texto := Texto +'</soap12:Envelope>';

  Acao.Text := Texto;

  {$IFDEF ACBrNFeOpenSSL}
     Acao.SaveToStream(Stream);
     HTTP := THTTPSend.Create;
  {$ELSE}
     ReqResp := THTTPReqResp.Create(nil);
     ConfiguraReqResp( ReqResp );
     ReqResp.URL := FURL;
     ReqResp.UseUTF8InHeader := True;
     ReqResp.SoapAction := 'http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2';
  {$ENDIF}
  NFeRetorno := TRetInutNFe.Create;
  try
    TACBrNFe( FACBrNFe ).SetStatus( stNfeInutilizacao );
    if FConfiguracoes.Geral.Salvar then
     begin
       FPathArqEnv := StringReplace(FID,'ID','',[rfIgnoreCase])+'-ped-inu.xml';
       FConfiguracoes.Geral.Save(FPathArqEnv, FDadosMsg);
     end;

    if FConfiguracoes.Arquivos.Salvar then
      FConfiguracoes.Geral.Save(StringReplace(FID,'ID','',[rfIgnoreCase])+'-ped-inu.xml', FDadosMsg, FConfiguracoes.Arquivos.GetPathInu);

    {$IFDEF ACBrNFeOpenSSL}
       HTTP.Document.LoadFromStream(Stream);
       ConfiguraHTTP(HTTP,'SOAPAction: "http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2"');
       HTTP.HTTPMethod('POST', FURL);

       StrStream := TStringStream.Create('');
       StrStream.CopyFrom(HTTP.Document, 0);
       FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
       FRetWS := SeparaDados( FRetornoWS,'nfeInutilizacaoNF2Result');
       StrStream.Free;
    {$ELSE}
       ReqResp.Execute(Acao.Text, Stream);
       StrStream := TStringStream.Create('');
       StrStream.CopyFrom(Stream, 0);
       FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
       FRetWS := SeparaDados( FRetornoWS,'nfeInutilizacaoNF2Result');
       StrStream.Free;
    {$ENDIF}

    if FConfiguracoes.Geral.Salvar then
     begin
       FPathArqResp := StringReplace(FID,'ID','',[rfIgnoreCase])+'-inu.xml';
       FConfiguracoes.Geral.Save(FPathArqResp, FRetWS);
     end;

    if FConfiguracoes.Arquivos.Salvar then
      FConfiguracoes.Geral.Save(StringReplace(FID,'ID','',[rfIgnoreCase])+'-inu.xml', FRetWS, FConfiguracoes.Arquivos.GetPathInu);

    NFeRetorno.Leitor.Arquivo := FRetWS;
    NFeRetorno.LerXml;

    TACBrNFe( FACBrNFe ).SetStatus( stIdle );
    aMsg := 'Ambiente : '+TpAmbToStr(NFeRetorno.TpAmb)+LineBreak+
            'Vers�o Aplicativo : '+NFeRetorno.verAplic+LineBreak+
            'Status C�digo : '+IntToStr(NFeRetorno.cStat)+LineBreak+
            'Status Descri��o : '+NFeRetorno.xMotivo+LineBreak+
            'UF : '+CodigoParaUF(NFeRetorno.cUF)+LineBreak+
            'Recebimento : '+DFeUtil.SeSenao(NFeRetorno.DhRecbto = 0, '', DateTimeToStr(NFeRetorno.dhRecbto));
    if FConfiguracoes.WebServices.Visualizar then
      ShowMessage(aMsg);

    if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
       TACBrNFe( FACBrNFe ).OnGerarLog(aMsg);

    FTpAmb    := NFeRetorno.TpAmb;
    FverAplic := NFeRetorno.verAplic;
    FcStat    := NFeRetorno.cStat;
    FxMotivo  := NFeRetorno.xMotivo;
    FcUF      := NFeRetorno.cUF ;
    FdhRecbto := NFeRetorno.dhRecbto;
    Fprotocolo:= NFeRetorno.nProt;
    FMsg   := NFeRetorno.XMotivo;
    Result := (NFeRetorno.cStat = 102);

    //gerar arquivo proc de inutilizacao
    if NFeRetorno.cStat=102 then
    begin
      wProc := TStringList.Create;
      wProc.Add('<?xml version="1.0" encoding="UTF-8" ?>');
      wProc.Add('<ProcInutNFe versao="2.00" xmlns="http://www.portalfiscal.inf.br/nfe">');
      wProc.Add(FDadosMSG);
      wProc.Add(FRetWS);
      wProc.Add('</ProcInutNFe>');
      FXML_ProcInutNFe:=wProc.Text;
      wProc.Free;
      if FConfiguracoes.Geral.Salvar then
         FConfiguracoes.Geral.Save(StringReplace(FID,'ID','',[rfIgnoreCase])+'-ProcInutNFe.xml', FXML_ProcInutNFe);
      if FConfiguracoes.Arquivos.Salvar then
         FConfiguracoes.Geral.Save(StringReplace(FID,'ID','',[rfIgnoreCase])+'-ProcInutNFe.xml', FXML_ProcInutNFe, FConfiguracoes.Arquivos.GetPathInu );
    end;

  finally
    {$IFDEF ACBrNFeOpenSSL}
       HTTP.Free;
    {$ELSE}
      ReqResp.Free;
    {$ENDIF}
    NFeRetorno.Free; //(se descomentar essa linha n�o ser� poss�vel ler a propriedade ACBrNFe1.WebServices.Consulta.protNFe)
    Acao.Free;
    Stream.Free;
    NotaUtil.ConfAmbiente;
    TACBrNFe( FACBrNFe ).SetStatus( stIdle );
  end;
end;

procedure TNFeInutilizacao.SetJustificativa(AValue: WideString);
begin
  if DFeUtil.EstaVazio(AValue) then
   begin
     if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
        TACBrNFe( FACBrNFe ).OnGerarLog('ERRO: Informar uma Justificativa para Inutiliza��o de numera��o da Nota Fiscal Eletronica');
     raise EACBrNFeException.Create('Informar uma Justificativa para Inutiliza��o de numera��o da Nota Fiscal Eletronica')
   end
  else
    AValue := DFeUtil.TrataString(AValue);

  if Length(Trim(AValue)) < 15 then
   begin
     if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
        TACBrNFe( FACBrNFe ).OnGerarLog('ERRO: A Justificativa para Inutiliza��o de numera��o da Nota Fiscal Eletronica deve ter no minimo 15 caracteres');
     raise EACBrNFeException.Create('A Justificativa para Inutiliza��o de numera��o da Nota Fiscal Eletronica deve ter no minimo 15 caracteres')
   end
  else
    FJustificativa := Trim(AValue);
end;

{ TNFeConsultaCadastro }
destructor TNFeConsultaCadastro.destroy;
begin
  FRetConsCad.Free;

  inherited;
end;

function TNFeConsultaCadastro.Executar: Boolean;
var
  aMsg : String;
  Texto : String;
  Acao  : TStringList ;
  Stream: TMemoryStream;
  StrStream: TStringStream;
  {$IFDEF ACBrNFeOpenSSL}
     HTTP: THTTPSend;
  {$ELSE}
     ReqResp: THTTPReqResp;
  {$ENDIF}
begin
  if assigned(FRetConsCad) then
     FreeAndNil(FRetConsCad);

  inherited Executar;

  Acao := TStringList.Create;
  Stream := TMemoryStream.Create;  

  Texto := '<?xml version="1.0" encoding="utf-8"?>';
  Texto := Texto + '<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">';
  Texto := Texto +   '<soap12:Header>';
  Texto := Texto +     '<nfeCabecMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/CadConsultaCadastro2">';
  Texto := Texto +       '<cUF>'+IntToStr(UFparaCodigo(TNFeConsultaCadastro(Self).UF))+'</cUF>';

  if (FConfiguracoes.Geral.ModeloDF = moNFCe) then 
     Texto := Texto + '<versaoDados>' + NFCeConsCad + '</versaoDados>'
  else 
     Texto := Texto + '<versaoDados>' + NFeConsCad + '</versaoDados>';

  Texto := Texto +     '</nfeCabecMsg>';
  Texto := Texto +   '</soap12:Header>';
  Texto := Texto +   '<soap12:Body>';
  Texto := Texto +     '<nfeDadosMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/CadConsultaCadastro2">';
  Texto := Texto + FDadosMsg;
  Texto := Texto +     '</nfeDadosMsg>';
  Texto := Texto +   '</soap12:Body>';
  Texto := Texto +'</soap12:Envelope>';

  Acao.Text := Texto;

  {$IFDEF ACBrNFeOpenSSL}
     Acao.SaveToStream(Stream);
     HTTP := THTTPSend.Create;
  {$ELSE}
     ReqResp := THTTPReqResp.Create(nil);
     ConfiguraReqResp( ReqResp );
     ReqResp.URL := FURL;
     ReqResp.UseUTF8InHeader := True;
     ReqResp.SoapAction := 'http://www.portalfiscal.inf.br/nfe/wsdl/CadConsultaCadastro2' ;
  {$ENDIF}
  try
    TACBrNFe( FACBrNFe ).SetStatus( stNFeCadastro );

    FRetConsCad := TRetConsCad.Create;

    if FConfiguracoes.Geral.Salvar then
     begin
       FPathArqEnv := FormatDateTime('yyyymmddhhnnss',Now)+'-ped-cad.xml';
       FConfiguracoes.Geral.Save(FPathArqEnv, FDadosMsg);
     end;
    FRetWS := '';
    {$IFDEF ACBrNFeOpenSSL}
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

    if FConfiguracoes.Geral.Salvar then
     begin
       FPathArqResp := FormatDateTime('yyyymmddhhnnss',Now)+'-cad.xml';
       FConfiguracoes.Geral.Save(FPathArqResp, FRetWS);
     end;

    FRetConsCad.Leitor.Arquivo := FRetWS;
    FRetConsCad.LerXml;

    aMsg := 'Vers�o Aplicativo : '+FRetConsCad.verAplic+LineBreak+
            'Status C�digo : '+IntToStr(FRetConsCad.cStat)+LineBreak+
            'Status Descri��o : '+FRetConsCad.xMotivo+LineBreak+
            'UF : '+CodigoParaUF(FRetConsCad.cUF)+LineBreak+
            'Consulta : '+DateTimeToStr(FRetConsCad.dhCons);

    TACBrNFe( FACBrNFe ).SetStatus( stIdle );
    if FConfiguracoes.WebServices.Visualizar then
       ShowMessage(aMsg);

    if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
       TACBrNFe( FACBrNFe ).OnGerarLog(aMsg);

    FverAplic := FRetConsCad.verAplic;
    FcStat    := FRetConsCad.cStat;
    FxMotivo  := FRetConsCad.xMotivo;
    FdhCons   := FRetConsCad.dhCons;
    FcUF      := FRetConsCad.cUF ;

    FMsg      := FRetConsCad.XMotivo;

   Result := (FRetConsCad.cStat in [111,112]);
  finally
    {$IFDEF ACBrNFeOpenSSL}
       HTTP.Free;
    {$ELSE}
       ReqResp.Free;
    {$ENDIF}
    Acao.Free;
    Stream.Free;
    NotaUtil.ConfAmbiente;
    TACBrNFe( FACBrNFe ).SetStatus( stIdle );
  end;
end;

procedure TNFeConsultaCadastro.SetCNPJ(const Value: String);
begin
  if DFeUtil.NaoEstaVazio(Value) then
   begin
     FIE   := '';
     FCPF  := '';
   end;
  FCNPJ := Value;
end;

procedure TNFeConsultaCadastro.SetCPF(const Value: String);
begin
  if DFeUtil.NaoEstaVazio(Value) then
   begin
     FIE   := '';
     FCNPJ := '';
   end;
  FCPF  := Value;
end;

procedure TNFeConsultaCadastro.SetIE(const Value: String);
begin
  if DFeUtil.NaoEstaVazio(Value) then
   begin
     FCNPJ := '';
     FCPF  := '';
   end;
  FIE   := Value;
end;

{ TNFeEnvDPEC }
function TNFeEnvDPEC.Executar: Boolean;
var
  Texto : String;
  Acao  : TStringList ;
  Stream: TMemoryStream;
  StrStream: TStringStream;
  aMsg : String;
  {$IFDEF ACBrNFeOpenSSL}
     HTTP: THTTPSend;
  {$ELSE}
     ReqResp: THTTPReqResp;
  {$ENDIF}
  RetDPEC : TRetDPEC;
  wProc: TStringList;
begin
  inherited Executar;

  Acao := TStringList.Create;
  Stream := TMemoryStream.Create;
  Texto := '<?xml version="1.0" encoding="utf-8"?>';
  Texto := Texto + '<soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">';
  Texto := Texto + '<soap:Header>';
  Texto := Texto + '  <sceCabecMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/SCERecepcaoRFB">';
  Texto := Texto + '    <versaoDados>'+NFeEnvDPEC+'</versaoDados>';
  Texto := Texto + '  </sceCabecMsg>';
  Texto := Texto + '</soap:Header>';
  Texto := Texto + '<soap:Body>';
  Texto := Texto + '  <sceDadosMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/SCERecepcaoRFB">';
  Texto := Texto +  FDadosMsg;
  Texto := Texto +   '</sceDadosMsg>';
  Texto := Texto + '</soap:Body>';
  Texto := Texto + '</soap:Envelope>';

  Acao.Text := Texto;

  {$IFDEF ACBrNFeOpenSSL}
     Acao.SaveToStream(Stream);
     HTTP := THTTPSend.Create;
  {$ELSE}
     ReqResp := THTTPReqResp.Create(nil);
     ReqResp.OnBeforePost := OnBeforePost;
     ReqResp.URL := FURL;
     ReqResp.UseUTF8InHeader := True;
     ReqResp.SoapAction := 'http://www.portalfiscal.inf.br/nfe/wsdl/SCERecepcaoRFB/sceRecepcaoDPEC';
  {$ENDIF}

  try
    TACBrNFe( FACBrNFe ).SetStatus( stNFeEnvDPEC );
    if FConfiguracoes.Geral.Salvar then
     begin
       FPathArqEnv := FormatDateTime('yyyymmddhhnnss',Now)+'-env-dpec.xml';
       FConfiguracoes.Geral.Save(FPathArqEnv, FDadosMsg);
     end;

    if FConfiguracoes.Arquivos.Salvar then
      FConfiguracoes.Geral.Save(FormatDateTime('yyyymmddhhnnss',Now)+'-env-dpec.xml', FDadosMsg, FConfiguracoes.Arquivos.GetPathDPEC);

    FRetWS := '';
    StrStream := TStringStream.Create('');
    {$IFDEF ACBrNFeOpenSSL}
       HTTP.Document.LoadFromStream(Stream);
       ConfiguraHTTP(HTTP,'SOAPAction: "http://www.portalfiscal.inf.br/nfe/wsdl/SCERecepcaoRFB/sceRecepcaoDPEC"');
       HTTP.HTTPMethod('POST', FURL);

       StrStream.CopyFrom(HTTP.Document, 0);
       FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
       FRetWS := SeparaDados( FRetornoWS,'sceRecepcaoDPECResult',True);
    {$ELSE}
       ReqResp.Execute(Acao.Text, Stream);
       StrStream.CopyFrom(Stream, 0);
       FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
       FRetWS := SeparaDados( FRetornoWS,'sceRecepcaoDPECResult',True);
    {$ENDIF}
    StrStream.Free;

    RetDPEC := TRetDPEC.Create;
    RetDPEC.Leitor.Arquivo := FRetWS;
    RetDPEC.LerXml;

    aMsg := 'Vers�o Aplicativo : '+RetDPEC.verAplic+LineBreak+
            'ID : '+RetDPEC.Id+LineBreak+
            'Status C�digo : '+IntToStr(RetDPEC.cStat)+LineBreak+
            'Status Descri��o : '+RetDPEC.xMotivo+LineBreak+
            'Data Registro : '+DateTimeToStr(RetDPEC.dhRegDPEC)+LineBreak+
            'nRegDPEC : '+RetDPEC.nRegDPEC+LineBreak+
            'ChaveNFe : '+RetDPEC.chNFE;

    TACBrNFe( FACBrNFe ).SetStatus( stIdle );
    if FConfiguracoes.WebServices.Visualizar then
       ShowMessage(aMsg);

    if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
       TACBrNFe( FACBrNFe ).OnGerarLog(aMsg);

    FverAplic := RetDPEC.verAplic;
    FcStat    := RetDPEC.cStat;
    FxMotivo  := RetDPEC.xMotivo;
    FId       := RetDPEC.Id;
    FTpAmb    := RetDPEC.tpAmb;
    FdhRegDPEC := RetDPEC.dhRegDPEC;
    FnRegDPEC  := RetDPEC.nRegDPEC;
    FNFeChave  := RetDPEC.chNFE;

    FMsg      := RetDPEC.XMotivo;

    Result := (RetDPEC.cStat = 124);

    if FConfiguracoes.Geral.Salvar then
     begin
       FPathArqResp := FormatDateTime('yyyymmddhhnnss',Now)+'-ret-dpec.xml';
       FConfiguracoes.Geral.Save(FPathArqResp, FRetWS);
     end;

    if FConfiguracoes.Arquivos.Salvar then
      FConfiguracoes.Geral.Save(FormatDateTime('yyyymmddhhnnss',Now)+'-ret-dpec.xml', FRetWS, FConfiguracoes.Arquivos.GetPathDPEC);

    //gerar arquivo proc de DPEC
    if (RetDPEC.cStat = 124) then
    begin
      wProc := TStringList.Create;
      wProc.Add('<?xml version="1.0" encoding="UTF-8" ?>');
      wProc.Add('<procDPEC>');
      wProc.Add(FDadosMSG);
      wProc.Add(FRetWS);
      wProc.Add('</procDPEC>');
      FXML_ProcDPEC:=wProc.Text;
      wProc.Free;
      if FConfiguracoes.Geral.Salvar then
         FConfiguracoes.Geral.Save(FormatDateTime('yyyymmddhhnnss',Now)+'-procdpec.xml', FXML_ProcDPEC);
    end;

    RetDPEC.Free;

  finally
    {$IFDEF ACBrNFeOpenSSL}
       HTTP.Free;
    {$ELSE}
       ReqResp.Free;
    {$ENDIF}
    Acao.Free;
    Stream.Free;
    NotaUtil.ConfAmbiente;
    TACBrNFe( FACBrNFe ).SetStatus( stIdle );
  end;
end;

{ TNFeConsultaDPEC }
function TNFeConsultaDPEC.Executar: Boolean;
var
  Texto : String;
  Acao  : TStringList ;
  Stream: TMemoryStream;
  StrStream: TStringStream;
  aMsg : String;
  {$IFDEF ACBrNFeOpenSSL}
     HTTP: THTTPSend;
  {$ELSE}
     ReqResp: THTTPReqResp;
  {$ENDIF}
  FretDPEC: TRetDPEC;
begin
  inherited Executar;

  Acao := TStringList.Create;
  Stream := TMemoryStream.Create;
  Texto := '<?xml version="1.0" encoding="utf-8"?>';
  Texto := Texto + '<soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">';
  Texto := Texto + '  <soap:Header>';
  Texto := Texto + '    <sceCabecMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/SCEConsultaRFB">';
  Texto := Texto + '      <versaoDados>'+NFeConsDPEC+'</versaoDados>';
  Texto := Texto + '    </sceCabecMsg>';
  Texto := Texto + '  </soap:Header>';
  Texto := Texto + '  <soap:Body>';
  Texto := Texto + '    <sceDadosMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/SCEConsultaRFB">';
  Texto := Texto + FDadosMsg;
  Texto := Texto +     '</sceDadosMsg>';
  Texto := Texto + '  </soap:Body>';
  Texto := Texto + '</soap:Envelope>';

  Acao.Text := Texto;

  {$IFDEF ACBrNFeOpenSSL}
     Acao.SaveToStream(Stream);
     HTTP := THTTPSend.Create;
  {$ELSE}
     ReqResp := THTTPReqResp.Create(nil);
     ReqResp.OnBeforePost := OnBeforePost;
     ReqResp.URL := FURL;
     ReqResp.UseUTF8InHeader := True;
     ReqResp.SoapAction := 'http://www.portalfiscal.inf.br/nfe/wsdl/SCEConsultaRFB/sceConsultaDPEC';
  {$ENDIF}

  FretDPEC:= TRetDPEC.Create;
  try
    TACBrNFe( FACBrNFe ).SetStatus( stNFeConsultaDPEC );
    //if Assigned(FretDPEC) then
    //   FretDPEC.Free;

    if FConfiguracoes.Geral.Salvar then
     begin
       FPathArqEnv := FormatDateTime('yyyymmddhhnnss',Now)+'-cons-dpec.xml';
       FConfiguracoes.Geral.Save(FPathArqEnv, FDadosMsg);
     end;
    FRetWS := '';
    {$IFDEF ACBrNFeOpenSSL}
       HTTP.Document.LoadFromStream(Stream);
       ConfiguraHTTP(HTTP,'SOAPAction: "http://www.portalfiscal.inf.br/nfe/wsdl/SCEConsultaRFB/sceConsultaDPEC"');
       HTTP.HTTPMethod('POST', FURL);

       StrStream := TStringStream.Create('');
       StrStream.CopyFrom(HTTP.Document, 0);
       FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
       FRetWS := SeparaDados( FRetornoWS,'sceConsultaDPECResult',True);
       StrStream.Free;
    {$ELSE}
       ReqResp.Execute(Acao.Text, Stream);
       StrStream := TStringStream.Create('');
       StrStream.CopyFrom(Stream, 0);
       FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
       FRetWS := SeparaDados( FRetornoWS,'sceConsultaDPECResult',True);
       StrStream.Free;
    {$ENDIF}
    if FConfiguracoes.Geral.Salvar then
     begin
       FPathArqResp := FormatDateTime('yyyymmddhhnnss',Now)+'-sit-dpec.xml';
       FConfiguracoes.Geral.Save(FPathArqResp, FRetWS);
     end;

    //FretDPEC := TRetDPEC.Create;
    FretDPEC.Leitor.Arquivo := FRetWS;
    FretDPEC.LerXml;

    aMsg := 'Vers�o Aplicativo : '+{RetDPEC}FretDPEC.verAplic+LineBreak+
            'ID : '+{RetDPEC}FretDPEC.Id+LineBreak+
            'Status C�digo : '+IntToStr({RetDPEC}FretDPEC.cStat)+LineBreak+
            'Status Descri��o : '+{RetDPEC}FretDPEC.xMotivo+LineBreak+
            'Data Registro : '+DateTimeToStr({RetDPEC}FretDPEC.dhRegDPEC)+LineBreak+
            'nRegDPEC : '+{RetDPEC}FretDPEC.nRegDPEC+LineBreak+
            'ChaveNFe : '+{RetDPEC}FretDPEC.chNFE;

    TACBrNFe( FACBrNFe ).SetStatus( stIdle );
    if FConfiguracoes.WebServices.Visualizar then
       ShowMessage(aMsg);

    if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
       TACBrNFe( FACBrNFe ).OnGerarLog(aMsg);

    FverAplic := {RetDPEC}FretDPEC.verAplic;
    FcStat    := {RetDPEC}FretDPEC.cStat;
    FxMotivo  := {RetDPEC}FretDPEC.xMotivo;
    FTpAmb    := {RetDPEC}FretDPEC.tpAmb;
    FnRegDPEC  := {RetDPEC}FretDPEC.nRegDPEC;
    FNFeChave  := {RetDPEC}FretDPEC.chNFE;
    FdhRegDPEC := {RetDPEC}FretDPEC.dhRegDPEC;

    FMsg      := {RetDPEC}FretDPEC.XMotivo;
    Result := ({RetDPEC}FretDPEC.cStat = 125);

  finally
    {$IFDEF ACBrNFeOpenSSL}
       HTTP.Free;
    {$ELSE}
       ReqResp.Free;
    {$ENDIF}
    Acao.Free;
    Stream.Free;
    NotaUtil.ConfAmbiente;
    TACBrNFe( FACBrNFe ).SetStatus( stIdle );
    FretDPEC.Free;
  end;
end;

procedure TNFeConsultaDPEC.SetNFeChave(const Value: String);
begin
  if DFeUtil.NaoEstaVazio(Value) then
     FnRegDPEC := '';
  FNFeChave := StringReplace(Value,'NFe','',[rfReplaceAll]);
end;

procedure TNFeConsultaDPEC.SetnRegDPEC(const Value: String);
begin
  if DFeUtil.NaoEstaVazio(Value) then
     FNFeChave := '';
  FnRegDPEC := Value;
end;

{ TNFeCartaCorrecao }

constructor TNFeCartaCorrecao.Create(AOwner: TComponent; ACCe: TCCeNFe);
begin
  inherited Create(AOwner);

  FCCe := ACCe;
end;

destructor TNFeCartaCorrecao.Destroy;
begin
  if Assigned(FCCeRetorno) then
     FCCeRetorno.Free;
  inherited;
end;

function TNFeCartaCorrecao.Executar: Boolean;
var
  aMsg, NomeArq: string;
  Texto : String;
  Acao  : TStringList ;
  Stream: TMemoryStream;
  StrStream: TStringStream;
  wProc  : TStringList ;
  i,j : integer;
  Leitor : TLeitor;
  {$IFDEF ACBrNFeOpenSSL}
     HTTP: THTTPSend;
  {$ELSE}
     ReqResp: THTTPReqResp;
  {$ENDIF}
begin
  FCCe.idLote := idLote;
  if Assigned(FCCeRetorno) then
     FCCeRetorno.Free;

  inherited Executar;

  Acao := TStringList.Create;
  Stream := TMemoryStream.Create;

  Texto := '<?xml version="1.0" encoding="utf-8"?>';
  Texto := Texto + '<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">';
  Texto := Texto +   '<soap12:Header>';
  Texto := Texto +     '<nfeCabecMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento">';
  Texto := Texto +       '<cUF>'+IntToStr(FConfiguracoes.WebServices.UFCodigo)+'</cUF>';
  Texto := Texto +       '<versaoDados>'+NFeCCeNFe+'</versaoDados>';
  Texto := Texto +     '</nfeCabecMsg>';
  Texto := Texto +   '</soap12:Header>';
  Texto := Texto +   '<soap12:Body>';
  Texto := Texto +     '<nfeDadosMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento">';
  Texto := Texto + FDadosMsg;
  Texto := Texto +     '</nfeDadosMsg>';
  Texto := Texto +   '</soap12:Body>';
  Texto := Texto +'</soap12:Envelope>';

  Acao.Text := Texto;

   {$IFDEF ACBrNFeOpenSSL}
     Acao.SaveToStream(Stream);
     HTTP := THTTPSend.Create;
  {$ELSE}
     ReqResp := THTTPReqResp.Create(nil);
     ConfiguraReqResp( ReqResp );
     ReqResp.URL := FURL;
     ReqResp.UseUTF8InHeader := True;
     ReqResp.SoapAction := 'http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento';
  {$ENDIF}

  try
    TACBrNFe( FACBrNFe ).SetStatus( stNFeCCe );
    FPathArqEnv := IntToStr(FCCe.idLote)+ '-ped-cce.xml';

    if FConfiguracoes.Geral.Salvar then
      FConfiguracoes.Geral.Save(FPathArqEnv, FDadosMsg);

    if FConfiguracoes.Arquivos.Salvar then
     begin
       if not FConfiguracoes.Arquivos.SalvarCCeCanEvento then
          FConfiguracoes.Geral.Save(FPathArqEnv, FDadosMsg, FConfiguracoes.Arquivos.GetPathCCe)
       else
          FConfiguracoes.Geral.Save(FPathArqEnv, FDadosMsg, FConfiguracoes.Arquivos.GetPathEvento(teCCe));
     end;

    {$IFDEF ACBrNFeOpenSSL}
       HTTP.Document.LoadFromStream(Stream);
       ConfiguraHTTP(HTTP,'SOAPAction: "http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento"');
       HTTP.HTTPMethod('POST', FURL);

       StrStream := TStringStream.Create('');
       StrStream.CopyFrom(HTTP.Document, 0);
       FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
       FRetWS := SeparaDados( FRetornoWS,'nfeRecepcaoEventoResult');
       StrStream.Free;
    {$ELSE}
       ReqResp.Execute(Acao.Text, Stream);
       StrStream := TStringStream.Create('');
       StrStream.CopyFrom(Stream, 0);
       FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
       FRetWS := SeparaDados( FRetornoWS,'nfeRecepcaoEventoResult');
       StrStream.Free;
    {$ENDIF}

    FCCeRetorno := TRetCCeNFe.Create;
    FCCeRetorno.Leitor.Arquivo := FRetWS;
    FCCeRetorno.LerXml;

    TACBrNFe( FACBrNFe ).SetStatus( stIdle );
    aMsg := 'Ambiente : '+TpAmbToStr(CCeRetorno.tpAmb)+LineBreak+
            'Vers�o Aplicativo : '+CCeRetorno.verAplic+LineBreak+
            'Status C�digo : '+IntToStr(CCeRetorno.cStat)+LineBreak+
            'Status Descri��o : '+CCeRetorno.xMotivo+LineBreak+
            'Recebimento : '+DFeUtil.SeSenao(CCeRetorno.retEvento.Items[0].RetInfEvento.dhRegEvento = 0, '', DateTimeToStr(CCeRetorno.retEvento.Items[0].RetInfEvento.dhRegEvento));
    if FConfiguracoes.WebServices.Visualizar then
      ShowMessage(aMsg);

    if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
       TACBrNFe( FACBrNFe ).OnGerarLog(aMsg);

    FcStat   := CCeRetorno.cStat;
    FxMotivo := CCeRetorno.xMotivo;
    // Altera��o realizada por Italo em 30/08/2011 conforme sugest�o do Wilson
    /// Alterado linha abaixo para retornar a mensagem da informa��o do Evento e n�o o xMotivo pois o mesmo j�
    /// se encontra na classe acima "FxMotivo"
    // FMsg     := CCeRetorno.retEvento.Items[0].RetInfEvento.xMotivo;
    //Altera��o desfeita, pois primeiro deve ser visto se o lote foi processado e depois verificar nos eventos qual foi o resultado de cada um.
    FMsg     := CCeRetorno.xMotivo;
    FTpAmb   := CCeRetorno.tpAmb;

    /// Alterado a linha Abaixo para Result=True apenas se o lote foi processado e o evento retornou sucesso e n�o rejei��o.
    // Result   := (CCeRetorno.cStat = 128) and ((CCeRetorno.retEvento.Items[0].RetInfEvento.cStat = 135) or (CCeRetorno.retEvento.Items[0].RetInfEvento.cStat = 136));
    // Desfeito altera��o pois um lote pode ter v�rios eventos e o primeiro ser processado e os demais n�o. A aplica��o deve verificar se o lote foi processado e verificar se cada evento foi aceito
    Result   := (CCeRetorno.cStat = 128) or (CCeRetorno.cStat = 135) or (CCeRetorno.cStat = 136);

    FPathArqResp := IntToStr(FCCe.idLote) + '-cce.xml';
    if FConfiguracoes.Geral.Salvar then
      FConfiguracoes.Geral.Save(FPathArqResp, FRetWS);

    if FConfiguracoes.Arquivos.Salvar then
     begin
       if not FConfiguracoes.Arquivos.SalvarCCeCanEvento then
          FConfiguracoes.Geral.Save(FPathArqResp, FRetWS, FConfiguracoes.Arquivos.GetPathCCe)
       else
          FConfiguracoes.Geral.Save(FPathArqResp, FRetWS, FConfiguracoes.Arquivos.GetPathEvento(teCCe));
     end;

    //gerar arquivo proc de cce
    if Result then
    begin
      Leitor := TLeitor.Create;
      for i:= 0 to FCCe.Evento.Count-1 do
       begin
        for j:= 0 to CCeRetorno.retEvento.Count-1 do
         begin
           if FCCe.Evento.Items[i].InfEvento.chNFe = CCeRetorno.retEvento.Items[j].RetInfEvento.chNFe then
            begin
              wProc := TStringList.Create;
              wProc.Add('<?xml version="1.0" encoding="UTF-8" ?>');
              wProc.Add('<procEventoNFe versao="' + NFeCCeNFe + '" xmlns="http://www.portalfiscal.inf.br/nfe">');
              wProc.Add('<evento versao="' + NFeCCeNFe + '">');
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

              wProc.Add('</evento>');
              wProc.Add('<retEvento versao="' + NFeCCeNFe + '">');
              Leitor.Arquivo := FRetWS;
              wProc.Add(UTF8Encode(Leitor.rExtrai(1, 'infEvento', '', j + 1)));
              wProc.Add('</infEvento>');
              wProc.Add('</retEvento>');
              wProc.Add('</procEventoNFe>');

              CCeRetorno.retEvento.Items[j].RetInfEvento.XML:=wProc.Text;

              NomeArq := FCCe.Evento.Items[i].InfEvento.chNFe +
                         '110110' +
                         IntToStr(FCCe.Evento.Items[i].InfEvento.nSeqEvento) +
                         '-procEventoNFe.xml';

              if FConfiguracoes.Geral.Salvar then
                 FConfiguracoes.Geral.Save(NomeArq, wProc.Text);

              if FConfiguracoes.Arquivos.Salvar then
               begin
                 if not FConfiguracoes.Arquivos.SalvarCCeCanEvento then
                    FConfiguracoes.Geral.Save(NomeArq, wProc.Text, FConfiguracoes.Arquivos.GetPathCCe)
                 else
                    FConfiguracoes.Geral.Save(NomeArq, wProc.Text, FConfiguracoes.Arquivos.GetPathEvento(teCCe));
               end;
              wProc.Free;
              break;
            end;
         end;
       end;
      Leitor.Free;
    end;
  finally
    {$IFDEF ACBrNFeOpenSSL}
       HTTP.Free;
    {$ELSE}
       ReqResp.Free;
    {$ENDIF}
    Acao.Free;
    Stream.Free;
    NotaUtil.ConfAmbiente;
    TACBrNFe( FACBrNFe ).SetStatus( stIdle );
  end;
end;

{ TNFeEnvEvento }
constructor TNFeEnvEvento.Create(AOwner: TComponent; AEvento: TEventoNFe);
begin
  inherited Create(AOwner);

  FEvento := AEvento;
end;

destructor TNFeEnvEvento.Destroy;
begin
  if Assigned(FEventoRetorno) then
     FEventoRetorno.Free;
  inherited;
end;

function TNFeEnvEvento.Executar: Boolean;
var
  aMsg, NomeArq: string;
  Texto : String;
  Acao  : TStringList ;
  Stream: TMemoryStream;
  StrStream: TStringStream;
  wProc  : TStringList ;
  i,j : integer;
  Leitor : TLeitor;
  {$IFDEF ACBrNFeOpenSSL}
     HTTP: THTTPSend;
  {$ELSE}
     ReqResp: THTTPReqResp;
  {$ENDIF}
begin
  FEvento.idLote := idLote;
  if Assigned(FEventoRetorno) then
     FEventoRetorno.Free;

  inherited Executar;

  Acao := TStringList.Create;
  Stream := TMemoryStream.Create;

  Texto := '<?xml version="1.0" encoding="utf-8"?>';
  Texto := Texto + '<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">';
  Texto := Texto +   '<soap12:Header>';
  Texto := Texto +     '<nfeCabecMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento">';
  Texto := Texto +       '<cUF>'+IntToStr(FConfiguracoes.WebServices.UFCodigo)+'</cUF>';
  Texto := Texto +       '<versaoDados>'+NFeEventoNFe+'</versaoDados>';
  Texto := Texto +     '</nfeCabecMsg>';
  Texto := Texto +   '</soap12:Header>';
  Texto := Texto +   '<soap12:Body>';
  Texto := Texto +     '<nfeDadosMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento">';
  Texto := Texto +       FDadosMsg;
  Texto := Texto +     '</nfeDadosMsg>';
  Texto := Texto +   '</soap12:Body>';
  Texto := Texto +'</soap12:Envelope>';

  Acao.Text := Texto;

   {$IFDEF ACBrNFeOpenSSL}
     Acao.SaveToStream(Stream);
     HTTP := THTTPSend.Create;
  {$ELSE}
     ReqResp := THTTPReqResp.Create(nil);
     ConfiguraReqResp( ReqResp );
     ReqResp.URL := FURL;
     ReqResp.UseUTF8InHeader := True;
     ReqResp.SoapAction := 'http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento';
  {$ENDIF}

  try
    TACBrNFe( FACBrNFe ).SetStatus( stNFeEvento );
    FPathArqEnv := IntToStr(FEvento.idLote)+'-ped-evento.xml';

    if FConfiguracoes.Geral.Salvar then
      FConfiguracoes.Geral.Save(FPathArqEnv, FDadosMsg);

    if FConfiguracoes.Arquivos.Salvar then
     begin
       if (FEvento.Evento.Items[0].InfEvento.tpEvento = teCCe) and not FConfiguracoes.Arquivos.SalvarCCeCanEvento then
          FConfiguracoes.Geral.Save(FPathArqEnv, FDadosMsg, FConfiguracoes.Arquivos.GetPathCCe)
       else if (FEvento.Evento.Items[0].InfEvento.tpEvento = teCancelamento) and not FConfiguracoes.Arquivos.SalvarCCeCanEvento then
          FConfiguracoes.Geral.Save(FPathArqEnv, FDadosMsg, FConfiguracoes.Arquivos.GetPathCan)
       else
          FConfiguracoes.Geral.Save(FPathArqEnv, FDadosMsg, FConfiguracoes.Arquivos.GetPathEvento(FEvento.Evento.Items[0].InfEvento.tpEvento));
     end;

    {$IFDEF ACBrNFeOpenSSL}
       HTTP.Document.LoadFromStream(Stream);
       ConfiguraHTTP(HTTP,'SOAPAction: "http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento"');
       HTTP.HTTPMethod('POST', FURL);

       StrStream := TStringStream.Create('');
       StrStream.CopyFrom(HTTP.Document, 0);
       FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
       FRetWS := SeparaDados( FRetornoWS,'nfeRecepcaoEventoResult');
       StrStream.Free;
    {$ELSE}
       ReqResp.Execute(Acao.Text, Stream);
       StrStream := TStringStream.Create('');
       StrStream.CopyFrom(Stream, 0);
       FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
       FRetWS := SeparaDados( FRetornoWS,'nfeRecepcaoEventoResult');
       StrStream.Free;
    {$ENDIF}

    FPathArqResp := IntToStr(FEvento.idLote) + '-eve.xml';
    if FConfiguracoes.Geral.Salvar then
      FConfiguracoes.Geral.Save(FPathArqResp, FRetWS);

    FEventoRetorno                := TRetEventoNFe.Create;
    FEventoRetorno.Leitor.Arquivo := FRetWS;
    FEventoRetorno.LerXml;

    TACBrNFe( FACBrNFe ).SetStatus( stIdle );
    aMsg := 'Ambiente : '+TpAmbToStr(EventoRetorno.tpAmb)+LineBreak+
            'Vers�o Aplicativo : '+EventoRetorno.verAplic+LineBreak+
            'Status C�digo : '+IntToStr(EventoRetorno.cStat)+LineBreak+
            'Status Descri��o : '+EventoRetorno.xMotivo+LineBreak;
    if (EventoRetorno.retEvento.Count > 0) then
      aMsg := aMsg + 'Recebimento : '+DFeUtil.SeSenao(EventoRetorno.retEvento.Items[0].RetInfEvento.dhRegEvento = 0,
                                                       '',
                                                       DateTimeToStr(EventoRetorno.retEvento.Items[0].RetInfEvento.dhRegEvento));
    if FConfiguracoes.WebServices.Visualizar then
      ShowMessage(aMsg);

    if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
       TACBrNFe( FACBrNFe ).OnGerarLog(aMsg);

    FcStat   := EventoRetorno.cStat;
    FxMotivo := EventoRetorno.xMotivo;
    FMsg     := EventoRetorno.xMotivo;
    FTpAmb   := EventoRetorno.tpAmb;
    Result   := (EventoRetorno.cStat = 128) or (EventoRetorno.cStat = 135) or (EventoRetorno.cStat = 136);

    FPathArqResp := IntToStr(FEvento.idLote) + '-eve.xml';

    if FConfiguracoes.Geral.Salvar then
      FConfiguracoes.Geral.Save(FPathArqResp, FRetWS);

    if FConfiguracoes.Arquivos.Salvar then
     begin
       if (FEvento.Evento.Items[0].InfEvento.tpEvento = teCCe) and not FConfiguracoes.Arquivos.SalvarCCeCanEvento  then
          FConfiguracoes.Geral.Save(FPathArqResp, FRetWS, FConfiguracoes.Arquivos.GetPathCCe)
       else if (FEvento.Evento.Items[0].InfEvento.tpEvento = teCancelamento) and not FConfiguracoes.Arquivos.SalvarCCeCanEvento  then
          FConfiguracoes.Geral.Save(FPathArqEnv, FDadosMsg, FConfiguracoes.Arquivos.GetPathCan)
       else
          FConfiguracoes.Geral.Save(FPathArqResp, FRetWS, FConfiguracoes.Arquivos.GetPathEvento(FEvento.Evento.Items[0].InfEvento.tpEvento));
     end;

    //gerar arquivo proc de evento
    if Result then
    begin
      Leitor := TLeitor.Create;
      try
         for i:= 0 to FEvento.Evento.Count-1 do
          begin
           for j:= 0 to EventoRetorno.retEvento.Count-1 do
            begin
              if FEvento.Evento.Items[i].InfEvento.chNFe = EventoRetorno.retEvento.Items[j].RetInfEvento.chNFe then
               begin
                 FEvento.Evento.Items[i].RetInfEvento.nProt       := EventoRetorno.retEvento.Items[j].RetInfEvento.nProt;
                 FEvento.Evento.Items[i].RetInfEvento.dhRegEvento := EventoRetorno.retEvento.Items[j].RetInfEvento.dhRegEvento;
                 FEvento.Evento.Items[i].RetInfEvento.cStat       := EventoRetorno.retEvento.Items[j].RetInfEvento.cStat;

                 wProc := TStringList.Create;
                 wProc.Add('<?xml version="1.0" encoding="UTF-8" ?>');
                 wProc.Add('<procEventoNFe versao="' + NFeEventoNFe + '" xmlns="http://www.portalfiscal.inf.br/nfe">');
                 wProc.Add('<evento versao="' + NFeEventoNFe + '">');
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

                 wProc.Add('</evento>');
                 wProc.Add('<retEvento versao="' + NFeEventoNFe + '">');
                 Leitor.Arquivo := FRetWS;
                 wProc.Add(UTF8Encode(Leitor.rExtrai(1, 'infEvento', '', j + 1)));
                 wProc.Add('</infEvento>');
                 wProc.Add('</retEvento>');
                 wProc.Add('</procEventoNFe>');

                 EventoRetorno.retEvento.Items[j].RetInfEvento.XML:=wProc.Text;

                 NomeArq := FEvento.Evento.Items[i].InfEvento.chNFe +
                            FEvento.Evento.Items[i].InfEvento.TipoEvento +
                            IntToStr(FEvento.Evento.Items[i].InfEvento.nSeqEvento) +
                            '-procEventoNFe.xml';

              {   NomeArq := FEvento.Evento.Items[i].InfEvento.id +
                            '-procEventoNFe.xml'; }

                 if FConfiguracoes.Geral.Salvar then
                    FConfiguracoes.Geral.Save(NomeArq, wProc.Text);

                 if FConfiguracoes.Arquivos.Salvar then
                  begin
                    if (FEvento.Evento.Items[0].InfEvento.tpEvento = teCCe) and not FConfiguracoes.Arquivos.SalvarCCeCanEvento  then
                       FConfiguracoes.Geral.Save(NomeArq, wProc.Text, FConfiguracoes.Arquivos.GetPathCCe)
                    else if (FEvento.Evento.Items[0].InfEvento.tpEvento = teCancelamento) and not FConfiguracoes.Arquivos.SalvarCCeCanEvento then
                       FConfiguracoes.Geral.Save(NomeArq, wProc.Text, FConfiguracoes.Arquivos.GetPathCan)
                    else
                       FConfiguracoes.Geral.Save(NomeArq, wProc.Text, FConfiguracoes.Arquivos.GetPathEvento(FEvento.Evento.Items[0].InfEvento.tpEvento));
                  end;
                 wProc.Free;
                 break;
               end;
            end;
          end;
      finally
         Leitor.Free;
      end;   
    end;
  finally
    {$IFDEF ACBrNFeOpenSSL}
       HTTP.Free;
    {$ELSE}
       ReqResp.Free;
    {$ENDIF}
    Acao.Free;
    Stream.Free;
    NotaUtil.ConfAmbiente;
    TACBrNFe( FACBrNFe ).SetStatus( stIdle );
  end;
end;

{ TNFeConsNFeDest }
constructor TNFeConsNFeDest.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
  if not Assigned(FretConsNFeDest) then
    FretConsNFeDest := TretConsNFeDest.Create;
end;

destructor TNFeConsNFeDest.Destroy;
begin
  if Assigned(FretConsNFeDest) then
    FretConsNFeDest.Free;
  inherited;
end;

function TNFeConsNFeDest.Executar: Boolean;
var
  aMsg: string;
  Texto : String;
  Acao  : TStringList ;
  Stream: TMemoryStream;
  StrStream: TStringStream;

  {$IFDEF ACBrNFeOpenSSL}
     HTTP: THTTPSend;
  {$ELSE}
     ReqResp: THTTPReqResp;
  {$ENDIF}
begin
  inherited Executar;

  Result := False;

  Acao := TStringList.Create;
  Stream := TMemoryStream.Create;

  Texto := '<?xml version="1.0" encoding="utf-8"?>';
  Texto := Texto + '<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">';
  Texto := Texto +   '<soap12:Header>';
  Texto := Texto +     '<nfeCabecMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsultaDest">';
  Texto := Texto +       '<cUF>'+IntToStr(FConfiguracoes.WebServices.UFCodigo)+'</cUF>';
//<indComp>string</indComp>

  Texto := Texto +       '<versaoDados>'+NFeConsNFeDest+'</versaoDados>';
  Texto := Texto +     '</nfeCabecMsg>';
  Texto := Texto +   '</soap12:Header>';
  Texto := Texto +   '<soap12:Body>';
  Texto := Texto +     '<nfeDadosMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsultaDest">';
  Texto := Texto + FDadosMsg;
  Texto := Texto +     '</nfeDadosMsg>';
  Texto := Texto +   '</soap12:Body>';
  Texto := Texto +'</soap12:Envelope>';

  Acao.Text := Texto;

  {$IFDEF ACBrNFeOpenSSL}
     Acao.SaveToStream(Stream);
     HTTP := THTTPSend.Create;
  {$ELSE}
     ReqResp := THTTPReqResp.Create(nil);
     ConfiguraReqResp( ReqResp );
     ReqResp.URL := FURL;
     ReqResp.UseUTF8InHeader := True;
     ReqResp.SoapAction := 'http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsultaDest/nfeConsultaNFDest';
  {$ENDIF}

  // Movido para fora do try por Italo em 16/08/2012
//  if Assigned(FretConsNFeDest)
//   then FretConsNFeDest.Free;
  if Assigned(FretConsNFeDest)
   then FreeAndNil(FretConsNFeDest);

  FretConsNFeDest := TRetConsNFeDest.Create;

  try
    TACBrNFe( FACBrNFe ).SetStatus( stConsNFeDest );
    if FConfiguracoes.Geral.Salvar then
     begin
       FPathArqEnv := FormatDateTime('yyyymmddhhnnss',Now)+'-con-nfe-dest.xml';
       FConfiguracoes.Geral.Save(FPathArqEnv, FDadosMsg);
     end;

    try
      {$IFDEF ACBrNFeOpenSSL}
         HTTP.Document.LoadFromStream(Stream);
         ConfiguraHTTP(HTTP,'SOAPAction: "http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsultaDest/nfeConsultaNFDest"');
         HTTP.HTTPMethod('POST', FURL);
         StrStream := TStringStream.Create('');
         StrStream.CopyFrom(HTTP.Document, 0);
         FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
         FRetWS := SeparaDados( FRetornoWS,'nfeConsultaNFDestResult');
         StrStream.Free;
      {$ELSE}
         ReqResp.Execute(Acao.Text, Stream);
         StrStream := TStringStream.Create('');
         StrStream.CopyFrom(Stream, 0);
         FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
         FRetWS := SeparaDados( FRetornoWS,'nfeConsultaNFDestResult');
         StrStream.Free;
      {$ENDIF}

      FretConsNFeDest.Leitor.Arquivo := FRetWS;
      FretConsNFeDest.LerXml;

      TACBrNFe( FACBrNFe ).SetStatus( stIdle );
      aMsg := 'Vers�o : '+FretConsNFeDest.versao+LineBreak+
              'Ambiente : '+TpAmbToStr(FretConsNFeDest.tpAmb)+LineBreak+
              'Vers�o Aplicativo : '+FretConsNFeDest.verAplic+LineBreak+
              'Status C�digo : '+IntToStr(FretConsNFeDest.cStat)+LineBreak+
              'Status Descri��o : '+FretConsNFeDest.xMotivo+LineBreak+
              'Recebimento : '+DFeUtil.SeSenao(FretConsNFeDest.dhResp = 0, '', DateTimeToStr(RetConsNFeDest.dhResp))+LineBreak+
              'Ind. Continua��o : '+IndicadorContinuacaoToStr(FretConsNFeDest.indCont)+LineBreak+
              '�ltimo NSU : '+FretConsNFeDest.ultNSU+LineBreak;
              
      if FConfiguracoes.WebServices.Visualizar then
        ShowMessage(aMsg);

      if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
         TACBrNFe( FACBrNFe ).OnGerarLog(aMsg);

      Result := (FretConsNFeDest.CStat =137) or (FretConsNFeDest.CStat =138);

      if FConfiguracoes.Geral.Salvar then
       begin
         FPathArqResp := FormatDateTime('yyyymmddhhnnss',Now)+'-nfe-dest.xml';
         FConfiguracoes.Geral.Save(FPathArqResp, FRetWS);
       end;

    except on E: Exception do
      begin
       if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
          TACBrNFe( FACBrNFe ).OnGerarLog('WebService Consulta NF-e Destinadas:'+LineBreak+
                                          '- Inativo ou Inoperante tente novamente.'+LineBreak+
                                          '- '+E.Message);
       raise EACBrNFeException.Create('WebService Consulta NF-e Destinadas:'+LineBreak+
                              '- Inativo ou Inoperante tente novamente.'+LineBreak+
                              '- '+E.Message);
      end;
    end;
  finally
    {$IFDEF ACBrNFeOpenSSL}
      HTTP.Free;
    {$ELSE}
      ReqResp.Free;
    {$ENDIF}
    Acao.Free;
    Stream.Free;
    NotaUtil.ConfAmbiente;
    TACBrNFe( FACBrNFe ).SetStatus( stIdle );
  end;
end;

{ TNFeDownloadNFe }
constructor TNFeDownloadNFe.Create(AOwner: TComponent;
  ADownload: TDownloadNFe);
begin
  inherited Create(AOwner);

 FDownload := ADownload;
end;

destructor TNFeDownloadNFe.Destroy;
begin
  if Assigned(FRetDownloadNFe) then
     FRetDownloadNFe.Free;

  inherited;
end;

function TNFeDownloadNFe.Executar: Boolean;
var
  aMsg: string;
  Texto : String;
  Acao  : TStringList ;
  Stream: TMemoryStream;
  StrStream: TStringStream;
  i: Integer;

  {$IFDEF ACBrNFeOpenSSL}
     HTTP: THTTPSend;
  {$ELSE}
     ReqResp: THTTPReqResp;
  {$ENDIF}
begin
  inherited Executar;

  Result := False;

  Acao := TStringList.Create;
  Stream := TMemoryStream.Create;

  Texto := '<?xml version="1.0" encoding="utf-8"?>';
  Texto := Texto + '<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">';
  Texto := Texto +   '<soap12:Header>';
  Texto := Texto +     '<nfeCabecMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/NfeDownloadNF">';
  Texto := Texto +       '<cUF>'+IntToStr(FConfiguracoes.WebServices.UFCodigo)+'</cUF>';
  Texto := Texto +       '<versaoDados>'+NFeDownloadNFe+'</versaoDados>';
  Texto := Texto +     '</nfeCabecMsg>';
  Texto := Texto +   '</soap12:Header>';
  Texto := Texto +   '<soap12:Body>';
  Texto := Texto +     '<nfeDadosMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/NfeDownloadNF">';
  Texto := Texto + FDadosMsg;
  Texto := Texto +     '</nfeDadosMsg>';
  Texto := Texto +   '</soap12:Body>';
  Texto := Texto +'</soap12:Envelope>';

  Acao.Text := Texto;

  {$IFDEF ACBrNFeOpenSSL}
     Acao.SaveToStream(Stream);
     HTTP := THTTPSend.Create;
  {$ELSE}
     ReqResp := THTTPReqResp.Create(nil);
     ConfiguraReqResp( ReqResp );
     ReqResp.URL := FURL;
     ReqResp.UseUTF8InHeader := True;
     ReqResp.SoapAction := 'http://www.portalfiscal.inf.br/nfe/wsdl/NfeDownloadNF/nfeDownloadNF';
  {$ENDIF}

  // Movido para fora do try por Italo em 23/08/2012
  if Assigned(FRetDownloadNFe)
   then FreeAndNil(FRetDownloadNFe);

  FRetDownloadNFe := TRetDownloadNFe.Create;

  try
    // Alterado por Italo em 17/07/2012
    TACBrNFe( FACBrNFe ).SetStatus( stDownloadNFe );
    if FConfiguracoes.Geral.Salvar then
     begin
       FPathArqEnv := FormatDateTime('yyyymmddhhnnss',Now)+'-ped-down-nfe.xml';
       FConfiguracoes.Geral.Save(FPathArqEnv, FDadosMsg);
     end;

    try
      {$IFDEF ACBrNFeOpenSSL}
         HTTP.Document.LoadFromStream(Stream);
         ConfiguraHTTP(HTTP,'SOAPAction: "http://www.portalfiscal.inf.br/nfe/wsdl/NfeDownloadNF/nfeDownloadNF"');
         HTTP.HTTPMethod('POST', FURL);
         StrStream := TStringStream.Create('');
         StrStream.CopyFrom(HTTP.Document, 0);
         FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
         FRetWS := SeparaDados( FRetornoWS,'nfeDownloadNFResult');
         StrStream.Free;
      {$ELSE}
         ReqResp.Execute(Acao.Text, Stream);
         StrStream := TStringStream.Create('');
         StrStream.CopyFrom(Stream, 0);
         FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
         FRetWS := SeparaDados( FRetornoWS,'nfeDownloadNFResult');
         StrStream.Free;
      {$ENDIF}

      FRetDownloadNFe.Leitor.Arquivo := FRetWS;
      FRetDownloadNFe.LerXml;

      TACBrNFe( FACBrNFe ).SetStatus( stIdle );

      aMsg := 'Vers�o : '+FRetDownloadNFe.versao+LineBreak+
              'Ambiente : '+TpAmbToStr(FRetDownloadNFe.tpAmb)+LineBreak+
              'Vers�o Aplicativo : '+FRetDownloadNFe.verAplic+LineBreak+
              'Status C�digo : '+IntToStr(FRetDownloadNFe.cStat)+LineBreak+
              'Status Descri��o : '+FRetDownloadNFe.xMotivo+LineBreak+
              'Recebimento : '+DFeUtil.SeSenao(FRetDownloadNFe.dhResp = 0, '', DateTimeToStr(FRetDownloadNFe.dhResp))+LineBreak;

      if FConfiguracoes.WebServices.Visualizar then
        ShowMessage(aMsg);

      if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
         TACBrNFe( FACBrNFe ).OnGerarLog(aMsg);

      Result := (FRetDownloadNFe.cStat = 139);

      if FConfiguracoes.Geral.Salvar then
       begin
         FPathArqResp := FormatDateTime('yyyymmddhhnnss',Now)+'-down-nfe.xml';
         FConfiguracoes.Geral.Save(FPathArqResp, FRetWS);
       end;

      for i := 0 to FRetDownloadNFe.retNFe.Count - 1 do
       begin
         if FRetDownloadNFe.retNFe.Items[i].cStat = 140
          then begin
           FPathArqResp := FRetDownloadNFe.retNFe.Items[i].chNFe + '-nfe.xml';
           FConfiguracoes.Geral.Save(FPathArqResp, FRetDownloadNFe.retNFe.Items[i].procNFe);
          end;
       end;

    except on E: Exception do
      begin
       if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
          TACBrNFe( FACBrNFe ).OnGerarLog('WebService Download de NF-e:'+LineBreak+
                                          '- Inativo ou Inoperante tente novamente.'+LineBreak+
                                          '- '+E.Message);
       raise EACBrNFeException.Create('WebService Download de NF-e:'+LineBreak+
                              '- Inativo ou Inoperante tente novamente.'+LineBreak+
                              '- '+E.Message);
      end;
    end;
  finally
    {$IFDEF ACBrNFeOpenSSL}
      HTTP.Free;
    {$ELSE}
      ReqResp.Free;
    {$ENDIF}
    Acao.Free;
    Stream.Free;
    NotaUtil.ConfAmbiente;
    TACBrNFe( FACBrNFe ).SetStatus( stIdle );
  end;
end;

{ TNFeEnvioWebService }

function TNFeEnvioWebService.Executar: Boolean;
var
  Texto, Versao : String;
  Acao  : TStringList ;
  Stream: TMemoryStream;
  StrStream: TStringStream;

  {$IFDEF ACBrNFeOpenSSL}
     HTTP: THTTPSend;
  {$ELSE}
     ReqResp: THTTPReqResp;
  {$ENDIF}

  LeitorXML : TLeitor;
begin
  LeitorXML := TLeitor.Create;
  try
     LeitorXML.Arquivo := FXMLEnvio;
     LeitorXML.Grupo := FXMLEnvio;
     Versao := LeitorXML.rAtributo('versao')
  finally
     LeitorXML.Free;
  end;

  FDadosMsg := FXMLEnvio;

  FURL := FURLEnvio;

  Result := True;

  Acao := TStringList.Create;
  Stream := TMemoryStream.Create;

  Texto := '<?xml version="1.0" encoding="utf-8"?>';
  Texto := Texto + '<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">';
  Texto := Texto +   '<soap12:Header>';
  Texto := Texto +     '<nfeCabecMsg xmlns="'+FSoapActionEnvio+'">';
  Texto := Texto +       '<cUF>'+IntToStr(FConfiguracoes.WebServices.UFCodigo)+'</cUF>';
  Texto := Texto +       '<versaoDados>'+Versao+'</versaoDados>';
  Texto := Texto +     '</nfeCabecMsg>';
  Texto := Texto +   '</soap12:Header>';
  Texto := Texto +   '<soap12:Body>';
  Texto := Texto +     '<nfeDadosMsg xmlns="'+FSoapActionEnvio+'">';
  Texto := Texto + FDadosMsg;
  Texto := Texto +     '</nfeDadosMsg>';
  Texto := Texto +   '</soap12:Body>';
  Texto := Texto +'</soap12:Envelope>';

  Acao.Text := Texto;

  {$IFDEF ACBrNFeOpenSSL}
     Acao.SaveToStream(Stream);
     HTTP := THTTPSend.Create;
  {$ELSE}
     ReqResp := THTTPReqResp.Create(nil);
     ConfiguraReqResp( ReqResp );
     ReqResp.URL := FURL;
     ReqResp.UseUTF8InHeader := True;
     ReqResp.SoapAction := FSoapActionEnvio;
  {$ENDIF}

  try
    try
      {$IFDEF ACBrNFeOpenSSL}
         HTTP.Document.LoadFromStream(Stream);
         ConfiguraHTTP(HTTP,'SOAPAction: "'+FSoapActionEnvio+'"');
         HTTP.HTTPMethod('POST', FURL);
         StrStream := TStringStream.Create('');
         StrStream.CopyFrom(HTTP.Document, 0);
         FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
         FRetWS := SeparaDados( FRetornoWS,'soap:Body');
         StrStream.Free;
      {$ELSE}
         ReqResp.Execute(Acao.Text, Stream);
         StrStream := TStringStream.Create('');
         StrStream.CopyFrom(Stream, 0);
         FRetornoWS := TiraAcentos(ParseText(StrStream.DataString, True));
         FRetWS := SeparaDados( FRetornoWS,'soap:Body');
         StrStream.Free;
      {$ENDIF}

    except on E: Exception do
      begin
       Result := False;
       if Assigned(TACBrNFe( FACBrNFe ).OnGerarLog) then
          TACBrNFe( FACBrNFe ).OnGerarLog('WebService'+LineBreak+
                                          '- Inativo ou Inoperante tente novamente.'+LineBreak+
                                          '- '+E.Message);
       raise EACBrNFeException.Create('WebService'+LineBreak+
                              '- Inativo ou Inoperante tente novamente.'+LineBreak+
                              '- '+E.Message);
      end;
    end;
  finally
    {$IFDEF ACBrNFeOpenSSL}
      HTTP.Free;
    {$ELSE}
      ReqResp.Free;
    {$ENDIF}
    Acao.Free;
    Stream.Free;
    NotaUtil.ConfAmbiente;
  end;
end;

end.

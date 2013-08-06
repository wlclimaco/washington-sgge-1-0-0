{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2004 Daniel Simoes de Almeida               }
{                                                                              }
{ Colaboradores nesse arquivo:                                                 }
{                                                                              }
{  Voc� pode obter a �ltima vers�o desse arquivo na pagina do  Projeto ACBr    }
{ Componentes localizado em      http://www.sourceforge.net/projects/acbr      }
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

{$I ACBr.inc}

Unit ACBrECFClass ;

interface
uses ACBrDevice,
     SysUtils ,
     Classes,
     ACBrConsts,
     ACBrBase,
     Contnrs
     {$IFNDEF NOGUI}
       {$IFDEF VisualCLX},
         {$IFDEF QT3CLX} QtLibrary, QtSignalHooks {$ELSE} Qt {$ENDIF},
          QControls, QForms, QGraphics, QDialogs, QExtCtrls
       {$ENDIF}
       {$IFDEF VCL}
          , Controls, Forms, Graphics, Dialogs, ExtCtrls
       {$ENDIF}
       {$IFDEF MSWINDOWS}
         , Windows, messages
       {$ENDIF}
     {$ENDIF} ;

type

  EACBrECFErro            = class(Exception) ;
  EACBrECFCMDInvalido     = class(EACBrECFErro) ;
  EACBrECFSemResposta     = class(EACBrECFErro) ;
  EACBrECFSemPapel        = class(EACBrECFErro) ;
  EACBrECFTimeOut         = class(EACBrECFErro) ;
  EACBrECFNaoInicializado = class(EACBrECFErro) ;
  EACBrECFOcupado         = class(EACBrECFErro) ;

{ Definindo novo tipo para armazenar os dados que ir�o compor o rodap� }

{ TACBrECFRodape }

TACBrECFRodapeNotaLegalDF = class( TPersistent )
  private
    fsProgramaDeCredito: Boolean;
    fsValorICMS: Double;
    fsValorISS: Double;
    fsImprimir: Boolean;
  public
  published
    property Imprimir: Boolean read fsImprimir write fsImprimir default False;
    property ProgramaDeCredito: Boolean read fsProgramaDeCredito
       write fsProgramaDeCredito default False;
    property ValorICMS: Double read fsValorICMS write fsValorICMS stored false;
    property ValorISS : Double read fsValorISS  write fsValorISS  stored false;
end;

TACBrECFRodapeRestaurante = class( TPersistent )
  private
    fsECF : Integer;
    fsCER:  Integer;
    fsCOO:  Integer;
    fsMesa: String;
    fsImprimir: Boolean;
  public
  published
    property Imprimir : Boolean read fsImprimir write fsImprimir default False;
    property ECF      : Integer read fsECF      write fsECF stored false;
    property CER      : Integer read fsCER      write fsCER stored false;
    property COO      : Integer read fsCOO      write fsCOO stored false;
    property Mesa     : String  read fsMesa     write fsMesa stored false;
end;

TACBrECFRodape = class( TPersistent )
  private
    fsPreVenda: String;
    fsDavOs: String;
    fsMD5: String;
    fsDav: String;
    fsRestaurante: TACBrECFRodapeRestaurante;
    fsMinasLegal: Boolean;
    fsCupomMania: Boolean;
    fsNotaLegalDF: TACBrECFRodapeNotaLegalDF;
    fsParaibaLegal: Boolean;
    procedure SetMD5(AValue : String) ;
  public
    constructor Create;
    destructor Destroy; override;

    procedure Clear;
  published
    property MD5         : String  read fsMD5         write SetMD5;
    property Dav         : String  read fsDav         write fsDav   stored False;
    property DavOs       : String  read fsDavOs       write fsDavOs stored False;
    property PreVenda    : String  read fsPreVenda    write fsPreVenda stored False;
    property Restaurante : TACBrECFRodapeRestaurante read fsRestaurante write fsRestaurante;
    property CupomMania  : Boolean read fsCupomMania  write fsCupomMania default False;
    property MinasLegal  : Boolean read fsMinasLegal  write fsMinasLegal default False;
    property ParaibaLegal: Boolean read fsParaibaLegal write fsParaibaLegal default False;
    property NotaLegalDF : TACBrECFRodapeNotaLegalDF read fsNotaLegalDF write fsNotaLegalDF;
end;

{ Definindo novo tipo para armazenar Aliquota de ICMS }
TACBrECFAliquota = class
 private
    fsIndice: String;
    fsAliquota: Double ;
    fsTipo: Char;
    fsTotal: Double;
    fsSequencia: Byte;
    procedure SetTipo(const Value: Char);
 public
    constructor create ;
    procedure Assign( AAliquota : TACBrECFAliquota ) ;

    property Sequencia : Byte   read fsSequencia write fsSequencia ;
    property Indice    : String read fsIndice    write fsIndice ;
    property Aliquota  : Double read fsAliquota  write fsAliquota ;
    property Tipo      : Char read fsTipo write SetTipo ;
    property Total     : Double read fsTotal write fsTotal ;
end;

{ Lista de Objetos do tipo TACBrECFAliquota }
TACBrECFAliquotas = class(TObjectList)
  protected
    procedure SetObject (Index: Integer; Item: TACBrECFAliquota);
    function GetObject (Index: Integer): TACBrECFAliquota;
    procedure Insert (Index: Integer; Obj: TACBrECFAliquota);
  public
    function New: TACBrECFAliquota;
    function Add (Obj: TACBrECFAliquota): Integer;
    property Objects [Index: Integer]: TACBrECFAliquota
      read GetObject write SetObject; default;
  end;


{ Definindo novo tipo para armazenar as Formas de Pagamento }
TACBrECFFormaPagamento = class
 private
    fsIndice: String;
    fsDescricao: String;
    fsPermiteVinculado: Boolean;
    fsTotal: Double;
    fsData: TDateTime;
    fsTipoDoc: String;
 public
    constructor create ;
    procedure Assign( AFormaPagamento : TACBrECFFormaPagamento ) ;

    property Indice    : String read fsIndice    write fsIndice ;
    property Descricao : String read fsDescricao write fsDescricao ;
    property PermiteVinculado : Boolean read fsPermiteVinculado
                                       write fsPermiteVinculado ;
    property Total : Double read fsTotal write fsTotal ;
    property Data: TDateTime read fsData write fsData;
    property TipoDoc: String read fsTipoDoc write fsTipoDoc;
end;

{ Lista de Objetos do tipo TACBrECFFormaPagamento }
TACBrECFFormasPagamento = class(TObjectList)
  protected
    procedure SetObject (Index: Integer; Item: TACBrECFFormaPagamento);
    function GetObject (Index: Integer): TACBrECFFormaPagamento;
  public
    procedure Ordenar;
    function New: TACBrECFFormaPagamento;
    function Add (Obj: TACBrECFFormaPagamento): Integer;
    procedure Insert (Index: Integer; Obj: TACBrECFFormaPagamento);
    property Objects [Index: Integer]: TACBrECFFormaPagamento
      read GetObject write SetObject; default;
  end;

{ Definindo novo tipo para armazenar as unidades de Medida }
TACBrECFUnidadeMedida = class
 private
    fsIndice: String;
    fsDescricao: String;
 public
    constructor create ;
    property Indice    : String read fsIndice    write fsIndice ;
    property Descricao : String read fsDescricao write fsDescricao ;
end;

{ Lista de Objetos do tipo TACBrECFunidadeMedida }
TACBrECFUnidadesMedida = class(TObjectList)
  protected
    procedure SetObject (Index: Integer; Item: TACBrECFUnidadeMedida);
    function GetObject (Index: Integer): TACBrECFUnidadeMedida;
  public
    function Add (Obj: TACBrECFUnidadeMedida): Integer;
    procedure Insert (Index: Integer; Obj: TACBrECFUnidadeMedida);
    property Objects [Index: Integer]: TACBrECFUnidadeMedida
      read GetObject write SetObject; default;
  end;

{ Definindo novo tipo para armazenar os Relet�rios Gerenciais (RG) }
TACBrECFRelatorioGerencial = class
 private
    fsIndice: String;
    fsDescricao: String;
    fsContador: Integer;
 public
    constructor create ;
    procedure Assign( ARelatorioGerencial : TACBrECFRelatorioGerencial ) ;
    
    property Indice    : String read fsIndice    write fsIndice ;
    property Descricao : String read fsDescricao write fsDescricao ;
    property Contador : Integer read fsContador write fsContador;
 end;

{ Lista de Objetos do tipo TACBrECFRelatoriosGerencial }
TACBrECFRelatoriosGerenciais = class(TObjectList)
  protected
    procedure SetObject (Index: Integer; Item: TACBrECFRelatorioGerencial);
    function GetObject (Index: Integer): TACBrECFRelatorioGerencial;
  public
    function Add (Obj: TACBrECFRelatorioGerencial): Integer;
    procedure Insert (Index: Integer; Obj: TACBrECFRelatorioGerencial);
    property Objects [Index: Integer]: TACBrECFRelatorioGerencial
      read GetObject write SetObject; default;
  end;

{ Definindo novo tipo para armazenar os Comprovantes NAO Fiscais (CNF) }
TACBrECFComprovanteNaoFiscal = class
 private
    fsIndice: String;
    fsDescricao: String;
    fsPermiteVinculado: Boolean;
    fsFormaPagamento: String;
    fsTotal: Double ;
    fsContador: Integer;
 public
    constructor create ;
    procedure Assign( AComprovanteNaoFiscal : TACBrECFComprovanteNaoFiscal ) ;
    
    property Indice    : String read fsIndice    write fsIndice ;
    property Descricao : String read fsDescricao write fsDescricao ;
    property PermiteVinculado : Boolean read fsPermiteVinculado
                                       write fsPermiteVinculado ;
    property FormaPagamento : String read fsFormaPagamento
                                    write fsFormaPagamento ;
    property Total    : Double  read fsTotal    write fsTotal ;
    property Contador : Integer read fsContador write fsContador;
 end;


{ Definindo novo tipo para armazenar o Consumidor que ser� impresso no CF }
TACBrECFConsumidor = class
 private
    fsNome      : String;
    fsEndereco  : String;
    fsDocumento : String;
    fsEnviado   : Boolean;
    function GetEnviado: Boolean;
    function GetAtribuido: Boolean;
 public
    constructor create ;
    property Enviado   : Boolean read GetEnviado write fsEnviado ;
    property Nome      : String  read fsNome ;
    property Endereco  : String  read fsEndereco ;
    property Documento : String  read fsDocumento  ;
    property Atribuido : Boolean read GetAtribuido ;

    procedure AtribuiConsumidor(CPF_CNPJ, Nome, Endereco: String);
    procedure Zera;
end ;

{ Lista de Objetos do tipo TACBrECFComprovanteNaoFiscal }
TACBrECFComprovantesNaoFiscais = class(TObjectList)
  protected
    procedure SetObject (Index: Integer; Item: TACBrECFComprovanteNaoFiscal);
    function GetObject (Index: Integer): TACBrECFComprovanteNaoFiscal;
  public
    function Add (Obj: TACBrECFComprovanteNaoFiscal): Integer;
    procedure Insert (Index: Integer; Obj: TACBrECFComprovanteNaoFiscal);
    property Objects [Index: Integer]: TACBrECFComprovanteNaoFiscal
      read GetObject write SetObject; default;
  end;

{ Dados da atual ou �ltima redu��o Z }

{ TACBrECFDadosRZ }

TACBrECFDadosRZ = class
  private
    fsCOO: integer;
    fsCFD: integer;
    fsCancelamentoISSQN: Double;
    fsGNFC: integer;
    fsCRO: integer;
    fsValorVendaBruta: Double;
    fsTotalizadoresNaoFiscais: TACBrECFComprovantesNaoFiscais;
    fsICMS: TACBrECFAliquotas;
    fsTodasAliquotas: TACBrECFAliquotas;
    fsAcrescimoICMS: Double;
    fsDescontoICMS: Double;
    fsNaoTributadoICMS: Double;
    fsRelatorioGerencial: TACBrECFRelatoriosGerenciais;
    fsCRZ: integer;
    fsISSQN: TACBrECFAliquotas;
    fsGRG: integer;
    fsValorGrandeTotal: Double;
    fsAcrescimoISSQN: Double;
    fsNaoTributadoISSQN: Double;
    fsIsentoICMS: Double;
    fsSubstituicaoTributariaICMS: Double;
    fsDataDaImpressora: TDateTime;
    fsTotalOperacaoNaoFiscal: Double;
    fsDescontoISSQN: Double;
    fsCancelamentoOPNF: Double;
    fsAcrescimoOPNF: Double;
    fsDescontoOPNF: Double;
    fsCancelamentoICMS: Double;
    fsGNF: integer;
    fsIsentoISSQN: Double;
    fsSubstituicaoTributariaISSQN: Double;
    fsVendaLiquida: Double;
    fsCFC: integer;
    fsCCF: integer;
    fsTotalISSQN: Double;
    fsTotalICMS: Double;
    fsCDC: integer;
    fsCCDC: integer;
    fsNCN: integer;
    fsDataDoMovimento: TDateTime;
    fsMeiosDePagamento: TACBrECFFormasPagamento;
    fsNumeroCOOInicial: AnsiString;
    fsNumeroDoECF: AnsiString;
    fsNumeroDeSerie: AnsiString;
    fsNumeroDeSerieMFD: AnsiString;
    fsNumeroDaLoja: AnsiString;
    fsTotalTroco: Double;
    procedure SetDataDoMovimento(AValue: TDateTime);
  public
    constructor Create;
    destructor Destroy; override ;
    procedure Clear;
    Procedure CalculaValoresVirtuais;

    Function MontaDadosReducaoZ : AnsiString;
    procedure AdicionaAliquota( AliqZ: TACBrECFAliquota );

    property DataDaImpressora: TDateTime read fsDataDaImpressora write fsDataDaImpressora;
    property NumeroDeSerie: AnsiString read fsNumeroDeSerie write fsNumeroDeSerie;
    property NumeroDeSerieMFD: AnsiString read fsNumeroDeSerieMFD write fsNumeroDeSerieMFD;
    property NumeroDoECF: AnsiString read fsNumeroDoECF write fsNumeroDoECF;
    property NumeroDaLoja: AnsiString read fsNumeroDaLoja write fsNumeroDaLoja;
    property NumeroCOOInicial: AnsiString read fsNumeroCOOInicial write fsNumeroCOOInicial;
    // REDU��O Z
    property DataDoMovimento: TDateTime read fsDataDoMovimento write SetDataDoMovimento;
    // CONTADORES
    property COO: integer read fsCOO write fsCOO;
    property GNF: integer read fsGNF write fsGNF;
    property CRO: integer read fsCRO write fsCRO;
    property CRZ: integer read fsCRZ write fsCRZ;
    property CCF: integer read fsCCF write fsCCF;
    property CFD: integer read fsCFD write fsCFD;   //Contador Fita Detalhe
    property CDC: integer read fsCDC write fsCDC;
    property NCN: integer read fsNCN write fsNCN;
    property GRG: integer read fsGRG write fsGRG;
    property GNFC: integer read fsGNFC write fsGNFC;
    property CCDC: integer read fsCCDC write fsCCDC;
    property CFC: integer read fsCFC write fsCFC;
    // TOTALIZADORES
    property ValorGrandeTotal: Double read fsValorGrandeTotal write fsValorGrandeTotal;
    property ValorVendaBruta: Double read fsValorVendaBruta write fsValorVendaBruta;
    property CancelamentoICMS: Double read fsCancelamentoICMS write fsCancelamentoICMS;
    property DescontoICMS: Double read fsDescontoICMS write fsDescontoICMS;
    property TotalISSQN: Double read fsTotalISSQN write fsTotalISSQN;
    property TotalICMS: Double read fsTotalICMS write fsTotalICMS;
    property CancelamentoISSQN: Double read fsCancelamentoISSQN write fsCancelamentoISSQN;
    property CancelamentoOPNF: Double read fsCancelamentoOPNF write fsCancelamentoOPNF;
    property DescontoISSQN: Double read fsDescontoISSQN write fsDescontoISSQN;
    property DescontoOPNF: Double read fsDescontoOPNF write fsDescontoOPNF;
    property VendaLiquida: Double read fsVendaLiquida write fsVendaLiquida;
    property AcrescimoICMS: Double read fsAcrescimoICMS write fsAcrescimoICMS;
    property AcrescimoISSQN: Double read fsAcrescimoISSQN write fsAcrescimoISSQN;
    property AcrescimoOPNF: Double read fsAcrescimoOPNF write fsAcrescimoOPNF;

    // Todas as Aliquotas, de ICMS e ISSQN na ordem original de programa��o no ECF
    property TodasAliquotas: TACBrECFAliquotas read fsTodasAliquotas;
    // ICMS
    property ICMS: TACBrECFAliquotas read fsICMS;
    property SubstituicaoTributariaICMS: Double read fsSubstituicaoTributariaICMS write fsSubstituicaoTributariaICMS;
    property IsentoICMS: Double read fsIsentoICMS write fsIsentoICMS;
    property NaoTributadoICMS: Double read fsNaoTributadoICMS write fsNaoTributadoICMS;
    // ISSQN
    property ISSQN: TACBrECFAliquotas read fsISSQN;
    property SubstituicaoTributariaISSQN: Double read fsSubstituicaoTributariaISSQN write fsSubstituicaoTributariaISSQN;
    property IsentoISSQN: Double read fsIsentoISSQN write fsIsentoISSQN;
    property NaoTributadoISSQN: Double read fsNaoTributadoISSQN write fsNaoTributadoISSQN;
    // TOTALIZADORES N�O FISCAIS
    property TotalizadoresNaoFiscais: TACBrECFComprovantesNaoFiscais read fsTotalizadoresNaoFiscais ;
    property TotalOperacaoNaoFiscal: Double read fsTotalOperacaoNaoFiscal write fsTotalOperacaoNaoFiscal;
    // RELAT�RIO GERENCIAL
    property RelatorioGerencial: TACBrECFRelatoriosGerenciais read fsRelatorioGerencial;
    // MEIOS DE PAGAMENTO
    property MeiosDePagamento: TACBrECFFormasPagamento read fsMeiosDePagamento;
    property TotalTroco: Double read fsTotalTroco write fsTotalTroco;
  end;

TACBrECFConfigBarras = class(TPersistent)
  private
    FMostrarCodigo: Boolean;
    FAltura: Integer;
    FLarguraLinha: Integer;
  published
    property MostrarCodigo: Boolean read FMostrarCodigo write FMostrarCodigo;
    property LarguraLinha: Integer read FLarguraLinha write FLarguraLinha;
    property Altura: Integer read FAltura write FAltura;
end;

{ Evento para o usu�rio exibir os erros encontrados pela classe TACBrECFClass.
  Se o evento OnMsgErro NAO for programado a Classe TACBrECFClass exibir� as
  Msg de erro atrav�s de Exce�oes. Se o evento OnMsgErro for programado a Classe
  nao exibe nenhuma msg de erro, que deverao ser tratados dentro deste evento }
TACBrECFExibeErroEvent = procedure(Erro : Exception) of object ;

{ Evento para enviar as msg de Aguarde para o Componente  }
TACBrECFMsgAguarde = procedure(const Mensagem : String) of object ;

{ Evento para enviar mensagem de Retentar para o Componente }
TACBrECFMsgRetentar = procedure(const Mensagem : String;
   const Situacao : String; var Result : Boolean) of object ;

{ Evento disparado quando o componente adicionar algo em MemoBobina  }
TACBrECFBobinaAdicionaLinhas = procedure(const Linhas : String;
   const Operacao : String) of object ;

TACBrECFOnChequeEstado = procedure(const EstadoAtual: TACBrECFCHQEstado;
  var Continuar: Boolean) of object;

TACBrFormMsgProcedure = procedure of object ;

TACBrFormMsgEstado = (fmsNenhum, fmsProcessando, fmsConcluido, fmsAbortado) ;

{ Classe generica de ECF, nao implementa nenhum modelo especifico, apenas
  declara a Classe. NAO DEVE SER INSTANCIADA. Usada apenas como base para
  as demais Classes de ECF como por exemplo a classe TACBrECFBematech  }

{ Nota sobre procimentos e fun��es VIRTUAL. Essas fun�oes/procedimentos PODEM
  ou NAO ser implementados nas Classes filhas com a clausula OVERRIDE. Se n�o
  forem implementadas nas classes filhas, a fun�ao/procedimento definida aqui
  nessa classe (TACBrECFClass) e que ser� executada }

{ TACBrECFClass }

TACBrECFClass = class
 private
    fsRetentar     : Boolean;

    fsBloqueiaMouseTeclado: Boolean;
    fsExibeMensagem: Boolean;
    fsTempoInicioMsg: Integer;
    fsIntervaloAposComando: Integer ;
    fsMsgAguarde: String;
    fsMsgTrabalhando: String;
    fsMsgRelatorio : String;
    fsPausaRelatorio : Integer ;
    fsLinhasEntreCupons : Integer ;
    fsMaxLinhasBuffer : Integer ;
    fsMsgPausaRelatorio : String ;
    fsMsgPoucoPapel: Integer;
    fsDescricaoGrande: Boolean;
    fsOnMsgErro    : TACBrECFExibeErroEvent ;
    fsOnMsgAguarde : TACBrECFMsgAguarde ;
    fsAguardandoResposta: Boolean;
    fsOnAguardandoRespostaChange: TNotifyEvent;
    fsOnMsgPoucoPapel: TNotifyEvent;
    fsOnErrorSemPapel : TNotifyEvent ;
    fsOnMsgRetentar : TACBrECFMsgRetentar ;
    fsOnChequeEstado: TACBrECFOnChequeEstado;
    fsOperador: String;
    fsBytesRec : Integer ;
    fsAguardaImpressao: Boolean;

    {$IFNDEF NOGUI}
      fsFormMsg: TForm ;           { Form para exibir Msgs de Aguarde... }
      fsFormMsgFont  : TFont ;
      fsFormMsgColor : TColor ;
      fsFormMsgProcedureAExecutar : TACBrFormMsgProcedure ;
      fsFormMsgTeclaParaFechar    : Word ;
      fsFormMsgEstado             : TACBrFormMsgEstado ;
      fsFormMsgControla : Boolean ;
      fsFormMsgException: String  ;
      fsUsandoBlockInput : Boolean ;
    {$ENDIF}

    fsRelatorio : TStrings ;
    fsVias      : Word ;
    fsIndiceRG  : Integer;

    fsPathDLL: string;
    fpInfoRodapeCupom: TACBrECFRodape;
    fpRespostasComando: TACBrInformacoes;

    function GetPathDLL : string ;
    procedure SetAtivo(const Value: Boolean);
    procedure SetTimeOut(const Value: Integer);
    function GetTimeOut: Integer;

    procedure ErroAbstract( NomeProcedure : String ) ;
    function GetAliquotas: TACBrECFAliquotas;
    function GetFormasPagamentos: TACBrECFFormasPagamento;
    procedure SetAguardandoResposta(const Value: Boolean);
    function GetComprovantesNaoFiscais: TACBrECFComprovantesNaoFiscais;
    function GetUnidadesMedida: TACBrECFUnidadesMedida;
    function GetRelatoriosGerenciais: TACBrECFRelatoriosGerenciais;

    {$IFNDEF NOGUI}
      procedure FormMsgTimer(Sender: TObject);
      procedure FormMsgCloseQuery(Sender: TObject; var CanClose: Boolean);
      procedure FormMsgKeyPress(Sender: TObject; var Key: Char);

      {$IFDEF VisualCLX}
       procedure FormMsgEvent(Sender: QObjectH; Event: QEventH;
         var Handled: Boolean);
      {$ENDIF}
      {$IFDEF LINUX}
       {$IFNDEF FPC}
        procedure FormShow(Sender: TObject);
       {$ENDIF}
      {$ENDIF}
      function FormMsgExibe : Boolean;

      {$IFDEF MSWINDOWS}
        procedure BlockInput(const Block, ClearTypeAhead: Boolean);
      {$ENDIF}
    {$ENDIF}

    procedure DoLeResposta ;
    procedure DoRelatorioGerencial ;
    procedure DoCupomVinculado ;

 Protected
    fpDevice : TACBrDevice ;
    fpOwner  : TComponent ;   { Componente ACBrECF }
    fpAtivo  : Boolean ;
    fpColunas: Integer;
    fpPaginaDeCodigo : Word ;
    fpRFDID  : String;
    fpModeloStr: String;
    fpComandoEnviado: AnsiString;
    fpRespostaComando: AnsiString;
    fpUltimaMsgPoucoPapel : TDateTime ;
    fpEstado: TACBrECFEstado;
    fpArredondaPorQtd: Boolean;
    fpArredondaItemMFD : Boolean ;
    fpIgnorarErroSemPapel : Boolean ;
    fpIgnorarTagsFormatacao : Boolean ;
    fpDecimaisPreco: Integer;
    fpDecimaisQtd: Integer;
    fpArqLOG: String;
    fpComandoLOG: AnsiString;
    fpMFD: Boolean;
    fpTermica: Boolean;
    fpIdentificaConsumidorRodape: Boolean;
    fpModoPreVenda: Boolean;

    { Cole�ao de objetos TACBrECFAliquota }
    fpAliquotas: TACBrECFAliquotas;
    { Cole�ao de objetos TACBrECFFormasPagamento }
    fpFormasPagamentos : TACBrECFFormasPagamento;
    { Cole�ao de objetos TACBrECFRelat�rios Gerenciais }
    fpRelatoriosGerenciais : TACBrECFRelatoriosGerenciais;
    { Cole�ao de objetos TACBrECFComprovantesNaoFiscais }
    fpComprovantesNaoFiscais : TACBrECFComprovantesNaoFiscais;
    { Cole�ao de objetos TACBrECFUnidadesMedida}
    fpUnidadesMedida : TACBrECFUnidadesMedida;

    fpConsumidor : TACBrECFConsumidor ;

    { Class com instacia para armazenar dados da RZ }
    fpDadosReducaoZClass: TACBrECFDadosRZ;

    procedure GeraErro( E : Exception ) ;

    function GetModeloStr: String; virtual ;
    function GetDataHora: TDateTime; virtual ;
    function GetNumCupom: String; virtual ;
    function GetNumECF: String; virtual ;
    function GetNumLoja: String; virtual ;
    function GetNumSerie: String; virtual ;
    function GetNumSerieMFD: String; virtual ;
    function GetNumVersao: String ; virtual ;
    function GetSubTotal: Double; virtual ;
    function GetTotalPago: Double; virtual ;
    function GetNumReducoesZRestantes: String; virtual;

    function GetCNPJ: String; virtual ;
    function GetIE: String; virtual ;
    function GetIM: String; virtual ;
    function GetCliche: AnsiString; virtual ;
    function GetUsuarioAtual: String; virtual ;
    function GetDataHoraSB: TDateTime; virtual ;
    function GetSubModeloECF: String ; virtual ;

    function GetPAF: String; virtual ;
    function GetDataMovimento: TDateTime; virtual ;
    function GetGrandeTotal: Double; virtual ;
    function GetNumCRO: String; virtual ;
    function GetNumCCF: String; virtual ;
    function GetNumGNF: String; virtual ;
    function GetNumGRG: String; virtual ;
    function GetNumCDC: String; virtual ;
    function GetNumCFC: String; virtual ;
    function GetNumGNFC: String; virtual ;
    function GetNumCRZ: String; virtual ;
    function GetNumCFD: String; virtual ;
    function GetNumNCN: String; virtual ;
    function GetNumCCDC: String; virtual ;
    function GetVendaBruta: Double; virtual ;
    function GetTotalTroco: Double; virtual ;

    function GetTotalAcrescimos: Double; virtual ;
    function GetTotalCancelamentos: Double; virtual ;
    function GetTotalDescontos: Double; virtual ;
    function GetTotalSubstituicaoTributaria: Double; virtual ;
    function GetTotalNaoTributado: Double; virtual ;
    function GetTotalIsencao: Double; virtual ;
    function GetTotalNaoFiscal: Double; virtual ;

    function GetTotalAcrescimosISSQN: Double; virtual ;
    function GetTotalCancelamentosISSQN: Double; virtual ;
    function GetTotalDescontosISSQN: Double; virtual ;
    function GetTotalIsencaoISSQN: Double; virtual ;
    function GetTotalNaoTributadoISSQN: Double; virtual ;
    function GetTotalSubstituicaoTributariaISSQN: Double; virtual ;

    function GetTotalAcrescimosOPNF: Double; virtual ;
    function GetTotalCancelamentosOPNF: Double; virtual ;
    function GetTotalDescontosOPNF: Double; virtual ;

    function GetNumCOOInicial: String; virtual ;
    function GetNumUltimoItem: Integer; virtual ;

    function GetDadosUltimaReducaoZ: AnsiString; Virtual ;
    function GetDadosReducaoZ: AnsiString; Virtual ;
    Procedure InitDadosUltimaReducaoZ;

    function GetEstado: TACBrECFEstado; virtual ;
    function GetGavetaAberta: Boolean; virtual ;
    function GetPoucoPapel : Boolean; virtual ;
    function GetHorarioVerao: Boolean; virtual ;
    function GetArredonda: Boolean; virtual ;
    function GetChequePronto: Boolean; virtual ;
    function GetParamDescontoISSQN: Boolean; virtual;

    function GetTipoUltimoDocumento : TACBrECFTipoDocumento ; virtual ;

    Function EnviaComando_ECF( cmd : AnsiString ) : AnsiString ; virtual ;

    procedure LeResposta ; virtual ;
    function TransmiteComando( Cmd : AnsiString ) : Boolean ; virtual ;

    function VerificaFimLeitura(var Retorno: AnsiString; var TempoLimite: TDateTime) : Boolean ; virtual ;
    function VerificaFimImpressao(var TempoLimite: TDateTime) : Boolean ; virtual ;
    Procedure VerificaEmLinha( TimeOut : Integer = 3) ; virtual ;

    procedure ListaRelatorioGerencial(Relatorio : TStrings; Vias : Integer = 1; Indice: Integer = 0);
       virtual ;
    Procedure ListaCupomVinculado( Relatorio : TStrings; Vias : Integer = 1) ;
       virtual ;
    procedure PausarRelatorio( Via : Integer) ;

    procedure DoOnMsgPoucoPapel( Mensagem : String = '') ;
    procedure DoOnErrorSemPapel ;
    Function DoOnMsgRetentar( const Mensagem : String;
       const Situacao : String = '') : Boolean ;
    procedure DoOnChequeEstado(const Estado: TACBrECFCHQEstado;
       var Continuar: Boolean);

    procedure ImprimirLinhaALinha( Texto, Cmd : AnsiString ) ;

    function ConfigBarras: TACBrECFConfigBarras;
 public
    Constructor create( AOwner : TComponent ) ;
    Destructor Destroy  ; override ;

    Property Ativo  : Boolean read fpAtivo write SetAtivo ;
    procedure Ativar ; virtual ;
    procedure Desativar ; virtual ;

    procedure GravaLog(AString: AnsiString; Traduz :Boolean = False);

    property ArredondaPorQtd : Boolean read fpArredondaPorQtd
       write fpArredondaPorQtd ;
    property ArredondaItemMFD : Boolean read fpArredondaItemMFD
       write fpArredondaItemMFD ;
    property IgnorarErroSemPapel : Boolean read fpIgnorarErroSemPapel
       write fpIgnorarErroSemPapel;
    property IgnorarTagsFormatacao : Boolean read fpIgnorarTagsFormatacao
                write fpIgnorarTagsFormatacao default false ;

    property DecimaisPreco : Integer read fpDecimaisPreco
       write fpDecimaisPreco default 3 ;
    property DecimaisQtd : Integer read fpDecimaisQtd
       write fpDecimaisQtd default 3 ;
    property ArqLOG : String read fpArqLOG write fpArqLOG ;
    property ComandoLOG : AnsiString read fpComandoLOG write fpComandoLOG ;
    property AguardaImpressao : Boolean read fsAguardaImpressao
       write fsAguardaImpressao ;

    {$IFNDEF NOGUI}
      function FormMsgDoProcedure( AProcedure : TACBrFormMsgProcedure;
         TeclaParaFechar : Word) : Boolean ;
      procedure FormMsgPinta( Texto : String ) ;
      property FormMsgEstado   : TACBrFormMsgEstado read fsFormMsgEstado ;
      property FormMsgControla : Boolean read fsFormMsgControla write fsFormMsgControla ;
    {$ENDIF}
    
    { Proriedades de uso interno, configurando o funcionamento da classe,
      atribuidas pelo Objeto TACBrECF dono dessa classe }
//    property OnMsgErro : TACBrECFExibeErroEvent read  fpOnMsgErro
//                write fpOnMsgErro ;
    property OnMsgAguarde : TACBrECFMsgAguarde
        read  fsOnMsgAguarde write fsOnMsgAguarde ;
    property OnAguardandoRespostaChange : TNotifyEvent
        read fsOnAguardandoRespostaChange write fsOnAguardandoRespostaChange ;
    property OnMsgPoucoPapel : TNotifyEvent
        read fsOnMsgPoucoPapel write fsOnMsgPoucoPapel ;
    property OnMsgRetentar : TACBrECFMsgRetentar
        read  fsOnMsgRetentar write fsOnMsgRetentar ;
    property OnErrorSemPapel : TNotifyEvent
        read fsOnErrorSemPapel write fsOnErrorSemPapel ;
    property OnChequeEstado: TACBrECFOnChequeEstado
        read fsOnChequeEstado write fsOnChequeEstado;

    Property TimeOut  : Integer read GetTimeOut write SetTimeOut ;
    Property Retentar : Boolean read fsRetentar write fsRetentar ;

    property BloqueiaMouseTeclado : Boolean read  fsBloqueiaMouseTeclado
                                            write fsBloqueiaMouseTeclado ;
    property Operador   : String  read fsOperador   write fsOperador ;
    property MsgAguarde : String  read fsMsgAguarde write fsMsgAguarde ;
    property MsgTrabalhando : String read fsMsgTrabalhando write fsMsgTrabalhando ;
    property MsgRelatorio : String  read fsMsgRelatorio write fsMsgRelatorio ;
    property PausaRelatorio : Integer read fsPausaRelatorio
                write fsPausaRelatorio ;
    property LinhasEntreCupons : Integer read fsLinhasEntreCupons
                write fsLinhasEntreCupons ;
    property MaxLinhasBuffer : Integer read fsMaxLinhasBuffer
                write fsMaxLinhasBuffer ;
    property MsgPausaRelatorio : String  read fsMsgPausaRelatorio
                write fsMsgPausaRelatorio ;
    property ExibeMensagem : Boolean read fsExibeMensagem write fsExibeMensagem ;
    property TempoInicioMsg : Integer read  fsTempoInicioMsg
                                      write fsTempoInicioMsg ;
    Property IntervaloAposComando : Integer read  fsIntervaloAposComando
                                            write fsIntervaloAposComando ;
    property MsgPoucoPapel : Integer read  fsMsgPoucoPapel
                                     write fsMsgPoucoPapel  ;
    property DescricaoGrande : Boolean read fsDescricaoGrande
                                      write fsDescricaoGrande ;

    property PaginaDeCodigo : Word read fpPaginaDeCodigo write fpPaginaDeCodigo ;

    property InfoRodapeCupom: TACBrECFRodape read fpInfoRodapeCupom write fpInfoRodapeCupom;

    { Proriedades ReadOnly }
    Property Colunas  : Integer read fpColunas  ;
    Property ModeloStr: String  read GetModeloStr ;
    Property RFDID    : String  read fpRFDID ;
    Property AguardandoResposta : Boolean read fsAguardandoResposta
       write SetAguardandoResposta ;
    { String com Comando exatamente como foi enviado para impressora }
    Property ComandoEnviado : AnsiString read fpComandoEnviado
       write fpComandoEnviado ;
    { String com a Resposta Completa da Impressora (sem tratamentos) }
    Property RespostaComando : AnsiString read fpRespostaComando
       write fpRespostaComando ;
    { lista com as resposta de comando tratadas }
    property RespostasComando: TACBrInformacoes read fpRespostasComando
       write fpRespostasComando;

    { Propriedades relacionadas aos dados do ECF }
    { ECF - Variaveis }
    Property DataHora  : TDateTime read GetDataHora  ;
    Property NumCupom  : String    read GetNumCupom  ;
    Property NumLoja   : String    read GetNumLoja   ;
    Property NumECF    : String    read GetNumECF    ;
    Property NumSerie  : String    read GetNumSerie  ;
    Property NumSerieMFD  : String read GetNumSerieMFD  ;
    Property NumVersao : String    read GetNumVersao ;
    Property NumReducoesZRestantes: String read GetNumReducoesZRestantes ;

    { Dados da Reducao Z - Registro 60M }
    Property DataMovimento      : TDateTime  read GetDataMovimento ;
    Property CNPJ               : String     read GetCNPJ ;
    Property IE                 : String     read GetIE ;
    Property IM                 : String     read GetIM ;
    Property Cliche             : AnsiString read GetCliche ;
    Property UsuarioAtual       : String     read GetUsuarioAtual ;
    Property DataHoraSB         : TDateTime  read GetDataHoraSB ;
    Property SubModeloECF       : String     read GetSubModeloECF ;

    Property PAF                : String     read GetPAF ;
    Property NumCRZ             : String     read GetNumCRZ ;
    Property NumCRO             : String     read GetNumCRO ;
    Property NumCCF             : String     read GetNumCCF ;
    Property NumGNF             : String     read GetNumGNF ;
    Property NumGRG             : String     read GetNumGRG ;
    Property NumCDC             : String     read GetNumCDC ;
    Property NumCFC             : String     read GetNumCFC ;
    Property NumGNFC            : String     read GetNumGNFC ;
    Property NumCFD             : String     read GetNumCFD ;
    Property NumNCN             : String     read GetNumNCN ;
    Property NumCCDC            : String     read GetNumCCDC ;
    Property NumCOOInicial      : String     read GetNumCOOInicial ;
    Property VendaBruta         : Double     read GetVendaBruta ;
    Property GrandeTotal        : Double     read GetGrandeTotal ;
    Property TotalCancelamentos : Double     read GetTotalCancelamentos ;
    Property TotalDescontos     : Double     read GetTotalDescontos ;
    Property TotalAcrescimos    : Double     read GetTotalAcrescimos ;
    Property TotalTroco         : Double     read GetTotalTroco ;
    Property TotalSubstituicaoTributaria : Double
       read GetTotalSubstituicaoTributaria ;
    Property TotalNaoTributado  : Double     read GetTotalNaoTributado ;
    Property TotalIsencao       : Double     read GetTotalIsencao ;

    Property TotalCancelamentosISSQN          : Double read GetTotalCancelamentosISSQN;
    Property TotalDescontosISSQN              : Double read GetTotalDescontosISSQN;
    Property TotalAcrescimosISSQN             : Double read GetTotalAcrescimosISSQN;
    Property TotalSubstituicaoTributariaISSQN : Double read GetTotalSubstituicaoTributariaISSQN;
    Property TotalNaoTributadoISSQN           : Double read GetTotalNaoTributadoISSQN;
    Property TotalIsencaoISSQN                : Double read GetTotalIsencaoISSQN;

    Property TotalCancelamentosOPNF           : Double read GetTotalCancelamentosOPNF;
    Property TotalDescontosOPNF               : Double read GetTotalDescontosOPNF;
    Property TotalAcrescimosOPNF              : Double read GetTotalAcrescimosOPNF;

    Property NumUltItem         : Integer    read GetNumUltimoItem ;
    Property TotalNaoFiscal     : Double     read GetTotalNaoFiscal ;

    Property DadosReducaoZ : AnsiString  read GetDadosReducaoZ ;
    Property DadosUltimaReducaoZ : AnsiString read GetDadosUltimaReducaoZ ;
    Property DadosReducaoZClass: TACBrECFDadosRZ read fpDadosReducaoZClass;

    { Aliquotas de ICMS }
    procedure CarregaAliquotas ; virtual ;
    procedure LerTotaisAliquota ; virtual ;
    Property Aliquotas : TACBrECFAliquotas read GetAliquotas ;
    function AchaICMSAliquota( Aliquota : Double; Tipo : Char = ' ' ) :
       TACBrECFAliquota ;  overload ; virtual;
    function AchaICMSAliquota( var AliquotaICMS : String ) :
       TACBrECFAliquota ;  overload ; virtual;
    function AchaICMSIndice( Indice : String ) : TACBrECFAliquota ; virtual ;
    Procedure ProgramaAliquota( Aliquota : Double; Tipo : Char = 'T';
       Posicao : String = '') ; virtual ;

    { Formas de Pagamento }
    procedure CarregaFormasPagamento ; virtual ;
    procedure LerTotaisFormaPagamento ; virtual ;
    Property FormasPagamento : TACBrECFFormasPagamento read GetFormasPagamentos;
    function AchaFPGDescricao( Descricao : String;
       BuscaExata : Boolean = False; IgnorarCase : Boolean = True ) :
       TACBrECFFormaPagamento ; virtual ;
    function AchaFPGIndice( Indice : String ) : TACBrECFFormaPagamento ;
       virtual ;
    Procedure ProgramaFormaPagamento( var Descricao: String;
       PermiteVinculado : Boolean = true; Posicao : String = '' ) ; virtual ;

    { Relat�rio Gerencial (RG) }
    procedure CarregaRelatoriosGerenciais ; virtual ;
    Property RelatoriosGerenciais : TACBrECFRelatoriosGerenciais
       read GetRelatoriosGerenciais ;
    procedure LerTotaisRelatoriosGerenciais ; virtual ;
    function AchaRGDescricao( Descricao : String;
       BuscaExata : Boolean = False; IgnorarCase : Boolean = True ) :
       TACBrECFRelatorioGerencial ; virtual ;
    function AchaRGIndice( Indice : String ) : TACBrECFRelatorioGerencial ;
       virtual ;
    Procedure ProgramaRelatorioGerencial( var Descricao: String;
       Posicao : String = '') ; virtual ;

    { Comprovantes Nao Fiscais (CNF) }
    procedure CarregaComprovantesNaoFiscais ; virtual ;
    procedure LerTotaisComprovanteNaoFiscal ; virtual ;
    Property ComprovantesNaoFiscais : TACBrECFComprovantesNaoFiscais
       read GetComprovantesNaoFiscais ;
    function AchaCNFDescricao( Descricao : String;
       BuscaExata : Boolean = False; IgnorarCase : Boolean = True ) :
       TACBrECFComprovanteNaoFiscal ; virtual ;
    function AchaCNFIndice( Indice : String ) : TACBrECFComprovanteNaoFiscal ;
       virtual ;
    function AchaCNFFormaPagamento( CodFPG : String ) :
       TACBrECFComprovanteNaoFiscal ; virtual ;
    Procedure ProgramaComprovanteNaoFiscal( var Descricao: String;
       Tipo : String = ''; Posicao : String = '') ; virtual ;

    { Unidades de Medida (UMD) }
    procedure CarregaUnidadesMedida ; virtual ;
    Property UnidadesMedida : TACBrECFUnidadesMedida read GetUnidadesMedida;
    function AchaUMDDescricao( Descricao : String ) : TACBrECFUnidadeMedida ;
       virtual ;
    function AchaUMDIndice( Indice : String ) : TACBrECFUnidadeMedida ;
       virtual ;
    Procedure ProgramaUnidadeMedida( var Descricao: String) ; virtual ;

    { ECF - Flags }
    Function EmLinha( lTimeOut : Integer = 1) : Boolean ; virtual ;
    Property PoucoPapel   : Boolean read GetPoucoPapel ;
    Property Estado       : TACBrECFEstado read GetEstado ;
    Property HorarioVerao : Boolean read GetHorarioVerao ;
    Property Arredonda    : Boolean read GetArredonda ;
    Property Termica      : Boolean read fpTermica ;
    Property MFD          : Boolean read fpMFD ;
    Property ParamDescontoISSQN : Boolean read GetParamDescontoISSQN ;
    Property IdentificaConsumidorRodape : Boolean read fpIdentificaConsumidorRodape ;
    Property ModoPreVenda: Boolean read fpModoPreVenda write fpModoPreVenda ;

    Property TipoUltimoDocumento: TACBrECFTipoDocumento read GetTipoUltimoDocumento ;

    { Procedimentos de Cupom Fiscal }
    property Consumidor : TACBrECFConsumidor read fpConsumidor ;
    Procedure AbreCupom ; virtual ;
    procedure LegendaInmetroProximoItem ; Virtual ;
    Procedure VendeItem( Codigo, Descricao : String; AliquotaECF : String;
       Qtd : Double ; ValorUnitario : Double; ValorDescontoAcrescimo : Double = 0;
       Unidade : String = ''; TipoDescontoAcrescimo : String = '%';
       DescontoAcrescimo : String = 'D'; CodDepartamento: Integer = -1) ; virtual ;
    Procedure DescontoAcrescimoItemAnterior( ValorDescontoAcrescimo : Double = 0;
       DescontoAcrescimo : String = 'D'; TipoDescontoAcrescimo : String = '%';
       NumItem : Integer = 0 ) ;  virtual ;
    Procedure SubtotalizaCupom( DescontoAcrescimo : Double = 0;
       MensagemRodape : AnsiString = '' ) ;  virtual ;
    procedure CancelaDescontoAcrescimoSubTotal(TipoAcrescimoDesconto: Char) ;
       Virtual ;{ A -> Acrescimo D -> Desconto } 
    Procedure EfetuaPagamento( CodFormaPagto : String; Valor : Double;
       Observacao : AnsiString = ''; ImprimeVinculado : Boolean = false) ;
       virtual ;
    procedure EstornaPagamento(const CodFormaPagtoEstornar,
      CodFormaPagtoEfetivar : String; const Valor: Double;
      Observacao : AnsiString = '') ; Virtual ;

    { Para quebrar linhas nos parametros Observacao use #10 ou chr(10),
      Geralmente o ECF aceita no m�ximo 8 linhas }
    Procedure FechaCupom( Observacao : AnsiString = ''; IndiceBMP : Integer = 0) ; virtual ;
    Procedure CancelaCupom ; virtual ;
    Procedure CancelaItemVendido( NumItem : Integer ) ; virtual ;
    procedure CancelaItemVendidoParcial( NumItem : Integer;
      Quantidade : Double) ; Virtual ; 
    procedure CancelaDescontoAcrescimoItem( NumItem : Integer) ; Virtual ; 
    Property Subtotal  : Double read GetSubTotal ;
    Property TotalPago : Double read GetTotalPago ;

    { Procedimentos de Cupom N�o Fiscal }
    Procedure NaoFiscalCompleto( CodCNF : String; Valor : Double;
       CodFormaPagto  : String; Obs : AnsiString = ''; IndiceBMP : Integer = 0 ) ; virtual ;
    Procedure AbreNaoFiscal( CPF_CNPJ: String = ''; Nome: String = '';
       Endereco: String = '' ) ; virtual ;
    Procedure RegistraItemNaoFiscal( CodCNF : String; Valor : Double;
       Obs : AnsiString = '') ; virtual ;
    Procedure SubtotalizaNaoFiscal( DescontoAcrescimo : Double = 0;
       MensagemRodape: AnsiString = '') ; virtual ;
    Procedure EfetuaPagamentoNaoFiscal( CodFormaPagto : String; Valor : Double;
       Observacao : AnsiString = ''; ImprimeVinculado : Boolean = false) ; virtual ;
    Procedure FechaNaoFiscal( Observacao : AnsiString = ''; IndiceBMP : Integer = 0) ; virtual ;
    Procedure CancelaNaoFiscal ; virtual ;
    Procedure CancelaItemNaoFiscal(const AItem: Integer); virtual;

    procedure Sangria( const Valor: Double;  Obs : AnsiString;
       DescricaoCNF: String; DescricaoFPG: String; IndiceBMP: Integer ) ; virtual ;
    procedure Suprimento( const Valor: Double; Obs : AnsiString;
       DescricaoCNF: String; DescricaoFPG: String; IndiceBMP: Integer ) ; virtual ;

    Function EstornaCCD( const Todos: Boolean = True) : Integer; virtual ;

    { Gaveta de dinheiro }
    Procedure AbreGaveta  ; virtual ;
    Property GavetaAberta : Boolean read GetGavetaAberta ;

    { Relatorios }
    Procedure LeituraX ; virtual ;
    Procedure LeituraXSerial( Linhas : TStringList) ; virtual ;
    Procedure ReducaoZ( DataHora : TDateTime = 0 ) ; virtual ;
    Procedure RelatorioGerencial(Relatorio : TStrings; Vias : Integer = 1; Indice: Integer = 0) ;
    Procedure AbreRelatorioGerencial(Indice: Integer = 0) ; virtual ;
    Procedure LinhaRelatorioGerencial( Linha : AnsiString; IndiceBMP: Integer = 0 ) ; virtual ;

    Procedure CupomVinculado(COO, CodFormaPagto, CodComprovanteNaoFiscal :
       String; Valor : Double;  Relatorio : TStrings;
       Vias : Integer = 1) ;
    Procedure AbreCupomVinculado(COO, CodFormaPagto, CodComprovanteNaoFiscal :
       String; Valor : Double) ; virtual ; 
    Procedure LinhaCupomVinculado( Linha : AnsiString ) ; virtual ;

    Procedure SegundaViaVinculado; virtual;
    procedure ReimpressaoVinculado; virtual;

    Procedure FechaRelatorio ; virtual ;
    Procedure PulaLinhas( NumLinhas : Integer = 0 ) ; virtual ;
    Procedure CortaPapel( const CorteParcial : Boolean = false) ; virtual ;

    { Cheques }
    Procedure ImprimeCheque(Banco : String; Valor : Double ; Favorecido,
       Cidade : String; Data : TDateTime ;Observacao : String = '') ; virtual ;
    Procedure CancelaImpressaoCheque ; virtual ;
    Function LeituraCMC7 : AnsiString ; virtual ;
    Property ChequePronto : Boolean read GetChequePronto ;

    { Utilitarios e Diversos }
    Procedure MudaHorarioVerao ; overload ; virtual ;
    Procedure MudaHorarioVerao( EHorarioVerao : Boolean ) ; overload ; virtual ;
    Procedure MudaArredondamento( Arredondar : Boolean ) ; virtual ;
    Procedure PreparaTEF ; virtual ; { Carrega as Formas, de Pagamento e CNF,
                            verifica por Vinculos, etc Particular de cada ECF }
    Procedure CorrigeEstadoErro( Reducao: Boolean = True ) ; virtual ; { Verifica o estado da impressora e
                                              tenta deixar em estado Livre }
    Procedure ImpactoAgulhas( NivelForca : Integer = 2) ; virtual ;
    Procedure LeituraMemoriaFiscal( DataInicial, DataFinal : TDateTime;
       Simplificada : Boolean = False ) ; overload ; virtual ;
    Procedure LeituraMemoriaFiscal( ReducaoInicial, ReducaoFinal : Integer;
       Simplificada : Boolean = False ); overload ; virtual ;
    Procedure LeituraMemoriaFiscalSerial( DataInicial, DataFinal : TDateTime;
       Linhas : TStringList; Simplificada : Boolean = False ) ;
       overload ; virtual ;
    Procedure LeituraMemoriaFiscalSerial( ReducaoInicial, ReducaoFinal: Integer;
       Linhas : TStringList; Simplificada : Boolean = False ) ;
       overload ; virtual ;

    Procedure LeituraMFDSerial( DataInicial, DataFinal : TDateTime;
       Linhas : TStringList; Documentos : TACBrECFTipoDocumentoSet = [docTodos] ) ; overload ; virtual ;
    Procedure LeituraMFDSerial( COOInicial, COOFinal : Integer;
       Linhas : TStringList; Documentos : TACBrECFTipoDocumentoSet = [docTodos] ) ; overload ; virtual ;

    Procedure EspelhoMFD_DLL( DataInicial, DataFinal : TDateTime;
       NomeArquivo : AnsiString; Documentos : TACBrECFTipoDocumentoSet = [docTodos]  ) ; overload ; virtual ;
    Procedure EspelhoMFD_DLL( COOInicial, COOFinal : Integer;
       NomeArquivo : AnsiString; Documentos : TACBrECFTipoDocumentoSet = [docTodos]  ) ; overload ; virtual ;
    Procedure ArquivoMFD_DLL( DataInicial, DataFinal : TDateTime;
       NomeArquivo : AnsiString; Documentos : TACBrECFTipoDocumentoSet = [docTodos];
       Finalidade: TACBrECFFinalizaArqMFD = finMFD  ) ; overload ; virtual ;
    Procedure ArquivoMFD_DLL( ContInicial, ContFinal : Integer;
       NomeArquivo : AnsiString; Documentos : TACBrECFTipoDocumentoSet = [docTodos];
       Finalidade: TACBrECFFinalizaArqMFD = finMFD;
       TipoContador: TACBrECFTipoContador = tpcCOO ) ; overload ; virtual ;

    procedure PafMF_GerarCAT52(const DataInicial, DataFinal: TDateTime;
      const DirArquivos: String); virtual;

    Procedure IdentificaOperador(Nome : String); virtual;
    Procedure IdentificaPAF( NomeVersao, MD5 : String) ; virtual ;
    Function RetornaInfoECF( Registrador: String) : AnsiString; Virtual ;

    { Retorna a Resposta do ECF }
    Function EnviaComando( cmd : AnsiString = '') : AnsiString ; overload ;
    { Versao que Permite mudar o TimeOut padrao }
    Function EnviaComando( cmd : AnsiString; lTimeOut : Integer): AnsiString; overload ;
    { Versao que Permite mudar o TimeOut padrao e o TempoInicioMsg }
    Function EnviaComando( cmd : AnsiString; lTimeOut, lTempoInicioMsg : Integer):
       AnsiString; overload ;

    { Gera erro se nao puder abrir Cupom, informando o motivo }
    Function TestaPodeAbrirCupom : Boolean ; virtual ;

    { Obs: De/Codifica e Verifica Textos C-->  Codifica D--> Decodifica V--> Verifica }
    function DecodificaTexto(Operacao: Char; Texto: String; var Resposta: String): Boolean; virtual;

    property PathDLL: string read GetPathDLL write fsPathDLL;

    procedure ProgramarBitmapPromocional(const AIndice: Integer;
      const APathArquivo: AnsiString;
      const AAlinhamento: TACBrAlinhamento = alCentro); virtual;

    function TraduzirTag(const ATag: AnsiString): AnsiString; virtual;
    function TraduzirTagBloco(const ATag, Conteudo: AnsiString): AnsiString; virtual;
    function PossuiTagCodBarra(const ATexto: String): Boolean; virtual;
    function CodificarPaginaDeCodigoECF(ATexto: String): AnsiString; virtual;
    function DecodificarPaginaDeCodigoECF(ATexto: AnsiString): String; virtual;
end ;

implementation
Uses ACBrECF, ACBrUtil, Math,
     {$IFDEF COMPILER6_UP} DateUtils, StrUtils {$ELSE} ACBrD5, Windows {$ENDIF},
     TypInfo ;

{ ---------------------------- TACBrECFAliquotas -------------------------- }

{ TACBrECFAliquota }
constructor TACBrECFAliquota.create;
begin
  fsSequencia := 0 ;
  fsIndice    := ''  ;
  fsAliquota  := 0   ;
  fsTipo      := 'T' ;
  fsTotal     := 0 ;
end;

procedure TACBrECFAliquota.Assign(AAliquota: TACBrECFAliquota);
begin
  fsSequencia := AAliquota.Sequencia ;
  fsIndice    := AAliquota.Indice ;
  fsAliquota  := AAliquota.Aliquota ;
  fsTipo      := AAliquota.Tipo ;
  fsTotal     := AAliquota.Total ;
end;

procedure TACBrECFAliquota.SetTipo(const Value: Char);
  Var NewVar : Char ;
begin
  NewVar := UpCase(Value) ;
  if NewVar = ' ' then
     NewVar := 'T' ;

  if not (NewVar in ['T','S']) then
     raise EACBrECFErro.create( ACBrStr(cACBrECFAliquotaSetTipoException));
  fsTipo := Value;
end;

function TACBrECFAliquotas.Add(Obj: TACBrECFAliquota): Integer;
begin
  Result := inherited Add(Obj) ;
  Obj.Sequencia := Count ;
end;

function TACBrECFAliquotas.GetObject(Index: Integer): TACBrECFAliquota;
begin
  Result := inherited GetItem(Index) as TACBrECFAliquota ;
end;

procedure TACBrECFAliquotas.Insert(Index: Integer; Obj: TACBrECFAliquota);
begin
  inherited Insert(Index, Obj);
end;

function TACBrECFAliquotas.New: TACBrECFAliquota;
begin
  Result := TACBrECFAliquota.create;
  Add(Result);
end;

procedure TACBrECFAliquotas.SetObject(Index: Integer; Item: TACBrECFAliquota);
begin
  inherited SetItem (Index, Item) ;
end;

{ --------------------------- TACBrECFFormasPagamento ---------------------- }

// m�todo de compara��o utilizado para ordenar a lista por data
function CompararCamposOrdenacao(FormaPagto1,
  FormaPagto2: TACBrECFFormaPagamento): Integer;
var
  sCampo1, sCampo2: String;
begin
  sCampo1 := FormatDateTime('YYYYMMDD', FormaPagto1.Data) + Trim(FormaPagto1.Descricao);
  sCampo2 := FormatDateTime('YYYYMMDD', FormaPagto2.Data) + Trim(FormaPagto2.Descricao);

  Result := AnsiCompareText(sCampo1, sCampo2);
end;

{ TACBrECFFormaPagamento }
constructor TACBrECFFormaPagamento.create;
begin
  fsIndice           := '' ;
  fsDescricao        := '' ;
  fsPermiteVinculado := true ;
  fsTotal            := 0.00 ;
  fsData             := 0.00;
end;

procedure TACBrECFFormaPagamento.Assign(
  AFormaPagamento: TACBrECFFormaPagamento);
begin
  fsIndice           := AFormaPagamento.Indice ;
  fsDescricao        := AFormaPagamento.Descricao ;
  fsPermiteVinculado := AFormaPagamento.PermiteVinculado ;
  fsTotal            := AFormaPagamento.Total ;
  fsData             := AFormaPagamento.Data ;
end;

function TACBrECFFormasPagamento.Add(Obj: TACBrECFFormaPagamento): Integer;
begin
  Result := inherited Add(Obj) ;
end;

function TACBrECFFormasPagamento.GetObject( Index: Integer):
   TACBrECFFormaPagamento;
begin
  Result := inherited GetItem(Index) as TACBrECFFormaPagamento ;
end;

procedure TACBrECFFormasPagamento.Insert(Index: Integer;
  Obj: TACBrECFFormaPagamento);
begin
  inherited Insert(Index, Obj);
end;

function TACBrECFFormasPagamento.New: TACBrECFFormaPagamento;
begin
  Result := TACBrECFFormaPagamento.Create;
  Add(Result);
end;

procedure TACBrECFFormasPagamento.Ordenar;
begin
  Self.Sort(@CompararCamposOrdenacao);
end;

procedure TACBrECFFormasPagamento.SetObject(Index: Integer;
  Item: TACBrECFFormaPagamento);
begin
  inherited SetItem (Index, Item) ;
end;

{ ----------------------------- TACBrECFConsumidor -------------------------- }
constructor TACBrECFConsumidor.create;
begin
   Zera ;
end;

procedure TACBrECFConsumidor.Zera ;
begin
  fsNome      := '' ;
  fsEndereco  := '' ;
  fsDocumento := '' ;
  fsEnviado   := False ;
end ;

procedure TACBrECFConsumidor.AtribuiConsumidor(CPF_CNPJ, Nome, Endereco: String);
begin
  CPF_CNPJ  := Trim( CPF_CNPJ );
  Nome      := Trim( Nome );
  Endereco  := Trim( Endereco );

  if CPF_CNPJ = '' then
     raise EACBrECFErro.Create( ACBrStr(cACBrECFConsumidorCPFCNPJException)) ;

  if (Nome = '') and (Endereco <> '') then
     raise EACBrECFErro.Create( ACBrStr(cACBrECFConsumidorNomeException) ) ;

  fsDocumento := CPF_CNPJ ;
  fsNome      := Nome ;
  fsEndereco  := Endereco ;
  fsEnviado   := False ;
end;

function TACBrECFConsumidor.GetEnviado: Boolean;
begin
  Result := fsEnviado or (not Atribuido) ;
end;

function TACBrECFConsumidor.GetAtribuido: Boolean;
begin
  Result := (fsNome <> '') or (fsEndereco <> '') or (fsDocumento <> '') ;
end;


{------------------------- TACBrECFRelatoriosGerenciais -----------------------}
{ TACBrECFRelatorioGerencial }

constructor TACBrECFRelatorioGerencial.create;
begin
  fsIndice    := '' ;
  fsDescricao := '' ;
  fsContador  := 0 ;
end;

procedure TACBrECFRelatorioGerencial.Assign(
  ARelatorioGerencial: TACBrECFRelatorioGerencial);
begin
  fsIndice    := ARelatorioGerencial.Indice ;
  fsDescricao := ARelatorioGerencial.Descricao ;
  fsContador  := ARelatorioGerencial.Contador ;
end;

function TACBrECFRelatoriosGerenciais.Add(
  Obj: TACBrECFRelatorioGerencial): Integer;
begin
  Result := inherited Add(Obj) ;
end;

function TACBrECFRelatoriosGerenciais.GetObject(
  Index: Integer): TACBrECFRelatorioGerencial;
begin
  Result := inherited GetItem(Index) as TACBrECFRelatorioGerencial ;
end;

procedure TACBrECFRelatoriosGerenciais.Insert(Index: Integer;
  Obj: TACBrECFRelatorioGerencial);
begin
  inherited Insert(Index, Obj);
end;

procedure TACBrECFRelatoriosGerenciais.SetObject(Index: Integer;
  Item: TACBrECFRelatorioGerencial);
begin
  inherited SetItem (Index, Item) ;
end;
               

{ ---------------------- TACBrECFComprovantesNaoFiscais --------------------- }

{ TACBrECFComprovanteNaoFiscal }
constructor TACBrECFComprovanteNaoFiscal.create;
begin
  fsIndice           := '' ;
  fsDescricao        := '' ;
  fsPermiteVinculado := true ;
  fsFormaPagamento   := '' ;
  fsTotal            := 0 ;
  fsContador         := 0 ;
end;

procedure TACBrECFComprovanteNaoFiscal.Assign(
  AComprovanteNaoFiscal: TACBrECFComprovanteNaoFiscal);
begin
  fsIndice           := AComprovanteNaoFiscal.Indice ;
  fsDescricao        := AComprovanteNaoFiscal.Descricao ;
  fsPermiteVinculado := AComprovanteNaoFiscal.PermiteVinculado ;
  fsFormaPagamento   := AComprovanteNaoFiscal.FormaPagamento ;
  fsTotal            := AComprovanteNaoFiscal.Total ;
  fsContador         := AComprovanteNaoFiscal.Contador ;
end;

function TACBrECFComprovantesNaoFiscais.Add(
  Obj: TACBrECFComprovanteNaoFiscal): Integer;
begin
  Result := inherited Add(Obj) ;
end;

function TACBrECFComprovantesNaoFiscais.GetObject(
  Index: Integer): TACBrECFComprovanteNaoFiscal;
begin
  Result := inherited GetItem(Index) as TACBrECFComprovanteNaoFiscal ;
end;

procedure TACBrECFComprovantesNaoFiscais.Insert(Index: Integer;
  Obj: TACBrECFComprovanteNaoFiscal);
begin
  inherited Insert(Index, Obj);
end;

procedure TACBrECFComprovantesNaoFiscais.SetObject(Index: Integer;
  Item: TACBrECFComprovanteNaoFiscal);
begin
  inherited SetItem (Index, Item) ;
end;

{-------------------------- TACBrECFUnidadesMedida ---------------------------}
Constructor TACBrECFUnidadeMedida.create;
begin
  fsIndice           := '' ;
  fsDescricao        := '' ;
end;

function TACBrECFUnidadesMedida.Add(Obj: TACBrECFUnidadeMedida): Integer;
begin
  Result := inherited Add(Obj) ;
end;

function TACBrECFUnidadesMedida.GetObject(
  Index: Integer): TACBrECFUnidadeMedida;
begin
  Result := inherited GetItem(Index) as TACBrECFUnidadeMedida ;
end;

procedure TACBrECFUnidadesMedida.Insert(Index: Integer;
  Obj: TACBrECFUnidadeMedida);
begin
  inherited Insert(Index, Obj);
end;

procedure TACBrECFUnidadesMedida.SetObject(Index: Integer;
  Item: TACBrECFUnidadeMedida);
begin
  inherited SetItem (Index, Item) ;
end;

{ ---------------------------- TACBrECFClass ------------------------------ }

constructor TACBrECFClass.create( AOwner : TComponent ) ;
begin
  if not (AOwner is TACBrECF) then
     raise EACBrECFErro.create( ACBrStr(cACBrECFClassCreateException) );

  fpOwner := AOwner ;

  { Criando ponteiro interno para as Propriedade SERIAL e FORMMSG de ACBrECF,
    para permitir as Classes Filhas o acesso a essas propriedades do Componente}
  fpDevice := (AOwner as TACBrECF).Device ;
  fpDevice.SetDefaultValues ;

  { Criando ponteiro para as propriedade de FormMsg ficarem visiveis nessa Unit }
  {$IFNDEF NOGUI}
    with (AOwner as TACBrECF) do
    begin
       fsFormMsgFont  := FormMsgFonte ;
       fsFormMsgColor := FormMsgColor ;
    end ;
  {$ENDIF}

  { Ajustando variaveis Internas }
  fsRetentar             := true ;
  fsOperador             := '' ;
  fsMsgAguarde           := cACBrMsgAguardando ;
  fsMsgTrabalhando       := cACBrMsgTrabalhando ;
  fsMsgRelatorio         := cACBrMsgRelatorio ;
  fsPausaRelatorio       := cACBrPausaRelatorio ;
  fsLinhasEntreCupons    := cACBrLinhasEntreCupons ;
  fsMaxLinhasBuffer      := cACBrMaxLinhasBuffer ;
  fsMsgPausaRelatorio    := cACBrMsgPausaRelatorio ;
  fsTempoInicioMsg       := cACBrTempoInicioMsg ;
  fsIntervaloAposComando := cACBrIntervaloAposComando ;
  fsExibeMensagem        := true ;
  fsBloqueiaMouseTeclado := true ;
  fsMsgPoucoPapel        := cACBrMsgPoucoPapel ;
  fsDescricaoGrande      := false ;
  fsVias                 := 0 ;
  fsRelatorio            := nil ;
  fsBytesRec             := 0 ;
  fsOnAguardandoRespostaChange := nil ;
  fsOnMsgPoucoPapel            := nil ;
  fsAguardandoResposta         := false ;
  fsOnMsgErro                  := nil ;
  fsOnMsgAguarde               := nil ;
  fsOnMsgRetentar              := nil ;
  {$IFNDEF NOGUI}
   fsUsandoBlockInput          := False ;
  {$ENDIF}
  
  { Variaveis Protected fp___ acessiveis pelas Classes filhas }
  fpAtivo                 := false ;
  fpEstado                := estNaoInicializada ;
  fpPaginaDeCodigo        := 0 ;
  fpColunas               := 48 ;
  fpRFDID                 := '' ;
  fpModeloStr             := 'N�o Definido' ;
  fpComandoEnviado        := '' ;
  fpRespostaComando       := '' ;
  fpUltimaMsgPoucoPapel   := 0 ;
  fpArredondaPorQtd       := False ;
  fpArredondaItemMFD      := False ;
  fpIgnorarErroSemPapel   := False ;
  fpIgnorarTagsFormatacao := False ;
  fpDecimaisPreco         := 3 ;
  fpDecimaisQtd           := 3 ;
  fpAliquotas             := nil ;
  fpFormasPagamentos      := nil ;
  fpRelatoriosGerenciais  := nil ;
  fpComprovantesNaoFiscais:= nil ;
  fpTermica               := False ;
  fpMFD                   := False ;
  fpIdentificaConsumidorRodape := False ;
  fpModoPreVenda          := False;
  fpArqLOG                := '' ;
  fpComandoLOG            := '' ;

  fpDadosReducaoZClass    := TACBrECFDadosRZ.Create ;
  fpConsumidor            := TACBrECFConsumidor.create ;
  fpInfoRodapeCupom       := TACBrECFRodape.Create;
  fpRespostasComando      := TACBrInformacoes.Create;

  {$IFNDEF NOGUI}
    fsFormMsg                   := nil ;
    fsFormMsgProcedureAExecutar := nil ;
    fsFormMsgTeclaParaFechar    := 0 ;
    fsFormMsgEstado             := fmsNenhum ;
    fsFormMsgControla           := true ;
  {$ENDIF}
end;

destructor TACBrECFClass.Destroy;
begin
  Desativar ;
  fpDevice  := nil ; { Apenas remove referencia (ponteiros internos) }

  if Assigned( fpAliquotas ) then
     fpAliquotas.Free ;

  if Assigned( fpFormasPagamentos ) then
     fpFormasPagamentos.Free ;

  if Assigned( fpRelatoriosGerenciais ) then
     fpRelatoriosGerenciais.Free ;

  if Assigned( fpComprovantesNaoFiscais ) then
     fpComprovantesNaoFiscais.Free ;

  if Assigned( fpUnidadesMedida ) then
     fpUnidadesMedida.Free;

  fpDadosReducaoZClass.Free;
  fpConsumidor.Free ;
  fpInfoRodapeCupom.Free ;
  fpRespostasComando.Free ;

  {$IFNDEF NOGUI}
    if Assigned( fsFormMsg ) then
       FreeAndNil( fsFormMsg ) ;
  {$ENDIF}

  inherited Destroy ;
end;

procedure TACBrECFClass.SetAtivo(const Value: Boolean);
begin
  if Value then
     Ativar
  else
     Desativar ;
end;

function TACBrECFClass.GetPathDLL : string ;
begin
  Result := PathWithDelim(fsPathDLL);
end;

procedure TACBrECFClass.Ativar;
begin
  if fpAtivo then exit ;

  GravaLog( sLineBreak +
            StringOfChar('-',80)+ sLineBreak +
            'ATIVAR - '+FormatDateTime('dd/mm/yy hh:nn:ss:zzz',now)+
            ' - Modelo: '+ModeloStr+
            ' - Porta: '+fpDevice.Porta+
            ' - TimeOut: '+IntToStr(TimeOut)+ sLineBreak +
            '         Device: '+fpDevice.DeviceToString(False) + sLineBreak +
            StringOfChar('-',80) + sLineBreak );

  fpDevice.Ativar ;

  fpEstado := estDesconhecido ;
  fpAtivo  := true ;
end;

procedure TACBrECFClass.Desativar;
begin
  AguardandoResposta := False;
  AguardaImpressao   := False;

  fpEstado := estNaoInicializada ;

  if not fpAtivo then exit ;

  fpDevice.Desativar ;
  fpAtivo := false ;
end;


{------------------------------------------------------------------------------}
function TACBrECFClass.EnviaComando(cmd: AnsiString; lTimeOut: Integer): AnsiString;
Var wTimeOut : Integer ;
begin
  wTimeOut := TimeOut ;                      { Salvando os valores antigos }
  TimeOut  := max(lTimeOut,wTimeOut) ;       { Novo Valor recebido pelo metodo }

  try
     result := EnviaComando( cmd ) ;
  finally
     TimeOut := wTimeOut ;     { Restaurando valor antigo }
  end ;
end;

function TACBrECFClass.EnviaComando(cmd: AnsiString; lTimeOut,
  lTempoInicioMsg: Integer): AnsiString;
Var wTimeOut, wTempoInicioMsg : Integer ;
begin
  wTimeOut        := TimeOut ;          { Salvando os valores antigos }
  wTempoInicioMsg := TempoInicioMsg ;

  TimeOut         := max(lTimeOut,wTimeOut) ;   { Novos Valores recebidos pelo metodo }
  TempoInicioMsg  := max(lTempoInicioMsg,wTempoInicioMsg) ;

  try
     result := EnviaComando( cmd ) ;
  finally
     TimeOut        := wTimeOut ;      { Restaurando valores antigos }
     TempoInicioMsg := wTempoInicioMsg ;
  end ;
end;

function TACBrECFClass.EnviaComando(cmd: AnsiString = ''): AnsiString;
begin
  try
    try
       GravaLog('-- '+FormatDateTime('hh:nn:ss:zzz',now)+' '+fpComandoLOG,True );

       if (not fpDevice.Ativo) then
          raise EACBrECFNaoInicializado.create( ACBrStr(cACBrECFNaoInicializadoException) );

       if AguardandoResposta then
          raise EACBrECFOcupado.create( ACBrStr(cACBrECFOcupadoException) ) ;

       VerificaEmLinha ;

       fsBytesRec         := 0 ;
       AguardandoResposta := True ;
       try
          Result := EnviaComando_ECF( Cmd ) ;
       finally
          AguardandoResposta  := False ;
          IgnorarErroSemPapel := False;
          GravaLog('   '+FormatDateTime('hh:nn:ss:zzz',now)+' RX <- '+fpRespostaComando, True);
       end ;
    except
       On E: Exception do
       begin
          GravaLog('----------------- ERRO -----------------' + sLineBreak +
                   ACBrStrToAnsi( E.Message ) + sLineBreak +
                   '----------------------------------------' + sLineBreak );
          raise ;
       end ;
    end ;
  finally
     fpComandoLOG := '' ;
  end ;
end;

Procedure TACBrECFClass.GravaLog(AString: AnsiString; Traduz :Boolean = False);
Var Buf, Ch : AnsiString ;
    I   : Integer ;
    ASC : Byte ;
begin
  if fpArqLOG = '' then
     exit ;

  if not Traduz then
     Buf := AString
  else
   begin
     Buf := '' ;
     For I := 1 to Length(AString) do
     begin
        ASC := Ord(AString[I]) ;

        case AString[I] of
           NUL   : Ch := '[NUL]' ;
           SOH   : Ch := '[SOH]' ;
           STX   : Ch := '[STX]' ;
           ETX   : Ch := '[ETX]' ;
           ENQ   : Ch := '[ENQ]' ;
           ACK   : Ch := '[ACK]' ;
           TAB   : Ch := '[TAB]' ;
           BS    : Ch := '[BS]' ;
           LF    : Ch := '[LF]' ;
           FF    : Ch := '[FF]' ;
           CR    : Ch := '[CR]' ;
           WAK   : Ch := '[WAK]' ;
           NAK   : Ch := '[NAK]' ;
           ESC   : Ch := '[ESC]' ;
           FS    : Ch := '[FS]' ;
           GS    : Ch := '[GS]' ;
           #32..#126 : Ch := AString[I] ;
        else ;
          Ch := '['+IntToStr(ASC)+']'
        end;

        Buf := Buf + Ch ;
     end ;
   end ;

  try
     WriteToTXT(fpArqLOG, Buf, True);
  except
  end ;
end ;

function TACBrECFClass.EnviaComando_ECF(cmd: AnsiString): AnsiString;
begin
  Result := '';
  ErroAbstract( 'EnviaComando_ECF' );
end;

{- LE RESPOSTA - Rotina de Leitura da Resposta do ECF com Bloqueio de Teclado -}

procedure TACBrECFClass.LeResposta;
begin
  {$IFNDEF NOGUI}
    if FormMsgExibe then
     begin
       {$IFDEF MSWINDOWS}
        if (not ExibeMensagem) and Assigned( xBlockInput ) then
         begin
           BlockInput(True, False);
           try
              DoLeResposta ;
           finally
              BlockInput(False, True);
           end ;
         end
        else
       {$ENDIF}
          FormMsgDoProcedure( DoLeResposta, 0 ) ;
     end 
    else
  {$ENDIF}
     DoLeResposta ;

  if (pos('ACBrErro:',fpRespostaComando) = 1)  then
  begin
     fpRespostaComando := copy( fpRespostaComando, 11, Length( fpRespostaComando ) );
     raise EACBrECFTimeOut.create( Format(ACBrStr(cACBrECFSemRespostaException), [ModeloStr]) ) ;
  end ;
end;

procedure TACBrECFClass.DoLeResposta;
Var Fim : Boolean ;
    TempoInicio, TempoLimite : TDateTime ;
    TempoRestante, LenResp : Integer ;
    Texto : AnsiString ;
    ProcessaFormMsg, FimLeitura : Boolean ;
begin
  try
     fpRespostaComando := '' ;
     {$IFNDEF NOGUI}
       ProcessaFormMsg := (Assigned( fsFormMsg ) and fsFormMsgControla) ;
     {$ENDIF}
     
     { Calcula Tempo Limite. Espera resposta at� Tempo Limite. Se a resposta
       for Lida antes, j� encerra. Se nao chegar at� TempoLimite, gera erro.}
     TempoLimite := IncSecond( now, TimeOut) ;
     TempoInicio := IncSecond( now, TempoInicioMsg) ;
     Fim := True ;
     FimLeitura := False ;

     { - Le at� atingir a condi�ao descrita na fun�ao VerificaFimLeitura que
         � particular de cada Classe Filha (override)
       - VerificaFimImpressao � necess�rio apenas nos ECFs que respondem
         antes do termino da impressao (Sweda, Bematech, Daruma) }
     repeat
        { Atualizando a Msg no Form }
        {$IFNDEF NOGUI}
          if ProcessaFormMsg and (now >= TempoInicio) then
          begin
             TempoRestante := SecondsBetween( now, TempoLimite) ;
             
             if (TimeOut - TempoRestante) > 1 then
              begin
                try
                   Texto := Format(MsgAguarde, [ TempoRestante ]) ;
                except
                   Texto := MsgAguarde ;
                end ;
              end
             else
                Texto := MsgTrabalhando ;

             FormMsgPinta( Texto );
          end ;
        {$ENDIF}

        if now > TempoLimite then       { TimeOut }
        begin
           {$IFNDEF NOGUI}
             if Retentar then
             begin
                if ProcessaFormMsg then
                begin
                   fsFormMsg.Width  := 0 ;  { Escondendo o Form da Msg }
                   fsFormMsg.Height := 0 ;
                end ;

                if DoOnMsgRetentar( Format(cACBrECFDoOnMsgSemRespostaRetentar,
                                          [ ModeloStr ]),
                    'LerResposta') then
                   TempoLimite := IncSecond( now, TimeOut)  ;
             end ;
           {$ENDIF}

           if now > TempoLimite then      { Respondeu Nao a Retentar }
           begin
              fpRespostaComando := 'ACBrErro: '+fpRespostaComando ;
              break ;
           end ;
        end ;

        Fim := True ;
        if not FimLeitura then
         begin
           Fim  := False ;
           try
              fpRespostaComando := fpRespostaComando + { Le conteudo da porta }
                                   fpDevice.LeString(100) ;

              LenResp := Length( fpRespostaComando ) ;
              if LenResp <> fsBytesRec then
              begin
                 // ECF est� respondendo, portanto est� trabalhando //
                 TempoLimite := IncSecond(now, TimeOut);
                 fsBytesRec  := LenResp ;
              end ;
           except
              sleep(10) ;
           end ;

           FimLeitura := VerificaFimLeitura(fpRespostaComando, TempoLimite) ;
         end
        else
           if AguardaImpressao then
           begin
              Fim := VerificaFimImpressao( TempoLimite ) ;

              if not Fim then
                 sleep(200) ;
           end ;

        {$IFNDEF NOGUI}
          if fpDevice.ProcessMessages then
	     Application.ProcessMessages;
        {$ENDIF}
     until Fim ;
  finally
     AguardaImpressao := False ;
     if Assigned( fsOnMsgAguarde ) then
        fsOnMsgAguarde( '' ) ;
  end ;
end;

{ Essa fun��o PODE ser override por cada Classe Filha criada
 - Transmite a string do Comando.
   - Se conseguiu retorna True.
   - Se n�o conseguiu e a propriedade Retentar, estiver ligada retorna False,
   - Se Retentar estiver desligada ou respondeu NAO ao Retentar, dispara Excecao}
function TACBrECFClass.TransmiteComando(Cmd: AnsiString): Boolean;
begin
  Result := True ;

  try
     fpDevice.EnviaString( Cmd );   { Eviando o comando }
     GravaLog('                TX -> '+Cmd, True);
  except
     if not DoOnMsgRetentar(Format(cACBrECFCmdSemRespostaException, [ ModeloStr ]),
       'TransmitirComando') then
       raise EACBrECFSemResposta.create(Format(ACBrStr(cACBrECFEnviaCmdSemRespostaException), [ ModeloStr ])) 
     else
        Result := False ;
  end ;
end;


{ Essa fun��o DEVE ser override por cada Classe Filha criada }
function TACBrECFClass.VerificaFimLeitura(var Retorno: AnsiString;
   var TempoLimite: TDateTime) : Boolean ;
begin
  Result := False;
  raise EACBrECFErro.Create( ACBrStr(Format(cACBrECFVerificaFimLeituraException, [ ModeloStr ]))) ;
end;

function TACBrECFClass.VerificaFimImpressao(var TempoLimite: TDateTime): Boolean;
begin
{ Essa fun��o PODE ser override por cada Classe Filha criada
  - Ela � necess�ria apenas para ECFs que respondem antes do termino da
    Impressao como a Sweda, Bematech, Daruma, etc.
  - Substitui a antiga fun��o "EnviaComandoEspera"
  - Para usa-la ative a Propriedade "AguardarImpressao" entes de chamar
    "EnviaComando"
  - IMPORTANTE: N�o � permitido o uso de chamadas EnviaComando dentra dessa
    fun��o, caso constr�rio ela entrar� em chamada Recursiva Infinita }

  Result := EmLinha(1) ;  //  Result := True ;
end;

Procedure TACBrECFClass.VerificaEmLinha( TimeOut : Integer = 3) ;
begin
  while not EmLinha( TimeOut ) do  { Impressora est� em-linha ? }
  begin
     {$IFNDEF NOGUI}
       if Retentar and
          DoOnMsgRetentar(Format(cACBrECFVerificaEmLinhaMsgRetentar,
                           [ ModeloStr ]), 'VerEmLinha') then
          Continue ;
     {$ENDIF}

     raise EACBrECFSemResposta.create(Format(ACBrStr(cACBrECFVerificaEmLinhaException),
                                     [ ModeloStr ])) ;
  end ;
end;


function TACBrECFClass.GetTimeOut: Integer;
begin
  Result := fpDevice.TimeOut ;
end;

procedure TACBrECFClass.SetTimeOut(const Value: Integer);
begin
  fpDevice.TimeOut := Value ;
end;

{ Essa fun��o PODE ser override por cada Classe Filha criada }
Function TACBrECFClass.TestaPodeAbrirCupom : Boolean ;
Var Msg : String ;
    Est : TACBrECFEstado ;
begin
  Result := true ;
  Est    := Estado ;

  case Est of
     estRequerX :
        Msg := Format(cACBrECFPodeAbrirCupomRequerX, [ ModeloStr ]);
     estRequerZ :
        Msg := cACBrECFPodeAbrirCupomRequerZ;
     estBloqueada :
        Msg := cACBrECFPodeAbrirCupomBloqueada;
     estVenda :
        Msg := cACBrECFPodeAbrirCupomCFAberto;
     estNaoInicializada :
        Msg := cACBrECFPodeAbrirCupomNaoAtivada;
     estLivre :
        Msg := '' ;
  else ;
     Msg := Format(cACBrECFPodeAbrirCupomEstado,
                     [ ModeloStr, GetEnumName(TypeInfo(TACBrECFEstado), integer(Est)) ]);
  end;

  if Msg <> '' then
  begin
     result := false ;
     GeraErro( EACBrECFCMDInvalido.Create( ACBrStr(Msg) ) );
  end ;
end;

procedure TACBrECFClass.SetAguardandoResposta(const Value: Boolean);
begin
  if Value = fsAguardandoResposta then exit ;

  fsAguardandoResposta := Value;
  if Assigned( fsOnAguardandoRespostaChange ) then
     fsOnAguardandoRespostaChange( self ) ;
end;

procedure TACBrECFClass.GeraErro( E : Exception ) ;
begin
  if Assigned( fsOnMsgErro ) then
     fsOnMsgErro( E )
  else
     raise E ;
end;

function TACBrECFClass.EmLinha( lTimeOut: Integer): Boolean;
begin
  Result := fpDevice.EmLinha( lTimeOut ) ;
end;

function TACBrECFClass.GetDataHora: TDateTime;
begin
  Result := now ;
end;

function TACBrECFClass.GetNumECF: String;
begin
  Result := '001' ;
end;

function TACBrECFClass.GetNumCRO: String;
begin
  Result := '001' ;
end;

function TACBrECFClass.GetNumCCF: String;
begin
  Result := '' ;
end;

function TACBrECFClass.GetNumGNF: String;
begin
  Result := '' ;
end;

function TACBrECFClass.GetNumGNFC: String;
begin
  Result := '' ;
end;

function TACBrECFClass.GetNumGRG: String;
begin
  Result := '' ;
end;

function TACBrECFClass.GetNumCDC: String;
begin
  Result := '' ;
end;

function TACBrECFClass.GetNumCFC: String;
begin
  Result := '' ;
end;

function TACBrECFClass.GetNumCFD: String;
begin
  Result := '' ;
end;

function TACBrECFClass.GetNumLoja: String;
begin
  Result := '001' ;
end;

function TACBrECFClass.GetNumNCN: String;
begin
  Result := '' ;
end;

function TACBrECFClass.GetNumCCDC: String;
begin
  Result := '' ;
end;

function TACBrECFClass.GetNumSerie: String;
begin
  Result := '' ;
end;

function TACBrECFClass.GetNumSerieMFD: String;
begin
  Result := '' ;
end;

{ Essa fun��o DEVE ser override por cada Classe Filha criada }
Procedure TACBrECFClass.AbreGaveta ;
begin
  GeraErro( EACBrECFCMDInvalido.Create( Format(cACBrECFAbreGavetaException, [ModeloStr] ))) ;
end;

{ Essa fun��o DEVE ser override por cada Classe Filha criada }
function TACBrECFClass.GetEstado: TACBrECFEstado;
begin
  Result := fpEstado ;
end;

{ Essa fun��o PODE ser override por cada Classe Filha criada }
function TACBrECFClass.GetGavetaAberta: Boolean;
begin
  Result := false ;
end;

{ Essa fun��o DEVE ser override por cada Classe Filha criada }
function TACBrECFClass.GetPoucoPapel: Boolean;
begin
  Result := false ;
end;

{ Essa fun��o DEVE ser override por cada Classe Filha criada }
function TACBrECFClass.GetArredonda: Boolean;
begin
  Result := false ;
end;

{ Essa fun��o DEVE ser override por cada Classe Filha criada }
function TACBrECFClass.GetHorarioVerao: Boolean;
begin
  Result := false ;
end;

{ Essa fun��o PODE ser override por cada Classe Filha criada }
procedure TACBrECFClass.ImpactoAgulhas( NivelForca : Integer = 2);
begin
  GeraErro( EACBrECFCMDInvalido.Create( Format(cACBrECFImpactoAgulhasException,
                                               [ ModeloStr ] ))) ;
end;

{ Essa fun��o PODE ser override por cada Classe Filha criada }
procedure TACBrECFClass.LeituraMemoriaFiscal(ReducaoInicial,
   ReducaoFinal: Integer; Simplificada : Boolean);
begin
  ErroAbstract('LeituraMemoriaFiscal');
end;

procedure TACBrECFClass.LeituraMemoriaFiscal(DataInicial, DataFinal: TDateTime;
   Simplificada : Boolean);
begin
  ErroAbstract('LeituraMemoriaFiscal');
end;

procedure TACBrECFClass.LeituraMemoriaFiscalSerial(ReducaoInicial,
   ReducaoFinal: Integer; Linhas : TStringList; Simplificada : Boolean);
begin
  ErroAbstract('LeituraMemoriaFiscalSerial');
end;

procedure TACBrECFClass.LeituraMemoriaFiscalSerial(DataInicial,
   DataFinal: TDateTime; Linhas : TStringList; Simplificada : Boolean);
begin
  ErroAbstract('LeituraMemoriaFiscalSerial');
end;


procedure TACBrECFClass.LeituraMFDSerial(DataInicial, DataFinal: TDateTime;
  Linhas: TStringList; Documentos : TACBrECFTipoDocumentoSet );
begin
  ErroAbstract('LeituraMFDSerial');
end;

procedure TACBrECFClass.LeituraMFDSerial(COOInicial,
  COOFinal: Integer; Linhas: TStringList; Documentos : TACBrECFTipoDocumentoSet);
begin
  ErroAbstract('LeituraMFDSerial');
end;

procedure TACBrECFClass.EspelhoMFD_DLL(DataInicial,
  DataFinal: TDateTime; NomeArquivo: AnsiString;
  Documentos: TACBrECFTipoDocumentoSet);
begin
  ErroAbstract('EspelhoMFD_DLL');
end;

procedure TACBrECFClass.EspelhoMFD_DLL(COOInicial, COOFinal: Integer;
  NomeArquivo: AnsiString; Documentos: TACBrECFTipoDocumentoSet);
begin
  ErroAbstract('EspelhoMFD_DLL');
end;

procedure TACBrECFClass.ArquivoMFD_DLL(DataInicial, DataFinal: TDateTime;
  NomeArquivo: AnsiString; Documentos: TACBrECFTipoDocumentoSet;
  Finalidade: TACBrECFFinalizaArqMFD);
begin
  ErroAbstract('ArquivoMFD_DLL');
end;

procedure TACBrECFClass.ArquivoMFD_DLL(ContInicial, ContFinal: Integer;
  NomeArquivo: AnsiString; Documentos: TACBrECFTipoDocumentoSet;
  Finalidade: TACBrECFFinalizaArqMFD; TipoContador: TACBrECFTipoContador);
begin
  ErroAbstract('ArquivoMFD_DLL');
end;


{ Essa fun��o PODE ser override por cada Classe Filha criada }
procedure TACBrECFClass.ImprimeCheque(Banco: String; Valor: Double; Favorecido,
  Cidade: String; Data: TDateTime; Observacao: String);
begin
  GeraErro( EACBrECFCMDInvalido.Create( Format(cACBrECFImprimeChequeException,
                                               [ ModeloStr ] ))) ;
end;

{ Essa fun��o PODE ser override por cada Classe Filha criada }
Function TACBrECFClass.LeituraCMC7 : AnsiString ;
begin
  Result := '';
  GeraErro( EACBrECFCMDInvalido.Create( Format(cACBrECFLeituraCMC7Exception,
                                               [ ModeloStr ] ))) ;
end;

{ Essa fun��o PODE ser override por cada Classe Filha criada }
procedure TACBrECFClass.CancelaImpressaoCheque;
begin
  ErroAbstract('CancelaImpressaoCheque');
end;

{ Essa fun��o PODE ser override por cada Classe Filha criada }
function TACBrECFClass.GetChequePronto: Boolean;
begin
  result := True ;
end;

function TACBrECFClass.ConfigBarras: TACBrECFConfigBarras;
begin
  Result := TACBrECF(fpOwner).ConfigBarras;
end;

procedure TACBrECFClass.CorrigeEstadoErro( Reducao: Boolean );
begin
  case Estado of
     estRequerX : LeituraX ;

     estRequerZ :
       if Reducao then
         try
           ReducaoZ(now);
         except
           try
             CancelaCupom;
             ReducaoZ(now);
           except
           end;
         end;

     estRelatorio : FechaRelatorio ;

     estVenda, estPagamento :  CancelaCupom ;

     estNaoFiscal : CancelaNaoFiscal ;

     estBloqueada : GeraErro( EACBrECFCMDInvalido.Create(
                               ACBrStr(cACBrECFPodeAbrirCupomBloqueada) ) );
  end;

  if Estado <> estLivre then
     try
        FechaNaoFiscal ;
     except
     end
  else
     exit ;

  if Estado <> estLivre then
     try
        CancelaNaoFiscal ;
     except
     end 
  else
     exit ;

  if Estado <> estLivre then
     try
        FechaRelatorio ;
     except
     end ;
end;

procedure TACBrECFClass.AbreCupom ;
begin
  ErroAbstract('AbreCupom');
end;

procedure TACBrECFClass.CancelaCupom;
begin
  ErroAbstract('CancelaCupom');
end;

procedure TACBrECFClass.CancelaItemVendido(NumItem: Integer);
begin
  ErroAbstract('CancelaItemVendido');
end;

procedure TACBrECFClass.EfetuaPagamento(CodFormaPagto: String;
  Valor: Double; Observacao: AnsiString; ImprimeVinculado: Boolean);
begin
  ErroAbstract('EfetuaPagamento');
end;

procedure TACBrECFClass.EstornaPagamento(const CodFormaPagtoEstornar,
  CodFormaPagtoEfetivar : String; const Valor: Double;
   Observacao : AnsiString = '') ;
begin
  ErroAbstract('EstornaPagamento');
end;

procedure TACBrECFClass.FechaCupom(Observacao: AnsiString; IndiceBMP : Integer);
begin
  ErroAbstract('FechaCupom');
end;

procedure TACBrECFClass.FechaRelatorio;
begin
  ErroAbstract('FechaRelatorio');
end;

procedure TACBrECFClass.AbreNaoFiscal(CPF_CNPJ : String ; Nome : String ;
   Endereco : String) ;
begin
  ErroAbstract('AbreNaoFiscal');
end;

procedure TACBrECFClass.CancelaNaoFiscal;
begin
  CancelaCupom ;
end;

Procedure TACBrECFClass.CancelaItemNaoFiscal(const AItem: Integer);
begin
  ErroAbstract('AbreNaoFiscal');
end;

procedure TACBrECFClass.EfetuaPagamentoNaoFiscal(CodFormaPagto: String;
  Valor: Double; Observacao: AnsiString; ImprimeVinculado: Boolean);
begin
  EfetuaPagamento( CodFormaPagto, Valor, Observacao, ImprimeVinculado );
end;

procedure TACBrECFClass.FechaNaoFiscal(Observacao: AnsiString; IndiceBMP : Integer);
begin
  FechaCupom(Observacao, IndiceBMP);
end;

procedure TACBrECFClass.NaoFiscalCompleto(CodCNF: String; Valor: Double;
  CodFormaPagto: String; Obs: AnsiString; IndiceBMP : Integer);
begin
  { Chama rotinas da classe Pai (fpOwner) para atualizar os Memos }
  with TACBrECF(fpOwner) do
  begin
     AbreNaoFiscal ;
     try
        RegistraItemNaoFiscal(CodCNF, Valor);
        SubtotalizaNaoFiscal(0);
        EfetuaPagamentoNaoFiscal(CodFormaPagto, Valor );
        FechaNaoFiscal( Obs, IndiceBMP);
     except
        try
           CancelaNaoFiscal
        except
        end;

        raise ;
     end ;
  end ;
end;

procedure TACBrECFClass.RegistraItemNaoFiscal(CodCNF: String;
  Valor: Double; Obs: AnsiString = '');
begin
  ErroAbstract('RegistraItemNaoFiscal');
end;

procedure TACBrECFClass.SubtotalizaNaoFiscal(DescontoAcrescimo: Double;
   MensagemRodape: AnsiString);
begin
  SubtotalizaCupom(DescontoAcrescimo, MensagemRodape);
end;

procedure TACBrECFClass.Sangria(const Valor : Double ; Obs : AnsiString ;
  DescricaoCNF : String ; DescricaoFPG : String; IndiceBMP: Integer) ;
Var
  CNF : TACBrECFComprovanteNaoFiscal ;
  FPG : TACBrECFFormaPagamento ;
begin
  CNF := AchaCNFDescricao(DescricaoCNF, True) ;
  if CNF = nil then
     raise EACBrECFErro.Create( ACBrStr(Format(cACBrECFAchaCNFException,
                                   [ DescricaoCNF ] ))) ;

  FPG := AchaFPGDescricao(DescricaoFPG, True) ;
  if FPG = nil then
     raise EACBrECFErro.Create( ACBrStr(Format(cACBrECFAchaFPGException,
                                   [ DescricaoFPG ]))) ;

  NaoFiscalCompleto( CNF.Indice, Valor, FPG.Indice, Obs);
end;

procedure TACBrECFClass.Suprimento(const Valor : Double ; Obs : AnsiString ;
  DescricaoCNF : String ; DescricaoFPG : String; IndiceBMP: Integer) ;
begin
  Sangria( Valor, Obs, DescricaoCNF, DescricaoFPG, IndiceBMP);
end;

function TACBrECFClass.EstornaCCD(const Todos : Boolean) : Integer ;
begin
  Result := 0;
  ErroAbstract('EstornaCCD');
end ;

procedure TACBrECFClass.PulaLinhas(NumLinhas: Integer);
begin
  if NumLinhas = 0 then
     NumLinhas := LinhasEntreCupons ;

  LinhaRelatorioGerencial( StringOfChar(#10, NumLinhas ) ) ;
end;

procedure TACBrECFClass.CortaPapel(const CorteParcial : Boolean ) ;
begin
  LinhaRelatorioGerencial( #10+#10+#10 ) ;
end;

function TACBrECFClass.GetNumCupom: String;
begin
  Result := '' ;
end;

function TACBrECFClass.GetNumVersao: String ;
begin
  Result := ''
end;

function TACBrECFClass.GetNumReducoesZRestantes: String;
var
  CRZR: String;
  I: Integer;
  LeituraX: TStringList;
  Linha: String;
begin
  // implementada a leitura do contador pela leitura X para suprir
  // as impressoras que n�o possuem retorno do contador
  // nas impressoras que possuem, sobrescrever o m�todo e utilizar o
  // comando apropriado
  CRZR := '';
  LeituraX := TStringList.Create;
  try
    LeituraXSerial(LeituraX);

    for I := LeituraX.Count - 1 downto 0 do
    begin
      Linha := AnsiUpperCase(LeituraX[I]);
      if pos('REDU��ES RESTANTES:', Linha) > 0 then
      begin
        CRZR := Trim(Copy(Linha, 30, 40));
        CRZR := ACBrUtil.OnlyNumber(CRZR);
        Break;
      end;
    end;
  finally
    LeituraX.Free;
  end;

  Result := Trim( CRZR ) ;
end;

function TACBrECFClass.GetSubTotal: Double;
begin
  Result := 0 ;
end;

function TACBrECFClass.GetTotalPago: Double;
begin
  Result := 0 ;
end;

function TACBrECFClass.GetCNPJ: String;
begin
  Result := '' ;
end;

function TACBrECFClass.GetIE: String;
begin
  Result := '' ;
end;

function TACBrECFClass.GetIM: String;
begin
  Result := '' ;
end;
function TACBrECFClass.GetCliche: AnsiString;
begin
  Result := '' ;
end;

function TACBrECFClass.GetUsuarioAtual: String;
begin
  Result := '' ;
end;

function TACBrECFClass.GetDataHoraSB: TDateTime;
begin
  Result := now ;
end;
function TACBrECFClass.GetSubModeloECF: String;
begin
  Result := '' ;
end;

function TACBrECFClass.GetPAF: String;
begin
  Result := '' ;
end;

{ Essa fun��o DEVE ser override por cada Classe Filha criada }
function TACBrECFClass.GetParamDescontoISSQN: Boolean;
begin
  Result := (Trim(IM) <> '') ;
end;

function TACBrECFClass.GetTipoUltimoDocumento : TACBrECFTipoDocumento ;
begin
   Result := docNenhum;
end ;

function TACBrECFClass.GetDataMovimento: TDateTime;
begin
  Result := now ;
end;

function TACBrECFClass.GetGrandeTotal: Double;
begin
  Result := 0 ;
end;

function TACBrECFClass.GetNumCRZ: String ;
begin
  Result := '00000'
end;

function TACBrECFClass.GetTotalAcrescimos: Double;
begin
  Result := 0 ;
end;

function TACBrECFClass.GetTotalCancelamentos: Double;
begin
  Result := 0 ;
end;

function TACBrECFClass.GetTotalDescontos: Double;
begin
  Result := 0 ;
end;

function TACBrECFClass.GetTotalTroco: Double;
begin
  Result := 0 ;
end;

function TACBrECFClass.GetTotalSubstituicaoTributaria: Double;
begin
  Result := 0 ;
end;

function TACBrECFClass.GetTotalIsencao: Double;
begin
  Result := 0 ;
end;

function TACBrECFClass.GetTotalNaoFiscal: Double;
  Var I : Integer ;
begin
  Result := 0 ;
  try
     LerTotaisComprovanteNaoFiscal ;

     For I := 0 to ComprovantesNaoFiscais.Count-1 do
        Result := Result + ComprovantesNaoFiscais[I].Total ;
  except
  end ;
end;

function TACBrECFClass.GetTotalAcrescimosISSQN: Double;
begin
  Result := 0;
end;

function TACBrECFClass.GetTotalCancelamentosISSQN: Double;
begin
  Result := 0;
end;

function TACBrECFClass.GetTotalDescontosISSQN: Double;
begin
  Result := 0;
end;

function TACBrECFClass.GetTotalAcrescimosOPNF: Double;
begin
  Result := 0;
end;

function TACBrECFClass.GetTotalCancelamentosOPNF: Double;
begin
  Result := 0;
end;

function TACBrECFClass.GetTotalDescontosOPNF: Double;
begin
  Result := 0;
end;

function TACBrECFClass.GetTotalIsencaoISSQN: Double;
begin
  Result := 0;
end;

function TACBrECFClass.GetTotalNaoTributadoISSQN: Double;
begin
  Result := 0;
end;

function TACBrECFClass.GetTotalSubstituicaoTributariaISSQN: Double;
begin
  Result := 0;
end;

function TACBrECFClass.GetNumUltimoItem: Integer;
begin
  Result := 0 ;
end;

function TACBrECFClass.GetTotalNaoTributado: Double;
begin
  Result := 0 ;
end;

function TACBrECFClass.GetVendaBruta: Double;
begin
  Result := 0 ;
end;

function TACBrECFClass.GetNumCOOInicial: String;
begin
  Result := ''
end;

function TACBrECFClass.GetDadosUltimaReducaoZ: AnsiString;
begin
  Result := '';
  ErroAbstract('DadosUltimaReducaoZ');
end;

function TACBrECFClass.GetDadosReducaoZ: AnsiString;
Var
  I     : Integer ;
  AliqZ : TACBrECFAliquota ;
  FPGZ  : TACBrECFFormaPagamento ;
  CNFZ  : TACBrECFComprovanteNaoFiscal ;
  RGZ   : TACBrECFRelatorioGerencial ;
begin
  { Alimenta a class com os dados atuais do ECF }
  with fpDadosReducaoZClass do
  begin
    { Zerar variaveis e inicializa Dados do ECF }
    InitDadosUltimaReducaoZ;

    with TACBrECF(fpOwner) do
    begin
      { REDU��O Z }
      try DataDoMovimento  := DataMovimento; except end ;
      try NumeroCOOInicial := NumCOOInicial; except end ;

      { CONTADORES }
      try COO  := StrToIntDef(NumCOO,0);  except end ;
      try GNF  := StrToIntDef(NumGNF,0);  except end ;
      try CRO  := StrToIntDef(NumCRO,0);  except end ;
      try CRZ  := StrToIntDef(NumCRZ,0);  except end ;
      try CCF  := StrToIntDef(NumCCF,0);  except end ;
      try CDC  := StrToIntDef(NumCDC,0);  except end ;
      try CFC  := StrToIntDef(NumCFC,0);  except end ;
      try GRG  := StrToIntDef(NumGRG,0);  except end ;
      try GNFC := StrToIntDef(NumGNFC,0); except end ;
      try CFD  := StrToIntDef(NumCFD,0);  except end ;
      try NCN  := StrToIntDef(NumNCN,0);  except end ;
      try CCDC := StrToIntDef(NumCCDC,0); except end ;

      { TOTALIZADORES }
      try ValorGrandeTotal  := GrandeTotal;             except end ;
      try ValorVendaBruta   := VendaBruta;              except end ;
      try CancelamentoICMS  := TotalCancelamentos;      except end ;
      try DescontoICMS      := TotalDescontos;          except end ;
      try AcrescimoICMS     := TotalAcrescimos;         except end ;
      try CancelamentoISSQN := TotalCancelamentosISSQN; except end ;
      try DescontoISSQN     := TotalDescontosISSQN;     except end ;
      try AcrescimoISSQN    := TotalAcrescimosISSQN;    except end ;
      try CancelamentoOPNF  := TotalCancelamentosOPNF;  except end ;
      try DescontoOPNF      := TotalDescontosOPNF;      except end ;
      try AcrescimoOPNF     := TotalAcrescimosOPNF;     except end ;

      { Copiando objetos de ICMS e ISS}
      try
        CarregaAliquotas;
        LerTotaisAliquota;

        for I := 0 to fpAliquotas.Count - 1 do
        begin
          AliqZ := TACBrECFAliquota.Create ;
          AliqZ.Assign( fpAliquotas[I] );

          AdicionaAliquota( AliqZ );
        end;
      except
      end;

      { ICMS }
      try SubstituicaoTributariaICMS  := TotalSubstituicaoTributaria; except end ;
      try IsentoICMS                  := TotalIsencao;                except end ;
      try NaoTributadoICMS            := TotalNaoTributado;           except end ;

      { ISSQN }
      try SubstituicaoTributariaISSQN := TotalSubstituicaoTributariaISSQN; except end ;
      try IsentoISSQN                 := TotalIsencaoISSQN;                except end ;
      try NaoTributadoISSQN           := TotalNaoTributadoISSQN;           except end ;

      { TOTALIZADORES N�O FISCAIS }
      try
        CarregaComprovantesNaoFiscais ;
        LerTotaisComprovanteNaoFiscal ;

        For I := 0 to fpComprovantesNaoFiscais.Count-1 do
        begin
          CNFZ := TACBrECFComprovanteNaoFiscal.Create ;
          CNFZ.Assign( fpComprovantesNaoFiscais[I] );

          TotalizadoresNaoFiscais.Add( CNFZ ) ;
        end ;

        TotalOperacaoNaoFiscal := TotalNaoFiscal;
      except
      end ;

      { RELAT�RIO GERENCIAL }
      try
        CarregaRelatoriosGerenciais ;

        For I := 0 to fpRelatoriosGerenciais.Count-1 do
        begin
           RGZ := TACBrECFRelatorioGerencial.Create ;
           RGZ.Assign( fpRelatoriosGerenciais[I] );

           fpDadosReducaoZClass.RelatorioGerencial.Add( RGZ ) ;
        end ;
      except
      end ;

      { MEIOS DE PAGAMENTO }
      try
        CarregaFormasPagamento ;
        LerTotaisFormaPagamento ;

        For I := 0 to fpFormasPagamentos.Count-1 do
        begin
          FPGZ := TACBrECFFormaPagamento.Create ;
          FPGZ.Assign( fpFormasPagamentos[I] );

          MeiosDePagamento.Add( FPGZ ) ;
        end ;

        fpDadosReducaoZClass.TotalTroco := TACBrECF(fpOwner).TotalTroco;
      except
      end ;
    end;

    CalculaValoresVirtuais;
    Result := MontaDadosReducaoZ;
  end;
end;

procedure TACBrECFClass.InitDadosUltimaReducaoZ;
begin
  with fpDadosReducaoZClass do
  begin
    Clear ;

    { DADOS DO ECF }
    with TACBrECF(fpOwner) do
    begin
      try DataDaImpressora := DataHora;    except end ;
      try NumeroDeSerie    := NumSerie;    except end ;
      try NumeroDeSerieMFD := NumSerieMFD; except end ;
      try NumeroDoECF      := NumECF;      except end ;
      try NumeroDaLoja     := NumLoja;     except end ;
    end;
  end;
end;

procedure TACBrECFClass.LeituraX;
begin
  ErroAbstract('LeituraX');
end;

procedure TACBrECFClass.LeituraXSerial(Linhas: TStringList);
begin
  ErroAbstract('LeituraXSerial');
end;

procedure TACBrECFClass.MudaHorarioVerao;
begin
  ErroAbstract('MudaHorarioVerao');
end;

procedure TACBrECFClass.MudaHorarioVerao(EHorarioVerao: Boolean);
begin
  ErroAbstract('MudaHorarioVerao(EHorarioVerao: Boolean)');
end;

procedure TACBrECFClass.MudaArredondamento(Arredondar: Boolean);
begin
  ErroAbstract('MudaArredondamento');
end;

procedure TACBrECFClass.IdentificaOperador(Nome: String);
begin
//  ErroAbstract('IdentificaOperador');
end;

procedure TACBrECFClass.IdentificaPAF(NomeVersao, MD5: String);
begin
  ErroAbstract('IdentificaPAF');
end;

Function TACBrECFClass.RetornaInfoECF( Registrador : String ) : AnsiString ;
begin
  Result := '';
  ErroAbstract('RetornaInfoECF');
end;

procedure TACBrECFClass.PreparaTEF;
begin
  try
     FechaRelatorio ;
  except
  end ;

  TACBrECF( fpOwner ).CarregaAliquotas ;
  TACBrECF( fpOwner ).CarregaFormasPagamento ;
  TACBrECF( fpOwner ).CarregaComprovantesNaoFiscais ;
end;

procedure TACBrECFClass.ReducaoZ(DataHora: TDateTime);
begin
  ErroAbstract('ReducaoZ');
end;

procedure TACBrECFClass.SubtotalizaCupom(DescontoAcrescimo: Double;
       MensagemRodape : AnsiString);
begin
  ErroAbstract('SubtotalizaCupom');
end;

procedure TACBrECFClass.CancelaDescontoAcrescimoSubTotal(
  TipoAcrescimoDesconto: Char);
begin
  ErroAbstract('CancelaDescontoAcrescimoSubTotal');
end;

procedure TACBrECFClass.LegendaInmetroProximoItem;
begin
  ErroAbstract('LegendaInmetroProximoItem');
end;

Procedure TACBrECFClass.VendeItem( Codigo, Descricao : String;
  AliquotaECF : String; Qtd : Double ; ValorUnitario : Double;
  ValorDescontoAcrescimo : Double; Unidade : String;
  TipoDescontoAcrescimo : String; DescontoAcrescimo : String;
  CodDepartamento: Integer) ;
begin
  ErroAbstract('VendeItem');
end;

Procedure TACBrECFClass.DescontoAcrescimoItemAnterior(
   ValorDescontoAcrescimo : Double; DescontoAcrescimo : String;
   TipoDescontoAcrescimo : String; NumItem : Integer) ;
begin
  ErroAbstract('DescontoAcrescimoItemAnterior');
end ;

procedure TACBrECFClass.CancelaDescontoAcrescimoItem(NumItem: Integer);
begin
  ErroAbstract('CancelaDescontoAcrescimoItem');
end;

procedure TACBrECFClass.CancelaItemVendidoParcial(NumItem: Integer;
  Quantidade: Double);
begin
  ErroAbstract('CancelaItemVendidoParcial');
end;

procedure TACBrECFClass.ErroAbstract(NomeProcedure: String);
begin
  raise EACBrECFCMDInvalido.create(ACBrStr(Format(cACBrECFCMDInvalidoException,
                                          [NomeProcedure, ModeloStr] ))) ;
end;

function TACBrECFClass.GetModeloStr: String;
begin
  Result := fpModeloStr ;
  //if fpMFD then
  //   Result := Result + ' MFD' ;
end;

procedure TACBrECFClass.DoOnMsgPoucoPapel( Mensagem : String ) ;
begin
  if MsgPoucoPapel < 0 then exit;

  if now > IncSecond(fpUltimaMsgPoucoPapel, MsgPoucoPapel) then { Avisa ? }
  begin
     if Assigned( fsOnMsgPoucoPapel ) then
        fsOnMsgPoucoPapel( self )
     else
      begin
        if Mensagem = '' then
           Mensagem := cACBrECFDoOnMsgPoucoPapel;

        Mensagem := ACBrStr( Mensagem ) ;
        {$IFNDEF NOGUI}
          MessageDlg( Mensagem ,mtError,[mbOk],0)  ;
        {$ELSE}
          writeln( Mensagem ) ;
        {$ENDIF}
      end ;

     fpUltimaMsgPoucoPapel := now ;
  end ;
end;

procedure TACBrECFClass.DoOnChequeEstado(const Estado: TACBrECFCHQEstado;
  var Continuar: Boolean);
begin
  if Assigned( fsOnChequeEstado ) then
    fsOnChequeEstado( Estado, Continuar );
end;

procedure TACBrECFClass.DoOnErrorSemPapel ;
begin
   if fpIgnorarErroSemPapel then exit;

   if Assigned( fsOnErrorSemPapel ) then
      fsOnErrorSemPapel( self )
   else
      raise EACBrECFSemPapel.Create( cACBrECFSemPapelException );
end ;

function TACBrECFClass.DoOnMsgRetentar( const Mensagem : String;
   const Situacao : String = ''): Boolean;
{$IFDEF MSWINDOWS}
 Var
   UsandoBlockInput : Boolean ;
{$ENDIF}
begin
  Result := False ;

  {$IFNDEF NOGUI}
    {$IFDEF MSWINDOWS}
      UsandoBlockInput := False ;

      if fsUsandoBlockInput then
      begin
         UsandoBlockInput := True ;
         BlockInput(False,True);
      end ;
    {$ENDIF}
  {$ENDIF}

  if Assigned( fsOnMsgRetentar ) then
     fsOnMsgRetentar( ACBrStr(Mensagem), Situacao, Result )
  else
   begin
     {$IFNDEF NOGUI}
      if Retentar and
        (MessageDlg( ACBrStr( Mensagem+sLineBreak+sLineBreak + cACBrECFDoOnMsgRetentar ),
                     mtConfirmation,[mbYes,mbNo],0) = mrYes) then
        Result := True ;
     {$ENDIF}
   end ;

  {$IFNDEF NOGUI}
    {$IFDEF MSWINDOWS}
      if UsandoBlockInput then
         BlockInput(True,False);
    {$ENDIF}
  {$ENDIF}
end;

{ Esta rotina � usada por Impressoras que n�o permitem enviar v�rias
  linhas de uma s� vez }
procedure TACBrECFClass.ImprimirLinhaALinha(Texto, Cmd: AnsiString );
Var Linhas : TStringList ;
    I : Integer ;
begin
  if Texto = '' then
     Texto := padL(Texto,Colunas) ;

  Texto := AjustaLinhas( Texto, Colunas );

  Linhas := TStringList.Create ;
  try
     Linhas.Text := Texto ;

     for I := 0 to Linhas.Count-1 do
     begin
        EnviaComando( Cmd + padL( Linhas[I], Colunas), 6 ) ;
        if not fpTermica then
           sleep(100) ;

        { Aguarda 1 segundo ou at� o ECF ficar Em linha novamente }
        { Semelhante ao "AguardaImpressao := True", por�m � mais r�pido, pois no
          m�todo "VerificaFimImpressao" alem de verificado o "EmLinha" tamb�m �
          solicitado o Status do ECF }
        try
           EmLinha( 1 ) ;
        except
        end ;
     end ;
  finally
     Linhas.Free ;
  end ;
end;

{-------------------------------- ALIQUOTAS ----------------------------------}
procedure TACBrECFClass.CarregaAliquotas;
begin
  { Apenas instancia um ObjectList de Aliquotas (TACBrECFAliquotas) vazia }
  if Assigned( fpAliquotas ) then
     fpAliquotas.Free ;

  fpAliquotas := TACBrECFAliquotas.create( true ) ;
end;

procedure TACBrECFClass.LerTotaisAliquota;
begin
  ErroAbstract('LerTotaisAliquota');
end;

procedure TACBrECFClass.ProgramaAliquota(Aliquota: Double; Tipo: Char;
   Posicao : String);
begin
  ErroAbstract('ProgramaAliquota');
end;

function TACBrECFClass.GetAliquotas: TACBrECFAliquotas;
begin
  if not Assigned( fpAliquotas ) then
     TACBrECF( fpOwner ).CarregaAliquotas ;

  result := fpAliquotas ;
end;

function TACBrECFClass.AchaICMSAliquota( var AliquotaICMS: String):
   TACBrECFAliquota;
{   AliquotaICMS, Formatos v�lidos:
  - Por Valor da aliquota, Ex: '18', '12,00' '2,56'
  - Por Valor da Aliquota especificando o Tipo como sufixo
    (T = ICMS, ou S = ISS),      Ex: '18T',  '2,5S'
  - Por Indice, adicione o prefixo 'T', Ex: 'T01', 'T03', 'TA', 'TT01'
    ( o indice deve ser no mesmo formato retornado pela propriedade
      "Aliquotas[n].Indice" (que varia para cada modelo de ECF) )
}
   procedure VerificaTipoAliquota(var AliquotaICMS: String;
     var Tipo: char);
   Var UltDigito : String ;
   begin
     UltDigito := RightStr(AliquotaICMS,1) ;
     case UltDigito[1] of
        'T','S' :
          begin
            Tipo := UltDigito[1] ;
            AliquotaICMS := copy( AliquotaICMS,1,Length(AliquotaICMS)-1 ) ;
          end ;
     else
        Tipo := ' ' ;
     end ;
   end;

   Var AliquotaStr : String ;
       Tipo        : Char ;
       ValAliquota : Double ;
begin
  if not Assigned( fpAliquotas ) then
     TACBrECF( fpOwner ).CarregaAliquotas ;

  Result := nil ;

  AliquotaICMS := UpperCase( TrimLeft( AliquotaICMS ) ) ;
  case AliquotaICMS[1] of
    'I' : AliquotaICMS := 'II' ;
    'N' : AliquotaICMS := 'NN' ;
    'F' : AliquotaICMS := 'FF' ;
  else 
     begin
        if AliquotaICMS[1] = 'T' then  { Informou por Indice ? }
         begin
            AliquotaICMS := copy(AliquotaICMS,2,Length(AliquotaICMS)) ; {Remove o "T"}
            Result := AchaICMSIndice( AliquotaICMS ) ;
         end
        else      { Informou por valor }
         begin
           { Verificando se informou T ou S no final da Aliquota (Tipo) }
           AliquotaStr := AliquotaICMS ;
           Tipo        := ' ' ;
           VerificaTipoAliquota( AliquotaStr, Tipo) ;

           try
              ValAliquota := StringToFloat( AliquotaStr ) ;
           except
              raise EACBrECFCMDInvalido.Create(ACBrStr(cACBrECFAchaICMSAliquotaInvalida) + AliquotaICMS);
           end ;

           Result := AchaICMSAliquota( ValAliquota, Tipo ) ;
         end ;

        if Result = nil then
           raise EACBrECFCMDInvalido.Create(ACBrStr(cACBrECFAchaICMSCMDInvalido) + AliquotaICMS)
     end ;
  end ;

  if Result <> nil then
     AliquotaICMS := Result.Indice ;
end;

function TACBrECFClass.AchaICMSAliquota( Aliquota: Double; Tipo : Char ) :
   TACBrECFAliquota;
var
  A : Integer ;
  ftAliq : Double ;
  cTipo  : Char ;
begin
  if not Assigned( fpAliquotas ) then
     TACBrECF( fpOwner ).CarregaAliquotas ;

  if not (Tipo in ['S','T']) then
     Tipo := ' ' ;

  Aliquota := SimpleRoundTo(Aliquota,-2) ;
  Result   := nil ;

  with fpAliquotas do
  begin
     For A := 0 to Count -1 do
     begin
        ftAliq := SimpleRoundTo(Objects[A].Aliquota,-2) ;
        cTipo  := Objects[A].Tipo ;
        if ( ftAliq = Aliquota) and (Tipo in [' ',cTipo]) then
        begin
           result := Objects[A] ;
           Break ;
        end ;
     end ;
  end ;
end;

function TACBrECFClass.AchaICMSIndice(Indice: String): TACBrECFAliquota;
var A : Integer ;
begin
  if not Assigned( fpAliquotas ) then
     TACBrECF( fpOwner ).CarregaAliquotas ;

  Result := nil ;
  Indice := UpperCase(Indice) ;

  with fpAliquotas do
  begin
     For A := 0 to Count -1 do
     begin
        if UpperCase( Objects[A].Indice ) = Indice then
        begin
           result := Objects[A] ;
           Break ;
        end ;
     end ;
  end ;
end;

{--------------------------- FORMAS DE PAGAMENTO ------------------------------}
procedure TACBrECFClass.CarregaFormasPagamento;
begin
  if Assigned( fpFormasPagamentos ) then
     fpFormasPagamentos.Free ;

  fpFormasPagamentos := TACBrECFFormasPagamento.Create( true ) ;
end;

procedure TACBrECFClass.LerTotaisFormaPagamento;
begin
  ErroAbstract('LerTotaisFormaPagamento');
end;

procedure TACBrECFClass.ProgramaFormaPagamento(var Descricao: String;
  PermiteVinculado: Boolean; Posicao : String);
begin
  ErroAbstract('ProgramaFormaPagamento');
end;

function TACBrECFClass.GetFormasPagamentos: TACBrECFFormasPagamento;
begin
  if not Assigned( fpFormasPagamentos ) then
    TACBrECF( fpOwner ).CarregaFormasPagamento ;

  result := fpFormasPagamentos ;
end;

function TACBrECFClass.AchaFPGDescricao(Descricao: String;
 BuscaExata : Boolean; IgnorarCase : Boolean ) : TACBrECFFormaPagamento;
 var Tamanho, A : Integer ;
     DescrECF : String ;
begin
  if not Assigned( fpFormasPagamentos ) then
     TACBrECF( fpOwner ).CarregaFormasPagamento ; ;

  result := nil ;
  with fpFormasPagamentos do
  begin
     Descricao := TrimRight( Descricao ) ;
     if IgnorarCase then
        Descricao := UpperCase( Descricao ) ;
     Tamanho := Length( Descricao ) ;
     For A := 0 to Count -1 do
     begin
        DescrECF := TrimRight( Objects[A].Descricao ) ;
        if not BuscaExata then
           DescrECF := LeftStr( DescrECF, Tamanho) ;
        if IgnorarCase then
           DescrECF := UpperCase(DescrECF) ;

        if DescrECF = Descricao then
        begin
           result := Objects[A] ;
           Break ;
        end ;
     end ;
  end ;
end;

function TACBrECFClass.AchaFPGIndice( Indice: String) :
   TACBrECFFormaPagamento;
var A : Integer ;
begin
  if not Assigned( fpFormasPagamentos ) then
     TACBrECF( fpOwner ).CarregaFormasPagamento ;

  result := nil ;
  with fpFormasPagamentos do
  begin
     For A := 0 to Count -1 do
     begin
        if Objects[A].Indice = Indice then
        begin
           result := Objects[A] ;
           Break ;
        end ;
     end ;
  end ;
end;

{---------------------------- Relat�rios Gerenciais ---------------------------}
procedure TACBrECFClass.CarregaRelatoriosGerenciais;
begin
  if Assigned( fpRelatoriosGerenciais ) then
     fpRelatoriosGerenciais.Free ;

  fpRelatoriosGerenciais := TACBrECFRelatoriosGerenciais.Create( true ) ;
end;

procedure TACBrECFClass.LerTotaisRelatoriosGerenciais ;
begin
  ErroAbstract('LerTotaisRelatoriosGerenciais');
end ;

procedure TACBrECFClass.ProgramaRelatorioGerencial(var Descricao: String;
  Posicao: String);
begin
  ErroAbstract('ProgramaRelat�riosGerenciais');
end;

function TACBrECFClass.GetRelatoriosGerenciais: TACBrECFRelatoriosGerenciais;
begin
  if not Assigned( fpRelatoriosGerenciais ) then
     TACBrECF( fpOwner ).CarregaRelatoriosGerenciais ;

  result := fpRelatoriosGerenciais ;
end;

function TACBrECFClass.AchaRGDescricao(Descricao: String;
  BuscaExata: Boolean; IgnorarCase : Boolean): TACBrECFRelatorioGerencial;
var Tamanho, A : Integer ;
     DescrECF : String ;
begin
  if not Assigned( fpRelatoriosGerenciais ) then
     TACBrECF( fpOwner ).CarregaRelatoriosGerenciais ;

  result := nil ;
  with fpRelatoriosGerenciais do
  begin
     Descricao := TrimRight( Descricao ) ;
     if IgnorarCase then
        Descricao := UpperCase( Descricao ) ;
     Tamanho := Length( Descricao ) ;
     For A := 0 to Count -1 do
     begin
        DescrECF := TrimRight( Objects[A].Descricao ) ;
        if not BuscaExata then
           DescrECF := LeftStr( DescrECF, Tamanho) ;
        if IgnorarCase then
           DescrECF := UpperCase(DescrECF) ;

        if DescrECF = Descricao then
        begin
           result := Objects[A] ;
           Break ;
        end ;
     end ;
  end ;
end;

function TACBrECFClass.AchaRGIndice(
  Indice: String): TACBrECFRelatorioGerencial;
var A : Integer ;
begin
  if not Assigned( fpRelatoriosGerenciais ) then
     TACBrECF( fpOwner ).CarregaRelatoriosGerenciais ;

  result := nil ;
  with fpRelatoriosGerenciais do
  begin
     For A := 0 to Count -1 do
     begin
        if Objects[A].Indice = Indice then
        begin
           result := Objects[A] ;
           Break ;
        end ;
     end ;
  end ;

end;


{------------------------- COMPROVANTES NAO FISCAIS ---------------------------}
procedure TACBrECFClass.CarregaComprovantesNaoFiscais;
begin
  if Assigned( fpComprovantesNaoFiscais ) then
     fpComprovantesNaoFiscais.Free ;

  fpComprovantesNaoFiscais := TACBrECFComprovantesNaoFiscais.Create( true ) ;
end;

procedure TACBrECFClass.LerTotaisComprovanteNaoFiscal;
begin
  ErroAbstract('LerTotaisComprovanteNaoFiscal');
end;

procedure TACBrECFClass.ProgramaComprovanteNaoFiscal(var Descricao: String;
  Tipo: String; Posicao : String);
begin
  ErroAbstract('ProgramaComprovanteNaoFiscal');
end;

function TACBrECFClass.GetComprovantesNaoFiscais: TACBrECFComprovantesNaoFiscais;
begin
  if not Assigned( fpComprovantesNaoFiscais ) then
     TACBrECF( fpOwner ).CarregaComprovantesNaoFiscais ;

  result := fpComprovantesNaoFiscais ;
end;

function TACBrECFClass.AchaCNFDescricao( Descricao: String;
       BuscaExata : Boolean; IgnorarCase : Boolean ): TACBrECFComprovanteNaoFiscal;
 var Tamanho, A : Integer ;
     DescrECF : String ;
begin
  if not Assigned( fpComprovantesNaoFiscais ) then
     TACBrECF( fpOwner ).CarregaComprovantesNaoFiscais ;

  result := nil ;
  with fpComprovantesNaoFiscais do
  begin
     Descricao := TrimRight( Descricao ) ;
     if IgnorarCase then
        Descricao := UpperCase( Descricao ) ;
     Tamanho := Length( Descricao ) ;
     For A := 0 to Count -1 do
     begin
        DescrECF := TrimRight( Objects[A].Descricao ) ;
        if not BuscaExata then
           DescrECF := LeftStr( DescrECF, Tamanho) ;
        if IgnorarCase then
           DescrECF := UpperCase(DescrECF) ;

        if DescrECF = Descricao then
        begin
           result := Objects[A] ;
           Break ;
        end ;
     end ;
  end ;
end;

function TACBrECFClass.AchaCNFIndice(
  Indice: String): TACBrECFComprovanteNaoFiscal;
var A : Integer ;
begin
  if not Assigned( fpComprovantesNaoFiscais ) then
     TACBrECF( fpOwner ).CarregaComprovantesNaoFiscais ;

  result := nil ;
  with fpComprovantesNaoFiscais do
  begin
     For A := 0 to Count -1 do
     begin
        if Objects[A].Indice = Indice then
        begin
           result := Objects[A] ;
           Break ;
        end ;
     end ;
  end ;
end;

function TACBrECFClass.AchaCNFFormaPagamento(
  CodFPG: String): TACBrECFComprovanteNaoFiscal;
var A : Integer ;
begin
  if not Assigned( fpComprovantesNaoFiscais ) then
     TACBrECF( fpOwner ).CarregaComprovantesNaoFiscais ;

  result := nil ;
  with fpComprovantesNaoFiscais do
  begin
     For A := 0 to Count -1 do
     begin
        if Objects[A].FormaPagamento = CodFPG then
        begin
           result := Objects[A] ;
           Break ;
        end ;
     end ;
  end ;
end;

{---------------------------- UNIDADES DE MEDIDA ------------------------------}
procedure TACBrECFClass.CarregaUnidadesMedida;
begin
  if Assigned( fpUnidadesMedida ) then
     fpUnidadesMedida.Free ;

  fpUnidadesMedida := TACBrECFUnidadesMedida.Create( true ) ;
end;

procedure TACBrECFClass.ProgramaUnidadeMedida(var Descricao: String);
begin
  ErroAbstract('ProgramaUnidadeMedida');
end;

function TACBrECFClass.GetUnidadesMedida: TACBrECFUnidadesMedida;
begin
  if not Assigned( fpUnidadesMedida ) then
     TACBrECF( fpOwner ).CarregaUnidadesMedida ;

  result := fpUnidadesMedida ;
end;

function TACBrECFClass.AchaUMDDescricao(
  Descricao: String): TACBrECFUnidadeMedida;
var A : Integer ;
begin
  if not Assigned( fpUnidadesMedida ) then
     TACBrECF( fpOwner ).CarregaUnidadesMedida ;

  result := nil ;
  with fpUnidadesMedida do
  begin
     Descricao := Trim(UpperCase( Descricao )) ;
     For A := 0 to Count -1 do
     begin
        if Trim(UpperCase( Objects[A].Descricao )) = Descricao then
        begin
           result := Objects[A] ;
           Break ;
        end ;
     end ;
  end ;
end;

function TACBrECFClass.AchaUMDIndice( Indice: String): TACBrECFUnidadeMedida;
var A : Integer ;
begin
  if not Assigned( fpUnidadesMedida ) then
     TACBrECF( fpOwner ).CarregaUnidadesMedida ;

  result := nil ;
  with fpUnidadesMedida do
  begin
     For A := 0 to Count -1 do
     begin
        if Objects[A].Indice = Indice then
        begin
           result := Objects[A] ;
           Break ;
        end ;
     end ;
  end ;
end;

{ ------------------------------ Cupom Vinculado -----------------------------}
procedure TACBrECFClass.CupomVinculado(COO, CodFormaPagto,
  CodComprovanteNaoFiscal: String; Valor: Double; Relatorio: TStrings;
  Vias: Integer);
Var wRetentar : Boolean ;
begin
  { Chama rotinas da classe Pai (fpOwner) para atualizar os Memos }
  TACBrECF(fpOwner).AbreCupomVinculado( COO, CodFormaPagto, CodComprovanteNaoFiscal, Valor) ;

  wRetentar := Retentar ;
  try
     Retentar    := false ;
     fsVias      := Vias ;
     fsRelatorio := Relatorio ;

     {$IFNDEF NOGUI}
       if not ExibeMensagem  then
          DoCupomVinculado
       else
        begin
          { Isso far� a procedure LeResposta nao pintar o FormMsgAguarde }
          fsFormMsgControla := false ;
          FormMsgDoProcedure( DoCupomVinculado, 0)
        end ;
     {$ELSE}
       DoCupomVinculado
     {$ENDIF}
  finally
     {$IFNDEF NOGUI}
       fsFormMsgControla := true ;
     {$ENDIF}
     Retentar := wRetentar ;
  end ;
end;

procedure TACBrECFClass.DoCupomVinculado;
begin
  ListaCupomVinculado( fsRelatorio, fsVias );
end;

procedure TACBrECFClass.AbreCupomVinculado(COO, CodFormaPagto,
  CodComprovanteNaoFiscal: String; Valor: Double);
begin
  ErroAbstract('AbreCupomVinculado');
end;

procedure TACBrECFClass.LinhaCupomVinculado(Linha: AnsiString);
begin
  ErroAbstract('LinhaCupomVinculado');
end;

procedure TACBrECFClass.SegundaViaVinculado;
begin
  ErroAbstract('SegundaViaVinculado');
end;

procedure TACBrECFClass.ReimpressaoVinculado;
begin
  ErroAbstract('Reimpress�oVinculado');
end;

procedure TACBrECFClass.ListaCupomVinculado( Relatorio: TStrings;
  Vias: Integer);
Var
  Imp   : Integer ;
  Texto : String ;
begin
  Imp := 0 ;

  while Imp < Vias do
  begin
     {$IFNDEF NOGUI}
       try
          Texto := Format(MsgRelatorio,['Cupom Vinculado',Imp+1 ]) ;
       except
          Texto := MsgRelatorio ;
       end ;

       FormMsgPinta( Texto );
     {$ENDIF}

     TACBrECF(fpOwner).LinhaCupomVinculado( Relatorio.Text ) ;

     Inc(Imp) ;
     if Imp < Vias then
     begin
        TACBrECF(fpOwner).PulaLinhas  ;
        TACBrECF(fpOwner).CortaPapel  ;
        PausarRelatorio( Imp ) ;
     end ;
  end ;

  {$IFNDEF NOGUI}
    FormMsgPinta( 'Fechando Cupom Vinculado' );
  {$ENDIF}
  TACBrECF(fpOwner).FechaRelatorio ;
end;

{ ------------------------------ Relatorio Gerencial -------------------------}
procedure TACBrECFClass.RelatorioGerencial(Relatorio: TStrings; Vias: Integer; Indice: Integer);
Var wMsgAguarde : String ;
    wRetentar : Boolean ;
begin
  { Chama rotinas da classe Pai (fpOwner) para atualizar os Memos }
  try
     TACBrECF(fpOwner).FechaRelatorio ; { Fecha se ficou algum aberto }
  Except
  end ;

  wMsgAguarde := MsgAguarde ;
  MsgAguarde  := cACBrECFAbrindoRelatorioGerencial ;
  try
     TACBrECF(fpOwner).AbreRelatorioGerencial(Indice) ;
  finally
     MsgAguarde := wMsgAguarde ;
  end ;

  wRetentar := Retentar ;
  try
     Retentar    := false ;
     fsVias      := Vias ;
     fsRelatorio := Relatorio ;
     fsIndiceRG  := Indice;

     {$IFNDEF NOGUI}
       if not ExibeMensagem  then
         DoRelatorioGerencial
       else
        begin
          { Isso far� a procedure LeResposta nao pintar o FormMsgAguarde }
          fsFormMsgControla := false ;
          FormMsgDoProcedure(DoRelatorioGerencial,0) ;
        end ;
     {$ELSE}
       DoRelatorioGerencial ;
     {$ENDIF}
  finally
     {$IFNDEF NOGUI}
       fsFormMsgControla := true ;
     {$ENDIF}
     Retentar := wRetentar ;
  end ;
end;

procedure TACBrECFClass.DoRelatorioGerencial;
begin
   ListaRelatorioGerencial( fsRelatorio, fsVias, fsIndiceRG )
end;

procedure TACBrECFClass.AbreRelatorioGerencial(Indice : Integer) ;
begin
  ErroAbstract('AbreRelatorioGerencial');
end;

procedure TACBrECFClass.LinhaRelatorioGerencial(Linha: AnsiString; IndiceBMP: Integer);
begin
  ErroAbstract('LinhaRelatorioGerencial');
end;

function TACBrECFClass.DecodificaTexto(Operacao: Char; Texto: String;
  var Resposta: String): Boolean;
begin
  Result := False ;
  ErroAbstract('DecodificaTexto');
end;

procedure TACBrECFClass.ListaRelatorioGerencial(Relatorio: TStrings;
  Vias: Integer; Indice: Integer);
Var
  Imp   : Integer ;
  Texto : String ;
begin
  Imp := 0 ;

  while Imp < Vias do
  begin
    try
      Texto := Format(MsgRelatorio,['Relat�rio Gerencial',Imp+1 ]) ;
    except
      Texto := MsgRelatorio ;
    end ;

    {$IFNDEF NOGUI}
      FormMsgPinta( Texto );
    {$ENDIF}

    TACBrECF(fpOwner).LinhaRelatorioGerencial( Relatorio.Text ) ;

    Inc(Imp) ;
    if Imp < Vias then
    begin
      TACBrECF(fpOwner).PulaLinhas ;
      TACBrECF(fpOwner).CortaPapel ;
      if Imp < 1 then
         Sleep( 200 ) ;

      PausarRelatorio( Imp ) ;
    end ;
  end ;

  {$IFNDEF NOGUI}
    FormMsgPinta( cACBrECFFechandoRelatorioGerencial );
  {$ENDIF}

  TACBrECF(fpOwner).FechaRelatorio ;
end;

{ ------------------------------ Pausar Relatorios -------------------------}

procedure TACBrECFClass.PausarRelatorio( Via : Integer );
Var Texto : String ;
    SecRest, SecAnt : Integer ;
    FimPausa : TDateTime ;
begin

  Try
     FimPausa := IncSecond( now, PausaRelatorio ) ;
     SecAnt := 0 ;
     {$IFNDEF NOGUI}
       fsFormMsgTeclaParaFechar := 13 ;
     {$ENDIF}

     while (now < FimPausa) do
     begin
       {$IFNDEF NOGUI}
         if not (fsFormMsgEstado = fmsProcessando) then
            Break ;

          SecRest := SecondsBetween(now,  FimPausa) ;
          if SecAnt <> SecRest then  { Verifica se mudou os segundos }
          begin
             SecAnt := SecRest ;

             try
                Texto := Format(MsgPausaRelatorio, [Via, SecRest]) ;
             except
                Texto := MsgPausaRelatorio ;
             end ;

             FormMsgPinta( Texto );
          end ;

          if fpDevice.ProcessMessages then
	          Application.ProcessMessages;
       {$ELSE}
          sleep(100) ;
       {$ENDIF}
     end ;
  finally
     {$IFNDEF NOGUI}
       fsFormMsgTeclaParaFechar := 0 ;
       fsFormMsgEstado := fmsProcessando ;
     {$ENDIF}
  end ;
end;





{$IFNDEF NOGUI}
  {$IFDEF MSWINDOWS}
    procedure TACBrECFClass.BlockInput( const Block, ClearTypeAhead : Boolean ) ;
    var
       Msg: TMsg;
    begin
      if not Assigned( xBlockInput ) then
         exit ;
         
      if ClearTypeAhead then
      begin
        try
           // Remove todas as Teclas do Buffer do Teclado //
           while PeekMessage(Msg, 0, WM_KEYFIRST, WM_KEYLAST, PM_REMOVE or PM_NOYIELD) do;
        except
        end ;
      end ;

      xBlockInput( Block ) ;
      fsUsandoBlockInput := Block ;
    end ;
  {$ENDIF}

  function TACBrECFClass.FormMsgDoProcedure(AProcedure: TACBrFormMsgProcedure;
    TeclaParaFechar: Word): Boolean;
  Var Timer : TTimer ;
      {$IFDEF VisualCLX}
      OldOnEvent : TEventEvent;
      OldCursor  : TCursor ;
      {$ENDIF}
  begin
    Result := true ;
    {$IFDEF VisualCLX}
    OldOnEvent := Application.OnEvent ;
    OldCursor  := Screen.Cursor ;
    {$ENDIF}

    if Assigned(fsFormMsg) then
       Raise EACBrECFErro.Create( ACBrStr(cACBrECFFormMsgDoProcedureException)) ;

    fsFormMsg  := TForm.create( Application ) ;

    try
       {$IFDEF VisualCLX}
       Application.OnEvent := FormMsgEvent ;
       {$ENDIF}
       fsFormMsgProcedureAExecutar := AProcedure ;
       fsFormMsgTeclaParaFechar    := TeclaParaFechar ;
       fsFormMsgEstado             := fmsProcessando ;

       fsFormMsg.KeyPreview   := true ;
       fsFormMsg.OnKeyPress   := FormMsgKeyPress ;
       fsFormMsg.OnCloseQuery := FormMsgCloseQuery ;
       fsFormMsg.Color        := fsFormMsgColor ;
       fsFormMsg.Font         := fsFormMsgFont ;
       fsFormMsg.BorderIcons  := [] ;
       fsFormMsg.BorderStyle  := {$IFDEF VisualCLX} fbsNone {$ELSE} bsNone {$ENDIF};
       fsFormMsg.Position     := poMainFormCenter ;
       fsFormMsg.FormStyle    := fsStayOnTop ;
       fsFormMsg.Width        := 0 ;   { Cria o form escondido }
       fsFormMsg.Height       := 0 ;
       fsFormMsgException     := '' ;
       {$IFDEF LINUX}
        {$IFNDEF FPC}
         fsFormMsg.OnShow     := FormShow ;
        {$ENDIF}
       {$ENDIF}

       if BloqueiaMouseTeclado then
        begin
          { Quando o Timer for ativado, a procedure em fsFormMsgProcedureAExecutar
            ser� executada... Ao fim da Procurede o fsFormMsg � fechado }
          { O objeto Timer ser� destruido no proprio evento FormMsgTimer }

          Timer := TTimer.Create(fsFormMsg);
          Timer.Enabled  := false ;
          Timer.OnTimer  := FormMsgTimer ;
          Timer.Interval := 3 ;
          Timer.Enabled  := True ;

          fsFormMsg.ShowModal ;
        end
       else
        begin
          fsFormMsg.Show ;
          FormMsgTimer(Self);
        end ;

       if fsFormMsgException <> '' then
          raise EACBrECFErro.Create( ACBrStr(fsFormMsgException) ) ;
    finally
       {$IFDEF VisualCLX}
       Application.OnEvent := OldOnEvent;
       Screen.Cursor       := OldCursor ;
       {$ENDIF}
       FreeAndNil(fsFormMsg) ;
    end
  end;

  procedure TACBrECFClass.FormMsgTimer(Sender: TObject);
  begin
    if Sender is TTimer then
    begin
       TTimer(Sender).Enabled := false ;
       FreeAndNil( Sender ) ;
    end ;

    try
       try
          if Assigned( fsFormMsgProcedureAExecutar ) then
             fsFormMsgProcedureAExecutar  ;
       finally
          if Assigned( fsOnMsgAguarde ) then
             fsOnMsgAguarde( '' ) ;

          if fsFormMsgEstado = fmsProcessando then
             fsFormMsgEstado := fmsConcluido
          else
             fsFormMsgEstado := fmsAbortado ;

          fsFormMsg.Close ;
          //{$IFNDEF COMPLIB_CLX}
          Application.BringToFront ;
          //{$ENDIF}
       end ;
    except
      { N�o dispra a exce��o dentro do Envento do Timer para a ordem da Pilha
        de Exce��es ficar correta }
      on E : Exception
      do begin
         fsFormMsgException := E.Message ;
      end ;
    end ;
  end;

  procedure TACBrECFClass.FormMsgKeyPress(Sender: TObject; var Key: Char);
  begin
    if (fsFormMsgTeclaParaFechar <> 0)  and
       (UpCase( Key ) = chr(fsFormMsgTeclaParaFechar)) and
       (fsFormMsgEstado <> fmsAbortado) then
       fsFormMsgEstado := fmsAbortado
    else
       Key := chr(0) ;
  end;

  procedure TACBrECFClass.FormMsgCloseQuery(Sender: TObject;
    var CanClose: Boolean);
  begin
    CanClose := (fsFormMsgEstado <> fmsProcessando) ;
  end;
  {$IFDEF VisualCLX}
    {$D-}
    procedure TACBrECFClass.FormMsgEvent(Sender: QObjectH; Event: QEventH;
      var Handled: Boolean);
    {$IFDEF QT3CLX}
    Var  EventType: QEventH;
    {$ELSE}
    Var  EventType: QEventType;
    {$ENDIF}
    begin
      {$IFDEF QT3CLX}
      EventType := Event;
      {$ELSE}
      EventType := QEvent_type(Event);
      {$ENDIF}

      {$IFDEF LINUX}
      { No Linux o Formulario pode ser Minimizado ou arrastado mesmo com um
        ShowModal sobrepondo a aplica��o... Portanto vamos esconder o cursor e n�o
        permitir que ele se mova }
         if Assigned(fsFormMsg) then
         begin
            {$IFDEF QT3CLX}
            if EventType.type_ in [QEvent_Type_Close, QEvent_Type_Hide, QEvent_Type_Quit,
                             QEvent_Type_ShowMinimized, QEvent_Type_WindowDeActivate,
                             QEvent_Type_MouseMove, QEvent_Type_MouseButtonPress,
                             QEvent_Type_MouseButtonRelease,
                             QEvent_Type_MouseButtonDblClick,
                             QEvent_Type_DragMove,
                             QEvent_Type_Leave, QEvent_Type_Enter] then
            {$ELSE}
            if EventType in [QEventType_Close, QEventType_Hide, QEventType_Quit,
                             QEventType_ShowMinimized, QEventType_WindowDeActivate,
                             QEventType_MouseMove, QEventType_MouseButtonPress,
                             QEventType_MouseButtonRelease,
                             QEventType_MouseButtonDblClick,
                             QEventType_DragMove,
                             QEventType_Leave, QEventType_Enter] then
            {$ENDIF}                 
            begin
               Handled := true ;
               fsFormMsg.SetFocus ;
            end ;

            if fsFormMsg.Visible then
            begin
               Mouse.CursorPos := Point(fsFormMsg.Left,fsFormMsg.Top) ;
               Screen.Cursor   := crNone ;
            end ;
         end ;
      {$ENDIF}

      {$IFDEF QT3CLX}
      if EventType.type_ = QEvent_Type_Close then
         Handled := true

      else if ((EventType.type_ in [QEvent_Type_KeyPress]) and (fsFormMsgTeclaParaFechar = 0)) or
               ( EventType.type_ = QEvent_Type_MouseButtonPress) then
         Handled := true ;
      {$ELSE}
      if EventType = QEventType_Close then
         Handled := true

      else if ((EventType in [QEventType_KeyPress]) and (fsFormMsgTeclaParaFechar = 0)) or
               ( EventType = QEventType_MouseButtonPress) then
         Handled := true ;
      {$ENDIF}
    end;
    {$D+}
  {$ENDIF}
  procedure TACBrECFClass.FormMsgPinta( Texto : String );
  Var H, W, X, Y : Integer ;
  begin
    if Assigned( fsOnMsgAguarde ) then
       fsOnMsgAguarde( Texto ) ;

    if not Assigned( fsFormMsg ) then
       exit ;

    if fsFormMsg.Visible and ExibeMensagem then
    begin
       fsFormMsg.BringToFront ;
       fsFormMsg.SetFocus ;

       with fsFormMsg.Canvas do      { Pintando <Texto> no Canvas do fpFormMsg }
       begin
          H := TextHeight(Texto) + 10 ;    { Calcula o tamanho do Texto }
          W := TextWidth (Texto) + 20 ;

          { Ajusta o Form para caber o Texto }
          if (abs(W - fsFormMsg.Width ) > 4) or
             (abs(H - fsFormMsg.Height) > 4) then
          begin
             fsFormMsg.Width  := W ;
             fsFormMsg.Height := H ;
             {$IFDEF FPC}
             fsFormMsg.Position := poDesktopCenter ;
             {$ELSE}
             fsFormMsg.Position := poMainFormCenter ;
             {$ENDIF}
          end ;

          Brush.Color := fsFormMsg.Color ;
          Font.Color  := fsFormMsg.Font.Color ;
          Pen.Color   := fsFormMsg.Font.Color ;
          Rectangle(fsFormMsg.ClientRect);
         {$IFDEF VisualCLX}
          X := 0 ;
          Y := 0 ;
          TextRect(fsFormMsg.ClientRect,X,Y, Texto, 36 ) ; { 36 = No Centro }
         {$ELSE}
          { Na VCL nao tem como centralizar no Form nem quebra de Linhas }
          Texto := StringReplace( Texto, #10, ' ', [rfReplaceAll,rfIgnoreCase] ) ;
          X := 10 ;
          Y := 5 ;
          TextRect(fsFormMsg.ClientRect,X,Y, Texto ) ;
         {$ENDIF}
       end ;
       if fpDevice.ProcessMessages then
	       Application.ProcessMessages;
    end ;
  end;

  function TACBrECFClass.FormMsgExibe : Boolean;
  begin
    Result := (ExibeMensagem or BloqueiaMouseTeclado) and
              (AguardaImpressao or ((TimeOut - TempoInicioMsg) > 1) ) and
              FormMsgControla and
              Application.ShowMainForm ;
  end;

  {$IFDEF LINUX}
   {$IFNDEF FPC}
    procedure TACBrECFClass.FormShow(Sender: TObject);
    begin
     {$IFDEF QT3CLX}
      fsFormMsg.Handle.grabKeyboard;
      fsFormMsg.Handle.releaseKeyboard;
     {$ELSE}
      QWidget_grabKeyboard(fsFormMsg.Handle);
      QWidget_releaseKeyboard(fsFormMsg.Handle);
     {$ENDIF}
    end;
   {$ENDIF}
  {$ENDIF}
{$ENDIF}

procedure TACBrECFClass.ProgramarBitmapPromocional(const AIndice: Integer;
  const APathArquivo: AnsiString; const AAlinhamento: TACBrAlinhamento);
begin
  ErroAbstract('ProgramarBitmapPromocional');
end;

function TACBrECFClass.PossuiTagCodBarra(const ATexto: String): Boolean;
var
  I: Integer;
begin
  for I := 12 to High(ARRAY_TAGS) do
  begin
    Result := pos(ARRAY_TAGS[I], ATexto) > 0;
    if Result then
      Exit;
  end;
end;

function TACBrECFClass.TraduzirTag(const ATag: AnsiString): AnsiString;
begin
  {*************************************************

    TAGS ACEITAS
    ============
      </linha_simples>    - ------------------...
      </linha_dupla>      - ==================...
      <e></e>             - Expandido
      <n></n>             - Negrito
      <s></s>             - Sublinhado
      <c></c>             - Condensado
      <i></i>             - It�lico
      <ad></ad>           - Alinhado a direita
      <ce></ce>           - centralizado

      <ean8></ean8>       - ean 8
      <ean13></ean13>     - ean 13
      <std></std>         - standart
      <inter></inter>     - interleave
      <code11></code11>   - code 11
      <code39></code39>   - code 39
      <code93></code93>   - code 93
      <code128></code128> - code 128
      <upca></upca>       - upca
      <codabar></codabar> - codabar
      <msi></msi>         - msi

  *************************************************}

  // retornar vazio para que n�o de problema nas
  // impressoras que n�o implementaram ainda

  if AnsiIndexText( ATag, ARRAY_TAGS) < 0 then  // N�o � uma TAG conhecida ?
     Result := ATag
  else
     Result := '' ;
end;

function TACBrECFClass.TraduzirTagBloco(const ATag, Conteudo : AnsiString
   ) : AnsiString ;
var
   LowerTag : String ;
begin
  LowerTag := LowerCase( ATag );

  Result := TraduzirTag( LowerTag ) + Conteudo +
            TraduzirTag( '</'+copy(LowerTag,2,Length(LowerTag)) );

end ;

function TACBrECFClass.CodificarPaginaDeCodigoECF(ATexto: String): AnsiString;
begin
  if fpPaginaDeCodigo > 0 then
     Result := TranslateString( ACBrStrToAnsi( ATexto ), fpPaginaDeCodigo )
  else
     Result := TiraAcentos( ATexto );
end ;

function TACBrECFClass.DecodificarPaginaDeCodigoECF(ATexto : AnsiString
   ) : String ;
begin
  if fpPaginaDeCodigo > 0 then
     Result := ACBrStr( TranslateString( ATexto, 0, fpPaginaDeCodigo ) )
  else
     Result := ACBrStr( ATexto ) ;
end ;

procedure TACBrECFClass.PafMF_GerarCAT52(const DataInicial,
  DataFinal: TDateTime; const DirArquivos: String);
begin
  Self.ArquivoMFD_DLL(DataInicial, DataFinal, DirArquivos, [docTodos], finNFPTDM);
end;


{ TACBrECFDadosRZ }

procedure TACBrECFDadosRZ.SetDataDoMovimento(AValue: TDateTime);
begin
   if fsDataDoMovimento=AValue then Exit;
   fsDataDoMovimento := DateOf( AValue );
end;

constructor TACBrECFDadosRZ.Create;
begin
   fsTotalizadoresNaoFiscais := TACBrECFComprovantesNaoFiscais.Create;
   fsRelatorioGerencial      := TACBrECFRelatoriosGerenciais.Create;
   fsMeiosDePagamento        := TACBrECFFormasPagamento.Create;
   fsTodasAliquotas          := TACBrECFAliquotas.Create;
   fsICMS                    := TACBrECFAliquotas.Create(False);
   fsISSQN                   := TACBrECFAliquotas.Create(False);
   
   Clear ;
end;

procedure TACBrECFDadosRZ.Clear;
begin
   fsTotalizadoresNaoFiscais.Clear ;
   fsRelatorioGerencial.Clear ;
   fsMeiosDePagamento.Clear ;
   fsICMS.Clear ;
   fsISSQN.Clear ;
   fsTodasAliquotas.Clear;

   fsCOO                         := -1 ;
   fsCFD                         := -1 ;
   fsCancelamentoISSQN           := -1 ;
   fsGNFC                        := -1 ;
   fsCRO                         := -1 ;
   fsValorVendaBruta             := -1 ;
   fsAcrescimoICMS               := -1 ;
   fsDescontoICMS                := -1 ;
   fsNaoTributadoICMS            := -1 ;
   fsCRZ                         := -1 ;
   fsGRG                         := -1 ;
   fsValorGrandeTotal            := -1 ;
   fsAcrescimoISSQN              := -1 ;
   fsNaoTributadoISSQN           := -1 ;
   fsIsentoICMS                  := -1 ;
   fsSubstituicaoTributariaICMS  := -1 ;
   fsTotalOperacaoNaoFiscal      := -1 ;
   fsDescontoISSQN               := -1 ;
   fsCancelamentoICMS            := -1 ;
   fsGNF                         := -1 ;
   fsIsentoISSQN                 := -1 ;
   fsSubstituicaoTributariaISSQN := -1 ;
   fsVendaLiquida                := -1 ;
   fsCFC                         := -1 ;
   fsCCF                         := -1 ;
   fsTotalISSQN                  := -1 ;
   fsTotalICMS                   := -1 ;
   fsCDC                         := -1 ;
   fsCCDC                        := -1 ;
   fsNCN                         := -1 ;
   fsCancelamentoOPNF            := -1 ;
   fsAcrescimoOPNF               := -1 ;
   fsDescontoOPNF                := -1 ;
   fsTotalTroco                  := -1;
   fsDataDoMovimento             := 0 ;
   fsDataDaImpressora            := 0 ;
   fsNumeroCOOInicial            := '' ;
   fsNumeroDoECF                 := '' ;
   fsNumeroDeSerie               := '' ;
   fsNumeroDeSerieMFD            := '' ;
   fsNumeroDaLoja                := '' ;
end ;

procedure TACBrECFDadosRZ.CalculaValoresVirtuais;
Var
  I : Integer ;
  V: Double;
begin
  // Computando Total de Opera��es n�o fiscais //
  if (fsTotalOperacaoNaoFiscal < 0) then
  begin
    fsTotalOperacaoNaoFiscal := IfThen(fsTotalizadoresNaoFiscais.Count > 0, 0, -1) ;

    For I := 0 to fsTotalizadoresNaoFiscais.Count-1 do
      fsTotalOperacaoNaoFiscal := fsTotalOperacaoNaoFiscal + fsTotalizadoresNaoFiscais[I].Total;
  end;

  // Computando Total de ICMS //
  if (fsTotalICMS < 0) then
  begin
    fsTotalICMS := IfThen(fsICMS.Count > 0, 0, -1) ;

    For I := 0 to fsICMS.Count-1 do
      fsTotalICMS := fsTotalICMS + fsICMS[I].Total;
  end;

  // Computando Total de ISSQN; //
  if (fsTotalISSQN < 0) then
  begin
    fsTotalISSQN := IfThen(fsISSQN.Count > 0, 0, -1) ;

    For I := 0 to fsISSQN.Count-1 do
      fsTotalISSQN := fsTotalISSQN + fsISSQN[I].Total;
  end;

  // Computando a Venda Bruta //
  if (fsValorVendaBruta < 0) then
  begin
    V := 0 ;

    // ICMS //
    if (IsentoICMS > -1) then
       V := V + IsentoICMS;

    if (NaoTributadoICMS > -1) then
       V := V + NaoTributadoICMS;

    if (SubstituicaoTributariaICMS > -1) then
       V := V + SubstituicaoTributariaICMS;

    if (DescontoICMS > -1) then
       V := V + DescontoICMS;

    if (CancelamentoICMS > -1) then
       V := V + CancelamentoICMS;

    // ISSQN //
    if (IsentoISSQN > -1) then
       V := V + IsentoISSQN;

    if (NaoTributadoISSQN > -1) then
       V := V + NaoTributadoISSQN;

    if (SubstituicaoTributariaISSQN > -1) then
       V := V + SubstituicaoTributariaISSQN;

    if (DescontoISSQN > -1) then
       V := V + DescontoISSQN;

    if (CancelamentoISSQN > -1) then
       V := V + CancelamentoISSQN;

{
    // OPNF //
    if (AcrescimoOPNF > -1) then
       V := V + AcrescimoOPNF;

    if (DescontoOPNF > -1) then
       V := V + DescontoOPNF;

    if (CancelamentoOPNF > -1) then             // Entra ?
       V := V + CancelamentoOPNF;
}
    // Aliquotas //
    For I := 0 to TodasAliquotas.Count-1 do
       V := V + TodasAliquotas[I].Total;

    if V > 0 then
       fsValorVendaBruta := V;
  end;

  // Computando a Venda L�quida //
  if (fsVendaLiquida < 0) and (ValorVendaBruta > -1) then
  begin
    fsVendaLiquida := ValorVendaBruta ;

    if (CancelamentoICMS > -1) then
      fsVendaLiquida := fsVendaLiquida - CancelamentoICMS ;

    if (DescontoICMS > -1) then
      fsVendaLiquida := fsVendaLiquida - DescontoICMS ;

    if (TotalISSQN > -1) then
      fsVendaLiquida := fsVendaLiquida - TotalISSQN;

    if (CancelamentoISSQN > -1) then
      fsVendaLiquida := fsVendaLiquida - CancelamentoISSQN;

    if (DescontoISSQN > -1) then
      fsVendaLiquida := fsVendaLiquida - DescontoISSQN;

    if (SubstituicaoTributariaISSQN > -1) then
      fsVendaLiquida := fsVendaLiquida - SubstituicaoTributariaISSQN ;

    if (NaoTributadoISSQN > -1) then
      fsVendaLiquida := fsVendaLiquida - NaoTributadoISSQN ;

    if (IsentoISSQN > -1) then
      fsVendaLiquida := fsVendaLiquida - IsentoISSQN ;
  end;
end;

function TACBrECFDadosRZ.MontaDadosReducaoZ: AnsiString;
Var
  I: Integer ;
  S: String;
begin
  Result := '[ECF]' + sLineBreak ;

  // Apenas grava no .INI os valores que foram realmente preenchidos pelo retorno do ECF //
  if DataDaImpressora > 0 then
     Result := Result + 'DataECF = ' + FormatDateTime('dd/mm/yy', DataDaImpressora) + sLineBreak ;
  if DataDoMovimento > 0 then
     Result := Result + 'DataMovimento = ' + FormatDateTime('dd/mm/yy', DataDoMovimento) + sLineBreak ;
  if NumeroDeSerie <> '' then
     Result := Result + 'NumSerie = '      + NumeroDeSerie               + sLineBreak ;
  if NumeroDeSerieMFD <> '' then
     Result := Result + 'NumSerieMFD = '   + NumeroDeSerieMFD            + sLineBreak ;
  if NumeroDoECF <> '' then
     Result := Result + 'NumECF = '        + NumeroDoECF                 + sLineBreak ;
  if NumeroDaLoja <> '' then
     Result := Result + 'NumLoja = '       + NumeroDaLoja                + sLineBreak ;
  if NumeroCOOInicial <> '' then
     Result := Result + 'NumCOOInicial = ' + NumeroCOOInicial            + sLineBreak ;
  if COO > -1 then
     Result := Result + 'NumCOO = '        + FormatFloat('000000', COO)  + sLineBreak ;
  if CRZ > -1 then
     Result := Result + 'NumCRZ = '        + FormatFloat('000000', CRZ)  + sLineBreak ;
  if CRO > -1 then
     Result := Result + 'NumCRO = '        + FormatFloat('000000', CRO)  + sLineBreak ;
  if GNF > -1 then
     Result := Result + 'NumGNF = '        + FormatFloat('000000', GNF)  + sLineBreak ;
  if CCF > -1 then
     Result := Result + 'NumCCF = '        + FormatFloat('000000', CCF)  + sLineBreak ;
  if CFD > -1 then
     Result := Result + 'NumCFD = '        + FormatFloat('000000', CFD)  + sLineBreak ;
  if CDC > -1 then
     Result := Result + 'NumCDC = '        + FormatFloat('000000', CDC)  + sLineBreak ;
  if GRG > -1 then
     Result := Result + 'NumGRG = '        + FormatFloat('000000', GRG)  + sLineBreak ;
  if GNFC > -1 then
  begin
     Result := Result + 'NumNFC = '        + FormatFloat('000000', GNFC) + sLineBreak ;
     Result := Result + 'NumGNFC = '       + FormatFloat('000000', GNFC) + sLineBreak ;
  end;
  if CFC > -1 then
     Result := Result + 'NumCFC = '        + FormatFloat('000000', CFC)  + sLineBreak ;
  if NCN > -1 then
     Result := Result + 'NumNCN = '        + FormatFloat('000000', NCN)  + sLineBreak ;
  if CCDC > -1 then
     Result := Result + 'NumCCDC = '       + FormatFloat('000000', CCDC) + sLineBreak ;

  Result := Result + sLineBreak + '[Totalizadores]' + sLineBreak ;

  if ValorVendaBruta > -1 then
     Result := Result + 'VendaBruta = '              + FormatFloat('0.00',ValorVendaBruta)        + sLineBreak ;
  if VendaLiquida > -1 then
     Result := Result + 'VendaLiquida = '            + FormatFloat('0.00',VendaLiquida)           + sLineBreak ;
  if ValorGrandeTotal > -1 then
     Result := Result + 'GrandeTotal = '             + FormatFloat('0.00',ValorGrandeTotal)       + sLineBreak ;
  if DescontoICMS > -1 then
     Result := Result + 'TotalDescontos = '          + FormatFloat('0.00',DescontoICMS)           + sLineBreak ;
  if CancelamentoICMS > -1 then
     Result := Result + 'TotalCancelamentos = '      + FormatFloat('0.00',CancelamentoICMS)       + sLineBreak ;
  if AcrescimoICMS > -1 then
     Result := Result + 'TotalAcrescimos = '         + FormatFloat('0.00',AcrescimoICMS)          + sLineBreak ;
  if DescontoISSQN > -1 then
     Result := Result + 'TotalDescontosISSQN = '     + FormatFloat('0.00',DescontoISSQN)          + sLineBreak ;
  if CancelamentoISSQN > -1 then
     Result := Result + 'TotalCancelamentosISSQN = ' + FormatFloat('0.00',CancelamentoISSQN)      + sLineBreak ;
  if AcrescimoISSQN > -1 then
     Result := Result + 'TotalAcrescimosISSQN = '    + FormatFloat('0.00',AcrescimoISSQN)         + sLineBreak ;
  if TotalOperacaoNaoFiscal > -1 then
     Result := Result + 'TotalNaoFiscal = '          + FormatFloat('0.00',TotalOperacaoNaoFiscal) + sLineBreak ;
  if DescontoOPNF > -1 then
     Result := Result + 'TotalDescontosOPNF = '      + FormatFloat('0.00',DescontoOPNF)           + sLineBreak ;
  if CancelamentoOPNF > -1 then
     Result := Result + 'TotalCancelamentosOPNF = '  + FormatFloat('0.00',CancelamentoOPNF)       + sLineBreak ;
  if AcrescimoOPNF > -1 then
     Result := Result + 'TotalAcrescimosOPNF = '     + FormatFloat('0.00',AcrescimoOPNF)          + sLineBreak ;
  if TotalTroco > -1 then
     Result := Result + 'TotalTroco = '              + FormatFloat('0.00',TotalTroco)             + sLineBreak ;

  if TodasAliquotas.Count > 0 then
     Result := Result + sLineBreak + '[Aliquotas]' + sLineBreak ;

  For I := 0 to TodasAliquotas.Count-1 do
  begin
     Result := Result +
               FormatFloat('00', I+1 ) +
               TodasAliquotas[I].Tipo +
               IntToStrZero(Round(TodasAliquotas[I].Aliquota*100),4) + ' = ' +
               FormatFloat('0.00',TodasAliquotas[I].Total) + sLineBreak ;
  end ;

  Result := Result + sLineBreak + '[OutrasICMS]' + sLineBreak ;

  if TotalICMS > -1 then
     Result := Result + 'TotalICMS = '                        + FormatFloat('0.00',TotalICMS)                   + sLineBreak ;
  if TotalISSQN > -1 then
     Result := Result + 'TotalISSQN = '                       + FormatFloat('0.00',TotalISSQN)                  + sLineBreak ;
  if SubstituicaoTributariaICMS > -1 then
     Result := Result + 'TotalSubstituicaoTributaria = '      + FormatFloat('0.00',SubstituicaoTributariaICMS)  + sLineBreak ;
  if NaoTributadoICMS > -1 then
     Result := Result + 'TotalNaoTributado = '                + FormatFloat('0.00',NaoTributadoICMS)            + sLineBreak ;
  if IsentoICMS > -1 then
     Result := Result + 'TotalIsencao = '                     + FormatFloat('0.00',IsentoICMS)                  + sLineBreak ;
  if SubstituicaoTributariaISSQN > -1 then
     Result := Result + 'TotalSubstituicaoTributariaISSQN = ' + FormatFloat('0.00',SubstituicaoTributariaISSQN) + sLineBreak ;
  if NaoTributadoISSQN > -1 then
     Result := Result + 'TotalNaoTributadoISSQN = '           + FormatFloat('0.00',NaoTributadoISSQN)           + sLineBreak ;
  if IsentoISSQN > -1 then
     Result := Result + 'TotalIsencaoISSQN = '                + FormatFloat('0.00',IsentoISSQN)                 + sLineBreak ;

  if TotalizadoresNaoFiscais.Count > 0 then
     Result := Result + sLineBreak + '[NaoFiscais]' + sLineBreak ;
  S := '';
  For I := 0 to TotalizadoresNaoFiscais.Count-1 do
  begin
     Result := Result + padL(TotalizadoresNaoFiscais[I].Indice,2) + '_' +
                        TotalizadoresNaoFiscais[I].Descricao + ' = ' +
                        FormatFloat('0.00',TotalizadoresNaoFiscais[I].Total) + sLineBreak ;
     S := S + 'CON_' + TotalizadoresNaoFiscais[I].Descricao +' = '+
          FormatFloat('0000', TotalizadoresNaoFiscais[I].Contador) + sLineBreak ;
  end;
  Result := Result + S + sLineBreak ;

  if RelatorioGerencial.Count > 0 then
     Result := Result + sLineBreak + '[RelatoriosGerenciais]' + sLineBreak ;
  For I := 0 to RelatorioGerencial.Count-1 do
  begin
     Result := Result + padL(RelatorioGerencial[I].Indice,2) + '_' +
                        RelatorioGerencial[I].Descricao +' = '+
                        FormatFloat('0000', RelatorioGerencial[I].Contador) + sLineBreak ;
  end ;

  if MeiosDePagamento.Count > 0 then
     Result := Result + sLineBreak + '[MeiosDePagamento]' + sLineBreak ;
  For I := 0 to MeiosDePagamento.Count-1 do
  begin
     Result := Result + padL(MeiosDePagamento[I].Indice,2) + '_' +
                        MeiosDePagamento[I].Descricao + ' = ' +
                        FormatFloat('0.00',MeiosDePagamento[I].Total) + sLineBreak ;
  end;
end;

procedure TACBrECFDadosRZ.AdicionaAliquota(AliqZ: TACBrECFAliquota);
begin
  fsTodasAliquotas.Add( AliqZ );

  if AliqZ.Tipo = 'S' then
    fsISSQN.Add( AliqZ )
  else
    fsICMS.Add( AliqZ );
end;

destructor TACBrECFDadosRZ.Destroy;
begin
   fsTotalizadoresNaoFiscais.Free;
   fsRelatorioGerencial.Free;
   fsMeiosDePagamento.Free;
   fsICMS.Free;
   fsISSQN.Free;
   fsTodasAliquotas.Free;

   inherited Destroy ;
end;

{ TACBrECFRodape }

procedure TACBrECFRodape.SetMD5(AValue : String) ;
Var
  P : Integer ;
  UpValue : String ;
begin
  if fsMD5 = AValue then Exit ;

  // Removendo o pre-fixo "MD5", "MD5:" e "MD-5:"
  UpValue := UpperCase(AValue);
  P       := 1 ;
  if LeftStr(UpValue,5) = 'MD-5:' then
     P := 6
  else if LeftStr(UpValue,4) = 'MD5:' then
     P := 5
  else if LeftStr(UpValue,3) = 'MD5' then
     P := 4 ;

  fsMD5 := copy(AValue,P,Length(AValue));
end ;

constructor TACBrECFRodape.Create;
begin
  fsMD5        := EmptyStr;
  fsCupomMania := False;
  fsMinasLegal := False;
  fsParaibaLegal := False;

  fsNotaLegalDF := TACBrECFRodapeNotaLegalDF.Create;
  fsNotaLegalDF.Imprimir := False;
  fsNotaLegalDF.fsProgramaDeCredito := False;

  fsRestaurante := TACBrECFRodapeRestaurante.Create;
  fsRestaurante.Imprimir := False;

  Self.Clear;
end;

destructor TACBrECFRodape.Destroy;
begin
  FreeAndNil(fsNotaLegalDF);
  FreeAndNil(fsRestaurante);
  inherited;
end;

procedure TACBrECFRodape.Clear;
begin
  fsDav        := EmptyStr;
  fsDavOs      := EmptyStr;
  fsPreVenda   := EmptyStr;

  fsRestaurante.Imprimir:= False;
  fsRestaurante.CER    := 0;
  fsRestaurante.COO    := 0;
  fsRestaurante.ECF    := 0;

  // restante dos dados do nota legal DF n�o deve limpar
  fsNotaLegalDF.fsValorICMS := 0.00;
  fsNotaLegalDF.fsValorISS  := 0.00;
end;

end.

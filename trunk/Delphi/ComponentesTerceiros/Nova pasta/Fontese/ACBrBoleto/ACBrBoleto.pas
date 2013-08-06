{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2009 Juliana Rodrigues Prado e              }
{                                       Daniel Simoes de Almeida               }
{                                                                              }
{ Colaboradores nesse arquivo:  Andr� Ferreira de Moraes                       }
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

{******************************************************************************
|* Historico
|*
|* 02/10/2009: Andr� Ferreira de Moraes,  Daniel Sim�es de Almeida
|*  - Esbo�o das Classes
|*
|* 01/04/2010: Juliana Rodrigues Prado Tamizou,  Daniel Sim�es de Almeida
|*  - Primeira Versao ACBrBoleto
|*  Componente desenvolvido usando como base os projetos GBBoleto, RLBoleto,
|*  FreeBoleto, OpenBoleto, JFMBoleto e outras dicas encontradas na internet
******************************************************************************}
{$I ACBr.inc}

unit ACBrBoleto;

interface
uses ACBrBase,  {Units da ACBr}
     {$IFDEF FPC}
       LResources,
     {$ENDIF}
     SysUtils, ACBrValidador,
     {$IFDEF COMPILER6_UP} Types, {$ELSE} Windows, {$ENDIF}
     smtpsend, ssl_openssl, mimemess, mimepart, // units para enviar email
     Graphics, Contnrs, Classes;

const
  CACBrBoleto_Versao = '0.0.68a' ;

type
  TACBrTipoCobranca =
   (cobNenhum,
    cobBancoDoBrasil,
    cobSantander,
    cobCaixaEconomica,
    cobCaixaSicob,
    cobBradesco,
    cobItau,
    cobBancoMercantil,
    cobSicred,
    cobBancoob,
    cobBanrisul,
    cobBanestes,
    cobHSBC,
    cobBancoDoNordeste,
    cobBRB
    );

  TACBrTitulo = class;
  TACBrBoletoFCClass = class;
  TACBrCedente = class;
  TACBrBanco  = class;
  TACBrBoleto = class;

  TACBrLayoutRemessa = (c400, c240);

  {Tipos de ocorr�ncias permitidas no arquivos remessa / retorno}
  TACBrTipoOcorrencia =
  (
    {Ocorr�ncias para arquivo remessa}
    toRemessaRegistrar,
    toRemessaBaixar,
    toRemessaDebitarEmConta,
    toRemessaConcederAbatimento,
    toRemessaCancelarAbatimento,
    toRemessaConcederDesconto,
    toRemessaCancelarDesconto,
    toRemessaAlterarVencimento,
    toRemessaProtestar,
    toRemessaSustarProtesto,
    toRemessaCancelarInstrucaoProtestoBaixa,
    toRemessaCancelarInstrucaoProtesto,
    toRemessaDispensarJuros,
    toRemessaAlterarNomeEnderecoSacado,
    toRemessaAlterarNumeroControle,
    toRemessaOutrasOcorrencias,
    toRemessaAlterarControleParticipante,
    toRemessaAlterarSeuNumero,
    toRemessaTransfCessaoCreditoIDProd10,
    toRemessaTransferenciaCarteira,
    toRemessaDevTransferenciaCarteira,
    toRemessaDesagendarDebitoAutomatico,
    toRemessaAcertarRateioCredito,
    toRemessaCancelarRateioCredito,
    toRemessaAlterarUsoEmpresa,
    toRemessaNaoProtestar,
    toRemessaProtestoFinsFalimentares,
    toRemessaBaixaporPagtoDiretoCedente,
    toRemessaCancelarInstrucao,
    toRemessaAlterarVencSustarProtesto,
    toRemessaCedenteDiscordaSacado,
    toRemessaCedenteSolicitaDispensaJuros,
    toRemessaOutrasAlteracoes,
    toRemessaAlterarModalidade,

    {Ocorr�ncias para arquivo retorno}
    toRetornoRegistroConfirmado,
    toRetornoTransferenciaCarteira,
    toRetornoRegistroRecusado,
    toRetornoComandoRecusado,
    toRetornoLiquidado,
    toRetornoLiquidadoEmCartorio,
    toRetornoLiquidadoParcialmente,
    toRetornoLiquidadoSaldoRestante,
    toRetornoLiquidadoSemRegistro,
    toRetornoLiquidadoPorConta,
    toRetornoLiquidadoAposBaixaOuNaoRegistro,
    toRetornoBaixaRejeitada,
    toRetornoBaixaSolicitada,
    toRetornoBaixado,
    toRetornoBaixaAutomatica,
    toRetornoBaixadoViaArquivo,
    toRetornoBaixadoInstAgencia,
    toRetornoBaixadoPorDevolucao,
    toRetornoBaixadoFrancoPagamento,
    toRetornoBaixaPorProtesto,
    toRetornoBaixaSimples,
    toRetornoBaixaPorTerSidoLiquidado,
    toRetornoBaixaOuLiquidacaoEstornada,
    toRetornoBaixaTransferenciaParaDesconto,
    toRetornoBaixaCreditoCCAtravesSispag,
    toRetornoBaixaCreditoCCAtravesSispagSemTituloCorresp,
    toRetornoTituloEmSer,
    toRetornoTituloNaoExiste,
    toRetornoTituloPagoEmCheque,
    toRetornoTituloPagamentoCancelado,
    toRetornoTituloJaBaixado,
    toRetornoTituloSustadoJudicialmente,
    toRetornoRecebimentoInstrucaoBaixar,
    toRetornoRecebimentoInstrucaoConcederAbatimento,
    toRetornoRecebimentoInstrucaoCancelarAbatimento,
    toRetornoRecebimentoInstrucaoConcederDesconto,
    toRetornoRecebimentoInstrucaoCancelarDesconto,
    toRetornoRecebimentoInstrucaoAlterarDados,
    toRetornoRecebimentoInstrucaoAlterarVencimento,
    toRetornoRecebimentoInstrucaoProtestar,
    toRetornoRecebimentoInstrucaoSustarProtesto,
    toRetornoRecebimentoInstrucaoNaoProtestar,
    toRetornoRecebimentoInstrucaoAlterarNomeSacado,
    toRetornoRecebimentoInstrucaoAlterarEnderecoSacado,
    toRetornoRecebimentoInstrucaoAlterarTipoCobranca,
    toRetornoRecebimentoInstrucaoDispensarJuros,
    toRetornoAbatimentoConcedido,
    toRetornoAbatimentoCancelado,
    toRetornoDescontoConcedido,
    toRetornoDescontoCancelado,
    toRetornoDadosAlterados,
    toRetornoVencimentoAlterado,
    toRetornoAlteracaoDadosNovaEntrada,
    toRetornoAlteracaoDadosBaixa,
    toRetornoAlteracaoDadosRejeitados,
    toRetornoAlteracaoOutrosDadosRejeitada,
    toRetornoProtestado,
    toRetornoProtestoSustado,
    toRetornoProtestoOuSustacaoEstornado,
    toRetornoInstrucaoProtestoRejeitadaSustadaOuPendente,
    toRetornoInstrucaoRejeitada,
    toRetornoInstrucaoCancelada,
    toRetornoDebitoEmConta,
    toRetornoNomeSacadoAlterado,
    toRetornoEnderecoSacadoAlterado,
    toRetornoEncaminhadoACartorio,
    toRetornoEntradaEmCartorio,
    toRetornoRetiradoDeCartorio,
    toRetornoJurosDispensados,
    toRetornoDespesasProtesto,
    toRetornoDespesasSustacaoProtesto,
    toRetornoCustasSustacao,
    toRetornoCustasProtesto,
    toRetornoCustasCartorioDistribuidor,
    toRetornoCustasEdital,
    toRetornoCustasSustacaoJudicial,
    toRetornoCustasIrregularidade,
    toRetornoAcertoDepositaria,
    toRetornoAcertoControleParticipante,
    toRetornoAcertoDadosRateioCredito,
    toRetornoEntradaRejeitaCEPIrregular,
    toRetornoEntradaConfirmadaRateioCredito,
    toRetornoEntradaRegistradaAguardandoAvaliacao,
    toRetornoEntradaRejeitadaCarne,
    toRetornoDesagendamentoDebitoAutomatico,
    toRetornoEstornoPagamento,
    toRetornoSustadoJudicial,
    toRetornoManutencaoTituloVencido,
    toRetornoTipoCobrancaAlterado,
    toRetornoCancelamentoDadosRateio,
    toRetornoOutrasOcorrencias,
    toRetornoOcorrenciasDoSacado,
    toRetornoCobrancaContratual,
    toRetornoTarifaExtratoPosicao,
    toRetornoTarifaDeRelacaoDasLiquidacoes,
    toRetornoTarifaDeManutencaoDeTitulosVencidos,
    toRetornoTarifaEmissaoBoletoEnvioDuplicata,
    toRetornoTarifaInstrucao,
    toRetornoTarifaOcorrencias,
    toRetornoTarifaAvisoCobranca,
    toRetornoTarifaMensalEmissaoBoletoEnvioDuplicata,
    toRetornoTarifaMensalRefEntradasBancosCorrespCarteira,
    toRetornoTarifaMensalBaixasCarteira,
    toRetornoTarifaMensalBaixasBancosCorrespCarteira,
    toRetornoTarifaMensalLiquidacoesCarteira,
    toRetornoTarifaMensalLiquidacoesBancosCorrespCarteira,
    toRetornoTarifaEmissaoAvisoMovimentacaoTitulos,
    toRetornoDebitoTarifas,
    toRetornoDebitoCustasAntecipadas,
    toRetornoDebitoMensalTarifasExtradoPosicao,
    toRetornoDebitoMensalTarifasOutrasInstrucoes,
    toRetornoDebitoMensalTarifasManutencaoTitulosVencidos,
    toRetornoDebitoMensalTarifasOutrasOcorrencias,
    toRetornoDebitoMensalTarifasProtestos,
    toRetornoDebitoMensalTarifasSustacaoProtestos,
    toRetornoDebitoMensalTarifaAvisoMovimentacaoTitulos,
    toRetornoChequeDevolvido,
    toRetornoChequeCompensado,
    toRetornoConfirmacaoEntradaCobrancaSimples,
    toRetornoAlegacaoDoSacado
  );

  {TACBrOcorrencia}
  TACBrOcorrencia = class
  private
     fTipo: TACBrTipoOcorrencia;
     fpAOwner: TACBrTitulo;
     function GetCodigoBanco: String;
     function GetDescricao: String;
  public
     constructor Create(AOwner: TACBrTitulo);
     property Tipo: TACBrTipoOcorrencia read fTipo write fTipo;
     property Descricao  : String  read GetDescricao;
     property CodigoBanco: String  read GetCodigoBanco;
  end;

  { TACBrBancoClass }

  TACBrBancoClass = class
  private
     procedure ErroAbstract( NomeProcedure : String ) ;
  protected
    fpDigito: Integer;
    fpNome:   String;
    fpNumero: Integer;
    fpModulo: TACBrCalcDigito;
    fpTamanhoAgencia: Integer;
    fpTamanhoCarteira: Integer;
    fpTamanhoConta: Integer;
    fpAOwner: TACBrBanco;
    fpTamanhoMaximoNossoNum: Integer;
    fpOrientacoesBanco: TStringList;
    function CalcularFatorVencimento(const DataVencimento: TDateTime): String; virtual;
    function CalcularDigitoCodigoBarras(const CodigoBarras: String): String; virtual;
  public
    Constructor create(AOwner: TACBrBanco);
    Destructor Destroy; override ;

    property ACBrBanco : TACBrBanco      read fpAOwner;
    property Numero    : Integer         read fpNumero;
    property Digito    : Integer         read fpDigito;
    property Nome      : String          read fpNome;
    Property Modulo    : TACBrCalcDigito read fpModulo;
    property TamanhoMaximoNossoNum: Integer    read fpTamanhoMaximoNossoNum;
    property TamanhoAgencia  :Integer read fpTamanhoAgencia;
    property TamanhoConta    :Integer read fpTamanhoConta;
    property TamanhoCarteira :Integer read fpTamanhoCarteira;
    property OrientacoesBanco: TStringList read fpOrientacoesBanco;

    function CalcularDigitoVerificador(const ACBrTitulo : TACBrTitulo): String; virtual;
    function CalcularTamMaximoNossoNumero(const Carteira : String; NossoNumero : String = ''): Integer; virtual;

    function TipoOcorrenciaToDescricao(const TipoOcorrencia: TACBrTipoOcorrencia): String; virtual;
    function CodOcorrenciaToTipo(const CodOcorrencia:Integer): TACBrTipoOcorrencia; virtual;
    function TipoOCorrenciaToCod(const TipoOcorrencia: TACBrTipoOcorrencia): String; virtual;
    function CodMotivoRejeicaoToDescricao(const TipoOcorrencia: TACBrTipoOcorrencia;CodMotivo:Integer): String; virtual;

    function MontarCodigoBarras(const ACBrTitulo : TACBrTitulo): String; virtual;
    function MontarCampoNossoNumero(const ACBrTitulo : TACBrTitulo): String; virtual;
    function MontarLinhaDigitavel(const CodigoBarras: String): String; virtual;
    function MontarCampoCodigoCedente(const ACBrTitulo: TACBrTitulo): String; virtual;

    procedure GerarRegistroHeader400(NumeroRemessa : Integer; ARemessa:TStringList);  Virtual;
    function GerarRegistroHeader240(NumeroRemessa : Integer): String;    Virtual;
    procedure GerarRegistroTransacao400(ACBrTitulo : TACBrTitulo; aRemessa: TStringList); Virtual;
    function GerarRegistroTransacao240(ACBrTitulo : TACBrTitulo): String; Virtual;
    procedure GerarRegistroTrailler400(ARemessa:TStringList);  Virtual;
    function GerarRegistroTrailler240(ARemessa:TStringList): String;  Virtual;
    Procedure LerRetorno400(ARetorno:TStringList); Virtual;
    Procedure LerRetorno240(ARetorno:TStringList); Virtual;

    function CalcularNomeArquivoRemessa : String; Virtual;
  end;


  { TACBrBanco }

  TACBrBanco = class(TComponent)
  private
    fACBrBoleto        : TACBrBoleto;
    fNumeroBanco       : Integer;
    fTipoCobranca      : TACBrTipoCobranca;
    fBancoClass        : TACBrBancoClass;
    function GetNome   : String;
    function GetDigito : Integer;
    function GetNumero : Integer;
    function GetOrientacoesBanco: TStringList;
    function GetTamanhoAgencia: Integer;
    function GetTamanhoCarteira: Integer;
    function GetTamanhoConta: Integer;
    function GetTamanhoMaximoNossoNum : Integer;
    procedure SetDigito(const AValue: Integer);
    procedure SetNome(const AValue: String);
    procedure SetTipoCobranca(const AValue: TACBrTipoCobranca);
    procedure SetNumero(const AValue: Integer);
    procedure SetTamMaximoNossoNumero(Const Avalue:Integer);
    procedure SetOrientacoesBanco(Const Avalue: TStringList);
  public
    constructor Create( AOwner : TComponent); override;
    destructor Destroy ; override ;

    property ACBrBoleto : TACBrBoleto     read fACBrBoleto;
    property BancoClass : TACBrBancoClass read fBancoClass ;
    property TamanhoAgencia        :Integer read GetTamanhoAgencia;
    property TamanhoConta          :Integer read GetTamanhoConta;
    property TamanhoCarteira       :Integer read GetTamanhoCarteira;

    function TipoOcorrenciaToDescricao(const TipoOcorrencia: TACBrTipoOcorrencia): String;
    function CodOcorrenciaToTipo(const CodOcorrencia:Integer): TACBrTipoOcorrencia;
    function TipoOCorrenciaToCod(const TipoOcorrencia: TACBrTipoOcorrencia): String;
    function CodMotivoRejeicaoToDescricao(const TipoOcorrencia: TACBrTipoOcorrencia;CodMotivo:Integer): String;

    function CalcularDigitoVerificador(const ACBrTitulo : TACBrTitulo): String;
    function CalcularTamMaximoNossoNumero(const Carteira : String; NossoNumero : String = ''): Integer;

    function MontarCampoCodigoCedente(const ACBrTitulo: TACBrTitulo): String;
    function MontarCampoNossoNumero(const ACBrTitulo :TACBrTitulo): String;
    function MontarCodigoBarras(const ACBrTitulo : TACBrTitulo): String;
    function MontarLinhaDigitavel(const CodigoBarras: String): String;

    procedure GerarRegistroHeader400(NumeroRemessa : Integer; ARemessa:TStringList);
    function GerarRegistroHeader240(NumeroRemessa : Integer): String;
    procedure GerarRegistroTransacao400(ACBrTitulo : TACBrTitulo;aRemessa: TStringList);
    function GerarRegistroTransacao240(ACBrTitulo : TACBrTitulo): String;
    procedure GerarRegistroTrailler400(ARemessa:TStringList);
    function GerarRegistroTrailler240(ARemessa:TStringList): String;

    procedure LerRetorno400(ARetorno:TStringList);
    procedure LerRetorno240(ARetorno:TStringList);

    function CalcularNomeArquivoRemessa : String;
  published
    property Numero    : Integer        read GetNumero  write SetNumero default 0;
    property Digito    : Integer        read GetDigito  write SetDigito stored false;
    property Nome      : String         read GetNome    write SetNome   stored false;
    property TamanhoMaximoNossoNum :Integer read GetTamanhoMaximoNossoNum  write SetTamMaximoNossoNumero;
    property TipoCobranca : TACBrTipoCobranca read fTipoCobranca   write SetTipoCobranca;
    property OrientacoesBanco : TStringList read GetOrientacoesBanco write SetOrientacoesBanco;
  end;

  TACBrResponEmissao = (tbCliEmite,tbBancoEmite,tbBancoReemite,tbBancoNaoReemite);
  TACBrCaracTitulo = (tcSimples,tcVinculada,tcCaucionada,tcDescontada,tcVendor);
  TACBrPessoa = (pFisica,pJuridica,pOutras);
  TACBrPessoaCedente = pFisica..pJuridica;

  {Aceite do titulo}
  TACBrAceiteTitulo = (atSim, atNao);   

  { TACBrCedente }

  TACBrCedente = class(TComponent)
  private
    fCodigoTransmissao: String;
    fLogradouro: String;
    fBairro: String;
    fNumeroRes: String;
    fCEP: String;
    fCidade: String;
    fCodigoCedente: String;
    fComplemento: String;
    fNomeCedente   : String;
    fAgencia       : String;
    fAgenciaDigito : String;
    fConta         : String;
    fContaDigito   : String;
    fModalidade    : String;
    fConvenio      : String;
    fResponEmissao : TACBrResponEmissao;
    fCaracTitulo:TACBrCaracTitulo;
    fCNPJCPF       : String;
    fTipoInscricao : TACBrPessoaCedente;
    fUF            : String;
    fAcbrBoleto    : TACBrBoleto;
    procedure SetAgencia(const AValue: String);
    procedure SetCNPJCPF ( const AValue: String ) ;
    procedure SetConta(const AValue: String);
    procedure SetTipoInscricao ( const AValue: TACBrPessoaCedente ) ;
  public
    constructor Create( AOwner : TComponent ) ; override ;
    destructor Destroy; override;
  published
    property Nome         : String read fNomeCedente   write fNomeCedente;
    property CodigoCedente: String read fCodigoCedente write fCodigoCedente;
    property CodigoTransmissao : String read fCodigoTransmissao write fCodigoTransmissao;
    property Agencia      : String read fAgencia       write SetAgencia;
    property AgenciaDigito: String read fAgenciaDigito write fAgenciaDigito;
    property Conta        : String read fConta         write SetConta;
    property ContaDigito  : String read fContaDigito   write fContaDigito;
    property Modalidade   : String read fModalidade    write fModalidade;
    property Convenio     : String read fConvenio      write fConvenio;
    property ResponEmissao: TACBrResponEmissao read fResponEmissao  write fResponEmissao default tbCliEmite ;
    property CaracTitulo: TACBrCaracTitulo read fCaracTitulo  write fCaracTitulo default tcSimples;
    property CNPJCPF      : String  read fCNPJCPF  write SetCNPJCPF;
    property TipoInscricao: TACBrPessoaCedente  read fTipoInscricao write  SetTipoInscricao;
    property Logradouro  : String  read fLogradouro  write fLogradouro;
    property NumeroRes   : String  read fNumeroRes      write fNumeroRes;
    property Complemento : String  read fComplemento write fComplemento;
    property Bairro      : String  read fBairro      write fBairro;
    property Cidade      : String  read fCidade      write fCidade;
    property UF          : String  read fUF          write fUF;
    property CEP         : String  read fCEP         write fCEP;
    property ACBrBoleto  : TACBrBoleto read fACBrBoleto;
  end;

  TACBrSacado = class
  private
    fTipoPessoa  : TACBrPessoa;
    fNomeSacado  : String;
    fCNPJCPF     : String;
    fAvalista    : String;
    fLogradouro  : String;
    fNumero      : String;
    fComplemento : String;
    fBairro      : String;
    fCidade      : String;
    fUF          : String;
    fCEP         : String;
    fEmail       : String;
    fFone        : String;
  public
    property Pessoa      : TACBrPessoa read fTipoPessoa  write fTipoPessoa;
    property NomeSacado  : String  read fNomeSacado  write fNomeSacado;
    property CNPJCPF     : String  read fCNPJCPF     write fCNPJCPF;
    property Avalista    : String  read fAvalista    write fAvalista;
    property Logradouro  : String  read fLogradouro  write fLogradouro;
    property Numero      : String  read fNumero      write fNumero;
    property Complemento : String  read fComplemento write fComplemento;
    property Bairro      : String  read fBairro      write fBairro;
    property Cidade      : String  read fCidade      write fCidade;
    property UF          : String  read fUF          write fUF;
    property CEP         : String  read fCEP         write fCEP;
    property Email       : String  read fEmail       write fEmail;
    property Fone        : String  read fFone        write fFone;
  end;

  { TACBrTitulo }

  TACBrTitulo = class
  private
    fInstrucao1        : String;
    fInstrucao2        : String;
    fLocalPagamento    : String;
    fOcorrenciaOriginal: TACBrOcorrencia;
    fParcela           : Integer;
    fPercentualMulta   : Double;
    fSeuNumero         : String;
    fTotalParcelas: Integer;
    fVencimento        : TDateTime;
    fDataDocumento     : TDateTime;
    fNumeroDocumento   : String;
    fEspecieDoc        : String;
    fAceite            : TACBrAceiteTitulo;
    fDataProcessamento : TDateTime;
    fNossoNumero       : String;
    fUsoBanco          : String;
    fCarteira          : String;
    fEspecieMod        : String;
    fValorDocumento    : Currency;
    fMensagem          : TStrings;
    fInstrucoes        : TStrings;
    fSacado            : TACBrSacado;

    fMotivoRejeicaoComando          : TStrings;
    fDescricaoMotivoRejeicaoComando : TStrings; 

    fDataOcorrencia       : TDateTime;
    fDataCredito          : TDateTime;
    fDataAbatimento       : TDateTime;
    fDataDesconto         : TDateTime;
    fDataMoraJuros        : TDateTime;
    fDataProtesto         : TDateTime;
    fDataBaixa            : TDateTime;
    fValorDespesaCobranca : Currency;
    fValorAbatimento      : Currency;
    fValorDesconto        : Currency;
    fValorMoraJuros       : Currency;
    fValorIOF             : Currency;
    fValorOutrasDespesas  : Currency;
    fValorOutrosCreditos  : Currency;
    fValorRecebido        : Currency;
    fReferencia           : String;
    fVersao               : String;
    fACBrBoleto           : TACBrBoleto;
    fTextoLivre           : String;

    fCodigoMora           : String;

    procedure SetCarteira(const AValue: String);
    procedure SetNossoNumero ( const AValue: String ) ;
    procedure SetParcela ( const AValue: Integer ) ;
    procedure SetTotalParcelas ( const AValue: Integer );
   public
     constructor Create(ACBrBoleto:TACBrBoleto);
     destructor Destroy; override;

     property ACBrBoleto        : TACBrBoleto read fACBrBoleto;
     property LocalPagamento    : String      read fLocalPagamento    write fLocalPagamento;
     property Vencimento        : TDateTime   read fVencimento        write fVencimento;
     property DataDocumento     : TDateTime   read fDataDocumento     write fDataDocumento;
     property NumeroDocumento   : String      read fNumeroDocumento   write fNumeroDocumento ;
     property EspecieDoc        : String      read fEspecieDoc        write fEspecieDoc;
     property Aceite            : TACBrAceiteTitulo   read fAceite           write fAceite      default atNao;
     property DataProcessamento : TDateTime   read fDataProcessamento write fDataProcessamento;
     property NossoNumero       : String      read fNossoNumero       write SetNossoNumero;
     property UsoBanco          : String      read fUsoBanco          write fUsoBanco;
     property Carteira          : String      read fCarteira          write SetCarteira;
     property EspecieMod        : String      read fEspecieMod        write fEspecieMod;
     property ValorDocumento    : Currency    read fValorDocumento    write fValorDocumento;
     property Mensagem          : TStrings    read fMensagem          write fMensagem;
     property Instrucao1        : String      read fInstrucao1        write fInstrucao1;
     property Instrucao2        : String      read fInstrucao2        write fInstrucao2;
     property Sacado            : TACBrSacado read fSacado            write fSacado;
     property Parcela           :Integer      read fParcela           write SetParcela default 1;
     property TotalParcelas     :Integer      read fTotalParcelas     write SetTotalParcelas default 1;

     property OcorrenciaOriginal : TACBrOcorrencia read  fOcorrenciaOriginal write fOcorrenciaOriginal;

     property MotivoRejeicaoComando          : TStrings    read fMotivoRejeicaoComando  write fMotivoRejeicaoComando;
     property DescricaoMotivoRejeicaoComando : TStrings    read fDescricaoMotivoRejeicaoComando  write fDescricaoMotivoRejeicaoComando;

     property DataOcorrencia                 : TDateTime read fDataOcorrencia  write fDataOcorrencia;
     property DataCredito                    : TDateTime read fDataCredito     write fDataCredito;
     property DataAbatimento                 : TDateTime read fDataAbatimento  write fDataAbatimento;
     property DataDesconto                   : TDateTime read fDataDesconto    write fDataDesconto;
     property DataMoraJuros                  : TDateTime read fDataMoraJuros   write fDataMoraJuros;
     property DataProtesto                   : TDateTime read fDataProtesto    write fDataProtesto;
     property DataBaixa                      : TDateTime read fDataBaixa       write fDataBaixa;

     property ValorDespesaCobranca : Currency read fValorDespesaCobranca  write fValorDespesaCobranca;
     property ValorAbatimento      : Currency read fValorAbatimento       write fValorAbatimento;
     property ValorDesconto        : Currency read fValorDesconto         write fValorDesconto;
     property ValorMoraJuros       : Currency read fValorMoraJuros        write fValorMoraJuros;
     property ValorIOF             : Currency read fValorIOF              write fValorIOF;
     property ValorOutrasDespesas  : Currency read fValorOutrasDespesas   write fValorOutrasDespesas;
     property ValorOutrosCreditos  : Currency read fValorOutrosCreditos   write fValorOutrosCreditos;
     property ValorRecebido        : Currency read fValorRecebido         write fValorRecebido;
     property Referencia           : String   read fReferencia            write fReferencia;
     property Versao               : String   read fVersao                write fVersao;
     property SeuNumero            : String   read fSeuNumero             write fSeuNumero;
     property PercentualMulta      : Double   read fPercentualMulta       write fPercentualMulta;

     property TextoLivre : String read fTextoLivre write fTextoLivre;

     property CodigoMora : String read fCodigoMora write fCodigoMora;
   end;

  { TListadeBoletos }
  TListadeBoletos = class(TObjectList)
  protected
    procedure SetObject (Index: Integer; Item: TACBrTitulo);
    function  GetObject (Index: Integer): TACBrTitulo;
    procedure Insert (Index: Integer; Obj: TACBrTitulo);
  public
    function Add (Obj: TACBrTitulo): Integer;
    property Objects [Index: Integer]: TACBrTitulo
      read GetObject write SetObject; default;
  end;

  TACBrBolLayOut = (lPadrao, lCarne, lFatura, lPadraoEntrega) ;

  { TACBrBoleto }
 TACBrBoleto = class( TACBrComponent )
  private
    fBanco: TACBrBanco;
    fACBrBoletoFC: TACBrBoletoFCClass;
    fDirArqRemessa: String;
    fDirArqRetorno: String;
    fLayoutRemessa: TACBrLayoutRemessa;
    fImprimirMensagemPadrao: boolean;
    fListadeBoletos : TListadeBoletos;
    fCedente        : TACBrCedente;
    fNomeArqRemessa: String;
    fNomeArqRetorno: String;
    fNumeroArquivo : integer;     {N�mero seq�encial do arquivo remessa ou retorno}
    fDataArquivo : TDateTime;     {Data da gera��o do arquivo remessa ou retorno}
    fDataCreditoLanc : TDateTime; {Data de cr�dito dos lan�amentos do arquivo retorno}
    fLeCedenteRetorno: boolean;
    function GetAbout: String;
    procedure SetAbout(const AValue: String);
    procedure SetACBrBoletoFC(const Value: TACBrBoletoFCClass);
  protected
    procedure Notification(AComponent: TComponent; Operation: TOperation); override;
  public
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
    property ListadeBoletos : TListadeBoletos read fListadeBoletos write fListadeBoletos ;

    function CriarTituloNaLista: TACBrTitulo;

    procedure Imprimir;

    procedure GerarPDF;
    procedure GerarHTML;

    // enviar email.
    procedure EnviarEmail(const sSmtpHost,
                                sSmtpPort,
                                sSmtpUser,
                                sSmtpPasswd,
                                sFrom,
                                sTo,
                                sAssunto: String;
                                sMensagem : TStrings;
                                SSL : Boolean;
                                EnviaPDF: Boolean = true;
                                sCC: TStrings = nil;
                                Anexos:TStrings=nil;
                                PedeConfirma: Boolean = False;
                                AguardarEnvio: Boolean = False;
                                NomeRemetente: String = '';
                                TLS : Boolean = True);


    procedure AdicionarMensagensPadroes(Titulo : TACBrTitulo; AStringList: TStrings);

    procedure GerarRemessa(NumeroRemessa : Integer);
    procedure LerRetorno();

  published
    property About : String read GetAbout write SetAbout stored False ;

    property Banco          : TACBrBanco         read fBanco                  write fBanco;
    property Cedente        : TACBrCedente       read fCedente                write fCedente ;
    property NomeArqRemessa : String             read fNomeArqRemessa         write fNomeArqRemessa;  //SetNomeArqRemessa;
    property DirArqRemessa  : String             read fDirArqRemessa {GetDirArqRemessa} write fDirArqRemessa; //SetNomeArqRemessa;
    property NomeArqRetorno : String             read fNomeArqRetorno         write fNomeArqRetorno; //SetNomeArqRetorno;
    property DirArqRetorno  : String             read fDirArqRetorno {GetDirArqRetorno} write fDirArqRetorno; //SetNomeArqRetorno;
    property NumeroArquivo  : integer            read fNumeroArquivo          write fNumeroArquivo;
    property DataArquivo    : TDateTime          read fDataArquivo            write fDataArquivo;
    property DataCreditoLanc: TDateTime          read fDataCreditoLanc        write fDataCreditoLanc;
    property LeCedenteRetorno :boolean           read fLeCedenteRetorno       write fLeCedenteRetorno default false;
    property LayoutRemessa  : TACBrLayoutRemessa read fLayoutRemessa          write fLayoutRemessa default c400;
    property ImprimirMensagemPadrao : Boolean    read fImprimirMensagemPadrao write fImprimirMensagemPadrao default True;
    property ACBrBoletoFC : TACBrBoletoFCClass   read fACBrBoletoFC           write SetACBrBoletoFC;
    procedure ChecarDadosObrigatorios;
  end;

 {TACBrBoletoFCClass}
 TACBrBoletoFCFiltro = (fiNenhum, fiPDF, fiHTML ) ;

 TACBrBoletoFCOnObterLogo = procedure( const PictureLogo : TPicture; const NumeroBanco: Integer ) of object ;

 TACBrBoletoFCClass = class(TACBrComponent)
  private
    fDirLogo        : String;
    fFiltro: TACBrBoletoFCFiltro;
    fLayOut         : TACBrBolLayOut;
    fMostrarPreview : Boolean;
    fMostrarSetup: Boolean;
    fNomeArquivo: String;
    fNumCopias      : Integer;
    fOnObterLogo : TACBrBoletoFCOnObterLogo ;
    fSoftwareHouse  : String;
    function GetAbout: String;
    function GetArqLogo: String;
    function GetDirLogo: String;
    function GetNomeArquivo: String;
    procedure SetAbout(const AValue: String);
    procedure SetACBrBoleto(const Value: TACBrBoleto);
    procedure SetDirLogo(const AValue: String);
  protected
    fpAbout : String ;
    fACBrBoleto : TACBrBoleto;
    procedure SetNumCopias(AValue: Integer);
    procedure Notification(AComponent: TComponent; Operation: TOperation); override;
  public

    Constructor Create(AOwner: TComponent); override;

    procedure Imprimir; virtual;
    procedure GerarPDF; virtual;
    procedure GerarHTML; virtual;

    procedure CarregaLogo( const PictureLogo : TPicture; const NumeroBanco: Integer ) ;

    property ArquivoLogo : String read GetArqLogo;
  published
    property About : String read GetAbout write SetAbout stored False ;

    property OnObterLogo    : TACBrBoletoFCOnObterLogo read fOnObterLogo write fOnObterLogo ;
    property ACBrBoleto     : TACBrBoleto     read fACBrBoleto     write SetACBrBoleto ;
    property LayOut         : TACBrBolLayOut  read fLayOut         write fLayOut         default lPadrao;
    property DirLogo        : String          read GetDirLogo      write SetDirLogo;
    property MostrarPreview : Boolean         read fMostrarPreview write fMostrarPreview default True ;
    property MostrarSetup   : Boolean         read fMostrarSetup   write fMostrarSetup   default True ;
    property NumCopias      : Integer         read fNumCopias      write SetNumCopias    default 1;
    property Filtro         : TACBrBoletoFCFiltro read fFiltro     write fFiltro         default fiNenhum ;
    property NomeArquivo    : String          read GetNomeArquivo  write fNomeArquivo ;
    property SoftwareHouse  : String          read fSoftwareHouse  write fSoftwareHouse;
  end;

  //email
  TSendMailThread = class(TThread)
  private
    FException : Exception;
    FOwner: TACBrBoleto;
    procedure DoHandleException;
  public
    OcorreramErros: Boolean;
    Terminado: Boolean;
    smtp : TSMTPSend;
    sFrom : String;
    sTo : String;
    sCC : TStrings;
    slmsg_Lines : TStrings;
    constructor Create(AOwner: TACBrBoleto);
    destructor Destroy; override;
  protected
    procedure Execute; override;
    procedure HandleException;
  end;


procedure Register;

implementation

Uses ACBrUtil, ACBrBancoBradesco, ACBrBancoBrasil, ACBrBanestes, ACBrBancoItau, ACBrBancoSicredi,
     ACBrBancoMercantil, ACBrCaixaEconomica, ACBrBancoBanrisul, ACBrBancoSantander,
     ACBrBancoob, ACBrCaixaEconomicaSICOB ,ACBrBancoHSBC,ACBrBancoNordeste , ACBrBancoBRB,Forms,
     {$IFDEF COMPILER6_UP} StrUtils {$ELSE} ACBrD5 {$ENDIF}, Math;

{$IFNDEF FPC}
   {$R ACBrBoleto.dcr}
{$ENDIF}

procedure Register;
begin
   RegisterComponents('ACBr', [TACBrBoleto]);
end;

{ TACBrCedente }
procedure TACBrCedente.SetCNPJCPF ( const AValue: String ) ;
var
   ACbrValidador: TACBrValidador;
begin
   if trim(AValue) = '' then
   begin
      fCNPJCPF:= AValue;
      exit;
   end;

   if fCNPJCPF = AValue then
      exit;

   ACbrValidador := TACBrValidador.Create(Self);
   try
     with ACbrValidador do
     begin
        if TipoInscricao = pFisica then
           TipoDocto := docCPF
        else
           TipoDocto := docCNPJ;

        IgnorarChar := './-';
        RaiseExcept := True;
        Documento   := AValue;
        Validar;    // Dispara Exception se Documento estiver errado

        fCNPJCPF := Formatar;
     end;
   finally
      ACbrValidador.Free;
   end;
end;

procedure TACBrCedente.SetConta(const AValue: String);
var
  aConta: Integer;
begin
   if fConta = AValue then
      exit;

   fConta:= AValue;
   aConta:= StrToIntDef(trim(AValue),0);

   if aConta = 0 then
      exit;

   fConta:= IntToStrZero(aConta, ACBrBoleto.Banco.TamanhoConta );
end;

procedure TACBrCedente.SetAgencia(const AValue: String);
var  aAgencia: Integer;
begin
   if fAgencia = AValue then
      exit;

   fAgencia:= AValue;

   aAgencia:= StrToIntDef(trim(AValue),0);

   if aAgencia = 0 then
      exit;

   fAgencia:= IntToStrZero(aAgencia, ACBrBoleto.Banco.TamanhoAgencia );
end;

procedure TACBrCedente.SetTipoInscricao ( const AValue: TACBrPessoaCedente ) ;
begin
   if fTipoInscricao = AValue then
      exit;

   fTipoInscricao := AValue;
end;

constructor TACBrCedente.Create( AOwner : TComponent );
begin
   inherited Create(AOwner);

   fNomeCedente   := '';
   fAgencia       := '';
   fAgenciaDigito := '';
   fConta         := '';
   fContaDigito   := '';
   fModalidade    := '';
   fConvenio      := '';
   fCNPJCPF       := '';
   fResponEmissao := tbCliEmite;
   fCaracTitulo   := tcSimples;
   fTipoInscricao := pJuridica;
   fAcbrBoleto    := TACBrBoleto(AOwner);
end;

destructor TACBrCedente.Destroy;
begin
  inherited;
end;

procedure TACBrTitulo.SetNossoNumero ( const AValue: String ) ;
var
   wTamNossoNumero: Integer;
begin
   with ACBrBoleto.Banco do
   begin
      wTamNossoNumero := TamanhoMaximoNossoNum;
      if wTamNossoNumero < 1 then
         wTamNossoNumero:= CalcularTamMaximoNossoNumero(Carteira, AValue);

      if Length(trim(AValue)) > wTamNossoNumero then
         raise Exception.Create( ACBrStr('Tamanho M�ximo do Nosso N�mero �: '+ IntToStr(wTamNossoNumero) ));

      fNossoNumero := padR(trim(AValue),wTamNossoNumero,'0');
   end;
end;

procedure TACBrTitulo.SetCarteira(const AValue: String);
var
  aCarteira: Integer;
begin
   if fCarteira = AValue then
      exit;

   with ACBrBoleto.Banco do
   begin
      aCarteira:= StrToIntDef(trim(AValue),0);

      fCarteira:=  AValue;

      if aCarteira < 1 then
         exit;

      fCarteira:= IntToStrZero(aCarteira,TamanhoCarteira);

   end;
end;

procedure TACBrTitulo.SetParcela ( const AValue: Integer ) ;
begin
   if (AValue > TotalParcelas) and (ACBrBoleto.ACBrBoletoFC.LayOut = lCarne) then
      raise Exception.Create( ACBrStr('Numero da Parcela Atual deve ser menor ' +
                                      'que o Total de Parcelas do Carn�') );
   fParcela := AValue;
end;

procedure TACBrTitulo.SetTotalParcelas ( const AValue: Integer ) ;
begin
   if (AValue < Parcela) and (ACBrBoleto.ACBrBoletoFC.LayOut = lCarne) then
      raise Exception.Create( ACBrStr('Numero da Parcela Atual deve ser menor ou igual ' +
                                      'o Total de Parcelas do Carn�') );
   fTotalParcelas := AValue;
end;

{ TACBrTitulo }

constructor TACBrTitulo.Create(ACBrBoleto:TACBrBoleto);
begin
   inherited Create;

   fACBrBoleto        := ACBrBoleto;
   fLocalPagamento    := 'Pagar preferencialmente nas agencias do '+ ACBrBoleto.Banco.Nome;
   fVencimento        := 0;
   fDataDocumento     := 0;
   fNumeroDocumento   := '';
   fEspecieDoc        := 'DM';
   fAceite            := atNao;
   fDataProcessamento := now;
   fNossoNumero       := '';
   fUsoBanco          := '';
   fCarteira          := '';
   fEspecieMod        := '';
   fValorDocumento    := 0;
   fMensagem          := TStringList.Create;
   fInstrucoes        := TStringList.Create;
   fSacado            := TACBrSacado.Create;

   fOcorrenciaOriginal:= TACBrOcorrencia.Create(Self);
   fMotivoRejeicaoComando          := TStringList.Create;
   fDescricaoMotivoRejeicaoComando := TStringList.Create;

   fDataOcorrencia       := 0;
   fDataCredito          := 0;
   fDataAbatimento       := 0;
   fDataDesconto         := 0;
   fDataMoraJuros        := 0;
   fDataProtesto         := 0;
   fDataBaixa            := 0;
   fValorDespesaCobranca := 0;
   fValorAbatimento      := 0;
   fValorDesconto        := 0;
   fValorMoraJuros       := 0;
   fValorIOF             := 0;
   fValorOutrasDespesas  := 0;
   fValorOutrosCreditos  := 0;
   fValorRecebido        := 0;
   fReferencia           := '';
   fVersao               := '';

   fCodigoMora := '0';
end;

destructor TACBrTitulo.Destroy;
begin
   fMensagem.Free;
   fSacado.Free;
   fInstrucoes.Free;
   fOcorrenciaOriginal.Free;
   fMotivoRejeicaoComando.Free;
   fDescricaoMotivoRejeicaoComando.Free;
   inherited;
end;

procedure TACBrBoleto.SetACBrBoletoFC ( const Value: TACBrBoletoFCClass ) ;
Var OldValue: TACBrBoletoFCClass;
begin
   if Value <> fACBrBoletoFC then
   begin
      if Assigned(fACBrBoletoFC) then
         fACBrBoletoFC.RemoveFreeNotification(Self);

      OldValue      := fACBrBoletoFC ;   // Usa outra variavel para evitar Loop Infinito
      fACBrBoletoFC := Value;            // na remo��o da associa��o dos componentes

      if Assigned(OldValue) then
         if Assigned(OldValue.ACBrBoleto) then
            OldValue.ACBrBoleto := nil ;

      if Value <> nil then
      begin
         Value.FreeNotification(self);
         Value.ACBrBoleto := self ;
      end ;
   end ;
end;

function TACBrBoleto.GetAbout: String;
begin
  Result := 'ACBrBoleto Ver: '+CACBrBoleto_Versao;
end;

procedure TACBrBoleto.SetAbout(const AValue: String);
begin
  {}
end;

procedure TACBrBoleto.Notification ( AComponent: TComponent;
   Operation: TOperation ) ;
begin
   inherited Notification ( AComponent, Operation ) ;

   if (Operation = opRemove) and (fACBrBoletoFC <> nil) and (AComponent is TACBrBoletoFCClass) then
     fACBrBoletoFC := nil ;
end;

{ TACBrBoleto }

constructor TACBrBoleto.Create(AOwner: TComponent);
begin
   inherited Create(AOwner);

   fACBrBoletoFC           := nil;
   fImprimirMensagemPadrao := True;

   fListadeBoletos := TListadeBoletos.Create(true);

   fBanco := TACBrBanco.Create(self);
   fBanco.Name := 'Banco';
   {$IFDEF COMPILER6_UP}
    fBanco.SetSubComponent(True);   // Ajustando como SubComponente para aparecer no ObjectInspector
   {$ENDIF}

   fCedente      := TACBrCedente.Create(self);
   fCedente.Name := 'Cedente';
   {$IFDEF COMPILER6_UP}
    fCedente.SetSubComponent(True);   // Ajustando como SubComponente para aparecer no ObjectInspector
   {$ENDIF}
end;

destructor TACBrBoleto.Destroy;
begin
   fListadeBoletos.Free;
   fCedente.Free;
   fBanco.Free;

   inherited;
end;

function TACBrBoleto.CriarTituloNaLista: TACBrTitulo;
var
   I : Integer;
begin
   I      := fListadeBoletos.Add(TACBrTitulo.Create(self));
   Result := fListadeBoletos[I];
end;

Procedure TACBrBoleto.Imprimir;
begin
   if not Assigned(ACBrBoletoFC) then
      raise Exception.Create( ACBrStr('Nenhum componente "ACBrBoletoFC" associado' ) ) ;

   if Banco.Numero = 0 then
      raise Exception.Create( ACBrStr('Banco n�o definido, impossivel listar boleto') );

   ChecarDadosObrigatorios;

   ACBrBoletoFC.Imprimir;
end;

procedure TACBrBoleto.GerarPDF;
begin
   if not Assigned(ACBrBoletoFC) then
      raise Exception.Create( ACBrStr('Nenhum componente "ACBrBoletoFC" associado' ) ) ;

   ChecarDadosObrigatorios;

   ACBrBoletoFC.GerarPDF;
end;


procedure TACBrBoleto.EnviarEmail(const sSmtpHost,
                                      sSmtpPort,
                                      sSmtpUser,
                                      sSmtpPasswd,
                                      sFrom,
                                      sTo,
                                      sAssunto: String;
                                      sMensagem : TStrings;
                                      SSL : Boolean;
                                      EnviaPDF: Boolean = true;
                                      sCC: TStrings=nil;
                                      Anexos:TStrings=nil;
                                      PedeConfirma: Boolean = False;
                                      AguardarEnvio: Boolean = False;
                                      NomeRemetente: String = '';
                                      TLS : Boolean = True);
var
  ThreadSMTP : TSendMailThread;
  m:TMimemess;
  p: TMimepart;
  StreamNFe : TStringStream;
  i: Integer;
begin
  m:=TMimemess.create;

  ThreadSMTP := TSendMailThread.Create(Self) ;  // N�o Libera, pois usa FreeOnTerminate := True ;
  StreamNFe  := TStringStream.Create('');
  try
    p := m.AddPartMultipart('mixed', nil);
    if sMensagem <> nil then
       m.AddPartText(sMensagem, p);

    if (EnviaPDF) then
     begin
       if ACBrBoletoFC.NomeArquivo = '' then
          ACBrBoletoFC.NomeArquivo := 'boleto.pdf';;
       GerarPDF;
       m.AddPartBinaryFromFile(ACBrBoletoFC.NomeArquivo, p);
     end
    else
     begin
       if ACBrBoletoFC.NomeArquivo = '' then
          ACBrBoletoFC.NomeArquivo := 'boleto.html';;
       GerarHTML;
       m.AddPartBinaryFromFile(ACBrBoletoFC.NomeArquivo, p);
     end;


    if assigned(Anexos) then
       for i := 0 to Anexos.Count - 1 do
       begin
          m.AddPartBinaryFromFile(Anexos[i], p);
       end;

    m.header.tolist.add(sTo);

    if Trim(NomeRemetente) <> '' then
       m.header.From := Format('%s<%s>', [NomeRemetente, sFrom])
    else
       m.header.From := sFrom;

    m.header.subject:= sAssunto;
    m.Header.ReplyTo := sFrom;
    if PedeConfirma then
       m.Header.CustomHeaders.Add('Disposition-Notification-To: '+sFrom);
    m.EncodeMessage;

    ThreadSMTP.sFrom := sFrom;
    ThreadSMTP.sTo   := sTo;
    if sCC <> nil then
       ThreadSMTP.sCC.AddStrings(sCC);
    ThreadSMTP.slmsg_Lines.AddStrings(m.Lines);

    ThreadSMTP.smtp.UserName := sSmtpUser;
    ThreadSMTP.smtp.Password := sSmtpPasswd;

    ThreadSMTP.smtp.TargetHost := sSmtpHost;
    if trim(sSmtpPort)<>'' then     // Usa default
       ThreadSMTP.smtp.TargetPort := sSmtpPort;

    ThreadSMTP.smtp.FullSSL := SSL;
    ThreadSMTP.smtp.AutoTLS := TLS;


    ThreadSMTP.Resume; // inicia a thread
    if AguardarEnvio then
    begin
      repeat
         Sleep(1000);
         Application.ProcessMessages;
      until ThreadSMTP.Terminado;
    end;
 finally
    m.free;
    StreamNFe.Free ;
 end;
end;

// email
procedure TSendMailThread.DoHandleException;
begin
   if FException is Exception then
      Application.ShowException(FException)
   else
      SysUtils.ShowException(FException, nil);
end;

// email
constructor TSendMailThread.Create(AOwner: TACBrBoleto);
begin
   FOwner      := AOwner;
   smtp        := TSMTPSend.Create;
   slmsg_Lines := TStringList.Create;
   sCC         := TStringList.Create;
   sFrom       := '';
   sTo         := '';

   FreeOnTerminate := True;

   inherited Create(True);
end;


destructor TSendMailThread.Destroy;
begin
   slmsg_Lines.Free;
   sCC.Free;
   smtp.Free;

   inherited;
end;

procedure TSendMailThread.Execute;
var
   I: integer;
begin
   inherited;

   try
     Terminado := False;
     try
       if not smtp.Login() then
          raise Exception.Create('SMTP ERROR: Login:' + smtp.EnhCodeString+sLineBreak+smtp.FullResult.Text);

       if not smtp.MailFrom( sFrom, Length(sFrom)) then
          raise Exception.Create('SMTP ERROR: MailFrom:' + smtp.EnhCodeString+sLineBreak+smtp.FullResult.Text);

       if not smtp.MailTo(sTo) then
          raise Exception.Create('SMTP ERROR: MailTo:' + smtp.EnhCodeString+sLineBreak+smtp.FullResult.Text);

       if (sCC <> nil) then
       begin
          for I := 0 to sCC.Count - 1 do
          begin
             if not smtp.MailTo(sCC.Strings[i]) then
                raise Exception.Create('SMTP ERROR: MailTo:' + smtp.EnhCodeString+sLineBreak+smtp.FullResult.Text);
          end;
       end;

       if not smtp.MailData(slmsg_Lines) then
          raise Exception.Create('SMTP ERROR: MailData:' + smtp.EnhCodeString+sLineBreak+smtp.FullResult.Text);

       if not smtp.Logout() then
          raise Exception.Create('SMTP ERROR: Logout:' + smtp.EnhCodeString+sLineBreak+smtp.FullResult.Text);
     finally
       try
         smtp.Sock.CloseSocket;
       except
       end ;
       Terminado := True;
     end;
   except
     Terminado := True;
     HandleException;
   end;
end;

procedure TSendMailThread.HandleException;
begin
   FException := Exception(ExceptObject);
   try
     // N�o mostra mensagens de EAbort
     if not (FException is EAbort) then
        Synchronize(DoHandleException);
   finally
     FException := nil;
   end;
end;


procedure TACBrBoleto.GerarHTML;
begin
   if not Assigned(ACBrBoletoFC) then
      raise Exception.Create( ACBrStr('Nenhum componente "ACBrBoletoFC" associado' ) );

   ChecarDadosObrigatorios;

   ACBrBoletoFC.GerarHTML;
end;

Procedure TACBrBoleto.AdicionarMensagensPadroes( Titulo : TACBrTitulo; AStringList: TStrings );
begin
   if not ImprimirMensagemPadrao  then
      exit;

   with Titulo do
   begin
      if DataProtesto <> 0 then
         AStringList.Add(ACBrStr('Protestar em ' + FormatDateTime('dd/mm/yyyy',DataProtesto)));

      if ValorAbatimento <> 0 then
      begin
         if DataAbatimento <> 0 then
            AStringList.Add(ACBrStr('Conceder abatimento de ' +
                             FormatCurr('R$ #,##0.00',ValorAbatimento) +
                             ' para pagamento ate ' + FormatDateTime('dd/mm/yyyy',DataAbatimento)))
         else
            AStringList.Add(ACBrStr('Conceder abatimento de ' +
                             FormatCurr('R$ #,##0.00',ValorAbatimento) +
                             ' para pagamento ate ' + FormatDateTime('dd/mm/yyyy',Vencimento)));
      end;

      if ValorDesconto <> 0 then
      begin
         if DataDesconto <> 0 then
            AStringList.Add(ACBrStr('Conceder desconto de '                       +
                             FormatCurr('R$ #,##0.00',ValorDesconto)       +
                             ' para pagamento at� ' +
                             FormatDateTime('dd/mm/yyyy',DataDesconto)))
         else
            AStringList.Add(ACBrStr('Conceder desconto de '                 +
                             FormatCurr('R$ #,##0.00',ValorDesconto) +
                             ' por dia de antecipa�ao'));
      end;

      if ValorMoraJuros <> 0 then
      begin
         if DataMoraJuros <> 0 then
            AStringList.Add(ACBrStr('Cobrar juros de '                               +
                             FormatCurr('R$ #,##0.00',ValorMoraJuros)         +
                             ' por dia de atraso para pagamento a partir de ' +
                             FormatDateTime('dd/mm/yyyy',DataMoraJuros)))
         else
            AStringList.Add(ACBrStr('Cobrar juros de '                       +
                             FormatCurr('R$ #,##0.00',ValorMoraJuros) +
                             ' por dia de atraso'));
      end;

      if PercentualMulta <> 0 then
         AStringList.Add(ACBrStr('Cobrar Multa de ' +
                         FormatCurr('R$ #,##0.00',ValorDocumento*( 1+ PercentualMulta/100)-ValorDocumento) +
                         ' ap�s o vencimento.'));
   end;
end;

{ TListadeBoletos }

procedure TListadeBoletos.SetObject ( Index: Integer; Item: TACBrTitulo ) ;
begin
   inherited SetItem (Index, Item) ;
end;

function TListadeBoletos.GetObject ( Index: Integer ) : TACBrTitulo;
begin
   Result := inherited GetItem(Index) as TACBrTitulo ;
end;

procedure TListadeBoletos.Insert ( Index: Integer; Obj: TACBrTitulo ) ;
begin
   inherited Insert(Index, Obj);
end;

function TListadeBoletos.Add ( Obj: TACBrTitulo ) : Integer;
begin
   Result := inherited Add(Obj) ;
end;

{ TACBrBanco }

constructor TACBrBanco.Create ( AOwner: TComponent ) ;
begin
   inherited Create ( AOwner ) ;

   if not (AOwner is TACBrBoleto) then
      raise Exception.Create(ACBrStr('Aowner deve ser do tipo TACBrBoleto'));

   fACBrBoleto  := TACBrBoleto(AOwner);
   fNumeroBanco := 0;

   fBancoClass := TACBrBancoClass.create(Self);
end;

destructor TACBrBanco.Destroy ;
begin
   fBancoClass.Free;
   inherited ;
end ;

function TACBrBanco.GetNome: String;
begin
   Result:= ACBrStr(fBancoClass.Nome);
end;

function TACBrBanco.GetDigito: Integer;
begin
   Result := fBancoClass.Digito;
end;

function TACBrBanco.GetNumero: Integer;
begin
  Result:=  BancoClass.Numero ;
end;

function TACBrBanco.GetOrientacoesBanco: TStringList;
begin
  Result:= BancoClass.OrientacoesBanco;
end;

function TACBrBanco.GetTamanhoAgencia: Integer;
begin
  Result:= BancoClass.TamanhoAgencia;
end;

function TACBrBanco.GetTamanhoCarteira: Integer;
begin
  Result:= BancoClass.TamanhoCarteira;
end;

function TACBrBanco.GetTamanhoConta: Integer;
begin
   Result:= BancoClass.TamanhoConta;
end;

function TACBrBanco.GetTamanhoMaximoNossoNum: Integer;
begin
   Result := BancoClass.TamanhoMaximoNossoNum;
end;

procedure TACBrBanco.SetDigito(const AValue: Integer);
begin
  {Apenas para aparecer no ObjectInspector do D7}
end;

procedure TACBrBanco.SetNome(const AValue: String);
begin
  {Apenas para aparecer no ObjectInspector do D7}
end;

procedure TACBrBanco.SetNumero(const AValue: Integer);
begin
  {Apenas para aparecer no ObjectInspector do D7}
end;

procedure TACBrBanco.SetTamMaximoNossoNumero(const Avalue: Integer);
begin
  {Altera o tamanho maximo do Nosso Numero} 
  BancoClass.fpTamanhoMaximoNossoNum := AValue;
end;

procedure TACBrBanco.SetOrientacoesBanco(const Avalue: TStringList);
begin
   BancoClass.fpOrientacoesBanco.Text := AValue.Text;
end;

procedure TACBrBanco.SetTipoCobranca(const AValue: TACBrTipoCobranca);
begin
   if fTipoCobranca = AValue then
      exit;

   fBancoClass.Free;

   case AValue of
     cobBancoDoBrasil  : fBancoClass := TACBrBancoBrasil.create(Self);         {001}
     cobBancoDoNordeste:fBancoClass  := TACBrBancoNordeste.create(Self);       {004}
     cobBanestes       : fBancoClass := TACBrBanestes.create(self);            {021}
     cobSantander      : fBancoClass := TACBrBancoSantander.create(Self);      {033,353,008}
     cobBanrisul       : fBancoClass := TACBrBanrisul.create(Self);            {041}
     cobBRB            : fBancoClass := TACBrBancoBRB.create(self);             {070}
     cobCaixaEconomica : fBancoClass := TACBrCaixaEconomica.create(Self);      {104}
     cobCaixaSicob     : fBancoClass := TACBrCaixaEconomicaSICOB.create(Self); {104}
     cobBradesco       : fBancoClass := TACBrBancoBradesco.create(Self);       {237}
     cobItau           : fBancoClass := TACBrBancoItau.Create(self);           {341}
     cobBancoMercantil : fBancoClass := TACBrBancoMercantil.create(Self);      {389}
     cobSicred         : fBancoClass := TACBrBancoSicredi.Create(self);        {748}
     cobBancoob        : fBancoClass := TACBrBancoob.create(self);              {756}
     cobHSBC           : fBancoClass := TACBrBancoHSBC.create(self);            {399}
   else
     fBancoClass := TACBrBancoClass.create(Self);
   end;

   fTipoCobranca := AValue;
end;

function TACBrBanco.TipoOcorrenciaToDescricao( const TipoOcorrencia: TACBrTipoOcorrencia
   ) : String;
begin
   Result:= BancoClass.TipoOcorrenciaToDescricao(TipoOCorrencia);
end;

function TACBrBanco.CodOcorrenciaToTipo(const CodOcorrencia: Integer ) : TACBrTipoOcorrencia;
begin
   Result:= BancoClass.CodOcorrenciaToTipo(CodOcorrencia);
end;

function TACBrBanco.TipoOCorrenciaToCod (
   const TipoOcorrencia: TACBrTipoOcorrencia ) : String;
begin
   Result:= BancoClass.TipoOCorrenciaToCod(TipoOcorrencia);
end;

function TACBrBanco.CodMotivoRejeicaoToDescricao( const TipoOcorrencia:
   TACBrTipoOcorrencia;CodMotivo: Integer) : String;
begin
  Result:= BancoClass.CodMotivoRejeicaoToDescricao(TipoOcorrencia,CodMotivo);
end;

function TACBrBanco.CalcularDigitoVerificador ( const ACBrTitulo: TACBrTitulo
   ) : String;
begin
   Result:=  BancoClass.CalcularDigitoVerificador(ACBrTitulo);
end;

function TACBrBanco.CalcularTamMaximoNossoNumero(const Carteira: String; NossoNumero : String = ''): Integer;
begin
  Result:= BancoClass.CalcularTamMaximoNossoNumero(Carteira,NossoNumero);
end;

function TACBrBanco.MontarCampoNossoNumero ( const ACBrTitulo: TACBrTitulo
   ) : String;
begin
   Result:= BancoClass.MontarCampoNossoNumero(ACBrTitulo);
end;

function TACBrBanco.MontarCodigoBarras ( const ACBrTitulo: TACBrTitulo) : String;
begin
   Result:= BancoClass.MontarCodigoBarras(ACBrTitulo);
end;

function TACBrBanco.MontarLinhaDigitavel ( const CodigoBarras:String) : String;
begin
   Result:= BancoClass.MontarLinhaDigitavel(CodigoBarras);
end;

procedure TACBrBanco.GerarRegistroHeader400(NumeroRemessa: Integer; ARemessa:TStringList);
begin
  BancoClass.GerarRegistroHeader400( NumeroRemessa, ARemessa );
end;

function TACBrBanco.GerarRegistroHeader240(NumeroRemessa: Integer): String;
begin
  Result :=  BancoClass.GerarRegistroHeader240( NumeroRemessa );
end;

procedure TACBrBanco.GerarRegistroTransacao400(ACBrTitulo: TACBrTitulo; aRemessa: TStringList);
begin
  BancoClass.GerarRegistroTransacao400( ACBrTitulo, aRemessa );
end;

function TACBrBanco.GerarRegistroTransacao240(ACBrTitulo: TACBrTitulo): String;
begin
  Result := BancoClass.GerarRegistroTransacao240( ACBrTitulo );
end;

procedure TACBrBanco.GerarRegistroTrailler400(ARemessa: TStringList);
begin
  BancoClass.GerarRegistroTrailler400( ARemessa );
end;

function TACBrBanco.GerarRegistroTrailler240(ARemessa: TStringList): String;
begin
 Result :=  BancoClass.GerarRegistroTrailler240( ARemessa );
end;

procedure TACBrBanco.LerRetorno400(ARetorno: TStringList);
begin
   BancoClass.LerRetorno400(ARetorno);
end;

procedure TACBrBanco.LerRetorno240(ARetorno: TStringList);
begin
   BancoClass.LerRetorno240(ARetorno);
end;

function TACBrBanco.CalcularNomeArquivoRemessa : String;
begin
  Result:= BancoClass.CalcularNomeArquivoRemessa ;
end;

function TACBrBanco.MontarCampoCodigoCedente(
  const ACBrTitulo: TACBrTitulo): String;
begin
  Result:= BancoClass.MontarCampoCodigoCedente(ACBrTitulo);
end;


{ TACBrBancoClass }

constructor TACBrBancoClass.create(AOwner: TACBrBanco);
begin
   inherited create;

   fpAOwner := AOwner;
   fpDigito := 0;
   fpNome   := 'N�o definido';
   fpNumero := 0;
   fpTamanhoMaximoNossoNum := 10;
   fpTamanhoAgencia        := 4;
   fpTamanhoConta          := 10;
   fpModulo := TACBrCalcDigito.Create;
   fpOrientacoesBanco := TStringList.Create;
end;

destructor TACBrBancoClass.Destroy;
begin
   fpModulo.Free;
   fpOrientacoesBanco.Free;
   Inherited Destroy;
end;

procedure TACBrBancoClass.GerarRegistroHeader400( NumeroRemessa: Integer; aRemessa: TStringList);
begin
  { M�todo implementado apenas para evitar Warnings de compila��o (poderia ser abstrato)
    Voc� de fazer "override" desse m�todo em todas as classes filhas de TACBrBancoClass }
  ErroAbstract('GerarRemessa400');
end;

function TACBrBancoClass.GerarRegistroHeader240 ( NumeroRemessa: Integer
   ) : String;
begin
  Result := '';
  ErroAbstract('GerarRemessa240');
end;

procedure TACBrBancoClass.GerarRegistroTrailler400( ARemessa: TStringList);
begin
  { M�todo implementado apenas para evitar Warnings de compila��o (poderia ser abstrato)
    Voc� de fazer "override" desse m�todo em todas as classes filhas de TACBrBancoClass }
end;

function TACBrBancoClass.MontarCampoCodigoCedente(
  const ACBrTitulo: TACBrTitulo): String;
begin
  Result := '';
end;


function TACBrBancoClass.GerarRegistroTrailler240 ( ARemessa: TStringList
   ) : String;
begin
   Result:= '';
end;

Procedure TACBrBancoClass.LerRetorno400 ( ARetorno: TStringList );
begin
   ErroAbstract('LerRetorno400');
end;

Procedure TACBrBancoClass.LerRetorno240 ( ARetorno: TStringList );
begin
   ErroAbstract('LerRetorno240');
end;

procedure TACBrBancoClass.GerarRegistroTransacao400(  ACBrTitulo: TACBrTitulo; aRemessa: TStringList);
begin
  { M�todo implementado apenas para evitar Warnings de compila��o (poderia ser abstrato)
    Voc� de fazer "override" desse m�todo em todas as classes filhas de TACBrBancoClass }
end;

function TACBrBancoClass.GerarRegistroTransacao240 ( ACBrTitulo: TACBrTitulo
   ) : String;
begin
   Result:= '';
end;

function TACBrBancoClass.CalcularDigitoVerificador(const ACBrTitulo :TACBrTitulo ): String;
begin
   Result:= '';
end;

function TACBrBancoClass.CalcularTamMaximoNossoNumero(
  const Carteira: String; NossoNumero : String = ''): Integer;
begin
  Result := ACBrBanco.TamanhoMaximoNossoNum;
end;

function TACBrBancoClass.TipoOcorrenciaToDescricao(
  const TipoOcorrencia : TACBrTipoOcorrencia) : String ;
begin
  Result := '';
end ;

function TACBrBancoClass.CodOcorrenciaToTipo(const CodOcorrencia : Integer
  ) : TACBrTipoOcorrencia ;
begin
  Result := toRemessaRegistrar;
end ;

function TACBrBancoClass.TipoOCorrenciaToCod(
  const TipoOcorrencia : TACBrTipoOcorrencia) : String ;
begin
  Result := '';
end ;

function TACBrBancoClass.CodMotivoRejeicaoToDescricao(
  const TipoOcorrencia : TACBrTipoOcorrencia ; CodMotivo : Integer) : String ;
begin
  Result := '';
end ;
{
 function TACBrBancoClass.GetNumero: Integer;
begin
   Result:= ACBrBanco.Numero;
end;
}
procedure TACBrBancoClass.ErroAbstract(NomeProcedure: String);
begin
   raise Exception.Create(Format(ACBrStr('Fun��o %s n�o implementada '+
                                         ' para o banco %s') + sLineBreak +
                                         'Ajude no desenvolvimento do ACBrECF. '+ sLineBreak+
                                         'Acesse nosso Forum em: http://acbr.sf.net/',[NomeProcedure,Nome])) ;
end;

function TACBrBancoClass.CalcularFatorVencimento(const DataVencimento: TDatetime) : String;
begin
   Result := IntToStr( Trunc(DataVencimento - EncodeDate(1997,10,07)) );
end;

function TACBrBancoClass.CalcularDigitoCodigoBarras (
   const CodigoBarras: String ) : String;
begin
   Modulo.CalculoPadrao;
   Modulo.Documento := CodigoBarras;
   Modulo.Calcular;

   if (Modulo.DigitoFinal = 0) or (Modulo.DigitoFinal > 9) then
      Result := '1'
   else
      Result := IntToStr(Modulo.DigitoFinal);
end;

function TACBrBancoClass.CalcularNomeArquivoRemessa : String;
var
  Sequencia :Integer;
  NomeFixo, NomeArq: String;
begin
   Sequencia := 0;

   with ACBrBanco.ACBrBoleto do
   begin
      if NomeArqRemessa = '' then
       begin
         NomeFixo := DirArqRemessa + PathDelim + 'cb' + FormatDateTime( 'ddmm', Now );

         repeat
            Inc( Sequencia );
            NomeArq := NomeFixo + IntToStrZero( Sequencia, 2 ) + '.rem'
         until not FileExists( NomeArq ) ;

         Result := NomeArq;
       end
      else
         Result := DirArqRemessa + PathDelim + NomeArqRemessa ;
   end;
end;

function TACBrBancoClass.MontarCodigoBarras ( const ACBrTitulo: TACBrTitulo) : String;
begin
   Result:= '';
end;

function TACBrBancoClass.MontarCampoNossoNumero ( const ACBrTitulo: TACBrTitulo
   ) : String;
begin
   Result:= ACBrTitulo.NossoNumero;
end;

function TACBrBancoClass.MontarLinhaDigitavel (const CodigoBarras: String): String;
var
  Campo1, Campo2, Campo3, Campo4, Campo5: String;
begin
   fpModulo.FormulaDigito        := frModulo10;
   fpModulo.MultiplicadorInicial := 1;
   fpModulo.MultiplicadorFinal   := 2;
   fpModulo.MultiplicadorAtual   := 2;


  {Campo 1(C�digo Banco,Tipo de Moeda,5 primeiro digitos do Campo Livre) }
   fpModulo.Documento := Copy(CodigoBarras,1,3)+'9'+Copy(CodigoBarras,20,5);
   fpModulo.Calcular;

   Campo1 := copy( fpModulo.Documento, 1, 5) + '.' +
             copy( fpModulo.Documento, 6, 4) +
             IntToStr( fpModulo.DigitoFinal );

  {Campo 2(6� a 15� posi��es do campo Livre)}
   fpModulo.Documento:= copy( CodigoBarras, 25, 10);
   fpModulo.Calcular;

   Campo2 := Copy( fpModulo.Documento, 1, 5) + '.' +
             Copy( fpModulo.Documento, 6, 5) +
             IntToStr( fpModulo.DigitoFinal );

  {Campo 3 (16� a 25� posi��es do campo Livre)}
   fpModulo.Documento:= copy( CodigoBarras, 35, 10);
   fpModulo.Calcular;

   Campo3 := Copy( fpModulo.Documento, 1, 5) + '.' +
             Copy( fpModulo.Documento, 6, 5) +
             IntToStr( fpModulo.DigitoFinal );

  {Campo 4 (Digito Verificador Nosso Numero)}
   Campo4 := Copy( CodigoBarras, 5, 1);

  {Campo 5 (Fator de Vencimento e Valor do Documento)}
   Campo5 := Copy( CodigoBarras, 6, 14);

   Result := Campo1+' '+Campo2+' '+Campo3+' '+Campo4+' '+Campo5;
end;

procedure TACBrBoleto.GerarRemessa( NumeroRemessa : Integer );
var
   SLRemessa   : TStringList;
   ContTitulos : Integer;
   NomeArq     : String ;
begin
   if ListadeBoletos.Count < 1 then
      raise Exception.Create(ACBrStr('Lista de Boletos est� vazia'));

   ChecarDadosObrigatorios;

   if not DirectoryExists( DirArqRemessa ) then
      ForceDirectories( DirArqRemessa );

   if not DirectoryExists( DirArqRemessa ) then
      raise Exception.Create( ACBrStr('Diret�rio inv�lido:' + sLineBreak + DirArqRemessa) );

   if ( NomeArqRemessa = '' ) then
      NomeArq := Banco.CalcularNomeArquivoRemessa
   else
      NomeArq := DirArqRemessa + PathDelim + NomeArqRemessa;

   SLRemessa := TStringList.Create;
   try
      if LayoutRemessa =c400 then
      begin
         Banco.GerarRegistroHeader400( NumeroRemessa, SLRemessa );

         for ContTitulos:= 0 to ListadeBoletos.Count-1 do
            Banco.GerarRegistroTransacao400( ListadeBoletos[ContTitulos], SLRemessa);

         Banco.GerarRegistroTrailler400( SLRemessa );

      end
      else
      begin
        SLRemessa.Add( Banco.GerarRegistroHeader240( NumeroRemessa ) );

         for ContTitulos:= 0 to ListadeBoletos.Count-1 do
             SLRemessa.Add( Banco.GerarRegistroTransacao240( ListadeBoletos[ContTitulos] ) );

         SLRemessa.Add( Banco.GerarRegistroTrailler240( SLRemessa ) );
      end;
      SLRemessa.SaveToFile( NomeArq );

   finally
      SLRemessa.Free;
   end;
end;

procedure TACBrBoleto.LerRetorno( ) ;
var
  SlRetorno: TStringList;
  NomeArq  : String;
begin
   SlRetorno:= TStringList.Create;
   try
     Self.ListadeBoletos.Clear;

     if NomeArqRetorno = '' then
        raise Exception.Create(ACBrStr('NomeArqRetorno deve ser informado.'));

     NomeArq := fDirArqRetorno + PathDelim + NomeArqRetorno;

     if not FilesExists( NomeArq ) then
        raise Exception.Create(ACBrStr('Arquivo n�o encontrado:'+sLineBreak+NomeArq));

     SlRetorno.LoadFromFile( NomeArq );

     if SlRetorno.Count < 1 then
        raise exception.Create(ACBrStr('O Arquivo de Retorno:'+sLineBreak+
                                       NomeArq + sLineBreak+
                                       'est� vazio.'+sLineBreak+
                                       ' N�o h� dados para processar'));

     case Length(SlRetorno.Strings[0]) of
        240 :
          begin
            if Copy(SlRetorno.Strings[0],143,1) <> '2' then
               Raise Exception.Create( ACBrStr( NomeArq + sLineBreak +
                  'N�o � um arquivo de Retorno de cobran�a com layout CNAB240') );
            LayoutRemessa := c240 ;
          end;

        400 :
          begin
             if (Copy(SlRetorno.Strings[0],1,9) <> '02RETORNO')   then
               Raise Exception.Create( ACBrStr( NomeArq + sLineBreak +
                  'N�o � um arquivo de Retorno de cobran�a com layout CNAB400'));
            LayoutRemessa := c400 ;
          end;
        else
            raise Exception.Create( ACBrStr( NomeArq + sLineBreak+
               'N�o � um arquivo de  Retorno de cobran�a CNAB240 ou CNAB400'));
     end;

     if LayoutRemessa = c240 then
        Banco.LerRetorno240(SlRetorno)
     else
        Banco.LerRetorno400(SlRetorno);
   finally
     SlRetorno.Free;
   end;
end;

procedure TACBrBoleto.ChecarDadosObrigatorios;
begin
   if (Cedente.Nome= '') or (cedente.Conta = '') or (Cedente.ContaDigito ='') or
      (Cedente.Agencia = '') or (Cedente.AgenciaDigito = '') then
     raise Exception.Create(ACBrStr('Informa��es do Cedente incompletas'));
end;

{ TACBrBoletoFCClass }

constructor TACBrBoletoFCClass.Create ( AOwner: TComponent ) ;
begin
   inherited Create ( AOwner ) ;

   fpAbout         := 'ACBrBoletoFCClass' ;
   fACBrBoleto     := nil;
   fLayOut         := lPadrao;
   fNumCopias      := 1;
   fMostrarPreview := True;
   fMostrarSetup   := True;
   fFiltro         := fiNenhum;
   fNomeArquivo    := '' ;
end;

procedure TACBrBoletoFCClass.Notification ( AComponent: TComponent;
   Operation: TOperation ) ;
begin
   inherited Notification ( AComponent, Operation ) ;

   if (Operation = opRemove) and (fACBrBoleto <> nil) and (AComponent is TACBrBoleto) then
      fACBrBoleto := nil ;
end;

procedure TACBrBoletoFCClass.CarregaLogo(const PictureLogo : TPicture; const NumeroBanco: Integer ) ;
begin
  if Assigned( fOnObterLogo ) then
     fOnObterLogo( PictureLogo, NumeroBanco)
  else
   begin
     if FileExists( ArquivoLogo ) then
        PictureLogo.LoadFromFile( ArquivoLogo );
   end ;
end ;

procedure TACBrBoletoFCClass.SetACBrBoleto ( const Value: TACBrBoleto ) ;
  Var OldValue : TACBrBoleto ;
begin
  if Value <> fACBrBoleto then
  begin
     if Assigned(fACBrBoleto) then
        fACBrBoleto.RemoveFreeNotification(Self);

     OldValue    := fACBrBoleto ;   // Usa outra variavel para evitar Loop Infinito
     fACBrBoleto := Value;          // na remo��o da associa��o dos componentes

     if Assigned(OldValue) then
        if Assigned(OldValue.ACBrBoletoFC) then
           OldValue.ACBrBoletoFC := nil ;

     if Value <> nil then
     begin
        Value.FreeNotification(self);
        Value.ACBrBoletoFC := self ;
     end ;
  end ;

end;

procedure TACBrBoletoFCClass.SetDirLogo(const AValue: String);
begin
  fDirLogo := PathWithoutDelim( AValue );
end;

function TACBrBoletoFCClass.GetArqLogo: String;
begin
   Result := DirLogo + PathDelim + IntToStrZero( ACBrBoleto.Banco.Numero, 3)+'.bmp';
end;

function TACBrBoletoFCClass.GetAbout: String;
begin
  Result := fpAbout ;
end;

function TACBrBoletoFCClass.GetDirLogo: String;
begin
  if fDirLogo = '' then
     if not (csDesigning in Self.ComponentState) then
        fDirLogo := ExtractFilePath( ParamStr(0) ) + 'Logos' ;

  Result := fDirLogo ;
end;

function TACBrBoletoFCClass.GetNomeArquivo: String;
var
  wPath: String;
begin
   wPath  := ExtractFilePath(fNomeArquivo);
   Result := '';

   if wPath = '' then
      if not (csDesigning in Self.ComponentState) then
         Result := ExtractFilePath(ParamStr(0)) ;

   Result := trim(Result + fNomeArquivo);
end;

procedure TACBrBoletoFCClass.SetAbout(const AValue: String);
begin
  {}
end;

procedure TACBrBoletoFCClass.SetNumCopias ( AValue: Integer ) ;
begin
  fNumCopias := max( 1, Avalue);
end;

procedure TACBrBoletoFCClass.Imprimir;
begin
   if not Assigned(fACBrBoleto) then
      raise Exception.Create(ACBrStr('Componente n�o est� associado a ACBrBoleto'));

   if fACBrBoleto.ListadeBoletos.Count < 1 then
      raise Exception.Create(ACBrStr('Lista de Boletos est� vazia'));
end;

procedure TACBrBoletoFCClass.GerarPDF;
var
   FiltroAntigo         : TACBrBoletoFCFiltro;
   MostrarPreviewAntigo : Boolean;
   MostrarSetupAntigo   : Boolean;
begin
   if NomeArquivo = '' then
      raise Exception.Create( ACBrStr('NomeArquivo n�o especificado')) ;

   FiltroAntigo         := Filtro;
   MostrarPreviewAntigo := MostrarPreview;
   MostrarSetupAntigo   := MostrarSetup;
   try
     Filtro         := fiPDF;
     MostrarPreview := false;
     MostrarSetup   := false;

     Imprimir;
   finally
     Filtro         := FiltroAntigo;
     MostrarPreview := MostrarPreviewAntigo;
     MostrarSetup   := MostrarSetupAntigo;
   end;
end;

procedure TACBrBoletoFCClass.GerarHTML;
var
   FiltroAntigo         : TACBrBoletoFCFiltro;
   MostrarPreviewAntigo : Boolean;
   MostrarSetupAntigo   : Boolean;
begin
   if NomeArquivo = '' then
      raise Exception.Create( ACBrStr('NomeArquivo n�o especificado')) ;

   FiltroAntigo         := Filtro;
   MostrarPreviewAntigo := MostrarPreview;
   MostrarSetupAntigo   := MostrarSetup;

   try
     Filtro         := fiHTML;
     MostrarPreview := false;
     MostrarSetup   := false;

     Imprimir;
   finally
     Filtro         := FiltroAntigo;
     MostrarPreview := MostrarPreviewAntigo;
     MostrarSetup   := MostrarSetupAntigo;
   end;
end;

{ TACBrOcorrencia }

function TACBrOcorrencia.GetCodigoBanco: String;
begin
   Result:= fpAowner.AcbrBoleto.Banco.TipoOCorrenciaToCod(Tipo);
end;

function TACBrOcorrencia.GetDescricao: String;
begin
   //if Tipo <> 0 then
      Result:= fpAowner.ACBrBoleto.Banco.TipoOcorrenciaToDescricao(Tipo)
   //else
   //   Result:= '';
end;

constructor TACBrOcorrencia.Create(AOwner: TACBrTitulo);
begin
   fTipo := toRemessaRegistrar;
   fpAOwner:= AOwner;
end;

{$ifdef FPC}
initialization
   {$I ACBrBoleto.lrs}
{$endif}

end.


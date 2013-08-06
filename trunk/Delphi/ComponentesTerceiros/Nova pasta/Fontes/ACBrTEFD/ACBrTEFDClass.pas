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

{******************************************************************************
|* Historico
|*
|* 21/11/2009: Daniel Simoes de Almeida
|*  - Primeira Versao: Cria�ao e Distribui�ao da Primeira Versao
******************************************************************************}

{$I ACBr.inc}

unit ACBrTEFDClass ;

interface

uses
  Classes, SysUtils, Contnrs, ACBrBase
  {$IFNDEF NOGUI}
    {$IFDEF VisualCLX}
       ,QForms, QDialogs, QControls
    {$ELSE}
       ,Forms, Dialogs, Controls
    {$ENDIF}
  {$ENDIF} ;

{$IFDEF NOGUI}
type TModalResult = (mrNone = 0, mrYes = 6, mrNo = 7, mrOK = 1, mrCancel = 2, mrAbort = 3, mrRetry = 4, mrIgnore = 5, mrAll = 8, mrNoToAll = 9, mrYesToAll = 10);
{$ENDIF}

const
   CACBrTEFD_Versao      = '4.3.4' ;
   CACBrTEFD_EsperaSTS   = 7 ;
   CACBrTEFD_EsperaSleep = 250 ;
   CACBrTEFD_NumVias     = 2 ;
   CACBrTEFD_DestaqueVia = 'Destaque a %d� Via' ;
   CACBrTEFD_Erro_ECFNaoLivre = 'ECF n�o est� LIVRE' ;
   CACBrTEFD_Erro_ECFNaoResponde = 'Impressora n�o responde'+sLineBreak+
                                   'Deseja imprimir novamente ?' ;
   CACBrTEFD_Erro_NaoAtivo = 'O gerenciador padr�o %s n�o est� ativo !' ;
   CACBrTEFD_Erro_SemRequisicao = 'Nenhuma Requisi��o Iniciada' ;
   CACBrTEFD_Erro_OutraFormaPagamento = 'Gostaria de continuar a transa��o com '+
                                   'outra(s) forma(s) de pagamento ?';

type

  { Tipos de TEF Existente. Cado novo Tipo de Tef precisa de uma NOVA Classe,
    filha de  TACBrTEFDClass }
  TACBrTEFDTipo = ( gpNenhum, gpTefDial, gpTefDisc, gpHiperTef, gpCliSiTef,
                    gpTefGpu, gpVeSPague, gpBanese, gpTefAuttar, gpGoodCard,
                    gpFoxWin, gpCliDTEF, gpPetrocard, gpCrediShop, gpTicketCar,
                    gpConvCard ) ;

  TACBrTEFDReqEstado = ( reqNenhum,             // Nennhuma Requisi��o em andamento
                         reqIniciando,          // Iniciando uma nova Requisicao
                         reqCriandoArquivo,     // Arquivo Tempor�rio de requisi��o est� sendo criado
                         reqAguardandoResposta, // Requisi��o Escrita, Aguardando Resposta
                         reqConferindoResposta, // Verifica se o STS � v�lido
                         reqFinalizada ) ;

  TACBrTEFDRespEstado = ( respNenhum,              // Nennhuma Resposta em andamento
                          respAguardandoResposta,  // Requisi��o Escrita, Aguardando Resposta
                          respProcessando,         // Processando a Resposta
                          respConcluida ) ;

  TACBrTEFDRespParceladoPor = (parcNenhum,parcADM, parcLoja);

  TACBrTEFDRespTipoOperacao = (opOutras,opAvista,opParcelado,opPreDatado);

  EACBrTEFDErro              = class(Exception) ;
  EACBrTEFDGPNaoResponde     = class(EACBrTEFDErro) ;
  EACBrTEFDGPNaoInicializado = class(EACBrTEFDErro) ;
  EACBrTEFDSTSInvalido       = class(EACBrTEFDErro) ;
  EACBrTEFDArquivo           = class(EACBrTEFDErro) ;
  EACBrTEFDECF               = class(EACBrTEFDErro) ;

  TACBrTEFDAguardaRespEvent = procedure( Arquivo: String;
     SegundosTimeOut : Integer; var Interromper : Boolean) of object ;

  TACBrTEFDOperacaoMensagem = ( opmOK, opmYesNo,
                                opmExibirMsgOperador, opmRemoverMsgOperador,
                                opmExibirMsgCliente, opmRemoverMsgCliente,
                                opmDestaqueVia ) ;

  TACBrTEFDReq = class ;
  TACBrTEFDResp = class ;
  TACBrTEFDRespostasPendentes = class ;

  TACBrTEFDAntesFinalizarReq = procedure( Req : TACBrTEFDReq ) of object ;
  TACBrTEFDMudaEstadoReq     = procedure( EstadoReq  : TACBrTEFDReqEstado  ) of object ;
  TACBrTEFDMudaEstadoResp    = procedure( EstadoResp : TACBrTEFDRespEstado ) of object ;

  TACBrTEFDProcessarTransacoesPendentes = procedure( RespostasPendentes :
     TACBrTEFDRespostasPendentes ) of object ;

  TACBrTEFDAntesCancelarTransacao = procedure( RespostaPendente :
     TACBrTEFDResp ) of object ;

  TACBrTEFDExibeMsg = procedure( Operacao : TACBrTEFDOperacaoMensagem;
     Mensagem : String; var AModalResult : TModalResult ) of object ;

  TACBrTEFDOperacaoECF = ( opeAbreGerencial, opeFechaGerencial,
                           opePulaLinhas, opeSubTotalizaCupom, opeFechaCupom,
                           opeFechaVinculado, opeCancelaCupom,
                           opeImprimePagamentos ) ;

  TACBrTEFDBloqueiaMouseTeclado = procedure( Bloqueia : Boolean;
     var Tratado : Boolean ) of object ;

  TACBrTEFDExecutaAcao = procedure( var Tratado : Boolean ) of object ;

  TACBrTEFDComandaECF = procedure( Operacao : TACBrTEFDOperacaoECF; Resp : TACBrTEFDResp;
     var RetornoECF : Integer ) of object ; { -1 - N�o tratado, 0 - Erro na Execucao, 1 - Sucesso }

  TACBrTEFDComandaECFSubtotaliza = procedure( DescAcre : Double;
     var RetornoECF : Integer ) of object ; { -1 - N�o tratado, 0 - Erro na Execucao, 1 - Sucesso }

  TACBrTEFDComandaECFPagamento = procedure( IndiceECF : String; Valor : Double;
     var RetornoECF : Integer ) of object ; { -1 - N�o tratado, 0 - Erro na Execucao, 1 - Sucesso }

  TACBrTEFDComandaECFAbreVinculado = procedure( COO, IndiceECF : String; Valor : Double;
     var RetornoECF : Integer ) of object ; { -1 - N�o tratado, 0 - Erro na Execucao, 1 - Sucesso }

  TACBrTEFDTipoRelatorio = ( trGerencial, trVinculado ) ;

  TACBrTEFDComandaECFImprimeVia = procedure( TipoRelatorio : TACBrTEFDTipoRelatorio;
     Via : Integer; ImagemComprovante : TStringList;
     var RetornoECF : Integer ) of object ; { -1 - N�o tratado, 0 - Erro na Execucao, 1 - Sucesso }

  TACBrTEFDInfoECF = ( ineSubTotal,  // Valor do Saldo restante "A Pagar" do Cupom
                       ineEstadoECF, // Estado do ECF "L" Livre, "V" Em Venda de Itens,
                                     //               "P" Em Pagamento,
                                     //               "C" CDC ou Vinculado
                                     //               "G" Relat�rio Gerencial
                                     //               "N" N�o Fiscal (em qq fase, pois � dificil detectar a fase)
                                     //               "O" Outro
                       ineTotalAPagar// Valor Total de Pagamentos registrados, na Aplica��o, e n�o enviados ao ECF
                     ) ;

  TACBrTEFDObterInfoECF = procedure( Operacao : TACBrTEFDInfoECF;
     var RetornoECF : String  ) of object ;

   { TACBrTEFDLinha }

   TACBrTEFDLinha = class
   private
     fIdentificacao : SmallInt;
     fACBrTEFDLinhaInformacao : TACBrInformacao;
     fLinha : AnsiString;
     fSequencia : SmallInt;
     function GetChave : AnsiString ;
   protected
     function GetLinha : AnsiString; virtual;
     procedure SetLinha(const AValue : AnsiString); virtual;
   public
     constructor Create ;
     destructor Destroy ; override;

     property Linha : AnsiString read GetLinha write SetLinha ;

     property Identificacao : SmallInt   read fIdentificacao  ;
     property Sequencia     : SmallInt   read fSequencia      ;
     property Chave         : AnsiString read GetChave ;
     property Informacao    : TACBrInformacao read fACBrTEFDLinhaInformacao ;
   end ;

   { TACBrTEFDArquivo }

   TACBrTEFDArquivo = class
   private
      fStringList    : TStringList ;
      fACBrTEFDLinha : TACBrTEFDLinha ;
      function AchaLinha(const Identificacao : Integer;
         const Sequencia : Integer = 0 ) : Integer;
      function GetCount : Integer;
      function GetLinha(Index : Integer) : TACBrTEFDLinha;
   public
     constructor Create ;
     destructor Destroy ; override;

     procedure Clear ;

     property Conteudo : TStringList read fStringList ;
     property Count    : Integer read GetCount ;
     property Linha [Index: Integer]: TACBrTEFDLinha read GetLinha ;

     procedure GravarArquivo( const NomeArquivo : String;
        DoFlushToDisk : Boolean = False ) ;
     procedure LeArquivo( const NomeArquivo : String ) ;

     procedure GravaInformacao( const Chave, Informacao : AnsiString ) ; overload;
     procedure GravaInformacao( const Chave : AnsiString;
        const Informacao : TACBrInformacao ) ; overload;
     procedure GravaInformacao( const Identificacao : Integer;
        const Sequencia : Integer; const Informacao : AnsiString ) ; overload;
     procedure GravaInformacao( const Identificacao : Integer;
        const Sequencia : Integer; const Informacao : TACBrInformacao ) ; overload;
     function LeInformacao( const Identificacao : Integer;
        const Sequencia : Integer = 0 ) : TACBrInformacao ;

     function LeLinha( const Identificacao : Integer;
        const Sequencia : Integer = 0) : TACBrTEFDLinha ;
   end ;

   { TACBrTEFDReq }

   TACBrTEFDReq = class
   private
     fAgencia : String;
     fAgenciaDC : String;
     fBanco : String;
     fCheque : String;
     fChequeDC : String;
     fCMC7 : String;
     fConta : String;
     fContaDC : String;
     fConteudo   : TACBrTEFDArquivo;
     fDataCheque : TDateTime;
     fDataHoraTransacaoComprovante : TDateTime;
     fDocumentoPessoa : String;
     fFinalizacao : String;
     fInformacao : TACBrInformacao;
     fHeader : String;
     fID : Integer;
     fMoeda : Integer;
     fNSU : String;
     fRede : String;
     fTipoPessoa : AnsiChar;
     fValorTotal : Double;
     fDocumentoVinculado : String;
     procedure SetAgencia(const AValue : String);
     procedure SetAgenciaDC(const AValue : String);
     procedure SetBanco(const AValue : String);
     procedure SetCheque(const AValue : String);
     procedure SetChequeDC(const AValue : String);
     procedure SetCMC7(const AValue : String);
     procedure SetConta(const AValue : String);
     procedure SetContaDC(const AValue : String);
     procedure SetDataCheque(const AValue : TDateTime);
     procedure SetDataHoraTransacaoComprovante(const AValue : TDateTime);
     procedure SetDocumentoPessoa(const AValue : String);
     procedure SetFinalizacao(const AValue : String);
     procedure SetHeader(const AValue : String);
     procedure SetID(const AValue : Integer);
     procedure SetMoeda(const AValue : Integer);
     procedure SetNSU(const AValue : String);
     procedure SetRede(const AValue : String);
     procedure SetTipoPessoa(const AValue : AnsiChar);
     procedure SetValorTotal(const AValue : Double);
     procedure SetDocumentoVinculado(const AValue : String);
   public
     constructor Create ;
     destructor Destroy ; override;

     procedure Clear ;

     property Conteudo : TACBrTEFDArquivo read fConteudo ;

     property Header            : String    read fHeader             write SetHeader ;
     property ID                : Integer   read fID                 write SetID ;
     property DocumentoVinculado: String    read fDocumentoVinculado write SetDocumentoVinculado ;
     property ValorTotal        : Double    read fValorTotal         write SetValorTotal ;
     property Moeda             : Integer   read fMoeda              write SetMoeda ;
     property CMC7              : String    read fCMC7               write SetCMC7 ;
     property TipoPessoa        : AnsiChar  read fTipoPessoa         write SetTipoPessoa ;
     property DocumentoPessoa   : String    read fDocumentoPessoa    write SetDocumentoPessoa ;
     property DataCheque        : TDateTime read fDataCheque         write SetDataCheque ;
     property Rede              : String    read fRede               write SetRede ;
     property NSU               : String    read fNSU                write SetNSU ;
     property Finalizacao       : String    read fFinalizacao        write SetFinalizacao ;
     property Banco             : String    read fBanco              write SetBanco ;
     property Agencia           : String    read fAgencia            write SetAgencia ;
     property AgenciaDC         : String    read fAgenciaDC          write SetAgenciaDC ;
     property Conta             : String    read fConta              write SetConta ;
     property ContaDC           : String    read fContaDC            write SetContaDC ;
     property Cheque            : String    read fCheque             write SetCheque ;
     property ChequeDC          : String    read fChequeDC           write SetChequeDC ;
     property DataHoraTransacaoComprovante : TDateTime read fDataHoraTransacaoComprovante write SetDataHoraTransacaoComprovante ;

     procedure GravaInformacao( const Identificacao : Integer;
        const Sequencia : Integer; const Informacao : String ) ;
   end;


   { Definindo novo tipo para armazenar as Parcelas }

   { TACBrTEFDRespParcela }

   TACBrTEFDRespParcela = class
    private
       fsVencimentoParcela: TDateTime;
       fsValorParcela: Double;
       fsNSUParcela: String;
    public
       constructor create ;

       property Vencimento : TDateTime read fsVencimentoParcela write fsVencimentoParcela ;
       property Valor      : Double    read fsValorParcela      write fsValorParcela ;
       property NSUParcela : String    read fsNSUParcela        write fsNSUParcela ;
   end;


   { TACBrTEFDRespParcelas }
   { Lista de Objetos do tipo TACBrTEFParcela }

   TACBrTEFDRespParcelas = class(TObjectList)
     protected
       procedure SetObject (Index: Integer; Item: TACBrTEFDRespParcela);
       function GetObject (Index: Integer): TACBrTEFDRespParcela;
     public
       function Add (Obj: TACBrTEFDRespParcela): Integer;
       procedure Insert (Index: Integer; Obj: TACBrTEFDRespParcela);
       property Objects [Index: Integer]: TACBrTEFDRespParcela
         read GetObject write SetObject; default;
     end;


   { TACBrTEFDResp }

   TACBrTEFDResp = class
   protected
     fpAgencia : String;
     fpAgenciaDC : String;
     fpAutenticacao : String;
     fpArqBackup : String;
     fpArqRespPendente: String;
     fpBanco : String;
     fpCheque : String;
     fpChequeDC : String;
     fpCMC7 : String;
     fpCNFEnviado : Boolean;
     fpCodigoAutorizacaoTransacao : Integer;
     fpConta : String;
     fpContaDC : String;
     fpConteudo : TACBrTEFDArquivo;
     fpDataCheque : TDateTime;
     fpDataHoraTransacaoCancelada : TDateTime;
     fpDataHoraTransacaoComprovante : TDateTime;
     fpDataHoraTransacaoHost : TDateTime;
     fpDataHoraTransacaoLocal : TDateTime;
     fpDataPreDatado : TDateTime;
     fpDocumentoPessoa : String;
     fpFinalizacao : String;
     fpHeader : String;
     fpID : Integer;
     fpIndiceFPG_ECF : String;
     fpMoeda : Integer;
     fpNomeAdministradora : String;
     fpNSU : String;
     fpNSUTransacaoCancelada : String;
     fpNumeroLoteTransacao : Integer;
     fpOrdemPagamento : Integer;
     fpQtdLinhasComprovante : Integer;
     fpQtdParcelas : Integer;
     fpRede : String;
     fpStatusTransacao : String;
     fpTextoEspecialCliente : String;
     fpTextoEspecialOperador : String;
     fpTipoGP : TACBrTEFDTipo;
     fpTipoPessoa : AnsiChar;
     fpTipoTransacao : Integer;
     fpTrailer : String;
     fpValorTotal : Double;
     fpValorOriginal: Double;
     fpSaque: Double;
     fpDesconto: Double;
     fpDocumentoVinculado : String;
     fpTipoParcelamento : Integer;
     fpParcelas : TACBrTEFDRespParcelas ;
     fpImagemComprovante1aVia : TStringList ;
     fpImagemComprovante2aVia : TStringList ;
     fpDataVencimento: TDateTime;
     fpInstituicao: String;
     fpModalidadePagto: String;
     fpModalidadePagtoDescrita: String;
     fpModalidadePagtoExtenso: String;
     fpCodigoRedeAutorizada: String;
     fpDebito: Boolean;
     fpCredito: Boolean;
     fpParceladoPor: TACBrTEFDRespParceladoPor;
     fpValorEntradaCDC:Double;
     fpDataEntradaCDC:TDateTime;
     fpTipoOperacao: TACBrTEFDRespTipoOperacao;

     procedure SetCNFEnviado(const AValue : Boolean);
     procedure SetIndiceFPG_ECF(const AValue : String);
     procedure SetArqBackup(const AValue : String);
     procedure SetOrdemPagamento(const AValue : Integer);
   protected
     function GetTransacaoAprovada : Boolean; virtual;
   published

   public
     constructor Create ;
     destructor Destroy ; override;

     procedure Assign( Source : TACBrTEFDResp ) ;
     procedure ConteudoToProperty; virtual;

     procedure Clear ;
     procedure LeArquivo( const NomeArquivo : String ) ;
     Function LeInformacao( const Identificacao : Integer;
        const Sequencia : Integer = 0 ) : TACBrInformacao ;

     property Conteudo : TACBrTEFDArquivo read fpConteudo ;

     property Header                      : String    read fpHeader ;
     property ID                          : Integer   read fpID ;
     property DocumentoVinculado          : String    read fpDocumentoVinculado ;
     property ValorTotal                  : Double    read fpValorTotal ;
     property ValorOriginal               : Double    read fpValorOriginal ;
     property Saque                       : Double    read fpSaque ;
     property Desconto                    : Double    read fpDesconto ;
     property Moeda                       : Integer   read fpMoeda ;
     property CMC7                        : String    read fpCMC7 ;
     property TipoPessoa                  : AnsiChar  read fpTipoPessoa ;
     property DocumentoPessoa             : String    read fpDocumentoPessoa ;
     property DataCheque                  : TDateTime read fpDataCheque ;
     property Rede                        : String    read fpRede ;
     property NSU                         : String    read fpNSU ;
     property Finalizacao                 : String    read fpFinalizacao ;
     property StatusTransacao             : String    read fpStatusTransacao ;
     property TransacaoAprovada           : Boolean   read GetTransacaoAprovada ;
     property TipoTransacao               : Integer   read fpTipoTransacao ;
     property CodigoAutorizacaoTransacao  : Integer   read fpCodigoAutorizacaoTransacao ;
     property NumeroLoteTransacao         : Integer   read fpNumeroLoteTransacao ;
     property DataHoraTransacaoHost       : TDateTime read fpDataHoraTransacaoHost ;
     property DataHoraTransacaoLocal      : TDateTime read fpDataHoraTransacaoLocal ;
     property TipoParcelamento            : Integer   read fpTipoParcelamento ;
     property QtdParcelas                 : Integer   read fpQtdParcelas ;
     property DataPreDatado               : TDateTime read fpDataPreDatado ;
     property NSUTransacaoCancelada       : String    read fpNSUTransacaoCancelada ;
     property DataHoraTransacaoCancelada  : TDateTime read fpDataHoraTransacaoCancelada ;
     property QtdLinhasComprovante        : Integer   read fpQtdLinhasComprovante ;
     property TextoEspecialOperador       : String    read fpTextoEspecialOperador ;
     property TextoEspecialCliente        : String    read fpTextoEspecialCliente ;
     property Autenticacao                : String    read fpAutenticacao ;
     property Banco                       : String    read fpBanco ;
     property Agencia                     : String    read fpAgencia ;
     property AgenciaDC                   : String    read fpAgenciaDC ;
     property Conta                       : String    read fpConta ;
     property ContaDC                     : String    read fpContaDC ;
     property Cheque                      : String    read fpCheque ;
     property ChequeDC                    : String    read fpChequeDC ;
     property NomeAdministradora          : String    read fpNomeAdministradora ;
     property DataHoraTransacaoComprovante: TDateTime read fpDataHoraTransacaoComprovante ;
     property Trailer                     : String    read fpTrailer ;

     property Parcelas : TACBrTEFDRespParcelas read fpParcelas ;

     property ImagemComprovante1aVia : TStringList read fpImagemComprovante1aVia ;
     property ImagemComprovante2aVia : TStringList read fpImagemComprovante2aVia ;

     property ArqBackup      : String  read fpArqBackup       write SetArqBackup ;
     property ArqRespPendente: String  read fpArqRespPendente write fpArqRespPendente;
     property TipoGP : TACBrTEFDTipo   read fpTipoGP          write fpTipoGP ;

     property IndiceFPG_ECF  : String  read fpIndiceFPG_ECF   write SetIndiceFPG_ECF ;
     property CNFEnviado     : Boolean read fpCNFEnviado      write SetCNFEnviado ;
     property OrdemPagamento : Integer read fpOrdemPagamento  write SetOrdemPagamento ;

     property DataVencimento : TDateTime read fpDataVencimento;
     property Instituicao    : String read fpInstituicao;
     property ModalidadePagto :String read fpModalidadePagto;
     property ModalidadePagtoDescrita:String read fpModalidadePagtoDescrita;
     property ModalidadeExtenso:String read fpModalidadePagtoExtenso;
     property CodigoRedeAutorizada:String read fpCodigoRedeAutorizada;
     property Debito:Boolean read fpDebito;
     property Credito:Boolean read fpCredito;
     property ParceladoPor: TACBrTEFDRespParceladoPor read fpParceladoPor;
     property ValorEntradaCDC:Double read fpValorEntradaCDC;
     property DataEntradaCDC:TDateTime read fpDataEntradaCDC;
     property TipoOperacao: TACBrTEFDRespTipoOperacao read fpTipoOperacao;
   end;

   { TACBrTEFDRespTXT }

   TACBrTEFDRespTXT = class( TACBrTEFDResp )
   private
     function GetTrailerOK : Boolean;
   protected
     function GetTransacaoAprovada : Boolean; override;
   public
     procedure ConteudoToProperty; override;
     property TrailerOk : Boolean read GetTrailerOK ;
   end;

   { TACBrTEFDRespostasPendentes }

   TACBrTEFDRespostasPendentes = class(TObjectList)
   private
      fSaldoAPagar : Double;
      function GetSaldoRestante : Double;
      function GetTotalPago : Double;
      function GetTotalDesconto : Double;
   protected
      procedure SetObject (Index: Integer; Item: TACBrTEFDResp);
      function GetObject (Index: Integer): TACBrTEFDResp;
   public
      function Add (Obj: TACBrTEFDResp): Integer;
      procedure Insert (Index: Integer; Obj: TACBrTEFDResp);
      property Objects [Index: Integer]: TACBrTEFDResp
        read GetObject write SetObject; default;

      property SaldoAPagar   : Double read fSaldoAPagar write fSaldoAPagar ;
      property TotalPago     : Double read GetTotalPago ;
      property TotalDesconto : Double read GetTotalDesconto ;
      property SaldoRestante : Double read GetSaldoRestante ;
   end;

   TACBrTEFDArrayGrupoRespostasPendentes = array of record
      IndiceFPG_ECF  : String ;
      OrdemPagamento : Integer ;
      Total  : Double ;
   end ;

   { TACBrTEFDClass }

   TACBrTEFDClass = class( TComponent )
   private
     fArqLOG : String;
     fArqReq : String;
     fArqTmp : String;
     fArqResp: String;
     fArqSTS : String;

     fAutoAtivarGP : Boolean;
     fEsperaSTS : Integer;
     fGPExeName : String;
     fHabilitado : Boolean;

     procedure SetArqReq(const AValue : String);
     procedure SetArqResp(const AValue : String);
     procedure SetArqSTS(const AValue : String);
     procedure SetArqTmp(const AValue : String);
     procedure SetInicializado(const AValue : Boolean);
     procedure SetGPExeName(const AValue : String);

   protected
     fpInicializado : Boolean;
     fpReq  : TACBrTEFDReq ;
     fpResp : TACBrTEFDResp ;
     fpTipo : TACBrTEFDTipo;
     fpIDSeq: Integer ;
     fpNumVias : Integer;
     fpAguardandoResposta : Boolean ;
     fpSalvarArquivoBackup: Boolean;

     procedure SetNumVias(const AValue : Integer); virtual;

     procedure IniciarRequisicao( AHeader : String; AID : Integer = 0 ); virtual;
     procedure AdicionarIdentificacao ; virtual;
     procedure FinalizarRequisicao ; virtual;

     Function VerificarRespostaRequisicao : Boolean ; virtual;
     Procedure LerRespostaRequisicao ; virtual;
     procedure FinalizarResposta( ApagarArqResp : Boolean ) ; virtual;

     Function CriarResposta( Tipo: TACBrTEFDTipo ): TACBrTEFDResp;
     Function CopiarResposta : String ; virtual;

     procedure ProcessarResposta ; virtual;
     Function ProcessarRespostaPagamento( const IndiceFPG_ECF : String;
        const Valor : Double) : Boolean; virtual;

     procedure VerificarIniciouRequisicao; virtual;

     procedure ImprimirRelatorio ; virtual;
     procedure ConfirmarESolicitarImpressaoTransacoesPendentes ; virtual ;

     Procedure VerificarTransacaoPagamento(Valor : Double); virtual;
     Function TransacaoEPagamento( AHeader: String ): Boolean;

   protected
     property AutoAtivarGP : Boolean read fAutoAtivarGP write fAutoAtivarGP
       default True ;

     property EsperaSTS : Integer read fEsperaSTS write fEsperaSTS
        default CACBrTEFD_EsperaSTS ;

     property ArqTemp  : String read fArqTmp    write SetArqTmp ;
     property ArqReq   : String read fArqReq    write SetArqReq ;
     property ArqSTS   : String read fArqSTS    write SetArqSTS  ;
     property ArqResp  : String read fArqResp   write SetArqResp ;
     property GPExeName: String read fGPExeName write SetGPExeName ;
   public
     constructor Create( AOwner : TComponent ) ; override;
     destructor Destroy ; override;

     property Tipo : TACBrTEFDTipo read fpTipo ;

     property NumVias : Integer read fpNumVias write SetNumVias
        default CACBrTEFD_NumVias ;

     property Req  : TACBrTEFDReq  read fpReq  ;
     property Resp : TACBrTEFDResp read fpResp ;

     property AguardandoResposta : Boolean read fpAguardandoResposta ;

     procedure GravaLog(AString: AnsiString);

     property Inicializado : Boolean read fpInicializado write SetInicializado ;
     procedure VerificaInicializado ;

     procedure Inicializar ; virtual;
     procedure DesInicializar ; virtual;

     procedure AtivarGP ; virtual;
     procedure VerificaAtivo ; virtual;

     procedure CancelarTransacoesPendentesClass; virtual;
     procedure ConfirmarTransacoesAnteriores; virtual;

     Procedure ATV ; virtual;
     Function ADM : Boolean; virtual;
     Function CRT( Valor : Double; IndiceFPG_ECF : String;
        DocumentoVinculado : String = ''; Moeda : Integer = 0 ) : Boolean; virtual;
     Function CHQ( Valor : Double; IndiceFPG_ECF : String;
        DocumentoVinculado : String = ''; CMC7 : String = '';
        TipoPessoa : AnsiChar = 'F'; DocumentoPessoa : String = '';
        DataCheque : TDateTime = 0; Banco   : String = '';
        Agencia    : String = ''; AgenciaDC : String = '';
        Conta      : String = ''; ContaDC   : String = '';
        Cheque     : String = ''; ChequeDC  : String = '';
        Compensacao: String = '' ) : Boolean ; virtual;
     Procedure NCN ; overload; virtual;
     Procedure NCN(Rede, NSU, Finalizacao : String;
        Valor : Double = 0; DocumentoVinculado : String = '') ;
        overload; virtual; 
     Procedure CNF ; overload; virtual;
     Procedure CNF(Rede, NSU, Finalizacao : String;
        DocumentoVinculado : String = ''); overload; virtual;
     Function CNC : Boolean ; overload; virtual;
     Function CNC(Rede, NSU : String; DataHoraTransacao : TDateTime;
        Valor : Double) : Boolean; overload; virtual;
   published
     property ArqLOG : String read fArqLOG write fArqLOG ;

     Property Habilitado: Boolean read fHabilitado write fHabilitado
       default False ;
   end;

   { Lista de Objetos do tipo TACBrTEFDClass }

   { TACBrTEFDClasses }

   TACBrTEFDClassList = class(TObjectList)
     protected
       procedure SetObject (Index: Integer; Item: TACBrTEFDClass);
       function GetObject (Index: Integer): TACBrTEFDClass;
       procedure Insert (Index: Integer; Obj: TACBrTEFDClass);
     public
       function Add (Obj: TACBrTEFDClass): Integer;
       property Objects [Index: Integer]: TACBrTEFDClass
         read GetObject write SetObject; default;
     end;

   { TACBrTEFDClassTXT }

   TACBrTEFDClassTXT = class( TACBrTEFDClass )
   public
     constructor Create( AOwner : TComponent ) ; override;

   published
     property AutoAtivarGP ;

     property NumVias;
     property EsperaSTS;

     property ArqTemp  ;
     property ArqReq   ;
     property ArqSTS   ;
     property ArqResp  ;
     property GPExeName;
   end;

function NomeCampo(const Identificacao: Integer; const Sequencia: Integer ): String;

implementation

Uses ACBrUtil, ACBrTEFD, dateutils, StrUtils, Math, ACBrTEFDCliSiTef,
     ACBrTEFDVeSPague ;

function NomeCampo(const Identificacao: Integer; const Sequencia: Integer): String;
var
   Casas: Integer;
begin
   Casas  := max(Length(IntToStr(Identificacao)),3) ;
   Result := IntToStrZero(Identificacao, Casas)+'-'+IntToStrZero(Sequencia, 3);
end;


{ TACBrTEFDLinha }

constructor TACBrTEFDLinha.Create;
begin
  fLinha         := '' ;
  fIdentificacao := 0 ;
  fSequencia     := 0 ;

  inherited ;

  fACBrTEFDLinhaInformacao := TACBrInformacao.Create;
end;

destructor TACBrTEFDLinha.Destroy;
begin
  if Assigned( fACBrTEFDLinhaInformacao ) then
     fACBrTEFDLinhaInformacao.Free ;

  inherited Destroy;
end;

function TACBrTEFDLinha.GetChave : AnsiString ;
var
  P : Integer ;
begin
   Result := '' ;

   if fLinha <> '' then
    begin
      P := pos(' = ',fLinha+' ') ;  // +' ' serve para que o POS funcione em Strings que nao tenham o ultimo espa�o " ="
      Result := copy(fLinha, 1, P-1 ) ;
    end
   else if fIdentificacao <> 0 then
      Result :=  NomeCampo( fIdentificacao, fSequencia ) ;
end;

function TACBrTEFDLinha.GetLinha : AnsiString;
begin
   Result := '' ;

   if fLinha <> '' then
      Result := fLinha
   else if fIdentificacao <> 0 then
      Result := NomeCampo( fIdentificacao, fSequencia ) + ' = ' + Informacao.AsString ;
end;

procedure TACBrTEFDLinha.SetLinha(const AValue : AnsiString);
Var
  P : Integer ;
  Chave, Valor : AnsiString ;
begin
   if fLinha = AValue then exit;

   fLinha := AValue;

   P := pos(' = ',fLinha+' ') ;   // +' ' serve para que o POS funcione em Strings que nao tenham o ultimo espa�o " ="
   if P = 0 then
   begin
      Informacao.AsString := '';
      fIdentificacao := 0 ;
      fSequencia     := 0 ;
      exit ;
   end ;

   Chave := copy( fLinha, 1, P - 1 ) ;
   Valor := copy( fLinha, P + 3, Length(fLinha) ) ;

   P := max(pos('-',Chave),4) ;
   Informacao.AsString := Valor ;
   fIdentificacao := StrToIntDef( copy(Chave,1,P-1), 0);
   fSequencia     := StrToIntDef( copy(Chave,P+1,3), 0);
end;


{ TACBrTEFDArquivo }

constructor TACBrTEFDArquivo.Create;
begin
  inherited Create;

  fACBrTEFDLinha := TACBrTEFDLinha.Create;
  fStringList    := TStringList.Create;
  fStringList.Sorted := True ;
end;

destructor TACBrTEFDArquivo.Destroy;
begin
  fStringList.Free ;
  fACBrTEFDLinha.Free ;

  Inherited ;
end;

procedure TACBrTEFDArquivo.Clear;
begin
  fStringList.Clear;
end;

procedure TACBrTEFDArquivo.GravarArquivo(const NomeArquivo : String;
   DoFlushToDisk : Boolean = False );
begin
  fStringList.SaveToFile(NomeArquivo);

  if DoFlushToDisk then
     FlushFileToDisk( NomeArquivo );
end;

procedure TACBrTEFDArquivo.LeArquivo(const NomeArquivo : String);
begin
  fStringList.Clear;
  if not FilesExists( NomeArquivo ) then
     raise EACBrTEFDArquivo.Create( ACBrStr( 'Arquivo: '+sLineBreak+
                                      NomeArquivo+sLineBreak+'n�o encontrado' ) ) ;
  fStringList.LoadFromFile( NomeArquivo );
end;

procedure TACBrTEFDArquivo.GravaInformacao(const Chave, Informacao : AnsiString) ;
var
  I, IndChave : Integer ;
begin
  IndChave := -1 ;
  I        := 0 ;
  while (IndChave < 0) and (I < fStringList.Count) do
  begin
     if copy(fStringList[I],1,Length(Chave)+3) = Chave + ' = ' then
        IndChave := I
     else
        Inc( I ) ;
  end;

  if IndChave >= 0 then
     fStringList.Delete(I);  // Remove o Antigo

  if Informacao <> '' then
     fStringList.Add( Chave + ' = '+ Informacao )
end ;

procedure TACBrTEFDArquivo.GravaInformacao(const Chave : AnsiString ;
  const Informacao : TACBrInformacao) ;
begin
   GravaInformacao(Chave, Informacao.AsString);
end ;

procedure TACBrTEFDArquivo.GravaInformacao(const Identificacao : Integer;
   const Sequencia : Integer; const Informacao : AnsiString);
Var
  I : Integer ;
begin
  I := AchaLinha(Identificacao, Sequencia) ;
  if I >= 0 then
     fStringList.Delete(I);  // Remove o Antigo

  if Informacao <> '' then
     fStringList.Add( NomeCampo(Identificacao,Sequencia) + ' = '+ Informacao ) ;
end;

procedure TACBrTEFDArquivo.GravaInformacao( const Identificacao : Integer;
   const Sequencia : Integer; const Informacao : TACBrInformacao ) ;
begin
  GravaInformacao(Identificacao, Sequencia, Informacao.AsString);
end;

function TACBrTEFDArquivo.AchaLinha(const Identificacao : Integer;
  const Sequencia : Integer = 0 ) : Integer;
Var
  Campo : String;
  I : Integer;
begin
  Campo := NomeCampo(Identificacao, Sequencia);

  Result := -1 ;
  I      := 0 ;
  while (Result < 0) and (I < fStringList.Count) do
  begin
     if copy(fStringList[I],1,Length(Campo)+3) = Campo + ' = ' then
        Result := I;
     Inc( I ) ;
  end;
end;

function TACBrTEFDArquivo.GetCount : Integer;
begin
   Result := Conteudo.Count;
end;

function TACBrTEFDArquivo.GetLinha(Index : Integer) : TACBrTEFDLinha;
begin
  fACBrTEFDLinha.Linha := Conteudo[Index];
  Result := fACBrTEFDLinha;
end;

function TACBrTEFDArquivo.LeLinha(const Identificacao : Integer;
  const Sequencia : Integer = 0 ) : TACBrTEFDLinha;
Var
  I : Integer ;
begin
  I := AchaLinha(Identificacao, Sequencia) ;

  if I > -1 then
     fACBrTEFDLinha.Linha := fStringList[I]
  else
     fACBrTEFDLinha.Linha := NomeCampo(Identificacao,Sequencia) + ' =  '  ;

  Result := fACBrTEFDLinha;
end;

function TACBrTEFDArquivo.LeInformacao(const Identificacao : Integer;
  const Sequencia : Integer = 0) : TACBrInformacao;
begin
  Result := LeLinha(Identificacao, Sequencia).Informacao ;
end;

{ TACBrTEFDReq }

constructor TACBrTEFDReq.Create ;
begin
  inherited Create;

  fConteudo   := TACBrTEFDArquivo.Create;
  fInformacao := TACBrInformacao.Create;

  Clear;
end;

destructor TACBrTEFDReq.Destroy;
begin
  fInformacao.Free ;
  fConteudo.Free;

  inherited ;
end;

procedure TACBrTEFDReq.Clear;
begin
  fConteudo.Clear;

  fID     := 0 ;
  fHeader := '' ;
end;

procedure TACBrTEFDReq.GravaInformacao(const Identificacao : Integer;
   const Sequencia : Integer; const Informacao : String);
begin
   fConteudo.GravaInformacao( Identificacao, Sequencia, Informacao);
end;

procedure TACBrTEFDReq.SetHeader(const AValue : String);
begin
  fConteudo.GravaInformacao(0,0,AValue);
  fHeader := AValue;
end;

procedure TACBrTEFDReq.SetID(const AValue : Integer);
begin
  fInformacao.AsInteger := AValue;
  fConteudo.GravaInformacao(1,0,fInformacao);
  fID := AValue;
end;

procedure TACBrTEFDReq.SetDocumentoVinculado(const AValue : String);
begin
  fDocumentoVinculado := OnlyNumber(AValue);
  fConteudo.GravaInformacao(2,0,fDocumentoVinculado);
end;

procedure TACBrTEFDReq.SetValorTotal(const AValue : Double);
begin
  fInformacao.AsFloat := AValue;
  fConteudo.GravaInformacao(3,0,fInformacao);
  fValorTotal := AValue;
end;

procedure TACBrTEFDReq.SetMoeda(const AValue : Integer);
begin
  fInformacao.AsString := IntToStr(AValue);   // Converte para String para garantir a existencia
  fConteudo.GravaInformacao(4,0,fInformacao);
  fMoeda := AValue;
end;

procedure TACBrTEFDReq.SetCMC7(const AValue : String);
begin
  fConteudo.GravaInformacao(5,0,AValue);
  fCMC7 := AValue;
end;

procedure TACBrTEFDReq.SetTipoPessoa(const AValue : AnsiChar);
begin
  fConteudo.GravaInformacao(6,0,AValue);
  fTipoPessoa := AValue;
end;

procedure TACBrTEFDReq.SetDocumentoPessoa(const AValue : String);
begin
  fConteudo.GravaInformacao(7,0,AValue);
  fDocumentoPessoa := AValue;
end;

procedure TACBrTEFDReq.SetDataCheque(const AValue : TDateTime);
begin
  fInformacao.AsDate := AValue;
  fConteudo.GravaInformacao(8,0,fInformacao);
  fDataCheque := AValue;
end;

procedure TACBrTEFDReq.SetRede(const AValue : String);
begin
  fConteudo.GravaInformacao(10,0,AValue);
  fRede := AValue;
end;

procedure TACBrTEFDReq.SetNSU(const AValue : String);
begin
  fNSU := OnlyNumber(AValue);
  fConteudo.GravaInformacao(12,0,fNSU);
end;

procedure TACBrTEFDReq.SetDataHoraTransacaoComprovante(const AValue : TDateTime);
begin
  fInformacao.AsDate := AValue;
  fConteudo.GravaInformacao(22,0,fInformacao);
  fInformacao.AsTime := AValue;
  fConteudo.GravaInformacao(23,0,fInformacao);

  //fInformacao.AsString := FormatDateTime('YYMMDDHHNNSS', AValue);
  //fConteudo.GravaInformacao(717,0,fInformacao);

  fDataHoraTransacaoComprovante := AValue;
end;

procedure TACBrTEFDReq.SetFinalizacao(const AValue : String);
begin
  fConteudo.GravaInformacao(27,0,AValue);
  fFinalizacao := AValue;
end;

procedure TACBrTEFDReq.SetBanco(const AValue : String);
begin
  fConteudo.GravaInformacao(33,0,AValue);
  fBanco := AValue;
end;

procedure TACBrTEFDReq.SetAgencia(const AValue : String);
begin
  fConteudo.GravaInformacao(34,0,AValue);
  fAgencia := AValue;
end;

procedure TACBrTEFDReq.SetAgenciaDC(const AValue : String);
begin
  fConteudo.GravaInformacao(35,0,AValue);
  fAgenciaDC := AValue;
end;

procedure TACBrTEFDReq.SetConta(const AValue : String);
begin
  fConteudo.GravaInformacao(36,0,AValue);
  fConta := AValue;
end;

procedure TACBrTEFDReq.SetContaDC(const AValue : String);
begin
  fConteudo.GravaInformacao(37,0,AValue);
  fContaDC := AValue;
end;

procedure TACBrTEFDReq.SetCheque(const AValue : String);
begin
  fConteudo.GravaInformacao(38,0,AValue);
  fCheque := AValue;
end;

procedure TACBrTEFDReq.SetChequeDC(const AValue : String);
begin
  fConteudo.GravaInformacao(39,0,AValue);
  fChequeDC := AValue;
end;


{ TACBrTEFDResp }

constructor TACBrTEFDResp.Create ;
begin
  inherited Create ;

  fpConteudo := TACBrTEFDArquivo.Create;
  fpParcelas := TACBrTEFDRespParcelas.create(True) ;
  
  fpImagemComprovante1aVia := TStringList.Create;
  fpImagemComprovante2aVia := TStringList.Create;

  // Inicializa as vari�veis internas //
  Clear ;
end;

destructor TACBrTEFDResp.Destroy;
begin
  fpConteudo.Free;
  fpParcelas.Free ;

  fpImagemComprovante1aVia.Free ;
  fpImagemComprovante2aVia.Free ;

  inherited ;
end;

procedure TACBrTEFDResp.Assign(Source : TACBrTEFDResp);
begin
  Conteudo.Clear;
  Conteudo.Conteudo.Assign(Source.Conteudo.Conteudo);
  ConteudoToProperty;

  ArqBackup := Source.ArqBackup; { ArqBackup n�o � salva em Conteudo (memory only) }
  TipoGP    := Source.TipoGP;    { TipoGP n�o � salva em Conteudo (memory only) }
end;

procedure TACBrTEFDResp.ConteudoToProperty;
begin
   {}
end;

procedure TACBrTEFDResp.Clear;
begin
   fpConteudo.Clear;
   fpParcelas.Clear;
   fpImagemComprovante1aVia.Clear;
   fpImagemComprovante2aVia.Clear;

   fpHeader                       := '' ;
   fpID                           := 0 ;
   fpAgencia                      := '' ;
   fpAgenciaDC                    := '';
   fpAutenticacao                 := '';
   fpBanco                        := '' ;
   fpCheque                       := '' ;
   fpChequeDC                     := '' ;
   fpCMC7                         := '' ;
   fpCodigoAutorizacaoTransacao   := 0 ;
   fpConta                        := '' ;
   fpContaDC                      := '' ;
   fpDataCheque                   := 0 ;
   fpDataHoraTransacaoCancelada   := 0 ;
   fpDataHoraTransacaoComprovante := 0 ;
   fpDataHoraTransacaoHost        := 0 ;
   fpDataHoraTransacaoLocal       := 0 ;
   fpDataPreDatado                := 0 ;
   fpDocumentoPessoa              := '' ;
   fpFinalizacao                  := '' ;
   fpMoeda                        := 0 ;
   fpNomeAdministradora           := '' ;
   fpNSU                          := '' ;
   fpNSUTransacaoCancelada        := '' ;
   fpNumeroLoteTransacao          := 0 ;
   fpQtdLinhasComprovante         := 0 ;
   fpQtdParcelas                  := 0 ;
   fpRede                         := '' ;
   fpStatusTransacao              := '' ;
   fpTextoEspecialCliente         := '' ;
   fpTextoEspecialOperador        := '' ;
   fpTipoPessoa                   := ' ';
   fpTipoTransacao                := 0 ;
   fpTrailer                      := '' ;
   fpValorTotal                   := 0 ;
   fpValorOriginal                := 0 ;
   fpSaque                        := 0 ;
   fpDesconto                     := 0 ;
   fpDocumentoVinculado           := '' ;
   fpTipoParcelamento             := 0 ;
   fpValorEntradaCDC              := 0;
   fpDataEntradaCDC               := 0;

   fpParceladoPor := parcNenhum;
   fpTipoOperacao := opOutras;

   fpCredito:= False;
   fpDebito := False;

   fpCNFEnviado     := False ;
   fpIndiceFPG_ECF  := '' ;
   fpOrdemPagamento := 0 ;

   fpArqBackup := '' ;
   fpArqRespPendente := '' ;
end;

procedure TACBrTEFDResp.LeArquivo(const NomeArquivo : String);
begin
  Clear;
  Conteudo.LeArquivo(NomeArquivo);

  ConteudoToProperty;
end;

procedure TACBrTEFDResp.SetCNFEnviado(const AValue : Boolean);
begin
  fpConteudo.GravaInformacao(899,1,IfThen(AValue,'S','N'));
  fpCNFEnviado := AValue;
end;

procedure TACBrTEFDResp.SetIndiceFPG_ECF(const AValue : String);
begin
  fpConteudo.GravaInformacao(899,2,AValue);
  fpIndiceFPG_ECF := AValue;
end;

procedure TACBrTEFDResp.SetArqBackup(const AValue : String);
begin
  fpArqBackup := Trim( AValue );
end;

procedure TACBrTEFDResp.SetOrdemPagamento(const AValue : Integer);
begin
  fpConteudo.GravaInformacao(899,3,IntToStr(AValue));
  fpOrdemPagamento := AValue;
end;

function TACBrTEFDResp.GetTransacaoAprovada: Boolean;
begin
   Result := True ;   { Abstrata }
end;

function TACBrTEFDResp.LeInformacao(const Identificacao : Integer;
   const Sequencia : Integer) : TACBrInformacao;
begin
   Result := Conteudo.LeInformacao(Identificacao,Sequencia);
end;

{ TACBrTEFDRespTXT }

procedure TACBrTEFDRespTXT.ConteudoToProperty;
var
   Linha : TACBrTEFDLinha ;
   I     : Integer;
   Parc  : TACBrTEFDRespParcela;
   TemReduzido, TemParcelas : Boolean ;

   function AjustaLinhaImagemComprovante( Linha: AnsiString ) : AnsiString;
   begin
      Result := Linha;

      if LeftStr(Result,1) = '"' then
         Delete(Result,1,1);
      if RightStr(Result,1) = '"' then
         Delete(Result,Length(Result),1);
   end;

begin
   fpDataHoraTransacaoComprovante := 0 ;
   fpImagemComprovante1aVia.Clear;
   fpImagemComprovante2aVia.Clear;
   TemReduzido := False;
   TemParcelas := False;

   for I := 0 to Conteudo.Count - 1 do
   begin
     Linha := Conteudo.Linha[I];

     case Linha.Identificacao of
       0   : fpHeader                     := Linha.Informacao.AsString;
       1   : fpID                         := Linha.Informacao.AsInteger;
       2   : fpDocumentoVinculado         := Linha.Informacao.AsString;
       3   : fpValorTotal                 := Linha.Informacao.AsFloat;
       4   : fpMoeda                      := Linha.Informacao.AsInteger;
       5   : fpCMC7                       := Linha.Informacao.AsString;
       6   : fpTipoPessoa                 := padL(Linha.Informacao.AsString, 1 )[ 1 ];
       7   : fpDocumentoPessoa            := Linha.Informacao.AsString;
       8   : fpDataCheque                 := Linha.Informacao.AsDate;
       9   : fpStatusTransacao            := Linha.Informacao.AsString;
       10  : fpRede                       := Linha.Informacao.AsString;
       11  : fpTipoTransacao              := Linha.Informacao.AsInteger;
       12  : fpNSU                        := Linha.Informacao.AsString;
       13  : fpCodigoAutorizacaoTransacao := Linha.Informacao.AsInteger;
       14  : fpNumeroLoteTransacao        := Linha.Informacao.AsInteger;
       15  : fpDataHoraTransacaoHost      := Linha.Informacao.AsTimeStamp;
       16  : fpDataHoraTransacaoLocal     := Linha.Informacao.AsTimeStamp;
       17  :
         begin
           fpTipoParcelamento := Linha.Informacao.AsInteger;
           case fpTipoParcelamento  of
              0 : fpParceladoPor:= parcLoja;
              1 : fpParceladoPor:= parcADM;
           else
              fpParceladoPor:= parcNenhum;
           end;
         end;
       18  : fpQtdParcelas                := Linha.Informacao.AsInteger;
       19  : TemParcelas := True ;
       22  : fpDataHoraTransacaoComprovante := fpDataHoraTransacaoComprovante +
                                               Linha.Informacao.AsDate;
       23  : fpDataHoraTransacaoComprovante := fpDataHoraTransacaoComprovante +
                                               Linha.Informacao.AsTime;
       24  : fpDataPreDatado              := Linha.Informacao.AsDate;
       25  : fpNSUTransacaoCancelada      := Linha.Informacao.AsString;
       26  : fpDataHoraTransacaoCancelada := Linha.Informacao.AsTimeStamp;
       27  : fpFinalizacao                := Linha.Informacao.AsString;
       28  :
         begin
           fpImagemComprovante1aVia.Clear;
           fpQtdLinhasComprovante := Linha.Informacao.AsInteger;
         end;
       29  : fpImagemComprovante1aVia.Add( AjustaLinhaImagemComprovante( Linha.Informacao.AsString ) );
       30  : fpTextoEspecialOperador := Linha.Informacao.AsString;
       31  : fpTextoEspecialCliente  := Linha.Informacao.AsString;
       32  : fpAutenticacao          := Linha.Informacao.AsString;
       33  : fpBanco                 := Linha.Informacao.AsString;
       34  : fpAgencia               := Linha.Informacao.AsString;
       35  : fpAgenciaDC             := Linha.Informacao.AsString;
       36  : fpConta                 := Linha.Informacao.AsString;
       37  : fpContaDC               := Linha.Informacao.AsString;
       38  : fpCheque                := Linha.Informacao.AsString;
       39  : fpChequeDC              := Linha.Informacao.AsString;
       40  : fpNomeAdministradora    := Linha.Informacao.AsString;
       707 : fpValorOriginal         := Linha.Informacao.AsFloat;
       708 : fpSaque                 := Linha.Informacao.AsFloat;
       709 : fpDesconto              := Linha.Informacao.AsFloat;

       710 :
         begin
           TemReduzido := True;
           fpImagemComprovante1aVia.Clear;
           fpQtdLinhasComprovante := Linha.Informacao.AsInteger;
         end;
       711 : fpImagemComprovante1aVia.Add( AjustaLinhaImagemComprovante( Linha.Informacao.AsString ) );

       712 :
         begin
           if not TemReduzido then
           begin
              fpImagemComprovante1aVia.Clear;
              fpQtdLinhasComprovante := Linha.Informacao.AsInteger;
           end;
         end;

       713 :
         begin
           if not TemReduzido then
              fpImagemComprovante1aVia.Add( AjustaLinhaImagemComprovante( Linha.Informacao.AsString ) );
         end ;

       714 : fpImagemComprovante2aVia.Clear;
       715 : fpImagemComprovante2aVia.Add( AjustaLinhaImagemComprovante( Linha.Informacao.AsString ) );

       899 :  // Tipos de Uso Interno do ACBrTEFD
        begin
          case Linha.Sequencia of
            1 : fpCNFEnviado     := (UpperCase( Linha.Informacao.AsString ) = 'S' );
            2 : fpIndiceFPG_ECF  := Linha.Informacao.AsString ;
            3 : fpOrdemPagamento := Linha.Informacao.AsInteger ;
          end;
        end;
       999 : fpTrailer           := Linha.Informacao.AsString ;
     end;
   end ;

   // TEF Discado, nem sempre a 2a via � enviada //
   if fpImagemComprovante2aVia.Count = 0 then
      fpImagemComprovante2aVia.AddStrings( fpImagemComprovante1aVia );

   fpParcelas.Clear;
   if TemParcelas then
   begin
      for I := 1 to fpQtdParcelas do
      begin
         Parc := TACBrTEFDRespParcela.create;
         Parc.Vencimento := LeInformacao( 19 , I).AsDate ;
         Parc.Valor      := LeInformacao( 20 , I).AsFloat ;
         Parc.NSUParcela := LeInformacao( 21 , I).AsString ;

         fpParcelas.Add(Parc);
      end;
   end;

   // Tipo da transa��o se foi Cr�dito ou D�bito
   fpDebito  := ((fpTipoTransacao >= 20) and (fpTipoTransacao <= 25)) or (fpTipoTransacao = 40) ;
   fpCredito := (fpTipoTransacao >= 10) and (fpTipoTransacao <= 12) ;

   case fpTipoTransacao of
     10,20,23    : fpTipoOperacao:= opAvista;
     11,12,22    : fpTipoOperacao:= opParcelado;
     21,24,25    : fpTipoOperacao:= opPreDatado;
     40 :
       begin
          fpTipoOperacao:= opParcelado;
          fpParceladoPor:= parcADM;
       end;

   else
     fpTipoOperacao:= opOutras;
   end;


end;

function TACBrTEFDRespTXT.GetTransacaoAprovada : Boolean;
begin
   Result := (StrToIntDef(Trim(fpStatusTransacao),-1) = 0) or
             (UpperCase(Trim(fpStatusTransacao)) = 'P1') ;  // Consulta de Cheque
end;

function TACBrTEFDRespTXT.GetTrailerOK : Boolean;
begin
  Result :=  (fpTrailer <> '') ;
end;


{ TACBrTEFDRespParcela }

constructor TACBrTEFDRespParcela.create;
begin
   fsValorParcela      := 0 ;
   fsVencimentoParcela := 0 ;
   fsNSUParcela        := '' ;
end;

{ TACBrTEFDRespParcelas }

procedure TACBrTEFDRespParcelas.SetObject(Index : Integer;
   Item : TACBrTEFDRespParcela);
begin
  inherited SetItem (Index, Item) ;
end;

function TACBrTEFDRespParcelas.GetObject(Index : Integer ) : TACBrTEFDRespParcela;
begin
   Result := inherited GetItem(Index) as TACBrTEFDRespParcela ;
end;

function TACBrTEFDRespParcelas.Add(Obj : TACBrTEFDRespParcela) : Integer;
begin
   Result := inherited Add(Obj) ;
end;

procedure TACBrTEFDRespParcelas.Insert(Index : Integer;
   Obj : TACBrTEFDRespParcela);
begin
   inherited Insert(Index, Obj);
end;

{ TACBrTEFDClassTXT }

constructor TACBrTEFDClassTXT.Create(AOwner : TComponent);
begin
   inherited Create(AOwner);

   if Assigned( fpResp ) then
      fpResp.Free ;
   fpResp := TACBrTEFDRespTXT.Create;
   fpResp.TipoGP := Tipo;
end;

{ TACBrTEFDClass }

constructor TACBrTEFDClass.Create(AOwner : TComponent);
begin
  if not (AOwner is TACBrTEFD) then
     raise EACBrTEFDErro.Create( ACBrStr('TACBrTEFDClass deve ser criado por TACBrTEFD') ) ;

  inherited Create(AOwner);

  fAutoAtivarGP := True ;
  fArqLOG       := '' ;
  fArqReq       := '' ;
  fArqTmp       := '' ;
  fArqResp      := '' ;
  fArqSTS       := '' ;
  fpTipo        := gpNenhum ;
  fpIDSeq       := SecondOfTheDay(now) ;
  fpNumVias     := CACBrTEFD_NumVias ;

  fpAguardandoResposta := False ;
  fpSalvarArquivoBackup := True;

  fEsperaSTS := CACBrTEFD_EsperaSTS ;
  fpResp     := TACBrTEFDResp.Create;
  fpReq      := TACBrTEFDReq.Create;

  TACBrTEFD(Owner).EstadoReq  := reqNenhum;
  TACBrTEFD(Owner).EstadoResp := respNenhum;
end;

destructor TACBrTEFDClass.Destroy;
begin
  fpResp.Free ;
  fpReq.Free ;

  inherited Destroy;
end;

procedure TACBrTEFDClass.VerificaInicializado;
begin
  if not fpInicializado then
     raise EACBrTEFDGPNaoInicializado.Create(
        ACBrStr('Gerenciador Padr�o: '+Name+' n�o foi inicializado')) ;
end;

procedure TACBrTEFDClass.SetInicializado(const AValue : Boolean);
begin
  if AValue then
     Inicializar
  else
     DesInicializar ;
end;

procedure TACBrTEFDClass.Inicializar;
begin
  if Inicializado then exit ;

  ApagaEVerifica( ArqTemp );  // Apagando Arquivo Temporario anterior //
  ApagaEVerifica( ArqReq );   // Apagando Arquivo de Requisicao anterior //
  ApagaEVerifica( ArqSTS );   // Apagando Arquivo de Status anterior //

  fpInicializado := True ;
  GravaLog( Name +' Inicializado' );

  { Verificando se o arquivo de Resposta � invalido ou seja, gerado quando
    clica-se em 9 - CANCELAR sem selecionar nenhuma Bandeira }
  if FileExists( ArqResp ) then
  begin
     Resp.LeArquivo( ArqResp );

     if UpperCase(Resp.Conteudo.LeInformacao(9,0).AsString) = 'FF' then
        ApagaEVerifica( ArqResp );

     Resp.Clear;
  end ;

  CancelarTransacoesPendentesClass ;

  VerificaAtivo;
end;

procedure TACBrTEFDClass.DesInicializar;
begin
  fpInicializado := False ;
  GravaLog( Name +' DesInicializado' );
end;

procedure TACBrTEFDClass.VerificaAtivo;
begin
  try
    ATV;
  except
    on E : EACBrTEFDGPNaoResponde do
     begin
       if AutoAtivarGP then
       begin
         TACBrTEFD(Owner).DoExibeMsg( opmOK,
               'O Gerenciador Padr�o n�o est� ativo e ser� ativado automaticamente!');
         AtivarGP;
         ATV;
       end;
     end;
    else
      raise ;
  end;
end;

Procedure TACBrTEFDClass.ATV ;
begin
  IniciarRequisicao('ATV');
  FinalizarRequisicao;
end;

Function TACBrTEFDClass.ADM : Boolean;
begin
  Result := False ;

  IniciarRequisicao('ADM');
  AdicionarIdentificacao;
  FinalizarRequisicao;

  LerRespostaRequisicao;
  Result := Resp.TransacaoAprovada ;
  try
     ProcessarResposta ;         { Faz a Impress�o e / ou exibe Mensagem ao Operador }
  finally
     FinalizarResposta( True ) ; { True = Apaga Arquivo de Resposta }
  end;
end;

Function TACBrTEFDClass.CRT( Valor : Double; IndiceFPG_ECF : String;
   DocumentoVinculado: String = ''; Moeda : Integer = 0 ) : Boolean;
begin
  Result := False ;
  VerificarTransacaoPagamento( Valor );

  IniciarRequisicao('CRT');
  Req.DocumentoVinculado  := DocumentoVinculado;
  Req.ValorTotal          := Valor;
  Req.Moeda               := Moeda;
  AdicionarIdentificacao;
  FinalizarRequisicao;

  Result := ProcessarRespostaPagamento( IndiceFPG_ECF, Valor);
end;

Function TACBrTEFDClass.CHQ( Valor : Double; IndiceFPG_ECF : String;
   DocumentoVinculado : String = ''; CMC7 : String = '';
   TipoPessoa : AnsiChar = 'F'; DocumentoPessoa : String = '';
   DataCheque : TDateTime = 0; Banco   : String = '';
   Agencia    : String = ''; AgenciaDC : String = '';
   Conta      : String = ''; ContaDC   : String = '';
   Cheque     : String = ''; ChequeDC  : String = '';
   Compensacao: String = '' ) : Boolean ;
begin
  // Compensacao n�o � utilizado em TEF discado //

  Result := False ;
  VerificarTransacaoPagamento( Valor );

  IniciarRequisicao('CHQ');
  Req.DocumentoVinculado := DocumentoVinculado;
  Req.ValorTotal         := Valor;
  Req.CMC7               := CMC7;
  if TipoPessoa <> ' ' then
     Req.TipoPessoa      := TipoPessoa;
  Req.DocumentoPessoa    := DocumentoPessoa;
  Req.DataCheque         := DataCheque;
  Req.Banco              := Banco;
  Req.Agencia            := Agencia;
  Req.AgenciaDC          := AgenciaDC;
  Req.Conta              := Conta;
  Req.ContaDC            := ContaDC;
  Req.Cheque             := Cheque;
  Req.ChequeDC           := ChequeDC;
  Req.Moeda              := 0;            // Moeda 0 = Real
  AdicionarIdentificacao;
  FinalizarRequisicao;

  LerRespostaRequisicao;

  try
    if (Resp.QtdLinhasComprovante <= 0) and (Resp.StatusTransacao = '0') then
       ProcessarResposta ;         { Faz a Impress�o e / ou exibe Mensagem ao Operador }
  finally
     Result := ProcessarRespostaPagamento( IndiceFPG_ECF, Valor);
  end;
end;

Function TACBrTEFDClass.CNC : Boolean ;
Var
  OldResp : TACBrTEFDRespTXT ;
begin
  Result  := False ;
  OldResp := TACBrTEFDRespTXT.Create;
  try
     OldResp.Assign(Resp);      { Salvando dados da Resposta Atual }

     IniciarRequisicao('CNC');
     Req.DocumentoVinculado           := OldResp.DocumentoVinculado;
     Req.ValorTotal                   := OldResp.ValorTotal;
     Req.CMC7                         := OldResp.CMC7;
     if OldResp.TipoPessoa <> ' ' then
        Req.TipoPessoa                := OldResp.TipoPessoa;
     Req.DocumentoPessoa              := OldResp.DocumentoPessoa;
     Req.DataCheque                   := OldResp.DataCheque;
     Req.Rede                         := OldResp.Rede;
     Req.NSU                          := OldResp.NSU;
     Req.DataHoraTransacaoComprovante := OldResp.DataHoraTransacaoComprovante;
     Req.Banco                        := OldResp.Banco;
     Req.Agencia                      := OldResp.Agencia;
     Req.AgenciaDC                    := OldResp.AgenciaDC;
     Req.Conta                        := OldResp.Conta;
     Req.ContaDC                      := OldResp.ContaDC;
     Req.Cheque                       := OldResp.Cheque;
     Req.ChequeDC                     := OldResp.ChequeDC;
     Req.Moeda                        := OldResp.Moeda;
     AdicionarIdentificacao;
     FinalizarRequisicao;

     LerRespostaRequisicao;
     Result := Resp.TransacaoAprovada;

     try
        ProcessarResposta ;         { Faz a Impress�o e / ou exibe Mensagem ao Operador }
     finally
        FinalizarResposta( True ) ; { True = Apaga Arquivo de Resposta }
     end;
  finally
     OldResp.Free;
  end;
end;

Function TACBrTEFDClass.CNC( Rede, NSU : String; DataHoraTransacao :
   TDateTime; Valor : Double) : Boolean;
begin
  Result := False ;

  IniciarRequisicao('CNC');
  Req.ValorTotal                   := Valor;
  Req.Rede                         := Rede;
  Req.NSU                          := NSU;
  Req.DataHoraTransacaoComprovante := DataHoraTransacao;
  AdicionarIdentificacao;
  FinalizarRequisicao;

  LerRespostaRequisicao;
  Result := Resp.TransacaoAprovada;
  try
     ProcessarResposta ;         { Faz a Impress�o e / ou exibe Mensagem ao Operador }
  finally
     FinalizarResposta( True ) ; { True = Apaga Arquivo de Resposta }
  end;
end;

Procedure TACBrTEFDClass.CNF ;
begin
  CNF( Resp.Rede, Resp.NSU, Resp.Finalizacao, Resp.DocumentoVinculado ) ;
end;

Procedure TACBrTEFDClass.CNF( Rede, NSU, Finalizacao : String;
  DocumentoVinculado : String = '') ;
begin
  IniciarRequisicao('CNF');
  Req.DocumentoVinculado := DocumentoVinculado;
  Req.Rede               := Rede;
  Req.NSU                := NSU;
  Req.Finalizacao        := Finalizacao;

  FinalizarRequisicao;
end;

procedure TACBrTEFDClass.NCN;
begin
  NCN( Resp.Rede, Resp.NSU, Resp.Finalizacao, Resp.ValorTotal,
       Resp.DocumentoVinculado );
end;

procedure TACBrTEFDClass.NCN(Rede, NSU, Finalizacao : String;
  Valor : Double = 0; DocumentoVinculado : String = '');
Var
  MsgStr : String ;
begin
  IniciarRequisicao('NCN');
  Req.DocumentoVinculado := DocumentoVinculado;
  Req.Rede               := Rede;
  Req.NSU                := NSU;
  Req.Finalizacao        := Finalizacao;

  FinalizarRequisicao;

  MsgStr := '' ;
  if Rede <> '' then
     MsgStr := MsgStr + 'Rede: '+Rede + sLineBreak;
  if NSU <> '' then
     MsgStr := MsgStr + 'NSU: '+NSU + sLineBreak;
  if Valor <> 0 then
     MsgStr := MsgStr + 'Valor: '+FormatFloat('0.00',Valor);

  MsgStr := '�ltima Transa��o TEF foi cancelada' +
            IfThen(MsgStr <> '' , sLineBreak+sLineBreak, '' ) +
            MsgStr ;

  { TEF Auttar deve emitir msg apenas no Final de todos Desfazimentos }
  if Tipo <> gpTefAuttar then
     TACBrTEFD(Owner).DoExibeMsg( opmOK, MsgStr ) ;
end;

procedure TACBrTEFDClass.AtivarGP;
begin
  if (GPExeName = '') then
     raise EACBrTEFDErro.Create(ACBrStr('Nome do execut�vel do Gerenciador Padr�o n�o definido'));

  VerificaInicializado;

  RunCommand( GPExeName );
  Sleep(2000);
  TACBrTEFD(Owner).RestaurarFocoAplicacao;
end;

procedure TACBrTEFDClass.IniciarRequisicao( AHeader : String;
   AID : Integer = 0);
begin
  if fpAguardandoResposta then
     raise Exception.Create( ACBrStr( 'Requisi��o anterior n�o concluida' ) ) ;

  GravaLog( Name +' IniciarRequisicao: '+AHeader );

  { Verificando se a Classe de TEF est� Inicializado }
  VerificaInicializado ;

  { Transa��o a ser enviada � pagamento ? (CRT ou CHQ) }
  if TransacaoEPagamento(AHeader) then
  begin
     { Para TEF DISCADO tradicional, se for MultiplosCartoes, precisa
       confirma a transa��o anterior antes de enviar uma nova }
     if TACBrTEFD(Owner).MultiplosCartoes then      // � multiplos cartoes ?
        ConfirmarTransacoesAnteriores;
  end;

  { VisaNET exige um ATV antes de cada transa��o }
  if (AHeader <> 'ATV') then
     VerificaAtivo;

  TACBrTEFD(Owner).EstadoReq := reqIniciando;

  { Limpando da Mem�ria os dados do Objeto de Requisicao e Resposta }
  Req.Clear;
  Resp.Clear ;

  ApagaEVerifica( ArqTemp );  // Apagando Arquivo Temporario anterior //
  ApagaEVerifica( ArqReq  );  // Apagando Arquivo de Requisicao anterior //
  ApagaEVerifica( ArqResp );  // Apagando Arquivo de Resposta anterior //
  ApagaEVerifica( ArqSTS  );  // Apagando Arquivo de Status anterior //

  if AID > 0 then
     fpIDSeq := AID
  else
     fpIDSeq := fpIDSeq + 1 ;

  Req.Header := AHeader;
  Req.ID     := fpIDSeq;
  Req.Conteudo.GravarArquivo( ArqTemp );
end;

procedure TACBrTEFDClass.FinalizarRequisicao;
Var
  TempoInicioEspera, TempoFimEspera : TDateTime ;
  Interromper : Boolean ;
begin
  VerificarIniciouRequisicao;

  with TACBrTEFD(Owner) do
  begin
     EstadoReq := reqCriandoArquivo;

     if Assigned( OnAntesFinalizarRequisicao ) then
        OnAntesFinalizarRequisicao( Self.Req );
  end ;

  GravaLog( Name +' FinalizarRequisicao: '+Req.Header+', Fechando arquivo: '+ArqTemp);
  Req.Conteudo.GravaInformacao(999,999,'0');
  Req.Conteudo.GravarArquivo( ArqTemp, True ); { True = DoFlushToDisk }

  // DEBUG, ATIVE o Log abaixo para gravar no Log uma copia do Arq.de Requisicao //
  //GravaLog( Req.Conteudo.Conteudo.Text );

  GravaLog( Name +' FinalizarRequisicao: '+Req.Header+', Renomeando: '+ArqTemp+' para: '+ArqReq);
  if not RenameFile( ArqTemp, ArqReq ) then
     raise EACBrTEFDArquivo.Create( ACBrStr( 'Erro ao Renomear:' + sLineBreak +
                                ArqTemp + 'para:' + sLineBreak + ArqReq ) ) ;

  TACBrTEFD(Owner).EstadoReq := reqAguardandoResposta;

  TempoInicioEspera := now ;
  TempoFimEspera    := IncSecond(TempoInicioEspera, EsperaSTS );
  Interromper       := False ;
  fpAguardandoResposta := True ;
  try
     GravaLog( Name +' FinalizarRequisicao: '+Req.Header+', Aguardando: '+ArqSTS );

     repeat
        Sleep( TACBrTEFD(Owner).EsperaSleep );  // Necess�rio Para n�o sobrecarregar a CPU //

        with TACBrTEFD(Owner) do
        begin
           if Assigned( OnAguardaResp ) then
              OnAguardaResp( ArqSTS, SecondsBetween(TempoFimEspera, Now), Interromper ) ;
        end;
     until FileExists( ArqSTS ) or ( now > TempoFimEspera ) or Interromper;

     GravaLog( Name +' FinalizarRequisicao: '+Req.Header+', Fim da Espera de: '+
               ArqSTS+' '+ifthen(FileExists( ArqSTS ),'Recebido','N�o recebido') );
  finally
     fpAguardandoResposta := False ;
     with TACBrTEFD(Owner) do
     begin
        if Assigned( OnAguardaResp ) then
           OnAguardaResp( ArqSTS, -1, Interromper ) ;
     end;
  end;

  if not FileExists( ArqSTS ) then
     raise EACBrTEFDGPNaoResponde.Create(
        ACBrStr( Format(CACBrTEFD_Erro_NaoAtivo, [Self.Name]) ) ) ;

  TACBrTEFD(Owner).EstadoReq := reqConferindoResposta;

  GravaLog( Name +' FinalizarRequisicao: '+Req.Header+', Verificando conteudo de: '+ArqSTS );
  Resp.LeArquivo( ArqSTS );

  DeleteFile( ArqTemp );
  DeleteFile( ArqSTS );

  if not VerificarRespostaRequisicao then
  begin
    DeleteFile( ArqResp );

    Req.Clear;
    Resp.Clear;

    raise EACBrTEFDSTSInvalido.Create( ACBrStr('Falha na comunica��o com o Gereciador Padr�o: '+Name) ) ;
  end;

  TACBrTEFD(Owner).EstadoReq := reqFinalizada;
end;

procedure TACBrTEFDClass.AdicionarIdentificacao;
var
  TemIdentificacao : Boolean ;
  Operacoes : String ;
begin
  TemIdentificacao := False;
  
  with TACBrTEFD(Owner) do
  begin
     if (Identificacao.NomeAplicacao + Identificacao.VersaoAplicacao <> '') then
     begin
        Req.Conteudo.GravaInformacao(701,000, Trim( Identificacao.NomeAplicacao + ' ' +
                                                    Identificacao.VersaoAplicacao ) ) ;
        TemIdentificacao := True;
     end;

     if (Identificacao.RazaoSocial <> '') then
     begin
        Req.Conteudo.GravaInformacao(716,000, Identificacao.RazaoSocial ) ;
        TemIdentificacao := True;
     end;

     Operacoes := '1';      // 1 = Suporta Saque, 2 = Suporta Desconto
     if Assigned( OnComandaECFSubtotaliza ) and (not AutoEfetuarPagamento) then
        Operacoes := '3';   // 1 + 2 = Suporta Saque e Desconto

     if TemIdentificacao then
        Req.Conteudo.GravaInformacao(706,000, Operacoes ) ;
  end;
end;

Function TACBrTEFDClass.VerificarRespostaRequisicao : Boolean ;
begin
  Result := True ;
  if Resp is TACBrTEFDRespTXT then
    with TACBrTEFDRespTXT( Resp ) do
      Result := (Header = Req.Header) and (ID = Req.ID) and TrailerOk ;
end;

procedure TACBrTEFDClass.LerRespostaRequisicao;
var
   TempoInicioEspera : Double;
   Interromper, OK   : Boolean;

begin
  VerificarIniciouRequisicao;
  Resp.Clear;

  if pos(Req.Header,'CNF|ATV') = 0 then      // Se nao for ATV ou CNF...
  begin
     with TACBrTEFD(Owner) do
     begin
        if TecladoBloqueado then             // Teclado est� bloqueado ?
           BloquearMouseTeclado( False );    // Desbloqueia o Teclado para o G.P. funcionar
     end;
  end;

  TACBrTEFD(Owner).EstadoResp  := respAguardandoResposta;
  Interromper := False ;
  OK          := False ;
  try
     while not (OK or Interromper) do
     begin
        TempoInicioEspera := now ;
        fpAguardandoResposta := True ;
        try
           GravaLog( Name +' LerRespostaRequisicao: '+Req.Header+', Aguardando: '+ArqResp );

           repeat
              Sleep( TACBrTEFD(Owner).EsperaSleep );  // Necess�rio Para n�o sobrecarregar a CPU //

              with TACBrTEFD(Owner) do
              begin
                 if Assigned( OnAguardaResp ) then
                    OnAguardaResp( ArqResp, SecondsBetween(TempoInicioEspera, Now),
                                   Interromper ) ;
              end ;
           until FileExists( ArqResp ) or Interromper ;

           GravaLog( Name +' LerRespostaRequisicao: '+Req.Header+', Fim da Espera de: '+
                     ArqResp+' '+ifthen(FileExists( ArqResp ),'Recebido','N�o recebido') );
        finally
           fpAguardandoResposta := False ;
           with TACBrTEFD(Owner) do
           begin
              if Assigned( OnAguardaResp ) then
                 OnAguardaResp( ArqResp, -1, Interromper ) ;
           end ;
        end;

        GravaLog( Name +' LerRespostaRequisicao: '+Req.Header+', Verificando conteudo de: '+ArqResp );
        Resp.LeArquivo( ArqResp );
        Ok := VerificarRespostaRequisicao ;

        if not Ok then
        begin
           GravaLog( Name +' LerRespostaRequisicao: '+Req.Header+', Arquivo inv�lido desprezado: "'+ArqResp+'"'+sLineBreak +
                     Resp.Conteudo.Conteudo.Text );
           Resp.Clear;
           DeleteFile( ArqResp );
        end ;

        // DEBUG, ATIVE o Log abaixo para gravar no Log uma copia do Arq.Recebido //
        //GravaLog( Resp.Conteudo.Conteudo.Text );

     end ;
  finally
    Resp.TipoGP := Tipo;
    TACBrTEFD(Owner).EstadoResp := respNenhum;
    DeleteFile( ArqReq );  // Apaga a Requisicao (caso o G.P. nao tenha apagado)
  end ;
end;

procedure TACBrTEFDClass.ProcessarResposta ;
var
   RespostaPendente: TACBrTEFDRespTXT;
begin
  VerificarIniciouRequisicao;

  GravaLog( Name +' ProcessarResposta: '+Req.Header );

  TACBrTEFD(Owner).EstadoResp := respProcessando;

  if Resp.QtdLinhasComprovante > 0 then
   begin
      { Cria c�pia do Objeto Resp, e salva no ObjectList "RespostasPendentes" }
      RespostaPendente := TACBrTEFDRespTXT.Create ;
      try
         RespostaPendente.Assign( Resp );
         TACBrTEFD(Owner).RespostasPendentes.Add( RespostaPendente );

         ImprimirRelatorio ;

         with TACBrTEFD(Owner) do
         begin
            if Assigned( OnDepoisConfirmarTransacoes ) then
               OnDepoisConfirmarTransacoes( RespostasPendentes );
         end ;
      finally
         TACBrTEFD(Owner).RespostasPendentes.Clear;
      end;
   end
  else
     if Resp.TextoEspecialOperador <> '' then
        TACBrTEFD(Owner).DoExibeMsg( opmOK, Resp.TextoEspecialOperador )
end;

procedure TACBrTEFDClass.FinalizarResposta( ApagarArqResp : Boolean );
begin
   TACBrTEFD(Owner).EstadoResp := respConcluida;

   GravaLog( Name +' FinalizarResposta: '+Req.Header );

   if ApagarArqResp then
      ApagaEVerifica( ArqResp );

   Req.Clear;
   Resp.Clear;
end;

function TACBrTEFDClass.CriarResposta(Tipo : TACBrTEFDTipo) : TACBrTEFDResp ;
begin
  Case Tipo of
    gpCliSiTef : Result := TACBrTEFDRespCliSiTef.Create;
    gpVeSPague : Result := TACBrTEFDRespVeSPague.Create;
  else
    Result := TACBrTEFDRespTXT.Create;
  end ;
end ;

procedure TACBrTEFDClass.CancelarTransacoesPendentesClass;
Var
  ArquivosVerficar    : TStringList ;
  RespostaCancela     : TACBrTEFDResp ;
  RespostasCanceladas : TObjectList ;
  I, Topo             : Integer;
  JaCancelado         : Boolean ;
  ArqMask             : String;
begin
  GravaLog( Name +' CancelarTransacoesPendentesClass ');

  ArquivosVerficar    := TStringList.Create;
  RespostasCanceladas := TObjectList.create(True);

  try
     ArquivosVerficar.Clear;
     RespostasCanceladas.Clear;

     { Achando Arquivos de Backup deste GP }
     ArqMask := TACBrTEFD(Owner).PathBackup + PathDelim + 'ACBr_' + Self.Name + '_*.tef' ;
     FindFiles( ArqMask, ArquivosVerficar, True );

     { Vamos processar primeiro os CNCs e ADMs, e as N�o Confirmadas }
     I    := ArquivosVerficar.Count-1 ;
     Topo := 0 ;
     while I > Topo do
     begin
        Resp.LeArquivo( ArquivosVerficar[ I ] );

        if (pos(Resp.Header, 'CNC,ADM') > 0) or (not Resp.CNFEnviado) then
         begin
           ArquivosVerficar.Move(I,Topo);
           Topo := Topo + 1;
         end
        else
           I := I - 1 ;
     end;

     { Adicionando Arquivo de Resposta deste GP (se ainda n�o foi apagado) }
     if FileExists( ArqResp ) then
        ArquivosVerficar.Add( ArqResp );

     { Enviando NCN ou CNC para todos os arquivos encontrados }
     while ArquivosVerficar.Count > 0 do
     begin
        if not FileExists( ArquivosVerficar[ 0 ] ) then
        begin
           ArquivosVerficar.Delete( 0 );
           Continue;
        end;

        Resp.LeArquivo( ArquivosVerficar[ 0 ] );

        {  Verificando se essa Resposta j� foi cancela em outro arquivo }
        JaCancelado := False ;
        I := 0 ;
        while (not JaCancelado) and (I < RespostasCanceladas.Count) do
        begin
           if (RespostasCanceladas[I] is TACBrTEFDRespTXT) or
              (Tipo = gpVeSPague) then
            begin
              with TACBrTEFDResp( RespostasCanceladas[I] ) do
              begin
                 JaCancelado := (Resp.Rede        = Rede)        and
                                (Resp.NSU         = NSU)         and
                                (Resp.Finalizacao = Finalizacao) and
                                (Resp.ValorTotal  = ValorTotal) ;
              end;
            end
           else
            begin
              with TACBrTEFDResp( RespostasCanceladas[I] ) do
              begin
                 JaCancelado := (Resp.DocumentoVinculado = DocumentoVinculado) ;
              end;
            end;

           Inc( I ) ;
        end;

        if not JaCancelado then
         begin
           { Criando c�pia da Resposta Atual }
           RespostaCancela := CriarResposta( Tipo );
           RespostaCancela.Assign( Resp );

           { Enviando NCN ou CNC }
           try
              with TACBrTEFD(Owner) do
              begin
                 if Assigned( OnAntesCancelarTransacao ) then
                 try
                    OnAntesCancelarTransacao( RespostaCancela ) ;
                 except
                    { Nao deixa exceptions em OnAntesCancelarTransacao interromper }
                 end;
              end;

              if Resp.CNFEnviado and (Resp.Header <> 'CHQ') then
               begin
                 if not CNC then
                    raise EACBrTEFDErro.Create('CNC nao efetuado') ;
               end
              else
                 NCN;

              DeleteFile( ArquivosVerficar[ 0 ] );
              ArquivosVerficar.Delete( 0 );

              { Adicionando na lista de Respostas Canceladas }
              RespostasCanceladas.Add( RespostaCancela );
           except
           end;
         end
        else
         begin
           DeleteFile( ArquivosVerficar[ 0 ] );
           ArquivosVerficar.Delete( 0 );
         end;
     end;

     { TEF Auttar deve emitir msg apenas no Final de todos Desfazimentos }
     if Tipo = gpTefAuttar then
        if RespostasCanceladas.count > 0 then
           TACBrTEFD(Owner).DoExibeMsg( opmOK, 'Transa��o TEF n�o Efetuada Reter o Cupom Fiscal!' ) ;
  finally
     ArquivosVerficar.Free;
     RespostasCanceladas.Free;
  end;
end;

procedure TACBrTEFDClass.ConfirmarTransacoesAnteriores;
var
   I : Integer;
begin
  GravaLog( 'ConfirmarTransacoesAnteriores' ) ;

  { Se for Multiplos Cartoes precisa confirmar a transa��o anterior antes de
    enviar uma Nova }

  I := 0 ;
  while I < TACBrTEFD(Owner).RespostasPendentes.Count do
  begin
     with TACBrTEFD(Owner).RespostasPendentes[I] do
     begin
       if (not CNFEnviado)  and                     // Ainda n�o confirmou ?
          (TipoGP = TACBrTEFD(Owner).GPAtual) and   // � do mesmo GP ?
          TransacaoEPagamento(Header) then          // � CRT ou CHQ ?
       begin
          Self.CNF( Rede, NSU, Finalizacao, DocumentoVinculado );
          CNFEnviado := True ;
          if ArqBackup <> '' then
             Conteudo.GravarArquivo( ArqBackup, True ) ;   { True = DoFlushToDisk }
          ApagaEVerifica( ArqRespPendente );
       end;
     end ;

     Inc( I ) ;
  end ;
end;

procedure TACBrTEFDClass.ImprimirRelatorio;
Var
  I : Integer;
  TempoInicio : TDateTime ;
  ImpressaoOk, RemoverMsg, GerencialAberto : Boolean ;
  Est : AnsiChar ;
  ArqBackup : String ;
  ImagemComprovante : TStringList ;
begin
  VerificarIniciouRequisicao;

  if Resp.QtdLinhasComprovante < 1 then
     exit ;

  GravaLog( Name +' ImprimirRelatorio: '+Req.Header );

  if fpSalvarArquivoBackup then
     CopiarResposta ;

  ImpressaoOk  := False ;
  RemoverMsg   := False ;
  TempoInicio  := now ;

  with TACBrTEFD( Owner ) do
  begin
     try
        BloquearMouseTeclado( True );

        while not ImpressaoOk do
        begin
           try
              try
                 Est := EstadoECF;

                 if Est <> 'L' then
                 begin
                    { Fecha Vinculado ou Gerencial ou Cupom, se ficou algum aberto por Desligamento }
                    case Est of
                      'C'         : ComandarECF( opeFechaVinculado );
                      'G','R'     : ComandarECF( opeFechaGerencial );
                      'V','P','N' : ComandarECF( opeCancelaCupom );
                    end;

                    if EstadoECF <> 'L' then
                       raise EACBrTEFDECF.Create( ACBrStr(CACBrTEFD_Erro_ECFNaoLivre) ) ;
                 end;

                 GerencialAberto := False ;
                 TempoInicio     := now ;

                 if Self.Resp.TextoEspecialOperador <> '' then
                 begin
                    RemoverMsg := True ;
                    DoExibeMsg( opmExibirMsgOperador, Self.Resp.TextoEspecialOperador ) ;
                 end;

                 if Self.Resp.TextoEspecialCliente <> '' then
                 begin
                    RemoverMsg := True ;
                    DoExibeMsg( opmExibirMsgCliente, Self.Resp.TextoEspecialCliente ) ;
                 end;

                 I := 1 ;
                 while I <= self.NumVias do
                 begin
                   if I = 1 then
                      ImagemComprovante := Self.Resp.ImagemComprovante1aVia
                   else
                      ImagemComprovante := Self.Resp.ImagemComprovante2aVia ;

                   if ImagemComprovante.Count > 0 then
                   begin
                     if not GerencialAberto then
                      begin
                        ComandarECF( opeAbreGerencial ) ;
                        GerencialAberto := True;
                      end
                     else
                      begin
                        if I <> 1 then 
                           ComandarECF( opePulaLinhas ) ;
                        DoExibeMsg( opmDestaqueVia,
                                    Format( CACBrTEFD_DestaqueVia, [I]) ) ;
                      end ;

                     ECFImprimeVia( trGerencial, I, ImagemComprovante  )
                   end ;

                   Inc( I ) ;
                 end;

                 if GerencialAberto then
                    ComandarECF( opeFechaGerencial );

                 ImpressaoOk := True ;

              finally
                 { Removendo a mensagem do Operador }
                 if RemoverMsg then
                 begin
                    { Verifica se Mensagem Ficou pelo menos por 5 segundos }
                    if ImpressaoOk then
                    begin
                       while SecondsBetween(now,TempoInicio) < 5 do
                       begin
                          Sleep(EsperaSleep);
                          {$IFNDEF NOGUI}
                          Application.ProcessMessages;
                          {$ENDIF}
                       end;
                    end;

                    DoExibeMsg( opmRemoverMsgOperador, '' ) ;
                    DoExibeMsg( opmRemoverMsgCliente, '' ) ;
                 end;
              end;

           except
              on EACBrTEFDECF do
                 ImpressaoOk := False ;
              else
                 raise ;
           end;

           if not ImpressaoOk then
           begin
             if DoExibeMsg( opmYesNo, CACBrTEFD_Erro_ECFNaoResponde ) <> mrYes then
                break ;
           end;
        end;
     finally
       { Enviando CNF ou NCN e apagando Arquivo de Backup }
       ArqBackup := Resp.ArqBackup ;
       while FileExists( ArqBackup ) do
       begin
          try
             if ImpressaoOk then
                self.CNF
             else
                self.NCN ;
          except
          end;

          DeleteFile( ArqBackup ) ;
       end ;

       BloquearMouseTeclado( False );
     end ;

     if not ImpressaoOk then
        raise EACBrTEFDECF.Create( ACBrStr('Impress�o de Relat�rio Falhou' ) ) ;
  end;
end;

procedure TACBrTEFDClass.ConfirmarESolicitarImpressaoTransacoesPendentes;
Var
  ArquivosVerficar : TStringList ;
  ArqMask, NSUs    : AnsiString;
  ExibeMsg         : Boolean ;
  RespostaConfirmada : TACBrTEFDResp ;
begin
  ArquivosVerficar := TStringList.Create;

  try
     ArquivosVerficar.Clear;
     TACBrTEFD(Owner).RespostasPendentes.Clear;

     { Achando Arquivos de Backup deste GP }
     ArqMask  := TACBrTEFD(Owner).PathBackup + PathDelim + 'ACBr_' + Self.Name + '_*.tef' ;
     FindFiles( ArqMask, ArquivosVerficar, True );
     NSUs     := '' ;
     ExibeMsg := (ArquivosVerficar.Count > 0) ;

     { Enviando NCN ou CNC para todos os arquivos encontrados }
     while ArquivosVerficar.Count > 0 do
     begin
        if not FileExists( ArquivosVerficar[ 0 ] ) then
        begin
           ArquivosVerficar.Delete( 0 );
           Continue;
        end;

        Resp.LeArquivo( ArquivosVerficar[ 0 ] );

        try
           CNF;   {Confirma}

           { Criando c�pia da Resposta Atual }
           RespostaConfirmada := CriarResposta( Resp.TipoGP );
           RespostaConfirmada.Assign( Resp );
           TACBrTEFD(Owner).RespostasPendentes.Add( RespostaConfirmada );

           if Trim(Resp.NSU) <> '' then
              NSUs := NSUs + Resp.NSU + sLineBreak;

           SysUtils.DeleteFile( ArquivosVerficar[ 0 ] );
           ArquivosVerficar.Delete( 0 );
        except
        end;
     end;

     // Chamando evento de confirma��o das Respostas //
     with TACBrTEFD(Owner) do
     begin
       try
          if Assigned( OnDepoisConfirmarTransacoes ) and
             (RespostasPendentes.Count > 0) then
             OnDepoisConfirmarTransacoes( RespostasPendentes );
       finally
          RespostasPendentes.Clear;
       end;
     end;

     if ExibeMsg then
        TACBrTEFD(Owner).DoExibeMsg( opmOK,
           Format( CACBrTEFD_CliSiTef_TransacaoEfetuadaReImprimir, [NSUs] ) ) ;
  finally
     ArquivosVerficar.Free;
  end;
end;

Function TACBrTEFDClass.CopiarResposta : String;
Var
   I : Integer ;
begin
  Result := '' ;
//VerificarIniciouRequisicao;

  with TACBrTEFD(Owner) do
  begin
     I := 1 ;
     repeat
        Result := PathBackup + PathDelim + 'ACBr_' +
                  Self.Name + '_' + IntToStrZero(I,3) + '.tef' ;
        Inc( I ) ;
     until not FileExists( Result );

     GravaLog( Name + ' CopiarResposta: '+Resp.Header+' - '+IntToStr(Resp.ID)+
                      ' Arq: '+Result);

     Resp.Conteudo.GravarArquivo( Result, True );   { True = DoFlushToDisk }
     Resp.ArqBackup := Result ;
  end;
end;

Function TACBrTEFDClass.ProcessarRespostaPagamento( const IndiceFPG_ECF : String;
   const Valor : Double) : Boolean ;
var
  UltimaTransacao, ImpressaoOk, TecladoEstavaLivre : Boolean;
  RespostaPendente : TACBrTEFDRespTXT;
begin
  Result := False ;

  LerRespostaRequisicao;

  GravaLog( Name +' ProcessarRespostaPagamento: '+Resp.Header+' - '+IntToStr(Resp.ID)+
            ' Indice: '+IndiceFPG_ECF+' Valor:'+FormatFloat('0.00',Valor) );

  Result := Resp.TransacaoAprovada ;

  with TACBrTEFD(Owner) do
  begin
     UltimaTransacao := (Valor >= RespostasPendentes.SaldoRestante );

     {Se a transa��o n�o foi aprovada, faz tratamento e sai}
     if not Self.Resp.TransacaoAprovada then
     begin
       ProcessarResposta;           { Exibe a Mensagem ao Operador }
       FinalizarResposta( True ) ;  { True = Apaga Arquivo de Resposta }

                               { Ja tem RespostasPendentes ? }
       if UltimaTransacao and ( RespostasPendentes.Count > 0 ) then
       begin
          if DoExibeMsg( opmYesNo, CACBrTEFD_Erro_OutraFormaPagamento ) <> mrYes then
          begin
             ComandarECF( opeCancelaCupom );
             CancelarTransacoesPendentes;
          end;
       end;

       exit ;
     end ;

     {...Se est� aqui, ent�o a Transa��o foi aprovada...}

     Self.Resp.IndiceFPG_ECF := IndiceFPG_ECF;

     { Cria Arquivo de Backup, contendo inclusive informa��es internas como :
       899 - 001 : CNFEnviado (S, N)
       899 - 002 : IndiceFPG_ECF : String
       899 - 003 : OrdemPagamento : Integer
     }
     CopiarResposta ;

     { Cria c�pia do Objeto Resp, e salva no ObjectList "RespostasPendentes" }
     RespostaPendente := TACBrTEFDRespTXT.Create ;
     RespostaPendente.ArqRespPendente := ArqResp;
     RespostaPendente.Assign( Resp );
     RespostasPendentes.Add( RespostaPendente );

     ImpressaoOk        := True;
     TecladoEstavaLivre := True ;

     {Efetuar o pagamento automaticamente?}
     if AutoEfetuarPagamento then
     begin
        try //Faz tratamento para caso ocorra erro na impressora durante impress�o
           ImpressaoOk := False ;

           while not ImpressaoOk do
           begin
              try
                 try
                    TecladoEstavaLivre := not TecladoBloqueado;
                    BloquearMouseTeclado( True );
                    ECFPagamento( IndiceFPG_ECF, Valor );
                    RespostasPendentes.SaldoAPagar  := RoundTo( RespostasPendentes.SaldoAPagar - Valor, -2 ) ;
                    if (RespostaPendente.Header = 'CHQ') and CHQEmGerencial then
                       RespostaPendente.OrdemPagamento := 999
                    else
                       RespostaPendente.OrdemPagamento := RespostasPendentes.Count + 1 ;
                    ImpressaoOk := True ;
                 finally
                    if TecladoEstavaLivre then
                       BloquearMouseTeclado( False );
                  end ;
              except
                 on EACBrTEFDECF do
                    ImpressaoOk := False ;
                 else
                    raise ;
              end;

              if not ImpressaoOk then
              begin
                 if DoExibeMsg( opmYesNo, CACBrTEFD_Erro_ECFNaoResponde ) <> mrYes then
                 begin
                    try ComandarECF(opeCancelaCupom); except {Exce��o Muda} end ;
                    break ;
                 end;
              end;
           end;
        finally
           if not ImpressaoOk then
              CancelarTransacoesPendentes;
        end; //end do "if AutoEfetuarPagamento try ..."
     end;

     if not ImpressaoOk then exit ;

     FinalizarResposta( False );    { False = NAO Apaga Arquivo de Resposta }

     if AutoFinalizarCupom and (RespostasPendentes.SaldoRestante <= 0) then
     begin
        FinalizarCupom( False );  { False n�o desbloqueia o MouseTeclado }
        ImprimirTransacoesPendentes;
     end ;
  end;
end;

procedure TACBrTEFDClass.VerificarIniciouRequisicao;
begin
  if Req.Header = '' then
     raise EACBrTEFDErro.Create( ACBrStr( CACBrTEFD_Erro_SemRequisicao ) ) ;
end;

Procedure TACBrTEFDClass.VerificarTransacaoPagamento(Valor : Double );
var
  SaldoAPagar : Double ;
begin
  Valor := RoundTo( Valor, -2);

  if (Valor <= 0) then
     raise Exception.Create( ACBrStr( 'Valor inv�lido' ) );

  { Lendo o SubTotal do ECF }
  with TACBrTEFD(Owner) do
  begin
    if not (EstadoECF in ['V','P','N']) then
       raise Exception.Create(
          ACBrStr('ECF deve estar em Estado de "Venda", "Pagamento" ou "N�o Fiscal"') );

    SaldoAPagar := InfoECFAsDouble(ineSubTotal) ;
    SaldoAPagar := SaldoAPagar - InfoECFAsDouble(ineTotalAPagar,0);
    RespostasPendentes.SaldoAPagar := SaldoAPagar ;

    if TrocoMaximo <= 0 then
     begin
       if (Valor > RespostasPendentes.SaldoRestante ) then
          raise Exception.Create( ACBrStr( 'Opera��o TEF deve ser limitada ao '+
                                           'Saldo restante a Pagar' ) );
     end
    else
     begin
       if (Valor > RespostasPendentes.SaldoRestante + TrocoMaximo ) then
          raise Exception.Create( ACBrStr( 'Opera��o TEF permite '+
                                           'Troco M�ximo de R$ '+FormatCurr('0.00',TrocoMaximo) ) );
     end ;

    if MultiplosCartoes and (NumeroMaximoCartoes > 0) and      // Tem multiplos Cartoes ?
       (RespostasPendentes.Count >= NumeroMaximoCartoes) then  // J� informou todos cart�es ?
       raise Exception.Create( ACBrStr( 'Multiplos Cart�es Limitado a '+
             IntToStr(NumeroMaximoCartoes)+' opera��es.' ) );

    if Self is TACBrTEFDClassTXT then   // Limita Saldo Restante se for derivado de TEF discado
    begin
      if MultiplosCartoes and (NumeroMaximoCartoes > 0) and   // Tem multiplos Cartoes ?
         (Valor <> RespostasPendentes.SaldoRestante) and      // Valor � diferente do Saldo Restante a Pagar ?
         ((NumeroMaximoCartoes - RespostasPendentes.Count) <= 1) then  // Est� no �ltimo cart�o ?
         raise Exception.Create( ACBrStr( 'Multiplos Cart�es Limitado a '+
               IntToStr(NumeroMaximoCartoes)+' opera��es.'+sLineBreak+
               'Esta Opera��o TEF deve ser igual ao Saldo a Pagar' ) );
    end ;
  end;
end;

function TACBrTEFDClass.TransacaoEPagamento(AHeader: String): Boolean;
begin
   Result := (pos(AHeader,'CRT|CHQ') > 0);
end;

procedure TACBrTEFDClass.SetArqReq(const AValue : String);
begin
  fArqReq := Trim( AValue ) ;
end;

procedure TACBrTEFDClass.SetArqResp(const AValue : String);
begin
  fArqResp := Trim( AValue ) ;
end;

procedure TACBrTEFDClass.SetArqSTS(const AValue : String);
begin
  fArqSTS := Trim( AValue ) ;
end;

procedure TACBrTEFDClass.SetArqTmp(const AValue : String);
begin
  fArqTmp := Trim( AValue ) ;
end;

procedure TACBrTEFDClass.SetGPExeName(const AValue : String);
begin
  fGPExeName := Trim( AValue ) ;
end;

procedure TACBrTEFDClass.SetNumVias(const AValue : Integer);
begin
   fpNumVias := AValue;
end;

procedure TACBrTEFDClass.GravaLog(AString : AnsiString);
begin
  if fArqLOG = '' then
     exit ;

  try
     WriteToTXT( fArqLOG, '-- '+FormatDateTime('dd/mm hh:nn:ss:zzz',now) +
                          ' - ' + AString, True);
  except
  end ;
end;

{ TACBrTEFDClasses }

procedure TACBrTEFDClassList.SetObject(Index : Integer; Item : TACBrTEFDClass);
begin
  inherited SetItem (Index, Item) ;
end;

function TACBrTEFDClassList.GetObject(Index : Integer) : TACBrTEFDClass;
begin
  Result := inherited GetItem(Index) as TACBrTEFDClass ;
end;

procedure TACBrTEFDClassList.Insert(Index : Integer; Obj : TACBrTEFDClass);
begin
  inherited Insert(Index, Obj);
end;

function TACBrTEFDClassList.Add(Obj : TACBrTEFDClass) : Integer;
begin
  Result := inherited Add(Obj) ;
end;


{ TACBrTEFDRespostasPendentes }

function TACBrTEFDRespostasPendentes.GetSaldoRestante : Double;
var
   I : Integer;
   TotalPagoENaoImpresso : Double ;
begin
  TotalPagoENaoImpresso := 0 ;
  For I := 0 to Count-1 do
  begin
     with TACBrTEFDResp(Items[I]) do
     begin
        if OrdemPagamento = 0 then  // Ainda nao imprimiu no ECF ?
           TotalPagoENaoImpresso := TotalPagoENaoImpresso +
                                    (ValorTotal - Saque + Desconto) ;
     end ;
  end;

  TotalPagoENaoImpresso := RoundTo( TotalPagoENaoImpresso, -2);

  Result := RoundTo( SaldoAPagar - TotalPagoENaoImpresso, -2) ;
end;

function TACBrTEFDRespostasPendentes.GetTotalPago : Double;
var
   I : Integer;
begin
  Result := 0 ;
  For I := 0 to Count-1 do
  begin
     with TACBrTEFDResp(Items[I]) do
     begin
        Result := Result + (ValorTotal - Saque);
     end;
  end;

  Result := RoundTo( Result, -2);
end;

function TACBrTEFDRespostasPendentes.GetTotalDesconto: Double;
var
   I : Integer;
begin
  Result := 0 ;
  For I := 0 to Count-1 do
  begin
     with TACBrTEFDResp(Items[I]) do
     begin
        Result := Result + Desconto;
     end;
  end;

  Result := RoundTo( Result, -2) * -1 ;
end;

procedure TACBrTEFDRespostasPendentes.SetObject(Index : Integer; Item : TACBrTEFDResp);
begin
  inherited SetItem (Index, Item) ;
end;

function TACBrTEFDRespostasPendentes.GetObject(Index : Integer ) : TACBrTEFDResp;
begin
   Result := inherited GetItem(Index) as TACBrTEFDResp ;
end;

function TACBrTEFDRespostasPendentes.Add(Obj : TACBrTEFDResp) : Integer;
begin
   Result := inherited Add(Obj) ;
end;

procedure TACBrTEFDRespostasPendentes.Insert(Index : Integer; Obj : TACBrTEFDResp);
begin
  inherited Insert(Index, Obj);
end;

end.


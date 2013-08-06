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

unit ACBrTEFDCliSiTef;

interface

uses
  Classes, SysUtils, ACBrTEFDClass, ACBrConsts
  {$IFNDEF NOGUI}
   {$IFDEF VisualCLX} ,QControls {$ELSE} ,Controls {$ENDIF}
  {$ENDIF};

Const
   CACBrTEFD_CliSiTef_ImprimeGerencialConcomitante = False ;
   CACBrTEFD_CliSiTef_PressioneEnter = 'PRESSIONE <ENTER>' ;
   CACBrTEFD_CliSiTef_TransacaoNaoEfetuada =
      'Transa��o n�o efetuada.'+sLineBreak+'Favor reter o Cupom' ;
   CACBrTEFD_CliSiTef_TransacaoEfetuadaReImprimir =
      'Transa��o TEF efetuada.'        + sLineBreak+
      'Favor reimprimir �ltimo Cupom.' + sLineBreak +
      'NSU: %s'                        + sLineBreak +
      '(Para Cielo utilizar os 6 �ltimos d�gitos.)';
   CACBrTEFD_CliSiTef_NaoInicializado = 'CliSiTEF n�o inicializado' ;
   CACBrTEFD_CliSiTef_NaoConcluido = 'Requisi��o anterior n�o concluida' ;
   CACBrTEFD_CliSiTef_Erro1  = 'Endere�o IP inv�lido ou n�o resolvido' ;
   CACBrTEFD_CliSiTef_Erro2  = 'C�digo da loja inv�lido' ;
   CACBrTEFD_CliSiTef_Erro3  = 'C�digo de terminal inv�lido' ;
   CACBrTEFD_CliSiTef_Erro6  = 'Erro na inicializa��o do TCP/IP' ;
   CACBrTEFD_CliSiTef_Erro7  = 'Falta de mem�ria' ;
   CACBrTEFD_CliSiTef_Erro8  = 'N�o encontrou a CliSiTef ou ela est� com problemas' ;
   CACBrTEFD_CliSiTef_Erro10 = 'O PinPad n�o est� devidamente configurado no arquivo CliSiTef.ini' ;
   CACBrTEFD_CliSiTef_Erro11 = 'Dados inv�lidos passados pela automa��o.';
   CACBrTEFD_CliSiTef_Erro12 = 'Modo seguro n�o ativo (poss�vel falta de configura��o no servidor SiTef do arquivo .cha).';
   CACBrTEFD_CliSiTef_Erro13 = 'Caminho da DLL inv�lido (o caminho completo das bibliotecas est� muito grande).';

{$IFDEF LINUX}
  CACBrTEFD_CliSiTef_Lib = 'libclisitef.so' ;
{$ELSE}
  CACBrTEFD_CliSiTef_Lib = 'CliSiTef32I.dll' ;
{$ENDIF}

type
  { TACBrTEFDRespCliSiTef }

  TACBrTEFDRespCliSiTef = class( TACBrTEFDResp )
  protected
    function GetTransacaoAprovada : Boolean; override;
  public
    procedure ConteudoToProperty; override;
    procedure GravaInformacao( const Identificacao : Integer;
      const Informacao : AnsiString );
  end;

  TACBrTEFDCliSiTefExibeMenu = procedure( Titulo : String; Opcoes : TStringList;
    var ItemSelecionado : Integer; var VoltarMenu : Boolean ) of object ;

  TACBrTEFDCliSiTefOperacaoCampo = (tcString, tcDouble, tcCMC7, tcBarCode) ;

  TACBrTEFDCliSiTefObtemCampo = procedure( Titulo : String;
    TamanhoMinimo, TamanhoMaximo : Integer ;
    TipoCampo : Integer; Operacao : TACBrTEFDCliSiTefOperacaoCampo;
    var Resposta : AnsiString; var Digitado : Boolean; var VoltarMenu : Boolean )
    of object ;

  { TACBrTEFDCliSiTef }

   TACBrTEFDCliSiTef = class( TACBrTEFDClass )
   private
      fIniciouRequisicao: Boolean;
      fReimpressao: Boolean; {Indica se foi selecionado uma reimpress�o no ADM}
      fCancelamento: Boolean; {Indica se foi selecionado Cancelamento no ADM}
      fCodigoLoja : AnsiString;
      fEnderecoIP : AnsiString;
      fNumeroTerminal : AnsiString;
      fOnExibeMenu : TACBrTEFDCliSiTefExibeMenu;
      fOnObtemCampo : TACBrTEFDCliSiTefObtemCampo;
      fOperacaoADM : Integer;
      fOperacaoATV : Integer;
      fOperacaoCHQ : Integer;
      fOperacaoCNC : Integer;
      fOperacaoCRT : Integer;
      fOperacaoReImpressao: Integer;
      fOperador : AnsiString;
      fParametrosAdicionais : TStringList;
      fRespostas: TStringList;
      fRestricoes : AnsiString;
      fDocumentosProcessados : AnsiString ;
      fPathDLL: string;

     xConfiguraIntSiTefInterativoEx : function (
                pEnderecoIP: PAnsiChar;
                pCodigoLoja: PAnsiChar;
                pNumeroTerminal: PAnsiChar;
                ConfiguraResultado: smallint;
                pParametrosAdicionais: PAnsiChar): integer;
               {$IFDEF LINUX} cdecl {$ELSE} stdcall {$ENDIF} ;

     xIniciaFuncaoSiTefInterativo : function (
                Modalidade: integer;
                pValor: PAnsiChar;
                pNumeroCuponFiscal: PAnsiChar;
                pDataFiscal: PAnsiChar;
                pHorario: PAnsiChar;
                pOperador: PAnsiChar;
                pRestricoes: PAnsiChar ): integer;
                {$IFDEF LINUX} cdecl {$ELSE} stdcall {$ENDIF} ;


     xFinalizaTransacaoSiTefInterativo : procedure (
                 smallint: Word;
                 pNumeroCuponFiscal: PAnsiChar;
                 pDataFiscal: PAnsiChar;
                 pHorario: PAnsiChar );
                 {$IFDEF LINUX} cdecl {$ELSE} stdcall {$ENDIF} ;


     xContinuaFuncaoSiTefInterativo : function (
                var ProximoComando: Integer;
                var TipoCampo: Integer;
                var TamanhoMinimo: smallint;
                var TamanhoMaximo: smallint;
                pBuffer: PAnsiChar;
                TamMaxBuffer: Integer;
                ContinuaNavegacao: Integer ): integer;
                {$IFDEF LINUX} cdecl {$ELSE} stdcall {$ENDIF} ;

     xEscreveMensagemPermanentePinPad: function(Mensagem:PAnsiChar):Integer;
     {$IFDEF LINUX} cdecl {$ELSE} stdcall {$ENDIF} ;           

     xObtemQuantidadeTransacoesPendentes: function(
        DataFiscal:AnsiString;
        NumeroCupon:AnsiString):Integer;
     {$IFDEF LINUX} cdecl {$ELSE} stdcall {$ENDIF} ;        
        
     procedure AvaliaErro(Sts : Integer);
     procedure FinalizarTransacao( Confirma : Boolean;
        DocumentoVinculado : AnsiString);
     procedure LoadDLLFunctions;
     procedure UnLoadDLLFunctions;
     procedure SetParametrosAdicionais(const AValue : TStringList) ;
   protected
     procedure SetNumVias(const AValue : Integer); override;

     Function FazerRequisicao( Funcao : Integer; AHeader : AnsiString = '';
        Valor : Double = 0; Documento : AnsiString = '';
        ListaRestricoes : AnsiString = '') : Integer ;
     Function ContinuarRequisicao( ImprimirComprovantes : Boolean ) : Integer ;

     procedure ProcessarResposta ; override;
     Function ProcessarRespostaPagamento( const IndiceFPG_ECF : String;
        const Valor : Double) : Boolean; override;

     procedure VerificarIniciouRequisicao; override;

     Function SuportaDesconto : Boolean ;
     Function HMS : String ;

   public
     property Respostas : TStringList read fRespostas ;
     property PathDLL: string read fPathDLL write fPathDLL;

     constructor Create( AOwner : TComponent ) ; override;
     destructor Destroy ; override;

     procedure Inicializar ; override;
     procedure DesInicializar ; override;

     procedure AtivarGP ; override;
     procedure VerificaAtivo ; override;

     Procedure ATV ; override;
     Function ADM : Boolean ; override;
     Function CRT( Valor : Double; IndiceFPG_ECF : String;
        DocumentoVinculado : String = ''; Moeda : Integer = 0 ) : Boolean; override;
     Function CHQ( Valor : Double; IndiceFPG_ECF : String;
        DocumentoVinculado : String = ''; CMC7 : String = '';
        TipoPessoa : AnsiChar = 'F'; DocumentoPessoa : String = '';
        DataCheque : TDateTime = 0; Banco   : String = '';
        Agencia    : String = ''; AgenciaDC : String = '';
        Conta      : String = ''; ContaDC   : String = '';
        Cheque     : String = ''; ChequeDC  : String = '';
        Compensacao: String = '' ) : Boolean ; override;
     Procedure NCN(Rede, NSU, Finalizacao : String;
        Valor : Double = 0; DocumentoVinculado : String = ''); override;
     Procedure CNF(Rede, NSU, Finalizacao : String;
        DocumentoVinculado : String = ''); override;
     Function CNC(Rede, NSU : String; DataHoraTransacao : TDateTime;
        Valor : Double) : Boolean; overload; override;
     Function DefineMensagemPermanentePinPad(Mensagem:AnsiString):Integer;
     Function ObtemQuantidadeTransacoesPendentes(Data:TDateTime;
        CupomFiscal:AnsiString):Integer;
   published
     property EnderecoIP     : AnsiString read fEnderecoIP     write fEnderecoIP ;
     property CodigoLoja     : AnsiString read fCodigoLoja     write fCodigoLoja ;
     property NumeroTerminal : AnsiString read fNumeroTerminal write fNumeroTerminal ;
     property Operador       : AnsiString read fOperador       write fOperador ;
     property ParametrosAdicionais : TStringList read fParametrosAdicionais
        write SetParametrosAdicionais ;
     property Restricoes : AnsiString read fRestricoes write fRestricoes ;
     property OperacaoATV : Integer read fOperacaoATV write fOperacaoATV
        default 111 ;
     property OperacaoADM : Integer read fOperacaoADM write fOperacaoADM
        default 110 ;
     property OperacaoCRT : Integer read fOperacaoCRT write fOperacaoCRT
        default 0 ;
     property OperacaoCHQ : Integer read fOperacaoCHQ write fOperacaoCHQ
        default 1 ;
     property OperacaoCNC : Integer read fOperacaoCNC write fOperacaoCNC
        default 200 ;
     property OperacaoReImpressao : Integer read fOperacaoReImpressao
        write fOperacaoReImpressao default 112 ;

     property OnExibeMenu : TACBrTEFDCliSiTefExibeMenu read fOnExibeMenu
        write fOnExibeMenu ;
     property OnObtemCampo : TACBrTEFDCliSiTefObtemCampo read fOnObtemCampo
        write fOnObtemCampo ;
   end;

implementation

Uses ACBrUtil, dateutils, StrUtils, ACBrTEFD, Math;

{ TACBrTEFDRespCliSiTef }

function TACBrTEFDRespCliSiTef.GetTransacaoAprovada : Boolean;
begin
   Result := True ;
end;

procedure TACBrTEFDRespCliSiTef.ConteudoToProperty;
var
   Linha : TACBrTEFDLinha ;
   I     : Integer;
   Parc  : TACBrTEFDRespParcela;
   LinStr: AnsiString ;
   wTipoOperacao: Integer;
   TemParcelas : Boolean ;
begin
   fpValorTotal := 0 ;
   fpImagemComprovante1aVia.Clear;
   fpImagemComprovante2aVia.Clear;
   fpDebito    := False;
   fpCredito   := False;
   TemParcelas := False;

   for I := 0 to Conteudo.Count - 1 do
   begin
     Linha  := Conteudo.Linha[I];
     LinStr := StringToBinaryString( Linha.Informacao.AsString );

     case Linha.Identificacao of
       // TODO: Mapear mais propriedades do CliSiTef //
       100 :
         begin
            fpModalidadePagto := LinStr;

            case StrToIntDef(Copy(fpModalidadePagto,1,2),0) of
              01 : fpDebito  := True;
              02 : fpCredito := True;
            end;

            wTipoOperacao:= StrToIntDef(Copy(fpModalidadePagto,3,2),0);
            {Tipo de Parcelamento}
            case wTipoOperacao of
               02 : fpParceladoPor:= parcLoja;
               03 : fpParceladoPor:= parcADM;
            end;

            case wTipoOperacao of
               00    : fpTipoOperacao:= opAvista;
               01    : fpTipoOperacao:= opPreDatado;
               02,03 : fpTipoOperacao:= opParcelado;
               else
                 fpTipoOperacao:= opOutras;
            end;
         end;
       101 : fpModalidadePagtoExtenso  := LinStr;
       102 : fpModalidadePagtoDescrita := LinStr;
       105 :
         begin
           fpDataHoraTransacaoComprovante  := Linha.Informacao.AsTimeStampSQL;
           fpDataHoraTransacaoHost         := fpDataHoraTransacaoComprovante ;
           fpDataHoraTransacaoLocal        := fpDataHoraTransacaoComprovante ;
         end;
       120 : fpAutenticacao                := LinStr;
       121 : fpImagemComprovante1aVia.Text := StringReplace( StringToBinaryString( Linha.Informacao.AsString ), #10, sLineBreak, [rfReplaceAll] );
       122 : fpImagemComprovante2aVia.Text := StringReplace( StringToBinaryString( Linha.Informacao.AsString ), #10, sLineBreak, [rfReplaceAll] );
       130 :
         begin
           fpSaque      := Linha.Informacao.AsFloat ;
           fpValorTotal := fpValorTotal + fpSaque ;
         end;
       131 : fpInstituicao                 := LinStr;
       133 : fpCodigoAutorizacaoTransacao  := Linha.Informacao.AsInteger;
       134 : fpNSU                         := LinStr;
       139 : fpValorEntradaCDC             := Linha.Informacao.AsFloat;
       140 : fpDataEntradaCDC              := Linha.Informacao.AsDate;
       141 : TemParcelas                   := True;
       156 : fpRede                        := LinStr;
       501 : fpTipoPessoa                  := AnsiChar(IfThen(Linha.Informacao.AsInteger = 0,'J','F')[1]);
       502 : fpDocumentoPessoa             := LinStr ;
       505 : fpQtdParcelas                 := Linha.Informacao.AsInteger ;
       506 : fpDataPreDatado               := Linha.Informacao.AsDate;
       511 : fpQtdParcelas                 := Linha.Informacao.AsInteger;  {Parcelas CDC - Neste caso o campo 505 n�o � retornado}
       515 : fpDataHoraTransacaoCancelada  := Linha.Informacao.AsDate ;
       516 : fpNSUTransacaoCancelada       := LinStr;
       527 : fpDataVencimento              := Linha.Informacao.AsDate ; {Data Vencimento}
       613 :
        begin
          fpCheque                         := copy(LinStr, 21, 6);
          fpCMC7                           := LinStr;
        end;
       626 : fpBanco                       := LinStr;
       627 : fpAgencia                     := LinStr;
       628 : fpAgenciaDC                   := LinStr;
       629 : fpConta                       := LinStr;
       630 : fpContaDC                     := LinStr;

       899 :  // Tipos de Uso Interno do ACBrTEFD
        begin
          case Linha.Sequencia of
              1 : fpCNFEnviado         := (UpperCase( Linha.Informacao.AsString ) = 'S' );
              2 : fpIndiceFPG_ECF      := Linha.Informacao.AsString ;
              3 : fpOrdemPagamento     := Linha.Informacao.AsInteger ;
            100 : fpHeader             := LinStr;
            101 : fpID                 := Linha.Informacao.AsInteger;
            102 : fpDocumentoVinculado := LinStr;
            103 : fpValorTotal         := fpValorTotal + Linha.Informacao.AsFloat;
          end;
        end;

       4029 :
        begin
          fpDesconto   := Linha.Informacao.AsFloat;
          fpValorTotal := fpValorTotal - fpDesconto;
        end;

     end;
   end ;

   fpQtdLinhasComprovante := max( fpImagemComprovante1aVia.Count,
                                  fpImagemComprovante2aVia.Count ) ;

   fpParcelas.Clear;
   if TemParcelas then
   begin
      for I := 1 to fpQtdParcelas do
      begin
         Parc := TACBrTEFDRespParcela.create;
         Parc.Vencimento := LeInformacao( 141, I).AsDate ;
         Parc.Valor      := LeInformacao( 142, I).AsFloat ;

         fpParcelas.Add(Parc);
      end;
   end;
end;

procedure TACBrTEFDRespCliSiTef.GravaInformacao(const Identificacao : Integer;
   const Informacao : AnsiString);
Var
  Sequencia : Integer ;
begin
  Sequencia := 0 ;

  if (Identificacao in [141,142]) then  // 141 - Data Parcela, 142 - Valor Parcela
  begin
    Sequencia := 1 ;
    while LeInformacao(Identificacao, Sequencia).AsString <> '' do
       Inc( Sequencia ) ;
  end;

  fpConteudo.GravaInformacao( Identificacao, Sequencia,
                              BinaryStringToString(Informacao) ); // Converte #10 para "\x0A"
end;


{ TACBrTEFDClass }

constructor TACBrTEFDCliSiTef.Create(AOwner : TComponent);
begin
  inherited Create(AOwner);

  fIniciouRequisicao := False;
  fReimpressao       := False;
  fCancelamento      := False;
  ArqReq    := '' ;
  ArqResp   := '' ;
  ArqSTS    := '' ;
  ArqTemp   := '' ;
  GPExeName := '' ;
  fpTipo    := gpCliSiTef;
  Name      := 'CliSiTef' ;

  fEnderecoIP     := '' ;
  fCodigoLoja     := '' ;
  fNumeroTerminal := '' ;
  fOperador       := '' ;
  fRestricoes     := '' ;

  fOperacaoATV         := 111 ; // 111 - Teste de comunica��o com o SiTef
  fOperacaoReImpressao := 112 ; // 112 - Menu Re-impress�o
  fOperacaoADM         := 110 ; // 110 - Abre o leque das transa��es Gerenciais
  fOperacaoCRT         := 0 ;   // A CliSiTef permite que o operador escolha a forma
                                // de pagamento atrav�s de menus
  fOperacaoCHQ         := 1 ;   // Cheque
  fOperacaoCNC         := 200 ; // 200 Cancelamento Normal: Inicia a coleta dos dados
                                // no ponto necess�rio para fazer o cancelamento de uma
                                // transa��o de d�bito ou cr�dito, sem ser necess�rio
                                // passar antes pelo menu de transa��es administrativas
  fDocumentosProcessados := '' ;

  fParametrosAdicionais := TStringList.Create;
  fRespostas            := TStringList.Create;

  xConfiguraIntSiTefInterativoEx    := nil ;
  xIniciaFuncaoSiTefInterativo      := nil ;
  xContinuaFuncaoSiTefInterativo    := nil ;
  xFinalizaTransacaoSiTefInterativo := nil ;
  xEscreveMensagemPermanentePinPad  := nil; 
  xObtemQuantidadeTransacoesPendentes := nil;

  fOnExibeMenu  := nil ;
  fOnObtemCampo := nil ;

  if Assigned( fpResp ) then
     fpResp.Free ;

  fpResp := TACBrTEFDRespCliSiTef.Create;
  fpResp.TipoGP := Tipo;
end;

function TACBrTEFDCliSiTef.DefineMensagemPermanentePinPad(Mensagem:AnsiString):Integer;
begin
   if Assigned(xEscreveMensagemPermanentePinPad) then  
      Result := xEscreveMensagemPermanentePinPad(PAnsiChar(Mensagem))
   else
      raise Exception.Create( ACBrStr( CACBrTEFD_CliSiTef_NaoInicializado ) ) ;
end;

destructor TACBrTEFDCliSiTef.Destroy;
begin
   fParametrosAdicionais.Free ;
   fRespostas.Free ;

   inherited Destroy;
end;

procedure TACBrTEFDCliSiTef.LoadDLLFunctions ;
 procedure CliSiTefFunctionDetect( FuncName: AnsiString; var LibPointer: Pointer ) ;
 var
 sLibName: string;
 begin
   if not Assigned( LibPointer )  then
   begin
     // Verifica se exite o caminho das DLLs
     sLibName := '';
     if Length(PathDLL) > 0 then
        sLibName := PathWithDelim(PathDLL);

     // Concatena o caminho se exitir mais o nome da DLL.
     sLibName := sLibName + CACBrTEFD_CliSiTef_Lib;

     if not FunctionDetect( sLibName, FuncName, LibPointer) then
     begin
        LibPointer := NIL ;
        raise Exception.Create( ACBrStr( 'Erro ao carregar a fun��o:'+FuncName+
                                         ' de: '+CACBrTEFD_CliSiTef_Lib ) ) ;
     end ;
   end ;
 end ;
begin
   CliSiTefFunctionDetect('ConfiguraIntSiTefInterativoEx', @xConfiguraIntSiTefInterativoEx);
   CliSiTefFunctionDetect('IniciaFuncaoSiTefInterativo', @xIniciaFuncaoSiTefInterativo);
   CliSiTefFunctionDetect('ContinuaFuncaoSiTefInterativo', @xContinuaFuncaoSiTefInterativo);
   CliSiTefFunctionDetect('FinalizaTransacaoSiTefInterativo', @xFinalizaTransacaoSiTefInterativo);
   CliSiTefFunctionDetect('EscreveMensagemPermanentePinPad',@xEscreveMensagemPermanentePinPad);
   CliSiTefFunctionDetect('ObtemQuantidadeTransacoesPendentes',@xObtemQuantidadeTransacoesPendentes);
end ;

procedure TACBrTEFDCliSiTef.UnLoadDLLFunctions;
var
   sLibName: String;
begin
  sLibName := '';
  if Length(PathDLL) > 0 then
     sLibName := PathWithDelim(PathDLL);

  UnLoadLibrary( sLibName + CACBrTEFD_CliSiTef_Lib );

  xConfiguraIntSiTefInterativoEx      := Nil;
  xIniciaFuncaoSiTefInterativo        := Nil;
  xContinuaFuncaoSiTefInterativo      := Nil;
  xFinalizaTransacaoSiTefInterativo   := Nil;
  xEscreveMensagemPermanentePinPad    := Nil;
  xObtemQuantidadeTransacoesPendentes := Nil;
end;

procedure TACBrTEFDCliSiTef.SetParametrosAdicionais(const AValue : TStringList
   ) ;
begin
   fParametrosAdicionais.Assign( AValue ) ;
end ;

procedure TACBrTEFDCliSiTef.SetNumVias(const AValue : Integer);
begin
   fpNumVias := 2;
end;

procedure TACBrTEFDCliSiTef.Inicializar;
Var
  Sts : Integer ;
  ParamAdic : AnsiString ;
  Erro : String;
  Est  : AnsiChar;
begin
  if Inicializado then exit ;

  if not Assigned( OnExibeMenu ) then
     raise Exception.Create( ACBrStr('Evento "OnExibeMenu" n�o programado' ) ) ;

  if not Assigned( OnObtemCampo ) then
     raise Exception.Create( ACBrStr('Evento "OnObtemCampo" n�o programado' ) ) ;

  LoadDLLFunctions;

  ParamAdic := StringReplace( ParametrosAdicionais.Text, sLineBreak, ';',
                              [rfReplaceAll] ) ;

  if (pos('VersaoAutomacaoCielo',ParamAdic) = 0) then
  begin
     if SuportaDesconto then
     begin
        if ParamAdic <> '' then
           ParamAdic := ParamAdic + ';' ;

        ParamAdic := ParamAdic + '[VersaoAutomacaoCielo='+
                     PadL( TACBrTEFD(Owner).Identificacao.SoftwareHouse, 8 ) + '10]';
     end ;
  end;

  GravaLog( '*** ConfiguraIntSiTefInterativoEx. EnderecoIP: '   +fEnderecoIP+
                                            ' CodigoLoja: '     +fCodigoLoja+
                                            ' NumeroTerminal: ' +fNumeroTerminal+
                                            ' Resultado: 0'     +
                                            ' ParametrosAdicionais: '+ParamAdic ) ;

  Sts := xConfiguraIntSiTefInterativoEx( PAnsiChar(fEnderecoIP),
                                         PAnsiChar(fCodigoLoja),
                                         PAnsiChar(fNumeroTerminal),
                                         0,
                                         PAnsiChar(ParamAdic) );
  Erro := '' ;
  Case Sts of
    1 :	Erro := CACBrTEFD_CliSiTef_Erro1;
    2 : Erro := CACBrTEFD_CliSiTef_Erro2;
    3 : Erro := CACBrTEFD_CliSiTef_Erro3;
    6 : Erro := CACBrTEFD_CliSiTef_Erro6;
    7 : Erro := CACBrTEFD_CliSiTef_Erro7;
    8 : Erro := CACBrTEFD_CliSiTef_Erro8;
   10 : Erro := CACBrTEFD_CliSiTef_Erro10;
   11 : Erro := CACBrTEFD_CliSiTef_Erro11;
   12 : Erro := CACBrTEFD_CliSiTef_Erro12;
   13 : Erro := CACBrTEFD_CliSiTef_Erro13;
  end;

  if Erro <> '' then
     raise EACBrTEFDErro.Create( ACBrStr( Erro ) ) ;

  GravaLog( Name +' Inicializado CliSiTEF' );

  try
     Est := TACBrTEFD(Owner).EstadoECF;
  except
     Est := 'O' ;
     { TODO: Criar arquivo de Status da Transa��o

         Se o ECF estiver desligado, ser� retornado 'O', o que far� o c�digo
       abaixo Cancelar Todas as Transa��es Pendentes, por�m, pelo Roteiro do
       TEF dedicado, � necess�rio confirmar a Transa��o se o Cupom foi
       finalizado com sucesso.
         Criar um arquivo de Status que seja atualizado no Fim do Cupom e no
       inicio do CCD, de maneira que seja poss�vel identificar o Status do
       Documento no ECF indepentende do mesmo estar ou n�o ligado

         Como alteranativa, � poss�vel implementar c�digo no Evento "OnInfoECF"
       para buscar o Status do Documento no Banco de dados da sua aplica��o, e
       responder diferente de 'O',   (Veja exemplo nos fontes do TEFDDemo)
     }
  end ;

  fpInicializado := True ;

  // Cupom Ficou aberto ?? Se SIM, Cancele tudo... //
  if (Est in ['V','P','N','O']) then
     CancelarTransacoesPendentesClass
  else
     // NAO, Cupom Fechado, Pode confirmar e Mandar aviso para re-imprimir //
     ConfirmarESolicitarImpressaoTransacoesPendentes ;
end;

procedure TACBrTEFDCliSiTef.DesInicializar;
begin
   UnLoadDLLFunctions ;

   inherited DesInicializar;
end;

procedure TACBrTEFDCliSiTef.AtivarGP;
begin
   raise Exception.Create( ACBrStr( 'CliSiTef n�o pode ser ativado localmente' )) ;
end;

procedure TACBrTEFDCliSiTef.VerificaAtivo;
begin
   {Nada a Fazer}
end;

Procedure TACBrTEFDCliSiTef.ATV ;
var
   Sts : Integer;
begin
  Sts := FazerRequisicao( fOperacaoATV, 'ATV', 0, HMS ) ;

  if Sts = 10000 then
     Sts := ContinuarRequisicao( CACBrTEFD_CliSiTef_ImprimeGerencialConcomitante ) ;

  if ( Sts <> 0 ) then
     AvaliaErro( Sts )
  else
     if not CACBrTEFD_CliSiTef_ImprimeGerencialConcomitante then
        ProcessarResposta;
end;

Function TACBrTEFDCliSiTef.ADM : Boolean;
var
   Sts : Integer;
begin
  Sts := FazerRequisicao( fOperacaoADM, 'ADM', 0, HMS, fRestricoes ) ;

  if Sts = 10000 then
     Sts := ContinuarRequisicao( CACBrTEFD_CliSiTef_ImprimeGerencialConcomitante ) ;

  Result := ( Sts = 0 ) ;

  if not Result then
     AvaliaErro( Sts )
  else
     if not CACBrTEFD_CliSiTef_ImprimeGerencialConcomitante then
        ProcessarResposta;
end;

Function TACBrTEFDCliSiTef.CRT( Valor : Double; IndiceFPG_ECF : String;
   DocumentoVinculado : String = ''; Moeda : Integer = 0 ) : Boolean;
var
  Sts : Integer;
  Restr : AnsiString ;
begin
  VerificarTransacaoPagamento( Valor );

  Restr := fRestricoes;
  if Restr = '' then
     Restr := '[10]' ;     // 10 - Cheques

  Sts := FazerRequisicao( fOperacaoCRT, 'CRT', Valor, DocumentoVinculado, Restr ) ;

  if Sts = 10000 then
     Sts := ContinuarRequisicao( False ) ;  { False = NAO Imprimir Comprovantes agora }

  Result := ( Sts = 0 ) ;

  if not Result then
     AvaliaErro( Sts )
  else
     ProcessarRespostaPagamento( IndiceFPG_ECF, Valor );
end;

Function TACBrTEFDCliSiTef.CHQ(Valor : Double; IndiceFPG_ECF : String;
   DocumentoVinculado : String; CMC7 : String; TipoPessoa : AnsiChar;
   DocumentoPessoa : String; DataCheque : TDateTime; Banco : String;
   Agencia : String; AgenciaDC : String; Conta : String; ContaDC : String;
   Cheque : String; ChequeDC : String; Compensacao: String) : Boolean ;
var
  Sts : Integer;
  Restr : AnsiString ;

  Function FormataCampo( Campo : AnsiString; Tamanho : Integer ) : AnsiString ;
  begin
    Result := padR( OnlyNumber( Trim( Campo ) ), Tamanho, '0') ;
  end ;

begin
  VerificarTransacaoPagamento( Valor );

  Respostas.Values['501'] := ifthen(TipoPessoa = 'J','1','0');

  if DocumentoPessoa <> '' then
     Respostas.Values['502'] := OnlyNumber(Trim(DocumentoPessoa));

  if DataCheque <> 0  then
     Respostas.Values['506'] := FormatDateTime('DDMMYYYY',DataCheque) ;

  if CMC7 <> '' then
     Respostas.Values['517'] := '1:'+CMC7
  else
     Respostas.Values['517'] := '0:'+FormataCampo(Compensacao,3)+
                                     FormataCampo(Banco,3)+
                                     FormataCampo(Agencia,4)+
                                     FormataCampo(AgenciaDC,1)+
                                     FormataCampo(Conta,10)+
                                     FormataCampo(ContaDC,1)+
                                     FormataCampo(Cheque,6)+
                                     FormataCampo(ChequeDC,1) ;

  Restr := fRestricoes;
  if Restr = '' then
     Restr := '[15;25]' ;  // 15 - Cart�o Credito; 25 - Cartao Debito

  Sts := FazerRequisicao( fOperacaoCHQ, 'CHQ', Valor, DocumentoVinculado, Restr ) ;

  if Sts = 10000 then
     Sts := ContinuarRequisicao( False ) ;  { False = NAO Imprimir Comprovantes agora }

  Result := ( Sts = 0 ) ;

  if not Result then
     AvaliaErro( Sts )
  else
     ProcessarRespostaPagamento( IndiceFPG_ECF, Valor );
end;

Procedure TACBrTEFDCliSiTef.CNF(Rede, NSU, Finalizacao : String;
   DocumentoVinculado : String) ;
begin
  // CliSiTEF n�o usa Rede, NSU e Finalizacao

  FinalizarTransacao( True, DocumentoVinculado );
end;

Function TACBrTEFDCliSiTef.CNC(Rede, NSU : String;
   DataHoraTransacao : TDateTime; Valor : Double) : Boolean;
var
   Sts : Integer;
begin
  Respostas.Values['146'] := FormatFloat('0.00',Valor);
  Respostas.Values['147'] := FormatFloat('0.00',Valor);
  Respostas.Values['515'] := FormatDateTime('DDMMYYYY',DataHoraTransacao) ;
  Respostas.Values['516'] := NSU ;

  Sts := FazerRequisicao( fOperacaoCNC, 'CNC', 0, HMS ) ;

  if Sts = 10000 then
     Sts := ContinuarRequisicao( CACBrTEFD_CliSiTef_ImprimeGerencialConcomitante ) ;

  Result := ( Sts = 0 ) ;

  if not Result then
     AvaliaErro( Sts )
  else
     if not CACBrTEFD_CliSiTef_ImprimeGerencialConcomitante then
        ProcessarResposta;
end;

Procedure TACBrTEFDCliSiTef.NCN(Rede, NSU, Finalizacao : String;
   Valor : Double; DocumentoVinculado : String) ;
begin
  // CliSiTEF n�o usa Rede, NSU, Finalizacao e Valor

  FinalizarTransacao( False, DocumentoVinculado );
end;

function TACBrTEFDCliSiTef.ObtemQuantidadeTransacoesPendentes(Data:TDateTime;
  CupomFiscal: AnsiString): Integer;
var
  sDate:AnsiString;
begin
   sDate:= FormatDateTime('yyyymmdd',Data);
   if Assigned(xObtemQuantidadeTransacoesPendentes) then  
      Result := xObtemQuantidadeTransacoesPendentes(sDate,CupomFiscal)
   else
      raise Exception.Create( ACBrStr( CACBrTEFD_CliSiTef_NaoInicializado ) ) ;
end;

Function TACBrTEFDCliSiTef.FazerRequisicao( Funcao : Integer;
   AHeader : AnsiString = ''; Valor : Double = 0; Documento : AnsiString = '';
   ListaRestricoes : AnsiString = '') : Integer ;
Var
  ValorStr, DataStr, HoraStr : AnsiString;
  ANow : TDateTime ;
begin
   if fpAguardandoResposta then
      raise Exception.Create( ACBrStr( CACBrTEFD_CliSiTef_NaoConcluido ) ) ;

   if (pos('{TipoTratamento=4}',ListaRestricoes) = 0) and
      (pos(AHeader,'CRT,CHQ') > 0 ) and
      SuportaDesconto then
   begin
      ListaRestricoes := ListaRestricoes + '{TipoTratamento=4}';
   end;

   fIniciouRequisicao := True;

   ANow     := Now ;
   DataStr  := FormatDateTime('YYYYMMDD', ANow );
   HoraStr  := FormatDateTime('HHNNSS', ANow );
   ValorStr := StringReplace( FormatFloat( '0.00', Valor ),
                              DecimalSeparator, ',', [rfReplaceAll]) ;
   fDocumentosProcessados := '' ;

   GravaLog( '*** IniciaFuncaoSiTefInterativo. Modalidade: '+IntToStr(Funcao)+
                                             ' Valor: '     +ValorStr+
                                             ' Documento: ' +Documento+
                                             ' Data: '      +DataStr+
                                             ' Hora: '      +HoraStr+
                                             ' Operador: '  +fOperador+
                                             ' Restricoes: '+ListaRestricoes ) ;

   Result := xIniciaFuncaoSiTefInterativo( Funcao,
                                           PAnsiChar( ValorStr ),
                                           PAnsiChar( Documento ),
                                           PAnsiChar( DataStr ),
                                           PAnsiChar( HoraStr ),
                                           PAnsiChar( fOperador ),
                                           PAnsiChar( ListaRestricoes ) ) ;

   { Adiciona Campos j� conhecidos em Resp, para processa-los em
     m�todos que manipulam "RespostasPendentes" (usa c�digos do G.P.)  }
   Resp.Clear;

   with TACBrTEFDRespCliSiTef( Resp ) do
   begin
     fpIDSeq := fpIDSeq + 1 ;
     if Documento = '' then
        Documento := IntToStr(fpIDSeq) ;

     Conteudo.GravaInformacao(899,100, AHeader ) ;
     Conteudo.GravaInformacao(899,101, IntToStr(fpIDSeq) ) ;
     Conteudo.GravaInformacao(899,102, Documento ) ;
     Conteudo.GravaInformacao(899,103, IntToStr(Trunc(SimpleRoundTo( Valor * 100 ,0))) );

     Resp.TipoGP := fpTipo;
   end;
end;

Function TACBrTEFDCliSiTef.ContinuarRequisicao( ImprimirComprovantes : Boolean )
  : Integer;
var
  ProximoComando, TipoCampo, Continua, ItemSelecionado, I: Integer;
  TamanhoMinimo, TamanhoMaximo : SmallInt ;
  Buffer: array [0..20000] of AnsiChar;
  Mensagem, MensagemOperador, MensagemCliente, CaptionMenu : String ;
  Resposta, ArqBackUp : AnsiString;
  SL : TStringList ;
  Interromper, Digitado, GerencialAberto, FechaGerencialAberto, ImpressaoOk,
     HouveImpressao, Voltar : Boolean ;
  Est : AnsiChar;

  function ProcessaMensagemTela( AMensagem : String ) : String ;
  begin
    Result := StringReplace( AMensagem, '@', sLineBreak, [rfReplaceAll] );
    Result := StringReplace( Result, '/n', sLineBreak, [rfReplaceAll] );
  end;

begin
   Result            := 0;
   ProximoComando    := 0;
   TipoCampo         := 0;
   TamanhoMinimo     := 0;
   TamanhoMaximo     := 0;
   Continua          := 0;
   Mensagem          := '' ;
   MensagemOperador  := '' ;
   MensagemCliente   := '' ;
   CaptionMenu       := '' ;
   GerencialAberto   := False ;
   ImpressaoOk       := True ;
   HouveImpressao    := False ;
   fIniciouRequisicao:= True ;
   fCancelamento     := False ;
   fReimpressao      := False;
   ArqBackUp         := '' ;
   Resposta          := '' ;

   fpAguardandoResposta := True ;
   FechaGerencialAberto := True ;

   with TACBrTEFD(Owner) do
   begin
      try
         BloquearMouseTeclado( True );

         repeat
            GravaLog( 'ContinuaFuncaoSiTefInterativo, Chamando: Continua = '+
                      IntToStr(Continua)+' Buffer = '+Resposta ) ;

            Result := xContinuaFuncaoSiTefInterativo( ProximoComando,
                                                      TipoCampo,
                                                      TamanhoMinimo, TamanhoMaximo,
                                                      Buffer, sizeof(Buffer),
                                                      Continua );
            Continua := 0;
            Mensagem := TrimRight( Buffer ) ;
            Resposta := '' ;
            Voltar   := False;
            Digitado := True ;

            GravaLog( 'ContinuaFuncaoSiTefInterativo, Retornos: STS = '+IntToStr(Result)+
                      ' ProximoComando = '+IntToStr(ProximoComando)+
                      ' TipoCampo = '+IntToStr(TipoCampo)+
                      ' Buffer = '+Mensagem +
                      ' Tam.Min = '+IntToStr(TamanhoMinimo) +
                      ' Tam.Max = '+IntToStr(TamanhoMaximo)) ;

            if Result = 10000 then
            begin
              if TipoCampo > 0 then
                 Resposta := fRespostas.Values[IntToStr(TipoCampo)];

              case ProximoComando of
                 0 :
                   begin
                     TACBrTEFDRespCliSiTef( Self.Resp ).GravaInformacao( TipoCampo, Mensagem ) ;

                     case TipoCampo of
                        15 : TACBrTEFDRespCliSiTef( Self.Resp ).GravaInformacao( TipoCampo, 'True') ;//Selecionou Debito;
                        25 : TACBrTEFDRespCliSiTef( Self.Resp ).GravaInformacao( TipoCampo, 'True') ;//Selecionou Credito;
                        {Indica que foi escolhido menu de reimpress�o}
                        56,57,58 : fReimpressao := True;
                        110      : fCancelamento:= True;
                        121, 122 :
                          if ImprimirComprovantes then
                          begin
                             { Impress�o de Gerencial, deve ser Sob demanda }
                             if not HouveImpressao then
                             begin
                                HouveImpressao := True ;
                                ArqBackUp      := CopiarResposta;
                             end;

                             SL := TStringList.Create;
                             ImpressaoOk := False ;
                             I := TipoCampo ;
                             try
                                while not ImpressaoOk do
                                begin
                                  try
                                    while I <= TipoCampo do
                                    begin
                                       if FechaGerencialAberto then
                                       begin
                                         Est := EstadoECF;

                                         { Fecha Vinculado ou Gerencial ou Cupom, se ficou algum aberto por Desligamento }
                                         case Est of
                                           'C'         : ComandarECF( opeFechaVinculado );
                                           'G','R'     : ComandarECF( opeFechaGerencial );
                                           'V','P','N' : ComandarECF( opeCancelaCupom );
                                         end;

                                         GerencialAberto      := False ;
                                         FechaGerencialAberto := False ;

                                         if EstadoECF <> 'L' then
                                           raise EACBrTEFDECF.Create( ACBrStr(CACBrTEFD_Erro_ECFNaoLivre) ) ;
                                       end;

                                       Mensagem := Self.Resp.LeInformacao(I).AsString ;
                                       Mensagem := StringToBinaryString( Mensagem ) ;
                                       if Mensagem <> '' then
                                       begin
                                          SL.Text  := StringReplace( Mensagem, #10, sLineBreak, [rfReplaceAll] ) ;

                                          if not GerencialAberto then
                                           begin
                                             ComandarECF( opeAbreGerencial ) ;
                                             GerencialAberto := True ;
                                           end
                                          else
                                           begin
                                             ComandarECF( opePulaLinhas ) ;
                                             DoExibeMsg( opmDestaqueVia,
                                                         Format(CACBrTEFD_DestaqueVia, [1]) ) ;
                                           end;

                                          ECFImprimeVia( trGerencial, I-120, SL );

                                          ImpressaoOk := True ;
                                       end;

                                       Inc( I ) ;
                                    end;

                                    if (TipoCampo = 122) and GerencialAberto then
                                    begin
                                       ComandarECF( opeFechaGerencial );
                                       GerencialAberto := False;
                                    end;
                                  except
                                    on EACBrTEFDECF do ImpressaoOk := False ;
                                    else
                                       raise ;
                                  end;

                                  if not ImpressaoOk then
                                  begin
                                    if DoExibeMsg( opmYesNo, CACBrTEFD_Erro_ECFNaoResponde ) <> mrYes then
                                      break ;

                                    I := 121 ;
                                    FechaGerencialAberto := True ;
                                  end;
                                end ;
                             finally
                                if not ImpressaoOk then
                                   Continua := -1 ;

                                SL.Free;
                             end;
                          end ;
                     end;
                   end;

                 1 :
                   begin
                     MensagemOperador := ProcessaMensagemTela( Mensagem );
                     DoExibeMsg( opmExibirMsgOperador, MensagemOperador ) ;
                   end ;

                 2 :
                   begin
                     MensagemCliente := ProcessaMensagemTela( Mensagem );
                     DoExibeMsg( opmExibirMsgCliente, MensagemCliente ) ;
                   end;

                 3 :
                   begin
                     MensagemOperador := ProcessaMensagemTela( Mensagem );
                     MensagemCliente  := MensagemOperador;
                     DoExibeMsg( opmExibirMsgOperador, MensagemOperador ) ;
                     DoExibeMsg( opmExibirMsgCliente, MensagemCliente ) ;
                   end ;

                 4 : CaptionMenu := ACBrStr( Mensagem ) ;

                 11 :
                   begin
                     MensagemOperador := '' ;
                     DoExibeMsg( opmRemoverMsgOperador, '' ) ;
                   end;

                 12 :
                   begin
                     MensagemCliente := '' ;
                     DoExibeMsg( opmRemoverMsgCliente, '' ) ;
                   end;

                 13 :
                   begin
                     DoExibeMsg( opmRemoverMsgOperador, '' ) ;
                     DoExibeMsg( opmRemoverMsgCliente, '' ) ;
                   end ;

                 14 : CaptionMenu := '' ;
                   {Deve limpar o texto utilizado como cabe�alho na apresenta��o do menu}

                 20 :
                   begin
                     if Mensagem = '' then
                        Mensagem := 'CONFIRMA ?';

                     Resposta := ifThen( (DoExibeMsg( opmYesNo, Mensagem ) = mrYes), '0', '1' ) ;
                     {Digitado := ( Resposta <> '1') ;}
                   end ;

                 21 :
                   begin
                     SL := TStringList.Create;
                     try
                        ItemSelecionado := -1 ;
                        SL.Text := StringReplace( Mensagem, ';',
                                                         sLineBreak, [rfReplaceAll] ) ;
                        BloquearMouseTeclado(False);
                        OnExibeMenu( CaptionMenu, SL, ItemSelecionado, Voltar ) ;
                        BloquearMouseTeclado(True);

                        if (not Voltar) then
                        begin
                           if (ItemSelecionado >= 0) and
                              (ItemSelecionado < SL.Count) then
                              Resposta := copy( SL[ItemSelecionado], 1,
                                             pos(':',SL[ItemSelecionado])-1 )
                           else
                              Digitado := False ;
                        end;
                     finally
                        SL.Free ;
                     end ;
                   end;

                 22 :
                   begin
                     if Mensagem = '' then
                        Mensagem := CACBrTEFD_CliSiTef_PressioneEnter;

                     DoExibeMsg( opmOK, Mensagem );
                   end ;

                 23 :
                   begin
                     Interromper := False ;
                     OnAguardaResp( '23', 0, Interromper ) ;
                     if Interromper then
                        Continua := -1 ;
                   end;

                 30 :
                   begin
                     BloquearMouseTeclado(False);
                     OnObtemCampo( ACBrStr(Mensagem), TamanhoMinimo, TamanhoMaximo,
                                   TipoCampo, tcString, Resposta, Digitado, Voltar) ;
                     {Se o tipo campo for 505 � a quantidade de parcelas}
                     TACBrTEFDRespCliSiTef( Self.Resp ).GravaInformacao( TipoCampo, Resposta ) ;
                     BloquearMouseTeclado(True);
                   end;

                 31 :
                   begin
                     BloquearMouseTeclado(False);
                     OnObtemCampo( ACBrStr(Mensagem), TamanhoMinimo, TamanhoMaximo,
                                   TipoCampo, tcCMC7, Resposta, Digitado, Voltar ) ;
                     BloquearMouseTeclado(True);
                   end;

                 34 :
                   begin
                     BloquearMouseTeclado(False);
                     OnObtemCampo( ACBrStr(Mensagem), TamanhoMinimo, TamanhoMaximo,
                                   TipoCampo, tcDouble, Resposta, Digitado, Voltar ) ;
                     BloquearMouseTeclado(True);

                     // Garantindo que a Resposta � Float //
                     Resposta := FormatFloat('0.00', StringToFloatDef(Resposta, 0));
                     // Garantindo que o Seprador de Decimal � a Virgula //
                     if DecimalSeparator <> ',' then
                        Resposta := StringReplace( Resposta, DecimalSeparator, ',', [] );
                   end;

                 35 :
                   begin
                     BloquearMouseTeclado(False);
                     OnObtemCampo( ACBrStr(Mensagem), TamanhoMinimo, TamanhoMaximo,
                                   TipoCampo, tcBarCode, Resposta, Digitado, Voltar ) ;
                     BloquearMouseTeclado(True);
                   end;

              end;
            end
            else
               GravaLog( '*** ContinuaFuncaoSiTefInterativo, Finalizando: STS = '+IntToStr(Result) ) ;

            if Voltar then
               Continua := 1     { Volta para o menu anterior }
            else if not Digitado then
               Continua := -1 ;  { Cancela operacao }

            if (Voltar and (Result = 10000)) or (not Digitado) then
            begin
              DoExibeMsg( opmRemoverMsgOperador, '' ) ;
              DoExibeMsg( opmRemoverMsgCliente, '' ) ;
            end ;

            StrPCopy(Buffer, Resposta);

         until Result <> 10000;
      finally
         if GerencialAberto then
         try
            ComandarECF( opeFechaGerencial );
         except
            ImpressaoOk := False ;
         end;

         if (ArqBackUp <> '') and FileExists( ArqBackUp ) then
            SysUtils.DeleteFile( ArqBackUp );

         if HouveImpressao or ( ImprimirComprovantes and fCancelamento) then
            FinalizarTransacao( ImpressaoOk, Resp.DocumentoVinculado );

         BloquearMouseTeclado( False );

         { Transfere valore de "Conteudo" para as propriedades }
         // DEBUG
         //GravaLog( Self.Resp.Conteudo.Conteudo.Text );
         TACBrTEFDRespCliSiTef( Self.Resp ).ConteudoToProperty ;

         if (HouveImpressao and fCancelamento) then
            DoExibeMsg( opmOK,
                        Format( CACBrTEFD_CliSiTef_TransacaoEfetuadaReImprimir,
                                [Resp.NSU]) ) ;

         fpAguardandoResposta := False ;
      end;
   end ;
end;

procedure TACBrTEFDCliSiTef.ProcessarResposta;
var
   RespostaPendente: TACBrTEFDRespCliSiTef;
begin
  GravaLog( Name +' ProcessarResposta: '+Req.Header );

  TACBrTEFD(Owner).EstadoResp := respProcessando;

  if Resp.QtdLinhasComprovante > 0 then
  begin
      { Cria c�pia do Objeto Resp, e salva no ObjectList "RespostasPendentes" }
      RespostaPendente := TACBrTEFDRespCliSiTef.Create ;
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
  end ;
end;

procedure TACBrTEFDCliSiTef.FinalizarTransacao( Confirma : Boolean;
   DocumentoVinculado : AnsiString);
Var
   DataStr, HoraStr : AnsiString;
   Finalizacao : Integer;
begin
   fRespostas.Clear;
   fIniciouRequisicao := False;

   { Re-Impress�o n�o precisa de Finaliza��o }
   if fReimpressao then
      exit ;

   { J� Finalizou este Documento por outra Transa��o ? }
   if (pos(DocumentoVinculado, fDocumentosProcessados) > 0) then
      exit;

  fDocumentosProcessados := fDocumentosProcessados + DocumentoVinculado + '|' ;

  DataStr     := FormatDateTime('YYYYMMDD',Now);
  HoraStr     := FormatDateTime('HHNNSS',Now);
  Finalizacao := ifthen(Confirma or fCancelamento,1,0);

  GravaLog( '*** FinalizaTransacaoSiTefInterativo. Confirma: '+
                                          IfThen(Finalizacao = 1,'SIM','NAO')+
                                          ' Documento: ' +DocumentoVinculado+
                                          ' Data: '      +DataStr+
                                          ' Hora: '      +HoraStr ) ;

  xFinalizaTransacaoSiTefInterativo( Finalizacao,
                                     PAnsiChar( DocumentoVinculado ),
                                     PAnsiChar( DataStr ),
                                     PAnsiChar( HoraStr ) ) ;

  if not Confirma then
  begin
     if fCancelamento  then
        TACBrTEFD(Owner).DoExibeMsg( opmOK,
                    Format( CACBrTEFD_CliSiTef_TransacaoEfetuadaReImprimir,
                            [Resp.NSU]) )
     else
        TACBrTEFD(Owner).DoExibeMsg( opmOK, CACBrTEFD_CliSiTef_TransacaoNaoEfetuada );
  end;

end;

procedure TACBrTEFDCliSiTef.AvaliaErro( Sts : Integer );
//var
//   Erro : String;
begin
(*
   Erro := '' ;
   Case Sts of
        -1 : Erro := 'M�dulo n�o inicializado' ;
        -2 : Erro := 'Opera��o cancelada pelo operador' ;
        -3 : Erro := 'Fornecida uma modalidade inv�lida' ;
        -4 : Erro := 'Falta de mem�ria para rodar a fun��o' ;
        -5 : Erro := '' ; // 'Sem comunica��o com o SiTef' ; // Comentado pois SiTEF j� envia a msg de Erro
        -6 : Erro := 'Opera��o cancelada pelo usu�rio' ;
   else
      if Sts < 0 then
         Erro := 'Erros detectados internamente pela rotina ('+IntToStr(Sts)+')'
      else
         Erro := 'Negada pelo autorizador ('+IntToStr(Sts)+')' ;
   end;


   if Erro <> '' then
      TACBrTEFD(Owner).DoExibeMsg( opmOK, Erro );
*)
end ;

Function TACBrTEFDCliSiTef.ProcessarRespostaPagamento(
   const IndiceFPG_ECF : String; const Valor : Double) : Boolean; 
var
  ImpressaoOk : Boolean;
  RespostaPendente : TACBrTEFDResp ;
begin
  Result := True ;

  with TACBrTEFD(Owner) do
  begin
     Self.Resp.IndiceFPG_ECF := IndiceFPG_ECF;

     { Cria Arquivo de Backup, contendo Todas as Respostas }
     CopiarResposta ;

     { Cria c�pia do Objeto Resp, e salva no ObjectList "RespostasPendentes" }
     RespostaPendente := TACBrTEFDRespCliSiTef.Create ;
     RespostaPendente.Assign( Resp );
     RespostasPendentes.Add( RespostaPendente );

     if AutoEfetuarPagamento then
     begin
        ImpressaoOk := False ;

        try
           while not ImpressaoOk do
           begin
              try
                 ECFPagamento( IndiceFPG_ECF, Valor );
                 RespostasPendentes.SaldoAPagar  := RoundTo( RespostasPendentes.SaldoAPagar - Valor, -2 ) ;
                 RespostaPendente.OrdemPagamento := RespostasPendentes.Count + 1 ;
                 ImpressaoOk := True ;
              except
                 on EACBrTEFDECF do ImpressaoOk := False ;
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
        end;
     end;

     if RespostasPendentes.SaldoRestante <= 0 then
     begin
        if AutoFinalizarCupom then
        begin
           FinalizarCupom( False );  { False n�o desbloqueia o MouseTeclado }
           ImprimirTransacoesPendentes;
        end;
     end ;
  end;
end;

procedure TACBrTEFDCliSiTef.VerificarIniciouRequisicao;
begin
  if not fIniciouRequisicao then
     raise EACBrTEFDErro.Create( ACBrStr( CACBrTEFD_Erro_SemRequisicao ) ) ;
end;

function TACBrTEFDCliSiTef.SuportaDesconto: Boolean;
begin
  with TACBrTEFD(Owner) do
  begin
     Result := (Identificacao.SoftwareHouse <> '') and
               Assigned( OnComandaECFSubtotaliza ) and
               (not AutoEfetuarPagamento) ;
  end;
end;

function TACBrTEFDCliSiTef.HMS: String;
begin
   Result := FormatDateTime('hhmmss',Now);
end;

end.


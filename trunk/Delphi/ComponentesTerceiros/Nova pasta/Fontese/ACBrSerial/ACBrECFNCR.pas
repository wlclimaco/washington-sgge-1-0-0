{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados(c)2007 Andr� Bohn (AMCOM Sistemas de Informa��o)}
{                                                                              }
{ Colaboradores nesse arquivo: Daniel Simoes de Almeida                        }
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
{ http://www.opensource.org/licenses/gpl-license.php                           }
{                                                                              }
{ Daniel Sim�es de Almeida  -  daniel@djsystem.com.br  -  www.djsystem.com.br  }
{              Pra�a Anita Costa, 34 - Tatu� - SP - 18270-410                  }
{                                                                              }
{******************************************************************************}

{******************************************************************************
|* Historico
|*
|* 04/12/2007: Andr� Bohn (AMCOM Sistemas de Informa��o)
|* - Primeira Versao: Cria�ao e Distribui�ao da Primeira Versao
******************************************************************************}

{$I ACBr.inc}

unit ACBrECFNCR ;

interface
uses ACBrECFClass, ACBrDevice, ACBrUtil, Synautil, ACBrConsts,
     Classes ;

const  SOH = #01 ;
       ENQ = #05 ;
       ACK = 06 ;
       WAK = 17 ;
       NAK = 21 ;
       SYN = 22 ;
       CAN = #24 ;

type

TACBrECFNCRComando = class
  private
    fsComando : AnsiString ;
    fsParams  : TStringList ;
    fsSeq     : Byte ;
    fsTimeOut : Integer;

    function GetPacoteEnvio: AnsiString;
    procedure SetComando(const Value: AnsiString);
 public
    constructor create ;
    destructor destroy ; override ;

    property Comando     : AnsiString  write SetComando  ;
    property TimeOut     : Integer     read fsTimeOut write fsTimeOut ;
    property PacoteEnvio : AnsiString  read GetPacoteEnvio ;
    property Params      : TStringList read fsParams ;
    property Seq         : Byte read fsSeq  write fsSeq;

    Procedure AddParam(AString : AnsiString) ;
 end ;

TACBrPosParam = Array of Byte;
 
TACBrECFNCRResposta = class
  private
    fsResposta     : AnsiString ;

    fsSeq          : Byte ;
    fsCmd          : Byte ;
    fsRetorno      : AnsiString ;
    fsParams       : TStringList ;
    fsChkSum       : AnsiString ;
    fsPosParam     : TACBrPosParam;

    procedure SetResposta(const Value: AnsiString) ;
    function  GetDescRetorno: AnsiString ;
    procedure CarregaPosParam ;
 public
    constructor create ;
    destructor destroy ; override ;

    property Resposta     : AnsiString    read fsResposta write SetResposta ;
    property Seq          : Byte          read fsSeq;
    property Retorno      : AnsiString    read fsRetorno ;
    property DescRetorno  : AnsiString    read GetDescRetorno ;
    property Params       : TStringList   read fsParams ;
    property ChkSum       : AnsiString    read fsChkSum ;
 end ;

{ Classe filha de TACBrECFClass com implementa�ao para NCR }
TACBrECFNCR = class( TACBrECFClass )
 private
    fsNumLoja       : String ;
    fsCNPJ          : String ;
    fsIE            : String ;
    fsHorarioVerao  : String ;
    fsNCRComando    : TACBrECFNCRComando ;
    fsNCRResposta   : TACBrECFNCRResposta ;
    fsImprimeCheque : Boolean ;
    fsLeituraCMC7   : Boolean ;

    procedure Sincroniza ;
    function  BuscaSequenciaVinculado( CodFormaPagto : String = '' ) : String ;
 protected
    function GetDataHora: TDateTime; override ;
    function GetNumCupom: String; override ;
    function GetNumCCF: String; override ;
    function GetNumECF: String; override ;
    function GetNumLoja: String; override ;
    function GetNumSerie: String; override ;
    function GetNumVersao: String; override ;
    function GetSubTotal: Double; override ;
    function GetTotalPago: Double; override ;

    function GetEstado: TACBrECFEstado; override ;
    function GetGavetaAberta: Boolean; override ;
    function GetPoucoPapel : Boolean; override ;
    function GetHorarioVerao: Boolean; override ;

    function GetCNPJ: String; override ;
    function GetIE: String; override ;
    function GetDataMovimento: TDateTime; override ;
    function GetGrandeTotal: Double; override ;
    function GetNumCRO: String; override ;
    function GetNumCRZ: String; override ;
    function GetVendaBruta: Double; override ;
    function GetTotalAcrescimos: Double; override ;
    function GetTotalCancelamentos: Double; override ;
    function GetTotalDescontos: Double; override ;
    function GetTotalSubstituicaoTributaria: Double; override ;
    function GetTotalNaoTributado: Double; override ;
    function GetTotalIsencao: Double; override ;
    function GetNumCOOInicial: String; override ;

    Function VerificaFimLeitura(var Retorno: AnsiString;
       var TempoLimite: TDateTime) : Boolean ; override ;

    function GetChequePronto: Boolean; override ;
 public
    Constructor create( AOwner : TComponent  )  ;
    Destructor Destroy  ; override ;

    procedure Ativar ; override ;

    property NCRComando : TACBrECFNCRComando  read fsNCRComando ;
    property NCRResposta: TACBrECFNCRResposta read fsNCRResposta ;

    Function EnviaComando_ECF( cmd : AnsiString = '') : AnsiString ; override ;

    Procedure AbreCupom ; override ;
    Procedure VendeItem( Codigo, Descricao : String; AliquotaECF : String;
       Qtd : Double ; ValorUnitario : Double; ValorDescontoAcrescimo : Double = 0;
       Unidade : String = ''; TipoDescontoAcrescimo : String = '%';
       DescontoAcrescimo : String = 'D'; CodDepartamento: Integer = -1 ) ; override ;
    Procedure SubtotalizaCupom( DescontoAcrescimo : Double = 0;
       MensagemRodape : AnsiString  = '' ) ; override ;
    Procedure EfetuaPagamento( CodFormaPagto : String; Valor : Double;
       Observacao : AnsiString = ''; ImprimeVinculado : Boolean = false) ;
       override ;
    Procedure FechaCupom( Observacao : AnsiString = ''; IndiceBMP : Integer = 0) ; override ;
    Procedure CancelaCupom ; override ;
    Procedure CancelaItemVendido( NumItem : Integer ) ; override ;

    Procedure LeituraX ; override ;
    Procedure ReducaoZ(DataHora : TDateTime) ; override ;
    Procedure AbreRelatorioGerencial(Indice: Integer = 0) ; override ;
    Procedure LinhaRelatorioGerencial( Linha : AnsiString; IndiceBMP: Integer = 0 ) ; override ;
    Procedure AbreCupomVinculado(COO, CodFormaPagto, CodComprovanteNaoFiscal :
       String; Valor : Double) ; override ;
    Procedure LinhaCupomVinculado( Linha : AnsiString ) ; override ;
    Procedure FechaRelatorio ; override ;
    Procedure PulaLinhas( NumLinhas : Integer = 0 ) ; override ;

    Procedure MudaHorarioVerao  ; overload ; override ;
    Procedure MudaHorarioVerao( EHorarioVerao : Boolean ) ; overload ; override ;
    Procedure CorrigeEstadoErro(Reducao: Boolean = True) ; override ;


    Procedure LeituraMemoriaFiscal( DataInicial, DataFinal : TDateTime;
       Simplificada : Boolean = False ) ; override ;
    Procedure LeituraMemoriaFiscal( ReducaoInicial, ReducaoFinal : Integer;
       Simplificada : Boolean = False ); override ;
    Procedure AbreGaveta ; override ;

    { Procedimentos de Cupom N�o Fiscal }
    Procedure AbreNaoFiscal( CPF_CNPJ: String = ''; Nome: String = '';
       Endereco: String = '' ) ; override ;
    Procedure RegistraItemNaoFiscal( CodCNF : String; Valor : Double;
       Obs : AnsiString = '' ) ; override ;
    Procedure SubtotalizaNaoFiscal( DescontoAcrescimo : Double = 0;
       MensagemRodape: AnsiString = '') ; override ;
    Procedure EfetuaPagamentoNaoFiscal( CodFormaPagto : String; Valor : Double;
       Observacao : AnsiString = ''; ImprimeVinculado : Boolean = false) ; override ;
    Procedure FechaNaoFiscal( Observacao : AnsiString = ''; IndiceBMP : Integer = 0) ; override ;
    Procedure CancelaNaoFiscal ; override ;
    procedure NaoFiscalCompleto(CodCNF: String; Valor: Double;
      CodFormaPagto: String; Obs: AnsiString; IndiceBMP : Integer = 0); override ;

    procedure CarregaAliquotas ; override ;
    procedure LerTotaisAliquota ; override ;
    Procedure ProgramaAliquota( Aliquota : Double; Tipo : Char = 'T';
       Posicao : String = '') ; override ;
    procedure CarregaFormasPagamento ; override ;
    procedure LerTotaisFormaPagamento ; override ;
    Procedure ProgramaFormaPagamento( var Descricao: String;
       PermiteVinculado : Boolean = true; Posicao : String = '' ) ; override ;

    procedure CarregaComprovantesNaoFiscais ; override ;
    procedure LerTotaisComprovanteNaoFiscal ; override ;
    Procedure ProgramaComprovanteNaoFiscal( var Descricao: String;
       Tipo : String = ''; Posicao : String = '') ; override ;
    procedure ImprimeCheque(Banco : String; Valor : Double ; Favorecido,
       Cidade : String; Data : TDateTime ;Observacao : String = '') ; override ;
    Function LeituraCMC7 : AnsiString ; override ;
 end ;

function NCRCheckSum(Dados: AnsiString): Char;

implementation
Uses ACBrECF,
 {$IFDEF COMPILER6_UP}
   DateUtils, StrUtils, Variants
 {$ELSE}
   ACBrD5, Windows
 {$ENDIF},
     SysUtils,  Math ;

function NCRCheckSum(Dados: AnsiString): Char;
var
 i : Integer;
begin
  i := SomaAscII(Dados) ;
  Result :=  chr( i ) ;
end;

{ -------------------------  TACBrECFNCRComando -------------------------- }
constructor TACBrECFNCRComando.create;
begin
  inherited create ;

  fsParams := TStringList.create ;
  fsSeq    := 1 ;
end;

destructor TACBrECFNCRComando.destroy;
begin
  fsParams.Free ;

  inherited destroy ;
end;

procedure TACBrECFNCRComando.SetComando(const Value: AnsiString);
begin
  if fsSeq >= 255 then
     fsSeq := 1
  else
     Inc( fsSeq ) ;

  { Zerando instrucoes adicionais do comando }
  fsParams.Clear ;
  fsTimeOut := 0 ;

  fsComando := Value
end;

procedure TACBrECFNCRComando.AddParam(AString: AnsiString);
begin
  fsParams.Add(  AString  ) ;
end;

function TACBrECFNCRComando.GetPacoteEnvio: AnsiString;
 Var I : Integer ;
     ParamsStr : AnsiString ;
     TBC : Byte;
begin
  { Montando pacote com Parametros }
  ParamsStr := '' ;
  For I := 0 to fsParams.Count-1 do
    ParamsStr := ParamsStr +  fsParams[I] + '\' ;

  TBC := Length( ParamsStr ) ;

  { Montando Pacote de Envio }
  Result := AnsiChar( chr( fsSeq ) ) + AnsiChar( Chr( StrToInt(fsComando) ) ) +
            AnsiChar( Chr( TBC ) ) + ParamsStr ;

  { Calculando o Checksum }
  Result := SOH + Result + NCRCheckSum( Result ) ;
end;


{ ------------------------- TACBrECFNCRResposta -------------------------- }

constructor TACBrECFNCRResposta.create;
begin
  inherited create ;

  fsParams        := TStringList.create ;
  fsSeq           := 0 ;
  fsRetorno       := '' ;
  fsChkSum        := '' ;
  fsResposta      := '' ;
end;

destructor TACBrECFNCRResposta.destroy;
begin
  fsParams.Free ;

  inherited destroy ;
end;

function CharToBin(sChar:string):string;
var iBit, iChr, iPos : integer;
    sSts : AnsiString;

begin
   iChr := Ord(sChar[1]);
   iBit := 128;
   iPos := 1;
   sSts := '00000000';

   while iBit > 0 do
   begin
      if iChr >= iBit then
      begin
         iChr := iChr - iBit;
         sSts[iPos] := '1';
      end;
      iBit := trunc(iBit / 2);
      inc(iPos);
   end;
   Result := sSts;
end;

{ CarregaPosParam - Criado para pegar o tamanho dos campos de retorno,
  para que ao ler a resposta ja carrega os campos de retorno do comando no array Params,
  pois a NCR n�o possui separador de campos no retorno, e manter o objeto comando
  e resposta como foi feito com a ECF Epson. Isso facilitao tratamento de com
  o campos de retorno da ECF

Exemplo: Comando 1 - Leitura do Numero de S�rie

Descri��o                               Formato    Min    M�x   Conte�do   Default
----------------------------------------------------------------------------------
N�mero de s�rie do ECF                     Q         20    20
Identifica��o da Mem�ria Fiscal            Q          1     1
Fabricante                                 Q         20    20
Tipo do equipamento                        Q          7     7
Modelo do ECF                              Q         20    20
N�mero de s�rie da MFD                     Q         19    19
Data de grava��o                           D          6     6
Hor�rio de grava��o                        H          6     6
Indicador do hor�rio de ver�o da grava��o  N          1     1

  O Array neste caso ser� todos os valores da coluna "M�x".

   fsPosParam := VarArrayOf([20, 1, 20, 7, 20, 19, 6, 6, 1]);             }
procedure TACBrECFNCRResposta.CarregaPosParam ;
begin
  case fsCmd of
    1 : fsPosParam := VarArrayOf([20, 1, 20, 7, 20, 19, 6, 6, 1]); { Leitura do n�mero de s�rie }
    3 : fsPosParam := VarArrayOf([3, 20, 20, 20, 18, 6, 6, 1]);    { Leitura e Usuarios } 
    7 : fsPosParam := VarArrayOf([3]);                             { Leitura de n�mero de seq��ncia do ECF no estabelecimento }
   12 : fsPosParam := VarArrayOf([6, 6, 1]);                       { Leitura do rel�gio interno do ECF }
   15 : fsPosParam := VarArrayOf([4]);                             { Leitura do hor�rio m�nimo para libera��o da Redu��o Z }
   60 : fsPosParam := VarArrayOf([1, 6, 6, 6, 1, 4, 6, 3, 18, 6]); { Leitura de dados da reducao Z }
   63 : fsPosParam := VarArrayOf([1]);                             { Leitura do modo de opera��o }
   64 : fsPosParam := VarArrayOf([2, 2, 4, 1, 1]);                 { Leitura do contexto de opera��o }
   65 : fsPosParam := VarArrayOf([1, 6]);                          { Leitura do status da Redu��o Z }
   66 : fsPosParam := VarArrayOf([1, 1, 1]);                       { Leitura do status do mecanismo impressor }
   67 : fsPosParam := VarArrayOf([1]);                             { Leitura do status da gaveta }
   68 : fsPosParam := VarArrayOf([1]);                             { Leitura do status do cancelamento }
   69 : fsPosParam := VarArrayOf([18]);                            { Leitura de valor dos acumuladores - Valor atual }
   70 : fsPosParam := VarArrayOf([18]);                            { Leitura de valor dos acumuladores - Documento corrente }
   71 : fsPosParam := VarArrayOf([18]);                            { Leitura de valor dos acumuladores - Valor no in�cio do dia }
   72 : fsPosParam := VarArrayOf([18, 18]);                        { Leitura de valor dos registradores parciais tributados - Valor atual }
   74 : fsPosParam := VarArrayOf([18, 4, 4, 4, 4]);                { Leitura de valor dos registradores de meios de pagamento - Valor atual }
   76 : fsPosParam := VarArrayOf([6, 18]);                         { Leitura de valor dos registradores parciais n�o fiscais - Valor atual }
  160 : fsPosParam := VarArrayOf([2]);                             { Leitura da quantidade de registradores fiscais parciais programados }
  161 : fsPosParam := VarArrayOf([4, 6, 6, 1]);                    { Leitura de dados de registradores fiscais parciais programados }
  162 : fsPosParam := VarArrayOf([15, 6, 6, 1]);                   { Leitura de dados de registradores n�o fiscais parciais programados }
  163 : fsPosParam := VarArrayOf([2]);                             { Leitura da quantidade de registradores n�o fiscais parciais programados }
  165 : fsPosParam := VarArrayOf([2]);                             { Leitura do pr�ximo registrador n�o fiscal parcial programado }
  166 : fsPosParam := VarArrayOf([1, 15, 6, 6, 1]);                { Leitura de dados de registradores de meio de pagamento programados }
  167 : fsPosParam := VarArrayOf([2]);                             { Leitura da quantidade de registradores de meios de pagamento programados }
  169 : fsPosParam := VarArrayOf([2]);                             { Leitura do pr�ximo registrador de meio de pagamento programado }
  187 : fsPosParam := VarArrayOf([3, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 2, 4]); { Leitura de caracter�sticas do ECF }
  189 : fsPosParam := VarArrayOf([2, 2, 2, 6, 6, 1]);              { Leitura de atualiza��es do firmware }
  220 : fsPosParam := VarArrayOf([6]);                             { Leitura dos contadores - Valor atual }
  221 : fsPosParam := VarArrayOf([6]);                             { Leitura dos contadores - Valor no in�cio do dia }
  224 : fsPosParam := VarArrayOf([4, 4, 4]);                       { Leitura do status dos Comprovantes de Cr�dito ou D�bito � �ltimo documento }
  225 : fsPosParam := VarArrayOf([2, 2, 1, 2, 18]);                { Leitura dos dados dos Comprovantes de Cr�dito ou D�bito � �ltimo documento }
  226 : fsPosParam := VarArrayOf([1, 1, 1, 1, 1, 1, 1, 1, 1 , 1]); { Leitura do estado das defini��es do ECF }
  else
    SetLength( fsPosParam, 0) ;
  end;
end;

procedure TACBrECFNCRResposta.SetResposta(const Value: AnsiString);
Var Buf : AnsiString ;
    P   : Integer ;
    TamRetorno : Integer;
begin
  fsParams.Clear ;
  fsSeq           := 0 ;
  fsRetorno       := '' ;
  fsChkSum        := '' ;
  fsResposta      := '' ;

  if Value = '' then exit ;

  fsResposta := Value ;

  if ( LeftStr(fsResposta,1) <> SOH ) and ( LeftStr(fsResposta,1) <> CAN ) then
     raise EACBrECFERRO.Create(ACBrStr('Resposta inv�lida. N�o inicia com SOH (01) ou CAN (24) ')) ;

  if ( LeftStr(fsResposta,1) = CAN ) then
  begin
    fsRetorno := copy( fsResposta, 4, 2 ) ;
  end
  else if ( LeftStr(fsResposta,1) = SOH ) then
  begin
    fsChkSum := RightStr(fsResposta,1) ;
    if NCRCheckSum( copy(fsResposta,2,Length(fsResposta)-2) ) <> fsChkSum then
       raise EACBrECFERRO.create(copy(fsResposta,2,Length(fsResposta)-2) + ' | ' + fsResposta+ACBrStr('Resposta inv�lida. CheckSum da Resposta n�o est� correto.')) ;

    try
       fsSeq := ord(fsResposta[2]) ;
    except
       raise EACBrECFERRO.Create(ACBrStr('Resposta inv�lida. Num.Sequencia inv�lido')) ;
    end ;

    fsCmd := ord(fsResposta[3]) ;
    TamRetorno := Synautil.BinToInt( CharToBin( fsResposta[5] ) + CharToBin( fsResposta[4] ) );
    { Pega apenas o BRS }
    Buf := copy(fsResposta, 6, TamRetorno) ;  //  Remove SOH, SEQ, CMD, TBR e CHKSUM

    CarregaPosParam ;
    { Quebrando Parametros com os tamanhos carregados em fsPosParam }
    for P := low( fsPosParam ) to high( fsPosParam ) do
    begin
      fsParams.Add( LeftStr(Buf, fsPosParam[P])  ) ;
      delete(buf, 1, fsPosParam[P]) ;
    end;
  end;
end;


function TACBrECFNCRResposta.GetDescRetorno: AnsiString;
 Var  sValorSaida : String;
      sRetorno    : AnsiString ;
begin
  sValorSaida := '';
  sRetorno    := fsRetorno ;

  Case sRetorno[1] of
    #1 : sValorSaida := 'Tamanho do par�metro menor que o m�nimo permitido.' ;
    #2 : sValorSaida := 'Tamanho do par�metro maior que o m�ximo permitido.' ;
    #3 : sValorSaida := 'Conte�do do par�metro inv�lido.' ;
    #4 : sValorSaida := 'Falta par�metro.' ;
    #5 : sValorSaida := 'Excesso de par�metros.' ;
    #6 : sValorSaida := 'Caractere inv�lido no par�metro.' ;
    #7 : case sRetorno[2] of
           #1 : sValorSaida := 'Ocorreu erro no envio do pacote de comando.' ;
           #2 : sValorSaida := 'Ocorreu timeout na recep��o da resposta do protocolo confirmando a recep��o do pacote de comando.' ;
           #3 : sValorSaida := 'O pacote de comando recebido pelo ECF cont�m erro de protocolo ou checksum.' ;
           #4 : sValorSaida := 'Foi recebido um caractere de controle diferente de ACK, WAK e NAK como resposta a um pacote de comando enviado.' ;
           #5 : sValorSaida := 'Ocorreu erro no envio de uma solicita��o de status.' ;
           #6 : sValorSaida := 'Ocorreu timeout na recep��o da resposta de uma solicita��o de status.' ;
           #7 : sValorSaida := 'Foi recebido um caractere de controle diferente de CAN, SOH e WAK como resposta a uma solicita��o de status.' ;
           #9 : sValorSaida := 'Ocorreu erro no envio do pacote de sincronismo.' ;
          #10 : sValorSaida := 'Ocorreu timeout na recep��o da resposta de um pedido de sincronismo.' ;
          #11 : sValorSaida := 'Foi recebido um caractere de controle diferente de SYN e WAK como resposta a um pedido de sincronismo.' ;
          #12 : sValorSaida := 'A porta serial n�o foi iniciada.' ;
         end;
    #8 : case sRetorno[2] of
           #1 : sValorSaida := 'A �rea destinada a armazenar as linhas para impress�o da 2a via do Comprovante de Cr�dito ou D�bito esgotou-se. O Comprovante de Cr�dito ou D�bito dever� ser encerrado.' ;
         end;
    #9 : case sRetorno[2] of
           #0 : sValorSaida := 'O comando de In�cio do Dia n�o pode ser executado sem que o �ltimo dia de movimento seja reduzido.' ;
           #1 : sValorSaida := 'Sem a realiza��o do comando In�cio do Dia, � permitida apenas a emiss�o de Leitura X e Leitura da Mem�ria Fiscal.' ;
           #2 : sValorSaida := 'Esgotada a quantidade m�xima de Redu��es Z suportada pelo ECF.' ;
           #3 : sValorSaida := 'Esgotada a �rea da Mem�ria Fiscal destinada a grava��o de Redu��es Z.' ;
         #253 : sValorSaida := 'J� passam de 2:00 hs da manh� do dia seguinte ao dia do movimento corrente. O in�cio de um novo Cupom Fiscal, Comprovante N�o Fiscal e Relat�rio Gerencial somente ser� permitido ap�s a abertura de uma nova data de movimento.' ;
         #254 : sValorSaida := 'O hor�rio do ECF � anterior ao hor�rio m�nimo programado para a emiss�o de Redu��es Z.' ;
         #255 : sValorSaida := 'J� foi realizada uma Redu��o Z na data corrente.' ;
         end;
   #10 : case sRetorno[2] of
           #0 : sValorSaida := 'Comando inv�lido recebido pelo firmware.' ;
           #1 : sValorSaida := 'N�o � poss�vel a impress�o de texto gen�rico na se��o corrente do documento.' ;
           #3 : sValorSaida := 'Excedida a quantidade de avan�os de linha na se��o corrente do documento.' ;
         end;
   #11 : case sRetorno[2] of
           #0 : sValorSaida := '� mandat�rio realizar o acerto do rel�gio interno do ECF.' ;
           #1 : sValorSaida := '� mandat�ria a defini��o do n�mero de s�rie do ECF.' ;
           #2 : sValorSaida := '� mandat�ria a programa��o de um usu�rio.' ;
           #3 : sValorSaida := '� mandat�ria a programa��o de pelo menos uma linha do tipo Nome no clich�.' ;
           #4 : sValorSaida := '� mandat�ria a programa��o de pelo menos uma linha do tipo Endere�o no clich�.' ;
           #5 : sValorSaida := '� mandat�ria a defini��o do N�mero de Seq��ncia no Estabelecimento.' ;
           #6 : sValorSaida := '� mandat�ria a defini��o dos Par�metros de Configura��o do ECF.' ;
           #7 : sValorSaida := '� mandat�ria a defini��o do S�mbolo da Moeda.' ;
           #8 : sValorSaida := '� mandat�ria a defini��o da Criptografia do GT.' ;
          #16 : sValorSaida := 'Excedida a quantidade de autentica��es permitida na se��o corrente do documento.' ;
          #32 : sValorSaida := 'Comando somente poder� ser enviado com a utiliza��o do cabo do fisco pela porta serial do fisco.' ;
          #48 : sValorSaida := 'Mecanismo incompat�vel com o modelo do n�mero de s�rie.' ;
          #49 : sValorSaida := 'Marca incompat�vel com o n�mero de s�rie.' ;
          #50 : sValorSaida := 'Sigla da marca do n�mero de s�rie inv�lida.' ;
          #51 : sValorSaida := 'Tipo do ECF inv�lido.' ;
          #53 : sValorSaida := 'Mecanismo inv�lido.' ;
          #64 : sValorSaida := 'O s�mbolo da moeda j� se encontra programado com o conte�do passado como par�metro.' ;
          #65 : sValorSaida := 'Ainda n�o foi realizada nenhuma programa��o de s�mbolo de moeda no EC.' ;
          #66 : sValorSaida := 'O seq�encial da programa��o de s�mbolo de moeda ainda n�o foi utilizado.' ;
          #67 : sValorSaida := 'Ainda n�o foi realizada nenhuma programa��o de criptografia do GT no ECF.' ;
          #68 : sValorSaida := 'O seq�encial da programa��o de criptografia do GT ainda n�o foi utilizado.' ;
          #69 : sValorSaida := 'A criptografia do GT j� se encontra programada com o conte�do passado como par�metro.' ;
          #80 : sValorSaida := 'O primeiro boot do ECF somente pode ser realizado em Interven��o.' ;
         #157 : sValorSaida := 'O ECF detectou uma Mem�ria de Fita Detalhe gravada por outro ECF. Apenas os comandos de leitura est�o habilitados.' ;
         #158 : sValorSaida := 'O valor do CFD solicitado ainda n�o foi utilizado.' ;
         #159 : sValorSaida := 'Ainda n�o foi realizada nenhuma reimpress�o de Mem�ria de Fita Detalhe.' ;
         #179 : sValorSaida := 'Inscri��o Estadual diferente da Inscri��o Estadual cadastrada no usu�rio.' ;
         #180 : sValorSaida := 'Inscri��o Municipal diferente da Inscri��o Municipal cadastrada no usu�rio.' ;
         #181 : sValorSaida := 'CNPJ diferente do CNPJ cadastrado no usu�rio.' ;
         #182 : sValorSaida := 'A programa��o de um novo usu�rio no ECF somente poder� ocorrer se n�o houver nenhum documento emitido na data da programa��o.' ;
         #183 : sValorSaida := 'A senha informada para a programa��o do usu�rio n�o confere.' ;
         #184 : sValorSaida := 'A senha deve, obrigatoriamente, ser informada na programa��o do usu�rio.' ;
         #186 : sValorSaida := 'Existem caracteres repetidos na string de criptografia do GT.' ;
         #187 : sValorSaida := 'J� existe um usu�rio definido no ECF.' ;
         #188 : sValorSaida := '� obrigat�ria a informa��o de pelo menos uma inscri��o (Estadual ou Municipal) para o usu�rio.' ;
         #189 : sValorSaida := 'A quantidade m�xima de usu�rios permitida no ECF foi atingida.' ;
         #190 : sValorSaida := 'O seq�encial de usu�rio solicitado ainda n�o foi utilizado.' ;
         #191 : sValorSaida := 'Os dados informados na programa��o do usu�rio coincidem com o usu�rio corrente do ECF.' ;
         #204 : sValorSaida := '� mandat�rio que a NVRAM seja zerada para efetivar a troca da vers�o.' ;
         #205 : sValorSaida := '� mandat�rio que o ECF esteja em Modo de Interven��o T�cnica para efetivar a troca da vers�o.' ;
         #206 : sValorSaida := 'O seq�encial de troca de vers�o do firmware solicitado ainda n�o foi utilizado.' ;
         #207 : sValorSaida := 'Ainda n�o foi realizada nenhuma troca de vers�o do firmware no ECF.' ;
         #222 : sValorSaida := 'O valor do CRO solicitado ainda n�o foi utilizado.' ;
         #238 : sValorSaida := 'O valor do CRZ solicitado ainda n�o foi utilizado.' ;
         #246 : sValorSaida := 'Excedido o limite de Interven��es T�cnicas  .' ;
         #247 : sValorSaida := 'Limite de reimpress�es de MFD esgotado.' ;
         #248 : sValorSaida := 'Per�odo n�o encontrado.' ;
         #249 : sValorSaida := 'O COO inicial � maior do que o COO final.' ;
         #251 : sValorSaida := 'O CRZ inicial � maior do que o CRZ final.' ;
         #252 : sValorSaida := 'A data inicial do intervalo � maior do que data final.' ;
         #253 : sValorSaida := 'O comando enviado somente pode ser submetido ao ECF em Modo de Opera��o Fiscal.' ;
         #254 : sValorSaida := 'O comando enviado somente pode ser submetido ao ECF em Modo de Interven��o T�cnica.' ;
         #255 : sValorSaida := 'O n�mero de s�rie do ECF j� foi definido.' ;
         end;
   #12 : case sRetorno[2] of
           #4 : sValorSaida := 'O comando enviado n�o pode ser executado pois existe um Cupom Fiscal aberto.' ;
           #7 : sValorSaida := 'O comando enviado n�o pode ser executado pois existe um Comprovante N�o Fiscal aberto.' ;
           #9 : sValorSaida := 'O comando enviado n�o pode ser executado pois existe um Comprovante de Cr�dito ou D�bito aberto.' ;
          #13 : sValorSaida := 'O comando enviado n�o pode ser executado pois existe um Estorno de Comprovante de Cr�dito ou D�bito aberto.' ;
          #14 : sValorSaida := 'O comando enviado n�o pode ser executado pois existe um Relat�rio Gerencial aberto.' ;
          #15 : sValorSaida := 'O comando enviado n�o pode ser executado pois o ECF est� em repouso.' ;
         #227 : sValorSaida := 'A quantidade m�xima de itens em um Cupom Fiscal ou registros em um Comprovante N�o Fiscal foi excedida.' ;
         #228 : sValorSaida := 'O tipo de leitura solicitada n�o � compat�vel com o documento em emiss�o.' ;
         #229 : sValorSaida := 'A segunda via do CCD j� foi emitida.' ;
         #230 : sValorSaida := 'O tipo de documento solicitado � diferente de Cupom Fiscal e Comprovante N�o Fiscal.' ;
         #231 : sValorSaida := 'A emiss�o de segunda via do CCD n�o � poss�vel pois o conte�do do comprovante n�o foi salvo.' ;
         #233 : sValorSaida := 'O CCD especificado j� foi impresso.' ;
         #234 : sValorSaida := 'O CCD especificado j� foi reimpresso.' ;
         #235 : sValorSaida := 'O CCD especificado n�o foi impresso.' ;
         #238 : sValorSaida := 'O cancelamento de um Cupom Fiscal ou Comprovante N�o Fiscal somente ser� permitido ap�s o estorno de todos os CCDs emitidos.' ;
         #239 : sValorSaida := 'O limite m�ximo de CCDs por cupom foi excedido.' ;
         #240 : sValorSaida := 'A quantidade de parcelas somente pode ser especificada para os pagamentos que envolvam meios que aceitem a emiss�o de CCD.' ;
         #243 : sValorSaida := 'A leitura de dados dos meios de pagamento n�o foi poss�vel pois o documento corrente ou anterior n�o permitem meios de pagamento.' ;
         #245 : sValorSaida := 'O Cupom Adicional somente pode ser emitido uma �nica vez.' ;
         #246 : sValorSaida := 'O documento anterior n�o permite estorno de meios de pagamento.' ;
         #247 : sValorSaida := 'O Comprovante de Cr�dito ou D�bito solicitado j� foi emitido.' ;
         #248 : sValorSaida := 'O documento anterior n�o permite a emiss�o de Cupom Adicional.' ;
         #249 : sValorSaida := 'O comando n�o pode ser aceito pois o documento anterior est� cancelado.' ;
         #250 : sValorSaida := 'O limite m�ximo de 30 pagamentos por documento j� foi atingido.' ;
         #251 : sValorSaida := 'O Comprovante de Cr�dito ou D�bito informado nos par�metros n�o existe.' ;
         #252 : sValorSaida := 'O documento anterior n�o permite a utiliza��o de Comprovante de Cr�dito ou D�bito.' ;
         #253 : sValorSaida := 'O documento anterior n�o permite cancelamento.' ;
         #254 : sValorSaida := 'O documento anterior j� foi cancelado.' ;
         #255 : sValorSaida := 'O documento atual n�o permite cancelamento.' ;
         end;  
   #13 : case sRetorno[2] of
           #0 : sValorSaida := 'Comando n�o pode ser executado antes da impress�o do clich�.' ;
           #1 : sValorSaida := 'Comando n�o pode ser executado ap�s a impress�o do clich�.' ;
           #2 : sValorSaida := 'Comando n�o pode ser executado em documento com registros efetuados.' ;
           #3 : sValorSaida := 'Comando n�o pode ser executado ap�s desconto ou acr�scimo em subtotal.' ;
           #4 : sValorSaida := 'Comando n�o pode ser executado em documento sem pagamentos.' ;
           #5 : sValorSaida := 'Comando n�o pode ser executado em documentos ainda n�o totalmente pagos.' ;
           #6 : sValorSaida := 'Comando n�o pode ser executado em documentos totalmente pagos.' ;
           #7 : sValorSaida := 'Comando n�o pode ser executado ap�s a impress�o do consumidor.' ;
           #8 : sValorSaida := 'Comando n�o pode ser executado dentro de Relat�rios Gerenciais.' ;
           #9 : sValorSaida := 'Comando n�o pode ser executado dentro de Comprovantes de Cr�dito ou D�bito.' ;
          #10 : sValorSaida := 'Comando n�o pode ser executado antes do t�rmino do documento.' ;
          #11 : sValorSaida := 'Comando n�o pode ser executado durante a emiss�o do clich�.' ;
          end;
   #16 : case sRetorno[2] of
           #0 : sValorSaida := 'O avan�o do rel�gio interno do ECF n�o pode ser superior a 31 dias.' ;
           #1 : sValorSaida := 'O rel�gio interno do ECF n�o pode ser atrasado para um hor�rio anterior a �ltima grava��o realizada na Mem�ria Fiscal.' ;
           #2 : sValorSaida := 'O rel�gio interno do ECF n�o encontra-se no hor�rio de ver�o.' ;
           #3 : sValorSaida := 'O rel�gio interno do ECF encontra-se no hor�rio de ver�o.' ;
           #4 : sValorSaida := 'A sa�da do hor�rio de ver�o somente pode ocorrer entre 01:00hs e 23:59hs.' ;
           #5 : sValorSaida := 'A entrada no hor�rio de ver�o somente pode ocorrer entre 00:00hs e 22:59hs.' ;
           #8 : sValorSaida := 'O rel�gio interno do ECF n�o pode ser atrasado para um hor�rio anterior a �ltima grava��o realizada na Mem�ria de Fita Detalhe.' ;
           #9 : sValorSaida := 'A altera��o do hor�rio de ver�o somente � permitida imediatamente ap�s a Redu��o Z.' ;
          #10 : sValorSaida := 'O rel�gio interno do ECF n�o pode ser atrasado para um hor�rio anterior ao impresso no rodap� do �ltimo documento emitido.' ;
         end;  
   #17 : case sRetorno[2] of
           #0 : sValorSaida := 'Registrador parcial j� existente.' ;
           #1 : sValorSaida := 'A quantidade m�xima de parciais foi excedida.' ;
           #2 : sValorSaida := 'Registrador parcial n�o existente.' ;
           #3 : sValorSaida := 'Final da lista de registradores parciais.' ;
           #4 : sValorSaida := 'Registrador parcial n�o fiscal j� existente.' ;
           #6 : sValorSaida := 'Registrador parcial n�o fiscal n�o existente.' ;
           #7 : sValorSaida := 'Final da lista de registradores parciais n�o fiscais.' ;
           #8 : sValorSaida := 'Registrador de meio de pagamento j� existente.' ;
          #10 : sValorSaida := 'Registrador de meio de pagamento n�o existente.' ;
          #11 : sValorSaida := 'Final da lista de registradores de meio de pagamento.' ;
          #13 : sValorSaida := 'Registrador de unidade de medida n�o existente.' ;
          #14 : sValorSaida := 'Registrador de unidade de medida j� existente.' ;
          #15 : sValorSaida := 'Descri��o de unidade de medida j� existente.' ;
          #16 : sValorSaida := 'N�o � poss�vel criar registradores parciais sujeitos a tributa��o do ISSQN para usu�rios sem Inscri��o Municipal.' ;
          #17 : sValorSaida := 'N�o � poss�vel criar registradores parciais sujeitos a tributa��o do ICMS para usu�rios sem Inscri��o Estadual.' ;
          #18 : sValorSaida := 'Registrador de Relat�rio Gerencial n�o existente.' ;
          #19 : sValorSaida := 'Registrador de Relat�rio Gerencial j� existente.' ;
          #20 : sValorSaida := 'Final da lista de registradores de Relat�rio Gerencial.' ;
          #21 : sValorSaida := 'Final da lista de registradores de unidade de medida.' ;
          end;
   #18 : case sRetorno[2] of
           #0 : sValorSaida := 'Registrador fiscal parcial n�o programado.' ;
           #1 : sValorSaida := 'Seq�encial do item de Cupom Fiscal ou registro de Comprovante N�o Fiscal inexistente.' ;
           #2 : sValorSaida := 'Desconto maior ou igual a venda l�quida.' ;
           #3 : sValorSaida := 'Desconto igual a zeros.' ;
           #5 : sValorSaida := 'Documento com total igual a zeros.' ;
           #6 : sValorSaida := 'O seq�encial do registro informado no par�metro j� foi exclu�do do buffer e portanto n�o pode mais ser referenciado.' ;
           #7 : sValorSaida := 'O registro referente ao seq�encial informado j� foi cancelado.' ;
           #8 : sValorSaida := 'O valor do desconto no subtotal deve ser menor do que o total.' ;
           #9 : sValorSaida := 'N�o foi concedido desconto no subtota.' ;
          #10 : sValorSaida := 'O seq�encial do pagamento informado n�o existe.' ;
          #11 : sValorSaida := 'O c�digo do item � obrigat�rio para produtos sujeitos ao ICMS.' ;
          #12 : sValorSaida := 'O registrador parcial n�o fiscal n�o foi programado.' ;
          #13 : sValorSaida := 'O registrador do meio de pagamento que est� substituindo o meio de pagamento estornado, n�o foi programado.' ;
          #17 : sValorSaida := 'O desconto em registros sujeitos a tributa��o do ISS n�o est� habilitado.' ;
          #18 : sValorSaida := 'O valor do desconto no subtotal � maior que a soma dos valores l�quidos dos registros sujeitos ao ICMS e o desconto em registros sujeitos a tributa��o do ISS n�o est� habilitado.' ;
          #19 : sValorSaida := 'Acr�scimo maior ou igual a venda l�quida.' ;
          #20 : sValorSaida := 'Acr�scimo igual a zeros.' ;
          #21 : sValorSaida := 'O valor do acr�scimo no subtotal deve ser menor do que o total.' ;
          #22 : sValorSaida := 'N�o foi concedido acr�scimo no subtotal.' ;
          #23 : sValorSaida := 'N�o � poss�vel conceder outro desconto no item.' ;
          #24 : sValorSaida := 'N�o � poss�vel conceder outro acr�scimo no item.' ;
          #25 : sValorSaida := 'N�o � poss�vel conceder outro desconto no subtotal.' ;
          #26 : sValorSaida := 'N�o � poss�vel conceder outro acr�scimo no subtotal.' ;
          #27 : sValorSaida := 'O valor total do registro deve ser igual ao produto da quantidade pelo pre�o unit�rio.' ;
         end;
   #19 : case sRetorno[2] of
           #1 : sValorSaida := 'A NVRAM somente poder� ser zerada quando o ECF estiver em Modo de Interven��o T�cnica.' ;
         end;  
   #20 : case sRetorno[2] of
           #4 : sValorSaida := 'As leituras manuais n�o podem ser executadas pois existe um Cupom Fiscal aberto.' ;
           #7 : sValorSaida := 'As leituras manuais n�o podem ser executadas pois existe um Comprovante N�o Fiscal aberto.' ;
           #9 : sValorSaida := 'As leituras manuais n�o podem ser executadas pois existe um Comprovante de Cr�dito ou D�bito aberto.' ;
          #13 : sValorSaida := 'As leituras manuais n�o podem ser executadas pois existe um Estorno de Comprovante de Cr�dito ou D�bito aberto.' ;
          #14 : sValorSaida := 'As leituras manuais n�o podem ser executadas pois existe um Relat�rio Gerencial aberto.' ;
         #255 : sValorSaida := 'As leituras manuais n�o podem ser executadas com a inicia��o do ECF incompleta.' ;
         end;
   #21 : case sRetorno[2] of
          #18 : sValorSaida := 'Excedido o limite superior da �rea de �ndices da Mem�ria Fiscal em uma opera��o de SEEK.' ;
          #19 : sValorSaida := 'Excedido o limite inferior da �rea de �ndices da Mem�ria Fiscal em uma opera��o de SEEK.' ;
          #20 : sValorSaida := 'Excedido o limite superior da �rea de dados da Mem�ria Fiscal em uma opera��o de SEEK.' ;
          #21 : sValorSaida := 'Excedido o limite inferior da �rea de dados da Mem�ria Fiscal em uma opera��o de SEEK.' ;
          #27 : sValorSaida := 'Erro na leitura de dados ap�s a grava��o na Mem�ria Fiscal.' ;
          #28 : sValorSaida := 'Erro checando a grava��o na Mem�ria Fiscal.' ;
          #29 : sValorSaida := 'Erro de grava��o na Mem�ria Fisca.' ;
          #30 : sValorSaida := 'T�rmino do espa�o dispon�vel para grava��o de registros na �rea de �ndice da Mem�ria Fiscal em fun��o de erros ocorridos anteriormente.' ;
          #31 : sValorSaida := 'Tentativa de leitura al�m do limite superior da �rea de �ndice da Mem�ria Fiscal.' ;
          #34 : sValorSaida := 'Tentativa de leitura al�m do limite superior da �rea de dados da Mem�ria Fiscal.' ;
          #35 : sValorSaida := 'T�rmino do espa�o dispon�vel para grava��o de registros na �rea de dados da Mem�ria Fiscal em fun��o de erros ocorridos anteriormente.' ;
          #36 : sValorSaida := 'Erro de checksum em uma leitura de registro gravado na Mem�ria Fiscal.' ;
          #37 : sValorSaida := 'Tentativa de endere�ar al�m do limite superior da Mem�ria Fiscal.' ;
          #39 : sValorSaida := 'Mem�ria Fiscal n�o est� limpa.' ;
          #40 : sValorSaida := 'Mem�ria Fiscal foi desconectada. � necess�rio realizar interven��o t�cnica.' ;
          #41 : sValorSaida := 'Erro de leitura na Mem�ria Fiscal.' ;
          #42 : sValorSaida := 'Mem�ria fiscal inconsistente.' ;
          #43 : sValorSaida := 'Erro na verifica��o da data de registro na Mem�ria Fiscal.' ;
          #44 : sValorSaida := 'Erro na verifica��o do buffer do registro na Mem�ria Fiscal.' ;
          #47 : sValorSaida := 'Erro na verifica��o da quantidade de registros gravados na Mem�ria Fiscal.' ;
          #64 : sValorSaida := 'Fim de bobina.' ;
          #65 : sValorSaida := 'Bobina acabando.' ;
          #80 : sValorSaida := 'Aguardando a inser��o de cheque.' ;
          #81 : sValorSaida := 'Cheque presente.' ;
          #82 : sValorSaida := 'Cheque ausente.' ;
          #95 : sValorSaida := 'Timeout aguardando a inser��o do cheque para impress�o.' ;
          #96 : sValorSaida := 'Aguardando a inser��o de documento para autentica��o.' ;
          #97 : sValorSaida := 'Documento para autentica��o presente.' ;
          #98 : sValorSaida := 'Documento para autentica��o ausente.' ;
         #111 : sValorSaida := 'Timeout aguardando a inser��o do documento para autentica��o.' ;
         #131 : sValorSaida := 'Tampa aberta.' ;
         #132 : sValorSaida := 'Modelo de ECF n�o realiza autentica��o de documentos.' ;
         #133 : sValorSaida := 'Modelo de ECF n�o realiza a impress�o de cheques.' ;
         #136 : sValorSaida := 'Mecanismo impressor desconhecido.' ;
         #137 : sValorSaida := 'Erro de timeout no mecanismo impressor.' ;
         #138 : sValorSaida := 'Tecla de FEED do mecanismo impressor pressionada.' ;
         #144 : sValorSaida := 'Modelo de ECF n�o possui leitor de CMC-7.' ;
         #145 : sValorSaida := 'Erro na leitura do CMC-7.' ;
         #146 : sValorSaida := 'Erro de timeout aguardando a inser��o de cheque para a leitura de CMC-7.' ;
         #161 : sValorSaida := 'Mem�ria de fita detalhe n�o est� limpa.' ;
         #162 : sValorSaida := 'MFD desconectada.' ;
         #163 : sValorSaida := 'A MFD foi desconectada em uma execu��o anterior.' ;
         #164 : sValorSaida := 'Erro de grava��o na MFD.' ;
         end;
   #22 : case sRetorno[2] of
           #0 : sValorSaida := 'Overflow na parte inteira durante a convers�o de um n�mero representado por uma string para BCD.' ;
           #1 : sValorSaida := 'Overflow na parte decimal durante a convers�o de um n�mero representado por uma string para BCD.' ;
           #3 : sValorSaida := 'Overflow em registrador de situa��o tribut�ria.' ;
           #4 : sValorSaida := 'Overflow em registrador parcial de acr�scimos.' ;
           #5 : sValorSaida := 'Overflow em registrador parcial de descontos.' ;
           #6 : sValorSaida := 'Overflow em registrador n�o fiscal.' ;
           #7 : sValorSaida := 'Overflow em registrador de meio de pagamento.' ;
           #8 : sValorSaida := 'Overflow em registrador de troco.' ;
           #9 : sValorSaida := 'Overflow em registrador de cancelamento.' ;
         end;
   #23 : case sRetorno[2] of
           #0 : sValorSaida := 'Caractere de controle inv�lido recebido no ECF.' ;
           #1 : sValorSaida := 'Checksum inv�lido no pacote recebido no ECF.' ;
           #2 : sValorSaida := 'ECF encontra-se em erro fatal.' ;
          #17 : sValorSaida := 'Checksum da NVRAM inv�lido.' ;
          #33 : sValorSaida := 'Mem�ria de Fita detalhe esgotada.' ;
         end;
   #24 : case sRetorno[2] of
           #1 : sValorSaida := 'A Mem�ria Fiscal n�o est� presente.' ;
           #3 : sValorSaida := 'Erro de grava��o na Mem�ria Fiscal.' ;
           #8 : sValorSaida := 'Tentativa de regrava��o na Mem�ria Fiscal.' ;
           #9 : sValorSaida := 'Erro de verifica��o da grava��o na Mem�ria Fiscal.' ;
          #17 : sValorSaida := 'Data e hora do rel�gio interno do ECF inv�lidas.' ;
          #18 : sValorSaida := 'Falha no rel�gio interno do ECF.' ;
          #64 : sValorSaida := 'Falha na abertura da porta serial de comandos.' ;
          #65 : sValorSaida := 'Falha na abertura da porta serial do fisco.' ;
          #66 : sValorSaida := 'Falha na configura��o do buffer da porta serial de comandos.' ;
          #67 : sValorSaida := 'Falha na configura��o do buffer da porta serial do fisco.' ;
          #70 : sValorSaida := 'Falha na configura��o dos par�metros de comunica��o da porta serial de comandos e do fisco.' ;
          #86 : sValorSaida := 'Falha na configura��o do buffer da porta serial do mecanismo impressor.' ;
          #87 : sValorSaida := 'Falha na opera��o de flush da porta serial do mecanismo impressor.' ;
          #88 : sValorSaida := 'Falha na configura��o dos par�metros de comunica��o do mecanismo impressor.' ;
         #160 : sValorSaida := 'Configura��o inv�lida na DIP SWITCH.' ;
         #181 : sValorSaida := 'Erro de leitura na Mem�ria de Fita Detalhe.' ;
         end;
   #25 : case sRetorno[2] of
         #145 : sValorSaida := 'Overflow na quantidade m�xima de campos em um template.' ;
         #146 : sValorSaida := 'Overflow no buffer do template.' ;
         #149 : sValorSaida := 'Erro na recupera��o de registros da Mem�ria de Fita Detalhe.' ;
         #150 : sValorSaida := 'Reimpress�o de documentos da Mem�ria de Fita Detalhe interrompida.' ;
         #151 : sValorSaida := 'T�rmino da �rea dispon�vel para grava��o na mem�ria de fita-detalhe.' ;
         #152 : sValorSaida := 'Final dos registros na �rea de dados.' ;
         #153 : sValorSaida := 'Overflow no buffer de template da Mem�ria de Fita Detalhe.' ;
         #154 : sValorSaida := 'Overflow no buffer de bloco da Mem�ria de Fita Detalhe.' ;
         #155 : sValorSaida := 'Overflow no buffer de comando da Mem�ria de Fita Detalhe.' ;
         #156 : sValorSaida := 'In�cio dos registros na �rea de dados.' ;
         end;
   #26 : case sRetorno[2] of
           #1 : sValorSaida := 'O rel�gio interno do ECF com hor�rio anterior ao do �ltimo comando enviado.' ;
           #2 : sValorSaida := 'O rel�gio interno do ECF encontra-se adiantado em 30 dias ou mais em rela��o ao �ltimo comando enviado.' ;
          #16 : sValorSaida := 'N�o foi localizado o registro de n�mero de s�rie na Mem�ria Fiscal.' ;
          #17 : sValorSaida := 'N�mero de s�rie do ECF incompat�vel no registro da Mem�ria Fiscal.' ;
          #18 : sValorSaida := 'Marca do ECF incompat�vel no registro da Mem�ria Fiscal.' ;
          #19 : sValorSaida := 'Tipo do ECF incompat�vel no registro da Mem�ria Fiscal.' ;
          #20 : sValorSaida := 'Modelo do ECF incompat�vel no registro da Mem�ria Fiscal.' ;
          #21 : sValorSaida := 'N�mero de s�rie da Mem�ria de Fita Detalhe incompat�vel no registro da Mem�ria Fiscal.' ;
          #22 : sValorSaida := 'Logotipo da NCR do ECF incompat�vel no registro da Mem�ria Fiscal.' ;
          #23 : sValorSaida := 'N�o foi localizado o registro de usu�rio na Mem�ria Fiscal.' ;
          #24 : sValorSaida := 'CNPJ incompat�vel no registro da Mem�ria Fiscal.' ;
          #25 : sValorSaida := 'Inscri��o Estadual incompat�vel no registro da Mem�ria Fiscal.' ;
          #26 : sValorSaida := 'Inscri��o Municipal incompat�vel no registro da Mem�ria Fiscal.' ;
          #27 : sValorSaida := 'Criptografia do GT incompat�vel no registro da Mem�ria Fiscal.' ;
          #28 : sValorSaida := 'Logotipo BR incompat�vel no registro da Mem�ria Fiscal.' ;
          #29 : sValorSaida := 'Mecanismo impressor incompat�vel no registro da Mem�ria Fiscal.' ;
          #31 : sValorSaida := 'N�o foi localizado o registro de s�mbolo da moeda na Mem�ria Fiscal.' ;
          #32 : sValorSaida := 'S�mbolo da moeda incompat�vel no registro da Mem�ria Fiscal.' ;
          #33 : sValorSaida := 'N�o foi localizado o registro de criptografia do GT na Mem�ria Fiscal.' ;
          #34 : sValorSaida := 'N�o foi localizado o registro de troca de vers�o na Mem�ria Fiscal.' ;
          #35 : sValorSaida := 'Erro de integridade no MD5.' ;
         end;
   #27 : case sRetorno[2] of
           #1 : sValorSaida := 'Configura��o das DIP switches inv�lida.' ;
         end;  
   #29 : case sRetorno[2] of
           #1 : sValorSaida := 'Configura��o de baudrate inv�lida para o mecanismo impressor.' ;
           #2 : sValorSaida := 'Erro mec�nico no mecanismo impressor.' ;
           #3 : sValorSaida := 'Erro irrecuper�vel no mecanismo impressor.' ;
           #4 : sValorSaida := 'Erro no autocutter do mecanismo impressor.' ;
           #5 : sValorSaida := 'Erro auto recuper�vel no mecanismo impressor.' ;
         end;
   #31 : case sRetorno[2] of
           #1 : sValorSaida := 'O tamanho do texto do valor por extenso � maior do que o espa�o dispon�vel para impress�o.' ;
           #2 : sValorSaida := 'O tamanho do texto do favorecido � maior do que o espa�o dispon�vel para impress�o.' ;
           #3 : sValorSaida := 'O tamanho do texto do local � maior do que o espa�o dispon�vel para impress�o.' ;
           #4 : sValorSaida := 'Posi��o inicial para impress�o do dia da data do cheque inv�lida.' ;
           #5 : sValorSaida := 'O tamanho do texto do dia da data do cheque � maior do que o espa�o dispon�vel para impress�o.' ;
           #6 : sValorSaida := 'Posi��o inicial para impress�o do m�s da data do cheque inv�lida.' ;
           #7 : sValorSaida := 'O tamanho do texto do m�s da data do cheque � maior do que o espa�o dispon�vel para impress�o.' ;
           #8 : sValorSaida := 'O tamanho do texto do ano da data do cheque � maior do que o espa�o dispon�vel para impress�o.' ;
           #9 : sValorSaida := 'Posi��o inicial para impress�o da linha adicional do cheque inv�lida.' ;
          #10 : sValorSaida := 'Altura da primeira linha de extenso inv�lida.' ;
          #11 : sValorSaida := 'Altura da segunda linha de extenso inv�lida.' ;
          #12 : sValorSaida := 'Altura da linha do favorecido inv�lida.' ;
          #13 : sValorSaida := 'Altura da linha do local e data inv�lida.' ;
          #14 : sValorSaida := 'Altura da linha adicional inv�lida.' ;
          #15 : sValorSaida := 'Altura da linha do valor inv�lida.' ;
          #16 : sValorSaida := 'Posi��o inicial para impress�o da primeira linha do extenso do cheque inv�lida.' ;
          #17 : sValorSaida := 'Posi��o inicial para impress�o do local inv�lida.' ;
          #18 : sValorSaida := 'Tamanho da linha adicional inv�lido.' ;
         end;
   #32 : case sRetorno[2] of
           #1 : sValorSaida := 'Velocidade da porta serial inv�lida.' ;
         end;

  end;

  Result := sValorSaida;
end;



{ ----------------------------- TACBrECFNCR ----------------------------- }

constructor TACBrECFNCR.create( AOwner : TComponent ) ;
begin
  inherited create( AOwner ) ;

  fsNCRComando   := TACBrECFNCRComando.create ;
  fsNCRResposta  := TACBrECFNCRResposta.create ;

  fpDevice.Baud      := 115200;
  fpDevice.Parity    := pNone;
  fpDevice.Stop      := s1;
  fpDevice.Data      := 8;
  fpDevice.HandShake := hsNenhum;
  fpDecimaisQtd      := 3 ;
  fpDecimaisPreco    := 2 ;

  { Variaveis internas dessa classe }
  fsNumLoja   := '' ;
  fsCNPJ      := '' ;
  fsIE        := '' ;
  fpModeloStr := 'NCR 7167/7197' ;
  fpMFD       := True ;
  fpTermica   := True ;
  fpIdentificaConsumidorRodape := True ;
  fsImprimeCheque := False ;
  fsLeituraCMC7   := False ;
  fsHorarioVerao  := '' ;
end;

destructor TACBrECFNCR.Destroy;
begin
  fsNCRComando.Free ;
  fsNCRResposta.Free ;

  inherited Destroy ;
end;

procedure TACBrECFNCR.Sincroniza ;
var
  ByteSYN : Byte;
begin
  fpDevice.Serial.DeadlockTimeout := 2000 ; { Timeout p/ Envio }

  ByteSYN := 0;
  while (ByteSYN <> SYN) do     { Se ACK = 6 Comando foi reconhecido }
  begin
    fpDevice.Serial.Purge ;                   { Limpa a Porta }
    fpDevice.Serial.SendByte( SYN );  { Solicita sincronismo }

    ByteSYN := fpDevice.Serial.RecvByte( 200 ) ; { Resposta sincronismo }
    Try
      if ByteSYN = SYN then
      begin
         try
          NCRComando.Seq := fpDevice.Serial.RecvByte( 200 ) ; { Pega numero de sequencia }
         except
         end;
      end
      else
      if ByteSYN = WAK then
      begin
        sleep( 1000 ) ;
        continue ;
      end
      else
        raise EACBrECFSemResposta.create( ACBrStr(
              'Impressora '+fpModeloStr+' n�o responde ' ) );

    except
      on E : EACBrECFSemResposta do
      begin
        fpDevice.Serial.Purge ;

        if not DoOnMsgRetentar( E.Message +sLineBreak+sLineBreak+
          'Se o problema persistir, verifique os cabos, ou'+sLineBreak+
          'experimente desligar a impressora durante 5 seg,'+sLineBreak+
          'liga-la novamente, e repetir a opera��o...'
          , 'Sincronismo') then
        raise ;
      end ;
      else
         raise ;
    end ;
  end;
end;

procedure TACBrECFNCR.Ativar;
begin
  if not fpDevice.IsSerialPort  then
     raise EACBrECFERRO.Create(ACBrStr('A impressora: '+fpModeloStr+' requer'+sLineBreak+
                            'Porta Serial:  (COM1, COM2, COM3, ...)'));

  inherited Ativar ; { Abre porta serial }

  fsNumLoja       := '' ;
  fsCNPJ          := '' ;
  fsIE            := '' ;
  fsHorarioVerao  := '' ;
  fsImprimeCheque := False ;
  fsLeituraCMC7   := False ;
  try
     try
        Sincroniza ; // Sincroniza o sequencial de comando com a impressora

        // Verifica Modo de Opera��o
        NCRComando.Comando := '63' ;
        EnviaComando ;

        if NCRResposta.Params[0] <> '0' then  // Diferente de Modo Normal ?
           raise EACBrECFERRO.Create(ACBrStr('A impressora: '+fpModeloStr+' esta em'+sLineBreak+
                                  'modo de interven��o t�cnica.'));
                                  
        NCRComando.Comando := '187' ;  // Obtendo o numero de colunas
        EnviaComando ;

        fpColunas := StrToIntDef( NCRResposta.Params[11], 48 ) ;
        fsImprimeCheque :=  NCRResposta.Params[2] = '1';
        fsLeituraCMC7   :=  NCRResposta.Params[5] = '1';
     except
        raise EACBrECFNaoInicializado.Create( ACBrStr(
                 'Erro inicializando a impressora '+fpModeloStr ));
     end ;
  except
     Desativar ;
     raise ;
  end ;
end;

Function TACBrECFNCR.EnviaComando_ECF( cmd : AnsiString = '' ) : AnsiString ;
Var ErroMsg    : String ;
    OldTimeOut : Integer ;
    ByteACK    : Byte ;
    ByteSOH    : Byte ;
begin
  cmd := NCRComando.PacoteEnvio ;

  ByteACK := 0 ;
  Result  := '' ;
  ErroMsg := '' ;
  fpComandoEnviado   := '' ;
  fpRespostaComando  := '' ;
  NCRResposta.Resposta := '' ;  // Zera resposta
  OldTimeOut := TimeOut ;
  TimeOut    := max(NCRComando.TimeOut, TimeOut) ;
  fpDevice.Serial.DTR := false ;  
  fpDevice.Serial.RTS := false ;

  try
     fpDevice.Serial.DeadlockTimeout := 2000 ; { Timeout p/ Envio }

     while (ByteACK <> ACK) do     { Se ACK = 6 Comando foi reconhecido }
     begin
        ByteACK := 0 ;
        fpDevice.Serial.Purge ;                   { Limpa a Porta }

        if not TransmiteComando( cmd ) then
           continue ;

        try
           { espera ACK chegar na Porta por 2s  }
           try
              ByteACK := fpDevice.Serial.RecvByte( 2000 ) ;
           except
           end ;

           if ByteACK = 0 then
              raise EACBrECFSemResposta.create( ACBrStr(
                    'Impressora '+fpModeloStr+' n�o responde (ACK = 0)' ) )
           else if ByteACK = NAK then    { retorno em caracter 21d=15h=NACK }
              raise EACBrECFSemResposta.create( ACBrStr(
                    'Impressora '+fpModeloStr+' n�o reconheceu o Comando'+
                    sLineBreak+' (NAK)') )
           else if ByteACK = WAK then { retorno = WAK espera 1 segundo e reenvia solicita��o }
           begin
             sleep( 1000 ) ;
             continue ;
           end
           else if ByteACK <> 6 then
              raise EACBrECFSemResposta.create( ACBrStr(
                    'Erro. Resposta da Impressora '+fpModeloStr+' inv�lida'+
                    sLineBreak+' (ACK = '+IntToStr(ByteACK)+')') ) ;
        except
           on E : EACBrECFSemResposta do
            begin
              fpDevice.Serial.Purge ;

              if not DoOnMsgRetentar( E.Message +sLineBreak+sLineBreak+
                 'Se o problema persistir, verifique os cabos, ou'+sLineBreak+
                 'experimente desligar a impressora durante 5 seg,'+sLineBreak+
                 'liga-la novamente, e repetir a opera��o...'
                 , 'LerACK') then
                 raise ;
            end ;
           else
              raise ;
        end ;

     end ;

     fpComandoEnviado := cmd ;
     sleep( 100 ) ;
     ByteSOH := 0 ;
     while ByteSOH <> Ord( SOH ) do
     begin
       { Envia pedido para encaminhar a resposta da ECF }
       fpDevice.Serial.SendByte( ord(ENQ) );

       { Chama Rotina da Classe m�e TACBrClass para ler Resposta. Se houver
         falha na leitura LeResposta dispara Exce�ao.
         Resposta fica gravada na v�riavel "fpRespostaComando" }
       LeResposta ;

       Try
          ByteSOH := ord( fpRespostaComando[1] ) ;

          if ByteSOH = 0 then
             raise EACBrECFSemResposta.create( ACBrStr(
                   'Impressora '+fpModeloStr+' n�o responde (SOH = 0)' ) )
          else if ByteSOH = NAK then    { retorno em caracter 21d=15h=NACK }
             raise EACBrECFSemResposta.create( ACBrStr(
                   'Impressora '+fpModeloStr+' n�o reconheceu o Comando'+
                   sLineBreak+' (NAK)') )
          else if ByteSOH = WAK then { retorno = WAK espera 1 segundo e reenvia solicita��o }
          begin
            sleep( 1000 ) ;
            continue ;
          end
          else if ByteSOH = Ord( CAN ) then { retorno = CAN erro na execu��o }
          begin
            NCRResposta.Resposta := fpRespostaComando ;
            ErroMsg := 'Erro: '+ IntToStrZero(Ord(NCRResposta.Retorno[1]), 3)+'/'+
                                 IntToStrZero(Ord(NCRResposta.Retorno[2]), 3)+
                                 ' - '+NCRResposta.DescRetorno  ;
            break ;
          end
          else if ByteSOH <> 1 then
             raise EACBrECFSemResposta.create( ACBrStr(
                   'Erro. Resposta da Impressora '+fpModeloStr+' inv�lida'+
                   sLineBreak+' (SOH = '+IntToStr(ByteSOH)+')') ) ;

          NCRResposta.Resposta := fpRespostaComando ;
          if NCRResposta.Seq <> NCRComando.Seq then
             raise EACBrECFERRO.Create(ACBrStr('Sequencia de Resposta diferente da enviada')) ;
           
       except
          on E : Exception do
          begin
             ErroMsg := E.Message ;
             break ;
          end
          else
            break ;
       end ;
     end;

     if ErroMsg <> '' then
      begin
        ErroMsg := ACBrStr('Erro retornado pela Impressora: '+fpModeloStr+#10+#10+
                   ErroMsg );
        raise EACBrECFSemResposta.create(ErroMsg) ;
      end
     else
        Sleep( IntervaloAposComando ) ;  { Pequena pausa entre comandos }

  finally
     TimeOut := OldTimeOut ;
  end ;
end;

Function TACBrECFNCR.VerificaFimLeitura(var Retorno: AnsiString;
   var TempoLimite: TDateTime) : Boolean ;
var
//  ByteWAK : Byte;
  TamRetorno : Integer;
begin
  Result := (LeftStr(Retorno,1) = chr( WAK )) ;
  if not result then
  begin
//  Result := (LeftStr(Retorno,1) = SOH) or (LeftStr(Retorno,1) = CAN) ;

    Result := Length( Retorno ) >= 5 ;
    if Result then
    begin
      // Verifica se pacote veio completo, caso comando sem erro
      if LeftStr(Retorno,1) = SOH then
      begin
        TamRetorno := Synautil.BinToInt( CharToBin( Retorno[5] ) + CharToBin( Retorno[4] ) );
        Result := Length( Retorno ) >= (TamRetorno + 6) ;
      end;
    end ;
  end ;
end;

function TACBrECFNCR.GetDataHora: TDateTime;
Var RetCmd : AnsiString ;
    OldShortDateFormat : String ;
begin
  NCRComando.Comando := '12' ;
  EnviaComando ;
  RetCmd := NCRResposta.Params[0] ;
  OldShortDateFormat := ShortDateFormat ;
  try
     ShortDateFormat := 'dd/mm/yy' ;
     Result := StrToDate(copy(RetCmd, 1,2) + DateSeparator +
                         copy(RetCmd, 3,2) + DateSeparator +
                         copy(RetCmd, 5,2)) ;
  finally
     ShortDateFormat := OldShortDateFormat ;
  end ;

  RetCmd := NCRResposta.Params[1] ;
  Result := RecodeHour(  Result,StrToInt(copy(RetCmd,1,2))) ;
  Result := RecodeMinute(Result,StrToInt(copy(RetCmd,3,2))) ;
  Result := RecodeSecond(Result,StrToInt(copy(RetCmd,5,2))) ;

  fsHorarioVerao := NCRResposta.Params[2] ;
end;

function TACBrECFNCR.GetNumCupom: String;
begin
  NCRComando.Comando := '220' ;
  NCRComando.AddParam('02');
  EnviaComando ;
  Result := NCRResposta.Params[0] ;
end;

function TACBrECFNCR.GetNumCCF: String;
begin
  NCRComando.Comando := '220' ;
  NCRComando.AddParam('05');
  EnviaComando ;
  Result := NCRResposta.Params[0] ;
end;

function TACBrECFNCR.GetNumCRO: String;
begin
  NCRComando.Comando := '220' ;
  NCRComando.AddParam('01');
  EnviaComando ;

  Result := NCRResposta.Params[0] ;
end;

function TACBrECFNCR.GetNumLoja: String;
begin
  if fsNumLoja = '' then
  begin
     NCRComando.Comando := '3' ;
     NCRComando.AddParam('0');
     EnviaComando ;

     fsNumLoja := NCRResposta.Params[0] ;
     fsCNPJ    := NCRResposta.Params[1] ;
     fsIE      := NCRResposta.Params[2] ;
  end ;

  Result := fsNumLoja ;
end;

function TACBrECFNCR.GetNumECF: String;
begin
  NCRComando.Comando := '7' ;
  EnviaComando ;

  Result := NCRResposta.Params[0] ;
end;

function TACBrECFNCR.GetNumSerie: String;
begin
  NCRComando.Comando := '1' ;
  EnviaComando ;

  Result      := NCRResposta.Params[0] ;
end;

function TACBrECFNCR.GetNumVersao: String ;
begin
  NCRComando.Comando := '189' ;
  NCRComando.AddParam('0') ;
  EnviaComando ;

  Result :=  NCRResposta.Params[0] +'.'+NCRResposta.Params[1]+'.'+NCRResposta.Params[2] ;
end;

function TACBrECFNCR.GetTotalPago: Double;
begin
  try
     NCRComando.Comando := '70' ;
     NCRComando.AddParam('98');
     EnviaComando ;

     Result := StrToFloatDef(NCRResposta.Params[0],0) /100 ;
     Result := RoundTo( Result, -2) ;
  except
     on E : Exception do
     begin
       Result := 0 ;
       if Pos('012/015', E.Message) = 0 then
          raise ;
     end ;
  end ;
end;

function TACBrECFNCR.GetSubTotal: Double;
begin
  try
     NCRComando.Comando := '70' ;
     NCRComando.AddParam('95');
     EnviaComando ;

     Result := StrToFloatDef(NCRResposta.Params[0],0) /100 ;
     Result := RoundTo( Result, -2) ;
  except
     on E : Exception do
     begin
       Result := 0 ;
       if Pos('012/015', E.Message) = 0 then
          raise ;
     end ;
  end ;
end;


function TACBrECFNCR.GetEstado: TACBrECFEstado;
  Var
//    BitS : String ;
      StatusRZ : String ;
      DataMov  : String ;
      DataMovLiber  : TDateTime ;
      Dia, Mes, Ano : Word ;
begin
//  fpEstadoGeral := [];
  if (not fpAtivo) then
     fpEstado := estNaoInicializada
  else
  begin
    fpEstado := estDesconhecido ;

    // Verifica Estado da Redu��o Z
    NCRComando.Comando := '65' ;
    EnviaComando ;
    StatusRZ := NCRResposta.Params[0] ;
    DataMov  := NCRResposta.Params[1] ;

    if (StatusRZ = '9') then
    begin
      if fpEstado = estDesconhecido then
         fpEstado := estRequerZ ;
//      Include(fpEstadoGeral, estRequerZ) ;
    end;

    if (StatusRZ = '1') and
       (DataMov  = '000000') then
    begin
      NCRComando.Comando := '60' ; // Pega dados da ultima Reducao Z
      EnviaComando ;

      // Pega a Data e hora limite que a ECF estar� bloqueada
      DataMovLiber := 0 ;
      if NCRResposta.Params[1] <> '000000' then
      begin
        Dia := StrToInt(Copy(NCRResposta.Params[1], 1, 2));
        Mes := StrToInt(Copy(NCRResposta.Params[1], 3, 2));
        Ano := StrToInt('20' + Copy(NCRResposta.Params[1], 5, 2));

        // Pois a ECF fica bloqueada at� as 02:00h do dia seguinte da data do movimento
        DataMovLiber := EncodeDate(Ano, Mes, Dia);
        DataMovLiber := IncDay(DataMovLiber, 1) ;
        DataMovLiber := RecodeHour( DataMovLiber, 2) ;
        DataMovLiber := RecodeMinute( DataMovLiber, 0) ;
        DataMovLiber := RecodeSecond( DataMovLiber, 0) ;
      end;

      if GetDataHora <= DataMovLiber then
      begin
        if fpEstado = estDesconhecido then
           fpEstado := estBloqueada ;
//        Include(fpEstadoGeral, estBloqueada) ;
      end
      else
      begin
        if fpEstado = estDesconhecido then
           fpEstado := estRequerX ;
//        Include(fpEstadoGeral, estRequerX) ;
      end;
    end;

    // Verifica Contexto Atual
    NCRComando.Comando := '64' ;
    EnviaComando ;

    if NCRResposta.Params[0] = '15' then
    begin
      if fpEstado = estDesconhecido then
         fpEstado := estLivre ;
//      Include(fpEstadoGeral, estLivre) ;
    end;

    if (NCRResposta.Params[1] >= '04') then
    begin
      if fpEstado = estDesconhecido then
         fpEstado := estPagamento ;
//      Include(fpEstadoGeral, estPagamento) ;
    end;

    if (NCRResposta.Params[0] = '04') then
    begin
      if fpEstado = estDesconhecido then
         fpEstado := estVenda ;
//      Include(fpEstadoGeral, estVenda);
    end ;

    if (NCRResposta.Params[0] = '14') then
    begin
      if fpEstado = estDesconhecido then
         fpEstado := estRelatorio ;
//      Include(fpEstadoGeral, estRelatorio) ;
    end ;

    if (NCRResposta.Params[0] = '07') or
       (NCRResposta.Params[0] = '09') then
    begin
      if fpEstado = estDesconhecido then
         fpEstado := estNaoFiscal ;
//      Include(fpEstadoGeral, estNaoFiscal) ;
    end ;
    
  end ;

  Result := fpEstado ;
end;

function TACBrECFNCR.GetGavetaAberta: Boolean;
begin
  NCRComando.Comando := '67' ;
  EnviaComando ;

  Result := (NCRResposta.Params[0] = '1') ;
end;

function TACBrECFNCR.GetPoucoPapel: Boolean;
begin
  NCRComando.Comando := '66' ;
  EnviaComando ;

  Result := (NCRResposta.Params[0] = '1') ;
end;

function TACBrECFNCR.GetHorarioVerao: Boolean;
begin
  if fsHorarioVerao = '' then
     GetDataHora ;

  Result := (fsHorarioVerao = '1') ;
end;

Procedure TACBrECFNCR.LeituraX ;
begin
  if fpEstado = estRequerx then
     NCRComando.Comando := '18'
  else
     NCRComando.Comando := '19' ;

  NCRComando.TimeOut := TempoInicioMsg + 2 ;  // apenas para o bloqueio de teclado funcionar
  EnviaComando ;
end;

Procedure TACBrECFNCR.AbreGaveta ;
begin
  NCRComando.Comando := '206' ;
  EnviaComando ;
end;

Procedure TACBrECFNCR.ReducaoZ(DataHora: TDateTime) ;
begin
  NCRComando.Comando := '17' ;
  NCRComando.AddParam('0');
  NCRComando.AddParam('0');
  NCRComando.TimeOut := TempoInicioMsg + 2 ;  // apenas para o bloqueio de teclado funcionar

  EnviaComando ;
end;

Procedure TACBrECFNCR.MudaHorarioVerao ;
begin
  MudaHorarioVerao( not HorarioVerao ) ;
end;

procedure TACBrECFNCR.MudaHorarioVerao(EHorarioVerao: Boolean);
begin
  NCRComando.Comando := '11' ;
  if not EHorarioVerao then
     NCRComando.AddParam('0')
  else
     NCRComando.AddParam('1');

  EnviaComando ;
end;

procedure TACBrECFNCR.AbreCupom ;
begin
  NCRComando.Comando  := '21' ;
  NCRComando.AddParam('4');
  
  EnviaComando ;
end;

function  TACBrECFNCR.BuscaSequenciaVinculado( CodFormaPagto : String ) : String ; 
Var A, TotVinc : Integer ;
begin
  result := '' ; 
  NCRComando.Comando  := '224' ; // Verifica se tem documento vinculado para cancelar
  EnviaComando ;
  if CodFormaPagto <> '' then
     TotVinc := StrToIntDef( NCRResposta.Params[2], 0 )
  else
     TotVinc := StrToIntDef( NCRResposta.Params[0], 0 ) ;

  for A := 1 to  TotVinc do
  begin
    NCRComando.Comando  := '225' ; // Verifica a sequencia do pagamento a cancelar
    NCRComando.AddParam( IntToStr(A) );
    EnviaComando ;
    if CodFormaPagto <> '' then
    begin
       if NCRResposta.Params[3] = CodFormaPagto then
       begin
         result := NCRResposta.Params[0] ;
         break ;
       end;
    end
    else
    begin
      if NCRResposta.Params[2] = '2' then // Comprovante Emitido
      begin
        result := NCRResposta.Params[0] ;
        break ;
      end;
    end
  end;
end ;
 
procedure TACBrECFNCR.CancelaCupom;
var SeqVinculado : String ;
begin
  NCRComando.Comando  := '68' ;
  EnviaComando ;

  if NCRResposta.Params[0] = '0' then
     raise EACBrECFERRO.create(ACBrStr('N�o existe documento para ser cancelado.')) ;

  SeqVinculado := BuscaSequenciaVinculado;
  if SeqVinculado <> '' then
  begin
    NCRComando.Comando  := '211' ;          // Cancela comprovante vinculado
    NCRComando.AddParam( SeqVinculado ) ;   // Seq��ncia do pagamento
    NCRComando.AddParam( '1' ) ;            // Numero de Parcelas
    NCRComando.AddParam( '0' ) ;            // Armazena Linhas na NVRAM 0/1
    EnviaComando ;

    NCRComando.Comando  := '22' ;           // Fecha o comprovante de estorno de CCD
    EnviaComando ;
  end;

  NCRComando.Comando  := '37' ;
  EnviaComando ;
end;

procedure TACBrECFNCR.CancelaItemVendido(NumItem: Integer);
begin
  NCRComando.Comando  := '35' ;
  NCRComando.AddParam(IntToStr(NumItem)) ;
  EnviaComando ;
end;

procedure TACBrECFNCR.EfetuaPagamento(CodFormaPagto: String;
  Valor: Double; Observacao: AnsiString; ImprimeVinculado: Boolean);
//Var
//  IdxSeq : Integer ;
begin
  NCRComando.Comando  := '42' ;
  NCRComando.AddParam( CodFormaPagto ) ;
  NCRComando.AddParam( '1' ) ;
  NCRComando.AddParam( FloatToStr( Valor ) ) ;
  NCRComando.AddParam( Observacao ) ;
  EnviaComando ;
end;

procedure TACBrECFNCR.FechaCupom(Observacao: AnsiString; IndiceBMP : Integer);
 Var SL : TStringList ;
     I  : Integer ;
begin
  if not Consumidor.Enviado then
  begin
     NCRComando.Comando  := '207' ;
     NCRComando.AddParam(LeftStr(Consumidor.Documento,20));
     NCRComando.AddParam(LeftStr(Consumidor.Nome,30));
     NCRComando.AddParam(copy(Consumidor.Endereco, 1,40));
     NCRComando.AddParam(copy(Consumidor.Endereco,41,39));
     EnviaComando ;
  end ;

  if Trim(Observacao) <> '' then
  begin
     Observacao := AjustaLinhas(Observacao, 42) ;
     SL := TStringList.create ;
     try
        SL.Text := Observacao ;
        NCRComando.Comando  := '233' ;
        for I := 0 to SL.Count-1 do
           if I <= 1 then
              NCRComando.AddParam(SL[I]) ;
{ ???
        for A := I to 1 do
           NCRComando.AddParam('') ;
}
        EnviaComando ;
     finally
        SL.Free ;
     end ;
  end ;

  NCRComando.Comando  := '22' ;
  EnviaComando ;
end;

procedure TACBrECFNCR.SubtotalizaCupom(DescontoAcrescimo: Double;
       MensagemRodape : AnsiString);
begin
  if DescontoAcrescimo <> 0 then
  begin
    NCRComando.Comando  := '38' ; // Desconto no SubTotal
    if DescontoAcrescimo > 0 then
       NCRComando.Comando  := '40' ; // Acrescimo no SubTotal
    NCRComando.AddParam( FloatToStr( abs(DescontoAcrescimo) )  );
    EnviaComando ;
  end;
  
  NCRComando.Comando  := '36' ;
  EnviaComando ;
end;

Procedure TACBrECFNCR.VendeItem( Codigo, Descricao : String;
  AliquotaECF : String; Qtd : Double ; ValorUnitario : Double;
  ValorDescontoAcrescimo : Double; Unidade : String;
  TipoDescontoAcrescimo : String; DescontoAcrescimo : String ;
  CodDepartamento: Integer) ;
 Var
  ValDesc, ValTotal : Double ;
  IndAliq : Integer ;  
begin
  with NCRComando do
  begin
    ValTotal := RoundABNT(TruncFix(Qtd*ValorUnitario*100)/100,-2) ;
    Comando := '30' ;
    AddParam( LeftStr(Codigo,14) );
    AddParam( LeftStr(Descricao,233) );
    AddParam( FloatToStr(Qtd)  );
    AddParam( LeftStr(Unidade,3) );
    AddParam( FloatToStr(ValorUnitario)  );
    AddParam( FloatToStr(ValTotal)  );

    IndAliq := StrToIntDef( AliquotaECF, -1);
    if IndAliq >= 0 then
    begin
      AddParam( '1' ) ;
      AddParam( FormatFloat('###,##0.00',Aliquotas[Pred(IndAliq)].Aliquota) );
    end
    else
    begin
      if AliquotaECF = 'F' then
         AddParam( '3' )
      else if AliquotaECF = 'N' then
         AddParam( '4' )
      else
         AddParam( '2' ) ;

      AddParam( '0,00' );
    end;
  end ;
  EnviaComando ;

  { Se o desconto � maior que zero d� o comando de desconto de item }
  if ValorDescontoAcrescimo > 0 then
  begin
    if TipoDescontoAcrescimo = '%' then
       ValDesc := RoundTo( ValTotal * (ValorDescontoAcrescimo / 100), -2)
    else
       ValDesc := ValorDescontoAcrescimo ;

    if DescontoAcrescimo = 'D' then
       NCRComando.Comando  := '31'
    else
       NCRComando.Comando  := '33' ;
    NCRComando.AddParam( '0' ) ;
    NCRComando.AddParam( FloatToStr(ValDesc) ) ;
    EnviaComando ;
  end;
end;

procedure TACBrECFNCR.CarregaAliquotas;
var
  A         : Integer;
  Aliquota  : TACBrECFAliquota ;
  Aliq, TotAliq : Double ;
  IndAliq   : String ;
  iAliquotas: Integer ;
begin
  inherited CarregaAliquotas ;   { Cria fpAliquotas }

  // Le a Quantidade de Aliquotas Cadastradas
  NCRComando.Comando := '160' ;
  NCRComando.Params.Add( '1' ) ;
  EnviaComando ;

  iAliquotas :=  StrToIntDef( NCRResposta.Params[0], 0 ) ;
  for A := 1 to iAliquotas do
  begin
    // Le a Aliquota Cadastrada
    IndAliq := IntToStrZero( A , 2 ) ;
    NCRComando.Comando := '161' ;
    NCRComando.Params.Add( '1' ) ;
    NCRComando.Params.Add( IndAliq ) ;
    EnviaComando ;

    Aliq := RoundTo( StrToIntDef( NCRResposta.Params[0], 0) / 100, -2 ) ;

    // Le Valores Acumulados na Aliquota
    NCRComando.Comando := '72' ;
    NCRComando.Params.Add( '1' ) ;
    NCRComando.Params.Add( FloatToStr(Aliq) ) ;
    EnviaComando ;

    TotAliq  := StrToFloatDef(NCRResposta.Params[1], 0 ) ;

    Aliquota := TACBrECFAliquota.create;

    Aliquota.Indice   := IndAliq ;
    Aliquota.Tipo     := 'T';
    Aliquota.Aliquota := Aliq ;
    Aliquota.Total    := TotAliq ;

    fpAliquotas.Add(Aliquota);
  end;
end;

procedure TACBrECFNCR.LerTotaisAliquota;
begin
  CarregaAliquotas ;
end;


procedure TACBrECFNCR.ProgramaAliquota(Aliquota: Double; Tipo: Char;
   Posicao : String);
begin
  Tipo := UpCase(Tipo) ;

  NCRComando.Comando := '100' ;
  if Tipo = 'T' then
     NCRComando.Params.Add('1')
  else
     NCRComando.Params.Add('0');
  NCRComando.Params.Add( FloatToStr( Aliquota ) ) ;
  EnviaComando ;

  CarregaAliquotas ;
end;

procedure TACBrECFNCR.CarregaFormasPagamento;
Var A     : Integer ;
    Qtde  : Integer ;
    Ident : String ;
    FPagto : TACBrECFFormaPagamento ;

begin
  inherited CarregaFormasPagamento ;   { Cria fpFormasPagamentos }

  // Le a Quantidade de Formas de Pagamento Cadastradas
  NCRComando.Comando := '167' ;
  EnviaComando ;

  Qtde :=  StrToIntDef( NCRResposta.Params[0], 0 ) ;

  // Posiciona na primeira forma de pagamento cadastrado
  NCRComando.Comando := '168' ;
  EnviaComando ;

  for A := 1 to Qtde do
  begin
    // Pega a proxima forma de pagamento a ser lida
    NCRComando.Comando := '169' ;
    EnviaComando ;
    Ident := NCRResposta.Params[0] ;

    // Pega descricao da forma de pagamento
    NCRComando.Comando := '166' ;
    NCRComando.AddParam( Ident );
    EnviaComando ;

    try
       FPagto := TACBrECFFormaPagamento.create ;
       FPagto.Indice    := Ident ;
       FPagto.Descricao := NCRResposta.Params[1] ;
       FPagto.PermiteVinculado := NCRResposta.Params[0] = '1' ;

       fpFormasPagamentos.Add( FPagto ) ;
    except
       on E : Exception do
       begin
         fpFormasPagamentos.Free ;
         fpFormasPagamentos := nil ;
         raise ;
       end ;
    end;
  end;
end;

procedure TACBrECFNCR.LerTotaisFormaPagamento;
Var A : Integer ;
begin
  if not Assigned( fpFormasPagamentos ) then
     CarregaFormasPagamento ;

  For A := 0 to FormasPagamento.Count-1 do
  begin
    // Le Valores Acumulados na Forma de Pagamento
    NCRComando.Comando := '74' ;
    NCRComando.AddParam( FormasPagamento[A].Indice );
    EnviaComando ;
    FormasPagamento[A].Total := RoundTo( StrToFloatDef( NCRResposta.Params[0], 0) / 100, -2) ;
  end ;
end;

procedure TACBrECFNCR.ProgramaFormaPagamento( var Descricao: String;
  PermiteVinculado : Boolean; Posicao : String) ;
var  FPagto: TACBrECFFormaPagamento ;
     ProxIndice : Integer ;
begin
  ProxIndice := StrToIntDef(Posicao,0) ;
  if (ProxIndice < 1) or (ProxIndice > 20) then { Indice passado � v�lido ? }
  begin
     For ProxIndice := 2 to 20 do  { Procurando Lacuna }
     begin
        if AchaFPGIndice(IntToStr(ProxIndice)) = nil then
           break ;
     end ;
  end ;

  if ProxIndice > 20 then
     raise EACBrECFERRO.create(ACBrStr('N�o h� espa�o para programar novas Formas de '+
                            'Pagamento'));

  NCRComando.Comando := '109' ;
  NCRComando.AddParam( IntToStr(ProxIndice) ) ;
  if PermiteVinculado then
     NCRComando.AddParam( '1' )
  else
     NCRComando.AddParam( '0' ) ;
  NCRComando.AddParam( LeftStr(Descricao,15) ) ;
  EnviaComando ;

  { Adicionando nova FPG no ObjectList }
  if Assigned( fpFormasPagamentos ) then
  begin
     FPagto := TACBrECFFormaPagamento.create ;
     FPagto.Indice    := IntToStr(ProxIndice) ;
     FPagto.Descricao := LeftStr(Descricao,15) ;
     FPagto.PermiteVinculado := PermiteVinculado  ;

     fpFormasPagamentos.Add( FPagto ) ;
  end ;
end;

procedure TACBrECFNCR.CarregaComprovantesNaoFiscais;
Var A    : Integer ;
    CNF  : TACBrECFComprovanteNaoFiscal ;
    Qtde : Integer ;
    Ident : String ;
begin
  inherited CarregaComprovantesNaoFiscais ;

  // Le a quantidade de totalizadores n�o fiscais cadastrados
  NCRComando.Comando := '163' ;
  EnviaComando ;

  Qtde :=  StrToIntDef( NCRResposta.Params[0], 0 ) ;

  // Posiciona no primeiro totalizador cadastrado
  NCRComando.Comando := '164' ;
  EnviaComando ;

  for A := 1 to Qtde do
  begin
    // Pega o proximo totalizador a ser lido
    NCRComando.Comando := '165' ;
    EnviaComando ;
    Ident := NCRResposta.Params[0] ;

    // Pega descricao da forma de pagamento
    NCRComando.Comando := '162' ;
    NCRComando.AddParam( Ident );
    EnviaComando ;

    try
      CNF := TACBrECFComprovanteNaoFiscal.create ;

      CNF.Indice    := Ident ;
      CNF.Descricao := NCRResposta.Params[0] ;

      fpComprovantesNaoFiscais.Add( CNF ) ;
    except
       on E : Exception do
       begin
         fpComprovantesNaoFiscais.Free ;
         fpComprovantesNaoFiscais := nil ;
         raise ;
       end ;
    end;
  end;
end;

procedure TACBrECFNCR.LerTotaisComprovanteNaoFiscal;
Var A : Integer ;
begin
  if not Assigned( fpComprovantesNaoFiscais ) then
     CarregaComprovantesNaoFiscais ;

  For A := 0 to ComprovantesNaoFiscais.Count-1 do
  begin
    // Le Valores Acumulados na Forma de Pagamento
    NCRComando.Comando := '76' ;
    NCRComando.AddParam( ComprovantesNaoFiscais[A].Indice );
    EnviaComando ;
    ComprovantesNaoFiscais[A].Contador := StrToIntDef( NCRResposta.Params[0], 0) ;
    ComprovantesNaoFiscais[A].Total    := RoundTo( StrToFloatDef( NCRResposta.Params[1], 0) / 100, -2) ;
  end ;
end;

procedure TACBrECFNCR.ProgramaComprovanteNaoFiscal(var Descricao : String;
   Tipo: String; Posicao : String);
 var ProxIndice : Integer ;
begin
  For ProxIndice := 1 to 20 do  { Procurando Lacuna }
  begin
    if AchaCNFIndice(IntToStr(ProxIndice)) = nil then
       break ;
  end ;

  if ProxIndice > 20 then
     raise EACBrECFERRO.create(ACBrStr('N�o h� espa�o para programar novas Formas de '+
                            'Pagamento'));

  NCRComando.Comando := '104' ;
  NCRComando.AddParam( IntToStr(ProxIndice) ) ;
  NCRComando.AddParam( Descricao ) ;
  EnviaComando ;

  CarregaComprovantesNaoFiscais ;
end;

procedure TACBrECFNCR.AbreRelatorioGerencial;
begin
  NCRComando.Comando  := '28' ;
  NCRComando.AddParam( '1' ) ;
  EnviaComando ;
end;

procedure TACBrECFNCR.LinhaRelatorioGerencial(Linha: AnsiString; IndiceBMP: Integer);
Var I  : Integer ;
    SL : TStringList ;
begin
  Linha := AjustaLinhas( Linha, Colunas );  { Formata as Linhas de acordo com "Coluna" }

  SL := TStringList.create ;
  try
     SL.Text := Linha ;
     For I := 0 to SL.Count-1 do
     begin
        NCRComando.Comando  := '204' ;
        NCRComando.AddParam(SL[I]) ;
        EnviaComando ;
     end ;
  finally
     SL.Free ;
  end ;
end;

procedure TACBrECFNCR.AbreCupomVinculado(COO, CodFormaPagto,
   CodComprovanteNaoFiscal :  String; Valor : Double ) ;
Var SeqVinculado : String ;
begin
  SeqVinculado := BuscaSequenciaVinculado( CodFormaPagto ) ;
  if SeqVinculado = '' then
     raise EACBrECFERRO.create(ACBrStr('N�o registrado nenhum pagamento para comprovante vinculado.'));

  NCRComando.Comando := '210' ;
  NCRComando.AddParam( SeqVinculado ) ; // Seq��ncia do pagamento
  NCRComando.AddParam( '1' ) ;            // Numero de Parcelas
  NCRComando.AddParam( '0' ) ;            // Armazena Linhas na NVRAM 0/1
  EnviaComando ;
end;

procedure TACBrECFNCR.LinhaCupomVinculado(Linha: AnsiString);
begin
  LinhaRelatorioGerencial( Linha );
end;

procedure TACBrECFNCR.FechaRelatorio;
begin
  try
    FechaCupom ;
  except
    on E : Exception do
    begin
      if Pos('012/015', E.Message) = 0 then
         raise ;
    end;
  end;  
end;

procedure TACBrECFNCR.PulaLinhas(NumLinhas: Integer);
begin
  if NumLinhas = 0 then
     NumLinhas := LinhasEntreCupons ;

  NCRComando.Comando := '205' ;
  NCRComando.AddParam( IntToStr(NumLinhas) );
  EnviaComando ;
end;

procedure TACBrECFNCR.LeituraMemoriaFiscal(ReducaoInicial,
   ReducaoFinal : Integer; Simplificada : Boolean);
begin
  with NCRComando do
  begin
     if Simplificada then
        Comando := '24'
     else
        Comando := '26' ;
        
     AddParam( IntToStr(ReducaoInicial) ) ;
     AddParam( IntToStr(ReducaoFinal) ) ;
  end ;
  EnviaComando ;
end;

procedure TACBrECFNCR.LeituraMemoriaFiscal(DataInicial,
   DataFinal: TDateTime; Simplificada : Boolean);
begin
  with NCRComando do
  begin
     if Simplificada then
        Comando := '23'
     else
        Comando := '25' ;
        
     AddParam( FormatDateTime('ddmmyy',DataInicial) ) ;
     AddParam( FormatDateTime('ddmmyy',DataFinal) ) ;
  end ;
  EnviaComando ;
end;

procedure TACBrECFNCR.CorrigeEstadoErro(Reducao: Boolean);
begin
  inherited CorrigeEstadoErro(Reducao) ;

  if Estado <> estLivre then
     try
        NCRComando.Comando := '20' ;
        EnviaComando ;
        sleep(500) ;
     except
     end ;
end;

function TACBrECFNCR.GetCNPJ: String;
begin
  if fsCNPJ = '' then
     GetNumLoja ;

  Result := fsCNPJ ;
end;

function TACBrECFNCR.GetIE: String;
begin
  if fsIE = '' then
     GetNumLoja ;

  Result := fsIE ;
end;

function TACBrECFNCR.GetDataMovimento: TDateTime;
Var RetCmd : AnsiString ;
    OldShortDateFormat : String ;
begin
  NCRComando.Comando := '65' ;
  EnviaComando ;
  RetCmd := NCRResposta.Params[1] ;
  OldShortDateFormat := ShortDateFormat ;
  try
     if NCRResposta.Params[1] = '000000' then
        result := 0
     else
     begin
       ShortDateFormat := 'dd/mm/yy' ;
       Result := StrToDate(copy(RetCmd, 1,2) + DateSeparator +
                           copy(RetCmd, 3,2) + DateSeparator +
                           copy(RetCmd, 5,2)) ;
     end;
  finally
     ShortDateFormat := OldShortDateFormat ;
  end ;
end;

function TACBrECFNCR.GetGrandeTotal: Double;
begin
  NCRComando.Comando := '69' ;
  NCRComando.AddParam('10');
  EnviaComando ;
  
  Result := RoundTo( StrToFloatDef(NCRResposta.Params[0],0) / 100, -2);
end;

function TACBrECFNCR.GetNumCRZ: String;
begin
  NCRComando.Comando := '220' ;
  NCRComando.AddParam('03');
  EnviaComando ;
  
  Result := NCRResposta.Params[0] ;
end;

function TACBrECFNCR.GetTotalAcrescimos: Double;
begin
  NCRComando.Comando := '69' ;
  NCRComando.AddParam('08');
  EnviaComando ;

  Result := RoundTo( StrToFloatDef(NCRResposta.Params[0],0) / 100, -2) ;
end;

function TACBrECFNCR.GetTotalCancelamentos: Double;
begin
  NCRComando.Comando := '69' ;
  NCRComando.AddParam('09');
  EnviaComando ;
  
  Result := RoundTo( StrToFloatDef(NCRResposta.Params[0],0) / 100, -2);
end;

function TACBrECFNCR.GetTotalDescontos: Double;
begin
  NCRComando.Comando := '69' ;
  NCRComando.AddParam('07');
  EnviaComando ;

  Result := RoundTo( StrToFloatDef(NCRResposta.Params[0],0) / 100, -2);
end;

function TACBrECFNCR.GetTotalSubstituicaoTributaria: Double;
begin
  NCRComando.Comando := '69' ;
  NCRComando.AddParam('02');
  EnviaComando ;

  Result := RoundTo( StrToFloatDef(NCRResposta.Params[0],0) / 100, -2) ;
end;

function TACBrECFNCR.GetTotalIsencao: Double;
begin
  NCRComando.Comando := '69' ;
  NCRComando.AddParam('01');
  EnviaComando ;

  Result := RoundTo( StrToFloatDef(NCRResposta.Params[0],0) / 100, -2) ;
end;

function TACBrECFNCR.GetTotalNaoTributado: Double;
begin
  NCRComando.Comando := '69' ;
  NCRComando.AddParam('03');
  EnviaComando ;

  Result := RoundTo( StrToFloatDef(NCRResposta.Params[0],0) / 100, -2) ;
end;

function TACBrECFNCR.GetVendaBruta: Double;
Var
  VendaBrutaInicial : Double ;
begin
  NCRComando.Comando := '71' ;
  NCRComando.AddParam('10');
  EnviaComando ;

  VendaBrutaInicial := RoundTo( StrToFloatDef( NCRResposta.Params[0], 0) / 100, -2) ;
  Result := abs(GetGrandeTotal - VendaBrutaInicial) ;
end;

function TACBrECFNCR.GetNumCOOInicial: String;
begin
  NCRComando.Comando := '221' ;
  NCRComando.AddParam('02');
  EnviaComando ;

  Result := NCRResposta.Params[0] ;
end;

procedure TACBrECFNCR.AbreNaoFiscal( CPF_CNPJ, Nome, Endereco: String );
begin
  if Trim(CPF_CNPJ) <> '' then
     Consumidor.AtribuiConsumidor(CPF_CNPJ,'','');
     
  NCRComando.Comando := '21' ;
  NCRComando.AddParam( '07' );
  EnviaComando ;
end;

procedure TACBrECFNCR.CancelaNaoFiscal;
begin
  CancelaCupom ;
end;

procedure TACBrECFNCR.RegistraItemNaoFiscal(CodCNF: String;
  Valor: Double; Obs: AnsiString);
begin
  NCRComando.Comando := '201' ;
  NCRComando.AddParam( CodCNF );
  NCRComando.AddParam( '' );
  NCRComando.AddParam( FloatToStr( Valor ) );
  EnviaComando ;
end;

procedure TACBrECFNCR.SubtotalizaNaoFiscal(DescontoAcrescimo: Double;
   MensagemRodape: AnsiString);
begin
  SubtotalizaCupom( DescontoAcrescimo ) ;
end;

procedure TACBrECFNCR.EfetuaPagamentoNaoFiscal(CodFormaPagto: String;
  Valor: Double; Observacao: AnsiString; ImprimeVinculado: Boolean);
begin
  EfetuaPagamento(CodFormaPagto, Valor, Observacao, ImprimeVinculado) ;
end;

procedure TACBrECFNCR.FechaNaoFiscal(Observacao: AnsiString; IndiceBMP : Integer);
begin
  FechaCupom( Observacao ) ;
end;

procedure TACBrECFNCR.NaoFiscalCompleto(CodCNF: String; Valor: Double;
  CodFormaPagto: String; Obs: AnsiString; IndiceBMP : Integer);
begin
  { Chama rotinas da classe Pai (fpOwner) para atualizar os Memos }
  with TACBrECF(fpOwner) do
  begin
     AbreNaoFiscal ;
     try
        RegistraItemNaoFiscal(CodCNF, Valor);
        try
           SubtotalizaNaoFiscal(0);
           EfetuaPagamentoNaoFiscal(CodFormaPagto, Valor );
        except
        end ;
        FechaNaoFiscal( Obs );
     except
        try
           CancelaNaoFiscal
        except
        end;

        raise ;
     end ;
  end ;
end;

// Andre Bohn - Segundo suporte da NCR � mais seguro imprimir o cheque com
// o cupom fechado, eu fiz um teste com o cupom aberto e tive que trocar a MFD.
// Ele disse que futuramente v�o preparar a impress�o do cheque para ter
// o mesmo funcionamento das outras ECFs.
procedure TACBrECFNCR.ImprimeCheque(Banco: String; Valor: Double;
  Favorecido, Cidade: String; Data: TDateTime; Observacao: String);
begin
  if fsImprimeCheque then
  begin
    with NCRComando do
    begin
       Comando := '232' ;
       AddParam( FloatToStr( Valor ) ) ;
       AddParam( LeftStr(Favorecido,40) ) ;
       AddParam( LeftStr(Cidade,30) ) ;
       AddParam( Observacao ) ;
    end ;
    EnviaComando ;         // Envia comando para imprimir o Cheque
  end ;
end;

// Andre Bohn - Comando para fazer a leitura do CMC7
Function TACBrECFNCR.LeituraCMC7 : AnsiString ;
begin
  Result :=  '';
  if fsLeituraCMC7 then
  begin
    with NCRComando do
    begin
       Comando := '216' ;
       AddParam( '0' ) ;
       AddParam( '' ) ;
    end ;
    EnviaComando ;       // Envia o comando para Ler o CMC7

    Result := NCRResposta.Params[0] ;
  end;
end;

function TACBrECFNCR.GetChequePronto: Boolean;
begin
  NCRComando.Comando := '66' ;
  EnviaComando ;         // Obtem o Estado da ECF

  Result := (NCRResposta.Params[2] = '1') ;
end;




end.

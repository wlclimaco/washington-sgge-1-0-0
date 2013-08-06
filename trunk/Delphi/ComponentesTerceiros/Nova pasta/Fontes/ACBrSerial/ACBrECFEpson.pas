{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2004 Daniel Simoes de Almeida               }
{                                                                              }
{ Colaboradores nesse arquivo:   Eduardo Durieux Lopes                         }
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

{$I ACBr.inc}

unit ACBrECFEpson ;

interface
uses ACBrECFClass, ACBrDevice, ACBrUtil,
     Classes ;

const
  {$IFDEF LINUX}
   cLIB_Epson = 'libInterfaceEpson.so';
  {$ELSE}
   cLIB_Epson = 'InterfaceEpson.dll';
  {$ENDIF}

type

TACBrECFEpson = class ;

{ TACBrECFEpsonComando }

TACBrECFEpsonComando = class
  private
    fsComando : AnsiString ;
    fsExtensao: AnsiString ;
    fsParams  : TStringList ;
    fsSeq     : Byte ;
    fsTimeOut : Integer;

    function GetFrameEnvio: AnsiString;
    function GetFrameEnvioDLL : AnsiString ;
    procedure SetComando(const Value: AnsiString);
    procedure SetExtensao(const Value: AnsiString);
 public
    constructor create ;
    destructor destroy ; override ;

    property Comando      : AnsiString  write SetComando  ;
    property Extensao     : AnsiString  write SetExtensao ;
    property TimeOut      : Integer     read fsTimeOut write fsTimeOut ;
    property FrameEnvio   : AnsiString  read GetFrameEnvio ;
    property FrameEnvioDLL: AnsiString  read GetFrameEnvioDLL ;
    property Params       : TStringList read fsParams ;
    property Seq          : Byte read fsSeq  ;

    Procedure AddParamString(AString: AnsiString) ;
    Procedure AddParamInteger(AInteger: Integer) ;
    Procedure AddParamDouble(ADouble: Double; CasasDecimais: Integer = 2) ;
    Procedure AddParamBool(ABool: Boolean) ;
    Procedure AddParamDateTime(ADateTime: TDateTime;Tipo : Char = 'D'  ) ;
 end ;

{ TACBrECFEpsonResposta }

TACBrECFEpsonResposta = class
  private
    fsOwner : TACBrECFEpson ;
    fsResposta : AnsiString ;

    fsSeq: Byte;
    fsStatusPrinter: Integer;
    fsStatusFiscal : Integer;
    fsRetorno      : AnsiString ;
    fsParams       : TStringList ;
    fsChkSum       : AnsiString ;

    procedure SetResposta(const AValue: AnsiString);
    function GetDescRetorno: String;
    procedure SetRespostaDLL(AValue : AnsiString) ;
 public
    constructor create( AOwner : TACBrECFEpson ) ;
    destructor destroy ; override ;

    property Resposta     : AnsiString  read fsResposta write SetResposta ;
    property Seq          : Byte        read fsSeq;
    property StatusPrinter: Integer     read fsStatusPrinter ;
    property StatusFiscal : Integer     read fsStatusFiscal ;
    property Retorno      : AnsiString  read fsRetorno ;
    property DescRetorno  : String      read GetDescRetorno ;
    property Params       : TStringList read fsParams ;
    property ChkSum       : AnsiString  read fsChkSum ;
 end ;

{ Classe filha de TACBrECFClass com implementa�ao para Epson }

{ TACBrECFEpson }

TACBrECFEpson = class( TACBrECFClass )
 private
    fsUsarDLL: Boolean;
    fsTentaDetectarVelocidade: Boolean;
    fsNumVersao : String ;
    fsIsFBIII   : Boolean;
    fsALNegrito : Boolean;
    fsALExpandido : Boolean;
    fsALSublinhado : Boolean;
    fsNumECF    : String ;
    fsNumLoja   : String ;
    fsCNPJ      : String ;
    fsIE        : String ;
    fsIM        : String ;
    fsCliche    : AnsiString ;
    fsUsuarioAtual : String ;
    fsDataHoraSB   : TDateTime ;
    fsSubModeloECF : String ;
    fsRet0906 : AnsiString ;
    fsRet0907 : AnsiString ;
    fsBytesIn : Integer ;
    fsByteACK : Byte ;
    fsEpsonComando: TACBrECFEpsonComando;
    fsEpsonResposta: TACBrECFEpsonResposta;
    fsImprimeCheque: Boolean;
    fsLeituraCMC7  : Boolean;
    fsVerificaChecksum: Boolean;
    fsPAF1, fsPAF2 : String ;
    fsEmPagamento : Boolean ;

    xEPSON_Serial_Abrir_Porta : function (dwVelocidade:Integer;
       wPorta:Integer):Integer; {$IFDEF LINUX} cdecl {$ELSE} stdcall {$ENDIF} ;
    xEPSON_Serial_Fechar_Porta : function : Integer;
       {$IFDEF LINUX} cdecl {$ELSE} stdcall {$ENDIF} ;
    xEPSON_Serial_Obter_Estado_Com : function : Integer;
       {$IFDEF LINUX} cdecl {$ELSE} stdcall {$ENDIF} ;
    xEPSON_Obter_Dados_MF_MFD : function (pszInicio:AnsiString;
       pszFinal:AnsiString; dwTipoEntrada:Integer; dwEspelhos:Integer;
       dwAtoCotepe:Integer; dwSintegra:Integer; pszArquivoSaida:AnsiString) :
       Integer; {$IFDEF LINUX} cdecl {$ELSE} stdcall {$ENDIF} ;
    xEPSON_Send_From_FileEXX : function (pszLineIn:AnsiString;
       pszStatus:PAnsiChar; pszLineOut:PAnsiChar ) : Integer;
       {$IFDEF LINUX} cdecl {$ELSE} stdcall {$ENDIF} ;

    procedure Ativar_Epson ;

    procedure LoadDLLFunctions;
    procedure AbrePortaSerialDLL;
    procedure FechaPortaSerialDLL(const OldAtivo : Boolean) ;

    procedure ZeraCache( ZeraRespostaComando: Boolean = True ) ;

    Function DocumentosToNum(Documentos : TACBrECFTipoDocumentoSet) : Integer ;

    Procedure PreparaCmd( cmd : AnsiString ) ;

    function  GetRet0402( Indice: Integer): AnsiString;
    function  GetRet0906: AnsiString;
    property  Ret0906 : AnsiString read GetRet0906 ;
    function  GetRet0907: AnsiString;
    property  Ret0907 : AnsiString read GetRet0907 ;
    procedure EnviaPAF;
 protected
    function GetDataHora: TDateTime; override ;
    function GetNumCupom: String; override ;
    function GetNumCCF: String; override ;
    function GetNumECF: String; override ;
    function GetNumCRO: String; override ;
    function GetNumCRZ: String; override ;
    function GetNumGNF: String; override ;
    function GetNumGNFC: String; override ;
    function GetNumCFD: String; override ;
    function GetNumGRG: String; override ;
    function GetNumCDC: String; override ;
    function GetNumCFC: String; override ;
    function GetNumNCN: String; override ;
    function GetNumLoja: String; override ;
    function GetNumSerie: String; override ;
    function GetNumSerieMFD: String; override ;
    function GetNumVersao: String; override ;
    function GetSubTotal: Double; override ;
    function GetTotalPago: Double; override ;
    function GetNumReducoesZRestantes: String; override ;

    function GetEstado: TACBrECFEstado; override ;
    function GetGavetaAberta: Boolean; override ;
    function GetPoucoPapel : Boolean; override ;
    function GetHorarioVerao: Boolean; override ;
    function GetParamDescontoISSQN: Boolean; override ;

    function GetTipoUltimoDocumento : TACBrECFTipoDocumento ; override ;

    function GetCNPJ: String; override ;
    function GetIE: String; override ;
    function GetIM: String; override ;
    function GetCliche: AnsiString; override ;
    function GetUsuarioAtual: String; override ;
    function GetDataHoraSB: TDateTime; override ;
    function GetSubModeloECF: String ; override ;
    
    function GetPAF: String; override ;
    function GetDataMovimento: TDateTime; override ;
    function GetGrandeTotal: Double; override ;
    function GetVendaBruta: Double; override ;
    function GetTotalAcrescimos: Double; override ;
    function GetTotalCancelamentos: Double; override ;
    function GetTotalDescontos: Double; override ;
    function GetTotalTroco: Double; override ;
    function GetTotalSubstituicaoTributaria: Double; override ;
    function GetTotalNaoTributado: Double; override ;
    function GetTotalIsencao: Double; override ;

    function GetTotalAcrescimosISSQN: Double; override;
    function GetTotalCancelamentosISSQN: Double; override;
    function GetTotalDescontosISSQN: Double; override;
    function GetTotalSubstituicaoTributariaISSQN: Double; override;
    function GetTotalIsencaoISSQN: Double; override;
    function GetTotalNaoTributadoISSQN: Double; override;

    function GetTotalAcrescimosOPNF: Double; override;
    function GetTotalCancelamentosOPNF: Double; override;
    function GetTotalDescontosOPNF: Double; override;

    function GetNumCOOInicial: String; override ;
    function GetNumUltimoItem: Integer; override ;

    Function VerificaFimLeitura(var Retorno: AnsiString;
       var TempoLimite: TDateTime) : Boolean ; override ;

    function GetChequePronto: Boolean; override ;
 public
    Constructor create( AOwner : TComponent  )  ;
    Destructor Destroy  ; override ;

    procedure Ativar ; override ;
    procedure Desativar ; override ;

    property VerificaChecksum : Boolean read fsVerificaChecksum
       write fsVerificaChecksum ;
    property EpsonComando : TACBrECFEpsonComando  read fsEpsonComando ;
    property EpsonResposta: TACBrECFEpsonResposta read fsEpsonResposta ;

    Function EnviaComando_ECF( cmd : AnsiString = '') : AnsiString ; override ;

    Procedure AbreCupom ; override ;
    Procedure VendeItem( Codigo, Descricao : String; AliquotaECF : String;
       Qtd : Double ; ValorUnitario : Double; ValorDescontoAcrescimo : Double = 0;
       Unidade : String = ''; TipoDescontoAcrescimo : String = '%';
       DescontoAcrescimo : String = 'D'; CodDepartamento: Integer = -1 ) ; override ;
    Procedure DescontoAcrescimoItemAnterior( ValorDescontoAcrescimo : Double = 0;
       DescontoAcrescimo : String = 'D'; TipoDescontoAcrescimo : String = '%';
       NumItem : Integer = 0 ) ;  override ;
    Procedure SubtotalizaCupom( DescontoAcrescimo : Double = 0;
       MensagemRodape : AnsiString  = '' ) ; override ;
    Procedure EfetuaPagamento( CodFormaPagto : String; Valor : Double;
       Observacao : AnsiString = ''; ImprimeVinculado : Boolean = false) ;
       override ;
    Procedure FechaCupom( Observacao : AnsiString = ''; IndiceBMP : Integer = 0) ; override ;
    Procedure CancelaCupom ; override ;
    Procedure CancelaItemVendido( NumItem : Integer ) ; override ;

    Procedure LeituraX ; override ;
    Procedure LeituraXSerial( Linhas : TStringList) ; override ;
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
       Simplificada : Boolean = False ) ; override ;
    Procedure LeituraMemoriaFiscalSerial( DataInicial, DataFinal : TDateTime;
       Linhas : TStringList; Simplificada : Boolean = False ) ; override ;
    Procedure LeituraMemoriaFiscalSerial( ReducaoInicial, ReducaoFinal : Integer;
       Linhas : TStringList; Simplificada : Boolean = False ) ; override ;

    Procedure EspelhoMFD_DLL( DataInicial, DataFinal : TDateTime;
       NomeArquivo : AnsiString; Documentos : TACBrECFTipoDocumentoSet = [docTodos]  ) ; override ;
    Procedure EspelhoMFD_DLL( COOInicial, COOFinal : Integer;
       NomeArquivo : AnsiString; Documentos : TACBrECFTipoDocumentoSet = [docTodos]  ) ; override ;
    Procedure ArquivoMFD_DLL( DataInicial, DataFinal : TDateTime;
       NomeArquivo : AnsiString; Documentos : TACBrECFTipoDocumentoSet = [docTodos];
       Finalidade: TACBrECFFinalizaArqMFD = finMFD  ) ; override ;
    Procedure ArquivoMFD_DLL( ContInicial, ContFinal : Integer;
       NomeArquivo : AnsiString; Documentos : TACBrECFTipoDocumentoSet = [docTodos];
       Finalidade: TACBrECFFinalizaArqMFD = finMFD;
       TipoContador: TACBrECFTipoContador = tpcCOO ) ; override ;

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
    function AchaICMSAliquota( var AliquotaICMS : String ) :
       TACBrECFAliquota ;  override;

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
    procedure CancelaImpressaoCheque ; override ;
    Function LeituraCMC7 : AnsiString ; override ;

    Procedure IdentificaOperador ( Nome: String); override;
    Procedure IdentificaPAF( NomeVersao, MD5 : String) ; override ;
    Procedure CortaPapel( const CorteParcial : Boolean = false) ; override ;
    procedure Suprimento( const Valor: Double; Obs : AnsiString;
       DescricaoCNF: String; DescricaoFPG: String; IndiceBMP: Integer) ; override ;

    Function EstornaCCD( const Todos: Boolean = True) : Integer; override ;

    procedure CarregaRelatoriosGerenciais; override;
    procedure LerTotaisRelatoriosGerenciais ; override ;
    procedure ProgramaRelatorioGerencial(var Descricao: string;
       Posicao: string=''); override;
    function AchaCNFDescricao( Descricao: String;
       BuscaExata : Boolean; IgnorarCase : Boolean = True  ):
       TACBrECFComprovanteNaoFiscal; override;

    function GetDadosUltimaReducaoZ: AnsiString; override ;

    function TraduzirTag(const ATag: AnsiString): AnsiString; override;
 end ;

function EpsonCheckSum(Dados: AnsiString): AnsiString;
function RemoveEsc(const Campo : AnsiString) : AnsiString ;
Function InsertEsc(const Campo: AnsiString): AnsiString ;

implementation
Uses ACBrECF, ACBrConsts,
     {$IFDEF COMPILER6_UP}
       DateUtils, StrUtils
     {$ELSE}
       ACBrD5, Windows
     {$ENDIF},
     SysUtils, Math ;

function EpsonCheckSum(Dados: AnsiString): AnsiString;
begin
  Result := IntToHex( SomaAscII(Dados), 4);
end;


function InsertEsc(const Campo: AnsiString): AnsiString;
Var
  I : Integer ;
begin
  Result := '' ;

  For I := 1 to Length(Campo) do
  begin
    if Campo[I] in [#2, #3, #26 .. #31] then
       Result := Result + ESC ;

    Result := Result + Campo[I];
  end ;
end;

function RemoveEsc(const Campo : AnsiString) : AnsiString ;
Var
  I, L : Integer ;
begin
  Result := '' ;
  L := Length(Campo);
  I := 1 ;

  while I <= L do
  begin
    if (Campo[I] = ESC) and (I < L) and  (Campo[I+1] in [#2, #3, #26 .. #31]) then
       Inc(I);

    Result := Result + Campo[I];
    Inc(I);
  end ;
end ;

{ -------------------------  TACBrECFEpsonComando -------------------------- }
constructor TACBrECFEpsonComando.create;
begin
  inherited create ;

  fsParams := TStringList.create ;
  fsSeq    := 129 ;
end;

destructor TACBrECFEpsonComando.destroy;
begin
  fsParams.Free ;

  inherited destroy ;
end;

procedure TACBrECFEpsonComando.AddParamString(AString : AnsiString) ;
begin
  fsParams.Add( InsertEsc( AString ) ) ;
end ;

procedure TACBrECFEpsonComando.AddParamInteger(AInteger : Integer) ;
begin
  AddParamString( IntToStr(AInteger) );
end ;

procedure TACBrECFEpsonComando.AddParamDouble(ADouble : Double; CasasDecimais: Integer) ;
begin
  AddParamInteger( Round( ADouble * power(10, CasasDecimais) ) ) ;
end ;

procedure TACBrECFEpsonComando.AddParamBool(ABool : Boolean) ;
begin
  AddParamString( IfThen(ABool,'S','N') ) ;
end ;

procedure TACBrECFEpsonComando.AddParamDateTime(ADateTime : TDateTime ;
  Tipo : Char) ;
var
  Texto : String ;
begin
  if Tipo in ['T','H'] then
     Texto := FormatDateTime('hhnnss',ADateTime)
  else
     Texto := FormatDateTime('ddmmyyyy',ADateTime) ;

  AddParamString( Texto ) ;
end ;

procedure TACBrECFEpsonComando.SetComando(const Value: AnsiString);
Var
  Tamanho : Integer ;
begin
  if fsSeq >= 255 then
     fsSeq := 129
  else
     Inc( fsSeq ) ;

  Tamanho := Length(Trim(Value)) ;
  if (Tamanho <> 2) and (Tamanho <> 4) then
     raise EACBrECFERRO.Create(ACBrStr('Comando Epson deve ter 4 Caracteres em Hexadecimal')) ;

  { Zerando instrucoes adicionais do comando }
  fsParams.Clear ;
  fsExtensao:= #0 + #0 ;
  fsTimeOut := 0 ;

  if Tamanho = 2 then
     fsComando := Value
  else
     fsComando := InsertEsc( HexToAscii(Value) ) ;
end;

procedure TACBrECFEpsonComando.SetExtensao(const Value: AnsiString);
 Var Tamanho : Integer ;
begin
  Tamanho := Length(Trim(Value)) ;
  if (Tamanho <> 2) and (Tamanho <> 4) then
     raise EACBrECFERRO.Create(ACBrStr('Extensao de Comando Epson deve ter 4 Caracteres em Hexadecimal')) ;

  if Tamanho = 2 then
     fsExtensao := Value
  else
     fsExtensao := InsertEsc( HexToAscii(Value) ) ;
end;

function TACBrECFEpsonComando.GetFrameEnvio: AnsiString;
Var
  I : Integer ;
  ParamsStr : AnsiString ;
begin
  { Montando pacote com Parametros }
  ParamsStr := '' ;
  For I := 0 to fsParams.Count-1 do
    ParamsStr := ParamsStr + FS + fsParams[I] ;

  { Montando Pacote de Envio }
  Result := STX + AnsiChar(chr(fsSeq)) + fsComando + FS + fsExtensao + ParamsStr + ETX ;

  { Calculando o Checksum }
  Result := Result + EpsonCheckSum( Result ) ;
end;

function TACBrECFEpsonComando.GetFrameEnvioDLL : AnsiString ;
Var
  I : Integer ;
begin
  Result := AsciiToHex( RemoveEsc(fsComando) )+'|'+AsciiToHex( RemoveEsc(fsExtensao) );

  { Montando pacote com Parametros }
  For I := 0 to fsParams.Count-1 do
    Result := Result + '|'+ StringReplace( RemoveEsc(fsParams[I]), '|','/',[rfReplaceAll] ) ;
end;


{ ------------------------- TACBrECFEpsonResposta -------------------------- }

constructor TACBrECFEpsonResposta.create( AOwner : TACBrECFEpson );
begin
  fsOwner         := AOwner ;
  fsParams        := TStringList.create ;
  fsSeq           := 0 ;
  fsStatusPrinter := 0 ;
  fsStatusFiscal  := 0 ;
  fsRetorno       := '' ;
  fsChkSum        := '' ;
  fsResposta      := '' ;
end;

destructor TACBrECFEpsonResposta.destroy;
begin
  fsParams.Free ;

  inherited destroy ;
end;

procedure TACBrECFEpsonResposta.SetResposta(const AValue: AnsiString);
Var
  Buf : AnsiString ;
  P   : Integer ;
begin
  fsParams.Clear ;
  fsSeq           := 0 ;
  fsStatusPrinter := 0 ;
  fsStatusFiscal  := 0 ;
  fsRetorno       := '' ;
  fsChkSum        := '' ;
  fsResposta      := '' ;

  if AValue = '' then exit ;

  fsResposta := AValue ;

  if pos('|:|',AValue) = 21 then
  begin
     SetRespostaDLL( AValue );
     exit;
  end ;

  if LeftStr(fsResposta,1) <> STX then
     raise EACBrECFERRO.Create(ACBrStr('Resposta inv�lida. N�o inicia com STX (02)')) ;

  if copy(fsResposta,Length(fsResposta)-4,1) <> ETX then
     raise EACBrECFERRO.Create(ACBrStr('Resposta inv�lida. N�o finaliza com ETX (03)')) ;

  if fsOwner.VerificaChecksum then
  begin
     fsChkSum := RightStr(fsResposta,4) ;
     if EpsonCheckSum( copy(fsResposta,1,Length(fsResposta)-4) ) <> fsChkSum then
        raise EACBrECFERRO.create(ACBrStr('Resposta inv�lida. CheckSum da Resposta n�o est� correto.')) ;
  end ;

  try
     fsSeq := ord(fsResposta[2]) ;
  except
     raise EACBrECFERRO.Create(ACBrStr('Resposta inv�lida. Num.Sequencia inv�lido')) ;
  end ;

  { Pega apenas o Frame de Dados }
  Buf := copy(fsResposta,3,Length(fsResposta)-7) ;  //  Remove STX, SEQ, ETX e CHKSUM

  { Quebrando Parametros Separados por FS e inserindo-os em fsParams }
  while Buf <> '' do
  begin
     P := pos(FS,Buf) ;
     if (P > 1) and (Buf[P-1] = #27) then   // Achou #27+#28 ?
        P := PosEx(FS,Buf,P+1) ;           // Se SIM, pegue o proximo #28

     if P = 0 then
        P := Length(Buf)+1 ;

     fsParams.Add( RemoveEsc( LeftStr(Buf,P-1) ) ) ;
     Buf := copy(Buf,P+1,Length(Buf)) ;  // Pega pr�ximo bloco
  end ;

  if fsParams.Count < 4 then
     raise EACBrECFERRO.Create(ACBrStr('Resposta Incompleta'));

  { Removendo Status Printer de fsParams }
  try
     fsStatusPrinter := StrToInt( '$'+AsciiToHex( fsParams[0]  ) ) ;
  except
     on E : Exception do
     begin
        raise EACBrECFERRO.Create(ACBrStr('Resposta Inv�lida. Erro ao calcular Status da Impressora')+sLineBreak+
                               E.Message) ;
     end ;
  end ;
  fsParams.Delete(0);

  { Removendo Status Fiscal de fsParams }
  try
     fsStatusFiscal := StrToInt( '$'+AsciiToHex( fsParams[0]  ) ) ;
  except
     on E : Exception do
     begin
        raise EACBrECFERRO.Create(ACBrStr('Resposta Inv�lida. Erro ao calcular Status Fiscal')+sLineBreak+
                               E.Message) ;
     end ;
  end ;
  fsParams.Delete(0);

  fsParams.Delete(0);   // Remove da pilha Reservado 1, pois n�o � parametro

  { Removendo Cod.Retorno de fsParams }
  try
     fsRetorno := AsciiToHex( fsParams[0]  ) ;
  except
     on E : Exception do
     begin
        raise EACBrECFERRO.Create(ACBrStr('Resposta Inv�lida. Erro ao calcular Cod.Retorno')+sLineBreak+
                               E.Message) ;
     end ;
  end ;
  fsParams.Delete(0);   

  if fsParams.Count > 0 then   // Possui Reservado 2 ?
     if fsParams[0] = '' then
        fsParams.Delete(0);   // Remove da pilha Reservado 2, pois n�o � parametro
end;

procedure TACBrECFEpsonResposta.SetRespostaDLL(AValue : AnsiString) ;
Var
  Status, LineOut : AnsiString ;
  P : Integer ;
begin
  Status  := copy( AValue, 1, 20);
  LineOut := copy( AValue,24, Length(AValue));

  fsStatusPrinter := StrToInt( '$'+copy(Status, 9,3) );
  fsStatusFiscal  := StrToInt( '$'+copy(Status,13,4) );
  fsRetorno       := UpperCase( copy(Status, 17,4) );

  { Quebrando Parametros Separados por | e inserindo-os em fsParams }
  while LineOut <> '' do
  begin
     P := pos('|',LineOut+'|') ;

     fsParams.Add( LeftStr(LineOut,P-1) ) ;
     LineOut := copy(LineOut,P+1,Length(LineOut)) ;  // Pega pr�ximo bloco
  end ;
end ;


function TACBrECFEpsonResposta.GetDescRetorno: String;
Var
  sRetorno : AnsiString ;
begin
  Result   := '';
  sRetorno := HexToAscii(fsRetorno) ;

  Case sRetorno[1] of

    #0 : begin
            Case sRetorno[2] of
              #0 : Result := '';
              #1 : Result := 'Erro interno.';
              #2 : Result := 'Erro de inicia��o do equipamento.';
              #3 : Result := 'Erro de processo interno.';
            end;
          end;

    #1 : begin
           Case sRetorno[2] of
             #1 : Result := 'Comando inv�lido para estado atual.';
             #2 : Result := 'Comando inv�lido para documento atual.';
             #6 : Result := 'Comando aceito apenas fora de interven��o.';
             #7 : Result := 'Comando aceito apenas dentro de interven��o.';
             #8 : Result := 'Comando inv�lido durante processo de scan.';
             #9 : Result := 'Exce�o de interven��es.';
           end;
         end;

    #2 : begin
           Case sRetorno[2] of
             #1  : Result := 'Comando com Frame inv�lido.';
             #2  : Result := 'Comando inv�lido.';
             #3  : Result := 'Campos em excesso.';
             #4  : Result := 'Campos em falta.';
             #5  : Result := 'Campo n�o opcional.';
             #6  : Result := 'Campo alfanum�rico inv�lido.';
             #7  : Result := 'Campo alfab�tico inv�lido.';
             #8  : Result := 'Campo num�rico inv�lido.';
             #9  : Result := 'Campo bin�rio inv�lido.';
             #10 : Result := 'Campo imprim�vel inv�lido.';
             #11 : Result := 'Campo hexadecimal inv�lido.';
             #12 : Result := 'Campo data inv�lido.';
             #13 : Result := 'Campo hora inv�lido.';
             #14 : Result := 'Campos com atributos de impress�o inv�lido.';
             #15 : Result := 'Campo booleano inv�lido.';
             #16 : Result := 'Campo com tamanho inv�lido.';
             #17 : Result := 'Extens�o de comando inv�lida.';
             #18 : Result := 'C�digo de barras n�o permitido.';
             #19 : Result := 'Atributos de impress�o n�o permitidos.';
             #20 : Result := 'Atributos de impress�o inv�lidos.';
             #21 : Result := 'C�digo de barras incorretamente definido.';
             #22 : Result := 'Comando inv�lido para a porta selecionada.';
           end;
         end;

    #3 : begin
           Case sRetorno[2] of
             #1  : Result := 'Erro de hardware.';
             #2  : Result := 'Impressora n�o est� pronta.';
             #3  : Result := 'Erro de Impress�o.';
             #4  : Result := 'Falta de papel.';
             #5  : Result := 'Pouco papel dispon�vel.';
             #6  : Result := 'Erro em carga ou expuls�o do papel.';
             #7  : Result := 'Caracter�stica n�o suportada pela impressora.';
             #8  : Result := 'Erro de display.';
             #9  : Result := 'Sequ�ncia de scan inv�lida.';
             #10 : Result := 'N�mero de �rea de recorte inv�lido.';
             #11 : Result := 'Scanner n�o preparado.';
             #12 : Result := 'Qualidade de logotipo n�o suportado pela impressora.';
             #14 : Result := 'Erro de leitura de microc�digo.';
           end;
         end;

    #4 : begin
           Case sRetorno[2] of
             #1  : Result := 'N�mero de s�rie inv�lido.';
             #2  : Result := 'Requer dados de fiscaliza��o j� configurados.';
           end;
         end;

    #5 : begin
           Case sRetorno[2] of
             #1  : Result := 'Data / Hora n�o configurada.';
             #2  : Result := 'Data inv�lida.';
             #3  : Result := 'Data em intervalo inv�lido.';
             #4  : Result := 'Nome operador inv�lido.';
             #5  : Result := 'N�mero de caixa inv�lido.';
             #8  : Result := 'Dados de Cabe�alho ou rodap� inv�lidos.';
             #9  : Result := 'Excesso de fiscaliza��o.';
             #12 : Result := 'N�mero m�ximo de meios de pagamento j� definidos.';
             #13 : Result := 'Meio de pagamento j� definido.';
             #14 : Result := 'Meio de pagamento inv�lido.';
             #15 : Result := 'Descri��o do meio de pagamento inv�lido.';
             #16 : Result := 'Valor m�ximo de desconto inv�lido.';
             #19 : Result := 'Logotipo do usu�rio inv�lido.';
             #20 : Result := 'Seq��ncia de logotipo inv�lido.';
             #21 : Result := 'Configura��o de display inv�lida.';
             #22 : Result := 'Dados do MICR inv�lidos.';
             #23 : Result := 'Campo de endere�o inv�lido.';
             #24 : Result := 'Nome da loja n�o definido.';
             #25 : Result := 'Dados fiscais n�o definidos.';
             #26 : Result := 'N�mero seq�encial do ECF inv�lido.';
             #27 : Result := 'Simbologia do GT inv�lida, devem ser todos diferentes.';
             #28 : Result := 'N�mero de CNPJ inv�lido.';
             #29 : Result := 'Senha de fiscaliza��o inv�lida.';
             #30 : Result := '�ltimo documento deve ser uma redu��o Z.';
             #31 : Result := 'S�mbolo da moeda igual ao atualmente cadastrado.';
             #32 : Result := 'Identifica��o da al�quota n�o cadastrada.';
             #33 : Result := 'Al�quota n�o cadastrada.';
           end;
         end;

    #6 : begin
           Case sRetorno[2] of
             #1  : Result := 'Mem�ria de Fita-detalhe esgotada.';
             #5  : Result := 'N�mero de s�rie invalido para a Mem�ria de Fita-detalhe.';
             #6  : Result := 'Mem�ria de Fita-detalhe n�o iniciada.';
             #7  : Result := 'Mem�ria de Fita-detalhe n�o pode estar iniciada.';
             #8  : Result := 'N�mero de s�rie da Mem�ria de Fita-detalhe n�o confere.';
             #9  : Result := 'Erro Interno na Mem�ria de Fita-detalhe.';
           end;
         end;

    #7 : begin
           Case sRetorno[2] of
             #1  : Result := 'Valor inv�lido para o n�mero do registro.';
             #2  : Result := 'Valor inv�lido para o n�mero do item.';
             #3  : Result := 'Intervalo inv�lido para a leitura da MFD.';
             #4  : Result := 'N�mero de usu�rio inv�lido para MFD.';
           end;
         end;

    #8 : begin
           Case sRetorno[2] of
             #1   : Result := 'Comando inv�lido com jornada fiscal fechada.';
             #2   : Result := 'Comando inv�lido com jornada fiscal aberta.';
             #3   : Result := 'Mem�ria Fiscal esgotada.';
             #4   : Result := 'Jornada fiscal deve ser fechada.';
             #5   : Result := 'N�o h� meios de pagamento definidos.';
             #6   : Result := 'Excesso de meios de pagamento utilizados na jornada fiscal.';
             #7   : Result := 'Jornada fiscal sem movimento de vendas.';
             #8   : Result := 'Intervalo de jornada fiscal inv�lido.';
             #9   : Result := 'Existem mais dados para serem lidos.';
             #10  : Result := 'N�o existem mais dados para serem lidos.';
             #11  : Result := 'N�o pode abrir jornada fiscal.';
             #12  : Result := 'N�o pode fechar jornada fiscal.';
             #13  : Result := 'Limite m�ximo do per�odo fiscal atingido.';
             #14  : Result := 'Limite m�ximo do per�odo fiscal n�o atingido.';
             #15  : Result := 'Abertura da jornada fiscal n�o permitida.';
           end;
         end;

    #9 : begin
           Case sRetorno[2] of
             #1   : Result := 'Valor muito grande.';
             #2   : Result := 'Valor muito pequeno.';
             #3   : Result := 'Itens em excesso.';
             #4   : Result := 'Al�quotas em excesso.';
             #5   : Result := 'Desconto ou acr�scimos em excesso.';
             #6   : Result := 'Meios de pagamento em excesso.';
             #7   : Result := 'Item n�o encontrado.';
             #8   : Result := 'Meio de pagamento n�o encontrado.';
             #9   : Result := 'Total nulo.';
             #12  : Result := 'Tipo de pagamento n�o definido.';
             #15  : Result := 'Al�quota n�o encontrada.';
             #16  : Result := 'Al�quota inv�lida.';
             #17  : Result := 'Excesso de meios de pagamento com CDC.';
             #18  : Result := 'Meio de pagamento com CDC j� emitido.';
             #19  : Result := 'Meio de pagamento com CDC ainda n�o emitido.';
             #20  : Result := 'Leitura da Mem�ria Fiscal � intervalo CRZ inv�lido.';
             #21  : Result := 'Leitura da Mem�ria Fiscal � intervalo de data inv�lido.';
           end;
         end;

     #10: begin
            Case sRetorno[2] of
              #01 : Result := 'Opera��o n�o permitida ap�s desconto / acr�scimo.';
              #02 : Result := 'Opera��o n�o permitida ap�s registro de pagamentos.s';
              #03 : Result := 'Tipo de item inv�lido.';
              #04 : Result := 'Linha de descri��o em branco.';
              #05 : Result := 'Quantidade muito pequena.';
              #06 : Result := 'Quantidade muito grande.';
              #07 : Result := 'Total do item com valor muito alto.';
              #08 : Result := 'Opera��o n�o permitida antes do registro de pagamentos.';
              #09 : Result := 'Registro de pagamento incompleto.';
              #10 : Result := 'Registro de pagamento finalizado.';
              #11 : Result := 'Valor pago inv�lido.';
              #12 : Result := 'Valor de desconto ou acr�scimo n�o permitido.';
              #14 : Result := 'Valor n�o pode ser zero.';
              #15 : Result := 'Opera��o n�o permitida antes do registro de itens.';
              #17 : Result := 'Cancelamento de desconto e acr�scimo somente para item atual.';
              #18 : Result := 'N�o foi poss�vel cancelar �ltimo Cupom Fiscal.';
              #19 : Result := '�ltimo Cupom Fiscal n�o encontrado.';
              #20 : Result := '�ltimo Comprovante N�o-Fiscal n�o encontrado.';
              #21 : Result := 'Cancelamento de CDC necess�ria.';
              #22 : Result := 'N�mero de item em Cupom Fiscal inv�lido.';
              #23 : Result := 'Opera��o somente permitida ap�s subtotaliza��o.';
              #24 : Result := 'Opera��o somente permitida durante a venda de itens.';
              #25 : Result := 'Opera��o n�o permitida em item com desconto ou acr�scimo.';
              #26 : Result := 'D�gitos de quantidade inv�lidos.';
              #27 : Result := 'D�gitos de valor unit�rio inv�lido.';
              #28 : Result := 'N�o h� desconto ou acr�scimo a cancelar.';
              #29 : Result := 'N�o h� item para cancelar.';
              #30 : Result := 'Desconto ou acr�scimo somente no item atual.';
              #31 : Result := 'Desconto ou acr�scimo j� efetuado.';
              #32 : Result := 'Desconto ou acr�scimo nulo n�o permitido.';
              #33 : Result := 'Valor unit�rio inv�lido.';
              #34 : Result := 'Quantidade inv�lida.';
              #35 : Result := 'C�digo de item inv�lido.';
              #36 : Result := 'Descri��o inv�lida.';
              #37 : Result := 'Opera��o de desconto ou acr�scimo n�o permitida.';
              #38 : Result := 'Mensagem promocional j� impressa.';
              #39 : Result := 'Mensagem promocional n�o pode ser impressa.';
              #40 : Result := 'Dados do consumidor j� impresso.';
              #41 : Result := 'Dados do consumidor somente no fim do documento.';
              #42 : Result := 'Dados do consumidor somente no inicio do documento.';
              #43 : Result := 'Comando Inv�lido para o item.';
            end;
          end;

     #14: begin
            Case sRetorno[2] of
              #01 : Result := 'N�mero de linhas em documento excedido.';
              #02 : Result := 'N�mero do relat�rio inv�lido.';
              #03 : Result := 'Opera��o n�o permitida ap�s registro de itens.';
              #04 : Result := 'Registro de valor nulo n�o permitido.';
              #05 : Result := 'N�o h� desconto a cancelar.';
              #06 : Result := 'N�o h� acr�scimo a cancelar.';
              #07 : Result := 'Opera��o somente permitida ap�s subtotaliza��o.';
              #08 : Result := 'Opera��o somente permitida durante registro de itens.';
              #09 : Result := 'Opera��o n�o-fiscal inv�lida.';
              #10 : Result := '�ltimo comprovante N�o-Fiscal n�o encontrado.';
              #11 : Result := 'Meio de pagamento n�o encontrado.';
              #12 : Result := 'N�o foi poss�vel imprimir nova via.';
              #13 : Result := 'N�o foi poss�vel realizar reimpress�o.';
              #14 : Result := 'N�o foi poss�vel imprimir nova parcela.';
              #15 : Result := 'N�o h� mais parcelas a imprimir.';
              #16 : Result := 'Registro de item N�o-Fiscal inv�lido.';
              #17 : Result := 'Desconto ou acr�scimo j� efetuado.';
              #18 : Result := 'Valor de desconto ou acr�scimo inv�lido.';
              #19 : Result := 'N�o foi poss�vel cancelar o item.';
              #20 : Result := 'Itens em excesso.';
              #21 : Result := 'Opera��o N�o-Fiscal n�o cadastrada.';
              #22 : Result := 'Excesso de relat�rios / opera��es n�o-fiscais cadastradas.';
              #23 : Result := 'Relat�rio n�o encontrado.';
              #24 : Result := 'Comando n�o permitido.';
              #25 : Result := 'Comando n�o permitido em opera��es n�o-fiscais para movimento de monet�rio.';
              #26 : Result := 'Comando permitido apenas em opera��es n�o-fiscais para movimento de monet�rio.';
              #27 : Result := 'N�mero de parcelas inv�lido para a emiss�o de CCD';
              #28 : Result := 'Opera��o n�o fiscal j� cadastrada.';
              #29 : Result := 'Relat�rio gerencial j� cadastrado.';
              #30 : Result := 'Relat�rio Gerencial Inv�lido.';
            end;
          end;

     #18: begin
            Case sRetorno[2] of
              #01 : Result := 'Configura��o de cheque n�o registrada.';
              #02 : Result := 'Configura��o de cheque n�o encontrada.';
              #03 : Result := 'Valor do cheque j� impresso.';
              #04 : Result := 'Nominal ao cheque j� impresso.';
              #05 : Result := 'Linhas adicionais no cheque j� impresso.';
              #06 : Result := 'Autentica��o j� impressa.';
              #07 : Result := 'N�mero m�ximo de autentica��es j� impresso.';
            end;
          end;

     #255 : begin
              Case sRetorno[2] of
                #255 : Result := 'Erro desconhecido.';
              end;
            end;
  end;
end;


{ ----------------------------- TACBrECFEpson ----------------------------- }

constructor TACBrECFEpson.create( AOwner : TComponent ) ;
begin
  inherited create( AOwner ) ;

  fsEpsonComando   := TACBrECFEpsonComando.create ;
  fsEpsonResposta  := TACBrECFEpsonResposta.create( self ) ;

  fpDevice.Baud      := 38400;
  fpDevice.Parity    := pNone;
  fpDevice.Stop      := s1;
  fpDevice.Data      := 8;
  fpDevice.HandShake := hsDTR_DSR;
  fpColunas          := -1 ;  // For�a a detec��o de Par�metros de inicializa�ao ao ativar

  fsTentaDetectarVelocidade := True;

  { Variaveis internas dessa classe }
  fsNumVersao := '' ;
  fsNumECF    := '' ;
  fsNumLoja   := '' ;
  fsCNPJ      := '' ;
  fsIE        := '' ;
  fsIM        := '' ;
  fsCliche    := '' ;
  fsUsuarioAtual    := '' ;
  fsDataHoraSB      := now ;
  fsSubModeloECF    := '' ;
  fsPAF1      := '' ;
  fsPAF2      := '' ;
  fsImprimeCheque := False ;
  fsLeituraCMC7   := False ;
  fsVerificaChecksum := True ;
  fsEmPagamento := false ;

  fpMFD       := True ;
  fpTermica   := True ;
  fpIdentificaConsumidorRodape := True ;

  ZeraCache;

  fpModeloStr := 'Epson' ;
  fpRFDID     := 'EP' ;
  fpPaginaDeCodigo := 850 ;

  xEPSON_Obter_Dados_MF_MFD := NIL ;
  xEPSON_Serial_Abrir_Porta := NIL ;
  xEPSON_Serial_Fechar_Porta := NIL ;
  xEPSON_Serial_Obter_Estado_Com := NIL ;
  xEPSON_Send_From_FileEXX := NIL;
end;

destructor TACBrECFEpson.Destroy;
begin
  fsEpsonComando.Free ;
  fsEpsonResposta.Free ;

  inherited Destroy ;
end;

procedure TACBrECFEpson.Ativar_Epson ;
begin
  try
     if fpColunas < 0 then
     begin
        EpsonComando.Comando := '0905' ;  // Obtendo o numero de colunas
        EnviaComando ;
        fsTentaDetectarVelocidade := False;
        fpColunas := max( StrToIntDef( EpsonResposta.Params[0], 0 ), 48) ;

        EpsonComando.Comando := '0585' ;  // Obtendo o numero de Decimais
        EnviaComando ;
        fpDecimaisQtd   := StrToIntDef( EpsonResposta.Params[0], fpDecimaisQtd) ;
        fpDecimaisPreco := StrToIntDef( EpsonResposta.Params[1], fpDecimaisPreco) ;

        EpsonComando.Comando := '090A' ; // Obtendo se a ECF Imprime Cheque, e Le CMC7
        EnviaComando ;
        fsImprimeCheque :=  EpsonResposta.Params[4]  = 'S';
        fsLeituraCMC7   :=  EpsonResposta.Params[14] = 'S';
     end ;

     fsIsFBIII := (pos( 'FBIII', SubModeloECF ) > 0) ;

  except
     On E : Exception do
     begin
        raise EACBrECFNaoInicializado.Create( ACBrStr(
                 'Erro inicializando a impressora '+fpModeloStr) + sLineBreak +
                 E.Message );
     end;
  end ;
end ;

procedure TACBrECFEpson.Ativar;
begin
  GravaLog( 'ACBrDevice.Ativar' );

  fsUsarDLL := False;

  if fpDevice.IsDLLPort then
   begin
     LoadDLLFunctions ;
     PaginaDeCodigo := 852;
     AbrePortaSerialDLL;
     fsUsarDLL := True;
   end
  else
   begin
     if not fpDevice.IsSerialPort  then
        raise EACBrECFERRO.Create(ACBrStr('A impressora: '+fpModeloStr+' requer'+sLineBreak+
                                'Porta Serial:  (COM1, COM2, COM3, ...)'));
   end ;

  inherited Ativar ; { Abre porta serial }

  fsNumVersao := '' ;
  fsNumECF    := '' ;
  fsNumLoja   := '' ;
  fsCNPJ      := '' ;
  fsIE        := '' ;
  fsIM        := '' ;
  fsCliche    := '' ;
  fsUsuarioAtual := '' ;
  fsDataHoraSB   := now ;
  fsSubModeloECF := '' ;

  fsALNegrito    := False;
  fsALExpandido  := False;
  fsALSublinhado := False;

  ZeraCache;

  try
    try
       Ativar_Epson;
    except
       On E : Exception do
       begin
          if fsTentaDetectarVelocidade and (pos('(ACK = 0)',E.message) > 0) then
           begin
             if fpDevice.Baud = 38400 then
              begin
                fpDevice.Baud := 115200 ;
                Ativar_Epson;
              end
             else if fpDevice.Baud = 115200 then
              begin
                fpDevice.Baud := 38400 ;
                Ativar_Epson;
              end
             else
                raise ;
           end
          else
             raise ;
       end ;
    end ;
  except
     Desativar;
     raise ;
  end ;

end;

procedure TACBrECFEpson.Desativar ;
begin
  if fsUsarDLL and Ativo then
     try
        FechaPortaSerialDLL(False);
     except
        {Exce��o muda pois Porta pode j� estar fechada}
     end ;

  inherited Desativar ;
end ;


Function TACBrECFEpson.EnviaComando_ECF( cmd : AnsiString = '' ) : AnsiString ;
Var
  ErroMsg    : String ;
  OldTimeOut, Ret : Integer ;
  aStatus  : array [0..20] of AnsiChar;
  aLineOut : array [0..4096] of AnsiChar;
begin
  if cmd <> '' then
     PreparaCmd(cmd) ;  // Ajusta e move para Epsoncomando

  EpsonResposta.Resposta := '' ;  // Zera resposta
  fpComandoEnviado       := '' ;
  fpRespostaComando      := '' ;
  Result                 := '' ;
  ErroMsg                := '' ;

  if fsUsarDLL then
   begin
     cmd := EpsonComando.FrameEnvioDLL ;

     aStatus[0]  := #0; // Zera Buffer de Saida
     aLineOut[0] := #0;

     GravaLog( '   xEPSON_Send_From_FileEXX -> '+cmd, True );
     Ret := xEPSON_Send_From_FileEXX( cmd, aStatus, aLineOut ) ;
     GravaLog( '      Ret = '+IntToStr(Ret) );

     fpComandoEnviado  := cmd ;
     fpRespostaComando := TrimRight( aStatus )+'|:|'+TrimRight( aLineOut );

     if Ret <> 0 then
     begin
        Ret := StrToIntDef( '$'+copy(aStatus,5,4), 0 ); // Le Status da Comunica��o
        if Ret <> 0 then
           raise EACBrECFSemResposta.create(Format(ACBrStr(cACBrECFEnviaCmdSemRespostaException), [ ModeloStr ]));
     end ;

     EpsonResposta.Resposta := fpRespostaComando ;
   end
  else
   begin
     cmd := EpsonComando.FrameEnvio ;

     fsBytesIn := 0 ;
     fsByteACK := 0 ;

     OldTimeOut := TimeOut ;
     TimeOut    := max(EpsonComando.TimeOut, TimeOut) ;

     try
        fpDevice.Serial.DeadlockTimeout := 2000 ; { Timeout p/ Envio }

        { Segundo suporte da Epson, em alguns casos ECF n�o envia o ACK,
          enviando diretamente o STX (que � o inicio do Frame de Resposta) }
        while not (chr(fsByteACK) in [STX,ACK]) do     { Se ACK = 6 Comando foi reconhecido }
        begin
           fsByteACK := 0 ;
           fpDevice.Serial.Purge ;                   { Limpa a Porta }

           if not TransmiteComando( cmd ) then
              continue ;

           fpComandoEnviado := cmd ;

           if fpDevice.HandShake = hsDTR_DSR then
              fpDevice.Serial.DTR := True ;  { Liga o DTR para ler a Resposta }

           if not fpDevice.Serial.CTS then
              fpDevice.Serial.RTS := false ;

           try
              { espera ACK chegar na Porta por 1,5s  }
              try
                 fsByteACK := fpDevice.Serial.RecvByte( 1500 ) ;
              except
              end ;

              if fsByteACK = 0 then
                 raise EACBrECFSemResposta.create( ACBrStr(
                       'Impressora '+fpModeloStr+' n�o responde (ACK = 0)' ))
              else if chr(fsByteACK) = NAK then    { retorno em caracter 21d=15h=NACK }
                 raise EACBrECFSemResposta.create( ACBrStr(
                       'Impressora '+fpModeloStr+' n�o reconheceu o Comando'+
                       sLineBreak+' (NACK)') )
              else if not (chr(fsByteACK) in [STX,ACK]) then
                 raise EACBrECFSemResposta.create( ACBrStr(
                       'Erro. Resposta da Impressora '+fpModeloStr+' inv�lida'+
                       sLineBreak+' (ACK = '+IntToStr(fsByteACK)+')')) ;
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

        { Chama Rotina da Classe m�e TACBrClass para ler Resposta. Se houver
          falha na leitura LeResposta dispara Exce�ao.
          Resposta fica gravada na v�riavel "fpRespostaComando" }
        LeResposta ;
     finally
        TimeOut := OldTimeOut ;
        Sleep( IntervaloAposComando ) ;  { Pequena pausa entre comandos }
     end ;
   end ;

  ErroMsg := EpsonResposta.DescRetorno ;
  if ErroMsg <> '' then
  begin
     ErroMsg := 'Erro retornado pela Impressora: ' + fpModeloStr + sLineBreak+sLineBreak+
                'Erro: '+ EpsonResposta.Retorno+ ' - '+ErroMsg  ;

     if EpsonResposta.Retorno = '0304' then
        DoOnErrorSemPapel
     else
        raise EACBrECFSemResposta.create( ACBrStr(ErroMsg) ) ;
  end
end;

Procedure TACBrECFEpson.PreparaCmd(cmd: AnsiString) ;
 Var Buf : AnsiString ;
     P   : Integer ;
     SL  : TStringList ;
begin
  { Quebrando Parametros Separados por FS }
  Buf := Cmd ;
  SL  := TStringList.create;
  try
     SL.Clear ;
     while Buf <> '' do
     begin
        P := pos(FS,Buf) ;
        if P = 0 then
           P := Length(Buf)+1 ;

        SL.Add( LeftStr(Buf,P-1) ) ;
        Buf := copy(Buf,P+1,Length(Buf)) ;  // Pega pr�ximo bloco
     end ;

     if SL.Count < 1 then
        raise EACBrECFERRO.create(ACBrStr('Erro ao informar comando.  Use:'+sLineBreak+
                               'Comando(4 Hex) #28 Extensao(4 Hex) [ #28 PARAM1 [ #28 PARAM2 ... ]] '+sLineBreak+sLineBreak+
                               'Exemplo, Para emitir Leitura X use: '+sLineBreak+
                               'EnviaComando("0802" + #28 + "0000" ) ') );

     if SL.Count < 2 then
        SL.Add('0000') ;

     EpsonComando.Comando  := SL[0] ;
     EpsonComando.Extensao := SL[1] ;

     for P := 2 to SL.Count-1 do
        EpsonComando.AddParamString( SL[P] );
  finally
     SL.Free ;
  end ;
end;

Function TACBrECFEpson.VerificaFimLeitura(var Retorno: AnsiString;
   var TempoLimite: TDateTime) : Boolean ;

var
  LenRet : Integer ;

  Function BlocoEValido : Boolean ;
  begin
    Result := ((LeftStr(Retorno,1) = STX) and (LenRet >= 7) and
               (copy( Retorno, LenRet-4, 1) = ETX) ) ;
  end ;

begin
  if fsUsarDLL then
  begin
    Result := True;
    exit;
  end ;

  if chr(fsByteACK) = STX then  // N�o houve ACK, ECF mandou STX direto, e STX j� foi lido
  begin
    Retorno   := STX + Retorno ;
    fsByteACK := 0;
  end ;

  LenRet := Length(Retorno) ;
  Result := BlocoEValido;

  if LenRet > fsBytesIn then  // ECF est� enviando dados... aumente o TimeOut
     TempoLimite := IncSecond(now, TimeOut);

  fsBytesIn := LenRet;

  if not Result then
  begin
     // DEBUG //
     //GravaLog( 'Aguardando Bloco: ' +Retorno, True );
     exit ;
  end ;

  // � Envio de Resposta Intermedi�ria ?
  if (LeftStr(Retorno,7) = #2 + #128 + #3 + '0085') then
  begin
     Retorno     := Copy(Retorno, 8, LenRet );
     LenRet      := Length(Retorno) ;
     fsBytesIn   := LenRet;
     Result      := BlocoEValido ; // Re-avalia o Retorno restante
     GravaLog( Space(16) + 'RI' + IfThen(Result,'+','-'), True );

     // NOTA: No caso de FIM DE PAPEL, o ECF Epson pode ficar retornando
     //       resposta intermedi�ria indefinidamente, o que causa um Loop Infinito.
     //       Segundo suporte da Epson n�o h� solu��o poss�vel no momento.
     //       Detectado que o mesmo problema pode ocorrer com a DLL do Fabricante
  end ;

  if Result then
  begin
     try
        { Esta atribui��o, J� verifica o ChkSum, em caso de erro gera exception }
        EpsonResposta.Resposta := Retorno ;
        fpDevice.Serial.SendByte(ord(ACK));

        if EpsonResposta.Seq <> EpsonComando.Seq then  // Despreza esse Bloco
        begin
           GravaLog( '   Sequencia de Resposta ('+IntToStr(EpsonResposta.Seq)+')'+
                     'diferente da enviada ('+IntToStr(EpsonComando.Seq)+
                     '). Bloco Desprezado: '+Retorno, True) ;
           Result  := False ;
           Retorno := '' ;
        end;
     except
        on E : Exception do
        begin
           fpDevice.Serial.SendByte(ord(NAK));
           GravaLog( '   Pacote Inv�lido, NACK enviado: '+Retorno, True ) ;
           Result  := False ;
           Retorno := '' ;
           fpDevice.Serial.Purge;  // Zera conteudo de Porta Serial
        end ;
     end ;
  end ;

  // DEBUG //
  //if Result then
  //   GravaLog('Fim da Leitura: '+Retorno, True);
end;

function TACBrECFEpson.GetDataHora: TDateTime;
Var
  DtStr, HrStr : AnsiString ;
begin
  EpsonComando.Comando := '0502' ;
  EnviaComando ;

  DtStr := EpsonResposta.Params[0] ;
  HrStr := EpsonResposta.Params[1] ;

  Result := EncodeDateTime( StrToInt(copy(DtStr, 5,4)),   // Ano
                            StrToInt(copy(DtStr, 3,2)),   // Mes
                            StrToInt(copy(DtStr, 1,2)),   // Dia
                            StrToInt(copy(HrStr, 1,2)),   // Hora
                            StrToInt(copy(HrStr, 3,2)),   // Min
                            StrToInt(copy(HrStr, 5,2)),   // Seg
                            0 );
end;

function TACBrECFEpson.GetNumCupom: String;
begin
  EpsonResposta.Resposta := Ret0907 ;
  Result := EpsonResposta.Params[0] ;
end;

function TACBrECFEpson.GetNumCCF: String;
begin
  EpsonResposta.Resposta := Ret0907 ;
  Result := EpsonResposta.Params[7] ;
end;

function TACBrECFEpson.GetNumCRO: String;
begin
  EpsonResposta.Resposta := Ret0907 ;
  Result := EpsonResposta.Params[2] ;
end;

function TACBrECFEpson.GetNumCRZ: String;
begin
  EpsonResposta.Resposta := Ret0907 ;
  Result := EpsonResposta.Params[1] ;
end;

function TACBrECFEpson.GetNumGNF: String;
begin
  EpsonResposta.Resposta := Ret0907 ;
  Result := EpsonResposta.Params[3] ;
end;

function TACBrECFEpson.GetNumGNFC : String ;
begin
  EpsonResposta.Resposta := Ret0907 ;
  Result := EpsonResposta.Params[5] ;
end ;

function TACBrECFEpson.GetNumCFD : String ;
begin
  EpsonResposta.Resposta := Ret0907 ;
  Result := EpsonResposta.Params[9] ;
end ;

function TACBrECFEpson.GetNumGRG: String;
begin
  EpsonResposta.Resposta := Ret0907 ;
  Result := EpsonResposta.Params[6] ;
end;

function TACBrECFEpson.GetNumCDC: String;
begin
  EpsonResposta.Resposta := Ret0907 ;
  Result := EpsonResposta.Params[4] ;
end;

function TACBrECFEpson.GetNumCFC: String;
begin
  EpsonResposta.Resposta := Ret0907 ;
  Result := EpsonResposta.Params[8] ;
end;

function TACBrECFEpson.GetNumNCN : String ;
begin
  EpsonResposta.Resposta := Ret0907 ;
  Result := EpsonResposta.Params[11] ;
end ;

function TACBrECFEpson.GetNumLoja: String;
begin
  if fsNumLoja = '' then
  begin
     EpsonComando.Comando := '0507' ;
     EnviaComando ;

     fsCNPJ    := Trim(EpsonResposta.Params[5]) ;
     fsIE      := Trim(EpsonResposta.Params[6]) ;
     fsCliche  := Trim(EpsonResposta.Params[0]) ;
     fsIM      := Trim(EpsonResposta.Params[7]) ;
     fsUsuarioAtual    := Trim(EpsonResposta.Params[11]) ;
     fsNumECF  := EpsonResposta.Params[8] ;
     fsNumLoja := OnlyNumber( EpsonResposta.Params[9] ) ;
  end ;

  Result := fsNumLoja ;
end;

function TACBrECFEpson.GetNumECF: String;
begin
  if fsNumECF = '' then
     GetNumLoja ;

  Result := fsNumECF ;
end;

function TACBrECFEpson.GetRet0402(Indice : Integer) : AnsiString ;
begin
  EpsonComando.Comando := '0402' ;
  EnviaComando ;

  Indice := min( max(Indice,0), EpsonResposta.Params.Count-1) ;
  Result := EpsonResposta.Params[Indice] ;

  fsSubModeloECF := EpsonResposta.Params[3] ;
  fsNumVersao    := EpsonResposta.Params[5];
end ;

function TACBrECFEpson.GetNumSerie: String;
begin
  Result := GetRet0402(0);
end;

function TACBrECFEpson.GetNumSerieMFD: String;
begin
  Result := GetRet0402(1);
end;

function TACBrECFEpson.GetNumVersao: String ;
begin
  if fsNumVersao = '' then
     GetNumSerie ;

  Result := fsNumVersao ;
end;

function TACBrECFEpson.GetTotalPago: Double;
begin
  try
     EpsonComando.Comando := '0A0A' ;
     EnviaComando ;

     Result := StrToFloatDef(EpsonResposta.Params[2],0) /100 ;
     Result := RoundTo( Result, -2) ;
  except
     on E : Exception do
     begin
        if (pos('0102',E.Message) <> 0) then
           Result := 0
        else
           raise ;
     end ;
  end ;
end;

function TACBrECFEpson.GetNumReducoesZRestantes: String;
begin
  EpsonComando.Comando := '080A' ;
  EnviaComando ;

  Result := EpsonResposta.Params[7] ;
end;

function TACBrECFEpson.GetSubTotal: Double;
begin
  try
    Result := RespostasComando['SubTotal'].AsFloat;
  except
    try
       if Estado = estNaoFiscal then
        begin
          EpsonComando.Comando := '0E03' ;
          EnviaComando ;
        end
       else
        begin
          EpsonComando.Comando := '0A03' ;
          EnviaComando ;
        end ;

       RespostasComando.AddField( 'SubTotal', EpsonResposta.Params[0] );
       Result := StrToFloatDef(EpsonResposta.Params[0],0) /100 ;
       Result := RoundTo( Result, -2) ;
    except
       on E : Exception do
       begin
          if (pos(EpsonResposta.Retorno, '0102|0A13') <> 0)  then
             Result := 0
          else
             raise ;
       end ;
    end ;
  end ;
end;

{  Ordem de Retorno do Estado da Impressora
   estNaoInicializada - N�o Inicializada (Nova)
   estDesconhecido    - Desconhecido
   estPagamento       - Cupom Venda Aberto em Pagamento
   estVenda           - Cupom Venda Aberto em Itens
   estNaoFiscal       - Cupom N�o Fiscal Aberto
   estRelatorio       - Cupom Vinculado Aberto | Relat�rio Gerencial Aberto
   estBloqueada       - Impressora Bloqueada para venda
   estRequerZ         - Requer Emiss�o da Redu��o da Z
   estRequerX         - Requer Leitura X
   estLivre           - Livre para vender
}
function TACBrECFEpson.GetEstado: TACBrECFEstado;
  Var BitS : AnsiString ;
begin
   Result := fpEstado ;  // Suprimir Warning
   try
      fpEstado := estNaoInicializada ;
      if (not fpAtivo) then
         exit ;

      fpEstado := estDesconhecido ;

      try
         EpsonComando.Comando := '0810' ;
         EnviaComando ;
      except
         on E : Exception do
         begin
            if (pos('0106',E.Message) <> 0) then  // Erro: 0106 - Comando aceito apenas fora de interven��o.
               exit ;

            raise;
         end ;
      end ;

      BitS := ACBrUtil.IntToBin(EpsonResposta.StatusFiscal, 16) ;

      if copy(BitS,1,2) <> '11' then  // Diferente de Modo fiscalizado ?
         exit ;

      if copy(BitS,13,4) = '0001' then
       begin
         EpsonComando.Comando := '0A0A' ;
         EnviaComando ;

         if fsEmPagamento or (EpsonResposta.Params[10] >= '2') then
            fpEstado := estPagamento
         else
            fpEstado := estVenda
       end
      else if copy(BitS,13,4) = '1000' then
         fpEstado := estNaoFiscal

      else if pos(copy(BitS,13,4),'0010|0011|0100') > 0 then
         fpEstado := estRelatorio

      else if (EpsonResposta.Params[0] = '2') then
         fpEstado := estBloqueada

      else if (EpsonResposta.Params[0] = '4') then
         fpEstado := estRequerZ

      else if (EpsonResposta.Params[0] = '3') then
         fpEstado := estRequerX

      else if copy(BitS,13,4) = '0000' then
         fpEstado := estLivre;

   finally
      Result := fpEstado ;
   end ;
end;

function TACBrECFEpson.GetGavetaAberta: Boolean;
begin
  EpsonComando.Comando := '0708' ;
  EnviaComando ;

  Result := (EpsonResposta.Params[0] = 'S') ;
end;

function TACBrECFEpson.GetPoucoPapel: Boolean;
begin
  EpsonComando.Comando := '0001' ;
  EnviaComando ;

  Result := TestBit(EpsonResposta.StatusPrinter,0) or
            TestBit(EpsonResposta.StatusPrinter,2) ;
end;

function TACBrECFEpson.GetHorarioVerao: Boolean;
begin
  EpsonComando.Comando := '0511' ;
  EnviaComando ;

  Result := (EpsonResposta.Params[0] = 'S') ;
end;

function TACBrECFEpson.GetParamDescontoISSQN : Boolean ;
begin
  EpsonComando.Comando := '0513' ;
  EnviaComando ;

  Result := (EpsonResposta.Params[0] = 'S') ;
end ;

function TACBrECFEpson.GetTipoUltimoDocumento : TACBrECFTipoDocumento ;
var
   Tipo : Integer ;
begin
  EpsonComando.Comando := '0908' ;
  EnviaComando ;

  Tipo := StrToIntDef(EpsonResposta.Params[0],0) ;
  case Tipo of
    1 : Result := docCF;
    2 : Result := docRZ;
    3 : Result := docLX;
    5 : Result := docLMF;
    22: Result := docCupomAdicional;
    23: Result := docCFCancelamento;
    24: Result := docCNF;
    25: Result := docCNFCancelamento;
    26: Result := docEstornoPagto;
    27: Result := docCCD;
    28: Result := docEstornoCCD;
    29,14: Result := docRG;
  else
    Result := docNenhum;
  end ;
end ;

Procedure TACBrECFEpson.LeituraX ;
begin
  if Estado = estRequerx then
     EpsonComando.Comando := '0805'
  else
     EpsonComando.Comando := '0802' ;

  EpsonComando.TimeOut := TempoInicioMsg + 2 ;  // apenas para o bloqueio de teclado funcionar
  EnviaComando ;

  ZeraCache;
end;

procedure TACBrECFEpson.LeituraXSerial(Linhas: TStringList);
  Var I : Integer ;
begin
  with EpsonComando do
  begin
     Comando  := '0802' ;
     Extensao := '0001' ;
  end ;
  EnviaComando ;
  Sleep(200);

  Linhas.Clear ;
  while (EpsonResposta.Params.Count > 1) do
  begin
     if (EpsonResposta.Params.Count > 2) then
        For I := 2 to EpsonResposta.Params.Count-1 do
           Linhas.Add( EpsonResposta.Params[I] ) ;

     if EpsonResposta.Params[1] <> 'S' then
        break ;

     with EpsonComando do
     begin
        Comando  := '0802' ;
        Extensao := '0003' ;
     end ;

     try
        EnviaComando ;
     except
        break ;
     end ;
  end ;
end;

Procedure TACBrECFEpson.AbreGaveta ;
begin
  EpsonComando.Comando := '0707' ;   // Gaveta 1 ??
  EpsonComando.Extensao := '0000' ;
  EnviaComando ;

  EpsonComando.Comando := '0707' ;   // Gaveta 2 ??
  EpsonComando.Extensao := '0001' ;
  EnviaComando ;
end;

Procedure TACBrECFEpson.ReducaoZ(DataHora: TDateTime) ;
var
  DtHrECF : TDateTime ;
begin
  if DataHora <> 0 then
  begin
     DtHrECF  := GetDataHora;
     DataHora := max( IncMinute(DtHrECF,-5), min( IncMinute(DtHrECF,5), DataHora)) ;
  end ;

  EpsonComando.Comando := '0801' ;
  EpsonComando.TimeOut := TempoInicioMsg + 30 ;  // apenas para o bloqueio de teclado funcionar
  if DataHora <> 0 then
   begin
     EpsonComando.Extensao := '0001' ;
     EpsonComando.AddParamDateTime( DataHora, 'D' ) ;
     EpsonComando.AddParamDateTime( DataHora, 'T' ) ;
   end
  else
   begin
     EpsonComando.AddParamString( '' ) ;
     EpsonComando.AddParamString( '' ) ;
   end ;

  try
     EnviaComando ;
  except
     on E : Exception do
     begin
        if (pos('0107',E.Message) > 0) or     // Erro de Hora fora da faixa ?
           (pos('0503',E.Message) > 0) then   // Data em intervalo inv�lido.
           ReducaoZ(0)                         // Tenta sem DataHora
        else if (pos('0102',E.Message) > 0) then   // Comando inv�lido para o documento atual.
         begin                                      //  Ficou algum Cupom aberto ?
           CancelaCupom ;
           ReducaoZ(DataHora);
         end 
        else
           raise ;
     end ;
  end ;

  ZeraCache;
end;

Procedure TACBrECFEpson.MudaHorarioVerao ;
begin
  MudaHorarioVerao( not HorarioVerao ) ;
end;

procedure TACBrECFEpson.MudaHorarioVerao(EHorarioVerao: Boolean);
begin
  EpsonComando.Comando := '0510' ;
  EnviaComando ;
end;

procedure TACBrECFEpson.AbreCupom ;
begin
  if Consumidor.Atribuido then
  begin
     EpsonComando.Comando  := '0A20' ;
     EpsonComando.Extensao := '0001' ;
     EpsonComando.AddParamString( LeftStr(Consumidor.Documento,20) );
     EpsonComando.AddParamString( LeftStr(Consumidor.Nome,30) );
     EpsonComando.AddParamString( copy(Consumidor.Endereco, 1,40) );
     EpsonComando.AddParamString( copy(Consumidor.Endereco,41,40) );
     EnviaComando ;
  end ;

  EpsonComando.Comando := '0A01' ;
  EpsonComando.AddParamString( '' );   // 2 Campos de entrada Reservados ;
  EpsonComando.AddParamString( '' );
  EnviaComando ;

  Consumidor.Enviado := True ;

  ZeraCache;
  fsEmPagamento := false ;
end;

procedure TACBrECFEpson.CancelaCupom;
Var
  Erro : String ;
begin
  ZeraCache;
  fsEmPagamento := false ;

  try
     // Cancelando o Cupom
     EpsonComando.Comando  := '0A18' ;
     EpsonComando.Extensao := '0008' ;
     EpsonComando.AddParamInteger( 1 );   // 1 Campo de entrada N�O usado no Cancelamento de cupom ;
     EnviaComando ;

     RespostasComando.AddField( 'SubTotal', EpsonResposta.Params[0] );
     RespostasComando.AddField( 'ValorCancelado', EpsonResposta.Params[1] );
  except
     on E : Exception do
     begin
        Erro := E.Message ;

        // Verificando se motivo do Erro foi falta do cancelamento do CDC (Erro 0A15)
        if (pos('0A15',Erro) > 0) then
         begin
           TACBrECF(fpOwner).EstornaCCD( True ) ;

           { Agora sim... Cancelando o Cupom }
           CancelaCupom;
         end
        else
         begin
           { Verifica se ficou algum relatorio aberto, e fecha }
           if Estado = estRelatorio then
            begin
              try
                 FechaRelatorio ;
              except
                 { Se n�o conseguiu Fechar Relatorio, dispara Msg de exce��o original }
                 raise EACBrECFERRO.Create( Erro );
              end ;
            end
           else
              raise ;
         end ;
     end ;
  end ;
end;

procedure TACBrECFEpson.CancelaItemVendido(NumItem: Integer);
begin
  EpsonComando.Comando  := '0A18' ;
  EpsonComando.Extensao := '0004' ;
  EpsonComando.AddParamInteger( NumItem ) ;
  EnviaComando ;

  ZeraCache;
  RespostasComando.AddField( 'SubTotal', EpsonResposta.Params[0] );
  RespostasComando.AddField( 'ValorCancelado', EpsonResposta.Params[1] );
end;

procedure TACBrECFEpson.EfetuaPagamento(CodFormaPagto: String;
  Valor: Double; Observacao: AnsiString; ImprimeVinculado: Boolean);
begin
  EpsonComando.Comando  := '0A05' ;
  EpsonComando.AddParamString( CodFormaPagto ) ;
  EpsonComando.AddParamDouble( Valor, 2 ) ;
  EpsonComando.AddParamString( copy(Observacao, 1,40) ) ;
  EpsonComando.AddParamString( copy(Observacao,41,40) ) ;
  EnviaComando ;

  ZeraCache;
  RespostasComando.AddField( 'TotalAPagar', EpsonResposta.Params[0] );
  RespostasComando.AddField( 'TotalTroco', EpsonResposta.Params[1] );
end;

procedure TACBrECFEpson.FechaCupom(Observacao: AnsiString; IndiceBMP : Integer);
 Var SL  : TStringList ;
     I   : Integer ;
     Obs : AnsiString ;
begin
  Obs := Observacao ;
  if not Consumidor.Enviado then
  begin
     { Removendo o Consumidor da Observa��o, pois vai usar comando pr�prio }
     Obs := StringReplace(Obs,#10+'CPF/CNPJ consumidor: '+Consumidor.Documento,'',[]) ;
     Obs := StringReplace(Obs,#10+'Nome: '+Consumidor.Nome,'',[]) ;
     Obs := StringReplace(Obs,#10+'Endereco: '+Consumidor.Endereco,'',[]) ;

     try
        EpsonComando.Comando  := '0A20' ;
        EpsonComando.Extensao := '0002' ;
        EpsonComando.AddParamString( LeftStr(Consumidor.Documento,20) );
        EpsonComando.AddParamString( LeftStr(Consumidor.Nome,30) );
        EpsonComando.AddParamString( copy(Consumidor.Endereco, 1,40) );
        EpsonComando.AddParamString( copy(Consumidor.Endereco,41,40) );
        EnviaComando ;
        
        Consumidor.Enviado := True ;
     except
        Obs := Observacao ;
     end ;
  end ;

  if Trim(Obs) <> '' then
  begin
     Obs := AjustaLinhas(Obs,50) ;
     SL := TStringList.create ;
     try
        SL.Text := Obs ;
        EpsonComando.Comando := '0A22' ;
        For I := 0 to 7 do
           if I >= SL.Count then
              EpsonComando.AddParamString( '' )
           else
              EpsonComando.AddParamString( SL[I] ) ;
        EnviaComando ;
     finally
        SL.Free ;
     end ;
  end ;

  EnviaPAF ;

  EpsonComando.Comando  := '0A06' ;
  EpsonComando.Extensao := '0001' ;   // Corta folha
  EnviaComando ;

  ZeraCache;
  fsEmPagamento := false ;
  RespostasComando.AddField( 'NumCCF', EpsonResposta.Params[0] );
  RespostasComando.AddField( 'TotalPago', EpsonResposta.Params[1] );
  RespostasComando.AddField( 'TotalTroco', EpsonResposta.Params[2] );
end;

procedure TACBrECFEpson.SubtotalizaCupom(DescontoAcrescimo: Double;
       MensagemRodape : AnsiString);
begin
  fsEmPagamento := True ;
  if DescontoAcrescimo = 0 then
     exit ;

  EpsonComando.Comando  := '0A04' ;
  if DescontoAcrescimo < 0 then
     EpsonComando.Extensao := '0006'
  else
     EpsonComando.Extensao := '0007' ;
  EpsonComando.AddParamDouble( abs(DescontoAcrescimo), 2 );
  EnviaComando ;

  ZeraCache;
  RespostasComando.AddField( 'SubTotal', EpsonResposta.Params[0] );
end;

Procedure TACBrECFEpson.VendeItem( Codigo, Descricao : String;
  AliquotaECF : String; Qtd : Double ; ValorUnitario : Double;
  ValorDescontoAcrescimo : Double; Unidade : String;
  TipoDescontoAcrescimo : String; DescontoAcrescimo : String ;
  CodDepartamento: Integer) ;
begin
  Codigo    := LeftStr(Codigo,14);
  Unidade   := Trim(LeftStr( OnlyAlphaNum(Unidade),3)) ;
  Descricao := LeftStr(Descricao,233);

  with EpsonComando do
  begin
    if not fsIsFBIII then
     begin
       ArredondaItemMFD := False;  // N�o suportado na FBII

       Comando := '0A02' ;
       AddParamString( Codigo );
       AddParamString( Descricao );
       AddParamDouble( Qtd, fpDecimaisQtd );
       AddParamString( Unidade );
       AddParamDouble( ValorUnitario, fpDecimaisPreco );
       AddParamString( AliquotaECF );
     end
    else
     begin
        Comando := '0A12' ;
        AddParamString( Codigo );
        AddParamString( Descricao );
        AddParamDouble( Qtd, fpDecimaisQtd );
        AddParamString( Unidade );
        AddParamDouble( ValorUnitario, fpDecimaisPreco );
        AddParamString( AliquotaECF );
        AddParamInteger( fpDecimaisQtd );
        AddParamInteger( fpDecimaisPreco );
        AddParamBool( ArredondaItemMFD );
        AddParamString( 'N' );
     end ;
  end ;

  EnviaComando ;

  ZeraCache;
  fsEmPagamento := false ;

  if EpsonResposta.Params.Count > 0 then
     RespostasComando.AddField( 'NumUltItem', EpsonResposta.Params[0] );

  { Se o desconto � maior que zero d� o comando de desconto de item }
  if ValorDescontoAcrescimo > 0 then
     DescontoAcrescimoItemAnterior( ValorDescontoAcrescimo, DescontoAcrescimo,
        TipoDescontoAcrescimo);
end;

procedure TACBrECFEpson.DescontoAcrescimoItemAnterior(
   ValorDescontoAcrescimo : Double ; DescontoAcrescimo : String ;
   TipoDescontoAcrescimo : String ; NumItem : Integer) ;
begin
  if not fsIsFBIII then
   begin
     EpsonComando.Comando  := '0A04' ;
     if TipoDescontoAcrescimo = '%' then
      begin
        if DescontoAcrescimo = 'D' then
           EpsonComando.Extensao := '0000'
        else
           EpsonComando.Extensao := '0001' ;
      end
     else
      begin
        if DescontoAcrescimo = 'D' then
           EpsonComando.Extensao := '0004'
        else
           EpsonComando.Extensao := '0005' ;
      end ;

     EpsonComando.AddParamDouble( ValorDescontoAcrescimo, 2 );
     EnviaComando ;
   end
  else
   begin
      EpsonComando.Comando  := '0A07' ;
      if TipoDescontoAcrescimo = '%' then
       begin
         if DescontoAcrescimo = 'D' then
            EpsonComando.Extensao := '0000'
         else
            EpsonComando.Extensao := '0001' ;
       end
      else
       begin
         if DescontoAcrescimo = 'D' then
            EpsonComando.Extensao := '0010'
         else
            EpsonComando.Extensao := '0011' ;
       end ;

      EpsonComando.AddParamInteger( NumItem );
      EpsonComando.AddParamDouble( ValorDescontoAcrescimo, 2 );
      EnviaComando ;
   end ;

  ZeraCache( False );
  RespostasComando.AddField( 'SubTotal', EpsonResposta.Params[0] );
  fsEmPagamento := false ;
end ;

procedure TACBrECFEpson.CarregaAliquotas;
var
  A         : Integer;
  Aliquota  : TACBrECFAliquota ;
  ValAliq, TotAliq : Double ;
  IndAliq   : AnsiString ;
  iAliquotas: Integer ;
begin
  inherited CarregaAliquotas ;   { Cria fpAliquotas }

  try
     EpsonComando.Comando := '0542' ;
     EnviaComando ;

     iAliquotas := Trunc(EpsonResposta.Params.Count / 3);

     For A := 1 to iAliquotas do
     begin
        IndAliq  := EpsonResposta.Params[(A * 3) - 3];
        ValAliq  := RoundTo( StrToFloatDef(EpsonResposta.Params[(A * 3) - 2], 0 ) / 100, -2) ;
        TotAliq  := RoundTo( StrToFloatDef(EpsonResposta.Params[(A * 3) - 1], 0 ) / 100, -2) ;

        Aliquota := TACBrECFAliquota.create;

        Aliquota.Indice   := IndAliq ;
        Aliquota.Tipo     := Char(IndAliq[1]);
        Aliquota.Aliquota := ValAliq ;
        Aliquota.Total    := TotAliq ;

        fpAliquotas.Add(Aliquota);
     end;

  except
     { Se falhou ao carregar, deve "nilzar" as variaveis para que as rotinas
       "Acha*" tentem carregar novamente }
     fpAliquotas.Free;
     fpAliquotas := nil;

     raise ;
  end ;
end;

procedure TACBrECFEpson.LerTotaisAliquota;
begin
  CarregaAliquotas ;
end;


procedure TACBrECFEpson.ProgramaAliquota(Aliquota: Double; Tipo: Char;
   Posicao : String);
begin
  Tipo := UpCase(Tipo) ;

  EpsonComando.Comando := '0540' ;
  if Tipo = 'S' then
     EpsonComando.Extensao := '0001' ;
  EpsonComando.Params.Add( IntToStr(Round(Aliquota * 100)) ) ;
  EnviaComando ;

  CarregaAliquotas ;
  ZeraCache;
end;

function TACBrECFEpson.AchaICMSAliquota( var AliquotaICMS: String):
   TACBrECFAliquota;
Var
  AliquotaStr : String ;
begin
  AliquotaStr := '' ;
  Result      := nil ;

  if pos(copy(AliquotaICMS,1,2), 'TT,SS') > 0 then { Corrige Duplo T ou S }
     AliquotaICMS := Trim(Copy(AliquotaICMS,2,5));

  if copy(AliquotaICMS,1,2) = 'SF' then
     AliquotaStr := 'FS'
  else if copy(AliquotaICMS,1,2) = 'SN' then
     AliquotaStr := 'NS'
  else if copy(AliquotaICMS,1,2) = 'SI' then
     AliquotaStr := 'IS'
  else
     case AliquotaICMS[1] of
        'F','I','N' : AliquotaStr := AliquotaICMS[1] ;
        'T' : AliquotaICMS := 'TT'+copy(AliquotaICMS,2,2) ; {Indice}
        'S' : AliquotaICMS := 'TS'+copy(AliquotaICMS,2,2) ; {Indice}
     end ;

  if AliquotaStr = '' then
     Result := inherited AchaICMSAliquota( AliquotaICMS )
  else
     AliquotaICMS := AliquotaStr ;
end;


procedure TACBrECFEpson.CarregaFormasPagamento;
Var A      : Integer;
    FPagto : TACBrECFFormaPagamento ;
begin
  inherited CarregaFormasPagamento ;   { Cria fpFormasPagamentos }

  try
     { L� as Formas de Pagamento cadastradas na impressora }
     A := 1;
     while (A <= 20) do
     begin
        EpsonComando.Comando := '050D' ;
        EpsonComando.AddParamInteger( A ) ;
        try
           EnviaComando ;

           FPagto := TACBrECFFormaPagamento.create ;
           FPagto.Indice    := IntToStr( A ) ;
           FPagto.Descricao := EpsonResposta.Params[0] ;
           FPagto.PermiteVinculado := ( EpsonResposta.Params[1] = 'S' ) ;

           fpFormasPagamentos.Add( FPagto ) ;
        except
           on E : Exception do
           begin
              // 090C �Tipo de pagamento n�o definido�
              if (pos('090C',E.Message) > 0) then
                 Break
              else
                 raise ;
           end ;
        end;

        Inc( A )
     end ;
  except
     { Se falhou ao carregar, deve "nilzar" as variaveis para que as rotinas
       "Acha*" tentem carregar novamente }
     fpFormasPagamentos.Free;
     fpFormasPagamentos := nil;

     raise ;
  end ;
end;

procedure TACBrECFEpson.LerTotaisFormaPagamento;
Var A : Integer ;
begin
  if not Assigned( fpFormasPagamentos ) then
     CarregaFormasPagamento ;

  For A := 0 to FormasPagamento.Count-1 do
  begin
     EpsonComando.Comando := '0902' ;
     EpsonComando.AddParamString( FormasPagamento[A].Indice );
     EnviaComando ;

     // Andre Bohn - O Total da Forma de Pagamento vem no Params[1] e n�o no
     //              Params[2], no caso da Epson TM H6000 FBII, mas
     //              pode ser o caso da TM88 tamb�m.   
     FormasPagamento[A].Total := RoundTo( StringToFloatDef(
                                        EpsonResposta.Params[1], 0) / 100, -2) ;
  end ;
end;

procedure TACBrECFEpson.ProgramaFormaPagamento( var Descricao: String;
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

  EpsonComando.Comando := '050C' ;
  if PermiteVinculado then
     EpsonComando.Extensao := '0001' ;
  EpsonComando.AddParamInteger( ProxIndice ) ;
  EpsonComando.AddParamString( LeftStr(Descricao,15) ) ;
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

  ZeraCache;
end;

procedure TACBrECFEpson.CarregaComprovantesNaoFiscais;
Var A    : Integer ;
    CNF  : TACBrECFComprovanteNaoFiscal ;
begin
  inherited CarregaComprovantesNaoFiscais ;

  try
     A := 1 ;
     while (A <= 20) do
     begin
        EpsonComando.Comando  := '0902' ;
        EpsonComando.Extensao := '0001' ;
        EpsonComando.AddParamInteger( A ) ;
        try
           EnviaComando ;

           if EpsonResposta.Params.Count > 2 then
           begin
             CNF := TACBrECFComprovanteNaoFiscal.create ;

             CNF.Indice    := IntToStr(A) ;
             CNF.Descricao := EpsonResposta.Params[0] ;
             CNF.Total     := RoundTo( StringToFloatDef(
                                             EpsonResposta.Params[1], 0) / 100, -2) ;
             CNF.Contador  := StrToIntDef( EpsonResposta.Params[2], 0) ;

             fpComprovantesNaoFiscais.Add( CNF ) ;
           end;
        except
           on E : Exception do
           begin
              if (pos('0701',E.Message) = 0) then
                 raise ;
           end ;
        end;

        Inc( A ) ;
     end ;
  except
     { Se falhou ao carregar, deve "nilzar" as variaveis para que as rotinas
       "Acha*" tentem carregar novamente }
     fpComprovantesNaoFiscais.Free;
     fpComprovantesNaoFiscais := nil ;

     raise ;
  end ;
end;

procedure TACBrECFEpson.LerTotaisComprovanteNaoFiscal;
begin
  CarregaComprovantesNaoFiscais ;
end;

procedure TACBrECFEpson.ProgramaComprovanteNaoFiscal(var Descricao : String;
   Tipo: String; Posicao : String);
begin
  EpsonComando.Comando := '0572' ;
  EpsonComando.AddParamString( Descricao ) ;
  EnviaComando ;

  CarregaComprovantesNaoFiscais ;

  ZeraCache;
end;

procedure TACBrECFEpson.AbreRelatorioGerencial(Indice : Integer) ;
begin
  EpsonComando.Comando  := '0E01' ;
  EpsonComando.Extensao := '0004' ;
  EpsonComando.AddParamInteger( max(Indice,1) ) ;
  EnviaComando ;
  
  ZeraCache;
end;

procedure TACBrECFEpson.LinhaRelatorioGerencial(Linha: AnsiString; IndiceBMP: Integer);
Var I  : Integer ;
    SL : TStringList ;
begin
  Linha := AjustaLinhas( Linha, Colunas );  { Formata as Linhas de acordo com "Coluna" }

  SL := TStringList.create ;
  try
     SL.Text := Linha ;
     For I := 0 to SL.Count-1 do
     begin
        EpsonComando.Comando  := '0E02' ;
        EpsonComando.AddParamString( SL[I] ) ;
        EnviaComando ;
     end ;
  finally
     SL.Free ;
  end ;
end;

procedure TACBrECFEpson.AbreCupomVinculado(COO, CodFormaPagto,
   CodComprovanteNaoFiscal :  String; Valor : Double ) ;
Var FPG : TACBrECFFormaPagamento ;
begin
  FPG := AchaFPGIndice( CodFormaPagto ) ;

  if FPG = nil then
     raise EACBrECFERRO.create( ACBrStr('Forma de Pagamento: '+CodFormaPagto+
                             ' n�o foi cadastrada.') ) ;

  EpsonComando.Comando := '0E30' ;
  EpsonComando.AddParamString( CodFormaPagto ) ;
  EpsonComando.AddParamDouble( Valor, 2 );
  EpsonComando.AddParamInteger( 1 ) ;
  EpsonComando.AddParamString( '' ) ;
  EnviaComando ;

  ZeraCache;
end;

procedure TACBrECFEpson.LinhaCupomVinculado(Linha: AnsiString);
begin
  LinhaRelatorioGerencial( Linha );
end;

procedure TACBrECFEpson.FechaRelatorio;
begin
  if Estado = estRelatorio then
  begin
     EnviaPAF ;
     
     EpsonComando.Comando  := '0E06' ;
     EpsonComando.Extensao := '0001' ;  // Corta folha
     EnviaComando ;
  end ;

  ZeraCache;
end;

procedure TACBrECFEpson.PulaLinhas(NumLinhas: Integer);
begin
  if NumLinhas = 0 then
     NumLinhas := LinhasEntreCupons ;

  EpsonComando.Comando := '0701' ;
  EpsonComando.AddParamInteger( NumLinhas );
  EnviaComando ;
end;

procedure TACBrECFEpson.LeituraMemoriaFiscal(ReducaoInicial,
   ReducaoFinal : Integer; Simplificada : Boolean);
begin
  with EpsonComando do
  begin
     Comando  := '0910' ;
     if Simplificada then
        Extensao := '0002'
     else
        Extensao := '0000' ;

     AddParamInteger( ReducaoInicial ) ;
     AddParamInteger( ReducaoFinal ) ;
     AddParamString( '' ) ;
     AddParamString( '' ) ;
  end ;
  EnviaComando ;

  ZeraCache;
end;

procedure TACBrECFEpson.LeituraMemoriaFiscal(DataInicial,
   DataFinal: TDateTime; Simplificada : Boolean);
begin
  with EpsonComando do
  begin
     Comando  := '0910' ;
     if Simplificada then
        Extensao := '0003'
     else
        Extensao := '0001' ;

     AddParamString( '' ) ;
     AddParamString( '' ) ;
     AddParamDateTime( DataInicial ) ;
     AddParamDateTime( DataFinal ) ;
  end ;
  EnviaComando ;

  ZeraCache;
end;

procedure TACBrECFEpson.LeituraMemoriaFiscalSerial(ReducaoInicial,
   ReducaoFinal: Integer; Linhas : TStringList; Simplificada : Boolean);
  Var I : Integer ;
begin
  with EpsonComando do
  begin
     Comando  := '0910' ;
     if Simplificada then
        Extensao := '000A'
     else
        Extensao := '0008' ;

     AddParamInteger( ReducaoInicial ) ;
     AddParamInteger( ReducaoFinal ) ;
     AddParamString( '' ) ;
     AddParamString( '' ) ;
  end ;
  EnviaComando ;
  Sleep(200);

  Linhas.Clear ;
  while (EpsonResposta.Params.Count > 1) do
  begin
     For I := 0 to EpsonResposta.Params.Count-2 do
        Linhas.Add( EpsonResposta.Params[I] ) ;

     if EpsonResposta.Params[EpsonResposta.Params.Count-1] <> 'S' then
        break ;

     with EpsonComando do
     begin
        Comando  := '0910' ;
        if Simplificada then
           Extensao := '000E'
        else
           Extensao := '000C' ;

        AddParamInteger( ReducaoInicial ) ;
        AddParamInteger( ReducaoFinal ) ;
        AddParamString( '' ) ;
        AddParamString( '' ) ;
     end ;
     try
        EnviaComando ;
     except
        break ;
     end ;
  end ;
end;

procedure TACBrECFEpson.LeituraMemoriaFiscalSerial(DataInicial,
   DataFinal: TDateTime; Linhas : TStringList; Simplificada : Boolean);
  Var I : Integer ;
begin
  with EpsonComando do
  begin
     Comando  := '0910' ;
     if Simplificada then
        Extensao := '000B'
     else
        Extensao := '0009' ;
        
     AddParamString( '' ) ;
     AddParamString( '' ) ;
     AddParamDateTime( DataInicial ) ;
     AddParamDateTime( DataFinal ) ;
  end ;
  EnviaComando ;
  Sleep(200);

  Linhas.Clear ;
  while (EpsonResposta.Params.Count > 1) do
  begin
     For I := 0 to EpsonResposta.Params.Count-2 do
        Linhas.Add( EpsonResposta.Params[I] ) ;

     if EpsonResposta.Params[EpsonResposta.Params.Count-1] <> 'S' then
        break ;

     with EpsonComando do
     begin
        Comando  := '0910' ;
        if Simplificada then
           Extensao := '000F'
        else
           Extensao := '000D' ;
           
        AddParamString( '' ) ;
        AddParamString( '' ) ;
        AddParamDateTime( DataInicial ) ;
        AddParamDateTime( DataFinal ) ;
     end ;
     try
        EnviaComando ;
     except
        break ;
     end ;
  end ;
end;


procedure TACBrECFEpson.CorrigeEstadoErro(Reducao: Boolean);
begin
  inherited CorrigeEstadoErro(Reducao) ;

  if Estado <> estLivre then
  begin
     try
        EpsonComando.Comando := '0210' ;
        EnviaComando ;
        sleep(500) ;
     except
     end ;
  end ;

  ZeraCache;
end;

function TACBrECFEpson.GetCNPJ: String;
begin
  if fsCNPJ = '' then
     GetNumLoja ;

  Result := fsCNPJ ;
end;

function TACBrECFEpson.GetIE: String;
begin
  if fsIE = '' then
     GetNumLoja ;

  Result := fsIE ;
end;

function TACBrECFEpson.GetIM: String;
begin
  if fsIM = '' then
     GetNumLoja ;

  Result := fsIM ;
end;

function TACBrECFEpson.GetCliche: AnsiString;
begin
  if fsCliche = '' then
     GetNumLoja ;

  Result := fsCliche ;
end;

function TACBrECFEpson.GetUsuarioAtual: String;
begin
  if fsUsuarioAtual = '' then
     GetNumLoja ;

  Result := fsUsuarioAtual ;
end;

function TACBrECFEpson.GetDataHoraSB: TDateTime;
Var
  DtStr, HrStr : AnsiString ;
begin
  EpsonComando.Comando := '0402' ;
  EnviaComando ;

  DtStr := EpsonResposta.Params[8] ;
  HrStr := '000000' ;
  { Atualmente n�o tem informa��es de como pegar a hora por comando direto,
    tem que utilizar a mesma forma que a Bemateh realizar a partir da LMF.
    A ser implementado.... }

  Result := EncodeDateTime( StrToInt(copy(DtStr, 5,4)),   // Ano
                            StrToInt(copy(DtStr, 3,2)),   // Mes
                            StrToInt(copy(DtStr, 1,2)),   // Dia
                            StrToInt(copy(HrStr, 1,2)),   // Hora
                            StrToInt(copy(HrStr, 3,2)),   // Min
                            StrToInt(copy(HrStr, 5,2)),   // Seg
                            0 );
end;

function TACBrECFEpson.GetSubModeloECF: String;
begin
  if fsSubModeloECF = '' then
     GetRet0402(0);

  Result := fsSubModeloECF ;
end;

function TACBrECFEpson.GetDataMovimento: TDateTime;
Var
  DtStr, HrStr : AnsiString ;
begin
  EpsonComando.Comando := '080A' ;
  EnviaComando ;
  DtStr := EpsonResposta.Params[0] ;
  HrStr := EpsonResposta.Params[1] ;

  Result := EncodeDateTime( StrToInt(copy(DtStr, 5,4)),   // Ano
                            StrToInt(copy(DtStr, 3,2)),   // Mes
                            StrToInt(copy(DtStr, 1,2)),   // Dia
                            StrToInt(copy(HrStr, 1,2)),   // Hora
                            StrToInt(copy(HrStr, 3,2)),   // Min
                            StrToInt(copy(HrStr, 5,2)),   // Seg
                            0 );
end;

function TACBrECFEpson.GetGrandeTotal: Double;
begin
  EpsonResposta.Resposta := Ret0906 ;
  Result := RoundTo( StrToFloatDef(EpsonResposta.Params[0],0) /100, -2) ;
end;

function TACBrECFEpson.GetTotalAcrescimos: Double;
begin
  EpsonResposta.Resposta := Ret0906 ;
  Result := RoundTo( StrToFloatDef(EpsonResposta.Params[8],0) /100, -2) ;
end;

function TACBrECFEpson.GetTotalCancelamentos: Double;
begin
  EpsonResposta.Resposta := Ret0906 ;
  Result := RoundTo( StrToFloatDef(EpsonResposta.Params[2],0) /100, -2) ;
end;

function TACBrECFEpson.GetTotalDescontos: Double;
begin
  EpsonResposta.Resposta := Ret0906 ;
  Result := RoundTo( StrToFloatDef(EpsonResposta.Params[3],0) /100, -2) ;
end;

function TACBrECFEpson.GetTotalTroco: Double;
begin
  EpsonResposta.Resposta := Ret0906 ;
  Result := RoundTo( StrToFloatDef(EpsonResposta.Params[14],0) /100, -2) ;
end;

function TACBrECFEpson.GetTotalSubstituicaoTributaria: Double;
begin
  EpsonResposta.Resposta := Ret0906 ;
  Result := RoundTo( StrToFloatDef(EpsonResposta.Params[15],0) /100, -2) ;
end;

function TACBrECFEpson.GetTotalIsencao: Double;
begin
  EpsonResposta.Resposta := Ret0906 ;
  Result := RoundTo( StrToFloatDef(EpsonResposta.Params[16],0) /100, -2) ;
end;

function TACBrECFEpson.GetTotalNaoTributado: Double;
begin
  EpsonResposta.Resposta := Ret0906 ;
  Result := RoundTo( StrToFloatDef(EpsonResposta.Params[17],0) /100, -2) ;
end;

function TACBrECFEpson.GetVendaBruta: Double;
begin
  EpsonResposta.Resposta := Ret0906 ;
  Result := RoundTo( StrToFloatDef(EpsonResposta.Params[1],0) /100, -2) ;
end;

function TACBrECFEpson.GetNumCOOInicial: String;
begin
  EpsonComando.Comando := '080A' ;
  EnviaComando ;

  Result := EpsonResposta.Params[4] ;
end;

function TACBrECFEpson.GetNumUltimoItem: Integer;
begin
  try
    Result := RespostasComando['NumUltItem'].AsInteger;
  except
    try
       EpsonComando.Comando := '0903' ;
       EnviaComando ;

       RespostasComando.AddField( 'NumUltItem', EpsonResposta.Params[0] );
       Result := StrToIntDef( EpsonResposta.Params[0],0 ) ;
    except
       on E : Exception do
       begin
          if (pos('0102',E.Message) <> 0) then
             Result := 0
          else
             raise ;
       end ;
    end ;
  end ;
end;

procedure TACBrECFEpson.AbreNaoFiscal(CPF_CNPJ : String ; Nome : String ;
   Endereco : String) ;
begin
  if Trim(CPF_CNPJ) <> '' then
     Consumidor.AtribuiConsumidor(CPF_CNPJ,Nome,Endereco);
     
  if Consumidor.Atribuido then
  begin
     EpsonComando.Comando  := '0A20' ;
     EpsonComando.Extensao := '0001' ;
     EpsonComando.AddParamString( LeftStr(Consumidor.Documento,20) );
     EpsonComando.AddParamString( LeftStr(Consumidor.Nome,30) );
     EpsonComando.AddParamString( copy(Consumidor.Endereco, 1,40) );
     EpsonComando.AddParamString( copy(Consumidor.Endereco,41,40) );
     EnviaComando ;
  end ;

  EpsonComando.Comando := '0E01' ;
  EpsonComando.AddParamString( '' );
  EnviaComando ;

  ZeraCache;
end;

procedure TACBrECFEpson.CancelaNaoFiscal;
begin
  EpsonComando.Comando  := '0E18' ;
  EpsonComando.Extensao := '0008' ;
  EpsonComando.AddParamInteger( 1 );   // 1 Campo de entrada N�O usado no Cancelamento de cupom ;
  EnviaComando ;

  ZeraCache;
  RespostasComando.AddField( 'SubTotal', EpsonResposta.Params[0] );
  RespostasComando.AddField( 'ValorCancelado', EpsonResposta.Params[1] );
end;

procedure TACBrECFEpson.RegistraItemNaoFiscal(CodCNF: String;
  Valor: Double; Obs: AnsiString);
begin
  EpsonComando.Comando := '0E15' ;
  EpsonComando.AddParamString( CodCNF );
  EpsonComando.AddParamDouble( Valor, 2 );
  EnviaComando ;

  ZeraCache;
end;

procedure TACBrECFEpson.SubtotalizaNaoFiscal(DescontoAcrescimo: Double;
   MensagemRodape: AnsiString);
begin
  if DescontoAcrescimo = 0 then
     exit ;

  EpsonComando.Comando := '0E16' ;
  if DescontoAcrescimo < 0 then
     EpsonComando.Extensao := '0006'
  else
     EpsonComando.Extensao := '0007' ;
  EpsonComando.AddParamDouble( abs(DescontoAcrescimo), 2 );
  EnviaComando ;

  ZeraCache;
  RespostasComando.AddField( 'SubTotal', EpsonResposta.Params[0] );
end;

procedure TACBrECFEpson.EfetuaPagamentoNaoFiscal(CodFormaPagto: String;
  Valor: Double; Observacao: AnsiString; ImprimeVinculado: Boolean);
begin
  EpsonComando.Comando  := '0E1A' ;
  EpsonComando.AddParamString( CodFormaPagto ) ;
  EpsonComando.AddParamDouble( Valor, 2 ) ;
  EpsonComando.AddParamString( copy(Observacao, 1,40) ) ;
  EpsonComando.AddParamString( copy(Observacao,41,40) ) ;
  EnviaComando ;

  ZeraCache;
  RespostasComando.AddField( 'TotalAPagar', EpsonResposta.Params[0] );
  RespostasComando.AddField( 'TotalTroco', EpsonResposta.Params[1] );
end;

procedure TACBrECFEpson.FechaNaoFiscal(Observacao: ansiString; IndiceBMP : Integer);
  Var SL : TStringList ;
      I  : Integer ;
begin
  if Trim(Observacao) <> '' then
  begin
     Observacao := AjustaLinhas(Observacao,50) ;
     SL := TStringList.create ;
     try
        SL.Text := Observacao ;
        EpsonComando.Comando  := '0A22' ;
        For I := 0 to 7 do
           if I >= SL.Count then
              EpsonComando.AddParamString( '' )
           else
              EpsonComando.AddParamString( SL[I] ) ;
        EnviaComando ;
     finally
        SL.Free ;
     end ;
  end ;

  EnviaPAF ;
  
  EpsonComando.Comando  := '0E06' ;
  EpsonComando.Extensao := '0001' ;
  EnviaComando ;

  ZeraCache;
end;

procedure TACBrECFEpson.NaoFiscalCompleto(CodCNF: String; Valor: Double;
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

        try
           FechaNaoFiscal( Obs );
        except
           on E : Exception do
           begin
              if (pos('0A27',E.Message) <> 0) then   // Erro: "Mensagem promocional n�o pode ser impressa"
                 FechaNaoFiscal                      // Tenta sem Obs
              else
                 raise ;
           end ;
        end ;
     except
        try
           CancelaNaoFiscal
        except
        end;

        raise ;
     end ;
  end ;
end;

function TACBrECFEpson.GetRet0906: AnsiString;
begin
  if fsRet0906 = '' then
  begin
     EpsonComando.Comando := '0906' ;
     EnviaComando ;

     fsRet0906 := EpsonResposta.Resposta ;
  end ;

  Result := fsRet0906 ;
end;

function TACBrECFEpson.GetRet0907: AnsiString;
begin
  if fsRet0907 = '' then
  begin
     EpsonComando.Comando := '0907' ;
     EnviaComando ;

     fsRet0907 := EpsonResposta.Resposta ;
  end ;

  Result := fsRet0907 ;
end;

// Andre Bohn - Comando para cancelar impress�o do cheque
procedure TACBrECFEpson.CancelaImpressaoCheque;
begin
  if fsImprimeCheque then
  begin
    EpsonComando.Comando := 'EE12' ;
    EnviaComando ;

    ZeraCache;
  end;
end;

// Andre Bohn - Segundo suporte da epson � mais seguro imprimir o cheque com
// o cupom fechado, eu fiz um teste com o cupom aberto e tive que trocar a MFD.
// Ele disse que futuramente v�o preparar a impress�o do cheque para ter
// o mesmo funcionamento das outras ECFs.
procedure TACBrECFEpson.ImprimeCheque(Banco: String; Valor: Double;
  Favorecido, Cidade: String; Data: TDateTime; Observacao: String);
begin
  if fsImprimeCheque then
  begin
    with EpsonComando do
    begin
       Comando := 'EE10' ;
       AddParamString( LeftStr(Banco,2) ) ;
       AddParamDouble( Valor, fpDecimaisPreco ) ;
       AddParamString( LeftStr(Favorecido,40) ) ;
       AddParamString( LeftStr(Cidade,30) ) ;
       AddParamString( Observacao ) ;
       AddParamString( '' ) ;
       AddParamDateTime( Data ) ;
    end ;
    EnviaComando ;         // Envia comando para imprimir o Cheque

    EpsonComando.Comando := '0001' ;
    EnviaComando ;
    while TestBit(EpsonResposta.StatusPrinter,9) and
          (not TestBit(EpsonResposta.StatusPrinter,10)) do // Aguarda a Impress�o e retirar o cheque
    begin
      Sleep(300) ;
      EpsonComando.Comando := '0001' ;
      EnviaComando ;
      
      if (not TestBit(EpsonResposta.StatusPrinter,5)) and
         (not TestBit(EpsonResposta.StatusPrinter,6)) then
         break ;
    end ;
  end ;
end;

// Andre Bohn - Comando para fazer a leitura do CMC7
Function TACBrECFEpson.LeituraCMC7 : AnsiString;
begin
  Result :=  '';
  if fsLeituraCMC7 then
  begin
    with EpsonComando do
    begin
       Comando := '0721' ;
       AddParamInteger( 1 ) ;   // Formato '0' = E13B ou '1' - CMC7
       AddParamBool( False ) ;  // Recebe Informa��es Extendidas (S/N)
       AddParamBool( False ) ;  // Substituir caracteres n�o reconhecidos por '?' (S/N)
    end ;
    EnviaComando ;       // Envia o comando para Ler o CMC7

    EpsonComando.Comando := '0001' ;
    EnviaComando ;       // Envia comando para verificar se o cheque foi inserido

    while TestBit(EpsonResposta.StatusPrinter, 9) and TestBit(EpsonResposta.StatusPrinter, 10) do // Aguarda leitura do CMC7
    begin
      EpsonComando.Comando := '0001' ;
      EnviaComando ;

      if TestBit(EpsonResposta.StatusPrinter, 4) then
         break ;
    end ;

    EpsonComando.Comando := '0724' ;
    EnviaComando ;       // Busca o CMC7 lido na ECF

    Result := EpsonResposta.Params[0] ;

    EpsonComando.Comando := '0722' ;
    EnviaComando ;       // Ejeta Cheque
  end;
end;

function TACBrECFEpson.GetChequePronto: Boolean;
begin
  EpsonComando.Comando := '0001' ;
  EnviaComando ;         // Obtem o Estado da ECF

  Result := TestBit(EpsonResposta.StatusPrinter, 6) and
            TestBit(EpsonResposta.StatusPrinter, 5) ; // Estado do Sensor de cheque
end;

procedure TACBrECFEpson.IdentificaOperador(Nome: String);
 Var RetCmd : AnsiString ;
begin
  // Lendo Operador atual //
  try
     EpsonComando.Comando  := '0551' ;
     Enviacomando ;
     RetCmd := UpperCase(Trim(EpsonResposta.Params[0])) ;
  except
     RetCmd := '' ;
  end ;

  if UpperCase(Trim(Nome)) <> RetCmd then   // Operador � diferente ? //
  begin
     try
        EpsonComando.Comando  := '0550' ;
        EpsonComando.AddParamString( LeftStr(Nome,20) );
        EnviaComando ;

        RetCmd := '' ;
        repeat
           Sleep(100) ;
           try
              RetCmd := NumCupom ;
           except
           end ;
        until RetCmd <> '' ;
     except
     end ;
  end ;
end;

procedure TACBrECFEpson.CortaPapel(const CorteParcial: Boolean);
begin
  EpsonComando.Comando  := '0702' ;
  EnviaComando ;
end;

procedure TACBrECFEpson.Suprimento(const Valor : Double ; Obs : AnsiString ;
  DescricaoCNF : String ; DescricaoFPG : String; IndiceBMP: Integer) ;
begin
  if UpperCase(Trim(DescricaoCNF)) = 'SUPRIMENTO' then
     DescricaoCNF := 'FUNDO DE TROCO' ;

  inherited Suprimento(Valor, Obs, DescricaoCNF, DescricaoFPG, IndiceBMP);
end;

function TACBrECFEpson.EstornaCCD(const Todos : Boolean) : Integer ;
var
   COOCDC, COOCupom : Integer ;
begin
  Result := 0;

  if TipoUltimoDocumento <> docCCD then
     exit ;

  // Pega o nro do CDC a cancelar
  EpsonComando.Comando := '0907';
  EnviaComando;
  COOCDC   := StrToInt( Trim(EpsonResposta.Params[0] ) ) ;
  COOCupom := StrToInt( Trim(EpsonResposta.Params[12]) ) ;

  while COOCDC > COOCupom do
  begin
     // Estorna o CDC para poder cancelar o cupom
     EpsonComando.Comando  := '0E30';
     EpsonComando.Extensao := '0001';
     { Passando apenas o numero do COO, os parametros iniciais n�o s�o
       necess�riospara efetuar o cancelameto de CDC }
     EpsonComando.AddParamString( '' );
     EpsonComando.AddParamString( '' );
     EpsonComando.AddParamString( '' );
     EpsonComando.AddParamInteger( COOCDC );
     EnviaComando;
     FechaRelatorio;   { Fecha o estorno do CDC }

     Inc( Result ) ;
     Dec( COOCDC ) ;

     if not Todos then
        Break;
  end ;
end ;

function TACBrECFEpson.GetPAF: String;
begin
  Result := padL(fsPAF1,42)+'|'+padL(fsPAF2,42) ; 
end;

procedure TACBrECFEpson.IdentificaPAF(NomeVersao, MD5 : String);
begin
  fsPAF1 := LeftStr( MD5, 42) ;
  fsPAF2 := LeftStr( NomeVersao, 42) ;
end;

procedure TACBrECFEpson.EnviaPAF ;
begin
  if fsPAF1+fsPAF2 <> '' then
  begin
     try
        EpsonComando.Comando  := '0A23' ;
        EpsonComando.AddParamString( fsPAF1 );
        EpsonComando.AddParamString( fsPAF2 );
        EnviaComando ;
     except
     end ;
  end ;
end ;

function TACBrECFEpson.GetDadosUltimaReducaoZ: AnsiString;
var
  Aliq, AliqZ : TACBrECFAliquota ;
  ECFCRZ, DtStr, HrStr : String ;
  J : Integer ;
  DataMov, DataFechaZ : TDateTime;
  ECFEst: TACBrECFEstado;
begin
//Esta fun��o utiliza o comando "Obter Totais da Jornada (09 0D)", que aceita
//como par�metro um CRZ. Ent�o para obter os totais da �ltima redu��o, passamos o
//CRZ atual, que guarda o n�mero da �ltima redu��o Z. Os campos retornados pelo
//comando est�o abaixo:
//Campo                      Tipo Tamanho
//1-N�mero da Redu��o Z      N    4
//2-N�mero do COO            N    6
//3-CRO                      N    6
//4-Venda Bruta Di�ria       N    14
//5-Total F                  N    13
//6-Total I                  N    13
//7-Total N                  N    13
//8-Total FS                 N    13
//9-Total IS                 N    13
//10-Total NS                N    13
//11-Total Canc. ICMS        N    13
//12-Total Canc. ISS         N    13
//13-Total Canc. NF          N    13
//14-Total Desc. ICMS        N    13
//15-Total Desc. ISS         N    13
//16-Total Desc. NF          N    13
//17-Total Acre. ICMS        N    13
//18-Total Acre. ISS         N    13
//19-Total Acre. NF          N    13
//20-Total ICMS              N    13
//21-Total ISS               N    13
//22-Total NF                N    13
//23-Data de Fechamento RZ  (D)  8
//24-Hora de Fechamento RZ  (H)  6
//25-Totalizador Geral       N   17
//n+25-Percentual do Totalizador parcial  N  4
//n+26-Total vendido         N  13

  // Zerar variaveis e inicializa Dados do ECF //
  InitDadosUltimaReducaoZ;

  if not Assigned( fpAliquotas ) then
    CarregaAliquotas ;

  with TACBrECF(fpOwner) do
  begin
    DataMov := DataMovimento;
    ECFEst  := Estado;
    ECFCRZ  := NumCRZ;
  end;

  EpsonComando.Comando := '090D';
  EpsonComando.Params.Clear;
  EpsonComando.AddParamString( ECFCRZ );
  EnviaComando;

  DtStr := EpsonResposta.Params[22] ;
  HrStr := EpsonResposta.Params[23] ;
  DataFechaZ := EncodeDateTime( StrToInt(copy(DtStr, 5,4)),   // Ano
                                StrToInt(copy(DtStr, 3,2)),   // Mes
                                StrToInt(copy(DtStr, 1,2)),   // Dia
                                StrToInt(copy(HrStr, 1,2)),   // Hora
                                StrToInt(copy(HrStr, 3,2)),   // Min
                                StrToInt(copy(HrStr, 5,2)),   // Seg
                                0 );
  { Epson n�o retorna a Data do Movimento e SIM a Data de Fechamento da Z
    tentando descobir se o Fechamento ocorreu no dia correto do movimento }
  if (not (ECFEst in [estBloqueada, estRequerX])) and  // Ainda n�o abriu a Jornada
     (DataFechaZ < DataMov) then
  begin
    if DateOf( DataFechaZ ) = DateOf( DataMov ) then
      DataMov := EndOfTheDay( IncDay( DateOf( DataFechaZ ), - 1) )
    else
      DataMov := DataFechaZ;
  end;

  { Alimenta a class com os dados atuais do ECF }
  with fpDadosReducaoZClass do
  begin
    DataDoMovimento := DataMov;

    CRZ := StrToIntDef( EpsonResposta.Params[0], 0) ;
    COO := StrToIntDef( EpsonResposta.Params[1], 0) ;
    CRO := StrToIntDef( EpsonResposta.Params[2], 0) ;

    ValorVendaBruta  := RoundTo( StrToFloatDef(EpsonResposta.Params[3],0)/100, -2);

    SubstituicaoTributariaICMS := RoundTo( StrToFloatDef(EpsonResposta.Params[4],0)/100, -2);
    IsentoICMS := RoundTo( StrToFloatDef(EpsonResposta.Params[5],0)/100, -2);
    NaoTributadoICMS := RoundTo( StrToFloatDef(EpsonResposta.Params[6],0)/100, -2);

    SubstituicaoTributariaISSQN := RoundTo( StrToFloatDef(EpsonResposta.Params[7],0)/100, -2);
    IsentoISSQN := RoundTo( StrToFloatDef(EpsonResposta.Params[8],0)/100, -2);
    NaoTributadoISSQN := RoundTo( StrToFloatDef(EpsonResposta.Params[9],0)/100, -2);

    CancelamentoICMS := RoundTo( StrToFloatDef(EpsonResposta.Params[10],0)/100, -2);
    CancelamentoISSQN := RoundTo( StrToFloatDef(EpsonResposta.Params[11],0)/100, -2);
    CancelamentoOPNF := RoundTo( StrToFloatDef(EpsonResposta.Params[12],0)/100, -2);

    DescontoICMS := RoundTo( StrToFloatDef(EpsonResposta.Params[13],0)/100, -2);
    DescontoISSQN := RoundTo( StrToFloatDef(EpsonResposta.Params[14],0)/100, -2);
    DescontoOPNF := RoundTo( StrToFloatDef(EpsonResposta.Params[15],0)/100, -2);

    AcrescimoICMS := RoundTo( StrToFloatDef(EpsonResposta.Params[16],0)/100, -2);
    AcrescimoISSQN := RoundTo( StrToFloatDef(EpsonResposta.Params[17],0)/100, -2);
    AcrescimoOPNF := RoundTo( StrToFloatDef(EpsonResposta.Params[18],0)/100, -2);

    // A informa��o abaixo traz o Total da Base de Calculo e n�o o total por Tipo de aliquota //
    //TotalICMS := RoundTo( StrToFloatDef(EpsonResposta.Params[19],0)/100, -2);
    //TotalISSQN := RoundTo( StrToFloatDef(EpsonResposta.Params[20],0)/100, -2);
    TotalOperacaoNaoFiscal := RoundTo( StrToFloatDef(EpsonResposta.Params[21],0)/100, -2);
    ValorGrandeTotal := RoundTo( StrToFloatDef(EpsonResposta.Params[24],0)/100, -2);

    { Copiando objetos de ICMS }
    J := 25;
    while J < EpsonResposta.Params.Count do
    begin
      Aliq := AchaICMSAliquota( RoundTo( StrToFloatDef(EpsonResposta.Params[ J ],0)/100, -2), 'T' );

      if Aliq <> Nil then
      begin
        AliqZ := TACBrECFAliquota.Create ;
        AliqZ.Assign( Aliq );
        AliqZ.Total := RoundTo( StrToFloatDef(EpsonResposta.Params[ J+1 ],0)/100, -2) ;
        AdicionaAliquota( AliqZ );
      end ;

      J := J + 2 ;
    end;

    // Lendo dados do ISSQN //
    EpsonComando.Comando := '090D';
    EpsonComando.Extensao:= '0001';
    EpsonComando.Params.Clear;
    EpsonComando.AddParamString( ECFCRZ );
    EnviaComando;

    { Copiando objetos de ISSQN }
    J := 25;
    while J < EpsonResposta.Params.Count do
    begin
      Aliq := AchaICMSAliquota( RoundTo( StrToFloatDef(EpsonResposta.Params[ J ],0)/100, -2), 'S' );

      if Aliq <> Nil then
      begin
        AliqZ := TACBrECFAliquota.Create ;
        AliqZ.Assign( Aliq );
        AliqZ.Total := RoundTo( StrToFloatDef(EpsonResposta.Params[ J+1 ],0)/100, -2) ;
        AdicionaAliquota( AliqZ );
      end ;

      J := J + 2 ;
    end;

    CalculaValoresVirtuais;
    Result := MontaDadosReducaoZ;
  end;
end;

procedure TACBrECFEpson.ZeraCache( ZeraRespostaComando: Boolean ) ;
begin
  if ZeraRespostaComando then
     RespostasComando.Clear;

  fsRet0906 := '';
  fsRet0907 := '';
end ;

procedure TACBrECFEpson.LoadDLLFunctions ;
 procedure EpsonFunctionDetect( FuncName: String; var LibPointer: Pointer ) ;
 var
 sLibName: string;
 begin
   if not Assigned( LibPointer )  then
   begin
     // Verifica se exite o caminho das DLLs
     if Length(PathDLL) > 0 then
        sLibName := PathWithDelim(PathDLL);

     // Concatena o caminho se exitir mais o nome da DLL.
     sLibName := sLibName + cLIB_Epson;

     if not FunctionDetect( sLibName, FuncName, LibPointer) then
     begin
        LibPointer := NIL ;
        raise EACBrECFERRO.Create( ACBrStr( 'Erro ao carregar a fun��o:'+FuncName+' de: '+cLIB_Epson ) ) ;
     end ;
   end ;
 end ;
begin
   EpsonFunctionDetect('EPSON_Obter_Dados_MF_MFD', @xEPSON_Obter_Dados_MF_MFD);
   EpsonFunctionDetect('EPSON_Serial_Abrir_Porta', @xEPSON_Serial_Abrir_Porta);
   EpsonFunctionDetect('EPSON_Serial_Fechar_Porta', @xEPSON_Serial_Fechar_Porta);
   EpsonFunctionDetect('EPSON_Serial_Obter_Estado_Com', @xEPSON_Serial_Obter_Estado_Com);
   EpsonFunctionDetect('EPSON_Send_From_FileEXX', @xEPSON_Send_From_FileEXX);
end ;

procedure TACBrECFEpson.AbrePortaSerialDLL ;
Var
  Porta, Resp : Integer ;
begin
  if fsUsarDLL and Ativo then exit;

  Porta := StrToIntDef( OnlyNumber( fpDevice.Porta ), 0) ;

  GravaLog( '   Desativando ACBrECF' );
  if not fsUsarDLL then
     Ativo := False ;

  GravaLog( '   xEPSON_Serial_Abrir_Porta' );
  Resp := xEPSON_Serial_Abrir_Porta( fpDevice.Baud, Porta ) ;
  if Resp <> 0 then
     raise EACBrECFERRO.Create( ACBrStr('Erro: '+IntToStr(Resp)+' ao abrir a Porta com:'+sLineBreak+
        'EPSON_Serial_Abrir_Porta('+IntToStr(fpDevice.Baud)+', '+IntToStr(Porta)+')' ));
end ;

procedure TACBrECFEpson.FechaPortaSerialDLL(const OldAtivo : Boolean) ;
var
  Resp : Integer ;
begin
  if fsUsarDLL and OldAtivo then exit;

  GravaLog( '   xEPSON_Serial_Fechar_Porta' ) ;
  Resp := xEPSON_Serial_Fechar_Porta ;
  if Resp <> 0 then
     raise EACBrECFERRO.Create( ACBrStr('Erro: '+IntToStr(Resp)+' ao Fechar a Porta com:'+sLineBreak+
        'EPSON_Serial_Fechar_Porta' ));

  GravaLog( '   Ativar ACBr: '+ifthen(OldAtivo,'SIM','NAO') ) ;

  if OldAtivo then
  begin
     while (Resp < 5) and (not Ativo) do
     begin
        Inc( Resp ) ;

        try
          GravaLog('   - Tentativa: '+IntToStr(Resp));
          Ativar;
        except
          GravaLog( '     Falha... Aguardando '+IntToStr(Resp)+' segundo(s)' );
          Sleep(Resp * 1000);
        end ;
     end ;

     if not Ativo then
        Ativar;
  end ;
end ;

procedure TACBrECFEpson.EspelhoMFD_DLL(DataInicial,
  DataFinal: TDateTime; NomeArquivo: AnsiString;
  Documentos: TACBrECFTipoDocumentoSet);
Var
  Resp : Integer ;
  ArqTmp, DiaIni, DiaFim : AnsiString ;
  OldAtivo : Boolean ;
begin
  LoadDLLFunctions ;

  ArqTmp := ExtractFilePath( NomeArquivo ) + 'ACBr' ;
  DeleteFiles( ArqTmp + '_???.txt' ) ;

  OldAtivo := Ativo ;
  try
    AbrePortaSerialDLL ;

    DiaIni := FormatDateTime('ddmmyyyy',DataInicial) ;
    DiaFim := FormatDateTime('ddmmyyyy',DataFinal) ;

    GravaLog( '   xEPSON_Obter_Dados_MF_MFD' );
    Resp := xEPSON_Obter_Dados_MF_MFD(  DiaIni, DiaFim,
                                        0,                // 0 = Faixa em Datas
                                        DocumentosToNum(Documentos),
                                        0,                // 0 = N�o Gera Ato Cotepe
                                        0,                // 0 = Nao Gera Sintegra
                                        ArqTmp );
    if (Resp <> 0) then
      raise EACBrECFERRO.Create( ACBrStr( 'Erro ao executar EPSON_Obter_Dados_MF_MFD.'+sLineBreak+
                                       'Cod.: '+IntToStr(Resp) ))
  finally
    FechaPortaSerialDLL(OldAtivo) ;
  end ;

  if FileExists( ArqTmp + '_ESP.txt' ) then
   begin
     if not CopyFileTo( ArqTmp + '_ESP.txt', NomeArquivo ) then
        raise EACBrECFERRO.Create( ACBrStr( 'Erro ao copiar: '+sLineBreak+
                                ArqTmp + '_ESP.txt'+sLineBreak+
                                'para'+sLineBreak+NomeArquivo ))
   end
  else
     raise EACBrECFERRO.Create( ACBrStr( 'Erro na execu��o de EPSON_Obter_Dados_MF_MFD.'+sLineBreak+
                            'Arquivo: '+ArqTmp + '_ESP.txt n�o gerado' ))
end;

procedure TACBrECFEpson.EspelhoMFD_DLL(COOInicial, COOFinal: Integer;
  NomeArquivo: AnsiString; Documentos: TACBrECFTipoDocumentoSet);
Var
  Resp : Integer ;
  ArqTmp, CooIni, CooFim : AnsiString ;
  OldAtivo : Boolean ;
begin
  LoadDLLFunctions ;

  ArqTmp := ExtractFilePath( NomeArquivo ) + 'ACBr' ;
  DeleteFiles( ArqTmp + '_???.txt' ) ;

  OldAtivo := Ativo ;
  try
    AbrePortaSerialDLL ;

    CooIni := IntToStrZero( COOInicial, 6 ) ;
    CooFim := IntToStrZero( COOFinal, 6 ) ;

    GravaLog( '   xEPSON_Obter_Dados_MF_MFD' );
    Resp := xEPSON_Obter_Dados_MF_MFD(  COOIni, CooFim,
                                        2,                // 2 = Faixa em COO
                                        DocumentosToNum(Documentos),
                                        0,                // 0 = N�o Gera Ato Cotepe
                                        0,                // 0 = Nao Gera Sintegra
                                        ArqTmp );
    if (Resp <> 0) then
      raise EACBrECFERRO.Create( ACBrStr( 'Erro ao executar EPSON_Obter_Dados_MF_MFD.'+sLineBreak+
                                       'Cod.: '+IntToStr(Resp) ))
  finally
    FechaPortaSerialDLL(OldAtivo);
  end ;

  if FileExists( ArqTmp + '_ESP.txt' ) then
   begin
     if not CopyFileTo( ArqTmp + '_ESP.txt', NomeArquivo ) then
        raise EACBrECFERRO.Create( ACBrStr( 'Erro ao copiar: '+sLineBreak+
                                ArqTmp + '_ESP.txt'+sLineBreak+
                                'para'+sLineBreak+NomeArquivo ))
   end
  else
     raise EACBrECFERRO.Create( ACBrStr( 'Erro na execu��o de EPSON_Obter_Dados_MF_MFD.'+sLineBreak+
                            'Arquivo: "'+ArqTmp + '_ESP.txt" n�o gerado' ))
end;

procedure TACBrECFEpson.ArquivoMFD_DLL(DataInicial, DataFinal: TDateTime;
  NomeArquivo: AnsiString; Documentos: TACBrECFTipoDocumentoSet;
  Finalidade: TACBrECFFinalizaArqMFD);
Var
  Resp, Tipo : Integer ;
  ArqTmp, DiaIni, DiaFim : AnsiString ;
  OldAtivo : Boolean ;
begin
  LoadDLLFunctions ;

  ArqTmp := ExtractFilePath( NomeArquivo ) + 'ACBr' ;
  DeleteFiles( ArqTmp + '_???.txt' ) ;

  case Finalidade of
     finMF  : Tipo := 1;
     finMFD : Tipo := 2;
  else
     Tipo := 3;
  end ;

  OldAtivo := Ativo ;
  try
    AbrePortaSerialDLL ;

    DiaIni := FormatDateTime('ddmmyyyy',DataInicial) ;
    DiaFim := FormatDateTime('ddmmyyyy',DataFinal) ;

    GravaLog( '   xEPSON_Obter_Dados_MF_MFD' );
    Resp := xEPSON_Obter_Dados_MF_MFD(  DiaIni, DiaFim,
                                        0,                // 0 = Faixa em Datas
                                        0,                // 0 = Sem Espelhos
                                        Tipo,
                                        0,                // 0 = Nao Gera Sintegra
                                        ArqTmp );
    if (Resp <> 0) then
      raise EACBrECFERRO.Create( ACBrStr( 'Erro ao executar EPSON_Obter_Dados_MF_MFD.'+sLineBreak+
                                       'Cod.: '+IntToStr(Resp) ))
  finally
    FechaPortaSerialDLL(OldAtivo);
  end ;

  if FileExists( ArqTmp + '_CTP.txt' ) then
   begin
     if not CopyFileTo( ArqTmp + '_CTP.txt', NomeArquivo ) then
        raise EACBrECFERRO.Create( ACBrStr( 'Erro ao copiar: '+sLineBreak+
                                ArqTmp + '_CTP.txt'+sLineBreak+
                                'para'+sLineBreak+NomeArquivo ))
   end
  else
     raise EACBrECFERRO.Create( ACBrStr( 'Erro na execu��o de EPSON_Obter_Dados_MF_MFD.'+sLineBreak+
                            'Arquivo: '+ArqTmp + '_CTP.txt n�o gerado' ))
end;

procedure TACBrECFEpson.ArquivoMFD_DLL(ContInicial, ContFinal: Integer;
  NomeArquivo: AnsiString; Documentos: TACBrECFTipoDocumentoSet;
  Finalidade: TACBrECFFinalizaArqMFD;
  TipoContador: TACBrECFTipoContador);
Var
  Resp, Tipo : Integer ;
  ArqTmp, CooIni, CooFim : AnsiString ;
  OldAtivo : Boolean ;
begin

  // Extraido do Manual da Epson:
  // Leituras somente podem ser realizadas por faixa de CRZ ou Data de Movimento
  if TipoContador = tpcCOO then
     raise EACBrECFERRO.Create( ACBrStr(cACBrECFPAFFuncaoNaoSuportada) ) ;

  LoadDLLFunctions ;

  case Finalidade of
     finMF  : Tipo := 1;
     finMFD : Tipo := 2;
  else
     Tipo := 3;
  end ;

  ArqTmp := ExtractFilePath( NomeArquivo ) + 'ACBr' ;
  DeleteFiles( ArqTmp + '_???.txt' ) ;

  OldAtivo := Ativo ;
  try
    AbrePortaSerialDLL ;

    CooIni := IntToStrZero( ContInicial, IfThen( TipoContador = tpcCOO, 6, 4) ) ;
    CooFim := IntToStrZero( ContFinal, IfThen( TipoContador = tpcCOO, 6, 4) ) ;

    GravaLog( '   xEPSON_Obter_Dados_MF_MFD' );
    Resp := xEPSON_Obter_Dados_MF_MFD(  COOIni, CooFim,
                                        IfThen( TipoContador = tpcCOO, 2, 1),
                                        0,                // 0 = Sem Espelhos
                                        Tipo,
                                        0,                // 0 = Nao Gera Sintegra
                                        ArqTmp );
    if (Resp <> 0) then
      raise EACBrECFERRO.Create( ACBrStr( 'Erro ao executar EPSON_Obter_Dados_MF_MFD.'+sLineBreak+
                                       'Cod.: '+IntToStr(Resp) ))
  finally
    FechaPortaSerialDLL(OldAtivo) ;
  end ;

  if FileExists( ArqTmp + '_CTP.txt' ) then
   begin
     if not CopyFileTo( ArqTmp + '_CTP.txt', NomeArquivo ) then
        raise EACBrECFERRO.Create( ACBrStr( 'Erro ao copiar: '+sLineBreak+
                                ArqTmp + '_CTP.txt'+sLineBreak+
                                'para'+sLineBreak+NomeArquivo ))
   end
  else
     raise EACBrECFERRO.Create( ACBrStr( 'Erro na execu��o de EPSON_Obter_Dados_MF_MFD.'+sLineBreak+
                            'Arquivo: "'+ArqTmp + '_CTP.txt" n�o gerado' ))
end;


function TACBrECFEpson.DocumentosToNum(
  Documentos: TACBrECFTipoDocumentoSet): Integer;
begin
  Result := 65535 ;
  if (Documentos - [docTodos]) = [] then exit ;

  Result := 0 ;

  if docCF  in Documentos then Result := Result + 1 ;
  if docRZ  in Documentos then Result := Result + 2 ;
  if docLMF in Documentos then Result := Result + 4 ;
  if docLX  in Documentos then Result := Result + 8 ;
  if docRG  in Documentos then Result := Result + 16 ;
  if docCCD in Documentos then Result := Result + 32 ;
  if docCNF in Documentos then Result := Result + 64 ;
  if docCNFCancelamento in Documentos then Result := Result + 128 ;

end;

function TACBrECFEpson.GetTotalAcrescimosISSQN: Double;
begin
  EpsonResposta.Resposta := Ret0906 ;
  Result := RoundTo( StrToFloatDef(EpsonResposta.Params[9],0) /100, -2) ;
end;

function TACBrECFEpson.GetTotalCancelamentosISSQN: Double;
begin
  EpsonResposta.Resposta := Ret0906 ;
  Result := RoundTo( StrToFloatDef(EpsonResposta.Params[5],0) /100, -2) ;
end;

function TACBrECFEpson.GetTotalDescontosISSQN: Double;
begin
  EpsonResposta.Resposta := Ret0906 ;
  Result := RoundTo( StrToFloatDef(EpsonResposta.Params[6],0) /100, -2) ;
end;

function TACBrECFEpson.GetTotalIsencaoISSQN: Double;
begin
  EpsonResposta.Resposta := Ret0906 ;
  Result := RoundTo( StrToFloatDef(EpsonResposta.Params[19],0) /100, -2) ;
end;

function TACBrECFEpson.GetTotalNaoTributadoISSQN: Double;
begin
  EpsonResposta.Resposta := Ret0906 ;
  Result := RoundTo( StrToFloatDef(EpsonResposta.Params[20],0) /100, -2) ;
end;

function TACBrECFEpson.GetTotalAcrescimosOPNF : Double ;
begin
  EpsonResposta.Resposta := Ret0906 ;
  Result := RoundTo( StrToFloatDef(EpsonResposta.Params[13],0) /100, -2) ;
end ;

function TACBrECFEpson.GetTotalCancelamentosOPNF : Double ;
begin
  EpsonResposta.Resposta := Ret0906 ;
  Result := RoundTo( StrToFloatDef(EpsonResposta.Params[11],0) /100, -2) ;
end ;

function TACBrECFEpson.GetTotalDescontosOPNF : Double ;
begin
  EpsonResposta.Resposta := Ret0906 ;
  Result := RoundTo( StrToFloatDef(EpsonResposta.Params[12],0) /100, -2) ;
end ;

function TACBrECFEpson.GetTotalSubstituicaoTributariaISSQN: Double;
begin
  EpsonResposta.Resposta := Ret0906 ;
  Result := RoundTo( StrToFloatDef(EpsonResposta.Params[18],0) /100, -2) ;
end;

procedure TACBrECFEpson.CarregaRelatoriosGerenciais;  
var
  i, QtdeRG: integer ;
  RG: TACBrECFRelatorioGerencial;
begin
  inherited CarregaRelatoriosGerenciais ;   {Inicializa fpRelatoriosGerenciais}

  try
     //Consulta a qtde de RG cadastrados
     EpsonComando.Comando  :='0901';
     EpsonComando.Extensao :='0002';
     EnviaComando;

     QtdeRG := StrToIntDef(EpsonResposta.Params[0],0);

     //Consultas informa��es de cada relatorio individualmente e
     //adiciona em fpRelatoriosGerenciais;
     for i := 1 to QtdeRG do
     begin
        EpsonComando.Comando  :='0902';
        EpsonComando.Extensao :='0002';
        EpsonComando.AddParamInteger( i );
        EnviaComando;

        RG := TACBrECFRelatorioGerencial.Create;
        RG.Indice    := IntToStr(i);
        RG.Descricao := EpsonResposta.Params[0];
        RG.Contador  := StrToIntDef(EpsonResposta.Params[1],0);
        fpRelatoriosGerenciais.Add(RG);
     end;
  except
     { Se falhou ao carregar, deve "nilzar" as variaveis para que as rotinas
       "Acha*" tentem carregar novamente }
     fpRelatoriosGerenciais.Free;
     fpRelatoriosGerenciais := nil;

     raise ;
  end ;
end;

procedure TACBrECFEpson.LerTotaisRelatoriosGerenciais ;
begin
  CarregaRelatoriosGerenciais;
end ;

procedure TACBrECFEpson.ProgramaRelatorioGerencial( var Descricao: String; Posicao: String);
begin
  CarregaRelatoriosGerenciais;
  Descricao := Copy(Trim(Descricao),1,15);

  if AchaRGDescricao(Descricao, True)<>nil then
     raise EACBrECFERRO.Create(ACBrStr('Relat�rio Gerencial ('+Descricao+') j� existe.')) ;

  EpsonComando.Comando  := '0570';
  EpsonComando.Extensao := '0000';
  EpsonComando.AddParamString( PadL(Descricao,15) );
  EnviaComando;
  
  CarregaRelatoriosGerenciais;
end;


function TACBrECFEpson.AchaCNFDescricao( Descricao: String;
       BuscaExata : Boolean; IgnorarCase : Boolean  ): 
  TACBrECFComprovanteNaoFiscal;
begin
  if UpperCase(Trim(Descricao)) = 'SUPRIMENTO' then
     Descricao := 'FUNDO DE TROCO' ;

  Result := inherited AchaCNFDescricao( Descricao, BuscaExata, IgnorarCase );
end;

function TACBrECFEpson.TraduzirTag(const ATag : AnsiString) : AnsiString ;
const
  C_OFF = 0;
  // <e>
  cExpandidoOn = 4;
  // <n>
  cNegritoOn = 1;
  // <s></s>
  cSublinhadoOn = 2;

  cBarras = ESC + #128 ;
  // ESC + #128 + T + H + W + HRIp + HRIl + EEEEEEEEEEEEE..EE
  // --------
  // ESC + #128 = Comando para impress�o das barras
  // T = 1 byte Tipo de codigo conforme tabela abaixo
  // H = 1 byte. Altura do c�digo de 0 a 255. 0 = Default
  // W = 1 byte. Largura do c�digo de 2 a 6. 0 = Default
  // HRIp = 1 byte. Posi�ao de Impressao do Texto. 0-Nao imprime, 1-Acima, 2-Abaixo, 3-Ambos
  // HRIl = 1 byte. Letra da Impressao do Texto. 0-Letra A, 1-Letra B
  // E = Codigo de barra

  cEAN8     = 68 ; // <ean8></ean8>
  cEAN13    = 67 ; // <ean13></ean13>
  cINTER    = 70 ; // <inter></inter>
  cCODE39   = 69; // <code39></code39>
  cCODE93   = 72; // <code93></code93>
  cCODE128  = 73; // <code128></code128>
  cUPCA     = 65; // <upca></upca>
  cCODABAR  = 71; // <codabar></codabar>
  cBarraFim = '';

  function ConfigurarBarras(const ACodigo: Integer): AnsiString;
  Var
    Altura, Largura, Mostrar : Integer ;
  begin
    Altura  := max(min(ConfigBarras.Altura,255),0);
    if (Altura = 0) and fpDevice.IsDLLPort then
       Altura := 32;
    Largura := max(min(ConfigBarras.LarguraLinha,6),2);
    Mostrar := IfThen(fpDevice.IsDLLPort,2,0);
    if ConfigBarras.MostrarCodigo then
       Mostrar := 2;

    Result := cBarras + chr(ACodigo) + chr( Altura ) + chr( Largura ) +
              chr( Mostrar ) + #48;
  end;
var
  TagNum, Cmd : Integer ;
begin

  TagNum := AnsiIndexText( ATag, ARRAY_TAGS) ;

  case TagNum of
     -1: Result := ATag;
     2 : fsALExpandido := True ;
     3 : fsALExpandido := False ;
     4 : fsALNegrito   := True ;
     5 : fsALNegrito   := False ;
     6 : fsALSublinhado:= True ;
     7 : fsALSublinhado:= False ;
     12: Result := ConfigurarBarras(cEAN8);
     13: Result := cBarraFim;
     14: Result := ConfigurarBarras(cEAN13);
     15: Result := cBarraFim;
     18: Result := ConfigurarBarras(cINTER);
     19: Result := cBarraFim;
     22: Result := ConfigurarBarras(cCODE39);
     23: Result := cBarraFim;
     24: Result := ConfigurarBarras(cCODE93);
     25: Result := cBarraFim;
     26: Result := ConfigurarBarras(cCODE128);
     27: Result := cBarraFim;
     28: Result := ConfigurarBarras(cUPCA);
     29: Result := cBarraFim;
     30: Result := ConfigurarBarras(cCODABAR);
     31: Result := cBarraFim;
  else
     Result := '' ;
  end;

  if (TagNum > 1) and (TagNum < 8) then
  begin
     Cmd := C_OFF ;

     if fsALNegrito then
        Cmd := Cmd + cNegritoOn;

     if fsALExpandido then
        Cmd := Cmd + cExpandidoOn;

     if fsALSublinhado then
        Cmd := Cmd + cSublinhadoOn;

     if fpDevice.IsDLLPort and (Cmd = C_OFF) then
        Cmd := 32 ;

     Result := ESC + chr( Cmd );
  end ;
end ;

end.



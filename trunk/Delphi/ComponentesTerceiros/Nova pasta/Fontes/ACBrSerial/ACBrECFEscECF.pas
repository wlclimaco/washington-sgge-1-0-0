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
{ http://www.opensource.org/licenses/gpl-license.php                           }
{                                                                              }
{ Daniel Sim�es de Almeida  -  daniel@djsystem.com.br  -  www.djsystem.com.br  }
{              Pra�a Anita Costa, 34 - Tatu� - SP - 18270-410                  }
{                                                                              }
{******************************************************************************}

{******************************************************************************
|* Historico
|*
|* 20/10/2012:  Daniel Simoes de Almeida
|*   Primeira Versao: Cria�ao e Distribui�ao da Primeira Versao
******************************************************************************}

{$I ACBr.inc}

unit ACBrECFEscECF ;

interface
uses ACBrECFClass, ACBrUtil, ACBrDevice, ACBrConsts,
     Classes ;

const
    cEscECFMaxBuffer = 4096 ;

type

{ TACBrECFEscECFRET }

TACBrECFEscECFRET = class
private
   fsECF: Byte;
   fsFabricante: Byte;
   fsFisco: Byte;
   fsRET: AnsiString;
   fsSPR: Byte;
   procedure SetRET(AValue: AnsiString);
 public
    constructor Create;
    property ECF        : Byte read fsECF ;
    property Fisco      : Byte read fsFisco ;
    property SPR        : Byte read fsSPR ;
    property Fabricante : Byte read fsFabricante ;

    property RET : AnsiString read fsRET write SetRET ;

    procedure Clear;
end;


{ TACBrECFEscECFComando }

TACBrECFEscECFComando = class
  private
    fsCMD     : Byte ;
    fsEXT     : Byte ;
    fsSEQ     : Byte ;
    fsParams  : TStringList ;
    fsTimeOut : Integer;

    function GetComando: AnsiString;
    procedure SetCMD(const Value: Byte);
 public
    constructor Create ;
    destructor Destroy ; override ;

    property CMD : Byte read fsCMD write SetCMD ;
    property EXT : Byte read fsEXT write fsEXT ;
    property SEQ : Byte read fsSEQ write fsSEQ ;
    property TimeOut : Integer read fsTimeOut write fsTimeOut ;

    property Comando : AnsiString  read GetComando ;
    property Params  : TStringList read fsParams ;

    Procedure AddParamString(AString : AnsiString) ;
    Procedure AddParamInteger(AInteger : Integer) ;
    Procedure AddParamDouble(ADouble : Double; Decimais: Byte = 2) ;
    Procedure AddParamDateTime( ADateTime: TDateTime; Tipo : Char = 'D';
                                FlagHV : String = '' ) ;
 end ;

{ TACBrECFEscECFResposta }

TACBrECFEscECFResposta = class
  private
    fsResposta : AnsiString ;
    fsParams   : TStringList ;
    fsRET      : TACBrECFEscECFRET;
    fsSEQ      : Byte ;
    fsCMD      : Byte ;
    fsEXT      : Byte ;
    fsCAT      : Byte ;
    fsTBR      : Integer ;
    fsBRS      : AnsiString ;
    fsCHK      : Byte ;

    procedure SetResposta(const Value: AnsiString);
 public
    constructor create ;
    destructor Destroy ; override ;

    procedure Clear( ClearParams: Boolean = True ) ;

    property Resposta : AnsiString  read fsResposta write SetResposta ;
    property Params   : TStringList read fsParams ;
    property SEQ      : Byte        read fsSEQ ;
    property CMD      : Byte        read fsCMD ;
    property EXT      : Byte        read fsEXT ;
    property CAT      : Byte        read fsCAT ;
    property RET      : TACBrECFEscECFRET read fsRET ;
    property TBR      : Integer     read fsTBR ;
    property BRS      : AnsiString  read fsBRS ;
    property CHK      : Byte        read fsCHK ;
 end ;


 { Classe filha de TACBrECFClass com implementa�ao para EscECF }
TACBrECFEscECF = class( TACBrECFClass )
 private
    fsSPR            : Byte;
    fsPAF            : AnsiString ;
    fsNumVersao      : String ;
    fsVersaoEscECF   : String ;
    fsNumECF         : String ;
    fsNumCRO         : String ;
    fsEscECFComando  : TACBrECFEscECFComando;
    fsEscECFResposta : TACBrECFEscECFResposta;
    fsMarcaECF       : String ;
    fsModeloECF      : String ;
    fsEmPagamento    : Boolean ;
    fsNomeArqMemoria : String ;
    fsArqMemoria     : String ;

    procedure EnviaConsumidor(var Obs: String);
    function PreparaCmd(CmdExtBcd: AnsiString): AnsiString;
    Function TraduzErroMsg( CAT, Ocorrencia : Byte) : String;

    Function GetValorTotalizador( N, I: Integer): Double;

    Procedure SalvaRespostasMemoria( AtualizaVB: Boolean = True );
    Procedure LeRespostasMemoria;

 protected
    function VerificaFimLeitura(var Retorno: AnsiString;
      var TempoLimite: TDateTime): Boolean; override;

    function GetModeloStr: String; override ;
    function GetDataHora: TDateTime; override ;
    function GetNumCupom: String; override ;
    function GetNumECF: String; override ;
    //TODO (n�o encontrado): function GetNumLoja: String; override ;
    function GetNumCRO: String; override ;
    function GetNumSerie: String; override ;
    function GetNumVersao: String; override ;
    function GetSubTotal: Double; override ;
    function GetTotalPago: Double; override ;

    function GetNumReducoesZRestantes: String; override;

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
    function GetNumCCF: String; override ;
    function GetNumGNF: String; override ;
    function GetNumGRG: String; override ;
    function GetNumCDC: String; override ;
    function GetNumCFC: String; override ;
    function GetNumGNFC: String; override ;
    function GetNumCRZ: String; override ;
    function GetNumCFD: String; override ;
    function GetNumNCN: String; override ;
    function GetVendaBruta: Double; override ;
    function GetTotalAcrescimos: Double; override ;
    function GetTotalCancelamentos: Double; override ;
    function GetTotalDescontos: Double; override ;
    function GetTotalTroco: Double; override ;
    function GetTotalSubstituicaoTributaria: Double; override ;
    function GetTotalNaoTributado: Double; override ;
    function GetTotalIsencao: Double; override ;
    function GetTotalAcrescimosISSQN: Double; override ;
    function GetTotalCancelamentosISSQN: Double; override ;
    function GetTotalDescontosISSQN: Double; override ;
    function GetTotalIsencaoISSQN: Double; override ;
    function GetTotalNaoTributadoISSQN: Double; override ;
    function GetTotalSubstituicaoTributariaISSQN: Double; override ;
    {TODO (n�o encontrado):
    function GetTotalAcrescimosOPNF: Double; override ;
    function GetTotalCancelamentosOPNF: Double; override ;
    function GetTotalDescontosOPNF: Double; override ;
    }
    function GetNumCOOInicial: String; override ;

    { TODO (n�o encontrado): function GetNumUltimoItem: Integer; override ;}

    function GetDadosUltimaReducaoZ: AnsiString; override ;

    function GetEstado: TACBrECFEstado; override ;
    function GetGavetaAberta: Boolean; override ;
    function GetPoucoPapel : Boolean; override ;
    function GetHorarioVerao: Boolean; override ;
    { TODO (n�o encontrado): function GetChequePronto: Boolean; override ;}

    function GetParamDescontoISSQN: Boolean; override;

    { TODO (n�o encontrado): function GetTipoUltimoDocumento : TACBrECFTipoDocumento ; override ; }
 public
    Constructor create( AOwner : TComponent  )  ;
    Destructor Destroy  ; override ;

    procedure Ativar ; override ;

    property NomeArqMemoria : String read fsNomeArqMemoria write fsNomeArqMemoria;

    property EscECFComando  : TACBrECFEscECFComando  read fsEscECFComando ;
    property EscECFResposta : TACBrECFEscECFResposta read fsEscECFResposta ;

    Function EnviaComando_ECF( cmd : AnsiString = '' ) : AnsiString ; override ;

    { Aliquotas de ICMS }
    procedure CarregaAliquotas ; override ;
    procedure LerTotaisAliquota ; override ;
    function AchaICMSAliquota( var AliquotaICMS : String ) :
       TACBrECFAliquota ;  overload ; override;
    Procedure ProgramaAliquota( Aliquota : Double; Tipo : Char = 'T';
       Posicao : String = '') ; override ;

    { Formas de Pagamento }
    procedure CarregaFormasPagamento ; override ;
    procedure LerTotaisFormaPagamento ; override ;
    Procedure ProgramaFormaPagamento( var Descricao: String;
       PermiteVinculado : Boolean = true; Posicao : String = '' ) ; override ;

    { Relat�rio Gerencial (RG) }
    procedure CarregaRelatoriosGerenciais ; override ;
    procedure LerTotaisRelatoriosGerenciais ; override ;
    Procedure ProgramaRelatorioGerencial( var Descricao: String;
       Posicao : String = '') ; override ;

    { Comprovantes Nao Fiscais (CNF) }
    procedure CarregaComprovantesNaoFiscais ; override ;
    procedure LerTotaisComprovanteNaoFiscal ; override ;
    Procedure ProgramaComprovanteNaoFiscal( var Descricao: String;
       Tipo : String = ''; Posicao : String = '') ; override ;

    { Cupom Fiscal }
    Procedure AbreCupom ; override ;
    Procedure VendeItem( Codigo, Descricao : String; AliquotaECF : String;
       Qtd : Double ; ValorUnitario : Double; ValorDescontoAcrescimo : Double = 0;
       Unidade : String = ''; TipoDescontoAcrescimo : String = '%';
       DescontoAcrescimo : String = 'D'; CodDepartamento: Integer = -1) ; override ;
    Procedure DescontoAcrescimoItemAnterior( ValorDescontoAcrescimo : Double = 0;
       DescontoAcrescimo : String = 'D'; TipoDescontoAcrescimo : String = '%';
       NumItem : Integer = 0 ) ;  override ;
    Procedure SubtotalizaCupom( DescontoAcrescimo : Double = 0;
       MensagemRodape : AnsiString = '' ) ;  override ;
    procedure CancelaDescontoAcrescimoSubTotal(TipoAcrescimoDesconto: Char) ;
       override ;{ A -> Acrescimo D -> Desconto }
    Procedure EfetuaPagamento( CodFormaPagto : String; Valor : Double;
       Observacao : AnsiString = ''; ImprimeVinculado : Boolean = false) ;
       override ;
    procedure EstornaPagamento(const CodFormaPagtoEstornar,
      CodFormaPagtoEfetivar : String; const Valor: Double;
      Observacao : AnsiString = '') ; override ;

    { Para quebrar linhas nos parametros Observacao use #10 ou chr(10),
      Geralmente o ECF aceita no m�ximo 8 linhas }
    Procedure FechaCupom( Observacao : AnsiString = ''; IndiceBMP : Integer = 0) ; override ;
    Procedure CancelaCupom ; override ;
    Procedure CancelaItemVendido( NumItem : Integer ) ; override ;
    procedure CancelaItemVendidoParcial( NumItem : Integer; Quantidade : Double) ; override ;
    procedure CancelaDescontoAcrescimoItem( NumItem : Integer) ; override ;

    { Procedimentos de Cupom N�o Fiscal }
    Procedure AbreNaoFiscal( CPF_CNPJ: String = ''; Nome: String = '';
       Endereco: String = '' ) ; override ;
    Procedure RegistraItemNaoFiscal( CodCNF : String; Valor : Double;
       Obs : AnsiString = '') ; override ;
    Procedure FechaNaoFiscal( Observacao : AnsiString = ''; IndiceBMP : Integer = 0) ; override ;
    Procedure CancelaItemNaoFiscal(const AItem: Integer); override;

    procedure Sangria( const Valor: Double;  Obs : AnsiString;
       DescricaoCNF: String; DescricaoFPG: String; IndiceBMP: Integer ) ; override ;
    procedure Suprimento( const Valor: Double; Obs : AnsiString;
       DescricaoCNF: String; DescricaoFPG: String; IndiceBMP: Integer ) ; override ;

    Function EstornaCCD( const Todos: Boolean = True) : Integer; override ;

    { Gaveta de dinheiro }
    Procedure AbreGaveta  ; override ;

    { Relatorios }
    Procedure LeituraX ; override ;
    Procedure LeituraXSerial( Linhas : TStringList) ; override ;
    Procedure ReducaoZ( DataHora : TDateTime = 0 ) ; override ;
    Procedure AbreRelatorioGerencial(Indice: Integer = 0) ; override ;
    Procedure LinhaRelatorioGerencial( Linha : AnsiString; IndiceBMP: Integer = 0 ) ; override ;

    Procedure AbreCupomVinculado(COO, CodFormaPagto, CodComprovanteNaoFiscal :
       String; Valor : Double) ; override ;
    Procedure LinhaCupomVinculado( Linha : AnsiString ) ; override ;

    Procedure SegundaViaVinculado; override;
    procedure ReimpressaoVinculado; override;

    Procedure FechaRelatorio ; override ;
    Procedure CortaPapel( const CorteParcial : Boolean = false) ; override ;

    { TODO: Cheques
    Procedure ImprimeCheque(Banco : String; Valor : Double ; Favorecido,
       Cidade : String; Data : TDateTime ;Observacao : String = '') ; override ;
    Procedure CancelaImpressaoCheque ; override ;
    Function LeituraCMC7 : AnsiString ; override ;
    }

    { Utilitarios e Diversos }
    Procedure MudaHorarioVerao ; overload ; override ;
    Procedure MudaHorarioVerao( EHorarioVerao : Boolean ) ; overload ; override ;
    Procedure MudaArredondamento( Arredondar : Boolean ) ; override ;

    Procedure LeituraMemoriaFiscal( DataInicial, DataFinal : TDateTime;
       Simplificada : Boolean = False ) ; overload ; override ;
    Procedure LeituraMemoriaFiscal( ReducaoInicial, ReducaoFinal : Integer;
       Simplificada : Boolean = False ); overload ; override ;
    Procedure LeituraMemoriaFiscalSerial( DataInicial, DataFinal : TDateTime;
       Linhas : TStringList; Simplificada : Boolean = False ) ;
       overload ; override ;
    Procedure LeituraMemoriaFiscalSerial( ReducaoInicial, ReducaoFinal: Integer;
       Linhas : TStringList; Simplificada : Boolean = False ) ;
       overload ; override ;

    Procedure LeituraMFDSerial( DataInicial, DataFinal : TDateTime;
       Linhas : TStringList; Documentos : TACBrECFTipoDocumentoSet = [docTodos] ) ; overload ; override ;
    Procedure LeituraMFDSerial( COOInicial, COOFinal : Integer;
       Linhas : TStringList; Documentos : TACBrECFTipoDocumentoSet = [docTodos] ) ; overload ; override ;

    Procedure IdentificaOperador(Nome : String); override;
    Procedure IdentificaPAF( NomeVersao, MD5 : String) ; override ;

    Function RetornaInfoECF( Registrador: String) : AnsiString; override ;
 end ;

implementation
Uses SysUtils, Math, ACBrECF,
    {$IFDEF COMPILER6_UP} DateUtils, StrUtils {$ELSE} ACBrD5, Windows{$ENDIF} ;

{ TACBrECFEscECFRET }

constructor TACBrECFEscECFRET.Create;
begin
  inherited create ;

  Clear;
end;

procedure TACBrECFEscECFRET.SetRET(AValue: AnsiString);
begin
   if fsRET=AValue then Exit;

   if Length(AValue) < 4 then
      raise EACBrECFERRO.Create(ACBrStr('RET deve ter tamanho de 4 bytes')) ;

   fsRET        := AValue;
   fsECF        := ord( fsRET[1] );
   fsFisco      := ord( fsRET[2] );
   fsSPR        := ord( fsRET[3] );
   fsFabricante := ord( fsRET[4] );
end;

procedure TACBrECFEscECFRET.Clear;
begin
   fsECF        := 0;
   fsFabricante := 0;
   fsFisco      := 0;
   fsSPR        := 0;
   fsRET        := '';
end;

{ ------------------------------ TACBrECFEscECFComando -------------------------- }

constructor TACBrECFEscECFComando.Create;
begin
  inherited Create ;

  fsParams := TStringList.create ;
  fsSEQ    := 0 ;
end;

destructor TACBrECFEscECFComando.destroy;
begin
  fsParams.Free ;

  inherited destroy ;
end;


procedure TACBrECFEscECFComando.SetCMD(const Value: Byte);
begin
  Inc( fsSEQ ) ;

  fsCMD     := Value ;
  fsEXT     := 0;
  fsTimeOut := 0 ;
  fsParams.Clear ;
end;

function TACBrECFEscECFComando.GetComando: AnsiString;
Var
  I, LenCmd, Soma : Integer ;
  Buffer, BCD : AnsiString ;
  TBC : Integer ;
  CHK : Byte ;
begin
  if (fsCMD = 255) and (fsEXT = 0) then
     raise EACBrECFERRO.Create(ACBrStr('Para comandos 255, EXT deve ser especificado')) ;

  BCD := '' ;
  For I := 0 to fsParams.Count-1 do
    BCD := BCD + StringToBinaryString( AnsiString(fsParams[I]) ) + '|';
  BCD := LeftStr( BCD, cEscECFMaxBuffer);
  TBC := Length( BCD ) ;

  Buffer := AnsiChar(chr(fsSEQ)) + AnsiChar(chr(fsCMD)) + AnsiChar(chr(fsEXT)) +
            IntToLEStr(TBC) + BCD ;

  Soma := 0 ;
  LenCmd := Length( Buffer ) ;
  For I := 1 to LenCmd do
     Soma := Soma + ord( Buffer[I] ) ;
  CHK := Soma mod 256  ;

  Result := SOH + Buffer + AnsiChar(Chr( CHK )) ;
end;

procedure TACBrECFEscECFComando.AddParamString(AString: AnsiString);
var
  Buf : AnsiString ;
begin
  { Convertendo caracteres de comando para Hexa para poder armazenar
    corretamente no TStringList }
  Buf := BinaryStringToString( AString );

  fsParams.Add( TrimRight( Buf ) ) ;
end;

procedure TACBrECFEscECFComando.AddParamDouble(ADouble: Double; Decimais: Byte);
begin
  AddParamInteger( Round( ADouble * power(10, Decimais) ) ) ;
end;

procedure TACBrECFEscECFComando.AddParamInteger(AInteger: Integer);
begin
  AddParamString( IntToStr( AInteger ) ) ;
end;

procedure TACBrECFEscECFComando.AddParamDateTime(ADateTime: TDateTime;
   Tipo : Char = 'D'; FlagHV : String = ''  ) ;
Var
  Formato : String ;
begin
  case Tipo of
    'T','H' : Formato := 'hhnnss' ;
        'D' : Formato := 'ddmmyyyy' ;
  else
     Formato := 'ddmmyyyyhhnnss' ;
  end ;

  AddParamString( FormatDateTime(Formato, ADateTime) + FlagHV ) ;
end;


{ ----------------------------- TACBrECFEscECFResposta -------------------------- }

constructor TACBrECFEscECFResposta.create;
begin
  inherited create ;

  fsParams := TStringList.create ;
  fsRET    := TACBrECFEscECFRET.Create;
  fsSEQ    := 0 ;
  fsCMD    := 0 ;
  fsEXT    := 0  ;
  fsCAT    := 0  ;
  fsTBR    := 0  ;
  fsBRS    := '' ;
  fsCHK    := 0 ;
end;

destructor TACBrECFEscECFResposta.destroy;
begin
  fsParams.Free ;
  fsRET.Free;
  inherited destroy ;
end;

procedure TACBrECFEscECFResposta.Clear(ClearParams: Boolean);
begin
  if ClearParams then
     fsParams.Clear ;

  fsRET.Clear;
  fsSEQ := 0 ;
  fsCMD := 0 ;
  fsEXT := 0  ;
  fsCAT := 0  ;
  fsTBR := 0  ;
  fsBRS := '' ;
  fsCHK := 0 ;
end;

procedure TACBrECFEscECFResposta.SetResposta(const Value: AnsiString);
Var
  Soma, I, F, LenCmd : Integer ;
  CHK  : Byte ;
begin
  Clear( False ) ;    // N�o Zera Params, pois pode acumular 2 retornos

  if Value = '' then exit ;

  LenCmd := Length( Value ) ;

  if (LenCmd = 6) then  // Retorno de NAK ou WAK
  begin
    fsResposta := Value ;
    fsCAT      := ord( Value[2] ) ;
    fsRET.RET  := Copy( Value, 3, 4 );
    exit ;
  end;

  if LenCmd < 12 then
     raise EACBrECFSemResposta.Create('Tamanho de Resposta muito curto: '+
                                      IntToStr(LenCmd)+' bytes');

  fsResposta := Value ;
  fsSEQ      := ord( Value[2] ) ;
  fsCMD      := ord( Value[3] ) ;
  fsEXT      := ord( Value[4] ) ;
  fsCAT      := ord( Value[5] ) ;
  fsRET.RET  := Copy( Value, 6, 4 );
  fsTBR      := LEStrToInt( copy(Value,10,2) );
  fsBRS      := copy( Value, 12, fsTBR ) ;
  fsCHK      := ord( Value[ 12 + fsTBR ] ) ;

  Soma := 0 ;
  LenCmd := LenCmd-1 ;  { -1 por causa do CHK }
  For I := 2 to LenCmd do  
     Soma := Soma + ord( Value[I] ) ;
  CHK := Soma mod 256  ;

  if CHK <> fsCHK then
     raise EACBrECFSemResposta.Create(ACBrStr('Erro CHK Resposta. '+
        'Calculado:'+IntToStr(CHK)+' Recebido:'+IntToStr(fsCHK)));

  { Quebrando Parametros Separados por '|' e inserindo-os em fsParams }
  I := 1;
  while I < fsTBR do
  begin
     F := PosEx('|',fsBRS,I) ;
     if F < I then
        Break ;

     fsParams.Add( Copy(fsBRS, I, F-I) ) ;
     I := F+1;
  end ;

  if (I < fsTBR) and (fsTBR > 0) then   // Resposta sem �ltimo '|'
     fsParams.Add( copy( fsBRS, I, fsTBR+1-I ) ) ;
end;

{ ----------------------------- TACBrECFEscECF ------------------------------ }

constructor TACBrECFEscECF.create( AOwner : TComponent ) ;
begin
  inherited create( AOwner ) ;

  fsEscECFComando  := TACBrECFEscECFComando.create ;
  fsEscECFResposta := TACBrECFEscECFResposta.create ;

  fpDevice.HandShake := hsDTR_DSR ;
  fpPaginaDeCodigo   := 1252;
  fsArqMemoria       := '';

  fpModeloStr := 'EscECF' ;
  fpColunas   := 57 ;
  fpMFD       := True ;
  fpTermica   := True ;
  fpIdentificaConsumidorRodape := True ;

  { Variaveis internas dessa classe }
  fsNumVersao     := '' ;
  fsVersaoEscECF  := '' ;
  fsPAF           := '' ;
  fsNumECF        := '' ;
  fsNumCRO        := '' ;
  fsMarcaECF      := '';
  fsModeloECF     := '';
  fsEmPagamento   := False ;

  RespostasComando.Clear;
end;

destructor TACBrECFEscECF.Destroy;
begin
  fsEscECFComando.Free ;
  fsEscECFResposta.Free ;

  inherited Destroy ;
end;

procedure TACBrECFEscECF.Ativar;
var
   Params: String;
begin
  if not fpDevice.IsSerialPort  then
     raise EACBrECFERRO.Create(ACBrStr('A impressora: '+ModeloStr+' requer'+sLineBreak+
                            'Porta Serial:  (COM1, COM2, COM3, ...)'));

  inherited Ativar ; { Abre porta serial }

  fsNumVersao    := '' ;
  fsVersaoEscECF := '' ;
  fsPAF          := '' ;
  fsNumECF       := '' ;
  fsNumCRO       := '' ;
  fsMarcaECF     := '';
  fsModeloECF    := '';
  
  fpMFD     := True ;
  fpTermica := True ;

  RespostasComando.Clear;

  try
     { Testando a comunica�ao com a porta }
     Params := RetornaInfoECF( '15|0' );

     if Params = '' then
        raise EACBrECFNaoInicializado.Create( ACBrStr(
                 'Erro inicializando a impressora '+ModeloStr ));

     fsMarcaECF      := EscECFResposta.Params[0];
     fsModeloECF     := EscECFResposta.Params[1];
     fsNumECF        := EscECFResposta.Params[4];
     fpDecimaisPreco := min( StrToIntDef( EscECFResposta.Params[11], 2), 3);
     fpDecimaisQtd   := min( StrToIntDef( EscECFResposta.Params[12], 3), 3);
     fsNumVersao     := EscECFResposta.Params[13];
     fsVersaoEscECF  := EscECFResposta.Params[19];

     if NomeArqMemoria <> '' then
       fsArqMemoria := NomeArqMemoria
     else
       fsArqMemoria := ExtractFilePath( ParamStr(0) )+'ACBrECFEscECF'+
                       Poem_Zeros( fsNumECF, 3 )+'.txt';

     LeRespostasMemoria;
  except
     Desativar ;
     raise ;
  end ;
end;


function TACBrECFEscECF.EnviaComando_ECF(cmd : AnsiString) : AnsiString ;
Var
  ErroMsg : String ;
  OldTimeOut : Integer ;
begin
  if cmd <> '' then
     cmd := PreparaCmd(cmd) ;  // Ajusta e move para EscECFcomando

  EscECFResposta.Clear( True ) ;       // Zera toda a Resposta
  cmd := EscECFComando.Comando ;

  fsSPR             := 0 ;
  Result            := '' ;
  ErroMsg           := '' ;
  fpComandoEnviado  := '' ;
  fpRespostaComando := '' ;
  OldTimeOut        := TimeOut ;
  TimeOut           := max(EscECFComando.TimeOut, TimeOut) ;

  try
     fpDevice.Serial.DeadlockTimeout := 2000 ; { Timeout p/ Envio }
     fpDevice.Serial.Purge ;                   { Limpa a Porta }

     while fpComandoEnviado = '' do
     begin
        fpDevice.Serial.Purge ;                   { Limpa a Porta }

        if not TransmiteComando( cmd ) then
           continue ;

        fpComandoEnviado := cmd ;
     end ;

     { Chama Rotina da Classe m�e TACBrClass para ler Resposta. Se houver
       falha na leitura LeResposta dispara Exce�ao.
       Resposta fica gravada na v�riavel "fpRespostaComando" }
     LeResposta ;

     Try
        //EscECFResposta.Resposta := fpRespostaComando ;
        // Resposta j� foi atribuida em VerificaFimLeitura();

        ErroMsg := '' ;
        if EscECFResposta.CAT > 0 then
           ErroMsg := 'Erro: '+TraduzErroMsg(EscECFResposta.CAT, EscECFResposta.RET.ECF) ;
     except
        On E : Exception do
        begin
           ErroMsg := 'Resposta do ECF inv�lida' + sLineBreak + E.Message ;
        end ;
     end ;

     if ErroMsg <> '' then
      begin
        ErroMsg := ACBrStr('Erro retornado pela Impressora: '+ModeloStr+
                           sLineBreak+sLineBreak + ErroMsg ) ;

        if (EscECFResposta.CAT = 12) then
           DoOnErrorSemPapel
        else
           raise EACBrECFSemResposta.create(ErroMsg) ;
      end
     else
        Sleep( IntervaloAposComando ) ;  { Pequena pausa entre comandos }

  finally
     TimeOut := OldTimeOut ;
  end ;
end;

function TACBrECFEscECF.VerificaFimLeitura(var Retorno : AnsiString ;
  var TempoLimite : TDateTime) : Boolean ;
var
  LenRet, TBR : Integer;
  Byte1  : AnsiChar;

   procedure PedeStatus;
   begin
      GravaLog( '         Status TX -> '+ENQ+chr(fsSPR), True);
      fpDevice.Serial.SendBlock( ENQ + chr(fsSPR) ); // ACK ok, Pede Resposta
      Retorno := '';
      TempoLimite := IncSecond(now, TimeOut);
   end;

begin
  LenRet := Length( Retorno );
  Result := False;

  if LenRet < 1 then exit;

  Byte1 := Retorno[1];

  case Byte1 of
    SOH :
       begin
          if LenRet >= 11 then
          begin
             TBR    := LEStrToInt( copy(Retorno,10,2) ) ;
             Result := ( LenRet >=  (11 + TBR + 1) ) ;
          end ;
       end;

    ACK :
      begin
        fsSPR := 0;
        GravaLog( '                RX <- '+Retorno, True);
        PedeStatus ;
      end;

    NAK,WAK :  Result := (LenRet >= 6) ;
  end;

  if Result then
  begin
     try
        { Esta atribui��o, J� verifica o ChkSum, em caso de erro gera exception }
        EscECFResposta.Resposta := Retorno ;

        if (Byte1 = SOH) and
           (EscECFResposta.SEQ <> EscECFComando.SEQ) then  // Despreza esse Bloco
        begin
           raise EACBrECFCMDInvalido.Create(
              'Sequencia de Resposta ('+IntToStr(EscECFResposta.SEQ)+')'+
              'diferente da enviada ('+IntToStr(EscECFComando.SEQ)+
              '). Bloco Desprezado' ) ;
        end;
     except
        on E : EACBrECFCMDInvalido do
         begin
           GravaLog( '              Erro <- '+E.Message + ' - ' + Retorno  , True ) ;
           Result  := False ;
           Retorno := '' ;
         end
        else
           raise ;
     end ;
  end ;

  if Result then
  begin
     if (Byte1 = WAK) then // Ocupado, aguarde e solicite novo Status
      begin
        GravaLog('                RX <- '+Retorno, True);
        Sleep( 100 );
        PedeStatus;
        Result := False;
      end
     else if (Byte1 = SOH) and (EscECFResposta.CAT = 0) then
      begin
        if not TestBit( EscECFResposta.RET.ECF, 0 ) then
        begin
          GravaLog('     '+IntToStrZero(EscECFResposta.TBR,4)+' bytes RX <- '+Retorno, True);
          Inc( fsSPR );
          PedeStatus;
          Result := False;
        end;
      end;
  end;
end;

function TACBrECFEscECF.GetModeloStr: String;
begin
  Result := fsMarcaECF ;
  if fsModeloECF <> '' then
     Result := Result + ' - ' + fsModeloECF;
  if Result = '' then
     Result := fpModeloStr;
end;


function TACBrECFEscECF.PreparaCmd(CmdExtBcd: AnsiString): AnsiString;
Var
  CMD, EXT : Byte ;
  BCD : AnsiString ;
begin
  if Length(CmdExtBcd) < 7 then
     raise EACBrECFERRO.Create(ACBrStr('Comando EscECF inv�lido')) ;

  CMD := ord( CmdExtBcd[1] ) ;
  EXT := ord( CmdExtBcd[2] ) ;
  BCD := copy( CmdExtBcd, 3, Length(CmdExtBcd) ) ;

  if (CMD = 255) and (EXT = 0) then
     raise EACBrECFERRO.Create(ACBrStr('Erro ! CMD = 255 e EXT = 0')) ;

  if (CMD <> 255) and (EXT <> 0) then
     raise EACBrECFERRO.Create(ACBrStr('Erro ! EXT deve ser 0 quando comando <> 255')) ;

  EscECFComando.CMD := CMD ;
  EscECFComando.EXT := EXT ;
  EscECFComando.Params.Text := BCD ;

  Result := EscECFComando.Comando ;
end;

procedure TACBrECFEscECF.EnviaConsumidor(var Obs: String);
begin
  { Removendo o Consumidor da Observa��o, pois vai usar comando pr�prio }
  Obs := StringReplace(Obs, LF+'CPF/CNPJ consumidor: ' + Consumidor.Documento, '', []) ;
  Obs := StringReplace(Obs, LF+'Nome: '+Consumidor.Nome, '', []) ;
  Obs := StringReplace(Obs, LF+'Endereco: '+Consumidor.Endereco, '', []) ;
  try
     with EscECFComando do
     begin
        CMD := 149;
        AddParamString(LeftStr(OnlyNumber(Consumidor.Documento), 14)) ;
        AddParamString(LeftStr(Consumidor.Nome, 30)) ;
        AddParamString(LeftStr(Consumidor.Endereco, 79)) ;
     end;
     EnviaComando;
     Consumidor.Enviado := True ;
  except
  end ;
end;

function TACBrECFEscECF.TraduzErroMsg(CAT, Ocorrencia: Byte): String;
var
   MsgCAT, MsgOcorrencia: String;
begin
   MsgCAT        := '';
   MsgOcorrencia := '';

   case CAT of
     01 :
       begin
         MsgCAT := 'Comando Inv�lido';

         case Ocorrencia of
           01 : MsgOcorrencia := 'O comando enviado para a impressora n�o existe no Software B�sico' ;
         end;
       end;

     02 :
       begin
         MsgCAT := 'Erro em par�metro do comando';

         case Ocorrencia of
           01 : MsgOcorrencia := 'Conte�do de par�metro inv�lido no comando.' ;
           02 : MsgOcorrencia := 'Falta par�metro no comando' ;
           03 : MsgOcorrencia := 'Excesso de par�metros no comando' ;
           04 : MsgOcorrencia := 'COO inicial maior que COO final.' ;
           05 : MsgOcorrencia := 'CRZ inicial maior que CRZ final' ;
           06 : MsgOcorrencia := 'Data inicial maior que Data final' ;
         end;
       end;

     03 :
       begin
         MsgCAT := 'Overflow de capacidade';

         case Ocorrencia of
           01 : MsgOcorrencia := 'Excedeu a capacidade m�xima do totalizador.' ;
         end;
       end;

     04 :
       begin
         MsgCAT := 'Erro de contexto';

         case Ocorrencia of
           01 : MsgOcorrencia := 'Comando s� pode ser executado em interven��o' ;
           02 : MsgOcorrencia := 'Comando n�o pode ser executado em interven��o' ;
           03 : MsgOcorrencia := 'Comando n�o pode ser executado localmente' ;
           04 : MsgOcorrencia := 'Comando n�o pode ser executado remotamente' ;
         end;
       end;

     05 :
       begin
         MsgCAT := 'Erro em Cupom Fiscal';

         case Ocorrencia of
           01 : MsgOcorrencia := 'Comando enviado n�o pode ser executado, pois existe um Cupom Fiscal aberto.' ;
           02 : MsgOcorrencia := 'Comando enviado n�o pode ser executado, pois existe um Comprovante N�o Fiscal aberto.' ;
           03 : MsgOcorrencia := 'Comando enviado n�o pode ser executado, pois existe um Comprovante de Cr�dito ou D�bito aberto.' ;
           04 : MsgOcorrencia := 'Comando enviado n�o pode ser executado, pois existe um Estorno de Comprovante de Cr�dito ou D�bito aberto.' ;
           05 : MsgOcorrencia := 'Comando enviado n�o pode ser executado, pois existe um Relat�rio Gerencial aberto.' ;
           06 : MsgOcorrencia := 'Comando enviado n�o pode ser executado, pois o ECF est� em repouso.' ;
           07 : MsgOcorrencia := 'A quantidade m�xima de itens em um Cupom Fiscal foi ultrapassada.' ;
           08 : MsgOcorrencia := 'A quantidade de parcelas somente pode ser especificada para os pagamentos que envolvam meios que aceitem a emiss�o de CCD.' ;
           09 : MsgOcorrencia := 'Limite m�ximo de pagamentos por documento j� foi atingido.' ;
           10 : MsgOcorrencia := 'Cancelamento de um Cupom Fiscal somente ser� permitido ap�s o estorno de todos os CCDs emitidos.' ;
           11 : MsgOcorrencia := 'Comando n�o pode ser executado em documento n�o pago.';
           12 : MsgOcorrencia := 'Comando n�o pode ser executado ap�s desconto ou acr�scimo em Subtotal' ;
           13 : MsgOcorrencia := 'Comando de acr�scimo/desconto j� executado.' ;
           14 : MsgOcorrencia := 'Comando de consumidor j� executado no clich�' ;
         end;
       end;

     06 :
       begin
         MsgCAT := 'Erro em Comprovante N�o Fiscal';

         case Ocorrencia of
           01 : MsgOcorrencia := 'Comando enviado n�o pode ser executado, pois existe um Cupom Fiscal aberto.' ;
           02 : MsgOcorrencia := 'Comando enviado n�o pode ser executado, pois existe um Comprovante N�o Fiscal aberto.' ;
           03 : MsgOcorrencia := 'Comando enviado n�o pode ser executado, pois existe um Comprovante de Cr�dito ou D�bito aberto.' ;
           04 : MsgOcorrencia := 'Comando enviado n�o pode ser executado, pois existe um Estorno de Comprovante de Cr�dito ou D�bito aberto.' ;
           05 : MsgOcorrencia := 'Comando enviado n�o pode ser executado, pois existe um Relat�rio Gerencial aberto.' ;
           06 : MsgOcorrencia := 'A quantidade m�xima de itens em um Comprovante N�o Fiscal foi ultrapassada.' ;
           07 : MsgOcorrencia := 'A quantidade de parcelas somente pode ser especificada para os pagamentos que envolvam meios que aceitem a emiss�o de CCD.' ;
           08 : MsgOcorrencia := 'Limite m�ximo de pagamentos por documento j� foi atingido.' ;
           09 : MsgOcorrencia := 'Cancelamento de um Comprovante N�o Fiscal somente ser� permitido ap�s o estorno de todos os CCDs emitidos.' ;
           10 : MsgOcorrencia := 'Comando n�o pode ser executado em documento n�o pago.' ;
           11 : MsgOcorrencia := 'Comando n�o pode ser executado ap�s desconto ou acr�scimo em Subtotal';
           12 : MsgOcorrencia := 'Comando de acr�scimo/desconto j� executado.' ;
           13 : MsgOcorrencia := 'Comando de consumidor j� executado no clich�' ;
         end;
       end;

     07 :
       begin
         MsgCAT := 'Erro em Relat�rio Gerencial ou CCD';

         case Ocorrencia of
           01 : MsgOcorrencia := 'Comando enviado n�o pode ser executado, pois existe um Cupom Fiscal aberto.' ;
           02 : MsgOcorrencia := 'Comando enviado n�o pode ser executado, pois existe um Comprovante N�o Fiscal aberto.' ;
           03 : MsgOcorrencia := 'Comando enviado n�o pode ser executado, pois existe um Comprovante de Cr�dito ou D�bito aberto.' ;
           04 : MsgOcorrencia := 'Comando enviado n�o pode ser executado, pois existe um Estorno de Comprovante de Cr�dito ou D�bito aberto.' ;
           05 : MsgOcorrencia := 'Comando enviado n�o pode ser executado, pois existe um Relat�rio Gerencial aberto.' ;
           06 : MsgOcorrencia := 'N�o existe CCD para o pagamento especificado.' ;
           07 : MsgOcorrencia := 'CCD especificado j� foi impresso.' ;
           08 : MsgOcorrencia := 'CCD especificado j� foi re-impresso' ;
           09 : MsgOcorrencia := 'CCD especificado j� foi estornado.' ;
           10 : MsgOcorrencia := 'CDD n�o especificado no estorno n�o foi impresso' ;
           11 : MsgOcorrencia := 'limite m�ximo de CCD�s por cupom foi excedido.';
           12 : MsgOcorrencia := 'Comando enviado n�o pode ser executado dentro de CCD' ;
           13 : MsgOcorrencia := 'Documento anterior diferente de Cupom Fiscal e Comprovante N�o fiscal.' ;
           14 : MsgOcorrencia := 'Envio de texto gen�rico para CCD ou Relat�rio Gerencial j� fechado.' ;
         end;
       end;

     08 :
       begin
         MsgCAT := 'Erro em Redu��o Z';

         case Ocorrencia of
           01 : MsgOcorrencia := 'Redu��o Z pendente ou j� realizada na data' ;
         end;
       end;

     09 :
       begin
         MsgCAT := 'Integridade';

         case Ocorrencia of
           01 : MsgOcorrencia := 'Mem�ria Fiscal inicializada em outro ECF' ;
           02 : MsgOcorrencia := 'Mem�ria de Fita Detalhe inicializada em outro de ECF.' ;
           03 : MsgOcorrencia := 'Marca do ECF, Tipo ou Modelo incompat�vel com o gravado na Mem�ria Fiscal.' ;
           04 : MsgOcorrencia := 'N�mero de s�rie da MF diferente do gravado na MFD.' ;
           05 : MsgOcorrencia := 'N�o foi localizado o n�mero de s�rie na MF.' ;
           06 : MsgOcorrencia := 'N�o foi localizado na MF o registro do BR.' ;
           07 : MsgOcorrencia := 'N�o foi localizado na MF o S�mbolo da moeda.' ;
           08 : MsgOcorrencia := 'N�o foram localizados na MF os s�mbolos de criptografia do GT.' ;
           09 : MsgOcorrencia := 'N�o foi localizado na MF o CNPJ/ IE ou IM do usu�rio' ;
           10 : MsgOcorrencia := 'Vers�o do Software b�sico inv�lida.' ;
           11 : MsgOcorrencia := 'Mem�ria Fiscal foi desconectada.';
           12 : MsgOcorrencia := 'MFD foi desconectada' ;
           13 : MsgOcorrencia := 'Erro de grava��o na Mem�ria fiscal.' ;
           14 : MsgOcorrencia := 'Erro de grava��o na MFD' ;
           15 : MsgOcorrencia := 'Erro na recupera��o de dados da MF.' ;
           16 : MsgOcorrencia := 'Erro na recupera��o de dados da MFD' ;
           17 : MsgOcorrencia := 'Checksum inv�lido no comando recebido pelo ECF.' ;
         end;
       end;

     10 :
       begin
         MsgCAT := 'Cheque/CMC-7';

         case Ocorrencia of
           01 : MsgOcorrencia := 'Documento n�o inserido' ;
         end;
       end;

     11 :
       begin
         MsgCAT := 'Autentica��o';

         case Ocorrencia of
           01 : MsgOcorrencia := 'Excedida a quantidade permitida.' ;
           02 : MsgOcorrencia := 'N�o permitida na condi��o' ;
         end;
       end;

     12 :
       begin
         MsgCAT := 'Sem Papel';
       end;

     13 :
       begin
         MsgCAT := 'Rel�gio';

         case Ocorrencia of
           01 : MsgOcorrencia := 'Qualquer altera��o do rel�gio n�o permitida.' ;
           02 : MsgOcorrencia := 'Entrada ou sa�da de ver�o n�o permitida' ;
           03 : MsgOcorrencia := 'Rel�gio com data/hora anterior ao �ltimo documento gravado na MFD.' ;
           04 : MsgOcorrencia := 'Data/hora do rel�gio inv�lida' ;
         end;
       end;

     14 :
       begin
         MsgCAT := 'Programa��o';

         case Ocorrencia of
           01 : MsgOcorrencia := '�ndice de al�quota de ICMS j� existente.' ;
           02 : MsgOcorrencia := '�ndice de al�quota de ISSQN j� existente  ' ;
           03 : MsgOcorrencia := '�ndice de ISSQN n�o permitido.' ;
           04 : MsgOcorrencia := '�ndice de Meio de pagamento j� existente' ;
           05 : MsgOcorrencia := '�ndice de N�o Fiscal j� existente.' ;
           06 : MsgOcorrencia := '�ndice de relat�rio gerencial j� existente' ;
           07 : MsgOcorrencia := 'Excedida a quantidade m�xima' ;
         end;
       end;

     15 :
       begin
         MsgCAT := 'Protocolo';

         case Ocorrencia of
           01 : MsgOcorrencia := 'Caractere de controle inv�lido no comando recebido pelo ECF.' ;
           02 : MsgOcorrencia := 'Checksum inv�lido no comando recebido pelo ECF' ;
         end;
       end;

     16 :
       begin
         MsgCAT := 'Erro espec�fico do Fabricante';
       end;

   end;

   Result := IntToStr(CAT)+'-'+IntToStr(Ocorrencia) + ' - ' + MsgCAT ;
   if MsgOcorrencia <> '' then
      Result := Result + sLineBreak + MsgOcorrencia;
end;

function TACBrECFEscECF.GetValorTotalizador(N, I : Integer) : Double ;
var
  StrValue: String;
begin
  Result := 0;
  RetornaInfoECF( '6|'+IntToStr( N ) ) ;
  if EscECFResposta.Params.Count >= I then
  begin
     StrValue := EscECFResposta.Params[ I ] ;
     Result   := StrToInt( StrValue ) / 100;
  end;
end;

procedure TACBrECFEscECF.SalvaRespostasMemoria(AtualizaVB : Boolean) ;
Var
  ValVB : Double;
begin
  try
     ValVB := RespostasComando.FieldByName('VendaBruta').AsFloat;
  except
     AtualizaVB := True;
  end ;

  if AtualizaVB then
  begin
    ValVB := GetVendaBruta;
    RespostasComando.AddField( 'VendaBruta', IntToStr(Trunc(SimpleRoundTo( ValVB * 100 ,0))) );
    RespostasComando.AddField( 'EmPagamento', ifthen( fsEmPagamento,'1','0') );
  end ;

  RespostasComando.SaveToFile( fsArqMemoria );
end ;

procedure TACBrECFEscECF.LeRespostasMemoria ;
Var
  ValVB : Double;
begin
  if not FileExists( fsArqMemoria ) then
    exit ;

  RespostasComando.LoadFromFile( fsArqMemoria );

  try
     ValVB := RespostasComando.FieldByName('VendaBruta').AsFloat;
     if ValVB <> GetVendaBruta then
        RespostasComando.Clear;    // Arquivo invalido

     fsEmPagamento := (RespostasComando.FieldByName( 'EmPagamento' ).AsInteger = 1);
  except
     RespostasComando.Clear;       // Arquivo invalido
  end;
end ;

function TACBrECFEscECF.RetornaInfoECF(Registrador: String): AnsiString;
begin
  if Pos('|',Registrador) = 0 then
     Registrador := Registrador + '|' ;

  EscECFComando.CMD := 26;
  EscECFComando.AddParamString(Registrador);
  EnviaComando;

  Result := EscECFResposta.BRS;
  if (RightStr(Result,1) = '|') then
     Delete( Result, Length(Result), 1 );
end;

function TACBrECFEscECF.GetDataHora: TDateTime;
Var
  RetCmd : String ;
begin
  RetCmd := RetornaInfoECF( '9' ) ;

  Result := EncodeDateTime( StrToInt(copy(RetCmd,5,4)),  // Ano
                            StrToInt(copy(RetCmd,3,2)),  // Mes
                            StrToInt(copy(RetCmd,1,2)),  // Dia
                            StrToInt(copy(RetCmd,9,2)),  // Hora
                            StrToInt(copy(RetCmd,11,2)), // Minuto
                            StrToInt(copy(RetCmd,13,2)), // Segundo
                            0 ) ;
end;

function TACBrECFEscECF.GetNumCupom: String;
begin
  RetornaInfoECF( '1|1' ) ;
  Result := EscECFResposta.Params[1] ;
end;

function TACBrECFEscECF.GetNumECF: String;
begin
  Result := fsNumECF ;
end;

function TACBrECFEscECF.GetNumCRO: String;
begin
  if Trim(fsNumCRO) = '' then
  begin
    RetornaInfoECF( '1|3' ) ;
    Result := EscECFResposta.Params[1] ;
  end ;

  Result := fsNumCRO ;
end;

function TACBrECFEscECF.GetNumSerie: String;
begin
  Result := RetornaInfoECF( '15|4' ) ;
end;

function TACBrECFEscECF.GetNumVersao: String ;
begin
  Result := fsNumVersao ;
end;

function TACBrECFEscECF.GetTotalPago: Double;
var
  APagar : Double ;
begin
  // Obs: N�o h� comando que retorne o TotalPago...
  try
     APagar := RespostasComando.FieldByName('TotalAPagar').AsFloat;
  except
     APagar := 0;
  end;

  if APagar > 0 then
    Result := (GetSubTotal - APagar)
  else
    Result := 0;
end;

function TACBrECFEscECF.GetNumReducoesZRestantes: String;
begin
  RetornaInfoECF( '1|15' ) ;
  Result := EscECFResposta.Params[1] ;
end;

function TACBrECFEscECF.GetSubTotal: Double;
begin
  // Obs: N�o h� comando que retorne o SubTotal...
  try
     Result := RespostasComando.FieldByName('SubTotal').AsFloat;
  except
     Result := 0;
  end;
end;

function TACBrECFEscECF.GetEstado: TACBrECFEstado;
Var
  FlagEst : Integer ;
begin
  Result := fpEstado;
  try
     if (not fpAtivo) then
        fpEstado := estNaoInicializada
     else
      begin
        fpEstado := estDesconhecido ;

        FlagEst := StrToInt( RetornaInfoECF( '16|4' ) );
        if FlagEst = 3 then
           fpEstado := estBloqueada
        else if fsEmPagamento then
           fpEstado := estPagamento 
        else
         begin
           FlagEst := StrToInt( RetornaInfoECF( '16|5' ) );
           Case FlagEst of
             0  :             fpEstado := estLivre;
             10 :             fpEstado := estVenda;
             11..13, 21..23 : fpEstado := estPagamento;
             20 :             fpEstado := estNaoFiscal;
             30..32 :         fpEstado := estRelatorio;
           end;
         end;

        if fpEstado in [estLivre, estBloqueada] then
        begin
           RetornaInfoECF( '8' ) ;
           FlagEst := StrToInt( EscECFResposta.Params[1] );

           if FlagEst = 2 then
              fpEstado := estRequerZ;
        end;
      end ;
  finally
     Result := fpEstado ;
  end;
end;

function TACBrECFEscECF.GetGavetaAberta: Boolean;
begin
  Result := RetornaInfoECF( '16|1' ) = '1' ;
end;

function TACBrECFEscECF.GetPoucoPapel: Boolean;
begin
  Result := RetornaInfoECF( '16|2' ) > '0' ;
end;

function TACBrECFEscECF.GetHorarioVerao: Boolean;
begin
  Result := UpperCase(RightStr( RetornaInfoECF( '9' ) ,1 )) =  'V' ;
end;

function TACBrECFEscECF.GetParamDescontoISSQN : Boolean ;
begin
  Result := True; // ESCEcf sempre permite Desconto de ISSQN
end ;

procedure TACBrECFEscECF.LeituraX ;
begin
  EscECFComando.CMD := 20;
  EscECFComando.AddParamInteger(0); // Imprime no ECF
  EnviaComando;
end;

procedure TACBrECFEscECF.LeituraXSerial(Linhas: TStringList);
Var
  Buffer : AnsiString;
  I: Integer;
begin
  EscECFComando.CMD := 20;
  EscECFComando.AddParamInteger(1); // Envia pela Serial
  EnviaComando;

  Buffer := '';
  For I := 0 to EscECFResposta.Params.Count-1 do
     Buffer := Buffer + EscECFResposta.Params[I] ;

  Linhas.Clear;
  Linhas.Text := Buffer;
end;

procedure TACBrECFEscECF.AbreGaveta ;
begin
  EscECFComando.CMD := 6;
  EnviaComando;
end;

procedure TACBrECFEscECF.ReducaoZ(DataHora : TDateTime) ;
begin
  if DataHora = 0 then  { Aparentemente a DataHora � obrigat�ria na EscECF }
     DataHora := now ;

  EscECFComando.CMD := 21;
  EscECFComando.AddParamDateTime(DataHora, 'D' );
  EscECFComando.AddParamDateTime(DataHora, 'H' );
  EscECFComando.AddParamInteger(0); // Imprime no ECF

  try
     EnviaComando ;
     RespostasComando.Clear;
     SalvaRespostasMemoria(True);
  except
     on E : Exception do
     begin
        if (pos('999-999',E.Message) <> 0) then     // TODO: Erro de Hora fora da faixa ?  
           ReducaoZ(0)                              // Tenta sem DataHora
        else if (pos('5-1',E.Message) <> 0) then    // Comando inv�lido para o documento atual.
         begin                                      //  Ficou algum Cupom aberto ?
           // Cancelando o Cupom em aberto
           EscECFComando.CMD := 31;
           EnviaComando;

           ReducaoZ(DataHora);
         end 
        else
           raise ;
     end ;
  end ;
end;

procedure TACBrECFEscECF.AbreRelatorioGerencial(Indice: Integer);
begin
  Indice := max(Indice,1) ;

  EscECFComando.CMD := 12;
  EscECFComando.AddParamInteger(Indice);
  EnviaComando;

  RespostasComando.Clear;
  RespostasComando.AddField( 'COO',        EscECFResposta.Params[0] );
  RespostasComando.AddField( 'DataHora',   EscECFResposta.Params[1] );
  RespostasComando.AddField( 'VendaBruta', EscECFResposta.Params[2] );
  RespostasComando.AddField( 'NumSerie',   EscECFResposta.Params[3] );
  SalvaRespostasMemoria(False);
end;

procedure TACBrECFEscECF.LinhaRelatorioGerencial(Linha: AnsiString;
   IndiceBMP: Integer);
var
  P, Espera: Integer;
  Buffer   : AnsiString ;
begin
  Linha := AjustaLinhas( Linha, Colunas );  { Formata as Linhas de acordo com "Coluna" }

  while Length( Linha ) > 0 do
  begin
     P := Length( Linha ) ;
     if P > cEscECFMaxBuffer then    { Acha o fim de Linha mais pr�ximo do limite m�ximo }
        P := PosLast(LF, LeftStr(Linha,cEscECFMaxBuffer) ) ;

     if P = 0 then
        P := Colunas ;

     Buffer := copy( Linha, 1, P)  ;
     Espera := Trunc( CountStr( Buffer, LF ) / 4) ;

     EscECFComando.CMD := 9                                ;
     EscECFComando.TimeOut := Espera ;
     EscECFComando.AddParamString(Buffer);
     EnviaComando;

     { ficou apenas um LF sozinho ? }
     if (P = Colunas) and (RightStr( Buffer, 1) <> LF) and
        (copy( Linha, P+1, 1) = LF) then
        P := P + 1 ;

     Linha  := copy( Linha, P+1, Length(Linha) ) ;   // O Restante
  end ;
end;

procedure TACBrECFEscECF.AbreCupomVinculado(COO, CodFormaPagto,
   CodComprovanteNaoFiscal: String; Valor: Double);
begin
  with EscECFComando do
  begin
     CMD := 8;
     AddParamInteger(1) ;   // TODO: Achar a Sequencia do Pagamento...
     AddParamString(CodFormaPagto) ;
     AddParamInteger(1) ;   // Qtd Parcelas ??
     AddParamInteger(1) ;   // Num Parcela ??
     AddParamString(LeftStr(OnlyNumber(Consumidor.Documento),14)) ;
     AddParamString(LeftStr(Consumidor.Nome,30)) ;
     AddParamString(LeftStr(Consumidor.Endereco,79)) ;
  end;
  EnviaComando;

  RespostasComando.Clear;
  RespostasComando.AddField( 'COO',            EscECFResposta.Params[0] );
  RespostasComando.AddField( 'DataHora',       EscECFResposta.Params[1] );
  RespostasComando.AddField( 'VendaBruta',     EscECFResposta.Params[2] );
  RespostasComando.AddField( 'NumSerie',       EscECFResposta.Params[3] );
  RespostasComando.AddField( 'SeqPagto',       EscECFResposta.Params[4] );
  RespostasComando.AddField( 'NumParcela',     EscECFResposta.Params[5] );
  RespostasComando.AddField( 'NumParcelaFalta',EscECFResposta.Params[6] );

  Consumidor.Enviado := True ;
  fsEmPagamento := false ;

  SalvaRespostasMemoria(False);
end;

procedure TACBrECFEscECF.MudaHorarioVerao ;
begin
  MudaHorarioVerao( not HorarioVerao) ;
end;

procedure TACBrECFEscECF.MudaHorarioVerao(EHorarioVerao: Boolean);
begin
  EscECFComando.CMD := 80;
  EscECFComando.AddParamInteger( IfThen(EHorarioVerao,1,0) );
  EnviaComando;
end;

procedure TACBrECFEscECF.MudaArredondamento(Arredondar: Boolean);
begin
   inherited MudaArredondamento(Arredondar);
end;

procedure TACBrECFEscECF.LeituraMemoriaFiscal(DataInicial,
   DataFinal: TDateTime; Simplificada: Boolean);
begin
  EscECFComando.CMD := 22;
  EscECFComando.AddParamInteger( 0 ); // Imprime no ECF
  EscECFComando.AddParamInteger( ifthen(Simplificada,2,1) );
  EscECFComando.AddParamInteger( 1 );   // Por Data
  EscECFComando.AddParamDateTime( DataInicial );
  EscECFComando.AddParamDateTime( DataFinal );

  EnviaComando;
end;

procedure TACBrECFEscECF.LeituraMemoriaFiscal(ReducaoInicial,
   ReducaoFinal: Integer; Simplificada: Boolean);
begin
  EscECFComando.CMD := 22;
  EscECFComando.AddParamInteger( 0 ); // Imprime no ECF
  EscECFComando.AddParamInteger( ifthen(Simplificada,2,1) );
  EscECFComando.AddParamInteger( 2 );   // Por CRZ
  EscECFComando.AddParamInteger( ReducaoInicial );
  EscECFComando.AddParamInteger( ReducaoFinal );

  EnviaComando;
end;

procedure TACBrECFEscECF.LeituraMemoriaFiscalSerial(DataInicial,
   DataFinal: TDateTime; Linhas: TStringList; Simplificada: Boolean);
var
   Buffer: AnsiString;
   I: Integer;
begin
  EscECFComando.CMD := 22;
  EscECFComando.AddParamInteger( 1 ); // Pela Serial
  EscECFComando.AddParamInteger( ifthen(Simplificada,2,1) );
  EscECFComando.AddParamInteger( 1 );   // Por Data
  EscECFComando.AddParamDateTime( DataInicial );
  EscECFComando.AddParamDateTime( DataFinal );

  EnviaComando;

  Buffer := '';
  For I := 0 to EscECFResposta.Params.Count-1 do
     Buffer := Buffer + EscECFResposta.Params[I] ;

  Linhas.Clear;
  Linhas.Text := Buffer;
end;

procedure TACBrECFEscECF.LeituraMemoriaFiscalSerial(ReducaoInicial,
   ReducaoFinal: Integer; Linhas: TStringList; Simplificada: Boolean);
var
   Buffer: AnsiString;
   I: Integer;
begin
  EscECFComando.CMD := 22;
  EscECFComando.AddParamInteger( 1 ); // Pela Serial
  EscECFComando.AddParamInteger( ifthen(Simplificada,2,1) );
  EscECFComando.AddParamInteger( 2 );   // Por CRZ
  EscECFComando.AddParamInteger( ReducaoInicial );
  EscECFComando.AddParamInteger( ReducaoFinal );

  EnviaComando;

  Buffer := '';
  For I := 0 to EscECFResposta.Params.Count-1 do
     Buffer := Buffer + EscECFResposta.Params[I] ;

  Linhas.Clear;
  Linhas.Text := Buffer;
end;

procedure TACBrECFEscECF.LeituraMFDSerial(DataInicial, DataFinal: TDateTime;
   Linhas: TStringList; Documentos: TACBrECFTipoDocumentoSet);
{var
   Buffer: AnsiString;
   I: Integer;}
begin
  // TODO:  N�o h� como retornar o espelho pela Serial ??

  Inherited LeituraMFDSerial(DataFinal, DataFinal, Linhas, Documentos);

  {
  EscECFComando.CMD := 100;
  EscECFComando.AddParamInteger( 1 );   // Por Data
  EscECFComando.AddParamDateTime( DataInicial );
  EscECFComando.AddParamDateTime( DataFinal );

  EnviaComando;

  Buffer := '';
  For I := 0 to EscECFResposta.Params.Count-1 do
     Buffer := Buffer + EscECFResposta.Params[I] ;

  Linhas.Clear;
  Linhas.Text := Buffer;
  }
end;

procedure TACBrECFEscECF.LeituraMFDSerial(COOInicial, COOFinal: Integer;
   Linhas: TStringList; Documentos: TACBrECFTipoDocumentoSet);
{var
   Buffer: AnsiString;
   I: Integer;}
begin
  // TODO:  N�o h� como retornar o espelho pela Serial ??

  Inherited LeituraMFDSerial(COOInicial, COOFinal, Linhas, Documentos);

  {
  EscECFComando.CMD := 100;
  EscECFComando.AddParamInteger( 2 );   // Por COO
  EscECFComando.AddParamDateTime( COOInicial );
  EscECFComando.AddParamDateTime( COOFinal );

  EnviaComando;

  Buffer := '';
  For I := 0 to EscECFResposta.Params.Count-1 do
     Buffer := Buffer + EscECFResposta.Params[I] ;

  Linhas.Clear;
  Linhas.Text := Buffer;
  }
end;

procedure TACBrECFEscECF.IdentificaOperador(Nome: String);
begin
  EscECFComando.CMD := 154;
  EscECFComando.AddParamString(LeftStr(Nome,20)) ;
  EnviaComando;
end;

procedure TACBrECFEscECF.IdentificaPAF(NomeVersao, MD5: String);
begin
  fsPAF := LeftStr(Trim(MD5) + LF + Trim(NomeVersao), 84) ;
  EscECFComando.CMD := 24;
  EscECFComando.AddParamString( fsPAF ) ;
  EnviaComando;
end;

procedure TACBrECFEscECF.AbreCupom ;
begin
  EscECFComando.CMD := 1;
  EscECFComando.AddParamString(LeftStr(OnlyNumber(Consumidor.Documento),14)) ;
  EscECFComando.AddParamString(LeftStr(Consumidor.Nome,30)) ;
  EscECFComando.AddParamString(LeftStr(Consumidor.Endereco,79)) ;
  EnviaComando;

  RespostasComando.Clear;
  RespostasComando.AddField( 'COO',        EscECFResposta.Params[0] );
  RespostasComando.AddField( 'DataHora',   EscECFResposta.Params[1] );
  RespostasComando.AddField( 'VendaBruta', EscECFResposta.Params[2] );
  RespostasComando.AddField( 'NumSerie',   EscECFResposta.Params[3] );

  Consumidor.Enviado := True ;
  fsEmPagamento := false ;

  SalvaRespostasMemoria(False);
end;

procedure TACBrECFEscECF.VendeItem(Codigo, Descricao: String;
   AliquotaECF: String; Qtd: Double; ValorUnitario: Double;
   ValorDescontoAcrescimo: Double; Unidade: String;
   TipoDescontoAcrescimo: String; DescontoAcrescimo: String;
   CodDepartamento: Integer);
begin
  with EscECFComando do
  begin
     CMD := 2 ;
     AddParamString( LeftStr(Codigo,14) );
     AddParamString( LeftStr(Descricao,233) );
     AddParamString( AliquotaECF );
     AddParamString( Trim(LeftStr( OnlyAlphaNum(Unidade),3)) );
     AddParamDouble( Qtd, fpDecimaisQtd );
     AddParamInteger( fpDecimaisQtd );
     AddParamDouble( ValorUnitario, fpDecimaisPreco );
     AddParamInteger( fpDecimaisPreco );
     AddParamString( ifthen(ArredondaItemMFD,'A','T') );
  end ;

  EnviaComando ;

  RespostasComando.AddField( 'NumUltItem', EscECFResposta.Params[0] );
  RespostasComando.AddField( 'TotalItem',  EscECFResposta.Params[1] );
  RespostasComando.AddField( 'SubTotal',   EscECFResposta.Params[2] );
  fsEmPagamento := false ;

  SalvaRespostasMemoria(True);

  { Se o desconto � maior que zero d� o comando de desconto de item }
  if ValorDescontoAcrescimo > 0 then
     DescontoAcrescimoItemAnterior( ValorDescontoAcrescimo, DescontoAcrescimo,
        TipoDescontoAcrescimo);
end;

procedure TACBrECFEscECF.DescontoAcrescimoItemAnterior(
   ValorDescontoAcrescimo: Double; DescontoAcrescimo: String;
   TipoDescontoAcrescimo: String; NumItem: Integer);
begin
  with EscECFComando do
  begin
     CMD := 27 ;
     AddParamInteger( ifthen(DescontoAcrescimo    ='D',0,1) );
     AddParamInteger( ifthen(TipoDescontoAcrescimo='%',0,1) );
     if NumItem > 0 then
        AddParamInteger( NumItem )
     else
        AddParamString( '' ) ;
  end ;

  EnviaComando ;

  RespostasComando.AddField( 'TotalItem',  EscECFResposta.Params[0] );
  RespostasComando.AddField( 'SubTotal',   EscECFResposta.Params[1] );
  SalvaRespostasMemoria(False);
end;

procedure TACBrECFEscECF.CancelaCupom;
var
   UltimoCOO: Integer;
   Est: TACBrECFEstado;
begin
  RespostasComando.Clear;
  Est := TACBrECF( fpOwner ).Estado;

  case Est of
    estRelatorio : FechaRelatorio ;

    estVenda, estPagamento :
      begin
        EscECFComando.CMD := 31;
        EnviaComando;
      end;
  else
    begin
      UltimoCOO := StrToInt( TACBrECF( fpOwner ).NumCOO );
      EscECFComando.CMD := 7;
      EscECFComando.AddParamInteger( UltimoCOO );
      EnviaComando;
    end;
  end;

  fsEmPagamento := false ;
  SalvaRespostasMemoria(False);
end;

procedure TACBrECFEscECF.CancelaItemVendido(NumItem: Integer);
begin
  EscECFComando.CMD := 3;
  EscECFComando.AddParamInteger( NumItem );
  EnviaComando;

  RespostasComando.AddField( 'SubTotal',   EscECFResposta.Params[0] );
  SalvaRespostasMemoria(False);
end;

procedure TACBrECFEscECF.CancelaItemVendidoParcial(NumItem: Integer;
   Quantidade: Double);
begin
  with EscECFComando do
  begin
     CMD := 151 ;
     AddParamInteger( NumItem );
     AddParamDouble( Quantidade, 3 )
  end ;

  EnviaComando ;

  RespostasComando.AddField( 'TotalItem',  EscECFResposta.Params[0] );
  RespostasComando.AddField( 'SubTotal',   EscECFResposta.Params[1] );
  SalvaRespostasMemoria(False);
end;

procedure TACBrECFEscECF.CancelaDescontoAcrescimoItem(NumItem: Integer);
begin
  with EscECFComando do
  begin
     CMD := 28 ;
     AddParamInteger( 0 );
     AddParamInteger( NumItem )
  end ;

  EnviaComando ;

  RespostasComando.AddField( 'TotalItem',  EscECFResposta.Params[0] );
  RespostasComando.AddField( 'SubTotal',   EscECFResposta.Params[1] );
  SalvaRespostasMemoria(False);
end;

procedure TACBrECFEscECF.AbreNaoFiscal(CPF_CNPJ: String; Nome: String;
   Endereco: String);
begin
  EscECFComando.CMD := 16;
  EscECFComando.AddParamString(LeftStr(OnlyNumber(Consumidor.Documento),14)) ;
  EscECFComando.AddParamString(LeftStr(Consumidor.Nome,30)) ;
  EscECFComando.AddParamString(LeftStr(Consumidor.Endereco,79)) ;
  EnviaComando;

  RespostasComando.Clear;
  RespostasComando.AddField( 'COO',        EscECFResposta.Params[0] );
  RespostasComando.AddField( 'DataHora',   EscECFResposta.Params[1] );
  RespostasComando.AddField( 'VendaBruta', EscECFResposta.Params[2] );
  RespostasComando.AddField( 'NumSerie',   EscECFResposta.Params[3] );

  Consumidor.Enviado := True ;
  fsEmPagamento := false ;
  SalvaRespostasMemoria(False);
end;

procedure TACBrECFEscECF.RegistraItemNaoFiscal(CodCNF: String; Valor: Double;
   Obs: AnsiString);
begin
  EscECFComando.CMD := 17;
  EscECFComando.AddParamString( CodCNF ) ;
  EscECFComando.AddParamDouble( Valor ) ;
  EnviaComando;

  RespostasComando.AddField( 'NumUltItem', EscECFResposta.Params[0] );
  RespostasComando.AddField( 'SubTotal',   EscECFResposta.Params[2] );
  fsEmPagamento := false ;
  SalvaRespostasMemoria(True);
end;

procedure TACBrECFEscECF.EfetuaPagamento(CodFormaPagto: String;
  Valor: Double; Observacao: AnsiString; ImprimeVinculado: Boolean);
begin
  with EscECFComando do
  begin
     CMD := 4 ;
     AddParamString( CodFormaPagto );
     AddParamDouble( Valor );
     AddParamInteger( 1 );  // Parcelas ??
     AddParamString( LeftStr(Observacao, 84) );
     AddParamInteger( 7 );  // TODO: Meios de pagamento ??
     { 1-Dinheiro, 2-Cheque, 3-Cart�o de Cr�dito, 4-Cart�o de D�bito,
       5-Cart�o Refei��o/Alimenta��o, 6-Vale Refei��o/Alimenta��o (em papel),
       7-Outros }
  end ;

  EnviaComando ;

  RespostasComando.AddField( 'TotalAPagar', EscECFResposta.Params[0] );
  SalvaRespostasMemoria(False);
end;

procedure TACBrECFEscECF.EstornaPagamento(const CodFormaPagtoEstornar,
   CodFormaPagtoEfetivar: String; const Valor: Double; Observacao: AnsiString);
begin
  with EscECFComando do
  begin
     CMD := 19 ;
     AddParamString( CodFormaPagtoEstornar );
     AddParamString( CodFormaPagtoEfetivar );
     AddParamDouble( Valor );
     AddParamInteger( 1 );  // Parcelas ??
     AddParamString( LeftStr(Observacao, 84) );
  end ;

  EnviaComando ;

  RespostasComando.AddField( 'COO',        EscECFResposta.Params[0] );
  RespostasComando.AddField( 'DataHora',   EscECFResposta.Params[1] );
  RespostasComando.AddField( 'VendaBruta', EscECFResposta.Params[2] );
  RespostasComando.AddField( 'NumSerie',   EscECFResposta.Params[3] );
  SalvaRespostasMemoria(False);
end;

procedure TACBrECFEscECF.FechaCupom(Observacao: AnsiString; IndiceBMP: Integer);
var
   Obs: String;
begin
  Obs := Observacao ;

  if not Consumidor.Enviado then
     EnviaConsumidor( Obs );

  with EscECFComando do
  begin
     CMD := 5 ;
     AddParamInteger( 0 );  // Sem Cupom Adicional
     AddParamInteger( 1 );  // Aciona a Guilhotina
     AddParamString( Obs );
  end ;
  EnviaComando ;

  RespostasComando.AddField( 'COO',        EscECFResposta.Params[0] );
  RespostasComando.AddField( 'DataHora',   EscECFResposta.Params[1] );
  RespostasComando.AddField( 'VendaBruta', EscECFResposta.Params[2] );
  fsEmPagamento := false ;

  SalvaRespostasMemoria(False);
end;

procedure TACBrECFEscECF.SubtotalizaCupom(DescontoAcrescimo: Double;
       MensagemRodape : AnsiString );
begin
  fsEmPagamento := True ;
  if DescontoAcrescimo = 0 then exit ;
  
  with EscECFComando do
  begin
     CMD := 29 ;
     AddParamInteger( ifthen(DescontoAcrescimo < 0,0,1) );
     AddParamInteger( 1 );
     AddParamDouble( abs(DescontoAcrescimo) );
  end ;
  EnviaComando ;

  RespostasComando.AddField( 'SubTotal', EscECFResposta.Params[0] );
  SalvaRespostasMemoria(False);
end;

procedure TACBrECFEscECF.CancelaDescontoAcrescimoSubTotal(
   TipoAcrescimoDesconto: Char);
begin
  with EscECFComando do
  begin
     CMD := 30 ;
     AddParamInteger( ifthen(TipoAcrescimoDesconto = 'D',0,1) );
  end ;
  EnviaComando ;

  RespostasComando.AddField( 'SubTotal', EscECFResposta.Params[0] );
  SalvaRespostasMemoria(False);
end;


procedure TACBrECFEscECF.CarregaAliquotas;
Var
  I, N :Integer ;
  Aliquota: TACBrECFAliquota;
begin
  RetornaInfoECF( '5' );

  inherited CarregaAliquotas;

  try
     N := Trunc(EscECFResposta.Params.Count / 4) - 1;
     For I := 0 to N do
     begin
       Aliquota := TACBrECFAliquota.create;
       Aliquota.Sequencia := I;
       Aliquota.Tipo      := EscECFResposta.Params[ 4*I + 1 ][1] ;
       { Adiciona o tipo no Indice, pois no comando de Venda de Item ele ser� necessario }
       Aliquota.Indice    := Aliquota.Tipo + EscECFResposta.Params[ 4*I ] ;
       Aliquota.Aliquota  := StrToInt( OnlyNumber(EscECFResposta.Params[ 4*I + 2 ]) ) / 100 ;
       Aliquota.Total     := StrToInt( EscECFResposta.Params[ 4*I + 3 ] ) / 100 ;

       fpAliquotas.Add(Aliquota);
     end;
  except
     fpAliquotas.Free ;
     fpAliquotas := nil ;
     raise ;
  end;
end;

procedure TACBrECFEscECF.ProgramaAliquota(Aliquota: Double; Tipo: Char;
   Posicao : String );
var
   PosAliq: Integer;
begin
  if not Assigned( fpAliquotas ) then
     CarregaAliquotas ;

  EscECFComando.CMD := 81;
  if Posicao = '' then
     PosAliq := Aliquotas.Count + 1
  else
     PosAliq := StrToInt( Posicao );
  EscECFComando.AddParamInteger( PosAliq ) ;
  EscECFComando.AddParamString( Tipo ) ;
  EscECFComando.AddParamString( IntToStrZero( Trunc(Aliquota*100), 4 ) ) ;
  EnviaComando;

  CarregaAliquotas;
end;

procedure TACBrECFEscECF.CarregaFormasPagamento;
Var
  I, N :Integer ;
  FPG: TACBrECFFormaPagamento;
begin
  RetornaInfoECF( '14' );

  inherited CarregaFormasPagamento;

  try
     N := Trunc(EscECFResposta.Params.Count / 3) - 1;
     For I := 0 to N do
     begin
       FPG := TACBrECFFormaPagamento.create;
       FPG.Indice           := EscECFResposta.Params[ 3*I ] ;
       FPG.Descricao        := EscECFResposta.Params[ 3*I + 1 ] ;
       FPG.PermiteVinculado := (EscECFResposta.Params[ 3*I + 2 ] = '1') ;

       fpFormasPagamentos.Add(FPG);
     end;
  except
     fpFormasPagamentos.Free ;
     fpFormasPagamentos := nil ;
     raise ;
  end;
end;

procedure TACBrECFEscECF.LerTotaisFormaPagamento;
Var
  I, N :Integer ;
  FPG: TACBrECFFormaPagamento;
begin
  if not Assigned( fpFormasPagamentos ) then
     CarregaFormasPagamento ;

  RetornaInfoECF( '7' );

  N := Trunc(EscECFResposta.Params.Count / 2) - 1;
  For I := 0 to N do
  begin
    FPG := AchaFPGIndice( IntToStr(StrToInt(EscECFResposta.Params[ 2*I ])) ) ;
    if Assigned( FPG ) then
       FPG.Total := StrToInt( EscECFResposta.Params[ 2*I + 1 ] ) / 100;
  end;
end;

procedure TACBrECFEscECF.ProgramaFormaPagamento(var Descricao: String;
  PermiteVinculado: Boolean; Posicao : String);
var
   PosFPG : Integer;
begin
  if not Assigned( fpFormasPagamentos ) then
     CarregaFormasPagamento ;

  Descricao := LeftStr(TiraAcentos( DecodificarPaginaDeCodigoECF(Descricao) ),15) ;

  EscECFComando.CMD := 84;
  if Posicao = '' then
     PosFPG := FormasPagamento.Count + 1
  else
     PosFPG := StrToInt( Posicao );
  EscECFComando.AddParamInteger( PosFPG ) ;
  EscECFComando.AddParamString( Descricao ) ;
  EscECFComando.AddParamInteger( ifthen(PermiteVinculado,1,0) ) ;
  EnviaComando;

  CarregaFormasPagamento;
end;

procedure TACBrECFEscECF.CarregaRelatoriosGerenciais;
Var
  I, N :Integer ;
  RelGer: TACBrECFRelatorioGerencial;
begin
  RetornaInfoECF( '13' );

  inherited CarregaRelatoriosGerenciais;

  try
     N := Trunc(EscECFResposta.Params.Count / 2) - 1;
     For I := 0 to N do
     begin
       RelGer := TACBrECFRelatorioGerencial.create;
       RelGer.Indice    := EscECFResposta.Params[ 2*I ] ;
       RelGer.Descricao := EscECFResposta.Params[ 2*I + 1 ] ;

       fpRelatoriosGerenciais.Add(RelGer);
     end;
  except
     fpRelatoriosGerenciais.Free ;
     fpRelatoriosGerenciais := nil ;
     raise ;
  end;
end;

procedure TACBrECFEscECF.LerTotaisRelatoriosGerenciais;
Var
  I, N :Integer ;
  RelGer : TACBrECFRelatorioGerencial;
begin
  if not Assigned( fpRelatoriosGerenciais ) then
     CarregaRelatoriosGerenciais ;

  RetornaInfoECF( '2' );

  N := Trunc(EscECFResposta.Params.Count / 2) - 1;
  For I := 0 to N do
  begin
    RelGer := AchaRGIndice( EscECFResposta.Params[ 2*I ] ) ;
    if Assigned( RelGer ) then
       RelGer.Contador := StrToInt( EscECFResposta.Params[ 2*I + 1 ] ) ;
  end;
end;

procedure TACBrECFEscECF.ProgramaRelatorioGerencial(var Descricao: String;
   Posicao: String);
var
   PosRel : Integer;
begin
  if not Assigned( fpRelatoriosGerenciais ) then
     CarregaRelatoriosGerenciais ;

  Descricao := LeftStr(TiraAcentos( DecodificarPaginaDeCodigoECF(Descricao) ),15) ;

  EscECFComando.CMD := 86;
  if Posicao = '' then
     PosRel := RelatoriosGerenciais.Count + 1
  else
     PosRel := StrToInt( Posicao );
  EscECFComando.AddParamInteger( PosRel ) ;
  EscECFComando.AddParamString( Descricao ) ;
  EnviaComando;

  CarregaRelatoriosGerenciais;
end;

procedure TACBrECFEscECF.CarregaComprovantesNaoFiscais;
Var
  I, N :Integer ;
  CNF : TACBrECFComprovanteNaoFiscal;
begin
  RetornaInfoECF( '12' );

  inherited CarregaComprovantesNaoFiscais;

  try
     N := Trunc(EscECFResposta.Params.Count / 2) - 1;
     For I := 0 to N do
     begin
       CNF := TACBrECFComprovanteNaoFiscal.create;
       CNF.Indice    := EscECFResposta.Params[ 2*I ] ;
       CNF.Descricao := EscECFResposta.Params[ 2*I + 1 ] ;

       fpComprovantesNaoFiscais.Add(CNF);
     end;
  except
     fpComprovantesNaoFiscais.Free ;
     fpComprovantesNaoFiscais := nil ;
     raise ;
  end;
end;

procedure TACBrECFEscECF.LerTotaisComprovanteNaoFiscal;
Var
  I, N :Integer ;
  CNF : TACBrECFComprovanteNaoFiscal;
begin
  if not Assigned( fpComprovantesNaoFiscais ) then
     CarregaComprovantesNaoFiscais ;

  RetornaInfoECF( '3' );

  N := Trunc(EscECFResposta.Params.Count / 3) - 1;
  For I := 0 to N do
  begin
    CNF := AchaCNFIndice( EscECFResposta.Params[ 3*I ] ) ;
    if Assigned( CNF ) then
    begin
       CNF.Contador := StrToInt( EscECFResposta.Params[ 3*I + 1 ] ) ;
       CNF.Total    := StrToInt( EscECFResposta.Params[ 3*I + 1 ] ) / 100 ;
    end;
  end;
end;

procedure TACBrECFEscECF.ProgramaComprovanteNaoFiscal(var Descricao: String;
  Tipo: String; Posicao : String );
var
   PosCNF : Integer;
begin
  if not Assigned( fpComprovantesNaoFiscais ) then
     CarregaComprovantesNaoFiscais ;

  Descricao := LeftStr(TiraAcentos( DecodificarPaginaDeCodigoECF(Descricao) ),15) ;
  if (Tipo = '-') then
     Tipo := 'S'
  else
     Tipo := 'E' ;

  EscECFComando.CMD := 85;
  if Posicao = '' then
     PosCNF := ComprovantesNaoFiscais.Count + 1
  else
     PosCNF := StrToInt( Posicao );
  EscECFComando.AddParamInteger( PosCNF ) ;
  EscECFComando.AddParamString( Descricao ) ;
  EscECFComando.AddParamString( Tipo ) ;
  EnviaComando;

  CarregaComprovantesNaoFiscais;
end;

procedure TACBrECFEscECF.LinhaCupomVinculado(Linha: AnsiString);
begin
  LinhaRelatorioGerencial(Linha);
end;

procedure TACBrECFEscECF.SegundaViaVinculado;
begin
  EscECFComando.CMD := 14;
  EnviaComando;
end;

procedure TACBrECFEscECF.ReimpressaoVinculado;
begin
  EscECFComando.CMD := 15;
  EnviaComando;
end;

procedure TACBrECFEscECF.FechaRelatorio;
begin
  EscECFComando.CMD := 10;
  EscECFComando.AddParamInteger( 1 );  // Aciona a Guilhotina
  EnviaComando;

  RespostasComando.AddField( 'COO',        EscECFResposta.Params[0] );
  RespostasComando.AddField( 'DataHora',   EscECFResposta.Params[1] );
  RespostasComando.AddField( 'VendaBruta', EscECFResposta.Params[2] );
  fsEmPagamento := false ;
  SalvaRespostasMemoria(False);
end;

procedure TACBrECFEscECF.CortaPapel(const CorteParcial: Boolean);
begin
  EscECFComando.CMD := 11;
  EnviaComando;
end;

function TACBrECFEscECF.GetNumCRZ: String;
begin
  RetornaInfoECF( '1|4' ) ;
  Result := EscECFResposta.Params[1] ;
end;

function TACBrECFEscECF.GetNumCFD: String;
begin
  RetornaInfoECF( '1|7' ) ;
  Result := EscECFResposta.Params[1] ;
end;

function TACBrECFEscECF.GetNumNCN: String;
begin
  RetornaInfoECF( '1|14' ) ;
  Result := EscECFResposta.Params[1] ;
end;

function TACBrECFEscECF.GetGrandeTotal: Double;
var
  StrValue: String;
begin
  RetornaInfoECF( '4|1' ) ;
  StrValue := EscECFResposta.Params[1] ;
  Result   := StrToInt( StrValue ) / 100;
end;

function TACBrECFEscECF.GetNumCCF: String;
begin
  RetornaInfoECF( '1|5' ) ;
  Result := EscECFResposta.Params[1] ;
end;

function TACBrECFEscECF.GetNumGNF: String;
begin
  RetornaInfoECF( '1|2' ) ;
  Result := EscECFResposta.Params[1] ;
end;

function TACBrECFEscECF.GetNumGRG: String;
begin
  RetornaInfoECF( '1|9' ) ;
  Result := EscECFResposta.Params[1] ;
end;

function TACBrECFEscECF.GetNumCDC: String;
begin
  RetornaInfoECF( '1|8' ) ;
  Result := EscECFResposta.Params[1] ;
end;

function TACBrECFEscECF.GetNumCFC: String;
begin
  RetornaInfoECF( '1|11' ) ;
  Result := EscECFResposta.Params[1] ;
end;

function TACBrECFEscECF.GetNumGNFC: String;
begin
  RetornaInfoECF( '1|10' ) ;
  Result := EscECFResposta.Params[1] ;
end;

function TACBrECFEscECF.GetNumCOOInicial: String;
begin
  RetornaInfoECF( '8' ) ;
  Result := EscECFResposta.Params[2] ;
end;

function TACBrECFEscECF.GetDadosUltimaReducaoZ : AnsiString ;
var
  DataStr, ECFCRZ  : String ;
  I: Integer;
  AliqZ : TACBrECFAliquota ;

  function AchaValorRegistrador(Registrador: String; Aliq: Double = 0): Double ;
  var
    I: Integer;
  begin
    I := 0 ; Result := 0;
    while  (I+2 < EscECFResposta.Params.Count) do
    begin
      if (EscECFResposta.Params[I] = Registrador) and
         (StringToFloatDef(EscECFResposta.Params[I+1],0) = Aliq) then
      begin
         Result := RoundTo( StrToFloatDef(EscECFResposta.Params[ I+2 ],0)/100, -2);
         Break;
      end ;

      Inc( I ) ;
    end ;
  end ;
begin
  // Zerar variaveis e inicializa Dados do ECF //
  InitDadosUltimaReducaoZ;

  if not Assigned( fpAliquotas ) then
    CarregaAliquotas ;

  with TACBrECF(fpOwner) do
  begin
    ECFCRZ := Trim(NumCRZ);
  end;

  RetornaInfoECF( '17|'+ECFCRZ ) ;

  // DEBUG
  //WriteToTXT('C:\TEMP\REDZ.TXT', EscECFResposta.Params.Text);

  { Alimenta a class com os dados atuais do ECF }
  with fpDadosReducaoZClass do
  begin
    CRZ              := StrToIntDef( EscECFResposta.Params[0], 0) ;
    DataStr          := EscECFResposta.Params[1];
    DataDoMovimento  := EncodeDate( StrToInt(copy(DataStr,5,4)),   // Ano
                                    StrToInt(copy(DataStr,3,2)),   // Mes
                                    StrToInt(copy(DataStr,1,2)) ); // Dia
    CRO              := StrToIntDef( EscECFResposta.Params[3], 0) ;
    NumeroCOOInicial := EscECFResposta.Params[4];
    COO              := StrToIntDef( EscECFResposta.Params[5], 0) ;
    ValorVendaBruta  := RoundTo( StrToFloatDef(EscECFResposta.Params[07],0)/100, -2);
    DescontoICMS     := RoundTo( StrToFloatDef(EscECFResposta.Params[08],0)/100, -2);
    AcrescimoICMS    := RoundTo( StrToFloatDef(EscECFResposta.Params[09],0)/100, -2);
    CancelamentoICMS := RoundTo( StrToFloatDef(EscECFResposta.Params[10],0)/100, -2);
    DescontoISSQN    := RoundTo( StrToFloatDef(EscECFResposta.Params[11],0)/100, -2);
    AcrescimoISSQN   := RoundTo( StrToFloatDef(EscECFResposta.Params[12],0)/100, -2);
    CancelamentoISSQN:= RoundTo( StrToFloatDef(EscECFResposta.Params[13],0)/100, -2);

    {Aliquotas}
    {Percorrendo as aliquotas cadastradas no ECF para procurar por todas}
    for I := 0 to Aliquotas.Count - 1 do
    begin
      AliqZ := TACBrECFAliquota.Create ;
      AliqZ.Assign( fpAliquotas[I] );
      {Procura pela aliquota no formato T/Snnnn na string}
      AliqZ.Total := AchaValorRegistrador( AliqZ.Tipo, AliqZ.Aliquota ) ;

      AdicionaAliquota( AliqZ );
    end ;

    SubstituicaoTributariaICMS := AchaValorRegistrador('F1') +
                                  AchaValorRegistrador('F2') +
                                  AchaValorRegistrador('F3') ;

    NaoTributadoICMS           := AchaValorRegistrador('N1') +
                                  AchaValorRegistrador('N2') +
                                  AchaValorRegistrador('N3') ;

    IsentoICMS                 := AchaValorRegistrador('I1') +
                                  AchaValorRegistrador('I2') +
                                  AchaValorRegistrador('I3') ;

    SubstituicaoTributariaISSQN:= AchaValorRegistrador('FS1') +
                                  AchaValorRegistrador('FS2') +
                                  AchaValorRegistrador('FS3') ;

    NaoTributadoISSQN          := AchaValorRegistrador('NS1') +
                                  AchaValorRegistrador('NS2') +
                                  AchaValorRegistrador('NS3') ;

    IsentoISSQN                := AchaValorRegistrador('IS1') +
                                  AchaValorRegistrador('IS2') +
                                  AchaValorRegistrador('IS3') ;

    CalculaValoresVirtuais;
    Result := MontaDadosReducaoZ;
  end;
end ;

function TACBrECFEscECF.GetVendaBruta: Double;
var
  StrValue: String;
begin
  RetornaInfoECF( '4|2' ) ;
  StrValue := EscECFResposta.Params[1] ;
  Result   := StrToInt( StrValue ) / 100;
end;

procedure TACBrECFEscECF.FechaNaoFiscal(Observacao: AnsiString;
   IndiceBMP: Integer);
var
  Obs: String;
begin
  Obs := Observacao ;

  if not Consumidor.Enviado then
     EnviaConsumidor(Obs);

  with EscECFComando do
  begin
     CMD := 18 ;
     AddParamInteger( 1 );  // Aciona a Guilhotina
     AddParamString( Obs );
  end ;
  EnviaComando ;

  RespostasComando.AddField( 'COO',        EscECFResposta.Params[0] );
  RespostasComando.AddField( 'DataHora',   EscECFResposta.Params[1] );
  RespostasComando.AddField( 'VendaBruta', EscECFResposta.Params[2] );
  fsEmPagamento := false ;
  SalvaRespostasMemoria(False);
end;

procedure TACBrECFEscECF.CancelaItemNaoFiscal(const AItem: Integer);
begin
   CancelaItemVendido( AItem );
end;

procedure TACBrECFEscECF.Sangria(const Valor: Double; Obs: AnsiString;
   DescricaoCNF: String; DescricaoFPG: String; IndiceBMP: Integer);
begin
  EscECFComando.CMD := 23;
  EscECFComando.AddParamInteger( 2 ) ;  // Sangria
  EscECFComando.AddParamDouble( Valor ) ;
  EscECFComando.AddParamString( Obs ) ;
  EnviaComando;

  RespostasComando.Clear;
  RespostasComando.AddField( 'COO',        EscECFResposta.Params[0] );
  RespostasComando.AddField( 'DataHora',   EscECFResposta.Params[1] );
  RespostasComando.AddField( 'VendaBruta', EscECFResposta.Params[2] );
  RespostasComando.AddField( 'NumSerie',   EscECFResposta.Params[3] );
  SalvaRespostasMemoria(False);
end;

procedure TACBrECFEscECF.Suprimento(const Valor: Double; Obs: AnsiString;
   DescricaoCNF: String; DescricaoFPG: String; IndiceBMP: Integer);
begin
  EscECFComando.CMD := 23;
  EscECFComando.AddParamInteger( 1 ) ;  // Fundo de Troco
  EscECFComando.AddParamDouble( Valor ) ;
  EscECFComando.AddParamString( Obs ) ;
  EnviaComando;

  RespostasComando.Clear;
  RespostasComando.AddField( 'COO',        EscECFResposta.Params[0] );
  RespostasComando.AddField( 'DataHora',   EscECFResposta.Params[1] );
  RespostasComando.AddField( 'VendaBruta', EscECFResposta.Params[2] );
  RespostasComando.AddField( 'NumSerie',   EscECFResposta.Params[3] );
  SalvaRespostasMemoria(False);
end;

function TACBrECFEscECF.EstornaCCD(const Todos: Boolean): Integer;
var
   UltimoCOO: Integer;
begin
  // TODO: Como ler os CCDs pendentes ??
  Result := 0 ;
  UltimoCOO := StrToInt( TACBrECF( fpOwner ).NumCOO );

  with EscECFComando do
  begin
     CMD := 13;
     AddParamInteger( UltimoCOO ) ;
     AddParamString(LeftStr(OnlyNumber(Consumidor.Documento),14)) ;
     AddParamString(LeftStr(Consumidor.Nome,30)) ;
     AddParamString(LeftStr(Consumidor.Endereco,79)) ;
  end;
  EnviaComando;

  RespostasComando.Clear;
  RespostasComando.AddField( 'COO',            EscECFResposta.Params[0] );
  RespostasComando.AddField( 'DataHora',       EscECFResposta.Params[1] );
  RespostasComando.AddField( 'VendaBruta',     EscECFResposta.Params[2] );
  RespostasComando.AddField( 'NumSerie',       EscECFResposta.Params[3] );
  RespostasComando.AddField( 'SeqPagto',       EscECFResposta.Params[4] );
  RespostasComando.AddField( 'NumParcela',     EscECFResposta.Params[5] );

  Consumidor.Enviado := True ;
  fsEmPagamento := false ;

  FechaRelatorio;
end;

function TACBrECFEscECF.GetTotalAcrescimos: Double;
var
  StrValue: String;
begin
  RetornaInfoECF( '4|8' ) ;
  StrValue := EscECFResposta.Params[1] ;
  Result   := StrToInt( StrValue ) / 100;
end;

function TACBrECFEscECF.GetTotalCancelamentos: Double;
var
  StrValue: String;
begin
  RetornaInfoECF( '4|3' ) ;
  StrValue := EscECFResposta.Params[1] ;
  Result   := StrToInt( StrValue ) / 100;
end;

function TACBrECFEscECF.GetTotalDescontos: Double;
var
  StrValue: String;
begin
  RetornaInfoECF( '4|4' ) ;
  StrValue := EscECFResposta.Params[1] ;
  Result   := StrToInt( StrValue ) / 100;
end;

function TACBrECFEscECF.GetTotalTroco: Double;
var
  StrValue: String;
begin
  RetornaInfoECF( '7|21' ) ;
  StrValue := EscECFResposta.Params[1] ;
  Result   := StrToInt( StrValue ) / 100;
end;

function TACBrECFEscECF.GetTotalIsencao: Double;
begin
  Result := GetValorTotalizador( 1, 3 ) +
            GetValorTotalizador( 2, 3 ) +
            GetValorTotalizador( 3, 3 ) ;
end;

function TACBrECFEscECF.GetTotalAcrescimosISSQN : Double ;
var
  StrValue: String;
begin
  RetornaInfoECF( '4|9' ) ;
  StrValue := EscECFResposta.Params[1] ;
  Result   := StrToInt( StrValue ) / 100;
end ;

function TACBrECFEscECF.GetTotalCancelamentosISSQN : Double ;
var
  StrValue: String;
begin
  RetornaInfoECF( '4|5' ) ;
  StrValue := EscECFResposta.Params[1] ;
  Result   := StrToInt( StrValue ) / 100;
end ;

function TACBrECFEscECF.GetTotalDescontosISSQN : Double ;
var
  StrValue: String;
begin
  RetornaInfoECF( '4|6' ) ;
  StrValue := EscECFResposta.Params[1] ;
  Result   := StrToInt( StrValue ) / 100;
end ;

function TACBrECFEscECF.GetTotalIsencaoISSQN : Double ;
begin
  Result := GetValorTotalizador( 1, 9 ) +
            GetValorTotalizador( 2, 9 ) +
            GetValorTotalizador( 3, 9 ) ;
end ;

function TACBrECFEscECF.GetTotalNaoTributadoISSQN : Double ;
begin
  Result := GetValorTotalizador( 1, 11 ) +
            GetValorTotalizador( 2, 11 ) +
            GetValorTotalizador( 3, 11 ) ;
end ;

function TACBrECFEscECF.GetTotalSubstituicaoTributariaISSQN : Double ;
begin
  Result := GetValorTotalizador( 1, 7 ) +
            GetValorTotalizador( 2, 7 ) +
            GetValorTotalizador( 3, 7 ) ;
end ;

function TACBrECFEscECF.GetTotalNaoTributado: Double;
begin
  Result := GetValorTotalizador( 1, 5 ) +
            GetValorTotalizador( 2, 5 ) +
            GetValorTotalizador( 3, 5 ) ;
end;

function TACBrECFEscECF.GetTotalSubstituicaoTributaria: Double;
begin
  Result := GetValorTotalizador( 1, 1 ) +
            GetValorTotalizador( 2, 1 ) +
            GetValorTotalizador( 3, 1 ) ;
end;

function TACBrECFEscECF.GetCNPJ: String;
begin
  Result := RetornaInfoECF( '15|8' ) ;
end;

function TACBrECFEscECF.GetIE: String;
begin
  Result := RetornaInfoECF( '15|9' ) ;
end;

function TACBrECFEscECF.GetIM: String;
begin
  Result := RetornaInfoECF( '15|10' ) ;
end;

function TACBrECFEscECF.GetCliche: AnsiString;
begin
  Result := RetornaInfoECF( '15|15' ) + sLineBreak +  // Razao Social
            RetornaInfoECF( '15|16' ) + sLineBreak +  // Nome Fantasia
            RetornaInfoECF( '15|17' ) ;               // Endere�o
end;

function TACBrECFEscECF.GetUsuarioAtual : String ;
begin
  Result := '001' ; // No conv�nio 09/09 n�o � mais poss�vel cadastrar outros usu�rios
end ;

function TACBrECFEscECF.GetDataHoraSB : TDateTime ;
var
   RetCmd: String;
begin
  EscECFComando.CMD := 140;
  EscECFComando.AddParamString( fsNumECF ) ;
  EnviaComando;

  RetCmd := EscECFResposta.Params[0] ;

  try
    Result := EncodeDateTime( StrToInt(copy(RetCmd,82,4)),   // Ano
                              StrToInt(copy(RetCmd,86,2)),   // Mes
                              StrToInt(copy(RetCmd,88,2)),   // Dia
                              StrToInt(copy(RetCmd,90,2)),   // Hora
                              StrToInt(copy(RetCmd,92,2)),   // Min
                              StrToInt(copy(RetCmd,94,2)),   // Seg
                              0 ) ;
  except
     // Notei que o ECF de Bamatech retorna a Data no formato DDMMAAAA
     Result := EncodeDateTime( StrToInt(copy(RetCmd,86,4)),   // Ano
                               StrToInt(copy(RetCmd,84,2)),   // Mes
                               StrToInt(copy(RetCmd,82,2)),   // Dia
                               StrToInt(copy(RetCmd,90,2)),   // Hora
                               StrToInt(copy(RetCmd,92,2)),   // Min
                               StrToInt(copy(RetCmd,94,2)),   // Seg
                               0 ) ;
  end ;
end ;

function TACBrECFEscECF.GetSubModeloECF: String;
begin
   Result := fsModeloECF;
end;

function TACBrECFEscECF.GetPAF: String;
begin
  Result := fsPAF ;
end;

function TACBrECFEscECF.GetDataMovimento: TDateTime;
var
   DataStr: String;
begin
  RetornaInfoECF( '8' ) ;
  DataStr := EscECFComando.Params[0];

  Result := EncodeDate( StrToInt(copy(DataStr,5,4)),   // Ano
                        StrToInt(copy(DataStr,3,2)),   // Mes
                        StrToInt(copy(DataStr,1,2)) ); // Dia
end;

procedure TACBrECFEscECF.LerTotaisAliquota;
begin
  CarregaAliquotas;
end;

function TACBrECFEscECF.AchaICMSAliquota(var AliquotaICMS: String
   ): TACBrECFAliquota;
Var
  AliquotaStr : String ;
begin
  AliquotaStr := '' ;
  Result      := nil ;

  if pos(copy(AliquotaICMS,1,2), 'TT,SS') > 0 then { Corrige Duplo T ou S }
     AliquotaICMS := Trim(Copy(AliquotaICMS,2,5));

  if copy(AliquotaICMS,1,2) = 'SF' then
     AliquotaStr := 'FS1'
  else if copy(AliquotaICMS,1,2) = 'SN' then
     AliquotaStr := 'NS1'
  else if copy(AliquotaICMS,1,2) = 'SI' then
     AliquotaStr := 'IS1'
  else if pos(copy(AliquotaICMS,1,2), 'IS|FS|NS') > 0 then
     AliquotaStr := copy(AliquotaICMS,1,2) +
                    ifthen( AliquotaICMS[3] in ['1'..'3'],AliquotaICMS[3],'1' )
  else
     case AliquotaICMS[1] of
        'F','I','N' : AliquotaStr  := AliquotaICMS[1] +
                        ifthen( AliquotaICMS[2] in ['1'..'3'],AliquotaICMS[2],'1' ) ;
        'T','S'     : AliquotaStr  := AliquotaICMS[1] +
                        padR( Trim( Copy(AliquotaICMS,2,2) ), 2, '0' );
     end ;

  if AliquotaStr = '' then
     Result := inherited AchaICMSAliquota( AliquotaICMS )
  else
     AliquotaICMS := AliquotaStr ;
end;

end.

{ Observa�oes:

- Registro E01 do comando 139 traz a Data de Sw.Basico no formato DDMMAAAA quando o correto � AAAAMMDD

- N�o encontrado:
-- Num.Loja
-- Total Acrescimos OPNF
-- Total Cancelamentos OPNF
-- Total Descontos OPNF
}


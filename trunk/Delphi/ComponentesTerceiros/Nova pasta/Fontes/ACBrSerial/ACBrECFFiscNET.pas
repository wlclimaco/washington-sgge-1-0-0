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

{$I ACBr.inc}

unit ACBrECFFiscNET ;

interface
uses ACBrECFClass, ACBrDevice, ACBrUtil,
     Classes;
type

TACBrECFFiscNETComando = class
  private
    fsNomeComando : String ;
    fsParams  : TStringList ;
    fsCont    : Byte ;
    fsTimeOut: Integer;

    function GetComando: AnsiString;
    procedure SetNomeComando(const Value: String);
 public
    constructor create ;
    destructor destroy ; override ;

    property NomeComando : String  write SetNomeComando ;
    property TimeOut     : Integer read fsTimeOut write fsTimeOut ;
    property Comando     : AnsiString  read GetComando ;
    property Params      : TStringList read fsParams ;

    Procedure AddParamString(ParamName : String; AString  : AnsiString) ;
    Procedure AddParamInteger(ParamName : String; AInteger : Integer) ;
    Procedure AddParamDouble(ParamName : String; ADouble  : Double) ;
    Procedure AddParamBool(ParamName : String; ABool    : Boolean) ;
    Procedure AddParamDateTime(ParamName : String; ADateTime: TDateTime;Tipo : Char = 'D'  ) ;
 end ;

TACBrECFFiscNETResposta = class
  private
    fsParams  : TStringList ;
    fsCont: Byte;
    fsCodRetorno: Integer;
    fsTamanho: Integer;
    fsResposta : AnsiString ;

    procedure SetResposta(const Value: AnsiString);
 public
    constructor create ;
    destructor destroy ; override ;

    property Resposta   : AnsiString  read fsResposta write SetResposta ;
    property Cont       : Byte        read fsCont;
    property CodRetorno : Integer     read fsCodRetorno ;
    property Params     : TStringList read fsParams ;
    property Tamanho    : Integer     read fsTamanho ;
 end ;

{ Classe filha de TACBrECFClass com implementa�ao para FiscNET }

{ TACBrECFFiscNET }

TACBrECFFiscNET = class( TACBrECFClass )
 private
    fsNumVersao : String ;
    fsNumECF    : String ;
    fsNumLoja   : String ;
    fsPAF       : String ;
    fsBaseTotalDiaMeioPagamento : Integer ;
    fsBaseTotalDiaNaoFiscal     : Integer ;
    fsArredonda : Integer ;
    fsFiscNETComando: TACBrECFFiscNETComando;
    fsFiscNETResposta: TACBrECFFiscNETResposta;
    fsComandoVendeItem : String ;
    fsComandosImpressao : array[0..10] of AnsiString ;
    fsEmPagamento : Boolean ;
    fsMarcaECF : String ;
    fsModeloECF: String ;

    //dataregis | termoprinter
    xGera_PAF                       : Function ( ComPort     : AnsiString;
                                                 Modelo      : AnsiString;
                                                 RegFileName : AnsiString;
                                                 COOInicial  : AnsiString;
                                                 COOFinal    : AnsiString) : integer; stdcall;
    xGera_AtoCotepe1704_Periodo_MFD : Function ( ComPort            : AnsiString;
                                                 Modelo             : AnsiString;
                                                 RegFileName        : AnsiString;
                                                 DataReducaoInicial : AnsiString;
                                                 DataReducaoFinal   : AnsiString) : integer; stdcall;
    xGera_AtoCotepe1704_Periodo_MF : Function ( ComPort            : AnsiString;
                                                 Modelo             : AnsiString;
                                                 RegFileName        : AnsiString;
                                                 DataReducaoInicial : AnsiString;
                                                 DataReducaoFinal   : AnsiString) : integer; stdcall;

    // urano e demais
    xDLLReadLeMemorias : function (szPortaSerial, szNomeArquivo,
       szSerieECF: AnsiString; bAguardaConcluirLeitura : Char) : Integer; stdcall;

    xDLLATO17GeraArquivo : function (szArquivoBinario, szArquivoTexto, szPeriodoIni,
       szPeriodoFIM: AnsiString; TipoPeriodo: Char;
       szUsuario, szTipoLeitura: AnsiString) : Integer; stdcall;

    //Elgin
    xElgin_AbrePortaSerial  : function : Integer; StdCall;
    XElgin_FechaPortaSerial : function : Integer; StdCall;
    xElgin_DownloadMFD      : function(Arquivo          : AnsiString;
                                       TipoDownload     : AnsiString;
                                       ParametroInicial : AnsiString;
                                       ParametroFinal   : AnsiString;
                                       UsuarioECF       : AnsiString) : Integer; StdCall;
    xElgin_FormatoDadosMFD  : function(ArquivoOrigem    : AnsiString;
                                       ArquivoDestino   : AnsiString;
                                       TipoFormato      : AnsiString;
                                       TipoDownload     : AnsiString;
                                       ParametroInicial : AnsiString;
                                       ParametroFinal   : AnsiString;
                                       UsuarioECF       : AnsiString) : Integer; StdCall;
    xElgin_LeMemoriasBinario : function(Arquivo: AnsiString;
                                        NumSerie: AnsiString;
                                        AguardaLeitura: Boolean) : Integer; StdCall;
    xElgin_GeraArquivoATO17Binario : function(ArquivoBinario: AnsiString;
                                              ArquivoTexto: AnsiString;
                                              PeriodoIni: AnsiString;
                                              PeriodoFim: AnsiString;
                                              TipoPeriodo :AnsiChar;
                                              UsuarioECF: AnsiString;
                                              TipoLeitura: AnsiString) : Integer; StdCall;

    Function LeMoeda(const Registrador: String): Double;
    Function LeInteiro(const Registrador: String): Integer;
    Function LeTexto(const Registrador: String): String;

    procedure LoadDLLFunctions;
    procedure AbrePortaSerialDLL(const Porta, Path : String ) ;

    Procedure PreparaCmd( cmd : AnsiString ) ;
    Function AjustaLeitura( AString : AnsiString ) : AnsiString ;
    function DocumentosToStr(Documentos: TACBrECFTipoDocumentoSet): String;
    function GetErroAtoCotepe1704(pRet: Integer): string;

 protected
    function GetDataHora: TDateTime; override ;
    function GetNumCupom: String; override ;
    function GetNumCCF: String; override ;
    function GetNumECF: String; override ;
    function GetNumLoja: String; override ;
    function GetNumSerie: String; override ;
    function GetNumSerieMFD: String; override ;
    function GetNumVersao: String; override ;
    function GetSubTotal: Double; override ;
    function GetTotalPago: Double; override ;

    function GetEstado: TACBrECFEstado; override ;
    function GetGavetaAberta: Boolean; override ;
    function GetPoucoPapel : Boolean; override ;
    function GetHorarioVerao: Boolean; override ;
    function GetParamDescontoISSQN: Boolean; override ;
    function GetChequePronto: Boolean; override ;
    function GetArredonda: Boolean; override ;

    function GetTipoUltimoDocumento : TACBrECFTipoDocumento ; override ;

    function GetCNPJ: String; override ;
    function GetIE: String; override ;
    function GetIM: String; override ;
    function GetCliche: AnsiString; override ;
    function GetUsuarioAtual: String; override ;
    function GetDataHoraSB: TDateTime; override ;
    function GetSubModeloECF: String ; override ;
    
    function GetDataMovimento: TDateTime; override ;
    function GetGrandeTotal: Double; override ;
    function GetNumCRO: String; override ;
    function GetNumGRG: String; override ;
    function GetNumGNF: String; override ;
    function GetNumCDC: String; override ;    
    function GetNumCFC: String; override ;    
    function GetNumCRZ: String; override ;
    function GetVendaBruta: Double; override ;
    function GetTotalTroco: Double; override ;

    function GetTotalAcrescimos: Double; override ;
    function GetTotalCancelamentos: Double; override ;
    function GetTotalDescontos: Double; override ;
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

    function GetDadosUltimaReducaoZ: AnsiString; override ;    

    function GetPAF: String; override ;

    Function VerificaFimLeitura(var Retorno: AnsiString;
       var TempoLimite: TDateTime) : Boolean ; override ;
    function GetNumReducoesZRestantes: String; override;

 public
    Constructor create( AOwner : TComponent  )  ;
    Destructor Destroy  ; override ;

    procedure Ativar ; override ;

    property FiscNETComando : TACBrECFFiscNETComando  read fsFiscNETComando ;
    property FiscNETResposta: TACBrECFFiscNETResposta read fsFiscNETResposta ;

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

    { Procedimentos de Cupom N�o Fiscal }
    Procedure AbreNaoFiscal( CPF_CNPJ: String = ''; Nome: String = '';
       Endereco: String = '' ) ; override ;
    Procedure RegistraItemNaoFiscal( CodCNF : String; Valor : Double;
       Obs : AnsiString = '') ; override ;
    procedure NaoFiscalCompleto(CodCNF: String; Valor: Double;
      CodFormaPagto: String; Obs: AnsiString; IndiceBMP : Integer = 0); override ;

    Function EstornaCCD( const Todos: Boolean = True) : Integer; override ;

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
    Procedure CortaPapel( const CorteParcial : Boolean = false) ; override ;

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
    Procedure LeituraMFDSerial( DataInicial, DataFinal : TDateTime;
       Linhas : TStringList; Documentos : TACBrECFTipoDocumentoSet = [docTodos] ) ; overload ; override ;
    Procedure LeituraMFDSerial( COOInicial, COOFinal : Integer;
       Linhas : TStringList; Documentos : TACBrECFTipoDocumentoSet = [docTodos] ) ; overload ; override ;

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
       TipoContador: TACBrECFTipoContador = tpcCOO  ) ; override ;

    Procedure ImprimeCheque(Banco : String; Valor : Double ; Favorecido,
       Cidade : String; Data : TDateTime ;Observacao : String = '') ; override ;
    Procedure CancelaImpressaoCheque ; override ;
    Function LeituraCMC7 : AnsiString ; override ;
    
    Procedure AbreGaveta ; override ;

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

    procedure CarregaRelatoriosGerenciais ; override ;
    procedure LerTotaisRelatoriosGerenciais ; override ;
    Procedure ProgramaRelatorioGerencial( var Descricao: String;
       Posicao : String = '') ; override ;
       
    procedure CarregaComprovantesNaoFiscais ; override ;
    procedure LerTotaisComprovanteNaoFiscal ; override ;
    Procedure ProgramaComprovanteNaoFiscal( var Descricao: String;
       Tipo : String = ''; Posicao : String = '') ; override ;

    procedure IdentificaPAF( NomeVersao, MD5 : String) ; override ;
    function TraduzirTag(const ATag: AnsiString): AnsiString; override;
 end ;

implementation
Uses ACBrECF, ACBrConsts,
     {$IFDEF COMPILER6_UP} DateUtils, StrUtils{$ELSE} ACBrD5, Windows{$ENDIF},
     SysUtils, Math, IniFiles ;

{ -------------------------  TACBrECFFiscNETComando -------------------------- }
constructor TACBrECFFiscNETComando.create;
begin
  inherited create ;

  fsParams := TStringList.create ;
  fsCont   := 0 ;
end;

destructor TACBrECFFiscNETComando.destroy;
begin
  fsParams.Free ;

  inherited destroy ;
end;

procedure TACBrECFFiscNETComando.SetNomeComando(const Value: String);
begin
  if fsCont >= 250 then
     fsCont := 1
  else
     Inc( fsCont ) ;

  fsNomeComando := Trim(Value) ;
  fsTimeOut     := 0 ;
  fsParams.Clear ;
end;

function TACBrECFFiscNETComando.GetComando: AnsiString;
var
  I: Integer;
begin
  Result := '{'+IntToStr(fsCont)+';'+fsNomeComando+';' ;

  For I := 0 to fsParams.Count-1 do
    Result := Result + Trim(fsParams[I]) + ' ';
  Result := Trim(Result) ;

  Result := Result + ';'+IntToStr(Length(Result))+'}' ;
end;

procedure TACBrECFFiscNETComando.AddParamString(ParamName: String;
  AString: AnsiString);
var
  Buf : AnsiString ;
begin
  if AString = '' then exit ;

  AString := StringReplace(AString,'\','\\',[rfReplaceAll]) ;
  AString := StringReplace(AString,'"','\"',[rfReplaceAll]) ;
  { Restaurando caracteres de controle }
  AString := StringReplace(AString,'\\x','\x',[rfReplaceAll]) ;

  Buf := BinaryStringToString(AString);

  fsParams.Add( ParamName + '="' + TrimRight( Buf ) + '"' ) ;
end;

procedure TACBrECFFiscNETComando.AddParamDouble(ParamName: String;
  ADouble: Double);
var
  AFloatStr: string;
begin
  ADouble   := RoundTo(ADouble,-4) ; // FiscNet aceita no m�ximo 4 casas decimais
  AFloatStr := FloatToStr(ADouble) ;
  // FiscNET sempre usar "," como separador de decimal //
  AFloatStr := StringReplace(AFloatStr,DecimalSeparator,',',[rfReplaceAll]) ;
  fsParams.Add(ParamName + '=' + AFloatStr ) ;
end;

procedure TACBrECFFiscNETComando.AddParamInteger(ParamName: String;
  AInteger: Integer);
begin
  fsParams.Add(ParamName + '=' + IntToStr(AInteger) )
end;

procedure TACBrECFFiscNETComando.AddParamBool(ParamName: String;
  ABool: Boolean);
var
  CharBool: Char;
begin
  if ABool then
    CharBool := 't'
  else
    CharBool := 'f';

  fsParams.Add(ParamName + '=' + CharBool )
end;

Procedure TACBrECFFiscNETComando.AddParamDateTime(ParamName : String;
  ADateTime: TDateTime;Tipo : Char = 'D'  ) ;
var
  Texto: string;
begin
  if Tipo in ['T','H'] then
     Texto := FormatDateTime('hh:nn:ss',ADateTime)
  else
     Texto := FormatDateTime('dd/mm/yyyy',ADateTime) ;

  fsParams.Add(ParamName + '=' + '#'+Texto+'#' )
end;

{ ------------------------- TACBrECFFiscNETResposta -------------------------- }

constructor TACBrECFFiscNETResposta.create;
begin
  inherited create ;

  fsParams     := TStringList.create ;
  fsCont       := 0 ;
  fsCodRetorno := 0;
  fsTamanho    := 0;
  fsResposta   := '' ;
end;

destructor TACBrECFFiscNETResposta.destroy;
begin
  fsParams.Free ;
  inherited destroy ;
end;

procedure TACBrECFFiscNETResposta.SetResposta(const Value: AnsiString);
var
  Buf: AnsiString;
  P,I : Integer ;
  Param : AnsiString ;
  CharAposIgual : AnsiChar ;
begin
  fsParams.Clear ;
  fsCont       := 0 ;
  fsCodRetorno := 0;
  fsTamanho    := 0;
  fsResposta   := '' ;

  if Value = '' then exit ;

  fsResposta := Value ;
  Buf        := copy(fsResposta,2,Length(fsResposta)-2) ;  //  Remove "{"  "}"

  P := PosLast(';',Buf) ;
  fsTamanho := StrToIntDef(copy(Buf,P+1,Length(Buf)),0) ;
  Buf       := copy(Buf,1,P-1) ;  // Remove tamanho

  P := pos(';',Buf) ;
  try
     fsCont := StrToInt( copy(Buf,1,(P-1)) ) ;
  except
     raise EACBrECFERRO.Create(ACBrStr('Num.Identifica��o inv�lido')) ;
  end ;
  Buf := copy(Buf,P+1,Length(Buf)) ;  // Remove a Ident.

  P := pos(';',Buf) ;
  try
     fsCodRetorno := StrToInt( copy(Buf,1,(P-1)) ) ;
  except
     raise EACBrECFERRO.Create(ACBrStr('Cod.Retorno inv�lido')) ;
  end ;
  Buf := Trim(copy(Buf,P+1,Length(Buf))) ;  // Remove Retorno

  if Buf = '' then
     exit ;

  Buf := StringReplace(Buf,'\"','\x22',[rfReplaceAll]) ;  // Tira aspas internas
  // Tem Parametros ? //
  P := pos('=',Buf) ;
  while P > 0 do
  begin
     try
        CharAposIgual := Buf[P+1] ;
        case CharAposIgual of
           '#' : P := PosAt('#',Buf,2) ;
           '"' : P := PosAt('"',Buf,2) ;
        else
           P := Pos(' ',Buf) ;
        end ;
     except
        CharAposIgual := ' ';
        P := 0 ;
     end ;

     if P = 0 then
        P := Length(Buf) ;

     Param := Trim(copy(Buf,1,P)) ;
     Buf   := Trim(copy(Buf,P+1,Length(Buf))) ;  // Restante

     if CharAposIgual in ['"','#'] then   // � parametro Texto ou Data/Hora ?
     begin
        I := pos('=',Param) ;
        { removendo as " ou # }
        Param := copy(Param,1,I) + copy(Param,I+2,(Length(Param)-I-2) ) ;

        { Verificando por codigos em Hexa }
        Param := StringToBinaryString(Param);
     end ;

     fsParams.Add(Param) ;

     P := pos('=',Buf) ;
  end ;
end;

{ ----------------------------- TACBrECFFiscNET ----------------------------- }

constructor TACBrECFFiscNET.create( AOwner : TComponent ) ;
begin
  inherited create( AOwner ) ;

  fsFiscNETComando   := TACBrECFFiscNETComando.create ;
  fsFiscNETResposta  := TACBrECFFiscNETResposta.create ;

  fpDevice.HandShake := hsRTS_CTS ;
  fpDevice.Baud      := 115200 ;
  fpDevice.Parity    := pEven ;

  (*
     Para funcionar na TermoPrinter use as configura��es abaixo...
     Vc pode programar essas caracteristicas em tempo de execu��o no
     ACBrECF.Device ap�s ajustar o Modelo e antes de Ativar... Exemplo:

     ACBrECF1.Modelo           := ecfFiscNET ;
     ACBrECF1.Device.Baud      := 9600 ;
     ACBrECF1.Device.Parity    := pNone ;
     ACBrECF1.Device.HandShake := hsDTR_DSR ;
     ACBrECF1.Ativar ;
  *)


  { Variaveis internas dessa classe }
  fsNumVersao := '' ;
  fsNumECF    := '' ;
  fsNumLoja   := '' ;
  fsPAF       := '' ;
  fsBaseTotalDiaMeioPagamento := 99;
  fsBaseTotalDiaNaoFiscal     := 99;
  fsArredonda := -1 ;
  fsComandoVendeItem := '' ;
  fsEmPagamento := false ;
  fsMarcaECF    := '';
  fsModeloECF   := '';
  
  fpModeloStr := 'FiscNET' ;
  fpPaginaDeCodigo := 850 ;
  fpColunas   := 57 ;
  fpMFD       := True ;
  fpTermica   := True ;
  fpIdentificaConsumidorRodape := True ;

  { Criando Lista de String com comandos de Impressao a Remover de Leituras }
  fsComandosImpressao[0]  := #27 + 'E1';
  fsComandosImpressao[1]  := #27 + 'E0';
  fsComandosImpressao[2]  := #27 + 'E';
  fsComandosImpressao[3]  := #27 + 'F';
  fsComandosImpressao[4]  := #27 + '!(';
  fsComandosImpressao[5]  := #27 + '!' + #1 ;
  fsComandosImpressao[6]  := #27 + '!' + #2 ;
  fsComandosImpressao[7]  := #27 + 'W1';
  fsComandosImpressao[8]  := #27 + 'W0';
  fsComandosImpressao[9]  := #30 ;
  fsComandosImpressao[10] := #12 ;
end;

destructor TACBrECFFiscNET.Destroy;
begin
  fsFiscNETComando.Free ;
  fsFiscNETResposta.Free ;

  inherited Destroy ;
end;

procedure TACBrECFFiscNET.Ativar;
begin
  if not fpDevice.IsSerialPort  then
     raise EACBrECFERRO.Create(ACBrStr('A impressora: '+fpModeloStr+' requer'+sLineBreak+
                            'Porta Serial:  (COM1, COM2, COM3, ...)'));

  inherited Ativar ; { Abre porta serial }

  fsNumVersao := '' ;
  fsNumECF    := '' ;
  fsNumLoja   := '' ;
  fsArredonda := -1 ;
  fsComandoVendeItem := '' ;
  fsMarcaECF  := '' ;
  fsModeloECF := '' ;
  fsBaseTotalDiaMeioPagamento := 99;
  fsBaseTotalDiaNaoFiscal     := 99;

  { FiscNET sempre aceita at� 3 decimais na QTD e PrecoUnit }
  fpDecimaisQtd   := 3 ;
  fpDecimaisPreco := 3 ;

  try
     if NumVersao = '' then
        raise EACBrECFNaoInicializado.Create( ACBrStr(
                 'Erro inicializando a impressora '+fpModeloStr ));

     GetPAF ;

     fsMarcaECF  := LeTexto( 'Marca' );
     fpModeloStr := 'FiscNET: '+ fsMarcaECF ;
     fsMarcaECF  := LowerCase(Trim(fsMarcaECF)) ;
     fsModeloECF := LeTexto( 'Modelo' );

     fpModeloStr := fpModeloStr + ' - ' + fsModeloECF ;

     // Ajuste de Colunas para modelos Espec�ficos //
     if (fsModeloECF = 'TPF2001') then
        fpColunas := 40
     else if (pos(fsModeloECF, 'X5|3202DT|ELGIN FIT|ELGIN K') > 0) then
        fpColunas := 48;

  except
     Desativar ;
     raise ;
  end ;
end;


Function TACBrECFFiscNET.EnviaComando_ECF( cmd : AnsiString = '' ) : AnsiString ;
var
  ErroMsg: string;
  OldTimeOut : Integer ;
begin
  if cmd <> '' then
     PreparaCmd(cmd) ;  // Ajusta e move para FiscNETcomando

  cmd := FiscNETComando.Comando ;

  Result  := '' ;
  ErroMsg := '' ;
  fpComandoEnviado   := '' ;
  fpRespostaComando  := '' ;
  FiscNETResposta.Resposta := '' ;
  OldTimeOut := TimeOut ;
  TimeOut    := max(FiscNETComando.TimeOut, TimeOut) ;

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
        FiscNETResposta.Resposta := fpRespostaComando ;

        ErroMsg := '' ;
        if FiscNETResposta.CodRetorno > 0 then
           ErroMsg := 'Erro: '+IntToStr(FiscNETResposta.CodRetorno) + ' - ' +
                      FiscNETResposta.Params.Values['NomeErro'] + #10 +
                      FiscNETResposta.Params.Values['Circunstancia'] ;
     except
        On E : Exception do
        begin
           ErroMsg := 'Resposta do ECF inv�lida' + sLineBreak + E.Message ;
        end ;
     end ;

     if ErroMsg <> '' then
      begin
        ErroMsg := ACBrStr('Erro retornado pela Impressora: '+fpModeloStr+
                           sLineBreak+sLineBreak + ErroMsg ) ;

        if (FiscNETResposta.CodRetorno = 7003) or
           (FiscNETResposta.CodRetorno = 7004) or
           (FiscNETResposta.CodRetorno = 15011) then
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

Procedure TACBrECFFiscNET.PreparaCmd(cmd: AnsiString) ;
var
  P: Integer;
begin
  P := pos(';',cmd) ;
  if P = 0 then
     P := Length(cmd)+1 ;
  FiscNETComando.NomeComando := copy(cmd,1,P-1) ;
  FiscNETComando.Params.Text := copy(cmd,P+1,Length(cmd)) ;
end;

Function TACBrECFFiscNET.VerificaFimLeitura(var Retorno: AnsiString;
   var TempoLimite: TDateTime) : Boolean ;
begin
  Result := (RightStr(Retorno,1) = '}') and (CountStr(Retorno,';') >= 3) ; 
end;

function TACBrECFFiscNET.GetDataHora: TDateTime;
var
  RetCmd: AnsiString;
  OldShortDateFormat : String ;
begin
  FiscNETComando.NomeComando := 'LeData' ;
  FiscNETComando.AddParamString('NomeData','Data');
  EnviaComando ;
  RetCmd := FiscNETResposta.Params.Values['ValorData'] ;
  RetCmd := StringReplace(RetCmd ,'/',DateSeparator, [rfReplaceAll] );
  OldShortDateFormat := ShortDateFormat ;
  try
     ShortDateFormat := 'dd/mm/yyyy' ;
     Result := StrToDate( RetCmd ) ;
  finally
     ShortDateFormat := OldShortDateFormat ;
  end ;

  try
     FiscNETComando.NomeComando := 'LeHora' ;
     FiscNETComando.AddParamString('NomeHora','Hora');
     EnviaComando ;
     RetCmd := FiscNETResposta.Params.Values['ValorHora'] ;
  except
     if fsNumVersao = '01.00.01' then   // Versao 01.00.01 da TermoPrinter n�o l� hora
        RetCmd := FormatDateTime('hh:mm:ss',Now)
     else
        raise ;
  end ;

  Result := RecodeHour(  Result,StrToInt(copy(RetCmd,1,2))) ;
  Result := RecodeMinute(Result,StrToInt(copy(RetCmd,4,2))) ;
  Result := RecodeSecond(Result,StrToInt(copy(RetCmd,7,2))) ;
end;

function TACBrECFFiscNET.GetNumCupom: String;
begin
  Result := IntToStrZero( LeInteiro( 'COO' ), 6) ;
end;

function TACBrECFFiscNET.GetNumCCF: String;
begin
  Result := IntToStrZero( LeInteiro( 'CCF' ), 6) ;
end;

function TACBrECFFiscNET.GetNumReducoesZRestantes: String;
begin
  Result := IntToStrZero( LeInteiro( 'CRZRestantes' ), 6) ;
end;

function TACBrECFFiscNET.GetNumCRO: String;
begin
  Result := IntToStrZero( LeInteiro( 'CRO' ), 6) ;
end;

function TACBrECFFiscNET.GetNumGRG: String;
begin
  Result := IntToStrZero( LeInteiro( 'GRG' ), 6) ;
end;

function TACBrECFFiscNET.GetNumGNF: String;
begin
  Result := IntToStrZero( LeInteiro( 'GNF' ), 6) ;
end;

function TACBrECFFiscNET.GetNumCDC: String;
begin
  Result := IntToStrZero( LeInteiro( 'CDC' ), 6) ;
end;

function TACBrECFFiscNET.GetNumCFC: String;
begin
  Result := IntToStrZero( LeInteiro( 'CFC' ), 6) ;
end;

function TACBrECFFiscNET.GetNumLoja: String;
begin
  if fsNumLoja = '' then
     fsNumLoja := IntToStrZero( LeInteiro( 'Loja' ), 3) ;

  Result := fsNumLoja ;
end;

function TACBrECFFiscNET.GetNumECF: String;
begin
  if fsNumECF = '' then
     fsNumECF := IntToStrZero( LeInteiro( 'ECF' ), 5) ;

  Result := fsNumECF ;
end;

function TACBrECFFiscNET.GetNumSerie: String;
begin
  Result := LeTexto( 'NumeroSerieECF' );
end;

function TACBrECFFiscNET.GetNumSerieMFD: String;
begin
  Result := LeTexto( 'NumeroSerieMFD' );
end;

function TACBrECFFiscNET.GetNumVersao: String ;
begin
  if fsNumVersao = '' then
     fsNumVersao := LeTexto( 'VersaoSW' );

  Result := fsNumVersao ;
end;

function TACBrECFFiscNET.GetTotalPago: Double;
begin
  Result := LeMoeda( 'TotalDocValorPago' ) ;
end;

function TACBrECFFiscNET.GetSubTotal: Double;
begin
  Result := LeMoeda( 'TotalDocLiquido' ) ;
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
function TACBrECFFiscNET.GetEstado: TACBrECFEstado;
var
  Est, Ind: Integer;
begin
  Result := fpEstado ;  // Suprimir Warning
  try
    fpEstado := estNaoInicializada ;
    if (not fpAtivo) then
      exit ;

    fpEstado := estDesconhecido ;

    Est := LeInteiro( 'EstadoFiscal' );
    case Est of
      1      : fpEstado := estLivre ;
      2      : fpEstado := estVenda ;
      4,8,16 : fpEstado := estPagamento ;
      32,64  : fpEstado := estRelatorio ;
      128    : fpEstado := estNaoFiscal ;
    end ;

    if fsEmPagamento and (fpEstado = estVenda) then
       fpEstado := estPagamento ;

    if fpEstado in [estDesconhecido, estLivre] then
    begin
      Ind := LeInteiro('Indicadores');

      if TestBit(Ind,5) then
         fpEstado := estBloqueada

      else if TestBit(Ind,7) then
         fpEstado := estRequerZ

//    else if not TestBit(Ind,6) then
//       fpEstado := estRequerX

    end ;
  finally
    Result := fpEstado ;
  end ;
end;

function TACBrECFFiscNET.GetGavetaAberta: Boolean;
begin
  FiscNETComando.NomeComando := 'LeIndicador' ;
  FiscNETComando.AddParamString('NomeIndicador','SensorGaveta') ;
  EnviaComando ;

  Result := (FiscNETResposta.Params.Values['ValorNumericoIndicador'] = '1');
end;

function TACBrECFFiscNET.GetPoucoPapel: Boolean;
begin
  try
     FiscNETComando.NomeComando := 'LeIndicador' ;
     FiscNETComando.AddParamString('NomeIndicador','SensorPoucoPapel') ;
     EnviaComando ;
     Result := (FiscNETResposta.Params.Values['ValorNumericoIndicador'] = '1');
  except
     if fsNumVersao = '01.00.01' then   // Versao 01.00.01 da TermoPrinter n�o l� SensorPoucoPapel
        Result := False
     else
        raise ;
  end ;
end;

function TACBrECFFiscNET.GetHorarioVerao: Boolean;
begin
  try
     FiscNETComando.NomeComando := 'LeIndicador' ;
     FiscNETComando.AddParamString('NomeIndicador','HorarioVerao') ;
     EnviaComando ;
     Result := (FiscNETResposta.Params.Values['ValorNumericoIndicador'] = '1') ;
  except
     if fsNumVersao = '01.00.01' then   // Versao 01.00.01 da TermoPrinter n�o l� HorarioVerao
        Result := False
     else
        raise ;
  end ;
end;

Procedure TACBrECFFiscNET.LeituraX ;
begin
  FiscNETComando.NomeComando := 'EmiteLeituraX' ;
  FiscNETComando.TimeOut     := 30 ;
  FiscNETComando.AddParamString('Destino','I') ;
  FiscNETComando.AddParamString('Operador',Operador) ;
  EnviaComando ;
end;

procedure TACBrECFFiscNET.LeituraXSerial(Linhas: TStringList);
Var
  Leitura, RetCmd : AnsiString ;
begin
  with FiscNETComando do
  begin
     NomeComando := 'EmiteLeituraX' ;
     AddParamString('Destino','S') ;
     AddParamString('Operador',Operador) ;
  end ;
  FiscNETComando.TimeOut := 10 ;
  EnviaComando ;
  Sleep(500);

  Leitura := '' ;
  Linhas.Clear ;
  repeat
     FiscNETComando.NomeComando := 'LeImpressao' ;
     EnviaComando ;

     RetCmd  := FiscNETResposta.Params.Values['TextoImpressao'] ;
     Leitura := Leitura + RetCmd ;
     sleep(100) ;
  until (RetCmd = '') ;

  Linhas.Text := AjustaLeitura( Leitura );
end;


Procedure TACBrECFFiscNET.AbreGaveta ;
begin
  FiscNETComando.NomeComando := 'AbreGaveta' ;
  EnviaComando ;
end;

Procedure TACBrECFFiscNET.ReducaoZ(DataHora: TDateTime) ;
begin
  FiscNETComando.NomeComando := 'EmiteReducaoZ' ;
  FiscNETComando.TimeOut     := 900 ;
  if DataHora <> 0 then
     FiscNETComando.AddParamDateTime('Hora',DataHora,'T') ;
  FiscNETComando.AddParamString('Operador',Operador) ;

  try
     EnviaComando ;
  except
     on E : Exception do
     begin
        if (pos('8092',E.Message) <> 0) then   // Erro de Hora fora da faixa ?
           ReducaoZ(0)                         // Tenta sem DataHora
        else
           raise ;
     end ;
  end ;
end;

Procedure TACBrECFFiscNET.MudaHorarioVerao ;
begin
  MudaHorarioVerao( not HorarioVerao ) ;
end;

procedure TACBrECFFiscNET.MudaHorarioVerao(EHorarioVerao: Boolean);
begin
  FiscNETComando.NomeComando := 'AcertaHorarioVerao' ;
  FiscNETComando.AddParamInteger('EntradaHV', IfThen(EHorarioVerao,1,0)) ;
  EnviaComando ;
end;

procedure TACBrECFFiscNET.AbreCupom ;
begin
  FiscNETComando.NomeComando := 'AbreCupomFiscal' ;
  FiscNETComando.TimeOut     := 10 ;
  FiscNETComando.AddParamString('IdConsumidor',LeftStr(Consumidor.Documento,29)) ;
  if Consumidor.Nome <> '' then
     FiscNETComando.AddParamString('NomeConsumidor',LeftStr(Consumidor.Nome,30)) ;
  if Consumidor.Endereco <> '' then
     FiscNETComando.AddParamString('EnderecoConsumidor',LeftStr(Consumidor.Endereco,80)) ;
  EnviaComando ;
  Consumidor.Enviado := True ;
  fsEmPagamento := false ;
end;

procedure TACBrECFFiscNET.CancelaCupom;
var
  Erro : string;
  CCD  : Integer ;
begin
  try
     FiscNETComando.NomeComando := 'CancelaCupom' ;
     FiscNETComando.TimeOut     := 30 ;
     FiscNETComando.AddParamString('Operador',Operador) ;
     EnviaComando ;
  except
     on E : Exception do
     begin
        Erro := E.Message ;
        CCD  := StrToIntDef(NumCupom,0) ;

        // 8000 - ErroCMDForaDeSequencia
        // 8086 - ErroCMDCancelamentoInvalido
        // Todos CCD�s vinculados ao cupom devem ser estornados antes da operacao de cancelamento
        while {pos('CCD',Erro) <> 0) and }((pos('8000',Erro) <> 0) or (pos('8086',Erro) <> 0) ) do
        begin
           try
              Erro := '' ;
              FiscNETComando.NomeComando := 'EstornaCreditoDebito' ;
              FiscNETComando.AddParamInteger('COO',CCD) ;
              EnviaComando ;

              Dec(CCD);

              FiscNETComando.NomeComando := 'EncerraDocumento' ;
              FiscNETComando.AddParamString('Operador',Operador) ;
              EnviaComando ;

              FiscNETComando.NomeComando := 'CancelaCupom' ;
              FiscNETComando.AddParamString('Operador',Operador) ;
              EnviaComando ;
           except
              on E : Exception do
                 Erro := E.Message ;
           end ;
        end ;
     end ;
  end ;

  if Erro <> '' then
     raise EACBrECFERRO.create(Erro);

  fsEmPagamento := false ;
    
  FechaRelatorio ;   { Fecha relatorio se ficou algum aberto (s� por garantia)}
end;

procedure TACBrECFFiscNET.CancelaItemVendido(NumItem: Integer);
begin
  FiscNETComando.NomeComando := 'CancelaItemFiscal' ;
  FiscNETComando.AddParamInteger('NumItem',NumItem) ;
  EnviaComando ;
end;

procedure TACBrECFFiscNET.EfetuaPagamento(CodFormaPagto: String;
  Valor: Double; Observacao: AnsiString; ImprimeVinculado: Boolean);
begin
  FiscNETComando.NomeComando := 'PagaCupom' ;
  FiscNETComando.AddParamInteger('CodMeioPagamento', StrToInt(CodFormaPagto)) ;
  FiscNETComando.AddParamString('TextoAdicional',Observacao) ;
  FiscNETComando.AddParamDouble('Valor',Valor) ;
  EnviaComando ;
end;

procedure TACBrECFFiscNET.FechaCupom(Observacao: AnsiString; IndiceBMP : Integer);
var
  Obs: AnsiString;
begin
  Obs := Observacao ;

     { Tem PAF ? }     { PAF ainda n�o est� na Obs ?}
  if (fsPAF <> '') and (pos(fsPAF,Obs) = 0) then
  begin
    if Obs = '' then
       Obs := fsPAF
    else
       Obs := fsPAF + #10 + Obs ;
  end ;

  if not Consumidor.Enviado then
  begin
     { Removendo o Consumidor da Observa��o, pois vai usar comando pr�prio }
     Obs := StringReplace(Obs,#10+'CPF/CNPJ consumidor: '+Consumidor.Documento,'',[]) ;
     Obs := StringReplace(Obs,#10+'Nome: '+Consumidor.Nome,'',[]) ;
     Obs := StringReplace(Obs,#10+'Endereco: '+Consumidor.Endereco,'',[]) ;
     try
        { Se tiver Observa��es no rodape, deve enviar antes do consumidor }
        if Obs <> '' then
        begin
           FiscNETComando.NomeComando := 'ImprimeTexto' ;
           FiscNETComando.AddParamString('TextoLivre',Obs);
           EnviaComando ;
           Obs := '' ;
        end ;

        FiscNETComando.NomeComando := 'IdentificaConsumidor' ;
        FiscNETComando.AddParamString('IdConsumidor',LeftStr(Consumidor.Documento,29)) ;
        if Consumidor.Nome <> '' then
           FiscNETComando.AddParamString('NomeConsumidor',LeftStr(Consumidor.Nome,30)) ;
        if Consumidor.Nome <> '' then
           FiscNETComando.AddParamString('EnderecoConsumidor',LeftStr(Consumidor.Endereco,80)) ;
        EnviaComando ;
        Consumidor.Enviado := True ;
     except
//        Obs := Observacao ;
     end ;
  end ;

  try
     FiscNETComando.NomeComando := 'EncerraDocumento' ;
     if (Obs <> '') then
        FiscNETComando.AddParamString('TextoPromocional',Obs);
     FiscNETComando.TimeOut     := 5 ;
     FiscNETComando.AddParamString('Operador',Operador) ;
     EnviaComando ;

  except
     on E : Exception do
     begin
        if (pos('ErroProtSequenciaComando',E.Message) <> 0) or
           (pos('ErroCMDForaDeSequencia',E.Message) <> 0) then
        begin
           if (Obs <> '') then
           begin
              FiscNETComando.NomeComando := 'ImprimeTexto' ;
              FiscNETComando.AddParamString('TextoLivre',Obs);
              EnviaComando ;
           end ;

           FiscNETComando.NomeComando := 'EncerraDocumento' ;
           FiscNETComando.TimeOut     := 5 ;
           FiscNETComando.AddParamString('Operador',Operador) ;
           EnviaComando ;
        end ;
     end ;
  end ;

  fsEmPagamento := false ;
end;

procedure TACBrECFFiscNET.SubtotalizaCupom(DescontoAcrescimo: Double;
       MensagemRodape : AnsiString);
begin
  fsEmPagamento := True ;
  if DescontoAcrescimo = 0 then
     exit ;
  FiscNETComando.NomeComando := 'AcresceSubtotal' ;
  FiscNETComando.AddParamBool('Cancelar',False) ;
  FiscNETComando.AddParamDouble('ValorAcrescimo',DescontoAcrescimo) ;
  EnviaComando ;
end;

Procedure TACBrECFFiscNET.VendeItem( Codigo, Descricao : String;
  AliquotaECF : String; Qtd : Double ; ValorUnitario : Double;
  ValorDescontoAcrescimo : Double; Unidade : String;
  TipoDescontoAcrescimo : String; DescontoAcrescimo : String ;
  CodDepartamento: Integer) ;
var
  CodAliq: Integer;
begin
  Unidade := padL(Unidade,2) ;

  try
     CodAliq := StrToInt(AliquotaECF) ;
  except
     raise EACBrECFCMDInvalido.Create(ACBrStr('Aliquota Inv�lida: '+AliquotaECF));
  end ;

  try
    if (CodDepartamento = -1) and fpArredondaItemMFD and (not Arredonda) then
       CodDepartamento := 1;  // Arredondamento por CodDepartamento

    with FiscNETComando do
    begin
       if fsComandoVendeItem = '' then
          NomeComando := 'VendeItem'
       else
          NomeComando := fsComandoVendeItem ;
          
       if CodDepartamento <> -1 then
         AddParamInteger('CodDepartamento', CodDepartamento);
         
       AddParamInteger('CodAliquota',CodAliq) ;
       AddParamString('CodProduto',LeftStr(Codigo,48));
       AddParamString('NomeProduto',LeftStr(Descricao,200));
       AddParamDouble('PrecoUnitario',ValorUnitario);
       AddParamDouble('Quantidade',Qtd);
       AddParamString('Unidade',Unidade);
    end ;
    EnviaComando ;
  except
     on E : Exception do
     begin
        if (fsComandoVendeItem = '') and
           (pos('ComandoInexistente',E.Message) > 0) then   // N�o reconheceu o comando
         begin
           fsComandoVendeItem := 'VendaDeItem' ;
           VendeItem( Codigo,Descricao,AliquotaECF,Qtd,ValorUnitario,
                      ValorDescontoAcrescimo,Unidade,TipoDescontoAcrescimo,
                      DescontoAcrescimo );
         end
        else
           raise ;
     end ;
  end ;

  { Se o desconto � maior que zero d� o comando de desconto de item }
  if ValorDescontoAcrescimo > 0 then
     DescontoAcrescimoItemAnterior( ValorDescontoAcrescimo, DescontoAcrescimo,
        TipoDescontoAcrescimo );

  fsEmPagamento := false ;
end;

procedure TACBrECFFiscNET.DescontoAcrescimoItemAnterior(
   ValorDescontoAcrescimo : Double ; DescontoAcrescimo : String ;
   TipoDescontoAcrescimo : String ; NumItem : Integer) ;
begin
  if DescontoAcrescimo = 'D' then
     ValorDescontoAcrescimo := -ValorDescontoAcrescimo ;

  FiscNETComando.NomeComando := 'AcresceItemFiscal' ;
  FiscNETComando.AddParamBool('Cancelar',False);
  if NumItem > 0 then
     FiscNETComando.AddParamInteger('NumItem',NumItem) ;
  if TipoDescontoAcrescimo = '%' then
     FiscNETComando.AddParamDouble('ValorPercentual',ValorDescontoAcrescimo)
  else
     FiscNETComando.AddParamDouble('ValorAcrescimo',ValorDescontoAcrescimo);

  EnviaComando ;
end ;

procedure TACBrECFFiscNET.CarregaAliquotas;
var
  A        : Integer;
  Aliquota : TACBrECFAliquota ;
  ValAliq  : Double ;
  TipoAliq : Char ;
  Erro     : Boolean ;
begin
  inherited CarregaAliquotas ;   { Cria fpAliquotas }

  { L� as al�quotas cadastradas na impressora }
  A    := 0 ;
  Erro := False ;
  while (A <= 15) and (not Erro) do
  begin
     FiscNETComando.NomeComando := 'LeAliquota' ;
     FiscNETComando.AddParamInteger('CodAliquotaProgramavel',A) ;
     try
        EnviaComando ;

        ValAliq  := StringToFloat(
                         FiscNETResposta.Params.Values['PercentualAliquota'] );
        if UpCase(
            FiscNETResposta.Params.Values['AliquotaICMS'][1]) in ['F','N'] then
           TipoAliq := 'S'
        else
           TipoAliq := 'T' ;
        Aliquota          := TACBrECFAliquota.create ;
        Aliquota.Indice   :=
            FiscNETResposta.Params.Values['CodAliquotaProgramavel'] ;
        Aliquota.Aliquota := ValAliq ;
        Aliquota.Tipo     := TipoAliq ;

        fpAliquotas.Add( Aliquota ) ;
     except
        on E : Exception do
        begin
           Erro := (pos('ErroCMDAliquotaNaoCarregada',E.Message) = 0)
        end ;
     end;

     Inc( A ) ;
  end;

  if Erro then   { "niliza" para tentar carregar novamente no futuro }
  begin
     fpAliquotas.Free ;
     fpAliquotas := nil ;
  end ;
end;

procedure TACBrECFFiscNET.LerTotaisAliquota;
var
  A: Integer;
begin
  if not Assigned( fpAliquotas ) then
     CarregaAliquotas ;

  For A := 0 to Aliquotas.Count-1 do
     Aliquotas[A].Total := LeMoeda( 'TotalDiaValorAliquota['+Trim(Aliquotas[A].indice)+']' );
end;

procedure TACBrECFFiscNET.ProgramaAliquota(Aliquota: Double; Tipo: Char;
   Posicao : String);
var
  AliqECF : TACBrECFAliquota;
  Descr   : String ;
begin
  Tipo := UpCase(Tipo) ;
  if not (Tipo in ['T','S']) then
     Tipo := 'T' ;

  if Tipo = 'T' then
     Descr := 'ICMS: '
  else
     Descr := 'ISS: ' ;
  Descr := Descr + FloatToStr(Aliquota)+'%' ;

  with FiscNETComando do
  begin
     NomeComando := 'DefineAliquota' ;
     AddParamBool('AliquotaICMS',(Tipo = 'T')) ;
     AddParamInteger('CodAliquotaProgramavel', StrToIntDef(Posicao,-1) ) ;
     AddParamString('DescricaoAliquota', Descr)  ;
     AddParamDouble('PercentualAliquota',Aliquota);
  end ;
  EnviaComando ;

  { Adicionanodo nova Aliquota no ObjectList }
  if Assigned( fpAliquotas ) then
  begin
     AliqECF          := TACBrECFAliquota.create ;
     AliqECF.Indice   := FiscNETResposta.Params.Values['CodAliquotaProgramavel'] ;
     AliqECF.Aliquota := Aliquota ;
     AliqECF.Tipo     := Tipo ;

     fpAliquotas.Add( AliqECF ) ;
  end ;
end;

function TACBrECFFiscNET.AchaICMSAliquota( var AliquotaICMS: String):
   TACBrECFAliquota;
var
  AliquotaStr: string;
begin
  AliquotaStr := '' ;
  Result      := nil ;

  if copy(AliquotaICMS,1,2) = 'SF' then
     AliquotaStr := '-11'
  else if copy(AliquotaICMS,1,2) = 'SN' then
     AliquotaStr := '-13'
  else if copy(AliquotaICMS,1,2) = 'SI' then
     AliquotaStr := '-12'
  else
     case AliquotaICMS[1] of
        'F' : AliquotaStr := '-2' ;
        'I' : AliquotaStr := '-3' ;
        'N' : AliquotaStr := '-4' ;
        'T' :
           try
              AliquotaICMS := 'T'+IntToStr(StrToInt(copy(AliquotaICMS,2,2))) ; {Indice}
           except
               raise EACBrECFCMDInvalido.Create(ACBrStr('Aliquota Inv�lida: '+AliquotaICMS));
           end ;
     end ;

  if AliquotaStr = '' then
     Result := inherited AchaICMSAliquota( AliquotaICMS )
  else
     AliquotaICMS := AliquotaStr ;
end;


procedure TACBrECFFiscNET.CarregaFormasPagamento;
  Function SubCarregaFormasPagamento(Indice : Integer) : Boolean ;
  var
    FPagto: TACBrECFFormaPagamento;
  begin
     Result := True ;
     FiscNETComando.NomeComando := 'LeMeioPagamento' ;
     FiscNETComando.AddParamInteger('CodMeioPagamentoProgram',Indice) ;
     try
        EnviaComando ;

        FPagto := TACBrECFFormaPagamento.create ;
        FPagto.Indice :=
            FiscNETResposta.Params.Values['CodMeioPagamentoProgram'] ;
        FPagto.Descricao := FiscNETResposta.Params.Values['NomeMeioPagamento'] ;
        FPagto.PermiteVinculado := ( UpCase(
           FiscNETResposta.Params.Values['PermiteVinculado'][1]) in ['T','Y']) ;

        fpFormasPagamentos.Add( FPagto ) ;
     except
        on E : Exception do
        begin
           Result := (pos('ErroCMDFormaPagamentoIndefinida',E.Message) <> 0)
        end ;
     end;
  end ;

var
  A    : Integer;
  Erro : Boolean ;
begin
  inherited CarregaFormasPagamento ;   { Cria fpFormasPagamentos }

  Erro :=  not SubCarregaFormasPagamento(-2) ; { Le Forma Padr�o (fixa) -2 = Dinheiro }

  { L� as Formas de Pagamento cadastradas na impressora }
  A := 0 ;
  while (A <= 14) and (not Erro) do
  begin
     Erro := not SubCarregaFormasPagamento(A);
     Inc( A )
  end ;

  if Erro then   { "niliza" para tentar carregar novamente no futuro }
  begin
     fpFormasPagamentos.Free ;
     fpFormasPagamentos := nil ;
  end ;
end;

procedure TACBrECFFiscNET.LerTotaisFormaPagamento;
var
  A: Integer;
  V: Double;
begin
  if not Assigned( fpFormasPagamentos ) then
     CarregaFormasPagamento ;

  A := 0 ;
  while fsBaseTotalDiaMeioPagamento = 99 do
  begin
    FiscNETComando.NomeComando := 'LeMoeda' ;
    FiscNETComando.AddParamString('NomeDadoMonetario',
                                  'TotalDiaMeioPagamento['+IntToStrZero(A,2)+']');
    try
       EnviaComando ;
       fsBaseTotalDiaMeioPagamento := A;
    except
       On E : Exception do
       begin
          if Pos('11017',E.Message) > 0 then  // ErroProtIndiceRegistrador
             Inc(A)
          else
             raise ;
       end ;
    end ;
  end ;

  For A := 0 to FormasPagamento.Count-1 do
  begin
     if A = 0 then
        V := LeMoeda('TotalDiaDinheiro')
     else
        V := LeMoeda('TotalDiaMeioPagamento['+IntToStrZero(A+fsBaseTotalDiaMeioPagamento-1,2)+']');

     FormasPagamento[A].Total := V ;
  end ;
end;

procedure TACBrECFFiscNET.ProgramaFormaPagamento( var Descricao: String;
  PermiteVinculado : Boolean; Posicao : String) ;
var
  FPagto: TACBrECFFormaPagamento;
begin
  with FiscNETComando do
  begin
     NomeComando := 'DefineMeioPagamento' ;
     if StrToIntDef(Posicao,-1) >= 0 then
        AddParamInteger('CodMeioPagamentoProgram', StrToInt(Posicao) ) ;
     AddParamString('DescricaoMeioPagamento',Descricao) ;
     AddParamString('NomeMeioPagamento',Descricao) ;
     AddParamBool('PermiteVinculado',PermiteVinculado) ;
  end ;
  EnviaComando ;

  { Adicionanodo nova FPG no ObjectList }
  if Assigned( fpFormasPagamentos ) then
  begin
     FPagto := TACBrECFFormaPagamento.create ;
     FPagto.Indice    := FiscNETResposta.Params.Values['CodMeioPagamentoProgram'] ;
     FPagto.Descricao := Descricao ;
     FPagto.PermiteVinculado :=  PermiteVinculado ;

     fpFormasPagamentos.Add( FPagto ) ;
  end ;
end;

procedure TACBrECFFiscNET.CarregaComprovantesNaoFiscais;
var
  A    : Integer;
  CNF  : TACBrECFComprovanteNaoFiscal ;
  Erro : Boolean ;
begin
  inherited CarregaComprovantesNaoFiscais ;

  A    := 0 ;
  Erro := False ;
  while (A <= 14) and (not Erro) do
  begin
     FiscNETComando.NomeComando := 'LeNaoFiscal' ;
     FiscNETComando.AddParamInteger('CodNaoFiscal',A) ;
     try
        EnviaComando ;

        CNF := TACBrECFComprovanteNaoFiscal.create ;

        CNF.Indice    := FiscNETResposta.Params.Values['CodNaoFiscal'] ;
        CNF.Descricao := FiscNETResposta.Params.Values['NomeNaoFiscal'] ;

        fpComprovantesNaoFiscais.Add( CNF ) ;
     except
        on E : Exception do
        begin
           Erro := (pos('ErroCMDNaoFiscalIndefinido',E.Message) = 0)
        end ;
     end;

     Inc( A ) ;
  end ;

  if Erro then   { "niliza" para tentar carregar novamente no futuro }
  begin
     fpComprovantesNaoFiscais.Free ;
     fpComprovantesNaoFiscais := nil ;
  end ;
end;

procedure TACBrECFFiscNET.LerTotaisComprovanteNaoFiscal;
var
  A: Integer;
begin
  if not Assigned( fpComprovantesNaoFiscais ) then
     CarregaComprovantesNaoFiscais ;

  A := 0 ;
  while fsBaseTotalDiaNaoFiscal = 99 do
  begin
    FiscNETComando.NomeComando := 'LeMoeda' ;
    FiscNETComando.AddParamString('NomeDadoMonetario',
                                  'TotalDiaNaoFiscal['+IntToStrZero(A,2)+']');
    try
       EnviaComando ;
       fsBaseTotalDiaNaoFiscal := A;
    except
       On E : Exception do
       begin
          if Pos('11017',E.Message) > 0 then  // ErroProtIndiceRegistrador
             Inc(A)
          else
             raise ;
       end ;
    end ;
  end ;

  For A := 0 to ComprovantesNaoFiscais.Count-1 do
  begin
     ComprovantesNaoFiscais[A].Total := LeMoeda(
        'TotalDiaNaoFiscal['+IntToStrZero(A+fsBaseTotalDiaNaoFiscal,2)+']' );
     ComprovantesNaoFiscais[A].Contador := LeInteiro(
       'CON['+IntToStrZero(A+fsBaseTotalDiaNaoFiscal,2)+']' );
  end ;
end;

procedure TACBrECFFiscNET.ProgramaComprovanteNaoFiscal(var Descricao : String;
   Tipo: String; Posicao : String);
var
  CNF: TACBrECFComprovanteNaoFiscal;
begin
  with FiscNETComando do
  begin
     NomeComando := 'DefineNaoFiscal' ;
     AddParamInteger('CodNaoFiscal', StrToIntDef(Posicao,-1) ) ;
     AddParamString('DescricaoNaoFiscal',Descricao) ;
     AddParamString('NomeNaoFiscal',Descricao) ;
     AddParamBool('TipoNaoFiscal',
                  (not (UpCase(PadR(Tipo,1)[1]) in ['-','F','0'])) ) ;
  end ;
  EnviaComando ;

  { Adicionanodo novo CNF no ObjectList }
  if Assigned( fpComprovantesNaoFiscais ) then
  begin
     CNF := TACBrECFComprovanteNaoFiscal.create ;
     CNF.Indice    := FiscNETResposta.Params.Values['CodNaoFiscal'] ;
     CNF.Descricao := Descricao ;

     fpComprovantesNaoFiscais.Add( CNF ) ;
  end ;
end;

procedure TACBrECFFiscNET.AbreRelatorioGerencial(Indice: Integer = 0);
begin
  if Indice = 0 then
  begin
     try
        { Procurando por Relatorio Gerencial na posi��o informada na vari�vel Indice... Se nao achar, programa }
        FiscNETComando.NomeComando := 'LeGerencial' ;
        FiscNETComando.AddParamInteger('CodGerencial', Indice ) ;
        EnviaComando ;
     except
        { Se nao existir,  gera exce��o e nesse caso programa a posicao }
        FiscNETComando.NomeComando := 'DefineGerencial' ;
        FiscNETComando.AddParamInteger('CodGerencial', Indice ) ;
        FiscNETComando.AddParamString('DescricaoGerencial','Relatorio Gerencial') ;
        FiscNETComando.AddParamString('NomeGerencial','Relatorio Gerencial') ;
        EnviaComando ;
     end ;
  end ;

  FiscNETComando.NomeComando := 'AbreGerencial' ;
  FiscNETComando.TimeOut     := 5 ;
  FiscNETComando.AddParamInteger('CodGerencial', Indice ) ;
  EnviaComando ;
end;

procedure TACBrECFFiscNET.LinhaRelatorioGerencial(Linha: AnsiString; IndiceBMP: Integer);
var
  P, Espera: Integer;
  Buffer   : AnsiString ;
  MaxChars : Integer ;
begin
  Linha    := AjustaLinhas( Linha, Colunas );  { Formata as Linhas de acordo com "Coluna" }
  MaxChars := 492 ;  { FiscNet aceita no m�ximo 492 caract. por comando }

  while Length( Linha ) > 0 do
  begin
      P := Length( Linha ) ;
     if P > MaxChars then    { Acha o fim de Linha mais pr�ximo do limite m�ximo }
        P := PosLast(#10, LeftStr(Linha,MaxChars) ) ;

     if P = 0 then
        P := Colunas ;

     Buffer := copy( Linha, 1, P)  ;
     Espera := Trunc( CountStr( Buffer, #10 ) / 4) ;

     AguardaImpressao := (Espera > 3) ;
     FiscNETComando.NomeComando := 'ImprimeTexto' ;
     FiscNETComando.TimeOut     := Espera ;
     FiscNETComando.AddParamString('TextoLivre',Buffer);
     EnviaComando ;

     { ficou apenas um LF sozinho ? }
     if (P = Colunas) and (RightStr( Buffer, 1) <> #10) and
        (copy( Linha, P+1, 1) = #10) then
        P := P + 1 ;

     Linha  := copy( Linha, P+1, Length(Linha) ) ;   // O Restante
  end ;
end;

procedure TACBrECFFiscNET.AbreCupomVinculado(COO, CodFormaPagto,
   CodComprovanteNaoFiscal :  String; Valor : Double ) ;
var
  FPG: TACBrECFFormaPagamento;
begin
  FPG := AchaFPGIndice( CodFormaPagto ) ;

  if FPG = nil then
     raise EACBrECFERRO.create( ACBrStr('Forma de Pagamento: '+CodFormaPagto+
                             ' n�o foi cadastrada.') ) ;

  FiscNETComando.NomeComando := 'AbreCreditoDebito' ;
  FiscNETComando.TimeOut     := 5 ;
  FiscNETComando.AddParamInteger('CodMeioPagamento',
                                 StrToIntDef(CodFormaPagto,0)) ;
  FiscNETComando.AddParamInteger('COO', StrToIntDef(COO,0));
  FiscNETComando.AddParamDouble('Valor',Valor);
  EnviaComando ;
end;

procedure TACBrECFFiscNET.LinhaCupomVinculado(Linha: AnsiString);
begin
  LinhaRelatorioGerencial( Linha );
end;

procedure TACBrECFFiscNET.FechaRelatorio;
begin
  if Estado = estRelatorio then
  begin
     FiscNETComando.NomeComando := 'EncerraDocumento' ;
     FiscNETComando.TimeOut     := 5 ;
     FiscNETComando.AddParamString('Operador',Operador) ;
     EnviaComando ;
  end ;
end;

procedure TACBrECFFiscNET.PulaLinhas(NumLinhas: Integer);
begin
  if NumLinhas = 0 then
     NumLinhas := LinhasEntreCupons ;

  { Alguem sabe quantos Dots tem 1 linha impressa ?? (no manual n�o consta :) )
    Estou considerando que uma Linha tem 30 dots }

  FiscNETComando.NomeComando := 'AvancaPapel' ;
  FiscNETComando.AddParamInteger('Avanco',NumLinhas * 30) ;
  EnviaComando ;
end;

procedure TACBrECFFiscNET.CortaPapel(const CorteParcial: Boolean);
var
  TipoCorte: Integer;
begin
  if CorteParcial then
     TipoCorte := 1
  else
     TipoCorte := 0 ;

  FiscNETComando.NomeComando := 'CortaPapel';
  FiscNETComando.AddParamInteger('TipoCorte', TipoCorte);
  EnviaComando;
end;


procedure TACBrECFFiscNET.LeituraMemoriaFiscal(ReducaoInicial,
   ReducaoFinal : Integer; Simplificada : Boolean);
begin
  with FiscNETComando do
  begin
     NomeComando := 'EmiteLeituraMF' ;
     AddParamString('Destino','I') ;
     AddParamBool('LeituraSimplificada',Simplificada);
     AddParamString('Operador',Operador) ;
     AddParamInteger('ReducaoFinal',ReducaoFinal) ;
     AddParamInteger('ReducaoInicial',ReducaoInicial) ;
  end ;
  FiscNETComando.TimeOut := 30 + (ReducaoFinal - ReducaoInicial) ;
  EnviaComando ;
end;

procedure TACBrECFFiscNET.LeituraMemoriaFiscal(DataInicial,
   DataFinal: TDateTime; Simplificada : Boolean);
begin
  with FiscNETComando do
  begin
     NomeComando := 'EmiteLeituraMF' ;
     AddParamDateTime('DataFinal',DataFinal) ;
     AddParamDateTime('DataInicial',DataInicial) ;
     AddParamString('Destino','I') ;
     AddParamBool('LeituraSimplificada',Simplificada);
     AddParamString('Operador',Operador) ;
  end ;
  FiscNETComando.TimeOut := 30 + DaysBetween(DataInicial,DataFinal) ;
  EnviaComando ;
end;

procedure TACBrECFFiscNET.LeituraMemoriaFiscalSerial(ReducaoInicial,
   ReducaoFinal: Integer; Linhas : TStringList; Simplificada : Boolean);
Var
  Leitura, RetCmd : AnsiString ;
begin
  with FiscNETComando do
  begin
     NomeComando := 'EmiteLeituraMF' ;
     AddParamString('Destino','S') ;
     AddParamBool('LeituraSimplificada',Simplificada);
     AddParamString('Operador',Operador) ;
     AddParamInteger('ReducaoFinal',ReducaoFinal) ;
     AddParamInteger('ReducaoInicial',ReducaoInicial) ;
  end ;
  FiscNETComando.TimeOut := 30 + (ReducaoFinal - ReducaoInicial) ;
  EnviaComando ;
  Sleep(500);

  Leitura := '' ;
  Linhas.Clear ;
  repeat
     FiscNETComando.NomeComando := 'LeImpressao' ;
     EnviaComando ;

     RetCmd  := FiscNETResposta.Params.Values['TextoImpressao'] ;
     Leitura := Leitura + RetCmd ;
     sleep(100) ;
  until (RetCmd = '') ;

  Linhas.Text := AjustaLeitura( Leitura );
end;

procedure TACBrECFFiscNET.LeituraMemoriaFiscalSerial(DataInicial,
   DataFinal: TDateTime; Linhas : TStringList; Simplificada : Boolean);
Var
  Leitura, RetCmd : AnsiString ;
begin
  with FiscNETComando do
  begin
     NomeComando := 'EmiteLeituraMF' ;
     AddParamDateTime('DataFinal',DataFinal) ;
     AddParamDateTime('DataInicial',DataInicial) ;
     AddParamString('Destino','S') ;
     AddParamBool('LeituraSimplificada',Simplificada);
     AddParamString('Operador',Operador) ;
  end ;
  FiscNETComando.TimeOut := 30 + DaysBetween(DataInicial,DataFinal) ;
  EnviaComando ;
  Sleep(500);

  Leitura := '' ;
  Linhas.Clear ;
  repeat
     FiscNETComando.NomeComando := 'LeImpressao' ;
     EnviaComando ;

     RetCmd := FiscNETResposta.Params.Values['TextoImpressao'] ;
     Leitura := Leitura + RetCmd ;
     sleep(100) ;
  until (RetCmd = '') ;

  Linhas.Text := AjustaLeitura( Leitura );
end;

procedure TACBrECFFiscNET.LeituraMFDSerial(DataInicial,
  DataFinal: TDateTime; Linhas: TStringList; Documentos : TACBrECFTipoDocumentoSet = [docTodos]);
Var
  Leitura, RetCmd : AnsiString ;
  Doctos : String ;
begin
  Doctos := DocumentosToStr(Documentos) ;

  with FiscNETComando do
  begin
     NomeComando := 'EmiteLeituraFitaDetalhe' ;
     AddParamDateTime('DataFinal',DataFinal);
     AddParamDateTime('DataInicial',DataInicial);
     AddParamString('Destino','S') ;
     if Doctos <> '' then
        AddParamString('TipoDocumento',Doctos);
  end ;
  FiscNETComando.TimeOut := 5 + DaysBetween(DataInicial,DataFinal) ;
  EnviaComando ;
  Sleep(500);

  //WriteToTXT('d:\temp\mfd_ret.txt','', False);
  Leitura := '' ;
  repeat
     FiscNETComando.NomeComando := 'LeImpressao' ;
     EnviaComando ;

     RetCmd := FiscNETResposta.Params.Values['TextoImpressao'] ;
     //WriteToTXT('d:\temp\mfd_ret.txt',RetCmd, True);
     Leitura := Leitura + RetCmd ;
     sleep(100) ;
  until (RetCmd = '') ;

  Linhas.Text := AjustaLeitura( Leitura );
  //WriteToTXT('d:\temp\mfd_limpo.txt',Linhas.Text, False);
end;

procedure TACBrECFFiscNET.LeituraMFDSerial(COOInicial,
  COOFinal: Integer; Linhas: TStringList; Documentos : TACBrECFTipoDocumentoSet = [docTodos]);
Var
  Leitura, RetCmd : AnsiString ;
  Doctos : String ;
begin
  Doctos := DocumentosToStr(Documentos) ;

  with FiscNETComando do
  begin
     NomeComando := 'EmiteLeituraFitaDetalhe' ;
     AddParamInteger('COOFinal',COOFinal);
     AddParamInteger('COOInicial',COOInicial);
     AddParamString('Destino','S') ;
     if Doctos <> '' then
        AddParamString('TipoDocumento',Doctos);
  end ;
  FiscNETComando.TimeOut := 5 + (COOFinal - COOInicial) ;
  EnviaComando ;
  Sleep(400);

  Leitura := '' ;
  Linhas.Clear ;
  repeat
     FiscNETComando.NomeComando := 'LeImpressao' ;
     EnviaComando ;

     RetCmd := FiscNETResposta.Params.Values['TextoImpressao'] ;
     Leitura := Leitura + RetCmd ;
     sleep(100) ;
  until (RetCmd = '') ;

  Linhas.Text := AjustaLeitura( Leitura ) ;
end;

Function TACBrECFFiscNET.DocumentosToStr(Documentos : TACBrECFTipoDocumentoSet) : String ;
begin
  Result := '' ;
  if (docTodos in Documentos) then
     exit ;

  if docLX              in Documentos then Result := Result + '1,' ;
  if docRZ              in Documentos then Result := Result + '2,' ;
  if docCF              in Documentos then Result := Result + '3,' ;
  if docCNF             in Documentos then Result := Result + '4,5,6,'
  else
   begin
     if docSuprimento   in Documentos then Result := Result + '5,' ;
     if docSangria      in Documentos then Result := Result + '6,' ;
   end ;
  if docCFCancelamento  in Documentos then Result := Result + '7,8,' ;
  if docCNFCancelamento in Documentos then Result := Result + '9,' ;
  if docCupomAdicional  in Documentos then Result := Result + '10,' ;
  if docLMF             in Documentos then Result := Result + '11,' ;
  if docCCD             in Documentos then Result := Result + '12,13,14,' ;
  if docRG              in Documentos then Result := Result + '15,' ;
  if docEstornoPagto    in Documentos then Result := Result + '16,' ;
  if docEstornoCCD      in Documentos then Result := Result + '17,' ;

  Result := copy(Result,1,Length(Result)-1) ; // Remove a ultima Virgula
end ;

Function TACBrECFFiscNET.AjustaLeitura( AString : AnsiString ) : AnsiString ;
Var
  A, Cols : Integer ;
begin
  { Detectando o n�mero de Colunas (n�o encontrei registrador no ECF que
    retorne o Numero de Colunas) }
  A := pos(StringOfChar('-',40), AString ) ;
  if A < 1 then
     Cols := Colunas
  else
   begin
     Cols := 40 ;
     while copy(AString,A+Cols,1) = '-' do
        Inc( Cols ) ;
   end ;

  { Remove caracteres de Impressao }
  Result := RemoveStrings( AString, fsComandosImpressao ) ;

  { Ajusta o Tamanho das Colunas }
  Result := AjustaLinhas( Result, Cols ) ;
end;

procedure TACBrECFFiscNET.CorrigeEstadoErro(Reducao: Boolean);
begin
  inherited CorrigeEstadoErro(Reducao) ;

  if Estado <> estLivre then
     try
        FiscNETComando.NomeComando := 'ReinicializaEquipamento' ;
        EnviaComando ;
        sleep(200) ;
     except
     end ;
end;

procedure TACBrECFFiscNET.CancelaImpressaoCheque;
begin
  FiscNETComando.NomeComando := 'ChancelaCheque' ;
  EnviaComando ;
end;

function TACBrECFFiscNET.GetChequePronto: Boolean;
begin
  FiscNETComando.NomeComando := 'LeIndicador' ;
  FiscNETComando.AddParamString('NomeIndicador','SensorCheque') ;
  EnviaComando ;

  Result := (FiscNETResposta.Params.Values['ValorNumericoIndicador'] = '1')
end;

procedure TACBrECFFiscNET.ImprimeCheque(Banco: String; Valor: Double;
  Favorecido, Cidade: String; Data: TDateTime; Observacao: String);
begin
   { N�o implementado pois NAO encontrei uma tabela com as cordenadas de
     impress�o para cada Banco }

 {14;ImprimeCheque;Cidade="Tatui" Data=#01/03/07# Favorecido="Daniel Simoes de Almeida" HPosAno=1 HPosCidade=2 HPosDia=3 HPosExtensoLinha1=4 HPosExtensoLinha2=5 HPosFavorecido=6 HPosMes=7 HPosMsgLinha1=8 HPosMsgLinha2=9 HPosMsgLinha3=10 HPosValor=11 MensagemDocLinha1="Msg DOC Linha 1" MensagemDocLinha2="Msg DOC Linha 1" MensagemDocLinha3="Msg DOC Linha 1" TempoEspera=10 Valor=100,00 VPosCidade=12 VPosExtensoLinha1=13 VPosExtensoLinha2=14 VPosFavorecido=15 VPosMsgLinha1=16 VPosMsgLinha2=17 VPosMsgLinha3=18 VPosValor=19;522}
end;

Function TACBrECFFiscNET.LeituraCMC7 : AnsiString ;     
begin
  FiscNETComando.NomeComando := 'LeTexto' ;
  FiscNETComando.AddParamString('NomeTexto', 'CMC7Documento') ;
  AguardaImpressao := True;
  EnviaComando ;
  sleep(500);
  
  Result := FiscNETResposta.Params.Values['ValorTexto'] ;
end;

function TACBrECFFiscNET.GetCNPJ: String;
begin
  Result := LeTexto( 'CNPJ' ) ;
end;

function TACBrECFFiscNET.GetIE: String;
begin
  Result := LeTexto( 'IE' ) ;
end;

function TACBrECFFiscNET.GetIM: String;
begin
  Result := LeTexto( 'IM' ) ;
end;

function TACBrECFFiscNET.GetCliche: AnsiString;
begin
  Result := LeTexto( 'Cliche' ) ;
end;

function TACBrECFFiscNET.GetUsuarioAtual: String;
begin
  Result := IntToStrZero( LeInteiro( 'ContadorProprietarios' ), 3) ;
end;

function TACBrECFFiscNET.GetDataHoraSB: TDateTime;
Var Linha, LinhaVer : AnsiString ;
    OldShortDateFormat : String ;
    Linhas : TStringList;
    I, CRZ :Integer;
    AchouBlocoSB : Boolean ;
begin
  Result := 0.0;

  // verificar se a redu��o Z est� pendente e n�o fazer se estiver
  // porque acontecer� erro, conforme consulta ao atendimento da bematech
  if Estado in [estLivre] then
  begin
    Linhas := TStringList.Create;

    try
      CRZ := StrToIntDef(NumCRZ, 1) ;
      LeituraMemoriaFiscalSerial(CRZ, CRZ, Linhas);

      I := 0 ;
      AchouBlocoSB := False;
      while (not AchouBlocoSB) and (I < Linhas.Count) do
      begin
         Linha := Linhas[I] ;
         AchouBlocoSB := (pos('SOFTWARE B', Linha ) > 0) ;
         Inc( I ) ;
      end ;

      Linha    := '';
      LinhaVer := '';
      while AchouBlocoSB and (I < Linhas.Count) and (Linha = LinhaVer) do
      begin
         Linha := Trim(Linhas[I]) ;
         if (Linha <> '') then
         begin
            if ( StrIsNumber( copy(Linha,1,2) ) and ( copy(Linha,3,1) = '.' ) and
                 StrIsNumber( copy(Linha,4,2) ) and ( copy(Linha,6,1) = '.' ) and
                 StrIsNumber( copy(Linha,7,2) ) ) then
               LinhaVer := Linha;
         end ;

         Inc( I ) ;
      end ;

      if LinhaVer <> '' then
      begin
        // SOFTWARE B�SICO:
        //03.00.00 09/10/2002   12:18:47
        //03.03.00 06/03/2006   10:57:23

        I := pos('/', LinhaVer ) ;

        OldShortDateFormat := ShortDateFormat ;
        try
          ShortDateFormat := 'dd/mm/yyyy' ;
          Result := StrToDate( StringReplace( copy(LinhaVer, I-2, 10 ),
                                           '/', DateSeparator, [rfReplaceAll] ) ) ;

          I := pos(':', LinhaVer ) ;
          Result := RecodeHour(  result,StrToInt(copy(LinhaVer, I-2,2))) ;
          Result := RecodeMinute(result,StrToInt(copy(LinhaVer, I+1,2))) ;
          Result := RecodeSecond(result,StrToInt(copy(LinhaVer, I+4,2))) ;
        finally
          ShortDateFormat := OldShortDateFormat ;
        end ;
      end
    finally
      Linhas.Free ;
    end ;
  end;
end;

function TACBrECFFiscNET.GetSubModeloECF: String;
begin
  Result := fsModeloECF;
end;

function TACBrECFFiscNET.GetDataMovimento: TDateTime;
var
  RetCmd: AnsiString;
  OldShortDateFormat : String ;
  bDiaAberto, bDiaFechado: boolean;
  sParam: String;
begin
   FiscNETComando.TimeOut := 15;
   FiscNETComando.NomeComando := 'LeIndicador' ;
   FiscNETComando.AddParamString('NomeIndicador','DiaAberto');
   EnviaComando ;
   bDiaAberto := FiscNETResposta.Params.Values['ValorTextoIndicador'] = '1';

   FiscNETComando.NomeComando := 'LeIndicador' ;
   FiscNETComando.AddParamString('NomeIndicador','DiaFechado');
   EnviaComando ;
   bDiaFechado := FiscNETResposta.Params.Values['ValorTextoIndicador'] = '1';

   if not (bDiaAberto or bDiaFechado) then
      sParam := 'Data'
   else
      sParam := 'DataAbertura';

  FiscNETComando.NomeComando := 'LeData' ;
  FiscNETComando.AddParamString('NomeData', sParam);
  EnviaComando ;
  RetCmd := FiscNETResposta.Params.Values['ValorData'] ;
  RetCmd := StringReplace(RetCmd ,'/',DateSeparator, [rfReplaceAll] );
  OldShortDateFormat := ShortDateFormat ;
  try
     ShortDateFormat := 'dd/mm/yyyy' ;
     Result := StrToDate( RetCmd ) ;
  finally
     ShortDateFormat := OldShortDateFormat ;
  end ;
end;

function TACBrECFFiscNET.GetGrandeTotal: Double;
begin
  Result := LeMoeda('GT') ;
end;

function TACBrECFFiscNET.GetNumCRZ: String;
begin
  Result := IntToStrZero( LeInteiro( 'CRZ' ), 6) ;
end;

function TACBrECFFiscNET.GetTotalAcrescimos: Double;
begin
  Result := LeMoeda( 'TotalDiaAcrescimos' ) ;
end;

function TACBrECFFiscNET.GetTotalAcrescimosISSQN: Double;
begin
  Result := LeMoeda( 'TotalDiaAcrescimosISSQN') ;
end;

function TACBrECFFiscNET.GetTotalCancelamentos: Double;
begin
  Result := LeMoeda( 'TotalDiaCancelamentosIcms' ) ;
end;

function TACBrECFFiscNET.GetTotalCancelamentosISSQN: Double;
begin
  Result := LeMoeda( 'TotalDiaCancelamentosISSQN' ) ;
end;

function TACBrECFFiscNET.GetTotalDescontos: Double;
begin
  Result := LeMoeda( 'TotalDiaDescontos' ) ;
end;

function TACBrECFFiscNET.GetTotalTroco: Double;
begin
  Result := LeMoeda( 'TotalDiaTroco' ) ;
end;

function TACBrECFFiscNET.GetTotalDescontosISSQN: Double;
begin
  Result := LeMoeda( 'TotalDiaDescontosISSQN' ) ;
end;

function TACBrECFFiscNET.GetTotalSubstituicaoTributaria: Double;
begin
  Result := LeMoeda( 'TotalDiaSubstituicaoTributariaICMS' ) ;
end;

function TACBrECFFiscNET.GetTotalSubstituicaoTributariaISSQN: Double;
begin
  Result := LeMoeda( 'TotalDiaSubstituicaoTributariaISSQN' ) ;
end;

function TACBrECFFiscNET.GetTotalIsencao: Double;
begin
  Result := LeMoeda( 'TotalDiaIsencaoICMS' ) ;
end;

function TACBrECFFiscNET.GetTotalIsencaoISSQN: Double;
begin
  Result := LeMoeda( 'TotalDiaIsencaoISSQN' ) ;
end;

function TACBrECFFiscNET.GetTotalNaoTributado: Double;
begin
  Result := LeMoeda( 'TotalDiaNaoTributadoICMS' ) ;
end;

function TACBrECFFiscNET.GetTotalNaoTributadoISSQN: Double;
begin
  Result := LeMoeda( 'TotalDiaNaoTributadoISSQN' ) ;
end;

function TACBrECFFiscNET.GetTotalAcrescimosOPNF: Double;
begin
  Result := LeMoeda( 'TotalDiaAcrescimosNaoFiscais' ) ;
end;

function TACBrECFFiscNET.GetTotalCancelamentosOPNF: Double;
begin
  Result := LeMoeda( 'TotalDiaCancelamentosNaoFiscais' ) ;
end;

function TACBrECFFiscNET.GetTotalDescontosOPNF: Double;
begin
  Result := LeMoeda( 'TotalDiaDescontosNaoFiscais' ) ;
end;

function TACBrECFFiscNET.GetVendaBruta: Double;
begin
  Result := LeMoeda( 'TotalDiaVendaBruta' ) ;
end;

function TACBrECFFiscNET.GetNumCOOInicial: String;
begin
  Result := IntToStrZero( LeInteiro( 'COOInicioDia' ), 6) ;
end;

function TACBrECFFiscNET.GetNumUltimoItem: Integer;
begin
  Result := LeInteiro( 'ContadorDocUltimoItemVendido' );
end;

procedure TACBrECFFiscNET.AbreNaoFiscal(CPF_CNPJ : String ; Nome : String ;
   Endereco : String) ;
begin
  FiscNETComando.NomeComando := 'AbreCupomNaoFiscal' ;
  FiscNETComando.TimeOut     := 5 ;
  FiscNETComando.AddParamString('IdConsumidor',LeftStr(CPF_CNPJ,29)) ;
  if Nome <> '' then
     FiscNETComando.AddParamString('NomeConsumidor',LeftStr(Nome,30)) ;
  if Endereco <> '' then
     FiscNETComando.AddParamString('EnderecoConsumidor',LeftStr(Endereco,80)) ;

  EnviaComando ;
end;

procedure TACBrECFFiscNET.RegistraItemNaoFiscal(CodCNF: String;
  Valor: Double; Obs: AnsiString);
begin
  FiscNETComando.NomeComando := 'EmiteItemNaoFiscal' ;
  FiscNETComando.TimeOut     := 5 ;
  FiscNETComando.AddParamInteger('CodNaoFiscal',StrToInt(CodCNF) ) ;
  FiscNETComando.AddParamDouble('Valor',Valor) ;
  EnviaComando ;
end;

function TACBrECFFiscNET.GetArredonda: Boolean;
begin
  if fsArredonda < 0 then
  begin
     try
        fsArredonda := LeInteiro( 'Arredondamento' ) ;
     except
        on E : Exception do
        begin
           // Erro: 11011 - ErroProtNomeRegistrador Parametro NomeInteiro contem nome de registrador inexistente
           if (pos('ErroProtNomeRegistrador',E.Message) = 0) then
              raise ;
           fsArredonda := 1 ;  // N�o tem o comando, assume Truncamento
        end ;
     end ;
  end ;

  { Os valores v�lidos s�o: 0 para arredondamento segundo ABNT,
                            1 para truncamento e
                            2 para arredondamento para cima  }
  Result := (fsArredonda = 0) or (fsArredonda = 2) ;
end;

function TACBrECFFiscNET.GetTipoUltimoDocumento : TACBrECFTipoDocumento ;
var
   Tipo : Integer ;
begin
  Tipo := LeInteiro( 'TipoUltimoDocEmitido' ) ;

  case Tipo of
    1        : Result := docLX;
    2        : Result := docRZ;
    3        : Result := docCF;
    4,5,6    : Result := docCNF;
    7,8      : Result := docCFCancelamento;
    9        : Result := docCNFCancelamento;
    10       : Result := docCupomAdicional;
    11       : Result := docLMF;
    12,13,14 : Result := docCCD;
    15       : Result := docRG;
    16       : Result := docEstornoPagto;
    17       : Result := docEstornoCCD;
  else
    Result := docNenhum;
  end ;
end ;

procedure TACBrECFFiscNET.NaoFiscalCompleto(CodCNF: String; Valor: Double;
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
           FechaNaoFiscal        // Tenta sem Obs
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

function TACBrECFFiscNET.EstornaCCD(const Todos : Boolean) : Integer ;
var
   CCD : Integer ;
   Fim : Boolean ;
   Erro : String ;
begin
  Result := 0;

  if TipoUltimoDocumento <> docCCD then
     exit ;

  CCD := StrToIntDef(NumCupom,0) ;
  Fim := not Todos;

  repeat
     try
        FiscNETComando.NomeComando := 'EstornaCreditoDebito' ;
        FiscNETComando.AddParamInteger('COO',CCD) ;
        EnviaComando ;


        FiscNETComando.NomeComando := 'EncerraDocumento' ;
        FiscNETComando.AddParamString('Operador',Operador) ;
        EnviaComando ;

        Dec(CCD);
        Inc( Result );
     except
        On E : Exception do
        begin
           Erro := E.Message;

           if pos('ErroCMDCOOInvalido', Erro) > 0 then
              Fim := True
           else
              raise ;
        end ;
     end ;
  until Fim;

end ;

procedure TACBrECFFiscNET.CarregaRelatoriosGerenciais;
  Function SubCarregaGerenciais(Indice : Integer) : Boolean ;
  var
    RG: TACBrECFRelatorioGerencial;
  begin
     Result := True ;
     FiscNETComando.NomeComando := 'LeGerencial' ;
     FiscNETComando.AddParamInteger('CodGerencial',Indice) ;
     try
        EnviaComando ;

        RG := TACBrECFRelatorioGerencial.create ;
        RG.Indice    := FiscNETResposta.Params.Values['CodGerencial'] ;
        RG.Descricao := FiscNETResposta.Params.Values['NomeGerencial'] ;

        fpRelatoriosGerenciais.Add( RG ) ;
     except
        on E : Exception do
        begin
           Result := (pos('ErroCMDGerencialNaoDefinido',E.Message) <> 0)
        end ;
     end;
  end ;

var
  A    : Integer;
  Erro : Boolean ;
begin
  inherited CarregaRelatoriosGerenciais ;   { Cria fpRelatoriosGerenciais }

  Erro := False ;

  { L� as Formas de Pagamento cadastradas na impressora }
  A := 0 ;
  while (A <= 19) and (not Erro) do
  begin
     Erro := not SubCarregaGerenciais(A);
     Inc( A )
  end ;

  if Erro then   { "niliza" para tentar carregar novamente no futuro }
  begin
     fpRelatoriosGerenciais.Free ;
     fpRelatoriosGerenciais := nil ;
  end ;
end;

procedure TACBrECFFiscNET.LerTotaisRelatoriosGerenciais ;
var
  A: Integer;
begin
  if not Assigned( fpRelatoriosGerenciais ) then
     CarregaRelatoriosGerenciais ;

  For A := 0 to RelatoriosGerenciais.Count-1 do
     RelatoriosGerenciais[A].Contador := LeInteiro(
        'CER['+RelatoriosGerenciais[A].Indice+']');
end ;

procedure TACBrECFFiscNET.ProgramaRelatorioGerencial(var Descricao: String;
  Posicao: String);
var
  RG: TACBrECFRelatorioGerencial;
begin
  with FiscNETComando do
  begin
     NomeComando := 'DefineGerencial' ;
     if StrToIntDef(Posicao,-1) >= 0 then
        AddParamInteger('CodGerencial', StrToInt(Posicao) ) ;
     AddParamString('DescricaoGerencial',Descricao) ;
     AddParamString('NomeGerencial',Descricao) ;
  end ;
  EnviaComando ;

  { Adicionanodo novo RG no ObjectList }
  if Assigned( fpRelatoriosGerenciais ) then
  begin
     RG := TACBrECFRelatorioGerencial.create ;
     RG.Indice    := FiscNETResposta.Params.Values['CodGerencial'] ;
     RG.Descricao := Descricao ;

     fpRelatoriosGerenciais.Add( RG ) ;
  end ;
end;

procedure TACBrECFFiscNET.IdentificaPAF(NomeVersao, MD5 : String);
begin
   fsPAF := Trim( MD5 + #10 + NomeVersao ) ;
   FiscNETComando.NomeComando := 'EscreveTexto' ;
   FiscNETComando.AddParamString('NomeTexto' ,'TextoLivre') ;
   FiscNETComando.AddParamString('ValorTexto', fsPAF ) ;
   EnviaComando ;
end;

function TACBrECFFiscNET.GetPAF: String;   
begin
  fsPAF  := LeTexto( 'TextoLivre' ) ;
  Result := fsPAf ;
end;

function TACBrECFFiscNET.GetParamDescontoISSQN: Boolean;
begin
  try
    Result := (LeInteiro( 'PermiteISS' ) = 15) ;
  except
     On E : Exception do
     begin
        Result := False;
        // Comando n�o existe em vers�es antigas //
        if pos('ErroProtNomeRegistrador', E.Message) = 0 then
           raise ;
     end ;
  end;
end;

procedure TACBrECFFiscNET.LoadDLLFunctions;
Var
  LIB_FiscNet : String ;

 procedure FiscNetFunctionDetect( LibName, FuncName: String; var LibPointer: Pointer ) ;
 var
 sLibName: string;
 begin
   if not Assigned( LibPointer )  then
   begin
     // Verifica se exite o caminho das DLLs
     if Length(PathDLL) > 0 then
        sLibName := PathWithDelim(PathDLL);

     // Concatena o caminho se exitir mais o nome da DLL.
     sLibName := sLibName + LibName;

     if not FunctionDetect( sLibName, FuncName, LibPointer) then
     begin
        LibPointer := NIL ;
        raise EACBrECFERRO.Create( ACBrStr( 'Erro ao carregar a fun��o:'+FuncName+' de: '+LibName ) ) ;
     end ;
   end ;
 end ;
begin
  if pos(fsMarcaECF, 'dataregis|termoprinter') > 0 then
   begin
     LIB_FiscNet := 'DLLG2_Gerador.dll' ;
     FiscNetFunctionDetect(LIB_FiscNet, 'Gera_AtoCotepe1704_Periodo_MFD',@xGera_AtoCotepe1704_Periodo_MFD );
     FiscNetFunctionDetect(LIB_FiscNet, 'Gera_AtoCotepe1704_Periodo_MF',@xGera_AtoCotepe1704_Periodo_MF );
     FiscNetFunctionDetect(LIB_FiscNet, 'Gera_PAF',@xGera_PAF );
   end

  else if (fsMarcaECF = 'elgin') then
   begin
     LIB_FiscNet := 'Elgin.dll' ;

     FiscNetFunctionDetect(LIB_FiscNet, 'Elgin_AbrePortaSerial', @xElgin_AbrePortaSerial );
     FiscNetFunctionDetect(LIB_FiscNet, 'Elgin_FechaPortaSerial', @xElgin_FechaPortaSerial );
     FiscNetFunctionDetect(LIB_FiscNet, 'Elgin_DownloadMFD', @xElgin_DownloadMFD );
     FiscNetFunctionDetect(LIB_FiscNet, 'Elgin_FormatoDadosMFD', @xElgin_FormatoDadosMFD );
     FiscNetFunctionDetect(LIB_FiscNet, 'Elgin_LeMemoriasBinario', @xElgin_LeMemoriasBinario );
     FiscNetFunctionDetect(LIB_FiscNet, 'Elgin_GeraArquivoATO17Binario', @xElgin_GeraArquivoATO17Binario  );
   end
  else    // Urano e demais
   begin
     FiscNetFunctionDetect('Leitura.dll', 'DLLReadLeMemorias',  @xDLLReadLeMemorias );
     FiscNetFunctionDetect('ATO17.dll',   'DLLATO17GeraArquivo', @xDLLATO17GeraArquivo );
   end ;
end;

function TACBrECFFiscNET.LeMoeda(const Registrador: String): Double;
begin
   FiscNETComando.NomeComando := 'LeMoeda' ;
   FiscNETComando.AddParamString('NomeDadoMonetario', Registrador ) ;
   EnviaComando ;

   Result := StringToFloatDef(
      RemoveString('.', FiscNETResposta.Params.Values['ValorMoeda'] ), 0) ;
end;

function TACBrECFFiscNET.LeInteiro(const Registrador: String): Integer;
begin
  FiscNETComando.NomeComando := 'LeInteiro' ;
  FiscNETComando.AddParamString('NomeInteiro', Registrador ) ;
  EnviaComando ;

  Result := StrToIntDef( FiscNETResposta.Params.Values['ValorInteiro'], 0 );
end;

function TACBrECFFiscNET.LeTexto(const Registrador: String): String;
begin
  FiscNETComando.NomeComando := 'LeTexto';
  FiscNETComando.AddParamString('NomeTexto', Registrador );
  EnviaComando;
  Result := FiscNETResposta.Params.Values['ValorTexto'] ;
end;

procedure TACBrECFFiscNET.AbrePortaSerialDLL(const Porta, Path: String);
Var
  Resp : Integer ;
  IniFile : String ;
  Ini  : TIniFile ;
begin
  if (fsMarcaECF = 'elgin') then
  begin
     Resp := xElgin_AbrePortaSerial();
     {
     1: Indica que nenhum erro ocorreu
     -4: O arquivo de inicializa��o Elgin.ini n�o foi encontrado no diret�rio de sistema do Windows.
     -5: Erro ao abrir a porta de comunica��o.
     -50: N�mero de s�rie inv�lido.
     }
     if (Resp = -4) or (Resp = -5) then
     begin
        IniFile := ExtractFilePath( ParamStr(0) )+'ELGIN.ini' ;
        Ini := TIniFile.Create( IniFile );
        try
           Ini.WriteString('Sistema','Porta',Porta ) ;
           Ini.WriteString('Sistema','Path',Path ) ;
           Ini.WriteString('Sistema','PathRFD', Path );
           Ini.WriteString('Sistema','Gera_RFD_REDZ', '1');
        finally
           Ini.Free ;
        end ;

        Resp := xElgin_AbrePortaSerial();
     end ;

     if Resp <> 1 then
        raise EACBrECFERRO.Create( ACBrStr('Erro: '+IntToStr(Resp)+' ao abrir a Porta com:'+sLineBreak+
        'Elgin_AbrePortaSerial()'));
  end ;
end;

procedure TACBrECFFiscNET.EspelhoMFD_DLL(DataInicial, DataFinal: TDateTime; NomeArquivo: AnsiString;
  Documentos: TACBrECFTipoDocumentoSet);
Var
  iRet : Integer;
  PortaSerial : String;
  DiaIni, DiaFim, ArqTmp : String ;
  OldAtivo : Boolean ;
begin
  if (fsMarcaECF <> 'elgin') then
  begin
     TACBrECF(fpOwner).LeituraMFDSerial( DataInicial, DataFinal, NomeArquivo, Documentos );
     exit ;
  end ;

  LoadDLLFunctions;

  DiaIni := FormatDateTime('ddmmyy', DataInicial) ;
  DiaFim := FormatDateTime('ddmmyy', DataFinal) ;

  PortaSerial := fpDevice.Porta ;
  OldAtivo    := Ativo ;
  try
     Ativo := False ;

     AbrePortaSerialDLL( PortaSerial, ExtractFilePath( NomeArquivo ) ) ;

     ArqTmp := ExtractFilePath( NomeArquivo ) ;
     DeleteFile( ArqTmp + '.mfd' ) ;

     iRet := xElgin_DownloadMFD(ArqTmp + '.mfd', '1', DiaIni, DiaFim, '');
     if (iRet <> 1) then
        raise EACBrECFERRO.Create( ACBrStr( 'Erro ao executar Elgin_DownloadMFD.'+sLineBreak+
                                         'Cod.: ' + IntToStr(iRet) )) ;
     if not FileExists( ArqTmp + '.mfd' ) then
        raise EACBrECFERRO.Create( ACBrStr( 'Erro na execu��o de Elgin_DownloadMFD.'+sLineBreak+
                                         'Arquivo: "' + ArqTmp + '.mfd" n�o gerado' )) ;

     iRet := xElgin_FormatoDadosMFD(ArqTmp + '.mfd', nomeArquivo, '0', '1', DiaIni, DiaFim, '');
     if (iRet <> 1) then
        raise EACBrECFERRO.Create( ACBrStr( 'Erro ao executar Elgin_FormatoDadosMFD.'+sLineBreak+
                                         'Cod.: ' + IntToStr(iRet) )) ;
     if not FileExists( NomeArquivo ) then
        raise EACBrECFERRO.Create( ACBrStr( 'Erro na execu��o de Elgin_FormatoDadosMFD.'+sLineBreak+
                                         'Arquivo: "' + NomeArquivo + '" n�o gerado' )) ;
     xElgin_FechaPortaSerial();
     DeleteFile( ArqTmp + '.mfd' ) ;
  finally
    Ativo := OldAtivo ;
  end;
end;

procedure TACBrECFFiscNET.EspelhoMFD_DLL(COOInicial, COOFinal: Integer; NomeArquivo: AnsiString;
  Documentos: TACBrECFTipoDocumentoSet);
Var
  iRet : Integer;
  PortaSerial : String;
  CooIni, CooFim, Prop, ArqTmp : String ;
  OldAtivo : Boolean ;
begin
  if (fsMarcaECF <> 'elgin') then
  begin
     TACBrECF(fpOwner).LeituraMFDSerial( COOInicial, COOFinal, NomeArquivo, Documentos );
     exit ;
  end ;

  LoadDLLFunctions;

  CooIni := IntToStrZero( COOInicial, 6 ) ;
  CooFim := IntToStrZero( COOFinal, 6 ) ;
  Prop   := IntToStr( StrToIntDef( UsuarioAtual, 1) ) ;

  PortaSerial := fpDevice.Porta ;

  OldAtivo := Ativo ;
  try
     Ativo := False ;

     AbrePortaSerialDLL( PortaSerial, ExtractFilePath( NomeArquivo ) ) ;

     ArqTmp := ExtractFilePath( NomeArquivo ) ;
     DeleteFile( ArqTmp + '.mfd' ) ;

     iRet := xElgin_DownloadMFD(ArqTmp + '.mfd', '2', CooIni, CooFim, Prop);
     if (iRet <> 1) then
        raise EACBrECFERRO.Create( ACBrStr( 'Erro ao executar Elgin_DownloadMFD.'+sLineBreak+
                                         'Cod.: ' + IntToStr(iRet) )) ;
     if not FileExists( ArqTmp + '.mfd' ) then
        raise EACBrECFERRO.Create( ACBrStr( 'Erro na execu��o de Elgin_DownloadMFD.'+sLineBreak+
                                         'Arquivo: "' + ArqTmp + '.mfd" n�o gerado' )) ;

     iRet := xElgin_FormatoDadosMFD(ArqTmp + '.mfd', nomeArquivo, '0', '2', CooIni, CooFim, Prop);
     if (iRet <> 1) then
        raise EACBrECFERRO.Create( ACBrStr( 'Erro ao executar Elgin_FormatoDadosMFD.'+sLineBreak+
                                         'Cod.: ' + IntToStr(iRet) )) ;
     if not FileExists( NomeArquivo ) then
        raise EACBrECFERRO.Create( ACBrStr( 'Erro na execu��o de Elgin_FormatoDadosMFD.'+sLineBreak+
                                         'Arquivo: "' + NomeArquivo + '" n�o gerado' )) ;
     xElgin_FechaPortaSerial();
     DeleteFile( ArqTmp + '.mfd' ) ;
  finally
    Ativo := OldAtivo ;
  end;
end;

procedure TACBrECFFiscNET.ArquivoMFD_DLL(DataInicial, DataFinal: TDateTime;
  NomeArquivo: AnsiString; Documentos: TACBrECFTipoDocumentoSet;
  Finalidade: TACBrECFFinalizaArqMFD);
Var
  iRet : Integer;
  PortaSerial, ModeloECF, NumFab, ArqTmp, Prop : AnsiString;
  DiaIni, DiaFim : AnsiString;
  OldAtivo  : Boolean;
  cFinalidade:AnsiString;
begin
  NumFab      := NumSerie;
  ModeloECF   := SubModeloECF;
  PortaSerial := fpDevice.Porta;
  Prop        := IntToStr( StrToIntDef( UsuarioAtual, 1) ) ;

  LoadDLLFunctions;
  OldAtivo := Ativo;
  try
     Ativo := False;

     if (Finalidade = finMF) then
        cFinalidade := 'MF'
     else if (Finalidade in [finTDM,finNFPTDM]) then
        cFinalidade := 'TDM'
     else
        cFinalidade := 'MFD';

     if pos(fsMarcaECF, 'dataregis|termoprinter') > 0 then
      begin
        DiaIni := FormatDateTime('dd/mm/yyyy', DataInicial);
        DiaFim := FormatDateTime('dd/mm/yyyy', DataFinal);

        if (Finalidade = finMF) then
          iRet := xGera_AtoCotepe1704_Periodo_MF( PortaSerial, ModeloECF,
                                                 NomeArquivo, DiaIni, DiaFim )
        else
          iRet := xGera_AtoCotepe1704_Periodo_MFD( PortaSerial, ModeloECF,
                                                 NomeArquivo, DiaIni, DiaFim );

        if iRet <> 0 then
           raise EACBrECFERRO.Create( ACBrStr( 'Erro ao executar Gera_AtoCotepe1704_Periodo_MFD.'+sLineBreak+
                                            'Cod.: '+IntToStr(iRet) + ' - ' +
                                            GetErroAtoCotepe1704(iRet) )) ;

        if not FileExists( NomeArquivo ) then
           raise EACBrECFERRO.Create( ACBrStr( 'Erro na execu��o de Gera_AtoCotepe1704_Periodo_MFD.'+sLineBreak+
                                            'Arquivo: "'+NomeArquivo + '" n�o gerado' ))
      end
     else if (fsMarcaECF = 'elgin') then
      begin
        DiaIni := FormatDateTime('yyyymmdd', DataInicial);
        DiaFim := FormatDateTime('yyyymmdd', DataFinal);

        AbrePortaSerialDLL( PortaSerial, ExtractFilePath(NomeArquivo) );

        ArqTmp := ExtractFilePath( NomeArquivo ) + 'Memoria.tdm' ;

        iRet := xElgin_LeMemoriasBinario( ArqTmp, NumFab, true );

        if (iRet <> 1) then
           raise EACBrECFERRO.Create(ACBrStr('Erro ao executar Elgin_LeMemoriasBinario.'+sLineBreak+
                                          'Cod.: ' + IntToStr(iRet))) ;

        if not FilesExists( ArqTmp ) then
           raise EACBrECFERRO.Create(ACBrStr('Erro na execu��o de Elgin_LeMemoriasBinario.'+sLineBreak+
                                          'Arquivo bin�rio n�o gerado!'));

        iRet := xElgin_GeraArquivoATO17Binario( ArqTmp, NomeArquivo, DiaIni,
                                                DiaFim, 'D', Prop, cFinalidade);

        if (iRet <> 1) then
           raise EACBrECFERRO.Create(ACBrStr('Erro ao executar Elgin_GeraArquivoATO17Binario.'+sLineBreak+
                                          'Cod.: ' + IntToStr(iRet))) ;

        xElgin_FechaPortaSerial();
      end
     else    // Urano e demais
      begin
        if (Finalidade = finNFPTDM) then
        begin
           //TODO: gerar o "nome do arquivo" no padr�o CAT-52
           //Geralmente as DLL's precisam somente do diret�rio, o nome do arquivo � gerado automaticamente.
           //No caso da Urano voc� deve especificar o nome do arquivo (ACBr tem alguma fun��o pra gerar esse nome no padr�o cat?)
           if Length(Trim(ExtractFileName(NomeArquivo))) = 0 then
              NomeArquivo := NomeArquivo + 'CAT52.txt';
        end;

        ArqTmp := ExtractFilePath( NomeArquivo ) + 'ACBr.TDM' ;
        if FileExists( NomeArquivo ) then
           DeleteFile( NomeArquivo ) ;

        DiaIni := FormatDateTime('yyyymmdd', DataInicial);
        DiaFim := FormatDateTime('yyyymmdd', DataFinal);

        iRet := xDLLReadLeMemorias( PortaSerial, ArqTmp, NumFab, '1');

        if iRet <> 0 then
           raise EACBrECFERRO.Create( ACBrStr( 'Erro ao executar DLLReadLeMemorias.' + sLineBreak +
                                            'Cod.: '+ IntToStr(iRet) + ' - ' +
                                            GetErroAtoCotepe1704(iRet) )) ;

        iRet := xDLLATO17GeraArquivo( ArqTmp, NomeArquivo, DiaIni, DiaFim,
                                      'M', '1', cFinalidade );

        if iRet <> 0 then
           raise EACBrECFERRO.Create( ACBrStr( 'Erro ao executar DLLATO17GeraArquivo.' + sLineBreak +
                                            'Cod.: '+ IntToStr(iRet) + ' - ' +
                                            GetErroAtoCotepe1704(iRet) )) ;
      end ;
  finally
     Ativo := OldAtivo ;
  end ;
end;

procedure TACBrECFFiscNET.ArquivoMFD_DLL(ContInicial, ContFinal: Integer; NomeArquivo: AnsiString;
  Documentos: TACBrECFTipoDocumentoSet;
  Finalidade: TACBrECFFinalizaArqMFD; TipoContador: TACBrECFTipoContador);
Var
  iRet : Integer;
  PortaSerial, ModeloECF, NumFab : AnsiString;
  CooIni, CooFim, Prop, ArqTmp : AnsiString ;
  OldAtivo : Boolean ;
  cFinalidade:AnsiString;
begin
  NumFab      := NumSerie;
  ModeloECF   := SubModeloECF;
  CooIni      := IntToStrZero( ContInicial, 6 ) ;
  CooFim      := IntToStrZero( ContFinal, 6 ) ;
  Prop        := IntToStr( StrToIntDef( UsuarioAtual, 1) ) ;
  PortaSerial := fpDevice.Porta ;

  LoadDLLFunctions;
  OldAtivo := Ativo;
  try
     Ativo := False;

     if (Finalidade = finMF) then
        cFinalidade := 'MF'
     else if (Finalidade = finTDM) then
        cFinalidade := 'TDM'
     else
        cFinalidade := 'MFD';

     if pos(fsMarcaECF, 'dataregis|termoprinter') > 0 then
      begin
        iRet := xGera_PAF( PortaSerial, ModeloECF, NomeArquivo, CooIni, CooFim );

        if iRet <> 0 then
           raise EACBrECFERRO.Create( ACBrStr( 'Erro ao executar Gera_PAF.'+sLineBreak+
                                            'Cod.: '+IntToStr(iRet) + ' - ' +
                                            GetErroAtoCotepe1704(iRet) )) ;

        if not FileExists( NomeArquivo ) then
           raise EACBrECFERRO.Create( ACBrStr( 'Erro na execu��o de Gera_PAF.'+sLineBreak+
                                            ': "'+NomeArquivo + '" n�o gerado' ))
      end

     else if (fsMarcaECF = 'elgin') then
      begin
        AbrePortaSerialDLL(fpDevice.Porta, ExtractFilePath(NomeArquivo));

        ArqTmp := ExtractFilePath( NomeArquivo ) + 'Memoria.tdm' ;

        iRet := xElgin_LeMemoriasBinario( ArqTmp, NumFab, true );

        if (iRet <> 1) then
           raise EACBrECFERRO.Create(ACBrStr('Erro ao executar Elgin_LeMemoriasBinario.'+sLineBreak+
                                                   'Cod.: ' + IntToStr(iRet))) ;

        if not FilesExists( ArqTmp ) then
           raise EACBrECFERRO.Create(ACBrStr('Erro na execu��o de Elgin_LeMemoriasBinario.'+sLineBreak+
                                          'Arquivo bin�rio n�o gerado!'));

        iRet := xElgin_GeraArquivoATO17Binario( ArqTmp, NomeArquivo, CooIni,
                                                CooFim, 'C', Prop, cFinalidade);

        if (iRet <> 1) then
           raise EACBrECFERRO.Create(ACBrStr('Erro ao executar Elgin_GeraArquivoATO17Binario.'+sLineBreak+
                                          'Cod.: ' + IntToStr(iRet))) ;

        xElgin_FechaPortaSerial();
      end
     else
      begin
        ArqTmp := ExtractFilePath( NomeArquivo ) + 'ACBr.TDM' ;
        if FileExists( NomeArquivo ) then
           DeleteFile( NomeArquivo ) ;

        iRet := xDLLReadLeMemorias( PortaSerial, ArqTmp, NumFab, '1');

        if iRet <> 0 then
           raise EACBrECFERRO.Create( ACBrStr( 'Erro ao executar DLLReadLeMemorias.' + sLineBreak +
                                            'Cod.: '+ IntToStr(iRet) + ' - ' +
                                            GetErroAtoCotepe1704(iRet) )) ;

        iRet := xDLLATO17GeraArquivo( ArqTmp, NomeArquivo, CooIni, CooFim,
                                      'C', '1', cFinalidade );

        if iRet <> 0 then
           raise EACBrECFERRO.Create( ACBrStr( 'Erro ao executar DLLATO17GeraArquivo.' + sLineBreak +
                                            'Cod.: '+ IntToStr(iRet) + ' - ' +
                                            GetErroAtoCotepe1704(iRet) )) ;
      end ;
  finally
    Ativo := OldAtivo ;
  end;
end;

function TACBrECFFiscNET.GetDadosUltimaReducaoZ: AnsiString;
var
   RetCmd, S, SS : AnsiString ;
   I, ECFCRZ, ECFCRO : Integer;
   ECFVBruta : Double ;
   AliqZ: TACBrECFAliquota;
   CNFZ: TACBrECFComprovanteNaoFiscal;
begin
  // Zerar variaveis e inicializa Dados do ECF //
  InitDadosUltimaReducaoZ;

  if not Assigned( fpAliquotas ) then
    CarregaAliquotas ;

  if not Assigned( fpComprovantesNaoFiscais ) then
    CarregaComprovantesNaoFiscais ;

  with TACBrECF(fpOwner) do
  begin
    ECFCRZ := StrToIntDef( NumCRZ, 0);
    ECFCRO := StrToIntDef( NumCRO, 0);
  end;

  EnviaComando ;
  ECFVBruta := LeMoeda( 'VendaBrutaReducao['+IntToStr(ECFCRZ) + ']' ) ;

  FiscNETComando.NomeComando := 'LeTexto' ;
  FiscNETComando.AddParamString( 'NomeTexto', 'DadosUltimaReducaoZ' );
  EnviaComando ;
  RetCmd := FiscNETResposta.Params.Values['ValorTexto'] ;

  { Tamanho de Retorno 616 d�gitos BCD (308 bytes),
    com a seguinte estrutura.                                Ini  Fim
    2 Constante 00.                                            1    2
   18 GTDA GT no momento da �ltima redu��o.                    3   20
   14 CANCEL Cancelamentos                                    21   34
   14 DESCON Descontos                                        35   48
   64 TR Tributos                                             49  112
  266 TP Totalizadores Parciais Tributados                   113  378
   14 SANGRIA Sangria                                        379  392
   14 SUPRIMENTOS Suprimentos                                393  406
  126 NSI Totalizadores n�o Sujeitos ao ICMS                 407  532
   36 CNSI Contadores dos TP�s n�o Sujeitos ao ICMS          533  568
    6 COO Contador de Ordem de Opera��o                      569  574
    6 CNS Contador de Opera��es n�o Sujeitas ao ICMS         575  580
    2 AL N�mero de Al�quotas Cadastradas                     581  582
    6 DATA_PC Data do Movimento                              583  588
   14 ACRESC Acr�scimo                                       589  602
   14 ACRFIN Acr�scimo Financeiro                            603  616

  RRGGGGGGGGGGGGGGGGGGCCCCCCCCCCCCCCDDDDDDDDDDDDDDT001T002T003T004T005T006T007T008T009T010T011T012T013T014T015T016TPT00000000001TPT00000000002TPT00000000003TPT00000000004TPT00000000005TPT00000000006TPT00000000007TPT00000000008TPT00000000009TPT00000000010TPT00000000011TPT00000000012TPT00000000013TPT00000000014TPT00000000015TPT00000000016IIIIIIIIIIIIIINNNNNNNNNNNNNNFFFFFFFFFFFFFFAAAAAAAAAAAAAAUUUUUUUUUUUUUUTNS00000000001TNS00000000002TNS00000000003TNS00000000004TNS00000000005TNS00000000006TNS00000000007TNS00000000008TNS00000000009CN01CN02CN03CN04CN05CN06CN07CN08CN09COOCOOCNSCNSALDTMOVTAAAAAAAAAAAAAAFFFFFFFFFFFFFF
  0000000000000014231000000000000000000000000000001800021605001200050025000250180013001600170002110200100006000100000000000001000000000000020000000000000300000000000004010000000000050100000000000601000000000007010000000000080100000000000901000000000010010000000000110200000000001202000000000013020000000000140200000000001502000000000016020000000001001400000000010114000000000408640000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000162000019161708070000000000011100000000000000
  ....+....1....+....2....+....3....+....4....+....5....+....6....+....7....+....8....+....9....+....0....+....1....+....2....+....3....+....4....+....5....+....6....+....7....+....8....+....9....+....0....+....1....+....2....+....3....+....4....+....5....+....6....+....7....+....8....+....9....+....0....+....1....+....2....+....3....+....4....+....5....+....6....+....7....+....8....+....9....+....0....+....1....+....2....+....3....+....4....+....5....+....6....+....7....+....8....+....9....+....0....+....1....+....2....+....3....+....4....+....5....+....6....+....7....+....8....+....9....+....0....+....1....+.
  }

  { Alimenta a class com os dados atuais do ECF }
  with fpDadosReducaoZClass do
  begin
    CRO := ECFCRO;
    CRZ := ECFCRZ;
    ValorVendaBruta := ECFVBruta;

    ValorGrandeTotal := RoundTo( StrToFloatDef( copy( RetCmd, 3, 18 ), 0 ) / 100, -2 ) ;
    CancelamentoICMS := RoundTo( StrToFloatDef( copy( RetCmd, 21, 14 ), 0 ) / 100, -2 )  ;
    DescontoICMS := RoundTo( StrToFloatDef( copy( RetCmd, 35, 14 ), 0 ) / 100, -2 ) ;

    // Dados das Aliquotas //
    S := copy( RetCmd, 113, 224 ) ;  // 16 * 14
    For I := 0 to fpAliquotas.Count-1 do
    begin
      AliqZ := TACBrECFAliquota.Create ;
      AliqZ.Assign( fpAliquotas[I] );
      AliqZ.Total := RoundTo( StrToFloatDef( copy(S,(I*14)+1,14),0) / 100, -2);

      AdicionaAliquota( AliqZ );
    end ;

    SubstituicaoTributariaICMS := RoundTo( StrToFloatDef( copy( RetCmd, 337, 14 ),0 ) / 100, -2 ) ;
    NaoTributadoICMS := RoundTo( StrToFloatDef( copy( RetCmd, 351, 14 ), 0 ) / 100, -2 ) ;
    IsentoICMS := RoundTo( StrToFloatDef( copy( RetCmd, 365, 14 ), 0 ) / 100, -2 ) ;

    { TOTALIZADORES N�O FISCAIS }
    S  := Copy(RetCmd,407,126);   // 9 * 14
    SS := Copy(RetCmd,533,36);    // 9 * 4
    for I := 0 to fpComprovantesNaoFiscais.Count - 1 do
    begin
      CNFZ := TACBrECFComprovanteNaoFiscal.Create ;
      CNFZ.Assign( fpComprovantesNaoFiscais[I] );
      CNFZ.Total    := RoundTo(StrToFloatDef( copy(S,(I*14)+1,14),0) / 100, -2 ) ;
      CNFZ.Contador := StrToIntDef( copy(SS,(I*4)+1,4), 0);

      TotalizadoresNaoFiscais.Add( CNFZ ) ;
    end;

    if Length(RetCmd) > 569 then
      COO := StrToIntDef( copy( RetCmd, 569, 6 ), 0) ;
    // TODO: CNS Contador de Opera��es n�o Sujeitas ao ICMS         575  580

    if Length(RetCmd) > 587 then
       DataDoMovimento := StringToDateTimeDef( copy( RetCmd, 583, 2 ) + DateSeparator +
                                               copy( RetCmd, 585, 2 ) + DateSeparator +
                                               copy( RetCmd, 587, 2 ), 0, 'dd/mm/yy' );
    if Length(RetCmd) > 589 then
       AcrescimoICMS := RoundTo( StrToFloatDef( copy( RetCmd, 589, 14 ), 0 ) / 100, -2 ) ;

    // TODO: 14 ACRFIN Acr�scimo Financeiro                         603  616

    CalculaValoresVirtuais;
    Result := MontaDadosReducaoZ;
  end;
end;

//Constantes usada para DLL do Ato Cotepe 1704
function TACBrECFFiscNET.GetErroAtoCotepe1704(pRet: Integer): string;
const
  ERROS_DLL : Array[1..17] of String =
    ( 'Erro ao executar comando EmiteLeituraX.',
      'Erro ao executar comando EmiteLeituraMF.',
      'Erro ao executar comando EmiteLeituraFitaDetalhe.',
      'Comando Inexistente.',
      'Erro ao obter dados de impress�o.',
      'Erro ao acessar o arquivo.',
      'Erro ao executar comando.Data inv�lida.',
      'N�o existe redu��o executada na data informada.',
      'Modelo n�o permitido.',
      'Comando inv�lido.',
      'Biblioteca n�o foi encontrada.',
      'Sem Sinal de CTS.',
      'Nome do arquivo inv�lido',
      'Intervalo de data n�o permitido',
      'Caminho de origem n�o permitido.',
      'Caminho de destino n�o permitido.',
      'Erro Desconhecido.' ) ;
begin
  if (-pRet >= Low(ERROS_DLL)) and (-pRet <= High(ERROS_DLL)) then begin
     Result := ERROS_DLL[ -pRet ] ;
  end else begin
     Result := '';
  end;
end;

function TACBrECFFiscNET.TraduzirTag(const ATag : AnsiString) : AnsiString ;
const
  // <e></e>
  cExpandidoON   = #14 ;
  cExpandidoOFF  = '' ;
  cExpandidoON_B = ESC + '!' + #32 ;
  cExpandidoOFF_B= ESC + '!' + #0 ;

  // <n></n>
  cNegritoON     = ESC + 'E' ;
  cNegritoOFF    = ESC + 'F' ;
  cNegritoON_B   = ESC + '!' + #8 ;
  cNegritoOFF_B  = ESC + '!' + #0 ;

  cBarras           = #29 ;
  cBarrasAltura     = cBarras + 'h' ;
  cBarrasLargura    = cBarras + 'w' ;
  cBarrasMostrarOFF = cBarras + 'H' + #0 ;
  cBarrasMostrarON  = cBarras + 'H' + #2 ; // HRI na Base
  cBarrasFonte      = cBarras + 'f' + #0 ; // Fonte A

  cBarrasCodigo = cBarras + 'k' ;

  cEAN8     = 3 ; // <ean8></ean8>
  cEAN13    = 2 ; // <ean13></ean13>
  cINTER    = 5 ; // <inter></inter>
  cCODE39   = 4 ; // <code39></code39>
  cUPCA     = 0 ; // <upca></upca>
  cCODABAR  = 6 ; // <codabar></codabar>
  cBarraFim = #0 ;
var
  CodB : Boolean ;

  function ConfigurarBarras(const ACodigo: Integer): AnsiString;
  Var
    Altura, Largura : Integer ;
  begin
    Result := '';

    if ConfigBarras.MostrarCodigo then
       Result := Result + cBarrasMostrarON + cBarrasFonte
    else
       Result := Result + cBarrasMostrarOFF;

    if ConfigBarras.Altura > 0 then
    begin
       Altura := max(min(ConfigBarras.Altura,255),1);
       Result := Result + cBarrasAltura + chr(Altura);
    end ;

    if ConfigBarras.LarguraLinha > 0 then
    begin
       Largura := max(min(ConfigBarras.LarguraLinha,6),2);
       Result  := Result + cBarrasLargura + chr(Largura);
    end ;

    Result := Result + cBarrasCodigo + Chr( ACodigo );
  end;

begin

  // Modelos mais antigos usam comandos "B" //
  CodB := (pos(fsModeloECF,'3202DT|X5|ELGIN FIT|ELGIN K') > 0 );

  case AnsiIndexText( ATag, ARRAY_TAGS) of
     -1: Result := ATag;
     2 : Result := ifthen(CodB, cExpandidoON_B , cExpandidoON);
     3 : Result := ifthen(CodB, cExpandidoOFF_B, cExpandidoOFF);
     4 : Result := ifthen(CodB, cNegritoON_B , cNegritoON);
     5 : Result := ifthen(CodB, cNegritoOFF_B, cNegritoOFF);
     12: Result := ConfigurarBarras(cEAN8);
     13: Result := cBarraFim;
     14: Result := ConfigurarBarras(cEAN13);
     15: Result := cBarraFim;
     18: Result := ConfigurarBarras(cINTER);
     19: Result := cBarraFim;
     22: Result := ConfigurarBarras(cCODE39);
     23: Result := cBarraFim;
     28: Result := ConfigurarBarras(cUPCA);
     29: Result := cBarraFim;
     30: Result := ConfigurarBarras(cCODABAR);
     31: Result := cBarraFim;

  else
     Result := '' ;
  end;
end ;

end.

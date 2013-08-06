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

unit ACBrECFSwedaSTX ;

interface
uses ACBrECFClass, ACBrDevice, ACBrUtil, Classes, Contnrs;

const
   CFALHAS = 3 ;
  {$IFDEF LINUX}
   cLIB_Sweda = 'libconvecf.so';
  {$ELSE}
   cLIB_Sweda = 'CONVECF.DLL';
  {$ENDIF}


type

{ Classe para armazenar Cache de Informa��es do 34 }
TACBrECFSwedaInfo34 = class
  private
    FSecao: String;
    FDados: AnsiString;
  public
     property Secao : String     read FSecao write FSecao;
     property Dados : AnsiString read FDados write FDados;
end ;

{ Lista de Objetos do tipo TACBrECFSwedaCache }
TACBrECFSwedaCache = class(TObjectList)
protected
  procedure SetObject (Index: Integer; Item: TACBrECFSwedaInfo34);
  function GetObject (Index: Integer): TACBrECFSwedaInfo34;
  procedure Insert (Index: Integer; Obj: TACBrECFSwedaInfo34);
public
  function AchaSecao( Secao : String ) : Integer ;
  function Add (Obj: TACBrECFSwedaInfo34): Integer;
  property Objects [Index: Integer]: TACBrECFSwedaInfo34
    read GetObject write SetObject; default;
end;


{ Classe filha de TACBrECFClass com implementa�ao para SwedaSTX }

{ TACBrECFSwedaSTX }

TACBrECFSwedaSTX = class( TACBrECFClass )
  private
    fsSEQ       : Byte ;
    fsVerProtocolo : String ;
    fsCache34   : TACBrECFSwedaCache ;
    fsRespostasComando : String ;
    fsFalhasRX : Byte ;
    fsSubModelo:String ;
    fsApplicationPath: String ;

    xECF_AbreConnectC : Function(Meio: Integer; PathW: AnsiString): Integer; {$IFDEF LINUX} cdecl {$ELSE} stdcall {$ENDIF} ;

    xECF_DownloadMF : Function (Arquivo: AnsiString): Integer {$IFDEF LINUX} cdecl {$ELSE} stdcall {$ENDIF} ;

    xECF_DownloadMFD : Function (Arquivo: AnsiString; TipoDownload: AnsiString;
      ParametroInicial: AnsiString; ParametroFinal: AnsiString; UsuarioECF: AnsiString ):
      Integer; {$IFDEF LINUX} cdecl {$ELSE} stdcall {$ENDIF} ;

    xECF_GeraRegistrosCAT52MFD : Function (Arquivo : AnsiString ; Data: AnsiString):
      Integer; {$IFDEF LINUX} cdecl {$ELSE} stdcall {$ENDIF} ;

    xECF_ReproduzirMemoriaFiscalMFD : Function (tipo: AnsiString; fxai: AnsiString;
      fxaf:  AnsiString; asc: AnsiString; bin: AnsiString): Integer; {$IFDEF LINUX} cdecl {$ELSE} stdcall {$ENDIF} ;
    xECF_FechaPortaSerial : Function: Integer; {$IFDEF LINUX} cdecl {$ELSE} stdcall {$ENDIF} ;

    Function DescricaoErroDLL(const NErro: Integer) : String;
    procedure LoadDLLFunctions;
    procedure AbrePortaSerialDLL;

    function RemoveNulos(Str:AnsiString):AnsiString;
    Function PreparaCmd( cmd : AnsiString ) : AnsiString ;
    function CalcCheckSum(cmd: AnsiString): AnsiChar;
    function DescompactaRetorno(const Dados: AnsiString): AnsiString;
    function DescreveErro(Erro: Integer): String;
    function AjustaRetorno(Retorno: AnsiString): AnsiString;
    function AjustaValor( ADouble : Double; Decimais : Integer = 2 ) : String ;
    function ExtraiRetornoLeituras(Retorno: AnsiString): AnsiString;
  protected
    function GetDataHora: TDateTime; override ;
    function GetNumCupom: String; override ;
    function GetNumECF: String; override ;
    function GetNumLoja: String; override ;
    function GetNumSerie: String; override ;
    function GetNumSerieMFD: String; override ;    
    function GetNumVersao: String; override ;
    function GetSubModeloECF: String; override ;    
    function GetSubTotal: Double; override ;
    function GetTotalPago: Double; override ;

    function GetEstado: TACBrECFEstado; override ;
    function GetGavetaAberta: Boolean; override ;
    function GetPoucoPapel : Boolean; override ;
    function GetHorarioVerao: Boolean; override ;
    function GetChequePronto: Boolean; override ;
    function GetParamDescontoISSQN: Boolean; override ;

    function GetCNPJ: String; override ;
    function GetIE: String; override ;
    function GetIM: String; override ;
    function GetCliche: AnsiString; override ;
    function GetUsuarioAtual: String; override ;
    function GetDataHoraSB: TDateTime; override ;
    function GetPAF: String; override ;
    function GetDataMovimento: TDateTime; override ;
    function GetGrandeTotal: Double; override ;
    function GetNumCRO: String; override ;
    function GetNumCCF: String; override ;
    function GetNumGNF: String; override ;
    function GetNumGRG: String; override ;
    function GetNumCDC: String; override ;
    function GetNumCFC: String; override ;
    function GetNumGNFC: String; override ;
    function GetNumCFD: String; override ;
    function GetNumNCN: String; override ;
    function GetNumCRZ: String; override ;
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
    function GetTotalAcrescimosOPNF: Double; override ;
    function GetTotalCancelamentosOPNF: Double; override ;
    function GetTotalDescontosOPNF: Double; override ;
    function GetNumCOOInicial: String; override ;
    function GetNumUltimoItem: Integer; override ;

    function GetDadosUltimaReducaoZ: AnsiString; override ;

    Function VerificaFimLeitura(var Retorno: AnsiString;
       var TempoLimite: TDateTime) : Boolean ; override ;
    function VerificaFimImpressao(var TempoLimite: TDateTime) : Boolean ; override ;

    function GetNumReducoesZRestantes: String; override;
  public
    Constructor create( AOwner : TComponent  )  ;
    Destructor Destroy  ; override ;
    procedure Ativar ; override ;
    Function EnviaComando_ECF( cmd : AnsiString ) : AnsiString ; override ;
    Procedure IdentificaOperador ( Nome: String); override;
    Procedure AbreCupom ; override ;
    Procedure VendeItem( Codigo, Descricao : String; AliquotaECF : String;
       Qtd : Double ; ValorUnitario : Double; ValorDescontoAcrescimo : Double = 0;
       Unidade : String = ''; TipoDescontoAcrescimo : String = '%';
       DescontoAcrescimo : String = 'D'; CodDepartamento: Integer = -1 ) ; override ;
    Procedure DescontoAcrescimoItemAnterior( ValorDescontoAcrescimo : Double = 0;
       DescontoAcrescimo : String = 'D'; TipoDescontoAcrescimo : String = '%';
       NumItem : Integer = 0 ) ;  override ;
    Procedure SubtotalizaCupom( DescontoAcrescimo : Double = 0;
       MensagemRodape : AnsiString  = '') ; override ;
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
    Procedure SubtotalizaNaoFiscal( DescontoAcrescimo : Double = 0;
       MensagemRodape: AnsiString = '') ; override ;
    Procedure EfetuaPagamentoNaoFiscal( CodFormaPagto : String; Valor : Double;
       Observacao : AnsiString = ''; ImprimeVinculado : Boolean = false) ; override ;
    Procedure FechaNaoFiscal( Observacao : AnsiString = ''; IndiceBMP : Integer = 0) ; override ;
    Procedure CancelaNaoFiscal ; override ;

    Procedure LeituraX ; override ;
    Procedure LeituraXSerial( Linhas : TStringList) ; override ;
    Procedure ReducaoZ(DataHora : TDateTime = 0 ) ; override ;
    Procedure AbreRelatorioGerencial(Indice: Integer = 2) ; override ;
    Procedure LinhaRelatorioGerencial( Linha : AnsiString; IndiceBMP: Integer = 0 ) ; override ;
    Procedure AbreCupomVinculado(COO, CodFormaPagto, CodComprovanteNaoFiscal :
       String; Valor : Double) ; override ;
    Procedure LinhaCupomVinculado( Linha : AnsiString ) ; override ;
    Procedure FechaRelatorio ; override ;

    Procedure ImprimeCheque(Banco : String; Valor : Double ; Favorecido,
       Cidade : String; Data : TDateTime ;Observacao : String = '') ; override ;
    Procedure CancelaImpressaoCheque ; override ;

    Procedure MudaHorarioVerao  ; overload ; override ;
    Procedure MudaHorarioVerao( EHorarioVerao : Boolean ) ; overload ; override ;
    Procedure CorrigeEstadoErro( Reducao: Boolean = True ) ; override ;

    Procedure LeituraMemoriaFiscal( DataInicial, DataFinal : TDateTime;
       Simplificada : Boolean = False ) ; override ;
    Procedure LeituraMemoriaFiscal( ReducaoInicial, ReducaoFinal : Integer;
       Simplificada : Boolean = False ); override ;
    Procedure LeituraMemoriaFiscalSerial( DataInicial, DataFinal : TDateTime;
       Linhas : TStringList; Simplificada : Boolean = False ) ; override ;
    Procedure LeituraMemoriaFiscalSerial( ReducaoInicial, ReducaoFinal : Integer;
       Linhas : TStringList; Simplificada : Boolean = False ) ; override ;
    Procedure IdentificaPAF( NomeVersao, MD5 : String) ; override ;
    Function RetornaInfoECF( Registrador: String) : AnsiString; override ;

    Procedure AbreGaveta ; override ;

    procedure CarregaAliquotas ; override ;
    procedure LerTotaisAliquota ; override ;
    Procedure ProgramaAliquota( Aliquota : Double; Tipo : Char = 'T';
       Posicao : String = '') ; override ;
    function AchaICMSAliquota( var AliquotaICMS: String ):
       TACBrECFAliquota; override ;

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

    Procedure CortaPapel( const CorteParcial : Boolean = false) ; override ;
    procedure NaoFiscalCompleto(CodCNF: String; Valor: Double;
          CodFormaPagto: String; Obs: AnsiString; IndiceBMP : Integer);override;

    Function LeituraCMC7 : AnsiString ; override ;

    Procedure LeituraMFDSerial(DataInicial, DataFinal : TDateTime;
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

    procedure PafMF_GerarCAT52(const DataInicial: TDateTime;
      const DataFinal: TDateTime; const DirArquivos: string); override;

    function TraduzirTag(const ATag: AnsiString): AnsiString; override;
    function TraduzirTagBloco(const ATag, Conteudo : AnsiString) : AnsiString ; override;
 end ;

implementation
Uses ACBrECF, ACBrConsts,
     SysUtils, IniFiles,
   {$IFDEF COMPILER6_UP} DateUtils, StrUtils, {$ELSE} ACBrD5, Windows,{$ENDIF}
     Math;

{ --------------------------- TACBrECFSwedaCache ---------------------------- }
function TACBrECFSwedaCache.AchaSecao(Secao: String): Integer;
Var I : Integer ;
begin
  I := 0 ;
  Result := -1 ;
  while (Result < 0) and (I < Count) do
  begin
    if Secao = Objects[I].Secao then
       Result := I ;

    Inc( I ) ;
  end ;
end;

function TACBrECFSwedaCache.Add(Obj: TACBrECFSwedaInfo34): Integer;
begin
  Result := inherited Add(Obj) ;
end;

function TACBrECFSwedaCache.GetObject(Index: Integer): TACBrECFSwedaInfo34;
begin
  Result := inherited GetItem(Index) as TACBrECFSwedaInfo34 ;
end;

procedure TACBrECFSwedaCache.Insert(Index: Integer;
  Obj: TACBrECFSwedaInfo34);
begin
  inherited Insert(Index, Obj);
end;

procedure TACBrECFSwedaCache.SetObject(Index: Integer;
  Item: TACBrECFSwedaInfo34);
begin
  inherited SetItem (Index, Item) ;
end;


{ ----------------------------- TACBrECFSwedaSTX ------------------------------ }

constructor TACBrECFSwedaSTX.create( AOwner : TComponent ) ;
begin
  inherited create( AOwner ) ;

  fpDevice.HandShake := hsDTR_DSR ;
  fpPaginaDeCodigo   := 1252 ;

  { Variaveis internas dessa classe }
  fsVerProtocolo     := '' ;
  fsSubModelo        := '';
  fsApplicationPath  := '';
  fsCache34          := TACBrECFSwedaCache.create( True );
  fsSEQ              := 42 ;
  fsRespostasComando := '' ;
  fsFalhasRX         := 0 ;
  fpModeloStr        := 'SwedaSTX' ;
  fpRFDID            := 'SW' ;
end;

destructor TACBrECFSwedaSTX.Destroy;
begin
  fsCache34.Free ;
   
  inherited Destroy ;
end;

procedure TACBrECFSwedaSTX.Ativar;
Var
  RetCmd : AnsiString ;
  LargFonte, AreaImp : Integer ;
begin
  if not fpDevice.IsSerialPort  then
     raise EACBrECFERRO.Create(ACBrStr('A impressora: '+fpModeloStr+' requer'+sLineBreak+
                            'Porta Serial:  (COM1, COM2, COM3, ...)'));

  //fpDevice.HandShake := hsDTR_DSR ;
  inherited Ativar ; { Abre porta serial }

  fsVerProtocolo    := '' ;
  fsSubModelo       := '' ;
  fsApplicationPath := ExtractFilePath( ParamStr(0) );
  fsCache34.Clear ;
  fsRespostasComando := '' ;
  fsFalhasRX         := 0 ;
  
  fpColunas := 56;
  fpMFD     := True ;
  fpTermica := True ;

  try
     { Testando a comunica�ao com a porta }
     fsVerProtocolo := Trim(copy( TACBrECF(fpOwner).RetornaInfoECF( 'I1' ), 82, 1)) ;

     if fsVerProtocolo = '' then
        raise EACBrECFNaoInicializado.Create( ACBrStr(
                 'Erro inicializando a impressora '+fpModeloStr ));

     fpDecimaisPreco := 0 ;
     RetCmd := TACBrECF(fpOwner).RetornaInfoECF( 'H2' ) ;
     if copy(RetCmd,10,1) = 'S' then
        fpDecimaisPreco := 2 ;
     if copy(RetCmd,11,1) = 'S' then
        fpDecimaisPreco := fpDecimaisPreco + 1 ;

     fpDecimaisQtd := StrToIntDef(copy( TACBrECF(fpOwner).RetornaInfoECF( 'U2' ),  1, 1), 2 ) ;

     try
        // Tentando calcular o numero de Colunas //
        RetCmd := TACBrECF(fpOwner).RetornaInfoECF( 'R2' ) ;
        LargFonte := StrToInt( copy( RetCmd, IfThen( copy(RetCmd, 36, 1) = 'A', 37, 41 ), 2) );
        AreaImp   := StrToInt( copy( RetCmd, 45, 4) );
        fpColunas := Trunc( AreaImp / LargFonte ) ;
     except
     end ;

  except
     Desativar ;
     raise ;
  end ;
end;

Function TACBrECFSwedaSTX.EnviaComando_ECF( cmd : AnsiString ) : AnsiString ;
Var ErroMsg, Mensagem : String ;
    FalhasTX : Integer;
    ACK_ECF  : Byte ;
begin
   Result             := '' ;
   fpComandoEnviado   := '' ;
   fpRespostaComando  := '' ;
   fsRespostasComando := '' ;
   fsFalhasRX         := 0 ;

   if (LeftStr(cmd,2) <> '34') then
      fsCache34.Clear ;         // Limpa o Cache do 34

   { Codificando CMD de acordo com o protocolo da SwedaSTX }
   cmd := PreparaCmd( cmd ) ;

   ACK_ECF  := 0 ;
   FalhasTX := 0 ;

   fpDevice.Serial.DeadlockTimeout := 2000 ; { Timeout p/ Envio }

   while (chr(ACK_ECF) <> ACK) do
   begin
      fpDevice.Serial.Purge ;                   { Limpa a Porta }

      if not TransmiteComando( cmd ) then
         continue;

      try
         { espera ACK chegar na Porta por 7s }
         try
            ACK_ECF := fpDevice.Serial.RecvByte(TimeOut * 1000 ) ;
         except
         end ;

         if ACK_ECF = 0 then
            raise EACBrECFSemResposta.create( ACBrStr(
                     'Impressora '+fpModeloStr+' n�o responde (ACK = 0)') )
         else if chr(ACK_ECF) = NAK then    { retorno em caracter 21d=15h=NAK }
            raise EACBrECFSemResposta.create( ACBrStr(
                  'Impressora '+fpModeloStr+' n�o reconheceu o Comando'+
                  sLineBreak+' (ACK = 21). Falha: '+IntToStr(FalhasTX)) )
         else if chr(ACK_ECF) <> ACK then
            raise EACBrECFSemResposta.create( ACBrStr(
                  'Erro. Resposta da Impressora '+fpModeloStr+' inv�lida'+
                  sLineBreak+' (ACK = '+IntToStr(ACK_ECF)+')') ) ;
      except
         on E : EACBrECFSemResposta do
          begin
            fpDevice.Serial.Purge ;

            Inc( FalhasTX ) ;
            if FalhasTX < CFALHAS then
               Sleep(100)
            else
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

   { Chama Rotina da Classe m�e TACBrClass para ler Resposta. Se houver
     falha na leitura LeResposta dispara Exce�ao.
     Resposta fica gravada na v�riavel "fpRespostaComando" }
   LeResposta ;

   { Captura informa��es do Ultimo Bloco Enviado }
   Mensagem := copy(fpRespostaComando,6,4) ;

   fpRespostaComando := fsRespostasComando ;   // Respostas Acumuladas

   { Limpando de "fpRespostaComando" os Status n�o solicitados }
   fpRespostaComando := AjustaRetorno( fpRespostaComando  );

   ErroMsg := DescreveErro( StrToIntDef(Mensagem,-1) ) ;

   if ErroMsg <> '' then
    begin
      ErroMsg := ACBrStr('Erro retornado pela Impressora: '+fpModeloStr+
                 sLineBreak+sLineBreak+
                 'Erro ('+Mensagem+') '+ErroMsg ) ;

      if Trim(Mensagem) = '125' then
         DoOnErrorSemPapel
      else
         raise EACBrECFSemResposta.create(ErroMsg) ;
    end
   else
      Sleep( IntervaloAposComando ) ;  { Pequena pausa entre comandos }

   { Descompactando Strings dentro do Retorno }
   Result := DescompactaRetorno( fpRespostaComando ) ;
end;

function TACBrECFSwedaSTX.DescreveErro( Erro : Integer ) : String ;
begin
  Result := '' ;

  case Erro of
     -1 : Result := 'Erro na Interpreta��o da Resposta do ECF' ;
      0 : Result := '' ;

    002 : Result := 'Documento j� Cancelado' ;
    003 : Result := 'Documento j� foi Totalmente Pago' ;
    004 : Result := 'Documento ainda n�o foi Totalmente Pago' ;
    005 : Result := 'Documento j� foi Totalizado' ;
    006 : Result := 'Item Inv�lido' ;
    007 : Result := 'Item Cancelado' ;
    008 : Result := 'Total apurado igual a Zero' ;
    009 : Result := 'Acr�scimo j� aplicado sobre este Item' ;
    010 : Result := 'N�o h� Acr�scimo sobre este Item' ;
    011 : Result := 'Desconto j� aplicado sobre este Item' ;
    012 : Result := 'N�o h� Desconto sobre este Item' ;
    013 : Result := 'Valor de Desconto superior ao Total do Item' ;
    014 : Result := 'Acr�scimo j� aplicado em Subtotal' ;
    015 : Result := 'N�o h� Acr�scimo aplicado no Subtotal' ;
    016 : Result := 'Desconto j� aplicado em Subtotal' ;
    017 : Result := 'N�o h� Desconto aplicado no Subtotal' ;
    018 : Result := 'Valor de Desconto superior ao Total do Documento' ;
    019 : Result := 'Meio de Pagamento n�o programado' ;
    020 : Result := 'Atingido Limite de Itens por Cupom' ;
    021 : Result := 'Al�quota de Imposto n�o programada' ;
    022 : Result := 'Altera��o de Estilo de Fonte n�o permitida nesse comando' ;
    023 : Result := 'Erro na Sintaxe do Comando Enviado' ;
    025 : Result := 'Informado Valor Nulo' ;
    027 : Result := 'Data com formato inv�lido' ;
    028 : Result := 'Hora com formato inv�lido' ;
    029 : Result := 'Comando n�o reconhecido' ;
    030 : Result := 'Tabela Cheia' ;
    031 : Result := 'Faixa Informada � Inv�lida' ;
    032 : Result := 'Tentativa de registro em um mesmo comprovante de '+
                     'opera��es n�o fiscais cadastradas com sinais distintos' ;
    033 : Result := 'Informado Sinal Inv�lido' ;
    034 : Result := 'Excedida capacidade de pagamento por meio de CCD' ;
    035 : Result := 'Opera��o de TEF informada pelo comando de abertura do comprovante n�o encontrada' ;
    036 : Result := 'Classifica��o do meio de pagamento inv�lida' ;
    037 : Result := 'T�tulo informado na abertura de Relat�rio Gerencial n�o encontrado' ;
    040 : Result := 'Mensagem: Abertura do Movimento' ;
    041 : Result := 'Denomina��o informada no Registro de Opera��o n�o fiscal n�o encontrada ' ;
    042 : Result := 'Valor total do Item excedido' ;
    043 : Result := 'Valor do estorno excede a soma dos pagamentos registrados no meio indicado' ;
    044 : Result := 'Valor efetivado � insuficiente para o pagamento!' ;
    050 : Result := 'Campo de Descri��o n�o informado' ;
    058 : Result := 'Comando ou opera��o inv�lida!' ;
    060 : Result := '� necess�ria a emiss�o do documento de Redu��o Z!' ;
    061 : Result := 'O ECF est� em Modo de Interven��o T�cnica!';
    062 : Result := 'O ECF est� inativo!';
    067 : Result := 'Permitida uma �nica reimpress�o!';
    068 : Result := 'Erro f�sico de grava��o na mem�ria fiscal!';
    074 : Result := 'Ejetando folha solta...';
    080 : Result := 'Esgotamento de Dispositivo: Mem�ria Fiscal';
    087 : Result := 'Leiaute de cheque n�o programado!';
    092 : Result := 'J� emitida a 2� via!';
    093 : Result := 'Excede o limite de 24 parcelas!';
    094 : Result := 'Informado n�mero incorreto da parcela!';
    095 : Result := 'Informado valor unit�rio inv�lido!';
    096 : Result := 'N�o foram estornados os Comprovantes de Cr�dito ou D�bito emitidos!';
    098 : Result := 'Processando...';
    099 : Result := 'Confirme';
    103 : Result := 'Inserir a frente para preenchimento!';
    104 : Result := 'Inserir o verso para preenchimento!';
    105 : Result := 'Inserir o cheque para preenchimento!';
    109 : Result := 'Inserir cheque.';
    110 : Result := 'Resultado de leitura MICR-CMC7';
    111 : Result := 'Resultado de leitura MICR-E13B';
    112 : Result := 'N�o foi detectado nenhum caracter!';
    113 : Result := 'Um dos caracteres n�o foi reconhecido!';
    114 : Result := 'As dimens�es do cheque est�o fora das especifica��es! ';
    115 : Result := 'Erro na impressora durante o processamento!';
    116 : Result := 'A tampa foi aberta durante a leitura!';
    117 : Result := 'Fonte inv�lida!';
    120 : Result := 'Erro de grava��o no dispositivo de mem�ria de fita-detalhe!';
    121 : Result := 'Erro mec�nico na impressora!';
    122 : Result := 'Erro na guilhotina!';
    123 : Result := 'Erro recuper�vel!';
    124 : Result := 'Tampa Aberta' ;
    125 : Result := 'Sem Papel' ;
    126 : Result := 'Avan�ando Papel' ;
    127 : Result := 'Substituir Bobina' ;
    128 : Result := 'Falha de comunica��o com o mecanismo de impress�o!';
    130 : Result := 'N�o emitida redu��o Z!';
    131 : Result := 'Totalizador desabilitado!';
    132 : Result := 'Esgotamento de Dispositivo: Mem�ria de Fita-Detalhe';
    133 : Result := 'O ECF est� emitindo a Redu��o Z para entrada em Interven��o T�cnica...';
    134 : Result := 'Transmiss�o de leitura via porta de comunica��o serial abortada';
    135 : Result := 'J� emitido o Cupom Adicional!';
    136 : Result := 'Indicado CDC Inv�lido';
    139 : Result := 'A cabe�a de impress�o t�rmica est� levantada!';
    140 : Result := 'Status da cabe�a de impress�o t�rmica: Temperatura elevada!';
    141 : Result := 'Status da cabe�a de impress�o t�rmica: Tens�o inadequada!';
    142 : Result := 'Informado c�digo de barras Inv�lido!';
    148 : Result := 'Quantidade inv�lida!';
    149 : Result := 'Desconto sobre servi�o desabilitado';
    151 : Result := 'Diverg�ncia de rel�gio!';
    156 : Result := 'Fun��o MICR n�o dispon�vel!';
    157 : Result := 'Fun��o de preenchimento de cheques n�o dispon�vel!';
    159 : Result := 'Preenchendo...';
    160 : Result := 'N�o h� acr�scimo ou desconto aplicado sobre o item';
    161 : Result := 'N�o h� acr�scimo ou desconto aplicado sobre o subtotal';
    162 : Result := 'N�o cancelado a opera��o de acr�scimo aplicada sobre o item ap�s o desconto';
    163 : Result := 'N�o cancelado a opera��o de desconto aplicada sobre o item ap�s o acr�scimo';
    164 : Result := 'N�o cancelado a opera��o de acr�scimo aplicada sobre o subotal ap�s o desconto';
    165 : Result := 'N�o cancelado a opera��o de desconto aplicada sobre o subotal ap�s o acr�scimo';
    166 : Result := 'O mecanismo de impress�o detectado n�o pertence a este modelo de ECF';
    170 : Result := 'C�digo de barras n�o dispon�vel!';
    171 : Result := 'Erro MICR: Falha de acionamento do leitor!';
    172 : Result := 'Mensagem: preenchimento de cheque conclu�do!';
    187 : Result := 'Identificar-se!';
    193 : Result := 'Falha de comunica��o na transmiss�o das informa��es' ;
    195 : Result := 'Enviar imagem';
    196 : Result := 'Dimens�es inv�lidas!';
    197 : Result := 'Falha no envio da imagem!';
    198 : Result := 'Processando....';
    200 : Result := 'Efetuando leitura MICR...';
    201 : Result := 'Pre�o unit�rio inv�lido!';
    202 : Result := 'J� foi impressa a identifica��o do consumidor!';
    203 : Result := 'Erro no formato do logotipo!';
    204 : Result := 'Fun��o de autentica��o n�o dispon�vel!';
    205 : Result := 'Autentica��o cancelada!';
    206 : Result := 'Inserir documento!';
    207 : Result := 'Autenticando...';
    208 : Result := 'Limitado a 5 autentica��es!';
    209 : Result := 'Erro nos par�metros do comando de repeti��o';
    215 : Result := 'Centavos n�o habilitados!';
    216 : Result := 'A data est� avan�ada em mais de 30 dias em rela��o ao '+
                    '�ltimo documento emitido pelo '+
                    'ECF. Envie o comando de programa��o do rel�gio para verifica��o.';
    217 : Result := 'Preparando a impress�o da fita-detalhe...';
    220 : Result := 'Mensagem de progress�o durante a emiss�o da Redu��o Z!';
    24,38,39,45..47,65,66,69..73,75..79,81..86,88..91,97,100..102,106,118,119,
    129,137,138,145..147,150,152..155,158,173..183,185,186,188..191,199,210,
    219,221,225,230,235..237,241,242,244..248
        : Result := 'Chamar Assist�ncia T�cnica' ;
    243 : Result := 'Redu��o n�o encontrada!' ;
    249 : Result := 'Totalizadores de ISSQN desabilitados, Inscri��o Municipal n�o programada!' ;
    250 : Result := 'Totalizadores de ICMS desabilitados, CNPJ n�o programado!' ;
  else
    Result := 'Consulte o manual' ;
  end ;
end ;

function TACBrECFSwedaSTX.VerificaFimLeitura(var Retorno: AnsiString;
   var TempoLimite: TDateTime) : Boolean ;
Var
  LenRet, PosETX, PosSTX, Erro : Integer ;
  Bloco, Tarefa, MsgLog : AnsiString ;
  Sequencia, ACK_PC : Byte ;
  Tipo : AnsiChar ;
begin
  LenRet := Length(Retorno) ;
  Result := False ;

  // DEBUG
  //GravaLog( 'Retorno: '+Retorno);

  if LenRet < 5 then
     exit ;

  PosSTX := Pos(STX,Retorno);
  if PosSTX < 1 then     // N�o recebeu o STX
     exit
  else if PosSTX > 1 then
     Retorno := copy(Retorno, PosSTX, Length(Retorno) ) ;  // STX deve estar no inicio.

  PosETX := Pos(ETX, Retorno) ;
  if PosETX < 1 then    // N�o recebeu ETX
     exit ;

  if (LenRet = PosETX) then  // Sem CHK
     exit ;

  { Ok, temos um bloco completo... Vamos trata-lo}
  Bloco     := copy(Retorno, 1, PosETX+1) ;
  Result    := True ;
  Sequencia := Ord( Bloco[2] ) ;
  Tarefa    := copy(Bloco,3,2) ;
  Tipo      := Bloco[5] ;
  Erro      := StrToIntDef( copy(Bloco,6,4), 0 ) ;
  MsgLog    := '';

  { ECF est� enviando dados... aumente o TimeOut }
  TempoLimite := IncSecond(now, TimeOut);

  { Verificando a Sequencia }
  if Sequencia <> fsSEQ then
  begin
     Result := False ;  // Ignore o Bloco, pois n�o � a resposta do CMD solicitado
     MsgLog := 'Sequencia diferente da enviada ('+IntToStr(fsSEQ)+')' ;
  end ;

  if Result and (Tipo = '!') then  // Bloco de Satus n�o solicitado, Verificando
     Result := (Erro in [ 52, 110, 216, 240 ]) ;

  { Verificando o CheckSum }
  ACK_PC := Ord(ACK) ;

  if Result and
    ( CalcCheckSum(LeftStr(Bloco,Length(Bloco)-1)) <> RightStr(Bloco,1) ) then
  begin
    ACK_PC := Ord(NAK) ;  // Erro no CheckSum, retornar NACK
    if fsFalhasRX > CFALHAS then
       raise EACBrECFERRO( ACBrStr('Erro no digito Verificador da Resposta.'+sLineBreak+
                        'Falha: '+IntToStr(fsFalhasRX)) ) ;
    Inc( fsFalhasRX ) ;  // Incrementa numero de Falhas
    Result := False ;
  end ;

  fpDevice.Serial.SendByte(ACK_PC);

  if Result then
     fsRespostasComando := fsRespostasComando + Retorno ;  // Salva este Bloco

  if (chr(ACK_PC) = ACK) then           // ACK OK ?
  begin
     if Tipo = '-' then            // Erro ocorrido,
        AguardaImpressao := False  //   portanto, Desliga AguardaImpressao (caso estivesse ligado)
     else if Result and (Tipo = '!') then
        MsgLog := 'Bloco "!" considerado'
     else if Tipo <> '+' then      // Tipo n�o � '-' nem '+', portanto n�o � o Ultimo Bloco
        Result := False ;          //   portanto Zera para Ler proximo Bloco
  end ;

  if not Result then
  begin
    if (Tipo in ['-','+']) then
       MsgLog := MsgLog + ' - Bloco removido:' ;
    Retorno := copy(Retorno, PosETX+2, Length(Retorno) ) ;
  end ;

  if MsgLog <> '' then
   GravaLog( '         VerificaFimLeitura, '+MsgLog+' Seq:'+IntToStr(Sequencia)+
           ' Tipo:'+Tipo+' Tarefa:'+Tarefa+' Erro:'+IntToStr(Erro)+
           ' ACK:'+IntToStr(ACK_PC)+' - Bloco:'+Bloco  ) ;

end;

function TACBrECFSwedaSTX.VerificaFimImpressao(var TempoLimite: TDateTime): Boolean;
Var Cmd, Ret, RetCmd : AnsiString ;
    wACK : Byte ;
    I : Integer ;
begin
  { Essa fun��o s� � chamada se AguardaImpressao = True,
    Como essa fun��o � executada dentro da "LeResposta", que por sua vez foi
    chamada por "EnviaComando", n�o podemos usar o m�todo "EnviaComando" (ou
    teriamos uma chamada recursiva infinita), por isso o Loop abaixo envia o
    comando '34' diretamente para a Serial, e aguarda por 5 segundos a resposta...
    Se a SwedaSTX conseguir responder, significa que a Impress�o Terminou }
  Result := false ;

  if not EmLinha() then
   begin
     Sleep(100) ;
     GravaLog('         VerificaFimImpressao: ECF fora de linha') ;
   end
  else
   begin
     RetCmd := '' ;
     Cmd    := PreparaCmd( '34' ) ;           // Pede Status //

     try
        GravaLog('         VerificaFimImpressao: Pedindo o Status. Seq:'+IntToStr(fsSEQ)) ;

        fpDevice.Serial.Purge ;          // Limpa buffer de Entrada e Saida //
        fpDevice.EnviaString( Cmd );     // Envia comando //

        wACK := fpDevice.Serial.RecvByte( TimeOut * 1000 ) ; // espera ACK chegar na Porta  //

        if wACK = 6 then   // ECF Respondeu corretamente, portanto est� trabalhando //
        begin
           GravaLog('         VerificaFimImpressao: ACK = 6, OK... Aguardando Bloco') ;

           // Aguarda por Bloco at� 6 seg //
           I := 0 ;
           while (I < 30) and (not Result) do
           begin
              Inc( I ) ;
              TempoLimite := IncSecond(now, TimeOut);
              try
                 Ret := fpDevice.Serial.RecvPacket(200) ;
              except
              end ;

              if Length( Ret ) > 0 then
              begin
                 // DEBUG
                 //GravaLog('         VerificaFimImpressao: ECF respondeu, continue esperando' ) ;
                 I := 0 ;
              end ;

              RetCmd := RetCmd + Ret ;

              // DEBUG
              //GravaLog('         VerificaFimImpressao: I: '+IntToStr(I)+' Bloco Lido: '+RetCmd ) ;
              Result :=  VerificaFimLeitura( RetCmd, TempoLimite) ;
           end ;

           Result := Result and (pos(copy(RetCmd,11,1), 'ACDGI') > 0) ;
        end ;
     except
       On E : Exception  do
       begin
         GravaLog('         VerificaFimImpressao: Exception:'+E.Message ) ;
       end ;
     end ;
   end ;
end;

Function TACBrECFSwedaSTX.PreparaCmd( cmd : AnsiString ) : AnsiString ;
begin
  Result := '' ;

  if cmd = '' then exit ;

  Inc(fsSEQ) ;
  if fsSEQ = 255 then
     fsSEQ := 43 ;

 cmd := STX + AnsiChar(chr( fsSEQ ))+  cmd + ETX ;   { Prefixo ESC }
 //cmd := #02+chr( fsSEQ )+'15'#03;
// cmd := #02+chr( fsSEQ )+'34I1'#03;

  Result := cmd + CalcCheckSum( cmd ) ;
end ;

Function TACBrECFSwedaSTX.CalcCheckSum( cmd : AnsiString ) : AnsiChar ;
Var A, iSoma, LenCmd, CheckSum : Integer ;
begin
  { Calculando a Soma dos caracteres ASC }
  LenCmd := Length( cmd ) ;
  iSoma := 0 ;
  For A := 1 to LenCmd  do
     iSoma := iSoma + ord( cmd[A] ) ;

  { Calculando o digito verificado }
  CheckSum := iSoma mod 256 ;

  Result := AnsiChar( Chr( CheckSum ) ) ;
end ;

{ Remove Blocos de Resposta de Status n�o solicitados  (envio autom�tico pelo ECF)}
Function TACBrECFSwedaSTX.AjustaRetorno(Retorno: AnsiString) : AnsiString ;
Var
  LenRet, PosETX, PosSTX, Erro : Integer ;
  Bloco, Tipo : AnsiString ;
begin
  LenRet := Length(Retorno) ;
  Result := Retorno ;

  if LenRet < 5 then
     exit ;

  PosSTX := Pos(STX,Result);
  if PosSTX < 1 then
     Result := ''               // N�o recebeu o STX, invalida Retorno
  else if PosSTX > 1 then
     Result := copy(Result, PosSTX, Length(Result) ) ;  // Deve iniciar em STX

  while PosSTX > 0 do
  begin
     PosETX := PosEx(ETX, Result, PosSTX ) ;
     if PosETX < 1 then          // Ainda n�o recebeu o ETX final
        break ;

     Bloco := copy(Result, PosSTX, PosETX-PosSTX + 2  ) ;  // Pega um Bloco; +2 para pegar CHK
     Tipo  := copy(Bloco,5,1) ;

     if Tipo = '!' then  // Bloco de Status nao solicitado, excluindo
     begin
        Erro := StrToIntDef( copy(Bloco,6,4), 0 ) ;

        if Erro <> 110 then  // 110 = Leitura de CMC7 completada, mantenha o bloco
        begin
           Delete(Result, PosSTX, PosETX-PosSTX + 2 ) ;
           PosETX := max(PosSTX - 2,0) ;
        end;
     end ;

     PosSTX := PosEx( STX , Result, PosETX);  // Acha inicio do proximo Bloco
  end ;
end ;

{ Remove Blocos de Resposta de Status n�o solicitados  (envio autom�tico pelo ECF)}
Function TACBrECFSwedaSTX.ExtraiRetornoLeituras(Retorno: AnsiString) : AnsiString ;
Var
  PosETX, PosSTX : Integer ;
  Bloco, Tipo : AnsiString ;
begin
  Result := '' ;

  PosSTX := Pos(STX,Retorno);
  while PosSTX > 0 do
  begin
     PosETX := PosEx(ETX, Retorno, PosSTX ) ;
     if PosETX < 1 then          // Ainda n�o recebeu o ETX final
        break ;

     Bloco := copy(Retorno, PosSTX, PosETX-PosSTX + 2  ) ;  // Pega um Bloco; +2 para pegar CHK
     Tipo  := copy(Bloco,5,1) ;

     if Tipo = '>' then  // Bloco de Resposta
        Result := Result + copy(Bloco,7, Length(Bloco) - 8 ) ;

     PosSTX := PosEx( STX , Retorno, PosETX);
  end ;

  Result := StringReplace(Result, #151 ,'-' ,[rfReplaceAll] );
end ;

function TACBrECFSwedaSTX.DescompactaRetorno( const Dados : AnsiString ) : AnsiString ;
Var P      : Integer ;
    AChar  : AnsiChar ;
    NTimes : Byte ;
begin
   Result   := Dados ;

   P := pos(ESC, Result) ;
   while (P > 0) do
   begin
      AChar  := Result[P-1] ;

      if AChar <> ETX then  // N�o usa caso ESC esteja no CHK
      begin
         NTimes := ord( copy( Result, P+1, 1)[1] ) - 31 ;
         Result := StuffString(Result, P, 2, StringOfChar(AChar,NTimes) ) ;
      end ;

      P := PosEx( ESC, Result, P+1) ;
   end ;
end ;

function TACBrECFSwedaSTX.AjustaValor( ADouble : Double;
  Decimais : Integer = 2 ) : String ;
begin
  Result := FormatFloat('0.'+StringOfChar('0',Decimais) ,ADouble) ;
  Result := Trim(StringReplace(Result,DecimalSeparator,',',[])) ;
end;

procedure TACBrECFSwedaSTX.EspelhoMFD_DLL(COOInicial, COOFinal: Integer;
  NomeArquivo: AnsiString; Documentos: TACBrECFTipoDocumentoSet);
Var
  Resp : Integer ;
  CooIni, CooFim : AnsiString ;
  OldAtivo : Boolean ;
begin
  LoadDLLFunctions ;

  OldAtivo := Ativo ;
  try
    AbrePortaSerialDLL ;

    CooIni := IntToStrZero( COOInicial, 6 ) ;
    CooFim := IntToStrZero( COOFinal, 6 ) ;
    Resp := xECF_DownloadMFD( NomeArquivo, '2', CooIni, CooFim, '0');
    if (Resp <> 1) then
      raise EACBrECFERRO.Create( ACBrStr( 'Erro ao executar ECF_DownloadMFD.'+sLineBreak+
                                       DescricaoErroDLL(Resp) ))
  finally
    xECF_FechaPortaSerial ;
    Ativo := OldAtivo ;
  end ;

  if not FileExists( NomeArquivo ) then
     raise EACBrECFERRO.Create( ACBrStr( 'Erro na execu��o de ECF_DownloadMFD.'+sLineBreak+
                            'Arquivo: "'+NomeArquivo + '" n�o gerado' ))
end;

procedure TACBrECFSwedaSTX.EspelhoMFD_DLL(DataInicial, DataFinal: TDateTime;
  NomeArquivo: AnsiString; Documentos: TACBrECFTipoDocumentoSet);
Var
  Resp : Integer ;
  DiaIni, DiaFim : AnsiString ;
  OldAtivo : Boolean ;
begin
  LoadDLLFunctions ;
  OldAtivo           := Ativo ;
  try
    AbrePortaSerialDLL ;

    DiaIni := FormatDateTime('dd"/"mm"/"yy', DataInicial) ;
    DiaFim := FormatDateTime('dd"/"mm"/"yy', DataFinal) ;

    Resp := xECF_DownloadMFD( NomeArquivo, '1', DiaIni, DiaFim, '0');
    if (Resp <> 1) then
      raise EACBrECFERRO.Create( ACBrStr( 'Erro ao executar ECF_DownloadMFD.'+sLineBreak+
                                       DescricaoErroDLL(Resp) ))
  finally
    xECF_FechaPortaSerial ;
    Ativo := OldAtivo ;
  end ;

  if not FileExists( NomeArquivo ) then
     raise EACBrECFERRO.Create( ACBrStr( 'Erro na execu��o de ECF_DownloadMFD.'+sLineBreak+
                            'Arquivo: "'+NomeArquivo+'" n�o gerado' ))
end;

procedure TACBrECFSwedaSTX.ArquivoMFD_DLL(ContInicial, ContFinal: Integer;
  NomeArquivo: AnsiString; Documentos: TACBrECFTipoDocumentoSet;
  Finalidade: TACBrECFFinalizaArqMFD;
  TipoContador: TACBrECFTipoContador);
Var
  Resp : Integer ;
  CooIni, CooFim : AnsiString ;
  OldAtivo : Boolean ;
  Tipo :AnsiString;
begin
  LoadDLLFunctions ;

  OldAtivo := Ativo ;
  try
    AbrePortaSerialDLL ;

    if TipoContador = tpcCRZ then
     begin
       {Por CRZ}
       CooIni := IntToStrZero( ContInicial, 4 ) ;
       CooFim := IntToStrZero( ContFinal, 4 ) ;
     end
    else
     begin
       {POr COO}
       CooIni  := IntToStrZero( ContInicial, 7 );
       CooFim  := IntToStrZero( ContFinal, 7 ) ;
     end ;

    case Finalidade of
       finMF  : Tipo := '1';
       finTDM : Tipo := '3';
    else
       Tipo := '2' ;
    end;

    Resp := xECF_ReproduzirMemoriaFiscalMFD(Tipo , CooIni, CooFim, NomeArquivo, '');
    if (Resp <> 1) then
      raise EACBrECFERRO.Create( ACBrStr( 'Erro ao executar xECF_ReproduzirMemoriaFiscalMFD.'+sLineBreak+
                                       DescricaoErroDLL(Resp) ))
  finally
    xECF_FechaPortaSerial ;
    Ativo := OldAtivo ;
  end ;

  if not FileExists( NomeArquivo ) then
     raise EACBrECFERRO.Create( ACBrStr( 'Erro na execu��o de ECF_DownloadMFD.'+sLineBreak+
                            'Arquivo: "'+NomeArquivo + '" n�o gerado' ))
end;

procedure TACBrECFSwedaSTX.PafMF_GerarCAT52(const DataInicial: TDateTime;
   const DataFinal: TDateTime; const DirArquivos: string);
var
  Resp: Integer;
  Dia, FileMF : AnsiString;
  OldAtivo: Boolean;
  DataArquivo: TDateTime;
begin
  LoadDLLFunctions;

  OldAtivo := Ativo ;
  try
    // a sweda n�o possui a gera��o do CAT52 por per�odo, mas pode-se
    // gerar arquivos de um arquivo MF, ent�o baixamos a MF do ECF
    // e rodamos um loop com a data gerando o arquivo para cada dia dentro
    // do per�odo
    AbrePortaSerialDLL;
    FileMF := 'ACBr.MF';

    // fazer primeiro o download da MF
    Resp := xECF_DownloadMF(FileMF);
    if (Resp <> 1) then
       raise EACBrECFERRO.Create( ACBrStr( 'Erro ao executar ECF_DownloadMF.'+sLineBreak+
                                  DescricaoErroDLL(Resp) ));

    // gerar o arquivo para cada dia dentro do per�odo a partir da
    // MFD baixada da impressora fiscal
    DataArquivo := DataInicial;
    repeat
      Dia := FormatDateTime('dd/mm/yyyy', DataInicial);

      Resp := xECF_GeraRegistrosCAT52MFD( FileMF, Dia ) ;
      if (Resp <> 1) then
      raise EACBrECFERRO.Create( ACBrStr( 'Erro ao executar ECF_DownloadMF.'+sLineBreak+
                                 DescricaoErroDLL(Resp) + sLineBreak +
                                 'Para a data de: "' + Dia + '"' ));

      // pr�ximo dia
      DataArquivo := IncDay( DataArquivo, 1 );

    until DataArquivo > DataFinal;

  finally
     xECF_FechaPortaSerial ;
     try
        Ativo := OldAtivo ;
     except
     end;
  end;
end;

procedure TACBrECFSwedaSTX.ArquivoMFD_DLL(DataInicial, DataFinal: TDateTime;
  NomeArquivo: AnsiString; Documentos: TACBrECFTipoDocumentoSet;
  Finalidade: TACBrECFFinalizaArqMFD);
Var
  Resp : Integer ;
  DiaIni, DiaFim : AnsiString ;
  OldAtivo : Boolean ;
  Tipo:AnsiString;
begin
  LoadDLLFunctions ;

  OldAtivo := Ativo ;
  try
    AbrePortaSerialDLL ;

    case Finalidade of
       finMF  : Tipo := '1';
       finTDM : Tipo := '3';
    else
       Tipo := '2' ;
    end;

    DiaIni := FormatDateTime('dd"/"mm"/"yy', DataInicial) ;
    DiaFim := FormatDateTime('dd"/"mm"/"yy', DataFinal) ;

    if Tipo='3' then
    begin
      Resp := xECF_DownloadMF('ACBr.MF');
      if (Resp <> 1) then
        raise EACBrECFERRO.Create( ACBrStr( 'Erro ao executar ECF_DownloadMF.'+sLineBreak+
                                       DescricaoErroDLL(Resp) ));
       Resp := xECF_ReproduzirMemoriaFiscalMFD(Tipo, DiaIni, DiaFim, NomeArquivo, 'TMP.MF');
    end
    else
    begin
       Resp := xECF_ReproduzirMemoriaFiscalMFD(Tipo, DiaIni, DiaFim, NomeArquivo, '');
    end;

    if (Resp <> 1) then
      raise EACBrECFERRO.Create( ACBrStr( 'Erro ao executar ECF_ReproduzirMemoriaFiscalMFD.'+sLineBreak+
                                       DescricaoErroDLL(Resp) ))
  finally
    xECF_FechaPortaSerial ;
    Ativo := OldAtivo ;
  end ;

  if not FileExists( NomeArquivo ) then
     raise EACBrECFERRO.Create( ACBrStr( 'Erro na execu��o de ECF_DownloadMFD.'+sLineBreak+
                            'Arquivo: "'+NomeArquivo+'" n�o gerado' ))
end;

function TACBrECFSwedaSTX.GetDataHora: TDateTime;
Var RetCmd : AnsiString ;
    OldShortDateFormat : String ;
begin
  RetCmd := Trim(RetornaInfoECF( 'I8' )) ;
  OldShortDateFormat := ShortDateFormat ;
  try
     ShortDateFormat := 'dd/mm/yyyy' ;
     result := StrToDate(copy(RetCmd, 1,10)) ;
  finally
     ShortDateFormat := OldShortDateFormat ;
  end ;
  result := RecodeHour(  result,StrToInt(copy(RetCmd,12,2))) ;
  result := RecodeMinute(result,StrToInt(copy(RetCmd,15,2))) ;
  result := RecodeSecond(result,StrToInt(copy(RetCmd,18,2))) ;
end;

function TACBrECFSwedaSTX.GetNumCupom: String;
begin
   Result := Trim(copy( RetornaInfoECF( 'A4' ), 33, 6)) ;
end;

function TACBrECFSwedaSTX.GetNumCRO: String;
begin
  Result := Trim(copy( RetornaInfoECF( 'A4' ), 1, 4)) ;
end;

function TACBrECFSwedaSTX.GetNumCCF: String;
begin
  Result := Trim(copy( RetornaInfoECF( 'A4' ), 21, 6)) ;
end;

function TACBrECFSwedaSTX.GetNumLoja: String;
begin
  Result := Trim(copy( RetornaInfoECF( 'H2' ), 1, 5)) ;
end;

function TACBrECFSwedaSTX.GetNumECF: String;
begin
  Result := Trim(copy( RetornaInfoECF( 'H2' ), 6, 4)) ;
end;

function TACBrECFSwedaSTX.GetNumSerie: String;
begin
  Result := Trim(copy( RetornaInfoECF( 'I1' ), 51, 22)) ;
end;

function TACBrECFSwedaSTX.GetNumSerieMFD: String;
begin
  Result := '' ;
  if fpMFD then
     Result := trim(copy(RetornaInfoECF( 'I32' ),3,21));
end;


function TACBrECFSwedaSTX.GetNumVersao: String ;
begin
  Result := Trim(copy( RetornaInfoECF( 'I1' ), 73, 9)) ;
end;

function TACBrECFSwedaSTX.GetTotalPago: Double;
begin
  Result := StrToFloatDef( Trim(copy( RetornaInfoECF( 'L1' ), 52, 13)),0)/100 ;
end;

function TACBrECFSwedaSTX.GetSubModeloECF: String;
begin
 if fsSubModelo = '' then
     fsSubModelo := trim(copy(RetornaInfoECF( 'I1' ),22,21));

 Result := fsSubModelo;
end;

function TACBrECFSwedaSTX.GetSubTotal: Double;
begin
  Result := StrToFloatDef( Trim(copy( RetornaInfoECF( 'L1' ), 26, 13)),0)/100 ;
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
function TACBrECFSwedaSTX.GetEstado: TACBrECFEstado;
Var RetCmd : AnsiString ;
    Estado, Docto : AnsiChar ;
    Sinalizadores : AnsiString ;
    B : Integer ;
begin
  Result := fpEstado ;  // Suprimir Warning
  try
    fpEstado := estNaoInicializada ;
    if (not fpAtivo) then
      exit ;

    fpEstado := estDesconhecido ;

    RetCmd := EnviaComando( '34' ) ;
    if (copy(RetCmd,3,2) <> '34') or (Length(RetCmd) < 18) then
       exit ;         // Retorno inv�lido

    Estado := RetCmd[10] ;
    Docto  := RetCmd[11] ;
    Sinalizadores := copy(RetCmd,12,5) ;

    case Estado of
      'A' :
        begin
          case Docto of
             'A' :
               begin
//               if TestBit( Ord(Sinalizadores[1]), 1 ) then
//                  fpEstado := estRequerX
//               else
                   fpEstado := estLivre ;
               end ;

             'C' :
               begin
                 B := Ord( Sinalizadores[2] ) ;
                 if TestBit( B, 5 )  then
                   fpEstado := estPagamento
                 else if TestBit( B, 4 )  then
                   fpEstado := estVenda ;
               end ;

             'D' : fpEstado := estNaoFiscal ;

             'E','G','I' : fpEstado := estRelatorio ;
          end ;
        end ;

      'B' : fpEstado := estBloqueada ;

      'C' : fpEstado := estRequerZ ;
    end ;
  finally
    Result := fpEstado ;
  end ;
end;

function TACBrECFSwedaSTX.GetGavetaAberta: Boolean;
Var RetCmd : AnsiString ;
   B : Integer ;
begin
  Result := False ;
  RetCmd := EnviaComando( '34' ) ;
  if (copy(RetCmd,3,2) = '34') and (Length(RetCmd) >= 12) then
  begin
     B := Ord(RetCmd[12]) ;
     Result := TestBit( B , 2 ) ;
  end ;
end;

function TACBrECFSwedaSTX.GetPoucoPapel: Boolean;
Var RetCmd : AnsiString ;
   B : Integer ;
begin
  Result := False ;
  RetCmd := EnviaComando( '34' ) ;
  if (copy(RetCmd,3,2) = '34') and (Length(RetCmd) >= 12) then
  begin
     B := Ord(RetCmd[12]) ;
     Result := TestBit( B , 5 ) ;
  end ;
end;

function TACBrECFSwedaSTX.GetHorarioVerao: Boolean;
Var RetCmd : AnsiString ;
begin
  RetCmd := Trim(RetornaInfoECF( 'I8' )) ;
  Result := (UpperCase( copy(RetCmd,20,1) ) = 'V') ;
end;

Procedure TACBrECFSwedaSTX.LeituraX ;
begin
  AguardaImpressao := True ;
  EnviaComando( '15' ) ;
end;

procedure TACBrECFSwedaSTX.LeituraXSerial(Linhas: TStringList);
 Var RetCmd : AnsiString ;
begin
  RetCmd := EnviaComando('15|TXT|CPWIN') ;
  Linhas.Text := ExtraiRetornoLeituras( RetCmd ) ;
end;

Procedure TACBrECFSwedaSTX.AbreGaveta ;
begin
  EnviaComando( '11' ) ;
  Sleep(200);
end;

Procedure TACBrECFSwedaSTX.ReducaoZ(DataHora: TDateTime) ;
Var Cmd : String ;
     DtHrECF : TDateTime ;
begin
  Cmd := '16' ;
  if DataHora <> 0 then
  begin
     DtHrECF  := GetDataHora;
     DataHora := max( IncMinute(DtHrECF,-5), min( IncMinute(DtHrECF,5), DataHora)) ;
     Cmd := Cmd + '|' + FormatDateTime('dd"/"mm"/"yyyy',DataHora) +
                  '|' + FormatDateTime('hh":"nn":"ss',DataHora) ;
  end;

  try
     AguardaImpressao := True ;
     EnviaComando(Cmd,30) ;
  except
     on E : Exception do
     begin
        if (pos('0058',E.Message) <> 0) then   // Comando ou opera��o inv�lida!
         begin                                 // Ficou algum Cupom aberto ?
           CancelaCupom ;
           ReducaoZ(DataHora);
         end
        else
           raise ;
    end ;
  end;
end;

Procedure TACBrECFSwedaSTX.MudaHorarioVerao ;
begin
   MudaHorarioVerao(not HorarioVerao)
end;

procedure TACBrECFSwedaSTX.MudaHorarioVerao(EHorarioVerao: Boolean);
var
   cmd:String;
begin
   if EHorarioVerao then
      cmd := 'S'
   else cmd := 'N';
   EnviaComando('35|'+cmd);
end;

procedure TACBrECFSwedaSTX.CorrigeEstadoErro(Reducao: Boolean);
begin
   inherited CorrigeEstadoErro(Reducao);

   if Estado = estDesconhecido then
   begin
     try
       LeituraX;
     except
       On E: EACBrECFErro do
       begin
         if (pos('216', E.Message) > 0) then
         begin
            EnviaComando('23|'+FormatDateTime('dd/mm/yyyy|hh:nn:ss',Now));
            inherited CorrigeEstadoErro(Reducao);
         end;
       end;
     end;
   end;
end;


procedure TACBrECFSwedaSTX.NaoFiscalCompleto(CodCNF: String; Valor: Double;
  CodFormaPagto: String; Obs: AnsiString; IndiceBMP: Integer);
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
         FechaNaoFiscal( Obs, IndiceBMP );
      except
         try
            CancelaNaoFiscal
         except
         end;

         raise ;
      end ;
   end ;
end;

function TACBrECFSwedaSTX.LeituraCMC7: AnsiString;
var
   OldTimeOut: Integer;
   P1, P2: Integer;
begin
   Result := '';
   EnviaComando('24|1|0|1000');

   { Leitura do CMC7 deve retornar mais dados }
   OldTimeOut := TimeOut;
   try
      TimeOut := max(OldTimeOut,10);  // Espere mais 10 segundos...
      GravaLog( '         Aguardando Resposta CMC7');
      LeResposta;

      fpRespostaComando := fsRespostasComando ;   // Respostas Acumuladas
      //DEBUG
      //GravaLog( '         Retorno Completo: '+fpRespostaComando );
      { Limpando de "fpRespostaComando" os Status n�o solicitados }
      fpRespostaComando := AjustaRetorno( fpRespostaComando  );
      //DEBUG
      //GravaLog( '         Retorno Tratado: '+fpRespostaComando );

      P1 := pos('!0110', fpRespostaComando) ;  // Procura por resposta do CMC7
      if P1 > 0 then
      begin
         P1 := P1 + 12;
         P2 := PosEx(ETX, fpRespostaComando, P1 );
         Result := copy(fpRespostaComando, P1, P2-P1-1 );
      end;
   finally
     TimeOut := OldTimeOut;
   end;
end;

procedure TACBrECFSwedaSTX.AbreCupom  ;
begin
  if Trim(Consumidor.Documento) <> '' then    { Tem Docto ? }
  begin
     EnviaComando('12|'+LeftStr(Consumidor.Documento ,20)+'|'+
                        LeftStr(Consumidor.Nome      ,30)+'|'+
                        LeftStr(Consumidor.Endereco  ,79)+'|0') ;
     Consumidor.Enviado := True ;
  end ;

  fpUltimaMsgPoucoPapel := 0 ;  { Zera tempo pra msg de pouco papel }
  AguardaImpressao := True ;
  EnviaComando( '01' ) ;
end;

procedure TACBrECFSwedaSTX.CancelaCupom;
var
   sVinculado:String;
   iVinculados:Integer;
   I:Integer;
begin
  try
    FechaRelatorio ;   { Fecha relatorio se ficou algum aberto (s� por garantia)}
  except   // Exce�ao silenciosa, pois a Impressora pode nao estar em Estado
  end ;    // de Relatorio.
  //Procurar por CCDs em aberto para Estorna-los
  sVinculado :=  RetornaInfoECF('L8');
  {Verifica se tem vinculado}
  iVinculados := StrToIntDef(Copy(sVinculado,3,2),0);
   if iVinculados > 0 then
  begin
     {Extorna todos comprovantes}
     for I := 1 to iVinculados do
     begin
        try
           {garante o fechamento do cdc}
           FechaCupom;
        except
        end;               
        EnviaComando('52',30);
        FechaCupom();
     end;
  end;
  EnviaComando('08') ;
end;

procedure TACBrECFSwedaSTX.CancelaItemVendido(NumItem: Integer);
begin
  EnviaComando( '05|' + IntToStr(NumItem) ) ;
end;

procedure TACBrECFSwedaSTX.EfetuaPagamento(CodFormaPagto: String;
  Valor: Double; Observacao: AnsiString; ImprimeVinculado: Boolean);
begin
  EnviaComando( '06|' + CodFormaPagto +'|'+AjustaValor(Valor)+'|'+
                LeftStr(Observacao,84) ) ;
end;

procedure TACBrECFSwedaSTX.FechaCupom(Observacao: AnsiString; IndiceBMP : Integer);
begin
  AguardaImpressao := True ;
  EnviaComando( '07|' + LeftStr( Observacao,800) ) ;
end;


procedure TACBrECFSwedaSTX.SubtotalizaCupom(DescontoAcrescimo: Double;
       MensagemRodape : AnsiString);
 Var Cmd : String ;
begin
  Cmd := '' ;
  if DescontoAcrescimo < 0 then
     Cmd := '55'
  else if DescontoAcrescimo > 0 then
     Cmd := '54' ;

  if Cmd <> '' then
     EnviaComando( Cmd+'|'+AjustaValor( Abs(DescontoAcrescimo) )) ;

  EnviaComando('64') ;  // Totaliza��o
end;

Procedure TACBrECFSwedaSTX.VendeItem( Codigo, Descricao : String;
  AliquotaECF : String; Qtd : Double ; ValorUnitario : Double;
  ValorDescontoAcrescimo : Double; Unidade : String;
  TipoDescontoAcrescimo : String; DescontoAcrescimo : String ;
  CodDepartamento: Integer) ;
var
   Aliquota : TACBrECFAliquota;
   IAT:String;
begin
  if Qtd > 9999 then
     raise EACBrECFCMDInvalido.Create( ACBrStr(
           'Quantidade deve ser inferior a 9999.'));

  {Usa o arredondamento por item  (apenas em ST120 ou superior) }
  IAT := '';
  if fsVerProtocolo > 'D' then
  begin
    if fpArredondaItemMFD then
       IAT := 'A'
    else
       IAT := 'T';

    IAT := '|'+IAT;
  end
  else
     fpArredondaItemMFD := False;

 {Vai vir o indice, tem que transformar em aliquota no formato Tipo + Aliquota}
  if (AliquotaECF[1] <> 'I') and
     (AliquotaECF[1] <> 'F') and
     (AliquotaECF[1] <> 'N') then
  begin
     {Formato tem que ser T18,00% por exemplo}
     Aliquota := AchaICMSIndice(AliquotaECF);
     AliquotaECF := FormatFloat(Aliquota.Tipo+'00.00%',Aliquota.Aliquota);
  end;

  EnviaComando('02|' + AjustaValor(Qtd,fpDecimaisQtd)              +'|'+
                       Trim(LeftStr(Codigo,14))                    +'|'+
                       AjustaValor(ValorUnitario, fpDecimaisPreco) +'|'+
                       Trim(LeftStr(Unidade,2))                    +'|'+
                       AliquotaECF                                 +'|'+
                       Trim(LeftStr(Descricao,33))                 +
                       IAT
                       );

  if ValorDescontoAcrescimo > 0 then
     DescontoAcrescimoItemAnterior( ValorDescontoAcrescimo, DescontoAcrescimo,
        TipoDescontoAcrescimo );
end;

procedure TACBrECFSwedaSTX.DescontoAcrescimoItemAnterior(
   ValorDescontoAcrescimo : Double ; DescontoAcrescimo : String;
   TipoDescontoAcrescimo : String; NumItem : Integer) ;
var
   CMD : String ;
begin
  CMD := ifthen(DescontoAcrescimo = 'A','03','04') + '|' +
         AjustaValor(ValorDescontoAcrescimo) ;

  if TipoDescontoAcrescimo = '%' then
     CMD := CMD + '%' ;

  if NumItem > 0 then
     CMD := CMD + '|' +IntToStr(NumItem);

  EnviaComando( CMD ) ;
end ;

procedure TACBrECFSwedaSTX.CarregaAliquotas;
var
   RetICMS, RetISSQN:String;
   Aliquota : TACBrECFAliquota ;
   iAliquotas:Integer;
   I:Integer;
begin
   RetICMS  := RemoveNulos( RetornaInfoECF('D4') );
   RetISSQN := RemoveNulos( RetornaInfoECF('E4') );

   inherited CarregaAliquotas;

   iAliquotas := Trunc(Length(RetICMS)/4);
   for I := 1 to iAliquotas do
   begin
      Aliquota := TACBrECFAliquota.create;
      Aliquota.Sequencia := I;
      Aliquota.Indice := FormatFloat('T00',I);
      Aliquota.Aliquota := StrToFloatDef(Copy(RetICMS,(I*4)-3,4),0)/100;
      fpAliquotas.Add(Aliquota);
   end;

   iAliquotas := Trunc(Length(RetISSQN)/4);
   for I := 1 to iAliquotas do
   begin
      Aliquota := TACBrECFAliquota.create;
      Aliquota.Sequencia := I;
      Aliquota.Indice := FormatFloat('S00',I);
      Aliquota.Tipo := 'S';
      Aliquota.Aliquota := StrToFloatDef(Copy(RetISSQN,(I*4)-3,4),0)/100;
      fpAliquotas.Add(Aliquota);
   end;
end;

procedure TACBrECFSwedaSTX.LerTotaisAliquota;
var
   I:Integer;
   RetCMD:String;
begin
    if not Assigned(fpAliquotas) then
       CarregaAliquotas;

    RetCMD := RemoveNulos( RetornaInfoECF('D2') ) +    // ICMS
              RemoveNulos( RetornaInfoECF('E2') ) ;    // ISSQN

    for I := 0 to fpAliquotas.Count - 1 do
       fpAliquotas[I].Total := StrToFloatDef(Copy(RetCMD,((I+1)*13)-12,13),0)/100;
end;


procedure TACBrECFSwedaSTX.ProgramaAliquota(Aliquota: Double; Tipo: Char;
   Posicao : String);
var
   sAliquota:String;
begin
   sAliquota := FormatFloat(Tipo+'00.00',Aliquota);
   {Nesse protocolo n�o � necess�rio a posi��o :) }
   EnviaComando('32|'+sAliquota);
end;

function TACBrECFSwedaSTX.AchaICMSAliquota( var AliquotaICMS: String):
   TACBrECFAliquota;
Var AliquotaStr : String ;
begin
   Result      := nil ;
   AliquotaStr := '';

  AliquotaICMS := UpperCase( Trim( AliquotaICMS ) ) ;
  case AliquotaICMS[1] of
    'I' : AliquotaStr  := 'I1' ;
    'N' : AliquotaStr  := 'N1' ;
    'F' : AliquotaStr  := 'F1' ;
    'T' : AliquotaICMS := 'T'+AliquotaICMS; {Indice}
    'S' : {ISSQN}
        begin
           case AliquotaICMS[2] of
              'I':AliquotaStr := 'IS1';
              'N':AliquotaStr := 'NS1';
              'F':AliquotaStr := 'FS1';
           else AliquotaICMS  := 'T'+AliquotaICMS;
           end
        end;
  end;

  if AliquotaStr = '' then
     Result := inherited AchaICMSAliquota( AliquotaICMS )
  else
     AliquotaICMS := AliquotaStr ;

end;

procedure TACBrECFSwedaSTX.CarregaFormasPagamento;  { fun�ao Lenta +- 3 sec. }
var
   sDenominador :String;
   I:Integer;
   FPagto : TACBrECFFormaPagamento ;
   iFormasPagto:integer;
   sVinculados:String;
begin
   {Inicializa o objeto FpFormasPagamento}
   inherited CarregaFormasPagamento;
   sDenominador := RetornaInfoECF('B4');
   sVinculados := RetornaInfoECF('B2');

   {Retirar os #0, o stringReplace n�o funciona nesse caso }
   sDenominador := RemoveNulos(sDenominador);
   {S�o 20 formas de pagamento no m�ximo de 21 caracteres}
   iFormasPagto := Trunc(Length(sDenominador)/21);
   for I := 1 to iFormasPagto do
   begin
      FPagto := TACBrECFFormaPagamento.create;
      FPagto.Indice := FormatFloat('00',I);
      FPagto.Descricao := Copy(sDenominador,(I*21)-20,21);
      {Se for vinculado, o valor vai ser igual a 2}
      FPagto.PermiteVinculado := sVinculados[I] = '2';
      fpFormasPagamentos.Add(FPagto);
   end;
end;

procedure TACBrECFSwedaSTX.CarregaRelatoriosGerenciais;
var
   sDenominacoes:String;
   sCRE:String;
   iRelGerenciais:Integer;
   I:integer;
   RG  : TACBrECFRelatorioGerencial ;
begin
   inherited CarregaRelatoriosGerenciais ;
   sDenominacoes := RetornaInfoECF('F1');
   sDenominacoes := RemoveNulos(sDenominacoes);

   sCRE := RetornaInfoECF('F2');
   sCRE := RemoveNulos(sCRE);

   iRelGerenciais := Trunc(Length(sDenominacoes)/26);
   for I := 1 to iRelGerenciais do
   begin
      RG := TACBrECFRelatorioGerencial.create;
      RG.Indice := FormatFloat('00',I);
      RG.Descricao := Copy(sDenominacoes,(I*26)-25,25);
      RG.Contador := StrToIntDef(Copy(sCRE,(I*4)-3,4),0);
      fpRelatoriosGerenciais.Add(RG);
   end;
end;

procedure TACBrECFSwedaSTX.LerTotaisRelatoriosGerenciais ;
begin
  CarregaRelatoriosGerenciais;
end ;

procedure TACBrECFSwedaSTX.LerTotaisFormaPagamento;
var
   sTotalizador:String;
   I:Integer;
begin
   if not Assigned(fpFormasPagamentos) then
      CarregaFormasPagamento;

   sTotalizador := RetornaInfoECF('B8');
  {Retirar os #0, o stringReplace n�o funciona nesse caso }
   sTotalizador := RemoveNulos(sTotalizador);

   for I := 0 to fpFormasPagamentos.Count -1 do
   begin
      fpFormasPagamentos[I].Total := StrToFloatDef(
                                     Copy(sTotalizador,((I+1)*13)-12,13),0)/100;
   end;
end;


procedure TACBrECFSwedaSTX.ProgramaFormaPagamento( var Descricao: String;
  PermiteVinculado : Boolean; Posicao : String) ;
var
   sClassificacao:String;
begin
   { Parametros poss�veis:
     0 - N�o classificada
     1 - Moeda
     2 - Cart�o de cr�dito ou d�bito
     3 - Ticket - Contra Vale
     4 - Cheque
   }
   sClassificacao := '0';
   if PermiteVinculado then
      sClassificacao := '2';
   EnviaComando('36|'+sClassificacao+'|'+Descricao);
end;

procedure TACBrECFSwedaSTX.ProgramaRelatorioGerencial( var Descricao: String; Posicao: String);
begin
   EnviaComando('42|'+Descricao);
end;

procedure TACBrECFSwedaSTX.CarregaComprovantesNaoFiscais;
var
   sDenominadores:String;
   iDenominadores:Integer;{Quantos CNFs existem}
   I:Integer;
   CNF:TACBrECFComprovanteNaoFiscal;
begin
   sDenominadores := RetornaInfoECF('C4');
   sDenominadores := RemoveNulos(sDenominadores);
   iDenominadores := Trunc(Length(sDenominadores)/20);

   inherited CarregaComprovantesNaoFiscais;
   for I := 1 to iDenominadores do
   begin
      CNF := TACBrECFComprovanteNaoFiscal.create;
      CNF.Indice := FormatFloat('00',I);
      CNF.Descricao :=Copy(sDenominadores,(I*20)-18,19);
      fpComprovantesNaoFiscais.Add(CNF);
   end;
end;

procedure TACBrECFSwedaSTX.LerTotaisComprovanteNaoFiscal;
var
   sTotais:String;
   sCon:String;
   I:Integer;
begin
   if not Assigned(fpComprovantesNaoFiscais) then
      CarregaComprovantesNaoFiscais;
   sTotais := RetornaInfoECF('C2');
   sCon := RetornaInfoECF('C8');
   for I := 0 to fpComprovantesNaoFiscais.Count - 1 do
   begin
      fpComprovantesNaoFiscais[i].Total := StrToFloatDef(
                                           Copy(sTotais,((I+1)*13)-12,13),0)/100;
      fpComprovantesNaoFiscais[I].Contador:= StrToIntDef(
                                            Copy(sCon,((I+1)*4)-3,4),0);
   end;
end;

procedure TACBrECFSwedaSTX.ProgramaComprovanteNaoFiscal(var Descricao : String;
   Tipo: String; Posicao : String);
begin
{
Argumento(s): sinal:
Ascii Dec Sinal
  +   43   Positivo
  -   45   Negativo
Opcional, se omitido � assumido o valor padr�o do sinal: +
opera��o Denomina��o da opera��o n�o-fiscal.
Alfanum�rico - Extens�o m�xima: 15 caracteres
Poder�o ser cadastradas, em um �nico comando, um conjunto de at� 30 opera��es.

   Nota(s): Opera��es com sinal negativo n�o admitem os seguintes registros:
   - Pagamento;
   - Identifica��o do consumidor;
   - Acr�scimo;
   - Desconto.
}
   EnviaComando('37|'+Tipo+Descricao);
end;


procedure TACBrECFSwedaSTX.ImprimeCheque(Banco: String; Valor: Double;
  Favorecido, Cidade: String; Data: TDateTime; Observacao: String);
var
   Moeda,Moedas:String;
   sValor:String;
   sData:String;
begin
  {Apesar de implementadao, n�o foi poss�vel testar essa rotina por falta de
   equipamento que tivesse o recurso}
   Banco      := IntToStrZero(StrToIntDef(Banco,1),3) ;
   Favorecido := padL(Favorecido,80) ;
   Cidade     := padL(Cidade,30) ;
   Moeda      := padL('Real',20) ;
   Moedas     := padL('Reais',20) ;
   sValor     := FormatFloat('#0.00',Valor);
   sData      := FormatDateTime('MM-DD-yyyy',Data);
   EnviaComando('14|'+Banco+'|'+sValor+'|'+Moeda+'|'+Moedas+'|'+Favorecido+
                '|'+Cidade+'|'+sData);
end;

procedure TACBrECFSwedaSTX.CancelaImpressaoCheque;
begin
   EnviaComando('47');
end;

function TACBrECFSwedaSTX.GetChequePronto: Boolean;
begin
   {N�o existe comando que implemente esse m�todo}
   Result := True;
end;

function TACBrECFSwedaSTX.GetParamDescontoISSQN : Boolean ;
var
   RetCmd : AnsiString ;
begin
  RetCmd := RetornaInfoECF( 'H2' ) ;
  Result := (copy(RetCmd, 13, 1) = 'S') ;
end ;

procedure TACBrECFSwedaSTX.AbreRelatorioGerencial(Indice: Integer = 2 );
var
   sDescricao:String;
   RG:TACBrECFRelatorioGerencial;
begin
   { N�o existe indice 0 nessa impressora usando esse protocolo}
   { O indice 1 � reservado }
   if ( Indice = 0 ) or ( Indice = 1 ) then
      Indice := 2;


   RG := AchaRGIndice(FormatFloat('00',Indice));
   if RG = nil then
     raise EACBrECFERRO.create( ACBrStr('Relat�rio Gerencial: '+IntToStr(Indice)+
                                 ' n�o foi cadastrado.' ));
   sDescricao := PadL(RG.Descricao,15);
   AguardaImpressao := True;
   EnviaComando('43|'+sDescricao);
end;

procedure TACBrECFSwedaSTX.LinhaRelatorioGerencial(Linha: AnsiString; IndiceBMP: Integer);
Var P, Espera : Integer ;
    Buffer : AnsiString ;
    MaxChars : Integer ;
begin

  Linha := AjustaLinhas( Linha, Colunas );  { Formata as Linhas de acordo com "Coluna" }
  MaxChars := 1190 ;  { Sweda aceita no m�ximo 1190 caract. por comando }

  if not fpTermica then   { Se n�o � Termica, Imprime Linha a Linha }
     ImprimirLinhaALinha( Linha, '25|' )
  else
     while Length( Linha ) > 0 do
     begin
        P := 0 ;
        if LeftStr(Linha,1) = STX then
           P := Pos(ETX, Linha);
        if P = 0 then
           P := Pos(STX, Linha)-1;
        if P <= 0 then
           P := Length( Linha ) ;

        if P > MaxChars then    { Acha o fim de Linha mais pr�ximo do limite m�ximo }
           P := PosLast(#10, LeftStr(Linha,MaxChars) ) ;

        if P = 0 then
           P := Colunas ;

        Buffer := copy( Linha, 1, P)  ;
        Espera := Trunc( CountStr( Buffer, #10 ) / 4) ;

        AguardaImpressao := (Espera > 3) ;

        if LeftStr(Buffer,1) = STX then
           EnviaComando( copy(Buffer, 2, Length(Buffer)-2 ), Espera )
        else
           EnviaComando( '25|' + Buffer, Espera ) ;

        { ficou apenas um LF sozinho ? }
        if (P = Colunas) and (RightStr( Buffer, 1) <> #10) and
           (copy( Linha, P+1, 1) = #10) then
           P := P + 1 ;

        Linha := copy( Linha, P+1, Length(Linha) ) ;   // O Restante
     end ;
end;

procedure TACBrECFSwedaSTX.AbreCupomVinculado(COO, CodFormaPagto,
   CodComprovanteNaoFiscal :  String; Valor : Double ) ;
var
   sValor:String;
begin
   sValor := FormatFloat('#0.00',Valor);
   EnviaComando('50|'+CodFormaPagto+'|'+sValor);
end;

procedure TACBrECFSwedaSTX.LinhaCupomVinculado(Linha: AnsiString);
begin
   LinhaRelatorioGerencial( Linha );
end;

procedure TACBrECFSwedaSTX.FechaRelatorio;
begin
   if Estado = estRelatorio then
      FechaCupom();
end;

procedure TACBrECFSwedaSTX.LeituraMemoriaFiscal(ReducaoInicial,
   ReducaoFinal : Integer; Simplificada : Boolean);
var
   sSimplificada:String ;
begin
   sSimplificada := 'C';
   if Simplificada then
      sSimplificada := 'S';

   EnviaComando('17|'+IntToStr(ReducaoInicial)+'|'
                     +IntToStr(ReducaoFinal)
                     +'|'+sSimplificada);
end;

procedure TACBrECFSwedaSTX.LeituraMemoriaFiscal(DataInicial,
   DataFinal: TDateTime; Simplificada : Boolean);
var
   sDataInicial:String;
   sDataFinal:String;
   sSimplificada:String;
begin
   sSimplificada := 'C';
   if Simplificada then
      sSimplificada := 'S';
   sDataInicial := FormatDateTime('dd"/"mm"/"yyyy',DataInicial);
   sDataFinal := FormatDateTime('dd"/"mm"/"yyyy',DataFinal);
   AguardaImpressao := True ;
   EnviaComando('18|'+sDataInicial+'|'+sDataFinal+'|'+sSimplificada);
end;

procedure TACBrECFSwedaSTX.LeituraMemoriaFiscalSerial(ReducaoInicial,
   ReducaoFinal: Integer; Linhas : TStringList; Simplificada : Boolean);
var
   sSimplificada:String;
   Espera:Integer;
   RetCmd : AnsiString ;
begin
   Espera := Trunc(30 + ((ReducaoFinal - ReducaoInicial)/2) );
   sSimplificada := 'C';
   if Simplificada then
      sSimplificada := 'S';

   RetCmd := EnviaComando('17|'+IntToStr(ReducaoInicial)+'|'+IntToStr(ReducaoFinal)+'|'+
                          sSimplificada+'|TXT|'+'CPWIN',Espera);
   Linhas.Text := ExtraiRetornoLeituras( RetCmd ) ;
end;

procedure TACBrECFSwedaSTX.LeituraMFDSerial(DataInicial, DataFinal: TDateTime;
  Linhas: TStringList; Documentos: TACBrECFTipoDocumentoSet);
var
   sDataInicial:String;
   sDataFinal:String;
   Espera:Integer;
   RetCmd : AnsiString ;
begin
    Espera := Trunc(30 + (DaysBetween(DataInicial,DataFinal)/2) ) ;
   sDataInicial := FormatDateTime('dd"/"mm"/"yyyy',DataInicial);
   sDataFinal   := FormatDateTime('dd"/"mm"/"yyyy',DataFinal);
   RetCmd       := EnviaComando('45|'+sDataInicial+'|'+sDataFinal+'|TXT|'
                                 +'CPWIN',Espera);
   Linhas.Text  := ExtraiRetornoLeituras( RetCmd );
end;

procedure TACBrECFSwedaSTX.LeituraMFDSerial(COOInicial, COOFinal: Integer;
  Linhas: TStringList; Documentos: TACBrECFTipoDocumentoSet);
var
   Espera:Integer;
   RetCmd : AnsiString ;
begin
   Espera := Trunc(30 + ((COOFinal - COOInicial)/2) );
   RetCmd := EnviaComando('44|'+IntToStr(COOInicial)+'|'+IntToStr(COOFinal)+
                          '||TXT|'+'CPWIN',Espera);
   Linhas.Text  := ExtraiRetornoLeituras( RetCmd );
end;

procedure TACBrECFSwedaSTX.LeituraMemoriaFiscalSerial(DataInicial,
   DataFinal: TDateTime; Linhas : TStringList; Simplificada : Boolean);
var
   Espera:Integer;
   sDataInicial:String;
   sDataFinal:String;
   sSimplificada:String;
   RetCmd : AnsiString ;
begin
   sSimplificada := 'C';
   if Simplificada then
      sSimplificada := 'S';

   Espera := Trunc(30 + (DaysBetween(DataInicial,DataFinal)/2) );
   sDataInicial := FormatDateTime('dd"/"mm"/"yyyy',DataInicial);
   sDataFinal   := FormatDateTime('dd"/"mm"/"yyyy',DataFinal);
   RetCmd       := EnviaComando('18|'+sDataInicial+'|'+sDataFinal+'|'+
                                sSimplificada+'|TXT|'+'|CPWIN',Espera);
   Linhas.Text := ExtraiRetornoLeituras( RetCmd ) ;
end;

function TACBrECFSwedaSTX.GetCNPJ: String;
var
   RetCMD:String;
begin
   RetCMD := RetornaInfoECF('G64');
   Result := Copy(RemoveNulos(RetCMD),3,21);
end;

function TACBrECFSwedaSTX.GetIE: String;
var
   RetCMD:String;
begin
   RetCMD := RetornaInfoECF('G64');
   RetCMD := RemoveNulos(RetCMD);
   Result := Copy(RetCMD,21,21);
end;

function TACBrECFSwedaSTX.GetIM: String;
var
   RetCMD:String;
begin
   RetCMD := RetornaInfoECF('G64');
   Result := Copy(RemoveNulos(RetCMD),42,21);
end;

function TACBrECFSwedaSTX.GetCliche: AnsiString;
var
   RetCMD : AnsiString;
begin
   RetCMD := RetornaInfoECF('H4');
   Result := RemoveNulos(RetCMD);
end;

function TACBrECFSwedaSTX.GetUsuarioAtual: String;
 var
   RetCMD : AnsiString;
begin
  Result := '';
   RetCMD := RetornaInfoECF('I32');
   Result := copy(RemoveNulos(RetCMD),1,2);
end;

function TACBrECFSwedaSTX.GetDataHoraSB : TDateTime ;
Var RetCmd : AnsiString ;
    OldShortDateFormat : String ;
begin
  RetCmd := Trim(RetornaInfoECF( 'J1' )) ;
  OldShortDateFormat := ShortDateFormat ;
  try
     ShortDateFormat := 'dd/mm/yyyy' ;
     Result := StrToDate(copy(RetCmd,10,10)) ;
  finally
     ShortDateFormat := OldShortDateFormat ;
  end ;
  Result := RecodeHour(  Result,StrToInt(copy(RetCmd,21,2))) ;
  Result := RecodeMinute(Result,StrToInt(copy(RetCmd,24,2))) ;
  Result := RecodeSecond(Result,StrToInt(copy(RetCmd,27,2))) ;
end ;


function TACBrECFSwedaSTX.GetDataMovimento: TDateTime;
 Var
  RetCmd : AnsiString ;
  OldShortDateFormat: AnsiString;
  sData:String;
begin
   RetCmd := Trim(RetornaInfoECF('A2'));
   OldShortDateFormat := ShortDateFormat ;
   try
      sData := Copy(RetCmd,22,10);
      ShortDateFormat := 'dd/mm/yyyy' ;
      Result := StrToDate(sData);
   finally
      ShortDateFormat := OldShortDateFormat ;
   end ;
end;

function TACBrECFSwedaSTX.GetGrandeTotal: Double;
var
   RetCMD : AnsiString;
begin
   RetCMD := Trim(RetornaInfoECF('A1'));
   Result := StrToFloatDef(Copy(RetCMD,1,18),0)/100;
end;

function TACBrECFSwedaSTX.GetNumCRZ: String;
begin
  Result := Trim(copy( RetornaInfoECF( 'A4' ), 5, 4)) ;
end;

function TACBrECFSwedaSTX.GetTotalAcrescimos: Double;
var
   RetCMD:String;
begin
   RetCMD := Trim(RetornaInfoECF('D1'));
   Result := StrToFloatDef(Copy(RetCMD,1,13),0)/100;
end;

function TACBrECFSwedaSTX.GetTotalCancelamentos: Double;
var
   RetCMD:String;
begin
   RetCMD := Trim(RetornaInfoECF('D1'));
   Result := StrToFloatDef(Copy(RetCMD,27,13),0)/100;
end;

function TACBrECFSwedaSTX.GetTotalDescontos: Double;
var
   RetCMD:String;
begin
   RetCMD := Trim(RetornaInfoECF('D1'));
   Result := StrToFloatDef(Copy(RetCMD,14,13),0)/100;
end;

function TACBrECFSwedaSTX.GetTotalTroco: Double;
var
   RetCMD:String;
begin
   RetCMD := Trim(RetornaInfoECF('B1'));
   Result := StrToFloatDef(Copy(RetCMD,1,14),0)/100;
end;

function TACBrECFSwedaSTX.GetTotalIsencao: Double;
var
   I1:Double;
   I2:Double;
   I3:Double;
   RetCMD:String;
begin
   RetCMD := Trim(RetornaInfoECF('D1'));
   I1:= StrToFloatDef(Copy(RetCMD,118,13),0)/100;
   I2:= StrToFloatDef(Copy(RetCMD,131,13),0)/100;
   I3:= StrToFloatDef(Copy(RetCMD,144,13),0)/100;
   Result := I1+I2+I3;
end;

function TACBrECFSwedaSTX.GetTotalAcrescimosISSQN: Double;
var
   RetCMD:String;
begin
   RetCMD := Trim(RetornaInfoECF('E1'));
   Result := StrToFloatDef(Copy(RetCMD,1,13),0)/100;
end;

function TACBrECFSwedaSTX.GetTotalCancelamentosISSQN: Double;
var
   RetCMD:String;
begin
   RetCMD := Trim(RetornaInfoECF('E1'));
   Result := StrToFloatDef(Copy(RetCMD,27,13),0)/100;
end;

function TACBrECFSwedaSTX.GetTotalDescontosISSQN: Double;
var
   RetCMD:String;
begin
   RetCMD := Trim(RetornaInfoECF('E1'));
   Result := StrToFloatDef(Copy(RetCMD,14,13),0)/100;
end;

function TACBrECFSwedaSTX.GetTotalIsencaoISSQN: Double;
var
   I1:Double;
   I2:Double;
   I3:Double;
   RetCMD:String;
begin
   RetCMD := Trim(RetornaInfoECF('E1'));
   I1:= StrToFloatDef(Copy(RetCMD,118,13),0)/100;
   I2:= StrToFloatDef(Copy(RetCMD,131,13),0)/100;
   I3:= StrToFloatDef(Copy(RetCMD,144,13),0)/100;
   Result := I1+I2+I3;
end;

function TACBrECFSwedaSTX.GetTotalNaoTributadoISSQN: Double;
var
   RetCMD :String;
   N1:Double;
   N2:Double;
   N3:Double;
begin
   RetCMD := Trim(RetornaInfoECF('E1'));
   N1 := StrToFloatDef(Copy(RetCMD,79,13),0)/100;
   N2 := StrToFloatDef(Copy(RetCMD,92,13),0)/100;
   N3 := StrToFloatDef(Copy(RetCMD,105,13),0)/100;
   Result := N1+N2+N3;
end;

function TACBrECFSwedaSTX.GetTotalSubstituicaoTributariaISSQN: Double;
var
   RetCMD :String;
   F1:Double;
   F2:Double;
   F3:Double;
begin
   RetCMD := Trim(RetornaInfoECF('E1'));
   F1 := StrToFloatDef(Copy(RetCMD,40,13),0)/100;
   F2 := StrToFloatDef(Copy(RetCMD,53,13),0)/100;
   F3 := StrToFloatDef(Copy(RetCMD,66,13),0)/100;
   Result := F1+F2+F3;
end;

function TACBrECFSwedaSTX.GetTotalAcrescimosOPNF: Double;
var
   RetCMD:String;
begin
   RetCMD := Trim(RetornaInfoECF('C1'));
   Result := StrToFloatDef(Copy(RetCMD,1,13),0)/100;
end;

function TACBrECFSwedaSTX.GetTotalCancelamentosOPNF: Double;
var
   RetCMD:String;
begin
   RetCMD := Trim(RetornaInfoECF('C1'));
   Result := StrToFloatDef(Copy(RetCMD,27,13),0)/100;
end;

function TACBrECFSwedaSTX.GetTotalDescontosOPNF: Double;
var
   RetCMD:String;
begin
   RetCMD := Trim(RetornaInfoECF('C1'));
   Result := StrToFloatDef(Copy(RetCMD,14,13),0)/100;
end;

function TACBrECFSwedaSTX.GetTotalNaoTributado: Double;
var
   RetCMD :String;
   N1:Double;
   N2:Double;
   N3:Double;
begin
   RetCMD := Trim(RetornaInfoECF('D1'));
   N1 := StrToFloatDef(Copy(RetCMD,79,13),0)/100;
   N2 := StrToFloatDef(Copy(RetCMD,92,13),0)/100;
   N3 := StrToFloatDef(Copy(RetCMD,105,13),0)/100;
   Result := N1+N2+N3;
end;

function TACBrECFSwedaSTX.GetTotalSubstituicaoTributaria: Double;
var
   RetCMD :String;
   F1:Double;
   F2:Double;
   F3:Double;
begin
   RetCMD := Trim(RetornaInfoECF('D1'));
   F1 := StrToFloatDef(Copy(RetCMD,40,13),0)/100;
   F2 := StrToFloatDef(Copy(RetCMD,53,13),0)/100;
   F3 := StrToFloatDef(Copy(RetCMD,66,13),0)/100;
   Result := F1+F2+F3;
end;

function TACBrECFSwedaSTX.GetNumUltimoItem: Integer;
var
   RetCMD :String;
begin
   RetCMD := Trim(RetornaInfoECF('L2'));
   Result := StrToIntDef(Copy(RetCMD,1,4),0);
end;

function TACBrECFSwedaSTX.GetVendaBruta: Double;
var
   RetCMD :String;
begin
   RetCMD := Trim(RetornaInfoECF('A1'));
   Result := StrToFloatDef(Copy(RetCMD,33,14),0)/100;
end;

function TACBrECFSwedaSTX.GetNumCOOInicial: String;
var
   RetCMD :String;
begin
   {Comando suportado apenas a partir da vers�o 01.00.04}
   RetCMD := RemoveNulos(EnviaComando('65|0000'));//retorna dados do movimento atual
   {Remove a primeira parte da string (#2'265+0000AA�����)}
   RetCMD := Copy(RetCMD,17,length(RetCMD));
   Result := Copy(RetCMD,210,6);
end ;

procedure TACBrECFSwedaSTX.AbreNaoFiscal(CPF_CNPJ : String ; Nome : String ;
   Endereco : String) ;
begin
   EnviaComando('20');
end;

procedure TACBrECFSwedaSTX.LoadDLLFunctions;
var
  sLibName: string;

 procedure SwedaFunctionDetect( FuncName: String; var LibPointer: Pointer ) ;
 begin
   if not Assigned( LibPointer )  then
   begin
     if not FunctionDetect( sLibName, FuncName, LibPointer) then
     begin
        LibPointer := NIL ;
        raise EACBrECFERRO.Create( ACBrStr( 'Erro ao carregar a fun��o:'+FuncName+' de: '+cLIB_Sweda ) ) ;
     end ;
   end ;
 end ;
begin
  // Verifica se exite o caminho das DLLs
  sLibName := '';
  if Length(PathDLL) > 0 then
     sLibName := PathDLL;

  // Concatena o caminho se exitir mais o nome da DLL.
  sLibName := sLibName + cLIB_Sweda;

  SwedaFunctionDetect('ECF_AbreConnectC', @xECF_AbreConnectC);
  SwedaFunctionDetect('ECF_DownloadMFD', @xECF_DownloadMFD);
  SwedaFunctionDetect('ECF_GeraRegistrosCAT52MFD', @xECF_GeraRegistrosCAT52MFD);
  SwedaFunctionDetect('ECF_DownloadMF', @xECF_DownloadMF);
  SwedaFunctionDetect('ECF_ReproduzirMemoriaFiscalMFD', @xECF_ReproduzirMemoriaFiscalMFD);
  SwedaFunctionDetect('ECF_FechaPortaSerial', @xECF_FechaPortaSerial);
end ;

Function TACBrECFSwedaSTX.DescricaoErroDLL(const NErro: Integer) : String ;
var
  Descr : String ;
begin
  case NErro of
    -30 : Descr := 'N�o implementado no modelo conectado.' ;
    -27 : Descr := 'Status do ECF diferente de 6,0,0,0 (Ack,St1,St2,St3).' ;
     -3 : Descr := 'N�o existe movimento' ;
     -2 : Descr := 'Par�metro inv�lido na fun��o.' ;
     -1 : Descr := 'Falta movimento em um dos arquivos bin�rios.' ;
      0 : Descr := 'Erro de comunica��o.' ;
  else
     Descr := '';
  end ;

   Result := 'Cod.: '+IntToStr(NErro) ;
   if Descr <> '' then
      Result := Result + ' - ' + Descr;
end ;

procedure TACBrECFSwedaSTX.AbrePortaSerialDLL ;
Var
  Porta, Resp, Velocidade : Integer ;
  Ini : TIniFile ;
begin
  Porta      := StrToIntDef( OnlyNumber( fpDevice.Porta ), 0) ;
  Velocidade := fpDevice.Baud;

  Ativo := False ;
  Sleep(500);

  Ini := TIniFile.Create( fsApplicationPath + 'SWC.INI' );
  try
     Ini.WriteInteger('COMUNICA��O','PORTA', Porta ) ;
     Ini.WriteInteger('COMUNICA��O','VELOCIDADE', Velocidade ) ;
     Ini.WriteString('COMUNICA��O','LOG', fsApplicationPath+'LogDLLSweda.txt' ) ;
  finally
     Ini.Free ;
  end ;

  Resp := xECF_AbreConnectC( 0, fsApplicationPath );
  if Resp <> 1 then
     raise EACBrECFERRO.Create( ACBrStr('Erro: '+IntToStr(Resp)+' ao abrir a Porta com:'+sLineBreak+
        'ECF_AbrePortaSerial'));
end ;

procedure TACBrECFSwedaSTX.RegistraItemNaoFiscal(CodCNF: String;
  Valor: Double; Obs: AnsiString = '');
var
   CNF : TACBrECFComprovanteNaoFiscal ;
   P:Integer;
   sDescricao:String;
begin
   P := StrToInt(CodCNF);
   CNF := AchaCNFIndice(IntToStrZero(P,2));
   if CNF = nil then
      raise EACBrECFERRO.Create('Indice n�o encontrado!');
   sDescricao :=CNF.Descricao;
//   {Remove o sinal da descri��o}
//   sDescricao[1]:= ' ';
   EnviaComando('21|'+Trim(sDescricao)+'|'+FormatFloat('#0.00',Valor));
end;

function TACBrECFSwedaSTX.RemoveNulos(Str: AnsiString): AnsiString;
var
   I:Integer;
begin
   for I := 1 to Length(Str) do
   begin
      if Str[I]= #0 then
      begin
         Str[I] := ' ';
      end;
   end;
   {Remove o ETX e o checksum da resposta}
   Result := Copy(Str,1,Pos(ETX,Str)-1);
end;

procedure TACBrECFSwedaSTX.EfetuaPagamentoNaoFiscal(CodFormaPagto: String;
  Valor: Double; Observacao: AnsiString; ImprimeVinculado: Boolean);
begin
   EfetuaPagamento(CodFormaPagto,Valor,Observacao,ImprimeVinculado);
end;

procedure TACBrECFSwedaSTX.SubtotalizaNaoFiscal(DescontoAcrescimo: Double;
   MensagemRodape: AnsiString);
begin
   SubtotalizaCupom(DescontoAcrescimo,MensagemRodape);
end;

procedure TACBrECFSwedaSTX.FechaNaoFiscal(Observacao: AnsiString; IndiceBMP : Integer);
begin
   FechaCupom(Observacao,IndiceBMP);
end;

procedure TACBrECFSwedaSTX.CancelaNaoFiscal;
begin
   CancelaCupom;
end;

function TACBrECFSwedaSTX.GetDadosUltimaReducaoZ: AnsiString;
var
  RetCMD :String;
  I : Integer;
  AliqZ : TACBrECFAliquota;

  function AchaValorRegistrador( Registrador: String ): Double ;
  var
     PosI: Integer;
  begin
    Result := 0;
    PosI   := Pos(padL(Registrador,5), RetCMD);
    if PosI > 0 then
       Result := RoundTo( StrToFloatDef( Trim(Copy(RetCMD,PosI+5,18)), 0)/100, -2);
  end ;
begin
{ Descri��o                                         Tamanho  Inicial  Final
  1 Marca (Sweda)                                      21        1      21
  2 Modelo do ECF                                      21       22      42
  3 Tipo de ECF                                        8        43      50
  4 N�mero de Fabrica��o                               22       51      72
  5 N�mero seq�encial do ECF                           3        73      75
  6 N�mero serial do dispositivo de MFD                21       76      86
  7 N�mero seq�encial do usu�rio na MF                 2        97      98
  8 N�mero do C.N.P.J.                                 21       99     119
  9 Inscri��o Estadual                                 21      120     140
  10 Inscri��o Municipal                               21      141     161
  11 S�mbolo da Moeda                                  5       162     166
  12 Centavos                                          1       167     167
  13 Contador de Redu��es Z (redu��o inicial)          4       168     171
  14 Data de emiss�o da Redu��o Z (redu��o inicial)    11      172     182
  15 Hor�rio de emiss�o da Redu��o Z (redu��o inicial) 10      183     182
  16 COO do documento Redu��o Z (redu��o inicial)      6       193     198
  17 Data do movimento (redu��o inicial)               11      199     209
  18 COO da primeira opera��o do dia (redu��o inicial) 6       210     215
  19 Contador de Rein�cio de Opera��o (redu��o inicial)4       216     219
  20 Contador de Redu��es Z (redu��o final)            4       220     223
  21 Data de emiss�o da Redu��o Z (redu��o final)      11      224     234
  22 Hor�rio de emiss�o da Redu��o Z (redu��o final)   10      235     244
  23 COO do documento Redu��o Z (redu��o final)        6       245     250
  24 Data do movimento (redu��o final)                 11      251     261
  25 COO da primeira opera��o do dia (redu��o final)   6       262     267
  26 Contador de Rein�cio de Opera��o (redu��o final)  4       268     271
  27 Venda L�quida de ICMS                             18      272     289
  28 Venda Bruta de ICMS                               18      290     307
  29 Venda L�quida de ISSQN                            18      308     325
  30 Venda Bruta de ISSQN                              18      326     343
  31 Venda L�quida N�o-Fiscal                          18      344     361
  xx Legenda - Vide Tabela de Legendas 5 362 --
  xx Valor acumulado no respectivo totalizador 18 -- --

  Legenda Totalizador - As legendas tem 5 caracteres + 18 de valores acumulados
  Exemplo: GT no arquivo est� como 'GT   000000000000000000'

  GT Totalizador Geral
  VB Venda Bruta Di�ria
  ON Venda Bruta N�o-Fiscais
  DT Desconto de ICMS
  DS Desconto de ISSQN
  AT Acr�scimo de ICMS
  AS Acr�scimo de ISSQN
  CT Cancelamento de ICMS
  CS Cancelamento de ISSQN
  Tnnnn Totalizador de ICMS com carga tribut�ria vinculada.
        nnnn corresponde � al�quota �nn,nn%� (at� 15 totalizadores)
  Fn Substitui��o de ICMS
     n � o n�mero do totalizador, de 1 a 3
  In Isento de ICMS
     n � o n�mero do totalizador, de 1 a 3
  Nn N�o tributado de ICMS
     n � o n�mero do totalizador, de 1 a 3
  Snnnn Totalizador de ISSQN com carga tribut�ria vinculada
        nnnn corresponde � al�quota �nn,nn%� (at� 15 totalizadores)
  FSn Substitui��o de ISSQN
      n � o n�mero do totalizador, de 1 a 3
  Isn Isento de ISSQN
      n � o n�mero do totalizador, de 1 a 3
  NSn N�o tributado de ISSQN
      n � o n�mero do totalizador, de 1 a 3

  Os totalizadores s�o retornados na ordem apresentada. Com exce��o dos tr�s primeiros (GT, VB e ON) os
  demais totalizadores s�o inclu�dos no retorno somente se acumularem valor significativo (diferente de zero).
  }

  // Zerar variaveis e inicializa Dados do ECF //
  InitDadosUltimaReducaoZ;

  if not Assigned( fpAliquotas ) then
    LerTotaisAliquota ;

  {Comando suportado apenas a partir da vers�o 01.00.04}
  RetCMD := RemoveNulos(EnviaComando('65|9999' ));//retorna dados do movimento atual
  {Remove a primeira parte da string (#2'265+0000AA�����)}
  RetCMD := Copy(RetCMD,17,length(RetCMD));

  { Alimenta a class com os dados atuais do ECF }
  with fpDadosReducaoZClass do
  begin
    NumeroDeSerie    := Copy(RetCMD,51,22);
    NumeroDoECF      := Copy(RetCMD,73,3);
    NumeroDeSerieMFD := Copy(RetCMD,76,21);
    CRZ              := StrToIntDef( Copy(RetCMD,168,04), 0) ;
    COO              := StrToIntDef( Copy(RetCMD,193,06), 0) ;
    DataDoMovimento  := StringToDateTimeDef( Copy(RetCMD,199,5) + DateSeparator+
                                             Copy(RetCMD,207,2), 0, 'dd/mm/yy' );
    NumeroCOOInicial := Copy(RetCMD,210,06) ;
    CRO              := StrToIntDef( Copy(RetCMD,216,04), 0) ;
    VendaLiquida     := RoundTo( StrToFloatDef( copy(RetCMD,272,18),0) / 100, -2) ;
    TotalOperacaoNaoFiscal := RoundTo( StrToFloatDef( copy(RetCMD,344,18),0) / 100, -2) ;

    {Aliquotas}
    {As aliquotas s�o retornadas nesse comando, mas apenas se tiver valor }
    {Por isso percorro as aliquotas cadastradas no ECF para pegar todas}
    for I := 0 to Aliquotas.Count - 1 do
    begin
      AliqZ := TACBrECFAliquota.Create ;
      AliqZ.Assign( fpAliquotas[I] );
      {Procura pela aliquota no formato T/Snnnn na string}
      AliqZ.Total := AchaValorRegistrador(
           AliqZ.Tipo + FormatFloat( '0000', AliqZ.Aliquota*100 ) ) ;

      AdicionaAliquota( AliqZ );
    end ;

    ValorGrandeTotal           := AchaValorRegistrador('GT');
    ValorVendaBruta            := AchaValorRegistrador('VB');
    DescontoICMS               := AchaValorRegistrador('DT');
    DescontoISSQN              := AchaValorRegistrador('DS');
    AcrescimoICMS              := AchaValorRegistrador('AT');
    AcrescimoISSQN             := AchaValorRegistrador('AS');
    CancelamentoICMS           := AchaValorRegistrador('CT');
    CancelamentoISSQN          := AchaValorRegistrador('CS');

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
  end ;
end;

procedure TACBrECFSwedaSTX.CortaPapel(const CorteParcial: Boolean);
var
  Cmd : String ;
begin
  Cmd := '62' ;
  if (fsVerProtocolo > 'D') then
     Cmd := Cmd + '|' + ifthen( CorteParcial, '1', '2') ;

  EnviaComando( Cmd );
end;

procedure TACBrECFSwedaSTX.IdentificaOperador(Nome: String);
begin
   EnviaComando_ECF('56|'+Copy(Nome,1,20));
end;

procedure TACBrECFSwedaSTX.IdentificaPAF( NomeVersao, MD5 : String);
begin
   EnviaComando('39|D|' + padL(MD5,Colunas) + padL(NomeVersao,Colunas) );
end;

function TACBrECFSwedaSTX.GetPAF: String;
var
   RetCMD:String;
begin
   RetCMD :=  RetornaInfoECF('N2');
   Result := RemoveNulos(RetCMD);
end;

function TACBrECFSwedaSTX.GetNumCDC: String;
begin
  Result := Trim(copy( RetornaInfoECF( 'A4' ), 39, 4)) ;
end;

function TACBrECFSwedaSTX.GetNumCFC: String;
begin
  Result := Trim(copy( RetornaInfoECF( 'A4' ), 51, 4)) ;
end;

function TACBrECFSwedaSTX.GetNumGNFC: String;
begin
  Result := Trim(copy( RetornaInfoECF( 'A4' ), 47, 4)) ;
end;

function TACBrECFSwedaSTX.GetNumCFD: String;
begin
  Result := Trim(copy( RetornaInfoECF( 'A4' ), 27, 6)) ;
end;

function TACBrECFSwedaSTX.GetNumNCN: String;
begin
  Result := Trim(copy( RetornaInfoECF( 'A4' ), 43, 4)) ;
end;

function TACBrECFSwedaSTX.GetNumGNF: String;
begin
  Result := Trim(copy( RetornaInfoECF( 'A4' ), 9, 6)) ;
end;

function TACBrECFSwedaSTX.GetNumGRG: String;
begin
  Result := Trim(copy( RetornaInfoECF( 'A4' ), 15, 6)) ;
end;

function TACBrECFSwedaSTX.RetornaInfoECF(Registrador: String): AnsiString;
Var RetCmd : AnsiString ;
    I : Integer ;
    Info : TACBrECFSwedaInfo34 ;
begin
  I  := fsCache34.AchaSecao( Registrador ) ;
  if I >= 0 then
  begin
     Result := fsCache34[I].Dados ;
     exit ;
  end ;

  RetCmd := EnviaComando( '34|' + Registrador ) ;

  { Extraindo "DADOS" do bloco abaixo :
    STX[1]+Seq[1]+Tarefa[1]+Tipo[1]+Secao[4]+Dados[N]+ETX[1]+CHK[1] }
  if Copy(RetCmd,3,2) = '34' then
     Result := copy( RetCmd, 10, Length(RetCmd)-11 ) ;

  if pos('I8',Registrador) > 0 then  // Sem cache para Data/Hora
     exit ;

  { Adicionando resposta no Cache }
  Info := TACBrECFSwedaInfo34.create ;
  Info.Secao := Registrador ;
  Info.Dados := Result ;
  fsCache34.Add( Info ) ;
end;

function TACBrECFSwedaSTX.TraduzirTag(const ATag: AnsiString): AnsiString;
const
  INI = #22;
  OFF = INI + #175;

  // <e></e>
  cExpandidoOn   = INI + #171;
  cExpandidoOff  = OFF;

  // <n></n>
  cNegritoOn     = INI + #167;
  cNegritoOff    = OFF;

  // <s></s>
  cSublinhadoOn  = INI + #163;
  cSublinhadoOff = OFF;

  // <c></c>
  cCondensadoOn  = INI + #65;
  cCondensadoOff = OFF;

  //<i></i>
  cItalicoOn  = '';
  cITalicoOff = '';
begin

  case AnsiIndexText( ATag, ARRAY_TAGS) of
     -1: Result := ATag;
     2 : Result := cExpandidoOn;
     3 : Result := cExpandidoOff;
     4 : Result := cNegritoOn;
     5 : Result := cNegritoOff;
     6 : Result := cSublinhadoOn;
     7 : Result := cSublinhadoOff;
     8 : Result := cCondensadoOn;
     9 : Result := cCondensadoOff;
     10: Result := cItalicoOn;
     11: Result := cITalicoOff;
  else
     Result := '' ;
  end;

end;

function TACBrECFSwedaSTX.TraduzirTagBloco(const ATag, Conteudo : AnsiString
   ) : AnsiString ;
const
  // 10|T|EEEEEEEEEEEEE..EE|A|H|M|P|F|ME|BCCDD
  // --------
  // 10 = Comando para impress�o das barras
  // T = Tipo de codigo de Barras
  // EEEE..EE = Codigo de barra, m�ximo 40
  // A = Alinhamento (fixo em 1 - Centro )
  // H = Altura em Milimitros 3 a 32, (padr�o 16)
  // M = Magnitude, espessura (fixo em 2), usado como Largura
  // P = Posi�ao HRI. imprimir ou n�o codigo abaixo da barra
  //     0 - N�o imprime ou 2 - Ap�s o C�digo
  // F - Fonte para HRI, fixo em 'A' - Normal
  // ME - Margem esquerda, fixo em 0

  cEAN8     = 'D'; // <ean8></ean8>
  cEAN13    = 'C'; // <ean13></ean13>
  cINTER25  = 'F'; // <inter></inter>
  cCODE39   = 'E'; // <code39></code39>
  cCODE93   = 'I'; // <code93></code93>
  cCODE128  = 'J'; // <code128></code128>
  cUPCA     = 'A'; // <upca></upca>
  cCODABAR  = 'G'; // <codabar></codabar>

  function MontaCodBarras(const ATipo: AnsiString; ACodigo: AnsiString;
    TamFixo: Integer = 0): AnsiString;
  var
    L, A : Integer ;
  begin
    L := IfThen( ConfigBarras.LarguraLinha = 0, 2, max(min(ConfigBarras.LarguraLinha,5),1) );
    A := IfThen( ConfigBarras.Altura = 0, 16, max(min(ConfigBarras.Altura,32),3) );

    ACodigo := Trim( ACodigo );
    if TamFixo > 0 then
       ACodigo := padR( ACodigo, TamFixo, '0') ;

    Result := STX + '10|' + ATipo + '|'+ ACodigo + '|1|' + IntToStr(A) + '|' +
              IntToStr(L) + '|' + ifthen( ConfigBarras.MostrarCodigo, '2', '0' ) +
              '|A|0' + ETX ;
  end;

begin
  case AnsiIndexText( ATag, ARRAY_TAGS) of
     12,13: Result := MontaCodBarras(cEAN8, Conteudo, 7);
     14,15: Result := MontaCodBarras(cEAN13, Conteudo, 12);
     18,19: Result := MontaCodBarras(cINTER25, Conteudo);
     22,23: Result := MontaCodBarras(cCODE39, Conteudo ) ;
     24,25: Result := ifthen(fsVerProtocolo > 'E', MontaCodBarras(cCODE93, Conteudo), '' ) ;
     26,27: Result := IfThen(fsVerProtocolo > 'E', MontaCodBarras(cCODE128, Conteudo), ''  );
     28,29: Result := MontaCodBarras(cUPCA, Conteudo, 11);
     30,31: Result := MontaCodBarras(cCODABAR, Conteudo ) ;
  else
     Result := inherited TraduzirTagBloco(ATag, Conteudo) ;
  end;

end ;

function TACBrECFSwedaSTX.GetNumReducoesZRestantes: String;
var
  NumRedAtual: Integer;
const
  // o numero 3693 e n�mero de redu��es possiveis para as impressoras sweda
  // conforme informado pelo atendimento sweda
  NumMaximoReducoes = 3693;
begin
  NumRedAtual := StrToIntDef(Self.NumCRZ, 0);
  Result := Format('%4.4d', [NumMaximoReducoes - NumRedAtual]);
end;

end.


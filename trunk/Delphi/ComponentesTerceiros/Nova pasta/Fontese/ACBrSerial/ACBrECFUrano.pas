{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2004 Aurimenes Apolonio B Silva             }
{                                       Daniel Simoes de Almeida               }
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
|* 21/09/2005: Daniel Sim�es de Almeida / Aurimenes Apolonio B Silva
|* Primeira Versao: Cria�ao e Distribui�ao da Primeira Versao
|* 08/12/2005:  Daniel Simoes de Almeida
|*  - Diminuido tempo de alguns Sleeps de 100 para 10 a fim de agilizar a
|*    comunica�ao com o ECF (experimental)
|* 29/02/2008:  Valmor Flores
|*  - Implementado CarregaAliquotas
|* 11/03/2008:  Gelson Oliveira
|*  - Adequacao da Rotina FechaCupom para imprimir a mensagem promocional.
|*  (Quatro linhas ao final do cupom contendo 48 caracteres cada linha.)
|* 13/07/2009:  Carlos Antonio da Silva
|*  - Altera��o do m�todo GetEstado para obter estado de Redu��o Z
|*  - Implementados novos m�todos:
|*    - RetornaInfoECF
|*    - GetCNPJ
|*    - GetIE
|*    - GetGrandeTotal
|*    - GetVendaBruta
|*    - GetNumCRZ
|*    - GetNumGNF
|*    - GetNumCOOInicial
|*    - GetTotalCancelamentos
|*    - GetTotalDescontos
|*    - GetTotalAcrescimos
|*    - GetTotalIsencao
|*    - GetTotalNaoTributado
|*    - GetTotalSubstituicaoTributaria
|*    - LerTotaisAliquota
|*    - LerTotaisFormaPagamento
******************************************************************************}

{$I ACBr.inc}

unit ACBrECFUrano ;

interface
uses ACBrECFClass, ACBrDevice, ACBrUtil, ACBrConsts,
     Classes ;

const R = 'XXXXX' ;

type

{ Classe filha de TACBrECFClass com implementa�ao para Urano }
TACBrECFUrano = class( TACBrECFClass )
 private
    { Tamanho da Resposta Esperada ao comando. Necess�rio, pois a Urano nao
      usa um Sufixo padr�o no fim da resposta da Impressora. }
    fsBytesResp : Integer ;
    fsNumVersao : String ;
    fsNumECF    : String ;
    fsNumCRO    : String ;
    fsArredonda : Char ;

    Function PreparaCmd( cmd : AnsiString ) : AnsiString ;

    function RetornaInfoECFValor(ARegistrador: string) : Double;
 protected
    function GetDataHora: TDateTime; override ;
    function GetNumCupom: String; override ;
    function GetNumECF: String; override ;
    function GetNumCRO: String; override ;
    function GetNumSerie: String; override ;
    function GetNumVersao: String; override ;
    function GetSubTotal: Double; override ;
    function GetTotalPago: Double; override ;

    function GetEstado: TACBrECFEstado; override ;
    function GetGavetaAberta: Boolean; override ;
    function GetPoucoPapel : Boolean; override ;
    function GetHorarioVerao: Boolean; override ;
    function GetArredonda: Boolean; override ;

    function GetCNPJ: String; override;
    function GetIE: String; override;
    function GetGrandeTotal: Double; override;
    function GetVendaBruta: Double; override;
    function GetNumCRZ: String; override;
    function GetNumGNF: String; override;
    function GetNumCOOInicial: String; override;
    function GetTotalCancelamentos: Double; override;
    function GetTotalDescontos: Double; override;
    function GetTotalAcrescimos: Double; override;
    function GetTotalIsencao: Double; override;
    function GetTotalNaoTributado: Double; override;
    function GetTotalSubstituicaoTributaria: Double; override;

    Function VerificaFimLeitura(var Retorno: AnsiString;
       var TempoLimite: TDateTime) : Boolean ; override ;

 public
    Constructor create( AOwner : TComponent  )  ;
    Destructor Destroy  ; override ;

    procedure Ativar ; override ;

    Property BytesResp : Integer read fsBytesResp write fsBytesResp ;
    Function EnviaComando_ECF( cmd : AnsiString ) : AnsiString ; override ;

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

    { Procedimentos de Cupom N�o Fiscal }
    Procedure NaoFiscalCompleto( CodCNF : String; Valor : Double;
       CodFormaPagto  : String; Obs : AnsiString; IndiceBMP : Integer = 0 ) ; override ;
    Procedure AbreNaoFiscal( CPF_CNPJ: String = ''; Nome: String = '';
       Endereco: String = '' ) ; override ;
    Procedure RegistraItemNaoFiscal( CodCNF : String; Valor : Double;
       Obs : AnsiString = '') ; override ;

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
    Procedure MudaArredondamento( Arredondar : Boolean ) ; override ;
    Procedure LeituraMemoriaFiscal( DataInicial, DataFinal : TDateTime;
       Simplificada : Boolean = False ) ; override ;
    Procedure LeituraMemoriaFiscal( ReducaoInicial, ReducaoFinal : Integer;
       Simplificada : Boolean = False); override ;
    Procedure LeituraMemoriaFiscalSerial( DataInicial, DataFinal : TDateTime;
       Linhas : TStringList; Simplificada : Boolean = False ) ; override ;
    Procedure LeituraMemoriaFiscalSerial( ReducaoInicial, ReducaoFinal : Integer;
       Linhas : TStringList; Simplificada : Boolean = False ) ; override ;

    Procedure AbreGaveta ; override ;

    procedure CarregaAliquotas ; override ;
    function AchaICMSAliquota( var AliquotaICMS : String ) :
       TACBrECFAliquota ;  override;
    procedure LerTotaisAliquota; override;

    procedure CarregaFormasPagamento ; override ;
    Procedure ProgramaFormaPagamento( var Descricao: String;
       PermiteVinculado : Boolean = true; Posicao : String = '' ) ; override ;
    procedure LerTotaisFormaPagamento; override;

    procedure CarregaComprovantesNaoFiscais ; override ;
    Procedure ProgramaComprovanteNaoFiscal( var Descricao: String;
       Tipo : String = ''; Posicao : String = '') ; override ;

    function RetornaInfoECF(Registrador: String): AnsiString; override;
 end ;

implementation
Uses {$IFDEF COMPILER6_UP} DateUtils, StrUtils {$ELSE} ACBrD5, SysUtils, Windows{$ENDIF},
     SysUtils,  Math, ACBrECF;

{ ----------------------------- TACBrECFUrano ------------------------------ }

constructor TACBrECFUrano.create( AOwner : TComponent ) ;
begin
  inherited create( AOwner ) ;

  fpDevice.HandShake := hsRTS_CTS ;
  { Variaveis internas dessa classe }
  fsBytesResp := 0 ;
  fsNumVersao := '' ;
  fsNumECF    := '' ;
  fsNumCRO    := '' ;
  fsArredonda := ' ';
  fpModeloStr := 'Urano' ;
  fpRFDID     := 'UR' ;
end;

destructor TACBrECFUrano.Destroy;
begin

  inherited Destroy ;
end;

procedure TACBrECFUrano.Ativar;
begin
  if not fpDevice.IsSerialPort  then
     raise EACBrECFERRO.Create(ACBrStr('A impressora: '+fpModeloStr+' requer'+sLineBreak+
                            'Porta Serial:  (COM1, COM2, COM3, ...)'));

  fpDevice.HandShake := hsRTS_CTS ;
  inherited Ativar ; { Abre porta serial }

  fsNumVersao := '' ;
  fsNumECF    := '' ;
  fsNumCRO    := '' ;
  fsArredonda := ' ';
  fpIdentificaConsumidorRodape := True ;

  try
     { Testando a comunica�ao com a porta }
     if NumVersao = '' then
        raise EACBrECFNaoInicializado.Create( ACBrStr(
                 'Erro inicializando a impressora '+fpModeloStr ));
  except
     Desativar ;
     raise ;
  end ;
end;


Function TACBrECFUrano.EnviaComando_ECF( cmd : AnsiString ) : AnsiString ;
Var ErroMsg     : String ;
    Erro        : Byte ;
    PediuEstado : Boolean ;
begin
  result  := '' ;
  ErroMsg := '' ;
  Erro    := 0 ;
  fpComandoEnviado   := '' ;
  fpRespostaComando  := '' ;

  try
     { Codificando CMD de acordo com o protocolo da Urano }
     PediuEstado := (cmd = '23' + R) or (Copy(cmd,0,2) = '27');
     cmd := PreparaCmd( cmd ) ;

     fpDevice.Serial.DeadlockTimeout := 2000 ; { Timeout p/ Envio }
     fpDevice.Serial.Purge ;                   { Limpa a Porta }

     while fpComandoEnviado = '' do
     begin
        fpDevice.Serial.Purge ;                   { Limpa a Porta }

        if not TransmiteComando( cmd ) then
           continue ;

        fpComandoEnviado := cmd ;
     end ;

     { Sleep para poder receber a resposta da impressora (principalmente na
       leitura de registradore) }
     Sleep(300);

     { Chama Rotina da Classe m�e TACBrClass para ler Resposta. Se houver
       falha na leitura LeResposta dispara Exce�ao.
       Resposta fica gravada na v�riavel "fpRespostaComando" }
     LeResposta ;

     ErroMsg := '' ;

     if PediuEstado then
        Result := fpRespostaComando
     else
      begin
        { Removendo o cod.Erro do Retorno }
        Result := copy( fpRespostaComando, 2, length(fpRespostaComando) ) ;

        { Verificando por erros }
        Try
           Erro := ord(fpRespostaComando[1])
        except
           Erro := 0
        end  ;

        case Erro of
            0  : ErroMsg := 'Nenhuma resposta do ECF' ;
           33  : ErroMsg := '' ;  { comando OK }
           34  : ErroMsg := 'Cancelamento inv�lido' ;
           35  : ErroMsg := 'Abertura do Dia inv�lida' ;
           36  : ErroMsg := 'Aliquota inexistente' ;
           37,39,41 : ErroMsg := 'Erro na grava��o da Memoria Fiscal' ;
           40  : ErroMsg := 'Impressora em interven��o t�cnica' ;
           42  : ErroMsg := 'Indice de Aliquota inv�lido' ;
           44,61 : ErroMsg := 'Desconto Inv�lido' ;
           47  : ErroMsg := 'Viola�ao da memoria RAM' ;
           48  : ErroMsg := 'Comando aceito apenas durante Interve��o T�cnica' ;
           50  : ErroMsg := 'Fechamendo do dia (Redu��o Z) n�o realizada' ;
           51  : ErroMsg := 'Fechamendo do dia (Redu��o Z) j� realizada' ;
           52  : ErroMsg := 'Comando fora da sequ�ncia' ;
           53  : ErroMsg := 'Venda n�o iniciada (Abra o Cupom)' ;
           54  : ErroMsg := 'N�o houve pagamento' ;
           55  : ErroMsg := 'Cupom j� totalizado' ;
           56  : ErroMsg := 'Comando inexistente' ;
           57  : ErroMsg := 'TimeOut de transmiss�o de Comando' ;
           63  : ErroMsg := 'Cancelamento de Cupom Inv�lido' ;
           65  : ErroMsg := 'Altera��o do Hor�rio de Ver�o n�o permitida nesse momento' ;
           66  : ErroMsg := 'Aliquota j� programada' ;
           69  : ErroMsg := 'Mem�ria fiscal cheia' ;
           71  : ErroMsg := 'C�digo de Produto inv�lido' ;
           72  : ErroMsg := 'Limite do Valor Total por Item ultrapassado' ;
           73  : ErroMsg := 'Cabe�alho j� impresso' ;
           74  : ErroMsg := 'Altera��o do Hor�rio de Ver�o, somente ap�s Redu��o Z' ;
           75  : ErroMsg := 'Altera��o do Hor�rio de Ver�o, permitida apenas 1 vez ao dia' ;
           76  : ErroMsg := 'Rel�gio Inconsistente' ;
           82  : ErroMsg := 'Falta Papel para autenticar documento' ;
           83  : ErroMsg := 'N�o h� item a descontar' ;
           84  : ErroMsg := 'Item a cancelar inexistente' ;
           85  : ErroMsg := 'Item j� cancelado' ;
           87  : ErroMsg := 'Falta de Papel' ;
           88  : ErroMsg := 'Acrescimo no SubTotal inv�lido' ;
           89  : ErroMsg := 'Desconto no SubTotal inv�lido' ;
           91  : ErroMsg := 'Total do Item Vendido resulta em Zero' ;
           93  : ErroMsg := 'Indice de Forma de Pagamento inv�lido' ;
           94  : ErroMsg := 'Indice de Forma de Pagamento j� programado' ;
           96  : ErroMsg := 'Limite de autentica��o alcan�ado' ;
           97  : ErroMsg := 'Indice de Forma de Pagamento n�o programado' ;
           98  : ErroMsg := 'Valor Unit�rio inv�lido' ;
           99  : ErroMsg := 'Quantidade inv�lida' ;
           100 : ErroMsg := 'Aliquota inv�lida' ;
           105 : ErroMsg := 'Vinculado n�o encontrado' ;
           106 : ErroMsg := 'Valor da Aliquota inv�lido' ;
           109 : ErroMsg := 'N�mero de Vinculados por cupom, excedido' ;
           110 : ErroMsg := 'Vinculado cheio' ;
           111 : ErroMsg := 'Acrescimo financeiro n�o habilitado' ;

           { TODO : Continuar com a tradu��o dos ERROS mais comuns }
        else
           ErroMsg := 'Erro retornado pelo ECF: '+IntToStrZero(Erro,2) ;
        end ;
      end ;

     if ErroMsg <> '' then
      begin
        ErroMsg := ACBrStr('Erro retornado pela Impressora: '+fpModeloStr+#10+#10+
                   ErroMsg );

        if Erro = 87 then
           DoOnErrorSemPapel
        else
           raise EACBrECFSemResposta.create(ErroMsg) ;
      end
     else
        Sleep( IntervaloAposComando ) ;  { Pequena pausa entre comandos }

  finally
     BytesResp := 0 ;
  end ;
end;

Function TACBrECFUrano.VerificaFimLeitura(var Retorno: AnsiString;
   var TempoLimite: TDateTime) : Boolean ;
begin
  { L� at� atingir todos os Bytes esperados (BytesResp) e ECF entra EmLinha}
  { BytesResp � necess�rio, pois a Urano nao usa um Sufixo padr�o no fim
    da resposta da Impressora. }
  { Soma +1 pois a Urano sempre envia o Cod de Erro no Inicio }

  Result := (Length( Retorno ) >= (BytesResp+1) )
end;

Function TACBrECFUrano.PreparaCmd( cmd : AnsiString ) : AnsiString ;
begin
  result := '' ;
  if cmd = '' then exit ;

  Result := #27 + 'f' + cmd ;   { Prefixo ESC + 'f' }
end ;


function TACBrECFUrano.GetDataHora: TDateTime;
Var RetCmd : AnsiString ;
    OldShortDateFormat : String ;
begin
  RetCmd := RetornaInfoECF('27');
  OldShortDateFormat := ShortDateFormat ;
  try
     ShortDateFormat := 'dd/mm/yy' ;
     result := StrToDate( StringReplace(Copy(RetCmd,1,8),'/',DateSeparator,
                          [rfReplaceAll] ) );
  finally
     ShortDateFormat := OldShortDateFormat ;
  end ;
  RetCmd := RetornaInfoECF('28');
  result := RecodeHour(  result,StrToInt(copy(RetCmd,1,2))) ;
  result := RecodeMinute(result,StrToInt(copy(RetCmd,4,2))) ;
  result := RecodeSecond(result,StrToInt(copy(RetCmd,7,2))) ;
end;

function TACBrECFUrano.GetNumCupom: String;
begin
  Result := RetornaInfoECF('18');
end;

function TACBrECFUrano.GetNumCRO: String;
begin
  if fsNumCRO = '' then
    fsNumCRO := RetornaInfoECF('23');

  Result := fsNumCRO ;
end;

function TACBrECFUrano.GetNumECF: String;
begin
  if fsNumECF = '' then
    fsNumECF := RetornaInfoECF('26');

  Result := fsNumECF ;
end;

function TACBrECFUrano.GetNumSerie: String;
begin
  Result := RetornaInfoECF('25');
end;

function TACBrECFUrano.GetNumVersao: String ;
begin
  if fsNumVersao = '' then
    fsNumVersao := RetornaInfoECF('97');

  Result := fsNumVersao ;
end;

function TACBrECFUrano.GetTotalPago: Double;
begin
  Result := 0                  { Nao achei nenhum registrador para isso ??? }
end;

function TACBrECFUrano.GetSubTotal: Double;
begin
  Result := RetornaInfoECFValor('00');
end;


function TACBrECFUrano.GetEstado: TACBrECFEstado;
Var RetCmd : AnsiString ;
    B      : Byte ;
begin
  if (not fpAtivo) then
     fpEstado := estNaoInicializada
  else
   begin
      RetCmd := EnviaComando( '23' + R ) ;
      Try B := ord( RetCmd[1] ) except B := 0 end ;

      { Como detectar estados Requer Z e Requer X  ??? }
      case B of
        50  : fpEstado := estRequerZ;
        124 : fpEstado := estBloqueada ;
        120,121 : fpEstado := estPagamento ;
        119 : fpEstado := estVenda ;
        125,126,127 : fpEstado := estRelatorio ;
        118 : fpEstado := estLivre ;
      else
        fpEstado := estDesconhecido ;
      end;

   end ;

  Result := fpEstado ;
end;

function TACBrECFUrano.GetGavetaAberta: Boolean;
begin
  Result := False ;
end;

function TACBrECFUrano.GetPoucoPapel: Boolean;
begin
  Result := (EnviaComando( '271' + R ) = '1');
end;

function TACBrECFUrano.GetHorarioVerao: Boolean;
begin
  Result := False ;    { ????? AAS }
end;

function TACBrECFUrano.GetArredonda: Boolean;
begin
  Result := False ;    { ????? AAS }
end;

Procedure TACBrECFUrano.LeituraX ;
begin
  BytesResp := 0 ;
  EnviaComando( '11' + '0' + R , 35 ) ;
end;

Procedure TACBrECFUrano.AbreGaveta ;
begin
  BytesResp := 0;
  EnviaComando( '19' + R, 5 );
end;

Procedure TACBrECFUrano.ReducaoZ(DataHora: TDateTime) ;
begin
  BytesResp := 0 ;
  EnviaComando( '11' + '1' + R , 35 ) ;
end;

Procedure TACBrECFUrano.MudaHorarioVerao ;
begin
end;

procedure TACBrECFUrano.MudaHorarioVerao(EHorarioVerao: Boolean);
begin
  if EHorarioVerao <> HorarioVerao then
     MudaHorarioVerao ;
end;

procedure TACBrECFUrano.MudaArredondamento(Arredondar: Boolean);
begin
end;

procedure TACBrECFUrano.AbreCupom ;
begin
  BytesResp := 0 ;

  try
    EnviaComando('00' + R ) ;
  except
    On E: Exception do
    begin
      if not (Pos('j� impresso', E.Message) > 0) then
        Raise;
    end;
  end;
end;

procedure TACBrECFUrano.CancelaCupom;
Var Est : TACBrECFEstado ;
begin
  BytesResp := 0 ;
  Est       := Estado ;  { usa variavel, para evitar comunica��o v�rias vezes }

  case Est of
     estVenda, estPagamento :  EnviaComando('07' + padL(Operador,8) + R, 20 ) ;
     estLivre : EnviaComando('08' + '1' + padL(Operador,8) + R, 20 ) ;
  end ;

  FechaRelatorio ;   { Fecha relatorio se ficou algum aberto (s� por garantia)}
end;

procedure TACBrECFUrano.CancelaItemVendido(NumItem: Integer);
var
  Descricao, ItemStr: String;
begin
  Descricao := padL('Item Cancelado', 22) ;
  ItemStr := IntToStrZero(NumItem, 3) ;

  EnviaComando('02' + Descricao + ItemStr + R ) ;
end;

procedure TACBrECFUrano.EfetuaPagamento(CodFormaPagto: String;
  Valor: Double; Observacao: AnsiString; ImprimeVinculado: Boolean);
var
  ValorStr,
  Vinculado: String;
begin
  Observacao := padL(Observacao, 17);
  ValorStr := IntToStrZero(Round(Valor * 100), 11);
  if ImprimeVinculado then Vinculado := '1' else Vinculado := '0' ;

  EnviaComando('04' + CodFormaPagto + Observacao + ValorStr + Vinculado + R);
end;

procedure TACBrECFUrano.FechaCupom(Observacao: AnsiString; IndiceBMP : Integer);
var
  Nome,
  Tipo,
  CPF_CNPJ, 
  Linha1,
  Linha2 : String;

begin
  { Documenta��o do consumidor }
  if Trim(Consumidor.Documento) <> '' then
  begin
    Nome := padL('IDENTIFICACAO DO CONSUMIDOR', 42);
    Linha1 := padL('', 42);
    Linha2 := padL('', 42);
    CPF_CNPJ := padL(Consumidor.Documento, 18);

    if Length(Trim(CPF_CNPJ)) = 11 then
      Tipo := '0'
    else
      Tipo := '1';

    EnviaComando('34' + Nome + Tipo + CPF_CNPJ + Linha1 + Linha2 + R);
  end;

  if Trim( Observacao ) <> '' then
     EnviaComando( '170' + padL(Observacao, 192) + R, 10 );
  
    EnviaComando('050' + padL(Operador, 8) + R, 10);  
end;

procedure TACBrECFUrano.SubtotalizaCupom(DescontoAcrescimo: Double;
       MensagemRodape : AnsiString);
var
  DescontoAcrescimoStr: String;
begin
  DescontoAcrescimoStr := IntToStrZero(abs(Round(DescontoAcrescimo * 100)), 10);

  if DescontoAcrescimo > 0 then
  begin
    EnviaComando('090' + padL('', 10) + DescontoAcrescimoStr + R );
  end
  else if DescontoAcrescimo < 0 then
  begin
    EnviaComando('100' + padL('', 10) + DescontoAcrescimoStr + R );
  end;
end;

Procedure TACBrECFUrano.VendeItem( Codigo, Descricao : String;
  AliquotaECF : String; Qtd : Double ; ValorUnitario : Double;
  ValorDescontoAcrescimo : Double; Unidade : String;
  TipoDescontoAcrescimo : String; DescontoAcrescimo : String ;
  CodDepartamento: Integer) ;
Var QtdStr, ValorStr, DescontoStr : String ;
    Tipo : Char ;
begin
  if Qtd > 9999 then
     raise EACBrECFCMDInvalido.Create(
           'Quantidade deve ser inferior a 9999.');

  { Urano n�o permite Acrescimo por Item }
  if (ValorDescontoAcrescimo > 0) and (DescontoAcrescimo = 'A') then
     raise EACBrECFCMDInvalido.Create( ACBrStr(
           'ECF '+fpModeloStr+' n�o permite Acr�scimo por Item'));

  Codigo    := padL(Codigo,13) ;    { Ajustando Tamanhos }
  Descricao := PadL(Copy(Descricao, 1, 66),66) ;

  if Round( Qtd ) = Qtd then
  begin
     Tipo := '0';
     QtdStr := IntToStrZero(Round(Qtd), 7);
     ValorStr := IntToStrZero( Round(ValorUnitario * 100 ), 9) ;
  end
  else
  begin
     Tipo := '2';
     QtdStr := IntToStrZero(Round(Qtd * 1000), 7);
     QtdStr := Copy(QtdStr,2,3) + ',' + Copy(QtdStr,5,3);
     ValorStr := IntToStrZero( Round(ValorUnitario * 1000 ), 9) ;
  end;

  Unidade   := padL(Unidade,2) ;

  EnviaComando('01' + Codigo + Descricao + QtdStr + ValorStr + AliquotaECF
    + Unidade + Tipo + R, 20);

  { Se o desconto � maior que zero d� o comando de desconto de item }
  if ValorDescontoAcrescimo > 0 then
  begin
     { Urano tem apenas desconto por Valor }
     if TipoDescontoAcrescimo='%' then
        DescontoStr := IntToStrZero( Round(ValorUnitario*Qtd*ValorDescontoAcrescimo), 11)
     else
        DescontoStr := IntToStrZero( Round(ValorDescontoAcrescimo*100), 9) ;

    EnviaComando('030' + PadL('Desconto Aplicado', 26) + DescontoStr + R);
  end;
end;

procedure TACBrECFUrano.CarregaAliquotas;
var
  RetCmd : AnsiString ;
  A: Integer;
  ValStr: String;
  ValAliq: Double;
  Aliquota : TACBrECFAliquota ;
begin
  inherited CarregaAliquotas ;  { Cria fpAliquotas }

  { L� as al�quotas cadastradas na impressora }
  for A := 0 to 6 do
  begin

    { Pegar o percentual da aliquota }
    BytesResp := 21 ;
    RetCmd    := EnviaComando( '24' + IntToStrZero( A + 34, 2 ) + R );
    ValStr    := StringReplace(trim( RetCmd ),',',DecimalSeparator,[]);
    ValAliq   := StringToFloatDef( ValStr, 0 ) ;

    if ValAliq > 0 then
    begin
      Aliquota := TACBrECFAliquota.create ;

      Aliquota.Indice  := IntToStrZero(A,2) ;
      Aliquota.Aliquota := ValAliq ;
      if copy(RetCmd,4,1) = 'S' then
        Aliquota.Tipo  := 'S';

      fpAliquotas.Add( Aliquota ) ;
    end;
  end;
end;

function TACBrECFUrano.AchaICMSAliquota( var AliquotaICMS: String):
  TACBrECFAliquota;
  Var AliquotaStr : String ;
begin
  AliquotaStr := '' ;
  Result      := nil ;

  case AliquotaICMS[1] of
    'F' : AliquotaStr := '16' ;
    'I' : AliquotaStr := '17' ;
    'N' : AliquotaStr := '18' ;
    'T' : AliquotaICMS := 'T'+padR(copy(AliquotaICMS,2,2),2,'0') ; {Indice}
  end ;

  if AliquotaStr = '' then
     Result := inherited AchaICMSAliquota( AliquotaICMS )
  else
     AliquotaICMS := AliquotaStr ;
end;



procedure TACBrECFUrano.CarregaFormasPagamento;  { fun�ao Lenta +- 3 sec. }
var
  Cont: Integer;
  RetCmd,  Descricao: AnsiString;
  FPagto: TACBrECFFormaPagamento ;
  Vinculados: TStringList;
begin
  inherited CarregaFormasPagamento ;   { Cria fpFormasPagamentos }

  { *TESTAR* }
  try
     Vinculados := TStringList.Create();
     try

        { Percorre os vinculados para registrar as formas que podem emitir
          vinculados }

        for Cont := 0 to 9 do
        begin
           BytesResp := 21 ;
           RetCmd    := EnviaComando( '24' + IntToStrZero(Cont + 62,2) + R ) ;
           Descricao := UpperCase(Trim(copy(RetCmd,1,22))) ;

           if Descricao <> '' then
              Vinculados.Add(Descricao);
        end;

        for Cont := 0 to 9 do
        begin
           BytesResp := 21 ;
           RetCmd    := EnviaComando( '24' + IntToStrZero(Cont + 42,2) + R ) ;
           Descricao := Trim(copy(RetCmd,1,22)) ;

           if (Descricao <> '') then
           begin
              FPagto := TACBrECFFormaPagamento.create ;
              FPagto.Indice    := IntToStrZero(Cont,2) ;
              FPagto.Descricao := Descricao ;
//            FPagto.PermiteVinculado := (Vinculados.IndexOf(UpperCase(Descricao)) >= 0);

              fpFormasPagamentos.Add( FPagto ) ;
          end;
        end;
     finally
        Vinculados.Free ;
     end ;
  except
     fpFormasPagamentos.Free ;
     fpFormasPagamentos := nil ;

     raise ;
  end ;
end;

procedure TACBrECFUrano.ProgramaFormaPagamento( var Descricao: String;
  PermiteVinculado : Boolean; Posicao : String) ;
begin
  { c�digo aqui }
end;

procedure TACBrECFUrano.CarregaComprovantesNaoFiscais;
Var RetCmd: AnsiString;
    Cont  : Integer;
    CNF   : TACBrECFComprovanteNaoFiscal;
    Descr : String;
begin
  inherited CarregaComprovantesNaoFiscais;

  try
     for Cont := 0 to 14 do
     begin
        BytesResp := 21;
        RetCmd    := EnviaComando( '24' + IntToStrZero(Cont + 62,2) + R );
        Descr     := Trim(copy(RetCmd,1,22));

        if Descr <> '' then
        begin
           CNF := TACBrECFComprovanteNaoFiscal.create;

           CNF.Indice    := IntToStrZero(Cont,2);
           CNF.Descricao := Descr;

           fpComprovantesNaoFiscais.Add( CNF );
        end;
     end;
  except
     fpComprovantesNaoFiscais.Free ;
     fpComprovantesNaoFiscais := nil ;

     raise ;
  end ;
end;

procedure TACBrECFUrano.ProgramaComprovanteNaoFiscal(var Descricao : String;
   Tipo: String; Posicao : String);
begin
  { c�digo aqui }
end;

procedure TACBrECFUrano.AbreRelatorioGerencial;
begin
//  BytesResp := 0 ;
//  EnviaComando( '11' + '2' + R , 35 ) ;
end;

procedure TACBrECFUrano.LinhaRelatorioGerencial(Linha: AnsiString; IndiceBMP: Integer);
Var Linhas : TStringList ;
    I : Integer ;
    Buffer : String ;
begin
  Linha := AjustaLinhas( Linha, Colunas );

  Linhas := TStringList.Create ;
  try
     Linhas.Text := Linha ;

     while Linhas.Count > 0 do
     begin
        Buffer := '' ;
        I      := 0  ;
       { Formatando at� 10 Linhas em uma String de 480 bytes }
        while (Linhas.Count > 0) and (I < 10) do
        begin
           Buffer := Buffer + padL(Linhas[0],fpColunas) ;
           Linhas.Delete(0);
           Inc(I)
        end ;

        EnviaComando('06' + padL(Buffer, 480, #0) + R, 35);
     end ;
  finally
     Linhas.Free ;
  end ;
end;

procedure TACBrECFUrano.AbreCupomVinculado(COO, CodFormaPagto,
   CodComprovanteNaoFiscal :  String; Valor : Double ) ;
Var FPG : TACBrECFFormaPagamento ;
begin
  FPG := AchaFPGIndice( CodFormaPagto ) ;

  if FPG = nil then
     raise EACBrECFERRO.create( ACBrStr('Forma de Pagamento: '+CodFormaPagto+
                             ' n�o foi cadastrada.' )) ;

  EnviaComando( '44' + '000000' + '01' + R );
end;

procedure TACBrECFUrano.LinhaCupomVinculado(Linha: AnsiString);
begin
  LinhaRelatorioGerencial( Linha );
end;

procedure TACBrECFUrano.FechaRelatorio;
begin
  if Estado = estRelatorio then
     EnviaComando( '12' + padL(Operador, 8) + R, 5 ) ;  { Fecha o relatorio Gerencial ou Vinculado }
end;

procedure TACBrECFUrano.PulaLinhas(NumLinhas: Integer);
begin
  if NumLinhas = 0 then
     NumLinhas := LinhasEntreCupons ;

  EnviaComando('200' + IntToStrZero(NumLinhas,2) + R ) ;
end;

procedure TACBrECFUrano.LeituraMemoriaFiscal(ReducaoInicial,
   ReducaoFinal : Integer; Simplificada : Boolean);
var
  Datas,
  Reducoes: String;
begin
  // Urano n�o possui Leitura Simplificada
  Datas := Padl('',12);
  Reducoes := IntToStrZero(ReducaoInicial, 4) + IntToStrZero(ReducaoFinal, 4);

  EnviaComando('16' + '1' + Datas + Reducoes + R, 30 * (ReducaoFinal - ReducaoInicial + 1));
end;

procedure TACBrECFUrano.LeituraMemoriaFiscal(DataInicial,
   DataFinal: TDateTime; Simplificada : Boolean);
var
  Datas,
  Reducoes: String;
begin
  // Urano n�o possui Leitura Simplificada
  Datas := FormatDateTime('ddmmyy', DataInicial) +
    FormatDateTime('ddmmyy', DataFinal);
  Reducoes := Padl('',8);

  EnviaComando('16' + '0' + Datas + Reducoes + R, 30 * DaysBetween(DataFinal, DataInicial));
end;

procedure TACBrECFUrano.LeituraMemoriaFiscalSerial(ReducaoInicial,
   ReducaoFinal: Integer; Linhas : TStringList; Simplificada : Boolean);
begin
  // Urano n�o possui Leitura Simplificada
end;

procedure TACBrECFUrano.LeituraMemoriaFiscalSerial(DataInicial,
   DataFinal: TDateTime; Linhas : TStringList; Simplificada : Boolean);
Var
  Espera : Integer ;
begin
  // Urano n�o possui Leitura Simplificada
  Espera := Trunc(TimeOut + (DaysBetween(DataInicial,DataFinal)/5) ) ;
  BytesResp := -1 ;   { Reposta de Tamanho Variavel }
  Linhas.Clear ;
  Linhas.Text := EnviaComando( '16' +
                           FormatDateTime('ddmmyy',DataInicial)+
                           FormatDateTime('ddmmyy',DataFinal)  +
                           IntToStrZero(0,8) + R, Espera ) ;
end;

procedure TACBrECFUrano.AbreNaoFiscal( CPF_CNPJ, Nome, Endereco: String );
begin
  { nada a fazer, o Cupom N�o Fiscal ser� aberto no RegistraItemNaoFiscal }
end;

procedure TACBrECFUrano.RegistraItemNaoFiscal(CodCNF: String;
  Valor: Double; Obs: AnsiString);
begin
  { CodCNF deve ser numerico(2) }
  { Valor  deve ser numerico(9) }
  EnviaComando( '41' + CodCNF + StringOfChar(' ',20) +
                       IntToStrZero(Round(Valor * 100),9) + R );
end;

procedure TACBrECFUrano.NaoFiscalCompleto(CodCNF: String; Valor: Double;
  CodFormaPagto: String; Obs: AnsiString; IndiceBMP : Integer);
begin
  { Chama rotinas da classe Pai (fpOwner) para atualizar os Memos }
  with TACBrECF(fpOwner) do
  begin
     RegistraItemNaoFiscal(CodCNF, Valor);
     try
        EfetuaPagamento(CodFormaPagto, Valor );
        FechaCupom( Obs );
     except
        try
           CancelaNaoFiscal
        except
        end;

        raise ;
     end ;
  end ;
end;


function TACBrECFUrano.GetCNPJ: String;
begin
  Result := RetornaInfoECF('30');
end;

function TACBrECFUrano.GetIE: String;
begin
  Result := RetornaInfoECF('31');
end;

function TACBrECFUrano.GetGrandeTotal: Double;
begin
  Result := RetornaInfoECFValor('01');
end;

function TACBrECFUrano.GetNumCRZ: String;
begin
  Result := RetornaInfoECF('24');
end;

function TACBrECFUrano.GetNumGNF: String;
begin
  Result := RetornaInfoECF('20');
end;

function TACBrECFUrano.GetNumCOOInicial: String;
begin
  Result := RetornaInfoECF('17');
end;

function TACBrECFUrano.GetTotalCancelamentos: Double;
begin
  Result := RetornaInfoECFValor('02');
end;

function TACBrECFUrano.GetTotalDescontos: Double;
begin
  Result := RetornaInfoECFValor('04');
end;

function TACBrECFUrano.GetTotalAcrescimos: Double;
begin
  Result := RetornaInfoECFValor('05'); 
end;

function TACBrECFUrano.GetTotalIsencao: Double;
begin
  Result := RetornaInfoECFValor('13');
end;

function TACBrECFUrano.GetTotalNaoTributado: Double;
begin
  Result := RetornaInfoECFValor('14');
end;

function TACBrECFUrano.GetTotalSubstituicaoTributaria: Double;
begin
  Result := RetornaInfoECFValor('15');
end;

function TACBrECFUrano.GetVendaBruta: Double;
begin
  // GT final - GT inicial
  Result := GrandeTotal - RetornaInfoECFValor('32');
end;

procedure TACBrECFUrano.LerTotaisAliquota;
var i : Integer ;
begin
  if not Assigned( fpAliquotas ) then
     CarregaAliquotas ;

  for i := 0 to Aliquotas.Count-1 do
    Aliquotas[i].Total := RetornaInfoECFValor(IntToStrZero(i + 6, 2));
end;

procedure TACBrECFUrano.LerTotaisFormaPagamento;
var i : Integer ;
begin
  if not Assigned( fpFormasPagamentos ) then
     CarregaFormasPagamento ;

  for i := 0 to FormasPagamento.Count-1 do
    FormasPagamento[i].Total := RetornaInfoECFValor(IntToStrZero(i + 52, 2));
end;

function TACBrECFUrano.RetornaInfoECF(Registrador: String): AnsiString;
begin
  BytesResp := 21 ;
  Result := EnviaComando( '24' + Registrador + R ) ;
  Result := Trim( Result ) ;
end;

function TACBrECFUrano.RetornaInfoECFValor(ARegistrador: string): Double;
var RetCmd : AnsiString;
begin
  RetCmd := RetornaInfoECF(ARegistrador) ;
  RetCmd := StringReplace( RetCmd, '.', DecimalSeparator, []);
  Result := RoundTo(StrToFloatDef(RetCmd, 0), -2);
end;

end.



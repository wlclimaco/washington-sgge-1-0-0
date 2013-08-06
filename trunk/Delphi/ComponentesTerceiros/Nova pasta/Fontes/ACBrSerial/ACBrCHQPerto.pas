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
|* Agradecimentos �:
|* Miguel Silva  -  Perto S/A - Teste deste Modulo  
******************************************************************************}

{******************************************************************************
|* Historico
|*
|* 24/08/2004: Daniel Simoes de Almeida
|*  - Primeira Versao ACBrCHQPerto
|* 07/07/2009: Marcelo Correia Pinheiro
|*  - Corrigido bug na comuica��o com a impressora e defini��o de timeouts
|*    para comandos enviados � impressora.
******************************************************************************}

{$I ACBr.inc}

unit ACBrCHQPerto;

interface
uses ACBrCHQClass,  
     Classes ;

Const cCmdImpCheque = ';9' ;

type TACBrCHQPerto = class( TACBrCHQClass )
  private
    fsAguardandoResposta : Boolean ;
    fsBancoLido: String;
    fsContaLida: String;
    fsAgenciaLida: String;
    fsChequeLido: String;
    fsCompLida: String;
    fsCmdImpCheque: String;

  protected
    procedure SetBomPara(const Value : TDateTime); override;

  public
    constructor Create(AOwner: TComponent);

    procedure Ativar ; override ;

    Function EnviaComando( cmd : String; SecTimeOut : Integer = 2 ) : String ;
    Function PreparaCmd( cmd : String ) : String ;
    Procedure VerificaErro( Err : String ) ;

    Property AguardandoResposta : Boolean read fsAguardandoResposta ;

    Property CmdImpCheque : String read fsCmdImpCheque write fsCmdImpCheque ;
    Property BancoLido    : String read fsBancoLido   ;
    Property AgenciaLida  : String read fsAgenciaLida ;
    Property ContaLida    : String read fsContaLida   ;
    Property ChequeLido   : String read fsChequeLido  ;
    Property CompLida     : String read fsCompLida ;

    procedure ImprimirCheque ; Override ;
    procedure ImprimirVerso( AStringList : TStrings ) ; Override ;
    Procedure TravarCheque ;  Override ;
    Procedure DestravarCheque ;  Override ;

end ;

implementation
Uses ACBrUtil, ACBrDevice, ACBrConsts,
   {$IFDEF COMPILER6_UP} DateUtils {$ELSE} ACBrD5{$ENDIF},
     SysUtils ;

{ TACBrCHQPerto }

constructor TACBrCHQPerto.Create(AOwner: TComponent);
begin
  inherited Create( AOwner );

  fpModeloStr := 'Perto' ;
  fpDevice.HandShake := hsDTR_DSR ;

  fsCmdImpCheque       := cCmdImpCheque ;
  fsAguardandoResposta := false ;
  fsBancoLido          := '' ;
  fsContaLida          := '' ;
  fsAgenciaLida        := '' ;
  fsChequeLido         := '' ;
  fsCompLida           := '' ;
end;

procedure TACBrCHQPerto.Ativar;
begin
  if not fpDevice.IsSerialPort then
     raise Exception.Create(ACBrStr('Impressora de Cheques '+fpModeloStr+' requer'+#10+
                            'Porta Serial (COMn)'));

  fsAguardandoResposta := false ;
  fpDevice.HandShake := hsDTR_DSR ;
 
  inherited Ativar ; { Abre porta serial }
end;

procedure TACBrCHQPerto.TravarCheque;
Var Resp : String ;
begin
  fsBancoLido   := '' ;
  fsContaLida   := '' ;
  fsAgenciaLida := '' ;
  fsChequeLido  := '' ;
  fsCompLida    := '' ;

  Resp := EnviaComando('=', 30) ; { Ler e Alinhar Cheque }
  VerificaErro( Resp ) ;

  fsBancoLido   := copy( Resp,4,3) ;
  fsAgenciaLida := copy( Resp,7,4) ;
  fsContaLida   := copy( Resp,11,10) ;
  fsChequeLido  := copy( Resp,21,6) ;
  fsCompLida    := copy( Resp,27,3) ;
  fpCMC7        := Resp;
end;

procedure TACBrCHQPerto.DestravarCheque;
begin
  VerificaErro( EnviaComando('>', 30) ) { Expulsar Cheque }
end;

procedure TACBrCHQPerto.ImprimirCheque;
Var ValStr, DataStr : String ;
begin
  TravarCheque ;

  { Favorecido }
  VerificaErro( EnviaComando( '%' + Trim(UpperCase(fpFavorecido)), 2 ));

  { Cidade }
  VerificaErro( EnviaComando( '#' + Trim(UpperCase(fpCidade)), 2 ));

  { Data }
  DataStr := FormatDateTime('ddmmyy', fpData) ;
  VerificaErro( EnviaComando( '!' + DataStr, 2 ));

  { Comanda Preenchimento }
  ValStr := IntToStrZero( Round( fpValor * 100), 12) ;
  VerificaErro( EnviaComando( fsCmdImpCheque + ValStr + fpBanco, 30));
end;

procedure TACBrCHQPerto.ImprimirVerso(AStringList: TStrings);
Var Texto, Texto1, Texto2 : String ;
begin
  if AStringList.Text = '' then exit ;

  Texto1 := '' ;
  Texto2 := '' ;
  if AStringList.Count > 2 then
   begin
     Texto  := StringReplace( AStringList.Text, #13+#10,' / ',[rfReplaceAll]) ;
     Texto1 := copy(Texto,01,60) ;
     Texto2 := copy(Texto,61,60) ;
   end
  else
   begin
     if AStringList.Count > 0 then
        Texto1 := AStringList[0] ;
     if AStringList.Count > 1 then
        Texto2 := AStringList[1] ;
   end ;

  Texto1 := padL( UpperCase( TiraAcentos( Texto1 )),60) ;
  Texto2 := padL( UpperCase( TiraAcentos( Texto2 )),60) ;

  VerificaErro( EnviaComando( '"' + Texto1+#255 + Texto2+#255 ));

end;

function TACBrCHQPerto.EnviaComando(cmd: String; SecTimeOut : Integer): String;
Var ACK : Byte ;
    wTempoLimite : TDateTime ;
begin

  result  := '' ;
  SecTimeOut := SecTimeOut * 1000 ;

  fpDevice.Serial.DTR := true;
  fpDevice.Serial.DeadlockTimeout := SecTimeOut ; { Ajusta Timeout }

  try
     if not fpDevice.EmLinha( 3 ) then  { Impressora est� em-linha ? }
       raise Exception.Create(ACBrStr('A impressora de Cheques '+fpModeloStr+
                              ' n�o est� pronta.')) ;

     { Para evita chamadas recursivas, enquanto j� est� esperando uma resposta }
     fsAguardandoResposta := true ;
     try
        { Codificando CMD de acordo com o protocolo da PertoCheck }
        cmd := PreparaCmd( cmd ) ;
        fpComandoEnviado := cmd ;

        repeat
           try
              fpDevice.Serial.Purge;
              fpDevice.EnviaString( cmd );   { Eviando o comando }

              { p�e pra dormir para atualizar o buffer da porta serial... }
              Sleep(200);
           except
              raise Exception.create(ACBrStr('Erro ao enviar comandos para a PertoCheck')) ;
           end ;

           try
              ACK := fpDevice.Serial.RecvByte(SecTimeOut) ;
           except
              raise Exception.create(ACBrStr('PertoCheck n�o responde'));
           end ;

           if ACK = 21 then
              raise Exception.create(ACBrStr('PertoCheck n�o reconheceu o comando')) ;
        until ACK = 6;

        { Le conteudo da porta }
        { Calcula Tempo Limite. Espera resposta at� Tempo Limite. Se a resposta
          for Lida antes, j� encerra. Se nao chegar at� TempoLimite, gera erro.}
        fpRespostaComando := '' ;
        wTempoLimite := IncMilliSecond( now, SecTimeOut) ;

        repeat
           try
              fpRespostaComando := fpRespostaComando + { Le conteudo da porta }
                                   fpDevice.Serial.RecvPacket(SecTimeOut) ;
           except
              { Exce�ao silenciosa }
           end ;

           if now > wTempoLimite then       { TimeOut }
              raise Exception.create(ACBrStr('Impressora PertoCheck n�o est� respondendo')) ;
        until (copy(fpRespostaComando, length(fpRespostaComando) - 1, 1) = #3);

        { Separando o Retorno... Tirando STX, cmd, ETX, BCC }
        fpRespostaComando := copy(fpRespostaComando, 3, length(fpRespostaComando)-4);
        Result := fpRespostaComando;
     finally
        fsAguardandoResposta := false ;
     end ;
  except
     raise;
  end ;
end;

Function TACBrCHQPerto.PreparaCmd( cmd : String ) : String ;
Var A : Integer ;
    BCC : Byte ;
begin

  result := '' ;
  if cmd = '' then exit ;

  cmd := STX + cmd + ETX ;   { STX + COMANDO + DADOS + ETX }

  { Calculando BCC }
  BCC := ord(cmd[1]) xor ord(cmd[2]) ;
  For A := 3 to Length(cmd) do
     BCC := BCC xor ord(cmd[A]) ;

  result := cmd + chr(BCC) ;
end ;

procedure TACBrCHQPerto.VerificaErro(Err: String);
Var MsgErro : String ;
begin
  Err := IntToStrZero( StrToIntDef( copy(Err,1,3) , 0 ), 3) ;

  if Err = '000' then exit ;

  MsgErro := '' ;
  if Err = '001' then MsgErro := 'Mensagem com dados inv�lidos.' ;
  if Err = '002' then MsgErro := 'Tamanho de mensagem inv�lido.' ;
  if Err = '005' then MsgErro := 'Leitura dos caracteres magn�ticos inv�lida.';
  if Err = '006' then MsgErro := 'Problemas no acionamento do motor 1.' ;
  if Err = '008' then MsgErro := 'Problemas no acionamento do motor 2.' ;
  if Err = '009' then MsgErro := 'Banco diferente do solicitado.';
  if Err = '011' then MsgErro := 'Sensor 1 obstru�do.' ;
  if Err = '012' then MsgErro := 'Sensor 2 obstru�do.' ;
  if Err = '013' then MsgErro := 'Sensor 4 obstru�do.' ;
  if Err = '014' then
     MsgErro := 'Erro no posicionamento da cabe�a de impress�o (relativo a S4).';
  if Err = '016' then
     MsgErro := 'D�gito verificador do cheque n�o confere.' ;
  if Err = '017' then
     MsgErro := 'Aus�ncia de caracteres magn�ticos ou cheque na posi��o errada.';
  if Err = '018' then MsgErro := 'Tempo esgotado.' ;
  if Err = '019' then MsgErro := 'Documento mal inserido.' ;
  if Err = '020' then
     MsgErro := 'Cheque preso durante o alinhamento (S1 e S2 desobstru�dos).';
  if Err = '021' then
     MsgErro := 'Cheque preso durante o alinhamento (S1 obstru�do e S2 desobstru�do).';
  if Err = '022' then
     MsgErro := 'Cheque preso durante o alinhamento (S1 desobstru�do e S2 obstru�do).';
  if Err = '023' then
     MsgErro := 'Cheque preso durante o alinhamento (S1 e S2 obstru�dos).';
  if Err = '024' then
     MsgErro := '	Cheque preso durante o preenchimento (S1 e S2 desobstru�dos).';
  if Err = '025' then
     MsgErro := 'Cheque preso durante o preenchimento (S1 obstru�do e S2 desobstru�do).';
  if Err = '026' then
     MsgErro := 'Cheque preso durante o preenchimento (S1 desobstru�do e S2 obstru�do).';
  if Err = '027' then
     MsgErro := 'heque preso durante o preenchimento (S1 e S2 obstru�dos).';
  if Err = '028' then MsgErro := 'Caractere inexistente.';
  if Err = '030' then MsgErro := 'N�o h� cheques na mem�ria.';
  if Err = '031' then MsgErro := 'Lista negra interna cheia';
  if Err = '042' then MsgErro := 'Cheque ausente.';
  if Err = '050' then MsgErro := 'Erro de transmiss�o.' ;
  if Err = '051' then
     MsgErro := 'Erro de transmiss�o: Impressora offline, desconectada ou ocupada.';
  if Err = '060' then MsgErro := 'Cheque na lista negra.';
  if Err = '073' then MsgErro := 'Cheque n�o encontrado na lista negra.';
  if Err = '074' then MsgErro := 'Comando cancelado.' ;
  if Err = '084' then MsgErro := 'Arquivo de layout�s cheio';
  if Err = '085' then MsgErro := 'Layout inexistente na mem�ria.';
  if Err = '091' then MsgErro := 'Leitura de cart�o inv�lida.';
  if Err = '097' then MsgErro := 'Cheque na posi��o errada.';
  if Err = '255' then MsgErro := 'Comando inexistente.';

  MsgErro := 'PertoCheck retorno erro: '+Err+#10+' '+MsgErro ;

  raise Exception.Create(ACBrStr(MsgErro));

end;

procedure TACBrCHQPerto.SetBomPara(const Value : TDateTime);
begin
  inherited SetBomPara(Value);
  VerificaErro( EnviaComando('+BOM PARA: ' + FormatDateTime('dd/mm/yy', fpBomPara), 2) );
end;

end.


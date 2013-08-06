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
|* 04/10/2005: Daniel Sim�es de Almeida
|*  - Primeira Versao ACBrBALToledo
|* 11/04/2007 Daniel Sim�es de Almeida
|*  - Corrigido para trabalhar com diversos protocolos da Toledo
******************************************************************************}

{$I ACBr.inc}

unit ACBrBALToledo;

interface
uses ACBrBALClass,
     Classes;

type
  TACBrBALToledo = class( TACBrBALClass )
  public
    constructor Create(AOwner: TComponent);
    function LePeso( MillisecTimeOut : Integer = 3000) :Double; override;
    procedure LeSerial( MillisecTimeOut : Integer = 500) ; override ;
  end ;

implementation
Uses ACBrUtil, ACBrConsts,
     {$IFDEF COMPILER6_UP} DateUtils, StrUtils {$ELSE} ACBrD5, synaser, Windows{$ENDIF},
     SysUtils, Math ;

{ TACBrBALToledo }

constructor TACBrBALToledo.Create(AOwner: TComponent);
begin
  inherited Create( AOwner );

  fpModeloStr := 'Toledo' ;
end;

function TACBrBALToledo.LePeso( MillisecTimeOut : Integer) : Double;
begin
  fpUltimoPesoLido := 0 ;
  fpUltimaResposta := '' ;
  
  fpDevice.Serial.Purge ;           { Limpa a Porta }
  fpDevice.EnviaString( #05 );      { Envia comando solicitando o Peso }
  sleep(200) ;

  LeSerial( MillisecTimeOut );

  Result := fpUltimoPesoLido ;
end;

procedure TACBrBALToledo.LeSerial(MillisecTimeOut: Integer);
Var
  Resposta : AnsiString ;
  Decimais : Integer ;
  St2      : AnsiChar ;
  PI,PF    : Integer ;
begin
  fpUltimoPesoLido := 0 ;
  fpUltimaResposta := '' ;

  Decimais := 1000 ;
  Try
     fpUltimaResposta := fpDevice.Serial.RecvPacket( MillisecTimeOut );

     if Length(fpUltimaResposta) > 20 then
      begin
        { Protocolo A
          [ STX ] [ S1 ] [ PPPPPP ] [ S2 ] [ TTTTTT ] [ UUUUUU ] [ CR ] [ CS ]
          S1 = 1 byte - Status 1
          PPPPPP = 6 bytes - peso
          S2 = 1 byte - Status 2
          TTTTTT = 6 bytes - Pre�o Total
          UUUUUU = 6 bytes - Pre�o/kg
          CS = 1 byte - Checksum. O c�lculo do checksum � feito pelo complemento
             de 2 da soma de todos os bytes transmitidos de STX, incluindo o CR.

          S1 - STATUS 1
          bit 0 = motion flag
          bit 1 = print flag
          bit 2 = data do sistema ( 0 = pesagem e 1 = data )
          bit 3 = out of range
          bit 4 = tipo de balan�a ( 0 = Prix II/ Prix III e 1 = Prix I )
          bit 5 = n�mero de casas decimais ( 0 = 2 casas e 1 = 0 casas )
          bit 6 = autoriza��o de totaliza��o ( 0 = n�o e 1 = sim totaliza )

          S2 - STATUS 2
          bit 0 = s�timo d�gito no pre�o total ( 0 = sem 1 = com )
          bit 1 = reservado
          bit 2 = reservado
          bit 3 = casas decimais no peso ( 0 = 3 casas e 1 = 2 casas )
          bit 4 = reservado
          bit 5 = reservado
          bit 6 = opera��o com tara ( 0 = sem tara e 1 = com tara ) deve
                  imprimir peso l�quido         }
                  
        St2 := fpUltimaResposta[9] ;
        if TestBit(Ord(St2),3) then   { Bit 3 de ST2 ligado = 2 casas decimais }
           Decimais := 100 ;
        Resposta := Trim(Copy(fpUltimaResposta,3,6));
      end

     else
      begin
      { Protocolo B = [ ENQ ] [ STX ] [ PESO ] [ ETX ]
        Protocolo C = [ STX ] [ PESO ] [ CR ]
        Linha Automacao = [ STX ] [ PPPPP ] [ ETX ]  - Peso Est�vel;
                          [ STX ] [ IIIII ] [ ETX ]  - Peso Inst�vel;
                          [ STX ] [ NNNNN ] [ ETX ]  - Peso Negativo;
                          [ STX ] [ SSSSS ] [ ETX ]  - Peso Acima (Sobrecarga) }

        PI := pos(STX, fpUltimaResposta) ;
        PF := pos(ETX, fpUltimaResposta) ;
        if PF = 0 then                       { N�o achou ETX, procura por CR }
           PF := pos(CR, fpUltimaResposta) ;
        if PF = 0 then                       { N�o achou CR, usa toda a String } 
           PF := Length(fpUltimaResposta) + 1 ;

        Resposta := Trim( copy( fpUltimaResposta, PI+1, PF-PI-1 )) ;
      end ;

     { Ajustando o separador de Decimal corretamente }
     Resposta := StringReplace(Resposta, '.', DecimalSeparator, [rfReplaceAll]);
     Resposta := StringReplace(Resposta, ',', DecimalSeparator, [rfReplaceAll]);

     try
        if pos(DecimalSeparator,Resposta) > 0 then  { J� existe ponto decimal ? }
           fpUltimoPesoLido := StrToFloat(Resposta)
        else
           fpUltimoPesoLido := StrToInt(Resposta) / Decimais ;
     except
        case Resposta[1] of
          'I' : fpUltimoPesoLido := -1  ;  { Instavel }
          'N' : fpUltimoPesoLido := -2  ;  { Peso Negativo }
          'S' : fpUltimoPesoLido := -10 ;  { Sobrecarga de Peso }
        else
           fpUltimoPesoLido := 0 ;
        end;
     end;
  except
     { Peso n�o foi recebido (TimeOut) }
     fpUltimoPesoLido := -9 ;
  end ;
end;

end.

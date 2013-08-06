{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2004 Fabio Farias                           }
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
{ http://www.opensource.org/licenses/lgpl-license.php                          }
{                                                                              }
{ Daniel Sim�es de Almeida  -  daniel@djsystem.com.br  -  www.djsystem.com.br  }
{              Pra�a Anita Costa, 34 - Tatu� - SP - 18270-410                  }
{                                                                              }
{******************************************************************************}

{******************************************************************************
|* Historico
|*
|* 04/10/2005: Fabio Farias  / Daniel Sim�es de Almeida
|*  - Primeira Versao ACBrBALFilizola
|* 25/05/2005: Daniel Simoes de Almeida
|*  - Adaptado para funcionar com v�rios modelos de Filizola (MF, BP) permitindo
|*    varia��o na posi��o do ponto flutante
|* 16/02/2007: Juliano Pereira dos Santos
|*  - Adaptado para funcionar com modelo "CS"
******************************************************************************}

{$I ACBr.inc}

unit ACBrBALFilizola;

interface
uses ACBrBALClass,
     Classes;

type
  TACBrBALFilizola = class( TACBrBALClass )
  public
    constructor Create(AOwner: TComponent);
    function LePeso( MillisecTimeOut : Integer = 3000) :Double; override;
    procedure LeSerial( MillisecTimeOut : Integer = 500) ; override ;
  end ;

implementation
Uses ACBrConsts,
     {$IFDEF COMPILER6_UP} DateUtils, StrUtils {$ELSE} ACBrD5, Windows{$ENDIF},
     SysUtils ;

{ TACBrBALGertecSerial }

constructor TACBrBALFilizola.Create(AOwner: TComponent);
begin
  inherited Create( AOwner );

  fpModeloStr := 'Filizola' ;
end;

function TACBrBALFilizola.LePeso( MillisecTimeOut : Integer) : Double;
Var TempoFinal : TDateTime ;
begin
  { A Filizola pode responder com Instavel inicalmente, mas depois ela poderia
    estabilizar... Portanto o Loop abaixo tenta ler um Peso v�lido at� o limite
    de tempo estabelecido em "MilliSecTimeOut" ser atingido ou um Peso valido
    retornado }
  Result := 0 ;
  fpUltimoPesoLido := 0 ;
  fpUltimaResposta := '' ;
  TempoFinal := IncMilliSecond(now,MillisecTimeOut) ;

  while (Result <= 0) and (TempoFinal > now) do
  begin
     fpDevice.Serial.Purge ;
     fpDevice.EnviaString( #05 );      { Envia comando solicitando o Peso }
     sleep(200) ;
     MillisecTimeOut := MilliSecondsBetween(now,TempoFinal) ;

     LeSerial( MillisecTimeOut );

     Result := fpUltimoPesoLido ;
  end ;
end;

procedure TACBrBALFilizola.LeSerial( MillisecTimeOut : Integer) ;
Var Resposta : AnsiString ;
begin
  fpUltimoPesoLido := 0 ;
  fpUltimaResposta := '' ;

  Try
     fpUltimaResposta := fpDevice.Serial.RecvPacket( MillisecTimeOut );

     { Retira STX, ETX }
     Resposta := fpUltimaResposta;
     if copy(Resposta, 1, 1) = STX then
        Resposta := copy(Resposta, 2, Length(Resposta));

     if copy(Resposta, Length(Resposta), 1) = ETX then
        Resposta := copy(Resposta, 1, Length(Resposta) - 1);
     
     { Ajustando o separador de Decimal corretamente }
     Resposta := StringReplace(Resposta, '.', DecimalSeparator, [rfReplaceAll]);
     Resposta := StringReplace(Resposta, ',', DecimalSeparator, [rfReplaceAll]);

     try
        if Length(Resposta) > 10 then
           fpUltimoPesoLido := StrToFloat(copy(Resposta, 1, 6)) / 1000
        else if pos(DecimalSeparator, Resposta) > 0 then
           fpUltimoPesoLido := StrToFloat(Resposta)
        else
           fpUltimoPesoLido := StrToInt(Resposta) / 1000
     except
        case Trim(Resposta)[1] of
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

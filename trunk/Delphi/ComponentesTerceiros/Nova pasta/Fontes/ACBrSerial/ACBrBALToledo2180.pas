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

unit ACBrBALToledo2180;

interface
uses ACBrBALClass,
     Classes;

type
  TACBrBALToledo2180 = class( TACBrBALClass )
  public
    constructor Create(AOwner: TComponent);
    function LePeso( MillisecTimeOut : Integer = 3000) :Double; override;
    procedure LeSerial( MillisecTimeOut : Integer = 500) ; override ;
  end ;

implementation
Uses ACBrUtil, ACBrConsts,
     {$IFDEF COMPILER6_UP} DateUtils, StrUtils {$ELSE} ACBrD5, synaser, synaser, Windows{$ENDIF},
     SysUtils, Math ;

{ TACBrBALToledo2180 }

constructor TACBrBALToledo2180.Create(AOwner: TComponent);
begin
  inherited Create( AOwner );

  fpModeloStr := 'Toledo 2180' ;
end;

function TACBrBALToledo2180.LePeso( MillisecTimeOut : Integer) : Double;
begin
  fpUltimoPesoLido := 0 ;
  fpUltimaResposta := '' ;

  fpDevice.Serial.Purge ;           { Limpa a Porta }
  fpDevice.EnviaString( #05 );      { Envia comando solicitando o Peso }
  sleep(200) ;

  LeSerial( MillisecTimeOut );

  Result := fpUltimoPesoLido;
end;

procedure TACBrBALToledo2180.LeSerial(MillisecTimeOut: Integer);
Var
  Resposta : AnsiString ;
  P, I     : Integer ;
  Pesos    : array[1..5] of Double;
begin
  fpUltimoPesoLido := 0 ;
  fpUltimaResposta := '' ;

  Try
     for I := 1 to 5 do
     begin
        fpUltimaResposta := fpDevice.Serial.RecvTerminated( MillisecTimeOut, #13);

        P := Pos( #96, fpUltimaResposta );

        if P > 0 then
        begin
           Resposta := Copy(fpUltimaResposta, P + 1, Length(fpUltimaResposta) );
           Resposta := Copy(Resposta,1,6);
           Insert('.',Resposta,6);
        end
        else
           Resposta := 'I';

        { Ajustando o separador de Decimal corretamente }
        Resposta := StringReplace(Resposta, '.', DecimalSeparator, [rfReplaceAll]);
        Resposta := StringReplace(Resposta, ',', DecimalSeparator, [rfReplaceAll]);

        try
           Pesos[I] := StrToFloat(Resposta)
        except
           Pesos[I] := 0;
        end;
     end;

     if (Pesos[3] = Pesos[4]) and (Pesos[4] = Pesos[5]) then
        fpUltimoPesoLido := pesos[5]
     else
        fpUltimoPesoLido := -1 ;

  except
     { Peso n�o foi recebido (TimeOut) }
     fpUltimoPesoLido := -9 ;
  end ;
end;

end.

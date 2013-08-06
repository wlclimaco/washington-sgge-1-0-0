{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2004 Ivan Carlos Martello                   }
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
|* 17/02/2009: Ivan Carlos Martello
|*  - Primeira Versao ACBrBALUrano
******************************************************************************}

{$I ACBr.inc}

unit ACBrBALUrano;

interface
uses ACBrBALClass,
     Classes;

type
  TACBrBALUrano = class( TACBrBALClass )
  public
    constructor Create(AOwner: TComponent);
    function LePeso( MillisecTimeOut : Integer = 3000) :Double; override;
    procedure LeSerial( MillisecTimeOut : Integer = 500) ; override ;
  end ;

implementation
Uses
  ACBrConsts,
  {$IFDEF COMPILER6_UP} DateUtils, StrUtils {$ELSE} ACBrD5, Windows{$ENDIF},
  SysUtils ;

{ TACBrBALGertecSerial }

constructor TACBrBALUrano.Create(AOwner: TComponent);
begin
  inherited Create( AOwner );
  fpModeloStr := 'Urano' ;
end;

function TACBrBALUrano.LePeso(MillisecTimeOut : Integer) : Double;
begin
  fpDevice.Serial.Purge;
  fpDevice.EnviaString(#05); { Envia comando solicitando o Peso }
  sleep(200);
  LeSerial( MillisecTimeOut );
  Result := fpUltimoPesoLido;
end;

procedure TACBrBALUrano.LeSerial( MillisecTimeOut : Integer) ;
 var
   Resposta: String;
   Quantos, PosDelim: integer;
begin
  fpUltimoPesoLido := 0 ;
  fpUltimaResposta := '' ;
  try
    fpUltimaResposta := fpDevice.Serial.RecvPacket( MillisecTimeOut );
    //fpUltimaResposta := '1BT11BA131BN01BS21BD41BQ1931BB * PESO: 5,10kg1BE1BP01';
    Resposta := fpUltimaResposta;

    PosDelim := pos(':', Resposta);
    if PosDelim = 0 then
       PosDelim := pos('N0', Resposta);

    if Copy(Resposta, pos('PESO', Resposta)-2, 1) = ' ' then
      Resposta := 'I'
    else if Copy(Resposta, PosDelim + 1, 1) = '-' then
      Resposta := 'N';

    if Length(Resposta) > 1  then
    begin
      Quantos := (pos('g', Resposta) - 2);
      Quantos := Quantos - (PosDelim + 1);
      Resposta := Copy(Resposta, PosDelim + 2, Quantos); //123456
    end;

    { Ajustando o separador de Decimal corretamente }
    Resposta := StringReplace(Resposta, '.', DecimalSeparator, [rfReplaceAll]);
    Resposta := StringReplace(Resposta, ',', DecimalSeparator, [rfReplaceAll]);

    try
      if pos(DecimalSeparator, Resposta) > 0 then  { J� existe ponto decimal ? }
        fpUltimoPesoLido := StrToFloat(Resposta)
      else
        fpUltimoPesoLido := StrToInt(Resposta) / 1000 ;
    except
      case Trim(Resposta)[1] of
        'I' : fpUltimoPesoLido := -1  ;  { Instavel }
        'N' : fpUltimoPesoLido := -2  ;  { Peso Negativo }
        //'S' : fpUltimoPesoLido := -10 ;  { Sobrecarga de Peso }
      else
        fpUltimoPesoLido := 0 ;
      end;
    end;
  except
     { Peso n�o foi recebido (TimeOut) }
     fpUltimoPesoLido := -9;
  end ;
end;

end.


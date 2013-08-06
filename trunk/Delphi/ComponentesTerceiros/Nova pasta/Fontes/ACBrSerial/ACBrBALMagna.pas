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
|* 05/11/2011: Levindo Damasceno
|*  - Primeira Versao ACBrBALFilizola
******************************************************************************}

{$I ACBr.inc}

unit ACBrBALMagna;

interface
uses ACBrBALClass,
     Classes;

type
  TACBrBALMagna = class( TACBrBALClass )
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

constructor TACBrBALMagna.Create(AOwner: TComponent);
begin
  inherited Create( AOwner );

  fpModeloStr := 'Magna' ;
end;

function TACBrBALMagna.LePeso( MillisecTimeOut : Integer) : Double;
Var TempoFinal : TDateTime ;
begin
  Result := 0 ;
  fpUltimoPesoLido := 0 ;
  fpUltimaResposta := '' ;
  TempoFinal := IncMilliSecond(now,MillisecTimeOut) ;

  while (Result <= 0) and (TempoFinal > now) do
  begin
     fpDevice.Serial.Purge ;
     fpDevice.EnviaString( #80 );      { Envia comando solicitando o Peso }
     sleep(200) ;
     MillisecTimeOut := MilliSecondsBetween(now,TempoFinal) ;

     LeSerial( MillisecTimeOut );

     Result := fpUltimoPesoLido ;
  end ;
end;

procedure TACBrBALMagna.LeSerial( MillisecTimeOut : Integer) ;
Var Resposta : AnsiString ;
    MInicio  : Integer;
    MFinal   : integer;
begin
  fpUltimoPesoLido := 0 ;
  fpUltimaResposta := '' ;

  Try
     fpUltimaResposta := fpDevice.Serial.RecvPacket( MillisecTimeOut );
     fpUltimaResposta := Copy(fpUltimaResposta,1,pos(#10,fpUltimaResposta)-1);

     { Limpa para reposta para pegar o Valor }
      Resposta := fpUltimaResposta;

      MInicio := Pos(' ',Resposta);
      Mfinal  := Pos('k',Resposta);
      if Mfinal = 0 then
         Mfinal := Length(resposta);
      Resposta := Copy (Resposta,Minicio,MFinal - Minicio);

      Resposta := StringReplace(Resposta, '.', DecimalSeparator, [rfReplaceAll]);
      Resposta := StringReplace(Resposta, ',', DecimalSeparator, [rfReplaceAll]);


     try
         if Length(Resposta) > 10 then
            fpUltimoPesoLido := StrToFloat(copy(Resposta, 1, 6)) / 1000
         else if pos(Decimalseparator, Resposta) > 0 then                        
           fpUltimoPesoLido := StrToFloat(Resposta)
        else
           fpUltimoPesoLido := StrToInt(Resposta) / 1000
     except
        case Trim(Resposta)[1] of
//          'I' : fpUltimoPesoLido := -1  ;  { Instavel }
            'N' : begin
                   fpUltimoPesoLido := -2  ;  { Peso Negativo }
                   fpUltimaResposta := 'Peso Negativo ';
                  end;
//          'S' : fpUltimoPesoLido := -10 ;  { Sobrecarga de Peso }
        else
           fpUltimoPesoLido := 0 ;
        end;
     end;
  except
     { Peso n�o foi recebido (TimeOut) }
     fpUltimoPesoLido := -9 ;
     fpUltimaResposta := 'Peso N�o Lido ';
  end ;
end;

end.

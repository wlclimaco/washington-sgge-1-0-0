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
|* 27/08/2010: Jo�o Paulo
|*  - Primeira Versao ACBrBALLucasTec
******************************************************************************}

{$I ACBr.inc}

unit ACBrBALLucasTec;

interface
uses ACBrBALClass,
     Classes;

type
  TACBrBALLucasTec = class( TACBrBALClass )
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

constructor TACBrBALLucasTec.Create(AOwner: TComponent);
begin
  inherited Create( AOwner );
  fpModeloStr := 'LucasTec' ;
end;

function TACBrBALLucasTec.LePeso(MillisecTimeOut : Integer) : Double;
begin
  fpUltimoPesoLido := 0 ;
  fpUltimaResposta := '' ;

  fpDevice.Serial.Purge;
  fpDevice.EnviaString( #05 );      { Envia comando solicitando o Peso }
  sleep(200) ;

  LeSerial( MillisecTimeOut );

  Result := fpUltimoPesoLido ;
end;

procedure TACBrBALLucasTec.LeSerial( MillisecTimeOut : Integer) ;
var Resposta: String;
    Peso, PesoL, PesoB, PesoT:Double;
    i:Integer;
    pesos:array[1..4] of Double;

    function StrToPeso(StrPeso:string):Double;
    var ii,pp:Integer;
        s:String;
    begin
      Result:=0;
      pp:=Pos(UpperCase(StrPeso),UpperCase(Resposta));
      if pp>0 then begin
         s:=trim(Copy(Resposta,1,pp-1));
         for ii:=Length(s) downto 1 do
           if s[ii]=' ' then begin
              s:=RightStr(s,Length(s)-ii+1);
              { Ajustando o separador de Decimal corretamente }
              s := StringReplace(s, '.', DecimalSeparator, [rfReplaceAll]);
              s := StringReplace(s, ',', DecimalSeparator, [rfReplaceAll]);
              try
                if pos(DecimalSeparator, s) > 0 then  { J� existe ponto decimal ? }
                   Result := StrToFloat(s)
                  else
                   Result := StrToInt(s) / 1000 ;
              except
                Result := 0 ;
              end;
              break;
           end;

      end;
    end;

begin
  fpUltimoPesoLido := 0 ;
  fpUltimaResposta := '' ;
  try
    for i:=1 to 4 do begin
      //fpUltimaResposta := fpDevice.Serial.RecvPacket( MillisecTimeOut);
      fpUltimaResposta := fpDevice.Serial.RecvTerminated( MillisecTimeOut, #13 );
      //fpUltimaResposta := #2+'0000 000001 20/06/05 50.00 kg B  0.60 kg T  49.40 kg L'+#13#180;
      fpUltimaResposta := Copy(fpUltimaResposta,Pos(#2,fpUltimaResposta)+1,Length(fpUltimaResposta));

      Resposta := fpUltimaResposta;

      if not ((Pos('kg B ',Resposta)>0)) then
      begin
        Resposta := 'I';
      end;

      Peso := 0;
      if Length(Resposta) > 1  then
      begin
         PesoL:=StrToPeso('kg L');
         PesoT:=StrToPeso('kg T');
         PesoB:=StrToPeso('kg B');
         if PesoT > PesoB then begin
            Peso:=-2;
            Resposta:='N';
           end
           else
            if PesoL>0 then
               Peso:=PesoL
              else
               Peso:=PesoB;
      end;

      pesos[i]:=Peso;
    end;

    if (pesos[2]=pesos[3]) and (pesos[3]=pesos[4]) then
       fpUltimoPesoLido := pesos[4]
      else begin
       fpUltimoPesoLido := -1 ;
    end;

  except
     { Peso n�o foi recebido (TimeOut) }
     on E:Exception do begin
        fpUltimoPesoLido := -9;
        fpUltimaResposta := e.Message;
     end;
  end ;
end;

end.


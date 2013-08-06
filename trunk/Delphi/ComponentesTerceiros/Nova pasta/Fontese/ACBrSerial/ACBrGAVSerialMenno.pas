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
|* 28/06/2004: Daniel Simoes de Almeida
|*  - Primeira Versao ACBrGAV
******************************************************************************}

{$I ACBr.inc}

unit ACBrGAVSerialMenno;

interface
uses ACBrGAVClass, 
     Classes ;

type
TACBrGAVSerialMenno = class( TACBrGAVClass )
  private

  protected
    function GetGavetaAberta: Boolean; override ;

  public
    constructor Create(AOwner: TComponent);

    procedure Ativar ; override ;

    Procedure AbreGaveta  ; override ;
    Property GavetaAberta : Boolean read GetGavetaAberta ;
end ;


implementation

uses SysUtils, ACBrUtil
  {$IFNDEF COMPILER6_UP} ,Windows {$ENDIF} ;

{ TACBrGAVSerialMenno }

constructor TACBrGAVSerialMenno.Create(AOwner: TComponent);
begin
  inherited Create( AOwner );

  fpModeloStr := 'Menno Serial' ;
  fpAberturaIntervalo := 5000 ;
end;

procedure TACBrGAVSerialMenno.Ativar;
begin
  if not fpDevice.IsSerialPort  then
     raise Exception.Create(ACBrStr('Esse modelo de Gaveta requer'+#10+
                            'Porta Serial: (COM1, COM2, COM3, ...)'));

  inherited Ativar ; { Abre porta serial }

  try
     fpDevice.Serial.DTR := true ;
     fpDevice.Serial.RTS := true ;
     CalculaProximaAbertura ;
  except
     Desativar ;
     raise
  end ;
end;

procedure TACBrGAVSerialMenno.AbreGaveta;
begin
  Inherited AbreGaveta ;

  fpDevice.Serial.RTS := false ;
  Sleep(200) ;
  fpDevice.Serial.DTR := true ;
  fpDevice.Serial.RTS := true ;
  
  CalculaProximaAbertura ;
end;

function TACBrGAVSerialMenno.GetGavetaAberta: Boolean;
begin
  fpDevice.Serial.DTR := False;
  Sleep(300);
  Result := not fpDevice.Serial.DSR;
  fpDevice.Serial.DTR := true ;
end;

end.

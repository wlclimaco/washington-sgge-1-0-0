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

unit ACBrGAVImpressoraComum;

interface
uses ACBrGAVClass, 
     Classes ;

type
TACBrGAVImpressoraComum = class( TACBrGAVClass )
  private
    fsComando : String ;
    
  protected

  public
    constructor Create(AOwner: TComponent);

    procedure Ativar ; override ;
    Procedure AbreGaveta  ; override ;
end ;


implementation
uses ACBrDevice, ACBrUtil,
     SysUtils;

{ TACBrGAVImpressoraComum }

constructor TACBrGAVImpressoraComum.Create(AOwner: TComponent);
begin
  inherited Create( AOwner );

  fpDevice.HandShake := hsRTS_CTS ;
  fpModeloStr := 'Conectada a Imp.Comum' ;
  fsComando   := '' ;
end;

procedure TACBrGAVImpressoraComum.Ativar;
begin
  if fpDevice.Porta = '' then
     raise Exception.Create(ACBrStr('Para usar Gaveta "gavImpressoraComum" deve ser'+
                            ' definida uma Porta Paralela ou Serial'));

  if StrComando = '' then
     raise Exception.Create(ACBrStr('Para usar Gaveta "gavImpressoraComum" deve ser'+
                            ' definido uma String de comando em StrComando'));

  inherited Ativar ; { Apenas ajusta fpAtivo }

  if fpDevice.IsSerialPort then
  begin
     fpDevice.Serial.RaiseExcept     := true ;
     fpDevice.Serial.DeadlockTimeout := 1000 ;
  end ;

  { Traduzindo StrComando }
  fsComando := TraduzComando( StrComando ) ;
end;

procedure TACBrGAVImpressoraComum.AbreGaveta;
begin
  Inherited AbreGaveta ;

  fpDevice.EnviaString( fsComando );

  CalculaProximaAbertura ;
end;

end.

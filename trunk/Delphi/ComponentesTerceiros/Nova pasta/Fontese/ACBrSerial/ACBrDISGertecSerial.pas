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
|* 30/09/2004: Daniel Simoes de Almeida
|*  - Primeira Versao ACBrDISGertecSerial
******************************************************************************}

{$I ACBr.inc}

unit ACBrDISGertecSerial;

interface
uses ACBrDISClass,
     Classes;

type

TACBrDISGertecSerial = class( TACBrDISClass )
  public 
    constructor Create(AOwner: TComponent);

    procedure LimparDisplay ; override ;

    procedure PosicionarCursor(Linha, Coluna: Integer ) ; override ;
    procedure Escrever( Texto : String ) ; override ;
end ;

implementation
Uses
  SysUtils,
  {$IFDEF COMPILER6_UP} DateUtils {$ELSE} ACBrD5{$ENDIF} ;

{ TACBrDISGertecSerial }

constructor TACBrDISGertecSerial.Create(AOwner: TComponent);
begin
  inherited Create( AOwner );

  fpModeloStr := 'Gertec Serial' ;
  LinhasCount := 2 ;
  Colunas     := 20 ;
end;


procedure TACBrDISGertecSerial.LimparDisplay;
begin
  fpDevice.EnviaString( #12 );
end;

procedure TACBrDISGertecSerial.PosicionarCursor(Linha, Coluna: Integer);
begin
  fpDevice.EnviaString( #9 + chr(Coluna) + chr(Linha) );
end;

procedure TACBrDISGertecSerial.Escrever(Texto: String);
begin
  fpDevice.EnviaString( Texto );
end;

end.

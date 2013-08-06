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
|* 24/08/2004: Daniel Simoes de Almeida
|*  - Primeira Versao ACBrCHQChronos
|* 01/06/2005: Daniel Simoes de Almeida
|*  - Corrigido BUG no envio da Data em Linux (data como dd-mm-aa)
|*    Bug reportado por Matheus Nogueira
******************************************************************************}

{$I ACBr.inc}

unit ACBrCHQChronos;

interface
uses ACBrCHQClass, 
     Classes ;

type TACBrCHQChronos = class( TACBrCHQClass )
  private

  protected
    function GetChequePronto: Boolean; Override ;

  public
    constructor Create(AOwner: TComponent);

    procedure Ativar ; override ;

    procedure ImprimirCheque ; Override ;
    Procedure TravarCheque ;  Override ;
    Procedure DestravarCheque ;  Override ;
end ;

implementation
Uses
  SysUtils,
  {$IFDEF COMPILER6_UP} DateUtils, {$ELSE} Windows,{$ENDIF}
   ACBrUtil, ACBrConsts ;

{ TACBrCHQChronos }

constructor TACBrCHQChronos.Create(AOwner: TComponent);
begin
  inherited Create( AOwner );

  fpModeloStr := 'Chronos' ;
end;

procedure TACBrCHQChronos.Ativar;
begin
  if fpDevice.Porta = ''  then
     raise Exception.Create(ACBrStr('Impressora de Cheques '+fpModeloStr+' requer'+#10+
                            'Porta Serial (COMn) ou Paralela (LPTn)'));

  inherited Ativar ; { Abre porta serial }
end;

procedure TACBrCHQChronos.TravarCheque;
begin
  fpDevice.EnviaString( #27 + #177 );
  Sleep(100);
end;

procedure TACBrCHQChronos.DestravarCheque;
begin
  fpDevice.EnviaString( #27 + #178 );
  Sleep(100);
end;

procedure TACBrCHQChronos.ImprimirCheque;
Var ValStr, DataStr : String ;
begin
  { Banco }
  fpDevice.EnviaString( #27 + #162 + fpBanco + #13 ) ;
  Sleep(100);
  { Valor }
  ValStr := IntToStrZero( Round( fpValor * 100), 11) ;
  ValStr := copy(ValStr,1,9)+','+copy(ValStr,10,2) ;
  fpDevice.EnviaString( #27 + #163 + ValStr + #13 ) ;
  Sleep(100);
  { Favorecido }
  fpDevice.EnviaString( #27 + #160 + Trim(fpFavorecido) + #13 ) ;
  Sleep(100);
  { Cidade }
  fpDevice.EnviaString( #27 + #161 + Trim(fpCidade) + #13 ) ;
  Sleep(100);
  { Data }
  DataStr := FormatDateTime('dd/mm/yy',fpData) ;
  DataStr := StringReplace(DataStr,DateSeparator,'/',[rfReplaceAll]) ;
  fpDevice.EnviaString( #27 + #164 + DataStr + #13 ) ;
  Sleep(100);

  fpDevice.EnviaString( #27 + #176 ) ;   // Imprimir...
  Sleep(100);
end;

function TACBrCHQChronos.GetChequePronto: Boolean;
Var nBit : Byte ;
begin
  Result := true ;

  if not fpDevice.IsSerialPort then
     exit ;

  fpDevice.EnviaString( #0 ) ;   // Pede Status
  nBit := fpDevice.Serial.RecvByte( 200 ) ;

  Result := not TestBit( nBit , 2 ) ;
end;

end.

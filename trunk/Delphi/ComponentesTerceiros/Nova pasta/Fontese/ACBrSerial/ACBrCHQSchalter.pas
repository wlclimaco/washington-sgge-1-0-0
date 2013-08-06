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
|*  - Primeira Versao ACBrCHQSchalter
|* 12/11/2005: Daniel Simoes de Almeida
|*  - Corrigido m�todos ImprimeLinha e ImprimeVerso
******************************************************************************}

{$I ACBr.inc}

unit ACBrCHQSchalter;

interface
uses ACBrCHQClass,  
     Classes ;

type TACBrCHQSchalter = class( TACBrCHQClass )
  private

  protected
    function GetChequePronto: Boolean; Override ;

  public
    constructor Create(AOwner: TComponent);

    procedure Ativar ; override ;

    procedure ImprimirCheque ; Override ;
    procedure ImprimirLinha( AString : AnsiString ) ; Override ;
    procedure ImprimirVerso( AStringList : TStrings ) ; Override ;
end ;

implementation
Uses ACBrUtil, ACBrConsts,
     SysUtils,
   {$IFDEF COMPILER6_UP} DateUtils, {$ELSE} Windows,{$ENDIF}
     ACBrDevice;

{ TACBrCHQSchalter }

constructor TACBrCHQSchalter.Create(AOwner: TComponent);
begin
  inherited Create( AOwner );

  fpDevice.Stop := s2 ;
  fpModeloStr := 'Schalter' ;
end;

procedure TACBrCHQSchalter.Ativar;
begin
  if fpDevice.Porta = ''  then
     raise Exception.Create(ACBrStr('Impressora de Cheques '+fpModeloStr+' requer'+#10+
                            'Porta Serial (COMn) ou Paralela (LPTn)'));

  inherited Ativar ; { Abre porta serial }
end;

procedure TACBrCHQSchalter.ImprimirCheque;
Var ValStr, DataStr : String ;
begin
  { Banco }
  fpDevice.EnviaString( #27 + 'B' + fpBanco ) ;
  Sleep(100);
  { Favorecido }
  fpDevice.EnviaString( #27 + 'F' + Trim(fpFavorecido) + '$' ) ;
  Sleep(100);
  { Cidade }
  fpDevice.EnviaString( #27 + 'C' + Trim(fpCidade) + '$' ) ;
  Sleep(100);
  { Data }
  DataStr := FormatDateTime('ddmmyy',fpData) ;
  fpDevice.EnviaString( #27 + 'D' + DataStr ) ;
  Sleep(100);
  { Valor }
  ValStr := IntToStrZero( Round( fpValor * 100), 14) ;
  fpDevice.EnviaString( #27 + 'V' + ValStr ) ;
  Sleep(100);
  { Envio do comando Valor Inicia a Impress�o }
  
end;

function TACBrCHQSchalter.GetChequePronto: Boolean;
begin
  Result := fpDevice.EmLinha ;
end;

procedure TACBrCHQSchalter.ImprimirLinha(AString: AnsiString);
begin
  if Trim(AString) <> '' then
     AString := StringOfChar(' ',10) + AString
  else
     AString := Trim(AString) ;

  fpDevice.EnviaString( AString + #10 );  { Adciona LF }
  Sleep(100);
end;

procedure TACBrCHQSchalter.ImprimirVerso(AStringList: TStrings);
Var A : Integer ;
begin
  For A := 0 to AStringList.Count - 1 do
     ImprimirLinha( StringOfChar(' ',10) + TiraAcentos( AStringList[A] ) );

  fpDevice.EnviaString( #12 ) { Envia FF } ;
  Sleep(100);
end;

end.

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
|* Agradecimentos �:
|* Everton H. Cardoso  -  Bematech S/A - Testes deste Modulo  
******************************************************************************}

{******************************************************************************
|* Historico
|*
|* 24/08/2004: Daniel Simoes de Almeida
|*  - Primeira Versao ACBrCHQBematech
******************************************************************************}

{$I ACBr.inc}

unit ACBrCHQBematech;

interface
uses ACBrCHQClass,  
     Classes
     {$IFNDEF COMPILER6_UP} ,Windows {$ENDIF} ;

type TACBrCHQBematech = class( TACBrCHQClass )
  private

  protected

  public
    constructor Create(AOwner: TComponent);

    procedure Ativar ; override ;

    procedure ImprimirCheque ; Override ;
    Procedure TravarCheque ;  Override ;
    Procedure DestravarCheque ;  Override ;
end ;

implementation
Uses ACBrUtil, ACBrConsts,
     SysUtils
    {$IFDEF COMPILER6_UP}, DateUtils {$ENDIF} ;

{ TACBrCHQBematech }

constructor TACBrCHQBematech.Create(AOwner: TComponent);
begin
  inherited Create( AOwner );

  fpDevice.HardFlow := true ;
  fpModeloStr := 'Bematech' ;
end;

procedure TACBrCHQBematech.Ativar;
begin
  if fpDevice.Porta = ''  then
     raise Exception.Create(ACBrStr('Impressora de Cheques '+fpModeloStr+' requer'+#10+
                            'Porta Serial (COMn) ou Paralela (LPTn)'));

  fpDevice.HardFlow := true ;  { Ativar RTS/CTS } 
  inherited Ativar ; { Abre porta serial }
end;

procedure TACBrCHQBematech.TravarCheque;
begin
  fpDevice.EnviaString( #27 + #177 );
//  fpDevice.EnviaString( #27 + #119 + #1 ) ;
  Sleep(100);
end;

procedure TACBrCHQBematech.DestravarCheque;
begin
  fpDevice.EnviaString( #27 + #176 );
//  fpDevice.EnviaString( #27 + #119 + #0 ) ;
  Sleep(100);
end;

procedure TACBrCHQBematech.ImprimirCheque;
Var ValStr, DataStr : String ;
begin
  if not fpDevice.EmLinha( 3 ) then  { Impressora est� em-linha ? }
    raise Exception.Create(ACBrStr('A impressora de Cheques '+fpModeloStr+
                           ' n�o est� pronta.')) ;

  TravarCheque ;

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

  DestravarCheque ;
end;

end.
 
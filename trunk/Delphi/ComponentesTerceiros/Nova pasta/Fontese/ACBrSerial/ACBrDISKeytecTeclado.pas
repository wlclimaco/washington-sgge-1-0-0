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
|* 02/06/2006: Fabio Farias
|*  - Primeira Versao ACBrDISKeytecTeclado
******************************************************************************}

{$I ACBr.inc}

unit ACBrDISKeytecTeclado;

interface
uses ACBrDISClass,
     Classes;

{ Nota: - A comunica��o com a Porta AT n�o � t�o r�pida quando a Porta Serial,
          por isso, evite o uso excessivo de textos "animados"
        - A fun�ao TxKeyboard() funciona normalmente em Win9x,
        - XP /NT /2000, deve-se usar uma DLL que permita acesso direto
          a porta AT  ( inpout32.dll )  http://www.logix4u.net/inpout32.htm
        - Linux: � necess�rio ser ROOT para acessar /dev/port
          (use: su  ou  chmod u+s SeuPrograma ) }
type
TACBrDISKeytecTeclado = class( TACBrDISClass )
  public
    constructor Create(AOwner: TComponent);

    procedure Ativar ; override ;

    procedure LimparDisplay ; override ;
    procedure PosicionarCursor(Linha, Coluna: Integer ) ; override ;
    procedure Escrever( Texto : String ) ; override ;
end ;

implementation
Uses ACBrUtil,
     SysUtils,
     {$IFDEF COMPILER6_UP} DateUtils {$ELSE} ACBrD5, Math, Windows{$ENDIF} ;

{ TACBrDISKeytecTeclado}

constructor TACBrDISKeytecTeclado.Create(AOwner: TComponent);
begin
  inherited Create( AOwner );

  fpModeloStr := 'Keytec Teclado' ;
  LinhasCount := 2 ;
  Colunas     := 40 ;
end;

procedure TACBrDISKeytecTeclado.LimparDisplay;
begin
  TxKeyboard( 160 );
  TxKeyboard( 1 );
end;

procedure TACBrDISKeytecTeclado.PosicionarCursor(Linha, Coluna: Integer);
Var Pos : Byte;
begin
  {
   Tabela de enderecamento do Visor
   Linha 1 come�a na coluna endere�o = Hex 80  ( 128 decimal )
   Linha 2 come�a na coluna endere�o = Hex C0  ( 192 decimal )
  }
  if Linha = 1 then
     Pos := 127 + Coluna
  else
     Pos := 191 + Coluna;

  TxKeyboard(160);
  TxKeyboard(Pos);
end;

procedure TACBrDISKeytecTeclado.Escrever(Texto: String);
Var A : Integer ;
begin
  TxKeyboard( 8 );
  for A := 1 to Length( Texto ) do
     TxKeyboard( ord(Texto[A]) ) ;      // Envia um Byte por vez...
  TxKeyboard( 9 );
end;

procedure TACBrDISKeytecTeclado.Ativar;
begin
  { Nao precisa de inicializa�ao }
  fpAtivo := true ;
end;

end.

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
|*  - Primeira Versao ACBrDISGertecTeclado
|* 05/11/2004: Daniel Simoes de Almeida
|*  - Adcionado suporte a Linux
******************************************************************************}

{$I ACBr.inc}

unit ACBrDISGertecTeclado;

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

{ TACBrDISGertecTeclado }

TACBrDISGertecTeclado = class( TACBrDISClass )
  public
    constructor Create(AOwner: TComponent);

    procedure Ativar ; override ;
    
    procedure LimparDisplay ; override ;
    procedure LimparLinha( Linha: Integer ) ; override ;

    procedure PosicionarCursor(Linha, Coluna: Integer ) ; override ;
    procedure Escrever( Texto : String ) ; override ;
end ;

implementation
Uses ACBrUtil,
     SysUtils,
     {$IFDEF COMPILER6_UP} DateUtils {$ELSE} ACBrD5, Windows{$ENDIF} ;

{ TACBrDISGertecTeclado}

constructor TACBrDISGertecTeclado.Create(AOwner: TComponent);
begin
  inherited Create( AOwner );

  fpModeloStr := 'Gertec Teclado' ;
  LinhasCount := 2 ;
  Colunas     := 40 ;
  fpIntervaloEnvioBytes := 0;
end;


procedure TACBrDISGertecTeclado.LimparDisplay;
begin
{ TxKeyboard( 231 );  // Liga display
  TxKeyboard( 12 );   // Limpa Display
  TxKeyboard( 232 );  // DesLiga display }

  TxKeyboard( 212 );  // Limpa Display direto 
end;

procedure TACBrDISGertecTeclado.LimparLinha(Linha: Integer);
begin
  if Linha = 1 then
   begin
     TxKeyboard( 219 ) ;   // DB ou 219 Apaga linha 1
//   TxKeyboard( 217 ) ;   // D9 ou 217 -  Teclados Antigos ??
   end
  else
   begin
     TxKeyboard( 221 ) ;   // DD ou 221 = Apaga linha 2
//   TxKeyboard( 218 ) ;   // DA ou 218 - Teclados Antigos ??
   end ;
end;

procedure TACBrDISGertecTeclado.PosicionarCursor(Linha, Coluna: Integer);
begin
{ TxKeyboard( 231 );  // Liga display
  TxKeyboard( 9 ) ;
  TxKeyboard( ColB );
  TxKeyboard( LinB ) ;
  TxKeyboard( 232 );  // DesLiga display }

  TxKeyboard( 214 ) ;   { posicionamento de cursor direto }
  TxKeyboard( Coluna );
  TxKeyboard( Linha ) ;
end;

procedure TACBrDISGertecTeclado.Escrever(Texto: String);
Var
  A : Integer ;
begin
  TxKeyboard( 231 );  // Liga display
  For A := 1 to Length( Texto ) do
     TxKeyboard( ord(Texto[A]) ) ;      // Envia um Byte por vez...
  TxKeyboard( 232 );  // DesLiga display
end;

procedure TACBrDISGertecTeclado.Ativar;
begin
  { Nao precisa de inicializa�ao }
  fpAtivo := true ;
end;

end.

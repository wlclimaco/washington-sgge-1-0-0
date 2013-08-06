{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2004   Gabriel Rodrigo Frones               }
{                                                                              }
{ Colaboradores nesse arquivo:            Daniel Sim�es de Almeida            }
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
|* 31/10/2005: Gabriel Rodrigo Frones
|*  - Primeira Versao ACBrTERWilbor
|
|* 11/05/2011: Marcelo Ferreira (Marcelo-sp)
|*  - Corre��o   - LeSerial  
|*  - Implemento - Fun��o LeBalanca
******************************************************************************}

{$I ACBr.inc}

Unit ACBrTERWilbor;

Interface

Uses ACBrTERClass,
     Classes;

Type
    TACBrTERWilbor = Class( TACBrTERClass )
      Public
        Constructor Create( AOwner: TComponent );

        Procedure LeBalanca(  Terminal : Word = 0  ); Override;
        Procedure LeSerial( MillisecTimeOut : Integer = 500 ); Override;
        Procedure EnviaString( Texto : String; Terminal : Word = 0 ); Override;
        Procedure EnviaRotacao( Texto : String; Linha : Word = 1; Terminal : Word = 0 ); Override;
        Procedure LimpaTela( Terminal : Word = 0 ); Override;
        Procedure PosicionaCursor( Linha, Coluna : Word; Terminal : Word = 0 ); Override;
        Procedure BackSpace( Terminal : Word = 0 ); Override;
    End;

Implementation

Uses ACBrTER, ACBrUtil, Math, 
     {$IFDEF COMPILER6_UP} DateUtils, StrUtils {$ELSE} ACBrD5, Windows{$ENDIF},
     SysUtils ;

{ TACBrTERWilbor }

Constructor TACBrTERWilbor.Create( AOwner: TComponent );
Begin
    Inherited Create( AOwner );

    fpModeloStr := 'Wilbor';
End;

Procedure TACBrTERWilbor.LeBalanca(  Terminal : Word = 0 );
  var PesoOk : string ;
Begin
    if TACBrTER( fpOwner ).Comutadora Then
     begin
       fpDevice.EnviaString( 'S'+Format('%2.2d', [Terminal]) + #5  );
       PesoOk := fpDevice.Serial.RecvPacket( 500 );
     end
    else
     begin
       // N�o implementado
     end ;
End;

Procedure TACBrTERWilbor.LeSerial( MillisecTimeOut: Integer );
Var Packet : String;
Begin
  Try
     Packet := fpDevice.Serial.RecvPacket( MillisecTimeOut );
     If TACBrTER( fpOwner ).Comutadora Then
      begin   //Possui Comutadora gerenciando v�rios Terminais?
        While Length( Packet ) >= 3 Do
        begin
           TACBrTER( fpOwner ).DoRecebeChar( StrToIntDef( Copy( Packet, 1, 2 ), 0 ), Packet[3] );
           Delete( Packet, 1, 3 );
        end;
      end
     else
      begin
        while Length( Packet ) > 1 Do
        begin
           TACBrTER( fpOwner ).DoRecebeChar( 0, Packet[1] );
           Delete( Packet, 1, 1 );
        end;
      end;
  Except
    { String n�o foi recebida (TimeOut) }
  End;
End;

Procedure TACBrTERWilbor.EnviaString( Texto : String; Terminal : Word = 0 );
Var I : Integer;
Begin
    If TACBrTER( fpOwner ).Comutadora Then
     begin
       For I := 1 To Length( Texto ) Do
       begin
          fpDevice.EnviaString( 'D' + FormatFloat( '00', Terminal ) + Texto[I] + StringOfChar( ' ', 4 ) );
          fpDevice.EnviaString( 'A' + FormatFloat( '00', Terminal ) + StringOfChar( ' ', 12 ) ); //Comando fora do protocolo. Serve para, conforme o Manual, preencher o tempo de espera que o Terminal precisa para Processar esse comando antes de receber o pr�ximo. Veja Apendice A (pag. 13) no Manual da Comutadora.
       end;
     end
    else
       fpDevice.EnviaString( Texto );
End;

Procedure TACBrTERWilbor.EnviaRotacao( Texto : String; Linha : Word = 1; Terminal : Word = 0 );
Begin
    Dec( Linha );
    Linha := min( max(Linha, 0 ), 1) ;

    If Length( Texto ) <= 40 Then
        Texto := StringOfChar( ' ', 40 ) + Texto;

    TACBrTER( fpOwner ).ListaRotacao.Add( IntToStr( Linha ) + FormatFloat( '00', Terminal ) + Texto );
End;

Procedure TACBrTERWilbor.LimpaTela( Terminal : Word = 0 );
Begin
    If TACBrTER( fpOwner ).Comutadora Then
     begin
       fpDevice.EnviaString( 'L' + FormatFloat( '00', Terminal ) + StringOfChar( ' ', 12 ) );
       fpDevice.EnviaString( 'A' + FormatFloat( '00', Terminal ) + StringOfChar( ' ', 12 ) ); //Comando fora do protocolo. Serve para, conforme o Manual, preencher o tempo de espera que o Terminal precisa para Processar esse comando antes de receber o pr�ximo. Veja Apendice A (pag. 13) no Manual da Comutadora.
     end
    else
       fpDevice.EnviaString( #27'[H'#27'[J' );
End;

Procedure TACBrTERWilbor.PosicionaCursor( Linha, Coluna : Word; Terminal : Word = 0 );
Begin
    {No MicroTerminal, o �ndice � zero}
    Dec( Linha );
    Dec( Coluna );

    {Limitando a Linha e a Coluna}
    Linha  := min( max(Linha,  0 ),  1) ;
    Coluna := min( max(Coluna, 0 ), 39) ;

    If TACBrTER( fpOwner ).Comutadora Then
     begin
       fpDevice.EnviaString( 'C' + FormatFloat( '00', Terminal ) + IntToStr( Linha ) + FormatFloat( '00', Coluna ) + StringOfChar( ' ', 12 ) );
       fpDevice.EnviaString( 'A' + FormatFloat( '00', Terminal ) + StringOfChar( ' ', 12 ) ); //Comando fora do protocolo. Serve para, conforme o Manual, preencher o tempo de espera que o Terminal precisa para Processar esse comando antes de receber o pr�ximo. Veja Apendice A (pag. 13) no Manual da Comutadora.
     end
    else
       fpDevice.EnviaString( #27'[' + IntToStr( Linha ) + ';' + IntToStr( Coluna ) + 'H' );
End;

Procedure TACBrTERWilbor.BackSpace( Terminal : Word = 0 );
Begin
    If TACBrTER( fpOwner ).Comutadora Then
     begin
       fpDevice.EnviaString( 'O' + FormatFloat( '00', Terminal ) + Chr(16) + StringOfChar( ' ', 12 ) );
       fpDevice.EnviaString( 'A' + FormatFloat( '00', Terminal ) + StringOfChar( ' ', 12 ) ); //Comando fora do protocolo. Serve para, conforme o Manual, preencher o tempo de espera que o Terminal precisa para Processar esse comando antes de receber o pr�ximo. Veja Apendice A (pag. 13) no Manual da Comutadora.
       EnviaString( ' ', Terminal );
       fpDevice.EnviaString( 'O' + FormatFloat( '00', Terminal ) + Chr(16) + StringOfChar( ' ', 12 ) );
       fpDevice.EnviaString( 'A' + FormatFloat( '00', Terminal ) + StringOfChar( ' ', 12 ) ); //Comando fora do protocolo. Serve para, conforme o Manual, preencher o tempo de espera que o Terminal precisa para Processar esse comando antes de receber o pr�ximo. Veja Apendice A (pag. 13) no Manual da Comutadora.
     end
    else
       fpDevice.EnviaString( #8'[K' );
End;

End.

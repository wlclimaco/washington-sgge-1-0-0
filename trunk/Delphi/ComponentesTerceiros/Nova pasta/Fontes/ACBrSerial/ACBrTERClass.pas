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
|* 25/10/2005: Gabriel Rodrigo Frones
|*  - Primeira Versao ACBrTERClass
|
|* 11/05/2011: Marcelo Ferreira (Marcelo-sp)
|*  - Implemento - Fun��o LeBalan�a
******************************************************************************}

{$I ACBr.inc}

Unit ACBrTERClass;

Interface

Uses ACBrDevice,    {Units da ACBr}
     Classes,
     {$IFDEF COMPILER6_UP}Types{$ELSE}Windows{$ENDIF};

Type

{ Classe generica de TERMINAL, nao implementa nenhum modelo especifico, apenas
  declara a Classe. NAO DEVE SER INSTANCIADA. Usada apenas como base para
  as demais Classes de TERMINAL como por exemplo a classe TACBrTERWilbor }

    TACBrTERClass = Class
        Private
            Procedure SetAtivo( Const Value : Boolean );
        Protected
            fpOwner : TComponent;
            fpDevice : TACBrDevice;
            fpAtivo : Boolean;
            fpModeloStr : String;
        Public
            Constructor Create(AOwner: TComponent);
            Destructor Destroy; Override;

            Property Ativo : Boolean Read fpAtivo Write SetAtivo;
            Procedure Ativar; Virtual;
            Procedure Desativar; Virtual;

            Procedure LeBalanca( Terminal : Word = 0  ); Virtual;  
            Procedure LeSerial( MillisecTimeOut : Integer = 500 ); Virtual;
            Procedure EnviaString( Texto : String; Terminal : Word = 0 ); Virtual;
            Procedure EnviaRotacao( Texto : String; Linha : Word = 0; Terminal : Word = 0 ); Virtual;
            Procedure LimpaTela( Terminal : Word = 0 ); Virtual;
            Procedure PosicionaCursor( Linha, Coluna : Word; Terminal : Word = 0 ); Virtual;
            Procedure BackSpace( Terminal : Word = 0 ); Virtual;
            Property ModeloStr : String Read fpModeloStr;
        End;

Implementation

Uses ACBrTER, ACBrUtil, SysUtils;

{ TACBrTERClass }

Constructor TACBrTERClass.Create( AOwner: TComponent );
Begin
    If Not ( AOwner Is TACBrTER ) Then
        Raise Exception.Create( ACBrStr('Essa Classe deve ser instanciada por TACBrTER') );

  { Criando ponteiro interno para as Propriedade SERIAL de ACBrTER,
    para permitir as Classes Filhas o acesso a essas propriedades do Componente }

    fpOwner := AOwner;

    fpDevice    := ( AOwner As TACBrTER ).Device;
    fpDevice.SetDefaultValues;

    fpAtivo     := False;
    fpModeloStr := 'N�o Definida';
End;

Destructor TACBrTERClass.Destroy;
Begin
    fpDevice := Nil; { Apenas remove referencia (ponteiros internos) }

    Inherited Destroy;
End;

Procedure TACBrTERClass.SetAtivo( Const Value: Boolean );
Begin
    If Value Then
        Ativar
    Else
        Desativar;
End;

Procedure TACBrTERClass.Ativar;
Begin
    If fpAtivo Then
        Exit;

    If fpDevice.Porta <> '' Then
        fpDevice.Ativar;

    fpAtivo := True;
End;

Procedure TACBrTERClass.Desativar;
Begin
    If Not fpAtivo Then
        Exit;

    If fpDevice.Porta <> '' Then
        fpDevice.Desativar;

    fpAtivo := False;
End;

Procedure TACBrTERClass.LeSerial( MillisecTimeOut : Integer );
Begin
    { Deve ser implementada na Classe Filha }
    Raise Exception.Create(ACBrStr('Procedure LeSerial n�o implementada em: ') + ModeloStr );
End;

Procedure TACBrTERClass.LimpaTela( Terminal : Word = 0 );
Begin
    { Deve ser implementada na Classe Filha }
    Raise Exception.Create(ACBrStr('Procedure LimpaTela n�o implementada em: ') + ModeloStr );
End;

Procedure TACBrTERClass.PosicionaCursor( Linha, Coluna : Word; Terminal : Word = 0 );
Begin
    { Deve ser implementada na Classe Filha }
    Raise Exception.Create(ACBrStr('Procedure PosicionaCursor n�o implementada em: ') + ModeloStr );
End;

Procedure TACBrTERClass.BackSpace( Terminal : Word = 0 );
Begin
    { Deve ser implementada na Classe Filha }
    Raise Exception.Create(ACBrStr('Procedure BackSpace n�o implementada em: ') + ModeloStr );
End;

Procedure TACBrTERClass.EnviaString( Texto : String; Terminal : Word = 0 );
Begin
    { Deve ser implementada na Classe Filha }
    Raise Exception.Create(ACBrStr('Procedure EnviaString n�o implementada em: ') + ModeloStr );
End;

Procedure TACBrTERClass.EnviaRotacao( Texto : String; Linha : Word = 0; Terminal : Word = 0 );
Begin
    { Deve ser implementada na Classe Filha }
    Raise Exception.Create(ACBrStr('Procedure EnviaRotacao n�o implementada em: ') + ModeloStr );
End;


Procedure TACBrTERClass.LeBalanca( Terminal : Word = 0  );
Begin
    { Deve ser implementada na Classe Filha }
    Raise Exception.Create(ACBrStr('Procedure LeBalanca n�o implementada em: ') + ModeloStr );
End;

End.

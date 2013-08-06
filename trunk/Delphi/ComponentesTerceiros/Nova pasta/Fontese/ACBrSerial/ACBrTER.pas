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
{ Esse arquivo usa a classe  SynaSer   Copyright (c)2001-2003, Lukas Gebauer   }
{  Project : Ararat Synapse     (Found at URL: http://www.ararat.cz/synapse/)  }
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
|*  - Primeira Versao ACBrTER
|
|* 11/05/2011: Marcelo Ferreira (Marcelo-sp)
|*  - Implemento - Fun��o LeBalanca
******************************************************************************}

{$I ACBr.inc}

Unit ACBrTER;

Interface

Uses ACBrBase, ACBrDevice, ACBrTERClass,  {Units da ACBr}
     SysUtils,
     {$IFDEF VisualCLX} QExtCtrls {$ELSE} ExtCtrls {$ENDIF},
     {$IFDEF COMPILER6_UP} Types {$ELSE} Windows {$ENDIF},
      Classes;

Type
    TACBrTERModelo = ( terNenhum, terWilbor );
    TACBrTERRecebeChar = Procedure( Terminal : Word; Char : Char ) Of Object;

    TACBrRotacao = Class( TPersistent )
        Private
            fsOwner : TComponent;
            fsAtivo : Boolean;
            fsPasso : Cardinal;

            Procedure SetAtivo( Const Value : Boolean );
            Procedure SetPasso( Const Value : Cardinal );
        Public
            Constructor Create( AOwner : TComponent );
            Procedure Ativar;
            Procedure Desativar;
        Published
            Property Ativo : Boolean Read fsAtivo Write SetAtivo;
            Property Passo : Cardinal Read fsPasso Write SetPasso;
        End;

    { Componente ACBrTER }
    TACBrTER = Class( TACBrComponent )
        Private
            fsDevice : TACBrDevice; { SubComponente ACBrDevice }
            fsTimer : TTimer;

            { Propriedades do Componente ACBrTER }
            fsAtivo : Boolean;
            fsComutadora : Boolean;
            fsIntervalo : Integer;
            fsModelo : TACBrTERModelo;
            fsTER : TACBrTERClass;
            fsOnRecebeChar : TACBrTERRecebeChar;

            { Propriedades para Controle da Rota��o do Texto nos Terminais}
            fsRotacao : TACBrRotacao;
            fsTRotacao : TTimer;
            fsListaRotacao : TStringList; //Cada Linha � Definida na forma [L][TT][...]
                                          //Onde [L] � o n�mero da Linha do Terminal ['1', '2'];
                                          //[TT] � o n�mero do Terminal ['00'..'99'];
                                          //[...] � a String que ainda falta para girar.

            Procedure SetModelo( Const Value : TACBrTERModelo );
            Procedure SetPorta( Const Value : String );
            Procedure SetAtivo( Const Value : Boolean );
            Procedure LeSerial( Sender : TObject );
            Procedure Passo( Sender : TObject );

            Function GetPorta : String;
            Function GetModeloStrClass : String;
            Procedure SetIntervalo( Const Value : Integer );
        Public
            Constructor Create( AOwner: TComponent ); Override;
            Destructor Destroy; Override;

            Procedure Ativar;
            Procedure Desativar;
            Procedure DoRecebeChar( Terminal : Word; Char : Char );

            {M�todos do fsTER}

            Procedure LeBalanca( Terminal : Word = 0  );

            Procedure EnviaString( Texto : String; Terminal : Word = 0 );
            Procedure EnviaRotacao( Texto : String; Linha : Word = 1; Terminal : Word = 0 );
            Procedure LimpaTela( Terminal : Word = 0 );
            Procedure PosicionaCursor( Linha, Coluna : Word; Terminal : Word = 0 );
            Procedure BackSpace( Terminal : Word = 0 );

            Property ListaRotacao : TStringList Read fsListaRotacao;
            Property Ativo : Boolean Read fsAtivo Write SetAtivo;
            Property Ter : TACBrTERClass Read fsTER;
            Property ModeloStr : String Read GetModeloStrClass;
        Published
            Property Modelo : TACBrTERModelo Read fsModelo Write SetModelo Default terNenhum;
            Property Porta : String Read GetPorta Write SetPorta;
            Property Intervalo : Integer Read fsIntervalo Write SetIntervalo Default 200;
            Property Comutadora : Boolean Read fsComutadora Write fsComutadora Default False; //Possui Comutadora gerenciando v�rios Terminais?
            Property Rotacao : TACBrRotacao Read fsRotacao;
            { Instancia do Componente ACBrDevice, ser� passada para fsTER.create }
            Property Device : TACBrDevice Read fsDevice;
            Property OnRecebeChar : TACBrTERRecebeChar Read fsOnRecebeChar Write fsOnRecebeChar;
        End;

Implementation

Uses ACBrUtil, ACBrTERWilbor,
     {$IFDEF COMPILER6_UP} StrUtils {$ELSE} ACBrD5{$ENDIF},
     Math;

{ TACBrTER }
Constructor TACBrTER.Create( AOwner: TComponent );
Begin
    Inherited Create( AOwner );

    fsAtivo := False;
    fsModelo := terNenhum;
    fsIntervalo := 200;

    {Objetosm para controle da Rota��o do Texto nos Terminais}
    fsListaRotacao := TStringList.Create;
    fsTRotacao := TTimer.Create( Self );
    fsTRotacao.OnTimer := Passo;
    fsRotacao := TACBrRotacao.Create( Self );
    fsRotacao.Ativo := False;
    fsRotacao.Passo := 500;

    { Instanciando SubComponente TACBrDevice }
    fsDevice := TACBrDevice.Create( Self ) ;  { O dono � o proprio componente }
    fsDevice.Name := 'ACBrDevice';      { Apenas para aparecer no Object Inspector}
    {$IFDEF COMPILER6_UP}
    fsDevice.SetSubComponent( True );{ para gravar no DFM/XFM }
    {$ENDIF}
    fsDevice.Porta := 'COM1';
    fsDevice.Serial.DeadlockTimeout := 1000;

    { Timer para monitorar a recep��o de dados }
    fsTimer := TTimer.Create( Self );
    fsTimer.OnTimer := LeSerial;

    { Instanciando fsTER com modelo Generico (TACBrTERClass) }
    fsTER := TACBrTERClass.Create( Self );
End;

Destructor TACBrTER.Destroy;
Begin
    Desativar;

    fsTimer.Enabled := False;
    fsTimer.Free;

    fsRotacao.Desativar;
    fsRotacao.Free;
    fsTRotacao.Free;
    fsListaRotacao.Free;

    If Assigned( fsTER ) Then
        FreeAndNil( fsTER );

    FreeAndNil( fsDevice );

    Inherited Destroy;
End;

Procedure TACBrTER.SetModelo( Const Value: TACBrTERModelo );
Begin
    If fsModelo = Value Then
        Exit;

    If fsAtivo Then
        Raise Exception.Create( ACBrStr('N�o � poss�vel mudar o Modelo com ACBrTER Ativo') );

    FreeAndNil( fsTER );

    { Instanciando uma nova classe de acordo com fsModelo }
    Case Value Of
        terWilbor : fsTER := TACBrTERWilbor.Create( Self );
    Else
        fsTER := TACBrTERClass.Create( Self );
    End;

    fsModelo := Value;
End;

Procedure TACBrTER.SetAtivo( Const Value: Boolean );
Begin
    If Value Then
        Ativar
    Else
        Desativar;
End;

Procedure TACBrTER.Ativar;
Begin
    If fsAtivo Then
        Exit;

    fsTER.Ativar;
    fsAtivo := True;
    Intervalo := fsIntervalo; { isso apenas verifica se precisa ligar o timer }
End;

Procedure TACBrTER.Desativar;
Begin
    If Not fsAtivo Then
        Exit;

    fsTimer.Enabled := False;
    fsTER.Desativar;
    fsAtivo := False;
End;


Function TACBrTER.GetModeloStrClass : String;
Begin
    Result := ACBrStr(fsTER.ModeloStr)
End;

Function TACBrTER.GetPorta : String;
Begin
    Result := fsDevice.Porta;
End;

Procedure TACBrTER.SetPorta( Const Value : String );
Begin
    fsDevice.Porta := Value;
End;

Procedure TACBrTer.DoRecebeChar( Terminal : Word; Char : Char );
Begin
    If Assigned( fsOnRecebeChar ) Then
       fsOnRecebeChar( Terminal, Char );
End;

Procedure TACBrTER.LeSerial(Sender: TObject);  { Chamado pelo Timer interno }
Begin
    fsTimer.Enabled := False;  { Desliga o Timer para evitar chamadas Recursivas }

    Try
        { Est� ativo ? Tem dados esperando na porta Serial ? }
        If fsDevice.Serial.InstanceActive Then Begin
            If ( fsDevice.Serial.WaitingDataEx > 0 ) Then
                fsTER.LeSerial( 500 );
        End;
    Finally
        fsTimer.Enabled := True;
    End;
End;

Procedure TACBrTER.Passo( Sender: TObject );  { Chamado pelo Timer de Rota��o }
Var I : Integer;
    StringLinha : String;
    Terminal : Word;
    Linha : Byte;
Begin
    fsTRotacao.Enabled := False;  { Desliga o Timer para evitar chamadas Recursivas }

    Try
        For I := fsListaRotacao.Count - 1 DownTo 0 Do Begin // Fazemos invertido pois podem haver exclus�es de itens durante o loop.
            StringLinha := fsListaRotacao[I];
            Linha := StrToIntDef( StringLinha[1], 0 );
            If Linha In [1, 2] Then Begin
                Terminal := IfThen( fsComutadora, StrToIntDef( Copy( StringLinha, 2, 2 ), 0 ), 0 );
                fsTER.PosicionaCursor( Linha, 1, Terminal );
                fsTER.EnviaString( Copy( StringLinha, 4, 40 ), Terminal );
                If Length( StringLinha ) <= 43 Then
                    fsListaRotacao.Delete( I )
                Else Begin
                    Delete( StringLinha, 4, 1 );
                    fsListaRotacao[I] := StringLinha;
                End;
            End
            Else
                fsListaRotacao.Delete( I );
        End;
    Finally
//        If fsListaRotacao.Count = 0 Then
            fsTRotacao.Enabled := True;
    End;
End;

Procedure TACBrTER.SetIntervalo( Const Value: Integer );
Begin
    fsIntervalo := Value;
    fsTimer.Interval := Value;
    fsTimer.Enabled := ( ( Value <> 0 ) And Ativo );
End;

{M�todos do fsTER}
Procedure TACBrTER.LeBalanca( Terminal : Word = 0 );
Begin
    fsTER.LeBalanca(  Terminal  );
End;                                        

Procedure TACBrTER.EnviaString( Texto : String; Terminal : Word = 0 );
Begin
    fsTER.EnviaString( Texto, Terminal );
End;

Procedure TACBrTER.EnviaRotacao( Texto : String; Linha : Word = 1; Terminal : Word = 0 );
Begin
    fsTER.EnviaRotacao( Texto, Linha, Terminal );
End;

Procedure TACBrTER.LimpaTela( Terminal : Word = 0 );
Begin
    fsTER.LimpaTela( Terminal );
End;

Procedure TACBrTER.PosicionaCursor( Linha, Coluna : Word; Terminal : Word = 0 );
Begin
    fsTER.PosicionaCursor( Linha, Coluna, Terminal );
End;

Procedure TACBrTER.BackSpace( Terminal : Word = 0 );
Begin
    fsTER.BackSpace( Terminal );
End;

{TACBrRotacao}
Constructor TACBrRotacao.Create( AOwner : TComponent );
Begin
    fsOwner := AOwner;
End;

Procedure TACBrRotacao.Ativar;
Begin
    TACBrTER( fsOwner ).fsTRotacao.Enabled := fsPasso > 0;
    fsAtivo := True;
End;

Procedure TACBrRotacao.Desativar;
Begin
    TACBrTER( fsOwner ).fsTRotacao.Enabled := False;
    fsAtivo := False;
End;

Procedure TACBrRotacao.SetAtivo( Const Value : Boolean );
Begin
    If Value Then
        Ativar
    Else
        Desativar;
End;

Procedure TACBrRotacao.SetPasso( Const Value : Cardinal );
Begin
    TACBrTER( fsOwner ).fsTRotacao.Interval := Value;
    fsPasso := Value;
    SetAtivo( fsAtivo );
End;

End.



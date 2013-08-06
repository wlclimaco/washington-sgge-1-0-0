{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2004 Daniel Simoes de Almeida               }
{                                                                              }
{ Colaboradores nesse arquivo: JvEnterTab.PAS    http://jvcl.sourceforge.net   }                                             
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
|* 16/10/2004: Primeira Versao
|*    Daniel Simoes de Almeida
******************************************************************************}

{$I ACBr.inc}

unit ACBrEnterTab;

interface

uses
 ACBrBase,
 Classes,
 {$IFDEF VisualCLX}
  QControls, QForms, QStdCtrls,
 {$ELSE}
  Controls, Forms, StdCtrls,
 {$ENDIF}
 SysUtils ;


type
  THackForm = class(TForm);

  THackButtomControl = class(TButtonControl);

  TACBrEnterTab = class ( TACBrComponent )
  private
    FAllowDefault: Boolean;
    FEnterAsTab: Boolean;
    FOldKeyPreview : Boolean ;
    FOldOnKeyPress : TKeyPressEvent ; 
    procedure SetEnterAsTab(const Value: Boolean);
  public
    constructor Create(AOwner: TComponent ); override ;
    destructor Destroy ; override ;
    
    procedure DoEnterAsTab(AForm : TObject; var Key: Char);
  published
    property EnterAsTab: Boolean read FEnterAsTab write SetEnterAsTab
       default false ;
    property AllowDefault: Boolean read FAllowDefault write FAllowDefault
       default true ;
  end;

implementation

{ TACBrEnterTab }
constructor TACBrEnterTab.Create( AOwner: TComponent );
begin
  if not ( AOwner is TForm ) then
     raise Exception.Create('"Owner" do componente ACBrEnterTab deve ser do tipo TForm');

  inherited Create( AOwner );
  FEnterAsTab   := false ;
  FAllowDefault := True ;

  { Salvando estado das Propriedades do Form, que serao modificadas }
  with TForm( Owner ) do
  begin
     FOldKeyPreview := KeyPreview ;
     FOldOnKeyPress := OnKeyPress ;
  end ;
end;


destructor TACBrEnterTab.Destroy;
begin
  { Restaurando estado das propriedades de Form modificadas }
  if Assigned( Owner ) then
     if not (csFreeNotification in Owner.ComponentState) then
        with TForm( Owner ) do
        begin
           KeyPreview := FOldKeyPreview ;
           OnKeyPress := FOldOnKeyPress ;
        end ;

  inherited Destroy ;
end;

procedure TACBrEnterTab.DoEnterAsTab(AForm: TObject; var Key: Char);
Var
  DoClick : Boolean ;
begin
  try
     if not (AForm is TForm) then
        exit ;

     If Key = #13 Then
     begin
        if (TForm(AForm).ActiveControl is TButtonControl) and FAllowDefault then
        begin
           {$IFDEF VisualCLX}
            TButtonControl( TForm(AForm).ActiveControl ).AnimateClick ;
           {$ELSE}
             DoClick := True;
            {$IFDEF FPC}
             {$IFNDEF Linux}
              DoClick := False;  // Para evitar Click ocorre 2x em FPC com Win32
             {$ENDIF}
            {$ENDIF}
            if DoClick then
               THackButtomControl( TForm(AForm).ActiveControl ).Click ;
           {$ENDIF}
           exit ;
        end ;

        {$IFDEF FPC}
         THackForm( AForm ).SelectNext( Screen.ActiveControl, True, True );
        {$ELSE}
         THackForm( AForm ).SelectNext( TForm(AForm).ActiveControl, True, True );
        {$ENDIF}
     end ;
  finally
     if Assigned( FOldOnKeyPress ) then
        FOldOnKeyPress( AForm, Key ) ;

     If Key = #13 Then
        Key := #0;
  end ;
end;

procedure TACBrEnterTab.SetEnterAsTab(const Value: Boolean);
begin
  if Value = FEnterAsTab then exit ;

  if not (csDesigning in ComponentState) then
  begin
     with TForm( Owner ) do
     begin
        if Value then
         begin
           KeyPreview := true ;
           OnKeyPress := DoEnterAsTab ;
         end
        else
         begin
           KeyPreview := FOldKeyPreview ;
           OnKeyPress := FOldOnKeyPress ;
         end ;
     end ;
  end ;

  FEnterAsTab := Value;
end;

end.

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
|* 27/05/2004: Primeira Versao
|*    Daniel Simoes de Almeida
|*    Cria�ao de um arquivo unico para registro dos Componentes
******************************************************************************}

{$I ACBr.inc}
unit ACBrDiversosReg;

interface
Uses Classes ,
    {$IFDEF VisualCLX}
      QDialogs
    {$ELSE}
      Dialogs
      {$IFNDEF FPC}
        {$WARN UNIT_PLATFORM OFF}, FileCtrl {$WARN UNIT_PLATFORM ON}
      {$ENDIF}
    {$ENDIF},
    {$IFDEF FPC}
       LResources, LazarusPackageIntf, PropEdits, componenteditors
    {$ELSE}
       {$IFNDEF COMPILER6_UP}
          DsgnIntf
       {$ELSE}
          DesignIntf,
          DesignEditors
       {$ENDIF}
    {$ENDIF} ;

type
  { Editor de Componente para mostrar a Calculadora}
  TACBrCalculadoraEditor = class( TComponentEditor )
  public
    procedure Edit; override;
  end;

  { Editor de Componente para ACBrFala falar em Design }
  TACBrFalaEditor = class( TComponentEditor )
  public
    procedure Edit; override;
  end;

  { Editor de Proriedades de Componente para chamar OpenDialog }
  TACBrDirProperty = class( TStringProperty )
  public
    procedure Edit; override;
    function GetAttributes: TPropertyAttributes; override;
  end;

procedure Register;

implementation
Uses ACBrEnterTab, ACBrUtil, ACBrGIF, ACBrCargaBal,
     ACBrCalculadora, ACBrExtenso, ACBrTroco, ACBrValidador,
     ACBrCMC7, ACBrFala, ACBrBarCode, ACBrInStore, SysUtils;

{$IFNDEF FPC}
   {$R ACBrDiversos.dcr}
{$ENDIF}

procedure Register;
begin
  RegisterComponents('ACBr', [TACBrCalculadora, TACBrCMC7, TACBrExtenso, TACBrTroco,
     TACBrValidador, TACBrFala, TACBrEnterTab, TACBrGIF, TACBrBarCode, TACBrInStore, TACBrCargaBal]);

  { Registrando os Editores de Propriedade }
  RegisterPropertyEditor(TypeInfo(String), TACBrFala, 'OrigemArquivos',
     TACBrDirProperty);

  { Registrando os Editores de Componente }
  RegisterComponentEditor(TACBrCalculadora, TACBrCalculadoraEditor);
  RegisterComponentEditor(TACBrFala, TACBrFalaEditor);
end;


{ TACBrCalculadoraEditor }

procedure TACBrCalculadoraEditor.Edit;
begin
  with Component as TACBrCalculadora do
     Execute ;
end;

{ TACBrFalaEditor }

procedure TACBrFalaEditor.Edit;
begin
  with Component as TACBrFala do
     Falar ;
end;

{ TACBrDirProperty }

procedure TACBrDirProperty.Edit;
Var
{$IFNDEF VisualCLX} Dir : String ; {$ELSE} Dir : WideString ; {$ENDIF}
begin
  {$IFNDEF VisualCLX}
  Dir := GetValue ;
  if SelectDirectory(Dir,[],0) then
     SetValue( Dir ) ;
  {$ELSE}
  Dir := '' ;
  if SelectDirectory('Selecione o Diret�rio','',Dir) then
     SetValue( Dir ) ;
  {$ENDIF}
end;

function TACBrDirProperty.GetAttributes: TPropertyAttributes;
begin
  Result := [paDialog];
end;

{$IFDEF FPC}
initialization
   {$i ACBrDiversos.lrs}
{$ENDIF}

end.

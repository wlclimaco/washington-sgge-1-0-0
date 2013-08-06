{******************************************************************************}
{ Projeto: Componente ACBrMDFe                                                 }
{  Biblioteca multiplataforma de componentes Delphi                            }
{                                                                              }
{  Voc� pode obter a �ltima vers�o desse arquivo na pagina do Projeto ACBr     }
{ Componentes localizado em http://www.sourceforge.net/projects/acbr           }
{                                                                              }
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
|* 01/08/2012: Italo Jurisato Junior
|*  - Doa��o do componente para o Projeto ACBr
******************************************************************************}

{$I ACBr.inc}

unit ACBrMDFeReg;

interface

uses
  SysUtils, Classes,
  pcnConversao, ACBrMDFe,
  {$IFDEF VisualCLX} QDialogs {$ELSE} Dialogs, FileCtrl {$ENDIF},
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
  { Editor de Proriedades de Componente para mostrar o AboutACBr }
  TACBrAboutDialogProperty = class(TPropertyEditor)
  public
    procedure Edit; override;
    function GetAttributes: TPropertyAttributes; override;
    function GetValue: string; override;
  end;

  THRWEBSERVICEUFProperty = class( TStringProperty )
  public
    function GetAttributes: TPropertyAttributes; override;
    procedure GetValues( Proc : TGetStrProc); override;
  end;

  { Editor de Proriedades de Componente para chamar OpenDialog }
  TACBrMDFeDirProperty = class( TStringProperty )
  public
    procedure Edit; override;
    function GetAttributes: TPropertyAttributes; override;
  end;

procedure Register;

implementation

uses ACBrMDFeConfiguracoes;

{$IFNDEF FPC}
   {$R ACBrMDFe.dcr}
{$ENDIF}

procedure Register;
begin
  RegisterComponents('ACBr', [TACBrMDFe]);

  RegisterPropertyEditor(TypeInfo(TACBrMDFeAboutInfo), nil, 'AboutACBrMDFe',
     TACBrAboutDialogProperty);

  RegisterPropertyEditor(TypeInfo(TCertificadosConf), TConfiguracoes, 'Certificados',
    TClassProperty);

  RegisterPropertyEditor(TypeInfo(TConfiguracoes), TACBrMDFe, 'Configuracoes',
    TClassProperty);

  RegisterPropertyEditor(TypeInfo(TWebServicesConf), TConfiguracoes, 'WebServices',
    TClassProperty);

  RegisterPropertyEditor(TypeInfo(String), TWebServicesConf, 'UF',
     THRWEBSERVICEUFProperty);

  RegisterPropertyEditor(TypeInfo(TGeralConf), TConfiguracoes, 'Geral',
    TClassProperty);

  RegisterPropertyEditor(TypeInfo(String), TGeralConf, 'PathSalvar',
     TACBrMDFeDirProperty);

  RegisterPropertyEditor(TypeInfo(TArquivosConf), TConfiguracoes, 'Arquivos',
    TClassProperty);

  RegisterPropertyEditor(TypeInfo(String), TArquivosConf, 'PathMDFe',
     TACBrMDFeDirProperty);

  RegisterPropertyEditor(TypeInfo(String), TArquivosConf, 'PathCan',
     TACBrMDFeDirProperty);

  RegisterPropertyEditor(TypeInfo(String), TArquivosConf, 'PathInu',
     TACBrMDFeDirProperty);

  RegisterPropertyEditor(TypeInfo(String), TArquivosConf, 'PathDPEC',
     TACBrMDFeDirProperty);
end;

{ TACBrAboutDialogProperty }
procedure TACBrAboutDialogProperty.Edit;
begin
  ACBrAboutDialog;
end;

function TACBrAboutDialogProperty.GetAttributes: TPropertyAttributes;
begin
  Result := [paDialog, paReadOnly];
end;

function TACBrAboutDialogProperty.GetValue: string;
begin
  Result := 'Vers�o: ' + ACBRMDFe_VERSAO;
end;

{ THRWEBSERVICEUFProperty }

function THRWEBSERVICEUFProperty.GetAttributes: TPropertyAttributes;
begin
  Result := [paValueList, paAutoUpdate];
end;

procedure THRWEBSERVICEUFProperty.GetValues(Proc: TGetStrProc);
var
 i : integer;
begin
  inherited;
  for i:= 0 to High(NFeUF) do
    Proc(NFeUF[i]);
end;

{ TACBrMDFeDirProperty }

procedure TACBrMDFeDirProperty.Edit;
Var
{$IFNDEF VisualCLX} Dir : String; {$ELSE} Dir : WideString; {$ENDIF}
begin
  {$IFNDEF VisualCLX}
  Dir := GetValue;
  if SelectDirectory(Dir,[],0) then
     SetValue( Dir );
  {$ELSE}
  Dir := '';
  if SelectDirectory('Selecione o Diret�rio','',Dir) then
     SetValue( Dir );
  {$ENDIF}
end;

function TACBrMDFeDirProperty.GetAttributes: TPropertyAttributes;
begin
  Result := [paDialog];
end;

initialization

{$IFDEF FPC}
   {$I ACBrMDFe.lrs}
{$ENDIF}

end.

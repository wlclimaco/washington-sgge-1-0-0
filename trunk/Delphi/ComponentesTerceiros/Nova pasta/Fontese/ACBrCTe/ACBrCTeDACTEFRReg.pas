{******************************************************************************}
{ Projeto: Componente ACBrCTe                                                  }
{ Biblioteca multiplataforma de componentes Delphi para emiss�o de Conhecimento}
{ Transporte eletr�nica - CTe - http://www.cte.fazenda.gov.br                  }
{                                                                              }
{ Direitos Autorais Reservados (c) 2008 Wemerson Souto                         }
{                                       Daniel Simoes de Almeida               }
{                                       Andr� Ferreira de Moraes               }
{                                                                              }
{ Colaboradores nesse arquivo:                                                 }
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
|* 30/03/2011: Jeickson Gobeti
|*  - Inicio do desenvolvimento Dacte FastReport
******************************************************************************}
{$I ACBr.inc}

unit ACBrCTeDACTEFRReg;

interface

uses
  SysUtils, Classes, ACBrCTeDACTEFR,
  {$IFDEF VisualCLX} QDialogs {$ELSE} Dialogs{$ENDIF},
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

Type
  { Editor de Proriedades de Componente para chamar OpenDialog dos Relatorios }
  TACBrCTeDACTEFRFileNameProperty = class(TStringProperty)
  public
    procedure Edit; override;
    function GetAttributes: TPropertyAttributes; override;
  end;


procedure Register;

implementation

{$IFNDEF FPC}
   {$R ACBrCTe.dcr}
{$ENDIF}

procedure Register;
begin
  RegisterComponents('ACBr', [TACBrCTeDACTEFR]);

  RegisterPropertyEditor(TypeInfo(String), TACBrCTeDACTEFR, 'FastFile',
     TACBrCTeDACTEFRFileNameProperty);
end;

{ TACBrNFeDANFERaveFileNameProperty }

procedure TACBrCTeDACTEFRFileNameProperty.Edit;
var Dlg : TOpenDialog ;
begin
  Dlg := TOpenDialog.Create(nil);
  try
     Dlg.FileName   := GetValue ;
     Dlg.InitialDir := ExtractFilePath(GetValue) ;
     Dlg.Filter     := 'Arquivos do FastReport|*.fr3|Todos os arquivos|*.*' ;

     if Dlg.Execute then
        SetValue(Dlg.FileName);
  finally
     Dlg.Free ;
  end ;
end;

function TACBrCTeDACTEFRFileNameProperty.GetAttributes: TPropertyAttributes;
begin
  Result := [paDialog];
end;

end.

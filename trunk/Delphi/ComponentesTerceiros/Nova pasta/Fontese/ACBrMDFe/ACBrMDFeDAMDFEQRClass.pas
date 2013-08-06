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

unit ACBrMDFeDAMDFEQRClass;

interface

uses
  Forms, SysUtils, Classes,
  ACBrMDFeDAMDFEClass, pmdfeMDFe;

type
  TACBrMDFeDAMDFEQR = class( TACBrMDFeDAMDFEClass )
   private
   public
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
    procedure ImprimirDAMDFE(MDFe: TMDFe = nil); override;
    procedure ImprimirDAMDFEPDF(MDFe: TMDFe = nil); override;
  end;

implementation

uses
 StrUtils, Dialogs,
 ACBrUtil, ACBrMDFe, ACBrMDFeUtil, ACBrMDFeDAMDFEQRRetrato;

constructor TACBrMDFeDAMDFEQR.Create(AOwner: TComponent);
begin
  inherited create( AOwner );
end;

destructor TACBrMDFeDAMDFEQR.Destroy;
begin
  inherited Destroy;
end;

procedure TACBrMDFeDAMDFEQR.ImprimirDAMDFE(MDFe: TMDFe = nil);
var
 i: Integer;
 fqrDAMDFEQRRetrato: TfqrDAMDFEQRRetrato;
 sProt: String;
begin
  fqrDAMDFEQRRetrato := TfqrDAMDFEQRRetrato.Create(Self);

  sProt := TACBrMDFe(ACBrMDFe).DAMDFE.ProtocoloMDFe;
  fqrDAMDFEQRRetrato.ProtocoloMDFe( sProt );

  if MDFe = nil then
   begin
     for i:= 0 to TACBrMDFe(ACBrMDFe).Manifestos.Count-1 do
      begin
        fqrDAMDFEQRRetrato.Imprimir( TACBrMDFe(ACBrMDFe).Manifestos.Items[i].MDFe
                                   , Logo
                                   , Email
                                   , ExpandirLogoMarca
                                   , Fax
                                   , NumCopias
                                   , Sistema
                                   , Site
                                   , Usuario
                                   , MostrarPreview
                                   , MargemSuperior
                                   , MargemInferior
                                   , MargemEsquerda
                                   , MargemDireita
                                   , Impressora );
      end;
   end
  else
     fqrDAMDFEQRRetrato.Imprimir( MDFe
                                , Logo
                                , Email
                                , ExpandirLogoMarca
                                , Fax
                                , NumCopias
                                , Sistema
                                , Site
                                , Usuario
                                , MostrarPreview
                                , MargemSuperior
                                , MargemInferior
                                , MargemEsquerda
                                , MargemDireita
                                , Impressora );

  fqrDAMDFEQRRetrato.Free;
end;

procedure TACBrMDFeDAMDFEQR.ImprimirDAMDFEPDF(MDFe: TMDFe = nil);
var
 NomeArq: String;
 i: Integer;
 fqrDAMDFEQRRetrato: TfqrDAMDFEQRRetrato;
 sProt: String;
begin
  fqrDAMDFEQRRetrato := TfqrDAMDFEQRRetrato.Create(Self);

  sProt := TACBrMDFe(ACBrMDFe).DAMDFe.ProtocoloMDFe;
  fqrDAMDFEQRRetrato.ProtocoloMDFe( sProt );
  if MDFe = nil then
  begin
    for i:= 0 to TACBrMDFe(ACBrMDFe).Manifestos.Count-1 do
    begin
      NomeArq := StringReplace(TACBrMDFe(ACBrMDFe).Manifestos.Items[i].MDFe.infMDFe.ID,'MDFe', '', [rfIgnoreCase]);
      NomeArq := PathWithDelim(Self.PathPDF)+NomeArq+'.pdf';

      fqrDAMDFEQRRetrato.SavePDF( NomeArq
                                 ,TACBrMDFe(ACBrMDFe).Manifestos.Items[i].MDFe
                                 , Logo
                                 , Email
                                 , ExpandirLogoMarca
                                 , Fax
                                 , NumCopias
                                 , Sistema
                                 , Site
                                 , Usuario
                                 , MargemSuperior
                                 , MargemInferior
                                 , MargemEsquerda
                                 , MargemDireita );
    end;
  end
  else
  begin
    NomeArq := StringReplace(MDFe.infMDFe.ID,'MDFe', '', [rfIgnoreCase]);
    NomeArq := PathWithDelim(Self.PathPDF)+NomeArq+'.pdf';

    fqrDAMDFEQRRetrato.SavePDF( NomeArq
                              , MDFe
                              , Logo
                              , Email
                              , ExpandirLogoMarca
                              , Fax
                              , NumCopias
                              , Sistema
                              , Site
                              , Usuario
                              , MargemSuperior
                              , MargemInferior
                              , MargemEsquerda
                              , MargemDireita );
  end;

  fqrDAMDFEQRRetrato.Free;
end;

end.

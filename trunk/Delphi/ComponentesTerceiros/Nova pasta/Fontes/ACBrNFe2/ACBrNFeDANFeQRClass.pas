{******************************************************************************}
{ Projeto: Componente ACBrNFe                                                  }
{  Biblioteca multiplataforma de componentes Delphi para emiss�o de Nota Fiscal}
{ eletr�nica - NFe - http://www.nfe.fazenda.gov.br                          }
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
|* 11/12/2009: Emerson Crema
|*  - Implementado fqrDANFeQRRetrato.ProtocoloNFE( sProt ).
|* 16/12/2008: Wemerson Souto
|*  - Doa��o do componente para o Projeto ACBr
|* 20/08/2009: Caique Rodrigues
|*  - Doa��o units para gera��o do Danfe via QuickReport
|* 23/11/2010: Peterson de Cerqueira Matos
|*  - Tratamento das propriedades "CasasDecimais._qCom", "CasasDecimais._vUnCom",
|*    "Impressora"
|* 20/05/2011: Peterson de Cerqueira Matos
|*  - Tratamento da propriedade "ExibirResumoCanhoto_Texto"
******************************************************************************}
{$I ACBr.inc}
unit ACBrNFeDANFeQRClass;

interface

uses
 Forms, SysUtils, Classes,
 ACBrNFeDANFeQR, ACBrNFeDANFEClass, pcnNFe, pcnConversao;

type
  TACBrNFeDANFEQR = class( TACBrNFeDANFEClass )
   private
   public
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
    procedure ImprimirDANFE(NFE : TNFe = nil); override ;
    procedure ImprimirDANFEPDF(NFE : TNFe = nil); override ;
  end;

implementation

uses
 StrUtils, Dialogs,
 ACBrNFe, ACBrNFeUtil, ACBrUtil, ACBrNFeDANFeQRRetrato{, ACBrNFeDANFeQRNFCe};

constructor TACBrNFeDANFEQR.Create(AOwner: TComponent);
begin
  inherited create( AOwner );
end;

destructor TACBrNFeDANFEQR.Destroy;
begin
  inherited Destroy ;
end;


procedure TACBrNFeDANFEQR.ImprimirDANFE(NFE : TNFe = nil);
var
  i : Integer;
  fqrDANFeQRRetrato : TfqrDANFeQR; //TfqrDANFeQRRetrato;
  sProt : String;
begin
  case TipoDANFe of
    tiNFCe: begin
//           fqrDANFeQRRetrato := TfqrDANFeQRNFCe.Create(Self);
          end;
     else begin // tiRetrato
           fqrDANFeQRRetrato := TfqrDANFeQRRetrato.Create(Self);
          end;
  end;

//  fqrDANFeQRRetrato := TfqrDANFeQRRetrato.Create(Self);
  sProt := TACBrNFe(ACBrNFe).DANFE.ProtocoloNFe ;
//  fqrDANFeQRRetrato.ProtocoloNFE( sProt ) ;

  if NFE = nil then
   begin
     for i:= 0 to TACBrNFe(ACBrNFe).NotasFiscais.Count-1 do
      begin
        fqrDANFeQRRetrato.Imprimir(   TACBrNFe(ACBrNFe).NotasFiscais.Items[i].NFe
                                    , Logo
                                    , Email
                                    , ExibirResumoCanhoto
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
                                    , CasasDecimais._qCom
                                    , CasasDecimais._vUnCom
                                    , Impressora
                                    , ExibirResumoCanhoto_Texto
                                    , ExpandirLogoMarca
                                    , NFeCancelada  //Incluido por Luis Fernando em  22/01/2013
                                    , LocalImpCanhoto ); //Incluido por Luis Fernando em  22/01/2013);
      end;
   end
  else
     fqrDANFeQRRetrato.Imprimir(  NFe
                                , Logo
                                , Email
                                , ExibirResumoCanhoto
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
                                , CasasDecimais._qCom
                                , CasasDecimais._vUnCom
                                , Impressora
                                , ExibirResumoCanhoto_Texto
                                , ExpandirLogoMarca
                                , NFeCancelada  //Incluido por Luis Fernando em  22/01/2013
                                , LocalImpCanhoto ); //Incluido por Luis Fernando em  22/01/2013););

  fqrDANFeQRRetrato.Free;
end;

procedure TACBrNFeDANFEQR.ImprimirDANFEPDF(NFE : TNFe = nil);
var
  NomeArq : String;
  i : Integer;
  fqrDANFeQRRetrato : TfqrDANFeQR; //TfqrDANFeQRRetrato;
  sProt : String;
begin
  case TipoDANFe of
    tiNFCe: begin
//           fqrDANFeQRRetrato := TfqrDANFeQRNFCe.Create(Self);
          end;
     else begin // tiRetrato
           fqrDANFeQRRetrato := TfqrDANFeQRRetrato.Create(Self);
          end;
  end;

//  fqrDANFeQRRetrato := TfqrDANFeQRRetrato.Create(Self);
  sProt := TACBrNFe(ACBrNFe).DANFE.ProtocoloNFe ;
//  fqrDANFeQRRetrato.ProtocoloNFE( sProt ) ;

  if NFE = nil then
   begin
     for i:= 0 to TACBrNFe(ACBrNFe).NotasFiscais.Count-1 do
      begin
        NomeArq := StringReplace(TACBrNFe(ACBrNFe).NotasFiscais.Items[i].NFe.infNFe.ID,'NFe', '', [rfIgnoreCase]);
        NomeArq := PathWithDelim(Self.PathPDF)+NomeArq+'.pdf';

        fqrDANFeQRRetrato.SavePDF(  NomeArq
                                    ,TACBrNFe(ACBrNFe).NotasFiscais.Items[i].NFe
                                    , Logo
                                    , Email
                                    , ExibirResumoCanhoto
                                    , Fax
                                    , NumCopias
                                    , Sistema
                                    , Site
                                    , Usuario
                                    , MargemSuperior
                                    , MargemInferior
                                    , MargemEsquerda
                                    , MargemDireita
                                    , CasasDecimais._qCom
                                    , CasasDecimais._vUnCom
                                    , ExibirResumoCanhoto_Texto
                                    , ExpandirLogoMarca
                                    , NFeCancelada  //Incluido por Luis Fernando em  22/01/2013
                                    , LocalImpCanhoto ); //Incluido por Luis Fernando em  22/01/2013););
      end;
   end
   else
   begin
     NomeArq := StringReplace(NFe.infNFe.ID,'NFe', '', [rfIgnoreCase]);
     NomeArq := PathWithDelim(Self.PathPDF)+NomeArq+'.pdf';
     fqrDANFeQRRetrato.SavePDF( NomeArq
                                , NFe
                                , Logo
                                , Email
                                , ExibirResumoCanhoto
                                , Fax
                                , NumCopias
                                , Sistema
                                , Site
                                , Usuario
                                , MargemSuperior
                                , MargemInferior
                                , MargemEsquerda
                                , MargemDireita
                                , CasasDecimais._qCom
                                , CasasDecimais._vUnCom
                                , ExibirResumoCanhoto_Texto
                                , ExpandirLogoMarca
                                , NFeCancelada  //Incluido por Luis Fernando em  22/01/2013
                                , LocalImpCanhoto ); //Incluido por Luis Fernando em  22/01/2013););
   end;

  fqrDANFeQRRetrato.Free;
end;

end.

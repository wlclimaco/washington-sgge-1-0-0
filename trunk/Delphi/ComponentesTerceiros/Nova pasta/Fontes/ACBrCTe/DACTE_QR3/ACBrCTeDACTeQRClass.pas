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
|*  - Implementado fqrDACTeQRRetrato.ProtocoloNFE( sProt ).
|* 16/12/2008: Wemerson Souto
|*  - Doa��o do componente para o Projeto ACBr
|* 20/08/2009: Caique Rodrigues
|*  - Doa��o units para gera��o do DANFe via QuickReport
******************************************************************************}

{$I ACBr.inc}

unit ACBrCTeDACTeQRClass;

interface

uses
 Forms, SysUtils, Classes, QRPrntr,
 pcnConversao, pcteCTe, ACBrCTeDACTeQR, ACBrCTeDACTeClass,
 ACBrCTeDACTeQRRetrato, ACBrCTeDACTeQRRetratoA5;

type
  TACBrCTeDACTeQR = class(TACBrCTeDACTeClass)
  private
    FPosRecibo: TPosRecibo;
  public
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
    procedure ImprimirDACTe(CTe: TCTe = nil); override;
    procedure ImprimirDACTePDF(CTe: TCTe = nil); override;
//    procedure SetTamanhoPapel(Value: TpcnTamanhoPapel); virtual;
  published
    property PosRecibo: TPosRecibo read FPosRecibo write FPosRecibo default prCabecalho;
//    property TamanhoPapel: TpcnTamanhoPapel read FTamanhoPapel write SetTamanhoPapel;
  end;

implementation

uses StrUtils, Dialogs, ACBrUtil, ACBrCTe, ACBrCteUtil;

constructor TACBrCTeDACTeQR.Create(AOwner: TComponent);
begin
  inherited create(AOwner);
end;

destructor TACBrCTeDACTeQR.Destroy;
begin
  inherited Destroy;
end;

procedure TACBrCTeDACTeQR.ImprimirDACTe(CTe: TCTe = nil);
var
  i     : Integer;
  sProt : string;

  frmDACTeQRRetrato : TfrmDACTeQR; //TfrmDACTeQRRetrato;
begin
  case TamanhoPapel of
    tpA5: begin
           frmDACTeQRRetrato := TfrmDACTeQRRetratoA5.Create(Self);
          {$IFDEF QReport_PDF}
           frmDACTeQRRetrato.QRCTe.Page.PaperSize := A5Trans;
          {$ELSE}
           frmDACTeQRRetrato.QRCTe.Page.PaperSize := A5;
          {$ENDIF}
           frmDACTeQRRetrato.QRCTe.Page.Length    := 148.0;
           frmDACTeQRRetrato.QRCTe.Page.Width     := 210.0;
          end;
     else begin // tpA4
           frmDACTeQRRetrato := TfrmDACTeQRRetrato.Create(Self);
           frmDACTeQRRetrato.QRCTe.Page.PaperSize := A4;
           frmDACTeQRRetrato.QRCTe.Page.Length    := 297.0;
           frmDACTeQRRetrato.QRCTe.Page.Width     := 210.0;
          end;
  end;

//  frmDACTeQRRetrato := TfrmDACTeQRRetrato.Create(Self);
  sProt := TACBrCTe(ACBrCTe).DACTe.ProtocoloCTe;
//  frmDACTeQRRetrato.ProtocoloCTe(sProt);

  if CTe = nil then
  begin
    for i := 0 to TACBrCTe(ACBrCTe).Conhecimentos.Count - 1 do
      frmDACTeQRRetrato.Imprimir(TACBrCTe(ACBrCTe).Conhecimentos.Items[i].CTe
                                    , Logo
                                    , Email
                                    , ImprimirHoraSaida
                                    , ExpandirLogoMarca
                                    , ImprimirHoraSaida_Hora
                                    , false
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
                                    , Impressora
                                    , PosRecibo);
  end
  else
    frmDACTeQRRetrato.Imprimir(CTe
                                , Logo
                                , Email
                                , ImprimirHoraSaida
                                , ExpandirLogoMarca
                                , ImprimirHoraSaida_Hora
                                , False
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
                                , Impressora
                                , PosRecibo);

  frmDACTeQRRetrato.Free;
end;

procedure TACBrCTeDACTeQR.ImprimirDACTePDF(CTe: TCTe = nil);
var
  i       : Integer;
  sProt   : String;
  NomeArq : string;

  frmDACTeQRRetrato : TfrmDACTeQR; //TfrmDACTeQRRetrato;
begin
  case TamanhoPapel of
    tpA5: begin
           frmDACTeQRRetrato := TfrmDACTeQRRetratoA5.Create(Self);
          {$IFDEF QReport_PDF}
           frmDACTeQRRetrato.QRCTe.Page.PaperSize := A5Trans;
          {$ELSE}
           frmDACTeQRRetrato.QRCTe.Page.PaperSize := A5;
          {$ENDIF}
           frmDACTeQRRetrato.QRCTe.Page.Length    := 148.0;
           frmDACTeQRRetrato.QRCTe.Page.Width     := 210.0;
          end;
     else begin // tpA4
           frmDACTeQRRetrato := TfrmDACTeQRRetrato.Create(Self);
           frmDACTeQRRetrato.QRCTe.Page.PaperSize := A4;
           frmDACTeQRRetrato.QRCTe.Page.Length    := 297.0;
           frmDACTeQRRetrato.QRCTe.Page.Width     := 210.0;
          end;
  end;

//  frmDACTeQRRetrato := TfrmDACTeQRRetrato.Create(Self);
  sProt := TACBrCTe(ACBrCTe).DACTe.ProtocoloCTe ;
//  frmDACTeQRRetrato.ProtocoloCTe( sProt ) ;

  if CTe = nil then
   begin
     for i:= 0 to TACBrCTe(ACBrCTe).Conhecimentos.Count-1 do
      begin
        NomeArq := StringReplace(TACBrCTe(ACBrCTe).Conhecimentos.Items[i].CTe.infCTe.ID,'CTe', '', [rfIgnoreCase]);
        NomeArq := PathWithDelim(Self.PathPDF)+NomeArq+'.pdf';

        frmDACTeQRRetrato.SavePDF(  NomeArq
                                    ,TACBrCTe(ACBrCTe).Conhecimentos.Items[i].CTe
                                    , Logo
                                    , Email
                                    , ImprimirHoraSaida
                                    , ExpandirLogoMarca
                                    , ImprimirHoraSaida_Hora
                                    , false
                                    , Fax
                                    , NumCopias
                                    , Sistema
                                    , Site
                                    , Usuario
                                    , MargemSuperior
                                    , MargemInferior
                                    , MargemEsquerda
                                    , MargemDireita
                                    , PosRecibo);
      end;
   end
  else
  begin
     NomeArq := StringReplace(CTe.infCTe.ID,'CTe', '', [rfIgnoreCase]);
     NomeArq := PathWithDelim(Self.PathPDF)+NomeArq+'.pdf';
     frmDACTeQRRetrato.SavePDF( NomeArq
                                , CTe
                                , Logo
                                , Email
                                , ImprimirHoraSaida
                                , ExpandirLogoMarca
                                , ImprimirHoraSaida_Hora
                                , False
                                , Fax
                                , NumCopias
                                , Sistema
                                , Site
                                , Usuario
                                , MargemSuperior
                                , MargemInferior
                                , MargemEsquerda
                                , MargemDireita
                                , PosRecibo);
  end;

  frmDACTeQRRetrato.Free;

end;
(*
procedure TACBrCTeDACTeQR.SetTamanhoPapel(Value: TpcnTamanhoPapel);
begin
  FTamanhoPapel := Value;
end;
*)
end.


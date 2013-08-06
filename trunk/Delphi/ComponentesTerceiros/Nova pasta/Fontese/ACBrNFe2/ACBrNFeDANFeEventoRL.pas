{******************************************************************************}
{ Projeto: Componente ACBrNFe                                                  }
{  Biblioteca multiplataforma de componentes Delphi para emiss�o de Nota Fiscal}
{ eletr�nica - NFe - http://www.nfe.fazenda.gov.br                             }
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
|* 14/03/2013: Peterson de Cerqueira Matos
|*  - In�cio da impress�o dos eventos em Fortes Report
******************************************************************************}
{$I ACBr.inc}
unit ACBrNFeDANFeEventoRL;

interface

uses
  SysUtils, Variants, Classes,
  {$IFDEF CLX}
  QGraphics, QControls, QForms, QDialogs, QExtCtrls, Qt,
  {$ELSE}
    {$IFDEF MSWINDOWS}Windows, Messages, {$ENDIF}
    Graphics, Controls, Forms, Dialogs, ExtCtrls,
  {$ENDIF}
  pcnEnvEventoNFe, pcnConversao, pcnNFe, ACBrNFeDANFeRLClass, ACBrDFeUtil,
  RLReport, RLPDFFilter, RLConsts, RLFilters, RLPrinters;

type
  TfrlDANFeEventoRL = class(TForm)
    RLEvento: TRLReport;
    RLPDFFilter1: TRLPDFFilter;
    function FormatarCEP(AValue: String): String;
    function FormatarFone(AValue: String): String;

  private
    { Private declarations }
  protected
    FNFe: TNFe;
    FEventoNFe: TInfEventoCollectionItem;
    FLogo: String;
    FMarcaDagua: String;
    FNumCopias: Integer;
    FSsitema: String;
    FUsuario: String;
    FMostrarPreview: Boolean;
    FNomeFonte: TNomeFonte;
    FNegrito: Boolean;
    FMargemSuperior: Double;
    FMargemInferior: Double;
    FMargemEsquerda: Double;
    FMargemDireita: Double;
    FImpressora: String;

  public
    { Public declarations }
    class procedure Imprimir(AEventoNFe: TInfEventoCollectionItem; ALogo: String = '';
                    AMarcaDagua: String = ''; ANumCopias: Integer = 1;
                    ASistema: String = ''; AUsuario: String = '';
                    AMostrarPreview: Boolean = True;
                    ANomeFonte: TNomeFonte = nfTimesNewRoman;
                    ANegrito: Boolean = True;
                    AMargemSuperior: Double = 0.7;
                    AMargemInferior: Double = 0.7;
                    AMargemEsquerda: Double = 0.7;
                    AMargemDireita: Double = 0.7;
                    AImpressora: String = '';
                    ANFe: TNFe = nil);

    class procedure SavePDF(AEventoNFe: TInfEventoCollectionItem; ALogo: String = '';
                    AMarcaDagua: String = ''; AFile: String = '';
                    ASistema: String = ''; AUsuario: String = '';
                    ANomeFonte: TNomeFonte = nfTimesNewRoman;
                    ANegrito: Boolean = True;
                    AMargemSuperior: Double = 0.7;
                    AMargemInferior: Double = 0.7;
                    AMargemEsquerda: Double = 0.7;
                    AMargemDireita: Double = 0.7;
                    ANFe: TNFe = nil);
  end;

implementation


{$R *.dfm}

class procedure TfrlDANFeEventoRL.Imprimir(AEventoNFe: TInfEventoCollectionItem; ALogo: String = '';
                    AMarcaDagua: String = ''; ANumCopias: Integer = 1;
                    ASistema: String = ''; AUsuario: String = '';
                    AMostrarPreview: Boolean = True;
                    ANomeFonte: TNomeFonte = nfTimesNewRoman;
                    ANegrito: Boolean = True;
                    AMargemSuperior: Double = 0.7;
                    AMargemInferior: Double = 0.7;
                    AMargemEsquerda: Double = 0.7;
                    AMargemDireita: Double = 0.7;
                    AImpressora: String = '';
                    ANFe: TNFe = nil);

begin
  with Create ( nil ) do
    try
      FEventoNFe := AEventoNFe;
      FLogo := ALogo;
      FMarcaDagua := AMarcaDagua;
      FNumCopias := ANumCopias;
      FSsitema := ASistema;
      FUsuario := AUsuario;
      FMostrarPreview := AMostrarPreview;
      FNomeFonte := ANomeFonte;
      FNegrito := ANegrito;
      FMargemSuperior := AMargemSuperior;
      FMargemInferior := AMargemInferior;
      FMargemEsquerda := AMargemEsquerda;
      FMargemDireita := AMargemDireita;
      FImpressora := AImpressora;

      if ANFe <> nil then
        FNFe := ANFe;

      if FImpressora > '' then
        RLPrinter.PrinterName := FImpressora;

      if FNumCopias > 0 then
        RLPrinter.Copies := FNumCopias
      else
        RLPrinter.Copies := 1;

      if FMostrarPreview = True then
        RLEvento.PreviewModal
      else
        RLEvento.Print;

    finally
      Free ;
    end ;
end;

class procedure TfrlDANFeEventoRL.SavePDF(AEventoNFe: TInfEventoCollectionItem; ALogo: String = '';
                    AMarcaDagua: String = ''; AFile: String = '';
                    ASistema: String = ''; AUsuario: String = '';
                    ANomeFonte: TNomeFonte = nfTimesNewRoman;
                    ANegrito: Boolean = True;
                    AMargemSuperior: Double = 0.7;
                    AMargemInferior: Double = 0.7;
                    AMargemEsquerda: Double = 0.7;
                    AMargemDireita: Double = 0.7;
                    ANFe: TNFe = nil);

begin
  with Create ( nil ) do
    try
      FEventoNFe := AEventoNFe;
      FLogo := ALogo;
      FMarcaDagua := AMarcaDagua;
      FSsitema := ASistema;
      FUsuario := AUsuario;
      FNomeFonte := ANomeFonte;
      FNegrito := ANegrito;
      FMargemSuperior := AMargemSuperior;
      FMargemInferior := AMargemInferior;
      FMargemEsquerda := AMargemEsquerda;
      FMargemDireita := AMargemDireita;

      if ANFe <> nil then
        FNFe := ANFe;

      RLEvento.SaveToFile(AFile);

    finally
      Free ;
    end ;
end;

{Fun��o original de ACBrNFeUtil modificada para exibir em outro formato}
function TfrlDANFeEventoRL.FormatarCEP(AValue: String): String;
var i, iZeros: Integer;
sCep: String;
begin
  if Length(AValue) <= 8 then
    begin
      iZeros := 8 - Length(AValue);
      sCep := AValue;
      For i := 1 to iZeros do
        begin
          sCep := '0' + sCep;
        end;
      Result := copy(sCep,1,5) + '-' + copy(sCep,6,3);
    end
  else
    Result := copy(AValue,1,5) + '-' + copy(AValue,6,3);
end;

{Fun��o original de ACBrNFeUtil modificada para exibir em outro formato}
function TfrlDANFeEventoRL.FormatarFone(AValue: String): String;
begin
  Result := AValue;
  if DFeUtil.NaoEstaVazio(AValue) then
  begin
    if Length(DFeUtil.LimpaNumero(AValue)) > 10 then AValue := copy(DFeUtil.LimpaNumero(AValue),2,10); //Casos em que o DDD vem com ZERO antes somando 3 digitos

    AValue := DFeUtil.Poem_Zeros(DFeUtil.LimpaNumero(AValue), 10);
    Result := '('+copy(AValue,1,2) + ') ' + copy(AValue,3,4) + '-' + copy(AValue,7,4);
  end;
end;

end.
